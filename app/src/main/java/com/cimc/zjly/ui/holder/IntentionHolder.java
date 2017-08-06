package com.cimc.zjly.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.cimc.zjly.R;
import com.cimc.zjly.bean.IntentionPageItems;
import com.cimc.zjly.utils.TimeUtils;
import com.cimc.zjly.utils.UIUtils;

/**
 * Created by lyw on 2017/8/6.
 */

public class IntentionHolder extends BaseHolder<IntentionPageItems.PageInfoBean.ListBean> {
    public TextView mOpportsubject;
    public TextView mCustName;
    public TextView mCurrentStageValue;
    public TextView mOpportno;
    public TextView mPlanmoney;
    public TextView mCreatedate;
    public TextView mProductcount;
    public TextView mPlansigndate;
    public TextView mProductid;

    @Override
    public View initView() {
        // 1. 加载布局
        View view = UIUtils.inflate(R.layout.list_intention_item);
        // 2. 初始化控件
        mOpportsubject = (TextView) view.findViewById(R.id.opportsubject_tv);
        mCustName = (TextView) view.findViewById(R.id.custName_tv);
        mCurrentStageValue = (TextView) view.findViewById(R.id.currentStageValue_tv);
        mOpportno = (TextView) view.findViewById(R.id.opportno_tv);
        mPlanmoney = (TextView) view.findViewById(R.id.planmoney_tv);
        mCreatedate = (TextView) view.findViewById(R.id.createdate_tv);
        mProductcount = (TextView) view.findViewById(R.id.productcount_tv);
        mPlansigndate = (TextView) view.findViewById(R.id.plansigndate_tv);
        mProductid = (TextView) view.findViewById(R.id.productid_tv);

        return view;
    }

    @Override
    public void refreshView(IntentionPageItems.PageInfoBean.ListBean data) {
        mOpportsubject.setText(data.getOpportsubject());
        mCustName.setText(data.getCustName());
        mCurrentStageValue.setText(data.getCurrentstage());
        mOpportno.setText(data.getOpportno());
        mPlanmoney.setText(Integer.valueOf(data.getPlanmoney()));
        mCreatedate.setText(TimeUtils.getTime(data.getCreatedate(),TimeUtils.DEFAULT_DATE_FORMAT));
        mProductcount.setText(data.getProductcount().toString());
        mPlansigndate.setText(TimeUtils.getTime(data.getPlansigndate(),TimeUtils.DEFAULT_DATE_FORMAT));
        mProductid.setText(data.getProductid());

    }
}
