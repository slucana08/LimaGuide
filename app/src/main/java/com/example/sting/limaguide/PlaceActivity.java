package com.example.sting.limaguide;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

/**
 * Main page where information about the different places is shown, it makes use of
 * {@link CategoryAdapter} to call the correct instance of {@link CategoryFragment}
 */

public class PlaceActivity extends AppCompatActivity {

    // Array that contains the state of views of all fragments
    private ArrayList<boolean[]> isExpandedCheck;

    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        float density = getResources().getDisplayMetrics().density;

        // Set ToolBar as ActionBar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Drawable dr = getResources().getDrawable(R.mipmap.lima_launcher_round);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap,
                (int) (36 * density), (int) (36 * density), true));
        getSupportActionBar().setIcon(d);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewPager);

        // Checks if there is an instance saved
        if (savedInstanceState != null){
            // Initialize ArrayList and Retrieve values saved in bundle
            isExpandedCheck = new ArrayList<>();
            isExpandedCheck.add(savedInstanceState.getBooleanArray("stateFragmentA"));
            isExpandedCheck.add(savedInstanceState.getBooleanArray("stateFragmentB"));
            isExpandedCheck.add(savedInstanceState.getBooleanArray("stateFragmentC"));
            isExpandedCheck.add(savedInstanceState.getBooleanArray("stateFragmentD"));
        }

        // Create an adapter that knows which fragment should be shown on each page passing the
        // state of views inside fragments to be created
        adapter = new CategoryAdapter(getSupportFragmentManager(),this,
                isExpandedCheck);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Check phone orientation
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            // Fix TabLayout
            tabLayout.setTabMode(TabLayout.MODE_FIXED);;
        } else {
            // In portrait
            // Make TabLayout scrollable
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save state of views in fragments inside the bundle
        isExpandedCheck = adapter.getIsExpandedCheck();
        outState.putBooleanArray("stateFragmentA",isExpandedCheck.get(0));
        outState.putBooleanArray("stateFragmentB",isExpandedCheck.get(1));
        outState.putBooleanArray("stateFragmentC",isExpandedCheck.get(2));
        outState.putBooleanArray("stateFragmentD",isExpandedCheck.get(3));
        super.onSaveInstanceState(outState);
    }


}


