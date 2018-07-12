
/* Rental.java */

package javaiina;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rental {
    private Member mMember;
    private RentalObject mRentalObject;
    private LocalDate mBeginDate;
    private LocalDate mDesiredReturnDate;
    private LocalDate mActualReturnDate;
    private int mOverduePayment;
    
    public Rental(Member member, RentalObject rentalObject, 
                  LocalDate beginDate, LocalDate desiredReturnDate, LocalDate actualReturnDate,
                  int overduePayment)
    {
        this.mMember = member;
        this.mRentalObject = rentalObject;
        this.mBeginDate = beginDate;
        this.mDesiredReturnDate = desiredReturnDate;
        this.mActualReturnDate = actualReturnDate;
        this.mOverduePayment = overduePayment;
    }
    
    public Member getMember() { return this.mMember; }
    public RentalObject getRentalObject() { return this.mRentalObject;}
    public LocalDate getBeginDate() { return this.mBeginDate; }
    public LocalDate getDesiredReturnDate() { return this.mDesiredReturnDate; }
    public LocalDate getActualReturnDate() { return this.mActualReturnDate; }
    public int getOverduePayment() { return this.mOverduePayment; }
}
