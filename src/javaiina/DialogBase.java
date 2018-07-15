
/* DialogBase.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class DialogBase extends JDialog
{
    private static final long serialVersionUID = 7337022313040820744L;
    
    protected DialogResult mResult;
    
    protected Border mDefaultBorder;
    
    protected JPanel mPanelHeader;
    protected JPanel mPanelCenter;
    protected JPanel mPanelBottom;
    
    protected Font mFontHeaderTitle;
    protected JLabel mLabelHeaderTitle;
    protected Font mFontHeaderDescription;
    protected JLabel mLabelHeaderDescription;
    
    protected Font mFontLabel;
    
    public DialogBase(JFrame parentFrame)
    {
        super(parentFrame);
        
        this.mResult = DialogResult.None;
        
        this.initializeComponent();
        this.addEventHandler();
    }
    
    protected void initializeComponent()
    {
        /* Border */
        this.mDefaultBorder = new EmptyBorder(5, 5, 5, 5);
        
        /* Container */
        this.mPanelHeader = new JPanel();
        this.mPanelHeader.setLayout(new BoxLayout(this.mPanelHeader, BoxLayout.Y_AXIS));
        this.mPanelHeader.setBorder(this.mDefaultBorder);
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
        this.mLabelHeaderTitle.setFont(this.mFontHeaderTitle);
        this.mPanelHeader.add(this.mLabelHeaderTitle);
        
        this.mPanelHeader.add(Box.createVerticalStrut(5));
        
        this.mFontHeaderDescription = new Font(Font.DIALOG, Font.PLAIN, 12);
        this.mLabelHeaderDescription = new JLabel();
        this.mLabelHeaderDescription.setFont(this.mFontHeaderDescription);
        this.mPanelHeader.add(this.mLabelHeaderDescription);
        
        this.mPanelHeader.add(Box.createVerticalStrut(15));
        this.mPanelHeader.add(new JSeparator());
        
        /* Center */
        this.mFontLabel = new Font(Font.DIALOG, Font.PLAIN, 12);
    }
    
    protected void addEventHandler()
    {
    }
    
    protected GridBagConstraints setLayoutConstraints(
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
    
    protected void setHeaderTitle(String headerTitle)
    {
        this.mLabelHeaderTitle.setText(headerTitle);
    }
    
    protected void setHeaderDescription(String headerDescription)
    {
        this.mLabelHeaderDescription.setText(headerDescription);
    }
    
    public DialogResult getResult()
    {
        return this.mResult;
    }
}
