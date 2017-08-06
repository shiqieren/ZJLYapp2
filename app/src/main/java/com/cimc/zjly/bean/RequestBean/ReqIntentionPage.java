package com.cimc.zjly.bean.RequestBean;

/**
 * Created by lyw on 2017/8/6.
 */

public class ReqIntentionPage {
    public ReqIntentionPage(int pageNum, int pageSize, String orderBy, OpportUnityBean opportUnity) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.opportUnity = opportUnity;
    }

    /**
     * pageNum : 1
     * pageSize : 10
     * orderBy :
     * opportUnity : {"creator":555555928}
     */

    private int pageNum;
    private int pageSize;
    private String orderBy;
    private OpportUnityBean opportUnity;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public OpportUnityBean getOpportUnity() {
        return opportUnity;
    }

    public void setOpportUnity(OpportUnityBean opportUnity) {
        this.opportUnity = opportUnity;
    }

    public static class OpportUnityBean {
        public OpportUnityBean(int creator) {
            this.creator = creator;
        }

        /**
         * creator : 555555928
         */

        private int creator;

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }
    }
}
