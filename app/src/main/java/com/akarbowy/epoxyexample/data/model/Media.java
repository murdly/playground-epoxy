package com.akarbowy.epoxyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {
    @SerializedName("images") @Expose public List<MediaImage> images;
}
