/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0160.common;

import static business.blap.NSBL0160.constant.NSBL0160Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

import business.blap.NSBL0160.NSBL0160CMsg;
import business.blap.NSBL0160.NSBL0160Query;
import business.blap.NSBL0160.NSBL0160SMsg;
import business.blap.NSBL0160.constant.NSBL0160Constant;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.db.SVC_MEMO_TPTMsg;
import business.file.NSBL0160F00FMsg;


/** 
 *<pre>
 *
 * Memo Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/11   Hitachi         Y.Igarashi         Create          N/A
 * 2013/11/18   Hitachi         T.Kanasaka         Update       QC2852
 * 2015/10/29   Hitachi         T.Tomita           Update          N/A
 * 2016/02/19   Hitachi         K.Kasai            Update       QC3689
 * 2022/05/11   Hitachi         D.Yoshida          Update       QC#56411
 *</pre>
 */
public class NSBL0160CommonLogic {

    // START 2015/10/29 T.Tomita [N/A, MOD]
    /**
     * 
     * getInitPulldownList
     * 
     * @param glblCmpyCd Global Campany ID
     * @param cMsg NSBL0160CMsg
     */
    public static void getInitPulldownList(String glblCmpyCd, NSBL0160CMsg cMsg) {
        // del start 2016/02/19 CSA Defect#3689
//        // Setup Service Memo Type
//        setSvcMemoPulldownList(glblCmpyCd, cMsg);
        // del start 2016/02/19 CSA Defect#3689
        // Setup Service Memo Reason
        setSearchSvcMemoRsnPulldownList(glblCmpyCd, cMsg);
        // del start 2016/02/24 CSA Defect#3689
//        setAddLineSvcMemoRsnPulldownList(glblCmpyCd, cMsg);
        // del end 2016/02/24 CSA Defect#3689
    }
    // END 2015/10/29 T.Tomita [N/A, MOD]

    // START 2015/10/29 T.Tomita [N/A, ADD]
    // del start 2016/02/19 CSA Defect#3689
//    private static void setSvcMemoPulldownList(String glblCmpyCd, NSBL0160CMsg cMsg) {
//        if (!hasValue(cMsg.svcMemoCatgCd_HD)) {
//            return;
//        }
//
//        SVC_MEMO_TPTMsgArray tMsgAry = getSvcMemoPulldownList(glblCmpyCd, cMsg.svcMemoCatgCd_HD.getValue(), cMsg.svcMemoTpCd_HD.getValue());
//        Map<String, String> tMsgKeys = new HashMap<String, String>();
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoTpCd");
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoTpDescTxt");
//        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoTpCd_SP, cMsg.svcMemoTpDescTxt_SP);
//        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoTpCd_AP, cMsg.svcMemoTpDescTxt_AP);
//        if (hasValue(cMsg.svcMemoTpCd_HD)) {
//            setValue(cMsg.svcMemoTpCd_SC, cMsg.svcMemoTpCd_HD);
//            // add start 2016/02/19 CSA Defect#3689
//            setValue(cMsg.svcMemoTpCd_AC, cMsg.svcMemoTpCd_HD);
//            // add end 2016/02/19 CSA Defect#3689
//        }
//    }
//
//    private static SVC_MEMO_TPTMsgArray getSvcMemoPulldownList(String glblCmpyCd, String svcMemoCatgCd, String svcMemoTpCd) {
//        SVC_MEMO_TPTMsg inMsg = new SVC_MEMO_TPTMsg();
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("svcMemoCatgCd01", svcMemoCatgCd);
//        if (hasValue(svcMemoTpCd)) {
//            inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
//            inMsg.setSQLID("002");
//        } else {
//            inMsg.setSQLID("001");
//        }
//        return (SVC_MEMO_TPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // del start 2016/02/19 CSA Defect#3689

