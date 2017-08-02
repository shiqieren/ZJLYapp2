package com.cimc.zjly.bean.RequestBean;

/**
 * 当前所有者
 * Created by lyw on 2017/8/2.
 */

public class OwnerAllCustReq {
    public OwnerAllCustReq(int owner) {
        this.owner = owner;
    }
    /**
     * owner : 555555754
     */

    private int owner;

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
