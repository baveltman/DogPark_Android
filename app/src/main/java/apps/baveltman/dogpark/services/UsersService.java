package apps.baveltman.dogpark.services;

import apps.baveltman.dogpark.models.User;
import apps.baveltman.dogpark.models.UserResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Client to interact with /users/ endpoint
 */
public interface UsersService {

    static final String LOGGER_TAG = "UsersService";
    static final String USERS_ENDPOINT = "http://dogpark-baveltman.rhcloud.com";

    @GET("/users/{facebookId}")
    void getUserByFacebookId(@Path("facebookId") String facebookId, Callback<UserResponse> callback);

    @POST("/users/")
    void createUser(@Body User user, Callback<UserResponse> callback);

    @PUT("/users/")
    void updateUser(@Body User user, Callback<UserResponse> callback);


}
