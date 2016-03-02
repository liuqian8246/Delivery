package newer.com.liuqian.senddelivery.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import newer.com.liuqian.senddelivery.R;
import newer.com.liuqian.senddelivery.activity.Main2Activity;
import newer.com.liuqian.senddelivery.adapter.ListViewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment {

    private ListView listView;
    private TextView textView_start;
    private TextView textView_end;
    private ListViewAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();
    private Callback callback;
    private Spinner spinnercountry;
    private Spinner spinnerlitter;
    private Spinner spinnerless;
    private boolean price;
    private boolean time;
    private boolean show;


    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nearby,container,false);
        initView(v);
        return v;
    }


    private void initView(View v) {
        listView = (ListView) v.findViewById(R.id.listView);
        textView_start = (TextView) v.findViewById(R.id.textView_start);
        textView_end = (TextView) v.findViewById(R.id.textView_end);
        spinnercountry = (Spinner) v.findViewById(R.id.spinner_country);
        spinnerless= (Spinner) v.findViewById(R.id.spinner_less);
        spinnerlitter = (Spinner) v.findViewById(R.id.spinner_litter);
        textView_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Main2Activity.class);
                startActivity(intent);
            }
        });


        textView_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View vi = LayoutInflater.from(getContext()).inflate(R.layout.mudi,null);
                new AlertDialog.Builder(getContext())
                        .setTitle("选择目的地")
                        .setView(vi)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                show = true;
                                adapter = new ListViewAdapter(getContext(),list,show);
                                listView.setAdapter(adapter);

                                Log.d("ssss", String.valueOf(adapter.show));
                            }
                        })
                        .show();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        time = callback.getTime();
        price = callback.getPrice();
        list = callback.setData();
        //这里排序
        Log.d("222", String.valueOf(list.size()));

        adapter = new ListViewAdapter(getContext(),list,show);
        listView.setAdapter(adapter);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback{
        ArrayList<String> setData();
        boolean getPrice();
        boolean getTime();
    }
}
