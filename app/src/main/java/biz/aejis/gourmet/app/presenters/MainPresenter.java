package biz.aejis.gourmet.app.presenters;

import android.location.Location;
import android.util.Log;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.helpers.MapHelper;
import biz.aejis.gourmet.app.managers.DatabaseManager;
import biz.aejis.gourmet.app.models.Atmosfere;
import biz.aejis.gourmet.app.models.Atmosferes;
import biz.aejis.gourmet.app.models.Response;
import biz.aejis.gourmet.app.views.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import retrofit.Callback;
import retrofit.RetrofitError;

import java.util.Date;
import java.util.List;

/**
 * Created by Sutula on 28.06.15.
 */
public class MainPresenter implements Updater {

    private static final String TAG = "MainPresenter";

    private MapView mapView;

    private MapHelper mapHelper;

    private Date dateOfLastUpdate = new Date();

    public MainPresenter(MapView mapView) {
        this.mapView = mapView;

        ApiClient.getGourmetApiClient().getAtmosferes(new Callback<Atmosferes>() {
            @Override
            public void success(Atmosferes atmosferes, retrofit.client.Response response) {
                DatabaseManager.getInstance().addAtmosferes(atmosferes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getUrl() + " " + error.getBody() + " " + error.getResponse());
            }
        });
    }

    public void setUpMap(GoogleMap map) {
        mapHelper = MapHelper.getInstance();
        mapHelper.setUpMap(map, this);
    }

    @Override
    public void updateInfo() {

        mapView.setProgressBarVisible();

        LatLng northwest = mapHelper.getNorthwestPoint();
        LatLng southeast = mapHelper.getSoutheastPoint();

        final Date dateOfCurrentUpdate = new Date();

        ApiClient.getGourmetApiClient().getRestaurantsInSquare(northwest.latitude, northwest.longitude,
                southeast.latitude, southeast.longitude, "ru", new Callback<Response>() {
                    @Override
                    public void success(Response response, retrofit.client.Response response2) {
                        Log.d(TAG, "ApiClient success, data: " + response);
                        if (dateOfCurrentUpdate.after(dateOfLastUpdate)){
                            dateOfLastUpdate = dateOfCurrentUpdate;
                            GourmetApplication.getInstance().setLatestResponse(response);
                            updateViews();
                            mapView.setProgressBarGone();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mapView.showAlert("Network troubles!");
                        mapView.setProgressBarGone();
                    }
                });

    }

    @Override
    public void startRestaurantInfoActivity() {
        mapView.startRestaurantInfoActivity();
    }

    public void setPositionOn(Location location) {
        mapHelper.setPositionOn(location);
    }

    private void updateViews() {
        mapHelper.redraw();
        mapView.updateList();
    }
}
