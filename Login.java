package demo5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 
public class Demo5 extends JFrame implements ActionListener {
      

    JTextField userField;
    JPasswordField passField;
    JButton loginButton, showButton;

     Demo5() {

        setTitle("Login - College Canteen");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(220, 235, 255));

        JLabel title = new JLabel("Canteen Login");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(120, 20, 200, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(150, 80, 150, 25);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(150, 120, 150, 25);
        add(passField);

        showButton = new JButton("Show");
        showButton.setBounds(310, 120, 70, 25);
        add(showButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 170, 100, 30);
        add(loginButton);

        loginButton.addActionListener(this);
        showButton.addActionListener(this);

        setVisible(true);
    }
       public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showButton) {
            if (passField.getEchoChar() == 0)
                passField.setEchoChar('*');
            else
                passField.setEchoChar((char) 0);
        }

        if (e.getSource() == loginButton) {

            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                dispose();
                new Demo5();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login!");
            }
        }
    }

    
    public static void main(String[] args) {
        Demo5 d=new Demo5();
         
    }
    
}
