import java.util.List;

/**
 * Represents an item in the Staff table.
 *
 * @author Karanbir Toor & Ameet Toor
 */
public class StaffMember {

    private int myStaffNumber;
    private String myName;
    private String myTelephoneExtNumber;
    private String myVehicleLicenseNumber;
    private List<SpaceBooking> myParkingSpaces;

    /**
     * Constructs a StaffMember object representing an item in the StaffMember table.
     *
     * @param theStaffNumber          the unique staff number.
     * @param theName                 the name of the staff member.
     * @param theTelephoneExtNumber   the telephone extention of the staff member.
     * @param theVehicleLicenseNumber the vehicle license number of the staff member's car.
     */
    public StaffMember(int theStaffNumber, String theName,
                       String theTelephoneExtNumber, String theVehicleLicenseNumber) {
        this.myStaffNumber = theStaffNumber;
        this.myName = theName;
        this.myTelephoneExtNumber = theTelephoneExtNumber;
        this.myVehicleLicenseNumber = theVehicleLicenseNumber;
    }

    /**
     * Gets the unique staff number.
     *
     * @return the integer staff number.
     */
    public int getMyStaffNumber() {
        return myStaffNumber;
    }

    /**
     * Sets the staff number.
     *
     * @param myStaffNumber the integer staff number.
     */
    public void setMyStaffNumber(int myStaffNumber) {
        this.myStaffNumber = myStaffNumber;
    }

    /**
     * Get the name of this staff member.
     *
     * @return The String name.
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Sets the name of this staff member.
     *
     * @param myName The String name.
     */
    public void setMyName(String myName) {
        this.myName = myName;
    }

    /**
     * Gets the telephone extention of this staff member.
     *
     * @return String representing telephone to preserve leading 0's.
     */
    public String getMyTelephoneExtNumber() {
        return myTelephoneExtNumber;
    }

    /**
     * Sets the telephone number for this staff member.
     *
     * @param myTelephoneExtNumber String representing telephone to preserve leading 0's.
     */
    public void setMyTelephoneExtNumber(String myTelephoneExtNumber) {
        this.myTelephoneExtNumber = myTelephoneExtNumber;
    }

    /**
     * Gets the staff member's vehicle license number.
     *
     * @return String license number.
     */
    public String getMyVehicleLicenseNumber() {
        return myVehicleLicenseNumber;
    }

    /**
     * Sets the staff memmber's vehicle license number.
     *
     * @param myVehicleLicenseNumber String license number.
     */
    public void setMyVehicleLicenseNumber(String myVehicleLicenseNumber) {
        this.myVehicleLicenseNumber = myVehicleLicenseNumber;
    }

    /**
     * Gets a list of booked spaces.
     *
     * @return The list of parking spaces booked.
     */
    public List<SpaceBooking> getMyParkingSpaces() {
        return myParkingSpaces;
    }

    /**
     * Sets the list of spaces booked for this booking.
     *
     * @param myParkingSpaces The list of parking spaces booked.
     */
    public void setMyParkingSpaces(List<SpaceBooking> myParkingSpaces) {
        this.myParkingSpaces = myParkingSpaces;
    }
}
