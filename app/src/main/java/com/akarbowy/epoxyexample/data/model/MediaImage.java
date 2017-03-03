package com.akarbowy.epoxyexample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaImage {
    @SerializedName("orderNumber") @Expose public Integer orderNumber;
    @SerializedName("type") @Expose public String type;
    @SerializedName("thumbnailHdUrl") @Expose public String thumbnailHdUrl;
    @SerializedName("smallUrl") @Expose public String smallUrl;
    @SerializedName("smallHdUrl") @Expose public String smallHdUrl;
    @SerializedName("mediumUrl") @Expose public String mediumUrl;
    @SerializedName("mediumHdUrl") @Expose public String mediumHdUrl;
    @SerializedName("largeUrl") @Expose public String largeUrl;
    @SerializedName("largeHdUrl") @Expose public String largeHdUrl;


}
