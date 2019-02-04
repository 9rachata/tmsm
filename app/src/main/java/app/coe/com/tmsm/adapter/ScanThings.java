package app.coe.com.tmsm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.coe.com.tmsm.R;

public class ScanThings  extends RecyclerView.Adapter<ScanThings.ViewHolder> {


    private List<app.coe.com.tmsm.models.ScanThings> data;
    private Context context;


    public ScanThings(List<app.coe.com.tmsm.models.ScanThings> data, Context context) {

        this.context = context;
        this.data = data;


        Log.i("LLLSLSLSLSLS" , "LIST DATA => " + data.size());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scan_things_rec, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        app.coe.com.tmsm.models.ScanThings scanThings = data.get(i);


        Log.i("TAGGG" , scanThings.getNameThings() + " " + scanThings.getMacAddress());
        viewHolder.nameThings.setText(scanThings.getNameThings());
        viewHolder.macAddress.setText(scanThings.getMacAddress());


        ;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameThings;
        public TextView macAddress;

        public ViewHolder(View view) {
            super(view);

            nameThings = (TextView) view.findViewById(R.id.nameThings);
            macAddress = (TextView) view.findViewById(R.id.macAddress);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("TAG_DEBUG" , getLayoutPosition()+"");
            Log.i("TAG_DEBUG" , data.get(getLayoutPosition()).getMacAddress().toString());

        }
    }
}
