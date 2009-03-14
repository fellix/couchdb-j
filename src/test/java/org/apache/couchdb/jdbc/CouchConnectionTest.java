/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.util.Properties;
import org.apache.couchdb.jdbc.statement.CouchStatement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for CouchConnection
 * @author Rafael Felix
 */
public class CouchConnectionTest {

    private Properties info;
    public CouchConnectionTest() {
    }

    @Before
    public void setUp() {
        info = new Properties();
        info.put("host", "127.0.0.1");
        info.put("port", "5984");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createStatement method, of class CouchConnection.
     */
    @Test
    public void testCreateStatement_0args() throws Exception {
        CouchConnection instance = new CouchConnection(info);
        Statement expResult = new CouchStatement(instance);
        Statement result = instance.createStatement();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of close method, of class CouchConnection.
     */
    @Test
    public void testClose() throws Exception {
        CouchConnection instance = new CouchConnection(info);
        instance.close();
        assertTrue(instance.isClosed());
    }

    /**
     * Test of isClosed method, of class CouchConnection.
     */
    @Test
    public void testIsClosed() throws Exception {
        CouchConnection instance = new CouchConnection(info);
        boolean result = instance.isClosed();
        assertFalse(result);
    }

    /**
     * Test of getMetaData method, of class CouchConnection.
     */
    @Test
    public void testGetMetaData() throws Exception {
        CouchConnection instance = new CouchConnection(info);
        DatabaseMetaData expResult = new CouchDatabaseMetaData(instance.getServerInfo(), instance.getBaseUrl());
        DatabaseMetaData result = instance.getMetaData();
        assertTrue(expResult.equals(result));
    }

}