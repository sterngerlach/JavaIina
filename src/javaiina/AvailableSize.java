
/* AvailableSize.java */

package javaiina;

public class AvailableSize
{
    private RentalObject mRentalObject;
    private RentalObjectSizeInfo mRentalObjectSizeInfo;
    
    public AvailableSize(RentalObject rentalObject, RentalObjectSizeInfo sizeinfo)
    {
        this.mRentalObject = rentalObject;
        this.mRentalObjectSizeInfo = sizeinfo;
    }
    
    public RentalObject getRentalObject() { return this.mRentalObject; }
    public RentalObjectSizeInfo getSizeInfo() { return this.mRentalObjectSizeInfo; }
}
