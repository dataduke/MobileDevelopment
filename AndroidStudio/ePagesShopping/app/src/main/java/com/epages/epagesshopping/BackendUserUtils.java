package com.epages.epagesshopping;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class BackendUserUtils {
    // TODO: Set correct URL
    private static final String URL = "http://pm.epages.com/rs/shops/dpauli/";
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String EMAIL = "email";

    private static final String TAG = "BackendUserUtils";

    public static ProductData getUser(String id) throws JSONException, IOException {

        String name = null;

        // Get the JSON-object for request. Build URL with given ID
        JSONObject jsonobject = new JSONObject(BackendUserUtils.getFromServer(MessageFormat.format(URL, id)));

        // Set name, if object has a name
        if (jsonobject.has(NAME)) {
            name = jsonobject.getString(NAME);
        }

        return new ProductData(name);
    }

    // Method to get informations from server
    public static String getFromServer(String url) throws IOException {

        StringBuilder sb = new StringBuilder();
        URL _url = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) _url.openConnection();

        // TODO: Switch to OAuth
        /* Authorize with backend
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        String authString = "USER" + ":" + "";
        byte[] authEncBytes = android.util.Base64.encode(authString.getBytes(), android.util.Base64.DEFAULT);
        String authStringEnc = new String(authEncBytes);
        httpURLConnection.addRequestProperty("Authorization", "Basic "+ authStringEnc);
        */
        // Get response code
        final int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(
                    httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Get string with given informations
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        // Disconnect from connection
        httpURLConnection.disconnect();
        return sb.toString();
    }
}

