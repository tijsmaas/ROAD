package road.movementservice.mapper;

import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleInvoiceDto;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleInvoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class DtoMapper
{

    /**
     * Map an Invoice object to a simple invoiceDTO, not containing any of the child relations
     * @param invoice the invoice to map
     * @return simple InvoiceDTO
     */
    public InvoiceDto mapSimple(Invoice invoice)
    {
        if(invoice == null){
            return null;
        }
        BigDecimal total = new BigDecimal("0");
        for (VehicleInvoice vehicleInvoice :invoice.getVehicleInvoices())
        {
            total = total.add(vehicleInvoice.getSubTotal());
        }

        InvoiceDto invoiceDto = new InvoiceDto(invoice.getInvoiceID(), invoice.getUserID(), invoice.getGenerationDate(), invoice.getStartDate(), invoice.getEndDate(), invoice.getPaymentStatus().ordinal(), total);
        return invoiceDto;
    }

    /**
     * Map a VehicleInvoice to a simple version of the VehicleInvoiceDTO, not containing the list of cityMovements
     * @param vehicleInvoice the vehicleInvoice to map
     * @return The VehicleInvoiceDto object
     */
    public VehicleInvoiceDto mapSimple(VehicleInvoice vehicleInvoice)
    {
        return new VehicleInvoiceDto(vehicleInvoice.getId(), this.map(vehicleInvoice.getOwnership().getVehicle()), vehicleInvoice.getSubTotal());
    }

    /**
     * Maps a Vehicle to a VehicleDTO
     * @param vehicle The Vehicle object to map
     * @return The Vehicle Dto object
     */
    public VehicleDto map(Vehicle vehicle)
    {
        //TODO: Contribute GPS data in vehicle object
        return new VehicleDto(vehicle.getLicensePlate(), true);
    }

    /**
     * Map an Invoice to a Dto, containing all the invoice details.
     * @param invoice The invoice to map
     * @return The Invoice Dto containing the children (like vehicleMovements)
     */
    public InvoiceDto map(Invoice invoice)
    {
        if(invoice == null){
            return null;
        }

        InvoiceDto invoiceDto = this.mapSimple(invoice);

        List<VehicleInvoiceDto> vehicleInvoiceList = new ArrayList<>();
        for (VehicleInvoice vehicleInvoice : invoice.getVehicleInvoices())
        {
            vehicleInvoiceList.add(this.mapSimple(vehicleInvoice));
        }

        invoiceDto.setVehicleInvoices(vehicleInvoiceList);

        return invoiceDto;
    }

}
