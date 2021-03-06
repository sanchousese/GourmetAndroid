package biz.aejis.gourmet.app.presenters;

import android.location.Location;
import android.util.Log;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.activities.MainActivity;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.helpers.MapHelper;
import biz.aejis.gourmet.app.models.Response;
import biz.aejis.gourmet.app.views.MainView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import retrofit.Callback;
import retrofit.RetrofitError;

import java.util.Date;

/**
 * Created by Sutula on 28.06.15.
 */
public class MainPresenter implements Updater {

    private static final String TAG = "MainPresenter";

    private MainView mainView;

    private MapHelper mapHelper;

    private Date dateOfLastUpdate = new Date();

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void setUpMap(GoogleMap map) {
        mapHelper = MapHelper.getInstance();
        mapHelper.setUpMap(map, this);
    }

    @Override
    public void updateInfo() {

        mainView.setProgressBarVisible();

        LatLng northwest = mapHelper.getNorthwestPoint();
        LatLng southeast = mapHelper.getSoutheastPoint();

        final Date dateOfCurrentUpdate = new Date();

        ApiClient.getGourmetApiClient().getRestaurantsInSquare(northwest.latitude, northwest.longitude,
                southeast.latitude, southeast.longitude, new Callback<Response>() {
                    @Override
                    public void success(Response response, retrofit.client.Response response2) {
                        Log.d(TAG, "ApiClient success, data: " + response);
                        if (dateOfCurrentUpdate.after(dateOfLastUpdate)){
                            dateOfLastUpdate = dateOfCurrentUpdate;
                            GourmetApplication.getInstance().setLatestResponse(response);
                            updateViews();
                            mainView.setProgressBarGone();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mainView.showAlert("Network troubles!");
                        mainView.setProgressBarGone();
                    }
                });

    }

    public void setPositionOn(Location location) {
        mapHelper.setPositionOn(location);
    }

    private void updateViews() {
        mapHelper.redraw();
        mainView.updateList();
    }
}
