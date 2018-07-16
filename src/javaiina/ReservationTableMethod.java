
/* ReservationTableMethod.java */

package javaiina;

public class ReservationTableMethod extends DatabaseAccess{
    public ReservationTableMethod() throws SQLException, ClassNotFoundException{
        super();
    }
    
    public void reservationInsert(Member m, long reservationId) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate("insert into MemberDataTest values("
            + reservationId +
            + m.id() + ","
            + "'" + rentalObject() + "',"
            + reservationDate() +","
            + ")"
        );
        mStmt.close();
    }

    public void reservationDelete(Member m) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate(
            "delete from Reservation where reservationId = " + m.id());
        mStmt.close();
    }
    
}
