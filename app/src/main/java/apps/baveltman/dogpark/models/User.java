package apps.baveltman.dogpark.models;


import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private Integer id;
    @Expose
    private Integer facebookId;
    @Expose
    private String email;
    @Expose
    private String birthdate;
    @Expose
    private Integer gender;
    @Expose
    private String description;
    @Expose
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