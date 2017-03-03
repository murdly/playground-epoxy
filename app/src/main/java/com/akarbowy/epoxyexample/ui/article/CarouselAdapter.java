package com.akarbowy.epoxyexample.ui.article;


import com.airbnb.epoxy.EpoxyAdapter;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.ui.article.models.ThumbnailModel;
import com.akarbowy.epoxyexample.ui.article.models.ThumbnailModel_;

import java.util.List;

public class CarouselAdapter extends EpoxyAdapter {

    public CarouselAdapter(List<Article> articles, ThumbnailModel.OnModelClick listener){
        for (Article a : articles) {
            addModel(new ThumbnailModel_()
                    .article(a)
                    .clickListener(listener)
            );
        }
    }

}
