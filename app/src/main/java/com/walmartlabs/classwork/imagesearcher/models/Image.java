package com.walmartlabs.classwork.imagesearcher.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abalak5 on 10/17/15.
 */
public class Image implements Parcelable{
    private String fullUrl;
    private String thumbUrl;
    private String title;

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<Image> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Image> imageResults = new ArrayList<Image>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject imageJson = null;
            try {
                imageJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Image image = Image.fromJson(imageJson);
            if (image != null) {
                imageResults.add(image);
            }
        }
        return imageResults;
    }

    // Returns a Image given the expected JSON
    public static Image fromJson(JSONObject jsonObject) {
        Image image = new Image();
        try {
            // Deserialize json into object fields
            if (jsonObject.has("url")) {
                image.setFullUrl(jsonObject.getString("url"));
            }

            if(jsonObject.has("tbUrl")) {
                image.setThumbUrl(jsonObject.getString("tbUrl"));
            }

            image.title = jsonObject.has("title") ? jsonObject.getString("title") : "";

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullUrl);
        dest.writeString(this.thumbUrl);
        dest.writeString(this.title);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.fullUrl = in.readString();
        this.thumbUrl = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
