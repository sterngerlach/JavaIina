
/* CreateDatabaseTable.java */

package javaiina;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateDatabaseTable{
	private String mConnectionUri;
	private Connection mConnToDatabase;
	private Statement mStmt;

	protected CreateDatabaseTable(){
		mConnectionUri= "jdbc:derby:c:/derby/dat/test;create = true";
		mConnToDatabase = DriverManager.getConnection(mConnectionUri);
		mStmt = mConnToDatabase.createStatement();
	}

	private void CreateMemberTable(){
		String createMemberTable = String(
			"create table Member values (" +
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
	    PreparedStatement createMemberTablePrepStmt = mConnToDatabase.prepareStatement(createMemberTable);
	    
	    mStmt.executeQuery(createMemberTablePrepStmt);
	    mStmt.close();
	    mConnToDatabase.close();
	}

	private void CreateRentalTable(){
	    String createRentalTable = String(
	    	"create table Rental values (" +
	    	"memberId bigint primary key," +
	      	"rentalObject varchar," +
	      	"beginDate date," +
	      	"desiredReturnDate date," +
      		"actualReturnDate date," +
      		"overduePayment int"+
      		")"
    	);
    	PreparedStatement createRentalTablePrepStmt = mConnToDatabase.prepareStatement(createRentalTable);

    	mStmt.executeQuery(createRentalTablePrepStmt);
    	mStmt.close();
    	mConnToDatabase.close();
  	}


	private void CreateReservationTable(){
	    String createReservationTable = String(
	    	"create table Reservation values (" +
	    	"memberId bigint primary key," +
	     	"rentalObject varchar," +
	    	"reservationData varchar)"
	    );
	    PreparedStatement createReservationTablePrepStmt = mConnToDatabase.prepareStatement(createReservationTable);
	
	    mStmt.executeQuery(createReservationTablePrepStmt);
	    mStmt.close();
	    mConnToDatabase.close();  	
	}

	private void CreateSizeInfoTable(){
		String createSizeInfoTable = String(
			"create table Rental values (" +
			"sizeId int primary key,"+
			"sizeName varchar," +
			"weight int," +
			"waistMin int," +
			"waistMax int," +
			"chestMeasure int," +
			"shoulderWidth int,"+
			"lenSleeve int," +
			"inseam int" +
			")"
		);
	    PreparedStatement createSizeInfoTablePrepStmt = mConnToDatabase.prepareStatement(createSizeInfoTable);
	
	    mStmt.executeQuery(createSizeInfoTablePrepStmt);
	    mConnToDatabase.close();
	    mConnToDatabase.close();
  	}
}
