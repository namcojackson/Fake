/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB252001;

import static com.canon.cusa.s21.batch.NWA.NWAB252001.constant.NWAB252001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.ART10FMsg;
import business.db.DEF_WH_MAP_TMPLTMsg;
import business.db.ORD_DEF_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Mass Upload Default WH Mapping Template
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         K.Kojima        Create          N/A
 * 2016/04/05   Hitachi         K.Kojima        Update          CSA QC#6330
 * 2016/04/06   Hitachi         K.Kojima        Update          CSA QC#6258
 * 2016/05/29   SRAA            K.Aratani       Update          QC#8596
 * 2016/06/16   Hitachi         Y.Takeno        Update          QC#8156
 * 2017/09/12   Fujitsu         T.Ogura         Update          QC#19805
 *</pre>
 */
public class NWAB252001 extends S21BatchMain {

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Commit Max Number */
    private int commitNumber = 0;

    /** S21RequestBatchHelper */
    private S21RequestBatchHelper reqbatch = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /** Code Map (MDSE_ITEM_CLS_TP) */
    private Map<String, Map<String, Object>> mdseItemClsTpMap = null;

    /** Code Map (RTL_WH) */
    // START 2016/04/05 K.Kojima [QC#6330,MOD]
    // private Map<String, Map<String, Object>> rtlWhMap = null;
    // START 2016/04/06 K.Kojima [QC#6258,MOD]
    // private Map<String, List<Map<String, Object>>> rtlWhMap = null;
    private Map<String, Map<String, Object>> rtlWhMap = null;

    // END 2016/04/06 K.Kojima [QC#6258,MOD]

    // END 2016/04/05 K.Kojima [QC#6330,MOD]

    /** Code Map (SWH) */
    private Map<String, Map<String, Object>> swhMap = null;

    /** Code Map (ORD_LINE_SRC) */
    private Map<String, Map<String, Object>> ordLineSrcMap = null;

    /** Duplicated File Map */
    private Map<String, List<Map<String, Object>>> duplicatedFileMap = null;

    /** Duplicated Record Map */
    private Map<String, List<Map<String, Object>>> duplicatedRecordMap = null;

    /** Insert List ORD_DEF_WHTMsg */
    private List<ORD_DEF_WHTMsg> tMsgList = null;

    // START 2016/04/06 K.Kojima [QC#6258,MOD]
    // /** DROP_SHIP_RTL_WH_CD */
    // private String dropShipWhCd = null;
    /** DROP_SHIP_PHYS_WH_CD */
    private String dropShipPhysWhCd = null;

    // END 2016/04/06 K.Kojima [QC#6258,MOD]

    /**
     * initRoutine
     */
    @Override
    protected void initRoutine() {

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.reqbatch = new S21RequestBatchHelper();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.mdseItemClsTpMap = new HashMap<String, Map<String, Object>>();
        // START 2016/04/05 K.Kojima [QC#6330,MOD]
        // this.rtlWhMap = new HashMap<String, Map<String, Object>>();
        // START 2016/04/06 K.Kojima [QC#6258,MOD]
        // this.rtlWhMap = new HashMap<String, List<Map<String,
        // Object>>>();
        this.rtlWhMap = new HashMap<String, Map<String, Object>>();
        // END 2016/04/06 K.Kojima [QC#6258,MOD]
        // END 2016/04/05 K.Kojima [QC#6330,MOD]
        this.swhMap = new HashMap<String, Map<String, Object>>();
        this.ordLineSrcMap = new HashMap<String, Map<String, Object>>();
        // START 2016/04/06 K.Kojima [QC#6258,MOD]
        // this.dropShipWhCd =
        // ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD",
        // getGlobalCompanyCode());
        this.dropShipPhysWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_PHYS_WH_CD", getGlobalCompanyCode());
        // END 2016/04/06 K.Kojima [QC#6258,MOD]
    }

