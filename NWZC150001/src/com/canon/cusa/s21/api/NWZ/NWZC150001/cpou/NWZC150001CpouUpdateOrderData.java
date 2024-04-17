/**
 * <pre>
 * Update / Inser Order Data
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/10   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/05/22   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2 (Status for display)
 * 2017/06/13   Fujitsu         S.Takami        Update          S21_NA#18869-2
 * 2017/06/14   Fujitsu         Y.Kanefusa      Update          S21_NA#19040
 * 2017/06/16   Fujitsu         S.Takami        Update          S21_NA#19288
 * 2017/06/20   Fujitsu         S.Takami        Update          S21_NA#19288-2
 * 2018/06/20   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2019/07/31   Fujitsu         R.Nakamura      Update          S21_NA#52267
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_MOD;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RQST_DTL_NEW;
import static com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant.RTRN_VALID_MODE_ORD_VALID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_VTMsg;
import business.db.CPO_VTMsgArray;
import business.db.HLDTMsg;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NWZC004001PMsg;
import business.parts.NWZC102001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153002PMsg;
import business.parts.NWZC188001PMsg;
import business.parts.NWZC400001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NWZ.NWZC004001.NWZC004001;
import com.canon.cusa.s21.api.NWZ.NWZC102001.NWZC102001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Query;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouInsBizProcLog;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouInsRecRecord;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouUpdCpo;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouUpdCpoDtl;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouUpdHld;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouUpdReqMsgData;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.dbAccs.NWZC150001CpouUpdShpgPln;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouEditAmount;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouValidCheck;
import com.canon.cusa.s21.api.NWZ.NWZC153001.NWZC153001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.api.NWZ.NWZC400001.NWZC400001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

public class NWZC150001CpouUpdateOrderData {

    // 2017/06/13 S21_NA#18869-2 Del Start
//    private static final NWZC150001CpouUpdateOrderData MY_INSTANCE = new NWZC150001CpouUpdateOrderData();
    // 2017/06/13 S21_NA#18869-2 Del End

    private S21SsmBatchClient ssmClient = null;

    public NWZC150001CpouUpdateOrderData() {
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    private ONBATCH_TYPE onBatchType = ONBATCH_TYPE.BATCH;

    private S21ApiMessageIdMgr msgIdMgr = null;

    /** API cache. */
    private final Map<Class<? extends S21ApiCommonBase>, S21ApiCommonBase> apiCache = new HashMap<Class<? extends S21ApiCommonBase>, S21ApiCommonBase>();

    /** Return Update API Parameter */
    private NWZC153001PMsg ruPMsg = null;

    private String rtrnMode = "";

    // 2017/06/13 S21_NA#18869-2 Add Start
    /** Instance of NWZC150001CpouUpdCpoDtl */
    private NWZC150001CpouUpdCpoDtl updCpoDtlInstance = null;
    // 2017/06/13 S21_NA#18869-2 Add End

    // 2017/06/13 S21_NA#18869-2 Del Start
