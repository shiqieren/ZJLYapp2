package com.cimc.zjly.ui.frags.subfrags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cimc.zjly.R;
import com.cimc.zjly.bean.RequestBean.AddCustomerReq;
import com.cimc.zjly.ui.frags.BaseFragment;
import com.cimc.zjly.utils.TimeUtils;

/**
 * Created by lyw on 2017/8/1.
 */

public class AddCustomerFragment extends BaseFragment implements View.OnClickListener {

    public AddCustomerReq getmAddCustomer() {
        return mAddCustomer;
    }

    private AddCustomerReq mAddCustomer ;

    private View mView;
    private LinearLayout linelayoutall;
    //控件初始化
    private TextView mCreatedate;
    private TextView mCusttype;
    private EditText mCustname;
    private EditText mShortname;
    private EditText mCusttel;
    private EditText mFax;
    private LinearLayout mCustaddress;
    private TextView mCountry;
    private TextView mProvince;
    private TextView mCity;
    private EditText mFamilyaddress;
    private EditText mZipcode;
    private TextView mWeb;
    private EditText mMakeup;
    private EditText mContact_personname;
    private EditText mContact_conttel;
    private EditText mContact_contmobile;
    private LinearLayout mContact_custaddress;
    private TextView mContact_country;
    private TextView mContact_province;
    private TextView mContact_city;
    private TextView mContact_familyaddress;
    private TextView mContact_summary;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAddCustomer = new AddCustomerReq();
        mView = inflater.inflate(R.layout.fragment_addCustomer_info,container,false);
        linelayoutall = (LinearLayout) mView.findViewById(R.id.linelay_all);
        linelayoutall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                linelayoutall.setFocusable(true);
                linelayoutall.setFocusableInTouchMode(true);
                linelayoutall.requestFocus();
                return false;
            }
        });

        //控件初始化
        mCreatedate =(TextView) mView.findViewById(R.id.createdate_tv);
        mCusttype =(TextView) mView.findViewById(R.id.custtype_tv);
        mCustname =(EditText)mView.findViewById(R.id.custname_ev);
        mShortname =(EditText)mView.findViewById(R.id.shortname_ev);
        mCusttel =(EditText)mView.findViewById(R.id.custtel_ev);
        mFax =(EditText)mView.findViewById(R.id.fax_ev);
        //地址条
        mCustaddress =(LinearLayout)mView.findViewById(R.id.custaddress_tv);
        mCountry =(TextView)mView.findViewById(R.id.country_tv);
        mProvince =(TextView)mView.findViewById(R.id.province_tv);
        mCity =(TextView)mView.findViewById(R.id.city_tv);
        mFamilyaddress =(EditText)mView.findViewById(R.id.familyaddress_ev);
        mZipcode =(EditText)mView.findViewById(R.id.zipcode_ev);
        mWeb=(TextView)mView.findViewById(R.id.web_tv);
        mMakeup =(EditText)mView.findViewById(R.id.makeup_ev);
        mContact_personname =(EditText)mView.findViewById(R.id.contact_personname_ev);
        mContact_conttel =(EditText)mView.findViewById(R.id.contact_conttel_ev);
        mContact_contmobile =(EditText)mView.findViewById(R.id.contact_contmobile_ev);
        //联系人地址条
        mContact_custaddress =(LinearLayout)mView.findViewById(R.id.contact_custaddress_tv);
        mContact_country=(TextView)mView.findViewById(R.id.contact_country_tv);
        mContact_province=(TextView)mView.findViewById(R.id.contact_province_tv);
        mContact_city=(TextView)mView.findViewById(R.id.contact_city_tv);
        mContact_familyaddress =(EditText)mView.findViewById(R.id.contact_familyaddress_ev);
        mContact_summary =(EditText)mView.findViewById(R.id.contact_summary_et);

        initView();
        initData();
        comiitadddata();
        return mView;
    }
    private void comiitadddata() {
        //提交表单数据

    }



    @Override
    public void initView() {
        String curdate = TimeUtils.getTime(System.currentTimeMillis(),TimeUtils.DATE_FORMAT_DATE);
        mCreatedate.setText(curdate);
    }

    @Override
    public void initData() {
        setListener();
    }
    private void setListener(){
        mCusttype.setOnClickListener(this);
        mCustaddress.setOnClickListener(this);
        //税种
        mWeb.setOnClickListener(this);
        mContact_custaddress.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //客户类别
            case R.id.custtype_tv:

                break;
            //客户地址
            case R.id.custaddress_tv:
                break;
            //税种
            case R.id.web_tv:
                break;
            //联系人地址
            case R.id.contact_custaddress_tv:
                break;
        }
    }
}
