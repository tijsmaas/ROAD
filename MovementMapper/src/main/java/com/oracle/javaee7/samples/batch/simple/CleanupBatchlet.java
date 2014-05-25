package com.oracle.javaee7.samples.batch.simple;

import javax.batch.api.AbstractBatchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent @Named("CleanupBatchlet")
public class CleanupBatchlet extends AbstractBatchlet
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    public CleanupBatchlet()
    {
    }

    @Override
    public String process() throws Exception
    {
        int mb = 1024*1024;
         
        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();
         
        System.out.println("##### Heap utilization statistics [MB] #####");
         
        //Print used memory
        System.out.println("Used Memory:"
            + (runtime.totalMemory() - runtime.freeMemory()) / mb);
 
        //Print free memory
        System.out.println("Free Memory:"
            + runtime.freeMemory() / mb);
         
        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);
 
        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
        
        System.out.println("Cleaning up...");
        em.getEntityManagerFactory().getCache().evictAll();
        
        return "COMPLETED";
    }
}
