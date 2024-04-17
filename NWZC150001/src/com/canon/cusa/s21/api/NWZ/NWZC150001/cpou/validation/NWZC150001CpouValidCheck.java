/**
 * <pre>
 * Error check CPO Update API
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2019/11/27   Fujitsu         K.Kato          Update          QC#52339
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.api.S21ApiTBLAccessor.findByCondition;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.MDSETMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001Common;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouHldBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * Error check CPO Update API
 * @author q05163
 *
 */
public class NWZC150001CpouValidCheck {

    /** Class Name */
    private static final String CLASS_NM = "NWZC150001CpouValidCheck";

    /** Shipped Shipping Status Codes */
    private static final Set<String> SHIPPED_SHPG_STS;

    static {
        SHIPPED_SHPG_STS = new HashSet<String>();
        SHIPPED_SHPG_STS.add(SHPG_STS.HARD_ALLOCATED);
        SHIPPED_SHPG_STS.add(SHPG_STS.S_OR_O_PRINTED);
        SHIPPED_SHPG_STS.add(SHPG_STS.PICKED);
        SHIPPED_SHPG_STS.add(SHPG_STS.PACKED);
        SHIPPED_SHPG_STS.add(SHPG_STS.STAGED);
        SHIPPED_SHPG_STS.add(SHPG_STS.INSHED);
        SHIPPED_SHPG_STS.add(SHPG_STS.SHIPPED);
        SHIPPED_SHPG_STS.add(SHPG_STS.P_OR_O_PRINTED);
        SHIPPED_SHPG_STS.add(SHPG_STS.ARRIVED);
        SHIPPED_SHPG_STS.add(SHPG_STS.N_INVOICE_READY);
        SHIPPED_SHPG_STS.add(SHPG_STS.INVOICED);
    }

    // 2019/11/27 QC#52339 Add Start
    /** Shipped Shipping Status Codes Intangible */
    private static final Set<String> SHIPPED_SHPG_STS_INTANGIBLE;

    static {
        SHIPPED_SHPG_STS_INTANGIBLE = new HashSet<String>();
        SHIPPED_SHPG_STS_INTANGIBLE.add(SHPG_STS.INVOICED);
    }
    // 2019/11/27 QC#52339 Add End

    /**
     * <pre>
     * Validation check for mandatory
     * </pre>
     * @param pMsg API Parameter
     * @param cpouBean CPOU Bean. must be set up from API Parameter
     * @param resPMsgList detail error message list
     * @param msgIdMgr message id manager
     */
    public static void validReqParams(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr) {
        final String methodNm = "validReqParams";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(CLASS_NM, methodNm);

        // 2017/04/04 S21_NA#Review structure Lv.2 Add Start
        if (isNovalidationCall(cpouBean)) {
            return;
        }
        // 2017/04/04 S21_NA#Review structure Lv.2 Add End
        try {

            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            if (pMsg.A.getValidCount() == 0) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0821E, pMsg);
//                return;
//            }
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End

            validReqParamsForCommon(pMsg, cpouBean, resPMsgList, msgIdMgr);
            validReqParamsForCpo(pMsg, cpouBean, msgIdMgr);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            validReqParamsForTrialLoanScrn(pMsg, resPMsgList);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Del
            validReqParamsForManualPricing(cpouBean, resPMsgList, msgIdMgr);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//            validReqParamsForDoubleSlip(pMsg, resPMsgList);
            // 2017/03/29 S21_NA#Review structure Lv.1 Del End
            // S21_NA#9278 Delete
            // validReqParamsForCustomerReferenceNumber(pMsg, resPMsgList);

            final String rqstTpCd = cpouBean.getRqstTpCd();
//            if (NWZC150001CpouConstant.CPO_MODIFY.equals(pMsg.xxRqstTpCd.getValue()) || NWZC150001CpouConstant.CPO_CANCEL.equals(pMsg.xxRqstTpCd.getValue())) { 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
            if (NWZC150001CpouConstant.CPO_MODIFY.equals(rqstTpCd) //
                    || NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd)) { // 2017/03/31 S21_NA#Review structure Lv.2 Mod End
                if (!chkShpgPlnSts(cpouBean)) {
                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0921E, pMsg);
                    return;
                }
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(CLASS_NM, methodNm);
        }
    }

    private static void validReqParamsForCommon(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr) {

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        // GLBL_CMPY_CD
//        if (!hasValue(pMsg.glblCmpyCd)) {
//            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0163E, pMsg);
//        }
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End

        // XX_RQST_TP_CD
//        if (!hasValue(pMsg.xxRqstTpCd)) {
        if (!hasValue(cpouBean.getRqstTpCd())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0157E, pMsg);
        }

        // CPO_ORD_TP_CD
