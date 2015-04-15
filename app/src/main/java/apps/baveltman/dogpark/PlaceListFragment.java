package apps.baveltman.dogpark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class PlaceListFragment extends Fragment {

    //constants
    private static final String LOGGER_TAG = "LoginFragment";

    //instance vars
    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mAccessTokenTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize Facebook SDK
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                if (currentAccessToken == null){
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_parks, parent, false);

        mLoginButton = (LoginButton) v.findViewById(R.id.facebook_login_button);
        mLoginButton.setReadPermissions(Arrays.asList(LoginFragment.FACEBOOK_PERMISSIONS));
        mLoginButton.setFragment(this);

        // Callback registration
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(),
                        R.string.facebook_logout_success,
                        Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        mAccessTokenTracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
