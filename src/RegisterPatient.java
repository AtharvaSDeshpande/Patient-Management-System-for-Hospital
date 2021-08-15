import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class RegisterPatient extends JFrame {
    JTextField fName, doBirth, hAddress,lName,age,phone1,phone2;
    JComboBox sex;
    JRadioButton medical, dentistry, optical,oPatient,admitted;
    JCheckBox died, discharged;
    JButton Apply, clearButton, exitButton;
    String[] theBox= {"M","F"};
    String UID;
    Connection connection;


    //function to create unique uid
    String createUID()
    {
        String createString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rand=new Random();
        StringBuilder res=new StringBuilder();
        //create unique id of length 6
        for (int i = 0; i < 6; i++) {
            int randIndex=rand.nextInt(createString.length());
            res.append(createString.charAt(randIndex));
        }

        return res.toString();
    }


    public static void main(String args[])
    {

    }

    public RegisterPatient()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false", "demo", "Atharva");

        }catch(Exception e) {
            System.out.println(e);
        }
        this.setTitle("PATIENT REGISTRATION FORM");
        this.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());


        addItem(panel1, new JLabel("First NAME OF PATIENT:"),
                0, 0, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("Last NAME OF PATIENT:"),
                3, 0, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("DATE OF BIRTH(yyyy-mm-dd):"),
                0, 1, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("HOME ADDRESS ( Vge / TA / District):"),
                0, 2, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("SEX:"),
                0, 3, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("Age:"),
                2, 3, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("Phone Number:"),
                0, 6, 1, 1, GridBagConstraints.EAST);

        addItem(panel1, new JLabel("Alternate number:"),
                3, 6, 1, 1, GridBagConstraints.EAST);



        fName = new JTextField(20);
        lName = new JTextField(20);
        doBirth = new JTextField(20);
        hAddress = new JTextField(20);
        phone1 = new JTextField(20);
        phone2 = new JTextField(20);
        sex = new JComboBox(theBox);
        age= new JTextField(20);


        addItem(panel1, fName, 1, 0, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, lName, 4, 0, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, doBirth, 1, 1, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, age, 3, 3, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1, sex, 1, 3, 2, 1,
                GridBagConstraints.WEST);
        addItem(panel1,hAddress, 2,2,2,1,
                GridBagConstraints.WEST);

        addItem(panel1, phone1, 1,6,2,1,
                GridBagConstraints.WEST);
        addItem(panel1, phone2, 4,6,2,1,
                GridBagConstraints.WEST);



        Box buttonBox = Box.createHorizontalBox();
        Apply = new JButton("Apply");
        exitButton = new JButton("Exit");

        buttonBox.add(Apply);
        buttonBox.add(Box.createHorizontalStrut(25));
        buttonBox.add(Box.createHorizontalStrut(25));
        buttonBox.add(exitButton);
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
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
                            CallableStatement stat=connection.prepareCall("{call InsertPatient(?,?,?,?,?,?,?,?,?)}");
                            UID = createUID();
                            stat.setString(1,UID);
                            String temp = fName.getText();
                            System.out.println(temp);
                            stat.setString(3, temp);

                            temp= lName.getText();
                            System.out.println(temp);
                            stat.setString(2, temp);
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            stat.setTimestamp(4,(timestamp));

                            temp = age.getText();
                            System.out.println(temp);
                            stat.setInt(5, Integer.parseInt(temp));
                            temp = (String)sex.getSelectedItem();
                            System.out.println(temp);
                            stat.setString(6, temp);
                            temp = hAddress.getText();
                            System.out.println(temp);
                            stat.setString(7, temp);
                            temp = phone1.getText();
                            System.out.println(temp);
                            stat.setLong(8, Integer.parseInt(temp));
                            temp = phone2.getText();
                            System.out.println(temp);
                            stat.setLong(9, Integer.parseInt(temp));

                            stat.execute();
                            connection.close();
                            stat.close();

                            System.out.println("Your data has been inserted into table.");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                        JTextArea textarea=new JTextArea();
                        JScrollPane scroll=new JScrollPane( textarea,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                        JFrame frame= new JFrame();

                        if (e.getSource()==Apply){
                            String name= fName.getText()+" "+lName.getText();
                            textarea.append("\n\nUID: "+UID+"\n\n");
                            textarea.append("    FULL NAME OF PATIENT: "+name+"\n\n");

                            String name1= doBirth.getText();
                            textarea.append("    DATE OF BIRTH: "+name1+"\n\n");

                            String name3 =  hAddress.getText();
                            textarea.append("    HOME ADDRESS (Vge / TA / District): "+ name3+"\n\n");
                            String enteredText = (String)sex.getSelectedItem();
                            textarea.append("    SEX: "+enteredText+"\n\n");

                            String aget=age.getText();
                            textarea.append("    Age:"+aget+"\n\n");

                            String phone=phone1.getText();
                            textarea.append("    Phone: "+phone+"\n\n");

                            phone=phone2.getText();
                            textarea.append("    Alternate number: "+phone+"\n\n");

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