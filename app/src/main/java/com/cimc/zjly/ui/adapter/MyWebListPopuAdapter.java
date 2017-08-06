package com.cimc.zjly.ui.adapter;

import com.cimc.zjly.bean.CustomerSelectSqlDataOption;
import com.cimc.zjly.ui.holder.BaseHolder;
import com.cimc.zjly.ui.holder.PopuLinkmanListHolder;
import com.cimc.zjly.ui.holder.PopuWebListHolder;

import java.util.List;

/**
 * Created by lyw on 2017/8/2.
 */

public class MyWebListPopuAdapter extends MyBaseAdapter {

    public MyWebListPopuAdapter(List<CustomerSelectSqlDataOption.WebBean> data) {
        super(data);
    }

    @Override
    public BaseHolder getHolder(int position) {
        return new PopuWebListHolder();
    }
}
