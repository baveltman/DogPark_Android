package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model of app user
 */
public class User {

    @SerializedName("id")
    private Integer mId;

    @SerializedName("facebookId")
    private Integer mFacebookId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("birthdate")
    private String mBirthdate;

    @SerializedName("gender")
    private Integer mGender;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("active")
    private Integer mActive;


    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return mId;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        mId = id;
    }

    /**
     *
     * @return
     * The facebookId
     */
    public Integer getFacebookId() {
        return mFacebookId;
    }

    /**
     *
     * @param facebookId
     * The facebookId
     */
    public void setFacebookId(Integer facebookId) {
        mFacebookId = facebookId;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        mEmail = email;
    }

    /**
     *
     * @return
     * The birthdate
     */
    public String getBirthdate() {
        return mBirthdate;
    }

    /**
     *
     * @param birthdate
     * The birthdate
     */
    public void setBirthdate(String birthdate) {
        mBirthdate = birthdate;
    }

    /**
     *
     * @return
     * The gender
     */
    public Integer getGender() {
        return mGender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(Integer gender) {
        mGender = gender;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     *
     * @return
     * The active
     */
    public Integer getActive() {
        return mActive;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(Integer active) {
        mActive = active;
    }

}