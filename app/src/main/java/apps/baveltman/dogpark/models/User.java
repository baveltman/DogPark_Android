package apps.baveltman.dogpark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model of app user
 */
public class User {


    private Integer id;

    private Integer facebookId;

    private String email;

    private String birthdate;

    private Integer gender;

    private String description;

    private Integer active;


    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The facebookId
     */
    public Integer getFacebookId() {
        return facebookId;
    }

    /**
     *
     * @param facebookId
     * The facebookId
     */
    public void setFacebookId(Integer facebookId) {
        this.facebookId = facebookId;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @param birthdate
     * The birthdate
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     *
     * @return
     * The gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
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

    /**
     *
     * @return
     * The active
     */
    public Integer getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(Integer active) {
        this.active = active;
    }

}