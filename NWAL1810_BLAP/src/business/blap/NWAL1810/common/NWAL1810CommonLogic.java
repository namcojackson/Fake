/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1810.common;

import static business.blap.NWAL1810.constant.NWAL1810Constant.LINE_STR_PTN;
import static business.blap.NWAL1810.constant.NWAL1810Constant.NWAM0006I;
import static business.blap.NWAL1810.constant.NWAL1810Constant.NZZM0001W;
import static business.blap.NWAL1810.constant.NWAL1810Constant.ORD_PRFT_TRX_CATG_SHELL;
import static business.blap.NWAL1810.constant.NWAL1810Constant.PERIOD;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SCRN_ID_ORDER_ENTRY;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SCRN_ID_QUOTE_ENTRY;
import static business.blap.NWAL1810.constant.NWAL1810Constant.SCRN_ID_SCHD_AGMT;
import static business.blap.NWAL1810.constant.NWAL1810Constant.YYYYMMDDHHMISS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDMsg;
import business.blap.NWAL1810.NWAL1810CMsg;
import business.blap.NWAL1810.NWAL1810Query;
import business.blap.NWAL1810.NWAL1810SMsg;
import business.blap.NWAL1810.NWAL1810_ASMsg;
import business.blap.NWAL1810.NWAL1810_ASMsgArray;
import business.blap.NWAL1810.NWAL1810_BSMsg;
import business.blap.NWAL1810.NWAL1810_BSMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1810CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/01   Fujitsu         M.Yamada        Update          QC#5283
 * 2016/04/08   Fujitsu         M.Yamada        Update          QC#6731
 * 2016/04/13   Fujitsu         M.Yamada        Update          QC#6965
 * 2022/10/20   Hitachi         H.Watanabe      Update          QC#60258
 *</pre>
 */
public class NWAL1810CommonLogic {

    /**
     * Page Setting Init Summary
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    public static void pageSettingInitSummary(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NWAL1810Query.getInstance().getSummaryList(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.A.length();
            }

            // Search results posting
            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Settings page item
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum_A.clear();
            bizMsg.xxPageShowToNum_A.clear();
            bizMsg.xxPageShowOfNum_A.clear();
        }
    }

    /**
     * Page Setting Init Detail
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    public static void pageSettingInitDetail(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        S21SsmEZDResult ssmResult = NWAL1810Query.getInstance().getSummaryList(bizMsg, glblMsg);

        if (ssmResult.isCodeNormal()) {

            // Maximum acquisition number exceeded message
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.B.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.B.length();
            }

            // Search results posting
            int i = 0;
            for (; i < glblMsg.B.getValidCount(); i++) {
                if (i == bizMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i), null);
            }
            bizMsg.B.setValidCount(i);

            // Settings page item
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            bizMsg.xxPageShowFromNum_B.clear();
            bizMsg.xxPageShowToNum_B.clear();
            bizMsg.xxPageShowOfNum_B.clear();
        }
    }

    /**
     * Page Setting Page Next Summary
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    public static void pageSettingPageNextSummary(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum_A.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    /**
     * Page Setting Page Next Detail
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    public static void pageSettingPageNextDetail(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_B.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.B.length(); i++) {
            if (i < glblMsg.B.getValidCount()) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum_B.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum_B.setValue(pagenationFrom + bizMsg.B.getValidCount());
    }

    /**
     * Page Setting Page Preview Summary
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    public static void pageSettingPagePrevSummary(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagination items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum_A.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_A.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);
    }

    /**
     * Page Setting Page Preview Detail
     * @param bizMsg NWAL1810CMsg
     * @param glblMsg NWAL1810SMsg
     */
    public static void pageSettingPagePrevDetail(NWAL1810CMsg bizMsg, NWAL1810SMsg glblMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_B.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.B.length(); i++) {
            if (i < glblMsg.B.getValidCount()) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagination items
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum_B.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum_B.setValue(pagenationFrom + bizMsg.B.getValidCount() - 1);
    }

