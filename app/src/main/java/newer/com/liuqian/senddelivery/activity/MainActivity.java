package newer.com.liuqian.senddelivery.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import newer.com.liuqian.senddelivery.R;
import newer.com.liuqian.senddelivery.fragment.NearbyFragment;
import newer.com.liuqian.senddelivery.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity implements NearbyFragment.Callback {


    private Button buttonNear;
    private Button buttonOrder;
    private NearbyFragment fragmentNear;
    private OrderFragment fragmentOrder;
    private Toolbar toolbar;
    private RelativeLayout relative;
    boolean price = false;
    boolean time = false;

    private ArrayList<String> list = new ArrayList<>();
    private android.support.v4.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");

        initView();

//        initView2();n
    }

    private void initView() {
        buttonNear = (Button) findViewById(R.id.button_near);
        buttonOrder = (Button) findViewById(R.id.button_order);
        fragmentManager = getSupportFragmentManager();
        fragmentNear = new NearbyFragment();
        fragmentNear.setCallback(this);
        fragmentOrder = new OrderFragment();
        toolbar = (Toolbar) findViewById(R.id.toolbar_view);
        relative = (RelativeLayout) findViewById(R.id.relative);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconfont_geren);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");





    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentManager.beginTransaction().replace(R.id.framelayout_frag,fragmentNear).commit();
        buttonNear.setBackgroundResource(R.drawable.send_lbutton_press);
        buttonOrder.setBackgroundResource(R.drawable.send_rbutton_normal);
    }

    public void tabChange(View view) {
        switch(view.getId()) {
            case R.id.button_near:
                fragmentManager.beginTransaction().replace(R.id.framelayout_frag,fragmentNear).commit();
                buttonNear.setBackgroundResource(R.drawable.send_lbutton_press);
                buttonOrder.setBackgroundResource(R.drawable.send_rbutton_normal);
                break;
            case R.id.button_order:
                fragmentManager.beginTransaction().replace(R.id.framelayout_frag,fragmentOrder).commit();
                buttonNear.setBackgroundResource(R.drawable.send_lbutton_normal);
                buttonOrder.setBackgroundResource(R.drawable.send_rbutton_press);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //可能要用到异步
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.menu,null);
        Button button = (Button) v.findViewById(R.id.buttonfrim);
        final RadioButton radioButtonP= (RadioButton) v.findViewById(R.id.radioButton_people);
        final RadioButton radioButtonPrice= (RadioButton) v.findViewById(R.id.radioButton_price);
        final RadioButton radioButtonT= (RadioButton) v.findViewById(R.id.radioButton_time);
        if(item.getItemId() == R.id.sort_menu) {
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("排序")
                    .setView(v)
                    .show();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ssss","22223");
                    if(radioButtonP.isChecked()) {

                        alertDialog.cancel();
                    }
                    if(radioButtonPrice.isChecked()) {

                        //list 排序
                        //list 显示时间 金钱
                        //对话框取消
                        Log.d("sss", "2222");
                        price = true;
                        time = false;
                        alertDialog.cancel();


                    }
                    if(radioButtonT.isChecked()) {

                        time = true;
                        price = false;
                        alertDialog.cancel();

                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }


    //回调接口
    @Override
    public ArrayList<String> setData() {
        return list;
    }

    @Override
    public boolean getPrice() {
        return price;
    }

    @Override
    public boolean getTime() {
        return time;
    }
}
