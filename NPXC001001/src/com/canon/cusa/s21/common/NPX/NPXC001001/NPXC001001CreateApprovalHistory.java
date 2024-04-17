/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.APVL_HIST_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Create Approval History
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/15   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NPXC001001CreateApprovalHistory {

    /** */
    public static final String NORMAL = "0";

    /** */
    public static final String ERROR = "1";

    /** */
    private static final String FORMAT_TIMESTAMP = "yyyyMMddHHmmss";

    /** */
    private static S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NPXC001001CreateApprovalHistory.class);

    /** */
    private static final int DEBUG_MSG_LVL = 8;

    /** */
    private static final String PROGRAM_ID = "NPXC001001CreatePOHistory";

    /**
     * <pre>
     * </pre>
     * @param inParam NPXC001001CreateApprovalHistoryBean
     * @return Return Code (0:Normal, other:Error) String
     */
    public static String createApprovalHistory(NPXC001001CreateApprovalHistoryBean inParam) {

        // Param check
        if (!ZYPCommonFunc.hasValue(inParam.getGlblCmpyCd())) {
            debugLog("GLBL_CMPY_CD is mandatory");
            return ERROR;
        }
        if (!ZYPCommonFunc.hasValue(inParam.getApvlHistSrcTpCd())) {
            debugLog("APVL_HIST_SRC_TP_CD is mandatory");
            return ERROR;
        }
        if (!ZYPCommonFunc.hasValue(inParam.getTrxRefNum())) {
            debugLog("TRX_REF_NUM is mandatory");
            return ERROR;
        }
        if (!ZYPCommonFunc.hasValue(inParam.getApvlHistActTpCd())) {
            debugLog("APVL_HIST_ACT_TP_CD is mandatory");
            return ERROR;
        }
        if (!ZYPCommonFunc.hasValue(inParam.getApvlByPsnCd())) {
            debugLog("APVL_BY_PSN_CD is mandatory");
            return ERROR;
        }
        if (!ZYPCommonFunc.hasValue(inParam.getApvlHistInfoTs())) {
            inParam.setApvlHistInfoTs(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP));
        }

        String fullPsnNm = "";

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", inParam.getGlblCmpyCd());
        params.put("psnCd", inParam.getApvlByPsnCd());
        List<String> resultList = (List<String>) ssmClient.queryObjectList("getFullPsnNm", params);
        if (resultList.size() < 1) {
            debugLog("S21_PSN_V Reccord is not found");
        } else {
            fullPsnNm = resultList.get(0);
        }

        // Insert APVL_HIST_INFO
        APVL_HIST_INFOTMsg tMsg = new APVL_HIST_INFOTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, inParam.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tMsg.apvlHistInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal("APVL_HIST_INFO_SQ"));
        ZYPEZDItemValueSetter.setValue(tMsg.apvlHistSrcTpCd, inParam.getApvlHistSrcTpCd());
        ZYPEZDItemValueSetter.setValue(tMsg.trxRefNum, inParam.getTrxRefNum());
        ZYPEZDItemValueSetter.setValue(tMsg.apvlHistInfoTs, inParam.getApvlHistInfoTs());
        ZYPEZDItemValueSetter.setValue(tMsg.apvlHistActTpCd, inParam.getApvlHistActTpCd());
        ZYPEZDItemValueSetter.setValue(tMsg.apvlByPsnCd, inParam.getApvlByPsnCd());
        ZYPEZDItemValueSetter.setValue(tMsg.apvlFullPsnNm, fullPsnNm);
        ZYPEZDItemValueSetter.setValue(tMsg.apvlHistTxt, inParam.getApvlHistTxt());

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            debugLog("insert error. [APVL_HIST_INFOT]");
            return ERROR;
        }

        return NORMAL;

    }

    /**
     * @param logmsg String
     */
    private static void debugLog(String logmsg) {
        EZDDebugOutput.println(DEBUG_MSG_LVL, PROGRAM_ID + logmsg, NPXC001001CreatePOHistory.class);
    }
}
