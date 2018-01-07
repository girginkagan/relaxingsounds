package com.fashionone.rahatlaticisesler.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.fashionone.rahatlaticisesler.Models.LibrarySongModel;
import com.fashionone.rahatlaticisesler.R;
import com.fashionone.rahatlaticisesler.Utils.FavoritesUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.fashionone.rahatlaticisesler.Utils.PlayerFactory.checkIfSongPlaying;
import static com.fashionone.rahatlaticisesler.Utils.PlayerFactory.getMediaPlayerVolume;
import static com.fashionone.rahatlaticisesler.Utils.PlayerFactory.pauseSong;
import static com.fashionone.rahatlaticisesler.Utils.PlayerFactory.playSong;
import static com.fashionone.rahatlaticisesler.Utils.PlayerFactory.setVolume;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ItemViewHolder> {
    private Context context;
    private Activity activity;
    private JSONArray jsonArray;
    private RecyclerView recyclerView;
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ToggleButton playPauseBtn;
        SeekBar volumeSeekBar;
        ToggleButton favBtn;
        ItemViewHolder(final View rootView) {
            super(rootView);
            title = (TextView) rootView.findViewById(R.id.title);
            playPauseBtn = (ToggleButton) rootView.findViewById(R.id.playPauseBtn);
            volumeSeekBar = (SeekBar) rootView.findViewById(R.id.volumeSeekBar);
            favBtn = (ToggleButton) rootView.findViewById(R.id.favBtn);
        }
    }

    private ArrayList<LibrarySongModel> contentItems = new ArrayList<>();

    public FavoritesRecyclerViewAdapter(Activity activity, Context context, JSONArray xy, RecyclerView recyclerView){
        this.context = context;
        this.activity = activity;
        this.recyclerView = recyclerView;
        jsonArray = xy;
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
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.title.setText(contentItems.get(i).title);
        itemViewHolder.playPauseBtn.setChecked(checkIfSongPlaying(contentItems.get(i).song));
        itemViewHolder.volumeSeekBar.setProgress(getMediaPlayerVolume(contentItems.get(i).song));
        itemViewHolder.favBtn.setChecked(FavoritesUtil.checkIfSongFavorited(contentItems.get(i).song));
        itemViewHolder.volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setVolume(((float)progress / 100), contentItems.get(i).song);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        itemViewHolder.favBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try {
                        FavoritesUtil.favoritesArray.put(jsonArray.getJSONObject(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    FavoritesUtil.SaveFavorites(activity, FavoritesUtil.favoritesArray.toString());
                }
                else{
                    for (int i = 0; i< FavoritesUtil.favoritesArray.length(); i++){
                        try {
                            if(FavoritesUtil.favoritesArray.getJSONObject(i).getString("song").equals(contentItems.get(i).song)){
                                FavoritesUtil.favoritesArray.remove(i);
                            }
                        } catch (JSONException e) {

                        }
                    }
                    FavoritesUtil.SaveFavorites(activity, FavoritesUtil.favoritesArray.toString());
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        itemViewHolder.playPauseBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    playSong(itemViewHolder.itemView.getContext(), contentItems.get(i).song, ((float)itemViewHolder.volumeSeekBar.getProgress() / 100));
                }
                else{
                    pauseSong(contentItems.get(i).song);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentItems.size();
    }
}
