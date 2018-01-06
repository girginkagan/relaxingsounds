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

import com.fashionone.rahatlaticisesler.Adapters.LibraryCategoriesRecyclerViewAdapter;
import com.fashionone.rahatlaticisesler.Adapters.LibraryCategorySelectedRecyclerViewAdapter;
import com.fashionone.rahatlaticisesler.R;
import com.fashionone.rahatlaticisesler.SongCategory;

import org.json.JSONArray;
import org.json.JSONException;

public class LibraryCategorySelectedFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recyclerView;
    JSONArray jsonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_category_selected, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.songsRecyclerView);

        //Get json response from file in assets folder
        String response = SongCategory.LoadSongsJSON(getActivity(), getArguments().getString("id"));
        try {
            Log.i("x", response);
            jsonArray = new JSONArray(response);
            //Set recyclerview adapter
            LibraryCategorySelectedRecyclerViewAdapter adapter = new LibraryCategorySelectedRecyclerViewAdapter(getContext(), jsonArray);
            recyclerView.setAdapter(adapter);
            //Set recyclerview layout manager
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setHasFixedSize(true);

        } catch (JSONException e) {
            Log.i("x", e.toString());
        }

        return view;
    }
}
