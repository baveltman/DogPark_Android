package apps.baveltman.dogpark.services;


import org.json.JSONObject;

import apps.baveltman.dogpark.models.User;
import apps.baveltman.dogpark.models.UserResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface FacebookService {

    static final String LOGGER_TAG = "FacebookService";
    static final String ENDPOINT = "https://graph.facebook.com";

    @GET("/v2.3/{facebookId}")
    void getUserByFacebookId(@Path("facebookId") String facebookId, Callback<JSONObject> callback);

}
