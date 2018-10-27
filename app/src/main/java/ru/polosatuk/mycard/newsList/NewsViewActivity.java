package ru.polosatuk.mycard.newsList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import ru.polosatuk.mycard.App;
import ru.polosatuk.mycard.NewsDetailsActivity;
import ru.polosatuk.mycard.R;
import ru.polosatuk.mycard.about.AboutActivity;
import ru.polosatuk.mycard.newsList.adapters.NewsViewAdapter;
import ru.polosatuk.mycard.newsList.converter.NewsConverter;
import ru.polosatuk.mycard.newsList.data.DataUtils;
import ru.polosatuk.mycard.newsList.decorator.NewsItemDecorator;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;

public class NewsViewActivity extends AppCompatActivity {

    private static final int SCREEN_WIDTH_DP = 600;
    private static final int LARGE_SCREEN_WIDTH_DP = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_board);

        Toolbar myToolbar = findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_news);
        initRecyclerView(recyclerView, this);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_about_tool_bar: {
                Intent intent = AboutActivity.getActivityAboutIntent(this);
                startActivity(intent);
            }
            break;
            default: {
                Log.d("Tag", "Клик по кнопкене сработал");
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(@NonNull RecyclerView recyclerView, @NonNull Context context) {
        List<NewsDisplayableModel> uiModels = NewsConverter.convert(DataUtils.generateNews());
        setLayoutManager(recyclerView, context);
        recyclerView.setAdapter(new NewsViewAdapter(context, uiModels, newsItem ->
                context.startActivity(NewsDetailsActivity.setFullNewsToExtra(context, NewsConverter.newsToJson(newsItem)))));
        recyclerView.addItemDecoration(new NewsItemDecorator(getResources().getDimensionPixelSize(R.dimen.padding_4dp)));
    }


    private void setLayoutManager(@NonNull RecyclerView recyclerView, @NonNull Context context) {

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount(displayMetrics), StaggeredGridLayoutManager.VERTICAL));
    }

    private int spanCount(DisplayMetrics displayMetrics) {

        float screenWidthInDp = displayMetrics.widthPixels / displayMetrics.density;
        int spanCount;
        if (screenWidthInDp < SCREEN_WIDTH_DP) {
            spanCount = 1;
        } else if (screenWidthInDp > SCREEN_WIDTH_DP && screenWidthInDp < LARGE_SCREEN_WIDTH_DP) {
            spanCount = 2;
        } else {
            spanCount = 3;
        }
        return spanCount;
    }


}
