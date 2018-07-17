
/* RentalObjectTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalObjectTableMethod {
    private RentalObjectTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        if(tableExist("RentalObject"))
            stmt.executeUpdate("insert into RentalObject values" 
                + "(0, 'スーツレンタル', 'スーツ', 2000)"
                + ",(1, '制服レンタル', 'スカート', 4000)"
                + ",(2, 'コスプレレンタル', 'コスプレ衣装', 10000)"
            );
        stmt.close();
    }
    
    public void rentalObjectInsert(RentalObject ro) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = mConnToDatabase.createStatement();
        stmt.executeUpdate("insert into RentalObject values("
            + ro.id() + ","
            + "'" + ro.name() + "',"
            + "'" + ro.categoryName() + "',"
            + ro.cost() + ","
            + ")"
        );
        stmt.close();
    }

    public void rentalObjectDelete(RentalObject ro) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from RentalObject where rentalObjectId = " + ro.id());
        stmt.close();
    }
    public void rentalObjectUpdate(String column, String updateTada, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update RentalObject "
            + "set " + column + " = " + "'" + updateData + "' "
            + condition
        );
        stmt.close();
    }
    
    public void rentalObjectUpdate(String column, int updateData, String condition) throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update RentalObject "
            + "set " + column + " = " + updateData + " "
            + condition
        );
        stmt.close();
    }
    
    public void rentalObjectUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update RentalObject "
            +"set " + column + " = " + updateData + " " 
            + condition
        );
        stmt.close();
    }
    
    public void rentalObjectUpdateRentalObjectName(RentalObject ro) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update RentalObject "
            + "set rentalObjectName = '" + ro.name() + "' " 
            + "where rentalObjectId = " + ro.id()
        ); 
        stmt.close();
    }
    
    public void rentalObjectUpdateCategoryName(RentalObject ro) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update RentalObject "
            + "set categoryName = " + ro.categoryName() + " "  
            + "where rentalObjectId = " + ro.id()
         ); 
        stmt.close();
    }
    
    public void rentalObjectUpdateCost(RentalObject ro) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        stmt = conn.createStatement();
        stmt.executeUpdate(
            "update RentalObject"
            +" set cost = " + ro.cost() + " "
            + "where rentalObjectId = " + ro.id()
         ); 
        stmt.close();
    }
}
