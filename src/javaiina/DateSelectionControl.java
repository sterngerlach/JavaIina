
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
    private LocalDate mMaxDate;
    
    private Font mFontDefault;
    
    private JLabel mLabelYear;
    private JComboBox<Integer> mComboBoxYear;
    private JLabel mLabelMonth;
    private JComboBox<Integer> mComboBoxMonth;
    private JLabel mLabelDay;
    private JComboBox<Integer> mComboBoxDay;
    
    public DateSelectionControl(LocalDate minDate, LocalDate maxDate)
    {
        if (minDate.isAfter(maxDate))
            throw new IllegalArgumentException("minDate is after the maxDate");
        
        this.mMinDate = minDate;
        this.mMaxDate = maxDate;
        
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
        Integer maxYear = this.mMaxDate.getYear();
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
        this.mComboBoxMonth = new JComboBox<>();
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
        this.mComboBoxMonth.addPopupMenuListener(this);
        this.mComboBoxDay.addPopupMenuListener(this);
        
        /* Set Default Value */
        comboBoxYearModel.setSelectedItem(null);
    }
    
    private void updateComboBoxMonth()
    {
        if (this.mComboBoxYear.getModel() == null)
            return;
        
        Integer selectedYear = (Integer)this.mComboBoxYear.getModel().getSelectedItem();
        
        if (selectedYear == null)
            return;
        
        /* Add month ComboBox items */
        Integer[] monthItems = null;
        
        if (this.mMinDate.getYear() == this.mMaxDate.getYear())
            monthItems = Arrays.stream(Month.values())
                .map(monthEnum -> monthEnum.getValue())
                .skip(this.mMinDate.getMonthValue() - 1)
                .limit(this.mMaxDate.getMonthValue() - this.mMinDate.getMonthValue() + 1)
                .toArray(Integer[]::new);
        else if (selectedYear == this.mMaxDate.getYear())
            monthItems = Stream.iterate(1, n -> n + 1)
                .limit(this.mMaxDate.getMonthValue())
                .toArray(Integer[]::new);
        else if (selectedYear == this.mMinDate.getYear())
            monthItems = Arrays.stream(Month.values())
                .map(monthEnum -> monthEnum.getValue())
                .skip(this.mMinDate.getMonthValue() - 1)
                .toArray(Integer[]::new);
        else
            monthItems = Arrays.stream(Month.values())
                .map(monthEnum -> monthEnum.getValue())
                .toArray(Integer[]::new);
        
        DefaultComboBoxModel<Integer> comboBoxMonthModel = new DefaultComboBoxModel<>(monthItems);
        this.mComboBoxMonth.setModel(comboBoxMonthModel);
        comboBoxMonthModel.setSelectedItem(null);
    }
    
    private void updateComboBoxDay()
    {
        if (this.mComboBoxYear.getModel() == null)
            return;
        
        if (this.mComboBoxMonth.getModel() == null)
            return;
        
        Integer selectedYear = (Integer)this.mComboBoxYear.getModel().getSelectedItem();
        Integer selectedMonth = (Integer)this.mComboBoxMonth.getModel().getSelectedItem();
        
        if (selectedYear == null || selectedMonth == null)
            return;
        
        /* Add day ComboBox items */
        YearMonth selectedYearMonth = YearMonth.of(selectedYear, selectedMonth);
        Integer[] dayItems = null;
        
        if (this.mMinDate.getYear() == this.mMaxDate.getYear() &&
            this.mMinDate.getMonthValue() == this.mMaxDate.getMonthValue())
            dayItems = Stream.iterate(1, n -> n + 1)
                .skip(this.mMinDate.getDayOfMonth() - 1)
                .limit(this.mMaxDate.getDayOfMonth() - this.mMinDate.getDayOfMonth() + 1)
                .toArray(Integer[]::new);
        else if (selectedYearMonth.getYear() == this.mMaxDate.getYear() &&
            selectedYearMonth.getMonthValue() == this.mMaxDate.getMonthValue())
            dayItems = Stream.iterate(1, n -> n + 1)
                .limit(this.mMaxDate.getDayOfMonth()).toArray(Integer[]::new);
        else if (selectedYearMonth.getYear() == this.mMinDate.getYear() &&
            selectedYearMonth.getMonthValue() == this.mMinDate.getMonthValue())
            dayItems = Stream.iterate(1, n -> n + 1)
                .skip(this.mMinDate.getDayOfMonth() - 1)
                .limit(selectedYearMonth.lengthOfMonth() - this.mMinDate.getDayOfMonth() + 1)
                .toArray(Integer[]::new);
        else
            dayItems = Stream.iterate(1, n -> n + 1)
                .limit(selectedYearMonth.lengthOfMonth()).toArray(Integer[]::new);
        
        DefaultComboBoxModel<Integer> comboBoxDayModel = new DefaultComboBoxModel<>(dayItems);
        this.mComboBoxDay.setModel(comboBoxDayModel);
        comboBoxDayModel.setSelectedItem(null);
    }
    
    public LocalDate getSelectedDate()
    {
        if (this.mComboBoxYear.getModel() == null)
            return null;
        
        if (this.mComboBoxMonth.getModel() == null)
            return null;
        
        if (this.mComboBoxDay.getModel() == null)
            return null;
        
        Integer selectedYear = (Integer)this.mComboBoxYear.getModel().getSelectedItem();
        Integer selectedMonth = (Integer)this.mComboBoxMonth.getModel().getSelectedItem();
        Integer selectedDay = (Integer)this.mComboBoxYear.getModel().getSelectedItem();
        
        if (selectedYear == null || selectedMonth == null || selectedDay == null)
            return null;
        
        return LocalDate.of(selectedYear, selectedMonth, selectedDay);
    }
    
    public void setSelectedDate(LocalDate selectedDate)
    {
        if (this.mMinDate.isAfter(selectedDate))
            throw new IllegalArgumentException("minDate is after the selectedDate");
        
        if (this.mMaxDate.isBefore(selectedDate))
            throw new IllegalArgumentException("maxDate is before the selectedDate");
        
        this.mComboBoxYear.getModel().setSelectedItem(selectedDate.getYear());
        this.mComboBoxMonth.getModel().setSelectedItem(selectedDate.getMonthValue());
        this.mComboBoxDay.getModel().setSelectedItem(selectedDate.getDayOfMonth());
    }
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() != ItemEvent.SELECTED)
            return;
        
        if (e.getSource() == this.mComboBoxYear) {
            /* Update month ComboBox */
            this.updateComboBoxMonth();
            
            /* Clear month ComboBox selection */
            if (this.mComboBoxMonth.getModel() != null)
                this.mComboBoxMonth.getModel().setSelectedItem(null);
            
            /* Clear day ComboBox selection */
            if (this.mComboBoxDay.getModel() != null)
                this.mComboBoxDay.getModel().setSelectedItem(null);
        } else if (e.getSource() == this.mComboBoxMonth) {
            /* Update day ComboBox */
            this.updateComboBoxDay();
            
            /* Clear day ComboBox selection */
            if (this.mComboBoxDay.getModel() != null)
                this.mComboBoxDay.getModel().setSelectedItem(null);
        } else if (e.getSource() == this.mComboBoxDay) {
        } else {
            throw new UnsupportedOperationException("Unknown event source");
        }
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
    {
        if (e.getSource() == this.mComboBoxMonth)
            this.updateComboBoxMonth();
        else if (e.getSource() == this.mComboBoxDay)
            this.updateComboBoxDay();
        else
            throw new UnsupportedOperationException("Unknown event source");
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
