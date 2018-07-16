
/* CreateDatabaseTable.java */

package javaiina;

import java.sql.*;

public class CreateDatabaseTable extends DatabaseAccess{
    
    private CreateDatabaseTable() throws SQLException, ClassNotFoundException{
        super();
    }    
    
    private void createMemberTable() throws SQLException{
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
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(memberTable);
        mStmt.close();
    }

    private void createRentalTable() throws SQLException{
        String rentalObjectTable = 
            "create table RentalObject ("
            + "rentalId bigint,"
            + "memberId bigint,"
            + "rentalObject varchar(256),"
            + "sizeId int,"
            + "beginDate date,"
            + "desiredReturnDate date,"
            + "actualReturnDate date," 
            + "overduePayment int"
            + ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(rentalObjectTable);
        mStmt.close();
    }

    private void createReservationTable() throws SQLException{
        String reservationTable =
            "create table Reservation ("
            + "reservationId bigint"
            + "memberId bigint,"
            + "rentalId bigint,"
            + "reservationDate date,"
            + "sizeId int,"
            + "done bit,"
            + ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(reservationTable);
        mStmt.close();	
    }
    
    private void createRentalObjectTable() throws SQLException{
        String rentalTable =
            "create table RentalObject ("
            + "rentalObjectId int,"
            + "rentalObjectName varchar(256),"
            + "categoryName varchar(256),"
            + "cost int"
            + ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(rentalTable);
        mStmt.close();
    }

    private void createSizeInfoTable() throws SQLException{
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
        
        mStmt = mConnToDatabase.createStatement();    
        mStmt.executeQuery(sizeInfoTable);
        mStmt.close();
    }
    
    private void createAvailableSizeInfoTable() throws SQLException{
        String availableSizeInfoTable =
            "create table AvailableSizeInfo("
            + "rentalObjectId bigint,"
            + "sizeId int"
            +")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(availableSizeInfoTable);
        mStmt.close();
    }
}
