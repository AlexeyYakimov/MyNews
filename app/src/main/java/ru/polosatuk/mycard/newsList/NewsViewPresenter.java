package ru.polosatuk.mycard.newsList;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.polosatuk.mycard.NewsDetailsActivity;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;

@InjectViewState
public class NewsViewPresenter extends MvpPresenter<NewsView> {
    @NonNull
    CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        compositeDisposable.add(Single.fromCallable(DataUtils::generateNews).
                subscribeOn(Schedulers.io()).
                map(NewsConverter::convert).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(newsRequest -> getViewState().showItems(newsRequest),
                        throwable -> getViewState().showError(throwable)));
    }

    public void onRetryBtnClick(){
        startLoading();
    }

    public void onItemClick(NewsDisplayableModel item){
       getViewState().showNewsDetails(item);}
}
