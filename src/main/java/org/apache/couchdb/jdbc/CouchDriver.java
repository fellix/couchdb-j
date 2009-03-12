/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Rafael Felix da Silva
 * @version 1.0
 */
public class CouchDriver implements Driver {

    static {
        try {
            DriverManager.registerDriver(new CouchDriver());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        Properties defaults = new Properties();
        if ((defaults = parseUrl(url, info)) == null) {
            throw new SQLException("Inválid connection URL");
        }
        return new CouchConnection(defaults);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return parseUrl(url, null) != null;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * URL STRING jdbc:couchdb://host:port
     * @param url
     * @param info
     * @return
     */
    private Properties parseUrl(String url, Properties info) {
        if (!url.contains("jdbc") || !url.contains("couchdb")) {
            return null;
        }
        String newUrl = url.substring(url.lastIndexOf("couchdb://")+10);
        String[] parts = newUrl.split(":");
        if (parts.length < 2) {
            return null;
        }
        Properties prop = new Properties(info);
        prop.put("host", parts[0]);
        prop.put("port", parts[1]);
        return prop;
    }

    public int getMajorVersion() {
        return 1;
    }

    public int getMinorVersion() {
        return 1;
    }

    public boolean jdbcCompliant() {
        return false;
    }
}
