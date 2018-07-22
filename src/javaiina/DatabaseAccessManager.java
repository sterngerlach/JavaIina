
/* DatabaseAccess.java */

package javaiina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccessManager
{
    private static DatabaseAccessManager mInstance;
    private String mConnUri;
    private Connection mConnToDatabase;

    public DatabaseAccessManager() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        this.mConnUri = "jdbc:derby:../database;create = true";
        this.mConnToDatabase = DriverManager.getConnection(mConnUri);
    }
    
    public static DatabaseAccessManager getInstance() throws SQLException, ClassNotFoundException
    { 
        if (mInstance == null)
            mInstance = new DatabaseAccessManager();
        
        return DatabaseAccessManager.mInstance;
    }
    
    public Connection getConnection()
    {
        return this.mConnToDatabase;
    }
}
