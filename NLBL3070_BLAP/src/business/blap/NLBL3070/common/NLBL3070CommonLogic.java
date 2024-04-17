/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3070.common;

import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL3070.NLBL3070CMsg;
import business.blap.NLBL3070.NLBL3070Query;
import business.blap.NLBL3070.NLBL3070SMsg;
import business.blap.NLBL3070.NLBL3070_ASMsg;
import business.blap.NLBL3070.NLBL3070_BSMsg;
import business.blap.NLBL3070.constant.NLBL3070Constant;
import business.db.CPOTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.SHPG_ORD_DTLTMsgArray;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_PLNTMsgArray;
import business.db.SVC_TASKTMsg;
import business.file.NLBL3070F01FMsg;
import business.parts.NLZC060001PMsg;
import business.parts.NSZC033001PMsg;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC060001.NLZC060001;
import com.canon.cusa.s21.api.NLZ.NLZC060001.constant.NLZC060001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#7334
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 * 11/21/2016   CSAI            Y.Imazu         Update          QC#15969
 * 06/09/2017   CSAI            Y.Imazu         Update          QC#18940
 * 06/15/2017   CITS            R.Shimamoto     Update          QC#18272
 * 09/07/2017   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 * 2017/12/28   CITS            T.Hakodate      Update          QC#18460(SOL#087)
 * 01/09/2018   CITS            M.Naito         Update          QC#18889
 * 01/19/2018   Hitachi         K.Ochiai        Update          QC#18889
 * 07/02/2018   CITS            T.Tokutomi      Update          QC#23726
 * 10/03/2018   CITS            M.Naito         Update          QC#28539
 * 10/25/2018   CITS            M.Naito         Update          QC#28867
 * 04/23/2019   Hitachi         K.Kitachi       Update          QC#31245
 * 09/02/2019   Fujitsu         T.Ogura         Update          QC#52581
 * 09/26/2019   CITS            K.Ogino         Update          QC#53743
 * 10/09/2019   Fujitsu         T.Ogura         Update          QC#54059
 * 02/06/2020   CITS            M.Furugoori     Update          QC#50121
 * 05/31/2021   CITS            A.Marte         Update          QC#58786
 * 12/09/2022   Hitachi         T.Kuroda        Update          QC#60810
 *</pre>
 */
