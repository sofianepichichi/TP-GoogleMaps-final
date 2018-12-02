package com.example.fianso.gpsdestination;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements DirectionFinderListener {



    EditText txt1;
    TextView textRoute;
    TextView textDistance;
    EditText txt2;

    Button btnPath;

    private MapService mMapService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txt2 = (EditText) findViewById(R.id.etDestination);
      //btn = (Button) findViewById(R.id.btnLocation);
        txt1 = (EditText) findViewById(R.id.etOrigin);
        btnPath = (Button) findViewById(R.id.btnFindPath);
        textRoute =  ((TextView) findViewById(R.id.tvDuration));
        textDistance =((TextView) findViewById(R.id.tvDistance));


        btnPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendRequest();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        this.mMapService = new MapService(MapsActivity.this);
        this.mMapService.initMap();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        this.mMapService.OnrequestPermission(requestCode, permissions, grantResults);
    }

    private void sendRequest() throws UnsupportedEncodingException {
        String origin = txt1.getText().toString();
        String destination = txt2.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        new DirectionFinder((DirectionFinderListener) this, origin, destination).execute();
    }



    @Override
    public void onDirectionFinderStart() {
        this.mMapService.onDirectionFinderStart();
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        this.mMapService.onDirectionFinderSuccess(routes);

    }

}