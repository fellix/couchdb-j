/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc.resultset;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CouchDB ResultSetMetdaData Implementation
 * @author Rafael Felix
 * @version 1.0
 */
public class CouchResultSetMetaData implements ResultSetMetaData{

    private JSONObject obj;
    private Map<Integer, String> keys;
    private String dbname;
    private String docname;

    public CouchResultSetMetaData(JSONObject obj, Map<Integer, String> keys, String sql) {
        this.obj = obj;
        this.keys = keys;
        String aux = sql;
        if(sql.startsWith("/")){
            aux = sql.replaceFirst("/", "");
        }
        String[] splited = aux.split("/");
        dbname = "/"+splited[0];//gets the dbname
        docname = splited[1];
    }

    @Override
    public int getColumnCount() throws SQLException {
        return obj.length();
    }
    
    @Override
    public boolean isAutoIncrement(int column) throws SQLException {
        return keys.get(column).equals("_rev");
    }

    /**
     * All is case Sensitive
     * @param column
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isCaseSensitive(int column) throws SQLException {
        return true;
    }

    /**
     * All is searchble
     * @param column
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isSearchable(int column) throws SQLException {
        return true;
    }
    
    /**
     * Currency value is not supported for CouchDB
     * @param column
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isCurrency(int column) throws SQLException {
        return false;
    }

    @Override
    public int isNullable(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSigned(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        return keys.get(column);
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        return getColumnLabel(column);
    }

    @Override
    public String getSchemaName(int column) throws SQLException {
        return dbname.replace("/", "");
    }

    public int getPrecision(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getScale(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTableName(int column) throws SQLException {
        return docname;
    }

    @Override
    public String getCatalogName(int column) throws SQLException {
        return getSchemaName(column);
    }

    @Override
    public int getColumnType(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        try {
            return obj.get(keys.get(column)).getClass().getSimpleName();
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }
    /**
     * Nothing is readonly
     * @param column
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isReadOnly(int column) throws SQLException {
        return false;
    }
    /**
     * All is writable
     * @param column
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isWritable(int column) throws SQLException {
        return true;
    }

    public boolean isDefinitelyWritable(int column) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getColumnClassName(int column) throws SQLException {
        try {
            return obj.get(keys.get(column)).getClass().toString();
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public Object unwrap(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
