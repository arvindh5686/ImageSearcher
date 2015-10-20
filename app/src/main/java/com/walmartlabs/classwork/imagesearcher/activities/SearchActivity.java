package com.walmartlabs.classwork.imagesearcher.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.walmartlabs.classwork.imagesearcher.R;
import com.walmartlabs.classwork.imagesearcher.adapters.ImageResultsAdapter;
import com.walmartlabs.classwork.imagesearcher.models.Filter;
import com.walmartlabs.classwork.imagesearcher.models.Image;
import com.walmartlabs.classwork.imagesearcher.net.ImageSearchClient;
import com.walmartlabs.classwork.imagesearcher.utility.EndlessScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    private static final int SETTINGS_CODE = 1001;
    private EditText etQuery;
    private GridView gvResults;
    private ArrayList<Image> imageResults;
    private ImageResultsAdapter aImageResults;
    private ImageSearchClient client;
    private JsonHttpResponseHandler handler;
    private Filter filter;
    private String queryString;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        client = new ImageSearchClient();

        setupViews();
    }

    private void setupViews() {
        gvResults = (GridView) findViewById(R.id.gvResults);
        imageResults = new ArrayList<Image>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);

        handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray resultsJson = null;
                try {
                    resultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    aImageResults.addAll(Image.fromJsonArray(resultsJson));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        };

        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SearchActivity.this, DisplayActivity.class);
                Image image = (Image) aImageResults.getItem(position);
                i.putExtra("image", image);
                startActivity(i);
            }
        });

        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                if(page > 8) return false;
                onImageSearch(page);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.menuSettings) {
            //onAdd(item);
            Intent intent = new Intent(SearchActivity.this, SettingActivity.class);
            intent.putExtra("filter", filter);
            startActivityForResult(intent, SETTINGS_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                aImageResults.clear();
                queryString = query;
                onImageSearch(0);
                // perform query here
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                aImageResults.clear();
               // queryString = newText;
               // onImageSearch(1, handler);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((SETTINGS_CODE == requestCode) && (resultCode == RESULT_OK)) {
            filter = data.getParcelableExtra("filter");
            aImageResults.clear();
            onImageSearch(0);
        }
    }

    public void onImageSearch(int page) {
        if (queryString == null) return;

        if( !isNetworkAvailable() ) {
            Toast.makeText(this, "No network available...", Toast.LENGTH_SHORT).show();
            return;
        }

        client.getImages(filter, queryString, page, handler);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
