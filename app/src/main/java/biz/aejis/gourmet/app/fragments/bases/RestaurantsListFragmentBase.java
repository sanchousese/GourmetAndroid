package biz.aejis.gourmet.app.fragments.bases;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.creators.ListSwipeMenuCreator;
import biz.aejis.gourmet.app.listeners.ListSwipeOnMenuItemClickListener;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuListView;


/**
 * Created by Sutula on 15.07.15.
 */
public class RestaurantsListFragmentBase extends Fragment{

    @InjectView(R.id.restaurantList)
    protected SwipeMenuListView restaurantList;

    protected BaseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants_list, container, false);
        ButterKnife.inject(this, view);

        initSwipeMenuCreator();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initOnItemClickListener(adapter);
    }

    private void initSwipeMenuCreator() {
        restaurantList.setMenuCreator(new ListSwipeMenuCreator(getActivity()));
    }

    protected void initOnItemClickListener(BaseAdapter adapter) {
        restaurantList.setOnMenuItemClickListener(
                new ListSwipeOnMenuItemClickListener(getActivity(), adapter));
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
    }

}
