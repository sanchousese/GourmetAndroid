package biz.aejis.gourmet.app.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import biz.aejis.gourmet.app.helpers.PreferencesHelper;
import biz.aejis.gourmet.app.models.Restaurant;
import biz.aejis.gourmet.app.views.MapView;

/**
 * Created by Sutula on 07.07.15.
 */
public class RestaurantItemClickListener implements View.OnClickListener {
    private static final String TAG = "RestaurantClickListener";

    private final Restaurant currRestaurant;

    private final Context context;

    public RestaurantItemClickListener(Restaurant currRestaurant, Context context) {
        this.currRestaurant = currRestaurant;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "click " + currRestaurant.getId());
        PreferencesHelper.writeRestaurantIdToSharedPreferences(currRestaurant.getId());
        ((MapView) context).startRestaurantInfoActivity();
    }
}