public class NLBL3070CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * saveCurrentPageToSMsgScheduling
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    public static void saveCurrentPageToSMsgScheduling(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

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
     * saveCurrentPageToSMsgDeliveries
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     */
    public static void saveCurrentPageToSMsgDeliveries(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.B.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.B.clearErrorInfo();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {

            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(cMsg.B.no(i).xxNum_B1.getValueInt()), null);
        }
    }

    /**
     * page nation
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @param fromIdx int
     */
    public static void pagenation(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg, int fromIdx) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

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

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            int sMsgIdx = 0;
            int cMsgIdx = 0;

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxDplyCtrlFlg_B1.getValue())) {

                    if (fromIdx <= sMsgIdx && cMsgIdx < cMsg.B.length()) {

                        EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(cMsgIdx), null);
                        cMsgIdx++;
                    }

                    sMsgIdx++;
                }
            }

            // set value to pagenation items
            cMsg.B.setValidCount(cMsgIdx);
            cMsg.xxPageShowFromNum_B.setValue(fromIdx + 1);
            cMsg.xxPageShowToNum_B.setValue(fromIdx + cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B.setValue(sMsgIdx);
        }
    }

    /**
     * getPageStartRowIndex
     * @param index int
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return int
     */
    public static int getPageStartRowIndex(int index, NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        int startIndex = 0;

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            startIndex = (index / cMsg.A.length()) * cMsg.A.length();

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            String preTrxHdrNum = "";
            int convIndex = 0;

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {

                if (index == i) {

                    index = convIndex;
                    break;
                }

                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {

                    convIndex++;

                } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxSmryLineFlg_B1.getValue()) && !preTrxHdrNum.equals(sMsg.B.no(i).trxHdrNum_B1.getValue())) {

                    // Summary Flag == Y
                    convIndex++;
                }

                preTrxHdrNum = sMsg.B.no(i).trxHdrNum_B1.getValue();
            }

            startIndex = (index / cMsg.B.length()) * cMsg.B.length();
        }

        return startIndex;
    }

    /**
     * getPageNum
     * @param rowNum int
     * @param cMsg NLBL3070CMsg
     * @return int
     */
    public static int getPageNum(int rowNum, NLBL3070CMsg cMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(cMsg.xxDplyTab.getValue())) {

            return ((rowNum - 1) / cMsg.A.length() + 1);

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(cMsg.xxDplyTab.getValue())) {

            return ((rowNum - 1) / cMsg.B.length() + 1);
        }

        return 0;
    }

    /**
     * allExpansion
     * @param sMsg NLBL3070SMsg
     */
    public static void allExpansion(NLBL3070SMsg sMsg) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxSmryLineFlg_B1, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * isExistSaveSearchName
     * @param cMsg NLBL3070CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLBL3070CMsg cMsg) {

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
    public static void callNszc0330forSaveSearch(NLBL3070CMsg cMsg, String usrId, String glblCmpyCd) {

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

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3070Constant.BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.trxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.dsOrdCatgCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.t_MdlNm.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.svcConfigMstrPk.getValue().toString());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.soNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.dsSoLineStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.shpgSvcLvlCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.schdCoordStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.bolNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.rtlWhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.rtlSwhCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.shipToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.carrCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.schdCoordPsnCd.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.rddDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.rddDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.rddDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.rddDt_TO.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.schdDelyDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdDelyDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.schdDelyDt_TO.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, cMsg.actlDelyDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.actlDelyDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.actlDelyDt_TO.getValue());
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.wmsDropFlg_Y.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.wmsDropFlg_N.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.delyReqFlg_Y.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.delyReqFlg_N.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_FR)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.schdCarrPickUpDt_FR.getValue());
        }

        if (ZYPCommonFunc.hasValue(cMsg.schdCarrPickUpDt_TO)) {

            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.schdCarrPickUpDt_TO.getValue());
        }

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk.getValue());

            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLBL3070Constant.SCREEN_ID, "Save Search") });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NLBL3070CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NLBL3070CMsg cMsg) {

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
     * @param cMsg NLBL3070CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NLBL3070CMsg cMsg) {

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
    private static boolean callNszc0330(NLBL3070CMsg cMsg, NSZC033001PMsg pMsg) {

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
     * createSavedSearchOptionsPullDown
     * @param cMsg NLBL3070CMsg
     * @param srchOptUsrId String
     */
    public static void createSavedSearchOptionsPullDown(NLBL3070CMsg cMsg, String srchOptUsrId) {

        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getSavedSearchOptionList(cMsg.glblCmpyCd.getValue(), srchOptUsrId);

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
     * callNszc0330forDeleteSearch
     * @param cMsg NLBL3070CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearch(NLBL3070CMsg cMsg, String userId, String glblCmpyCd) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3070Constant.BUSINESS_ID);

        if (callNszc0330(cMsg, pMsg)) {

            createSavedSearchOptionsPullDown(cMsg, userId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(NLBL3070Constant.ZZZM9003I, new String[] {converter.convLabel2i18nLabel(NLBL3070Constant.SCREEN_ID, "Delete Search") });
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NLBL3070CMsg
     * @param usrId String
     */
    public static void callNszc0330forSearchOption(NLBL3070CMsg cMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, NLBL3070Constant.BUSINESS_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_S, pMsg.srchOptNm.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum, pMsg.srchOptTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsOrdCatgCd, pMsg.srchOptTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsOrdTpCd, pMsg.srchOptTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm, pMsg.srchOptTxt_04.getValue());

        if (isNumberCheck(pMsg.srchOptTxt_05.getValue())) {

            ZYPEZDItemValueSetter.setValue(cMsg.svcConfigMstrPk, new BigDecimal(pMsg.srchOptTxt_05.getValue()));

        } else {

            cMsg.svcConfigMstrPk.clear();
        }

        ZYPEZDItemValueSetter.setValue(cMsg.soNum, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsSoLineStsCd, pMsg.srchOptTxt_07.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd, pMsg.srchOptTxt_08.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.schdCoordStsCd, pMsg.srchOptTxt_09.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.bolNum, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, pMsg.srchOptTxt_11.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, pMsg.srchOptTxt_12.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, pMsg.srchOptTxt_13.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd, pMsg.srchOptTxt_14.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.schdCoordPsnCd, pMsg.srchOptTxt_15.getValue());

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_16.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.rddDt_FR, pMsg.srchOptTxt_16.getValue());

        } else {

            cMsg.rddDt_FR.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_17.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.rddDt_TO, pMsg.srchOptTxt_17.getValue());

        } else {

            cMsg.rddDt_TO.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_18.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.schdDelyDt_FR, pMsg.srchOptTxt_18.getValue());

        } else {

            cMsg.schdDelyDt_FR.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_19.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.schdDelyDt_TO, pMsg.srchOptTxt_19.getValue());

        } else {

            cMsg.schdDelyDt_TO.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_20.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.actlDelyDt_FR, pMsg.srchOptTxt_20.getValue());

        } else {

            cMsg.actlDelyDt_FR.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_21.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.actlDelyDt_TO, pMsg.srchOptTxt_21.getValue());

        } else {

            cMsg.actlDelyDt_TO.clear();
        }

        ZYPEZDItemValueSetter.setValue(cMsg.wmsDropFlg_Y, pMsg.srchOptTxt_22.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.wmsDropFlg_N, pMsg.srchOptTxt_23.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.delyReqFlg_Y, pMsg.srchOptTxt_24.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.delyReqFlg_N, pMsg.srchOptTxt_25.getValue());

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_26.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.schdCarrPickUpDt_FR, pMsg.srchOptTxt_26.getValue());

        } else {

            cMsg.schdCarrPickUpDt_FR.clear();
        }

        if (ZYPDateUtil.isValidDate(pMsg.srchOptTxt_27.getValue(), "yyyyMMdd")) {

            ZYPEZDItemValueSetter.setValue(cMsg.schdCarrPickUpDt_TO, pMsg.srchOptTxt_27.getValue());

        } else {

            cMsg.schdCarrPickUpDt_TO.clear();
        }
    }

    /**
     * getCarrCdList
     * @param glblCmpyCd String
     * @param carrNm String
     * @return List<String>
     */
    public static List<String> getCarrCdList(String glblCmpyCd, String carrNm) {

        S21SsmEZDResult ssmResultCarr = NLBL3070Query.getInstance().getCarrCdList(glblCmpyCd, carrNm);

        if (!ssmResultCarr.isCodeNormal()) {

            return null;
        }

        List<String> carrCdList = (List<String>) ssmResultCarr.getResultObject();

        return carrCdList;
    }

    /**
     * getAcctCarrCnt
     * @param cMsg NLBL3070CMsg
     * @param carrCd String
     * @param dsAcctNum String
     * @param carrAcctNum String
     * @return BigDecimal
     */
    public static BigDecimal getAcctCarrCnt(NLBL3070CMsg cMsg, String carrCd, String dsAcctNum, String carrAcctNum) {

        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getAcctCarrCnt(cMsg, dsAcctNum, carrCd, carrAcctNum);

        if (!ssmResult.isCodeNormal()) {

            return null;
        }

        BigDecimal acctCarrCnt = (BigDecimal) ssmResult.getResultObject();

        return acctCarrCnt;
    }

    /**
     * getCarrSvcLvlCdList
     * @param cMsg NLBL3070CMsg
     * @param shpgSvcLvlCd String
     * @param carrCd String
     * @return List<String>
     */
    public static List<String> getCarrSvcLvlCdList(NLBL3070CMsg cMsg, String shpgSvcLvlCd, String carrCd) {

        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getCarrSvcLvlCdList(cMsg, shpgSvcLvlCd, carrCd);

        if (!ssmResult.isCodeNormal()) {

            return null;
        }

        List<String> carrSvcLvlCdList = (List<String>) ssmResult.getResultObject();

        return carrSvcLvlCdList;
    }

    /**
     * convDplyDtTm
     * @param dt String
     * @param tm String
     * @return boolean
     */
    public static String convDplyDtTm(String dt, String tm) {

        if (ZYPCommonFunc.hasValue(dt) && dt.length() == 8 && ZYPCommonFunc.hasValue(tm) && 6 == tm.length()) {

            return ZYPDateUtil.formatDisp14ToEzd(dt + tm);

        } else if (ZYPCommonFunc.hasValue(dt) && dt.length() == 8) {

            return ZYPDateUtil.formatDisp8ToEzd(dt);
        }

        return null;
    }

    /**
     * convDplyDtTm
     * @param timeStamp String
     * @return String
     */
    public static String convDplyDtTm(String timeStamp) {

        if (ZYPCommonFunc.hasValue(timeStamp)) {

            return ZYPDateUtil.formatEzd14ToDisp(timeStamp);
        }

        return null;
    }

    /**
     * changedValue
     * @param newVal String
     * @param oldVal String
     * @return boolean
     */
    public static boolean changedValue(String newVal, String oldVal) {

        if (ZYPCommonFunc.hasValue(newVal)) {

            if (!newVal.equals(oldVal)) {

                // Changed
                return true;
            }

        } else if (ZYPCommonFunc.hasValue(oldVal)) {

            // Changed
            return true;
        }

        return false;
    }

    /**
     * changedValue
     * @param newVal BigDecimal
     * @param oldVal BigDecimal
     * @return boolean
     */
    public static boolean changedValue(BigDecimal newVal, BigDecimal oldVal) {

        if (ZYPCommonFunc.hasValue(newVal)) {

            if (!ZYPCommonFunc.hasValue(oldVal)) {

                // Changed
                return true;

            } else if (newVal.compareTo((oldVal)) != 0) {

                // Changed
                return true;
            }

        } else if (ZYPCommonFunc.hasValue(oldVal)) {

            // Changed
            return true;
        }

        return false;
    }

    /**
     * sort
     * @param sMsgArray EZDSMsgArray
     * @param sortItemNm String
     * @param sortOrdBy String
     * @param baseContents String[][]
     * @param nullLast boolean
     */
    public static void sort(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents, boolean nullLast) {

        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);

        if (nullLast) {

            S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, sMsgArray.getValidCount());

        } else {

            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        }
    }

    /**
     * checkTimeStampSoLine
     * @param sMsgBLine NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @param chkHdrTimeStamp boolean
     * @return boolean
     */
    public static boolean checkTimeStampSoLine(NLBL3070_BSMsg sMsgBLine, String glblCmpyCd, boolean chkHdrTimeStamp) {

        // SHPG_ORD
        if (chkHdrTimeStamp) {

            // SHPG_ORD
            SHPG_ORDTMsg shpgOrd = new SHPG_ORDTMsg();
            ZYPEZDItemValueSetter.setValue(shpgOrd.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpgOrd.soNum, sMsgBLine.soNum_B1.getValue());
            shpgOrd = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrd);

            if (shpgOrd == null) {

                return false;
            }

            if (!ZYPDateUtil.isSameTimeStamp(sMsgBLine.ezUpTime_SO.getValue(), sMsgBLine.ezUpTimeZone_SO.getValue(), shpgOrd.ezUpTime.getValue(), shpgOrd.ezUpTimeZone.getValue())) {

                // anyone update
                return false;
            }
        }

        // SHPG_ORD_DTL
        SHPG_ORD_DTLTMsg shpgOrdDtl = new SHPG_ORD_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.soNum, sMsgBLine.soNum_B1.getValue());
        ZYPEZDItemValueSetter.setValue(shpgOrdDtl.soSlpNum, sMsgBLine.soSlpNum_B1.getValue());
        shpgOrdDtl = (SHPG_ORD_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdDtl);

        if (shpgOrdDtl == null) {

            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(sMsgBLine.ezUpTime_SD.getValue(), sMsgBLine.ezUpTimeZone_SD.getValue(), shpgOrdDtl.ezUpTime.getValue(), shpgOrdDtl.ezUpTimeZone.getValue())) {

            // anyone update
            return false;
        }
        return true;
    }

    /**
     * String
     * @param sMsg NLBL3070SMsg
     * @param sMsgBLine NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPartial(NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLine, String glblCmpyCd) {

        SHPG_ORD_DTLTMsg shpgOrdDtl = new SHPG_ORD_DTLTMsg();
        shpgOrdDtl.setSQLID("002");
        shpgOrdDtl.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shpgOrdDtl.setConditionValue("soNum01", sMsgBLine.soNum_B1.getValue());
        SHPG_ORD_DTLTMsgArray shpgOrdDtlList = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtl);
        int shpgOrdDtlCnt = 0;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (sMsgBLine.soNum_B1.getValue().equals(sMsg.B.no(i).soNum_B1.getValue())) {

                if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                    return false;

                } else {

                    shpgOrdDtlCnt++;
                }
            }
        }

        // If not display data having same So#
        if (shpgOrdDtlList == null || shpgOrdDtlCnt != shpgOrdDtlList.getValidCount()) {

            return false;
        }

        return true;
    }

    /**
     * checkSetComponent
     * @param sMsg NLBL3070SMsg
     * @param sMsgBLine NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkSetComponent(NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLine, String glblCmpyCd) {

        String setMdseCd = sMsgBLine.setMdseCd_B1.getValue();
        String trxHdrNum = sMsgBLine.trxHdrNum_B1.getValue();
        String trxHdrLineNum = sMsgBLine.trxLineNum_B1.getValue();
        String setShpgPlnNum = sMsgBLine.setShpgPlnNum_B1.getValue();
        String soNum = sMsgBLine.soNum_B1.getValue();
        HashSet<String> soSlpNumSet = new HashSet<String>();

        if (!ZYPCommonFunc.hasValue(setMdseCd) && !ZYPCommonFunc.hasValue(setShpgPlnNum)) {
            // Skip
            // Not Set Component Item
            return true;
        }

        boolean chkOffSetMdse = false;
        boolean chkOffSetShpgPlnNum = false;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(setMdseCd)) {

                if (soNum.equals(sMsg.B.no(i).soNum_B1.getValue()) && setMdseCd.equals(sMsg.B.no(i).setMdseCd_B1.getValue()) && trxHdrNum.equals(sMsg.B.no(i).trxHdrNum_B1.getValue())
                        && trxHdrLineNum.equals(sMsg.B.no(i).trxLineNum_B1.getValue())) {

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                        // Found Check OFF
                        chkOffSetMdse = true;
                    }

                    soSlpNumSet.add(sMsg.B.no(i).soSlpNum_B1.getValue());
                }
            }

            if (soNum.equals(sMsg.B.no(i).soNum_B1.getValue()) && ZYPCommonFunc.hasValue(setShpgPlnNum)) {

                if (setShpgPlnNum.equals(sMsg.B.no(i).setShpgPlnNum_B1.getValue())) {

                    if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                        // Found Check OFF
                        chkOffSetShpgPlnNum = true;
                    }

                    soSlpNumSet.add(sMsg.B.no(i).soSlpNum_B1.getValue());
                }
            }
        }

        if (chkOffSetMdse && chkOffSetShpgPlnNum) {

            // There is check off line of same component Item.
            return false;
        }

        // DB Check
        boolean chkOk = true;

        if (ZYPCommonFunc.hasValue(setMdseCd)) {

            SHPG_ORD_DTLTMsg shpgOrdDtl = new SHPG_ORD_DTLTMsg();
            shpgOrdDtl.setSQLID("006");
            shpgOrdDtl.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shpgOrdDtl.setConditionValue("soNum01", soNum);
            shpgOrdDtl.setConditionValue("setMdseCd01", setMdseCd);
            shpgOrdDtl.setConditionValue("trxHdrNum01", trxHdrNum);
            shpgOrdDtl.setConditionValue("trxLineNum01", trxHdrLineNum);
            SHPG_ORD_DTLTMsgArray shpgOrdDtlList = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtl);

            if (shpgOrdDtlList != null && 0 < shpgOrdDtlList.getValidCount()) {

                for (int i = 0; i < shpgOrdDtlList.getValidCount(); i++) {

                    String soSlpNum = shpgOrdDtlList.no(i).soSlpNum.getValue();

                    if (!soSlpNumSet.contains(soSlpNum)) {

                        chkOk = false;
                        break;
                    }
                }

                if (chkOk) {

                    return true;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(setShpgPlnNum)) {

            SHPG_PLNTMsg shpgPln = new SHPG_PLNTMsg();
            shpgPln.setSQLID("039");
            shpgPln.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shpgPln.setConditionValue("soNum01", soNum);
            shpgPln.setConditionValue("setShpgPlnNum01", setShpgPlnNum);
            SHPG_PLNTMsgArray shpgPlnList = (SHPG_PLNTMsgArray) EZDTBLAccessor.findByCondition(shpgPln);

            if (shpgPlnList != null && 0 < shpgPlnList.getValidCount()) {

                for (int i = 0; i < shpgPlnList.getValidCount(); i++) {

                    String soSlpNum = shpgPlnList.no(i).soSlpNum.getValue();

                    if (!soSlpNumSet.contains(soSlpNum)) {

                        chkOk = false;
                        break;
                    }
                }

                if (chkOk) {

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * checkConfigId
     * @param sMsg NLBL3070SMsg
     * @param sMsgBLine NLBL3070_BSMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkConfigId(NLBL3070SMsg sMsg, NLBL3070_BSMsg sMsgBLine, String glblCmpyCd) {

        // Get SHPG_ORD_DTL
        SHPG_ORD_DTLTMsg shpgOrdDtl = new SHPG_ORD_DTLTMsg();
        shpgOrdDtl.setSQLID("502");
        shpgOrdDtl.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shpgOrdDtl.setConditionValue("soNum01", sMsgBLine.soNum_B1.getValue());
        shpgOrdDtl.setConditionValue("pickSvcConfigMstrPk01", sMsgBLine.pickSvcConfigMstrPk_B1.getValue());
        SHPG_ORD_DTLTMsgArray shpgOrdDtlList = (SHPG_ORD_DTLTMsgArray) EZDTBLAccessor.findByCondition(shpgOrdDtl);
        int configIdCnt = 0;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            if (sMsgBLine.soNum_B1.getValue().equals(sMsg.B.no(i).soNum_B1.getValue()) && sMsgBLine.pickSvcConfigMstrPk_B1.getValue().equals(sMsg.B.no(i).pickSvcConfigMstrPk_B1.getValue())) {

                if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_B2.getValue())) {

                    return true;

                } else {

                    configIdCnt++;
                }
            }
        }

        // If not display data having same Configuration Id
        if (shpgOrdDtlList == null || configIdCnt != shpgOrdDtlList.getValidCount()) {

            return true;
        }

        return false;
    }

    /**
     * chkPiActivity
     * @param glblCmpyCd String
     * @param soNum String
     * @return boolean
     */
    public static boolean chkPiActivity(String glblCmpyCd, String soNum) {

        NLZC060001PMsg piActivityStsApiPMsg = new NLZC060001PMsg();
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(piActivityStsApiPMsg.soNum, soNum);

        NLZC060001 piActivityStsApi = new NLZC060001();
        piActivityStsApi.execute(piActivityStsApiPMsg, ONBATCH_TYPE.ONLINE);

        if (NLZC060001Constant.RETURN_CODE_ERROR.equals(piActivityStsApiPMsg.xxRsltStsCd.getValue())) {

            return false;
        }

        return true;
    }

    /**
     * Create setPulldownTimeAmPm
     * @param cMsg NLBL3070CMsg
     */
    public static void setPulldownTimeAmPm(NLBL3070CMsg cMsg) {

        cMsg.rqstRcvDtTxt_CD.no(0).setValue(NLBL3070Constant.TIME_AM);
        cMsg.rqstRcvDtTxt_CD.no(1).setValue(NLBL3070Constant.TIME_PM);
        cMsg.rqstRcvDtTxt_DI.no(0).setValue(NLBL3070Constant.TIME_AM);
        cMsg.rqstRcvDtTxt_DI.no(1).setValue(NLBL3070Constant.TIME_PM);
    }

    /**
     * checkTimeAndAmPm
     * @param timeHHMM EZDSStringItem
     * @param timeAMPM EZDSStringItem
     * @return boolean
     */
    public static boolean checkTimeAndAmPm(EZDSStringItem timeHHMM, EZDSStringItem timeAMPM) {

        boolean checkTimeAndAmPm = true;

        if (ZYPCommonFunc.hasValue(timeHHMM) && !ZYPCommonFunc.hasValue(timeAMPM)) {

            timeAMPM.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Time(AM/PM)" });
            checkTimeAndAmPm = false;
        }

        if (!ZYPCommonFunc.hasValue(timeHHMM) && ZYPCommonFunc.hasValue(timeAMPM)) {

            timeHHMM.setErrorInfo(1, NLBL3070Constant.ZZM9000E, new String[] {"Time" });
            checkTimeAndAmPm = false;
        }

        if (ZYPCommonFunc.hasValue(timeHHMM) && ZYPCommonFunc.hasValue(timeAMPM)) {

            if (!checkTime(timeHHMM.getValue(), timeAMPM.getValue())) {

                timeHHMM.setErrorInfo(1, NLBL3070Constant.NWAM0665E);
                checkTimeAndAmPm = false;
            }
        }

        return checkTimeAndAmPm;
    }

    /**
     * checkTime
     * @param timeHHMM String
     * @param timeAMPM String
     * @return boolean
     */
    private static boolean checkTime(String timeHHMM, String timeAMPM) {

        String hourMinute = getAllDayTimes(timeHHMM, timeAMPM);

        if (!ZYPCommonFunc.hasValue(hourMinute)) {

            return false;
        }

        return true;
    }

    /**
     * getAllDayTimes
     * @param timeHHMM String
     * @param timeAMPM String
     * @return String
     */
    public static String getAllDayTimes(String timeHHMM, String timeAMPM) {

        int hour = 0;
        int minute = 0;

        if (0 <= timeHHMM.indexOf(":")) {

            String[] temp = timeHHMM.split(":");

            if (temp.length != 2) {

                return null;
            }

            if (!ZYPCommonFunc.isNumberCheck(temp[0]) || !ZYPCommonFunc.isNumberCheck(temp[1])) {

                return null;
            }

            hour = Integer.valueOf(temp[0]);
            minute = Integer.valueOf(temp[1]);

        } else {

            if (!ZYPCommonFunc.isNumberCheck(timeHHMM)) {

                return null;
            }

            if (timeHHMM.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH) {

                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else if (timeHHMM.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                timeHHMM = "0" + timeHHMM;
                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else {

                return null;
            }
        }

        if (hour < 0 || minute < 0) {

            return null;

        } else if (NLBL3070Constant.HALF_DAY_HOURS <= hour) {

            return null;

        } else if (NLBL3070Constant.ONE_HOUR_MINUTES <= minute) {

            return null;
        }

        if (NLBL3070Constant.TIME_PM.equals(timeAMPM)) {

            hour += NLBL3070Constant.HALF_DAY_HOURS;
        }

        return (String.format("%1$02d", hour)).concat(String.format("%1$02d", minute));
    }

    /**
     * getAllDayTimes
     * @param timeHHMM EZDSStringItem
     * @param timeAMPM EZDSStringItem
     * @param hourMinute String
     */
    public static void splitHourMinute(EZDSStringItem timeHHMM, EZDSStringItem timeAMPM, String hourMinute) {

        if (ZYPCommonFunc.hasValue(hourMinute) && ZYPCommonFunc.isNumberCheck(hourMinute)) {

            String hh = null;
            String mm = null;
            int hour = 0;

            if (hourMinute.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH) {

                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                hourMinute = "0" + hourMinute;
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            // QC#18272 Add.
            } else if (hourMinute.length() == NLBL3070Constant.HOUR_MINUTE_STR_LENGTH + 2) {
            	// ACTL_DELY_TM, SCHD_DELY_TM
            	hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2, 4);
                hour = Integer.valueOf(hh);

            } else {

                timeHHMM.clear();
                timeAMPM.clear();
                return;
            }

            if (NLBL3070Constant.HALF_DAY_HOURS <= hour) {

                hour -= NLBL3070Constant.HALF_DAY_HOURS;
                ZYPEZDItemValueSetter.setValue(timeAMPM, NLBL3070Constant.TIME_PM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(String.format("%1$02d", hour), ":", mm));
                return;

            } else {

                ZYPEZDItemValueSetter.setValue(timeAMPM, NLBL3070Constant.TIME_AM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(hh, ":", mm));
                return;
            }
        }

        timeHHMM.clear();
        timeAMPM.clear();
        return;
    }

    /**
     * changedValueSchdLine
     * @param sMsgALine NLBL3070_ASMsg
     * @return boolean
     */
    public static boolean changedValueSchdLine(NLBL3070_ASMsg sMsgALine) {

        // schdCarrPickUpDt
        if (changedValue(sMsgALine.schdCarrPickUpDt_A1.getValue(), sMsgALine.schdCarrPickUpDt_BK.getValue())) {

            return true;
        }

        // schdDelyDt
        if (changedValue(sMsgALine.schdDelyDt_A1.getValue(), sMsgALine.schdDelyDt_BK.getValue())) {

            return true;
        }

        // QC#18272 Add.
        // schdDelyTm
        if (changedValue(sMsgALine.schdDelyTsDplyTxt_A2.getValue(), sMsgALine.schdDelyTsDplyTxt_K1.getValue())) {

            return true;
        }

        if (changedValue(sMsgALine.rqstRcvDtTxt_S2.getValue(), sMsgALine.rqstRcvDtTxt_K1.getValue())) {

            return true;
        }

        // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        // techMeetTruckFlg
        // START 2020/02/14 [QC#50121, MOD]
        String techMeetTruckFlg = sMsgALine.techMeetTruckFlg_A1.getValue();

        if (!ZYPCommonFunc.hasValue(techMeetTruckFlg)) {
            techMeetTruckFlg = ZYPConstant.FLG_OFF_N;
        }
//        if (changedValue(sMsgALine.techMeetTruckFlg_A1.getValue(), sMsgALine.techMeetTruckFlg_BK.getValue())) {
        if (changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BK.getValue())) {

            return true;
        }
        // END 2020/02/14 [QC#50121, MOD]
        // END 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]

        // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        // nextActDt
        if (changedValue(sMsgALine.nextActDt_A1.getValue(), sMsgALine.nextActDt_BK.getValue())) {

            return true;
        }
        // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

        // carrCd
        if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BK.getValue())) {

            return true;
        }

        // carrNm
        if (changedValue(sMsgALine.carrNm_A1.getValue(), sMsgALine.carrNm_BK.getValue())) {

            return true;
        }

        // Time stamp(Date)
        if (changedValue(sMsgALine.schdIstlDt_A1.getValue(), sMsgALine.schdIstlDt_BK.getValue())) {

            return true;
        }

        // Time stamp(Time)
        if (changedValue(sMsgALine.schdDelyTsDplyTxt_A1.getValue(), sMsgALine.schdDelyTsDplyTxt_BK.getValue())) {

            return true;
        }

        // Time stamp(AM/PM)
        if (changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue())) {

            return true;
        }

        // schdDurnTmNum
        if (changedValue(sMsgALine.schdDurnTmNum_A1.getValue(), sMsgALine.schdDurnTmNum_BK.getValue())) {

            return true;
        }

        // schdCoordStsCd
        if (changedValue(sMsgALine.schdCoordStsCd_A1.getValue(), sMsgALine.schdCoordStsCd_BK.getValue())) {

            return true;
        }

        // shpgSvcLvlCd
        if (changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_BK.getValue())) {

            return true;
        }

        // tempSchdRsnCd
        if (changedValue(sMsgALine.tempSchdRsnCd_A1.getValue(), sMsgALine.tempSchdRsnCd_BK.getValue())) {

            return true;
        }

        // tempSchdCmntTxt
        if (changedValue(sMsgALine.tempSchdCmntTxt_A1.getValue(), sMsgALine.tempSchdCmntTxt_BK.getValue())) {

            return true;
        }

        // carrAcctNum
        if (changedValue(sMsgALine.carrAcctNum_A1.getValue(), sMsgALine.carrAcctNum_BK.getValue())) {

            return true;
        }

        return false;
    }

    /**
     * changedValueSchdLinePrev
     * @param sMsgALine NLBL3070_ASMsg
     * @return boolean
     */
    public static boolean changedValueSchdLinePrev(NLBL3070_ASMsg sMsgALine) {

        // schdCarrPickUpDt
        if (changedValue(sMsgALine.schdCarrPickUpDt_A1.getValue(), sMsgALine.schdCarrPickUpDt_BW.getValue())) {

            return true;
        }

        // schdDelyDt
        if (changedValue(sMsgALine.schdDelyDt_A1.getValue(), sMsgALine.schdDelyDt_BW.getValue())) {

            return true;
        }

        // QC#18272 Add.
        // schdDelyTm
        if (changedValue(sMsgALine.schdDelyTsDplyTxt_A2.getValue(), sMsgALine.schdDelyTsDplyTxt_W1.getValue())) {

            return true;
        }

        // rqstRcvDtTxt
        if (changedValue(sMsgALine.rqstRcvDtTxt_S2.getValue(), sMsgALine.rqstRcvDtTxt_W1.getValue())) {

            return true;
        }

        // START 2017/12/28 T.Hakodate [Sol#032(QC#13117),ADD]
        String techMeetTruckFlg = sMsgALine.techMeetTruckFlg_A1.getValue();

        // START 2020/02/06 [QC#50121, MOD]
//        if (!ZYPCommonFunc.hasValue(techMeetTruckFlg)) {
//            techMeetTruckFlg = ZYPConstant.FLG_OFF_N;
//        }
//
//        if (changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BK.getValue())) {
        if (changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BW.getValue())) {

            return true;
        }
        // END 2020/02/06 [QC#50121, MOD]
        // END 2017/12/28 T.Hakodate [Sol#032(QC#13117),ADD]

        // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        // nextActDt
        if (changedValue(sMsgALine.nextActDt_A1.getValue(), sMsgALine.nextActDt_BK.getValue())) {

            return true;
        }
        // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

        // carrCd
        if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BW.getValue())) {

            return true;
        }

        // carrNm
        if (changedValue(sMsgALine.carrNm_A1.getValue(), sMsgALine.carrNm_BW.getValue())) {

            return true;
        }

        // schdIstlDt
        if (changedValue(sMsgALine.schdIstlDt_A1.getValue(), sMsgALine.schdIstlDt_BW.getValue())) {

            return true;
        }

        // schdIstlTm
        if (changedValue(sMsgALine.schdDelyTsDplyTxt_A1.getValue(), sMsgALine.schdDelyTsDplyTxt_BW.getValue())) {

            return true;
        }

        // rqstRcvDtTxt
        if (changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BW.getValue())) {

            return true;
        }

        // schdDurnTmNum
        if (changedValue(sMsgALine.schdDurnTmNum_A1.getValue(), sMsgALine.schdDurnTmNum_BW.getValue())) {

            return true;
        }

        // schdCoordStsCd
        if (changedValue(sMsgALine.schdCoordStsCd_A1.getValue(), sMsgALine.schdCoordStsCd_BW.getValue())) {

            return true;
        }

        // shpgSvcLvlCd
        if (changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_BW.getValue())) {

            return true;
        }

        // tempSchdRsnCd
        if (changedValue(sMsgALine.tempSchdRsnCd_A1.getValue(), sMsgALine.tempSchdRsnCd_BW.getValue())) {

            return true;
        }

        // tempSchdCmntTxt
        if (changedValue(sMsgALine.tempSchdCmntTxt_A1.getValue(), sMsgALine.tempSchdCmntTxt_BW.getValue())) {

            return true;
        }

        return false;
    }

    /**
     * changedValueDelvLinePrev
     * @param sMsgBLine NLBL3070_BSMsg
     * @return boolean
     */
    public static boolean changedValueDelvLinePrev(NLBL3070_BSMsg sMsgBLine) {

        // shipQty
        if (changedValue(sMsgBLine.xxShipQty_B1.getValue(), sMsgBLine.shipQty_BW.getValue())) {

            return true;
        }

        // carrCd
        if (changedValue(sMsgBLine.carrCd_B1.getValue(), sMsgBLine.carrCd_BW.getValue())) {

            return true;
        }

        // carrNm
        if (changedValue(sMsgBLine.carrNm_B1.getValue(), sMsgBLine.carrNm_BW.getValue())) {

            return true;
        }

        // serNum
        if (sMsgBLine.serNum_B1.getValue().indexOf(",") == -1) {
            if (changedValue(sMsgBLine.serNum_B1.getValue(), sMsgBLine.serNum_BW.getValue())) {

                return true;
            }
        }

        // carrRsnCd
        if (changedValue(sMsgBLine.carrRsnCd_B1.getValue(), sMsgBLine.carrRsnCd_BW.getValue())) {

            return true;
        }

        // actlDelyDt
        if (changedValue(sMsgBLine.actlDelyDt_B1.getValue(), sMsgBLine.actlDelyDt_BW.getValue())) {

            return true;
        }

        // actlDelyTm
        // QC#18272 Mod.
        // QC#23726 Delete. schdDelyTs is not input.
//        if (changedValue(sMsgBLine.schdDelyTsDplyTxt_B2.getValue(), sMsgBLine.schdDelyTsDplyTxt_W2.getValue())) {
//
//            return true;
//        }

        // bolNum
        if (changedValue(sMsgBLine.proNum_B1.getValue(), sMsgBLine.bolNum_BW.getValue())) {

            return true;
        }

        // totFrtAmt
        if (changedValue(sMsgBLine.totFrtAmt_B1.getValue(), sMsgBLine.totFrtAmt_BW.getValue())) {

            return true;
        }

        return false;
    }

    /**
     * inputCheckWaringForValueChange
     * @param cMsg NLBL3070CMsg
     * @param sMsg NLBL3070SMsg
     * @return boolean
     */
    public static boolean inputCheckWaringForValueChange(NLBL3070CMsg cMsg, NLBL3070SMsg sMsg) {

        boolean hasWrng = false;
        int firstErrIdx = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NLBL3070_ASMsg sMsgALine = sMsg.A.no(i);

            // schdCarrPickUpDt
            if (changedValue(sMsgALine.schdCarrPickUpDt_A1.getValue(), sMsgALine.schdCarrPickUpDt_BK.getValue())) {

                sMsgALine.schdCarrPickUpDt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // schdDelyDt
            if (changedValue(sMsgALine.schdDelyDt_A1.getValue(), sMsgALine.schdDelyDt_BK.getValue())) {

                sMsgALine.schdDelyDt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // QC#18272 Add.
            // schdDelyTm(Time)
            if (changedValue(sMsgALine.schdDelyTsDplyTxt_A2.getValue(), sMsgALine.schdDelyTsDplyTxt_K1.getValue())) {

                sMsgALine.schdDelyTsDplyTxt_A2.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // schdDelyTm(AM/PM)
            if (changedValue(sMsgALine.rqstRcvDtTxt_S2.getValue(), sMsgALine.rqstRcvDtTxt_K1.getValue())) {

                sMsgALine.rqstRcvDtTxt_S2.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            String techMeetTruckFlg = sMsgALine.techMeetTruckFlg_A1.getValue();

            if (!ZYPCommonFunc.hasValue(techMeetTruckFlg)) {
                techMeetTruckFlg = ZYPConstant.FLG_OFF_N;
            }

            if (changedValue(techMeetTruckFlg, sMsgALine.techMeetTruckFlg_BK.getValue())) {

                sMsgALine.techMeetTruckFlg_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }
            // END 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            
            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            // nextActDt
            if (changedValue(sMsgALine.nextActDt_A1.getValue(), sMsgALine.nextActDt_BK.getValue())) {

                sMsgALine.nextActDt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

            // carrCd
            if (changedValue(sMsgALine.carrCd_A1.getValue(), sMsgALine.carrCd_BK.getValue())) {

                sMsgALine.carrNm_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // carrNm
            if (changedValue(sMsgALine.carrNm_A1.getValue(), sMsgALine.carrNm_BK.getValue())) {

                sMsgALine.carrNm_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

         // 201/12/28 QC#18460(SOL#087) T,Hakodate DEL START
            // Timestop(Date)
//            if (changedValue(sMsgALine.schdIstlDt_A1.getValue(), sMsgALine.schdIstlDt_BK.getValue())) {
//
//                sMsgALine.schdIstlDt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
//                hasWrng = true;
//
//                if (firstErrIdx == -1) {
//
//                    firstErrIdx = i;
//                }
//            }
//
//            // Timestop(Time)
//            if (changedValue(sMsgALine.schdDelyTsDplyTxt_A1.getValue(), sMsgALine.schdDelyTsDplyTxt_BK.getValue())) {
//
//                sMsgALine.schdDelyTsDplyTxt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
//                hasWrng = true;
//
//                if (firstErrIdx == -1) {
//
//                    firstErrIdx = i;
//                }
//            }
//
//            // Timestop(AM/PM)
//            if (changedValue(sMsgALine.rqstRcvDtTxt_A1.getValue(), sMsgALine.rqstRcvDtTxt_BK.getValue())) {
//
//                sMsgALine.rqstRcvDtTxt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
//                hasWrng = true;
//
//                if (firstErrIdx == -1) {
//
//                    firstErrIdx = i;
//                }
//            }
         // 201/12/28 QC#18460(SOL#087) T,Hakodate DEL END

            // schdDurnTmNum
            if (changedValue(sMsgALine.schdDurnTmNum_A1.getValue(), sMsgALine.schdDurnTmNum_BK.getValue())) {

                sMsgALine.schdDurnTmNum_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // schdCoordStsCd
            if (changedValue(sMsgALine.schdCoordStsCd_A1.getValue(), sMsgALine.schdCoordStsCd_BK.getValue())) {

                sMsgALine.schdCoordStsCd_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // shpgSvcLvlCd
            if (changedValue(sMsgALine.shpgSvcLvlCd_A1.getValue(), sMsgALine.shpgSvcLvlCd_BK.getValue())) {

                sMsgALine.shpgSvcLvlCd_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // tempSchdRsnCd
            if (changedValue(sMsgALine.tempSchdRsnCd_A1.getValue(), sMsgALine.tempSchdRsnCd_BK.getValue())) {

                sMsgALine.tempSchdRsnCd_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }

            // tempSchdCmntTxt
            if (changedValue(sMsgALine.tempSchdCmntTxt_A1.getValue(), sMsgALine.tempSchdCmntTxt_BK.getValue())) {

                sMsgALine.tempSchdCmntTxt_A1.setErrorInfo(2, NLBL3070Constant.NSBM0029W);
                hasWrng = true;

                if (firstErrIdx == -1) {

                    firstErrIdx = i;
                }
            }
        }

        if (hasWrng) {

            pagenation(cMsg, sMsg, getPageStartRowIndex(firstErrIdx, cMsg, sMsg));
            return false;
        }

        return true;
    }

    /**
     * Set Previous Data for Warning Check
     * @param sMsgALine NLBL3070_ASMsg
     */
    public static void setPrevDataForWrngChk(NLBL3070_ASMsg sMsgALine) {

        ZYPEZDItemValueSetter.setValue(sMsgALine.schdCarrPickUpDt_BW, sMsgALine.schdCarrPickUpDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.schdDelyDt_BW, sMsgALine.schdDelyDt_A1.getValue());
        // QC#18272 Mod.
        ZYPEZDItemValueSetter.setValue(sMsgALine.schdDelyTsDplyTxt_W1, sMsgALine.schdDelyTsDplyTxt_A2.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.rqstRcvDtTxt_W1, sMsgALine.rqstRcvDtTxt_S2.getValue());
        
        // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        ZYPEZDItemValueSetter.setValue(sMsgALine.techMeetTruckFlg_BW, sMsgALine.techMeetTruckFlg_A1.getValue());
        // END 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]

        // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        ZYPEZDItemValueSetter.setValue(sMsgALine.nextActDt_BW, sMsgALine.nextActDt_A1.getValue());
        // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]


        ZYPEZDItemValueSetter.setValue(sMsgALine.carrNm_BW, sMsgALine.carrNm_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.carrCd_BW, sMsgALine.carrCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.schdCoordStsCd_BW, sMsgALine.schdCoordStsCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.shpgSvcLvlCd_BW, sMsgALine.shpgSvcLvlCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.tempSchdRsnCd_BW, sMsgALine.tempSchdRsnCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.tempSchdCmntTxt_BW, sMsgALine.tempSchdCmntTxt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.schdIstlDt_BW, sMsgALine.schdIstlDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.schdDelyTsDplyTxt_BW, sMsgALine.schdDelyTsDplyTxt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.rqstRcvDtTxt_BW, sMsgALine.rqstRcvDtTxt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(sMsgALine.schdDurnTmNum_BW, sMsgALine.schdDurnTmNum_A1.getValue());
    }

    /**
     * initPrevVal
     * @param sMsgALine NLBL3070_ASMsg
     */
    public static void initSchdPrevVal(NLBL3070_ASMsg sMsgALine) {

        sMsgALine.schdCarrPickUpDt_BW.clear();
        sMsgALine.schdDelyDt_BW.clear();
        // QC#18272 Mod.
        sMsgALine.schdDelyTsDplyTxt_W1.clear();
        sMsgALine.rqstRcvDtTxt_W1.clear();
        // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
        sMsgALine.techMeetTruckFlg_BW.clear();
        // END 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]

        // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        sMsgALine.nextActDt_BW.clear();
        // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

        sMsgALine.carrCd_BW.clear();
        sMsgALine.carrNm_BW.clear();
        sMsgALine.schdIstlDt_BW.clear();
        sMsgALine.schdDelyTsDplyTxt_BW.clear();
        sMsgALine.rqstRcvDtTxt_BW.clear();
        sMsgALine.schdDurnTmNum_BW.clear();
        sMsgALine.schdCoordStsCd_BW.clear();
        sMsgALine.shpgSvcLvlCd_BW.clear();
        sMsgALine.tempSchdRsnCd_BW.clear();
        sMsgALine.tempSchdCmntTxt_BW.clear();
    }

    /**
     * Set Previous Data for Warning Check
     * @param glblCmpyCd String
     * @param sMsgALine NLBL3070_ASMsg
     * @return boolean
     */
    public static boolean chkDsShpgOrdTimeStamp(String glblCmpyCd, NLBL3070_ASMsg sMsgALine) {

        SHPG_ORDTMsg shpgOrdTMsg = new SHPG_ORDTMsg();
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(shpgOrdTMsg.soNum, sMsgALine.soNum_A1.getValue());

        shpgOrdTMsg = (SHPG_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdTMsg);

        if (shpgOrdTMsg == null) {

            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
            return false;
        }

        // anyone update
        if (!ZYPDateUtil.isSameTimeStamp(sMsgALine.ezUpTime_SO.getValue(), sMsgALine.ezUpTimeZone_SO.getValue(), shpgOrdTMsg.ezUpTime.getValue(), shpgOrdTMsg.ezUpTimeZone.getValue())) {

            sMsgALine.xxChkBox_A1.setErrorInfo(1, NLBL3070Constant.NLBM0009E);
            return false;
        }

        return true;
    }

    /**
     * Check API Result
     * @param cMsg NLBL3070CMsg
     * @param errChkItem EZDSStringItem
     * @param apiErrMsgList List<String>
     * @return boolean true : OK, false : NG
     */
    public static boolean chkApiRslt(NLBL3070CMsg cMsg, EZDSStringItem errChkItem, List<String> apiErrMsgList) {

        if (apiErrMsgList != null && !apiErrMsgList.isEmpty()) {

            for (String msgId : apiErrMsgList) {

                if (ZYPCommonFunc.hasValue(msgId)) {

                    cMsg.setMessageInfo(msgId, null);

                    if (msgId.endsWith("E")) {

                        errChkItem.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * hasWarehousePermission
     * @param cMsg NLBL3070CMsg
     * @param rtlWhCd String
     * @param userId String
     * @param functionList List<String>
     * @return true:OK false:NG
     */
    public static boolean hasWarehousePermission(NLBL3070CMsg cMsg, String rtlWhCd, String userId, List<String> functionList) {

        // All Wh Permission User
        if (chkWarehousePermtReq(functionList)) {

            return true;
        }

        HashMap<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        param.put("schdCoordPsnCd", userId);
        param.put("salesDate", cMsg.slsDt.getValue());

        S21SsmEZDResult result = NLBL3070Query.getInstance().getPermissionWhList(param);

        if (result.isCodeNormal()) {

            List<String> permissionWhList = (List<String>) result.getResultObject();

            if (permissionWhList.isEmpty()) {

                return false;
            }

            // All Wh permission
            if (permissionWhList.contains("*")) {

                return true;
            }

            if (permissionWhList.contains(rtlWhCd)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Checks for Warehouse Permission Required.
     * @param functionList List<String>
     * @return true, if successful
     */
    public static boolean chkWarehousePermtReq(List<String> functionList) {
        return functionList.contains(NLBL3070Constant.ROLE_ALL_WH_PERMISSION);
    }

    /**
     * getSerListText
     * @param glblCmpyCd String
     * @param sMsgBLine NLBL3070_BSMsg
     * @param fMsg NLBL3070F01FMsg
     * @param shipFlg String
     * @return String
     */
    public static String getSerListText(String glblCmpyCd, NLBL3070_BSMsg sMsgBLine, NLBL3070F01FMsg fMsg, String shipFlg) {

        S21SsmEZDResult ssmSerResult = NLBL3070Query.getInstance().getSerNumListGlobal(glblCmpyCd, sMsgBLine, fMsg, shipFlg);

        if (ssmSerResult.isCodeNormal()) {

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmSerResult.getResultObject();

            int maxLength = 0;

            if (sMsgBLine != null) {

                maxLength = sMsgBLine.getAttr("serNum_B1").getDigit();

            } else {

                maxLength = fMsg.getAttr("serNum").getDigit();
            }

            StringBuilder sb = new StringBuilder();
            int serCnt = 0;    // 2019/09/02 T.Ogura [QC#52581,ADD]

            for (Map<String, Object> resultMap : resultList) {

                // START 2019/10/09 T.Ogura [QC#54059,MOD]
//                // START 2019/09/02 T.Ogura [QC#52581,ADD]
//                if (sMsgBLine.xxShipQty_B1.getValueInt() <= serCnt) {
//                    break;
//                }
//                // END   2019/09/02 T.Ogura [QC#52581,ADD]
                if (sMsgBLine != null) {
                    if (sMsgBLine.xxShipQty_B1.getValueInt() == serCnt) {
                        break;
                    }
                } else {
                    if (fMsg.shipQty.getValueInt() == serCnt) {
                        break;
                    }
                }
                // END   2019/10/09 T.Ogura [QC#54059,MOD]

                if (sb.length() > 0) {

                    sb.append(",");
                }

                sb.append((String) resultMap.get("SER_NUM"));

                if (sb.length() >= maxLength) {

                    return sb.toString().substring(0, maxLength);
                }
                serCnt++;    // 2019/09/02 T.Ogura [QC#52581,ADD]
            }

            if (sb.length() > 0) {

                return sb.toString();
            }
        }

        return null;
    }

    // START 2018/01/09 M.Naito [QC#18889,ADD]
    /**
     * chkDeliveryScheduleMail
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return boolean
     */
    public static boolean chkDeliveryScheduleMail(NLBL3070CMsg cMsg, String soNum) {

        S21SsmEZDResult result = NLBL3070Query.getInstance().chkDeliveryScheduleMail(cMsg,soNum);

        if (!result.isCodeNormal()) {
            return false;
        }

        BigDecimal chkDeliveryScheduleMail = (BigDecimal) result.getResultObject();

        if (chkDeliveryScheduleMail != null) {
            return true;
        }
        return false;
    }

    // START 2018/10/03 M.Naito [QC#28539,ADD]
    /**
     * chkChngTechMeetTheTruck
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return boolean
     */
    // START 2019/04/23 K.Kitachi [QC#31245, MOD]
//    public static boolean chkChngTechMeetTheTruck(NLBL3070CMsg cMsg, String techMeetTruckFlg, String soNum, String bfTechMeetTruckFlg) {
    public static boolean chkChngTechMeetTheTruck(NLBL3070CMsg cMsg, String techMeetTruckFlg, String soNum) {
    // END 2019/04/23 K.Kitachi [QC#31245, MOD]

        // START 2019/04/23 K.Kitachi [QC#31245, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(techMeetTruckFlg) && ZYPConstant.FLG_OFF_N.equals(bfTechMeetTruckFlg)) {
        if (ZYPConstant.FLG_ON_Y.equals(techMeetTruckFlg)) {
        // END 2019/04/23 K.Kitachi [QC#31245, MOD]
 
            S21SsmEZDResult result = NLBL3070Query.getInstance().chkChngTechMeetTheTruck(cMsg, soNum);
            if (!result.isCodeNormal()) {
                return false;
            }
            BigDecimal chkChngTechMeetTheTruck = (BigDecimal) result.getResultObject();
            if (chkChngTechMeetTheTruck != null) {
                return true;
            }
        }
        return false;
    }
    // END 2018/10/03 M.Naito [QC#28539,ADD]

    /**
     * getSvcBrMngrEmlAddr
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     */
    public static String getSvcBrMngrEmlAddr(NLBL3070CMsg cMsg, String soNum) {

        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getSvcBrMngrEmlAddr(cMsg, soNum);

        if (!ssmResult.isCodeNormal()) {
            return null;
        }

        Map<String, String> svcBrMngrEmlAddrMap = (Map<String, String>) ssmResult.getResultObject();
        return svcBrMngrEmlAddrMap.get("EML_ADDR").toString();
    }

    /**
     * getSvcBrMngrEmlAddr
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @return String
     */
    public static Map<String, Object> getDeliveryScheduleMailInfo(NLBL3070CMsg cMsg, String soNum) {

        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getDeliveryScheduleMailInfo(cMsg, soNum);

        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        return (Map<String, Object>) resultList.get(0);
    }

    /**
     * sendMail
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @param strToAddr String
     * @return void
     */
    // START 2018/10/03 M.Naito [QC#28539,MOD]
//    public static void sendMail(NLBL3070CMsg cMsg, String soNum, String strToAddr) {
    // START 2019/04/23 K.Kitachi [QC#31245, MOD]
//    public static String sendMail(NLBL3070CMsg cMsg, String soNum, String strToAddr) {
    public static String sendMail(NLBL3070CMsg cMsg, String soNum, String strToAddr, boolean isSendMail) {
    // END 2019/04/23 K.Kitachi [QC#31245, MOD]
        String afterErlStartTs = null;
    // END 2018/10/03 M.Naito [QC#28539,MOD]

        // START 2019/04/23 K.Kitachi [QC#31245, ADD]
        // get Mail Informations
        Map<String, Object> mailInfoMap = getDeliveryScheduleMailInfo(cMsg, soNum);

        // QC#53743
        if (mailInfoMap == null) {
            return afterErlStartTs;
        }

        String beforeSystemTimeZoneTs = (String) mailInfoMap.get("BEFORE_ERL_START_TS");
        String beforesystemTimeZoneTm = "";
        if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("BEFORE_SCHD_DELY_DT"))) {
            beforeSystemTimeZoneTs = (String) mailInfoMap.get("BEFORE_SCHD_DELY_DT");
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("BEFORE_SCHD_DELY_TM"))) {
                beforesystemTimeZoneTm = (String) mailInfoMap.get("BEFORE_SCHD_DELY_TM");
                beforeSystemTimeZoneTs = beforeSystemTimeZoneTs + beforesystemTimeZoneTm;
            } else {
                // Get Constant Value of Early Start Time
                beforesystemTimeZoneTm = getMeetTruckErlStrtTm(cMsg.glblCmpyCd.getValue(), (String) mailInfoMap.get("FLD_SVC_BR_CD"));
                beforeSystemTimeZoneTs = beforeSystemTimeZoneTs + beforesystemTimeZoneTm;
                SvcTimeZoneInfo converSystBeforeErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, beforeSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
                if (converSystBeforeErlStartTs != null) {
                    beforeSystemTimeZoneTs = converSystBeforeErlStartTs.getDateTime().substring(0, 12);
                }
            }
        }
        SvcTimeZoneInfo convertBeforeErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, beforeSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
        String beforeErlStartDate = "";
        String beforeErlStartTime = "";
        if (convertBeforeErlStartTs != null) {
            beforeErlStartDate = convertBeforeErlStartTs.getDateTime().substring(0, 8);
            beforeErlStartTime = convertBeforeErlStartTs.getDateTime().substring(8, 12);
        }

        String afterSystemTimeZoneTs = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        String afterSystemTimeZoneTm = "";
        if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("AFTER_SCHD_DELY_DT"))) {
            afterSystemTimeZoneTs = (String) mailInfoMap.get("AFTER_SCHD_DELY_DT");
        }
        if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("AFTER_SCHD_DELY_TM"))) {
            afterSystemTimeZoneTm = (String) mailInfoMap.get("AFTER_SCHD_DELY_TM");
            afterSystemTimeZoneTs = afterSystemTimeZoneTs + afterSystemTimeZoneTm;
        } else {
            // Get Constant Value of Early Start Time
            afterSystemTimeZoneTm = getMeetTruckErlStrtTm(cMsg.glblCmpyCd.getValue(), (String) mailInfoMap.get("FLD_SVC_BR_CD"));
            afterSystemTimeZoneTs = afterSystemTimeZoneTs + afterSystemTimeZoneTm;
            SvcTimeZoneInfo convertSysAfterErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, afterSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
            if (convertSysAfterErlStartTs != null) {
                afterSystemTimeZoneTs = convertSysAfterErlStartTs.getDateTime().substring(0, 12);
            }
        }

        SvcTimeZoneInfo convertAfterErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, afterSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
        String afterErlStartDate = "";
        String afterErlStartTime = "";
        if (convertAfterErlStartTs != null) {
            afterErlStartDate = convertAfterErlStartTs.getDateTime().substring(0, 8);
            afterErlStartTime = convertAfterErlStartTs.getDateTime().substring(8, 12);
            afterErlStartTs = convertAfterErlStartTs.getDateTime();
        }

        String beforeDateTime = convertDateTxt(beforeErlStartDate) + "/" + beforeErlStartTime;
        String afterDateTime = convertDateTxt(afterErlStartDate) + "/" + afterErlStartTime;

        if (!isSendMail || !ZYPCommonFunc.hasValue(strToAddr)) {
            return afterErlStartTs;
        }
        // END 2019/04/23 K.Kitachi [QC#31245, ADD]

        S21MailGroup fromGrp = new S21MailGroup(cMsg.glblCmpyCd.getValue(), NLBL3070Constant.MAIL_GROUP_ID_FROM);
        fromGrp.setMailKey1(NLBL3070Constant.MAIL_FROM_GRP);
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();

        S21Mail mail = new S21Mail(cMsg.glblCmpyCd.getValue());

        if (fromAddrList.size() > 0) {

            mail.setFromAddress(fromAddrList.get(0));

            S21MailAddress toAddr = new S21MailAddress(cMsg.glblCmpyCd.getValue(), strToAddr);
            mail.setToAddress(toAddr);

            S21MailTemplate template = new S21MailTemplate(cMsg.glblCmpyCd.getValue(), NLBL3070Constant.MAIL_TEMPLATE_ID);

            if (template == null) {
                cMsg.setMessageInfo(NLBL3070Constant.NLBM1065E);
                // START 2018/10/03 M.Naito [QC#28539,MOD]
//                return;
                return afterErlStartTs;
                // END 2018/10/03 M.Naito [QC#28539,MOD]
            }

            // START 2019/04/23 K.Kitachi [QC#31245, DEL]
//            // get Mail Informations
//            Map<String, Object> mailInfoMap = getDeliveryScheduleMailInfo(cMsg, soNum);
//
//            // START 2018/01/19 K.Ochiai [QC#18889,MOD]
//            String beforeSystemTimeZoneTs = (String) mailInfoMap.get("BEFORE_ERL_START_TS");
//            String beforesystemTimeZoneTm = "";
//            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("BEFORE_SCHD_DELY_DT"))) {
//                beforeSystemTimeZoneTs = (String) mailInfoMap.get("BEFORE_SCHD_DELY_DT");
//                if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("BEFORE_SCHD_DELY_TM"))) {
//                    beforesystemTimeZoneTm = (String) mailInfoMap.get("BEFORE_SCHD_DELY_TM");
//                    beforeSystemTimeZoneTs = beforeSystemTimeZoneTs + beforesystemTimeZoneTm;
//                } else {
//                    // Get Constant Value of Early Start Time
//                    beforesystemTimeZoneTm = getMeetTruckErlStrtTm(cMsg.glblCmpyCd.getValue(), (String) mailInfoMap.get("FLD_SVC_BR_CD"));
//                    beforeSystemTimeZoneTs = beforeSystemTimeZoneTs + beforesystemTimeZoneTm;
//                    SvcTimeZoneInfo converSystBeforeErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, beforeSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
//                    if (converSystBeforeErlStartTs != null) {
//                        beforeSystemTimeZoneTs = converSystBeforeErlStartTs.getDateTime().substring(0, 12);
//                    }
//                }
//            }
//            SvcTimeZoneInfo convertBeforeErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, beforeSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
//            String beforeErlStartDate = "";
//            String beforeErlStartTime = "";
//            if (convertBeforeErlStartTs != null) {
//                beforeErlStartDate = convertBeforeErlStartTs.getDateTime().substring(0, 8);
//                beforeErlStartTime = convertBeforeErlStartTs.getDateTime().substring(8, 12);
//            }
//
//            String afterSystemTimeZoneTs = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
//            String afterSystemTimeZoneTm = "";
//            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("AFTER_SCHD_DELY_DT"))) {
//                afterSystemTimeZoneTs = (String) mailInfoMap.get("AFTER_SCHD_DELY_DT");
//            }
//            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("AFTER_SCHD_DELY_TM"))) {
//                afterSystemTimeZoneTm = (String) mailInfoMap.get("AFTER_SCHD_DELY_TM");
//                afterSystemTimeZoneTs = afterSystemTimeZoneTs + afterSystemTimeZoneTm;
//            } else {
//                // Get Constant Value of Early Start Time
//                afterSystemTimeZoneTm = getMeetTruckErlStrtTm(cMsg.glblCmpyCd.getValue(), (String) mailInfoMap.get("FLD_SVC_BR_CD"));
//                afterSystemTimeZoneTs = afterSystemTimeZoneTs + afterSystemTimeZoneTm;
//                SvcTimeZoneInfo convertSysAfterErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, afterSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
//                if (convertSysAfterErlStartTs != null) {
//                    afterSystemTimeZoneTs = convertSysAfterErlStartTs.getDateTime().substring(0, 12);
//                }
//            }
//
//            SvcTimeZoneInfo convertAfterErlStartTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, afterSystemTimeZoneTs, (String) mailInfoMap.get("CTRY_CD"), (String) mailInfoMap.get("POST_CD"));
//            String afterErlStartDate = "";
//            String afterErlStartTime = "";
//            if (convertAfterErlStartTs != null) {
//                afterErlStartDate = convertAfterErlStartTs.getDateTime().substring(0, 8);
//                afterErlStartTime = convertAfterErlStartTs.getDateTime().substring(8, 12);
//                // START 2018/10/03 M.Naito [QC#28539,ADD]
//                afterErlStartTs = convertAfterErlStartTs.getDateTime();
//                // END 2018/10/03 M.Naito [QC#28539,ADD]
//            }
//
//            String beforeDateTime = convertDateTxt(beforeErlStartDate) + "/" + beforeErlStartTime;
//            String afterDateTime = convertDateTxt(afterErlStartDate) + "/" + afterErlStartTime;
//            // END 2018/01/19 K.Ochiai [QC#18889,MOD]
            // END 2019/04/23 K.Kitachi [QC#31245, DEL]
            template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_BEFORE_DATE_TIME, beforeDateTime);
            template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_AFTER_DATE_TIME, afterDateTime);
            template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TASK_NUM, (String) mailInfoMap.get("SVC_TASK_NUM"));
            template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_FSR_NUM, (String) mailInfoMap.get("FSR_NUM"));
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("DS_ACCT_NM"))) {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_CUST_NM, (String) mailInfoMap.get("DS_ACCT_NM"));
            } else {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_CUST_NM, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("ISTL_LOC"))) {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_ISTL_LOC, (String) mailInfoMap.get("ISTL_LOC"));
            } else {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_ISTL_LOC, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("SER_NUM"))) {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_SER_NUM, (String) mailInfoMap.get("SER_NUM"));
            } else {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_SER_NUM, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("PSN_FIRST_NM")) && ZYPCommonFunc.hasValue((String) mailInfoMap.get("PSN_LAST_NM"))) {
                String techNm = (String) mailInfoMap.get("PSN_FIRST_NM");
                if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("PSN_MID_NM"))) {
                    techNm = techNm + " " + (String) mailInfoMap.get("PSN_MID_NM");
                }
                techNm = techNm + " " + (String) mailInfoMap.get("PSN_LAST_NM");
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TECH_NM, techNm);
            } else {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TECH_NM, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("EML_ADDR"))) {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TECH_EML_ADDR, (String) mailInfoMap.get("EML_ADDR"));
            } else {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TECH_EML_ADDR, "");
            }
            if (ZYPCommonFunc.hasValue((String) mailInfoMap.get("OFC_TEL_NUM"))) {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TECH_PHONE_NUM, (String) mailInfoMap.get("OFC_TEL_NUM"));
            } else {
                template.setTemplateParameter(NLBL3070Constant.EMAIL_PARAM_TECH_PHONE_NUM, "");
            }

            // Set mail subject
            mail.setSubject(template.getSubject(NLBL3070Constant.ML_LANG), NLBL3070Constant.ML_LANG_CD);
            mail.setMailTemplate(template);

            // Post
            mail.postMail();
        }
        // START 2018/10/03 M.Naito [QC#28539,ADD]
        return afterErlStartTs;
        // END 2018/10/03 M.Naito [QC#28539,ADD]
    }

    /**
     * convertDateTxt
     * @param strDate String
     * @return String
     */
    public static String convertDateTxt(String strDate) {
        SimpleDateFormat dateFormatIn = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        SimpleDateFormat dateFormatOut = new SimpleDateFormat(NLBL3070Constant.DATE_FORMAT_DDMMMYYYY, Locale.US);
        // START 2018/01/19 K.Ochiai [QC#18889,ADD]
        String result = "";
        if (!ZYPCommonFunc.hasValue(strDate)) {
            return result;
        }
        // END 2018/01/19 K.Ochiai [QC#18889,ADD]
        try {
            Date resultDate = dateFormatIn.parse(strDate);
            result = dateFormatOut.format(resultDate);
        } catch (ParseException e) {
            return result;
        }
        return result;
    }
    // END 2018/01/09 M.Naito [QC#18889,ADD]
    // START 2018/01/19 K.Ochiai [QC#18889,ADD]
    private static String getMeetTruckErlStrtTm(String glblCmpyCd, String svcByLineBizTp) {
        String meetTruckErlStrtTm = null;

        if (LINE_BIZ_TP.LFS.equals(svcByLineBizTp)) {
            meetTruckErlStrtTm = ZYPCodeDataUtil.getVarCharConstValue(NLBL3070Constant.VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_LFS, glblCmpyCd);
        } else if (LINE_BIZ_TP.PPS.equals(svcByLineBizTp)) {
            meetTruckErlStrtTm = ZYPCodeDataUtil.getVarCharConstValue(NLBL3070Constant.VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_PPS, glblCmpyCd);
        } else {
            meetTruckErlStrtTm = ZYPCodeDataUtil.getVarCharConstValue(NLBL3070Constant.VAR_CHAR_CONST_MEET_TRUCK_ERL_START_TM_ESS, glblCmpyCd);
        }
        return meetTruckErlStrtTm;
    }
    // END 2018/01/19 K.Ochiai [QC#18889,ADD]
    
    /**
     * checkCustCarrSvcLvlRelation
     * QC#23726 Add method.
     * @param cMsg NLBL3070CMsg
     * @param cpoOrdNum String
     * @param shpgSvcLvlCd String
     * @param carrCd String
     * @param dsAcctNum String
     * @return boolean if error then return true.
     */
    public static boolean checkCustCarrSvcLvlRelation(NLBL3070CMsg cMsg, String cpoOrdNum, String shpgSvcLvlCd, String carrCd, String dsAcctNum) {

        // Get CPO
        CPOTMsg cpo = NLBL3070Query.getInstance().getCPOTmsg(cMsg.glblCmpyCd.getValue(), cpoOrdNum);

        // Get Carrier Account Service Level Code
        S21SsmEZDResult result = NLBL3070Query.getInstance().getCarrSvcLvlCdList(cMsg, shpgSvcLvlCd, carrCd);
        String carrAcctSvcLvlCd = null;
        if (result.isCodeNormal()) {
            List<String> caslList = (List<String>) result.getResultObject();
            // Search Primary key.
            carrAcctSvcLvlCd = caslList.get(0);
        }

        // Check Customer Carrier Service Level Relation
        return NWXC150001DsCheck.checkCustCarrSvcLvlRelation(cMsg.glblCmpyCd.getValue()//
                , cpo.dsOrdCatgCd.getValue()//
                , cpo.dsOrdTpCd.getValue()//
                , cpo.dsOrdRsnCd.getValue()//
                , dsAcctNum//
                , carrAcctSvcLvlCd//
                , null);
    }

    // START 2018/10/03 M.Naito [QC#28539,MOD]
    /**
     * callSendTaskInfoAPI
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     * @param afterErlStartTs String
     * @return boolean
     */
    public static NSZC043001PMsg updateSvcTaskErlStartTs(NLBL3070CMsg cMsg, String soNum, String afterErlStartTs, String userId) {

        NSZC043001PMsg pMsg = new NSZC043001PMsg();
        if (!ZYPCommonFunc.hasValue(afterErlStartTs)) {
            return pMsg;
        }
        S21SsmEZDResult result = NLBL3070Query.getInstance().getSvcTaskInfoFromSoNum(cMsg, soNum);
        Map<String, Object> svcTaskInfoMap = (Map<String, Object>) result.getResultObject();
        if (svcTaskInfoMap == null) {
            return pMsg;
        }
        String svcTaskNum = (String) svcTaskInfoMap.get("SVC_TASK_NUM");
        if (!ZYPCommonFunc.hasValue(svcTaskNum)) {
            return pMsg;
        }

        // get late start time
        BigDecimal meetTruckRspTmMnAot = ZYPCodeDataUtil.getNumConstValue(NLBL3070Constant.NUM_CONST_MEET_TRUCK_RSP_TM_MN_AOT, cMsg.glblCmpyCd.getValue());
        if (!ZYPCommonFunc.hasValue(meetTruckRspTmMnAot)) {
            meetTruckRspTmMnAot = NLBL3070Constant.DEF_MEET_TRUCK_RSP_TM_MN_AOT;
        }
        String erlStartDt = afterErlStartTs.substring(0, 8);
        String erlStartTm = afterErlStartTs.substring(8, 12);
        String afterLateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(cMsg.glblCmpyCd.getValue(), meetTruckRspTmMnAot, (BigDecimal) svcTaskInfoMap.get("SVC_MACH_MSTR_PK"), erlStartDt, convertTimeForHHmmss(erlStartTm),
                false);

        if (ZYPCommonFunc.hasValue(afterLateStartTs) && ZYPCommonFunc.hasValue(afterLateStartTs)) {
            pMsg = callFsrUpdateAPI(cMsg, svcTaskNum, userId, afterErlStartTs, afterLateStartTs);
        }
        return pMsg;
    }

    /**
     * convertTimeForHHmmss
     * @param time String time
     * @return String
     */
    public static String convertTimeForHHmmss(String time) {
        if (!ZYPCommonFunc.hasValue(time) || time.length() != NLBL3070Constant.HOUR_MINUTE_LEN) {
            time = NLBL3070Constant.DEF_HHMMSS;
        } else {
            time = time + "00";
        }
        return time;
    }

    /**
     * callFsrUpdateAPI
     * @param cMsg NLBL3070CMsg
     * @param svcTaskNum String
     * @param userId String
     * @param erlStartTs String
     * @param lateStartTs String
     * @return NSZC043001PMsg
     */
    public static NSZC043001PMsg callFsrUpdateAPI(NLBL3070CMsg cMsg, String svcTaskNum, String userId, String erlStartTs, String lateStartTs) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
        svcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKey(svcTaskTMsg);

        NSZC043001PMsg fsrUpdPMsg = new NSZC043001PMsg();
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.slsDt, cMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.fsrNum, svcTaskTMsg.fsrNum.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.userId, userId);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.xxRqstSendFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.svcMachMstrPk, svcTaskTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).svcTaskNum, svcTaskNum);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).erlStartTs, erlStartTs);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).lateStartTs, lateStartTs);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).ovrdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fsrUpdPMsg.taskList.no(0).xxOrigFollUpTaskFlg, ZYPConstant.FLG_OFF_N);
        fsrUpdPMsg.taskList.setValidCount(1);

        NSZC043001 api = new NSZC043001();
        api.execute(fsrUpdPMsg, ONBATCH_TYPE.ONLINE);

        return fsrUpdPMsg;
    }
    // END 2018/10/03 M.Naito [QC#28539,MOD]


    // START 2018/10/25 M.Naito [QC#28867,ADD]
    public static Map<String, String> setParamsForLog(NLBL3070CMsg cMsg) {
        Map<String, String> setParams = new HashMap<String, String>();
        setMap(setParams, "soNum", cMsg.soNum.getValue());
        setMap(setParams, "rtlWhCd", cMsg.rtlWhCd.getValue());
        setMap(setParams, "trxHdrNum", cMsg.trxHdrNum.getValue());
        setMap(setParams, "shipToCustCd", cMsg.shipToCustCd.getValue());
        setMap(setParams, "rddDt_FR", cMsg.rddDt_FR.getValue());
        setMap(setParams, "rddDt_TO", cMsg.rddDt_TO.getValue());
        setMap(setParams, "wmsDropFlg_Y", cMsg.wmsDropFlg_Y.getValue());
        setMap(setParams, "wmsDropFlg_N", cMsg.wmsDropFlg_N.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcConfigMstrPk.getValue())) {
            setMap(setParams, "svcConfigMstrPk", cMsg.svcConfigMstrPk.getValue().toString());
        }
        setMap(setParams, "delyReqFlg_Y", cMsg.delyReqFlg_Y.getValue());
        setMap(setParams, "delyReqFlg_N", cMsg.delyReqFlg_N.getValue());
        setMap(setParams, "shpgSvcLvlCd", cMsg.shpgSvcLvlCd.getValue());
        setMap(setParams, "schdCoordStsCd", cMsg.schdCoordStsCd.getValue());
        setMap(setParams, "carrCd", cMsg.carrCd.getValue());
        setMap(setParams, "schdCoordPsnCd", cMsg.schdCoordPsnCd.getValue());
        setMap(setParams, "schdDelyDt_FR", cMsg.schdDelyDt_FR.getValue());
        setMap(setParams, "schdDelyDt_TO", cMsg.schdDelyDt_TO.getValue());
        setMap(setParams, "schdCarrPickUpDt_FR", cMsg.schdCarrPickUpDt_FR.getValue());
        setMap(setParams, "schdCarrPickUpDt_TO", cMsg.schdCarrPickUpDt_TO.getValue());
        setMap(setParams, "dsOrdCatgCd", cMsg.dsOrdCatgCd.getValue());
        setMap(setParams, "dsOrdTpCd", cMsg.dsOrdTpCd.getValue());
        setMap(setParams, "t_MdlNm", cMsg.t_MdlNm.getValue());
        setMap(setParams, "dsSoLineStsCd", cMsg.dsSoLineStsCd.getValue());
        setMap(setParams, "rtlSwhCd", cMsg.rtlSwhCd.getValue());
        setMap(setParams, "actlDelyDt_FR", cMsg.actlDelyDt_FR.getValue());
        setMap(setParams, "actlDelyDt_TO", cMsg.actlDelyDt_TO.getValue());
        setMap(setParams, "bolNum", cMsg.bolNum.getValue());

        return setParams;
    }

    public static Map<String, String> setMap(Map<String, String> map, String key, String value) {
        if (ZYPCommonFunc.hasValue(value)) {
            map.put(key, value);
        }
        return map;
    }
    // END 2018/10/25 M.Naito [QC#28867,ADD]

    // START 2019/04/23 K.Kitachi [QC#31245, ADD]
    /**
     * getEmlAddrFromTaskSvcBrMgr
     * @param cMsg NLBL3070CMsg
     * @param soNum String
     */
    public static String getEmlAddrFromTaskSvcBrMgr(NLBL3070CMsg cMsg, String soNum) {
        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getEmlAddrFromTaskSvcBrMgr(cMsg, soNum);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }
    // END 2019/04/23 K.Kitachi [QC#31245, ADD]

    // START 2021/05/31 A.Marte [QC#58786, ADD]
    /**
     * getInvtyTrxHdrNumFromSO
     * @param glblCmpyCd String
     * @param soNum String
     */
    public static String getInvtyTrxHdrNumFromSO(String glblCmpyCd, String soNum) {
        S21SsmEZDResult ssmResult = NLBL3070Query.getInstance().getInvtyTrxHdrNumFromSO(glblCmpyCd, soNum);
        if (!ssmResult.isCodeNormal()) {
            return null;
        }
        return (String) ssmResult.getResultObject();
    }
    // END 2021/05/31 A.Marte [QC#58786, ADD]

    // START 2022/12/09 T.Kuroda [QC#60810, ADD]
    /**
     * isWMSWarehouseCheck
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean true:Exist false:Not Exist
     */
    public static boolean isWMSWarehouseCheck(String glblCmpyCd, String rtlWhCd) {
        S21SsmEZDResult result = NLBL3070Query.getInstance().getCountWMSWarehouseList(glblCmpyCd, rtlWhCd);

        BigDecimal count = BigDecimal.ZERO;
        if (result.isCodeNormal()) {
            count = (BigDecimal) result.getResultObject();
        }

        return BigDecimal.ZERO.compareTo(count) != 0;
    }
    // END   2022/12/09 T.Kuroda [QC#60810, ADD]
}
