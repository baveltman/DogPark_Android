package apps.baveltman.dogpark;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, parent, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "Are You Freakin' Serious.ttf");
        TextView logoText = (TextView)v.findViewById(R.id.dogpark_logo_text);
        logoText.setTypeface(myTypeface);

        return v;
    }
}
