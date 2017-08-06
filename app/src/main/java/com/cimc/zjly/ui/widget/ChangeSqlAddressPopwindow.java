package com.cimc.zjly.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.cimc.zjly.R;
import com.cimc.zjly.bean.CustomerSelectSqlDataOption;
import com.cimc.zjly.bean.Result;
import com.cimc.zjly.ui.widget.wheelviewaddress.OnWheelChangedListener;
import com.cimc.zjly.ui.widget.wheelviewaddress.OnWheelScrollListener;
import com.cimc.zjly.ui.widget.wheelviewaddress.WheelView;
import com.cimc.zjly.ui.widget.wheelviewaddress.adapter.AbstractWheelTextAdapter1;
import com.cimc.zjly.utils.GetAssert;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cimc.zjly.utils.UIUtils.getContext;

public class ChangeSqlAddressPopwindow extends PopupWindow implements View.OnClickListener {

	private WheelView wvProvince;
	private WheelView wvCitys;
	private WheelView wvArea;

	private WheelView wvCityscode;
	private WheelView wvAreacode;

	private View lyChangeAddress;
	private View lyChangeAddressChild;
	private TextView btnSure;
	private TextView btnCancel;

	private Context context;
	private int mSelectMode;
	private JSONObject mJsonObj;
	/**
	 * 所有省
	 */
	private String[] mProvinceDatas;
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	private Map<String, String[]> mCitisDatasMapcode = new HashMap<String, String[]>();
	private Map<String, String[]> mAreaDatasMapcode = new HashMap<String, String[]>();

	private ArrayList<String> arrProvinces = new ArrayList<String>();
	private ArrayList<String> arrCitys = new ArrayList<String>();
	private ArrayList<String> arrAreas = new ArrayList<String>();

	private ArrayList<String> arrCityscode = new ArrayList<String>();
	private ArrayList<String> arrAreascode = new ArrayList<String>();

	private AddressTextAdapter provinceAdapter;
	private AddressTextAdapter cityAdapter;
	private AddressTextAdapter areaAdapter;

	private AddressTextAdapter cityAdaptercode;
	private AddressTextAdapter areaAdaptercode;

	private String strProvince;
	private String strCity;
	private String strArea;

	private String strCitycode;
	private String strAreacode;

	private OnAddressCListener onAddressCListener;

	private int maxsize = 14;
	private int minsize = 12;

//	public ChangeSqlAddressPopwindow(final Context context ,int userId) {
public ChangeSqlAddressPopwindow(final Context context) {
		super(context);
		this.context = context;
		View view=View.inflate(context, R.layout.edit_changeaddress_pop_layout,null);

		wvProvince = (WheelView) view.findViewById(R.id.wv_address_province);
		wvCitys = (WheelView) view.findViewById(R.id.wv_address_city);
		wvArea = (WheelView)view. findViewById(R.id.wv_address_area);

		wvCityscode = (WheelView) view.findViewById(R.id.wv_address_citycode);
		wvAreacode = (WheelView)view. findViewById(R.id.wv_address_areacode);

		lyChangeAddress = view.findViewById(R.id.ly_myinfo_changeaddress);
		lyChangeAddressChild = view.findViewById(R.id.ly_myinfo_changeaddress_child);
		btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView)view. findViewById(R.id.btn_myinfo_cancel);


		//设置SelectPicPopupWindow的View
		this.setContentView(view);
		//设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		//设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
		//设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		//设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimBottom);
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		//设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);

		lyChangeAddressChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

	//	initJsonData();
	//	initDatas(userId);
		initDatas();

