/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLC.NLCB009001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NLCI0050_01TMsg;
import business.db.INVTY_DISCR_RPT_WRKTMsg;
import business.db.INVTY_SNAP_SHOTTMsg;

import com.canon.cusa.s21.common.NLX.NLXC009001.NLXMailSend;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
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
 * Inventory Discrepancy Report
 * 
 *  Inventory Information is received from WMS or eStore.
 *  The difference between 1. and Inventory Master is checked.
 *  The difference is output to the work table for Discrepancy Report.
 * 
 *  Date        Company      Name         Create/Update  Defect No          
 *------------------------------------------------------------------------
 *  07/31/2009  Canon        Y.Bae        Create         N/A
 *  08/26/2010  Fujitsu      M.Yamada     Update         319
 *  04/05/2011  CSAI         M.Takahashi  Update         2022(PROD)
 *  05/18/2011  CSAI         M.Takahashi  Update         2233(PROD)
 *  09/06/2011  CSAI         M.Takahashi  Update         356760(PROD)
 *  03/27/2012  CSAI         T.Miyamoto   Update         Oce#5
 *  07/11/2012  CSAI         T.Miyamoto   Update         Oce#475
 *  07/31/2012  CSAI         N.Sasaki     Update         ITG#397579
 *  08/26/2013  Fujitsu      Y.Taoka      Update         Def#1767
 *  11/18/2013  Fujitsu      Y.Taoka      Update         Def#3134
 *  12/04/2013  Hitachi      T.Aoyagi     Update         QC2852
 *  06/12/2014  Fujitsu      Y.Taoka      Update         WDS-NA#401
 *  08/03/2019  CITS         K.Ogino      Update         QC#52306
 * <pre>
 */
public class NLCB009001 extends S21BatchMain  implements NLCB009001Constant {

    /** SQL Accessor */
    private S21SsmLowLevelCodingClient ssmLLCodingClient = null;

    /** User Profile */
    private S21UserProfileService profile;

    /** Terminate Code */
    private TERM_CD termCd;

    /** Counter : S21 read */
    private int selectCount = 0;

    /** Counter : No Discrepancy */
    private int normalRecCount = 0;

    /** Counter : INVTY_DISCR_RPT_WRK output */
    private int rptRecCount = 0;

    /** Counter : Error */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Create Report Date */
    private String createReportDate = null;

    /** Create Report Time */
    private String createReportTime = null;

    /** Transaction ID Oce#475*/
    private BigDecimal[] trxId = null;

    /** key : Warehouse Code */
    private int keyWhCodeSize;

    /** key : Mdse Code */
    private int keyMdseCdSize;

    /** key : Stock Status Code */
    private int keyStkStsSize;

    /** errorList for Mail*/
    private List<Map<String, String>> errorList = new ArrayList<Map<String, String>>();

    /** ssmExecParam */
    private S21SsmExecutionParameter ssmExecParam = null;

    // QC#52306
    private String[] dcStockLocStsCd = null;

    /**
     * Main
     * @param args argument
     */
    public static void main(String[] args) {

        // Initialization of S21WfBatchMain
        new NLCB009001().executeBatch(NLCB009001.class.getSimpleName());
    }

