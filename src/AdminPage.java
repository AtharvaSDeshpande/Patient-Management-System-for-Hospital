import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Random;

public class AdminPage {
    public JPanel AdminPanel ;
    private JTextField FirstNameTextField;
    private JTextField lastNameTextField;
    private JTextField ageTextField;
    private JTextField IDEmailTextField;
    private JPasswordField passwordPasswordField;
    private JTextField roleIDTextField;
    private JTextField departmentIDForDoctorTextField;
    private JTextField salaryTextField;
    private JPanel InsertPannel;
    private JPanel SearchUpdatePannel;
    private JButton insertButton;
    private JTextField enterIDTextField;
    private JButton SEARCHButton;
    private JTextField FNameTextField;
    private JTextField LNameTextField;
    private JTextField ageTextField1;
    private JTextField IDTextField;
    private JTextField salaryTextField1;
    private JTextField roleTextField;
    private JTextField dutySlot;
    private JLabel Duty;

    JFrame frame5 = new JFrame("WELCOME ADMIN");
    Connection connection;
    String SQL;
    Statement statement;
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

        return "EMP" + res.toString();
    }
    PreparedStatement insertToDatabasePreparedStatement(PreparedStatement preparedStatement) throws IOException, ClassNotFoundException,SQLException  {
        preparedStatement.setString(1,createUID());

        preparedStatement.setString(2,roleIDTextField.getText());
        preparedStatement.setString(3,FirstNameTextField.getText());
        preparedStatement.setString(4,lastNameTextField.getText());
        Integer A = Integer.parseInt(ageTextField.getText());
        System.out.println(A);
        preparedStatement.setInt(5,A);

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        preparedStatement.setDate(6,date);
        return preparedStatement;
    }
    AdminPage() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?autoReconnect=true&useSSL=false",
                    "root", "Atharva");
        }catch(Exception e)
        {
            System.out.println(e);
        }
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQL = "select ID,Role,FName,LName,DutySlot,Age from employee where employee.ID = \"" + enterIDTextField.getText() + "\"";
                try {
                    statement =connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(SQL);
                    if (resultSet.next())
                    {
                        FNameTextField.setVisible(true);
                        LNameTextField.setVisible(true);
                        ageTextField1.setVisible(true);
                        IDTextField.setVisible(true);
                        roleTextField.setVisible(true);
                        FNameTextField.setText(resultSet.getString(3));
                        LNameTextField.setText(resultSet.getString(4));
                        ageTextField1.setText(resultSet.getInt(6) + "");
                        IDTextField.setText(resultSet.getString(1));
                        dutySlot.setText(resultSet.getString(5));
                        roleTextField.setText(resultSet.getString(2));
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQL = "insert into Employee(Id,Role,FName,LName,Age,StartDate) values (?,?,?,?,?,?)";
                try {
                    PreparedStatement insertIntoEmployeeTable =  insertToDatabasePreparedStatement(connection.prepareStatement(SQL));
                    insertIntoEmployeeTable.executeUpdate();

                } catch (IOException S) {
                    S.printStackTrace();

                    String errorMsg = "SQL Database Error!!  "  + S;
                    System.out.println(errorMsg);
                } catch (ClassNotFoundException S) {
                    S.printStackTrace();

                    String errorMsg = "SQL Database Error!!  "  + S;
                    System.out.println(errorMsg);
                } catch (SQLException S) {
                    S.printStackTrace();

                    String errorMsg = "SQL Database Error!!  "  + S;
                    System.out.println(errorMsg);
                }


            }
        });

    }



}
