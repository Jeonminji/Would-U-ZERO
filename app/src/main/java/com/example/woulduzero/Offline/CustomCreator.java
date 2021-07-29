package com.example.woulduzero.Offline;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomCreator implements Parcelable.Creator<OfflineShop> {

    @Override
    public OfflineShop createFromParcel(Parcel source) {
        return new OfflineShop(source);
    }

    @Override
    public OfflineShop[] newArray(int size) {
        return new OfflineShop[size];
    }
}
