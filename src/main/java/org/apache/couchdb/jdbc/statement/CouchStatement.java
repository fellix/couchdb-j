/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc.statement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.couchdb.jdbc.CouchConnection;
import org.apache.couchdb.jdbc.HttpCaller;
import org.apache.couchdb.jdbc.HttpClientUtil;
import org.apache.couchdb.jdbc.resultset.CouchResultSet;
import org.apache.couchdb.jdbc.util.StringConstants;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CouchDB Statement implementation
 * @author Rafael Felix da Silva
 * @version 1.0
 */
public class CouchStatement implements Statement {

    protected String url;
    private ResultSet resultSet;
    private HttpClient con;
    private JSONObject dbStatus;
    private CouchConnection connection;
    private HttpCaller caller;

    /**
     * Defaults constructor
     * @param connection the active connection
     * @param url the url of the connection
     * @since 1.0
     */
    public CouchStatement(String url, CouchConnection connection) {
        con = HttpClientUtil.getInstance().getClient();
        this.url = url;
        this.connection = connection;
        caller = HttpCaller.getInstance();
    }

    /**
     * Make an query in the database.
     * The queries is ever GET type
     * @param sql the parameters to consult
     * @return ResultSet of the query
     * @throws java.sql.SQLException
     * @since 1.0
     */
    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        if (con == null) {
            throw new SQLException("Not connected");
        }
        try {
            return new CouchResultSet(new String(caller.processGet(sql)), this, sql, url);
        } catch (HttpException ex) {
            throw new SQLException(ex);
        }

    }

    /**
     * Execute an update to the couchDb.
     * Update is POST method.<br />
     * Usage:
     * <pre>
     *  /database/doc#key=value&key2=value2
     * </pre>
     * @param sql the update param
     * @return the status code of the response
     * @throws java.sql.SQLException
     * @since 1.0
     */
    @Override
    public int executeUpdate(String sql) throws SQLException {
        if (!sql.contains("#")) {
            throw new SQLException("Couldn't update with no parameters");
        }
        String[] splited = sql.split("#");
        //Gets the /database/doc
        String base = splited[0];
        try {
            byte[] response = caller.processGet(base);
            JSONObject obj = new JSONObject(new String(response));
            //Sttrips the params to find the key and the value
            stripParams(splited[1], obj);
            caller.processPut(base, caller.generateEntity(obj));
            return 200;
        } catch (HttpException ex) {
            throw new SQLException(ex);
        } catch (IOException ex) {
            throw new SQLException(ex);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    /**
     * Strips the params URL and give the parms to an JSONObject
     * @param params the parameters in the url
     * @param obj the JSONObject to retreive the params
     * @throws org.json.JSONException
     * @since 1.0
     */
    private void stripParams(String params, JSONObject obj) throws JSONException {
        String[] param = params.split("&");
        for (String s : param) {
            String[] kv = s.split("=");
            obj.put(kv[0], kv[1]);
        }
    }

    /**
     * Generic execute METHOD + params.</p>
     * For example:
     * <pre>
     * DELETE database/doc </p>
     * PUT database/doc_id#key=value</p>
     * GET database
     * </pre>
     * @param sql the command to execute
     * @return true if the execution is completed success fully
     * @throws java.sql.SQLException
     */
    @Override
    public boolean execute(String sql) throws SQLException {
        if (!sql.contains(" ")) {
            throw new SQLException("Cann't find the blank space for separte the method with the statement");
        }
        String[] splited = sql.split(" ");
        if (!validMethod(splited[0])) {
            throw new SQLException("Unknown HTTP method.");
        }
        if (splited[1].contains("#")) {
            executeUpdate(splited[1]);
        } else {
            try {
                String method = splited[0];
                if (method.equals(StringConstants.GET)) {
                    byte[] resp = caller.processGet(splited[1]);
                    resultSet = generateResultSet(new String(resp), splited[0]);
                } else if (method.equals(StringConstants.PUT) || method.equals(StringConstants.POST)) {
                    //Prepres to insert a document
                    String db = getDatabase(splited[1]);
                    String doc = getDoc(splited[1]);
                    if (doc != null && db != null) {
                        JSONObject obj = new JSONObject();
                        obj.put("_id", doc);
                        byte[] resp = caller.processPut(db, caller.generateEntity(obj));
                        resultSet = generateResultSet(new String(resp), splited[0]);
                    } else {
                        throw new SQLException("Invalid PUT URL. Verify if respect the pattern '/database/document'!");
                    }
                } else if (method.equals(StringConstants.DELETE)) {
                    String db = getDatabase(splited[1]);
                    String doc = getDoc(splited[1]);
                    if (doc != null && db != null) {
                        if (doc.contains("&")) {
                            String p[] = doc.split("&");
                            byte[] resp = caller.processDelete(db, p[0], p[1]);
                            resultSet = generateResultSet(new String(resp), splited[0]);
                        } else {
                            throw new SQLException("Cann't delete without revision.");
                        }
                    } else {
                        throw new SQLException("No document to delete");
                    }
                }else{
                    throw new SQLException("Unsuported method: "+method);
                }

            } catch (HttpException ex) {
                throw new SQLException(ex);
            } catch (UnsupportedEncodingException ex) {
                throw new SQLException(ex);
            } catch (IOException ex) {
                throw new SQLException(ex);
            } catch (JSONException ex) {
                throw new SQLException(ex);
            }
        }
        return true;
    }

    /**
     * Generates a basic ResultSet
     * @param entity
     * @param query
     * @return
     * @throws java.sql.SQLException
     */
    private CouchResultSet generateResultSet(String entity, String query) throws SQLException{
        return new CouchResultSet(entity, this, query, url);
    }

    /**
     * Gets the document id in the url
     * @param params
     * @return document id in string fromat
     * @since 1.0
     */
    private String getDoc(String params) {
        String p = params.replaceFirst("/", "");
        if (p.contains("/")) {
            String doc = p.split("/")[1];
            return doc;
        }
        return null;
    }

    /**
     * Strips the dabase from /database/document
     * @param params the url default
     * @return the database
     * @since 1.0
     */
    private String getDatabase(String params){
        String p = params.replaceFirst("/", "");
        if (p.contains("/")) {
            String db = p.split("/")[0];
            return db;
        }
        return null;
    }

    /**
     * Verifies is the metodo is an valid method (GET, POST, DELETE, PUT)
     * @param method to verify
     * @return true if the method is valid
     */
    private boolean validMethod(String method) {
        return method.equals("GET") || method.equals("POST") || method.equals("DELETE") || method.equals("PUT");
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return resultSet;
    }

    public int getUpdateCount() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getMoreResults() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFetchDirection(int direction) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getFetchDirection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFetchSize(int rows) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getFetchSize() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetConcurrency() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetType() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addBatch(String sql) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearBatch() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int[] executeBatch() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    public boolean getMoreResults(int current) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getResultSetHoldability() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isClosed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPoolable(boolean poolable) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isPoolable() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object unwrap(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void close() throws SQLException {
        //TODO:
    }

    public int getMaxFieldSize() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMaxFieldSize(int max) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxRows() throws SQLException {
        return 1;
    }

    public void setMaxRows(int max) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getQueryTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cancel() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCursorName(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
