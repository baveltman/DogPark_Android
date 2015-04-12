package apps.baveltman.dogpark.services;

import apps.baveltman.dogpark.models.Dog;
import apps.baveltman.dogpark.models.DogResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * service to interact with /dogs/ endpoint
 */
public interface DogsService {
    static final String LOGGER_TAG = "DogsService";
    static final String ENDPOINT = "http://dogpark-baveltman.rhcloud.com";

    @GET("/dogs/{facebookId}")
    void getByFacebookId(@Path("facebookId") String facebookId, Callback<DogResponse> callback);

    @POST("/dogs/")
    void createDog(@Body Dog dog, Callback<DogResponse> callback);

    @PUT("/dogs/")
    void updateDog(@Body Dog dog, Callback<DogResponse> callback);
}
