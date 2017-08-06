package com.cimc.zjly.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cimc.zjly.R;
import com.cimc.zjly.bean.IntentionPageItems;
import com.cimc.zjly.ui.holder.BaseHolder;
import com.cimc.zjly.ui.holder.IntentionHolder;
import com.cimc.zjly.utils.TimeUtils;
import com.cimc.zjly.utils.UIUtils;

import java.util.List;

/**
 * Created by lyw on 2017/8/6.
 */

public class IntentionListAdapter extends MyBaseAdapter<IntentionPageItems.PageInfoBean.ListBean> {
    public IntentionListAdapter(List<IntentionPageItems.PageInfoBean.ListBean> data) {
        super(data);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IntentionHolder holder;
        if (convertView == null) {
            convertView = UIUtils.inflate(R.layout.list_intention_item);
            holder = (IntentionHolder)getHolder(position);// 子类返回具体对象
            holder.mOpportsubject = (TextView) convertView.findViewById(R.id.opportsubject_tv);
            holder.mCustName = (TextView) convertView.findViewById(R.id.custName_tv);
            holder.mCurrentStageValue = (TextView) convertView.findViewById(R.id.currentStageValue_tv);
            holder.mOpportno = (TextView) convertView.findViewById(R.id.opportno_tv);
            holder.mPlanmoney = (TextView) convertView.findViewById(R.id.planmoney_tv);
            holder.mCreatedate = (TextView) convertView.findViewById(R.id.createdate_tv);
            holder.mProductcount = (TextView) convertView.findViewById(R.id.productcount_tv);
            holder.mPlansigndate = (TextView) convertView.findViewById(R.id.plansigndate_tv);
            holder.mProductid = (TextView) convertView.findViewById(R.id.productid_tv);

            // 3. 打一个标记tag
            convertView.setTag(holder);
        } else {
            holder = (IntentionHolder) convertView.getTag();
        }
        holder.mOpportsubject.setText(data.get(position).getOpportsubject());
        holder.mCustName.setText(data.get(position).getCustName());
        holder.mCurrentStageValue.setText(data.get(position).getCurrentStageValue());
        holder.mOpportno.setText(data.get(position).getOpportno());
        holder.mPlanmoney.setText(String.valueOf(data.get(position).getPlanmoney()));
        holder.mCreatedate.setText(TimeUtils.getTime(data.get(position).getCreatedate(),TimeUtils.DEFAULT_DATE_FORMAT));
        holder.mProductcount.setText(String.valueOf(data.get(position).getProductcount()));
        holder.mPlansigndate.setText(TimeUtils.getTime(data.get(position).getPlansigndate(),TimeUtils.DEFAULT_DATE_FORMAT));
        holder.mProductid.setText(data.get(position).getProductid());
        //  holder.setData(getItem(position));


        return convertView;
    }
    @Override
    public BaseHolder<IntentionPageItems.PageInfoBean.ListBean> getHolder(int position) {
        return new IntentionHolder();
    }


}