    // mod start 2016/02/19 CSA Defect#3689
    /**
     * setSearchSvcMemoRsnPulldownList
     * @param glblCmpyCd String
     * @param cMsg NSBL0160CMsg
     */
    public static void setSearchSvcMemoRsnPulldownList(String glblCmpyCd, NSBL0160CMsg cMsg) {
        if (!hasValue(cMsg.svcMemoCatgCd_HD)) {
            return;
        }

        if (!hasValue(cMsg.svcMemoTpCd_HD)) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("svcMemoCatgCd", cMsg.svcMemoCatgCd_HD.getValue());
            List<Map<String, Object>> resultMapList = NSBL0160Query.getInstance().getSvcMemoRsnPullDown(queryParam);
            int i = 0;
            for (Map<String, Object> resultMap : resultMapList) {
                setValue(cMsg.svcMemoRsnCd_SP.no(i), (String) resultMap.get("SVC_MEMO_RSN_CD"));
                setValue(cMsg.svcMemoRsnDescTxt_SP.no(i), (String) resultMap.get("SVC_MEMO_RSN_DESC_TXT"));
                i++;
            }
            return;
        }

        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(glblCmpyCd, cMsg.svcMemoTpCd_HD.getValue());
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_SP, cMsg.svcMemoRsnDescTxt_SP);
   }
    // mod end 2016/02/19 CSA Defect#3689

    // del start 2016/02/19 CSA Defect#3689
