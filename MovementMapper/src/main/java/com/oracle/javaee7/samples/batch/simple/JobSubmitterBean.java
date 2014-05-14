package com.oracle.javaee7.samples.batch.simple;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Schedule;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class JobSubmitterBean
{
	public void startbatch() {
        {
	String jobName="PayrollJob";
	String selectedMonthYear= "JAN-2013";
	
   try { 
   	JobOperator jobOperator = BatchRuntime.getJobOperator();

       Properties props = new Properties();
       props.setProperty("Month-Year", selectedMonthYear);
       for(String job : jobOperator.getJobNames())
       		System.out.println("EXISTING JOB: "+job);
       
       System.out.println("Starting batch via servlet");
       long executionID = jobOperator.start(jobName, props);
   	
   		Thread.sleep(3000); 
   	
       System.out.println("Job with ID "+executionID+" started");
       JobExecution jobExec = jobOperator.getJobExecution(executionID);
       String status = jobExec.getBatchStatus().toString();
       System.out.println("Job status: "+status);

   } catch (Exception ex) {
   	ex.printStackTrace();
   }
}
}}
