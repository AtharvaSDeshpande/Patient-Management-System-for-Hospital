import com.mysql.jdbc.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class ReceptionDashboard extends javax.swing.JFrame {
    public JPanel sidebar;
    private JButton employeeDetailsButton;
    private JButton patientDetailsButton;
    private JButton searchEmployeeButton;
    private JButton searchPatientButton;
    private JButton registerPatientButton;
    private JPanel EmployeeCount;
    private JPanel PatientCount;
    private JPanel RoomCount;
    private JLabel countDisplay;
    public JPanel recPanel;
    private JLabel image;
    private JTable EmployeeInfo;
    private JButton searchByIDButton;
    private JButton searchByNameButton;
    private JTextField searchField;
    private JLabel searchLabel;
    private JButton search;
    private JLabel patientCount;
    private JTable PatientInfo;
    private JButton cancelrecep;

    Connection connection;
    Statement statement;
    String sql;
    int flag;
    String searchFlag;

    JFrame frame = new JFrame("Welcome Receptionist");

    public ReceptionDashboard()
    {
        searchByIDButton.setVisible(false);
        searchByNameButton.setVisible(false);
        searchLabel.setVisible(false);
        searchField.setVisible(false);
        search.setVisible(false);
        EmployeeInfo.setVisible(false);
        PatientInfo.setVisible(false);

        try{

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false", "root", "Atharva");
            EmployeeCount();
            PatientCount();
        }catch(Exception e) {
            System.out.println(e);
        }

        //employee detail
        employeeDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //hide default dashboard content
                EmployeeCount.setVisible(false);
                PatientCount.setVisible(false);
                RoomCount.setVisible(false);
                image.setVisible(false);
                searchByIDButton.setVisible(false);
                searchByNameButton.setVisible(false);
                searchLabel.setVisible(false);
                searchField.setVisible(false);
                EmployeeInfo.setVisible(true);
                search.setVisible(false);
                PatientInfo.setVisible(false);

                String[] columnNames = {"ID", "Name", "Role","DutySlot"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                EmployeeInfo.setSize(900,900);

                try {
                    Statement statement = (Statement) connection.createStatement();
                    String sql = "Select * from Employee" ;
                    ResultSet rs = null;
                    rs = statement.executeQuery(sql);
                    System.out.println(rs);
                    tableModel.addRow(new Object[] { "ID", "Name", "Role", "Duty-Slot" });
                    while (rs.next()) {
                        String ID = rs.getString("ID");
                        String Lname = rs.getString("LNAme");
                        String Fname = rs.getString("FName");
                        String name = Lname+" "+Fname;
                        String role = rs.getString("Role");
                        Time slot = rs.getTime("DutySlot");

                        // create a single array of one row's worth of data
                        String[] data = { ID, name, role, String.valueOf(slot)} ;
                        System.out.println(data);
                        tableModel.addRow(data);
                    }
                    EmployeeInfo.setModel(tableModel);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }



            }
        });

        cancelrecep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.toBack();
                frame.dispose();
                LandingPage lp = new LandingPage();
                lp.frame1.setContentPane(lp.LandingPanel);
                lp.frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lp.frame1.pack();
                lp.frame1.setVisible(true);
                lp.frame1.toFront();
            }
        });

        //button to search employee
        searchEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EmployeeCount.setVisible(false);
                PatientCount.setVisible(false);
                RoomCount.setVisible(false);
                image.setVisible(false);
                EmployeeInfo.setVisible(false);
                searchByIDButton.setVisible(true);
                searchByNameButton.setVisible(true);
                search.setVisible(false);
                EmployeeInfo.setVisible(false);
                PatientInfo.setVisible(false);
                PatientInfo.setVisible(false);
                searchField.setVisible(false);
                searchLabel.setVisible(false);
                searchFlag = "Employee";
            }
        });

        //search employee by name
        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchField.setVisible(true);
                searchLabel.setVisible(true);
                searchByIDButton.setVisible(false);
                searchByNameButton.setVisible(false);
                EmployeeInfo.setVisible(false);
                PatientInfo.setVisible(false);
                search.setVisible(true);
                if(searchFlag =="Employee")
                    searchLabel.setText("Enter Employee Name");
                else
                    searchLabel.setText("Enter Patient Name");
                flag =1;
            }
        });

        //search employee by id
        searchByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                searchField.setVisible(true);
                searchLabel.setVisible(true);
                EmployeeInfo.setVisible(false);
                PatientInfo.setVisible(false);
                searchByIDButton.setVisible(false);
                searchByNameButton.setVisible(false);
                searchByIDButton.setVisible(false);
                search.setVisible(true);
                if(searchFlag =="Employee")
                    searchLabel.setText("Enter Employee ID");
                else
                    searchLabel.setText("Enter Patient ID");
                flag =2;
            }
        });

        //search by name or id
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EmployeeInfo.clearSelection();
                EmployeeInfo.setVisible(true);
                if (flag == 1 && searchFlag=="Employee")//search by name employee
                {
                    String sname = searchField.getText();
                    String[] columnNames = {"ID", "Name", "Role", "DutySlot"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                    EmployeeInfo.setSize(900, 900);

                    try {
                        statement = (Statement) connection.createStatement();
                        sql ="SELECT * from EmployeeSearch where FName =\"" + sname + "\";";
                        ResultSet rs = null;
                        rs = statement.executeQuery(sql);

                        tableModel.addRow(new Object[]{"ID", "Name", "Role", "Duty-Slot"});
                        while (rs.next()) {
                            String ID = rs.getString("ID");
                            String Lname = rs.getString("LNAme");
                            String Fname = rs.getString("FName");
                            String name = Lname + " " + Fname;
                            String role = rs.getString("Role");
                            Time slot = rs.getTime("DutySlot");

                            // create a single array of one row's worth of data
                            String[] data = {ID, name, role, String.valueOf(slot)};
                            System.out.println(data);
                            tableModel.addRow(data);
                        }
                        EmployeeInfo.setModel(tableModel);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }


                else if (flag == 2 &&searchFlag=="Employee")//search by ID employee
                {
                    String sid = searchField.getText();
                    String[] columnNames = {"ID", "Name", "Role", "DutySlot"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                    EmployeeInfo.setSize(900, 900);

                    try {
                        statement = (Statement) connection.createStatement();
                        sql ="SELECT * from EmployeeSearch where ID =\"" + sid + "\";";
                        ResultSet rs = null;
                        rs = statement.executeQuery(sql);

                        tableModel.addRow(new Object[]{"ID", "Name", "Role", "Duty-Slot"});

                        while (rs.next()) {
                            String ID = rs.getString("ID");
                            String Lname = rs.getString("LNAme");
                            String Fname = rs.getString("FName");
                            String name = Lname + " " + Fname;
                            String role = rs.getString("Role");
                            Time slot = rs.getTime("DutySlot");

                            // create a single array of one row's worth of data
                            String[] data = {ID, name, role, String.valueOf(slot)};
                            System.out.println(data);
                            tableModel.addRow(data);
                        }
                        EmployeeInfo.setModel(tableModel);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else if (flag == 1 && searchFlag=="Patient")//search by name employee
                {
                    String sname = searchField.getText();
                    String[] columnNames = {"ID", "Name", "Age","Sex","Address","Phone number","Alternate number"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                    EmployeeInfo.setSize(900,900);

                    try {
                        statement = (Statement) connection.createStatement();
                        sql ="SELECT * from PatientSearch where FirstName =\"" + sname + "\";";
                        ResultSet rs = null;
                        rs = statement.executeQuery(sql);

                        tableModel.addRow(new Object[] {"ID", "Name", "Age","Sex","Address","Phone number","Alternate number" });
                        while (rs.next()) {
                            String ID = rs.getString("ID");
                            String Lname = rs.getString("LastNAme");
                            String Fname = rs.getString("FirstName");
                            String name = Lname + " " + Fname;
                            String age = rs.getString("Age");
                            String sex = rs.getString("Sex");
                            String add = rs.getString("Address");
                            String phone1 = rs.getString("PhoneNumber1");
                            String phone2 = rs.getString("PhoneNumber2");

                            // create a single array of one row's worth of data
                            String[] data = { ID, name, age, sex, add, phone1, phone2} ;
                            System.out.println(data);
                            tableModel.addRow(data);
                        }
                        EmployeeInfo.setModel(tableModel);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }


                else if (flag == 2 &&searchFlag=="Patient")//search by ID employee
                {
                    String sid = searchField.getText();
                    String sname = searchField.getText();
                    String[] columnNames = {"ID", "Name", "Age","Sex","Address","Phone number","Alternate number"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                    EmployeeInfo.setSize(900,900);

                    try {
                        statement = (Statement) connection.createStatement();
                        sql ="SELECT * from PatientSearch where ID =\"" + sid + "\";";
                        ResultSet rs = null;
                        rs = statement.executeQuery(sql);

                        tableModel.addRow(new Object[] {"ID", "Name", "Age","Sex","Address","Phone number","Alternate number" });
                        while (rs.next()) {
                            String ID = rs.getString("ID");
                            String Lname = rs.getString("LastNAme");
                            String Fname = rs.getString("FirstName");
                            String name = Lname + " " + Fname;
                            String age = rs.getString("Age");
                            String sex = rs.getString("Sex");
                            String add = rs.getString("Address");
                            String phone1 = rs.getString("PhoneNumber1");
                            String phone2 = rs.getString("PhoneNumber2");

                            // create a single array of one row's worth of data
                            String[] data = { ID, name, age, sex,  add, phone1, phone2} ;
                            System.out.println(data);
                            tableModel.addRow(data);
                        }
                        EmployeeInfo.setModel(tableModel);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

            }
        });


        //display all patients
        patientDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //hide default dashboard content
                EmployeeCount.setVisible(false);
                PatientCount.setVisible(false);
                RoomCount.setVisible(false);
                image.setVisible(false);
                searchByIDButton.setVisible(false);
                searchByNameButton.setVisible(false);
                searchLabel.setVisible(false);
                searchField.setVisible(false);
                search.setVisible(false);
                PatientInfo.setVisible(true);
                EmployeeInfo.setVisible(false);


                //display all employee in a table
                String[] columnNames = {"ID", "Name", "Age","Sex","Address","Phone number","Alternate number"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                PatientInfo.setSize(900,900);

                try {
                    statement = (Statement) connection.createStatement();
                    sql = "Select * from Patient" ;
                    ResultSet rs = null;
                    rs = statement.executeQuery(sql);

                    tableModel.addRow(new Object[] {"ID", "Name", "Age","Sex","Address","Phone number","Alternate number" });
                    while (rs.next()) {
                        String ID = rs.getString("ID");
                        String Lname = rs.getString("LastNAme");
                        String Fname = rs.getString("FirstName");
                        String name = Lname + " " + Fname;
                        String age = rs.getString("Age");
                        String sex = rs.getString("Sex");
                        String add = rs.getString("Address");
                        String phone1 = rs.getString("PhoneNumber1");
                        String phone2 = rs.getString("PhoneNumber2");

                        // create a single array of one row's worth of data
                        String[] data = { ID, name, age, sex, add, phone1, phone2} ;
                        System.out.println(data);
                        tableModel.addRow(data);
                    }
                    PatientInfo.setModel(tableModel);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        //search patient button listener
        searchPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EmployeeCount.setVisible(false);
                PatientCount.setVisible(false);
                RoomCount.setVisible(false);
                image.setVisible(false);
                EmployeeInfo.setVisible(false);
                PatientInfo.setVisible(false);
                searchByIDButton.setVisible(true);
                searchByNameButton.setVisible(true);
                search.setVisible(false);
                searchField.setVisible(false);
                searchLabel.setVisible(false);
                searchFlag ="Patient";
            }
        });

        //listener to register new patient
        registerPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new RegisterPatient();

            }
        });
    }

    //display count of all employees on dashboard
    void EmployeeCount() throws SQLException {
        statement = (Statement) connection.createStatement();
        sql = "select Concat(Role.Designation,'s = ',Count(*)) as count from Employee JOIN Role where Role.ID='D'And Employee.Role='D' group by Employee.Role;";
        ResultSet rs = statement.executeQuery(sql);
        rs.beforeFirst();
        rs.next();
        String c= " "+rs.getString(1)+"\n";
        sql = "select Concat(Role.Designation,'s = ',Count(*)) as count from Employee JOIN Role where Role.ID='A'And Employee.Role='A' group by Employee.Role;";
        rs = statement.executeQuery(sql);
        rs.beforeFirst();
        rs.next();
        c+=" "+ rs.getString(1)+"\n";
        System.out.println(c);
        countDisplay.setText(c);

    }

    //display count of all patients on dashboard
    void PatientCount() throws SQLException {
        statement = (Statement) connection.createStatement();
        sql = "select Concat('Patients =', Count(*)) from Patient";
        ResultSet rs = statement.executeQuery(sql);
        rs.beforeFirst();
        rs.next();
        String c= " "+rs.getString(1)+"\n";

        System.out.println(c);
        patientCount.setText(c);

    }



}