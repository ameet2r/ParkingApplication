import java.sql.Date;

/**
 * Represents an item in the SpaceBooking table.
 *
 * @author Karanbir Toor & Ameet Toor
 */
public class SpaceBooking {
    private int myBookingId;
    private int myParkingSpaceNumber;
    private int myStaffNumber;
    private String myLicenseNumber;
    private Date myDateOfVisit;

    /**
     * Constructs a Booking entry representing an item in the SpaceBooking table.
     *
     * @param theBookingId          the unique booking id.
     * @param theParkingSpaceNumber the unique parking space number.
     * @param theStaffNumber        the unique staff number.
     * @param theLicenseNumber      the license number of the visitor.
     * @param theDateOfVisit        the date the visitor is visiting. YYYY-MM-DD.
     */
    public SpaceBooking(int theBookingId, int theParkingSpaceNumber, int theStaffNumber,
                        String theLicenseNumber, Date theDateOfVisit) {
        this.myBookingId = theBookingId;
        this.myParkingSpaceNumber = theParkingSpaceNumber;
        this.myStaffNumber = theStaffNumber;
        this.myLicenseNumber = theLicenseNumber;
        this.myDateOfVisit = theDateOfVisit;
    }

    /**
     * Get the parking space number.
     *
     * @return integer representing parking space.
     */
    public int getMyParkingSpaceNumber() {
        return myParkingSpaceNumber;
    }

    /**
     * Set the parking space number.
     *
     * @param myParkingSpaceNumber integer id of parking space.
     */
    public void setMyParkingSpaceNumber(int myParkingSpaceNumber) {
        this.myParkingSpaceNumber = myParkingSpaceNumber;
    }

    /**
     * Get the unique staff number.
     *
     * @return integer representing staff number.
     */
    public int getMyStaffNumber() {
        return myStaffNumber;
    }

    /**
     * Set the staff number for this reservation.
     *
     * @param myStaffNumber integer representing staff number.
     */
    public void setMyStaffNumber(int myStaffNumber) {
        this.myStaffNumber = myStaffNumber;
    }

    /**
     * Gets the license number of the visitor.
     *
     * @return String representing license.
     */
    public String getMyLicenseNumber() {
        return myLicenseNumber;
    }

    /**
     * Set the license number of the visitor.
     *
     * @param myLicenseNumber String representing license.
     */
    public void setMyLicenseNumber(String myLicenseNumber) {
        this.myLicenseNumber = myLicenseNumber;
    }

    /**
     * Gets the date of the reservation.
     *
     * @return the date as YYYY-MM-DD.
     */
    public Date getMyDateOfVisit() {
        return myDateOfVisit;
    }

    /**
     * Sets the date of the visit.
     *
     * @param myDateOfVisit date as YYYY-MM-DD.
     */
    public void setMyDateOfVisit(Date myDateOfVisit) {
        this.myDateOfVisit = myDateOfVisit;
    }

    /**
     * Gets the unique booking id.
     *
     * @return integer booking id.
     */
    public int getMyBookingId() {
        return myBookingId;
    }

    /**
     * Sets the booking id.
     *
     * @param myBookingId integer booking id.
     */
    public void setMyBookingId(int myBookingId) {
        this.myBookingId = myBookingId;
    }
}
