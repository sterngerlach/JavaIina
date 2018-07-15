
/* RentalObjectSizeInfo.java */

package javaiina;

public class RentalObjectSizeInfo
{
    private int mId;
    private String mSizeName;
    private int mWeight;
    private int mWaistMin;
    private int mWaistMax;
    private int mChestWidth;
    private int mShoulderWidth;
    private int mSleeveLength;
    private int mInseamLength;
    
    public int id() { return this.mId; }
    public String sizeName() { return this.mSizeName; }
    public int weight() { return this.mWeight; }
    public int waistMin() { return this.mWaistMin; }
    public int waistMax() { return this.mWaistMax; }
    public int chestWidth() { return this.mChestWidth; }
    public int shoulderWidth() { return this.mShoulderWidth; }
    public int sleeveLength() { return this.mSleeveLength; }
    public int inseamLength() { return this.mInseamLength; }
    
    public RentalObjectSizeInfo(
        int id, String sizeName, int weight,
        int waistMin, int waistMax,
        int chestWidth, int shoulderWidth,
        int sleeveLength, int inseamLength)
    {
        this.mId = id;
        this.mSizeName = sizeName;
        this.mWeight = weight;
        this.mWaistMin = waistMin;
        this.mWaistMax = waistMax;
        this.mChestWidth = chestWidth;
        this.mShoulderWidth = shoulderWidth;
        this.mSleeveLength = sleeveLength;
        this.mInseamLength = inseamLength;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s (%d kg, Waist: %d - %d cm)",
            this.mSizeName, this.mWeight, this.mWaistMin, this.mWaistMax);
    }
}
