package operation;

import model.Member;

import java.sql.*;
import java.util.*;
public class MemberOperations {

    public boolean addMember(Connection con, Member mem) throws SQLException {
        String sql = "INSERT INTO members VALUES(?,?,?,?,?)";


        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,mem.getMemberId());
            pst.setString(2,mem.getMemberName());
            pst.setString(3,mem.getEmail());
            pst.setString(4,mem.getMobile());
            pst.setString(5,mem.getAddress());

            int rows = pst.executeUpdate() ;
            return rows > 0;

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public List<Member> displayMembers(Connection con) throws SQLException{
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try{
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("memberId"));
                member.setMemberName(rs.getString("memberName"));
                member.setEmail(rs.getString("email"));
                member.setMobile(rs.getString("mobile"));
                member.setAddress(rs.getString("address"));
                memberList.add(member);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return memberList;
    };

    public Member searchMember(Connection con, int memberId) throws SQLException{

        try{
            String sql = "SELECT * FROM members WHERE memberId =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, memberId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getInt("memberId"));
                member.setMemberName(rs.getString("memberName"));
                member.setEmail(rs.getString("email"));
                member.setMobile(rs.getString("mobile"));
                member.setAddress(rs.getString("address"));
                return member;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    };

    public boolean updateMember(Connection con,Member member) throws SQLException{
        try{
            String sql = "UPDATE members SET email = ?, mobile = ?, address = ? WHERE memberId =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, member.getEmail());
            pst.setString(2, member.getMobile());
            pst.setString(3, member.getAddress());
            pst.setInt(4, member.getMemberId());

            int rows = pst.executeUpdate();
            return rows > 0;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    };

    public boolean deleteMember(Connection con, int memberId) throws SQLException{
        String sql = "DELETE FROM members WHERE memberId = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, memberId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    };
}
