
/* RentalListViewRenderer.java */

package javaiina;

import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class RentalListViewRenderer implements ListCellRenderer<Rental>
{
    public enum RenderMode
    {
        BorrowingItems,
        BorrowingHistory
    }
    
    private RenderMode mRenderMode;
    private JPanel mPanelItem;
    private JLabel mLabelItemName;
    private JLabel mLabelItemSize;
    private JLabel mLabelItemCost;
    private JLabel mLabelBeginDate;
    private JLabel mLabelDesiredReturnDate;
    private JLabel mLabelActualReturnDate;
    private DateTimeFormatter mDateFormatter;
    private JLabel mLabelOverduePayment;
    
    public RentalListViewRenderer(RenderMode renderMode)
    {
        this.mRenderMode = renderMode;
        
        this.mPanelItem = new JPanel();
        this.mPanelItem.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.mPanelItem.setLayout(new BoxLayout(this.mPanelItem, BoxLayout.Y_AXIS));
        
        this.mLabelItemName = new JLabel();
        this.mPanelItem.add(this.mLabelItemName);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelItemSize = new JLabel();
        this.mPanelItem.add(this.mLabelItemSize);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelItemCost = new JLabel();
        this.mPanelItem.add(this.mLabelItemCost);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelBeginDate = new JLabel();
        this.mPanelItem.add(this.mLabelBeginDate);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelDesiredReturnDate = new JLabel();
        this.mPanelItem.add(this.mLabelDesiredReturnDate);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelActualReturnDate = new JLabel();
        this.mPanelItem.add(this.mLabelActualReturnDate);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelOverduePayment = new JLabel();
        this.mPanelItem.add(this.mLabelOverduePayment);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    @Override
    public Component getListCellRendererComponent(
        JList<? extends Rental> list,
        Rental value, int index,
        boolean isSelected, boolean cellHasFocus)
    {
        this.mLabelItemName.setText("Name: " + value.getRentalObject().name());
        this.mLabelItemSize.setText("Size: " + value.getSizeInfo().sizeName());
        this.mLabelItemCost.setText("Cost: " + value.getRentalObject().cost() + " Yen/day");
        this.mLabelBeginDate.setText("Borrowed On: " +
            value.getBeginDate().format(this.mDateFormatter));
        this.mLabelDesiredReturnDate.setText("Return By: " +
            value.getDesiredReturnDate().format(this.mDateFormatter));

        if (this.mRenderMode == RenderMode.BorrowingItems) {
            this.mLabelActualReturnDate.setVisible(false);
            
            if (value.getDesiredReturnDate().isBefore(LocalDate.now())) {
                this.mLabelDesiredReturnDate.setForeground(Color.RED);
            } else {
                this.mLabelDesiredReturnDate.setForeground(Color.BLUE);
            }
        } else if (this.mRenderMode == RenderMode.BorrowingHistory) {
            this.mLabelActualReturnDate.setText("Actual Return Date: " +
                value.getActualReturnDate().format(this.mDateFormatter));
            this.mLabelActualReturnDate.setVisible(true);
            
            this.mLabelOverduePayment.setText("Overdue Payment: " +
                value.getOverduePayment());
            this.mLabelOverduePayment.setVisible(true);
            
            if (value.getDesiredReturnDate().isBefore(value.getActualReturnDate())) {
                this.mLabelDesiredReturnDate.setForeground(Color.RED);
                this.mLabelActualReturnDate.setForeground(Color.RED);
            } else {
                this.mLabelDesiredReturnDate.setForeground(Color.BLUE);
                this.mLabelActualReturnDate.setForeground(Color.BLUE);
            }
        }
        
        if (isSelected)
            this.mPanelItem.setBackground(Color.LIGHT_GRAY);
        else
            this.mPanelItem.setBackground(Color.WHITE);
        
        return this.mPanelItem;
    }
}
