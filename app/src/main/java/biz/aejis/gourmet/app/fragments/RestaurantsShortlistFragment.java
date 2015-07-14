package biz.aejis.gourmet.app.fragments;

import android.app.Activity;
import android.net.Uri;
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
import biz.aejis.gourmet.app.managers.DatabaseManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class RestaurantsShortlistFragment extends Fragment {
    private static final String TAG = "RestaurantsListFragment";

    @InjectView(R.id.restaurantList)
    ListView restaurantList;

    private BaseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseManager.init(getActivity());

        View view = inflater.inflate(R.layout.fragment_restaurants_list, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new RestaurantsListAdapter(
                getActivity(),
                DatabaseManager.getInstance().getAllRestaurants()
        );

        restaurantList.setAdapter(adapter);
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
    }
}
