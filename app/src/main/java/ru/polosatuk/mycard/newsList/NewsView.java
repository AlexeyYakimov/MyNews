package ru.polosatuk.mycard.newsList;

import android.content.Context;
import android.view.View;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import androidx.annotation.NonNull;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.newsList.models.NewsItem;

public interface NewsView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void showItems(List< NewsDisplayableModel> list);
    @StateStrategyType(SingleStateStrategy.class)
    void showError(Throwable th);
    @StateStrategyType(SingleStateStrategy.class)
    void showProgressBar(boolean visability);
    @StateStrategyType(SingleStateStrategy.class)
    void showNewsDetail(Context context, NewsDisplayableModel item);
}
