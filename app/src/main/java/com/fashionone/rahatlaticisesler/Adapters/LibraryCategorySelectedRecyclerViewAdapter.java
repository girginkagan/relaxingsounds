package com.fashionone.rahatlaticisesler.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fashionone.rahatlaticisesler.Models.LibrarySongModel;
import com.fashionone.rahatlaticisesler.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class LibraryCategorySelectedRecyclerViewAdapter extends RecyclerView.Adapter<LibraryCategorySelectedRecyclerViewAdapter.ItemViewHolder> {
    private Context context;
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ItemViewHolder(View rootView) {
            super(rootView);
            title = (TextView) rootView.findViewById(R.id.title);
        }
    }

    private ArrayList<LibrarySongModel> contentItems = new ArrayList<>();

    public LibraryCategorySelectedRecyclerViewAdapter(Context context, JSONArray xy){
        this.context = context;
        try {
            for (int i = 0; i < xy.length(); i++) {
                LibrarySongModel x = new LibrarySongModel(xy.getJSONObject(i).getString("title"), xy.getJSONObject(i).getString("song"));
                contentItems.add(x);
            }
        } catch (JSONException e) {
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_of_library_category_selected, viewGroup, false);
        final ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.title.setText(contentItems.get(i).title);
    }

    @Override
    public int getItemCount() {
        return contentItems.size();
    }
}
