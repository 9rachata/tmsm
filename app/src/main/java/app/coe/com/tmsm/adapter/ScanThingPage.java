package app.coe.com.tmsm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.coe.com.tmsm.AllThingsFragment;
import app.coe.com.tmsm.ScanThingsFragment;

public class ScanThingPage extends FragmentPagerAdapter {


    public ScanThingPage(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        if(i == 0)
            return  new ScanThingsFragment();
        else if(i == 1)
            return new AllThingsFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
