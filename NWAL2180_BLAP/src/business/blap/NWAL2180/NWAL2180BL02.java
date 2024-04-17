/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2180;

import static business.blap.NWAL2180.constant.NWAL2180Constant.NWAM0705E;
import static business.blap.NWAL2180.constant.NWAL2180Constant.POP_UP_TIER_PRICING;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         T.Murai         Create          N/A
 * 2016/04/21   Fujitsu         M.Yamada        UPdate          QC#6660
 * 2016/05/17   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/05/17   Fujitsu         M.Yamada        Update          QC#4631
 * 2016/05/30   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/07/26   Fujitsu         M.Yamada        Update          QC#10379
 * 2016/09/07   Fujitsu         M.Yamada        Update          QC#13256
 * 2016/09/14   Fujitsu         M.Yamada        Update          QC#14513
 * 2016/09/14   Fujitsu         M.Yamada        Update          QC#14869
 * 2016/11/14   Fujitsu         M.Yamada        Update          QC#8613
 * 2016/12/20   Fujitsu         S.Iidaka        Update          QC#16141
 * 2017/03/01   Fujitsu         M.Yamada        Update          QC#17789
 * 2017/03/08   Fujitsu         S.Ohki          Update          QC#16752
 * 2017/03/13   Fujitsu         M.Yamada        Update          QC#17700
 * 2017/03/29   Fujitsu         S.Ohki          Update          QC#18171
 *</pre>
 */
