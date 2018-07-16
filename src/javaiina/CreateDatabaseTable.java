
/* CreateDatabaseTable.java */

package javaiina;

import java.sql.*;

public class CreateDatabaseTable extends DatabaseAccess{
    
    private CreateDatabaseTable() throws SQLException, ClassNotFoundException{
        super();
    }    
    
    private void createMemberTable() throws SQLException{
        String memberTable = 
            "create table Member (" +
            "memberId bigint primary key," +
            "firstName varchar(128)," +
            "secondName varchar(128)," +
            "nickName varchar(128)," +
            "birthData datetime," +
            "registerData datetime," +
            "gender varchar(64)," +
            "phoneNumber varchar(128)," +
            "emailAddress varchar(128)," +
            ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(memberTable);
        mStmt.close();
    }

    private void createRentalObjectTable() throws SQLException{
        String rentalObjectTable = 
            "create table RentalObject (" +
            "memberId bigint primary key," +
            "rentalObject varchar(256)," +
            "beginDate date," +
            "desiredReturnDate date," +
            "actualReturnDate date," +
            "overduePayment int"+
            ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(rentalObjectTable);
        mStmt.close();
    }

    private void createReservationTable() throws SQLException{
        String reservationTable =
            "create table Reservation (" +
            "memberId bigint primary key," +
            "rentalObject varchar(256)," +
            "reservationData varchar(256)" +
            ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(reservationTable);
        mStmt.close();	
    }
    
    private void createAvailableSizeInfoTable() throws SQLException{
        String availableSizeInfoTable =
            "create talbe AvailableSizeInfo (" +
            "rentalObjectId bigint," +
            "sizeId int," +
            /*"rentalObject," +
            "height int," +
            "weight int," +
            "waistMin int," +
            "waistMax int" +*/
            ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(availableSizeInfoTable);
        mStmt.close();
    }

    private void createSizeInfoTable() throws SQLException{
        String sizeInfoTable =
            "create table SizeInfo (" +
            "sizeId int primary key,"+
            "sizeName varchar(64)," +
            "weight int," +
            "waistMin int," +
            "waistMax int," +
            "chestMeasure int," +
            "shoulderWidth int,"+
            "lenSleeve int," +
            "inseam int" +
            ")";
        
        mStmt = mConnToDatabase.createStatement();    
        mStmt.executeQuery(sizeInfoTable);
        mStmt.close();
    }
    
    /*private void createGenderTable() throws SQLException{
        String genderTable =
            "create table Gender(" +
            "genderId int," +
            "gender vatchar(64)" +
            ")";
        
        mStmt = mConnToDatabase.createStatement();
        mStmt.execute(genderTable);
        mStmt.close();
    }*/
}
