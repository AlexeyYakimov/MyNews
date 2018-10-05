package ru.polosatuk.mycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.polosatuk.mycard.news.data.NewsItem;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> {
    private final Context context;
    private final List<NewsItem> newsItems;
    private final LayoutInflater inflater;
    private final onItemClickListener listener;
    private final RequestManager imageLoader;
    int i = 0;

    public NewsViewAdapter(@NonNull Context context,
                           @NonNull List<NewsItem> newsItems,
                           @NonNull onItemClickListener listener) {
        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.place_holder)
                .fallback(R.drawable.place_holder)
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);
        this.context = context;
        this.newsItems = newsItems;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    public interface onItemClickListener {
        void onItemClick(NewsItem newsItem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.news_card, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(newsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView category;
        public final TextView title;
        public final TextView previewText;
        public final TextView date;
        public final ImageView imageNews;


        public ViewHolder(@NonNull View itemView, @NonNull onItemClickListener listener) {
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

        void bind(NewsItem newsItem) {
            category.setText(newsItem.getNewsCategory().getName());
            title.setText(newsItem.getTitle());
            previewText.setText(newsItem.getPreviewText());
            date.setText(SupportUtils.getSimpleDate(newsItem.getPublishDate()));
            imageLoader.load(newsItem.getImageUrl()).into(imageNews);
        }


    }

}
