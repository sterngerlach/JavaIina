
/* MemberTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberTable
{    
    public MemberTable() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();

        // Add test data
        if (TableCreateHelper.checkTableExists("Member"))
            stmt.executeUpdate(
                "insert into Member values" +
                    "(0, '大樹', '鈴木', 'ひろき', 'すずき', 'すーさん', '1997-09-12', '2016-11-07', " +
                    "'MALE', '東京都世田谷区池尻1111-11', '154-0001', '000-0000-0000', 'aaa@aaa', 'aaa')" +
                    ",(1, '遙香', '佐藤', 'はるか', 'さとう', 'はるちゃん', '1990-05-22', '2016-12-03', " +
                    "'FEMALE', '東京都世田谷区池尻1111-12', '154-0001', '001-0000-0000', 'bbb@bbb', 'bbb')" +
                    ",(2, '太郎', '山田', 'たろう', 'やま', 'やまちゃん', '1997-09-12', '2016-11-07', " +
                    "'MALE', '青森県三戸郡南部町22-33', 174-0101', '002-0000-0000', 'ccc@ccc', 'ccc')");
        
        stmt.close();
    }

    public void insert(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "insert into Member values(" +
                m.id() + "," +
                "'" + m.firstName() + "'," +
                "'" + m.secondName() + "'," +
                "'" + m.firstNameKana() + "'," +
                "'" + m.secondNameKana() + "'," +
                "'" + m.nickName() + "'," +
                "'" + m.birthDate().toString() + "'," +
                "'" + m.registerDate().toString() + "'," +
                "'" + m.gender().toString() + "'," +
                "'" + m.phoneNumber() + "'," +
                "'" + m.emailAddress() + "'" + ")");
        stmt.close();
    }

    public void delete(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("delete from Member where memberId = " + m.id());
        stmt.close();
    }

    public void update(String columnName, String newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set " + columnName + " = '" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void update(String columnName, LocalDate newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set " + columnName + " = '" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void update(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set " + columnName + " = '" + newValue + "' " + 
                "where " + conditionWhere);
        stmt.close();
    }

    public void updateFirstName(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set firstName = '" + m.firstName() + "' " +
                "where memberId = " + m.id()); 
        stmt.close();
    }

    public void updateSecondName(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set secondName = '" + m.secondName() + "' " +
                "where memberId = " + m.id());
        stmt.close();
    }

    public void updateFirstNameKana(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set firstNameKana = '" + m.firstNameKana() + "' " +
                "where memberId = " + m.id());
        stmt.close();
    }

    public void updateSecondNameKana(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set secondNameKana = '" + m.secondNameKana() + "' " +
                "where memberId = " + m.id());
        stmt.close();
    }

    public void updateNickName(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set nickName = '" + m.nickName() + "' " +
                "where memberId = " + m.id());
        stmt.close();
    }

    public void updatePhoneNumber(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set phoneNumber = '" + m.phoneNumber() + "' " +
                "where memberId = " + m.id());
        stmt.close();
    }

    public void updateEmailAddress(Member m) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Member " +
                "set emailAddress = '" + m.emailAddress() + "' " +
                "where memberId = " + m.id());
        stmt.close();
    }

    public List<Member> select() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        List<Member> resultList = new ArrayList<Member>();
        ResultSet resultSet = stmt.executeQuery("select * from Member");
        
        while (resultSet.next()) { 
            String gender = resultSet.getString("gender");
            Gender genderValue = gender.equals("Male") ? Gender.MALE :
                gender.equals("Female") ? Gender.FEMALE : Gender.UNSPECIFIED;
            
            resultList.add(new Member(
                resultSet.getLong("memberId"),
                resultSet.getString("firstName"),
                resultSet.getString("secontName"),
                resultSet.getString("firstNameKana"),
                resultSet.getString("secontNameKana"),
                resultSet.getString("nickName"),
                resultSet.getDate("birthDate").toLocalDate(),
                resultSet.getDate("registerDate").toLocalDate(),
                genderValue,
                resultSet.getString("address"),
                resultSet.getString("postCode"),
                resultSet.getString("phoneNumber"),
                resultSet.getString("emailAddress"),
                resultSet.getString("password")));
        }
        
        resultSet.close();
        stmt.close();
        
        return resultList;
    }  
}
