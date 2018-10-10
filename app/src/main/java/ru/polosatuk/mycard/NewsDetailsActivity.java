package ru.polosatuk.mycard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.polosatuk.mycard.news.data.NewsConverter;
import ru.polosatuk.mycard.news.data.NewsItem;
import ru.polosatuk.mycard.utils.DateUtils;
import ru.polosatuk.mycard.utils.ImageUtils;

import static ru.polosatuk.mycard.news.data.NewsConverter.NEWS_KEY_EXTRA;

public class NewsDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NewsItem news = NewsConverter.getNewsDetailFromGson(getIntent(), NewsItem.class);

        TextView tvTitle = findViewById(R.id.news_details_title);
        TextView tvFullNews = findViewById(R.id.news_details_full_text);
        ImageView tvImageView = findViewById(R.id.news_details_image);
        TextView tvDate = findViewById(R.id.news_details_date);

        tvTitle.setText(news.getTitle());
        tvFullNews.setText(news.getFullText());
        tvDate.setText(DateUtils.getNewsDetailDate(news.getPublishDate()));
        myToolbar.setTitle(news.getNewsCategory().getName());

        RequestManager imageLoader = ImageUtils.getImageOption(this);

        imageLoader.load(news.getImageUrl()).into(tvImageView);
    }

    @NonNull
    public static Intent setFullNewsToExtra(@NonNull Context context,
                                            @NonNull String content) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(NEWS_KEY_EXTRA, content);
        if (intent.resolveActivity(context.getPackageManager()) != null)
            return intent;
        else
            return null;
    }

}
