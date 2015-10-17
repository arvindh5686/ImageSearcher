package com.walmartlabs.classwork.imagesearcher.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by abalak5 on 10/17/15.
 */

public class ImageSearchClient {
    private static final String API_BASE_URL = "https://ajax.googleapis.com/";
    private AsyncHttpClient client;

    public ImageSearchClient() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    // Method for accessing the search API
    public void getImages(final String query, JsonHttpResponseHandler handler) {
        try {
            String url = getApiUrl("ajax/services/search/images?q=");
            client.get(url + URLEncoder.encode(query, "utf-8") + "&v=1.0&rsz=8", handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