//        if (!hasValue(pMsg.cpoOrdTpCd)) {
        if (!hasValue(cpouBean.getCpoOrdTpCd())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0097E, pMsg);
        }

        // CPO_SRC_TP_CD
//        if (!hasValue(pMsg.cpoSrcTpCd)) {
        if (!hasValue(cpouBean.getCpoSrcTpCd())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0098E, pMsg);
        }

        // SYS_SRC_CD
//        if (!hasValue(pMsg.sysSrcCd)) {
        if (!hasValue(cpouBean.getSysSrcCd())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0453E, pMsg);
        }

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        // SLS_DT
//        if (!hasValue(pMsg.slsDt)) {
//            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0346E, pMsg);
//        }
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End

//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
        boolean hasHdrShipToCd = hasValue(cpouBean.getAddShipToCustCd());

        int i = 0;
        for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

//            NWZC150001_APMsg linePMsg = pMsg.A.no(i);

            // XX_RQST_TP_CD
            if (!hasValue(cpouDetailBean.getDtlRqstTpCd())) {
                setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0164E, resPMsgList, i);
            }

            // MDSE_CD
            if (!hasValue(cpouDetailBean.getMdseCd())) {
                setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0035E, resPMsgList, i);
            }

            // CPO_DTL_LINE_NUM
            if (!hasValue(cpouDetailBean.getCpoDtlLineNum())) {
                setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0100E, resPMsgList, i);
            }

            // CPO_DTL_LINE_SUB_NUM
            if (!hasValue(cpouDetailBean.getCpoDtlLineSubNum())) {
                setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0101E, resPMsgList, i);
            }

            // ORD_QTY
            if (!hasValue(cpouDetailBean.getOrdQty())) {
                setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0041E, resPMsgList, i);
            }

            // ADD_SHIP_TO_CUST_CD
            if (!hasHdrShipToCd) {
                // SHIP_TO_CUST_CD
                if (!hasValue(cpouDetailBean.getShipToCustCd())) {
                    setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0201E, resPMsgList, i);
                }
            }
            i++;
        }

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start (Move into above loop)
//        // ADD_SHIP_TO_CUST_CD
//        if (!hasValue(cpouBean.getAddShipToCustCd())) {
//            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//                // SHIP_TO_CUST_CD
//                if (!hasValue(pMsg.A.no(i).shipToCustCd_A1)) {
//                    setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0201E, resPMsgList, i);
//                }
//            }
//        }
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End (Move into above loop)

        // 2017/03/31 S21_NA#Review structure Lv.2 Mod Start
//        // 1.2WDS add start -->
//        // Hold List
//        for (int i = 0; i < pMsg.holdList.getValidCount(); i++) {
//            // XX_RQST_TP_CD
//            if (!hasValue(pMsg.holdList.no(i).xxRqstTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1034E, pMsg);
//            }
//
//            // HLD_PK
//            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(pMsg.holdList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.holdList.no(i).hldPk)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//        }
        for (NWZC150001CpouHldBean cpouHldBean : cpouBean.getHldBeanList()) {
            // XX_RQST_TP_CD
            if (!hasValue(cpouHldBean.getXxRqstTpCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1034E, pMsg);
            }

            // HLD_PK
            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(cpouHldBean.getXxRqstTpCd()) //
                    && !hasValue(cpouHldBean.getHldPk())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
            }
        }
        // 2017/03/31 S21_NA#Review structure Lv.2 Mod End

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
        // CPO Header Contact Person
//        for (int i = 0; i < pMsg.cpoHeaderContactPersonList.getValidCount(); i++) {
//            // XX_RQST_TP_CD
//            if (!hasValue(pMsg.cpoHeaderContactPersonList.no(i).xxRqstTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1100E, pMsg);
//            }
//
//            // DS_CTAC_PSN_PK
//            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(pMsg.cpoHeaderContactPersonList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.cpoHeaderContactPersonList.no(i).dsCpoCtacPsnPk)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//
//            // CTAC_PSN_TP_CD
//            if (!hasValue(pMsg.cpoHeaderContactPersonList.no(i).ctacPsnTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//        }

        // CPO Detail Contact Person
