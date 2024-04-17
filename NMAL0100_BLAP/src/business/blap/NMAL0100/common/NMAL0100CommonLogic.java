package business.blap.NMAL0100.common;

import static business.blap.NMAL0100.constant.NMAL0100Constant.BUSINESS_ID;
import static business.blap.NMAL0100.constant.NMAL0100Constant.NZZM0000E;
import static business.blap.NMAL0100.constant.NMAL0100Constant.NZZM0001W;
import static business.blap.NMAL0100.constant.NMAL0100Constant.SCREEN_ID;
import static business.blap.NMAL0100.constant.NMAL0100Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL0100.NMAL0100CMsg;
import business.blap.NMAL0100.NMAL0100Query;
import business.blap.NMAL0100.NMAL0100SMsg;
import business.blap.NMAL0100.NMAL0100_ASMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.MKT_MDLTMsg;
import business.db.MKT_MDL_SEGTMsg;
import business.file.NMAL0100F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/25/2015   SRAA            K.Aratani       Create          
 * 2015/06/24   Fujitsu         M.Yamada        Update          0601
 * 10/02/2015   SRAA            K.Aratani       Update
 * 02/18/2016   SRAA            Y.Chen          Update          QC#2443
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 *</pre>
 */
public class NMAL0100CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    public static void setInitialItemStsPulldown(NMAL0100CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0100Query.getInstance().getItemStatusList(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("MDSE_ITEM_STS_CD") != null) {
                    bizMsg.mdseItemStsCd_L1.no(i).setValue((String) map.get("MDSE_ITEM_STS_CD"));
                    bizMsg.mdseItemStsNm_L1.no(i).setValue((String) map.get("MDSE_ITEM_STS_NM"));
                    i++;
                }
            }
        }
    }
    
    public static void setInitialPlanningGroupPulldown(NMAL0100CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0100Query.getInstance().getPlanningGroupList(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (map != null && map.get("PRCH_GRP_CD") != null) {
                    bizMsg.prchGrpCd_L1.no(i).setValue((String) map.get("PRCH_GRP_CD"));
                    bizMsg.prchGrpNm_L1.no(i).setValue((String) map.get("PRCH_GRP_NM"));
                    i++;
                }
            }
        }
    }

    public static void setProductLevelName(NMAL0100CMsg bizMsg, String globalCompanyCode) {
        S21SsmEZDResult result = NMAL0100Query.getInstance().getProductLevelName(bizMsg, globalCompanyCode);
        if (result.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            for (Map<String, String> map : list) {
                if (map != null && map.get("MDSE_STRU_ELMNT_TP_CD") != null) {
                    //PLG
                    if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L1.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LINE.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L2.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL2
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L3.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL3
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L4.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                        //PL4
                    } else if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4.equals((String) map.get("MDSE_STRU_ELMNT_TP_CD"))) {
                        bizMsg.mdseStruElmntTpNm_L5.setValue((String) map.get("MDSE_STRU_ELMNT_TP_NM"));
                    }
                }
            }
        }
    }

    private static boolean isSameSaveSearchName(NMAL0100CMsg cMsg) {
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

    public static void callNszc0330forDeleteSearch(NMAL0100CMsg bizMsg, NMAL0100SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Delete Search") });
        }
    }

    public static boolean isExistSaveSearchName(NMAL0100CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                        && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void callNszc0330forSaveSearch(NMAL0100CMsg bizMsg, NMAL0100SMsg glblMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) //
                || isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, bizMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.mdseItemMnfCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.mnfItemCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.upcCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.mdseItemStsCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.mdseDescLongTxt_H1);

        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemActvDt_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.mdseItemActvDt_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.mdseItemActvDt_H2)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.mdseItemActvDt_H2.getValue());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.mdseCratTmplNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.mdseItemTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.mdseItemClsTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.coaMdseTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.coaProdCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.coaProdNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.prchGrpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.thirdPtyItemFlg_HY);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.thirdPtyItemFlg_HN);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.mdsePrcGrpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.mktMdlCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.mktMdlNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.mktMdlSegCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.mktMdlSegNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.zerothProdCtrlCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.zerothProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.firstProdCtrlCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.firstProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, bizMsg.scdProdCtrlCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, bizMsg.scdProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, bizMsg.thirdProdCtrlCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, bizMsg.thirdProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, bizMsg.frthProdCtrlCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, bizMsg.frthProdCtrlNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, bizMsg.slsMatGrpDescTxt_01);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_35, bizMsg.slsMatGrpDescTxt_02);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_36, bizMsg.slsMatGrpDescTxt_03);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_37, bizMsg.dsCmsnGrpDescTxt_H1);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo(ZZZM9003I //
                    , new String[] {converter.convLabel2i18nLabel(SCREEN_ID, "Save Search") });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NMAL0100CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL0100CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    private static boolean callNszc0330(NMAL0100CMsg bizMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param bizMsg NMAL0100CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL0100CMsg bizMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NMAL0100Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.srchOptPk_L.clear();
            bizMsg.srchOptNm_L.clear();
            return;
        }

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < bizMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            bizMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            bizMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }

    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NMAL0100CMsg
     * @param sMsg NMAL0100SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL0100CMsg bizMsg, NMAL0100SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H1, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemMnfCd_H1, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.mnfItemCd_H1, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.upcCd_H1, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemStsCd_H1, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescLongTxt_H1, pMsg.srchOptTxt_07);

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_08.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemActvDt_H1, pMsg.srchOptTxt_08.getValue());
        } else {
            bizMsg.mdseItemActvDt_H1.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_09.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemActvDt_H2, pMsg.srchOptTxt_09.getValue());
        } else {
            bizMsg.mdseItemActvDt_H2.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCratTmplNm_H1, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemTpCd_H1, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseItemClsTpCd_H1, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_H1, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(bizMsg.prchGrpCd_H1, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HY, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdPtyItemFlg_HN, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdsePrcGrpCd_H1, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlCd_H1, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlNm_H1, pMsg.srchOptTxt_21);
        ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegCd_H1, pMsg.srchOptTxt_22);
        ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegNm_H1, pMsg.srchOptTxt_23);
        ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlCd_H1, pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(bizMsg.zerothProdCtrlNm_H1, pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlCd_H1, pMsg.srchOptTxt_26);
        ZYPEZDItemValueSetter.setValue(bizMsg.firstProdCtrlNm_H1, pMsg.srchOptTxt_27);
        ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlCd_H1, pMsg.srchOptTxt_28);
        ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, pMsg.srchOptTxt_29);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlCd_H1, pMsg.srchOptTxt_30);
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdProdCtrlNm_H1, pMsg.srchOptTxt_31);
        ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlCd_H1, pMsg.srchOptTxt_32);
        ZYPEZDItemValueSetter.setValue(bizMsg.frthProdCtrlNm_H1, pMsg.srchOptTxt_33);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_01, pMsg.srchOptTxt_34);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_02, pMsg.srchOptTxt_35);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsMatGrpDescTxt_03, pMsg.srchOptTxt_36);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCmsnGrpDescTxt_H1, pMsg.srchOptTxt_37);

    }

    public static void writeCsvFile(NMAL0100CMsg bizMsg, ResultSet rs, int rownum) throws SQLException {
        if (!rs.next()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        NMAL0100F00FMsg fMsg = new NMAL0100F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        // QC#2443
        fMsg.addExclusionItem("xxRadioBtn");

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        //write contents
        do {
            if (rs.getRow() > rownum) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mnfItemCd, rs.getString("MNF_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseItemClsTpNm, rs.getString("MDSE_ITEM_CLS_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseItemTpNm, rs.getString("MDSE_ITEM_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem54Txt, rs.getString("COA_MDSE_TP_CD") + ":" + rs.getString("COA_PROJ_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem61Txt, rs.getString("COA_PROD_CD") + ":" + rs.getString("COA_PROD_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseItemStsNm, rs.getString("MDSE_ITEM_STS_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mktMdlNm, rs.getString("MKT_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mktMdlSegNm, rs.getString("MKT_MDL_SEG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.zerothProdCtrlNm, rs.getString("ZEROTH_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.firstProdCtrlNm, rs.getString("FIRST_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.scdProdCtrlNm, rs.getString("SCD_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.thirdProdCtrlNm, rs.getString("THIRD_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.frthProdCtrlNm, rs.getString("FRTH_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.slsMatGrpDescTxt_01, rs.getString("SLS_MAT_GRP_DESC_TXT_01"));
            ZYPEZDItemValueSetter.setValue(fMsg.slsMatGrpDescTxt_02, rs.getString("SLS_MAT_GRP_DESC_TXT_02"));
            ZYPEZDItemValueSetter.setValue(fMsg.slsMatGrpDescTxt_03, rs.getString("SLS_MAT_GRP_DESC_TXT_03"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCmsnGrpDescTxt, rs.getString("DS_CMSN_GRP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseItemMnfNm, rs.getString("MDSE_ITEM_MNF_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.upcCd, rs.getString("UPC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AD, getEditDt(rs.getString("MDSE_ITEM_ACTV_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.prchGrpNm, rs.getString("PRCH_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescLongTxt, rs.getString("MDSE_DESC_LONG_TXT"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private static String getEditDt(String yyyymmdd) {
        if (!ZYPCommonFunc.hasValue(yyyymmdd)) {
            return "";
        }
        return ZYPDateUtil.formatEzd8ToDisp(yyyymmdd);
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NMAL0100F00FMsg fMsg, NMAL0100CMsg bizMsg) {

        final String[] csvHeader = new String[] {
                // QC#2443
                  ""                    //xxRadioBtn
                , "Item Number"         //mdseCd
                , "Item Description"    //mdseNm
                , "Manufacturer Item #" //mnfItemCd
                , "Item Type"           //mdseItemTpNm
                , "Item Class"          //mdseItemClsTpNm
                , "Merchandise Type"    //xxScrItem50Txt
                , "Product Code"        //xxScrItem61Txt
                , "Status"              //mdseItemStsCd
                , "Marketing Model"     //mktMdlNm
                , "Marketing Segment"   //mktMdlSegNm
                , "Product Level1"      //zerothProdCtrlNm
                , "Product Level2"      //firstProdCtrlNm
                , "Product Level3"      //scdProdCtrlNm
                , "Product Level4"      //thirdProdCtrlNm
                , "Product Level5"      //frthProdCtrlNm
                , "Material Group1"     //slsMatGrpDescTxt
                , "Material Group2"     //slsMatGrpDescTxt
                , "Material Group3"     //slsMatGrpDescTxt
                , "Commissions Group"   //dsCmsnGrpDescTxt
                , "Manufacturer"        //mdseItemMnfNm
                , "UPC Code"            //upcCd
                , "Implementation Date" //xxDtTxt
                , "Planning Group"      //prchGrpNm
                , "Long Description"    //mdseFmlNm
                // QC#2443
                // , ""                    //mdseCratTmplNm *** Need one extra field for Preferred View bug. ***
        };

        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
    }

    public static void setSearchResultToGlblMsg(S21SsmEZDResult ssmResult, NMAL0100SMsg glblMsg) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        for (int i = 0; i < resultList.size() && i < glblMsg.A.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            NMAL0100_ASMsg glblLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseCd_A1, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseDescShortTxt_A1, (String) resultMap.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mnfItemCd_A1, (String) resultMap.get("MNF_ITEM_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemClsTpCd_A1, (String) resultMap.get("MDSE_ITEM_CLS_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemClsTpNm_A1, (String) resultMap.get("MDSE_ITEM_CLS_TP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemTpCd_A1, (String) resultMap.get("MDSE_ITEM_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemTpNm_A1, (String) resultMap.get("MDSE_ITEM_TP_NM"));
            if (resultMap.get("COA_MDSE_TP_CD") != null) {
	            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A1, (String) resultMap.get("COA_MDSE_TP_CD") + ":" + resultMap.get("COA_PROJ_DESC_TXT"));
            } else {
	            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A1, "");
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaMdseTpCd_A1, (String) resultMap.get("COA_MDSE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaProjDescTxt_A1, (String) resultMap.get("COA_PROJ_DESC_TXT"));
            if (resultMap.get("COA_PROD_CD") != null) {
	            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem61Txt_A1, (String) resultMap.get("COA_PROD_CD") + ":" + resultMap.get("COA_PROD_NM"));
            } else {
	            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem61Txt_A1, "");
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaProdCd_A1, (String) resultMap.get("COA_PROD_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.coaProdNm_A1, (String) resultMap.get("COA_PROD_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemStsCd_A1, (String) resultMap.get("MDSE_ITEM_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemStsNm_A1, (String) resultMap.get("MDSE_ITEM_STS_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlCd_A1, (String) resultMap.get("MKT_MDL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlNm_A1, (String) resultMap.get("MKT_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlSegCd_A1, (String) resultMap.get("MKT_MDL_SEG_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mktMdlSegNm_A1, (String) resultMap.get("MKT_MDL_SEG_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.zerothProdCtrlCd_A1, (String) resultMap.get("ZEROTH_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.zerothProdCtrlNm_A1, (String) resultMap.get("ZEROTH_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.firstProdCtrlCd_A1, (String) resultMap.get("FIRST_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.firstProdCtrlNm_A1, (String) resultMap.get("FIRST_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.scdProdCtrlCd_A1, (String) resultMap.get("SCD_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.scdProdCtrlNm_A1, (String) resultMap.get("SCD_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.thirdProdCtrlCd_A1, (String) resultMap.get("THIRD_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.thirdProdCtrlNm_A1, (String) resultMap.get("THIRD_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.frthProdCtrlCd_A1, (String) resultMap.get("FRTH_PROD_CTRL_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.frthProdCtrlNm_A1, (String) resultMap.get("FRTH_PROD_CTRL_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemMnfCd_A1, (String) resultMap.get("MDSE_ITEM_MNF_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemMnfNm_A1, (String) resultMap.get("MDSE_ITEM_MNF_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.upcCd_A1, (String) resultMap.get("UPC_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseItemActvDt_A1, (String) resultMap.get("MDSE_ITEM_ACTV_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.prchGrpCd_A1, (String) resultMap.get("PRCH_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.prchGrpNm_A1, (String) resultMap.get("PRCH_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdsePrcGrpCd_A1, (String) resultMap.get("MDSE_PRC_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdsePrcGrpNm_A1, (String) resultMap.get("MDSE_PRC_GRP_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseDescLongTxt_A1, (String) resultMap.get("MDSE_DESC_LONG_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseCratTmplNm_A1, (String) resultMap.get("MDSE_CRAT_TMPL_NM"));

            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpCd_A1, (String) resultMap.get("SLS_MAT_GRP_CD_01"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpDescTxt_A1, (String) resultMap.get("SLS_MAT_GRP_DESC_TXT_01"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpCd_A2, (String) resultMap.get("SLS_MAT_GRP_CD_02"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpDescTxt_A2, (String) resultMap.get("SLS_MAT_GRP_DESC_TXT_02"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpCd_A3, (String) resultMap.get("SLS_MAT_GRP_CD_03"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.slsMatGrpDescTxt_A3, (String) resultMap.get("SLS_MAT_GRP_DESC_TXT_03"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.dsCmsnGrpCd_A1, (String) resultMap.get("DS_CMSN_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.dsCmsnGrpDescTxt_A1, (String) resultMap.get("DS_CMSN_GRP_DESC_TXT"));

            glblMsg.A.setValidCount(i + 1);
        }

    }

    public static void preSearchForSetName(NMAL0100CMsg bizMsg, String glblCmpyCd) {
        // MERCHANDISE TYPE
        if (ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
        	COA_PROJTMsg coaProjMsg = new COA_PROJTMsg();
            ZYPEZDItemValueSetter.setValue(coaProjMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProjMsg.coaProjCd, bizMsg.coaMdseTpCd_H1);
            coaProjMsg = (COA_PROJTMsg) EZDTBLAccessor.findByKey(coaProjMsg);
            if (coaProjMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, coaProjMsg.coaProjDescTxt);
            } else {
                bizMsg.coaProjDescTxt_H1.clear();
            }
        } else {
            bizMsg.coaProjDescTxt_H1.clear();
        }
        // PRODUCT NAME
        if (ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
            COA_PRODTMsg coaProdMsg = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProdMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProdMsg.coaProdCd, bizMsg.coaProdCd_H1);
            coaProdMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProdMsg);
            if (coaProdMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaProdNm_H1, coaProdMsg.coaProdNm);
            } else {
                bizMsg.coaProdNm_H1.clear();
            }
        } else {
            bizMsg.coaProdNm_H1.clear();
        }

        // MARKETING MODEL NAME
        if (ZYPCommonFunc.hasValue(bizMsg.mktMdlCd_H1)) {
            MKT_MDLTMsg mktMdlTMsg = new MKT_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(mktMdlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mktMdlTMsg.mktMdlCd, bizMsg.mktMdlCd_H1);
            mktMdlTMsg = (MKT_MDLTMsg) EZDTBLAccessor.findByKey(mktMdlTMsg);
            if (mktMdlTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlNm_H1, mktMdlTMsg.mktMdlNm);
            } else {
                bizMsg.mktMdlNm_H1.clear();
            }
        } else {
            bizMsg.mktMdlNm_H1.clear();
        }

        // MARKETING SEGMENT NAME
        if (ZYPCommonFunc.hasValue(bizMsg.mktMdlSegCd_H1)) {
            MKT_MDL_SEGTMsg mktMdlSegTMsg = new MKT_MDL_SEGTMsg();
            ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mktMdlSegTMsg.mktMdlSegCd, bizMsg.mktMdlSegCd_H1);
            mktMdlSegTMsg = (MKT_MDL_SEGTMsg) EZDTBLAccessor.findByKey(mktMdlSegTMsg);
            if (mktMdlSegTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mktMdlSegNm_H1, mktMdlSegTMsg.mktMdlSegNm);
            } else {
                bizMsg.mktMdlSegNm_H1.clear();
            }
        } else {
            bizMsg.mktMdlSegNm_H1.clear();
        }
    }

    public static void createSsmPrm(NMAL0100CMsg cMsg, String glblCmpyCd, int rownum, Map<String, Object> ssmParam) {
        ssmParam.put("rowNum", rownum);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", cMsg.mdseCd_H1.getValue()); //ITEM NUMBER
        ssmParam.put("mdseDescShortTxt", cMsg.mdseDescShortTxt_H1.getValue()); //ITEM DESCRIPTION
        // 2019/10/18 QC#53815 Mod Start
        // ssmParam.put("mdseItemMnfCd", cMsg.mdseItemMnfCd_H1.getValue()); //MANUFACTURER
        ssmParam.put("mdseItemMnfNm", cMsg.mdseItemMnfNm_H1.getValue()); //MANUFACTURER
        // 2019/10/18 QC#53815 Mod End
        ssmParam.put("mnfItemCd", cMsg.mnfItemCd_H1.getValue()); //MANUFACTURER ITEM #
        ssmParam.put("upcCd", cMsg.upcCd_H1.getValue()); //UPC CODE
        ssmParam.put("mdseItemStsCd", cMsg.mdseItemStsCd_H1.getValue()); //STATUS
        ssmParam.put("mdseDescLongTxt", cMsg.mdseDescLongTxt_H1.getValue()); //LONG DESCRIPTION
        ssmParam.put("mdseItemActvDtFrom", cMsg.mdseItemActvDt_H1.getValue()); //IMPLEMENTATION DATE
        ssmParam.put("mdseItemActvDtTo", cMsg.mdseItemActvDt_H2.getValue()); //IMPLEMENTATION DATE
        ssmParam.put("mdseCratTmplNm", cMsg.mdseCratTmplNm_H1.getValue()); //CREATED FROM TEMPLATE
        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_H1.getValue()); //ITEM TYPE
        ssmParam.put("mdseItemClsTpCd", cMsg.mdseItemClsTpCd_H1.getValue()); //ITEM CLASSIFICATION
        ssmParam.put("coaMdseTpCd", cMsg.coaMdseTpCd_H1.getValue()); //MERCHANDISE TYPE
        ssmParam.put("coaProdCd", cMsg.coaProdCd_H1.getValue()); //PRODUCT CODE
        ssmParam.put("prchGrpCd", cMsg.prchGrpCd_H1.getValue()); //PLANNING GROUP
        ssmParam.put("mdsePrcGrpCd", cMsg.mdsePrcGrpCd_H1.getValue()); //PRICING GROUP
        ssmParam.put("mktMdlCd", cMsg.mktMdlCd_H1.getValue()); //MARKETING MODEL
        ssmParam.put("mktMdlSegCd", cMsg.mktMdlSegCd_H1.getValue()); //MARKETING SEGMENT
        ssmParam.put("zerothProdCtrlNm", cMsg.zerothProdCtrlNm_H1.getValue()); //PRODUCT LEVEL 1
        ssmParam.put("firstProdCtrlNm", cMsg.firstProdCtrlNm_H1.getValue()); //PRODUCT LEVEL 2
        ssmParam.put("scdProdCtrlNm", cMsg.scdProdCtrlNm_H1.getValue()); //PRODUCT LEVEL 3
        ssmParam.put("thirdProdCtrlNm", cMsg.thirdProdCtrlNm_H1.getValue()); //PRODUCT LEVEL 4
        ssmParam.put("frthProdCtrlNm", cMsg.frthProdCtrlNm_H1.getValue()); //PRODUCT LEVEL 5
        ssmParam.put("slsMatGrpDescTxt_01", cMsg.slsMatGrpDescTxt_01.getValue()); //Material Group 1
        ssmParam.put("slsMatGrpDescTxt_02", cMsg.slsMatGrpDescTxt_02.getValue()); //Material Group 2
        ssmParam.put("slsMatGrpDescTxt_03", cMsg.slsMatGrpDescTxt_03.getValue()); //Material Group 3
        ssmParam.put("dsCmsnGrpDescTxt", cMsg.dsCmsnGrpDescTxt_H1.getValue()); //Commision Group

        ssmParam.put("thirdPtyItemFlg_Y", cMsg.thirdPtyItemFlg_HY.getValue());
        ssmParam.put("thirdPtyItemFlg_N", cMsg.thirdPtyItemFlg_HN.getValue());
        
    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> getThirdProdHrch(NMAL0100CMsg bizMsg, String globalCompanyCode) {
		S21SsmEZDResult result = NMAL0100Query.getInstance().getThirdProdHrch(bizMsg, globalCompanyCode);
		if (result.isCodeNormal()) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
			if (list != null && list.size() > 0) {
				for(Map<String, Object> map : list) {
					if (map != null && map.get("THIRD_PROD_CTRL_CD") != null) {
						return map;
					}
				}
			}
		}
		return null;
	}
}
