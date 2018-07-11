
/* RentalObject.java */

package javaiina;

public class RentalObject
{
    private int mId;
    private String mName;
    private String mCategoryName;
    private RentalObjectSizeInfo[] mAvailableSizeInfo;
    private int mCost;
    
    public int id() { return this.mId; }
    public String name() { return this.mName; }
    public String categoryName() { return this.mCategoryName; }
    public RentalObjectSizeInfo[] availableSizeInfo() { return this.mAvailableSizeInfo; }
    public int cost() { return this.mCost; }
    
    public RentalObject(
        int id, String name, String categoryName,
        RentalObjectSizeInfo[] availableSizeInfo, int cost)
    {
        this.mId = id;
        this.mName = name;
        this.mCategoryName = categoryName;
        this.mAvailableSizeInfo = availableSizeInfo;
        this.mCost = cost;
    }
}
