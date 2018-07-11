
/* MainModel.java */

package javaiina;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.EventListenerList;

public class MainModel
{
    public static final LocalDate MinBirthDate = LocalDate.of(1900, Month.JANUARY, 1);
    
    private EventListenerList mEventListenerList;
    private Member mLoggedInMember;
    
    // Generate sample data for debugging
    // This data makes absolutely no sense
    private List<String> mDummyCategoryList = Arrays.asList(
        "CategoryA", "CategoryB", "CategoryC");
    
    // This data makes absolutely no sense
    private List<RentalObjectSizeInfo> mDummySizeInfoList = Arrays.asList(
        new RentalObjectSizeInfo(0, "Y1", 45, 50, 55, 60, 70, 80, 80),
        new RentalObjectSizeInfo(1, "A2", 50, 55, 60, 70, 80, 90, 90),
        new RentalObjectSizeInfo(2, "B3", 55, 60, 65, 80, 90, 90, 90));
    
    // This data makes absolutely no sense
    private List<RentalObject> mDummyRentalObjectList = Arrays.asList(
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
            new RentalObjectSizeInfo[] { mDummySizeInfoList.get(1), mDummySizeInfoList.get(2) }, 500));
    
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
        this.reset();
    }
    
    public void reset()
    {
        this.mEventListenerList = null;
        this.mLoggedInMember = null;
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
    
    public List<String> getRentalCategoryList()
    {
        // TODO: Database access may be needed
        
        // Return sample list for debugging
        return this.mDummyCategoryList;
    }
    
    public List<RentalObject> filterRentalObjectByCategory(String categoryName)
    {
        // TODO: Database access may be needed
        
        // Return sample list for debugging
        return this.mDummyRentalObjectList.stream()
            .filter(rentalObject -> rentalObject.categoryName().equals(categoryName))
            .collect(Collectors.toList());
    }
    
    public List<RentalObject> filterRentalObjectByName(String itemName)
    {
        // TODO: Database access may be needed
        
        // Return sample list for debugging
        return this.mDummyRentalObjectList.stream()
            .filter(rentalObject -> rentalObject.name()
                .toLowerCase().contains(itemName.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public List<RentalObject> filterRentalObjectByNameAndCategory(String itemName, String categoryName)
    {
        // TODO: Database access may be needed
        
        // Return sample list for debugging
        return this.mDummyRentalObjectList.stream()
            .filter(rentalObject -> rentalObject.name()
                .toLowerCase().contains(itemName.toLowerCase()) &&
                rentalObject.categoryName().equals(categoryName))
            .collect(Collectors.toList());
    }
}
