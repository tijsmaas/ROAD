/*
 * Copyright by AIDaS.
 */

package road.movemententities.converters;

import road.movementdtos.dtos.CityDto;
import road.movementdtos.dtos.VehicleDto;
import road.movemententities.entities.City;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;

import java.util.List;

/**
 * This class represents the converter which can be used to convert the {@link road.movemententities.entities.Vehicle} class.
 *
 * @author Geert
 */
public class CityConverter {

    /**
     *
     * Convert the {@link road.movemententities.entities.City} to a {@link road.movementdtos.dtos.CityDto} class.
     * @param city the city entity to convert.
     * @return the converted city entity.
     */
    public static CityDto toCityDto(City city) {
        return new CityDto(city.getCityId(), city.getCityName());
    }
}
