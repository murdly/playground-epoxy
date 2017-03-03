package com.akarbowy.epoxyexample.ui.article;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.akarbowy.epoxyexample.R;
import com.akarbowy.epoxyexample.data.ZalandoService;
import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.data.model.ArticlesListing;
import com.akarbowy.epoxyexample.ui.helpers.AppBarStateChangeListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.header_main_photo) ImageView mainPhotoView;
    @BindView(R.id.appbar) AppBarLayout appbar;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindString(R.string.article_recommendation_text) String recommendationsTitle;
    @BindString(R.string.article_brand_offer_text) String brandOfferTitle;

    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                onBackPressed();
            }
        });

        String articleJson = getIntent().getStringExtra("article");
        final Article article = new Gson().fromJson(articleJson, Article.class);

        toolbar.setTitle(article.brand.name);

        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    toolbar.setTitle(article.brand.name);
                } else {
                    toolbar.setTitle("");
                }
            }
        });

        Picasso.with(this)
                .load(article.media.images.get(0).largeUrl)
                .noFade()
                .into(mainPhotoView);

        articleAdapter = new ArticleAdapter(article);
        articleAdapter.setCallback(new ArticleAdapter.Callback() {
            @Override public void onArticleClicked(Article article) {
                Toast.makeText(ArticleActivity.this,
                        String.format("article %s callback", article.id),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        int spanCount = 3;
        articleAdapter.setSpanCount(spanCount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(articleAdapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(articleAdapter);

        ZalandoService.getApi()
                .getRecommendations(article.id, "id,name,media")
                .enqueue(new Callback<List<Article>>() {
                    @Override public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        if (response.isSuccessful()) {
                            articleAdapter.setRecommendations(recommendationsTitle, response.body());
                        } else {
                            showFailToast();
                        }
                    }

                    @Override public void onFailure(Call<List<Article>> call, Throwable t) {
                        showFailToast();
                    }
                });

        ZalandoService.getApi()
                .getArticlesByBrand(article.brand.key)
                .enqueue(new Callback<ArticlesListing>() {
                    @Override public void onResponse(Call<ArticlesListing> call, Response<ArticlesListing> response) {
                        if (response.isSuccessful()) {
                            articleAdapter.setBrandOffer(brandOfferTitle, response.body().articles);
                        } else {
                            showFailToast();
                        }
                    }

                    @Override public void onFailure(Call<ArticlesListing> call, Throwable t) {
                        showFailToast();

                    }
                });
    }

    private void showFailToast() {
        Toast.makeText(this, "api call fail", Toast.LENGTH_LONG).show();
    }

}
