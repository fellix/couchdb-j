/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.couchdb.jdbc.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to make object convertions
 * @author Rafael Felix
 * @version 1.0
 */
public class Converter {

    /**
     * Converts a JSONObject into a NameValuePair array
     * @param obj the json object to conver
     * @return array of nameValuePair
     * @throws org.json.JSONException
     */
    public static NameValuePair[] jsonToNameValuePair(JSONObject obj) throws JSONException {
        if (obj == null) {
            throw new JSONException("Couldn't convert an null object");
        }
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator<String> it = obj.keys();
        while (it.hasNext()) {
            String key = it.next();
            if (!key.equals("_id") && !key.equals("_rev")) {
                Object value = obj.get(key);
                list.add(new NameValuePair(key, value.toString()));
            }
        }
        NameValuePair[] array = new NameValuePair[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
