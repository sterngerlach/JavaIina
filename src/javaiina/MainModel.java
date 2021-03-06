
/* MainModel.java */

package javaiina;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.event.EventListenerList;

public class MainModel
{
    public static final LocalDate MinBirthDate = LocalDate.of(1900, Month.JANUARY, 1);
    
    private DBManager mDBManager;
    private EventListenerList mEventListenerList;
    private Member mLoggedInMember;
    
    // Generate sample data for debugging
    // This data makes absolutely no sense
    /* private List<Member> mDummyUserList = Arrays.asList(
        new Member(0, "達郎", "山下", "たつろう", "やました", "音の職人",
            LocalDate.of(1953, 2, 4), LocalDate.of(1973, 4, 1),
            Gender.MALE, "東京都世田谷区", "100-0000", "000-0000-0000", "aaa@bbb.com","aa"),
        new Member(1, "龍一", "坂本", "りゅういち", "さかもと", "教授 ",
            LocalDate.of(1952, 1, 17), LocalDate.of(1978, 4, 1),
            Gender.MALE, "東京都中野区", "200-0000", "001-0000-0000", "ccc@ddd.com","bb"),
        new Member(2, "晴臣", "細野", "はるおみ", "ほその", "Harry",
            LocalDate.of(1947, 7, 9), LocalDate.of(1969, 4, 1),
            Gender.MALE, "東京都港区", "300-0000", "002-0000-0000", "eee@fff.com","cc"),
        new Member(0, "顕子", "矢野", "あきこ", "やの", "アッコちゃん",
            LocalDate.of(1955, 2, 13), LocalDate.of(1976, 4, 1),
            Gender.FEMALE, "青森県青森市", "400-0000", "003-0000-0000", "ggg@hhh.com","dd")); */
        
    // This data makes absolutely no sense
    /* private List<String> mDummyCategoryList = Arrays.asList(
        "CategoryA", "CategoryB", "CategoryC"); */
    
    // This data makes absolutely no sense
    /* private List<RentalObjectSizeInfo> mDummySizeInfoList = Arrays.asList(
        new RentalObjectSizeInfo(0, "Y1", 150, 45, 50, 55, 60, 70, 80, 80),
        new RentalObjectSizeInfo(1, "A2", 155, 50, 55, 60, 70, 80, 90, 90),
        new RentalObjectSizeInfo(2, "B3", 160, 55, 60, 65, 80, 90, 90, 90)); */
    
    // This data makes absolutely no sense
    /* private List<RentalObject> mDummyRentalObjectList = Arrays.asList(
        new RentalObject(0, "ClothA1", "CategoryA",
            new RentalObjectSizeInfo[] { mDummySizeInfoList.get(0), mDummySizeInfoList.get(1) }, 100),
        new RentalObject(1, "ClothA2", "CategoryA",
            new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1) }, 150),
        new RentalObject(2, "ClothA3", "CategoryA",
            new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1), mDummySizeInfoList.get(2) }, 200),
        new RentalObject(3, "ClothB1", "CategoryB",
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
            new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1), mDummySizeInfoList.get(2) }, 500)); */
    
    // This data makes absolutely no sense
    /* private List<Rental> mDummyRentalList = Arrays.asList(
        new Rental(0, this.mDummyUserList.get(0), this.mDummyRentalObjectList.get(1),
            this.mDummyRentalObjectList.get(1).availableSizeInfo()[0],
            LocalDate.of(2018, Month.JULY, 1), LocalDate.of(2018, Month.JULY, 8),
            null, 0),
        new Rental(1, this.mDummyUserList.get(1), this.mDummyRentalObjectList.get(2),
            this.mDummyRentalObjectList.get(2).availableSizeInfo()[0],
            LocalDate.of(2018, Month.JULY, 2), LocalDate.of(2018, Month.JULY, 9),
            null, 0),
        new Rental(2, this.mDummyUserList.get(2), this.mDummyRentalObjectList.get(2),
            this.mDummyRentalObjectList.get(2).availableSizeInfo()[1],
            LocalDate.of(2018, Month.JULY, 3), LocalDate.of(2018, Month.JULY, 10),
            null, 0),
        new Rental(3, this.mDummyUserList.get(3), this.mDummyRentalObjectList.get(4),
            this.mDummyRentalObjectList.get(4).availableSizeInfo()[0],
            LocalDate.of(2018, Month.JULY, 4), LocalDate.of(2018, Month.JULY, 11),
            null, 0),
        new Rental(4, this.mDummyUserList.get(0), this.mDummyRentalObjectList.get(5),
            this.mDummyRentalObjectList.get(5).availableSizeInfo()[0],
            LocalDate.of(2018, Month.JULY, 5), LocalDate.of(2018, Month.JULY, 19),
            null, 0),
        new Rental(5, this.mDummyUserList.get(1), this.mDummyRentalObjectList.get(5),
            this.mDummyRentalObjectList.get(5).availableSizeInfo()[1],
            LocalDate.of(2018, Month.JULY, 6), LocalDate.of(2018, Month.JULY, 20),
            null, 0),
        new Rental(6, this.mDummyUserList.get(2), this.mDummyRentalObjectList.get(7),
            this.mDummyRentalObjectList.get(7).availableSizeInfo()[0],
            LocalDate.of(2018, Month.JULY, 7), LocalDate.of(2018, Month.JULY, 21),
            null, 0),
        new Rental(7, this.mDummyUserList.get(3), this.mDummyRentalObjectList.get(8),
            this.mDummyRentalObjectList.get(8).availableSizeInfo()[1],
            LocalDate.of(2018, Month.JULY, 8), LocalDate.of(2018, Month.JULY, 22),
            null, 0)); */
    
