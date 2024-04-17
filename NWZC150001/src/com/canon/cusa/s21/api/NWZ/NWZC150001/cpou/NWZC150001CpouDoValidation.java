/**
 * <pre>
 * Do Data Validation end derive default data same as CPO Update API
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/07   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2019/09/27   Fujitsu         R.Matsuki       Update          QC#53593
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouEditAmount;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouSetData;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.derive.NWZC150001CpouSetExpdShipDt;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouExistsCdInDbCheck;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouOtherCheck;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation.NWZC150001CpouValidCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;

public class NWZC150001CpouDoValidation {

//    private ONBATCH_TYPE onBatchType;

//    private S21ApiMessageIdMgr msgIdMgr;

    /**
     * Local Data Cache Object.
     */
    private NWZC150001CpouLocalCache localCache = null;

//    /**
//     * API cache.
//     */
//    private final Map<Class<? extends S21ApiCommonBase>, S21ApiCommonBase> apiCache = new HashMap<Class<? extends S21ApiCommonBase>, S21ApiCommonBase>();

    // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor Plan
    // Order] - START
//    private String origPayerCustCd = "";

//    private boolean isPayerChanged = false;

    // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor Plan
    // Order] - E N D

    public NWZC150001CpouDoValidation() {
//        super();
//        ssmClient = S21SsmBatchClient.getClient(this.getClass());
        localCache = new NWZC150001CpouLocalCache();
    }

    public NWZC150001CpouDoValidation(NWZC150001CpouLocalCache localCache) {
//      super();
//      ssmClient = S21SsmBatchClient.getClient(this.getClass());
        this.localCache = localCache;
  }

    /**
     * <pre>
     * Execute validation and derive default data same as CPO Update API
     * @param pMsg DS CPO Update API PMessage
     * @param cpouBean cpou bean, must be set data from PMessage in advance.
     * @param resPMsgList result message list
     * @param onBatchType On-Batch type
     */
    public void executeValidation(NWZC150001PMsg pMsg, //
            NWZC150001CpouBean cpouBean, //
            final List<NWZC150002PMsg> resPMsgList, //
            S21ApiMessageIdMgr msgIdMgr, //
            ONBATCH_TYPE onBatchType
            // 2019/09/27 QC#53593 ADD START
            , String slsDt
            // 2019/09/27 QC#53593 ADD END
    ) {
        final String methodNm = "execute: xxRqstTpCd=[" + cpouBean.getRqstTpCd() + "]";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

        try {

//            S21ApiMessageIdMgr msgIdMgr = new S21ApiMessageIdMgr();
//            this.onBatchType = onBatchType;

            initializeResData(cpouBean, resPMsgList);

            NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(getClass(), methodNm);

            // 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
//                final String xxRqstTpCd = pMsg.xxRqstTpCd.getValue();
//            final String xxRqstTpCd = cpouBean.getRqstTpCd();
            // 2017/03/31 S21_NA#Review structure Lv.2 Mod End
//            final boolean isSaveRqst = NWZC150001CpouConstant.CPO_SAVE.equals(xxRqstTpCd);
//            final boolean isSubmitRqst = NWZC150001CpouConstant.CPO_SUBMIT.equals(xxRqstTpCd);
//            final boolean isCancelRqst = NWZC150001CpouConstant.CPO_CANCEL.equals(xxRqstTpCd);
//            final boolean isModifyRqst = NWZC150001CpouConstant.CPO_MODIFY.equals(xxRqstTpCd);
            // 20130307 Defect#785 S.Iidaka Start
            // final boolean wasDsPmtMethCC = wasDsPmtMethCC(pMsg); // For Performance QC#8166 Del
            // 20130307 Defect#785 S.Iidaka End

            // ===================================================================
            // validate requested [PMsg].
            // ===================================================================
            NWZC150001CpouValidCheck.validReqParams(pMsg, cpouBean, resPMsgList, msgIdMgr);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            NWZC150001CpouExistsCdInDbCheck.existsCdInDB(pMsg, cpouBean, resPMsgList, msgIdMgr, this.localCache);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // ===================================================================
            // mapping from [PMsg] to [NWZC150001Bean].
            // ===================================================================
//                final NWZC150001CpouBean cpoBean = toCpoBean(pMsg);
            NWZC150001CpouSetData.setDefaultData(cpouBean, pMsg, resPMsgList, localCache, msgIdMgr, onBatchType);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor
            // Plan Order] - START

//            // [CPO]
//            CPOTMsg cpoTMsg = new CPOTMsg();
//            setValue(cpoTMsg.glblCmpyCd, cpouBean.getGlblCmpyCd());
//            setValue(cpoTMsg.cpoOrdNum, cpouBean.getCpoOrdNum());
//            cpoTMsg = (CPOTMsg) findByKey(cpoTMsg);
//
//            // get original PAYER_CUST_CD.
//            if (cpoTMsg != null) {
//                origPayerCustCd = cpoTMsg.payerCustCd.getValue();
//            } else {
//                origPayerCustCd = "";
//            }
//
//            isPayerChanged = false;
//            if (isModifyRqst) {
//                isPayerChanged = !origPayerCustCd.equals(cpouBean.getPayerCustCd());
//                NWZC150001CpouLogWriter.writePerformanceProfilingLog(getClass(), " #isPayerChanged=[" + isPayerChanged + "] : payerCustCd=[" + origPayerCustCd + "] -> [" + cpouBean.getPayerCustCd() + "]");
//            }
            // ********** add by K.Tajima [Def# 1423(PROD) <273>Floor
            // Plan Order] - E N D

            // 20121130 M.Fuji WDS Solution#104,105 Pricing Start
            // ===================================================================
            // edit amount.
            // ===================================================================
            NWZC150001CpouEditAmount.getInstance().editAmountForWDS(cpouBean, resPMsgList, pMsg, this.localCache, msgIdMgr);
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }
            // 20121130 M.Fuji WDS Solution#104,105 Pricing End

            // ===================================================================
            // Other check
            // ===================================================================
            if (NWZC150001CpouOtherCheck.getInstance().doOtherCheck(cpouBean, pMsg, resPMsgList, msgIdMgr, this.localCache)) {
                return;
            }

            // ===================================================================
            // set [EXPD_SHIP_DT].
            // ===================================================================
            // 2019/09/27 QC#53593 MOD START
//            NWZC150001CpouSetExpdShipDt.setExpdShipDt(cpouBean, resPMsgList, this.localCache, onBatchType);
            NWZC150001CpouSetExpdShipDt.setExpdShipDt(cpouBean, resPMsgList, this.localCache, onBatchType, slsDt);
            // 2019/09/27 QC#53593 MOD END
            if (NWZC150001Common.hasError(resPMsgList, msgIdMgr)) {
                return;
            }

            // 2017/03/31 S21_NA#Review structure Lv.2 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpouBean.getOnlyValidationFlg())) {
                return;
            }
            // 2017/03/31 S21_NA#Review structure Lv.2 Add End

            // TODO Number Setting before call this object 2017/04/06 ---->
