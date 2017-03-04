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
    private static String myUserName = "toork"; //Change to yours
    private static String myPassword = "Ammed9";
    private static String myServerName = "cssgate.insttech.washington.edu";
    private static Connection myConn;
    private List<StaffMember> myStaffList;
    private List<ParkingLot> myLotList;
    private List<ParkingSpace> mySpaceList;

    /**
     * Creates a sql connection to MySQL using the properties for
     * userid, password and server information.
     * @throws SQLException
     */
    public static void createConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", myUserName);
        connectionProps.put("password", myPassword);

        myConn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
                + myServerName + "/", connectionProps);

        System.out.println("Connected to database");
    }

    public List<StaffMember> getStaffMembers() throws SQLException {
        if (myConn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select staffNumber, staffName, telephoneExt, vehicleLicenseNumber "
                + "from toork.Staff ";

        myStaffList = new ArrayList<StaffMember>();
        try {
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int staffNumber = rs.getInt("staffNumber");
                String staffName = rs.getString("staffName");
                String telephoneExt = rs.getString("telephoneExt");
                String vehicleLicenseNumber = rs.getString("vehicleLicenseNumber");
                StaffMember staffMember = new StaffMember(staffNumber, staffName,
                        telephoneExt, vehicleLicenseNumber);
                myStaffList.add(staffMember);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return myStaffList;
    }

    public List<ParkingLot> getParkingLots() throws SQLException {
        if (myConn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select lotName, location, capacity, floors "
                + "from toork.ParkingLot ";

        myLotList = new ArrayList<ParkingLot>();
        try {
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String lotName = rs.getString("lotName");
                String location = rs.getString("location");
                int capacity = rs.getInt("capacity");
                int floors = rs.getInt("floors");
                ParkingLot parkingLot = new ParkingLot(lotName, location,
                        capacity, floors);
                myLotList.add(parkingLot);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return myLotList;
    }

    public List<ParkingSpace> getParkingSpaces() throws SQLException {
        if (myConn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select spaceNumber, monthlyRate, lotName, taken "
                + "from toork.ParkingSpace ";

        mySpaceList = new ArrayList<ParkingSpace>();
        try {
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int spaceNumber = rs.getInt("spaceNumber");
                BigDecimal monthlyRate = rs.getBigDecimal("monthlyRate");
                String lotName = rs.getString("lotName");
                boolean taken = rs.getBoolean("taken");
                ParkingSpace parkingSpace = new ParkingSpace(spaceNumber, monthlyRate,
                        lotName, taken);
                mySpaceList.add(parkingSpace);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return mySpaceList;
    }

    public int getMaxParkingSpaceNumber() throws SQLException {
        int num = -1;
        if (myConn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select max(spaceNumber) AS 'space' "
                + "from toork.ParkingSpace ";

        try {
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                num = rs.getInt("space");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return num;
    }

    public int getMaxBookingNumber() throws SQLException {
        int num = -1;
        if (myConn == null) {
            createConnection();
        }
        Statement stmt = null;
        String query = "select max(bookingId) AS 'space' "
                + "from toork.SpaceBooking ";

        try {
            stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                num = rs.getInt("space");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return num;
    }

    public void addParkingSpace(int theSpaces, String theLotName, int theMaxSpace) {
        String sql = "insert into toork.ParkingSpace values " + "(?, ?, ?, ?); ";

        for(int i = theMaxSpace + 1; i <= theSpaces + theMaxSpace; i++) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.setInt(1, i);
                preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
                preparedStatement.setString(3, theLotName);
                preparedStatement.setBoolean(4, false);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public void addParkingLot(ParkingLot theParkingLot) {
        String sql = "insert into toork.ParkingLot values " + "(?, ?, ?, ?); ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
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

    public StaffMember getStaff(int theStaffNumber) {
        StaffMember toReturn = null;
        try {
            myStaffList = getStaffMembers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (StaffMember staffMember : myStaffList) {
            if (staffMember.getMyStaffNumber() == theStaffNumber) {
                toReturn = staffMember;
            }
        }
        return toReturn;
    }

    public void addStaffMember(StaffMember theStaffMember) {
        String sql = "insert into toork.Staff values " + "(?, ?, ?, ?); ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
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

    public void updateStaff(int theRow, String theColumnName, Object theData) {

        StaffMember staffMember = myStaffList.get(theRow);
        int staffNumber = staffMember.getMyStaffNumber();
        String sql = "update toork.Staff set " + theColumnName + " = ?  where staffNumber = ? ";
        System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
            if (theData instanceof String)
                preparedStatement.setString(1, (String) theData);
            preparedStatement.setInt(2, staffNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateParkingSpace(int theSpaceNumber, String theColumnName, Object theData) {

        String sql = "update toork.ParkingSpace set " + theColumnName + " = ?  where spaceNumber = ? ";
        System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
            if (theData instanceof BigDecimal)
                preparedStatement.setBigDecimal(1, (BigDecimal) theData);
            else
                preparedStatement.setBoolean(1, (boolean) theData);
            preparedStatement.setInt(2, theSpaceNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateParkingLot(String theLotName, String theColumnName, int theData) {

        String sql = "update toork.ParkingSpace set " + theColumnName + " = ?  where lotName = ? ";
        System.out.println(sql);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
            preparedStatement.setInt(1, (int) theData);
            preparedStatement.setString(2, theLotName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void addStaffSpace(int theStaffNumber, int theSpaceNumber) {
        String sql = "insert into toork.StaffSpace values " + "(?, ?); ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
            preparedStatement.setInt(1, theStaffNumber);
            preparedStatement.setInt(2, theSpaceNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void addBooking(SpaceBooking theSpaceBooking) {
        String sql = "insert into toork.SpaceBooking values " + "(?, ?, ?, ?, ?); ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = myConn.prepareStatement(sql);
            preparedStatement.setInt(1, theSpaceBooking.getMyBookingId());
            preparedStatement.setInt(2, theSpaceBooking.getMyParkingSpaceNumber());
            preparedStatement.setInt(3, theSpaceBooking.getMyStaffNumber());
            preparedStatement.setString(4, theSpaceBooking.getMyLicenseNumber());
            preparedStatement.setDate(5, theSpaceBooking.getMyDateOfVisit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
