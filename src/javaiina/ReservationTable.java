
/* ReservationTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
    
    public void insert(Reservation res) throws SQLException
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

    public void delete(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("delete from Reservation where reservationId = " + res.id());
        stmt.close();
    }
    
    public void update(String columnName, String newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set " + columnName + " = " + "'" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }
    
    public void update(String columnName, LocalDate newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set " + columnName + " = " + newValue + " " +
                "where " + conditionWhere);
        stmt.close();
    }
    
    public void update(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set " + columnName + " = " + newValue + " "  +
                "where " + conditionWhere);
        stmt.close();
    }
    
    public void updateMember(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set memberId = " + res.member().id() + " " +
                "where reservationId = " + res.id()); 
        stmt.close();
    }
    
    public void updateRentalObject(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set rentalObjectId = " + res.rentalObject().id() + " " +
                "where reservationId = " + res.id());
        stmt.close();
    }
    
    public void updateSizeInfo(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set sizeInfo = " + res.sizeInfo().id() + " " +
                "where reservationId = " + res.id());
        stmt.close();
    }
    
    public void updateReservationDate(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set reservationDate = " + res.reservationDate() + " " +
                "where reservationId = " + res.id());
        stmt.close();
    }
    
    public void updateDone(Reservation res) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Reservation " +
                "set done = " + res.isDone() + " " +
                "where reservationId = " + res.id()); 
        stmt.close();
    }
    
    public List<Reservation> select(
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
