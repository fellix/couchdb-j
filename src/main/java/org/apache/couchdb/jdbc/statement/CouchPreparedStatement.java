/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc.statement;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import org.apache.couchdb.jdbc.CouchConnection;

/**
 *
 * @author root
 * @version 1.0
 */
public class CouchPreparedStatement extends CouchStatement implements PreparedStatement{

    public CouchPreparedStatement(String url, CouchConnection connection){
        super(url, connection);
    }

    public ResultSet executeQuery() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int executeUpdate() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setInt(int parameterIndex, int x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setLong(int parameterIndex, long x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setString(int parameterIndex, String x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clearParameters() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addBatch() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRef(int parameterIndex, Ref x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(int parameterIndex, Clob x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setArray(int parameterIndex, Array x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setURL(int parameterIndex, URL x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNString(int parameterIndex, String value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
