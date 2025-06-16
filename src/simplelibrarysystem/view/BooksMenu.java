/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem.view;

import simplelibrarysystem.model.Book;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import simplelibrarysystem.DatabaseAccess.BookDatabase;

/**
 *
 * @author Donut
 */
public final class BooksMenu extends AbstractManagementPanel<Book> {

    // components
    private final BookDatabase bookDatabase;
    private JTextField titleField, authorField, barcodeField;

    public BooksMenu() {
        super(); // Calls the constructor of AbstractManagementPanel
        this.bookDatabase = new BookDatabase();
        // Set button text specific to this panel
        addButton.setText("Add Book");
        updateButton.setText("Update Book");
        deleteButton.setText("Delete Book");
        refreshTable(); // Refresh with the correct database
    }

    @Override
    protected String[] getColumnNames() {
        return new String[]{"ID", "Title", "Author", "Barcode"};
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        // Initialize specific fields
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        barcodeField = new JTextField(20);

        infoPanel.add(new JLabel("Title:"));
        infoPanel.add(titleField);
        infoPanel.add(new JLabel("Author:"));
        infoPanel.add(authorField);
        infoPanel.add(new JLabel("Barcode:"));
        infoPanel.add(barcodeField);

        return infoPanel;
    }

    @Override
    protected Object[] convertItemToRow(Book book) {
        return new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getBarcode()};
    }

    @Override
    protected List<Book> getAllItemsFromDatabase() {
        try {
            return bookDatabase.getAllBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BooksMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void populateFormWithSelectedItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            titleField.setText((String) tableModel.getValueAt(selectedRow, 1));
            authorField.setText((String) tableModel.getValueAt(selectedRow, 2));
            barcodeField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }

    @Override
    protected void clearFormFields() {
        titleField.setText("");
        authorField.setText("");
        barcodeField.setText("");
        resetSelection(); // Call common method from base class
    }

    @Override
    protected void performAdd() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String barcode = barcodeField.getText().trim();

        String errorMessage = validateBookData(title, author, barcode);

        if (errorMessage != null) {
            JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Book book = new Book(title, author, barcode);
                bookDatabase.addBook(book);
                refreshTable();
                clearFormFields();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error adding book: A book with this Barcode already exists.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String validateBookData(String title, String author, String barcode) {
        if (title.isEmpty()) {
            return "Book title cannot be empty.";
        }
        if (author.isEmpty()) {
            return "Author name cannot be empty.";
        }
        if (barcode.isEmpty()) {
            return "Barcode cannot be empty.";
        }
        if (!barcode.matches("^[0-9-]+$")) {
            return "Barcode can only contain numbers and dashes.";
        }
        return null; // A null return value indicates success (no error message)
    }

    @Override
    protected void performUpdate() {
        if (selectedItemId == -1) {
            return;
        }
        Book book = new Book(titleField.getText(), authorField.getText(), barcodeField.getText());
        book.setId(selectedItemId);
        try {
            bookDatabase.updateBook(book);
        } catch (SQLException ex) {
            Logger.getLogger(BooksMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
        clearFormFields();
    }

    @Override
    protected void performDelete() {
        if (selectedItemId == -1) {
            return;
        }
        try {
            bookDatabase.deleteBook(selectedItemId);
        } catch (SQLException ex) {
            Logger.getLogger(BooksMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
        clearFormFields();
    }
}
