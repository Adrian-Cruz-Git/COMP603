/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem.view;

import simplelibrarysystem.model.Member;
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
import simplelibrarysystem.DatabaseAccess.MembersDatabase;

/**
 *
 * @author Donut
 */
public final class MembersMenu extends AbstractManagementPanel<Member> {
    
    private final MembersDatabase membersDatabase;
    private JTextField nameField, emailField, phoneField;
    
    public MembersMenu(){
         super(); // This calls the constructor of AbstractManagementPanel to build the common UI
        this.membersDatabase = new MembersDatabase();

        // Customize the button text for this specific panel
        addButton.setText("Add Member");
        updateButton.setText("Update Member");
        deleteButton.setText("Delete Member");
        
        // Load the initial data
        refreshTable();
    }
    
     @Override
    protected String[] getColumnNames() {
        return new String[]{"ID", "Name", "Email", "Phone"};
    }
    
    @Override
    protected JPanel createFormPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Member Details"));

        // Initialize the specific text fields for members
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);

        // Add the fields and their labels to the panel
        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(nameField);
        infoPanel.add(new JLabel("Email:"));
        infoPanel.add(emailField);
        infoPanel.add(new JLabel("Phone:"));
        infoPanel.add(phoneField);

        return infoPanel;
    }
    
     @Override
    protected Object[] convertItemToRow(Member member) {
        return new Object[]{member.getId(), member.getName(), member.getEmail(), member.getPhonenumber()};
    }
    
     @Override
    protected List<Member> getAllItemsFromDatabase() {
        try {
            return membersDatabase.getAllMembers();
        } catch (SQLException ex) {
            Logger.getLogger(MembersMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    protected void populateFormWithSelectedItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            nameField.setText((String) tableModel.getValueAt(selectedRow, 1));
            emailField.setText((String) tableModel.getValueAt(selectedRow, 2));
            phoneField.setText((String) tableModel.getValueAt(selectedRow, 3));
        }
    }
    
    @Override
    protected void clearFormFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        resetSelection(); // Call the common method from the base class
    }
    
    @Override
    protected void performAdd() {
        if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Email cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Member member = new Member(nameField.getText(), emailField.getText(), phoneField.getText());
        try {
            membersDatabase.addMember(member);
        } catch (SQLException ex) {
            Logger.getLogger(MembersMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
        clearFormFields();
    }
    
    @Override
    protected void performUpdate() {
        if (selectedItemId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Member member = new Member(nameField.getText(), emailField.getText(), phoneField.getText());
        member.setId(selectedItemId); // Crucial step for the WHERE clause in SQL
        try {
            membersDatabase.updateMember(member);
        } catch (SQLException ex) {
            Logger.getLogger(MembersMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshTable();
        clearFormFields();
    }
    
    @Override
    protected void performDelete() {
        if (selectedItemId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this member?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                membersDatabase.deleteMember(selectedItemId);
            } catch (SQLException ex) {
                Logger.getLogger(MembersMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            refreshTable();
            clearFormFields();
        }
    }
}
