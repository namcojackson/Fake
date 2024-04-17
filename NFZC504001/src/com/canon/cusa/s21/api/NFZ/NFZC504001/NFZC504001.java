/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFZ.NFZC504001;

import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.AP_INV_TP_CD_00;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.AP_VND_INV_SQ_NUM_00;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.DATE_TIME_PATTERN;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFBM0243E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFBM0244E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFBM0245E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFBM0256E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFBM0257E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFBM0258E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NFZM0027E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.NPZM0021E;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.PARAMS_AP_VND_CD_KEY;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.PARAMS_AP_VND_INV_NUM_KEY;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.PMT_HLD_REL_CMNT_TXT_THEREFORE;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.PMT_HLD_REL_PSN_CD_THEREFORE;
import static com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant.YYYYMMDD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_INV_PMT_HLDTMsg;
import business.db.CM_INV_PMT_HLDTMsgArray;
import business.parts.NFZC504001PMsg;
import business.parts.NPZC004001PMsg;

import com.canon.cusa.s21.api.NFZ.NFZC504001.constant.NFZC504001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Hold Release API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/23   Fujitsu         S.Iidaka        Create
 * 2016/10/25   Fujitsu         S.Iidaka        Update          S21_NA#15551
 * 2016/10/26   Fujitsu         S.Iidaka        Update          S21_NA#15581
 * 2016/11/29   Fujitsu         W.Honda         Update          S21_NA#16268
 * 2018/04/06   CITS            T.Kikuhara      Update          QC#20316
 * 2018/10/31   Hitachi         Y.Takeno        Update          QC#28988
 * </pre>
 */
