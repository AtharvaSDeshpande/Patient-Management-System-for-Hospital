import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddHistory extends JFrame {
    JTextField PatientID,Details,Medicines,Disease,DoctorID;
    JComboBox Admit;
    JButton Apply, clearButton, exitButton;
    Boolean[] theBox= {true,false};
    Connection connection;
    Timestamp timestamp;

    public AddHistory()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
        }catch(Exception e) {
            System.out.println(e);
        }

        this.setTitle("PATIENT HISTORY");
        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.cyan);
        panel1.setLayout(new GridBagLayout());
        addItem(panel1, new JLabel("PATIENT ID: "),
                0, 0, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("DISEASE:"),
                3, 0, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("MEDICINES:"),
                0, 1, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("DETAILS:"),
                0, 2, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("ADMIT:"),
                0, 3, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("DOCTOR ID:"),
                2, 3, 1, 1, GridBagConstraints.EAST);

//        addItem(panel1, new JLabel("Phone Number:"),
//                0, 6, 1, 1, GridBagConstraints.EAST);
//
//        addItem(panel1, new JLabel("Alternate number:"),
//                3, 6, 1, 1, GridBagConstraints.EAST);



        PatientID = new JTextField(20);
        Disease = new JTextField(20);
        Medicines = new JTextField(20);
        Details = new JTextField(20);
        Admit = new JComboBox(theBox);
        DoctorID= new JTextField(20);



        addItem(panel1, PatientID, 1, 0, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, Disease, 4, 0, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, Medicines, 1, 1, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, DoctorID, 3, 3, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, Admit, 1, 3, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1,Details, 2,2,2,1,
                GridBagConstraints.WEST);

//        addItem(panel1, phone1, 1,6,2,1,
//                GridBagConstraints.WEST);
//        addItem(panel1, phone2, 4,6,2,1,
//                GridBagConstraints.WEST);



        Box buttonBox = Box.createHorizontalBox();
        Apply = new JButton("Apply");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        buttonBox.add(Apply);
        buttonBox.add(Box.createHorizontalStrut(25));
        //buttonBox.add(clearButton);
        //clearButton.addActionListener(this);
        buttonBox.add(Box.createHorizontalStrut(25));
        buttonBox.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel1();
            }
        });
        addItem(panel1, buttonBox, 0, 7, 3, 1, GridBagConstraints.CENTER);



        Apply.setText("Apply");
        Apply.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.out.println("hello");

                        try {
                            //Statement stat =connection.createStatement();

//                            String SQL="Insert into Patient Values(ID,FirstName,RegisterTime,LastName,Age,Sex,Address,PhoneNumber1,PhoneNumber2);";
//                            PreparedStatement preparedStatement=(connection.prepareStatement(SQL));
//                            preparedStatement.executeUpdate();

                            CallableStatement stat=connection.prepareCall("{call InsertPatientHistory(?,?,?,?,?,?,?)}");
                            String temp = PatientID.getText();
                            stat.setString(1,temp);
                            System.out.println(temp);

                            temp= Details.getText();
                            stat.setString(2, temp);
                            System.out.println(temp);

                            temp= Medicines.getText();
                            stat.setString(3, temp);
                            System.out.println(temp);
//                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                            stat.setTimestamp(4,(timestamp));

                            Boolean temp1 = (Boolean) Admit.getSelectedItem();
                            System.out.println(temp1);
                            stat.setBoolean(4, temp1);

                            temp= Disease.getText();
                            stat.setString(5, temp);
                            System.out.println(temp);

                            temp= DoctorID.getText();
                            stat.setString(6, temp);
                            System.out.println(temp);

                            timestamp = new Timestamp(System.currentTimeMillis());
                            stat.setString(7, String.valueOf(timestamp));

                            stat.execute();
                            connection.close();
                            stat.close();

//                            Statement stmt = connection.createStatement();
//
//                            String sql = "INSERT INTO Patient VALUES ('Pt101', 'Zara','2018-07-21 13:0:0', 'Ali' ,18,'F','Sahakar nagar pune','9389402793','4892749300')";
//                            stmt.executeUpdate(sql);
                            System.out.println("Your data has been inserted into table.");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                        JTextArea textarea=new JTextArea();
                        JScrollPane scroll=new JScrollPane( textarea,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                        JFrame frame= new JFrame();
                        frame.setBackground(Color.pink);

                        if (e.getSource()==Apply){
                            String name= PatientID.getText();
                            textarea.append("\n\n");
                            textarea.append("    PATIENT ID: "+name+"\n\n");

                            String name1= Disease.getText();
                            textarea.append("    DISEASE: "+name1+"\n\n");

                            String name3 =  Medicines.getText();
                            textarea.append("    MEDICINES: "+ name3+"\n\n");
                            Boolean enteredText = (Boolean) Admit.getSelectedItem();
                            textarea.append("    ADMIT: "+enteredText+"\n\n");

                            String aget=Details.getText();
                            textarea.append("    DETAILS:"+aget+"\n\n");

                            String phone=DoctorID.getText();
                            textarea.append("    DOCTOR ID: "+phone+"\n\n");

                            textarea.append("    TIMESTAMP: "+timestamp+"\n\n");


                            textarea.append("\n\n\t\t\tThankyou wait for your turn your data has been registered");

                            textarea.setEditable(false);
                        }
                        frame.add(scroll);
                        frame.setTitle("    DETAILS OF REGISTERED PATIENT");
                        frame.setSize(500,500);
                        frame.setLocation(350,50);
                        frame.setVisible(true);

                    }
                });

        this.add(panel1);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent evnt){
        if(evnt.getSource()==exitButton){
            System.exit(0);
        }
        else if (evnt.getSource()==clearButton){
            cancel1();
        }
    }

    public void cancel1() {
        // pName.setText("");

        this.dispose();

    }

    private void addItem(JPanel p, JComponent c,
                         int x, int y, int width, int height,
                         int align)
    {
        GridBagConstraints gc =
                new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(20, 20, 20, 20);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }
}