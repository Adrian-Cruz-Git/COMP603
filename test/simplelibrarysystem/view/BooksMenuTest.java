/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simplelibrarysystem.view;

import java.util.List;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simplelibrarysystem.model.Book;

/**
 *
 * @author Donut
 */
public class BooksMenuTest {
    
    public BooksMenuTest() {
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
     * Test of getColumnNames method, of class BooksMenu.
     */
    @Test
    public void testGetColumnNames() {
        System.out.println("getColumnNames");
        BooksMenu instance = new BooksMenu();
        String[] expResult = null;
        String[] result = instance.getColumnNames();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFormPanel method, of class BooksMenu.
     */
    @Test
    public void testCreateFormPanel() {
        System.out.println("createFormPanel");
        BooksMenu instance = new BooksMenu();
        JPanel expResult = null;
        JPanel result = instance.createFormPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertItemToRow method, of class BooksMenu.
     */
    @Test
    public void testConvertItemToRow() {
        System.out.println("convertItemToRow");
        Book book = null;
        BooksMenu instance = new BooksMenu();
        Object[] expResult = null;
        Object[] result = instance.convertItemToRow(book);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItemsFromDatabase method, of class BooksMenu.
     */
    @Test
    public void testGetAllItemsFromDatabase() {
        System.out.println("getAllItemsFromDatabase");
        BooksMenu instance = new BooksMenu();
        List<Book> expResult = null;
        List<Book> result = instance.getAllItemsFromDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateFormWithSelectedItem method, of class BooksMenu.
     */
    @Test
    public void testPopulateFormWithSelectedItem() {
        System.out.println("populateFormWithSelectedItem");
        BooksMenu instance = new BooksMenu();
        instance.populateFormWithSelectedItem();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearFormFields method, of class BooksMenu.
     */
    @Test
    public void testClearFormFields() {
        System.out.println("clearFormFields");
        BooksMenu instance = new BooksMenu();
        instance.clearFormFields();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performAdd method, of class BooksMenu.
     */
    @Test
    public void testPerformAdd() {
        System.out.println("performAdd");
        BooksMenu instance = new BooksMenu();
        instance.performAdd();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performUpdate method, of class BooksMenu.
     */
    @Test
    public void testPerformUpdate() {
        System.out.println("performUpdate");
        BooksMenu instance = new BooksMenu();
        instance.performUpdate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performDelete method, of class BooksMenu.
     */
    @Test
    public void testPerformDelete() {
        System.out.println("performDelete");
        BooksMenu instance = new BooksMenu();
        instance.performDelete();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
