
/* RegisterMemberViewController.java */

package javaiina;

public class RegisterMemberViewController
{
    private RegisterMemberView mView;
    private RegisterMemberViewModel mModel;
    
    public RegisterMemberViewController(
        RegisterMemberView registerMemberView,
        RegisterMemberViewModel registerMemberViewModel)
    {
        this.mView = registerMemberView;
        this.mModel = registerMemberViewModel;
    }
}
