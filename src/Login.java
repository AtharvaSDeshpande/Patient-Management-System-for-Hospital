import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    public JPanel panel1;
    private JTextField textField1;
    private JButton LOGINButton;
    private JPasswordField passwordField1;
    private JButton CANCELButton;
    String DuserID;

    JFrame frame = new JFrame("MyForm");

    public Login(String role) {
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                System.out.println(role);
                DuserID = textField1.getText();
                String DPassword = String.valueOf(passwordField1.getPassword());

                if (DuserID.trim().equals("")){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter your ID","Empty Username",2);
                }
                else if(DPassword.trim().equals("")){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter Password","Empty Password",2);
                }
                else {

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection cone = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
                        PreparedStatement ps = cone.prepareStatement("select * from "+role);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            if (rs.getString("ID").equals(DuserID) && rs.getString("pwd").equals(DPassword)) {
                                System.out.println(" exists");
                                frame.dispose();
                                if (role== "Doctor")
                                    doctorDash();
                                else if (role == "Reception")
                                    recepDashboard();
                                else if (role == "Admin")
                                    adminDashboard();
                                break;
                            }
                        }
                    }
                    //JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Check Your UserID Or Password","Employee Not Found",2);
                    catch (SQLException | ClassNotFoundException sql) {
                        System.out.println(sql);
                    }
                }

            }
        });
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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
    }
    void doctorDash(){
        ForDoctor doc = new ForDoctor(DuserID);
        doc.frame.setContentPane(doc.DocPanel);
        doc.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        doc.frame.pack();
        doc.frame.setVisible(true);
    }
    void recepDashboard(){
        ReceptionDashboard rec = new ReceptionDashboard();
        rec.frame.setContentPane(rec.recPanel);
        rec.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rec.frame.pack();
        rec.frame.setVisible(true);

    }
    void adminDashboard()
    {
        AdminPage adminPage = new AdminPage();
        adminPage.frame5.setContentPane(adminPage.AdminPanel);
        adminPage.frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPage.frame5.pack();
        adminPage.frame5.setVisible(true);
    }
}