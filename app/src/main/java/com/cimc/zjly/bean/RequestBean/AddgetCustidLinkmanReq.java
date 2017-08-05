package com.cimc.zjly.bean.RequestBean;

/**
 * Created by lyw on 2017/8/2.
 */

public class AddgetCustidLinkmanReq {
    public AddgetCustidLinkmanReq(String custid) {
        this.custid = custid;
    }

    /**
     * custid : 1542
     */

    private String custid;

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }
}
