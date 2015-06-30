package biz.aejis.gourmet.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.fragments.MapPageFragment;
import biz.aejis.gourmet.app.fragments.RestaurantsListPageFragment;
import biz.aejis.gourmet.app.interfaces.ViewPageTransmitter;
import biz.aejis.gourmet.app.views.MainView;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Sutula on 30.06.15.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter implements ViewPageTransmitter {

    private MainView mainView;

    private MapPageFragment mapPageFragment;

    private RestaurantsListPageFragment restaurantsListPageFragment;

    public MainViewPagerAdapter(FragmentManager fm, MainView mainView) {
        super(fm);
        this.mainView = mainView;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mapPageFragment = new MapPageFragment();
                mapPageFragment.setMainView(mainView);
                return mapPageFragment;

            case 1:
                restaurantsListPageFragment = new RestaurantsListPageFragment();
                restaurantsListPageFragment.setMainView(mainView);
                return restaurantsListPageFragment;
            case 2:
                return new RestaurantsListPageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return GourmetApplication
                .getInstance()
                .getResources()
                .getStringArray(R.array.page_titles)[position];
    }

    @Override
    public void updateList() {
        restaurantsListPageFragment.updateList();
    }

    @Override
    public void setProgressBarVisible() {
        mapPageFragment.setProgressBarVisible();
    }

    @Override
    public void setProgressBarGone() {
        mapPageFragment.setProgressBarGone();
    }
}
