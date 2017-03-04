/**
 * Author: mmuppa, modified by Ameet2r
 */
import org.omg.CORBA.INTERNAL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//TODO finish layouts                                                       DONE
//TODO grab data from textboxes when user presses add                       DONE
//TODO modify input to tell user what to input                              DONE
//TODO add specific column names for each table.
//TODO change fields to start with my



public class GUI extends JFrame implements ActionListener, TableModelListener
{

    private static final long serialVersionUID = 1779520078061383929L;
    private JButton btnAddParkingLotView, btnAddParkingSpaceView, btnAddStaffMemberView, btnEditStaffMemberView, btnAssignParkingSpaceView, btnAssignParkingSpaceToVisitorView;
    private JButton btnAddParkingLot, btnAddParkingSpace, btnAddStaffMember, btnEditStaffMember, btnAssignParkingSpace, btnAssignParkingSpaceToVisitor;
    private JPanel pnlButtons, pnlContent;
   // private MovieDB db;
    private List<Movie> list;
    private String[] columnNames = {"Title",
            "Year",
            "Length",
            "Genre",
            "StudioName"};

    private String[] myParkingLotColumnNames = {"lotName", "location", "capacity", "floors"};
    private String[] myParkingSpaceColumnNames = {"spaceNumber", "monthlyRate", "lotName", "isTaken"};
    private String[] myStaffColumnNames = {"staffNumber", "staffName", "telephoneExt", "vehicleLicenseNumber"};

    private Object[][] data;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel pnlSearch;
    private JLabel lblTitle;;
    private JTextField txfTitle;
    private JButton btnTitleSearch;

    private JPanel pnlAdd;
    private JLabel[] myTxfLabel = new JLabel[5];
    private JTextField[] myTxfField = new JTextField[5];
    private JButton btnAddMovie;

    private JPanel pnlAddParkingLot;
    private JPanel pnlAddParkingSpace;
    private JPanel pnlAddStaffMember;
    private JPanel pnlEditStaffMember;
    private JPanel pnlAssignParkingSpace;
    private JPanel pnlAssignParkingSpaceToVisitor;

    private JTable myStaffTable;
    private JTable myParkingSpacesTable;
    private JTable myParkingLotsTable;

    private JScrollPane myStaffScrollPane;
    private JScrollPane myParkingSpacesScrollPane;
    private JScrollPane myParkingLotsScrollPane;

    private ParkingAppDB myParkingAppDB;

