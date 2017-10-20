package com.example.icog.mapsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.ui.IconGenerator;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    public static float MAX_ZOOM = 20;
    public static float MIN_ZOOM = 7;

    private int maptype = 1;
    private int showZones = 0;

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
            Toast.makeText(getApplicationContext(), "Gallery Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(getApplicationContext(), "Contact us Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(getApplicationContext(), "Tools Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(), "Share Clicked", Toast.LENGTH_LONG).show();
        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "About Clicked", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));

        mMap = googleMap;
        IconGenerator iconFactory = new IconGenerator(this);
        iconFactory.setColor(R.color.red_Color);
        iconFactory.setTextAppearance(R.style.iconGenText);

        try {
//            KmlLayer ethiopiaLayer = new KmlLayer(mMap,R.raw.ethiopia,getApplicationContext());
            KmlLayer ZoneLayer = new KmlLayer(mMap,R.raw.zones,getApplicationContext());
            KmlLayer woredaLayer = new KmlLayer(mMap,R.raw.woreda,getApplicationContext());
            KmlLayer regionLayer = new KmlLayer(mMap,R.raw.region,getApplicationContext());
            KmlLayer zoneAnnotationLayer = new KmlLayer(mMap,R.raw.annotation,getApplicationContext());


//            ethiopiaLayer.addLayerToMap();
            regionLayer.addLayerToMap();
            ZoneLayer.addLayerToMap();
            zoneAnnotationLayer.addLayerToMap();
//            woredaLayer.addLayerToMap();

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

        mMap.addMarker(zone1marker).setTitle("Zone 1");
        mMap.addMarker(zone2marker).setTitle("Zone 2");
        mMap.addMarker(zone3marker).setTitle("Zone 3");
        mMap.addMarker(zone4marker).setTitle("Zone 4");
        mMap.addMarker(zone5marker).setTitle("Zone 5");

        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(8.50, 38.90), new LatLng(15.50, 42)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(11.815915, 40.824674), 6.0f));

        mMap.setMinZoomPreference(MIN_ZOOM);
        mMap.setMaxZoomPreference(MAX_ZOOM);


        if (maptype == 1) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }

        final Intent i = new Intent(this, DataViewActivity.class);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "YOU CLICKED ON " + marker.getTitle(), Toast.LENGTH_LONG).show();
                i.putExtra("Title", marker.getTitle());
                startActivity(i);
                return false;
            }

        });
    }
}
