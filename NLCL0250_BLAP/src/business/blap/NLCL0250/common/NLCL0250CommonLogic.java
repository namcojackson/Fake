/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0250.common;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLCL0250.NLCL0250CMsg;
import business.blap.NLCL0250.NLCL0250Query;
import business.blap.NLCL0250.NLCL0250SMsg;
import business.blap.NLCL0250.NLCL0250_ASMsg;
import business.blap.NLCL0250.constant.NLCL0250Constant;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.STK_STSTMsg;
import business.db.STK_STSTMsgArray;
import business.file.NLCL0250F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3137
 * 02/18/2016   CSAI            Y.Imazu         Update          QC#3141
 * 09/14/2016   CSAI            Y.Imazu         Update          QC#13187
 * 10/20/2016   CSAI            Y.Imazu         Update          QC#14081
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 * 02/22/2018   CITS            T.Wada          Update          QC#21830
 * 03/20/2018   CITS            S.Katsuma       Update          QC#24715
 * 08/23/2018   CITS            K.Ogino         Update          QC#27696
 * 10/03/2018   CITS            T.Hakodate      Update          QC#28599
 * 10/25/2018   CITS            M.Naito         Update          QC#28867
 * 12/06/2018   CITS            M.Naito         Update          QC#29249
 * 02/07/2019   CITS            K.Ogino         Update          QC#30045
 * 05/12/2020   Fujitsu         T.Ogura         Update          QC#56668
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */
public class NLCL0250CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * createInitHeader
     * @param cMsg NLCL0250CMsg
     * @param srchOptUsrId String
     */
    public static void createInitHeader(NLCL0250CMsg cMsg, String srchOptUsrId) {

        // Setup select box 
        createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);

        // Create Pull-Down
        createRtlWhCatgPullDown(cMsg);
        createCoaProjPullDown(cMsg);
        createMdseItemTpPullDown(cMsg);
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        createWhOpPullDown(cMsg);
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        // Check Box Table
        createLocStsChkBox(cMsg);
        createStkStsChkBox(cMsg);

        // Check Display Option Summary
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_H1, NLCL0250Constant.SUMMARY_SEARCH);
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param cMsg NLCL0250CMsg
     * @param srchOptUsrId String
     */
    private static void createSavedSearchOptionsPullDown(NLCL0250CMsg cMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getSavedSearchOptionList(cMsg, srchOptUsrId);

        if (!ssmResult.isCodeNormal()) {

            cMsg.srchOptPk_L.clear();
            cMsg.srchOptNm_L.clear();
            return;
        }

        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < cMsg.srchOptPk_L.length(); i++) {

            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            cMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * creatertlWhCatgPullDown
     * @param cMsg NLCL0250CMsg
     */
    private static void createRtlWhCatgPullDown(NLCL0250CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getPullDownList(cMsg, "getRtlWhCatgList");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> rtlWhCatgList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (rtlWhCatgList != null && !rtlWhCatgList.isEmpty()) {

                for (int i = 0; i < rtlWhCatgList.size(); i++) {

                    Map<String, Object> rtlWhCatgMap = (Map<String, Object>) rtlWhCatgList.get(i);

                    if (i >= cMsg.rtlWhCatgCd_L1.length()) {

                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_L1.no(i), (String) rtlWhCatgMap.get("RTL_WH_CATG_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_LR.no(i), (String) rtlWhCatgMap.get("RTL_WH_CATG_DESC_TXT"));
                }

                return;
            }
        }

        cMsg.setMessageInfo(NLCL0250Constant.NLCM0170E, new String[]{"Warehouse Type Master"});
        return;
    }

    /**
     * createCoaProjPullDown
     * @param cMsg NLCL0250CMsg
     */
    private static void createCoaProjPullDown(NLCL0250CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getPullDownList(cMsg, "getCoaProjList");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> coaProjList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (coaProjList != null && !coaProjList.isEmpty()) {

                for (int i = 0; i < coaProjList.size(); i++) {

                    Map<String, Object> coaProjgMap = (Map<String, Object>) coaProjList.get(i);

                    if (i >= cMsg.coaProjCd_L1.length()) {

                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_L1.no(i), (String) coaProjgMap.get("COA_PROJ_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_LP.no(i), (String) coaProjgMap.get("COA_PROJ_DESC_TXT"));
                }

                return;
            }
        }

        cMsg.setMessageInfo(NLCL0250Constant.NLCM0170E, new String[]{"Merchandise Type Master"});
        return;
    }

    /**
     * createMdseItemTpPullDown
     * @param cMsg NLCL0250CMsg
     */
    private static void createMdseItemTpPullDown(NLCL0250CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getPullDownList(cMsg, "getMdseItemTpList");

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> mdseItemTpList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (mdseItemTpList != null && !mdseItemTpList.isEmpty()) {

                for (int i = 0; i < mdseItemTpList.size(); i++) {

                    Map<String, Object> mdseItemTpMap = (Map<String, Object>) mdseItemTpList.get(i);

                    if (i >= cMsg.mdseItemTpCd_L1.length()) {

                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd_L1.no(i), (String) mdseItemTpMap.get("MDSE_ITEM_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem61Txt_LI.no(i), (String) mdseItemTpMap.get("MDSE_ITEM_TP_DESC_TXT"));
                }

                return;
            }
        }

        cMsg.setMessageInfo(NLCL0250Constant.NLCM0170E, new String[]{"Item Type Master"});
        return;
    }
    // START 2023/03/07 S.Dong [QC#61205, ADD]
    /**
     * creatertlWhOpPullDown
     * @param cMsg NLCL0250CMsg
     */
    private static void createWhOpPullDown(NLCL0250CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(WH_OWNR.class, cMsg.whOwnrCd_L1, cMsg.whOwnrNm_L1);
    }
    // END 2023/03/07 S.Dong [QC#61205, ADD]
    /**
     * createLocStsChkBox
     * @param cMsg NLCL0250CMsg
     */
    private static void createLocStsChkBox(NLCL0250CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getLocStsList(cMsg);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (resultList != null && !resultList.isEmpty()) {

                int validCount = resultList.size();

                for (int i = 0; i < resultList.size(); i++) {

                    Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

                    if (i >= cMsg.L.length()) {

                        validCount = i;
                        break;
                    }

                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxScrItem61Txt_LS, (String) resultMap.get("LOC_STS_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).locStsCd_LS, (String) resultMap.get("LOC_STS_CD"));
                }

                cMsg.L.setValidCount(validCount);
                return;
            }
        }

        cMsg.setMessageInfo(NLCL0250Constant.NLCM0170E, new String[]{"Location Status Master"});
        return;
    }

    /**
     * createStkStsChkBox
     * @param cMsg NLCL0250CMsg
     */
    private static void createStkStsChkBox(NLCL0250CMsg cMsg) {

        STK_STSTMsg stkStsTMsg = new STK_STSTMsg();
        stkStsTMsg.setSQLID("001");
        stkStsTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        STK_STSTMsgArray stkStsResult = (STK_STSTMsgArray) EZDTBLAccessor.findByCondition(stkStsTMsg);

        if (stkStsResult != null && stkStsResult.getValidCount() != 0) {

            StringBuffer chkBoxNm = null;
            int validCount = stkStsResult.getValidCount();

            for (int i = 0; i < stkStsResult.getValidCount(); i++) {

                if (i >= cMsg.S.length()) {

                    validCount = i;
                    break;
                }

                chkBoxNm = new StringBuffer();
                chkBoxNm.append(stkStsResult.no(i).stkStsCd.getValue());
                chkBoxNm.append(":");
                chkBoxNm.append(stkStsResult.no(i).stkStsDescTxt.getValue());

                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxScrItem61Txt_SS, chkBoxNm.toString());
                ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).stkStsCd_SS, stkStsResult.no(i).stkStsCd);
            }

            cMsg.S.setValidCount(validCount);

        } else {

            cMsg.setMessageInfo(NLCL0250Constant.NLZM0024E);
            return;
        }
    }

    /**
     * clearAll
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     */
    public static void clearAll(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) {

        // Clear Table
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.S);
        ZYPTableUtil.clear(cMsg.L);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(sMsg.A);
        sMsg.clear();

        // Clear Search Option
        cMsg.srchOptPk_S.clear();
        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_S.clear();
        cMsg.srchOptNm_L.clear();

        // Clear Search Condition
        cMsg.zerothProdCtrlNm_H1.clear();
        cMsg.firstProdCtrlNm_H1.clear();
        cMsg.scdProdCtrlNm_H1.clear();
        cMsg.thirdProdCtrlNm_H1.clear();
        cMsg.frthProdCtrlNm_H1.clear();
        cMsg.coaProjCd_H1.clear();
        cMsg.coaProjCd_L1.clear();
        cMsg.xxScrItem61Txt_LP.clear();
        cMsg.mdseItemTpCd_H1.clear();
        cMsg.mdseItemTpCd_L1.clear();
        cMsg.xxScrItem61Txt_LI.clear();
        cMsg.xxMdseSrchTxt_H1.clear();
        cMsg.xxSerNumSrchTxt_H1.clear();
        cMsg.srchOptTxt_CF.clear();
        cMsg.rtlWhCatgCd_H1.clear();
        cMsg.rtlWhCatgCd_L1.clear();
        cMsg.xxScrItem61Txt_LR.clear();
        cMsg.rtlWhCdSrchTxt_RW.clear();
        cMsg.rtlWhNmSrchTxt_RW.clear();
        cMsg.rtlWhCdSrchTxt_SW.clear();
        cMsg.rtlWhNmSrchTxt_SW.clear();
        cMsg.xxFldValTxt_HC.clear();
        cMsg.xxFldValTxt_HN.clear();
        cMsg.xxFromDt_H1.clear();
        cMsg.xxThruDt_H1.clear();
        cMsg.xxDplyCtrlFlg_H1.clear();
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        cMsg.xxChkBox_PR.clear();
        cMsg.rtrnCtrlTpVndRelnPk_H1.clear();
        cMsg.rtrnCtrlTpCd_H1.clear();
        cMsg.rtrnCtrlTpNm_H1.clear();
        cMsg.prntVndCd_H1.clear();
        cMsg.prntVndNm_H1.clear();
        cMsg.vndCd_H1.clear();
        cMsg.vndNm_H1.clear();
        cMsg.xxScrItem500Txt_H1.clear();
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        cMsg.whOwnrCd_H1.clear();
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        // Clear Search Condition Backup
        cMsg.zerothProdCtrlNm_HB.clear();
        cMsg.firstProdCtrlNm_HB.clear();
        cMsg.scdProdCtrlNm_HB.clear();
        cMsg.thirdProdCtrlNm_HB.clear();
        cMsg.frthProdCtrlNm_HB.clear();
        cMsg.xxMdseSrchTxt_HB.clear();
        cMsg.xxSerNumSrchTxt_HB.clear();
        cMsg.srchOptTxt_HB.clear();
        cMsg.rtlWhCdSrchTxt_RB.clear();
        cMsg.rtlWhNmSrchTxt_RB.clear();
        cMsg.rtlWhCdSrchTxt_SB.clear();
        cMsg.rtlWhNmSrchTxt_SB.clear();
        cMsg.xxFldValTxt_BC.clear();
        cMsg.xxFldValTxt_BN.clear();
        cMsg.xxFromDt_HB.clear();
        cMsg.xxThruDt_HB.clear();
        cMsg.coaProjCd_HB.clear();
        cMsg.mdseItemTpCd_HB.clear();
        cMsg.rtlWhCatgCd_HB.clear();
        cMsg.xxDplyCtrlFlg_HB.clear();
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        cMsg.rtrnCtrlTpVndRelnPk_HB.clear();
        cMsg.xxChkBox_HB.clear();
        cMsg.rtrnCtrlTpCd_HB.clear();
        cMsg.rtrnCtrlTpNm_HB.clear();
        cMsg.prntVndCd_HB.clear();
        cMsg.prntVndNm_HB.clear();
        cMsg.vndCd_HB.clear();
        cMsg.vndNm_HB.clear();
        cMsg.xxScrItem500Txt_HB.clear();
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        cMsg.whOwnrCd_HB.clear();
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        // Hidden
        cMsg.xxMntEventNm.clear();
        cMsg.srchOptTxt_LS.clear();
        cMsg.srchOptTxt_SS.clear();
        cMsg.xxPopSqlTxt.clear();
        cMsg.xxFileData.clear();

        // Clear Detail
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        cMsg.xxPageShowCurNum_A.clear();
        cMsg.xxPageShowTotNum_A.clear();
        cMsg.xxSortTblNm_A.clear();
        cMsg.xxSortItemNm_A.clear();
        cMsg.xxSortOrdByTxt_A.clear();
    }

    /**
     * isChkReqSrchCond
     * @param cMsg NLCL0250CMsg
     * @return boolean
     */
    public static boolean isChkReqSrchCond(NLCL0250CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCdSrchTxt_RB)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.rtlWhCdSrchTxt_SB)) {

            return true;

        } else if (ZYPCommonFunc.hasValue(cMsg.xxFldValTxt_BC)) {

            return true;
        // QC#21830
        } else if (ZYPCommonFunc.hasValue(cMsg.xxMdseSrchTxt_HB)) {
            return true;
        }

        return false;
    }
    /**
     * getSrchCondListMap
     * @param cMsg NLCL0250CMsg
     * @param isDnld boolean
     * @return boolean
     */
    public static Map<String, ArrayList<String>> getSrchCondListMap(NLCL0250CMsg cMsg, boolean isDnld) {

        Map<String, ArrayList<String>> srchCondListMap = new HashMap<String, ArrayList<String>>();
        boolean isCheckResult = true;
        boolean isCheckInvtyLoc = true;

        // Retail WH
        if (chkScrhCondHasValue(cMsg.rtlWhCdSrchTxt_RB)) {

            ArrayList<String> rtlWhCdList = getRtlWhCdList(cMsg, cMsg.rtlWhCdSrchTxt_RB, isDnld);

            if (rtlWhCdList == null || rtlWhCdList.isEmpty()) {

                isCheckResult = false;

            } else {

                srchCondListMap.put("whCdList", rtlWhCdList);
            }

        } else {

            isCheckInvtyLoc = false;
        }

        // Sub WH
        if (chkScrhCondHasValue(cMsg.rtlWhCdSrchTxt_SB)) {

            ArrayList<String> swhCdList = getSwhCdList(cMsg, cMsg.rtlWhCdSrchTxt_SB, isDnld);

            if (swhCdList == null || swhCdList.isEmpty()) {

                isCheckResult = false;

            } else {

                srchCondListMap.put("swhCdList", swhCdList);
            }

        } else {

            isCheckInvtyLoc = false;
        }

        // Inventory Location
        if (isCheckResult && isCheckInvtyLoc && !isChkInvtyLoc(cMsg)) {

            isCheckResult = false;
        }

        // Ship To Location
        if (chkScrhCondHasValue(cMsg.xxFldValTxt_BC)) {

            ArrayList<String> toLocCdList = getToLocCdList(cMsg, cMsg.xxFldValTxt_BC, isDnld);

            if (toLocCdList == null || toLocCdList.isEmpty()) {

                isCheckResult = false;

            } else {

                srchCondListMap.put("toLocCdList", toLocCdList);
            }
        }
        // QC#21830
        // Item Master Check
        if (chkScrhCondHasValue(cMsg.xxMdseSrchTxt_HB)) {

            ArrayList<String> mdseCdList = getMdseCdList(cMsg, cMsg.xxMdseSrchTxt_HB);

            if (mdseCdList == null || mdseCdList.isEmpty()) {

                isCheckResult = false;

            } else {

                srchCondListMap.put("mdseCdList", mdseCdList);
            }
        }
//        if (ZYPCommonFunc.hasValue(cMsg.xxMdseSrchTxt_H1)) {
//
//            isCheckResult = getMdseCd(cMsg, isDnld);
//
//        }

        if (!isCheckResult) {

            return null;
        }

        return srchCondListMap;
    }
    // QC#21830
    /**
     * 
     * @param cMsg
     * @param mdseCdStrgItem
     * @param isDnld
     * @return
     */
    public static ArrayList<String> getMdseCdList(NLCL0250CMsg cMsg, EZDCStringItem mdseCdStrgItem) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdseCdList", splitSrchTxt(mdseCdStrgItem, false, null));
        params.put("rowNum", NLCL0250Constant.CODE_SEARCH_SIZE);

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getMdseCd(params);

        if (ssmResult.isCodeNormal()) {

            ArrayList<Map<String, String>> toLocMapList = (ArrayList<Map<String, String>>) ssmResult.getResultObject();

            if (toLocMapList != null && !toLocMapList.isEmpty()) {

                ArrayList<String> mdseCdList = new ArrayList<String>();

                for (Map<String, String> toLocMap : toLocMapList) {

                    mdseCdList.add(toLocMap.get("MDSE_CD"));
                }


                return mdseCdList;
            }
        }
        cMsg.xxMdseSrchTxt_H1.setErrorInfo(1, NLCL0250Constant.NLZM2278E, new String[]{"Item Number"});
        return null;

    }
    /**
     * chkScrhCondHasValue
     * @param item EZDCStringItem
     */
    private static boolean chkScrhCondHasValue(EZDCStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {

            ArrayList<String> splitItemList = splitSrchTxt(item, false, null);

            if (splitItemList == null || splitItemList.isEmpty()) {

                return false;
            }

            return true;
        }

        return false;
    }

    /**
     * setSearchCondToBackUp
     * @param cMsg NLCL0250CMsg
     */
    public static void setSearchCondToBackUp(NLCL0250CMsg cMsg) {

        // Copy from Search Condition to Backup
        ZYPEZDItemValueSetter.setValue(cMsg.zerothProdCtrlNm_HB, cMsg.zerothProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.firstProdCtrlNm_HB, cMsg.firstProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.scdProdCtrlNm_HB, cMsg.scdProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.thirdProdCtrlNm_HB, cMsg.thirdProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.frthProdCtrlNm_HB, cMsg.frthProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_HB, cMsg.coaProjCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd_HB, cMsg.mdseItemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxMdseSrchTxt_HB, cMsg.xxMdseSrchTxt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxSerNumSrchTxt_HB, cMsg.xxSerNumSrchTxt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptTxt_HB, cMsg.srchOptTxt_CF);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_HB, cMsg.rtlWhCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_RB, cMsg.rtlWhCdSrchTxt_RW);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_SB, cMsg.rtlWhCdSrchTxt_SW);
        ZYPEZDItemValueSetter.setValue(cMsg.xxFldValTxt_BC, cMsg.xxFldValTxt_HC);
        ZYPEZDItemValueSetter.setValue(cMsg.xxFromDt_HB, cMsg.xxFromDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxThruDt_HB, cMsg.xxThruDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_HB, cMsg.xxDplyCtrlFlg_H1);
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_HB, cMsg.xxChkBox_PR);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpVndRelnPk_HB, cMsg.rtrnCtrlTpVndRelnPk_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpCd_HB, cMsg.rtrnCtrlTpCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpNm_HB, cMsg.rtrnCtrlTpNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd_HB, cMsg.prntVndCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm_HB, cMsg.prntVndNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd_HB, cMsg.vndCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.vndNm_HB, cMsg.vndNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem500Txt_HB, cMsg.xxScrItem500Txt_H1);
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.whOwnrCd_HB, cMsg.whOwnrCd_H1);
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        for (int i = 0; i < cMsg.S.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SB, cMsg.S.no(i).xxChkBox_SS);
        }

        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxChkBox_LB, cMsg.L.no(i).xxChkBox_LS);
        }
    }

    /**
     * createSearchCondSmry
     * @param cMsg NLCL0250CMsg
     * @param rownum int
     * @param srchCondListMap ArrayList<String>>
     * @return Map<String, Object>
     */
    public static Map<String, Object> createSearchCondSmry(NLCL0250CMsg cMsg, int rownum, Map<String, ArrayList<String>> srchCondListMap) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("stkStsList", createStkStsList(cMsg));
        params.put("locStsList", createLocStsList(cMsg));
        params.put("mdseCdList", splitSrchTxt(cMsg.xxMdseSrchTxt_HB, true, cMsg.glblCmpyCd.getValue()));
        // START 2018/10/25 M.Naito [QC#28867,MOD]
