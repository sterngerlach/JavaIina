
/* Member.java */

package javaiina;

import java.time.LocalDate;

public class Member
{
    private long mId;
    private String mFirstName;
    private String mSecondName;
    private String mFirstNameKana;
    private String mSecondNameKana;
    private String mNickName;
    private LocalDate mBirthDate;
    private LocalDate mRegisterDate;
    private Gender mGender;
    private String mAddress;
    private String mPostcode;
    private String mPhoneNumber;
    private String mEmailAddress;
    
    public long id() { return this.mId; }
    public String firstName() { return this.mFirstName; }
    public String secondName() { return this.mSecondName; }
    public String firstNameKana() { return this.mFirstNameKana; }
    public String secondNameKana() { return this.mSecondNameKana; }
    public String nickName() { return this.mNickName; }
    public LocalDate birthDate() { return this.mBirthDate; }
    public LocalDate registerDate() { return this.mRegisterDate; }
    public Gender gender() { return this.mGender; }
    public String address() { return this.mAddress; }
    public String postcode() { return this.mPostcode; }
    public String phoneNumber() { return this.mPhoneNumber; }
    public String emailAddress() { return this.mEmailAddress; }
    
    public Member(
        long id, String firstName, String secondName,
        String firstNameKana, String secondNameKana, String nickName,
        LocalDate birthDate, LocalDate registerDate,
        Gender gender, String address, String postcode,
        String phoneNumber, String emailAddress)
    {
        this.mId = id;
        this.mFirstName = firstName;
        this.mSecondName = secondName;
        this.mFirstNameKana = firstNameKana;
        this.mSecondNameKana = secondNameKana;
        this.mNickName = nickName;
        this.mBirthDate = birthDate;
        this.mRegisterDate = registerDate;
        this.mGender = gender;
        this.mAddress = address;
        this.mPostcode = postcode;
        this.mPhoneNumber = phoneNumber;
        this.mEmailAddress = emailAddress;
    }

    @Override
    public String toString()
    {
        return String.format(
            "Member [Id: %s, FirstName: %s, SecondName: %s, " +
            "FirstNameKana: %s, SecondNameKana: %s, NickName: %s, " +
            "BirthDate: %s, RegisterDate: %s, Gender: %s, " +
            "Address: %s, Postcode: %s, " +
            "PhoneNumber: %s, EmailAddress: %s]",
            this.mId, this.mFirstName, this.mSecondName,
            this.mFirstNameKana, this.mSecondNameKana,
            this.mNickName, this.mBirthDate, this.mRegisterDate,
            this.mGender, this.mAddress, this.mPostcode,
            this.mPhoneNumber, this.mEmailAddress);
    } 
}
