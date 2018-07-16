
/* ReservationTableMethod.java */

package javaiina;

public class ReservationTableMethod extends DatabaseAccess{
    public ReservationTableMethod() throws SQLException, ClassNotFoundException{
        super();
    }
    
    public void reservationInsert(Reservation res) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate("insert into Reservation values("
            + res.reservationId() + ","
            + res.member().id() + ","
            + "'" + res.rentalObject() + "',"
            + res.sizeInfo() +
            + res.reservationDate() + ","
            + ")"
        );
        mStmt.close();
    }

    public void reservationDelete(Reservation res) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate("delete from Reservation where reservationId = " + res.reservationId());
        mStmt.close();
    }
    
    public void reservationUpdate(String column, String updateData, String condition) {
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + "'" + updateData + "' "
            + condition
        );
        mStmt.close();
    }
    
    public void reservationUpdate(String column, Date updateData, String condition) {
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + updateData + " "
            + condition
        );
        mStmt.close();
    }
    
    public void reservationUpdate(String column, long updateData, String condition) {
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation "
            +"set " + column + " = " + updateData + " " 
            + condition
        );
        mStmt.close();
    }
    
    public void reservationUpdateMember(Reservation res) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation"
            +" set memberId = " + "'" + res.member().id() + "' " 
            + "where reservationId = " + res.reservationId()
        ); 
        mStmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation"
            +" set rentalObjectName = " + "'" + res.rentalObject() + "' "  
            + "where reservationId = " + res.reservationId()
        ); 
        mStmt.close();
    }
    
    public void reservationUpdateSizeInfo(Reservation res) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation"
            +" set sizeInfo = " + res.sizeInfo() + " "
            + "where reservationId = " + res.reservationId()
        ); 
        mStmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "update Reservation"
            +" set reservationDate = " + res.reservationDate() + " "
            + "where reservationId = " + res.reservationId()
        ); 
        mStmt.close();
    }

}
