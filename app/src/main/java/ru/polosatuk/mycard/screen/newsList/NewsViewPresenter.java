package ru.polosatuk.mycard.screen.newsList;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.polosatuk.mycard.App;
import ru.polosatuk.mycard.R;
import ru.polosatuk.mycard.network.api.NytApi;
import ru.polosatuk.mycard.network.api.NytClient;
import ru.polosatuk.mycard.screen.newsList.models.NewsCategory;
import ru.polosatuk.mycard.screen.newsList.models.NewsDisplayableModel;

@InjectViewState
public class NewsViewPresenter extends MvpPresenter<NewsView> {
    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @NonNull
    private String section = "";

    @NonNull
    private final NytApi api;

    public NewsViewPresenter() {
        this.api = NytClient.getInstance().getNews();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        startLoading(getDefaultSection());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void startLoading(NewsCategory section) {
        getViewState().showProgressBar(true);
        compositeDisposable.add(
                api.getNewsInSection(section)
                        .map(NewsConverter::convert)
                        .subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(newsRequest -> getViewState().showItems(newsRequest),
                                throwable -> getViewState().showError(throwable)));
    }

    public void onRetryBtnClick() {
        if ("".equals(section)) {
            startLoading(getDefaultSection());
        } else {
            startLoading(NewsCategory.valueOf(section));
        }
    }

    public void onItemClick(NewsDisplayableModel item) {
        getViewState().showNewsDetails(item);
    }

    public void onItemClick(String section) {

        startLoading(NewsCategory.valueOf(section));
    }

    private NewsCategory getDefaultSection() {
        return NewsCategory.home;
    }

    public HashMap<NewsCategory, String> createSpinner() {
        HashMap<NewsCategory, String> spinnerList = new HashMap<NewsCategory, String>();

        spinnerList.put(NewsCategory.home, App.getAppContext().getResources().getString(R.string.home));
        spinnerList.put(NewsCategory.opinion, App.getAppContext().getResources().getString(R.string.opinion));
        spinnerList.put(NewsCategory.national, App.getAppContext().getResources().getString(R.string.national));
        return spinnerList;
    }
}
