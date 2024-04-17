/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/20   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class WtyDtBean {

    /** Warranty Start Date */
    private String wtyStartDt;
    
    /** Warranty End Date */
    private String wtyEndDt;

    public String getWtyStartDt() {
        return wtyStartDt;
    }

    public void setWtyStartDt(String wtyStartDt) {
        this.wtyStartDt = wtyStartDt;
    }

    public String getWtyEndDt() {
        return wtyEndDt;
    }

    public void setWtyEndDt(String wtyEndDt) {
        this.wtyEndDt = wtyEndDt;
    }

}
