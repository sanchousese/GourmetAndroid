package biz.aejis.gourmet.app.adapters.lists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import biz.aejis.gourmet.app.GourmetApplication;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.models.Response;
import biz.aejis.gourmet.app.models.Restaurant;
import butterknife.ButterKnife;
import butterknife.InjectView;

import java.util.List;

/**
 * Created by Sutula on 29.06.15.
 */
public class RestaurantsListAdapter extends BaseAdapter {

    private Context context;

    private List<Restaurant> restaurants;

    public RestaurantsListAdapter(Context context) {
        this.context = context;
        restaurants = GourmetApplication.getInstance().getLatestResponse().getRestaurants();
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
        if (view != null){
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.restaurant_list_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Restaurant currRestaurant = (Restaurant) getItem(position);

        holder.title.setText(currRestaurant.getName());

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        restaurants.clear();
        restaurants.addAll(GourmetApplication.getInstance().getLatestResponse().getRestaurants());
        super.notifyDataSetChanged();
    }

    static class ViewHolder {
        @InjectView(R.id.tvTitle)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
