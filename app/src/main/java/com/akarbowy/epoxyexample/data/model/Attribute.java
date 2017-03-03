package com.akarbowy.epoxyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attribute {

    @SerializedName("name") @Expose public String name;
    @SerializedName("values") @Expose public List<String> values;
}
