package operation;

import java.sql.*;

public class DisplayOperation {
    public void displayIssuedBooks(Connection con) throws SQLException {

        String sql = "SELECT * FROM issues WHERE status='Issued'";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            System.out.println(
                    rs.getInt("issueId")+"  "+
                            rs.getInt("bookId")+"  "+
                            rs.getInt("memberId")+"  "+
                            rs.getDate("issueDate")+"  "+
                            rs.getString("status")
            );
        }
    }
    public void displayReturnedBooks(Connection con) throws SQLException {

        String sql = "SELECT * FROM issues WHERE status='Returned'";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            System.out.println(
                    rs.getInt("issueId")+"  "+
                            rs.getInt("bookId")+"  "+
                            rs.getInt("memberId")+"  "+
                            rs.getDate("returnDate")+"  "+
                            rs.getString("status")
            );
        }
    }
    public void displayAvailableBooks(Connection con) throws SQLException {

        String sql = "SELECT * FROM books WHERE quantity>0";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            System.out.println(
                    rs.getInt("bookId")+"  "+
                            rs.getString("bookName")+"  "+
                            rs.getInt("quantity")
            );
        }
    }
}
