package biz.aejis.gourmet.app.fragments;

import android.util.Log;
import biz.aejis.gourmet.app.adapters.lists.RestaurantsListAdapter;
import biz.aejis.gourmet.app.adapters.lists.RestaurantsListAdapter.DataSource;
import biz.aejis.gourmet.app.fragments.bases.RestaurantsListFragmentBase;
import biz.aejis.gourmet.app.managers.DatabaseManager;

public class RestaurantsShortlistFragment extends RestaurantsListFragmentBase {
    private static final String TAG = "RestaurantsListFragment";

    @Override
    public void onStart() {
        adapter = new RestaurantsListAdapter(
                getActivity(),
                DatabaseManager.getInstance().getAllRestaurants(),
                DataSource.DATABASE
        );

        restaurantList.setAdapter(adapter);
        super.onStart();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(menuVisible){
            adapter.notifyDataSetChanged();
        }
    }
}
