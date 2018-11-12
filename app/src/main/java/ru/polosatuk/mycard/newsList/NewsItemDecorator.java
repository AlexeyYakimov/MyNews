package ru.polosatuk.mycard.newsList;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class NewsItemDecorator extends RecyclerView.ItemDecoration {
    private final int offset;

    public NewsItemDecorator(int offset) {
        this.offset = offset;
    }

    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {

        final int position = parent.getChildLayoutPosition(view);

        if (position != RecyclerView.NO_POSITION) {
            outRect.set(offset, offset, offset, offset);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }
}
