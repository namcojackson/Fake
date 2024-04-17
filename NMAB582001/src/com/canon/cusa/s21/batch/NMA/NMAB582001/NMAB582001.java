/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB582001;

import static com.canon.cusa.s21.batch.NMA.NMAB582001.constant.NMAB582001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CNTYTMsg;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.PRNT_VND_TPTMsg;
import business.parts.NMZC201001PMsg;
import business.parts.NMZC201002PMsg;
import business.parts.NMZC201003PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC201001.NMZC201001;
import com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Receive Supplier Info From ARCS
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 04/05/2016   CITS            T.Wada          Create                      
 * 2016/05/11   CITS            T.Hakodate      UPDATE          QC#6943
 * 2016/05/12   CITS            T.Hakodate      UPDATE          QC#8171
 * 2016/09/01   CITS            T.Wada          UPDATE          QC#14043
 * 2016/09/08   CITS            T.Gotoda        UPDATE          QC#11907
 * 2016/09/28   CITS            T.Gotoda        UPDATE          QC#13163
 * 2016/12/20   CITS            R.Shimamoto     UPDATE          QC#16596
 * 2017/01/05   CITS            R.Shimamoto     UPDATE          QC#15381
 * 2017/01/05   CITS            R.Shimamoto     UPDATE          QC#16594
 * 2017/01/17   CITS            R.Shimamoto     UPDATE          QC#14489
 * 2017/02/07   CITS            R.Shimamoto     UPDATE          QC#16594-1
 * 2018/01/12   CITS            T.Gotoda        UPDATE          QC#23361
 * 2018/03/20   CITS            M.Naito         UPDATE          QC#21001
 * 2018/07/20   CITS            T.Tokutomi      UPDATE          QC#27045
 * 2021/02/15   CITS            G.Delgado       UPDATE          QC#55912
 * 2021/10/04   CITS            G.Delgado       UPDATE          QC#59223
 *</pre>
 */