public class NWAL2180BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;
            NWAL2180SMsg glblMsg = (NWAL2180SMsg) sMsg;

            if ("NWAL2180_INIT".equals(screenAplID)) {
                doProcess_NWAL2180_INIT(bizMsg, glblMsg);

            } else if ("NWAL2180_NWAL2160".equals(screenAplID)) {
                doProcess_NWAL2180_NWAL2160(bizMsg); // service tier
                                                     // pricing popup

            } else if ("NWAL2180_NWAL2190".equals(screenAplID)) {
                doProcess_NWAL2180_NWAL2190(bizMsg, glblMsg);

            } else if ("NWAL2180Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL2180Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_CMN_Return(bizMsg);

            } else if ("NWAL2180Scrn00_OpenWin_Supply_Agreement".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_OpenWin_Supply_Agreement(bizMsg);

            } else if ("NWAL2180Scrn00_Expand_Line".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_Expand_Line();

            } else if ("NWAL2180Scrn00_Expand_LineConfig".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_Expand_LineConfig();

            } else if ("NWAL2180Scrn00_Expand_ConfigPricing".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_Expand_ConfigPricing();

            } else if ("NWAL2180Scrn00_Collapse_Line".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_Collapse_Line();

            } else if ("NWAL2180Scrn00_Collapse_LineConfig".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_Collapse_LineConfig();

            } else if ("NWAL2180Scrn00_Collapse_ConfigPricing".equals(screenAplID)) {
                doProcess_NWAL2180Scrn00_Collapse_ConfigPricing();

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL2180_NWAL2190(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        doProcess_NWAL2180_INIT(bizMsg, glblMsg);
    }

    private void doProcess_NWAL2180Scrn00_Collapse_ConfigPricing() {
        // no operation
    }

    private void doProcess_NWAL2180Scrn00_Expand_ConfigPricing() {
        // no operation
    }

    private void doProcess_NWAL2180Scrn00_Collapse_Line() { // [-] -> [+]
        // no operation
    }

    private void doProcess_NWAL2180Scrn00_Collapse_LineConfig() { // [-] -> [+]
        // no operation
    }

    private void doProcess_NWAL2180Scrn00_Expand_Line() { // [+] -> [-]
        // no operation
    }

    private void doProcess_NWAL2180Scrn00_Expand_LineConfig() { // [+] -> [-]
        // no operation
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2180_INIT(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, ZYPDateUtil.getSalesDate());
        NWAL2180CommonLogic.createPulldownList(glblCmpyCd, ZYPDateUtil.getSalesDate(), bizMsg);

        boolean isResultNormal = true;

        NWAL2180CommonLogic.setInitData(bizMsg, getGlobalCompanyCode(), glblMsg);
        NWAL2180CommonLogic.setRentalOrderFlag(bizMsg);

        List<BigDecimal> modelList = new ArrayList<BigDecimal>();
        Map<BigDecimal, String> reOpenFlgMap = new HashMap<BigDecimal, String>();

        String prcCatgNm = NWAL2180CommonLogic.getPrcCatgNm(bizMsg.prcCatgCd_C.getValue(), glblCmpyCd);
        bizMsg.prcCatgNm_C.setValue(prcCatgNm);

        // Fleet
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {

            if (bizMsg.A.getValidCount() > 0) {
                // REOPEN
                isResultNormal = NWAL2180CommonLogic.setHeader(bizMsg, glblCmpyCd);

                // set Service Pricing Info
                isResultNormal = NWAL2180CommonLogic.setSvcPricingReOpenFL(bizMsg, glblCmpyCd) || isResultNormal;
                if (!isResultNormal) {
                    return;
                }
                // Set Accessory Charge
                for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                    prcCatgNm = NWAL2180CommonLogic.getPrcCatgNm(bizMsg.J.no(i).addlBasePrcCatgCd_J.getValue(), glblCmpyCd);
                    bizMsg.J.no(i).prcCatgNm_J.setValue(prcCatgNm);
                    NWAL2180CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "J");
                }
                // Set Rental Equip Accessory Charge
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    prcCatgNm = NWAL2180CommonLogic.getPrcCatgNm(bizMsg.B.no(i).addlBasePrcCatgCd_B.getValue(), glblCmpyCd);
                    bizMsg.B.no(i).prcCatgNm_B.setValue(prcCatgNm);
                    NWAL2180CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "B");
                }

                // Set Add Charge
                for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                    String mdseNm = NWAL2180CommonLogic.getMdseNm(bizMsg.C.no(i).mdseCd_CI.getValue(), glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_CI, mdseNm);
                    NWAL2180CommonLogic.setAddChrgUpdate(bizMsg, i, glblCmpyCd);
                }
            } else {
                // Fleet - init

                // Count Qty Each Model Map<ModelNm, qty>
                Map<BigDecimal, Integer> countQtyList = NWAL2180CommonLogic.countQtyI(bizMsg);

                // Header
                NWAL2180CommonLogic.setHeader(bizMsg, glblCmpyCd);
                // Service Pricing
                isResultNormal = NWAL2180CommonLogic.setSvcPrcInitFl(bizMsg, countQtyList, glblCmpyCd);
                if (!isResultNormal) {
                    return;
                }
            }
        } else {
            // Non Fleet

            for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
                BigDecimal modelId = bizMsg.I.no(i).mdlId_I.getValue();
                if (!ZYPCommonFunc.hasValue(modelId)) {
                    continue;
                }
                if (modelList.contains(modelId)) {
                    continue;
                }
                modelList.add(modelId);
                boolean reOpenFlg = false;
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {

                    if (modelId.compareTo(bizMsg.A.no(j).mdlId_A.getValue()) == 0) {
                        reOpenFlgMap.put(modelId, ZYPConstant.FLG_ON_Y);
                        reOpenFlg = true;
                        break;
                    }
                }
                if (!reOpenFlg) {
                    reOpenFlgMap.put(modelId, ZYPConstant.FLG_OFF_N);
                }
            }

            int ix = 0;
            for (BigDecimal mdlId : modelList) {
                //
                if (ZYPConstant.FLG_ON_Y.equals(reOpenFlgMap.get(mdlId))) {
                    // REOPEN
                    isResultNormal = NWAL2180CommonLogic.setHeader(bizMsg, glblCmpyCd);

                    // set Service Pricing Info
                    isResultNormal //
                    = NWAL2180CommonLogic.setSvcPricingReOpen(bizMsg, mdlId, glblCmpyCd) || isResultNormal;
                    if (!isResultNormal) {
                        return;
                    }
                } else {
                    // INIT

                    // Count Qty Each Model Map<ModelNm, qty>
                    Map<BigDecimal, Integer> countQtyList = NWAL2180CommonLogic.countQtyI(bizMsg);

                    // Header
                    NWAL2180CommonLogic.setHeader(bizMsg, glblCmpyCd);

                    // Service Pricing
                    isResultNormal = NWAL2180CommonLogic.setSvcPrcInit(bizMsg, countQtyList, mdlId, glblCmpyCd);
                    if (!isResultNormal) {
                        return;
                    }
                    for (int i = 0; i < countQtyList.get(mdlId); i++) {
                        NWAL2180CommonLogic.setSvcPrcInitByConfig(bizMsg, mdlId);
                    }
                }
                bizMsg.xxNum_A.setValue(ix++);
                NWAL2180CommonLogic.createPrcMtrPkgPulldown(bizMsg, glblCmpyCd);
            }

            // Set Accessory Charge
            for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                prcCatgNm = NWAL2180CommonLogic.getPrcCatgNm(bizMsg.J.no(i).addlBasePrcCatgCd_J.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).prcCatgNm_J, prcCatgNm);
                NWAL2180CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "J");
            }
            // Set Rental Equip Accessory Charge
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                prcCatgNm = NWAL2180CommonLogic.getPrcCatgNm(bizMsg.B.no(i).addlBasePrcCatgCd_B.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, prcCatgNm);
                NWAL2180CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "B");
            }

            // Set Add Charge
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                String mdseNm = NWAL2180CommonLogic.getMdseNm(bizMsg.C.no(i).mdseCd_CI.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_CI, mdseNm);
                NWAL2180CommonLogic.setAddChrgUpdate(bizMsg, i, glblCmpyCd);
            }

        }

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            NWAL2180CommonLogic.deriveDefaultMdlSvcPrcList(bizMsg, glblMsg, getGlobalCompanyCode());
        }
        if (bizMsg.Z.getValidCount() > 0 && NWAL2180CommonLogic.isFleet(bizMsg.dsContrCatgCd.getValue())) {
            NWAL2180CommonLogic.reOrdUsg(bizMsg);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcTierSvcOfferCd_A)) {
                NWAL2180CommonLogic.setTierInfoInputted(bizMsg);
            }
        }
        if (!NWAL2180CommonLogic.isImport(bizMsg) && bizMsg.U.getValidCount() > 0) {
            NWAL2180CommonLogic.reOrdUsgConfig(bizMsg);
        }
        if (bizMsg.R.getValidCount() > 0) {
            NWAL2180CommonLogic.copyConfigToModel(bizMsg, glblMsg);
        }

        bizMsg.setCommitSMsg(true);

        if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
            bizMsg.U.setValidCount(0);
            bizMsg.V.setValidCount(0);
        }

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue()) //
                && bizMsg.B.getValidCount() > 0) {
            bizMsg.B.clear();
            bizMsg.B.setValidCount(0);
        }
    }

    private void doProcess_NWAL2180_NWAL2160(NWAL2180CMsg bizMsg) {

        if (POP_UP_TIER_PRICING.equals(bizMsg.xxPopPrm_P2.getValue())) {
            NWAL2180CommonLogic.reOrdUsg(bizMsg);

        } else if (POP_UP_TIER_PRICING.equals(bizMsg.xxPopPrm_P2.getValue())) {
            NWAL2180CommonLogic.reOrdUsgConfig(bizMsg);

        }
    }

    /**
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NWAL2180Scrn00_CMN_Reset(NWAL2180CMsg bizMsg, NWAL2180SMsg glblMsg) {
        List<String> flgList = new ArrayList<String>(bizMsg.A.getValidCount());
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            flgList.add(bizMsg.A.no(i).xxSmryLineFlg_A.getValue());
        }
        NWAL2180CommonLogic.resetBizMsgBeforeInit(bizMsg);
        doProcess_NWAL2180_INIT(bizMsg, glblMsg);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_A, flgList.get(i));
        }
    }

    /**
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2180Scrn00_CMN_Return(NWAL2180CMsg bizMsg) {
        return; // no operation.
    }

    /**
     * @param bizMsg Business Msg
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NWAL2180Scrn00_OpenWin_Supply_Agreement(NWAL2180CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        ssmResult = NWAL2180Query.getInstance().getPrcListStruForAupplyAgreement(bizMsg, index, getGlobalCompanyCode());

        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(NWAM0705E, new String[] {"Service Price List" });
            return;
        } else {
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                String stru = (String) resList.get(0).get("PRC_LIST_STRU_TP_CD");

                if (!PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(stru)) {
                    bizMsg.setMessageInfo(NWAM0705E, new String[] {"Service Price List" });
                    return;
                }
            }
        }
    }

}
