package com.oracle.javaee7.samples.batch.simple;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class JobApplicationScoped
{
    
    @Inject
    private JobSubmitterBean jobsubmitter;
    
	public void startbatch() {
		jobsubmitter.startbatch();
	}

}
