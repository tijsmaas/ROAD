package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Niek  on 4/03/14..
 * © Aidas 2014
 */
@Entity
public class Movement
{
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar movementDate;

    @ManyToOne
    private Lane lane;

    @ManyToOne
    private Edge edge;

    @OneToMany
    private List<MovementVehicle> movementVehicles;
}
