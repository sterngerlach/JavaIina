
/* StartMenuPanel.java */

package javaiina;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

public class StartMenuPanel extends PanelBase
{
    private static final long serialVersionUID = 2107679826433799133L;
    
    private MainModel mMainModel;
    
    private JButton mButtonLogin;
    private JButton mButtonRegisterMember;
    
    @Override
    public String getPanelName()
    {
        return "Start";
    }
    
    public StartMenuPanel()
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
        this.setHeaderTitle("Welcome");
        this.setHeaderDescription("Welcome to Javaiina - Costume rental management system.");
        
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
