/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1330;

import static business.blap.NSAL1330.constant.NSAL1330Constant.MAX_LINE;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0112E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0646E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0647E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0650E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0668W;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0676E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.NSAM0680E;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_ACTIVE_METER;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_CHARGE_ITEM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_BAND_LIST;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_BAND_LIST_CONFIG;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_COVERED_ITEM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_RENTAL_EQUIP_COVERED_ITEM;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AC_P;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_AC;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_RE;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_RE_P;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SP;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_SVC_PRC_SPC;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_TIER_PRICING;
import static business.blap.NSAL1330.constant.NSAL1330Constant.POP_UP_TIER_PRICING_CONFIG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1330.common.NSAL1330CommonLogic;
import business.db.SELL_TO_CUSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/12   Hitachi         A.Kohinata      Update          QC#18935
 * 2017/08/04   Hitachi         Y.Takeno        Update          QC#20443
 * 2017/10/03   Hitachi         Y.Takeno        Update          QC#20059
 * 2018/03/08   Hitachi         T.Tomita        Update          QC#24624
 * 2018/03/27   Hitachi         K.Kojima        Update          QC#24929
 * 2018/03/13   CITS            M.Naito         Update          QC#23378
 * 2018/07/06   Fujitsu         K.Ishizuka      Update          QC#26528
 * 2018/07/10   Hitachi         K.Kojima        Update          QC#27135
 * 2018/08/24   Fujitsu         K.Ishizuka      Update          QC#25105
 * 2018/10/10   Hitachi         K.Kojima        Update          QC#28715
 * 2018/12/11   Hitachi         T.Tomita        Update          QC#29423
 * 2018/12/19   Fujitsu         M.Yamada        Update          QC#29248
 * 2019/09/25   Fujitsu         K.Kato          Update          QC#51323
 *</pre>
 */
