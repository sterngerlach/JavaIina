
/* Reservation.java */

package javaiina;

import java.time.LocalDate;

public class Reservation
{
    private Member mMember;
    private RentalObject mRentalObject;
    private RentalObjectSizeInfo mSizeInfo;
    private LocalDate mReservationDate;
    private boolean mDone;
	
    public Reservation(
        Member member, RentalObject rentalObject,
        RentalObjectSizeInfo sizeInfo,
        LocalDate reservationDate, boolean done)
    {
        this.mMember = member;
        this.mRentalObject = rentalObject;
        this.mSizeInfo = sizeInfo;
        this.mReservationDate = reservationDate;
        this.mDone = done;
    }
    
    public Member member() { return this.mMember; }
    public RentalObject rentalObject() { return this.mRentalObject; }
    public RentalObjectSizeInfo sizeInfo() { return this.mSizeInfo; }
    public LocalDate reservationDate() { return this.mReservationDate; }
    public boolean isDone() { return this.mDone; }

    @Override
    public String toString()
    {
        return String.format(
            "Reservation[Member ID: %s, RentalObject ID: %s," +
            "Size ID: %s, Reservation Date: %s, Done: %s]",
            this.mMember.id(), this.mRentalObject.id(),
            this.mSizeInfo.id(),
            this.mReservationDate, this.mDone);
    }
}
