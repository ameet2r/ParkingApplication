import java.math.BigDecimal;

/**
 * Represents an item in the ParkingSpace table.
 *
 * @author Karanbir Toor & Ameet Toor
 */
public class ParkingSpace {
    private int myParkingSpaceNumber;
    private BigDecimal myMonthlyRate;
    private String myLotName;
    private boolean myTaken;

    /**
     * Constructs a ParkingSpace representing a parking space in the ParkingSpace table.
     *
     * @param theParkingSpaceNumber the parking space id.
     * @param theMonthlyRate        the monthly rate of charge for a staff member.
     * @param theLotName            the name of the lot this space is in.
     * @param theTaken              true if this lot is taken.
     */
    public ParkingSpace(int theParkingSpaceNumber, BigDecimal theMonthlyRate, String theLotName,
                        boolean theTaken) {
        this.myParkingSpaceNumber = theParkingSpaceNumber;
        this.myMonthlyRate = theMonthlyRate;
        this.myLotName = theLotName;
        this.myTaken = theTaken;
    }

    /**
     * True if this lot is taken. False otherwise.
     *
     * @return boolean representing if the space is taken.
     */
    public boolean getMyTaken() {
        return myTaken;
    }

    /**
     * Sets the spot to taken or not taken.
     *
     * @param myTaken True if taken.
     */
    public void setMyTaken(boolean myTaken) {
        this.myTaken = myTaken;
    }

    /**
     * Gets the parking space number.
     *
     * @return integer representing parking space number.
     */
    public int getMyParkingSpaceNumber() {
        return myParkingSpaceNumber;
    }

    /**
     * Sets the parking space id.
     *
     * @param myParkingSpaceNumber integer id of space.
     */
    public void setMyParkingSpaceNumber(int myParkingSpaceNumber) {
        this.myParkingSpaceNumber = myParkingSpaceNumber;
    }

    /**
     * Gets the monthly rate of space rental.
     *
     * @return the cost of this spot as Big Decimal.
     */
    public BigDecimal getMyMonthlyRate() {
        return myMonthlyRate;
    }

    /**
     * Sets the monthly rate of this spot.
     *
     * @param myMonthlyRate Big Decimal representing the cost of this spot.
     */
    public void setMyMonthlyRate(BigDecimal myMonthlyRate) {
        this.myMonthlyRate = myMonthlyRate;
    }

    /**
     * Gets the lot this parking space is in.
     *
     * @return The String parking lot.
     */
    public String getMyLotName() {
        return myLotName;
    }

    /**
     * Sets the parking lot this space is in.
     *
     * @param myLotName The name of the parking lot.
     */
    public void setMyLotName(String myLotName) {
        this.myLotName = myLotName;
    }
}