public class NMAB582001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId;

    /** Sales Date */
    private String slsDt;

    // -- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Insert */
    private int insertCount;

    /** The number of case : Skip */
    private int errCount;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    /** Termination code */
    private TERM_CD termCd;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess;

    /** Contact Type */
    private String ctacTp = null;

    /** Contact Type */
    private String currentSystm = null;

    /** MAIL USER GROUP */
    private String mailUserGroup = null;

    /** Error Massage List */
    private ArrayList<Map<String, String>> newRegInfoList = new ArrayList<Map<String, String>>();

    /** Error Massage List */
    private ArrayList<Map<String, String>> warnInfoList = new ArrayList<Map<String, String>>();

    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NMAB582001().executeBatch(NMAB582001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        selectCount = 0;
        insertCount = 0;
        errCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_COMP_CODE });
        }

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_INTERFACE_ID });
        }

        // Mail Group
        mailUserGroup = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mailUserGroup)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_PARAM_01 });
        }

        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Proc time
        currentSystm = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);

        // Contact Type
        ctacTp = getCtacTp();
        if (!ZYPCommonFunc.hasValue(ctacTp)) {
            throw new S21AbendException(MSG_ID_NMAM8186E, new String[] {MSG_STR_CTAC_TP });
        }
    }

    @Override
    protected void mainRoutine() {

        /**
         * Main Proc
         */
        recieveSupplierInfoFromARCS();
    }

    @Override
    protected void termRoutine() {

        /**
         * Send Warn/Info Mail
         */
        if (warnInfoList.size() > 0) {
            sendWarnMail();
            termCd = TERM_CD.WARNING_END;
        }
        if (newRegInfoList.size() > 0) {
            sendNewRegMail();
        }

        String[] tmp = null;
        // The number of cases : Insert is output
        tmp = new String[] {interfaceId, "Insert", Integer.toString(insertCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp);
        // Setting of termination code
        setTermState(termCd, insertCount, errCount, selectCount);
    }

    /**
     * recieveSupplierInfoFromARCS
     */
    private void recieveSupplierInfoFromARCS() {

        NMZC201001PMsg pMsg01 = new NMZC201001PMsg();
        List<NMZC201002PMsg> pMsg02List = new ArrayList<NMZC201002PMsg>();
        List<NMZC201003PMsg> pMsg03List = new ArrayList<NMZC201003PMsg>();

        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        String prevSplySiteId = "";
        String prevProcDt = MIN_DT;
        String prntVndTp = null;
        Map<String, Object> prtnVndInfo = null;
        Map<String, Object> vndInfo = null;
        List<Map<String, Object>> ctacInfoList = null;
        BigDecimal arcsSplyId = null;

        BigDecimal dsBizProcLogPk = null;
        int procCount = 0;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            // get the previous processing time
            Map<String, Object> prevProcInfo = getPrevProcDt(PROGRAM_ID);
            if (prevProcInfo != null) {
                dsBizProcLogPk = (BigDecimal) prevProcInfo.get(COL_DS_BIZ_PROC_LOG_PK);
                if (prevProcInfo.get(COL_DS_BIZ_LAST_UPD_DT) != null) {
                    prevProcDt = (String) prevProcInfo.get(COL_DS_BIZ_LAST_UPD_DT);
                }
            }

            // Get the TRANSACTION_ID to be processed
            BigDecimal[] tranIds = trxAccess.getIntegrationRecord(this.interfaceId);
            if (tranIds == null) {
                return;
            }

            for (BigDecimal tranId : tranIds) {

                // Only snapshots brute cord to the latest
                // TRANSACTION_ID
                // is processed
                BigDecimal latestTranId = tranIds[tranIds.length - 1];

                procCount = 0;
                if (tranId.compareTo(latestTranId) != 0) {
                    continue;
                }

                // Get Supplier Id
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("INTERFACE_ID", this.interfaceId);
                param.put("TRANSACTION_ID", tranId);
                param.put("DS_BIZ_LAST_UPD_TS", prevProcDt);
                prdStmt = ssmLLClient.createPreparedStatement("getSplyIds", param, execParam);
                rs = prdStmt.executeQuery();

                while (rs.next()) {
                    selectCount++;
                    procCount++;

                    BigDecimal prevArcsSplyId = null;

                    // Current ARCS_SPLY_ID
                    arcsSplyId = rs.getBigDecimal(COL_ARCS_SPLY_ID);

                    // Get Supplier & SupplierSite Info List
                    List<Map<String, Object>> splySiteList = getSplySiteInfoList(tranId, arcsSplyId);

                    boolean errFlg = false;
                    boolean supplierCreateFlag = false;
                    BigDecimal prntVndPk = null;

                    for (Map<String, Object> splySiteInfo : splySiteList) {

                        supplierCreateFlag = false;
                        // ////////////////////////////////////////////////////////////////////////
                        // SupplierSite Info MODE Decison & PMsg
                        // Create
                        // ////////////////////////////////////////////////////////////////////////
                        if (splySiteInfo.get(COL_ARCS_SPLY_SITE_ID) != null && !prevSplySiteId.equals(splySiteInfo.get(COL_ARCS_SPLY_SITE_ID).toString())) {

                            NMZC201002PMsg pMsg02 = null;

                            // ARCS_SPLY_SITE_ATTRB_TXT_05<>NULL
                            if (ZYPCommonFunc.hasValue((String) splySiteInfo.get(COL_ARCS_SPLY_SITE_ATTRB_TXT_05))) {

                                // 2.2.1 Supper Site Search by
                                vndInfo = getVndInfoByVndCd((String) splySiteInfo.get(COL_ARCS_SPLY_SITE_ATTRB_TXT_05));

                                if (vndInfo != null) {

                                    // UPDATE:Supplier
                                    pMsg02 = setSplySiteInfoForUpdate(splySiteInfo, vndInfo);
                                    prntVndPk = (BigDecimal) vndInfo.get(PRNT_VND_PK);

                                } else {

                                    // QC#27045 Update. Create Supplier Site.
                                    pMsg02 = setSplySiteInfoForNew(splySiteInfo, pMsg01.prntVndCd.getValue());
                                    ZYPEZDItemValueSetter.setValue(pMsg02.vndCd, (String) splySiteInfo.get(COL_ARCS_SPLY_SITE_ATTRB_TXT_05));
                                    setNewRegReportInfo(splySiteInfo);

                                    // Supplier level check
                                    prtnVndInfo = getPrntVndByArcsSplyId(arcsSplyId);

                                    if (prtnVndInfo == null) {
                                        supplierCreateFlag = true;
                                    } else {
                                        prntVndPk = (BigDecimal) prtnVndInfo.get(PRNT_VND_PK);
                                    }

                                    // Send Business error mail
                                    // setWarnReportInfo(splySiteInfo);
                                    // errFlg = true;
                                    // break;
                                }

                            } else {

                                // ARCS_SPLY_SITE_ATTRB_TXT_05=NULL

                                // 2.2.2 Supper Site Search by ARCS ID
                                vndInfo = getVndInfoByArcsSplySiteId(splySiteInfo.get(COL_ARCS_SPLY_SITE_ID).toString());

                                if (vndInfo != null) {

                                    // UPDATE:Supplier
                                    pMsg02 = setSplySiteInfoForUpdate(splySiteInfo, vndInfo);
                                    prntVndPk = (BigDecimal) vndInfo.get(PRNT_VND_PK);

                                } else {

                                    // CREATE:Supplier
                                    pMsg02 = setSplySiteInfoForNew(splySiteInfo, pMsg01.prntVndCd.getValue());
                                    setNewRegReportInfo(splySiteInfo);

                                    // Supplier level check
                                    prtnVndInfo = getPrntVndByArcsSplyId(arcsSplyId);

                                    if (prtnVndInfo == null) {
                                        supplierCreateFlag = true;
                                    } else {
                                        prntVndPk = (BigDecimal) prtnVndInfo.get(PRNT_VND_PK);
                                    }
                                }
                            }

                            pMsg02List.add(pMsg02);
                            prevSplySiteId = splySiteInfo.get(COL_ARCS_SPLY_SITE_ID).toString();
                        }

                        // ////////////////////////////////////////////////////////////////////////
                        // Supplier Info MODE Decison & PMsg Create
                        // ////////////////////////////////////////////////////////////////////////
                        if (!arcsSplyId.equals(prevArcsSplyId)) {

                            if (supplierCreateFlag) {
                                // NEW_MODE
                                prntVndTp = getPrntVndTpCd((String) splySiteInfo.get(COL_ARCS_VND_TP_LKUP_TXT));

                                if (!ZYPCommonFunc.hasValue(prntVndTp)) {
                                    // QC#15381 Mod.
//                                    errFlg = true;
//                                    break;
                                    continue;
                                    // throw new
                                    // S21AbendException(MSG_ID_NMAM8186E,
                                    // new String[] {MSG_STR_SPLT_TP
                                    // });
                                }

                                pMsg01 = setSplyInfoForNew(splySiteInfo, prntVndTp);

                            } else {

                                // UPDATE_MODE
                                prtnVndInfo = getPrntVndInfo(prntVndPk);
                                pMsg01 = setSplyInfoForUpdate(splySiteInfo, prtnVndInfo);
                            }

                            // QC#13163 Start
                            // Check Payment Term Code
                            if (ZYPCommonFunc.hasValue(pMsg01.vndPmtTermDescTxt) && !ZYPCommonFunc.hasValue(pMsg01.vndPmtTermCd)) {

                                String errMsgText = S21MessageFunc.clspGetMessage(MSG_ID_NMAM8132E, new String[] {"VND_PMT_TERM_DESC_TXT:" + pMsg01.vndPmtTermDescTxt.getValue(), "VND_PMT_TERM"});
                                S21InfoLogOutput.println(errMsgText);

                                // Send Business error mail
                                setWarnReportInfo(splySiteInfo);
                                errFlg = true;
                                break;
                            }
                            // QC#13163 End

                            prevArcsSplyId = arcsSplyId;

                        }
                    }
                    // loop Supplier&Supplier Site End

                    if (!errFlg) {
                        // /////////////////////////////////////////
                        // API Execute (Supplier & SupplierSite)
                        // /////////////////////////////////////////
                        if (!execUpdateSupplierApi(pMsg01, pMsg02List, pMsg03List)) {

                            rollback();

                            // Send Business error mail
                            setWarnReportInfoForApiErr(splySiteList);
                            errCount++;

                            // QC#14489 Mod.
                            pMsg02List.clear();
                            continue;
                        }

                        pMsg02List.clear();

                        setVndCdOnNewRegReportInfo();

                        // ///////////////////////////////////////////////////
                        // Get Contact Info
                        // ///////////////////////////////////////////////////
                        List<Map<String, Object>> splyCntctInfoList = getContactInfoList(tranId, arcsSplyId, prevProcDt);
                        boolean splyCntctInfoErrFlg = false;
                        for (Map<String, Object> splyCntctInfo : splyCntctInfoList) {

                            if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM)) || ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_EML_ADDR))
                                    || ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM))) {

                                // SPLY = UPDATE_MODE
                                prtnVndInfo = getPrntVndByArcsSplyId((BigDecimal) splyCntctInfo.get(COL_ARCS_SPLY_ID));
                                pMsg01 = setSplyInfoForUpdate(splyCntctInfo, prtnVndInfo);

                                // SPLY_SITE = UPDATE_MODE
                                vndInfo = getVndInfoByArcsSplySiteId(splyCntctInfo.get(COL_ARCS_SPLY_SITE_ID).toString());
                                NMZC201002PMsg pMsg02 = setSplySiteInfoForUpdate(splyCntctInfo, vndInfo);
                                pMsg02List.add(pMsg02);

                                // Get CTAC_PSN&DS_CTAC_PNT Info
                                ctacInfoList = getCtacInfoList(vndInfo.get(COL_LOC_NUM).toString(), (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_FIRST_NM), (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_LAST_NM));

                                if (ctacInfoList != null && ctacInfoList.size() > 0) {
                                    // UPDATE_MODE
                                    NMZC201003PMsg pMsg3 = setCntctInfoForUpdate(splyCntctInfo, vndInfo, ctacInfoList);
                                    pMsg03List.add(pMsg3);
                                } else {
                                    // NEW_MODE
                                    NMZC201003PMsg pMsg3 = setCntctInfoForNew(splyCntctInfo, vndInfo);
                                    pMsg03List.add(pMsg3);
                                }

                                // /////////////////////////////////////////
                                // API Execute (Contact)
                                // /////////////////////////////////////////
                                if (!execUpdateSupplierApi(pMsg01, pMsg02List, pMsg03List)) {

                                    rollback();

                                    // Send Business error mail
                                    setWarnReportInfoForApiErr(splySiteList);

                                    // QC#14489 Mod.
                                    splyCntctInfoErrFlg = true;
                                    break;
                                }

                            } else {

                                continue;

                            }

                            pMsg02List.clear();
                            pMsg03List.clear();
                        }

                        // QC#14489 Mod.
                        if (splyCntctInfoErrFlg) {
                            pMsg02List.clear();
                            pMsg03List.clear();
                            errCount++;
                            continue;

                        }

                        // Commit For Supplier
                        insertCount++;
                        commit();

                    } else {
                        errCount++;
                        pMsg02List.clear();
                        errFlg = false;
                    }

                } // loop Supplier End

                // 3.1.1. UpdateDS_BIZ_PROC_LOG
                if (procCount > 0) {
                    String maxBizLastUpdDt = getMaxBizLastUpdDt(tranId);
                    if (dsBizProcLogPk != null) {
                        updateBizProcLog(dsBizProcLogPk, maxBizLastUpdDt);
                    } else {
                        insertBizProcLog(maxBizLastUpdDt);
                    }
                }

                // Update Transaction ID
                trxAccess.endIntegrationProcess(this.interfaceId, tranId);
                // Commit For Transaction Unit
                commit();

            } // Transaction Id loop

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * insertBizProcLog
     * @param dsBizProcLogPk
     * @param dsBizLastProcTs
     * @param dsBizLastUpdTs
     */
    private void insertBizProcLog(String dsBizLastUpdTs) {
        DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);

        // DS_BIZ_PROC_LOG_PK
        // QC14043
        //ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WMS_INBD_TRX_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));

        // PROC_PGM_ID
        ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, PROGRAM_ID);
        //
        // // DS_BIZ_PROC_DT
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, this.slsDt);
        //
        // // DS_BIZ_PROC_FLG
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        // // DS_BIZ_LAST_PROC_TS
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, this.currentSystm);
        // DS_BIZ_LAST_UPD_TS
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, dsBizLastUpdTs);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_ZZZM9012E, new String[] {tMsg.getReturnCode() });
        }

    }

    /**
     * updateBizProcLog
     * @param dsBizProcLogPk
     * @param dsBizLastProcDt
     * @param dsBizLastUpdTs
     */
    private void updateBizProcLog(BigDecimal dsBizProcLogPk, String dsBizLastProcDt) {
        DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, dsBizProcLogPk);

        tMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_NMAM8132E, new String[] {COL_GLBL_CMPY_CD + ":" + this.glblCmpyCd + COL_DS_BIZ_PROC_LOG_PK + ":" + dsBizProcLogPk.toString(), TBL_DS_BIZ_PROC_LOG });
        }

        ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, this.currentSystm);
        ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, dsBizLastProcDt);

        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(MSG_ID_ZZZM9013E, new String[] {tMsg.getReturnCode() });
        }
    }

    private String getCtacTp() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("SPLY_IND_FLG", ZYPConstant.FLG_ON_Y);
        queryParam.put("ACTV_FLG", ZYPConstant.FLG_ON_Y);
        return (String) this.ssmBatchClient.queryObject("getCtacTp", queryParam);
    }

    private Map<String, Object> getPrevProcDt(String procPgmId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("PROC_PGM_ID", procPgmId);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrevProcDt", queryParam);
        return ret;
    }

    private String getPrntVndTp(String param) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("XTRNL_SYS_VND_TP_LKUP_TXT", param);
        String ret = (String) this.ssmBatchClient.queryObject("getPrntVndTp", queryParam);
        return ret;

    }

    private List<Map<String, Object>> getCtacInfoList(String vndCd, String ctacPsnFirstNm, String ctacPsnLastNmTxt) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("LOC_NUM", vndCd);
        queryParam.put("CTAC_PSN_FIRST_NM", ctacPsnFirstNm);
        queryParam.put("CTAC_PSN_LAST_NM", ctacPsnLastNmTxt);
        String[] dsCtacPntTpCd = {DS_CTAC_PNT_TP.PHONE_WORK, DS_CTAC_PNT_TP.EMAIL_WORK, DS_CTAC_PNT_TP.FAX_WORK };
        queryParam.put("dsCtacPntTpCd", dsCtacPntTpCd);
        List ret = this.ssmBatchClient.queryObjectList("getCtacInfoList", queryParam);
        return ret;
    }

    private List<Map<String, Object>> getSplySiteInfoList(BigDecimal tranId, BigDecimal arcsSplyId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("INTERFACE_ID", this.interfaceId);
        queryParam.put("TRANSACTION_ID", tranId);
        queryParam.put("ARCS_SPLY_ID", arcsSplyId);
        List rwsSerList = this.ssmBatchClient.queryObjectList("getSplySiteInfoList", queryParam);
        return rwsSerList;
    }

    private List<Map<String, Object>> getContactInfoList(BigDecimal tranId, BigDecimal arcsSplyId, String dsBizLastUudTs) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("INTERFACE_ID", this.interfaceId);
        queryParam.put("TRANSACTION_ID", tranId);
        queryParam.put("ARCS_SPLY_ID", arcsSplyId);
        queryParam.put("DS_BIZ_LAST_UPD_TS", dsBizLastUudTs);
        List ret = this.ssmBatchClient.queryObjectList("getContactInfoList", queryParam);
        return ret;
    }

    private Map<String, Object> getVndInfoByVndCd(String vndCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("VND_CD", vndCd);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getVndInfoByVndCd", queryParam);
        return ret;
    }

    private Map<String, Object> getVndInfoByArcsSplySiteId(String arcsSplySiteId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("ARCS_SPLY_SITE_ID", arcsSplySiteId);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getVndInfoByArcsSplySiteId", queryParam);
        return ret;
    }

    private Map<String, Object> getPrntVndByArcsSplyId(BigDecimal arcsSplyId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("ARCS_SPLY_ID", arcsSplyId);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrntVndByArcsSplyId", queryParam);
        return ret;
    }

    private Map<String, Object> getPrntVndInfo(BigDecimal prntVndPk) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("PRNT_VND_PK", prntVndPk);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getPrntVndInfo", queryParam);
        return ret;
    }

    // QC#13163 Start
    private String getVndPmtTermCd(String vndPmtTermDescTxt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("VND_PMT_TERM_DESC_TXT", vndPmtTermDescTxt);

        return (String) this.ssmBatchClient.queryObject("getVndPmtTermCd", queryParam);
    }
    // QC#13163 End

    /**
     * getMaxBizLastProcDt
     * @param tranId
     * @return
     */
    private String getMaxBizLastUpdDt(BigDecimal tranId) {
        String ret = MIN_DT;

        Map<String, Object> map = getBizLastUpdDt(tranId);

        String pvLastDt = (String) map.get(COL_MAX_PRNT_VND_LAST_UPD_DT);
        String vLastDt = (String) map.get(COL_MAX_VND_LAST_UPD_DT);
        String vcLastDt = (String) map.get(COL_MAX_VND_CTAC_LAST_UPD_DT);

        if (pvLastDt.compareTo(ret) >= 0) {
            ret = pvLastDt;
        }
        if (vLastDt.compareTo(ret) >= 0) {
            ret = vLastDt;
        }
        if (vcLastDt.compareTo(ret) >= 0) {
            ret = vcLastDt;
        }

        return ret;

    }

    private Map<String, Object> getBizLastUpdDt(BigDecimal tranId) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("INTERFACE_ID", this.interfaceId);
        queryParam.put("TRANSACTION_ID", tranId);
        Map<String, Object> ret = (Map<String, Object>) this.ssmBatchClient.queryObject("getBizLastUpdDt", queryParam);
        return ret;
    }

    // START 2021/02/15 G.Delgado [QC#55912,ADD]
    /**
     * checkPrntVndTpSendArcs
     * @param prntVndCd
     * @return Boolean
     */
    private Boolean checkPrntVndTpSendArcs(String prntVndCd) {
        Boolean ret = false;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("PRNT_VND_CD", prntVndCd);
        Integer retCnt = (Integer) this.ssmBatchClient.queryObject("checkPrntVndTpSendArcs", queryParam);
        if (retCnt > 0) {
            ret = true;
        }
        return ret;
    }

    /**
     * checkSplySitesWithPurFlgOnPayFlgOff
     * @param prntVndPk
     * @return Integer
     */
    private Integer checkSplySitesWithPurFlgOnPayFlgOff(BigDecimal prntVndPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("GLBL_CMPY_CD", this.glblCmpyCd);
        queryParam.put("PRNT_VND_PK", prntVndPk);
        return (Integer) this.ssmBatchClient.queryObject("checkSplySitesWithPurFlgOnPayFlgOff", queryParam);
    }
    // END 2021/02/15 G.Delgado [QC#55912,ADD]

    /**
     * getPrntVndTpCd
     * @param arcsVndTpLkupTxt
     * @return
     */
    private String getPrntVndTpCd(String arcsVndTpLkupTxt) {

        String prntVndTp = null;

        if (ZYPCommonFunc.hasValue(arcsVndTpLkupTxt)) {
            prntVndTp = getPrntVndTp(arcsVndTpLkupTxt);
        }

        // re-search by changing the search conditions
        if (!ZYPCommonFunc.hasValue(prntVndTp)) {
            // QC#15381 Mod.
            return null;
//            prntVndTp = getPrntVndTp(VAL_IF_DEF);
        }

        return prntVndTp;
    }

    /**
     * setSplyInfoComn
     * @param arcsSplyInfo
     * @param pMsg01
     * @return
     */
    private NMZC201001PMsg setSplyInfoComn(Map<String, Object> arcsSplyInfo, NMZC201001PMsg pMsg01) {
        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.glblCmpyCd, this.glblCmpyCd);

        // PROC_DT
        ZYPEZDItemValueSetter.setValue(pMsg01.procDt, this.slsDt);

        // PRNT_VND_NM
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndNm, (String) arcsSplyInfo.get(COL_PRNT_VND_NM));

        // TAX_PAYER_ID
        ZYPEZDItemValueSetter.setValue(pMsg01.taxPayerId, (String) arcsSplyInfo.get(COL_TAX_PAYER_ID));

        // INAC_DT
        ZYPEZDItemValueSetter.setValue(pMsg01.inacDt, (String) arcsSplyInfo.get(COL_PRNT_VND_INAC_DT));

        // ARCS_SPLY_NUM
        ZYPEZDItemValueSetter.setValue(pMsg01.arcsSplyNum, (String) arcsSplyInfo.get(COL_ARCS_SPLY_NUM));

        // ARCS_SPLY_ID
        if (arcsSplyInfo.get(COL_ARCS_SPLY_ID) != null) {
            String arcsSplyId = arcsSplyInfo.get(COL_ARCS_SPLY_ID).toString();
            ZYPEZDItemValueSetter.setValue(pMsg01.arcsSplyId, new BigDecimal(arcsSplyId));
        }

        // PAY_ALONE_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.payAloneFlg, (String) arcsSplyInfo.get(COL_PAY_ALONE_FLG));

        // COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.coaAfflCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_07));
        // VND_PMT_TERM_DESC_TXT
        ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtTermDescTxt, (String) arcsSplyInfo.get(COL_PRNT_VND_PMT_TERM_DESC_TXT));

        // QC#13163 Start
        // VND_PMT_TERM_CD
        if (ZYPCommonFunc.hasValue(pMsg01.vndPmtTermDescTxt)) {
            ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtTermCd, getVndPmtTermCd(pMsg01.vndPmtTermDescTxt.getValue()));
        } else {
            pMsg01.vndPmtTermCd.clear();
        }
        // QC#13163 End

        return pMsg01;
    }

    /**
     * @param rs
     * @param prntVndInfo
     * @param modeSupplier
     * @param prntVndTp
     * @return
     */
    private NMZC201001PMsg setSplyInfoForNew(Map<String, Object> arcsSplyInfo, String prntVndTp) {

        NMZC201001PMsg pMsg01 = new NMZC201001PMsg();
        // MODE
        ZYPEZDItemValueSetter.setValue(pMsg01.xxModeCd, NMZC201001Constant.CREATE_MODE);

        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndCd, (String) arcsSplyInfo.get(COL_ARCS_SPLY_NUM));
        // PRNT_VND_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndTpCd, prntVndTp);

        pMsg01 = setSplyInfoComn(arcsSplyInfo, pMsg01);

        return pMsg01;
    }

    /**
     * setSplyInfoForUpdate
     * @param arcsSplyInfo
     * @param prntVndInfo
     * @return
     */
    private NMZC201001PMsg setSplyInfoForUpdate(Map<String, Object> arcsSplyInfo, Map<String, Object> prntVndInfo) {

        NMZC201001PMsg pMsg01 = new NMZC201001PMsg();
        // MODE
        ZYPEZDItemValueSetter.setValue(pMsg01.xxModeCd, NMZC201001Constant.UPDATE_MODE);

        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndCd, (String) prntVndInfo.get(COL_PRNT_VND_CD));

        // PRNT_VND_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndTpCd, (String) prntVndInfo.get(COL_PRNT_VND_TP_CD));

        // TAX_PAYER_RG_NUM
        ZYPEZDItemValueSetter.setValue(pMsg01.taxPayerRgNum, (String) prntVndInfo.get(COL_TAX_PAYER_RG_NUM));

        // INDY_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.indyTpCd, (String) prntVndInfo.get(COL_INDY_TP_CD));
        // MNRTY_OWND_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.mnrtyOwndTpCd, (String) prntVndInfo.get(COL_MNRTY_OWND_TP_CD));
        // SPLY_ORG_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.splyOrgTpCd, (String) prntVndInfo.get(COL_SPLY_ORG_TP_CD));
        // SPLY_ONE_TM_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.splyOneTmFlg, (String) prntVndInfo.get(COL_SPLY_ONE_TM_FLG));
        // SM_BIZ_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.smBizFlg, (String) prntVndInfo.get(COL_SM_BIZ_FLG));
        // WOMEN_OWND_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.womenOwndFlg, (String) prntVndInfo.get(COL_WOMEN_OWND_FLG));
        // DISC_TAKE_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.discTakeFlg, (String) prntVndInfo.get(COL_DISC_TAKE_FLG));
        // PO_PMT_HLD_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.poPmtHldFlg, (String) prntVndInfo.get(COL_PO_PMT_HLD_FLG));
        // FD_RPT_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.fdRptFlg, (String) prntVndInfo.get(COL_FD_RPT_FLG));
        // INC_TAX_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.incTaxTpCd, (String) prntVndInfo.get(COL_INC_TAX_TP_CD));
        // ST_TAX_FLG
        ZYPEZDItemValueSetter.setValue(pMsg01.stTaxFlg, (String) prntVndInfo.get(COL_ST_TAX_FLG));
        // SPLY_RPT_NM
        ZYPEZDItemValueSetter.setValue(pMsg01.splyRptNm, (String) prntVndInfo.get(COL_SPLY_RPT_NM));
        // SPLY_HUB_ZN_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.splyHubZnCd, (String) prntVndInfo.get(COL_SPLY_HUB_ZN_CD));
        // DS_ACCT_NUM
        ZYPEZDItemValueSetter.setValue(pMsg01.dsAcctNum, (String) prntVndInfo.get(COL_DS_ACCT_NUM));
        // VND_PMT_TERM_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtTermCd, (String) prntVndInfo.get(COL_VND_PMT_TERM_CD));
        // VND_PMT_METH_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtMethCd, (String) prntVndInfo.get(COL_VND_PMT_METH_CD));
        // PAY_GRP_CD
        ZYPEZDItemValueSetter.setValue(pMsg01.payGrpCd, (String) prntVndInfo.get(COL_PAY_GRP_CD));

        pMsg01 = setSplyInfoComn(arcsSplyInfo, pMsg01);

        // START 2021/02/15 G.Delgado [QC#55912,ADD]
        // Do not update inactive date if supplier has site with Purchasing Flag = ON, Pay Flag = OFF, and Supplier Send ARCS Flag = OFF
        if (ZYPCommonFunc.hasValue(pMsg01.prntVndCd) && !checkPrntVndTpSendArcs(pMsg01.prntVndCd.getValue())
                && checkSplySitesWithPurFlgOnPayFlgOff((BigDecimal) prntVndInfo.get(PRNT_VND_PK)) > 0) {
            // INAC_DT
            ZYPEZDItemValueSetter.setValue(pMsg01.inacDt, (String) prntVndInfo.get(COL_INAC_DT));
        }
        // END 2021/02/15 G.Delgado [QC#55912,ADD]

        return pMsg01;
    }

    /**
     * setSplySiteInfoComn
     * @param arcsSplyInfo
     * @param pMsg02
     * @return
     */
    private NMZC201002PMsg setSplySiteInfoComn(Map<String, Object> arcsSplyInfo, NMZC201002PMsg pMsg02) {
        // QC#16596 Mod.
//        // FIRST_LINE_ADDR
//        if (arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_01) != null) {
//            String firstValText = (String) arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_01);
//            if (firstValText.length() > LEN_FIRST_LINE_ADDR) {
//                ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, firstValText.substring(0, LEN_FIRST_LINE_ADDR));
//            } else {
//                ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, firstValText);
//            }
//        }
        String allAddrTxt = "";
        if (arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_01) != null) {
            allAddrTxt = (String) arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_01) + SPACE;
        }
        if (arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_02) != null) {
            allAddrTxt = allAddrTxt + (String) arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_02) + SPACE;
        }
        if (arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_03) != null) {
            allAddrTxt = allAddrTxt + (String) arcsSplyInfo.get(COL_VND_ADDR_ALL_TXT_03);
        }

        int length = allAddrTxt.length();
        int start = 0;
        int end = 0;

        // QC#23361 Start
        // FIRST_LINE_ADDR
        if (length > 0 && length <= LEN_SUBSTRING_ADDR) {
            ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, allAddrTxt);
            length = 0;
        } else if (length > LEN_SUBSTRING_ADDR) {
            end = allAddrTxt.lastIndexOf(SPACE, LEN_SUBSTRING_ADDR);
            if (end == -1) {
                end = LEN_SUBSTRING_ADDR;
            }
            ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, allAddrTxt.substring(start, end));
            length = length - end;
            start = end;
        }

        // SCD_LINE_ADDR
        if (length > 0 && length <= LEN_SUBSTRING_ADDR) {
            ZYPEZDItemValueSetter.setValue(pMsg02.scdLineAddr, allAddrTxt.substring(start));
        	length = 0;
        } else if (length > LEN_SUBSTRING_ADDR) {
            end = allAddrTxt.lastIndexOf(SPACE, start + LEN_SUBSTRING_ADDR);
            if (end == start) {
                end = start + LEN_SUBSTRING_ADDR;
            }
            ZYPEZDItemValueSetter.setValue(pMsg02.scdLineAddr, allAddrTxt.substring(start, end));
            length = length - (end - start);
            start = end;
        }

        // THIRD_LINE_ADDR
        if (length > 0 && length <= LEN_SUBSTRING_ADDR) {
            ZYPEZDItemValueSetter.setValue(pMsg02.thirdLineAddr, allAddrTxt.substring(start));
            length = 0;
        } else if (length > LEN_SUBSTRING_ADDR) {
            end = allAddrTxt.lastIndexOf(SPACE, start + LEN_SUBSTRING_ADDR);
            if (end == start) {
                end = start + LEN_SUBSTRING_ADDR;
            }
            ZYPEZDItemValueSetter.setValue(pMsg02.thirdLineAddr, allAddrTxt.substring(start, end));
            length = length - (end - start);
            start = end;
        }

        // FOURTH_LINE_ADDR
        if (length > 0 && length <= LEN_SUBSTRING_ADDR) {
            ZYPEZDItemValueSetter.setValue(pMsg02.frthLineAddr, allAddrTxt.substring(start));
            length = 0;
        } else if (length > LEN_SUBSTRING_ADDR) {
            end = allAddrTxt.lastIndexOf(SPACE, start + LEN_SUBSTRING_ADDR);
            if (end == start) {
                end = start + LEN_SUBSTRING_ADDR;
            }
            ZYPEZDItemValueSetter.setValue(pMsg02.frthLineAddr, allAddrTxt.substring(start, end));
        }
        // QC#23361 End

        // CTY_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.ctyAddr, (String) arcsSplyInfo.get(COL_CTY_ADDR));
        // CNTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.cntyNm, (String) arcsSplyInfo.get(COL_ARCS_CNTY_NM));

        // PROV_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.provNm, (String) arcsSplyInfo.get(COL_ARCS_PROV_NM));
        // ST_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.stCd, (String) arcsSplyInfo.get(COL_ST_CD));
        // POST_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.postCd, (String) arcsSplyInfo.get(COL_POST_CD));

        // CTRY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.ctryCd, (String) arcsSplyInfo.get(COL_ARCS_CTRY_TXT));
        // LOC_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.locNm, (String) arcsSplyInfo.get(COL_ARCS_SPLY_SITE_CD));
        // ADDL_LOC_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.addlLocNm, (String) arcsSplyInfo.get(COL_ALT_VND_ADDR_ALL_TXT));

        // EFF_FROM_DT
        ZYPEZDItemValueSetter.setValue(pMsg02.effFromDt, (String) arcsSplyInfo.get(COL_PRNT_VND_EFF_FROM_DT));
        // EFF_THRU_DT
        if (arcsSplyInfo.get(COL_VND_INAC_DT) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, ZYPDateUtil.addDays((String) arcsSplyInfo.get(COL_VND_INAC_DT), -1));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, DEF_THRU_DT);
        }

        // SPLY_COA_CMPY_CDARCS_SPLY_COA_SEG_TXT_01
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaCmpyCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_01));
        // SPLY_COA_BR_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaBrCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_02));
        // SPLY_COA_CC_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaCcCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_03));
        // SPLY_COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaAcctCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_04));
        // SPLY_COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProdCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_05));
        // SPLY_COA_CH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaChCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_06));
        // SPLY_COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaAfflCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_07));

        // SPLY_COA_PROJ_CD
        if (arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_08) != null) {
            String splyCoaProjCd = (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_08);
            // if (splyCoaProjCd.subSequence(0, 2).equals("00")) {
            if (splyCoaProjCd.length() >= 2 && HL_COA_PROJ_CD.equals(splyCoaProjCd.subSequence(0, 2))) {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProjCd, splyCoaProjCd.substring(SP_ARCS_LIAB_SEG_TXT_08, EP_ARCS_LIAB_SEG_TXT_08));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProjCd, splyCoaProjCd);
            }
        }

        // SPLY_COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaExtnCd, (String) arcsSplyInfo.get(COL_ARCS_LIAB_SEG_TXT_09));

        // VND_PMT_TERM_DESC_TXT
        ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermDescTxt, (String) arcsSplyInfo.get(COL_VND_PMT_TERM_DESC_TXT));

        // QC#13163 Start
        // VND_PMT_TERM_CD
        if (ZYPCommonFunc.hasValue(pMsg02.vndPmtTermDescTxt)) {
            ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermCd, getVndPmtTermCd(pMsg02.vndPmtTermDescTxt.getValue()));
        } else {
            pMsg02.vndPmtTermCd.clear();
        }
        // QC#13163 End

        // INAC_DT
        ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, (String) arcsSplyInfo.get(COL_VND_INAC_DT));

        // ARCS_SPLY_SITE_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.arcsSplySiteCd, (String) arcsSplyInfo.get(COL_ARCS_SPLY_SITE_CD));

        // ARCS_SPLY_SITE_ID
        if (arcsSplyInfo.get(COL_ARCS_SPLY_SITE_ID) != null) {
            String arcsSplySiteId = arcsSplyInfo.get(COL_ARCS_SPLY_SITE_ID).toString();
            ZYPEZDItemValueSetter.setValue(pMsg02.arcsSplySiteId, new BigDecimal(arcsSplySiteId));
        }

        // SEND_ARCS_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.sendArcsFlg, ZYPConstant.FLG_ON_Y);

        return pMsg02;
    }

    /**
     * setSplySiteInfoForNew
     * @param arcsSplyInfo
     * @param prtnVndInfo
     * @param modeSupplier
     * @param modeSupplierSite
     * @param vndInfo
     * @return
     */
    private NMZC201002PMsg setSplySiteInfoForNew(Map<String, Object> arcsSplyInfo, String prntVndCd) {
        NMZC201002PMsg pMsg02 = new NMZC201002PMsg();

        // MODE
        ZYPEZDItemValueSetter.setValue(pMsg02.xxModeCd, NMZC201001Constant.CREATE_MODE);
        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prntVndCd, prntVndCd);

        // SPLY_PMT_FLG
        if (arcsSplyInfo.get(COL_SPLY_PMT_FLG) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg02.splyPmtFlg, (String) arcsSplyInfo.get(COL_SPLY_PMT_FLG));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg02.splyPmtFlg, ZYPConstant.FLG_OFF_N);
        }
        // SPLY_PO_FLG
        if (arcsSplyInfo.get(COL_SPLY_PO_FLG) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg02.splyPoFlg, (String) arcsSplyInfo.get(COL_SPLY_PO_FLG));
        } else {
            // START 2018/03/20 M.Naito [QC#21001,ADD]
            String vndTpCd = getVndTpCd(this.glblCmpyCd, (String) arcsSplyInfo.get(COL_PRNT_VND_TP_CD));
            if (ZYPCommonFunc.hasValue(vndTpCd) && (VND_TP.SUPPLIER.equals(vndTpCd))) {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyPoFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyPoFlg, ZYPConstant.FLG_OFF_N);
            }
            // END 2018/03/20 M.Naito [QC#21001,ADD]
        }

        // Common Setting Items
        pMsg02 = setSplySiteInfoComn(arcsSplyInfo, pMsg02);
        return pMsg02;
    }

    /**
     * @param arcsSplyInfo
     * @param prtnVndInfo
     * @param modeSupplier
     * @param modeSupplierSite
     * @param vndInfo
     * @return
     */
    private NMZC201002PMsg setSplySiteInfoForUpdate(Map<String, Object> arcsSplyInfo, Map<String, Object> vndInfo) {
        NMZC201002PMsg pMsg02 = new NMZC201002PMsg();

        // MODE
        ZYPEZDItemValueSetter.setValue(pMsg02.xxModeCd, NMZC201001Constant.UPDATE_MODE);

        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prntVndCd, (String) vndInfo.get(COL_PRNT_VND_CD));

        // VND_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndCd, (String) vndInfo.get(COL_VND_CD));

        // GLN_NUM
        if (vndInfo.get(COL_GLN_NUM) != null) {
            String glnNum = vndInfo.get(COL_GLN_NUM).toString();
            ZYPEZDItemValueSetter.setValue(pMsg02.glnNum, new BigDecimal(glnNum));
        }

        // FIRST_REF_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(pMsg02.firstRefCmntTxt, (String) vndInfo.get(COL_FIRST_REF_CMNT_TXT));

        // SCD_REF_CMNT_TXT
        ZYPEZDItemValueSetter.setValue(pMsg02.scdRefCmntTxt, (String) vndInfo.get(COL_SCD_REF_CMNT_TXT));

        // DBA_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.dbaNm, (String) vndInfo.get(COL_DBA_NM));

        // DUNS_NUM
        ZYPEZDItemValueSetter.setValue(pMsg02.dunsNum, (String) vndInfo.get(COL_DUNS_NUM));

        // RGTN_STS_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.rgtnStsCd, (String) vndInfo.get(COL_RGTN_STS_CD));

        // LOC_ROLE_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.locRoleTpCd, (String) vndInfo.get(COL_LOC_ROLE_TP_CD));

        // LOC_GRP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.locGrpCd, (String) vndInfo.get(COL_LOC_GRP_CD));

        // COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.coaAfflCd, (String) vndInfo.get(COL_COA_AFFL_CD));
        // INTL_VND_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.intlVndFlg, (String) vndInfo.get(COL_INTL_VND_FLG));

        // ASN_REQ_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.asnReqFlg, (String) vndInfo.get(COL_ASN_REQ_FLG));

        // FAX_NUM
        ZYPEZDItemValueSetter.setValue(pMsg02.faxNum, (String) vndInfo.get(COL_FAX_NUM));

        // TEL_NUM
        ZYPEZDItemValueSetter.setValue(pMsg02.telNum, (String) vndInfo.get(COL_TEL_NUM));

        // PAYEE_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.payeeFlg, (String) vndInfo.get(COL_PAYEE_FLG));
        // THIRD_PTY_VND_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.thirdPtyVndFlg, (String) vndInfo.get(COL_THIRD_PTY_VND_FLG));
        // TRSMT_METH_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.trsmtMethTpCd, (String) vndInfo.get(COL_TRSMT_METH_TP_CD));
        // SHPG_SVC_LVL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.shpgSvcLvlCd, (String) vndInfo.get(COL_SHPG_SVC_LVL_CD));
        // FRT_CHRG_TO_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.frtChrgToCd, (String) vndInfo.get(COL_FRT_CHRG_TO_CD));
        // FRT_CHRG_METH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.frtChrgMethCd, (String) vndInfo.get(COL_FRT_CHRG_METH_CD));
        // INV_RCV_METH_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.invRcvMethTpCd, (String) vndInfo.get(COL_INV_RCV_METH_TP_CD));
        // CHRG_RATE_VND_GRP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.chrgRateVndGrpCd, (String) vndInfo.get(COL_CHRG_RATE_VND_GRP_CD));
        // AFFL_GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.afflGlblCmpyCd, (String) vndInfo.get(COL_AFFL_GLBL_CMPY_CD));
        // ATTN_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.attnNm, (String) vndInfo.get(COL_ATTN_NM));
        // WH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.whCd, (String) vndInfo.get(COL_WH_CD));
        // SEND_PO_EML_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.sendPoEmlAddr, (String) vndInfo.get(COL_SEND_PO_EML_ADDR));
        // DEAL_CCY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.dealCcyCd, (String) vndInfo.get(COL_DEAL_CCY_CD));
        // INV_VND_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.invVndCd, (String) vndInfo.get(COL_INV_VND_CD));
        // SPLY_PMT_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.splyPmtFlg, (String) vndInfo.get(COL_SPLY_PMT_FLG));
        // SPLY_PO_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.splyPoFlg, (String) vndInfo.get(COL_SPLY_PO_FLG));

        // INV_MATCH_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.invMatchTpCd, (String) vndInfo.get(COL_INV_MATCH_TP_CD));
        // INV_TOL_RATE
        if (vndInfo.get(COL_INV_TOL_RATE) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg02.invTolRate, new BigDecimal(vndInfo.get(COL_INV_TOL_RATE).toString()));
        }

        // RCV_TOL_RATE
        if (vndInfo.get(COL_RCV_TOL_RATE) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg02.rcvTolRate, new BigDecimal(vndInfo.get(COL_RCV_TOL_RATE).toString()));

        }
        // VND_PMT_TERM_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermCd, (String) vndInfo.get(COL_VND_PMT_TERM_CD));

        // VND_PMT_METH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtMethCd, (String) vndInfo.get(COL_VND_PMT_METH_CD));
        // PAY_GRP_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.payGrpCd, (String) vndInfo.get(COL_PAY_GRP_CD));
        // SPLY_EDI_LOC_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyEdiLocCd, (String) vndInfo.get(COL_SPLY_EDI_LOC_CD));
        // SPLY_EDI_NUM
        ZYPEZDItemValueSetter.setValue(pMsg02.splyEdiNum, (String) vndInfo.get(COL_SPLY_EDI_NUM));

        // SPLY_SITE_DEAL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splySiteDealCd, (String) vndInfo.get(COL_SPLY_SITE_DEAL_CD));
        // PRE_PMT_COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaCmpyCd, (String) vndInfo.get(COL_PRE_PMT_COA_CMPY_CD));
        // PRE_PMT_COA_BR_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaBrCd, (String) vndInfo.get(COL_PRE_PMT_COA_BR_CD));
        // PRE_PMT_COA_CC_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaCcCd, (String) vndInfo.get(COL_PRE_PMT_COA_CC_CD));
        // PRE_PMT_COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaAcctCd, (String) vndInfo.get(COL_PRE_PMT_COA_ACCT_CD));
        // PRE_PMT_COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaProdCd, (String) vndInfo.get(COL_PRE_PMT_COA_PROD_CD));
        // PRE_PMT_COA_CH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaChCd, (String) vndInfo.get(COL_PRE_PMT_COA_CH_CD));
        // PRE_PMT_COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaAfflCd, (String) vndInfo.get(COL_PRE_PMT_COA_AFFL_CD));
        // PRE_PMT_COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaProjCd, (String) vndInfo.get(COL_PRE_PMT_COA_PROJ_CD));
        // PRE_PMT_COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaExtnCd, (String) vndInfo.get(COL_PRE_PMT_COA_EXTN_CD));
        // VND_RTRN_COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaCmpyCd, (String) vndInfo.get(COL_VND_RTRN_COA_CMPY_CD));
        // VND_RTRN_COA_BR_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaBrCd, (String) vndInfo.get(COL_VND_RTRN_COA_BR_CD));
        // VND_RTRN_COA_CC_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaCcCd, (String) vndInfo.get(COL_VND_RTRN_COA_CC_CD));
        // VND_RTRN_COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaAcctCd, (String) vndInfo.get(COL_VND_RTRN_COA_ACCT_CD));
        // VND_RTRN_COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaProdCd, (String) vndInfo.get(COL_VND_RTRN_COA_PROD_CD));
        // VND_RTRN_COA_CH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaChCd, (String) vndInfo.get(COL_VND_RTRN_COA_CH_CD));
        // VND_RTRN_COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaAfflCd, (String) vndInfo.get(COL_VND_RTRN_COA_AFFL_CD));
        // VND_RTRN_COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaProjCd, (String) vndInfo.get(COL_VND_RTRN_COA_PROJ_CD));
        // VND_RTRN_COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaExtnCd, (String) vndInfo.get(COL_VND_RTRN_COA_EXTN_CD));
        // XTRNL_REF_NUM
        ZYPEZDItemValueSetter.setValue(pMsg02.xtrnlRefNum, (String) vndInfo.get(COL_XTRNL_REF_NUM));
        // END_CUST_NUM
        ZYPEZDItemValueSetter.setValue(pMsg02.endCustNum, (String) vndInfo.get(COL_END_CUST_NUM));

        // BILL_TO_CUST_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.billToCustCd, (String) vndInfo.get(COL_BILL_TO_CUST_CD));

        // START 2021/02/15 G.Delgado [QC#55912,MOD]
        // Do not update inactive date if Purchasing Flag = ON, Pay Flag = OFF, and Supplier Send ARCS Flag = OFF
        if (ZYPConstant.FLG_ON_Y.equals(pMsg02.splyPoFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(pMsg02.splyPmtFlg.getValue())
                && !checkPrntVndTpSendArcs(pMsg02.prntVndCd.getValue())) {
            // INAC_DT
            ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, (String) vndInfo.get(COL_INAC_DT));
            // EFF_THRU_DT
            ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, (String) vndInfo.get(COL_EFF_THRU_DT));

            // Common Setting Items from VND
            pMsg02 = setSplySiteInfoComnFromVnd(vndInfo, pMsg02);
        } else {
            // INAC_DT
            ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, (String) arcsSplyInfo.get(COL_VND_INAC_DT));
            // EFF_THRU_DT
            if (arcsSplyInfo.get(COL_VND_INAC_DT) != null) {
                ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, ZYPDateUtil.addDays((String) arcsSplyInfo.get(COL_VND_INAC_DT), -1));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, DEF_THRU_DT);
            }

            // START 2021/10/04 G.Delgado [QC#59223,ADD]
            boolean prevActive = true;

            // Check previous inactive date
            String prevInacDt = (String) vndInfo.get(COL_INAC_DT);
            if (ZYPCommonFunc.hasValue(prevInacDt) && ZYPDateUtil.compare(this.slsDt, prevInacDt) >= 0) {
                prevActive = false;
            }
            // END 2021/10/04 G.Delgado [QC#59223,ADD]

            if (ZYPDateUtil.compare(this.slsDt, pMsg02.effThruDt.getValue()) <= 0) {
                // Common Setting Items from ARCS
                pMsg02 = setSplySiteInfoComn(arcsSplyInfo, pMsg02);

                // START 2021/10/04 G.Delgado [QC#59223,ADD]
                if (!prevActive) {
                    // Update RGTN_STS_CD if previously inactive
                    ZYPEZDItemValueSetter.setValue(pMsg02.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                }
                // END 2021/10/04 G.Delgado [QC#59223,ADD]
            } else {
                // Common Setting Items from VND
                pMsg02 = setSplySiteInfoComnFromVnd(vndInfo, pMsg02);

                // START 2021/10/04 G.Delgado [QC#59223,ADD]
                if (prevActive) {
                    // Update RGTN_STS_CD if previously active
                    ZYPEZDItemValueSetter.setValue(pMsg02.rgtnStsCd, RGTN_STS.TERMINATED);
                }
                // END 2021/10/04 G.Delgado [QC#59223,ADD]
            }
        }

        // Common Setting Items
        // pMsg02 = setSplySiteInfoComn(arcsSplyInfo, pMsg02);
        // END 2021/02/15 G.Delgado [QC#55912,MOD]
        return pMsg02;
    }

    /**
     * setCntctInfoForNew
     * @param splyCntctInfo
     * @param vndInfo
     * @return
     */
    private NMZC201003PMsg setCntctInfoForNew(Map<String, Object> splyCntctInfo, Map<String, Object> vndInfo) {
        NMZC201003PMsg pMsg03 = new NMZC201003PMsg();
        // MODE
        ZYPEZDItemValueSetter.setValue(pMsg03.xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
        // LOC_NUM
        // ZYPEZDItemValueSetter.setValue(pMsg03.locNum, (String)
        // vndInfo.get(COL_VND_CD));
        ZYPEZDItemValueSetter.setValue(pMsg03.locNum, (String) vndInfo.get(COL_LOC_NUM));
        // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

        // EFF_FROM_DT
        if (splyCntctInfo.get(COL_PRNT_VND_EFF_FROM_DT) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg03.effFromDt, (String) splyCntctInfo.get(COL_PRNT_VND_EFF_FROM_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg03.effFromDt, this.slsDt);
        }

        // EFF_THRU_DT
        if (splyCntctInfo.get(COL_VND_CTAC_INAC_DT) != null) {
            ZYPEZDItemValueSetter.setValue(pMsg03.effThruDt, ZYPDateUtil.addDays((String) splyCntctInfo.get(COL_VND_CTAC_INAC_DT), -1));
        }

        // 2016/05/11 CITS T.Hakodate UPDATE QC#8171 START
        // // CTAC_PSN_FIRST_NM
        // ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnFirstNm,
        // (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_FIRST_NM));
        // // CTAC_PSN_LAST_NM
        // ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnLastNm,
        // (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_LAST_NM));
        // CTAC_PSN_FIRST_NM
        if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_FIRST_NM))) {
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnFirstNm, (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_FIRST_NM));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnFirstNm, NONE);
        }

        // CTAC_PSN_LAST_NM
        if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_LAST_NM))) {
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnLastNm, (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_LAST_NM));
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnLastNm, NONE);
        }

        // 2016/05/11 CITS T.Hakodate UPDATE QC#8171 END

        // CTAC_TP_CD
        ZYPEZDItemValueSetter.setValue(pMsg03.ctacTpCd, this.ctacTp);

        // CTAC_PSN_ACTV_FLG
        ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

        int i = 0;
        if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM))) {
            // MODE
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

            // DS_CTAC_PNT_TP_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);

            // DS_CTAC_PNT_VAL_TXT
            String dsCtacPntValTxt = null;
            // QC#16594 Mod.
//            dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM) + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
//            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
            if (splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM) != null) {
                dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM);
            }
            if (splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM) != null) {
                if (dsCtacPntValTxt != null) {
                    dsCtacPntValTxt = dsCtacPntValTxt + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                } else {
                    dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                }
            }
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
            i++;
        }

        if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_EML_ADDR))) {
            // MODE
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

            // DS_CTAC_PNT_TP_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);

            // DS_CTAC_PNT_VAL_TXT
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, (String) splyCntctInfo.get(COL_ARCS_CTAC_EML_ADDR));
            i++;
        }

        if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM))) {
            // MODE
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

            // DS_CTAC_PNT_TP_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);

            // DS_CTAC_PNT_VAL_TXT
            String dsCtacPntValTxt = null;
            // QC#16594 Mod.
//            dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM) + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
            if (splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM) != null) {
                dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM);
            }
            if (splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM) != null) {
                if (dsCtacPntValTxt != null) {
                    dsCtacPntValTxt = dsCtacPntValTxt + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                } else {
                    dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                }
            }
            ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
            i++;
        }

        if (i != 0) {
            pMsg03.ContactPointInfoList.setValidCount(i);
        }

        return pMsg03;
    }

    /**
     * @param splyCntctInfo
     * @param vndInfo
     * @param ctacInfo
     * @return
     */
    private NMZC201003PMsg setCntctInfoForUpdate(Map<String, Object> splyCntctInfo, Map<String, Object> vndInfo, List<Map<String, Object>> ctacInfoList) {

        NMZC201003PMsg pMsg03 = new NMZC201003PMsg();
        int i = 0;

        boolean phoneUpdFlg = false;
        boolean eMailUpdFlg = false;
        boolean faxUpdFlg = false;

        for (Map<String, Object> ctacInfo : ctacInfoList) {

            // MODE
            ZYPEZDItemValueSetter.setValue(pMsg03.xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_UPD);

            // CTAC_PSN_PK
            if (ctacInfo.get(COL_CTAC_PSN_PK) != null) {
                String ctacPsnPk = ctacInfo.get(COL_CTAC_PSN_PK).toString();
                ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnPk, new BigDecimal(ctacPsnPk));
            }

            // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 START
            // LOC_NUM
            // ZYPEZDItemValueSetter.setValue(pMsg03.locNum, (String)
            // vndInfo.get(COL_VND_CD));
            ZYPEZDItemValueSetter.setValue(pMsg03.locNum, (String) vndInfo.get(COL_LOC_NUM));
            // 2016/05/11 CITS T.Hakodate UPDATE QC#6943 END

            // EFF_FROM_DT
            if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_PRNT_VND_EFF_FROM_DT))) {
                ZYPEZDItemValueSetter.setValue(pMsg03.effFromDt, (String) splyCntctInfo.get(COL_PRNT_VND_EFF_FROM_DT));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg03.effFromDt, (String) ctacInfo.get(COL_EFF_FROM_DT));
            }

            // EFF_THRU_DT
            if (ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_VND_CTAC_INAC_DT))) {
                ZYPEZDItemValueSetter.setValue(pMsg03.effThruDt, ZYPDateUtil.addDays((String) splyCntctInfo.get("VND_CTAC_INAC_DT"), -1));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg03.effThruDt, (String) ctacInfo.get(COL_EFF_THRU_DT));
            }

            // DS_PRIM_LOC_FLG
            ZYPEZDItemValueSetter.setValue(pMsg03.dsPrimLocFlg, (String) ctacInfo.get(COL_DS_PRIM_LOC_FLG));

            // CTAC_PSN_FIRST_NM
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnFirstNm, (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_FIRST_NM));
            // CTAC_PSN_LAST_NM
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnLastNm, (String) splyCntctInfo.get(COL_ARCS_CTAC_PSN_LAST_NM));
            // CTAC_TP_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacTpCd, (String) ctacInfo.get(COL_CTAC_TP_CD));
            // CTAC_PSN_CMNT_TXT
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnCmntTxt, (String) ctacInfo.get(COL_CTAC_PSN_CMNT_TXT));
            // CTAC_PSN_ACTV_FLG
            ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnActvFlg, (String) ctacInfo.get(COL_CTAC_PSN_ACTV_FLG));
            // DS_CTAC_PSN_SALT_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.dsCtacPsnSaltCd, (String) ctacInfo.get(COL_DS_CTAC_PSN_SALT_CD));
            // DS_CTAC_PSN_DEPT_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.dsCtacPsnDeptCd, (String) ctacInfo.get(COL_DS_CTAC_PSN_DEPT_CD));
            // DS_CTAC_PSN_TTL_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.dsCtacPsnTtlCd, (String) ctacInfo.get(COL_DS_CTAC_PSN_TTL_CD));
            // DS_PRIM_CTAC_TP_CD
            ZYPEZDItemValueSetter.setValue(pMsg03.dsPrimCtacTpCd, (String) ctacInfo.get(COL_DS_PRIM_CTAC_TP_CD));

            if (ctacInfo.get(COL_DS_CTAC_PNT_TP_CD) != null) {
                if (DS_CTAC_PNT_TP.PHONE_WORK.equals(ctacInfo.get(COL_DS_CTAC_PNT_TP_CD).toString())) {
                    // MODE
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_UPD);
                    // DS_CTAC_PNT_PK
                    if (ctacInfo.get(COL_DS_CTAC_PNT_PK) != null) {
                        // String dsCtacPntPk =
                        // ctacInfo.get(COL_DS_CTAC_PNT_PK).toString();
                        // ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk,
                        // new BigDecimal(dsCtacPntPk));
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, (BigDecimal) ctacInfo.get(COL_DS_CTAC_PNT_PK));
                    }
                    // DS_CTAC_PNT_TP_CD
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                    // DS_CTAC_PNT_VAL_TXT
                    String dsCtacPntValTxt = null;
                    // QC#16594 Mod.
