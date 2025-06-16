/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem.DatabaseAccess;

import simplelibrarysystem.model.Member;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Donut
 */
public class MembersDatabase {

    private final Connection conn;

    public MembersDatabase() {
        this.conn = DBManager.getInstance().getConnection();
    }

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO MEMBERS (NAME, EMAIL, PHONENUMBER) VALUES(?, ?, ?)";
        try {
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, member.getName());
            pS.setString(2, member.getEmail());
            pS.setString(3, member.getPhonenumber());
            pS.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM MEMBERS";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Member member = new Member(rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PHONENUMBER"));
                member.setId(rs.getInt("ID"));
                members.add(member);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return members;
    }

    public void deleteMember(int id) throws SQLException {
        String sql = "DELETE FROM MEMBERS WHERE ID = ?";
        try {
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, id);
            pS.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void updateMember(Member member) throws SQLException {
        String sql = "UPDATE MEMBERS SET NAME = ?, EMAIL = ?, PHONENUMBER = ? WHERE ID = ?";
        try {
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, member.getName());
            pS.setString(2, member.getEmail());
            pS.setString(3, member.getPhonenumber());
            pS.setInt(4, member.getId());
            pS.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
