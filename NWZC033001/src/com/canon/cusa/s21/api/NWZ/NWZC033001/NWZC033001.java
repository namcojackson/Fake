/**<pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>*/
/**
 * <pre>
 * Hold of the object is updated in the state of release.
 * There is no Hold of the object or it has not already updated it when having released it.
 * 
 * If released Hold is "S/O Cancel Hold", or "P/O Cancel Hold", it processes it as follows.
 * 
 * - Shipping Status is updated to Hard Allocation for "S/O Cancel Hold", and it updates it to Validated for "P/O Cancel Hold".
 * - It merges it with the record if there is a record of the same status and the same shipment condition when "S/O Cancel Hold" is released.
 * - It merges it with the record if there is a record of the same status when "P/O Cancel Hold" is released.
 * - Similarly, it merges it if there is relation PRC_DTL in SHPG_PLN that becomes a merging origin.
 * - Shipping Plan Number of SOFT_ALLOC in the merging origin is updated with Shipping Plan Number merging ahead when there is data of SOFT_ALLOC.
 * - It updates it with Order Quantity after Hold Quantity is merged when there is Sales Hold that relates to SHPG_PLN merging ahead.
 * - Hold information on the merging origin logical is deleted.
 * 
 * After it and Hold are released, Validation Process Manager API of each order is started.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/21/2009   Fujitsu         S.Sugino        Create          N/A
 * 01/25/2010   Fujitsu         K.Kato          Update          Ship Complete Hold
 * 05/12/2010   Fujitsu         A.Suda          Update          5359
 * 05/21/2010   Fujitsu         A.Suda          Update          6664
 * 06/09/2010   Fujitsu         S.Takami        Update          6957
 * 10/14/2010   Fujitsu         K.Tajima        Update          Performance tuning
 * 12/30/2010   Fujitsu         K.Tajima        Update          1053 [Performance] NWAL0050 : Hold Release
 * 01/18/2011   Fujitsu         K.Tajima        Update          1123 [Performance] NWAL0050 : Hold Release (Submit)
 * 02/09/2011   Fujitsu         N.Aoyama        Update          1434 Performance Log Commentout
 * 04/18/2011   Fujitsu         S.Yamamoto      Update          1481(PROD)
 * 01/23/2013   Fujitsu         S.Iidaka        Update          WDS Defect#234
 * 07/12/2013   Fujitsu         D.Yanagisawa    Update          #R-OM028
 * 08/05/2013   Fujitsu         D.N.Nakazawa    Update          #R-OM050
 * 2015/09/11   Fujitsu         M.Yamada        Update          CSA
 * 02/26/2016   Fujitsu         T.Ishii         Update          S21_NA#3960
 * 2016/04/21   Fujitsu         M.Yamada        Update          S21_NA#7257
 * 2016/04/26   Fujitsu         M.Yamada        Update          S21_NA#7534
 * 2016/05/20   Fujitsu         S.Takami        Update          S21_NA#5335
 * 2016/09/20   Fujitsu         Y.Kanefusa      Update          S21_NA#11306
 * 2017/01/04   Fujitsu         T.Yoshida       Update          S21_NA#14512
 * 2017/02/13   Fujitsu         S.Iidaka        Update          S21_NA#QC#16184
 * 2017/04/06   Fujitsu         S.Iidaka        Update          S21_NA#QC#18229
 * 2018/08/27   Fujitsu         Y.Matsui        Update          S21_NA#QC#27692
 * </pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC033001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByConditionForUpdate;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.update;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.updateByPartialValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.updateSelectionField;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKeyForUpdateAPI;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.BIZ_PROC_LOGTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.HLDTMsg;
import business.db.HLD_RSNTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NAZC006001PMsg;
import business.parts.NMZC600001PMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC400001PMsg;

import com.canon.cusa.s21.api.NAZ.NAZC006001.NAZC006001;
import com.canon.cusa.s21.api.NAZ.NAZC006001.NAZC006001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC033001.cache.DataCacheForHoldRelease;
import com.canon.cusa.s21.api.NWZ.NWZC033001.cache.FindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC400001.NWZC400001;
import com.canon.cusa.s21.api.NWZ.NWZC400001.constant.NWZC400001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Hold Release API.
 */
public class NWZC033001 extends S21ApiCommonBase {

    /** Error Message */
    public static final String NWZM0001E = "NWZM0001E";

    /** Error Message */
    public static final String NWZM0002E = "NWZM0002E";

    /** Error Message */
    public static final String NWZM0005E = "NWZM0005E";

    /** Error Message */
    public static final String NWZM0445E = "NWZM0445E";

    /** Error Message */
    public static final String NWZM0721E = "NWZM0721E";

    /** Error Message */
    public static final String NWZM0148E = "NWZM0148E";

    /** Error Message */
    public static final String NWZM1757E = "NWZM1757E";

    /** Flag 'Y' */
    private static final String Y = ZYPConstant.FLG_ON_Y;

    /** Flag 'N' */
    private static final String N = ZYPConstant.FLG_OFF_N;

    /** SSM Client */
    private final S21SsmBatchClient ssmClient;