public class NSAL1330BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;
            NSAL1330SMsg glblMsg = (NSAL1330SMsg) sMsg;

            if ("NSAL1330_INIT".equals(screenAplID)) {
                doProcess_NSAL1330_INIT(bizMsg, glblMsg);

            } else if ("NSAL1330_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL1330_NWAL1130(bizMsg); // common popup

            } else if ("NSAL1330_NWAL1760".equals(screenAplID)) {
                doProcess_NSAL1330_NWAL1760(bizMsg, glblMsg); // price list popup

            } else if ("NSAL1330_NSAL1340".equals(screenAplID)) {
                doProcess_NSAL1330_NSAL1340(bizMsg, glblMsg); // band list popup

            } else if ("NSAL1330_NSAL1370".equals(screenAplID)) {
                doProcess_NSAL1330_NSAL1370(bizMsg); // service tier pricing popup

            } else if ("NSAL1330_NSAL1380".equals(screenAplID)) {
                doProcess_NSAL1330_NSAL1380(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_Add_Accessory".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Add_Accessory(bizMsg);

            } else if ("NSAL1330Scrn00_Add_RentalEquip".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Add_RentalEquip(bizMsg);

            } else if ("NSAL1330Scrn00_Add_Service".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Add_Service(bizMsg);

                //            } else if ("NSAL1330Scrn00_Add_Tier".equals(screenAplID)) {
                //                doProcess_NSAL1330Scrn00_Add_Tier(bizMsg);

                //            } else if ("NSAL1330Scrn00_Apply_Bundle_Price".equals(screenAplID)) {
                //                doProcess_NSAL1330Scrn00_Apply_Bundle_Price(bizMsg);

            } else if ("NSAL1330Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Delete(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Return(bizMsg);

            } else if ("NSAL1330Scrn00_Del_Accessory".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Del_Accessory(bizMsg);

            } else if ("NSAL1330Scrn00_Del_RentalEquip".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Del_RentalEquip(bizMsg);

            } else if ("NSAL1330Scrn00_Del_Service".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Del_Service(bizMsg);

            } else if ("NSAL1330Scrn00_Del_ActiveMeter".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Del_ActiveMeter(bizMsg);

            } else if ("NSAL1330Scrn00_Item_Desc_Additional_Charge_Item".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Item_Desc_Additional_Item(bizMsg);

            } else if ("NSAL1330Scrn00_Item_Desc_Covered_Item".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Item_Desc_Covered_Item(bizMsg);

            } else if ("NSAL1330Scrn00_Item_Desc_Covered_Item_RentalEquip".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Item_Desc_Covered_Item_RentalEquip(bizMsg);

            } else if ("NSAL1330Scrn00_Item_Desc_Covered_Unit".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Item_Desc_Covered_Unit(bizMsg);

            } else if ("NSAL1330Scrn00_OpenWin_Supply_Agreement".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OpenWin_Supply_Agreement(bizMsg);

            } else if ("NSAL1330Scrn00_OnChange_Band".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_Band(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnChange_BandConfig".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_BandConfig(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnChange_SvcPkg".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_SvcPkg(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnChange_SvcPkg_forConfigPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_SvcPkgForConfigPricing(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnChange_Tierd".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_Tierd(bizMsg);

            } else if ("NSAL1330Scrn00_OnChange_Tiered_forConfigPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_Tierd_forConfigPricing(bizMsg);

                //            } else if ("NSAL1330Scrn00_Refresh".equals(screenAplID)) {
                //                doProcess_NSAL1330Scrn00_Refresh(bizMsg);

            } else if ("NSAL1330Scrn00_Expand_Line".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Expand_Line();

            } else if ("NSAL1330Scrn00_Expand_LineConfig".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Expand_LineConfig();

            } else if ("NSAL1330Scrn00_Expand_ConfigPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Expand_ConfigPricing();

            } else if ("NSAL1330Scrn00_Collapse_Line".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Collapse_Line();

            } else if ("NSAL1330Scrn00_Collapse_LineConfig".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Collapse_LineConfig();

            } else if ("NSAL1330Scrn00_Collapse_ConfigPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Collapse_ConfigPricing();

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_RentalEquip".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_RentalEquip(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg_P".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg_P(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_RentalEquip_P".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_RentalEquip_P(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_OnBlur_ServicePriceList_AddlChrg".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_AddlChrg(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_CoveredItem".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_CoveredItem(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_CoveredItem_RentalEquip".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_CoveredItem_RentalEquip(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_CoveredUnit".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_CoveredUnit(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_AddlChrgItem".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_AddlChrgItem(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNmConfig".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNmConfig(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumConfig".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumConfig(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_DeriveFromCustLocCdConfig".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_DeriveFromCustLocCdConfig(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNmMeter".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNmMeter(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumMeter".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumMeter(bizMsg);

            } else if ("NSAL1330Scrn00_OnBlur_DeriveFromCustLocCdMeter".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnBlur_DeriveFromCustLocCdMeter(bizMsg);

            } else if ("NSAL1330Scrn00_OnChange_SvcPkgForConfigPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OnChange_SvcPkgForConfigPricing(bizMsg, glblMsg);

            } else if ("NSAL1330Scrn00_ApplyAllBillToFromHeader".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_ApplyAllBillToFromHeader(bizMsg);

            } else if ("NSAL1330Scrn00_ApplyAllFromModel".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_ApplyAllFromModel(bizMsg);

            // START 2017/10/03 [QC#20059, ADD]
            } else if ("NSAL1330Scrn00_Toggle_ServicePriceSettingLevel".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_Toggle_ServicePriceSettingLevel(bizMsg);

            // END   2017/10/03 [QC#20059, ADD]
            // Add Start 2018/03/08 QC#24624
            } else if ("NSAL1330Scrn00_OpenWin_Supply_Agreement_forModelPricing".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_CMN_Save(bizMsg, glblMsg);
            // Add End 2018/03/08 QC#24624
            // START 2018/10/10 K.Kojima [QC#28715,ADD]
            } else if ("NSAL1330Scrn00_OpenWin_Item_Search_Additional_Charge_Item".equals(screenAplID)) {
                doProcess_NSAL1330Scrn00_OpenWin_Item_Search_Additional_Charge_Item(bizMsg);
            // END 2018/10/10 K.Kojima [QC#28715,ADD]
            } else {
                return;
            }

            if (!"NSAL1330Scrn00_CMN_Delete".equals(screenAplID)) {
                bizMsg.delFlg_W.clear();
            }
            if (!"NSAL1330Scrn00_CMN_Save".equals(screenAplID)) {
                bizMsg.submtFlg_W.clear();
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1330_NSAL1380(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        // START 2018/03/27 K.Kojima [QC#24929,ADD]
        // 2019/09/25 QC#51323 Mod Start
        //List<String> flgList = new ArrayList<String>(bizMsg.A.getValidCount());
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    flgList.add(bizMsg.A.no(i).xxSmryLineFlg_A.getValue());
        //}
        Map<BigDecimal, String> flgMap = NSAL1330CommonLogic.getSmryLineFlgMap(bizMsg);
        // 2019/09/25 QC#51323 Mod End
        NSAL1330CommonLogic.resetBizMsgBeforeInit(bizMsg);
        // END 2018/03/27 K.Kojima [QC#24929,ADD]
        doProcess_NSAL1330_INIT(bizMsg, glblMsg);
        // START 2018/03/27 K.Kojima [QC#24929,ADD]
        // 2019/09/25 QC#51323 Mod Start
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_A, flgList.get(i));
        //}
        NSAL1330CommonLogic.setSmryLineFlgMap(bizMsg, flgMap);
        // 2019/09/25 QC#51323 Mod End
        // END 2018/03/27 K.Kojima [QC#24929,ADD]
    }

    private void doProcess_NSAL1330Scrn00_Del_ActiveMeter(NSAL1330CMsg bizMsg) {
        NSAL1330_UCMsg uBizMsgWk = bizMsg.U.no(bizMsg.xxNum_A.getValueInt());
        BigDecimal mdlId = uBizMsgWk.mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = uBizMsgWk.cpoSvcConfigRefPk_U.getValue();
        BigDecimal dsContrDtlPk = uBizMsgWk.dsContrDtlPk_U.getValue();
        String bllgMtrLbCd = uBizMsgWk.bllgMtrLbCd_U.getValue();

        Map<String, Integer> bllgMtrMap = new HashMap<String, Integer>(bizMsg.U.getValidCount());
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(uBizMsg.actvFlg_U.getValue()) //
                    || mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0 //
//                    || cpoSvcConfigRefPk.compareTo(uBizMsg.cpoSvcConfigRefPk_U.getValue()) != 0) {
                    || dsContrDtlPk.compareTo(uBizMsg.dsContrDtlPk_U.getValue()) != 0) {
                continue;
            }
            if (!bllgMtrMap.containsKey(uBizMsg.bllgMtrLbCd_U.getValue())) {
                bllgMtrMap.put(uBizMsg.bllgMtrLbCd_U.getValue(), i);
            }
        }
        if (bllgMtrMap.size() == 1) {
            int ix = bllgMtrMap.get(bllgMtrLbCd);
            bizMsg.U.no(ix).prcListBandDescTxt_U.setErrorInfo(1, NSAM0676E);
            return;
        }

        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(i);
            if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0 //
//                    || cpoSvcConfigRefPk.compareTo(uBizMsg.cpoSvcConfigRefPk_U.getValue()) != 0 //
                    || dsContrDtlPk.compareTo(uBizMsg.dsContrDtlPk_U.getValue()) != 0 //
                    || !bllgMtrLbCd.equals(uBizMsg.bllgMtrLbCd_U.getValue())) {
                continue;
            }
            uBizMsg.actvFlg_U.setValue(ZYPConstant.FLG_OFF_N);
        }

        for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
            NSAL1330_VCMsg vBizMsg = bizMsg.V.no(i);
            if (mdlId.compareTo(vBizMsg.mdlId_V.getValue()) != 0 //
//                    || cpoSvcConfigRefPk.compareTo(vBizMsg.cpoSvcConfigRefPk_V.getValue()) != 0 //
                    || dsContrDtlPk.compareTo(vBizMsg.dsContrDtlPk_V.getValue()) != 0 //
                    || !bllgMtrLbCd.equals(vBizMsg.bllgMtrLbCd_V.getValue())) {
                continue;
            }
            vBizMsg.actvFlg_V.setValue(ZYPConstant.FLG_OFF_N);
        }
    }

    private void doProcess_NSAL1330Scrn00_Collapse_ConfigPricing() {
        // no operation
    }

    private void doProcess_NSAL1330Scrn00_ApplyAllFromModel(NSAL1330CMsg bizMsg) {
        NSAL1330CommonLogic.applyAllFromModel(bizMsg);
    }


    private void doProcess_NSAL1330Scrn00_ApplyAllBillToFromHeader(NSAL1330CMsg bizMsg) {
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.R.no(i).billToCustCd_R, bizMsg.dsAcctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.R.no(i).dsAcctNm_R, bizMsg.dsAcctNm); // 2017/03/29 S21_NA#18171 Mod
            ZYPEZDItemValueSetter.setValue(bizMsg.R.no(i).billToLocNum_R, bizMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.R.no(i).xxGenlFldAreaTxt_R, bizMsg.xxGenlFldAreaTxt_BI);
        }
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i).billToCustCd_U, bizMsg.dsAcctNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i).dsAcctNm_U, bizMsg.dsAcctNm); // 2017/03/29 S21_NA#18171 Mod
            ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i).billToLocNum_U, bizMsg.soldToCustLocCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.U.no(i).xxGenlFldAreaTxt_U, bizMsg.xxGenlFldAreaTxt_BI);
        }
    }

    private void doProcess_NSAL1330Scrn00_Expand_ConfigPricing() {
        // no operation
    }

    private void doProcess_NSAL1330Scrn00_OnChange_Band(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixZ = bizMsg.xxNum_Z.getValueInt();
        NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(ixZ);
        String prcListBandDescTxt = zBizMsg.prcListBandDescTxt_Z.getValue();
        if (ZYPCommonFunc.hasValue(zBizMsg.prcListBandDescTxt_Z)) {
            List<String> bandList = NSAL1330CommonLogic.getPrcListBandCdList(prcListBandDescTxt);
            if (bandList == null || bandList.size() == 0) {
                zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0647E //
                        , new String[] {"PRC_LIST_BAND", "Band : " + prcListBandDescTxt });
                return;
            }
            if (bandList.size() > 1) {
                zBizMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0680E //
                        , new String[] {"PRC_LIST_BAND", "Band", prcListBandDescTxt });
                return;
            }
        }

        NSAL1330_ACMsg aBizMsg = null;
        BigDecimal mdlId = zBizMsg.mdlId_Z.getValue();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(mdlId)) {
                break;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).mdlId_A) //
                    && mdlId.compareTo(bizMsg.A.no(i).mdlId_A.getValue()) == 0) {
                aBizMsg = bizMsg.A.no(i);
                break;
            }
        }
        if (aBizMsg != null //
                && ZYPCommonFunc.hasValue(prcListBandDescTxt)) {
            if (!NSAL1330CommonLogic.deriveDefaultBand(bizMsg, aBizMsg, zBizMsg, prcListBandDescTxt)) {
                return;
            }
        }
        doProcess_NSAL1330_NSAL1340(bizMsg, glblMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnChange_BandConfig(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ixU = bizMsg.xxNum_Z.getValueInt();
        NSAL1330_UCMsg uBizMsg = bizMsg.U.no(ixU);
        String prcListBandDescTxt = uBizMsg.prcListBandDescTxt_U.getValue();
        if (ZYPCommonFunc.hasValue(uBizMsg.prcListBandDescTxt_U)) {
            List<String> bandList = NSAL1330CommonLogic.getPrcListBandCdList(prcListBandDescTxt);
            if (bandList == null || bandList.size() == 0) {
                uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0647E //
                        , new String[] {"PRC_LIST_BAND", "Band : " + prcListBandDescTxt });
                return;
            }
            if (bandList.size() > 1) {
                uBizMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0680E //
                        , new String[] {"PRC_LIST_BAND", "Band", prcListBandDescTxt });
                return;
            }
        }

        NSAL1330_RCMsg rBizMsg = null;
        BigDecimal mdlId = uBizMsg.mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = uBizMsg.cpoSvcConfigRefPk_U.getValue();
        BigDecimal dsContrDtlPk = uBizMsg.dsContrDtlPk_U.getValue();
        for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(mdlId)) {
                break;
            }
            if (ZYPCommonFunc.hasValue(bizMsg.R.no(i).mdlId_R) //
                    && mdlId.compareTo(bizMsg.R.no(i).mdlId_R.getValue()) == 0 //
//                    && ZYPCommonFunc.hasValue(bizMsg.R.no(i).cpoSvcConfigRefPk_R) //
//                    && cpoSvcConfigRefPk.compareTo(bizMsg.R.no(i).cpoSvcConfigRefPk_R.getValue()) == 0) {
                    && ZYPCommonFunc.hasValue(bizMsg.R.no(i).dsContrDtlPk_R) //
                    && dsContrDtlPk.compareTo(bizMsg.R.no(i).dsContrDtlPk_R.getValue()) == 0) {
                rBizMsg = bizMsg.R.no(i);
                break;
            }
        }
        if (rBizMsg != null //
                && ZYPCommonFunc.hasValue(prcListBandDescTxt)) {
            if (!NSAL1330CommonLogic.deriveDefaultBandConfig(bizMsg, rBizMsg, uBizMsg, prcListBandDescTxt)) {
                return;
            }
        }
        doProcess_NSAL1330_NSAL1340(bizMsg, glblMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_AddlChrgItem(NSAL1330CMsg bizMsg) {
        int ix = bizMsg.xxNum_C.getValueInt();
        NSAL1330_CCMsg cBizMsg = bizMsg.C.no(ix);
        NSAL1330CommonLogic.deriveFromAddlChrgItemCd(cBizMsg.mdseCd_CI, bizMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_CoveredUnit(NSAL1330CMsg bizMsg) {
        int ix = bizMsg.xxNum_C.getValueInt();
        NSAL1330_CCMsg cBizMsg = bizMsg.C.no(ix);
        NSAL1330CommonLogic.deriveFromCoveredUnitItemCd(cBizMsg.mdseCd_CU, bizMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_CoveredItem(NSAL1330CMsg bizMsg) {
        int ix = bizMsg.xxNum_B.getValueInt();
        NSAL1330_JCMsg jBizMsg = bizMsg.J.no(ix);
        NSAL1330CommonLogic.deriveFromCoveredItemItemCd(jBizMsg.mdseCd_J, bizMsg, "J");
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_CoveredItem_RentalEquip(NSAL1330CMsg bizMsg) {
        int ix = bizMsg.xxNum_B.getValueInt();
        NSAL1330_BCMsg bBizMsg = bizMsg.B.no(ix);
        NSAL1330CommonLogic.deriveFromCoveredItemItemCd(bBizMsg.mdseCd_B, bizMsg, "B");

        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgNm_HB) //
                && !ZYPCommonFunc.hasValue(bBizMsg.prcCatgNm_B)) {
            ZYPEZDItemValueSetter.setValue(bBizMsg.addlBasePrcCatgCd_B, bizMsg.prcCatgCd_HB);
            ZYPEZDItemValueSetter.setValue(bBizMsg.prcCatgNm_B, bizMsg.prcCatgNm_HB);
        }
        NSAL1330CommonLogic.getPrcConfName(bizMsg);
        NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_AddlChrg(NSAL1330CMsg bizMsg) {
        NSAL1330CommonLogic.deriveFromAddlChrgPrcCatgNm(bizMsg.prcCatgNm_C, bizMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg_P(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ix = bizMsg.xxNum_B.getValueInt();
        NSAL1330_JCMsg jBizMsg = bizMsg.J.no(ix);
        NSAL1330CommonLogic.deriveFromAsryChrgPPrcCatgNm(jBizMsg.prcCatgNm_J, bizMsg, PRC_CTX_TP.SERVICE_PRICING, "J");

        bizMsg.xxNum_A.setValue(ix);
        bizMsg.xxPopPrm_P1.setValue(POP_UP_SVC_PRC_AC_P);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg.getValue())) {
            return;
        }
        doProcess_NSAL1330_NWAL1760(bizMsg, glblMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_RentalEquip_P(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ix = bizMsg.xxNum_B.getValueInt();
        NSAL1330_BCMsg aBizMsg = bizMsg.B.no(ix);
        NSAL1330CommonLogic.deriveFromAsryChrgPPrcCatgNm(aBizMsg.prcCatgNm_B, bizMsg, PRC_CTX_TP.SALES_PRICING, "B");

        bizMsg.xxNum_A.setValue(ix);
        bizMsg.xxPopPrm_P1.setValue(POP_UP_SVC_PRC_RE_P);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxRqstFlg.getValue())) {
            return;
        }
        doProcess_NSAL1330_NWAL1760(bizMsg, glblMsg);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_AsryChrg(NSAL1330CMsg bizMsg) {
        NSAL1330CommonLogic.deriveFromAsryChrgPrcCatgNm(//
                bizMsg.prcCatgNm_HJ, bizMsg, "J", PRC_CTX_TP.SERVICE_PRICING, getGlobalCompanyCode());
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_RentalEquip(NSAL1330CMsg bizMsg) {
        NSAL1330CommonLogic.deriveFromAsryChrgPrcCatgNm(//
                bizMsg.prcCatgNm_HB, bizMsg, "B", PRC_CTX_TP.SALES_PRICING, getGlobalCompanyCode());
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ix = bizMsg.xxNum_A.getValueInt();
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ix);
        NSAL1330CommonLogic.deriveFromMdlPrcCatgNm(aBizMsg.prcCatgNm_A, bizMsg, glblMsg, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A)) {
            doProcess_NSAL1330Scrn00_OnChange_SvcPkg(bizMsg, glblMsg);
        }

    }

    private void doProcess_NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ix = bizMsg.xxNum_A.getValueInt();
        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ix);
        NSAL1330CommonLogic.deriveFromMdlPrcCatgNmConfig(rBizMsg.prcCatgNm_R, bizMsg, glblMsg, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R)) {
            doProcess_NSAL1330Scrn00_OnChange_SvcPkgForConfigPricing(bizMsg, glblMsg);
        }

    }

    private void doProcess_NSAL1330Scrn00_CMN_Delete(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.delFlg_W.getValue())) {
            // 2019/09/25 QC#51323 Mod Start
            //List<String> flgList = new ArrayList<String>(bizMsg.A.getValidCount());
            //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            //    flgList.add(bizMsg.A.no(i).xxSmryLineFlg_A.getValue());
            //}
            Map<BigDecimal, String> flgMap = NSAL1330CommonLogic.getSmryLineFlgMap(bizMsg);
            // 2019/09/25 QC#51323 Mod End
            NSAL1330CommonLogic.resetBizMsgBeforeInit(bizMsg);
            bizMsg.delFlg_W.clear();
            doProcess_NSAL1330_INIT(bizMsg, glblMsg);
            // 2019/09/25 QC#51323 Mod Start
            //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            //    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_A, flgList.get(i));
            //}
            NSAL1330CommonLogic.setSmryLineFlgMap(bizMsg, flgMap);
            // 2019/09/25 QC#51323 Mod End
        } else {
            bizMsg.setMessageInfo(NSAM0668W);
            ZYPEZDItemValueSetter.setValue(bizMsg.delFlg_W, ZYPConstant.FLG_ON_Y);
        }

    }

    private void doProcess_NSAL1330Scrn00_CMN_Save(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.submtFlg_W.getValue())) {
            return;
        }
        // 2019/09/25 QC#51323 Mod Start
        //List<String> flgList = new ArrayList<String>(bizMsg.A.getValidCount());
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    flgList.add(bizMsg.A.no(i).xxSmryLineFlg_A.getValue());
        //}
        Map<BigDecimal, String> flgMap = NSAL1330CommonLogic.getSmryLineFlgMap(bizMsg);
        // 2019/09/25 QC#51323 Mod End
        NSAL1330CommonLogic.resetBizMsgBeforeInit(bizMsg);
        doProcess_NSAL1330_INIT(bizMsg, glblMsg);
        // 2019/09/25 QC#51323 Mod Start
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_A, flgList.get(i));
        //}
        NSAL1330CommonLogic.setSmryLineFlgMap(bizMsg, flgMap);
        // 2019/09/25 QC#51323 Mod End
    }

    private void doProcess_NSAL1330Scrn00_Collapse_Line() { //[-] -> [+]
        // no operation
    }

    private void doProcess_NSAL1330Scrn00_Collapse_LineConfig() { //[-] -> [+]
        // no operation
    }

    private void doProcess_NSAL1330Scrn00_Expand_Line() { //[+] -> [-]
        // no operation
    }

    private void doProcess_NSAL1330Scrn00_Expand_LineConfig() { //[+] -> [-]
        // no operation
    }

    private void doProcess_NSAL1330Scrn00_OnChange_Tierd(NSAL1330CMsg bizMsg) {
        boolean isFleet = DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue());
        BigDecimal mdlId = BigDecimal.ZERO;

        int ixA = bizMsg.xxNum_A.getValueInt();
        if (!isFleet) {
            mdlId = bizMsg.A.no(ixA).mdlId_A.getValue();
        }
        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
            NSAL1330_ZCMsg zBizMsg = bizMsg.Z.no(i);
            if (isFleet || mdlId.compareTo(zBizMsg.mdlId_Z.getValue()) == 0) {
                //                if (ZYPCommonFunc.hasValue(zBizMsg.usgMdseCd_Z)) {
                if (!ZYPCommonFunc.hasValue(zBizMsg.regMtrLbCd_Z) //
                        && !ZYPCommonFunc.hasValue(zBizMsg.prcSvcTierTpCd_Z)) {
                    zBizMsg.mlyCopyInclPrcQty_Z.clear();
                    zBizMsg.xsMtrAmtRate_Z.clear();
                    zBizMsg.xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_OFF_N);

                    NSAL1330CommonLogic.deleteTierInfo(bizMsg, zBizMsg, isFleet);
                }
                // 2018/07/06 S21_NA#26528 Add Start
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(ixA).prcTierSvcOfferCd_A)) {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.xxScrEdtTpCd_Z, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(zBizMsg.xxScrEdtTpCd_Z, ZYPConstant.FLG_OFF_N);
                }
                // 2018/07/06 S21_NA#26528 Add End
            }
        }
    }

    private void doProcess_NSAL1330Scrn00_OnChange_Tierd_forConfigPricing(NSAL1330CMsg bizMsg) {
        boolean isFleet = DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue());
        BigDecimal mdlId = BigDecimal.ZERO;
        BigDecimal dsContrDtlPk = BigDecimal.ZERO;

        int ixR = bizMsg.xxNum_A.getValueInt();
        if (!isFleet) {
            mdlId = bizMsg.R.no(ixR).mdlId_R.getValue();
//            cpoSvcConfigRefPk = bizMsg.R.no(ixR).cpoSvcConfigRefPk_R.getValue();
            dsContrDtlPk = bizMsg.R.no(ixR).dsContrDtlPk_R.getValue();
        }
        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NSAL1330_UCMsg uBizMsg = bizMsg.U.no(i);
            if (isFleet || (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) == 0 //
                    && dsContrDtlPk.compareTo(uBizMsg.dsContrDtlPk_U.getValue()) == 0)) {
                if (!ZYPCommonFunc.hasValue(uBizMsg.regMtrLbCd_U) //
                        && !ZYPCommonFunc.hasValue(uBizMsg.prcSvcTierTpCd_U)) {
                    uBizMsg.mlyCopyInclPrcQty_U.clear();
                    uBizMsg.xsMtrAmtRate_U.clear();
                    uBizMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_OFF_N);

                    NSAL1330CommonLogic.deleteTierInfo(bizMsg, uBizMsg);
                }
                // 2018/07/06 S21_NA#26528 Add Start
                if (ZYPCommonFunc.hasValue(bizMsg.R.no(ixR).prcTierSvcOfferCd_R)) {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xxScrEdtTpCd_U, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(uBizMsg.xxScrEdtTpCd_U, ZYPConstant.FLG_OFF_N);
                }
                // 2018/07/06 S21_NA#26528 Add End
            }
        }
    }

    private void doProcess_NSAL1330Scrn00_OnChange_SvcPkg(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        int ix = bizMsg.xxNum_A.getValueInt();
        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(ix);
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
            if (PRC_LIST_TP.SERVICE_TIERS.equals(aBizMsg.prcListTpCd_A.getValue()) //
                    && !ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
                NSAL1330CommonLogic.deriveTieredPricing(aBizMsg);
            }
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, NSAL1330CommonLogic.getPrcRateTpCd(bizMsg, aBizMsg)); // QC#29248
            NSAL1330CommonLogic.getMtrLbFL(bizMsg);
        } else {
            if (!ZYPCommonFunc.hasValue(aBizMsg.prcMtrPkgPk_A)) {
                aBizMsg.prcRateTpCd_A.clear();
                aBizMsg.xxTotPrcAmt_PB.clear();
                aBizMsg.prcTierSvcOfferCd_A.clear();
                return;
            }
            ZYPEZDItemValueSetter.setValue(aBizMsg.prcRateTpCd_A, NSAL1330CommonLogic.getPrcRateTpCd(bizMsg, aBizMsg));
            if (PRC_RATE_TP.ANNUAL.equals(aBizMsg.prcRateTpCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(aBizMsg.xxTotPrcAmt_PB, NSAL1330CommonLogic.getBasePrcForNonMeter(bizMsg, aBizMsg));
                aBizMsg.prcTierSvcOfferCd_A.clear();

            } else if (PRC_LIST_TP.SERVICE_TIERS.equals(aBizMsg.prcListTpCd_A.getValue()) //
                    && !ZYPCommonFunc.hasValue(aBizMsg.prcTierSvcOfferCd_A)) {
                NSAL1330CommonLogic.deriveTieredPricing(aBizMsg);
            }

            NSAL1330CommonLogic.getMtrLb(bizMsg, glblMsg);

        }
        //        NSAL1330CommonLogic.setUsgInfoFromGlblMsgToBizMsg(glblMsg, bizMsg);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg
     * @param glblMsg Global Msg
     */
    private void doProcess_NSAL1330_INIT(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
        // START 2018/07/10 K.Kojima [QC#27135,ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());
        // END 2018/07/10 K.Kojima [QC#27135,ADD]
        // START 2018/07/10 K.Kojima [QC#27135,MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, ZYPDateUtil.getSalesDate());
        // NSAL1330CommonLogic.createPulldownList(glblCmpyCd, ZYPDateUtil.getSalesDate(), bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt, bizMsg.slsDt);
        NSAL1330CommonLogic.createPulldownList(glblCmpyCd, bizMsg.slsDt.getValue(), bizMsg);
        // END 2018/07/10 K.Kojima [QC#27135,MOD]

        //        ZYPCodeDataUtil.createPulldownList("PRC_SVC_TIER_TP", bizMsg.prcSvcTierTpCd_L, bizMsg.prcSvcTierTpDescTxt_L);

        // if (NSAL1330CommonLogic.getFloorPrcList(bizMsg, glblCmpyCd)) {
        // return;
        // }

        boolean isResultNormal = true;

        NSAL1330CommonLogic.setInitData(bizMsg, getGlobalCompanyCode(), glblMsg);
        // Del Start 2018/12/11 QC#29423
        // NSAL1330CommonLogic.setRentalOrderFlag(bizMsg);
        // Del End 2018/12/11 QC#29423
        List<BigDecimal> modelList = new ArrayList<BigDecimal>();
        Map<BigDecimal, String> reOpenFlgMap = new HashMap<BigDecimal, String>();

        //        boolean isUsgBllgCycleEmpty = NSAL1330CommonLogic.isUsgBllgCycleEmpty(bizMsg, glblCmpyCd);
        String prcCatgNm = NSAL1330CommonLogic.getPrcCatgNm(bizMsg.prcCatgCd_C.getValue(), glblCmpyCd);
        bizMsg.prcCatgNm_C.setValue(prcCatgNm);

        // Fleet
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {

            if (bizMsg.A.getValidCount() > 0) {
                // REOPEN
                isResultNormal = NSAL1330CommonLogic.setHeader(bizMsg, glblCmpyCd);

                // set Service Pricing Info
                isResultNormal = NSAL1330CommonLogic.setSvcPricingReOpenFL(bizMsg, glblCmpyCd) || isResultNormal;
                if (!isResultNormal) {
                    return;
                }
                // Set Accessory Charge
                for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                    prcCatgNm = NSAL1330CommonLogic.getPrcCatgNm(bizMsg.J.no(i).addlBasePrcCatgCd_J.getValue(), glblCmpyCd);
                    bizMsg.J.no(i).prcCatgNm_J.setValue(prcCatgNm);
                    NSAL1330CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "J");
                }
                // Set Rental Equip Accessory Charge
                for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                    prcCatgNm = NSAL1330CommonLogic.getPrcCatgNm(bizMsg.B.no(i).addlBasePrcCatgCd_B.getValue(), glblCmpyCd);
                    bizMsg.B.no(i).prcCatgNm_B.setValue(prcCatgNm);
                    NSAL1330CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "B");
                    //                    NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, i, glblCmpyCd);
                }
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
                    if (bizMsg.B.getValidCount() == 0) {
                        NSAL1330CommonLogic.setDefaultHeaderRentalEqPrcList(bizMsg);
                    }

                    isResultNormal = NSAL1330CommonLogic.setAcsryChrgInitForRental(bizMsg, glblCmpyCd);
                    if (!isResultNormal) {
                        return;
                    }
                }

                // QC#28398
