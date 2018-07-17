
/* ReservationTableMethod.java */

package javaiina;

import com.sun.corba.se.pept.transport.Connection;

public class ReservationTableMethod{
    public ReservationTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
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
        Statement stmt = mConnToDatabase.createStatement();
        stmt.executeUpdate("insert into Reservation values("
            + res.id() + ","
            + res.member().id() + ","
            + res.rental.id() + ","
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
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + updateData + " "
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            +"set " + column + " = " + updateData + " " 
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdateMember(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set memberId = " + res.member().id() + " " 
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set rentalObjectId = " + res.rentalObject().id() + " "  
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateSizeInfo(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set sizeInfo = " + res.sizeInfo().id() + " "
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set reservationDate = " + res.reservationDate() + " "
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
}
