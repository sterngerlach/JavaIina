
/* Rental.java */

package javaiina;

import java.time.LocalDate;

public class Rental
{
    private long mId;
    private Member mMember;
    private RentalObject mRentalObject;
    private RentalObjectSizeInfo mSizeInfo;
    private LocalDate mBeginDate;
    private LocalDate mDesiredReturnDate;
    private LocalDate mActualReturnDate;
    private int mOverduePayment;
    
    public Rental(
        long id, Member member, RentalObject rentalObject,
        RentalObjectSizeInfo sizeInfo, 
        LocalDate beginDate, LocalDate desiredReturnDate,
        LocalDate actualReturnDate, int overduePayment)
    {
        this.mId = id;
        this.mMember = member;
        this.mRentalObject = rentalObject;
        this.mSizeInfo = sizeInfo;
        this.mBeginDate = beginDate;
        this.mDesiredReturnDate = desiredReturnDate;
        this.mActualReturnDate = actualReturnDate;
        this.mOverduePayment = overduePayment;
    }
    
    public long getId() { return this.mId; }
    public Member getMember() { return this.mMember; }
    public RentalObject getRentalObject() { return this.mRentalObject; }
    public RentalObjectSizeInfo getSizeInfo() { return this.mSizeInfo; }
    public LocalDate getBeginDate() { return this.mBeginDate; }
    public LocalDate getDesiredReturnDate() { return this.mDesiredReturnDate; }
    public LocalDate getActualReturnDate() { return this.mActualReturnDate; }
    public int getOverduePayment() { return this.mOverduePayment; }
    
    public void setActualReturnDate(LocalDate actualReturnDate)
    {
        this.mActualReturnDate = actualReturnDate;
    }
    
    public void setOverduePayment(int overduePayment)
    {
        this.mOverduePayment = overduePayment;
    }
}
