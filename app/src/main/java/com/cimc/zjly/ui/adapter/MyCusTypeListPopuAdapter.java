package com.cimc.zjly.ui.adapter;

import com.cimc.zjly.bean.CustomerSelectSqlDataOption;
import com.cimc.zjly.ui.holder.BaseHolder;
import com.cimc.zjly.ui.holder.PopuCusTypeListHolder;

import java.util.List;

/**
 * Created by lyw on 2017/8/2.
 */

public class MyCusTypeListPopuAdapter extends MyBaseAdapter {

    public MyCusTypeListPopuAdapter(List<CustomerSelectSqlDataOption.CustTypeBean> data) {
        super(data);
    }

    @Override
    public BaseHolder getHolder(int position) {
        return new PopuCusTypeListHolder();
    }
}
