
/* RegisterMemberViewController.java */

package javaiina;

import java.awt.event.ItemEvent;

public class RegisterMemberViewController
{
    private RegisterMemberView mView;
    private RegisterMemberViewModel mModel;
    
    public RegisterMemberViewController(
        RegisterMemberView registerMemberView,
        RegisterMemberViewModel registerMemberViewModel)
    {
        this.mView = registerMemberView;
        this.mModel = registerMemberViewModel;
        
        this.mView.addPrefectureChangeListener(e -> {
            if (e.getStateChange() != ItemEvent.SELECTED)
                return;
            
            // You can update the prefecture property of the model (if needed)
            // Retrieve the prefecture name by calling this.mView.getSelectedPrefectureName()
            // Then call Prefecture.getPrefectureIdFromName() to get the prefecture Id from the prefecture name
        });
        
        this.mView.addRegisterButtonClickListener(e -> {
            // TODO: It may also be possible to create an inner class to handle the event
        });
        
        this.mView.addCancelButtonClickListener(e -> {
            // TODO: It may also be possible to create an inner class to handle the event
        });
    }
}