//    /**
//     * setAddLineSvcMemoRsnPulldownList
//     * @param glblCmpyCd String
//     * @param cMsg NSBL0160CMsg
//     */
//    public static void setAddLineSvcMemoRsnPulldownList(String glblCmpyCd, NSBL0160CMsg cMsg) {
//        if (!hasValue(cMsg.svcMemoTpCd_AC)) {
//            return;
//        }
//        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(glblCmpyCd, cMsg.svcMemoTpCd_AC.getValue());
//        Map<String, String> tMsgKeys = new HashMap<String, String>();
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnDescTxt");
//        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_SP, cMsg.svcMemoRsnDescTxt_SP);
//   }
    // del end 2016/02/19 CSA Defect#3689

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // END 2015/10/29 T.Tomita [N/A, ADD]

    // START 2015/10/29 T.Tomita [N/A, MOD]
    /**
     * 
     * searchSvcMemo
     * 
     * @param glblCmpyCd Global Company Code
     * @param cMsg NSBL0160CMsg
     * @param sMsg NSBL0160SMsg
     * @param rowNum int
     * @return Search result
     */
    public static boolean searchSvcMemo(String glblCmpyCd, NSBL0160CMsg cMsg, NSBL0160SMsg sMsg, int rowNum) {
        Map<String, Object> sc = new HashMap<String, Object>();
        // mod start 2016/02/19 CSA Defect#3689
        sc = getSsmParam(glblCmpyCd, cMsg, rowNum);
        // mod end 2016/02/19 CSA Defect#3689

        if (SVC_MEMO_CATG.DISPATCH_MEMO.equals(cMsg.svcMemoCatgCd_HD.getValue())) {
            // For Dispatch
            return searchSvcMemoDispatchList(sc, cMsg, sMsg);
        }

        S21SsmEZDResult ssmResult = NSBL0160Query.getInstance().searchSvcMemoList(sc, sMsg);
        if (!ssmResult.isCodeNormal()) {
            return false;
        }

        // add start 2016/02/19 CSA Defect#3689
        if (ssmResult.getQueryResultCount() == 0) {
            cMsg.setMessageInfo(NZZM0000E, null);
        }
        // add end 2016/02/19 CSA Defect#3689

        // START 2022/05/11 [QC#56411, MOD]
//        if (ssmResult.getQueryResultCount() > sMsg.B.length()) {
        if (ssmResult.getQueryResultCount() >= sMsg.B.length()) {
            sMsg.B.no(sMsg.B.length() - 1).clear();
            sMsg.B.setValidCount(sMsg.B.length() - 1);
        // END   2022/05/11 [QC#56411, MOD]
            cMsg.setMessageInfo(NZZM0001W, null);
        }

        setDispTsFormat(sMsg);
        return true;
    }

    // add start 2016/02/19 CSA Defect#3689
    /**
     * @param glblCmpyCd
     * @param cMsg
     * @param rowNum
     * @return Map<String, Object>
     */
    public static Map<String, Object> getSsmParam(String glblCmpyCd, NSBL0160CMsg cMsg, int rowNum) {
        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put("glblCmpyCd", glblCmpyCd);
        sc.put("svcMemoCatgCd", cMsg.svcMemoCatgCd_HD.getValue());
        sc.put("svcMemoTpCd", cMsg.svcMemoTpCd_HD.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.svcMemoRsnCd_SC)) {
            sc.put("svcMemoRsnCd", cMsg.svcMemoRsnCd_SC.getValue());
        }
        sc.put("ezUpAplId", APLID);
        sc.put("Manual1", MANUAL1);
        sc.put("Manual2", MANUAL2);
        sc.put("rowNum", rowNum);
        String fromTs = null;
        if (ZYPCommonFunc.hasValue(cMsg.xxFromDt_SC)) {
            fromTs = cMsg.xxFromDt_SC.getValue() + NSBL0160Constant.SRCH_COND_FROM_TIME;
        }
        sc.put("fromTs", fromTs);
        String thruTs = null;
        if (ZYPCommonFunc.hasValue(cMsg.xxThruDt_SC)) {
            thruTs = cMsg.xxThruDt_SC.getValue() + NSBL0160Constant.SRCH_COND_THRU_TIME;
        }
        sc.put("thruTs", thruTs);
        String[] sortList = new String[cMsg.C.getValidCount()];
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (hasValue(cMsg.C.no(i).xxComnScrColLbTxt_CL)) {
                sc.put("where" + i, cMsg.C.no(i).xxComnScrColLbTxt_CL.getValue());
                sc.put("condition" + i, cMsg.C.no(i).xxComnScrColLbTxt_SC.getValue());
                sortList[i] = cMsg.C.no(i).xxComnScrColLbTxt_CL.getValue();
            }
        }
        sc.put("sortList", sortList);
        return sc;
    }

    /**
     * writeCsvFile
     * @param cMsg NSBL0160CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSBL0160CMsg cMsg, ResultSet rs) throws SQLException {

        NSBL0160F00FMsg fMsg = new NSBL0160F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        // Set don't display column
        fMsg.addExclusionItem("xxChkBox_A");
        fMsg.addExclusionItem("dsContrPk_A");

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            //resultSet -> fMsg
            String lastUpdTs = rs.getString("EZUPTIME");
            if (lastUpdTs.length() > LENGTH_OF_YYYYMMDDHHMMSS) {
                lastUpdTs = lastUpdTs.substring(0, LENGTH_OF_YYYYMMDDHHMMSS);
            }
            String dispDate = ZYPDateUtil.formatEzd14ToDisp(lastUpdTs);
            if (hasValue(dispDate)) {
                dispDate = dispDate.substring(0, LENGTH_OF_DISP);
            }
            setValue(fMsg.xxTsDsp19Txt_B, dispDate);
            setValue(fMsg.fullPsnNm_B, rs.getString("FULL_PSN_NM"));
            setValue(fMsg.xxScrItem40Txt_B, rs.getString("MANUAL"));
            setValue(fMsg.svcMemoRsnDescTxt_B, rs.getString("SVC_MEMO_RSN_DESC_TXT"));
            setValue(fMsg.svcCmntTxt_B, rs.getString("SVC_CMNT_TXT"));
            csvOutFile.write();
        } while (rs.next());
        csvOutFile.close();
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0160F00FMsg fMsg, NSBL0160CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {
                labelConv.convLabel2i18nLabel(SCRN_ID, "Date Updated")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Who Created")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Manual")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Reason Code")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Notes")
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }
    // add end 2016/02/19 CSA Defect#3689

    private static boolean searchSvcMemoDispatchList(Map<String, Object> sc, NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        SVC_MEMOTMsg checkTMsg = new SVC_MEMOTMsg();
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (checkTMsg.getDBColumnName("fsrNum").equals(cMsg.C.no(i).xxComnScrColLbTxt_CL.getValue())) {
                sc.put("fsrNum", cMsg.C.no(i).xxComnScrColLbTxt_SC.getValue());
                break;
            }
        }
        S21SsmEZDResult ssmResult = NSBL0160Query.getInstance().searchSvcMemoDispatchList(sc, sMsg);
        if (!ssmResult.isCodeNormal()) {
            return false;
        }

        // START 2022/05/11 [QC#56411, MOD]
//        if (ssmResult.getQueryResultCount() > sMsg.B.length()) {
        if (ssmResult.getQueryResultCount() >= sMsg.B.length()) {
            sMsg.B.no(sMsg.B.length() - 1).clear();
            sMsg.B.setValidCount(sMsg.B.length() - 1);
        // END   2022/05/11 [QC#56411, MOD]
            cMsg.setMessageInfo(NZZM0001W, null);
        }

        setDispTsFormat(sMsg);
        return true;
    }

//    /**
//     * 
//     * existMemoTpCd
//     * 
//     * @param glblCmpyCd Global Company Code
//     * @param cMsg NSBL0160CMsg
//     * @param sMsg NSBL0160SMsg
//     * @param svcMemoTpCd Add SvcMemoTpCd
//     * @return Search result
//     */
//    public static String existMemoTpCd(String glblCmpyCd, NSBL0160CMsg cMsg, NSBL0160SMsg sMsg, String svcMemoTpCd) {
//        Map<String, Object> sc = new HashMap<String, Object>();
//        sc.put("glblCmpyCd", glblCmpyCd);
//        sc.put("svcMemoCatgCd", cMsg.svcMemoCatgCd_SC.getValue());
//        sc.put("svcMemoTpCd", svcMemoTpCd);
//        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
//            if (hasValue(cMsg.C.no(i).xxComnScrColLbTxt_CL)) {
//                sc.put("where" + i, cMsg.C.no(i).xxComnScrColLbTxt_CL.getValue());
//                sc.put("condition" + i, cMsg.C.no(i).xxComnScrColLbTxt_SC.getValue());
//            }
//        }
//
//        S21SsmEZDResult ssmResult = NSBL0160Query.getInstance().checkSvcMemoType(sc);
//        if (!ssmResult.isCodeNormal()) {
//            return existMemoTpCdForLocal(sMsg.B, svcMemoTpCd);
//        }
//        return (String) ssmResult.getResultObject();
//    }

