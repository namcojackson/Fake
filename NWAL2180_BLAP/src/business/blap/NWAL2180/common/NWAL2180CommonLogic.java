/*
 * <pre>Copyright (c) 2015-2017 Canon USA Inc. All rights
 * reserved.</pre>
 */
package business.blap.NWAL2180.common;

import static business.blap.NWAL2180.constant.NWAL2180Constant.DS_CONTR_NUM;
import static business.blap.NWAL2180.constant.NWAL2180Constant.FLEET;
import static business.blap.NWAL2180.constant.NWAL2180Constant.MAN_CONTR_OVRD_RSN_NM;
import static business.blap.NWAL2180.constant.NWAL2180Constant.MDSE_CD;
import static business.blap.NWAL2180.constant.NWAL2180Constant.MDSE_DESC_SHORT_TXT;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0403E;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0551W;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0716E;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0782E;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0784W;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0789E;
import static business.blap.NWAL2180.constant.NWAL2180Constant.NWZM1661E;
import static business.blap.NWAL2180.constant.NWAL2180Constant.PRC_LIST_BAND_DESC_TXT;
import static business.blap.NWAL2180.constant.NWAL2180Constant.PRC_MTR_PKG_NM;
import static business.blap.NWAL2180.constant.NWAL2180Constant.PRC_MTR_PKG_PK;
import static business.blap.NWAL2180.constant.NWAL2180Constant.PRC_TIER_SVC_OFFER_CD;
import static business.blap.NWAL2180.constant.NWAL2180Constant.UNIT_MDSE;
import static business.blap.NWAL2180.constant.NWAL2180Constant.XX_FLG_HARD;
import static business.blap.NWAL2180.constant.NWAL2180Constant.XX_FLG_PARENT;
import static business.blap.NWAL2180.constant.NWAL2180Constant.XX_FLG_TIER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDMsg;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.blap.NWAL2180.NWAL2180Query;
import business.blap.NWAL2180.NWAL2180SMsg;
import business.blap.NWAL2180.NWAL2180_ACMsg;
import business.blap.NWAL2180.NWAL2180_BCMsg;
import business.blap.NWAL2180.NWAL2180_CCMsg;
import business.blap.NWAL2180.NWAL2180_ICMsg;
import business.blap.NWAL2180.NWAL2180_JCMsg;
import business.blap.NWAL2180.NWAL2180_RCMsg;
import business.blap.NWAL2180.NWAL2180_UCMsg;
import business.blap.NWAL2180.NWAL2180_VCMsg;
import business.blap.NWAL2180.NWAL2180_XCMsg;
import business.blap.NWAL2180.NWAL2180_ZCMsg;
import business.blap.NWAL2180.constant.NWAL2180Constant.XX_PAGE;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.PRC_LIST_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         T.Murai         Create          N/A
 * 2016/03/10   Fujitsu         M.Yamada        Update          QC#5261
 * 2016/03/30   Fujitsu         M.Yamada        Update          QC#5261-4
 * 2016/03/30   Fujitsu         M.Yamada        Update          QC#4631
 * 2016/05/17   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/05/30   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 * 2016/07/26   Fujitsu         M.Yamada        Update          QC#10379
 * 2016/08/01   Fujitsu         M.Yamada        Update          QC#10372, QC#10380
 * 2016/08/24   Fujitsu         M.Yamada        Update          QC#13057
 * 2016/09/07   Fujitsu         M.Yamada        Update          QC#13256
 * 2016/09/14   Fujitsu         M.Yamada        Update          QC#14513
 * 2016/09/15   Fujitsu         M.Yamada        Update          QC#13555
 * 2016/09/30   Fujitsu         M.Yamada        Update          QC#14869
 * 2016/10/04   Fujitsu         M.Yamada        Update          QC#14940
 * 2016/10/14   Fujitsu         M.Yamada        Update          QC#9362
 * 2016/10/17   Fujitsu         M.Yamada        Update          QC#15198
 * 2016/10/19   Fujitsu         M.Yamada        Update          QC#13256
 * 2016/12/20   Fujitsu         S.Iidaka        Update          QC#16141
 * 2017/02/10   Fujitsu         M.Yamada        Update          QC#17289
 * 2017/02/17   Fujitsu         M.Yamada        Update          QC#17665
 * 2017/03/01   Fujitsu         M.Yamada        Update          QC#17789
 * 2017/03/13   Fujitsu         M.Yamada        Update          QC#17700
 * 2017/03/15   Fujitsu         M.Yamada        Update          QC#17971
 * 2017/03/27   Fujitsu         M.Yamada        Update          QC#18156
 * 2017/03/29   Fujitsu         S.Ohki          Update          QC#18171
 * 2017/04/19   Fujitsu         M.Yamada        Update          QC#18346
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 * 2017/10/06   Fujitsu         H.Sugawara      Update          QC#21341
 * 2017/10/19   Fujitsu         R.Nakamura      Update          QC#21862
 * 2018/05/08   Fujitsu         W.Honda         Update          QC#25030
 * 2018/12/07   Fujitsu         K.Ishizuka      Update          QC#29484
 *</pre>
 */
public class NWAL2180CommonLogic {

    /**
     * set Header
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setHeader(NWAL2180CMsg bizMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(bizMsg.manContrOvrdFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.manContrOvrdRsnCd)) {
            S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getContrRsnNm(bizMsg, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (ssmResult.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NWAM0403E, new String[] {"manual reason", bizMsg.manContrOvrdRsnCd.getValue() });
                return false;
            }
            Map<String, Object> resultMap = resList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdRsnNm, (String) resultMap.get(MAN_CONTR_OVRD_RSN_NM));
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsContrPk)) {

            S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getDsContrNum(bizMsg, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (ssmResult.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NWAM0403E, new String[] {"Contract", bizMsg.dsContrPk.getValue().toPlainString() });
                return false;
            }
            Map<String, Object> resultMap = resList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum, (String) resultMap.get(DS_CONTR_NUM));
        }
        String prcCatgNmB = getPrcCatgNm(bizMsg.prcCatgCd_HB.getValue(), glblCmpyCd);
        String prcCatgNmC = getPrcCatgNm(bizMsg.prcCatgCd_C.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HB, prcCatgNmB);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_C, prcCatgNmC);

        return true;
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return Map<BigDecimal, Integer>
     */
    public static Map<BigDecimal, Integer> countQtyI(NWAL2180CMsg bizMsg) {
        Map<BigDecimal, Integer> qtyMap = new HashMap<BigDecimal, Integer>();

        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal modelId = bizMsg.I.no(i).mdlId_I.getValue();
            if (qtyMap.containsKey(modelId)) {
                int qty = qtyMap.get(modelId).intValue();
                qty++;
                qtyMap.put(modelId, qty);
            } else {
                qtyMap.put(modelId, 1);
            }
        }
        return qtyMap;
    }

    /**
     * set SvcPrc Init Fl
     * @param bizMsg NWAL2180CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPrcInitFl(NWAL2180CMsg bizMsg, Map<BigDecimal, Integer> countQtyList, String glblCmpyCd) {

        BigDecimal qty = BigDecimal.ZERO;
        for (Integer val : countQtyList.values()) {
            qty = qty.add(new BigDecimal(val));
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).t_MdlNm_A, FLEET);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).t_MdlNm_Z, FLEET);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxTotQtyCnt_A, qty);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).shpgIntvlMthNum_A, bizMsg.shpgIntvlMthNum);
        // Mod Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
//        bizMsg.A.no(0).xxTotPrcAmt_PB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(0).xxTotPrcAmt_EB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(0).xxTotPrcAmt_TB.setValue(BigDecimal.ZERO);
        bizMsg.A.no(0).xxTotPrcAmt_PB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(0).xxTotPrcAmt_EB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(0).xxTotPrcAmt_TB.setValue(round(BigDecimal.ZERO, scale));
        // Mod End 2017/10/19 QC#21862
        bizMsg.Z.no(0).xxFlgNm_Z.setValue(XX_FLG_PARENT);

        bizMsg.A.setValidCount(1);
        bizMsg.Z.setValidCount(1);

        return true;
    }

    /**
     * set SvcPrc at Init
     * @param bizMsg NWAL2180CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param modelId BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPrcInit(NWAL2180CMsg bizMsg, Map<BigDecimal, Integer> countQtyList, BigDecimal modelId, String glblCmpyCd) {

        // 2018/12/07 S21_NA#29484 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            return true;
        }
        // 2018/12/07 S21_NA#29484 Add End
        
        int aNum = bizMsg.A.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).mdlId_A, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).xxTotQtyCnt_A, new BigDecimal(countQtyList.get(modelId)));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aNum).shpgIntvlMthNum_A, bizMsg.shpgIntvlMthNum);
        // Mod Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
//        bizMsg.A.no(aNum).xxTotPrcAmt_PB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(aNum).xxTotPrcAmt_EB.setValue(BigDecimal.ZERO);
//        bizMsg.A.no(aNum).xxTotPrcAmt_TB.setValue(BigDecimal.ZERO);
        bizMsg.A.no(aNum).xxTotPrcAmt_PB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(aNum).xxTotPrcAmt_EB.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.A.no(aNum).xxTotPrcAmt_TB.setValue(round(BigDecimal.ZERO, scale));
        // Mod End 2017/10/19 QC#21862

        bizMsg.A.setValidCount(aNum + 1);

        int zNum = bizMsg.Z.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zNum).mdlId_Z, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zNum).xxFlgNm_Z, XX_FLG_PARENT);
        bizMsg.Z.setValidCount(zNum + 1);

        getMdlNm(bizMsg, aNum, glblCmpyCd);
        return true;
    }

    /**
     * set SvcPrc at Init by Config
     * @param bizMsg NWAL2180CMsg
     * @param countQtyList Map<BigDecimal, Integer>
     * @param modelId BigDecimal
     * @return boolean
     */
    public static void setSvcPrcInitByConfig(NWAL2180CMsg bizMsg, BigDecimal modelId) {
        
        // 2018/12/07 S21_NA#29484 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            return;
        }
        // 2018/12/07 S21_NA#29484 Add End

        int ixR = bizMsg.R.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).mdlId_R, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).shpgIntvlMthNum_R, bizMsg.shpgIntvlMthNum);
        // Mod Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//        bizMsg.R.no(ixR).xxTotPrcAmt_PR.setValue(BigDecimal.ZERO);
