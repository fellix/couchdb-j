/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc.resultset;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.apache.couchdb.jdbc.statement.CouchStatement;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author root
 * @version 1.0
 */
public class CouchResultSet implements ResultSet{

    private JSONObject object;
    private CouchStatement smt;
    private String sql;

    public CouchResultSet(String entity, CouchStatement smt, String sql) throws SQLException{
        try {
            object = new JSONObject(entity);
            this.smt = smt;
            this.sql = sql;
        } catch (JSONException ex) {
            new SQLException(ex);
        }
    }

    public boolean next() throws SQLException {
        return false;
    }

    public void close() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean wasNull() throws SQLException {
        return object == null;
    }

    public String getString(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte getByte(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public short getShort(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getInt(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getLong(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getFloat(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getDouble(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getString(String columnLabel) throws SQLException {
        try {
            return object.getString(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public boolean getBoolean(String columnLabel) throws SQLException {
        try {
            return object.getBoolean(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public byte getByte(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public short getShort(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getInt(String columnLabel) throws SQLException {
        try {
            return object.getInt(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public long getLong(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getFloat(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getDouble(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getBytes(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCursorName() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(String columnLabel) throws SQLException {
        try {
            return object.get(columnLabel);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public int findColumn(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getCharacterStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isBeforeFirst() throws SQLException {
        return false;
    }

    public boolean isAfterLast() throws SQLException {
        return false;
    }

    public boolean isFirst() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isLast() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void beforeFirst() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void afterLast() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean first() throws SQLException {
        return true;
    }

    public boolean last() throws SQLException {
        return true;
    }

    public int getRow() throws SQLException {
        return 1;
    }

    public boolean absolute(int row) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean relative(int rows) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean previous() throws SQLException {
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

    public int getType() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getConcurrency() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean rowUpdated() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean rowInserted() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean rowDeleted() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNull(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNull(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        try {
            object.put(columnLabel, x);
            //smt.executeUpdate(sql);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public void updateByte(String columnLabel, byte x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateShort(String columnLabel, short x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateInt(String columnLabel, int x) throws SQLException {
        try {
            object.put(columnLabel, x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public void updateLong(String columnLabel, long x) throws SQLException {
        try {
            object.put(columnLabel, x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateDouble(String columnLabel, double x) throws SQLException {
        try {
            object.put(columnLabel, x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateString(String columnLabel, String x) throws SQLException {
        try {
            object.put(columnLabel, x);
            System.out.println(sql+columnLabel+"="+x);
            smt.executeUpdate(sql);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateDate(String columnLabel, Date x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTime(String columnLabel, Time x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateObject(String columnLabel, Object x) throws SQLException {
        try {
            object.put(columnLabel, x);
        } catch (JSONException ex) {
            throw new SQLException(ex);
        }
    }

    public void insertRow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteRow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void refreshRow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cancelRowUpdates() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moveToInsertRow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moveToCurrentRow() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Statement getStatement() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(int columnIndex, Map map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ref getRef(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Blob getBlob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Clob getClob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Array getArray(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getObject(String columnLabel, Map map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Ref getRef(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Blob getBlob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Clob getClob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Array getArray(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public URL getURL(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public URL getURL(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRef(String columnLabel, Ref x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClob(String columnLabel, Clob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateArray(String columnLabel, Array x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RowId getRowId(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isClosed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNString(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getNString(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object unwrap(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isWrapperFor(Class iface) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
