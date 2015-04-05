package apps.baveltman.dogpark.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model of response from users endpoint
 */
public class UserResponse {

    @SerializedName("error")
    private int mErrorCount;

    @SerializedName("user")
    private User mUser;

    @SerializedName("message")
    private String mErrorMessage;

    public int getErrorCount(){
        return mErrorCount;
    }

    public void setErrorCount(int count){
        mErrorCount = count;
    }

    public User getUser(){
        return mUser;
    }

    public void setUser(User user){
        mUser = user;
    }

    public String getErrorMessage(){
        return mErrorMessage;
    }

    public void setErrorMessage(String message){
        mErrorMessage = message;
    }



}
