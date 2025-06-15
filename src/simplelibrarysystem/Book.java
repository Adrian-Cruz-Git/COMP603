/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

/**
 *
 * @author Donut
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String barcode;
    
    public Book(int id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.barcode = isbn;
    }

    public Book(String title, String author, String barcode) {
        this.title = title;
        this.author = author;
        this.barcode = barcode;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
