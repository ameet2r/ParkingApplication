import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ameet2r on 3/3/17.
 */
public class ParkingLot {
    private String myLotName;
    private String myLocation;
    private int myCapacity;
    private int myNumberOfFloors;

    public ParkingLot(String myLotName, String myLocation, int myCapacity, int myNumberOfFloors) {
        this.myLotName = myLotName;
        this.myLocation = myLocation;
        this.myCapacity = myCapacity;
        this.myNumberOfFloors = myNumberOfFloors;
    }

    public String getMyLotName() {
        return myLotName;
    }

    public void setMyLotName(String myLotName) {
        this.myLotName = myLotName;
    }

    public String getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }

    public int getMyCapacity() {
        return myCapacity;
    }

    public void setMyCapacity(int myCapacity) {
        this.myCapacity = myCapacity;
    }

    public int getMyNumberOfFloors() {
        return myNumberOfFloors;
    }

    public void setMyNumberOfFloors(int myNumberOfFloors) {
        this.myNumberOfFloors = myNumberOfFloors;
    }

    private ArrayList<ParkingSpace> myParkingSpaces;




}
