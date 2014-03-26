package entities;

import javax.persistence.*;
import java.util.Vector;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class Lane {

    @Id @GeneratedValue
	private int id; // example id: "-92_1" or ":-38_5_0" //TODO: NOPE
    private String laneIdentifier; // not used?

    @ManyToOne
    private Edge edge; // double relation, not needed?
    private int index;
    private float speed; // double?
    private float length; // double?


    private String position;
    //TODO: Find a way to map vector
    
    public Lane(String id, int index)
	{
		this.laneIdentifier = id;
		this.index = index;
	}

    public Lane() {
    }

    public String getLaneIdentifier()
	{
		return this.laneIdentifier;
	}

	public int getIndex()
	{
		return this.index;
	}

	public void setSpeed(Float speed)
	{
		this.speed = speed;
	}

	public void setLength(Float length)
	{
		this.length = length;
	}
}
