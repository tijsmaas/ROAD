/*
 * Copyright by AIDaS.
 */

package aidas.utils;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class represents the helper functions for the jpa queries.
 *
 * @author Geert
 */
public class Query {

    /**
     * Executes the query and expects a single result.
     *
     * NOTE: Only use this function if there is a maximum of 1 result that could be returned.
     * @param query the query to be executed.
     * @return the found result or null if no results are found.
     */
    public static Object getSingleOrDefault(javax.persistence.Query query) {
        List result = query.getResultList();

        return result.size() != 0
                ? result.get(0)
                : null;
    }

    /**
     * Executes the query and expects a single result.
     *
     * NOTE: Only use this function if there is a maximum of 1 result that could be returned.
     * @param query the query to be executed.
     * @return the found result or null if no results are found.
     */
    public static <T> T getSingleOrDefault(javax.persistence.TypedQuery<T> query) {
        List<T> result = query.getResultList();

        return result.size() != 0
                ? result.get(0)
                : null;
    }
}