		initProvinces();
		provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize, minsize);
		wvProvince.setVisibleItems(5);
		wvProvince.setViewAdapter(provinceAdapter);
		wvProvince.setCurrentItem(getProvinceItem(strProvince));

		initCitys(mCitisDatasMap.get(strProvince));
		cityAdapter = new AddressTextAdapter(context, arrCitys, getCityItem(strCity), maxsize, minsize);
		wvCitys.setVisibleItems(5);
		wvCitys.setViewAdapter(cityAdapter);
		wvCitys.setCurrentItem(getCityItem(strCity));

		initAreas(mAreaDatasMap.get(strCity));
		areaAdapter = new AddressTextAdapter(context, arrAreas, getAreaItem(strArea), maxsize, minsize);
		wvArea.setVisibleItems(5);
		wvArea.setViewAdapter(areaAdapter);
		wvArea.setCurrentItem(getAreaItem(strArea));

	//////////////////
	initCityscode(mCitisDatasMapcode.get(strProvince));
	cityAdaptercode = new AddressTextAdapter(context, arrCityscode, getCityItem(strCitycode), maxsize,
			minsize);
	wvCityscode.setVisibleItems(5);
	wvCityscode.setViewAdapter(cityAdaptercode);
	wvCityscode.setCurrentItem(getCityItem(strCitycode));

	initAreascode(mAreaDatasMapcode.get(strCitycode));
	areaAdaptercode = new AddressTextAdapter(context, arrAreascode, getAreaItem(strAreacode),
			maxsize,
			minsize);
	wvAreacode.setVisibleItems(5);
	wvAreacode.setViewAdapter(areaAdaptercode);
	wvAreacode.setCurrentItem(getAreaItem(strAreacode));

	/////////////////

		wvProvince.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				strProvince = currentText;
				setTextviewSize(currentText, provinceAdapter);

				String[] citys = mCitisDatasMap.get(currentText);
				initCitys(citys);
				cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize, minsize);
				wvCitys.setVisibleItems(5);
				wvCitys.setViewAdapter(cityAdapter);
				wvCitys.setCurrentItem(0);
				setTextviewSize("0", cityAdapter);

				//根据市，地区联动
				String[] areas = mAreaDatasMap.get(citys[0]);
				initAreas(areas);
				areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
				wvArea.setVisibleItems(5);
				wvArea.setViewAdapter(areaAdapter);
				wvArea.setCurrentItem(0);
				setTextviewSize("0", areaAdapter);

				///////////
				String[] cityscode = mCitisDatasMapcode.get(currentText);
				initCityscode(cityscode);
				cityAdaptercode = new AddressTextAdapter(context, arrCityscode, 0, maxsize,
						minsize);
				wvCityscode.setVisibleItems(5);
				wvCityscode.setViewAdapter(cityAdaptercode);
				wvCityscode.setCurrentItem(0);
				setTextviewSize("0", cityAdaptercode);

				//根据市，地区联动
				String[] areascode = mAreaDatasMapcode.get(citys[0]);
				initAreascode(areascode);
				areaAdaptercode = new AddressTextAdapter(context, arrAreascode, 0, maxsize, minsize);
				wvAreacode.setVisibleItems(5);
				wvAreacode.setViewAdapter(areaAdaptercode);
				wvAreacode.setCurrentItem(0);
				setTextviewSize("0", areaAdaptercode);
			}
		});

		wvProvince.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, provinceAdapter);
			}
		});

		wvCitys.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				strCity = currentText;
				setTextviewSize(currentText, cityAdapter);

				//根据市，地区联动
				String[] areas = mAreaDatasMap.get(currentText);
				initAreas(areas);
				areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
				wvArea.setVisibleItems(5);
				wvArea.setViewAdapter(areaAdapter);
				wvArea.setCurrentItem(0);
				setTextviewSize("0", areaAdapter);


			}
		});
	///<code></code>
	wvCityscode.addChangingListener(new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			String currentText = (String) cityAdaptercode.getItemText(wheel.getCurrentItem());
			strCitycode = currentText;
			setTextviewSize(currentText, cityAdaptercode);

			//根据市，地区联动
			String[] areascode = mAreaDatasMapcode.get(currentText);
			initAreascode(areascode);
			areaAdaptercode = new AddressTextAdapter(context, arrAreascode, 0, maxsize, minsize);
			wvAreacode.setVisibleItems(5);
			wvAreacode.setViewAdapter(areaAdaptercode);
			wvAreacode.setCurrentItem(0);
			setTextviewSize("0", areaAdaptercode);


		}
	});

		wvCitys.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, cityAdapter);
			}
		});

	///
	wvCityscode.addScrollingListener(new OnWheelScrollListener() {

		@Override
		public void onScrollingStarted(WheelView wheel) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			// TODO Auto-generated method stub
			String currentText = (String) cityAdaptercode.getItemText(wheel.getCurrentItem());
			setTextviewSize(currentText, cityAdaptercode);
		}
	});

		wvArea.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
				strArea = currentText;
				setTextviewSize(currentText, cityAdapter);
			}
		});
	///
	wvAreacode.addChangingListener(new OnWheelChangedListener() {

		@Override
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			// TODO Auto-generated method stub
			String currentText = (String) areaAdaptercode.getItemText(wheel.getCurrentItem());
			strAreacode = currentText;
			setTextviewSize(currentText, cityAdaptercode);
		}
	});

		wvArea.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, areaAdapter);
			}
		});
	////
	wvAreacode.addScrollingListener(new OnWheelScrollListener() {

		@Override
		public void onScrollingStarted(WheelView wheel) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			// TODO Auto-generated method stub
			String currentText = (String) areaAdaptercode.getItemText(wheel.getCurrentItem());
			setTextviewSize(currentText, areaAdaptercode);
		}
	});


	}


	private class AddressTextAdapter extends AbstractWheelTextAdapter1 {
		ArrayList<String> list;

		protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(14);
			} else {
				textvew.setTextSize(12);
			}
		}
	}

	public void setAddresskListener(OnAddressCListener onAddressCListener) {
		this.onAddressCListener = onAddressCListener;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSure) {
			if (onAddressCListener != null) {
				onAddressCListener.onClick(strProvince, strCity,strArea,strCitycode,strAreacode);
			}
		} else if (v == btnCancel) {

		} else if (v == lyChangeAddressChild) {
			return;
		} else {
//			dismiss();
		}
		dismiss();
	}

	/**
	 * 回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public interface OnAddressCListener {
		public void onClick(String province, String city, String area ,String citycode, String
				areacode );
	}



	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	//private void initDatas(int userId)
	private void initDatas()
	{
		//本地测试大区域list
		Type RegionType = new TypeToken<Result<CustomerSelectSqlDataOption>>(){}.getType();
		Result<CustomerSelectSqlDataOption> Regionstatus = new Gson().fromJson(GetAssert.getJson("cimccity.json", getContext()), RegionType);


		//网络请求
		List<CustomerSelectSqlDataOption.RegionBean> regionList = Regionstatus.getData().getRegion();
		mProvinceDatas = new String[regionList.size()];

		for(int i = 0; i < regionList.size(); i++){
			String province = regionList.get(i).getCategoryname();//中国
			strProvince = regionList.get(0).getCategoryname();
			mProvinceDatas[i] = province;
			List<CustomerSelectSqlDataOption.RegionBean.CateListBeanX> jsonCs = null;
			try
			{
				jsonCs = regionList.get(i).getCateList();
			} catch (Exception e1)
			{
				continue;
			}
			String[] mCitiesDatas = new String[jsonCs.size()];
			String[] mCitiesDatascode = new String[jsonCs.size()];
			for (int j = 0; j < jsonCs.size(); j++){
				String city = jsonCs.get(j).getCategoryname();//省会
				String citycode = jsonCs.get(j).getCategoryno();//code

				strCity =jsonCs.get(0).getCategoryname();
				strCitycode =jsonCs.get(0).getCategoryno();

				mCitiesDatas[j] = city;
				mCitiesDatascode[j] = citycode;

				List<CustomerSelectSqlDataOption.RegionBean.CateListBeanX.CateListBean> jsonAreas = null;
				try
				{

					jsonAreas = jsonCs.get(j).getCateList();
				} catch (Exception e)
				{
					continue;
				}
				String[] mAreasDatas = new String[jsonAreas.size()];// 当前市的所有区
				String[] mAreasDatascode = new String[jsonAreas.size()];// 当前市的所有区
				for (int k = 0; k < jsonAreas.size(); k++)
				{
					String area = jsonAreas.get(k).getCategoryname();// 区域的名称
					strArea =jsonAreas.get(0).getCategoryname();
					mAreasDatas[k] = area;
					String areacode = jsonAreas.get(k).getCategoryno();// 区域的名称
					strAreacode =jsonAreas.get(0).getCategoryno();
					mAreasDatascode[k] = areacode;
				}
				mAreaDatasMap.put(city, mAreasDatas);
				mAreaDatasMapcode.put(citycode, mAreasDatascode);
			}
			mCitisDatasMap.put(province, mCitiesDatas);
			mCitisDatasMapcode.put(province, mCitiesDatascode);
		}

		//网络请求
		/*String requid = Setting.getCachedUID(getContext());
		if (requid != ""){
			//网络请求  加载默认已有的字段
			String url = Setting.API_SERVER_URL + "/cust/getCustSelect";
			OkHttpUtils
					.post()
					.url(url)
					.addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
					.addHeader("sessionKey","2")
					//获取当前登录用户的全部客户列表
					.addParams("userId ",requid)
					.build()
					.execute(
							new StringCallback() {
								@Override
								public void onError(Call call, Exception e, int id) {
									Toast.makeText(getContext(),"????"+e.toString(),Toast.LENGTH_SHORT).show();

								}

								@Override
								public void onResponse(String response, int id) {
									Type RegionType = new TypeToken<Result<CustomerSelectSqlDataOption>>(){}.getType();
									Result<CustomerSelectSqlDataOption> Regionstatus = new Gson().fromJson(response, RegionType);


								}
							}
					);
		}else {
			Toast.makeText(getContext(),"获取不到当前登录用户uid",Toast.LENGTH_SHORT).show();
		}*/


