/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import simplelibrarysystem.DatabaseAccess.AdminsDatabase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Donut
 */
public class loginButtonListener implements ActionListener{
    
    private final AdminsDatabase adminDatabase;
    private final LoginMenu loginMenu;
    
    public loginButtonListener(LoginMenu loginMenu, AdminsDatabase adminDatabase){
        this.adminDatabase = adminDatabase;
        this.loginMenu = loginMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = loginMenu.getUsername().getText();
        String password = loginMenu.getPassword().getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginMenu, "Username and password cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (adminDatabase.checkUsernameAndPassword(username, password)){
            loginMenu.dispose();
            SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
            
        } else {
            JOptionPane.showMessageDialog(loginMenu, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
