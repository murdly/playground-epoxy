package com.akarbowy.epoxyexample.ui.catalog.models;


import com.airbnb.epoxy.SimpleEpoxyModel;
import com.akarbowy.epoxyexample.R;

public class LoaderModel extends SimpleEpoxyModel {

    public LoaderModel() {
        super(R.layout.model_loader_view);
    }

    @Override public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

}
