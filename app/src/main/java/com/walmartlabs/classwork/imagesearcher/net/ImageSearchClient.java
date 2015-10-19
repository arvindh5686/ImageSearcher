package com.walmartlabs.classwork.imagesearcher.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.walmartlabs.classwork.imagesearcher.models.Search;

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
    public void getImages(final Search search, JsonHttpResponseHandler handler) {
        try {
            String url = getApiUrl("ajax/services/search/images?q=");
            String queryString = generateQueryString(search);
            client.get(url + URLEncoder.encode(queryString, "utf-8") + "&v=1.0&rsz=8", handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String generateQueryString(Search searchQuery) {
        String queryString = searchQuery.getText();
        if(searchQuery.getImageSize() != null) queryString += "&imgsz=" + searchQuery.getImageSize();
        if(searchQuery.getImageType() != null) queryString += "&imgtype=" + searchQuery.getImageType();
        if(searchQuery.getColorFilter() != null) queryString += "&imgcolor=" + searchQuery.getColorFilter();
        if(searchQuery.getSiteFilter() != null &&
                ! searchQuery.getSiteFilter().equalsIgnoreCase("")) queryString += "&as_sitesearch=" + searchQuery.getSiteFilter();

        return queryString;
    }
}
