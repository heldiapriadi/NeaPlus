package com.example.neaplus.ui.bookmark;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.model.database.Article;
import com.example.neaplus.core.viewmodel.ArticleViewModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.BookmarkViewHolder> {
    private List<Article> articles;
    private Activity activity;
    private ArticleViewModel articleViewModel;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @NotNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_bookmark, viewGroup, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BookmarkViewHolder holder, int position) {
        Article article = articles.get(position);

        Date date1 = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(articles.get(position).getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get().load(article.getUrlToImage()).into(holder.imgPhoto);
        holder.tvTittle.setText(article.getTitle());
        holder.tvPublisher.setText(article.getSource_name());
        holder.tvPublishedAt.setText(sdf1.format(date1));

        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleViewModel = new ViewModelProvider((ViewModelStoreOwner) activity, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication())).get(ArticleViewModel.class);
                articleViewModel.delete(article);
                Toast.makeText(activity.getApplicationContext(),"Berita berhasil dihapus",Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(intent.EXTRA_TEXT, article.getTitle() + " (" + article.getUrl() + ")");
                intent.setType("text/plain");

                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Share to :"));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  Toast.makeText(holder.itemView.getContext(), "Kamu memilih "+ article.getTitle(),Toast.LENGTH_SHORT).show();
                onItemClickCallback.onItemClicked(articles.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (articles.isEmpty()) {
            return 0;
        }
        return articles.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, imgBtnShare, imgBtnDelete;
        TextView tvTittle, tvPublisher, tvPublishedAt;

        public BookmarkViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.news_photo);
            imgBtnDelete = itemView.findViewById(R.id.news_delete);
            imgBtnShare = itemView.findViewById(R.id.news_share);
            tvTittle = itemView.findViewById(R.id.news_title);
            tvPublishedAt = itemView.findViewById(R.id.news_published_at);
            tvPublisher = itemView.findViewById(R.id.news_publisher);
        }
    }

    public BookmarkListAdapter(List<Article> list, Activity activity) {
        this.articles = list;
        this.activity = activity;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Article article);
    }
}
