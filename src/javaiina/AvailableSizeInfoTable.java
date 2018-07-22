
/* AvailableSizeInfoTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class AvailableSizeInfoTable
{
    public AvailableSizeInfoTable() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();

        // Add test data
        if (TableCreateHelper.checkTableExists("AvailableSizeInfo"))
            stmt.executeUpdate(
                "insert into AvailableSizeInfo values" +
                    "(0, 0)" +
                    ",(0, 1)" + 
                    ",(0, 2)" +
                    ",(1, 0)" +
                    ",(1, 1)" +
                    ",(1, 2)" +
                    ",(2, 0)" +
                    ",(2, 1)" +
                    ",(2, 2)");
        
        stmt.close();
    }

    public void insert(AvailableSize availableSize) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "insert into AvailableSizeInfo values(" +
                availableSize.getRentalObject().id() + "," +
                availableSize.getSizeInfo().id() + ")");
        stmt.close();
    }

    public void deleteWhereRentalObject(RentalObject rentalObject) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "delete from AvailableSizeInfo " +
                "where rentalObjectId = " + rentalObject.id());
        stmt.close();
    }

    public void deleteWhereSizeInfo(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "delete from AvailableSizeInfo " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void update(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update AvailableSizeInfo " +
                "set " + columnName + " = " + newValue + " " + 
                "where " + conditionWhere);
        stmt.close();
    }
    
    public List<AvailableSize> select(
        List<RentalObject> rentalObjects, List<RentalObjectSizeInfo> sizeInfos) throws SQLException
    {   
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        ResultSet resultSet = stmt.executeQuery("select * from AvailableSizeInfo");
        List<AvailableSize> resultList = new ArrayList<AvailableSize>();
        
        while (resultSet.next()) {
            long rentalObjectId = resultSet.getLong("rentalObjectId");
            long sizeId = resultSet.getLong("sizeId");
            
            RentalObject foundRentalObject = rentalObjects.stream()
                .filter(rentalObject -> rentalObject.id() == rentalObjectId)
                .findFirst()
                .orElse(null);
            RentalObjectSizeInfo foundSizeInfo = sizeInfos.stream()
                .filter(sizeInfo -> sizeInfo.id() == sizeId)
                .findFirst()
                .orElse(null);
            
            resultList.add(new AvailableSize(foundRentalObject, foundSizeInfo));
        }
        
        resultSet.close();
        stmt.close();
        
        return resultList;
    }
}
