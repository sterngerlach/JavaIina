
/* RentalObject.java */

package javaiina;

public class RentalObject
{
    private int mId;
    private String mName;
    private String mCategoryName;
    private RentalObjectSizeInfo mSizeInfo;
    private int mCost;
    
    public int id() { return this.mId; }
    public String name() { return this.mName; }
    public String categoryName() { return this.mCategoryName; }
    public RentalObjectSizeInfo sizeInfo() { return this.mSizeInfo; }
    public int cost() { return this.mCost; }
    
    public RentalObject(
        int id, String name, String categoryName,
        RentalObjectSizeInfo sizeInfo, int cost)
    {
        this.mId = id;
        this.mName = name;
        this.mCategoryName = categoryName;
        this.mSizeInfo = sizeInfo;
        this.mCost = cost;
    }
}
