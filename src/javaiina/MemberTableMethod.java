
/* MemberTableMethod.java */

package javaiina;

import java.text.SimpleDateFormat;

public class MemberTableMethod extends DatabaseAccess{
    
    public MemberTableMethod() throws SQLException, ClassNotFoundException{
        super();
    }
    
    public void memberInsert(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate("insert into MemberDataTest values("
            + m.id() + ","
            + "'" + m.firstName() + "',"
            + "'" + m.secondName() + "',"
            + "'" + m.firstNameKana() + "',"
            + "'" + m.secondNameKana() + "',"
            + "'" + m.nickName() + "',"
            + m.birthDate() + ","
            + m.registerDate() + ","
            + m.gender() + ","
            + "'" + m.phoneNumber() + "',"
            + "'" + m.emailAddress() + "'," +
            ")"
        );
        mStmt.close();
    }

    public void memberDelete(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "delete from Member where memberId = " + m.id());
        mStmt.close();
    }
    
    public void memberUpdate(String column, String updateData, String condition) {
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member "
             + "set " + column + " = " + updateData + " "
             + condition
        );
        mStmt.close();
    }
    
    public void memberUpdate(String column, Date updateData, String condition) {
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member "
            + "set " + column + " = " + updateData + " "
            + condition
        );
        mStmt.close();
    }
    
    public void memberUpdate(String column, long updateData, String condition) {
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member " 
            + "set " + column + " = " + updateData + " " 
            + condition
        );
    }
    
    public void memberUpdateFirstName(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member "
            + "set firstName = " + "'" + m.firstName() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }
    
    public void memberUpdateSecondName(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member "
            + "set secondName = " + "'" + m.secontName() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }
    
    public void memberUpdateFirstNameKana(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member"
            +" set firstNameKana = " + "'" + m.firstNameKana() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }
    
    public void memberUpdateSecondNameKana(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member"
            +" set secondNameKana = " + "'" + m.secondNameKana() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }
    
    public void memberUpdateNickName(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member"
            + "set nickName = " + "'" + m.nickName() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }
    
    public void memberUpdatePhoneNumber(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member"
            +" set phoneNumber = " + "'" + m.phoneNumber() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }
    
    public void memberUpdateEmailAddress(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Member"
            +" set emailAddress = " + "'" + m.emailAddress() + "' " 
            + "where memberId = " + m.id()
        ); 
        mStmt.close();
    }

    public ArrayList<ArrayList<String>> memberSelectAll(Member m) throws SQLException{
        ArrayList<ArrayList<String>> resultListList = new ArrayList<ArrayList<String>>();
        ArrayList<String> resultList = new ArrayList<String>();
        mStmt = mConnToDatabase.createStatement();
        ResultSet rs = mStmt.executeQuery("select * from MemberDataTest where mId = " + m.id());
        while(rs.next()) {
            resultList.add(valueOf(rs.getInt("memberId")));
            resultList.add(rs.getString("firstName"));
            resultList.add(rs.getString("secontName"));
            resultList.add(rs.getString("firstNameKana"));
            resultList.add(rs.getString("secontNameKana"));
            resultList.add(rs.getString("nickName"));
            resultList.add(SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(rs.getDate("birthDate")));
            resultList.add(SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(rs.getDate("registerDate")));
            resultList.add(rs.getString("gender"));
            resultList.add(rs.getString("phoneNumber"));
            resultList.add(rs.getString("emailAddress"));
            resultListList.add(resultList);
            removeAll(resultList);
        }
        rs.close();
        mStmt.close();
        return resultListList;
    }
    
}
