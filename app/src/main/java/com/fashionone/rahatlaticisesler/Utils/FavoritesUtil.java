package com.fashionone.rahatlaticisesler.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

public class FavoritesUtil {

    private static String KEY_FAVS = "favorites";
    public static JSONArray favoritesArray = new JSONArray();

    public static void LoadFavorites(Activity activity) throws JSONException {
        SharedPreferences prefs = activity.getSharedPreferences("RunningAssistant.preferences", Context.MODE_PRIVATE);
        String val = prefs.getString(KEY_FAVS, "");
        if(!val.equals("")){
            favoritesArray = new JSONArray(val);
        }
    }

    public static void SaveFavorites(Activity activity, String newJSON)
    {
        SharedPreferences prefs = activity.getSharedPreferences("RunningAssistant.preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_FAVS, newJSON);
        editor.commit();
    }

    public static boolean checkIfSongFavorited(String song) {
        for (int i=0;i<favoritesArray.length();i++){
            try {
                if(song.equals(favoritesArray.getJSONObject(i).getString("song")))
                    return true;
            } catch (JSONException e) {

            }
        }
        return false;
    }
}
