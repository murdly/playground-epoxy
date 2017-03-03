package com.akarbowy.epoxyexample.ui.catalog.models;


import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.data.model.Units;
import com.akarbowy.epoxyexample.ui.helpers.BaseEpoxyHolder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_catalog_view)
public abstract class CatalogModel extends EpoxyModelWithHolder<CatalogModel.ThumbnailHolder> {

    @EpoxyAttribute public Article article;
    @EpoxyAttribute(hash = false) OnModelClick clickListener;

    @Override public void bind(ThumbnailHolder holder) {
        holder.nameView.setText(article.brand.name);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                clickListener.onClick(CatalogModel.this);
            }
        });

        Picasso.with(holder.imageView.getContext())
                .load(article.media.images.get(0).mediumHdUrl)
                .fit()
                .centerInside()
                .placeholder(R.color.gray)
                .into(holder.imageView);

        if (article.units != null && article.units.size() > 0) {
            Units unit = article.units.get(0);
            boolean sale = unit.salePrice.value < unit.originalPrice.value;

            holder.priceSaleView.setVisibility(sale ? View.VISIBLE : View.GONE);
            holder.priceOriginalView.setPaintFlags(sale ?
                    holder.priceOriginalView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                    : holder.priceOriginalView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

            holder.priceOriginalView.setText(unit.originalPrice.formatted);
            holder.priceSaleView.setText(unit.salePrice.formatted);
        }
    }

    public interface OnModelClick {
        void onClick(CatalogModel model);
    }

    static class ThumbnailHolder extends BaseEpoxyHolder {
        @BindView(R.id.catalog_image) ImageView imageView;
        @BindView(R.id.catalog_name) TextView nameView;
        @BindView(R.id.catalog_price_original) TextView priceOriginalView;
        @BindView(R.id.catalog_price_sale) TextView priceSaleView;
    }
}
