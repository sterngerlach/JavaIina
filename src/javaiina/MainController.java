
/* MainController.java */

package javaiina;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
        this.mView.addTabSelectionChangeListener(e -> this.onTabSelectionChange());
        
        this.mView.getMainMenuPanel().addShowItemsListener(e -> this.onShowItems());
        this.mView.getMainMenuPanel().addShowBorrowingItemsListener(e -> this.onShowBorrowingItems());
        this.mView.getMainMenuPanel().addShowMemberInfoListener(e -> this.onShowMemberInfo());
        this.mView.getMainMenuPanel().addLogoutListener(e -> this.onLogout());
        
        this.mView.getItemsPanel().addBorrowItemListener(e -> this.onBorrowItem());
        this.mView.getBorrowingItemsPanel().addReturnItemListener(e -> this.onReturnItem());
        
        this.mView.switchToStartMenuPanel();
        
        this.mModel.addModelListener(new ModelChangeListener());
    }
    
    private class MemberRegisterListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO: Implement appropriate program for member registration
            System.out.println("MemberRegisterListener::actionPerformed() called.");

            // Show the member registration dialog
            RegisterMemberView memberView = new RegisterMemberView(MainController.this.mView);
            
            memberView.setInputValidator(new InputValidator<RegisterMemberView>() {
                @Override
                public boolean validateInput(RegisterMemberView userInput)
                {
                    /* Email Address Validation */
                    if (userInput.getMailAddress().isEmpty() || userInput.getMailAddressConfirm().isEmpty()) {
                        this.setMessage("Mail address is empty.");
                        return false;
                    }
                    
                    if (!userInput.getMailAddress().equals(userInput.getMailAddressConfirm())) {
                        this.setMessage("Mail addresses are not equal.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidEmailAddress(userInput.getMailAddress())) {
                        this.setMessage("Mail address is not valid.");
                        return false;
                    }
                    
                    /* Password Validation */
                    if (userInput.getPassword().isEmpty() || userInput.getPasswordConfirm().isEmpty()) {
                        this.setMessage("User password is empty.");
                        return false;
                    }
                    
                    if (!userInput.getPassword().equals(userInput.getPasswordConfirm())) {
                        this.setMessage("User passwords are not equal.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidUserPassword(userInput.getPassword())) {
                        this.setMessage("User password is not valid.");
                        return false;
                    }
                    
                    /* Name Validation */
                    if (userInput.getFirstName().isEmpty()) {
                        this.setMessage("First name is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidUserName(userInput.getFirstName())) {
                        this.setMessage("First name is not valid.");
                        return false;
                    }
                    
                    if (userInput.getSecondName().isEmpty()) {
                        this.setMessage("Second name is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidUserName(userInput.getSecondName())) {
                        this.setMessage("Second name is not valid.");
                        return false;
                    }
                    
                    /* Name (Kana) Validation */
                    if (userInput.getFirstNameKana().isEmpty()) {
                        this.setMessage("First name (Kana) is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidUserNameKana(userInput.getFirstNameKana())) {
                        this.setMessage("First name (Kana) is not valid.");
                        return false;
                    }
                    
                    if (userInput.getSecondNameKana().isEmpty()) {
                        this.setMessage("Second name (Kana) is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidUserNameKana(userInput.getSecondNameKana())) {
                        this.setMessage("Second name (Kana) is not valid.");
                        return false;
                    }
                    
                    /* NickName Validation */
                    if (userInput.getNickName().isEmpty()) {
                        this.setMessage("NickName is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidNickName(userInput.getNickName())) {
                        this.setMessage("NickName is not valid.");
                        return false;
                    }
                    
                    /* Gender Validation */
                    if (userInput.getSelectedGender() == null) {
                        this.setMessage("Gender is not selected.");
                        return false;
                    }
                    
                    /* BirthDate Validation */
                    if (userInput.getSelectedBirthDate() == null) {
                        this.setMessage("Birth date is not selected.");
                        return false;
                    }
                    
                    /* Postcode Validation */
                    if (userInput.getPostcode1().isEmpty() ||
                        userInput.getPostcode2().isEmpty()) {
                        this.setMessage("Postcode is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidPostcode1(userInput.getPostcode1()) ||
                        !MainController.this.mModel.isValidPostcode2(userInput.getPostcode2())) {
                        this.setMessage("Postcode is not valid.");
                        return false;
                    }
                    
                    /* Prefecture Validation */
                    if (userInput.getSelectedPrefecture() == null) {
                        this.setMessage("Prefecture is not selected.");
                        return false;
                    }
                    
                    /* Address Validation */
                    if (userInput.getAddress1().isEmpty() ||
                        userInput.getAddress2().isEmpty()) {
                        this.setMessage("Address is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidAddress(userInput.getAddress1()) ||
                        !MainController.this.mModel.isValidAddress(userInput.getAddress2())) {
                        this.setMessage("Address is not valid.");
                        return false;
                    }
                    
                    /* Phone Number Validation */
                    if (userInput.getPhoneNumberAreaCode().isEmpty() ||
                        userInput.getPhoneNumberSubscriber1().isEmpty() ||
                        userInput.getPhoneNumberSubscriber2().isEmpty()) {
                        this.setMessage("Phone number is empty.");
                        return false;
                    }
                    
                    if (!MainController.this.mModel.isValidPhoneNumber(userInput.getPhoneNumberAreaCode()) ||
                        !MainController.this.mModel.isValidPhoneNumber(userInput.getPhoneNumberSubscriber1()) ||
                        !MainController.this.mModel.isValidPhoneNumber(userInput.getPhoneNumberSubscriber2())) {
                        this.setMessage("Phone number is not valid.");
                        return false;
                    }
                    
                    return true;
                }
            });
            
            memberView.setModalityType(ModalityType.APPLICATION_MODAL);
            memberView.setVisible(true);
            
            if (memberView.getResult() != DialogResult.OK)
                return;
            
            // If the dialog is closed, all input fields are valid
            // Therefore, sanity check is not needed
            
            // TODO: Database access may be needed
            
            // Set dummy user
            Member dummyMember = MainController.this.mModel.getMemberInfo(1);
            MainController.this.mModel.setLoggedInMember(dummyMember);
            
            // Switch to main menu if all input fields are valid
            MainController.this.mView.switchToMainMenuPanel();
        }
    }
    
    private class LogInListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // TODO: Implement appropriate program for login
            System.out.println("LogInListener::actionPerformed() called.");

            // Show the login dialog
            LoginView loginView = new LoginView(MainController.this.mView);
            
            loginView.setInputValidator(new InputValidator<LoginView>() {
                @Override
                public boolean validateInput(LoginView userInput)
                {
                    String inputUserId = userInput.getUserId();
                    String inputUserPassword = userInput.getPassword();
                    
                    if (MainController.this.mModel.memberExists(inputUserId, inputUserPassword))
                        return true;
                    
                    this.setMessage("User id or password is incorrect.");
                    return false;
                }
            });
            
            loginView.setModalityType(ModalityType.APPLICATION_MODAL);
            loginView.setVisible(true);
            
            if (loginView.getResult() != DialogResult.OK)
                return;
            
            // TODO: Database access may be needed
            
            // Set dummy user
            Member dummyMember = MainController.this.mModel.getMemberInfo(1);
            MainController.this.mModel.setLoggedInMember(dummyMember);
            
            // Switch to main menu if both user name and password are valid
            MainController.this.mView.switchToMainMenuPanel();
        }
    }
    
    private class ModelChangeListener implements ModelListener
    {
        @Override
        public void modelChanged(ModelEvent e)
        {
            // Change the title of the window
            if (MainController.this.mModel.loggedInMember() != null)
                MainController.this.mView.setTitle("JavaIina - " +
                    MainController.this.mModel.loggedInMember().nickName());
            else
                MainController.this.mView.setTitle("JavaIina");
        }
    }
    
    private void onTabSelectionChange()
    {
        if (this.mView.getSelectedPanel() != null)
            this.mView.getSelectedPanel().onPanelSelected();
    }
    
    private void onShowItems()
    {
        // Switch to items panel
        this.mView.switchToItemsPanel();
    }
    
    private void onShowBorrowingItems()
    {
        // Switch to borrowing items panel
        this.mView.switchToBorrowingItemsPanel();
    }
    
    private void onShowMemberInfo()
    {
        // Switch to member information panel
        this.mView.switchToMemberInformationPanel();
    }
    
    private void onLogout()
    {
        // Switch to start menu
        this.mView.switchToStartMenuPanel();
        
        // Call MainController.setLoggedInMember() with null because no one has logged in
        this.mModel.setLoggedInMember(null);
    }
    
    private void onBorrowItem()
    {
        // TODO: Implement program for borrowing
        ItemsPanel itemsPanel = this.mView.getItemsPanel();
        RentalObject borrowedObject = itemsPanel.getSelectedRentalObject();
        
        if (borrowedObject == null)
            return;
        
        // Set the minimum date and the maximum date
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.now().plusWeeks(2);
        
        // Show the rental dialog
        RentalView rentalView = new RentalView(this.mView, minDate, maxDate);
        rentalView.setRentalObject(itemsPanel.getSelectedRentalObject());
        
        rentalView.setInputValidator(new InputValidator<RentalView>() {
            @Override
            public boolean validateInput(RentalView userInput)
            {
                // TODO: Validate user input
                // Both RentalView.getBeginDate() and RentalView.getDesiredReturnDate() might return null
                
                if (userInput.getSizeInfo() == null) {
                    this.setMessage("Size info is not selected.");
                    return false;
                }
                
                if (userInput.getBeginDate() == null) {
                    this.setMessage("Begin date is not selected.");
                    return false;
                }
                
                if (userInput.getDesiredReturnDate() == null) {
                    this.setMessage("Desired return date is not selected.");
                    return false;
                }
                
                if (userInput.getBeginDate().isEqual(userInput.getDesiredReturnDate()) ||
                    userInput.getBeginDate().isAfter(userInput.getDesiredReturnDate())) {
                    this.setMessage("Desired return date should be after the begin date.");
                    return false;
                }
                
                return true;
            }
        });
        
        rentalView.setModalityType(ModalityType.APPLICATION_MODAL);
        rentalView.setVisible(true);
        
        if (rentalView.getResult() != DialogResult.OK)
            return;
        
        // TODO: Database access may be needed
        // First you need to check if the user can borrow it or the user needs to reserve it
        // by checking Reservation Database and Rental Database
        
        // Maybe you will need to create a new class derived from InputValidator<RentalView>, 
        // whose constructor takes a MainModel object as an argument,
        // so that you can do the database access in the validateInput() method
        
        // TODO: Initialize a new Rental object or a new Reservation object
    }
    
    private void onReturnItem()
    {
        // TODO: Implement program for returning
        Rental returnedObjectInfo = this.mView.getBorrowingItemsPanel().getSelectedRental();
        
        if (returnedObjectInfo == null)
            return;
        
        // TODO: Implement program for calculating overdue payment
        int overduePayment = 100;
        
        // Show the return dialog
        ReturnView returnView = new ReturnView(this.mView);
        returnView.setReturnedObjectInfo(returnedObjectInfo, LocalDate.now(), overduePayment);
        returnView.setModalityType(ModalityType.APPLICATION_MODAL);
        returnView.setVisible(true);
        
        if (returnView.getResult() != DialogResult.OK)
            return;
        
        // TODO: Database access may be needed
        // Update Rental database (actualReturnDate, overduePayment)
        // Check Reservation database
        // If someone is waiting for the rental object, then he/she automatically borrows it
        
        // Reload borrowing items panel
        this.mView.getBorrowingItemsPanel().reloadView();
    }
}
