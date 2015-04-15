package apps.baveltman.dogpark;

import android.support.v4.app.Fragment;

/**
 * Created by borisvelt on 4/4/15.
 */
public class ProfileActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ProfileFragment();
    }
}
