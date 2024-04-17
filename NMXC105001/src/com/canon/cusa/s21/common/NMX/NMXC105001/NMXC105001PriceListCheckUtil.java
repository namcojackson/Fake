/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC105001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.MDSETMsg;
import business.db.MKT_MDL_SEGTMsg;
import business.db.MKT_MDL_SEGTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_ADDL_CHRG_TPTMsg;
import business.db.PRC_ADDL_CHRG_TPTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CATGTMsgArray;
import business.db.PRC_EQUIP_TPTMsg;
import business.db.PRC_EQUIP_TPTMsgArray;
import business.db.PRC_FMLATMsg;
import business.db.PRC_FMLATMsgArray;
import business.db.PRC_IN_OUT_RGTMsg;
import business.db.PRC_IN_OUT_RGTMsgArray;
import business.db.PRC_LIST_BANDTMsg;
import business.db.PRC_LIST_BANDTMsgArray;
import business.db.PRC_LIST_TPTMsg;
import business.db.PRC_LIST_TPTMsgArray;
import business.db.PRC_PGM_TPTMsg;
import business.db.PRC_PGM_TPTMsgArray;
import business.db.PRC_QLFY_TPTMsg;
import business.db.PRC_QLFY_TPTMsgArray;
import business.db.PRC_RATE_TPTMsg;
import business.db.PRC_RATE_TPTMsgArray;
import business.db.PRC_SVC_CONTR_TPTMsg;
import business.db.PRC_SVC_CONTR_TPTMsgArray;
import business.db.PRC_SVC_PLN_TPTMsg;
import business.db.PRC_SVC_PLN_TPTMsgArray;
import business.db.PRC_SVC_TIER_TPTMsg;
import business.db.PRC_SVC_TIER_TPTMsgArray;
import business.db.PRC_SVC_ZONETMsg;
import business.db.PRC_SVC_ZONETMsgArray;
import business.db.PRC_TERM_UOMTMsg;
import business.db.PRC_TERM_UOMTMsgArray;
import business.db.PROD_CTRLTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SPLY_AGMT_PLNTMsg;
import business.db.SPLY_AGMT_PLNTMsgArray;
import business.db.SVC_SEGTMsg;
import business.db.SVC_SEGTMsgArray;
import business.parts.NWZC170001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC170001.NWZC170001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMXC105001PriceListCheckUtil
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Fujitsu         W.Honda         Create          N/A
 * 2017/08/28   Fujitsu         H.Sugawara      Update          QC#19873
 * 2018/04/03   Fujitsu         R.Nakamura      Update          QC#25210
 * 2018/04/10   Fujitsu         R.Nakamura      Update          QC#25211-2
 * 2018/06/06   Fujitsu         H.Kumagai       Update          QC#26292
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/07/20   Fujitsu         T.Noguchi       Update          QC#27331
 * 2018/08/07   Fujitsu         W.Honda         Update          QC#27488-1
 * 2018/08/16   Fujitsu         K.Ishizuka      Update          QC#27716
 * 2018/09/04   Fujitsu         T.Noguchi       Update          QC#28019
 * 2018/11/26   Fujitsu         H.Kumagai       Update          QC#29300
 * 2023/08/22   Hitachi         H.Watanabe      Update          QC#61385
 * 
 *</pre>
 */
public class NMXC105001PriceListCheckUtil extends S21SsmEZDQuerySupport {
    /** SSM Instance. */
    private static final NMXC105001PriceListCheckUtil MY_INSTANCE = new NMXC105001PriceListCheckUtil();

    /** Message Map */
    private static final Map<String, Object> messageMap = new HashMap<String, Object>();

    /** String Date High Value */
    public static final String HIGH_VAL_DT = "99991231";

    /** The entered [@] does not exist in [@]. */
    public static final String NMAM0163E = "NMAM0163E";

    /** Please entry 8 digits merchandise code. */
    public static final String NMAM8216E = "NMAM8216E";

    /** Please enter Price or Formula. */
    public static final String NMAM8217E = "NMAM8217E";

    /**
     * In case of Qualify Type = Item Code, Formula Type should be 01
     * : Price List or 03 : Standard Cost.
     */
    public static final String NMAM8218E = "NMAM8218E";

    /**
     * In case of Qualify Type = Merchandise Type or Product Hierarchy
     * 1-5, Formula Type should be 01 : Price List.
     */
    public static final String NMAM8219E = "NMAM8219E";

    /**
     * In case of Qualify Type = Item Code, Linked equipment price
     * list has price amount on same Item Code.
     */
    public static final String NMAM8220E = "NMAM8220E";

    // Mod Start 2018/04/10 QC#25211-2
//    /** Cannot calculate. Please confirm formula setup. */
//    public static final String NMAM8224E = "NMAM8224E";
    /** The entered UOM [@] is not recognized as a valid UOM. Please check your entry. */
    public static final String NMAM8680E = "NMAM8680E";
    // Mod End 2018/04/10 QC#25211-2

    /**
     * Term, Term UOM and Monthly Payment Amount must be entered
     * together, or all of them must be blank.
     */
    public static final String NMAM8364E = "NMAM8364E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Existence check error.[@] */
    public static final String NMAM8479E = "NMAM8479E";

    /** Validation check error.[@] */
    public static final String NMAM8480E = "NMAM8480E";

    /** Duplicate check error.[@] */
    public static final String NMAM8481E = "NMAM8481E";

    /** Price List Type does not accord with existing Price List. */
    public static final String NMAM8482E = "NMAM8482E";

    // Add Start 2018/06/06 QC#26292
    /** This csv data [@] doesn't match with the file format. */
    public static final String ZYEM0006E = "ZYEM0006E";
    // Add End 2018/06/06 QC#26292

    // Add Start 2018/07/17 QC#20267
    /** Merchandise Code cannot be uniquely identified. */
    public static final String NMAM8685E = "NMAM8685E";
    // Add End 2018/07/17 QC#20267

    // 2018/07/20 QC#27331 Add Start
    /** [@] field has too many decimal digits entered. */
    public static final String ZZM9015E = "ZZM9015E";
    // 2018/07/20 QC#27331 Add End

    // 2018/09/04 QC#28019 Add Start
    /** MDSE Code Length*/
    public static final int MDSE_CD_LENGTH = 16;
    // 2018/09/04 QC#28019 Add End

    // 2023/08/22 QC#61385 Add Start
    /** There are gaps in the effective periods. */
    public static final String NMAM8724E = "NMAM8724E";
    // 2023/08/22 QC#61385 Add End

    /**
     * getInstance.
     * @return NMXC105001PriceListCheckUtil
     */
    public static NMXC105001PriceListCheckUtil getInstance() {
        return MY_INSTANCE;
    }