    /** SET Item Parent Number */
    private static final String SET_ITEM_PARENT_NUM = "000";

    /** Max Amount */
    private static final BigDecimal MAX_AMT = new BigDecimal("999999999999999.9999");

    /** Begin Index of CPO_DTL_LINE_NUM in DS_ORD_POSN_NUM */
    private static final int CPO_DTL_LINE_NUM_BEG_IDX = 0;

    /** End Index of CPO_DTL_LINE_NUM in DS_ORD_POSN_NUM */
    private static final int CPO_DTL_LINE_NUM_END_IDX = 3;

    /** Begin Point of CPO_DTL_LINE_SUB_NUM in DS_ORD_POSN_NUM */
    private static final int CPO_DTL_LINE_SUB_NUM_BEG_IDX = 3;

    /** End Index of CPO_DTL_LINE_SUB_NUM in DS_ORD_POSN_NUM */
    private static final int CPO_DTL_LINE_SUB_NUM_END_IDX = 6;

    /** NWZC004001 : Validation Process Manager API */
    private final NWZC004001 validProcMgrAPI = new NWZC004001();

    /** NAZC006001 : Retail Validation API */
    private final NAZC006001 rtlValidAPI = new NAZC006001();

    /** NMZC600001 : Credit Limit Validation API */
    private final NMZC600001 creditLimitAPI = new NMZC600001();

    /** NWZC400001 : Return Validation API */
    private final NWZC400001 rtrnValidationAPI = new NWZC400001(); // 2015/09/11 Add

