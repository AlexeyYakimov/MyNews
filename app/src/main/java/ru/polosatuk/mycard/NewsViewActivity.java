package ru.polosatuk.mycard;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import ru.polosatuk.mycard.news.data.DataUtils;
import ru.polosatuk.mycard.news.data.NewsConverter;
import ru.polosatuk.mycard.news.data.NewsItem;

public class NewsViewActivity extends AppCompatActivity implements NewsViewAdapter.OnItemClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_board);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_news);
        recyclerView.setAdapter(new NewsViewAdapter(this, DataUtils.generateNews(), this));

        setLayoutManager(recyclerView);
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(@NonNull NewsItem newsItem) {
        startActivity(NewsDetailsActivity.setFullNewsToExtra(this, NewsConverter.newsToJson(newsItem)));
    }

    private void setLayoutManager(RecyclerView recyclerView) {
        final int SCREEN_WIDTH_DP = 600;
        final int LARGE_SCREEN_WIDTH_DP = 1000;

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float screenWidthInDp = displayMetrics.widthPixels / displayMetrics.density;
        if (screenWidthInDp < SCREEN_WIDTH_DP) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            setItemDecoration(recyclerView);
        } else if (screenWidthInDp > SCREEN_WIDTH_DP && screenWidthInDp < LARGE_SCREEN_WIDTH_DP) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            setItemDecoration(recyclerView);
        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            setItemDecoration(recyclerView);
        }
    }

    private void setItemDecoration(@NonNull RecyclerView recyclerView) {
        DividerItemDecoration decoration
                = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration decorationHor
                = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);

        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider);
        decoration.setDrawable(dividerDrawable);
        decorationHor.setDrawable(dividerDrawable);

        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(decorationHor);
    }

}
