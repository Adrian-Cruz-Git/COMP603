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
            ResultSet rs = statment.executeQuery("SELECT * FROM BOOKS");
            while(rs.next()){
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String barcode = rs.getString("BARCODE");
                
                Book book = new Book(id,title, author, barcode);
                books.add(book);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return books;
    }
    
    public void updateBook(Book book) throws SQLException{
        String sql = "update BOOKS set TITLE = ?, AUTHOR = ?, BARCODE = ? where ID = ?";
        try{
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, book.getTitle());
            pS.setString(2, book.getAuthor());
            pS.setString(3, book.getBarcode());
            pS.setInt(4, book.getId());
            pS.executeUpdate();
        } catch (SQLException ex){
            throw ex;
        }
    }
    
    public void deleteBook(int bookId) throws SQLException{
        String sql = "DELETE FROM BOOKS WHERE ID = ?";
        try{
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, bookId);
            pS.executeUpdate();
        } catch (SQLException ex){
            throw ex;
        }
    }
}
