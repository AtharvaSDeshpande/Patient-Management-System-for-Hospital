import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class LandingPage extends JFrame {
    private JButton administratorButton;
    private JButton receptionistButton;
    private JButton doctorButton;
    public JPanel LandingPanel;
    JFrame frame1 = new JFrame("MyForm");

    LandingPage()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String SQL;

            Connection connection;
            connection = DriverManager.getConnection(mysql.getUrl(), mysql.getUser(), mysql.getPassword());
        }catch(Exception e) {
            System.out.println(e);
        }


        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    loadLogin("Doctor");
                    frame1.dispose();
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });

        receptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    loadLogin("Reception");
                    frame1.dispose();
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });
        administratorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    loadLogin("Admin");
                    frame1.dispose();
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });

    }

    void loadLogin(String role){
        Login log1 = new Login(role);
        log1.frame.setContentPane(log1.panel1);
        log1.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log1.frame.pack();
        log1.frame.setVisible(true);

    }

    public static void main(String[] args) {
        LandingPage lp = new LandingPage();
        lp.frame1.setContentPane(lp.LandingPanel);
        lp.frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lp.frame1.pack();
        lp.frame1.setVisible(true);
    }

}