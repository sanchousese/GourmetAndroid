package biz.aejis.gourmet.app;

import android.app.Application;
import android.util.Log;
import biz.aejis.gourmet.app.models.Response;

/**
 * Created by Sutula on 28.06.15.
 */
public class GourmetApplication extends Application {

    private static final String TAG = "GourmetApplication";

    private Response latestResponse;

    private static GourmetApplication instance;

    public static GourmetApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public Response getLatestResponse() {
        return latestResponse;
    }

    public void setLatestResponse(Response latestResponse) {
        this.latestResponse = latestResponse;
        Log.d(TAG, "setLatestResponse: " + latestResponse);
    }
}
