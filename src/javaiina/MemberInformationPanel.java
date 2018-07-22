
/* MemberInformationPanel.java */

package javaiina;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import javaiina.RentalListViewRenderer.RenderMode;

public class MemberInformationPanel extends PanelBase
{
    private static final long serialVersionUID = 6185003850060266183L;
    
    private MainModel mMainModel;
    
    private JLabel mLabelId;
    private JLabel mLabelIdValue;
    private JLabel mLabelFirstName;
    private JLabel mLabelFirstNameValue;
    private JLabel mLabelSecondName;
    private JLabel mLabelSecondNameValue;
    private JLabel mLabelFirstNameKana;
    private JLabel mLabelFirstNameKanaValue;
    private JLabel mLabelSecondNameKana;
    private JLabel mLabelSecondNameKanaValue;
    private JLabel mLabelNickName;
    private JLabel mLabelNickNameValue;
    private JLabel mLabelBirthDate;
    private JLabel mLabelBirthDateValue;
    private JLabel mLabelRegisterDate;
    private JLabel mLabelRegisterDateValue;
    private JLabel mLabelGender;
    private JLabel mLabelGenderValue;
    private JLabel mLabelAddress;
    private JLabel mLabelAddressValue;
    private JLabel mLabelPostcode;
    private JLabel mLabelPostcodeValue;
    private JLabel mLabelPhoneNumber;
    private JLabel mLabelPhoneNumberValue;
    private JLabel mLabelEmailAddress;
    private JLabel mLabelEmailAddressValue;
    
    private JLabel mLabelRecentlyBorrowedItems;
    private JList<Rental> mListViewRecentlyBorrowedItems;
    private DefaultListModel<Rental> mRecentlyBorrowedItemsModel;
    
    @Override
    public String getPanelName()
    {
        return "Member Info";
    }
    
    public MemberInformationPanel()
    {
    }
    
    public void setModel(MainModel mainModel)
    {
        this.mMainModel = mainModel;
    }
    
    @Override
    protected void initializeComponent()
    {
        super.initializeComponent();
        
        /* Header */
        this.setHeaderTitle("Member Information");
        
        /* Center Panel */
        this.mPanelCenter.setLayout(new GridBagLayout());
        
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Member Information */
        this.mLabelId = new JLabel("Id: ");
        this.addComponentLeft(layoutConstraints, 0, 0, defaultInsets, this.mLabelId);
        this.mLabelIdValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 0, defaultInsets, this.mLabelIdValue);
        
