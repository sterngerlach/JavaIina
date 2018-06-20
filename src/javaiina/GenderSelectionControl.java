
/* GenderSelectionControl.java */

package javaiina;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GenderSelectionControl extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 7037645505537333071L;
    
    private Gender mSelectedGender;
    
    private JRadioButton mRadioButtonMale;
    private JRadioButton mRadioButtonFemale;
    private JRadioButton mRadioButtonUnspecified;
    
    public GenderSelectionControl()
    {
        this.initializeComponent();
    }
    
    private void initializeComponent()
    {
        this.setLayout(new GridBagLayout());
        
        /* Insets */
        Insets emptyInsets = new Insets(0, 0, 0, 0);
        Insets rightInsets = new Insets(0, 0, 0, 5);
        
        /* Radio Buttons */
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.anchor = GridBagConstraints.WEST;
        layoutConstraints.fill = GridBagConstraints.NONE;
        layoutConstraints.weightx = 0.0;
        layoutConstraints.weighty = 0.0;
        
        layoutConstraints.gridx = 0;
        layoutConstraints.insets = rightInsets;
        this.mRadioButtonMale = new JRadioButton(Gender.MALE.toString());
        this.add(this.mRadioButtonMale, layoutConstraints);
        
        layoutConstraints.gridx = 1;
        layoutConstraints.insets = rightInsets;
        this.mRadioButtonFemale = new JRadioButton(Gender.FEMALE.toString());
        this.add(this.mRadioButtonFemale, layoutConstraints);
        
        layoutConstraints.gridx = 2;
        layoutConstraints.insets = emptyInsets;
        this.mRadioButtonUnspecified = new JRadioButton(Gender.UNSPECIFIED.toString());
        this.add(this.mRadioButtonUnspecified, layoutConstraints);
        
        /* Radio Button Group */
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(this.mRadioButtonMale);
        genderButtonGroup.add(this.mRadioButtonFemale);
        genderButtonGroup.add(this.mRadioButtonUnspecified);
        
        /* Add ActionListener */
        this.mRadioButtonMale.addActionListener(this);
        this.mRadioButtonFemale.addActionListener(this);
        this.mRadioButtonUnspecified.addActionListener(this);
        
        /* Set Default Gender */
        this.mRadioButtonMale.doClick();
        
        /*
        genderButtonGroup.clearSelection();
        this.mRadioButtonMale.setSelected(true);
        this.mSelectedGender = Gender.MALE;
        */
    }
    
    public Gender getSelectedGender()
    {
        return this.mSelectedGender;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.mRadioButtonMale)
            this.mSelectedGender = Gender.MALE;
        else if (e.getSource() == this.mRadioButtonFemale)
            this.mSelectedGender = Gender.FEMALE;
        else if (e.getSource() == this.mRadioButtonUnspecified)
            this.mSelectedGender = Gender.UNSPECIFIED;
        else
            throw new UnsupportedOperationException("Unknown event source");
    }
}
