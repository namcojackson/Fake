/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLG.NLGC002001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttribute;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CYCLE_CNT_CATGTMsg;
import business.db.HAZ_MATTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.SW_LIC_CTRL_TPTMsg;
import business.db.WMS_INBD_ITEM_SER_WRKTMsg;
import business.db.WMS_INBD_ITEM_UPC_WRKTMsg;
import business.db.WMS_INBD_ITEM_WRKTMsg;
import business.db.WMS_MDSE_LISTTMsg;

import com.canon.cusa.s21.common.NLX.NLXC014001.NLXC014001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 * <pre>
 * Item Download Common Logic of NLGB002001(RWS and Item Download) and NLGB003001(MDSE to WMS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/13/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 * 11/10/2015   CSAI            K.Lee           Update          S21NA Initial
 * 02/07/2017   CITS            Y.Fujii         Update          QC#17422
 * 02/27/2019   CITS            M.Naito         Update          QC#30539
 * 03/22/2019   Fujitsu         T.Ogura         Update          QC#30565
 *</pre>
 */
public class NLGC002001 implements NLGC002001Constant {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** SSM-Batch-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** WMS_MDSE_DESC_TXT_01 Length */    
    private int wmsMdseDescTxt01Length = DEF_WMS_MDSE_DESC_TXT_01_LEN;

    /** WMS_MDSE_DESC_TXT_02 Length */    
    private int wmsMdseDescTxt02Length = DEF_WMS_MDSE_DESC_TXT_02_LEN;

    /**
     * Constructor
     * @param glblCmpyCd Global Company Code
     */
    public NLGC002001(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
        BigDecimal wmsMdseDescTxt01NumConst = ZYPCodeDataUtil.getNumConstValue("WMS_MDSE_TXT_01_LEN", glblCmpyCd); 
        BigDecimal wmsMdseDescTxt02NumConst = ZYPCodeDataUtil.getNumConstValue("WMS_MDSE_TXT_02_LEN", glblCmpyCd);
        if (wmsMdseDescTxt01NumConst != null) {
            this.wmsMdseDescTxt01Length =  wmsMdseDescTxt01NumConst.intValue();
        }
        if (wmsMdseDescTxt02NumConst != null) {
            this.wmsMdseDescTxt02Length =  wmsMdseDescTxt02NumConst.intValue();
        }
    }

    /**
     * Create WMS_INBD_ITEM_WRKTMsg, WMS_INBD_ITEM_UPC_WRKTMsg List,
     * WMS_INBD_ITEM_SER_WRKTMsg List. Parameter rs must has specific
     * column data below.
     * <P>
     * M_MDSE_CD, FIFTH_PROD_CTRL_CD, MDSE_NM, VND_CD, M_FRT_CLS_CD,
     * NMFC_CLS_NUM, NMFC_ITEM_NUM, NMFC_ITEM_DESC_TXT,
     * NMFC_ITEM_SUB_NUM, SHPG_SER_TAKE_FLG,
     * THIS_MTH_TOT_STD_COST_AMT, NEXT_MTH_TOT_STD_COST_AMT,
     * SCD_PROD_CTRL_CD, THIRD_PROD_CTRL_CD, FRTH_PROD_CTRL_CD,
     * COA_PROD_CD, ORIG_CTRY_CD, INVTY_CTRL_FLG, MDSE_FML_NM,
     * M_INTG_PROD_CATG_CD, INTG_PROD_CATG_NM, TRF_CD, MI_MDSE_CD,
     * BAT_NUM_CTRL_FLG, HAZ_MAT_FLG, HAZ_MAT_CD, HAZ_CLS_NM,
     * SFTY_HAZ_ID_NUM, LOT_CTRL_FLG
     * <P>
     * This method doesn't set WMS_SQ_NUM, WH_CD, and each primary
     * key.
     * @param rs ResultSet
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRKTMsg List as
     * return value
     * @param wmsInbdItemSerWrkList WMS_INBD_ITEM_SER_WRKTMsg List as
     * return value
     * @param intfcRecActCd INTFC_REC_ACT_CD
     * @return WMS_INBD_ITEM_WRKTMsg
     * @throws SQLException SQLException
     */
    public WMS_INBD_ITEM_WRKTMsg createWmsInbdItemInfo(ResultSet rs, List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList, List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList, String intfcRecActCd) throws SQLException {

        String mdseCd = rs.getString(COL_WML_MDSE_CD);
        List<NLGC0020bean01> mdseSerNumRngList = null;
        if (ZYPConstant.FLG_ON_Y.equals(rs.getString(NLGC002001Constant.COL_SHPG_SER_TAKE_FLG))) {
            // Create WMS_INBD_ITEM_SER_WRK Data when
            // MDSE.SHPG_SER_TAKE_FLG = 'Y'.

            // Get MDSE_SER_NUM_RNG.
            mdseSerNumRngList = getMdseSerNumRngList(mdseCd);
            // Create WMS_INBD_ITEM_SER_WRK Data.
            createWmsInbdItemSerWrk(wmsInbdItemSerWrkList, mdseSerNumRngList, intfcRecActCd);
        }

        // Get MDSE_STORE_PKG.
        List<MDSE_STORE_PKGTMsg> mdseStorePkgTList = getMdseStorePkgList(mdseCd);
        // Create WMS_INBD_ITEM_UPC_WRK Data.
        createWmsInbdItemUpcWrk(wmsInbdItemUpcWrkList, rs, mdseStorePkgTList, intfcRecActCd);
        // Create WMS_INBD_ITEM_WRK Data.
        WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT = createWmsInbdItemWrk(rs, mdseStorePkgTList, mdseSerNumRngList, intfcRecActCd);

        return wmsInbdItemWrkT;
    }

    /**
     * Create WMS_INBD_ITEM_WRK Data. Parameter rs must has specific
     * column data below.
     * <P>
     * M_MDSE_CD, FIFTH_PROD_CTRL_CD, MDSE_NM, VND_CD, M_FRT_CLS_CD,
     * NMFC_CLS_NUM, NMFC_ITEM_NUM, NMFC_ITEM_DESC_TXT,
     * NMFC_ITEM_SUB_NUM, SHPG_SER_TAKE_FLG,
     * THIS_MTH_TOT_STD_COST_AMT, NEXT_MTH_TOT_STD_COST_AMT,
     * SCD_PROD_CTRL_CD, THIRD_PROD_CTRL_CD, FRTH_PROD_CTRL_CD,
     * COA_PROD_CD, ORIG_CTRY_CD, INVTY_CTRL_FLG, MDSE_FML_NM,
     * M_INTG_PROD_CATG_CD, INTG_PROD_CATG_NM, TRF_CD, MI_MDSE_CD,
     * BAT_NUM_CTRL_FLG, HAZ_MAT_FLG, HAZ_MAT_CD, HAZ_CLS_NM,
     * SFTY_HAZ_ID_NUM, LOT_CTRL_FLG, MSI_MDSE_CD, CRTN_PER_LAYER_QTY,
     * CRTN_TIER_PER_PLT_QTY, UPC_CD.
     * <P>
     * This method doesn't set WMS_SQ_NUM, WH_CD,
     * WMS_INBD_ITEM_WRK_PK.
     * @param rs ResultSet
     * @param mdseStorePkgTList MDSE_STORE_PKGTMsg List
     * @param mdseSerNumRngList NLGC0020bean01 List
     * @param intfcRecActCd INTFC_REC_ACT_CD
     * @throws SQLException SQLException
     * @return WMS_INBD_ITEM_WRKTMsg
     */
    private WMS_INBD_ITEM_WRKTMsg createWmsInbdItemWrk(ResultSet rs, List<MDSE_STORE_PKGTMsg> mdseStorePkgTList, List<NLGC0020bean01> mdseSerNumRngList, String intfcRecActCd) throws SQLException {

        WMS_INBD_ITEM_WRKTMsg wmsInbdItemWrkT = new WMS_INBD_ITEM_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.procStsCd, PROC_STS.IN_COMPLETED);
        wmsInbdItemWrkT.errMsgCd.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.intfcTpId, VAL_INTFC_TP_ID_03);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.intfcRecTpId, VAL_INTFC_REC_TP_ID_1);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.intfcRecActCd, intfcRecActCd);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsCmpyCd, VAL_WMS_CMPY_CD_01);
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsItemCd, ZYPCommonFunc.trimHead(NLXC014001.nullToEmpty(rs.getString(COL_M_MDSE_CD))));
        if (NLXC014001.nullToEmpty(rs.getString(COL_FIFTH_PROD_CTRL_CD)).length() > 1) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsProdCd, NLXC014001.nullToEmpty(rs.getString(COL_FIFTH_PROD_CTRL_CD)).substring(LG_CUT_FIFTH_PROD_CTRL_CD_FROM, LG_CUT_FIFTH_PROD_CTRL_CD_TO));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsProdCd, NLXC014001.nullToEmpty(rs.getString(COL_FIFTH_PROD_CTRL_CD)));
        }

        if (NLXC014001.nullToEmpty(rs.getString(COL_MDSE_NM)).length() > wmsMdseDescTxt01Length) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_01, NLXC014001.nullToEmpty(rs.getString(COL_MDSE_NM)).substring(0, wmsMdseDescTxt01Length));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_01, rs.getString(COL_MDSE_NM));
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.vndCd, rs.getString(COL_VND_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsFrtClsCd, rs.getString(COL_M_FRT_CLS_CD));

        if (ZYPCommonFunc.hasValue(rs.getString(COL_M_FRT_CLS_CD))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMnfClsNum, rs.getBigDecimal(COL_NMFC_CLS_NUM));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mnfItemNum_01, rs.getString(COL_NMFC_ITEM_NUM));
            if (NLXC014001.nullToEmpty(rs.getString(COL_NMFC_ITEM_DESC_TXT)).length() > LG_CUT_MNF_ITEM_DESC_TXT_01_TO) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mnfItemDescTxt_01, NLXC014001.nullToEmpty(rs.getString(COL_NMFC_ITEM_DESC_TXT)).substring(LG_CUT_MNF_ITEM_DESC_TXT_01_FROM, LG_CUT_MNF_ITEM_DESC_TXT_01_TO));
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mnfItemDescTxt_01, NLXC014001.nullToEmpty(rs.getString(COL_NMFC_ITEM_DESC_TXT)));
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mnfItemNum_02, rs.getString(COL_NMFC_ITEM_SUB_NUM));
            if (NLXC014001.nullToEmpty(rs.getString(COL_NMFC_ITEM_DESC_TXT)).length() <= LG_CUT_MNF_ITEM_DESC_TXT_02_FROM) {
                wmsInbdItemWrkT.mnfItemDescTxt_02.clear();
            } else if (NLXC014001.nullToEmpty(rs.getString(COL_NMFC_ITEM_DESC_TXT)).length() <= LG_CUT_MNF_ITEM_DESC_TXT_02_TO) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mnfItemDescTxt_02, NLXC014001.nullToEmpty(rs.getString(COL_NMFC_ITEM_DESC_TXT)).substring(LG_CUT_MNF_ITEM_DESC_TXT_02_FROM));
            }
        }
        for (MDSE_STORE_PKGTMsg mdseStorePkgT : mdseStorePkgTList) {
            if (VAL_PKG_UOM_CD_CA.equals(mdseStorePkgT.pkgUomCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.unitCrtnQty, mdseStorePkgT.inEachQty);
            } else if (VAL_PKG_UOM_CD_PL.equals(mdseStorePkgT.pkgUomCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.unitPltQty, mdseStorePkgT.inEachQty);
            }
        }
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.serNumFlg, rs.getString(COL_SHPG_SER_TAKE_FLG));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.serLgNum, new BigDecimal(getSerLgNum(mdseSerNumRngList)));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.curStdCostNum, getDecimalValue(wmsInbdItemWrkT, ATT_CUR_STD_COST_NUM, rs.getBigDecimal(COL_THIS_MTH_TOT_STD_COST_AMT)));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.futStdCostNum, getDecimalValue(wmsInbdItemWrkT, ATT_FUT_STD_COST_NUM, rs.getBigDecimal(COL_NEXT_MTH_TOT_STD_COST_AMT)));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsSerTpCd, getSerTp(mdseSerNumRngList));
        if (NLXC014001.nullToEmpty(rs.getString(COL_SCD_PROD_CTRL_CD)).length() > 5) {
            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_01, rs.getString(COL_SCD_PROD_CTRL_CD).substring(1, 5));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_01, rs.getString(COL_SCD_PROD_CTRL_CD).substring(0, 5));
            // END   2019/03/22 T.Ogura [QC#30565,MOD]
        }else{
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_01, rs.getString(COL_SCD_PROD_CTRL_CD));
        }
        if (NLXC014001.nullToEmpty(rs.getString(COL_THIRD_PROD_CTRL_CD)).length() > 5) {
            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_02, rs.getString(COL_THIRD_PROD_CTRL_CD).substring(1, 5));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_02, rs.getString(COL_THIRD_PROD_CTRL_CD).substring(0, 5));
            // END   2019/03/22 T.Ogura [QC#30565,MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_02, rs.getString(COL_THIRD_PROD_CTRL_CD));
        }
        if (NLXC014001.nullToEmpty(rs.getString(COL_FRTH_PROD_CTRL_CD)).length() > 5) {
            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_03, rs.getString(COL_FRTH_PROD_CTRL_CD).substring(1, 5));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_03, rs.getString(COL_FRTH_PROD_CTRL_CD).substring(0, 5));
            // END   2019/03/22 T.Ogura [QC#30565,MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_03, rs.getString(COL_FRTH_PROD_CTRL_CD));
        }
        if (NLXC014001.nullToEmpty(rs.getString(COL_FIFTH_PROD_CTRL_CD)).length() > 5) {
            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_04, rs.getString(COL_FIFTH_PROD_CTRL_CD).substring(1, 5));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_04, rs.getString(COL_FIFTH_PROD_CTRL_CD).substring(0, 5));
            // END   2019/03/22 T.Ogura [QC#30565,MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_04, rs.getString(COL_FIFTH_PROD_CTRL_CD));
        }
        wmsInbdItemWrkT.wmsClsCd_05.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsClsCd_06, rs.getString(COL_COA_PROD_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsCntyOrgCd, rs.getString(COL_ORIG_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.invtyCtrlCd, rs.getString(COL_INVTY_CTRL_FLG));
        wmsInbdItemWrkT.invtyCatgCountCd.clear();
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.spclHdlgCd, ZYPConstant.FLG_OFF_N);
//        if (NLXC014001.nullToEmpty(rs.getString(COL_MDSE_FML_NM)).length() > LG_CUT_MDSE_DESC_TXT_02_TO) {
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_02, NLXC014001.nullToEmpty(rs.getString(COL_MDSE_FML_NM)).substring(LG_CUT_MDSE_DESC_TXT_02_FROM, LG_CUT_MDSE_DESC_TXT_02_TO));
//        } else {
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_02, NLXC014001.nullToEmpty(rs.getString(COL_MDSE_FML_NM)));
//        }
        // START 2019/02/27 M.Naito [QC#30539, MOD]
