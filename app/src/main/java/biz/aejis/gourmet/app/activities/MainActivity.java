package biz.aejis.gourmet.app.activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.adapters.MainViewPagerAdapter;
import biz.aejis.gourmet.app.presenters.MainPresenter;
import biz.aejis.gourmet.app.views.MapView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends BaseActivity implements MaterialTabListener, MapView,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private MainPresenter mainPresenter;
    MaterialTabHost tabHost;
    ViewPager pager;
    MainViewPagerAdapter adapter;
    GoogleApiClient googleApiClient;

    @InjectView(R.id.title) TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);

        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                title.setText(adapter.getPageTitle(position));
            }

        });

        insertAllTabsFromPagerToTabHost();

        initializeGoogleApi();

        mainPresenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initializeGoogleApi() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    private void insertAllTabsFromPagerToTabHost() {
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }
    }

    @Override
    public void setUpMap(GoogleMap map) {
        mainPresenter.setUpMap(map);
    }

    @Override
    public void updateList() {
        adapter.updateList();
    }

    @Override
    public void setProgressBarVisible() {
        adapter.setProgressBarVisible();
    }

    @Override
    public void setProgressBarGone() {
        adapter.setProgressBarGone();
    }

    @Override
    public void startRestaurantInfoActivity() {
        startActivityForResult(
                new Intent(this, RestaurantInfoActivity.class),
                GourmetApplication.RESTAURANT_INFO_REQUEST_CODE
        );
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        mainPresenter.setPositionOn(location);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GourmetApplication.RESTAURANT_INFO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("RESPONSE", "OKEY");
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        showAlert("Проблемы с подключнием к GoogleClientAPI");
    }
}