/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0240.common;

import static business.blap.NSBL0240.constant.NSBL0240Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDFBigDecimalItem;
import parts.common.EZDFItem;
import parts.common.EZDFMsg;
import parts.common.EZDFStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0240.NSBL0240CMsg;
import business.blap.NSBL0240.NSBL0240Query;
import business.blap.NSBL0240.NSBL0240SMsg;
import business.blap.NSBL0240.NSBL0240_ACMsg;
import business.blap.NSBL0240.NSBL0240_ACMsgArray;
import business.blap.NSBL0240.NSBL0240_ASMsg;
import business.db.DS_MDL_GRPTMsg;
import business.db.DS_MDL_GRPTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.SVC_TRVL_CHRGTMsg;
import business.db.SVC_TRVL_CHRGTMsgArray;
import business.db.SVC_TRVL_CHRG_TPTMsg;
import business.file.NSBL0240F00FMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/16   Hitachi         Y.Takeno        Update          QC#7746
 * 2016/08/09   Hitachi         T.Mizuki        Update          QC#12243
 * 2016/12/02   Hitachi         N.Arai          Create          QC#14204
 * 2018/01/30   Hitachi         M.Kidokoro      Update          QC#23252
 *</pre>
 */
public class NSBL0240CommonLogic {

    /**
     * create SVC_LINE_BIZ pull-down list.
     * @param cMsg NSBL0240CMsg
     */
    public static void createSvcLineBizPulldownList(NSBL0240CMsg cMsg) {
        S21SsmEZDResult ssmSvcLineBizResult = NSBL0240Query.getInstance().getSvcLineBizList();
        if (ssmSvcLineBizResult.isCodeNotFound()) {
            return;
        }
        List<Map<String, Object>> svcLineBizList = (List<Map<String, Object>>) ssmSvcLineBizResult.getResultObject();
        if (svcLineBizList.size() == 0) {
            return;
        }

        cMsg.lineBizTpCd_L.clear();
        cMsg.lineBizTpDescTxt_L.clear();
        for (int i = 0; i < svcLineBizList.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) svcLineBizList.get(i);
            cMsg.lineBizTpCd_L.no(i).setValue((String) map.get("LINE_BIZ_TP_CD"));
            cMsg.lineBizTpDescTxt_L.no(i).setValue((String) map.get("LINE_BIZ_TP_DESC_TXT"));
        }
    }

    /**
     * Paginate to item
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @param itemIndex itemIndex
     */
    public static void pagenation(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {

        // NSBL0240_ACMsg -> NSBL0240_ASMsg
        NSBL0240_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSBL0240_ACMsg acMsg = (NSBL0240_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum.getValueInt() - 1;

            NSBL0240_ASMsg asMsg = (NSBL0240_ASMsg) sMsg.A.get(targetIdxNumA);

            // copy CMsg to SMsg
            EZDMsg.copy(acMsg, null, asMsg, null);
        }
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage rowsPerPage
     * @param page page
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * getLastPageNum
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @return int
     */
    public static int getLastPageNum(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        // START 2016/05/16 [QC#7746, MOD]
        return BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP).intValue();
        // END   2016/05/16 [QC#7746, MOD]
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NSBL0240CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NSBL0240CMsg cMsg) {
        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        } else if (cMsg.xxPageShowTotNum.getValueInt() < cMsg.xxPageShowCurNum.getValueInt()) {
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
    }

    /**
     * getResultMapListFromFMsg
     * 
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSBL0240CMsg
     * @param fMsg NSBL0240F00FMsg
     * @param zoneCount zoneCount
     * @return List<Map<String, Object>>
     */
    public static final List<Map<String, Object>> getResultMapListFromFMsg(String glblCmpyCd, NSBL0240CMsg cMsg, NSBL0240F00FMsg fMsg, int zoneCount) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultBase = new HashMap<String, Object>();

        resultBase.put("MDL_GRP_NM", fMsg.mdlGrpNm.getValue());
        resultBase.put("MDL_GRP_DESC_TXT", fMsg.mdlGrpDescTxt.getValue());
        resultBase.put("MDL_GRP_ID", null);
        resultBase.put("SVC_LINE_BIZ_CD", fMsg.svcLineBizCd.getValue());
        resultBase.put("INTG_MDSE_CD", fMsg.intgMdseCd.getValue());
        resultBase.put("MDSE_DESC_SHORT_TXT", fMsg.mdseDescShortTxt.getValue());

        // find DS_MDL_GRP by MDL_GRP_NM
        DS_MDL_GRPTMsg mdlGrpTMsg = findDsMdlGrp(glblCmpyCd, fMsg.mdlGrpNm.getValue());
        if (mdlGrpTMsg != null) {
            resultBase.put("MDL_GRP_ID", mdlGrpTMsg.mdlGrpId.getValue());
            resultBase.put("MDL_GRP_DESC_TXT", mdlGrpTMsg.mdlGrpDescTxt.getValue());
        }

        // find MDSE by MDSE_CD
        MDSETMsg mdseTMsg = findMdse(glblCmpyCd, fMsg.intgMdseCd.getValue());
        if (mdseTMsg != null) {
            resultBase.put("MDSE_DESC_SHORT_TXT", mdseTMsg.mdseDescShortTxt.getValue());
        }

        for (int colIndex = 0; colIndex < zoneCount; colIndex++) {
            EZDFBigDecimalItem svcTrvlUnitAmt = (EZDFBigDecimalItem) getFItem(FLD_NM_SVC_TRVL_UNIT_AMT, fMsg, colIndex);
            EZDFStringItem svcTrvlChrgTpCd = (EZDFStringItem) getFItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, fMsg, colIndex);

            if (hasValue(svcTrvlUnitAmt) || hasValue(svcTrvlChrgTpCd)) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("MDL_GRP_NM", resultBase.get("MDL_GRP_NM"));
                result.put("MDL_GRP_DESC_TXT", resultBase.get("MDL_GRP_DESC_TXT"));
                result.put("MDL_GRP_ID", resultBase.get("MDL_GRP_ID"));
                result.put("SVC_LINE_BIZ_CD", resultBase.get("SVC_LINE_BIZ_CD"));
                result.put("INTG_MDSE_CD", resultBase.get("INTG_MDSE_CD"));
                result.put("MDSE_DESC_SHORT_TXT", resultBase.get("MDSE_DESC_SHORT_TXT"));

                result.put("SVC_TRVL_UNIT_AMT", svcTrvlUnitAmt.getValue());
                result.put("SVC_TRVL_CHRG_TP_CD", svcTrvlChrgTpCd.getValue());
                result.put("SVC_TRVL_CHRG_PK", null);
                result.put("EZUPTIME", null);
                result.put("EZUPTIMEZONE", null);

                // find SVC_TRVL_CHRG by MDL_GRP_ID, SVC_LINE_BIZ_CD,
                // SVC_ZN_CD
                if (mdlGrpTMsg != null) {
                    BigDecimal mdlGrpId = (BigDecimal) result.get("MDL_GRP_ID");
                    String svcZnCd = cMsg.C.no(colIndex).svcZnCd.getValue();
                    String svcLineBizCd = (String) result.get("SVC_LINE_BIZ_CD");

                    result.put("SVC_ZN_CD", svcZnCd);

                    SVC_TRVL_CHRGTMsg svcTrvlChrgTMsg = findSvcTrvlChrg(glblCmpyCd, mdlGrpId, svcLineBizCd, svcZnCd);
                    if (svcTrvlChrgTMsg != null) {
                        result.put("SVC_TRVL_CHRG_PK", svcTrvlChrgTMsg.svcTrvlChrgPk.getValue());
                        result.put("EZUPTIME", svcTrvlChrgTMsg.ezUpTime.getValue());
                        result.put("EZUPTIMEZONE", svcTrvlChrgTMsg.ezUpTimeZone.getValue());
                    }
                }

                resultList.add(result);
            }
        }

        return resultList;
    }

    /**
     * setResultToSMsg
     * @param result Map<String, Object>
     * @param sMsg NSBL0240SMsg
     * @param index index
     */
    public static final void setResultToSMsg(Map<String, Object> result, NSBL0240SMsg sMsg, int index) {
        setValue(sMsg.A.no(index).xxRowNum, BigDecimal.valueOf(index + 1));
        setValue(sMsg.A.no(index).mdlGrpNm, (String) result.get("MDL_GRP_NM"));
        setValue(sMsg.A.no(index).mdlGrpDescTxt, (String) result.get("MDL_GRP_DESC_TXT"));
        setValue(sMsg.A.no(index).mdlGrpId, (BigDecimal) result.get("MDL_GRP_ID"));
        setValue(sMsg.A.no(index).svcLineBizCd, (String) result.get("SVC_LINE_BIZ_CD"));
        setValue(sMsg.A.no(index).intgMdseCd, (String) result.get("INTG_MDSE_CD"));
        setValue(sMsg.A.no(index).mdseDescShortTxt, (String) result.get("MDSE_DESC_SHORT_TXT"));
        setZoneDataToSMsg(result, sMsg, index);
// START 2016/12/02 N.Arai [QC#14204, MOD]
        setValue(sMsg.A.no(index).xxRecHistCratTs_A, (String) result.get("XX_REC_HIST_CRAT_TS"));
        setValue(sMsg.A.no(index).xxRecHistCratByNm_A, (String) result.get("XX_REC_HIST_CRAT_BY_NM"));
        setValue(sMsg.A.no(index).xxRecHistUpdTs_A, (String) result.get("XX_REC_HIST_UPD_TS"));
        setValue(sMsg.A.no(index).xxRecHistUpdByNm_A, (String) result.get("XX_REC_HIST_UPD_BY_NM"));
        setValue(sMsg.A.no(index).xxRecHistTblNm_A, (String) result.get("XX_REC_HIST_TBL_NM"));
// END 2016/12/02 N.Arai [QC#14204, MOD]
    }

    private static void setZoneDataToSMsg(Map<String, Object> result, NSBL0240SMsg sMsg, int index) {

        for (int colIndex = 0; colIndex < sMsg.C.getValidCount(); colIndex++) {
            if (sMsg.C.no(colIndex).svcZnCd.getValue().equals((String) result.get("SVC_ZN_CD"))) {
                setSItem(FLD_NM_SVC_TRVL_UNIT_AMT, sMsg.A.no(index), colIndex, (BigDecimal) result.get("SVC_TRVL_UNIT_AMT"));
                setSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, sMsg.A.no(index), colIndex, (String) result.get("SVC_TRVL_CHRG_TP_CD"));
                setSItem(FLD_NM_SVC_TRVL_CHRG_PK, sMsg.A.no(index), colIndex, (BigDecimal) result.get("SVC_TRVL_CHRG_PK"));
                setSItem(FLD_NM_EZUPTIME, sMsg.A.no(index), colIndex, (String) result.get("EZUPTIME"));
                setSItem(FLD_NM_EZUPTIMEZONE, sMsg.A.no(index), colIndex, (String) result.get("EZUPTIMEZONE"));
                break;
            }
        }
    }

    /**
     * validateDetailLines
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @return true/false
     */
    public static final boolean validateDetailLines(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        return validateDetailLines(cMsg, sMsg, 0, sMsg.A.getValidCount());
    }

    /**
     * validateDetailLines
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @param startIndex startIndex
     * @param endIndex endIndex
     * @return true/false
     */
    public static final boolean validateDetailLines(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, int startIndex, int endIndex) {
        boolean result = true;

        // master existence check
        for (int i = startIndex; i < endIndex; i++) {
            if (!isExistsDsMdlGrp(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdlGrpNm.getValue())) {
                sMsg.A.no(i).mdlGrpNm.setErrorInfo(1, NSBM0151E, new String[] {"Model Group", "Model Group" });
                result = false;
            }
            if (!isExistsMdse(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).intgMdseCd.getValue())) {
                sMsg.A.no(i).intgMdseCd.setErrorInfo(1, NSBM0151E, new String[] {"Default Intangible Item#", "Mdse" });
                result = false;
            }
        }

        if (!result) {
            return false;
        }

        // consistency check
        Map<String, String> recordKeyMap = new HashMap<String, String>();
        for (int i = startIndex; i < endIndex; i++) {

            // Model Group, Line of Business (duplicate in sMsg)
            String recordKey = createRecordGroupKey(sMsg.A.no(i));
            if (recordKeyMap.containsKey(recordKey)) {
                cMsg.setMessageInfo(NSBM0149E, new String[] {sMsg.A.no(i).mdlGrpNm.getValue(), sMsg.A.no(i).svcLineBizCd.getValue() });
                result = false;
            }
            recordKeyMap.put(recordKey, recordKey);

            // Model Group, Line of Business (duplicate in table)
            if (isNewRow(sMsg, i)) {
                int count = countupSvcTrvlChrgDuplicateRecord(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).mdlGrpId.getValue(), sMsg.A.no(i).svcLineBizCd.getValue());
                if (count > 0) {
                    cMsg.setMessageInfo(NSBM0149E, new String[] {sMsg.A.no(i).mdlGrpNm.getValue(), sMsg.A.no(i).svcLineBizCd.getValue() });
                    result = false;
                }
            }

            // Intangible Item
            MDSETMsg msdeTMsg = findMdse(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).intgMdseCd.getValue());
            // START 2018/01/30 M.Kidokoro [QC#23252,MOD]