        this.mLabelFirstName = new JLabel("First Name: ");
        this.addComponentLeft(layoutConstraints, 0, 1, defaultInsets, this.mLabelFirstName);
        this.mLabelFirstNameValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 1, defaultInsets, this.mLabelFirstNameValue);
        
        this.mLabelSecondName = new JLabel("Second Name: ");
        this.addComponentLeft(layoutConstraints, 0, 2, defaultInsets, this.mLabelSecondName);
        this.mLabelSecondNameValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 2, defaultInsets, this.mLabelSecondNameValue);
        
        this.mLabelFirstNameKana = new JLabel("First Name (Kana): ");
        this.addComponentLeft(layoutConstraints, 0, 3, defaultInsets, this.mLabelFirstNameKana);
        this.mLabelFirstNameKanaValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 3, defaultInsets, this.mLabelFirstNameKanaValue);
        
        this.mLabelSecondNameKana = new JLabel("Second Name (Kana): ");
        this.addComponentLeft(layoutConstraints, 0, 4, defaultInsets, this.mLabelSecondNameKana);
        this.mLabelSecondNameKanaValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 4, defaultInsets, this.mLabelSecondNameKanaValue);
        
        this.mLabelNickName = new JLabel("Nickname: ");
        this.addComponentLeft(layoutConstraints, 0, 5, defaultInsets, this.mLabelNickName);
        this.mLabelNickNameValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 5, defaultInsets, this.mLabelNickNameValue);
        
        this.mLabelBirthDate = new JLabel("Birth Date: ");
        this.addComponentLeft(layoutConstraints, 0, 6, defaultInsets, this.mLabelBirthDate);
        this.mLabelBirthDateValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 6, defaultInsets, this.mLabelBirthDateValue);
        
        this.mLabelRegisterDate = new JLabel("Register Date: ");
        this.addComponentLeft(layoutConstraints, 0, 7, defaultInsets, this.mLabelRegisterDate);
        this.mLabelRegisterDateValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 7, defaultInsets, this.mLabelRegisterDateValue);
        
        this.mLabelGender = new JLabel("Gender: ");
        this.addComponentLeft(layoutConstraints, 0, 8, defaultInsets, this.mLabelGender);
        this.mLabelGenderValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 8, defaultInsets, this.mLabelGenderValue);
        
        this.mLabelAddress = new JLabel("Address: ");
        this.addComponentLeft(layoutConstraints, 0, 9, defaultInsets, this.mLabelAddress);
        this.mLabelAddressValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 9, defaultInsets, this.mLabelAddressValue);
        
        this.mLabelPostcode = new JLabel("Postcode: ");
        this.addComponentLeft(layoutConstraints, 0, 10, defaultInsets, this.mLabelPostcode);
        this.mLabelPostcodeValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 10, defaultInsets, this.mLabelPostcodeValue);
        
        this.mLabelPhoneNumber = new JLabel("Phone Number: ");
        this.addComponentLeft(layoutConstraints, 0, 11, defaultInsets, this.mLabelPhoneNumber);
        this.mLabelPhoneNumberValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 11, defaultInsets, this.mLabelPhoneNumberValue);
        
        this.mLabelEmailAddress = new JLabel("Email Address: ");
        this.addComponentLeft(layoutConstraints, 0, 12, defaultInsets, this.mLabelEmailAddress);
        this.mLabelEmailAddressValue = new JLabel();
        this.addComponentRight(layoutConstraints, 1, 12, defaultInsets, this.mLabelEmailAddressValue);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 13, 0.0, 1.0, defaultInsets, GridBagConstraints.VERTICAL);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
        
        /* Recently Borrowed Items ListView */
        this.mLabelRecentlyBorrowedItems = new JLabel("Recently Borrowed Items: ");
        this.addComponentLeft(layoutConstraints, 2, 0, defaultInsets, this.mLabelRecentlyBorrowedItems);
        
        this.mListViewRecentlyBorrowedItems = new JList<>();
        this.mRecentlyBorrowedItemsModel = new DefaultListModel<>();
        this.mListViewRecentlyBorrowedItems.setModel(this.mRecentlyBorrowedItemsModel);
        this.mListViewRecentlyBorrowedItems.setCellRenderer(
            new RentalListViewRenderer(RenderMode.BorrowingHistory));
        
        layoutConstraints.gridheight = 13;
        this.setLayoutConstraints(
            layoutConstraints, 2, 1, 1.0, 1.0, defaultInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(this.mListViewRecentlyBorrowedItems, layoutConstraints);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
    }
    
    @Override
    protected void onPanelSelected()
    {
        this.initializeView();
    }
    
    @Override
    protected void reloadView()
    {
        this.initializeView();
    }
    
    private void initializeView()
    {
        Member loggedInMember = this.mMainModel.loggedInMember();
        
        this.mLabelIdValue.setText(String.valueOf(loggedInMember.id()));
        this.mLabelFirstNameValue.setText(loggedInMember.firstName());
        this.mLabelSecondNameValue.setText(loggedInMember.secondName());
        this.mLabelFirstNameKanaValue.setText(loggedInMember.firstNameKana());
        this.mLabelSecondNameKanaValue.setText(loggedInMember.secondNameKana());
        this.mLabelNickNameValue.setText(loggedInMember.nickName());
        this.mLabelBirthDateValue.setText(loggedInMember.birthDate().toString());
        this.mLabelRegisterDateValue.setText(loggedInMember.registerDate().toString());
        this.mLabelGenderValue.setText(loggedInMember.gender().toString());
        this.mLabelAddressValue.setText(loggedInMember.address());
        this.mLabelPostcodeValue.setText(loggedInMember.postcode());
        this.mLabelPhoneNumberValue.setText(loggedInMember.phoneNumber());
        this.mLabelEmailAddressValue.setText(loggedInMember.emailAddress());
        
        this.mRecentlyBorrowedItemsModel.removeAllElements();
        this.mMainModel.getRecentlyBorrowedItems(5)
            .forEach(rentalInfo -> this.mRecentlyBorrowedItemsModel.addElement(rentalInfo));
    }
    
    private GridBagConstraints setLayoutConstraints(
        GridBagConstraints layoutConstraints,
        int gridx, int gridy, double weightx, double weighty, Insets insets, int fill)
    {
        layoutConstraints.gridx = gridx;
        layoutConstraints.gridy = gridy;
        layoutConstraints.weightx = weightx;
        layoutConstraints.weighty = weighty;
        layoutConstraints.insets = insets;
        layoutConstraints.fill = fill;
        
        return layoutConstraints;
    }
    
    private void addComponentLeft(
        GridBagConstraints layoutConstraints, int gridx, int gridy, Insets insets,
        JComponent newComponent)
    {
        this.setLayoutConstraints(
            layoutConstraints, gridx, gridy, 0.0, 0.0, insets, GridBagConstraints.NONE);
        this.mPanelCenter.add(newComponent, layoutConstraints);
    }
    
    private void addComponentRight(
        GridBagConstraints layoutConstraints, int gridx, int gridy, Insets insets,
        JComponent newComponent)
    {
        this.setLayoutConstraints(
            layoutConstraints, gridx, gridy, 0.0, 0.0, insets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(newComponent, layoutConstraints);
    }
}
