package com.example.icog.mapsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gmail.se.israel.abebe.AfarInfo;
import com.gmail.se.israel.abebe.Zone1_data;
import com.gmail.se.israel.abebe.Zone2_data;
import com.gmail.se.israel.abebe.Zone3_data;
import com.gmail.se.israel.abebe.Zone4_data;
import com.gmail.se.israel.abebe.Zone5_data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.ui.IconGenerator;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    public static float MAX_ZOOM = 11;
    public static float MIN_ZOOM = 7;
    private int ZONE_NUM = 0 ;

    public int ZOOM_TO_1 = 0;
    public int ZOOM_TO_2 = 0;
    private int maptype = 1;

    private int REFRESH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (maptype == 1) {
                    maptype = 2;
                } else {
                    maptype = 1;
                }
                onMapReady(mMap);

//                Snackbar.make(view, "Map View Changed", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(getApplicationContext(), "Recent Works Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(this, galleryShow.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Gallery Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, Contact.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Gallery Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(), "Tools Clicked", Toast.LENGTH_LONG).show();
        }else if (id == R.id.AboutAfar) {
            Intent i = getZoneIntent("AfarInfo");
            startActivity(i);
        }else if (id == R.id.full_map) {
            ZOOM_TO_1 = 0;
            onMapReady(mMap);
        } else if (id == R.id.zone_one) {
            ZOOM_TO_1 = 1;
            onMapReady(mMap);
        } else if (id == R.id.zone_two) {
            ZOOM_TO_1 = 2;
            onMapReady(mMap);
        } else if (id == R.id.zone_three) {
            ZOOM_TO_1 = 3;
            onMapReady(mMap);
        } else if (id == R.id.zone_four) {
            ZOOM_TO_1 = 4;
            onMapReady(mMap);
        } else if (id == R.id.zone_five) {
            ZOOM_TO_1 = 5;
            onMapReady(mMap);

        }


