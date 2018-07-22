
/* ReservationTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ReservationTableMethod{
    private ReservationTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        // test data
        if(tableExist("Reservation"))
            stmt.executeUpdate("insert into Reservation values" 
                + "(0, 0, 0, '2017-07-017', 0, 1)"
                + ",(1, 1, 1, '2017-07-017', 0, 1)"
                + ",(2, 2, 2, '2017-07-017', 0, 1)"
              );
        stmt.close();
    }
    
    public void reservationInsert(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Reservation values("
            + res.id() + ","
            + res.member().id() + ","
            + res.rentalObject().id() + ","
            + "'" + res.reservationDate() + "',"
            + res.sizeInfo().id() + ","
            + res.isDone() + ","
              + ")"
         );
        stmt.close();
    }

    public void reservationDelete(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Reservation where reservationId = " + res.id());
        stmt.close();
    }
    
    public void reservationUpdate(String column, String updateData, String condition) throws SQLException {
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + "'" + updateData + "' "
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdate(String column, Date updateData, String condition) throws SQLException {
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            + "set " + column + " = " + updateData + " "
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation "
            +"set " + column + " = " + updateData + " " 
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdateMember(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set memberId = " + res.member().id() + " " 
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateRentalObject(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set rentalObjectId = " + res.rentalObject().id() + " "  
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateSizeInfo(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set sizeInfo = " + res.sizeInfo().id() + " "
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateReservationDate(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            +" set reservationDate = " + res.reservationDate() + " "
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public void reservationUpdateDone(Reservation res) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Reservation"
            + "where reservationId = " + res.id()
         ); 
        stmt.close();
    }
    
    public List<Reservation> reservationSelectAll(Reservation res) throws SQLException{
        List<Reservation> resultList = new ArrayList<Reservation>();
        RentalObject[] ro;
        RentalObjectSizeInfo[] rosi;
        Member m;
        Gender g;
        String str = "";
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet roRs = stmt.executeQuery("select ro.* from RentalObject ro, AvailableSizeInfo a where a.rentalObjectId = " + res.rentalObject().id());
        ResultSet rosiRs = stmt.executeQuery("select si.* from SizeInfo si, AvailableSizeInfo a where a.sizeId = " + res.sizeInfo().id());
        ResultSet memRs = stmt.executeQuery("select * from Member where memberId = " + res.member().id());;
        ResultSet rs = stmt.executeQuery("select * from Reservation where reservationId = " + res.id());
        
        for (int i = 0; rosiRs.next(); i++)
            rosi[i] = new RentalObjectSizeInfo(
                    rosiRs.getLong("sizeId"),
                    rosiRs.getString("sizeName"),
                    rosiRs.getInt("height"),
                    rosiRs.getInt("weight"),
                    rosiRs.getInt("waistMin"),
                    rosiRs.getInt("waistMax"),
                    rosiRs.getInt("chestWidth"),
                    rosiRs.getInt("shoulderLength"),
                    rosiRs.getInt("sleeveLength"),
                    rosiRs.getInt("inseam")
                    );    
        
        for (int i = 0; roRs.next(); i++)
            ro[i] = new RentalObject(
                    roRs.getLong("rentalObjectId"),
                    roRs.getString("rentalObjectName"),
                    roRs.getString("categoryName"),
                    rosi,
                    roRs.getInt("cost")
                    );
        
        while(memRs.next()) { 
            str = rs.getString("gender");
            if (str.equals("Male")) { g = Gender.MALE;}
            else if (str.equals("Female")) { g = Gender.FEMALE;}
            else {g = Gender.UNSPECIFIED;}
            m = new Member(
                memRs.getLong("memberId"),
                memRs.getString("firstName"),
                memRs.getString("secontName"),
                memRs.getString("firstNameKana"),
                memRs.getString("secontNameKana"),
                memRs.getString("nickName"),
                memRs.getDate("birthDate").toLocalDate(),
                memRs.getDate("registerDate").toLocalDate(),
                g,
                memRs.getString("address"),
                memRs.getString("postCode"),
                memRs.getString("phoneNumber"),
                memRs.getString("emailAddress")
            );
        }

        for(int i = 0; rs.next(); i++) {
            resultList.add(new Reservation(
                rs.getInt("reservationId"),
                m,
                ro[i],
                rosi[i],
                rs.getDate("reservationDate").toLocalDate(),
                rs.getBoolean("done"))
            );
        }
        rs.close();
        stmt.close();
        return resultList;
    }
}
