package apps.baveltman.dogpark;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class PagerFragment extends Fragment {

    private static final String TAG = "PagerFragment";
    private static final int TAB_COUNT = 2;

    private ViewPager mViewPager;
    private CollectionPagerAdapter mPagerAdapter;

    private ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionBar = getActivity().getActionBar();

        // Specify that tabs should be displayed in the action bar.
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        bindTabEventsAndListeners();

        mPagerAdapter =
                new CollectionPagerAdapter(
                        getActivity().getSupportFragmentManager());
    }

    private void bindTabEventsAndListeners() {
        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 2; i++) {
            mActionBar.addTab(
                    mActionBar.newTab()
                            .setText("Tab " + (i + 1))
                            .setTabListener(tabListener));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.pager_fragment, parent, false);

        mViewPager = (ViewPager)v.findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        bindViewPagerEvents();

        return v;
    }

    private void bindViewPagerEvents() {
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActivity().getActionBar().setSelectedNavigationItem(position);
                    }
                });
    }

    private class CollectionPagerAdapter extends FragmentPagerAdapter {


        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new AddDogFragment();
            }

            if (position == 1){
                return new ParkListFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }
}
