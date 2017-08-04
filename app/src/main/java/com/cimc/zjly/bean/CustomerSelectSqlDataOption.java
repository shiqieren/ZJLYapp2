package com.cimc.zjly.bean;

import java.util.List;

/**
 * Created by lyw on 2017/8/4.
 */

public class CustomerSelectSqlDataOption {

    private List<WebBean> web;
    private List<CustTypeBean> custType;
    private List<RegionBean> region;

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public List<CustTypeBean> getCustType() {
        return custType;
    }

    public void setCustType(List<CustTypeBean> custType) {
        this.custType = custType;
    }

    public List<RegionBean> getRegion() {
        return region;
    }

    public void setRegion(List<RegionBean> region) {
        this.region = region;
    }

    public static class WebBean {
        /**
         * codeid : SW0
         * codevalue : 没有税
         */

        private String codeid;
        private String codevalue;

        public String getCodeid() {
            return codeid;
        }

        public void setCodeid(String codeid) {
            this.codeid = codeid;
        }

        public String getCodevalue() {
            return codevalue;
        }

        public void setCodevalue(String codevalue) {
            this.codevalue = codevalue;
        }
    }

    public static class CustTypeBean {
        /**
         * codeid : CT01
         * codevalue : 集团4S店G
         */

        private String codeid;
        private String codevalue;

        public String getCodeid() {
            return codeid;
        }

        public void setCodeid(String codeid) {
            this.codeid = codeid;
        }

        public String getCodevalue() {
            return codevalue;
        }

        public void setCodevalue(String codevalue) {
            this.codevalue = codevalue;
        }
    }

    public static class RegionBean {
        /**
         * categoryid : 0
         * categoryname : 中国
         * superid : null
         * cateList : [{"categoryid":84,"categoryname":"河南","categoryno":"PROV16","superid":0,"cateList":[{"cateList":null,"categoryid":9201,"categoryname":"郑州市","categoryno":"PROV1601","superid":84},{"cateList":null,"categoryid":9203,"categoryname":"洛阳市","categoryno":"PROV1603","superid":84}]}]
         */

        private int categoryid;
        private String categoryname;
        private Object superid;
        private List<CateListBeanX> cateList;

        public int getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(int categoryid) {
            this.categoryid = categoryid;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
        }

        public Object getSuperid() {
            return superid;
        }

        public void setSuperid(Object superid) {
            this.superid = superid;
        }

        public List<CateListBeanX> getCateList() {
            return cateList;
        }

        public void setCateList(List<CateListBeanX> cateList) {
            this.cateList = cateList;
        }

        public static class CateListBeanX {
            /**
             * categoryid : 84
             * categoryname : 河南
             * categoryno : PROV16
             * superid : 0
             * cateList : [{"cateList":null,"categoryid":9201,"categoryname":"郑州市","categoryno":"PROV1601","superid":84},{"cateList":null,"categoryid":9203,"categoryname":"洛阳市","categoryno":"PROV1603","superid":84}]
             */

            private int categoryid;
            private String categoryname;
            private String categoryno;
            private int superid;
            private List<CateListBean> cateList;

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public String getCategoryname() {
                return categoryname;
            }

            public void setCategoryname(String categoryname) {
                this.categoryname = categoryname;
            }

            public String getCategoryno() {
                return categoryno;
            }

            public void setCategoryno(String categoryno) {
                this.categoryno = categoryno;
            }

            public int getSuperid() {
                return superid;
            }

            public void setSuperid(int superid) {
                this.superid = superid;
            }

            public List<CateListBean> getCateList() {
                return cateList;
            }

            public void setCateList(List<CateListBean> cateList) {
                this.cateList = cateList;
            }

            public static class CateListBean {
                /**
                 * cateList : null
                 * categoryid : 9201
                 * categoryname : 郑州市
                 * categoryno : PROV1601
                 * superid : 84
                 */

                private Object cateList;
                private int categoryid;
                private String categoryname;
                private String categoryno;
                private int superid;

                public Object getCateList() {
                    return cateList;
                }

                public void setCateList(Object cateList) {
                    this.cateList = cateList;
                }

                public int getCategoryid() {
                    return categoryid;
                }

                public void setCategoryid(int categoryid) {
                    this.categoryid = categoryid;
                }

                public String getCategoryname() {
                    return categoryname;
                }

                public void setCategoryname(String categoryname) {
                    this.categoryname = categoryname;
                }

                public String getCategoryno() {
                    return categoryno;
                }

                public void setCategoryno(String categoryno) {
                    this.categoryno = categoryno;
                }

                public int getSuperid() {
                    return superid;
                }

                public void setSuperid(int superid) {
                    this.superid = superid;
                }
            }
        }
    }
}
