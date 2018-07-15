
/* RentalView.java */

package javaiina;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RentalView extends DialogBase
{
    private static final long serialVersionUID = 5733670985359475230L;
    
    private static final int DefaultWindowWidth = 480;
    private static final int DefaultWindowHeight = 320;
    
    private static final String DefaultWindowTitle = "Rental";
    
    private RentalObject mRentalObject;
    
    private Font mFontLabelBold;
    private JLabel mLabelItemId;
    private JLabel mLabelItemIdValue;
    private JLabel mLabelItemName;
    private JLabel mLabelItemNameValue;
    private JLabel mLabelItemAvailableSize;
    private JComboBox<RentalObjectSizeInfo> mComboBoxItemAvailableSize;
    private JLabel mLabelItemCost;
    private JLabel mLabelItemCostValue;
    private JLabel mLabelBeginDate;
    private DateSelectionControl mBeginDate;
    private JLabel mLabelReturnDate;
    private DateSelectionControl mReturnDate;
    
    private LocalDate mBorrowMinDate;
    private LocalDate mBorrowMaxDate;
    
    private JButton mButtonBorrow;
    private JButton mButtonCancel;
    
    private InputValidator<RentalView> mInputValidator;
    
    public RentalView(JFrame parentFrame, LocalDate minDate, LocalDate maxDate)
    {
        super(parentFrame);
        
        this.mBorrowMinDate = minDate;
        this.mBorrowMaxDate = maxDate;
        this.initializeDateControl();
        
        this.setMinimumSize(new Dimension(
            RentalView.DefaultWindowWidth, RentalView.DefaultWindowHeight));
        this.setTitle(RentalView.DefaultWindowTitle);
        this.setLocationRelativeTo(this.getParent());
    }
    
    public void setRentalObject(RentalObject rentalObject)
    {
        this.mRentalObject = rentalObject;
        
        this.mLabelItemIdValue.setText(Integer.toString(rentalObject.id()));
        this.mLabelItemNameValue.setText(rentalObject.name());
        this.mLabelItemCostValue.setText(Integer.toString(rentalObject.cost()) + " Yen/day");
        
        DefaultComboBoxModel<RentalObjectSizeInfo> sizeInfoModel =
            new DefaultComboBoxModel<>(rentalObject.availableSizeInfo());
        this.mComboBoxItemAvailableSize.setModel(sizeInfoModel);
        this.mComboBoxItemAvailableSize.setSelectedItem(null);
    }
    
    @Override
    protected void initializeComponent()
    {
        super.initializeComponent();
        
        /* Insets */
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        
        /* Header */
        this.setHeaderTitle("Rental");
        this.setHeaderDescription(
            "Please fill out the following form completely to borrow the item.");
        
        /* Center */
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Font */
        this.mFontLabelBold = new Font(Font.DIALOG, Font.BOLD, 12);
        
        /* Item Id Label */
        this.mLabelItemId = new JLabel("Item Id: ");
        this.mLabelItemId.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemId, layoutConstraints);
        
        this.mLabelItemIdValue = new JLabel();
        this.mLabelItemIdValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemIdValue, layoutConstraints);
        
        /* Item Name Label */
        this.mLabelItemName = new JLabel("Item Name: ");
        this.mLabelItemName.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 1, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemName, layoutConstraints);
        
        this.mLabelItemNameValue = new JLabel();
        this.mLabelItemNameValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 1, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemNameValue, layoutConstraints);
        
        /* Item Size Label */
        this.mLabelItemAvailableSize = new JLabel("Item Size Available: ");
        this.mLabelItemAvailableSize.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 2, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemAvailableSize, layoutConstraints);
        
        /* Item Size ComboBox */
        this.mComboBoxItemAvailableSize = new JComboBox<>();
        this.setLayoutConstraints(
            layoutConstraints, 1, 2, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelCenter.add(this.mComboBoxItemAvailableSize, layoutConstraints);
        
        /* Cost Label */
        this.mLabelItemCost = new JLabel("Cost: ");
        this.mLabelItemCost.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 3, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemCost, layoutConstraints);
        
        this.mLabelItemCostValue = new JLabel();
        this.mLabelItemCostValue.setFont(this.mFontLabelBold);
        this.setLayoutConstraints(
            layoutConstraints, 1, 3, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelItemCostValue, layoutConstraints);
        
        /* Bottom */
        /* Dummy Panel for padding */
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 0.0, defaultInsets, GridBagConstraints.HORIZONTAL);
        this.mPanelBottom.add(new JPanel(), layoutConstraints);
        
        /* Borrow Button */
        this.mButtonBorrow = new JButton("Borrow");
        this.setLayoutConstraints(
            layoutConstraints, 1, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonBorrow, layoutConstraints);
        this.getRootPane().setDefaultButton(this.mButtonBorrow);
        
        /* Cancel Button */
        this.mButtonCancel = new JButton("Cancel");
        this.setLayoutConstraints(
            layoutConstraints, 2, 0, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelBottom.add(this.mButtonCancel, layoutConstraints);
    }
    
    private void initializeDateControl()
    {
        /* Insets */
        Insets defaultInsets = new Insets(5, 5, 5, 5);
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Begin Date Label */
        this.mLabelBeginDate = new JLabel("Begin Date: ");
        this.mLabelBeginDate.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 4, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelBeginDate, layoutConstraints);
        
        /* Begin Date */
        this.mBeginDate = new DateSelectionControl(this.mBorrowMinDate, this.mBorrowMinDate);
        this.mBeginDate.setSelectedDate(this.mBorrowMinDate);
        this.setLayoutConstraints(
            layoutConstraints, 1, 4, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mBeginDate, layoutConstraints);
        
        /* Return Date Label */
        this.mLabelReturnDate = new JLabel("Return Date: ");
        this.mLabelReturnDate.setFont(this.mFontLabel);
        this.setLayoutConstraints(
            layoutConstraints, 0, 5, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mLabelReturnDate, layoutConstraints);
        
        /* Return Date */
        this.mReturnDate = new DateSelectionControl(this.mBorrowMinDate, this.mBorrowMaxDate);
        this.mReturnDate.setSelectedDate(this.mBorrowMinDate);
        this.setLayoutConstraints(
            layoutConstraints, 1, 5, 0.0, 0.0, defaultInsets, GridBagConstraints.NONE);
        this.mPanelCenter.add(this.mReturnDate, layoutConstraints);
        
        /* Dummy Panel for padding */
        this.setLayoutConstraints(
            layoutConstraints, 0, GridBagConstraints.RELATIVE,
            1.0, 1.0, emptyInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(new JPanel(), layoutConstraints);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
        
        this.mButtonBorrow.addActionListener(e -> this.onButtonBorrowClick());
        this.mButtonCancel.addActionListener(e -> this.onButtonCancelClick());
    }
    
    private void onButtonBorrowClick()
    {
        if (!this.mInputValidator.validateInput(this)) {
            JOptionPane.showMessageDialog(
                this, this.mInputValidator.getMessage(),
                RentalView.DefaultWindowTitle, JOptionPane.ERROR_MESSAGE);
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
    
    public void setInputValidator(InputValidator<RentalView> inputValidator)
    {
        this.mInputValidator = inputValidator;
    }
    
    public RentalObject getRentalObject()
    {
        return this.mRentalObject;
    }
    
    public RentalObjectSizeInfo getSizeInfo()
    {
        return (RentalObjectSizeInfo)this.mComboBoxItemAvailableSize.getModel().getSelectedItem();
    }
    
    public LocalDate getBeginDate()
    {
        return this.mBeginDate.getSelectedDate();
    }
    
    public LocalDate getDesiredReturnDate()
    {
        return this.mReturnDate.getSelectedDate();
    }
}
