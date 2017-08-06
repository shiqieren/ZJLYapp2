package com.cimc.zjly.bean;

import java.util.List;

/**
 * Created by lyw on 2017/8/6.
 */

public class IntentionPageItems {

    /**
     * opportAmountTotal : 0
     * opportCount : 0
     * orderAmountTotal : 0
     * orderCount : 0
     * pageInfo : {"endRow":10,"firstPage":1,"hasNextPage":true,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":false,"lastPage":8,"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":2,"pageNum":1,"pageSize":10,"pages":20,"prePage":0,"size":10,"startRow":1,"total":197,"list":[{"cityName":"盘锦市","companyid":null,"contactId":null,"createdate":1491837892000,"creator":555555924,"currency":"RMB","currentStageValue":"已转订单合同","currentstage":"CS03","custName":"锦州双盛运输有限公司","custid":6530,"custstatus":null,"detail":"CLY9407GYYD","modifyby":null,"modifydate":null,"opportid":3,"opportno":"17-000001","opportsubject":"购买液罐车","opporttype":"OT01","ordersource":null,"orgid":null,"paymentmethod":"PAM01","planmoney":400000,"plansigndate":1491782400000,"possibility":"PS03","possibilityValue":"高","productCategoryName":"液罐车","productcategory":"PC01","productcount":null,"productid":"CLY9407GYYD","productvariety":"PV02","remark":"智汇平台生产系统第一次试运行","stage":"ST05","stageValue":"生成合同","status":"T","unitid":null,"userName":"王岳宪"},{"cityName":"湖州市","companyid":null,"contactId":null,"createdate":1492590212000,"creator":555555821,"currency":"RMB","currentStageValue":"已转订单合同","currentstage":"CS03","custName":"湖州恒信汽车贸易有限公司","custid":6539,"custstatus":null,"detail":null,"modifyby":null,"modifydate":null,"opportid":7,"opportno":"17-000004","opportsubject":"购买液罐车-测试","opporttype":"OT01","ordersource":null,"orgid":null,"paymentmethod":"PAM01","planmoney":500000,"plansigndate":1493769600000,"possibility":"PS03","possibilityValue":"高","productCategoryName":"液罐车","productcategory":"PC01","productcount":null,"productid":"Z000","productvariety":"PV02","remark":null,"stage":"ST01","stageValue":"初期跟踪","status":"T","unitid":null,"userName":"解芳"}]}
     */

    private int opportAmountTotal;
    private int opportCount;
    private int orderAmountTotal;
    private int orderCount;
    private PageInfoBean pageInfo;

    public int getOpportAmountTotal() {
        return opportAmountTotal;
    }

    public void setOpportAmountTotal(int opportAmountTotal) {
        this.opportAmountTotal = opportAmountTotal;
    }

    public int getOpportCount() {
        return opportCount;
    }

    public void setOpportCount(int opportCount) {
        this.opportCount = opportCount;
    }

