package operation;

import model.Book;

import java.sql.*;
import java.util.*;
public class BookOperations {

    public boolean addBook(Connection con,Book bk) throws SQLException {
        String sql = "INSERT INTO books VALUES(?,?,?,?,?,?)";


        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bk.getBookId());
            pst.setString(2,bk.getBookName());
            pst.setString(3,bk.getAuthorName());
            pst.setString(4,bk.getPublisher());
            pst.setDouble(5,bk.getPrice());
            pst.setInt(6,bk.getQuantity());

            int rows = pst.executeUpdate() ;
            return rows > 0;

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public List<Book> displayBooks(Connection con) throws SQLException{
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try{
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthorName(rs.getString("AuthorName"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPrice(rs.getDouble("Price"));
                book.setQuantity(rs.getInt("Quantity"));

                bookList.add(book);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return bookList;
    };

     public Book searchBook(Connection con, int bookId) throws SQLException{

         try{
             String sql = "SELECT * FROM books WHERE bookId =?";
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1, bookId);
             ResultSet rs = pst.executeQuery();
             while(rs.next()){
                 Book book = new Book();
                 book.setBookId(rs.getInt("BookId"));
                 book.setBookName(rs.getString("BookName"));
                 book.setAuthorName(rs.getString("AuthorName"));
                 book.setPublisher(rs.getString("Publisher"));
                 book.setPrice(rs.getDouble("Price"));
                 book.setQuantity(rs.getInt("Quantity"));
                 return book;
             }
         }catch (Exception e){
             System.out.println(e);
         }
         return null;
     };

    public boolean updateBook(Connection con,Book book) throws SQLException{
        try{
            String sql = "UPDATE books SET price = ?, quantity = ? WHERE BookId =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1, book.getPrice());
            pst.setInt(2, book.getQuantity());
            pst.setInt(3, book.getBookId());

            int rows = pst.executeUpdate();
            return rows > 0;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    };

    public boolean deleteBook(Connection con, int bookId) throws SQLException{
        String sql = "DELETE FROM books WHERE bookId = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    };
}
