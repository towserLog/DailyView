package io.piazi.quiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by towse on 2017/10/7.
 */

public class Music implements Parcelable {
    private String artist;
    private String album;
    private String title;
    private String data;

    public Music() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    protected Music(Parcel in) {
        artist = in.readString();
        album = in.readString();
        title = in.readString();
        data = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeString(title);
        dest.writeString(data);
    }
}
