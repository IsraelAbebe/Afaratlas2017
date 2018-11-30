package com.gmail.se.israel.abebe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.icog.mapsdemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Zone3_data extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone3_data);

        PhotoView Zone3educational = (PhotoView) findViewById(R.id.Zone3educational_img);
        Zone3educational.setImageResource(R.drawable.zone_3_education);

        PhotoView Zone3helth = (PhotoView) findViewById(R.id.Zone3helth_img);
        Zone3helth.setImageResource(R.drawable.zone_3_animal);

        PhotoView Zone3helthfacilities = (PhotoView) findViewById(R.id.Zone3helthfacilities_img);
        Zone3helthfacilities.setImageResource(R.drawable.zone_3_helth);

        PhotoView Zone3riligious = (PhotoView) findViewById(R.id.Zone3riligious_img);
        Zone3riligious.setImageResource(R.drawable.zone_3_religion);

        PhotoView Zone3water = (PhotoView) findViewById(R.id.Zone3water_img);
        Zone3water.setImageResource(R.drawable.zone_3_water);

        PhotoView Zone3village = (PhotoView) findViewById(R.id.Zone3village_img);
        Zone3village.setImageResource(R.drawable.zone_3_villages);

        PhotoView Zone3urban = (PhotoView) findViewById(R.id.Zone3urban_img);
        Zone3urban.setImageResource(R.drawable.zone_3_urban);

        PhotoView Zone3metrology = (PhotoView) findViewById(R.id.Zone3metrology_img);
        Zone3metrology.setImageResource(R.drawable.zone_3_metrology);

        PhotoView Zone3pub = (PhotoView) findViewById(R.id.Zone3pub_img);
        Zone3pub.setImageResource(R.drawable.zone_3_public);

        PhotoView Zone3comm = (PhotoView) findViewById(R.id.Zone3comm_img);
        Zone3comm.setImageResource(R.drawable.zone_3_commercial);

//        PhotoView Zone3infrustructure = (PhotoView) findViewById(R.id.Zone3in);
//        Zone3infrustructure.setImageResource(R.drawable.zone_3_infrustructure);
    }
}
