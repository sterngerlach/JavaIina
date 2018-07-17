
/* BorrowingItemsPanel.java */

package javaiina;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BorrowingItemsPanel extends PanelBase
{
    private static final long serialVersionUID = 1340009896202568436L;
    
    private MainModel mMainModel;
    
    private JList<Rental> mListViewBorrowingItems;
    private DefaultListModel<Rental> mListViewBorrowingItemsModel;
    
    private JPanel mBottomPanel;
    private JButton mButtonReturnSelectedItem;
    
    @Override
    public String getPanelName()
    {
        return "Borrowing Items";
    }
    
    public BorrowingItemsPanel()
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
        this.setHeaderTitle("Borrowing Items");
        
        /* Center Panel */
        this.mPanelCenter.setLayout(new GridBagLayout());
        
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        
        /* Items ListView */
        this.mListViewBorrowingItems = new JList<>();
        this.mListViewBorrowingItemsModel = new DefaultListModel<>();
        this.mListViewBorrowingItems.setModel(this.mListViewBorrowingItemsModel);
        this.mListViewBorrowingItems.setCellRenderer(new RentalListViewRenderer());
        this.setLayoutConstraints(
            layoutConstraints, 0, 0, 1.0, 1.0, emptyInsets, GridBagConstraints.BOTH);
        this.mPanelCenter.add(this.mListViewBorrowingItems, layoutConstraints);
        
        /* Bottom Panel */
        this.mBottomPanel = new JPanel();
        this.mBottomPanel.setLayout(new BoxLayout(this.mBottomPanel, BoxLayout.X_AXIS));
        this.mBottomPanel.setBorder(new EmptyBorder(
            0, this.mDefaultBorderWidth,
            this.mDefaultBorderWidth, this.mDefaultBorderWidth));
        this.add(this.mBottomPanel, BorderLayout.SOUTH);
        
        /* Return Button */
        this.mButtonReturnSelectedItem = new JButton("Return Selected Item");
        this.mBottomPanel.add(Box.createHorizontalGlue());
        this.mBottomPanel.add(this.mButtonReturnSelectedItem);
    }
    
    @Override
    protected void addEventHandler()
    {
        super.addEventHandler();
    }
    
    @Override
    protected void onPanelSelected()
    {
        this.initializeItems();
    }
    
    private void initializeItems()
    {
        /* Update ListView */
        this.mListViewBorrowingItemsModel.removeAllElements();
        this.mMainModel.getBorrowingItems().forEach(
            borrowingItem -> this.mListViewBorrowingItemsModel.addElement(borrowingItem));
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
    
    public void addReturnItemListener(ActionListener actionListener)
    {
        this.mButtonReturnSelectedItem.addActionListener(actionListener);
    }
    
    public Rental getSelectedRental()
    {
        return this.mListViewBorrowingItems.getSelectedValue();
    }
}
