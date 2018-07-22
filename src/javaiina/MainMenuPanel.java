
/* MainMenuPanel.java */

package javaiina;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;

public class MainMenuPanel extends PanelBase
{
    private static final long serialVersionUID = 520562663600631546L;
    
    private MainModel mMainModel;
    
    private JButton mButtonShowItems;
    private JButton mButtonShowBorrowingItems;
    private JButton mButtonShowReservingItems;
    private JButton mButtonShowMemberInfo;
    private JButton mButtonLogout;
    
    @Override
    public String getPanelName()
    {
        return "Main Menu";
    }
    
    public MainMenuPanel()
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
        this.setHeaderTitle("Main Menu");
        this.setHeaderDescription("Welcome back!");
        
        /* Center */
        Font fontButton = new Font(Font.DIALOG, Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(300, 30);
        
        /* Show Items Button */
        this.mButtonShowItems = new JButton("Show Items");
        this.mButtonShowItems.setFont(fontButton);
        this.mButtonShowItems.setPreferredSize(buttonSize);
        this.mButtonShowItems.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonShowItems);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalStrut(10));
        
        /* Show Borrowing Items Button */
        this.mButtonShowBorrowingItems = new JButton("Show Borrowing Items");
        this.mButtonShowBorrowingItems.setFont(fontButton);
        this.mButtonShowBorrowingItems.setPreferredSize(buttonSize);
        this.mButtonShowBorrowingItems.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonShowBorrowingItems);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalStrut(10));
        
        /* Show Reserving Items Button */
        this.mButtonShowReservingItems = new JButton("Show Reserving Items");
        this.mButtonShowReservingItems.setFont(fontButton);
        this.mButtonShowReservingItems.setPreferredSize(buttonSize);
        this.mButtonShowReservingItems.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonShowReservingItems);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalStrut(10));
        
        /* Show Member Info Button */
        this.mButtonShowMemberInfo = new JButton("Show Member Information");
        this.mButtonShowMemberInfo.setFont(fontButton);
        this.mButtonShowMemberInfo.setPreferredSize(buttonSize);
        this.mButtonShowMemberInfo.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonShowMemberInfo);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalStrut(10));
        
        /* Logout Button */
        this.mButtonLogout = new JButton("Logout");
        this.mButtonLogout.setFont(fontButton);
        this.mButtonLogout.setPreferredSize(buttonSize);
        this.mButtonLogout.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonLogout);
        
        this.mPanelCenter.add(Box.createHorizontalGlue());
        this.mPanelCenter.add(Box.createVerticalGlue());
    }
    
    @Override
    protected void onPanelSelected()
    {
        this.setUserName(this.mMainModel.loggedInMember().nickName());
    }
    
    private void setUserName(String userName)
    {
        this.setHeaderDescription("Welcome back, " + userName + "!");
    }
    
    public void addShowItemsListener(ActionListener actionListener)
    {
        this.mButtonShowItems.addActionListener(actionListener);
    }
    
    public void addShowBorrowingItemsListener(ActionListener actionListener)
    {
        this.mButtonShowBorrowingItems.addActionListener(actionListener);
    }
    
    public void addShowReservingItemsListener(ActionListener actionListener)
    {
        this.mButtonShowReservingItems.addActionListener(actionListener);
    }
    
    public void addShowMemberInfoListener(ActionListener actionListener)
    {
        this.mButtonShowMemberInfo.addActionListener(actionListener);
    }
    
    public void addLogoutListener(ActionListener actionListener)
    {
        this.mButtonLogout.addActionListener(actionListener);
    }
}
