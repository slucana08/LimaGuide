package com.example.sting.limaguide;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * {@link Place} defines the places available in the app and all of their attributes
 * Implements Parcelable so that a list of Place instances can be transferred through an intent
 */
public class Place implements Parcelable {

    private String mTitle;
    private int mCost = 0;
    private int mImageResourceID;
    private String mDescription;
    private String mLocation;
    private String mTelephone;
    private String mHours;
    private double mLatitude;
    private double mLongitude;
    private String mReviewURL;

    // Constructor to create a landmark, will not have telephone
    // requires a context passed to it to pull strings from resources
    public Place (Context context, String mTitle, int mImageResourceID, String mDescription,
                  String mLocation, String hours, double mLatitude, double mLongitude,
                  String mReviewURL){
        this.mTitle = mTitle;
        this.mImageResourceID = mImageResourceID;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mTelephone = context.getString(R.string.no_phone);
        this.mHours = hours;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mReviewURL = mReviewURL;
    }

    // Constructor to create a restaurant, bar and museum
    // Needs all fields to instantiate object
    public Place (String mTitle, int mCost, int mImageResourceID, String mDescription,
                  String mLocation, String mTelephone, String mHours, Double mLatitude,
                  Double mLongitude, String mReviewURL){
        this.mTitle = mTitle;
        this.mCost = mCost;
        this.mImageResourceID = mImageResourceID;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mTelephone = mTelephone;
        this.mHours = mHours;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mReviewURL = mReviewURL;
    }

    protected Place(Parcel in) {
        mTitle = in.readString();
        mCost = in.readInt();
        mImageResourceID = in.readInt();
        mDescription = in.readString();
        mLocation = in.readString();
        mTelephone = in.readString();
        mHours = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
        mReviewURL = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getTitle(){ return mTitle; }
    public void setTitle (String title) { this.mTitle = title; }
    public int getCost(){ return mCost; };
    public void setCost(int cost){ this.mCost = cost; }
    public int getImageResourceID(){return mImageResourceID;}
    public void setImageResourceID (int imageResourceID) { this.mImageResourceID = imageResourceID;}
    public String getDescription(){ return mDescription; }
    public void setDescription (String description) { this.mDescription = description; }
    public String getLocation(){ return mLocation; }
    public void setLocation (String location) { this.mLocation = location; }
    public String getTelephone(){ return mTelephone; }
    public void setTelephone (String telephone) { this.mTelephone = telephone; }
    public String getHours(){return mHours;}
    public void setHours (String hours) { this.mHours = hours; }
    public double getLatitude(){ return mLatitude; }
    public void setLatitude (double latitude) { this.mLatitude = latitude; }
    public double getLongitude(){ return mLongitude; }
    public void setLongitude (double longitude) { this.mLongitude = longitude; }
    public String getReviewURL(){ return mReviewURL;}
    public void setReviewURL (String reviewURL) { this.mReviewURL = reviewURL; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeInt(mCost);
        parcel.writeInt(mImageResourceID);
        parcel.writeString(mDescription);
        parcel.writeString(mLocation);
        parcel.writeString(mTelephone);
        parcel.writeString(mHours);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
        parcel.writeString(mReviewURL);
    }
}
