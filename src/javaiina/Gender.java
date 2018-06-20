
/* Gender.java */

package javaiina;

public enum Gender
{
    MALE,
    FEMALE,
    UNSPECIFIED;
    
    @Override
    public String toString()
    {
        switch (this) {
            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            case UNSPECIFIED:
                return "Unspecified";
            default:
                return null;
        }
    }
}
