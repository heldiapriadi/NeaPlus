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

import com.example.neaplus.core.usecase.BookmarkViewModel;
import com.example.neaplus.databinding.FragmentBookmarkBinding;

public class BookmarkFragment extends Fragment {

    private com.example.neaplus.core.usecase.BookmarkViewModel BookmarkViewModel;
    private FragmentBookmarkBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel =
                new ViewModelProvider(this).get(BookmarkViewModel.class);

        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textBookmark;
        BookmarkViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}