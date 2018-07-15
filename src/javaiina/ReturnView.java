
/* ReturnView.java */

package javaiina;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReturnView extends DialogBase
{
    private static final long serialVersionUID = 9166743959504396572L;
    
    private static final int DefaultWindowWidth = 480;
    private static final int DefaultWindowHeight = 320;
    
    private static final String DefaultWindowTitle = "Return";
    
    private Rental mReturnedObjectInfo;
    private LocalDate mActualReturnDate;
    private int mOverduePayment;
    
    private Font mFontLabelBold;
    private JLabel mLabelItemName;
    private JLabel mLabelItemNameValue;
    private JLabel mLabelItemSize;
    private JLabel mLabelItemSizeValue;
    private JLabel mLabelItemCost;
    private JLabel mLabelItemCostValue;
    private JLabel mLabelBeginDate;
    private JLabel mLabelBeginDateValue;
    private JLabel mLabelDesiredReturnDate;
    private JLabel mLabelDesiredReturnDateValue;
    private JLabel mLabelActualReturnDate;
    private JLabel mLabelActualReturnDateValue;
    private JLabel mLabelOverduePayment;
    private JLabel mLabelOverduePaymentValue;
    private DateTimeFormatter mDateFormatter;
    
    private JButton mButtonReturn;
    private JButton mButtonCancel;

    public ReturnView(JFrame parentFrame)
    {
        super(parentFrame);
        
        this.setMinimumSize(new Dimension(
            ReturnView.DefaultWindowWidth, ReturnView.DefaultWindowHeight));
        this.setTitle(ReturnView.DefaultWindowTitle);
        this.setLocationRelativeTo(this.getParent());
    }
    
    public void setReturnedObjectInfo(
        Rental returnedObjectInfo, LocalDate actualReturnDate, int overduePayment)
    {
        this.mReturnedObjectInfo = returnedObjectInfo;
        this.mActualReturnDate = actualReturnDate;
        this.mOverduePayment = overduePayment;
        
        this.mLabelItemNameValue.setText(returnedObjectInfo.getRentalObject().name());
        this.mLabelItemSizeValue.setText(returnedObjectInfo.getSizeInfo().sizeName());
        this.mLabelItemCostValue.setText(
            Integer.toString(returnedObjectInfo.getRentalObject().cost()) + " Yen/day");
        this.mLabelBeginDateValue.setText(
            returnedObjectInfo.getBeginDate().format(this.mDateFormatter));
        this.mLabelDesiredReturnDateValue.setText(
            returnedObjectInfo.getDesiredReturnDate().format(this.mDateFormatter));
        this.mLabelActualReturnDateValue.setText(actualReturnDate.format(this.mDateFormatter));
        this.mLabelOverduePaymentValue.setText(Integer.toString(overduePayment) + " Yen");
        
        if (returnedObjectInfo.getDesiredReturnDate().isBefore(actualReturnDate)) {
            this.mLabelDesiredReturnDateValue.setForeground(Color.RED);
            this.mLabelActualReturnDateValue.setForeground(Color.RED);
        }
        
        if (overduePayment > 0)
            this.mLabelOverduePaymentValue.setForeground(Color.RED);
    }
    
    @Override
    protected void initializeComponent()
    {
        super.initializeComponent();
        
        /* Insets */
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        
        /* Header */
        this.setHeaderTitle("Return");
        this.setHeaderDescription("");
        
        /* Center */
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Font */
        this.mFontLabelBold = new Font(Font.DIALOG, Font.BOLD, 12);
        
        /* Item Name Label */
        this.mLabelItemName = new JLabel("Item Name: ");
        this.mLabelItemName.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemName, layoutConstraints);
        
        this.mLabelItemNameValue = new JLabel();
        this.mLabelItemNameValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemNameValue, layoutConstraints);
        
        /* Item Size Label */
        this.mLabelItemSize = new JLabel("Item Size: ");
        this.mLabelItemSize.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 1, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemSize, layoutConstraints);
        
        this.mLabelItemSizeValue = new JLabel();
        this.mLabelItemSizeValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 1, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemSizeValue, layoutConstraints);
        
        /* Item Cost Label */
        this.mLabelItemCost = new JLabel("Item Cost: ");
        this.mLabelItemCost.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 2, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemCost, layoutConstraints);
        
        this.mLabelItemCostValue = new JLabel();
        this.mLabelItemCostValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 2, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemCostValue, layoutConstraints);
        
        /* Begin Date Label */
        this.mLabelBeginDate = new JLabel("Begin Date: ");
        this.mLabelBeginDate.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 3, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelBeginDate, layoutConstraints);
        
        this.mLabelBeginDateValue = new JLabel();
        this.mLabelBeginDateValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 3, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelBeginDateValue, layoutConstraints);
        
        /* Desired Return Date Label */
        this.mLabelDesiredReturnDate = new JLabel("Desired Return Date: ");
        this.mLabelDesiredReturnDate.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 4, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelDesiredReturnDate, layoutConstraints);
        
        this.mLabelDesiredReturnDateValue = new JLabel();
        this.mLabelDesiredReturnDateValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 4, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelDesiredReturnDateValue, layoutConstraints);
        
        /* Actual Return Date Label */
        this.mLabelActualReturnDate = new JLabel("Actual Return Date: ");
        this.mLabelActualReturnDate.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 5, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelActualReturnDate, layoutConstraints);
        
        this.mLabelActualReturnDateValue = new JLabel();
        this.mLabelActualReturnDateValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 5, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelActualReturnDateValue, layoutConstraints);
        
        /* Overdue Payment Label */
        this.mLabelOverduePayment = new JLabel("Overdue Payment: ");
        this.mLabelOverduePayment.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 6, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelOverduePayment, layoutConstraints);
        
        this.mLabelOverduePaymentValue = new JLabel();
        this.mLabelOverduePaymentValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 6, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelOverduePaymentValue, layoutConstraints);
        
        /* Dummy panel for padding */
        this.setLayoutConstraints(
            layoutConstraints, 0, GridBagConstraints.RELATIVE,
            1.0, 1.0, emptyInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
        
        /* Bottom */
        /* Dummy Panel for padding */
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelBottom.add(new JPanel(), layoutConstraints);
        
        /* Return Button */
        this.mButtonReturn = new JButton("Return");
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonReturn, layoutConstraints);
        this.getRootPane().setDefaultButton(this.mButtonReturn);
        
        /* Cancel Button */
        this.mButtonCancel = new JButton("Cancel");
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonCancel, layoutConstraints);
        
        /* DateTimeFormatter */
        this.mDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
        
        this.mButtonReturn.addActionListener(e -> this.onButtonReturnClick());
        this.mButtonCancel.addActionListener(e -> this.onButtonCancelClick());
    }
    
    private void onButtonReturnClick()
    {
        this.mResult = DialogResult.OK;
        this.setVisible(false);
    }
    
    private void onButtonCancelClick()
    {
        this.mResult = DialogResult.Cancel;
        this.setVisible(false);
    }
    
    public Rental getReturnedObjectInfo()
    {
        return this.mReturnedObjectInfo;
    }
    
    public LocalDate getActualReturnDate()
    {
        return this.mActualReturnDate;
    }
    
    public int getOverduePayment()
    {
        return this.mOverduePayment;
    }
}
