package com.cimc.zjly.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.cimc.zjly.R;
import com.cimc.zjly.bean.CustomerSelectSqlDataOption;
import com.cimc.zjly.utils.UIUtils;

/**
 * Created by lyw on 2017/8/2.
 */

public class PopuCusTypeListHolder extends BaseHolder<CustomerSelectSqlDataOption.CustTypeBean> {



    private TextView tvName;
    private TextView tvVisiblecode;

    @Override
    public View initView() {
        // 1. 加载布局
        View view = UIUtils.inflate(R.layout.list_allcustorselect_item);
        // 2. 初始化控件
        tvVisiblecode = (TextView) view.findViewById(R.id.tvVisible_code);
        tvName = (TextView) view.findViewById(R.id.visit_name);



        return view;
    }

    @Override
    public void refreshView(CustomerSelectSqlDataOption.CustTypeBean data) {
        tvName.setText(data.getCodevalue().toString());
        tvVisiblecode.setText(String.valueOf(data.getCodeid()));
    }


}
