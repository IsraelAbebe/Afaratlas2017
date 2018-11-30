package com.gmail.se.israel.abebe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.icog.mapsdemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Zone4_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone4_data);

        PhotoView Zone4educational = (PhotoView) findViewById(R.id.Zone4educational_img);
        Zone4educational.setImageResource(R.drawable.zone_4_education);

        PhotoView Zone4helth = (PhotoView) findViewById(R.id.Zone4helth_img);
        Zone4helth.setImageResource(R.drawable.zone_4_animal);

        PhotoView Zone4helthfacilities = (PhotoView) findViewById(R.id.Zone4helthfacilities_img);
        Zone4helthfacilities.setImageResource(R.drawable.zone_4_helth);

        PhotoView Zone4riligious = (PhotoView) findViewById(R.id.Zone4riligious_img);
        Zone4riligious.setImageResource(R.drawable.zone_4_religion);

        PhotoView Zone4water = (PhotoView) findViewById(R.id.Zone4water_img);
        Zone4water.setImageResource(R.drawable.zone_4_water);

        PhotoView Zone4village = (PhotoView) findViewById(R.id.Zone4village_img);
        Zone4village.setImageResource(R.drawable.zone_4_villages);

        PhotoView Zone4urban = (PhotoView) findViewById(R.id.Zone4urban_img);
        Zone4urban.setImageResource(R.drawable.zone_4_urban);

        PhotoView Zone4metrology = (PhotoView) findViewById(R.id.Zone4metrology_img);
        Zone4metrology.setImageResource(R.drawable.zone_4_metrology);

        PhotoView Zone4pub = (PhotoView) findViewById(R.id.Zone4pub_img);
        Zone4pub.setImageResource(R.drawable.zone_4_public);
//
//        PhotoView Zone4comm = (PhotoView) findViewById(R.id.Zone4comm_img);
//        Zone4comm.setImageResource(R.drawable.zone_4_c);

//        PhotoView Zone4infrustructure = (PhotoView) findViewById(R.id.Zone_4infrustructure_img);
//        Zone4infrustructure.setImageResource(R.drawable.zone_4_in);
    }

}
