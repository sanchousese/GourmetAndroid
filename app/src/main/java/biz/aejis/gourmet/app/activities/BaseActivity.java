package biz.aejis.gourmet.app.activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Sutula on 08.07.15.
 */
public class BaseActivity extends ActionBarActivity {

    public void showAlert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
