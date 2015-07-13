package biz.aejis.gourmet.app.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.models.Photo;
import biz.aejis.gourmet.app.models.Restaurant;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Sutula on 10.07.15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "restaurantDB.sqlite";
    private static final int DATABASE_VERSION = 1;

    private Dao<Restaurant, Integer> restaurantDao = null;
    private Dao<Photo, Integer> photosDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Restaurant.class);
            TableUtils.createTable(connectionSource, Photo.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<Restaurant, Integer> getRestaurantDao() {
        if(restaurantDao == null) {
            try {
                restaurantDao = getDao(Restaurant.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return restaurantDao;
    }


    public Dao<Photo, Integer> getPhotosDao() {
        if (photosDao == null) {
            try {
                photosDao = getDao(Photo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return photosDao;
    }

}
