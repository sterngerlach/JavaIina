
/* ModelEvent.java */

package javaiina;

import java.util.EventObject;

public class ModelEvent extends EventObject
{
    private static final long serialVersionUID = -5959113389648840860L;

    public ModelEvent(Object source)
    {
        super(source);
    }
}
