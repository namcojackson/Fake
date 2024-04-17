/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL6890.common;

import static business.blap.NMAL6890.constant.NMAL6890Constant.DB_PARAM_WHOWNRCD_H1;
import static business.blap.NMAL6890.constant.NMAL6890Constant.SRCH_OPT_NM_DBCOLUMN;
import static business.blap.NMAL6890.constant.NMAL6890Constant.SRCH_OPT_PK_DBCOLUMN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL6890.NMAL6890CMsg;
import business.blap.NMAL6890.NMAL6890Query;
import business.blap.NMAL6890.NMAL6890SMsg;
import business.blap.NMAL6890.constant.NMAL6890Constant;
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
 * <pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : blap common logic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 * 21/10/2022   HITACHI         B.Amarsanaa     Update          (QC#60609)
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 *</pre>
 */
public class NMAL6890CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

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
     * get array from search text if it has "," in text field.
     * splitSrchTxt
     * @param srchTxtItem EZDCStringItem
     * @return ArrayList<String>
     */
    public static ArrayList<String> splitSrchTxt(EZDCStringItem srchTxtItem) {

        ArrayList<String> splitSrchTxtList = new ArrayList<String>();
        boolean isSplit = false;

        if (ZYPCommonFunc.hasValue(srchTxtItem)) {

            if (srchTxtItem.getValue().indexOf(NMAL6890Constant.COMMA) != -1) {

                String[] srchTxtArray = srchTxtItem.getValue().split(NMAL6890Constant.COMMA);

                for (int i = 0; i < srchTxtArray.length; i++) {

                    if (!srchTxtArray[i].trim().equals("") && srchTxtArray[i].length() > 0) {

                        String chkTxt = srchTxtArray[i].trim().replace(NMAL6890Constant.PERCENT, "");

                        if (ZYPCommonFunc.hasValue(chkTxt) && chkTxt.length() > 0) {

                            splitSrchTxtList.add(srchTxtArray[i].trim());
                        }

                        isSplit = true;
                    }
                }
            }

            if (!isSplit) {

                String chkTxt = srchTxtItem.getValue().trim().replace(NMAL6890Constant.PERCENT, "");

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
     * isExistSaveSearchName
     * @param cMsg NMAL6890CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NMAL6890CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm)) {

            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_SC.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_SC.no(i))) {

                return false;
            }

            if (cMsg.srchOptNm.getValue().equals(cMsg.srchOptNm_SC.no(i).getValue())) {

                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SS) && cMsg.srchOptPk_SS.getValue().compareTo(cMsg.srchOptPk_SC.no(i).getValue()) == 0) {

                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearch(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm) || isSameSaveSearchName(cMsg)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SS);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm);

        } else {

            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NMAL6890Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.rtlWhNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.rtlWhDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.rtlWhCatgCd_RS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.fullPsnNm_O1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.fullPsnNm_O2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.invtyAcctCd_IS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.shipToLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.rtrnToLocNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.telNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.procrTpCd_SS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.rtlWhNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.procrTpCd_RS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.rtlWhNm_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.procrTpCd_ES.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.rtlWhNm_H4.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.plnItemInsrcCd_MS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.vndLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.invtyOwnrCd_OS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.firstRefCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.effThruDt.getValue());
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.ctyAddr.getValue());
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.whOwnrCd_H1.getValue());
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SS, pMsg.srchOptPk);
            cMsg.setMessageInfo(NMAL6890Constant.MSG_CD_ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NMAL6890Constant.SCREEN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NMAL6890CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NMAL6890CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SS)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_SC.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_SC.no(i))) {
                return false;
            }

            if (cMsg.srchOptPk_SS.getValue().compareTo(cMsg.srchOptPk_SC.no(i).getValue()) == 0) {

                if (cMsg.srchOptNm.getValue().equals(cMsg.srchOptNm_SC.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }

        return false;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param cMsg NMAL6890CMsg
     * @param srchOptUsrId String
     */
    private static void createSavedSearchOptionsPullDown(NMAL6890CMsg cMsg, String srchOptUsrId) {

        S21SsmEZDResult srchOptnPulldownList = NMAL6890Query.getInstance().getSrchOptnPulldownList();

        cMsg.srchOptPk_SC.clear();
        cMsg.srchOptNm_SC.clear();

        if (!srchOptnPulldownList.isCodeNormal()) {
            return;
        }

        List<Map> srchOptnList = (List<Map>) srchOptnPulldownList.getResultObject();
        NMAL6890CommonLogic.createPulldownList(cMsg.srchOptPk_SC, cMsg.srchOptNm_SC, srchOptnList, new String[] {SRCH_OPT_PK_DBCOLUMN, SRCH_OPT_NM_DBCOLUMN });
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NMAL6890CMsg
     */
    private static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NMAL6890CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SS)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_SC.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_SC.no(i))) {
                return;
            }

            if (cMsg.srchOptPk_SS.getValue().compareTo(cMsg.srchOptPk_SC.no(i).getValue()) == 0) {

                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_SC.no(i));
            }
        }
        return;
    }

    /**
     * callNszc0330
     * @param cMsg NMAL6890CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NMAL6890CMsg cMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {

            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {

                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {

                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);

                    if (msgId.endsWith("E")) {

                        cMsg.srchOptPk_SS.setErrorInfo(1, msgId);
                        cMsg.srchOptNm.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * callNszc0330forDelete
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDelete(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg, String userId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NMAL6890Constant.BUSINESS_ID);

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, userId);
            cMsg.srchOptNm.clear();
            cMsg.setMessageInfo(NMAL6890Constant.MSG_CD_ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NMAL6890Constant.SCREEN_ID, "Delete Search") });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NMAL6890CMsg
     * @param sMsg NMAL6890SMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NMAL6890CMsg cMsg, NMAL6890SMsg sMsg, String usrId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SS);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NMAL6890Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!callNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhDescTxt, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_RS, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_O1, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_O2, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(cMsg.invtyAcctCd_IS, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(cMsg.rtrnToLocNm, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.telNum, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SS, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H2, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_RS, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H3, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_ES, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H4, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.plnItemInsrcCd_MS, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(cMsg.vndLocCd, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(cMsg.invtyOwnrCd_OS, pMsg.srchOptTxt_19);
        ZYPEZDItemValueSetter.setValue(cMsg.firstRefCmntTxt, pMsg.srchOptTxt_20);
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt, pMsg.srchOptTxt_21.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt, pMsg.srchOptTxt_22.getValue());
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD START
        ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr, pMsg.srchOptTxt_23.getValue());
        //10/21/2022 HITACHI B.Amarsanaa (QC#60608) ADD END
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.whOwnrCd_H1, pMsg.srchOptTxt_24.getValue());
        // END 2023/03/07 S.Dong [QC#61205, ADD]
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
