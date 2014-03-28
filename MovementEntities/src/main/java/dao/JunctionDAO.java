package dao;

import entities.Junction;
import entities.JunctionRequest;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface JunctionDAO
{

    /**
     *
     * @return The number of junctions in the database
     */
    int count();

    /**
     * Persist a junction in the database
     * @param junction The Junction object to persist
     */
    void create(Junction junction);

    /**
     * Edit an existing junction
     * @param junction The edited Junction object
     */
    void edit(Junction junction);

    /**
     * Remove a junction from the database
     * @param junction The Junction object to remove
     */
    void remove(Junction junction);

    /**
     * Find a junction by ID
     * @param junctionID The ID of the junction
     * @return The found Junction object
     */
    Junction find(int junctionID);

    /**
     * Find a junction by the SUMO JunctionIdentifier
     * @param junctionIdentifier The SUMO Junction Identifier
     * @return The found Junction object
     */
    Junction findByIdentifier(String junctionIdentifier);

    /**
     * Finds a junction request in the junction
     * @param junction The Junction object to search in
     * @param index the index of the junction request
     * @return The JunctionRequest object found
     */
    JunctionRequest findJunctionRequest(Junction junction, int index);


}
