package com.akarbowy.epoxyexample.ui.article.models;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.model.Attribute;
import com.akarbowy.epoxyexample.ui.helpers.BaseEpoxyHolder;

import butterknife.BindView;

@EpoxyModelClass(layout = R.layout.model_attribute_view)
public abstract class AttributeModel extends EpoxyModelWithHolder<AttributeModel.AttributeHolder> {

    @EpoxyAttribute Attribute attribute;

    @Override public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override public void bind(AttributeHolder holder) {
        holder.nameView.setText(attribute.name);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < attribute.values.size(); i++) {
            builder.append(attribute.values.get(i));
            if (i + 1 < attribute.values.size()) {
                builder.append(", ");
            }
        }
        holder.valueView.setText(builder.toString());
    }

    static class AttributeHolder extends BaseEpoxyHolder {
        @BindView(R.id.attribute_name) TextView nameView;
        @BindView(R.id.attribute_value) TextView valueView;

    }
}
