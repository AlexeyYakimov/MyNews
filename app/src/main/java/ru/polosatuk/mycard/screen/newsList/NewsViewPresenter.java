package ru.polosatuk.mycard.screen.newsList;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.polosatuk.mycard.network.api.NytClient;
import ru.polosatuk.mycard.screen.newsList.models.NewsCategory;
import ru.polosatuk.mycard.screen.newsList.models.NewsDisplayableModel;

@InjectViewState
public class NewsViewPresenter extends MvpPresenter<NewsView> {
    private static final String LOG_TAG = "NewsViewPresenter";
    @NonNull
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @NonNull
    NewsCategory[] spinnerValue = NewsCategory.values();

    @NonNull
    private String section = "";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        startLoading();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public void startLoading() {
        getViewState().showProgressBar(true);
        compositeDisposable.add(NytClient.getInstance().getNews().getNewsInSection(getDefaultSection()).subscribeOn(Schedulers.io()).
                map(NewsConverter::convert).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(newsRequest -> getViewState().showItems(newsRequest),
                        throwable -> getViewState().showError(throwable)));
    }

    public void startLoading(String section) {
        this.section = section;
        getViewState().showProgressBar(true);
        compositeDisposable.add(NytClient.getInstance().getNews().getNewsInSection(section.toLowerCase()).subscribeOn(Schedulers.io()).
                map(NewsConverter::convert).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(newsRequest -> getViewState().showItems(newsRequest),
                        throwable -> getViewState().showError(throwable)));
    }

    public void onRetryBtnClick() {
        if (section.equals("")) {
            startLoading();
        } else {
            startLoading(section.toLowerCase());
        }
    }

    public void onItemClick(NewsDisplayableModel item) {
        getViewState().showNewsDetails(item);
    }

    private String getDefaultSection() {
        return NewsCategory.HOME.getName();
    }
}
