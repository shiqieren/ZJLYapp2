package com.cimc.zjly.bean.RequestBean;

/**
 * Created by lyw on 2017/8/5.
 */

public class AddCustomerReq {

    /**
     * custname : 东莞市鸿业造纸有限公司
     * custaddress : 东莞11111
     * custtel : 0769-8814848
     * custtype : CT02
     * zipcode : 111111
     * web : SW1
     * modifyby : 2
     * owner : 2
     * shortname : 11111111
     * fax : 0769-8814848
     * country : 中国
     * province : PROV19
     * city : PROV1918
     * makeup : 3465646456
     * summary : 123455
     * contact : {"personname":"111","conttel":"0769-8814848","contmobile":"17620353766","familyarea":"PROV19","familycity":"PROV1918","familyaddress":"东莞11111","summary":"234344444444"}
     */

    private String custname;
    private String custaddress;
    private String custtel;
    private String custtype;
    private String zipcode;
    private String web;
    private int modifyby;
    private int owner;
    private String shortname;
    private String fax;
    private String country;
    private String province;
    private String city;
    private String makeup;
    private String summary;
    private ContactBean contact;

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustaddress() {
        return custaddress;
    }

    public void setCustaddress(String custaddress) {
        this.custaddress = custaddress;
    }

    public String getCusttel() {
        return custtel;
    }

    public void setCusttel(String custtel) {
        this.custtel = custtel;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getModifyby() {
        return modifyby;
    }

    public void setModifyby(int modifyby) {
        this.modifyby = modifyby;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMakeup() {
        return makeup;
    }

    public void setMakeup(String makeup) {
        this.makeup = makeup;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ContactBean getContact() {
        return contact;
    }

    public void setContact(ContactBean contact) {
        this.contact = contact;
    }

    public static class ContactBean {
        /**
         * personname : 111
         * conttel : 0769-8814848
         * contmobile : 17620353766
         * familyarea : PROV19
         * familycity : PROV1918
         * familyaddress : 东莞11111
         * summary : 234344444444
         */

        private String personname;
        private String conttel;
        private String contmobile;
        private String familyarea;
        private String familycity;
        private String familyaddress;
        private String summary;

        public String getPersonname() {
            return personname;
        }

        public void setPersonname(String personname) {
            this.personname = personname;
        }

        public String getConttel() {
            return conttel;
        }

        public void setConttel(String conttel) {
            this.conttel = conttel;
        }

        public String getContmobile() {
            return contmobile;
        }

        public void setContmobile(String contmobile) {
            this.contmobile = contmobile;
        }

        public String getFamilyarea() {
            return familyarea;
        }

        public void setFamilyarea(String familyarea) {
            this.familyarea = familyarea;
        }

        public String getFamilycity() {
            return familycity;
        }

        public void setFamilycity(String familycity) {
            this.familycity = familycity;
        }

        public String getFamilyaddress() {
            return familyaddress;
        }

        public void setFamilyaddress(String familyaddress) {
            this.familyaddress = familyaddress;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
