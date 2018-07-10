
/* MainController.java */

package javaiina;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController
{
    private MainView mView;
    private MainModel mModel;
    
    public MainController(MainView mainView, MainModel mainModel)
    {
        this.mView = mainView;
        this.mModel = mainModel;
        
        this.mView.addLoginListener(new LogInListener());
        this.mView.addMemberRegisterListener(new MemberRegisterListener());
        this.mView.addLogoutListener(e -> this.performLogout());
        
        this.mView.switchToStartMenu();
        
        this.mModel.addModelListener(new ModelChangeListener());
    }
    
    private class MemberRegisterListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO: Implement appropriate program for member registration
            System.out.println("MemberRegisterListener::actionPerformed() called.");
            
            RegisterMemberViewModel memberModel = new RegisterMemberViewModel();
            RegisterMemberView memberView = new RegisterMemberView(MainController.this.mView, memberModel);
            RegisterMemberViewController memberController = new RegisterMemberViewController(memberView, memberModel);
            
            // Show the member registration dialog
            memberView.setModalityType(ModalityType.APPLICATION_MODAL);
            memberView.setVisible(true);
            
            // Call MainController.setLoggedInMember() with null because no one has logged in
            MainController.this.mModel.setLoggedInMember(null);
        }
    }
    
    private class LogInListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO: Implement appropriate program for login
            System.out.println("LogInListener::actionPerformed() called.");
            
            LoginViewModel loginViewModel = new LoginViewModel();
            LoginView loginView = new LoginView(MainController.this.mView, loginViewModel);
            LoginViewController loginViewController = new LoginViewController(loginView, loginViewModel);
            
            // Show the login dialog
            loginView.setModalityType(ModalityType.APPLICATION_MODAL);
            loginView.setVisible(true);
            
            // Switch to main menu if both user name and password are valid
            MainController.this.mView.switchToMainMenu();
            
            // Call MainController.setLoggedInMember() with null because no one has logged in
            MainController.this.mModel.setLoggedInMember(null);
        }
    }
    
    private class ModelChangeListener implements ModelListener
    {
        @Override
        public void modelChanged(ModelEvent e)
        {
            // TODO: Implement appropriate program for modelChanged event
            System.out.println("ModelChangeListener::modelChanged() called.");
            
            // For instance, you can change the title of the window
            // MainController.this.mView.setTitle(
            //     MainController.this.mModel.loggedInMember().nickName());
        }
    }
    
    private void performLogout()
    {
        // TODO: Implement program for logout
        System.out.println("MainController::performLogout() called.");
        
        // Switch to start menu
        MainController.this.mView.switchToStartMenu();
        
        // Call MainController.setLoggedInMember() with null because no one has logged in
        MainController.this.mModel.setLoggedInMember(null);
    }
}
