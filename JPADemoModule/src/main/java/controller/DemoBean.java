package controller;

import road.movemententityaccess.dao.EdgeDAO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 10/04/14.
 * © Aidas 2014
 *
 * Simple demo bean that injects a DAO and queries it
 */
@Named
public class DemoBean
{
    //Inject the required DAO, in order to make injection possible from an external lib, see the producer package
    @Inject
    private EdgeDAO dao;

    private String content;



    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }


    public void doTest(){
        //Execute the count method of the road.movemententityaccess.dao, returning the number of edges
        Long count = dao.count();

        System.out.println("Number of edges in database: " + count.toString());
    }
}
