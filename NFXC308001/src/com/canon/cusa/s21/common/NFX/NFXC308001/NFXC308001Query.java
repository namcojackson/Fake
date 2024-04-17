/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 *
 * Date         Company    Name         Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/26/2011   Fujitsu    T.Tanaka     Create          ITG#363113
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC308001;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NFXC308001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NFXC308001Query MYINSTANCE = new NFXC308001Query();

    /**
     */
    private NFXC308001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NFXC308001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param inArRcptTrxTpMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcptTrxTpList(Map<String, Object> inArRcptTrxTpMap) {

        return getSsmEZDClient().queryObjectList("getArRcptTrxTpList", inArRcptTrxTpMap, -1, -1);
    }

    /**
     * @param inArRcptTpMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArRcptTpList(Map<String, Object> inArRcptTpMap) {

        return getSsmEZDClient().queryObjectList("getArRcptTpList", inArRcptTpMap, -1, -1);
    }

    /**
     * @param inArBanKAcctMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArBankAcctList(Map<String, Object> inArBanKAcctMap) {

        return getSsmEZDClient().queryObjectList("getArBankAcctList", inArBanKAcctMap, -1, -1);
    }
    
    /**
     * getWrtOffTargetData
     * @param paramMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrtOffTargetData(Map<String, Object> paramMap) {

        return getSsmEZDClient().queryObjectList("getWrtOffTargetData", paramMap, -1, -1);
    }
    
    /**
     * getWrtOffTargetDataForCons
     * @param paramMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrtOffTargetDataForCons(Map<String, Object> paramMap) {

        return getSsmEZDClient().queryObjectList("getWrtOffTargetDataForCons", paramMap, -1, -1);
    }
    
    //---- start add 2016/04/07 For research in write off request screen
    /**
     * getWrtOffTargetData
     * @param paramMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrtOffTargetDataResrc(Map<String, Object> paramMap) {

        return getSsmEZDClient().queryObjectList("getWrtOffTargetDataResrc", paramMap, -1, -1);
    }
    
    /**
     * getWrtOffTargetDataForCons
     * @param paramMap Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrtOffTargetDataForConsResrc(Map<String, Object> paramMap) {

        return getSsmEZDClient().queryObjectList("getWrtOffTargetDataForConsResrc", paramMap, -1, -1);
    }
    //---- end 2016/04/07

    /**
     * 
     * @param bizMsg
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult getArRcptUnApply(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getArRcptUnApply", ssmParam);
    }

}
