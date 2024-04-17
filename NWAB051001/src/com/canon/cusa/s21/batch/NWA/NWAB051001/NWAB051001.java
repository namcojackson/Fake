/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB051001;

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
 * Create Invoiced Order Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 01/19/2016   FUJITSU         K.Sato          CREATE          NEW
 *</pre>
 */
public class NWAB051001 extends S21BatchMain {

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
     * @param args
     */
    public static void main(String[] args) {
        new NWAB051001().executeBatch(NWAB051001.class.getSimpleName());
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
        String hintCloOrdV = "/*+ FULL(@SEL$10 VTR) FULL(@SEL$07BDC5B4 PC) FULL(@SEL$07BDC5B4 DOC) FULL(@SEL$07BDC5B4 DOR) FULL(@SEL$14 VTR) FULL(@SEL$01FFE04B OLC) FULL(@SEL$01FFE04B SPDS) FULL(@SEL$01FFE04B RLDS) FULL(@SEL$01FFE04B OLS) FULL(@SEL$22 RR) FULL(@SEL$22 OPT) FULL(@SEL$01FFE04B DOC) FULL(@SEL$01FFE04B OHDS) FULL(@SEL$01FFE04B CST) FULL(@SEL$01FFE04B CMT) FULL(@SEL$01FFE04B DOTPD) FULL(@SEL$01FFE04B PC) FULL(@SEL$01FFE04B VND) FULL(@SEL$6 SP) FULL(@SEL$07BDC5B4 PCR) FULL(@SEL$11 PC) FULL(@SEL$11 RD) FULL(@SEL$11 PCR) FULL(@SEL$01FFE04B PCR) FULL(@SEL$01FFE04B DOR) USE_NL(@SEL$07BDC5B4 DAC_SELL) USE_NL(@SEL$9 M) INDEX(@SEL$9 M MDSE_PK) LEADING(@SEL$6 I IB IL) USE_NL(@SEL$6 IB) USE_NL(@SEL$6 IL) USE_NL(@SEL$11 IB) USE_NL(@SEL$11 DAC_SELL) USE_NL(@SEL$11 IL) USE_NL(@SEL$13 M) INDEX(@SEL$13 M MDSE_PK) FULL(@SEL$10 V) FULL(@SEL$14 V) */";
        queryParam.put("hintCloOrdV", hintCloOrdV);


        if ("T".equals(deleteMode)) {
            // TruncateMode
            totalCorrectCount = ssmBatchClientCustom.delete("TruncateCloOrd", queryParam);
            S21InfoLogOutput.println("TRUNCATE_MODE");
        } else if ("Y".equals(deleteMode)) {
            // DeleteMode
            totalCorrectCount = ssmBatchClientCustom.delete("DeleteCloOrd", queryParam);
            S21InfoLogOutput.println("DELETE_COUNT:" + totalCorrectCount);
        }

        totalCorrectCount = ssmBatchClientCustom.insert("CreateCloOrd", queryParam);

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
