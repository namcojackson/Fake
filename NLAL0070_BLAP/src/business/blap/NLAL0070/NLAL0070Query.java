/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/14/2009   Fujitsu         N.Hasegawa      Create          N/A
 * 10/13/2010   Fujitsu         S.Yoshida       Update          N/A
 * 10/29/2013   Hitachi         K.Kasai         Update          QC2852
 *</pre>
 */
package business.blap.NLAL0070;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDFMsg;
import business.blap.NLAL0070.constant.NLAL0070Constant;
import business.file.NLAL0070F00FMsg;
import business.file.NLAL0070F01FMsg;
import business.file.NLAL0070F02FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INLND_SHPG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LGSC_DELY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NLAL0070Query extends S21SsmEZDQuerySupport implements NLAL0070Constant {

    /**
     * Singleton instance.
     */
    private static final NLAL0070Query myInstance = new NLAL0070Query();

    /**
     * Constructor
     */
    public NLAL0070Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLAL0070Query
     */
    public static NLAL0070Query getInstance() {
        return myInstance;
    }

    /**
     * <pre>getWhScheduleList
     * </pre>
     * 
     * @param map Map
     * @param sMsg NLAL0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getScheduleList(Map map, NLAL0070SMsg sMsg) {

        return getSsmEZDClient().queryObjectList("getScheduleList", map);

    }

    /**
     * <pre>getWhScheduleList
     * </pre>
     * 
     * @param map Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSummaryList(Map map) {

        return getSsmEZDClient().queryObjectList("getSummaryList", map);

    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NLAL0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullDownListCarrier(Map map, NLAL0070SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getPullDownListCarrier", map, sMsg.C);

    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NLAL0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApptCarrDrygList(Map map, NLAL0070SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getApptCarrDrygList", map, sMsg.D);

    }

    /**
     * <pre>getWhScheduleList
     * </pre>
     * 
     * @param map Map
     * @param sMsg NLAL0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInfoListToConnectInbdVisForImport(Map map, NLAL0070SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getInfoListToConnectInbdVisForImport", map, sMsg.G);

    }

    /**
     * <pre>getWhScheduleList
     * </pre>
     * 
     * @param map Map
     * @param sMsg NLAL0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInfoListToConnectInbdVisForDomestic(Map map, NLAL0070SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getInfoListToConnectInbdVisForDomestic", map, sMsg.H);

    }


    // **********************************************************************
    // **********************************************************************
    /**
     * get S21SsmBatchClient for CSV Download.
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Get WhSchedule Download List For Merchandise
     * </pre>
     * @param map        parameters
     * @param cMsg       NLAL0070CMsg
     * @param fMsg       NLAL0070F01FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @return result
     */
    public boolean getWhScheduleDownloadListForMdse(Map map, NLAL0070CMsg cMsg, NLAL0070F00FMsg fMsg, ZYPCSVOutFile csvOutFile) {

        return (Boolean) getSsmBatchClient().queryObject("getWhScheduleDownloadListForMdse", map,
                new CsvCreator(cMsg, fMsg, csvOutFile, CSV_DOWNLOAD_MAX_COUNT, TYPE.MDSE));

    }

    /**
     * <pre>
     * Get WhSchedule Download List For Container
     * </pre>
     * @param map        parameters
     * @param cMsg       NLAL0070CMsg
     * @param fMsg       NLAL0070F01FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @return result
     */
    public boolean getWhScheduleDownloadListForCntnrInv(Map map, NLAL0070CMsg cMsg, NLAL0070F02FMsg fMsg, ZYPCSVOutFile csvOutFile) {

        return (Boolean) getSsmBatchClient().queryObject("getWhScheduleDownloadListForCntnrInv", map,
                new CsvCreator(cMsg, fMsg, csvOutFile, CSV_DOWNLOAD_MAX_COUNT, TYPE.CNTNR));

    }

    /**
     * <pre>
     * Get Summary Download List
     * </pre>
     * @param map        parameters
     * @param cMsg       NLAL0070CMsg
     * @param fMsg       NLAL0070F01FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @return result
     */
    public boolean getSummaryDownloadList(Map map, NLAL0070CMsg cMsg, NLAL0070F01FMsg fMsg, ZYPCSVOutFile csvOutFile) {

        return (Boolean) getSsmBatchClient().queryObject("getSummaryDownloadList", map,
                new CsvCreator(cMsg, fMsg, csvOutFile, CSV_DOWNLOAD_MAX_COUNT, TYPE.SUM));

    }

    /**
     * Delivery Type when Search.
     */
    private static enum TYPE {
        MDSE, CNTNR, SUM
    }

    /**
     * Create FMsg using ResultSet.
     */
    private static class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** NLAL0070CMsg */
        private NLAL0070CMsg bizMsg;
        /** NLAL0070F00FMsg */
        private EZDFMsg fMsg;
        /** ZYPCSVOutFile */
        private ZYPCSVOutFile csvOutFile;
        /** TYPE */
        private final TYPE type;
        /** Limit Count */
        private int limitCnt;

        public CsvCreator(NLAL0070CMsg bizMsg, EZDFMsg fMsg, ZYPCSVOutFile csvOutFile, int limitCnt, TYPE type) {
            this.bizMsg     = bizMsg;
            this.fMsg       = fMsg;
            this.csvOutFile = csvOutFile;
            this.limitCnt   = limitCnt;
            this.type       = type;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();
            int cnt = rs.getRow();
//            EZDDebugOutput.println(1, "Download: records count = " + cnt, this);

            if (cnt == 0) {
                bizMsg.setMessageInfo("NZZM0000E");
                return false;
            }

            if (cnt > limitCnt) {
                bizMsg.setMessageInfo("NZZM0007E");
                return false;
            }

            rs.first();

            do {

                fMsg.clear();

                if (TYPE.MDSE.equals(type)) {
                    setCsvDataOfMdse((NLAL0070F00FMsg) fMsg, rs);

                } else if (TYPE.CNTNR.equals(type)) {
                    setCsvDataOfCntnr((NLAL0070F02FMsg) fMsg, rs);

                } else if (TYPE.SUM.equals(type)) {
                    setCsvDataOfSum((NLAL0070F01FMsg) fMsg, rs);
                }

                csvOutFile.write();

            } while (rs.next());

            return true;
        }

        /**
         * Mdse
         * 
         * @param fMsg
         * @param rs
         * @throws SQLException
         */
        private static void setCsvDataOfMdse(NLAL0070F00FMsg fMsg, ResultSet rs) throws SQLException {
            setValue(fMsg.invtyLocCd_E1,      rs.getString("INVTY_LOC_CD"));
            setValue(fMsg.rwsRefNum_E1,       rs.getString("RWS_REF_NUM"));
            setValue(fMsg.lgscDelyTpNm_E1,    rs.getString("LGSC_DELY_TP_NM"));
            setValue(fMsg.whInPrtyFlg_E1,     rs.getString("WH_IN_PRTY_FLG"));
            setValue(fMsg.inbdVisEventNm_E1,  rs.getString("INBD_VIS_EVENT_NM"));
            setValue(fMsg.xxDtTxt_E1,         rs.getString("IMPT_TRM_ETA_DT"));
            setValue(fMsg.xxDtTxt_E2,         rs.getString("LTST_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_E3,         rs.getString("TEMP_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_E4,         rs.getString("SCHD_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_E5,         rs.getString("FINAL_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_E6,         rs.getString("RAIL_AVAL_DT"));
            setValue(fMsg.apptTmTxt_E1,       rs.getString("APPT_TM_TXT"));
            setValue(fMsg.apptDrygCarrCd_E1,  rs.getString("APPT_DRYG_CARR_CD"));
            setValue(fMsg.mdseCd_E1,          rs.getString("MDSE_CD"));
            setValue(fMsg.mdseDescShortTxt_E1,rs.getString("MDSE_DESC_SHORT_TXT"));
            setValue(fMsg.reqStkInQty_E1,     rs.getBigDecimal("REQ_STK_IN_QTY"));
            setValue(fMsg.qtyPkgApvlStsCd_E1, rs.getString("MASTER_FLG"));
            setValue(fMsg.locNm_E1,           rs.getString("LOC_NM"));
            setValue(fMsg.imptInvVeslNm_E1,   rs.getString("IMPT_INV_VESL_NM"));
            setValue(fMsg.imptInvBolNum_E1,   rs.getString("IMPT_INV_BOL_NUM"));
            setValue(fMsg.rwsRefNum_E2,       rs.getString("RWS_REF_NUM_E2"));
            setValue(fMsg.sceOrdTpCd_E1,      rs.getString("SCE_ORD_TP_CD"));
            setValue(fMsg.schdTpNm_E1,        rs.getString("SCHD_TP_NM"));
            setValue(fMsg.imptTrmNm_E1,       rs.getString("IMPT_TRM_NM"));
            setValue(fMsg.whCd_E1,            rs.getString("MULTI_WH_CD"));
            setValue(fMsg.whSchdCmntTxt_E1,   rs.getString("WH_SCHD_CMNT_TXT"));
            setValue(fMsg.imptInvNum_E1,      rs.getString("IMPT_INV_NUM"));
            setValue(fMsg.imptCntnrNum_E1,    rs.getString("IMPT_CNTNR_NUM"));
            setValue(fMsg.inlndShpgMethCd_E1, rs.getString("INLND_SHPG_METH_CD"));
            setValue(fMsg.imptInvCnsgnCd_E1,  rs.getString("IMPT_INV_CNSGN_CD"));

            String     lgscDelyTpCd       = rs.getString("LGSC_DELY_TP_CD");
            String     rwsStsCd           = rs.getString("RWS_STS_CD");
            String     schdTpCd           = rs.getString("SCHD_TP_CD");
            BigDecimal imptTrmToWhDaysAot = rs.getBigDecimal("IMPT_TRM_TO_WH_DAYS_AOT");

            // If LGSC_DELY_CD equals "01", convert "NEF" to "" for LGSC_DELY_NM
            if (LGSC_DELY_TP.DEF.equals(lgscDelyTpCd)) {
                fMsg.lgscDelyTpNm_E1.clear();
            }

            // If INLND_SHPG_METH_CD equals "", convert "" to "RA" for INLND_SHPG_METH_CD
            if (!SCHD_TP.AIR.equals(schdTpCd)
                    && !SCHD_TP.DOMESTIC.equals(schdTpCd)) {
                if (!ZYPCommonFunc.hasValue(fMsg.inlndShpgMethCd_E1)) {
                    fMsg.inlndShpgMethCd_E1.setValue(INLND_SHPG_METH.RAIL);
                }
            }

            // If RWS_STS_CD equals "99", Delete RWS Infomation
            if (RWS_STS.CANCELED.equals(rwsStsCd)) {
                fMsg.rwsRefNum_E2.clear();
            }

            boolean scheduleCheckFlg = false;

            // Set Schedule ETA Check Flag
            if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_E4)) {
                fMsg.schdEtaChkFlg_E1.setValue(ZYPConstant.CHKBOX_ON_Y);
                scheduleCheckFlg = true;

            } else {

                // Set Temporary Date for WH Schedule
                if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_E3)
                        && ZYPCommonFunc.hasValue(imptTrmToWhDaysAot)) {
                    fMsg.xxDtTxt_E4.setValue(
                            ZYPDateUtil.addDays(fMsg.xxDtTxt_E3.getValue(), imptTrmToWhDaysAot.intValue()));

                } else if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_E3)
                        && !ZYPCommonFunc.hasValue(imptTrmToWhDaysAot)) {
                    fMsg.xxDtTxt_E4.setValue(fMsg.xxDtTxt_E3.getValue());

                // Set latest Date for WH Schedule
                } else {
                    fMsg.xxDtTxt_E4.setValue(fMsg.xxDtTxt_E2.getValue());
                }
            }

            // Set Final Schedule Check Flag
            if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_E5.getValue())) {
                fMsg.finalEtaChkFlg_E1.setValue(ZYPConstant.CHKBOX_ON_Y);

            } else {

                // Set latest Date for Final WH Schedule
                if (scheduleCheckFlg) {
                    fMsg.xxDtTxt_E5.setValue(fMsg.xxDtTxt_E2.getValue());
                }
            }

            //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//            fMsg.xxDtTxt_E1.setValue(dateFormatter(fMsg.xxDtTxt_E1.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_E2.setValue(dateFormatter(fMsg.xxDtTxt_E2.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_E3.setValue(dateFormatter(fMsg.xxDtTxt_E3.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_E4.setValue(dateFormatter(fMsg.xxDtTxt_E4.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_E5.setValue(dateFormatter(fMsg.xxDtTxt_E5.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_E6.setValue(dateFormatter(fMsg.xxDtTxt_E6.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
            fMsg.xxDtTxt_E1.setValue(dateFormatter(fMsg.xxDtTxt_E1.getValue()));
            fMsg.xxDtTxt_E2.setValue(dateFormatter(fMsg.xxDtTxt_E2.getValue()));
            fMsg.xxDtTxt_E3.setValue(dateFormatter(fMsg.xxDtTxt_E3.getValue()));
            fMsg.xxDtTxt_E4.setValue(dateFormatter(fMsg.xxDtTxt_E4.getValue()));
            fMsg.xxDtTxt_E5.setValue(dateFormatter(fMsg.xxDtTxt_E5.getValue()));
            fMsg.xxDtTxt_E6.setValue(dateFormatter(fMsg.xxDtTxt_E6.getValue()));
            //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
        }

        /**
         * Container
         * 
         * @param fMsg
         * @param rs
         * @throws SQLException
         */
        private static void setCsvDataOfCntnr(NLAL0070F02FMsg fMsg, ResultSet rs) throws SQLException {
            setValue(fMsg.invtyLocCd_I1,      rs.getString("INVTY_LOC_CD"));
            setValue(fMsg.rwsRefNum_I1,       rs.getString("RWS_REF_NUM"));
            setValue(fMsg.lgscDelyTpNm_I1,    rs.getString("LGSC_DELY_TP_NM"));
            setValue(fMsg.whInPrtyFlg_I1,     rs.getString("WH_IN_PRTY_FLG"));
            setValue(fMsg.inbdVisEventNm_I1,  rs.getString("INBD_VIS_EVENT_NM"));
            setValue(fMsg.xxDtTxt_I1,         rs.getString("IMPT_TRM_ETA_DT"));
            setValue(fMsg.xxDtTxt_I2,         rs.getString("LTST_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_I3,         rs.getString("TEMP_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_I4,         rs.getString("SCHD_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_I5,         rs.getString("FINAL_WH_IN_ETA_DT"));
            setValue(fMsg.xxDtTxt_I6,         rs.getString("RAIL_AVAL_DT"));
            setValue(fMsg.apptTmTxt_I1,       rs.getString("APPT_TM_TXT"));
            setValue(fMsg.apptDrygCarrCd_I1,  rs.getString("APPT_DRYG_CARR_CD"));
            setValue(fMsg.mdseCd_I1,          rs.getString("MDSE_CD"));
            setValue(fMsg.mdseDescShortTxt_I1,rs.getString("MDSE_DESC_SHORT_TXT"));
            setValue(fMsg.reqStkInQty_I1,     rs.getBigDecimal("REQ_STK_IN_QTY"));
            setValue(fMsg.qtyPkgApvlStsCd_I1, rs.getString("MASTER_FLG"));
            setValue(fMsg.locNm_I1,           rs.getString("LOC_NM"));
            setValue(fMsg.imptInvVeslNm_I1,   rs.getString("IMPT_INV_VESL_NM"));
            setValue(fMsg.imptInvBolNum_I1,   rs.getString("IMPT_INV_BOL_NUM"));
            setValue(fMsg.rwsRefNum_I2,       rs.getString("RWS_REF_NUM_I2"));
            setValue(fMsg.sceOrdTpCd_I1,      rs.getString("SCE_ORD_TP_CD"));
            setValue(fMsg.schdTpNm_I1,        rs.getString("SCHD_TP_NM"));
            setValue(fMsg.imptTrmNm_I1,       rs.getString("IMPT_TRM_NM"));
            setValue(fMsg.whCd_I1,            rs.getString("MULTI_WH_CD"));
            setValue(fMsg.whSchdCmntTxt_I1,   rs.getString("WH_SCHD_CMNT_TXT"));
            setValue(fMsg.imptInvNum_I1,      rs.getString("IMPT_INV_NUM"));
            setValue(fMsg.imptInvCnsgnCd_I1,  rs.getString("IMPT_INV_CNSGN_CD"));
            setValue(fMsg.imptCntnrNum_I1,    rs.getString("IMPT_CNTNR_NUM"));
            setValue(fMsg.inlndShpgMethCd_I1, rs.getString("INLND_SHPG_METH_CD"));

            String     lgscDelyTpCd       = rs.getString("LGSC_DELY_TP_CD");
            String     schdTpCd           = rs.getString("SCHD_TP_CD");
            String     rwsStsCd           = rs.getString("RWS_STS_CD");
            BigDecimal imptTrmToWhDaysAot = rs.getBigDecimal("IMPT_TRM_TO_WH_DAYS_AOT");

            // If LGSC_DELY_CD equals "01", convert "NEF" to "" for LGSC_DELY_NM
            if (LGSC_DELY_TP.DEF.equals(lgscDelyTpCd)) {
                fMsg.lgscDelyTpNm_I1.clear();
            }

            // If INLND_SHPG_METH_CD equals "", convert "" to "RA" for INLND_SHPG_METH_CD
            if (!SCHD_TP.AIR.equals(schdTpCd)
                    && !SCHD_TP.DOMESTIC.equals(schdTpCd)) {
                if (!ZYPCommonFunc.hasValue(fMsg.inlndShpgMethCd_I1)) {
                    fMsg.inlndShpgMethCd_I1.setValue(INLND_SHPG_METH.RAIL);
                }
            }

            // If RWS_STS_CD equals "99", Delete RWS Infomation
            if (RWS_STS.CANCELED.equals(rwsStsCd)) {
                fMsg.rwsRefNum_I2.clear();
            }

            fMsg.mdseCd_I1.clear();
            fMsg.mdseDescShortTxt_I1.clear();
            fMsg.reqStkInQty_I1.clear();
            fMsg.qtyPkgApvlStsCd_I1.clear();

            boolean scheduleCheckFlg = false;

            // Set Schedule ETA Check Flag
            if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_I4)) {
                fMsg.schdEtaChkFlg_I1.setValue(ZYPConstant.CHKBOX_ON_Y);
                scheduleCheckFlg = true;

            } else {

                // Set Temporary Date for WH Schedule
                if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_I3)
                        && ZYPCommonFunc.hasValue(imptTrmToWhDaysAot)) {
                    fMsg.xxDtTxt_I4.setValue(
                            ZYPDateUtil.addDays(fMsg.xxDtTxt_I3.getValue(), imptTrmToWhDaysAot.intValue()));

                } else if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_I3)
                        && !ZYPCommonFunc.hasValue(imptTrmToWhDaysAot)) {
                    fMsg.xxDtTxt_I4.setValue(fMsg.xxDtTxt_I3.getValue());

                // Set latest Date for WH Schedule
                } else {
                    fMsg.xxDtTxt_I4.setValue(fMsg.xxDtTxt_I2.getValue());
                }
            }

            // Set Final Schedule Check Flag
            if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt_I5.getValue())) {
                fMsg.finalEtaChkFlg_I1.setValue(ZYPConstant.CHKBOX_ON_Y);

            } else {

                // Set latest Date for Final WH Schedule
                if (scheduleCheckFlg) {
                    fMsg.xxDtTxt_I5.setValue(fMsg.xxDtTxt_I2.getValue());
                }
            }

            //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//            fMsg.xxDtTxt_I1.setValue(dateFormatter(fMsg.xxDtTxt_I1.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_I2.setValue(dateFormatter(fMsg.xxDtTxt_I2.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_I3.setValue(dateFormatter(fMsg.xxDtTxt_I3.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_I4.setValue(dateFormatter(fMsg.xxDtTxt_I4.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_I5.setValue(dateFormatter(fMsg.xxDtTxt_I5.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
