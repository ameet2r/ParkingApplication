/**
 * Author: mmuppa, modified by Ameet2r
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

//TODO finish layouts
//TODO grab data from textboxes when user presses add
//TODO modify input to tell user what to input                              DONE



public class GUI extends JFrame implements ActionListener, TableModelListener
{

    private static final long serialVersionUID = 1779520078061383929L;
    private JButton btnAddParkingLotView, btnAddParkingSpaceView, btnAddStaffMemberView, btnEditStaffMemberView, btnAssignParkingSpaceView, btnAssignParkingSpaceToVisitorView;
    private JButton btnAddParkingLot, btnAddParkingSpace, btnAddStaffMember, btnEditStaffMember, btnAssignParkingSpace, btnAssignParkingSpaceToVisitor;
    private JPanel pnlButtons, pnlContent;
    private MovieDB db;
    private List<Movie> list;
    private String[] columnNames = {"Title",
            "Year",
            "Length",
            "Genre",
            "StudioName"};

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




    /**
     * Creates the frame and components and launches the GUI.
     */
    public GUI() {
        super("Parking Application");

        //TODO replace with other parking db
        db = new MovieDB();
        try
        {
            list = db.getMovies();

            data = new Object[list.size()][columnNames.length];
            for (int i=0; i<list.size(); i++) {
                data[i][0] = list.get(i).getTitle();
                data[i][1] = list.get(i).getYear();
                data[i][2] = list.get(i).getLength();
                data[i][3] = list.get(i).getGenre();
                data[i][4] = list.get(i).getStudioName();

            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        createComponents();
        setVisible(true);
        setSize(1300, 1200);
        setMinimumSize(new Dimension(1300, 1200));
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
        String labelNames[] = {"Enter Lot Name: ", "Enter Lot Capacity", "Enter Number of floors: "};
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
        add(pnlContent, BorderLayout.CENTER);
    }

    private void addParkingSpacePanel()
    {
        pnlAddParkingSpace = new JPanel();
        pnlAddParkingSpace.setLayout(new GridLayout(3,0));
        String labelNames[] = {"Enter Lot name: ", "Enter monthly rate: "};
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
        //TODO remove and add real data
        try {
            list = db.getMovies();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[list.size()][columnNames.length];
        for (int i=0; i<list.size(); i++) {
            data[i][0] = list.get(i).getTitle();
            data[i][1] = list.get(i).getYear();
            data[i][2] = list.get(i).getLength();
            data[i][3] = list.get(i).getGenre();
            data[i][4] = list.get(i).getStudioName();
        }
        myParkingLotsTable = new JTable(data, columnNames); //modify by adding data and columnNames
        myParkingLotsScrollPane = new JScrollPane(myParkingLotsTable);
        panel.add(myParkingLotsScrollPane);
        myParkingLotsTable.getModel().addTableModelListener(this);
        pnlAddParkingSpace.add(panel);
    }

    private void addStaffMemberPanel()
    {
        pnlAddStaffMember = new JPanel();
        pnlAddStaffMember.setLayout( new GridLayout(6,2));
        String labelNames[] = {"Enter Name: ", "Enter license number: ", "Enter Telephone Extension Number: ",
                "Enter Number: ", "Enter Staff Number: "};
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
            list = db.getMovies();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[list.size()][columnNames.length];
        for (int i=0; i<list.size(); i++) {
            data[i][0] = list.get(i).getTitle();
            data[i][1] = list.get(i).getYear();
            data[i][2] = list.get(i).getLength();
            data[i][3] = list.get(i).getGenre();
            data[i][4] = list.get(i).getStudioName();
        }
        myStaffTable = new JTable(data, columnNames); //modify by adding data and columnNames
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        pnlEditStaffMember.add(panel);
    }

    private void assignParkingSpacePanel()
    {
        pnlAssignParkingSpace = new JPanel();
        pnlAssignParkingSpace.setLayout(new GridLayout(2,3));
        String labelNames[] = {"Enter staff number: ", "Enter parking space number: "};
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
            list = db.getMovies();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[list.size()][columnNames.length];
        for (int i=0; i<list.size(); i++) {
            data[i][0] = list.get(i).getTitle();
            data[i][1] = list.get(i).getYear();
            data[i][2] = list.get(i).getLength();
            data[i][3] = list.get(i).getGenre();
            data[i][4] = list.get(i).getStudioName();
        }
        myStaffTable = new JTable(data, columnNames); //modify by adding data and columnNames
        myStaffScrollPane = new JScrollPane(myStaffTable);
        panel.add(myStaffScrollPane);
        myStaffTable.getModel().addTableModelListener(this);
        pnlAssignParkingSpace.add(panel);


        //add list of parking spaces
        panel = new JPanel();
        //TODO remove and add real data
        try {
            list = db.getMovies();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[list.size()][columnNames.length];
        for (int i=0; i<list.size(); i++) {
            data[i][0] = list.get(i).getTitle();
            data[i][1] = list.get(i).getYear();
            data[i][2] = list.get(i).getLength();
            data[i][3] = list.get(i).getGenre();
            data[i][4] = list.get(i).getStudioName();
        }
        myParkingSpacesTable = new JTable(data, columnNames); //modify by adding data and columnNames
        myParkingSpacesScrollPane = new JScrollPane(myParkingSpacesTable);
        panel.add(myParkingSpacesScrollPane);
        myParkingSpacesTable.getModel().addTableModelListener(this);
        pnlAssignParkingSpace.add(panel);
    }

    private void assignParkingSpaceToVisitorPanel()
    {
        pnlAssignParkingSpaceToVisitor = new JPanel();
        pnlAssignParkingSpaceToVisitor.setLayout(new FlowLayout());
        String labelNames[] = {"Enter parking space number: ", "Enter visitor license number: "};
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
        //TODO remove and add real data
        try {
            list = db.getMovies();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        data = new Object[list.size()][columnNames.length];
        for (int i=0; i<list.size(); i++) {
            data[i][0] = list.get(i).getTitle();
            data[i][1] = list.get(i).getYear();
            data[i][2] = list.get(i).getLength();
            data[i][3] = list.get(i).getGenre();
            data[i][4] = list.get(i).getStudioName();
        }
        myParkingSpacesTable = new JTable(data, columnNames); //modify by adding data and columnNames
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

    private void updateStaffList()
    {

    }

    private void updateParkingLotList()
    {

    }

    private void updateParkingSpacesList()
    {

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

            //grab all parking lots and load into a list to add to choose parking lot thing

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

            //grab all staff members


            pnlContent.removeAll();
            editStaffMemberPanel();
            pnlContent.add(pnlEditStaffMember);
            pnlContent.revalidate();
            this.repaint();
        } else if (e.getSource() == btnAssignParkingSpaceView) {

            //grab all staff members, parking lots, and parking spaces

            pnlContent.removeAll();
            assignParkingSpacePanel();
            pnlContent.add(pnlAssignParkingSpace);
            pnlContent.revalidate();
            this.repaint();
        }
        else if (e.getSource() == btnAssignParkingSpaceToVisitorView)
        {
            //grab all parking lots and parking spaces
            pnlContent.removeAll();
            assignParkingSpaceToVisitorPanel();
            pnlContent.add(pnlAssignParkingSpaceToVisitor);
            pnlContent.revalidate();
            this.repaint();
        }
        else if(e.getSource() == btnAddParkingLot)
        {
            //get name

            //get capacity

            //get number of floors


            //fill in missing values

        }
        else if(e.getSource() == btnAddParkingSpace)
        {
            //grab parking lot

            //grab monthly rate

            //fill in missing values
        }
        else if(e.getSource() == btnAddStaffMember)
        {
            //get name

            //get telephone extension number

            //get staff number

            //get license number

            //get number

            //fill in missing values



        }
        else if(e.getSource() == btnEditStaffMember)
        {
            //get staff member

            //get vehicle license number

            //get telephone extension number

            //fill in missing values

        }
        else if(e.getSource() == btnAssignParkingSpace)
        {
            //get staff member

            //get parking space

            //fill in missing values



        }
        else if (e.getSource() == btnAssignParkingSpaceToVisitor)
        {

            //get parking space

            //get visitor license number

            //fill in missing values
        }

    }

    /**
     * Event handling for any cell being changed in the table.
     */
    @Override
    public void tableChanged(TableModelEvent e) {
//        int row = e.getFirstRow();
//        int column = e.getColumn();
//        TableModel model = (TableModel)e.getSource();
//        String columnName = model.getColumnName(column);
//        Object data = model.getValueAt(row, column);
//
//        db.updateMovie(row, columnName, data);

    }

}
