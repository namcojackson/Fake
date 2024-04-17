/**
 * <pre>
 * Error check CPO Update API exists CdInDb
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 * 2017/06/27   Fujitsu         S.Takami        Update          S21_NA#19589
 * 2017/10/11   Fujitsu         S.Takami        Update          S21_NA#21300
 * 2017/10/30   Fujitsu         R.Nakamura      Update          S21_NA#22140
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.validation;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor.findByKey;

import java.util.List;

import business.db.CCYTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.COA_PRODTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_ORD_TPTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.CTRYTMsg;
import business.db.FRT_CHRG_METHTMsg;
import business.db.FRT_CHRG_TOTMsg;
import business.db.FRT_CONDTMsg;
import business.db.MDSETMsg;
import business.db.ORD_FUFL_LVLTMsg;
import business.db.PKG_UOMTMsg;
import business.db.S21_ORGTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.STK_STSTMsg;
import business.db.STTMsg;
import business.db.SYS_SRCTMsg;
import business.db.VAR_CHAR_CONSTTMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150002PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouFindCondition;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.bean.NWZC150001CpouDetailBean;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.constants.NWZC150001CpouConstant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpou.logWriter.NWZC150001CpouLogWriter;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Export;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;

public class NWZC150001CpouExistsCdInDbCheck {

    /** Class Name */
    private static final String CLASS_NM = "NWZC150001CpouExistsCdInDbCheck";

//    public NWZC150001CpouExistsCdInDbCheck() {
//        
//    }

    public static void existsCdInDB(NWZC150001PMsg pMsg, NWZC150001CpouBean cpouBean, List<NWZC150002PMsg> resPMsgList, S21ApiMessageIdMgr msgIdMgr, NWZC150001CpouLocalCache localCache) {
        final String methodNm = "existsCdInDB";
        NWZC150001CpouLogWriter.writePerformanceProfilingLogStart(CLASS_NM, methodNm);

        // 2017/04/04 S21_NA#Review structure Lv.2 Add Start
        if (NWZC150001CpouValidCheck.isNovalidationCall(cpouBean)) {
            return;
        }
        final boolean isCancelRqst = NWZC150001CpouConstant.CPO_CANCEL.equals(cpouBean.getRqstTpCd());
        if (isCancelRqst) {
            return;
        }
        // 2017/04/04 S21_NA#Review structure Lv.2 Add End
        try {

            final String glblCmpyCd = cpouBean.getGlblCmpyCd();

            // --------------------------------------------------
            // Header
            // --------------------------------------------------
            // CPO_ORD_TP_CD
            if (!existsCpoOrdTp(glblCmpyCd, cpouBean.getCpoOrdTpCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0109E, pMsg);
            }

            // CPO_SRC_TP_CD
            if (!existsCpoSrcTp(glblCmpyCd, cpouBean.getCpoSrcTpCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0110E, pMsg);
            }

            // ORD_FUFL_LVL_CD
            if (!existsOrdFuflLvl(glblCmpyCd, cpouBean.getOrdFuflLvlCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0111E, pMsg);
            }

            // BILL_TO_CUST_CD
            if (!existsBillTo(cpouBean, localCache)) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0112E, pMsg);
            }

            // SELL_TO_CUST_CD
            if (!existsSellTo(cpouBean, localCache)) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0024E, pMsg);
            }

            // 2017/03/31 S21_NA#Review structure Lv.2 Del Start
