/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Donut
 */
public class BookDatabase {
    
    private final Connection conn;
    
    public BookDatabase (){
        this.conn = DBManager.getInstance().getConnection();
    }
    
    public void addBook(Book book){
        String sql = "INSERT INTO BOOKS (TITLE, AUTHOR, BARCODE) VALUES(?, ?, ?)";
        try{
            PreparedStatement infoIntoSqlStatement = conn.prepareStatement(sql);
            infoIntoSqlStatement.setString(1, book.getTitle());
            infoIntoSqlStatement.setString(2, book.getAuthor());
            infoIntoSqlStatement.setString(3, book.getBarcode());
            infoIntoSqlStatement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Error with:" + ex.getMessage());
        }
    }
    
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        try {
            Statement statment = conn.createStatement();
            ResultSet rs = conn.myQuery("select * from BOOKS");
            while(rs.next()){
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String barcode = rs.getString("BARCODE");
                
                Book book = new Book(title, author, barcode);
                books.add(book);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return books;
    }
    
    public void updateBook(){
        
    }
    
    public void deleteBook(){
        
    }
}
