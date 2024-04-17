/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC100001;


/**
 * <pre>
 * It is a class checking Whether given location information is available or not
 * {@link com.canon.cusa.s21.common.NMX.NMX100001.NMXC100001CheckEnableWH NMXC100001CheckEnableWH} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         H.Mizutani      Create          N/A
 * </pre>
 */
public class NMXC100001EnableWHData {

    /**
     * Inventory Location Name
     */
    private String invtyLocNm = null;

    /**
     * Location Role Type Code
     */
    private String locRoleTpCd = null;

    /**
     * Message 
     */
    private String xxMsgId = null;

    /**
     * Get Inventory location name
     * @return invtyLocNm Inventory Location Name
     */
    public String getInvtyLocNm() {
        return invtyLocNm;
    }

    /**
     * Set inventory location name
     * @param invtyLocNm Inventory Location Name
     */
    public void setInvtyLocNm(String invtyLocNm) {
        this.invtyLocNm = invtyLocNm;
    }

    /**
     * Get Location Role Type Code
     * @return locRoleTpCd Location Role Type Code
     */
    public String getLocRoleTpCd() {
        return locRoleTpCd;
    }

    /**
     * Set Location Role Type Code
     * @param locRoleTpCd Location Role Type Code
     */
    public void setLocRoleTpCd(String locRoleTpCd) {
        this.locRoleTpCd = locRoleTpCd;
    }

    /**
     * Get message info 
     * @return xxMsgId Message ID 
     */
    public String getXxMsgId() {
        return xxMsgId;
    }

    /**
     * Set message info 
     * @param xxMsgId Message ID 
     */
    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }

    /**
     * Get whole info as String
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("invtyLocNm=").append(getInvtyLocNm()).append(',');
        sb.append("locRoleTpCd=").append(getLocRoleTpCd()).append(',');
        sb.append("xxMsgId=").append(getXxMsgId());
        return sb.toString();
    }

}
