package apps.baveltman.dogpark;


import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apps.baveltman.dogpark.helpers.SlidingTabLayout;

public class PagerFragment extends Fragment {

    private static final String TAG = "PagerFragment";
    private static final int TAB_COUNT = 2;

    private ViewPager mViewPager;
    private CollectionPagerAdapter mPagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;

    private ActionBar mActionBar;

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

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
        mSlidingTabLayout.setViewPager(mViewPager);

        return v;
    }


    private class CollectionPagerAdapter extends FragmentPagerAdapter {

        private int[] imageResId = {
                R.drawable.ic_perm_identity_white_36dp,
                R.drawable.ic_list_white_36dp
        };


        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new AddDogFragment();
            }

            if (position == 1){
                return new PlaceListFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Drawable image = getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }
    }
}
