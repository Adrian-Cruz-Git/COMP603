/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simplelibrarysystem.DatabaseAccess;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simplelibrarysystem.model.Book;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author Donut
 */
public class BookDatabaseTest {

    private static BookDatabase bookDatabase;

    public BookDatabaseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Setting up test class...");
        bookDatabase = new BookDatabase();
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Tearing down test class...");
        DBManager.getInstance().shutdown();
    }

    @Before
    public void setUp() {
        bookDatabase = new BookDatabase();
        try {
            Connection conn = DBManager.getInstance().getConnection();
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM BOOKS");
        } catch (SQLException e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addBook method, of class BookDatabase. Test of getAllBooks
     * method, of class BookDatabase.
     */
    @Test
    public void testAddBookAndGetAllBooks() throws SQLException {
        assertTrue("Database should be empty at the start.", bookDatabase.getAllBooks().isEmpty());
        Book newBook = new Book("The Pragmatic Programmer", "Andy Hunt", "978-0201616224");
        bookDatabase.addBook(newBook);
        List<Book> books = bookDatabase.getAllBooks();
        assertEquals("There should be one book in the database.", 1, books.size());
        assertEquals("The retrieved book's title should match.", "The Pragmatic Programmer", books.get(0).getTitle());
    }

    /**
     * Test of updateBook method, of class BookDatabase.
     */
    @Test
    public void testUpdateBook() throws SQLException {
        Book book = new Book("Original Title", "Original Author", "22222");
        bookDatabase.addBook(book);
        int id = bookDatabase.getAllBooks().get(0).getId();
        Book updatedBook = new Book("Updated Title", "Updated Author", "33333");
        updatedBook.setId(id);
        bookDatabase.updateBook(updatedBook);
        Book retrievedBook = bookDatabase.getAllBooks().get(0);
        assertEquals("Updated Title", retrievedBook.getTitle());
    }

    /**
     * Test of deleteBook method, of class BookDatabase.
     */
    @Test
    public void testDeleteBook() throws SQLException {
        Book book = new Book("To Be Deleted", "Author", "11111");
        bookDatabase.addBook(book);
        int idToDelete = bookDatabase.getAllBooks().get(0).getId();
        bookDatabase.deleteBook(idToDelete);
        assertTrue("Database should be empty after deletion.", bookDatabase.getAllBooks().isEmpty());
    }

    @Test(expected = SQLException.class)
    public void testAddBookWithDuplicateBarcodeShouldThrowException() throws SQLException {
        Book book1 = new Book("First Book", "Author A", "UNIQUE-123");
        bookDatabase.addBook(book1);

        Book book2 = new Book("Second Book", "Author B", "UNIQUE-123");
        // This line is expected to throw the SQLException, and the test will pass if it does.
        bookDatabase.addBook(book2);
    }
}
