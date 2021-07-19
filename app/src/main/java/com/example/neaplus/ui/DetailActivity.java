package com.example.neaplus.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.neaplus.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView author;
    private TextView publisher;
    private TextView published;
    private ImageView image;
    private TextView content;
    private TextView url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.text_title);
        author = findViewById(R.id.text_author);
        publisher = findViewById(R.id.text_publisher);
        published = findViewById(R.id.text_published);
        image = findViewById(R.id.image_berita);
        content = findViewById(R.id.text_content);
        url = findViewById(R.id.text_url);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        author.setText(intent.getStringExtra("author"));
        publisher.setText(intent.getStringExtra("publisher") + ", ");
        published.setText(intent.getStringExtra("published"));
        Picasso.with(this).load(intent.getStringExtra("image")).into(image);
        content.setText(intent.getStringExtra("content"));
        SpannableString content = new SpannableString(intent.getStringExtra("url"));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        url.setText(content);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url",intent.getStringExtra("url"));
                Intent intent2 = new Intent(getApplicationContext(), WebActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
            }
        });

    }
}