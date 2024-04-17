/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

/**
 * <pre>
 * GetLocRoleTpCdForPrchReqBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/26   Hitachi         T.Tomita        Update          QC1325
 *</pre>
 */
public class NPXC001001GetLocRoleTpCdForPrchReqBean {
    /**
     * Location Type Code
     */
    private String locTpCd;

    /**
     * Location Role Type Code
     */
    private String locRoleTpCd;

    /**
     * Get Location Type Code
     * @return locTpCd
     */
    public String getLocTpCd() {
        return locTpCd;
    }

    /**
     * Set Location Type Code
     * @param locTpCd String
     */
    public void setLocTpCd(String locTpCd) {
        this.locTpCd = locTpCd;
    }

    /**
     * Get Location Role Type Code
     * @return locRoleTpCd
     */
    public String getLocRoleTpCd() {
        return locRoleTpCd;
    }

    /**
     * Set Location Role Type Code
     * @param locRoleTpCd String
     */
    public void setLocRoleTpCd(String locRoleTpCd) {
        this.locRoleTpCd = locRoleTpCd;
    }

}
