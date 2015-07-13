package biz.aejis.gourmet.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.views.MapView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.*;

/**
 * Created by Sutula on 30.06.15.
 */
public class MapPageFragment extends Fragment {

    private final static String TAG = "MapPageFragment";

    @InjectView(R.id.map)
    com.google.android.gms.maps.MapView mapView;
    @InjectView(R.id.progressBar) ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        ButterKnife.inject(this, view);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        int result = MapsInitializer.initialize(getActivity());

        if(result == ConnectionResult.SUCCESS) {
            ((MapView) getActivity()).setUpMap(mapView.getMap());
        } else {
            Toast.makeText(getActivity(), "Пожалуйста установите Google Play Services", Toast.LENGTH_LONG).show();
        }

        return view;

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
