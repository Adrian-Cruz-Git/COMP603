/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Donut
 */
public class LoginMenu extends JFrame {

    private JTextField username;
    private JTextField password;
    private AdminsDatabase adminsDatabase;

    public LoginMenu() {
        this.adminsDatabase = new AdminsDatabase();

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        username = new JTextField();
        add(username);

        add(new JLabel("Password:"));
        password = new JPasswordField();
        add(password);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new loginButtonListener(this, adminsDatabase));

        add(new JLabel()); // Placeholder
        add(loginButton);

    }
    
    public JTextField getUsername(){
        return username;
    }
    
    public JTextField getPassword(){
        return password;
    }
}
