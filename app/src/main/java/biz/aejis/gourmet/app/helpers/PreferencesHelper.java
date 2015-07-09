package biz.aejis.gourmet.app.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;

/**
 * Created by Sutula on 08.07.15.
 */
public class PreferencesHelper {

    private static final Context context = GourmetApplication.getInstance();

    public static void writeRestaurantIdToSharedPreferences(int id) {

        SharedPreferences sharedPreferences = context
                .getSharedPreferences(context.getString(R.string.preferences), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(R.string.saved_restaurant_id), id);
        editor.apply();
    }
}
