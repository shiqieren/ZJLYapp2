package com.cimc.zjly.ui.frags.subfrags;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cimc.zjly.R;
import com.cimc.zjly.Setting;
import com.cimc.zjly.bean.IntentionPageItems;
import com.cimc.zjly.bean.ListPagers;
import com.cimc.zjly.bean.RequestBean.ReqIntentionPage;
import com.cimc.zjly.bean.Result;
import com.cimc.zjly.ui.adapter.IntentionListAdapter;
import com.cimc.zjly.ui.atys.ChoosePlateActivity;
import com.cimc.zjly.ui.atys.ReadEditDetailActivity;
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
 * Created by lyw on 2017/8/6.
 */

public class MyIntentionFragment extends Fragment {
    public static int RESULT = 1000;
    private List<IntentionPageItems.PageInfoBean.ListBean> mIntentionList;
    private View mView;
    private IntentionListAdapter mAdapter;
    private PullToRefreshListView rflistview;
    private View headerView;
    //是否有下一页
    private boolean mHasNextPage;
    private int mNextPage;

    private static final String MY_CONTENT = "/opportUnit/pageList";
    private static final String MYUNDER_CONTENT = "/opportUnit/subPageList";
    private int mPosition;

    public MyIntentionFragment(int position) {
        this.mPosition = position;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_pulltorefreshlistview,container,false);
        }
        //更改搜索头,为总结
        headerView = inflater.inflate(R.layout.intentionheadview, rflistview, false);



        initView();
        initData();

        return mView;

    }

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
                    new GetDataTask(rflistview, mAdapter,mIntentionList).execute();
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


            }
        });
    }

    public void initData() {

        /*本地测试数据*/
        Type userlistType = new TypeToken<Result<IntentionPageItems>>(){}.getType();
        Result<IntentionPageItems> status = new Gson().fromJson(GetAssert.getJson("Itenttion_list.json",
                getContext()), userlistType);
        mIntentionList  = status.getData().getPageInfo().getList();

        mAdapter = (IntentionListAdapter) new IntentionListAdapter(mIntentionList);
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
                .content(new Gson().toJson(new ReqCustomerLinkmanListPagers(1,10,"",new ReqCustomerLinkmanListPagers.CustomerBean(String.valueOf(requid)))))
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
                                Type userlistType = new TypeToken<Result<ListPagers<Linkmans>>>(){}.getType();
                               Result<ListPagers<Linkmans>> status = new Gson().fromJson(response, userlistType);
                                mLinkmansList  =status.getData().getList();
                                mHasNextPage = (Boolean)status.getData().isHasNextPage();
                                mNextPage = status.getData().getNextPage();
                                mAdapter = (LinkmanListAdapter) new LinkmanListAdapter(mLinkmansList);
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
        private IntentionListAdapter mAdapter;
        private List<IntentionPageItems.PageInfoBean.ListBean> mListItems;
        //构造方法传递原有值
        public GetDataTask(PullToRefreshListView listView,
                           IntentionListAdapter adapter,List<IntentionPageItems.PageInfoBean.ListBean> listItems) {
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
                        .content(new Gson().toJson(new ReqIntentionPage(mNextPage,10,"",
                                new ReqIntentionPage.OpportUnityBean(requid)))
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
            Type userlistType = new TypeToken<Result<IntentionPageItems>>(){}.getType();
            Result<IntentionPageItems> status = new Gson().fromJson(result, userlistType);
            mListItems.addAll(status.getData().getPageInfo().getList());
            mHasNextPage = (Boolean)status.getData().getPageInfo().isHasNextPage();
            mNextPage = status.getData().getPageInfo().getNextPage();
            // 通知数据改变了
            mAdapter.notifyDataSetChanged();
            // 加载完成后停止刷新
            mPullRefreshListView.onRefreshComplete();
        }
    }
}
