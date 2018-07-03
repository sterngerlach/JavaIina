
/* LoginView.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class LoginView extends JDialog
{
    private static final long serialVersionUID = -7678380755203024447L;
    
    private static final int DefaultWindowWidth = 480;
    private static final int DefaultWindowHeight = 320;
    
    private static final String DefaultWindowTitle = "Login";
    
    private LoginViewModel mModel;
    
    private JPanel mPanelHeader;
    private JPanel mPanelCenter;
    private JPanel mPanelBottom;
    
    private Font mFontHeaderTitle;
    private JLabel mLabelHeaderTitle;
    private Font mFontHeaderDescription;
    private JLabel mLabelHeaderDescription;
    
    private Font mFontLabel;
    
    private JLabel mLabelUserId;
    private JTextField mTextBoxUserId;
    private JLabel mLabelPassword;
    private JTextField mTextBoxPassword;
    
    private JButton mButtonLogin;
    private JButton mButtonCancel;
    
    public LoginView(JFrame parentFrame, LoginViewModel viewModel)
    {
        super(parentFrame);
        
        this.mModel = viewModel;
        
        this.initializeComponent();
        
        this.setMinimumSize(new Dimension(
            LoginView.DefaultWindowWidth, LoginView.DefaultWindowHeight));
        this.setTitle(LoginView.DefaultWindowTitle);
        this.setLocationRelativeTo(this.getParent());
    }
    
    private void initializeComponent()
    {
        /* Border */
        Border defaultBorder = new EmptyBorder(5, 5, 5, 5);
        
        /* Insets */
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        
        /* Container */
        this.mPanelHeader = new JPanel();
        this.mPanelHeader.setLayout(new BoxLayout(this.mPanelHeader, BoxLayout.Y_AXIS));
        this.mPanelHeader.setBorder(defaultBorder);
        this.getContentPane().add(this.mPanelHeader, BorderLayout.NORTH);
        
        this.mPanelCenter = new JPanel();
        this.mPanelCenter.setLayout(new GridBagLayout());
        this.getContentPane().add(this.mPanelCenter, BorderLayout.CENTER);
        
        this.mPanelBottom = new JPanel();
        this.mPanelBottom.setLayout(new GridBagLayout());
        this.getContentPane().add(this.mPanelBottom, BorderLayout.SOUTH);
        
        /* Header */
        this.mFontHeaderTitle = new Font(Font.DIALOG, Font.BOLD, 14);
        this.mLabelHeaderTitle = new JLabel();
        this.mLabelHeaderTitle.setText("Login");
        this.mLabelHeaderTitle.setFont(this.mFontHeaderTitle);
        this.mPanelHeader.add(this.mLabelHeaderTitle);
        
        this.mPanelHeader.add(Box.createVerticalStrut(5));
        
        this.mFontHeaderDescription = new Font(Font.DIALOG, Font.PLAIN, 12);
        this.mLabelHeaderDescription = new JLabel();
        this.mLabelHeaderDescription.setText(
            "Login to Javaiina - Costume rental management system.");
        this.mLabelHeaderDescription.setFont(this.mFontHeaderDescription);
        this.mPanelHeader.add(this.mLabelHeaderDescription);
        
        this.mPanelHeader.add(Box.createVerticalStrut(15));
        
        /* Center */
        this.mFontLabel = new Font(Font.DIALOG, Font.PLAIN, 12);
        
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
    
    public void addLoginButtonClickListener(ActionListener actionListener)
    {
        this.mButtonLogin.addActionListener(actionListener);
    }
    
    public void addCancelButtonClickListener(ActionListener actionListener)
    {
        this.mButtonCancel.addActionListener(actionListener);
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
