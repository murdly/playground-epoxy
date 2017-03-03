package com.akarbowy.epoxyexample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Units {

    @SerializedName("originalPrice") @Expose public Price originalPrice;
    @SerializedName("price") @Expose public Price salePrice;


    public static class Price {
        @SerializedName("value") @Expose public double value;
        @SerializedName("formatted") @Expose public String formatted;
    }
}
