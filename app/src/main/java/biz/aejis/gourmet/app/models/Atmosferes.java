package biz.aejis.gourmet.app.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sutula on 14.07.15.
 */
public class Atmosferes {

    @Expose
    private List<Atmosfere> atmosferes = new ArrayList<>();

    /**
     *
     * @return
     * The atmosferes
     */
    public List<Atmosfere> getAtmosferes() {
        return atmosferes;
    }

    /**
     *
     * @param atmosferes
     * The atmosferes
     */
    public void setAtmosferes(List<Atmosfere> atmosferes) {
        this.atmosferes = atmosferes;
    }

}
