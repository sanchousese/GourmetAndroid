package biz.aejis.gourmet.app.activities;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.adapters.MainViewPagerAdapter;
import biz.aejis.gourmet.app.presenters.MainPresenter;
import biz.aejis.gourmet.app.views.MainView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends ActionBarActivity implements MaterialTabListener, MainView,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

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

        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                title.setText(adapter.getPageTitle(position));
            }

        });

        insertAllTabsFromPagerToTabHost();

        mainPresenter = new MainPresenter(this);

        initializeGoogleApi();
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
    public void showAlert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    public void onConnectionFailed(ConnectionResult connectionResult) {
        showAlert("Проблемы с подключнием GoogleClientAPI");
    }
}