package com.walmartlabs.classwork.imagesearcher.models;

/**
 * Created by abalak5 on 10/18/15.
 */
public class Search {
    private String text;
    private String imageSize;
    private String imageType;
    private String colorFilter;
    private String siteFilter;

    public Search(String text, String imageSize, String imageType, String colorFilter, String siteFilter) {
        this.text = text;
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.colorFilter = colorFilter;
        this.siteFilter = siteFilter;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageColor) {
        this.imageType = imageColor;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSiteFilter(String siteFilter) {
        this.siteFilter = siteFilter;
    }

}
