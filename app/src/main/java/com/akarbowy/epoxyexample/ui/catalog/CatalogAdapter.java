package com.akarbowy.epoxyexample.ui.catalog;


import com.airbnb.epoxy.EpoxyAdapter;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.ui.catalog.models.CatalogModel;
import com.akarbowy.epoxyexample.ui.catalog.models.CatalogModel_;

import java.util.List;

public class CatalogAdapter extends EpoxyAdapter {

    private CatalogModel.OnModelClick onModelClickListener = new CatalogModel.OnModelClick() {
        @Override public void onClick(CatalogModel model) {
            callback.onArticleClicked(model.article);
        }
    };

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setArticles(List<Article> articles) {
        for (Article a : articles) {
            addModel(new CatalogModel_()
                    .article(a)
                    .clickListener(onModelClickListener));
        }
    }

    public interface Callback {
        void onArticleClicked(Article article);
    }

}