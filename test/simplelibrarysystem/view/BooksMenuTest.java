/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simplelibrarysystem.view;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Donut
 */
public class BooksMenuTest {
    
    private BooksMenu booksMenuPanel;
    
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
        booksMenuPanel = new BooksMenu();
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void testValidateBookData_WithValidData_ShouldReturnNull() {
        System.out.println("Testing validation with all valid data...");
        String errorMessage = booksMenuPanel.validateBookData(
            "Clean Code", 
            "Robert C. Martin", 
            "978-0132350884"
        );
        assertNull("Validation should pass (return null) for correct data.", errorMessage);
    }

    @Test
    public void testValidateBookData_WithEmptyTitle_ShouldReturnErrorMessage() {
        System.out.println("Testing validation with an empty title...");
        String errorMessage = booksMenuPanel.validateBookData(
            "", // Invalid title
            "Robert C. Martin", 
            "978-0132350884"
        );
        assertEquals("Should return a specific error for an empty title.", 
                     "Book title cannot be empty.", errorMessage);
    }

    @Test
    public void testValidateBookData_WithEmptyAuthor_ShouldReturnErrorMessage() {
        System.out.println("Testing validation with an empty author...");
        String errorMessage = booksMenuPanel.validateBookData(
            "Clean Code", 
            "", // Invalid author
            "978-0132350884"
        );
        assertEquals("Should return a specific error for an empty author.", 
                     "Author name cannot be empty.", errorMessage);
    }

    @Test
    public void testValidateBookData_WithEmptyBarcode_ShouldReturnErrorMessage() {
        System.out.println("Testing validation with an empty barcode...");
        String errorMessage = booksMenuPanel.validateBookData(
            "Clean Code", 
            "Robert C. Martin", 
            "" // Invalid barcode
        );
        assertEquals("Should return a specific error for an empty barcode.", 
                     "Barcode cannot be empty.", errorMessage);
    }
    
    @Test
    public void testValidateBookData_WithInvalidBarcodeFormat_ShouldReturnErrorMessage() {
        System.out.println("Testing validation with a barcode containing letters...");
        String errorMessage = booksMenuPanel.validateBookData(
            "Another Book", 
            "Some Author", 
            "ABC-123" // Invalid format
        );
        assertEquals("Should return an error for a barcode with invalid characters.", 
                     "Barcode can only contain numbers and dashes.", errorMessage);
    }
    
}
