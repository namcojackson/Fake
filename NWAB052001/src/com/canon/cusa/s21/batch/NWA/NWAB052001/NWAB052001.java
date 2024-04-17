/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB052001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Create Open Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 01/19/2016   FUJITSU         K.Sato          CREATE          NEW
 *</pre>
 */
public class NWAB052001 extends S21BatchMain {

    /** Message ID */
    private static final String CST_MSG_ID_ERR_SHELL_VAR_DTL = "NWAM0262E";

    /** Message ID */
    private static final String CST_MSG_ID_ERR_INITIALIZE_INFO = "NWAM0270E";

    /** Err Message parameter */
    private static final String CST_MSG_PRAM_GLBL_CMPY_CD = "Global Company Code";

    /** SSM Statement Map Key */
    private static final String CST_MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** global company code */
    private String glblCmpyCd = "";

    /** deleteMode */
    private String deleteMode = "Y";

    /** total correct count */
    private int totalCorrectCount = 0;

    /** total error count */
    private int totalErrorCount = 0;

    /** total Execute count */
    private int totalExecuteCount = 0;

    /** SSM Client Custom */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    /**
     * Called batch Shell
     * @param args String[]
     */
    public static void main(String[] args) {

        new NWAB052001().executeBatch(NWAB052001.class.getSimpleName());

    }

    @Override
   protected void initRoutine() {

        // Initialization of termination code
        this.setTermCd(TERM_CD.NORMAL_END);

        // Get Environment variable
        if (!this.getEnvVariable()) {
            this.setTermCd(TERM_CD.ABNORMAL_END);
        }

        if (TERM_CD.ABNORMAL_END.equals(termCd)) {
            throw new S21AbendException(CST_MSG_ID_ERR_INITIALIZE_INFO);
        }

        // Initialization of SSM Custom
        ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(CST_MAP_KEY_GLBL_CMPY_CD, this.glblCmpyCd);

        String dateTime = EZDDBCICarrier.getStartDateTime();
        String upCmpyCd = EZDDBCICarrier.getUpCmpyCd();
        String upPgId = EZDDBCICarrier.getUppgID();
        String upTimeZone = EZDDBCICarrier.getUpTimeZone();
        String userId = EZDDBCICarrier.getUserID();

        queryParam.put("ezintime", dateTime);
        queryParam.put("ezintimezone", upTimeZone);
        queryParam.put("ezincompanycode", upCmpyCd);
        queryParam.put("ezinuserid", userId);
        queryParam.put("ezinaplid", upPgId);

        queryParam.put("ezuptime", dateTime);
        queryParam.put("ezuptimezone", upTimeZone);
        queryParam.put("ezupcompanycode", upCmpyCd);
        queryParam.put("ezupuserid", userId);
        queryParam.put("ezupaplid", upPgId);

        /** SQL Hint */
        String hintHdrWfV = "/*+ FULL(@SEL$04B9C198 OLS) FULL(@SEL$04B9C198 OLDS) FULL(@SEL$04B9C198 OLC) FULL(@SEL$04B9C198 PC) FULL(@SEL$04B9C198 VND) FULL(@SEL$04B9C198 CMT) FULL(@SEL$04B9C198 DOTPD) FULL(@SEL$04B9C198 DOC) FULL(@SEL$04B9C198 OHDS) FULL(@SEL$04B9C198 CST) FULL(@SEL$CF3D30C1 AAB) FULL(@SEL$5 RR) FULL(@SEL$6FB23B79 DOTPD) FULL(@SEL$6FB23B79 DOC) FULL(@SEL$6FB23B79 CST) NO_PUSH_PRED(@SEL$04B9C198 DPLY_VND) FULL(@SEL$9 VTR) FULL(@SEL$9 V) FULL(@SEL$9 PV) USE_NL(@SEL$6FB23B79 CD) USE_NL(@SEL$6FB23B79 DAC_BILL) USE_NL(@SEL$6FB23B79 DAC_SELL) USE_NL(@SEL$6FB23B79 DAC_SHIP) */";
        queryParam.put("hintHdrWfV", hintHdrWfV);
        String hintLineVldV = "/*+ FULL(@SEL$1438A744 CMT) FULL(@SEL$1438A744 OLC) FULL(@SEL$1438A744 SPDS) FULL(@SEL$1438A744 RLDS) FULL(@SEL$1438A744 DOTPD) FULL(@SEL$1438A744 DOC) FULL(@SEL$1438A744 OHDS) FULL(@SEL$1438A744 CST) FULL(@SEL$1438A744 OLS) FULL(@SEL$1438A744 PC) FULL(@SEL$1438A744 VND) USE_NL(@SEL$1438A744 DAC_SELL) USE_NL(@SEL$1438A744 DAC_SHIP) USE_NL(@SEL$1438A744 DAC_BILL) USE_NL(@SEL$20 M) NO_PUSH_PRED(@SEL$1438A744 DPLY_VND) FULL(@SEL$21 VTR) FULL(@SEL$21 V) FULL(@SEL$21 PV) */";
        queryParam.put("hintLineVldV", hintLineVldV);
        String hintLineShipV = "/*+ FULL(@SEL$2E998F30 CST) FULL(@SEL$2E998F30 DOC) FULL(@SEL$2E998F30 DOTPD) FULL(@SEL$2E998F30 OHDS) FULL(@SEL$2E998F30 CMT) FULL(@SEL$2E998F30 RLDS) FULL(@SEL$2E998F30 SPDS) FULL(@SEL$2E998F30 PC) FULL(@SEL$2E998F30 OLS) FULL(@SEL$2E998F30 OLC) FULL(@SEL$2E998F30 VND) USE_NL(@SEL$2E998F30 DAC_SELL) NO_PUSH_PRED(@SEL$2E998F30 DPLY_VND) FULL(@SEL$30 VTR) FULL(@SEL$30 V) FULL(@SEL$30 PV) */";
        queryParam.put("hintLineShipV", hintLineShipV);
        String hintRtrnLineV = "/*+ FULL(@SEL$B767D946 OLS) FULL(@SEL$B767D946 CST) FULL(@SEL$B767D946 DOTPD) FULL(@SEL$B767D946 VND) FULL(@SEL$B767D946 OLC) FULL(@SEL$B767D946 DOC) FULL(@SEL$B767D946 PC) FULL(@SEL$B767D946 RR) USE_NL(@SEL$B767D946 SMM) NO_PUSH_PRED(@SEL$B767D946 DPLY_VND) FULL(@SEL$44 VTR) FULL(@SEL$44 V) FULL(@SEL$44 PV) LEADING(@SEL$50795562 RWSH RWSD) NO_USE_HASH(@SEL$50795562 RWSD) USE_NL(@SEL$50795562 RWSD) INDEX(@SEL$50795562 RWSD RWS_DTL_PK) USE_NL(@SEL$50795562 RWSS) */";
        queryParam.put("hintRtrnLineV", hintRtrnLineV);
        String hintCancOrdV = "/*+ FULL(@SEL$48 RR) FULL(@SEL$146182C0 DOC) FULL(@SEL$146182C0 CST) FULL(@SEL$146182C0 VND) FULL(@SEL$146182C0 SPDS) FULL(@SEL$146182C0 OHDS) FULL(@SEL$146182C0 OLC) FULL(@SEL$146182C0 RLDS) FULL(@SEL$146182C0 DOTPD) FULL(@SEL$146182C0 CMT) NO_PUSH_PRED(@SEL$146182C0 DPLY_VND) FULL(@SEL$52 VTR) FULL(@SEL$52 V) FULL(@SEL$52 PV) */";
        queryParam.put("hintCancOrdV", hintCancOrdV);
        String hintPendImptV = "/*+ FULL(@SEL$D312661C PC) FULL(@SEL$D312661C OLC) FULL(@SEL$D312661C DOTPD) FULL(@SEL$D312661C DOC) FULL(@SEL$D312661C CST) USE_NL(@SEL$59 M) INDEX(@SEL$59 M MDSE_PK) FULL(@SEL$60 VTR) FULL(@SEL$60 PV) FULL(@SEL$60 V) */";
        queryParam.put("hintPendImptV", hintPendImptV);

        if ("T".equals(deleteMode)) {
            // TruncateMode
            totalCorrectCount = ssmBatchClientCustom.delete("TruncateOpenOrd", queryParam);
            S21InfoLogOutput.println("TRUNCATE_MODE");
        } else if ("Y".equals(deleteMode)) {
            // DeleteMode
            totalCorrectCount = ssmBatchClientCustom.delete("DeleteOpenOrd", queryParam);
            S21InfoLogOutput.println("DELETE_COUNT:" + totalCorrectCount);
        }

        totalCorrectCount = ssmBatchClientCustom.insert("CreateOpenOrd", queryParam);

        // rollback();
        commit();
    }

