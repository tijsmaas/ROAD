package road.movemententities.entities;


import javax.persistence.*;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class JunctionRequest {
    @Id @GeneratedValue
    private int id;

    @Column(name = "requestIndex")
    protected int index;

    private String response;
    private String foes;
    private boolean cont;

    @ManyToOne
    private Junction junction;

    //region Properties
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public String getFoes()
    {
        return foes;
    }

    public void setFoes(String foes)
    {
        this.foes = foes;
    }

    public boolean isCont()
    {
        return cont;
    }

    public void setCont(boolean cont)
    {
        this.cont = cont;
    }

    public Junction getJunction()
    {
        return junction;
    }

    public void setJunction(Junction junction)
    {
        this.junction = junction;
    }
    //endregion
}
