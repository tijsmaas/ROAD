package road.movemententities.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class VehicleInvoice implements MovementEntity<Integer>
{
    @Id @GeneratedValue
    private int id;

    @ManyToOne
    private VehicleOwnership ownership;

    @ManyToOne
    private Invoice invoice;

    private BigDecimal subTotal;

    @OneToMany
    private List<CityDistance> movementList;

    @Override
    public Integer getId()
    {
        return this.id;
    }

    public VehicleOwnership getOwnership()
    {
        return ownership;
    }

    public void setOwnership(VehicleOwnership ownership)
    {
        this.ownership = ownership;
    }

    public Invoice getInvoice()
    {
        return invoice;
    }

    public void setInvoice(Invoice invoice)
    {
        this.invoice = invoice;
    }

    public BigDecimal getSubTotal()
    {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal)
    {
        this.subTotal = subTotal;
    }

    public List<CityDistance> getMovementList()
    {
        return movementList;
    }

    public void setMovementList(List<CityDistance> movementList)
    {
        this.movementList = movementList;
    }
}
