/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc.util;

import org.apache.commons.httpclient.HttpStatus;

/**
 * String constatns for the application
 * @author Rafael Felix
 * @version 1.0
 */
public class StringConstants {

    public static final String MIME_JSON = "application/json";

    public static String errorMessage(int error) {
        String msg = "";
        switch (error) {
            case 304:
                msg = "Etag not modified since last update.";
                break;
            case 400:
                msg = "Request given is not valid in some way.";
                break;
            case 404:
                msg = "Such as a request via the HttpDocumentApi for a document which doesn't exist.";
                break;
            case 405:
                msg = "Request was accessing a non-existent URL.";
                break;
            case 406:
                msg = "Request contained invalid JSON.";
                break;
            case 409:
                msg = "Request attempted to created database which already exists.";
                break;
            case 412:
                msg = "Request resulted in an update conflict.";
                break;
            case 500:
                msg = "Woops! As you can see, this document is incomplete, please update.";
                break;

        }
        return msg;
    }
}