    /**
     * <pre>
     * format time stamp from YYYYMMDDHH24MISSFFF to MM/DD/YYYY HH24:MI:SS
     * </pre>
     * @param aGlblMsgAry NWAL1810_ASMsgArray
     */
    public static void formatTimestampForAGlblMsg(NWAL1810_ASMsgArray aGlblMsgAry) {
        for (int i = 0; i < aGlblMsgAry.getValidCount(); i++) {
            NWAL1810_ASMsg aGlblMsg = aGlblMsgAry.no(i);
            String timestamp = S21StringUtil.subStringByLength(aGlblMsg.xxDtTm_A0.getValue(), 0, YYYYMMDDHHMISS.length());
            ZYPEZDItemValueSetter.setValue(//
                    aGlblMsg.xxDtTm_A0, ZYPDateUtil.formatEzd14ToDisp(timestamp, true, true, true));
        }
    }

    /**
     * <pre>
     * format time stamp from YYYYMMDDHH24MISSFFF to MM/DD/YYYY HH24:MI:SS
     * </pre>
     * @param bGlblMsgAry NWAL1810_BSMsgArray
     */
    public static void formatTimestampForBGlblMsg(NWAL1810_BSMsgArray bGlblMsgAry) {
        for (int i = 0; i < bGlblMsgAry.getValidCount(); i++) {
            NWAL1810_BSMsg bGlblMsg = bGlblMsgAry.no(i);
            String timestamp = S21StringUtil.subStringByLength(bGlblMsg.xxDtTm_B0.getValue(), 0, YYYYMMDDHHMISS.length());
            ZYPEZDItemValueSetter.setValue(//
                    bGlblMsg.xxDtTm_B0, ZYPDateUtil.formatEzd14ToDisp(timestamp, true, true, true));
        }
    }

    /**
     * <pre>
     * convLineStrForAGlblMsg   e.g. from 001.001 to 1.1
     * </pre>
     * @param aGlblMsgAry NWAL1810_ASMsgArray
     * @param xxViewChngLogSrchNum (CPO_ORD_NUM)
     * @param trnsnOrigScrnId screen id of transition from application.
     */
    public static void convLineStrForAGlblMsg(//
            NWAL1810_ASMsgArray aGlblMsgAry, String xxViewChngLogSrchNum, String trnsnOrigScrnId) {
        Pattern p = Pattern.compile(LINE_STR_PTN);
        for (int i = 0; i < aGlblMsgAry.getValidCount(); i++) {
            NWAL1810_ASMsg aGlblMsg = aGlblMsgAry.no(i);
            String fromStr = aGlblMsg.docId_A0.getValue();
            Matcher m = p.matcher(fromStr);
            if (m.find()) {
                String toStr = getDisplayLineStr(//
                        aGlblMsg.docId_A0.getValue() //
                        , xxViewChngLogSrchNum //
                        , trnsnOrigScrnId //
                        , aGlblMsg.ordPrftTrxCatgCd_A0.getValue());
                if (ZYPCommonFunc.hasValue(toStr)) {
                    ZYPEZDItemValueSetter.setValue(aGlblMsg.docId_A0, toStr);
                }
            }
        }
    }

    /**
     * <pre>
     * convLineStrForBGlblMsg   e.g. from 001.001 to 1.1
     * </pre>
     * @param bGlblMsgAry NWAL1810_BSMsgArray
     * @param xxViewChngLogSrchNum (CPO_ORD_NUM)
     * @param trnsnOrigScrnId screen id of transition from
     * application.
     */
    public static void convLineStrForBGlblMsg(//
            NWAL1810_BSMsgArray bGlblMsgAry, String xxViewChngLogSrchNum, String trnsnOrigScrnId) {
        Pattern p = Pattern.compile(LINE_STR_PTN);
        for (int i = 0; i < bGlblMsgAry.getValidCount(); i++) {
            NWAL1810_BSMsg bGlblMsg = bGlblMsgAry.no(i);
            String fromStr = bGlblMsg.docId_B0.getValue();
            Matcher m = p.matcher(fromStr);
            if (m.find()) {
                String toStr = getDisplayLineStr(//
                        bGlblMsg.docId_B0.getValue() //
                        , xxViewChngLogSrchNum //
                        , trnsnOrigScrnId //
                        , bGlblMsg.ordPrftTrxCatgCd_B0.getValue());
                if (ZYPCommonFunc.hasValue(toStr)) {
                    ZYPEZDItemValueSetter.setValue(bGlblMsg.docId_B0, toStr);
                }
            }
        }
    }

