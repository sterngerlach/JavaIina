
/* MainModel.java */

package javaiina;

public class MainModel
{
    private Member mLoggedInMember;
    
    public Member loggedInMember() { return this.mLoggedInMember; }
    public void setLoggedInMember(Member newValue) { this.mLoggedInMember = newValue; }
    
    public MainModel()
    {
        this.reset();
    }
    
    public void reset()
    {
        this.mLoggedInMember = null;
    }
}
