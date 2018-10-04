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

import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.polosatuk.mycard.news.data.NewsItem;

public class FullNewsActivity extends AppCompatActivity {

    private RequestManager imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);
        Intent fullNews = getIntent();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        NewsItem news = gson.fromJson(fullNews.getStringExtra("title_value"), NewsItem.class);

        TextView tvTitle = findViewById(R.id.full_news_title);
        TextView tvFullNews = findViewById(R.id.full_news_full_text);
        ImageView tvImageView = findViewById(R.id.full_news_image);
        TextView tvDate = findViewById(R.id.full_news_date);
        tvTitle.setText(news.getTitle());
        tvFullNews.setText(news.getFullText());
        tvDate.setText(new SimpleDateFormat("EEEE, dd MMM hh:mm a").format(news.getPublishDate()));
        myToolbar.setTitle(news.getNewsCategory().getName());


        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.place_holder)
                .fallback(R.drawable.place_holder)
                .centerCrop();
        imageLoader = Glide.with(this).applyDefaultRequestOptions(imageOption);

        imageLoader.load(news.getImageUrl()).into(tvImageView);
    }
}
