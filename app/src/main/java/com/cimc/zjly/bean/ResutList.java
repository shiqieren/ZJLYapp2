package com.cimc.zjly.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyw on 2017/8/2.
 */

public class ResutList<T> implements Serializable {

    /**
     * data : [{},{},{},{},{},{}]
     * msg :
     * success : true
     */

    private String msg;
    private boolean success;
    private List<T> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