//    /**
//     * 
//     * existMemoTpCd
//     * 
//     * @param bsMsgArray NSBL0160_BSMsgArray
//     * @param svcMemoTpCd Select Service Memo Type Code
//     * @return Existed Service Memo Type Name
//     */
//    private static String existMemoTpCdForLocal(NSBL0160_BSMsgArray bsMsgArray, String svcMemoTpCd) {
//        for (int i = 0; i < bsMsgArray.getValidCount(); i++) {
//            if (!hasValue(bsMsgArray.no(i).svcMemoPk) && svcMemoTpCd.equals(bsMsgArray.no(i).svcMemoTpCd.getValue())) {
//                return bsMsgArray.no(i).svcMemoTpNm.getValue();
//            }
//        }
//        return null;
//    }

    /**
     * 
     * setDateTimeFormat
     * 
     * @param sMsg NSBL0160SMsg
     */
    public static void setDispTsFormat(NSBL0160SMsg sMsg) {
        // add start 2016/02/19 CSA Defect#3689
        String dispDate = null;
        // add end 2016/02/19 CSA Defect#3689
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (hasValue(sMsg.B.no(i).lastUpdTs_B)) {
                String lastUpdTs = sMsg.B.no(i).lastUpdTs_B.getValue();
                if (lastUpdTs.length() > LENGTH_OF_YYYYMMDDHHMMSS) {
                    lastUpdTs = lastUpdTs.substring(0, LENGTH_OF_YYYYMMDDHHMMSS);
                }
                // mod start 2016/02/19 CSA Defect#3689
                dispDate = ZYPDateUtil.formatEzd14ToDisp(lastUpdTs);
                if (hasValue(dispDate)) {
                    dispDate = dispDate.substring(0, LENGTH_OF_DISP);
                }
                setValue(sMsg.B.no(i).xxTsDsp19Txt_B, dispDate);
                // mod end 2016/02/19 CSA Defect#3689
            }

            // del end 2016/02/19 CSA Defect#3689
//            setValue(sMsg.B.no(i).tmZoneCd_B, NSXC001001SvcTimeZone.getSysTimeZone(sMsg.B.no(i).lastUpdTs_B));
            // del end 2016/02/19 CSA Defect#3689
        }
    }

    // add end 2016/02/19 CSA Defect#3689
    /**
     * getSvcMemoTpDescTxt
     * @param glblCmpyCd String
     * @param svcMemoRsnCd String
     * @return String
     */
    public static String getSvcMemoTpCd(String glblCmpyCd, String svcMemoRsnCd) {
        String rtnCd = null;
        if (!hasValue(svcMemoRsnCd)) {
            return rtnCd;
        }
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMemoRsnCd, svcMemoRsnCd);
        SVC_MEMO_RSNTMsg rtnMsg = (SVC_MEMO_RSNTMsg) S21CodeTableAccessor.findByKey(inMsg);
        if (rtnMsg != null) {
            rtnCd = rtnMsg.svcMemoTpCd.getValue();
        }
        return rtnCd;
    }
    // add end 2016/02/19 CSA Defect#3689

    /**
     * getSvcMemoTpDescTxt
     * @param glblCmpyCd String
     * @param svcMemoTpCd String
     * @return String
     */
    public static String getSvcMemoTpDescTxt(String glblCmpyCd, String svcMemoTpCd) {
        String rtnDescTxt = null;
        if (!hasValue(svcMemoTpCd)) {
            return rtnDescTxt;
        }
        SVC_MEMO_TPTMsg inMsg = new SVC_MEMO_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMemoTpCd, svcMemoTpCd);
        SVC_MEMO_TPTMsg rtnMsg = (SVC_MEMO_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
        if (rtnMsg != null) {
            rtnDescTxt = rtnMsg.svcMemoTpDescTxt.getValue();
        }
        return rtnDescTxt;
    }

    /**
     * getSvcMemoRsnDescTxt
     * @param glblCmpyCd String
     * @param svcMemoRsnCd String
     * @return String
     */
    public static String getSvcMemoRsnDescTxt(String glblCmpyCd, String svcMemoRsnCd) {
        String rtnDescTxt = null;
        if (!hasValue(svcMemoRsnCd)) {
            return rtnDescTxt;
        }
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMemoRsnCd, svcMemoRsnCd);
        SVC_MEMO_RSNTMsg rtnMsg = (SVC_MEMO_RSNTMsg) S21CodeTableAccessor.findByKey(inMsg);
        if (rtnMsg != null) {
            rtnDescTxt = rtnMsg.svcMemoRsnDescTxt.getValue();
        }
        return rtnDescTxt;
    }

    /**
     * Pre Set To Page One
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        setValue(xxPageShowFromNum, BigDecimal.ONE);
    }

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        setValue(xxPageShowToNum, new BigDecimal(xxPageShowFromNum + pageRecs - 1));
        setValue(xxPageShowOfNum, new BigDecimal(totalRecs));
    }

    /**
     * Copy cMsg To sMsg
     * @param cMsg NSBL0160CMsg
     * @param sMsg NSBL0160SMsg
     */
    public static void copyBizMsgToGlblMsg(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        int pageFromNum = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            EZDMsg.copy(cMsg.B.no(i), null, sMsg.B.no(pageFromNum++), null);
        }
    }

    /**
     * Copy sMsg To cMsg
     * @param cMsg NSBL0160CMsg
     * @param sMsg NSBL0160SMsg
     */
    public static void copyGlblMsgToBizMsg(NSBL0160CMsg cMsg, NSBL0160SMsg sMsg) {
        int pageFromNum = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.B.length() && pageFromNum < sMsg.B.getValidCount(); i++) {
            EZDMsg.copy(sMsg.B.no(pageFromNum++), null, cMsg.B.no(i), null);
        }
        cMsg.B.setValidCount(i);
    }
    // END 2015/10/29 T.Tomita [N/A, MOD]
}
