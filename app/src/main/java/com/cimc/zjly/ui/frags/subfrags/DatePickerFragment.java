package com.cimc.zjly.ui.frags.subfrags;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by lyw on 2017/8/2.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener  {
    int _year=1970;
    int _month=0;
    int _day=0;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用当前日期作为日期选择对话框的默认值
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // 创建日期选择对话框的一个实例并返回它
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        _year=year;
        _month=monthOfYear+1;
        _day=dayOfMonth;
    }

    private String getValue(){
        return _year+"-"+_month+"-"+_day;
    }


}