    public Member loggedInMember()
    {
        return this.mLoggedInMember;
    }
    
    public void setLoggedInMember(Member newValue)
    {
        this.mLoggedInMember = newValue;
        this.fireModelChanged();
    }
    
    public MainModel()
    {
        this.mDBManager = new DBManager();
        this.reset();
    }
    
    public void reset()
    {
        this.mEventListenerList = null;
        this.mLoggedInMember = null;
    }
    
    public DBManager getDBManager()
    {
        return this.mDBManager;
    }
    
    public void addModelListener(ModelListener modelListener)
    {
        if (this.mEventListenerList == null)
            this.mEventListenerList = new EventListenerList();
        
        this.mEventListenerList.add(ModelListener.class, modelListener);
    }
    
    public void removeEventListener(ModelListener modelListener)
    {
        if (this.mEventListenerList == null)
            return;
        
        this.mEventListenerList.remove(ModelListener.class, modelListener);
    }
    
    private void fireModelChanged()
    {
        if (this.mEventListenerList == null)
            return;
        
        ModelEvent modelEvent = new ModelEvent(this);
        ModelListener[] modelListeners = this.mEventListenerList.getListeners(ModelListener.class);
        
        for (ModelListener modelListener : modelListeners)
            modelListener.modelChanged(modelEvent);
    }
    
    public boolean isValidEmailAddress(String emailAddress)
    {
        return Pattern.matches("^(.+)@(.+)$", emailAddress);
    }
    
    public boolean isValidUserPassword(String userPassword)
    {
        return userPassword.chars().allMatch(ch -> 
            (('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'z') ||
                ('A' <= ch && ch <= 'Z') || ch == '_'));
    }
    
    public boolean isValidUserName(String userName)
    {
        return userName.chars().allMatch(ch -> Character.isLetterOrDigit(ch));
    }
    
    public boolean isValidUserNameKana(String name)
    {
        return Pattern.matches("^[ぁ-ゞー]+$", name);
    }
    
    public boolean isValidNickName(String nickName)
    {
        return nickName.chars().allMatch(ch -> Character.isLetterOrDigit(ch));
    }
    
    public boolean isValidPostcode1(String postcode1)
    {
        return Pattern.matches("^[0-9]{3}$", postcode1);
    }
    
    public boolean isValidPostcode2(String postcode2)
    {
        return Pattern.matches("^[0-9]{4}$", postcode2);
    }
    
    public boolean isValidAddress(String address)
    {
        return true;
    }
    
    public boolean isValidPhoneNumber(String phoneNumber)
    {
        return phoneNumber.chars().allMatch(ch -> Character.isDigit(ch));
    }
    
    public Member getMemberInfo(long userId)
    {
        return this.mDBManager.getMemberInfo(userId);
    }
    
    public boolean memberExists(String emailAddress, String userPassword)
    {
        return this.mDBManager.memberExists(emailAddress, userPassword);
    }
    
    public List<String> getRentalCategoryList()
    {
        List<RentalObject> rentalObjectList = this.mDBManager.allRentalObjects();
        Set<String> categorySet = new HashSet<>();
        rentalObjectList.forEach(rentalObject -> categorySet.add(rentalObject.categoryName()));
        return new ArrayList<String>(categorySet);
    }
    
    public List<RentalObject> filterRentalObjectByCategory(String categoryName)
    {
        return this.mDBManager.searchByCategoryName(categoryName);
    }
    
    public List<RentalObject> filterRentalObjectByName(String itemName)
    {
        return this.mDBManager.searchByName(itemName);
    }
    
    public List<RentalObject> filterRentalObjectByNameAndCategory(String itemName, String categoryName)
    {
        return this.mDBManager.searchByNameAndCategory(itemName, categoryName);
    }
    
    public List<Rental> getBorrowingItems()
    {
        return this.mDBManager.getBorrowingItems(this.mLoggedInMember);
    }
    
    public List<Reservation> getReservations()
    {
        return this.mDBManager.getReservations(this.mLoggedInMember);
    }
    
    public List<Rental> getRecentlyBorrowedItems(int maxSize)
    {
        return this.mDBManager.getBorrowedItems(this.mLoggedInMember).stream()
            .limit(maxSize).collect(Collectors.toList());
    }
}
