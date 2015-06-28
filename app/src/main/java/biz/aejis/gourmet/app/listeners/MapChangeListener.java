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

    private static final String TAG = "MapChangeListener";

    private MapHelper mapHelper;

    private static final long TIME_TO_WAIT = 2000L;

    private long lastRequestTime;

    public MapChangeListener(MapHelper mapHelper) {
        this.mapHelper = mapHelper;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

        if (System.currentTimeMillis() - lastRequestTime <= TIME_TO_WAIT) {
            return;
        }

        lastRequestTime = System.currentTimeMillis();

        Log.d(TAG, "Need to update info!");
        mapHelper.updateInfo();
    }


}
