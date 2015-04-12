package apps.baveltman.dogpark;

import android.support.v4.app.Fragment;

/**
 * Created by borisvelt on 4/12/15.
 */
public class PagerActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PagerFragment();
    }
}