//        if (NLXC014001.nullToEmpty(rs.getString(COL_MDSE_FML_NM)).length() > wmsMdseDescTxt02Length) {
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_02, NLXC014001.nullToEmpty(rs.getString(COL_MDSE_FML_NM)).substring(0, wmsMdseDescTxt02Length));
//        } else {
//            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_02, rs.getString(COL_MDSE_FML_NM));
//        }
        String mdseFmlNm = null;
        if (ZYPCommonFunc.hasValue(rs.getString(COL_MDSE_FML_NM))) {
            mdseFmlNm = rs.getString(COL_MDSE_FML_NM).replaceAll("\\r\\n|\\r|\\n|\\t", " ");
            mdseFmlNm = mdseFmlNm.trim();
        }
        if (NLXC014001.nullToEmpty(mdseFmlNm).length() > wmsMdseDescTxt02Length) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_02, NLXC014001.nullToEmpty(mdseFmlNm).substring(0, wmsMdseDescTxt02Length));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_02, mdseFmlNm);
        }
        // END 2019/02/27 M.Naito [QC#30539, MOD]
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsItemTpCd, rs.getString(COL_M_INTG_PROD_CATG_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mdseTpDescTxt, rs.getString(COL_INTG_PROD_CATG_NM));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsTrfNum, rs.getString(COL_TRF_CD));
        if (ZYPCommonFunc.hasValue(rs.getString(COL_HAZ_MAT_FLG))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsHazMatFlg, rs.getString(COL_HAZ_MAT_FLG));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsHazMatFlg, ZYPConstant.FLG_OFF_N);
        }
        wmsInbdItemWrkT.whIntfcTxt.clear();
        if (ZYPCommonFunc.hasValue(rs.getString(COL_M_MDSE_CD))) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.batCptrReqFlg, rs.getString(COL_BAT_NUM_CTRL_FLG));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.lotCtrlFlg, rs.getString(COL_LOT_CTRL_FLG));
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.batCptrReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.lotCtrlFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.hazMatCd, rs.getString(COL_HAZ_MAT_CD));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.hazClsNm, rs.getString(COL_HAZ_CLS_NM));
        ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.hazIdNum, rs.getString(COL_SFTY_HAZ_ID_NUM));

        MDSETMsg mdseMsg = getMdse(glblCmpyCd, wmsInbdItemWrkT.wmsItemCd.getValue());
        if (mdseMsg != null) {
            if (mdseMsg.shpgSerTakeFlg.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && mdseMsg.rcvSerTakeFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.serNumReqTpCd, "2");//TODO
            } else if (mdseMsg.shpgSerTakeFlg.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && mdseMsg.rcvSerTakeFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.serNumReqTpCd, "1");//TODO
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.serNumReqTpCd, "0");//TODO
            }

            SW_LIC_CTRL_TPTMsg swLicCtrlTpMsg = getSwLicCtrlTp(glblCmpyCd, mdseMsg.swLicCtrlTpCd.getValue());
            if (swLicCtrlTpMsg != null) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.swLicCtrlTpCd, swLicCtrlTpMsg.swLicReqFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.swLicCtrlTpCd, ZYPConstant.FLG_OFF_N);
            }

            CYCLE_CNT_CATGTMsg cycleCntCatgTMsg = getCycleCntCatg(glblCmpyCd, mdseMsg.cycleCntCatgCd.getValue());
            if (cycleCntCatgTMsg != null) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.cycleCntFreqNum, cycleCntCatgTMsg.cycleCntFreqNum);
            }

            if (!ZYPCommonFunc.hasValue(wmsInbdItemWrkT.cycleCntFreqNum)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.cycleCntFreqNum, BigDecimal.ZERO);
            }

            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.rtrnReqPrtFlg, mdseMsg.rtrnReqPrtFlg);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mdseItemTpCd, mdseMsg.mdseItemTpCd);
            if (ZYPCommonFunc.hasValue(mdseMsg.mdseItemTpCd)) {
                MDSE_ITEM_TPTMsg mdseItemTpTMsg = getMdseItemTp(glblCmpyCd, mdseMsg.mdseItemTpCd.getValue());
                if (mdseItemTpTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mdseItemTpNm, mdseItemTpTMsg.mdseItemTpNm);
                    ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.lbFmtTpTxt, mdseItemTpTMsg.lbFmtTpTxt);
                }
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mnfItemCd, mdseMsg.mnfItemCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mtrMachFlg, mdseMsg.mtrMachFlg);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mdseItemMnfTxt, mdseMsg.mdseItemMnfCd);
            if (ZYPCommonFunc.hasValue(mdseMsg.mdseDescShortTxt)) {
                if (NLXC014001.nullToEmpty(mdseMsg.mdseDescShortTxt.getValue()).length() > wmsMdseDescTxt01Length) {
                    ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_01, NLXC014001.nullToEmpty(mdseMsg.mdseDescShortTxt.getValue()).substring(0, wmsMdseDescTxt01Length));
                } else {
                    ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.wmsMdseDescTxt_01, mdseMsg.mdseDescShortTxt);
                }
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.backOrdImpctTpCd, mdseMsg.backOrdImpctTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.serNumReqTpCd, "0");// TODO
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.swLicCtrlTpCd, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.rtrnReqPrtFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.mtrMachFlg, ZYPConstant.FLG_OFF_N);
        }

        HAZ_MATTMsg hazMatMsg = getHazMat(glblCmpyCd, wmsInbdItemWrkT.hazMatCd.getValue());
        if (hazMatMsg != null) {
            ZYPEZDItemValueSetter.setValue(wmsInbdItemWrkT.hazPrpShipNm, hazMatMsg.hazPrpShipNm);
        }
        return wmsInbdItemWrkT;
    }

    /**
     * Create WMS_INBD_ITEM_UPC_WRK Data. Parameter rs must has
     * specific column data below.
     * <P>
     * MSI_MDSE_CD, CRTN_PER_LAYER_QTY, CRTN_TIER_PER_PLT_QTY, UPC_CD.
     * <P>
     * This method doesn't set WMS_SQ_NUM, WH_CD,
     * WMS_INBD_ITEM_UPC_WRK_PK.
     * @param wmsInbdItemUpcWrkList WMS_INBD_ITEM_UPC_WRK Data List
     * @param rs ResultSet
     * @param mdseStorePkgTList MDSE_STORE_PKGTMsg List
     * @param intfcRecActCd INTFC_REC_ACT_CD
     * @throws SQLException SQLException
     */
    private void createWmsInbdItemUpcWrk(List<WMS_INBD_ITEM_UPC_WRKTMsg> wmsInbdItemUpcWrkList, ResultSet rs, List<MDSE_STORE_PKGTMsg> mdseStorePkgTList, String intfcRecActCd) throws SQLException {

        BigDecimal csePctPltNum = null;
        BigDecimal tierPctPltNum = null;

        boolean hasPkgUomCdEa = false;

        for (MDSE_STORE_PKGTMsg mdseStorePkgT : mdseStorePkgTList) {
            WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT = new WMS_INBD_ITEM_UPC_WRKTMsg();

            // Set values.
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.procStsCd, PROC_STS.IN_COMPLETED);
            wmsInbdItemUpcWrkT.errMsgCd.clear();
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsUomCd, mdseStorePkgT.pkgUomCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.intfcTpId, VAL_INTFC_TP_ID_03);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.intfcRecTpId, VAL_INTFC_REC_TP_ID_2);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.intfcRecActCd, intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsCmpyCd, VAL_WMS_CMPY_CD_01);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemCd, ZYPCommonFunc.trimHead(NLXC014001.nullToEmpty(mdseStorePkgT.mdseCd.getValue())));
            if (VAL_PKG_UOM_CD_EA.equals(mdseStorePkgT.pkgUomCd.getValue())) {
                hasPkgUomCdEa = true;
                ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsUpcCd, rs.getString(COL_UPC_CD));
            } else if (ZYPCommonFunc.hasValue(mdseStorePkgT.ovrdUpcOrScc14Num)) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsUpcCd, mdseStorePkgT.ovrdUpcOrScc14Num);
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsUpcCd, mdseStorePkgT.scc14Num);
            }
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsBaseUomQty, mdseStorePkgT.inEachQty);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemWt, NLXC014001.nullToZero(mdseStorePkgT.inPoundWt.getValue()));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemLgNum, NLXC014001.nullToZero(mdseStorePkgT.inInchLg.getValue()));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemWdtNum, NLXC014001.nullToZero(mdseStorePkgT.inInchWdt.getValue()));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemHgtNum, NLXC014001.nullToZero(mdseStorePkgT.inInchHgt.getValue()));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemVolNum, NLXC014001.nullToZero(mdseStorePkgT.inInchVol.getValue()).divide(IN_INCH_VOL_DEVIDE_NUM, IN_INCH_VOL_NUM_OF_DECL_PLACE, BigDecimal.ROUND_HALF_UP));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.csePctTierNum, csePctPltNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.tierPctPltNum, tierPctPltNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsScc14Cd, wmsInbdItemUpcWrkT.wmsUpcCd);
            // Add to list.
            wmsInbdItemUpcWrkList.add(wmsInbdItemUpcWrkT);
        }
        // If PKG_UOM_CD = 'EA' does not exists, create default data.
        if (!hasPkgUomCdEa) {
            WMS_INBD_ITEM_UPC_WRKTMsg wmsInbdItemUpcWrkT = new WMS_INBD_ITEM_UPC_WRKTMsg();

            // Set values.
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.procStsCd, PROC_STS.IN_COMPLETED);
            wmsInbdItemUpcWrkT.errMsgCd.clear();
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsUomCd, VAL_PKG_UOM_CD_EA);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.intfcTpId, VAL_INTFC_TP_ID_03);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.intfcRecTpId, VAL_INTFC_REC_TP_ID_2);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.intfcRecActCd, intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsCmpyCd, VAL_WMS_CMPY_CD_01);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemCd, ZYPCommonFunc.trimHead(NLXC014001.nullToEmpty(rs.getString(COL_M_MDSE_CD))));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsUpcCd, rs.getString(COL_UPC_CD));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsBaseUomQty, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemWt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemLgNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemWdtNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemHgtNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.wmsItemVolNum, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.csePctTierNum, csePctPltNum);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemUpcWrkT.tierPctPltNum, tierPctPltNum);
            wmsInbdItemUpcWrkT.wmsScc14Cd.clear();
            // Add to list.
            wmsInbdItemUpcWrkList.add(wmsInbdItemUpcWrkT);
        }
    }

    /**
     * Create WMS_INBD_ITEM_SER_WRK Data. This method doesn't set
     * WMS_SQ_NUM, WH_CD, WMS_INBD_ITEM_SER_WRK_PK.
     * @param wmsInbdItemSerWrkList WMS_INBD_SER_WRK Data List
     * @param mdseSerNumRngList MDSE_SER_NUM_RNG List
     * @param intfcRecActCd INTFC_REC_ACT_CD
     */
    private void createWmsInbdItemSerWrk(List<WMS_INBD_ITEM_SER_WRKTMsg> wmsInbdItemSerWrkList, List<NLGC0020bean01> mdseSerNumRngList, String intfcRecActCd) {

        int count = 1;

        for (NLGC0020bean01 mdseSerNumRng : mdseSerNumRngList) {
            WMS_INBD_ITEM_SER_WRKTMsg wmsInbdItemSerWrkT = new WMS_INBD_ITEM_SER_WRKTMsg();

            // Set values
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.procStsCd, PROC_STS.IN_COMPLETED);
            wmsInbdItemSerWrkT.errMsgCd.clear();
            DecimalFormat df = new DecimalFormat(FMT_000);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.serLineNum, df.format(count++));
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.intfcTpId, VAL_INTFC_TP_ID_03);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.intfcRecTpId, VAL_INTFC_REC_TP_ID_3);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.intfcRecActCd, intfcRecActCd);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.wmsCmpyCd, VAL_WMS_CMPY_CD_01);
            ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.wmsItemCd, ZYPCommonFunc.trimHead(NLXC014001.nullToEmpty(mdseSerNumRng.getMdseCd())));
            if (NLXC014001.nullToEmpty(mdseSerNumRng.getFromSerNum()).length() > LG_CUT_FROM_SER_NUM_TO) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.fromSerNum, NLXC014001.nullToEmpty(mdseSerNumRng.getFromSerNum()).substring(0, LG_CUT_FROM_SER_NUM_TO));
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.fromSerNum, NLXC014001.nullToEmpty(mdseSerNumRng.getFromSerNum()));
            }
            if (NLXC014001.nullToEmpty(mdseSerNumRng.getThruSerNum()).length() > LG_CUT_TO_SER_NUM_TO) {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.toSerNum, NLXC014001.nullToEmpty(mdseSerNumRng.getThruSerNum()).substring(0, LG_CUT_TO_SER_NUM_TO));
            } else {
                ZYPEZDItemValueSetter.setValue(wmsInbdItemSerWrkT.toSerNum, NLXC014001.nullToEmpty(mdseSerNumRng.getThruSerNum()));
            }
            // Add to list.
            wmsInbdItemSerWrkList.add(wmsInbdItemSerWrkT);
        }
    }

    /**
     * Get MdseStorePkg Data.
     * @param mdseCd merchandiseCode
     * @return MDSE_STORE_PKGTMsg List
     */
    private List<MDSE_STORE_PKGTMsg> getMdseStorePkgList(String mdseCd) {

        // SQL bind parameter
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("pkgUomStdFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("qtyPkgApvlStsCdSa", VAL_QTY_PKG_APVL_STS_CD_SA);
        queryParam.put("qtyPkgApvlStsCdSu", VAL_QTY_PKG_APVL_STS_CD_SU);
        queryParam.put("qtyPkgApvlStsCdFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("declPlace", IN_INCH_NUM_OF_DECL_PLACE);

        List<MDSE_STORE_PKGTMsg> mdseStorePkgTList = (ArrayList<MDSE_STORE_PKGTMsg>) ssmBatchClient.queryObjectList("getMdseStorePkg", queryParam);
        return mdseStorePkgTList;
    }

    /**
     * Get MDSE_SER_NUM_RNG Data.
     * @param mdseCd merchandiseCode
     * @return MDSE_SER_NUM_RNGTMsg List
     * @throws SQLException
     */
    private List<NLGC0020bean01> getMdseSerNumRngList(String mdseCd) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<NLGC0020bean01> mdseSerNumRngList = new ArrayList<NLGC0020bean01>();
        try {
            // SQL bind parameter
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("mdseCd", mdseCd);
            queryParam.put("fmtLgSerNumFm09", FMT_LG_SER_NUM_FM09);

            stmt = ssmLLClient.createPreparedStatement("getMdseSerNumRng", queryParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                NLGC0020bean01 mdseSerNumRng = new NLGC0020bean01();
                mdseSerNumRng.setFmtLgSerNum(rs.getString(COL_FMT_LG_SER_NUM));
                mdseSerNumRng.setLgSerNum(rs.getString(COL_LG_SER_NUM));
                mdseSerNumRng.setNumRng(rs.getString(COL_NUM_RNG));
                mdseSerNumRng.setMinLgSerNum(rs.getString(COL_MIN_LEN));
                mdseSerNumRng.setMaxLgSerNum(rs.getString(COL_MAX_LEN));
                mdseSerNumRng.setSerNumDefFlg(rs.getString(COL_SER_NUM_DEF_FLG));
                mdseSerNumRng.setFromSerNum(rs.getString(COL_FROM_SER_NUM));
                mdseSerNumRng.setThruSerNum(rs.getString(COL_THRU_SER_NUM));
                mdseSerNumRng.setMdseSerNumRngPk(rs.getBigDecimal(COL_MDSE_SER_NUM_RNG_PK));
                mdseSerNumRng.setMdseCd(rs.getString(COL_MDSE_CD));
                mdseSerNumRng.setFmtMinLgSerNum(rs.getString(COL_FMT_MIN_LEN));
                mdseSerNumRng.setFmtMaxLgSerNum(rs.getString(COL_FMT_MAX_LEN));
                mdseSerNumRngList.add(mdseSerNumRng);
            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return mdseSerNumRngList;
    }

    /**
     * Get SER_TP.
     * @param mdseSerNumRngList NLGC0020bean01 List
     * @return SER_TP string
     */
    private static String getSerTp(List<NLGC0020bean01> mdseSerNumRngList) {

        if (mdseSerNumRngList == null) {
            return "";
        }

        String serTp = "";
        // Set Serial Type.
        if (mdseSerNumRngList.size() > LG_SER_TP_JUDGE) {
            NLGC0020bean01 mdseSerNumRng = (NLGC0020bean01) mdseSerNumRngList.get(0);
            serTp += VAL_SER_TP_S + mdseSerNumRng.getFmtMinLgSerNum() + VAL_HYPHEN + mdseSerNumRng.getFmtMaxLgSerNum();
        } else {
            serTp = VAL_SER_TP_S;
            for (NLGC0020bean01 mdseSerNumRng : mdseSerNumRngList) {
                serTp += mdseSerNumRng.getFmtLgSerNum();
            }
        }
        return serTp;
    }

    /**
     * Get SER_LG_NUM.
     * @param mdseSerNumRngList NLGC0020bean01 List
     * @return SER_LG_NUM string
     */
    private static String getSerLgNum(List<NLGC0020bean01> mdseSerNumRngList) {

        if (mdseSerNumRngList == null) {
            return VAL_SER_LEN_00;
        }

        String serLgNum = VAL_SER_LEN_00;
        // Set Serial Length.
        for (NLGC0020bean01 mdseSerNumRng : mdseSerNumRngList) {
            if (ZYPConstant.FLG_ON_Y.equals(mdseSerNumRng.getSerNumDefFlg())) {
                serLgNum = mdseSerNumRng.getFmtLgSerNum();
                break;
            }
        }
        return serLgNum;
    }

    /**
     * Judge whether WMS_MDSE_LIST exists or not.
     * @param glblCmpyCd GLBL_CMPY_CD
     * @param whCd WH_CD
     * @param mdseCd WMS_MDSE_CD
     * @return true / exists, false / not exists
     */
    public static boolean isExistsWmsMdseList(String glblCmpyCd, String whCd, String mdseCd) {

        // Get WMS_MDSE_LIST.
        WMS_MDSE_LISTTMsg wmsMdseListT = new WMS_MDSE_LISTTMsg();
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsMdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.whCd, whCd);
        wmsMdseListT = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKey(wmsMdseListT);

        if (wmsMdseListT == null) {
            return false;
        }
        return true;
    }

    /**
     * Update WMS_MDSE_LIST Table.
     * @param glblCmpyCd Global Company Code
     * @param whCd WarehouseCode
     * @param mdseCd MerchandiseCode
     * @param wmsItemDnldStsCd WMS_ITEM_DNLD_STS_CD
     * @return null / has no error, not null / error message code
     */
    public static String updateWmsMdseList(String glblCmpyCd, String whCd, String mdseCd, String wmsItemDnldStsCd) {

        String ezuptime = null;
        return updateWmsMdseList(glblCmpyCd, whCd, mdseCd, wmsItemDnldStsCd, ezuptime);
    }

    /**
     * Update WMS_MDSE_LIST Table.
     * @param glblCmpyCd Global Company Code
     * @param whCd WarehouseCode
     * @param mdseCd MerchandiseCode
     * @param wmsItemDnldStsCd WMS_ITEM_DNLD_STS_CD
     * @param ezuptime Ezuptime
     * @return null / has no error, not null / error message code
     */
    public static String updateWmsMdseList(String glblCmpyCd, String whCd, String mdseCd, String wmsItemDnldStsCd, String ezuptime) {

        // Get WMS_MDSE_LIST.
        WMS_MDSE_LISTTMsg wmsMdseListT = new WMS_MDSE_LISTTMsg();
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsMdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.whCd, whCd);
        wmsMdseListT = (WMS_MDSE_LISTTMsg) EZDTBLAccessor.findByKeyForUpdate(wmsMdseListT);

        if (wmsMdseListT == null) {
            return NLGM0044E;
        }
        if (ZYPCommonFunc.hasValue(ezuptime)) {
            ZYPEZDItemValueSetter.setValue(wmsMdseListT.lastIntfcMdseUpdTs, ezuptime); // Set_latest_ezuptime.
        }
        ZYPEZDItemValueSetter.setValue(wmsMdseListT.wmsItemDnldStsCd, wmsItemDnldStsCd);

        EZDTBLAccessor.update(wmsMdseListT);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(wmsMdseListT.getReturnCode())) {
            return NLGM0046E;
        }
        return null;
    }

    /**
     * getMdse
     * @param String glblCmpyCd, String mdseCd
     * @return MDSETMsg
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        MDSETMsg outMsg = (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getHazMat
     * @param String glblCmpyCd, String hazMatCd
     * @return HAZ_MATTMsg
     */
    private HAZ_MATTMsg getHazMat(String glblCmpyCd, String hazMatCd) {

        HAZ_MATTMsg inMsg = new HAZ_MATTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.hazMatCd, hazMatCd);
        HAZ_MATTMsg outMsg = (HAZ_MATTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }


    /**
     * getSwLicCtrlTp
     * @param String glblCmpyCd, String swLicCtrlTpCd
     * @return MDSETMsg
     */
    private SW_LIC_CTRL_TPTMsg getSwLicCtrlTp(String glblCmpyCd, String swLicCtrlTpCd) {

    	SW_LIC_CTRL_TPTMsg inMsg = new SW_LIC_CTRL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.swLicCtrlTpCd, swLicCtrlTpCd);
        SW_LIC_CTRL_TPTMsg outMsg = (SW_LIC_CTRL_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getCycleCntCatg
     * @param String glblCmpyCd, String cycleCntCatgCd
     * @return CYCLE_CNT_CATGTMsg
     */
    private CYCLE_CNT_CATGTMsg getCycleCntCatg(String glblCmpyCd, String cycleCntCatgCd) {

    	CYCLE_CNT_CATGTMsg inMsg = new CYCLE_CNT_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cycleCntCatgCd, cycleCntCatgCd);
        CYCLE_CNT_CATGTMsg outMsg = (CYCLE_CNT_CATGTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getMdseItemTp
     * @param String glblCmpyCd, String mdseItemTpCd
     * @return MDSE_ITEM_TPTMsg
     */
    private MDSE_ITEM_TPTMsg getMdseItemTp(String glblCmpyCd, String mdseItemTpCd) {

    	MDSE_ITEM_TPTMsg inMsg = new MDSE_ITEM_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseItemTpCd, mdseItemTpCd);
        MDSE_ITEM_TPTMsg outMsg = (MDSE_ITEM_TPTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * getDecimalValue
     * @param tMsg WMS_INBD_ITEM_WRKTMsg
     * @param attrName String
     * @param value BigDecimal
     * @return BigDecimal
     */
    private BigDecimal getDecimalValue(WMS_INBD_ITEM_WRKTMsg tMsg, String attrName, BigDecimal value) {

        int decimalDigit= 0;
        int decimalScale= 0;

        // attributes max length 
        EZDItemAttribute decimalAttr = tMsg.getAttr(attrName);
        if (decimalAttr != null) {
            decimalScale = decimalAttr.getFracDigit();
            decimalDigit = decimalAttr.getDigit() - decimalScale;
        }

        BigDecimal newValueDecimal = value;
        if (ZYPCommonFunc.hasValue(newValueDecimal) && (!checkNumericLength(newValueDecimal, decimalDigit))) {
            String newValueStr = ZYPCommonFunc.leftPad("", decimalDigit, VAL_PADING);
            if (decimalScale > 0) {
                newValueStr = ZYPCommonFunc.concatString(newValueStr, VAL_PADING_SCALE, "");
            }
            newValueDecimal = new BigDecimal(newValueStr);
        }
        return newValueDecimal;
    }

    /**
     * checkNumericLength
     * @param checkValue BigDecimal
     * @param length int
     * @return true / success, false / error
     */
    private boolean checkNumericLength(BigDecimal checkValue, int length) {

        if (checkValue == null) {
            return true;
        }
        String[] args = checkValue.toPlainString().split(VAL_ESC_PERIOD);
        if (args != null && args.length > 0) {
            if (args[0].length() > length) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }

}
