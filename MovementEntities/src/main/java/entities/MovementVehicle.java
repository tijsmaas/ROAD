package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class MovementVehicle
{

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Movement movement;

    @ManyToOne
    private Vehicle vehicle;

    private double position;
    private double speed;
}
