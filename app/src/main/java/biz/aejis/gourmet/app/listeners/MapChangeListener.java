package biz.aejis.gourmet.app.listeners;

import android.util.Log;
import biz.aejis.gourmet.app.helpers.MapHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by Sutula on 27.06.15.
 */
public class MapChangeListener implements GoogleMap.OnCameraChangeListener {

    public static final String TAG = "MapChangeListener";

    private MapHelper mapHelper;

    public MapChangeListener(MapHelper mapHelper) {
        this.mapHelper = mapHelper;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        mapHelper.updateInfo();
    }


}
