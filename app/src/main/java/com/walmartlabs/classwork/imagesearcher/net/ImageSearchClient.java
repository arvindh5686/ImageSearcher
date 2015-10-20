package com.walmartlabs.classwork.imagesearcher.net;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.walmartlabs.classwork.imagesearcher.models.Filter;

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
    public void getImages(final Filter filter, String text, int page, JsonHttpResponseHandler handler) {
        try {
            int start = page * 8;
            String url = getApiUrl("ajax/services/search/images?q=");
            String query = generateQueryString(filter, text);
            Log.i("url", url + URLEncoder.encode(query, "utf-8") + "&v=1.0&rsz=8&start=" + start);
            client.get(url + URLEncoder.encode(query, "utf-8") + "&v=1.0&rsz=8&start=" + start, handler);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String generateQueryString(Filter filter, String queryString) {
        if(filter == null) return queryString;
        if(filter.getImageSize() != null) queryString += "&imgsz=" + filter.getImageSize();
        if(filter.getImageType() != null) queryString += "&imgtype=" + filter.getImageType();
        if(filter.getColorFilter() != null) queryString += "&imgcolor=" + filter.getColorFilter();
        if(filter.getSiteFilter() != null &&
                ! filter.getSiteFilter().equalsIgnoreCase("")) queryString += "&as_sitesearch=" + filter.getSiteFilter();

        return queryString;
    }
}
