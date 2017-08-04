package com.cimc.zjly.ui.callback;

import android.content.Context;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lyw on 2017/8/3.
 */

public abstract class StringDialogCallback extends Callback<String>
{
    private  Context mContext;
    private  SpotsDialog mDialog;
    public StringDialogCallback(Context context) {
        mContext = context;
        mDialog = new SpotsDialog(mContext,"拼命加载中...");
    }


    public  void showDialog(){
        mDialog.show();
    }

    public  void dismissDialog(){
        mDialog.dismiss();
    }


    public void setLoadMessage(int resId){
        mDialog.setMessage(mContext.getString(resId));
    }
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request, int id)
    {
        showDialog();
    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter(int id)
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress, long total , int id)
    {

    }

    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }




}