public class NFZC504001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** SSM client */
    private S21SsmBatchClient ssmBatchClient;

    /** Attached Document Group Text */
    private String attDataGrpTxt;

    /** AP Vendor Code */
    private String apVndCd;

    /** AP Vendor Invoice Number */
    private String apVndInvNum;

    /**
     * Constructor
     */
    public NFZC504001() {
        super();
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Hold Release API (List)
     * </pre>
     * @param pMsgList Input parameter list.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final List<NFZC504001PMsg> pMsgList, final ONBATCH_TYPE onBatchType) {
        for (NFZC504001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchType);
        }
    }

    /**
     * <pre>
     * Hold Release API
     * </pre>
     * @param pMsg Input parameter.
     * @param onBatchType Kind of Online or Batch.
     */
    public void execute(final NFZC504001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        // Create Message Map
        msgMap = new S21ApiMessageMap(pMsg);

        try {
            // Parameter Check
            vldMandatoryCheck();
            if (hasErr()) {
                return;
            }

            getAttDataGrpTxtByDocIdCatgNum();
            if (hasErr()) {
                return;
            }

            getValidInvoiceCount();
            if (hasErr()) {
                return;
            }

            releaseHld();
            //Releae Hold
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));

            // locked by another user
        } catch (EZDDBRecordLockedException e) {
            throw e;

        } finally {

            // Flush Message Map.
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * Check has error or not.(error exist then true) 
     * </pre>
     * @return result
     */
    private boolean hasErr() {

        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Validate Mandatory Parameters Check
     * </pre>
     * @param
     */
    private void vldMandatoryCheck() {

        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NFBM0243E);
        }

        if (!hasValue(pMsg.docMgtDocId)) {
            setErrMsg(pMsg, NFBM0244E);
        }

        if (!hasValue(pMsg.docMgtCatgNum)) {
            setErrMsg(pMsg, NFBM0245E);
        }

        if (!hasValue(pMsg.docMgtBizDocNum)) {
            setErrMsg(pMsg, NFBM0256E);
        }

        return;
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg NFZC504001PMsg
     * @param msgId String
     */
    private void setErrMsg(NFZC504001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg NFZC504001PMsg
     * @param msgId String
     * @param msgPrms String[]
     */
    private void setErrMsg(NFZC504001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    /**
     * <pre>
     * Cut Error Message
     * </pre>
     * @param msg String
     * @return Error Message String within MAX_MSG_LEN
     */
    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > NFZC504001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NFZC504001Constant.MAX_MSG_LEN);
        }
        return msg;
    }

    /**
     * <pre>
     * getAttDataGrpTxtByDocIdCatgNum
     * </pre>
     * @param
     */
    private void getAttDataGrpTxtByDocIdCatgNum() {
        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("docMgtDocId", pMsg.docMgtDocId.getValue().toString()); // 2016/10/25 S21_NA#15551 Mod
        param.put("docMgtCatgNum", pMsg.docMgtCatgNum.getValue());

        List<Map<String, String>> rsltList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getAttDataGrpTxtByDocIdCatgNum", param);
        if (rsltList != null && rsltList.size() > 0) {
            attDataGrpTxt = rsltList.get(0).get("ATT_DATA_GRP_TXT");
            if (!hasValue(attDataGrpTxt)) {
                setErrMsg(pMsg, NFBM0257E);
            }

        } else {
            setErrMsg(pMsg, NFBM0257E);
        }
    }

    /**
     * <pre>
     * getValidInvoiceCount
     * </pre>
     * @param
     */
    private void getValidInvoiceCount() {
        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        int apVndCdSIndexStart    = this.attDataGrpTxt.indexOf(pMsg.glblCmpyCd.getValue() + PARAMS_AP_VND_CD_KEY);
        apVndCdSIndexStart = apVndCdSIndexStart + (pMsg.glblCmpyCd.getValue() + PARAMS_AP_VND_CD_KEY).length();
        int apVndCdSIndexEnd      = this.attDataGrpTxt.indexOf(PARAMS_AP_VND_INV_NUM_KEY);
        int apVndIndNumIndexStart = apVndCdSIndexEnd + (PARAMS_AP_VND_INV_NUM_KEY).length();

        if (apVndCdSIndexStart == -1 || apVndCdSIndexEnd == -1 || apVndIndNumIndexStart == -1) {
            setErrMsg(pMsg, NFBM0258E);
            return;
        }
        apVndCd = attDataGrpTxt.substring(apVndCdSIndexStart, apVndCdSIndexEnd);
        apVndInvNum = attDataGrpTxt.substring(apVndIndNumIndexStart);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("apVndCd", apVndCd);
        param.put("apVndInvNum", apVndInvNum);
        param.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);

        if (1 !=  (Integer) ssmBatchClient.queryObject("getValidInvoiceCount", param)) {
            setErrMsg(pMsg, NFBM0258E);
        }
    }

    /**
     * <pre>
     * releaseHld
     * </pre>
     * @param
     */
    private void releaseHld() {
        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        CM_INV_PMT_HLDTMsg cmInvPmtHldTMsgFirst = null;

        if (!hasThereforeHld()) {
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
            return;
        }

        // 2018/04/06 QC#20316 ADD START
        List<String> thereforApVndInvLineNumList = getThereforeHldApVndInvLineNumList();
        if (thereforApVndInvLineNumList == null) {
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
            return;
        }
        // 2018/04/06 QC#20316 ADD END

        // 2018/04/06 QC#20316 MOD START
        CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
        for (String apVndInvLineNum : thereforApVndInvLineNumList) {
            // Detail
            cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, apVndCd);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, apVndInvNum);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, apVndInvLineNum);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, PMT_HLD.THEREFORE);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, PMT_HLD_RSN.THEREFORE);
            cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvPmtHldTMsg);
            if (cmInvPmtHldTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, PMT_HLD_REL_PSN_CD_THEREFORE); // 2016/10/26 S21_NA#15581 Mod
                // START 2018/10/31 [QC#28988, MOD]
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, ZYPDateUtil.getSalesDate());
                // END   2018/10/31 [QC#28988, MOD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, PMT_HLD_REL_RSN.INVOICE_HOLD_RELEASE);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, PMT_HLD_REL_CMNT_TXT_THEREFORE);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                EZDTBLAccessor.update(cmInvPmtHldTMsg);
                // 2016/11/29 S21_NA#15581 Add Start
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                    setErrMsg(pMsg, NFZM0027E, new String[] {cmInvPmtHldTMsg.getTableName()});
                    return;
                }
                // 2016/11/29 S21_NA#15581 Add End
            }
        }
        // 2018/04/06 QC#20316 MOD END

        if (hasHld()) {
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
            return;
        }

        // First Hold Record
        cmInvPmtHldTMsgFirst = getFirstHld(cmInvPmtHldTMsg);
        if (cmInvPmtHldTMsgFirst == null) {
            ZYPEZDItemValueSetter.setValue(pMsg.docMgtIntfcProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
            return;
        }

        // 2018/04/06 QC#20316 DELL START
        // Header
        //CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, apVndCd);
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, apVndInvNum);
        //ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        //cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdrTMsg);
        //if (cmInvAcctHdrTMsg != null) {
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, PMT_HLD_REL_PSN_CD_THEREFORE); // 2016/10/26 S21_NA#15581 Mod
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, cmInvPmtHldTMsgFirst.pmtHldRelRsnCd.getValue());
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, cmInvPmtHldTMsgFirst.pmtHldRelCmntTxt.getValue());
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
        //    EZDTBLAccessor.update(cmInvAcctHdrTMsg);
        //    // 2016/11/29 S21_NA#15581 Add Start
        //    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
        //        setErrMsg(pMsg, NFZM0027E, new String[] {cmInvAcctHdrTMsg.getTableName()});
        //        return;
        //    }
        //    // 2016/11/29 S21_NA#15581 Add End
        //}
        // 2018/04/06 QC#20316 DELL END

        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, apVndCd);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, apVndInvNum);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
        if (cmApInvHdrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
            // 2018/04/06 QC#20316 ADD START
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
            // 2018/04/06 QC#20316 ADD END
            EZDTBLAccessor.update(cmApInvHdrTMsg);
            // 2016/11/29 S21_NA#15581 Add Start
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
                setErrMsg(pMsg, NFZM0027E, new String[] {cmApInvHdrTMsg.getTableName()});
                return;
            }
            // 2016/11/29 S21_NA#15581 Add End
        }

        List<Map<String, Object>> listCmApInvDtl = (List<Map<String, Object>>)getListCmApInvDtlByPartialKey(pMsg.glblCmpyCd.getValue(), apVndCd, apVndInvNum, AP_VND_INV_SQ_NUM_00);
        for (int j = 0; j < listCmApInvDtl.size(); j++) {
            Map<String, Object> map = (Map<String, Object>)listCmApInvDtl.get(j);
            String msgId = updatePoInformation(pMsg.glblCmpyCd.getValue(), (String)map.get("PO_NUM"), (String)map.get("PO_ORD_DTL_LINE_NUM"), (BigDecimal)map.get("INV_QTY"), (String)map.get("MDSE_CD"));
            if (msgId != null) {
                setErrMsg(pMsg, msgId);
            }
        }

    }

    /**
     * <pre>
     * hasThereforeHld
     * </pre>
     * @param
     * @return Hold exits then true
     */
    private boolean hasThereforeHld() {
        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("apVndCd", apVndCd);
        param.put("apVndInvNum", apVndInvNum);
        param.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        param.put("pmtHldCd", PMT_HLD.THEREFORE);
        param.put("pmtHldRsnCd", PMT_HLD_RSN.THEREFORE);
        param.put("pmtHldFlg", ZYPConstant.FLG_ON_Y);

        if (0 <  (Integer) ssmBatchClient.queryObject("getThereforeHldCount", param)) {
            return true;
        }

        return false;
    }

    // 2018/04/12 QC#20316 ADD START
    /**
     * <pre>
     * getThereforeHldApVndInvLineNumList
     * </pre>
     * @param
     * @return Hold exits then true
     */
    private List<String> getThereforeHldApVndInvLineNumList() {
        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("apVndCd", apVndCd);
        param.put("apVndInvNum", apVndInvNum);
        param.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        param.put("pmtHldCd", PMT_HLD.THEREFORE);
        param.put("pmtHldRsnCd", PMT_HLD_RSN.THEREFORE);
        param.put("pmtHldFlg", ZYPConstant.FLG_ON_Y);

        List<String> rsltList = (List<String>) ssmBatchClient.queryObjectList("getThereforeHldApVndInvLineNumList", param);

        if (rsltList != null && rsltList.size() > 0) {
            return rsltList;
        }
        return new ArrayList<String>();
    }
    // 2018/04/12 QC#20316 ADD END

    /**
     * <pre>
     * hasHld
     * </pre>
     * @param
     * @return Hold exits then true
     */
    private boolean hasHld() {
        NFZC504001PMsg pMsg = (NFZC504001PMsg) msgMap.getPmsg();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("apVndCd", apVndCd);
        param.put("apVndInvNum", apVndInvNum);
        param.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        param.put("pmtHldFlg", ZYPConstant.FLG_ON_Y);

        if (0 <  (Integer) ssmBatchClient.queryObject("getHldCount", param)) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * getFirstHld
     * </pre>
     * @param
     * @return first record in CM_INV_PMT_HLD queried by glblCmpyCd, apVndCd, apVndInvNum, apVndInvSqNum
     */
    private CM_INV_PMT_HLDTMsg getFirstHld(CM_INV_PMT_HLDTMsg cmInvPmtHldTMsgFirst) {
        CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
        cmInvPmtHldTMsg.setSQLID("001");
        cmInvPmtHldTMsg.setConditionValue("glblCmpyCd01", cmInvPmtHldTMsgFirst.glblCmpyCd.getValue());
        cmInvPmtHldTMsg.setConditionValue("apVndCd01", cmInvPmtHldTMsgFirst.apVndCd.getValue());
        cmInvPmtHldTMsg.setConditionValue("apVndInvNum01", cmInvPmtHldTMsgFirst.apVndInvNum.getValue());
        cmInvPmtHldTMsg.setConditionValue("apVndInvSqNum01", cmInvPmtHldTMsgFirst.apVndInvSqNum.getValue());
        CM_INV_PMT_HLDTMsgArray cmInvPmtHldTMsgArray = (CM_INV_PMT_HLDTMsgArray) S21ApiTBLAccessor.findByCondition(cmInvPmtHldTMsg);

        if (cmInvPmtHldTMsgArray.getValidCount() == 0) {
            return null;
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsgArray.no(0).getReturnCode())) {
            return null;
        }

        return cmInvPmtHldTMsgArray.no(0);
    }

    /**
     * Get CM_AP_INV_DTL list by partial keys.
     * @param glblCmpyCd String 
     * @param apVndCd String 
     * @param apVndInvNum String 
     * @param apVndInvSqNum String 
     */
    public List<Map<String, Object>> getListCmApInvDtlByPartialKey(String glblCmpyCd, String apVndCd, String apVndInvNum, String apVndInvSqNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("apVndCd", apVndCd);
        param.put("apVndInvNum", apVndInvNum);
        param.put("apVndInvSqNum", apVndInvSqNum);

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getListCmApInvDtlByPartialKey", param);

        if (rsltList != null && rsltList.size() > 0) {
            return rsltList;
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * updatePoInformation
     * @param glblCmpyCd String
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param poInvQty BigDecimal
     * @param mdseCd String
     * @return xxMsgId String
     */
    public String updatePoInformation(String glblCmpyCd, String poOrdNum, String poOrdDtlLineNum, BigDecimal poInvQty, String mdseCd) {
        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, poInvQty);
        NPZC004001 apiPOUpdate = new NPZC004001();
        apiPOUpdate.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // Error
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)
                && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)
                ) {
                    return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                }
            }
        }
        return null;
    }
}
