package apps.baveltman.dogpark;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;

import apps.baveltman.dogpark.models.User;
import apps.baveltman.dogpark.models.UserResponse;
import apps.baveltman.dogpark.services.FacebookService;
import apps.baveltman.dogpark.services.UsersService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginFragment extends Fragment {

    //constants
    private static final String LOGGER_TAG = "LoginFragment";
    private static final String EXTRA_ACCESS_TOKEN = "apps.baveltman.doppark.ACCESS_TOKEN";
    public static final String[] FACEBOOK_PERMISSIONS = new String [] {"public_profile", "email", "user_friends"};

    //instance vars
    private LoginButton mLoginButton;
    private ProgressBar mProgressBar;
    private CallbackManager mCallbackManager;
    private RestAdapter mDogParkRestAdapter;
    private UsersService mUsersService;
    private RestAdapter mFacebookRestAdapter;
    private FacebookService mFacebookService;
    private User mFacebookUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        //initialize Facebook SDK
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        checkFacebookLogin();

        //create DogPark rest adapter and usersService
        if (mDogParkRestAdapter == null) {
            mDogParkRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(UsersService.USERS_ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        if (mUsersService == null) {
            mUsersService = mDogParkRestAdapter.create(UsersService.class);
        }

        //create Facebook RestAdapter and service
        if (mFacebookRestAdapter == null){
            mFacebookRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(FacebookService.ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }

        if (mFacebookService == null){
            mFacebookService = mFacebookRestAdapter.create(FacebookService.class);
        }
    }

    private void checkFacebookLogin() {
       AccessToken token = AccessToken.getCurrentAccessToken();
       if (token != null && !token.isExpired()){
           redirectToPagerFragment();
       }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, parent, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "Are You Freakin' Serious.ttf");
        TextView logoText = (TextView)v.findViewById(R.id.dogpark_logo_text);
        logoText.setTypeface(myTypeface);

        mProgressBar = (ProgressBar)v.findViewById(R.id.login_progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mLoginButton = (LoginButton) v.findViewById(R.id.facebook_login_button);
        mLoginButton.setReadPermissions(Arrays.asList(FACEBOOK_PERMISSIONS));
        mLoginButton.setFragment(this);

        // Callback registration
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mProgressBar.setVisibility(View.VISIBLE);
                getAndSaveFacebookUser(loginResult);
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
    public void onStart() {
        super.onStart();
        checkFacebookLogin();
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

    private void getAndSaveFacebookUser(LoginResult loginResult){

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        Log.i(LOGGER_TAG, "returned user from facebook" + object.toString());

                        //parse facebook user
                        Gson gson = new Gson();
                        mFacebookUser = gson.fromJson(object.toString(), User.class);
                        if (mFacebookUser != null && mFacebookUser.getId() != null){
                            //check if user already in dogpark DB
                            mUsersService.getUserByFacebookId(mFacebookUser.getId(), new Callback<UserResponse>() {
                                @Override
                                public void success(UserResponse userResponse, Response response) {
                                    Log.i(LOGGER_TAG, "user info returned from dogpark");
                                    if (userResponse.getUser() != null){
                                        //user already exists, update existing user

                                        mUsersService.updateUser(mFacebookUser, new Callback<UserResponse>() {
                                            @Override
                                            public void success(UserResponse userResponse, Response response) {
                                                Log.i(LOGGER_TAG, "existing user updated, id: " + userResponse.getUser().getId());
                                                redirectToPagerFragment();
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                Log.i(LOGGER_TAG, "existing user update FAILED, id: " + mFacebookUser.getId() + " message: " + error.getMessage().toString());
                                                error.printStackTrace();
                                                redirectToPagerFragment();
                                            }
                                        });

                                    } else {
                                        //user does not exist, create and redirect to add dog activity
                                        mUsersService.createUser(mFacebookUser, new Callback<UserResponse>() {
                                            @Override
                                            public void success(UserResponse userResponse, Response response) {
                                                Log.i(LOGGER_TAG, " new user created with id: " + userResponse.getUser().getId());
                                                redirectToPagerFragment();
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                Log.i(LOGGER_TAG, "user creation failed");
                                                Toast.makeText(getActivity(),
                                                        R.string.facebook_login_failed,
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void failure(RetrofitError retrofitError) {
                                    Log.i(LOGGER_TAG, "bad shit happened, couldn't get user");
                                    retrofitError.printStackTrace();
                                }
                            });
                        }

                    }
                });
            request.executeAsync();
    }

    private void redirectToPagerFragment() {
        Intent i = new Intent(getActivity(), PagerActivity.class);
        startActivity(i);
    }

    private void redirectToAddDog() {
        Intent i = new Intent(getActivity(), AddDogActivity.class);
        startActivity(i);
    }

    private void redirectToParkList() {
        Intent i = new Intent(getActivity(), ParkListActivity.class);
        startActivity(i);
    }

}
