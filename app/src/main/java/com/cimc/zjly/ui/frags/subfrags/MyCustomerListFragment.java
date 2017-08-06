package com.cimc.zjly.ui.frags.subfrags;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cimc.zjly.R;
import com.cimc.zjly.Setting;
import com.cimc.zjly.bean.Customers;
import com.cimc.zjly.bean.ListPagers;
import com.cimc.zjly.bean.RequestBean.ReqCustomerLinkmanListPagers;
import com.cimc.zjly.bean.Result;
import com.cimc.zjly.bean.VehDataBean;
import com.cimc.zjly.ui.adapter.CustomerAdapter;
import com.cimc.zjly.ui.atys.ChoosePlateActivity;
import com.cimc.zjly.ui.atys.ReadEditDetailActivity;
import com.cimc.zjly.ui.callback.StringDialogCallback;
import com.cimc.zjly.ui.frags.BaseFragment;
import com.cimc.zjly.utils.GetAssert;
import com.cimc.zjly.utils.UIUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by lyw on 2017/8/5.
 */

public class MyCustomerListFragment extends BaseFragment {

    public static int RESULT = 1000;
    private static final String MY_CONTENT = "/cust/pageList";
    private static final String MYUNDER_CONTENT = "/cust/subPageList";
    private List<Customers> CustomerList;
    private View mView;
    private CustomerAdapter mAdapter;
    private PullToRefreshListView rflistview;
    private View headerView;
    //是否有下一页
    private boolean mHasNextPage;
    private int mNextPage;
    private int mPosition;


    public MyCustomerListFragment(int positon) {
        this.mPosition = positon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_pulltorefreshlistview,container,false);
        }
        //搜索框头布局
        headerView = inflater.inflate(R.layout.headview, rflistview, false);
        headerView.findViewById(R.id.search_et_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //放入putextra以识别不同的全搜索条件
                startActivityForResult(new Intent(UIUtils.getContext(), ChoosePlateActivity.class), RESULT);
            }
        });
        initView();
        initData();

        return mView;

    }
