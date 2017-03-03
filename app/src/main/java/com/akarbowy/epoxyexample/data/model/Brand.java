package com.akarbowy.epoxyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand {

    @SerializedName("key") @Expose public String key;
    @SerializedName("name") @Expose public String name;
}
