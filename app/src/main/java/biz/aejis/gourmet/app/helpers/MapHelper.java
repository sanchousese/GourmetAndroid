package biz.aejis.gourmet.app.helpers;

import android.util.Log;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.listeners.MapChangeListener;
import biz.aejis.gourmet.app.models.Restaurant;
import biz.aejis.gourmet.app.presenters.Updater;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sutula on 27.06.15.
 */
public class MapHelper {

    public static final String TAG = "MapHelper";

    private GoogleMap map;

    private List<Marker> markers = new ArrayList<Marker>();

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

    private void initializeMap(GoogleMap map) {
        map.setMyLocationEnabled(true);
        map.setOnCameraChangeListener(new MapChangeListener(this));
        Log.d(TAG, "Map has been initialized");
    }

    public void setMarker(double latitude, double longitude, String title) {
        LatLng position = new LatLng(latitude, longitude);
        Marker marker = map.addMarker(new MarkerOptions().position(position).title(title));
        markers.add(marker);
        Log.d(TAG, "Marker on position: latitude: " + latitude + " longitude: " + longitude + " title: " + title);
    }

    public void clearMap() {
        map.clear();
        markers = new ArrayList<Marker>();
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
}
