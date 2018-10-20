package ru.polosatuk.mycard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.polosatuk.mycard.newsList.converter.NewsConverter;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.newsList.models.NewsItem;
import ru.polosatuk.mycard.utils.DateUtils;
import ru.polosatuk.mycard.utils.ImageUtils;


public class NewsDetailsActivity extends AppCompatActivity {

    public static final String NEWS_KEY_EXTRA = "news_details_key_extra";

   private NewsDisplayableModel news;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar myToolbar = findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String json = getIntent().getStringExtra(NEWS_KEY_EXTRA);
        try {
            news = NewsConverter.getNewsDetailFromGson(json);
        } catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(this, "No news", Toast.LENGTH_LONG).show();
            Log.d("NewsDetailsActivity", "Exception from json");
        }


        TextView tvTitle = findViewById(R.id.news_details_title);
        TextView tvFullNews = findViewById(R.id.news_details_full_text);
        ImageView tvImageView = findViewById(R.id.news_details_image);
        TextView tvDate = findViewById(R.id.news_details_date);

        tvTitle.setText(news.getTitle());
        tvFullNews.setText(news.getFullText());
        tvDate.setText(news.getPublishDate());
        myToolbar.setTitle(news.getNewsCategoryName());

        RequestManager imageLoader = ImageUtils.getImageOption(this);

        imageLoader.load(news.getImageUrl()).into(tvImageView);
    }

    @NonNull
    public static Intent setFullNewsToExtra(@NonNull Context context, @NonNull String content) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(NEWS_KEY_EXTRA, content);
            return intent;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
