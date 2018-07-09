package sg.edu.rp.webservices.locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    GoogleMap map;
    Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = PermissionChecker.checkSelfPermission
                (MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PermissionChecker.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            // stops the action from proceeding further as permission not
            //  granted yet
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                btn1 = (Button) findViewById(R.id.button);
                btn2 = (Button) findViewById(R.id.button2);
                btn3 = (Button) findViewById(R.id.button3);


                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }
                final LatLng singapore= new LatLng(1.352083, 103.819836);

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        10));
                final LatLng poi_CausewayPoint = new LatLng(1.461379, 103.815527);
                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_CausewayPoint)
                        .title("North-HQ:")
                        .snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                final LatLng poi_RP = new LatLng(1.352083, 103.819836);
                Marker rp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_RP)
                        .title("Central:")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                final LatLng poi_df = new LatLng(1.332920, 103.799601);
                Marker df = map.addMarker(new
                        MarkerOptions()
                        .position(poi_df)
                        .title("East:")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);
                ui.setCompassEnabled(true);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
                                    15));

                        }
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_RP,
                                    15));
                        }
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (map != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_df,
                                    15));
                        }
                    }
                });
            }
        });




    }
}
