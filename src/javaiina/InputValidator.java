
/* InputValidator.java */

package javaiina;

public abstract class InputValidator<T>
{
    private String mMessage;
    
    public String getMessage()
    {
        return this.mMessage;
    }
    
    protected void setMessage(String message)
    {
        this.mMessage = message;
    }
    
    public abstract boolean validateInput(T userInput);
}
