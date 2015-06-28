package biz.aejis.gourmet.app.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String city;
    @Expose
    private String index;
    @Expose
    private String street;
    @Expose
    private int popularity;
    @Expose
    private int rating;
    @Expose
    private String phone;
    @Expose
    private String latitude;
    @Expose
    private String longitude;
    @SerializedName("atmosfere_ids")
    @Expose
    private List<Integer> atmosfereIds = new ArrayList<Integer>();
    @Expose
    private int averagesum;
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @Expose
    private String worktime;
    @Expose
    private String description;

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The index
     */
    public String getIndex() {
        return index;
    }

    /**
     *
     * @param index
     * The index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     *
     * @return
     * The street
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @param street
     * The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @return
     * The popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     *
     * @param popularity
     * The popularity
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     *
     * @return
     * The rating
     */
    public int getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The latitude
     */
    public double getLatitude() { return Double.parseDouble(latitude); }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return Double.parseDouble(longitude);
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The atmosfereIds
     */
    public List<Integer> getAtmosfereIds() {
        return atmosfereIds;
    }

    /**
     *
     * @param atmosfereIds
     * The atmosfere_ids
     */
    public void setAtmosfereIds(List<Integer> atmosfereIds) {
        this.atmosfereIds = atmosfereIds;
    }

    /**
     *
     * @return
     * The averagesum
     */
    public int getAveragesum() {
        return averagesum;
    }

    /**
     *
     * @param averagesum
     * The averagesum
     */
    public void setAveragesum(int averagesum) {
        this.averagesum = averagesum;
    }

    /**
     *
     * @return
     * The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     * The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     *
     * @return
     * The worktime
     */
    public String getWorktime() {
        return worktime;
    }

    /**
     *
     * @param worktime
     * The worktime
     */
    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}