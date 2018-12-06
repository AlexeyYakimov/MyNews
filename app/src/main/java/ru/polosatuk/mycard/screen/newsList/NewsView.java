package ru.polosatuk.mycard.screen.newsList;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.polosatuk.mycard.screen.newsList.models.NewsDisplayableModel;

public interface NewsView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void showItems(List<NewsDisplayableModel> list);

    @StateStrategyType(SingleStateStrategy.class)
    void showError(Throwable th);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showProgressBar(boolean visability);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showNewsDetails(NewsDisplayableModel item);

}
