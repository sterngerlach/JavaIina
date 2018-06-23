
/* RegisterMemberModel.java */

package javaiina;

import java.time.LocalDate;
import java.time.Month;

public class RegisterMemberViewModel
{
    public static final LocalDate MinBirthDate = LocalDate.of(1900, Month.JANUARY, 1);
    
    private Member mMember;
    
    public RegisterMemberViewModel()
    {
        this.reset();
    }
    
    public void reset()
    {
        this.mMember = null;
    }
}