    private static String getDisplayLineStr(//
            String lineStr, String xxViewChngLogSrchNum, String scrnId, String ordPrftTrxCatgCd) {
        String displayLineStr = "";
        List< ? > lineList = S21StringUtil.stringToList(lineStr, PERIOD);

        // case of shell
        if (ORD_PRFT_TRX_CATG_SHELL.equals(ordPrftTrxCatgCd)) {
            for (int i = 0; i < lineList.size(); i++) {
                String str = (String) lineList.get(i);

                String regex = "\\A[-]?[0-9]+\\z";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(str);

                if (m.find()) {
                    str = Integer.valueOf(str).toString();
                }

                if (i == lineList.size() - 1) {
                    displayLineStr = displayLineStr + str;

                } else {
                    displayLineStr = displayLineStr + str + PERIOD;
                }
            }
            return displayLineStr;
        }


        if (lineList.size() < 2) {
            return null;
        }

        for (int i = 0; i < lineList.size() - 2; i++) {
            String str = (String) lineList.get(0);
            displayLineStr = displayLineStr + str + PERIOD;
        }

        String sqlId = "getDisplayLineStr";
        if (scrnId.startsWith(SCRN_ID_ORDER_ENTRY)) {
            sqlId = "getDisplayLineStr";
        } else if (scrnId.startsWith(SCRN_ID_QUOTE_ENTRY)) {
            sqlId = "getDisplayLineStrForQuote";
        } else if (scrnId.startsWith(SCRN_ID_SCHD_AGMT)) {
            sqlId = "getDisplayLineStrForSchedule";
        }
        String outboundFlg = ZYPConstant.FLG_OFF_N;
        if (!ORD_PRFT_TRX_CATG.INBOUND.equals(ordPrftTrxCatgCd)) {
            outboundFlg = ZYPConstant.FLG_ON_Y;
        }
        S21SsmEZDResult ssmResult //
        = NWAL1810Query.getInstance().getDisplayLineStr(//
                (String) lineList.get(lineList.size() - 2) //
                , (String) lineList.get(lineList.size() - 1) //
                , xxViewChngLogSrchNum //
                , outboundFlg, sqlId);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        List<String> strList = (List<String>) ssmResult.getResultObject();
        return displayLineStr + strList.get(0);
    }

    // 2022/10/20 QC#60258 Add Start
    public static String[] formatDocId(String docId){
        String[] docIdList = docId.split(",");
        List<String> nullRemoveList = new ArrayList<String>(Arrays.asList(docIdList));
        nullRemoveList.removeAll(Collections.singleton(null));
        nullRemoveList.removeAll(Collections.singleton(""));
        String[] returnDocIdList = {};
        if(nullRemoveList.size() > 0 || nullRemoveList != null){
            returnDocIdList = (String[])nullRemoveList.toArray(new String[nullRemoveList.size()]);
        }
        return returnDocIdList;
    }

    public static void clearSearchParam(NWAL1810CMsg bizMsg){
        bizMsg.xxFromDt.clear();
        bizMsg.xxThruDt.clear();
        bizMsg.eventId.clear();
        bizMsg.ordPrftTrxCatgCd.clear();
        bizMsg.docId.clear();
        bizMsg.xxSrUsrId.clear();
        bizMsg.xxPsnNm.clear();
        bizMsg.bizProcCmntTxt.clear();
        bizMsg.recDbItemAttrbNm.clear();
        bizMsg.xxRecValBefTxt.clear();
        bizMsg.xxRecValAftTxt.clear();
    }
    // 2022/10/20 QC#60258 Add End
}
