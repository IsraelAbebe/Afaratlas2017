package com.gmail.se.israel.abebe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.icog.mapsdemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Zone5_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone5_data);

        PhotoView Zone5educational = (PhotoView) findViewById(R.id.Zone5educational_img);
        Zone5educational.setImageResource(R.drawable.zone_5_education);

        PhotoView Zone5helth = (PhotoView) findViewById(R.id.Zone5helth_img);
        Zone5helth.setImageResource(R.drawable.zone_5_animal);

        PhotoView Zone5helthfacilities = (PhotoView) findViewById(R.id.Zone5helthfacilities_img);
        Zone5helthfacilities.setImageResource(R.drawable.zone_5_helth);

        PhotoView Zone5riligious = (PhotoView) findViewById(R.id.Zone5riligious_img);
        Zone5riligious.setImageResource(R.drawable.zone_5_religion);

        PhotoView Zone5water = (PhotoView) findViewById(R.id.Zone5water_img);
        Zone5water.setImageResource(R.drawable.zone_5_water);

        PhotoView Zone5village = (PhotoView) findViewById(R.id.Zone5village_img);
        Zone5village.setImageResource(R.drawable.zone_5_villages);

        PhotoView Zone5urban = (PhotoView) findViewById(R.id.Zone5urban_img);
        Zone5urban.setImageResource(R.drawable.zone_5_urban);

        PhotoView Zone5metrology = (PhotoView) findViewById(R.id.Zone5metrology_img);
        Zone5metrology.setImageResource(R.drawable.zone_5_metrology);

        PhotoView Zone5pub = (PhotoView) findViewById(R.id.Zone5pub_img);
        Zone5pub.setImageResource(R.drawable.zone_5_public);

        PhotoView Zone5comm = (PhotoView) findViewById(R.id.Zone5comm_img);
        Zone5comm.setImageResource(R.drawable.zone_5_commercial);

//        PhotoView Zone5infrustructure = (PhotoView) findViewById(R.id.Zone_4infrustructure_img);
//        Zone5infrustructure.setImageResource(R.drawable.zone_5_infr);
        
    }
}
