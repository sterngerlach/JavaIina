
/* MainFrame.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class MainView extends JFrame
{
    private static final long serialVersionUID = 610819827986115380L;
    
    private static final int DefaultWindowWidth = 960;
    private static final int DefaultWindowHeight = 640;
    
    private static final String StatusBarReadyMessage = "Ready";
    
    private MainModel mModel;
    
    private JPanel mPanelToolBar;
    private JMenuBar mMenuBar;
    private JToolBar mToolBarMenu;
    private JMenu mMenuFile;
    private JMenuItem mMenuItemExit;
    
    private JToolBar mToolBarMain;
    private JButton mButtonLogIn;
    private JButton mButtonRegisterMember;
    
    private JPanel mPanelStatusBar;
    private JLabel mLabelStatusBar;
    
    public MainView(MainModel mainModel, String title)
    {
        this.mModel = mainModel;
        
        this.initializeComponent();
        
        this.setSize(MainView.DefaultWindowWidth, MainView.DefaultWindowHeight);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private void initializeComponent()
    {
        /* Menu and Toolbar container */
        this.mPanelToolBar = new JPanel();
        this.mPanelToolBar.setLayout(new BoxLayout(this.mPanelToolBar, BoxLayout.Y_AXIS));
        this.getContentPane().add(this.mPanelToolBar, BorderLayout.NORTH);
        
        this.mToolBarMenu = new JToolBar();
        this.mToolBarMenu.setFloatable(true);
        this.mToolBarMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.mPanelToolBar.add(this.mToolBarMenu);
        
        /* Menubar */
        this.mMenuBar = new JMenuBar();
        this.mToolBarMenu.add(this.mMenuBar);
        
        this.mMenuFile = new JMenu("File");
        this.mMenuFile.setMnemonic(KeyEvent.VK_F);
        this.mMenuBar.add(this.mMenuFile);
        
        this.mMenuItemExit = new JMenuItem("Exit");
        this.mMenuItemExit.setMnemonic(KeyEvent.VK_X);
        this.mMenuItemExit.addActionListener(e -> {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        this.mMenuFile.add(this.mMenuItemExit);
        
        /* Main Toolbar */
        this.mToolBarMain = new JToolBar();
        this.mToolBarMain.setFloatable(true);
        this.mToolBarMain.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.mPanelToolBar.add(this.mToolBarMain);
        
        this.mButtonLogIn = new JButton("Login");
        this.mButtonLogIn.setMnemonic(KeyEvent.VK_L);
        this.mToolBarMain.add(this.mButtonLogIn);
        
        this.mButtonRegisterMember = new JButton("Member Registration");
        this.mButtonRegisterMember.setMnemonic(KeyEvent.VK_R);
        this.mToolBarMain.add(this.mButtonRegisterMember);
        
        /* Statusbar */
        this.mPanelStatusBar = new JPanel();
        this.mPanelStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.mPanelStatusBar.setLayout(new BoxLayout(this.mPanelStatusBar, BoxLayout.X_AXIS));
        this.getContentPane().add(this.mPanelStatusBar, BorderLayout.SOUTH);
        
        this.mLabelStatusBar = new JLabel(MainView.StatusBarReadyMessage);
        this.mLabelStatusBar.setHorizontalAlignment(SwingConstants.LEFT);
        this.mLabelStatusBar.setVerticalAlignment(SwingConstants.CENTER);
        this.mPanelStatusBar.add(this.mLabelStatusBar);
    }
    
    public void addMemberRegisterListener(ActionListener actionListener)
    {
        this.mButtonRegisterMember.addActionListener(actionListener);
    }
    
    public void addLogInListener(ActionListener actionListener)
    {
        this.mButtonLogIn.addActionListener(actionListener);
    }
}
