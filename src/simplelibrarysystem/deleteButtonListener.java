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
public class deleteButtonListener implements ActionListener {
    private final BooksMenu booksMenu;
    private final BookDatabase bookDatabase;

    public deleteButtonListener(BooksMenu booksMenu, BookDatabase bookDatabase) {
        this.booksMenu = booksMenu;
        this.bookDatabase = bookDatabase;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
