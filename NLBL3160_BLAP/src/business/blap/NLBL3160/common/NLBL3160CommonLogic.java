/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3160.common;

import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3160.NLBL3160CMsg;
import business.blap.NLBL3160.NLBL3160Query;
import business.blap.NLBL3160.NLBL3160SMsg;
import business.blap.NLBL3160.constant.NLBL3160Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 * 2018/02/06   CITS            K.Ogino         Update          QC#23875
 * 2022/10/07   Hitachi         T.NEMA          Update          QC#60607
 *</pre>
 */
public class NLBL3160CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    public static void createSavedSearchOptionsPullDown(NLBL3160CMsg cMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NLBL3160Query.getInstance().getSavedSearchOptionList(cMsg.glblCmpyCd.getValue(), srchOptUsrId);

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
     * isExistSaveSearchName
     * @param cMsg NLBL3160CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLBL3160CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {

                return false;
            }

            if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) //
                        && cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {

                    return false;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NLBL3070CMsg
     * @param usrId NLBL3070SMsg
     * @param glblCmpyCd NLBL3070SMsg
     */
    public static void callNszc0330forSaveSearch(NLBL3160CMsg cMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S) || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S.getValue());
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3160Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.cpoNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.psnCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.dsOrdCatgDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.schdCoordStsCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.svcConfigMstrPk.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.shpgSvcLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.lineBizTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.xxChkBox_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.xxChkBox_DC.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.xxChkBox_NA.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.xxChkBox_F.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.xxChkBox_RS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.dsAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.stCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.svcBrCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.rddDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.rddDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.rddDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.rddDt_TO.getValue());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.rtlSwhCd.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.nextActDt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.nextActDt_FR.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.nextActDt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.nextActDt_TO.getValue());
        }
        // START 2022/11/07 T.NEMA [QC#60607, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.xxChkBox_I.getValue());
        // END   2022/11/07 T.NEMA [QC#60607, ADD]
        
        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk.getValue());

            cMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(NLBL3160Constant.SCREEN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NLBL3160CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLBL3160CMsg cMsg) {

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
     * @param cMsg NLBL3160CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLBL3160CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {

            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {

                return;
            }

            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L.no(i).getValue());
            }
        }

        return;
    }

    /**
     * callNszc0330
     * @param cMsg NLBL3070CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NLBL3160CMsg cMsg, NSZC033001PMsg pMsg) {

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
     * callNszc0330forDeleteSearch
     * @param cMsg NLBL3070CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NLBL3160CMsg cMsg, String userId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3160Constant.BUSINESS_ID);

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, userId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo("ZZZM9003I", new String[] {converter.convLabel2i18nLabel(NLBL3160Constant.SCREEN_ID, "Delete Search") });
        }
    }

    /**
     * saveCurrentPageToSMsgScheduling
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     */
    public static void saveCurrentPageToSMsg(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg) {

        cMsg.clearErrorInfo();
        sMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum_A.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * page nation
     * @param cMsg NLBL3160CMsg
     * @param sMsg NLBL3160SMsg
     * @param fromIdx int
     */
    public static void pagenation(NLBL3160CMsg cMsg, NLBL3160SMsg sMsg, int fromIdx) {

        int i = fromIdx;

        for (; i < fromIdx + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - fromIdx), null);

            } else {

                break;
            }
        }

        // set value to pagenation items
        cMsg.A.setValidCount(i - fromIdx);
        cMsg.xxPageShowFromNum_A.setValue(fromIdx + 1);
        cMsg.xxPageShowToNum_A.setValue(fromIdx + cMsg.A.getValidCount());
    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NLBL3160CMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NLBL3160CMsg cMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3160Constant.BUSINESS_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return; // error
        }


        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_S, pMsg.srchOptNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.cpoNum, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.psnCd, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsOrdCatgDescTxt, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.schdCoordStsCd, pMsg.srchOptTxt_04.getValue());
        if (isNumberCheck(pMsg.srchOptTxt_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, new BigDecimal(pMsg.srchOptTxt_05.getValue()));
        } else {
            cMsg.svcConfigMstrPk.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_S, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_DC, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_NA, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_F, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_RS, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.stCd, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.svcBrCd, pMsg.srchOptTxt_16.getValue());
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_17.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(cMsg.rddDt_FR, pMsg.srchOptTxt_17.getValue());
        } else {
            cMsg.rddDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_18.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(cMsg.rddDt_TO, pMsg.srchOptTxt_18.getValue());
        } else {
            cMsg.rddDt_TO.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, pMsg.srchOptTxt_19.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, pMsg.srchOptTxt_20.getValue());
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_21.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(cMsg.nextActDt_FR, pMsg.srchOptTxt_21.getValue());
        } else {
            cMsg.nextActDt_FR.clear();
        }
        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_22.getValue(), "yyyyMMdd")) {
            ZYPEZDItemValueSetter.setValue(cMsg.nextActDt_TO, pMsg.srchOptTxt_22.getValue());
        } else {
            cMsg.nextActDt_TO.clear();
        }
        // START 2022/11/07 T.NEMA [QC#60607, ADD]        
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_I, pMsg.srchOptTxt_23.getValue());
        // END   2022/11/07 T.NEMA [QC#60607, MOD]
    }

    /**
     * QC#23875 getWarehousePermission
     * @param cMsg NLBL3160CMsg
     * @param functionList List<String>
     * @param loginUser String
     * @return List<String> WH List
     */
    public static List<String> getWarehousePermission(NLBL3160CMsg cMsg, List<String> functionList, String loginUser) {

        if (functionList.contains(NLBL3160Constant.FUNC_ID_SUPERVISOR_MANAGER) || functionList.contains(NLBL3160Constant.FUNC_ID_LOGI_IT)) {
            // All Wh Permission User
            return null;
        }

        List<String> permissionWhList = null;

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("schdCoordPsnCd", loginUser);
        param.put("salesDate", cMsg.slsDt.getValue());

        S21SsmEZDResult result = NLBL3160Query.getInstance().getPermissionWhList(param);

        if (result.isCodeNormal()) {

            permissionWhList = (List<String>) result.getResultObject();

            if (permissionWhList.isEmpty()) {
                return null;
            }

            if (permissionWhList.contains("*")) {
                // All Wh permission
                return null;
            }
        }

        return permissionWhList;
    }
}
