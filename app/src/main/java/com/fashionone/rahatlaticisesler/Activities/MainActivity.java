package com.fashionone.rahatlaticisesler.Activities;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fashionone.rahatlaticisesler.Fragments.FavoritesFragment;
import com.fashionone.rahatlaticisesler.Fragments.LibraryFragment;
import com.fashionone.rahatlaticisesler.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class MainActivity extends AppCompatActivity {

    TextView toolbarTitle;
    FragmentTransaction fragmentTransaction;
    RadioButton favoritesRadioBtn;
    RadioButton libraryRadioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        favoritesRadioBtn = (RadioButton) findViewById(R.id.favsRadioBtn);
        libraryRadioBtn = (RadioButton) findViewById(R.id.libRadioBtn);

        Drawable drawableFavorites = MaterialDrawableBuilder.with(MainActivity.this)
                .setIcon(MaterialDrawableBuilder.IconValue.HEART)
                .setColor(getResources().getColor(R.color.colorWhite))
                .setToActionbarSize()
                .setSizeDp(20)
                .build();
        Drawable drawableLibrary = MaterialDrawableBuilder.with(MainActivity.this)
                .setIcon(MaterialDrawableBuilder.IconValue.HAMBURGER)
                .setColor(getResources().getColor(R.color.colorWhite))
                .setToActionbarSize()
                .setSizeDp(20)
                .build();


        //Set drawables for tab radio buttons
        favoritesRadioBtn.setCompoundDrawables(null,drawableFavorites,null,null);
        libraryRadioBtn.setCompoundDrawables(null,drawableLibrary,null,null);


        //Set OnCheckedChangeListeners for tab radio buttons
        favoritesRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.rootFrame, new FavoritesFragment());
                    fragmentTransaction.commit();
                }
            }
        });
        libraryRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.rootFrame, new LibraryFragment());
                    fragmentTransaction.commit();
                }
            }
        });

        //Load library as the first page
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.rootFrame, new LibraryFragment());
        fragmentTransaction.commit();
    }

    //Change toolbar title method for fragments
    public void changeToolbarTitle(String title){
        toolbarTitle.setText(title);
    }
}
