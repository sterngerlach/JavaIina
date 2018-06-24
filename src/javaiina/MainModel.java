
/* MainModel.java */

package javaiina;

import javax.swing.event.EventListenerList;

public class MainModel
{
    private EventListenerList mEventListenerList;
    private Member mLoggedInMember;
    
    public Member loggedInMember()
    {
        return this.mLoggedInMember;
    }
    
    public void setLoggedInMember(Member newValue)
    {
        this.mLoggedInMember = newValue;
        this.fireModelChanged();
    }
    
    public MainModel()
    {
        this.reset();
    }
    
    public void reset()
    {
        this.mEventListenerList = null;
        this.mLoggedInMember = null;
    }
    
    public void addModelListener(ModelListener modelListener)
    {
        if (this.mEventListenerList == null)
            this.mEventListenerList = new EventListenerList();
        
        this.mEventListenerList.add(ModelListener.class, modelListener);
    }
    
    public void removeEventListener(ModelListener modelListener)
    {
        if (this.mEventListenerList == null)
            return;
        
        this.mEventListenerList.remove(ModelListener.class, modelListener);
    }
    
    private void fireModelChanged()
    {
        if (this.mEventListenerList == null)
            return;
        
        ModelEvent modelEvent = new ModelEvent(this);
        ModelListener[] modelListeners = this.mEventListenerList.getListeners(ModelListener.class);
        
        for (ModelListener modelListener : modelListeners)
            modelListener.modelChanged(modelEvent);
    }
}
