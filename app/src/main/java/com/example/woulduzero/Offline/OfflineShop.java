package com.example.woulduzero.Offline;

import android.os.Parcel;
import android.os.Parcelable;

public class OfflineShop implements Parcelable {
    private double lat;
    private double lng;
    private String store_name;
    private String address;
    private String store_phone_number;
    private String store_type;
    private String opening_hours;
    private String link;
    private String other_info;
    private String img1;
    private String img2;
    private String img3;

    public OfflineShop(){}

    public OfflineShop(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
        store_name = in.readString();
        address = in.readString();
        store_phone_number = in.readString();
        store_type = in.readString();
        opening_hours = in.readString();
        link = in.readString();
        other_info = in.readString();
        img1 = in.readString();
        img2 = in.readString();
        img3 = in.readString();
    }

    public static final Creator<OfflineShop> CREATOR = new Creator<OfflineShop>() {
        @Override
        public OfflineShop createFromParcel(Parcel in) {
            return new OfflineShop(in);
        }

        @Override
        public OfflineShop[] newArray(int size) {
            return new OfflineShop[size];
        }
    };

    public String getStore_name() {
        return store_name;
    }

    public String getAddress() {
        return address;
    }

    public String getStore_phone_number() {
        return store_phone_number;
    }

    public String getStore_type() {
        return store_type;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public String getLink() {
        return link;
    }

    public String getOther_info() {
        return other_info;
    }

    public String getImg1() {
        return img1;
    }

    public String getImg2() {
        return img2;
    }

    public String getImg3() {
        return img3;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(store_name);
        dest.writeString(address);
        dest.writeString(store_phone_number);
        dest.writeString(store_type);
        dest.writeString(opening_hours);
        dest.writeString(link);
        dest.writeString(other_info);
        dest.writeString(img1);
        dest.writeString(img2);
        dest.writeString(img3);
    }
}
