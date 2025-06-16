/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simplelibrarysystem;

import javax.swing.SwingUtilities;

/**
 *
 * @author Donut
 */
public class SimpleLibrarySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            LoginMenu mainFrame = new LoginMenu();
            mainFrame.setVisible(true);
        });
    }

}
