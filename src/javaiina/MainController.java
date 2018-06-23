
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
        
        this.mView.addLogInListener(new LogInListener());
        this.mView.addMemberRegisterListener(new MemberRegisterListener());
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
            
            // Call MainController.setLoggedInMember() with null because no one has logged in
            MainController.this.mModel.setLoggedInMember(null);
        }
    }
}
