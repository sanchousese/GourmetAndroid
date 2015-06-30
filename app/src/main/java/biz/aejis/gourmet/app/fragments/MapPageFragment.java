package biz.aejis.gourmet.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.views.MainView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.gms.maps.*;

/**
 * Created by Sutula on 30.06.15.
 */
public class MapPageFragment extends Fragment {

    @InjectView(R.id.map) MapView mapView;
    @InjectView(R.id.progressBar) ProgressBar progressBar;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        ButterKnife.inject(this, view);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        MapsInitializer.initialize(getActivity());

        mainView.setUpMap(mapView.getMap());

        return view;

    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setProgressBarGone() {
        progressBar.setVisibility(View.GONE);
    }
}
