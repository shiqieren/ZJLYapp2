package com.cimc.zjly.ui.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

/**
 * Created by lyw on 2017/8/2.
 */

public class MyDatePickerDialog extends DatePickerDialog {
    public MyDatePickerDialog (Context context, OnDateSetListener callBack,
                               int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);

        this.setTitle("选择任务的日期");
        this.setButton2("取消", (OnClickListener)null);
        this.setButton("确定", this);  //setButton和this参数组合表示这个按钮是确定按钮

    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        this.setTitle("选择任务的日期");
    }
}
