package com.walmartlabs.classwork.imagesearcher.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.walmartlabs.classwork.imagesearcher.R;
import com.walmartlabs.classwork.imagesearcher.models.Image;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //getActionBar().hide();
        // Fetch views
        ImageView ivDisplayImage = (ImageView) findViewById(R.id.ivDisplayImage);
        Image image = (Image) getIntent().getParcelableExtra("image");

        Picasso.with(this).load(image.getFullUrl()).into(ivDisplayImage, new Callback() {
            @Override
            public void onSuccess() {
                // Setup share intent now that image has loaded
                //setupShareIntent();
            }

            @Override
            public void onError() {
                // ...
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
