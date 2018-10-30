package ru.polosatuk.mycard.newsList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    private ProgressBar progressBar;
    private NewsViewAdapter adapter;
    private Disposable disposable;
    private RecyclerView recyclerView;

    @Override
    protected void onStart() {
        super.onStart();
        loadNews();


    }

    @Override
    protected void onStop() {
        super.onStop();
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
        progressBar = null;
        recyclerView = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_board);

        progressBar = findViewById(R.id.progressBar);


        Toolbar myToolbar = findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);

         recyclerView = findViewById(R.id.recycler_view_news);
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

        setLayoutManager(recyclerView, context);
        adapter = new NewsViewAdapter(this, newsItem -> NewsDetailsActivity.start(context, newsItem));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new NewsItemDecorator(getResources().getDimensionPixelSize(R.dimen.padding_4dp)));
    }

    private void loadNews() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        disposable = Observable.fromCallable(DataUtils::generateNews).
                subscribeOn(Schedulers.io()).
                map(NewsConverter::convert).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(this::updateItems);
    }

    private void updateItems(List<NewsDisplayableModel> newsDisplayableModelList) {
        if (adapter != null) {
            adapter.replaceItems(newsDisplayableModelList);
        }
        progressBar.setVisibility(ProgressBar.INVISIBLE);

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
