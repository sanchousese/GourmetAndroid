package biz.aejis.gourmet.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.fragments.MapPageFragment;
import biz.aejis.gourmet.app.fragments.RestaurantsListPageFragment;
import biz.aejis.gourmet.app.fragments.RestaurantsShortlistFragment;
import biz.aejis.gourmet.app.interfaces.ViewPageTransmitter;
import biz.aejis.gourmet.app.views.MapView;

/**
 * Created by Sutula on 30.06.15.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter implements ViewPageTransmitter {

    private MapPageFragment mapPageFragment;

    private RestaurantsListPageFragment restaurantsListPageFragment;

    private RestaurantsShortlistFragment restaurantsShortlistFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mapPageFragment = new MapPageFragment();
                return mapPageFragment;
            case 1:
                restaurantsListPageFragment = new RestaurantsListPageFragment();
                return restaurantsListPageFragment;
            case 2:
                restaurantsShortlistFragment = new RestaurantsShortlistFragment();
                return restaurantsShortlistFragment;
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
        if (restaurantsListPageFragment != null) {
            restaurantsListPageFragment.updateList();
        }
    }

    @Override
    public void updateShortList() {
        restaurantsShortlistFragment.updateList();
    }

    @Override
    public void setProgressBarVisible() {
        if (mapPageFragment != null) {
            mapPageFragment.setProgressBarVisible();
        }
    }

    @Override
    public void setProgressBarGone() {
        if (mapPageFragment != null) {
            mapPageFragment.setProgressBarGone();
        }
    }
}
