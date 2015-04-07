package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

import apps.baveltman.dogpark.Enums.Gender;

/**
 * Model of app user
 */
public class User {

    @SerializedName("id")
    private int mId;

    @SerializedName("active")
    private int mActive;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("timezone")
    private int mTimeZone;

    @SerializedName("name")
    private int mFullName;

    @SerializedName("locale")
    private int mLocale;

    @SerializedName("last_name")
    private int mLastName;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("description")
    private int mDescription;



    public int getId() {
        return mId;
    }

    public void setId(Integer id) {
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

    public int getFullName() {
        return mFullName;
    }

    public void setFullName(int mFullName) {
        this.mFullName = mFullName;
    }

    public int getLocale() {
        return mLocale;
    }

    public void setLocale(int mLocale) {
        this.mLocale = mLocale;
    }

    public int getLastName() {
        return mLastName;
    }

    public void setLastName(int mLastName) {
        this.mLastName = mLastName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String mGender) {
        this.mGender = mGender;
    }

    public int getDescription() {
        return mDescription;
    }

    public void setDescription(int mDescription) {
        this.mDescription = mDescription;
    }
}