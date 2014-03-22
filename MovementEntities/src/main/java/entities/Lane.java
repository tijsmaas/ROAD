package entities;

import java.util.Vector;

/**
 * Created by Niek on 14/03/14.
 */
public class Lane {
	private String id; // example id: "-92_1" or ":-38_5_0"
    private String laneIdentifier; // not used?
    private Edge edge; // double relation, not needed?
    private int index;
    private float speed; // double?
    private float length; // double?
    private Vector position;
    
    public Lane(String id, int index)
	{
		this.id = id;
		this.index = index;
	}

	public String getId()
	{
		return id;
	}

	public int getIndex()
	{
		return index;
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
