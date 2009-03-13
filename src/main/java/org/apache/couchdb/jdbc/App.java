package org.apache.couchdb.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      
        try {
            Class.forName("org.apache.couchdb.jdbc.CouchDriver");
            Connection con = DriverManager.getConnection("jdbc:couchdb://127.0.0.1:5984");
            DatabaseMetaData dbData = con.getMetaData();
            System.out.println(dbData.getDatabaseProductName()+ " - "+dbData.getDatabaseProductVersion());
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("/test/jdbc");
            System.out.println(rs.getBoolean("jdbc"));
            rs.updateBoolean("jdbc", false);
            System.out.println(rs.getBoolean("jdbc"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}