//        bizMsg.R.no(ixR).xxTotPrcAmt_ER.setValue(BigDecimal.ZERO);
//        bizMsg.R.no(ixR).xxTotPrcAmt_TR.setValue(BigDecimal.ZERO);
        bizMsg.R.no(ixR).xxTotPrcAmt_PR.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.R.no(ixR).xxTotPrcAmt_ER.setValue(round(BigDecimal.ZERO, scale));
        bizMsg.R.no(ixR).xxTotPrcAmt_TR.setValue(round(BigDecimal.ZERO, scale));
        // Mod End 2017/10/19 QC#21862

        bizMsg.R.setValidCount(ixR + 1);

        int ixU = bizMsg.U.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(ixU).mdlId_U, modelId);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(ixU).xxFlgNm_U, XX_FLG_PARENT);
        bizMsg.U.setValidCount(ixU + 1);
    }

    /**
     * set SvcPricing at Update
     * @param bizMsg NWAL2180CMsg
     * @param mdlId BigDecimal
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPricingReOpen(NWAL2180CMsg bizMsg, BigDecimal mdlId, String glblCmpyCd) {

        int aIndex = 0;
        for (; aIndex < bizMsg.A.getValidCount(); aIndex++) {
            if (mdlId.compareTo(bizMsg.A.no(aIndex).mdlId_A.getValue()) == 0) {
                break;
            }
        }

        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(aIndex);
        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A)) {

            S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getSvcPricingPkList(bizMsg, aIndex, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {

                for (int j = 0; j < resList.size(); j++) {
                    Map<String, Object> result = resList.get(j);
                    ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_KP.no(j), (BigDecimal) result.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgNm_VW.no(j), (String) result.get("PRC_MTR_PKG_NM"));
                }
            }
        }
        // get Name
        getMdlNm(bizMsg, aIndex, glblCmpyCd);
        String prcCatgNm = getPrcCatgNm(aBizMsg.prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgNm_A, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(aBizMsg.prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcListTpCd_A, prcListTpCd);

        for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
            NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(j);
            if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
                continue;
            }
            if (aBizMsg.mdlId_A.getValue().compareTo(zBizMsg.mdlId_Z.getValue()) == 0) {
                // set BackUp
                ZYPEZDItemValueSetter.setValue(zBizMsg.mlyCopyInclPrcQty_BK, zBizMsg.mlyCopyInclPrcQty_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.contrMtrMultRate_BK, zBizMsg.contrMtrMultRate_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_BK, zBizMsg.xsMtrAmtRate_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.minCopyVolCnt_BK, zBizMsg.minCopyVolCnt_Z);
                ZYPEZDItemValueSetter.setValue(zBizMsg.maxCopyVolCnt_BK, zBizMsg.maxCopyVolCnt_Z);

                if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z)) {
                    zBizMsg.xxFlgNm_Z.setValue(XX_FLG_HARD);
                } else if (ZYPCommonFunc.hasValue(zBizMsg.prcSvcTierTpCd_Z)) {
                    zBizMsg.xxFlgNm_Z.setValue(XX_FLG_TIER);
                } else {
                    zBizMsg.xxFlgNm_Z.setValue(XX_FLG_PARENT);
                }
                // set Name
                if (ZYPCommonFunc.hasValue(zBizMsg.bllgMtrLbCd_Z)) {
                    String bllgMtrNm = getMtrNm(zBizMsg.bllgMtrLbCd_Z.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                        bizMsg.setMessageInfo(NWAM0403E, new String[] {"MTR_LB", zBizMsg.bllgMtrLbCd_Z.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(zBizMsg.mtrLbDescTxt_ZB, bllgMtrNm);
                    }
                }
                String mdseNm = getMdseNm(zBizMsg.usgMdseCd_Z.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(zBizMsg.mdseDescShortTxt_Z, mdseNm);

                if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z)) {
                    String regMtrNm = getMtrNm(zBizMsg.regMtrLbCd_Z.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                        bizMsg.setMessageInfo(NWAM0403E, new String[] {"MTR_LB", zBizMsg.regMtrLbCd_Z.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(zBizMsg.mtrLbDescTxt_Z, regMtrNm);
                    }
                }
            }
        }
        BigDecimal baseBllgCycle = getBllgCycle(bizMsg);

        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            setSvcPricingReOpenConfig(bizMsg, bizMsg.R.no(i), glblCmpyCd, baseBllgCycle);
        }
        return true;
    }

    /**
     * set SvcPricing at Update
     * @param bizMsg NWAL2180CMsg
     * @param rBizMsg BigDecimal
     * @param glblCmpyCd String
     * @param baseBllgCycle billing cycle
     * @return boolean
     */
    public static boolean setSvcPricingReOpenConfig(//
            NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg, String glblCmpyCd, BigDecimal baseBllgCycle) {

        // get Name
        String prcCatgNm = getPrcCatgNm(rBizMsg.prcCatgCd_R.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(rBizMsg.prcCatgCd_R.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, prcListTpCd);

        for (int j = 0; j < bizMsg.U.getValidCount(); j++) {
            NWAL2180_UCMsg uBizMsg = bizMsg.U.no(j);
            if (!ZYPCommonFunc.hasValue(uBizMsg.mdlId_U)) {
                continue;
            }
            if (rBizMsg.mdlId_R.getValue().compareTo(uBizMsg.mdlId_U.getValue()) == 0 //
                    && rBizMsg.dsOrdPosnNum_R.getValue().equals(uBizMsg.dsOrdPosnNum_U.getValue())) { // QC#16141
                if (!ZYPCommonFunc.hasValue(rBizMsg.cpoSvcConfigRefPk_R) || !ZYPCommonFunc.hasValue(uBizMsg.cpoSvcConfigRefPk_U)) {
                    continue;
                }
                if (rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(uBizMsg.cpoSvcConfigRefPk_U.getValue()) != 0) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                    uBizMsg.xxFlgNm_U.setValue(XX_FLG_HARD);
                } else if (ZYPCommonFunc.hasValue(uBizMsg.prcSvcTierTpCd_U)) {
                    uBizMsg.xxFlgNm_U.setValue(XX_FLG_TIER);
                } else {
                    uBizMsg.xxFlgNm_U.setValue(XX_FLG_PARENT);
                }
                // set Name
                if (ZYPCommonFunc.hasValue(uBizMsg.bllgMtrLbCd_U)) {
                    String bllgMtrNm = getMtrNm(uBizMsg.bllgMtrLbCd_U.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                        bizMsg.setMessageInfo(NWAM0403E, new String[] {"MTR_LB", uBizMsg.bllgMtrLbCd_U.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(uBizMsg.mtrLbDescTxt_UB, bllgMtrNm);
                    }
                }
                String mdseNm = getMdseNm(uBizMsg.usgMdseCd_U.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(uBizMsg.mdseDescShortTxt_U, mdseNm);

                if (ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                    String regMtrNm = getMtrNm(uBizMsg.regMtrLbCd_U.getValue(), glblCmpyCd);
                    if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                        bizMsg.setMessageInfo(NWAM0403E, new String[] {"MTR_LB", uBizMsg.regMtrLbCd_U.getValue() });
                    } else {
                        ZYPEZDItemValueSetter.setValue(uBizMsg.mtrLbDescTxt_U, regMtrNm);
                    }
                }
            }
        }
        if (ZYPCommonFunc.hasValue(rBizMsg.xxTotPrcAmt_PR)) {
            // set Price
            BigDecimal periodicBase = rBizMsg.xxTotPrcAmt_PR.getValue();
            if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.shpgIntvlMthNum.getValue();

                BigDecimal extendedBase = periodicBase;
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/19 QC#21862
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    // Mod Start 2017/10/06 QC#21341
                    //totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
//                    totalBase = getTotalBase_TR(extendedBase, term, baseBllgCycle);
                    totalBase = getTotalBase(extendedBase, term, baseBllgCycle, scale);
                    // Mod End 2017/10/06 QC#21341
                }

//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, extendedBase);
//                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, totalBase);
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_ER, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_TR, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21862
            }
        }

        return true;
    }

    /**
     * set SvcPricing at Update
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setSvcPricingReOpenFL(NWAL2180CMsg bizMsg, String glblCmpyCd) {

        int aIndex = 0;

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).prcMtrPkgPk_A)) {

            S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getSvcPricingPkList(bizMsg, aIndex, glblCmpyCd);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {

                for (int j = 0; j < resList.size(); j++) {
                    Map<String, Object> result = resList.get(j);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcMtrPkgPk_KP.no(j), (BigDecimal) result.get("PRC_MTR_PKG_PK"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcMtrPkgNm_VW.no(j), (String) result.get("PRC_MTR_PKG_NM"));
                }
            }
        }
        // get Name
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).t_MdlNm_A, FLEET);
        String prcCatgNm = getPrcCatgNm(bizMsg.A.no(aIndex).prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcCatgNm_A, prcCatgNm);

        String prcListTpCd = getPrcListTpCd(bizMsg.A.no(aIndex).prcCatgCd_A.getValue(), glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).prcListTpCd_A, prcListTpCd);

        for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
            // set BackUp
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mlyCopyInclPrcQty_BK, bizMsg.Z.no(j).mlyCopyInclPrcQty_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).contrMtrMultRate_BK, bizMsg.Z.no(j).contrMtrMultRate_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).xsMtrAmtRate_BK, bizMsg.Z.no(j).xsMtrAmtRate_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).minCopyVolCnt_BK, bizMsg.Z.no(j).minCopyVolCnt_Z);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).maxCopyVolCnt_BK, bizMsg.Z.no(j).maxCopyVolCnt_Z);

            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).regMtrLbCd_Z)) {
                bizMsg.Z.no(j).xxFlgNm_Z.setValue(XX_FLG_HARD);
            } else if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).prcSvcTierTpCd_Z)) {
                bizMsg.Z.no(j).xxFlgNm_Z.setValue(XX_FLG_TIER);
            } else {
                bizMsg.Z.no(j).xxFlgNm_Z.setValue(XX_FLG_PARENT);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).bllgMtrLbCd_Z)) {
                String bllgMtrNm = getMtrNm(bizMsg.Z.no(j).bllgMtrLbCd_Z.getValue(), glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(bllgMtrNm)) {
                    bizMsg.setMessageInfo(NWAM0403E, new String[] {"MTR_LB", bizMsg.Z.no(j).bllgMtrLbCd_Z.getValue() });
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mtrLbDescTxt_ZB, bllgMtrNm);
                }
            }
            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).usgMdseCd_Z)) {
                String mdseNm = getMdseNm(bizMsg.Z.no(j).usgMdseCd_Z.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mdseDescShortTxt_Z, mdseNm);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(j).regMtrLbCd_Z)) {
                String regMtrNm = getMtrNm(bizMsg.Z.no(j).regMtrLbCd_Z.getValue(), glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(regMtrNm)) {
                    bizMsg.setMessageInfo(NWAM0403E, new String[] {"MTR_LB", bizMsg.Z.no(j).regMtrLbCd_Z.getValue() });
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(j).mtrLbDescTxt_Z, regMtrNm);
                }
            }

        }
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB)) {
            // set Price
            BigDecimal periodicBase = bizMsg.A.no(aIndex).xxTotPrcAmt_PB.getValue();
            // BigDecimal qty =
            // bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue();
            BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
            if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.shpgIntvlMthNum.getValue();

                BigDecimal extendedBase = periodicBase;
                // BigDecimal totalBase =
                // extendedBase.multiply((term.divide(baseBllgCycle)));
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/19 QC#21862
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
                int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());

                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    // Mod Start 2017/10/06 QC#21341
                    //totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
//                    totalBase = getTotalBase_TB(extendedBase, term, baseBllgCycle);
                    totalBase = getTotalBase(extendedBase, term, baseBllgCycle, scale);
                    // Mod End 2017/10/06 QC#21341
                }

//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, extendedBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, totalBase);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21862
            }
        }

        return true;
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    public static BigDecimal getBllgCycle(NWAL2180CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getbllgCycle(bizMsg);
        if (ssmResult.isCodeNormal()) {

            BigDecimal bllgCycle = (BigDecimal) ssmResult.getResultObject();
            if (ZYPCommonFunc.hasValue(bllgCycle)) {
                return bllgCycle;
            }
        }
        bizMsg.setMessageInfo(NWAM0403E, new String[] {"BLLG_CYCLE", bizMsg.baseBllgCycleCd.getValue() });
        return null;
    }

    /**
     * get ModelNm
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     */
    public static void getMdlNm(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getMdlNm(bizMsg, index, glblCmpyCd);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(index);
        BigDecimal mdlId = aBizMsg.mdlId_A.getValue();
        if (resList != null && !resList.isEmpty() && ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {

            Map<String, Object> result = resList.get(0);
            String mdlNm = (String) result.get("T_MDL_NM");
            ZYPEZDItemValueSetter.setValue(aBizMsg.t_MdlNm_A, mdlNm);
            for (int j = 0; j < bizMsg.Z.getValidCount(); j++) {
                NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(j);
                if (!ZYPCommonFunc.hasValue(zBizMsg.mdlId_Z)) {
                    continue;
                }
                if (mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.t_MdlNm_Z, mdlNm);
                }
            }
            for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                NWAL2180_UCMsg uBizMsg = bizMsg.U.no(ixU);
                if (!ZYPCommonFunc.hasValue(uBizMsg.mdlId_U)) {
                    continue;
                }
                if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.t_MdlNm_U, mdlNm);
                }
            }
        } else {
            bizMsg.setMessageInfo(NWAM0403E, new String[] {"Model", mdlId.toPlainString() });

        }
    }

    /**
     * get MeterNm
     * @param mtrLbCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getMtrNm(String mtrLbCd, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getMtrNm(mtrLbCd, glblCmpyCd);
        @SuppressWarnings("unchecked")
        List<String> resList = (List<String>) ssmResult.getResultObject();
        if (resList != null && !resList.isEmpty() && ZYPCommonFunc.hasValue(mtrLbCd)) {

            String resultStr = resList.get(0);
            return resultStr;
        }

        return null;
    }

    /**
     * /** get PrcListNm
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getPrcCatgNm(String prcCatgCd, String glblCmpyCd) {
        String prcCatgNm = "";

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getPrcCatgNm(prcCatgCd, glblCmpyCd);
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
        if (result != null && !result.isEmpty()) {

            prcCatgNm = (String) result.get("PRC_CATG_NM");
        }
        return prcCatgNm;
    }

    /**
     * /** get PrcListNm
     * @param prcCatgCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getPrcListTpCd(String prcCatgCd, String glblCmpyCd) {
        String prcListTpCd = "";

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getPrcListTp(prcCatgCd, glblCmpyCd);
        @SuppressWarnings("unchecked")
        Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
        if (result != null && !result.isEmpty()) {

            prcListTpCd = (String) result.get("PRC_LIST_TP_CD");
        }
        return prcListTpCd;
    }

    /**
     * get MdseNm
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return String
     */
    public static String getMdseNm(String mdseCd, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getMdseNm(mdseCd, glblCmpyCd);
        String result1 = (String) ssmResult.getResultObject();
        if (ZYPCommonFunc.hasValue(result1)) {

            return result1;
        }
        return null;
    }

    /**
     * setAcsryChrgUpdate
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @param target String J / B
     * @return boolean
     */
    public static boolean setAcsryChrgUpdate(NWAL2180CMsg bizMsg, int index, String glblCmpyCd, String target) {

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getUpdateAcsry(bizMsg, index, glblCmpyCd, target);

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            String mdseCd = (String) resultMap.get(MDSE_CD);
            String mdseNm = (String) resultMap.get(MDSE_DESC_SHORT_TXT);
            String dplyLineNum = (String) resultMap.get("DPLY_LINE_NUM");

            if ("B".equals(target)) { // Rental Equip Accessory Charge
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseCd_B, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseDescShortTxt_B, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).xxLineNum_B, dplyLineNum);
                ZYPEZDItemValueSetter.setValue(//
                        bizMsg.B.no(index).prcListEquipConfigNum_B, (BigDecimal) resultMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(//
                        bizMsg.B.no(index).prcListEquipConfigNm_B, (String) resultMap.get("PRC_LIST_EQUIP_CONFIG_NM"));
            } else { // Accessory Charge
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).mdseCd_J, mdseCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).mdseDescShortTxt_J, mdseNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).xxLineNum_J, dplyLineNum);
            }

        }
        return true;
    }

    /**
     * setAddChrgUpdate
     * @param bizMsg NWAL2180CMsg
     * @param index int
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setAddChrgUpdate(NWAL2180CMsg bizMsg, int index, String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getUpdateAddChrg(bizMsg, index, glblCmpyCd);
        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            String dplyLineNum = (String) resultMap.get("DPLY_LINE_NUM");
            String unitMdseCd = (String) resultMap.get(MDSE_CD);
            String unitMdseNm = (String) resultMap.get(UNIT_MDSE);

            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxLineNum_C, dplyLineNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).mdseCd_CU, unitMdseCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).mdseDescShortTxt_CU, unitMdseNm);
            // Add Start 2018/05/08 QC#25030
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).printDtlFlg_C, (String) resultMap.get("PRINT_DTL_FLG"));
            // Add End 2018/05/08 QC#25030
        }
        return true;
    }

    /**
     * @param bizMsg NWAL2180CMsg
     */
    public static void getMtrLbFL(NWAL2180CMsg bizMsg) {

        List<BigDecimal> mdlList = new ArrayList<BigDecimal>();
        List<String> mdlNmList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal mdl = bizMsg.I.no(i).mdlId_I.getValue();
            if (!mdlList.contains(mdl)) {
                mdlList.add(mdl);
                mdlNmList.add(bizMsg.I.no(i).t_MdlNm_I.getValue());
            }
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcMtrPkgPk_A)) {

            List<Integer> delList = new ArrayList<Integer>();
            for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
                delList.add(i);
            }

            ZYPTableUtil.deleteRows(bizMsg.Z, delList);

            return;
        }
        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getMtrLbFL(bizMsg, mdlList);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NWAM0784W, new String[] {mdlNmList.toString() });
            return;
        }

        S21SsmEZDResult ssmResultWk = NWAL2180Query.getInstance().isInMtrDB(bizMsg, mdlList);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResultWk.getResultObject();
        if (resList == null || resList.size() == 0) {
            bizMsg.setMessageInfo(NWAM0716E, new String[] {mdlNmList.toString() });
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList2 = (List<Map<String, Object>>) ssmResult.getResultObject();

        int ixZ = 0;
        for (int j = 0; j < resList2.size(); j++) {

            Map<String, Object> resMap = resList2.get(j);

            if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList2.get(j - 1).get("BILLING_COUNTER_CD"))) {

                bizMsg.Z.no(ixZ).clear();
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).xxFlgNm_Z, XX_FLG_PARENT);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]

                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).t_MdlNm_Z, bizMsg.A.no(0).t_MdlNm_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mdlId_Z, bizMsg.A.no(0).mdlId_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcCatgCd_Z, bizMsg.A.no(0).prcCatgCd_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcMtrPkgPk_Z, bizMsg.A.no(0).prcMtrPkgPk_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mdseDescShortTxt_Z, (String) resMap.get("USAGE_ITEM_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).usgMdseCd_Z, (String) resMap.get("USAGE_ITEM_CD"));
                ixZ++;
            }
            bizMsg.Z.no(ixZ).clear();

            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).xxFlgNm_Z, XX_FLG_HARD);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).t_MdlNm_Z, bizMsg.A.no(0).t_MdlNm_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mdlId_Z, bizMsg.A.no(0).mdlId_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcCatgCd_Z, bizMsg.A.no(0).prcCatgCd_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).prcMtrPkgPk_Z, bizMsg.A.no(0).prcMtrPkgPk_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).mtrLbDescTxt_Z, (String) resMap.get("HARD_COUNTER_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).regMtrLbCd_Z, (String) resMap.get("HARD_COUNTER_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).contrMtrMultRate_Z, (BigDecimal) resMap.get("MTR_MULT_RATE"));
            ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(ixZ).contrMtrMultRate_BK, (BigDecimal) resMap.get("MTR_MULT_RATE"));
            ixZ++;
        }
        bizMsg.xxNum_Z.setValue(0);
        bizMsg.Z.setValidCount(ixZ);

    }

    /**
     * @param bizMsg NWAL2180SMsg
     * @param glblMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void getMtrLb(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {

        int ixA = bizMsg.xxNum_A.getValueInt();

        int zStartRow = bizMsg.Z.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(ixA);
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                if (delList.size() == 0) {
                    zStartRow = i;
                }
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.Z, delList); // delete exists
        // data

        delList.clear();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.X.no(i).mdlId_X.getValue()) == 0) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.X, delList); // delete exists
        // data

        if (!ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A) //
                || isRateTypeAnnual(getPrcRateTpCd(bizMsg, aBizMsg))) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getMtrLb(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NWAM0784W, new String[] {aBizMsg.t_MdlNm_A.getValue() });
            return;
        }

        for (int i = bizMsg.Z.getValidCount() - 1; i > zStartRow - 1; i--) {
            EZDMsg.copy(bizMsg.Z.no(i), null, bizMsg.Z.no(i + 1), null);
        }

        // set First Line
        bizMsg.Z.no(zStartRow).clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).mdlId_Z, aBizMsg.mdlId_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).t_MdlNm_Z, aBizMsg.t_MdlNm_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).prcCatgCd_Z, aBizMsg.prcCatgCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).prcMtrPkgPk_Z, aBizMsg.prcMtrPkgPk_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxFlgNm_Z, XX_FLG_PARENT);
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(zStartRow).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]

        bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + 1);
        bizMsg.xxNum_Z.setValue(zStartRow);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int zRowCount = 0;
        zStartRow = 0;
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                if (zRowCount == 0) {
                    zStartRow = i;
                }
                zRowCount++;
            }
        }
        bizMsg.xxNum_Z.setValue(zStartRow);

        int addLineCount = 0;
        for (int j = 0; j < resList.size(); j++) {
            Map<String, Object> resMap = resList.get(j);
            if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {
                addLineCount++;
            }
            addLineCount++;
        }

        for (int i = bizMsg.Z.getValidCount() - 1; i > zStartRow; i--) {
            EZDMsg.copy(bizMsg.Z.no(i), null, bizMsg.Z.no(i + addLineCount - 1), null);
        }

        int ixZ = 0;
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) { // usage
            // area

            if (aBizMsg.mdlId_A.getValue().compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0) {
                for (int j = 0; j < resList.size(); j++) {

                    Map<String, Object> resMap = resList.get(j);
                    if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {

                        bizMsg.Z.no(i + ixZ).clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxFlgNm_Z, XX_FLG_PARENT);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y); // [+]

                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).t_MdlNm_Z, aBizMsg.t_MdlNm_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mdlId_Z, aBizMsg.mdlId_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcCatgCd_Z, aBizMsg.prcCatgCd_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcMtrPkgPk_Z, aBizMsg.prcMtrPkgPk_A);
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mdseDescShortTxt_Z, (String) resMap.get("USAGE_ITEM_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).usgMdseCd_Z, (String) resMap.get("USAGE_ITEM_CD"));

                        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A) //
                                && ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                            if (deriveDefaultBand(bizMsg, aBizMsg, bizMsg.Z.no(i + ixZ), null)) {
                                ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_Z, new BigDecimal(i + ixZ));
                                bizMsg.Z.setValidCount(i + ixZ + 1);
                                NWAL2180CommonLogic.afterBandPopupProc(bizMsg, glblMsg);
                            }
                        }
                        ixZ++;
                    }
                    bizMsg.Z.no(i + ixZ).clear();

                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).xxFlgNm_Z, XX_FLG_HARD);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).t_MdlNm_Z, aBizMsg.t_MdlNm_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mdlId_Z, aBizMsg.mdlId_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcCatgCd_Z, aBizMsg.prcCatgCd_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcMtrPkgPk_Z, aBizMsg.prcMtrPkgPk_A);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).bllgMtrLbCd_Z, (String) resMap.get("BILLING_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mtrLbDescTxt_ZB, (String) resMap.get("BILLING_COUNTER_NM"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).mtrLbDescTxt_Z, (String) resMap.get("HARD_COUNTER_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).regMtrLbCd_Z, (String) resMap.get("HARD_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).contrMtrMultRate_Z, (BigDecimal) resMap.get("MTR_MULT_RATE"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).contrMtrMultRate_BK, (BigDecimal) resMap.get("MTR_MULT_RATE"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcListBandDescTxt_Z //
                            , bizMsg.Z.no(i + ixZ - 1).prcListBandDescTxt_Z);
                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i + ixZ).prcBookMdseCd_Z //
                            , bizMsg.Z.no(i + ixZ - 1).prcBookMdseCd_Z);

                    ixZ++;
                }
                break;
            }
        }
        for (int i = 0; i < bizMsg.Z.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.Z.no(i).mdlId_Z)) {
                break;
            }
            bizMsg.Z.setValidCount(i + 1);
        }

        if (bizMsg.A.getValidCount() > 0) {
            reOrdUsg(bizMsg);
        }

    }

    public static void getMtrLbConfig(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
            return;
        }

        int ixR = bizMsg.xxNum_A.getValueInt();

        int uStartRow = bizMsg.U.getValidCount();
        List<Integer> delList = new ArrayList<Integer>();
        NWAL2180_RCMsg rBizMsg = bizMsg.R.no(ixR);
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 //
                    && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
                if (delList.size() == 0) {
                    uStartRow = i;
                }
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.U, delList); // delete exists
        // data

        delList.clear();
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.V.no(i).mdlId_V.getValue()) == 0 //
                    && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) == 0) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.V, delList); // delete exists
        // data

        if (!ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                || isRateTypeAnnual(getPrcRateTpCd(bizMsg, rBizMsg))) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getMtrLbConfig(rBizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NWAM0784W, new String[] {rBizMsg.t_MdlNm_R.getValue() });
            return;
        }

        for (int i = bizMsg.U.getValidCount() - 1; i > uStartRow - 1; i--) {
            EZDMsg.copy(bizMsg.U.no(i), null, bizMsg.U.no(i + 1), null);
        }

        // set First Line
        bizMsg.U.no(uStartRow).clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).mdlId_U, rBizMsg.mdlId_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).cpoSvcConfigRefPk_U, rBizMsg.cpoSvcConfigRefPk_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).t_MdlNm_U, rBizMsg.t_MdlNm_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).prcCatgCd_U, rBizMsg.prcCatgCd_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).prcMtrPkgPk_U, rBizMsg.prcMtrPkgPk_R);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).xxFlgNm_U, XX_FLG_PARENT);
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).xxSmryLineFlg_U, ZYPConstant.FLG_ON_Y); // [+]
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(uStartRow).actvFlg_U, ZYPConstant.FLG_ON_Y);

        bizMsg.U.setValidCount(bizMsg.U.getValidCount() + 1);
        bizMsg.xxNum_Z.setValue(uStartRow);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int zRowCount = 0;
        uStartRow = 0;
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 //
                    && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
                if (zRowCount == 0) {
                    uStartRow = i;
                }
                zRowCount++;
            }
        }
        bizMsg.xxNum_Z.setValue(uStartRow);

        int addLineCount = 0;
        for (int j = 0; j < resList.size(); j++) {
            Map<String, Object> resMap = resList.get(j);
            if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {
                addLineCount++;
            }
            addLineCount++;
        }

        for (int i = bizMsg.U.getValidCount() - 1; i > uStartRow; i--) {
            EZDMsg.copy(bizMsg.U.no(i), null, bizMsg.U.no(i + addLineCount - 1), null);
        }

        int ixU = 0;
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) { // usage
            // area

            if (rBizMsg.mdlId_R.getValue().compareTo(bizMsg.U.no(i).mdlId_U.getValue()) == 0 && rBizMsg.cpoSvcConfigRefPk_R.getValue().compareTo(bizMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0) {
                for (int j = 0; j < resList.size(); j++) {

                    Map<String, Object> resMap = resList.get(j);
                    if (j == 0 || !resMap.get("BILLING_COUNTER_CD").equals(resList.get(j - 1).get("BILLING_COUNTER_CD"))) {

                        bizMsg.U.no(i + ixU).clear();
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxFlgNm_U, XX_FLG_PARENT);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).actvFlg_U, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxSmryLineFlg_U, ZYPConstant.FLG_ON_Y); // [+]

                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).t_MdlNm_U, rBizMsg.t_MdlNm_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mdlId_U, rBizMsg.mdlId_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).cpoSvcConfigRefPk_U, rBizMsg.cpoSvcConfigRefPk_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcCatgCd_U, rBizMsg.prcCatgCd_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcMtrPkgPk_U, rBizMsg.prcMtrPkgPk_R);
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).bllgMtrLbCd_U, (String) resMap.get("BILLING_COUNTER_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mtrLbDescTxt_UB, (String) resMap.get("BILLING_COUNTER_NM"));

                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mdseDescShortTxt_U, (String) resMap.get("USAGE_ITEM_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).usgMdseCd_U, (String) resMap.get("USAGE_ITEM_CD"));

                        if (!ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R) //
                                || (!isInitialEvent(bizMsg))) {
                            if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R) //
                                    && ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                                if (deriveDefaultBandConfig(bizMsg, rBizMsg, bizMsg.U.no(i + ixU), null)) {
                                    ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_Z, new BigDecimal(i + ixU));
                                    bizMsg.U.setValidCount(i + ixU + 1);
                                    NWAL2180CommonLogic.afterBandPopupProcConfig(bizMsg, glblMsg);
                                }
                            }
                        }
                        ixU++;
                    }
                    bizMsg.U.no(i + ixU).clear();

                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).xxFlgNm_U, XX_FLG_HARD);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).actvFlg_U, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).t_MdlNm_U, rBizMsg.t_MdlNm_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mdlId_U, rBizMsg.mdlId_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).cpoSvcConfigRefPk_U, rBizMsg.cpoSvcConfigRefPk_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).dsOrdPosnNum_U, rBizMsg.dsOrdPosnNum_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcCatgCd_U, rBizMsg.prcCatgCd_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcMtrPkgPk_U, rBizMsg.prcMtrPkgPk_R);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).bllgMtrLbCd_U, (String) resMap.get("BILLING_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mtrLbDescTxt_UB, (String) resMap.get("BILLING_COUNTER_NM"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).mtrLbDescTxt_U, (String) resMap.get("HARD_COUNTER_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).regMtrLbCd_U, (String) resMap.get("HARD_COUNTER_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).contrMtrMultRate_U, (BigDecimal) resMap.get("MTR_MULT_RATE"));

                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcListBandDescTxt_U //
                            , bizMsg.U.no(i + ixU - 1).prcListBandDescTxt_U);
                    ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i + ixU).prcBookMdseCd_U //
                            , bizMsg.U.no(i + ixU - 1).prcBookMdseCd_U);

                    ixU++;
                }
                break;
            }
        }
        for (int i = 0; i < bizMsg.U.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.U.no(i).mdlId_U)) {
                break;
            }
            bizMsg.U.setValidCount(i + 1);
        }

        if (bizMsg.R.getValidCount() > 0) {
            reOrdUsgConfig(bizMsg);
        }
    }

    /**
     * @param bizMsg
     * @return
     */
    private static boolean isInitialEvent(NWAL2180CMsg bizMsg) {
        return "NWAL2180_INIT".equals(bizMsg.getScreenAplID()) //
                || "NWAL2180Scrn00_CMN_Reset".equals(bizMsg.getScreenAplID()) //
                || "NWAL2180Scrn00_CMN_Save".equals(bizMsg.getScreenAplID());
    }

    /**
     * deriveDefaultBand
     * @param bizMsg NWAL2180CMsg
     * @param aBizMsg NWAL2180_ACMsg
     * @param zBizMsg NWAL2180_ZCMsg
     * @param prcListBandDescTxt String
     * @return boolean
     */
    public static boolean deriveDefaultBand(//
            NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg, NWAL2180_ZCMsg zBizMsg, String prcListBandDescTxt) {
        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getPrcListStruTp(aBizMsg.prcCatgCd_A.getValue());
        if (ssmResult.isCodeNotFound()) {
            aBizMsg.prcCatgCd_A.setErrorInfo(1, NWAM0403E //
                    , new String[] {"DS_PRC_CATG", "getPrcListStruTp : " + aBizMsg.prcCatgCd_A.getValue() });
            return false;
        }

        if (ZYPCommonFunc.hasValue(prcListBandDescTxt)) {
            S21SsmEZDResult ssmBandResult //
            = NWAL2180Query.getInstance().getCountPrcListBandFromDescTxt(prcListBandDescTxt);
            if ((Integer) ssmBandResult.getResultObject() == 0) {
                zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NWAM0403E //
                        , new String[] {"PRC_LIST_BAND", "PRC_LIST_BAND_DESC_TXT : " + prcListBandDescTxt });
                return false;
            }
        }

        String prcListStruTp = (String) ssmResult.getResultObject();

        if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTp)) {
            ssmResult = NWAL2180Query.getInstance().getDefaultBandForService(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            ssmResult = NWAL2180Query.getInstance().getDefaultBandForServiceTiers(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTp)) {
            ssmResult = NWAL2180Query.getInstance().getDefaultBandForSupplyProgram(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt);
            if (ssmResult.getQueryResultCount() == 0) {
                S21SsmEZDResult sapn = NWAL2180Query.getInstance().getSplyAgmtPlnNm(aBizMsg, zBizMsg, bizMsg);
                List<Map<String, String>> splyAgmtPlnNmList = (List<Map<String, String>>) sapn.getResultObject();
                if (splyAgmtPlnNmList == null || splyAgmtPlnNmList.size() == 0) {
                    zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NWAM0789E //
                            , new String[] {"Band:" + prcListBandDescTxt });
                } else {
                    for (Map<String, String> splyAgmtPlnNmMap : splyAgmtPlnNmList) {
                        if (ZYPConstant.FLG_ON_Y.equals(splyAgmtPlnNmMap.get("EXISTS_FLG"))) {
                            continue;
                        }
                        zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NWZM1661E //
                                , new String[] {splyAgmtPlnNmMap.get("EDIT_SPLY_AGMT_PLN_NM") });
                        break;
                    }
                }
                return false;
            }

        } else {
            aBizMsg.prcCatgCd_A.setErrorInfo(1, NWAM0403E //
                    , new String[] {"PRC_LIST_STRU_TP", "PRC_LIST_STRU_TP : " + prcListStruTp });
            return false;
        }

        if (ssmResult.getQueryResultCount() != 1) {
            return true;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(zBizMsg.prcListBandDescTxt_Z, (String) rsltMap.get("PRC_LIST_BAND_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(zBizMsg.prcBookMdseCd_Z, (String) rsltMap.get("MDSE_CD"));

        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            return true;
        }

        BigDecimal minCopyVolCnt = (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT");
        BigDecimal maxCopyVolCnt = (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT");

        if (!ZYPCommonFunc.hasValue(minCopyVolCnt)//
                || !ZYPCommonFunc.hasValue(maxCopyVolCnt)) {
            zBizMsg.mlyCopyInclPrcQty_Z.setValue(BigDecimal.ZERO);
        } else if (minCopyVolCnt.compareTo(maxCopyVolCnt) == 0) {
            ZYPEZDItemValueSetter.setValue(zBizMsg.mlyCopyInclPrcQty_Z, minCopyVolCnt);
        } else {
            zBizMsg.mlyCopyInclPrcQty_Z.setValue(BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_Z, (BigDecimal) rsltMap.get("CPC_AMT_RATE"));

        return true;
    }

    /**
     * deriveDefaultBand
     * @param bizMsg NWAL2180CMsg
     * @param rBizMsg NWAL2180_ACMsg
     * @param uBizMsg NWAL2180_ZCMsg
     * @param prcListBandDescTxt String
     * @return boolean
     */
    public static boolean deriveDefaultBandConfig(//
            NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg, NWAL2180_UCMsg uBizMsg, String prcListBandDescTxt) {
        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getPrcListStruTp(rBizMsg.prcCatgCd_R.getValue());
        if (ssmResult.isCodeNotFound()) {
            rBizMsg.prcCatgCd_R.setErrorInfo(1, NWAM0403E //
                    , new String[] {"DS_PRC_CATG", "getPrcListStruTp : " + rBizMsg.prcCatgCd_R.getValue() });
            return false;
        }

        if (ZYPCommonFunc.hasValue(prcListBandDescTxt)) {
            S21SsmEZDResult ssmBandResult //
            = NWAL2180Query.getInstance().getCountPrcListBandFromDescTxt(prcListBandDescTxt);
            if ((Integer) ssmBandResult.getResultObject() == 0) {
                uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NWAM0403E //
                        , new String[] {"PRC_LIST_BAND", "PRC_LIST_BAND_DESC_TXT : " + prcListBandDescTxt });
                return false;
            }
        }

        String prcListStruTp = (String) ssmResult.getResultObject();

        if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTp)) {
            ssmResult = NWAL2180Query.getInstance().getDefaultBandForService(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            ssmResult = NWAL2180Query.getInstance().getDefaultBandForServiceTiers(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt);

        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTp)) {
            ssmResult = NWAL2180Query.getInstance().getDefaultBandForSupplyProgram(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt);
            if (ssmResult.getQueryResultCount() == 0) {
                S21SsmEZDResult sapn = NWAL2180Query.getInstance().getSplyAgmtPlnNm(rBizMsg, uBizMsg, bizMsg);
                List<Map<String, String>> splyAgmtPlnNmList = (List<Map<String, String>>) sapn.getResultObject();
                if (splyAgmtPlnNmList == null || splyAgmtPlnNmList.size() == 0) {
                    uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NWAM0789E //
                            , new String[] {"Band:" + prcListBandDescTxt });
                } else {
                    for (Map<String, String> splyAgmtPlnNmMap : splyAgmtPlnNmList) {
                        if (ZYPConstant.FLG_ON_Y.equals(splyAgmtPlnNmMap.get("EXISTS_FLG"))) {
                            continue;
                        }
                        uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NWZM1661E //
                                , new String[] {splyAgmtPlnNmMap.get("EDIT_SPLY_AGMT_PLN_NM") });
                        break;
                    }
                }
                return false;
            }

        } else {
            rBizMsg.prcCatgCd_R.setErrorInfo(1, NWAM0403E //
                    , new String[] {"PRC_LIST_STRU_TP", "PRC_LIST_STRU_TP : " + prcListStruTp });
            return false;
        }

        if (ssmResult.getQueryResultCount() != 1) {
            return true;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(uBizMsg.prcListBandDescTxt_U, (String) rsltMap.get("PRC_LIST_BAND_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(uBizMsg.prcBookMdseCd_U, (String) rsltMap.get("MDSE_CD"));

        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTp)) {
            return true;
        }

        BigDecimal minCopyVolCnt = (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT");
        BigDecimal maxCopyVolCnt = (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT");

        if (!ZYPCommonFunc.hasValue(minCopyVolCnt)//
                || !ZYPCommonFunc.hasValue(maxCopyVolCnt)) {
            uBizMsg.mlyCopyInclPrcQty_U.setValue(BigDecimal.ZERO);
        } else if (minCopyVolCnt.compareTo(maxCopyVolCnt) == 0) {
            ZYPEZDItemValueSetter.setValue(uBizMsg.mlyCopyInclPrcQty_U, minCopyVolCnt);
        } else {
            uBizMsg.mlyCopyInclPrcQty_U.setValue(BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(uBizMsg.xsMtrAmtRate_U, (BigDecimal) rsltMap.get("CPC_AMT_RATE"));

        return true;
    }

    private static boolean isRateTypeAnnual(String prcRateTpCd) {
        return PRC_RATE_TP.ANNUAL.equals(prcRateTpCd);
    }

    public static void reOrdUsg(NWAL2180CMsg bizMsg) {
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            return;
        }
        List<NWAL2180_ZCMsg> zMsgList = new ArrayList<NWAL2180_ZCMsg>();
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NWAL2180_ZCMsg zBizMsg = new NWAL2180_ZCMsg();
            EZDMsg.copy(bizMsg.Z.no(i), null, zBizMsg, null);
            zMsgList.add(zBizMsg);
        }

        List<String> xKeyList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            NWAL2180_XCMsg xBizMsg = bizMsg.X.no(i);
            String vKey = S21StringUtil.concatStrings(//
                    xBizMsg.mdlId_X.getValue().toPlainString() //
                    , "," //
                    , xBizMsg.bllgMtrLbCd_X.getValue());
            xKeyList.add(vKey);
        }

        ZYPTableUtil.clear(bizMsg.Z);
        int ixZ = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            BigDecimal mdlId = bizMsg.A.no(i).mdlId_A.getValue();
            for (NWAL2180_ZCMsg zBizMsgWk : zMsgList) {
                if (mdlId.compareTo(zBizMsgWk.mdlId_Z.getValue()) == 0) {
                    NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
                    EZDMsg.copy(zBizMsgWk, null, zBizMsg, null);

                    String uKey = S21StringUtil.concatStrings(//
                            zBizMsg.mdlId_Z.getValue().toPlainString() //
                            , "," //
                            , zBizMsg.bllgMtrLbCd_Z.getValue());
                    if (xKeyList.contains(uKey)) {
                        zBizMsg.xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        zBizMsg.xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    ixZ++;
                }
            }
        }
        bizMsg.Z.setValidCount(ixZ);
    }

    public static void reOrdUsgConfig(NWAL2180CMsg bizMsg) {
        List<NWAL2180_UCMsg> uMsgList = new ArrayList<NWAL2180_UCMsg>();
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NWAL2180_UCMsg uBizMsg = new NWAL2180_UCMsg();
            EZDMsg.copy(bizMsg.U.no(i), null, uBizMsg, null);
            uMsgList.add(uBizMsg);
        }

        List<String> wkKeyList = new ArrayList<String>();
        List<String> vKeyList = new ArrayList<String>();
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            NWAL2180_VCMsg vBizMsg = bizMsg.V.no(i);
            String vKey = S21StringUtil.concatStrings(//
                    vBizMsg.mdlId_V.getValue().toPlainString() //
                    , "," //
                    , vBizMsg.dsOrdPosnNum_V.getValue() //
                    , "," //
                    , vBizMsg.bllgMtrLbCd_V.getValue());
            if (wkKeyList.contains(vKey)) {
                vKeyList.add(vKey); // if vKey exists multiple records
                // then set to vKeyList.
            } else {
                wkKeyList.add(vKey);
            }
        }

        ZYPTableUtil.clear(bizMsg.U);
        int ixU = 0;
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            BigDecimal mdlId = bizMsg.R.no(i).mdlId_R.getValue();
            BigDecimal cpoSvcConfigRefPk = bizMsg.R.no(i).cpoSvcConfigRefPk_R.getValue();
            for (NWAL2180_UCMsg uBizMsgWk : uMsgList) {
                if (!isSameValue(mdlId, uBizMsgWk.mdlId_U.getValue())) {
                    continue;
                }
                if (!ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) || !ZYPCommonFunc.hasValue(uBizMsgWk.cpoSvcConfigRefPk_U)) {
                    continue;
                }
                if (cpoSvcConfigRefPk.compareTo(uBizMsgWk.cpoSvcConfigRefPk_U.getValue()) == 0) {
                    NWAL2180_UCMsg uBizMsg = bizMsg.U.no(ixU);
                    EZDMsg.copy(uBizMsgWk, null, uBizMsg, null);

                    String uKey = S21StringUtil.concatStrings(//
                            uBizMsg.mdlId_U.getValue().toPlainString() //
                            , "," //
                            , uBizMsg.dsOrdPosnNum_U.getValue() //
                            , "," //
                            , uBizMsg.bllgMtrLbCd_U.getValue());
                    if (vKeyList.contains(uKey)) {
                        uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_ON_Y);
                    } else {
                        uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    ixU++;
                }
            }
        }
        bizMsg.U.setValidCount(ixU);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd String
     * @return
     */
    public static void createPrcMtrPkgPulldown(NWAL2180CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(index).mdlId_A)) {
            return;
        }
        bizMsg.A.no(index).prcMtrPkgPk_KP.clear();
        bizMsg.A.no(index).prcMtrPkgNm_VW.clear();
        String prcListTp = bizMsg.A.no(index).prcListTpCd_A.getValue();

        if (PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcListTp)) {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg21(bizMsg, index, glblCmpyCd);

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(prcListTp)) {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg07(bizMsg, index, glblCmpyCd);

        } else {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg(bizMsg, index, glblCmpyCd);

        }

        // set Service Package
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resMap = resList.get(i);
            BigDecimal pkgPk = (BigDecimal) resMap.get(PRC_MTR_PKG_PK);
            String pkgNm = (String) resMap.get(PRC_MTR_PKG_NM);

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgPk_KP.no(i), pkgPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgNm_VW.no(i), pkgNm);

        }
    }

    public static void createPrcMtrPkgPulldownConfig(NWAL2180CMsg bizMsg) {
        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        NWAL2180_RCMsg rBizMsg = bizMsg.R.no(index);
        if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
            return;
        }
        rBizMsg.prcMtrPkgPk_RL.clear();
        rBizMsg.prcMtrPkgNm_RL.clear();
        String prcListTp = rBizMsg.prcListTpCd_R.getValue();

        if (PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcListTp)) {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg21(bizMsg, rBizMsg);

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(prcListTp)) {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg07(bizMsg, rBizMsg);

        } else {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg(bizMsg, rBizMsg);

        }

        // set Service Package
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resMap = resList.get(i);
            BigDecimal pkgPk = (BigDecimal) resMap.get(PRC_MTR_PKG_PK);
            String pkgNm = (String) resMap.get(PRC_MTR_PKG_NM);

            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_RL.no(i), pkgPk);
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgNm_RL.no(i), pkgNm);
        }
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param mdlList List<BigDecimal>
     * @param glblCmpyCd String
     * @return
     */
    public static void createPrcMtrPkgPulldownFL(NWAL2180CMsg bizMsg, List<BigDecimal> mdlList, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = null;

        int index = 0;
        bizMsg.A.no(index).prcMtrPkgPk_KP.clear();
        bizMsg.A.no(index).prcMtrPkgNm_VW.clear();
        String prcListTp = bizMsg.A.no(index).prcListTpCd_A.getValue();

        if (PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(prcListTp)) {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg21FL(bizMsg, mdlList, glblCmpyCd);

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(prcListTp)) {
            ssmResult = NWAL2180Query.getInstance().getSvcPkg07FL(bizMsg, mdlList, glblCmpyCd);

        } else {
            ssmResult = NWAL2180Query.getInstance().getSvcPkgFL(bizMsg, mdlList, glblCmpyCd);

        }

        // set Service Package
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            Map<String, Object> resMap = resList.get(i);
            BigDecimal pkgPk = (BigDecimal) resMap.get(PRC_MTR_PKG_PK);
            String pkgNm = (String) resMap.get(PRC_MTR_PKG_NM);

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgPk_KP.no(i), pkgPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).prcMtrPkgNm_VW.no(i), pkgNm);

        }
    }

    /**
     * @param bizMsg NWAL2180CMsg
     */
    public static void setRentalOrderFlag(NWAL2180CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().cntRentalOrder(bizMsg);
        @SuppressWarnings("unchecked")
        List<Integer> cnt = (List<Integer>) ssmResult.getResultObject();

        if (cnt.get(0) > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.rntlOrdFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * <pre>
     * @param bizMsg    NWAL2180SMsg
     * @param ssmResult S21SsmEZDResult
     * @param zIndex    zIndex
     * @param aIndex    aIndex
     * </pre>
     */
    public static void setTierInfo(NWAL2180CMsg bizMsg, S21SsmEZDResult ssmResult, int zIndex, int aIndex) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }

        NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(zIndex);
        BigDecimal mdlId = zBizMsg.mdlId_Z.getValue();
        String bllgMterLbCd = zBizMsg.bllgMtrLbCd_Z.getValue();

        List<Integer> tierIxList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            if (mdlId.compareTo(bizMsg.X.no(i).mdlId_X.getValue()) != 0) {
                continue;
            }
            if (!bllgMterLbCd.equals(bizMsg.X.no(i).bllgMtrLbCd_X.getValue())) {
                continue;
            }
            tierIxList.add(i);
        }
        ZYPTableUtil.deleteRows(bizMsg.X, tierIxList);

        boolean hasError = false;
        BigDecimal prevMaxCnt = BigDecimal.ZERO;
        int ixX = bizMsg.X.getValidCount();
        for (Map<String, Object> resMap : resList) {
            String tierCd = (String) resMap.get("PRC_SVC_TIER_TP_CD");
            BigDecimal rangeFrom = (BigDecimal) resMap.get("MIN_COPY_VOL_CNT");
            BigDecimal rangeTo = (BigDecimal) resMap.get("MAX_COPY_VOL_CNT");
            BigDecimal imageCharge = (BigDecimal) resMap.get("CPC_AMT_RATE");
            String tierDescTxt = (String) resMap.get("PRC_SVC_TIER_TP_DESC_TXT");

            NWAL2180_XCMsg xBizMsg = bizMsg.X.no(ixX);
            xBizMsg.clear();
            ZYPEZDItemValueSetter.setValue(xBizMsg.mdlId_X, zBizMsg.mdlId_Z);
            ZYPEZDItemValueSetter.setValue(xBizMsg.bllgMtrLbCd_X, zBizMsg.bllgMtrLbCd_Z);
            ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpCd_X, tierCd);
            ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpDescTxt_X, tierDescTxt);
            ZYPEZDItemValueSetter.setValue(xBizMsg.minCopyVolCnt_X, rangeFrom);
            ZYPEZDItemValueSetter.setValue(xBizMsg.maxCopyVolCnt_X, rangeTo);
            ZYPEZDItemValueSetter.setValue(xBizMsg.xsMtrAmtRate_X, imageCharge);

            ixX++;

            if (hasError) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(xBizMsg.minCopyVolCnt_X)//
                    || !ZYPCommonFunc.hasValue(xBizMsg.maxCopyVolCnt_X)) {
                hasError = true;
                continue;
            }
            if ((prevMaxCnt.add(BigDecimal.ONE)).compareTo(xBizMsg.minCopyVolCnt_X.getValue()) != 0) {
                hasError = true;
            }
            prevMaxCnt = xBizMsg.maxCopyVolCnt_X.getValue();

        }
        if (hasError) {
            zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NWAM0782E, new String[] {"Tier Information" });
        }
        bizMsg.X.setValidCount(ixX);
    }

    /**
     * <pre>
     * @param bizMsg    NWAL2180SMsg
     * @param ssmResult S21SsmEZDResult
     * @param ixU    uIndex
     * @param ixR    rIndex
     * </pre>
     */
    public static void setTierInfoConfig(NWAL2180CMsg bizMsg, S21SsmEZDResult ssmResult, int ixU, int ixR) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }

        NWAL2180_UCMsg uBizMsg = bizMsg.U.no(ixU);
        BigDecimal mdlId = uBizMsg.mdlId_U.getValue();
        BigDecimal cpoSvcConfigRefPk = uBizMsg.cpoSvcConfigRefPk_U.getValue();
        String bllgMterLbCd = uBizMsg.bllgMtrLbCd_U.getValue();

        List<Integer> tierIxList = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            if (mdlId.compareTo(bizMsg.V.no(i).mdlId_V.getValue()) != 0 || cpoSvcConfigRefPk.compareTo(bizMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) != 0) {
                continue;
            }
            if (!bllgMterLbCd.equals(bizMsg.V.no(i).bllgMtrLbCd_V.getValue())) {
                continue;
            }
            tierIxList.add(i);
        }
        ZYPTableUtil.deleteRows(bizMsg.V, tierIxList);

        boolean hasError = false;
        BigDecimal prevMaxCnt = BigDecimal.ZERO;
        int ixV = bizMsg.V.getValidCount();
        for (Map<String, Object> resMap : resList) {
            String tierCd = (String) resMap.get("PRC_SVC_TIER_TP_CD");
            BigDecimal rangeFrom = (BigDecimal) resMap.get("MIN_COPY_VOL_CNT");
            BigDecimal rangeTo = (BigDecimal) resMap.get("MAX_COPY_VOL_CNT");
            BigDecimal imageCharge = (BigDecimal) resMap.get("CPC_AMT_RATE");
            String tierDescTxt = (String) resMap.get("PRC_SVC_TIER_TP_DESC_TXT");

            NWAL2180_VCMsg vBizMsg = bizMsg.V.no(ixV);
            vBizMsg.clear();
            ZYPEZDItemValueSetter.setValue(vBizMsg.mdlId_V, uBizMsg.mdlId_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.cpoSvcConfigRefPk_V, uBizMsg.cpoSvcConfigRefPk_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.dsOrdPosnNum_V, uBizMsg.dsOrdPosnNum_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.bllgMtrLbCd_V, uBizMsg.bllgMtrLbCd_U);
            ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpCd_V, tierCd);
            ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpDescTxt_V, tierDescTxt);
            ZYPEZDItemValueSetter.setValue(vBizMsg.minCopyVolCnt_V, rangeFrom);
            ZYPEZDItemValueSetter.setValue(vBizMsg.maxCopyVolCnt_V, rangeTo);
            ZYPEZDItemValueSetter.setValue(vBizMsg.xsMtrAmtRate_V, imageCharge);

            ixV++;

            if (hasError) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(vBizMsg.minCopyVolCnt_V)//
                    || !ZYPCommonFunc.hasValue(vBizMsg.maxCopyVolCnt_V)) {
                hasError = true;
                continue;
            }
            if ((prevMaxCnt.add(BigDecimal.ONE)).compareTo(vBizMsg.minCopyVolCnt_V.getValue()) != 0) {
                hasError = true;
            }
            prevMaxCnt = vBizMsg.maxCopyVolCnt_V.getValue();

        }
        if (hasError) {
            uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NWAM0782E, new String[] {"Tier Information" });
        }
        bizMsg.V.setValidCount(ixV);
    }

    /**
     * <pre>
     * @param bizMsg        NWAL2180CMsg
     * @param aBizMsg       NWAL2180_ACMsg
     * @param glblCmpyCd    glblCmpyCd
     * </pre>
     */
    public static void deriveBasePrcForNonMeter(NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg, String glblCmpyCd) {
        String prcListStruTpCd = getPrcListStruTp(glblCmpyCd, aBizMsg.prcListTpCd_A.getValue());
        if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd) //
                || PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            return;
        }

        S21SsmEZDResult rslt = NWAL2180Query.getInstance().getAnnualPrc(glblCmpyCd, bizMsg, aBizMsg);
        if (rslt == null || rslt.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rsList.size() > 1) {
            bizMsg.setMessageInfo(NWAM0551W, new String[] {"PRC_LIST_SVC" });
            return;
        }
        // Mod Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(glblCmpyCd);
        int scale = getDealCcyDigit(glblCmpyCd, glblCmpyTMsg.stdCcyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, (BigDecimal) rsList.get(0).get("BASE_AMT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, round((BigDecimal) rsList.get(0).get("BASE_AMT"), scale));
        // Mod End 2017/10/19 QC#21862
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, PRC_RATE_TP.ANNUAL);
    }

    private static String getPrcListStruTp(String glblCmpyCd, String prcListTpCd) {
        PRC_LIST_TPTMsg tMsg = new PRC_LIST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcListTpCd, prcListTpCd);

        tMsg = (PRC_LIST_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.prcListStruTpCd.getValue();
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param bizMsg        NWAL2180CMsg
     * </pre>
     */
    public static void createPulldownList(String glblCmpyCd, String slsDt, NWAL2180CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList("PRC_TIER_SVC_OFFER", bizMsg.prcTierSvcOfferCd_KP, bizMsg.prcTierSvcOfferDescTxt_VW);
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_CONTR_TP", bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("PRC_SVC_PLN_TP", bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);

        createBillWithEquipPulldown(bizMsg);
        createContractIndicatorPulldown(bizMsg); // DS_CONTR_CATG

        createBllgCyclePulldown(bizMsg);

        ZYPCodeDataUtil.createPulldownList("BILL_BY_TP", bizMsg.billByTpCd_L, bizMsg.billByTpDescTxt_L);
        ZYPCodeDataUtil.createPulldownList("MTR_READ_METH", bizMsg.mtrReadMethCd_L, bizMsg.mtrReadMethDescTxt_L);
    }

    private static void createContractIndicatorPulldown(NWAL2180CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NWAL2180Query.getInstance().getContractIndicatorList();
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgDescTxt_L.no(i), (String) rsltMap.get("DS_CONTR_CATG_DESC_TXT"));
            i++;
        }
    }

    private static void createBllgCyclePulldown(NWAL2180CMsg bizMsg) {
        S21SsmEZDResult rsltSvc = NWAL2180Query.getInstance().getBllgCycleList(bizMsg);
        if (rsltSvc.isCodeNotFound()) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleCd_L.no(i), (String) rsltMap.get("BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgCycleDescTxt_L.no(i), (String) rsltMap.get("BLLG_CYCLE_DESC_TXT"));

            i++;
        }
    }

    private static void createBillWithEquipPulldown(NWAL2180CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg_L.no(1), ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(0), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxFlgNm_L.no(1), ZYPConstant.FLG_ON_Y);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param glblCmpyCd glblCmpyCd
     * @param glblMsg global message
     */
    public static void setInitData(NWAL2180CMsg bizMsg, String glblCmpyCd, NWAL2180SMsg glblMsg) {
        if (isImport(bizMsg)) {
            bizMsg.xxPageNm.setValue(XX_PAGE.PAGE_IMPT.getName());
        } else {
            bizMsg.xxPageNm.setValue(XX_PAGE.PAGE_SHELL.getName());
        }
        bizMsg.X.setValidCount(0);
        bizMsg.Z.setValidCount(0);
        bizMsg.U.setValidCount(0);
        bizMsg.V.setValidCount(0);

        // CPO_SVC_DTL
        S21SsmEZDResult rsltCsd = NWAL2180Query.getInstance().getCpoSvcDtl(bizMsg);
        if (rsltCsd.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, Object> rsltMap = (Map<String, Object>) rsltCsd.getResultObject();
            setCpoSvcDtlRsltToBizMsg(bizMsg, rsltMap);
        }

        S21SsmEZDResult rsltSph = null;
        S21SsmEZDResult rsltSphConfig = null;
        boolean isFleet = DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue());
        if (isFleet) {
            rsltSph = NWAL2180Query.getInstance().getSvcPrcHdrForFleet(bizMsg);
            if (rsltSph.isCodeNormal()) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSph.getResultObject();
                setSvcPrcHdrRsltToBizMsg(bizMsg, rsltList);
            } else {
                bizMsg.A.setValidCount(0);
            }
        } else {
            rsltSphConfig = NWAL2180Query.getInstance().getSvcPrcHdrByConfig(bizMsg);
            if (rsltSphConfig.isCodeNormal()) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSphConfig.getResultObject();
                setSvcPrcHdrConfigRsltToBizMsg(bizMsg, rsltList, glblMsg);
            } else {
                bizMsg.R.setValidCount(0);
            }
        }

        S21SsmEZDResult rsltSup = NWAL2180Query.getInstance().getSvcUsgPrc(bizMsg);
        if (rsltSup.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSup.getResultObject();
            if (isFleet) {
                setSvcPrcUsgRsltToBizMsg(bizMsg, rsltList);
            } else {
                setSvcPrcUsgRsltToBizMsgConfig(bizMsg, rsltList);
            }
        }

        S21SsmEZDResult rsltAbp = NWAL2180Query.getInstance().getSvcAddlBasePrc(bizMsg);
        if (rsltAbp.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAbp.getResultObject();
            setSvcPrcAbpRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.J.setValidCount(0);
            bizMsg.B.setValidCount(0);
        }

        S21SsmEZDResult rsltAcp = NWAL2180Query.getInstance().getSvcAddlChrgPrc(bizMsg);
        if (rsltAcp.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltAcp.getResultObject();
            setSvcPrcAcpRsltToBizMsg(bizMsg, rsltList);
        } else {
            bizMsg.C.setValidCount(0);
        }

        S21SsmEZDResult rsltSvc = NWAL2180Query.getInstance().getInitDataFromCpoSvc(bizMsg.glblCmpyCd.getValue(), bizMsg);
        if (rsltSvc.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rsltSvc.getResultObject();
            setCpoSvcRsltToBizMsg(bizMsg, rsltList);
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcPk, (BigDecimal) rsltList.get(0).get("CPO_SVC_PK"));
        } else {
            bizMsg.I.setValidCount(0);
        }

    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return if import process then return true.
     */
    public static boolean isImport(NWAL2180CMsg bizMsg) {
        return isImport(bizMsg.xxPageCd.getValue());
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @return if import process then return true.
     */
    public static boolean isImport(String val) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(val);
    }

    private static void setCpoSvcRsltToBizMsg(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int i = 0;
        // 2018/12/07 S21_NA#29484 Add Start
        String configTp = "";
        boolean addAsryFlg = false;
        // 2018/12/07 S21_NA#29484 Add End
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2180_ICMsg iBizMsg = bizMsg.I.no(i);

            ZYPEZDItemValueSetter.setValue(iBizMsg.dsOrdPosnNum_I, (String) rsltMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.mdlId_I, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.t_MdlNm_I, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.cpoDtlLineNum_I, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.cpoDtlLineSubNum_I, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.mtrReadMethCd_I, (String) rsltMap.get("MTR_READ_METH_CD"));
            ZYPEZDItemValueSetter.setValue(iBizMsg.cpoSvcConfigRefPk_I, (BigDecimal) rsltMap.get("CPO_SVC_CONFIG_REF_PK"));
            
            // 2018/12/07 S21_NA#29484 Add Start
            configTp = (String) rsltMap.get("CONFIG_TP_CD");
            if (S21StringUtil.isEquals(CONFIG_TP.ADD_TO_CONFIG, configTp)) {
                addAsryFlg = true;
            }
            // 2018/12/07 S21_NA#29484 Add End
            i++;
        }
        // 2018/12/07 S21_NA#29484 Add Start
        if (addAsryFlg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.addAsryFlg, ZYPConstant.CHKBOX_ON_Y);
        }
        // 2018/12/07 S21_NA#29484 Add End
        bizMsg.I.setValidCount(i);

    }

    private static void setSvcPrcHdrRsltToBizMsg(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList) {

        int i = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2180_ACMsg aBizMsg = bizMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(aBizMsg.cpoSvcPrcPk_A, (BigDecimal) rsltMap.get("CPO_SVC_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.t_MdlNm_A, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.mdlId_A, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgCd_A, (String) rsltMap.get("MAINT_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgNm_A, (String) rsltMap.get("PRC_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.maintFlPrcCatgCd_A, (String) rsltMap.get("MAINT_FL_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_A, (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK")); // QC#4631
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(aBizMsg.basePrcDealAmt_A, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(aBizMsg.basePrcDealAmt_A, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_A, (String) rsltMap.get("PRC_TIER_SVC_OFFER_CD"));
//            ZYPEZDItemValueSetter.setValue(aBizMsg.dealPrcListPrcAmt_A, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(aBizMsg.dealPrcListPrcAmt_A, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21862
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotQtyCnt_A, (BigDecimal) rsltMap.get("CNT"));

            ZYPEZDItemValueSetter.setValue(aBizMsg.scrEntAvalFlg_A, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            ZYPEZDItemValueSetter.setValue(aBizMsg.shpgIntvlMthNum_A, bizMsg.shpgIntvlMthNum);

            if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A)) {
                    ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, getPrcRateTpCd(bizMsg, aBizMsg));
                }
            }

            i++;
        }
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            bizMsg.A.setValidCount(1);
        } else {
            bizMsg.A.setValidCount(i);
        }

    }

    private static void setSvcPrcHdrConfigRsltToBizMsg(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList, NWAL2180SMsg glblMsg) {

        int ixR = 0;
        BigDecimal mdlId = BigDecimal.ONE.negate();
        int ixA = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2180_RCMsg rBizMsg = bizMsg.R.no(ixR);

            ZYPEZDItemValueSetter.setValue(rBizMsg.cpoSvcPrcPk_R, (BigDecimal) rsltMap.get("CPO_SVC_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.t_MdlNm_R, (String) rsltMap.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.mdlId_R, (BigDecimal) rsltMap.get("MDL_ID"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgCd_R, (String) rsltMap.get("MAINT_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, (String) rsltMap.get("PRC_CATG_NM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.maintFlPrcCatgCd_R, (String) rsltMap.get("MAINT_FL_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcMtrPkgPk_R, (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK")); // QC#4631
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(rBizMsg.basePrcDealAmt_R, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(rBizMsg.basePrcDealAmt_R, round((BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcTierSvcOfferCd_R, (String) rsltMap.get("PRC_TIER_SVC_OFFER_CD"));
//            ZYPEZDItemValueSetter.setValue(rBizMsg.dealPrcListPrcAmt_R, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.dealPrcListPrcAmt_R, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21862

            ZYPEZDItemValueSetter.setValue(rBizMsg.scrEntAvalFlg_R, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            ZYPEZDItemValueSetter.setValue(rBizMsg.shpgIntvlMthNum_R, bizMsg.shpgIntvlMthNum);

            if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R)) {
                ZYPEZDItemValueSetter.setValue(rBizMsg.prcRateTpCd_R, getPrcRateTpCd(bizMsg, rBizMsg));
            }
            ZYPEZDItemValueSetter.setValue(rBizMsg.cpoSvcConfigRefPk_R, (BigDecimal) rsltMap.get("CPO_SVC_CONFIG_REF_PK"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.dsOrdPosnNum_R, (String) rsltMap.get("DS_ORD_POSN_NUM"));

            ZYPEZDItemValueSetter.setValue(rBizMsg.billToCustCd_R, (String) rsltMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.dsAcctNm_R, (String) rsltMap.get("DS_ACCT_NM")); // 2017/03/29
            // S21_NA#18171
            // Mod
            ZYPEZDItemValueSetter.setValue(rBizMsg.billToLocNum_R, (String) rsltMap.get("BILL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, (String) rsltMap.get("PRC_LIST_TP_CD"));
            if (ZYPCommonFunc.hasValue(rBizMsg.billToLocNum_R)) {
                ZYPEZDItemValueSetter.setValue(rBizMsg.xxBillToAcctNmSrchTxt_R, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
            }
            bizMsg.xxNum_A.setValue(ixR);
            //
            if (!ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R)) {
                S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getDefaultMdlSvcPrc(bizMsg);
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (ssmResult.getQueryResultCount() > 0) {
                    Map<String, Object> resultMap = resList.get(0);
                    ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgCd_R, (String) resultMap.get("DEF_MAINT_PRC_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(rBizMsg.prcCatgNm_R, (String) resultMap.get("PRC_CATG_NM"));
                    ZYPEZDItemValueSetter.setValue(rBizMsg.prcListTpCd_R, (String) resultMap.get("PRC_LIST_TP_CD"));
                }
            }

            deriveMtrPkgPulldownForNonFleetConfig(bizMsg, glblMsg, ixR);

            ixR++;
            if (ZYPCommonFunc.hasValue(rBizMsg.mdlId_R) //
                    && mdlId.compareTo(rBizMsg.mdlId_R.getValue()) != 0) {
                EZDBMsg.copy(rBizMsg, "R", bizMsg.A.no(ixA), "A");
                EZDBMsg.copy(rBizMsg, "PR", bizMsg.A.no(ixA), "PB");
                EZDBMsg.copy(rBizMsg, "ER", bizMsg.A.no(ixA), "EB");
                EZDBMsg.copy(rBizMsg, "TR", bizMsg.A.no(ixA), "TB");
                mdlId = rBizMsg.mdlId_R.getValue();
                ixA++;
            }
        }
        bizMsg.R.setValidCount(ixR);
        bizMsg.A.setValidCount(ixA);

    }

    /**
     * getPrcRateTpCd
     * @param bizMsg NWAL2180CMsg
     * @param aBizMsg NWAL2180_ACMsg
     * @return prcRateTpCd
     */
    public static String getPrcRateTpCd(NWAL2180CMsg bizMsg, NWAL2180_ACMsg aBizMsg) {
        if (!ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {
            return "";
        }
        S21SsmEZDResult rslt = NWAL2180Query.getInstance().getPrcRateTpCd(bizMsg, aBizMsg);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        String prcRateTpCd = (String) rslt.getResultObject();

        return prcRateTpCd;
    }

    private static void setSvcPrcAcpRsltToBizMsg(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList) {
        if (rsltList.size() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_C, (String) rsltList.get(0).get("ADDL_CHRG_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_C, (String) rsltList.get(0).get("PRC_CATG_NM"));
        }
        int i = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            NWAL2180_CCMsg cBizMsg = bizMsg.C.no(i);

            ZYPEZDItemValueSetter.setValue(cBizMsg.dsImptSvcLineNum_C, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseCd_CU, (String) rsltMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseDescShortTxt_CU, (String) rsltMap.get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseCd_CI, (String) rsltMap.get("ADDL_CHRG_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.mdseDescShortTxt_CI, (String) rsltMap.get("ADDL_CHRG_MDSE_NM"));
            // Mod Start 2017/10/19 QC#21862
//            ZYPEZDItemValueSetter.setValue(cBizMsg.addlChrgPrcDealAmt_C, (BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"));
//            ZYPEZDItemValueSetter.setValue(cBizMsg.dealPrcListPrcAmt_C, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.addlChrgPrcDealAmt_C, round((BigDecimal) rsltMap.get("ADDL_CHRG_PRC_DEAL_AMT"), scale));
            ZYPEZDItemValueSetter.setValue(cBizMsg.dealPrcListPrcAmt_C, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
            // Mod End 2017/10/19 QC#21862
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoSvcAddlChrgPrcPk_C, (BigDecimal) rsltMap.get("CPO_SVC_ADDL_CHRG_PRC_PK"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoDtlLineNum_C, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.cpoDtlLineSubNum_C, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.addlChrgPrcCatgCd_C, (String) rsltMap.get("ADDL_CHRG_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.svcPrcCatgCd_C, (String) rsltMap.get("SVC_PRC_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.billWithEquipInvdFlg_C, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
            ZYPEZDItemValueSetter.setValue(cBizMsg.crRebilCd_C, (String) rsltMap.get("CR_REBIL_CD"));

            ZYPEZDItemValueSetter.setValue(cBizMsg.scrEntAvalFlg_C, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

            // Add Start 2018/05/08 QC#25030
            ZYPEZDItemValueSetter.setValue(cBizMsg.printDtlFlg_C, (String) rsltMap.get("PRINT_DTL_FLG"));
            // Add End 2018/05/08 QC#25030

            i++;
        }
        bizMsg.C.setValidCount(i);
    }

    private static void setSvcPrcAbpRsltToBizMsg(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int ixJ = 0;
        int ixB = 0;
        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (Map<String, Object> rsltMap : rsltList) {
            String svcPrcCatgCd = (String) rsltMap.get("SVC_PRC_CATG_CD");
            if (SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE.equals(svcPrcCatgCd)) {
                NWAL2180_JCMsg jBizMsg = bizMsg.J.no(ixJ);

                ZYPEZDItemValueSetter.setValue(jBizMsg.dsImptSvcLineNum_J, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.cpoSvcAddlBasePrcPk_J, (BigDecimal) rsltMap.get("CPO_SVC_ADDL_BASE_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.cpoDtlLineNum_J, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.cpoDtlLineSubNum_J, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcCatgCd_J, (String) rsltMap.get("ADDL_BASE_PRC_CATG_CD"));
                // Mod Start 2017/10/19 QC#21862
//                ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcDealAmt_J, (BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"));
//                ZYPEZDItemValueSetter.setValue(jBizMsg.dealPrcListPrcAmt_J, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcDealAmt_J, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"), scale));
                ZYPEZDItemValueSetter.setValue(jBizMsg.dealPrcListPrcAmt_J, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
                // Mod End 2017/10/19 QC#21862
                ZYPEZDItemValueSetter.setValue(jBizMsg.prcListEquipConfigNum_J, (BigDecimal) rsltMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.svcPrcCatgCd_J, (String) rsltMap.get("SVC_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.billWithEquipInvdFlg_J, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
                ZYPEZDItemValueSetter.setValue(jBizMsg.crRebilCd_J, (String) rsltMap.get("CR_REBIL_CD"));

                ZYPEZDItemValueSetter.setValue(jBizMsg.scrEntAvalFlg_J, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

                ixJ++;
            } else {
                NWAL2180_BCMsg bBizMsg = bizMsg.B.no(ixB);

                ZYPEZDItemValueSetter.setValue(bBizMsg.dsImptSvcLineNum_B, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.cpoSvcAddlBasePrcPk_B, (BigDecimal) rsltMap.get("CPO_SVC_ADDL_BASE_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineNum_B, (String) rsltMap.get("CPO_DTL_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.cpoDtlLineSubNum_B, (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcCatgCd_B, (String) rsltMap.get("ADDL_BASE_PRC_CATG_CD"));
                // Mod Start 2017/10/19 QC#21862
//                ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, (BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"));
//                ZYPEZDItemValueSetter.setValue(bBizMsg.dealPrcListPrcAmt_B, (BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcDealAmt_B, round((BigDecimal) rsltMap.get("ADDL_BASE_PRC_DEAL_AMT"), scale));
                ZYPEZDItemValueSetter.setValue(bBizMsg.dealPrcListPrcAmt_B, round((BigDecimal) rsltMap.get("DEAL_PRC_LIST_PRC_AMT"), scale));
                // Mod End 2017/10/19 QC#21862
                ZYPEZDItemValueSetter.setValue(bBizMsg.prcListEquipConfigNum_B, (BigDecimal) rsltMap.get("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.svcPrcCatgCd_B, (String) rsltMap.get("SVC_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.billWithEquipInvdFlg_B, (String) rsltMap.get("BILL_WITH_EQUIP_INVD_FLG"));
                ZYPEZDItemValueSetter.setValue(bBizMsg.crRebilCd_B, (String) rsltMap.get("CR_REBIL_CD"));

                ZYPEZDItemValueSetter.setValue(bBizMsg.scrEntAvalFlg_B, (String) rsltMap.get("SCR_ENT_AVAL_FLG"));

                ixB++;
            }
        }
        bizMsg.J.setValidCount(ixJ);
        bizMsg.B.setValidCount(ixB);
    }

    private static void setSvcPrcUsgRsltToBizMsg(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int ixZ = 0;
        int ixX = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            String prcSvcTierTpCd = (String) rsltMap.get("PRC_SVC_TIER_TP_CD");
            if (ZYPCommonFunc.hasValue(prcSvcTierTpCd)) {
                NWAL2180_XCMsg xBizMsg = bizMsg.X.no(ixX);

                ZYPEZDItemValueSetter.setValue(xBizMsg.mdlId_X, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.dsImptSvcUsgPrcPk_X, (BigDecimal) rsltMap.get("CPO_SVC_USG_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.bllgMtrLbCd_X, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.minCopyVolCnt_X, (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.maxCopyVolCnt_X, (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.xsMtrAmtRate_X, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpCd_X, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(xBizMsg.prcSvcTierTpDescTxt_X, (String) rsltMap.get("PRC_SVC_TIER_TP_DESC_TXT"));

                ixX++;

            } else {
                NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);

                ZYPEZDItemValueSetter.setValue(zBizMsg.xxSmryLineFlg_Z, ZYPConstant.FLG_ON_Y);

                ZYPEZDItemValueSetter.setValue(zBizMsg.mdlId_Z, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.dsImptSvcUsgPrcPk_Z, (BigDecimal) rsltMap.get("CPO_SVC_USG_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcCatgCd_Z, (String) rsltMap.get("MAINT_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcListBandDescTxt_Z, (String) rsltMap.get(PRC_LIST_BAND_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcBookMdseCd_Z, (String) rsltMap.get("PRC_BOOK_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgMtrLbCd_Z, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.usgMdseCd_Z, (String) rsltMap.get("USG_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.mlyCopyInclPrcQty_Z, (BigDecimal) rsltMap.get("COPY_INCL_PRC_QTY"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.regMtrLbCd_Z, (String) rsltMap.get("REG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.xsMtrAmtRate_Z, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.contrMtrMultRate_Z, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.prcSvcTierTpCd_Z, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.minCopyVolCnt_Z, (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.maxCopyVolCnt_Z, (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT"));
                // Add Start 2018/05/08 QC#25030
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgFreeCopyCnt_Z, (BigDecimal) rsltMap.get("BLLG_FREE_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgMinCnt_Z, (BigDecimal) rsltMap.get("BLLG_MIN_CNT"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgMinAmtRate_Z, (BigDecimal) rsltMap.get("BLLG_MIN_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(zBizMsg.bllgRollOverRatio_Z, (BigDecimal) rsltMap.get("BLLG_ROLL_OVER_RATIO"));
                // Add End 2018/05/08 QC#25030

                ixZ++;
            }

        }
        bizMsg.Z.setValidCount(ixZ);
        bizMsg.X.setValidCount(ixX);

    }

    private static void setSvcPrcUsgRsltToBizMsgConfig(NWAL2180CMsg bizMsg, List<Map<String, Object>> rsltList) {
        int ixZ = 0;
        int ixX = 0;
        int ixU = 0;
        int ixV = 0;

        Map<BigDecimal, BigDecimal> prcMap = new HashMap<BigDecimal, BigDecimal>(bizMsg.A.getValidCount());
        List<String> tierList = new ArrayList<String>(rsltList.size());
        List<String> tierWkList = new ArrayList<String>(rsltList.size());

        for (Map<String, Object> rsltMap : rsltList) {
            String prcSvcTierTpCd = (String) rsltMap.get("PRC_SVC_TIER_TP_CD");
            BigDecimal mdlId = (BigDecimal) rsltMap.get("MDL_ID");
            BigDecimal cpoSvcConfigRefPk = (BigDecimal) rsltMap.get("CPO_SVC_CONFIG_REF_PK");
            String dsOrdPosnNum = (String) rsltMap.get("DS_ORD_POSN_NUM");

            if (!prcMap.containsKey(mdlId)) {
                prcMap.put(mdlId, cpoSvcConfigRefPk);
            }
            BigDecimal pk = prcMap.get(mdlId);
            boolean isModelPricing = ZYPCommonFunc.hasValue(pk) //
                    && ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) //
                    && pk.compareTo(cpoSvcConfigRefPk) == 0;
            if (ZYPCommonFunc.hasValue(prcSvcTierTpCd)) {
                NWAL2180_VCMsg vBizMsg = bizMsg.V.no(ixV);

                ZYPEZDItemValueSetter.setValue(vBizMsg.mdlId_V, mdlId);
                ZYPEZDItemValueSetter.setValue(vBizMsg.dsImptSvcUsgPrcPk_V, (BigDecimal) rsltMap.get("CPO_SVC_USG_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.bllgMtrLbCd_V, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.minCopyVolCnt_V, (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.maxCopyVolCnt_V, (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.xsMtrAmtRate_V, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpCd_V, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.prcSvcTierTpDescTxt_V, (String) rsltMap.get("PRC_SVC_TIER_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(vBizMsg.cpoSvcConfigRefPk_V, cpoSvcConfigRefPk);
                ZYPEZDItemValueSetter.setValue(vBizMsg.dsOrdPosnNum_V, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(vBizMsg.actvFlg_V, (String) rsltMap.get("ACTV_FLG"));

                String tierKey = S21StringUtil.concatStrings(mdlId.toPlainString(), ",", dsOrdPosnNum, ",", vBizMsg.bllgMtrLbCd_V.getValue());
                if (tierWkList.contains(tierKey)) {
                    if (!tierList.contains(tierKey)) {
                        tierList.add(tierKey);
                    }
                } else {
                    tierWkList.add(tierKey);
                }
                if (isModelPricing) {
                    EZDBMsg.copy(vBizMsg, null, bizMsg.X.no(ixX), null);
                    ixX++;
                }
                ixV++;

            } else {
                NWAL2180_UCMsg uBizMsg = bizMsg.U.no(ixU);

                ZYPEZDItemValueSetter.setValue(uBizMsg.xxSmryLineFlg_U, ZYPConstant.FLG_ON_Y);

                ZYPEZDItemValueSetter.setValue(uBizMsg.mdlId_U, mdlId);
                ZYPEZDItemValueSetter.setValue(uBizMsg.dsImptSvcUsgPrcPk_U, (BigDecimal) rsltMap.get("CPO_SVC_USG_PRC_PK"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcCatgCd_U, (String) rsltMap.get("MAINT_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcListBandDescTxt_U, (String) rsltMap.get(PRC_LIST_BAND_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcBookMdseCd_U, (String) rsltMap.get("PRC_BOOK_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgMtrLbCd_U, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.usgMdseCd_U, (String) rsltMap.get("USG_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.mlyCopyInclPrcQty_U, (BigDecimal) rsltMap.get("COPY_INCL_PRC_QTY"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.regMtrLbCd_U, (String) rsltMap.get("REG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.xsMtrAmtRate_U, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.contrMtrMultRate_U, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.prcSvcTierTpCd_U, (String) rsltMap.get("PRC_SVC_TIER_TP_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.minCopyVolCnt_U, (BigDecimal) rsltMap.get("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.maxCopyVolCnt_U, (BigDecimal) rsltMap.get("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.actvFlg_U, (String) rsltMap.get("ACTV_FLG"));

                // Add Start 2018/05/08 QC#25030
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgFreeCopyCnt_U, (BigDecimal) rsltMap.get("BLLG_FREE_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgMinCnt_U, (BigDecimal) rsltMap.get("BLLG_MIN_CNT"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgMinAmtRate_U, (BigDecimal) rsltMap.get("BLLG_MIN_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.bllgRollOverRatio_U, (BigDecimal) rsltMap.get("BLLG_ROLL_OVER_RATIO"));
                // Add End 2018/05/08 QC#25030
                ZYPEZDItemValueSetter.setValue(uBizMsg.cpoSvcConfigRefPk_U, cpoSvcConfigRefPk);
                ZYPEZDItemValueSetter.setValue(uBizMsg.dsOrdPosnNum_U, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(uBizMsg.billToCustCd_U, (String) rsltMap.get("BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(uBizMsg.dsAcctNm_U, (String) rsltMap.get("DS_ACCT_NM")); // 2017/03/29
                // S21_NA#18171
                // Mod
                ZYPEZDItemValueSetter.setValue(uBizMsg.billToLocNum_U, (String) rsltMap.get("BILL_TO_LOC_NUM"));
                if (ZYPCommonFunc.hasValue(uBizMsg.billToLocNum_U)) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xxBillToAcctNmSrchTxt_U, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
                }

                if (isModelPricing) {
                    EZDBMsg.copy(uBizMsg, "U", bizMsg.Z.no(ixZ), "Z");
                    EZDBMsg.copy(uBizMsg, "UB", bizMsg.Z.no(ixZ), "ZB");
                    ixZ++;
                }
                ixU++;
            }
        }
        bizMsg.U.setValidCount(ixU);
        bizMsg.V.setValidCount(ixV);
        setTierEntry(bizMsg, tierList);

        bizMsg.Z.setValidCount(ixZ);
        bizMsg.X.setValidCount(ixX);

    }

    private static void setTierEntry(NWAL2180CMsg bizMsg, List<String> tierList) {
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NWAL2180_UCMsg uBizMsg = bizMsg.U.no(i);
            String tierKey = S21StringUtil.concatStrings(uBizMsg.mdlId_U.getValue(), ",", uBizMsg.dsOrdPosnNum_U.getValue(), ",", uBizMsg.bllgMtrLbCd_U.getValue());
            if (tierList.contains(tierKey)) {
                uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    private static void setCpoSvcDtlRsltToBizMsg(NWAL2180CMsg bizMsg, Map<String, Object> rsltMap) {

        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcPk, (BigDecimal) rsltMap.get("CPO_SVC_PK"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem50Txt, (String) rsltMap.get("REF_CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcDtlPk, (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsImptSvcLineNum, (BigDecimal) rsltMap.get("CPO_SVC_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsImptSvcMdseCd, (String) rsltMap.get("CPO_SVC_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcSvcContrTpCd, (String) rsltMap.get("PRC_SVC_CONTR_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcSvcPlnTpCd, (String) rsltMap.get("PRC_SVC_PLN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billWithEquipFlg, (String) rsltMap.get("BILL_WITH_EQUIP_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.fromPerMthNum, (BigDecimal) rsltMap.get("FROM_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.toPerMthNum, (BigDecimal) rsltMap.get("TO_PER_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgIntvlMthNum, (BigDecimal) rsltMap.get("TERM_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.fixTermInMthAot, (BigDecimal) rsltMap.get("FIX_TERM_IN_MTH_AOT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.maxUplftPct, (BigDecimal) rsltMap.get("MAX_UPLFT_PCT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcLineActCd, (String) rsltMap.get("CPO_SVC_LINE_ACT_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.baseBllgCycleCd, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.usgBllgCycleCd, (String) rsltMap.get("USG_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billByTpCd, (String) rsltMap.get("BILL_BY_TP_CD"));

        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm, (String) rsltMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum, (String) rsltMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, (String) rsltMap.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxBillToAcctNmSrchTxt, (String) rsltMap.get("BILL_TO_SRCH_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, (String) rsltMap.get("SOLD_TO_CUST_LOC_CD"));

        ZYPEZDItemValueSetter.setValue(bizMsg.shpgIntvlMthNum, (BigDecimal) rsltMap.get("TERM_MTH_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(bizMsg.addAsryFlg, (String) rsltMap.get("ADD_ASRY_FLG"));

        ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdFlg, (String) rsltMap.get("MAN_CONTR_OVRD_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdRsnCd, (String) rsltMap.get("MAN_CONTR_OVRD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.manContrOvrdCmntTxt, (String) rsltMap.get("MAN_CONTR_OVRD_CMNT_TXT"));

        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrNum, (String) rsltMap.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcAgmtVerNum, (String) rsltMap.get("CPO_SVC_AGMT_VER_NUM"));

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) rsltMap.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, (String) rsltMap.get("DS_ORD_CATG_CD"));

        ZYPEZDItemValueSetter.setValue(bizMsg.applyEquipBillToFlg, (String) rsltMap.get("APPLY_EQUIP_BILL_TO_FLG"));
        // Mod Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, (BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, round((BigDecimal) rsltMap.get("TOT_BASE_PRC_DEAL_AMT"), scale));
        // Mod End 2017/10/19 QC#21862

        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));
    }

    /**
     * @param glblMsg NWAL2180SMsg
     * @return if Contract Indicator is "Fleet" then return true.
     */
    public static boolean isFleet(NWAL2180SMsg glblMsg) {
        return isFleet(glblMsg.dsContrCatgCd.getValue());
    }

    /**
     * @param dsContrCatgCd dsContrCatgCd
     * @return if Contract Indicator is "Fleet" then return true.
     */
    public static boolean isFleet(String dsContrCatgCd) {
        return DS_CONTR_CATG.FLEET.equals(dsContrCatgCd);
    }

    private static boolean isSameValue(BigDecimal val1, BigDecimal val2) {
        if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return true;
        }
        return val1.compareTo(val2) == 0;
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param glblMsg NWAL2180SMsg
     * @param glblCmpyCd glblCmpyCd
     */
    public static void deriveDefaultMdlSvcPrcList(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getDefaultMdlSvcPrc(bizMsg);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (ssmResult.getQueryResultCount() == 0) {
            return;
        }
        Map<String, Object> resultMap = resList.get(0);
        String prcCatgCd = (String) resultMap.get("DEF_MAINT_PRC_CATG_CD");
        String prcCatgNm = (String) resultMap.get("PRC_CATG_NM");
        String prcListTpCd = (String) resultMap.get("PRC_LIST_TP_CD");

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                break;
            }
            NWAL2180_ACMsg aBizMsg = bizMsg.A.no(i);
            bizMsg.xxNum_A.setValue(i);

            if (!ZYPCommonFunc.hasValue(aBizMsg.mdlId_A)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(aBizMsg.prcCatgCd_A)) {
                continue;
            }

            ssmResult = NWAL2180Query.getInstance().getDefaultMdlSvcPrcByQlfy(bizMsg, aBizMsg);
            if (ssmResult.getQueryResultCount() == 1) {
                resList = (List<Map<String, Object>>) ssmResult.getResultObject();
                resultMap = resList.get(0);
                prcCatgCd = (String) resultMap.get("PRC_CATG_CD");
                prcCatgNm = (String) resultMap.get("PRC_CATG_NM");
                prcListTpCd = (String) resultMap.get("PRC_LIST_TP_CD");
            }

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgCd_A, prcCatgCd);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcCatgNm_A, prcCatgNm);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcListTpCd_A, prcListTpCd);

            if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
                deriveMtrPkgPulldownForFleet(bizMsg);
            }
        }

        if (bizMsg.J.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, bizMsg.J.no(0).addlBasePrcCatgCd_J);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, bizMsg.J.no(0).prcCatgNm_J);
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcCatgCd_A)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, bizMsg.A.no(0).prcCatgCd_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, bizMsg.A.no(0).prcCatgNm_A);
                return;
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_HJ, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm_HJ, prcCatgNm);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     */
    public static void deriveMtrPkgPulldownForFleet(NWAL2180CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
            // does not derive price. in case of fleet, there are no
            // price lists.
            return;
        }

        List<BigDecimal> mdlList = new ArrayList<BigDecimal>();
        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal mdl = bizMsg.I.no(i).mdlId_I.getValue();
            if (!mdlList.contains(mdl)) {
                mdlList.add(mdl);
            }
        }

        NWAL2180CommonLogic.createPrcMtrPkgPulldownFL(bizMsg, mdlList, bizMsg.glblCmpyCd.getValue());
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(0);
        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(0)) //
                && !ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(1))) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_A, aBizMsg.prcMtrPkgPk_KP.no(0).getValue());
            // QC#4631
            if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                NWAL2180CommonLogic.deriveBasePrcForNonMeter(bizMsg, aBizMsg, bizMsg.glblCmpyCd.getValue());
            }
        } else {
            aBizMsg.prcMtrPkgPk_A.clear();
        }

        deriveTieredPricing(aBizMsg);
        NWAL2180CommonLogic.getMtrLbFL(bizMsg);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param index selected line number.
     * @param glblCmpyCd
     * @param glblMsg
     */
    public static void deriveMtrPkgPulldownForNonFleet(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg, int index) {

        NWAL2180CommonLogic.createPrcMtrPkgPulldown(bizMsg, bizMsg.glblCmpyCd.getValue());
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(index);
        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(0)) //
                && !ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_KP.no(1))) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcMtrPkgPk_A, aBizMsg.prcMtrPkgPk_KP.no(0).getValue());
            // QC#4631
            if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
                NWAL2180CommonLogic.deriveBasePrcForNonMeter(bizMsg, aBizMsg, bizMsg.glblCmpyCd.getValue());
            }
        }

        deriveTieredPricing(aBizMsg);
        NWAL2180CommonLogic.getMtrLb(bizMsg, glblMsg);
    }

    public static void deriveMtrPkgPulldownForNonFleetConfig(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg, int index) {

        NWAL2180CommonLogic.createPrcMtrPkgPulldownConfig(bizMsg);
        if (isImport(bizMsg)) {
            return;
        }

    }

    /**
     * @param bizMsg
     * @param index
     */
    public static void deriveTieredPricing(NWAL2180_ACMsg aBizMsg) {
        // set Tiered Pricing
        S21SsmEZDResult ssmResult = NWAL2180Query.getInstance().getTierdByCatg(aBizMsg);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> tierList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (tierList != null && !tierList.isEmpty()) {
            Map<String, Object> resMap = tierList.get(0);
            String tierCd = (String) resMap.get(PRC_TIER_SVC_OFFER_CD);

            ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_A, tierCd);
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcTierSvcOfferCd_AB, tierCd);
        } else {
            aBizMsg.prcTierSvcOfferCd_A.clear();
            aBizMsg.prcTierSvcOfferCd_AB.clear();
        }
    }

    /**
     * @param bizMsg NWAL2180CMsg
     */
    public static void resetBizMsgBeforeInit(NWAL2180CMsg bizMsg) {
        String funcId = bizMsg.xxFuncId_UP.getValue();
        BigDecimal cpoSvcDtlPk = bizMsg.cpoSvcDtlPk.getValue();
        String xxPageCd = bizMsg.xxPageCd.getValue();

        bizMsg.clear();

        ZYPEZDItemValueSetter.setValue(bizMsg.xxFuncId_UP, funcId);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSvcDtlPk, cpoSvcDtlPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageCd, xxPageCd);
    }

    /**
     * @param bizMsg
     * @param ix
     * @param bBizMsg
     */
    public static void getPrcConfName(NWAL2180CMsg bizMsg) {

        int ix = bizMsg.xxNum_B.getValueInt();
        NWAL2180_BCMsg bBizMsg = bizMsg.B.no(ix);
        if (!ZYPCommonFunc.hasValue(bBizMsg.prcCatgNm_B)) {
            bBizMsg.prcListEquipConfigNm_B.clear();
            bBizMsg.prcListEquipConfigNum_B.clear();
            return;
        }

        S21SsmEZDResult ssmResult //
        = NWAL2180Query.getInstance().getPrcConfName(bizMsg, ix);
        if (!ssmResult.isCodeNotFound()) {
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(//
                        bBizMsg.prcListEquipConfigNum_B //
                        , (BigDecimal) resList.get(0).get("PRC_LIST_EQUIP_CONFIG_NUM"));
                ZYPEZDItemValueSetter.setValue(//
                        bBizMsg.prcListEquipConfigNm_B //
                        , (String) resList.get(0).get("PRC_LIST_EQUIP_CONFIG_NM"));
            }
        }
    }

    public static BigDecimal getPeriodicBase(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        if (isFleet(glblMsg)) {
            return null;
        }
        int ixA = bizMsg.xxNum_A.getValueInt();
        BigDecimal mdlId = bizMsg.A.no(ixA).mdlId_A.getValue();

        BigDecimal basePrc = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(i);
            if (mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) != 0) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(zBizMsg.prcListBandDescTxt_Z)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z)) {
                continue;
            }
            basePrc = basePrc.add(getBandBasePrc(bizMsg, i));
        }
        return basePrc;
    }

    public static BigDecimal getPeriodicBaseConfig(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        if (isFleet(glblMsg)) {
            return null;
        }
        int ixR = bizMsg.xxNum_A.getValueInt();
        BigDecimal mdlId = bizMsg.R.no(ixR).mdlId_R.getValue();
        BigDecimal cpoSvcConfigRefPk = bizMsg.R.no(ixR).cpoSvcConfigRefPk_R.getValue();

        BigDecimal basePrc = BigDecimal.ZERO;
        for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
            NWAL2180_UCMsg uBizMsg = bizMsg.U.no(ixU);
            if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0) {
                continue;
            }
            if (cpoSvcConfigRefPk.compareTo(uBizMsg.cpoSvcConfigRefPk_U.getValue()) != 0) {
                continue;
            }
            if (!ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U)) {
                continue;
            }
            basePrc = basePrc.add(getBandBasePrcConfig(bizMsg, ixU));
        }
        return basePrc;
    }

    private static BigDecimal getBandBasePrc(NWAL2180CMsg bizMsg, int ixZ) {
        S21SsmEZDResult ssmResult //
        = NWAL2180Query.getInstance().getBandBasePrc(bizMsg, ixZ);

        BigDecimal basePrc = BigDecimal.ZERO;
        if (!ssmResult.isCodeNotFound()) {
            basePrc = (BigDecimal) ssmResult.getResultObject();
        }
        if (ZYPCommonFunc.hasValue(basePrc)) {
            return basePrc;
        }
        return BigDecimal.ZERO;
    }

    private static BigDecimal getBandBasePrcConfig(NWAL2180CMsg bizMsg, int ixU) {
        S21SsmEZDResult ssmResult //
        = NWAL2180Query.getInstance().getBandBasePrcConfig(bizMsg, ixU);

        BigDecimal basePrc = BigDecimal.ZERO;
        if (!ssmResult.isCodeNotFound()) {
            basePrc = (BigDecimal) ssmResult.getResultObject();
        }
        if (ZYPCommonFunc.hasValue(basePrc)) {
            return basePrc;
        }
        return BigDecimal.ZERO;
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param glblMsg NWAL2180SMsg
     */
    public static void afterBandPopupProc(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        S21SsmEZDResult ssmResult = null;

        int aIndex = bizMsg.xxNum_A.getValueInt();

        // Set Total Price
        BigDecimal baseBllgCycle = NWAL2180CommonLogic.getBllgCycle(bizMsg);
        NWAL2180_ACMsg aBizMsg = bizMsg.A.no(aIndex);
        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

            BigDecimal periodicBase = NWAL2180CommonLogic.getPeriodicBase(bizMsg, glblMsg); // QC#10379

            if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal qty = bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue();
                BigDecimal term = bizMsg.shpgIntvlMthNum.getValue();

                if (!ZYPCommonFunc.hasValue(qty)) {
                    qty = BigDecimal.ZERO;
                }
                // Mod Start 2017/10/19 QC#21862
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
                int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
//                BigDecimal extendedBase = periodicBase.multiply(qty);
                BigDecimal extendedBase = multiply(periodicBase, qty, scale);
                BigDecimal totalBase = BigDecimal.ZERO;
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    //totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
//                    totalBase = getTotalBase_TB(extendedBase, term, baseBllgCycle);
                    totalBase = getTotalBase(extendedBase, term, baseBllgCycle, scale);
                }

//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB, periodicBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, extendedBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, totalBase);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_PB, round(periodicBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21862
            }
        }

        if (!PRC_LIST_TP.SERVICE_TIERS.equals(aBizMsg.prcListTpCd_A.getValue()) //
                && !ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
            return;
        }
        if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            ssmResult = NWAL2180Query.getInstance().getTierInfo_BandSearch(bizMsg);
        }

        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            return;
        }

        NWAL2180CommonLogic.setTierInfo(bizMsg, ssmResult, bizMsg.xxNum_Z.getValueInt(), aIndex);
    }

    /**
     * @param bizMsg NWAL2180CMsg
     * @param glblMsg NWAL2180SMsg
     */
    public static void afterBandPopupProcConfig(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        S21SsmEZDResult ssmResult = null;

        int ixR = bizMsg.xxNum_A.getValueInt();

        // Set Total Price
        BigDecimal baseBllgCycle = NWAL2180CommonLogic.getBllgCycle(bizMsg);
        NWAL2180_RCMsg rBizMsg = bizMsg.R.no(ixR);
        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

            BigDecimal periodicBase = NWAL2180CommonLogic.getPeriodicBaseConfig(bizMsg, glblMsg);

            if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(baseBllgCycle)) {

                BigDecimal term = bizMsg.shpgIntvlMthNum.getValue();

                BigDecimal extendedBase = periodicBase;
                BigDecimal totalBase = BigDecimal.ZERO;
                // Mod Start 2017/10/19 QC#21862
                GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
                int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
                if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                    totalBase = extendedBase;
                } else {
                    // Mod Start 2017/10/06 QC#21341
                    //totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
//                    totalBase = getTotalBase_TR(extendedBase, term, baseBllgCycle);
                    totalBase = getTotalBase(extendedBase, term, baseBllgCycle, scale);
                    // Mod End 2017/10/06 QC#21341
                }

//                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_PR, periodicBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_ER, extendedBase);
//                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_TR, totalBase);
                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_PR, round(periodicBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_ER, round(extendedBase, scale));
                ZYPEZDItemValueSetter.setValue(bizMsg.R.no(ixR).xxTotPrcAmt_TR, round(totalBase, scale));
                // Mod End 2017/10/19 QC#21862
            }
        }

        if (!PRC_LIST_TP.SERVICE_TIERS.equals(rBizMsg.prcListTpCd_R.getValue()) //
                && !ZYPCommonFunc.hasValue(rBizMsg.prcTierSvcOfferCd_R)) {
            return;
        }
        if (!DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            ssmResult = NWAL2180Query.getInstance().getTierInfo_BandSearchConfig(bizMsg);
        }

        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            return;
        }

        NWAL2180CommonLogic.setTierInfoConfig(bizMsg, ssmResult, bizMsg.xxNum_Z.getValueInt(), ixR);
    }

    public static String getPrcRateTpCd(NWAL2180CMsg bizMsg, NWAL2180_RCMsg rBizMsg) {
        if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
            return "";
        }
        S21SsmEZDResult rslt = NWAL2180Query.getInstance().getPrcRateTpCd(bizMsg, rBizMsg);
        if (rslt.isCodeNotFound()) {
            return "";
        }
        String prcRateTpCd = (String) rslt.getResultObject();

        return prcRateTpCd;
    }

    public static void copyConfigToModel(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        Map<BigDecimal, NWAL2180_ACMsg> defaultMap = new HashMap<BigDecimal, NWAL2180_ACMsg>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2180_ACMsg aBizMsg = new NWAL2180_ACMsg();
            EZDBMsg.copy(bizMsg.A.no(i), null, aBizMsg, null);
            defaultMap.put(aBizMsg.mdlId_A.getValue(), aBizMsg);
        }
        BigDecimal mdlId = BigDecimal.ONE.negate();
        int ixA = 0;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        int ixZ = 0;
        bizMsg.Z.clear();
        bizMsg.Z.setValidCount(0);
        int ixX = 0;
        bizMsg.X.clear();
        bizMsg.X.setValidCount(0);

        Map<BigDecimal, BigDecimal> modelMap = new HashMap<BigDecimal, BigDecimal>(bizMsg.R.getValidCount());
        for (int ixR = 0; ixR < bizMsg.R.getValidCount(); ixR++) {
            NWAL2180_RCMsg rBizMsg = bizMsg.R.no(ixR);
            if (!ZYPCommonFunc.hasValue(rBizMsg.mdlId_R)) {
                continue;
            }
            BigDecimal mdlIdR = rBizMsg.mdlId_R.getValue();
            if (!modelMap.containsKey(mdlIdR)) {
                modelMap.put(mdlIdR, BigDecimal.ZERO);
            }
            BigDecimal cnt = modelMap.get(mdlIdR);
            modelMap.put(mdlIdR, cnt.add(BigDecimal.ONE));

            if (mdlId.compareTo(mdlIdR) != 0) {
                NWAL2180_ACMsg aBizMsg = bizMsg.A.no(ixA);

                if (ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R)) {
                    EZDBMsg.copy(rBizMsg, "R", aBizMsg, "A");
                    EZDBMsg.copy(rBizMsg, "RL", aBizMsg, "KP");
                    EZDBMsg.copy(rBizMsg, "RL", aBizMsg, "VW");
                    EZDBMsg.copy(rBizMsg, "PR", aBizMsg, "PB");
                }

                aBizMsg.cpoSvcPrcPk_A.clear();
                ixA++;
                mdlId = mdlIdR;
                String dsOrdPosnNum = rBizMsg.dsOrdPosnNum_R.getValue();
                if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(rBizMsg.cpoSvcPrcPk_R)) {
                    for (int ixU = 0; ixU < bizMsg.U.getValidCount(); ixU++) {
                        if (mdlId.compareTo(bizMsg.U.no(ixU).mdlId_U.getValue()) == 0 //
                                && dsOrdPosnNum.equals(bizMsg.U.no(ixU).dsOrdPosnNum_U.getValue())) {
                            NWAL2180_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
                            EZDBMsg.copy(bizMsg.U.no(ixU), "U", zBizMsg, "Z");
                            EZDBMsg.copy(bizMsg.U.no(ixU), "UB", zBizMsg, "ZB");
                            zBizMsg.dsImptSvcUsgPrcPk_Z.clear();
                            ixZ++;
                        }
                    }

                    for (int ixV = 0; ixV < bizMsg.V.getValidCount(); ixV++) {
                        if (mdlId.compareTo(bizMsg.V.no(ixV).mdlId_V.getValue()) == 0 //
                                && dsOrdPosnNum.equals(bizMsg.V.no(ixV).dsOrdPosnNum_V.getValue())) {
                            NWAL2180_XCMsg xBizMsg = bizMsg.X.no(ixX);
                            EZDBMsg.copy(bizMsg.V.no(ixV), "V", xBizMsg, "X");
                            xBizMsg.dsImptSvcUsgPrcPk_X.clear();
                            ixX++;
                        }
                    }
                    bizMsg.Z.setValidCount(ixZ);
                    bizMsg.X.setValidCount(ixX);

                } else {
                    ixZ = bizMsg.Z.getValidCount();
                    ixX = bizMsg.X.getValidCount();
                }
            }
        }
        bizMsg.A.setValidCount(ixA);

        // Add Start 2017/10/19 QC#21862
        GLBL_CMPYTMsg glblCmpyTMsg = getGlblCmpy(bizMsg.glblCmpyCd.getValue());
        int scale = getDealCcyDigit(bizMsg.glblCmpyCd.getValue(), glblCmpyTMsg.stdCcyCd.getValue());
        // Add End 2017/10/19 QC#21862
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2180_ACMsg aBizMsg = bizMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotQtyCnt_A, modelMap.get(aBizMsg.mdlId_A.getValue()));

            BigDecimal baseBllgCycle = getBllgCycle(bizMsg);
            if (ZYPCommonFunc.hasValue(aBizMsg.xxTotPrcAmt_PB)) {
                // set Price
                BigDecimal periodicBase = aBizMsg.xxTotPrcAmt_PB.getValue();
                BigDecimal qty = aBizMsg.xxTotQtyCnt_A.getValue();
                if (ZYPCommonFunc.hasValue(baseBllgCycle)) {

                    BigDecimal term = bizMsg.shpgIntvlMthNum.getValue();
                    BigDecimal extendedBase = BigDecimal.ZERO;
                    BigDecimal totalBase = BigDecimal.ZERO;

                    if (ZYPCommonFunc.hasValue(periodicBase) && ZYPCommonFunc.hasValue(qty)) {
                        // Mod Start 2017/10/19 QC#21862
//                        extendedBase = periodicBase.multiply(qty);
                        extendedBase = multiply(periodicBase, qty, scale);
                        // Mod End 2017/10/19 QC#21862
                    }

                    if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bizMsg.baseBllgCycleCd.getValue())) {
                        totalBase = extendedBase;
                    } else {
                        // Mod Start 2017/10/06 QC#21341
                        //totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
                        // Mod Start 2017/10/19 QC#21862
//                        totalBase = getTotalBase_TB(extendedBase, term, baseBllgCycle);
                        totalBase = getTotalBase(extendedBase, term, baseBllgCycle, scale);
                        // Mod End 2017/10/19 QC#21862
                        // Mod End 2017/10/06 QC#21341
                    }

                    // Mod Start 2017/10/19 QC#21862
//                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, extendedBase);
//                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, totalBase);
                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_EB, round(extendedBase, scale));
                    ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_TB, round(totalBase, scale));
                    // Mod End 2017/10/19 QC#21862
                }
            }
        }
    }


    public static void setTierInfoInputted(NWAL2180CMsg bizMsg) {
        List<String> tierKeyList = new ArrayList<String>(bizMsg.X.getValidCount());
        List<String> tierWkList = new ArrayList<String>(bizMsg.X.getValidCount());
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {
            String tierKey = bizMsg.X.no(i).bllgMtrLbCd_X.getValue();
            if (tierWkList.contains(tierKey)) {
                tierKeyList.add(tierKey);
            } else {
                tierWkList.add(tierKey);
            }
        }

        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            String tierKey = bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue();
            if (tierKeyList.contains(tierKey)) {
                bizMsg.Z.no(i).xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                bizMsg.Z.no(i).xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_OFF_N);
            }
        }
    }

    // Mod Start 2017/10/06 QC#21341
    // Del Start 2017/10/19 QC#21862
