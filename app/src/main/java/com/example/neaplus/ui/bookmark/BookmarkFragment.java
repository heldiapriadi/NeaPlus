package com.example.neaplus.ui.bookmark;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.model.database.Article;
import com.example.neaplus.core.resource.ArticlesData;
import com.example.neaplus.core.viewmodel.ArticleViewModel;
import com.example.neaplus.core.viewmodel.BookmarkViewModel;
import com.example.neaplus.databinding.FragmentBookmarkBinding;
import com.example.neaplus.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends Fragment {
    private RecyclerView rvArticles;

    private com.example.neaplus.core.viewmodel.BookmarkViewModel BookmarkViewModel;
    private FragmentBookmarkBinding binding;
    ArrayList<Articles> listArticle;
    private ArticleViewModel articleViewModel;
    private TextView getCountArticle;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel =
                new ViewModelProvider(this).get(BookmarkViewModel.class);

        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        articleViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(ArticleViewModel.class);
        getCountArticle = root.findViewById(R.id.textView3);
        rvArticles = root.findViewById(R.id.rv_articles);
        listArticle = ArticlesData.getListData();

        showRecyclerListView();
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showRecyclerListView(){
        articleViewModel.getmAllArticle().observe(getViewLifecycleOwner(), news -> {
            // Update the cached copy of the words in the adapter.
            getCountArticle.setText("Show "+news.size()+" news");
            rvArticles.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            BookmarkListAdapter bookmarkListAdapter = new BookmarkListAdapter(news, getActivity());
            rvArticles.setAdapter(bookmarkListAdapter);

            bookmarkListAdapter.setOnItemClickCallback(new BookmarkListAdapter.OnItemClickCallback() {
                @Override
                public void onItemClicked(Article article) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", article.getTitle());
                    bundle.putString("author", article.getAuthor());
                    bundle.putString("publisher", article.getSource_name());
                    bundle.putString("published", article.getPublishedAt());
                    bundle.putString("image", article.getUrlToImage());
                    bundle.putString("content", article.getContent());
                    bundle.putString("url", article.getUrl());
                    Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        });


//        BookmarkListAdapter
    }
}