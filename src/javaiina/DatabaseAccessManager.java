package javaiina;

import java.sql.*;
import java.util.Properties;

public class DatabaseAccess {
    private static DatabaseAccess mInstance = null;
    private String mConnUri;
    private Connection mConnToDatabase;

    private DatabaseAccess() throws SQLException,ClassNotFoundException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        this.mConnUri = "jdbc:derby:../database;create = true";
        this.mConnToDatabase = DriverManager.getConnection(mConnUri);
    }
    
    public static DatabaseAccess getInstance() throws SQLException, ClassNotFoundException{ 
        if (mInstance == null) mInstance = new DatabaseAccess();
        return DatabaseAccess.mInstance;
    }
    
    public Connection getConnection() { return this.mConnToDatabase;}
}
