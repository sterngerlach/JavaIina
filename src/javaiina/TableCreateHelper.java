
/* CreateDatabaseTable.java */

package javaiina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreateHelper
{
    public TableCreateHelper() throws SQLException, ClassNotFoundException
    {
        if (!(checkTableExists("Member")))
            createMemberTable();
        
        if (!(checkTableExists("Rental")))
            createRentalTable();
        
        if (!(checkTableExists("Reservation")))
            createReservationTable();
        
        if (!(checkTableExists("RentalObject")))
            createRentalObjectTable();
        
        if (!(checkTableExists("AvailableSizeInfo")))
            createAvailableSizeInfoTable();
        
        if (!(checkTableExists("SizeInfo")))
            createSizeInfoTable();
    }
    
    private void createMemberTable() throws SQLException, ClassNotFoundException
    {
        String memberTable = 
            "create table Member ("
            + "memberId bigint primary key,"
            + "firstName varchar(128),"
            + "secondName varchar(128),"
            + "firstNameKana varchar(256),"
            + "secondNameKana varchar(256),"
            + "nickName varchar(128),"
            + "birthDate date,"
            + "registerDate date,"
            + "gender varchar(64),"
            + "address varchar(512),"
            + "postCode varchar(128)"
            + "phoneNumber varchar(128)," 
            + "emailAddress varchar(128),"
            + ")";
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(memberTable);
        stmt.close();
    }

    private void createRentalTable() throws SQLException, ClassNotFoundException
    {
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
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(rentalObjectTable);
        stmt.close();
    }

    private void createReservationTable() throws SQLException, ClassNotFoundException
    {
        String reservationTable =
            "create table Reservation ("
            + "reservationId bigint primary key"
            + "memberId bigint,"
            + "rentalId bigint,"
            + "reservationDate date,"
            + "sizeId int,"
            + "done bit,"
            + ")";
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(reservationTable);
        stmt.close();
    }
    
    private void createRentalObjectTable() throws SQLException, ClassNotFoundException
    {
        String rentalTable =
            "create table RentalObject ("
            + "rentalObjectId int primary key,"
            + "rentalObjectName varchar(256),"
            + "categoryName varchar(256),"
            + "cost int"
            + ")";
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(rentalTable);
        stmt.close();
    }

    private void createSizeInfoTable() throws SQLException, ClassNotFoundException
    {
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
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sizeInfoTable);
        stmt.close();
    }
    
    private void createAvailableSizeInfoTable() throws SQLException, ClassNotFoundException
    {
        String availableSizeInfoTable =
            "create table AvailableSizeInfo("
            + "rentalObjectId bigint primary key,"
            + "sizeId int"
            + ")";        
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(availableSizeInfoTable);
        stmt.close();
   }
    
   public boolean checkTableExists(String tableName) throws SQLException, ClassNotFoundException
   {
       Connection conn = DatabaseAccessManager.getInstance().getConnection();
       ResultSet resultSet = conn.getMetaData().getTables(null, null, tableName, null);
       
       while (resultSet.next()) {
           String name = resultSet.getString("TABLE_NAME");
           if (name != null && name.equals(tableName))
               return true;
       }
       
       return false;
   }
}
