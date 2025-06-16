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

/**
 *
 * @author Donut
 */
public class MembersDatabaseTest {
    
    public MembersDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMember method, of class MembersDatabase.
     */
    @Test
    public void testAddMember() {
        System.out.println("addMember");
        Member member = null;
        MembersDatabase instance = new MembersDatabase();
        instance.addMember(member);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMembers method, of class MembersDatabase.
     */
    @Test
    public void testGetAllMembers() {
        System.out.println("getAllMembers");
        MembersDatabase instance = new MembersDatabase();
        List<Member> expResult = null;
        List<Member> result = instance.getAllMembers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMember method, of class MembersDatabase.
     */
    @Test
    public void testDeleteMember() {
        System.out.println("deleteMember");
        int id = 0;
        MembersDatabase instance = new MembersDatabase();
        instance.deleteMember(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMember method, of class MembersDatabase.
     */
    @Test
    public void testUpdateMember() {
        System.out.println("updateMember");
        Member member = null;
        MembersDatabase instance = new MembersDatabase();
        instance.updateMember(member);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
