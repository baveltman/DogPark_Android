package apps.baveltman.dogpark;

import android.support.v4.app.Fragment;

/**
 * Created by borisvelt on 4/5/15.
 */
public class ParkListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ParkListFragment();
    }
}
