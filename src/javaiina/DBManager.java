
/* DBManager.java */

package javaiina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DBManager {
    
    /* replace to Derby later */
    private class DB {
        private List<AvailableSize> mAvailableSizeList;
        private List<Member> mMemberList;
        private List<Rental> mRentalList;
        private List<RentalObject> mRentalObjectList;
        private List<RentalObjectSizeInfo> mRentalObjectSizeInfoList;
        private List<Reservation> mReservationList;
        
        public DB() {
            
            this.mAvailableSizeList = new ArrayList<>();
            this.mMemberList = new ArrayList<>();
            this.mRentalList = new ArrayList<>();
            this.mRentalObjectList = new ArrayList<>();
            this.mRentalObjectSizeInfoList = new ArrayList<>();
            this.mAvailableSizeList = new ArrayList<>();
            this.mReservationList = new ArrayList<>();
        }
        
        public List<Member> selectMember() {
            return this.mMemberList;
        }
        
        public List<Member> selectMemberWhereId(long id) {
            return this.mMemberList.stream().filter(member -> member.id() == id).collect(Collectors.toList());
        }
        
        public List<Member> selectMemberWhereEmailAdressAndPassword(String emailAdress, String password) {
            List<Member> memberList = new ArrayList<>();
            for (int i = 0; i < this.mMemberList.size(); ++i) {
                if (this.mMemberList.get(i).emailAddress().equals(emailAdress) &&
                    this.mMemberList.get(i).password().equals(password)) { 
                    memberList.add(this.mMemberList.get(i));
                }
            }
            return memberList;
        }
        
        public void insertMember(Member member) {
            this.mMemberList.add(member);
        }
        
        public void insertRentalObject(RentalObject rentalObject) {
            this.mRentalObjectList.add(rentalObject);
        }
        
        public List<RentalObject> selectRentalObjectWhereMember(Member member) {
            List<RentalObject> roList = new ArrayList<>();
            
            for (int i = 0; i < this.mRentalList.size(); ++i) {
                if (this.mRentalList.get(i).getMember().id() == member.id()) {
                    roList.add(this.mRentalList.get(i).getRentalObject());
                }
            }
            return roList;
        }
        
        public List<RentalObject> selectRentalObject() { 
            return this.mRentalObjectList; 
        }
        
        public List<RentalObject> selectRentalObjectWhereId(int id) {
            return this.mRentalObjectList.stream()
                    .filter(rentalObject -> rentalObject.id() == id).collect(Collectors.toList());
        }
        
        public List<RentalObject> selectRentalObjectWhereCategory(String categoryName) {
            List<RentalObject> roList = new ArrayList<>();
            for (int i = 0; i < this.mRentalObjectList.size(); ++i) {
                if (this.mRentalObjectList.get(i).categoryName().toLowerCase().equals(categoryName.toLowerCase())) {
                    roList.add(this.mRentalObjectList.get(i));
                }
            }
            return roList;
        }
        
        public List<RentalObject> selectRentalObjectWhereName(String name) {
            List<RentalObject> roList = new ArrayList<>();
            for (int i = 0; i < this.mRentalObjectList.size(); ++i) {
                if (this.mRentalObjectList.get(i).name().toLowerCase().contains(name.toLowerCase())) {
                    roList.add(this.mRentalObjectList.get(i));
                }
            }
            return roList;
        }
        
        public void insertRentalObjectSizeInfo(RentalObjectSizeInfo sizeInfo) {
            this.mRentalObjectSizeInfoList.add(sizeInfo);
        }
        
        public List<RentalObjectSizeInfo> selectRentalObjectSizeInfoWhereId(int id) {
            return this.mRentalObjectSizeInfoList.stream()
                    .filter(rentalObject -> rentalObject.id() == id).collect(Collectors.toList());
        }
        
        public void insertRental(Rental rental) {
             this.mRentalList.add(rental);
        }
        
        public List<Rental> selectRental() {
            return this.mRentalList;
        }
        
        public List<Rental> selectRentalWhereRentalObjectAndSizeInfo(RentalObject rentalObject, RentalObjectSizeInfo sizeInfo) {
            List<Rental> rList = new ArrayList<>();
            for (int i = 0; i < this.mRentalList.size(); ++i) {
                if (this.mRentalList.get(i).getRentalObject().id() == rentalObject.id()
                    && this.mRentalList.get(i).getSizeInfo().id() == sizeInfo.id()) {
                    rList.add(this.mRentalList.get(i));
                }
            }
            return rList;
        }
        
        public List<Rental> selectRentalWhereMember(Member member) {
            return this.mRentalList.stream().filter(rental -> rental.getMember().id() == member.id())
                   .collect(Collectors.toList());
        }
        
        public List<Rental> selectBorrowingItemWhereMember(Member member) {
            return this.mRentalList.stream().filter(rental -> rental.getMember().id() == member.id() )
                    .filter(rental -> rental.getActualReturnDate() == null).collect(Collectors.toList());
        }
        
        public List<Rental> selectBorrowedItemWhereMember(Member member) {
            return this.mRentalList.stream().filter(rental -> rental.getMember().id() == member.id() )
            .filter(rental -> rental.getActualReturnDate() != null).collect(Collectors.toList());
        }
        
        public void updateRental(Rental rental, LocalDate actualReturnDate, int overduePayment) {
            for (int i = 0; i < this.mRentalList.size(); ++i) {
                if (this.mRentalList.get(i).getId() == rental.getId()) {
                    this.mRentalList.get(i).setActualReturnDate(actualReturnDate);
                    this.mRentalList.get(i).setOverduePayment(overduePayment);
                }
            }
        }
        
        public void insertReservation(Reservation reservation) {
            this.mReservationList.add(reservation);
        }
        
        public List<Reservation> selectReservation() {
            return this.mReservationList;
        }
        
        public List<Reservation> selectReservationWhereMember(Member member) {
            List<Reservation> resList = new ArrayList<>();
            for (int i = 0; i < this.mReservationList.size(); ++i) {
                if (this.mReservationList.get(i).isDone() == false && this.mReservationList.get(i).member().id() == member.id()) {
                    resList.add(this.mReservationList.get(i));
                }
            }
            return resList;
        }
        
        public List<Reservation> selectReservationWhereRentalObjectAndSizeInfo(RentalObject rentalObject, RentalObjectSizeInfo sizeInfo) {
            List<Reservation> resList = new ArrayList<>();
            for (int i = 0; i < this.mReservationList.size(); ++i) {
                if (this.mReservationList.get(i).rentalObject().id() == rentalObject.id()) {
                    resList.add(this.mReservationList.get(i));
                }
            }
            return resList;
        }
        
        public void updateReservationDone(Reservation reservation) {
            for (int i = 0; i < this.mReservationList.size(); ++i) {
                if (this.mReservationList.get(i).id() == reservation.id()) {
                    this.mReservationList.get(i).changeDone(true);
                }
            }
        }
        
    }
/*---------------------------------------------------------------------------*/
    private DB db;
    
    public DBManager() {
        this.db = new DB();
        this.readData();
    }
    
    @Override
    public void finalize () {
        this.saveData();
    }
    
    public int generateMemberId() {
        return this.db.selectMember().size();
    }
    
    public Member getMemberInfo(long id) {
        List<Member> memberList = this.db.selectMemberWhereId(id);
        return memberList.get(0);
    }
    
    public Member getMember(String emailAddress, String password) {
        return this.db.selectMemberWhereEmailAdressAndPassword(emailAddress, password).get(0);
    }
    
    public boolean memberExists(String emailAddress, String password) {
        return this.db.selectMemberWhereEmailAdressAndPassword(emailAddress, password).size() > 0;
    }
    
    public void addMember(Member member) {
        this.db.insertMember(member);
    }
    
    public List<RentalObject> nowRetalingObjects(Member member) {
        return this.db.selectRentalObjectWhereMember(member);
    }
    
    public List<RentalObject> allRentalObjects() {
        return this.db.selectRentalObject();
    }
    
    public List<RentalObject> searchByCategoryName(String categoryName) {
        return this.db.selectRentalObjectWhereCategory(categoryName);
    }
    
    public List<RentalObject> searchByName(String name) {
        return this.db.selectRentalObjectWhereName(name);
    }
    
    public List<RentalObject> searchByNameAndCategory(String name, String categoryName) {
        return this.db.mRentalObjectList.stream()
                .filter(rentalObject -> rentalObject.name().toLowerCase().contains(name.toLowerCase()))
                .filter(rentalObject -> rentalObject.categoryName().toLowerCase().equals(categoryName.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Rental> getBorrowingItems(Member member) {
        return this.db.selectBorrowingItemWhereMember(member);
    }
    
    public List<Rental> getBorrowedItems(Member member) {
        return this.db.selectBorrowedItemWhereMember(member);
    }
    
    public boolean isAvailableRentalObject(RentalObject rentalObject, RentalObjectSizeInfo sizeInfo) {
        List<Rental> rentalLogList = this.db.selectRentalWhereRentalObjectAndSizeInfo(rentalObject, sizeInfo);
        for (int i = 0; i < rentalLogList.size(); ++i) {
            if (rentalLogList.get(i).getActualReturnDate() == null) { 
                return false;
            }
        }
        return true;
    }
    
    public int generateReserveId() {
        return this.db.selectReservation().size();
    }
    
    public void Reserve(Reservation reservation) {
        this.db.insertReservation(reservation);
    }
    
    public boolean Rental(Member member, RentalObject rentalObject, RentalObjectSizeInfo sizeInfo, LocalDate beginDate, LocalDate disiredDate) {
        if (this.isAvailableRentalObject(rentalObject, sizeInfo)) {
            List<Rental> rentalList = this.db.selectRental();
            int id_rental = rentalList.size();
            Rental rental;
            rental = new Rental(id_rental, member, rentalObject, sizeInfo, beginDate, disiredDate, null, 0);
            this.db.insertRental(rental);
            return true;
        } else {
            List<Reservation> reservationList = this.db.selectReservation();
            int id_reservation = reservationList.size();
            Reservation reservation = new Reservation(id_reservation, member, rentalObject, sizeInfo, LocalDate.now(), false);
            this.db.insertReservation(reservation);
            return false;
        }
    }
    
    public List<Reservation> ReservedRentalObjectList(Member member) {
        return this.db.selectReservationWhereMember(member);
    }
    
    public void ReturnObject(Rental rental, LocalDate actualReturnDate, int overduePayment) {
        this.db.updateRental(rental, actualReturnDate, overduePayment);
        List<Reservation> resList = this.db.selectReservationWhereRentalObjectAndSizeInfo(rental.getRentalObject(), rental.getSizeInfo())
                .stream().filter(res -> res.isDone() == false).collect(Collectors.toList());
        
        if (resList.size() > 0) {
            this.Rental(resList.get(0).member(), rental.getRentalObject(), rental.getSizeInfo(), actualReturnDate, actualReturnDate.plusWeeks(2));
            this.db.updateReservationDone(resList.get(0));
        } else {
            ;
        }
        
    }
    
    public int generateRentalId() {
        return this.db.selectRental().size();
    }
    
    public void saveData() {
        BufferedWriter bw = null;
        try {
            
            bw = Files.newBufferedWriter(Paths.get("Rental.csv"), Charset.defaultCharset());
            for (Rental rental : this.db.selectRental()) {
                System.out.println(rental.toString());
                bw.write(rental.getId() + "," + 
                        rental.getMember().id() + "," + 
                        rental.getRentalObject().id() + "," + 
                        rental.getSizeInfo().id() + "," + 
                        rental.getBeginDate().toString() + "," + 
                        ((rental.getActualReturnDate() == null) ? "" : rental.getActualReturnDate().toString()) + "," +
                        rental.getOverduePayment() + System.lineSeparator()
                        );
            }
            
            bw.close();
            
            bw = Files.newBufferedWriter(Paths.get("Reservation.csv"), Charset.defaultCharset());
           
            for ( Reservation res : this.db.selectReservation()) {
                bw.write(res.id() + "," + 
                        res.member().id() +"," + 
                        res.rentalObject().id() +"," + 
                        res.sizeInfo().id() + "," + 
                        res.reservationDate().toString() + "," + 
                        res.isDone() + System.lineSeparator()
                        );
            }
            bw.close();
            
            bw = Files.newBufferedWriter(Paths.get("Member.csv"), Charset.defaultCharset());
            for (Member member : this.db.mMemberList) {
                bw.write(member.id() + "," + 
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
                        member.password() + System.lineSeparator()
                        );
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    
                    bw.close();
                }
            } catch (Exception e){
                ;
            }
        }
    }
    
    private void readMember() throws IOException {
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
            this.db.insertMember(member);
        }
    }

    private void readRentalObjectSizeInfo() throws IOException {
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
            this.db.insertRentalObjectSizeInfo(sizeInfo);
        }
    }
    
    private void readRentalObject() throws IOException {
        List<String> rentalObjectLineList = Files.readAllLines(Paths.get("RentalObject.csv"));
        
        for (String rentalObjectLine : rentalObjectLineList) {
            String[] valueArray = rentalObjectLine.split(",");
            
            int id = Integer.valueOf(valueArray[0]);
            String name = valueArray[1];
            String categoryName = valueArray[2];
            int cost = Integer.valueOf(valueArray[3]);
            List<RentalObjectSizeInfo> availableSizeInfoList = new ArrayList<>();
            
            for (int i = 4; i < valueArray.length; ++i) {
                availableSizeInfoList.add(this.db.selectRentalObjectSizeInfoWhereId(Integer.valueOf(valueArray[i])).get(0));
            }
            RentalObjectSizeInfo[] availableSizeInfoArray = availableSizeInfoList.toArray(new RentalObjectSizeInfo[availableSizeInfoList.size()]);
            
            RentalObject rentalObject = new RentalObject(
                    id, name, categoryName,
                    availableSizeInfoArray, cost
                    );
            
            this.db.insertRentalObject(rentalObject);
        }
    }
    
    private void readReservation() throws IOException {
        List<String> reservationLineList = Files.readAllLines(Paths.get("Reservation.csv"));
        for (String reservationLine : reservationLineList) {
            String[] valueArray = reservationLine.split(",");
            int id = Integer.valueOf(valueArray[0]);
            Member member = this.db.selectMemberWhereId(Integer.valueOf(valueArray[1])).get(0);
            
            RentalObject rentalObject = this.db.selectRentalObjectWhereId(Integer.valueOf(valueArray[2])).get(0);
            RentalObjectSizeInfo sizeInfo = this.db.selectRentalObjectSizeInfoWhereId(Integer.valueOf(valueArray[3])).get(0);
            LocalDate reservationDate = LocalDate.parse(valueArray[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boolean done = Boolean.valueOf(valueArray[5]);
            
            Reservation reservation = new Reservation(
                    id, member,
                    rentalObject, sizeInfo,
                    reservationDate, done
                    );
            this.db.insertReservation(reservation);
        }
    }
    
    private void readRental() throws IOException {
        List<String> rentalLineList = Files.readAllLines(Paths.get("Rental.csv"));
        
        for (String rentalLine : rentalLineList) {
            String[] valueArray = rentalLine.split(",");
            int id = Integer.valueOf(valueArray[0]);
            Member member = this.db.selectMemberWhereId((long)Integer.valueOf(valueArray[1])).get(0);
            RentalObject rentalObject = this.db.selectRentalObjectWhereId(Integer.valueOf(valueArray[2])).get(0);
            RentalObjectSizeInfo sizeInfo = this.db.selectRentalObjectSizeInfoWhereId(Integer.valueOf(valueArray[3])).get(0);
            LocalDate beginDate = LocalDate.parse(valueArray[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate desiredReturnDate = LocalDate.parse(valueArray[5], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate actualReturnDate = valueArray[6].equals("") ? null : LocalDate.parse(valueArray[6], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int overduePayment = Integer.valueOf(valueArray[7]);
            Rental rental = new Rental(
                    id, member, rentalObject,
                    sizeInfo, beginDate, desiredReturnDate,
                    actualReturnDate, overduePayment);
            this.db.insertRental(rental);
        }
    }
    
    public void readData() {
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
