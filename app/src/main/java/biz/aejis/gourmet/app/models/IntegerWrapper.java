package biz.aejis.gourmet.app.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Sutula on 14.07.15.
 */
public class IntegerWrapper {
    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    Integer value;

    @DatabaseField(foreign = true)
    Restaurant restaurant;

    public IntegerWrapper() {
    }

    public IntegerWrapper(Integer value, Restaurant restaurant) {
        this.value = value;
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
