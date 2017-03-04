import java.util.List;

/**
 * Created by Ameet2r on 3/3/17.
 */
public class StaffMember {

    private int myStaffNumber;
    private String myName;
    private String myTelephoneExtNumber;
    private String myVehicleLicenseNumber;
    private List<SpaceBooking> myParkingSpaces;

    public StaffMember(int theStaffNumber, String theName,
                       String theTelephoneExtNumber, String theVehicleLicenseNumber) {
        this.myStaffNumber = theStaffNumber;
        this.myName = theName;
        this.myTelephoneExtNumber = theTelephoneExtNumber;
        this.myVehicleLicenseNumber = theVehicleLicenseNumber;
    }

    public int getMyStaffNumber() {
        return myStaffNumber;
    }

    public void setMyStaffNumber(int myStaffNumber) {
        this.myStaffNumber = myStaffNumber;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyTelephoneExtNumber() {
        return myTelephoneExtNumber;
    }

    public void setMyTelephoneExtNumber(String myTelephoneExtNumber) {
        this.myTelephoneExtNumber = myTelephoneExtNumber;
    }

    public String getMyVehicleLicenseNumber() {
        return myVehicleLicenseNumber;
    }

    public void setMyVehicleLicenseNumber(String myVehicleLicenseNumber) {
        this.myVehicleLicenseNumber = myVehicleLicenseNumber;
    }

    public List<SpaceBooking> getMyParkingSpaces() {
        return myParkingSpaces;
    }

    public void setMyParkingSpaces(List<SpaceBooking> myParkingSpaces) {
        this.myParkingSpaces = myParkingSpaces;
    }
}
