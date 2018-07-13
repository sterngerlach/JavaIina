
/* Reservation.java */

package javaiina;

import java.time.LocalDate;

public class Reservation
{
    private Member mMember;
    private RentalObject mRentalObject;
    private LocalDate mReservationDate;
    private boolean mDone;
	
    public Reservation(
        Member member, RentalObject rentalObject,
        LocalDate reservationDate, boolean done)
    {
        this.mMember = member;
        this.mRentalObject = rentalObject;
        this.mReservationDate = reservationDate;
        this.mDone = done;
    }
    
    public Member Member() { return this.mMember; }
    public RentalObject RentalObject() { return this.mRentalObject; }
    public LocalDate ReservationDate() { return this.mReservationDate; }
    public boolean isDone() { return this.mDone; }

    @Override
    public String toString()
    {
        return String.format(
            "Reservation[Member ID: %s, RentalObject ID: %s," +
            "Reservation Date: %s, Done: %s]",
            this.mMember.id(), this.mRentalObject.id(),
            this.mReservationDate, this.mDone);
    }
}
