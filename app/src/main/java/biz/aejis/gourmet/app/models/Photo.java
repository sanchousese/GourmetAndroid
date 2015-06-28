package biz.aejis.gourmet.app.models;

import com.google.gson.annotations.Expose;

public class Photo {

    @Expose
    private String thumb;
    @Expose
    private String normal;
    @Expose
    private String original;

    /**
     *
     * @return
     * The thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb
     * The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return
     * The normal
     */
    public String getNormal() {
        return normal;
    }

    /**
     *
     * @param normal
     * The normal
     */
    public void setNormal(String normal) {
        this.normal = normal;
    }

    /**
     *
     * @return
     * The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     *
     * @param original
     * The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "thumb='" + thumb + '\'' +
                ", normal='" + normal + '\'' +
                ", original='" + original + '\'' +
                '}';
    }
}