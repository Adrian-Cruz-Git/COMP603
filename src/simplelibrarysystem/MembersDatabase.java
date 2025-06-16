/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Donut
 */
public class MembersDatabase {

    private final Connection conn;

    public MembersDatabase() {
        this.conn = DBManager.getInstance().getConnection();
    }

    public void addMember(Member member) {
        String sql = "INSERT INTO MEMBERS (NAME, EMAIL, PHONENUMBER) VALUES(?, ?, ?)";
        try {
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, member.getName());
            pS.setString(2, member.getEmail());
            pS.setString(3, member.getPhonenumber());
            pS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error with adding member: " + ex);
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        return members;
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM MEMBERS WHERE ID = ?";
        try {
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, id);
            pS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("failed to delete member: " + ex);
        }
    }

    public void updateMember(Member member) {
        String sql = "UPDATE MEMBERS SET NAME = ?, EMAIL = ?, PHONENUMBER = ? WHERE ID = ?";
        try {
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, member.getName());
            pS.setString(2, member.getEmail());
            pS.setString(3, member.getPhonenumber());
            pS.setInt(4, member.getId());
        } catch (SQLException ex) {
            System.out.println("Error with updating member: " + ex);
        }
    }
}
