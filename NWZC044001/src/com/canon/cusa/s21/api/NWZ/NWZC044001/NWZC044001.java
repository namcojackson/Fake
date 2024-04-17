/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC044001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.HLD_APPLY_RSNTMsg;
import business.db.HLD_RSNTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NAZC006001PMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC400001PMsg;

import com.canon.cusa.s21.api.NAZ.NAZC006001.NAZC006001;
import com.canon.cusa.s21.api.NAZ.NAZC006001.NAZC006001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC400001.NWZC400001;
import com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Add Hold Info API
 * 
 * Date          Company     Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009    Fujitsu     K.Sato         Create          N/A
 * 12/29/2009    Fujitsu     K.Tajima       Update          N/A (refactoring)
 * 05/10/2010    Fujitsu     K.Tajima       Update          2663 (performance tuning)
 * 09/16/2015    Fujitsu     T.Yoshida      Update          For CSA
 * 12/24/2015    Fujitsu     T.Yoshida      Update          CSA QC#2397
 * 01/05/2016    Fujitsu     H.Nagashima    Update          CSA QC#2692
 * 02/26/2016    Fujitsu     T.Ishii        Update          S21_NA#3960
 * 2016/04/21    Fujitsu     M.Yamada       Update          S21_NA#7257
 * 2016/09/20    Fujitsu     Y.Kanefusa     Update          S21_NA#11306
 * 2017/01/11    Fujitsu     R.Nakamura     Update          S21_NA#16954
 * 2017/02/08    Fujitsu     T.Aoi          Update          S21_NA#17287
 * 2017/08/31    Fujitsu     S.Takami       Update          S21_NA#20796
 * 2019/12/03    Fujitsu     C.Hara         Update          QC#54861
 *</pre>
 */
public class NWZC044001 extends S21ApiCommonBase implements HLD_LVL {

    /** Data Global Company Code is not entered. */
    public static final String NWZM0163E = "NWZM0163E";

    /** Order Number is not entered. */
    public static final String NWZM0002E = "NWZM0002E";

    /** Order Line Number is not entered. */
    public static final String NWZM0003E = "NWZM0003E";

    /** Order Line Sub Number is not entered. */
    public static final String NWZM0004E = "NWZM0004E";

    /** The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** The data does not exist in CPO_DTL. */
    public static final String NWZM0074E = "NWZM0074E";

    /** The data does not exist in SHPG_PLN. */
    public static final String NWZM0075E = "NWZM0075E";

    /** It failed to update the HLD. */
    public static final String NWZM0080E = "NWZM0080E";

    /** The "Hold Reason Code" does not exist in the Master. */
    public static final String NWZM0441E = "NWZM0441E";

    /** The "Hold Detail Text" is not entered. */
    public static final String NWZM0442E = "NWZM0442E";

    /** Registration cannot be completed because unreleased Hold information exists. */
    public static final String NWZM0443E = "NWZM0443E";

    /** Sales Date is not entered. */
    public static final String NWZM0445E = "NWZM0445E";

    /** "Hold Reason Code" is not entered. */
    public static final String NWZM0649E = "NWZM0649E";

    /** Relation between 'Hold' and 'Hold Level' is incorrect. */
    public static final String NWZM0659E = "NWZM0659E";

    /** It failed to update the DS_HLD. */
    public static final String NWZM1499E = "NWZM1499E";

    /** The "Hold Apply Reason Code" does not exist in the Master. */
    public static final String NWZM2206E = "NWZM2206E";

    /** Hold Level Code */
    private String hldLvlCd = null;

    /** Hold Quantity */
    private int hldQty = 0;

    /** On Batch Type */
    private ONBATCH_TYPE batchType = null;;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    // 2019/12/03 QC#54861 Add Start
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;
    // 2019/12/03 QC#54861 Add End

