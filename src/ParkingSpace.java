import java.math.BigDecimal;

/**
 * Created by Ameet2r on 3/3/17.
 */
public class ParkingSpace {

    //have to be able to create new parking spaces, assign a parking space to staff or visitor, and know if parking space
    //if uncovered or covered.
    private int myParkingSpaceNumber;
    private BigDecimal myMonthlyRate;
    private String myLotName;

    public ParkingSpace(int myParkingSpaceNumber, BigDecimal myMonthlyRate, String myLotName) {
        this.myParkingSpaceNumber = myParkingSpaceNumber;
        this.myMonthlyRate = myMonthlyRate;
        this.myLotName = myLotName;
    }

    public int getMyParkingSpaceNumber() {
        return myParkingSpaceNumber;
    }

    public void setMyParkingSpaceNumber(int myParkingSpaceNumber) {
        this.myParkingSpaceNumber = myParkingSpaceNumber;
    }

    public BigDecimal getMyMonthlyRate() {
        return myMonthlyRate;
    }

    public void setMyMonthlyRate(BigDecimal myMonthlyRate) {
        this.myMonthlyRate = myMonthlyRate;
    }

    public String getMyLotName() {
        return myLotName;
    }

    public void setMyLotName(String myLotName) {
        this.myLotName = myLotName;
    }
}