////////////////////////////////////////////
		/*String url = Setting.API_SERVER_URL + "/cust/getCustSelect";
		OkHttpUtils
				.post()
				.url(url)
				.addHeader("checkTokenKey","2FD08ED0-E53B-48B1-B8E6-E6B4290A2770")
				.addHeader("sessionKey","2")
				.addParams("userId ",String.valueOf(userId))
				.build()
				.execute(
						new StringCallback() {
							@Override
							public void onError(Call call, Exception e, int id) {
								Toast.makeText(UIUtils.getContext(),"客户地址请求异常："+e.toString(),Toast.LENGTH_SHORT).show();

							}

							@Override
							public void onResponse(String response, int id) {
								//大区域list
								Type RegionType = new TypeToken<Result<CustomerSelectSqlDataOption>>(){}.getType();
								Result<CustomerSelectSqlDataOption> Regionstatus = new Gson().fromJson(response, RegionType);
								List<CustomerSelectSqlDataOption.RegionBean> regionList = Regionstatus.getData().getRegion();
								mProvinceDatas = new String[regionList.size()];
								for(int i = 0; i < regionList.size(); i++){
									String province = regionList.get(i).getCategoryname();//中国
									mProvinceDatas[i] = province;
									List<CustomerSelectSqlDataOption.RegionBean.CateListBeanX> jsonCs = null;
									try
									{
										jsonCs = regionList.get(i).getCateList();
									} catch (Exception e1)
									{
										continue;
									}
									String[] mCitiesDatas = new String[jsonCs.size()];
									for (int j = 0; j < jsonCs.size(); j++){
										String city = jsonCs.get(j).getCategoryname();//省会
										mCitiesDatas[j] = city;

										List<CustomerSelectSqlDataOption.RegionBean.CateListBeanX.CateListBean> jsonAreas = null;
										try
										{

											jsonAreas = jsonCs.get(j).getCateList();
										} catch (Exception e)
										{
											continue;
										}
										String[] mAreasDatas = new String[jsonAreas.size()];// 当前市的所有区
										for (int k = 0; k < jsonAreas.size(); k++)
										{
											String area = jsonAreas.get(k).getCategoryname();// 区域的名称
											mAreasDatas[k] = area;
										}
										mAreaDatasMap.put(city, mAreasDatas);
									}
									mCitisDatasMap.put(province, mCitiesDatas);

								}


							}
						}
				);*/
		////////////////
	}

	/**
	 * 初始化省会
	 */
	public void initProvinces() {
		int length = mProvinceDatas.length;
		for (int i = 0; i < length; i++) {
			arrProvinces.add(mProvinceDatas[i]);
		}
	}

	/**
	 * 根据省会，生成该省会的所有城市
	 * 
	 * @param citys
	 */
	public void initCitys(String[] citys) {
		if (citys != null) {
			arrCitys.clear();
			int length = citys.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(citys[i]);
			}
		} else {
			String[] city = mCitisDatasMap.get(strProvince);
			arrCitys.clear();
			int length = city.length;
			for (int i = 0; i < length; i++) {
				arrCitys.add(city[i]);
			}
		}
		if (arrCitys != null && arrCitys.size() > 0
				&& !arrCitys.contains(strCity)) {
			strCity = arrCitys.get(0);
		}
	}

	public void initCityscode(String[] cityscode) {
		if (cityscode != null) {
			arrCityscode.clear();
			int length = cityscode.length;
			for (int i = 0; i < length; i++) {
				arrCityscode.add(cityscode[i]);
			}
		} else {
			String[] citycode = mCitisDatasMapcode.get(strProvince);
			arrCityscode.clear();
			int length = citycode.length;
			for (int i = 0; i < length; i++) {
				arrCityscode.add(citycode[i]);
			}
		}
		if (arrCityscode != null && arrCityscode.size() > 0
				&& !arrCityscode.contains(strCitycode)) {
			strCitycode = arrCityscode.get(0);
		}
	}

	/**
	 * 根据城市，生成该城市的所有地区
	 *
	 * @param areas
	 */
	public void initAreas(String[] areas) {
		if (areas != null) {
			arrAreas.clear();
			int length = areas.length;
			for (int i = 0; i < length; i++) {
				arrAreas.add(areas[i]);
			}
		} else {
			String[] area = mAreaDatasMap.get(strCity);
			arrAreas.clear();
			int length = area.length;
			for (int i = 0; i < length; i++) {
				arrAreas.add(area[i]);
			}
		}
		if (arrAreas != null && arrAreas.size() > 0
				&& !arrAreas.contains(strArea)) {
			strArea = arrAreas.get(0);
		}
	}

	public void initAreascode(String[] areascode) {
		if (areascode != null) {
			arrAreascode.clear();
			int length = areascode.length;
			for (int i = 0; i < length; i++) {
				arrAreascode.add(areascode[i]);
			}
		} else {
			String[] areacode = mAreaDatasMapcode.get(strCitycode);
			arrAreascode.clear();
			int length = areacode.length;
			for (int i = 0; i < length; i++) {
				arrAreascode.add(areacode[i]);
			}
		}
		if (arrAreascode != null && arrAreascode.size() > 0
				&& !arrAreascode.contains(strAreacode)) {
			strAreacode = arrAreascode.get(0);
		}
	}

	/**
	 * 初始化地点
	 * 
	 * @param province
	 * @param city
	 */
	public void setAddress(String province, String city, String area, String citycode, String
			areacode) {
		if (province != null && province.length() > 0) {
			this.strProvince = province;
		}
		if (city != null && city.length() > 0) {
			this.strCity = city;
		}

		if (area != null && area.length() > 0) {
			this.strArea = area;
		}
		if (citycode != null && citycode.length() > 0) {
			this.strCitycode = citycode;
		}

		if (areacode != null && areacode.length() > 0) {
			this.strAreacode = areacode;
		}
	}

	/**
	 * 返回省会索引，没有就返回默认“广东”
	 * 
	 * @param province
	 * @return
	 */
	public int getProvinceItem(String province) {
		int size = arrProvinces.size();
		int provinceIndex = 0;
		boolean noprovince = true;
		for (int i = 0; i < size; i++) {
			if (province.equals(arrProvinces.get(i))) {
				noprovince = false;
				return provinceIndex;
			} else {
				provinceIndex++;
			}
		}
		if (noprovince) {
			strProvince = "无";
			return 0;
		}
		return provinceIndex;
	}

	/**
	 * 得到城市索引，没有返回默认“深圳”
	 * 
	 * @param city
	 * @return
	 */
	public int getCityItem(String city) {
		int size = arrCitys.size();
		int cityIndex = 0;
		boolean nocity = true;
		for (int i = 0; i < size; i++) {
			System.out.println(arrCitys.get(i));
			if (city.equals(arrCitys.get(i))) {
				nocity = false;
				return cityIndex;
			} else {
				cityIndex++;
			}
		}
		if (nocity) {
			strCity = "无";
			return 2;
		}
		return cityIndex;
	}

	/**
	 * 得到地区索引，没有返回默认“福田区”
	 *
	 * @param area
	 * @return
	 */
	public int getAreaItem(String area) {
		int size = arrAreas.size();
		int areaIndex = 0;
		boolean noarea = true;
		for (int i = 0; i < size; i++) {
			System.out.println(arrAreas.get(i));
			if (area.equals(arrAreas.get(i))) {
				noarea = false;
				return areaIndex;
			} else {
				areaIndex++;
			}
		}
		if (noarea) {
			strArea = "无";
			return 1;
		}
		return areaIndex;
	}


}