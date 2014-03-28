package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.awt.*;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Junction
{
    @Id
    @GeneratedValue
    private int id;
    private String junctionIdentifier;
    private double x;
    private double y;

    @OneToMany
    private List<Lane> incLanes;

    @OneToMany
    private List<Lane> intLanes;

    private Polygon shape;

    @OneToMany
    private List<JunctionRequest> requests;


}
