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
    private ArrayList<ParkingSpace> myParkingSpaces;

    //have to be able to create new parking lots, when new parking lot created, automatically add some ParkingSpaces


    public void ParkingLot(String theLotName, String theLocation, int theCapacity, int theNumberOfFloors)
    {
        myLotName = theLotName;
        myLocation = theLocation;
        myCapacity = theCapacity;
        myNumberOfFloors = theNumberOfFloors;
        myParkingSpaces = new ArrayList<ParkingSpace>();
    }

    public String getLotName()
    {
        return myLotName;
    }

    public  String getLocation()
    {
        return myLocation;
    }

    public int getCapacity()
    {
        return myCapacity;
    }

    public int getNumberOfFloors()
    {
        return  myNumberOfFloors;
    }

    public boolean addParkingSpace(ParkingSpace theParkingSpace)
    {
        boolean couldAddParkingSpace = false;


        myParkingSpaces.add(theParkingSpace);
        return couldAddParkingSpace;
    }

    public ArrayList<ParkingSpace> getParkingSpaces()
    {
        return myParkingSpaces;
    }

//    private int getNextParkingNumber()
//    {
//        boolean couldGetNextParkingNumber = false;
//
//        //get next number, check if less than capacity, if it is, return true
//        return couldGetNextParkingNumber;
//    }
//


}