//        params.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        params.put("rowNum", rownum);
        // QC#27696 Performance Issue.
        params.put("mrpInfoRgtnStsCdAvailable", MRP_INFO_RGTN_STS.AVAILABLE);
        params.put("mrpInfoRgtnStsCdErrorBySupersede", MRP_INFO_RGTN_STS.ERROR_BY_SUPERSEDE);

        ArrayList<String> whCdList = srchCondListMap.get("whCdList");

        if (whCdList != null && !whCdList.isEmpty()) {

            if (whCdList.size() >= 1001) {

                params.put("whCdList", splitSrchTxt(cMsg.rtlWhCdSrchTxt_RB, false, null));
                params.put("whNmList", splitSrchTxt(cMsg.rtlWhNmSrchTxt_RB, false, null));

            } else {

                params.put("whCdList", whCdList);
            }
        }

        ArrayList<String> swhCdList = srchCondListMap.get("swhCdList");

        if (swhCdList != null && !swhCdList.isEmpty()) {

            if (swhCdList.size() >= 1001) {

                params.put("swhCdList", splitSrchTxt(cMsg.rtlWhCdSrchTxt_SB, false, null));
                params.put("swhNmList", splitSrchTxt(cMsg.rtlWhNmSrchTxt_SB, false, null));

            } else {

                params.put("swhCdList", swhCdList);
            }
        }

        // START 2018/10/25 M.Naito [QC#28867,MOD]
        S21InfoLogOutput.println("### Set Parameters (SQLID: searchSmry) ###################################");
        S21InfoLogOutput.println(params.toString());
        Map<String, String> logParams = NLCL0250CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());

        params.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        return params;
    }

    /**
     * createSearchCondDtl
     * @param cMsg NLCL0250CMsg
     * @param rownum int
     * @param srchCondListMap ArrayList<String>>
     * @return Map<String, Object>
     */
    public static Map<String, Object> createSearchCondDtl(NLCL0250CMsg cMsg, int rownum, Map<String, ArrayList<String>> srchCondListMap) {

        Map<String, String> locStsMap = getAllLocStsList(cMsg.glblCmpyCd.getValue());
        ArrayList<String> locStsList = createLocStsList(cMsg);
        // START 2017/12/18 S.Katsuma [QC#22469,MOD]
//        ArrayList<BigDecimal> configIDList = splitSrchTxtForNum(cMsg.srchOptTxt_HB);
        ArrayList<String> configIDList = splitSrchTxtForNum(cMsg.srchOptTxt_HB);
        // START 2017/12/18 S.Katsuma [QC#22469,MOD]

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        // START 2018/12/06 M.Naito [QC#29249,MOD]
        params.put("shpgStsList", createShpgStsList());
        // END 2018/12/06 M.Naito [QC#29249,MOD]
        params.put("stkStsList", createStkStsList(cMsg));
        params.put("locStsList", locStsList);
        params.put("mdseCdList", splitSrchTxt(cMsg.xxMdseSrchTxt_HB, true, cMsg.glblCmpyCd.getValue()));
        params.put("serNumList", splitSrchTxt(cMsg.xxSerNumSrchTxt_HB, false, null));
        params.put("configIDList", configIDList);
        // START 2018/10/25 M.Naito [QC#28867,MOD]
//        params.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        params.put("rowNum", rownum);

        // Location Status Code
        params.put("locStsInTrnst", LOC_STS.IN_TRANSIT);
        params.put("locStsDcStk", LOC_STS.DC_STOCK);
        params.put("locStsWipComp", LOC_STS.WORK_IN_PROCESS_COMPONENT);
        params.put("locStsWipKit", LOC_STS.WORK_IN_PROCESS_KIT);
        params.put("locStsLoan", LOC_STS.TRIAL_USE);
        params.put("locStsW4I", LOC_STS.WAITING_FOR_INSTALLATION);
        params.put("locStsWHTrans", LOC_STS.IN_TRANSIT_WH);
        // QC#30045
        params.put("locStsReman", LOC_STS.WORK_IN_PROCESS_REMAN);
        params.put("locStsSubcontract", LOC_STS.WORK_IN_PROCESS_SUBCONTRACT);

        // Location Status Name
        params.put("locStsInTrnstNm", getLocStsNm(LOC_STS.IN_TRANSIT, locStsMap));
        params.put("locStsDcStkNm", getLocStsNm(LOC_STS.DC_STOCK, locStsMap));
        params.put("locStsWipCompNm", getLocStsNm(LOC_STS.WORK_IN_PROCESS_COMPONENT, locStsMap));
        params.put("locStsWipKitNm", getLocStsNm(LOC_STS.WORK_IN_PROCESS_KIT, locStsMap));
        params.put("locStsLoanNm", getLocStsNm(LOC_STS.TRIAL_USE, locStsMap));
        params.put("locStsW4INm", getLocStsNm(LOC_STS.WAITING_FOR_INSTALLATION, locStsMap));
        params.put("locStsWHTransNm", getLocStsNm(LOC_STS.IN_TRANSIT_WH, locStsMap));
        // QC#30045
        params.put("locStsRemanNm", getLocStsNm(LOC_STS.WORK_IN_PROCESS_REMAN, locStsMap));
        params.put("locStsSubcontractNm", getLocStsNm(LOC_STS.WORK_IN_PROCESS_SUBCONTRACT, locStsMap));

        // Search Target Location Status
        String locStsInTrnstFlg = chkLocSts(LOC_STS.IN_TRANSIT, locStsList, configIDList);
        String locStsDcStkFlg = chkLocSts(LOC_STS.DC_STOCK, locStsList);
        String locStsKitFlg = chkLocSts(LOC_STS.WORK_IN_PROCESS_COMPONENT, LOC_STS.WORK_IN_PROCESS_KIT, locStsList);
        String locStsLoanFlg = chkLocSts(LOC_STS.TRIAL_USE, locStsList);
        String locStsW4IFlg = chkLocSts(LOC_STS.WAITING_FOR_INSTALLATION, locStsList);
        String locStsWHTransFlg = chkLocSts(LOC_STS.IN_TRANSIT_WH, locStsList);
        // QC#30045
        String locStsRemanFlg = chkLocSts(LOC_STS.WORK_IN_PROCESS_REMAN, locStsList);
        String locStsSubconFlg = chkLocSts(LOC_STS.WORK_IN_PROCESS_SUBCONTRACT, locStsList);

        if (!Arrays.asList(locStsInTrnstFlg, locStsDcStkFlg, locStsKitFlg, locStsLoanFlg, locStsW4IFlg, locStsWHTransFlg, locStsRemanFlg, locStsSubconFlg).contains(ZYPConstant.FLG_ON_Y)) {

            return null;
        }

        params.put("locStsInTrnstFlg", locStsInTrnstFlg);
        params.put("locStsDcStkFlg", locStsDcStkFlg);
        params.put("locStsKitFlg", locStsKitFlg);
        params.put("locStsLoanFlg", locStsLoanFlg);
        params.put("locStsW4IFlg", locStsW4IFlg);
        params.put("locStsWHTransFlg", locStsWHTransFlg);
        // QC#30045
        params.put("locStsRemanFlg", locStsRemanFlg);
        params.put("locStsSubconFlg", locStsSubconFlg);

        // SCE Order Type
        params.put("sceOrdTpDG", SCE_ORD_TP.DOMESTIC_CANON_GROUP);
        params.put("sceOrdTpRS", SCE_ORD_TP.DIRECT_SALES);
        params.put("sceOrdTpDT", SCE_ORD_TP.DC_TRANSFER);
        params.put("sceOrdTpTR", SCE_ORD_TP.TECH_REQUEST);
        params.put("sceOrdTpKT", SCE_ORD_TP.KITTING);
        params.put("sceOrdTpKU", SCE_ORD_TP.UN_KITTING);
        params.put("sceOrdTpKC", SCE_ORD_TP.KITTING_CANCEL);
        // QC#30045
        params.put("sceOrdTpRL", SCE_ORD_TP.REMAN_LOCATOR_TRANSFER);
        params.put("sceOrdTpRP", SCE_ORD_TP.REPAIR_SUBCONTRACT);

        // Other Value
        params.put("shpgSts25", SHPG_STS.HARD_ALLOCATED);
        params.put("shpgSts38", SHPG_STS.SHIPPED);
        params.put("inbdOtbdCdIn", INBD_OTBD.INBOUND);
        params.put("inbdOtbdCdOt", INBD_OTBD.OUTBOUND);
        params.put("svcMachMstrStsTerm", SVC_MACH_MSTR_STS.TERMINATED);
        params.put("rtlWhLoan", "LO");
        params.put("trxLineSubNum", "001");
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        // QC#30045
        params.put("poSts05", PO_STS.CLOSED);
        params.put("poSts06", PO_STS.CANCELLED);

        // MRP Info
        // QC#27696 Performance Issue.
        params.put("mrpInfoRgtnStsCdTerminated", MRP_INFO_RGTN_STS.TERMINATED);
        params.put("mrpInfoRgtnStsCdAvailable", MRP_INFO_RGTN_STS.AVAILABLE);
        params.put("mrpInfoRgtnStsCdErrorBySupersede", MRP_INFO_RGTN_STS.ERROR_BY_SUPERSEDE);

        ArrayList<String> whCdList = srchCondListMap.get("whCdList");

        if (whCdList != null && !whCdList.isEmpty()) {

            if (whCdList.size() >= NLCL0250Constant.CODE_SEARCH_SIZE) {

                params.put("whCdList", splitSrchTxt(cMsg.rtlWhCdSrchTxt_RB, false, null));

            } else {

                params.put("whCdList", whCdList);
            }
        }

        ArrayList<String> swhCdList = srchCondListMap.get("swhCdList");

        if (swhCdList != null && !swhCdList.isEmpty()) {

            if (swhCdList.size() >= NLCL0250Constant.CODE_SEARCH_SIZE) {

                params.put("swhCdList", splitSrchTxt(cMsg.rtlWhCdSrchTxt_SB, false, null));

            } else {

                params.put("swhCdList", swhCdList);
            }
        }

        ArrayList<String> toLocCdList = srchCondListMap.get("toLocCdList");

        if (toLocCdList != null && !toLocCdList.isEmpty()) {

            if (toLocCdList.size() >= NLCL0250Constant.CODE_SEARCH_SIZE) {

                params.put("toLocCdList", splitSrchTxt(cMsg.xxFldValTxt_BC, false, null));

            } else {

                params.put("toLocCdList", toLocCdList);
            }
        }

        // START 2018/10/25 M.Naito [QC#28867,MOD]
        S21InfoLogOutput.println("### Set Parameters (SQLID: searchDtl) ###################################");
        S21InfoLogOutput.println(params.toString());
        Map<String, String> logParams = NLCL0250CommonLogic.setParamsForLog(cMsg);
        S21InfoLogOutput.println(logParams.toString());

        params.put("cMsg", cMsg);
        // END 2018/10/25 M.Naito [QC#28867,MOD]
        return params;
    }

    /**
     * createStkStsList
     * @param cMsg NLCL0250CMsg
     * @return ArrayList<String>
     */
    private static ArrayList<String> createStkStsList(NLCL0250CMsg cMsg) {

        ArrayList<String> stkStsList = new ArrayList<String>();

        for (int i = 0; i < cMsg.S.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.S.no(i).xxChkBox_SB.getValue())) {

                stkStsList.add(cMsg.S.no(i).stkStsCd_SS.getValue());
            }
        }

        return stkStsList;
    }

    // START 2018/12/06 M.Naito [QC#29249,ADD]
    /**
     * createShpgStsList
     * @return ArrayList<String>
     */
    private static ArrayList<String> createShpgStsList() {

        ArrayList<String> shpgStsList = new ArrayList<String>();
        shpgStsList.add(SHPG_STS.PICKED);
        shpgStsList.add(SHPG_STS.PACKED);
        shpgStsList.add(SHPG_STS.STAGED);
        shpgStsList.add(SHPG_STS.S_OR_O_PRINTED);
        return shpgStsList;
    }
    // END 2018/12/06 M.Naito [QC#29249,ADD]

    /**
     * createLocStsList
     * @param cMsg NLCL0250CMsg
     * @return ArrayList<String>
     */
    private static ArrayList<String> createLocStsList(NLCL0250CMsg cMsg) {

        ArrayList<String> locStsList = new ArrayList<String>();
        ArrayList<String> allLocStsList = new ArrayList<String>();

        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            allLocStsList.add(cMsg.L.no(i).locStsCd_LS.getValue());

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.L.no(i).xxChkBox_LB.getValue())) {

                // Age criteria is entered
                if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_HB) || ZYPCommonFunc.hasValue(cMsg.xxThruDt_HB)) {

                    if (LOC_STS.DC_STOCK.equals(cMsg.L.no(i).locStsCd_LS.getValue())) {

                        locStsList.add(cMsg.L.no(i).locStsCd_LS.getValue());
//                        break;
                    }

                    if (LOC_STS.TRIAL_USE.equals(cMsg.L.no(i).locStsCd_LS.getValue())) {

                        locStsList.add(cMsg.L.no(i).locStsCd_LS.getValue());
//                        break;
                    }

                    continue;
                }

                locStsList.add(cMsg.L.no(i).locStsCd_LS.getValue());
            }
        }

        if (locStsList.isEmpty()) {

            // Age criteria is entered
            if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_HB) || ZYPCommonFunc.hasValue(cMsg.xxThruDt_HB)) {

                locStsList.add(LOC_STS.DC_STOCK);

            } else {

                return allLocStsList;
            }
        }

        return locStsList;
    }

    /**
     * getAllLocStsList
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getAllLocStsList(String glblCmpyCd) {

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getAllLocStsList(glblCmpyCd);

        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            if (resultList != null && !resultList.isEmpty()) {

                Map<String, String> locStsMap = new HashMap<String, String>();

                for (Map<String, Object> resultMap : resultList) {

                    locStsMap.put((String) resultMap.get("LOC_STS_CD"), (String) resultMap.get("LOC_STS_DESC_TXT"));
                }

                return locStsMap;
            }
        }

        return null;
    }

    /**
     * getLocStsNm
     * @param locStsCd String
     * @param locStsMap Map<String, String>
     * @return String
     */
    private static String getLocStsNm(String locStsCd, Map<String, String> locStsMap) {

        if (!ZYPCommonFunc.hasValue(locStsCd) || locStsMap == null) {

            return null;
        }

        return locStsMap.get(locStsCd);
    }

    /**
     * chkLocSts
     * @param locStsCd String
     * @param locStsCdList ArrayList<String>
     * @param configPkList ArrayList<BigDecimal>
     * @return String
     */
    // START 2017/12/18 S.Katsuma [QC#22469,MOD]
