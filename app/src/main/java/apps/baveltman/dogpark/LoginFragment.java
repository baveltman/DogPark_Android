package apps.baveltman.dogpark;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

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
    private static final String EXTRA_ACCESS_TOKEN = "apps.baveltman.doppark.ACCESS_TOKEN";
    private static final String[] FACEBOOK_PERMISSIONS = new String [] {"public_profile", "email", "user_friends"};

    //instance vars
    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;
    private AccessToken mAccessToken;
    private RestAdapter mRestAdapter;
    private UsersService mUsersService;
    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        //initialize Facebook SDK
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

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

        mLoginButton = (LoginButton) v.findViewById(R.id.facebook_login_button);
        mLoginButton.setReadPermissions(Arrays.asList(FACEBOOK_PERMISSIONS));
        mLoginButton.setFragment(this);

        // Callback registration
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mAccessToken = loginResult.getAccessToken();
                Intent i = new Intent(getActivity(), AddDogActivity.class);
                i.putExtra(EXTRA_ACCESS_TOKEN, mAccessToken);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(),
                        R.string.facebook_login_canceled,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(),
                        R.string.facebook_login_failed,
                        Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Facebook logging: Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();

        // Facebook logging: Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
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
