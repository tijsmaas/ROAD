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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.Properties;
import javax.enterprise.context.Dependent;

import javax.xml.bind.JAXBElement;

import road.movemententities.entities.Lane;
import road.movemententities.entities.Movement;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleMovement;
import road.movementmapper.dao.MovementsDAO;
import sumo.movements.jaxb.TimestepType;

@Dependent
@Named("SimpleItemProcessor")
public class SimpleItemProcessor
        implements ItemProcessor
{

    @Inject
    private JobContext jobContext;

    @Inject
    private MovementsDAO movementsDAO;
    
    public SimpleItemProcessor() {}
    
    /**
     * Parse all timesteps and all vehicle movements within them.
     */
    public Object processItem(Object obj) throws Exception
    {
        long startTime = System.nanoTime();
        Properties jobParameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());

        
        Calendar basedate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss");
        basedate.setTime(sdf.parse((String)jobParameters.get("basedate")));

        TimestepType timestep = (TimestepType) obj;
        
        System.out.println("processing timestep "+timestep.getTime());
        List<Movement> movementsChunk = new ArrayList();

        for (Serializable a : timestep.getContent())
        {
            // The xml parser parses whitespace strings, this is a workaround
            if (a instanceof String) continue;
                
                sumo.movements.jaxb.EdgeType xmlEdge = (sumo.movements.jaxb.EdgeType) ((JAXBElement) a).getValue();

                for (sumo.movements.jaxb.LaneType xmlLane : xmlEdge.getLane())
                {
                    Lane lane = movementsDAO.getLaneById(xmlLane.getId());
                    // set datetime and timestep time of movement to now.
                    Movement movement = new Movement(basedate, timestep.getTime(), lane);
                    

                    List<VehicleMovement> movementVehicles = new ArrayList();

                    for (Serializable b : xmlLane.getContent())
                    {
                        if (b instanceof String) continue;

                        sumo.movements.jaxb.VehicleType xmlVehicle = (sumo.movements.jaxb.VehicleType) ((JAXBElement) b).getValue();
                        String cartrackerID = xmlVehicle.getId();

                        // Get vehicle by its license plate (<vehicle id="license">) or create a vehicle
                        Vehicle vehicle = movementsDAO.getOrCreateVehicleById(cartrackerID);

                        VehicleMovement movementVehicle = new VehicleMovement(
                                movement, vehicle, xmlVehicle.getPos(), xmlVehicle.getSpeed());
                        
                        // write for each vehicle
                        movementVehicles.add(movementVehicle);                        
                    }

                    // Add all moving vehicles to the movement
                    movement.setVehicleMovements(movementVehicles);
                    movementsChunk.add(movement);
                }
        }
        System.out.println("Processed in " + (System.nanoTime() - startTime) + "ns");

        return movementsChunk;
    }


}
