
/* MemberTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberTableMethod{
    
    private MemberTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        // test data
        if(tableExist("Member"))
            stmt.executeUpdate("insert into Member values" 
                + "(0, '大樹', '鈴木', 'ひろき', 'すずき', 'すーさん', '1997-09-12', '2016-11-07', 'MALE', '東京都世田谷区池尻1111-11', '154-0001', '000-0000-0000', 'aaa@aaa')"
                + ",(1, '遙香', '佐藤', 'はるか', 'さとう', 'はるちゃん', '1990-05-22', '2016-12-03', 'FEMALE', '東京都世田谷区池尻1111-12', '154-0001', '001-0000-0000', 'bbb@bbb')"
                + ",(2, '太郎', '山田', 'たろう', 'やま', 'やまちゃん', '1997-09-12', '2016-11-07', 'MALE', '青森県三戸郡南部町22-33', 174-0101', '002-0000-0000', 'ccc@ccc')"
              );
        stmt.close();
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
            + "'" + m.birthDate() + "',"
            + "'" + m.registerDate() + "',"
            + "'" + m.gender().toString() + "',"
            + "'" + m.phoneNumber() + "',"
            + "'" + m.emailAddress() + "'" +
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
            + "'" + condition + "'"
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
        Gender g;
        String str = "";
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Member where memberId = " + m.id());
        while(rs.next()) { 
            str = rs.getString("gender");
            if (str.equals("Male")) { g = Gender.MALE;}
            else if (str.equals("Female")) { g = Gender.FEMALE;}
            else {g = Gender.UNSPECIFIED;}
            resultList.add(new Member(
                rs.getLong("memberId"),
                rs.getString("firstName"),
                rs.getString("secontName"),
                rs.getString("firstNameKana"),
                rs.getString("secontNameKana"),
                rs.getString("nickName"),
                rs.getDate("birthDate").toLocalDate(),
                rs.getDate("registerDate").toLocalDate(),
                g,
                rs.getString("address"),
                rs.getString("postCode"),
                rs.getString("phoneNumber"),
                rs.getString("emailAddress"))
            );
        }
        rs.close();
        stmt.close();
        return resultList;
    }  
}
