/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Rafael Felix
 */
public class CouchDriverTest {

    public CouchDriverTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class CouchDriver.
     */
    @Test
    public void testConnect() throws Exception {
        String url = "jdbc:couchdb://127.0.0.1:5984";
        Properties info = new Properties();
        CouchDriver instance = new CouchDriver();
        Properties expect = new Properties();
        expect.put("host", "127.0.0.1");
        expect.put("port", "5984");
        Connection expResult = new CouchConnection(expect);
        Connection result = instance.connect(url, info);
        assertNotSame(expResult, result);
        
    }

    @Test(expected = SQLException.class)
    public void invalidUrl() throws Exception {
        String url = "asdaqweqweqwe";
        Properties info = null;
        CouchDriver instance = new CouchDriver();
        Connection result = instance.connect(url, info);
    }

    /**
     * Test of acceptsURL method, of class CouchDriver.
     */
    @Test
    public void testRejectURL() throws Exception {
        String url = "";
        CouchDriver instance = new CouchDriver();
        boolean result = instance.acceptsURL(url);
        assertFalse(result);
    }

    /**
     * Test of acceptsURL method, of class CouchDriver.
     */
    @Test
    public void testAcceptsURL() throws Exception {
        String url = "jdbc:couchdb://127.0.0.1:5984";
        CouchDriver instance = new CouchDriver();
        boolean result = instance.acceptsURL(url);
        assertTrue(result);
    }
}