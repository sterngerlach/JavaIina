
/* DBManager.java */

package javaiina;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
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
            
         // This data makes absolutely no sense
            
            this.mMemberList.add(new Member(0, "a","a", "あ","あ", "a", 
                    LocalDate.of(1998, 2, 14), LocalDate.of(2000, 5, 9), Gender.MALE, 
                    "東京都千代田区千代田", "000-0000", "000-0000-0000","a@a", "aa"));
            
            this.mMemberList.add(new Member(1, "b","b", "ぶ","ぶ", "b", 
                    LocalDate.of(1997, 4, 27), LocalDate.of(2012, 3, 30), Gender.UNSPECIFIED, 
                    "東京都世田谷区", "111-1111", "111-1111-1111","b@b", "bb"));
       
            this.mRentalObjectSizeInfoList.add(new RentalObjectSizeInfo(0, "Y1", 150, 45, 50, 55, 60, 70, 80, 80));
            this.mRentalObjectSizeInfoList.add(new RentalObjectSizeInfo(1, "A2", 155, 50, 55, 60, 70, 80, 90, 90));
            this.mRentalObjectSizeInfoList.add(new RentalObjectSizeInfo(2, "B3", 160, 55, 60, 65, 80, 90, 90, 90));
            
            this.mRentalObjectList.add(new RentalObject(0, "ClothA1", "CategoryA",
                    new RentalObjectSizeInfo[] { this.mRentalObjectSizeInfoList.get(0)}, 100));
            this.mRentalObjectList.add(new RentalObject(1, "ClothB2", "CategoryB",
                    new RentalObjectSizeInfo[] { this.mRentalObjectSizeInfoList.get(1), this.mRentalObjectSizeInfoList.get(2) }, 150));
            this.mRentalObjectList.add(new RentalObject(2, "ClothC3", "CategoryC",
                    new RentalObjectSizeInfo[] { this.mRentalObjectSizeInfoList.get(2) }, 200));
                /*new RentalObject(3, "ClothB1", "CategoryB",
                    new RentalObjectSizeInfo[] { mDummySizeInfoList.get(0), mDummySizeInfoList.get(1) }, 250),
                new RentalObject(4, "ClothB2", "CategoryB",
                    new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1) }, 300),
                new RentalObject(5, "ClothB3", "CategoryB",
                    new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1), mDummySizeInfoList.get(2) }, 350),
                new RentalObject(6, "ClothC1", "CategoryC",
                    new RentalObjectSizeInfo[] { mDummySizeInfoList.get(0), mDummySizeInfoList.get(1) }, 400),
                new RentalObject(7, "ClothC2", "CategoryC",
                    new RentalObjectSizeInfo[] { mDummySizeInfoList.get(0) }, 450),
                new RentalObject(8, "ClothC3", "CategoryC",
                    new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1), mDummySizeInfoList.get(2) }, 500));*/
            /*this.mRentalList.add(new Rental(0, this.mMemberList.get(0), this.mRentalObjectList.get(1),
                    this.mRentalObjectList.get(1).availableSizeInfo()[0],
                    LocalDate.of(2018, Month.JULY, 1), LocalDate.of(2018, Month.JULY, 4),
                    null, 0));*/
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
            
            for (Rental rental : this.db.mRentalList) {
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
           
            for ( Reservation res : this.db.mReservationList) {
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
    
    public void readData() {
        ;;
    }
}
