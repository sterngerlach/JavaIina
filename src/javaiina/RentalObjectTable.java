
/* RentalObjectTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class RentalObjectTable
{
    public RentalObjectTable() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        if (TableCreateHelper.checkTableExists("RentalObject"))
            stmt.executeUpdate("insert into RentalObject values" +
                "(0, 'スーツレンタル', 'スーツ', 2000)" +
                ",(1, '制服レンタル', 'スカート', 4000)" +
                ",(2, 'コスプレレンタル', 'コスプレ衣装', 10000)");
        
        stmt.close();
    }

    public void insert(RentalObject ro) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("insert into RentalObject values(" +
            ro.id() + "," +
            "'" + ro.name() + "'," +
            "'" + ro.categoryName() + "'," +
            ro.cost() + "," + ")");
        stmt.close();
    }

    public void delete(RentalObject ro) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("delete from RentalObject where rentalObjectId = " + ro.id());
        stmt.close();
    }
    
    public void update(String columnName, String newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update RentalObject " +
                "set " + columnName + " = " + "'" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void update(String columnName, int newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update RentalObject " +
                "set " + columnName + " = " + newValue + " " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void update(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update RentalObject " +
                "set " + columnName + " = " + newValue + " " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void updateName(RentalObject ro) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update RentalObject " +
                "set rentalObjectName = '" + ro.name() + "' " +
                "where rentalObjectId = " + ro.id()); 
        stmt.close();
    }

    public void updateCategoryName(RentalObject ro) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update RentalObject " +
                "set categoryName = '" + ro.categoryName() + "' "  +
                "where rentalObjectId = " + ro.id());
        stmt.close();
    }

    public void updateCost(RentalObject ro) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update RentalObject " +
                "set cost = " + ro.cost() + " " +
                "where rentalObjectId = " + ro.id()); 
        stmt.close();
    }

    public List<RentalObject> select(
        List<AvailableSize> availableSizes,
        List<RentalObjectSizeInfo> sizeInfos) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        ResultSet resultSet = stmt.executeQuery("select * from RentalObject");
        List<RentalObject> resultList = new ArrayList<RentalObject>();
        
        while (resultSet.next()) {
            long rentalObjectId = resultSet.getLong("rentalObjectId");
            
            RentalObjectSizeInfo[] foundAvailableSizes = availableSizes.stream()
                .filter(availableSize -> availableSize.getRentalObject().id() == rentalObjectId)
                .map(availableSize -> availableSize.getSizeInfo())
                .toArray(RentalObjectSizeInfo[]::new);
                
            resultList.add(new RentalObject(
                rentalObjectId,
                resultSet.getString("rentalObjectName"),
                resultSet.getString("categoryName"),
                foundAvailableSizes,
                resultSet.getInt("cost")));
        }
        
        resultSet.close();
        stmt.close();
        
        return resultList;
    }
}