    /**
     * checkPrcListEquip.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param slsDt String
     * @param messenger ZYPCSVUploadMessenger
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListEquip(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, String slsDt, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_QLFY_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Qualifer Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_QLFY_VAL_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Qualifer Value");
            isSuccess = false;
        }

        // 2018/11/26 Mod Start QC#29300
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PKG_UOM_DESC_TXT"))) {
//            messenger.addMessageForRecord(rowNum, ZZM9000E, "UOM");
//            isSuccess = false;
            String mdseCd = rsSet.getString("PRC_QLFY_VAL_TXT");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", glblCmpyCd);
            params.put("mdseCd", mdseCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTableList(params, "getBasePkgUOM");
            if (!ssmResult.isCodeNotFound()) {
                List<Map<String, Object>> uomCdList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (uomCdList.size() > 0){
                    bean.setPkgUomDescTxt((String)uomCdList.get(0).get("BASE_PKG_UOM_CD"));
                }
            }
        }
        // 2018/11/26 Mod End QC#29300

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // Add Start 2018/06/06 QC#26292
        if (!ZYPCommonFunc.hasValue(rsSet.getString("OPEN_MKT_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Open Market");
            isSuccess = false;
        } else if(!rsSet.getString("OPEN_MKT_DESC_TXT").equals(ZYPConstant.FLG_ON_Y) && !rsSet.getString("OPEN_MKT_DESC_TXT").equals(ZYPConstant.FLG_OFF_N)){
            //Format
            messenger.addMessageForRecord(rowNum, ZYEM0006E, "Open Market");
            isSuccess = false;
        }
        // Add End 2018/06/06 QC#26292

        // Code Table Check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_QLFY_TP_DESC_TXT"))) {
            PRC_QLFY_TPTMsg pqtMsg = new PRC_QLFY_TPTMsg();
            PRC_QLFY_TPTMsgArray pqtMsgArray = new PRC_QLFY_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pqtMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pqtMsg.prcQlfyTpDescTxt, rsSet.getString("PRC_QLFY_TP_DESC_TXT"));
            pqtMsgArray = (PRC_QLFY_TPTMsgArray) S21CodeTableAccessor.findByCondition(pqtMsg);

            if (pqtMsgArray.length() > 0) {
                bean.setPrcQlfyTpCd(pqtMsgArray.no(0).prcQlfyTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_QLFY_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        // 2018/09/04 QC#28019 Del Start
        //if (ZYPCommonFunc.hasValue(rsSet.getString("PKG_UOM_DESC_TXT"))) {
        //    // 2018/08/16 S21_NA#27716 Mod Start
        //    // PKG_UOMTMsg puMsg = new PKG_UOMTMsg();
        //    // PKG_UOMTMsgArray puMsgArray = new PKG_UOMTMsgArray();
        //    // ZYPEZDItemValueSetter.setValue(puMsg.glblCmpyCd, glblCmpyCd);
        //    // ZYPEZDItemValueSetter.setValue(puMsg.pkgUomDescTxt, rsSet.getString("PKG_UOM_DESC_TXT"));
        //    // puMsgArray = (PKG_UOMTMsgArray) S21CodeTableAccessor.findByCondition(puMsg);
        //
        //    // if (puMsgArray.length() > 0) {
        //    //     bean.setPkgUomCd(puMsgArray.no(0).pkgUomCd.getValue());
        //    // } else {
        //    //     messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PKG_UOM_DESC_TXT"));
        //    //     isSuccess = false;
        //    //}
        //    String pkgUomCd = getPkgUomCd(rsSet.getString("PKG_UOM_DESC_TXT"));
        //    if (ZYPCommonFunc.hasValue(pkgUomCd)) {
        //        bean.setPkgUomCd(pkgUomCd);
        //    } else {
        //        messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PKG_UOM_DESC_TXT"));
        //        isSuccess = false;
        //    }
        //    // 2018/08/16 S21_NA#27716 Mod End
        //}
        // 2018/09/04 QC#28019 Del End

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_TERM_UOM_DESC_TXT"))) {
            PRC_TERM_UOMTMsg ptuMsg = new PRC_TERM_UOMTMsg();
            PRC_TERM_UOMTMsgArray ptuMsgArray = new PRC_TERM_UOMTMsgArray();
            ZYPEZDItemValueSetter.setValue(ptuMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ptuMsg.prcTermUomDescTxt, rsSet.getString("PRC_TERM_UOM_DESC_TXT"));
            ptuMsgArray = (PRC_TERM_UOMTMsgArray) S21CodeTableAccessor.findByCondition(ptuMsg);

            if (ptuMsgArray.length() > 0) {
                bean.setPrcTermUomCd(ptuMsgArray.no(0).prcTermUomCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_TERM_UOM_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_FMLA_NM"))) {
            PRC_FMLATMsg pdTMsg = new PRC_FMLATMsg();
            pdTMsg.setSQLID("002");
            pdTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pdTMsg.setConditionValue("prcFmlaNm01", rsSet.getString("PRC_FMLA_NM"));
            PRC_FMLATMsgArray pdTMsgArray = (PRC_FMLATMsgArray) EZDTBLAccessor.findByCondition(pdTMsg);
            if (pdTMsgArray.length() > 0) {
                bean.setPrcFmlaPk(pdTMsgArray.no(0).prcFmlaPk.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_FMLA_NM"));
                isSuccess = false;
            }
        }

        // Business
        if (ZYPCommonFunc.hasValue(bean.getPrcQlfyTpCd()) && ZYPCommonFunc.hasValue(rsSet.getString("PRC_QLFY_VAL_TXT"))) {
            String rtrnVal = "";
            // Mod Start 2018/07/17 QC#20267
            if (!checkPrcQlfyVal(bean.getPrcQlfyTpCd(), rsSet.getString("PRC_QLFY_VAL_TXT"), glblCmpyCd, rtrnVal, ONBATCH_TYPE.BATCH, bean)) {
            // Mod End 2018/07/17 QC#20267
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // 2018/09/04 QC#28019 Add Start
        String pkgUomCdConv = null;
        // 2018/11/26 Mod Start QC#29300
        if (ZYPCommonFunc.hasValue(rsSet.getString("PKG_UOM_DESC_TXT")) || ZYPCommonFunc.hasValue(bean.getPkgUomDescTxt())) {
//            String pkgUomCd = getPkgUomCd(rsSet.getString("PKG_UOM_DESC_TXT"), bean.getPkgUomClsCd());
            String pkgUomCd = null;
            if (ZYPCommonFunc.hasValue(rsSet.getString("PKG_UOM_DESC_TXT"))) {
                pkgUomCd = getPkgUomCd(rsSet.getString("PKG_UOM_DESC_TXT"), bean.getPkgUomClsCd());
            } else if (ZYPCommonFunc.hasValue(bean.getPkgUomDescTxt())) {
                pkgUomCd = getPkgUomCd(bean.getPkgUomDescTxt(), bean.getPkgUomClsCd());
            }
         // 2018/11/26 Mod End QC#29300
            if (ZYPCommonFunc.hasValue(pkgUomCd)) {
                bean.setPkgUomCd(pkgUomCd);
                if (PRC_QLFY_TP.ITEM_CODE.equals(bean.getPrcQlfyTpCd())) {
                    String mdseCd = rsSet.getString("PRC_QLFY_VAL_TXT");
                    if (ZYPCommonFunc.hasValue(bean.getMdseCd())) {
                        mdseCd = bean.getMdseCd();
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("glblCmpyCd", glblCmpyCd);
                    params.put("basePkgUomCd", pkgUomCd);
                    params.put("mdseCd", mdseCd);
                    S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTableList(params, "getPkgUomCdForBaseUom");
                    if (!ssmResult.isCodeNotFound()) {
                        List<Map<String, Object>> uomCdList = (List<Map<String, Object>>) ssmResult.getResultObject();
                        if (uomCdList.size() > 0){
                            pkgUomCdConv = (String)uomCdList.get(0).get("PKG_UOM_CD");
                        }
                    }
                }
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PKG_UOM_DESC_TXT"));
                isSuccess = false;
            }
        }
        // 2018/09/04 QC#28019 Add End

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("MIN_PRC_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Min Price");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MAX_PRC_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Max Price");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("PRC_TERM_AOT"), 0)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Term");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MLY_PMT_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Monthly Payment Amount");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MIN_LEASE_PMT_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Lease Payment Min");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MAX_LEASE_PMT_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Lease Payment Max");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("QUOT_REV_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Quota Rev");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        if (((ZYPCommonFunc.hasValue(bean.getPrcFmlaPk()) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT")))) //
                || (!ZYPCommonFunc.hasValue(bean.getPrcFmlaPk()) && !ZYPCommonFunc.hasValue(rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT")))) {
            messenger.addMessageForRecord(rowNum, NMAM8217E, null);
            isSuccess = false;
        } else {
            if (ZYPCommonFunc.hasValue(bean.getPrcFmlaPk())) {
                // Mod Start 2018/07/17 QC#20267
                if (ZYPCommonFunc.hasValue(bean.getMnfMdseCd())) {
                    if (!checkFormula(bean.getPrcFmlaPk(), bean.getMnfMdseCd(), bean.getPrcQlfyTpCd(), glblCmpyCd, ONBATCH_TYPE.BATCH)) {
                        setErrorMessage(messenger, rowNum);
                        isSuccess = false;
                    } else {
                        GLBL_CMPYTMsg gcTMsg = new GLBL_CMPYTMsg();
                        ZYPEZDItemValueSetter.setValue(gcTMsg.glblCmpyCd, glblCmpyCd);
                        gcTMsg = (GLBL_CMPYTMsg) S21CodeTableAccessor.findByKey(gcTMsg);
                        if (!fmlaApiCall(bean.getPrcFmlaPk(), bean.getPrcQlfyTpCd(), bean.getMnfMdseCd(), bean.getPkgUomCd(), glblCmpyCd, slsDt, gcTMsg.stdCcyCd.getValue())) {
                            isSuccess = false;
                        }
                    }
                } else {
                    if (!checkFormula(bean.getPrcFmlaPk(), rsSet.getString("PRC_QLFY_VAL_TXT"), bean.getPrcQlfyTpCd(), glblCmpyCd, ONBATCH_TYPE.BATCH)) {
                        setErrorMessage(messenger, rowNum);
                        isSuccess = false;
                    } else {
                        GLBL_CMPYTMsg gcTMsg = new GLBL_CMPYTMsg();
                        ZYPEZDItemValueSetter.setValue(gcTMsg.glblCmpyCd, glblCmpyCd);
                        gcTMsg = (GLBL_CMPYTMsg) S21CodeTableAccessor.findByKey(gcTMsg);
                        if (!fmlaApiCall(bean.getPrcFmlaPk(), bean.getPrcQlfyTpCd(), rsSet.getString("PRC_QLFY_VAL_TXT"), bean.getPkgUomCd(), glblCmpyCd, slsDt, gcTMsg.stdCcyCd.getValue())) {
                            isSuccess = false;
                        }
                    }
                }
               // Mod End 2018/07/17 QC#20267
            }

            // UOM
            // Mod Start 2018/07/17 QC#20267
            if (ZYPCommonFunc.hasValue(bean.getMnfMdseCd())) {
                if (PRC_QLFY_TP.ITEM_CODE.equals(bean.getPrcQlfyTpCd())) {
                    if (!checkScale(rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"), aftDeclPntDigitNum)) {
                        messenger.addMessageForRecord(rowNum, ZZM9015E, "Price");
                        isSuccess = false;
                    } else {
                        if (!checkPrcByUom(bean.getMnfMdseCd(), bean.getPkgUomCd(), rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"))) {
                            messenger.addMessageForRecord(rowNum, NMAM8680E, rsSet.getString("PKG_UOM_DESC_TXT"));
                            isSuccess = false;
                        }
                    }
                }
            } else {
                if (PRC_QLFY_TP.ITEM_CODE.equals(bean.getPrcQlfyTpCd())) {
                    // 2018/07/20 QC#27331 Add Start
                    if (!checkScale(rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"), aftDeclPntDigitNum)) {
                        messenger.addMessageForRecord(rowNum, ZZM9015E, "Price");
                        isSuccess = false;
                    } else {
                    // 2018/07/20 QC#27331 Add End
                        if (!checkPrcByUom(rsSet.getString("PRC_QLFY_VAL_TXT"), bean.getPkgUomCd(), rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"))) {
                            // Mod Start 2018/04/10 QC#25211-2
//                            messenger.addMessageForRecord(rowNum, NMAM8224E, null);
                            messenger.addMessageForRecord(rowNum, NMAM8680E, rsSet.getString("PKG_UOM_DESC_TXT"));
                            // Mod End 2018/04/10 QC#25211-2
                            isSuccess = false;
                        }
                    // 2018/07/20 QC#27331 Add Start
                    }
                    // 2018/07/20 QC#27331 Add End
                }
            }
            // Mod End 2018/07/17 QC#20267
        }

        // Price Amount
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_PRC_AMT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_PRC_AMT"))) {
            if (rsSet.getBigDecimal("MIN_PRC_AMT").compareTo(rsSet.getBigDecimal("MAX_PRC_AMT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Price");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Price");
                isSuccess = false;
            }
        }

        // Lease Payment Amount
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_LEASE_PMT_AMT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_LEASE_PMT_AMT"))) {
            if (rsSet.getBigDecimal("MIN_LEASE_PMT_AMT").compareTo(rsSet.getBigDecimal("MAX_LEASE_PMT_AMT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Price");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Price");
                isSuccess = false;
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }

        // Term combination check
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("PRC_TERM_AOT")) || ZYPCommonFunc.hasValue(bean.getPrcTermUomCd()) || ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MLY_PMT_AMT"))) {
            if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("PRC_TERM_AOT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8364E, null);
                isSuccess = false;
            }
            if (!ZYPCommonFunc.hasValue(bean.getPrcTermUomCd())) {
                messenger.addMessageForRecord(rowNum, NMAM8364E, null);
                isSuccess = false;
            }
            if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MLY_PMT_AMT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8364E, null);
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {

            // Add Start 2017/08/28 QC#19873
            if (bean.getPrcCatgCd() != null) {
                // 2018/09/04 QC#28019 Add Start
                if (S21StringUtil.isNotEmpty(pkgUomCdConv)) {
                    bean.setPkgUomCd(pkgUomCdConv);
                }
                // 2018/09/04 QC#28019 Add End
                Map<String, Object> paramMap = createEquipParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListEquipDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_EQUIP");
                    isSuccess = false;
                }
            }
            // Add End 2017/08/28 QC#19873

            Map<String, Object> wrkParamMap = createEquipWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListEquipWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_EQUIP_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListEquipWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }

        return isSuccess;
    }

    /**
     * checkPrcListSvc.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @throws SQLException
     * @return boolean
     */
    public static boolean checkPrcListSvc(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_RATE_TP_DESC_TXT"))) {
            PRC_RATE_TPTMsg prtMsg = new PRC_RATE_TPTMsg();
            PRC_RATE_TPTMsgArray prtMsgArray = new PRC_RATE_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(prtMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prtMsg.prcRateTpDescTxt, rsSet.getString("PRC_RATE_TP_DESC_TXT"));
            prtMsgArray = (PRC_RATE_TPTMsgArray) S21CodeTableAccessor.findByCondition(prtMsg);

            if (prtMsgArray.length() > 0) {
                bean.setPrcRateTpCd(prtMsgArray.no(0).prcRateTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_RATE_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            PRC_SVC_PLN_TPTMsg psptMsg = new PRC_SVC_PLN_TPTMsg();
            PRC_SVC_PLN_TPTMsgArray psptMsgArray = new PRC_SVC_PLN_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psptMsg.prcSvcPlnTpDescTxt, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
            psptMsgArray = (PRC_SVC_PLN_TPTMsgArray) S21CodeTableAccessor.findByCondition(psptMsg);

            if (psptMsgArray.length() > 0) {
                bean.setPrcSvcPlnTpCd(psptMsgArray.no(0).prcSvcPlnTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            PRC_SVC_CONTR_TPTMsg psctMsg = new PRC_SVC_CONTR_TPTMsg();
            PRC_SVC_CONTR_TPTMsgArray psctMsgArray = new PRC_SVC_CONTR_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcSvcContrTpDescTxt, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
            psctMsgArray = (PRC_SVC_CONTR_TPTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcSvcContrTpCd(psctMsgArray.no(0).prcSvcContrTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
            PRC_LIST_BANDTMsg psctMsg = new PRC_LIST_BANDTMsg();
            PRC_LIST_BANDTMsgArray psctMsgArray = new PRC_LIST_BANDTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcListBandDescTxt, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
            psctMsgArray = (PRC_LIST_BANDTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcListBandCd(psctMsgArray.no(0).prcListBandCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_ZONE_DESC_TXT"))) {
            PRC_SVC_ZONETMsg pszMsg = new PRC_SVC_ZONETMsg();
            PRC_SVC_ZONETMsgArray pszMsgArray = new PRC_SVC_ZONETMsgArray();
            ZYPEZDItemValueSetter.setValue(pszMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pszMsg.prcSvcZoneDescTxt, rsSet.getString("PRC_SVC_ZONE_DESC_TXT"));
            pszMsgArray = (PRC_SVC_ZONETMsgArray) S21CodeTableAccessor.findByCondition(pszMsg);

            if (pszMsgArray.length() > 0) {
                bean.setPrcSvcZoneCd(pszMsgArray.no(0).prcSvcZoneCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_ZONE_DESC_TXT"));
                isSuccess = false;
            }
        }

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_RATE_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Rate Type");
            isSuccess = false;
        }

        if (PRC_RATE_TP.CPC.equals(bean.getPrcRateTpCd()) || PRC_RATE_TP.ANNUAL.equals(bean.getPrcRateTpCd())) {
            if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Meter Package Name");
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Service Model Name");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_MDSE_CD"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Item#");
                isSuccess = false;
            }
        }

        if (PRC_RATE_TP.ANNUAL.equals(bean.getPrcRateTpCd())) {

            if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Meter Type");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MIN_COPY_VOL_CNT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Vol");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MAX_COPY_VOL_CNT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Vol");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Band");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Cost Per Copy OVERAGE");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MIN_CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min CPC");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MAX_CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max CPC");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_MDSE_CD"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Item#");
                isSuccess = false;
            }
        }

        // BaseOnly
        if (PRC_RATE_TP.BASE_ONLY.equals(bean.getPrcRateTpCd())) {
            if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_MDSE_CD"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Item#");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Service Model Name");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Meter Package Name");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Meter Type");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MIN_COPY_VOL_CNT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Vol");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MAX_COPY_VOL_CNT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Vol");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Band");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Cost Per Copy OVERAGE");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MIN_CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min CPC");
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(rsSet.getString("MAX_CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max CPC");
                isSuccess = false;
            }
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Plan Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Contract Type");
            isSuccess = false;
        }

        // Annual check
        if (PRC_RATE_TP.CPC.equals(bean.getPrcRateTpCd())) {
            if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_COPY_VOL_CNT"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Min Vol");
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_COPY_VOL_CNT"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Max Vol");
                isSuccess = false;
            }
        }

        if (PRC_RATE_TP.CPC.equals(bean.getPrcRateTpCd())) {
            if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Band");
                isSuccess = false;
            }
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("BASE_AMT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Base Amount");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_FROM_MTH_AOT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Term From (MTH)");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_THRU_MTH_AOT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Term To (MTH)");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        if (PRC_RATE_TP.CPC.equals(bean.getPrcRateTpCd())) {
            if (!ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Meter Type");
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("CPC_AMT_RATE"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Cost Per Copy (OVERAGE)");
                isSuccess = false;
            }

            if (!ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
                messenger.addMessageForRecord(rowNum, ZZM9000E, "Service Item");
                isSuccess = false;
            }
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Base Amount");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MIN_BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Min Base");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MAX_BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Max Base");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("QUOT_REV_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Quota Rev");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Meter Label exists check
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbNm = getMtrLb(rsSet.getString("MTR_LB_NM"));
            if (!ZYPCommonFunc.hasValue(mtrLbNm)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MTR_LB_NM"));
                isSuccess = false;
            }
        }

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            if (rsltTMsg == null || rsltTMsg.length() == 0) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MDL_NM"));
                isSuccess = false;
            } else {
                bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
            }
        }

        // Meter Package Name
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            BigDecimal mtrPkgPk = getBigDecimalAnyItem("PRC_MTR_PKG", rsSet.getString("PRC_MTR_PKG_NM"));
            if (!ZYPCommonFunc.hasValue(mtrPkgPk)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_MTR_PKG_NM"));
                isSuccess = false;
            }
            bean.setPrcMtrPkgPk(mtrPkgPk);
        }

        // Meter Type
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbCd = getBllgMtrLb(rsSet.getString("MTR_LB_NM"));
            if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MTR_LB_NM"));
                isSuccess = false;
            }
            bean.setMtrLbCd(mtrLbCd);
        }

        // Item#
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_MDSE_CD"))) {
            String rtrnVal = getMdseNm(glblCmpyCd, rsSet.getString("PRC_LIST_MDSE_CD"), ONBATCH_TYPE.BATCH);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // Service Item
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            String rtrnVal = getMdseNm(glblCmpyCd, rsSet.getString("MDSE_CD"), ONBATCH_TYPE.BATCH);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // Volume
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_COPY_VOL_CNT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_COPY_VOL_CNT"))) {
            if (rsSet.getBigDecimal("MIN_COPY_VOL_CNT").compareTo(rsSet.getBigDecimal("MAX_COPY_VOL_CNT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Vol");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Vol");
                isSuccess = false;
            }
        }

        // Base Amount
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_BASE_AMT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_BASE_AMT"))) {
            if (rsSet.getBigDecimal("MIN_BASE_AMT").compareTo(rsSet.getBigDecimal("MAX_BASE_AMT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Base");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Base");
                isSuccess = false;
            }
        }

        // CPC
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_CPC_AMT_RATE")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_CPC_AMT_RATE"))) {
            if (rsSet.getBigDecimal("MIN_CPC_AMT_RATE").compareTo(rsSet.getBigDecimal("MAX_CPC_AMT_RATE")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min CPC");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max CPC");
                isSuccess = false;
            }
        }

        // Term From
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_FROM_MTH_AOT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_THRU_MTH_AOT"))) {
            if (rsSet.getBigDecimal("TERM_FROM_MTH_AOT").compareTo(rsSet.getBigDecimal("TERM_THRU_MTH_AOT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Term From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Term Thru");
                isSuccess = false;
            }
        }

        // Price Meter Package Billing Meter
        if (PRC_RATE_TP.CPC.equals(bean.getPrcRateTpCd()) && ZYPCommonFunc.hasValue(bean.getPrcMtrPkgPk()) && ZYPCommonFunc.hasValue(bean.getMtrLbCd()) && ZYPCommonFunc.hasValue(bean.getMdlId())) {
            BigDecimal prcMtrPkgBllgMtPk = getPrcMtrPkgBllgMtr(bean.getPrcMtrPkgPk(), bean.getMtrLbCd(), bean.getMdlId());
            if (!ZYPCommonFunc.hasValue(prcMtrPkgBllgMtPk)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, "combined value");
                isSuccess = false;
            } else {
                bean.setPrcMtrPkgBllgMtrPk(prcMtrPkgBllgMtPk);
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createSvcParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListSvcDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_SVC");
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createSvcWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListSvcWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_SVC_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListSvcWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    /**
     * checkPrcListSvcTier.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListSvcTier(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"))) {
            PRC_SVC_TIER_TPTMsg psttMsg = new PRC_SVC_TIER_TPTMsg();
            PRC_SVC_TIER_TPTMsgArray psttMsgArray = new PRC_SVC_TIER_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psttMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psttMsg.prcSvcTierTpDescTxt, rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"));
            psttMsgArray = (PRC_SVC_TIER_TPTMsgArray) S21CodeTableAccessor.findByCondition(psttMsg);

            if (psttMsgArray.length() > 0) {
                bean.setPrcSvcTierTpCd(psttMsgArray.no(0).prcSvcTierTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            PRC_SVC_PLN_TPTMsg psptMsg = new PRC_SVC_PLN_TPTMsg();
            PRC_SVC_PLN_TPTMsgArray psptMsgArray = new PRC_SVC_PLN_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psptMsg.prcSvcPlnTpDescTxt, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
            psptMsgArray = (PRC_SVC_PLN_TPTMsgArray) S21CodeTableAccessor.findByCondition(psptMsg);

            if (psptMsgArray.length() > 0) {
                bean.setPrcSvcPlnTpCd(psptMsgArray.no(0).prcSvcPlnTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            PRC_SVC_CONTR_TPTMsg psctMsg = new PRC_SVC_CONTR_TPTMsg();
            PRC_SVC_CONTR_TPTMsgArray psctMsgArray = new PRC_SVC_CONTR_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcSvcContrTpDescTxt, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
            psctMsgArray = (PRC_SVC_CONTR_TPTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcSvcContrTpCd(psctMsgArray.no(0).prcSvcContrTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
            PRC_LIST_BANDTMsg psctMsg = new PRC_LIST_BANDTMsg();
            PRC_LIST_BANDTMsgArray psctMsgArray = new PRC_LIST_BANDTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcListBandDescTxt, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
            psctMsgArray = (PRC_LIST_BANDTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcListBandCd(psctMsgArray.no(0).prcListBandCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
                isSuccess = false;
            }
        }

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Meter Package Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_COPY_VOL_CNT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Min Vol");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_COPY_VOL_CNT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Max Vol");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Service Item");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Base Amount");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MIN_BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Min Base");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MAX_BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Max Base");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            if (rsltTMsg == null || rsltTMsg.length() == 0) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MDL_NM"));
                isSuccess = false;
            } else {
                bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
            }
        }

        // Meter Package Name
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            BigDecimal mtrPkgPk = getBigDecimalAnyItem("PRC_MTR_PKG", rsSet.getString("PRC_MTR_PKG_NM"));
            if (!ZYPCommonFunc.hasValue(mtrPkgPk)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_MTR_PKG_NM"));
                isSuccess = false;
            }
            bean.setPrcMtrPkgPk(mtrPkgPk);
        }

        // Meter Type
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbCd = getBllgMtrLb(rsSet.getString("MTR_LB_NM"));
            if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MTR_LB_NM"));
                isSuccess = false;
            }
            bean.setMtrLbCd(mtrLbCd);
        }

        // Service Item
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            String rtrnVal = getMdseNm(glblCmpyCd, rsSet.getString("MDSE_CD"), ONBATCH_TYPE.BATCH);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // Volume
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_COPY_VOL_CNT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_COPY_VOL_CNT"))) {
            if (rsSet.getBigDecimal("MIN_COPY_VOL_CNT").compareTo(rsSet.getBigDecimal("MAX_COPY_VOL_CNT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Vol");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Vol");
                isSuccess = false;
            }
        }

        // Base Amount
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_BASE_AMT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_BASE_AMT"))) {
            if (rsSet.getBigDecimal("MIN_BASE_AMT").compareTo(rsSet.getBigDecimal("MAX_BASE_AMT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Base");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Base");
                isSuccess = false;
            }
        }

        // CPC
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_CPC_AMT_RATE")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_CPC_AMT_RATE"))) {
            if (rsSet.getBigDecimal("MIN_CPC_AMT_RATE").compareTo(rsSet.getBigDecimal("MAX_CPC_AMT_RATE")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min CPC");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max CPC");
                isSuccess = false;
            }
        }

        // Term From
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_FROM_MTH_AOT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_THRU_MTH_AOT"))) {
            if (rsSet.getBigDecimal("TERM_FROM_MTH_AOT").compareTo(rsSet.getBigDecimal("TERM_THRU_MTH_AOT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Term From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Tern Thru");
                isSuccess = false;
            }
        }

        // Price Meter Package Billing Meter
        if (ZYPCommonFunc.hasValue(bean.getPrcMtrPkgPk()) && ZYPCommonFunc.hasValue(bean.getMtrLbCd()) && ZYPCommonFunc.hasValue(bean.getMdlId())) {
            BigDecimal prcMtrPkgBllgMtPk = getPrcMtrPkgBllgMtr(bean.getPrcMtrPkgPk(), bean.getMtrLbCd(), bean.getMdlId());
            if (!ZYPCommonFunc.hasValue(prcMtrPkgBllgMtPk)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, "combined value");
                isSuccess = false;
            } else {
                bean.setPrcMtrPkgBllgMtrPk(prcMtrPkgBllgMtPk);
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createSvcTierParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListSvcTierDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_SVC_TIER");
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createSvcTierWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListSvcTierWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_SVC_TIER_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListSvcTierWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    /**
     * checkPrcListSplyPgm.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @throws SQLException
     * @return boolean
     */
    public static boolean checkPrcListSplyPgm(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");
        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            PRC_SVC_CONTR_TPTMsg psctMsg = new PRC_SVC_CONTR_TPTMsg();
            PRC_SVC_CONTR_TPTMsgArray psctMsgArray = new PRC_SVC_CONTR_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcSvcContrTpDescTxt, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
            psctMsgArray = (PRC_SVC_CONTR_TPTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcSvcContrTpCd(psctMsgArray.no(0).prcSvcContrTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            PRC_SVC_PLN_TPTMsg psptMsg = new PRC_SVC_PLN_TPTMsg();
            PRC_SVC_PLN_TPTMsgArray psptMsgArray = new PRC_SVC_PLN_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psptMsg.prcSvcPlnTpDescTxt, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
            psptMsgArray = (PRC_SVC_PLN_TPTMsgArray) S21CodeTableAccessor.findByCondition(psptMsg);

            if (psptMsgArray.length() > 0) {
                bean.setPrcSvcPlnTpCd(psptMsgArray.no(0).prcSvcPlnTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
            PRC_LIST_BANDTMsg psctMsg = new PRC_LIST_BANDTMsg();
            PRC_LIST_BANDTMsgArray psctMsgArray = new PRC_LIST_BANDTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcListBandDescTxt, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
            psctMsgArray = (PRC_LIST_BANDTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcListBandCd(psctMsgArray.no(0).prcListBandCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_ZONE_DESC_TXT"))) {
            PRC_SVC_ZONETMsg pszMsg = new PRC_SVC_ZONETMsg();
            PRC_SVC_ZONETMsgArray pszMsgArray = new PRC_SVC_ZONETMsgArray();
            ZYPEZDItemValueSetter.setValue(pszMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pszMsg.prcSvcZoneDescTxt, rsSet.getString("PRC_SVC_ZONE_DESC_TXT"));
            pszMsgArray = (PRC_SVC_ZONETMsgArray) S21CodeTableAccessor.findByCondition(pszMsg);

            if (pszMsgArray.length() > 0) {
                bean.setPrcSvcZoneCd(pszMsgArray.no(0).prcSvcZoneCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_SVC_ZONE_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("SPLY_AGMT_PLN_NM"))) {
            SPLY_AGMT_PLNTMsg sapTMsg = new SPLY_AGMT_PLNTMsg();
            sapTMsg.setSQLID("001");
            sapTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            sapTMsg.setConditionValue("splyAgmtPlnNm01", rsSet.getString("SPLY_AGMT_PLN_NM"));
            SPLY_AGMT_PLNTMsgArray sapTMsgArray = (SPLY_AGMT_PLNTMsgArray) EZDTBLAccessor.findByCondition(sapTMsg);
            if (sapTMsgArray.length() > 0) {
                bean.setSplyAgmtPlnPk(sapTMsgArray.no(0).splyAgmtPlnPk.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("SPLY_AGMT_PLN_NM"));
                isSuccess = false;
            }
        }

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Meter Package Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_COPY_VOL_CNT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Min Vol");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_COPY_VOL_CNT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Max Vol");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("CPC_AMT_RATE"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Cost Per Copy(Overage)");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_FROM_MTH_AOT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Term From (MTH)");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_THRU_MTH_AOT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Term To (MTH)");
            isSuccess = false;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Service Item");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_ZONE_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Service Zone(s) Included");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            if (rsltTMsg == null || rsltTMsg.length() == 0) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MDL_NM"));
                isSuccess = false;
            } else {
                bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
            }
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("TOT_BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Total Base Amount");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("SPLY_BASE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Supply Base Amount");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Meter Package Name
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            BigDecimal mtrPkgPk = getBigDecimalAnyItem("PRC_MTR_PKG", rsSet.getString("PRC_MTR_PKG_NM"));
            if (!ZYPCommonFunc.hasValue(mtrPkgPk)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_MTR_PKG_NM"));
                isSuccess = false;
            }
            bean.setPrcMtrPkgPk(mtrPkgPk);
        }

        // Meter Type
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbCd = getBllgMtrLb(rsSet.getString("MTR_LB_NM"));
            if (!ZYPCommonFunc.hasValue(mtrLbCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MTR_LB_NM"));
                isSuccess = false;
            }
            bean.setMtrLbCd(mtrLbCd);
        }

        // Service Item
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            String rtrnVal = getMdseNm(glblCmpyCd, rsSet.getString("MDSE_CD"), ONBATCH_TYPE.BATCH);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // Volume
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_COPY_VOL_CNT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_COPY_VOL_CNT"))) {
            if (rsSet.getBigDecimal("MIN_COPY_VOL_CNT").compareTo(rsSet.getBigDecimal("MAX_COPY_VOL_CNT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min Vol");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max Vol");
                isSuccess = false;
            }
        }

        // CPC
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_CPC_AMT_RATE")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_CPC_AMT_RATE"))) {
            if (rsSet.getBigDecimal("MIN_CPC_AMT_RATE").compareTo(rsSet.getBigDecimal("MAX_CPC_AMT_RATE")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min CPC");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max CPC");
                isSuccess = false;
            }
        }

        // Term From
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_FROM_MTH_AOT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_THRU_MTH_AOT"))) {
            if (rsSet.getBigDecimal("TERM_FROM_MTH_AOT").compareTo(rsSet.getBigDecimal("TERM_THRU_MTH_AOT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Term From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Tern Thru");
                isSuccess = false;
            }
        }

        // Meter Label exists check
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbNm = getMtrLb(rsSet.getString("MTR_LB_NM"));
            if (!ZYPCommonFunc.hasValue(mtrLbNm)) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MTR_LB_NM"));
                isSuccess = false;
            }
        }

        // Price Meter Package Billing Meter
        if (ZYPCommonFunc.hasValue(bean.getPrcMtrPkgPk()) && ZYPCommonFunc.hasValue(bean.getMtrLbCd()) && ZYPCommonFunc.hasValue(bean.getMdlId())) {
            BigDecimal prcMtrPkgBllgMtPk = getPrcMtrPkgBllgMtr(bean.getPrcMtrPkgPk(), bean.getMtrLbCd(), bean.getMdlId());
            if (!ZYPCommonFunc.hasValue(prcMtrPkgBllgMtPk)) {
                // glblMsg.B.no(i).prcMtrPkgNm_PB.setErrorInfo(1,
                // NMAM0163E, new String[] {"combined value",
                // "Price Meter Package Billing Meter" });
                isSuccess = false;
            } else {
                bean.setPrcMtrPkgBllgMtrPk(prcMtrPkgBllgMtPk);
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createSplyPgmParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListSplyPgmDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_SPLY_PGM");
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createSplyPgmWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListSplyPgmWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_SPLY_PGM_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListSplyPgmWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    /**
     * checkPrcListSvcSpecChrg.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @throws SQLException
     * @return boolean
     */
    public static boolean checkPrcListSvcSpecChrg(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");

        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_ADDL_CHRG_TP_DESC_TXT"))) {
            PRC_ADDL_CHRG_TPTMsg pactMsg = new PRC_ADDL_CHRG_TPTMsg();
            PRC_ADDL_CHRG_TPTMsgArray pactMsgArray = new PRC_ADDL_CHRG_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pactMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pactMsg.prcAddlChrgTpDescTxt, rsSet.getString("PRC_ADDL_CHRG_TP_DESC_TXT"));
            pactMsgArray = (PRC_ADDL_CHRG_TPTMsgArray) S21CodeTableAccessor.findByCondition(pactMsg);

            if (pactMsgArray.length() > 0) {
                bean.setPrcAddlChrgTpCd(pactMsgArray.no(0).prcAddlChrgTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_ADDL_CHRG_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("MKT_MDL_SEG_DESC_TXT"))) {
            MKT_MDL_SEGTMsg mmsMsg = new MKT_MDL_SEGTMsg();
            MKT_MDL_SEGTMsgArray mmsMsgArray = new MKT_MDL_SEGTMsgArray();
            ZYPEZDItemValueSetter.setValue(mmsMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mmsMsg.mktMdlSegDescTxt, rsSet.getString("MKT_MDL_SEG_DESC_TXT"));
            mmsMsgArray = (MKT_MDL_SEGTMsgArray) S21CodeTableAccessor.findByCondition(mmsMsg);

            if (mmsMsgArray.length() > 0) {
                bean.setMktMdlSegCd(mmsMsgArray.no(0).mktMdlSegCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MKT_MDL_SEG_DESC_TXT"));
                isSuccess = false;
            }
        }
        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Service Item");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("ADDL_CHRG_PRC_AMT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("ADDL_CHRG_PRC_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Price");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            if (rsltTMsg == null || rsltTMsg.length() == 0) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MDL_NM"));
                isSuccess = false;
            } else {
                bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
            }
        }

        // Service Item
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDSE_CD"))) {
            String rtrnVal = getMdseNm(glblCmpyCd, rsSet.getString("MDSE_CD"), ONBATCH_TYPE.BATCH);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createAddlChrgParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListAddlChrgDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_ADDL_CHRG");
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createAddlChrgWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListAddlChrgWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_ADDL_CHRG_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListAddlChrgWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    /**
     * checkPrcListLeaseRate.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @throws SQLException
     * @return boolean
     */
    public static boolean checkPrcListLeaseRate(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");
        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_PGM_TP_DESC_TXT"))) {
            PRC_PGM_TPTMsg pptMsg = new PRC_PGM_TPTMsg();
            PRC_PGM_TPTMsgArray pptMsgArray = new PRC_PGM_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pptMsg.prcPgmTpDescTxt, rsSet.getString("PRC_PGM_TP_DESC_TXT"));
            pptMsgArray = (PRC_PGM_TPTMsgArray) S21CodeTableAccessor.findByCondition(pptMsg);

            if (pptMsgArray.length() > 0) {
                bean.setPrcPgmTpCd(pptMsgArray.no(0).prcPgmTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_PGM_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_EQUIP_TP_DESC_TXT"))) {
            PRC_EQUIP_TPTMsg petMsg = new PRC_EQUIP_TPTMsg();
            PRC_EQUIP_TPTMsgArray petMsgArray = new PRC_EQUIP_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(petMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(petMsg.prcEquipTpDescTxt, rsSet.getString("PRC_EQUIP_TP_DESC_TXT"));
            petMsgArray = (PRC_EQUIP_TPTMsgArray) S21CodeTableAccessor.findByCondition(petMsg);

            if (petMsgArray.length() > 0) {
                bean.setPrcEquipTpCd(petMsgArray.no(0).prcEquipTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_EQUIP_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NM"))) {
            SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
            // Mod Start 2017/08/28 QC#19873
            //inTMsg.setSQLID("500");
            inTMsg.setSQLID("505");
            // Mod End 2017/08/28 QC#19873
            inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inTMsg.setConditionValue("dsAcctNm01", rsSet.getString("DS_ACCT_NM"));
            SELL_TO_CUSTTMsgArray outTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
            String sellToCustCd = checkLeaseAcct(outTMsgArray, rsSet.getString("DS_ACCT_NM"));
            if (sellToCustCd != null) {
                bean.setSellToCustCd(sellToCustCd);
            } else {
                setErrorMessage(messenger, rowNum);
                isSuccess = false;
            }
        }

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }
        if (!ZYPCommonFunc.hasValue(rsSet.getString("LEASE_RATE"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Lease Rate");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("MIN_TOT_FIN_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Total Financed Min");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("MAX_TOT_FIN_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Total Financed Max");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            if (rsltTMsg == null || rsltTMsg.length() == 0) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MDL_NM"));
                isSuccess = false;
            } else {
                bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
            }
        }

        // Total Financed
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MIN_TOT_FIN_AMT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("MAX_TOT_FIN_AMT"))) {
            if (rsSet.getBigDecimal("MIN_TOT_FIN_AMT").compareTo(rsSet.getBigDecimal("MAX_TOT_FIN_AMT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Total Financed Min");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Total Financed Max");
                isSuccess = false;
            }
        }

        // Term From
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_FROM_MTH_AOT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TERM_THRU_MTH_AOT"))) {
            if (rsSet.getBigDecimal("TERM_FROM_MTH_AOT").compareTo(rsSet.getBigDecimal("TERM_THRU_MTH_AOT")) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Min CPC");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Max CPC");
                isSuccess = false;
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createLeaseRateParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListLeaseRateDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_LEASE_RATE");
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createLeaseRateWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListLeaseRateWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_LEASE_RATE_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListLeaseRateWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    /**
     * checkPrcListLeaseRtrnFee.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @throws SQLException
     * @return boolean
     */
    public static boolean checkPrcListLeaseRtrnFee(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");
        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("SVC_SEG_DESC_TXT"))) {
            SVC_SEGTMsg ssTMsg = new SVC_SEGTMsg();
            SVC_SEGTMsgArray ssTMsgArray = new SVC_SEGTMsgArray();
            ZYPEZDItemValueSetter.setValue(ssTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ssTMsg.svcSegDescTxt, rsSet.getString("SVC_SEG_DESC_TXT"));
            ssTMsgArray = (SVC_SEGTMsgArray) S21CodeTableAccessor.findByCondition(ssTMsg);

            if (ssTMsgArray.length() > 0) {
                bean.setSvcSegCd(ssTMsgArray.no(0).svcSegCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("SVC_SEG_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"))) {
            PRC_IN_OUT_RGTMsg piorTMsg = new PRC_IN_OUT_RGTMsg();
            PRC_IN_OUT_RGTMsgArray piorTMsgArray = new PRC_IN_OUT_RGTMsgArray();
            ZYPEZDItemValueSetter.setValue(piorTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(piorTMsg.prcInOutRgDescTxt, rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"));
            piorTMsgArray = (PRC_IN_OUT_RGTMsgArray) S21CodeTableAccessor.findByCondition(piorTMsg);

            if (piorTMsgArray.length() > 0) {
                bean.setPrcInOutRgCd(piorTMsgArray.no(0).prcInOutRgCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"));
                isSuccess = false;
            }
        }

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("RTRN_FEE_AMT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Return Fee");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("DST_MILE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Distance (Miles)");
            isSuccess = false;
        }

        if (!checkScale(rsSet.getBigDecimal("RTRN_FEE_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Return Fee");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createLeaseRtrnParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListLeaseRtrnDuplicate(paramMap, null)) {
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_LEASE_RTRN");
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createLeaseRtrnWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListLeaseRtrnWrkDuplicate(wrkParamMap)) {
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_LEASE_RTRN_WRK");
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListLeaseRtrnWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    /**
     * checkPrcListTradeIn.
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param messenger ZYPCSVUploadMessenger
     * @throws SQLException
     * @return boolean
     */
    public static boolean checkPrcListTradeIn(NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd, ZYPCSVUploadMessenger messenger) throws SQLException {

        boolean isSuccess = true;
        ResultSet rsSet = bean.getRsSet();
        BigDecimal rowNum = rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM");
        // code check
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            } else {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
                isSuccess = false;
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pcTMsg.setConditionValue("prcCatgNm01", rsSet.getString("PRC_CATG_NM"));
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                PRC_CATGTMsg inTMsg = pcTMsgArray.no(0);
                bean.setPrcCatgCd(inTMsg.prcCatgCd.getValue());
                if (!inTMsg.prcListTpCd.getValue().equals(bean.getPrcListTpCd())) {
                    messenger.addMessageForRecord(rowNum, NMAM8482E, null);
                    isSuccess = false;
                }
            }
        }

        // Mandatory
        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price Category Name");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Price List Type");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getBigDecimal("TI_AMT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Trade In Value");
            isSuccess = false;
        }

        if (!ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            messenger.addMessageForRecord(rowNum, ZZM9000E, "Effective Date From");
            isSuccess = false;
        }

        // 2018/07/20 QC#27331 Add Start
        // Scale Check
        int aftDeclPntDigitNum = getDealCcyDigit(glblCmpyCd);

        if (!checkScale(rsSet.getBigDecimal("TI_AMT"), aftDeclPntDigitNum)) {
            messenger.addMessageForRecord(rowNum, ZZM9015E, "Trade In Value");
            isSuccess = false;
        }
        // 2018/07/20 QC#27331 Add End

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            if (rsltTMsg == null || rsltTMsg.length() == 0) {
                messenger.addMessageForRecord(rowNum, NMAM8479E, rsSet.getString("MDL_NM"));
                isSuccess = false;
            } else {
                bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
            }
        }

        // Meter
        if (ZYPCommonFunc.hasValue(rsSet.getBigDecimal("FROM_MTR_COPY_VOL_CNT")) && ZYPCommonFunc.hasValue(rsSet.getBigDecimal("THRU_MTR_COPY_VOL_CNT"))) {
            if ((rsSet.getBigDecimal("FROM_MTR_COPY_VOL_CNT").compareTo(rsSet.getBigDecimal("THRU_MTR_COPY_VOL_CNT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Meter From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Meter To");
                isSuccess = false;
            }
        }

        // Effective Date
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (ZYPDateUtil.compare(rsSet.getString("EFF_FROM_DT"), toHighValDate(rsSet.getString("EFF_THRU_DT"))) > 0) {
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date From");
                messenger.addMessageForRecord(rowNum, NMAM8480E, "Effective Date To");
                isSuccess = false;
            }
        }
        if (ZYPCommonFunc.hasValue(rsSet.getString("EFF_FROM_DT"))) {
            if (bean.getPrcCatgCd() != null) {
                Map<String, Object> paramMap = createTradeInParamMap(rsSet, bean, glblCmpyCd);
                if (!checkPrcListTradeInDuplicate(paramMap, null)) {
                    // Mod Start 2017/08/28 QC#19873
                    //messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_TRADE_IN");
                    messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_TI_VAL");
                    // Mod End 2017/08/28 QC#19873
                    isSuccess = false;
                }
            }

            Map<String, Object> wrkParamMap = createTradeInWrkParamMap(rsSet, bean, glblCmpyCd);
            if (!checkPrcListTradeInWrkDuplicate(wrkParamMap)) {
                // Mod Start 2017/08/28 QC#19873
                //messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_TRADE_IN_WRK");
                messenger.addMessageForRecord(rowNum, NMAM8481E, "PRC_LIST_TI_VAL_WRK");
                // Mod End 2017/08/28 QC#19873
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add Start
            if (!checkPrcListTradeInWrkBlankPeriod(rsSet, bean, glblCmpyCd)) {
                messenger.addMessageForRecord(rowNum, NMAM8724E, null);
                isSuccess = false;
            }
            // 2023/08/22 QC#61385 Add End
        }
        return isSuccess;
    }

    private static void setErrorMessage(EZDSStringItem item) {
        item.setErrorInfo(1, (String) messageMap.get("msgId"), (String[]) messageMap.get("msgPrm"));
        messageMap.clear();
    }

    private static void setErrorMessage(EZDSBigDecimalItem item) {
        item.setErrorInfo(1, (String) messageMap.get("msgId"), (String[]) messageMap.get("msgPrm"));
        messageMap.clear();
    }

    private static void setErrorMessage(ZYPCSVUploadMessenger messenger, BigDecimal rowNum) {
        String[] msg = (String[]) messageMap.get("msgPrm");
        String msgStr = null;
        if (msg != null && msg.length > 0) {
            msgStr = msg[0];
        }
        messenger.addMessageForRecord(rowNum, (String) messageMap.get("msgId"), msgStr);
        messageMap.clear();
    }

    private static void saveMessage(String msgId, String[] msgPrm) {
        messageMap.put("msgId", msgId);
        messageMap.put("msgPrm", msgPrm);
    }

    /**
     * checkPrcQlfyVal.
     * @param prcQlfyTpCd EZDSStringItem
     * @param prcQlfyVal EZDSStringItem
     * @param prcQlfyNm EZDSStringItem
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrcQlfyVal(EZDSStringItem prcQlfyTpCd, EZDSStringItem prcQlfyVal, EZDSStringItem prcQlfyNm, String glblCmpyCd) {

        boolean isSuccess = true;
        String rtrnVal = "";
        String prcQlfyTpCdStr = prcQlfyTpCd.getValue();
        String prcQlfyValStr = prcQlfyVal.getValue();

        isSuccess = checkPrcQlfyVal(prcQlfyTpCdStr, prcQlfyValStr, glblCmpyCd, rtrnVal, ONBATCH_TYPE.ONLINE);

        if (!isSuccess) {
            setErrorMessage(prcQlfyVal);
        } else {
            ZYPEZDItemValueSetter.setValue(prcQlfyNm, rtrnVal);
        }
        return isSuccess;
    }

    // Add Start 2018/07/17 QC#20267
    /**
     * checkPrcQlfyVal.
     * @param prcQlfyTpCd String
     * @param prcQlfyVal String
     * @param glblCmpyCd String
     * @param rtrnVal String
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean checkPrcQlfyVal(String prcQlfyTpCd, String prcQlfyVal, String glblCmpyCd, String rtrnVal, ONBATCH_TYPE onBatchType) {
        return checkPrcQlfyVal(prcQlfyTpCd, prcQlfyVal, glblCmpyCd, rtrnVal, onBatchType , null);
    }
    // Add End 2018/07/17 QC#20267

    // Mod Start 2018/07/17 QC#20267
    /**
     * checkPrcQlfyVal.
     * @param prcQlfyTpCd String
     * @param prcQlfyVal String
     * @param glblCmpyCd String
     * @param rtrnVal String
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean checkPrcQlfyVal(String prcQlfyTpCd, String prcQlfyVal, String glblCmpyCd, String rtrnVal, ONBATCH_TYPE onBatchType, NMXC105001PriceListCheckUtilCd bean) {
        boolean isSuccess = true;
        if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {
            rtrnVal = getMdseNm(glblCmpyCd, prcQlfyVal, onBatchType, bean);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                isSuccess = false;
            }
        } else if (isProductHierarchy(prcQlfyTpCd)) {
            if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCd)) {
                PROD_CTRLTMsg outTMsg = getProdCtrl(glblCmpyCd, prcQlfyVal, MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
                if (outTMsg == null) {
                    rtrnVal = null;
                    if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                        saveMessage(NMAM0163E, new String[] {prcQlfyVal, "Product Hierarchy 1" });
                    } else {
                        saveMessage(NMAM8479E, new String[] {prcQlfyVal });
                    }
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCd)) {
                PROD_CTRLTMsg outTMsg = getProdCtrl(glblCmpyCd, prcQlfyVal, MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
                if (outTMsg == null) {
                    rtrnVal = null;
                    if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                        saveMessage(NMAM0163E, new String[] {prcQlfyVal, "Product Hierarchy 2" });
                    } else {
                        saveMessage(NMAM8479E, new String[] {prcQlfyVal });
                    }
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCd)) {
                PROD_CTRLTMsg outTMsg = getProdCtrl(glblCmpyCd, prcQlfyVal, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
                if (outTMsg == null) {
                    rtrnVal = null;
                    if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                        saveMessage(NMAM0163E, new String[] {prcQlfyVal, "Product Hierarchy 3" });
                    } else {
                        saveMessage(NMAM8479E, new String[] {prcQlfyVal });
                    }
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCd)) {
                PROD_CTRLTMsg outTMsg = getProdCtrl(glblCmpyCd, prcQlfyVal, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
                if (outTMsg == null) {
                    rtrnVal = null;
                    if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                        saveMessage(NMAM0163E, new String[] {prcQlfyVal, "Product Hierarchy 4" });
                    } else {
                        saveMessage(NMAM8479E, new String[] {prcQlfyVal });
                    }
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCd)) {
                PROD_CTRLTMsg outTMsg = getProdCtrl(glblCmpyCd, prcQlfyVal, MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4);
                if (outTMsg == null) {
                    rtrnVal = null;
                    if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                        saveMessage(NMAM0163E, new String[] {prcQlfyVal, "Product Hierarchy 5" });
                    } else {
                        saveMessage(NMAM8479E, new String[] {prcQlfyVal });
                    }
                    isSuccess = false;
                } else {
                    rtrnVal = outTMsg.prodCtrlDescTxt.getValue();
                }
            }

        } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCd)) {
            rtrnVal = ZYPCodeDataUtil.getName(COA_PROJ.class, glblCmpyCd, prcQlfyVal);
            if (!ZYPCommonFunc.hasValue(rtrnVal)) {
                if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                    saveMessage(NMAM0163E, new String[] {prcQlfyVal, "Merchandise Item Type" });
                } else {
                    saveMessage(NMAM8479E, new String[] {prcQlfyVal });
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    // Mod End 2018/07/17 QC#20267

    /**
     * getMdseNm.
     * @param glblCmpyCd String
     * @param mdseVal EZDSStringItem
     * @return String
     */
    public static String getMdseNm(String glblCmpyCd, EZDSStringItem mdseVal) {
        String mdseValStr = mdseVal.getValue();
        String rtrnVal = getMdseNm(glblCmpyCd, mdseValStr, ONBATCH_TYPE.ONLINE);
        if (rtrnVal == null) {
            setErrorMessage(mdseVal);
        }

        return rtrnVal;
    }

    // Add Start 2018/07/17 QC#20267
    /**
     * getMdseNm.
     * @param glblCmpyCd String
     * @param mdseVal EZDSStringItem
     * @param onBatchType ONBATCH_TYPE
     * @return String
     */
    public static String getMdseNm(String glblCmpyCd, String mdseVal, ONBATCH_TYPE onBatchType) {
        return getMdseNm(glblCmpyCd, mdseVal, onBatchType, null);
    }
    // Add End 2018/07/17 QC#20267

    // Mod Start 2018/07/17 QC#20267
    /**
     * getMdseNm.
     * @param glblCmpyCd String
     * @param mdseVal EZDSStringItem
     * @param onBatchType ONBATCH_TYPE
     * @return String
     */
    public static String getMdseNm(String glblCmpyCd, String mdseVal, ONBATCH_TYPE onBatchType, NMXC105001PriceListCheckUtilCd bean) {
        String rtrnVal;
        String mdseCd = "";
        boolean isOver = false;
        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();
        String baseMdseCd = null;

        if (mdseVal.length() > 8) {
            mdseCd = mdseVal.substring(0, 8);
            isOver = true;
        } else {
            mdseCd = mdseVal;
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg == null) {
            mdseCd = mdseVal;
        } else {
            if (isOver) {
                saveMessage(NMAM8216E, new String[] {});
                rtrnVal = null;
                return rtrnVal;
            }
            mdseCd = ordTakeOutTMsg.mdseCd.getValue();
        }

        // Mod Start 2018/07/17 QC#20267
        MDSETMsg mdseOutTMsg = getMdse(glblCmpyCd, mdseCd);
//        MDSETMsg mdseInTMsg = new MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);
//        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        if (mdseOutTMsg == null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", glblCmpyCd);
            paramMap.put("mnfItemCd", mdseVal);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTableList(paramMap, "getEquipmentMdseInfo");
            if (!ssmResult.isCodeNotFound()) {

                List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (1 < ssmResult.getQueryResultCount()) {

                    Set<String> mdse8Set = new HashSet<String>();
                    for (Map<String, Object> mnfItem : mnfItemList) {
                        String mdseCd2 = (String) mnfItem.get("MDSE_CD");
                        if (8 < mdseCd2.length()) {
                            mdseCd2 = mdseCd2.substring(0, 8);
                        }
                        mdse8Set.add(mdseCd2);
                    }
                    if (1 < mdse8Set.size()) {
                        saveMessage(NMAM8685E, new String[] {});
                        rtrnVal = null;
                        return rtrnVal;
                    } else {
                        // Found one MnfItemCd
                        for (String mdse8 : mdse8Set) {
                            baseMdseCd = mdse8;
                            break;
                        }
                        ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdse(glblCmpyCd, baseMdseCd);
                        if (tMsg != null) {
                            mdseOutTMsg = getMdse(glblCmpyCd, tMsg.mdseCd.getValue());
                            // 2018/09/04 QC#28019 Mod Start
                            //bean.setMnfMdseCd(baseMdseCd);
                            if (bean != null) {
                                bean.setMnfMdseCd(baseMdseCd);
                                bean.setPkgUomClsCd(mdseOutTMsg.pkgUomClsCd.getValue());
                            }
                            // 2018/09/04 QC#28019 Mod End
                            return mdseOutTMsg.mdseNm.getValue();
                        } else {
                            saveMessage(NMAM8685E, new String[] {});
                            rtrnVal = null;
                            return rtrnVal;
                        }
                    }
                } else {
                    for (Map<String, Object> mnfItem : mnfItemList) {
                        rtrnVal = (String) mnfItem.get("MDSE_NM");
                        // 2018/09/04 QC#28019 Mod Start
                        //bean.setMnfMdseCd(rtrnVal);
                        if (bean != null) {
                            bean.setMnfMdseCd((String) mnfItem.get("MDSE_CD"));
                            bean.setPkgUomClsCd((String) mnfItem.get("PKG_UOM_CLS_CD"));
                        }
                        // 2018/09/04 QC#28019 Mod End
                        return rtrnVal;
                    }
                }
            }
            // Mod End 2018/07/17 QC#20267

            if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                saveMessage(NMAM0163E, new String[] {mdseCd, "Merchandise" });
            } else {
                saveMessage(NMAM8479E, new String[] {mdseCd });
            }
            rtrnVal = null;
            return rtrnVal;
        } else {
            rtrnVal = mdseOutTMsg.mdseNm.getValue();
            // 2018/09/04 QC#28019 Add Start
            if (bean != null) {
                bean.setPkgUomClsCd(mdseOutTMsg.pkgUomClsCd.getValue());
                bean.setMdseCd(mdseCd);
            }
            // 2018/09/04 QC#28019 Add End
        }
        return rtrnVal;
    }
    // Mod Start 2018/07/17 QC#20267

    private static boolean isProductHierarchy(String prcQlfyTpCd) {
        if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCd) //
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCd) //
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCd) //
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCd) //
                || PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCd)) {
            return true;
        } else {
            return false;
        }
    }

    private static PROD_CTRLTMsg getProdCtrl(String glblCmpyCd, String prcQlfyVal, String mdseStruElmntTpCd) {
        PROD_CTRLTMsg inTMsg = new PROD_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.prodCtrlCd, prcQlfyVal);
        PROD_CTRLTMsg outTMsg = (PROD_CTRLTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg != null
                && mdseStruElmntTpCd.equals(outTMsg.mdseStruElmntTpCd.getValue())) {
            return outTMsg;
        }
        return null;
    }

    /**
     * checkFormula.
     * @param prcFmlaPk EZDSBigDecimalItem
     * @param prcQlfyVal EZDSStringItem
     * @param prcQlfyTpCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkFormula(EZDSBigDecimalItem prcFmlaPk, EZDSStringItem prcQlfyVal, String prcQlfyTpCd, String glblCmpyCd) {

        boolean isSuccess = true;
        BigDecimal prcFmlaPkVal = prcFmlaPk.getValue();
        String prcQlfyValStr = prcQlfyVal.getValue();

        isSuccess = checkFormula(prcFmlaPkVal, prcQlfyValStr, prcQlfyTpCd, glblCmpyCd, ONBATCH_TYPE.ONLINE);

        if (!isSuccess) {
            setErrorMessage(prcFmlaPk);
        }

        return isSuccess;
    }

    /**
     * checkFormula.
     * @param prcFmlaPk BigDecimal
     * @param prcQlfyVal String
     * @param prcQlfyTpCd String
     * @param glblCmpyCd String
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    public static boolean checkFormula(BigDecimal prcFmlaPk, String prcQlfyVal, String prcQlfyTpCd, String glblCmpyCd, ONBATCH_TYPE onBatchType) {

        boolean isSuccess = true;
        PRC_FMLATMsg inTMsg = new PRC_FMLATMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcFmlaPk, prcFmlaPk);
        PRC_FMLATMsg outTMsg = (PRC_FMLATMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                saveMessage(NMAM0163E, new String[] {prcFmlaPk.toString(), "Price Formula" });
            } else {
                saveMessage(NMAM8479E, new String[] {prcFmlaPk.toString() });
            }
            isSuccess = false;
        } else {
            if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {
                if (!PRC_FMLA_TP.PRICE_LIST.equals(outTMsg.prcFmlaTpCd.getValue()) && !PRC_FMLA_TP.STANDARD_COST.equals(outTMsg.prcFmlaTpCd.getValue())) {
                    saveMessage(NMAM8218E, new String[] {});
                    isSuccess = false;
                } else {
                    if (PRC_FMLA_TP.PRICE_LIST.equals(outTMsg.prcFmlaTpCd.getValue())) {
                        if (ZYPCommonFunc.hasValue(outTMsg.prcCatgCd)) {
                            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getPrcListEquip(outTMsg.prcCatgCd.getValue(), prcQlfyVal);
                            if (ssmResult.isCodeNotFound()) {
                                if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                                    saveMessage(NMAM0163E, new String[] {prcFmlaPk.toString(), "Price List Equipment" });
                                } else {
                                    saveMessage(NMAM8479E, new String[] {prcFmlaPk.toString() });
                                }
                                isSuccess = false;
                            } else {
                                Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();
                                if (!ZYPCommonFunc.hasValue(toStr((BigDecimal) rsltMap.get("PRC_LIST_EQUIP_PRC_AMT"))) || ZYPCommonFunc.hasValue(toStr((BigDecimal) rsltMap.get("PRC_FMLA_PK")))) {
                                    saveMessage(NMAM8220E, new String[] {});
                                    isSuccess = false;
                                }
                            }
                        } else {
                            if (ONBATCH_TYPE.ONLINE.equals(onBatchType)) {
                                saveMessage(NMAM0163E, new String[] {prcFmlaPk.toString(), "Price List Equipment" });
                            } else {
                                saveMessage(NMAM8479E, new String[] {prcFmlaPk.toString() });
                            }
                            isSuccess = false;
                        }
                    }
                }
            } else {
                if (!PRC_FMLA_TP.PRICE_LIST.equals(outTMsg.prcFmlaTpCd.getValue())) {
                    saveMessage(NMAM8219E, new String[] {});
                    isSuccess = false;
                }
            }
        }
        return isSuccess;
    }

    /**
     * fmlaApiCall.
     * @param prcFmlaPk BigDecimal
     * @param prcQlfyTpCd String
     * @param prcQlfyValTxt String
     * @param pkgUomCd String
     * @param glblCmpyCd String
     * @param slsDt String
     * @param stdCcyCd String
     * @return boolean
     */
    public static boolean fmlaApiCall(BigDecimal prcFmlaPk, String prcQlfyTpCd, String prcQlfyValTxt, String pkgUomCd, String glblCmpyCd, String slsDt, String stdCcyCd) {

        NWZC170001PMsg fmlaApiPMsg = new NWZC170001PMsg();
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.xxModeCd, NWZC170001.MODE_NO_BASE);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.prcBaseDt, slsDt);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.prcFmlaPk, prcFmlaPk);
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ccyCd, stdCcyCd);

        if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {
            if (ZYPCommonFunc.hasValue(prcQlfyValTxt)) {
                ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.mdseCd, prcQlfyValTxt);
                if (prcQlfyValTxt.length() <= 8) {
                    ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ordTakeMdseCd, prcQlfyValTxt);
                }
            }
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.firstProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.scdProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.thirdProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.frthProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_5.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.fifthProdCtrlCd, prcQlfyValTxt);
        } else if (PRC_QLFY_TP.MERCHANDISE_TYPE.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.coaMdseTpCd, prcQlfyValTxt);
        }
        ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.ordCustUomQty, BigDecimal.ONE);

        if (PRC_QLFY_TP.ITEM_CODE.equals(prcQlfyTpCd)) {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.pkgUomCd, pkgUomCd);
        } else {
            ZYPEZDItemValueSetter.setValue(fmlaApiPMsg.pkgUomCd, PKG_UOM.EACH);
        }

        NWZC170001 api = new NWZC170001();
        api.execute(fmlaApiPMsg, ONBATCH_TYPE.ONLINE);
        if (fmlaApiPMsg.xxMsgIdList.getValidCount() > 0) {
            saveMessage(fmlaApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {fmlaApiPMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue(), fmlaApiPMsg.xxMsgIdList.no(0).xxMsgPrmTxt_1.getValue(),
                    fmlaApiPMsg.xxMsgIdList.no(0).xxMsgPrmTxt_2.getValue() });
            return false;
        }
        return true;
    }

    /**
     * checkPrcByUom.
     * @param itemCd EZDSStringItem
     * @param uomCd String
     * @param prcAmt BigDecimal
     * @return boolean
     */
    public static boolean checkPrcByUom(EZDSStringItem itemCd, String uomCd, BigDecimal prcAmt) {
        String itemCdStr = itemCd.getValue();
        return checkPrcByUom(itemCdStr, uomCd, prcAmt);
    }

    /**
     * checkPrcByUom.
     * @param itemCd String
     * @param uomCd String
     * @param prcAmt BigDecimal
     * @return boolean
     */
    public static boolean checkPrcByUom(String itemCd, String uomCd, BigDecimal prcAmt) {

        if (!ZYPCommonFunc.hasValue(prcAmt)) {
            return false;
        }

        // Add Start 2018/04/03 QC#25210
        if (!ZYPCommonFunc.hasValue(uomCd)) {
            return false;
        }
        // Add End 2018/04/03 QC#25210

        // Add Start 2018/08/07 QC#27488
        if (!ZYPCommonFunc.hasValue(itemCd)) {
            return false;
        }
        // Add End 2018/08/07 QC#27488

        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getInEachQty(itemCd, uomCd);
        if (ssmResult.isCodeNotFound()) {
            return false;
        }
        Map< ? , ? > rsltMap = (Map< ? , ? >) ssmResult.getResultObject();

        BigDecimal amtScale4 = prcAmt.setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal divideVal = (amtScale4.divide((BigDecimal) rsltMap.get("IN_EACH_QTY"))).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal divideValScale2 = divideVal.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (divideVal.compareTo(divideValScale2) != 0) {
            return false;
        }

        return true;
    }

    private static Map<String, Object> createEquipWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("prcListEquipConfigNum", rsSet.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
        paramMap.put("prcListEquipConfigNm", rsSet.getString("PRC_LIST_EQUIP_CONFIG_NM"));
        paramMap.put("prcQlfyTpDescTxt", rsSet.getString("PRC_QLFY_TP_DESC_TXT"));
        paramMap.put("prcQlfyValTxt", rsSet.getString("PRC_QLFY_VAL_TXT"));
        paramMap.put("pkgUomDescTxt", rsSet.getString("PKG_UOM_DESC_TXT"));
        paramMap.put("prcTermUomDescTxt", rsSet.getString("PRC_TERM_UOM_DESC_TXT"));
        paramMap.put("prcTermAot", rsSet.getBigDecimal("PRC_TERM_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListEquipDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListEquipDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getEquip");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getEquip");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    // Add Start 2017/08/28 QC#19873
    /**
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    private static Map<String, Object> createEquipParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("prcListEquipConfigNum", rsSet.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
        paramMap.put("prcListEquipConfigNm", rsSet.getString("PRC_LIST_EQUIP_CONFIG_NM"));
        paramMap.put("prcQlfyTpCd", bean.getPrcQlfyTpCd());
        paramMap.put("prcQlfyValTxt", rsSet.getString("PRC_QLFY_VAL_TXT"));
        paramMap.put("pkgUomCd", bean.getPkgUomCd());
        paramMap.put("prcTermUomCd", bean.getPrcTermUomCd());
        paramMap.put("prcTermAot", rsSet.getBigDecimal("PRC_TERM_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkDuplicate
     * @param paramMap Map<String, Object>
     * @param ssmResult S21SsmEZDResult
     * @return boolean
     */
    private static boolean checkDuplicate(Map<String, Object> paramMap, S21SsmEZDResult ssmResult) {
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) resultList.get(i);

                // 2023/08/22 QC#61385 Add Start
                if (ZYPDateUtil.compare((String) map.get("EFF_FROM_DT"), (String) paramMap.get("effFromDt")) == 0) {
                    if (!ZYPCommonFunc.hasValue((String) map.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue((String) paramMap.get("effThruDt"))) {
                        continue;
                    }
                    if (!ZYPCommonFunc.hasValue((String) map.get("EFF_THRU_DT")) && ZYPDateUtil.compare((String) paramMap.get("effThruDt"), HIGH_VAL_DT) == 0) {
                        continue;
                    }
                    if (ZYPDateUtil.compare((String) map.get("EFF_THRU_DT"), (String) paramMap.get("effThruDt")) == 0) {
                        continue;
                    }
                }
                // 2023/08/22 QC#61385 Add End
                
                if (ZYPCommonFunc.hasValue((String) map.get("EFF_THRU_DT"))) {
                    // if at least one of records has EFF_THRU_DT,
                    // it is duplicate error.
                    return false;
                } else {
                    // If EFF_THRU_DT is null(latest record), compare
                    // from date.
                    //
                    // EFF_FROM_DT : From Date of master table
                    // effFromDt : From Date of work table
                    // EFF_FROM_DT < effFromDt : true
                    // EFF_FROM_DT = effFromDt : false
                    // EFF_FROM_DT > effFromDt : false
                    if (ZYPDateUtil.compare((String) map.get("EFF_FROM_DT"), (String) paramMap.get("effFromDt")) >= 0) {
                        return false;
                    }
                }
            }

            return true;
        } else if (ssmResult.isCodeNotFound()) {
            return true;
        } else {
            return false;
        }
    }
    // Add End 2017/08/28 QC#19873

    /**
     * checkPrcListEquipWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListEquipWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getEquipWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
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

    /**
     * toHighValDate.
     * @param dateVal String
     * @return String
     */
    public static String toHighValDate(String dateVal) {
        if (ZYPCommonFunc.hasValue(dateVal)) {
            return dateVal;
        }
        return HIGH_VAL_DT;
    }

    /**
     * getInEachQty.
     * @param itemCd String
     * @param pkgUomCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInEachQty(String itemCd, String pkgUomCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("itemCd", itemCd);
        params.put("pkgUomCd", pkgUomCd);

        return getSsmEZDClient().queryObject("getInEachQty", params);
    }

    /**
     * getPrcListEquip.
     * @param prcCatgCd String
     * @param prcQlfyValTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListEquip(String prcCatgCd, String prcQlfyValTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", prcCatgCd);
        params.put("prcQlfyValTxt", prcQlfyValTxt);

        return getSsmEZDClient().queryObject("getPrcListEquip", params);
    }

    /**
     * getBigDecimalAnyItem.
     * @param tblNm String
     * @param whereVal String
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimalAnyItem(String tblNm, String whereVal) {

        String colNm = "";
        String whereCol = "";
        if ("PRC_MTR_PKG".equals(tblNm)) {
            colNm = "PRC_MTR_PKG_PK";
            whereCol = "PRC_MTR_PKG_NM";
        }

        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getAnyColmn(colNm, tblNm, whereCol, whereVal);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return new BigDecimal((String) ssmResult.getResultObject());
    }

    /**
     * getAnyColmn.
     * @param colNm String
     * @param tblNm String
     * @param whereCol String
     * @param whereVal String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAnyColmn(String colNm, String tblNm, String whereCol, String whereVal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("colNm", colNm);
        params.put("tblNm", tblNm);
        params.put("whereCol", whereCol);
        params.put("whereVal", whereVal);

        return getSsmEZDClient().queryObject("getAnyColmn", params);
    }

    /**
     * getMtrLb.
     * @param mtrLbNm String
     * @return S21SsmEZDResult
     */
    public static String getMtrLb(String mtrLbNm) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getMtrLabel(mtrLbNm);
        // getMtrLb
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * getMtrLb.
     * @param mtrLbNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMtrLabel(String mtrLbNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mtrLbNm", mtrLbNm);

        return getSsmEZDClient().queryObject("getMtrLb", params);
    }

    /**
     * getBllgMtrLb.
     * @param bllgMtrLbNm String
     * @return S21SsmEZDResult
     */
    public static String getBllgMtrLb(String bllgMtrLbNm) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getBllgMtrLabel(bllgMtrLbNm);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * getBllgMtrLb.
     * @param bllgMtrLbNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrLabel(String bllgMtrLbNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bllgMtrLbNm", bllgMtrLbNm);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getBllgMtrLb", params);
    }

    /**
     * getPrcMtrPkgBllgMtr.
     * @param prcMtrPkgPk BigDecimal
     * @param mtrLbCd String
     * @param mdlId BigDecimal
     * @return S21SsmEZDResult
     */
    public static BigDecimal getPrcMtrPkgBllgMtr(BigDecimal prcMtrPkgPk, String mtrLbCd, BigDecimal mdlId) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getPrcMtrPkgBllgMeter(prcMtrPkgPk, mtrLbCd, mdlId);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        return (BigDecimal) ssmResult.getResultObject();
    }

    /**
     * getPrcMtrPkgBllgMtr.
     * @param prcMtrPkgPk BigDecimal
     * @param mtrLbCd String
     * @param mdlId BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcMtrPkgBllgMeter(BigDecimal prcMtrPkgPk, String mtrLbCd, BigDecimal mdlId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMtrPkgPk", prcMtrPkgPk);
        params.put("mtrLbCd", mtrLbCd);
        params.put("mdlId", mdlId);
        params.put("slsDt", ZYPDateUtil.getSalesDate());
        params.put("highValDt", HIGH_VAL_DT);

        return getSsmEZDClient().queryObject("getPrcMtrPkgBllgMtr", params);
    }

    private static Map<String, Object> createSvcParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("prcMtrPkgPk", bean.getPrcMtrPkgPk());
        paramMap.put("prcSvcPlnTpCd", bean.getPrcSvcPlnTpCd());
        paramMap.put("prcSvcContrTpCd", bean.getPrcSvcContrTpCd());
        paramMap.put("bllgMtrLbCd", bean.getMtrLbCd());
        paramMap.put("prcListBandCd", bean.getPrcListBandCd());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("prcListMdseCd", rsSet.getString("PRC_LIST_MDSE_CD"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListSvcDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListSvcDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getSvc");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getSvc");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createSvcWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("prcMtrPkgNm", rsSet.getString("PRC_MTR_PKG_NM"));
        paramMap.put("prcSvcPlnTpDescTxt", rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
        paramMap.put("prcSvcContrTpDescTxt", rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
        paramMap.put("mtrLbNm", rsSet.getString("MTR_LB_NM"));
        paramMap.put("prcListBandDescTxt", rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("prcListMdseCd", rsSet.getString("PRC_LIST_MDSE_CD"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListSvcWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListSvcWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getSvcWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static Map<String, Object> createSvcTierParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("prcSvcTierTpCd", bean.getPrcSvcTierTpCd());
        paramMap.put("prcMtrPkgPk", bean.getPrcMtrPkgPk());
        paramMap.put("prcSvcPlnTpCd", bean.getPrcSvcPlnTpCd());
        paramMap.put("prcSvcContrTpCd", bean.getPrcSvcContrTpCd());
        paramMap.put("bllgMtrLbCd", bean.getMtrLbCd());
        paramMap.put("prcListBandCd", bean.getPrcListBandCd());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListSvcTierDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListSvcTierDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getSvcTier");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getSvcTier");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createSvcTierWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("prcMtrPkgNm", rsSet.getString("PRC_MTR_PKG_NM"));
        paramMap.put("prcSvcTierTpDescTxt", rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"));
        paramMap.put("prcSvcPlnTpDescTxt", rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
        paramMap.put("prcSvcContrTpDescTxt", rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
        paramMap.put("mtrLbNm", rsSet.getString("MTR_LB_NM"));
        paramMap.put("prcListBandDescTxt", rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListSvcTierWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListSvcTierWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getSvcTierWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static Map<String, Object> createSplyPgmParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("prcMtrPkgPk", bean.getPrcMtrPkgPk());
        paramMap.put("prcSvcPlnTpCd", bean.getPrcSvcPlnTpCd());
        paramMap.put("prcSvcContrTpCd", bean.getPrcSvcContrTpCd());
        paramMap.put("bllgMtrLbCd", bean.getMtrLbCd());
        paramMap.put("prcListBandCd", bean.getPrcListBandCd());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListSplyPgmDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListSplyPgmDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getSplyPgm");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getSplyPgm");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createSplyPgmWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("prcMtrPkgNm", rsSet.getString("PRC_MTR_PKG_NM"));
        paramMap.put("prcSvcPlnTpDescTxt", rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
        paramMap.put("prcSvcContrTpDescTxt", rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
        paramMap.put("mtrLbNm", rsSet.getString("MTR_LB_NM"));
        paramMap.put("prcListBandDescTxt", rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListSplyPgmWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListSplyPgmWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getSplyPgmWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static Map<String, Object> createAddlChrgParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("mdseCd", rsSet.getString("MDSE_CD"));
        paramMap.put("mktMdlSegCd", bean.getMktMdlSegCd());
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListAddlChrgDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListAddlChrgDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getAddlChrg");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getAddlChrg");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createAddlChrgWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("mdseCd", rsSet.getString("MDSE_CD"));
        paramMap.put("mktMdlSegDescTxt", rsSet.getString("MKT_MDL_SEG_DESC_TXT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListAddlChrgWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListAddlChrgWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getAddlChrgWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static Map<String, Object> createLeaseRateParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("prcLeaseCmpyAbbrNm", rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
        paramMap.put("dsAcctNm", rsSet.getString("DS_ACCT_NM"));
        paramMap.put("prcPgmTpCd", bean.getPrcPgmTpCd());
        paramMap.put("prcEquipTpCd", bean.getPrcEquipTpCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListLeaseRateDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListLeaseRateDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getLeaseRate");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getLeaseRate");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createLeaseRateWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("prcLeaseCmpyAbbrNm", rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
        paramMap.put("dsAcctNm", rsSet.getString("DS_ACCT_NM"));
        paramMap.put("prcPgmTpDescTxt", rsSet.getString("PRC_PGM_TP_DESC_TXT"));
        paramMap.put("prcEquipTpDescTxt", rsSet.getString("PRC_EQUIP_TP_DESC_TXT"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListLeaseRateWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListLeaseRateWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getLeaseRateWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checkLeaseAcct.
     * @param dacTMsgArray SELL_TO_CUSTTMsgArray
     * @param dsAcctNm String
     * @return boolean
     */
    public static String checkLeaseAcct(SELL_TO_CUSTTMsgArray dacTMsgArray, String dsAcctNm) {
        if (dacTMsgArray.length() == 1) {
            if (DS_ACCT_TP.CUSTOMER.equals(dacTMsgArray.no(0).dsAcctTpCd.getValue()) //
                    && DS_ACCT_CLS.LEASE_CO.equals(dacTMsgArray.no(0).dsAcctClsCd.getValue())) {
                return dacTMsgArray.no(0).sellToCustCd.getValue();
            } else {
                saveMessage(NMAM8479E, new String[] {dsAcctNm });
                return null;
            }
        } else if (dacTMsgArray.length() >= 2) {
            saveMessage(NMAM8479E, new String[] {dsAcctNm });
            return null;
        } else {
            saveMessage(NMAM8479E, new String[] {dsAcctNm });
            return null;
        }

    }

    private static Map<String, Object> createLeaseRtrnParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("prcLeaseCmpyAbbrNm", rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
        paramMap.put("svcSegCd", bean.getSvcSegCd());
        paramMap.put("prcInOutRgCd", bean.getPrcInOutRgCd());
        paramMap.put("dstMileAmt", rsSet.getBigDecimal("DST_MILE_AMT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListLeaseRtrnDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListLeaseRtrnDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getLeaseRtrn");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getLeaseRtrn");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createLeaseRtrnWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("prcLeaseCmpyAbbrNm", rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
        paramMap.put("svcSegDescTxt", rsSet.getString("SVC_SEG_DESC_TXT"));
        paramMap.put("prcInOutRgDescTxt", rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"));
        paramMap.put("dstMileAmt", rsSet.getBigDecimal("DST_MILE_AMT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListLeaseRtrnWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListLeaseRtrnWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getLeaseRtrnWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static Map<String, Object> createTradeInParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        // Mod Start 2017/08/28 QC#19873
        //paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("mdlId", bean.getMdlId());
        // Mod End 2017/08/28 QC#19873
        paramMap.put("fromMtrCopyVolCnt", rsSet.getBigDecimal("FROM_MTR_COPY_VOL_CNT"));
        paramMap.put("thruMtrCopyVolCnt", rsSet.getBigDecimal("THRU_MTR_COPY_VOL_CNT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListTradeInDuplicate.
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @return boolean
     */
    public static boolean checkPrcListTradeInDuplicate(Map<String, Object> paramMap, List<BigDecimal> keyListRec) {
        // Mod Start 2017/08/28 QC#19873
        //S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, keyListRec, "getLeaseRtrn");
        //if ((Integer) ssmResult.getResultObject() > 0) {
        //    return false;
        //} else {
        //    return true;
        //}
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, keyListRec, "getTradeIn");
        return checkDuplicate(paramMap, ssmResult);
        // Mod End 2017/08/28 QC#19873
    }

    private static Map<String, Object> createTradeInWrkParamMap(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("upldCsvRqstRowNum", rsSet.getBigDecimal("UPLD_CSV_RQST_ROW_NUM"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("fromMtrCopyVolCnt", rsSet.getBigDecimal("FROM_MTR_COPY_VOL_CNT"));
        paramMap.put("thruMtrCopyVolCnt", rsSet.getBigDecimal("THRU_MTR_COPY_VOL_CNT"));
        paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
        paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
        return paramMap;
    }

    /**
     * checkPrcListTradeInWrkDuplicate.
     * @param paramMap Map<String, Object>
     * @return boolean
     */
    public static boolean checkPrcListTradeInWrkDuplicate(Map<String, Object> paramMap) {
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "getTradeInWrk");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    // Add Start 2017/08/28 QC#19873
    /**
     * getUpdateEquip
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateEquip(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search Equip
            Map<String, Object> paramMap = createEquipParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getEquip");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateSvc
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateSvc(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search Svc
            Map<String, Object> paramMap = createSvcParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getSvc");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateSvcTier
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateSvcTier(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search SvcTier
            Map<String, Object> paramMap = createSvcTierParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getSvcTier");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateAddlChrg
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateAddlChrg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search AddlChrg
            Map<String, Object> paramMap = createAddlChrgParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getAddlChrg");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateSplyPgm
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateSplyPgm(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search SplyPgmg
            Map<String, Object> paramMap = createSplyPgmParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getSplyPgm");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateLeaseRate
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateLeaseRate(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search LeaseRate
            Map<String, Object> paramMap = createLeaseRateParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getLeaseRate");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateLeaseRtrn
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateLeaseRtrn(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search LeaseRtrn
            Map<String, Object> paramMap = createLeaseRtrnParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getLeaseRtrn");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//          resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateTradeIn
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @param newEffFromDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getUpdateTradeIn(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        List<Map<String, Object>> resultList = null;

        if (bean.getPrcCatgCd() != null) {
            // Search TradeIn
            Map<String, Object> paramMap = createTradeInParamMap(rsSet, bean, glblCmpyCd);
            S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getObjectList(paramMap, null, "getTradeIn");

            String newEffFromDt = (String) rsSet.getString("EFF_FROM_DT");
            // 2023/08/22 QC#61385 Mod Start
//            resultList = getUpdateRecord(ssmResult, newEffFromDt);
            String newEffThruDt = (String) rsSet.getString("EFF_THRU_DT");
            resultList = getUpdateRecord(ssmResult, newEffFromDt, newEffThruDt);
            // 2023/08/22 QC#61385 Mod End
        }

        return resultList;
    }

    /**
     * getUpdateRecord
     * @param ssmResult S21SsmEZDResult
     * @param newEffFromDt String
     * @param newEffThruDt String
     * @return List<Map<String, Object>>
     */
    // 2023/08/22 QC#61385 Mod Start
//    private static List<Map<String, Object>> getUpdateRecord(S21SsmEZDResult ssmResult, String newEffFromDt) {
    private static List<Map<String, Object>> getUpdateRecord(S21SsmEZDResult ssmResult, String newEffFromDt, String newEffThruDt) {   
    // 2023/08/22 QC#61385 Mod Start
        List<Map<String, Object>> resultList = null;

        if (ssmResult.isCodeNormal()) {
            resultList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> selectList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int i = 0; i < selectList.size(); i++) {
                Map<String, Object> selectMap = (Map<String, Object>) selectList.get(i);

                String effFromDt = (String) selectMap.get("EFF_FROM_DT");
                String effThruDt = (String) selectMap.get("EFF_THRU_DT");
                // 2023/08/22 QC#61385 Add Start
                if (ZYPDateUtil.compare(effFromDt, newEffFromDt) == 0) {
                    if ((!ZYPCommonFunc.hasValue(effThruDt) && !ZYPCommonFunc.hasValue(newEffThruDt))
                        || ZYPDateUtil.compare(effThruDt, newEffThruDt) == 0) {
                        resultList.add(selectMap);
                    }
                }
                // 2023/08/22 QC#61385 Add End
                
                if (!ZYPCommonFunc.hasValue(effThruDt) && ZYPDateUtil.compare(effFromDt, newEffFromDt) < 0) {
                    resultList.add(selectMap);
                }
            }
        }

        return resultList;
    }

    // 2018/07/20 QC#27331 Add Start
    /**
     * checkScale
     * @param value BigDecimal
     * @param digitNum int
     * @return boolean
     */
    private static boolean checkScale(BigDecimal value, int digitNum) {

        if (value == null) {
            return true;
        }

        if (value.scale() > digitNum) {
            return false;
        }

        return true;
    }

    /**
     * getDealCcyDigit
     * @param glblCmpyCd String
     * @return int
     */
    private static int getDealCcyDigit(String glblCmpyCd) {
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg != null) {
            CCYTMsg ccyTMsg = new CCYTMsg();
            ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, glblCmpyTMsg.stdCcyCd);
            ccyTMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(ccyTMsg);

            if (ccyTMsg != null) {
                return ccyTMsg.aftDeclPntDigitNum.getValueInt();
            }
        }
        return 0;
    }
    // 2018/07/20 QC#27331 Add End

    /**
     * getObjectList
     * @param paramMap Map<String, Object>
     * @param keyListRec List<BigDecimal>
     * @param tableNm String
     * @return S21SsmEZDResult
     */
    private S21SsmEZDResult getObjectList(Map<String, Object> paramMap, List<BigDecimal> keyListRec, String tableNm) {

        if (keyListRec == null || keyListRec.size() <= 0) {
            paramMap.put("dtlPk", null);
        } else {
            BigDecimal[] dtlPk = (BigDecimal[]) keyListRec.toArray(new BigDecimal[keyListRec.size()]);
            paramMap.put("dtlPk", dtlPk);
        }

        return getSsmEZDClient().queryObjectList(tableNm, paramMap);
    }
    // Add End 2017/08/28 QC#19873

    private S21SsmEZDResult getTable(Map<String, Object> paramMap, List<BigDecimal> keyListRec, String tableNm) {

        if (keyListRec == null || keyListRec.size() <= 0) {
            paramMap.put("dtlPk", null);
        } else {
            BigDecimal[] dtlPk = (BigDecimal[]) keyListRec.toArray(new BigDecimal[keyListRec.size()]);
            paramMap.put("dtlPk", dtlPk);
        }

        return getSsmEZDClient().queryObject(tableNm, paramMap);
    }

    // Add Start 2018/07/17 QC#20267
    private S21SsmEZDResult getTableList(Map<String, Object> paramMap, String tableNm) {

        return getSsmEZDClient().queryObjectList(tableNm, paramMap);
    }
    // Add End 2018/07/17 QC#20267

    private S21SsmEZDResult getTable(Map<String, Object> paramMap, String tableNm) {

        return getSsmEZDClient().queryObject(tableNm, paramMap);
    }

    // Mod Start 2018/07/17 QC#20267
    /**
     * Get Ord Take Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsg getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdse = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.ordTakeMdseCd, mdseCd);

        return (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdse);
    }

    /**
     * Get Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        // 2018/09/04 QC#28019 Add Start
        if (mdseCd.length() > MDSE_CD_LENGTH) {
            return null;
        }
        // 2018/09/04 QC#28019 Add End
        MDSETMsg mdseInTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);

        return (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseInTMsg);
    }
    // Mod End 2018/07/17 QC#20267
    
    // 2018/08/16 S21_NA#27716 Add Start
    // 2018/09/04 QC#28019 Mod Start
    //private String getPkgUomCdFromCache(String pkgUomDescTxt) {
    private String getPkgUomCdFromCache(String pkgUomDescTxt, String pkgUomClsCd) {
    // 2018/09/04 QC#28019 Mod End
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", getGlobalCompanyCode());
        paramMap.put("pkgUomDescTxt", pkgUomDescTxt);
        // 2018/09/04 QC#28019 Add Start
        paramMap.put("pkgUomClsCd", pkgUomClsCd);
        // 2018/09/04 QC#28019 Add End
        return NMXC10500PriceListDataCacheForSSM.getInstance().getPkgUom(paramMap, getSsmEZDClient());
    }

    // 2018/09/04 QC#28019 Mod Start
    //public static String getPkgUomCd(String pkgUomDescTxt) {
    //    return NMXC105001PriceListCheckUtil.getInstance().getPkgUomCdFromCache(pkgUomDescTxt);
    public static String getPkgUomCd(String pkgUomDescTxt, String pkgUomClsCd) {
        return NMXC105001PriceListCheckUtil.getInstance().getPkgUomCdFromCache(pkgUomDescTxt, pkgUomClsCd);
    // 2018/09/04 QC#28019 Mod End
    }
    // 2018/08/16 S21_NA#27716 Add End

    // 2023/08/22 QC#61385 Add Start
    /**
     * checkPrcListEquipWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListEquipWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createEquipBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListEquipWrkBlankPeriodCnt");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createEquipBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createEquipBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("prcListEquipConfigNum", rsSet.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
        paramMap.put("prcListEquipConfigNm", rsSet.getString("PRC_LIST_EQUIP_CONFIG_NM"));
        paramMap.put("prcQlfyTpCd", bean.getPrcQlfyTpCd());
        paramMap.put("prcQlfyValTxt", rsSet.getString("PRC_QLFY_VAL_TXT"));
        paramMap.put("pkgUomCd", bean.getPkgUomCd());
        paramMap.put("prcTermUomCd", bean.getPrcTermUomCd());
        paramMap.put("prcTermAot", rsSet.getBigDecimal("PRC_TERM_AOT"));
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("prcQlfyTpDescTxt", rsSet.getString("PRC_QLFY_TP_DESC_TXT"));
        paramMap.put("pkgUomDescTxt", rsSet.getString("PKG_UOM_DESC_TXT"));
        paramMap.put("prcTermUomDescTxt", rsSet.getString("PRC_TERM_UOM_DESC_TXT"));
        return paramMap;
    }

    /**
     * checkPrcListSvcWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListSvcWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createSvcBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListSvcWrkBlankPeriodCnt");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createSvcBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createSvcBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("prcMtrPkgPk", bean.getPrcMtrPkgPk());
        paramMap.put("prcSvcPlnTpCd", bean.getPrcSvcPlnTpCd());
        paramMap.put("prcSvcContrTpCd", bean.getPrcSvcContrTpCd());
        paramMap.put("bllgMtrLbCd",bean.getPrcSvcContrTpCd());
        paramMap.put("prcListBandCd", bean.getPrcListBandCd());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("prcListMdseCd", rsSet.getString("PRC_LIST_MDSE_CD"));
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("prcMtrPkgNm", rsSet.getString("PRC_MTR_PKG_NM"));
        paramMap.put("prcSvcPlnTpDescTxt", rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
        paramMap.put("prcSvcContrTpDescTxt", rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
        paramMap.put("mtrLbNm", rsSet.getString("MTR_LB_NM"));
        paramMap.put("prcListBandDescTxt", rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
        return paramMap;
    }

    /**
     * checkPrcListSvcTierWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListSvcTierWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createSvcTierBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListSvcTierWrkBlankPeriodCnt");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createSvcTierBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createSvcTierBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("prcSvcTierTpCd", bean.getPrcSvcTierTpCd());
        paramMap.put("prcMtrPkgPk", bean.getPrcMtrPkgPk());
        paramMap.put("prcSvcPlnTpCd", bean.getPrcSvcPlnTpCd());
        paramMap.put("prcSvcContrTpCd", bean.getPrcSvcContrTpCd());
        paramMap.put("bllgMtrLbCd",bean.getPrcSvcContrTpCd());
        paramMap.put("prcListBandCd", bean.getPrcListBandCd());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("prcMtrPkgNm", rsSet.getString("PRC_MTR_PKG_NM"));
        paramMap.put("prcSvcTierTpDescTxt", rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"));
        paramMap.put("prcSvcPlnTpDescTxt", rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
        paramMap.put("prcSvcContrTpDescTxt", rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
        paramMap.put("mtrLbNm", rsSet.getString("MTR_LB_NM"));
        paramMap.put("prcListBandDescTxt", rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
        return paramMap;
    }

    /**
     * checkPrcListAddlChrgWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListAddlChrgWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createAddlChrgWrkBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListAddlChrgWrkBlankPeriod");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createAddlChrgWrkBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createAddlChrgWrkBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdseCd",bean.getMdseCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("mktMdlSegCd", bean.getMktMdlSegCd());
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("mktMdlSegDescTxt", rsSet.getString("MKT_MDL_SEG_DESC_TXT"));
        return paramMap;
    }

    /**
     * checkPrcListSplyPgmWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListSplyPgmWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createSplyPgmWrkBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListSplyPgmWrkBlankPeriod");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createSplyPgmWrkBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createSplyPgmWrkBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("prcMtrPkgPk", bean.getPrcMtrPkgPk());
        paramMap.put("prcSvcPlnTpCd", bean.getPrcSvcPlnTpCd());
        paramMap.put("prcSvcContrTpCd", bean.getPrcSvcContrTpCd());
        paramMap.put("bllgMtrLbCd", bean.getPrcSvcContrTpCd());
        paramMap.put("prcListBandCd", bean.getPrcListBandCd());
        paramMap.put("termFromMthAot", rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot", rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("prcCatgNm", rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        paramMap.put("prcMtrPkgNm", rsSet.getString("PRC_MTR_PKG_NM"));
        paramMap.put("prcSvcPlnTpDescTxt", rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
        paramMap.put("prcSvcContrTpDescTxt", rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
        paramMap.put("mtrLbNm", rsSet.getString("MTR_LB_NM"));
        paramMap.put("prcListBandDescTxt", rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
        return paramMap;
    }

    /**
     * checkPrcListLeaseRateWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListLeaseRateWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createLeaseRateWrkBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListLeaseRateWrkBlankPeriod");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createLeaseRateWrkBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createLeaseRateWrkBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("prcLeaseCmpyAbbrNm",rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
        paramMap.put("dsAcctNm",rsSet.getString("DS_ACCT_NM"));
        paramMap.put("prcPgmTpCd",bean.getPrcPgmTpCd());
        paramMap.put("prcEquipTpCd",bean.getPrcEquipTpCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("termFromMthAot",rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
        paramMap.put("termThruMthAot",rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
        paramMap.put("prcCatgNm",rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("prcPgmTpDescTxt",rsSet.getString("PRC_PGM_TP_DESC_TXT"));
        paramMap.put("prcEquipTpDescTxt",rsSet.getString("PRC_EQUIP_TP_DESC_TXT"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        return paramMap;
    }

    /**
     * checkPrcListLeaseRtrnWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListLeaseRtrnWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createLeaseRtrnWrkBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListLeaseRtrnWrkBlankPeriod");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createLeaseRtrnWrkBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createLeaseRtrnWrkBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("prcLeaseCmpyAbbrNm",rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
        paramMap.put("svcSegCd",bean.getSvcSegCd());
        paramMap.put("prcInOutRgCd",bean.getPrcInOutRgCd());
        paramMap.put("dstMileAmt",rsSet.getString("DST_MILE_AMT"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("prcCatgNm",rsSet.getString("PRC_CATG_NM"));
        paramMap.put("svcSegDescTxt",rsSet.getString("SVC_SEG_DESC_TXT"));
        paramMap.put("prcInOutRgDescTxt",rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"));
        return paramMap;
    }

    /**
     * checkPrcListTradeInWrkBlankPeriod
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkPrcListTradeInWrkBlankPeriod(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap = createTradeInWrkBlankPeriodParam(rsSet, bean, glblCmpyCd);
        S21SsmEZDResult ssmResult = NMXC105001PriceListCheckUtil.getInstance().getTable(paramMap, "checkPrcListTradeInWrkBlankPeriod");
        if ((Integer) ssmResult.getResultObject() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * createTradeInWrkBlankPeriodParam
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     * @throws SQLException
     */
    private static Map<String, Object> createTradeInWrkBlankPeriodParam(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String glblCmpyCd) throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("prcCatgCd", bean.getPrcCatgCd());
        paramMap.put("mdlId", bean.getMdlId());
        paramMap.put("fromMtrCopyVolCnt", rsSet.getBigDecimal("FROM_MTR_COPY_VOL_CNT"));
        paramMap.put("thruMtrCopyVolCnt", rsSet.getBigDecimal("THRU_MTR_COPY_VOL_CNT"));
        paramMap.put("prcCatgNm",rsSet.getString("PRC_CATG_NM"));
        paramMap.put("upldCsvRqstPk", rsSet.getBigDecimal("UPLD_CSV_RQST_PK"));
        paramMap.put("mdlNm", rsSet.getString("MDL_NM"));
        return paramMap;
    }
    // 2023/08/22 QC#61385 Add End
}
