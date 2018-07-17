package javaiina;

import java.sql.*;
import java.util.Properties;

public class DatabaseAccess {
    private static DatabaseAccess mInstance = null;
    private String mConnUri = "jdbc:derby:../database;create = true";
    private Connection mConnToDatabase = DriverManager.getConnection(mConnUri);

    private DatabaseAccess() throws SQLException,ClassNotFoundException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        
    }
    
    public static DatabaseAccess getInstance() throws SQLException, ClassNotFoundException{ 
        if (mInstance == null) mInstance = new DatabaseAccess();
        return DatabaseAccess.mInstance;
    }
    
    public Connection getConnection() { return this.mConnToDatabase;}
}
