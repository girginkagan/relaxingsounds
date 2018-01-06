package com.fashionone.rahatlaticisesler.Fragments;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fashionone.rahatlaticisesler.Activities.MainActivity;
import com.fashionone.rahatlaticisesler.Adapters.LibraryCategoriesRecyclerViewAdapter;
import com.fashionone.rahatlaticisesler.Interfaces.OnLibraryCategoryItemClick;
import com.fashionone.rahatlaticisesler.R;
import com.fashionone.rahatlaticisesler.SongCategory;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class LibraryFragment extends Fragment {

    FragmentTransaction fragmentTransaction;
    public LibraryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView categoriesRecyclerView;
    JSONArray jsonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        MainActivity activity = (MainActivity)getActivity();
        activity.changeToolbarTitle("Kitaplık");

        categoriesRecyclerView = (RecyclerView) view.findViewById(R.id.categoriesRecyclerView);

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialog.dismiss();

                        break;
                }
            }
        };

        //recycler view item click listener
        final OnLibraryCategoryItemClick clickListener = new OnLibraryCategoryItemClick() {
            @Override
            public void onItemClick(View v, int position) throws JSONException {
                MainActivity activity = (MainActivity)getActivity();
                activity.changeToolbarTitle(jsonArray.getJSONObject(position).getString("title"));
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                //set id as argument for the next fragment
                LibraryCategorySelectedFragment libraryCategorySelectedFragment = new LibraryCategorySelectedFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", jsonArray.getJSONObject(position).getString("id"));
                libraryCategorySelectedFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.rootFrame, libraryCategorySelectedFragment).addToBackStack(null).commit();
            }
        };

        //Get json response from file in assets folder
        String response = SongCategory.LoadCategories(getActivity());
        try {
            jsonArray = new JSONArray(response);
            //Set recyclerview adapter
            LibraryCategoriesRecyclerViewAdapter adapter = new LibraryCategoriesRecyclerViewAdapter(getContext(), jsonArray, clickListener);
            categoriesRecyclerView.setAdapter(adapter);
            //Set recyclerview layout manager
            categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            categoriesRecyclerView.setHasFixedSize(true);

        } catch (JSONException e) {
        }

        return view;
    }
}
