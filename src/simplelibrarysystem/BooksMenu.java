/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Donut
 */
public final class BooksMenu extends JPanel {

    // components
    private final BookDatabase bookDatabase;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, authorField, barcodeField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private int selectedBookId = -1;

    public BooksMenu() {
        this.bookDatabase = new BookDatabase();
        setLayout(new BorderLayout(10, 10));

        createComponents();
        createLayout();
        createListeners();
        refreshBookTable();
    }

    private void createComponents() {
        String[] columnNames = {"ID", "Title", "Author", "Barcode"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        barcodeField = new JTextField(20);
        addButton = new JButton("Add Book");
        updateButton = new JButton("Update Book");
        deleteButton = new JButton("Delete Book");
        clearButton = new JButton("Clear Form");
    }

    private void createLayout() {
        setLayout(new BorderLayout(10, 10));

        // this is the center panel
        this.add(new JScrollPane(bookTable), BorderLayout.CENTER);

        this.add(createBottomPanel(), BorderLayout.SOUTH);

    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        bottomPanel.add(createInfoPanel());

        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        bottomPanel.add(createButtonPanel());
        return bottomPanel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        infoPanel.add(new JLabel("Title:"));
        infoPanel.add(titleField);
        infoPanel.add(new JLabel("Author:"));
        infoPanel.add(authorField);
        infoPanel.add(new JLabel("Barcode:"));
        infoPanel.add(barcodeField);

        return infoPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        return buttonPanel;
    }

    private void createListeners() {
        addButton.addActionListener(new AddButtonListener(this, bookDatabase));
        updateButton.addActionListener(new updateButtonListener(this, bookDatabase));
        deleteButton.addActionListener(new deleteButtonListener(this, bookDatabase));
        clearButton.addActionListener(new clearButtonListener(this));
        bookTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleTableSelection();
            }
        });
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public JTextField getbarcodeField() {
        return barcodeField;
    }

    public int getSelectedBookId() {
        return selectedBookId;
    }

    private void handleTableSelection() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedBookId = (int) tableModel.getValueAt(selectedRow, 0);
            titleField.setText((String) tableModel.getValueAt(selectedRow, 1));
            authorField.setText((String) tableModel.getValueAt(selectedRow, 2));
            barcodeField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }

    public void refreshBookTable() {
        // reloads the books within the frame
        List<Book> books = bookDatabase.getAllBooks();
        tableModel.setRowCount(0);
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getBarcode()});
        }
    }

    public void clearFormFields() {
        // clears the text boxs specifically used to clear text boxes after an attempt to add a new book
        titleField.setText("");
        authorField.setText("");
        barcodeField.setText("");
        bookTable.clearSelection();
        selectedBookId = -1;
    }

    public void showMessage(String message, String title, int messageType) {
        // shows popup for the messages
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}
