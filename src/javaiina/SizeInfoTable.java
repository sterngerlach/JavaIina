
/* SizeInforTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class SizeInfoTable
{
    public SizeInfoTable() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        // Add test data
        if (TableCreateHelper.checkTableExists("SizeInfo"))
            stmt.executeUpdate(
                "insert into SizeInfo values" +
                    "(0, 'A1', 150, 49, 68, 78, 86, 42, 53, 66)" +
                    ",(1, 'A2', 155, 53, 70, 80, 88, 42, 54, 69)" +
                    ",(2, 'A3', 160, 57, 72, 83, 92, 43, 56, 71)");
        
        stmt.close();
    }

    public void insert(RentalObjectSizeInfo si) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "insert into SizeInfo values(" +
                si.id() + "," +
                "'" + si.sizeName() + "', " +
                si.height() + ", " +
                si.weight() + ", " +
                si.waistMin() + ", " +
                si.waistMax() + ", " +
                si.chestWidth() + ", " +
                si.shoulderWidth() + ", " +
                si.sleeveLength() + ", " +
                si.inseamLength() + ")");
        stmt.close();
    }

    public void delete(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("delete from SizeInfo where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void update(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set " + columnName + " = " + newValue + " " + 
                "where " + conditionWhere);
        stmt.close();
    }

    public void updateSizeName(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set sizeName = '" + sizeInfo.sizeName() + "' " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateHeight(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set height = " + sizeInfo.height() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateWeight(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set weight = " + sizeInfo.weight() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateWaistMin(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set waistMin = " + sizeInfo.waistMin() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateWaistMax(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set waistMax = " + sizeInfo.waistMax() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateChestWidth(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo " +
                "set ChestWidth = " + sizeInfo.chestWidth() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateShoulderWidth(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set shoulderWidth = " + sizeInfo.shoulderWidth() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateSleeveLength(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set sleeveLength = " + sizeInfo.sleeveLength() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public void updateInseamLength(RentalObjectSizeInfo sizeInfo) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update SizeInfo " +
                "set inseamLength = " + sizeInfo.inseamLength() + " " +
                "where sizeId = " + sizeInfo.id());
        stmt.close();
    }

    public List<RentalObjectSizeInfo> select() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        ResultSet resultSet = stmt.executeQuery("select * from SizeInfo");
        List<RentalObjectSizeInfo> resultList = new ArrayList<RentalObjectSizeInfo>();
        
        while (resultSet.next()) {
            resultList.add(new RentalObjectSizeInfo(
                resultSet.getLong("sizeId"),
                resultSet.getString("sizeName"),
                resultSet.getInt("height"),
                resultSet.getInt("weight"),
                resultSet.getInt("waistMin"),
                resultSet.getInt("waistMax"),
                resultSet.getInt("chestWidth"),
                resultSet.getInt("shoulderLength"),
                resultSet.getInt("sleeveLength"),
                resultSet.getInt("inseam")));
        }
        
        resultSet.close();
        stmt.close();
        
        return resultList;
    }
}
