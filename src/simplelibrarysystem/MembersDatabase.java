/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Donut
 */
public class MembersDatabase {
    private Connection conn;
    
    public void addMember(Member member){
        this.conn = DBManager.getInstance().getConnection();
    }
    
    public List<Member> getAllMembers(){
        List<Member> members = new ArrayList<>();
        return members;
    }
    
    public void deleteMember(int id){
        String sql = "DELETE FROM";
        try{
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, id);
            pS.executeUpdate();
        } catch(SQLException ex){
            System.out.println("failed to delete member: " + ex);
        }
    }
    
    public void updateMember(Member member){
        
    }
}
