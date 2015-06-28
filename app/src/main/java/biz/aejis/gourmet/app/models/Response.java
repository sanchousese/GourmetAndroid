package biz.aejis.gourmet.app.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Response {

    @Expose
    private List<Restaurant> restaurants = new ArrayList<Restaurant>();

    /**
     * @return The restaurants
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * @param restaurants The restaurants
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    public Restaurant getRestaurantByIndex(int index) {
        return restaurants.get(index);
    }

    public Restaurant getRestaurantById(int id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id){
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Response{" +
                "restaurants=" + restaurants +
                '}';
    }

}