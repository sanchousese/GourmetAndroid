package biz.aejis.gourmet.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.adapters.lists.RestaurantsListAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Sutula on 29.06.15.
 */
public class RestaurantsListPageFragment extends Fragment {

    private static final String TAG = "RestaurantsListFragment";

    @InjectView(R.id.restaurantList)
    ListView restaurantList;

    private BaseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants_list, container, false);
        ButterKnife.inject(this, view);

        adapter = new RestaurantsListAdapter(
                getActivity(),
                GourmetApplication
                        .getInstance()
                        .getLatestResponse()
                        .getRestaurants()
        );
        restaurantList.setAdapter(adapter);

        return view;
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
    }
}
