package ru.polosatuk.mycard.newsList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.polosatuk.mycard.R;
import ru.polosatuk.mycard.newsList.models.NewsDisplayableModel;
import ru.polosatuk.mycard.utils.ImageUtils;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> {
    @NonNull
    private final List<NewsDisplayableModel> newsItems = new ArrayList();
    @NonNull
    private final RequestManager imageLoader;
    @NonNull
    private final OnItemClickListener listener;
    @NonNull
    private LayoutInflater inflater;

    public NewsViewAdapter(@NonNull Context context,
                           @NonNull OnItemClickListener listener
    ) {
        this.imageLoader = ImageUtils.getRequestManager(context);
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(viewType, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(newsItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        switch (newsItems.get(position).getNewsCategory()) {
            case MUSIC: {
                return R.layout.news_card_music;
            }
            case ANIMALS:
            case DARVIN_AWARDS:
            case CRIMINAL: {
                return R.layout.news_card;
            }

            default: {
                Log.d("NewsViewAdapter", "No activity to add");
                return R.layout.news_card;
            }
        }
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public void replaceItems(@NonNull List<NewsDisplayableModel> items){
        newsItems.clear();
        newsItems.addAll(items);
        notifyDataSetChanged();
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
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(newsItems.get(position));
                }
            });
            category = itemView.findViewById(R.id.news_card_category);
            title = itemView.findViewById(R.id.news_card_title);
            previewText = itemView.findViewById(R.id.news_card_text_preview);
            date = itemView.findViewById(R.id.news_card_date);
            imageNews = itemView.findViewById(R.id.news_card_image);

        }

        private void bind(@NonNull NewsDisplayableModel newsItem) {
            category.setText(newsItem.getNewsCategory().getName());
            title.setText(newsItem.getPreviewText());
            previewText.setText(newsItem.getPreviewText());
            date.setText(newsItem.getPublishDate());
            imageLoader.load(newsItem.getImageUrl()).into(imageNews);
        }


    }

    public interface OnItemClickListener {
        void onItemClick(NewsDisplayableModel newsItem);
    }
}
