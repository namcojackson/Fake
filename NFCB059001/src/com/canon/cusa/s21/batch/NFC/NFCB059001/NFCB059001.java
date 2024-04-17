/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.batch.NFC.NFCB059001;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDPBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;
import business.parts.NFZC204001PMsg;

import com.canon.cusa.s21.api.NFZ.NFZC204001.NFZC204001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Create Late Fee Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2019/05/09   Fujitsu         H.Ikeda         Create          QC#50140
 */
public class NFCB059001 extends S21BatchMain {

    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;

    /** batProcDate */
    private String batProcDate = null;

    /** Account Date */
    private String acctDt = null;

    /** Termination Code */
    private TERM_CD termCd;

    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;

    /** Message String PROGLAM ID. */
    private static final String[] MSG_PROGLAM_ID = {"NFCB059001" };

    /** ssm Batch Client */
    private S21SsmBatchClient ssmClient;

    /** AR Statement Date */
    String arStmtDt = null;

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("NFCM0584I", MSG_PROGLAM_ID);

        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("NFCM0501E", new String[] {"GLBL_CMPY_CD"});
        }

        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        getAcctDt();
        if (this.acctDt == null) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("NFCM0501E", new String[] {"Account Date"});
        }

        ssmClient = S21SsmBatchClient.getClient(this.getClass());
        arStmtDt = getArStmtIssDay();

        this.termCd = TERM_CD.NORMAL_END;
    }

    @Override
    protected void mainRoutine() {

        NFZC204001PMsg pMsg = new NFZC204001PMsg();
        if (arStmtDt != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.acctDt);
            ZYPEZDItemValueSetter.setValue(pMsg.arStmtDt, this.arStmtDt);

            NFZC204001 nfzc204001 = new NFZC204001();
            nfzc204001.execute(pMsg, ONBATCH_TYPE.BATCH);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                    S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                this.termCd = TERM_CD.ABNORMAL_END;
                // this.errorRecordCnt = 1;
                this.errorRecordCnt = getItemValue(pMsg.batProcTotRecCnt) - getItemValue(pMsg.batProcNormRecCnt);
                rollback();
                return;
            }

            commit();
        }
        // this.normalRecordCnt = 1;
        this.normalRecordCnt = getItemValue(pMsg.batProcNormRecCnt);
    }

    @Override
    protected void termRoutine() {
        this.totalRecordCnt = this.normalRecordCnt + this.errorRecordCnt;
        setTermState(this.termCd, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
        S21InfoLogOutput.println("NFCM0593I", MSG_PROGLAM_ID);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB059001().executeBatch(NFCB059001.class.getSimpleName());
    }

    private String getArStmtIssDay() {
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("arAcctDt", this.acctDt);
        ssmParam.put("arStmtStsCd", AR_STMT_STS.PENDING);
        ssmParam.put("lateFeeFnlzFlgN", ZYPConstant.FLG_OFF_N);

        return (String) ssmClient.queryObject("getArStmtDt", ssmParam);
    }

    private void getAcctDt() {
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        final int dateLen = 6;
        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.onlBatTpCd, "2");
        ZYPEZDItemValueSetter.setValue(inMsg.subSysCd, subSysCd);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            this.acctDt = "";
        } else {
            String lclAcctDt = outMsg.acctDt.getValue();
            if (lclAcctDt == null) {
                this.acctDt = "";
            } else {

                if (lclAcctDt.substring(0, dateLen).equals(this.batProcDate.substring(0, dateLen))) {
                    this.acctDt = this.batProcDate;
                } else {
                    this.acctDt = lclAcctDt;
                }
            }
        }
    }

    private int getItemValue(EZDPBigDecimalItem value) {

        if (!ZYPCommonFunc.hasValue(value)) {
            return 0;
        }
        return value.getValueInt();
    }
}
