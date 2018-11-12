package ru.polosatuk.mycard.newsList;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;

public interface NewsView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void showItems(List< NewsDisplayableModel> list);
    @StateStrategyType(SingleStateStrategy.class)
    void showError(Throwable th);
    @StateStrategyType(SingleStateStrategy.class)
    void showProgressBar(boolean visability);
    @StateStrategyType(SingleStateStrategy.class)
    void showNewsDetails(NewsDisplayableModel item);
}
