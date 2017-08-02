package com.cimc.zjly.ui.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cimc.zjly.ui.holder.BaseHolder;


/**
 *我的指导案例adapter
 */
public abstract class ExampleAdapter<T> extends BaseAdapter {

	//定义两个int常量标记不同的Item视图

	public static final int SEARCH_ITEM = 0;

	public static final int NORMAL_ITEM = 1;

	private List<T> data;
	
	public ExampleAdapter(List<T> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size()+1;// 增加加载更多布局数量,加一个头布局

	}
	@Override
	public int getViewTypeCount() {
		return 2;//两种视图类型
	}
	//根据position设置对应的item是否可用，即是否能接收UI事件

	@Override
	public int getItemViewType(int position) {
		if(position==0){
			return SEARCH_ITEM;
		}else {
			return getInnerType(position);
		}
	}
	// 子类可以重写此方法来更改返回的布局类型
	public int getInnerType(int position) {
		return 0;// 默认就是普通类型
	}


	@Override
	public boolean isEnabled(int position) {
		return super.isEnabled(position);
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder = null;
		if (convertView == null) {
			// 1. 判断getItemViewType(position)加载不同布局文件
			// 2. 初始化控件 holder减少findViewById
			// 3. 打一个标记tag
				holder = getHolder(position);// 子类返回具体对象
				if(getItemViewType(position) == SEARCH_ITEM){
					//1:holder = new SearchHolder();
					//2:convertView = View.inflate()布局
					//3:holder属性控件=convertView.findViewById此处可获取控件对象
				}else{
					//子类重写的获得
					//1:holder = getHolder(position);
					//2:convertView = View.inflate()布局
					//3:holder属性控件=convertView.findViewById此处可获取控件对象
				}
				//打个holder标记
			convertView.setTag(holder);
		} else {
			//复用已经打了标记的
			holder = (BaseHolder) convertView.getTag();
		}
		holder.setData(getItem(position));
		//return holder.getRootView();
		return convertView;
	}



	// 返回当前页面的holder对象, 必须子类实现
	public abstract BaseHolder<T> getHolder(int position);


}
