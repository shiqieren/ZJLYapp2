package com.cimc.zjly.bean.RequestBean;

/**
 * Created by lyw on 2017/8/5.
 */

public class ReqVisitCusListPage {
    public ReqVisitCusListPage(int pageNum, int pageSize, String orderBy, CustomerVisitBean customerVisit) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.customerVisit = customerVisit;
    }

    /**
     * pageNum : 1
     * pageSize : 10
     * orderBy :
     * customerVisit : {"creator":555555928}
     */

    private int pageNum;
    private int pageSize;
    private String orderBy;
    private CustomerVisitBean customerVisit;

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

    public CustomerVisitBean getCustomerVisit() {
        return customerVisit;
    }

    public void setCustomerVisit(CustomerVisitBean customerVisit) {
        this.customerVisit = customerVisit;
    }

    public static class CustomerVisitBean {
        public CustomerVisitBean(int creator) {
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
