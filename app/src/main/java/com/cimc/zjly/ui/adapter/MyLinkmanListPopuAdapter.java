package com.cimc.zjly.ui.adapter;

import com.cimc.zjly.ui.holder.BaseHolder;
import com.cimc.zjly.ui.holder.PopuCustomerListHolder;
import com.cimc.zjly.ui.holder.PopuLinkmanListHolder;

import java.util.List;

/**
 * Created by lyw on 2017/8/2.
 */

public class MyLinkmanListPopuAdapter<SelectLinkmanItem> extends MyBaseAdapter {

    public MyLinkmanListPopuAdapter(List<SelectLinkmanItem> data) {
        super(data);
    }

    @Override
    public BaseHolder getHolder(int position) {
        return new PopuLinkmanListHolder();
    }
}