//        for (int i = 0; i < pMsg.cpoDetailContactPersonList.getValidCount(); i++) {
//            // XX_RQST_TP_CD
//            if (!hasValue(pMsg.cpoDetailContactPersonList.no(i).xxRqstTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1100E, pMsg);
//            }
//
//            // DS_CTAC_PSN_PK
//            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(pMsg.cpoDetailContactPersonList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.cpoDetailContactPersonList.no(i).dsCpoCtacPsnPk)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//
//            // CTAC_PSN_TP_CD
//            if (!hasValue(pMsg.cpoDetailContactPersonList.no(i).ctacPsnTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//        }

//        // Promotion
//        for (int i = 0; i < pMsg.mdsePromoList.getValidCount(); i++) {
//            // XX_RQST_TP_CD
//            if (!hasValue(pMsg.mdsePromoList.no(i).xxRqstTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1033E, pMsg);
//            }
//
//            // MDSE_PRMO_PK
//            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(pMsg.mdsePromoList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.mdsePromoList.no(i).mdsePrmoPk)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//
//            // LGCY_PRMO_TP_CD
//            if (!hasValue(pMsg.mdsePromoList.no(i).lgcyPrmoTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//
//            // MDSE_PRMO_FIRST_ORG_CD
//            if (!hasValue(pMsg.mdsePromoList.no(i).prcMgtGrpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//
//            // LGCY_PRMO_NUM
//            if (!hasValue(pMsg.mdsePromoList.no(i).lgcyPrmoNum)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//        }
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End

        // START DELETE M.Fuji [OM040]
        // DS_MDSE_OTH_SLS_REP
        //        for (int i = 0; i < pMsg.otherRepList.getValidCount(); i++) {
        //            // XX_RQST_TP_CD
        //            if (!hasValue(pMsg.otherRepList.no(i).xxRqstTpCd)) {
        //                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1032E, pMsg);
        //            }
        //
        //            // DS_MDSE_OTH_SLS_REP_PK
        //            if (!REQUEST_TYPE_NEW.equals(pMsg.otherRepList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.otherRepList.no(i).dsMdseOthSlsRepPk)) {
        //                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
        //            }
        //        }
        // END DELETE M.Fuji [OM040]

        // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//        // SO_SER
//        for (int i = 0; i < pMsg.soSerialList.getValidCount(); i++) {
//            // XX_RQST_TP_CD
//            if (!hasValue(pMsg.soSerialList.no(i).xxRqstTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1099E, pMsg);
//            }
//
//            // SO_SER_PK
//            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(pMsg.soSerialList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.soSerialList.no(i).soSerPk)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//        }

//        // 20130118 Defect#111 M.Fuji Start
//        // DS_CPO_PICK_UP_MACH
//        for (int i = 0; i < pMsg.cpoPickUpMachineList.getValidCount(); i++) {
//            // XX_RQST_TP_CD
//            if (!hasValue(pMsg.cpoPickUpMachineList.no(i).xxRqstTpCd)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM1099E, pMsg);
//            }
//
//            // DS_MDSE_OTH_SLS_REP_PK
//            if (!NWZC150001CpouConstant.REQUEST_TYPE_NEW.equals(pMsg.cpoPickUpMachineList.no(i).xxRqstTpCd.getValue()) && !hasValue(pMsg.cpoPickUpMachineList.no(i).dsCpoPickUpMachPk)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0841E, pMsg);
//            }
//        }
        // 20130118 Defect#111 M.Fuji End
        // 2017/03/31 S21_NA#Review structure Lv.2 Del End
        // 1.2WDS add end <--
    }

    //  private void validReqParamsForCpo(NWZC150001PMsg pMsg) {
    private static void validReqParamsForCpo(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, S21ApiMessageIdMgr msgIdMgr) {

        // XX_RQST_TP_CD
        if (!NWZC150001CpouConstant.CPO_MODIFY.equals(cpouBean.getRqstTpCd())) {
            return;
        }

        // CPO_ORD_NUM
        if (!hasValue(cpouBean.getCpoOrdNum())) {
            msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0002E, pMsg);
        }
    }

    private static void validReqParamsForManualPricing(NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr) {

        int i = 0;
        for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

            // MAN_PRC_FLG
            if (!ZYPConstant.FLG_ON_Y.equals(cpouDetailBean.getManPrcFlg())) {
                continue;
            }

            // CCY_CD
            if (!hasValue(cpouDetailBean.getCcyCd())) {
                setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0703E, resPMsgList, i);
            }
            i++;
        }
    }