    /**
     * Initialization process
     */
    @Override
    protected void initRoutine() {

        EZDDebugOutput.println(1, NLCB009001 + "( initRoutine ) start", this);

        // Initialization of SQL Accessor
        this.ssmLLCodingClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // Get a Global Company Code
        glblCmpyCd = profile.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            // When the Global Company Code is not obtained,
            // processing is ended
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_GLBL_CMPY_CODE });
        }

        if (!ZYPCommonFunc.hasValue(getUserVariable1())) {
            // When the Discrepancy Report Preservation is not
            // obtained,
            // processing is ended
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_DISCR_RPT_PRESER_PERIOD });
        }

        if (!ZYPCommonFunc.isHankakuSuuji(getUserVariable1())) {
            // When the Discrepancy Report Preservation is not
            // numerical value,
            // processing is ended
            throw new S21AbendException(ZZM9004E, new String[] {MSG_STR_DISCR_RPT_PRESER_PERIOD });
        }

        if (!ZYPCommonFunc.hasValue(getInterfaceID())) {
            // When the Interface ID is not obtained,
            // processing is ended
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_INTERFACE_ID });
        }
        if (!CHK_NLCI0050.equals(getInterfaceID()) && !CHK_NLCI0110.equals(getInterfaceID())) {
            // When the Interface ID is not correct,
            // processing is ended
            throw new S21AbendException(NLCM0063E, new String[] {MSG_STR_INTERFACE_ID + " : " + getInterfaceID() });
        }

        // QC#52306
        String constVal = ZYPCodeDataUtil.getVarCharConstValue("NLCB0090_DC_STOCK_SMRY_LOC_STS", this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(constVal)) {
            dcStockLocStsCd = constVal.split(",");
        } else {
            dcStockLocStsCd = new String[]{LOC_STS.DC_STOCK, LOC_STS.WORK_IN_PROCESS_REMAN};
        }

        // The size of the key for the comparison is obtained
        INVTY_SNAP_SHOTTMsg snapTmsg = new INVTY_SNAP_SHOTTMsg();
        keyWhCodeSize = snapTmsg.getAttr("invtyLocCd").getDigit();
        keyStkStsSize = snapTmsg.getAttr("stkStsCd").getDigit();
        NLCI0050_01TMsg alci0050Tmsg = new NLCI0050_01TMsg();
        keyMdseCdSize = alci0050Tmsg.getAttr("itemCodeIf").getDigit();

        ssmExecParam = new S21SsmExecutionParameter();
        ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        EZDDebugOutput.println(1, NLCB009001 + "( initRoutine ) end", this);
    }

    /**
     * Main process
     */
    @Override
    protected void mainRoutine() {

        EZDDebugOutput.println(1, NLCB009001 + "( mainRoutine ) start", this);

        // Get operation date
        createReportDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        // Get current system time
        createReportTime = ZYPDateUtil.getCurrentSystemTime(TM_PTRN_HHMMSS);

        // INVTY_DISCR_RPT_WORK table is deleted
        purgeDiscrRptWrk();

        // Create Report
        reportProcess();

        if (!errorList.isEmpty()) {
            errMailSend();
            commit();
        }

        EZDDebugOutput.println(1, NLCB009001 + "( mainRoutine ) end", this);
    }

    /**
     * End process
     */
    @Override
    protected void termRoutine() {

        EZDDebugOutput.println(1, NLCB009001 + "( termRoutine ) start", this);

        String[] tmp1 = {"INVTY", "read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(ZZBM0009I, tmp1);

        String[] tmp2 = {"INVTY", "skip", Integer.toString(normalRecCount) };
        S21InfoLogOutput.println(ZZBM0009I, tmp2);

        String[] tmp3 = {"INVTY_DISCR_RPT_WRK", "insert", Integer.toString(rptRecCount) };
        S21InfoLogOutput.println(ZZBM0009I, tmp3);

        // Setting of termination code
        setTermState(this.termCd, rptRecCount, errorCount, rptRecCount + errorCount);

        EZDDebugOutput.println(1, NLCB009001 + "( termRoutine ) end", this);
    }

    /**
     * INVTY_DISCR_RPT_WORK table is deleted
     */
    private void purgeDiscrRptWrk() {

        EZDDebugOutput.println(1, NLCB009001 + "( purgeDiscrRptWrk ) start", this);

        PreparedStatement stmtPurge = null;
        ResultSet rsPurge = null;

        try {
            //S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
            // set a fetch size
            //ssmExecParam.setFetchSize(FETCH_SIZE);

            // set a SQL parameter
            Map<String, String> queryParam = new HashMap<String, String>();
            // Global Company Code
            queryParam.put("glblCmpyCd", glblCmpyCd);
            // processDate - Discrepancy Report Preservation period
            queryParam.put("dateCondition", ZYPDateUtil.addDays(createReportDate, -Integer.parseInt(getUserVariable1())));

            stmtPurge = this.ssmLLCodingClient.createPreparedStatement("getPurgeWorkDiscrepancyReport", queryParam, ssmExecParam);

            rsPurge = stmtPurge.executeQuery();

            int commitCount = 0;

            while (rsPurge.next()) {

                final INVTY_DISCR_RPT_WRKTMsg outRecord = new INVTY_DISCR_RPT_WRKTMsg();

                outRecord.glblCmpyCd.setValue(rsPurge.getString(COL_NM_GLBL_CMPY_CD));
                outRecord.invtyDiscrRptWrkPk.setValue(rsPurge.getBigDecimal(COL_NM_INVTY_DISCR_RPT_WRK_PK));

                // The physical deletion processing is done
                EZDTBLAccessor.remove(outRecord);
                commitCount++;

                if (commitCount >= getCommitCount()) {
                    commit();
                    commitCount = 0;
                }
            }

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtPurge, rsPurge);
        }
        EZDDebugOutput.println(1, NLCB009001 + "( purgeDiscrRptWrk ) end", this);
    }

    /**
     * discrepancy check and report output
     */
    private void reportProcess() {

        PreparedStatement stmtRelated = null;
        ResultSet rsRelated = null;
        PreparedStatement stmtS21 = null;
        ResultSet rsS21 = null;

        try {

            // The Transaction ID is obtained
            S21TransactionTableAccessor trxTblAccessor = new S21TransactionTableAccessor();
            trxId = trxTblAccessor.getIntegrationRecordInitDesc(getInterfaceID()); //Oce#475

            if (0 == trxId.length) { //Oce#475
                // throw new S21AbendException(ZZM9000E, new String[]{MSG_STR_TRANSACTION_ID });
                Map<String, String> msgMap = new HashMap<String, String>();
                String msg = S21MessageFunc.clspGetMessage(ZZM9000E, new String[] {MSG_STR_TRANSACTION_ID });
                msgMap.put(NLXMailSend.KEY_MESSAGE_ID, ZZM9000E);
                msgMap.put(NLXMailSend.KEY_MESSAGE, msg);
                errorList.add(msgMap);
                S21InfoLogOutput.println(ZZM9000E, new String[] {MSG_STR_TRANSACTION_ID });
                return;
            }

            // Extraction of I/F table
            String statementId = "getWMSInterface";
            boolean isNLCI0110 = false;
            if (CHK_NLCI0110.equals(getInterfaceID())) {
                statementId = "getEStoreInterface";
                isNLCI0110 = true;
            }

            String[] whCdArr = getTargetWH_CD(isNLCI0110);
            if (whCdArr == null) {
                throw new S21AbendException("NLCM0111E", new String[] {"There", "No WH Code" });
            }

            // Loop on each WH_CD start.
            for (int i = 0; i < whCdArr.length; i++) {

                String wmsWhCd = whCdArr[i];

                Map<String, Object> queryParamRelated = setQueryParamRelated(isNLCI0110, wmsWhCd);
                stmtRelated = this.ssmLLCodingClient.createPreparedStatement(statementId, queryParamRelated, ssmExecParam);
                rsRelated = stmtRelated.executeQuery();

                // If no_data found then send Mail
                boolean curRelated = rsRelated.next();
                if (!curRelated) {
                    Map<String, String> msgMap = new HashMap<String, String>();
                    String msg = S21MessageFunc.clspGetMessage(NLCM0095E, new String[] {"wmsWhCd", wmsWhCd });
                    msgMap.put(NLXMailSend.KEY_MESSAGE_ID, NLCM0095E);
                    msgMap.put(NLXMailSend.KEY_MESSAGE, msg);
                    errorList.add(msgMap);
                    S21InfoLogOutput.println(NLCM0095E, new String[] {"WH", wmsWhCd });

                    continue;
                }

                boolean curS21 = false;
                String keyRelated = null;
                String keyS21 = null;
                final String highValue = getStringHighValue();

                Map<String, Object> queryParamS21 = setQueryParamS21(isNLCI0110);
                queryParamS21.put("invtyLocCd", wmsWhCd);
                queryParamS21.put("invtyLocCdList", getWhCdForWmsWh(wmsWhCd));
                queryParamS21.put("statementId", statementId);
                // QC#52306
                queryParamS21.put("dcStockLocStsCd", dcStockLocStsCd);
                queryParamS21.put("dcStock", LOC_STS.DC_STOCK);

                stmtS21 = this.ssmLLCodingClient.createPreparedStatement("getInventorySnapShot", queryParamS21, ssmExecParam);
                // Extraction of INVTY_SNAP_SHOT table
                rsS21 = stmtS21.executeQuery();
                curS21 = rsS21.next();

                // Discrepancy existence flag
                boolean isExistDiscrepancy = false;
                for (; curRelated || curS21;) {

                    // The key for the comparison is set
                    if (curRelated) {
                        StringBuilder keyBuilder = new StringBuilder();
                        keyBuilder.append(ZYPCommonFunc.rightPad(rsRelated.getString(COL_NM_INVTY_LOC_CD), keyWhCodeSize, " "));
                        keyBuilder.append(ZYPCommonFunc.rightPad(rsRelated.getString(COL_NM_MDSE_CD), keyMdseCdSize, " "));
                        keyBuilder.append(ZYPCommonFunc.rightPad(rsRelated.getString(COL_NM_STK_STS_CD), keyStkStsSize, " "));
                        keyRelated = keyBuilder.toString();
                        //S21InfoLogOutput.println("IF :" + keyRelated);
                    } else {
                        keyRelated = highValue;
                    }
                    if (curS21) {
                        StringBuilder keyBuilder = new StringBuilder();
                        keyBuilder.append(ZYPCommonFunc.rightPad(rsS21.getString(COL_NM_INVTY_LOC_CD), keyWhCodeSize, " "));
                        keyBuilder.append(ZYPCommonFunc.rightPad(rsS21.getString(COL_NM_MDSE_CD), keyMdseCdSize, " "));
                        keyBuilder.append(ZYPCommonFunc.rightPad(rsS21.getString(COL_NM_STK_STS_CD), keyStkStsSize, " "));
                        keyS21 = keyBuilder.toString();
                        //S21InfoLogOutput.println("S21:" + keyS21);
                        selectCount++;
                    } else {
                        keyS21 = highValue;
                    }

                    // Discrepancy check
                    int cmp = keyRelated.compareTo(keyS21);
                    if (cmp == NUM_ZERO) {
                        // The key to I/F and S21 is corresponding

                        // On Hand Qty is compared
                        if (checkNull(rsRelated.getBigDecimal(COL_NM_INVTY_QTY)).compareTo(checkNull(rsS21.getBigDecimal(COL_NM_INVTY_QTY))) == NUM_ZERO) {
                            // Same none
                            normalRecCount++;
                        } else {

                            BigDecimal seqNo = getSequenceNumber();
                            // TMsg parameter setting for
                            // INVTY_DISCR_RPT_WRKTMsg
                            INVTY_DISCR_RPT_WRKTMsg discrTMsg = setInvtyRptWrk(seqNo, rsRelated, rsS21, wmsWhCd);
                            // It registers to the INVTY_DISCR_RPT_WRK
                            // table
                            S21FastTBLAccessor.insert(discrTMsg);
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(discrTMsg.getReturnCode())) {
                                errorCount++;
                                // When terminating abnormally,
                                // processing is ended
                                String[] tmp = {TBL_ID_INVTY_DISCR_RPT_WRK, "GLBL_CMPY_CD = " + glblCmpyCd + ", INVTY_DISCR_RPT_WRK_SQ = " + seqNo };
                                throw new S21AbendException(NLCM0034E, tmp);
                            }
                            isExistDiscrepancy = true;

                            rptRecCount++;
                        }
                        // Logical deletion execution of interface table
                        // setRemoveInterface(rsRelated);
                        // The next record reading
                        curRelated = rsRelated.next();
                        curS21 = rsS21.next();

                    } else if (cmp > NUM_ZERO) {
                        // There is no record in I/F

                        if (BigDecimal.ZERO.compareTo(checkNull(rsS21.getBigDecimal(COL_NM_INVTY_QTY))) != NUM_ZERO) {

                            BigDecimal seqNo = getSequenceNumber();
                            // TMsg parameter setting for
                            // INVTY_DISCR_RPT_WRKTMsg
                            INVTY_DISCR_RPT_WRKTMsg discrTMsg = setInvtyRptWrk(seqNo, rsS21, false, wmsWhCd);
                            // It registers to the INVTY_DISCR_RPT_WRK
                            // table
                            S21FastTBLAccessor.insert(discrTMsg);
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(discrTMsg.getReturnCode())) {
                                errorCount++;
                                // When terminating abnormally,
                                // processing is ended
                                String[] tmp = {NLCM0034E, TBL_ID_INVTY_DISCR_RPT_WRK, "GLBL_CMPY_CD = " + rsS21.getString(COL_NM_GLBL_CMPY_CD) + ", INVTY_DISCR_RPT_WRK_SQ = " + seqNo };
                                throw new S21AbendException(NLCM0034E, tmp);
                            }
                            isExistDiscrepancy = true;

                            rptRecCount++;
                        }
                        // The next record reading
                        curS21 = rsS21.next();

                    } else if (cmp < NUM_ZERO) {
                        // There is no record in S21

                        if (BigDecimal.ZERO.compareTo(checkNull(rsRelated.getBigDecimal(COL_NM_INVTY_QTY))) == NUM_ZERO) {
                            // Difference none
                            normalRecCount++;
                        } else {

                            BigDecimal seqNo = getSequenceNumber();
                            // TMsg parameter setting for
                            // INVTY_DISCR_RPT_WRKTMsg
                            INVTY_DISCR_RPT_WRKTMsg discrTMsg = setInvtyRptWrk(seqNo, rsRelated, true, wmsWhCd);
                            // It registers to the INVTY_DISCR_RPT_WRK
                            // table
                            S21FastTBLAccessor.insert(discrTMsg);
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(discrTMsg.getReturnCode())) {
                                errorCount++;
                                // When terminating abnormally,
                                // processing is ended
                                String[] tmp = {NLCM0034E, TBL_ID_INVTY_DISCR_RPT_WRK, "GLBL_CMPY_CD = " + rsRelated.getString(COL_NM_GLBL_CMPY_CD) + ", INVTY_DISCR_RPT_WRK_SQ = " + seqNo };
                                throw new S21AbendException(NLCM0034E, tmp);
                            }
                            isExistDiscrepancy = true;
                            rptRecCount++;
                        }
                        curRelated = rsRelated.next();
                    }
                }
                // ITG#397579
                createEstoreWhRecToRptWrk(wmsWhCd);

                // when Loop is end
                commit(); // commit for ReportWRK

                // START 2014/06/12 [WDS-NA#401, MOD]
                // Output of Discrepancy report and e-mail
