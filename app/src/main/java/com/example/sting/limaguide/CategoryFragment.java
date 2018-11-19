package com.example.sting.limaguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * {@link CategoryFragment} displays a different list of items according to the set of data passed
 * to it when instantiated
 */

public class CategoryFragment extends Fragment {

    private ArrayList<Place> places;
    private boolean[] isExpanded;
    PlaceAdapter adapter;

    /**
     * @param places is the set of data to be shown in this fragment
     * @param isExpanded is the state of the views shown inside the fragment, could be null when
     *                   app is first launched
     * @return the fragment with designated set of information
     */
    public static CategoryFragment newInstance(ArrayList<Place> places,@Nullable
            boolean[] isExpanded){
        CategoryFragment f = new CategoryFragment();
        Bundle args = new Bundle();
        // Pass the ArrayList inside the bundle to be used on view creation
        args.putParcelableArrayList("category",places);
        // Pass the state of views to create fragment accordingly
        args.putBooleanArray("stateOfViews",isExpanded);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.place_list, container,
                false);
        places = getArguments().getParcelableArrayList("category");
        isExpanded = getArguments().getBooleanArray("stateOfViews");

        // Checks if there is an instance saved
        if (savedInstanceState != null){
            // Retrieve values saved in bundle
            isExpanded = savedInstanceState.getBooleanArray("stateOfViews");
        }

        // Create adapter using data to populate views inside it and
        // values representing state of the views to be created
        adapter = new PlaceAdapter(getActivity(), places, isExpanded);

        // Initialize values for state of views and capture new values
        isExpanded = new boolean[adapter.getItemCount()];
        isExpanded = adapter.getIsExpanded();

        // Attach adapter to recyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save values in bundle when fragment is destroyed
         outState.putBooleanArray("stateOfViews", isExpanded);
    }

    // Returns values that represent state of views
    public boolean[] getIsExpanded() {
        return isExpanded;
    }
}
