package com.akarbowy.epoxyexample.ui.article.models;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.ui.helpers.BaseEpoxyHolder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_thumbnail_view)
public abstract class ThumbnailModel extends EpoxyModelWithHolder<ThumbnailModel.ThumbnailHolder> {

    @EpoxyAttribute public Article article;
    @EpoxyAttribute(hash = false) OnModelClick clickListener;

    @Override public void bind(ThumbnailHolder holder) {
        holder.nameView.setText(article.name);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                clickListener.onClick(ThumbnailModel.this);
            }
        });

        Picasso.with(holder.imageView.getContext())
                .load(article.media.images.get(0).mediumHdUrl)
                .fit()
                .centerInside()
                .placeholder(R.color.gray)
                .into(holder.imageView);
    }

    public interface OnModelClick {
        void onClick(ThumbnailModel model);
    }

    static class ThumbnailHolder extends BaseEpoxyHolder {
        @BindView(R.id.thumbnail_image) ImageView imageView;
        @BindView(R.id.thumbnail_name) TextView nameView;
    }
}
