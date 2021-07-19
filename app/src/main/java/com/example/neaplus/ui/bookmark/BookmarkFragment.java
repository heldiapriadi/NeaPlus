package com.example.neaplus.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.resource.ArticlesData;
import com.example.neaplus.core.usecase.BookmarkViewModel;
import com.example.neaplus.databinding.FragmentBookmarkBinding;

import java.util.ArrayList;

public class BookmarkFragment extends Fragment {
    private RecyclerView rvArticles;

    private com.example.neaplus.core.usecase.BookmarkViewModel BookmarkViewModel;
    private FragmentBookmarkBinding binding;
    ArrayList<Articles> listArticle;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel =
                new ViewModelProvider(this).get(BookmarkViewModel.class);

        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
        rvArticles.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        BookmarkListAdapter bookmarkListAdapter = new BookmarkListAdapter(listArticle);
        rvArticles.setAdapter(bookmarkListAdapter);

        bookmarkListAdapter.setOnItemClickCallback(new BookmarkListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Articles article) {

            }
        });

//        BookmarkListAdapter
    }
}