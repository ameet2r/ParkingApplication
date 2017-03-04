import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.util.List;
import java.sql.SQLException;

/**
 * This is the GUI class that displays all of the tables, and allows the user to interact with the database.
 * Author: mmuppa, modified by Toork (Karanbir Toor), Ameet2r (Ameet Toor)
 */
public class GUI extends JFrame implements ActionListener, TableModelListener
{
    /**
     * The serial id of this class.
     */
    private static final long serialVersionUID = 1779520078061383929L;

    /**
     * Buttons that are at the top of the gui that allow the user to change the view of the gui.
     */
    private JButton myBtnAddParkingLotView, myBtnAddParkingSpaceView, myBtnAddStaffMemberView, myBtnEditStaffMemberView, myBtnAssignParkingSpaceView, myBtnAssignParkingSpaceToVisitorView;

    /**
     * Buttons that are in each view that allow the user to submit whatever they are changing.
     */
    private JButton myBtnAddParkingLot, myBtnAddParkingSpace, myBtnAddStaffMember, myBtnEditStaffMember, myBtnAssignParkingSpace, myBtnAssignParkingSpaceToVisitor;

    /**
     * JPanels that show the content of the gui.
     * myPnlButtons shows the buttons on the top of the gui.
     * myPnlContent shows the content under the buttons.
     */
    private JPanel myPnlButtons, myPnlContent;

    /**
     * Names for columns in the parking lot tables.
     */
    private String[] myParkingLotColumnNames = {"lotName", "location", "capacity", "floors"};

    /**
     * Names for columns in the parking space tables.
     */
    private String[] myParkingSpaceColumnNames = {"spaceNumber", "monthlyRate", "lotName", "isTaken"};

    /**
     * Names for columns in the staff tables.
     */
    private String[] myStaffColumnNames = {"staffNumber", "staffName", "telephoneExt", "vehicleLicenseNumber"};

    /**
     * 2D Object array that holds the data for the tables each time the tables need to be updated.
     */
    private Object[][] myData;

    /**
     * JLabel array that holds all of the labels that give directions of what to input into the JTextFields.
     */
    private JLabel[] myTxfLabel = new JLabel[5];

    /**
     * The JTextFields that will take input from the user.
     */
    private JTextField[] myTxfField = new JTextField[5];

    /**
     * The Add Parking Lot panel.
     */
    private JPanel myPnlAddParkingLot;

    /**
     * The Add Parking Space panel.
     */
    private JPanel myPnlAddParkingSpace;

    /**
     * The Add Staff member panel.
     */
    private JPanel myPnlAddStaffMember;

    /**
     * The Edit Staff member panel.
     */
    private JPanel myPnlEditStaffMember;

    /**
     * The Assign Parking Space panel.
     */
    private JPanel myPnlAssignParkingSpace;

    /**
     * The Assign Parking Space to Visitor panel.
     */
    private JPanel myPnlAssignParkingSpaceToVisitor;

    /**
     * The table that holds the staff members.
     */
    private JTable myStaffTable;

    /**
     * The table that holds the parking spaces.
     */
    private JTable myParkingSpacesTable;

    /**
     * The table that holds the parking lots.
     */
    private JTable myParkingLotsTable;

    /**
     * The JScrollPane that will hold the staff table.
     */
    private JScrollPane myStaffScrollPane;

    /**
     * The JScrollPane that will hold the Parking spaces tables.
     */
    private JScrollPane myParkingSpacesScrollPane;

    /**
     * The JScrollPane that will hold the parking lots table.
     */
    private JScrollPane myParkingLotsScrollPane;

    /**
     * The database class that connects to the database that holds all the tables we need to access.
     */
    private ParkingAppDB myParkingAppDB;

    /**
     * List of parking lots.
     */
    private List<ParkingLot> myListOfParkingLots;

    /**
     * List of staff members.
     */
    private List<StaffMember> myListOfStaffMembers;

    /**
     * List of parking spaces.
     */
    private List<ParkingSpace> myListOfParkingSpaces;



