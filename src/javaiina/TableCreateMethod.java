
/* CreateDatabaseTable.java */

package javaiina;

import java.sql.*;

public class TableCreateMethod{    
    private TableCreateMethod() throws SQLException, ClassNotFoundException{
        if(!(tableExist("Member"))) createMemberTable();
        if(!(tableExist("Rental"))) createRentalTable();
        if(!(tableExist("Reservation"))) createReservationTable();
        if(!(tableExist("RentalObject"))) createRentalObjectTable();
        if(!(tableExist("AvailableSizeInfo"))) createAvailableSizeInfoTable();
        if(!(tableExist("SizeInfo"))) createSizeInfoTable();
    }    
    
    private void createMemberTable() throws SQLException,ClassNotFoundException{
        String memberTable = 
            "create table Member ("
            + "memberId bigint primary key,"
            + "firstName varchar(128),"
            + "secondName varchar(128),"
            + "firstName varchar(256),"
            + "secondName varchar(256),"
            + "nickName varchar(128),"
            + "birthDate date,"
            + "registerDate date,"
            + "gender varchar(64),"
            + "address varchar(512),"
            + "postCode varchar(128)"
            + "phoneNumber varchar(128)," 
            + "emailAddress varchar(128),"
            + ")";        
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(memberTable);
        stmt.close();
    }

    private void createRentalTable() throws SQLException, ClassNotFoundException{
        String rentalObjectTable = 
            "create table RentalObject ("
            + "rentalId bigint primary key,"
            + "memberId bigint,"
            + "rentalObjectId bigint,"
            + "sizeId int,"
            + "beginDate date,"
            + "desiredReturnDate date,"
            + "actualReturnDate date," 
            + "overduePayment int"
            + ")";        
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(rentalObjectTable);
        stmt.close();
    }

    private void createReservationTable() throws SQLException, ClassNotFoundException{
        String reservationTable =
            "create table Reservation ("
            + "reservationId bigint primary key"
            + "memberId bigint,"
            + "rentalId bigint,"
            + "reservationDate date,"
            + "sizeId int,"
            + "done bit,"
            + ")";       
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(reservationTable);
        stmt.close();
    }
    
    private void createRentalObjectTable() throws SQLException, ClassNotFoundException{
        String rentalTable =
            "create table RentalObject ("
            + "rentalObjectId int primary key,"
            + "rentalObjectName varchar(256),"
            + "categoryName varchar(256),"
            + "cost int"
            + ")";
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(rentalTable);
        stmt.close();
    }

    private void createSizeInfoTable() throws SQLException, ClassNotFoundException{
        String sizeInfoTable =
            "create table SizeInfo ("
            + "sizeId int primary key,"
            + "sizeName varchar(64),"
            + "height int,"
            + "weight int,"
            + "waistMin int,"
            + "waistMax int,"
            + "chestMeasure int,"
            + "shoulderWidth int,"
            + "lenSleeve int,"
            + "inseam int"
            + ")";        
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sizeInfoTable);
        stmt.close();
    }
    
    private void createAvailableSizeInfoTable() throws SQLException, ClassNotFoundException{
        String availableSizeInfoTable =
            "create table AvailableSizeInfo("
            + "rentalObjectId bigint primary key,"
            + "sizeId int"
            + ")";        
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(availableSizeInfoTable);
        stmt.close();
   }
    
   public boolean tableExist (String tableName) throws SQLException, ClassNotFoundException{
       boolean tExists = false;
       Connection conn = DatabaseAccess.getInstance().getConnection();
       try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)){
           while(rs.next()) {
               String tName = rs.getString("TABLE_NAME");
               if (tName != null && tName.equals(tableName)){
                   tExists = true;
                   break;
               }
           }
       }
       return tExists;
   }
}
