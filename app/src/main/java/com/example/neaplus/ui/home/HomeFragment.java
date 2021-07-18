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

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewResult = binding.textViewResult;
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
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getNews();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getNews(){
        Map<String, String> parameters = new HashMap<>();

        parameters.put("country","id");
        parameters.put("category","business");
        parameters.put("apiKey","a038ee0f2c8d4665bd21853c6b634cf8");

        Call<News> call = jsonPlaceHolderApi.getPosts(parameters);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                News newsList = response.body();
                String content = "";
                String article = "";
                content += "Status: " + newsList.getStatus() + "\n";
                content += "TotalResults: " + newsList.getTotalResults() + "\n";
                content += "Article [ \n";
                content += "\t Sources [ \n";
                textViewResult.append(content);
                List<Articles> articlesList = newsList.getArticles();
                    for(Articles articles : articlesList){
                        article += "\t \t id : " + articles.getSource().getId() + "\n";
                        article += "\t \t name : " + articles.getSource().getName() + "\n";
                        article += "\t ] \n";
                        article += "\t author : " + articles.getAuthor() + "\n";
                        article += "\t title : " + articles.getTitle() + "\n";
                        article += "\t description : " + articles.getDescription() + "\n";
                        article += "\t url : " + articles.getUrl() + "\n";
                        article += "\t urlToImage : " + articles.getUlrToImage() + "\n";
                        article += "\t publishedAt : " + articles.getPublishedAt() + "\n";
                        article += "\t content : " + articles.getContent() + "\n";

                        textViewResult.append(article);
                    }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}