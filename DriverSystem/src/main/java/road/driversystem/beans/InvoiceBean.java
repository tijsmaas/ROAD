package road.driversystem.beans;

import road.driversystem.service.DriverService;
import road.movementdtos.dtos.InvoiceDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Bean containing all the functionalities for viewing a user's invoices.
 *
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
@Named
@RequestScoped
public class InvoiceBean
{
    @Inject
    private DriverService service;

    @Inject
    private UserBean userSession;

    private List<InvoiceDto> userInvoices;

    @PostConstruct
    public void init()
    {
        this.userInvoices = service.getUserInvoices(userSession.getLoggedinUser().getId());
    }

    public List<InvoiceDto> getUserInvoices()
    {
        return this.userInvoices;
    }

    public int getInvoiceLength(){
        return this.userInvoices.size();
    }
}
