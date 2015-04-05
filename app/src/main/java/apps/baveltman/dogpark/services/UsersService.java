package apps.baveltman.dogpark.services;

import apps.baveltman.dogpark.models.User;
import apps.baveltman.dogpark.models.UserResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Client to interact with /users/ endpoint
 */
public interface UsersService {

    static final String LOGGER_TAG = "UsersClient";
    static final String USERS_ENDPOINT = "https://dogpark-baveltman.rhcloud.com/";

    @GET("/users/{facebookId}")
    void getUserByFacebookId(@Path("facebookId") String facebookId, Callback<UserResponse> callback);


}
