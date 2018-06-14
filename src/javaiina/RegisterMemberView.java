
/* RegisterMemberView.java */

package javaiina;

import javax.swing.JDialog;

public class RegisterMemberView extends JDialog
{
    private static final long serialVersionUID = -3421065686754085314L;
    
    private RegisterMemberViewModel mModel;
    
    public RegisterMemberView(RegisterMemberViewModel viewModel)
    {
        this.mModel = viewModel;
    }
}
