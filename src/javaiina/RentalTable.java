
/* RentalTableMethod.java */

package javaiina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class RentalTableMethod {
    private RentalTableMethod() throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
         // test data
        if(tableExist("Rental"))
            stmt.executeUpdate("insert into RentalObject values" 
                + "(0, 0, 0, 0, '2016-11-07', '2016-11-21', '2016-11-20', 0)"
                + ",(1, 1, 1, 1, '2016-05-22', '2016-06-05', '2016-06-06', 100)"
                + ",(2, 2, 2, 2, '2016-11-07', '2016-11-21', '2016-11-15', 0)"
              );
        stmt.close();
    }
    
    public void rentalInsert(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("insert into Rental values("
            + ren.getId() + ","
            + ren.getMember().id() + ","
            + ren.getRentalObject().id() + ","
            + "'" + ren.getBeginDate() + "',"
            + "'" + ren.getDesiredReturnDate() + "',"
            + "'" + ren.getActualReturnDate() + "',"
            + ren.getOverduePayment()
            + ")"
        );
        mStmt.close();
    }
    
    public void rentalDelete(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Rental where rentalId = " + ren.getId());
        stmt.close();
    }
    
    public void rentalUpdate(String column, String updateData, String condition){
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set " + column + " = " + "'" + updateData + "' "
            + condition
         );
        stmt.close();
    }
    
    public void rentalUpdate(String column, Date updateData, String condition) throws SQLException {
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set " + column + " = '" + updateData + "' "
            + condition
         );
        stmt.close();
    }
    
    public void rentalUpdate(String column, long updateData, String condition) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set " + column + " = " + updateData + " " 
            + condition
         );
        stmt.close();
    }
    
    public void reservationUpdateMemberId(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnetion();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set memberId = " + ren.getMember().id() + " " 
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateRentalObjectId(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set rentalObjectId = " + ren.getRentalObject().id() + " "  
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateBeginDate(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set beginDate = " + ren.getBeginDate() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateDesiredReturnDate(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set desiredReturnDate = " + ren.getDesiredReturnDate() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateActualReturnDate(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set actualReturnDate = " + ren.getActualReturnDate() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public void rentalUpdateOverduePayment(Rental ren) throws SQLException{
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental "
            + "set overduePayment = " + ren.getOverduePayment() + " "
            + "where rentalId = " + ren.getId()
         ); 
        stmt.close();
    }
    
    public List<Rental> rentalSelectAll(Rental ren) throws SQLException{
        List<Rental> resultList = new ArrayList<Rental>();
        RentalObject[] ro;
        RentalObjectSizeInfo[] rosi;
        Member m;
        Gender g;
        String str = "";
        Connection conn = DatabaseAccess.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet roRs = stmt.executeQuery("select ro.* from RentalObject ro, AvailableSizeInfo a where a.rentalObjectId = " + ren.getRentalObject().id());
        ResultSet rosiRs = stmt.executeQuery("select si.* from SizeInfo si, AvailableSizeInfo a where a.sizeId = " + ren.getSizeInfo().id());
        ResultSet memRs = stmt.executeQuery("select * from Member where memberId = " + ren.getMember().id());
        ResultSet rs = stmt.executeQuery("select * from Rental where rentalId = " + ren.getId());
        
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
            resultList.add(new Rental(
                rs.getLong("rentalId"),
                m,
                ro[i],
                rosi[i],
                rs.getDate("beginDate").toLocalDate(),
                rs.getDate("desiredReturnDate").toLocalDate(),
                rs.getDate("actualReturnDate").toLocalDate(),
                rs.getInt("overduePayment"))
            );
        }
        rs.close();
        stmt.close();
        return resultList;
    }
}