//  private boolean chkShpgPlnSts(NWZC150001PMsg pMsg) {
    private static boolean chkShpgPlnSts(NWZC150001CpouBean cpouBean) {

        Map<String, BigDecimal> ordQtyMap = new LinkedHashMap<String, BigDecimal>();

        for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDetailBean.getCpoDtlLineSubNum())) {

                SHPG_PLNTMsgArray shpgPlnTMsgArray = getSHPG_PLNTMsgArray(cpouBean, cpouDetailBean, SHPG_STS.VALIDATED);

                if (shpgPlnTMsgArray.getValidCount() == 0) {

                    shpgPlnTMsgArray = getSHPG_PLNTMsgArray(cpouBean, cpouDetailBean, SHPG_STS.S_OR_O_CANCELLED);

                    if (shpgPlnTMsgArray.getValidCount() == 0) {

                        shpgPlnTMsgArray = getSHPG_PLNTMsgArray(cpouBean, cpouDetailBean, SHPG_STS.P_OR_O_CANCELLED);

                        if (shpgPlnTMsgArray.getValidCount() == 0) {
                            return true;
                        }
                    }
                }

                SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
                reqShpgPlnTMsg.setSQLID("030");
                reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                reqShpgPlnTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
                reqShpgPlnTMsg.setConditionValue("trxHdrNum01", cpouBean.getCpoOrdNum());
                reqShpgPlnTMsg.setConditionValue("trxLineNum01", cpouDetailBean.getCpoDtlLineNum());
                shpgPlnTMsgArray = (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);

                for (int j = 0; j < shpgPlnTMsgArray.getValidCount(); j++) {

                    // 2019/11/27 QC#52339 Add Start
                    MDSETMsg mdseTMsg = new MDSETMsg();
                    if (shpgPlnTMsgArray.getValidCount() > 0) {
                        mdseTMsg = NWZC150001Common.getMdse(shpgPlnTMsgArray.no(j).glblCmpyCd.getValue(), shpgPlnTMsgArray.no(j).mdseCd.getValue());
                    }
                    // 2019/11/27 QC#52339 Add End

                    // 2019/11/27 QC#52339 Mod Start
                    //if (SHIPPED_SHPG_STS.contains(shpgPlnTMsgArray.no(j).shpgStsCd.getValue(), mdseTMsg.invtyCtrlFlg.getValue())) {
                    if (NWZC150001CpouValidCheck.isShippedSts(shpgPlnTMsgArray.no(j).shpgStsCd.getValue(), mdseTMsg.invtyCtrlFlg.getValue())) {
                    // 2019/11/27 QC#52339 Mod End
                        if (!SHPG_STS.HARD_ALLOCATED.equals(shpgPlnTMsgArray.no(j).shpgStsCd.getValue())) {

                            StringBuilder dtlLineSb = new StringBuilder();
                            dtlLineSb.append(shpgPlnTMsgArray.no(j).trxLineNum.getValue());
                            dtlLineSb.append(shpgPlnTMsgArray.no(j).trxLineSubNum.getValue());
                            String dtlLine = dtlLineSb.toString();
                            BigDecimal ordQty = shpgPlnTMsgArray.no(j).ordQty.getValue();

                            if (ordQtyMap.containsKey(dtlLine)) {
                                ordQtyMap.put(dtlLine, ordQtyMap.get(dtlLine).add(ordQty));
                            } else {
                                ordQtyMap.put(dtlLine, ordQty);
                            }
                        }
                    }
                }
            }
        }

        for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

            if (NWZC150001CpouConstant.SET_LINE_SUB_NUM.equals(cpouDetailBean.getCpoDtlLineSubNum())) {

                CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
                reqCpoDtlTMsg.setSQLID("005");
                reqCpoDtlTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
                reqCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpouBean.getCpoOrdNum());
                reqCpoDtlTMsg.setConditionValue("cpoDtlLineNum01", cpouDetailBean.getCpoDtlLineNum());
                reqCpoDtlTMsg.setConditionValue("cpoDtlLineSubNum01", cpouDetailBean.getCpoDtlLineSubNum());
                CPO_DTLTMsgArray cpoDtlTMsgArray = (CPO_DTLTMsgArray) findByCondition(reqCpoDtlTMsg);

                for (int j = 0; j < cpoDtlTMsgArray.getValidCount(); j++) {

                    StringBuilder dtlLineSb = new StringBuilder();
                    dtlLineSb.append(cpoDtlTMsgArray.no(j).cpoDtlLineNum.getValue());
                    dtlLineSb.append(cpoDtlTMsgArray.no(j).cpoDtlLineSubNum.getValue());
                    String dtlLine = dtlLineSb.toString();

                    if (ordQtyMap.containsKey(dtlLine)) {

                        BigDecimal ordQty = cpoDtlTMsgArray.no(j).ordQty.getValue();
                        BigDecimal spOrdQty = ordQtyMap.get(dtlLine);

                        if (ordQty.compareTo(spOrdQty) == 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

//  private SHPG_PLNTMsgArray getSHPG_PLNTMsgArray(NWZC150001PMsg pMsg, NWZC150001_APMsg linePMsg, String status) {
    private static SHPG_PLNTMsgArray getSHPG_PLNTMsgArray(NWZC150001CpouBean cpouBean, NWZC150001CpouDetailBean cpouDetailBean, String status) {

        final SHPG_PLNTMsg reqShpgPlnTMsg = new SHPG_PLNTMsg();
        reqShpgPlnTMsg.setSQLID("006");
        reqShpgPlnTMsg.setConditionValue("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        reqShpgPlnTMsg.setConditionValue("trxSrcTpCd01", TRX_SRC_TP.WHOLE_SALES);
        reqShpgPlnTMsg.setConditionValue("trxHdrNum01", cpouBean.getCpoOrdNum());
        reqShpgPlnTMsg.setConditionValue("trxLineNum01", cpouDetailBean.getCpoDtlLineNum());
        reqShpgPlnTMsg.setConditionValue("trxLineSubNum01", cpouDetailBean.getCpoDtlLineSubNum());
        reqShpgPlnTMsg.setConditionValue("shpgStsCd01", status);

        return (SHPG_PLNTMsgArray) findByCondition(reqShpgPlnTMsg);
    }

    /**
     * The error code is stored in the CPO Detail area.
     * @param errMsgId String(Message code)
     * @param resPMsgList List<NWZC150002PMsg>
     * @param line int(No of corresponding List<NWZC150002PMsg >)
     */
    public static void setErrMsgIdForCpoDtl(String errMsgId, List<NWZC150002PMsg> resPMsgList, int line) {

        resPMsgList.get(line).xxMsgIdList.setValidCount(resPMsgList.get(line).xxMsgIdList.getValidCount() + 1);
        resPMsgList.get(line).xxMsgIdList.no(resPMsgList.get(line).xxMsgIdList.getValidCount() - 1).xxMsgId.setValue(errMsgId);
    }

    /**
     * Shipping Plan Status judgment (It is not possible to cancel).
     * 
     * <pre>
     * True is returned for status not to be able to cancel Shipping Status Code of the argument.
     * </pre>
     * @param shpgStsCd String
     * @return True : It is not possible to cancel. ／False : It is
     * possible to cancel.
     */
    // 2019/11/27 QC#52339 Mod Start 
    //public static boolean isShippedSts(String shpgStsCd) {
    public static boolean isShippedSts(String shpgStsCd, String invtyCtrlFlg) {
    // 2019/11/27 QC#52339 Mod End 

        // 2019/11/27 QC#52339 Mod Start 
        if (!ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
            return SHIPPED_SHPG_STS_INTANGIBLE.contains(shpgStsCd);
        } else {
            return SHIPPED_SHPG_STS.contains(shpgStsCd);
        }
        // 2019/11/27 QC#52339 Mod End 
    }

    // 2017/03/31 S21_NA#Review structure Lv.2 Add Start
    public static boolean isNovalidationCall(NWZC150001CpouBean cpouBean) {
        return S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, cpouBean.getNoValidationFlg());
    }
    // 2017/03/31 S21_NA#Review structure Lv.2 Add End
}
