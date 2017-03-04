import java.sql.Date;

/**
 * Created by Ameet2r on 3/3/17.
 */
public class SpaceBooking {
    private int myBookingId;
    private int myParkingSpaceNumber;
    private int myStaffNumber;
    private String myLicenseNumber;
    private Date myDateOfVisit;

    public SpaceBooking(int theBookingId, int theParkingSpaceNumber, int theStaffNumber,
                        String theLicenseNumber, Date theDateOfVisit) {
        this.myBookingId = theBookingId;
        this.myParkingSpaceNumber = theParkingSpaceNumber;
        this.myStaffNumber = theStaffNumber;
        this.myLicenseNumber = theLicenseNumber;
        this.myDateOfVisit = theDateOfVisit;
    }

    public int getMyParkingSpaceNumber() {
        return myParkingSpaceNumber;
    }

    public void setMyParkingSpaceNumber(int myParkingSpaceNumber) {
        this.myParkingSpaceNumber = myParkingSpaceNumber;
    }

    public int getMyStaffNumber() {
        return myStaffNumber;
    }

    public void setMyStaffNumber(int myStaffNumber) {
        this.myStaffNumber = myStaffNumber;
    }

    public String getMyLicenseNumber() {
        return myLicenseNumber;
    }

    public void setMyLicenseNumber(String myLicenseNumber) {
        this.myLicenseNumber = myLicenseNumber;
    }

    public Date getMyDateOfVisit() {
        return myDateOfVisit;
    }

    public void setMyDateOfVisit(Date myDateOfVisit) {
        this.myDateOfVisit = myDateOfVisit;
    }

    public int getMyBookingId() {
        return myBookingId;
    }

    public void setMyBookingId(int myBookingId) {
        this.myBookingId = myBookingId;
    }
}
