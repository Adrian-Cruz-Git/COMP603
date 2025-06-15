/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Donut
 */
public class updateButtonListener implements ActionListener {
    private final BooksMenu booksMenu;
    private final BookDatabase bookDatabase;

    public updateButtonListener(BooksMenu booksMenu, BookDatabase bookDatabase) {
        this.booksMenu = booksMenu;
        this.bookDatabase = bookDatabase;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int bookId = booksMenu.getSelectedBookId();
        if (bookId == -1){
            booksMenu.showMessage("Please select a book to delete.", "no selection made", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String title = booksMenu.getTitleField().getText();
        String author = booksMenu.getAuthorField().getText();
        String barcode = booksMenu.getbarcodeField().getText();
        
        try {
                bookDatabase.updateBook(new Book(bookId, title, author, barcode));
                booksMenu.showMessage("book successfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                booksMenu.refreshBookTable();
                booksMenu.clearFormFields();
            } catch (SQLException ex){
                booksMenu.showMessage("Failed to update book from database" + ex.getMessage(), "database error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
}
