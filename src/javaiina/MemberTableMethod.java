
/* MemberTableMethod.java */

package javaiina;

import java.sql.*;
import java.text.SimpleDateFormat;

public class MemberTableMethod{
    
    public MemberTableMethod() throws SQLException, ClassNotFoundException{
        super();
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

    public ArrayList<ArrayList<String>> memberSelectAll(Member m) throws SQLException{
        ArrayList<ArrayList<String>> resultListList = new ArrayList<ArrayList<String>>();
        ArrayList<String> resultList = new ArrayList<String>();
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from MemberDataTest where mId = " + m.id());
        while(rs.next()) {
            resultList.add(String.valueOf(rs.getInt("memberId")));
            resultList.add(rs.getString("firstName"));
            resultList.add(rs.getString("secontName"));
            resultList.add(rs.getString("firstNameKana"));
            resultList.add(rs.getString("secontNameKana"));
            resultList.add(rs.getString("nickName"));
            resultList.add(rs.getDate("birthDate").toLocalDate());
            resultList.add(rs.getDate("registerDate").toLocalDate());
            resultList.add(rs.getString("gender"));
            resultList.add(rs.getString("phoneNumber"));
            resultList.add(rs.getString("emailAddress"));
            resultListList.add(resultList);
            removeAll(resultList);
        }
        rs.close();
        stmt.close();
        return resultListList;
    }
    
}
