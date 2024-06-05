package com.wposs.apirickandmorty.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location extends BaseModel implements Parcelable {
    private final String name;
    private final String url;

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }

    // Parcelable implementation
    protected Location(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
    }
}
