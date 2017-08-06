package com.cimc.zjly.ui.frags.subfrags;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cimc.zjly.R;
import com.cimc.zjly.Setting;
import com.cimc.zjly.bean.CustomerSelectSqlDataOption;
import com.cimc.zjly.bean.CustomersDetailItem;
import com.cimc.zjly.bean.RequestBean.AddCustomerReq;
import com.cimc.zjly.bean.Result;
import com.cimc.zjly.ui.adapter.MyCusTypeListPopuAdapter;
import com.cimc.zjly.ui.adapter.MyWebListPopuAdapter;
import com.cimc.zjly.ui.atys.ReadEditDetailActivity;
import com.cimc.zjly.ui.callback.StringDialogCallback;
import com.cimc.zjly.ui.frags.BaseFragment;
import com.cimc.zjly.ui.widget.ChangeAddressPopwindow;
import com.cimc.zjly.ui.widget.ChangeSqlAddressPopwindow;
import com.cimc.zjly.ui.widget.MyOptionBottomDialog;
import com.cimc.zjly.utils.GetAssert;
import com.cimc.zjly.utils.TimeUtils;
import com.cimc.zjly.utils.UIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by lyw on 2017/8/1.
 */

public class ReadCustomerDetailFragment extends BaseFragment implements View.OnClickListener {

    public AddCustomerReq getmAddCustomer() {
        return mAddCustomer;
    }

    private AddCustomerReq mAddCustomer ;
    private AddCustomerReq.ContactBean mAddCustomerContactBean ;
    private static final int NEWCUSTOMER = 0;
    private static final int CONTACT_NEWCUSTOMER = 1;
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
    private EditText mContact_summary;
    private TextView mCustomerno;
    private EditText mSummary;
    //传递id
    private TextView mCusttypecodeid;
    private TextView mWebcodeid;
    private TextView mCountrycategoryno;
    private TextView mProvincecategoryno;
    private TextView mCitycategoryno;

    private boolean isCustExist = false;

    private List<CustomerSelectSqlDataOption.WebBean> webList;
    private List<CustomerSelectSqlDataOption.CustTypeBean> custTypeList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mAddCustomer = new AddCustomerReq();
        mAddCustomerContactBean = new AddCustomerReq.ContactBean();
        mView = inflater.inflate(R.layout.fragment_readdetailcustomer_info,container,false);
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
        mCustomerno=(TextView) mView.findViewById(R.id.custtype_tv);
        mSummary=(EditText)mView.findViewById(R.id.custname_ev);
        /////
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
        //隐藏id
         mCusttypecodeid=(TextView)mView.findViewById(R.id.custtypecodeid_tv);
         mWebcodeid=(TextView)mView.findViewById(R.id.webcodeid_tv);
         mCountrycategoryno=(TextView)mView.findViewById(R.id.countrycategoryno_tv);
         mProvincecategoryno=(TextView)mView.findViewById(R.id.provincecategoryno_tv);
         mCitycategoryno=(TextView)mView.findViewById(R.id.citycategoryno_tv);

