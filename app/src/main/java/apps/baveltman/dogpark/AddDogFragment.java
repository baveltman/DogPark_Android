package apps.baveltman.dogpark;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

import java.net.MalformedURLException;
import java.net.URL;

public class AddDogFragment extends Fragment {

    private Typeface mTypeFace;
    private TextView mUserGreeting;
    private ImageView mUserImage;
    private ImageView mDogImage;
    private EditText mDogName;
    private Button mDogPicButton;
    private CallbackManager mCallbackManager;
    private Profile mFacebookProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        //initialize Facebook SDK
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

    }

    private void getCurrentFacebookProfile() {
        mFacebookProfile = null;
        mFacebookProfile = Profile.getCurrentProfile();
        if(mFacebookProfile == null){
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_dog, parent, false);

        mTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "DroidSans.ttf");

        mUserGreeting = (TextView)v.findViewById(R.id.user_greeting);
        mUserGreeting.setTypeface(mTypeFace);

        mUserImage = (ImageView)v.findViewById(R.id.user_image);
        mDogImage = (ImageView)v.findViewById(R.id.dog_image);

        mDogName = (EditText)v.findViewById(R.id.dog_name);
        mDogName.setTypeface(mTypeFace);

        mDogPicButton = (Button)v.findViewById(R.id.dog_pic_button);
        mDogPicButton.setTypeface(mTypeFace);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getCurrentFacebookProfile();
        setProfileSpecificView();
    }

    private void setProfileSpecificView() {
        mUserGreeting.setText(getString(R.string.hi, mFacebookProfile.getFirstName()));
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

    private class DownloadProfilePic extends AsyncTask<String, String, URL> {

        @Override
        protected URL doInBackground(String... params) {
            try {
                URL imageURL = new URL("https://graph.facebook.com/" + 123 + "/picture?type=large");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(URL imageUrl) {

        }
    }


}
