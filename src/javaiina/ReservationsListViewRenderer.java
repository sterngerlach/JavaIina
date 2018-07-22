
/* ReservationsListViewRenderer.java */

package javaiina;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class ReservationsListViewRenderer implements ListCellRenderer<Reservation>
{
    private JPanel mPanelItem;
    private JLabel mLabelItemName;
    private JLabel mLabelItemSize;
    private JLabel mLabelReservationDate;
    
    public ReservationsListViewRenderer()
    {
        this.mPanelItem = new JPanel();
        this.mPanelItem.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.mPanelItem.setLayout(new BoxLayout(this.mPanelItem, BoxLayout.Y_AXIS));
        
        this.mLabelItemName = new JLabel();
        this.mPanelItem.add(this.mLabelItemName);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelItemSize = new JLabel();
        this.mPanelItem.add(this.mLabelItemSize);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelReservationDate = new JLabel();
        this.mPanelItem.add(this.mLabelReservationDate);
        this.mPanelItem.add(Box.createVerticalStrut(3));
    }
    
    @Override
    public Component getListCellRendererComponent(
        JList<? extends Reservation> list,
        Reservation value, int index,
        boolean isSelected, boolean cellHasFocus)
    {
        this.mLabelItemName.setText("Name: " + value.rentalObject().name());
        this.mLabelItemSize.setText("Size: " + value.sizeInfo().toString());
        this.mLabelReservationDate.setText("Reserved On: " + value.reservationDate().toString());
        
        if (isSelected)
            this.mPanelItem.setBackground(Color.LIGHT_GRAY);
        else
            this.mPanelItem.setBackground(Color.WHITE);
        
        return this.mPanelItem;
    }
}