//        else if (id == R.id.nav_manage) {
//            Toast.makeText(getApplicationContext(), "Tools Clicked", Toast.LENGTH_LONG).show();
//        } else if (id == R.id.nav_share) {
//            Toast.makeText(getApplicationContext(), "Share Clicked", Toast.LENGTH_LONG).show();
//        } else if (id == R.id.about) {
//            Toast.makeText(getApplicationContext(), "About Clicked", Toast.LENGTH_LONG).show();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        final boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));

        mMap = googleMap;
        IconGenerator iconFactory = new IconGenerator(this);
        iconFactory.setColor(R.color.red_Color);
        iconFactory.setTextAppearance(R.style.iconGenText);



        mMap.clear();

        KmlLayer ethiopiaLayer = null;
        KmlLayer zone1Layer = null;
        KmlLayer zone2Layer = null;
        KmlLayer zone3Layer = null;
        KmlLayer zone4Layer = null;
        KmlLayer zone5Layer = null;
        try {
            ethiopiaLayer = new KmlLayer(mMap, R.raw.afarrigional, getApplicationContext());
            zone1Layer = new KmlLayer(mMap, R.raw.zone1, getApplicationContext());
            zone2Layer = new KmlLayer(mMap, R.raw.zone2, getApplicationContext());
            zone3Layer = new KmlLayer(mMap, R.raw.zone3, getApplicationContext());
            zone4Layer = new KmlLayer(mMap, R.raw.zone4, getApplicationContext());
            zone5Layer = new KmlLayer(mMap, R.raw.zone5, getApplicationContext());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        LatLng zone1 = new LatLng(11.776444, 41.275195);
        LatLng zone2 = new LatLng(13.747022, 40.296543);
        LatLng zone3 = new LatLng(9.827293, 40.682615);
        LatLng Zone4 = new LatLng(12.15, 40.18);
        LatLng zone5 = new LatLng(10.514944, 40.276481);



        if(ZOOM_TO_1 == 0) {

            try {
                ethiopiaLayer.addLayerToMap();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone1marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 1")))
                    .position(zone1).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone2marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 2")))
                    .position(zone2).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone3marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 3")))
                    .position(zone3).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone4marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 4")))
                    .position(Zone4).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone5marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 5")))
                    .position(zone5).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());



            mMap.addMarker(zone1marker).setTitle("Zone1");
            mMap.addMarker(zone2marker).setTitle("Zone2");
            mMap.addMarker(zone3marker).setTitle("Zone3");
            mMap.addMarker(zone4marker).setTitle("Zone4");
            mMap.addMarker(zone5marker).setTitle("Zone5");

        }else if (ZOOM_TO_1 == 1){
            try {
                 zone1Layer.addLayerToMap();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone1marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 1")))
                    .position(zone1).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            mMap.addMarker(zone1marker).setTitle("Zone1");

        }else if (ZOOM_TO_1 == 2){
            try {
                zone2Layer.addLayerToMap();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone2marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 2")))
                    .position(zone2).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            mMap.addMarker(zone2marker).setTitle("Zone2");


        }else if (ZOOM_TO_1 == 3){
            try {
                zone3Layer.addLayerToMap();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone3marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 3")))
                    .position(zone3).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            mMap.addMarker(zone3marker).setTitle("Zone3");


        }else if (ZOOM_TO_1 == 4){
            try {
                 zone4Layer.addLayerToMap();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MarkerOptions zone4marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 4")))
                    .position(Zone4).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            mMap.addMarker(zone4marker).setTitle("Zone4");


        }else if (ZOOM_TO_1 == 5){
            try {
                 zone5Layer.addLayerToMap();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone5marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 5")))
                    .position(zone5).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            mMap.addMarker(zone5marker).setTitle("Zone5");


        }







        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.815915, 40.824674), 6.0f));
        if(ZONE_NUM == 0) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        }else if (ZONE_NUM == 1){
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(7, 35), new LatLng(12, 39)));
        }else if (ZONE_NUM == 2){
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        }else if (ZONE_NUM == 3){
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        }else if (ZONE_NUM == 4){
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        }else if (ZONE_NUM == 5){
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        }





        if (ZOOM_TO_1 == 0) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.815915, 40.824674), 6.0f));
        } else if (ZOOM_TO_1 == 1) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(11.45, 40.40), new LatLng(12.19, 41.52)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.776444, 41.275195), 8.0f));

        } else if (ZOOM_TO_1 == 2) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(12.84, 39.91), new LatLng(15.22, 42.0)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.85, 40.40), 8.0f));

        } else if (ZOOM_TO_1 == 3) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(9.09, 39.76), new LatLng(10.74, 41.04)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(9.95, 40.81), 8.5f));

        } else if (ZOOM_TO_1 == 4) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(12.10, 39.87), new LatLng(12.70, 40.58)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(12.50, 40.19), 8.5f));

        } else if (ZOOM_TO_1 == 5) {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(9.72, 39.9), new LatLng(11.57, 40.76)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.37, 40.27), 9.0f));

        } else {
            mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(11.55, 40.32), new LatLng(12.00, 41.00)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.776444, 41.275195), 8.0f));
        }


//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.815915, 40.824674), 6.0f));


        mMap.setMinZoomPreference(MIN_ZOOM);
        mMap.setMaxZoomPreference(MAX_ZOOM);


        if (maptype == 1) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }

//        final Intent i = new Intent(this, Zone1_data.class);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                Debug.Log("CLICKED "+marker.getTitle());
                Intent i = getZoneIntent(marker.getTitle());
                startActivity(i);
                return false;
            }

        });


    }

    public Intent getZoneIntent(String name) {

        Intent i = new Intent();

        if (name.equals("Zone1")) {
            i = new Intent(this, Zone1_data.class);
        } else if (name.equals("Zone2")) {
            i = new Intent(this, Zone2_data.class);
        } else if (name.equals("Zone3")) {
            i = new Intent(this, Zone3_data.class);
        } else if (name.equals("Zone4")) {
            i = new Intent(this, Zone4_data.class);
        } else if (name.equals("Zone5")) {
            i = new Intent(this, Zone5_data.class);
        }else if (name.equals("AfarInfo")) {
            i = new Intent(this, AfarInfo.class);
        }
        return i ;
    }


}
