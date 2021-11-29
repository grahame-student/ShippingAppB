package BusinessLogicLayer;

import BusinessLogicLayer.RestfulObjects.Berth;
import BusinessLogicLayer.RestfulObjects.StevedoreDto;
import BusinessLogicLayer.RestfulObjects.StevedoreServicesOrdered;

import java.time.LocalDate;

public class StevedoreService implements IStevedoreService
{
    /**
     * The day the ship is due to arrive
     */
    private LocalDate dayOfArrival;

    /**
     * The services that have been ordered
     */
    private StevedoreServicesOrdered servicesOrdered;

    /**
     * The berth the ship is porting at.
     */
    private Berth berth;

    /**
     * object containing all the URLs used for restful comms.
     */
    private UrlConfig urlConfig;

    /**
     * Initializes a new instance of the StevedoreService class.
     * @param dayOfArrival the day the ship is expected to port.
     * @param servicesOrdered the stevedore services that have been ordered.
     * @param berth the berth the ship is porting to.
     */
    public StevedoreService(LocalDate dayOfArrival, StevedoreServicesOrdered servicesOrdered, Berth berth, UrlConfig urls)
    {
        this.dayOfArrival = dayOfArrival;
        this.servicesOrdered = servicesOrdered;
        this.berth = berth;
        this.urlConfig = urls;
    }

    @Override
    public String orderStevedore()
    {
        var dto = new StevedoreDto(this.dayOfArrival.toString(), this.servicesOrdered, this.berth);


        return null;
    }
}
