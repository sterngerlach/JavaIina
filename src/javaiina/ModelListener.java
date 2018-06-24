
/* ModelListener.java */

package javaiina;

import java.util.EventListener;

public interface ModelListener extends EventListener
{
    public void modelChanged(ModelEvent e);
}
