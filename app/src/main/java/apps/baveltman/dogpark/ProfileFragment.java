package apps.baveltman.dogpark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import apps.baveltman.dogpark.helpers.ImageHelper;
import apps.baveltman.dogpark.models.Dog;
import apps.baveltman.dogpark.models.DogResponse;
import apps.baveltman.dogpark.services.DogsService;
import apps.baveltman.dogpark.services.FacebookService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileFragment extends Fragment {

    //code to be sought on activity result
    private static final int SELECT_PICTURE = 1;
    private static final String TAG = "AddDogFragment";


    private Typeface mTypeFace;
    private TextView mUserGreeting;
    private ImageView mUserImageBackground;
    private ImageView mUserImage;
    private ProgressBar mUserImageSpinner;
    private ListView mActivityList;

    private CallbackManager mCallbackManager;
    private Profile mFacebookProfile;

    private RestAdapter mDogParkRestAdapter;

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

        View v = inflater.inflate(R.layout.fragment_profile, parent, false);

        mTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "DroidSans.ttf");

        mUserGreeting = (TextView)v.findViewById(R.id.user_greeting);
        mUserGreeting.setTypeface(mTypeFace);

        mUserImageBackground = (ImageView)v.findViewById(R.id.user_image_background);
        mUserImage = (ImageView)v.findViewById(R.id.user_image);
        mUserImageSpinner = (ProgressBar)v.findViewById(R.id.user_image_spinner);

        TextView recentActivityText = (TextView)v.findViewById(R.id.recent_activity_text);
        recentActivityText.setTypeface(mTypeFace);

        mActivityList = (ListView)v.findViewById(R.id.activity_list);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        getCurrentFacebookProfile();
        if (mFacebookProfile != null){
            setProfileSpecificView();
        }
    }

    private void setProfileSpecificView() {
        mUserGreeting.setText(getString(R.string.hi, mFacebookProfile.getFirstName()));
        DownloadProfilePicTask picTask = new DownloadProfilePicTask();
        picTask.execute(new String[] {mFacebookProfile.getId()});
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

    private class DownloadProfilePicTask extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            String id = params[0];
            if (id != null) {
                try {
                    URL imageURL = new URL(String.format(FacebookService.IMAGE_URI, id));
                    Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                    return bitmap;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            return null;
        }

        protected void onPostExecute(Bitmap image) {
            if (image != null){
                mUserImageSpinner.setVisibility(View.GONE);
                mUserImageBackground.setVisibility(View.VISIBLE);
                mUserImage.setVisibility(View.VISIBLE);
                Bitmap roundedImage = ImageHelper.getRoundedCornerBitmap(image, 150);
                mUserImage.setImageBitmap(roundedImage);
            }
        }
    }

    private class ActivityListAdapter extends ArrayAdapter<Object>{

        public ActivityListAdapter(Context context, int resource) {
            super(context, resource);
        }
    }
}
