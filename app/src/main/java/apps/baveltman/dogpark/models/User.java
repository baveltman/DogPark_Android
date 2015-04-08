package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

import apps.baveltman.dogpark.Enums.Gender;

/**
 * Model of app user
 */
public class User {

    @SerializedName("id")
    private String mId;

    @SerializedName("active")
    private int mActive;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("timezone")
    private int mTimeZone;

    @SerializedName("name")
    private String mFullName;

    @SerializedName("locale")
    private String mLocale;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("description")
    private String mDescription;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public int getActive() {
        return mActive;
    }

    public void setActive(Integer active) {
        mActive = active;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public int getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(int mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getLocale() {
        return mLocale;
    }

    public void setLocale(String mLocale) {
        this.mLocale = mLocale;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}