//返回结果执行UI更新
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            VehDataBean bean = data.getParcelableExtra("VehDataBean");
            if (bean != null) {
                Toast.makeText(getActivity(),"返回的结果"+bean.toString(),Toast.LENGTH_SHORT).show();
                // tv_result.setText("结果：" + bean.getVehnumber());
            }
        }

    }
    @Override
    public void initView() {
        rflistview = (PullToRefreshListView) mView.findViewById(R.id.pull_refresh_list);
        ListView lv = rflistview.getRefreshableView();
        lv.addHeaderView(headerView);
        rflistview.setRefreshing(true);
        rflistview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);//只允许加载更多
        rflistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if(mHasNextPage){
                    new GetDataTask(rflistview, mAdapter,CustomerList).execute();
                }else {
                    Toast.makeText(getActivity(),"没有更多数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rflistview.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(UIUtils.getContext(), "已经到底了", Toast.LENGTH_SHORT).show();
            }
        });
        rflistview.setScrollingWhileRefreshingEnabled(true);
        rflistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // if(position<VisCuslist.size()&&VisCuslist.size()>0){
                Toast.makeText(getActivity(),String.valueOf(position-2),Toast.LENGTH_SHORT).show();
                //下标越界判断
                Customers viscus = CustomerList.get(position-2);

                Toast.makeText(getActivity(),"custId="+String.valueOf(viscus.getCustid()),Toast
                        .LENGTH_SHORT).show();
                if (viscus != null) {
                    Intent intent = new Intent(getActivity(),
                            ReadEditDetailActivity.class);

                    intent.putExtra("custId", viscus.getCustid());
                    // intent.putExtra("cvid", 10);
                    intent.putExtra("editmode","readcustomer");
                    startActivity(intent);
                }else {

                }
                //  }

            }
        });
    }

    @Override
    public void initData() {

        /*本地测试数据*/
        Type userlistType = new TypeToken<Result<ListPagers<Customers>>>(){}.getType();
        Result<ListPagers<Customers>> status = new Gson().fromJson(GetAssert.getJson("Customer_pageList.json",
                getContext()), userlistType);
        CustomerList  = status.getData().getList();

        mAdapter = (CustomerAdapter) new CustomerAdapter(CustomerList);
        // 通知数据改变了
        mAdapter.notifyDataSetChanged();
        // 加载完成后停止刷新
        rflistview.onRefreshComplete();

        rflistview.setAdapter(mAdapter);
        /*2.1	获取客户列表（分页）post*/
        // 请求网络, 只有网络成功了才执行生成view
       /* String url = "";
        if (mPosition == 1){
                url = Setting.API_SERVER_URL + MYUNDER_CONTENT;
            }else {
                url = Setting.API_SERVER_URL + MY_CONTENT;
            }
             if (Setting.getCachedUID(UIUtils.getContext()) != null){
            int requid = Integer.valueOf(Setting.getCachedUID(UIUtils.getContext()));
        OkHttpUtils
                .postString()
                .url(url)
                .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                .addHeader("sessionKey","2")
                .content(new Gson().toJson(new ReqCustomerLinkmanListPagers(1,10,"",new
                ReqCustomerLinkmanListPagers.CustomerBean(String.valueOf(requid)))))
                ).mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringDialogCallback(getActivity()) {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                dismissDialog();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                             dismissDialog();
                                Type userlistType = new TypeToken<Result<ListPagers<Customers>>>(){}.getType();
                               Result<ListPagers<Customers>> status = new Gson().fromJson(response, userlistType);
                                CustomerList  =status.getData().getList();
                                mHasNextPage = (Boolean)status.getData().isHasNextPage();
                                mNextPage = status.getData().getNextPage();
                                mAdapter = (CustomerAdapter) new CustomerAdapter(CustomerList);
                                  // 通知数据改变了
                                mAdapter.notifyDataSetChanged();
                                // 加载完成后停止刷新
                                rflistview.onRefreshComplete();
                                rflistview.setAdapter(mAdapter);
                            }
                        }
                );
        }else {
            Toast.makeText(getContext(),"获取不到当前登录用户uid",Toast.LENGTH_SHORT).show();
        }

                */
    }

    //异步加载更多
    private class GetDataTask extends AsyncTask<Void, Void, String>
    {
        //异步内的列表，适配器，和数据
        private PullToRefreshListView mPullRefreshListView;
        private CustomerAdapter mAdapter;
        private List<Customers> mListItems;
        //构造方法传递原有值
        public GetDataTask(PullToRefreshListView listView,
                           CustomerAdapter adapter,List<Customers> listItems) {
            // 自动生成的构造函数存根
            mPullRefreshListView = listView;
            mAdapter = adapter;
            mListItems = listItems;
        }
        @Override
        protected String doInBackground(Void... params)
        {

       /* 2.1	获取客户列表（分页）post*/
            String url = "";
            if (mPosition == 1){
                url = Setting.API_SERVER_URL + MYUNDER_CONTENT;
            }else {
                url = Setting.API_SERVER_URL + MY_CONTENT;
            }
            if (Setting.getCachedUID(UIUtils.getContext()) != null){
                int requid = Integer.valueOf(Setting.getCachedUID(UIUtils.getContext()));
            OkHttpUtils
                    .postString()
                    .url(url)
                    .addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
                    .addHeader("sessionKey","2")
                    .content(new Gson().toJson(new ReqCustomerLinkmanListPagers(mNextPage,10,"",
                            new ReqCustomerLinkmanListPagers.CustomerBean(String.valueOf(requid))))
                    ).mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(
                            new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onResponse(String response, int id) {

                                }
                            }
                    );
            }else {
                Toast.makeText(getContext(),"获取不到当前登录用户uid",Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        { // 自动生成的方法存根
            super.onPostExecute(result);
          /*  //得到当前的模式
            PullToRefreshBase.Mode mode = mPullRefreshListView.getCurrentMode();
            //list,linklist,arraylist的加入数据add方法
            if(mode == PullToRefreshBase.Mode.PULL_FROM_START) {
                //刷新的数据，linklist双向链表特性
                // mListItems.addFirst();
            }
            else {
                //加载更多的数据
                // mListItems.addLast();
            }*/
            Type userlistType = new TypeToken<Result<ListPagers<Customers>>>(){}.getType();
            Result<ListPagers<Customers>> status = new Gson().fromJson(result, userlistType);
            mListItems.addAll(status.getData().getList());
            mHasNextPage = (Boolean)status.getData().isHasNextPage();
            mNextPage = status.getData().getNextPage();
            // 通知数据改变了
            mAdapter.notifyDataSetChanged();
            // 加载完成后停止刷新
            mPullRefreshListView.onRefreshComplete();
        }
    }
}
