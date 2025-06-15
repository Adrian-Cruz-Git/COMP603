/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
