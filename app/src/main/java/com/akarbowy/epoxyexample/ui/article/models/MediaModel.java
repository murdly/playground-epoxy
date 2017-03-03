package com.akarbowy.epoxyexample.ui.article.models;


import android.view.View;
import android.widget.ImageView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.SimpleEpoxyModel;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.model.MediaImage;
import com.squareup.picasso.Picasso;

public class MediaModel extends SimpleEpoxyModel {

    @EpoxyAttribute MediaImage image;

    public MediaModel() {
        super(R.layout.model_media_view);
    }

    @Override public void bind(View view) {
        super.bind(view);

        Picasso.with(view.getContext())
                .load(image.mediumHdUrl)
                .fit()
                .centerCrop()
                .placeholder(R.color.gray)
                .error(R.color.colorAccent)
                .into((ImageView) view);
    }

}
