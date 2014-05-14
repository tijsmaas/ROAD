package domain.invoice;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Timer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;
import road.movemententityaccess.dao.EntityDAO;

/**
 * Service to generate invoices every month
 * @author tijs
 */
@ApplicationScoped
public class MonthlyInvoiceService {
    @Inject
    private EntityDAO entityDAO;

    /* REMOVE THIS IS FOR TESTING PURPOSES ONLY */
    @PostConstruct
    public void init() {
        String licenseplate = "t0";
        int userID = 1;
        
        Vehicle car = (Vehicle) entityDAO.findById(Vehicle.class, licenseplate);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.YEAR, 3);
        VehicleOwnership owner = new VehicleOwnership(car, userID, Calendar.getInstance(), expirationDate);
        entityDAO.create(owner);
        car.addVehicleOwner(owner);
        entityDAO.edit(car);
        System.out.println("Test user 1 has car with license plate t0");
    }
    /* END REMOVE */
    
    
    /**
     * Generate monthly invoice per car.
     * This data can later be requested per user using car ownership.
     */
    @Schedule(second="*/3", minute="*", hour="*", info="Generate monthly invoices")
//    @Schedule(month="*", info="Generate monthly invoices")
    public void generateMonthlyInvoice(Timer t) {
        System.out.println("Monthly invoice generator started:" + t.getInfo());
        // TODO invoice query
        
    }
}
