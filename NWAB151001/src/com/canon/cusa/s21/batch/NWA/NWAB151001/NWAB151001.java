/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB151001;


import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWZC033001PMsg;
import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REV_RECOG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Automatic Hold Release
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         A.Suda          Create          N/A
 * 2019/09/02   Fujitsu         T.Noguchi       Update          QC#53016
 *</pre>
 */
public class NWAB151001 extends S21BatchMain {

    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** MessageID */
    private static final String ZZM9000E = "ZZM9000E";

    /** MessageID */
    private static final String ZZXM0015E = "ZZXM0015E";

    /** MessageID */
    private static final String NWAM0189E = "NWAM0189E";

    /** Message Kind error */
    private static final String MESSAGE_KIND_ERROR = "E";

    /** Set Parent Line Sub Number */
    private static final String SET_PARENT_LINE_SUB_NUM = "000";

    // Add Start 2019/09/06 QC#53016
    /** Const Key Credit/Rebill And Bill Only Dummy WH Code  */
    private String CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD = "CR_AND_BILL_ONLY_DUMMY_WH_CD";
    // Add End 2019/09/06 QC#53016

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** executed record count. (Normal End records.) */
    private int normalRecCnt = 0;

    /** executed record count. (Error records.) */
    private int errRecCnt = 0;

    /** executed record count. (Total records.) */
    private int totalRecCnt = 0;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB151001().executeBatch(NWAB151001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // --------------------------------------------------
        // GLBL_CMPY_CD
        // --------------------------------------------------
        if (!isGlobalCompanyCode()) {
            S21InfoLogOutput.println(ZZM9000E, new String[] {"Global Company Code" });
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        // --------------------------------------------------
        // SALES_DATE
        // --------------------------------------------------
        if (!hasValue(getSalesDate())) {
            S21InfoLogOutput.println(ZZXM0015E, new String[] {"S", getGlobalCompanyCode() });
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            return;
        }

        // --------------------------------------------------
        // Initialization of SSM
        // --------------------------------------------------
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // --------------------------------------------------
        // getInformation : HoldOrder
        // --------------------------------------------------
        List<Map<String, Object>> holdOrderList = getHoldOrderInfo();
        String checkedCpoOrdNum = "";
        List<NWZC033001PMsg> pMsgList = new ArrayList<NWZC033001PMsg>();

        for (int i = 0; i < holdOrderList.size(); i++) {

            this.totalRecCnt++;
            Map<String, Object> holdOrderInfo = holdOrderList.get(i);
            String cpoOrdNum = (String) holdOrderInfo.get("CPO_ORD_NUM");
            if (!hasValue(checkedCpoOrdNum)) {
                checkedCpoOrdNum = cpoOrdNum;
            }

            NWZC033001PMsg pMsg = setNWZC033001PMsg(holdOrderInfo);

            if (checkedCpoOrdNum.equals(cpoOrdNum)) {
                lock(holdOrderInfo);
                pMsgList.add(pMsg);
                continue;
            }

            if (!holdRelease(pMsgList)) {
                rollback();
            } else {
                commit();
            }

            lock(holdOrderInfo);
            pMsgList = new ArrayList<NWZC033001PMsg>();
            pMsgList.add(pMsg);

        }

        if (!pMsgList.isEmpty()) {
            if (!holdRelease(pMsgList)) {
                rollback();
            } else {
                commit();
            }
        }

        // 2019/09/02 QC#50316 Add Start
        // --------------------------------------------------
        // getInformation : pendingBillingHoldOrder
        // --------------------------------------------------
        List<Map<String, Object>> pendingBillingHoldOrderList = getPendingBillingHoldOrderInfo();

        for (int i = 0; i < pendingBillingHoldOrderList.size(); i++) {

            this.totalRecCnt++;
            Map<String, Object> holdOrderInfo = pendingBillingHoldOrderList.get(i);

            NWZC033001PMsg pMsg = setNWZC033001PMsg(holdOrderInfo);
            List<NWZC033001PMsg> pPendingBillingMsgList = new ArrayList<NWZC033001PMsg>();
            pPendingBillingMsgList.add(pMsg);

            if (!holdRelease(pPendingBillingMsgList)) {
                rollback();
            } else {
                commit();
            }
        }
        // 2019/09/02 QC#50316 Add End
    }

    @Override
    protected void termRoutine() {
        super.setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);

    }