    private List<ParkingLot> myListOfParkingLots;
    private List<StaffMember> myListOfStaffMembers;
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
        data = new Object[myListOfParkingLots.size()][myParkingLotColumnNames.length];
        for (int i=0; i<myListOfParkingLots.size(); i++) {
            data[i][0] = myListOfParkingLots.get(i).getMyLotName();
            data[i][1] = myListOfParkingLots.get(i).getMyLocation();
            data[i][2] = myListOfParkingLots.get(i).getMyCapacity();
            data[i][3] = myListOfParkingLots.get(i).getMyNumberOfFloors();
        }
        createComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        setVisible(true);
    }

    /**
     * Creates panels for Movie list, search, add and adds the corresponding
     * components to each panel.
     * Author: mmuppa, modified by ameet2r
     */
    private void createComponents()
    {
        pnlContent = new JPanel();
        pnlButtons = new JPanel();

        btnAddParkingLotView = new JButton("Add Parking Lot");
        btnAddParkingLotView.addActionListener(this);

        btnAddParkingSpaceView = new JButton("Add Parking Space");
        btnAddParkingSpaceView.addActionListener(this);

        btnAddStaffMemberView = new JButton("Add Staff Member");
        btnAddStaffMemberView.addActionListener(this);

        btnEditStaffMemberView = new JButton("Edit Staff Member");
        btnEditStaffMemberView.addActionListener(this);

        btnAssignParkingSpaceView = new JButton("Assign Parking Space");
        btnAssignParkingSpaceView.addActionListener(this);

        btnAssignParkingSpaceToVisitorView = new JButton("Assign parking space to visitor");
        btnAssignParkingSpaceToVisitorView.addActionListener(this);

        pnlButtons.add(btnAddParkingLotView);
        pnlButtons.add(btnAddParkingSpaceView);
        pnlButtons.add(btnAddStaffMemberView);
        pnlButtons.add(btnEditStaffMemberView);
        pnlButtons.add(btnAssignParkingSpaceView);
        pnlButtons.add(btnAssignParkingSpaceToVisitorView);
        add(pnlButtons, BorderLayout.NORTH);

        //Add parking lot panel
        addParkingLotPanel();
        add(pnlContent, BorderLayout.CENTER);

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

    private void addParkingLotPanel()
    {
        pnlAddParkingLot = new JPanel();
        pnlAddParkingLot.setLayout(new GridLayout(4,0));
        String labelNames[] = {"Enter Lot Name: ", "Enter Lot Capacity", "Enter Number of floors: ", "Enter location: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            pnlAddParkingLot.add(panel);
        }
        JPanel panel = new JPanel();
        btnAddParkingLot = new JButton("Add");
        btnAddParkingLot.addActionListener(this);
        panel.add(btnAddParkingLot);
        pnlAddParkingLot.add(panel);
    }

    private void addParkingSpacePanel()
    {
        pnlAddParkingSpace = new JPanel();
        pnlAddParkingSpace.setLayout(new GridLayout(3,0));
        String labelNames[] = {"Enter Lot name: ", "Enter how many spaces to add: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            pnlAddParkingSpace.add(panel);
        }
        JPanel panel = new JPanel();
        btnAddParkingSpace = new JButton("Add");
        btnAddParkingSpace.addActionListener(this);
        panel.add(btnAddParkingSpace);
        pnlAddParkingSpace.add(panel);

        panel = new JPanel();
        //show parking lots scroll pane
        try {
            myListOfParkingLots = myParkingAppDB.getParkingLots();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[myListOfParkingLots.size()][myParkingLotColumnNames.length];
        for (int i=0; i<myListOfParkingLots.size(); i++) {
            data[i][0] = myListOfParkingLots.get(i).getMyLotName();
            data[i][1] = myListOfParkingLots.get(i).getMyLocation();
            data[i][2] = myListOfParkingLots.get(i).getMyCapacity();
            data[i][3] = myListOfParkingLots.get(i).getMyNumberOfFloors();
        }
        myParkingLotsTable = new JTable(data, myParkingLotColumnNames);
        myParkingLotsScrollPane = new JScrollPane(myParkingLotsTable);
        panel.add(myParkingLotsScrollPane);
        myParkingLotsTable.getModel().addTableModelListener(this);
        pnlAddParkingSpace.add(panel);
    }

    private void addStaffMemberPanel()
    {
        pnlAddStaffMember = new JPanel();
        pnlAddStaffMember.setLayout( new GridLayout(6,2));
        String labelNames[] = {"Enter Name: ", "Enter license number: ", "Enter Telephone Extension Number: ", "Enter Staff Number: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            pnlAddStaffMember.add(panel);
        }
        JPanel panel = new JPanel();
        btnAddStaffMember = new JButton("Add");
        btnAddStaffMember.addActionListener(this);
        panel.add(btnAddStaffMember);
        pnlAddStaffMember.add(panel);
    }

    private void editStaffMemberPanel()
    {
        pnlEditStaffMember = new JPanel();
        pnlEditStaffMember.setLayout(new GridLayout(2,0));
        String labelNames[] = {"Enter Staff number: ", "Enter new telephone extension number: ", "Enter new vehicle license number: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2,0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            pnlEditStaffMember.add(panel);
        }
        JPanel panel = new JPanel();
        btnEditStaffMember = new JButton("Add");
        btnEditStaffMember.addActionListener(this);
        panel.add(btnEditStaffMember);
        pnlEditStaffMember.add(panel);


        //add staff table
        panel = new JPanel();
        //TODO remove and add real data
        try {
            myListOfStaffMembers = myParkingAppDB.getStaffMembers();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[myListOfStaffMembers.size()][myStaffColumnNames.length];
        for (int i=0; i<myListOfStaffMembers.size(); i++) {
            data[i][0] = myListOfStaffMembers.get(i).getMyStaffNumber();
            data[i][1] = myListOfStaffMembers.get(i).getMyName();
            data[i][2] = myListOfStaffMembers.get(i).getMyTelephoneExtNumber();
            data[i][3] = myListOfStaffMembers.get(i).getMyVehicleLicenseNumber();
        }
        myStaffTable = new JTable(data, myStaffColumnNames);
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        pnlEditStaffMember.add(panel);
    }

    private void assignParkingSpacePanel()
    {
        pnlAssignParkingSpace = new JPanel();
        pnlAssignParkingSpace.setLayout(new GridLayout(2,3));
        String labelNames[] = {"Enter staff number: ", "Enter parking space number: ", "Enter monthly rate: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            pnlAssignParkingSpace.add(panel);
        }
        JPanel panel = new JPanel();
        btnAssignParkingSpace = new JButton("Add");
        btnAssignParkingSpace.addActionListener(this);
        panel.add(btnAssignParkingSpace);
        pnlAssignParkingSpace.add(panel);

        //add list of staff
        panel = new JPanel();
        //TODO remove and add real data
        try {
            myListOfStaffMembers = myParkingAppDB.getStaffMembers();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[myListOfStaffMembers.size()][myStaffColumnNames.length];
        for (int i=0; i<myListOfStaffMembers.size(); i++) {
            data[i][0] = myListOfStaffMembers.get(i).getMyStaffNumber();
            data[i][1] = myListOfStaffMembers.get(i).getMyName();
            data[i][2] = myListOfStaffMembers.get(i).getMyTelephoneExtNumber();
            data[i][3] = myListOfStaffMembers.get(i).getMyVehicleLicenseNumber();
        }
        myStaffTable = new JTable(data, myStaffColumnNames);
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        pnlAssignParkingSpace.add(panel);


        //add list of parking spaces
        panel = new JPanel();
        try {
            myListOfParkingSpaces = myParkingAppDB.getParkingSpaces();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[myListOfParkingSpaces.size()][myParkingSpaceColumnNames.length];
        for (int i=0; i<myListOfParkingSpaces.size(); i++) {
                data[i][0] = myListOfParkingSpaces.get(i).getMyParkingSpaceNumber();
                data[i][1] = myListOfParkingSpaces.get(i).getMyMonthlyRate();
                data[i][2] = myListOfParkingSpaces.get(i).getMyLotName();
                data[i][3] = myListOfParkingSpaces.get(i).isMyTaken();
        }
        myParkingSpacesTable = new JTable(data, myParkingSpaceColumnNames);
        myParkingSpacesScrollPane = new JScrollPane(myParkingSpacesTable);
        panel.add(myParkingSpacesScrollPane);
        myParkingSpacesTable.getModel().addTableModelListener(this);
        pnlAssignParkingSpace.add(panel);
    }

    private void assignParkingSpaceToVisitorPanel()
    {
        pnlAssignParkingSpaceToVisitor = new JPanel();
        pnlAssignParkingSpaceToVisitor.setLayout(new GridLayout(2,3));
        String labelNames[] = {"Enter parking space number: ", "Enter visitor license number: ",
                "Enter date (YYYY-MM-DD): ", "Enter staff number: "};
        for(int i = 0; i < labelNames.length; i++)
        {
            JPanel panel = new JPanel();
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            pnlAssignParkingSpaceToVisitor.add(panel);
        }
        JPanel panel = new JPanel();
        btnAssignParkingSpaceToVisitor = new JButton("Add");
        btnAssignParkingSpaceToVisitor.addActionListener(this);
        panel.add(btnAssignParkingSpaceToVisitor);
        pnlAssignParkingSpaceToVisitor.add(panel);


        //add list of parking spaces
        panel = new JPanel();
        try {
            myListOfParkingSpaces = myParkingAppDB.getParkingSpaces();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        data = new Object[myListOfParkingSpaces.size()][myParkingSpaceColumnNames.length];
        for (int i=0; i<myListOfParkingSpaces.size(); i++) {
            if(!myListOfParkingSpaces.get(i).isMyTaken()) {
                data[i][0] = myListOfParkingSpaces.get(i).getMyParkingSpaceNumber();
                data[i][1] = myListOfParkingSpaces.get(i).getMyMonthlyRate();
                data[i][2] = myListOfParkingSpaces.get(i).getMyLotName();
                data[i][3] = myListOfParkingSpaces.get(i).isMyTaken();
            }
        }
        myParkingSpacesTable = new JTable(data, myParkingSpaceColumnNames);
        myParkingSpacesScrollPane = new JScrollPane(myParkingSpacesTable);
        panel.add(myParkingSpacesScrollPane);
        myParkingSpacesTable.getModel().addTableModelListener(this);
        pnlAssignParkingSpaceToVisitor.add(panel);

    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Event handling to change the panels when different tabs are clicked,
     * add and search buttons are clicked on the corresponding add and search panels.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnAddParkingLotView){
            pnlContent.removeAll();
            addParkingLotPanel();
            pnlContent.add(pnlAddParkingLot);
            pnlContent.revalidate();
            this.repaint();

        } else if (e.getSource() == btnAddParkingSpaceView) {
            pnlContent.removeAll();
            addParkingSpacePanel();
            pnlContent.add(pnlAddParkingSpace);
            pnlContent.revalidate();
            this.repaint();
        } else if (e.getSource() == btnAddStaffMemberView) {

            pnlContent.removeAll();
            addStaffMemberPanel();
            pnlContent.add(pnlAddStaffMember);
            pnlContent.revalidate();
            this.repaint();

        } else if (e.getSource() == btnEditStaffMemberView) {
            pnlContent.removeAll();
            editStaffMemberPanel();
            pnlContent.add(pnlEditStaffMember);
            pnlContent.revalidate();
            this.repaint();
        } else if (e.getSource() == btnAssignParkingSpaceView) {
            pnlContent.removeAll();
            assignParkingSpacePanel();
            pnlContent.add(pnlAssignParkingSpace);
            pnlContent.revalidate();
            this.repaint();
        }
        else if (e.getSource() == btnAssignParkingSpaceToVisitorView)
        {
            pnlContent.removeAll();
            assignParkingSpaceToVisitorPanel();
            pnlContent.add(pnlAssignParkingSpaceToVisitor);
            pnlContent.revalidate();
            this.repaint();
        }
        else if(e.getSource() == btnAddParkingLot)
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


                //reset text fields
                for (int i=0; i<myTxfField.length; i++) {
                    myTxfField[i].setText("");
                }
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
                }


                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }
        }
        else if(e.getSource() == btnAddParkingSpace)
        {
            String parkingLotName = "";
            int numberOfParkingSpaces = 0;

            try{
                parkingLotName = myTxfField[0].getText();
                numberOfParkingSpaces = Integer.parseInt(myTxfField[1].getText());

                //reset text fields
                for (int i=0; i<myTxfField.length; i++) {
                    myTxfField[i].setText("");
                }

                //send to db
                myParkingAppDB.addParkingSpace(numberOfParkingSpaces, parkingLotName,
                        myParkingAppDB.getMaxParkingSpaceNumber());


                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }
            pnlContent.removeAll();
            addParkingSpacePanel();
            pnlContent.add(pnlAddParkingSpace);
            pnlContent.revalidate();
            this.repaint();



        }
        else if(e.getSource() == btnAddStaffMember)
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
        else if(e.getSource() == btnEditStaffMember)
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


            pnlContent.removeAll();
            editStaffMemberPanel();
            pnlContent.add(pnlEditStaffMember);
            pnlContent.revalidate();
            this.repaint();
        }
        else if(e.getSource() == btnAssignParkingSpace)
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
            pnlContent.removeAll();
            assignParkingSpacePanel();
            pnlContent.add(pnlAssignParkingSpace);
            pnlContent.revalidate();
            this.repaint();
        }
        else if (e.getSource() == btnAssignParkingSpaceToVisitor)
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
//                SpaceBooking spaceBooking = new SpaceBooking()



                JOptionPane.showMessageDialog(null, "Success");
            }catch (Throwable t) {
                JOptionPane.showMessageDialog(null, "There was a problem");
            }

            pnlContent.removeAll();
            assignParkingSpaceToVisitorPanel();
            pnlContent.add(pnlAssignParkingSpaceToVisitor);
            pnlContent.revalidate();
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
