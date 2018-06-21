
/* DateSelectionControl.java */

package javaiina;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class DateSelectionControl extends JPanel implements ItemListener, PopupMenuListener
{
    private static final long serialVersionUID = -8988322473723783107L;
    
    private LocalDate mMinDate;
    private Integer mSelectedYear;
    private Integer mSelectedMonth;
    private Integer mSelectedDay;
    
    private Font mFontDefault;
    
    private JLabel mLabelYear;
    private JComboBox<Integer> mComboBoxYear;
    private JLabel mLabelMonth;
    private JComboBox<Integer> mComboBoxMonth;
    private JLabel mLabelDay;
    private JComboBox<Integer> mComboBoxDay;
    
    public DateSelectionControl(LocalDate minDate)
    {
        this.mSelectedYear = null;
        this.mSelectedMonth = null;
        this.mSelectedDay = null;
        this.mMinDate = minDate;
        
        this.initializeComponent();
    }
    
    private void initializeComponent()
    {
        this.setLayout(new GridBagLayout());
        
        /* Insets */
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        Insets rightInsets = new Insets(0, 0, 0, 5);
        
        /* Layout Constraints */
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        layoutConstraints.fill = GridBagConstraints.NONE;
        layoutConstraints.weightx = 0.0;
        layoutConstraints.weighty = 0.0;
        
        this.mFontDefault = new Font(Font.DIALOG, Font.PLAIN, 12);
        
        /* Year Label */
        this.mLabelYear = new JLabel("Year: ");
        this.mLabelYear.setFont(this.mFontDefault);
        
        layoutConstraints.gridx = 0;
        layoutConstraints.insets = rightInsets;
        this.add(this.mLabelYear, layoutConstraints);
        
        /* Year ComboBox */
        Integer minYear = this.mMinDate.getYear();
        Integer maxYear = LocalDate.now().getYear();
        Integer[] yearItems = Stream
            .iterate(minYear, n -> n + 1)
            .limit(maxYear - minYear + 1)
            .toArray(Integer[]::new);
        
        DefaultComboBoxModel<Integer> comboBoxYearModel = new DefaultComboBoxModel<>(yearItems);
        this.mComboBoxYear = new JComboBox<>(comboBoxYearModel);
        this.mComboBoxYear.setFont(this.mFontDefault);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.insets = rightInsets;
        this.add(this.mComboBoxYear, layoutConstraints);
        
        /* Month Label */
        this.mLabelMonth = new JLabel("Month: ");
        this.mLabelMonth.setFont(this.mFontDefault);
        
        layoutConstraints.gridx = 2;
        layoutConstraints.insets = rightInsets;
        this.add(this.mLabelMonth, layoutConstraints);
        
        /* Month ComboBox */
        Integer[] monthItems = Arrays
            .stream(Month.values())
            .map(monthEnum -> monthEnum.getValue())
            .toArray(Integer[]::new);
        
        DefaultComboBoxModel<Integer> comboBoxMonthModel = new DefaultComboBoxModel<>(monthItems);
        this.mComboBoxMonth = new JComboBox<>(comboBoxMonthModel);
        this.mComboBoxMonth.setFont(this.mFontDefault);
        
        layoutConstraints.gridx = 3;
        layoutConstraints.insets = rightInsets;
        this.add(this.mComboBoxMonth, layoutConstraints);
        
        /* Day Label */
        this.mLabelDay = new JLabel("Day: ");
        this.mLabelDay.setFont(this.mFontDefault);
        
        layoutConstraints.gridx = 4;
        layoutConstraints.insets = rightInsets;
        this.add(this.mLabelDay, layoutConstraints);
        
        /* Day ComboBox */
        this.mComboBoxDay = new JComboBox<>();
        this.mComboBoxDay.setFont(this.mFontDefault);
        
        layoutConstraints.gridx = 5;
        layoutConstraints.insets = emptyInsets;
        this.add(this.mComboBoxDay, layoutConstraints);
        
        /* Add ActionListener */
        this.mComboBoxYear.addItemListener(this);
        this.mComboBoxMonth.addItemListener(this);
        this.mComboBoxDay.addItemListener(this);
        
        /* Add PopupMenuListener */
        this.mComboBoxDay.addPopupMenuListener(this);
        
        /* Set Default Value */
        comboBoxYearModel.setSelectedItem(null);
        comboBoxMonthModel.setSelectedItem(null);
    }
    
    public LocalDate getSelectedDate()
    {
        if (this.mSelectedYear == null || this.mSelectedMonth == null || this.mSelectedDay == null)
            return null;
        
        return LocalDate.of(this.mSelectedYear, this.mSelectedMonth, this.mSelectedDay);
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() != ItemEvent.SELECTED)
            return;
        
        if (e.getSource() == this.mComboBoxYear) {
            /* this.mSelectedYear is set to null if there is no selection */
            this.mSelectedYear = (Integer)this.mComboBoxYear.getModel().getSelectedItem();
            /* Clear day ComboBox selection */
            this.mComboBoxDay.getModel().setSelectedItem(null);
        } else if (e.getSource() == this.mComboBoxMonth) {
            /* this.mSelectedMonth is set to null if there is no selection */
            this.mSelectedMonth = (Integer)this.mComboBoxMonth.getModel().getSelectedItem();
            /* Clear day ComboBox selection */
            this.mComboBoxDay.getModel().setSelectedItem(null);
        } else if (e.getSource() == this.mComboBoxDay) {
            this.mSelectedDay = (Integer)this.mComboBoxDay.getModel().getSelectedItem();
        } else {
            throw new UnsupportedOperationException("Unknown event source");
        }
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
    {
        if (e.getSource() != this.mComboBoxDay)
            return;
        
        if (this.mSelectedYear == null || this.mSelectedMonth == null)
            return;
        
        /* Add day ComboBox items */
        YearMonth selectedYearMonth = YearMonth.of(this.mSelectedYear, this.mSelectedMonth);
        Integer[] dayItems = Stream
            .iterate(1, n -> n + 1)
            .limit(selectedYearMonth.lengthOfMonth())
            .toArray(Integer[]::new);
        
        DefaultComboBoxModel<Integer> comboBoxDayModel = new DefaultComboBoxModel<>(dayItems);
        this.mComboBoxDay.setModel(comboBoxDayModel);
        comboBoxDayModel.setSelectedItem(null);
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
    {
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e)
    {
    }
}