//            // PAYER_CUST_CD
//            if (!existsPayer(pMsg)) {
//                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0025E, pMsg);
//            }
            // 2017/03/31 S21_NA#Review structure Lv.2 Del End

            // ADD_SHIP_TO_CUST_CD
            if (!existsShipTo(glblCmpyCd, cpouBean.getAddShipToCustCd(), localCache)) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0114E, pMsg);
            }

            // ADD_SHIP_TO_CTRY_CD
            if (!existsShipToCtry(glblCmpyCd, cpouBean.getAddShipToCtryCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0117E, pMsg);
            }

            if (!isExportForCtry(glblCmpyCd, cpouBean.getAddShipToCtryCd(), localCache)) {

                //START DELETE S.Yoshida [WDS Defect #1489]
                //                // ADD_SHIP_TO_POST_CD
                //                if (!existsShipToPost(glblCmpyCd, pMsg.addShipToPostCd.getValue())) {
                //                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0116E, pMsg);
                //                }
                //END   DELETE S.Yoshida [WDS Defect #1489]

                // ADD_SHIP_TO_ST_CD
                if (!existsShipToSt(glblCmpyCd, cpouBean.getAddShipToStCd())) {
                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0115E, pMsg);
                }
            }

            // ADD_PMT_TERM_CASH_DISC_CD
            if (!existsPmtTermCashDisc(glblCmpyCd, cpouBean.getAddPmtTermCashDiscCd(), localCache)) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0925E, pMsg);
            }

            // SYS_SRC_CD
            if (!existsSysSrc(glblCmpyCd, cpouBean.getSysSrcCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0704E, pMsg);
            }

            // COA_BR_CD
            if (!existsCoaBr(glblCmpyCd, cpouBean.getCoaBrCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0890E, pMsg);
            }

            // COA_CH_CD
            if (!existsCoaCh(glblCmpyCd, cpouBean.getCoaChCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0891E, pMsg);
            }

            // COA_CC_CD
            if (!existsCoaCc(glblCmpyCd, cpouBean.getCoaCcCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0892E, pMsg);
            }

            // COA_PROD_CD
            if (!existsCoaProd(glblCmpyCd, cpouBean.getCoaProdCd())) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0893E, pMsg);
            }

            // COA_ACCT_CD
            if (!existsCoaAcct(glblCmpyCd, cpouBean.getCoaAcctCd(), localCache)) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0894E, pMsg);
            }

            // COA_PROJ_CD
            if (!existsCoaProj(glblCmpyCd, cpouBean.getCoaProjCd(), localCache)) {
                msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0895E, pMsg);
            }

            // S21_NA#10321-27 Del Start
//            // DSLP_INFO_FLG
//            if (ZYPConstant.FLG_ON_Y.equals(pMsg.dslpInfoFlg.getValue())) {
//
//                // ADD_DSLP_SELL_TO_CUST_CD
//                if (!existsDslpSellTo(pMsg)) {
//                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0119E, pMsg);
//                }
//
//                // ADD_DSLP_PMT_TERM_CASH_DISC_CD
//                if (!existsPmtTermCashDisc(glblCmpyCd, pMsg.addDslpPmtTermDiscCd.getValue())) {
//                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0926E, pMsg);
//                }
//
//                // ADD_DSLP_FRT_CHRG_TO_CD
//                if (!existsFrtChrgTo(glblCmpyCd, pMsg.addDslpFrtChrgToCd.getValue())) {
//                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0122E, pMsg);
//                }
//
//                // ADD_DSLP_FRT_CHRG_METH_CD
//                if (!existsFrtChrgMeth(glblCmpyCd, pMsg.addDslpFrtChrgMethCd.getValue())) {
//                    msgIdMgr.addXxMsgId(NWZC150001CpouConstant.NWZM0123E, pMsg);
//                }
//            }
            // S21_NA#10321-27 Del End

            // --------------------------------------------------
            // Detail
            // --------------------------------------------------
