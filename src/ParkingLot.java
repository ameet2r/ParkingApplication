import java.util.ArrayList;

/**
 * Represents an item in the Parking Lot table.
 *
 * @author Karanbir Toor & Ameet Toor
 */
public class ParkingLot {
    private String myLotName;
    private String myLocation;
    private int myCapacity;
    private int myNumberOfFloors;

    /**
     * Create a ParkingLot with the given data.
     *
     * @param theLotName        the parking lot name.
     * @param theLocation       the location of the lot.
     * @param theCapacity       the capacity of the lot.
     * @param theNumberOfFloors the number of floors the lot has.
     */
    public ParkingLot(String theLotName, String theLocation, int theCapacity, int theNumberOfFloors) {
        this.myLotName = theLotName;
        this.myLocation = theLocation;
        this.myCapacity = theCapacity;
        this.myNumberOfFloors = theNumberOfFloors;
    }

    /**
     * Get the lot name.
     *
     * @return String representation of the lot name.
     */
    public String getMyLotName() {
        return myLotName;
    }

    /**
     * Set the lot name.
     *
     * @param myLotName String lot name.
     */
    public void setMyLotName(String myLotName) {
        this.myLotName = myLotName;
    }

    /**
     * Get the location of the lot.
     *
     * @return String represntation of location.
     */
    public String getMyLocation() {
        return myLocation;
    }

    /**
     * Set the location of this lot.
     *
     * @param myLocation String representation of location.
     */
    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }

    /**
     * Get the lot's capacity.
     *
     * @return The integer capacity.
     */
    public int getMyCapacity() {
        return myCapacity;
    }

    /**
     * Set the lot's capacity.
     *
     * @param myCapacity the integer representing lot capacity.
     */
    public void setMyCapacity(int myCapacity) {
        this.myCapacity = myCapacity;
    }


    /**
     * Get the number of floors of this lot.
     *
     * @return Integer representing floors.
     */
    public int getMyNumberOfFloors() {
        return myNumberOfFloors;
    }

    /**
     * Sets the number of floors of this lot.
     *
     * @param myNumberOfFloors Integer number of floors.
     */
    public void setMyNumberOfFloors(int myNumberOfFloors) {
        this.myNumberOfFloors = myNumberOfFloors;
    }

    /**
     * Saves the parking spaces in this lot.
     */
    private ArrayList<ParkingSpace> myParkingSpaces;


}
