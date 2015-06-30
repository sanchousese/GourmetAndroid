package biz.aejis.gourmet.app.listeners;

import android.os.Handler;
import android.util.Log;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.helpers.MapHelper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;


/**
 * Created by Sutula on 27.06.15.
 */
public class MapChangeListener implements GoogleMap.OnCameraChangeListener {

    private static final String TAG = "MapChangeListener";

    private static final long DELAY_TIME = 1000;

    private MapHelper mapHelper;

    private final Runnable updater = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "Need to update info!");
            mapHelper.updateInfo();
        }
    };

    private final Handler timeoutHandler = new Handler();

    public MapChangeListener(MapHelper mapHelper) {
        this.mapHelper = mapHelper;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        timeoutHandler.removeCallbacks(updater);
        timeoutHandler.postDelayed(updater, DELAY_TIME);
    }


}
