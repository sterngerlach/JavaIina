
/* ReservationTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ReservationTable
{
    public ReservationTable() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        // Add test data
        if (TableCreateHelper.checkTableExists("Reservation"))
            stmt.executeUpdate(
                "insert into Reservation values" +
                    "(0, 0, 0, '2017-07-17', 0, 1)" +
                    ",(1, 1, 1, '2017-07-17', 0, 1)" +
                    ",(2, 2, 2, '2017-07-17', 0, 1)");
        
        stmt.close();
    }
    
    public void reservationInsert(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "insert into Reservation values(" +
                res.id() + "," +
                res.member().id() + "," +
                res.rentalObject().id() + "," +
                "'" + res.reservationDate().toString() + "'," +
                res.sizeInfo().id() + "," +
                Boolean.toString(res.isDone()) + ")");
        stmt.close();
    }

    public void reservationDelete(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("delete from Reservation where reservationId = " + res.id());
        stmt.close();
    }
    
    public void reservationUpdate(String columnName, String newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set " + columnName + " = " + "'" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }
    
    public void reservationUpdate(String columnName, Date newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set " + columnName + " = " + newValue + " " +
                "where " + conditionWhere);
        stmt.close();
    }
    
    public void reservationUpdate(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set " + columnName + " = " + newValue + " "  +
                "where " + conditionWhere);
        stmt.close();
    }
    
    public void reservationUpdateMember(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set memberId = " + res.member().id() + " " +
                "where reservationId = " + res.id()); 
        stmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set rentalObjectId = " + res.rentalObject().id() + " " +
                "where reservationId = " + res.id());
        stmt.close();
    }
    
    public void reservationUpdateSizeInfo(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set sizeInfo = " + res.sizeInfo().id() + " " +
                "where reservationId = " + res.id());
        stmt.close();
    }
    
    public void reservationUpdateReservationDate(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set reservationDate = " + res.reservationDate() + " " +
                "where reservationId = " + res.id());
        stmt.close();
    }
    
    public void reservationUpdateDone(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set done = " + res.isDone() + " " +
                "where reservationId = " + res.id()); 
        stmt.close();
    }
    
    public List<Reservation> reservationSelectAll(
        List<Member> members,
        List<RentalObject> rentalObjects,
        List<RentalObjectSizeInfo> sizeInfos) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        ResultSet resultSet = stmt.executeQuery("select * from Reservation");
        List<Reservation> resultList = new ArrayList<Reservation>();
        
        while (resultSet.next()) {
            long memberId = resultSet.getLong("memberId");
            long rentalObjectId = resultSet.getLong("rentalId");
            long sizeInfoId = resultSet.getInt("sizeId");
            
            Member foundMember = members.stream()
                .filter(member -> member.id() == memberId)
                .findFirst()
                .orElse(null);
            RentalObject foundRentalObject = rentalObjects.stream()
                .filter(rentalObject -> rentalObject.id() == rentalObjectId)
                .findFirst()
                .orElse(null);
            RentalObjectSizeInfo foundSizeInfo = sizeInfos.stream()
                .filter(sizeInfo -> sizeInfo.id() == sizeInfoId)
                .findFirst()
                .orElse(null);
            
            resultList.add(new Reservation(
                resultSet.getLong("reservationId"),
                foundMember,
                foundRentalObject,
                foundSizeInfo,
                resultSet.getDate("reservationDate").toLocalDate(),
                resultSet.getBoolean("done")));
        }
        
        resultSet.close();
        stmt.close();
        
        return resultList;
    }
}
