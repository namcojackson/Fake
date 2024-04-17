/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2500.common;

import static business.blap.NMAL2500.constant.NMAL2500Constant.BIZ_ID;
import static business.blap.NMAL2500.constant.NMAL2500Constant.SCRN_ID_00;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2500.NMAL2500CMsg;
import business.blap.NMAL2500.NMAL2500Query;
import business.blap.NMAL2500.NMAL2500SMsg;
import business.blap.NMAL2500.constant.NMAL2500Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NYX.NYXC880002.NYXC880002Query;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NMAL2500 Org Resource Search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC16481
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760     
 *</pre>
 */
public class NMAL2500CommonLogic {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * @param cMsg NMAL2500CMsg
     * @return boolean
     */
    public static boolean hasResourceSearchResult(NMAL2500CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.xxPsnNm_H3) || ZYPCommonFunc.hasValue(cMsg.psnCd_H3)
                || ZYPCommonFunc.hasValue(cMsg.jobTtlCd_H3) || ZYPCommonFunc.hasValue(cMsg.hrTtlNm_H3)
                || ZYPCommonFunc.hasValue(cMsg.psnNum_H3) || ZYPCommonFunc.hasValue(cMsg.orgFuncRoleTpCd_P3)
                || ZYPCommonFunc.hasValue(cMsg.orgNm_H3) || ZYPCommonFunc.hasValue(cMsg.effFromDt_FR) || ZYPCommonFunc.hasValue(cMsg.effThruDt_FR)) { // MOD S21_NA QC#16481
            return true;
        }
        return false;
    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * @param cd EZDBBigDecimalItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCBigDecimalItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (BigDecimal) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * master check
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @return boolean
     */
    public static boolean checkInputForSubmit(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        getAdditionalOrg(cMsg, sMsg);
        getActiveParentOrganization(cMsg, sMsg);

        S21SsmEZDResult ssmResult = NMAL2500Query.getInstance().getActiveChildOrganization(cMsg);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > 0) {
                cMsg.setMessageInfo(NMAL2500Constant.NMAM8245E);
                return false;
            }
        }

        ssmResult = NMAL2500Query.getInstance().getActiveResource(cMsg);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > 0) {
                cMsg.setMessageInfo(NMAL2500Constant.NMAM8246E);
                return false;
            }
        }

        if (cMsg.effThruDt_H4.getValue().compareTo(cMsg.effFromDt_G1.getValue()) < 0) {
            cMsg.effThruDt_H4.setErrorInfo(1, NMAL2500Constant.NMAM0044E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
        }

        return true;

    }

    /**
     * getAdditionalOrg
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     */
    public static void getAdditionalOrg(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2500Query.getInstance().getAdditionalOrg(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2500Constant.DB_COLUMN_ORG_CD) != null) {
                cMsg.orgCd_G1.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_ORG_CD));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_ORG_STRU_TP_CD) != null) {
                cMsg.orgStruTpCd_G1.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_ORG_STRU_TP_CD));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_EFF_FROM_DT) != null) {
                cMsg.effFromDt_G1.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_EFF_FROM_DT));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_EZUPTIME) != null) {
                cMsg.ezUpTime_G1.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_EZUPTIME));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_EZUPTIMEZONE) != null) {
                cMsg.ezUpTimeZone_G1.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_EZUPTIMEZONE));
            }
        }
    }

    /**
     * getActiveParentOrganization
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     */
    public static void getActiveParentOrganization(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2500Query.getInstance().getActiveParentOrganization(cMsg);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2500Constant.DB_COLUMN_ORG_CD) != null) {
                cMsg.orgCd_G2.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_ORG_CD));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_PRNT_ORG_CD) != null) {
                cMsg.prntOrgCd_G2.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_PRNT_ORG_CD));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_ORG_STRU_TP_CD) != null) {
                cMsg.orgStruTpCd_G2.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_ORG_STRU_TP_CD));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_EFF_FROM_DT) != null) {
                cMsg.effFromDt_G2.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_EFF_FROM_DT));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_EZUPTIME) != null) {
                cMsg.ezUpTime_G2.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_EZUPTIME));
            }

            if (map.get(NMAL2500Constant.DB_COLUMN_EZUPTIMEZONE) != null) {
                cMsg.ezUpTimeZone_G2.setValue((String) map.get(NMAL2500Constant.DB_COLUMN_EZUPTIMEZONE));
            }

            // 2017/03/07 S21_NA#17760 Add Start
            if (map.get(NMAL2500Constant.DB_COLUMN_DS_ORG_RELN_PK) != null) {
                cMsg.dsOrgRelnPk_G2.setValue((BigDecimal) map.get(NMAL2500Constant.DB_COLUMN_DS_ORG_RELN_PK));
            }
            // 2017/03/07 S21_NA#17760 Add End
        }
    }
    
    // Add Start CSA#4556 2016/03/01
    /**
     * getSelectBizAreaCd
     * @param cMsg NMAL2500CMsg
     * @return int
     */
    public static int getSelectBizAreaCd(NMAL2500CMsg cMsg) {
        String selectCd = cMsg.bizAreaOrgCd_P1.getValue();
        
        int i = 0;
        for ( ; i < cMsg.bizAreaOrgNm_H1.length(); i++) {
            if (selectCd.equals(cMsg.bizAreaOrgCd_H1.no(i).getValue())) {
                break;
            }
        }
        return i;
    }    
    // Add End CSA#4556 2016/03/01


    /*
     * Save Search Option
     */
    public static void callNszc0330forDeleteSearch(NMAL2500CMsg bizMsg, NMAL2500SMsg glblMsg, String userId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, userId, glblCmpyCd);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Delete Search") });
        }
    }

    public static boolean isExistSaveSearchName(NMAL2500CMsg bizMsg) {
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

    public static void callNszc0330forSaveSearch(NMAL2500CMsg bizMsg, NMAL2500SMsg glblMsg, String usrId, String glblCmpyCd) {
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
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.bizAreaOrgCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.xxModeCd_P1);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.orgNm_H2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.orgTierCd_P2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.csrRgTpCd_P2);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.xxChkBox_H2);
        if (!bizMsg.xxRadioBtn_H1.isClear()){
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.xxRadioBtn_H1.getValue().toPlainString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.xxPsnNm_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.psnCd_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.jobTtlCd_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.hrTtlNm_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.psnNum_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.orgFuncRoleTpCd_P3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.orgNm_H3);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.effFromDt_FR.getValue()); // MOD S21_NA QC#16481
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.effThruDt_FR.getValue()); // MOD S21_NA QC#16481

        if (callNszc0330(bizMsg, pMsg)) {
            createSavedSearchOptionsPullDown(bizMsg, usrId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
            bizMsg.setMessageInfo("ZZZM9003I" //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Save Search") });
        }
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param bizMsg NYEL8810CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL2500CMsg bizMsg) {
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


    private static boolean isSameSaveSearchName(NMAL2500CMsg cMsg) {
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

    
    private static boolean callNszc0330(NMAL2500CMsg bizMsg, NSZC033001PMsg pMsg) {
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
     * @param bizMsg NMAL2500CMsg
     * @param srchOptUsrId user id
     */
    public static void createSavedSearchOptionsPullDown(NMAL2500CMsg bizMsg, String srchOptUsrId, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NYXC880002Query.getInstance().getSavedSearchOptionList(srchOptUsrId, BIZ_ID, glblCmpyCd);
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
     * @param cMsg NMAL2500CMsg
     * @param sMsg NMAL2500SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchOption(NMAL2500CMsg bizMsg, NMAL2500SMsg glblMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        if (!callNszc0330(bizMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.bizAreaOrgCd_P1,  pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_P1,  pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H2, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgTierCd_P2, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(bizMsg.csrRgTpCd_P2, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H2,  pMsg.srchOptTxt_06);

        if (ZYPCommonFunc.hasValue(pMsg.srchOptTxt_07)){
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_H1, new BigDecimal(pMsg.srchOptTxt_07.getValue()));
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_H3,   pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnCd_H3, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(bizMsg.jobTtlCd_H3,  pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(bizMsg.hrTtlNm_H3,   pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_H3,    pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgFuncRoleTpCd_P3,   pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(bizMsg.orgNm_H3, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_FR, pMsg.srchOptTxt_15.getValue()); // MOD S21_NA QC#16481
        ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_FR, pMsg.srchOptTxt_16.getValue()); // MOD S21_NA QC#16481

    }

}
