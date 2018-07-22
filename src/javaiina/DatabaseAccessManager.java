
/* DatabaseAccess.java */

package javaiina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccessManager
{
    private static DatabaseAccessManager mInstance;
    private String mConnectionUri;
    private Connection mConnection;

    public DatabaseAccessManager() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        this.mConnectionUri = "jdbc:derby:db;create=true";
        this.mConnection = DriverManager.getConnection(mConnectionUri);
    }

    public static DatabaseAccessManager getInstance()
    {
        try {
            if (mInstance == null)
                mInstance = new DatabaseAccessManager();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DatabaseAccessManager.mInstance;
    }

    public Connection getConnection()
    {
        return this.mConnection;
    }
}
