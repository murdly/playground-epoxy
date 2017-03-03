package com.akarbowy.epoxyexample.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {
    @SerializedName("id") @Expose public String id;
    @SerializedName("name") @Expose public String name;
    @SerializedName("brand") @Expose public Brand brand;
    @SerializedName("attributes") @Expose public List<Attribute> attributes;
    @SerializedName("units") @Expose public List<Units> units;
    @SerializedName("media") @Expose public Media media;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        return id.equals(article.id);

    }

    @Override public int hashCode() {
        return id.hashCode();
    }
}
