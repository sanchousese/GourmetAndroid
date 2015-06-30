package biz.aejis.gourmet.app.activities;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.adapters.MainViewPagerAdapter;
import biz.aejis.gourmet.app.fragments.RestaurantsListPageFragment;
import biz.aejis.gourmet.app.interfaces.ViewPageTransmitter;
import biz.aejis.gourmet.app.presenters.MainPresenter;
import biz.aejis.gourmet.app.views.MainView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends ActionBarActivity implements MaterialTabListener, MainView {

    private MainPresenter mainPresenter;
    MaterialTabHost tabHost;
    ViewPager pager;
    MainViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.pager );

        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }

        });

        insertAllTabsFromPagerToTabHost();

        mainPresenter = new MainPresenter(this);
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
}