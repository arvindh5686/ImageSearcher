package com.walmartlabs.classwork.imagesearcher.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abalak5 on 10/18/15.
 */
public class Filter implements Parcelable{
    private String imageSize;
    private String imageType;
    private String colorFilter;
    private String siteFilter;

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getColorFilter() {
        return colorFilter;
    }

    public void setColorFilter(String colorFilter) {
        this.colorFilter = colorFilter;
    }

    public String getSiteFilter() {
        return siteFilter;
    }

    public void setSiteFilter(String siteFilter) {
        this.siteFilter = siteFilter;
    }

    public Filter() {}

    public Filter(String imageSize, String imageType, String colorFilter, String siteFilter) {
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.colorFilter = colorFilter;
        this.siteFilter = siteFilter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageSize);
        dest.writeString(this.imageType);
        dest.writeString(this.colorFilter);
        dest.writeString(this.siteFilter);
    }

    protected Filter(Parcel in) {
        this.imageSize = in.readString();
        this.imageType = in.readString();
        this.colorFilter = in.readString();
        this.siteFilter = in.readString();
    }

    public static final Creator<Filter> CREATOR = new Creator<Filter>() {
        public Filter createFromParcel(Parcel source) {
            return new Filter(source);
        }

        public Filter[] newArray(int size) {
            return new Filter[size];
        }
    };
}
