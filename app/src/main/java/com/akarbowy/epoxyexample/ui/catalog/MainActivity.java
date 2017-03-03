package com.akarbowy.epoxyexample.ui.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.ZalandoService;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.data.model.ArticlesListing;
import com.akarbowy.epoxyexample.ui.helpers.VerticalGridCardSpacingDecoration;
import com.akarbowy.epoxyexample.ui.article.ArticleActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.loader) ProgressBar loaderView;

    private CatalogAdapter catalogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        int spanCount = 2;
        catalogAdapter = new CatalogAdapter();
        catalogAdapter.setSpanCount(spanCount);
        catalogAdapter.setCallback(new CatalogAdapter.Callback() {
            @Override public void onArticleClicked(Article article) {
                String articleJson = new Gson().toJson(article);
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("article", articleJson);
                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(catalogAdapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setAdapter(catalogAdapter);

        loaderView.setVisibility(View.VISIBLE);
        ZalandoService.getApi()
                .getArticles()
                .enqueue(new Callback<ArticlesListing>() {
                    @Override public void onResponse(Call<ArticlesListing> call, Response<ArticlesListing> response) {
                        if (response.isSuccessful()) {
                            catalogAdapter.setArticles(response.body().articles);
                        } else {
                            showFailToast();
                        }
                        loaderView.setVisibility(View.GONE);
                    }

                    @Override public void onFailure(Call<ArticlesListing> call, Throwable t) {
                        loaderView.setVisibility(View.GONE);
                        showFailToast();
                    }
                });
    }

    private void showFailToast() {
        Toast.makeText(this, "api call fail", Toast.LENGTH_LONG).show();
    }


}
