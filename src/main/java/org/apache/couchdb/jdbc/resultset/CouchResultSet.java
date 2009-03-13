/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc.resultset;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.couchdb.jdbc.statement.CouchStatement;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CouchDB ResultSet implementation
 * @author Rafael Felix
 * @version 1.0
 */
public class CouchResultSet extends AbstractResultSet implements ResultSet{

    private JSONObject object;
    private CouchStatement smt;
    private String sql;
    private String url;
    private boolean closed = false;
    /**
     * Map to maintains an index -> key relationship
     */
    private Map<Integer, String> map;

    public CouchResultSet(String entity, CouchStatement smt, String sql, String url) throws SQLException{
        try {
            object = new JSONObject(entity);
            map = new HashMap<Integer, String>();
            Iterator<String> it = object.keys();
            int index = 0;
            while(it.hasNext()){
                String key = it.next();
                map.put(index, key);
            }
            this.smt = smt;
            this.sql = sql;
            this.url = url;
        } catch (JSONException ex) {
            new SQLException(ex);
        }
    }

    @Override
    public boolean next() throws SQLException {
        return false;
    }
    
    @Override
    public void close() throws SQLException {
        closed = true;
    }
    
    @Override
    public boolean wasNull() throws SQLException {
        return object == null;
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        try {
            return object.getString(map.get(columnIndex));
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        try {
            return object.getBoolean(map.get(columnIndex));
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        try {
            return object.getInt(map.get(columnIndex));
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        try {
            return object.getLong(map.get(columnIndex));
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        try {
            return object.getDouble(map.get(columnIndex));
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        try {
            return object.getString(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        try {
            return object.getBoolean(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        try {
            return object.getInt(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        try {
            return object.getLong(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        try {
            return object.getDouble(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new CouchResultSetMetaData(object, map, sql);
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        try {
            return object.get(map.get(columnIndex));
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        try {
            return object.get(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        if(!map.containsValue(columnLabel)){
            throw new SQLException("Column with label "+columnLabel+" not found");
        }
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            if(entry.getValue().equals(columnLabel)){
                return entry.getKey();
            }
        }
        throw new SQLException("Column with label "+columnLabel+" not found");
    }

    @Override
    public int getRow() throws SQLException {
        return 1;
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        try {
            String columnLabel = map.get(columnIndex);
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        try {
            String columnLabel = map.get(columnIndex);
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        try {
            String columnLabel = map.get(columnIndex);
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        try {
            String columnLabel = map.get(columnIndex);
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        try {
            String columnLabel = map.get(columnIndex);
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        try {
            String columnLabel = map.get(columnIndex);
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        try {
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        try {
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        try {
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        try {
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        try {
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        try {
            object.put(columnLabel, x);
            smt.executeUpdate(sql+"#"+columnLabel+"="+x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public Statement getStatement() throws SQLException {
        return smt;
    }

    @Override
    public Object getObject(int columnIndex, Map map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isClosed() throws SQLException {
        return closed;
    }

    /**
     * The JSON Object of this ResultSet
     * @return jsonObject
     * @since 1.0
     */
    public JSONObject getObject() {
        return object;
    }

}
