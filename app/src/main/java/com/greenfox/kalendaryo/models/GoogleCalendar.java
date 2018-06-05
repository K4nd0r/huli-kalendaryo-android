package com.greenfox.kalendaryo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.greenfox.kalendaryo.adapter.SharingOptionsAdapter;

import java.io.Serializable;

public class GoogleCalendar implements Parcelable, Serializable {

    String id;
    String summary;
    SharingOptionsAdapter.VisibilityOption sharingOption;

    public GoogleCalendar() {
    }

    public GoogleCalendar(Parcel in) {
        id = in.readString();
        summary = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SharingOptionsAdapter.VisibilityOption getSharingOption() {
        return sharingOption;
    }

    public void setSharingOption(SharingOptionsAdapter.VisibilityOption sharingOption) {
        this.sharingOption = sharingOption;
    }

    public static final Creator<GoogleCalendar> CREATOR = new Creator<GoogleCalendar>() {
        @Override
        public GoogleCalendar createFromParcel(Parcel in) {
            return new GoogleCalendar(in);
        }

        @Override
        public GoogleCalendar[] newArray(int size) {
            return new GoogleCalendar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(summary);
    }
}
