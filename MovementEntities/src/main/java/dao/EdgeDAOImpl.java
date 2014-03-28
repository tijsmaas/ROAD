package dao;

import entities.Edge;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class EdgeDAOImpl implements EdgeDAO
{
    /**
     * {@inheritDoc}
     */
    @Override
    public int count()
    {
        return 0;
    }

    /**
     * {@inheritDoc}
     * @param edge The edge object to persist
     */
    @Override
    public void create(Edge edge)
    {

    }

    /**
     * {@inheritDoc}
     * @param edge The edited edge object
     */
    @Override
    public void edit(Edge edge)
    {

    }

    /**
     * {@inheritDoc}
     * @param edge The edge object to remove
     */
    @Override
    public void remove(Edge edge)
    {

    }

    /**
     * {@inheritDoc}
     * @param edgeID The ID of the edge to find
     * @return the found edge object
     */
    @Override
    public Edge find(int edgeID)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param edgeIdentifier The sumo Edge Identifier
     * @return the found Edge object.
     */
    @Override
    public Edge findByIdentifier(String edgeIdentifier)
    {
        return null;
    }
}
