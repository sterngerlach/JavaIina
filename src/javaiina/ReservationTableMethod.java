
/* ReservationTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ReservationTableMethod{
    private ReservationTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        // test data
        if(tableExist("Reservation"))
            stmt.executeUpdate("insert into Reservation values" 
                + "(0, 0, 0, '2017-07-017', 0, 1)"
                + ",(1, 1, 1, '2017-07-017', 0, 1)"
                + ",(2, 2, 2, '2017-07-017', 0, 1)"
              );
        stmt.close();
    }
    
    public void reservationInsert(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Reservation values("
            + res.id() + ","
            + res.member().id() + ","
            + res.rentalObject().id() + ","
            + "'" + res.reservationDate() + "',"
            + res.sizeInfo().id() + ","
            + res.isDone() + ","
              + ")"
         );
        stmt.close();
    }

    public void reservationDelete(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Reservation where reservationId = " + res.id());
        stmt.close();
    }
    
    public void reservationUpdate(String column, String updateDta, String condition) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + "'" + updateData + "' "
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdate(String column, Date updateData, String condition) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + updateData + " "
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            +"set " + column + " = " + updateData + " " 
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdateMember(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set memberId = " + res.member().id() + " " 
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set rentalObjectId = " + res.rentalObject().id() + " "  
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateSizeInfo(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set sizeInfo = " + res.sizeInfo().id() + " "
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateReservationDate(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set reservationDate = " + res.reservationDate() + " "
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateDone(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public List<Reservation> reservationSelectAll(Reservation res) throws SQLException{
        List<Reservation> resultList = new ArrayList<Reservation>();
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Reservation where reservationId = " + res.id());
        while(rs.next()) {
            resultList.add(new Reservation(
                rs.getLong("reservationId")),
                rs.getLong("memberId"),
                rs.getLong("rentalObjectId"),
                rs.getDate("reservationDate"),
                rs.getDate("sizeId"),
                rs.getBoolean("done")
            );
        }
        rs.close();
        stmt.close();
        return resultList;
    }
}
