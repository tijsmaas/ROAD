package road.billsystem.beans;

import road.billsystem.dts.BillService;
import road.movemententities.entities.City;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mitch
 */
@Named
@SessionScoped
public class PriceBean implements Serializable
{
    private City city;
    private String price;
    private  List<City> cities;
    @Inject
    private BillService billService;

    public List<City> getCities() {
        return cities;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @PostConstruct
    public void init()
    {
        cities = billService.getCities();
    }

    public String adjustRate()
    {
        if(price.matches("\\d+[,.]\\d{2}"))
        {
            billService.adjustKilometerRate(city, new Date(), price);
            return "The kilometer rates has been changed successfully.";
        }
        else
        {
            return "Failed to adjust the kilometer rate. Enter a valid price.";
        }
    }
}