    @Override
    protected void termRoutine() {

        ssmBatchClientCustom = null;
        totalExecuteCount = totalCorrectCount;

        setTermState(this.termCd, this.totalCorrectCount, this.totalErrorCount, this.totalExecuteCount);

    }

    /**
     * <PRE>
     * 
     * Set termCd
     * 
     * </PRE>
     * @param termCd TERM_CD
     */
    private void setTermCd(TERM_CD ptermCd) {

        if (TERM_CD.WARNING_END.equals(ptermCd)) {
            if (TERM_CD.NORMAL_END.equals(this.termCd)) {
                this.termCd = ptermCd;
            }
        } else if (TERM_CD.ABNORMAL_END.equals(ptermCd)) {
            this.termCd = ptermCd;
        } else {
            this.termCd = ptermCd;
        }
    }

    /**
     * Get Environment variable
     * @param
     * @return Boolean
     */
    private Boolean getEnvVariable() {

        List<BatchErrMsgInfo> listErrMsgInfo = new ArrayList<BatchErrMsgInfo>();

        Boolean booRetValue = Boolean.TRUE;

        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            BatchErrMsgInfo errMsgInfo = new BatchErrMsgInfo();
            errMsgInfo.setMessageId(CST_MSG_ID_ERR_SHELL_VAR_DTL);
            String[] insertStr = {CST_MSG_PRAM_GLBL_CMPY_CD };
            errMsgInfo.setInsertStr(insertStr);
            listErrMsgInfo.add(errMsgInfo);
        }

        deleteMode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(deleteMode)) {
            if ("N".equals(deleteMode)) {
                deleteMode = "N"; // No DeleteMode
            } else if ("T".equals(deleteMode)) {
                deleteMode = "T"; // TruncateMode
            } else {
                deleteMode = "Y"; // DeleteMode
            }
        }

        // job log output
        if (!listErrMsgInfo.isEmpty()) {
            for (BatchErrMsgInfo errMsg : listErrMsgInfo) {
                S21InfoLogOutput.println(errMsg.getMessageId(), errMsg.getInsertStr());
            }
            booRetValue = Boolean.FALSE;
        }

        return booRetValue;
    }

}
