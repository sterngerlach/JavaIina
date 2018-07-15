
/* CreateDatabaseTable.java */

package javaiina;

import java.sql.*;

public class CreateDatabaseTable extends DatabaseAccess{
    
    private CreateDatabaseTable() throws SQLException, ClassNotFoundException{
        super();
    }    
    
    private void CreateMemberTable() throws SQLException{
        String createMemberTable = new String(
            "create table Member (" +
            "memberId bigint primary key," +
            "FirstName varchar," +
            "SecondName varchar," +
            "NickName varchar," +
            "BirthData datetime," +
            "RegisterData datetime," +
            "Gender int," +
            "PhoneNumber varchar," +
            "EmailAddress varchar," +
            ")"
        );
        mStmt.execute(createMemberTable);
        mStmt.close();
        mConnToDatabase.close();
    }

    private void CreateRentalTable() throws SQLException{
        String createRentalTable = new String(
            "create table Rental (" +
            "memberId bigint primary key," +
            "rentalObject varchar(200)," +
            "beginDate date," +
            "desiredReturnDate date," +
            "actualReturnDate date," +
            "overduePayment int"+
            ")"
        );
        mStmt.execute(createRentalTable);
        mStmt.close();
        mConnToDatabase.close();
    }

    private void CreateReservationTable() throws SQLException{
        String createReservationTable = new String(
            "create table Reservation (" +
            "memberId bigint primary key," +
            "rentalObject varchar(200)," +
            "reservationData varchar(200)" +
            ")"
        );
        mStmt.execute(createReservationTable);
        mStmt.close();
        mConnToDatabase.close();  	
    }

    private void CreateSizeInfoTable() throws SQLException{
        String createSizeInfoTable =  new String(
            "create table Rental (" +
            "sizeId int primary key,"+
            "sizeName varchar(10)," +
            "weight int," +
            "waistMin int," +
            "waistMax int," +
            "chestMeasure int," +
            "shoulderWidth int,"+
            "lenSleeve int," +
            "inseam int" +
            ")"
        );
        mStmt.executeQuery(createSizeInfoTable);
        mConnToDatabase.close();
        mConnToDatabase.close();
    }
}
