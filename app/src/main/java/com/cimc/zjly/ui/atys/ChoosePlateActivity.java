package com.cimc.zjly.ui.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.cimc.zjly.R;
import com.cimc.zjly.bean.VehDataBean;
import com.cimc.zjly.ui.adapter.CarAdapter;
import com.cimc.zjly.ui.frags.subfrags.MyVisitCusListFragment;
import com.cimc.zjly.ui.widget.SoftKeyBoardListener;
import com.cimc.zjly.ui.widget.fakesearchview.FakeSearchView;

import java.util.ArrayList;
import java.util.List;


public class ChoosePlateActivity extends AppCompatActivity implements FakeSearchView.OnSearchListener {

    private ListView cars;

    private FakeSearchView fakeSearchView;

    private TextView tv_cancel;

   private LinearLayout activity_main;

    private CarAdapter mCarAdapter;
    private InputMethodManager inputMethodManager;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_plate);

        //初始化键盘
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        fakeSearchView = (FakeSearchView)findViewById(R.id.fakeSearchView1);
        cars = (ListView)findViewById(R.id.car_list);
        tv_cancel =(TextView) findViewById(R.id.tv_cancel);
        activity_main =(LinearLayout)findViewById(R.id.activity_main);
        fakeSearchView.setOnSearchListener(this);
        mEditText = fakeSearchView.getSearch();

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏键盘
                inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });



        SoftKeyBoardListener.setListener(ChoosePlateActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                mEditText.requestFocus();
            }

            @Override
            public void keyBoardHide(int height) {
                mEditText.clearFocus();

            }
        });



    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mCarAdapter = new CarAdapter(this, getCars());

        cars.setAdapter(mCarAdapter);
        mCarAdapter.setOnViewClickListener(mViewClickListener);

    }

    CarAdapter.OnViewClickListener mViewClickListener = new CarAdapter.OnViewClickListener() {
        @Override
        public void OnItemClick(View view, VehDataBean bean) {
            //隐藏键盘
            inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            Intent intent = new Intent();
            intent.putExtra("VehDataBean", bean);
            //设置结果返回
            setResult(MyVisitCusListFragment.RESULT, intent);
            finish();
        }

    };


    @Override
    public void onSearch(FakeSearchView fakeSearchView, CharSequence constraint) {
        ((CarAdapter) cars.getAdapter()).getFilter().filter(constraint);
    }

    @Override
    public void onSearchHint(FakeSearchView fakeSearchView, CharSequence constraint) {
        ((CarAdapter) cars.getAdapter()).getFilter().filter(constraint);
    }

    List<VehDataBean> getCars() {

        List<VehDataBean> cars = new ArrayList<>();
        cars.add(new VehDataBean("粤BG3750"));
        cars.add(new VehDataBean("粤BG323"));
        cars.add(new VehDataBean("粤BG50"));
        cars.add(new VehDataBean("粤BGgg0"));
        cars.add(new VehDataBean("川BG3750"));
        cars.add(new VehDataBean("桂erssrari"));
        cars.add(new VehDataBean("桂erhhrari1"));
        cars.add(new VehDataBean("桂grrrrarig"));
        cars.add(new VehDataBean("京errerari"));
        cars.add(new VehDataBean("京errari"));
        cars.add(new VehDataBean("京erg6rari"));
        cars.add(new VehDataBean("京erruari"));
        cars.add(new VehDataBean("京err7ari"));
        cars.add(new VehDataBean("京erirari"));
        cars.add(new VehDataBean("京err9ari"));


        return cars;
    }



    @Override
    protected void onDestroy() {
        //隐藏键盘
        inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        super.onDestroy();
    }
}
