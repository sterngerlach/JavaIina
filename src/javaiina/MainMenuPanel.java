
/* MainMenuPanel.java */

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

public class MainMenuPanel extends JPanel
{
    private static final long serialVersionUID = 520562663600631546L;
    
    private JPanel mPanelHeader;
    private JPanel mPanelCenter;
    
    private Font mFontHeaderTitle;
    private JLabel mLabelHeaderTitle;
    private Font mFontHeaderDescription;
    private JLabel mLabelHeaderDescription;
    
    private JButton mButtonSearchAndBorrow;
    private JButton mButtonShowBorrowingItems;
    private JButton mButtonShowMemberInfo;
    private JButton mButtonLogout;
    
    public MainMenuPanel()
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
        this.mLabelHeaderTitle = new JLabel("Main Menu");
        this.mLabelHeaderTitle.setFont(this.mFontHeaderTitle);
        this.mPanelHeader.add(this.mLabelHeaderTitle);
        
        this.mPanelHeader.add(Box.createVerticalStrut(10));
        
        this.mFontHeaderDescription = new Font(Font.DIALOG, Font.PLAIN, 16);
        this.mLabelHeaderDescription = new JLabel("Welcome back!");
        this.mLabelHeaderDescription.setFont(this.mFontHeaderDescription);
        this.mPanelHeader.add(this.mLabelHeaderDescription);
        
        this.mPanelHeader.add(Box.createVerticalStrut(10));
        this.mPanelHeader.add(new JSeparator(JSeparator.HORIZONTAL));
        
        /* Center */
        Font fontButton = new Font(Font.DIALOG, Font.PLAIN, 16);
        Dimension buttonSize = new Dimension(300, 30);
        
        /* Search And Borrow Button */
        this.mButtonSearchAndBorrow = new JButton("Search & Borrow");
        this.mButtonSearchAndBorrow.setFont(fontButton);
        this.mButtonSearchAndBorrow.setPreferredSize(buttonSize);
        this.mButtonSearchAndBorrow.setMaximumSize(buttonSize);
        this.mPanelCenter.add(this.mButtonSearchAndBorrow);
        
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
    
    public void addSearchAndBorrowListener(ActionListener actionListener)
    {
        this.mButtonSearchAndBorrow.addActionListener(actionListener);
    }
    
    public void addShowBorrowingItemsListener(ActionListener actionListener)
    {
        this.mButtonShowBorrowingItems.addActionListener(actionListener);
    }
    
    public void addShowMemberInfoListener(ActionListener actionListener)
    {
        this.mButtonShowMemberInfo.addActionListener(actionListener);
    }
    
    public void addLogoutListener(ActionListener actionListener)
    {
        this.mButtonLogout.addActionListener(actionListener);
    }
    
    public void setUserName(String userName)
    {
        this.mLabelHeaderDescription.setText("Welcome back, " + userName + "!");
    }
}
