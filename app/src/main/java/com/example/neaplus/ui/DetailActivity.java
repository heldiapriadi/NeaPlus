package com.example.neaplus.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.neaplus.R;
import com.example.neaplus.core.model.database.Article;
import com.example.neaplus.core.viewmodel.ArticleViewModel;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView author;
    private TextView publisher;
    private TextView published;
    private ImageView image, imageShare, imageBookmark;
    private TextView content;
    private TextView url;
    private ImageView back;

    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        articleViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ArticleViewModel.class);

        title = findViewById(R.id.text_title);
        author = findViewById(R.id.text_author);
        publisher = findViewById(R.id.text_publisher);
        published = findViewById(R.id.text_published);
        image = findViewById(R.id.image_berita);
        content = findViewById(R.id.text_content);
        url = findViewById(R.id.text_url);
        imageShare = findViewById(R.id.image_share);
        imageBookmark = findViewById(R.id.image_bookmark);
        back = findViewById(R.id.image_back);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        author.setText(intent.getStringExtra("author"));
        publisher.setText(intent.getStringExtra("publisher") + ", ");
        published.setText(intent.getStringExtra("published"));
        Picasso.get().load(intent.getStringExtra("image")).into(image);
        content.setText(intent.getStringExtra("content"));
        SpannableString content = new SpannableString(intent.getStringExtra("url"));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        url.setText(content);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", intent.getStringExtra("url"));
                Intent intent2 = new Intent(getApplicationContext(), WebActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.putExtra(intent.EXTRA_TEXT, intent.getStringExtra("title") + " (" + intent.getStringExtra("url") + ")");
                intentShare.setType("text/plain");

                startActivity(Intent.createChooser(intentShare, "Share to :"));
            }
        });

        imageBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = new Article(
                        intent.getStringExtra("author"),
                        intent.getStringExtra("title"),
                        intent.getStringExtra("url"),
                        intent.getStringExtra("image"),
                        intent.getStringExtra("published"),
                        intent.getStringExtra("content"),
                        intent.getStringExtra("publisher")
                );
                articleViewModel.insert(article);
            }
        });
    }
}