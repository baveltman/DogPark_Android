package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

/**
 * model for dog
 */
public class Dog {

    @SerializedName("userId")
    private String mUserId;

    @SerializedName("breed")
    private String mBreed;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("weightInLbs")
    private double mWeight;

    @SerializedName("size")
    private int mSize;

    @SerializedName("picUrl")
    private String mPicUrl;

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getBreed() {
        return mBreed;
    }

    public void setBreed(String mBreed) {
        this.mBreed = mBreed;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int mSize) {
        this.mSize = mSize;
    }

    public String getPicUrl() {
        return mPicUrl;
    }

    public void setPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }
}
