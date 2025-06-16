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
public final class MembersMenu extends JPanel {
    
    private final MembersDatabase membersDatabase;
    
    private JTable memberTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, emailField, phoneField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    
    private int selectedMemberId = -1;
    
    
    public MembersMenu(){
        this.membersDatabase = new MembersDatabase();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        createMemberMenuComponents();
        createMemberLayout();
        createMemberListeners();
        
        refreshMemberTable();
    }
    
    private void createMemberMenuComponents(){
        String[] columnNames = {"ID", "Name", "Email", "Phonenumber"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        
        memberTable = new JTable(tableModel);
        memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        
        addButton = new JButton("Add Member");
        updateButton = new JButton("Update Member");
        deleteButton = new JButton("Delete Member");
        clearButton = new JButton("Clear Form");
    }
    
    private void createMemberLayout(){
        add(new JScrollPane(memberTable), BorderLayout.CENTER);
        
        add(createBottomPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        bottomPanel.add(createInfoPanel());
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        bottomPanel.add(createButtonPanel());

        return bottomPanel;
    }
    
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Member Details"));

        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(nameField);
        infoPanel.add(new JLabel("Email:"));
        infoPanel.add(emailField);
        infoPanel.add(new JLabel("Phone:"));
        infoPanel.add(phoneField);

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
    
    private void createMemberListeners(){
        addButton.addActionListener(new addMemberListener(this, membersDatabase));
        updateButton.addActionListener(new updateMemberListener(this, membersDatabase));
        deleteButton.addActionListener(new deleteMemberListener(this, membersDatabase));
        clearButton.addActionListener(new clearFormFieldsListener(this, membersDatabase));

        // Listener for table row selection
        memberTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleTableSelection();
            }
        });
    }
    
    public void refreshMemberTable() {
        List<Member> members = membersDatabase.getAllMembers();
        tableModel.setRowCount(0); // Clear existing data
        for (Member member : members) {
            tableModel.addRow(new Object[]{member.getId(), member.getName(), member.getEmail(), member.getPhonenumber()});
        }
    }
}