//                    dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM) + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                    if (splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM) != null) {
                        dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM);
                    }
                    if (splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM) != null) {
                        if (dsCtacPntValTxt != null) {
                            dsCtacPntValTxt = dsCtacPntValTxt + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                        } else {
                            dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
                    // DS_OPS_OUT_FLG
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsOpsOutFlg, (String) ctacInfo.get(COL_DS_OPS_OUT_FLG));
                    // DS_CTAC_PNT_ACTV_FLG
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntActvFlg, (String) ctacInfo.get(COL_DS_CTAC_PNT_ACTV_FLG));
                    phoneUpdFlg = true;
                    i++;

                } else if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(ctacInfo.get(COL_DS_CTAC_PNT_TP_CD).toString())) {
                    // MODE
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_UPD);
                    // DS_CTAC_PNT_PK
                    if (ctacInfo.get(COL_DS_CTAC_PNT_PK) != null) {
                        // String dsCtacPntPk =
                        // ctacInfo.get(COL_DS_CTAC_PNT_PK).toString();
                        // ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk,
                        // new BigDecimal(dsCtacPntPk));
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, (BigDecimal) ctacInfo.get(COL_DS_CTAC_PNT_PK));
                    }
                    // DS_CTAC_PNT_TP_CD
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                    // DS_CTAC_PNT_VAL_TXT
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, (String) splyCntctInfo.get(COL_ARCS_CTAC_EML_ADDR));
                    // DS_OPS_OUT_FLG
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsOpsOutFlg, (String) ctacInfo.get(COL_DS_OPS_OUT_FLG));
                    // DS_CTAC_PNT_ACTV_FLG
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntActvFlg, (String) ctacInfo.get(COL_DS_CTAC_PNT_ACTV_FLG));
                    eMailUpdFlg = true;
                    i++;

                } else if (DS_CTAC_PNT_TP.FAX_WORK.equals(ctacInfo.get(COL_DS_CTAC_PNT_TP_CD).toString())) {
                    // MODE
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_UPD);
                    // DS_CTAC_PNT_PK
                    if (ctacInfo.get(COL_DS_CTAC_PNT_PK) != null) {
                        // String dsCtacPntPk =
                        // ctacInfo.get(COL_DS_CTAC_PNT_PK).toString();
                        // ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk,
                        // new BigDecimal(dsCtacPntPk));
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, (BigDecimal) ctacInfo.get(COL_DS_CTAC_PNT_PK));
                    }
                    // DS_CTAC_PNT_TP_CD
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                    // DS_CTAC_PNT_VAL_TXT
                    String dsCtacPntValTxt = null;
                    // QC#16594 Mod.