//    /**
//     * @param extendedBase BigDecimal
//     * @param term BigDecimal
//     * @param baseBllgCycle BigDecimal
//     * @return BigDecimal
//     */
//    private static BigDecimal getTotalBase_TR(BigDecimal extendedBase, BigDecimal term, BigDecimal baseBllgCycle) {
//        return getTotalBase(extendedBase, term, baseBllgCycle);
//    }
//
//    /**
//     * @param extendedBase BigDecimal
//     * @param term BigDecimal
//     * @param baseBllgCycle BigDecimal
//     * @return BigDecimal
//     */
//    private static BigDecimal getTotalBase_TB(BigDecimal extendedBase, BigDecimal term, BigDecimal baseBllgCycle) {
//        return getTotalBase(extendedBase, term, baseBllgCycle);
//    }
    // Del End 2017/10/19 QC#21862

    /**
     * @param extendedBase BigDecimal
     * @param term BigDecimal
     * @param baseBllgCycle BigDecimal
     * @param scale int
     * @return BigDecimal
     */
    private static BigDecimal getTotalBase(BigDecimal extendedBase, BigDecimal term, BigDecimal baseBllgCycle, int scale) {
        BigDecimal totalBase = BigDecimal.ZERO;

        // Mod Start 2017/10/19 QC#21862
//        totalBase = extendedBase.multiply(term.divide(baseBllgCycle));
        totalBase = multiply(extendedBase, term.divide(baseBllgCycle, 4, BigDecimal.ROUND_HALF_UP), scale);
        // Mod End 2017/10/19 QC#21862

        return totalBase;
    }
    // Mod End 2017/10/06 QC#21341

    // Add Start 2017/10/19 QC#21862
    private static GLBL_CMPYTMsg getGlblCmpy(String glblCmpyCd) {
        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        inTMsg.glblCmpyCd.setValue(glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg;
    }

    private static int getDealCcyDigit(String glblCmpyCd, String dealCcyCd) {
        CCYTMsg ccyTMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, dealCcyCd);
        ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);
        if (ccyTMsg != null) {
            return ccyTMsg.aftDeclPntDigitNum.getValueInt();
        }
        return 0;
    }

    private static BigDecimal round(BigDecimal num, int digit) {
        if (num == null) {
            num = BigDecimal.ZERO;
        }
        return num.setScale(digit, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal multiply(BigDecimal num1, BigDecimal num2, int scale) {
        if (num1 == null || num2 == null) {
            return BigDecimal.ZERO;
        }
        return round(num1.multiply(num2), scale);
    }
    // Add End 2017/10/19 QC#21862
}
