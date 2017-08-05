package com.cimc.zjly.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cimc.zjly.R;
import com.cimc.zjly.bean.Linkmans;
import com.cimc.zjly.ui.holder.BaseHolder;
import com.cimc.zjly.ui.holder.LinkmanHolder;
import com.cimc.zjly.utils.UIUtils;

import java.util.List;

/**
 * Created by lyw on 2017/8/5.
 */

public class LinkmanListAdapter extends MyBaseAdapter<Linkmans> {
    public LinkmanListAdapter(List<Linkmans> data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinkmanHolder holder;
        if (convertView == null) {
            convertView = UIUtils.inflate(R.layout.list_linkman_item);
            holder = (LinkmanHolder)getHolder(position);// 子类返回具体对象
            holder.mPerosonname = (TextView) convertView.findViewById(R.id.tv_perosonname);
            holder.mCustname = (TextView) convertView.findViewById(R.id.tv_custname);
            holder.mConttel = (TextView) convertView.findViewById(R.id.tv_conttel);
            holder.mContmobile = (TextView) convertView.findViewById(R.id.tv_contmobile);
            // 3. 打一个标记tag
            convertView.setTag(holder);
        } else {
            holder = (LinkmanHolder) convertView.getTag();
        }
        holder.mPerosonname.setText(data.get(position).getPersonname());
        holder.mCustname.setText(data.get(position).getCustname());
        holder.mConttel.setText(data.get(position).getConttel());
        holder.mContmobile.setText(data.get(position).getContmobile());
        //  holder.setData(getItem(position));


        return convertView;
    }
    @Override
    public BaseHolder<Linkmans> getHolder(int position) {
        return new LinkmanHolder();
    }
}
