/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplelibrarysystem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Donut
 */
public class BookDatabase {
    
    private final Connection conn;
    
    public BookDatabase (){
        this.conn = DBManager.getInstance().getConnection();
    }
    
    public void addBook(){
        
    }
    
    public List<Book> getAllBooks(){
        List<Book> s = new ArrayList<>();
        return s;
    }
    
    public void updateBook(){
        
    }
    
    public void deleteBook(){
        
    }
}
