/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

/**
 * <pre>
 * Branch Code Information
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/25/2016   Hitachi         A.Kohinata      Create          QC#6951
 * 04/18/2018   Fujitsu         A.Kosai         Update          QC#21919
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * </pre>
 */
public class NSXC002001GetBrCdBean {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Postal Code */
    private String postCd;

    /** Service Line of Business Code */
    private String svcLineBizCd;

    /** Sales Date */
    private String salesDate;

    /** Field Service Branch Code */
    private String fldSvcBrCd;

    /** Finance Branch Code */
    private String finBrCd;

    // 2018/04/18 QC#21919 Add Start
    /** Service Branch Code Description Text */
    private String svcBrCdDescTxt;
    // 2018/04/18 QC#21919 Add End

    // START 2018/09/10 K.Kitachi [QC#26260, ADD]
    /** Sold By Line of Business Type Code */
    private String sldByLineBizTpCd;
    // END 2018/09/10 K.Kitachi [QC#26260, ADD]

    /**
     * Get Global Company Code
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set Global Company Code
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get Postal Code
     * @return postCd
     */
    public String getPostCd() {
        return postCd;
    }

    /**
     * Set Postal Code
     * @param postCd String
     */
    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }

    /**
     * Get Service Line of Business Code
     * @return svcLineBizCd
     */

    public String getSvcLineBizCd() {
        return svcLineBizCd;
    }

    /**
     * Set Service Line of Business Code
     * @param svcLineBizCd String
     */

    public void setSvcLineBizCd(String svcLineBizCd) {
        this.svcLineBizCd = svcLineBizCd;
    }

    /**
     * GetSales Date
     * @return salesDate
     */

    public String getSalesDate() {
        return salesDate;
    }

    /**
     * Set Sales Date
     * @param salesDate String
     */

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    /**
     * Get Field Service Branch Code
     * @return fldSvcBrCd
     */
    public String getFldSvcBrCd() {
        return fldSvcBrCd;
    }

    /**
     * Set Field Service Branch Code
     * @param fldSvcBrCd String
     */
    public void setFldSvcBrCd(String fldSvcBrCd) {
        this.fldSvcBrCd = fldSvcBrCd;
    }

    /**
     * Get Finance Branch Code
     * @return finBrCd String
     */
    public String getFinBrCd() {
        return finBrCd;
    }

    /**
     * Set Finance Branch Code
     * @param finBrCd String
     */
    public void setFinBrCd(String finBrCd) {
        this.finBrCd = finBrCd;
    }

    // 2018/04/18 QC#21919 Add Start
    /**
     * Get Service Branch Code Description Text
     * @return svcBrCdDescTxt
     */
    public String getSvcBrCdDescTxt() {
        return svcBrCdDescTxt;
    }

    /**
     * Set Service Branch Code Description Text
     * @param svcBrCdDescTxt
     */
    public void setSvcBrCdDescTxt(String svcBrCdDescTxt) {
        this.svcBrCdDescTxt = svcBrCdDescTxt;
    }
    // 2018/04/18 QC#21919 Add End

    // START 2018/09/10 K.Kitachi [QC#26260, ADD]
    /**
     * Get Sold By Line of Business Type Code
     * @return sldByLineBizTpCd String
     */
    public String getSldByLineBizTpCd() {
        return sldByLineBizTpCd;
    }

    /**
     * Set Sold By Line of Business Type Code
     * @param sldByLineBizTpCd String
     */
    public void setSldByLineBizTpCd(String sldByLineBizTpCd) {
        this.sldByLineBizTpCd = sldByLineBizTpCd;
    }
    // END 2018/09/10 K.Kitachi [QC#26260, ADD]
}