    /**
     * Creates the frame and components and launches the GUI.
     */
    public GUI() {
        super("Parking Application");

        myParkingAppDB = new ParkingAppDB();
        try
        {
            myListOfParkingLots = myParkingAppDB.getParkingLots();
            myListOfStaffMembers = myParkingAppDB.getStaffMembers();
            myListOfParkingSpaces = myParkingAppDB.getParkingSpaces();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        myData = new Object[myListOfParkingLots.size()][myParkingLotColumnNames.length];
        for (int i=0; i<myListOfParkingLots.size(); i++) {
            myData[i][0] = myListOfParkingLots.get(i).getMyLotName();
            myData[i][1] = myListOfParkingLots.get(i).getMyLocation();
            myData[i][2] = myListOfParkingLots.get(i).getMyCapacity();
            myData[i][3] = myListOfParkingLots.get(i).getMyNumberOfFloors();
        }
        createComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Creates panels for "Add Parking lot", "Add Parking Space", "Add Staff Member", "Edit Staff Member",
     * "Assign Parking Space", "Assign parking space to visitor" and assigns panels to each panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void createComponents()
    {
        myPnlContent = new JPanel();
        myPnlButtons = new JPanel();

        myBtnAddParkingLotView = new JButton("Add Parking Lot");
        myBtnAddParkingLotView.addActionListener(this);

        myBtnAddParkingSpaceView = new JButton("Add Parking Space");
        myBtnAddParkingSpaceView.addActionListener(this);

        myBtnAddStaffMemberView = new JButton("Add Staff Member");
        myBtnAddStaffMemberView.addActionListener(this);

        myBtnEditStaffMemberView = new JButton("Edit Staff Member");
        myBtnEditStaffMemberView.addActionListener(this);

        myBtnAssignParkingSpaceView = new JButton("Assign Parking Space");
        myBtnAssignParkingSpaceView.addActionListener(this);

        myBtnAssignParkingSpaceToVisitorView = new JButton("Assign parking space to visitor");
        myBtnAssignParkingSpaceToVisitorView.addActionListener(this);

        myPnlButtons.add(myBtnAddParkingLotView);
        myPnlButtons.add(myBtnAddParkingSpaceView);
        myPnlButtons.add(myBtnAddStaffMemberView);
        myPnlButtons.add(myBtnEditStaffMemberView);
        myPnlButtons.add(myBtnAssignParkingSpaceView);
        myPnlButtons.add(myBtnAssignParkingSpaceToVisitorView);
        add(myPnlButtons, BorderLayout.NORTH);

        //Add parking lot panel
        addParkingLotPanel();
        add(myPnlContent, BorderLayout.CENTER);

        //add parking space panel
        addParkingSpacePanel();

        //add staff member panel
        addStaffMemberPanel();

        //edit staff member panel
        editStaffMemberPanel();

        //assign parking space panel
        assignParkingSpacePanel();

        //assign parking space to visitor panel
        assignParkingSpaceToVisitorPanel();
    }

    /**
     * Adds the panels that correspond to the "Add Parking Lot" section of the gui to the add parking lot panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void addParkingLotPanel()
    {
        myPnlAddParkingLot = new JPanel();
        myPnlAddParkingLot.setLayout(new GridLayout(4,0));
        String labelNames[] = {"Enter Lot Name: ", "Enter Lot Capacity", "Enter Number of floors: ", "Enter location: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAddParkingLot.add(panel);
        }
        JPanel panel = new JPanel();
        myBtnAddParkingLot = new JButton("Submit");
        myBtnAddParkingLot.addActionListener(this);
        panel.add(myBtnAddParkingLot);
        myPnlAddParkingLot.add(panel);
    }

    /**
     * Adds the panels that correspond to the "Add Parking Space" section of the gui to the add parking space panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void addParkingSpacePanel()
    {
        myPnlAddParkingSpace = new JPanel();
        myPnlAddParkingSpace.setLayout(new GridLayout(3,0));
        String labelNames[] = {"Enter Lot name: ", "Enter how many spaces to add: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAddParkingSpace.add(panel);
        }
        JPanel panel = new JPanel();
        myBtnAddParkingSpace = new JButton("Submit");
        myBtnAddParkingSpace.addActionListener(this);
        panel.add(myBtnAddParkingSpace);
        myPnlAddParkingSpace.add(panel);

        panel = new JPanel();
        //show parking lots scroll pane
        try {
            myListOfParkingLots = myParkingAppDB.getParkingLots();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        myData = new Object[myListOfParkingLots.size()][myParkingLotColumnNames.length];
        for (int i=0; i<myListOfParkingLots.size(); i++) {
            myData[i][0] = myListOfParkingLots.get(i).getMyLotName();
            myData[i][1] = myListOfParkingLots.get(i).getMyLocation();
            myData[i][2] = myListOfParkingLots.get(i).getMyCapacity();
            myData[i][3] = myListOfParkingLots.get(i).getMyNumberOfFloors();
        }
        myParkingLotsTable = new JTable(myData, myParkingLotColumnNames);
        myParkingLotsScrollPane = new JScrollPane(myParkingLotsTable);
        panel.add(myParkingLotsScrollPane);
        myParkingLotsTable.getModel().addTableModelListener(this);
        myPnlAddParkingSpace.add(panel);
    }

    /**
     * Adds the panels that correspond to the "Add Staff Member" section of the gui to the add staff member panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void addStaffMemberPanel()
    {
        myPnlAddStaffMember = new JPanel();
        myPnlAddStaffMember.setLayout( new GridLayout(6,2));
        String labelNames[] = {"Enter Name: ", "Enter license number: ", "Enter Telephone Extension Number: ", "Enter Staff Number: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAddStaffMember.add(panel);
        }
        JPanel panel = new JPanel();
        myBtnAddStaffMember = new JButton("Submit");
        myBtnAddStaffMember.addActionListener(this);
        panel.add(myBtnAddStaffMember);
        myPnlAddStaffMember.add(panel);
    }

    /**
     * Adds the panels that correspond to the "Edit Staff Member" section of the gui to the edit staff member panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void editStaffMemberPanel()
    {
        myPnlEditStaffMember = new JPanel();
        myPnlEditStaffMember.setLayout(new GridLayout(2,0));
        String labelNames[] = {"Enter Staff number: ", "Enter new telephone extension number: ", "Enter new vehicle license number: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2,0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlEditStaffMember.add(panel);
        }
        JPanel panel = new JPanel();
        myBtnEditStaffMember = new JButton("Submit");
        myBtnEditStaffMember.addActionListener(this);
        panel.add(myBtnEditStaffMember);
        myPnlEditStaffMember.add(panel);

        //add staff table
        panel = new JPanel();
        try {
            myListOfStaffMembers = myParkingAppDB.getStaffMembers();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        myData = new Object[myListOfStaffMembers.size()][myStaffColumnNames.length];
        for (int i=0; i<myListOfStaffMembers.size(); i++) {
            myData[i][0] = myListOfStaffMembers.get(i).getMyStaffNumber();
            myData[i][1] = myListOfStaffMembers.get(i).getMyName();
            myData[i][2] = myListOfStaffMembers.get(i).getMyTelephoneExtNumber();
            myData[i][3] = myListOfStaffMembers.get(i).getMyVehicleLicenseNumber();
        }
        myStaffTable = new JTable(myData, myStaffColumnNames);
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        myPnlEditStaffMember.add(panel);
    }

    /**
     * Adds the panels that correspond to the "Assign Parking Space" section of the gui to the assign parking space panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void assignParkingSpacePanel()
    {
        myPnlAssignParkingSpace = new JPanel();
        myPnlAssignParkingSpace.setLayout(new GridLayout(2,3));
        String labelNames[] = {"Enter staff number: ", "Enter parking space number: ", "Enter monthly rate: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAssignParkingSpace.add(panel);
        }
        JPanel panel = new JPanel();
        myBtnAssignParkingSpace = new JButton("Submit");
        myBtnAssignParkingSpace.addActionListener(this);
        panel.add(myBtnAssignParkingSpace);
        myPnlAssignParkingSpace.add(panel);

        //add list of staff
        panel = new JPanel();
        try {
            myListOfStaffMembers = myParkingAppDB.getStaffMembers();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        myData = new Object[myListOfStaffMembers.size()][myStaffColumnNames.length];
        for (int i=0; i<myListOfStaffMembers.size(); i++) {
            myData[i][0] = myListOfStaffMembers.get(i).getMyStaffNumber();
            myData[i][1] = myListOfStaffMembers.get(i).getMyName();
            myData[i][2] = myListOfStaffMembers.get(i).getMyTelephoneExtNumber();
            myData[i][3] = myListOfStaffMembers.get(i).getMyVehicleLicenseNumber();
        }
        myStaffTable = new JTable(myData, myStaffColumnNames);
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        myPnlAssignParkingSpace.add(panel);


        //add list of parking spaces
        panel = new JPanel();
        try {
            myListOfParkingSpaces = myParkingAppDB.getParkingSpaces();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        myData = new Object[myListOfParkingSpaces.size()][myParkingSpaceColumnNames.length];
        for (int i=0; i<myListOfParkingSpaces.size(); i++) {
                myData[i][0] = myListOfParkingSpaces.get(i).getMyParkingSpaceNumber();
                myData[i][1] = myListOfParkingSpaces.get(i).getMyMonthlyRate();
                myData[i][2] = myListOfParkingSpaces.get(i).getMyLotName();
                myData[i][3] = myListOfParkingSpaces.get(i).isMyTaken();
        }
        myParkingSpacesTable = new JTable(myData, myParkingSpaceColumnNames);
        myParkingSpacesScrollPane = new JScrollPane(myParkingSpacesTable);
        panel.add(myParkingSpacesScrollPane);
        myParkingSpacesTable.getModel().addTableModelListener(this);
        myPnlAssignParkingSpace.add(panel);
    }

    /**
     * Adds the panels that correspond to the "Assign parking space to visitor" section of the gui
     * to the assign parking space to visitor panel.
     * Author: mmuppa, modified by Ameet Toor, Karanbir Toor
     */
    private void assignParkingSpaceToVisitorPanel()
    {
        myPnlAssignParkingSpaceToVisitor = new JPanel();
        myPnlAssignParkingSpaceToVisitor.setLayout(new GridLayout(2,3));
        String labelNames[] = {"space number: ", "license number: ",
                "date(YYYY-MM-DD): ", "staff number: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(15);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAssignParkingSpaceToVisitor.add(panel);
        }
        JPanel panel = new JPanel();
        myBtnAssignParkingSpaceToVisitor = new JButton("Submit");
        myBtnAssignParkingSpaceToVisitor.addActionListener(this);
        panel.add(myBtnAssignParkingSpaceToVisitor);
        myPnlAssignParkingSpaceToVisitor.add(panel);


        //add list of parking spaces
        panel = new JPanel();
        try {
            myListOfParkingSpaces = myParkingAppDB.getParkingSpaces();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        myData = new Object[myListOfParkingSpaces.size()][myParkingSpaceColumnNames.length];
        for (int i=0; i<myListOfParkingSpaces.size(); i++) {
            if(!myListOfParkingSpaces.get(i).isMyTaken()) {
                myData[i][0] = myListOfParkingSpaces.get(i).getMyParkingSpaceNumber();
                myData[i][1] = myListOfParkingSpaces.get(i).getMyMonthlyRate();
                myData[i][2] = myListOfParkingSpaces.get(i).getMyLotName();
                myData[i][3] = myListOfParkingSpaces.get(i).isMyTaken();
            }
        }
        myParkingSpacesTable = new JTable(myData, myParkingSpaceColumnNames);
        myParkingSpacesScrollPane = new JScrollPane(myParkingSpacesTable);
        panel.add(myParkingSpacesScrollPane);
        myParkingSpacesTable.getModel().addTableModelListener(this);
        myPnlAssignParkingSpaceToVisitor.add(panel);

        //add list of staff
        panel = new JPanel();
        try {
            myListOfStaffMembers = myParkingAppDB.getStaffMembers();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        myData = new Object[myListOfStaffMembers.size()][myStaffColumnNames.length];
        for (int i=0; i<myListOfStaffMembers.size(); i++) {
            myData[i][0] = myListOfStaffMembers.get(i).getMyStaffNumber();
            myData[i][1] = myListOfStaffMembers.get(i).getMyName();
            myData[i][2] = myListOfStaffMembers.get(i).getMyTelephoneExtNumber();
            myData[i][3] = myListOfStaffMembers.get(i).getMyVehicleLicenseNumber();
        }
        myStaffTable = new JTable(myData, myStaffColumnNames);
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        myPnlAssignParkingSpaceToVisitor.add(panel);
    }

    /**
     * The main method that creates the GUI object that runs this program.
     * @param args
     */
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Event handling to change the panels when different tabs are clicked,
     * the submit buttons do .
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == myBtnAddParkingLotView){
            myPnlContent.removeAll();
            addParkingLotPanel();
            myPnlContent.add(myPnlAddParkingLot);
            myPnlContent.revalidate();
            this.repaint();

        } else if (e.getSource() == myBtnAddParkingSpaceView) {
            myPnlContent.removeAll();
            addParkingSpacePanel();
            myPnlContent.add(myPnlAddParkingSpace);
            myPnlContent.revalidate();
            this.repaint();
        } else if (e.getSource() == myBtnAddStaffMemberView) {

            myPnlContent.removeAll();
            addStaffMemberPanel();
            myPnlContent.add(myPnlAddStaffMember);
            myPnlContent.revalidate();
            this.repaint();

        } else if (e.getSource() == myBtnEditStaffMemberView) {
            myPnlContent.removeAll();
            editStaffMemberPanel();
            myPnlContent.add(myPnlEditStaffMember);
            myPnlContent.revalidate();
            this.repaint();
        } else if (e.getSource() == myBtnAssignParkingSpaceView) {
            myPnlContent.removeAll();
            assignParkingSpacePanel();
            myPnlContent.add(myPnlAssignParkingSpace);
            myPnlContent.revalidate();
            this.repaint();
        }
        else if (e.getSource() == myBtnAssignParkingSpaceToVisitorView)
        {
            myPnlContent.removeAll();
            assignParkingSpaceToVisitorPanel();
            myPnlContent.add(myPnlAssignParkingSpaceToVisitor);
            myPnlContent.revalidate();
            this.repaint();
        }
        else if(e.getSource() == myBtnAddParkingLot)
        {
            String parkingLotName = "";
            int parkingLotCapacity = 0;
            int parkingLotNumFloors = 0;
            String parkingLotLocation = "";

            try{
                parkingLotName = myTxfField[0].getText();
                parkingLotCapacity = Integer.parseInt(myTxfField[1].getText());
                parkingLotNumFloors = Integer.parseInt(myTxfField[2].getText());
                parkingLotLocation = myTxfField[3].getText();

                ParkingLot parkingLot = new ParkingLot(parkingLotName, parkingLotLocation,
                        parkingLotCapacity, parkingLotNumFloors);

                //check if parking lot is already in list of parking lots
                myListOfParkingLots = myParkingAppDB.getParkingLots();
                boolean canAddParkingLot = true;
                for(ParkingLot parkingLot1 : myListOfParkingLots)
                {
                    if(parkingLot.getMyLotName() == parkingLot1.getMyLotName())
                    {
                        canAddParkingLot = false;
                    }
                }


                //send to db
                if(canAddParkingLot)
                {
                    myParkingAppDB.addParkingLot(parkingLot);

                    myParkingAppDB.addParkingSpace(parkingLotCapacity, parkingLotName, myParkingAppDB.getMaxParkingSpaceNumber());
                }



                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }
        }
        else if(e.getSource() == myBtnAddParkingSpace)
        {
            String parkingLotName = "";
            int numberOfParkingSpaces = 0;

            try{
                parkingLotName = myTxfField[0].getText();
                numberOfParkingSpaces = Integer.parseInt(myTxfField[1].getText());

                //grab current capacity
                myListOfParkingLots = myParkingAppDB.getParkingLots();
                int currentCapacity = 0;
                for(ParkingLot parkingLot : myListOfParkingLots)
                {
                    if(parkingLot.getMyLotName().equals(parkingLotName))
                    {
                        currentCapacity = parkingLot.getMyCapacity();
                        break;
                    }
                }

                //Update capacity of parking lot
                int newCapacity = currentCapacity + numberOfParkingSpaces;
                myParkingAppDB.updateParkingLot(parkingLotName, "capacity", newCapacity);


                //add parking space to lot
                myParkingAppDB.addParkingSpace(numberOfParkingSpaces, parkingLotName,
                        myParkingAppDB.getMaxParkingSpaceNumber());


                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }
            myPnlContent.removeAll();
            addParkingSpacePanel();
            myPnlContent.add(myPnlAddParkingSpace);
            myPnlContent.revalidate();
            this.repaint();



        }
        else if(e.getSource() == myBtnAddStaffMember)
        {
            String staffName = "";
            String licenseNumber = "";
            String telephoneExtNum = "";
            int staffNumber = 0;

            try{
                staffName = myTxfField[0].getText();
                licenseNumber = myTxfField[1].getText();
                telephoneExtNum = myTxfField[2].getText();
                staffNumber = Integer.parseInt(myTxfField[3].getText());

                StaffMember staffMember = new StaffMember(staffNumber, staffName, telephoneExtNum, licenseNumber);
                //reset text fields
//                for (int i=0; i < myTxfField.length; i++) {
//                    myTxfField[i].setText("");
//                }


                //send to db
                myParkingAppDB.addStaffMember(staffMember);


                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }
        }
        else if(e.getSource() == myBtnEditStaffMember)
        {
            int staffNumber = 0;
            String newTelephoneExtNum = "";
            String newLicenseNum = "";
            try{
                staffNumber = Integer.parseInt(myTxfField[0].getText());
                newTelephoneExtNum = myTxfField[1].getText();
                newLicenseNum = myTxfField[2].getText();

//                for (int i=0; i<myTxfField.length; i++) {
//                    myTxfField[i].setText("");
//                }

                //compare staff number against list of staff members, find index
                int indexToUpdate = -1;
                myListOfStaffMembers = myParkingAppDB.getStaffMembers();
                for(int i = 0; i < myListOfStaffMembers.size(); i++)
                {
                    if(myListOfStaffMembers.get(i).getMyStaffNumber() == staffNumber)
                    {
                        indexToUpdate = i;
                        break;
                    }
                }

                //then call updateStaff(index, "vehicleLicenseNumber", newTelephneExtNum);
                //and updateStaff(index, "telephoneExt", newLicenseNum);

                if(indexToUpdate != -1)
                {
                    if(!newTelephoneExtNum.equals(""))
                    {
                        myParkingAppDB.updateStaff(indexToUpdate, "telephoneExt", newTelephoneExtNum);
                        //System.out.println("update telephone");
                    }

                    if(!newLicenseNum.equals(""))
                    {
                        myParkingAppDB.updateStaff(indexToUpdate, "vehicleLicenseNumber", newLicenseNum);
                        //System.out.println("update license num");
                    }
                }

                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }


            myPnlContent.removeAll();
            editStaffMemberPanel();
            myPnlContent.add(myPnlEditStaffMember);
            myPnlContent.revalidate();
            this.repaint();
        }
        else if(e.getSource() == myBtnAssignParkingSpace)
        {
            int staffNumber = 0;
            int parkingSpaceNumber = 0;
            BigDecimal monthlyRate = BigDecimal.ZERO;
            try{
                staffNumber = Integer.parseInt(myTxfField[0].getText());
                parkingSpaceNumber = Integer.parseInt(myTxfField[1].getText());
                monthlyRate = BigDecimal.valueOf(Double.parseDouble(myTxfField[2].getText()));



                //send to db
                myParkingAppDB.addStaffSpace(staffNumber, parkingSpaceNumber);
                myParkingAppDB.updateParkingSpace(parkingSpaceNumber, "monthlyRate", monthlyRate);
                myParkingAppDB.updateParkingSpace(parkingSpaceNumber, "taken", true);

                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }
            myPnlContent.removeAll();
            assignParkingSpacePanel();
            myPnlContent.add(myPnlAssignParkingSpace);
            myPnlContent.revalidate();
            this.repaint();
        }
        else if (e.getSource() == myBtnAssignParkingSpaceToVisitor)
        {
            int parkingSpaceNumber = 0;
            String visitorLicenseNum = "";
            String date = "";
            int staffNum = 0;
            int bookingId = 0;
            try{
                parkingSpaceNumber = Integer.parseInt(myTxfField[0].getText());
                visitorLicenseNum = myTxfField[1].getText();
                date = myTxfField[2].getText();
                staffNum = Integer.parseInt(myTxfField[3].getText());



                //TODO send to db
                SpaceBooking spaceBooking = new SpaceBooking(myParkingAppDB.getMaxBookingNumber() + 1,
                        parkingSpaceNumber, staffNum, visitorLicenseNum, java.sql.Date.valueOf(date));

                myParkingAppDB.addBooking(spaceBooking);
                myParkingAppDB.updateParkingSpace(parkingSpaceNumber, "taken", true);

                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }

            myPnlContent.removeAll();
            assignParkingSpaceToVisitorPanel();
            myPnlContent.add(myPnlAssignParkingSpaceToVisitor);
            myPnlContent.revalidate();
            this.repaint();
        }

    }

    /**
     * Event handling for any cell being changed in the table.
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

    }

}