//    private static String chkLocSts(String locStsCd, ArrayList<String> locStsCdList, ArrayList<BigDecimal> configPkList) {
    private static String chkLocSts(String locStsCd, ArrayList<String> locStsCdList, ArrayList<String> configPkList) {
    // START 2017/12/18 S.Katsuma [QC#22469,MOD]

        if (configPkList != null && !configPkList.isEmpty()) {

            return ZYPConstant.FLG_OFF_N;
        }

        if (locStsCdList == null || locStsCdList.isEmpty()) {

            return ZYPConstant.FLG_ON_Y;
        }

        if (locStsCdList.contains(locStsCd)) {

            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * chkLocSts
     * @param locStsCd1 String
     * @param locStsCd2 String
     * @param locStsCdList ArrayList<String>
     * @return String
     */
    private static String chkLocSts(String locStsCd1, String locStsCd2, ArrayList<String> locStsCdList) {

        if (locStsCdList == null || locStsCdList.isEmpty()) {

            return ZYPConstant.FLG_ON_Y;
        }

        if (locStsCdList.contains(locStsCd1)) {

            return ZYPConstant.FLG_ON_Y;
        }

        if (locStsCdList.contains(locStsCd2)) {

            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * chkLocSts
     * @param locStsCd String
     * @param locStsCdList ArrayList<String>
     * @return String
     */
    private static String chkLocSts(String locStsCd, ArrayList<String> locStsCdList) {

        if (locStsCdList == null || locStsCdList.isEmpty()) {

            return ZYPConstant.FLG_ON_Y;
        }

        if (locStsCdList.contains(locStsCd)) {

            return ZYPConstant.FLG_ON_Y;
        }

        return ZYPConstant.FLG_OFF_N;
    }

    /**
     * get array from search text if it has "," in text field.
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItem
     * @param isMdse boolean
     * @String glblCmpyCd String
     * @return ArrayList<String>
     */
    private static ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem, boolean isMdse, String glblCmpyCd) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(NLCL0250Constant.COMMA) != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(NLCL0250Constant.COMMA);

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals("") && srchTxtArray[i].length() > 0) {

                        String chkTxt = srchTxtArray[i].trim().replace(NLCL0250Constant.PERCENT, "");

                        if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                            splitSrchTxtList.add(srchTxtArray[i].trim());
                        }

                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                String chkTxt = srchTxtItem.getValue().trim().replace(NLCL0250Constant.PERCENT, "");

                if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                    splitSrchTxtList.add(srchTxtItem.getValue().trim());
                }
            }
        }

        // Order Take Merchandise Check
        if (isMdse && splitSrchTxtList != null && !splitSrchTxtList.isEmpty()) {

                S21SsmEZDResult ordTakeMdseResult = NLCL0250Query.getInstance().getOrdTakeMdseList(glblCmpyCd, splitSrchTxtList);

                if (ordTakeMdseResult.isCodeNormal() && !ordTakeMdseResult.isCodeNotFound()) {

                    List<ORD_TAKE_MDSETMsg> ordTakeMdseList = (List<ORD_TAKE_MDSETMsg>) ordTakeMdseResult.getResultObject();

                    if (!ordTakeMdseList.isEmpty()) {

                        for (ORD_TAKE_MDSETMsg ordTakeMdse : ordTakeMdseList) {

                            splitSrchTxtList.add(ordTakeMdse.ordTakeMdseCd.getValue().concat(NLCL0250Constant.PERCENT));
                        }
                    }
                }
        }

        if (splitSrchTxtList != null && !splitSrchTxtList.isEmpty()) {

            return splitSrchTxtList;
        }

        return null;
    }

    /**
     * get array from search text if it has "," in text field.
     * splitSrchTxtForNum
     * @param srchTxtItem EZDCStringItem
     * @return ArrayList<BigDecimal>
     */
    // START 2017/12/18 S.Katsuma [QC#22469,MOD]
