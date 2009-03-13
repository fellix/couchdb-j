/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc;

import org.apache.commons.httpclient.HttpClient;

/**
 * Maintains a single HttpClient instance for all the application
 * @author Rafael Felix
 * @version 1.0
 */
public class HttpClientUtil {
    private static HttpClientUtil instance;

    private HttpClient client;
    /**
     * Defaults Constructor
     * @since 1.0
     */
    private HttpClientUtil(){
        client = new HttpClient();
    }
    /**
     * Return the instance of this object
     * @return singleton of this class
     * @since 1.0
     */
    public static HttpClientUtil getInstance() {
        if(instance == null){
            instance = new HttpClientUtil();
        }
        return instance;
    }
    /**
     * Returns the HttpClient object
     * @return the HttpClient
     * @since 1.0
     */
    public HttpClient getClient() {
        return client;
    }

}
