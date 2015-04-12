package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

/**
 * REST response for dogpark /dogs/ endpoint
 */
public class DogResponse {
    @SerializedName("error")
    private int mErrorCount;

    @SerializedName("dog")
    private Dog mDog;

    @SerializedName("message")
    private String mErrorMessage;

    public int getErrorCount() {
        return mErrorCount;
    }

    public void setErrorCount(int mErrorCount) {
        this.mErrorCount = mErrorCount;
    }

    public Dog getDog() {
        return mDog;
    }

    public void setDog(Dog dog) {
        this.mDog = dog;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
}
