package biz.aejis.gourmet.app.creators;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import biz.aejis.gourmet.app.R;
import biz.aejis.gourmet.app.adapters.lists.RestaurantsListAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;

/**
 * Created by Sutula on 15.07.15.
 */
public class ListSwipeMenuCreator implements SwipeMenuCreator {

    private Context context;

    public ListSwipeMenuCreator(Context context) {
        this.context = context;
    }

    @Override
    public void create(SwipeMenu swipeMenu) {
        swipeMenu.addMenuItem(createMenuItem(R.string.open));
        swipeMenu.addMenuItem(createMenuItem(R.string.call));

        // There are two types of items: in shortlist and not in shortlist
        switch (swipeMenu.getViewType()) {
            case RestaurantsListAdapter.IN_DATABASE:
                swipeMenu.addMenuItem(createMenuItem(R.string.fromShortList));
                break;
            case RestaurantsListAdapter.NOT_IN_DATABASE:
                swipeMenu.addMenuItem(createMenuItem(R.string.toShortList));
                break;
        }
    }

    private SwipeMenuItem createMenuItem(int titleRes) {

        SwipeMenuItem menuItem = new SwipeMenuItem(context);
        menuItem.setBackground(R.color.red);
        menuItem.setWidth(110);
        menuItem.setTitle(context.getResources().getString(titleRes));
        menuItem.setTitleSize(8);
        menuItem.setTitleColor(context.getResources().getColor(R.color.sand));

        return menuItem;
    }
}
