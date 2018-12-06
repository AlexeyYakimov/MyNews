package ru.polosatuk.mycard.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.polosatuk.mycard.R;
import ru.polosatuk.mycard.screen.newsList.NewsViewActivity;

public class IntroActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        Disposable disposable = Completable.complete()
                .delay(3,TimeUnit.SECONDS)
                .subscribe(this::startSecondActivity);
        Log.d("Tag", "Disposable Added");
        compositeDisposable.add(disposable);

    }

    private void startSecondActivity() {
        Log.d("Tag", "Go to next ACTIVITY");
        NewsViewActivity.start(this);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Tag", "Disposable Cleared");
        compositeDisposable.clear();
    }
}
