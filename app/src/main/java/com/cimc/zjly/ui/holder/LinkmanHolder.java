package com.cimc.zjly.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.cimc.zjly.R;
import com.cimc.zjly.bean.Linkmans;
import com.cimc.zjly.utils.UIUtils;

/**
 * Created by lyw on 2017/8/5.
 */

public class LinkmanHolder extends BaseHolder<Linkmans>{

    public TextView mPerosonname;
    public TextView mCustname;
    public TextView mConttel;
    public TextView mContmobile;

    @Override
    public View initView() {
        // 1. 加载布局
        View view = UIUtils.inflate(R.layout.list_linkman_item);
        // 2. 初始化控件
        mPerosonname = (TextView) view.findViewById(R.id.tv_perosonname);
        mCustname = (TextView) view.findViewById(R.id.tv_custname);
        mConttel = (TextView) view.findViewById(R.id.tv_conttel);
        mContmobile = (TextView) view.findViewById(R.id.tv_contmobile);
        return view;
    }

    @Override
    public void refreshView(Linkmans data) {
            mPerosonname.setText(data.getPersonname());
             mCustname.setText(data.getCustname());
             mConttel.setText(data.getConttel());
             mContmobile.setText(data.getContmobile());
    }
}
