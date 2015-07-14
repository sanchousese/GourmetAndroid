package biz.aejis.gourmet.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.managers.DatabaseManager;
import biz.aejis.gourmet.app.models.Atmosfere;
import biz.aejis.gourmet.app.models.Photo;
import biz.aejis.gourmet.app.models.Restaurant;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

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
    @InjectView(R.id.slider)
    SliderLayout slider;
    @InjectView(R.id.ratingRestaurantBar)
    RatingBar ratingBar;
    @InjectView(R.id.atmosferesPlaceholder)
    FrameLayout atmosferesPlaceholder;

    @InjectView(R.id.btnBookTheTable)
    Button btnBookTheTable;

    @InjectView(R.id.btnRemoveFromShortList)
    Button btnRemoveFromShortList;
    @InjectView(R.id.btnAddToShortlist)
    Button btnAddToShortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_restaurant_info);
        ButterKnife.inject(this);

        slider.setDuration(5000);
        restaurant = getCurrentRestaurant();
        if (DatabaseManager.getInstance().isRestaurantInDB(restaurant)) {
            setRemoveFromShortListVisible();
        } else {
            setAddToShortListVisible();
        }
        loadInfoToViews();
    }

    private void setAddToShortListVisible() {
        btnAddToShortList.setVisibility(View.VISIBLE);
        btnRemoveFromShortList.setVisibility(View.INVISIBLE);
    }

    private void setRemoveFromShortListVisible() {
        btnAddToShortList.setVisibility(View.INVISIBLE);
        btnRemoveFromShortList.setVisibility(View.VISIBLE);
    }

    private void loadInfoToViews() {
        loadImages();

        ratingBar.setNumStars(restaurant.getRating() / 20);
        tvRestaurantName.setText(restaurant.getName());
        tvStreet.setText(Html.fromHtml("<u>Av. " + restaurant.getStreet() + "</u>"));

        if (restaurant.getAveragesum() > 0)
            tvAverageCheck.setText("от " + restaurant.getAveragesum() + " €");
        else
            tvAverageCheck.setText("");

        tvWorkingTime.setText(restaurant.getWorktime());

        tvDetails.setText(restaurant.getDescription());

        btnBookTheTable.setText(btnBookTheTable.getText() + "\t" + restaurant.getPhone());

        List<Integer> atmosferesIds = restaurant.getAtmosfereIds();
        for (int i = 0; i < atmosferesIds.size(); i++) {
            String atmosfereName = DatabaseManager.getInstance().getNameOfAtmosfere(
                            atmosferesIds.get(i)
                    );
            Log.d(TAG, "atmosfereName\t" + atmosfereName);
        }
    }

    private void loadImages() {
        for (Photo photo : restaurant.getPhotos()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")
                    .image(ApiClient.SITE_NAME + photo.getNormal())
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            slider.addSlider(textSliderView);
        }
    }

    @OnClick(R.id.rlTitle)
    public void rlTitleClick() {
        finish();
    }

    @OnClick(R.id.btnAddToShortlist)
    public void addToShortListClick() {
        Log.d(TAG, "addToShortlist");
        DatabaseManager.getInstance().addToShortlist(restaurant);
        setRemoveFromShortListVisible();
    }

    @OnClick(R.id.btnRemoveFromShortList)
    public void removeFromShortList() {
        Log.d(TAG, "removeFromShortList");
        DatabaseManager.getInstance().removeFromShortList(restaurant);
        setAddToShortListVisible();
    }

    @OnClick(R.id.btnBookTheTable)
    public void bookTheTableClick() {
        Log.d(TAG, "BookTheTable");

        String phoneNumber = "+" + restaurant.getPhone().replaceAll("[^\\d]", "");

        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
    }

    @OnClick(R.id.btnFavoriteDishes)
    public void favoriteDishesClick() {
        Log.d(TAG, "FavoriteDishes");
    }

    @OnClick(R.id.btnSumUp)
    public void sumUpClick() {
        Log.d(TAG, "SumUp");
    }

    @OnClick(R.id.tvStreet)
    public void showOnMap() {
        startActivity(new Intent(this, RestaurantMapActivity.class));
    }

    private Restaurant getCurrentRestaurant() {

        int restaurantId = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE)
                .getInt(getString(R.string.saved_restaurant_id), ERROR_CODE);

        if (restaurantId == ERROR_CODE) {
            processException();
            return null;
        }

        return ((GourmetApplication) getApplication())
                .getRestaurantById(restaurantId);
    }

    private void processException() {
        showAlert(getString(R.string.error_message));
        finish();
    }

}
