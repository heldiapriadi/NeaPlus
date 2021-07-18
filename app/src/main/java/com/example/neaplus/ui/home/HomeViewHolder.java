package com.example.neaplus.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.squareup.picasso.Picasso;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    private final TextView publishedItemView;
    private final ImageView newsImageItemView;
    private final TextView titleItemView;
    private final TextView publisherItemView;
    ConstraintLayout constraintLayout;
    public HomeViewHolder(View itemView) {
        super(itemView);
        publishedItemView = (TextView)itemView.findViewById(R.id.text_published);
        newsImageItemView = (ImageView)itemView.findViewById(R.id.image_berita);
        titleItemView = (TextView)itemView.findViewById(R.id.text_title);
        publisherItemView = (TextView)itemView.findViewById(R.id.text_publisher);
    }

    public void bind(String published, String image, String title, String publisher, Context context) {
        publishedItemView.setText(published);
        Picasso.with(context).load(image).into(newsImageItemView);
        titleItemView.setText(title);
        publisherItemView.setText(publisher);
    }

    static HomeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        return new HomeViewHolder(view);
    }
}
