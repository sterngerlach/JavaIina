
/* RegisterMemberView.java */

package javaiina;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterMemberView extends DialogBase
{
    private static final long serialVersionUID = -3421065686754085314L;
    
    private static final int DefaultWindowWidth = 480;
    private static final int DefaultWindowHeight = 640;
    
    private static final String DefaultWindowTitle = "Member Registration";
    
    private JLabel mLabelMailAddress;
    private JTextField mTextBoxMailAddress;
    private JLabel mLabelMailAddressConfirm;
    private JTextField mTextBoxMailAddressComfirm;
    
    private JLabel mLabelPassword;
    private JTextField mTextBoxPassword;
    private JLabel mLabelPasswordConfirm;
    private JTextField mTextBoxPasswordConfirm;
    
    private JLabel mLabelName;
    private JPanel mPanelName;
    private JLabel mLabelFirstName;
    private JTextField mTextBoxFirstName;
    private JLabel mLabelSecondName;
    private JTextField mTextBoxSecondName;
    
    private JLabel mLabelNameKana;
    private JPanel mPanelNameKana;
    private JLabel mLabelFirstNameKana;
    private JTextField mTextBoxFirstNameKana;
    private JLabel mLabelSecondNameKana;
    private JTextField mTextBoxSecondNameKana;
    
    private JLabel mLabelNickName;
    private JTextField mTextBoxNickName;
    
    private JLabel mLabelGender;
    private GenderSelectionControl mGenderSelectionControl;
    
    private JLabel mLabelBirthDate;
    private DateSelectionControl mDateSelectionControl;
    
    private JLabel mLabelPostcode;
    private JPanel mPanelPostcode;
    private JTextField mTextBoxPostcode1;
    private JLabel mLabelPostcodeHyphen;
    private JTextField mTextBoxPostcode2;
    
    private JLabel mLabelPrefecture;
    private JComboBox<String> mComboBoxPrefecture;
    private JLabel mLabelAddress1;
    private JTextField mTextBoxAddress1;
    private JLabel mLabelAddress1Description;
    private JLabel mLabelAddress2;
    private JTextField mTextBoxAddress2;
    private JLabel mLabelAddress2Description;
    
    private JLabel mLabelPhoneNumber;
    private JPanel mPanelPhoneNumber;
    private JTextField mTextBoxPhoneNumberAreaCode;
    private JLabel mLabelPhoneNumberHyphen1;
    private JTextField mTextBoxPhoneNumberSubscriber1;
    private JLabel mLabelPhoneNumberHyphen2;
    private JTextField mTextBoxPhoneNumberSubscriber2;
    
    private JButton mButtonRegister;
    private JButton mButtonCancel;
    
    private InputValidator<RegisterMemberView> mInputValidator;
    
    public RegisterMemberView(JFrame parentFrame)
    {
        super(parentFrame);
        
        this.setMinimumSize(new Dimension(
            RegisterMemberView.DefaultWindowWidth, RegisterMemberView.DefaultWindowHeight));
        this.setTitle(RegisterMemberView.DefaultWindowTitle);
        this.setLocationRelativeTo(this.getParent());
    }
    
    @Override
    protected void initializeComponent()
    {
        super.initializeComponent();
        
        /* Insets */
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        Insets rightInsets = new Insets(0, 0, 0, 5);
        Insets leftInsets = new Insets(0, 5, 0, 0);
        
        /* Header */
        this.setHeaderTitle("Member Registration");
        this.setHeaderDescription(
            "Please fill out the following form completely to create an account.");

        /* Center */
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Mail Address Label */
        this.mLabelMailAddress = new JLabel();
        this.mLabelMailAddress.setText("Email Address: ");
        this.mLabelMailAddress.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelMailAddress, layoutConstraints);
        
        /* Mail Address TextBox */
        this.mTextBoxMailAddress = new JTextField();
        this.mTextBoxMailAddress.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxMailAddress, layoutConstraints);
        
        /* Mail Address Confirm Label */
        this.mLabelMailAddressConfirm = new JLabel();
        this.mLabelMailAddressConfirm.setText("Confirm Email Address: ");
        this.mLabelMailAddressConfirm.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 1, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelMailAddressConfirm, layoutConstraints);
        
        /* Mail Address Confirm TextBox */
        this.mTextBoxMailAddressComfirm = new JTextField();
        this.mTextBoxMailAddressComfirm.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 1, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxMailAddressComfirm, layoutConstraints);
        
        /* Password Label */
        this.mLabelPassword = new JLabel();
        this.mLabelPassword.setText("Password: ");
        this.mLabelPassword.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 2, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelPassword, layoutConstraints);
        
        /* Password TextBox */
        this.mTextBoxPassword = new JTextField();
        this.mTextBoxPassword.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 2, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxPassword, layoutConstraints);
        
        /* Password Confirm Label */
        this.mLabelPasswordConfirm = new JLabel();
        this.mLabelPasswordConfirm.setText("Confirm Password: ");
        this.mLabelPasswordConfirm.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 3, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelPasswordConfirm, layoutConstraints);
        
        /* Password Confirm TextBox */
        this.mTextBoxPasswordConfirm = new JTextField();
        this.mTextBoxPasswordConfirm.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 3, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxPasswordConfirm, layoutConstraints);
        
        /* Name Label */
        this.mLabelName = new JLabel();
        this.mLabelName.setText("Name");
        this.mLabelName.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 4, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelName, layoutConstraints);
        
        /* Name Panel */
        this.mPanelName = new JPanel();
        this.mPanelName.setLayout(new GridBagLayout());
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 4, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mPanelName, layoutConstraints);
        
        /* First Name Label */
        this.mLabelFirstName = new JLabel();
        this.mLabelFirstName.setText("First Name: ");
        this.mLabelFirstName.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelName.add(this.mLabelFirstName, layoutConstraints);
        
        /* First Name TextBox */
        this.mTextBoxFirstName = new JTextField();
        this.mTextBoxFirstName.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 1.0, 0.0, rightInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelName.add(this.mTextBoxFirstName, layoutConstraints);
        
        /* Second Name Label */
        this.mLabelSecondName = new JLabel();
        this.mLabelSecondName.setText("Second Name: ");
        this.mLabelSecondName.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelName.add(this.mLabelSecondName, layoutConstraints);
        
        /* Second Name TextBox */
        this.mTextBoxSecondName = new JTextField();
        this.mTextBoxSecondName.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 3, 0, 1.0, 0.0, emptyInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelName.add(this.mTextBoxSecondName, layoutConstraints);
        
        /* Name(Kana) Label */
        this.mLabelNameKana = new JLabel();
        this.mLabelNameKana.setText("Name (Kana)");
        this.mLabelNameKana.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 5, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelNameKana, layoutConstraints);
        
        /* Name(Kana) Panel */
        this.mPanelNameKana = new JPanel();
        this.mPanelNameKana.setLayout(new GridBagLayout());
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 5, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mPanelNameKana, layoutConstraints);
        
        /* First Name(Kana) Label */
        this.mLabelFirstNameKana = new JLabel();
        this.mLabelFirstNameKana.setText("First Name (Kana): ");
        this.mLabelFirstNameKana.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelNameKana.add(this.mLabelFirstNameKana, layoutConstraints);
        
        /* First Name(Kana) TextBox */
        this.mTextBoxFirstNameKana = new JTextField();
        this.mTextBoxFirstNameKana.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 1.0, 0.0, rightInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelNameKana.add(this.mTextBoxFirstNameKana, layoutConstraints);
        
        /* Second Name(Kana) Label */
        this.mLabelSecondNameKana = new JLabel();
        this.mLabelSecondNameKana.setText("Second Name (Kana): ");
        this.mLabelSecondNameKana.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelNameKana.add(this.mLabelSecondNameKana, layoutConstraints);
        
        /* Second Name(Kana) TextBox */
        this.mTextBoxSecondNameKana = new JTextField();
        this.mTextBoxSecondNameKana.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 3, 0, 1.0, 0.0, emptyInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelNameKana.add(this.mTextBoxSecondNameKana, layoutConstraints);
        
        /* Nick Name Label */
        this.mLabelNickName = new JLabel();
        this.mLabelNickName.setText("Nick Name: ");
        this.mLabelNickName.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 6, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelNickName, layoutConstraints);
        
        /* Nick Name TextBox */
        this.mTextBoxNickName = new JTextField();
        this.mTextBoxNickName.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 6, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxNickName, layoutConstraints);
        
        /* Gender Label */
        this.mLabelGender = new JLabel();
        this.mLabelGender.setText("Gender: ");
        this.mLabelGender.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 7, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelGender, layoutConstraints);
        
        /* Gender Selection Control */
        this.mGenderSelectionControl = new GenderSelectionControl();
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 7, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mGenderSelectionControl, layoutConstraints);
        
        /* Birthdate Label */
        this.mLabelBirthDate = new JLabel();
        this.mLabelBirthDate.setText("Birth Date: ");
        this.mLabelBirthDate.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 8, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelBirthDate, layoutConstraints);
        
        /* Date Selection Control */
        this.mDateSelectionControl = new DateSelectionControl(MainModel.MinBirthDate, LocalDate.now());
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 8, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mDateSelectionControl, layoutConstraints);
        
        /* Postcode Label */
        this.mLabelPostcode = new JLabel();
        this.mLabelPostcode.setText("Postcode: ");
        this.mLabelPostcode.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 9, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelPostcode, layoutConstraints);
        
        /* Postcode Panel */
        this.mPanelPostcode = new JPanel();
        this.mPanelPostcode.setLayout(new GridBagLayout());
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 9, 1.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mPanelPostcode, layoutConstraints);
        
        /* Postcode1 TextBox */
        this.mTextBoxPostcode1 = new JTextField();
        this.mTextBoxPostcode1.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 0.0, rightInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelPostcode.add(this.mTextBoxPostcode1, layoutConstraints);
        
        /* Postcode Hyphen Label */
        this.mLabelPostcodeHyphen = new JLabel();
        this.mLabelPostcodeHyphen.setText("-");
        this.mLabelPostcodeHyphen.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelPostcode.add(this.mLabelPostcodeHyphen, layoutConstraints);
        
        /* Postcode2 TextBox */
        this.mTextBoxPostcode2 = new JTextField();
        this.mTextBoxPostcode2.setHorizontalAlignment(JTextField.LEFT);
        this.mTextBoxPostcode2.setMinimumSize(new Dimension(150, this.mTextBoxPostcode2.getHeight()));
        
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 1.0, 0.0, emptyInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelPostcode.add(this.mTextBoxPostcode2, layoutConstraints);
        
        /* Prefecture Label */
        this.mLabelPrefecture = new JLabel();
        this.mLabelPrefecture.setText("Prefecture: ");
        this.mLabelPrefecture.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 10, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelPrefecture, layoutConstraints);
        
        /* Prefecture ComboBox */
        DefaultComboBoxModel<String> comboBoxPrefectureModel =
            new DefaultComboBoxModel<>(Prefecture.getPrefectureNames()); 
        comboBoxPrefectureModel.setSelectedItem(null);
        this.mComboBoxPrefecture = new JComboBox<>(comboBoxPrefectureModel);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 10, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mComboBoxPrefecture, layoutConstraints);

        /* Address1 Label */
        this.mLabelAddress1 = new JLabel();
        this.mLabelAddress1.setText("Address 1: ");
        this.mLabelAddress1.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 11, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelAddress1, layoutConstraints);
        
        /* Address1 TextBox */
        this.mTextBoxAddress1 = new JTextField();
        this.mTextBoxAddress1.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 11, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxAddress1, layoutConstraints);
        
        /* Dummy Panel */
        this.setLayoutConstraints(
            layoutConstraints, 0, 12, 0.0, 0.0, emptyInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
        
        /* Address1 Description Label */
        this.mLabelAddress1Description = new JLabel();
        this.mLabelAddress1Description.setText(
            "<html>"
            + "Enter the district and the municipality(city/town/village) name."
            + "</html>");
        this.mLabelAddress1Description.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 12, 1.0, 0.0, leftInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mLabelAddress1Description, layoutConstraints);
        
        /* Address2 Label */
        this.mLabelAddress2 = new JLabel();
        this.mLabelAddress2.setText("Address 2: ");
        this.mLabelAddress2.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 13, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelAddress2, layoutConstraints);
        
        /* Address2 TextBox */
        this.mTextBoxAddress2 = new JTextField();
        this.mTextBoxAddress2.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 13, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxAddress2, layoutConstraints);
        
        /* Dummy Panel */
        this.setLayoutConstraints(
            layoutConstraints, 0, 14, 0.0, 0.0, emptyInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
        
        /* Address2 Description Label */
        this.mLabelAddress2Description = new JLabel();
        this.mLabelAddress2Description.setText(
            "<html>"
            + "Enter the city number and the building number. "
            + "The apartment/condominium name and the room number "
            + "will also be required if you live in an apartment/condominium."
            + "</html>");
        this.mLabelAddress2Description.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 14, 1.0, 0.0, leftInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mLabelAddress2Description, layoutConstraints);
        
        /* Phone Number Label */
        this.mLabelPhoneNumber = new JLabel();
        this.mLabelPhoneNumber.setText("Phone Number: ");
        this.mLabelPhoneNumber.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 15, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelPhoneNumber, layoutConstraints);
        
        /* Phone Number Panel */
        this.mPanelPhoneNumber = new JPanel();
        this.mPanelPhoneNumber.setLayout(new GridBagLayout());
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 15, 1.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mPanelPhoneNumber, layoutConstraints);
        
        /* Phone Number(Area Code) TextBox */
        this.mTextBoxPhoneNumberAreaCode = new JTextField();
        this.mTextBoxPhoneNumberAreaCode.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 0.0, rightInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelPhoneNumber.add(this.mTextBoxPhoneNumberAreaCode, layoutConstraints);
        
        /* Phone Number Hyphen 1 Label */
        this.mLabelPhoneNumberHyphen1 = new JLabel();
        this.mLabelPhoneNumberHyphen1.setText("-");
        this.mLabelPhoneNumberHyphen1.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelPhoneNumber.add(this.mLabelPhoneNumberHyphen1, layoutConstraints);
        
        /* Phone Number(Subscriber Number 1) TextBox */
        this.mTextBoxPhoneNumberSubscriber1 = new JTextField();
        this.mTextBoxPhoneNumberSubscriber1.setHorizontalAlignment(JTextField.LEFT);
        
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 1.0, 0.0, rightInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelPhoneNumber.add(this.mTextBoxPhoneNumberSubscriber1, layoutConstraints);
        
        /* Phone Number Hyphen 2 Label */
        this.mLabelPhoneNumberHyphen2 = new JLabel();
        this.mLabelPhoneNumberHyphen2.setText("-");
        this.mLabelPhoneNumberHyphen2.setFont(this.mFontLabel);
        
        this.setLayoutConstraints(
            layoutConstraints, 3, 0, 0.0, 0.0, rightInsets, GridBagConstraints.NONE);
        this.mPanelPhoneNumber.add(this.mLabelPhoneNumberHyphen2, layoutConstraints);
        
        /* Phone Number(Subscriber Number 2) TextBox */
        this.mTextBoxPhoneNumberSubscriber2 = new JTextField();
        this.mTextBoxPhoneNumberSubscriber2.setHorizontalAlignment(JTextField.LEFT);
        this.mTextBoxPhoneNumberSubscriber2.setMinimumSize(
            new Dimension(150, this.mTextBoxPhoneNumberSubscriber2.getHeight()));
        
        this.setLayoutConstraints(
            layoutConstraints, 4, 0, 1.0, 0.0, emptyInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelPhoneNumber.add(this.mTextBoxPhoneNumberSubscriber2, layoutConstraints);
        
        /* Dummy Panel */
        this.setLayoutConstraints(
            layoutConstraints, 0, GridBagConstraints.RELATIVE, 0.0, 1.0,
            emptyInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
        
        /* Bottom */
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelBottom.add(new JPanel(), layoutConstraints);
        
        /* Register Button */
        this.mButtonRegister = new JButton();
        this.mButtonRegister.setText("Register");
        
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonRegister, layoutConstraints);
        this.getRootPane().setDefaultButton(this.mButtonRegister);
        
        /* Cancel Button */
        this.mButtonCancel = new JButton();
        this.mButtonCancel.setText("Cancel");
        
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonCancel, layoutConstraints);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
        
        this.mComboBoxPrefecture.addItemListener(
            e -> this.onComboBoxPrefectureSelectionChanged(e));
        this.mButtonRegister.addActionListener(
            e -> this.onButtonRegisterClick());
        this.mButtonCancel.addActionListener(
            e -> this.onButtonCancelClick());
    }
    
    private void onComboBoxPrefectureSelectionChanged(ItemEvent e)
    {
        if (e.getStateChange() != ItemEvent.SELECTED)
            return;
    }
    
    private void onButtonRegisterClick()
    {
        if (!this.mInputValidator.validateInput(this)) {
            JOptionPane.showMessageDialog(
                this, this.mInputValidator.getMessage(),
                RegisterMemberView.DefaultWindowTitle, JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.mResult = DialogResult.OK;
        this.setVisible(false);
    }
    
    private void onButtonCancelClick()
    {
        this.mResult = DialogResult.Cancel;
        this.setVisible(false);
    }
    
    public void setInputValidator(InputValidator<RegisterMemberView> inputValidator)
    {
        this.mInputValidator = inputValidator;
    }
    
    public String getMailAddress()
    {
        return this.mTextBoxMailAddress.getText();
    }
    
    public String getMailAddressConfirm()
    {
        return this.mTextBoxMailAddressComfirm.getText();
    }
    
    public String getPassword()
    {
        return this.mTextBoxPassword.getText();
    }
    
    public String getPasswordConfirm()
    {
        return this.mTextBoxPasswordConfirm.getText();
    }
    
    public String getFirstName()
    {
        return this.mTextBoxFirstName.getText();
    }
    
    public String getSecondName()
    {
        return this.mTextBoxSecondName.getText();
    }
    
    public String getFirstNameKana()
    {
        return this.mTextBoxFirstNameKana.getText();
    }
    
    public String getSecondNameKana()
    {
        return this.mTextBoxSecondNameKana.getText();
    }
    
    public String getNickName()
    {
        return this.mTextBoxNickName.getText();
    }
    
    public Gender getSelectedGender()
    {
        return this.mGenderSelectionControl.getSelectedGender();
    }
    
    public LocalDate getSelectedBirthDate()
    {
        return this.mDateSelectionControl.getSelectedDate();
    }
    
    public String getPostcode1()
    {
        return this.mTextBoxPostcode1.getText();
    }
    
    public String getPostcode2()
    {
        return this.mTextBoxPostcode2.getText();
    }
    
    public String getSelectedPrefecture()
    {
        return (String)this.mComboBoxPrefecture.getModel().getSelectedItem();
    }
    
    public String getAddress1()
    {
        return this.mTextBoxAddress1.getText();
    }
    
    public String getAddress2()
    {
        return this.mTextBoxAddress2.getText();
    }
    
    public String getPhoneNumberAreaCode()
    {
        return this.mTextBoxPhoneNumberAreaCode.getText();
    }
    
    public String getPhoneNumberSubscriber1()
    {
        return this.mTextBoxPhoneNumberSubscriber1.getText();
    }
    
    public String getPhoneNumberSubscriber2()
    {
        return this.mTextBoxPhoneNumberSubscriber2.getText();
    }
}