//            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            int i = 0;
            for (NWZC150001CpouDetailBean cpouDetailBean : cpouBean.getDtlBeanList()) {

//                NWZC150001_APMsg linePMsg = pMsg.A.no(i);

                // CPO_ORD_TP_CD
                if (!existsCpoOrdTp(glblCmpyCd, cpouDetailBean.getCpoOrdTpCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0124E, resPMsgList, i);
                }

                // MDSE_CD
                if (!existsMdse(cpouBean, cpouDetailBean.getMdseCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0036E, resPMsgList, i);
                }

                // 2016/02/18 S21_NA#2336 Del Start
                // ORIG_MDSE_CD
//                if (!existsMdse(pMsg, linePMsg.origMdseCd.getValue())) {
//                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0125E, resPMsgList, i);
//                }
                // 2016/02/18 S21_NA#2336 Del End

                // SHIP_TO_CUST_CD
                if (!existsShipTo(glblCmpyCd, cpouDetailBean.getShipToCustCd(), localCache)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0023E, resPMsgList, i);
                }

                // SHIP_TO_CTRY_CD
                if (!existsShipToCtry(glblCmpyCd, cpouDetailBean.getShipToCtryCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0090E, resPMsgList, i);
                }

                if (!isExportForCtry(glblCmpyCd, cpouDetailBean.getShipToCtryCd(), localCache)) {

                    //START DELETE S.Yoshida [WDS Defect #1489]
                    //                    // SHIP_TO_POST_CD
                    //                    if (!existsShipToPost(glblCmpyCd, linePMsg.shipToPostCd.getValue())) {
                    //                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0085E, resPMsgList, i);
                    //                    }
                    //END   DELETE S.Yoshida [WDS Defect #1489]

                    // SHIP_TO_ST_CD
                    if (!existsShipToSt(glblCmpyCd, cpouDetailBean.getShipToStCd())) {
                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0088E, resPMsgList, i);
                    }
                }

                // CCY_CD
                if (!existsCcy(glblCmpyCd, cpouDetailBean.getCcyCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0062E, resPMsgList, i);
                }

                // STK_STS_CD
                if (!existsStkSts(glblCmpyCd, cpouDetailBean.getStkStsCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0126E, resPMsgList, i);
                }

                // PMT_TERM_CASH_DISC_CD
                if (!existsPmtTermCashDisc(glblCmpyCd, cpouDetailBean.getPmtTermCashDiscCd(), localCache)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0927E, resPMsgList, i);
                }

                // SHPG_SVC_LVL_CD
                if (!existsShpgSvcLvl(glblCmpyCd, cpouDetailBean.getShpgSvcLvlCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0127E, resPMsgList, i);
                }

                // FRT_CHRG_TO_CD
                if (!existsFrtChrgTo(glblCmpyCd, cpouDetailBean.getFrtChrgToCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0058E, resPMsgList, i);
                }

                // FRT_CHRG_METH_CD
                if (!existsFrtChrgMeth(glblCmpyCd, cpouDetailBean.getFrtChrgMethCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0060E, resPMsgList, i);
                }

                // SLS_REP_OR_SLS_TEAM_TOC_CD
                if (!existsToc(glblCmpyCd, cpouDetailBean.getSlsRepOrSlsTeamTocCd(), localCache)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0054E, resPMsgList, i);
                }

                // CUST_UOM_CD
                if (!existsCustUom(glblCmpyCd, cpouDetailBean.getCustUomCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0128E, resPMsgList, i);
                }

                // CARR_CD
                if (!existsCarr(glblCmpyCd, cpouDetailBean.getCarrCd(), localCache)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0067E, resPMsgList, i);
                }

                // COA_ACCT_CD
                if (!existsCoaAcct(glblCmpyCd, cpouDetailBean.getCoaAcctCd(), localCache)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0069E, resPMsgList, i);
                }

                // COA_PROJ_CD
                if (!existsCoaProj(glblCmpyCd, cpouDetailBean.getCoaProjCd(), localCache)) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0134E, resPMsgList, i);
                }

                // S21_NA#10321-27 Del Start