        readData();
        initView();
        initData();
        mView.findViewById(R.id.commit_addcusvisdata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comiitadddata();
            }
        });
        mView.findViewById(R.id.commit_linkman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction= fm.beginTransaction();
                transaction.replace(R.id.addsubmit_page, new AddLinkmanFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mView.findViewById(R.id.commit_visit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction= fm.beginTransaction();
                transaction.replace(R.id.addsubmit_page, new AddVisitCusFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mView.findViewById(R.id.commit_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction= fm.beginTransaction();
                transaction.replace(R.id.addsubmit_page, new AddLinkmanFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return mView;
    }

    private void readData(){
        int custId = (Integer)getActivity().getIntent().getIntExtra("custId ",-1);
        String reqcustId = Integer.toString(custId);
        if(custId == -1){
            Toast.makeText(getContext(),"未获取到客户id",Toast.LENGTH_SHORT).show();
        }else {
            //获取到用户具体传值时网络请求加载默认已有的字段
            String url = Setting.API_SERVER_URL + "/cust/getCurrInfo";

            OkHttpUtils
                    .post()
                    .url(url)
                    .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                    .addHeader("sessionKey","2")
                    .addParams("custId",reqcustId)
                    .build()
                    .execute(
                            new StringDialogCallback(getActivity()){
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                    dismissDialog();
                                    Toast.makeText(getContext(),"????"+e.toString(),Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    dismissDialog();
                                    Type userlistType = new TypeToken<Result<CustomersDetailItem>>
                                            (){}.getType();
                                    Result<CustomersDetailItem> status = new Gson().fromJson(response, userlistType);
                                    CustomersDetailItem thisobj = status.getData();
                                    mCustomerno.setText(thisobj.getCustomerno());
                                    mCusttype.setText(thisobj.getCusttype());
                                    mCustname.setText(thisobj.getCustname());
                                    mShortname.setText(thisobj.getShortname());
                                    mCusttel.setText(thisobj.getCusttel());
                                    mFax.setText(thisobj.getFax());
                                    mCountry.setText(thisobj.getCountry());
                                    mProvince.setText(thisobj.getProvince());
                                     mCity.setText(thisobj.getCity());
                                    mFamilyaddress.setText(thisobj.getCustaddress());
                                    mZipcode.setText(thisobj.getZipcode());
                                    mWeb.setText(thisobj.getWeb());
                                    mMakeup.setText(thisobj.getMakeup());
                                    mSummary.setText(thisobj.getSummary().toString());
                                }
                            }
                    );
        }
    }

    private void comiitadddata() {
        //提交表单数据
        if(mCusttype.getText()!="" &&String.valueOf(mCustname.getText()) !="" && String.valueOf(mCusttel.getText()) !=""&&String.valueOf(mCountry.getText())!=""&&String.valueOf(mFamilyaddress.getText())!=""&&mWeb.getText()!=""){
            mAddCustomer.setCusttype(String.valueOf(mCusttypecodeid.getText()));
            mAddCustomer.setCustname(String.valueOf(mCustname.getText()));

            mAddCustomer.setCustaddress(String.valueOf(mFamilyaddress.getText()));
            mAddCustomer.setCusttel(String.valueOf(mCusttel.getText()));

            mAddCustomer.setZipcode(String.valueOf(mZipcode.getText()));
            mAddCustomer.setWeb(String.valueOf(mWebcodeid.getText()));
        //修改人当前登入uid
            mAddCustomer.setModifyby(Integer.valueOf(Setting.getCachedUID(UIUtils.getContext())));
            mAddCustomer.setOwner(Integer.valueOf(Setting.getCachedUID(UIUtils.getContext())));

            mAddCustomer.setShortname(String.valueOf(mShortname.getText()));
            mAddCustomer.setFax(String.valueOf(mFax.getText()));
            mAddCustomer.setCountry(String.valueOf(mCountry.getText()));

            mAddCustomer.setProvince(String.valueOf(mProvincecategoryno.getText()));
            mAddCustomer.setCity(String.valueOf(mCitycategoryno.getText()));
            mAddCustomer.setMakeup(String.valueOf(mMakeup.getText()));
            mAddCustomer.setSummary(String.valueOf(mContact_summary.getText()));

            mAddCustomerContactBean.setPersonname(String.valueOf(mContact_personname.getText()));
            mAddCustomerContactBean.setConttel(String.valueOf(mContact_conttel.getText()));
            mAddCustomerContactBean.setContmobile(String.valueOf(mContact_contmobile.getText()));
            mAddCustomerContactBean.setFamilyaddress(String.valueOf(mContact_familyaddress.getText()));
            mAddCustomerContactBean.setSummary(String.valueOf(mContact_summary.getText()));
            mAddCustomerContactBean.setFamilyarea(String.valueOf(mContact_province.getText()));
            mAddCustomerContactBean.setFamilycity(String.valueOf(mContact_city.getText()));

            mAddCustomer.setContact(mAddCustomerContactBean);
            if(Setting.getCachedUID(UIUtils.getContext()).toString()!=""){
                checkname();
                if (isCustExist){
                    String url = Setting.API_SERVER_URL + "/cust/addCust";
                    OkHttpUtils
                            .postString()
                            .url(url)
                            .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                            .addHeader("sessionKey","2")
                            .content(new Gson().toJson(mAddCustomer)
                            ).mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(
                                    new StringDialogCallback(getActivity()) {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            dismissDialog();
                                            Toast.makeText(getContext(),"????"+e.toString(),Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            dismissDialog();
                                            Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();

                                        }
                                    }
                            );
                }else {
                    Toast.makeText(getActivity(),"用户未登陆，获取不到UID",Toast.LENGTH_SHORT).show();
                }
            }else {
                mCustname.requestFocus();
            }

        }else {
            Toast.makeText(UIUtils.getContext(),"必填字段不能为空",Toast.LENGTH_SHORT).show();
        }

    }
    private void checkname(){
        if(mCustname.getText().toString()!=""&&Setting.getCachedUID(UIUtils.getContext())!=""){
            String url = Setting.API_SERVER_URL + "/cate/isCustExist";
            OkHttpUtils
                    .post()
                    .url(url)
                    .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                    .addHeader("sessionKey","2")
                    .addParams("custId",Setting.getCachedUID(UIUtils.getContext()))
                    .addParams("custName",mCustname.getText().toString())
                    .build()
                    .execute(
                            new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(getContext(),"????"+e.toString(),Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                                    Type userType = new TypeToken<Result>(){}.getType();
                                    Result status = new Gson().fromJson(response, userType);
                                    ;
                                    if ((boolean)status.getData()){
                                        isCustExist =  true;
                                        //背景色正常
                                        mCustname.setBackgroundColor(0);
                                    }else {
                                        isCustExist = false;
                                        //背景色不正常
                                        mCustname.setBackgroundColor(UIUtils.getColor(R.color.colorAccent));
                                    }

                                }
                            }
                    );
        }else {
            Toast.makeText(getActivity(),"请先输入用户名",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void initView() {
        String curdate = TimeUtils.getTime(System.currentTimeMillis(),TimeUtils.DATE_FORMAT_DATE);
        mCreatedate.setText(curdate);
    }

    @Override
    public void initData() {
        setdata();
        setListener();
    }
    private void setdata() {

        //本地测试大区域list
        Type RegionType = new TypeToken<Result<CustomerSelectSqlDataOption>>(){}.getType();
        Result<CustomerSelectSqlDataOption> status = new Gson().fromJson(GetAssert.getJson("cimccity.json", getContext()), RegionType);
        webList =(ArrayList<CustomerSelectSqlDataOption.WebBean>)status.getData().getWeb();
        custTypeList =
                (ArrayList<CustomerSelectSqlDataOption.CustTypeBean>)
                        status.getData().getCustType();

       /* if (Setting.getCachedUID(UIUtils.getContext()) != null){
            int requid = Integer.valueOf(Setting.getCachedUID(UIUtils.getContext()));
            //网络请求  加载默认已有的字段
            String url = Setting.API_SERVER_URL + "/cust/getCustSelect";
            OkHttpUtils
                    .post()
                    .url(url)
                    .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                    .addHeader("sessionKey","2")
                    .addParams("userId",String.valueOf(requid))
                    .build()
                    .execute(
                            new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(getContext(),"????"+e.toString(),Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Type userlistType = new TypeToken<Result<CustomerSelectSqlDataOption>>(){}.getType();
                                    Result<CustomerSelectSqlDataOption> status = new Gson().fromJson(response, userlistType);
                                   // weblist= (ArrayList<SelectCustomersItem>)status.getData();
                                    webList =(ArrayList<CustomerSelectSqlDataOption.WebBean>)status.getData().getWeb();
                                    custTypeList =
                                            (ArrayList<CustomerSelectSqlDataOption.CustTypeBean>)
                                                    status.getData().getCustType();






                                }
                            }
                    );
        }else {
            Toast.makeText(getContext(),"获取不到当前登录用户uid",Toast.LENGTH_SHORT).show();
        }*/
    }
//客户类别弹出框
    private void setPopuWindow(List<CustomerSelectSqlDataOption.CustTypeBean> stringList, String
            titileName, final TextView tv, MyCusTypeListPopuAdapter adapter){
        final MyOptionBottomDialog optionBottomDialog = new MyOptionBottomDialog(getContext(), stringList,titileName,adapter);
        optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerSelectSqlDataOption.CustTypeBean thisobj = (CustomerSelectSqlDataOption.CustTypeBean) parent.getItemAtPosition(position);
               //隐藏值
                mCusttypecodeid.setText(thisobj.getCodeid().toString());
                // (SelectCustomersItem)parent.getItemAtPosition(position)
                tv.setText(thisobj.getCodevalue().toString());
                optionBottomDialog.dismiss();
            }
        });
    }
        //税种弹出框
    private void setPopuWindow(List<CustomerSelectSqlDataOption.WebBean> stringList, String
            titileName, final TextView tv, MyWebListPopuAdapter adapter){
        final MyOptionBottomDialog optionBottomDialog = new MyOptionBottomDialog(getContext(), stringList,titileName,adapter);
        optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerSelectSqlDataOption.WebBean thisobj = (CustomerSelectSqlDataOption.WebBean) parent.getItemAtPosition(position);
                //隐藏值
                mWebcodeid.setText(thisobj.getCodeid().toString());
                // (SelectCustomersItem)parent.getItemAtPosition(position)
                tv.setText(thisobj.getCodevalue().toString());
                optionBottomDialog.dismiss();
            }
        });
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
                String custTypetitileName = "请选择客户类别";

                MyCusTypeListPopuAdapter mCustTypeAdapter = new
                        MyCusTypeListPopuAdapter
                        (custTypeList);

                setPopuWindow(custTypeList,custTypetitileName,mCusttype,
                        mCustTypeAdapter);
                break;
            //客户地址
            case R.id.custaddress_tv:
                setaddress();
                break;
            //税种
            case R.id.web_tv:
                String webtitileName = "请选择税种";
                MyWebListPopuAdapter mWebAdapter = new MyWebListPopuAdapter
                        (webList);
                setPopuWindow(webList,webtitileName,mWeb,mWebAdapter);
                break;
            //联系人地址
            case R.id.contact_custaddress_tv:
                setlocaladdress();
                break;
        }
    }
    // private void setaddress(int userid) {
    private void setaddress() {
        //ChangeSqlAddressPopwindow mChangeSqlAddressPopwindow = new ChangeSqlAddressPopwindow(getActivity(),userid);
        ChangeSqlAddressPopwindow mChangeSqlAddressPopwindow = new ChangeSqlAddressPopwindow(getActivity());
        // mChangeSqlAddressPopwindow.setAddress("广东", "深圳", "福田区");
        mChangeSqlAddressPopwindow.showAtLocation(mCustaddress, Gravity.BOTTOM, 0, 0);
        mChangeSqlAddressPopwindow.setAddresskListener(new ChangeSqlAddressPopwindow.OnAddressCListener() {

            @Override
            public void onClick(String province, String city, String area ,String citycode, String
                    areacode) {
                // TODO Auto-generated method stub

                mProvincecategoryno.setText(citycode);
                mCitycategoryno.setText(areacode);
                mCountry.setText(province);
                mProvince.setText(city);
                mCity.setText(area);
                //对象属性赋值
                if (mCountry.getText() != ""||mProvince.getText() != ""||mCity.getText() != ""){
                    //mAddVisitCustomer.setCustaddress(String.valueOf(mCustaddress_tv.getText()));
                }

            }
        });
    }

    private void setlocaladdress() {
        //ChangeSqlAddressPopwindow mChangeSqlAddressPopwindow = new ChangeSqlAddressPopwindow(getActivity(),userid);
        ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(getActivity());
        mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
        mChangeAddressPopwindow.showAtLocation(mContact_custaddress, Gravity.BOTTOM, 0, 0);
        mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

            @Override
            public void onClick(String province, String city, String area) {
                // TODO Auto-generated method stub
                mContact_country.setText(province);
                mContact_province.setText(city);
                mContact_city.setText(area);
                //对象属性赋值
                if (mContact_country.getText() != ""||mContact_province.getText() != ""||mContact_city.getText() != ""){
                    //mAddVisitCustomer.setCustaddress(String.valueOf(mCustaddress_tv.getText()));
                }

            }
        });
    }
}
