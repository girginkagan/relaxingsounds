package com.fashionone.rahatlaticisesler.Fragments;

import android.content.DialogInterface;
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
import com.fashionone.rahatlaticisesler.Adapters.LibraryCategoriesRecyclerViewAdapter;
import com.fashionone.rahatlaticisesler.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;


public class LibraryFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LibraryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView categoriesRecyclerView;
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

        //Get json response from file in assets folder
        String response = LoadData("library_category_json.html");
        try {
            JSONArray jsonArray = new JSONArray(response);
            //Set recyclerview adapter
            LibraryCategoriesRecyclerViewAdapter adapter = new LibraryCategoriesRecyclerViewAdapter(getContext(), jsonArray);
            categoriesRecyclerView.setAdapter(adapter);
            //Set recyclerview layout manager
            categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            categoriesRecyclerView.setHasFixedSize(true);

        } catch (JSONException e) {
        }

        return view;
    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = getActivity().getAssets().open(inFile);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
        }

        return tContents;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
