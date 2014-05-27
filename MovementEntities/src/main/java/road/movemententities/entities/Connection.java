package road.movemententities.entities;

import road.movemententities.entities.enumerations.ConnectionDirection;
import road.movemententities.entities.enumerations.ConnectionState;

import javax.persistence.*;

/**
 * Created by Niek on 14/03/14.
 *  Aidas 2014
 */
@Entity
public class Connection implements MovementEntity<Integer>
{

    @Id
    @GeneratedValue
    private int id;

    //The from Edge of this connection
    @ManyToOne
    private Edge from;

    //The to edge of this connection
    @ManyToOne
    private Edge to;

    @ManyToOne
    private Lane fromLane;

    @ManyToOne
    private Lane toLane;

    @Enumerated(EnumType.STRING)
    private ConnectionDirection direction;

    @Enumerated(EnumType.STRING)
    private ConnectionState state;

    // optional
    @ManyToOne
    private Lane via;

    // Empty constructor for JPA
    public Connection() { }

    public Connection(Edge from, Edge to, Lane fromLane, Lane toLane, String direction, String state)
    {
        if (from == null || to == null || /* fromLane == null || toLane == null || */ direction == null || state == null)
        {
            throw new IllegalArgumentException("Connection::Required connection attributes are empty: " + (from == null) + (to == null) + (fromLane == null) + (toLane == null) + (direction == null) + (state == null));
        }

        this.from = from;
        this.to = to;
        this.fromLane = fromLane;
        this.toLane = toLane;
        this.direction = ConnectionDirection.fromString(direction);
        this.state = ConnectionState.fromString(state);
    }

    //region Properties
    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Edge getFrom()
    {
        return from;
    }

    public void setFrom(Edge from)
    {
        this.from = from;
    }

    public Edge getTo()
    {
        return to;
    }

    public void setTo(Edge to)
    {
        this.to = to;
    }

    public Lane getFromLane()
    {
        return fromLane;
    }

    public void setFromLane(Lane fromLane)
    {
        this.fromLane = fromLane;
    }

    public Lane getToLane()
    {
        return toLane;
    }

    public void setToLane(Lane toLane)
    {
        this.toLane = toLane;
    }

    public ConnectionDirection getDirection()
    {
        return direction;
    }

    public void setDirection(ConnectionDirection direction)
    {
        this.direction = direction;
    }

    public ConnectionState getState()
    {
        return state;
    }

    public void setState(ConnectionState state)
    {
        this.state = state;
    }

    public Lane getVia()
    {
        return via;
    }

    public void setVia(Lane via)
    {
        this.via = via;
    }
    //endregion

}
