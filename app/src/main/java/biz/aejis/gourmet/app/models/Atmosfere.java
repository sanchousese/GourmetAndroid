package biz.aejis.gourmet.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Sutula on 14.07.15.
 */
@DatabaseTable(tableName = "atmosfere")
public class Atmosfere {

    @SerializedName("atmosfere_id")
    @Expose
    @DatabaseField(id = true)
    private int id;

    @Expose
    @DatabaseField
    private String name;

    public Atmosfere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