    /**
     * execute holdRelease.
     * @param holdList List<Map<String, Object>>
     * @return true/normal. false/Abnormality.
     */
    private boolean holdRelease(List<NWZC033001PMsg> pMsgList) {

        boolean hasNotError = true;

        NWZC033001 api = new NWZC033001();

        api.execute(pMsgList, ONBATCH_TYPE.BATCH);

        for (NWZC033001PMsg pmsg : pMsgList) {
            boolean errorFlg = false;
            for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                if (pmsg.xxMsgIdList.no(i).xxMsgId.getValue().endsWith(MESSAGE_KIND_ERROR)) {
                    errorFlg = true;
                    hasNotError = false;
                    StringBuilder errMsg = new StringBuilder();
                    errMsg.append(api.getClass().getSimpleName()).append(" : Hold Release API : xxMsgId=")
                          .append(pmsg.xxMsgIdList.no(0).xxMsgId.getValue())
                          .append(", HLD_PK= ").append(pmsg.hldPk.getValue())
                          .append(", CPO_ORD_NUM= ").append(pmsg.cpoOrdNum.getValue())
                          .append(", CPO_DTL_LINE_NUM= ").append(pmsg.cpoDtlLineNum.getValue())
                          .append(", CPO_DTL_LINE_SUB_NUM= ").append(pmsg.cpoDtlLineSubNum.getValue())
                          .append(", SHPG_PLN_NUM= ").append(pmsg.cpoDtlLineSubNum.getValue());

                    S21InfoLogOutput.println(NWAM0189E, new String[] {errMsg.toString() });
                }
            }

            if (errorFlg) {
                this.errRecCnt++;
            } else {
                this.normalRecCnt++;
            }
        }
        return hasNotError;
    }

    /**
     * lock
     * @param holdOrderInfo Map<String, Object>
     */
    private void lock(Map<String, Object> holdOrderInfo) {

        String hldLvlCd = (String) holdOrderInfo.get("HLD_LVL_CD");
        String cpoOrdNum = (String) holdOrderInfo.get("CPO_ORD_NUM");
        String lineNum = (String) holdOrderInfo.get("CPO_DTL_LINE_NUM");
        String lineSubNum = (String) holdOrderInfo.get("CPO_DTL_LINE_SUB_NUM");
        String shpgPlnNum = (String) holdOrderInfo.get("SHPG_PLN_NUM");

        if (HLD_LVL.CPO_LEVEL.equals(hldLvlCd)) {
            findCpoByKeyForUpdate(cpoOrdNum);
        } else if (HLD_LVL.CPO_DETAIL_LEVEL.equals(hldLvlCd)) {
            findCpoByKeyForUpdate(cpoOrdNum);
            findCpoDtlByKeyForUpdate(cpoOrdNum, lineNum, lineSubNum);
        } else if (HLD_LVL.SHIPPING_PLAN_LEVEL.equals(hldLvlCd)) {
            findCpoByKeyForUpdate(cpoOrdNum);
            findCpoDtlByKeyForUpdate(cpoOrdNum, lineNum, lineSubNum);
            findShpgPlnByKeyForUpdate(shpgPlnNum);
        }
    }

    /**
     * setNWZC033001PMsg
     * @param holdOrderInfo Map<String, Object>
     */
    private NWZC033001PMsg setNWZC033001PMsg(Map<String, Object> holdOrderInfo) {

        NWZC033001PMsg pMsg = new NWZC033001PMsg();
        setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(pMsg.hldPk, (BigDecimal) holdOrderInfo.get("HLD_PK"));
        setValue(pMsg.cpoOrdNum, (String) holdOrderInfo.get("CPO_ORD_NUM"));
        setValue(pMsg.cpoDtlLineNum, (String) holdOrderInfo.get("CPO_DTL_LINE_NUM"));
        setValue(pMsg.cpoDtlLineSubNum, (String) holdOrderInfo.get("CPO_DTL_LINE_SUB_NUM"));
        setValue(pMsg.shpgPlnNum, (String) holdOrderInfo.get("SHPG_PLN_NUM"));
        setValue(pMsg.slsDt, getSalesDate());
        setValue(pMsg.hldRelRsnCd, HLD_REL_RSN.AUTO);
        return pMsg;

    }

    /**
     * isGlobalCompanyCode.
     * @return true/has GlobalCompanyCode. false/has NOT
     * GlobalCompanyCode.
     */
    private boolean isGlobalCompanyCode() {

        String glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(glblCmpyCd)) {
            return false;
        }

        return ZYPCodeDataUtil.hasCodeValue(GLBL_CMPY.class, glblCmpyCd, glblCmpyCd);
    }

    private String getSalesDate() {

        return ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
    }

    /**
     * getHoldOrderInfo.
     * @return search results.
     */
    private List<Map<String, Object>> getHoldOrderInfo() {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("slsDt", getSalesDate());

        return ssmBatchClient.queryObjectList("getHoldOrderInfo", param);
    }

    // 2019/09/02 QC#50316 Add Start
    private List<Map<String, Object>> getPendingBillingHoldOrderInfo() {

        String crRebillDummyWhCdCsv = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_CR_AND_BILL_ONLY_DUMMY_WH_CD, getGlobalCompanyCode());
        List<String> crRebillDummyWhCdList = new ArrayList<String>();
        if (crRebillDummyWhCdCsv != null) {
            String[] crRebillDummyWhCd = crRebillDummyWhCdCsv.split(",");
            crRebillDummyWhCdList = Arrays.asList(crRebillDummyWhCd);
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("ordCatgCtxTp", ORD_CATG_CTX_TP.AUTO_PENDING_BILLING_HOLD_TARGET);
        param.put("hldRsnCd", HLD_RSN.AUTO_PENDING_BILLING);
        param.put("ignoreShpgStsCd", SHPG_STS.CANCELLED);
        param.put("revRecogMethCdPOD", REV_RECOG_METH.POD);
        param.put("revRecogMethCdInstall", REV_RECOG_METH.INSTALL);
        param.put("configCatgCdOutbound", CONFIG_CATG.OUTBOUND);
        param.put("setParentLineSubNum", SET_PARENT_LINE_SUB_NUM);
        param.put("crRebillDummyWhCdList", crRebillDummyWhCdList);

        return ssmBatchClient.queryObjectList("getPendingBillingHoldOrderInfo", param);
    }
    // 2019/09/02 QC#50316 Add End

    /**
     * <pre>
     * Perform the exclusive control of the CPO table key search.
     * </pre>
     * @param cpoOrdNum String
     * @return search results.
     */
    private void findCpoByKeyForUpdate(String cpoOrdNum) {

        CPOTMsg inMsg = new CPOTMsg();

        inMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        inMsg.cpoOrdNum.setValue(cpoOrdNum);

        S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * <pre>
     * Perform the exclusive control of the CPO_DTL table key search.
     * </pre>
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum String
     * @return search results.
     */
    private void findCpoDtlByKeyForUpdate(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {

        CPO_DTLTMsg inMsg = new CPO_DTLTMsg();

        inMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        inMsg.cpoOrdNum.setValue(cpoOrdNum);
        inMsg.cpoDtlLineNum.setValue(cpoDtlLineNum);
        inMsg.cpoDtlLineSubNum.setValue(cpoDtlLineSubNum);

        S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * <pre>
     * Perform the exclusive control of the SHPG_PLN table key search.
     * </pre>
     * @param shpgPlnNum String
     * @return search results.
     */
    private void findShpgPlnByKeyForUpdate(String shpgPlnNum) {

        SHPG_PLNTMsg inMsg = new SHPG_PLNTMsg();

        inMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        inMsg.shpgPlnNum.setValue(shpgPlnNum);

        S21FastTBLAccessor.findByKeyForUpdate(inMsg);
    }
}
