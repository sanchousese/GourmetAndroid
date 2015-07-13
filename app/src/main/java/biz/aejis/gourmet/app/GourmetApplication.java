package biz.aejis.gourmet.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import biz.aejis.gourmet.app.activities.RestaurantInfoActivity;
import biz.aejis.gourmet.app.models.Response;
import biz.aejis.gourmet.app.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sutula on 28.06.15.
 */
public class GourmetApplication extends Application {

    private static final String TAG = "GourmetApplication";

    private static GourmetApplication instance;

    private Response latestResponse = new Response();

    public static final int RESTAURANT_INFO_REQUEST_CODE = 111;

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

    public static int findRestaurantIdByName(String name) {
        return getInstance()
                .getLatestResponse()
                .getRestaurantByName(name)
                .getId();
    }
}
