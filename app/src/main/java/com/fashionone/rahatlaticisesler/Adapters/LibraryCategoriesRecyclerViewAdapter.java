package com.fashionone.rahatlaticisesler.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fashionone.rahatlaticisesler.Models.LibraryCategoryModel;
import com.fashionone.rahatlaticisesler.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class LibraryCategoriesRecyclerViewAdapter extends RecyclerView.Adapter<LibraryCategoriesRecyclerViewAdapter.ItemViewHolder> {
    //private PeopleItemClickListener listener;
    private Context context;
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView bgImg;
        ItemViewHolder(View rootView) {
            super(rootView);
            title = (TextView) rootView.findViewById(R.id.title);
            bgImg = (ImageView) rootView.findViewById(R.id.bgImg);
        }
    }

    private ArrayList<LibraryCategoryModel> contentItems = new ArrayList<>();

    public LibraryCategoriesRecyclerViewAdapter(Context context, JSONArray xy){
        this.context = context;
        try {
            for (int i = 0; i < xy.length(); i++) {
                LibraryCategoryModel x = new LibraryCategoryModel(xy.getJSONObject(i).getString("id"), xy.getJSONObject(i).getString("title"), xy.getJSONObject(i).getString("bgImage"));
                contentItems.add(x);
            }
        } catch (JSONException e) {
        }
        //this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_of_library_categories, viewGroup, false);
        final ItemViewHolder pvh = new ItemViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onItemClick(v, pvh.getPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.title.setText(contentItems.get(i).title);
        Picasso.with(context).load(contentItems.get(i).bgImage).into(itemViewHolder.bgImg);
    }

    @Override
    public int getItemCount() {
        return contentItems.size();
    }
}
