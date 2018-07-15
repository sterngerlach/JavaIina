package javaiina;

import java.sql.*;
import java.util.Properties;

public class DatabaseAccess {
    protected String mConnUri;
    protected Connection mConnToDatabase;
    protected Statement mStmt;

    protected DatabaseAccess() throws SQLException,ClassNotFoundException{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        mConnUri= "jdbc:derby:../database;create = true";
        mConnToDatabase = DriverManager.getConnection(mConnUri);
        mStmt = mConnToDatabase.createStatement();
    }
}
