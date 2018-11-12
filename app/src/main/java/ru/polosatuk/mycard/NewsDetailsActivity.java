package ru.polosatuk.mycard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.utils.ImageUtils;


public class NewsDetailsActivity extends AppCompatActivity {

    public static final String NEWS_KEY_EXTRA = "newsItem:item";

    @NonNull
    public static void start(@NonNull Context context, @NonNull NewsDisplayableModel item){
        context.startActivity(new Intent(context, NewsDetailsActivity.class).putExtra(NEWS_KEY_EXTRA, item));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar myToolbar = findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final NewsDisplayableModel news = (NewsDisplayableModel) getIntent().getSerializableExtra(NEWS_KEY_EXTRA);


        TextView tvTitle = findViewById(R.id.news_details_title);
        TextView tvFullNews = findViewById(R.id.news_details_full_text);
        ImageView tvImageView = findViewById(R.id.news_details_image);
        TextView tvDate = findViewById(R.id.news_details_date);

        tvTitle.setText(news.getTitle());
        tvFullNews.setText(news.getFullText());
        tvDate.setText(news.getPublishDate());
        myToolbar.setTitle(news.getNewsCategory().getName());

        RequestManager imageLoader = ImageUtils.getRequestManager(this);

        imageLoader.load(news.getImageUrl()).into(tvImageView);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
