package com.wposs.apirickandmorty.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Character extends BaseModel implements Parcelable {
    private final int id;
    private final String name;
    private final String status;
    private final String species;
    private final String type;
    private final String gender;
    private final Origin origin;
    private final Location location;
    private final String image;
    private final List<String> episode;
    private final String url;
    private final String created;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getStatus() {
        return status;
    }
    public String getSpecies() {
        return species;
    }
    public String getType() {
        return type;
    }
    public String getGender() {
        return gender;
    }
    public Origin getOrigin() {
        return origin;
    }
    public Location getLocation() {
        return location;
    }
    public String getImage() {
        return image;
    }
    public List<String> getEpisode() {
        return episode;
    }
    public String getUrl() {
        return url;
    }
    public String getCreated() {
        return created;
    }

    protected Character(Parcel in){
        id = in.readInt();
        name = in.readString();
        status = in.readString();
        species = in.readString();
        type = in.readString();
        gender = in.readString();
        origin = in.readParcelable(Origin.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
        image = in.readString();
        episode = in.createStringArrayList();
        url = in.readString();
        created = in.readString();
    }
    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(status);
        parcel.writeString(species);
        parcel.writeString(type);
        parcel.writeString(gender);
        parcel.writeParcelable(origin, flags);
        parcel.writeParcelable(location, flags);
        parcel.writeString(image);
        parcel.writeStringList(episode);
        parcel.writeString(url);
        parcel.writeString(created);
    }
}
