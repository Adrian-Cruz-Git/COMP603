/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class BooksMenu extends JFrame {

    // components
    private final BookDatabase bookDatabase;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, authorField, barcodeField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private int selectedBookId = -1;

    public BooksMenu() {
        this.bookDatabase = new BookDatabase();

        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        createComponents();
        createLayout();
        createListeners();
    }

    private void createComponents() {
        String[] columnNames = {"ID", "Title", "Author", "ISBN"};
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
        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        // for the bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // fillout information panel
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        
        infoPanel.add(new JLabel("Title:"));
        infoPanel.add(titleField);
        infoPanel.add(new JLabel("Author:"));
        infoPanel.add(authorField);
        infoPanel.add(new JLabel("Barcode:"));
        infoPanel.add(barcodeField);

        //buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        
        //  putting panels together
        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createListeners() {
        addButton.addActionListener(new AddButtonListener(this, bookDatabase));
        updateButton.addActionListener(new updateButtonListener(this, bookDatabase));
        deleteButton.addActionListener(new deleteButtonListener(this, bookDatabase));
        clearButton.addActionListener(new clearButtonListener(this, bookDatabase));
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
}
