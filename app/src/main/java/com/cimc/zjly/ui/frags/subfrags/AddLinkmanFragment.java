package com.cimc.zjly.ui.frags.subfrags;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.cimc.zjly.bean.RequestBean.AddLinkmanReq;
import com.cimc.zjly.bean.RequestBean.AddgetOwnerAllCustReq;
import com.cimc.zjly.bean.ResutList;
import com.cimc.zjly.bean.SelectCustomersItem;
import com.cimc.zjly.ui.adapter.MyCustomerListPopuAdapter;
import com.cimc.zjly.ui.callback.StringDialogCallback;
import com.cimc.zjly.ui.frags.BaseFragment;
import com.cimc.zjly.ui.widget.ChangeAddressPopwindow;
import com.cimc.zjly.ui.widget.MyOptionBottomDialog;
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
 * Created by lyw on 2017/8/6.
 */

public class AddLinkmanFragment extends BaseFragment implements View.OnClickListener {

    private AddLinkmanReq mAddLinkman;
    private View mView;
    private LinearLayout linelayoutall;
    //控件初始化
    private TextView mCreatedate;
    private TextView mCustname;
    private TextView mCustid;
    private LinearLayout mContact_custaddress;
    private TextView mContact_country;
    private TextView mContact_province;
    private TextView mContact_city;
    private EditText mPersonname;
    private EditText mConttel;
    private EditText mContmobile;
    private EditText mContact_familyaddress;
    private List<SelectCustomersItem> allResultlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAddLinkman = new AddLinkmanReq();
        mView = inflater.inflate(R.layout.fragment_addlinkmans_info,container,false);
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


        mCustname =(TextView) mView.findViewById(R.id.custname_tv);
        mCreatedate =(TextView) mView.findViewById(R.id.createdate_tv);
        mCustid =(TextView) mView.findViewById(R.id.custid_tv);
        mContact_country=(TextView)mView.findViewById(R.id.contact_country_tv);
        mContact_province=(TextView)mView.findViewById(R.id.contact_province_tv);
        mContact_city=(TextView)mView.findViewById(R.id.contact_city_tv);

        mPersonname=(EditText)mView.findViewById(R.id.personname_ev);
        mConttel=(EditText)mView.findViewById(R.id.conttel_ev);
        mContmobile=(EditText)mView.findViewById(R.id.contmobile_ev);
        mContact_familyaddress=(EditText)mView.findViewById(R.id.contact_familyaddress_ev);

        mContact_custaddress =(LinearLayout)mView.findViewById(R.id.contact_custaddress_tv);
        initView();
        initData();
        mView.findViewById(R.id.commit_addcusvisdata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comiitadddata();
            }
        });
        return mView;
    }

    private void comiitadddata() {
        mAddLinkman.setCustid(mCustid.getText().toString());
        mAddLinkman.setPersonname(mPersonname.getText().toString());
        mAddLinkman.setConttel(mConttel.getText().toString());
        mAddLinkman.setContmobile(mContmobile.getText().toString());
        mAddLinkman.setFamilyarea(mContact_province.getText().toString());
        mAddLinkman.setFamilycity(mContact_city.getText().toString());
        mAddLinkman.setFamilyaddress(mContact_familyaddress.getText().toString());
        String url = Setting.API_SERVER_URL + "/contact/addCont";
        OkHttpUtils
                .postString()
                .url(url)
                .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                .addHeader("sessionKey","2")
                .content(new Gson().toJson(mAddLinkman)
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
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //客户类别
            case R.id.custname_tv:
                String custTypetitileName = "请选择所属客户";
                getSelectCustomernameList();

                break;

            //联系人地址
            case R.id.contact_custaddress_tv:
                setlocaladdress();
                break;
        }
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
        mCustname.setOnClickListener(this);
        mContact_custaddress.setOnClickListener(this);
    }
    private void getSelectCustomernameList(){

        if (Setting.getCachedUID(UIUtils.getContext()) != null){
            int requid = Integer.valueOf(Setting.getCachedUID(UIUtils.getContext()));
            //网络请求  加载默认已有的字段
            String url = Setting.API_SERVER_URL + "/cust/list";
            OkHttpUtils
                    .postString()
                    .url(url)
                    .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                    .addHeader("sessionKey","2")
                    //获取当前登录用户的全部客户列表
                    .content(new Gson().toJson(new AddgetOwnerAllCustReq(requid)))
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(
                            new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(getContext(),"????"+e.toString(),Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Type userlistType = new TypeToken<ResutList<SelectCustomersItem>>(){}.getType();
                                    ResutList<SelectCustomersItem> status = new Gson().fromJson(response, userlistType);
                                    allResultlist= (ArrayList<SelectCustomersItem>)status.getData();
                                    //setadapter
                                    String titileName = "请选择客户名称";
                                    MyCustomerListPopuAdapter mAdapter = new MyCustomerListPopuAdapter(allResultlist);
                                    setPopuWindow(allResultlist,titileName,mCustname,mAdapter);

                                }
                            }
                    );
        }else {
            Toast.makeText(getContext(),"获取不到当前登录用户uid",Toast.LENGTH_SHORT).show();
        }



    }
    private void setPopuWindow(List<SelectCustomersItem> stringList, String titileName, final TextView tv, MyCustomerListPopuAdapter<SelectCustomersItem> adapter){
        final MyOptionBottomDialog optionBottomDialog = new MyOptionBottomDialog(getContext(), stringList,titileName,adapter);
        optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectCustomersItem thisobj = (SelectCustomersItem) parent.getItemAtPosition(position);

                // (SelectCustomersItem)parent.getItemAtPosition(position)
                tv.setText(thisobj.getCustname().toString());

                mCustid.setText(String.valueOf(thisobj.getCustid()));


                optionBottomDialog.dismiss();
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
