package biz.aejis.gourmet.app.managers;

import android.content.Context;
import android.util.Log;
import biz.aejis.gourmet.app.helpers.DatabaseHelper;
import biz.aejis.gourmet.app.models.Photo;
import biz.aejis.gourmet.app.models.Restaurant;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sutula on 10.07.15.
 */
public class DatabaseManager {

    private static final String TAG = "DatabaseManager";

    private static DatabaseManager instance;
    private final DatabaseHelper helper;

    private DatabaseManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = null;
        try {
            restaurants = helper.getRestaurantDao().queryForAll();
        } catch (SQLException e) {
            Log.d(TAG, e.getSQLState() + " " + e.getMessage());
        }
        return restaurants;
    }

    public void addToShortlist(Restaurant restaurant) {
        try {
            restaurant.hydrate();
            helper.getRestaurantDao().create(restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "SQLException");
        }
    }

    public void removeFromShortList(Restaurant restaurant) {
        try {
            helper.getRestaurantDao().delete(restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Restaurant getRestaurantById(int id) {
        try {
            return helper.getRestaurantDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addPhoto(Photo photo) {
        try {
            helper.getPhotosDao().create(photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRestaurantInDB(Restaurant restaurant) {
        try {
            return helper.getRestaurantDao().idExists(restaurant.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNameOfAtmosfere(int id) {
        try {
            return helper.getCategoryDao().queryForId(id).getName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
