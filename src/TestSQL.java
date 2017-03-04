import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Created by Karan on 3/3/17.
 */
public class TestSQL {
    public static void main(String[] args) {
        ParkingAppDB parkingAppDB = new ParkingAppDB();
        try {
            parkingAppDB.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        parkingAppDB.addStaffMember(new StaffMember(1234, "tester", "123122312", "asdfasdf"));
        try {
            parkingAppDB.getStaffMembers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        parkingAppDB.updateStaff(0, "vehicleLicenseNumber", "12311111111");
//        parkingAppDB.addStaffSpace(123, 4);
//        parkingAppDB.updateParkingSpace(4, "taken", true);
//        parkingAppDB.addBooking();
    }
}
