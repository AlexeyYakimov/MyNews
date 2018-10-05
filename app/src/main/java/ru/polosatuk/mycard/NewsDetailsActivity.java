package ru.polosatuk.mycard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.polosatuk.mycard.news.data.NewsItem;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);

        Intent fullNews = getIntent();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        NewsItem news = gson.fromJson(fullNews.getStringExtra(ThirdPartyIntentUtils.NEWS_KEY_EXTRA), NewsItem.class);

        TextView tvTitle = findViewById(R.id.news_details_title);
        TextView tvFullNews = findViewById(R.id.news_details_full_text);
        ImageView tvImageView = findViewById(R.id.news_details_image);
        TextView tvDate = findViewById(R.id.news_details_date);

        tvTitle.setText(news.getTitle());
        tvFullNews.setText(news.getFullText());
        tvDate.setText(SupportUtils.getSimpleDate(news.getPublishDate()));
        myToolbar.setTitle(news.getNewsCategory().getName());


        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.place_holder)
                .fallback(R.drawable.place_holder)
                .centerCrop();
        RequestManager imageLoader = Glide.with(this).applyDefaultRequestOptions(imageOption);

        imageLoader.load(news.getImageUrl()).into(tvImageView);
    }
}