//                // Set Add Charge
//                for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//                    String mdseNm = NSAL1330CommonLogic.getMdseNm(bizMsg.C.no(i).mdseCd_CI.getValue(), glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_CI, mdseNm);
//                    NSAL1330CommonLogic.setAddChrgUpdate(bizMsg, i, glblCmpyCd);
//                }
            } else {
                // Fleet - init

                // Count Qty Each Model Map<ModelNm, qty>
                Map<BigDecimal, Integer> countQtyList = NSAL1330CommonLogic.countQtyI(bizMsg);

                // Header
                NSAL1330CommonLogic.setHeader(bizMsg, glblCmpyCd);
                // Service Pricing
                isResultNormal = NSAL1330CommonLogic.setSvcPrcInitFl(bizMsg, countQtyList, glblCmpyCd);
                if (!isResultNormal) {
                    return;
                }
                // Accessory Charge
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
                    NSAL1330CommonLogic.setDefaultHeaderRentalEqPrcList(bizMsg);
                    if (bizMsg.B.getValidCount() == 0) {
                        isResultNormal = NSAL1330CommonLogic.setAcsryChrgInitForRental(bizMsg, glblCmpyCd);
                        if (!isResultNormal) {
                            return;
                        }
                    }
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
                    isResultNormal = NSAL1330CommonLogic.setHeader(bizMsg, glblCmpyCd);

                    // set Service Pricing Info
                    isResultNormal //
                    = NSAL1330CommonLogic.setSvcPricingReOpen(bizMsg, mdlId, glblCmpyCd) || isResultNormal;
                    if (!isResultNormal) {
                        return;
                    }
                    // START 2017/10/10 [QC#20059, ADD]
                    BigDecimal baseBllgCycle = NSAL1330CommonLogic.getBllgCycle(bizMsg);
                    for (int i = 0; i < bizMsg.R.getValidCount(); i++) {
                        NSAL1330CommonLogic.setSvcPricingReOpenConfig(bizMsg, bizMsg.R.no(i), glblCmpyCd, baseBllgCycle);
                    }
                    // END   2017/10/10 [QC#20059, ADD]

                } else {
                    // INIT

                    // Count Qty Each Model Map<ModelNm, qty>
                    Map<BigDecimal, Integer> countQtyList = NSAL1330CommonLogic.countQtyI(bizMsg);

                    // Header
                    NSAL1330CommonLogic.setHeader(bizMsg, glblCmpyCd);

                    // Service Pricing
                    isResultNormal = NSAL1330CommonLogic.setSvcPrcInit(bizMsg, countQtyList, mdlId, glblCmpyCd);
                    if (!isResultNormal) {
                        return;
                    }
                }
                bizMsg.xxNum_A.setValue(ix++);
                NSAL1330CommonLogic.createPrcMtrPkgPulldown(bizMsg, glblCmpyCd);
            }

            // Set Accessory Charge
            for (int i = 0; i < bizMsg.J.getValidCount(); i++) {
                prcCatgNm = NSAL1330CommonLogic.getPrcCatgNm(bizMsg.J.no(i).addlBasePrcCatgCd_J.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.J.no(i).prcCatgNm_J, prcCatgNm);
                NSAL1330CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "J");
            }
            // Set Rental Equip Accessory Charge
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                prcCatgNm = NSAL1330CommonLogic.getPrcCatgNm(bizMsg.B.no(i).addlBasePrcCatgCd_B.getValue(), glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, prcCatgNm);
                NSAL1330CommonLogic.setAcsryChrgUpdate(bizMsg, i, glblCmpyCd, "B");
                //                NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, i, glblCmpyCd);
            }

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue())) {
                NSAL1330CommonLogic.setDefaultHeaderRentalEqPrcList(bizMsg);
                //                if (bizMsg.B.getValidCount() == 0) {
                isResultNormal = NSAL1330CommonLogic.setAcsryChrgInitForRental(bizMsg, glblCmpyCd);
                if (!isResultNormal) {
                    return;
                }
                //                }
            }

            // QC#28398
