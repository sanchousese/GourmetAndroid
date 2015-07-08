package biz.aejis.gourmet.app.listeners;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.activities.RestaurantInfoActivity;
import biz.aejis.gourmet.app.models.Restaurant;

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
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(context.getString(R.string.preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(R.string.saved_restaurant_id), currRestaurant.getId());
        editor.apply();

        context.startActivity(new Intent(context, RestaurantInfoActivity.class));
    }
}