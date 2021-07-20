package com.example.neaplus.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.neaplus.core.viewmodel.HomeViewModel;
import com.example.neaplus.databinding.FragmentHomeBinding;
import com.example.neaplus.core.model.Articles;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView imageSearch;
    private HomeViewModel homeViewModel;
    private HomeListAdapter adapterNews;
    private RecyclerView recyclerViewNews;
    private Button businessButton;
    private Button entertainmentButton;
    private Button generalButton;
    private Button healthButton;
    private Button scienceButton;
    private Button sportsButton;
    private Button technologyButton;
    private Spinner spinner;
    private EditText inputSearch;
    private String category = "";
    private String country;
    private int positionSpinner;
    ArrayList<Articles> articleArrayList = new ArrayList<>();
    private boolean onsearch = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        textViewResult = binding.textViewResult;
        final TextView textView = binding.textNews;
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        spinner = binding.spinnerCountry;
        ArrayList<String> Item = new ArrayList<>();
        Item.add("id");
        Item.add("us");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        country = spinner.getSelectedItem().toString();

        recyclerViewNews = binding.listRecyclerView;
        imageSearch = binding.imageSearch;
        businessButton = binding.businessButton;
        entertainmentButton = binding.entertainmentButton;
        generalButton = binding.generalButton;
        healthButton = binding.healthButton;
        scienceButton = binding.scienceButton;
        sportsButton = binding.sportsButton;
        technologyButton = binding.technologyButton;
        inputSearch = binding.inputSearch;

        search_click();
        business_click();
        entertainment_click();
        general_click();
        health_click();
        science_click();
        sports_click();
        technology_click();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ct = parent.getItemAtPosition(position).toString();
                homeViewModel.init();
                loadNews(ct, category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    homeViewModel.init();
                    loadNewsSearch(inputSearch.getText().toString());
                    onsearch = true;
                    animation();
                    inputSearch.setText("");
                    return true;
                } else {
                    return false;
                }
            }

        });

        return root;
    }

    private void loadNews(String ct, String cty) {
        homeViewModel.getAllNews(ct, cty).observe(getViewLifecycleOwner(), news -> {
            // Update the cached copy of the words in the adapter.
            List<Articles> newsArticles = news.getArticles();
            articleArrayList.clear();
            articleArrayList.addAll(newsArticles);
            adapterNews.notifyDataSetChanged();
        });
        setupRecyclerView();
    }

    private void loadNewsSearch(String search) {
        homeViewModel.getAllNewsSearch(search).observe(getViewLifecycleOwner(), news -> {
            // Update the cached copy of the words in the adapter.
            List<Articles> newsArticles = news.getArticles();
            articleArrayList.clear();
            articleArrayList.addAll(newsArticles);
            adapterNews.notifyDataSetChanged();
        });
        setupRecyclerView();
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

    public void search_click() {
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation();
            }
        });
    }

    public void animation() {
        if (onsearch == false) {
            YoYo.with(Techniques.FadeOut)
                    .duration(500)
                    .playOn(binding.textNews);
            YoYo.with(Techniques.SlideInLeft)
                    .duration(500)
                    .playOn(binding.viewSearch);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.textNews.setVisibility(View.GONE);
                    binding.viewSearch.setVisibility(View.VISIBLE);
                }
            }, 500);
            onsearch = true;
        } else {
            YoYo.with(Techniques.FadeIn)
                    .duration(500)
                    .playOn(binding.textNews);
            YoYo.with(Techniques.SlideOutUp)
                    .duration(500)
                    .playOn(binding.viewSearch);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.textNews.setVisibility(View.VISIBLE);
                    binding.viewSearch.setVisibility(View.GONE);
                }
            }, 500);
            onsearch = false;
        }
    }

    public void business_click() {
        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("business")) {
                    binding.businessButton.setBackgroundColor(Color.RED);
                    binding.entertainmentButton.setBackgroundColor(Color.WHITE);
                    binding.generalButton.setBackgroundColor(Color.WHITE);
                    binding.healthButton.setBackgroundColor(Color.WHITE);
                    binding.scienceButton.setBackgroundColor(Color.WHITE);
                    binding.sportsButton.setBackgroundColor(Color.WHITE);
                    binding.technologyButton.setBackgroundColor(Color.WHITE);
                    category = "business";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }

    public void entertainment_click() {
        entertainmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("entertainment")) {
                    binding.businessButton.setBackgroundColor(Color.WHITE);
                    binding.entertainmentButton.setBackgroundColor(Color.RED);
                    binding.generalButton.setBackgroundColor(Color.WHITE);
                    binding.healthButton.setBackgroundColor(Color.WHITE);
                    binding.scienceButton.setBackgroundColor(Color.WHITE);
                    binding.sportsButton.setBackgroundColor(Color.WHITE);
                    binding.technologyButton.setBackgroundColor(Color.WHITE);
                    category = "entertainment";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }

    public void general_click() {
        generalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("general")) {
                    binding.businessButton.setBackgroundColor(Color.WHITE);
                    binding.entertainmentButton.setBackgroundColor(Color.WHITE);
                    binding.generalButton.setBackgroundColor(Color.RED);
                    binding.healthButton.setBackgroundColor(Color.WHITE);
                    binding.scienceButton.setBackgroundColor(Color.WHITE);
                    binding.sportsButton.setBackgroundColor(Color.WHITE);
                    binding.technologyButton.setBackgroundColor(Color.WHITE);
                    category = "general";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }

    public void health_click() {
        healthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("health")) {
                    binding.businessButton.setBackgroundColor(Color.WHITE);
                    binding.entertainmentButton.setBackgroundColor(Color.WHITE);
                    binding.generalButton.setBackgroundColor(Color.WHITE);
                    binding.healthButton.setBackgroundColor(Color.RED);
                    binding.scienceButton.setBackgroundColor(Color.WHITE);
                    binding.sportsButton.setBackgroundColor(Color.WHITE);
                    binding.technologyButton.setBackgroundColor(Color.WHITE);
                    category = "health";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }

    public void science_click() {
        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("science")) {
                    binding.businessButton.setBackgroundColor(Color.WHITE);
                    binding.entertainmentButton.setBackgroundColor(Color.WHITE);
                    binding.generalButton.setBackgroundColor(Color.WHITE);
                    binding.healthButton.setBackgroundColor(Color.WHITE);
                    binding.scienceButton.setBackgroundColor(Color.RED);
                    binding.sportsButton.setBackgroundColor(Color.WHITE);
                    binding.technologyButton.setBackgroundColor(Color.WHITE);
                    category = "science";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }

    public void sports_click() {
        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("sports")) {
                    binding.businessButton.setBackgroundColor(Color.WHITE);
                    binding.entertainmentButton.setBackgroundColor(Color.WHITE);
                    binding.generalButton.setBackgroundColor(Color.WHITE);
                    binding.healthButton.setBackgroundColor(Color.WHITE);
                    binding.scienceButton.setBackgroundColor(Color.WHITE);
                    binding.sportsButton.setBackgroundColor(Color.RED);
                    binding.technologyButton.setBackgroundColor(Color.WHITE);
                    category = "sports";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }

    public void technology_click() {
        technologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!category.equals("technology")) {
                    binding.businessButton.setBackgroundColor(Color.WHITE);
                    binding.entertainmentButton.setBackgroundColor(Color.WHITE);
                    binding.generalButton.setBackgroundColor(Color.WHITE);
                    binding.healthButton.setBackgroundColor(Color.WHITE);
                    binding.scienceButton.setBackgroundColor(Color.WHITE);
                    binding.sportsButton.setBackgroundColor(Color.WHITE);
                    binding.technologyButton.setBackgroundColor(Color.RED);
                    category = "technology";
                    homeViewModel.init();
                    loadNews(country, category);
                }
            }
        });
    }
}