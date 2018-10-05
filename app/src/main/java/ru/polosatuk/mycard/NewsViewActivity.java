package ru.polosatuk.mycard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.polosatuk.mycard.news.data.DataUtils;

public class NewsViewActivity extends AppCompatActivity {

    private final NewsViewAdapter.onItemClickListener clickListener = newsItem ->
            startActivity(ThirdPartyIntentUtils.setFullNewsToExtra(this, newsItem.newsToJson(newsItem)));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_board);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.title_tool_bar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_news);
        recyclerView.setAdapter(new NewsViewAdapter(this, DataUtils.generateNews(), clickListener));

        if (screenOrientationPortrait()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_about_tool_bar: {
                Intent intent = new Intent(NewsViewActivity.this, AboutActivity.class);
                startActivity(intent);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Boolean screenOrientationPortrait() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return true;
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        } else {
            Log.d("Tag", "Я так не играю, что за ориенация");
            return true;
        }
    }
}