//            fMsg.xxDtTxt_I6.setValue(dateFormatter(fMsg.xxDtTxt_I6.getValue(), "yyyyMMdd", "MM/dd/yyyy"));
            fMsg.xxDtTxt_I1.setValue(dateFormatter(fMsg.xxDtTxt_I1.getValue()));
            fMsg.xxDtTxt_I2.setValue(dateFormatter(fMsg.xxDtTxt_I2.getValue()));
            fMsg.xxDtTxt_I3.setValue(dateFormatter(fMsg.xxDtTxt_I3.getValue()));
            fMsg.xxDtTxt_I4.setValue(dateFormatter(fMsg.xxDtTxt_I4.getValue()));
            fMsg.xxDtTxt_I5.setValue(dateFormatter(fMsg.xxDtTxt_I5.getValue()));
            fMsg.xxDtTxt_I6.setValue(dateFormatter(fMsg.xxDtTxt_I6.getValue()));
            //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
        }

        /**
         * Summary
         * 
         * @param fMsg
         * @param rs
         * @throws SQLException
         */
        private static void setCsvDataOfSum(NLAL0070F01FMsg fMsg, ResultSet rs) throws SQLException {
            setValue(fMsg.invtyLocCd_F1,  rs.getString("INVTY_LOC_CD"));
            setValue(fMsg.schdTpNm_F1,    rs.getString("SCHD_TP_NM"));
            //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
//            setValue(fMsg.xxDtTxt_F1,     dateFormatter(rs.getString("LTST_WH_IN_ETA_DT"), "yyyyMMdd", "MM/dd/yyyy"));
            setValue(fMsg.xxDtTxt_F1,     dateFormatter(rs.getString("LTST_WH_IN_ETA_DT")));
            //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
            setValue(fMsg.rwsQty_F1,      rs.getBigDecimal("RWS_QTY"));
            setValue(fMsg.pltQty_F1,      rs.getBigDecimal("PLT_QTY"));
            setValue(fMsg.cseQty_F1,      rs.getBigDecimal("CSE_QTY"));
        }

        //START 2013/10/29 K.Kasai[MOD, Defect.#2852]
        /**
         * Date Format
         * 
         * @param  date      Date
         * @param  inFormat  in date format pattern.
         * @param  outFormat out date format pattern.
         * @return after formated Date
         */
//        private static String dateFormatter(String date, String inFormat, String outFormat) {
        private static String dateFormatter(String date) {
            if (!ZYPCommonFunc.hasValue(date)) {
                return "";
            }
//            return ZYPDateUtil.DateFormatter(date, inFormat, outFormat);
            return ZYPDateUtil.formatEzd8ToDisp(date);
        }
        //END 2013/10/29 K.Kasai[MOD, Defect.#2852]
    }
}
