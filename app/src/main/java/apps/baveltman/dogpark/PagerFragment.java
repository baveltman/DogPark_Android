package apps.baveltman.dogpark;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerFragment extends Fragment {

    private static final String TAG = "PagerFragment";
    private static final int TAB_COUNT = 2;

    private ViewPager mViewPager;
    private CollectionPagerAdapter mPagerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPagerAdapter =
                new CollectionPagerAdapter(
                        getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.pager_fragment, parent, false);

        mViewPager = (ViewPager)v.findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        return v;
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
