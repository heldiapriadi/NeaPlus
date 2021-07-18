package com.example.neaplus.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neaplus.R;
import com.example.neaplus.core.usecase.BookmarkViewModel;
import com.example.neaplus.core.usecase.HomeViewModel;
import com.example.neaplus.databinding.FragmentHomeBinding;
import com.example.neaplus.core.model.Articles;
import com.example.neaplus.core.resource.JsonPlaceHolderApi;
import com.example.neaplus.core.model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;
    private HomeViewModel homeViewModel;
    private HomeListAdapter adapterNews;
    private RecyclerView recyclerViewNews;
    ArrayList<Articles> articleArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        textViewResult = binding.textViewResult;
        final TextView textView = binding.textNews;

        final Spinner spinner = binding.spinnerCountry;
        ArrayList<String> Item = new ArrayList<>();
        Item.add("ID");
        Item.add("EN");


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity().getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, Item);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        recyclerViewNews = binding.listRecyclerView;
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getAllNews().observe(getViewLifecycleOwner(), news -> {
            // Update the cached copy of the words in the adapter.
            List<Articles> newsArticles = news.getArticles();
            articleArrayList.addAll(newsArticles);
            adapterNews.notifyDataSetChanged();
        });
        setupRecyclerView();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void setupRecyclerView() {
        if (adapterNews == null) {
            adapterNews = new HomeListAdapter(this.getActivity(), articleArrayList, new HomeListAdapter.NewsDiff());
            recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewNews.setAdapter(adapterNews);
            recyclerViewNews.setItemAnimator(new DefaultItemAnimator());
        } else {
            adapterNews.notifyDataSetChanged();
        }
    }

}