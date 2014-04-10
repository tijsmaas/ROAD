package controller;

import dao.EdgeDAO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 10/04/14.
 * © Aidas 2014
 */
@Named
public class DemoBean
{
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
        System.out.println("Counting number of edges");
        Long count = dao.count();
        System.out.println("Number of edges in database: " + count.toString());
    }
}
