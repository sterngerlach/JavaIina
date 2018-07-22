
/* DBManager.java */

package javaiina;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBManager
{    
    /* replace to Derby later */
    private class DB
    {
        private List<AvailableSize> mAvailableSizeList;
        private List<Member> mMemberList;
        private List<Rental> mRentalList;
        private List<RentalObject> mRentalObjectList;
        private List<RentalObjectSizeInfo> mRentalObjectSizeInfoList;
        private List<Reservation> mReservationList;
        
        public DB()
        {    
            this.mAvailableSizeList = new ArrayList<>();
            this.mMemberList = new ArrayList<>();
            this.mRentalList = new ArrayList<>();
            this.mRentalObjectList = new ArrayList<>();
            this.mRentalObjectSizeInfoList = new ArrayList<>();
            this.mAvailableSizeList = new ArrayList<>();
            this.mReservationList = new ArrayList<>();
        }
        
        /*
         * Member methods
         */
        public List<Member> selectMember()
        {
            return this.mMemberList;
        }
        
        public List<Member> selectMemberWhereId(long id)
        {
            return this.mMemberList.stream()
                .filter(member -> member.id() == id)
                .collect(Collectors.toList());
        }
        
        public List<Member> selectMemberWhereEmailAddressAndPassword(String emailAddress, String password)
        {
            return this.mMemberList.stream()
                .filter(member -> member.emailAddress().equals(emailAddress))
                .filter(member -> member.password().equals(password))
                .collect(Collectors.toList());
        }
        
        public void insertMember(Member member)
        {
            this.mMemberList.add(member);
        }
        
        /*
         * RentalObject methods
         */
        public List<RentalObject> selectRentalObject()
        { 
            return this.mRentalObjectList; 
        }
        
        public List<RentalObject> selectRentalObjectWhereId(int id)
        {
            return this.mRentalObjectList.stream()
                .filter(rentalObject -> rentalObject.id() == id)
                .collect(Collectors.toList());
        }
        
        public List<RentalObject> selectRentalObjectWhereMember(Member member)
        {
            return this.mRentalList.stream()
                .filter(rental -> rental.getMember().id() == member.id())
                .map(rental -> rental.getRentalObject())
                .collect(Collectors.toList());
        }
        
        public List<RentalObject> selectRentalObjectWhereCategory(String categoryName)
        {
            return this.mRentalObjectList.stream()
                .filter(rentalObject -> rentalObject.categoryName().toLowerCase().equals(categoryName.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        public List<RentalObject> selectRentalObjectWhereName(String name)
        {
            return this.mRentalObjectList.stream()
                .filter(rentalObject -> rentalObject.name().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        public List<RentalObject> selectRentalObjectWhereCategoryAndName(String categoryName, String name)
        {
            return this.mRentalObjectList.stream()
                .filter(rentalObject -> rentalObject.categoryName().toLowerCase().equals(categoryName.toLowerCase()))
                .filter(rentalObject -> rentalObject.name().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        public void insertRentalObject(RentalObject rentalObject)
        {
            this.mRentalObjectList.add(rentalObject);
        }
        
        /*
         * RentalObjectSizeInfo methods
         */
        public List<RentalObjectSizeInfo> selectRentalObjectSizeInfoWhereId(int id)
        {
            return this.mRentalObjectSizeInfoList.stream()
                .filter(rentalObject -> rentalObject.id() == id)
                .collect(Collectors.toList());
        }
        
        public void insertRentalObjectSizeInfo(RentalObjectSizeInfo sizeInfo)
        {
            this.mRentalObjectSizeInfoList.add(sizeInfo);
        }
        
        /*
         * Rental methods
         */
        public List<Rental> selectRental()
        {
            return this.mRentalList;
        }
        
        public void insertRental(Rental rental)
        {
             this.mRentalList.add(rental);
        }
        
        public List<Rental> selectRentalWhereRentalObjectAndSizeInfo(
            RentalObject rentalObject, RentalObjectSizeInfo sizeInfo)
        {
            return this.mRentalList.stream()
                .filter(rental -> rental.getRentalObject().id() == rentalObject.id())
                .filter(rental -> rental.getSizeInfo().id() == sizeInfo.id())
                .collect(Collectors.toList());
        }
        
        public List<Rental> selectRentalWhereMember(Member member)
        {
            return this.mRentalList.stream()
                .filter(rental -> rental.getMember().id() == member.id())
                .collect(Collectors.toList());
        }
        
        public List<Rental> selectBorrowingItemWhereMember(Member member)
        {
            return this.mRentalList.stream()
                .filter(rental -> rental.getMember().id() == member.id())
                .filter(rental -> rental.getActualReturnDate() == null)
                .collect(Collectors.toList());
        }
        
        public List<Rental> selectBorrowedItemWhereMember(Member member)
        {
            return this.mRentalList.stream()
                .filter(rental -> rental.getMember().id() == member.id())
                .filter(rental -> rental.getActualReturnDate() != null)
                .collect(Collectors.toList());
        }
        
        public void updateRental(Rental updatedRental, LocalDate actualReturnDate, int overduePayment)
        {
            this.mRentalList.stream()
                .filter(rental -> rental.getId() == updatedRental.getId())
                .forEach(rental -> {
                    rental.setActualReturnDate(actualReturnDate);
                    rental.setOverduePayment(overduePayment);
                });
        }
        
        /*
         * Reservation methods
         */
        public List<Reservation> selectReservation()
        {
            return this.mReservationList;
        }
        
        public void insertReservation(Reservation reservation)
        {
            this.mReservationList.add(reservation);
        }
        
        public List<Reservation> selectReservationWhereMember(Member member)
        {
            return this.mReservationList.stream()
                .filter(reservation -> reservation.isDone() == false)
                .filter(reservation -> reservation.member().id() == member.id())
                .collect(Collectors.toList());
        }
        
        public List<Reservation> selectReservationWhereRentalObjectAndSizeInfo(
            RentalObject rentalObject, RentalObjectSizeInfo sizeInfo)
        {
            return this.mReservationList.stream()
                .filter(reservation -> reservation.isDone() == false)
                .filter(reservation -> reservation.rentalObject().id() == rentalObject.id())
                .filter(reservation -> reservation.sizeInfo().id() == sizeInfo.id())
                .collect(Collectors.toList());
        }
        
        public void updateReservationDone(Reservation updatedReservation)
        {
            this.mReservationList.stream()
                .filter(reservation -> reservation.id() == updatedReservation.id())
                .forEach(reservation -> reservation.changeDone(true));
        }
    }
    
    private DB mDb;
    
    public DBManager()
    {
        this.mDb = new DB();
        this.readData();
    }
    
    public int generateMemberId()
    {
        return this.mDb.selectMember().size();
    }
    
    public Member getMemberInfo(long id)
    {
        return this.mDb.selectMemberWhereId(id).get(0);
    }
    
    public Member getMember(String emailAddress, String password)
    {
        return this.mDb.selectMemberWhereEmailAddressAndPassword(emailAddress, password).get(0);
    }
    
    public boolean memberExists(String emailAddress, String password)
    {
        return this.mDb.selectMemberWhereEmailAddressAndPassword(emailAddress, password).size() > 0;
    }
    
    public void addMember(Member member)
    {
        this.mDb.insertMember(member);
    }
    
    public List<RentalObject> nowRetalingObjects(Member member)
    {
        return this.mDb.selectRentalObjectWhereMember(member);
    }
    
    public List<RentalObject> allRentalObjects()
    {
        return this.mDb.selectRentalObject();
    }
    
    public List<RentalObject> searchByCategoryName(String categoryName)
    {
        return this.mDb.selectRentalObjectWhereCategory(categoryName);
    }
    
    public List<RentalObject> searchByName(String name)
    {
        return this.mDb.selectRentalObjectWhereName(name);
    }
    
    public List<RentalObject> searchByNameAndCategory(String name, String categoryName)
    {
        return this.mDb.selectRentalObjectWhereCategoryAndName(categoryName, name);
    }
    
    public List<Rental> getBorrowingItems(Member member)
    {
        return this.mDb.selectBorrowingItemWhereMember(member);
    }
    
    public List<Rental> getBorrowedItems(Member member)
    {
        return this.mDb.selectBorrowedItemWhereMember(member);
    }
    
    public boolean isAvailableRentalObject(RentalObject rentalObject, RentalObjectSizeInfo sizeInfo)
    {
        return !this.mDb.selectRentalWhereRentalObjectAndSizeInfo(rentalObject, sizeInfo)
            .stream()
            .anyMatch(rental -> rental.getActualReturnDate() == null);
    }
    
    public int generateReservationId()
    {
        return this.mDb.selectReservation().size();
    }
    
    public void addReservation(Reservation reservation)
    {
        this.mDb.insertReservation(reservation);
    }
    
    public List<Reservation> getReservations(Member member)
    {
        return this.mDb.mReservationList.stream()
            .filter(reservation -> reservation.member().id() == member.id())
            .filter(reservation -> reservation.isDone() == false)
            .collect(Collectors.toList());
    }
    
    public boolean processRental(
        Member member, RentalObject rentalObject, RentalObjectSizeInfo sizeInfo,
        LocalDate beginDate, LocalDate desiredDate)
    {
        if (this.isAvailableRentalObject(rentalObject, sizeInfo)) {
            int rentalId = this.generateRentalId();
            Rental rental = new Rental(rentalId, member, rentalObject, sizeInfo, beginDate, desiredDate, null, 0);
            this.mDb.insertRental(rental);
            return true;
        } else {
            int reservationId = this.generateReservationId();
            Reservation reservation = new Reservation(reservationId, member, rentalObject, sizeInfo, LocalDate.now(), false);
            this.mDb.insertReservation(reservation);
            return false;
        }
    }
    
    public List<Reservation> ReservedRentalObjectList(Member member)
    {
        return this.mDb.selectReservationWhereMember(member);
    }
    
    public void returnRentalObject(Rental rental, LocalDate actualReturnDate, int overduePayment)
    {
        this.mDb.updateRental(rental, actualReturnDate, overduePayment);
        List<Reservation> resList = this.mDb.selectReservationWhereRentalObjectAndSizeInfo(
            rental.getRentalObject(), rental.getSizeInfo());
        
        if (resList.size() > 0) {
            this.processRental(
                resList.get(0).member(), rental.getRentalObject(), rental.getSizeInfo(),
                actualReturnDate, actualReturnDate.plusWeeks(2));
            this.mDb.updateReservationDone(resList.get(0));
        }
    }
    
    public int generateRentalId()
    {
        return this.mDb.selectRental().size();
    }
    
    public void saveData()
    {
        BufferedWriter bw = null;
        
        try {
            bw = Files.newBufferedWriter(Paths.get("Rental.csv"), Charset.defaultCharset());
            
            for (Rental rental : this.mDb.selectRental()) {
                System.out.println(rental.toString());
                bw.write(
                    rental.getId() + "," + 
                    rental.getMember().id() + "," + 
                    rental.getRentalObject().id() + "," + 
                    rental.getSizeInfo().id() + "," + 
                    rental.getBeginDate().toString() + "," + 
                    rental.getDesiredReturnDate().toString() + "," + 
                    (rental.getActualReturnDate() == null ? "" : rental.getActualReturnDate().toString()) + "," +
                    rental.getOverduePayment() + System.lineSeparator());
            }
            
            bw.close();
            
            bw = Files.newBufferedWriter(Paths.get("Reservation.csv"), Charset.defaultCharset());
           
            for (Reservation res : this.mDb.selectReservation()) {
                bw.write(
                    res.id() + "," + 
                    res.member().id() +"," + 
                    res.rentalObject().id() +"," + 
                    res.sizeInfo().id() + "," + 
                    res.reservationDate().toString() + "," + 
                    res.isDone() + System.lineSeparator());
            }
            
            bw.close();
            
            bw = Files.newBufferedWriter(Paths.get("Member.csv"), Charset.defaultCharset());
            
            for (Member member : this.mDb.mMemberList) {
                bw.write(
                    member.id() + "," + 
                    member.firstName() + "," + 
                    member.secondName() + "," + 
                    member.firstNameKana() + "," + 
                    member.secondNameKana() + "," + 
                    member.nickName() + "," + 
                    member.birthDate().toString() + "," +
                    member.registerDate().toString() + "," +
                    member.gender().toString() + "," + 
                    member.address() + "," + 
                    member.postcode() + "," + 
                    member.phoneNumber() + "," + 
                    member.emailAddress() + "," + 
                    member.password() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e){
            }
        }
    }
    
    private void readMember() throws IOException
    {
        /* read Member.csv */
        List<String> memberLineList = Files.readAllLines(Paths.get("Member.csv"), Charset.defaultCharset());
        
        for (String line : memberLineList) {
            String[] valueArray = line.split(",");
            
            Gender gender = null;
            if (valueArray[8].equals(Gender.MALE.toString())) gender = Gender.MALE;
            else if (valueArray[8].equals(Gender.FEMALE.toString())) gender = Gender.FEMALE;
            else gender = Gender.UNSPECIFIED;
            
            Member member = new Member(
                    (long)Integer.valueOf(valueArray[0]), valueArray[1], valueArray[2],
                    valueArray[3], valueArray[4], valueArray[5],
                    LocalDate.parse(valueArray[6]),LocalDate.parse(valueArray[7]), 
                    gender, valueArray[9], valueArray[10],
                    valueArray[11], valueArray[12],
                    valueArray[13]
                    );
            this.mDb.insertMember(member);
        }
    }

    private void readRentalObjectSizeInfo() throws IOException
    {
        List<String> sizeInfoLineList = Files.readAllLines(Paths.get("RentalObjectSizeInfo.csv"));
        for(String sizeInfoLine : sizeInfoLineList) {
            String[] valueArray = sizeInfoLine.split(",");
            RentalObjectSizeInfo sizeInfo = new RentalObjectSizeInfo(
                    Integer.valueOf(valueArray[0]), valueArray[1],
                    Integer.valueOf(valueArray[2]), Integer.valueOf(valueArray[3]),
                    Integer.valueOf(valueArray[4]), Integer.valueOf(valueArray[5]),
                    Integer.valueOf(valueArray[6]), Integer.valueOf(valueArray[7]),
                    Integer.valueOf(valueArray[8]), Integer.valueOf(valueArray[9])
                    );
            this.mDb.insertRentalObjectSizeInfo(sizeInfo);
        }
    }
    
    private void readRentalObject() throws IOException
    {
        List<String> rentalObjectLineList = Files.readAllLines(Paths.get("RentalObject.csv"));
        
        for (String rentalObjectLine : rentalObjectLineList) {
            String[] valueArray = rentalObjectLine.split(",");
            
            int id = Integer.valueOf(valueArray[0]);
            String name = valueArray[1];
            String categoryName = valueArray[2];
            int cost = Integer.valueOf(valueArray[3]);
            List<RentalObjectSizeInfo> availableSizeInfoList = new ArrayList<>();
            
            for (int i = 4; i < valueArray.length; ++i) {
                availableSizeInfoList.add(this.mDb.selectRentalObjectSizeInfoWhereId(Integer.valueOf(valueArray[i])).get(0));
            }
            RentalObjectSizeInfo[] availableSizeInfoArray = availableSizeInfoList.toArray(new RentalObjectSizeInfo[availableSizeInfoList.size()]);
            
            RentalObject rentalObject = new RentalObject(
                    id, name, categoryName,
                    availableSizeInfoArray, cost
                    );
            
            this.mDb.insertRentalObject(rentalObject);
        }
    }
    
    private void readReservation() throws IOException
    {
        List<String> reservationLineList = Files.readAllLines(Paths.get("Reservation.csv"));
        for (String reservationLine : reservationLineList) {
            String[] valueArray = reservationLine.split(",");
            int id = Integer.valueOf(valueArray[0]);
            Member member = this.mDb.selectMemberWhereId(Integer.valueOf(valueArray[1])).get(0);
            
            RentalObject rentalObject = this.mDb.selectRentalObjectWhereId(Integer.valueOf(valueArray[2])).get(0);
            RentalObjectSizeInfo sizeInfo = this.mDb.selectRentalObjectSizeInfoWhereId(Integer.valueOf(valueArray[3])).get(0);
            LocalDate reservationDate = LocalDate.parse(valueArray[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boolean done = Boolean.valueOf(valueArray[5]);
            
            Reservation reservation = new Reservation(
                    id, member,
                    rentalObject, sizeInfo,
                    reservationDate, done
                    );
            this.mDb.insertReservation(reservation);
        }
    }
    
    private void readRental() throws IOException
    {
        List<String> rentalLineList = Files.readAllLines(Paths.get("Rental.csv"));
        
        for (String rentalLine : rentalLineList) {
            String[] valueArray = rentalLine.split(",");
            int id = Integer.valueOf(valueArray[0]);
            Member member = this.mDb.selectMemberWhereId((long)Integer.valueOf(valueArray[1])).get(0);
            RentalObject rentalObject = this.mDb.selectRentalObjectWhereId(Integer.valueOf(valueArray[2])).get(0);
            RentalObjectSizeInfo sizeInfo = this.mDb.selectRentalObjectSizeInfoWhereId(Integer.valueOf(valueArray[3])).get(0);
            LocalDate beginDate = LocalDate.parse(valueArray[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate desiredReturnDate = LocalDate.parse(valueArray[5], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate actualReturnDate = valueArray[6].equals("") ? null : LocalDate.parse(valueArray[6], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int overduePayment = Integer.valueOf(valueArray[7]);
            Rental rental = new Rental(
                    id, member, rentalObject,
                    sizeInfo, beginDate, desiredReturnDate,
                    actualReturnDate, overduePayment);
            this.mDb.insertRental(rental);
        }
    }
    
    public void readData()
    {
        try {
            this.readMember();
            this.readRentalObjectSizeInfo();
            this.readRentalObject();
            this.readReservation();
            this.readRental();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
