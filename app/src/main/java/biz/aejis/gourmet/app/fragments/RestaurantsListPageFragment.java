package biz.aejis.gourmet.app.fragments;

import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.adapters.lists.RestaurantsListAdapter;
import biz.aejis.gourmet.app.adapters.lists.RestaurantsListAdapter.DataSource;
import biz.aejis.gourmet.app.fragments.bases.RestaurantsListFragmentBase;

/**
 * Created by Sutula on 29.06.15.
 */
public class RestaurantsListPageFragment extends RestaurantsListFragmentBase {
    private static final String TAG = "RestaurantsListFragment";

    @Override
    public void onStart() {
        adapter = new RestaurantsListAdapter(
                getActivity(),
                GourmetApplication
                        .getInstance()
                        .getLatestResponse()
                        .getRestaurants(),
                DataSource.WEB
        );
        restaurantList.setAdapter(adapter);
        super.onStart();
    }
}