    public int getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(int orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PageInfoBean {
        /**
         * endRow : 10
         * firstPage : 1
         * hasNextPage : true
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : false
         * lastPage : 8
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * nextPage : 2
         * pageNum : 1
         * pageSize : 10
         * pages : 20
         * prePage : 0
         * size : 10
         * startRow : 1
         * total : 197
         * list : [{"cityName":"盘锦市","companyid":null,"contactId":null,"createdate":1491837892000,"creator":555555924,"currency":"RMB","currentStageValue":"已转订单合同","currentstage":"CS03","custName":"锦州双盛运输有限公司","custid":6530,"custstatus":null,"detail":"CLY9407GYYD","modifyby":null,"modifydate":null,"opportid":3,"opportno":"17-000001","opportsubject":"购买液罐车","opporttype":"OT01","ordersource":null,"orgid":null,"paymentmethod":"PAM01","planmoney":400000,"plansigndate":1491782400000,"possibility":"PS03","possibilityValue":"高","productCategoryName":"液罐车","productcategory":"PC01","productcount":null,"productid":"CLY9407GYYD","productvariety":"PV02","remark":"智汇平台生产系统第一次试运行","stage":"ST05","stageValue":"生成合同","status":"T","unitid":null,"userName":"王岳宪"},{"cityName":"湖州市","companyid":null,"contactId":null,"createdate":1492590212000,"creator":555555821,"currency":"RMB","currentStageValue":"已转订单合同","currentstage":"CS03","custName":"湖州恒信汽车贸易有限公司","custid":6539,"custstatus":null,"detail":null,"modifyby":null,"modifydate":null,"opportid":7,"opportno":"17-000004","opportsubject":"购买液罐车-测试","opporttype":"OT01","ordersource":null,"orgid":null,"paymentmethod":"PAM01","planmoney":500000,"plansigndate":1493769600000,"possibility":"PS03","possibilityValue":"高","productCategoryName":"液罐车","productcategory":"PC01","productcount":null,"productid":"Z000","productvariety":"PV02","remark":null,"stage":"ST01","stageValue":"初期跟踪","status":"T","unitid":null,"userName":"解芳"}]
         */

        private int endRow;
        private int firstPage;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int lastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<Integer> navigatepageNums;
        private List<ListBean> list;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

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

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cityName : 盘锦市
             * companyid : null
             * contactId : null
             * createdate : 1491837892000
             * creator : 555555924
             * currency : RMB
             * currentStageValue : 已转订单合同
             * currentstage : CS03
             * custName : 锦州双盛运输有限公司
             * custid : 6530
             * custstatus : null
             * detail : CLY9407GYYD
             * modifyby : null
             * modifydate : null
             * opportid : 3
             * opportno : 17-000001
             * opportsubject : 购买液罐车
             * opporttype : OT01
             * ordersource : null
             * orgid : null
             * paymentmethod : PAM01
             * planmoney : 400000
             * plansigndate : 1491782400000
             * possibility : PS03
             * possibilityValue : 高
             * productCategoryName : 液罐车
             * productcategory : PC01
             * productcount : null
             * productid : CLY9407GYYD
             * productvariety : PV02
             * remark : 智汇平台生产系统第一次试运行
             * stage : ST05
             * stageValue : 生成合同
             * status : T
             * unitid : null
             * userName : 王岳宪
             */

            private String cityName;
            private Object companyid;
            private Object contactId;
            private long createdate;
            private int creator;
            private String currency;
            private String currentStageValue;
            private String currentstage;
            private String custName;
            private int custid;
            private Object custstatus;
            private String detail;
            private Object modifyby;
            private Object modifydate;
            private int opportid;
            private String opportno;
            private String opportsubject;
            private String opporttype;
            private Object ordersource;
            private Object orgid;
            private String paymentmethod;
            private int planmoney;
            private long plansigndate;
            private String possibility;
            private String possibilityValue;
            private String productCategoryName;
            private String productcategory;
            private Object productcount;
            private String productid;
            private String productvariety;
            private String remark;
            private String stage;
            private String stageValue;
            private String status;
            private Object unitid;
            private String userName;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public Object getCompanyid() {
                return companyid;
            }

            public void setCompanyid(Object companyid) {
                this.companyid = companyid;
            }

            public Object getContactId() {
                return contactId;
            }

            public void setContactId(Object contactId) {
                this.contactId = contactId;
            }

            public long getCreatedate() {
                return createdate;
            }

            public void setCreatedate(long createdate) {
                this.createdate = createdate;
            }

            public int getCreator() {
                return creator;
            }

            public void setCreator(int creator) {
                this.creator = creator;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public String getCurrentStageValue() {
                return currentStageValue;
            }

            public void setCurrentStageValue(String currentStageValue) {
                this.currentStageValue = currentStageValue;
            }

            public String getCurrentstage() {
                return currentstage;
            }

            public void setCurrentstage(String currentstage) {
                this.currentstage = currentstage;
            }

            public String getCustName() {
                return custName;
            }

            public void setCustName(String custName) {
                this.custName = custName;
            }

            public int getCustid() {
                return custid;
            }

            public void setCustid(int custid) {
                this.custid = custid;
            }

            public Object getCuststatus() {
                return custstatus;
            }

            public void setCuststatus(Object custstatus) {
                this.custstatus = custstatus;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public Object getModifyby() {
                return modifyby;
            }

            public void setModifyby(Object modifyby) {
                this.modifyby = modifyby;
            }

            public Object getModifydate() {
                return modifydate;
            }

            public void setModifydate(Object modifydate) {
                this.modifydate = modifydate;
            }

            public int getOpportid() {
                return opportid;
            }

            public void setOpportid(int opportid) {
                this.opportid = opportid;
            }

            public String getOpportno() {
                return opportno;
            }

            public void setOpportno(String opportno) {
                this.opportno = opportno;
            }

            public String getOpportsubject() {
                return opportsubject;
            }

            public void setOpportsubject(String opportsubject) {
                this.opportsubject = opportsubject;
            }

            public String getOpporttype() {
                return opporttype;
            }

            public void setOpporttype(String opporttype) {
                this.opporttype = opporttype;
            }

            public Object getOrdersource() {
                return ordersource;
            }

            public void setOrdersource(Object ordersource) {
                this.ordersource = ordersource;
            }

            public Object getOrgid() {
                return orgid;
            }

            public void setOrgid(Object orgid) {
                this.orgid = orgid;
            }

            public String getPaymentmethod() {
                return paymentmethod;
            }

            public void setPaymentmethod(String paymentmethod) {
                this.paymentmethod = paymentmethod;
            }

            public int getPlanmoney() {
                return planmoney;
            }

            public void setPlanmoney(int planmoney) {
                this.planmoney = planmoney;
            }

            public long getPlansigndate() {
                return plansigndate;
            }

            public void setPlansigndate(long plansigndate) {
                this.plansigndate = plansigndate;
            }

            public String getPossibility() {
                return possibility;
            }

            public void setPossibility(String possibility) {
                this.possibility = possibility;
            }

            public String getPossibilityValue() {
                return possibilityValue;
            }

            public void setPossibilityValue(String possibilityValue) {
                this.possibilityValue = possibilityValue;
            }

            public String getProductCategoryName() {
                return productCategoryName;
            }

            public void setProductCategoryName(String productCategoryName) {
                this.productCategoryName = productCategoryName;
            }

            public String getProductcategory() {
                return productcategory;
            }

            public void setProductcategory(String productcategory) {
                this.productcategory = productcategory;
            }

            public Object getProductcount() {
                return productcount;
            }

            public void setProductcount(Object productcount) {
                this.productcount = productcount;
            }

            public String getProductid() {
                return productid;
            }

            public void setProductid(String productid) {
                this.productid = productid;
            }

            public String getProductvariety() {
                return productvariety;
            }

            public void setProductvariety(String productvariety) {
                this.productvariety = productvariety;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getStage() {
                return stage;
            }

            public void setStage(String stage) {
                this.stage = stage;
            }

            public String getStageValue() {
                return stageValue;
            }

            public void setStageValue(String stageValue) {
                this.stageValue = stageValue;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getUnitid() {
                return unitid;
            }

            public void setUnitid(Object unitid) {
                this.unitid = unitid;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
