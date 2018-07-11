
/* ItemsListViewRenderer.java */

package javaiina;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class ItemsListViewRenderer implements ListCellRenderer<RentalObject>
{
    private JPanel mPanelItem;
    private JLabel mLabelItemName;
    private JLabel mLabelAvailableSizeName;
    private JLabel mLabelCostPerDay;
    
    public ItemsListViewRenderer()
    {
        this.mPanelItem = new JPanel();
        this.mPanelItem.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.mPanelItem.setLayout(new BoxLayout(this.mPanelItem, BoxLayout.Y_AXIS));
        
        this.mLabelItemName = new JLabel();
        this.mPanelItem.add(this.mLabelItemName);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelAvailableSizeName = new JLabel();
        this.mPanelItem.add(this.mLabelAvailableSizeName);
        this.mPanelItem.add(Box.createVerticalStrut(3));
        
        this.mLabelCostPerDay = new JLabel();
        this.mPanelItem.add(this.mLabelCostPerDay);
        this.mPanelItem.add(Box.createVerticalStrut(3));
    }
    
    @Override
    public Component getListCellRendererComponent(
        JList<? extends RentalObject> list,
        RentalObject value, int index,
        boolean isSelected, boolean cellHasFocus)
    {
        this.mLabelItemName.setText("Name: " + value.name());
        this.mLabelAvailableSizeName.setText("Size Available: " +
            String.join(", ",
                Arrays.stream(value.availableSizeInfo())
                .map(sizeInfo -> sizeInfo.sizeName())
                .toArray(String[]::new)));
        this.mLabelCostPerDay.setText(
            "Cost: " + Integer.toString(value.cost()) + " Yen/day");
        
        if (isSelected)
            this.mPanelItem.setBackground(Color.LIGHT_GRAY);
        else
            this.mPanelItem.setBackground(Color.WHITE);
        
        return this.mPanelItem;
    }
}
