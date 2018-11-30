package com.gmail.se.israel.abebe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.icog.mapsdemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Zone2_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone2_data);

        PhotoView zone2educational = (PhotoView) findViewById(R.id.Zone2educational_img);
        zone2educational.setImageResource(R.drawable.zone_2_education);

        PhotoView Zone2helth = (PhotoView) findViewById(R.id.Zone2helth_img);
        Zone2helth.setImageResource(R.drawable.zone_2_animal);

        PhotoView Zone2helthfacilities = (PhotoView) findViewById(R.id.Zone2helthfacilities_img);
        Zone2helthfacilities.setImageResource(R.drawable.zone_2_helth);

        PhotoView Zone2riligious = (PhotoView) findViewById(R.id.Zone2riligious_img);
        Zone2riligious.setImageResource(R.drawable.zone_2_religion);

        PhotoView Zone2water = (PhotoView) findViewById(R.id.Zone2water_img);
        Zone2water.setImageResource(R.drawable.zone_2_water);

        PhotoView Zone2village = (PhotoView) findViewById(R.id.Zone2village_img);
        Zone2village.setImageResource(R.drawable.zone_2_villages);

        PhotoView Zone2urban = (PhotoView) findViewById(R.id.Zone2urban_img);
        Zone2urban.setImageResource(R.drawable.zone_2_urban);

        PhotoView Zone2metrology = (PhotoView) findViewById(R.id.Zone2metrology_img);
        Zone2metrology.setImageResource(R.drawable.zone_2_metrology);

        PhotoView Zone2pub = (PhotoView) findViewById(R.id.Zone2pub_img);
        Zone2pub.setImageResource(R.drawable.zone_2_public);

        PhotoView Zone2comm = (PhotoView) findViewById(R.id.Zone2comm_img);
        Zone2comm.setImageResource(R.drawable.zone_2_commercial);

        PhotoView Zone2infrustructure = (PhotoView) findViewById(R.id.Zone2infrustructure_img);
        Zone2infrustructure.setImageResource(R.drawable.zone_2_infrustructure);
    }
}
