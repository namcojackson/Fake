/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7040;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.db.MDSE_STRU_ELMNT_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_MDL_SEG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_ADDL_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_EQUIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_IN_OUT_RG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PGM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_TIER_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_ZONE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TERM_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SEG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Price List Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRA             E.Inada         Create          N/A
 * 2016/04/12   SRA             E.Inada         Update          QC#6378
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 *</pre>
 */
public class NMAL7040BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7040CMsg bizMsg = (NMAL7040CMsg) cMsg;
            NMAL7040SMsg glblMsg = (NMAL7040SMsg) sMsg;

            if ("NMAL7040_INIT".equals(screenAplID)) {
                doProcess_NMAL7040_INIT(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7040_INIT(NMAL7040CMsg bizMsg, NMAL7040SMsg glblMsg) {
        createPullDown(bizMsg);

        getProductLevelName(bizMsg);
    }

    public static void createPullDown(NMAL7040CMsg bizMsg) {
        String prcListStruTpCd = bizMsg.prcListStruTpCd_H.getValue();

        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(PRC_QLFY_TP.class, bizMsg.prcQlfyTpCd_L, bizMsg.prcQlfyTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PKG_UOM.class, bizMsg.pkgUomCd_L, bizMsg.pkgUomDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_TERM_UOM.class, bizMsg.prcTermUomCd_L, bizMsg.prcTermUomDescTxt_L);

        } else if (PRC_LIST_STRU_TP.SERVICE.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(PRC_RATE_TP.class, bizMsg.prcRateTpCd_L, bizMsg.prcRateTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_PLN_TP.class, bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_CONTR_TP.class, bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
            // ZYPCodeDataUtil.createPulldownList(PRC_LIST_BAND.class, bizMsg.prcListBandCd_L, bizMsg.prcListBandDescTxt_L); // 2018/11/17 QC#29147 Del
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_ZONE.class, bizMsg.prcSvcZoneCd_L, bizMsg.prcSvcZoneDescTxt_L);

        } else if (PRC_LIST_STRU_TP.SERVICE_TIERS.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_TIER_TP.class, bizMsg.prcSvcTierTpCd_L, bizMsg.prcSvcTierTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_PLN_TP.class, bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_CONTR_TP.class, bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
            // ZYPCodeDataUtil.createPulldownList(PRC_LIST_BAND.class, bizMsg.prcListBandCd_L, bizMsg.prcListBandDescTxt_L); // 2018/11/17 QC#29147 Del

        } else if (PRC_LIST_STRU_TP.SERVICE_SPECIAL_CHARGES.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(PRC_ADDL_CHRG_TP.class, bizMsg.prcAddlChrgTpCd_L, bizMsg.prcAddlChrgTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(MKT_MDL_SEG.class, bizMsg.mktMdlSegCd_L, bizMsg.mktMdlSegDescTxt_L);

        } else if (PRC_LIST_STRU_TP.SUPPLY_PROGRAM.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_PLN_TP.class, bizMsg.prcSvcPlnTpCd_L, bizMsg.prcSvcPlnTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_CONTR_TP.class, bizMsg.prcSvcContrTpCd_L, bizMsg.prcSvcContrTpDescTxt_L);
            // ZYPCodeDataUtil.createPulldownList(PRC_LIST_BAND.class, bizMsg.prcListBandCd_L, bizMsg.prcListBandDescTxt_L); // 2018/11/17 QC#29147 Del
            ZYPCodeDataUtil.createPulldownList(PRC_SVC_ZONE.class, bizMsg.prcSvcZoneCd_L, bizMsg.prcSvcZoneDescTxt_L);

        } else if (PRC_LIST_STRU_TP.LEASE_RATES.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(PRC_PGM_TP.class, bizMsg.prcPgmTpCd_L, bizMsg.prcPgmTpDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_EQUIP_TP.class, bizMsg.prcEquipTpCd_L, bizMsg.prcEquipTpDescTxt_L);

        } else if (PRC_LIST_STRU_TP.LEASE_RETURN_FEES.equals(prcListStruTpCd)) {
            ZYPCodeDataUtil.createPulldownList(SVC_SEG.class, bizMsg.svcSegCd_L, bizMsg.svcSegDescTxt_L);
            ZYPCodeDataUtil.createPulldownList(PRC_IN_OUT_RG.class, bizMsg.prcInOutRgCd_L, bizMsg.prcInOutRgDescTxt_L);

        }
    }

    private void getProductLevelName(NMAL7040CMsg bizMsg) {
        String prcListStruTpCd = bizMsg.prcListStruTpCd_H.getValue();

        if (PRC_LIST_STRU_TP.EQUIPMENT.equals(prcListStruTpCd)) {

            MDSE_STRU_ELMNT_TPTMsgArray tMsgArray = (MDSE_STRU_ELMNT_TPTMsgArray) S21CodeTableAccessor.findAll(MDSE_STRU_ELMNT_TP.class.getSimpleName());

            for (int i = 0; i < tMsgArray.length(); i++) {
                String mdseStruElmntTpCd = tMsgArray.no(i).mdseStruElmntTpCd.getValue();
                String prodLvllNm = tMsgArray.no(i).mdseStruElmntTpDescTxt.getValue();
                // PLG
                if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals(mdseStruElmntTpCd)) {
                    bizMsg.mdseStruElmntTpDescTxt_T1.setValue(prodLvllNm);
                    // PL
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals(mdseStruElmntTpCd)) {
                    bizMsg.mdseStruElmntTpDescTxt_T2.setValue(prodLvllNm);
                    // PL2
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals(mdseStruElmntTpCd)) {
                    bizMsg.mdseStruElmntTpDescTxt_T3.setValue(prodLvllNm);
                    // PL3
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(mdseStruElmntTpCd)) {
                    bizMsg.mdseStruElmntTpDescTxt_T4.setValue(prodLvllNm);
                    // PL4
                } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals(mdseStruElmntTpCd)) {
                    bizMsg.mdseStruElmntTpDescTxt_T5.setValue(prodLvllNm);
                }
            }
        }
    }
}