    /**
     * Constructor
     */
    public NWZC044001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass()); // 2019/12/03 QC#54861 Add
    }

    /**
     * add Hold Info
     * @param param NWZC044001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC044001PMsg param, final ONBATCH_TYPE onBatchType) {

        try {
            // initialize global variables
            this.msgMap = new S21ApiMessageMap(param);
            this.batchType = onBatchType;

            // 2019/12/03 QC#54861 Add Start
            // check Non IB Trackable for S03
            if (checkNonInstaleBaseForBillingHold(param)) {
                return;
            }
            // 2019/12/03 QC#54861 Add End

            // check input parameters
            if (!checkReqParams(param)) {
                return;
            }

            // add Hold info
            if (!addHld(param)) {
                return;
            }

            // print business process log
            printBizProcLog(param);

            // call [NWZC004001 : Validation Process Manager API]
            if (!callValidProcMgrAPI(param)) {
                return;
            }

        } finally {
            if (this.msgMap != null) {
                this.msgMap.flush();
            }
        }
    }

    /**
     * add Hold Info (List<PMsg>)
     * @param params List<NWZC044001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC044001PMsg> params, final ONBATCH_TYPE onBatchType) {

        // ********** [Def# 2663] K.Tajima - START
        if (params == null || params.size() == 0) {
            return;
        }

        this.batchType = onBatchType;
        final Map<String, NWZC044001PMsg> callValidProcMgrApiPMsgMap = new HashMap<String, NWZC044001PMsg>();

        for (int i = 0; i < params.size(); i++) {
            final NWZC044001PMsg pMsg = params.get(i);
            this.msgMap = new S21ApiMessageMap(pMsg);
            this.hldLvlCd = null;
            this.hldQty = 0;

            // 2019/12/03 QC#54861 Add Start
            // check Non IB Trackable for S03
            if (checkNonInstaleBaseForBillingHold(pMsg)) {
                continue;
            }
            // 2019/12/03 QC#54861 Add End

            // check input parameters
            if (checkReqParams(pMsg)) {
                // add Hold info
                if (addHld(pMsg)) {
                    // print business process log
                    printBizProcLog(pMsg);
                }
            }

            // has errors.
            if (this.msgMap.getMsgMgr().getXxMsgIdListSize() > 0) {
                this.msgMap.flush();
                return;
            }

            final String cpoOrdNum = pMsg.cpoOrdNum.getValue();
            if (!callValidProcMgrApiPMsgMap.containsKey(cpoOrdNum)) {
                callValidProcMgrApiPMsgMap.put(cpoOrdNum, pMsg);
            }
        }

        // --------------------------------------------------
        // call [NWZC004001 : Validation Process Manager API]
        // --------------------------------------------------
        for (Entry<String, NWZC044001PMsg> entry : callValidProcMgrApiPMsgMap.entrySet()) {
            final NWZC044001PMsg pMsg = entry.getValue();
            final NWZC044001PMsg reqPMsg = new NWZC044001PMsg();
            EZDMsg.copy(pMsg, null, reqPMsg, null);
            reqPMsg.cpoDtlLineNum.clear();
            reqPMsg.cpoDtlLineSubNum.clear();
            reqPMsg.shpgPlnNum.clear();

            this.msgMap = new S21ApiMessageMap(reqPMsg);
            if (!callValidProcMgrAPI(reqPMsg)) {
                this.msgMap.flush();
                EZDMsg.copy(reqPMsg.xxMsgIdList, null, pMsg.xxMsgIdList, null);
                return;
            }
        }
        // ********** [Def# 2663] K.Tajima - E N D
    }

    private boolean addHld(NWZC044001PMsg param) {

        BigDecimal hldPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.HLD_SQ);

        if (!insertHld(param, hldPk)) {
            return false;
        }

        return true;
    }

    /**
     * Insert HLD.
     * @param param NWZC044001PMsg
     * @param hldPk Hold Primary Key
     * @return If insert successful, return true.
     */
    private boolean insertHld(NWZC044001PMsg param, BigDecimal hldPk) {

        HLDTMsg hldTMsg = new HLDTMsg();
        ZYPEZDItemValueSetter.setValue(hldTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.hldPk, hldPk);
        ZYPEZDItemValueSetter.setValue(hldTMsg.hldRsnCd, param.hldRsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.hldDt, param.slsDt.getValue());
        if (CPO_DETAIL_LEVEL.equals(this.hldLvlCd) || SHIPPING_PLAN_LEVEL.equals(this.hldLvlCd)) {
            hldTMsg.hldQty.setValue(this.hldQty);
            ZYPEZDItemValueSetter.setValue(hldTMsg.hldQty, BigDecimal.valueOf(this.hldQty));
        }
        ZYPEZDItemValueSetter.setValue(hldTMsg.trxSrcTpCd, param.trxSrcTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.cpoOrdNum, param.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.cpoDtlLineNum, param.cpoDtlLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.shpgPlnNum, param.shpgPlnNum.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.relFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(hldTMsg.rvwFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(hldTMsg.hldUntilDt, param.hldUntilDt.getValue());
        ZYPEZDItemValueSetter.setValue(hldTMsg.hldApplyRsnCd, param.hldApplyRsnCd.getValue());
        String hldApplyPsnCd = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        int hldApplyPsnCdLength = new HLDTMsg().getAttr("hldApplyPsnCd").getDigit();
        if (hldApplyPsnCd.length() > hldApplyPsnCdLength) {
            hldApplyPsnCd = hldApplyPsnCd.substring(0, hldApplyPsnCdLength);
        }
        ZYPEZDItemValueSetter.setValue(hldTMsg.hldApplyPsnCd, hldApplyPsnCd);

        // 2017/08/31 S21_NA#20796 Add Start
        ZYPEZDItemValueSetter.setValue(hldTMsg.hldDtlTxt, param.hldDtlTxt);
        // 2017/08/31 S21_NA#20796 Add End
        S21ApiTBLAccessor.insert(hldTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hldTMsg.getReturnCode())) {
            addMsgId(NWZM0080E);
            return false;
        }

        return true;
    }


    private void addMsgId(String msgId) {

        this.msgMap.addXxMsgId(msgId);
    }

    private boolean callValidProcMgrAPI(NWZC044001PMsg param) {

        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        cpoTMsg.cpoOrdNum.setValue(param.cpoOrdNum.getValue());
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);
        if (cpoTMsg == null) {
            addMsgId(NWZM0002E);
            return false;
        }

        if (ORD_HDR_STS.SAVED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            return true;
        }

        if (checkPureReturn(param)) {
            NWZC400001PMsg apiPMsg = new NWZC400001PMsg();
            apiPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            apiPMsg.slsDt.setValue(param.slsDt.getValue());
            apiPMsg.xxProcMd.setValue(NWZC400001Constant.REQ_TYPE_RECAL);
            apiPMsg.cpoOrdNum_I.setValue(param.cpoOrdNum.getValue());
            apiPMsg.dsCpoRtrnLineNum_I.setValue(param.cpoDtlLineNum.getValue());        // QC#11306 2016/09/20 Add
            apiPMsg.dsCpoRtrnLineSubNum_I.setValue(param.cpoDtlLineSubNum.getValue());  // QC#11306 2016/09/20 Add

            // Call Return Validation API
            new NWZC400001().execute(apiPMsg, this.batchType);

            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                    addMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }

            return true;
        }

        // Call Retail Validation API
        String cpoOrdTpCd = cpoTMsg.cpoOrdTpCd.getValue();
        if (CPO_ORD_TP.PURCHASE.equals(cpoOrdTpCd) || CPO_ORD_TP.RENTAL.equals(cpoOrdTpCd) || CPO_ORD_TP.LEASE.equals(cpoOrdTpCd) || CPO_ORD_TP.SERVICE.equals(cpoOrdTpCd) || CPO_ORD_TP.RETAIL_TRIAL.equals(cpoOrdTpCd)
                || CPO_ORD_TP.RETAIL_ORDER.equals(cpoOrdTpCd)) {

            NAZC006001PMsg apiPMsg = new NAZC006001PMsg();
            apiPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            apiPMsg.slsDt.setValue(param.slsDt.getValue());
            apiPMsg.xxProcMd.setValue(NAZC006001Constant.PROC_MODE_RECALC);
            apiPMsg.cpoOrdNum.setValue(param.cpoOrdNum.getValue());
            apiPMsg.cpoDtlLineNum.setValue(param.cpoDtlLineNum.getValue());
            apiPMsg.cpoDtlLineSubNum.setValue(param.cpoDtlLineSubNum.getValue());
            apiPMsg.shpgPlnNum.setValue(param.shpgPlnNum.getValue());

            new NAZC006001().execute(apiPMsg, this.batchType);

            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                    addMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }

            // Call Validation Process Manager API
        } else {

            NWZC004001PMsg apiPMsg = new NWZC004001PMsg();
            apiPMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            apiPMsg.slsDt.setValue(param.slsDt.getValue());
            apiPMsg.xxRqstTpCd.setValue(NWZC004001.VAL_TP_RC);
            apiPMsg.cpoOrdNum_I.setValue(param.cpoOrdNum.getValue());
            apiPMsg.cpoDtlLineNum_I.setValue(param.cpoDtlLineNum.getValue());
            apiPMsg.cpoDtlLineSubNum_I.setValue(param.cpoDtlLineSubNum.getValue());
            apiPMsg.shpgPlnNum_I.setValue(param.shpgPlnNum.getValue());

            new NWZC004001().execute(apiPMsg, this.batchType);

            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < apiPMsg.xxMsgIdList.getValidCount(); i++) {
                    addMsgId(apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }
        }

        return true;
    }

    /**
     * Check pure return.
     * @param param NWZC044001PMsg
     * @return If "pure return" data, return true.
     */
    private boolean checkPureReturn(NWZC044001PMsg param) {

        if(TRX_SRC_TP.WHOLE_SALES_RETURN.equals(param.trxSrcTpCd.getValue())){
            return true;
        }
        final CPO_DTLTMsg condition = new CPO_DTLTMsg();
        condition.setSQLID("018");
        condition.setMaxCount(1);
        condition.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        condition.setConditionValue("cpoOrdNum01", param.cpoOrdNum.getValue());
        condition.setConditionValue("ordLineStsCd01", ORD_LINE_STS.CANCELLED);

        return S21ApiTBLAccessor.count(condition) == 0;
    }

    private boolean checkReqParams(NWZC044001PMsg param) {

        // GLBL_CMPY_CD
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            addMsgId(NWZM0163E);
            return false;
        }

        // CPO_ORD_NUM
        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum)) {
            addMsgId(NWZM0002E);
            return false;
        }

        // HLD_RSN_CD
        if (!ZYPCommonFunc.hasValue(param.hldRsnCd)) {
            addMsgId(NWZM0649E);
            return false;
        }

        // SLS_DT
        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            addMsgId(NWZM0445E);
            return false;
        }

        // CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM
        if (!ZYPCommonFunc.hasValue(param.cpoDtlLineNum) && ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum)) {
            addMsgId(NWZM0003E);
            return false;
        }

        // CPO_DTL_LINE_NUM, CPO_DTL_LINE_SUB_NUM
        if (ZYPCommonFunc.hasValue(param.cpoDtlLineNum) && !ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum)) {
            addMsgId(NWZM0004E);
            return false;
        }

        // HLD_RSN
        HLD_RSNTMsg hldRsnMsg = new HLD_RSNTMsg();
        hldRsnMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        hldRsnMsg.hldRsnCd.setValue(param.hldRsnCd.getValue());
        hldRsnMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(hldRsnMsg);
        if (hldRsnMsg == null) {
            addMsgId(NWZM0441E);
            return false;
        }

        // set HLD_LVL_CD
        if (ZYPCommonFunc.hasValue(param.cpoOrdNum) && !ZYPCommonFunc.hasValue(param.cpoDtlLineNum) && !ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum) && !ZYPCommonFunc.hasValue(param.shpgPlnNum)) {
            this.hldLvlCd = CPO_LEVEL;
        } else if (ZYPCommonFunc.hasValue(param.cpoOrdNum) && ZYPCommonFunc.hasValue(param.cpoDtlLineNum) && ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum) && !ZYPCommonFunc.hasValue(param.shpgPlnNum)) {
            this.hldLvlCd = CPO_DETAIL_LEVEL;
        } else if (ZYPCommonFunc.hasValue(param.cpoOrdNum) && ZYPCommonFunc.hasValue(param.cpoDtlLineNum) && ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum) && ZYPCommonFunc.hasValue(param.shpgPlnNum)) {
            this.hldLvlCd = SHIPPING_PLAN_LEVEL;
        }

        if (!hldRsnMsg.hldLvlCd.getValue().equals(this.hldLvlCd)) {
            addMsgId(NWZM0659E);
            return false;
        }

        // CPO
        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        cpoTMsg.cpoOrdNum.setValue(param.cpoOrdNum.getValue());
        cpoTMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoTMsg);
        if (cpoTMsg == null) {
            addMsgId(NWZM0073E);
            return false;
        }

        if (S21StringUtil.isEquals(param.trxSrcTpCd.getValue(), TRX_SRC_TP.WHOLE_SALES_RETURN)) {

            // for return
            if (CPO_DETAIL_LEVEL.equals(this.hldLvlCd) || SHIPPING_PLAN_LEVEL.equals(this.hldLvlCd)) {
                DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtl = new DS_CPO_RTRN_DTLTMsg();
                dsCpoRtrnDtl.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                dsCpoRtrnDtl.cpoOrdNum.setValue(param.cpoOrdNum.getValue());
                dsCpoRtrnDtl.dsCpoRtrnLineNum.setValue(param.cpoDtlLineNum.getValue());
                dsCpoRtrnDtl.dsCpoRtrnLineSubNum.setValue(param.cpoDtlLineSubNum.getValue());
                dsCpoRtrnDtl = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoRtrnDtl);
                if (dsCpoRtrnDtl == null) {
                    addMsgId(NWZM0074E);
                    return false;
                }
                this.hldQty = dsCpoRtrnDtl.ordQty.getValueInt();
            }
        } else {

            // CPO_DTL
            if (CPO_DETAIL_LEVEL.equals(this.hldLvlCd) || SHIPPING_PLAN_LEVEL.equals(this.hldLvlCd)) {
                CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                cpoDtlTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                cpoDtlTMsg.cpoOrdNum.setValue(param.cpoOrdNum.getValue());
                cpoDtlTMsg.cpoDtlLineNum.setValue(param.cpoDtlLineNum.getValue());
                cpoDtlTMsg.cpoDtlLineSubNum.setValue(param.cpoDtlLineSubNum.getValue());
                cpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(cpoDtlTMsg);
                if (cpoDtlTMsg == null) {
                    addMsgId(NWZM0074E);
                    return false;
                }
                this.hldQty = cpoDtlTMsg.ordQty.getValueInt();
            }

            // SHPG_PLN
            if (SHIPPING_PLAN_LEVEL.equals(this.hldLvlCd)) {
                SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                shpgPlnTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                shpgPlnTMsg.shpgPlnNum.setValue(param.shpgPlnNum.getValue());
                shpgPlnTMsg = (SHPG_PLNTMsg) S21FastTBLAccessor.findByKey(shpgPlnTMsg);
                if (shpgPlnTMsg == null) {
                    addMsgId(NWZM0075E);
                    return false;
                }
                this.hldQty = shpgPlnTMsg.ordQty.getValueInt();
            }
        }

        // HLD
        HLDTMsg hldTMsg = new HLDTMsg();

        if (CPO_LEVEL.equals(hldLvlCd)) {
            hldTMsg.setSQLID("016");
            hldTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            hldTMsg.setConditionValue("hldRsnCd01", param.hldRsnCd.getValue());
            hldTMsg.setConditionValue("cpoOrdNum01", param.cpoOrdNum.getValue());
            hldTMsg.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);

        } else if (CPO_DETAIL_LEVEL.equals(hldLvlCd)) {
            hldTMsg.setSQLID("015");
            hldTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            hldTMsg.setConditionValue("hldRsnCd01", param.hldRsnCd.getValue());
            hldTMsg.setConditionValue("cpoOrdNum01", param.cpoOrdNum.getValue());
            hldTMsg.setConditionValue("cpoDtlLineNum01", param.cpoDtlLineNum.getValue());
            hldTMsg.setConditionValue("cpoDtlLineSubNum01", param.cpoDtlLineSubNum.getValue());
            hldTMsg.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);

        } else if (SHIPPING_PLAN_LEVEL.equals(hldLvlCd)) {
            hldTMsg.setSQLID("014");
            hldTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
            hldTMsg.setConditionValue("hldRsnCd01", param.hldRsnCd.getValue());
            hldTMsg.setConditionValue("cpoOrdNum01", param.cpoOrdNum.getValue());
            hldTMsg.setConditionValue("cpoDtlLineNum01", param.cpoDtlLineNum.getValue());
            hldTMsg.setConditionValue("cpoDtlLineSubNum01", param.cpoDtlLineSubNum.getValue());
            hldTMsg.setConditionValue("shpgPlnNum01", param.shpgPlnNum.getValue());
            hldTMsg.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);
        }

        HLDTMsgArray resHldTMsgArray = (HLDTMsgArray) S21ApiTBLAccessor.findByCondition(hldTMsg);

        // Registration cannot be completed because unreleased Hold information exists.
        if (resHldTMsgArray.getValidCount() > 0) {

            for (int i = 0; i < resHldTMsgArray.getValidCount(); i++) {
                HLDTMsg resHldTMsg = resHldTMsgArray.no(i);
                if (S21StringUtil.isEquals(param.trxSrcTpCd.getValue(), resHldTMsg.trxSrcTpCd.getValue())) {
                    addMsgId(NWZM0443E);
                    return false;
                }
            }
        }

        // Hold Apply Reason Code
        if (ZYPCommonFunc.hasValue(param.hldApplyRsnCd)) {
            HLD_APPLY_RSNTMsg hldApplyRsnTMsg = new HLD_APPLY_RSNTMsg();
            hldApplyRsnTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            hldApplyRsnTMsg.hldApplyRsnCd.setValue(param.hldApplyRsnCd.getValue());
            hldApplyRsnTMsg = (HLD_APPLY_RSNTMsg) S21CacheTBLAccessor.findByKey(hldApplyRsnTMsg);

            if (hldApplyRsnTMsg == null) {
                addMsgId(NWZM2206E);
                return false;
            }
        }

        return true;
    }

    private void printBizProcLog(NWZC044001PMsg param) {

        S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();

        // SubSysId
        bizLogMsg.setSubSysId("NWZ");
        // ProcId
        bizLogMsg.setProcId("OM");
        // EventId
        if (isManualHold(param)) {
            bizLogMsg.setEventId("Manual Hold");
            // CmntTxt_02
            bizLogMsg.setBizProcCmntTxt_02(getHldCmntTxt(param));
        } else {
            bizLogMsg.setEventId("Hold");
            // CmntTxt_02
            bizLogMsg.setBizProcCmntTxt_02(param.hldRsnCd.getValue());
        }
        // DocTpCd
        if (S21StringUtil.isEquals(param.trxSrcTpCd.getValue(), TRX_SRC_TP.WHOLE_SALES_RETURN)) {
            bizLogMsg.setDocTpCd("RT");
        } else {
            bizLogMsg.setDocTpCd("OM");
        }
        // DocId
        if (CPO_DETAIL_LEVEL.equals(this.hldLvlCd) || SHIPPING_PLAN_LEVEL.equals(this.hldLvlCd)) {
            String docId = concat(param.cpoDtlLineNum.getValue(), ".", param.cpoDtlLineSubNum.getValue());
            bizLogMsg.setDocId(docId);
        }
        // PrntDocId
        bizLogMsg.setPrntDocId(param.cpoOrdNum.getValue());

        S21BusinessProcessLog.print(bizLogMsg);
    }

    private boolean isManualHold(NWZC044001PMsg param) {
        HLD_RSNTMsg tMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, param.hldRsnCd);

        tMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return HLD_APPLY_TP.MANUAL.equals(tMsg.hldApplyTpCd.getValue());
    }

    private String getHldCmntTxt(NWZC044001PMsg param) {
        HLD_RSNTMsg tMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, param.hldRsnCd);

        tMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return S21StringUtil.concatStrings(tMsg.hldRsnCd.getValue(), ":", tMsg.hldRsnDescTxt.getValue());
    }

    private static String concat(String... strs) {

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    // 2019/12/03 QC#54861 Add Start
    private boolean checkNonInstaleBaseForBillingHold(NWZC044001PMsg pMsg) {
        String instalBaseFlg = (String) this.ssmBatchClient.queryObject("checkInstalBase", pMsg);
        if (ZYPConstant.FLG_OFF_N.equals(instalBaseFlg)) {
            if (HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION.equals(pMsg.hldRsnCd.getValue())) {
                return true;
            }
        }
        return false;
    }
    // 2019/12/03 QC#54861 Add End
}
