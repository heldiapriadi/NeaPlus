package com.example.neaplus.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    private final TextView publishedItemView;
    private final ImageView newsImageItemView, shareButtonItemView;
    TextView titleItemView;
    private final TextView publisherItemView;
    public CardView cardView;

    public HomeViewHolder(View itemView) {
        super(itemView);
        publishedItemView = (TextView) itemView.findViewById(R.id.text_published);
        newsImageItemView = (ImageView) itemView.findViewById(R.id.image_berita);
        titleItemView = (TextView) itemView.findViewById(R.id.text_title);
        publisherItemView = (TextView) itemView.findViewById(R.id.text_publisher);
        shareButtonItemView = (ImageView) itemView.findViewById(R.id.image_share);
        cardView = (CardView) itemView.findViewById(R.id.cardViewNews);
    }

    public void bind(String published, String image, String title, String publisher, String url, Context context) {
        publishedItemView.setText(published);
        Picasso.get().load(image).into(newsImageItemView);
        titleItemView.setText(title);
        publisherItemView.setText(publisher);

        shareButtonItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                // Membawa data / pesan yang ingin dishare
                intent.putExtra(intent.EXTRA_TEXT, title + " (" + url + ")");
                intent.setType("text/plain");

                // Menjalankan perintah Intent Implicit
                context.startActivity(Intent.createChooser(intent, "Share to :"));
            }
        });
    }

    static HomeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_news, parent, false);
        return new HomeViewHolder(view);
    }
}
