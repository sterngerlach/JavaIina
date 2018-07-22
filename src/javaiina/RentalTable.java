
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

public class RentalTable
{
    public RentalTable() throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();

        // Add test data
        if (TableCreateHelper.checkTableExists("Rental"))
            stmt.executeUpdate(
                "insert into RentalObject values" +
                    "(0, 0, 0, 0, '2016-11-07', '2016-11-21', '2016-11-20', 0)" +
                    ",(1, 1, 1, 1, '2016-05-22', '2016-06-05', '2016-06-06', 100)" +
                    ",(2, 2, 2, 2, '2016-11-07', '2016-11-21', '2016-11-15', 0)");
        stmt.close();
    }
    
    public void insert(Rental rental) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "insert into Rental values(" +
                rental.getId() + "," +
                rental.getMember().id() + "," +
                rental.getRentalObject().id() + "," +
                "'" + rental.getBeginDate().toString() + "'," +
                "'" + rental.getDesiredReturnDate().toString() + "'," +
                "'" + rental.getActualReturnDate().toString() + "'," +
                rental.getOverduePayment() + ")");
        stmt.close();
    }

    public void delete(Rental rental) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("delete from Rental where rentalId = " + rental.getId());
        stmt.close();
    }

    public void update(String columnName, String newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set " + columnName + " = '" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void update(String columnName, LocalDate newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set " + columnName + " = '" + newValue + "' " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void update(String columnName, long newValue, String conditionWhere) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set " + columnName + " = " + newValue + " " +
                "where " + conditionWhere);
        stmt.close();
    }

    public void updateMemberId(Rental ren) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set memberId = " + ren.getMember().id() + " "  +
                "where rentalId = " + ren.getId());
        stmt.close();
    }

    public void updateRentalObjectId(Rental ren) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set rentalObjectId = " + ren.getRentalObject().id() + " " +
                "where rentalId = " + ren.getId());
        stmt.close();
    }

    public void updateBeginDate(Rental ren) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set beginDate = '" + ren.getBeginDate() + "' " +
                "where rentalId = " + ren.getId());
        stmt.close();
    }

    public void updateDesiredReturnDate(Rental ren) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set desiredReturnDate = '" + ren.getDesiredReturnDate() + "' " +
                "where rentalId = " + ren.getId());
        stmt.close();
    }

    public void updateActualReturnDate(Rental ren) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "update Rental " +
                "set actualReturnDate = '" + ren.getActualReturnDate() + "' " +
                "where rentalId = " + ren.getId());
        stmt.close();
    }

    public void updateOverduePayment(Rental ren) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate(
            "update Rental " +
                "set overduePayment = " + ren.getOverduePayment() + " " +
                "where rentalId = " + ren.getId());
        stmt.close();
    }

    public List<Rental> select(
        List<Member> members,
        List<RentalObject> rentalObjects,
        List<RentalObjectSizeInfo> sizeInfos) throws SQLException
    {
        Connection conn = DatabaseAccessManager.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        
        List<Rental> resultList = new ArrayList<Rental>();
        ResultSet resultSet = stmt.executeQuery("select * from Rental");
        
        while (resultSet.next()) {
            long memberId = resultSet.getLong("memberId");
            long rentalObjectId = resultSet.getLong("rentalObjectId");
            long sizeInfoId = resultSet.getInt("sizeId");
            
            Member foundMember = members.stream()
                .filter(member -> member.id() == memberId)
                .findFirst()
                .orElse(null);
            RentalObject foundRentalObject = rentalObjects.stream()
                .filter(rentalObject -> rentalObject.id() == rentalObjectId)
                .findFirst()
                .orElse(null);
            RentalObjectSizeInfo foundSizeInfo = sizeInfos.stream()
                .filter(sizeInfo -> sizeInfo.id() == sizeInfoId)
                .findFirst()
                .orElse(null);
            Date actualReturnDate = resultSet.getDate("actualReturnDate");
            
            resultList.add(new Rental(
                resultSet.getLong("rentalId"),
                foundMember,
                foundRentalObject,
                foundSizeInfo,
                resultSet.getDate("beginDate").toLocalDate(),
                resultSet.getDate("desiredReturnDate").toLocalDate(),
                actualReturnDate != null ? actualReturnDate.toLocalDate() : null,
                resultSet.getInt("overduePayment")));
        }
        
        resultSet.close();
        stmt.close();
        
        return resultList;
    }
}
