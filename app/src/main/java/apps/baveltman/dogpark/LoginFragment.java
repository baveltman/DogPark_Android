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

        //test code to be removed
        Button getUser = (Button)v.findViewById(R.id.get_user_button);
        getUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mUsersService.getUserByFacebookId("1234", new Callback<UserResponse>() {
                    @Override
                    public void success(UserResponse userResponse, Response response) {
                        Log.i(LOGGER_TAG, "user info returned: " + userResponse.getUser().getFacebookId());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.i(LOGGER_TAG, "bad shit happened");
                    }
                });
            }
        });

        Button createUser = (Button)v.findViewById(R.id.post_user_button);
        createUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                User newUser = new User();
                newUser.setFacebookId(100);
                newUser.setBirthdate("1987-09-09");
                newUser.setEmail("newuserfromAndroid1@abc.com");
                newUser.setGender(1);
                newUser.setDescription("user from android created via api");

                mUsersService.createUser(newUser, new Callback<UserResponse>() {
                    @Override
                    public void success(UserResponse userResponse, Response response) {
                        Log.i(LOGGER_TAG, " new user created with id: " + userResponse.getUser().getFacebookId());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(LOGGER_TAG, "user creation failed");
                    }
                });
            }

        });

        return v;
    }
}
