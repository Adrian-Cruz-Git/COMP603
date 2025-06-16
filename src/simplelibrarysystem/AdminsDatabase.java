/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Donut
 */
public class AdminsDatabase {

    private final Connection conn;

    public AdminsDatabase() {
        this.conn = DBManager.getInstance().getConnection();
    }
    
    public boolean checkUsernameAndPassword(String username, String password){
        String sql = "SELECT PASSWORD FROM ADMINS WHERE USERNAME = ?";
        try{
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, username);
            
            ResultSet rs = pS.executeQuery();
            if(rs.next()){
                String databasePassword = rs.getString("PASSWORD");
                return (databasePassword.equals(password));
            }
        } catch(SQLException ex){
            System.out.println("Error with check of username and password: " + ex);
        }
        return false;
    }

}
