/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import simplelibrarysystem.view.BooksMenu;
import simplelibrarysystem.view.MembersMenu;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Donut
 */
public class MainMenu extends JFrame{
    public MainMenu(){
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JTabbedPane tabs = new JTabbedPane();
        
        BooksMenu booksMenu = new BooksMenu();
        MembersMenu membersMenu = new MembersMenu();
        
        tabs.addTab("Manage Books", booksMenu);
        tabs.addTab("Manage Members", membersMenu);
        
        add(tabs);
    }
}
