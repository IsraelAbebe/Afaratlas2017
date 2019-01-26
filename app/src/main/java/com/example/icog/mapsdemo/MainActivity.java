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
import com.gmail.se.israel.abebe.aboutus;
import com.gmail.se.israel.abebe.ourService;
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
            Intent i = getZoneIntent("ourService");
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            Toast.makeText(getApplicationContext(), "Recent Works Clicked", Toast.LENGTH_LONG).show();
//        } else
        if (id == R.id.nav_gallery) {
            Intent i = new Intent(this, galleryShow.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this, Contact.class);
            startActivity(i);
        }
        else if (id == R.id.nav_manage) {
            Intent i = getZoneIntent("aboutus");
            startActivity(i);
        }
        else if (id == R.id.AboutAfar) {
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



        mMap.clear();

        KmlLayer ethiopiaLayer = null;
        KmlLayer zone1Layer = null;
        KmlLayer zone2Layer = null;
        KmlLayer zone3Layer = null;
        KmlLayer zone4Layer = null;
        KmlLayer zone5Layer = null;

//        KmlLayer zone1Layer_kml = null;
//        KmlLayer zone2Layer_kml = null;
//        KmlLayer zone3Layer_kml = null;
//        KmlLayer zone4Layer_kml = null;
//        KmlLayer zone5Layer_kml = null;
        try {
            ethiopiaLayer = new KmlLayer(mMap, R.raw.afarrigional, getApplicationContext());
            zone1Layer = new KmlLayer(mMap, R.raw.zone1, getApplicationContext());
            zone2Layer = new KmlLayer(mMap, R.raw.zone2, getApplicationContext());
            zone3Layer = new KmlLayer(mMap, R.raw.zone3, getApplicationContext());
            zone4Layer = new KmlLayer(mMap, R.raw.zone4, getApplicationContext());
            zone5Layer = new KmlLayer(mMap, R.raw.zone5, getApplicationContext());
//
//            zone1Layer_kml = new KmlLayer(mMap, R.raw.zone1_label, getApplicationContext());
//            zone2Layer_kml = new KmlLayer(mMap, R.raw.zone2_label, getApplicationContext());
//            zone3Layer_kml = new KmlLayer(mMap, R.raw.zone3_label, getApplicationContext());
//            zone4Layer_kml = new KmlLayer(mMap, R.raw.zone4_label, getApplicationContext());
//            zone5Layer_kml = new KmlLayer(mMap, R.raw.zone5_label, getApplicationContext());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



//         Zones
        LatLng zone1 = new LatLng(11.776444, 41.275195);
        LatLng zone2 = new LatLng(13.747022, 40.296543);
        LatLng zone3 = new LatLng(9.827293, 40.682615);
        LatLng Zone4 = new LatLng(12.15, 40.18);
        LatLng zone5 = new LatLng(10.514944, 40.276481);


//        Zone 1

        LatLng zone_1_dubti = new LatLng(11.9,41.1);
        LatLng zone_1_millei = new LatLng(11.3,41.0);
        LatLng zone_1_kory = new LatLng(12.4,41.0);
        LatLng zone_1_adeare = new LatLng(11.3,40.4);
        LatLng zone_1_afambo = new LatLng(11.3,41.7);
        LatLng zone_1_assayta = new LatLng(11.6,41.5);
        LatLng zone_1_chifra = new LatLng(11.6,40.2);
        LatLng zone_1_elideare= new LatLng(12.4,41.8);

//        Zone 2


        LatLng zone_2_Berhale = new LatLng(13.953583447,40.167210717);
        LatLng zone_2_Megale = new LatLng(12.827951567,39.936991909);
        LatLng zone_2_Erebti = new LatLng(13.140237836,40.165564474);
        LatLng zone_2_Abala = new LatLng(13.364696696,39.861514037);
        LatLng zone_2_Afdera = new LatLng(13.560927737,40.726855615);
        LatLng zone_2_Bidu = new LatLng(13.024047812,41.342734546);
        LatLng zone_2_Dalol = new LatLng(14.306313475,40.042640566);
        LatLng zone_2_Koneba = new LatLng(14.003588149,39.873022024);


// Zone 3


        LatLng zone_3_Argoba = new LatLng(9.48943508100,39.86696271700);
        LatLng zone_3_Amibara = new LatLng(9.46028567000,40.43227214600);
        LatLng zone_3_AwashFentale = new LatLng(9.08785099500,40.02086441300);
        LatLng zone_3_Gelaalo = new LatLng(10.12416629100,40.46829517700);
        LatLng zone_3_Dulecha = new LatLng(9.48474269800,40.06019051600);
        LatLng zone_3_Gewane = new LatLng(10.42121006100,40.76739858300);



//        Zone 4

        LatLng zone_4_Golina = new LatLng(12.18453484300,39.98186122800);
        LatLng zone_4_Awura = new LatLng(12.04673689800,40.30947601300);
        LatLng zone_4_Ewa = new LatLng(11.83418774300,40.16116388300);
        LatLng zone_4_Teru = new LatLng(12.57191242400,40.45152626700);
        LatLng zone_4_Yalo = new LatLng(12.42608372600,39.93334852100);


//        Zone 5

        LatLng zone_5_Dalifage = new LatLng(10.47341332900,40.46211204300);
        LatLng zone_5_Dewe = new LatLng(10.73011141200,40.27697459700);
        LatLng zone_5_Hadeleela = new LatLng(10.25705166800,40.24892822900);
        LatLng zone_5_Semiroby = new LatLng(9.87016918900,40.14598393900);
        LatLng zone_5_Telalak = new LatLng(10.97205401700,40.29248595500);

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
//                zone1Layer_kml.addLayerToMap();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone_1_adearemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Ade`are")))
                    .position(zone_1_adeare).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_afambomarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Afambo")))
                    .position(zone_1_afambo).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_assaytamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Assayta")))
                    .position(zone_1_assayta).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_chiframarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Chifra")))
                    .position(zone_1_chifra).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_dubtimarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Dubti")))
                    .position(zone_1_dubti).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_elidearemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Elide`are")))
                    .position(zone_1_elideare).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_korymarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Kory")))
                    .position(zone_1_kory).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_1_milleimarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Millei")))
                    .position(zone_1_millei).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());




            mMap.addMarker(zone_1_dubtimarker).setTitle("Dubti");
            mMap.addMarker(zone_1_milleimarker).setTitle("Millei");
            mMap.addMarker(zone_1_korymarker).setTitle("Kory");
            mMap.addMarker(zone_1_adearemarker).setTitle("Ade`are");
            mMap.addMarker(zone_1_afambomarker).setTitle("Afambo");
            mMap.addMarker(zone_1_assaytamarker).setTitle("Assayta");
            mMap.addMarker(zone_1_chiframarker).setTitle("Chifra");
            mMap.addMarker(zone_1_elidearemarker).setTitle("Elide`are");


//            MarkerOptions zone1marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 1")))
//                    .position(zone1).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
//            mMap.addMarker(zone1marker).setTitle("Zone1");

        }else if (ZOOM_TO_1 == 2){
            try {
                zone2Layer.addLayerToMap();
//                zone2Layer_kml.addLayerToMap();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone_2_Berhalemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Berhale")))
                    .position(zone_2_Berhale).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Megalemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Megale")))
                    .position(zone_2_Megale).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Erebtimarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Erebti")))
                    .position(zone_2_Erebti).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Abalamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Abala")))
                    .position(zone_2_Abala).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Afderamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Afdera")))
                    .position(zone_2_Afdera).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Bidumarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Bidu")))
                    .position(zone_2_Bidu).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Dalolmarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Dalol")))
                    .position(zone_2_Dalol).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_2_Konebamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Koneba")))
                    .position(zone_2_Koneba).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());




            mMap.addMarker(zone_2_Berhalemarker).setTitle("Berhale");
            mMap.addMarker(zone_2_Megalemarker).setTitle("Megale");
            mMap.addMarker(zone_2_Erebtimarker).setTitle("Erebti");
            mMap.addMarker(zone_2_Abalamarker).setTitle("Abala");
            mMap.addMarker(zone_2_Afderamarker).setTitle("Afdera");
            mMap.addMarker(zone_2_Bidumarker).setTitle("Bidu");
            mMap.addMarker(zone_2_Dalolmarker).setTitle("Dalol");
            mMap.addMarker(zone_2_Konebamarker).setTitle("Koneba");




//            MarkerOptions zone2marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 2")))
//                    .position(zone2).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
//            mMap.addMarker(zone2marker).setTitle("Zone2");


        }else if (ZOOM_TO_1 == 3){
            try {
                zone3Layer.addLayerToMap();
//                zone3Layer_kml.addLayerToMap();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            MarkerOptions zone_3_Argobamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Argoba")))
                    .position(zone_3_Argoba).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_3_Amibaramarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Amibara")))
                    .position(zone_3_Amibara).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_3_AwashFentalemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Awash Fentale")))
                    .position(zone_3_AwashFentale).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_3_Gelaalomarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Gelaalo")))
                    .position(zone_3_Gelaalo).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_3_Dulechamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Dulecha")))
                    .position(zone_3_Dulecha).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_3_Gewanemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Gewane")))
                    .position(zone_3_Gewane).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());




            mMap.addMarker(zone_3_Argobamarker).setTitle("Berhale");
            mMap.addMarker(zone_3_Amibaramarker).setTitle("Amibara");
            mMap.addMarker(zone_3_AwashFentalemarker).setTitle("Awash Fentale");
            mMap.addMarker(zone_3_Gelaalomarker).setTitle("Gelaalo");
            mMap.addMarker(zone_3_Dulechamarker).setTitle("Dulecha");
            mMap.addMarker(zone_3_Gewanemarker).setTitle("Gewane");


//            MarkerOptions zone3marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 3")))
//                    .position(zone3).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
//            mMap.addMarker(zone3marker).setTitle("Zone3");


        }else if (ZOOM_TO_1 == 4){
            try {
                 zone4Layer.addLayerToMap();
//                zone3Layer_kml.addLayerToMap();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            MarkerOptions zone_4_Golinamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Golina")))
                    .position(zone_4_Golina).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_4_Awuramarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Awura")))
                    .position(zone_4_Awura).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_4_Ewamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Ewa")))
                    .position(zone_4_Ewa).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_4_Terumarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Teru")))
                    .position(zone_4_Teru).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_4_Yalomarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Yalo")))
                    .position(zone_4_Yalo).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());





            mMap.addMarker(zone_4_Golinamarker).setTitle("Golina");
            mMap.addMarker(zone_4_Awuramarker).setTitle("Awura");
            mMap.addMarker(zone_4_Ewamarker).setTitle("Ewa");
            mMap.addMarker(zone_4_Terumarker).setTitle("Teru");
            mMap.addMarker(zone_4_Yalomarker).setTitle("Yalo");

//            MarkerOptions zone4marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 4")))
//                    .position(Zone4).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
//            mMap.addMarker(zone4marker).setTitle("Zone4");


        }else if (ZOOM_TO_1 == 5){
            try {
                 zone5Layer.addLayerToMap();
//                zone5Layer_kml.addLayerToMap();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MarkerOptions zone_5_Dalifagemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Dalifage")))
                    .position(zone_5_Dalifage).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_5_Dewemarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Dewe")))
                    .position(zone_5_Dewe).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_5_Hadeleelamarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Hadele`ela")))
                    .position(zone_5_Hadeleela).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_5_Semirobymarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Teru")))
                    .position(zone_5_Semiroby).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
            MarkerOptions zone_5_Telalakmarker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Yalo")))
                    .position(zone_5_Telalak).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());





            mMap.addMarker(zone_5_Dalifagemarker).setTitle("Dalifage");
            mMap.addMarker(zone_5_Dewemarker).setTitle("Dewe");
            mMap.addMarker(zone_5_Hadeleelamarker).setTitle("Hadele`ela");
            mMap.addMarker(zone_5_Semirobymarker).setTitle("Semiroby");
            mMap.addMarker(zone_5_Telalakmarker).setTitle("Telalak");

//            MarkerOptions zone5marker = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("Zone 5")))
//                    .position(zone5).anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
//            mMap.addMarker(zone5marker).setTitle("Zone5");


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




        mMap.setMinZoomPreference(MIN_ZOOM);
        mMap.setMaxZoomPreference(MAX_ZOOM);


        if (maptype == 1) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("Zone1") || marker.getTitle().equals("Zone2") || marker.getTitle().equals("Zone3") || marker.getTitle().equals("Zone4") || marker.getTitle().equals("Zone5")) {
                    Intent i = getZoneIntent(marker.getTitle());
                    startActivity(i);
                }
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
        }else if (name.equals("ourService")) {
            i = new Intent(this, ourService.class);
        }else if (name.equals("aboutus")) {
            i = new Intent(this, aboutus.class);
        }
        return i ;
    }


}
