package biz.aejis.gourmet.app.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.activities.RestaurantInfoActivity;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.listeners.MapChangeListener;
import biz.aejis.gourmet.app.models.Restaurant;
import biz.aejis.gourmet.app.presenters.Updater;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sutula on 27.06.15.
 */
public class MapHelper {

    public static final String TAG = "MapHelper";

    private GoogleMap map;

    private List<Marker> markers = new ArrayList<>();

    private LatLng northwestPoint, southeastPoint;

    private static MapHelper instance;

    private Updater updater;

    private MapHelper() {}

    public static MapHelper getInstance() {
        if(instance == null){
            instance = new MapHelper();
            return instance;
        }
        return instance;
    }

    public void setUpMap(GoogleMap map, Updater updater) {
        this.map = map;
        this.updater = updater;
        initializeMap(map);
    }

    private void initializeMap(final GoogleMap map) {
        map.setMyLocationEnabled(true);
        map.setOnCameraChangeListener(new MapChangeListener(this));
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow();
            return true;
        });
        map.setOnInfoWindowClickListener(marker -> {
            int restaurantId = GourmetApplication.findRestaurantIdByName(marker.getTitle());

            PreferencesHelper.writeRestaurantIdToSharedPreferences(restaurantId);

            updater.startRestaurantInfoActivity();
        });
        Log.d(TAG, "Map has been initialized");
    }

    public void setMarker(double latitude, double longitude, String title) {
        LatLng position = new LatLng(latitude, longitude);
        Marker marker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        );
        markers.add(marker);
        Log.d(TAG, "Marker on position: latitude: " + latitude + " longitude: " + longitude + " title: " + title);
    }

    public void clearMap() {
        map.clear();
        markers = new ArrayList<>();
        Log.d(TAG, "All markers have been deleted");
    }

    public void updateInfo() {
        updateBounds();

        updater.updateInfo();
    }

    private void updateBounds() {
        LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;
        northwestPoint = new LatLng(bounds.northeast.latitude, bounds.southwest.longitude);
        southeastPoint = new LatLng(bounds.southwest.latitude, bounds.northeast.longitude);
        Log.d(TAG, "Bounds was updated " + northwestPoint + southeastPoint);
    }

    public LatLng getNorthwestPoint() {
        return northwestPoint;
    }

    public LatLng getSoutheastPoint() {
        return southeastPoint;
    }

    public void redraw() {

        clearMap();

        List<Restaurant> restaurants = GourmetApplication.getInstance().getLatestResponse().getRestaurants();

        int restaurantsSize = restaurants.size();

        for (int i = 0; i < restaurantsSize; i++) {
            Restaurant restaurant = restaurants.get(i);
            setMarker(restaurant.getLatitude(), restaurant.getLongitude(), restaurant.getName());
        }
    }

    public void setPositionOn(Location location){
        if (location != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    }

    public static MarkerOptions generateMarkerFromRestaurant(Restaurant restaurant) {
        LatLng position = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        return new MarkerOptions()
                        .position(position)
                        .title(restaurant.getName())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
    }
}
