
/* LoginView.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class LoginView extends DialogBase
{
    private static final long serialVersionUID = -7678380755203024447L;
    
    private static final int DefaultWindowWidth = 480;
    private static final int DefaultWindowHeight = 320;
    
    private static final String DefaultWindowTitle = "Login";
    
    private JLabel mLabelUserId;
    private JTextField mTextBoxUserId;
    private JLabel mLabelPassword;
    private JTextField mTextBoxPassword;
    
    private JButton mButtonLogin;
    private JButton mButtonCancel;
    
    private InputValidator<LoginView> mInputValidator;
    
    public LoginView(JFrame parentFrame)
    {
        super(parentFrame);
        
        this.setMinimumSize(new Dimension(
            LoginView.DefaultWindowWidth, LoginView.DefaultWindowHeight));
        this.setTitle(LoginView.DefaultWindowTitle);
        this.setLocationRelativeTo(this.getParent());
    }
    
    @Override
    protected void initializeComponent()
    {
        super.initializeComponent();
        
        /* Insets */
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        
        /* Header */
        this.setHeaderTitle("Login");
        this.setHeaderDescription("Login to Javaiina - Costume rental management system.");
        
        /* Center */
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* User Id Label */
        // TODO: An Email address may be used as a user id
        this.mLabelUserId = new JLabel("Email Address: ");
        this.mLabelUserId.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelUserId, layoutConstraints);
        
        /* User Id TextBox */
        this.mTextBoxUserId = new JTextField();
        this.mTextBoxUserId.setHorizontalAlignment(JTextField.LEFT);
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxUserId, layoutConstraints);
        
        /* Password Label */
        this.mLabelPassword = new JLabel("Password: ");
        this.mLabelPassword.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 1, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelPassword, layoutConstraints);
        
        /* Password TextBox */
        this.mTextBoxPassword = new JTextField();
        this.mTextBoxPassword.setHorizontalAlignment(JTextField.LEFT);
        this.setLayoutConstraints(
            layoutConstraints, 1, 1, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mTextBoxPassword, layoutConstraints);
        
        /* Dummy Panel for padding */
        this.setLayoutConstraints(
            layoutConstraints, 0, GridBagConstraints.RELATIVE, 0.0, 1.0,
            emptyInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
        
        /* Bottom */
        /* Dummy Panel for padding */
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelBottom.add(new JPanel(), layoutConstraints);
        
        /* Login Button */
        this.mButtonLogin = new JButton("Login");
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonLogin, layoutConstraints);
        this.getRootPane().setDefaultButton(this.mButtonLogin);
        
        /* Cancel Button */
        this.mButtonCancel = new JButton("Cancel");
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonCancel, layoutConstraints);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
        
        this.mButtonLogin.addActionListener(e -> this.onButtonLoginClick());
        this.mButtonCancel.addActionListener(e -> this.onButtonCancelClick());
    }
    
    private void onButtonLoginClick()
    {
        if (!this.mInputValidator.validateInput(this)) {
            JOptionPane.showMessageDialog(
                this, this.mInputValidator.getMessage(),
                LoginView.DefaultWindowTitle, JOptionPane.ERROR_MESSAGE);
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
    
    public void setInputValidator(InputValidator<LoginView> inputValidator)
    {
        this.mInputValidator = inputValidator;
    }
    
    public String getUserId()
    {
        return this.mTextBoxUserId.getText();
    }
    
    public String getPassword()
    {
        return this.mTextBoxPassword.getText();
    }
}
