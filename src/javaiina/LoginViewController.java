
/* LoginViewController.java */

package javaiina;

public class LoginViewController
{
    private LoginView mView;
    private LoginViewModel mModel;
    
    public LoginViewController(
        LoginView loginView, LoginViewModel loginViewModel)
    {
        this.mView = loginView;
        this.mModel = loginViewModel;
        
        this.mView.addLoginButtonClickListener(e -> {
            // TODO: Implement appropriate program for input sanity check
            // If user id and password are valid, close the dialog by calling this.mView.setVisible(false)
        });
        
        this.mView.addCancelButtonClickListener(e -> {
            // TODO: Call this.mView.setVisible(false) to close the dialog
        });
    }
}