//                    dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM) + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                    if (splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM) != null) {
                        dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM);
                    }
                    if (splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM) != null) {
                        if (dsCtacPntValTxt != null) {
                            dsCtacPntValTxt = dsCtacPntValTxt + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                        } else {
                            dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
                    // DS_OPS_OUT_FLG
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsOpsOutFlg, (String) ctacInfo.get(COL_DS_OPS_OUT_FLG));
                    // DS_CTAC_PNT_ACTV_FLG
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntActvFlg, (String) ctacInfo.get(COL_DS_CTAC_PNT_ACTV_FLG));
                    faxUpdFlg = true;
                    i++;

                }
            }
        }

        // 02/07/2017 QC#16594-1 Add.
        if (i != 3) {

            if (!phoneUpdFlg && ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM))) {
                // MODE
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

                // DS_CTAC_PNT_TP_CD
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);

                // DS_CTAC_PNT_VAL_TXT
                String dsCtacPntValTxt = null;
                if (splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM) != null) {
                    dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_TEL_NUM);
                }
                if (splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM) != null) {
                    if (dsCtacPntValTxt != null) {
                        dsCtacPntValTxt = dsCtacPntValTxt + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                    } else {
                        dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_TEL_NUM);
                    }
                }
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
                i++;
            }

            if (!eMailUpdFlg && ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_EML_ADDR))) {
                // MODE
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

                // DS_CTAC_PNT_TP_CD
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);

                // DS_CTAC_PNT_VAL_TXT
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, (String) splyCntctInfo.get(COL_ARCS_CTAC_EML_ADDR));
                i++;
            }

            if (!faxUpdFlg && ZYPCommonFunc.hasValue((String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM))) {
                // MODE
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC201001Constant.NMZC2010_PROC_MODE_CRAT);

                // DS_CTAC_PNT_TP_CD
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);

                // DS_CTAC_PNT_VAL_TXT
                String dsCtacPntValTxt = null;
                if (splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM) != null) {
                    dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_AREA_FAX_NUM);
                }
                if (splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM) != null) {
                    if (dsCtacPntValTxt != null) {
                        dsCtacPntValTxt = dsCtacPntValTxt + "-" + (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                    } else {
                        dsCtacPntValTxt = (String) splyCntctInfo.get(COL_ARCS_CTAC_FAX_NUM);
                    }
                }
                ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, dsCtacPntValTxt);
                i++;
            }
            
        }

        if (i > 0) {
            pMsg03.ContactPointInfoList.setValidCount(i);
        }

        return pMsg03;
    }

    /**
     * execUpdateSupplierApi
     * @param pMsg01
     * @param pMsg02List
     * @param pMsg03List
     */
    private boolean execUpdateSupplierApi(NMZC201001PMsg pMsg01, List<NMZC201002PMsg> pMsg02List, List<NMZC201003PMsg> pMsg03List) {

        NMZC201001 updateSupplierApi = new NMZC201001();

        updateSupplierApi.execute(pMsg01, pMsg02List, pMsg03List, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg01)) {

            List<String> errIdList = S21ApiUtil.getXxMsgIdList(pMsg01);
            for (String msgId : errIdList) {
                if (msgId.endsWith("E")) {
                    String errMsgText = S21MessageFunc.clspGetMessage(MSG_ID_NPAM1323E, new String[] {"NMZC201001", S21MessageFunc.clspGetMessage(msgId)});
                    S21InfoLogOutput.println(errMsgText);
                }
            }

            return false;
        }

        return true;
    }

    /**
     * setWarnReportInfo
     * @param splySiteInfo
     */
    private void setWarnReportInfo(Map<String, Object> splySiteInfo) {

        Map<String, String> warnInfo = new HashMap<String, String>();
        warnInfo.put(COL_ARCS_SPLY_SITE_ATTRB_TXT_05, (String) splySiteInfo.get(COL_ARCS_SPLY_SITE_ATTRB_TXT_05));
        warnInfo.put(COL_ARCS_SPLY_NUM, (String) splySiteInfo.get(COL_ARCS_SPLY_NUM));
        warnInfo.put(COL_PRNT_VND_NM, (String) splySiteInfo.get(COL_PRNT_VND_NM));
        warnInfo.put(COL_ARCS_SPLY_SITE_CD, (String) splySiteInfo.get(COL_ARCS_SPLY_SITE_CD));
        warnInfoList.add(warnInfo);

    }

    /**
     * setWarnReportInfo for Supplier Update API error
     * @param splySiteList
     */
    private void setWarnReportInfoForApiErr(List<Map<String, Object>> splySiteList) {

        for (Map<String, Object> splySiteInfo : splySiteList) {
            setWarnReportInfo(splySiteInfo);
        }
    }

    /**
     * setNewRegReportInfo
     * @param splySiteInfo
     * @param vndInfo
     */
    private void setNewRegReportInfo(Map<String, Object> splySiteInfo) {

        Map<String, String> data = new HashMap<String, String>();
        data.put(COL_ARCS_SPLY_NUM, (String) splySiteInfo.get(COL_ARCS_SPLY_NUM));
        data.put(COL_PRNT_VND_NM, (String) splySiteInfo.get(COL_PRNT_VND_NM));
        data.put(COL_ARCS_SPLY_SITE_CD, (String) splySiteInfo.get(COL_ARCS_SPLY_SITE_CD));
        data.put(COL_ARCS_SPLY_SITE_ID, splySiteInfo.get(COL_ARCS_SPLY_SITE_ID).toString());
        newRegInfoList.add(data);

    }

    /**
     * setVndCdOnNewRegReportInfo
     */
    private void setVndCdOnNewRegReportInfo() {

        for (int i = 0; i < newRegInfoList.size(); i++) {
            Map<String, String> currentInfo = newRegInfoList.get(i);
            if (currentInfo.get(COL_VND_CD) == null) {
                Map<String, Object> vndInfo = getVndInfoByArcsSplySiteId(currentInfo.get(COL_ARCS_SPLY_SITE_ID));
                if (vndInfo != null) {
                    currentInfo.put(COL_VND_CD, (String) vndInfo.get(COL_VND_CD));
                }
            }
        }
    }

    /**
     * sendWarnMail
     */
    private void sendWarnMail() {

        StringBuilder mailMessage = new StringBuilder();
        int cnt = 0;

        for (Map<String, String> warnInfo : warnInfoList) {
            mailMessage.append(ML_LINE_SEPARATOR_TXT);
            mailMessage.append(ERR_MSG_CRLF);
            mailMessage.append(ZYPCommonFunc.rightPad(ML_ARCS_SPLY_SITE_ATTB5_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
            mailMessage.append(ML_COLON_TXT);
            mailMessage.append(ML_SPACE_TXT);
            mailMessage.append(warnInfo.get(COL_ARCS_SPLY_SITE_ATTRB_TXT_05));
            mailMessage.append(ERR_MSG_CRLF);
            mailMessage.append(ZYPCommonFunc.rightPad(ML_ARCS_SPLY_NUM_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
            mailMessage.append(ML_COLON_TXT);
            mailMessage.append(ML_SPACE_TXT);
            mailMessage.append(warnInfo.get(COL_ARCS_SPLY_NUM));
            mailMessage.append(ERR_MSG_CRLF);
            mailMessage.append(ZYPCommonFunc.rightPad(ML_PRNT_VND_NM_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
            mailMessage.append(ML_COLON_TXT);
            mailMessage.append(ML_SPACE_TXT);
            mailMessage.append(warnInfo.get(COL_PRNT_VND_NM));
            mailMessage.append(ERR_MSG_CRLF);
            mailMessage.append(ZYPCommonFunc.rightPad(ML_ARCS_SPLY_SITE_CD_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
            mailMessage.append(ML_COLON_TXT);
            mailMessage.append(ML_SPACE_TXT);
            mailMessage.append(warnInfo.get(COL_ARCS_SPLY_SITE_CD));
            mailMessage.append(ERR_MSG_CRLF);
            cnt++;
        }
        mailMessage.append(ML_LINE_SEPARATOR_TXT);
        if (cnt > 0) {
            sendMail(mailMessage.toString(), MAIL_TEMPLATE_ID_M001);
        }

    }

    /**
     * sendNewRegMail
     */
    private void sendNewRegMail() {

        StringBuilder mailMessage = new StringBuilder();
        int cnt = 0;
        for (Map<String, String> newRegInfo : newRegInfoList) {
            if (newRegInfo.get(COL_VND_CD) != null) {
                mailMessage.append(ML_LINE_SEPARATOR_TXT);
                mailMessage.append(ERR_MSG_CRLF);
                mailMessage.append(ZYPCommonFunc.rightPad(ML_ARCS_SPLY_NUM_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
                mailMessage.append(ML_COLON_TXT);
                mailMessage.append(ML_SPACE_TXT);
                mailMessage.append(newRegInfo.get(COL_ARCS_SPLY_NUM));
                mailMessage.append(ERR_MSG_CRLF);
                mailMessage.append(ZYPCommonFunc.rightPad(ML_PRNT_VND_NM_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
                mailMessage.append(ML_COLON_TXT);
                mailMessage.append(ML_SPACE_TXT);
                mailMessage.append(newRegInfo.get(COL_PRNT_VND_NM));
                mailMessage.append(ERR_MSG_CRLF);
                mailMessage.append(ZYPCommonFunc.rightPad(ML_VND_CD_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
                mailMessage.append(ML_COLON_TXT);
                mailMessage.append(ML_SPACE_TXT);
                mailMessage.append(newRegInfo.get(COL_VND_CD));
                mailMessage.append(ERR_MSG_CRLF);
                mailMessage.append(ZYPCommonFunc.rightPad(ML_ARCS_SPLY_SITE_CD_TXT, ML_LEN_PADDING_SIZE, ML_SPACE_TXT));
                mailMessage.append(ML_COLON_TXT);
                mailMessage.append(ML_SPACE_TXT);
                mailMessage.append(newRegInfo.get(COL_ARCS_SPLY_SITE_CD));
                mailMessage.append(ERR_MSG_CRLF);
                cnt++;
            }

        }
        mailMessage.append(ML_LINE_SEPARATOR_TXT);

        if (cnt > 0) {
            sendMail(mailMessage.toString(), MAIL_TEMPLATE_ID_M002);
        }
    }

    /**
     * sendMail
     * @param subject
     * @param msgTxt
     */
    /**
     * sendMail
     * @param subject
     * @param msgTxt
     */
    private void sendMail(String msgTxt, String mailTmpId) {

        // Get From Mail Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_FROM_ADDR_GRP_ID);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (!hasValueList(addrFromList)) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // Get To Mail Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, mailUserGroup);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        // Get Template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, mailTmpId);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(MSG_ID_NMAM8031E, new String[] {mailTmpId });
        }

        String inFormat = template.getDefaultDateFormat() + MAIL_TS_FMT.substring(MAIL_LEN_FMT_SUBSTR);
        String currentTs = ZYPDateUtil.getCurrentSystemTime(inFormat);
        ZYPLocalTimeData locTmDatlocal = ZYPLocalTimeUtil.convertTimeSys2Biz(currentTs, inFormat);
        currentTs = locTmDatlocal.getTime();
        template.setTemplateParameter(MAIL_FIELD_TIMESTAMP, currentTs);
        template.setTemplateParameter(MAIL_FIELD_MESSAGE, msgTxt);

        // *****************************************************************
        // Post mail
        // *****************************************************************
        S21Mail mail;
        for (S21MailAddress addr : addrToList) {
            mail = new S21Mail(this.glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddress(addr);
            mail.setMailTemplate(template);
            mail.postMail();
        }
    }

    // ****************************************************************
    // Data Check Method
    // ****************************************************************
    private static <T extends List< ? >> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    // START 2018/03/12 M.Naito [QC#21001,ADD]
    /**
     * Get VndTpCd.
     * @param glblCmpyCd String
     * @param prntVndTpCd String
     * @return vndTpCd or null
     */
    private String getVndTpCd(String glblCmpyCd, String prntVndTpCd) {

        PRNT_VND_TPTMsg prntVndTp = new PRNT_VND_TPTMsg();

        ZYPEZDItemValueSetter.setValue(prntVndTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVndTp.prntVndTpCd, prntVndTpCd);

        prntVndTp = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(prntVndTp);

        if (prntVndTp == null) {
            return null;
        } else {
            return prntVndTp.vndTpCd.getValue();
        }
    }
    // END 2018/03/12 M.Naito [QC#21001,ADD]

    // START 2021/02/15 G.Delgado [QC#55912,ADD]
    /**
     * setSplySiteInfoComnFromVnd
     * @param vndInfo
     * @param pMsg02
     * @return pMsg02
     */
    private NMZC201002PMsg setSplySiteInfoComnFromVnd(Map<String, Object> vndInfo, NMZC201002PMsg pMsg02) {
        // FIRST_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, (String) vndInfo.get(COL_FIRST_LINE_ADDR));
        // SCD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.scdLineAddr, (String) vndInfo.get(COL_SCD_LINE_ADDR));
        // THIRD_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.thirdLineAddr, (String) vndInfo.get(COL_THIRD_LINE_ADDR));
        // FOURTH_LINE_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.frthLineAddr, (String) vndInfo.get(COL_FRTH_LINE_ADDR));
        // CTY_ADDR
        ZYPEZDItemValueSetter.setValue(pMsg02.ctyAddr, (String) vndInfo.get(COL_CTY_ADDR));
        // CNTY_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.cntyNm, getCntyNm((BigDecimal) vndInfo.get(COL_CNTY_PK)));
        // PROV_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.provNm, (String) vndInfo.get(COL_PROV_NM));
        // ST_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.stCd, (String) vndInfo.get(COL_ST_CD));
        // POST_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.postCd, (String) vndInfo.get(COL_POST_CD));
        // CTRY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.ctryCd, (String) vndInfo.get(COL_CTRY_CD));
        // LOC_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.locNm, (String) vndInfo.get(COL_LOC_NM));
        // ADDL_LOC_NM
        ZYPEZDItemValueSetter.setValue(pMsg02.addlLocNm, (String) vndInfo.get(COL_ADDL_LOC_NM));
        // EFF_FROM_DT
        ZYPEZDItemValueSetter.setValue(pMsg02.effFromDt, (String) vndInfo.get(COL_EFF_FROM_DT));
        // SPLY_COA_CMPY_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaCmpyCd, (String) vndInfo.get(COL_SPLY_COA_CMPY_CD));
        // SPLY_COA_BR_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaBrCd, (String) vndInfo.get(COL_SPLY_COA_BR_CD));
        // SPLY_COA_CC_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaCcCd, (String) vndInfo.get(COL_SPLY_COA_CC_CD));
        // SPLY_COA_ACCT_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaAcctCd, (String) vndInfo.get(COL_SPLY_COA_ACCT_CD));
        // SPLY_COA_PROD_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProdCd, (String) vndInfo.get(COL_SPLY_COA_PROD_CD));
        // SPLY_COA_CH_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaChCd, (String) vndInfo.get(COL_SPLY_COA_CH_CD));
        // SPLY_COA_AFFL_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaAfflCd, (String) vndInfo.get(COL_SPLY_COA_AFFL_CD));
        // SPLY_COA_PROJ_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProjCd, (String) vndInfo.get(COL_SPLY_COA_PROJ_CD));
        // SPLY_COA_EXTN_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaExtnCd, (String) vndInfo.get(COL_SPLY_COA_EXTN_CD));
        // VND_PMT_TERM_DESC_TXT
        ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermDescTxt, (String) vndInfo.get(COL_VND_PMT_TERM_DESC_TXT));
        // VND_PMT_TERM_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermCd, (String) vndInfo.get(COL_VND_PMT_TERM_CD));
        // ARCS_SPLY_SITE_CD
        ZYPEZDItemValueSetter.setValue(pMsg02.arcsSplySiteCd, (String) vndInfo.get(COL_ARCS_SPLY_SITE_CD));
        // ARCS_SPLY_SITE_ID
        ZYPEZDItemValueSetter.setValue(pMsg02.arcsSplySiteId, (BigDecimal) vndInfo.get(COL_ARCS_SPLY_SITE_ID));
        // SEND_ARCS_FLG
        ZYPEZDItemValueSetter.setValue(pMsg02.sendArcsFlg, (String) vndInfo.get(COL_SEND_ARCS_FLG));

        return pMsg02;
    }

    /**
     * getCntyNm
     * @param cntyPk
     * @return cntyNm or null
     */
    private String getCntyNm(BigDecimal cntyPk) {

        CNTYTMsg cnty = new CNTYTMsg();

        ZYPEZDItemValueSetter.setValue(cnty.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cnty.cntyPk, cntyPk);

        cnty = (CNTYTMsg) EZDTBLAccessor.findByKey(cnty);

        if (cnty == null) {
            return null;
        } else {
            return cnty.cntyNm.getValue();
        }
    }
    // END 2021/02/15 G.Delgado [QC#55912,ADD]
}
