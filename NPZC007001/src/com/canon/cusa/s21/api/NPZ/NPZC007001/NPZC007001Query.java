/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/05/09   Fujitsu         K.Matsumura        Create          N/A
 * 11/28/2016   CITS            Y.Fujii            Update          R350
 *</pre>
 */
/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC007001;

import java.math.BigDecimal;
import java.util.Map;

import business.db.VNDTMsg;
import business.parts.NPZC007001PMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * Class name: NPZC007001Query <dd>The class explanation: Business
 * processing for Component ID : NPZC007001Query <dd>Remarks:
 * @version 1.0
 * @author K.Matsumura
 */
public final class NPZC007001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPZC007001Query INSTANCE = new NPZC007001Query();

    /**
     * Constructor.
     */
    private NPZC007001Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPZC007001Query
     */
    public static NPZC007001Query getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * getVNDTMsg
     * </pre>
     * @param bizMsg NPZC007001PMsg
     * @param vndTMsg VNDTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVNDTMsg(NPZC007001PMsg bizMsg, VNDTMsg vndTMsg) {

        return getSsmEZDClient().queryEZDMsg("getVNDTMsg", bizMsg, vndTMsg);
    }

    /**
     * getCnsgnCd
     * @param ssmParam Map<String, Object>
     * @return String
     */
    public String getCnsgnCd(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getCnsgnCd", ssmParam);
        return (String) result.getResultObject();
    }

    // STR 2016/02/01 R.Shimamoto [V1.5 ADD]
    /**
     * getPo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPo", ssmParam);
    }

    /**
     * getPoTotalAmount
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal getPoTotalAmount(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getPoTotalAmount", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    // END 2016/02/01 R.Shimamoto [V1.5 ADD]

    /**
     * getPoRptWrk
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal getPoRptWrk(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getPoRptWrk", ssmParam);
        return (BigDecimal) result.getResultObject();
    }

    /**
     * @param ssmParam
     * @return
     */
    public String getPrntVndNm(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getPrntVndNm", ssmParam);
        return (String) result.getResultObject();
    }

    /**
     * getPoRptInfo
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoRptInfo(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPoRptInfo", ssmParam);
    }

    /**
     * @param ssmParam
     * @return
     */
    public String getDepartment(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getDepartment", ssmParam);
        return (String) result.getResultObject();
    }
    
    //QC#23929 ADD START
    /**
     * getPoTotalAmountforOpen
     * @param ssmParam Map<String, Object>
     * @return BigDecimal
     */
    public BigDecimal getPoTotalAmountforOpen(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getPoTotalAmountforOpen", ssmParam);
        return (BigDecimal) result.getResultObject();
    }
    //QC#23929 ADD END}
    
    //QC#21354 ADD START
    /**
     * @param ssmParam
     * @return
     */
    public String getByrTelNum(Map<String, Object> ssmParam) {
        S21SsmEZDResult result = getSsmEZDClient().queryObject("getByrTelNum", ssmParam);
        return (String) result.getResultObject();
    }
    //QC#21354 ADD END
}