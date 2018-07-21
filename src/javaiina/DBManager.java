
/* DBManager.java */

package javaiina;

import java.time.LocalDate;
import java.util.*;

public class DBManager {
    
    /* replace to Derby later */
    private class DB {
        private List<AvailableSize> mAvailableSizeList;
        private List<Member> mMemberList;
        private List<String> mPasswordList;
        private List<Rental> mRentalList;
        private List<RentalObject> mRentalObjectList;
        private List<RentalObjectSizeInfo> mRentalObjectSizeInfoList;
        private List<Reservation> mReservationList;
        
        public DB() {
            /*
            this.mAvailableSizeList = new ArrayList<>();
            this.mMemberList = new ArrayList<>();
            this.mRentalList = new ArrayList<>();
            this.mRentalObjectList = new ArrayList<>();
            this.mRentalObjectSizeInfoList = new ArrayList<>();
            
            this.mAvailableSizeList.add(new AvailableSize())
            */
        	this.mMemberList = Arrays.asList(
        	        new Member(0, "達郎", "山下", "たつろう", "やました", "音の職人",
        	            LocalDate.of(1953, 2, 4), LocalDate.of(1973, 4, 1),
        	            Gender.MALE, "東京都世田谷区", "100-0000", "000-0000-0000", "aaa@bbb.com", "aa"),
        	        new Member(1, "龍一", "坂本", "りゅういち", "さかもと", "教授 ",
        	            LocalDate.of(1952, 1, 17), LocalDate.of(1978, 4, 1),
        	            Gender.MALE, "東京都中野区", "200-0000", "001-0000-0000", "ccc@ddd.com","bb"),
        	        new Member(2, "晴臣", "細野", "はるおみ", "ほその", "Harry",
        	            LocalDate.of(1947, 7, 9), LocalDate.of(1969, 4, 1),
        	            Gender.MALE, "東京都港区", "300-0000", "002-0000-0000", "eee@fff.com","cc"),
        	        new Member(0, "顕子", "矢野", "あきこ", "やの", "アッコちゃん",
        	            LocalDate.of(1955, 2, 13), LocalDate.of(1976, 4, 1),
        	            Gender.FEMALE, "青森県青森市", "400-0000", "003-0000-0000", "ggg@hhh.com", "dd"));
            
        }
        
        public Member selectMemberWhereEmailAdressAndPassword(String emailAdress, String password) {
            for (int i = 0; i < this.mMemberList.size(); ++i) {
                if (this.mMemberList.get(i).emailAddress().equals(emailAdress) &&
                    this.mMemberList.get(i).password().equals(password)) { 
                    return this.mMemberList.get(i);
                }
            }
            return null;
        }
        
        public void insert(Member member) {
            this.mMemberList.add(member);
        }
        
        public List<RentalObject> selectRentalObjectWhereMemberId(Member member) {
            List<RentalObject> roList = new ArrayList<>();
            
            for (int i = 0; i < this.mRentalList.size(); ++i) {
                if (this.mRentalList.get(i).getMember().id() == member.id()) {
                    roList.add(this.mRentalList.get(i).getRentalObject());
                }
            }

            return roList;
        }
        
        public List<RentalObject> selectRentalObjectList() { 
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
                if (this.mRentalObjectList.get(i).name().toLowerCase().equals(name.toLowerCase())) {
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
                    && this.mRentalObjectSizeInfoList.get(i).id() == sizeInfo.id()) {
                    rList.add(this.mRentalList.get(i));
                }
            }
            return rList;
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
    
    public Member login(String emailAdress, String password) {
        return this.db.selectMemberWhereEmailAdressAndPassword(emailAdress, password);
    }
    
    public void addMember(Member member) {
        this.db.insert(member);
    }
    
    public List<RentalObject> nowRetalingObjects(Member member) {
        return this.db.selectRentalObjectWhereMemberId(member);
    }
    
    public List<RentalObject> allRentalObjects() {
        return this.db.selectRentalObjectList();
    }
    
    public List<RentalObject> searchByCategoryName(String categoryName) {
        return this.db.selectRentalObjectWhereCategory(categoryName);
    }
    
    public List<RentalObject> searchByName(String name) {
        return this.db.selectRentalObjectWhereName(name);
    }
    
    public List<RentalObject> searchByNameAndCategory(String name, String categoryName) {
        List<RentalObject> roList = new ArrayList<>();
        List<RentalObject> roList_match_name = this.db.selectRentalObjectWhereName(name);
        
        for (int i = 0; i < roList_match_name.size(); ++i) {
            if (roList_match_name.get(i).categoryName().toLowerCase().equals(categoryName.toLowerCase())) {
                roList.add(roList_match_name.get(i));
            }
        }
        return roList;
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
    
    public void Return(Rental rental, LocalDate actualReturnDate, int overduePayment) {
        this.db.updateRental(rental, actualReturnDate, overduePayment);
        List<Reservation> resList = this.db.selectReservationWhereRentalObjectAndSizeInfo(rental.getRentalObject(), rental.getSizeInfo());
        if (resList.size() > 0) {
            this.Rental(resList.get(0).member(), rental.getRentalObject(), rental.getSizeInfo(), actualReturnDate, actualReturnDate.plusWeeks(2));
            this.db.updateReservationDone(resList.get(0));
        } else {
            ;
        }
        
    }
    
}
