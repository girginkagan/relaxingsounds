package com.fashionone.rahatlaticisesler;

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;

public class SongCategory {

    public static String LoadCategories(Activity activity) {
        String tContents = "";

        try {
            InputStream stream = activity.getAssets().open("library_category_json.html");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
        }

        return tContents;

    }


    public static String LoadSongsJSON(Activity activity, String id){
        String tContents = "";
        InputStream stream = null;
        try {
            switch (id) {
                case "1":
                    stream = activity.getAssets().open("library_birds.html");
                    break;
                case "2":
                    stream = activity.getAssets().open("library_birds.html");
                    break;
                case "3":
                    stream = activity.getAssets().open("library_birds.html");
                    break;
            }
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
        }

        return tContents;
    }
}
