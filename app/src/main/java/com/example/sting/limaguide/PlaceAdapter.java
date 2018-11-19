package com.example.sting.limaguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

/**
 * {@link PlaceAdapter} builds up RecyclerView to be displayed inside each instance of
 * {@link CategoryFragment}
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceAdapterViewHolder> {

    private ArrayList<Place> places;
    private Context context;
    private boolean[] isExpanded ;

    /**
     * Constructor to create an instance of the adapter
     * @param context is the correct context
     * @param places is the list of {@link Place} objects to be displayed
     * @param isExpanded is the states of the items inside recycler view, could be null when app
     *                   is first launched
     */
    public PlaceAdapter(Context context, ArrayList<Place> places,@Nullable boolean[] isExpanded) {
        this.context = context;
        this.places = places;

        // Check state of views
        if (isExpanded == null) {
            // Initialize array and set state of views
            isExpanded = new boolean[getItemCount()];
            for (int i = 0; i < getItemCount(); i++) {
                isExpanded[i] = false;
            }
        }

        this.isExpanded = isExpanded;
    }

    @Override
    public PlaceAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,
                false);
        return new PlaceAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceAdapterViewHolder holder, final int position) {

        // Transformation object that allows picasso to resize image according to size of container
        // view
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.placeImageView.getWidth();
                int targetHeight = holder.placeImageView.getHeight();

                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight,
                        false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        // Bind data from places to views
        holder.titleTextView.setText(places.get(position).getTitle());
        if (places.get(position).getCost() == 0) {
            holder.costTextView.setVisibility(View.GONE);
        } else {
            if (places.get(position).getCost() == 1)
                holder.costTextView.setText(context.getResources().getString(R.string.cost_low));
            else if (places.get(position).getCost() == 2)
                holder.costTextView.setText(context.getResources().getString(R.string.cost_medium));
            else if (places.get(position).getCost() == 3)
                holder.costTextView.setText(context.getResources().getString(R.string.cost_high));
            holder.costTextView.setVisibility(View.VISIBLE);
        }
        holder.descriptionTextView.setText(places.get(position).getDescription());
        holder.locationTextView.setText(places.get(position).getLocation());
        holder.telephoneTextView.setText(places.get(position).getTelephone());
        holder.hoursTextView.setText(places.get(position).getHours());
        Picasso.with(context).load(places.get(position).getImageResourceID()).
                transform(transformation).
                into(holder.placeImageView);

        // Check state of view before displaying it
        if (isExpanded[position]) {
            holder.descriptionTextView.setVisibility(View.VISIBLE);
            holder.detailsLinearLayout.setVisibility(View.VISIBLE);
            holder.lineMiddleView.setVisibility(View.VISIBLE);
            holder.expandImageView.setImageResource(R.drawable.arrow_up);
        } else {
            holder.expandImageView.setImageResource(R.drawable.arrow_down);
            holder.detailsLinearLayout.setVisibility(View.GONE);
            holder.descriptionTextView.setVisibility(View.GONE);
            holder.lineMiddleView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class PlaceAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Views that make up an item in recycler view
        CardView containerCardView;
        LinearLayout detailsLinearLayout;
        TextView titleTextView, costTextView, descriptionTextView, locationTextView,
                telephoneTextView, hoursTextView;
        View lineMiddleView;
        MaterialButton mapButton, reviewsButton;
        ImageView placeImageView,expandImageView;

        public PlaceAdapterViewHolder(View itemView) {
            super(itemView);
            costTextView = itemView.findViewById(R.id.cost_text_view);
            containerCardView = itemView.findViewById(R.id.container_card_view);
            expandImageView = itemView.findViewById(R.id.expand_image_view);
            lineMiddleView = itemView.findViewById(R.id.line_middle_view);
            detailsLinearLayout = itemView.findViewById(R.id.details_linear_layout);
            mapButton = itemView.findViewById(R.id.map_button);
            reviewsButton = itemView.findViewById(R.id.reviews_button);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            locationTextView = itemView.findViewById(R.id.location_text_view);
            telephoneTextView = itemView.findViewById(R.id.telephone_text_view);
            hoursTextView = itemView.findViewById(R.id.hours_text_view);
            placeImageView = itemView.findViewById(R.id.place_image_view);
            expandImageView.setImageResource(R.drawable.arrow_down);
            costTextView.setVisibility(View.GONE);
            lineMiddleView.setVisibility(View.GONE);
            detailsLinearLayout.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
            mapButton.setOnClickListener(this);
            reviewsButton.setOnClickListener(this);
            expandImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final boolean[] isMapClicked = {false};
            final boolean[] isReviewsClicked = {false};
            final boolean[] isShowMoreClicked = {false};

            // Loads up another activity to show location on map
            if (view == mapButton) {
                final double latitude = places.get(getAdapterPosition()).getLatitude();
                final double longitude = places.get(getAdapterPosition()).getLongitude();
                final String title = places.get(getAdapterPosition()).getTitle();
                if (!isMapClicked[0]) {
                    // Using a runnable to get a smoother transition after button is clicked
                    mapButton.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Intent to load MapActivity passes place's
                            // location information as an extra
                            context.startActivity(new Intent(context, MapActivity.class)
                                    .putExtra("latitude", latitude)
                                    .putExtra("longitude", longitude)
                                    .putExtra("title", title));
                            isMapClicked[0] = true;
                        }
                    }, 400);
                }
                isMapClicked[0] = false;
            }

            // Loads up another activity to show website with place's reviews
            if (view == reviewsButton) {
                final String reviewURL = places.get(getAdapterPosition()).getReviewURL();
                if (!isReviewsClicked[0]) {
                    // Using a runnable to get a smoother transition after button is clicked
                    reviewsButton.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Intent to load ReviewActivity passes URL for review website
                            // as an extra
                            context.startActivity(new Intent(context, ReviewActivity.class)
                                    .putExtra("reviewURL", reviewURL));
                            isReviewsClicked[0] = true;
                        }
                    }, 400);
                }
                isReviewsClicked[0] = false;
            }

            // Expands description to show more information
            if (view == expandImageView) {
                if (!isShowMoreClicked[0]) {
                    // Using a runnable to get a smoother transition after button is clicked
                    expandImageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!isExpanded[getAdapterPosition()]) {
                                descriptionTextView.setVisibility(View.VISIBLE);
                                detailsLinearLayout.setVisibility(View.VISIBLE);
                                lineMiddleView.setVisibility(View.VISIBLE);
                                expandImageView.setImageResource(R.drawable.arrow_up);
                                isExpanded[getAdapterPosition()] = true;
                            } else {
                                expandImageView.setImageResource(R.drawable.arrow_down);
                                detailsLinearLayout.setVisibility(View.GONE);
                                descriptionTextView.setVisibility(View.GONE);
                                lineMiddleView.setVisibility(View.GONE);
                                isExpanded[getAdapterPosition()] = false;
                            }
                            isShowMoreClicked[0] = true;
                        }
                    }, 400);
                }
                isShowMoreClicked[0] = false;
            }
        }
    }

    // Returns state of views
    public boolean[] getIsExpanded() {
        return isExpanded;
    }
}


