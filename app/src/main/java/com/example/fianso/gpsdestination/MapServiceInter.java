package com.example.fianso.gpsdestination;

import android.support.annotation.NonNull;

interface MapServiceInter {


    void initMap();

    void getLocationPermission();
    void OnrequestPermission(int requestCode,
                             @NonNull String permissions[],
                             @NonNull int[] grantResults);
    void updateLocationUI();
    void getDeviceLocation();
    void cleanMap();
}
