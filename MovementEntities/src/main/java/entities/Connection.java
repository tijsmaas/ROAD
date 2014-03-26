package entities;

import javax.persistence.*;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class Connection
{
    @Id @GeneratedValue
    private int id;
	// required
    @ManyToOne
	private Edge from;

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

    @ManyToOne
	private TrafficLightProgram trafficLight; // we won't use this

	private int linkIndex; // we won't use this

    public Connection() {
    }

    public enum ConnectionDirection
	{
		STRAIGHT("s"), TURN("t"), LEFT("l"), RIGHT("r"), PARTIALLY_LEFT("L"), PARTIALLY_RIGHT("R"), INVALID("");

		private String text;

		ConnectionDirection(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return this.text;
		}

		public static ConnectionDirection fromString(String s)
		{
			if (s != null)
			{
				for (ConnectionDirection cd : ConnectionDirection.values())
				{
					if (s.equals(cd.text))
					{
						return cd;
					}
				}
			}
			return ConnectionDirection.INVALID;
		}
	}

	public enum ConnectionState
	{
		// TODO add conversion strings
		DEAD_END(""), EQUAL("="), MINOR_LINK("m"), MAJOR_LINK("M"), CONTROLLER_OFF(""), YELLOW_FLASHING(""), YELLOW_MINOR_LINK(""), YELLOW_MAJOR_LINK(""), RED(""), GREEN_MINOR(""), GREEN_MAJOR("");
		/*
		 * <xsd:attribute name="state" use="required">
		 * <xsd:enumeration value="M"/>
		 * <xsd:enumeration value="m"/>
		 * <xsd:enumeration value="o"/>
		 * <xsd:enumeration value="="/>
		 * <xsd:enumeration value="-"/>
		 * <xsd:enumeration value="s"/>
		 * <xsd:enumeration value="w"/>
		 */
		
		private String text;

		ConnectionState(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return this.text;
		}

		public static ConnectionState fromString(String s)
		{
			if (s != null)
			{
				for (ConnectionState cs : ConnectionState.values())
				{
					if (s.equals(cs.text))
					{
						return cs;
					}
				}
			}
			return ConnectionState.DEAD_END;
		}
	}

	public Connection(Edge from, Edge to, Lane fromLane, Lane toLane, String direction, String state)
	{
		if (from == null || to == null || fromLane == null || toLane == null || direction == null || state == null)
			throw new IllegalArgumentException("Required connection attributes are empty");
		this.from = from;
		this.to = to;
		this.fromLane = fromLane;
		this.toLane = toLane;
		this.direction = ConnectionDirection.valueOf(direction);
		this.state = ConnectionState.valueOf(state);
	}

	public void setVia(Lane via)
	{
		this.via = via;
	}

}
