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

    public SpaceBooking(int myBookingId, int myParkingSpaceNumber, int myStaffNumber,
                        String myLicenseNumber, Date myDateOfVisit) {
        this.myBookingId = myBookingId;
        this.myParkingSpaceNumber = myParkingSpaceNumber;
        this.myStaffNumber = myStaffNumber;
        this.myLicenseNumber = myLicenseNumber;
        this.myDateOfVisit = myDateOfVisit;
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
