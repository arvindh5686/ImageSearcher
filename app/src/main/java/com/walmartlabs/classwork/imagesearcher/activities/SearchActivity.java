package com.walmartlabs.classwork.imagesearcher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

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
    public static String imageSize;
    public static String imageType;
    public static String colorFilter;
    public static String siteFilter;
    private ImageSearchClient client;
    private JsonHttpResponseHandler handler;
    private Filter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        client = new ImageSearchClient();
        handler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray resultsJson = null;
                try {
                    resultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    aImageResults.addAll(Image.fromJsonArray(resultsJson));
                    aImageResults.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(statusCode, headers, response);
            }
        };

        setupViews();
        imageResults = new ArrayList<Image>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);

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
                onImageSearch(null, page, handler);
                return true;
            }
        });
    }

    private void setupViews() {
        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((SETTINGS_CODE == requestCode) && (resultCode == RESULT_OK)) {
            filter = data.getParcelableExtra("filter");
        }
    }

    public void onImageSearch(View view) {
        aImageResults.clear();
        onImageSearch(view, 0, handler);
    }

    public void onImageSearch(View view, int page, JsonHttpResponseHandler handler) {
        String queryString = etQuery.getText().toString();
       // handler.onSuccess();
        if (filter == null) filter = new Filter();
        client.getImages(filter, queryString, page, handler);
    }
}
