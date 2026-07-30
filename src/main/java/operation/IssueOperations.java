package operation;

import model.Issue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueOperations {

    // Add Issue
    public boolean addIssue(Connection con, Issue issue) throws SQLException {

        String sql = "INSERT INTO issues(issueId, bookId, memberId, issueDate, returnDate, status) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, issue.getIssueId());
            pst.setInt(2, issue.getBookId());
            pst.setInt(3, issue.getMemberId());
            pst.setDate(4, issue.getIssueDate());
            pst.setDate(5, issue.getReturnDate());
            pst.setString(6, issue.getStatus());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    // Display Issues
    public List<Issue> displayIssues(Connection con) throws SQLException {

        List<Issue> issueList = new ArrayList<>();

        String sql = "SELECT * FROM issues";

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Issue issue = new Issue();

                issue.setIssueId(rs.getInt("issueId"));
                issue.setBookId(rs.getInt("bookId"));
                issue.setMemberId(rs.getInt("memberId"));
                issue.setIssueDate(rs.getDate("issueDate"));
                issue.setReturnDate(rs.getDate("returnDate"));
                issue.setStatus(rs.getString("status"));

                issueList.add(issue);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return issueList;
    }

    // Search Issue
    public Issue searchIssue(Connection con, int issueId) throws SQLException {

        String sql = "SELECT * FROM issues WHERE issueId=?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, issueId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Issue issue = new Issue();

                issue.setIssueId(rs.getInt("issueId"));
                issue.setBookId(rs.getInt("bookId"));
                issue.setMemberId(rs.getInt("memberId"));
                issue.setIssueDate(rs.getDate("issueDate"));
                issue.setReturnDate(rs.getDate("returnDate"));
                issue.setStatus(rs.getString("status"));

                return issue;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    // Update Issue
    public boolean updateIssue(Connection con, Issue issue) throws SQLException {

        String sql = "UPDATE issues SET issueDate=? WHERE issueId=?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDate(1, issue.getIssueDate());
            pst.setInt(2, issue.getIssueId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    // Delete Issue
    public boolean deleteIssue(Connection con, int issueId) throws SQLException {

        String sql = "DELETE FROM issues WHERE issueId=?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, issueId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }
}