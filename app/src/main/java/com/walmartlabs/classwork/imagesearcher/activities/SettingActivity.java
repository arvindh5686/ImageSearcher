package com.walmartlabs.classwork.imagesearcher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.walmartlabs.classwork.imagesearcher.R;

public class SettingActivity extends AppCompatActivity {

    private String imageSize;
    private String colorFilter;
    private String imageType;
    private String siteFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent i = getIntent();
        imageSize = i.getStringExtra("imageSize");
        imageType = i.getStringExtra("imageType");
        colorFilter = i.getStringExtra("colorFilter");
        siteFilter = i.getStringExtra("siteFilter");
        EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
        etSiteFilter.setText(siteFilter);
        setupSpinners();
    }

    public void setupSpinners() {
        Spinner spImageSize = (Spinner) findViewById(R.id.spImageSize);
        ArrayAdapter<CharSequence> aImageSizes = ArrayAdapter.createFromResource(this,
                R.array.image_size, android.R.layout.simple_spinner_item);
        aImageSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spImageSize.setAdapter(aImageSizes);

        int pos = aImageSizes.getPosition(imageSize);
        spImageSize.setSelection(pos);

        spImageSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSize = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spColorFilter = (Spinner) findViewById(R.id.spColorFilter);
        ArrayAdapter<CharSequence> aColorFilters = ArrayAdapter.createFromResource(this,
                R.array.color_filter, android.R.layout.simple_spinner_item);
        aColorFilters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColorFilter.setAdapter(aColorFilters);

        spColorFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colorFilter = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pos = aColorFilters.getPosition(colorFilter);
        spColorFilter.setSelection(pos);

        Spinner spImageType = (Spinner) findViewById(R.id.spImageType);
        ArrayAdapter<CharSequence> aImageTypes = ArrayAdapter.createFromResource(this,
                R.array.image_type, android.R.layout.simple_spinner_item);
        aImageTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spImageType.setAdapter(aImageTypes);

        spImageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pos = aImageTypes.getPosition(imageType);
        spImageType.setSelection(pos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

    public void onSave(View view) {
        Intent i = new Intent();
        EditText etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
        String siteFilter = etSiteFilter.getText().toString();

        i.putExtra("imageSize", imageSize);
        i.putExtra("imageType", imageType);
        i.putExtra("colorFilter", colorFilter);
        i.putExtra("siteFilter", siteFilter);

        setResult(RESULT_OK, i);
        finish();
    }
}
