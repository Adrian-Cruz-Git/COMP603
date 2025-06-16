/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Donut
 */
public class AddButtonListener implements ActionListener {

    private final BooksMenu booksMenu;
    private final BookDatabase bookDatabase;

    public AddButtonListener(BooksMenu booksMenu, BookDatabase bookDatabase) {
        this.booksMenu = booksMenu;
        this.bookDatabase = bookDatabase;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = booksMenu.getTitleField().getText();
        String author = booksMenu.getAuthorField().getText();
        String barcode = booksMenu.getbarcodeField().getText();

        if (title.trim().isEmpty() || author.trim().isEmpty()) {
            booksMenu.showMessage("Title and Author areas cannot be left empty", "Info Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            bookDatabase.addBook(new Book(title, author, barcode));
            booksMenu.showMessage("book added", "Success", JOptionPane.INFORMATION_MESSAGE);
            booksMenu.refreshBookTable();
            booksMenu.clearFormFields();
        } catch (Exception ex) {
            booksMenu.showMessage("Failed to add book from database" + ex.getMessage(), "database error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