    /**
     * mainRoutine
     */
    @Override
    protected void mainRoutine() {
        if (!this.reqbatch.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }
        for (ART10FMsg reqmsg : this.reqbatch.getRequestList()) {
            this.doProcess(reqmsg);
        }
    }

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg reqMsg) {

        this.duplicatedFileMap = new HashMap<String, List<Map<String, Object>>>();
        this.duplicatedRecordMap = new HashMap<String, List<Map<String, Object>>>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String glblCmpyCd = reqMsg.EZInCompanyCode.getValue();
            String upldCsvRqstPk = reqMsg.EZREQCondition.getValue();
            String upldCsvId = reqMsg.EZREQUserKey.getValue();

            this.messenger = new ZYPCSVUploadMessenger(upldCsvId, new BigDecimal(upldCsvRqstPk));

            BigDecimal wrkCount = getCountWrkData(glblCmpyCd, upldCsvRqstPk);
            if (wrkCount.intValue() == 0) {
                this.messenger.addMessageForFile(NWAM0803E, null);
                this.messenger.uploadMessage();
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            Map<String, Object> stesParam = new HashMap<String, Object>();
            stesParam.put("glblCmpyCd", glblCmpyCd);
            stesParam.put("upldCsvRqstPk", new BigDecimal(upldCsvRqstPk));
            ps = this.ssmLLClient.createPreparedStatement("getWrkData", stesParam, execParam());
            rs = ps.executeQuery();

            this.tMsgList = new ArrayList<ORD_DEF_WHTMsg>(wrkCount.intValue());

            int recordCount = 0;
            while (rs.next()) {
                recordCount++;

                if (!checkMandatory(rs)) {
                    this.tMsgList = null;
                    continue;
                }

                if (!checkFormat(rs)) {
                    this.tMsgList = null;
                    continue;
                }

                if (!checkMaster1(glblCmpyCd, rs)) {
                    this.tMsgList = null;
                    continue;
                }

                if (!checkMaster2(glblCmpyCd, upldCsvId, upldCsvRqstPk, rs)) {
                    this.tMsgList = null;
                    continue;
                }
            }
            if (this.tMsgList == null) {
                // START 2016/06/16 [QC#8156,MOD]
                // this.messenger.addMessageForFile(NWAM0804E, null);
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, recordCount));
                this.messenger.uploadMessage();
                // END 2016/06/16 [QC#8156,MOD]
                this.errorCount += recordCount;
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
                this.termCd = TERM_CD.WARNING_END;
            } else {
                if (UPLOAD_CSV_ID_REPLACE.equals(upldCsvId)) {
                    ORD_DEF_WHTMsg removeTMsg = new ORD_DEF_WHTMsg();
                    ZYPEZDItemValueSetter.setValue(removeTMsg.glblCmpyCd, glblCmpyCd);
                    S21FastTBLAccessor.logicalRemoveByPartialValue(removeTMsg, new String[] {"glblCmpyCd" });
                }
                int insertCount = 0;
                for (int i = 0; i < this.tMsgList.size(); i++) {
                    EZDTBLAccessor.create(this.tMsgList.get(i));
                    insertCount++;
                    if (insertCount == this.commitNumber) {
                        commit();
                        insertCount = 0;
                    }
                }
                if (insertCount > 0) {
                    commit();
                    insertCount = 0;
                }
                // START 2016/06/16 [QC#8156,MOD]
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(recordCount, 0, 0));
                this.messenger.uploadMessage();
                // END 2016/06/16 [QC#8156,MOD]
                this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
                this.normalCount += recordCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * termRoutine
     */
    @Override
    protected void termRoutine() {
        int requestCnt = this.reqbatch.getRecordCount();
        S21InfoLogOutput.printlnv(ZZBM0009I, this.reqbatch.getFilePath(), "read", requestCnt);
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB252001().executeBatch(NWAB252001.class.getSimpleName());
    }

    /**
     * checkMandatory
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatory(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkMandatoryColumn(rs, DEF_WH_MAP_TMPL_CD, CSV_DEF_WH_MAP_TMPL_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, MDSE_ITEM_CLS_TP_DESC_TXT, CSV_MDSE_ITEM_CLS_TP_DESC_TXT)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, FROM_POST_CD, CSV_FROM_POST_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryColumn(rs, TO_POST_CD, CSV_TO_POST_CD)) {
            checkResult = false;
        }

        if (!checkMandatoryWh(rs, OTBD_PRIM_ON_HND_CHK_WH_CD, OTBD_PRIM_ON_HND_CHK_SWH_NM, CSV_OTBD_PRIM_ON_HND_CHK_WH_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryWh(rs, OTBD_SCD_ON_HND_CHK_WH_CD, OTBD_SCD_ON_HND_CHK_SWH_NM, CSV_OTBD_SCD_ON_HND_CHK_WH_CD)) {
            checkResult = false;
        }
        if (!checkMandatoryWh(rs, OTBD_DEF_WH_CD, OTBD_DEF_SWH_NM, CSV_OTBD_DEF_WH_CD)) {
            checkResult = false;
        }
        // 2017/09/12 QC#19805 Del Start
//        if (!checkMandatoryWh(rs, RMA_DEF_WH_CD, RMA_DEF_SWH_NM, CSV_RMA_DEF_WH_CD)) {
//            checkResult = false;
//        }
        // 2017/09/12 QC#19805 Del End

        return checkResult;
    }

    /**
     * checkFormat
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkFormat(ResultSet rs) throws SQLException {
        boolean checkResult = true;

        if (!checkFormatFlag(rs, THIRD_PTY_ITEM_FLG, CSV_THIRD_PTY_ITEM_FLG)) {
            checkResult = false;
        }
        if (!checkFormatFlag(rs, ON_HND_CHK_FLG, CSV_ON_HND_CHK_FLG)) {
            checkResult = false;
        }

        return checkResult;
    }

    /**
     * checkMaster1
     * @param glblCmpyCd String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMaster1(String glblCmpyCd, ResultSet rs) throws SQLException {
        String defWhMapTmplCd = rs.getString(DEF_WH_MAP_TMPL_CD);
        DEF_WH_MAP_TMPLTMsg tMsg = findByKeyDefWhMapTmpl(glblCmpyCd, defWhMapTmplCd);
        if (tMsg == null) {
            BigDecimal upldCsvRqstRowNum = (BigDecimal) rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_DEF_WH_MAP_TMPL_CD);
            return false;
        }
        return true;
    }

    /**
     * checkMaster2
     * @param glblCmpyCd String
     * @param upldCsvId String
     * @param upldCsvRqstPk String
     * @param rs ResultSet
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMaster2(String glblCmpyCd, String upldCsvId, String upldCsvRqstPk, ResultSet rs) throws SQLException {
        boolean checkResult = true;

        BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);

        // Get Code MDSE_ITEM_CLS_TP_DESC_TXT
        String mdseItemClsTpDescTxt = rs.getString(MDSE_ITEM_CLS_TP_DESC_TXT);
        String mdseItemClsTpCd = null;
        if (!"*".equals(mdseItemClsTpDescTxt)) {
            Map<String, Object> mdseItemClsTpDescTxtInfo = getMdseItemClsTpInfo(glblCmpyCd, mdseItemClsTpDescTxt);
            if (mdseItemClsTpDescTxtInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_MDSE_ITEM_CLS_TP_DESC_TXT);
                checkResult = false;
            } else {
                mdseItemClsTpCd = (String) mdseItemClsTpDescTxtInfo.get(MDSE_ITEM_CLS_TP_CD);
            }
        } else {
            mdseItemClsTpCd = "*";
        }

        // Get Code OTBD_PRIM_ON_HND_CHK_SWH_NM
        String otbdPrimOnHndChkSwhNm = rs.getString(OTBD_PRIM_ON_HND_CHK_SWH_NM);
        String otbdPrimOnHndChkSwhCd = null;
        if (otbdPrimOnHndChkSwhNm != null && otbdPrimOnHndChkSwhNm.length() > 0) {
            Map<String, Object> otbdPrimOnHndChkSwhNmInfo = getSwhInfo(glblCmpyCd, otbdPrimOnHndChkSwhNm);
            if (otbdPrimOnHndChkSwhNmInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_PRIM_ON_HND_CHK_SWH_NM);
                checkResult = false;
            } else {
                otbdPrimOnHndChkSwhCd = (String) otbdPrimOnHndChkSwhNmInfo.get(RTL_SWH_CD);
            }
        }

        // Get Code OTBD_PRIM_ON_HND_LIN_SRC_NM
        String otbdPrimOnHndLinSrcNm = rs.getString(OTBD_PRIM_ON_HND_LIN_SRC_NM);
        String otbdPrimOnHndLinSrcCd = null;
        String otbdPrimOrdLineSrcCatgCd = null;
        if (otbdPrimOnHndLinSrcNm != null && otbdPrimOnHndLinSrcNm.length() > 0) {
            Map<String, Object> otbdPrimOnHndLinSrcNmInfo = getOrdLineSrcInfo(glblCmpyCd, otbdPrimOnHndLinSrcNm);
            if (otbdPrimOnHndLinSrcNmInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_PRIM_ON_HND_LIN_SRC_NM);
                checkResult = false;
            } else {
                otbdPrimOnHndLinSrcCd = (String) otbdPrimOnHndLinSrcNmInfo.get(ORD_LINE_SRC_CD);
                otbdPrimOrdLineSrcCatgCd = (String) otbdPrimOnHndLinSrcNmInfo.get(ORD_LINE_SRC_CATG_CD);
            }
        }

        // Get Code OTBD_SCD_ON_HND_CHK_SWH_NM
        String otbdScdOnHndChkSwhNm = rs.getString(OTBD_SCD_ON_HND_CHK_SWH_NM);
        String otbdScdOnHndChkSwhCd = null;
        if (otbdScdOnHndChkSwhNm != null && otbdScdOnHndChkSwhNm.length() > 0) {
            Map<String, Object> otbdScdOnHndChkSwhNmInfo = getSwhInfo(glblCmpyCd, otbdScdOnHndChkSwhNm);
            if (otbdScdOnHndChkSwhNmInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_SCD_ON_HND_CHK_SWH_NM);
                checkResult = false;
            } else {
                otbdScdOnHndChkSwhCd = (String) otbdScdOnHndChkSwhNmInfo.get(RTL_SWH_CD);
            }
        }

        // Get Code OTBD_SCD_ON_HND_LINE_SRC_NM
        String otbdScdOnHndLineSrcNm = rs.getString(OTBD_SCD_ON_HND_LINE_SRC_NM);
        String otbdScdOnHndLineSrcCd = null;
        String otbdScdOrdLineSrcCatgCd = null;
        if (otbdScdOnHndLineSrcNm != null && otbdScdOnHndLineSrcNm.length() > 0) {
            Map<String, Object> otbdScdOnHndLineSrcNmInfo = getOrdLineSrcInfo(glblCmpyCd, otbdScdOnHndLineSrcNm);
            if (otbdScdOnHndLineSrcNmInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_SCD_ON_HND_LINE_SRC_NM);
                checkResult = false;
            } else {
                otbdScdOnHndLineSrcCd = (String) otbdScdOnHndLineSrcNmInfo.get(ORD_LINE_SRC_CD);
                otbdScdOrdLineSrcCatgCd = (String) otbdScdOnHndLineSrcNmInfo.get(ORD_LINE_SRC_CATG_CD);
            }
        }

        // Get Code OTBD_DEF_SWH_NM
        String otbdDefSwhNm = rs.getString(OTBD_DEF_SWH_NM);
        String otbdDefSwhCd = null;
        if (otbdDefSwhNm != null && otbdDefSwhNm.length() > 0) {
            Map<String, Object> otbdDefSwhNmInfo = getSwhInfo(glblCmpyCd, otbdDefSwhNm);
            if (otbdDefSwhNmInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_DEF_SWH_NM);
                checkResult = false;
            } else {
                otbdDefSwhCd = (String) otbdDefSwhNmInfo.get(RTL_SWH_CD);
            }
        }

        // Get Code OTBD_SCD_ON_HND_LINE_SRC_NM
        String otbdDefLineSrcNm = rs.getString(OTBD_DEF_LINE_SRC_NM);
        String otbdDefLineSrcCd = null;
        String otbdDefOrdLineSrcCatgCd = null;
        if (otbdDefLineSrcNm != null && otbdDefLineSrcNm.length() > 0) {
            Map<String, Object> otbdDefLineSrcNmInfo = getOrdLineSrcInfo(glblCmpyCd, otbdDefLineSrcNm);
            if (otbdDefLineSrcNmInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_DEF_LINE_SRC_NM);
                checkResult = false;
            } else {
                otbdDefLineSrcCd = (String) otbdDefLineSrcNmInfo.get(ORD_LINE_SRC_CD);
                otbdDefOrdLineSrcCatgCd = (String) otbdDefLineSrcNmInfo.get(ORD_LINE_SRC_CATG_CD);
            }
        }

        // 2017/09/12 QC#19805 Del Start
//        // Get Code RMA_DEF_SWH_NM
//        String rmaDefSwhNm = rs.getString(RMA_DEF_SWH_NM);
//        String rmaDefSwhCd = null;
//        if (rmaDefSwhNm != null && rmaDefSwhNm.length() > 0) {
//            Map<String, Object> rmaDefSwhNmInfo = getSwhInfo(glblCmpyCd, rmaDefSwhNm);
//            if (rmaDefSwhNmInfo == null) {
//                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_RMA_DEF_SWH_NM);
//                checkResult = false;
//            } else {
//                rmaDefSwhCd = (String) rmaDefSwhNmInfo.get(RTL_SWH_CD);
//            }
//        }
//
//        // Get Code RMA_DEF_LINE_SRC_NM
//        String rmaDefLineSrcNm = rs.getString(RMA_DEF_LINE_SRC_NM);
//        String rmaDefLineSrcCd = null;
//        String rmaDefOrdLineSrcCatgCd = null;
//        if (rmaDefLineSrcNm != null && rmaDefLineSrcNm.length() > 0) {
//            Map<String, Object> rmaDefLineSrcNmInfo = getOrdLineSrcInfo(glblCmpyCd, rmaDefLineSrcNm);
//            if (rmaDefLineSrcNmInfo == null) {
//                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_RMA_DEF_LINE_SRC_NM);
//                checkResult = false;
//            } else {
//                rmaDefLineSrcCd = (String) rmaDefLineSrcNmInfo.get(ORD_LINE_SRC_CD);
//                rmaDefOrdLineSrcCatgCd = (String) rmaDefLineSrcNmInfo.get(ORD_LINE_SRC_CATG_CD);
//            }
//        }
        // 2017/09/12 QC#19805 Del End

        // Check Master : OTBD_PRIM_ON_HND_CHK_WH_CD
        String otbdPrimOnHndChkWhCd = rs.getString(OTBD_PRIM_ON_HND_CHK_WH_CD);
        // START 2016/04/05 K.Kojima [QC#6330,MOD]
        // String otbdPrimOnHndChkRtlWhCd = null;
        // if (otbdPrimOnHndChkWhCd != null &&
        // otbdPrimOnHndChkWhCd.length() > 0) {
        // Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd,
        // otbdPrimOnHndChkWhCd);
        // if (rtlWhInfo == null) {
        // uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E,
        // CSV_OTBD_PRIM_ON_HND_CHK_WH_CD);
        // checkResult = false;
        // } else {
        // otbdPrimOnHndChkRtlWhCd = (String)
        // rtlWhInfo.get(RTL_WH_CD);
        // }
        // }
        // START 2016/04/06 K.Kojima [QC#6258,DEL]
        // List<Map<String, Object>> otbdPrimOnHndChkRtlWhCd = null;
        // END 2016/04/06 K.Kojima [QC#6258,DEL]
        if (otbdPrimOnHndChkWhCd != null && otbdPrimOnHndChkWhCd.length() > 0) {
            // START 2016/04/06 K.Kojima [QC#6258,MOD]
            // otbdPrimOnHndChkRtlWhCd = getRtlWhInfo(glblCmpyCd,
            // otbdPrimOnHndChkWhCd);
            // if (otbdPrimOnHndChkRtlWhCd == null ||
            // otbdPrimOnHndChkRtlWhCd.size() == 0) {
            // uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E,
            // CSV_OTBD_PRIM_ON_HND_CHK_WH_CD);
            // otbdPrimOnHndChkRtlWhCd = null;
            // checkResult = false;
            // }
            Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd, otbdPrimOnHndChkWhCd);
            if (rtlWhInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_PRIM_ON_HND_CHK_WH_CD);
                checkResult = false;
            }
            // END 2016/04/06 K.Kojima [QC#6258,MOD]
        }
        // END 2016/04/05 K.Kojima [QC#6330,MOD]

        // Check Master : OTBD_SCD_ON_HND_CHK_WH_CD
        String otbdScdOnHndChkWhCd = rs.getString(OTBD_SCD_ON_HND_CHK_WH_CD);
        // START 2016/04/05 K.Kojima [QC#6330,MOD]
        // String otbdScdOnHndChkRtlWhCd = null;
        // if (otbdScdOnHndChkWhCd != null &&
        // otbdScdOnHndChkWhCd.length() > 0) {
        // Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd,
        // otbdScdOnHndChkWhCd);
        // if (rtlWhInfo == null) {
        // uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E,
        // CSV_OTBD_SCD_ON_HND_CHK_WH_CD);
        // checkResult = false;
        // } else {
        // otbdScdOnHndChkRtlWhCd = (String) rtlWhInfo.get(RTL_WH_CD);
        // }
        // }
        // START 2016/04/06 K.Kojima [QC#6258,DEL]
        // List<Map<String, Object>> otbdScdOnHndChkRtlWhCd = null;
        // END 2016/04/06 K.Kojima [QC#6258,DEL]
        if (otbdScdOnHndChkWhCd != null && otbdScdOnHndChkWhCd.length() > 0) {
            // START 2016/04/06 K.Kojima [QC#6258,MOD]
            // otbdScdOnHndChkRtlWhCd = getRtlWhInfo(glblCmpyCd,
            // otbdScdOnHndChkWhCd);
            // if (otbdScdOnHndChkRtlWhCd == null ||
            // otbdScdOnHndChkRtlWhCd.size() == 0) {
            // uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E,
            // CSV_OTBD_SCD_ON_HND_CHK_WH_CD);
            // otbdScdOnHndChkRtlWhCd = null;
            // checkResult = false;
            // }
            Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd, otbdScdOnHndChkWhCd);
            if (rtlWhInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_SCD_ON_HND_CHK_WH_CD);
                checkResult = false;
            }
            // END 2016/04/06 K.Kojima [QC#6258,MOD]
        }
        // END 2016/04/05 K.Kojima [QC#6330,MOD]

        // Check Master : OTBD_DEF_WH_CD
        String otbdDefWhCd = rs.getString(OTBD_DEF_WH_CD);
        // START 2016/04/05 K.Kojima [QC#6330,MOD]
        // String otbdDefRtlWhCd = null;
        // if (otbdDefWhCd != null && otbdDefWhCd.length() > 0) {
        // Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd,
        // otbdDefWhCd);
        // if (rtlWhInfo == null) {
        // uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E,
        // CSV_OTBD_DEF_WH_CD);
        // checkResult = false;
        // } else {
        // otbdDefRtlWhCd = (String) rtlWhInfo.get(RTL_WH_CD);
        // }
        // }
        // START 2016/04/06 K.Kojima [QC#6258,DEL]
        // List<Map<String, Object>> otbdDefRtlWhCd = null;
        // END 2016/04/06 K.Kojima [QC#6258,DEL]
        if (otbdDefWhCd != null && otbdDefWhCd.length() > 0) {
            // START 2016/04/06 K.Kojima [QC#6258,MOD]
            // otbdDefRtlWhCd = getRtlWhInfo(glblCmpyCd, otbdDefWhCd);
            // if (otbdDefRtlWhCd == null || otbdDefRtlWhCd.size() ==
            // 0) {
            // uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E,
            // CSV_OTBD_DEF_WH_CD);
            // otbdDefRtlWhCd = null;
            // checkResult = false;
            // }
            Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd, otbdDefWhCd);
            if (rtlWhInfo == null) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_DEF_WH_CD);
                checkResult = false;
            }
            // END 2016/04/06 K.Kojima [QC#6258,MOD]
        }
        // END 2016/04/05 K.Kojima [QC#6330,MOD]

        // 2017/09/12 QC#19805 Del Start
//        // Check Master : RMA_DEF_WH_CD
//        String rmaDefWhCd = rs.getString(RMA_DEF_WH_CD);
//        if (rmaDefWhCd != null && rmaDefWhCd.length() > 0) {
//            // START 2016/04/05 K.Kojima [QC#6330,MOD]
//            // Map<String, Object> rtlWhInfo =
//            // getRtlWhInfo(glblCmpyCd, rmaDefWhCd);
//            // if (rtlWhInfo == null) {
//            // START 2016/04/06 K.Kojima [QC#6258,MOD]
//            // List<Map<String, Object>> rtlWhInfo =
//            // getRtlWhInfo(glblCmpyCd, rmaDefWhCd);
//            // if (rtlWhInfo == null || rtlWhInfo.size() == 0) {
//            Map<String, Object> rtlWhInfo = getRtlWhInfo(glblCmpyCd, rmaDefWhCd);
//            if (rtlWhInfo == null) {
//                // END 2016/04/06 K.Kojima [QC#6258,MOD]
//                // END 2016/04/05 K.Kojima [QC#6330,MOD]
//                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_RMA_DEF_WH_CD);
//                checkResult = false;
//            }
//        }
        // 2017/09/12 QC#19805 Del End

        // Check CatgCd : OTBD_PRIM_ON_HND_LIN_SRC_CD
        if (otbdPrimOrdLineSrcCatgCd != null) {
            if (!otbdPrimOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.INTERNAL)) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_PRIM_ON_HND_LIN_SRC_NM);
                checkResult = false;
                // START 2016/04/05 K.Kojima [QC#6330,MOD]
                // } else if (otbdPrimOnHndChkRtlWhCd != null &&
                // otbdPrimOnHndChkRtlWhCd.equals(dropShipWhCd)) {
                // START 2016/04/06 K.Kojima [QC#6258,MOD]
                // } else if (otbdPrimOnHndChkRtlWhCd != null &&
                // checkDropShipWhCd(otbdPrimOnHndChkRtlWhCd,
                // dropShipWhCd) == false) {
            } else if (otbdPrimOnHndChkWhCd != null && otbdPrimOnHndChkWhCd.equals(dropShipPhysWhCd)) {
                // END 2016/04/06 K.Kojima [QC#6258,MOD]
                // END 2016/04/05 K.Kojima [QC#6330,MOD]
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0808E, CSV_OTBD_PRIM_ON_HND_LIN_SRC_NM);
                checkResult = false;
            }
        }
        // Check CatgCd : OTBD_SCD_ON_HND_LINE_SRC_CD
        if (otbdScdOrdLineSrcCatgCd != null) {
            if (!otbdScdOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.INTERNAL)) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_SCD_ON_HND_LINE_SRC_NM);
                checkResult = false;
                // START 2016/04/05 K.Kojima [QC#6330,MOD]
                // } else if (otbdScdOnHndChkRtlWhCd != null &&
                // otbdScdOnHndChkRtlWhCd.equals(dropShipWhCd)) {
                // START 2016/04/06 K.Kojima [QC#6258,MOD]
                // } else if (otbdScdOnHndChkRtlWhCd != null &&
                // checkDropShipWhCd(otbdScdOnHndChkRtlWhCd,
                // dropShipWhCd) == false) {
            } else if (otbdScdOnHndChkWhCd != null && otbdScdOnHndChkWhCd.equals(dropShipPhysWhCd)) {
                // END 2016/04/06 K.Kojima [QC#6258,MOD]
                // END 2016/04/05 K.Kojima [QC#6330,MOD]
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0808E, CSV_OTBD_SCD_ON_HND_LINE_SRC_NM);
                checkResult = false;
            }
        }
        // Check CatgCd : OTBD_DEF_LINE_SRC_CD
        if (otbdDefOrdLineSrcCatgCd != null) {
            if (!otbdDefOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.INTERNAL) && !otbdDefOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.EXTERNAL)) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_OTBD_DEF_LINE_SRC_NM);
                checkResult = false;
                // START 2016/04/05 K.Kojima [QC#6330,MOD]
                // } else if
                // (otbdDefOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.INTERNAL)
                // && otbdDefRtlWhCd != null &&
                // otbdDefRtlWhCd.equals(dropShipWhCd)) {
                // START 2016/04/06 K.Kojima [QC#6258,MOD]
                // } else if
                // (otbdDefOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.INTERNAL)
                // && otbdDefRtlWhCd != null &&
                // checkDropShipWhCd(otbdDefRtlWhCd, dropShipWhCd) ==
                // false) {
            } else if (otbdDefOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.INTERNAL) && otbdDefWhCd != null && otbdDefWhCd.equals(dropShipPhysWhCd)) {
                // END 2016/04/06 K.Kojima [QC#6258,MOD]
                // END 2016/04/05 K.Kojima [QC#6330,MOD]
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0808E, CSV_OTBD_DEF_LINE_SRC_NM);
                checkResult = false;
            }
        }

        // 2017/09/12 QC#19805 Del Start
//        // Check CatgCd : RMA_DEF_LINE_SRC_CD
//        if (rmaDefOrdLineSrcCatgCd != null) {
//            if (!rmaDefOrdLineSrcCatgCd.equals(ORD_LINE_SRC_CATG.RETURN)) {
//                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0805E, CSV_RMA_DEF_LINE_SRC_NM);
//                checkResult = false;
//            }
//        }
        // 2017/09/12 QC#19805 Del End

        String defWhMapTmplCd = rs.getString(DEF_WH_MAP_TMPL_CD);
        String thirdPtyItemFlg = rs.getString(THIRD_PTY_ITEM_FLG);
        String fromPostCd = rs.getString(FROM_POST_CD);
        String toPostCd = rs.getString(TO_POST_CD);

        //QC#8596
        if (fromPostCd != null && fromPostCd.length() > 0 && fromPostCd.length() != 5) {
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0841E, CSV_FROM_POST_CD);
            checkResult = false;
        }
        
        if (toPostCd != null && toPostCd.length() > 0 && toPostCd.length() != 5) {
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0841E, CSV_TO_POST_CD);
            checkResult = false;
        }
        
        if (UPLOAD_CSV_ID_REPLACE.equals(upldCsvId)) {
            if (!checkDuplicatedFile(glblCmpyCd, upldCsvRqstPk, upldCsvRqstRowNum, defWhMapTmplCd, mdseItemClsTpDescTxt, thirdPtyItemFlg)) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
                checkResult = false;
            }
        } else if (UPLOAD_CSV_ID_APPEND.equals(upldCsvId)) {
            if (!checkDuplicatedFile(glblCmpyCd, upldCsvRqstPk, upldCsvRqstRowNum, defWhMapTmplCd, mdseItemClsTpDescTxt, thirdPtyItemFlg)) {
                uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
                checkResult = false;
            } else if (mdseItemClsTpCd != null) {
                if (!checkDuplicatedRecord(glblCmpyCd, upldCsvRqstPk, upldCsvRqstRowNum, defWhMapTmplCd, mdseItemClsTpCd, thirdPtyItemFlg, fromPostCd, toPostCd)) {
                    uploadMesageForRecord(upldCsvRqstRowNum, NWAM0806E, null);
                    checkResult = false;
                }
            }
        }

        // Create Insert TMsg
        if (checkResult && this.tMsgList != null) {
            ORD_DEF_WHTMsg tMsg = new ORD_DEF_WHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.defWhMapTmplCd, rs.getString(DEF_WH_MAP_TMPL_CD));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpCd, mdseItemClsTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyItemFlg, rs.getString(THIRD_PTY_ITEM_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.fromPostCd, formatPostCdForInsert(rs.getString(FROM_POST_CD)));
            ZYPEZDItemValueSetter.setValue(tMsg.toPostCd, formatPostCdForInsert(rs.getString(TO_POST_CD)));
            ZYPEZDItemValueSetter.setValue(tMsg.onHndChkFlg, rs.getString(ON_HND_CHK_FLG));
            ZYPEZDItemValueSetter.setValue(tMsg.otbdPrimOnHndChkWhCd, otbdPrimOnHndChkWhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdPrimOnHndChkSwhCd, otbdPrimOnHndChkSwhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdPrimOnHndLinSrcCd, otbdPrimOnHndLinSrcCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdScdOnHndChkWhCd, otbdScdOnHndChkWhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdScdOnHndChkSwhCd, otbdScdOnHndChkSwhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdScdOnHndLineSrcCd, otbdScdOnHndLineSrcCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdDefWhCd, otbdDefWhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdDefSwhCd, otbdDefSwhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.otbdDefLineSrcCd, otbdDefLineSrcCd);
            // 2017/09/12 QC#19805 Del Start
//            ZYPEZDItemValueSetter.setValue(tMsg.rmaDefWhCd, rmaDefWhCd);
//            ZYPEZDItemValueSetter.setValue(tMsg.rmaDefSwhCd, rmaDefSwhCd);
//            ZYPEZDItemValueSetter.setValue(tMsg.rmaDefLineSrcCd, rmaDefLineSrcCd);
            // 2017/09/12 QC#19805 Del End
            ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, ZYPConstant.FLG_ON_Y);
            tMsgList.add(tMsg);
        }

        return checkResult;
    }

    /**
     * checkMandatoryColumn
     * @param rs ResultSet
     * @param targetColumnName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatoryColumn(ResultSet rs, String targetColumnName, String csvColumnName) throws SQLException {
        String targetColumn = rs.getString(targetColumnName);
        if (targetColumn == null || targetColumn.length() == 0) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, ZZM9000E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkMandatoryWh
     * @param rs ResultSet
     * @param whName String
     * @param swhName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkMandatoryWh(ResultSet rs, String whName, String swhName, String csvColumnName) throws SQLException {
        String whCd = rs.getString(whName);
        String swhNm = rs.getString(swhName);
        if (whCd == null || whCd.length() == 0) {
            if (swhNm != null && swhNm.length() > 0) {
                BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
                uploadMesageForRecord(upldCsvRqstRowNum, ZZM9000E, csvColumnName);
                return false;
            }
        }
        return true;
    }

    /**
     * checkFormatFlag
     * @param rs ResultSet
     * @param targetColumnName String
     * @param csvColumnName String
     * @return boolean
     * @throws SQLException SQLException
     */
    private boolean checkFormatFlag(ResultSet rs, String targetColumnName, String csvColumnName) throws SQLException {
        String targetColumn = rs.getString(targetColumnName);
        if (!ZYPConstant.FLG_ON_Y.equals(targetColumn) && !ZYPConstant.FLG_OFF_N.equals(targetColumn)) {
            BigDecimal upldCsvRqstRowNum = rs.getBigDecimal(UPLD_CSV_RQST_ROW_NUM);
            uploadMesageForRecord(upldCsvRqstRowNum, NWAM0807E, csvColumnName);
            return false;
        }
        return true;
    }

    /**
     * checkDuplicatedFile
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param upldCsvRqstRowNum BigDecimal
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpDescTxt String
     * @param thirdPtyItemFlg String
     * @return boolean
     */
    private boolean checkDuplicatedFile(String glblCmpyCd, String upldCsvRqstPk, BigDecimal upldCsvRqstRowNum, String defWhMapTmplCd, String mdseItemClsTpDescTxt, String thirdPtyItemFlg) {
        String key = defWhMapTmplCd + " - " + mdseItemClsTpDescTxt + " - " + thirdPtyItemFlg;
        List<Map<String, Object>> duplicateFileList = null;
        if (this.duplicatedFileMap.containsKey(key)) {
            duplicateFileList = this.duplicatedFileMap.get(key);
        } else {
            duplicateFileList = getDuplicatedFile(glblCmpyCd, upldCsvRqstPk, defWhMapTmplCd, mdseItemClsTpDescTxt, thirdPtyItemFlg);
            // Format Post Code
            for (int num1 = 0; num1 < duplicateFileList.size(); num1++) {
                Map<String, Object> map = duplicateFileList.get(num1);
                map.put(FROM_POST_CD, formatPostCdForCheck((String) map.get(FROM_POST_CD)));
                map.put(TO_POST_CD, formatPostCdForCheck((String) map.get(TO_POST_CD)));
                duplicateFileList.set(num1, map);
            }
            // All Data Check
            for (int num1 = 0; num1 < duplicateFileList.size(); num1++) {
                Map<String, Object> map1 = duplicateFileList.get(num1);
                String from1 = (String) map1.get(FROM_POST_CD);
                String to1 = (String) map1.get(TO_POST_CD);
                String checkResultKey = (String) map1.get(CHECK_RESULT_KEY);
                boolean checkResult = true;
                for (int num2 = num1 + 1; num2 < duplicateFileList.size(); num2++) {
                    Map<String, Object> map2 = duplicateFileList.get(num2);
                    String from2 = (String) map2.get(FROM_POST_CD);
                    String to2 = (String) map2.get(TO_POST_CD);
                    if (checkPostCdDuplicated(from1, to1, from2, to2) == false) {
                        map2.put(CHECK_RESULT_KEY, CHECK_RESULT_NG);
                        duplicateFileList.set(num2, map2);
                        checkResult = false;
                    }
                }
                if (checkResult == true) {
                    if (checkResultKey == null) {
                        map1.put(CHECK_RESULT_KEY, CHECK_RESULT_OK);
                    }
                } else {
                    map1.put(CHECK_RESULT_KEY, CHECK_RESULT_NG);
                }
                duplicateFileList.set(num1, map1);
            }
            duplicatedFileMap.put(key, duplicateFileList);
        }
        boolean result = true;
        for (int i = 0; i < duplicateFileList.size(); i++) {
            Map<String, Object> map = duplicateFileList.get(i);
            BigDecimal upldCsvRqstRowNum2 = (BigDecimal) map.get(UPLD_CSV_RQST_ROW_NUM);
            if (upldCsvRqstRowNum.intValue() == upldCsvRqstRowNum2.intValue()) {
                String checkResult = (String) map.get(CHECK_RESULT_KEY);
                if (CHECK_RESULT_OK.equals(checkResult)) {
                    result = true;
                    break;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * checkDuplicatedRecord
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param upldCsvRqstRowNum BigDecimal
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpCd String
     * @param thirdPtyItemFlg String
     * @param fromPostCd String
     * @param toPostCd String
     * @return boolean
     */
    private boolean checkDuplicatedRecord(String glblCmpyCd, String upldCsvRqstPk, BigDecimal upldCsvRqstRowNum, String defWhMapTmplCd, String mdseItemClsTpCd, String thirdPtyItemFlg, String fromPostCd, String toPostCd) {
        String key = defWhMapTmplCd + " - " + mdseItemClsTpCd + " - " + thirdPtyItemFlg;
        List<Map<String, Object>> duplicateFileList = null;
        if (this.duplicatedRecordMap.containsKey(key)) {
            duplicateFileList = this.duplicatedRecordMap.get(key);
        } else {
            duplicateFileList = getDuplicatedRecord(glblCmpyCd, upldCsvRqstPk, defWhMapTmplCd, mdseItemClsTpCd, thirdPtyItemFlg);
            // Format Post Code
            for (int num1 = 0; num1 < duplicateFileList.size(); num1++) {
                Map<String, Object> map = duplicateFileList.get(num1);
                map.put(FROM_POST_CD, formatPostCdForCheck((String) map.get(FROM_POST_CD)));
                map.put(TO_POST_CD, formatPostCdForCheck((String) map.get(TO_POST_CD)));
                duplicateFileList.set(num1, map);
            }
            duplicatedRecordMap.put(key, duplicateFileList);
        }
        boolean result = true;
        fromPostCd = formatPostCdForCheck(fromPostCd);
        toPostCd = formatPostCdForCheck(toPostCd);
        for (int i = 0; i < duplicateFileList.size(); i++) {
            Map<String, Object> map = duplicateFileList.get(i);
            String from2 = (String) map.get(FROM_POST_CD);
            String to2 = (String) map.get(TO_POST_CD);
            if (checkPostCdDuplicated(fromPostCd, toPostCd, from2, to2) == false) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * uploadMesageForRecord
     * @param upldCsvRqstRowNum BigDecimal
     * @param msgId String
     * @param msgStr String
     */
    private void uploadMesageForRecord(BigDecimal upldCsvRqstRowNum, String msgId, String msgStr) {
        this.messenger.addMessageForRecord(upldCsvRqstRowNum, msgId, msgStr);
    }

    /**
     * getCountWrkData
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @return BigDecimal
     */
    private BigDecimal getCountWrkData(String glblCmpyCd, String upldCsvRqstPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("upldCsvRqstPk", upldCsvRqstPk);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getCountWrkData", queryParam);
        return result;
    }

    /**
     * getMdseItemClsTpInfo
     * @param glblCmpyCd String
     * @param mdseItemClsTpDescTxt String
     * @return Map<String, Object>
     */
    private Map<String, Object> getMdseItemClsTpInfo(String glblCmpyCd, String mdseItemClsTpDescTxt) {
        if (this.mdseItemClsTpMap.containsKey(mdseItemClsTpDescTxt)) {
            return this.mdseItemClsTpMap.get(mdseItemClsTpDescTxt);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("mdseItemClsTpDescTxt", mdseItemClsTpDescTxt);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getMdseItemClsTpInfo", queryParam);
            if (result != null) {
                this.mdseItemClsTpMap.put(mdseItemClsTpDescTxt, result);
            }
            return result;
        }
    }

    // START 2016/04/06 K.Kojima [QC#6258,DEL]
    // /**
    // * getRtlWhInfo
    // * @param glblCmpyCd String
    // * @param physWhCd String
    // * @return List<Map<String, Object>>
    // */
    // // START 2016/04/05 K.Kojima [QC#6330,MOD]
    // // private Map<String, Object> getRtlWhInfo(String glblCmpyCd,
    // // String physWhCd) {
    // private List<Map<String, Object>> getRtlWhInfo(String
    // glblCmpyCd, String physWhCd) {
    // // END 2016/04/05 K.Kojima [QC#6330,MOD]
    // if (this.rtlWhMap.containsKey(physWhCd)) {
    // return this.rtlWhMap.get(physWhCd);
    // } else {
    // Map<String, Object> queryParam = new HashMap<String, Object>();
    // queryParam.put("glblCmpyCd", glblCmpyCd);
    // queryParam.put("physWhCd", physWhCd);
    // // START 2016/04/05 K.Kojima [QC#6330,MOD]
    // // Map<String, Object> result = (Map<String, Object>)
    // // this.ssmBatchClient.queryObject("getRtlWhInfo",
    // // queryParam);
    // List<Map<String, Object>> result = (List<Map<String, Object>>)
    // this.ssmBatchClient.queryObjectList("getRtlWhInfo",
    // queryParam);
    // // END 2016/04/05 K.Kojima [QC#6330,MOD]
    // if (result != null) {
    // this.rtlWhMap.put(physWhCd, result);
    // }
    // return result;
    // }
    // }
    // END 2016/04/06 K.Kojima [QC#6258,DEL]

    // START 2016/04/06 K.Kojima [QC#6258,ADD]
    /**
     * getRtlWhInfo
     * @param glblCmpyCd String
     * @param physWhCd String
     * @return List<Map<String, Object>>
     */
    private Map<String, Object> getRtlWhInfo(String glblCmpyCd, String physWhCd) {
        if (this.rtlWhMap.containsKey(physWhCd)) {
            return this.rtlWhMap.get(physWhCd);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("physWhCd", physWhCd);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getRtlWhInfo", queryParam);
            if (result != null) {
                this.rtlWhMap.put(physWhCd, result);
            }
            return result;
        }
    }

    // END 2016/04/06 K.Kojima [QC#6258,ADD]

    /**
     * getSwhInfo
     * @param glblCmpyCd String
     * @param rtlSwhNm String
     * @return Map<String, Object>
     */
    private Map<String, Object> getSwhInfo(String glblCmpyCd, String rtlSwhNm) {
        if (this.swhMap.containsKey(rtlSwhNm)) {
            return this.swhMap.get(rtlSwhNm);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("rtlSwhNm", rtlSwhNm);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getSwhInfo", queryParam);
            if (result != null) {
                this.swhMap.put(rtlSwhNm, result);
            }
            return result;
        }
    }

    /**
     * getOrdLineSrcInfo
     * @param glblCmpyCd String
     * @param ordLineSrcNm String
     * @return Map<String, Object>
     */
    private Map<String, Object> getOrdLineSrcInfo(String glblCmpyCd, String ordLineSrcNm) {
        if (this.ordLineSrcMap.containsKey(ordLineSrcNm)) {
            return this.ordLineSrcMap.get(ordLineSrcNm);
        } else {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("ordLineSrcNm", ordLineSrcNm);
            Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getOrdLineSrcInfo", queryParam);
            if (result != null) {
                this.ordLineSrcMap.put(ordLineSrcNm, result);
            }
            return result;
        }
    }

    /**
     * getDuplicatedFile
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpDescTxt String
     * @param thirdPtyItemFlg String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getDuplicatedFile(String glblCmpyCd, String upldCsvRqstPk, String defWhMapTmplCd, String mdseItemClsTpDescTxt, String thirdPtyItemFlg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("upldCsvRqstPk", new BigDecimal(upldCsvRqstPk));
        queryParam.put("defWhMapTmplCd", defWhMapTmplCd);
        queryParam.put("mdseItemClsTpDescTxt", mdseItemClsTpDescTxt);
        queryParam.put("thirdPtyItemFlg", thirdPtyItemFlg);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplicatedFile", queryParam);
        return result;
    }

    /**
     * getDuplicatedRecord
     * @param glblCmpyCd String
     * @param upldCsvRqstPk String
     * @param defWhMapTmplCd String
     * @param mdseItemClsTpCd String
     * @param thirdPtyItemFlg String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getDuplicatedRecord(String glblCmpyCd, String upldCsvRqstPk, String defWhMapTmplCd, String mdseItemClsTpCd, String thirdPtyItemFlg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("defWhMapTmplCd", defWhMapTmplCd);
        queryParam.put("mdseItemClsTpCd", mdseItemClsTpCd);
        queryParam.put("thirdPtyItemFlg", thirdPtyItemFlg);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDuplicatedRecord", queryParam);
        return result;
    }

    /**
     * findByKeyDefWhMapTmpl
     * @param glblCmpyCd String
     * @param defWhMapTmplCd String
     * @return DEF_WH_MAP_TMPLTMsg
     */
    private static DEF_WH_MAP_TMPLTMsg findByKeyDefWhMapTmpl(String glblCmpyCd, String defWhMapTmplCd) {
        DEF_WH_MAP_TMPLTMsg inMsg = new DEF_WH_MAP_TMPLTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.defWhMapTmplCd.setValue(defWhMapTmplCd);
        return (DEF_WH_MAP_TMPLTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * checkPostCdDuplicated
     * @param from1 String
     * @param to1 String
     * @param from2 String
     * @param to2 String
     * @return boolean
     */
    private static boolean checkPostCdDuplicated(String from1, String to1, String from2, String to2) {
        if (from1.compareTo(to2) <= 0 && from2.compareTo(to1) <= 0) {
            return false;
        }
        return true;
    }

    /**
     * formatPostCdForCheck
     * @param postCd String
     * @return String
     */
    private static String formatPostCdForCheck(String postCd) {
        String[] str = postCd.split("-");
        if (str.length == 2) {
            return ZYPCommonFunc.leftPad(str[0], 15, "0") + "-" + ZYPCommonFunc.rightPad(str[1], 15, "0");
        } else {
            return ZYPCommonFunc.leftPad(postCd, 15, "0") + "-" + ZYPCommonFunc.rightPad("", 15, "0");
        }
    }

    /**
     * formatPostCdForInsert
     * @param postCd String
     * @return String
     */
    private static String formatPostCdForInsert(String postCd) {
        String[] str = postCd.split("-");
        if (str.length == 2) {
            if (ZYPCommonFunc.isNumeric(str[0]) && ZYPCommonFunc.isNumeric(str[1])) {
                return ZYPCommonFunc.leftPad(str[0], 5, "0") + "-" + str[1];
            }
        } else if (str.length == 1 && ZYPCommonFunc.isNumeric(postCd)) {
            return ZYPCommonFunc.leftPad(postCd, 5, "0");
        }
        return postCd;
    }

    // START 2016/04/06 K.Kojima [QC#6258,DEL]
    // // START 2016/04/05 K.Kojima [QC#6330,ADD]
    // /**
    // * checkDropShipWhCd
    // * @param whList List<Map<String, Object>>
    // * @param dropShipWhCd String
    // * @return boolean
    // */
    // private static boolean checkDropShipWhCd(List<Map<String,
    // Object>> whList, String dropShipWhCd) {
    // for (int i = 0; i < whList.size(); i++) {
    // if (dropShipWhCd.equals(whList.get(i).get(RTL_WH_CD))) {
    // return false;
    // }
    // }
    // return true;
    // }
    // // END 2016/04/05 K.Kojima [QC#6330,ADD]
    // END 2016/04/06 K.Kojima [QC#6258,DEL]

    // START 2016/06/16 [QC#8156,ADD]
    private static String createResultMessageArg(int insCount, int updCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insCount> 0) {
            builder.append(String.format(RESULT_MSG_INS, insCount));
        }
        if (updCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, updCount));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }
    // END 2016/06/16 [QC#8156,ADD]
}
