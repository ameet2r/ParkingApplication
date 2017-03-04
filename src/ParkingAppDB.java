import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParkingAppDB {
    private static String userName = "toork"; //Change to yours
    private static String password = "Ammed9";
    private static String serverName = "cssgate.insttech.washington.edu";
    private static Connection conn;
    private List<StaffMember> staffList;
    private List<StaffSpace> staffSpaceList;
    private List<ParkingLot> lotList;
    private List<ParkingSpace> spaceList;
    private List<SpaceBooking> bookingList;

    /**
     * Creates a sql connection to MySQL using the properties for
     * userid, password and server information.
     * @throws SQLException
     */
    public static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
                + serverName + "/", connectionProps);

        System.out.println("Connected to database");
    }

    public List<StaffMember> getStaffMembers() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select staffNumber, staffName, telephoneExt, vehicleLicenseNumber "
                + "from toork.Staff ";

        staffList = new ArrayList<StaffMember>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int staffNumber = rs.getInt("staffNumber");
                String staffName = rs.getString("staffName");
                String telephoneExt = rs.getString("telephoneExt");
                String vehicleLicenseNumber = rs.getString("vehicleLicenseNumber");
                StaffMember staffMember = new StaffMember(staffNumber, staffName,
                        telephoneExt, vehicleLicenseNumber);
                staffList.add(staffMember);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return staffList;
    }

    public List<ParkingLot> getParkingLots() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select lotName, location, capacity, floors "
                + "from toork.ParkingLot ";

        lotList = new ArrayList<ParkingLot>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String lotName = rs.getString("lotName");
                String location = rs.getString("location");
                int capacity = rs.getInt("capacity");
                int floors = rs.getInt("floors");
                ParkingLot parkingLot = new ParkingLot(lotName, location,
                        capacity, floors);
                lotList.add(parkingLot);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return lotList;
    }

    public List<ParkingSpace> getParkingSpaces() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select spaceNumber, monthlyRate, lotName "
                + "from toork.ParkingSpace ";

        spaceList = new ArrayList<ParkingSpace>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int spaceNumber = rs.getInt("spaceNumber");
                BigDecimal monthlyRate = rs.getBigDecimal("monthlyRate");
                String lotName = rs.getString("lotName");
                ParkingSpace parkingSpace = new ParkingSpace(spaceNumber, monthlyRate,
                        lotName);
                spaceList.add(parkingSpace);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return spaceList;
    }

    public int getMaxParkingNumber() throws SQLException {
        if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select spaceNumber, monthlyRate, lotName "
                + "from toork.ParkingSpace ";

        spaceList = new ArrayList<ParkingSpace>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int spaceNumber = rs.getInt("spaceNumber");
                BigDecimal monthlyRate = rs.getBigDecimal("monthlyRate");
                String lotName = rs.getString("lotName");
                ParkingSpace parkingSpace = new ParkingSpace(spaceNumber, monthlyRate,
                        lotName);
                spaceList.add(parkingSpace);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return 0;
    }

    public void addParkingSpace(int theSpaces, String theLotName, int maxSpace) {
        String sql = "insert into toork.ParkingSpace values " + "(?, ?, ?, null); ";

        for(int i = maxSpace + 1; i <= theSpaces + maxSpace; i++) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, i);
                preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
                preparedStatement.setString(3, theLotName);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public void addParkingLot(ParkingLot theParkingLot) {
        String sql = "insert into toork.ParkingLot values " + "(?, ?, ?, ?, null); ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, theParkingLot.getMyLotName());
            preparedStatement.setString(2, theParkingLot.getMyLocation());
            preparedStatement.setInt(3, theParkingLot.getMyCapacity());
            preparedStatement.setInt(4, theParkingLot.getMyNumberOfFloors());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public StaffMember getStaff(int staffNumber) {
        StaffMember toReturn = null;
        try {
            staffList = getStaffMembers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (StaffMember staffMember : staffList) {
            if (staffMember.getMyStaffNumber() == staffNumber) {
                toReturn = staffMember;
            }
        }
        return toReturn;
    }

    public void addStaffMember(StaffMember theStaffMember) {
        String sql = "insert into toork.Staff values " + "(?, ?, ?, ?, null); ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, theStaffMember.getMyStaffNumber());
            preparedStatement.setString(2, theStaffMember.getMyName());
            preparedStatement.setString(3, theStaffMember.getMyTelephoneExtNumber());
            preparedStatement.setString(4, theStaffMember.getMyVehicleLicenseNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateStaff(int row, String columnName, Object data) {

        StaffMember staffMember = staffList.get(row);
        int staffNumber = staffMember.getMyStaffNumber();
        String sql = "update toork.Staff set " + columnName + " = ?  where staffNumber = ? ";
        System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            if (data instanceof String)
                preparedStatement.setString(1, (String) data);
            preparedStatement.setInt(2, staffNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
