
/* RentalTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalTableMethod {
    private RentalTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
         // test data
        if(tableExist("Rental"))
            stmt.executeUpdate("insert into RentalObject values" 
                + "(0, 0, 0, 0, '2016-11-07', '2016-11-21', '2016-11-20', 0)"
                + ",(1, 1, 1, 1, '2016-05-22', '2016-06-05', '2016-06-06', 100)"
                + ",(2, 2, 2, 2, '2016-11-07', '2016-11-21', '2016-11-15', 0)"
              );
        stmt.close();
    }
    
    public void rentalInsert(Rental ren) throws SQLException{
        mStmt = mConnToDatabase.createStatement();
        mStmt.executeUpdate("insert into Rental values("
            + ren.getId() + ","
            + ren.getMember().id() + ","
            + ren.getRentalObject().id() + ","
            + "'" + ren.getBeginDate() + "',"
            + "'" + ren.getDesiredReturnDate() + "',"
            + "'" + ren.getActualReturnDate() + "',"
            + ren.getOverduePayment()
            + ")"
        );
        mStmt.close();
    }
    
    public void rentalDelete(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Rental where rentalId = " + ren.getId());
        stmt.close();
    }
    
    public void rentalUpdate(String column, String updateData, String condition){
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set " + column + " = " + "'" + updateData + "' "
            + condition
         );
        stmt.close();
    }
    
    public void rentalUpdate(String column, Date updateData, String condition) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set " + column + " = '" + updateData + "' "
            + condition
         );
        stmt.close();
    }
    
    public void rentalUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set " + column + " = " + updateData + " " 
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdateMemberId(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set memberId = " + ren.getMember().id() + " " 
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateRentalObjectId(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set rentalObjectId = " + ren.getRentalObject().id() + " "  
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateSizeId(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set sizeId = " + ren.getSizeInfo().id() + " "
            + "where reservationId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateBeginDate(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set beginDate = " + ren.getBeginDate() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateDesiredReturnDate(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set desiredReturnDate = " + ren.getDesiredReturnDate() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateActualReturnDate(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set actualReturnDate = " + ren.getActualReturnDate() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateOverduePayment(Rental ren) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set overduePayment = " + ren.getOverduePayment() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
}
