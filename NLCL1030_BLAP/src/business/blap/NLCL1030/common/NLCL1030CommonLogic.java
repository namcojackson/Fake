/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1030.common;

import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_PRM_DEL_SRCH;
import static business.blap.NLCL1030.constant.NLCL1030Constant.MSG_PRM_SAVE_SRCH;
import static business.blap.NLCL1030.constant.NLCL1030Constant.SRCH_OPT_NM_DBCOLUMN;
import static business.blap.NLCL1030.constant.NLCL1030Constant.SRCH_OPT_PK_DBCOLUMN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLCL1030.NLCL1030CMsg;
import business.blap.NLCL1030.NLCL1030Query;
import business.blap.NLCL1030.NLCL1030SMsg;
import business.blap.NLCL1030.constant.NLCL1030Constant;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NLCL1030 Inventory ABC Analysis Search
 * Function Name : Common Logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/21/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1030CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     */
    public static void clearAll(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg) {

        // Clear Table
        ZYPTableUtil.clear(cMsg.A);

        // Clear Search Option
        cMsg.srchOptPk_S.clear();
        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_S.clear();
        cMsg.srchOptNm_L.clear();

        // Clear Search Condition

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

            if (i >= cd.length() - 1) {
                break;
            }
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S) || isSameSaveSearchName(cMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S);

        } else {

            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL1030Constant.BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.abcAsgNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.rtlWhCatgCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.rtlWhCdSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.rtlWhNmSrchTxt_RW.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.rtlSwhCdSrchTxt_SW.getValue());

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk);
            cMsg.setMessageInfo(NLCL1030Constant.MSG_CD_ZZZM9003I, new String[] {MSG_PRM_SAVE_SRCH});
        }
    }

    /**
     * callNszc0330forDelete
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDelete(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg, String userId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL1030Constant.BIZ_APP_ID);

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, userId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(NLCL1030Constant.MSG_CD_ZZZM9003I, new String[] {MSG_PRM_DEL_SRCH});
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NLCL1030CMsg
     * @param sMsg NLCL1030SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NLCL1030CMsg cMsg, NLCL1030SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLCL1030Constant.BIZ_APP_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!callNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_S, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.abcAsgNm, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_H1, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCdSrchTxt_RW, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNmSrchTxt_RW, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCdSrchTxt_SW, pMsg.srchOptTxt_05);

    }

    /**
     * createSavedSearchOptionsPullDown
     * @param cMsg NLCL1030CMsg
     * @param srchOptUsrId String
     */
    private static void createSavedSearchOptionsPullDown(NLCL1030CMsg cMsg, String srchOptUsrId) {

        S21SsmEZDResult srchOptnPulldownList = NLCL1030Query.getInstance().getSrchOptnPulldownList();

        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_L.clear();

        if (!srchOptnPulldownList.isCodeNormal()) {
            return;
        }

        List<Map> srchOptnList = (List<Map>) srchOptnPulldownList.getResultObject();
        NLCL1030CommonLogic.createPulldownList(cMsg.srchOptPk_L, cMsg.srchOptNm_L, srchOptnList, new String[] {SRCH_OPT_PK_DBCOLUMN, SRCH_OPT_NM_DBCOLUMN });
    }

    /**
     * isExistSaveSearchName
     * @param cMsg NLCL1030CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLCL1030CMsg cMsg) {

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
     * isSameSaveSearchName
     * @param cMsg NLCL1030CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLCL1030CMsg cMsg) {

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
     * @param cMsg NLCL1030CMsg
     */
    private static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLCL1030CMsg cMsg) {

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
     * @param cMsg NLCL1030CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NLCL1030CMsg cMsg, NSZC033001PMsg pMsg) {

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
     * get array from search text if it has "," in text field.
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItem
     * @return ArrayList<String>
     */
    public static ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(NLCL1030Constant.COMMA) != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(NLCL1030Constant.COMMA);

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals("") && srchTxtArray[i].length() > 0) {

                        String chkTxt = srchTxtArray[i].trim().replace(NLCL1030Constant.PERCENT, "");

                        if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                            splitSrchTxtList.add(srchTxtArray[i].trim());
                        }

                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                String chkTxt = srchTxtItem.getValue().trim().replace(NLCL1030Constant.PERCENT, "");

                if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                    splitSrchTxtList.add(srchTxtItem.getValue().trim());
                }
            }
        }
        if (splitSrchTxtList != null && !splitSrchTxtList.isEmpty()) {

            return splitSrchTxtList;
        }

        return null;
    }

    /**
     * @param tsStr String
     * @return String
     */
    public static String formatDt(String tsStr) {
        if (tsStr == null || tsStr.length() < 8) {
            return "";
        }
        return ZYPDateUtil.formatEzd8ToDisp(tsStr.substring(0, 8), true);
    }
}
