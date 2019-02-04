package app.coe.com.tmsm;


import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import java.util.ArrayList;

import app.coe.com.tmsm.adapter.ScanThingPage;
import app.coe.com.tmsm.adapter.ScanThings;


/**
 * A simple {@link Fragment} subclass.
 */
public class RootScanThingsFragment extends Fragment {





    private AHBottomNavigationViewPager viewPager;
    private static AHBottomNavigation bottomNavigation;

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private View v;
    public RootScanThingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_root_scan_things, container, false);
        initUI();
        return v;
    }

    private void initUI() {


            bottomNavigation =   v.findViewById(R.id.bottom_navigation);
            viewPager =  v.findViewById(R.id.view_pager);

            AHBottomNavigationItem item1 = new AHBottomNavigationItem("Scan Things", R.drawable.access_point);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem("All Things", R.drawable.database);


            bottomNavigationItems.clear();
            bottomNavigationItems.add(item1);
            bottomNavigationItems.add(item2);

            bottomNavigation.removeAllItems();
            bottomNavigation.addItems(bottomNavigationItems);

            bottomNavigation.setTranslucentNavigationEnabled(true);


            ScanThingPage adapter = new ScanThingPage(getChildFragmentManager());

            viewPager.removeAllViews();
            viewPager.setAdapter(adapter);


            bottomNavigation.setCurrentItem(0);
            viewPager.setCurrentItem(0);

            bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                Log.i("position" ,position+"");


                viewPager.setCurrentItem(position);

                Log.i("PAGE COUNT "  , String.valueOf(viewPager.getCurrentItem()));

                return true;
            }
        });


    }
    public static void showOrHideBottomNavigation(boolean show) {
        if (show) {
            bottomNavigation.restoreBottomNavigation(true);
        } else {
            bottomNavigation.hideBottomNavigation(true);
        }
    }

}
