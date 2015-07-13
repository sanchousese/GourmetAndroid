package biz.aejis.gourmet.app.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.helpers.MapHelper;
import biz.aejis.gourmet.app.models.Restaurant;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantMapActivity extends BaseActivity implements OnMapReadyCallback {

    private final static int ERROR_CODE = -1;
    private final static int ZOOM_LEVEL = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_restaurant_map);
        ButterKnife.inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @OnClick(R.id.rlTitle)
    public void returnToPrevActivity() {
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        Restaurant restaurant = getCurrentRestaurant();
        googleMap.setMyLocationEnabled(true);
        googleMap.addMarker(MapHelper.generateMarkerFromRestaurant(restaurant));
        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(restaurant.getLatitude(), restaurant.getLongitude()),
                        ZOOM_LEVEL
                )
        );
    }

    private Restaurant getCurrentRestaurant() {
        int restaurantId = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
                .getInt(getString(R.string.saved_restaurant_id), ERROR_CODE);

        if (restaurantId == ERROR_CODE) {
            processException();
            return null;
        }

        return ((GourmetApplication) getApplication())
                .getLatestResponse()
                .getRestaurantById(restaurantId);
    }

    private void processException() {
        showAlert(getString(R.string.error_message));
        finish();
    }
}