//                // ===================================================================
//                // numbering [CPO_ORD_NUM].
//                // ===================================================================
//                if (isSaveRqst || isSubmitRqst) {
//                    numberingCpoOrdNum(cpouBean, pMsg);
//                }
            // TODO Number Setting before call this object 2017/04/06 <----
        } finally {
//              super.updateMessage(pMsg, this.msgIdMgr);
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(getClass(), methodNm);
        }
    }

    /**
     * <pre>
     * The input data is posted in the output item.
     * When RequestType of Save and Detail is entire Cancel, RequestType of Header doesn't return OrderNumber.
     * </pre>
     * @param pMsg NWZC150001PMsg
     * @param resPMsgList List<NWZC150002PMsg>
     */
//    private static void initializeResData(NWZC150001PMsg pMsg, List<NWZC150002PMsg> resPMsgList) {
    private static void initializeResData(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList) {

        boolean mustRetCpoOrdNum = false;

        // 2017/04/04 S21_NA#Review structure Lv.2 Mod Start
        boolean isHdrSave = NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd());
//        if (NWZC150001CpouConstant.CPO_SAVE.equals(cpouBean.getRqstTpCd())) {
//            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//                final NWZC150001_APMsg linePMsg = pMsg.A.no(i);
//                if (NWZC150001CpouConstant.CPO_DTL_SAVE.equals(linePMsg.xxRqstTpCd_A1.getValue())) {
//                    mustRetCpoOrdNum = true;
//                    break;
//                }
//            }
//        } else {
//            mustRetCpoOrdNum = true;
//        }
//
//        // CPO_ORD_NUM
//        if (mustRetCpoOrdNum) {
//            cpouBean.setCpoOrdNum(cpouBean.getCpoOrdNum());
//        }
        // 2017/04/04 S21_NA#Review structure Lv.2 Mod End

        // List<NWZC150002PMsg>
        for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

            if (!isExistsLineMsg(resPMsgList, cpouDetailBean)) {
                final NWZC150002PMsg resPMsg = new NWZC150002PMsg();

                setValue(resPMsg.cpoDtlLineNum, cpouDetailBean.getCpoDtlLineNum());
                setValue(resPMsg.cpoDtlLineSubNum, cpouDetailBean.getCpoDtlLineSubNum());

                resPMsgList.add(resPMsg);
            }

            if (isHdrSave //
                    && NWZC150001CpouConstant.CPO_DTL_SAVE.equals(cpouDetailBean.getDtlRqstTpCd()) //
                    && !mustRetCpoOrdNum) {
                mustRetCpoOrdNum = true;
            }
        }

        if (mustRetCpoOrdNum && ZYPCommonFunc.hasValue(cpouBean.getCpoOrdNum())) {
            cpouBean.setCpoOrdNum_A1(cpouBean.getCpoOrdNum());
        }
    }

    private static boolean isExistsLineMsg(List<NWZC150002PMsg> resPMsgList, NWZC150001CpouDetailBean cpouDetailBean) {

        for (NWZC150002PMsg resPMsg : resPMsgList) {
            if (S21StringUtil.isEquals(resPMsg.cpoDtlLineNum.getValue(), cpouDetailBean.getCpoDtlLineNum()) //
                    && S21StringUtil.isEquals(resPMsg.cpoDtlLineSubNum.getValue(), cpouDetailBean.getCpoDtlLineSubNum())) {
                return true;
            }
        }
        return false;
    }
}