//    public static NWZC150001CpouUpdateOrderData getInstance() {
//
//        return MY_INSTANCE;
//    }
    // 2017/06/13 S21_NA#18869-2 Del End

    public void updateOrderData(NWZC150001PMsg pMsg, //
            NWZC150001CpouBean cpouBean, //
            List<NWZC150002PMsg> resPMsg2List, //
            List<NWZC150003PMsg> resPMsg3List, //
            S21ApiMessageIdMgr msgIdMgr, //
            ONBATCH_TYPE onBatchType) {

        this.onBatchType = onBatchType;
        this.msgIdMgr = msgIdMgr;

        String methodNm = "updateOrderData";
        try {

            final String xxRqstTpCd = cpouBean.getRqstTpCd();
            final boolean isSubmitRqst = NWZC150001CpouConstant.CPO_SUBMIT.equals(xxRqstTpCd);
            final boolean isSaveRqst = NWZC150001CpouConstant.CPO_SAVE.equals(xxRqstTpCd);
            final boolean isCancelRqst = NWZC150001CpouConstant.CPO_CANCEL.equals(xxRqstTpCd);
            final boolean isModifyRqst = NWZC150001CpouConstant.CPO_MODIFY.equals(xxRqstTpCd);

            // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor
            // Plan Order] - START

            // [CPO]
            CPOTMsg dbCpoTMsg = new CPOTMsg();

            setValue(dbCpoTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
            setValue(dbCpoTMsg.cpoOrdNum, cpouBean.getCpoOrdNum());
            dbCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(dbCpoTMsg);

            // get original PAYER_CUST_CD.
            String origPayerCustCd = null;
            if (dbCpoTMsg != null) {
                origPayerCustCd = dbCpoTMsg.payerCustCd.getValue();
            } else {
                origPayerCustCd = "";
            }

            boolean isPayerChanged = false;
            if (isModifyRqst) {
                isPayerChanged = !origPayerCustCd.equals(cpouBean.getPayerCustCd());
                NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), " #isPayerChanged=[" + isPayerChanged + "] : payerCustCd=[" + origPayerCustCd + "] -> [" + cpouBean.getPayerCustCd() + "]");
            }

            // add start 2023/04/25 QC#61337
            CPO_VTMsg befCpoVTMsg = getCpoV(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
            // add end 2023/04/25 QC#61337

            // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor

            // 1.2WDS modify start -->
            // Original CPO Update API first save CPO, however CSA mode CPO will be updated
            // after CPO_DTL and DS_CPO_RTRN_DTL
            // ===================================================================
            // update [CPO].
            // ===================================================================
//            CPOTMsg reqCpoTMsg = new CPOTMsg();
//            NWZC150001CpouUpdCpo.getInstance().updateCpo(cpouBean, dbCpoTMsg, reqCpoTMsg);

            // ===================================================================
            // update [CPO_DTL].
            // ===================================================================
            List<CPO_DTLTMsg> insCpoDtlTMsgAry = new ArrayList<CPO_DTLTMsg>();
            List<CPO_DTLTMsg> updCpoDtlTMsgAry = new ArrayList<CPO_DTLTMsg>();

            // 2017/06/13 S21_NA#18869-2 Mod Start
//            NWZC150001CpouUpdCpoDtl.getInstance().updateCpoDtl(cpouBean, insCpoDtlTMsgAry, updCpoDtlTMsgAry);
            getCpouUpdCpoDtlInstance().updateCpoDtl(cpouBean, insCpoDtlTMsgAry, updCpoDtlTMsgAry);
            // 2017/06/13 S21_NA#18869-2 Mod end
            // 1.2WDS modify end <--

            // 2017/04/11 S21_NA#Review structure Lv.2 Add Start
            // ===================================================================
            // Return Update API [DS_CPO_RTRN_TL].
            // ===================================================================
            boolean isPureReturn = isTargetOfOrdValidation(cpouBean);

            if (pMsg.rtnDtl.getValidCount() > 0) {
                char callSeq = 0;
                String rqstTpCd = cpouBean.getRqstTpCd();
                if (S21StringUtil.isEquals(NWZC150001CpouConstant.CPO_SAVE, rqstTpCd)) {
                    callSeq = NWZC150001Constant.CALL_SEQ_SAVE;
                } else if (S21StringUtil.isEquals(NWZC150001CpouConstant.CPO_SUBMIT, cpouBean.getRqstTpCd())) {
                    callSeq = NWZC150001Constant.CALL_SEQ_SUBMIT;
                } else if (S21StringUtil.isEquals(NWZC150001CpouConstant.CPO_MODIFY, cpouBean.getRqstTpCd())) {
                    callSeq = NWZC150001Constant.CALL_SEQ_SUBMIT;
                } else {
                    callSeq = NWZC150001Constant.CALL_SEQ_CANCEL;
                }
                boolean isVersionCheckUpRequired = false;
                if (isPureReturn && S21StringUtil.isEquals(NWZC150001CpouConstant.CPO_SAVE, cpouBean.getRqstTpCd())) {
                    isVersionCheckUpRequired = true;
                }
                callRtrnUpdateAPI(pMsg, cpouBean, resPMsg3List, dbCpoTMsg, isPureReturn, isVersionCheckUpRequired, callSeq);
            }
            // 2017/04/11 S21_NA#Review structure Lv.2 Add End

            // ===================================================================
            // update [CPO].
            // ===================================================================
            // Calculation total amount from details.
            // QC#19040 2017/06/14 Add Start
            cpouBean.setEntCpoTotDealSlsAmt(BigDecimal.ZERO);
            cpouBean.setEntCpoTotDealNetAmt(BigDecimal.ZERO);
            cpouBean.setEntCpoTotFuncSlsAmt(BigDecimal.ZERO);
            cpouBean.setEntCpoTotFuncNetAmt(BigDecimal.ZERO);
            cpouBean.setCpoTotDealSlsAmt(BigDecimal.ZERO);
            cpouBean.setCpoTotDealNetAmt(BigDecimal.ZERO);
            cpouBean.setCpoTotFuncSlsAmt(BigDecimal.ZERO);
            cpouBean.setCpoTotFuncNetAmt(BigDecimal.ZERO);
            // QC#19040 2017/06/14 Add End
            NWZC150001CpouEditAmount.getInstance().setAmountFromCpoDtl(pMsg, cpouBean);
            NWZC150001CpouEditAmount.getInstance().setAmountFromCpoRtrnDtl(pMsg, cpouBean);

            // 2017/06/13 S21_NA#18869-2 Mod Start
//            NWZC150001CpouUpdCpo.getInstance().updateCpo(pMsg, ruPMsg, cpouBean, dbCpoTMsg);
            NWZC150001CpouUpdCpo updCpo = new NWZC150001CpouUpdCpo();
            updCpo.updateCpo(pMsg, ruPMsg, cpouBean, dbCpoTMsg);
            // 2017/06/13 S21_NA#18869-2 Mod End

            // 1.2WDS add start -->
            // ===================================================================
            // update [DS_SITE_SRVY].
            // ===================================================================
            // updateDsSiteSrvy(cpoBean); // S21_NA#10321-27 Del

            // START DELETE M.Fuji [OM040]
            //            // ===================================================================
            //            // update [DS_MDSE_OTH_SLS_REP].
            //            // ===================================================================
            //            updateDsMdseOthSlsRep(cpoBean);
            //            // 1.2WDS add end <--
            // END DELETE M.Fuji [OM040]

            // ===================================================================
            // change [ORD_NUM] of [LB_REQ_INFO].
            // ===================================================================
            // 2017/06/13 S21_NA#18869-2 Mod Start
//            NWZC150001CpouUpdReqMsgData updReqMsgDataInstance = NWZC150001CpouUpdReqMsgData.getInstance();
            NWZC150001CpouUpdReqMsgData updReqMsgDataInstance = new NWZC150001CpouUpdReqMsgData();
            // 2017/06/13 S21_NA#18869-2 Mod End
            if (isSubmitRqst) {
                updReqMsgDataInstance.changeLbReqInfoCpoNum(cpouBean);
            }

            // ===================================================================
            // change [ORD_NUM] of [MSG_TXT_DTL].
            // ===================================================================
            if (isSubmitRqst) {
                updReqMsgDataInstance.changeMsgTxtDtlCpoNum(cpouBean);
            }

            // START DELETE S.Yamamoto [MS013]
            //            // 20130123 Defect#372 M.Fuji Start
            //            // ===================================================================
            //            // change [ORD_NUM] of [SUPD_TRX].
            //            // ===================================================================
            //            if (isSubmitRqst) {
            //                changeSupdTrxCpoNum(cpoBean);
            //            }
            //            // 20130123 Defect#372 M.Fuji End
            // END   DELETE S.Yamamoto [MS013]

            // ===================================================================
            // delete [LB_REQ_INFO].
            // ===================================================================
            if (isSaveRqst) {
                updReqMsgDataInstance.deleteLbReqInfo(cpouBean);
            }

            // ===================================================================
            // delete [MSG_TXT_DTL].
            // ===================================================================
            if (isSaveRqst) {
                updReqMsgDataInstance.deleteMsgTxtDtl(cpouBean);
            }

            // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//            // ===================================================================
//            // Update Promotion by [NWZC209001 :Promotion Validation
//            // API]
//            // ===================================================================
//            // 1.2WDS add start -->
//            callPromotionValidationAPI(cpoBean, pMsg);
//            // 1.2WDS add end <--
            // 2017/03/31 S21_NA#Review structure Lv.2 Del End

            // START DELETE S.Yamamoto [OM004]
            //            // ===================================================================
            //            // Credit Limit Update by [NMZC600001 : Credit Limit
            //            // Update API].
            //            // ===================================================================
            //            // 1.2WDS add start -->
            //
            //            // 20130307 Defect#785 S.Iidaka Start
            ////            if (!DS_PMT_METH.CREDIT_CARD.equals(cpoBean.getDsPmtMethCd()) && (isCancelRqst || isModifyRqst)) {
            //            if ((isCancelRqst || isModifyRqst) && !wasDsPmtMethCC) {
            //            // 20130307 Defect#785 S.Iidaka End
            //
            //                callCreditLimitUpdateAPI(cpoBean, pMsg);
            //            }
            //            // 1.2WDS add end <--
            // START DELETE S.Yamamoto [OM004]

            // ===================================================================
            // update [SHPG_PLN]. no update InShed.
            // ===================================================================
            // 1.2WDS add start -->
            final List<SHPG_PLNTMsg> removeShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();
            final List<SHPG_PLNTMsg> createShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();
            final List<SHPG_PLNTMsg> updateShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();
            final List<SHPG_PLNTMsg> cancelShpgPlnTMsgList = new ArrayList<SHPG_PLNTMsg>();

            // 2017/06/13 S21_NA#18869-2 Add Start
            NWZC150001CpouUpdShpgPln updShpgPlnInstance = new NWZC150001CpouUpdShpgPln();
            // 2017/06/13 S21_NA#18869-2 Add End
            if (!isSaveRqst) {
                // 2017/06/13 S21_NA#18869-2 Mod Start
//                NWZC150001CpouUpdShpgPln.getInstance().updateShpgPln(cpouBean, removeShpgPlnTMsgList, createShpgPlnTMsgList, updateShpgPlnTMsgList, cancelShpgPlnTMsgList, pMsg);
                // Mod Start 2019/07/31 QC#52267
//                updShpgPlnInstance.updateShpgPln(cpouBean, removeShpgPlnTMsgList, createShpgPlnTMsgList, updateShpgPlnTMsgList, cancelShpgPlnTMsgList, pMsg);
                if (!updShpgPlnInstance.updateShpgPln(cpouBean, removeShpgPlnTMsgList, createShpgPlnTMsgList, updateShpgPlnTMsgList, cancelShpgPlnTMsgList, pMsg, resPMsg2List, onBatchType)) {
                    return;
                }
                // Mod End 2019/07/31 QC#52267
                // 2017/06/13 S21_NA#18869-2 Mod End
            }
            // 1.2WDS add end <--

            // ===================================================================
            // update [DS_MDSE_SER_NUM_ASG].
            // ===================================================================
            // 1.2WDS add start -->
            // updateSoSer(cpoBean); // S21_NA#10321-27 Del
            // 1.2WDS add end <--

            // ===================================================================
            // update [DS_CTAC_PSN].
            // ===================================================================
            // 1.2WDS add start -->
            // updateHeaderDsCtacPsn(cpoBean); // S21_NA#10321-27 Del

            // updateDetailDsCtacPsn(cpoBean); // S21_NA#10321-27 Del
            // 1.2WDS add end <--

            // ===================================================================
            // update [DS_CPO_PICK_UP_MACH].
            // ===================================================================
            // 1.2WDS add start -->
            // updateDsCpoPickUpMach(cpoBean); // S21_NA#10321-27 Del
            // 1.2WDS add end <--

            // 20121130 M.Fuji WDS Solution#104,105 Pricing Start
            // ===================================================================
            // update [ORD_PRC_CALC_BASE].
            // ===================================================================
            List<ORD_PRC_CALC_BASETMsg> insCalcBaseTMsgAry = new ArrayList<ORD_PRC_CALC_BASETMsg>();
            List<ORD_PRC_CALC_BASETMsg> updCalcBaseTMsgAry = new ArrayList<ORD_PRC_CALC_BASETMsg>();
            if (!isCancelRqst) {
                // 2017/06/13 S21_NA#18869-2 Mod Start
//                NWZC150001CpouUpdCpoDtl.getInstance().updateOrdPrcCalcBase(cpouBean, insCalcBaseTMsgAry, updCalcBaseTMsgAry);
                getCpouUpdCpoDtlInstance().updateOrdPrcCalcBase(cpouBean, insCalcBaseTMsgAry, updCalcBaseTMsgAry);
                // 2017/06/13 S21_NA#18869-2 Mod end
            }

            // ===================================================================
            // update [PRC_DTL].
            // ===================================================================
            // 2017/06/13 S21_NA#18869-2 Mod Start
//            NWZC150001CpouUpdShpgPln.getInstance().updatePrcDtlForWDS(cpouBean, removeShpgPlnTMsgList, createShpgPlnTMsgList, updateShpgPlnTMsgList); // S21_NA#11972,13616 MOD
            updShpgPlnInstance.updatePrcDtlForWDS(cpouBean, removeShpgPlnTMsgList, createShpgPlnTMsgList, updateShpgPlnTMsgList);
            // 2017/06/13 S21_NA#18869-2 Mod End
            // 20121129 M.Fuji WDS Solution#104,105 Pricing End

            // ===================================================================
            // print Biz Process Log.
            // ===================================================================
            final List<S21BusinessProcessLogMsg> saveBizLogMsgList = new ArrayList<S21BusinessProcessLogMsg>();
            final List<S21BusinessProcessLogMsg> submitBizLogMsgList = new ArrayList<S21BusinessProcessLogMsg>();
            final List<S21BusinessProcessLogMsg> modifyBizLogMsgList = new ArrayList<S21BusinessProcessLogMsg>();
            final List<S21BusinessProcessLogMsg> cancelBizLogMsgList = new ArrayList<S21BusinessProcessLogMsg>();
            final List<S21BusinessProcessLogMsg> closeBizLogMsgList = new ArrayList<S21BusinessProcessLogMsg>();
            // 2017/06/13 S21_NA#18869-2 Mod Start
//            NWZC150001CpouInsBizProcLog cpouInsBizProcLog = NWZC150001CpouInsBizProcLog.getInstance();
            NWZC150001CpouInsBizProcLog cpouInsBizProcLog = new NWZC150001CpouInsBizProcLog();
            // 2017/06/13 S21_NA#18869-2 Mod End

            // Assort
            cpouInsBizProcLog.assortBizPrcessLogMsgs(cpouBean, //
                    saveBizLogMsgList, //
                    submitBizLogMsgList, //
                    modifyBizLogMsgList, //
                    cancelBizLogMsgList, //
                    closeBizLogMsgList);
            // print Biz Process Logs.
            cpouInsBizProcLog.printBizProcessLog(saveBizLogMsgList, submitBizLogMsgList, modifyBizLogMsgList);

            // ===================================================================
            // update [HLD].
            // ===================================================================
            final List<HLDTMsg> releaseHoldList = new ArrayList<HLDTMsg>();
            // 2017/06/13 S21_NA#18869-2 Add Start
            NWZC150001CpouUpdHld updHldInstance = new NWZC150001CpouUpdHld();
            // 2017/06/13 S21_NA#18869-2 Add End
            if (!isSaveRqst && !isSubmitRqst) {
                // 2017/06/13 S21_NA#18869-2 Mod Start
//                NWZC150001CpouUpdHld.getInstance().updateHld(cpouBean, removeShpgPlnTMsgList, releaseHoldList, createShpgPlnTMsgList, isPayerChanged);
                updHldInstance.updateHld(cpouBean, removeShpgPlnTMsgList, releaseHoldList, createShpgPlnTMsgList, isPayerChanged);
                // 2017/06/13 S21_NA#18869-2 Mod End
            }

            // ===================================================================
            // update [HLD](manual).
            // ===================================================================
            // 1.2WDS add start -->
            // 2017/06/13 S21_NA#18869-2 Mod Start
//            NWZC150001CpouUpdHld.getInstance().updateHld(cpouBean);
            updHldInstance.updateHld(cpouBean);
            // 2017/06/13 S21_NA#18869-2 Mod End
            // 1.2WDS add end <--

            // add start 2023/04/25 QC#61337
            CPO_VTMsg aftCpoVTMsg = getCpoV(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
            updateCpoAmtChngInd(befCpoVTMsg, aftCpoVTMsg);
            // add end 2023/04/25 QC#61337

            // ===================================================================
            // Validation by [NWZC004001 : Validation Process Manager
            // API].
            // ===================================================================
            if (!isSaveRqst) {
                if (isPureReturn) {
                    validateOrder(pMsg, cpouBean, resPMsg3List);
                    if (NWZC150001Common.hasReturnDtlError(resPMsg3List, this.msgIdMgr)) {
                        return;
                    }
                } else {
                    callValidationProcessMgrAPI(cpouBean, resPMsg2List, releaseHoldList);
                    if (NWZC150001Common.hasError(resPMsg2List, this.msgIdMgr)) {
                        return;
                    }
                }
            }

            // ===================================================================
            // Allocation by [NWZC102001 : Allocation API].
            // ===================================================================
            if (!isSaveRqst && !isSubmitRqst) {
                callAllocationAPI(cpouBean, resPMsg2List, pMsg);
                if (NWZC150001Common.hasError(resPMsg2List, this.msgIdMgr)) {
                    return;
                }
            }

            // ===================================================================
            // update [SHPG_PLN]. only InShed
            // ===================================================================
            if (isCancelRqst) {
                // 2017/06/13 S21_NA#18869-2 Mod Start
//                NWZC150001CpouUpdShpgPln.getInstance().cancelShpgPlnModeInShed(cancelShpgPlnTMsgList);
                updShpgPlnInstance.cancelShpgPlnModeInShed(cancelShpgPlnTMsgList);
                // 2017/06/13 S21_NA#18869-2 Mod End
            }

            // 2018/06/20 QC#20154 Add Start
            // ===================================================================
            // Update Shipping Order by [NLZC205001 : SO API].
            // ===================================================================
            if (!isSaveRqst) {
                callSoAPI(cpouBean, resPMsg2List, pMsg);
                if (NWZC150001Common.hasError(resPMsg2List, this.msgIdMgr)) {
                    return;
                }
            }
            // 2018/06/20 QC#20154 Add End

            // ===================================================================
            // print Biz Process Log. ([Cancel], [Close] and [Close
            // CPO])
            // ===================================================================
            if (!isSaveRqst) {

                // print Biz Process Logs.
                cpouInsBizProcLog.printBizProcessLog(cancelBizLogMsgList, closeBizLogMsgList);

                if (submitBizLogMsgList.isEmpty()) {
                    if (modifyBizLogMsgList.isEmpty()) {
                        if (!cancelBizLogMsgList.isEmpty() || !closeBizLogMsgList.isEmpty()) {
                            // print Biz Process for Close [CPO].
                            cpouInsBizProcLog.printBizProcessLogForCloseCPO(cpouBean);
                        }
                    }
                }
            }

            // ===================================================================
            // insert CPO Records.
            // ===================================================================

            // 2015/08/27 CSA Mod Start
//            insertCpoRecord(cpouBean);
            NWZC150001CpouInsRecRecord cpouInsRecRec = new NWZC150001CpouInsRecRecord();

            cpouInsRecRec.addCpoDtl(insCpoDtlTMsgAry);
            cpouInsRecRec.addCpoDtl(updCpoDtlTMsgAry);

            cpouInsRecRec.addOrdPrcCalcBase(insCalcBaseTMsgAry);
            cpouInsRecRec.addOrdPrcCalcBase(updCalcBaseTMsgAry);

            cpouInsRecRec.insertCpoRecord(cpouBean);
            //            if (!isSaveRqst) {
            //                insertCpoRecord(cpoBean);
            //            }
            // 2015/08/27 CSA Mod End

            // 20130211 Defect#541 M.Fuji Start
            // ===================================================================
            // check whether Freight in Order equals Freight in Invoice.
            // ===================================================================
            if (isModifyRqst) {
                checkFreight(cpouBean, resPMsg2List);
            }
            // 20130211 Defect#541 M.Fuji End

            // ===================================================================
            // set result data.
            // ===================================================================
            setResData(cpouBean, pMsg, resPMsg2List);

            // 2017/05/22 S21_NA#Review structure Lv.2 Display Status Change Add Start
            if (isSubmitRqst) {
                List<SHPG_PLNTMsg> allShpgPlnList = new ArrayList<SHPG_PLNTMsg>(removeShpgPlnTMsgList);
                allShpgPlnList.addAll(createShpgPlnTMsgList);
                allShpgPlnList.addAll(updateShpgPlnTMsgList);
                allShpgPlnList.addAll(cancelShpgPlnTMsgList);

                NWZC188001PMsg dplyStsApiPMsg = new NWZC188001PMsg();
                dplyStsApiPMsg.glblCmpyCd.setValue(cpouBean.getGlblCmpyCd());
                dplyStsApiPMsg.cpoOrdNum.setValue(NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
                int n = 0;
                for (SHPG_PLNTMsg shpgPlnTMsg : allShpgPlnList) {
                    String shpgPlnNum = shpgPlnTMsg.shpgPlnNum.getValue();
                    dplyStsApiPMsg.shpgPlnNumList.no(n).shpgPlnNum.setValue(shpgPlnNum);
                    n++;
                }
                dplyStsApiPMsg.shpgPlnNumList.setValidCount(n);

                if (pMsg.rtnDtl.getValidCount() > 0) {
                    for (n = 0; n < pMsg.rtnDtl.getValidCount(); n++) {
                        String dsCpoRtrnLineNum = pMsg.rtnDtl.no(n).cpoDtlLineNum_B1.getValue();
                        String dsCpoRtrnLineSubNum = pMsg.rtnDtl.no(n).cpoDtlLineSubNum_B1.getValue();

                        dplyStsApiPMsg.rmaLineList.no(n).dsCpoRtrnLineNum.setValue(dsCpoRtrnLineNum);
                        dplyStsApiPMsg.rmaLineList.no(n).dsCpoRtrnLineSubNum.setValue(dsCpoRtrnLineSubNum);
                    }
                }
                dplyStsApiPMsg.rmaLineList.setValidCount(n);

                new NWZC188001().execute(dplyStsApiPMsg, onBatchType);
                // 2017/05/22 S21_NA#Review structure Lv.2 Display Status Change Add End
            }
            // 
        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    private void checkFreight(NWZC150001CpouBean cpoBean, List<NWZC150002PMsg> resPMsgList) {

        for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {

            NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);

            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getXxTotFrtAmt())) {
                cpoDtlBean.setXxTotFrtAmt(BigDecimal.ZERO);
            }
            if (!ZYPCommonFunc.hasValue(cpoDtlBean.getXxTotSpclChrgAmt())) {
                cpoDtlBean.setXxTotSpclChrgAmt(BigDecimal.ZERO);
            }

            BigDecimal paramFrtAmt = cpoDtlBean.getXxTotFrtAmt().add(cpoDtlBean.getXxTotSpclChrgAmt());
            BigDecimal shpgPlnFrtAmt = getLineFrtAmt(NWZC150001Common.getCpoOrdNumFromBean(cpoBean), cpoDtlBean);

            if (paramFrtAmt.compareTo(shpgPlnFrtAmt) != 0) {
                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1219W, resPMsgList, i);
            }
        }
    }

    private BigDecimal getLineFrtAmt(String cpoOrdNum, NWZC150001CpouDetailBean detailBean) {

        final Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", detailBean.getGlblCmpyCd());
        ssmParam.put("trxHdrNum", cpoOrdNum);
        ssmParam.put("trxLineNum", detailBean.getCpoDtlLineNum());
        ssmParam.put("trxLineSubNum", detailBean.getCpoDtlLineSubNum());
        ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
        ssmParam.put("canShpgStsCd", SHPG_STS.CANCELLED);

        BigDecimal lineTotFrtAmt = (BigDecimal) ssmClient.queryObject("getLineFrtAmt", ssmParam);

        return lineTotFrtAmt;
    }


    private void setResData(NWZC150001CpouBean cpoBean, NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList) {
        final String methodNm = "setResData";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            // Header
            setValue(pMsg.cpoOrdTs, cpoBean.getCpoOrdTs());

            // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//            // Detail
//            for (int i = 0; i < cpoBean.getDtlBeanList().size(); i++) {
//
//                final NWZC150002PMsg resPMsg = resPMsgList.get(i);
//                final NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(i);
//
//                setValue(resPMsg.expdShipDt, cpoDtlBean.getExpdShipDt());
//                setValue(resPMsg.exptFlg, cpoDtlBean.getExptFlg());
//                setValue(resPMsg.cpoOrdTs, cpoDtlBean.getCpoOrdSubmtTs());
//            }
            // 2017/03/31 S21_NA#Review structure Lv.2 Del End

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * Validation processing
     * 
     * <pre>
     * Submit : Only Order Number is set, and Validation API(Order Validation) is called.
     * Modify : Only changed details are set, and Validation API(Order Validation) is called.
     * Cancel : Only details that Cancel cannot do are set, and Validation API(Order Validation) is called.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param releaseHoldList List<HLDTMsg>
     */
    private void callValidationProcessMgrAPI(NWZC150001CpouBean cpouBean, //
            List<NWZC150002PMsg> resPMsgList, //
            List<HLDTMsg> releaseHoldList) {
        final String methodNm = "callValidationProcessMgrAPI";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final String rqstTpCd = cpouBean.getRqstTpCd();

            // 20130215 Defect#316 A.Suda Start
            if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {
                for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {

                    final NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);
                    final String dtlRqstTpCd = cpoDtlBean.getDtlRqstTpCd();

                    if (NWZC150001CpouConstant.CPO_DTL_SUBMIT.equals(dtlRqstTpCd)) {
                        if (!cpoDtlBean.getShipCpltCd().equals(cpoDtlBean.getShipCpltCdOld())) {
                            // --------------------------------------------------
                            // create PMsg to call API.
                            // --------------------------------------------------
                            NWZC004001PMsg apiPMsg = new NWZC004001PMsg();
                            setValue(apiPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                            setValue(apiPMsg.slsDt, cpouBean.getSlsDt());
                            setValue(apiPMsg.cpoOrdNum_I, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
                            setValue(apiPMsg.xxRqstTpCd, NWZC004001.VAL_TP_OV);
                            setValue(apiPMsg.cpoDtlLineNum_I, NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
                            setValue(apiPMsg.cpoDtlLineSubNum_I, NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));

                            // --------------------------------------------------
                            // call [NWZC004001 : Validation Process
                            // Manager
                            // API]
                            // --------------------------------------------------
                            ((NWZC004001) getAPI(NWZC004001.class)).execute(apiPMsg, this.onBatchType);

                            // --------------------------------------------------
                            // API Error Judgment
                            // --------------------------------------------------
                            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                                for (int j = 0; j < apiPMsg.xxMsgIdList.getValidCount(); j++) {
                                    final String xxMsgId = apiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(xxMsgId, resPMsgList, i);
                                }
                                return;
                            }
                        }
                    }
                }
            }
            // 20130215 Defect#316 A.Suda End

            for (int i = 0; i < cpouBean.getDtlBeanList().size(); i++) {

                final NWZC150001CpouDetailBean cpoDtlBean = cpouBean.getDtlBeanList().get(i);

                // is cancelled line?
                boolean isCancelledLine = false;
                if (NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd)) {
                    isCancelledLine = NWZC150001Common.isCancelled(cpoDtlBean);
                }

                boolean execFlg = false;
                // --------------------------------------------------
                // create PMsg to call API.
                // --------------------------------------------------
                NWZC004001PMsg apiPMsg = new NWZC004001PMsg();
                setValue(apiPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                setValue(apiPMsg.slsDt, cpouBean.getSlsDt());
                setValue(apiPMsg.cpoOrdNum_I, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));

                if (releaseHoldList.size() != 0) {
                    setValue(apiPMsg.xxRqstTpCd, NWZC004001.VAL_TP_RC);
                    execFlg = true;

                } else if (isCancelledLine) {
                    setValue(apiPMsg.xxRqstTpCd, NWZC004001.VAL_TP_RC);
                    execFlg = true;

                } else if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd)) {
                    if (!cpoDtlBean.getShipCpltCd().equals(cpoDtlBean.getShipCpltCdOld())) {
                        setValue(apiPMsg.xxRqstTpCd, NWZC004001.VAL_TP_RC);
                        execFlg = true;
                    }
                }

                if (execFlg) {
                    // --------------------------------------------------
                    // call [NWZC004001 : Validation Process Manager
                    // API]
                    // --------------------------------------------------
                    ((NWZC004001) getAPI(NWZC004001.class)).execute(apiPMsg, this.onBatchType);

                    // --------------------------------------------------
                    // API Error Judgment
                    // --------------------------------------------------
                    if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                        for (int j = 0; j < apiPMsg.xxMsgIdList.getValidCount(); j++) {
                            final String xxMsgId = apiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                            NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(xxMsgId, resPMsgList, i);
                        }
                        return;
                    }
                }

                // 06/24/2010 Defect 7396
                if (isCancelledLine) {
                    return;
                }

                // --------------------------------------------------
                // create PMsg to call API.
                // --------------------------------------------------
                apiPMsg = new NWZC004001PMsg();
                setValue(apiPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                setValue(apiPMsg.slsDt, cpouBean.getSlsDt());
                setValue(apiPMsg.cpoOrdNum_I, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
                setValue(apiPMsg.xxRqstTpCd, NWZC004001.VAL_TP_OV);

                if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) || (NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd) && !isCancelledLine)) {
                    setValue(apiPMsg.cpoDtlLineNum_I, NWZC150001Common.getCpoDtlLineNumFromBean(cpoDtlBean));
                    setValue(apiPMsg.cpoDtlLineSubNum_I, NWZC150001Common.getCpoDtlLineSubNumFromBean(cpoDtlBean));
                }

                // S21_NA#12234 Add Start
                if (!NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd) && i != cpouBean.getDtlBeanList().size() - 1) {
                    // Not Last Loop
                    setValue(apiPMsg.xxRsltFlg, ZYPConstant.FLG_OFF_N);
                }
                // S21_NA#12234 Add End

                // --------------------------------------------------
                // call [NWZC004001 : Validation Process Manager API]
                // --------------------------------------------------
                ((NWZC004001) getAPI(NWZC004001.class)).execute(apiPMsg, this.onBatchType);

                // --------------------------------------------------
                // API Error Judgment
                // --------------------------------------------------
                if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int j = 0; j < apiPMsg.xxMsgIdList.getValidCount(); j++) {
                        final String xxMsgId = apiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(xxMsgId, resPMsgList, i);
                    }
                    return;
                }

                if (NWZC150001CpouConstant.CPO_SUBMIT.equals(rqstTpCd)) {
                    return;
                }

            }

            // Def#1634 add start -->
            // --------------------------
            // execute recalculation(after order validation)
            // --------------------------
            if (cpouBean.getDtlBeanList().size() > 0) {
                // --------------------------------------------------
                // create PMsg to call API.
                // --------------------------------------------------
                NWZC004001PMsg apiPMsg = new NWZC004001PMsg();
                setValue(apiPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
                setValue(apiPMsg.slsDt, cpouBean.getSlsDt());
                setValue(apiPMsg.cpoOrdNum_I, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
                setValue(apiPMsg.xxRqstTpCd, NWZC004001.VAL_TP_RC);

                // --------------------------------------------------
                // call [NWZC004001 : Validation Process Manager API]
                // --------------------------------------------------
                ((NWZC004001) getAPI(NWZC004001.class)).execute(apiPMsg, this.onBatchType);

                // --------------------------------------------------
                // API Error Judgment
                // --------------------------------------------------
                if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                    for (int j = 0; j < apiPMsg.xxMsgIdList.getValidCount(); j++) {
                        final String xxMsgId = apiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(xxMsgId, resPMsgList, 0);
                    }
                    return;
                }
            }
            // Def#1634 add end <--
        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }


    /**
     * Allocation processing
     * 
     * <pre>
     * Modify : Only the Allcation object is set, and the Allocation API call.
     * Cancel : Allocation API call of all.
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param pMsg NWZC150001PMsg
     */
    private void callAllocationAPI(NWZC150001CpouBean cpoBean, //
            List<NWZC150002PMsg> resPMsgList, //
            NWZC150001PMsg pMsg) {
        final String methodNm = "callAllocationAPI";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

            final List<NWZC102001PMsg> apiPMsgList = new ArrayList<NWZC102001PMsg>();

            // --------------------------------------------------
            // create List<PMsg> to call API.
            // --------------------------------------------------
            for (NWZC150001CpouDetailBean cpoDtlBean : cpoBean.getDtlBeanList()) {

                if (NWZC150001CpouConstant.CPO_MODIFY.equals(cpoBean.getRqstTpCd())) {
                    if (!NWZC150001CpouConstant.CPO_DTL_MODIFY.equals(cpoDtlBean.getDtlRqstTpCd())) {
                        continue;
                    }
                }

                NWZC102001PMsg apiPMsg = new NWZC102001PMsg();
                apiPMsgList.add(apiPMsg);
                apiPMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
                apiPMsg.xxOrigFuncTpCd.setValue(NWZC102001.ORG_FUNC_CD_ORDER_ENTRY);
                apiPMsg.xxRqstTpCd.setValue(NWZC102001.REQ_TP_ORD_CANCEL);
                apiPMsg.xxAllocTpCd.setValue(NWZC102001.ALLOC_TP_CD_SOFT_HARD_ALLOC);
                apiPMsg.trxSrcTpCd.setValue(TRX_SRC_TP.WHOLE_SALES);
                apiPMsg.trxHdrNum.setValue(cpoBean.getCpoOrdNum());
                apiPMsg.slsDt.setValue(cpoBean.getSlsDt());
                apiPMsg.trxLineNum.setValue(cpoDtlBean.getCpoDtlLineNum());
                apiPMsg.trxLineSubNum.setValue(cpoDtlBean.getCpoDtlLineSubNum());
            }

            if (!apiPMsgList.isEmpty()) {

                // --------------------------------------------------
                // call [NWZC102001 : Allocation API]
                // --------------------------------------------------
                ((NWZC102001) getAPI(NWZC102001.class)).execute(apiPMsgList, this.onBatchType);

                // --------------------------------------------------
                // API Error Judgment
                // --------------------------------------------------
                for (int i = 0; i < apiPMsgList.size(); i++) {

                    NWZC102001PMsg apiMsg = apiPMsgList.get(i);
                    String trxLineNum = apiMsg.trxLineNum.getValue();
                    String trxLineSubNum = apiMsg.trxLineSubNum.getValue();

                    for (int j = 0; j < cpoBean.getDtlBeanList().size(); j++) {

                        NWZC150001CpouDetailBean cpoDtlBean = cpoBean.getDtlBeanList().get(j);
                        String cpoDtlLineNum = cpoDtlBean.getCpoDtlLineNum();
                        String cpoDtlLineSubNum = cpoDtlBean.getCpoDtlLineSubNum();

                        if (trxLineNum.equals(cpoDtlLineNum) && trxLineSubNum.equals(cpoDtlLineSubNum)) {

                            for (int k = 0; k < pMsg.xxMsgIdList.getValidCount(); k++) {
                                String xxMsgId = pMsg.xxMsgIdList.no(k).xxMsgId.getValue();
                                NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(xxMsgId, resPMsgList, j);
                            }
                        }
                    }
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * validateOrder
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     * @param pMsg linePMsgList
     * @param isModifyHdrRqst boolean
     * @param isNewHdrRqst boolean
     */
//    private void validateOrder(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, List<NWZC150003PMsg> linePMsgList, boolean isNewHdrRqst, boolean isModifyHdrRqst) {
     private void validateOrder(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, List<NWZC150003PMsg> linePMsgList) {
         // 2017/06/20 S21_NA#19288-2 Add Start
         boolean isCallRtrnValidation = false;
         if (S21StringUtil.isEquals(NWZC153001Constant.RQST_HDR_NEW, rtrnMode)) {
             isCallRtrnValidation = true;
         }
         if (S21StringUtil.isEquals(MODE_CANCEL, pMsg.xxModeCd.getValue())) {
             if (!NWZC150001Query.getInstance().hasOpenCpoDtl(cpouBean) //
                     && NWZC150001Query.getInstance().hasOpenReturnDtl(cpouBean)) {
                 isCallRtrnValidation = true;
             }
         }
         // 2017/06/20 S21_NA#19288-2 Add End
         // 2017/06/20 S21_NA#19288-2 Mod Start
//     if (S21StringUtil.isEquals(NWZC153001Constant.RQST_HDR_NEW, rtrnMode)) {
         if (isCallRtrnValidation) { // 2017/06/20 S21_NA#19288-2 Mod End
            callNWZC40001RtrnValid(pMsg, cpouBean, linePMsgList);
            return;
        }

        if (S21StringUtil.isEquals(NWZC153001Constant.RQST_HDR_MOD, rtrnMode)) {
            for (int idx = 0; idx < pMsg.rtnDtl.getValidCount(); idx++) {
                NWZC150001_rtnDtlPMsg rtnDtlPMsg = pMsg.rtnDtl.no(idx);
                if (!S21StringUtil.isEquals(RQST_DTL_NEW, rtnDtlPMsg.xxRqstTpCd_B1.getValue()) //
                        && !S21StringUtil.isEquals(RQST_DTL_MOD, rtnDtlPMsg.xxRqstTpCd_B1.getValue())) {
                    continue;
                }

                if (!callNWZC40001RtrnValid(pMsg, cpouBean, rtnDtlPMsg, linePMsgList, idx)) {
                    return;
                }
            }
        }
    }


    /**
     * call NWXC001001EditPriceAmount
     * @param cpoBean NWZC153001CpoBean
     * @param linePMsgList List<NWZC153002PMsg>
     */
    private void callNWZC40001RtrnValid(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, List<NWZC150003PMsg> linePMsgList) {
        NWZC400001PMsg rtrnValidPMsg = new NWZC400001PMsg();

        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.slsDt, cpouBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.xxProcMd, RTRN_VALID_MODE_ORD_VALID);
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.cpoOrdNum_I, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));

        NWZC400001 rtrnValidApi = new NWZC400001();
        rtrnValidApi.execute(rtrnValidPMsg, this.onBatchType);

        if (rtrnValidPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int j = 0; j < rtrnValidPMsg.xxMsgIdList.getValidCount(); j++) {
                String xxMsgId = rtrnValidPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                this.msgIdMgr.addXxMsgId(xxMsgId, pMsg);
            }
        }
    }

    /**
     * call NWXC001001EditPriceAmount
     * @param cpoBean NWZC153001CpoBean
     * @param dtlBean NWZC153001DetailBean
     * @param linePMsgList List<NWZC153002PMsg>
     * @param idx int
     * @return BigDecimal
     */
    private boolean callNWZC40001RtrnValid(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, NWZC150001_rtnDtlPMsg rtnDtlPMsg, List<NWZC150003PMsg> linePMsgList, int idx) {
        NWZC400001PMsg rtrnValidPMsg = new NWZC400001PMsg();

        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.slsDt, cpouBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.xxProcMd, RTRN_VALID_MODE_ORD_VALID);
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.cpoOrdNum_I, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.dsCpoRtrnLineNum_I, rtnDtlPMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(rtrnValidPMsg.dsCpoRtrnLineSubNum_I, rtnDtlPMsg.cpoDtlLineSubNum_B1);

        NWZC400001 rtrnValidApi = new NWZC400001();
        rtrnValidApi.execute(rtrnValidPMsg, this.onBatchType);

        if (rtrnValidPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int j = 0; j < rtrnValidPMsg.xxMsgIdList.getValidCount(); j++) {
                String xxMsgId = rtrnValidPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                this.msgIdMgr.addXxMsgId(xxMsgId, pMsg);
            }
            return false;
        }
        return true;
    }


    /**
     * isTargetOfOrdValidation
     * @param cpoBean NWZC153001CpoBean
     * @return boolean
     */
    private boolean isTargetOfOrdValidation(NWZC150001CpouBean cpouBean) {

        CPO_DTLTMsg rqstCpoDtlTMsg = new CPO_DTLTMsg();
        rqstCpoDtlTMsg.setSQLID("001");
        rqstCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        rqstCpoDtlTMsg.setConditionValue("cpoOrdNum01", NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
        CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(rqstCpoDtlTMsg);

        // 2017/06/16 S21_NA#19288 Mod Start
//        if (cpoDtlTMsgArray.getValidCount() < 0) {
        if (cpoDtlTMsgArray.getValidCount() <= 0) {
            // 2017/06/16 S21_NA#19288 Mod End
            return true;
        }

        for (int i = 0; i < cpoDtlTMsgArray.length(); i++) {
            CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) cpoDtlTMsgArray.get(i);
            if (!ORD_LINE_STS.CANCELLED.equals(cpoDtlTMsg.ordLineStsCd.getValue())) {
                return false;
            }
        }
        return true;
    }

    private S21ApiCommonBase getAPI(Class<? extends S21ApiCommonBase> clazz) {

        if (!apiCache.containsKey(clazz)) {
            try {
                apiCache.put(clazz, (S21ApiCommonBase) Class.forName(clazz.getName()).newInstance());
            } catch (Exception e) {
                throw new S21AbendException(e);
            }
        }

        return apiCache.get(clazz);
    }

    /**
     * <pre>
     * 
     * </pre>
     * @param pMsg API Parameter
     * @param pMsg3List return error message
     * @param cpoTMsg CPO record
     * @param isPureReturn true: return only order false: Outbound / Inbound mixed order
     * @param isVersionCheckUpRequired true: set version check false: not
     * @param callSeq 8 bit mode, 2nd from last means save, last bit means submit.<br>
     * ex: 0x03 means save and submit, ex: 0x02 only save, 0x01, only submit
     * @return
     */
    public boolean callRtrnUpdateAPI(NWZC150001PMsg pMsg, //
            NWZC150001CpouBean cpouBean, //
            List<NWZC150003PMsg> pMsg3List, //
            CPOTMsg cpoTMsg, //
            boolean isPureReturn, //
            boolean isVersionCheckUpRequired, //
            char callSeq) {
        if (pMsg.rtnDtl.getValidCount() == 0) {
            return false;
        }

        String cpoOrdTpCd = NWXC150001DsCheck.getCpoOrdTpCdFromDsOrdTp(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdTpCd.getValue());

        ruPMsg = new NWZC153001PMsg();
        final List<NWZC153002PMsg> cpoRtnUpdApiOutMsgList = new ArrayList<NWZC153002PMsg>();

        EZDMsg.copy(pMsg, null, ruPMsg, null);
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(ruPMsg.cpoOrdNum, NWZC150001Common.getCpoOrdNumFromBean(cpouBean));
        }
        ZYPEZDItemValueSetter.setValue(ruPMsg.slsRepTocCd, pMsg.slsRepCd); // 2015/12/17 S21_NA#2007 Add
        ZYPEZDItemValueSetter.setValue(ruPMsg.cpoOrdTpCd, cpoOrdTpCd);

        int idx = 0;
        // Copy Return Detail
        for (; idx < pMsg.rtnDtl.getValidCount(); idx++) {
            EZDMsg.copy(pMsg.rtnDtl.no(idx), "B1", ruPMsg.ordRtrnDtlList.no(idx), "");

            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineNum, pMsg.rtnDtl.no(idx).cpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineSubNum, pMsg.rtnDtl.no(idx).cpoDtlLineSubNum_B1);

            if (!ZYPCommonFunc.hasValue(ruPMsg.ordRtrnDtlList.no(idx).slsRepOrSlsTeamTocCd)) {
                ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).slsRepOrSlsTeamTocCd, pMsg.slsRepCd);
            }
            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).pmtTermCashDiscCd, ruPMsg.addPmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).cashDiscTermCd, ruPMsg.addPmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).cpoOrdTpCd, cpoOrdTpCd);

            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
                if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
                    if (S21StringUtil.isEquals(ruPMsg.ordRtrnDtlList.no(idx).dsOrdPosnNum.getValue(), configMsg.dsOrdPosnNum.getValue())) {
                        ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(idx).dsCpoConfigPk, configMsg.dsCpoConfigPk);
                    }
                }
            }
        }
        ruPMsg.ordRtrnDtlList.setValidCount(idx);

        // Copy Return Price Calc List
        for (idx = 0; idx < pMsg.rtnPriceList.getValidCount(); idx++) {
            EZDMsg.copy(pMsg.rtnPriceList.no(idx), "", ruPMsg.prcCalcList.no(idx), "");
            ZYPEZDItemValueSetter.setValue(ruPMsg.prcCalcList.no(idx).cpoRtrnPrcCalcBasePk, pMsg.rtnPriceList.no(idx).ordPrcCalcBasePk);
            ZYPEZDItemValueSetter.setValue(ruPMsg.prcCalcList.no(idx).dsCpoRtrnLineNum, pMsg.rtnPriceList.no(idx).cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(ruPMsg.prcCalcList.no(idx).dsCpoRtrnLineSubNum, pMsg.rtnPriceList.no(idx).cpoDtlLineSubNum);
        }
        ruPMsg.prcCalcList.setValidCount(idx);

        // 2016/01/14 S21_NA#2996 Add Start
        if (isPureReturn) {
            ruPMsg.xxScrEdtTpCd.setValue(NWZC153001Constant.SCRN_EDT_TP_PURE);
        } else {
            ruPMsg.xxScrEdtTpCd.setValue(NWZC153001Constant.SCRN_EDT_TP_MIXED);
        }
        // 2016/01/14 S21_NA#2996 Add End
        if (NWZC150001Common.isSaveMode(pMsg, cpoTMsg)) {
            // 2015/12/24 S21_NA#2411 Mod Start
//            ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
            boolean isSubmit = (callSeq & NWZC150001Constant.CALL_SEQ_SUBMIT) > 0x00;
            boolean isSave = (callSeq & NWZC150001Constant.CALL_SEQ_SAVE) > 0x00;
            boolean isCancel = (callSeq & NWZC150001Constant.CALL_SEQ_CANCEL) > 0x00; // 2016/01/14 S21_NA#2996 Add
            // 2017/04/26 S21_NA#Review structure Lv.2 Add Start
            boolean isValid = (callSeq & NWZC150001Constant.CALL_SEQ_VALID) > 0x00;

            if (isValid) {
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_VAL);
                rtrnMode = NWZC153001Constant.RQST_HDR_VAL;
            } else if (isSave) { // // 2017/04/26 S21_NA#Review structure Lv.2 Add End
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
                rtrnMode = NWZC153001Constant.RQST_HDR_SAVE;
                for (int i = 0; i < ruPMsg.ordRtrnDtlList.getValidCount(); i++) {
                    String xxRqstTpCd = ruPMsg.ordRtrnDtlList.no(i).xxRqstTpCd.getValue();
                    if (!NWZC153001Constant.RQST_DTL_CANCEL.equals(xxRqstTpCd) && !NWZC153001Constant.RQST_DTL_SAVE.equals(xxRqstTpCd)) {
                        ZYPEZDItemValueSetter.setValue(ruPMsg.ordRtrnDtlList.no(i).xxRqstTpCd, NWZC153001Constant.RQST_DTL_SAVE);
                    }
                }
            } else if (isSubmit) {
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_NEW);
                rtrnMode = NWZC153001Constant.RQST_HDR_NEW;
            } else if (isCancel) { // 2016/01/14 S21_NA#2996 Add
                if (isPureReturn) {
                    ZYPEZDItemValueSetter.setValue(ruPMsg.xxScrEdtTpCd, NWZC153001Constant.SCRN_EDT_TP_PURE);
                } else {
                    ZYPEZDItemValueSetter.setValue(ruPMsg.xxScrEdtTpCd, NWZC153001Constant.SCRN_EDT_TP_MIXED);
                }
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_CANCEL);
                rtrnMode = NWZC153001Constant.RQST_HDR_CANCEL;
            } else {
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
                rtrnMode = NWZC153001Constant.RQST_HDR_SAVE;
            }
            // 2015/12/24 S21_NA#2411 Mod End
        }
        if (cpoTMsg != null && ORD_HDR_STS.VALIDATED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            if ((callSeq & NWZC150001Constant.CALL_SEQ_CANCEL) > 0x00) {// 2016/01/14 S21_NA#2996 Add Condition
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_CANCEL);
                rtrnMode = NWZC153001Constant.RQST_HDR_CANCEL;
            } else {
                ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_MOD);
                rtrnMode = NWZC153001Constant.RQST_HDR_MOD;
            }
        }
        // 2016/01/14 S21_NA#2996 Add Start
        if (cpoTMsg != null && ORD_HDR_STS.CANCELLED.equals(cpoTMsg.ordHdrStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(ruPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_CANCEL);
            rtrnMode = NWZC153001Constant.RQST_HDR_CANCEL;
        }
        // 2016/01/14 S21_NA#2996 Add End
        ZYPEZDItemValueSetter.setValue(ruPMsg.carrAcctNum, pMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(ruPMsg.invRcpntCustCd, ruPMsg.billToCustCd);
        // 2015/12/16 S21_NA#2007 Del Start
        // for (int i = 0; i < ruPMsg.ordRtrnDtlList.getValidCount(); i++) {
        //     NWZC153001_ordRtrnDtlListPMsg orPMsg = ruPMsg.ordRtrnDtlList.no(i);
        //
        //     if (!ZYPCommonFunc.hasValue(orPMsg.slsRepOrSlsTeamTocCd)) {
        //         ZYPEZDItemValueSetter.setValue(orPMsg.slsRepOrSlsTeamTocCd, pMsg.slsRepCd);
        //     }
        //     ZYPEZDItemValueSetter.setValue(orPMsg.pmtTermCashDiscCd, ruPMsg.addPmtTermCashDiscCd);
        //     ZYPEZDItemValueSetter.setValue(orPMsg.cashDiscTermCd, ruPMsg.addPmtTermCashDiscCd);
        // }
        // for (int i = 0; i < ruPMsg.prcCalcList.getValidCount(); i++) {
        //     NWZC153001_prcCalcListPMsg pc153PMsg = ruPMsg.prcCalcList.no(i);
        //     NWZC150001_rtnPriceListPMsg rp150PMsg = pMsg.rtnPriceList.no(i);

        //      EZDMsg.copy(rp150PMsg, null, pc153PMsg, null);
        //}
        // 2015/12/16 S21_NA#2007 Del End

        // S21_NA#1952 start
        String diChkHldFlg = ZYPConstant.FLG_OFF_N;
        if (cpoTMsg != null) {
            diChkHldFlg = cpoTMsg.diChkHldFlg.getValue();
        }

        if (isPureReturn & isVersionCheckUpRequired) {
            // pure return only.
            // version up required only.
            ZYPEZDItemValueSetter.setValue(ruPMsg.xxValUpdFlg, pMsg.xxValUpdFlg);
            // S21_NA#1957 start
            if (S21StringUtil.isEquals(pMsg.xxValUpdFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(ruPMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(ruPMsg.diChkHldFlg, diChkHldFlg);
            }
            // S21_NA#1957 end
        } else {
            ZYPEZDItemValueSetter.setValue(ruPMsg.xxValUpdFlg, ZYPConstant.FLG_OFF_N);
        }
        // S21_NA#1952 end

        new NWZC153001().execute(ruPMsg, cpoRtnUpdApiOutMsgList, this.onBatchType);

        if (S21ApiUtil.isXxMsgId(ruPMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(ruPMsg);
            for (String msgId : ml) {
                this.msgIdMgr.addXxMsgId(msgId, pMsg);
            }
        }

        boolean rtrnDtlErrFlg = false;
        for (int i = 0; i < cpoRtnUpdApiOutMsgList.size(); i++) {
            NWZC150003PMsg resPMsg = new NWZC150003PMsg();
            EZDMsg.copy(cpoRtnUpdApiOutMsgList.get(i), null, resPMsg, null);
            // 2016/01/08 S21_NA#2899 Add Start
            ZYPEZDItemValueSetter.setValue(resPMsg.cpoDtlLineNum, cpoRtnUpdApiOutMsgList.get(i).dsCpoRtrnLineNum);
            ZYPEZDItemValueSetter.setValue(resPMsg.cpoDtlLineSubNum, cpoRtnUpdApiOutMsgList.get(i).dsCpoRtrnLineSubNum);

            NWZC153002PMsg cpoUpdApiOutMsg = cpoRtnUpdApiOutMsgList.get(i);
            for (int j = 0; j < cpoUpdApiOutMsg.xxMsgIdList.getValidCount(); j++) {
                final String xxMsgId = cpoUpdApiOutMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    rtrnDtlErrFlg = true;
                }
            }
            // 2016/01/08 S21_NA#2899 Add End
            pMsg3List.add(resPMsg);

        }

        for (int i = 0; i < ruPMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = ruPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                return true;
            }
        }
        // 2016/01/08 S21_NA#2899 Mod Start
//        for (NWZC153002PMsg cpoUpdApiOutMsg : cpoRtnUpdApiOutMsgList) {
//
//            for (int i = 0; i < cpoUpdApiOutMsg.xxMsgIdList.getValidCount(); i++) {
//
//                final String xxMsgId = cpoUpdApiOutMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                if (xxMsgId.endsWith("E")) {
//                    return true;
//                }
//            }
//        }
        if (rtrnDtlErrFlg) {
            return true;
        }
//        // 2016/01/08 S21_NA#2899 Mod End
//        // 2015/12/16 S21_NA#2007 Add Start
//        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, ruPMsg.cpoOrdNum);
//        }
//        // 2015/12/16 S21_NA#2007 Add End
        return false;
    }

    // 2017/06/13 S21_NA#18869-2 Add Start
    private NWZC150001CpouUpdCpoDtl getCpouUpdCpoDtlInstance() {
        if (updCpoDtlInstance == null) {
            updCpoDtlInstance = new NWZC150001CpouUpdCpoDtl();
        }
        return updCpoDtlInstance;
    }
    // 2017/06/13 S21_NA#18869-2 Add End

    // 2018/06/20 QC#20154 Add Start
    /**
     * Call SO API
     * @param cpoBean NWZC150001CpouBean
     * @param resPMsgList List<NWZC150002PMsg>
     * @param pMsg NWZC150001PMsg
     */
    private void callSoAPI(NWZC150001CpouBean cpoBean, //
            List<NWZC150002PMsg> resPMsgList, //
            NWZC150001PMsg pMsg) {

        if (cpoBean.getShipToChgToWmsSendList() == null) {
            return;
        }
        for (BigDecimal dsCpoConfigPk : cpoBean.getShipToChgToWmsSendList()) {

            Map<String, Object> param = new HashMap<String, Object>();

            param.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
            param.put("cpoOrdNum", cpoBean.getCpoOrdNum());
            param.put("dsCpoConfigPk", dsCpoConfigPk);

            List<Map<String, Object>> soNumList = ssmClient.queryObjectList("getSoNumList", param);

            if (soNumList.size() == 0) {
                continue;
            }

            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                NWZC150001_cpoConfigPMsg configMsg = pMsg.cpoConfig.no(i);
                String dsOrdPosnNum = null;

                if (dsCpoConfigPk.equals(configMsg.dsCpoConfigPk.getValue())) {
                    dsOrdPosnNum = configMsg.dsOrdPosnNum.getValue();
                } else {
                    continue;
                }

                for (Map<String, Object> soNumMap : soNumList) {

                    String soNum = (String) soNumMap.get("SO_NUM");

                    SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
                    setValue(shpgOrdTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
                    setValue(shpgOrdTMsg.soNum, soNum);

                    shpgOrdTMsg = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKey(shpgOrdTMsg);

                    if (shpgOrdTMsg == null) {
                        continue;
                    }

                    List<NLZC205001PMsg> soApiParamList = new ArrayList<NLZC205001PMsg>();
                    NLZC205001 soApi = new NLZC205001();

                    NLZC205001PMsg soApiParam = new NLZC205001PMsg();
                    ZYPEZDItemValueSetter.setValue(soApiParam.glblCmpyCd, cpoBean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(soApiParam.xxModeCd, NLZC205001.MODE_UPD_SHIP_ADDR_AND_WMS);
                    ZYPEZDItemValueSetter.setValue(soApiParam.soNum, soNum);
                    ZYPEZDItemValueSetter.setValue(soApiParam.sceOrdTpCd, shpgOrdTMsg.sceOrdTpCd.getValue());
                    ZYPEZDItemValueSetter.setValue(soApiParam.shpgPlnNum, (String) soNumMap.get("SHPG_PLN_NUM"));
                    ZYPEZDItemValueSetter.setValue(soApiParam.shpgFrceFlg, ZYPConstant.FLG_OFF_N);

                    soApiParamList.add(soApiParam);

                    soApi.execute(soApiParamList, this.onBatchType);

                    for (NLZC205001PMsg soApiPMsg : soApiParamList) {
                        if (soApiPMsg.xxMsgIdList.getValidCount() > 0) {
                            for (int j = 0; j < soApiPMsg.xxMsgIdList.getValidCount(); j++) {
                                String xxMsgId = soApiPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                                NWZC150001Common.addMsgId2List(resPMsgList, dsOrdPosnNum, xxMsgId);
                            }
                        }
                    }
                }
            }
        }
    }
    // 2018/06/20 QC#20154 Add End

    // add start 2023/04/25 QC#61337
    private CPO_VTMsg getCpoV(String glblCmpyCd, String cpoOrdNum) {

        CPO_VTMsg dsCpoVTMsg = new CPO_VTMsg();
        dsCpoVTMsg.setSQLID("501");
        dsCpoVTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCpoVTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        CPO_VTMsgArray dsCpoVTMsgArray = (CPO_VTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoVTMsg);

        if (null == dsCpoVTMsgArray || dsCpoVTMsgArray.getValidCount() == 0) {
            return null;
        }
        return dsCpoVTMsgArray.no(0);
    }

    private void updateCpoAmtChngInd(CPO_VTMsg befCpoVTMsg, CPO_VTMsg aftCpoVTMsg) {

        if (befCpoVTMsg == null || aftCpoVTMsg == null) {
            return;
        }

        CPOTMsg cpoTMsg = new CPOTMsg();
        setValue(cpoTMsg.glblCmpyCd, aftCpoVTMsg.glblCmpyCd);
        setValue(cpoTMsg.cpoOrdNum, aftCpoVTMsg.cpoOrdNum);
        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdate(cpoTMsg);
        if (cpoTMsg == null) {
            return;
        }

        String befAmtChngInd = cpoTMsg.amtChngInd.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(cpoTMsg.ordBookFlg.getValue())) {
            BigDecimal befOrdTotDealNetAmt = befCpoVTMsg.ordTotDealNetAmt.getValue();
            BigDecimal befOrdTotDealChrgAmt = befCpoVTMsg.ordTotDealChrgAmt.getValue();
            BigDecimal aftfOrdTotDealNetAmt = aftCpoVTMsg.ordTotDealNetAmt.getValue();
            BigDecimal aftOrdTotDealChrgAmt = aftCpoVTMsg.ordTotDealChrgAmt.getValue();

            if (befOrdTotDealNetAmt.compareTo(aftfOrdTotDealNetAmt) != 0 || befOrdTotDealChrgAmt.compareTo(aftOrdTotDealChrgAmt) != 0) {
                setValue(cpoTMsg.amtChngInd, ZYPConstant.FLG_ON_1);
            } else {
                cpoTMsg.amtChngInd.clear();
            }
        }

        if (!S21StringUtil.isEquals(befAmtChngInd, cpoTMsg.amtChngInd.getValue())) {
            S21ApiTBLAccessor.update(cpoTMsg);
        }
    }
    // add end 2023/04/25 QC#61337
}
