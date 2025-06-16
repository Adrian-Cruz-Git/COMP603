/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Donut
 * @param <T>
 */
public abstract class AbstractManagementPanel<T> extends JPanel {
    
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected JButton addButton, updateButton, deleteButton, clearButton;
    
    protected int selectedItemId = -1;
    
    public AbstractManagementPanel() {
        // Set up the common layout for the entire panel
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the common components
        initializeTable();
        initializeButtons();

        // Create the common layout structure
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        // Add the common listeners
        attachListeners();
    }
    
    private void initializeTable() {
        tableModel = new DefaultTableModel(getColumnNames(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void initializeButtons() {
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear Form");
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        
        // The form panel is specific, so we delegate its creation to the subclass
        bottomPanel.add(createFormPanel(), BorderLayout.CENTER);
        
        // The button row is common
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return bottomPanel;
    }
    
    private void attachListeners() {
        // Button actions are delegated to abstract methods
        addButton.addActionListener(e -> performAdd());
        updateButton.addActionListener(e -> performUpdate());
        deleteButton.addActionListener(e -> performDelete());
        clearButton.addActionListener(e -> clearFormFields());

        // Table selection logic is common
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    selectedItemId = (int) tableModel.getValueAt(selectedRow, 0);
                    // Populating the form is specific, so we delegate
                    populateFormWithSelectedItem();
                }
            }
        });
    }
    
    public final void refreshTable() {
        List<T> items = getAllItemsFromDatabase();
        tableModel.setRowCount(0); // Clear table
        for (T item : items) {
            tableModel.addRow(convertItemToRow(item));
        }
    }
    
     protected void resetSelection() {
        table.clearSelection();
        selectedItemId = -1;
    }
     
    protected abstract String[] getColumnNames();
    
    protected abstract JPanel createFormPanel();
    
    protected abstract Object[] convertItemToRow(T item);
    
    protected abstract void populateFormWithSelectedItem();
    
    protected abstract List<T> getAllItemsFromDatabase();
    
    protected abstract void performAdd();
    protected abstract void performUpdate();
    protected abstract void performDelete();
    protected abstract void clearFormFields();
}
