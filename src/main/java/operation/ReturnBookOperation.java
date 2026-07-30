package operation;

import java.sql.*;

public class ReturnBookOperation {

    public boolean returnBook(Connection con, int issueId) throws SQLException {

        try {

            // Get Book ID
            String sql1 = "SELECT bookId FROM issues WHERE issueId=? AND status='Issued'";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, issueId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                return false;
            }

            int bookId = rs.getInt("bookId");

            // Update issue status
            String sql2 = "UPDATE issues SET status='Returned', returnDate=CURDATE() WHERE issueId=?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, issueId);
            ps2.executeUpdate();

            // Increase quantity
            String sql3 = "UPDATE books SET quantity = quantity + 1 WHERE bookId=?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setInt(1, bookId);

            return ps3.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}