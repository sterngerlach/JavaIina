
/* MemberTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MemberTableMethod{
    
    public MemberTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        if(tableExist("Member"))
            stmt.executeUpdate("insert into Member values" 
                + ("0, '', '', '', '', ''")
            );
    }
    
    public void memberInsert(Member m) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Member values("
            + m.id() + ","
            + "'" + m.firstName() + "',"
            + "'" + m.secondName() + "',"
            + "'" + m.firstNameKana() + "',"
            + "'" + m.secondNameKana() + "',"
            + "'" + m.nickName() + "',"
            + m.birthDate() + ","
            + m.registerDate() + ","
            + "'" + m.gender().toString() + "',"
            + "'" + m.phoneNumber() + "',"
            + "'" + m.emailAddress() + "'," +
            ")"
        );
        stmt.close();
    }

    public void memberDelete(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "delete from Member where memberId = " + m.id());
        stmt.close();
    }
    
    public void memberUpdate(String column, String updateData, String condition) {
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member "
             + "set " + column + " = " + updateData + " "
             + condition
        );
        stmt.close();
    }
    
    public void memberUpdate(String column, Date updateData, String condition) {
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member "
            + "set " + column + " = " + updateData + " "
            + condition
        );
        stmt.close();
    }
    
    public void memberUpdate(String column, long updateData, String condition) {
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member " 
            + "set " + column + " = " + updateData + " " 
            + condition
        );
        stmt.close();
    }
    
    public void memberUpdateFirstName(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member "
            + "set firstName = " + "'" + m.firstName() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }
    
    public void memberUpdateSecondName(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member "
            + "set secondName = " + "'" + m.secondName() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }
    
    public void memberUpdateFirstNameKana(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member"
            +" set firstNameKana = " + "'" + m.firstNameKana() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }
    
    public void memberUpdateSecondNameKana(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member"
            +" set secondNameKana = " + "'" + m.secondNameKana() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }
    
    public void memberUpdateNickName(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member"
            + "set nickName = " + "'" + m.nickName() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }
    
    public void memberUpdatePhoneNumber(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member"
            +" set phoneNumber = " + "'" + m.phoneNumber() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }
    
    public void memberUpdateEmailAddress(Member m) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Member"
            +" set emailAddress = " + "'" + m.emailAddress() + "' " 
            + "where memberId = " + m.id()
        ); 
        stmt.close();
    }

    public List<Member> memberSelectAll(Member m) throws SQLException{
        List<Member> resultList = new ArrayList<Member>();
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from MemberDataTest where mId = " + m.id());
        while(rs.next()) {
            resultList.add(Member.member(String.valueOf(rs.getInt("memberId")),
            rs.getString("firstName"),
            rs.getString("secontName"),
            rs.getString("firstNameKana"),
            rs.getString("secontNameKana"),
            rs.getString("nickName"),
            rs.getDate("birthDate").toLocalDate(),
            rs.getDate("registerDate").toLocalDate(),
            rs.getString("gender"),
            rs.getString("phoneNumber"),
            rs.getString("emailAddress")));
        }
        rs.close();
        stmt.close();
        return resultList;
    }
    
}
