/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc.statement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.couchdb.jdbc.resultset.CouchResultSet;
import org.apache.couchdb.jdbc.util.Converter;
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

    /**
     * Defaults constructor
     * @param connection the active connection
     * @since 1.0
     */
    public CouchStatement(String url) {
        con = new HttpClient();
        this.url = url;
    }

    /**
     * Execute an HTTP method.
     * @param method the method
     * @param sql the string to execute
     * @return Response of the server
     * @throws java.sql.SQLException
     * @see CouchDBHttp
     * @since 1.0
     *
    private HttpResponse executeHttp(String method, String sql) throws SQLException {
    return executeHttp(method, sql, false);
    }

    /**
     * Execute an HTTP Metodo using an entity
     * @param method
     * @param sql
     * @param entity
     * @return
     * @throws java.sql.SQLException
     *
    private HttpResponse executeHttp(String method, String sql, boolean sender) throws SQLException {
    HttpRequest request = new BasicHttpRequest(method, sql);
    request.setParams(http.getDefaultParams());
    if(sender){
    request.addHeader(new BasicHeader("teste", "editado"));
    }
    HttpRequestExecutor executor = new HttpRequestExecutor();
    try {
    executor.preProcess(request, http.getDefaultProcessor(), http.getDefaultContext());
    HttpResponse response = executor.execute(request, connection, http.getDefaultContext());
    response.setParams(http.getDefaultParams());
    executor = null;
    return response;
    } catch (HttpException ex) {
    throw new SQLException(ex);
    } catch (IOException ex) {
    throw new SQLException(ex);
    }
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
        return new CouchResultSet(new String(processGet(sql)), this, sql, url);

    }

    /**
     * Process an get request to the server
     * @param sql the params to get
     * @return byte array of response
     * @throws java.sql.SQLException
     * @since 1.0
     */
    private byte[] processGet(String sql) throws SQLException {
        HttpMethod method = new GetMethod(url + sql);
        try {
            int statusCode = con.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                throw new SQLException("Invalid request!");
            }
            byte[] response = method.getResponseBody();
            return response;
        } catch (HttpException ex) {
            throw new SQLException(ex);
        } catch (IOException ex) {
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
        byte[] response = processGet(base);
        try {
            JSONObject obj = new JSONObject(new String(response));
            //Sttrips the params to find the key and the value
            stripParams(splited[1], obj);
            PutMethod post = new PutMethod(url.concat(base));
            post.setQueryString(Converter.jsonToNameValuePair(obj));
            int status = con.executeMethod(post);
            if(status != HttpStatus.SC_OK){
                throw new SQLException("Failed to update the object. Error Code: "+status);
            }
            return status;
        }catch (HttpException ex) {
            throw new SQLException(ex);
        }catch (IOException ex) {
            throw new SQLException(ex);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    private void stripParams(String params, JSONObject obj) throws JSONException{
        String[] param = params.split("&");
        for(String s : param){
            String[] kv = s.split("=");
            obj.put(kv[0], kv[1]);
        }
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

    public boolean execute(String sql) throws SQLException {
        executeQuery(sql);
        return true;
    }

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

    public Connection getConnection() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
