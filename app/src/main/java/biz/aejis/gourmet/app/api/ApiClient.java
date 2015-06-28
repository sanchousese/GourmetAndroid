package biz.aejis.gourmet.app.api;

import biz.aejis.gourmet.app.models.Response;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Sutula on 28.06.15.
 */
public class ApiClient {

    private static GourmetApiInterface gourmetService;

    public static GourmetApiInterface getGourmetApiClient() {
        if (gourmetService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://gourmet.aejis.biz")
                    .build();
            gourmetService = restAdapter.create(GourmetApiInterface.class);
        }
        return gourmetService;
    }

    public interface GourmetApiInterface {
        @GET("/api/v2/restaurants")
        void getRestaurantsInSquare(@Query("lat0") double northwestLatitude, @Query("long0") double northwestLongitude,
                                    @Query("lat1") double southeastLatitude, @Query("long1") double southeastLongitude,
                                    Callback<Response> callback);
    }
}
