package biz.aejis.gourmet.app.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.models.Restaurant;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;

public class RestaurantInfoActivity extends BaseActivity {

    private final static String TAG = "RestaurantInfoActivity";
    private final static int ERROR_CODE = -1;

    private Restaurant restaurant;

    @InjectView(R.id.tvRestaurantName)
    TextView tvRestaurantName;
    @InjectView(R.id.tvStreet)
    TextView tvStreet;
    @InjectView(R.id.tvAverageCheck)
    TextView tvAverageCheck;
    @InjectView(R.id.tvWorkingTime)
    TextView tvWorkingTime;
    @InjectView(R.id.tvDetails)
    TextView tvDetails;
    @InjectView(R.id.ivPreview)
    ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_restaurant_info);
        ButterKnife.inject(this);

        restaurant = getCurrentRestaurant();
        loadInfoToViews();
    }

    private void loadInfoToViews() {
        tryToLoadImage();
        tvRestaurantName.setText(restaurant.getName());
        tvStreet.setText(Html.fromHtml("<u>Av. " + restaurant.getStreet() + "</u>"));

        if(restaurant.getAveragesum() > 0)
            tvAverageCheck.setText("от " + restaurant.getAveragesum() + " €");
        else
            tvAverageCheck.setText("");

        tvWorkingTime.setText(restaurant.getWorktime());

        tvDetails.setText(restaurant.getDescription());
    }

    private void tryToLoadImage() {
        if(restaurant.getPhotos().size() > 0) {
            Picasso.with(this)
                    .load(ApiClient.SITE_NAME + restaurant.getPhotos().get(0).getNormal())
                    .into(ivPreview);
        }
    }

    @OnClick(R.id.btnAddToShortlist)
    public void addToShortListClick() {
        Log.d(TAG, "addToShortlist");
    }

    @OnClick(R.id.btnBookTheTable)
    public void bookTheTableClick() {
        Log.d(TAG, "BookTheTable");
    }

    @OnClick(R.id.btnFavoriteDishes)
    public void favoriteDishesClick() {
        Log.d(TAG, "FavoriteDishes");
    }

    @OnClick(R.id.btnSumUp)
    public void sumUpClick() {
        Log.d(TAG, "SumUp");
    }

    private Restaurant getCurrentRestaurant() {
        int restaurantId = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
                .getInt(getString(R.string.saved_restaurant_id), ERROR_CODE);

        if (restaurantId == ERROR_CODE) {
            processException();
            return null;
        }

        return ((GourmetApplication) getApplication())
                .getLatestResponse()
                .getRestaurantById(restaurantId);
    }

    private void processException() {
        showAlert(getString(R.string.error_message));
        finish();
    }

}
