/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        return new String[]{"ID", "Title", "Author", "ISBN"};
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
        return bookDatabase.getAllBooks();
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
        Book book = new Book(titleField.getText(), authorField.getText(), barcodeField.getText());
        bookDatabase.addBook(book);
        refreshTable();
        clearFormFields();
    }

    @Override
    protected void performUpdate() {
        if (selectedItemId == -1) return;
        Book book = new Book(titleField.getText(), authorField.getText(), barcodeField.getText());
        book.setId(selectedItemId);
        bookDatabase.updateBook(book);
        refreshTable();
        clearFormFields();
    }

    @Override
    protected void performDelete() {
        if (selectedItemId == -1) return;
        bookDatabase.deleteBook(selectedItemId);
        refreshTable();
        clearFormFields();
    }
}
