package com.akarbowy.epoxyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesListing {
    @SerializedName("content") @Expose public List<Article> articles;
}
