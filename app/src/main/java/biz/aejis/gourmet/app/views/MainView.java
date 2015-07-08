package biz.aejis.gourmet.app.views;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Sutula on 28.06.15.
 */
public interface MainView extends BasicView {

    void setUpMap(GoogleMap map);

    void updateList();

    void setProgressBarVisible();

    void setProgressBarGone();
}
