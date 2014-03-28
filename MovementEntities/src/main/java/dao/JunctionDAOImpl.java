package dao;

import entities.Junction;
import entities.JunctionRequest;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class JunctionDAOImpl implements JunctionDAO
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
     * @param junction The Junction object to persist
     */
    @Override
    public void create(Junction junction)
    {

    }

    /**
     * {@inheritDoc}
     * @param junction The edited Junction object
     */
    @Override
    public void edit(Junction junction)
    {

    }

    /**
     * {@inheritDoc}
     * @param junction The Junction object to remove
     */
    @Override
    public void remove(Junction junction)
    {

    }

    /**
     * {@inheritDoc}
     * @param junctionID The ID of the junction
     */
    @Override
    public Junction find(int junctionID)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param junctionIdentifier The SUMO Junction Identifier
     */
    @Override
    public Junction findByIdentifier(String junctionIdentifier)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param junction The Junction object to search in
     * @param index the index of the junction request
     */
    @Override
    public JunctionRequest findJunctionRequest(Junction junction, int index)
    {
        return null;
    }
}
