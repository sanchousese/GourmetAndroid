package biz.aejis.gourmet.app.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.helpers.MapHelper;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    MapHelper mapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapHelper = MapHelper.getInstance();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapHelper.setUpMap(map);
    }

    @OnClick(R.id.button)
    public void click(){
        mapHelper.setMarker(-34, 151, "Marker in Sydney");
    }
}