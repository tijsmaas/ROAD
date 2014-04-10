import dao.EdgeDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 10/04/14.
 * © Aidas 2014
 */
@Named
@RequestScoped
public class DemoBean
{

    @Inject
    private EdgeDAO dao;

    private String result;

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public void doTest(){
        System.out.println("doing thigns");
        Long count = dao.count();
        this.result = count.toString();
    }
}
