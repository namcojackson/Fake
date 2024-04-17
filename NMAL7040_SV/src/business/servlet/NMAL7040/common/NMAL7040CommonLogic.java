/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDStringUtil;
import business.servlet.NMAL7040.NMAL7040BMsg;
import business.servlet.NMAL7040.constant.NMAL7040Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Price List Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRA             E.Inada         Create          N/A
 * 2016/04/12   SRA             E.Inada         Update          QC#6378
 * 2016/09/13   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 * 2018/11/27   Fujitsu         K.Kato          Update          QC#29319
 *</pre>
 */
public class NMAL7040CommonLogic implements NMAL7040Constant {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    public static void setInputParam(NMAL7040BMsg scrnMsg, Object[] arg) {
        scrnMsg.clear();

        if (!(arg instanceof Object[])) {
            return;
        }

        if (arg.length < FILTER_MAX_PARAM_NUM) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcListStruTpCd_H, getString(arg[FILTER_PRC_LIST_STRU_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_H, getString(arg[FILTER_PRC_CATG_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_H, getString(arg[FILTER_PRC_CATG_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyTpCd, getString(arg[FILTER_PRC_QLFY_TP_CD]));
        // 2018/11/27 QC#29319 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.prcQlfyValTxt, getString(arg[FILTER_PRC_QLFY_VAL_TXT]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcQlfyValSrchTxt, getString(arg[FILTER_PRC_QLFY_VAL_TXT]));
        // 2018/11/27 QC#29319 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm, getString(arg[FILTER_MDL_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgNm, getString(arg[FILTER_PRC_MTR_PKG_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcPgmTpCd, getString(arg[FILTER_PRC_PGM_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcEquipTpCd, getString(arg[FILTER_PRC_EQUIP_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcSegCd, getString(arg[FILTER_SVC_SEG_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcInOutRgCd, getString(arg[FILTER_PRC_IN_OUT_RG_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcListEquipConfigNum, getBigDecimal(arg[FILTER_PRC_LIST_EQUIP_CONFIG_NUM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcListEquipConfigNm, getString(arg[FILTER_PRC_LIST_EQUIP_CONFIG_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.pkgUomCd, getString(arg[FILTER_PKG_UOM_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcRateTpCd, getString(arg[FILTER_PRC_RATE_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcSvcPlnTpCd, getString(arg[FILTER_PRC_SVC_PLN_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcSvcContrTpCd, getString(arg[FILTER_PRC_SVC_CONTR_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.mtrLbNm, getString(arg[FILTER_MTR_LB_NM]));
        // 2018/11/17 QC#29147 Mod Start
        // ZYPEZDItemValueSetter.setValue(scrnMsg.prcListBandCd, getString(arg[FILTER_PRC_LIST_BAND_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcListBandDescTxt, getString(arg[FILTER_PRC_LIST_BAND_DESC_TXT]));
        // 2018/11/17 QC#29147 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcSvcTierTpCd, getString(arg[FILTER_PRC_SVC_TIER_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.splyAgmtPlnNm, getString(arg[FILTER_SPLY_AGMT_PLN_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, getString(arg[FILTER_MDSE_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcAddlChrgTpCd, getString(arg[FILTER_PRC_ADDL_CHRG_TP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.mktMdlSegCd, getString(arg[FILTER_MKT_MDL_SEG_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcLeaseCmpyAbbrNm, getString(arg[FILTER_PRC_LEASE_CMPY_ABBR_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm, getString(arg[FILTER_DS_ACCT_NM]));
        // Mod Start 2018/07/17 QC#20267
        // 2018/11/27 QC#29319 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.mnfItemCd, getString(arg[FILTER_MNF_ITEM_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMnfItemCdSrchTxt, getString(arg[FILTER_MNF_ITEM_CD]));
        // 2018/11/27 QC#29319 Mod End
        // Mod End 2018/07/17 QC#20267
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, getString(arg[FILTER_MDSE_DESC_SHORT_TXT]));
        // Mod Start 2016/10/17 QC#15193
//        ZYPEZDItemValueSetter.setValue(scrnMsg.coaMdseTpNm, getString(arg[FILTER_COA_MDSE_TP_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjNm, getString(arg[FILTER_COA_MDSE_TP_NM]));
      // Mod End 2016/10/17 QC#15193
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseItemTpNm, getString(arg[FILTER_MDSE_ITEM_TP_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm, getString(arg[FILTER_COA_PROD_NM]));
        // 2018/11/27 QC#29319 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm, getString(arg[FILTER_T_MDL_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTMdlNmSrchTxt, getString(arg[FILTER_T_MDL_NM]));
        // 2018/11/27 QC#29319 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlCd, getString(arg[FILTER_ZEROTH_PROD_CTRL_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlCd, getString(arg[FILTER_FIRST_PROD_CTRL_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlCd, getString(arg[FILTER_SCD_PROD_CTRL_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlCd, getString(arg[FILTER_THIRD_PROD_CTRL_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlCd, getString(arg[FILTER_FRTH_PROD_CTRL_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcTermAot, getBigDecimal(arg[FILTER_PRC_TERM_AOT]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcTermUomCd, getString(arg[FILTER_PRC_TERM_UOM_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.custBidQty, getBigDecimal(arg[FILTER_CUST_BID_QTY]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcFmlaNm, getString(arg[FILTER_PRC_FMLA_NM]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.openMktFlg, getString(arg[FILTER_OPEN_MKT_FLG]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.compCd, getString(arg[FILTER_COMP_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.termFromMthAot, getBigDecimal(arg[FILTER_TERM_FROM_MTH_AOT]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.termThruMthAot, getBigDecimal(arg[FILTER_TERM_THRU_MTH_AOT]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcSvcZoneCd, getString(arg[FILTER_PRC_SVC_ZONE_CD]));
        // Add Start 2016/09/13 QC#11615
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_H2, getString(arg[FILTER_MDSE_NM]));
        // Add End 2016/09/13 QC#11615
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, getString(arg[FILTER_XX_YES_NO_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcListMdseCd, getString(arg[FILTER_PRC_LIST_MDSE_CD]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_H1, getDtString(arg[FILTER_EFF_FROM_DT_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_H2, getDtString(arg[FILTER_EFF_FROM_DT_H2]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_H1, getDtString(arg[FILTER_EFF_THRU_DT_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_H2, getDtString(arg[FILTER_EFF_THRU_DT_H2]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFullNm_H1, getString(arg[FILTER_XX_FULL_NM_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxFullNm_H2, getString(arg[FILTER_XX_FULL_NM_H2]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_H1, getDtString(arg[FILTER_CRAT_DT_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_H2, getDtString(arg[FILTER_CRAT_DT_H2]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.lastUpdDt_H1, getDtString(arg[FILTER_LAST_UPD_DT_H1]));
        ZYPEZDItemValueSetter.setValue(scrnMsg.lastUpdDt_H2, getDtString(arg[FILTER_LAST_UPD_DT_H2]));
    }

    public static void setOutputParam(NMAL7040BMsg scrnMsg, Object[] arg) {

        setValue(arg[FILTER_PRC_LIST_STRU_TP_CD], scrnMsg.prcListStruTpCd_H.getValue());
        setValue(arg[FILTER_PRC_CATG_CD], scrnMsg.prcCatgCd_H.getValue());
        setValue(arg[FILTER_PRC_CATG_NM], scrnMsg.prcCatgNm_H.getValue());
        setValue(arg[FILTER_PRC_QLFY_TP_CD], scrnMsg.prcQlfyTpCd.getValue());
        // 2018/11/27 QC#29319 Mod Start
        //setValue(arg[FILTER_PRC_QLFY_VAL_TXT], scrnMsg.prcQlfyValTxt.getValue());
        setValue(arg[FILTER_PRC_QLFY_VAL_TXT], scrnMsg.xxPrcQlfyValSrchTxt.getValue());
        // 2018/11/27 QC#29319 Add End
        setValue(arg[FILTER_MDL_NM], scrnMsg.mdlNm.getValue());
        setValue(arg[FILTER_PRC_MTR_PKG_NM], scrnMsg.prcMtrPkgNm.getValue());
        setValue(arg[FILTER_PRC_PGM_TP_CD], scrnMsg.prcPgmTpCd.getValue());
        setValue(arg[FILTER_PRC_EQUIP_TP_CD], scrnMsg.prcEquipTpCd.getValue());
        setValue(arg[FILTER_SVC_SEG_CD], scrnMsg.svcSegCd.getValue());
        setValue(arg[FILTER_PRC_IN_OUT_RG_CD], scrnMsg.prcInOutRgCd.getValue());
        setValue(arg[FILTER_PRC_LIST_EQUIP_CONFIG_NUM], scrnMsg.prcListEquipConfigNum.getValue());
        setValue(arg[FILTER_PRC_LIST_EQUIP_CONFIG_NM], scrnMsg.prcListEquipConfigNm.getValue());
        setValue(arg[FILTER_PKG_UOM_CD], scrnMsg.pkgUomCd.getValue());
        setValue(arg[FILTER_PRC_RATE_TP_CD], scrnMsg.prcRateTpCd.getValue());
        setValue(arg[FILTER_PRC_SVC_PLN_TP_CD], scrnMsg.prcSvcPlnTpCd.getValue());
        setValue(arg[FILTER_PRC_SVC_CONTR_TP_CD], scrnMsg.prcSvcContrTpCd.getValue());
        setValue(arg[FILTER_MTR_LB_NM], scrnMsg.mtrLbNm.getValue());
        // 2018/11/17 QC#29147 Mod Start
        // setValue(arg[FILTER_PRC_LIST_BAND_CD], scrnMsg.prcListBandCd.getValue());
        setValue(arg[FILTER_PRC_LIST_BAND_DESC_TXT], scrnMsg.prcListBandDescTxt.getValue());
        // 2018/11/17 QC#29147 Mod End
        setValue(arg[FILTER_PRC_SVC_TIER_TP_CD], scrnMsg.prcSvcTierTpCd.getValue());
        setValue(arg[FILTER_SPLY_AGMT_PLN_NM], scrnMsg.splyAgmtPlnNm.getValue());
        setValue(arg[FILTER_MDSE_CD], scrnMsg.mdseCd.getValue());
        setValue(arg[FILTER_PRC_ADDL_CHRG_TP_CD], scrnMsg.prcAddlChrgTpCd.getValue());
        setValue(arg[FILTER_MKT_MDL_SEG_CD], scrnMsg.mktMdlSegCd.getValue());
        setValue(arg[FILTER_PRC_LEASE_CMPY_ABBR_NM], scrnMsg.prcLeaseCmpyAbbrNm.getValue());
        setValue(arg[FILTER_DS_ACCT_NM], scrnMsg.dsAcctNm.getValue());
        // Mod Start 2018/07/17 QC#20267
        // 2018/11/27 QC#29319 Mod Start
        //setValue(arg[FILTER_MNF_ITEM_CD], scrnMsg.mnfItemCd.getValue());
        setValue(arg[FILTER_MNF_ITEM_CD], scrnMsg.xxMnfItemCdSrchTxt.getValue());
        // 2018/11/27 QC#29319 Mod End
        // Mod End 2018/07/17 QC#20267
        setValue(arg[FILTER_MDSE_DESC_SHORT_TXT], scrnMsg.mdseDescShortTxt.getValue());
        // Mod Start 2016/10/17 QC#15193
//        setValue(arg[FILTER_COA_MDSE_TP_NM], scrnMsg.coaMdseTpNm.getValue());
        setValue(arg[FILTER_COA_MDSE_TP_NM], scrnMsg.coaProjNm.getValue());
    // Mod End 2016/10/17 QC#15193
        setValue(arg[FILTER_MDSE_ITEM_TP_NM], scrnMsg.mdseItemTpNm.getValue());
        setValue(arg[FILTER_COA_PROD_NM], scrnMsg.coaProdNm.getValue());
        // 2018/11/27 QC#29319 Mod Start
        //setValue(arg[FILTER_T_MDL_NM], scrnMsg.t_MdlNm.getValue());
        setValue(arg[FILTER_T_MDL_NM], scrnMsg.xxTMdlNmSrchTxt.getValue());
        // 2018/11/27 QC#29319 Mod End
        setValue(arg[FILTER_ZEROTH_PROD_CTRL_CD], scrnMsg.zerothProdCtrlCd.getValue());
        setValue(arg[FILTER_FIRST_PROD_CTRL_CD], scrnMsg.firstProdCtrlCd.getValue());
        setValue(arg[FILTER_SCD_PROD_CTRL_CD], scrnMsg.scdProdCtrlCd.getValue());
        setValue(arg[FILTER_THIRD_PROD_CTRL_CD], scrnMsg.thirdProdCtrlCd.getValue());
        setValue(arg[FILTER_FRTH_PROD_CTRL_CD], scrnMsg.frthProdCtrlCd.getValue());
        setValue(arg[FILTER_PRC_TERM_AOT], scrnMsg.prcTermAot.getValue());
        setValue(arg[FILTER_PRC_TERM_UOM_CD], scrnMsg.prcTermUomCd.getValue());
        setValue(arg[FILTER_CUST_BID_QTY], scrnMsg.custBidQty.getValue());
        setValue(arg[FILTER_PRC_FMLA_NM], scrnMsg.prcFmlaNm.getValue());
        setValue(arg[FILTER_OPEN_MKT_FLG], scrnMsg.openMktFlg.getValue());
        setValue(arg[FILTER_COMP_CD], scrnMsg.compCd.getValue());
        setValue(arg[FILTER_XX_YES_NO_CD], scrnMsg.xxYesNoCd.getValue());
        setValue(arg[FILTER_TERM_FROM_MTH_AOT], scrnMsg.termFromMthAot.getValue());
        setValue(arg[FILTER_TERM_THRU_MTH_AOT], scrnMsg.termThruMthAot.getValue());
        setValue(arg[FILTER_PRC_SVC_ZONE_CD], scrnMsg.prcSvcZoneCd.getValue());
        // Mod Start 2016/09/13 QC#11615
//        setValue(arg[FILTER_MDSE_NM], scrnMsg.mdseNm.getValue());
        setValue(arg[FILTER_MDSE_NM], scrnMsg.mdseDescShortTxt_H2.getValue());
        // Mod Start 2016/09/13 QC#11615
        setValue(arg[FILTER_PRC_LIST_MDSE_CD], scrnMsg.prcListMdseCd.getValue());
        setDtValue(arg[FILTER_EFF_FROM_DT_H1], scrnMsg.effFromDt_H1.getValue());
        setDtValue(arg[FILTER_EFF_FROM_DT_H2], scrnMsg.effFromDt_H2.getValue());
        setDtValue(arg[FILTER_EFF_THRU_DT_H1], scrnMsg.effThruDt_H1.getValue());
        setDtValue(arg[FILTER_EFF_THRU_DT_H2], scrnMsg.effThruDt_H2.getValue());
        setValue(arg[FILTER_XX_FULL_NM_H1], scrnMsg.xxFullNm_H1.getValue());
        setValue(arg[FILTER_XX_FULL_NM_H2], scrnMsg.xxFullNm_H2.getValue());
        setDtValue(arg[FILTER_CRAT_DT_H1], scrnMsg.cratDt_H1.getValue());
        setDtValue(arg[FILTER_CRAT_DT_H2], scrnMsg.cratDt_H2.getValue());
        setDtValue(arg[FILTER_LAST_UPD_DT_H1], scrnMsg.lastUpdDt_H1.getValue());
        setDtValue(arg[FILTER_LAST_UPD_DT_H2], scrnMsg.lastUpdDt_H2.getValue());
    }

    public static void clearScrn(NMAL7040BMsg scrnMsg) {
        scrnMsg.prcQlfyTpCd.clear();
        scrnMsg.prcQlfyValTxt.clear();
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.xxPrcQlfyValSrchTxt.clear();
        // 2018/11/27 QC#29319 Add End
        scrnMsg.mdlNm.clear();
        scrnMsg.prcMtrPkgNm.clear();
        scrnMsg.prcPgmTpCd.clear();
        scrnMsg.prcEquipTpCd.clear();
        scrnMsg.svcSegCd.clear();
        scrnMsg.prcInOutRgCd.clear();
        scrnMsg.prcListEquipConfigNum.clear();
        scrnMsg.prcListEquipConfigNm.clear();
        scrnMsg.pkgUomCd.clear();
        scrnMsg.prcRateTpCd.clear();
        scrnMsg.prcSvcPlnTpCd.clear();
        scrnMsg.prcSvcContrTpCd.clear();
        scrnMsg.mtrLbNm.clear();
        // 2018/11/17 QC#29147 Mod Start
        // scrnMsg.prcListBandCd.clear();
        scrnMsg.prcListBandDescTxt.clear();
        // 2018/11/17 QC#29147 Mod End
        scrnMsg.prcSvcTierTpCd.clear();
        scrnMsg.splyAgmtPlnNm.clear();
        scrnMsg.mdseCd.clear();
        scrnMsg.prcAddlChrgTpCd.clear();
        scrnMsg.mktMdlSegCd.clear();
        scrnMsg.prcLeaseCmpyAbbrNm.clear();
        scrnMsg.dsAcctNm.clear();
        // Mod Start 2018/07/17 QC#20267
        scrnMsg.mnfItemCd.clear();
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.xxMnfItemCdSrchTxt.clear();
        // 2018/11/27 QC#29319 Add End
        // Mod End 2018/07/17 QC#20267
        scrnMsg.mdseDescShortTxt.clear();
        // Mod Start 2016/10/17 QC#15193
//        scrnMsg.coaMdseTpNm.clear();
        scrnMsg.coaProjNm.clear();
        // Mod End 2016/10/17 QC#15193
        scrnMsg.mdseItemTpNm.clear();
        scrnMsg.coaProdNm.clear();
        scrnMsg.t_MdlNm.clear();
        // 2018/11/27 QC#29319 Add Start
        scrnMsg.xxTMdlNmSrchTxt.clear();
        // 2018/11/27 QC#29319 Add End
        scrnMsg.zerothProdCtrlCd.clear();
        scrnMsg.firstProdCtrlCd.clear();
        scrnMsg.scdProdCtrlCd.clear();
        scrnMsg.thirdProdCtrlCd.clear();
        scrnMsg.frthProdCtrlCd.clear();
        scrnMsg.prcTermAot.clear();
        scrnMsg.prcTermUomCd.clear();
        scrnMsg.custBidQty.clear();
        scrnMsg.prcFmlaNm.clear();
        scrnMsg.openMktFlg.clear();
        scrnMsg.compCd.clear();
        scrnMsg.xxYesNoCd.clear();
        scrnMsg.termFromMthAot.clear();
        scrnMsg.termThruMthAot.clear();
        scrnMsg.prcSvcZoneCd.clear();
        scrnMsg.mdseCd.clear();
        // Mod Start 2016/09/13 QC#11615
//        scrnMsg.mdseNm.clear();
        scrnMsg.mdseDescShortTxt_H2.clear();
        // Mod End 2016/09/13 QC#11615
        scrnMsg.prcListMdseCd.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effFromDt_H2.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.effThruDt_H2.clear();
        scrnMsg.xxFullNm_H1.clear();
        scrnMsg.xxFullNm_H2.clear();
        scrnMsg.cratDt_H1.clear();
        scrnMsg.cratDt_H2.clear();
        scrnMsg.lastUpdDt_H1.clear();
        scrnMsg.lastUpdDt_H2.clear();
    }

    public static void setScrnCtrl(NMAL7040BMsg scrnMsg) {
        scrnMsg.prcCatgCd_H.setInputProtected(true);
        scrnMsg.prcCatgNm_H.setInputProtected(true);

        scrnMsg.prcTermAot.setAppFracDigit(0);
    }

    private static String getString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBStringItem) obj).getValue();
    }

    private static BigDecimal getBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBBigDecimalItem) obj).getValue();
    }

    private static String getDtString(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((EZDBDateItem) obj).getValue();
    }

    private static void setValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) obj, val);
    }

    private static void setValue(Object obj, BigDecimal val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) obj, val);
    }

    private static void setDtValue(Object obj, String val) {
        if (obj == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue((EZDBDateItem) obj, val);
    }


    /**
     * Set Popup Argument for NWAL1130.
     * @param scrnMsg NMAL7040BMsg
     * @param eventNm String
     * @param eventRow int
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setArgumentNWAL1130(NMAL7040BMsg scrnMsg, String eventNm, int eventRow, String glblCmpyCd) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        scrnMsg.xxPopPrm_ZA.clear();

        if ("OpenWin_Model".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Service Model Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" MN.T_GLBL_CMPY_CD GLBL_CMPY_CD");
            tblNmP2.append(",MN.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(MN.T_MDL_ID) T_MDL_ID");
            tblNmP2.append(",MN.T_MDL_NM");
            tblNmP2.append(" FROM MDL_NM MN");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" MN.T_GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND MN.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Service Model ID";
            whereArray0[1] = "T_MDL_ID";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Service Model Name";
            whereArray1[1] = "T_MDL_NM";
            whereArray1[2] = scrnMsg.mdlNm.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Service Model ID";
            columnArray0[1] = "T_MDL_ID";
            columnArray0[2] = BigDecimal.valueOf(22);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Service Model Name";
            columnArray1[1] = "T_MDL_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "Y";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "T_MDL_ID";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_MtrPkg".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Meter Package Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PMP.GLBL_CMPY_CD");
            tblNmP2.append(",PMP.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(PMPM.MDL_ID) MDL_ID");
            tblNmP2.append(",PMPM.MDL_NM");
            tblNmP2.append(",TO_CHAR(PMP.PRC_MTR_PKG_PK) PRC_MTR_PKG_PK");
            tblNmP2.append(",PMP.PRC_MTR_PKG_NM");
            tblNmP2.append(",PMP.PRC_MTR_PKG_DESC_TXT");
            tblNmP2.append(" FROM PRC_MTR_PKG PMP");
            tblNmP2.append(",PRC_MTR_PKG_MDL PMPM");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PMP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PMP.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PMP.GLBL_CMPY_CD = PMPM.GLBL_CMPY_CD");
            tblNmP2.append(" AND PMP.EZCANCELFLAG = PMPM.EZCANCELFLAG");
            tblNmP2.append(" AND PMP.PRC_MTR_PKG_PK = PMPM.PRC_MTR_PKG_PK");
            tblNmP2.append(" AND (EXISTS (SELECT '1'");
            tblNmP2.append(" FROM PRC_MTR_PKG_BLLG_MTR PMPBM");
            tblNmP2.append(",PRC_MTR_PKG_PHYS_MTR PMPPM");
            tblNmP2.append(" WHERE PMPBM.GLBL_CMPY_CD = PMP.GLBL_CMPY_CD");
            tblNmP2.append(" AND PMPBM.EZCANCELFLAG = PMP.EZCANCELFLAG");
            tblNmP2.append(" AND PMPBM.PRC_MTR_PKG_PK = PMP.PRC_MTR_PKG_PK");
            tblNmP2.append(" AND PMPBM.GLBL_CMPY_CD = PMPPM.GLBL_CMPY_CD");
            tblNmP2.append(" AND PMPBM.EZCANCELFLAG = PMPPM.EZCANCELFLAG");
            tblNmP2.append(" AND PMPBM.PRC_MTR_PKG_BLLG_MTR_PK = PMPPM.PRC_MTR_PKG_BLLG_MTR_PK");
            tblNmP2.append(" )"); // Select Close
            tblNmP2.append(" )"); // Exist Close

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Meter Package Name";
            whereArray0[1] = "PRC_MTR_PKG_NM";
            whereArray0[2] = scrnMsg.prcMtrPkgNm.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Service Model Name";
            whereArray1[1] = "MDL_NM";
            whereArray1[2] = scrnMsg.mdlNm.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Unit of Measuree";
            whereArray2[1] = "PRC_SVC_UOM_TXT";
            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Meter Package PK";
            whereArray3[1] = "PRC_MTR_PKG_PK";
            whereArray3[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray3[3] = "Y";
            whereListP3.add(whereArray3);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Model ID";
            columnArray0[1] = "MDL_ID";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Service Model Name";
            columnArray1[1] = "MDL_NM";
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Meter Package Name";
            columnArray2[1] = "PRC_MTR_PKG_NM";
            columnArray2[2] = BigDecimal.valueOf(25);
            columnArray2[3] = "Y";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Meter Package Description";
            columnArray3[1] = "PRC_MTR_PKG_DESC_TXT";
            columnArray3[2] = BigDecimal.valueOf(25);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Meter Package PK";
            columnArray4[1] = "PRC_MTR_PKG_PK";
            columnArray4[2] = BigDecimal.valueOf(12);
            columnArray4[3] = "N";
            columnListP4.add(columnArray4);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "PRC_MTR_PKG_PK";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

        } else if ("OpenWin_QualifierValueProd-1".equals(eventNm) || "OpenWin_QualifierValueProd-2".equals(eventNm) || "OpenWin_QualifierValueProd-3".equals(eventNm) || "OpenWin_QualifierValueProd-4".equals(eventNm)
                || "OpenWin_QualifierValueProd-5".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Product Hierarchy Popup";

            String columnCdNm = "PROD_CTRL_CD";
            String columnNmNm = "PROD_CTRL_DESC_TXT";
            String mdseStruElmntTpCd = MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP;
            if ("OpenWin_QualifierValueProd-1".equals(eventNm)) {
                mdseStruElmntTpCd = MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP;
            } else if ("OpenWin_QualifierValueProd-2".equals(eventNm)) {
                mdseStruElmntTpCd = MDSE_STRU_ELMNT_TP.PRODUCT_LINE;
            } else if ("OpenWin_QualifierValueProd-3".equals(eventNm)) {
                mdseStruElmntTpCd = MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2;
            } else if ("OpenWin_QualifierValueProd-4".equals(eventNm)) {
                mdseStruElmntTpCd = MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3;
            } else if ("OpenWin_QualifierValueProd-5".equals(eventNm)) {
                mdseStruElmntTpCd = MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4;
            }

            tblNmP2.append(" SELECT");
            tblNmP2.append(" DISTINCT");
            tblNmP2.append(" PC.GLBL_CMPY_CD");
            tblNmP2.append(",PC.EZCANCELFLAG");
            tblNmP2.append(",PC.").append(columnCdNm);
            tblNmP2.append(",PC.").append(columnNmNm);
            tblNmP2.append(" FROM");
            tblNmP2.append(" PROD_CTRL PC");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PC.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(mdseStruElmntTpCd).append("'");
            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Prod Hierarchy";
            whereArray0[1] = columnCdNm;
            // 2018/11/27 QC#29319 Mod Start
            //whereArray0[2] = scrnMsg.prcQlfyValTxt.getValue();
            whereArray0[2] = scrnMsg.xxPrcQlfyValSrchTxt.getValue();
            if (MAX_LENGTH_NWAL1130_QLFY_VAL < scrnMsg.xxPrcQlfyValSrchTxt.getValue().length()) {
                whereArray0[2] = "";
            }
            // 2018/11/27 QC#29319 Mod End
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Prod Hierarchy Name";
            whereArray1[1] = columnNmNm;
            whereArray1[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Prod Hierarchy";
            columnArray0[1] = columnCdNm;
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Prod Hierarchy Name";
            columnArray1[1] = columnNmNm;
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = columnCdNm;
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);

        } else if ("OpenWin_SupplyPlan".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Supply Plan Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" SAP.GLBL_CMPY_CD");
            tblNmP2.append(",SAP.EZCANCELFLAG");
            tblNmP2.append(",TO_CHAR(SAP.SPLY_AGMT_PLN_PK) SPLY_AGMT_PLN_PK");
            tblNmP2.append(",SAP.ACTV_FLG");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_NM");
            tblNmP2.append(",SAP.SPLY_AGMT_PLN_SHORT_NM");
            tblNmP2.append(",SAPT.SPLY_AGMT_PLN_TP_DESC_TXT");
            tblNmP2.append(",SADT.SPLY_AGMT_DOC_TP_DESC_TXT");
            tblNmP2.append(",SAP.EFF_FROM_DT");
            tblNmP2.append(",SAP.EFF_THRU_DT");
            tblNmP2.append(" FROM SPLY_AGMT_PLN SAP");
            tblNmP2.append(",SPLY_AGMT_PLN_TP SAPT");
            tblNmP2.append(",SPLY_AGMT_DOC_TP SADT");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" SAP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND SAP.GLBL_CMPY_CD = SAPT.GLBL_CMPY_CD");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = SAPT.EZCANCELFLAG");
            tblNmP2.append(" AND SAP.SPLY_AGMT_PLN_TP_CD = SAPT.SPLY_AGMT_PLN_TP_CD");
            tblNmP2.append(" AND SAP.GLBL_CMPY_CD = SADT.GLBL_CMPY_CD");
            tblNmP2.append(" AND SAP.EZCANCELFLAG = SADT.EZCANCELFLAG");
            tblNmP2.append(" AND SAP.SPLY_AGMT_DOC_TP_CD = SADT.SPLY_AGMT_DOC_TP_CD");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Plan ID";
            whereArray0[1] = "SPLY_AGMT_PLN_PK";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Plan Name";
            whereArray1[1] = "SPLY_AGMT_PLN_NM";
            whereArray1[2] = scrnMsg.splyAgmtPlnNm.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "Plan Short Name";
            whereArray2[1] = "SPLY_AGMT_PLN_SHORT_NM";
            whereArray2[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray2[3] = "Y";
            whereListP3.add(whereArray2);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Plan Type Name";
            whereArray4[1] = "SPLY_AGMT_PLN_TP_DESC_TXT";
            whereArray4[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray4[3] = "Y";
            whereListP3.add(whereArray4);

            Object[] whereArray5 = new Object[CMN_PRM_WHERE_NUM];
            whereArray5[0] = "Document Type Name";
            whereArray5[1] = "SPLY_AGMT_DOC_TP_DESC_TXT";
            whereArray5[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray5[3] = "Y";
            whereListP3.add(whereArray5);

            Object[] whereArray6 = new Object[CMN_PRM_WHERE_NUM];
            whereArray6[0] = "Effective Date From";
            whereArray6[1] = "EFF_FROM_DT";
            whereArray6[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray6[3] = "N";
            whereListP3.add(whereArray6);

            Object[] whereArray7 = new Object[CMN_PRM_WHERE_NUM];
            whereArray7[0] = "Effective Date To";
            whereArray7[1] = "EFF_THRU_DT";
            whereArray7[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray7[3] = "N";
            whereListP3.add(whereArray7);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Plan ID";
            columnArray0[1] = "SPLY_AGMT_PLN_PK";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Active";
            columnArray1[1] = "ACTV_FLG";
            columnArray1[2] = BigDecimal.valueOf(5);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "Plan Name";
            columnArray2[1] = "SPLY_AGMT_PLN_NM";
            columnArray2[2] = BigDecimal.valueOf(32);
            columnArray2[3] = "Y";
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Plan Short Name";
            columnArray3[1] = "SPLY_AGMT_PLN_SHORT_NM";
            columnArray3[2] = BigDecimal.valueOf(18);
            columnArray3[3] = "N";
            columnListP4.add(columnArray3);

            Object[] columnArray5 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray5[0] = "Plan Type Name";
            columnArray5[1] = "SPLY_AGMT_PLN_TP_DESC_TXT";
            columnArray5[2] = BigDecimal.valueOf(15);
            columnArray5[3] = "N";
            columnListP4.add(columnArray5);

            Object[] columnArray6 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray6[0] = "Document Type Name";
            columnArray6[1] = "SPLY_AGMT_DOC_TP_DESC_TXT";
            columnArray6[2] = BigDecimal.valueOf(18);
            columnArray6[3] = "N";
            columnListP4.add(columnArray6);

            Object[] columnArray7 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray7[0] = "Effective Date From";
            columnArray7[1] = "EFF_FROM_DT";
            columnArray7[2] = BigDecimal.valueOf(13);
            columnArray7[3] = "N";
            columnListP4.add(columnArray7);

            Object[] columnArray8 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray8[0] = "Effective Date To";
            columnArray8[1] = "EFF_THRU_DT";
            columnArray8[2] = BigDecimal.valueOf(13);
            columnArray8[3] = "N";
            columnListP4.add(columnArray8);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "SPLY_AGMT_PLN_PK";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_DsAcctCust".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Lease Account# Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" STC.GLBL_CMPY_CD");
            tblNmP2.append(",STC.EZCANCELFLAG");
            tblNmP2.append(",STC.SELL_TO_CUST_CD");
            tblNmP2.append(",STC.DS_ACCT_NM");
            tblNmP2.append(" FROM SELL_TO_CUST STC");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" STC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND STC.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND STC.DS_ACCT_TP_CD = '").append(DS_ACCT_TP.CUSTOMER).append("'");
            tblNmP2.append(" AND STC.DS_ACCT_CLS_CD = '").append(DS_ACCT_CLS.LEASE_CO).append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Lease Account#";
            whereArray0[1] = "SELL_TO_CUST_CD";
            whereArray0[2] = scrnMsg.xxPopPrm_ZA.getValue();
            whereArray0[3] = "N";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Lease Account Name";
            whereArray1[1] = "DS_ACCT_NM";
            whereArray1[2] = scrnMsg.dsAcctNm.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_WHERE_NUM];
            columnArray0[0] = "Lease Account#";
            columnArray0[1] = "SELL_TO_CUST_CD";
            columnArray0[2] = BigDecimal.valueOf(28);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_WHERE_NUM];
            columnArray1[0] = "Lease Account Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(60);
            columnArray1[3] = "Y";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "SELL_TO_CUST_CD";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);

        } else if ("OpenWin_MtrLb".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Meter Type Popup";
            String slsDt = ZYPDateUtil.getSalesDate();

            tblNmP2.append(" SELECT");
            tblNmP2.append(" DISTINCT");
            tblNmP2.append(" MPV.GLBL_CMPY_CD");
            tblNmP2.append(",MPV.EZCANCELFLAG");
            tblNmP2.append(",BLLG_MTR_LB_CD");
            tblNmP2.append(",BLLG_MTR_LB_NM");
            tblNmP2.append(" FROM PRC_MTR_PKG_MTR_STRU_V MPV");
            tblNmP2.append(" ,PRC_MTR_PKG P");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" MPV.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND MPV.EZCANCELFLAG = '").append("0").append("'");
            tblNmP2.append(" AND MPV.MDL_MTR_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.MDL_MTR_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.BLLG_MTR_MAP_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.BLLG_MTR_MAP_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.MTR_PKG_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.MTR_PKG_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.MTR_PKG_MDL_EFF_FROM_DT <= '").append(slsDt).append("'");
            tblNmP2.append(" AND NVL(MPV.MTR_PKG_MDL_EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            tblNmP2.append(" AND MPV.GLBL_CMPY_CD = P.GLBL_CMPY_CD");
            tblNmP2.append(" AND MPV.EZCANCELFLAG = P.EZCANCELFLAG");
            tblNmP2.append(" AND MPV.PRC_MTR_PKG_PK = P.PRC_MTR_PKG_PK");
            if (ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgNm)) {
                tblNmP2.append(" AND P.PRC_MTR_PKG_NM = '").append(scrnMsg.prcMtrPkgNm.getValue()).append("'");
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.mdlNm)) {
                tblNmP2.append(" AND MPV.MDL_NM = '").append(scrnMsg.mdlNm.getValue()).append("'");
            }
            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Meter Type";
            whereArray0[1] = "BLLG_MTR_LB_CD";
            whereArray0[2] = "";
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Meter Type Name";
            whereArray1[1] = "BLLG_MTR_LB_NM";
            whereArray1[2] = scrnMsg.mtrLbNm.getValue();
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Meter Type";
            columnArray0[1] = "BLLG_MTR_LB_CD";
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Meter Type Name";
            columnArray1[1] = "BLLG_MTR_LB_NM";
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "BLLG_MTR_LB_CD";
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);
         // 2018/11/17 QC#29147 Add Start
        } else if ("OpenWin_PrcListBand".equals(eventNm)) {
            suffixP0 = "";
            scrnNmP1 = "Price List Band Popup";

            tblNmP2.append(" SELECT");
            tblNmP2.append(" PLB.GLBL_CMPY_CD GLBL_CMPY_CD");
            tblNmP2.append(",PLB.EZCANCELFLAG");
            tblNmP2.append(",PLB.PRC_LIST_BAND_CD");
            tblNmP2.append(",PLB.PRC_LIST_BAND_DESC_TXT");
            tblNmP2.append(" FROM PRC_LIST_BAND PLB");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" PLB.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND PLB.EZCANCELFLAG = '").append("0").append("'");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Price List Band Code";
            whereArray0[1] = "PRC_LIST_BAND_CD";
            whereArray0[2] = "";
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Description";
            whereArray1[1] = "PRC_LIST_BAND_DESC_TXT";
            whereArray0[2] = scrnMsg.prcListBandDescTxt.getValue();;
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Price List Band Code";
            columnArray0[1] = "PRC_LIST_BAND_CD";
            columnArray0[2] = BigDecimal.valueOf(20);
            columnArray0[3] = "N";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Description";
            columnArray1[1] = "PRC_LIST_BAND_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = "Y";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray1 = new Object[2];
            sortConditionArray1[0] = "PRC_LIST_BAND_DESC_TXT";
            sortConditionArray1[1] = "ASC";
            sortConditionListP5.add(sortConditionArray1);
        // 2018/11/17 QC#29147 Add End
        }
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * Set Popup Argument for NMAL6800.
     * @param scrnMsg NMAL7040BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6800(NMAL7040BMsg scrnMsg, String eventNm, int eventRow) {
        Object[] param = new Object[10];

        scrnMsg.xxPopPrm_Z0.clear();
        scrnMsg.xxPopPrm_ZA.clear();

        if ("OpenWin_QualifierValueItem".equals(eventNm)) {
            // 2018/11/27 QC#29319 Mod Start
            //param[0] = scrnMsg.prcQlfyValTxt;
            param[0] = scrnMsg.xxPrcQlfyValSrchTxt;
            if (MAX_LENGTH_NMAL6800_QLFY_VAL < scrnMsg.xxPrcQlfyValSrchTxt.getValue().length()) {
                ((EZDBStringItem)param[0]).clear();
            }
            // 2018/11/27 QC#29319 Mod End

        } else if ("OpenWin_PrcListMdse".equals(eventNm)) {
            param[0] = scrnMsg.prcListMdseCd;

        } else if ("OpenWin_Mdse".equals(eventNm)) {
            param[0] = scrnMsg.mdseCd;
        }

        param[1] = scrnMsg.xxPopPrm_ZA;
        param[2] = scrnMsg.xxPopPrm_ZA;
        param[3] = scrnMsg.xxPopPrm_ZA;
        param[4] = scrnMsg.xxPopPrm_ZA;
        param[5] = scrnMsg.xxPopPrm_ZA;
        param[6] = scrnMsg.xxPopPrm_ZA;
        if (param[7] == null) {
            param[7] = scrnMsg.xxPopPrm_ZA;
        }
        param[8] = scrnMsg.xxPopPrm_ZA;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "08");
        param[9] = scrnMsg.xxPopPrm_Z0;

        return param;
    }

    /**
     * Set Popup Argument for NMAL6050.
     * @param scrnMsg NMAL7040BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6050(NMAL7040BMsg scrnMsg, String eventNm, int eventRow) {
        // Parameter Clear
        scrnMsg.xxPopPrm_Z0.clear();
        scrnMsg.xxPopPrm_Z1.clear();
        scrnMsg.xxPopPrm_Z2.clear();
        scrnMsg.xxPopPrm_Z3.clear();
        scrnMsg.xxPopPrm_Z4.clear();
        scrnMsg.xxPopPrm_Z5.clear();
        scrnMsg.xxPopPrm_Z6.clear();
        scrnMsg.xxPopPrm_Z7.clear();
        scrnMsg.xxPopPrm_Z8.clear();
        scrnMsg.xxPopPrm_Z9.clear();
        scrnMsg.xxPopPrm_ZA.clear();

        Object[] param = new Object[11];

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z0, "COA_PROJ");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z1, "COA_PROJ_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z2, "COA_PROJ_DESC_TXT");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z3, "COA_PROJ_CD");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z4, "Merchandise Type Popup");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z5, "Merchandise Type Code");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z6, "Merchandise Type Name");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z7, "Merchandise Type ID");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_Z8, "Merchandise Type Name");

        // 2018/11/27 QC#29319 Mod Start
        //param[9] = scrnMsg.prcQlfyValTxt;
        param[9] = scrnMsg.xxPrcQlfyValSrchTxt;
        if (MAX_LENGTH_NMAL6050_QLFY_VAL < scrnMsg.xxPrcQlfyValSrchTxt.getValue().length()) {
            ((EZDBStringItem)param[9]).clear();
        }
        // 2018/11/27 QC#29319 Mod End
        param[10] = scrnMsg.xxPopPrm_ZA;

        param[0] = scrnMsg.xxPopPrm_Z0;
        param[1] = scrnMsg.xxPopPrm_Z1;
        param[2] = scrnMsg.xxPopPrm_Z2;
        param[3] = scrnMsg.xxPopPrm_Z3;
        param[4] = scrnMsg.xxPopPrm_Z4;
        param[5] = scrnMsg.xxPopPrm_Z5;
        param[6] = scrnMsg.xxPopPrm_Z6;
        param[7] = scrnMsg.xxPopPrm_Z7;
        param[8] = scrnMsg.xxPopPrm_Z8;
        return param;
    }

    /**
     * toStr.
     * @param val BigDecimal
     * @return String
     */
    public static String toStr(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        } else {
            return "";
        }
    }
}
