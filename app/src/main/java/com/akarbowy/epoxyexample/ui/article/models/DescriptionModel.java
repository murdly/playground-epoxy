package com.akarbowy.epoxyexample.ui.article.models;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.ui.helpers.BaseEpoxyHolder;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_description_view)
public abstract class DescriptionModel extends EpoxyModelWithHolder<DescriptionModel.DescriptionHolder> {
    @EpoxyAttribute(hash = false) View.OnClickListener clickListener;
    @EpoxyAttribute boolean isExpanded = false;

    @Override public boolean shouldSaveViewState() {
        return true;
    }

    @Override public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override public void bind(final DescriptionHolder holder) {

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                holder.expandView.setRotation(isExpanded ? 0 : 180);
                clickListener.onClick(view);
                isExpanded = !isExpanded;
            }
        });
    }

    static class DescriptionHolder extends BaseEpoxyHolder {
        @BindView(R.id.description_text) TextView textView;
        @BindView(R.id.description_expand_icon) ImageView expandView;
    }
}
