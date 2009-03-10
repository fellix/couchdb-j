/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import org.apache.couchdb.jdbc.statement.CouchStatement;
import org.apache.couchdb.jdbc.util.CouchDBHttp;
import org.apache.http.HttpClientConnection;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.protocol.ExecutionContext;

/**
 * CouchDB Connection Implementation
 * @author Rafael Felix da Silva
 * @version 1.0
 */
public class CouchConnection implements Connection {

    private boolean readOnly = false;
    private boolean closed = false;
    private boolean autoCommit = false;
    private String baseUrl;
    private String catalog;
    private HttpClientConnection con;
    private Properties info;

    /**
     * Default constructor for the CouchDB Connection
     * @param info the properties with info of the server
     * @throws java.sql.SQLException
     * @since 1.0
     */
    public CouchConnection(Properties info) throws SQLException {
        DefaultHttpClientConnection d = new DefaultHttpClientConnection();
        CouchDBHttp http = CouchDBHttp.getInstance();
        try {
            http.parseProperties(info);
            http.getDefaultContext().setAttribute(ExecutionContext.HTTP_CONNECTION, d);

            d.bind(http.getSocket(), http.getDefaultParams());
            con = d;
        } catch (UnknownHostException ex) {
            throw new SQLException(ex);
        } catch (IOException ex) {
            throw new SQLException(ex);
        }
    }
    /**
     * Creates a new Statment for CouchDB
     * @return the new Statement
     * @throws java.sql.SQLException
     * @see CouchStatement
     * @since 1.0
     */
    public Statement createStatement() throws SQLException {
        return new CouchStatement(con);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String nativeSQL(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getAutoCommit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void commit() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rollback() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Closes the connection
     * @throws java.sql.SQLException
     * @see HttpClientConnection#close()
     * @since 1.0
     */
    public void close() throws SQLException {
        try {
            con.close();
            closed = true;
        } catch (IOException ex) {
            throw new SQLException(ex);
        }
    }
    /**
     * Verify if the connectios is closed
     * @return true case the connection is closed, false otherwise
     * @throws java.sql.SQLException
     * @since 1.0
     */
    public boolean isClosed() throws SQLException {
        return closed;
    }
    /**
     * Returns the MetaData of this database
     * @return DatabaseMetadata
     * @throws java.sql.SQLException
     * @see CouchDatabaseMetaData
     * @since 1.0
     */
    public DatabaseMetaData getMetaData() throws SQLException {
        return new CouchDatabaseMetaData(con, info);
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isReadOnly() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCatalog(String catalog) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCatalog() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTransactionIsolation(int level) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getTransactionIsolation() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map getTypeMap() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTypeMap(Map map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setHoldability(int holdability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Savepoint setSavepoint() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Clob createClob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Blob createBlob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NClob createNClob() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLXML createSQLXML() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isValid(int timeout) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getClientInfo(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Properties getClientInfo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object unwrap(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
