package ru.polosatuk.mycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.polosatuk.mycard.news.data.NewsItem;
import ru.polosatuk.mycard.utils.DateUtils;
import ru.polosatuk.mycard.utils.ImageUtils;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> {
    @NonNull
    Context context;
    @NonNull
    private final List<NewsItem> newsItems;
    @NonNull
    private final RequestManager imageLoader;
    @NonNull
    private final OnItemClickListener listener;

    public NewsViewAdapter(@NonNull Context context,
                           @NonNull List<NewsItem> newsItems,
                           @NonNull OnItemClickListener listener
    ) {
        this.context = context;
        this.imageLoader = ImageUtils.getImageOption(context);
        this.newsItems = newsItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == 4) {
            return new ViewHolder(inflater.inflate(R.layout.news_card_music, parent, false), listener);
        } else {
            return new ViewHolder(inflater.inflate(R.layout.news_card, parent, false), listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(newsItems.get(position));
    }

    @Override
    @NonNull
    public int getItemViewType(int position) {
        return newsItems.get(position).getNewsCategory().getId();
    }

    @Override
    @NonNull
    public int getItemCount() {
        return newsItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final TextView category;
        @NonNull
        private final TextView title;
        @NonNull
        private final TextView previewText;
        @NonNull
        private final TextView date;
        @NonNull
        private final ImageView imageNews;

        public ViewHolder(@NonNull View itemView,
                          @NonNull OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(newsItems.get(position));
                }
            });
            category = itemView.findViewById(R.id.news_card_category);
            title = itemView.findViewById(R.id.news_card_title);
            previewText = itemView.findViewById(R.id.news_card_text_preview);
            date = itemView.findViewById(R.id.news_card_date);
            imageNews = itemView.findViewById(R.id.news_card_image);

        }

        private void bind(@NonNull NewsItem newsItem) {
            category.setText(newsItem.getNewsCategory().getName());
            title.setText(newsItem.getTitle());
            previewText.setText(newsItem.getPreviewText());
            date.setText(DateUtils.getDateToNews(newsItem.getPublishDate(), itemView.getContext()));
            imageLoader.load(newsItem.getImageUrl()).into(imageNews);
        }


    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem newsItem);
    }
}