    /**
     * Constractor
     */
    public NWZC033001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Hold Release.
     * @param param NWZC033001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC033001PMsg param, final ONBATCH_TYPE onBatchType) {

        final List<NWZC033001PMsg> params = new ArrayList<NWZC033001PMsg>();
        params.add(param);

        this.execute(params, onBatchType);
    }

    /**
     * Hold Release.
     * @param params List<NWZC033001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NWZC033001PMsg> params, final ONBATCH_TYPE onBatchType) {
        final String methodNm = "execute : params.size=[" + params.size() + "]";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            if (isEmpty(params)) {
                return;
            }

            // Map<CPO_ORD_NUM, NWZC033001PMsg> for "Recalculation" by [NWZC004001] : Validation Process Manager API.
            final Map<String, NWZC033001PMsg> recalcOrdMap = new HashMap<String, NWZC033001PMsg>();
            final Map<String, NWZC033001PMsg> recalcOrdRtrnMap = new HashMap<String, NWZC033001PMsg>();

            /**********************************************************************
             * 1. Hold Release Process
             **********************************************************************/
            writePerformanceProfilingLogStart(getClass(), "releaseHld");

            final List<HLDTMsg> relHldTMsgList = new ArrayList<HLDTMsg>();

            List<HLDTMsg> updHldTMsgList = new ArrayList<HLDTMsg>();

            for (NWZC033001PMsg param : params) {

                final S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

                if (!checkInput(msgMap)) {
                    msgMap.flush();
                    return;
                }

                writePerformanceProfilingLog(getClass(), " #cpoOrdNum=[" + param.cpoOrdNum.getValue() + "], cpoDtlLineNum=[" + param.cpoDtlLineNum.getValue() + "], cpoDtlLineSubNum=[" + param.cpoDtlLineSubNum.getValue() + "], shpgPlnNum=["
                        + param.shpgPlnNum.getValue() + "], hldPk=[" + param.hldPk.getValue() + "]");

                final String cpoOrdTpCd = getCpoOrdTpCd(param);

                // --------------------------------------------------
                // release [HLD]
                // --------------------------------------------------
                final HLDTMsg hldTMsg = releaseHld(msgMap, param, cpoOrdTpCd, onBatchType, updHldTMsgList);

                if (msgMap.getMsgMgr().isXxMsgId()) {
                    msgMap.flush();
                    return;
                }

                // QC#18229 2017/04/06 Add Start
                if(hldTMsg == null) {
                    continue;
                }
                // QC#18229 2017/04/06 Add End

                relHldTMsgList.add(hldTMsg);

                if (!isTrialLoanToSales(cpoOrdTpCd)) {
                    final String cpoOrdNum = param.cpoOrdNum.getValue();
                    if (ZYPCommonFunc.hasValue(hldTMsg.trxSrcTpCd) && TRX_SRC_TP.WHOLE_SALES_RETURN.equals(hldTMsg.trxSrcTpCd.getValue())) {
                        if (!recalcOrdRtrnMap.containsKey(cpoOrdNum)) {
                            recalcOrdRtrnMap.put(cpoOrdNum, param);
                        }
                    } else {
                        if (!recalcOrdMap.containsKey(cpoOrdNum)) {
                            recalcOrdMap.put(cpoOrdNum, param);
                        }
                    }
                }
            }
            writePerformanceProfilingLogEnd(getClass(), "releaseHld");

            if (!updHldTMsgList.isEmpty()) {
                S21FastTBLAccessor.update(updHldTMsgList.toArray(new HLDTMsg[0]));
            }

            /**********************************************************************
             * 2. write Biz Process Log
             **********************************************************************/
            writeBizProcLog(relHldTMsgList);
            relHldTMsgList.clear();

            /**********************************************************************
             * 3. Recalculation by [NWZC004001] : Validation Process Manager API
             **********************************************************************/

            // START 2018/08/27 [QC#27692, ADD]
            boolean isCreditHold = isCreditHold(updHldTMsgList);
            // END   2018/08/27 [QC#27692, ADD]

            for (Map.Entry<String, NWZC033001PMsg> recalcOrdEntry : recalcOrdMap.entrySet()) {

                final NWZC033001PMsg param = recalcOrdEntry.getValue();

                // START 2018/08/27 [QC#27692, MOD]
                if (!callValidationAPI(param, isCreditHold, onBatchType)) {
                    return;
                }
                // END   2018/08/27 [QC#27692, MOD]
            }

            for (Map.Entry<String, NWZC033001PMsg> recalcOrdEntry : recalcOrdRtrnMap.entrySet()) {

                final NWZC033001PMsg param = recalcOrdEntry.getValue();

                if (!callRtrnValidationAPI(param, onBatchType)) {
                    return;
                }
            }

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private boolean callValidationAPI(NWZC033001PMsg param, boolean isCreditHold, final ONBATCH_TYPE onBatchType) {

        // boolean isAllLineCancelled = NWXC150001DsCheck.checkAllLineCancelled(param.glblCmpyCd.getValue(), param.cpoOrdNum.getValue());
        // boolean isAllRmaLineCancelled = NWXC150001DsCheck.checkAllRmaLineCancelled(param.glblCmpyCd.getValue(), param.cpoOrdNum.getValue());
        boolean isAllLineCancelled = DataCacheForHoldRelease.getInstance().checkAllLineCancelled(param);
        boolean isAllRmaLineCancelled = DataCacheForHoldRelease.getInstance().checkRmaAllLineCancelled(param);

        if (isAllLineCancelled) {
            if (isAllRmaLineCancelled) { // means no detail data, no need validation.
                return true;
            } else {
                if (callRtrnValidationAPI(param, onBatchType)) {
                    return false;
                }
                return true;
            }
        }

        final String cpoOrdTpCd = param.cpoOrdTpCd.getValue();

        // Call Retail Validation API
        if (CPO_ORD_TP.PURCHASE.equals(cpoOrdTpCd) || CPO_ORD_TP.RENTAL.equals(cpoOrdTpCd) || CPO_ORD_TP.LEASE.equals(cpoOrdTpCd) || CPO_ORD_TP.SERVICE.equals(cpoOrdTpCd) || CPO_ORD_TP.RETAIL_TRIAL.equals(cpoOrdTpCd)
                || CPO_ORD_TP.RETAIL_ORDER.equals(cpoOrdTpCd)) {

            NAZC006001PMsg rtlValidPMsg = new NAZC006001PMsg();
            setValue(rtlValidPMsg.xxProcMd, NAZC006001Constant.PROC_MODE_RECALC);
            setValue(rtlValidPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(rtlValidPMsg.slsDt, param.slsDt);
            setValue(rtlValidPMsg.cpoOrdNum, param.cpoOrdNum);

            // call [NAZC006001] : Retail Validation API
            rtlValidAPI.execute(rtlValidPMsg, onBatchType);

            final int errMsgIdCnt = rtlValidPMsg.xxMsgIdList.getValidCount();

            // has API errors.
            if (errMsgIdCnt > 0) {
                for (int i = 0; i < errMsgIdCnt; i++) {
                    final String xxMsgId = rtlValidPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    param.xxMsgIdList.no(i).xxMsgId.setValue(xxMsgId);
                }
                param.xxMsgIdList.setValidCount(errMsgIdCnt);
                return false;
            }

            // Call Validation Process Manager API
        } else {

            if (isNoShpgPlnOrd(param)) {
                return true;
            }

            NWZC004001PMsg validMgrPMsg = new NWZC004001PMsg();
            setValue(validMgrPMsg.xxRqstTpCd, NWZC004001.VAL_TP_RC);
            setValue(validMgrPMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(validMgrPMsg.slsDt, param.slsDt);
            setValue(validMgrPMsg.cpoOrdNum_I, param.cpoOrdNum);

            // START 2018/08/27 [QC#27692, ADD]
            if (isCreditHold) {
                setValue(validMgrPMsg.xxPgFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2018/08/27 [QC#27692, ADD]

            // call [NWZC004001] : Validation Process Manager API
            validProcMgrAPI.execute(validMgrPMsg, onBatchType);

            final int errMsgIdCnt = validMgrPMsg.xxMsgIdList.getValidCount();

            // has API errors.
            if (errMsgIdCnt > 0) {
                for (int i = 0; i < errMsgIdCnt; i++) {
                    final String xxMsgId = validMgrPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    param.xxMsgIdList.no(i).xxMsgId.setValue(xxMsgId);
                }
                param.xxMsgIdList.setValidCount(errMsgIdCnt);
                return false;
            }
        }

        return true;
    }

    /**
     * 20150911 add call return validation api.
     * @param param NWZC033001PMsg
     * @param onBatchType
     * @return result of return validation. if error then return true.
     */
    private boolean callRtrnValidationAPI(NWZC033001PMsg param, ONBATCH_TYPE onBatchType) {
        NWZC400001PMsg pMsg = new NWZC400001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, param.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NWZC400001Constant.REQ_TYPE_ORD_VLD);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_I, param.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoRtrnLineNum_I, param.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoRtrnLineSubNum_I, param.cpoDtlLineSubNum);

        rtrnValidationAPI.execute(pMsg, onBatchType);

        if (!S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        List<String> msgList = S21ApiUtil.getXxMsgIdList(pMsg);
        for (String msgId : msgList) {
            int ix = param.xxMsgIdList.getValidCount();
            param.xxMsgIdList.no(ix++).xxMsgId.setValue(msgId);
            param.xxMsgIdList.setValidCount(ix);
        }
        return false;
    }

    private HLDTMsg releaseHld(S21ApiMessageMap msgMap, NWZC033001PMsg param, String cpoOrdTpCd, ONBATCH_TYPE onBatchType, List<HLDTMsg> updHldTMsgList) {

        /**************************************************
         * release [HLD]
         **************************************************/
        // lock
        HLDTMsg hldTMsg = new HLDTMsg();
        setValue(hldTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(hldTMsg.hldPk, param.hldPk);

        hldTMsg = (HLDTMsg) findByKeyForUpdateAPI(hldTMsg);

        if (hldTMsg == null) {
            msgMap.addXxMsgId(NWZM0721E);
            return null;
        }

        // already released.
        if (Y.equals(hldTMsg.relFlg.getValue())) {
            return null;
        }

        // ***** updateSelectionField
        setValue(hldTMsg.relFlg, Y);
        setValue(hldTMsg.relDt, param.slsDt);
        setValue(hldTMsg.relPsnCd, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        setValue(hldTMsg.relMemoTxt, param.relMemoTxt);
        setValue(hldTMsg.hldRelRsnCd, param.hldRelRsnCd);

        updHldTMsgList.add(hldTMsg);

        if (isTrialLoanToSales(cpoOrdTpCd)) {
            if (isUpdateTrialLoan(param)) {
                updateCpo(param);
                updateInProcAmt(msgMap, param, onBatchType);
            }
            return hldTMsg;
        }

        final String hldRsnCd = hldTMsg.hldRsnCd.getValue();
        final String hldLvlCd = getHldLvlCd(param, hldRsnCd);

        if (!HLD_LVL.CPO_LEVEL.equals(hldLvlCd)) {

            if (HLD_RSN.SHIP_COMPLETE_HOLD.equals(hldRsnCd)) {

                // If BOM Header or Base Component of BOM then SHIP_CPLT_CD is not cleared.
                if (!isBomHdrOrBaseCmpt(param)) {
                    updateCpoDtlShipCpltCd(msgMap, param);
                    updateShpgPlnShipCpltCd(msgMap, param);
                }

            } else if (HLD_LVL.SHIPPING_PLAN_LEVEL.equals(hldLvlCd)) {

                // S/O Cancel, Purchase Order Cancel
                if (asList(HLD_RSN.S_OR_O_CANCEL, HLD_RSN.PURCHASE_ORDER_CANCELL).contains(hldRsnCd)) {

                    // [SHPG_PLN]
                    SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
                    setValue(shpgPlnTMsg.glblCmpyCd, param.glblCmpyCd);
                    setValue(shpgPlnTMsg.shpgPlnNum, param.shpgPlnNum);

                    shpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(shpgPlnTMsg);

                    if (shpgPlnTMsg == null) {
                        msgMap.addXxMsgId(NWZM0721E);
                        return null;
                    }

                    updateShpgStsCd(msgMap, shpgPlnTMsg);
                    updateSetShpgStsCd(msgMap, shpgPlnTMsg);
                }
            }
        }

        return hldTMsg;
    }

    /**
     * Check Sales BOM Header or Base Component of Sales BOM.
     * @param param NWZC033001PMsg
     * @return isBomHdrOrBaseCmpt boolean
     */
    private boolean isBomHdrOrBaseCmpt(NWZC033001PMsg param) {
        final String methodNm = "isBomHdrOrBaseCmpt";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {
            Map<String, String> ssmParamOfChild = new HashMap<String, String>();
            ssmParamOfChild.put("glblCmpyCd", param.glblCmpyCd.getValue());
            ssmParamOfChild.put("cpoOrdNum", param.cpoOrdNum.getValue());
            ssmParamOfChild.put("cpoDtlLineNum", param.cpoDtlLineNum.getValue());
            ssmParamOfChild.put("cpoDtlLineSubNum", param.cpoDtlLineSubNum.getValue());

            // getting child of position(=self) Info
            Map<String, String> rsltObjOfChild = getBomInfo(ssmParamOfChild);
            if (rsltObjOfChild == null) {
                // In case of invalid data. This case will not be happened usually.
                return false;
            }

            String childDsOrdPosNum = rsltObjOfChild.get("DS_ORD_POSN_NUM");
            String childMdseCd = rsltObjOfChild.get("MDSE_CD");

            // if true, then child of position
            if (ZYPCommonFunc.hasValue(childDsOrdPosNum)) {

                // DS_ORD_POSN_NUM is (Position's) cpoDtlLineNum + cpoDtlLineSubNum
                // split DS_ORD_POSN_NUM into cpoDtlLineNum and cpoDtlLineSubNum
                String posiCpoDtlLineNum = childDsOrdPosNum.substring(CPO_DTL_LINE_NUM_BEG_IDX, CPO_DTL_LINE_NUM_END_IDX);
                String posiCpoDtlLineSubNum = childDsOrdPosNum.substring(CPO_DTL_LINE_SUB_NUM_BEG_IDX, CPO_DTL_LINE_SUB_NUM_END_IDX);

                // if true, Position's Header
                if (posiCpoDtlLineNum.equals(param.cpoDtlLineNum.getValue()) && posiCpoDtlLineSubNum.equals(param.cpoDtlLineSubNum.getValue())) {

                    String childMdseTpCd = rsltObjOfChild.get("MDSE_TP_CD");

                    // if MDSE.MDSE_TP_CD = '5' then this record is BOM Header.
                    // MDSE.MDSE_TP_CD != '5' then this record is not BOM(Header, Details).
                    if (MDSE_TP.SALES_BOM.equals(childMdseTpCd)) {
                        // if true, Sales BOM Header
                        return true;
                    }
                // else, Position's Detail
                } else {
                    Map<String, String> ssmParamOfPrnt = new HashMap<String, String>();
                    ssmParamOfPrnt.put("glblCmpyCd", param.glblCmpyCd.getValue());
                    ssmParamOfPrnt.put("cpoOrdNum", param.cpoOrdNum.getValue());
                    ssmParamOfPrnt.put("cpoDtlLineNum", posiCpoDtlLineNum);
                    ssmParamOfPrnt.put("cpoDtlLineSubNum", posiCpoDtlLineSubNum);

                    // getting parent of position info
                    Map<String, String> rsltObjOfPrnt = getBomInfo(ssmParamOfPrnt);
                    if (rsltObjOfPrnt == null) {
                        // In case of invalid data. This case will not
                        // be happened usually.
                        return false;
                    }

                    String prntMdseCd = rsltObjOfPrnt.get("MDSE_CD");
                    String prntMdseTpCd = rsltObjOfPrnt.get("MDSE_TP_CD");

                    // if true, child of Sales BOM
                    if (MDSE_TP.SALES_BOM.equals(prntMdseTpCd)) {

                        // if count larger than 0 then this record is Base
                        // Component of Sales BOM.
                        if (isBaseCmp(param.glblCmpyCd.getValue(), prntMdseCd, childMdseCd) > 0) {

                            // Base Component of Sales BOM
                            return true;
                        }
                    }
                }
            }

            return false;

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private Map<String, String> getBomInfo(Map<String, String> ssmParam) {
        final String methodNm = "getBomInfo";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {
            return (Map<String, String>) this.ssmClient.queryObject("getBomInfo", ssmParam);

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private Integer isBaseCmp(String glblCmpyCd, String prntMdseCd, String childMdseCd) {
        final String methodNm = "isBaseCmp";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("prntMdseCd", prntMdseCd);
            ssmParam.put("childMdseCd", childMdseCd);
            ssmParam.put("baseCmpFlg", ZYPConstant.FLG_ON_Y);

            return (Integer) this.ssmClient.queryObject("isBaseCmp", ssmParam);

        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private void updateSetShpgStsCd(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnMsg) {

        if (!hasValue(shpgPlnMsg.setMdseCd) && !hasValue(shpgPlnMsg.setShpgPlnNum)) {
            return;
        }

        SHPG_PLNTMsg setShpgPlnTMsg = new SHPG_PLNTMsg();
        setValue(setShpgPlnTMsg.glblCmpyCd, shpgPlnMsg.glblCmpyCd.getValue());
        setValue(setShpgPlnTMsg.shpgPlnNum, shpgPlnMsg.setShpgPlnNum.getValue());

        setShpgPlnTMsg = (SHPG_PLNTMsg) findByKeyForUpdateAPI(setShpgPlnTMsg);

        if (setShpgPlnTMsg == null) {
            msgMap.addXxMsgId(NWZM0721E);
            return;
        }

        if (SHPG_STS.S_OR_O_CANCELLED.equals(setShpgPlnTMsg.shpgStsCd.getValue())) {

            final Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", setShpgPlnTMsg.glblCmpyCd.getValue());
            ssmParam.put("trxSrcTpCd", setShpgPlnTMsg.trxSrcTpCd.getValue());
            ssmParam.put("trxHdrNum", setShpgPlnTMsg.trxHdrNum.getValue());
            ssmParam.put("trxLineNum", setShpgPlnTMsg.trxLineNum.getValue());
            ssmParam.put("trxLineNumSet", "000");

            final int compCnt = (Integer) this.ssmClient.queryObject("queryEachCompChk", ssmParam);

            if (compCnt == 0) {
                // It is composed only of VendorDrop. or It is composed only of Intangible.
                setValue(setShpgPlnTMsg.shpgStsCd, SHPG_STS.VALIDATED);
            } else {
                setValue(setShpgPlnTMsg.shpgStsCd, SHPG_STS.HARD_ALLOCATED);
            }

            // ***** update
            update(setShpgPlnTMsg);
        }
    }

    private void updateShpgStsCd(S21ApiMessageMap msgMap, SHPG_PLNTMsg shpgPlnTMsg) {

        final String shpgStsCd = shpgPlnTMsg.shpgStsCd.getValue();

        if (SHPG_STS.S_OR_O_CANCELLED.equals(shpgStsCd)) {

            setValue(shpgPlnTMsg.shpgStsCd, SHPG_STS.HARD_ALLOCATED);
            setValue(shpgPlnTMsg.rteStsCd, RTE_STS.UN_ROUTED);

        } else if (SHPG_STS.P_OR_O_CANCELLED.equals(shpgStsCd)) {

            setValue(shpgPlnTMsg.shpgStsCd, SHPG_STS.VALIDATED);

        } else {
            return;
        }

        // ***** update
        update(shpgPlnTMsg);
    }

    private void writeBizProcLog(List<HLDTMsg> relHldTMsgList) {
        final String methodNm = "writeBizProcLog";
        writePerformanceProfilingLogStart(getClass(), methodNm);

        try {
            List<BIZ_PROC_LOGTMsg> insBizProcLogTMsgList = new ArrayList<BIZ_PROC_LOGTMsg>();

            for (HLDTMsg hldTMsg : relHldTMsgList) {

                if (hldTMsg == null) {
                    continue;
                }

                final S21BusinessProcessLogMsg bizLogMsg = new S21BusinessProcessLogMsg();
                // SUB_SYS_ID
                bizLogMsg.setSubSysId("NWZ");
                // PROC_ID
                bizLogMsg.setProcId("OM");
                // EVENT_ID
                final String bizProcCmntTxt02 = hldTMsg.hldRsnCd.getValue();
                if (isManualHold(hldTMsg)) {
                    bizLogMsg.setEventId("Manual Hold Release");
                    // BIZ_PROC_CMNT_TXT_02
                    bizLogMsg.setBizProcCmntTxt_02(getHldCmntTxt(hldTMsg));
                } else {
                    bizLogMsg.setEventId("Hold Release");
                    // BIZ_PROC_CMNT_TXT_02
                    bizLogMsg.setBizProcCmntTxt_02(bizProcCmntTxt02);
                }
                // DOC_TP_CD
                if (S21StringUtil.isEquals(hldTMsg.trxSrcTpCd.getValue(), TRX_SRC_TP.WHOLE_SALES_RETURN)) { // S21_NA#3960
                    // for RMA
                    bizLogMsg.setDocTpCd("RT");
                } else {
                    bizLogMsg.setDocTpCd("OM");
                }

                // PRNT_DOC_ID
                final String prntDocId = hldTMsg.cpoOrdNum.getValue();
                bizLogMsg.setPrntDocId(prntDocId);

                // DOC_ID
                final String docId;
                if (hasValue(hldTMsg.cpoDtlLineNum)) {
                    docId = hldTMsg.cpoDtlLineNum.getValue() + "." + hldTMsg.cpoDtlLineSubNum.getValue();
                } else {
                    docId = "";
                }
                bizLogMsg.setDocId(docId);
                writePerformanceProfilingLog(getClass(), " #prntDocId=[" + prntDocId + "], docId=[" + docId + "], bizProcCmntTxt_02=[" + bizProcCmntTxt02 + "]");

                // ***** print
                insBizProcLogTMsgList.add(S21BusinessProcessLog.getBizProcLog(bizLogMsg));
            }

            if (!insBizProcLogTMsgList.isEmpty()) {
                S21FastTBLAccessor.insert(insBizProcLogTMsgList.toArray(new BIZ_PROC_LOGTMsg[0]));
            }
        } finally {
            writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private boolean isManualHold(HLDTMsg hldTMsg) {
        HLD_RSNTMsg tMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hldTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, hldTMsg.hldRsnCd);

        tMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return HLD_APPLY_TP.MANUAL.equals(tMsg.hldApplyTpCd.getValue());
    }

    private String getHldCmntTxt(HLDTMsg hldTMsg) {
        HLD_RSNTMsg tMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, hldTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, hldTMsg.hldRsnCd);

        tMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return S21StringUtil.concatStrings(tMsg.hldRsnCd.getValue(), ":", tMsg.hldRsnDescTxt.getValue());
    }

    private static boolean checkInput(S21ApiMessageMap msgMap) {

        NWZC033001PMsg inMsg = (NWZC033001PMsg) msgMap.getPmsg();

        if (!hasValue(inMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0001E);
            return false;
        }

        if (!hasValue(inMsg.hldPk)) {
            msgMap.addXxMsgId(NWZM0005E);
            return false;
        }

        if (!hasValue(inMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM0002E);
            return false;
        }

        if (!hasValue(inMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM0445E);
            return false;
        }

        if (!isValidHldRelRsnCd(inMsg)) {
            msgMap.addXxMsgId(NWZM1757E);
            return false;
        }
        return true;
    }

    /**
     * 20150911 add check the hold release reason code of pMsg.
     * @param inMsg
     * @return if valid code then return true, else return false.
     */
    private static boolean isValidHldRelRsnCd(NWZC033001PMsg inMsg) {
        HLD_RSNTMsg tMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, inMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, inMsg.hldRelRsnCd);
        tMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        if (ZYPDateUtil.compare(tMsg.hldEffFromDt.getValue(), inMsg.slsDt.getValue()) > 0) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(tMsg.hldEffToDt) && ZYPDateUtil.compare(tMsg.hldEffToDt.getValue(), inMsg.slsDt.getValue()) < 0) {
            return false;
        }
        return true;
    }

    private static String getCpoOrdTpCd(NWZC033001PMsg param) {

        final String cpoOrdTpCd;

        if (hasValue(param.cpoOrdTpCd)) {
            cpoOrdTpCd = param.cpoOrdTpCd.getValue();

        } else {

            CPOTMsg cpoTMsg = new CPOTMsg();
            setValue(cpoTMsg.glblCmpyCd, param.glblCmpyCd);
            setValue(cpoTMsg.cpoOrdNum, param.cpoOrdNum);

            cpoTMsg = (CPOTMsg) S21CacheTBLAccessor.findByKey(cpoTMsg); // S21_NA#14512 Mod (Use Cache)

            if (cpoTMsg != null) {
                cpoOrdTpCd = cpoTMsg.cpoOrdTpCd.getValue();
                param.cpoOrdTpCd.setValue(cpoOrdTpCd);
            } else {
                cpoOrdTpCd = "";
            }
        }

        return cpoOrdTpCd;
    }

    private static String getHldLvlCd(NWZC033001PMsg param, String hldRsnCd) {

        HLD_RSNTMsg hldRsnTMsg = new HLD_RSNTMsg();
        setValue(hldRsnTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(hldRsnTMsg.hldRsnCd, hldRsnCd);

        hldRsnTMsg = (HLD_RSNTMsg) S21CacheTBLAccessor.findByKey(hldRsnTMsg); // S21_NA#14512 Mod (Use Cache)

        if (hldRsnTMsg != null) {
            return hldRsnTMsg.hldLvlCd.getValue();
        } else {
            return "";
        }
    }

    private static boolean isEmpty(Collection col) {

        return col == null || col.isEmpty();
    }

    private static boolean isTrialLoanToSales(String cpoOrdTpCd) {

        //return CPO_ORD_TP.LOAN_TO_SALES.equals(cpoOrdTpCd) || CPO_ORD_TP.TRIAL_TO_SALES.equals(cpoOrdTpCd);
        return CPO_ORD_TP.LOAN_TO_SALES.equals(cpoOrdTpCd) || CPO_ORD_TP.TRIAL_TO_SALES.equals(cpoOrdTpCd) || CPO_ORD_TP.RENTAL_TO_SALES.equals(cpoOrdTpCd);
    }

    private static void updateCpo(NWZC033001PMsg param) {

        CPOTMsg cpoTMsg = new CPOTMsg();
        setValue(cpoTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(cpoTMsg.cpoOrdNum, param.cpoOrdNum);

        cpoTMsg = (CPOTMsg) findByKeyForUpdateAPI(cpoTMsg);

        setValue(cpoTMsg.cpoHldFlg, N);
        setValue(cpoTMsg.openFlg, Y);

        // ***** updateSelectionField
        updateSelectionField(cpoTMsg, new String[] {"cpoHldFlg", "openFlg" });
    }

    private static void updateCpoDtlShipCpltCd(S21ApiMessageMap msgMap, NWZC033001PMsg param) {

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        setValue(cpoDtlTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(cpoDtlTMsg.cpoOrdNum, param.cpoOrdNum);
        setValue(cpoDtlTMsg.cpoDtlLineNum, param.cpoDtlLineNum);
        setValue(cpoDtlTMsg.cpoDtlLineSubNum, param.cpoDtlLineSubNum);

        cpoDtlTMsg = (CPO_DTLTMsg) findByKeyForUpdateAPI(cpoDtlTMsg);

        if (cpoDtlTMsg == null) {
            msgMap.addXxMsgId(NWZM0721E);
            return;
        }

        // SHIP_CPLT_CD
        setValue(cpoDtlTMsg.shipCpltCd, "");

        // ***** update
        update(cpoDtlTMsg);
    }

    private static void updateShpgPlnShipCpltCd(S21ApiMessageMap msgMap, NWZC033001PMsg param) {

        final SHPG_PLNTMsg condMsg = new SHPG_PLNTMsg();
        condMsg.setSQLID("001");
        condMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        condMsg.setConditionValue("trxHdrNum01", param.cpoOrdNum.getValue());
        condMsg.setConditionValue("trxLineNum01", param.cpoDtlLineNum.getValue());
        condMsg.setConditionValue("trxLineSubNum01", param.cpoDtlLineSubNum.getValue());

        final SHPG_PLNTMsgArray shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByConditionForUpdate(condMsg);

        if (shpgPlnTMsgArray.getValidCount() == 0) {
            msgMap.addXxMsgId(NWZM0721E);
            return;
        }

        setValue(condMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(condMsg.trxHdrNum, param.cpoOrdNum);
        setValue(condMsg.trxLineNum, param.cpoDtlLineNum);
        setValue(condMsg.trxLineSubNum, param.cpoDtlLineSubNum);

        // SHIP_CPLT_CD
        final SHPG_PLNTMsg updTMsg = new SHPG_PLNTMsg();
        setValue(updTMsg.shipCpltCd, "");

        // ***** updateByPartialValue
        updateByPartialValue(condMsg, new String[] {"glblCmpyCd", "trxHdrNum", "trxLineNum", "trxLineSubNum" }, updTMsg, new String[] {"shipCpltCd" });
    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLog(Class clazz, String str) {

    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogEnd(Class clazz, String methodNm) {

    }

    @SuppressWarnings("unchecked")
    private static void writePerformanceProfilingLogStart(Class clazz, String methodNm) {

    }

    @SuppressWarnings("unchecked")
    private void updateInProcAmt(S21ApiMessageMap msgMap, NWZC033001PMsg param, ONBATCH_TYPE onBatchType) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum.getValue());
        ssmParam.put("cpoDtlLineSubNum", SET_ITEM_PARENT_NUM);

        final List<Map<String, Object>> resMap = ssmClient.queryObjectList("getInProcAmt", ssmParam);

        if (resMap.isEmpty()) {
            return;
        }

        BigDecimal inProcAmt = (BigDecimal) resMap.get(0).get("TOT_AMT");
        String payerCustCd = (String) resMap.get(0).get("PAYER_CUST_CD");

        if (MAX_AMT.compareTo(inProcAmt) < 0) {
            msgMap.addXxMsgId(NWZM0148E);
            return;
        } else if (ZERO.compareTo(inProcAmt) >= 0) {
            return;
        }

        NMZC600001PMsg crBalPMsg = new NMZC600001PMsg();
        setValue(crBalPMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(crBalPMsg.billToCustCd, payerCustCd);
        setValue(crBalPMsg.updKeyNum, param.cpoOrdNum);
        setValue(crBalPMsg.slsDt, param.slsDt);

        creditLimitAPI.execute(crBalPMsg, onBatchType);
        writePerformanceProfilingLog(getClass() //
                , "    #call NMZC600001 : Credit Limit Validation API. Account Number=[" //
                        + crBalPMsg.dsAcctNum.getValue() //
                        + "], Bill To Cust Code=[" //
                        + crBalPMsg.billToCustCd.getValue() + "]");

        // has API errors?
        final int apiErrMsgIdList = crBalPMsg.xxMsgIdList.getValidCount();
        if (apiErrMsgIdList > 0) {
            for (int i = 0; i < apiErrMsgIdList; i++) {
                msgMap.addXxMsgId(crBalPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return;
        }
    }

    private boolean isUpdateTrialLoan(NWZC033001PMsg param) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("cpoOrdNum", param.cpoOrdNum.getValue());

        final int cntHld = (Integer) ssmClient.queryObject("cntTrialLoanHold", ssmParam);

        if (cntHld > 0) {
            return false;
        }

        return true;
    }

    private boolean isNoShpgPlnOrd(NWZC033001PMsg param) {

        boolean rslt = false;

        final FindCondition condition = new FindCondition("023");
        condition.addCondition("glblCmpyCd01", param.glblCmpyCd.getValue());
        condition.addCondition("trxHdrNum01", param.cpoOrdNum.getValue());
        condition.addCondition("shpgStsCd01", SHPG_STS.CANCELLED);
        SHPG_PLNTMsgArray shpgPlnTMsgArray = DataCacheForHoldRelease.getInstance().getShpgPlnTMsgArray(condition);

        if (null == shpgPlnTMsgArray || shpgPlnTMsgArray.getValidCount() == 0) {
            rslt = true;
        }

        return rslt;
    }

    // START 2018/08/27 [QC#27692, ADD]
    private boolean isCreditHold(List<HLDTMsg> updHldTMsgList) {
        for (HLDTMsg hldTMsg : updHldTMsgList) {
            HLD_RSNTMsg inMsg = new HLD_RSNTMsg();
            setValue(inMsg.glblCmpyCd, hldTMsg.glblCmpyCd);
            setValue(inMsg.hldRsnCd, hldTMsg.hldRsnCd);
            HLD_RSNTMsg hldRsnTMsg = (HLD_RSNTMsg) S21CodeTableAccessor.findByKey(inMsg);
            if (HLD_TP.CREDIT_HOLD.equals(hldRsnTMsg.hldTpCd.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END   2018/08/27 [QC#27692, ADD]
}
