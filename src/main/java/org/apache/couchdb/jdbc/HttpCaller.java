/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.couchdb.jdbc.util.StringConstants;
import org.json.JSONObject;

/**
 * Class for make HTTP class to the CouchDB server
 * @author Rafael Felix
 * @version 1.0
 */
public class HttpCaller {

    private static HttpCaller instance;
    private HttpClient con;
    private String serverUrl;
    /**
     * Retrieve the last HttpMethod executed succesfully
     */
    private HttpMethod lastMethod;

    private HttpCaller() {
        con = HttpClientUtil.getInstance().getClient();
        //Default server is localhost
        serverUrl = "http://127.0.0.1:5984";
    }

    /**
     * Static method to make this singleton
     * @return single instance of this object
     * @since 1.0
     */
    public static HttpCaller getInstance() {
        if (instance == null) {
            instance = new HttpCaller();
        }
        return instance;
    }

    /**
     * Generate an Entity base on JSON Object
     * @param obj the JSON object to convert into an RequestEntity
     * @return the request entity of this object
     * @throws java.io.UnsupportedEncodingException
     * @since 1.0
     * @see #generateEntity(java.lang.String) 
     */
    public RequestEntity generateEntity(JSONObject obj) throws UnsupportedEncodingException {
        return generateEntity(obj.toString());
    }

    /**
     * Generate an Entity base on an String
     * @param obj the String to convert into an RequestEntity
     * @return the request entity of this object
     * @throws java.io.UnsupportedEncodingException
     * @since 1.0
     * @see #generateEntity(org.json.JSONObject)
     * @see StringRequestEntity
     */
    public RequestEntity generateEntity(String obj) throws UnsupportedEncodingException {
        return new StringRequestEntity(obj, StringConstants.MIME_JSON, StringConstants.CONTENT_TYPE);
    }

    /**
     * Process a GET in the server using the server url and usign an query parameter
     * @param query can by null for GET /
     * @return Request Body in byte array
     * @throws org.apache.commons.httpclient.HttpException
     * @since 1.0
     */
    public byte[] processGet(String query) throws HttpException {
        if (serverUrl == null) {
            throw new NullPointerException("Server URL cann't be null");
        }
        query = query == null ? "" : query;
        query = addInitialBar(query);
        GetMethod get = new GetMethod(serverUrl.concat(query));
        execute(get);
        lastMethod = get;
        return retrieveRespondeBody();
    }

    /**
     * Process a PUT in the server using the server url, the path to PUT and the entity to be into the path
     * @param path of the object
     * @param entity the entity to put
     * @return Request Body byte array
     * @throws org.apache.commons.httpclient.HttpException
     * @since 1.0
     */
    public byte[] processPut(String path, RequestEntity entity) throws HttpException {
        if (entity == null || path == null) {
            throw new NullPointerException("Couldn't PUT an null entity");
        }
        path = path == null ? "" : path;
        path = addInitialBar(path);
        PutMethod put = new PutMethod(serverUrl.concat(path));
        put.addRequestHeader(getDefaultHeader());
        put.setRequestEntity(entity);
        execute(put);
        lastMethod = put;
        return retrieveRespondeBody();
    }

    /**
     * Delete an Document from the Database
     * @param path of the document
     * @param id of document
     * @param rev revision to delete
     * @return Request Body byte array
     * @throws org.apache.commons.httpclient.HttpException
     * @since 1.0
     */
    public byte[] processDelete(String path, String id, String rev) throws HttpException{
        if(id == null || rev == null){
            throw new NullPointerException("Invalid DELETE request. Verify if the call use '/database/document&revision'");
        }
        path = path == null ? "" : path;
        path = addInitialBar(path);
        DeleteMethod delete = new DeleteMethod(serverUrl.concat(path));
        delete.setRequestHeader(getDefaultHeader());
        delete.setQueryString(new NameValuePair[]{ new NameValuePair("_id", id), new NameValuePair("rev", rev) });
        execute(delete);
        lastMethod = delete;
        return retrieveRespondeBody();
    }

    /**
     * Process an POST to the server
     * @param path of the post
     * @param entity to post
     * @return Response Body byte array
     * @throws org.apache.commons.httpclient.HttpException
     * @since 1.0
     */
    public byte[] processPost(String path, RequestEntity entity) throws HttpException{
        if(entity == null){
            throw new NullPointerException("Invalid POST request. Verify if the call use '/database/document&revision'");
        }
        path = path == null ? "" : path;
        path = addInitialBar(path);
        PostMethod post = new PostMethod(serverUrl.concat(path));
        post.setRequestHeader(getDefaultHeader());
        post.setRequestEntity(entity);
        execute(post);
        lastMethod = post;
        return retrieveRespondeBody();
    }

    /**
     * Execute an HTTPMethod in the server
     * @param method to execute
     * @throws org.apache.commons.httpclient.HttpException
     * @since 1.0
     */
    private void execute(HttpMethod method) throws HttpException {
        try {
            int status = con.executeMethod(method);
            if (!sucess(status)) {
                throw new HttpException("HTTP ERROR: " + status + " " + StringConstants.errorMessage(status));
            }
        } catch (IOException ex) {
            throw new HttpException("Couldn't execute the method.", ex);
        }
    }

    /**
     * Retreive the response Body of an method
     * @return Response Body byte array
     * @throws org.apache.commons.httpclient.HttpException
     * @see HttpMethod#getResponseBody()
     * @since 1.0
     */
    private byte[] retrieveRespondeBody() throws HttpException{
        try {
            return lastMethod.getResponseBody();
        } catch (IOException ex) {
            throw new HttpException("Couldn't execute the method.", ex);
        }
    }

    /**
     * Return the default header for Content-Type
     * @return header of Content-type
     * @since 1.0
     */
    private Header getDefaultHeader() {
        return new Header("Content-Type", StringConstants.MIME_JSON);
    }

    /**
     * Add / in the start of the query
     * @param query the query to gain the /
     * @return query what start with /
     * @since 1.0
     */
    private String addInitialBar(String query) {
        if (!query.startsWith("/")) {
            query = "/".concat(query);
        }
        return query;
    }

    /**
     * Verify the status of the request,
     * @param status of verify
     * @return true if the status is sucess false otherwise
     * @since 1.0
     */
    private boolean sucess(int status) {
        return status == HttpStatus.SC_OK || status == 201;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * Gets the las method executed, null if no have executed methods
     * @return last executed method
     * @since 1.0
     */
    public HttpMethod getLastMethod() {
        return lastMethod;
    }

}
