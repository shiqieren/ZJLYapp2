package com.cimc.zjly.ui.adapter;

import android.content.Context;

import com.cimc.zjly.bean.SelectCustomersItem;
import com.cimc.zjly.ui.holder.BaseHolder;
import com.cimc.zjly.ui.holder.PopuCustomerListHolder;

import java.util.List;

/**
 * Created by lyw on 2017/8/2.
 */

public class MyCustomerListPopuAdapter<SelectCustomersItem> extends MyBaseAdapter {

    public MyCustomerListPopuAdapter(List<SelectCustomersItem> data) {
        super(data);
    }

    @Override
    public BaseHolder getHolder(int position) {
        return new PopuCustomerListHolder();
    }
}
