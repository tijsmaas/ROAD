package road.movementparser.parser;

import com.thoughtworks.xstream.XStream;
import road.movementdtos.sumo.FcdExport;
import road.movementdtos.sumo.FcdTimeStep;
import road.movementdtos.sumo.FcdVehicle;
import road.movemententities.entities.Lane;
import road.movemententities.entities.Movement;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleMovement;
import road.movementparser.daos.ParserDAOImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by geh on 25-5-14.
 */
public class FcdParser
{
    private ParserDAOImpl parserDAO;
    private XStream xstream;
    private int numberParsed;
    private int missed;

    public FcdParser(ParserDAOImpl parserDAO)
    {
        this.parserDAO = parserDAO;
        this.numberParsed = 0;
        this.missed = 0;

        this.xstream = new XStream();
        this.xstream.useAttributeFor(FcdTimeStep.class);
        this.xstream.useAttributeFor(FcdVehicle.class);
    }

    public List<Movement> parse(String xml, int sequence)
    {
        if (numberParsed != sequence)
        {
            System.out.println("Tried to add Movements with serial number " + sequence + ", but counter was at " + numberParsed);
            this.missed++;
        }

        this.numberParsed++;

        FcdExport export = (FcdExport)this.xstream.fromXML(xml);

        List<Movement> movements = new ArrayList<Movement>();
        for(FcdTimeStep timeStep : export.getTimeSteps())
        {
            movements.add(this.parseTimeStep(timeStep, new GregorianCalendar()));
        }

        return movements;
    }

    private Movement parseTimeStep(FcdTimeStep timeStep, Calendar cal)
    {
        Lane lane = this.parserDAO.getLane(timeStep.getVehicles().get(0).getLaneId());
        Movement movement = new Movement(cal, (float)timeStep.getTime(), lane);

        List<VehicleMovement> vehicleMovements = new ArrayList();

        for(FcdVehicle fcdVehicle : timeStep.getVehicles())
        {
            Vehicle vehicle = this.parserDAO.getVehicle(fcdVehicle.getId());
            if (vehicle == null)
            {
                vehicle = new Vehicle(fcdVehicle.getId());
                this.parserDAO.addVehicle(vehicle);

                VehicleMovement vehicleMovement = new VehicleMovement(movement, vehicle, (float)fcdVehicle.getPos(),
                                                                      (float)fcdVehicle.getSpeed(), fcdVehicle.getLatitude(),
                                                                      fcdVehicle.getLongitude());
                vehicle.addVehicleMovement(vehicleMovement);
                vehicleMovements.add(vehicleMovement);
            }
        }

        movement.setVehicleMovements(vehicleMovements);
        return movement;
    }
}
