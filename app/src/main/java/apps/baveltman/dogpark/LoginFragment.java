package apps.baveltman.dogpark;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import apps.baveltman.dogpark.models.User;
import apps.baveltman.dogpark.models.UserResponse;
import apps.baveltman.dogpark.services.UsersService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginFragment extends Fragment {

    //constants
    private static final String LOGGER_TAG = "LoginFragment";

    //instance vars
    private RestAdapter mRestAdapter;
    private UsersService mUsersService;
    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        //create rest adapter and usersService
        if (mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(UsersService.USERS_ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        if (mUsersService == null) {
            mUsersService = mRestAdapter.create(UsersService.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, parent, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "Are You Freakin' Serious.ttf");
        TextView logoText = (TextView)v.findViewById(R.id.dogpark_logo_text);
        logoText.setTypeface(myTypeface);

        return v;
    }


    /**
     * POST to /users/ and create newUser
     * @param newUser is the user to be created
     */
    private void CreateUser(User newUser){
        mUsersService.createUser(newUser, new Callback<UserResponse>() {
            @Override
            public void success(UserResponse userResponse, Response response) {
                Log.i(LOGGER_TAG, " new user created with id: " + userResponse.getUser().getFacebookId());
                mUser = userResponse.getUser();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(LOGGER_TAG, "user creation failed");
            }
        });
    }

    /**
     * returns User instance by doing GET to /users/{facebookId}
     * @param facebookId facebookId of user to be returned
     */
    private void getUser(int facebookId){
        mUsersService.getUserByFacebookId(facebookId, new Callback<UserResponse>() {
            @Override
            public void success(UserResponse userResponse, Response response) {
                Log.i(LOGGER_TAG, "user info returned: " + userResponse.getUser().getFacebookId());
                mUser = userResponse.getUser();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i(LOGGER_TAG, "bad shit happened");
            }
        });
    }

}
