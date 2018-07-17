package javaiina;

import java.sql.*;
import java.util.Properties;

public class DatabaseAccess {
    private static DatabaseAccess mInstance = new DatabaseAccess();
    private String mConnUri = "jdbc:derby:../database;create = true";
    private Connection mConnToDatabase = DriverManager.getConnection(mConnUri);

    private DatabaseAccess() throws SQLException,ClassNotFoundException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        
    }
    
    public static DatabaseAccess getInstance() { return this.mInstance;}
    
    public Connection getConnection() { return this.mConnToDatabase;}
}