//                callReport(physWhCd, isExistDiscrepancy);
                callReportWithEMail(wmsWhCd, isExistDiscrepancy);
                // END 2014/06/12 [WDS-NA#401, ADD]

                // INVTY_DISCR_RPT_WRK table is update
                if (isExistDiscrepancy) {
                    updatePrintFlg(wmsWhCd);
                }

                // Per WH_CD
                commit();

                S21SsmLowLevelCodingClient.closeResource(stmtRelated, rsRelated);
                S21SsmLowLevelCodingClient.closeResource(stmtS21, rsS21);
            } // Loop on each WH_CD end.

            // Registration processing to transaction management table
            for (int i = 0; trxId.length > i; i++) { // Oce#475
                trxTblAccessor.endIntegrationProcess(getInterfaceID(), trxId[i]);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtRelated, rsRelated);
            S21SsmLowLevelCodingClient.closeResource(stmtS21, rsS21);
        }
    }

    /**
     * ITG#397579 getWhCdForEstr
     * @return String array
     */
    private String[] getWhCdForWmsWh(String wmsWhCd) {

        String[] strArr = new String[]{};

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("wmsWhCd", wmsWhCd);

        S21SsmEZDResult ssmResult = NLCB009001Query.getInstance().getWhCdByWmsWh(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List list = (List) ssmResult.getResultObject();
            strArr = (String[]) list.toArray(new String[list.size()]);
        }
        return strArr;
    }

    /**
     * ITG#397579 createEstoreWhRecToRptWrk
     * @param physWhCd String
     */
    private void createEstoreWhRecToRptWrk(String physWhCd) {

        // START  ADD 08/26/2013 Def#1767
        String[] estrWhArr = getEstrWhArr(getWhCdForWmsWh(physWhCd));
        if (estrWhArr.length <= 1) {
            return;
        }
        // END   ADD 08/26/2013 Def#1767

        List list = getEstoreWmsWhInvtyInfo();
        if (list != null && list.size() > 0) {

//            String[] estrWhArr = getEstrWhArr(getWhCdForEstr(physWhCd));// DEL 08/26/2013 Def#1767

            for (int i = 0; i < list.size(); i++) {

                Map map = (Map) list.get(i);

                INVTY_DISCR_RPT_WRKTMsg tMsg = new INVTY_DISCR_RPT_WRKTMsg();

                BigDecimal invtyDiscrRptWrkPk = getInvtyDiscrRptWrkPk(map);
                if (invtyDiscrRptWrkPk != null) {
                    setValueToTMsg(tMsg, map, invtyDiscrRptWrkPk);

                    S21FastTBLAccessor.update(tMsg);
                } else {
                    setValueToTMsg(tMsg, map, ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_NM_INVTY_DISCR_RPT_WRK_SQ));

                    S21FastTBLAccessor.insert(tMsg);
                }

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    errorCount++;
                    // end process
                    throw new S21AbendException("NLCM0111E", new String[] {"There", "an Error for Inserting INVTY_DISCR_RPT_WRK with code: " + tMsg.getReturnCode() });
                } else {
                    createMissingWh(tMsg, estrWhArr);
                    rptRecCount++;
                }
            }
        }
    }

    private void setValueToTMsg(INVTY_DISCR_RPT_WRKTMsg tMsg, Map map, BigDecimal invtyDiscrRptWrkPk) {

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.invtyDiscrRptWrkPk.setValue(invtyDiscrRptWrkPk);
        tMsg.invtyDiscrRptCratDt.setValue(checkNull((String) map.get("INVTY_DISCR_RPT_CRAT_DT")));
        tMsg.invtyDiscrRptCratTm.setValue(checkNull((String) map.get("INVTY_DISCR_RPT_CRAT_TM")));
        tMsg.invtyLocCd.setValue(checkNull((String) map.get("INVTY_LOC_CD")));
        tMsg.firstProdCtrlCd.setValue(checkNull((String) map.get("FIRST_PROD_CTRL_CD")));
        tMsg.mdseCd.setValue(checkNull((String) map.get("MDSE_CD")));
        tMsg.locStsCd.setValue(checkNull((String) map.get("LOC_STS_CD")));
        tMsg.stkStsCd.setValue(checkNull((String) map.get("STK_STS_CD")));
        tMsg.adjInvtyOrdNum.clear();
        tMsg.adjInvtyOrdLineNum.clear();
        tMsg.s21InvtyQty.setValue(checkNull((BigDecimal) map.get("INVTY_QTY")));
        tMsg.relnSysInvtyQty.clear();
        tMsg.diffInvtyQty.clear();
        tMsg.mdseNm.setValue(checkNull((String) map.get("MDSE_NM")));
        tMsg.invtyDiscrRptPrintFlg.setValue((String) map.get("INVTY_DISCR_RPT_PRINT_FLG"));
        tMsg.adjOrdRefInvtyQty.clear();
        tMsg.physWhCd.setValue(checkNull((String) map.get("PHYS_WH_CD")));
    }

    /**
     * ITG#397579 createMissingWh
     * @param tMsgParam INVTY_DISCR_RPT_WRKTMsg
     */
    private void createMissingWh(INVTY_DISCR_RPT_WRKTMsg tMsg, String[] estrWhArr) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("mdseCd", tMsg.mdseCd.getValue());
        ssmParam.put("locStsCd", tMsg.locStsCd.getValue());
        ssmParam.put("stkStsCd", tMsg.stkStsCd.getValue());
        ssmParam.put("invtyDiscrRptCratDt", tMsg.invtyDiscrRptCratDt.getValue());
        ssmParam.put("invtyDiscrRptCratTm", tMsg.invtyDiscrRptCratTm.getValue());
        ssmParam.put("physWhCd", tMsg.physWhCd.getValue());
        ssmParam.put("eStrWhCdList", estrWhArr);
        ssmParam.put("invtyLocCd", null);

        S21SsmEZDResult ssmResult = NLCB009001Query.getInstance().getWrkForEStore(ssmParam);
        if (ssmResult.isCodeNormal()) {
            List listCreated = (List) ssmResult.getResultObject();

            if (listCreated != null && listCreated.size() > 0) {

                List<String> listNotCreated = getWhNotCreated(estrWhArr, listCreated);
                for (int i = 0; i < listNotCreated.size(); i++) {
                    String whNotCreated = listNotCreated.get(i);
                    createWrkForNotCreatedWh(tMsg, whNotCreated);
                }
            }
        }
    }

    /**
     * ITG#397579 getInvtyDiscrRptWrkPk
     * @param paramMap Map
     * @return BigDecimal
     */
    private BigDecimal getInvtyDiscrRptWrkPk(Map paramMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("mdseCd", checkNull((String) paramMap.get("MDSE_CD")));
        ssmParam.put("locStsCd", checkNull((String) paramMap.get("LOC_STS_CD")));
        ssmParam.put("stkStsCd", checkNull((String) paramMap.get("STK_STS_CD")));
        ssmParam.put("invtyDiscrRptCratDt", checkNull((String) paramMap.get("INVTY_DISCR_RPT_CRAT_DT")));
        ssmParam.put("invtyDiscrRptCratTm", checkNull((String) paramMap.get("INVTY_DISCR_RPT_CRAT_TM")));
        ssmParam.put("physWhCd", checkNull((String) paramMap.get("PHYS_WH_CD")));
        ssmParam.put("invtyLocCd", checkNull((String) paramMap.get("INVTY_LOC_CD")));

        S21SsmEZDResult ssmResult = NLCB009001Query.getInstance().getWrkForEStore(ssmParam);

        // START MOD 08/26/2013 Def#1767
        if (ssmResult.isCodeNormal()) {
            List listCreated = (List) ssmResult.getResultObject();
            if (listCreated != null && listCreated.size() > 0) {

                Map<String, Object> map = (Map<String, Object>) listCreated.get(0);
                return (BigDecimal) map.get("INVTY_DISCR_RPT_WRK_PK");
            }
        }
        // END   MOD 08/26/2013 Def#1767

        return null;
    }

    /**
     * ITG#397579 getEstrWhArr
     * @param whCdList String[]
     * @return String[]
     */
    private String[] getEstrWhArr(String[] whCdList) {
        String[] estrWhArr = new String[whCdList.length];
        for (int i = 0; i < estrWhArr.length; i++) {
            estrWhArr[i] = whCdList[i] + "*";
        }
        return estrWhArr;
    }

    /**
     * ITG#397579 createWrkForNotCreatedWh
     * @param tMsgParam INVTY_DISCR_RPT_WRKTMsg
     * @param whNotCreated String
     */
    private void createWrkForNotCreatedWh(INVTY_DISCR_RPT_WRKTMsg tMsgParam, String whNotCreated) {

        INVTY_DISCR_RPT_WRKTMsg tMsg = new INVTY_DISCR_RPT_WRKTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.invtyDiscrRptWrkPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_NM_INVTY_DISCR_RPT_WRK_SQ));
        tMsg.invtyDiscrRptCratDt.setValue(tMsgParam.invtyDiscrRptCratDt.getValue());
        tMsg.invtyDiscrRptCratTm.setValue(tMsgParam.invtyDiscrRptCratTm.getValue());
        tMsg.invtyLocCd.setValue(whNotCreated);
        tMsg.firstProdCtrlCd.setValue(tMsgParam.firstProdCtrlCd.getValue());
        tMsg.mdseCd.setValue(tMsgParam.mdseCd.getValue());
        tMsg.locStsCd.setValue(tMsgParam.locStsCd.getValue());
        tMsg.stkStsCd.setValue(tMsgParam.stkStsCd.getValue());
        tMsg.adjInvtyOrdNum.clear();
        tMsg.adjInvtyOrdLineNum.clear();
        tMsg.s21InvtyQty.setValue(BigDecimal.ZERO);
        tMsg.relnSysInvtyQty.clear();
        tMsg.diffInvtyQty.clear();
        tMsg.mdseNm.setValue(tMsgParam.mdseNm.getValue());
        tMsg.invtyDiscrRptPrintFlg.setValue(tMsgParam.invtyDiscrRptPrintFlg.getValue());
        tMsg.adjOrdRefInvtyQty.clear();
        tMsg.physWhCd.setValue(tMsgParam.physWhCd.getValue());

        S21FastTBLAccessor.insert(tMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            errorCount++;
            // end process
            throw new S21AbendException("NLCM0111E", new String[] {"There", "an Error for Inserting INVTY_DISCR_RPT_WRK with code: " + tMsg.getReturnCode() });
        } else {
            rptRecCount++;
        }
    }

    /**
     * ITG#397579 getWhNotCreated
     * @return List<String>
     */
    private List<String> getWhNotCreated(String[] estrWhArr, List listCreated) {

        List<String> whList = new ArrayList<String>();
        for (int i = 0; i < estrWhArr.length; i++) {
            String estrWhCd = estrWhArr[i];
            if (!codeFound(listCreated, estrWhCd)) {
                whList.add(estrWhCd);
            }
        }
        return whList;
    }

    /**
     * ITG#397579 codeFound
     * @return boolean
     */
    private boolean codeFound(List listCreated, String estrWhCd) {
        for (int i = 0; i < listCreated.size(); i++) {
            Map map = (Map) listCreated.get(i);
            String whCdCreated = (String) map.get("INVTY_LOC_CD");
            if (whCdCreated.endsWith("*")) {
                if (estrWhCd.equals(whCdCreated)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * ITG#397579 getEstoreWmsWhInvtyInfo
     * @return List
     */
    private List getEstoreWmsWhInvtyInfo() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("createReportDate", createReportDate);
        ssmParam.put("invtySnapShotDt", ZYPDateUtil.addDays(createReportDate, -1));
        // START DEL 08/26/2013 Def#1767
//        ssmParam.put("eStoreWMSWh", ESTR_WH_TP.ESTORE_WMS_WH);
        // END   DEL 08/26/2013 Def#1767

        S21SsmEZDResult ssmResult = NLCB009001Query.getInstance().getEstoreWmsWhInvtyInfo(ssmParam);
        if (ssmResult.isCodeNormal()) {
            return (List) ssmResult.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * SQL parameter setting for I/F table
     * @param isNLCI0110
     * @return HashMap<String, Object>
     */
    private HashMap<String, Object> setQueryParamRelated(boolean isNLCI0110 , String wmsWhCd) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        // Parameter : Global Company Code
        map.put("glblCmpyCd", glblCmpyCd);
        // Parameter : Interface ID
        map.put("interfaceID", getInterfaceID());
        // Parameter : Transaction ID
        map.put("transactionID", trxId);
        // Parameter : Location Status Code(DC Stock)
        map.put("locStsCd", LOC_STS.DC_STOCK);
        // Parameter : Inventory Order Type Code(Adjustment)
        map.put("invtyOrdTpCd", INVTY_ORD_TP.ADJUSTMENT);
        // Parameter : Inventory Order Status Code(Closed)
        map.put("invtyOrdStsCdClosed", INVTY_ORD_STS.CLOSED);
        // Parameter : Inventory Order Status Code(Cancel)
        map.put("invtyOrdStsCdCancel", INVTY_ORD_STS.CANCEL);
        // Parameter : WH_CD invtyLocCd
        map.put("wmsWhCd", wmsWhCd);
        if (isNLCI0110) {
            // Parameter : Rec ID
            map.put("recID", "2");
        } else {
            map.put("closedDateIf", ZYPDateUtil.DateFormatter(ZYPDateUtil.addDays(createReportDate, -1), "yyyyMMdd", "yyyy/MM/dd"));
        }

        // START DEL 08/26/2013 WDS Def#1767
        // Oce#5 Start =======================================
//        if (!isNLCI0110) {
//
//            //DC Stock(Con) is made into a search condition. 
//            map.put("locStsCdCn", LOC_STS.DC_STOCK_CONSIGNMENT);
//        }
        // Oce#5 End   =======================================
        // END  DEL 08/26/2013 WDS Def#1767

        return map;
    }

    /**
     * SQL parameter setting for INVTY_SNAP_SHOT table
     * @param isNLCI0110
     * @return HashMap<String, String>
     */
    private HashMap<String, Object> setQueryParamS21(boolean isNLCI0110) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        // Parameter : Global Company Code
        map.put("glblCmpyCd", glblCmpyCd);
        // Parameter : Process Date
        map.put("procDate", ZYPDateUtil.addDays(createReportDate, -1));
        // Parameter : Location Status Code(DC Stock)
        map.put("locStsCdDcStk", LOC_STS.DC_STOCK);
        // Parameter : Inventory Order Type Code(Adjustment)
        map.put("invtyOrdTpCd", INVTY_ORD_TP.ADJUSTMENT);
        // Parameter : Inventory Order Status Code(Closed)
        map.put("invtyOrdStsCdClosed", INVTY_ORD_STS.CLOSED);
        // Parameter : Inventory Order Status Code(Cancel)
        map.put("invtyOrdStsCdCancel", INVTY_ORD_STS.CANCEL);

        // Parameter : Location Status Code(Insurance Claim Damaged)
        map.put("locStsCdInsClaimDmg", LOC_STS.INSURANCE_CLAIM_DAMAGED);

        // DEL START 08/26/2013 WDS Def#1767
        // Oce#5 Start =======================================
//
//        if (!isNLCI0110) {
//
//            //DC Stock(Con) is made into a search condition. 
//            map.put("locStsCdDcStkCn", LOC_STS.DC_STOCK_CONSIGNMENT); 
//            map.put("ConsignmentFlg", ZYPConstant.FLG_ON_Y);
//
//        } else {
//
//            // Default
//            map.put("ConsignmentFlg", ZYPConstant.FLG_OFF_N);
//        }
        // Oce#5 End   =======================================
        // DEL END  08/26/2013 WDS Def#1767

        return map;
    }

    /**
     * TMsg parameter setting for INVTY_DISCR_RPT_WRKTMsg
     * @param seqNo INVTY_DISCR_RPT_WRK_PK
     * @param rsRelated ResultSet for I/F table
     * @param rsS21 ResultSet for INVTY_SNAP_SHOT table
     * @return INVTY_DISCR_RPT_WRKTMsg
     * @throws SQLException
     */
    private INVTY_DISCR_RPT_WRKTMsg setInvtyRptWrk(BigDecimal seqNo, ResultSet rsRelated, ResultSet rsS21, String physWhCd) throws SQLException {

        EZDDebugOutput.println(1, NLCB009001 + "( setInvtyRptWrk ) start", this);

        INVTY_DISCR_RPT_WRKTMsg discrTMsg = new INVTY_DISCR_RPT_WRKTMsg();

        discrTMsg.glblCmpyCd.setValue(glblCmpyCd);
        discrTMsg.invtyDiscrRptWrkPk.setValue(seqNo);
        discrTMsg.invtyDiscrRptCratDt.setValue(createReportDate);
        discrTMsg.invtyDiscrRptCratTm.setValue(createReportTime);
        discrTMsg.invtyLocCd.setValue(rsS21.getString(COL_NM_INVTY_LOC_CD));
        discrTMsg.physWhCd.setValue(rsS21.getString(COL_NM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(discrTMsg.firstProdCtrlCd, rsS21.getString(COL_NM_PROD_LINE));
        ZYPEZDItemValueSetter.setValue(discrTMsg.mdseCd, rsS21.getString(COL_NM_MDSE_CD));

        //Start Oce#5 ==================================================================================
        ZYPEZDItemValueSetter.setValue(discrTMsg.locStsCd, rsS21.getString(COL_NM_LOC_STS_CD));
        //End ==========================================================================================

        ZYPEZDItemValueSetter.setValue(discrTMsg.stkStsCd, rsS21.getString(COL_NM_STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(discrTMsg.adjInvtyOrdNum, rsS21.getString(COL_NM_INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(discrTMsg.adjInvtyOrdLineNum, rsS21.getString(COL_NM_INVTY_ORD_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(discrTMsg.s21InvtyQty, rsS21.getBigDecimal(COL_NM_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(discrTMsg.relnSysInvtyQty, rsRelated.getBigDecimal(COL_NM_INVTY_QTY));
        discrTMsg.diffInvtyQty.setValue(checkNull(rsS21.getBigDecimal(COL_NM_INVTY_QTY)).subtract(checkNull(rsRelated.getBigDecimal(COL_NM_INVTY_QTY))));
        ZYPEZDItemValueSetter.setValue(discrTMsg.adjOrdRefInvtyQty, reverseSign(rsS21.getBigDecimal(COL_NM_ORD_QTY)));
        ZYPEZDItemValueSetter.setValue(discrTMsg.mdseNm, rsS21.getString(COL_NM_MDSE_NM));
        discrTMsg.invtyDiscrRptPrintFlg.setValue(ZYPConstant.FLG_OFF_N);
        discrTMsg.physWhCd.setValue(physWhCd);

        EZDDebugOutput.println(1, NLCB009001 + "( setInvtyRptWrk ) end", this);

        return discrTMsg;
    }

    /**
     * TMsg parameter setting for INVTY_DISCR_RPT_WRKTMsg
     * @param seqNo INVTY_DISCR_RPT_WRK_PK
     * @param rs ResultSet for I/F table or ResultSet for
     * INVTY_SNAP_SHOT table
     * @param interfaceFlg I/F flag (true : is I/F)
     * @return INVTY_DISCR_RPT_WRKTMsg
     * @throws SQLException
     */
    private INVTY_DISCR_RPT_WRKTMsg setInvtyRptWrk(BigDecimal seqNo, ResultSet rs, boolean interfaceFlg, String physWhCd) throws SQLException {

        EZDDebugOutput.println(1, NLCB009001 + "( setInvtyRptWrk ) start", this);

        INVTY_DISCR_RPT_WRKTMsg discrTMsg = new INVTY_DISCR_RPT_WRKTMsg();

        discrTMsg.glblCmpyCd.setValue(glblCmpyCd);
        discrTMsg.invtyDiscrRptWrkPk.setValue(seqNo);
        ZYPEZDItemValueSetter.setValue(discrTMsg.invtyDiscrRptCratDt, createReportDate);
        ZYPEZDItemValueSetter.setValue(discrTMsg.invtyDiscrRptCratTm, createReportTime);
        discrTMsg.invtyLocCd.setValue(rs.getString(COL_NM_INVTY_LOC_CD));
        discrTMsg.physWhCd.setValue(rs.getString(COL_NM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(discrTMsg.firstProdCtrlCd, rs.getString(COL_NM_PROD_LINE));
        ZYPEZDItemValueSetter.setValue(discrTMsg.mdseCd, rs.getString(COL_NM_MDSE_CD));
        discrTMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(discrTMsg.adjInvtyOrdNum, rs.getString(COL_NM_INVTY_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(discrTMsg.adjInvtyOrdLineNum, rs.getString(COL_NM_INVTY_ORD_LINE_NUM));
        if (interfaceFlg) {

            //Start Oce#5 ==================================================================================
            discrTMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
            //End ==========================================================================================

            ZYPEZDItemValueSetter.setValue(discrTMsg.stkStsCd, rs.getString(COL_NM_STK_STS_CD));
            discrTMsg.s21InvtyQty.setValue(BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(discrTMsg.relnSysInvtyQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
            ZYPEZDItemValueSetter.setValue(discrTMsg.diffInvtyQty, rs.getBigDecimal(COL_NM_INVTY_QTY).negate());
        } else {

            //Start Oce#5 ==================================================================================
            ZYPEZDItemValueSetter.setValue(discrTMsg.locStsCd, rs.getString(COL_NM_LOC_STS_CD));
            //End ==========================================================================================

            ZYPEZDItemValueSetter.setValue(discrTMsg.stkStsCd, rs.getString(COL_NM_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(discrTMsg.s21InvtyQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
            discrTMsg.relnSysInvtyQty.setValue(BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(discrTMsg.diffInvtyQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
        }
        ZYPEZDItemValueSetter.setValue(discrTMsg.adjOrdRefInvtyQty, reverseSign(rs.getBigDecimal(COL_NM_ORD_QTY)));
        ZYPEZDItemValueSetter.setValue(discrTMsg.mdseNm, rs.getString(COL_NM_MDSE_NM));
        discrTMsg.invtyDiscrRptPrintFlg.setValue(ZYPConstant.FLG_OFF_N);
        discrTMsg.physWhCd.setValue(physWhCd);

        EZDDebugOutput.println(1, NLCB009001 + "( setInvtyRptWrk ) end", this);

        return discrTMsg;
    }

    // START 2014/06/12 [WDS-NA#401, ADD]
    private void callReportWithEMail(String locCd, boolean isExistDiscrepancy) {

        S21Mail mail = new S21Mail(glblCmpyCd);
        // Getting of mail address (FROM)
        S21MailGroup mailGroup = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);
        mailGroup.setMailKey1(MAIL_FROM_KEY_1);
        List<S21MailAddress> mailFromList = mailGroup.getMailAddress();
        if (mailFromList.isEmpty()) {
            errorCount++;
            throw new S21AbendException(NLCM0079E);
        }
        mail.setFromAddress(mailFromList.get(0));

        // Getting of mail address (TO)
        mailGroup = new S21MailGroup(glblCmpyCd, MAIL_TO_GROUP_ID);
        mailGroup.setMailKey1(locCd);
        List<S21MailAddress> mailToList = mailGroup.getMailAddress();
        if (mailToList.isEmpty()) {
            errorCount++;
            throw new S21AbendException(NLCM0079E);
        }
        mail.setToAddressList(mailToList);

        // Getting of mail template
        S21MailTemplate mailTemplate = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!ZYPCommonFunc.hasValue(mailTemplate.getSubject())) {
            errorCount++;
            throw new S21AbendException(NLCM0079E);
        }
        mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_WH_CD, locCd);
        mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_RPT_ID, getReportId());
        mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_DATE, ZYPDateUtil.convertFormat(createReportDate, "yyyyMMdd", "MMddyyyy", '/'));
        mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_TIME, convertReportTime(createReportTime));

        String mailBodyTxt = ZYPCodeDataUtil.getVarCharConstValue(NLCB0090_ML_BODY_TXT_EN, glblCmpyCd);
        String[] mailBodyTxtAry = null;
        if (ZYPCommonFunc.hasValue(mailBodyTxt)) {
            mailBodyTxtAry = mailBodyTxt.split(VAL_DELIMITER);
        }
        if (mailBodyTxtAry != null && mailBodyTxtAry.length > 2) {
            if (isExistDiscrepancy) {
                mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_TITLE, mailBodyTxtAry[0] + "\n");

                //SET BODY
                mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_BODY, crtBodyTxtOnEMail(locCd));

            } else {
                mailTemplate.setTemplateParameter(MAIL_TMPL_PRM_TITLE, HALF_SPACE_44 + mailBodyTxtAry[1] + locCd + HALF_SPACE_2 + mailBodyTxtAry[2]);
            }
        }
        // Set mail template
        mail.setMailTemplate(mailTemplate);

        // set mail database
        mail.postMail();
    }


    /**
     * Create Body Text On E-Mail
     * @param locCd
     * @return
     */
    private String crtBodyTxtOnEMail(String locCd) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer bobyTxt = new StringBuffer();

        try {

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(RPT_SQL_KEY_GLBL_CMPY_CODE, glblCmpyCd);
            queryParam.put(RPT_SQL_KEY_INVTY_LOC_CD, locCd);
            queryParam.put(RPT_SQL_KEY_INVTY_DISCR_RPT_CRT_DATE, createReportDate);
            queryParam.put(RPT_SQL_KEY_INVTY_DISCR_RPT_CRT_TM, createReportTime);
            stmt = this.ssmLLCodingClient.createPreparedStatement("getInvtyDiscrRptWrkForSendEMail", queryParam, ssmExecParam);

            rs = stmt.executeQuery();

            while (rs.next()) {
                bobyTxt.append(convFixedLgLeftAlign("", 2));
                bobyTxt.append(convFixedLgLeftAlign(rs.getString(COL_NM_RPT_WH_CD), 6));
                bobyTxt.append(convFixedLgLeftAlign("", 2));
                bobyTxt.append(convFixedLgLeftAlign(rs.getString(COL_NM_RPT_PROD_CTRL_CD), 2));
                bobyTxt.append(convFixedLgLeftAlign("", 2));
                bobyTxt.append(convFixedLgLeftAlign(rs.getString(COL_NM_RPT_MDSE_CD), 16));
                bobyTxt.append(convFixedLgLeftAlign(rs.getString(COL_NM_RPT_MDSE_NM), 26));
                bobyTxt.append(convFixedLgLeftAlign("", 2));
                bobyTxt.append(convFixedLgLeftAlign(rs.getString(COL_NM_RPT_STK_STS_CD), 1));
                bobyTxt.append(convFixedLgLeftAlign("", 3));
                bobyTxt.append(convFixedLgRightAlign(rs.getString(COL_NM_RPT_INVTY_QTY), 14));
                bobyTxt.append(convFixedLgRightAlign(rs.getString(COL_NM_RPT_RELN_SYS_INVTY_QTY), 14));
                bobyTxt.append(convFixedLgLeftAlign("", 1));
                bobyTxt.append(convFixedLgRightAlign(rs.getString(COL_NM_RPT_DIFF_INVTY_QTY), 14));
                bobyTxt.append(convFixedLgLeftAlign("", 2));
                bobyTxt.append(convFixedLgLeftAlign(rs.getString(COL_NM_RPT_ADJ_INVTY_ORD_NUM), 10));
                bobyTxt.append(convFixedLgLeftAlign("", 1));
                bobyTxt.append(convFixedLgRightAlign(rs.getString(COL_NM_RPT_ADJ_ORD_REF_INVTY_QTY), 15));
                bobyTxt.append("\n");
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        return bobyTxt.toString();

    }

    /**
     * Convert fixed length left align
     * @param str String
     * @param lg FixedLength
     * @return String
     */
    private String convFixedLgLeftAlign(final String str, final int lg) {

        return ZYPCommonFunc.rightPad(str, lg, " ");
    }

    /**
     * Convert fixed length left align
     * @param str String
     * @param lg FixedLength
     * @return String
     */
    private String convFixedLgRightAlign(final String str, final int lg) {

        return ZYPCommonFunc.leftPad(str, lg, " ");
    }

    // END   2014/06/12 [WDS-NA#401, ADD]

    /**
     * INVTY_DISCR_RPT_WRK table is update (INVTY_DISCR_RPT_PRINT_FLG)
     * @param physWhCd INVTY_LOC_CD
     */
    private void updatePrintFlg(String physWhCd) {

        EZDDebugOutput.println(1, NLCB009001 + "( updatePrintFlg ) start", this);

        // S21SsmExecutionParameter ssmExecParam = new
        // S21SsmExecutionParameter();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Map<String, String> queryParam = new HashMap<String, String>();
            // Parameter : Interface ID
            queryParam.put("glblCmpyCd", glblCmpyCd);
            // Parameter : Create Report Date
            queryParam.put("createRptDt", createReportDate);
            // Parameter : Create Report Time
            queryParam.put("createRptTm", createReportTime);
            // Parameter : Warehouse Code
            queryParam.put("physWhCd", physWhCd);

            stmt = this.ssmLLCodingClient.createPreparedStatement("getPrinted", queryParam, ssmExecParam);

            rs = stmt.executeQuery();

            while (rs.next()) {

                INVTY_DISCR_RPT_WRKTMsg discrRptWrkTMsg = new INVTY_DISCR_RPT_WRKTMsg();

                discrRptWrkTMsg.glblCmpyCd.setValue(rs.getString(COL_NM_GLBL_CMPY_CD));
                discrRptWrkTMsg.invtyDiscrRptWrkPk.setValue(rs.getBigDecimal(COL_NM_INVTY_DISCR_RPT_WRK_PK));
                discrRptWrkTMsg.invtyDiscrRptCratDt.setValue(rs.getString(COL_NM_INVTY_DISCR_RPT_CRAT_DT));
                discrRptWrkTMsg.invtyDiscrRptCratTm.setValue(rs.getString(COL_NM_INVTY_DISCR_RPT_CRAT_TM));
                discrRptWrkTMsg.invtyLocCd.setValue(rs.getString(COL_NM_INVTY_LOC_CD));
                discrRptWrkTMsg.physWhCd.setValue(physWhCd);
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.firstProdCtrlCd, rs.getString(COL_NM_PROD_LINE));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.mdseCd, rs.getString(COL_NM_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.locStsCd, rs.getString(COL_NM_LOC_STS_CD));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.stkStsCd, rs.getString(COL_NM_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.adjInvtyOrdNum, rs.getString(COL_NM_INVTY_ORD_NUM));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.adjInvtyOrdLineNum, rs.getString(COL_NM_INVTY_ORD_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.s21InvtyQty, rs.getBigDecimal(COL_NM_INVTY_QTY));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.relnSysInvtyQty, rs.getBigDecimal(COL_NM_RELN_QTY));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.diffInvtyQty, rs.getBigDecimal(COL_NM_DIFF_QTY));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.adjOrdRefInvtyQty, rs.getBigDecimal(COL_NM_ORD_QTY));
                ZYPEZDItemValueSetter.setValue(discrRptWrkTMsg.mdseNm, rs.getString(COL_NM_MDSE_NM));
                discrRptWrkTMsg.invtyDiscrRptPrintFlg.setValue(ZYPConstant.FLG_ON_Y);

                S21FastTBLAccessor.update(discrRptWrkTMsg);

                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(discrRptWrkTMsg.getReturnCode())) {
                    errorCount++;
                    // When terminating abnormally,
                    // processing is ended
                    String[] tmp = {TBL_ID_INVTY_DISCR_RPT_WRK, "update", "GLBL_CMPY_CD =" + rs.getString(COL_NM_GLBL_CMPY_CD) + ", INVTY_DISCR_RPT_WRK_SQ =" + rs.getString(COL_NM_INVTY_DISCR_RPT_WRK_PK) };
                    throw new S21AbendException(NLCM0050E, tmp);
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
        EZDDebugOutput.println(1, NLCB009001 + "( updatePrintFlg ) end", this);
    }

    /**
     * INVTY_DISCR_RPT_WRK_SQ getting
     * @return sequence number
     */
    private BigDecimal getSequenceNumber() {
        BigDecimal seqNo = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_NM_INVTY_DISCR_RPT_WRK_SQ);
        if (seqNo == null) {
            // When the sequence is not obtained,
            // processing is ended
            String[] tmp = {"INVTY_DISCR_RPT_WRK_SQ", "ZYPOracleSeqAccessor.getNumberBigDecimal", "INVTY_DISCR_RPT_WRK_SQ" };
            throw new S21AbendException(NLCM0050E, tmp);
        }
        return seqNo;
    }

    /**
     * HIGH-VALUE getting for compare
     * @return HIGH-VALUE
     */
    private String getStringHighValue() {
        char[] highValue = new char[keyWhCodeSize + keyMdseCdSize + keyStkStsSize];
        for (int i = 0; i < highValue.length; i++) {
            highValue[i] = Character.MAX_VALUE;
        }
        return new String(highValue);
    }

    private String checkNull(String val) {
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    private BigDecimal checkNull(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        } else {
            return val;
        }
    }

    /**
     * When it is not NULL, the sign is reversed
     * @param target target
     * @return Conversion result
     */
    private BigDecimal reverseSign(BigDecimal target) {
        if (target == null) {
            return target;
        } else {
            return target.negate();
        }
    }

    /**
     * getTargetWH_CD
     * @param isNLCI0110 boolean
     * @return TargetWH from VarCharConst String[]
     */
    private String[] getTargetWH_CD(boolean isNLCI0110) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        if (isNLCI0110) {
            ssmParam.put("varCharConstNm", NLCI0110_WH_CD);
        } else {
            ssmParam.put("varCharConstNm", NLCI0050_WH_CD);
        }

        String whCds = NLCB009001Query.getInstance().getVarCharConstVal(ssmParam);
        if (ZYPCommonFunc.hasValue(whCds)) {
            String[] whCdArr = whCds.split(VAL_DELIMITER);
            return whCdArr;
        } else {
            return null;
        }
    }

    /**
     * Report Time is converted "HHmmss" to "HH:mm"
     * @param target target
     * @return Conversion result
     */
    private String convertReportTime(String target) {
        if (target != null && target.length() == DIGIT_REPORT_TIME) {
            return target.substring(0, 2) + ":" + target.substring(2, DIGIT_REPORT_TIME_DISP);
        } else {
            return target;
        }
    }

    /**
     * errMailSend
     */
    private void errMailSend() {

        NLXMailSend mailSender = new NLXMailSend(glblCmpyCd);

        if (!errorList.isEmpty()) {
            mailSender.send(NLCB009001, errorList);
        }
    }
}
