/**
 * Created by Ameet2r on 3/3/17.
 */
public class ParkingSpace {

    //have to be able to create new parking spaces, assign a parking space to staff or visitor, and know if parking space
    //if uncovered or covered.
    private int myParkingSpaceNumber;
    private double myMonthlyRate;
    private String myLotName;

    public void ParkingSpace(double theMonthlyRate)
    {
        myMonthlyRate = theMonthlyRate;
        myLotName = "";
        myParkingSpaceNumber = 0;
    }


    public int getParkingSpaceNumber()
    {
        return myParkingSpaceNumber;
    }

    public double getMonthlyRate()
    {
        return myMonthlyRate;
    }

    public String getLotName()
    {
        return myLotName;
    }


    public void setLotName(String theLotName)
    {
        myLotName = theLotName;
    }

    public void setParkingSpaceNumber(int theParkingSpaceNumber)
    {
        myParkingSpaceNumber = theParkingSpaceNumber;
    }


}
