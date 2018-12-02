package com.example.fianso.gpsdestination;

import android.widget.TextView;

import java.util.List;

interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> routes);
}
