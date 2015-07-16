package biz.aejis.gourmet.app.listeners;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.BaseAdapter;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.helpers.PreferencesHelper;
import biz.aejis.gourmet.app.managers.DatabaseManager;
import biz.aejis.gourmet.app.models.Restaurant;
import biz.aejis.gourmet.app.views.MapView;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;

/**
 * Created by Sutula on 15.07.15.
 */
public class ListSwipeOnMenuItemClickListener implements OnMenuItemClickListener {

    private static final String TAG = "ListSwipeListener";

    private Context context;
    private BaseAdapter adapter;

    public ListSwipeOnMenuItemClickListener(Context context, BaseAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu swipeMenu, int index) {

        Restaurant restaurant = ((Restaurant) adapter.getItem(position));

        switch (index) {
            case 0:
                Log.d(TAG, "Open");
                open(restaurant.getId());
                break;
            case 1:
                Log.d(TAG, "Call");
                call(restaurant);
                break;
            case 2:
                if(swipeMenu.getMenuItem(index).getTitle().equals(context.getString(R.string.toShortList))) {
                    addToShortList(restaurant);
                } else {
                    removeFromShortList(restaurant);
                }

        }
        return false;
    }

    private void call(Restaurant restaurant) {
        String phoneNumber = "+" + restaurant.getPhone().replaceAll("[^\\d]", "");

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(restaurant.getPhone())
                .setTitle(R.string.book_the_table);
        builder.setPositiveButton(R.string.call, (dialog, which) -> {
            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.create().show();
    }

    private void open(int restaurantId) {
        PreferencesHelper.writeRestaurantIdToSharedPreferences(restaurantId);
        ((MapView) context).startRestaurantInfoActivity();
    }

    private void addToShortList(Restaurant restaurant) {
        DatabaseManager.getInstance().addToShortlist(restaurant);
        adapter.notifyDataSetInvalidated();
        Log.d(TAG, "DatabaseManager.getInstance().addToShortlist(restaurant);");

    }

    private void removeFromShortList(Restaurant restaurant) {
        DatabaseManager.getInstance().removeFromShortList(restaurant);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "DatabaseManager.getInstance().removeFromShortList(restaurant);");

    }
}
