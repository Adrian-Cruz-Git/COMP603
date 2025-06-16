/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simplelibrarysystem.DatabaseAccess;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simplelibrarysystem.model.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Donut
 */
public class MembersDatabaseTest {
    
    private static MembersDatabase memberDatabase;
    
    public MembersDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Setting up MemberDatabaseTest class...");
        memberDatabase = new MembersDatabase();
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Tearing down MemberDatabaseTest class...");
        DBManager.getInstance().shutdown();
    }
    
    @Before
    public void setUp() {
        try {
            Connection conn = DBManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM MEMBERS");
        } catch (SQLException e) {
            fail("Test setup failed: Could not clear MEMBERS table. " + e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMember method, of class MembersDatabase.
     * Test of getAllMembers method, of class MembersDatabase.
     */
    @Test
    public void testAddMemberAndGetAllMembers() throws SQLException {
        // 1. Verify initial state
        assertTrue("Database should be empty before adding a member.", memberDatabase.getAllMembers().isEmpty());

        // 2. Add a new member
        Member newMember = new Member("John Doe", "john.doe@example.com", "555-1234");
        try {
            memberDatabase.addMember(newMember);
        } catch (SQLException e) {
            fail("Adding a valid member should not throw an exception: " + e.getMessage());
        }

        // 3. Verify the member was added correctly
        List<Member> members = memberDatabase.getAllMembers();
        assertEquals("There should be one member in the database after adding.", 1, members.size());
        assertEquals("name should match.", "John Doe", members.get(0).getName());
    }

    /**
     * Test of deleteMember method, of class MembersDatabase.
     */
    @Test
    public void testDeleteMember() throws SQLException {
        // 1. Add a member to be deleted
        Member memberToDelete = new Member("Delete Me", "delete@me.com", "000");
        memberDatabase.addMember(memberToDelete);
        assertFalse("Member list should not be empty before deletion.", memberDatabase.getAllMembers().isEmpty());
        int memberId = memberDatabase.getAllMembers().get(0).getId();

        // 2. Delete the member
        memberDatabase.deleteMember(memberId);

        // 3. Verify the member is gone
        assertTrue("Member list should be empty after deletion.", memberDatabase.getAllMembers().isEmpty());
    }

    /**
     * Test of updateMember method, of class MembersDatabase.
     */
    @Test
    public void testUpdateMember() throws SQLException {
        // 1. Add a member to be updated
        Member originalMember = new Member("Jane Smith", "jane.s@example.com", "555-5678");
        memberDatabase.addMember(originalMember);
        int memberId = memberDatabase.getAllMembers().get(0).getId();

        // 2. Create the updated member object and perform the update
        Member updatedMember = new Member("Jane Jones", "jane.j@example.com", "555-9999");
        updatedMember.setId(memberId);
        memberDatabase.updateMember(updatedMember);

        // 3. Retrieve and verify the changes
        Member retrievedMember = memberDatabase.getAllMembers().get(0);
        assertEquals("The member's name should be updated.", "Jane Jones", retrievedMember.getName());
        assertEquals("The member's email should be updated.", "jane.j@example.com", retrievedMember.getEmail());
    }
    
     @Test(expected = SQLException.class)
    public void testAddMemberWithDuplicateEmailShouldThrowException() throws SQLException {
        // 1. Add the first member
        Member member1 = new Member("First User", "unique.email@example.com", "111-1111");
        memberDatabase.addMember(member1);

        // 2. Attempt to add a second member with the same email
        Member member2 = new Member("Second User", "unique.email@example.com", "222-2222");

        // This line is expected to throw a SQLException due to the UNIQUE constraint.
        // The test will pass if and only if the exception is thrown.
        memberDatabase.addMember(member2);
    }
}
