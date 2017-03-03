package com.akarbowy.epoxyexample.ui.article.models;


import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.ui.helpers.BaseEpoxyHolder;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_title_view)
public abstract class TitleModel extends EpoxyModelWithHolder<TitleModel.TitleHolder> {

    @EpoxyAttribute String title;

    @Override public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override public void bind(TitleHolder holder) {
        super.bind(holder);
        holder.titleView.setText(title);
    }

    static class TitleHolder extends BaseEpoxyHolder {
        @BindView(R.id.title_text) TextView titleView;
    }
}
