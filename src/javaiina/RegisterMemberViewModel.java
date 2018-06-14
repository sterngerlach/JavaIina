
/* RegisterMemberModel.java */

package javaiina;

import java.time.ZonedDateTime;

public class RegisterMemberViewModel
{
    private String mFirstName;
    private String mSecondName;
    private String mFirstNameKana;
    private String mSecondNameKana;
    private String mNickName;
    private ZonedDateTime mBirthDate;
    private Gender mGender;
    private String mPhoneNumber;
    private String mEmailAddress;
    
    public String firstName() { return this.mFirstName; }
    public String secondName() { return this.mSecondName; }
    public String firstNameKana() { return this.mFirstNameKana; }
    public String secondNameKana() { return this.mSecondNameKana; }
    public String nickName() { return this.mNickName; }
    public ZonedDateTime birthDate() { return this.mBirthDate; }
    public Gender gender() { return this.mGender; }
    public String phoneNumber() { return this.mPhoneNumber; }
    public String emailAddress() { return this.mEmailAddress; }
    
    public RegisterMemberViewModel()
    {
        this.reset();
    }
    
    public void reset()
    {
        this.mFirstName = null;
        this.mSecondName = null;
        this.mFirstNameKana = null;
        this.mSecondNameKana = null;
        this.mNickName = null;
        this.mBirthDate = null;
        this.mGender = null;
        this.mPhoneNumber = null;
        this.mEmailAddress = null;
    }
}
