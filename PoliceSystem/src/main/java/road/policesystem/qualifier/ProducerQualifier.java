package road.policesystem.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by Niek on 10/04/14.
 * © Aidas 2014
 *
 * Simple road.policesystem.qualifier for the DAO producers
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface ProducerQualifier {}
