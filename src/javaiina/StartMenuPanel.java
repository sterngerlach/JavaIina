
/* StartMenuPanel.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class StartMenuPanel extends JPanel
{
    private static final long serialVersionUID = 2107679826433799133L;
    
    private JPanel mPanelHeader;
    private JPanel mPanelCenter;
    
    private Font mFontHeaderTitle;
    private JLabel mLabelHeaderTitle;
    private Font mFontHeaderDescription;
    private JLabel mLabelHeaderDescription;
    
    private JButton mButtonLogin;
    private JButton mButtonRegisterMember;
    
    public StartMenuPanel()
    {
        this.initializeComponent();
    }
    
    private void initializeComponent()
    {
        /* Border */
        Border defaultBorder = new EmptyBorder(10, 10, 10, 10);
        
        /* Container */
        this.setLayout(new BorderLayout());
        
        this.mPanelHeader = new JPanel();
        this.mPanelHeader.setLayout(new BoxLayout(this.mPanelHeader, BoxLayout.Y_AXIS));
        this.mPanelHeader.setBorder(defaultBorder);
        this.add(this.mPanelHeader, BorderLayout.NORTH);
        
        this.mPanelCenter = new JPanel();
        this.mPanelCenter.setLayout(new BoxLayout(this.mPanelCenter, BoxLayout.Y_AXIS));
        this.mPanelCenter.setBorder(defaultBorder);
        this.add(this.mPanelCenter, BorderLayout.CENTER);
        
        /* Header */
        this.mFontHeaderTitle = new Font(Font.DIALOG, Font.BOLD, 18);
        this.mLabelHeaderTitle = new JLabel("Welcome");
        this.mLabelHeaderTitle.setFont(this.mFontHeaderTitle);
        this.mPanelHeader.add(this.mLabelHeaderTitle);
        
        this.mPanelHeader.add(Box.createVerticalStrut(10));
        
        this.mFontHeaderDescription = new Font(Font.DIALOG, Font.PLAIN, 16);
        this.mLabelHeaderDescription = new JLabel(
            "Welcome to Javaiina - Costume rental management system.");
        this.mLabelHeaderDescription.setFont(this.mFontHeaderDescription);
        this.mPanelHeader.add(this.mLabelHeaderDescription);
        
        this.mPanelHeader.add(Box.createVerticalStrut(10));
        this.mPanelHeader.add(new JSeparator(JSeparator.HORIZONTAL));
        
        /* Center */
        Font fontButton = new Font(Font.DIALOG, Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(300, 30);
        
        /* Login Button */
        this.mButtonLogin = new JButton("Login");
        this.mButtonLogin.setFont(fontButton);
        this.mButtonLogin.setPreferredSize(buttonSize);
        this.mButtonLogin.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonLogin);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalStrut(10));
        
        /* Member Registration Button */
        this.mButtonRegisterMember = new JButton("Member Registration");
        this.mButtonRegisterMember.setFont(fontButton);
        this.mButtonRegisterMember.setPreferredSize(buttonSize);
        this.mButtonRegisterMember.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonRegisterMember);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalGlue());
    }
    
    public void addRegisterMemberListener(ActionListener actionListener)
    {
        this.mButtonRegisterMember.addActionListener(actionListener);
    }
    
    public void addLoginListener(ActionListener actionListener)
    {
        this.mButtonLogin.addActionListener(actionListener);
    }
}
