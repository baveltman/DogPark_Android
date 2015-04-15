package apps.baveltman.dogpark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private static final String LOGGER_TAG = "PlaceListFragment";

    //instance vars
    private ListView mPlacesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_places, parent, false);

        mPlacesList = (ListView)v.findViewById(R.id.places_list);

        return v;
    }

    private class PlaceListAdapter extends ArrayAdapter<Object>{

        public PlaceListAdapter(Context context, int resource) {
            super(context, resource);
        }
    }
}
