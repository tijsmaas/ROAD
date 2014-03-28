package entities;

import java.util.List;

public class MovementMap
{
	private List<Edge> edges;
	private List<Junction> junctions;
	private List<Connection> connections;
	private List<Movement> movements;
	
	public MovementMap() 
	{
		
	}

	public List<Edge> getEdges()
	{
		return edges;
	}

	public void setEdges(List<Edge> edges)
	{
		this.edges = edges;
	}

	public List<Junction> getJunctions()
	{
		return junctions;
	}

	public void setJunctions(List<Junction> junctions)
	{
		this.junctions = junctions;
	}

	public List<Connection> getConnections()
	{
		return connections;
	}

	public void setConnections(List<Connection> connections)
	{
		this.connections = connections;
	}

	public List<Movement> getMovements()
	{
		return movements;
	}

	public void setMovements(List<Movement> movements)
	{
		this.movements = movements;
	}
}
