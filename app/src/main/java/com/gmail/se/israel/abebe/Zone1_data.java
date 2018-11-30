package com.gmail.se.israel.abebe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.icog.mapsdemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Zone1_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone1_data);

        PhotoView zone1educational = (PhotoView) findViewById(R.id.zone1educational_img);
        zone1educational.setImageResource(R.drawable.zone_1_education);

        PhotoView zone1helth = (PhotoView) findViewById(R.id.zone1helth_img);
        zone1helth.setImageResource(R.drawable.zone_1_animal);

        PhotoView zone1helthfacilities = (PhotoView) findViewById(R.id.zone1helthfacilities_img);
        zone1helthfacilities.setImageResource(R.drawable.zone_1_helth);

        PhotoView zone1riligious = (PhotoView) findViewById(R.id.zone1riligious_img);
        zone1riligious.setImageResource(R.drawable.zone_1_religion);

        PhotoView zone1water = (PhotoView) findViewById(R.id.zone1water_img);
        zone1water.setImageResource(R.drawable.zone_1_water);

        PhotoView zone1village = (PhotoView) findViewById(R.id.zone1village_img);
        zone1village.setImageResource(R.drawable.zone_1_villages);

        PhotoView zone1urban = (PhotoView) findViewById(R.id.zone1urban_img);
        zone1urban.setImageResource(R.drawable.zone_1_urban);

        PhotoView zone1metrology = (PhotoView) findViewById(R.id.zone1metrology_img);
        zone1metrology.setImageResource(R.drawable.zone_1_metrology);

        PhotoView zone1pub = (PhotoView) findViewById(R.id.zone1pub_img);
        zone1pub.setImageResource(R.drawable.zone_1_public);

        PhotoView zone1comm = (PhotoView) findViewById(R.id.zone1comm_img);
        zone1comm.setImageResource(R.drawable.zone_1_commercial);

        PhotoView zone1infrustructure = (PhotoView) findViewById(R.id.zone1infrustructure_img);
        zone1infrustructure.setImageResource(R.drawable.zone_1_infrustructure);
    }
}
