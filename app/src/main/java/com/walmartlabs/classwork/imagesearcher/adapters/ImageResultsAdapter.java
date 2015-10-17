package com.walmartlabs.classwork.imagesearcher.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.walmartlabs.classwork.imagesearcher.R;
import com.walmartlabs.classwork.imagesearcher.models.Image;

import java.util.ArrayList;

public class ImageResultsAdapter extends ArrayAdapter<Image> {
    // View lookup cache
    private static class ViewHolder {
        public ImageView ivImage;
        public TextView tvTitle;
    }

    public ImageResultsAdapter(Context context, ArrayList<Image> aImages) {
        super(context, 0, aImages);
    }

    // Translates a particular `Image` given a position
    // into a relevant row within an AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Image image = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_image, parent, false);
            viewHolder.ivImage = (ImageView)convertView.findViewById(R.id.ivImage);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate data into the template view using the data object
        viewHolder.tvTitle.setText(Html.fromHtml(image.getTitle()));
        Picasso.with(getContext()).load(Uri.parse(image.getThumbUrl()))/*.placeholder(R.drawable.ic_nocover)*/.into(viewHolder.ivImage);
        // Return the completed view to render on screen
        return convertView;
    }
}