//            // Set Add Charge
//            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
//                String mdseNm = NSAL1330CommonLogic.getMdseNm(bizMsg.C.no(i).mdseCd_CI.getValue(), glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).mdseDescShortTxt_CI, mdseNm);
//                NSAL1330CommonLogic.setAddChrgUpdate(bizMsg, i, glblCmpyCd);
//            }

        }

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            NSAL1330CommonLogic.deriveDefaultMdlSvcPrcList(bizMsg, glblMsg, getGlobalCompanyCode());
        }
//        if (bizMsg.Z.getValidCount() > 0 && NSAL1330CommonLogic.isFleet(bizMsg.dsContrCatgCd.getValue())) {
        if (bizMsg.Z.getValidCount() > 0) {
            NSAL1330CommonLogic.reOrdUsg(bizMsg);
//            if(ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcTierSvcOfferCd_A)){
//                NSAL1330CommonLogic.setTierInfoInputted(bizMsg);
//            }
            NSAL1330CommonLogic.setTierInfoInputted(bizMsg);
        }
        if (!NSAL1330CommonLogic.isImport(bizMsg) && bizMsg.U.getValidCount() > 0) {
            NSAL1330CommonLogic.reOrdUsgConfig(bizMsg);
        }
        if (bizMsg.R.getValidCount() > 0 && bizMsg.A.getValidCount() == 0) {
            NSAL1330CommonLogic.copyConfigToModel(bizMsg, glblMsg);
        }

        bizMsg.setCommitSMsg(true);
        EZDMsg.copy(bizMsg, null, glblMsg, null); // back up of initial value.

        if (!ZYPCommonFunc.hasValue(bizMsg.usgBllgCycleCd)) {
            bizMsg.Z.setValidCount(0);
            bizMsg.X.setValidCount(0);

            bizMsg.U.setValidCount(0);
            bizMsg.V.setValidCount(0);
        }

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.rntlOrdFlg.getValue()) //
                && bizMsg.B.getValidCount() > 0) {
            bizMsg.B.clear();
            bizMsg.B.setValidCount(0);
        }
    }

    /**
     * after Band List Popup closed
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330_NSAL1340(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        if (POP_UP_BAND_LIST.equals(bizMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CommonLogic.afterBandPopupProc(bizMsg, glblMsg);

        } else if (POP_UP_BAND_LIST_CONFIG.equals(bizMsg.xxPopPrm_P1.getValue())) {
            NSAL1330CommonLogic.afterBandPopupProcConfig(bizMsg, glblMsg);
        }
    }

    private void doProcess_NSAL1330_NSAL1370(NSAL1330CMsg bizMsg) {

        if (POP_UP_TIER_PRICING.equals(bizMsg.xxPopPrm_P2.getValue())) {
            NSAL1330CommonLogic.reOrdUsg(bizMsg);

        } else if (POP_UP_TIER_PRICING_CONFIG.equals(bizMsg.xxPopPrm_P2.getValue())) {
            NSAL1330CommonLogic.reOrdUsgConfig(bizMsg);

        }
    }

    /**
     * ITEM DESC COVERED ITEM Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330_NWAL1130(NSAL1330CMsg bizMsg) {

        if (POP_UP_COVERED_ITEM.equals(bizMsg.xxPopPrm_P1.getValue())) {

            boolean resultFlg //
            = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SERVICE_PRICING);
            if (!resultFlg) {
                return;
            }

        } else if (POP_UP_RENTAL_EQUIP_COVERED_ITEM.equals(bizMsg.xxPopPrm_P1.getValue())) {

            boolean resultFlg = NSAL1330CommonLogic.acsryDuplicateCheck(bizMsg);
            if (!resultFlg) {
                return;
            }
            //            int bindex = bizMsg.xxNum_A.getValueInt();
            //            if (ZYPCommonFunc.hasValue(bizMsg.B.no(bindex).addlBasePrcCatgCd_B) //
            //                    && ZYPCommonFunc.hasValue(bizMsg.B.no(bindex).mdseCd_B)) {
            //
            //                NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, bindex, getGlobalCompanyCode());
            //            } else {
            //                return;
            //            }
            //            boolean resultFlg //
            //            = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SALES_PRICING);
            //            if (!resultFlg) {
            //                return;
            //            }

        } else if (POP_UP_ADD_CHARGE_ITEM.equals(bizMsg.xxPopPrm_P1.getValue())) {
            boolean resultFlg //
            = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SPECIAL_CHARGE);
            if (!resultFlg) {
                return;
            }

        } else if (POP_UP_ADD_ACTIVE_METER.equals(bizMsg.xxPopPrm_P1.getValue())) {
            NSAL1330_RCMsg rBizMsgWk = bizMsg.R.no(bizMsg.xxNum_A.getValueInt());
            BigDecimal mdlId = rBizMsgWk.mdlId_R.getValue();
//            BigDecimal cpoSvcConfigRefPk = rBizMsgWk.cpoSvcConfigRefPk_R.getValue();
            BigDecimal dsContrDtlPk = rBizMsgWk.dsContrDtlPk_R.getValue();
            String bllgMtrLbCd = bizMsg.P.no(0).xxComnScrColValTxt_P.getValue();

            for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
                NSAL1330_UCMsg uBizMsg = bizMsg.U.no(i);
                if (mdlId.compareTo(uBizMsg.mdlId_U.getValue()) != 0 //
                        || dsContrDtlPk.compareTo(uBizMsg.dsContrDtlPk_U.getValue()) != 0 //
                        || !bllgMtrLbCd.equals(uBizMsg.bllgMtrLbCd_U.getValue())) {
                    continue;
                }
                uBizMsg.actvFlg_U.setValue(ZYPConstant.FLG_ON_Y);
            }

            for (int i = 0; i < bizMsg.V.getValidCount(); i++) {
                NSAL1330_VCMsg vBizMsg = bizMsg.V.no(i);
                if (mdlId.compareTo(vBizMsg.mdlId_V.getValue()) != 0 //
                        || dsContrDtlPk.compareTo(vBizMsg.dsContrDtlPk_V.getValue()) != 0 //
                        || !bllgMtrLbCd.equals(vBizMsg.bllgMtrLbCd_V.getValue())) {
                    continue;
                }
                vBizMsg.actvFlg_V.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
    }

    /**
     * ITEM DESC COVERED ITEM Event
     * @param bizMsg Business Msg
     * @param glblMsg global msg
     */
    private void doProcess_NSAL1330_NWAL1760(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {

        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {

            if (POP_UP_SVC_PRC_SP.equals(bizMsg.xxPopPrm_P1.getValue())) {
                NSAL1330CommonLogic.deriveMtrPkgPulldownForFleet(bizMsg);

            } else if (POP_UP_SVC_PRC_RE_P.equals(bizMsg.xxPopPrm_P1.getValue())) {

                NSAL1330CommonLogic.getPrcConfName(bizMsg);
                NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);

                //                int index = bizMsg.xxNum_A.getValueInt();

                //                if (ZYPCommonFunc.hasValue(bizMsg.B.no(index).addlBasePrcCatgCd_B) //
                //                        && ZYPCommonFunc.hasValue(bizMsg.B.no(index).mdseCd_B)) {
                //
                //                    NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, index, getGlobalCompanyCode());
                //                }
                //                boolean resultFlg //
                //                = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SALES_PRICING);
                //                if (!resultFlg) {
                //                    return;
                //                }
            } else if (POP_UP_SVC_PRC_RE.equals(bizMsg.xxPopPrm_P1.getValue())) {
                // 2018/08/24 S21_NA#27840 Mod Start
                // for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                //     ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).addlBasePrcCatgCd_B, bizMsg.prcCatgCd_HB);
                //     ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, bizMsg.prcCatgNm_HB);

                //    ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, new BigDecimal(i));
                //    NSAL1330CommonLogic.getPrcConfName(bizMsg);

                    //                    if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).addlBasePrcCatgCd_B) //
                    //                            && ZYPCommonFunc.hasValue(bizMsg.B.no(i).mdseCd_B)) {
                    //
                    //                        NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, i, getGlobalCompanyCode());
                    //                    }
                // }
                NSAL1330CommonLogic.getPrcConfName(bizMsg);
                // 2018/08/24 S21_NA#27840 Mod End
                NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);

            }
        } else {

            int index = bizMsg.xxNum_A.getValueInt();
            // xxPopPrm_P1 : pop up title name
            if (POP_UP_SVC_PRC_SP.equals(bizMsg.xxPopPrm_P1.getValue())) {
                NSAL1330CommonLogic.deriveMtrPkgPulldownForNonFleet(bizMsg, glblMsg, index);
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).prcMtrPkgPk_A)) {
                    doProcess_NSAL1330Scrn00_OnChange_SvcPkg(bizMsg, glblMsg);
                }

            } else if (POP_UP_SVC_PRC_SPC.equals(bizMsg.xxPopPrm_P1.getValue())) {
                NSAL1330CommonLogic.deriveMtrPkgPulldownForNonFleetConfig(bizMsg, glblMsg, index);
                if (ZYPCommonFunc.hasValue(bizMsg.R.no(index).prcMtrPkgPk_R)) {
                    doProcess_NSAL1330Scrn00_OnChange_SvcPkgForConfigPricing(bizMsg, glblMsg);
                }

            // START 2017/08/04 [QC#20443, ADD]
            } else if (POP_UP_SVC_PRC_AC.equals(bizMsg.xxPopPrm_P1.getValue())) {
                NSAL1330CommonLogic.deriveFromAsryChrgPrcCatgNm(//
                        bizMsg.prcCatgNm_HJ, bizMsg, "J", PRC_CTX_TP.SERVICE_PRICING, getGlobalCompanyCode());

            // END   2017/08/04 [QC#20443, ADD]
            } else if (POP_UP_SVC_PRC_AC_P.equals(bizMsg.xxPopPrm_P1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, bizMsg.xxNum_A);
                BigDecimal basePrc //
                = NSAL1330CommonLogic.getPeriodicServicePriceForAccesoryCharge(bizMsg);
                if (ZYPCommonFunc.hasValue(basePrc)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).addlBasePrcDealAmt_J, basePrc);
                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).addlBasePrcDealAmt_BJ, basePrc);
                    ZYPEZDItemValueSetter.setValue(bizMsg.J.no(index).dealPrcListPrcAmt_J, basePrc);
                }

            } else if (POP_UP_SVC_PRC_RE_P.equals(bizMsg.xxPopPrm_P1.getValue())) {

                NSAL1330CommonLogic.getPrcConfName(bizMsg);
                NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);

                //                if (ZYPCommonFunc.hasValue(bizMsg.B.no(index).addlBasePrcCatgCd_B) //
                //                        && ZYPCommonFunc.hasValue(bizMsg.B.no(index).mdseCd_B)) {
                //
                //                    NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, index, getGlobalCompanyCode());
                //                }
                //                boolean resultFlg //
                //                = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SALES_PRICING);
                //                if (!resultFlg) {
                //                    return;
                //                }

            } else if (POP_UP_SVC_PRC_RE.equals(bizMsg.xxPopPrm_P1.getValue())) {
                // 2018/08/24 S21_NA#27840 Mod Start
                // for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                //     ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).addlBasePrcCatgCd_B, bizMsg.prcCatgCd_HB);
                //     ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_B, bizMsg.prcCatgNm_HB);

                //     ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, new BigDecimal(i));
                //     NSAL1330CommonLogic.getPrcConfName(bizMsg);

                    //                    if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).addlBasePrcCatgCd_B) //
                    //                            && ZYPCommonFunc.hasValue(bizMsg.B.no(i).mdseCd_B)) {
                    //
                    //                        NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, i, getGlobalCompanyCode());
                    //                    }
                // }
                NSAL1330CommonLogic.getPrcConfName(bizMsg);
                // 2018/08/24 S21_NA#27840 Mod End
                NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);

            }
        }
    }

    private void doProcess_NSAL1330Scrn00_OnChange_SvcPkgForConfigPricing(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        int ix = bizMsg.xxNum_A.getValueInt();
        NSAL1330_RCMsg rBizMsg = bizMsg.R.no(ix);
        if (!ZYPCommonFunc.hasValue(rBizMsg.prcMtrPkgPk_R)) {
            rBizMsg.prcRateTpCd_R.clear();
            rBizMsg.xxTotPrcAmt_PR.clear();
            rBizMsg.prcTierSvcOfferCd_R.clear();
            return;
        }
        ZYPEZDItemValueSetter.setValue(rBizMsg.prcRateTpCd_R, NSAL1330CommonLogic.getPrcRateTpCd(bizMsg, rBizMsg));
        if (PRC_RATE_TP.ANNUAL.equals(rBizMsg.prcRateTpCd_R.getValue())) {
            ZYPEZDItemValueSetter.setValue(rBizMsg.xxTotPrcAmt_PR, NSAL1330CommonLogic.getBasePrcForNonMeter(bizMsg, rBizMsg));
            rBizMsg.prcTierSvcOfferCd_R.clear();

        } else if (PRC_LIST_TP.SERVICE_TIERS.equals(rBizMsg.prcListTpCd_R.getValue()) //
                && !ZYPCommonFunc.hasValue(rBizMsg.prcTierSvcOfferCd_R)) {
            NSAL1330CommonLogic.deriveTieredPricing(rBizMsg);
        }

        NSAL1330CommonLogic.getMtrLbConfig(bizMsg, glblMsg);

        //        }
    }

    /**
     * Add_Accessory
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_Add_Accessory(NSAL1330CMsg bizMsg) {

        if (bizMsg.J.getValidCount() + 1 > MAX_LINE) {
            bizMsg.setMessageInfo(NSAM0112E);
            return;
        }

        bizMsg.J.setValidCount(bizMsg.J.getValidCount() + 1);
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HJ)) {
            bizMsg.J.no(bizMsg.J.getValidCount() - 1).addlBasePrcCatgCd_J.setValue(bizMsg.prcCatgCd_HJ.getValue());
            bizMsg.J.no(bizMsg.J.getValidCount() - 1).prcCatgNm_J.setValue(bizMsg.prcCatgNm_HJ.getValue());
        }
    }

    /**
     * Add_RentalEquip
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_Add_RentalEquip(NSAL1330CMsg bizMsg) {

        if (bizMsg.B.getValidCount() + 1 > MAX_LINE) {
            bizMsg.setMessageInfo(NSAM0112E);
            return;
        }

        bizMsg.B.setValidCount(bizMsg.B.getValidCount() + 1);
        if (ZYPCommonFunc.hasValue(bizMsg.prcCatgCd_HB)) {
            bizMsg.B.no(bizMsg.B.getValidCount() - 1).addlBasePrcCatgCd_B.setValue(bizMsg.prcCatgCd_HB.getValue());
            bizMsg.B.no(bizMsg.B.getValidCount() - 1).prcCatgNm_B.setValue(bizMsg.prcCatgNm_HB.getValue());
        }
    }

    /**
     * ITEM DESC COVERED ITEM Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_Add_Service(NSAL1330CMsg bizMsg) {

        if (bizMsg.C.getValidCount() + 1 > MAX_LINE) {
            bizMsg.setMessageInfo(NSAM0112E);
            return;
        }

        bizMsg.C.setValidCount(bizMsg.C.getValidCount() + 1);
    }

    //    /**
    //     * @param bizMsg Business Msg
    //     */
    //    private void doProcess_NSAL1330Scrn00_Add_Tier(NSAL1330CMsg bizMsg) {
    //
    //        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.Z, "xxChkBox_Z", ZYPConstant.FLG_ON_Y);
    //
    //        if (checkList.size() != 0) {
    //            int zIndex = checkList.get(0);
    //            bizMsg.xxNum_Z.setValue(zIndex);
    //        }
    //        int maxTierCnt = 0;
    //        for (int i = 0; i < bizMsg.prcSvcTierTpCd_L.length(); i++) {
    //            if (!ZYPCommonFunc.hasValue(bizMsg.prcSvcTierTpCd_L.no(i).getValue())) {
    //                break;
    //            }
    //            maxTierCnt++;
    //        }
    //        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
    //
    //            if (bizMsg.Z.getValidCount() + checkList.size() > MAX_LINE) {
    //                bizMsg.setMessageInfo(NSAM0112E);
    //                return;
    //            }
    //            for (int i = checkList.size() - 1; i >= 0; i--) {
    //                int zindex = checkList.get(i);
    //                if (getTierCnt(bizMsg, zindex, true) > maxTierCnt) {
    //                    bizMsg.Z.no(checkList.get(zindex)).xxChkBox_Z.setErrorInfo(1, NSAM0112E);
    //                    bizMsg.setMessageInfo(NSAM0112E);
    //                    return;
    //                }
    //
    //                for (int j = bizMsg.Z.getValidCount(); j > zindex; j--) {
    //                    EZDMsg.copy(bizMsg.Z.no(j), null, bizMsg.Z.no(j + 1), null);
    //                }
    //                NSAL1330CommonLogic.zCopyDetail(bizMsg, zindex);
    //                bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + 1);
    //
    //            }
    //
    //        } else {
    //
    //            int aIndex = bizMsg.xxNum_A.getValueInt();
    //            BigDecimal checkModel = bizMsg.A.no(aIndex).mdlId_A.getValue();
    //
    //            if (bizMsg.Z.getValidCount() + checkList.size() > MAX_LINE) {
    //                bizMsg.setMessageInfo(NSAM0112E);
    //                return;
    //            }
    //            int listNum = 0;
    //            int addCnt = 0;
    //            for (int zindex = 0; zindex < bizMsg.Z.getValidCount(); zindex++) {
    //                if (checkModel.compareTo(bizMsg.Z.no(zindex).mdlId_Z.getValue()) == 0) {
    //                    if (checkList.size() > listNum && checkList.get(listNum) + addCnt == zindex) {
    //                        if (getTierCnt(bizMsg, zindex, false) >= maxTierCnt) {
    //                            bizMsg.Z.no(checkList.get(listNum)).xxChkBox_Z.setErrorInfo(1, NSAM0112E);
    //                            bizMsg.setMessageInfo(NSAM0112E);
    //                            return;
    //                        }
    //                        listNum++;
    //
    //                        for (int j = bizMsg.Z.getValidCount() - 1; j > zindex; j--) {
    //                            EZDMsg.copy(bizMsg.Z.no(j), null, bizMsg.Z.no(j + 1), null);
    //                        }
    //                        NSAL1330CommonLogic.zCopyDetail(bizMsg, zindex);
    //                        bizMsg.Z.setValidCount(bizMsg.Z.getValidCount() + 1);
    //                        addCnt++;
    //                    }
    //                }
    //            }
    //        }
    //    }
    //
    //    private int getTierCnt(NSAL1330CMsg bizMsg, int zIx, boolean isFleet) {
    //        int cnt = 0;
    //        String bllgMtrCd = bizMsg.Z.no(zIx).bllgMtrLbCd_Z.getValue();
    //        BigDecimal mdlId = bizMsg.Z.no(zIx).mdlId_Z.getValue();
    //        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
    //            if (!XX_FLG_TIER.equals(bizMsg.Z.no(i).xxFlgNm_Z.getValue())) {
    //                continue;
    //            }
    //            if (bllgMtrCd.equals(bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue()) //
    //                    && (isFleet //
    //                    || !isFleet && mdlId.compareTo(bizMsg.Z.no(i).mdlId_Z.getValue()) == 0)) {
    //                cnt++;
    //            }
    //        }
    //        return cnt;
    //    }

    //    /**
    //     * @param bizMsg Business Msg
    //     */
    //    private void doProcess_NSAL1330Scrn00_Apply_Bundle_Price(NSAL1330CMsg bizMsg) {
    //
    //        NSAL1330CommonLogic.getSvcPrcAccs(bizMsg, getGlobalCompanyCode());
    //    }

    /**
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NSAL1330Scrn00_CMN_Reset(NSAL1330CMsg bizMsg, NSAL1330SMsg glblMsg) {
        // 2019/09/25 QC#51323 Mod Start
        //List<String> flgList = new ArrayList<String>(bizMsg.A.getValidCount());
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    flgList.add(bizMsg.A.no(i).xxSmryLineFlg_A.getValue());
        //}
        Map<BigDecimal, String> flgMap = NSAL1330CommonLogic.getSmryLineFlgMap(bizMsg);
        // 2019/09/25 QC#51323 Mod End
        NSAL1330CommonLogic.resetBizMsgBeforeInit(bizMsg);
        doProcess_NSAL1330_INIT(bizMsg, glblMsg);
        // 2019/09/25 QC#51323 Mod Start
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxSmryLineFlg_A, flgList.get(i));
        //}
        NSAL1330CommonLogic.setSmryLineFlgMap(bizMsg, flgMap);
        // 2019/09/25 QC#51323 Mod End
    }

    /**
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_CMN_Return(NSAL1330CMsg bizMsg) {
        return; // no operation.
        //        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcTierSvcOfferCd_A)) {
        //                NSAL1330CommonLogic.closeTierCheck(bizMsg, bizMsg.A.no(i).mdlId_A.getValue());
        //            }
        //        }
        //        if (NSAL1330CommonLogic.isRentalOrder(bizMsg, getGlobalCompanyCode())) {
        //            NSAL1330CommonLogic.setAcsryChrgCloseForRental(bizMsg, getGlobalCompanyCode());
        //        }
        //        NSAL1330CommonLogic.AcsryDuplicateCheck(bizMsg);
        //
        //        // item Check - Band, BookItem
        //        for (int i = 0; i < bizMsg.Z.getValidCount(); i++) {
        //
        //            // set hidden from parent
        //            if (i != 0) {
        //                if (ZYPCommonFunc.hasValue(bizMsg.Z.no(i).bllgMtrLbCd_Z) //
        //                        && bizMsg.Z.no(i).bllgMtrLbCd_Z.getValue().equals(bizMsg.Z.no(i - 1).bllgMtrLbCd_Z.getValue()) && bizMsg.Z.no(i).t_MdlNm_Z.getValue().equals(bizMsg.Z.no(i - 1).t_MdlNm_Z.getValue())) {
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i).prcListBandCd_Z, bizMsg.Z.no(i - 1).prcListBandCd_Z);
        //                    ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(i).prcBookMdseCd_Z, bizMsg.Z.no(i - 1).prcBookMdseCd_Z);
        //                }
        //            }
        //
        //            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(i).prcListBandCd_Z)) {
        //                String bandCdNm = ZYPCodeDataUtil.getName("PRC_LIST_BAND", getGlobalCompanyCode(), bizMsg.Z.no(i).prcListBandCd_Z.getValue());
        //                if (!ZYPCommonFunc.hasValue(bandCdNm)) {
        //                    bizMsg.Z.no(i).prcListBandCd_Z.setErrorInfo(1, NSAM0647E, new String[] {"PRC_LIST_BAND", bizMsg.Z.no(i).prcListBandCd_Z.getValue() });
        //                }
        //            }
        //            if (ZYPCommonFunc.hasValue(bizMsg.Z.no(i).prcBookMdseCd_Z)) {
        //
        //                S21SsmEZDResult ssmResult = null;
        //
        //                //                int index = bizMsg.xxNum_A.getValueInt();
        //                ssmResult = NSAL1330Query.getInstance().getBookItemNm(bizMsg, i, getGlobalCompanyCode());
        //                String bookItemNm = (String) ssmResult.getResultObject();
        //                if (!ZYPCommonFunc.hasValue(bookItemNm)) {
        //                    bizMsg.Z.no(i).prcBookMdseCd_Z.setErrorInfo(1, NSAM0647E, new String[] {"MDSE", bizMsg.Z.no(i).prcBookMdseCd_Z.getValue() });
        //                }
        //            }
        //        }
    }

    /**
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_Del_Accessory(NSAL1330CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.J, "xxChkBox_J", ZYPConstant.FLG_ON_Y);

        // add start 2017/06/12 QC#18935
        int ixM = bizMsg.M.getValidCount();
        for (Integer ixJ : checkList) {
            NSAL1330_JCMsg jBizMsg = bizMsg.J.no(ixJ);
            if (ZYPCommonFunc.hasValue(jBizMsg.dsContrDtlPk_J)) {
                NSAL1330_MCMsg mBizMsg = bizMsg.M.no(ixM);
                ZYPEZDItemValueSetter.setValue(mBizMsg.dsContrDtlPk_M, jBizMsg.dsContrDtlPk_J);
                ZYPEZDItemValueSetter.setValue(mBizMsg.svcPrcCatgCd_M, SVC_PRC_CATG.ACC_UNIT_BASE_CHARGE);
                ZYPEZDItemValueSetter.setValue(mBizMsg.addlChrgCatgCd_M, ADDL_CHRG_CATG.ACCESSORY);
                ixM++;
            }
        }
        bizMsg.M.setValidCount(ixM);
        // add end 2017/06/12 QC#18935

        ZYPTableUtil.deleteRows(bizMsg.J, checkList);
    }

    /**
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_Del_RentalEquip(NSAL1330CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        // add start 2017/06/12 QC#18935
        int ixM = bizMsg.M.getValidCount();
        for (Integer ixB : checkList) {
            NSAL1330_BCMsg bBizMsg = bizMsg.B.no(ixB);
            // START 2018/03/13 M.Naito [QC#23378, MOD]
//            if (ZYPCommonFunc.hasValue(bBizMsg.dsContrAddlChrgPk_B)) {
            if (ZYPCommonFunc.hasValue(bBizMsg.addlBasePrcDealAmt_B)) {
            // END 2018/03/13 M.Naito [QC#23378, MOD]
                NSAL1330_MCMsg mBizMsg = bizMsg.M.no(ixM);
                ZYPEZDItemValueSetter.setValue(mBizMsg.dsContrDtlPk_M, bBizMsg.dsContrDtlPk_B);
                ZYPEZDItemValueSetter.setValue(mBizMsg.dsContrAddlChrgPk_M, bBizMsg.dsContrAddlChrgPk_B);
                ZYPEZDItemValueSetter.setValue(mBizMsg.svcPrcCatgCd_M, SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
                ZYPEZDItemValueSetter.setValue(mBizMsg.addlChrgCatgCd_M, ADDL_CHRG_CATG.RENTAL);
                ixM++;
            }
        }
        bizMsg.M.setValidCount(ixM);
        // add end 2017/06/12 QC#18935

        ZYPTableUtil.deleteRows(bizMsg.B, checkList);
    }

    /**
     * @param bizMsg Business Msg
     */
    private void doProcess_NSAL1330Scrn00_Del_Service(NSAL1330CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.C, "xxChkBox_C", ZYPConstant.FLG_ON_Y);

        // add start 2017/06/12 QC#18935
        int ixM = bizMsg.M.getValidCount();
        for (Integer ixC : checkList) {
            NSAL1330_CCMsg cBizMsg = bizMsg.C.no(ixC);
            if (ZYPCommonFunc.hasValue(cBizMsg.dsContrAddlChrgPk_C)) {
                NSAL1330_MCMsg mBizMsg = bizMsg.M.no(ixM);
                ZYPEZDItemValueSetter.setValue(mBizMsg.dsContrDtlPk_M, cBizMsg.dsContrDtlPk_C);
                ZYPEZDItemValueSetter.setValue(mBizMsg.dsContrAddlChrgPk_M, cBizMsg.dsContrAddlChrgPk_C);
                ZYPEZDItemValueSetter.setValue(mBizMsg.svcPrcCatgCd_M, SVC_PRC_CATG.MAIN_UNIT_ADDITIONAL_CHARGE);
                ixM++;
            }
        }
        bizMsg.M.setValidCount(ixM);
        // add end 2017/06/12 QC#18935

        ZYPTableUtil.deleteRows(bizMsg.C, checkList);
        //        if (bizMsg.C.getValidCount() - row == 0) {
        //            bizMsg.C.setValidCount(1);
        //            return;
        //        }
    }

    //    /**
    //     * @param bizMsg Business Msg
    //     */
    //    private void doProcess_NSAL1330Scrn00_Del_Tier(NSAL1330CMsg bizMsg) {
    //
    //        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.Z, "xxChkBox_Z", ZYPConstant.FLG_ON_Y);
    //        List<Integer> deleteList = new ArrayList<Integer>();
    //
    //        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue())) {
    //
    //            int i = 0;
    //            for (; i < checkList.size(); i++) {
    //                int zindex = checkList.get(i);
    //                deleteList.add(zindex);
    //            }
    //        } else {
    //            int aIndex = bizMsg.xxNum_A.getValueInt();
    //            BigDecimal checkModel = bizMsg.A.no(aIndex).mdlId_A.getValue();
    //
    //            int i = 0;
    //            for (; i < checkList.size(); i++) {
    //                int zindex = checkList.get(i);
    //                if (checkModel.compareTo(bizMsg.Z.no(zindex).mdlId_Z.getValue()) == 0) {
    //                    deleteList.add(zindex);
    //                }
    //            }
    //        }
    //        ZYPTableUtil.deleteRows(bizMsg.Z, deleteList);
    //    }

    /**
     * @param bizMsg Business Msg
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NSAL1330Scrn00_Item_Desc_Covered_Item(NSAL1330CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        ssmResult = NSAL1330Query.getInstance().getItemCvrItem(bizMsg, bizMsg.J.no(index).mdseCd_J.getValue());

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0646E, new String[] {"Item Description" });
            return;
        }

        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }

        NSAL1330_JCMsg jBizMsg = bizMsg.J.no(index);
        ZYPEZDItemValueSetter.setValue(//
                jBizMsg.mdseDescShortTxt_J, (String) resList.get(0).get("MDSE_DESC_SHORT_TXT"));

        // Line#
        String lineNum = (String) resList.get(0).get("CPO_DTL_LINE_NUM");
        String lineSubNum = (String) resList.get(0).get("CPO_DTL_LINE_SUB_NUM");

        String lineNo = (String) resList.get(0).get("DPLY_LINE_NUM");

        ZYPEZDItemValueSetter.setValue(jBizMsg.xxLineNum_J, lineNo);
        ZYPEZDItemValueSetter.setValue(jBizMsg.shellLineNum_J, bizMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(jBizMsg.cpoDtlLineNum_J, lineNum);
        ZYPEZDItemValueSetter.setValue(jBizMsg.cpoDtlLineSubNum_J, lineSubNum);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, bizMsg.xxNum_A);
        BigDecimal basePrc = NSAL1330CommonLogic.getPeriodicServicePriceForAccesoryCharge(bizMsg);
        if (ZYPCommonFunc.hasValue(basePrc)) {
            ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcDealAmt_J, basePrc);
            ZYPEZDItemValueSetter.setValue(jBizMsg.addlBasePrcDealAmt_BJ, basePrc);
            ZYPEZDItemValueSetter.setValue(jBizMsg.dealPrcListPrcAmt_J, basePrc);
        }
    }

    /**
     * @param bizMsg Business Msg
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NSAL1330Scrn00_Item_Desc_Covered_Item_RentalEquip(NSAL1330CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        ssmResult = NSAL1330Query.getInstance().getItemCvrItem(bizMsg, bizMsg.B.no(index).mdseCd_B.getValue());

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0646E, new String[] {"Item Description" });
            return;
        }
        List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resList == null || resList.isEmpty()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).mdseDescShortTxt_B, (String) resList.get(0).get("MDSE_DESC_SHORT_TXT"));

        // Line#
        String lineNum = (String) resList.get(0).get("CPO_DTL_LINE_NUM");
        String lineSubNum = (String) resList.get(0).get("CPO_DTL_LINE_SUB_NUM");

        String lineNo = (String) resList.get(0).get("DPLY_LINE_NUM");

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).shellLineNum_B, bizMsg.shellLineNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).xxLineNum_B, lineNo);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).cpoDtlLineNum_B, lineNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(index).cpoDtlLineSubNum_B, lineSubNum);

        if (ZYPCommonFunc.hasValue(bizMsg.B.no(index).addlBasePrcCatgCd_B) //
                && ZYPCommonFunc.hasValue(bizMsg.B.no(index).mdseCd_B)) {

            ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_B, new BigDecimal(index));
            NSAL1330CommonLogic.getPrcConfName(bizMsg);
            NSAL1330CommonLogic.setDefaultPrcForRental(bizMsg);

            //            NSAL1330CommonLogic.getAccessoryChargePrcConfigPullDown(bizMsg, index, getGlobalCompanyCode());
        }
        //        // Pricing API
        //        boolean resultFlg = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SALES_PRICING);
        //        if (!resultFlg) {
        //            return;
        //        }
    }

    /**
     * ITEM DESC COVERED UNIT Event
     * @param bizMsg Business Msg
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NSAL1330Scrn00_Item_Desc_Covered_Unit(NSAL1330CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        ssmResult = NSAL1330Query.getInstance().getItemCvrUnit(bizMsg, getGlobalCompanyCode());

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0646E, new String[] {"Unit Description" });
            return;
        } else {
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).mdseDescShortTxt_CU, (String) resList.get(0).get("MDSE_DESC_SHORT_TXT"));

                // Line#
                String lineNo = bizMsg.C.no(index).xxLineNum_C.getValue();
                if (!ZYPCommonFunc.hasValue(lineNo)) {
                    String lineNum = (String) resList.get(0).get("CPO_DTL_LINE_NUM");
                    String lineSubNum = (String) resList.get(0).get("CPO_DTL_LINE_SUB_NUM");

                    lineNo = (String) resList.get(0).get("DPLY_LINE_NUM");

                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).xxLineNum_C, lineNo);
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).cpoDtlLineNum_C, lineNum);
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).cpoDtlLineSubNum_C, lineSubNum);
                }
            }
        }

        //        // Pricing API
        //        boolean resultFlg = NSAL1330CommonLogic.getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SPECIAL_CHARGE);
        //        if (!resultFlg) {
        //            return;
        //        }
    }

    /**
     * ITEM DESC ADDITIONAL ITEM Event
     * @param bizMsg Business Msg
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NSAL1330Scrn00_Item_Desc_Additional_Item(NSAL1330CMsg bizMsg) {

        int index = bizMsg.xxNum_A.getValueInt();

        // 2017/03/08 S21_NA#16752 Mod Start
//        S21SsmEZDResult ssmResult = null;
//        ssmResult = NSAL1330Query.getInstance().getItemAddItem(bizMsg, getGlobalCompanyCode());
//
//        if (ssmResult.isCodeNotFound()) {
//            bizMsg.setMessageInfo(NSAM0646E, new String[] {"Charge Description" });
//            return;
//        } else {

        EZDCStringItem mdseCdItem = bizMsg.C.no(index).mdseCd_CI;

        String mdseCd = "";
        if (ZYPCommonFunc.hasValue(mdseCdItem)) {
            mdseCd = mdseCdItem.getValue();
        }
        boolean isLike = false;
        if (mdseCd.indexOf("%") >= 0 || mdseCd.indexOf("_") >= 0) {
            isLike = true;
        }
        S21SsmEZDResult rslt = NSAL1330Query.getInstance().getMdseInfoForOnBlurAddlChrg(mdseCdItem, MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_CHARGE_ITEMS, isLike);
        if (rslt == null || rslt.isCodeNotFound()) {
            mdseCdItem.setErrorInfo(1, NSAM0647E, new String[] {"DS Merchandise Information", mdseCd });
            return;
        } else {
            List<Map<String, Object>> resList = (List<Map<String, Object>>) rslt.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(index).mdseDescShortTxt_CI, (String) resList.get(0).get("MDSE_DESC_SHORT_TXT"));
            }
        }
        // 2017/03/08 S21_NA#16752 Mod End

        // Pricing API
        boolean resultFlg = NSAL1330CommonLogic //
                .getPeriodicServicePrice(bizMsg, getGlobalCompanyCode(), PRC_CTX_TP.SPECIAL_CHARGE);
        if (!resultFlg) {
            return;
        }
    }

    /**
     * @param bizMsg Business Msg
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NSAL1330Scrn00_OpenWin_Supply_Agreement(NSAL1330CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        S21SsmEZDResult ssmResult = null;

        int index = bizMsg.xxNum_A.getValueInt();
        ssmResult = NSAL1330Query.getInstance().getPrcListStruForAupplyAgreement(bizMsg, index, getGlobalCompanyCode());

        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(NSAM0650E, new String[] {"Service Price List" });
            return;
        } else {
            List<Map<String, Object>> resList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resList != null && !resList.isEmpty()) {
                String stru = (String) resList.get(0).get("PRC_LIST_STRU_TP_CD");

                if (!PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(stru)) {
                    bizMsg.setMessageInfo(NSAM0650E, new String[] {"Service Price List" });
                    return;
                }
            }
        }
    }

    //    /**
    //     * @param bizMsg Business Msg
    //     */
    //    private void doProcess_NSAL1330Scrn00_Refresh(NSAL1330CMsg bizMsg) {
    //
    //        // Set Total Price
    //        BigDecimal baseBllgCycle = NSAL1330CommonLogic.getBllgCycle(bizMsg);
    //        if (ZYPCommonFunc.hasValue(baseBllgCycle)) {
    //            boolean isFleet = DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd.getValue());
    //            for (int aIndex = 0; aIndex < bizMsg.A.getValidCount(); aIndex++) {
    //
    //                BigDecimal term = bizMsg.termMthNum.getValue();
    //
    //                BigDecimal extendedBase = BigDecimal.ZERO;
    //                if (isFleet) {
    //                    extendedBase = bizMsg.A.no(aIndex).xxTotPrcAmt_PB.getValue();
    //                } else {
    //                    extendedBase = bizMsg.A.no(aIndex).xxTotPrcAmt_PB.getValue().multiply(//
    //                            bizMsg.A.no(aIndex).xxTotQtyCnt_A.getValue());
    //                }
    //                BigDecimal totalBase = extendedBase.multiply((term.divide(baseBllgCycle)));
    //
    //                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_EB, extendedBase);
    //                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(aIndex).xxTotPrcAmt_TB, totalBase);
    //            }
    //        }
    //    }

    // QC#16141 ADD START
    private void doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNmConfig(NSAL1330CMsg bizMsg) {
        int selectIndex = bizMsg.xxNum_A.getValueInt();
        EZDCStringItem targetItem = bizMsg.R.no(selectIndex).dsAcctNm_R; // 2017/03/29 S21_NA#18171 Mod

        SELL_TO_CUSTTMsg dsAcctCustTMsg //
        = NSAL1330CommonLogic.getDsAcctCustInfo(bizMsg, true, targetItem, "Customer Name", getGlobalCompanyCode());
        if (dsAcctCustTMsg == null) {
            return;
        }

        NSAL1330CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg, getGlobalCompanyCode(), true);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumConfig(NSAL1330CMsg bizMsg) {
        int selectIndex = bizMsg.xxNum_A.getValueInt();
        EZDCStringItem targetItem = bizMsg.R.no(selectIndex).billToCustCd_R;

        SELL_TO_CUSTTMsg dsAcctCustTMsg //
        = NSAL1330CommonLogic.getDsAcctCustInfo(bizMsg, false, targetItem, "Customer Number", getGlobalCompanyCode());
        if (dsAcctCustTMsg == null) {
            return;
        }

        NSAL1330CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg, getGlobalCompanyCode(), true);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_DeriveFromCustLocCdConfig(NSAL1330CMsg bizMsg) {
        int selectIndex = bizMsg.xxNum_A.getValueInt();
        Map<String, String> billToInfo //
        = NSAL1330CommonLogic.getBillToInfo(bizMsg, bizMsg.R.no(selectIndex).billToLocNum_R, "Location Code");
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).billToCustCd_R, billToInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).dsAcctNm_R, billToInfo.get("DS_ACCT_NM")); // 2017/03/29 S21_NA#18171 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).billToLocNum_R, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.R.no(selectIndex).xxGenlFldAreaTxt_R, billToInfo.get("BILL_TO_ADDR"));
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNmMeter(NSAL1330CMsg bizMsg) {
        int selectIndex = bizMsg.xxNum_Z.getValueInt();
        EZDCStringItem targetItem = bizMsg.U.no(selectIndex).dsAcctNm_U; // 2017/03/29 S21_NA#18171 Mod

        SELL_TO_CUSTTMsg dsAcctCustTMsg //
        = NSAL1330CommonLogic.getDsAcctCustInfo(bizMsg, true, targetItem, "Customer Name", getGlobalCompanyCode());
        if (dsAcctCustTMsg == null) {
            return;
        }

        NSAL1330CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg, getGlobalCompanyCode(), false);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_DeriveFromDsAcctNumMeter(NSAL1330CMsg bizMsg) {
        int selectIndex = bizMsg.xxNum_Z.getValueInt();
        EZDCStringItem targetItem = bizMsg.U.no(selectIndex).billToCustCd_U;

        SELL_TO_CUSTTMsg dsAcctCustTMsg //
        = NSAL1330CommonLogic.getDsAcctCustInfo(bizMsg, false, targetItem, "Customer Number", getGlobalCompanyCode());
        if (dsAcctCustTMsg == null) {
            return;
        }

        NSAL1330CommonLogic.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg, getGlobalCompanyCode(), false);
    }

    private void doProcess_NSAL1330Scrn00_OnBlur_DeriveFromCustLocCdMeter(NSAL1330CMsg bizMsg) {
        int selectIndex = bizMsg.xxNum_Z.getValueInt();
        Map<String, String> billToInfo //
        = NSAL1330CommonLogic.getBillToInfo(bizMsg, bizMsg.U.no(selectIndex).billToLocNum_U, "Location Code");
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).billToCustCd_U, billToInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).dsAcctNm_U, billToInfo.get("DS_ACCT_NM")); // 2017/03/29 S21_NA#18171 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).billToLocNum_U, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.U.no(selectIndex).xxGenlFldAreaTxt_U, billToInfo.get("BILL_TO_ADDR"));
    }
    // QC#16141 ADD END

    // START 2017/10/03 [QC#20059, ADD]
    private void doProcess_NSAL1330Scrn00_Toggle_ServicePriceSettingLevel(NSAL1330CMsg bizMsg) {
        int idxA = bizMsg.xxNum_A.getValueInt();

        NSAL1330_ACMsg aBizMsg = bizMsg.A.no(idxA);
        if (NSAL1330CommonLogic.isConfigLevelPriceSetting(aBizMsg)) {
            NSAL1330CommonLogic.applyAllFromModel(bizMsg);
            NSAL1330CommonLogic.clearModelPricing(bizMsg);
        } else {
            NSAL1330CommonLogic.clearConfigPricing(bizMsg);
        }
    }
    // END   2017/10/03 [QC#20059, ADD]

    // START 2018/10/10 K.Kojima [QC#28715,ADD]
    private void doProcess_NSAL1330Scrn00_OpenWin_Item_Search_Additional_Charge_Item(NSAL1330CMsg bizMsg) {
        bizMsg.mdseItemTpCd.clear();
        S21SsmEZDResult ssmResult = NSAL1330Query.getInstance().getAddlChrgMdseItemTpCd(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }
        String mdseItemTpCd = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd, mdseItemTpCd);
    }
    // END 2018/10/10 K.Kojima [QC#28715,ADD]
}
