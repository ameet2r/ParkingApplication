import java.sql.SQLException;

/**
 * Created by Karan on 3/3/17.
 */
public class TestSQL {
    public static void main(String[] args) {
        ParkingAppDB parkingAppDB = new ParkingAppDB();
        try {
            System.out.println(parkingAppDB.getMaxParkingNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
