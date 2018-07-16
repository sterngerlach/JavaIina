
/* Reservation.java */

package javaiina;

import java.time.LocalDate;

public class Reservation
{
    private int mId;
    private Member mMember;
    private RentalObject mRentalObject;
    private RentalObjectSizeInfo mSizeInfo;
    private LocalDate mReservationDate;
    private boolean mDone;
	
    public Reservation(
        int id, Member member,
        RentalObject rentalObject, RentalObjectSizeInfo sizeInfo,
        LocalDate reservationDate, boolean done)
    {
        this.mId = id;
        this.mMember = member;
        this.mRentalObject = rentalObject;
        this.mSizeInfo = sizeInfo;
        this.mReservationDate = reservationDate;
        this.mDone = done;
    }
    
    public int id() { return this.mId; }
    public Member member() { return this.mMember; }
    public RentalObject rentalObject() { return this.mRentalObject; }
    public RentalObjectSizeInfo sizeInfo() { return this.mSizeInfo; }
    public LocalDate reservationDate() { return this.mReservationDate; }
    public boolean isDone() { return this.mDone; }

    @Override
    public String toString()
    {
        return String.format(
            "Reservation[ID: %d, Member ID: %d, RentalObject ID: %d," +
            "Size ID: %d, Reservation Date: %s, Done: %b]",
            this.mId, this.mMember.id(),
            this.mRentalObject.id(), this.mSizeInfo.id(),
            this.mReservationDate.toString(), this.mDone);
    }
}
