package com.ayaanjaved.cpcalendar.fragments;

import android.os.Bundle;
import android.util.Log;
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

import com.ayaanjaved.cpcalendar.R;
import com.ayaanjaved.cpcalendar.adapters.CalenderAdapter;
import com.ayaanjaved.cpcalendar.models.AllContestsItem;
import com.ayaanjaved.cpcalendar.utils.ContestFilter;
import com.ayaanjaved.cpcalendar.viewmodels.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class HackerEarthFragment extends Fragment {
    private static final String TAG = "HackerEarthFragment";
    MainViewModel mainViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_hacker_earth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.hackerearth_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        CalenderAdapter calenderAdapter = mainViewModel.getCodeChefAdapter();
        recyclerView.setAdapter(calenderAdapter);

        mainViewModel.getAllContestItems().observe(getViewLifecycleOwner(), new Observer<List<AllContestsItem>>() {
            @Override
            public void onChanged(List<AllContestsItem> allContestsItemList) {
                List<AllContestsItem> contestsItems = ContestFilter.contestsFilter((ArrayList<AllContestsItem>) allContestsItemList, ContestFilter.HACKER_EARTH_FILTER);
                calenderAdapter.updateCalender(contestsItems);
                Log.i(TAG, "onChanged: ");
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
