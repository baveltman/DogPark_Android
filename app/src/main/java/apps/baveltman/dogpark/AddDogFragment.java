package apps.baveltman.dogpark;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class AddDogFragment extends Fragment {

    private EditText mDogInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_dog, parent, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");

        return v;
    }
}
