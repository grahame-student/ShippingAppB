package BusinessLogicLayer;

import BusinessLogicLayer.RestfulObjects.Berth;
import BusinessLogicLayer.RestfulObjects.Ship;
import ServiceRequestor.ServiceCaller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

/**
 * the class that initiates the port service requests.
 */
public class PortService implements IPortService
{
    /**
     * The length of the ship
     */
    private final double shipLength;

    /**
     * The width of the ship
     */
    private final double shipWidth;

    /**
     * The day the shipment is due
     */
    private final LocalDate dayOfBooking;

    /**
     * the vertical distance between the waterline and the bottom of the hull
     */
    private final double shipDraft;

    /**
     * the object that contains url paths.
     */
    private UrlConfig urlConfig;

    /**
     * Initializes a new instance of the PortService class.
     * @param shipDraft the vertical distance between the waterline and the bottom of the hull
     * @param shipLength the length of the ship
     * @param shipWidth the width of the ship
     * @param dayOfBooking the day the shipment is due
     */
    public PortService(double shipDraft, double shipLength, double shipWidth, LocalDate dayOfBooking, UrlConfig urlConfig)
    {
        this.shipDraft = shipDraft;
        this.shipLength = shipLength;
        this.shipWidth = shipWidth;
        this.dayOfBooking = dayOfBooking;
        this.urlConfig = urlConfig;
    }

    /**
     * Gets the availability of the port.
     * @return the string representation of the availability of the port.
     * @throws IllegalArgumentException when the params cannot be converted to strings
     * @throws IOException occurs if the connection is not
     */
    public String getBerths() throws IllegalArgumentException, IOException
    {
        var ship = new Ship(this.shipDraft, this.shipLength, this.shipWidth, this.dayOfBooking);
        var params = JsonParser.parseShipToJson(ship);

        var url = new URL(this.urlConfig.getRequestPortUrl());
        var serviceCaller = new ServiceCaller(url);

        var availability = serviceCaller.getRequest(params);

        return availability;
    }

    /**
     * orders the berth
     * @param berthId the id of the berth to use
     * @param dateOfArrival the date the ship is due.
     * @return the string representation of the receipt.
     * @throws IOException id the connection doesn't work.
     */
    public String getPortServices(int berthId, LocalDate dateOfArrival) throws IOException
    {
        var berth = new Berth(berthId, String.valueOf(dateOfArrival));
        var params = JsonParser.parseBerthToJson(berth);
        var url = new URL(this.urlConfig.getOrderPortUrl());
        var serviceCaller = new ServiceCaller(url);
        var receipt = serviceCaller.postRequest(params);

        return receipt;
    }
}
