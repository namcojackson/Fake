/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7410.common;

import static business.blap.NMAL7410.constant.NMAL7410Constant.CSV_FILE_EXT;
import static business.blap.NMAL7410.constant.NMAL7410Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NMAL7410.constant.NMAL7410Constant.CSV_MAX_ROW;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM0176E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM0177E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM0208E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM8175E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM8620E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.NMAM8621E;
import static business.blap.NMAL7410.constant.NMAL7410Constant.ZZZM9002W;
import static business.blap.NMAL7410.constant.NMAL7410Constant.ZZZM9001E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7410.NMAL7410CMsg;
import business.blap.NMAL7410.NMAL7410Query;
import business.blap.NMAL7410.NMAL7410SMsg;
import business.blap.NMAL7410.NMAL7410_ASMsg;
import business.blap.NMAL7410.NMAL7410_BSMsg;
import business.db.CSMP_PRC_XREFTMsg;
import business.db.CSMP_PRC_XREFTMsgArray;
import business.file.NMAL7410F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/29   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7410CommonLogic {

    /**
     * <pre>
     * copyFromSMsgOntoCmsg
     * </pre>
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    public static void copyFromSMsgOntoCmsg(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        ZYPTableUtil.clear(bizMsg.A);
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {

                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(bizMsg, (pagenationFrom + 1), (pagenationFrom + bizMsg.A.getValidCount()), glblMsg.A.getValidCount());
    }

    /**
     * <pre>
    * setPageNum
    * </pre>
     * @param bizMsg NMAL7410CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NMAL7410CMsg bizMsg, int fromCnt, int toCnt, int allCnt) {
        bizMsg.xxPageShowFromNum.setValue(fromCnt);
        bizMsg.xxPageShowToNum.setValue(toCnt);
        bizMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * <pre>
    * copyFromCMsgOntoSmsg
    * </pre>
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    public static void copyFromCMsgOntoSmsg(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {
        // copy data from CMsg onto SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (i + pagenationFrom - 1 < glblMsg.A.getValidCount()) {
                EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(i + pagenationFrom - 1), null);
            } else {
                break;
            }
        }
    }

    /**
     * Delete Line
     * @param glblMsg NMAL7410SMsg
     * @param glblCheckList List<Integer>
     * @return boolean false:Error
     */
    public static boolean deleteLine(NMAL7410SMsg glblMsg, List<Integer> glblCheckList) {

        for (int checkNum : glblCheckList) {
            BigDecimal csmpPrcXrefPk = glblMsg.A.no(checkNum).csmpPrcXrefPk_A.getValue();

            if (ZYPCommonFunc.hasValue(csmpPrcXrefPk)) {
                // Copy Message For Delete
                EZDMsg.copy(glblMsg.A.no(checkNum), "A", glblMsg.B.no(glblMsg.B.getValidCount()), "B");
                glblMsg.B.setValidCount(glblMsg.B.getValidCount() + 1);

            }
        }
        ZYPTableUtil.deleteRows(glblMsg.A, glblCheckList);

        return true;
    }

    /**
     * delete Line in NMAL7410_BSMsg - CSMP_PRC_XREF
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     * @return boolean false:Error
     */
    public static boolean deleteXref(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {
        List<CSMP_PRC_XREFTMsg> tMsgList = new ArrayList<CSMP_PRC_XREFTMsg>();

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            NMAL7410_BSMsg deleteMsg = glblMsg.B.no(i);

            CSMP_PRC_XREFTMsg tMsg = new CSMP_PRC_XREFTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.csmpPrcXrefPk, deleteMsg.csmpPrcXrefPk_B);

            tMsg = (CSMP_PRC_XREFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

            if (tMsg == null || !ZYPDateUtil.isSameTimeStamp(//
                    deleteMsg.ezUpTime_B.getValue(), deleteMsg.ezUpTimeZone_B.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NMAM8175E, new String[] {"CSMP_PRC_XREF", "CSMP_PRC_XREF_PK", deleteMsg.csmpPrcXrefPk_B.getValue().toPlainString() });
                return false;
            }
            tMsgList.add(tMsg);
        }

        int delCnt = tMsgList.size();

        if (delCnt != 0) {
            // DELETE
            int rsltCnt = S21FastTBLAccessor.removeLogical(tMsgList.toArray(new CSMP_PRC_XREFTMsg[tMsgList.size()]));
            if (rsltCnt != delCnt) {
                bizMsg.setMessageInfo(NMAM0208E, new String[] {"CSMP_PRC_XREF" });
                return false;
            }
        }
        return true;
    }

    /**
     * Insert or Update Line in NMAL7410_ASMsg - CSMP_PRC_XREF
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     * @return boolean false:Error
     */
    public static boolean registXref(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL7410_ASMsg registMsg = glblMsg.A.no(i);

            if (!submitCheck(bizMsg, glblMsg, registMsg)) {
                return false;
            }

            if (ZYPCommonFunc.hasValue(registMsg.csmpPrcXrefPk_A)) {

                // has PK -> Update
                CSMP_PRC_XREFTMsg tMsg = new CSMP_PRC_XREFTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.csmpPrcXrefPk, registMsg.csmpPrcXrefPk_A);

                tMsg = (CSMP_PRC_XREFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null || !ZYPDateUtil.isSameTimeStamp(//
                        registMsg.ezUpTime_A.getValue(), registMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NMAM8175E, new String[] {"CSMP_PRC_XREF", "CSMP_PRC_XREF_PK", registMsg.csmpPrcXrefPk_A.getValue().toPlainString() });
                    return false;
                }
                if (!hasChange(registMsg, tMsg)) {
                    continue;
                }

                ZYPEZDItemValueSetter.setValue(tMsg.crListTxt, registMsg.crListTxt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.crListGnrnNum, registMsg.crListGnrnNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.csaPrcCatgCd, registMsg.csaPrcCatgCd_A);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, registMsg.effFromDt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, registMsg.effThruDt_A);

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"CSMP_PRC_XREF" });
                    return false;
                }
            } else {

                // not has PK -> Insert
                CSMP_PRC_XREFTMsg tMsg = new CSMP_PRC_XREFTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.csmpPrcXrefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSMP_PRC_XREF_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.crListTxt, registMsg.crListTxt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.crListGnrnNum, registMsg.crListGnrnNum_A);
                ZYPEZDItemValueSetter.setValue(tMsg.csaPrcCatgCd, registMsg.csaPrcCatgCd_A);
                ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, registMsg.effFromDt_A);
                ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, registMsg.effThruDt_A);

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0176E, new String[] {"CSMP_PRC_XREF" });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * error Check for Submit
     * @param bizMsg NMAL7410CMsg
     * @param registMsg NMAL7410_ASMsg
     * @return boolean false:Error
     */
    private static boolean submitCheck(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg, NMAL7410_ASMsg registMsg) {

        List<CSMP_PRC_XREFTMsg> checkTMsgList = getMsgForCheck(bizMsg, glblMsg, registMsg);

        for (int i = 0; i < checkTMsgList.size(); i++) {

            CSMP_PRC_XREFTMsg checkTmsg = checkTMsgList.get(i);

            if (registMsg.csmpPrcXrefPk_A.getValueInt() != checkTmsg.csmpPrcXrefPk.getValueInt()) {
                // Record duplication check
                if (registMsg.effFromDt_A.getValue().equals(checkTmsg.effFromDt.getValue())) {
                    bizMsg.setMessageInfo(NMAM8620E, new String[] {//
                            registMsg.crListTxt_A.getValue(), registMsg.crListGnrnNum_A.getValue(), //
                                    registMsg.csaPrcCatgCd_A.getValue(), registMsg.effFromDt_A.getValue() });
                    return false;
                }
                // Date range duplicate check
                if (!rangeDuplicateCheck(registMsg, checkTmsg)) {
                    bizMsg.setMessageInfo(NMAM8621E, new String[] {//
                            registMsg.crListTxt_A.getValue(), registMsg.crListGnrnNum_A.getValue(), //
                                    registMsg.csaPrcCatgCd_A.getValue() });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * get CSMP_PRC_XREFTMsg For ErrorCheck
     * @param bizMsg NMAL7410CMsg
     * @param registMsg NMAL7410_ASMsg
     * @return CSMP_PRC_XREFTMsgArray
     */
    private static List<CSMP_PRC_XREFTMsg> getMsgForCheck(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg, NMAL7410_ASMsg registMsg) {

        // Get the CSMP_PRC_XREFTMsg from DB
        CSMP_PRC_XREFTMsg tMsg = new CSMP_PRC_XREFTMsg();
        tMsg.setSQLID("001");

        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("crListTxt01", registMsg.crListTxt_A.getValue());
        tMsg.setConditionValue("crListGnrnNum01", registMsg.crListGnrnNum_A.getValue());
        tMsg.setConditionValue("csaPrcCatgCd01", registMsg.csaPrcCatgCd_A.getValue());

        CSMP_PRC_XREFTMsgArray tMsgArray = (CSMP_PRC_XREFTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        List<CSMP_PRC_XREFTMsg> tMsgList = new ArrayList<CSMP_PRC_XREFTMsg>();

        for (int i = 0; i < tMsgArray.length(); i++) {
            tMsg = tMsgArray.no(i);
            tMsgList.add(tMsg);
        }

        // Get the CSMP_PRC_XREFTMsg from Screen and Overwrite DB Data
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NMAL7410_ASMsg checkMsg = glblMsg.A.no(i);

            if (checkMsg.crListTxt_A.getValue().equals(registMsg.crListTxt_A.getValue())//
                    && checkMsg.crListGnrnNum_A.getValue().equals(registMsg.crListGnrnNum_A.getValue())//
                    && checkMsg.csaPrcCatgCd_A.getValue().equals(registMsg.csaPrcCatgCd_A.getValue())) {
                if (ZYPCommonFunc.hasValue(checkMsg.csmpPrcXrefPk_A) && ZYPCommonFunc.hasValue(registMsg.csmpPrcXrefPk_A)
                        && !checkMsg.csmpPrcXrefPk_A.getValue().equals(registMsg.csmpPrcXrefPk_A.getValue())) {

                    for (int j = 0; j < tMsgList.size(); j++) {
                        if (tMsgList.get(j).csmpPrcXrefPk.getValueInt() == checkMsg.csmpPrcXrefPk_A.getValueInt()) {
                            tMsgList.remove(j);
                            break;
                        }
                    }
                    tMsg = new CSMP_PRC_XREFTMsg();
                    EZDMsg.copy(checkMsg, "A", tMsg, "");
                    tMsgList.add(tMsg);
                }
            }
        }

        return tMsgList;
    }

    /**
     * Date Range Check
     * @param bizMsg NMAL7410CMsg
     * @param registMsg NMAL7410_ASMsg
     * @return CSMP_PRC_XREFTMsgArray
     */
    private static boolean rangeDuplicateCheck(NMAL7410_ASMsg registMsg, CSMP_PRC_XREFTMsg checkTMsg) {

        if (ZYPCommonFunc.hasValue(registMsg.effThruDt_A) //
                && registMsg.effThruDt_A.getValue().compareTo(checkTMsg.effFromDt.getValue()) < 0) {
            return true;

        }
        if (ZYPCommonFunc.hasValue(checkTMsg.effThruDt) //
                && registMsg.effFromDt_A.getValue().compareTo(checkTMsg.effThruDt.getValue()) > 0) {
            return true;
        }

        return false;
    }

    /**
     * Whether there is a need to update
     * @param updateMsg NMAL7410_ASMsg
     * @param searchTmsg CSMP_PRC_XREFTMsg
     * @return boolean
     */
    private static boolean hasChange(NMAL7410_ASMsg updateMsg, CSMP_PRC_XREFTMsg searchTmsg) {

        if (!updateMsg.crListTxt_A.getValue().equals(searchTmsg.crListTxt.getValue())) {
            return true;
        }
        if (!updateMsg.crListGnrnNum_A.getValue().equals(searchTmsg.crListGnrnNum.getValue())) {
            return true;
        }
        if (!updateMsg.csaPrcCatgCd_A.getValue().equals(searchTmsg.csaPrcCatgCd.getValue())) {
            return true;
        }
        if (!updateMsg.effFromDt_A.getValue().equals(searchTmsg.effFromDt.getValue())) {
            return true;
        }
        if (!updateMsg.effThruDt_A.getValue().equals(searchTmsg.effThruDt.getValue())) {
            return true;
        }

        return false;
    }

    /**
     * csv Download Search result
     * @param bizMsg NMAL7410CMsg
     * @param glblMsg NMAL7410SMsg
     */
    public static void csvDownload(NMAL7410CMsg bizMsg, NMAL7410SMsg glblMsg) {

        NMAL7410Query.getInstance().searchForCSV(bizMsg, glblMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * Create Download Date for CSV
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {
        /** cMsg */
        private NMAL7410CMsg bizMsg;

        public CreateDownloadData(NMAL7410CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        /** csv Header Column */
        private static final String[] CSV_HEADER = {"CSMP Pr List", "CSMP Generation#", "CSA Price List", "Description", "Effective From", "Effective Thru" };

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(ZZZM9001E);
                return false;
            }
            NMAL7410F00FMsg fMsg = new NMAL7410F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            csvOutFile.writeHeader(CSV_HEADER);

            int rowCount = 1;
            do {
                if (rowCount > CSV_MAX_ROW) {
                    bizMsg.setMessageInfo(ZZZM9002W);
                    return true;
                }

                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.crListTxt, result.getString("CR_LIST_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.crListGnrnNum, result.getString("CR_LIST_GNRN_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.csaPrcCatgCd, result.getString("CSA_PRC_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcCatgNm, result.getString("PRC_CATG_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, convertDateFormat(result.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, convertDateFormat(result.getString("EFF_THRU_DT")));
                csvOutFile.write();
                rowCount++;
            } while (result.next());
            csvOutFile.close();

            return true;
        }
    }

    /**
     * convertDateFormat
     * @param date String
     * @return String
     */
    private static String convertDateFormat(String date) {
        if (ZYPCommonFunc.hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    /**
     * convertDateFormat
     * @param date String
     * @return String
     */
    public static Map<String, Object> setSearchParam(NMAL7410CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("csmpPrcList", bizMsg.crListTxt.getValue());
        params.put("csmpGeneration", bizMsg.crListGnrnNum.getValue());
        params.put("csaPrcList", bizMsg.prcCatgNm.getValue());
        params.put("effFromDtFr", bizMsg.effFromDt_FR.getValue());
        params.put("effFromDtTo", bizMsg.effFromDt_TO.getValue());
        params.put("effThruDtFr", bizMsg.effThruDt_FR.getValue());
        params.put("effThruDtTo", bizMsg.effThruDt_TO.getValue());
        return params;
    }
}
