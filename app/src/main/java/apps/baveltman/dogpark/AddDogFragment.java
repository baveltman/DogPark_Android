package apps.baveltman.dogpark;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import apps.baveltman.dogpark.helpers.ImageHelper;
import apps.baveltman.dogpark.services.FacebookService;

public class AddDogFragment extends Fragment {

    //code to be sought on activity result
    private static final int SELECT_PICTURE = 1;
    private static final String TAG = "AddDogFragment";


    private Typeface mTypeFace;
    private TextView mUserGreeting;
    private ImageView mUserImage;
    private ImageView mDogImage;
    private EditText mDogName;
    private TextView mDogPicButton;
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

        mDogPicButton = (TextView)v.findViewById(R.id.dog_pic_button);
        mDogPicButton.setTypeface(mTypeFace);

        bindDogPicButtonEvents();

        return v;
    }

    private void bindDogPicButtonEvents() {
        mDogPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });
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

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                Log.i(TAG, "recived dog image Uri: " + selectedImageUri.toString());

                if (selectedImageUri != null){
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                        if (bitmap != null){
                            Bitmap correctDimensionBitmap = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                            Bitmap roundedImage = ImageHelper.getRoundedCornerBitmap(correctDimensionBitmap, 150);
                            mDogImage.setImageBitmap(roundedImage);
                            mDogPicButton.setText(R.string.change_dog_pic);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
                Bitmap roundedImage = ImageHelper.getRoundedCornerBitmap(image, 150);
                mUserImage.setImageBitmap(roundedImage);
            }
        }
    }


}