//                // DSLP_INFO_FLG
//                if (ZYPConstant.FLG_ON_Y.equals(linePMsg.dslpInfoFlg.getValue())) {
//
//                    // DSLP_PMT_TERM_CASH_DISC_CD
//                    if (!existsPmtTermCashDisc(glblCmpyCd, linePMsg.dslpPmtTermCashDiscCd.getValue())) {
//                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0928E, resPMsgList, i);
//                    }
//
//                    // DSLP_FRT_CHRG_TO_CD
//                    if (!existsFrtChrgTo(glblCmpyCd, linePMsg.dslpFrtChrgToCd.getValue())) {
//                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0131E, resPMsgList, i);
//                    }
//
//                    // DSLP_FRT_CHRG_METH_CD
//                    if (!existsFrtChrgMeth(glblCmpyCd, linePMsg.dslpFrtChrgMethCd.getValue())) {
//                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0132E, resPMsgList, i);
//                    }
//
//                    // DSLP_CCY_CD
//                    if (!existsCcy(glblCmpyCd, linePMsg.dslpCcyCd.getValue())) {
//                        NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM0133E, resPMsgList, i);
//                    }
//                }
                // S21_NA#10321-27 Del End

                // START ADD S.Yamamoto [OM003]
                // FRT_COND_CD
                if (!existsFrtCond(glblCmpyCd, cpouDetailBean.getFrtCondCd())) {
                    NWZC150001CpouValidCheck.setErrMsgIdForCpoDtl(NWZC150001CpouConstant.NWZM1264E, resPMsgList, i);
                }
                // END   ADD S.Yamamoto [OM003]
                i++;
            }

        } finally {
            NWZC150001CpouLogWriter.writePerformanceProfilingLogEnd(CLASS_NM, methodNm);
        }
    }

    private static boolean existsCpoOrdTp(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final CPO_ORD_TPTMsg tMsg = new CPO_ORD_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cpoOrdTpCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCpoSrcTp(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final CPO_SRC_TPTMsg tMsg = new CPO_SRC_TPTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cpoSrcTpCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsOrdFuflLvl(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final ORD_FUFL_LVLTMsg tMsg = new ORD_FUFL_LVLTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ordFuflLvlCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsBillTo(NWZC150001CpouBean cpouBean, NWZC150001CpouLocalCache localCache) {

        // BILL_TO_CUST_CD
        if (!hasValue(cpouBean.getBillToCustCd())) {
            return true;
        }

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("024");
        fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        fc.addCondition("billToCustCd01", cpouBean.getBillToCustCd());
        fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return localCache.billToCustCache.getTMsgArray(fc).getValidCount() > 0;
    }

    private static boolean existsSellTo(NWZC150001CpouBean cpouBean, NWZC150001CpouLocalCache localCache) {

        // SELL_TO_CUST_CD
        if (!hasValue(cpouBean.getSellToCustCd())) {
            return true;
        }

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("008");
        fc.addCondition("glblCmpyCd01", cpouBean.getGlblCmpyCd());
        fc.addCondition("sellToCustCd01", cpouBean.getSellToCustCd());
        fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return localCache.sellToCustCache.getTMsgArray(fc).getValidCount() > 0;
    }

    private static boolean existsShipTo(String glblCmpyCd, String shipToCustCd, NWZC150001CpouLocalCache localCache) {

        if (!hasValue(shipToCustCd)) {
            return true;
        }

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("010");
        fc.addCondition("glblCmpyCd01", glblCmpyCd);
        fc.addCondition("shipToCustCd01", shipToCustCd);
        fc.addCondition("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return localCache.shipToCustCache.getTMsgArray(fc).getValidCount() > 0;
    }

    private static boolean existsShipToCtry(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final CTRYTMsg tMsg = new CTRYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ctryCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    public static boolean isExportForCtry(String glblCmpyCd, String shipToCtryCd, NWZC150001CpouLocalCache localCache) {

        // cache
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(shipToCtryCd);

        final String cacheKey = cacheKeySb.toString();

        Boolean checkRes = localCache.exportForCtryCheckResultCache.get(cacheKey);
        if (checkRes == null) {
            checkRes = NWXC001001Export.isExportForCtry(glblCmpyCd, shipToCtryCd);
            localCache.exportForCtryCheckResultCache.put(cacheKey, checkRes);
        }
        return checkRes;
    }

    public static boolean existsShipToSt(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final STTMsg tMsg = new STTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.stCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCcy(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final CCYTMsg tMsg = new CCYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ccyCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsStkSts(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final STK_STSTMsg tMsg = new STK_STSTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.stkStsCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsPmtTermCashDisc(String glblCmpyCd, String payTermCashDiscCd, NWZC150001CpouLocalCache localCache) {

        if (!hasValue(payTermCashDiscCd)) {
            return true;
        }

        return localCache.pmtTermCashDiscCache.getTMsgByKey(glblCmpyCd, payTermCashDiscCd) != null;
    }

    private static boolean existsShpgSvcLvl(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final SHPG_SVC_LVLTMsg tMsg = new SHPG_SVC_LVLTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.shpgSvcLvlCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsFrtChrgTo(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final FRT_CHRG_TOTMsg tMsg = new FRT_CHRG_TOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.frtChrgToCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsFrtChrgMeth(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final FRT_CHRG_METHTMsg tMsg = new FRT_CHRG_METHTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.frtChrgMethCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsToc(String glblCmpyCd, String tocCd, NWZC150001CpouLocalCache localCache) {

        if (!hasValue(tocCd)) {
            return true;
        }

        return localCache.tocCache.getTMsgByKey(glblCmpyCd, tocCd) != null;
    }

    private static boolean existsCustUom(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final PKG_UOMTMsg tMsg = new PKG_UOMTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.pkgUomCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCarr(String glblCmpyCd, String carrCd, NWZC150001CpouLocalCache localCache) {

        if (!hasValue(carrCd)) {
            return true;
        }

        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("003");
        fc.addCondition("glblCmpyCd01", glblCmpyCd);
        fc.addCondition("carrCd01", carrCd);

        return localCache.otbdCarrVCache.getTMsgArray(fc).getValidCount() > 0;
    }

    private static boolean existsSysSrc(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.sysSrcCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCoaBr(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final COA_BRTMsg tMsg = new COA_BRTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.coaBrCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCoaCh(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final COA_CHTMsg tMsg = new COA_CHTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.coaChCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCoaCc(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final COA_CCTMsg tMsg = new COA_CCTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.coaCcCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCoaProd(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final COA_PRODTMsg tMsg = new COA_PRODTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.coaProdCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    private static boolean existsCoaAcct(String glblCmpyCd, String coaAcctCd, NWZC150001CpouLocalCache localCache) {

        if (!hasValue(coaAcctCd)) {
            return true;
        }

        // 2017/06/27 S21_NA#19589 Mod Start
//        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("001");
        final NWZC150001CpouFindCondition fc = new NWZC150001CpouFindCondition("801");
        // 2017/06/27 S21_NA#19589 Mod End

        fc.addCondition("glblCmpyCd01", glblCmpyCd);
        fc.addCondition("coaAcctCd01", coaAcctCd);

        return localCache.coaAcctVCache.getTMsgArray(fc).getValidCount() > 0;
    }

    private static boolean existsCoaProj(String glblCmpyCd, String coaProjCd, NWZC150001CpouLocalCache localCache) {

        if (!hasValue(coaProjCd)) {
            return true;
        }

        return localCache.coaProjCache.getTMsgByKey(glblCmpyCd, coaProjCd) != null;
    }

    // START ADD S.Yamamoto [OM003]
    private static boolean existsFrtCond(String glblCmpyCd, String cd) {

        if (!hasValue(cd)) {
            return true;
        }

        final FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.frtCondCd.setValue(cd);

        return NWZC150001CpouLocalCache.findByKeyWithCache(tMsg) != null;
    }

    //  private boolean existsMdse(NWZC150001PMsg pMsg, String mdseCd) {
    private static boolean existsMdse(NWZC150001CpouBean cpouBean, String mdseCd) {

        if (!hasValue(mdseCd)) {
            return true;
        }

        // Mod Start 2017/10/30 QC#22140
//        return getMdse(cpouBean.getRqstTpCd(), cpouBean.getGlblCmpyCd(), mdseCd) != null;
        return getMdse(cpouBean.getRqstTpCd(), cpouBean.getGlblCmpyCd(), mdseCd, cpouBean.getCpoSrcTpCd()) != null;
        // Mod End 2017/10/30 QC#22140
    }

    /**
     * Merchandise information acquisition processing
     * 
     * <pre>
     * The commodity master is acquired.
     * </pre>
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    public static MDSETMsg getMdse(String rqstTpCd, String glblCmpyCd, String mdseCd) {

        // mdseCd = getOrdTakeMdse(glblCmpyCd, mdseCd);
        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg != null) {
            final String rgtnStsCd = mdseTMsg.rgtnStsCd.getValue();
            if (NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd) && (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd) || RGTN_STS.TERMINATED.equals(rgtnStsCd))) {
                return mdseTMsg;
            } else if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                return mdseTMsg;
            }
        }
        return null;
    }

    // Add Start 2017/10/30 QC#22140
    /**
     * Merchandise information acquisition processing
     * 
     * <pre>
     * The commodity master is acquired.
     * </pre>
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param cpoSrcTpCd String
     * @return MDSETMsg
     */
    public static MDSETMsg getMdse(String rqstTpCd, String glblCmpyCd, String mdseCd, String cpoSrcTpCd) {

        final MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        if (mdseTMsg != null) {
            final String rgtnStsCd = mdseTMsg.rgtnStsCd.getValue();
            if (NWZC150001CpouConstant.CPO_CANCEL.equals(rqstTpCd) && (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd) || RGTN_STS.TERMINATED.equals(rgtnStsCd))) {
                return mdseTMsg;
            } else if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                return mdseTMsg;
            } else if (CPO_SRC_TP.CREDIT.equals(cpoSrcTpCd) || CPO_SRC_TP.REBILL.equals(cpoSrcTpCd)) {
                return mdseTMsg;
            }
        }
        return null;
    }
    // Add End 2017/10/30 QC#22140

    /**
     * <pre>
     * Merchandise Information acquisition processing
     * </pre>
     * @param glblCmpyCd global company code
     * @param mdseCd Merchandise Code (Item COde)
     * @return MDSETMsg
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    /**
     * <pre>
     * Get Data from VAR_CHAR_CONST
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param varCharConstNm Varchar Const Name
     * @return Varchar Const Value
     */
    public static String getVarCharConstValue(String glblCmpyCd, String varCharConstNm) {

        VAR_CHAR_CONSTTMsg varCharConstTMsg = new VAR_CHAR_CONSTTMsg();
        setValue(varCharConstTMsg.glblCmpyCd, glblCmpyCd);
        setValue(varCharConstTMsg.varCharConstNm, varCharConstNm);

        varCharConstTMsg = (VAR_CHAR_CONSTTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(varCharConstTMsg);

        if (varCharConstTMsg == null) {
            return "";
        } else {
            return varCharConstTMsg.varCharConstVal.getValue();
        }
    }


    /**
     * Branch Code,Channel Code,Cost Center Code,Product Code
     * acquisition
     * 
     * <pre>
     * if cache doesn't have S21_ORG infomation, find [S21_ORG] table it.
     * </pre>
     * @param glblCmpyCd String
     * @param tocCd String
     * @return S21_ORG data
     */
    public static S21_ORGTMsg getS21OrgData(String glblCmpyCd, String tocCd) {

        if (!hasValue(tocCd)) {
            return null;
        }

        final S21_ORGTMsg s21orgMsg = new S21_ORGTMsg();
        s21orgMsg.glblCmpyCd.setValue(glblCmpyCd);
        s21orgMsg.tocCd.setValue(tocCd);

        return (S21_ORGTMsg) NWZC150001CpouLocalCache.findByKeyWithCache(s21orgMsg);
    }
    /**
     * CPO Detail retrieval
     * 
     * <pre>
     * CPO Detail is retrieved, and the acquisition result is returned.
     * ＜Search condition＞
     * ・Global Company Code
     * ・Order Line Number
     * ・Order Line Sub Number
     * </pre>
     * @param cpoBean NWZC150001CpouBean
     * @param cpoDtlBean NWZC150001CpouDetailBean
     * @return Acquisition result（CPO_DTLTMsg）
     */
    public static CPO_DTLTMsg getCpoDtlByPK(NWZC150001CpouBean cpoBean, NWZC150001CpouDetailBean cpoDtlBean) {

        final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
        reqCpoDtlTMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        reqCpoDtlTMsg.cpoOrdNum.setValue(cpoBean.getCpoOrdNum());
        reqCpoDtlTMsg.cpoDtlLineNum.setValue(cpoDtlBean.getCpoDtlLineNum());
        reqCpoDtlTMsg.cpoDtlLineSubNum.setValue(cpoDtlBean.getCpoDtlLineSubNum());

        final CPO_DTLTMsg resCpoDtlTMsg = (CPO_DTLTMsg) findByKey(reqCpoDtlTMsg);

        if (resCpoDtlTMsg != null) {
            return resCpoDtlTMsg;
        } else {
            return new CPO_DTLTMsg();
        }
    }

    // 2017/10/11 S21_NA#21300 Add Start
    /**
     * CPO Detail retrieval
     * 
     * <pre>
     * CPO Detail is retrieved, and the acquisition result is returned.
     * ＜Search condition＞
     * ・Global Company Code
     * ・Order Line Number
     * ・Order Line Sub Number
     * </pre>
     * @param shpgPlnTMsg SHPG_PLNTMsg
     * @return Acquisition result（CPO_DTLTMsg）
     */
    public static CPO_DTLTMsg getCpoDtlByPK(SHPG_PLNTMsg shpgPlnTMsg) {

        final CPO_DTLTMsg reqCpoDtlTMsg = new CPO_DTLTMsg();
        reqCpoDtlTMsg.glblCmpyCd.setValue(shpgPlnTMsg.glblCmpyCd.getValue());
        reqCpoDtlTMsg.cpoOrdNum.setValue(shpgPlnTMsg.trxHdrNum.getValue());
        reqCpoDtlTMsg.cpoDtlLineNum.setValue(shpgPlnTMsg.trxLineNum.getValue());
        reqCpoDtlTMsg.cpoDtlLineSubNum.setValue(shpgPlnTMsg.trxLineSubNum.getValue());

        final CPO_DTLTMsg resCpoDtlTMsg = (CPO_DTLTMsg) findByKey(reqCpoDtlTMsg);

        if (resCpoDtlTMsg != null) {
            return resCpoDtlTMsg;
        } else {
            return new CPO_DTLTMsg();
        }
    }
    // 2017/10/11 S21_NA#21300 Add End
}
