package biz.aejis.gourmet.app.activities;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.widget.Toast;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.helpers.MapHelper;
import biz.aejis.gourmet.app.presenters.MainPresenter;
import biz.aejis.gourmet.app.views.MainView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, MainView {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mainPresenter.setUpMap(map);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showAlert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}