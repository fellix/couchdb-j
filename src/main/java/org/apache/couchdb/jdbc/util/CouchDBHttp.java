/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apache.couchdb.jdbc.util;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;

/**
 * Default server configuration to the Driver
 * @author Rafael Felix da Silva
 * @version 1.0
 */
public class CouchDBHttp {
    
    private static CouchDBHttp instance;

    private String url;
    private int port;
    private String database;
    private HttpParams defaultParams;
    private BasicHttpProcessor defaultProcessor;
    private HttpContext defaultContext;
    private HttpHost host;

    private CouchDBHttp(){

    }

    public static CouchDBHttp getInstance() {
        if(instance == null){
            instance = new CouchDBHttp();
        }
        return instance;
    }

    public void parseProperties(Properties properties){
        url = properties.getProperty("host");
        port = Integer.parseInt(properties.getProperty("port"));
        //database = properties.getProperty("database");
    }
    //Need to set the connection
    public HttpContext getDefaultContext() {
        if(defaultContext == null){
            defaultContext = new BasicHttpContext();
            defaultContext.setAttribute(ExecutionContext.HTTP_PROXY_HOST, getHost());
        }
        return defaultContext;
    }

    public HttpParams getDefaultParams() {
        if(defaultParams == null){
            defaultParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(defaultParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(defaultParams, "UTF-8");
        }
        return defaultParams;
    }

    public BasicHttpProcessor getDefaultProcessor() {
        if(defaultProcessor == null){
            defaultProcessor = new BasicHttpProcessor();
            defaultProcessor.addInterceptor(new RequestContent());
            defaultProcessor.addInterceptor(new RequestTargetHost());
        }
        return defaultProcessor;
    }

    public HttpHost getHost() {
        if(host == null){
            host = new HttpHost(url, port);
        }
        return host;
    }
    
    public Socket getSocket() throws IOException{
        return new Socket(url, port);
    }
/*
    public String getDatabase() {
        return database;
    }*/

}
