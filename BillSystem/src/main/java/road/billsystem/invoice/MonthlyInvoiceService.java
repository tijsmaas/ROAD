package road.billsystem.invoice;

import javax.ejb.Schedule;
import javax.ejb.Timer;
import javax.enterprise.context.ApplicationScoped;

/**
 * Service to generate invoices every month
 * @author tijs
 */
@ApplicationScoped
public class MonthlyInvoiceService {
    
    /**
     * Generate monthly invoice per car.
     * This data can later be requested per user using car ownership.
     */
    @Schedule(second="*/3", minute="*", hour="*", info="Generate monthly invoices")
    public void generateMonthlyInvoice(Timer t) {
        System.out.println("Monthly invoice generator started:" + t.getInfo());
        // TODO invoice query
        
    }
}
