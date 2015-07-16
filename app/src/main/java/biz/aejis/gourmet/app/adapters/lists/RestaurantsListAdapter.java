package biz.aejis.gourmet.app.adapters.lists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.activities.RestaurantInfoActivity;
import biz.aejis.gourmet.app.api.ApiClient;
import biz.aejis.gourmet.app.helpers.transformations.RoundedTransformation;
import biz.aejis.gourmet.app.listeners.RestaurantItemClickListener;
import biz.aejis.gourmet.app.managers.DatabaseManager;
import biz.aejis.gourmet.app.models.Restaurant;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sutula on 29.06.15.
 */
public class RestaurantsListAdapter extends BaseAdapter {

    public enum DataSource { DATABASE, WEB }

    private static final String TAG = "RestaurantListAdapter";
    public static final int IN_DATABASE = 1;
    public static final int NOT_IN_DATABASE = 0;

    private Context context;

    private List<Restaurant> restaurants;

    private final static int ROUND_RADIUS = 10;
    private final static int ROUND_MARGIN = 0;

    private DataSource dataSource;

    public RestaurantsListAdapter(Context context,
                                  List<Restaurant> restaurants, DataSource dataSource) {
        this.context = context;
        this.restaurants = restaurants;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.restaurant_list_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        final Restaurant currRestaurant = (Restaurant) getItem(position);

        holder.title.setText(currRestaurant.getName());
        holder.street.setText(Html.fromHtml("<u>Av. " + currRestaurant.getStreet() + "</u>"));

        if (currRestaurant.getAveragesum() > 0)
            holder.averagePrice.setText("от " + currRestaurant.getAveragesum() + " €");
        else
            holder.averagePrice.setText("");

        Log.d(TAG, String.valueOf(currRestaurant.getRating()));
        holder.ratingBar.setNumStars(currRestaurant.getRating() / 20);

        holder.imageProgressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, String.valueOf(currRestaurant.containsPhotos()));
        if (currRestaurant.containsPhotos()) {
            loadCover(holder, currRestaurant.getPhotos().get(0).getThumb());
        }

        holder.restaurantImage.setOnClickListener(new RestaurantItemClickListener(currRestaurant, context));
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return DatabaseManager.getInstance().isRestaurantInDB((Restaurant) getItem(position)) ?
                IN_DATABASE : NOT_IN_DATABASE;
    }

    @Override
    public void notifyDataSetChanged() {
        restaurants.clear();

        List<Restaurant> restaurantsList =
                dataSource == DataSource.WEB ?
                GourmetApplication.getInstance().getLatestResponse().getRestaurants() :
                DatabaseManager.getInstance().getAllRestaurants();
        restaurants.addAll(restaurantsList);
        super.notifyDataSetChanged();
    }

    static class ViewHolder {

        @InjectView(R.id.tvName)
        TextView title;
        @InjectView(R.id.ivRestaurantImage)
        ImageView restaurantImage;
        @InjectView(R.id.imageProgressBar)
        ProgressBar imageProgressBar;
        @InjectView(R.id.tvStreet)
        TextView street;
        @InjectView(R.id.tvAverPrice)
        TextView averagePrice;
        @InjectView(R.id.ratingRestaurantBar)
        RatingBar ratingBar;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }

    public void loadCover(final ViewHolder holder, String photoUrl) {
        Picasso.with(context)
                .load(ApiClient.SITE_NAME + photoUrl)
                .transform(new RoundedTransformation(ROUND_RADIUS, ROUND_MARGIN))
                .into(holder.restaurantImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.imageProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.restaurantImage.setImageResource(R.drawable.no_photo);
                    }
                });
    }
}
