package biz.aejis.gourmet.app.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import biz.aejis.gourmet.app.managers.DatabaseManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "restaurant")
public class Restaurant {

    @Expose
    @DatabaseField(id = true)
    private int id;

    @Expose
    @DatabaseField
    private String name;

    @Expose
    @DatabaseField
    private String city;

    @Expose
    @DatabaseField
    private String index;

    @Expose
    @DatabaseField
    private String street;

    @Expose
    @DatabaseField
    private int popularity;

    @Expose
    @DatabaseField
    private int rating;

    @Expose
    @DatabaseField
    private String phone;

    @Expose
    @DatabaseField
    private String latitude;

    @Expose
    @DatabaseField
    private String longitude;

    @SerializedName("atmosfere_ids")
    @Expose
    private List<Integer> atmosfereIds = new ArrayList<>();

    @ForeignCollectionField
    private Collection<IntegerWrapper> atmosferesInDB = new ArrayList<>();

    @Expose
    @DatabaseField
    private int averagesum;

    @Expose
    @ForeignCollectionField(eager = true)
    private Collection<Photo> photos = new ArrayList<>();

    @Expose
    @DatabaseField
    private String worktime;

    @Expose
    @DatabaseField
    private String description;

    public Restaurant() {
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The index
     */
    public String getIndex() {
        return index;
    }

    /**
     * @param index The index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     * @return The street
     */
    public String getStreet() {
        return street != null ? street : "";
    }

    /**
     * @param street The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return The popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * @param popularity The popularity
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * @return The rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating The rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return The latitude
     */
    public double getLatitude() {
        return Double.parseDouble(latitude);
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", index='" + index + '\'' +
                ", street='" + street + '\'' +
                ", popularity=" + popularity +
                ", rating=" + rating +
                ", phone='" + phone + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", atmosfereIds=" + atmosfereIds +
                ", averagesum=" + averagesum +
                ", photos=" + photos +
                ", worktime='" + worktime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * @return The longitude
     */
    public Double getLongitude() {
        return Double.parseDouble(longitude);
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The atmosfereIds
     */
    public List<Integer> getAtmosfereIds() {
        if (atmosfereIds != null && atmosfereIds.size() > 0) {
            return atmosfereIds;
        }

        List<Integer> integers = new ArrayList<>();
        for (IntegerWrapper wrapper : atmosferesInDB) {
            integers.add(wrapper.getValue());
        }
        return integers;
    }

    /**
     * @param atmosfereIds The atmosfere_ids
     */
    public void setAtmosfereIds(List<Integer> atmosfereIds) {
        this.atmosfereIds = atmosfereIds;
    }

    /**
     * @return The averagesum
     */
    public int getAveragesum() {
        return averagesum;
    }

    /**
     * @param averagesum The averagesum
     */
    public void setAveragesum(int averagesum) {
        this.averagesum = averagesum;
    }

    /**
     * @return The photos
     */
    public List<Photo> getPhotos() {
        return new ArrayList<>(photos);
    }

    /**
     * @param photos The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return The worktime
     */
    public String getWorktime() {
        return worktime;
    }

    /**
     * @param worktime The worktime
     */
    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean containsPhotos() {
        return !photos.isEmpty();
    }

    public void hydrate() {
        for (Photo photo : photos) {
            photo.setRestaurant(this);
            DatabaseManager.getInstance().addPhoto(photo);
        }

        for (Integer integer : atmosfereIds) {
            IntegerWrapper wrapper = new IntegerWrapper(integer, this);
            atmosferesInDB.add(wrapper);
            DatabaseManager.getInstance().addIntegerWrapper(wrapper);
        }
    }

    public Collection<IntegerWrapper> getAtmosferesInDB() {
        return atmosferesInDB;
    }

    public void setAtmosferesInDB(Collection<IntegerWrapper> atmosferesInDB) {
        this.atmosferesInDB = atmosferesInDB;
    }
}