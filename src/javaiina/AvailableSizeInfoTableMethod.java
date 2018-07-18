
/* AvailableSizeInfoTableMethod.java*/

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class AvailableSizeInfoTableMethod {    
    private AvailableSizeInfoTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        // test data
        if(tableExist("AvailableSizeInfo"))
            stmt.executeUpdate("insert into AvailableSizeInfo values" 
                + "(0, 0)"
                + ",(0, 1)"
                + ",(0, 2)"
                + ",(1, 0)"
                + ",(1, 1)"
                + ",(1, 2)"
                + ",(2, 0)"
                + ",(2, 1)"
                + ",(2, 2)"
              );
        stmt.close();
    }
    
    public void reservationInsert(AvailableSize aSize) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into AvailableSizeInfo values("
            + aSize.getRentalObject().id() + ","
            + aSize.getSizeInfo().id() + ","
              + ")"
         );
        stmt.close();
    }

    public void reservationDeleteByRentalObject(AvailableSize aSize) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from AvailableSizeInfo where rentalObjectId = " + aSize.getRentalObject().id());
        stmt.close();
    }
    
    public void reservationDeleteBySizeInfo(AvailableSize aSize) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from AvailableSizeInfo where sizeId = " + aSize.getSizeInfo().id());
        stmt.close();
    }
    
    public void availableSizeInfoUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update AvailableSizeInfo "
            + "set " + column + " = " + updateData + " " 
            + condition
         );
        stmt.close();
    }
    
    public void availableSizeInfoUpdateSizeId(AvailableSize aSize) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update AvailableSizeInfo "
            + "set sizeId = " + aSize.getSizeInfo().id() + " " 
            + "where rentalObjectId = " + aSize.getRentalObject().id()
         ); 
        stmt.close();
    }
    
    public void availableSizeInfoUpdateRentalObjectId(AvailableSize aSize) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update AvailableSizeInfo "
            + "set rentalObjectId = " + aSize.getRentalObject().id() + " "  
            + "where sizeId = " + aSize.getSizeInfo().id()
         ); 
        stmt.close();
    }
    
    public List<AvailableSize> availableSizeSelectAll(AvailableSize aSize) throws SQLException{
        List<AvailableSize> resultList = new ArrayList<AvailableSize>();
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from AvailableSize where rentalObjectId = " + aSize.getRentalObject().id());
        while(rs.next()) {
            resultList.add(new AvailableSize(
                rs.getLong("rentalObjectId")),
                rs.getLong("sizeId")
            );
        }
        rs.close();
        stmt.close();
        return resultList;
    }
}
