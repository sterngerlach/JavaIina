
/* PanelBase.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PanelBase extends JPanel
{
    private static final long serialVersionUID = 4738075437493970928L;
    
    protected JPanel mPanelHeader;
    protected JPanel mPanelCenter;
    
    protected int mDefaultBorderWidth;
    
    protected Font mFontHeaderTitle;
    protected JLabel mLabelHeaderTitle;
    protected Font mFontHeaderDescription;
    protected JLabel mLabelHeaderDescription;
    
    public String getPanelName()
    {
        return "";
    }
    
    public PanelBase()
    {
        this.initializeComponent();
        this.addEventHandler();
    }
    
    protected void initializeComponent()
    {
        /* Border */
        this.mDefaultBorderWidth = 10;
        
        /* Container */
        this.setLayout(new BorderLayout());
        
        this.mPanelHeader = new JPanel();
        this.mPanelHeader.setLayout(new BoxLayout(this.mPanelHeader, BoxLayout.Y_AXIS));
        this.mPanelHeader.setBorder(new EmptyBorder(
            this.mDefaultBorderWidth, this.mDefaultBorderWidth,
            this.mDefaultBorderWidth, this.mDefaultBorderWidth));
        this.add(this.mPanelHeader, BorderLayout.NORTH);
        
        this.mPanelCenter = new JPanel();
        this.mPanelCenter.setLayout(new BoxLayout(this.mPanelCenter, BoxLayout.Y_AXIS));
        this.mPanelCenter.setBorder(new EmptyBorder(
            0, this.mDefaultBorderWidth,
            this.mDefaultBorderWidth, this.mDefaultBorderWidth));
        this.add(this.mPanelCenter, BorderLayout.CENTER);
        
        /* Header */
        this.mFontHeaderTitle = new Font(Font.DIALOG, Font.BOLD, 18);
        this.mLabelHeaderTitle = new JLabel();
        this.mLabelHeaderTitle.setFont(this.mFontHeaderTitle);
        this.mPanelHeader.add(this.mLabelHeaderTitle);
        
        this.mPanelHeader.add(Box.createVerticalStrut(10));
        
        this.mFontHeaderDescription = new Font(Font.DIALOG, Font.PLAIN, 16);
        this.mLabelHeaderDescription = new JLabel();
        this.mLabelHeaderDescription.setFont(this.mFontHeaderDescription);
        this.mPanelHeader.add(this.mLabelHeaderDescription);
        
        this.mPanelHeader.add(Box.createVerticalStrut(10));
        this.mPanelHeader.add(new JSeparator(JSeparator.HORIZONTAL));
    }
    
    protected void addEventHandler()
    {
    }
    
    protected void setHeaderTitle(String headerTitle)
    {
        this.mLabelHeaderTitle.setText(headerTitle);
    }
    
    protected void setHeaderDescription(String headerDescription)
    {
        this.mLabelHeaderDescription.setText(headerDescription);
    }
}
