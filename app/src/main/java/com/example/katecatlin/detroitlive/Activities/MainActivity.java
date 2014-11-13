package com.example.katecatlin.detroitlive.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.katecatlin.detroitlive.Fragments.MainFragment;
import com.example.katecatlin.detroitlive.Fragments.TodayFragment;
import com.example.katecatlin.detroitlive.Fragments.TomorrowFragment;
import com.example.katecatlin.detroitlive.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    static final int NUM_ITEMS = 3;
    private static final String[] titles = { "All Shows", "Today", "Tomorrow" };
    final static public String LOG_TAG = "LOG_TAG";


    MyPagerAdapter mFragmentPagerAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentPagerAdapter = new MyPagerAdapter(getFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mFragmentPagerAdapter);

        final PagerTabStrip strip = PagerTabStrip.class.cast(findViewById(R.id.tabs));
        strip.setTabIndicatorColor(Color.GRAY);
        strip.setPadding(0, 10, 0, 10);
        strip.setNonPrimaryAlpha(0.5f);
        strip.setTextSpacing(10);
        strip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        public static int NUM_FRAGMENTS = 3;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_FRAGMENTS;
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
            }


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            View tv = v.findViewById(R.id.text);
            Log.d(LOG_TAG, "mNum before setText in OnCreateView is " + mNum);
            ((TextView)tv).setText("Fragment #" + mNum);
            Log.d(LOG_TAG, "mNum in onCreateView is " + mNum);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            Log.d(LOG_TAG, "mNum in on ActivityCreated is " + mNum);


            ArrayList<String> list = new ArrayList<String>();
            list.add("Item1");
            list.add("Item2");
            list.add("Item3");
            list.add("Item4");

            ArrayList<String> cheese = new ArrayList<String>();
            list.add("chees1");
            list.add("cheez");
            list.add("cheeses");
            list.add("chzplz");

            ArrayList<String> chocolate = new ArrayList<String>();
            list.add("cocoa");
            list.add("dark");
            list.add("milk");
            list.add("ILOVECHOCOLATE");

            if (mNum == 0) {
                setListAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, list));
            }
            Log.d(LOG_TAG, "mNum is " + mNum);

            if (mNum == 1) {
                setListAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, cheese));
            }
            Log.d(LOG_TAG, "mNum is " + mNum);

            if (mNum == 2) {
                setListAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, chocolate));

            }
            Log.d(LOG_TAG, "mNum is " + mNum);

        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }
}
