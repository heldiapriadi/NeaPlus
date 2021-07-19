package com.example.neaplus.ui.bookmark;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.example.neaplus.core.model.Articles;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.CardViewViewHolder>  {
    ArrayList<Articles> articles;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @NotNull
    @Override
    public BookmarkListAdapter.CardViewViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_bookmark, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BookmarkListAdapter.CardViewViewHolder holder, int position) {
        Articles article = articles.get(position);

            Picasso.get().load(article.getUlrToImage()).into(holder.imgPhoto    );
          holder.tvTittle.setText(article.getTitle());
          holder.tvPublisher.setText(article.getSource().getName());
          holder.tvPublishedAt.setText(article.getPublishedAt());

          holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(holder.itemView.getContext(),"Clicked Button Delete",Toast.LENGTH_SHORT).show();
              }
          });

          holder.imgBtnShare.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(Intent.ACTION_SEND);

                // Membawa data / pesan yang ingin dishare
                    intent.putExtra(intent.EXTRA_TEXT, article.getTitle()+" ("+article.getUrl()+")");
                    intent.setType("text/plain");

                // Menjalankan perintah Intent Implicit
                    holder.itemView.getContext().startActivity(Intent.createChooser(intent,"Share to :"));
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
        if (articles.isEmpty()){
            return 0;
        }
        return articles.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto, imgBtnShare, imgBtnDelete;
        TextView tvTittle, tvPublisher, tvPublishedAt;

        public CardViewViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.news_photo);
            imgBtnDelete = itemView.findViewById(R.id.news_delete);
            imgBtnShare = itemView.findViewById(R.id.news_share);
            tvTittle = itemView.findViewById(R.id.news_title);
            tvPublishedAt = itemView.findViewById(R.id.news_published_at);
            tvPublisher = itemView.findViewById(R.id.news_publisher);
        }
    }

    public BookmarkListAdapter(ArrayList<Articles> list){
        this.articles = list;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Articles article);
    }
}
