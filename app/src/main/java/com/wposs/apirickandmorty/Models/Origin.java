package com.wposs.apirickandmorty.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Origin extends BaseModel implements Parcelable {
    private final String name;
    private final String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }


    // Parcelable implementation
    protected Origin(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<Origin> CREATOR = new Creator<Origin>() {
        @Override
        public Origin createFromParcel(Parcel in) {
            return new Origin(in);
        }

        @Override
        public Origin[] newArray(int size) {
            return new Origin[size];
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
