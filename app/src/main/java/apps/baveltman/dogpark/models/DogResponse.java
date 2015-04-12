package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

/**
 * REST response for dogpark /dogs/ endpoint
 */
public class DogResponse {
    @SerializedName("error")
    private int mErrorCount;

    @SerializedName("dog")
    private User mUser;

    @SerializedName("message")
    private String mErrorMessage;

    public int getErrorCount() {
        return mErrorCount;
    }

    public void setErrorCount(int mErrorCount) {
        this.mErrorCount = mErrorCount;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
}
