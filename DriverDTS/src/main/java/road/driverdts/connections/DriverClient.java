package road.driverdts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.userservice.dto.UserDto;
import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.movementdts.connections.ClientConnection;
import road.movementdts.connections.MovementConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geh on 22-4-14.
 */
public class DriverClient extends ClientConnection implements IDriverQuery
{
    public DriverClient()
    {
        super(MovementConnection.ServerAddress, MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @Override
    public MovementUserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", MovementUserDto.class, userId, password);
    }

    @Override
    public Long getLaneCount()
    {
        return this.remoteCall("getLaneCount", Long.class);
    }

    @Override
    public Long getEdgeCount()
    {
        return this.remoteCall("getEdgeCount", Long.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleDto> getVehicles(Integer userId) {
        return this.remoteCall("getVehicles", ArrayList.class, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateVehicle(String licencePlate, Boolean contributeGPSData) {
        return this.remoteCall("updateVehicle", Boolean.class, licencePlate, contributeGPSData);
    }

    @Override
    public List<InvoiceDto> getUserInvoices(Integer userID)
    {
        return this.remoteCall("getUserInvoices", ArrayList.class, userID);
    }

    @Override
    public Boolean updateInvoicePaymentStatus(Integer invoiceID, PaymentStatus paymentStatus)
    {
        return this.remoteCall("updateInvoicePaymentStatus", Boolean.class, invoiceID, paymentStatus);
    }

    @Override
    public InvoiceDto getInvoiceDetails(Integer invoiceID)
    {
        return this.remoteCall("getInvoiceDetails", InvoiceDto.class, invoiceID);
    }

    @Override
    public List<CityDistanceDto> getCityDistances(Integer vehicleInvoiceID)
    {
        if(vehicleInvoiceID == null){
            throw new IllegalArgumentException("vehicleInvoiceID was null");
        }
        return this.remoteCall("getCityDistances", ArrayList.class, vehicleInvoiceID);
    }

    @Override
    public String changePassword(Integer id, String oldPassword, String newPassword, String newPasswordValidate) {
        if (id == null) {
            throw new IllegalArgumentException("DriverClient.changePassword: id cannot be null.");
        }

        return this.remoteCall("changePassword", String.class, id, oldPassword, newPassword, newPasswordValidate);
    }

    @Override
    public MovementUserDto changeDetails(Integer id, String name, String street, String houseNumber, String postalCode, String city, Boolean invoiceNotification)
    {
        if (id == null)
        {
            throw new IllegalArgumentException("DriverClient.changePassword: id cannot be null.");
        }

        return this.remoteCall("changeDetails", MovementUserDto.class, id, name, street, houseNumber, postalCode, city, invoiceNotification);
    }
}
