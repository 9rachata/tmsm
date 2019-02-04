package app.coe.com.tmsm;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luseen.simplepermission.permissions.PermissionFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import app.coe.com.tmsm.adapter.ScanThings;
import app.coe.com.tmsm.api.FeedData;

import app.coe.com.tmsm.models.AllThings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanThingsFragment extends Fragment {
    private final String TAG_LOG = "TMSM";

    private WifiManager wifi;
    private WifiScanReceiver wifiReceiver;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private ProgressDialog dialog;

    public ScanThingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_scan_things, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.scan_things_recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext() , LinearLayout.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    RootScanThingsFragment.showOrHideBottomNavigation(false);
                } else if (dy < 0) {
                    RootScanThingsFragment.showOrHideBottomNavigation(true);
                } 
            }
        });

        String[] PERMS_INITIAL={
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        requestPermissions(PERMS_INITIAL, 127);
        wifi=(WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiScanReceiver();
        dialog = ProgressDialog.show(getContext(), "Scan Things",
                "Loading data from the server...", true);

        Gson gson = new GsonBuilder() .setLenient() .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://128.199.225.90")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FeedData feedData = retrofit.create(FeedData.class);

        Call<List<AllThings>> call = feedData.getUser();
        call.enqueue(new Callback<List<AllThings>>  () {
            @Override
            public void onResponse(Call<List<AllThings>>   call, Response<List<AllThings>>   response) {
                Log.i(TAG_LOG , response.body().toString());
                wifi.startScan();
            }

            @Override
            public void onFailure(Call<List<AllThings>>   call, Throwable t) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getContext());
                builder.setTitle("Error");
                builder.setMessage(t.getMessage().toString());
                builder.show();
                Log.i(TAG_LOG , t.getMessage().toString());

            }
        });



        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                t.cancel();
            }
        }, 10000);

        return v;
    }



    @Override
    public void onPause() {
        getActivity().getApplicationContext().unregisterReceiver(wifiReceiver);
        super.onPause();
    }

    @Override
    public void onResume() {
        getActivity().getApplicationContext().registerReceiver(
                wifiReceiver,
                new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        );
        super.onResume();
    }


    private class WifiScanReceiver extends BroadcastReceiver {

    public void onReceive(Context c, Intent intent) {

        dialog.dismiss();
        List<ScanResult> wifiScanList = wifi.getScanResults();
        List<app.coe.com.tmsm.models.ScanThings> dataset = new ArrayList<app.coe.com.tmsm.models.ScanThings>();
        for(int i = 0; i < wifiScanList.size(); i++){
            String info = wifiScanList.get(i).SSID + " " +  wifiScanList.get(i).BSSID;
            app.coe.com.tmsm.models.ScanThings data = new app.coe.com.tmsm.models.ScanThings(wifiScanList.get(i).SSID , wifiScanList.get(i).BSSID);
            dataset.add(data);

        }
        mAdapter = new ScanThings(dataset, getContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}

}
