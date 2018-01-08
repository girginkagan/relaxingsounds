package com.fashionone.rahatlaticisesler.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashionone.rahatlaticisesler.Activities.MainActivity;
import com.fashionone.rahatlaticisesler.Adapters.FavoritesRecyclerViewAdapter;
import com.fashionone.rahatlaticisesler.Adapters.LibraryCategorySelectedRecyclerViewAdapter;
import com.fashionone.rahatlaticisesler.R;
import com.fashionone.rahatlaticisesler.Utils.FavoritesUtil;
import com.fashionone.rahatlaticisesler.Utils.SongCategory;

import org.json.JSONArray;
import org.json.JSONException;

public class FavoritesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        MainActivity activity = (MainActivity)getActivity();
        activity.changeToolbarTitle("Favorilerim");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //Set recyclerview adapter
        FavoritesRecyclerViewAdapter adapter = new FavoritesRecyclerViewAdapter(getActivity(), getContext(), FavoritesUtil.favoritesArray);
        recyclerView.setAdapter(adapter);
        //Set recyclerview layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        return view;
    }
}
