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
public class clearButtonListener implements ActionListener {
    private final BooksMenu booksMenu;

    public clearButtonListener(BooksMenu booksMenu) {
        this.booksMenu = booksMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.booksMenu.clearFormFields();
    }
    
}
