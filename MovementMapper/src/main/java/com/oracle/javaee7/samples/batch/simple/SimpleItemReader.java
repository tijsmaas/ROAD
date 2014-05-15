/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.oracle.javaee7.samples.batch.simple;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBElement;
import road.movementparser.injectable.GenericParser;
import sumo.movements.jaxb.SumoNetstateType;
import sumo.movements.jaxb.TimestepType;

@Dependent
@Named("SimpleItemReader")
public class SimpleItemReader extends AbstractItemReader
{
    /* Package name of generated movement classes */

    private static final String SUMOMOVEMENTSJAXBPACKAGE = "sumo.movements.jaxb";

    @Inject
    private JobContext jobContext;

    @Inject
    private GenericParser jaxbparser;

    Iterator<TimestepType> timestepIterator;

    public SimpleItemReader()
    {
    }

    @Override
    public void open(Serializable e) throws Exception
    {
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        String filename = jobParameters.getProperty("inputfile");
        System.out.println("Opening file "+filename);
        File inputfile = getResourceFile(filename); 
        JAXBElement<SumoNetstateType> root = jaxbparser.parse(inputfile, SUMOMOVEMENTSJAXBPACKAGE);
        timestepIterator = root.getValue().getTimestep().iterator();
    }

    @Override
    public Object readItem() throws Exception
    {
        return timestepIterator.hasNext() ? timestepIterator.next() : null;
    }
    private File getResourceFile(String filename) {
        URL url = this.getClass().getResource("/"+filename);
        System.out.println("url: "+url.getPath());
        return new File(url.getFile());
    }

}
