package com.oracle.javaee7.samples.batch.simple;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class JobReceiverBean {

    private static final String JOBNAME = "PayrollJob";

    public void startbatch(String movements, String insertDate) {
        {
            if(movements == null || insertDate == null) return;
            ////////////////////// TODO FIX ////////////////////////////////////
//            movements="/home/tijs/Downloads/verpl_systeem/verplaatsingen_20110209_small.xml";
            //movements="/home/tijs/Dropbox/S6Project/PTS ESD/SUMO-OSM files/generated files Tijs/movement_vehicle_gen_t0.xml";
            Properties props = new Properties();
            props.setProperty("inputfile", movements);
            props.setProperty("basedate", insertDate);
            //insertDate
            ////////////////////////////////////////////////////////////////////
            try {
                JobOperator jobOperator = BatchRuntime.getJobOperator();

                
                for (String job : jobOperator.getJobNames()) {
                    System.out.println("EXISTING JOB: " + job);
                }

                System.out.println("Starting batch via servlet");
                long executionID = jobOperator.start(JOBNAME, props);

                Thread.sleep(300);

                System.out.println("Job with ID " + executionID + " started");
                JobExecution jobExec = jobOperator.getJobExecution(executionID);
                String status = jobExec.getBatchStatus().toString();
                System.out.println("Job status: " + status);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
