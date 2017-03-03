package com.akarbowy.epoxyexample.ui.article;


import android.view.View;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.data.model.Attribute;
import com.akarbowy.epoxyexample.ui.article.models.AttributeModel_;
import com.akarbowy.epoxyexample.ui.article.models.CarouselEpoxyModel_;
import com.akarbowy.epoxyexample.ui.article.models.DescriptionModel_;
import com.akarbowy.epoxyexample.ui.article.models.HeaderModel_;
import com.akarbowy.epoxyexample.ui.article.models.MediaModel_;
import com.akarbowy.epoxyexample.ui.article.models.SpaceModel;
import com.akarbowy.epoxyexample.ui.article.models.ThumbnailModel;
import com.akarbowy.epoxyexample.ui.article.models.TitleModel_;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends EpoxyAdapter {

    private final List<EpoxyModel<?>> attributeModels = new ArrayList<>();
    private final DescriptionModel_ descriptionModel = new DescriptionModel_();

    @SuppressWarnings("FieldCanBeLocal") private View.OnClickListener onDescriptionClick = new View.OnClickListener() {
        @Override public void onClick(View view) {
            for (int i = attributeModels.size() - 1; i >= 0; i--) {
                if (!descriptionModel.isExpanded()) {
                    insertModelAfter(attributeModels.get(i), descriptionModel);
                } else {
                    removeModel(attributeModels.get(i));
                }
            }
        }
    };
    private Callback callback;
    private ThumbnailModel.OnModelClick onCarouselItemClick = new ThumbnailModel.OnModelClick() {
        @Override public void onClick(ThumbnailModel model) {
            callback.onArticleClicked(model.article);
        }
    };

    public ArticleAdapter(Article article) {
        enableDiffing();

        addModel(new HeaderModel_().article(article));

        for (int i = 0; i < article.media.images.size() && i < 6; i++) {
            addModel(new MediaModel_().image(article.media.images.get(i)));
        }

        if (!article.attributes.isEmpty()) {
            addModel(descriptionModel
                    .isExpanded(false)
                    .clickListener(onDescriptionClick));

            for (Attribute a : article.attributes) {
                AttributeModel_ model_ = new AttributeModel_().attribute(a);
                attributeModels.add(model_);
            }
        }

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setRecommendations(String title, List<Article> recommendations) {
        if (!recommendations.isEmpty()) {
            CarouselAdapter carouselAdapter = new CarouselAdapter(recommendations, onCarouselItemClick);

            addModels(new TitleModel_().title(title),
                    new CarouselEpoxyModel_<CarouselAdapter>().adapter(carouselAdapter));
        }
    }

    public void setBrandOffer(String title, List<Article> offers) {
        if (!offers.isEmpty()) {
            CarouselAdapter carouselAdapter = new CarouselAdapter(offers, onCarouselItemClick);

            addModels(new TitleModel_().title(title),
                    new CarouselEpoxyModel_<CarouselAdapter>().adapter(carouselAdapter));
        }

        addModel(new SpaceModel());
    }

    public interface Callback {
        void onArticleClicked(Article article);
    }


}
