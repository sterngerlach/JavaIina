package javaiina;

public class SizeInfoTableMethod {
    private SizeInfoTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        // test data
        if(tableExist("SizeInfo"))
            stmt.executeUpdate("insert into SizeInfo values" 
                + "(0, 'A1', 150, 49, 68, 78, 86, 42, 53, 66)"
                + ",(1, 'A2', 155, 53, 70, 80, 88, 42, 54 69)"
                + ",(2, 'A3', 160, 57, 72, 83, 92, 43, 56, 71)"
            );
        stmt.close();
    }
    
    public void sizeInfoInsert(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into SizeInfo values("
            + si.id() + ","
            + "'" + si.sizeName() + "',"
            + si.height()
            + si.weight()
            + si.waistMin()
            + si.waistMax()
            + si.chestWidth()
            + si.shoulderWidth()
            + si.sleeveLength()
            + si.inseamLength()
            + ")"
        );
        stmt.close();
    }

    public void sizeInfoDelete(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from SizeInfo where sizeId = " + si.id());
        stmt.close();
    }
    
    public void sizeInfoUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set " + column + " = " + updateData + " " 
            + condition
        );
        stmt.close();
    }
    
    public void sizeInfoUpdateSizeName(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set sizeName = '" + si.sizeName() + "' " 
            + "where sizeId = " + si.id()
        );
        stmt.close();
    }
    
    public void sizeInfoUpdateHeight(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set height = " + si.height() + " " 
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateWeight(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set weight = " + si.weight() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateWaistMin(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set waistMin = " + si.waistMin() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateWaistMin(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set waistMax = " + si.waistMax() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateChestWidth(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set ChestWidth = " + si.chestWidth() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateShoulderWidth(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set shoulderWidth = " + si.shoulderWidth() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateSleeveLength(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set sleeveLength = " + si.sleeveLength() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
    
    public void sizeInfoUpdateInseamLength(RentalObjectSizeInfo si) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update SizeInfo "
            + "set inseamLength = " + si.inseamLength() + " "  
            + "where sizeId = " + si.id()
        ); 
        stmt.close();
    }
}
