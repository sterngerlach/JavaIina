package javaiina;

public class RentalTableMethod {
    public RentalTableMethod() throws SQLException, ClassNotFoundException{
        super();
    }
    
    public void rentalInsert(Rental ren) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate("insert into Rental values("
            + ren.rentalId() + ","
            + ren.getMember().id() + ","
            + ren.getRentalObject().id() + ","
            + ren.reservationDate() + ","
            + ")"
        );
        mStmt.close();
    }
}
