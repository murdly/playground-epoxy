package com.akarbowy.epoxyexample.ui.article.models;


import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.data.model.Units;
import com.akarbowy.epoxyexample.ui.helpers.BaseEpoxyHolder;

import butterknife.BindView;
import timber.log.Timber;

@EpoxyModelClass(layout = R.layout.model_header_view)
public abstract class HeaderModel extends EpoxyModelWithHolder<HeaderModel.HeaderHolder> {

    @EpoxyAttribute Article article;

    @Override public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override public void bind(HeaderHolder holder) {
        Timber.i("binding");
        holder.brandNameView.setText(article.brand.name);
        holder.articleNameView.setText(article.name);

        if (article.units != null && article.units.size() > 0) {
            Units unit = article.units.get(0);
            boolean sale = unit.salePrice.value < unit.originalPrice.value;

            holder.priceSaleView.setVisibility(sale ? View.VISIBLE : View.GONE);
            holder.priceOriginalView.setTextSize(TypedValue.COMPLEX_UNIT_SP, sale ? 16 : 18);
            holder.priceOriginalView.setPaintFlags(sale ?
                    holder.priceOriginalView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                    : holder.priceOriginalView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

            holder.priceOriginalView.setText(unit.originalPrice.formatted);
            holder.priceSaleView.setText(unit.salePrice.formatted);
        }
    }

    static class HeaderHolder extends BaseEpoxyHolder {
        @BindView(R.id.header_brand_name) TextView brandNameView;
        @BindView(R.id.header_article_name) TextView articleNameView;
        @BindView(R.id.header_price_original) TextView priceOriginalView;
        @BindView(R.id.header_price_sale) TextView priceSaleView;
    }
}