//    private static ArrayList<BigDecimal> splitSrchTxtForNum(EZDCStringItem srchTxtItem) {
    private static ArrayList<String> splitSrchTxtForNum(EZDCStringItem srchTxtItem) {

        ArrayList<String> splitSrchNumList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(NLCL0250Constant.COMMA) != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(NLCL0250Constant.COMMA);

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals(NLCL0250Constant.BLANK) && srchTxtArray[i].length() > 0) {

                        splitSrchNumList.add(srchTxtArray[i].trim());
                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                splitSrchNumList.add(srchTxtItem.getValue());
            }

            if (splitSrchNumList != null && !splitSrchNumList.isEmpty()) {

                return splitSrchNumList;
            }
        }

        return null;
    }
    // START 2017/12/18 S.Katsuma [QC#22469,MOD]

    /**
     * QC#24286 Mod
     * createDtlSrchTbl
     * @param result S21SsmEZDResult
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     * @throws SQLException
     */
    public static void createDtlSrchTbl(ResultSet rs, NLCL0250CMsg cMsg, NLCL0250SMsg sMsg) throws SQLException {

        Map<String, BigDecimal> qtyChkMap = new HashMap<String, BigDecimal>();
        int indexA = 0;

        do {

            if (indexA == sMsg.A.length()) {

                break;
            }

            BigDecimal invtyAvalQty = rs.getBigDecimal("INVTY_AVAL_QTY");

            // START 05/12/2020 T.Ogura [QC#56668,ADD]
            if (isAvalQtyHideLocSts(cMsg.glblCmpyCd.getValue(), rs.getString("LOC_STS_CD"))) {
                invtyAvalQty =BigDecimal.ZERO;
            }
            // END   05/12/2020 T.Ogura [QC#56668,ADD]

            // Not DC Stock
            if (!LOC_STS.DC_STOCK.equals(rs.getString("LOC_STS_CD"))) {

                setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyAvalQty);
                indexA++;
                sMsg.A.setValidCount(indexA);
                continue;

            } else if (BigDecimal.ZERO.compareTo(invtyAvalQty) == 0) {

                setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyAvalQty);
                indexA++;
                sMsg.A.setValidCount(indexA);
                continue;
            }

            String mdseCd = rs.getString("MDSE_CD");
            String invtylocCd = rs.getString("INVTY_LOC_CD");
            String stkStsCd = rs.getString("STK_STS_CD");
            BigDecimal invtyQty = rs.getBigDecimal("INVTY_QTY");
            String qtyChkKey = ZYPCommonFunc.concatString(ZYPCommonFunc.concatString(mdseCd, ":", invtylocCd), ":", stkStsCd);

            BigDecimal rmngInvtyAvalQty = qtyChkMap.get(qtyChkKey);

            if (rmngInvtyAvalQty == null) {

                rmngInvtyAvalQty = invtyAvalQty;
            }

            if (ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"))) {

                if (ZYPCommonFunc.hasValue(rs.getString("DPLY_LINE_NUM"))) {
                    invtyQty = BigDecimal.ZERO;
                    setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyQty);
                } else {
                    invtyQty = BigDecimal.ONE;
                    setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyQty);
                }

                qtyChkMap.put(qtyChkKey, rmngInvtyAvalQty.subtract(invtyQty));
                indexA++;
                sMsg.A.setValidCount(indexA);
                continue;
            }  else if (ZYPCommonFunc.hasValue(rs.getBigDecimal("SVC_MACH_MSTR_PK"))) {

                if (ZYPCommonFunc.hasValue(rs.getString("DPLY_LINE_NUM"))) {
                    invtyQty = BigDecimal.ZERO;
                    setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyQty);
                } else {
                    invtyQty = BigDecimal.ONE;
                    setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyQty);
                }

                qtyChkMap.put(qtyChkKey, rmngInvtyAvalQty.subtract(invtyQty));
                indexA++;
                sMsg.A.setValidCount(indexA);
                continue;
            } else if (BigDecimal.ZERO.compareTo(rmngInvtyAvalQty) >= 0) {

                setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, BigDecimal.ZERO);
                indexA++;
                sMsg.A.setValidCount(indexA);

            } else if (rmngInvtyAvalQty.compareTo(invtyQty) > 0) {

                setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, invtyQty);
                qtyChkMap.put(qtyChkKey, rmngInvtyAvalQty.subtract(invtyQty));
                indexA++;
                sMsg.A.setValidCount(indexA);

            } else {

                setCommonSrchRsltToSMsg(sMsg.A.no(indexA), rs, rmngInvtyAvalQty);
                qtyChkMap.put(qtyChkKey, BigDecimal.ZERO);
                indexA++;
                sMsg.A.setValidCount(indexA);
            }
        } while (rs.next());
    }

    /**
     * isDplyScrn
     * @param cMsg NLCL0250CMsg
     * @param stkInDt String
     * @return boolean
     */
    public static boolean isDplyScrn(NLCL0250CMsg cMsg,  String stkInDt) {

        if (!ZYPCommonFunc.hasValue(stkInDt)) {

            stkInDt = cMsg.slsDt.getValue();
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_HB) && ZYPCommonFunc.hasValue(cMsg.xxThruDt_HB)) {

            if (ZYPDateUtil.compare(cMsg.xxFromDt_HB.getValue(), stkInDt) < 1 && ZYPDateUtil.compare(stkInDt, cMsg.xxThruDt_HB.getValue()) < 1) {

                return true;
            }

            return false;

        } else if (!ZYPCommonFunc.hasValue(cMsg.xxFromDt_HB) && ZYPCommonFunc.hasValue(cMsg.xxThruDt_HB)) {

            if (ZYPDateUtil.compare(stkInDt, cMsg.xxThruDt_HB.getValue()) < 1) {

                return true;
            }

            return false;

        } else if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_HB) && !ZYPCommonFunc.hasValue(cMsg.xxThruDt_HB)) {

            if (ZYPDateUtil.compare(cMsg.xxFromDt_HB.getValue(), stkInDt) < 1) {

                return true;
            }

            return false;
        }

        return true;
    }

    /**
     * getRtlWhCdList
     * @param cMsg NLCL0250CMsg
     * @param whCdStrgItem EZDCStringItem
     * @param isDnld boolean
     * @return ArrayList<String>
     */
    public static ArrayList<String> getRtlWhCdList(NLCL0250CMsg cMsg, EZDCStringItem whCdStrgItem, boolean isDnld) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rtlWhCdList", splitSrchTxt(whCdStrgItem, false, null));
        params.put("rowNum", NLCL0250Constant.CODE_SEARCH_SIZE);

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getRtlWhList(params);

        if (ssmResult.isCodeNormal()) {

            ArrayList<Map<String, String>> rtlWhMapList = (ArrayList<Map<String, String>>) ssmResult.getResultObject();

            if (rtlWhMapList != null && !rtlWhMapList.isEmpty()) {

                ArrayList<String> rtlWhCdList = new ArrayList<String>();
                String rtlWhNmTxt = "";

                for (Map<String, String> rtlWhMap : rtlWhMapList) {

                    rtlWhCdList.add(rtlWhMap.get("RTL_WH_CD"));
                    rtlWhNmTxt = setSrchNmTxt(rtlWhNmTxt, rtlWhMap.get("RTL_WH_NM"));
                }

                if (!isDnld) {

                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_RW, rtlWhNmTxt);
                }

                return rtlWhCdList;
            }
        }

        cMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NLCL0250Constant.NLZM2278E, new String[]{"Warehouse"});
        cMsg.rtlWhNmSrchTxt_RW.clear();
        return null;
    }

    /**
     * getSwhCdList
     * @param cMsg NLCL0250CMsg
     * @param swhCdStrgItem EZDCStringItem
     * @param isDnld boolean
     * @return ArrayList<String>
     */
    public static ArrayList<String> getSwhCdList(NLCL0250CMsg cMsg, EZDCStringItem swhCdStrgItem, boolean isDnld) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("swhCdList", splitSrchTxt(swhCdStrgItem, false, null));
        params.put("rowNum", NLCL0250Constant.CODE_SEARCH_SIZE);

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getSwhList(params);

        if (ssmResult.isCodeNormal()) {

            ArrayList<Map<String, String>> swhMapList = (ArrayList<Map<String, String>>) ssmResult.getResultObject();

            if (swhMapList != null && !swhMapList.isEmpty()) {

                ArrayList<String> swhCdList = new ArrayList<String>();
                String swhNmTxt = "";

                for (Map<String, String> swhMap : swhMapList) {

                    swhCdList.add(swhMap.get("RTL_SWH_CD"));
                    swhNmTxt = setSrchNmTxt(swhNmTxt, swhMap.get("RTL_SWH_NM"));
                }

                if (!isDnld) {

                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_SW, swhNmTxt);
                }

                return swhCdList;
            }
        }

        cMsg.rtlWhCdSrchTxt_SW.setErrorInfo(1, NLCL0250Constant.NLZM2278E, new String[]{"Sub Warehouse"});
        cMsg.rtlWhNmSrchTxt_SW.clear();
        return null;
    }

    /**
     * getToLocCdList
     * @param cMsg NLCL0250CMsg
     * @param shipToCdStrgItem EZDCStringItem
     * @param isDnld boolean
     * @return ArrayList<String>
     */
    public static ArrayList<String> getToLocCdList(NLCL0250CMsg cMsg, EZDCStringItem shipToCdStrgItem, boolean isDnld) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("toLocCdList", splitSrchTxt(shipToCdStrgItem, false, null));
        params.put("rowNum", NLCL0250Constant.CODE_SEARCH_SIZE);

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getShipToLocList(params);

        if (ssmResult.isCodeNormal()) {

            ArrayList<Map<String, String>> toLocMapList = (ArrayList<Map<String, String>>) ssmResult.getResultObject();

            if (toLocMapList != null && !toLocMapList.isEmpty()) {

                ArrayList<String> toLocCdList = new ArrayList<String>();
                String toLocNmTxt = "";

                for (Map<String, String> toLocMap : toLocMapList) {

                    toLocCdList.add(toLocMap.get("SHIP_TO_CUST_CD"));
                    toLocNmTxt = setSrchNmTxt(toLocNmTxt, toLocMap.get("DS_ACCT_NM"));
                }

                if (!isDnld) {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxFldValTxt_HN, toLocNmTxt);
                }

                return toLocCdList;
            }
        }

        cMsg.xxFldValTxt_HC.setErrorInfo(1, NLCL0250Constant.NLZM2278E, new String[]{"To Location"});
        cMsg.xxFldValTxt_HN.clear();
        return null;
    }

    /**
     * setSrchNmTxt
     * @param srchNmTxt String
     * @param srchNmVal String
     * @return String
     */
    private static String setSrchNmTxt(String srchNmTxt, String srchNmVal) {

        if (ZYPCommonFunc.hasValue(srchNmTxt)) {

            if (srchNmTxt.length() > 1000) {

                return srchNmTxt;
            }

            srchNmTxt = ZYPCommonFunc.concatString(srchNmTxt, NLCL0250Constant.COMMA, srchNmVal);

            if (srchNmTxt.length() > 1000) {

                return srchNmTxt.substring(0, 1000);
            }

            return srchNmTxt;
        }

        return srchNmVal;
    }

    /**
     * isChkInvtyLoc
     * @param cMsg NLCL0250CMsg
     * @return boolean
     */
    private static boolean isChkInvtyLoc(NLCL0250CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("whCdList", splitSrchTxt(cMsg.rtlWhCdSrchTxt_RB, false, null));
        params.put("swhCdList", splitSrchTxt(cMsg.rtlWhCdSrchTxt_SB, false, null));

        S21SsmEZDResult swhResult = NLCL0250Query.getInstance().getInvtyLocCnt(params);

        if (swhResult.isCodeNormal()) {

            BigDecimal count =  (BigDecimal) swhResult.getResultObject();

            if (count.compareTo(BigDecimal.ZERO) > 0) {

                return true;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCdSrchTxt_RB)) {

            cMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NLCL0250Constant.NLZM2279E, new String[]{"Warehouse", "Sub Warehouse"});
        }

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCdSrchTxt_SB)) {

            cMsg.rtlWhCdSrchTxt_SW.setErrorInfo(1, NLCL0250Constant.NLZM2279E, new String[]{"Warehouse", "Sub Warehouse"});
        }

        return false;
    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL0250Constant.BUSINESS_ID);

        if (!callNszc0330(cMsg, pMsg)) {

            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_S, pMsg.srchOptNm);
        ZYPEZDItemValueSetter.setValue(cMsg.zerothProdCtrlNm_H1, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.firstProdCtrlNm_H1, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.scdProdCtrlNm_H1, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.thirdProdCtrlNm_H1, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.frthProdCtrlNm_H1, pMsg.srchOptTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.coaProjCd_H1, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseItemTpCd_H1, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxMdseSrchTxt_H1, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxSerNumSrchTxt_H1, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptTxt_CF, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_H1, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_RW, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_SW, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxFldValTxt_HC, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_RW, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_SW, pMsg.srchOptTxt_16.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxFldValTxt_HN, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxFromDt_H1, pMsg.srchOptTxt_18.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxThruDt_H1, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptTxt_LS, pMsg.srchOptTxt_20.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptTxt_SS, pMsg.srchOptTxt_21.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_H1, pMsg.srchOptTxt_22.getValue());
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_PR, pMsg.srchOptTxt_23.getValue());
        // QC#28599 mod start
        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_24.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpVndRelnPk_H1, new BigDecimal(pMsg.srchOptTxt_24.getValue()));
        }
        // QC#28599 mod start
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpCd_H1, pMsg.srchOptTxt_25.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpNm_H1, pMsg.srchOptTxt_26.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd_H1, pMsg.srchOptTxt_27.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm_H1, pMsg.srchOptTxt_28.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd_H1, pMsg.srchOptTxt_29.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndNm_H1, pMsg.srchOptTxt_30.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem500Txt_H1, pMsg.srchOptTxt_31.getValue());
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.whOwnrCd_H1, pMsg.srchOptTxt_32.getValue());
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        // Check Stock Status
        for (int i = 0; i < cMsg.S.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptTxt_SS)) {

            String stkStsTxt = cMsg.srchOptTxt_SS.getValue();

            if (stkStsTxt.indexOf(NLCL0250Constant.COMMA) != -1) {

                String[] stkStsArray = stkStsTxt.split(NLCL0250Constant.COMMA);

                for (int i = 0; i < stkStsArray.length; i++) {

                    if (!stkStsArray[i].trim().equals(NLCL0250Constant.BLANK) && stkStsArray[i].length() > 0) {

                        for (int j = 0; j < cMsg.S.getValidCount(); j++) {

                            if (stkStsArray[i].trim().equals(cMsg.S.no(j).stkStsCd_SS.getValue())) {

                                ZYPEZDItemValueSetter.setValue(cMsg.S.no(j).xxChkBox_SS, ZYPConstant.CHKBOX_ON_Y);
                            }
                        }
                    }
                }

            } else {

                if (!stkStsTxt.trim().equals(NLCL0250Constant.BLANK)) {

                    for (int i = 0; i < cMsg.S.getValidCount(); i++) {

                        if (stkStsTxt.trim().equals(cMsg.S.no(i).stkStsCd_SS.getValue())) {

                            ZYPEZDItemValueSetter.setValue(cMsg.S.no(i).xxChkBox_SS, ZYPConstant.CHKBOX_ON_Y);
                        }
                    }
                }
            }
        }

        // Check Location Status
        for (int i = 0; i < cMsg.L.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxChkBox_LS, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptTxt_LS)) {

            String locStsTxt = cMsg.srchOptTxt_LS.getValue();

            if (locStsTxt.indexOf(NLCL0250Constant.COMMA) != -1) {

                String[] locStsArray = locStsTxt.split(NLCL0250Constant.COMMA);

                for (int i = 0; i < locStsArray.length; i++) {

                    if (!locStsArray[i].trim().equals(NLCL0250Constant.BLANK) && locStsArray[i].length() > 0) {

                        for (int j = 0; j < cMsg.L.getValidCount(); j++) {

                            if (locStsArray[i].trim().equals(cMsg.L.no(j).locStsCd_LS.getValue())) {

                                ZYPEZDItemValueSetter.setValue(cMsg.L.no(j).xxChkBox_LS, ZYPConstant.CHKBOX_ON_Y);
                            }
                        }
                    }
                }

            } else {

                if (!locStsTxt.trim().equals(NLCL0250Constant.BLANK)) {

                    for (int i = 0; i < cMsg.L.getValidCount(); i++) {

                        if (locStsTxt.trim().equals(cMsg.L.no(i).locStsCd_LS.getValue())) {

                            ZYPEZDItemValueSetter.setValue(cMsg.L.no(i).xxChkBox_LS, ZYPConstant.CHKBOX_ON_Y);
                        }
                    }
                }
            }
        }
    }

    /**
     * callNszc0330forDeleteSearch
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     * @param userId String
     */
    public static void callNszc0330forDeleteSearch(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg, String userId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL0250Constant.BUSINESS_ID);

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, userId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(NLCL0250Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLCL0250Constant.SCREEN_ID, "Delete Search") });
        }
    }

    /**
     * isExistSaveSearchName
     * @param cMsg NLCL0250CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLCL0250CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NLCL0250CMsg
     * @param sMsg NLCL0250SMsg
     * @param usrId NLCL0250SMsg
     */
    public static void callNszc0330forSaveSearch(NLCL0250CMsg cMsg, NLCL0250SMsg sMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S) || isSameSaveSearchName(cMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S);

        } else {

            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL0250Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.zerothProdCtrlNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.firstProdCtrlNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.scdProdCtrlNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.thirdProdCtrlNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.frthProdCtrlNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.coaProjCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.mdseItemTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.xxMdseSrchTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.xxSerNumSrchTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.srchOptTxt_CF.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.rtlWhCatgCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.rtlWhCdSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.rtlWhCdSrchTxt_SW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.xxFldValTxt_HC.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.rtlWhNmSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.rtlWhNmSrchTxt_SW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.xxFldValTxt_HN.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.xxFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.xxThruDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.srchOptTxt_LS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.srchOptTxt_SS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.xxDplyCtrlFlg_H1.getValue());
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.xxChkBox_PR.getValue());
        // QC#28599 mod start
        if (ZYPCommonFunc.hasValue(cMsg.rtrnCtrlTpVndRelnPk_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.rtrnCtrlTpVndRelnPk_H1.getValue().toPlainString());
        }
        // QC#28599 mod end
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.rtrnCtrlTpCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.rtrnCtrlTpNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.prntVndCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.prntVndNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, cMsg.vndCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.vndNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.xxScrItem500Txt_H1.getValue());
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, cMsg.whOwnrCd_H1.getValue());
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk);
            cMsg.setMessageInfo(NLCL0250Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLCL0250Constant.SCREEN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NLCL0250CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLCL0250CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {

                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {

                    return true;
                }

                return false;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NLCL0250CMsg
     */
    private static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLCL0250CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {

                return;
            }

            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L.no(i));
            }
        }

        return;
    }

    /**
     * callNszc0330
     * @param cMsg NLCL0250CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NLCL0250CMsg cMsg, NSZC033001PMsg pMsg) {

        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {

                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        cMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * setPrevValMap
     * @param rs ResultSet
     * @return Map<String, Object>
     * @throws SQLException
     */
    public static Map<String, Object> setPrevValMap(ResultSet rs) throws SQLException {

        Map<String, Object> prevValMap = new HashMap<String, Object>();
        prevValMap.put("mdseCd", rs.getString("MDSE_CD"));
        prevValMap.put("mdseDescShortTxt", rs.getString("MDSE_DESC_SHORT_TXT"));
        prevValMap.put("serNum", rs.getString("SER_NUM"));
        prevValMap.put("rtlWhCatgDescTxt", rs.getString("RTL_WH_CATG_DESC_TXT"));
        prevValMap.put("rtlWhCd", rs.getString("RTL_WH_CD"));
        prevValMap.put("rtlWhNm", rs.getString("RTL_WH_NM"));
        prevValMap.put("rtlSwhCd", rs.getString("RTL_SWH_CD"));
        prevValMap.put("svcConfigMstrPk", rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        prevValMap.put("stkStsDescTxt", rs.getString("STK_STS_DESC_TXT"));
        prevValMap.put("locStsDescTxt", rs.getString("LOC_STS_DESC_TXT"));
        prevValMap.put("dsAcctNm", rs.getString("SHIP_FROM_ACCT_NM"));
        prevValMap.put("shipToAcctNm", rs.getString("SHIP_TO_ACCT_NM"));
        prevValMap.put("sceOrdTpNm", rs.getString("SCE_ORD_TP_NM"));
        prevValMap.put("trxHdrNum", rs.getString("TRX_HDR_NUM"));
        prevValMap.put("dplyLineNum", rs.getString("DPLY_LINE_NUM"));
        prevValMap.put("coaProjLongDescTxt", rs.getString("COA_PROJ_LONG_DESC_TXT"));
        prevValMap.put("mdseItemTpLongDescTxt", rs.getString("MDSE_ITEM_TP_LONG_DESC_TXT"));
        prevValMap.put("zerothProdCtrlDescTxt", rs.getString("ZEROTH_PROD_CTRL_DESC_TXT"));
        prevValMap.put("firstProdCtrlDescTxt", rs.getString("FIRST_PROD_CTRL_DESC_TXT"));
        prevValMap.put("scdProdCtrlDescTxt", rs.getString("SCD_PROD_CTRL_DESC_TXT"));
        prevValMap.put("thirdProdCtrlDescTxt", rs.getString("THIRD_PROD_CTRL_DESC_TXT"));
        prevValMap.put("frthProdCtrlDescTxt", rs.getString("FRTH_PROD_CTRL_DESC_TXT"));
        prevValMap.put("mainMachFlg", rs.getString("MACH_FLG"));
        prevValMap.put("serNumFlg", rs.getString("SER_NUM_FLG"));

        return prevValMap;
    }

    /**
     * QC#24286 Mod
     * setCommonSrchRsltToSMsg
     * @param sMsgA NLCL0250_ASMsg
     * @param rs ResultSet
     * @param invtyAvalQty BigDecimal
     * @throws SQLException
     */
    private static void setCommonSrchRsltToSMsg(NLCL0250_ASMsg sMsgA, ResultSet rs, BigDecimal invtyAvalQty) throws SQLException {

        ZYPEZDItemValueSetter.setValue(sMsgA.mdseCd_A1, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgA.mdseDescShortTxt_A1, rs.getString("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.serNum_A1, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.rtlWhCatgDescTxt_A1, rs.getString("RTL_WH_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.rtlWhCd_A1, rs.getString("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgA.rtlWhNm_A1, rs.getString("RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.rtlSwhCd_A1, rs.getString("RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgA.agingCnt_A1, rs.getBigDecimal("AGING_CNT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.svcConfigMstrPk_A1, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_AS, rs.getString("STK_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_AL, rs.getString("LOC_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.dsAcctNm_A1, rs.getString("SHIP_FROM_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.shipToAcctNm_A1, rs.getString("SHIP_TO_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.sceOrdTpNm_A1, rs.getString("SCE_ORD_TP_NM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.trxHdrNum_A1, rs.getString("TRX_HDR_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.dplyLineNum_A1, rs.getString("DPLY_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A5, rs.getString("COA_PROJ_LONG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A6, rs.getString("MDSE_ITEM_TP_LONG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A0, rs.getString("ZEROTH_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A1, rs.getString("FIRST_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A2, rs.getString("SCD_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A3, rs.getString("THIRD_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.xxScrItem61Txt_A4, rs.getString("FRTH_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsgA.mainMachFlg_A1, rs.getString("MACH_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsgA.serNumFlg_A1, rs.getString("SER_NUM_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsgA.svcMachMstrPk_A1, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsgA.invtyQty_A1, rs.getBigDecimal("INVTY_QTY"));
        ZYPEZDItemValueSetter.setValue(sMsgA.invtyAvalQty_A1, invtyAvalQty);
        ZYPEZDItemValueSetter.setValue(sMsgA.locStsCd_A1, rs.getString("LOC_STS_CD"));
        ZYPEZDItemValueSetter.setValue(sMsgA.stkStsCd_A1, rs.getString("STK_STS_CD"));
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        ZYPEZDItemValueSetter.setValue(sMsgA.ropQty_A1, rs.getBigDecimal("ROP_QTY"));
        ZYPEZDItemValueSetter.setValue(sMsgA.maxInvtyQty_A1, rs.getBigDecimal("MAX_INVTY_QTY"));
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
    }

    /**
     * setCommonSrchRsltTofMsg
     * @param fMsg NLCL0250F00FMsg
     * @param rs ResultSet
     * @param invtyAvalQty BigDecimal
     * @throws SQLException
     */
    public static void setCommonSrchRsltToFMsg(NLCL0250F00FMsg fMsg, ResultSet rs, BigDecimal invtyAvalQty) throws SQLException {

        ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, rs.getString("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.serNum_A1, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCatgDescTxt_A1, rs.getString("RTL_WH_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhCd_A1, rs.getString("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm_A1, rs.getString("RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhCd_A1, rs.getString("RTL_SWH_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.ropQty_A1, rs.getBigDecimal("ROP_QTY"));
        ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_A1, rs.getBigDecimal("MAX_INVTY_QTY"));
        ZYPEZDItemValueSetter.setValue(fMsg.agingCnt_A1, rs.getBigDecimal("AGING_CNT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A1, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AS, rs.getString("STK_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_AL, rs.getString("LOC_STS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, rs.getString("SHIP_FROM_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipToAcctNm_A1, rs.getString("SHIP_TO_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.sceOrdTpNm_A1, rs.getString("SCE_ORD_TP_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.trxHdrNum_A1, rs.getString("TRX_HDR_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum_A1, rs.getString("DPLY_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A5, rs.getString("COA_PROJ_LONG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A6, rs.getString("MDSE_ITEM_TP_LONG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A0, rs.getString("ZEROTH_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A1, rs.getString("FIRST_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A2, rs.getString("SCD_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A3, rs.getString("THIRD_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt_A4, rs.getString("FRTH_PROD_CTRL_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.mainMachFlg_A1, rs.getString("MACH_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.serNumFlg_A1, rs.getString("SER_NUM_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.invtyQty_A1, rs.getBigDecimal("INVTY_QTY"));
        ZYPEZDItemValueSetter.setValue(fMsg.invtyAvalQty_A1, invtyAvalQty);
    }

    // START 2017/12/18 S.Katsuma [QC#22469,ADD]
    /**
     * getRtlWhCdList
     * @param cMsg NLCL0250CMsg
     * @param whCdStrgItem EZDCStringItem
     * @param isDnld boolean
     * @return ArrayList<String>
     */
    public static boolean getRtrnCtrlTp(NLCL0250CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("rtrnCtrlTpVndRelnPk", cMsg.rtrnCtrlTpVndRelnPk_HB.getValue());

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getRtrnCtrlTp(params);

        if (ssmResult.isCodeNormal()) {
            Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpCd_H1, (String) rs.get("RTRN_CTRL_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.rtrnCtrlTpNm_H1, (String) rs.get("RTRN_CTRL_TP_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd_H1, (String) rs.get("PRNT_VND_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm_H1, (String) rs.get("PRNT_VND_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.vndCd_H1, (String) rs.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.vndNm_H1, (String) rs.get("VND_NM"));

            String xxScrItem500Txt_H1 = null;
            if (ZYPCommonFunc.hasValue(cMsg.rtrnCtrlTpNm_H1)) {
                xxScrItem500Txt_H1 = cMsg.rtrnCtrlTpNm_H1.getValue();
            }
            if (ZYPCommonFunc.hasValue(cMsg.prntVndNm_H1)) {
                if (ZYPCommonFunc.hasValue(xxScrItem500Txt_H1)) {
                    xxScrItem500Txt_H1 = xxScrItem500Txt_H1 + "/" + cMsg.prntVndNm_H1.getValue();
                } else {
                    xxScrItem500Txt_H1 = cMsg.prntVndNm_H1.getValue();
                }
            }
            if (ZYPCommonFunc.hasValue(cMsg.vndNm_H1)) {
                if (ZYPCommonFunc.hasValue(xxScrItem500Txt_H1)) {
                    xxScrItem500Txt_H1 = xxScrItem500Txt_H1 + "/" + cMsg.vndNm_H1.getValue();
                } else {
                    xxScrItem500Txt_H1 = cMsg.vndNm_H1.getValue();
                }
            }
            if (ZYPCommonFunc.hasValue(xxScrItem500Txt_H1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem500Txt_H1, xxScrItem500Txt_H1);
            }
        } else {
            cMsg.rtrnCtrlTpVndRelnPk_H1.setErrorInfo(1, NLCL0250Constant.NLZM2278E, new String[]{"Return Control Type"});
            cMsg.rtrnCtrlTpCd_H1.clear();
            cMsg.rtrnCtrlTpNm_H1.clear();
            cMsg.prntVndCd_H1.clear();
            cMsg.prntVndNm_H1.clear();
            cMsg.vndCd_H1.clear();
            cMsg.vndNm_H1.clear();
            cMsg.xxScrItem500Txt_H1.clear();
            return false;
        }

        return true;
    }
    // START 2017/12/18 S.Katsuma [QC#22469,ADD]

    /**
     * 
     */
    public static boolean getMdseCd(NLCL0250CMsg cMsg, boolean isDnld) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdseCdList", splitSrchTxt(cMsg.xxMdseSrchTxt_H1, false, null));

        S21SsmEZDResult ssmResult = NLCL0250Query.getInstance().getMdseCd(params);

        if (ssmResult.isCodeNormal()) {

            return true;
        }

        cMsg.xxMdseSrchTxt_H1.setErrorInfo(1, NLCL0250Constant.NLZM2278E, new String[]{"Item Number"});
        //cMsg.xxMdseSrchTxt_H1.clear();
        return false;
    }

    // START 2018/03/20 S.Katsuma [QC#24715,ADD]
    public static List<Map<String, Object>> getMrpInfoForOrdTakeMdse(Map<String, Object> queryParams, String glblCmpyCd) {
        Map<String, Object> queryParams4Mrp = new HashMap<String, Object>();
        queryParams4Mrp.put("glblCmpyCd", glblCmpyCd);
        queryParams4Mrp.put("mrpInfoRgtnStsCdTerminated", MRP_INFO_RGTN_STS.TERMINATED);
        queryParams4Mrp.put("whCdList", queryParams.get("whCdList"));
        queryParams4Mrp.put("swhCdList", queryParams.get("swhCdList"));

        S21SsmEZDResult res4Mrp = NLCL0250Query.getInstance().getMrpInfoForOrdTakeMdse(queryParams4Mrp);
        if (res4Mrp.isCodeNormal()) {
            List<Map<String, Object>> mrpInfoMap = (List<Map<String, Object>>) res4Mrp.getResultObject();
            if (!mrpInfoMap.isEmpty()) {
                return mrpInfoMap;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Map<Map<String, Object>, Integer> setSearchMapForMrpInfo(List<Map<String, Object>> map) {
        Map<Map<String, Object>, Integer> searchMap = new HashMap<Map<String, Object>, Integer>();

        if (map != null && !map.isEmpty()) {
            for (int i = 0; i < map.size(); i++) {
                final Map<String, Object> mrpInfo = map.get(i);

                searchMap.put(new HashMap<String, Object>() {
                    {
                       put("MDSE_CD", mrpInfo.get("MDSE_CD"));
                       put("RTL_WH_NM", mrpInfo.get("RTL_WH_NM"));
                       put("RTL_SWH_CD", mrpInfo.get("RTL_SWH_CD"));
                    }
                }, i);
            }
        }

        return searchMap;
    }

    private static boolean isSetMrpInfo(String mdseCd, BigDecimal ropQty, BigDecimal maxInvtyQty) {
        if (mdseCd.length() > 8
                && !ZYPCommonFunc.hasValue(ropQty)
                && !ZYPCommonFunc.hasValue(maxInvtyQty)) {
            return true;
        }

        return false;
    }

    public static void setMrpInfoForOrdTakeMdse(NLCL0250SMsg sMsg, Map<String, Object> queryParams, String glblCmpyCd) {
        List<Map<String, Object>> mrpInfoMap = getMrpInfoForOrdTakeMdse(queryParams, glblCmpyCd);

        if (mrpInfoMap != null && !mrpInfoMap.isEmpty()) {
            Map<Map<String, Object>, Integer> searchMap = setSearchMapForMrpInfo(mrpInfoMap);

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (isSetMrpInfo(sMsg.A.no(i).mdseCd_A1.getValue(), sMsg.A.no(i).ropQty_A1.getValue(), sMsg.A.no(i).maxInvtyQty_A1.getValue())) {
                    final String ordTakeMdseCd = sMsg.A.no(i).mdseCd_A1.getValue().substring(0, 8);
                    final String rtlWhNm = sMsg.A.no(i).rtlWhNm_A1.getValue();
                    final String rtlSwhCd = sMsg.A.no(i).rtlSwhCd_A1.getValue();
                    Map<String, Object> targetMap = new HashMap<String, Object>() {
                        {
                            put("MDSE_CD", ordTakeMdseCd);
                            put("RTL_WH_NM", rtlWhNm);
                            put("RTL_SWH_CD", rtlSwhCd);
                        }
                    };

                    if (searchMap.containsKey(targetMap)) {
                        int idx = searchMap.get(targetMap);
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ropQty_A1, new BigDecimal(mrpInfoMap.get(idx).get("ROP_QTY").toString()));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).maxInvtyQty_A1, new BigDecimal(mrpInfoMap.get(idx).get("MAX_INVTY_QTY").toString()));
                    }
                }
            }
        }
    }

    /**
     * setMrpInfoForOrdTakeMdse
     * @param fMsg
     * @param rs
     * @param mrpInfoMap
     * @param searchMap
     * @throws SQLException
     */
    public static void setMrpInfoForOrdTakeMdse(NLCL0250F00FMsg fMsg, ResultSet rs, List<Map<String, Object>> mrpInfoMap, Map<Map<String, Object>, Integer> searchMap) throws SQLException {
        final String mdseCd = rs.getString("MDSE_CD");
        final String rtlWhNm = rs.getString("RTL_WH_NM");
        final String rtlSwhCd = rs.getString("RTL_SWH_CD");
        BigDecimal ropQty = rs.getBigDecimal("ROP_QTY");
        BigDecimal maxInvtyQty = rs.getBigDecimal("MAX_INVTY_QTY");

        if (searchMap != null && !searchMap.isEmpty()) {
            if (isSetMrpInfo(mdseCd, ropQty, maxInvtyQty)) {
                final String ordTakeMdseCd = mdseCd.substring(0, 8);
                Map<String, Object> targetMap = new HashMap<String, Object>() {
                    {
                        put("MDSE_CD", ordTakeMdseCd);
                        put("RTL_WH_NM", rtlWhNm);
                        put("RTL_SWH_CD", rtlSwhCd);
                    }
                };

                if (searchMap.containsKey(targetMap)) {
                    int idx = searchMap.get(targetMap);
                    ZYPEZDItemValueSetter.setValue(fMsg.ropQty_A1, new BigDecimal(mrpInfoMap.get(idx).get("ROP_QTY").toString()));
                    ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_A1, new BigDecimal(mrpInfoMap.get(idx).get("MAX_INVTY_QTY").toString()));
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.ropQty_A1, ropQty);
                    ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_A1, maxInvtyQty);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.ropQty_A1, ropQty);
                ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_A1, maxInvtyQty);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(fMsg.ropQty_A1, ropQty);
            ZYPEZDItemValueSetter.setValue(fMsg.maxInvtyQty_A1, maxInvtyQty);
        }
    }
    // END 2018/03/20 S.Katsuma [QC#24715,ADD]

    // START 2018/10/25 M.Naito [QC#28867,ADD]
    public static Map<String, String> setParamsForLog(NLCL0250CMsg cMsg) {
        Map<String, String> setParams = new HashMap<String, String>();
        setMap(setParams, "xxFromDt_HB", cMsg.xxFromDt_HB.getValue());
        setMap(setParams, "xxThruDt_HB", cMsg.xxThruDt_HB.getValue());
        setMap(setParams, "zerothProdCtrlNm_HB", cMsg.zerothProdCtrlNm_HB.getValue());
        setMap(setParams, "firstProdCtrlNm_HB", cMsg.firstProdCtrlNm_HB.getValue());
        setMap(setParams, "scdProdCtrlNm_HB", cMsg.scdProdCtrlNm_HB.getValue());
        setMap(setParams, "thirdProdCtrlNm_HB", cMsg.thirdProdCtrlNm_HB.getValue());
        setMap(setParams, "frthProdCtrlNm_HB", cMsg.frthProdCtrlNm_HB.getValue());
        setMap(setParams, "coaProjCd_HB", cMsg.coaProjCd_HB.getValue());
        setMap(setParams, "mdseItemTpCd_HB", cMsg.mdseItemTpCd_HB.getValue());
        setMap(setParams, "rtlWhCatgCd_HB", cMsg.rtlWhCatgCd_HB.getValue());
        setMap(setParams, "xxChkBox_HB", cMsg.xxChkBox_HB.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.rtrnCtrlTpVndRelnPk_HB.getValue())) {
            setMap(setParams, "rtrnCtrlTpVndRelnPk_HB", cMsg.rtrnCtrlTpVndRelnPk_HB.getValue().toString());
        }
        setMap(setParams, "mdseItemTpCd_HB", cMsg.mdseItemTpCd_HB.getValue());
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        setMap(setParams, "whOwnrCd_HB", cMsg.whOwnrCd_HB.getValue());
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        return setParams;
    }

    public static Map<String, String> setMap(Map<String, String> map, String key, String value) {
        if (ZYPCommonFunc.hasValue(value)) {
            map.put(key, value);
        }
        return map;
    }
    // END 2018/10/25 M.Naito [QC#28867,ADD]

    // START 05/12/2020 T.Ogura [QC#56668,ADD]
    public static boolean isAvalQtyHideLocSts(String glblCmpyCd, String LocStsCd) {
        boolean hideFlg = false;
        List<String> avalQtyHideLocStsList = new ArrayList<String>();
        String avalQtyHideLocStsVal = ZYPCodeDataUtil.getVarCharConstValue("NLCL0250_AVAL_QTY_HIDE_LOC_STS", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalQtyHideLocStsVal)) {
            avalQtyHideLocStsList = Arrays.asList(avalQtyHideLocStsVal.split(","));
        }
        if (avalQtyHideLocStsList.contains(LocStsCd)) {
            hideFlg = true;
        }
        return hideFlg;
    }
    // END   05/12/2020 T.Ogura [QC#56668,ADD]
}