//            if (!MDSE_ITEM_BILL_TP.LABOR.equals(msdeTMsg.mdseItemBillTpCd.getValue()) || !MDSE_ITEM_TP.INTANGIBLE.equals(msdeTMsg.mdseItemTpCd.getValue())) {
            if (!MDSE_ITEM_BILL_TP.TRAVEL.equals(msdeTMsg.mdseItemBillTpCd.getValue()) || !MDSE_ITEM_TP.INTANGIBLE.equals(msdeTMsg.mdseItemTpCd.getValue())) {
            // END 2018/01/30 M.Kidokoro [QC#23252,MOD]
                sMsg.A.no(i).intgMdseCd.setErrorInfo(1, NSBM0152E);
                // START 2018/01/30 M.Kidokoro [QC#23252,ADD]
                result = false;
                // END 2018/01/30 M.Kidokoro [QC#23252,ADD]
            }
        }

        if (!result) {
            return false;
        }

        // validate Zone Data
        return validateZoneData(cMsg, sMsg, startIndex, endIndex);
    }

    /**
     * validateDetailLines
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @return true/false
     */
    public static final boolean validateDetailLinesForUpload(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        // consistency check
        Map<String, String> recordKeyMap = new HashMap<String, String>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // Model Group, Line of Business (duplicate in sMsg)
            String recordKey = createRecordGroupKey(sMsg.A.no(i));
            if (recordKeyMap.containsKey(recordKey)) {
                cMsg.setMessageInfo(NSBM0149E, new String[] {sMsg.A.no(i).mdlGrpNm.getValue(), sMsg.A.no(i).svcLineBizCd.getValue() });
                return false;
            }
            recordKeyMap.put(recordKey, recordKey);
        }

        return true;
    }

    private static boolean isNewRow(NSBL0240SMsg sMsg, int rowIndex) {
        for (int colIndex = 0; colIndex < sMsg.C.getValidCount(); colIndex++) {
            NSBL0240_ASMsg asMsg = sMsg.A.no(rowIndex);
            EZDSBigDecimalItem svcTrvlChrgPk = (EZDSBigDecimalItem) getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, colIndex);
            if (hasValue(svcTrvlChrgPk)) {
                return false;
            }
        }

        return true;
    }

    private static int countupSvcTrvlChrgDuplicateRecord(String glblCmpyCd, BigDecimal mdlGrpId, String svcLineBizCd) {
        SVC_TRVL_CHRGTMsg inMsg = new SVC_TRVL_CHRGTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpId01", mdlGrpId);
        inMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);

        return EZDTBLAccessor.count(inMsg);
    }

    private static boolean validateZoneData(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, int startIndex, int endIndex) {
        boolean result = true;
        for (int sIndex = startIndex; sIndex < endIndex; sIndex++) {
            NSBL0240_ASMsg asMsg = sMsg.A.no(sIndex);
            for (int colIndex = 0; colIndex < sMsg.C.getValidCount(); colIndex++) {
                EZDSStringItem svcTrvlChrgTpCd = (EZDSStringItem) getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, colIndex);
                EZDSBigDecimalItem svcTrvlUnitAmt = (EZDSBigDecimalItem) getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, colIndex);
                if (!validateZone(cMsg, sMsg, sIndex, colIndex, svcTrvlChrgTpCd, svcTrvlUnitAmt)) {
                    result = false;
                }
            }
        }
        return result;
    }

    private static boolean validateZone(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, int sIndex, int zIndex, EZDSStringItem svcTrvlChrgTpCd, EZDSBigDecimalItem svcTrvlUnitAmt) {

        if (!hasValue(svcTrvlChrgTpCd) && !hasValue(svcTrvlUnitAmt)) {
            return true;
        }

        // Zone is not completed
        if (!hasValue(svcTrvlChrgTpCd) || !hasValue(svcTrvlUnitAmt)) {
            // mod start 2016/08/09 CSA QC#12243
            if (!hasValue(svcTrvlChrgTpCd)) {
                svcTrvlChrgTpCd.setErrorInfo(1, NSBM0177E, new String[] {});
            } else {
                svcTrvlUnitAmt.setErrorInfo(1, NSBM0178E, new String[] {});
            }
            // mod end 2016/08/09 CSA QC#12243

            return false;
        }

        // svcTrvlChrgTpCd
        if (!isExistsSvcTrvlChrgTp(cMsg.glblCmpyCd.getValue(), svcTrvlChrgTpCd.getValue())) {
            svcTrvlChrgTpCd.setErrorInfo(1, NSBM0151E, new String[] {"UOM", "Service Travel Charge Type" });
            return false;
        }

        return true;
    }

    private static String createRecordGroupKey(NSBL0240_ASMsg asMsg) {
        StringBuilder builder = new StringBuilder();
        if (hasValue(asMsg.mdlGrpNm)) {
            builder.append(asMsg.mdlGrpNm.getValue());
        }
        builder.append(DELIM);
        if (hasValue(asMsg.svcLineBizCd)) {
            builder.append(asMsg.svcLineBizCd.getValue());
        }
        return builder.toString();
    }

    /**
     * findSvcTrvlChrg
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpId mdlGrpId
     * @param svcLineBizCd svcLineBizCd
     * @param svcZnCd svcZnCd
     * @return SVC_TRVL_CHRGTMsg
     */
    public static SVC_TRVL_CHRGTMsg findSvcTrvlChrg(String glblCmpyCd, BigDecimal mdlGrpId, String svcLineBizCd, String svcZnCd) {
        SVC_TRVL_CHRGTMsg inMsg = new SVC_TRVL_CHRGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpId01", mdlGrpId);
        inMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);
        inMsg.setConditionValue("svcZnCd01", svcZnCd);

        SVC_TRVL_CHRGTMsgArray tMsgArray = (SVC_TRVL_CHRGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (SVC_TRVL_CHRGTMsg) tMsgArray.no(0);
        }

        return null;
    }

    /**
     * isExistsMdse
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return true/false
     */
    public static boolean isExistsMdse(String glblCmpyCd, String mdseCd) {
        if (findMdse(glblCmpyCd, mdseCd) != null) {
            return true;
        }
        return false;
    }

    /**
     * findMdse
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return MDSETMsg
     */
    public static MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setSQLID("502");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);

        MDSETMsgArray tMsgArray = (MDSETMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (MDSETMsg) tMsgArray.no(0);
        }

        return null;
    }

    /**
     * isExistsDsMdlGrp
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @return true/false
     */
    public static boolean isExistsDsMdlGrp(String glblCmpyCd, String mdlGrpNm) {
        if (findDsMdlGrp(glblCmpyCd, mdlGrpNm) != null) {
            return true;
        }
        return false;
    }

    /**
     * findDsMdlGrp
     * @param glblCmpyCd glblCmpyCd
     * @param mdlGrpNm mdlGrpNm
     * @return DS_MDL_GRPTMsg
     */
    public static DS_MDL_GRPTMsg findDsMdlGrp(String glblCmpyCd, String mdlGrpNm) {
        DS_MDL_GRPTMsg inMsg = new DS_MDL_GRPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdlGrpNm01", mdlGrpNm);

        DS_MDL_GRPTMsgArray tMsgArray = (DS_MDL_GRPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() == 1) {
            return (DS_MDL_GRPTMsg) tMsgArray.no(0);
        }

        return null;
    }

    /**
     * isExistsSvcTrvlChrgTp
     * @param glblCmpyCd glblCmpyCd
     * @param svcTrvlChrgTpCd svcTrvlChrgTpCd
     * @return true/false
     */
    public static boolean isExistsSvcTrvlChrgTp(String glblCmpyCd, String svcTrvlChrgTpCd) {
        if (findSvcTrvlChrgTp(glblCmpyCd, svcTrvlChrgTpCd) != null) {
            return true;
        }
        return false;
    }

    /**
     * findSvcTrvlChrgTp
     * @param glblCmpyCd glblCmpyCd
     * @param svcTrvlChrgTpCd svcTrvlChrgTpCd
     * @return SVC_TRVL_CHRG_TPTMsg
     */
    public static SVC_TRVL_CHRG_TPTMsg findSvcTrvlChrgTp(String glblCmpyCd, String svcTrvlChrgTpCd) {
        SVC_TRVL_CHRG_TPTMsg inMsg = new SVC_TRVL_CHRG_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcTrvlChrgTpCd, svcTrvlChrgTpCd);

        return (SVC_TRVL_CHRG_TPTMsg) EZDTBLAccessor.findByKey(inMsg);

    }

    /**
     * getFirstErrorIndex
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @return error page number
     */
    public static final int getFirstErrorIndex(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        int errIndex = convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isError(sMsg, i)) {
                errIndex = i;
                break;
            }
        }
        return errIndex;
    }

    /**
     * isError
     * @param sMsg NSBL0240SMsg
     * @param index index
     * @return true/false
     */
    public static final boolean isError(NSBL0240SMsg sMsg, int index) {
        NSBL0240_ASMsg asMsg = sMsg.A.no(index);

        if (asMsg.mdlGrpNm.isError()) {
            return true;
        }

        if (asMsg.svcLineBizCd.isError()) {
            return true;
        }

        if (asMsg.intgMdseCd.isError()) {
            return true;
        }

        for (int colIndex = 0; colIndex < sMsg.C.getValidCount(); colIndex++) {
            EZDSStringItem svcTrvlChrgTpCd = (EZDSStringItem) getSItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, asMsg, colIndex);
            EZDSBigDecimalItem svcTrvlUnitAmt = (EZDSBigDecimalItem) getSItem(FLD_NM_SVC_TRVL_UNIT_AMT, asMsg, colIndex);

            if (svcTrvlChrgTpCd.isError() || svcTrvlUnitAmt.isError()) {
                return true;
            }
        }

        return false;
    }

    /**
     * setEmptyRecord
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSBL0240CMsg
     * @param sMsg NSBL0240SMsg
     * @param index index
     */
    public static final void setEmptyRecord(String glblCmpyCd, NSBL0240CMsg cMsg, NSBL0240SMsg sMsg, int index) {
        NSBL0240_ASMsg asMsg = sMsg.A.no(index);
        setValue(asMsg.xxRowNum, BigDecimal.valueOf(index + 1));
        sMsg.A.setValidCount(index + 1);
    }

    /**
     * reNumberingRowNum
     * @param sMsg NSBL0240SMsg
     */
    public static final void reNumberingRowNum(NSBL0240SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum, BigDecimal.valueOf(i + 1));
        }
    }

    /**
     * getFItem
     * @param fieldBaseName fieldBaseName
     * @param sMsg EZDFMsg
     * @param colIndex colIndex
     * @return EZDFItem
     */
    public static final EZDFItem getFItem(String fieldBaseName, EZDFMsg sMsg, int colIndex) {
        EZDFItem item = null;
        try {
            String fieldName = fieldBaseName + String.format("%02d", colIndex);
            Field fld = sMsg.getClass().getField(fieldName);
            Object fldObj = fld.get(sMsg);
            item = (EZDFItem) fldObj;

        } catch (NoSuchFieldException nsfe) {
            throw new S21AbendException(nsfe);

        } catch (IllegalAccessException iae) {
            throw new S21AbendException(iae);
        }

        return item;
    }

    /**
     * getSItem
     * @param fieldBaseName fieldBaseName
     * @param sMsg EZDSMsg
     * @param colIndex colIndex
     * @return EZDSItem
     */
    public static final EZDSItem getSItem(String fieldBaseName, EZDSMsg sMsg, int colIndex) {
        EZDSItem item = null;
        try {
            String fieldName = fieldBaseName + String.format("%02d", colIndex);
            Field fld = sMsg.getClass().getField(fieldName);
            Object fldObj = fld.get(sMsg);
            item = (EZDSItem) fldObj;

        } catch (NoSuchFieldException nsfe) {
            throw new S21AbendException(nsfe);

        } catch (IllegalAccessException iae) {
            throw new S21AbendException(iae);
        }

        return item;
    }

    /**
     * setSItem
     * @param fieldBaseName fieldBaseName
     * @param sMsg EZDSMsg
     * @param colIndex colIndex
     * @param value value
     */
    public static final void setSItem(String fieldBaseName, EZDSMsg sMsg, int colIndex, String value) {
        EZDSStringItem item = null;
        try {
            String fieldName = fieldBaseName + String.format("%02d", colIndex);
            Field fld = sMsg.getClass().getField(fieldName);
            Object fldObj = fld.get(sMsg);
            item = (EZDSStringItem) fldObj;
            setValue(item, value);

        } catch (NoSuchFieldException nsfe) {
            throw new S21AbendException(nsfe);

        } catch (IllegalAccessException iae) {
            throw new S21AbendException(iae);
        }
    }

    /**
     * setSItem
     * @param fieldBaseName fieldBaseName
     * @param cMsg EZDSMsg
     * @param colIndex colIndex
     * @param value value
     */
    public static final void setSItem(String fieldBaseName, EZDSMsg cMsg, int colIndex, BigDecimal value) {
        EZDSBigDecimalItem item = null;
        try {
            String fieldName = fieldBaseName + String.format("%02d", colIndex);
            Field fld = cMsg.getClass().getField(fieldName);
            Object fldObj = fld.get(cMsg);
            item = (EZDSBigDecimalItem) fldObj;
            setValue(item, value);

        } catch (NoSuchFieldException nsfe) {
            throw new S21AbendException(nsfe);

        } catch (IllegalAccessException iae) {
            throw new S21AbendException(iae);
        }
    }
}
