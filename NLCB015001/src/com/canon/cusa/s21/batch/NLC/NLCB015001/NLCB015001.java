package com.canon.cusa.s21.batch.NLC.NLCB015001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.INTFC_SLS_MTH_MGTTMsg;
import business.db.STK_OUT_RSLT_WRKTMsg;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * SCM/DB Daily Stock-Out Result To Canon,Inc.
 * Create Work Data.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2010   Fujitsu         S.Yoshida       Create          N/A
 * 03/11/2010   Fujitsu         S.Yoshida       Update          Def.4694
 * 03/19/2010   Fujitsu         S.Yoshida       Update          Def.5007
 * 02/11/2011   Fujitsu         M.Yamada        Update          intangible case missing
 * 09/04/2013   Fujitsu         Y.Taoka         Update          MEX-IF021
 * 10/17/2013   Fujitsu         S.Yoshida       Update          Def.2719
 * 2013/10/13   Fujitsu         C.Naito         Update          Def.2928
 * 2013/11/21   Fujitsu         Y.Taoka         Update          Def.3151
 * 12/10/2013   Fujitsu         T.Nakamura      Update          Def.2957
 * 12/17/2013   CSAI            K.Lee           Update          Def.3151
 * 07/01/2016   CITS            T.Kikuhara      Update          Ver2.0
 * 02/23/2018   CITS            S.Katsuma       Update          QC#24280
 * </pre>
 */
public class NLCB015001 extends S21BatchMain {

    //-- Error Message Code --------------------
    /** Message ID : ZZM9000E The field of [@] is not input. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";
    /** The value which is not numerical was input to the field of [@]. */
    private static final String MSG_ID_ZZM9004E = "ZZM9004E";
    /** Message ID : ZZBM0009I */
    private static final String MSG_ID_ZZBM0009I = "ZZBM0009I";
    /** Message ID : NLCM0053E The process abended. */
    private static final String MSG_ID_NLCM0053E = "NLCM0053E";
    // START ADD 2013/11/21 Def.3151
    /** Message ID : ZZZM9025E [@] is mandatory. */
    private static final String MSG_ID_ZZZM9025E = "ZZZM9025E";
    /** Message ID : NLCM0065E Entered @ is invalid. */
    private static final String MSG_ID_NLCM0065E = "NLCM0065E";
    // END   ADD 2013/11/21 Def.3151
    // START ADD 2013/12/17 Def.3151
    /** Message ID : NLCM0132E Interface Sales Month Managemnet data is incorrect. */
    private static final String MSG_ID_NLCM0132E = "NLCM0132E";
    /** Message ID : NLCM0133E Service Machine Master Snap Shot data cannot be retrieved. */
    private static final String MSG_ID_NLCM0133E = "NLCM0133E";
    // END ADD 2013/12/17 Def.3151

    //-- Error Message Parameter --------------------
    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";
    /** Message string : Commit Count */
    private static final String MSG_STR_PARAM_01 = "Commit Count(VAR_USER1)";
    /** Table ID : INVTY_TRX */
    private static final String TBL_ID_INVTY_TRX = "INVTY_TRX";
    /** Table ID : STK_IN_RSLT_WRK */
    private static final String TBL_ID_STK_OUT_RSLT_WRK = "STK_OUT_RSLT_WRK";
    // ADD 2013/11/21 Def.3151
    /** Message string : MDSE_INCL_TECH_INVTY_CINC_FLG */
    private static final String MSG_STR_VAR_CHAR_CONST = "Technician Inventory included Flag in SCMDB IF(VAR_CHAR_CONST)";

    //-- Other Internal constants --------------
    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;
    /** Fetch Size */
    private static final int FETCH_SIZE = 1000;
    // ADD 2013/11/21 Def.3151
    /** VARCHAR CONST Name */
    private static final String VAR_CHAR_NM_MDSE_INCL_TECH_INVTY_CINC_FLG = "MDSE_INCL_TECH_INVTY_CINC_FLG";

    //-- Dummy Value constants --------------
    /** Dummy Value : WH_CD */
    private static final String DUMMY_WH_CD = "ZZ";
    // ADD 2013/11/21 Def.3151
    /** Dummy Value : TRX_RSN_CD Rental Shipment out (It also uses in NLCB015001. )*/
    private static final String TRX_RNTL_SHIP_OUT_DUMMY = "99";
    // START 2016/07/01 T.Kikuhara V2.0
    // START 2018/02/23 S.Katsuma [MOD,QC#24280]
//    private static final String REMAN_ITEM_CHANGE_COMP_STOCK_IN = "05";
//    private static final String REMAN_ITEM_CHANGE_COMP_STOCK_OUT = "06";
    private static final String REMAN_ITEM_CHANGE_COMP_STOCK_IN = "06";
    private static final String REMAN_ITEM_CHANGE_COMP_STOCK_OUT = "05";
    // END 2018/02/23 S.Katsuma [MOD,QC#24280]
    // END 2016/07/01 T.Kikuhara V2.0

    //-- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;
    /** Commit Count */
    private int commitCount = 0;

    //-- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;
    /** The number of cases : Insert */
    private int insertCount;
    /** The number of case : Skip */
    private int skipCount;

    //-- Other Internal Variable ---------------
    /** User Profile Service  */
    private S21UserProfileService profileService = null;
    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    // START ADD 2013/12/17 Def.3151
    /** SSM Client */
    private S21SsmBatchClient ssmBatchClient = null;
    // END ADD 2013/12/17 Def.3151
    /** Termination code */
    private TERM_CD termCd;
    /** Batch Proc Date */
    private String batProcDate;
    // START ADD 2013/12/17 Def.3151
    /** Service Machine Master Snap Shot Compare Date */
    private String compDate;
    // END ADD 2013/12/17 Def.3151
    // ADD 2013/11/21 Def.3151
    /** MDSE Include Technician Inventory CanonINC Flag */
    private String mdseInclTechInvtyCincFlg;

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // START ADD 2013/12/17 Def.3151
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END ADD 2013/12/17 Def.3151
        // Initialization of variable
        selectCount = 0;
        insertCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Check on input parameter
        checkParameter();
        // START ADD 2013/12/17 Def.3151
        // Get operation date.
        batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        checkIntfcSlsMthMgt();

        // START 2016/07/01 T.Kikuhara V2.0
        //checkSvcMachMstrSnapShot();
        // END 2016/07/01 T.Kikuhara V2.0

        // END ADD 2013/12/17 Def.3151
    }

    @Override
    protected void mainRoutine() {

        // Delete work data Rental to Sales and Loan to Sales
        deleteStkOutRsltWrk();

        // Create work date.
        createStkOutRsltWrk();

        // Create work date.
        createStkOutRsltWrkFrmInv();

        // START 2016/07/01 T.Kikuhara V2.0
        // Create work date.
        //createStkOutRsltWrkFrmMMSS();
        // END 2016/07/01 T.Kikuhara V2.0

    }

    @Override
    protected void termRoutine() {

        // The number of cases : Select is output
        String[] tmp1 = {TBL_ID_INVTY_TRX, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp1);

        // The number of cases : Insert is output
        String[] tmp2 = {TBL_ID_STK_OUT_RSLT_WRK, "Insert", Integer.toString(insertCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp2);

        // Setting of termination code
        setTermState(termCd, selectCount, skipCount, insertCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[]{MSG_STR_COMP_CODE});
        }

        // Commit Count
        String str = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_PARAM_01});
        }
        try {
            commitCount = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {"Commit Count(" + str + ")"});
        }

        // START ADD 2013/11/21 Def.3151
        // Get MDSE Include Technician Inventory CanonINC Flag
        mdseInclTechInvtyCincFlg = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_NM_MDSE_INCL_TECH_INVTY_CINC_FLG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(mdseInclTechInvtyCincFlg)) {
            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[]{MSG_STR_VAR_CHAR_CONST});
        } else if (!(ZYPConstant.FLG_ON_Y.equals(mdseInclTechInvtyCincFlg) || ZYPConstant.FLG_OFF_N.equals(mdseInclTechInvtyCincFlg))) {
            throw new S21AbendException(MSG_ID_NLCM0065E, new String[]{"\"" + MSG_STR_VAR_CHAR_CONST + "\""});
        }
        // END   ADD 2013/11/21 Def.3151
    }

    // START ADD 2013/12/17 Def.3151
    /**
     * <pre>
     * Check INTFC_SLS_MTH_MGT
     * </pre>
     */
    private void checkSvcMachMstrSnapShot() {

        String procDate = getServiceMachineMasterSnapShotProcDate();
        if (!ZYPCommonFunc.hasValue(procDate)) {
            throw new S21AbendException(MSG_ID_NLCM0133E, new String[]{});
        }

        compDate = getServiceMachineMasterSnapShotCompareDate();
        if (!ZYPCommonFunc.hasValue(compDate)) {
            throw new S21AbendException(MSG_ID_NLCM0133E, new String[]{});
        }
    }

    /**
     * <pre>
     * Check INTFC_SLS_MTH_MGT
     * </pre>
     */
    private void checkIntfcSlsMthMgt() {

        INTFC_SLS_MTH_MGTTMsg inMsg = new INTFC_SLS_MTH_MGTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        INTFC_SLS_MTH_MGTTMsg outMsg = (INTFC_SLS_MTH_MGTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            throw new S21AbendException(MSG_ID_NLCM0132E, new String[]{});
        }

        if (!ZYPCommonFunc.hasValue(outMsg.intfcSlsYrMth)) {
            throw new S21AbendException(MSG_ID_NLCM0132E, new String[]{});
        }

        if (!ZYPCommonFunc.hasValue(outMsg.procExecDt)) {
            throw new S21AbendException(MSG_ID_NLCM0132E, new String[]{});
        }
    }
    // END ADD 2013/12/17 Def.3151

    /**
     * <pre>
     * Create Stock out Result Work. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void createStkOutRsltWrk() {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            List<STK_OUT_RSLT_WRKTMsg> stkOutRsltWrkTMsgList = new ArrayList<STK_OUT_RSLT_WRKTMsg>();

            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            prdStmt = ssmLLClient.createPreparedStatement("findInvtyTrx", getFindInvtyTrxParam(), execParam);
            rs = insertStkOutRsltWrk(prdStmt, stkOutRsltWrkTMsgList);

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * @param prdStmt
     * @param stkOutRsltWrkTMsgList
     * @return
     * @throws SQLException
     */
    private ResultSet insertStkOutRsltWrk(PreparedStatement prdStmt, List<STK_OUT_RSLT_WRKTMsg> stkOutRsltWrkTMsgList) throws SQLException {

        ResultSet rs;
        STK_OUT_RSLT_WRKTMsg stkOutRsltWrkTMsg;
        rs = prdStmt.executeQuery();

        int size = 0;
        int execCnt = 0;
//        StringBuffer logMsg = null;
        String invtyTrxPk = null;

        while (rs.next()) {
            selectCount++;

            // Copy Result Set to TMsg
            stkOutRsltWrkTMsg = new STK_OUT_RSLT_WRKTMsg();
            S21ResultSetMapper.map(rs, stkOutRsltWrkTMsg);

            invtyTrxPk = String.valueOf(stkOutRsltWrkTMsg.invtyTrxPk.getValue());
            // START 2016/07/01 T.Kikuhara V2.0
//            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, invtyTrxPk, true);
            // END 2016/07/01 T.Kikuhara V2.0

            // START DEL 09/04/2013 MEX-IF021
            // Check Stock Point Code.
//            if (!checkStkPnt(stkOutRsltWrkTMsg)) {
//                logMsg = new StringBuffer();
//                logMsg.append("Stock Point is Null. Skip INVTY_TRX_PK=");
//                logMsg.append(invtyTrxPk);
//                logMsg.append(".");
//                S21InfoLogOutput.println(logMsg.toString());
//                skipCount++;
//                continue;
//            }
//
//            // Set Default warehouse
//            if (!setDefWHCd(rs, stkOutRsltWrkTMsg)) {
//                continue;
//            }
            // END   DEL 09/04/2013 MEX-IF021

            stkOutRsltWrkTMsgList.add(stkOutRsltWrkTMsg);

            // commit
            size = stkOutRsltWrkTMsgList.size();
            if (size == commitCount) {
                execCnt = S21FastTBLAccessor.insert(
                        (STK_OUT_RSLT_WRKTMsg[]) stkOutRsltWrkTMsgList.toArray(new STK_OUT_RSLT_WRKTMsg[0]));
                if (execCnt != size) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                insertCount += size;
                stkOutRsltWrkTMsgList.clear();
            }
        }
        size = stkOutRsltWrkTMsgList.size();
        if (size > 0) {
            execCnt = S21FastTBLAccessor.insert(
                    (STK_OUT_RSLT_WRKTMsg[]) stkOutRsltWrkTMsgList.toArray(new STK_OUT_RSLT_WRKTMsg[0]));
            if (execCnt != size) {
                throw new S21AbendException(MSG_ID_NLCM0053E);
            }
            commit();
            insertCount += size;
        }
        return rs;
    }

    /**
     * <pre>
     * Create Stock out Result Work From Invoice (Rental To Sales, Loan To Sales). 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void createStkOutRsltWrkFrmInv() {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            List<STK_OUT_RSLT_WRKTMsg> stkOutRsltWrkTMsgList = new ArrayList<STK_OUT_RSLT_WRKTMsg>();

            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            prdStmt = ssmLLClient.createPreparedStatement("findRentalToSales", getFindRentalToSalesParam(), execParam);
            rs = insertStkOutRsltWrk(prdStmt, stkOutRsltWrkTMsgList);

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }
    //START ADD 2013/10/30 Def.2928
    /**
     * <pre>
     * Create Rental_Instal_Qty From MachineMasterSnapShot. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void createStkOutRsltWrkFrmMMSS() {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            List<STK_OUT_RSLT_WRKTMsg> stkOutRsltWrkTMsgList = new ArrayList<STK_OUT_RSLT_WRKTMsg>();

            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            prdStmt = ssmLLClient.createPreparedStatement("findRentalInstalQty", getFindRentalInstalQtyParam(), execParam);
            rs = insertStkOutRsltWrk(prdStmt, stkOutRsltWrkTMsgList);

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }
    //END   ADD 2013/10/30 Def.2928

    /**
     * <pre>
     * delete Stock out Result Work Rental to Sales data. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void deleteStkOutRsltWrk() {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            STK_OUT_RSLT_WRKTMsg stkOutRsltWrkTMsg = null;

            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            Map<String, String> param = new HashMap<String, String>();
            param.put("GLBL_CMPY_CD", glblCmpyCd);
            param.put("TRX_SALES", TRX.SALES);
            param.put("TRX_RSN_RENTAL_TO_SALES", TRX_RSN.RENTAL_TO_SALES);
            param.put("TRX_RSN_LOAN_TO_SALES", TRX_RSN.LOAN_TO_SALES); // START ADD 09/04/2013 MEX-IF021

            // Execute search for delete query.
            prdStmt = ssmLLClient.createPreparedStatement("findDeleteWrk", param, execParam);
            rs = prdStmt.executeQuery();

            while (rs.next()) {
                selectCount++;

                // Copy Result Set to TMsg
                stkOutRsltWrkTMsg = new STK_OUT_RSLT_WRKTMsg();
                S21ResultSetMapper.map(rs, stkOutRsltWrkTMsg);

                EZDTBLAccessor.remove(stkOutRsltWrkTMsg);

            }
            commit();

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * <pre>
     * Set the find for Inventory Transaction parameters.
     * </pre>
     * @return Find for IInventory Transaction parameters.
     */
    private Map<String, String> getFindInvtyTrxParam() {
        Map<String, String> param = new HashMap<String, String>();

        param.put("GLBL_CMPY_CD", glblCmpyCd);
        param.put("PROC_DATE", batProcDate);

        // Stock Status Code
        param.put("SS_GOOD", STK_STS.GOOD);

        // Location Status Code
        param.put("IN_SHED", LOC_STS.IN_SHED);
//        param.put("WTNG_FOR_ISTL", LOC_STS.WAITING_FOR_INSTALLATION);               // DEL 09/04/2013 MEX-IF021

        // Transaction Code
        param.put("T_SLS", TRX.SALES);
        param.put("T_MOVE", TRX.MOVEMENT);
        param.put("T_EXP_SHIP", TRX.EXPENSE_SHIPMENT);
        param.put("T_LOAN_SHIP", TRX.LOAN_SHIPMENT);
        param.put("T_RNTL_SHIP", TRX.RENTAL_SHIPMENT);
        param.put("T_ADJ", TRX.ADJUSTMENT);
        param.put("T_INS_CLM", TRX.INSURANCE_CLAIM);
        param.put("T_PARTS_USG", TRX.PARTS_USAGE);                                    // ADD 2013/11/21 Def.3151
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("T_REMAN", TRX.REMANUFACTURING);
        param.put("T_EMSD_SHIP", TRX.EMSD_SHIPMENT);
        // END 2016/07/01 T.Kikuhara V2.0

        // Transaction Reason Code
        // 010:SALES
        param.put("TR_REG_SLS_WITH_COST", TRX_RSN.REGULAR_SALES_WITH_COST);
        param.put("TR_CR_AND_REBIL", TRX_RSN.CREDIT_AND_REBILL);
        param.put("TR_RTRN", TRX_RSN.RETURN);
//        param.put("TR_LOAN_TO_SLS", TRX_RSN.LOAN_TO_SALES);                          // DEL 09/04/2013 MEX-IF021
        param.put("TR_TRIAL_TO_SLS", TRX_RSN.TRIAL_TO_SALES);
        param.put("TR_REG_SLS_ASSET", TRX_RSN.REGULAR_SALES_ASSET);                    // ADD 09/04/2013 MEX-IF021
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("CASH_MDSE", TRX_RSN.REGULAR_SALES_CASH_LEASE_MDSE);
        param.put("CASH_INI_SUP", TRX_RSN.REGULAR_SALES_CASH_LEASE_INIT_SUP);
        param.put("CASH_SUP", TRX_RSN.REGULAR_SALES_CASH_LEASE_SUP);
        param.put("CASH_CNT_SUP", TRX_RSN.REGULAR_SALES_CASH_LEASE_CNTR_SUP);
        param.put("R_CASH_RMA", TRX_RSN.RETURN);
        // END 2016/07/01 T.Kikuhara V2.0
        // 020:MOVEMENT
//        param.put("TR_WH_TRNSF_STK_OUT", TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);      // DEL 09/04/2013 MEX-IF021
//        param.put("TR_STK_STS_CHNG_STK_OUT", TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT); // DEL 2013/10/30 Def.2928
//        param.put("TR_STK_STS_CHNG_STK_IN", TRX_RSN.STOCK_STATUS_CHANGE_STOCK_IN);   // DEL 2013/10/30 Def.2928
        param.put("TR_TRIAL_SHIP_STK_OUT", TRX_RSN.TRIAL_SHIPMENT_STOCK_OUT);
        param.put("TR_DRCT_SALE_SHIP_STK_OUT", TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT);
        param.put("TR_EXPT_SALE_SHIP_STK_OUT", TRX_RSN.EXPORT_SALE_SHIPMENT_STOCK_OUT);
        param.put("TR_TRIAL_TO_INVTY_STK_IN", TRX_RSN.TRIAL_TO_INVENTORY_STOCK_IN);
        param.put("TR_DRCT_SALE_SHIP_CANC_STK_IN", TRX_RSN.DIRECT_SALE_SHIPPMENT_CANCEL_STOCK_IN);
        param.put("TR_DROP_SHIP_TRIAL_STK_OUT", TRX_RSN.DROP_SHIPMENT_TRIAL_STOCK_OUT);
        param.put("TR_EXPT_TRIAL_STK_OUT", TRX_RSN.EXPORT_TRIAL_STOCK_OUT);
        param.put("TR_TRIAL_STK_OUT_INTG_W_COST", TRX_RSN.TRIAL_STOCK_OUT_INTANGIBLE_WITH_COST);
//        param.put("TR_DRCT_SALE_STK_OUT_INTG_W_COST", TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST); // DEL 09/04/2013 MEX-IF021
        param.put("TR_WH_TRANS_STK_OUT", TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);         // ADD 2013/11/21 Def.3151
        param.put("TR_INTNL_WH_TRANS_STK_IN", TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN);   // ADD 2013/11/21 Def.3151
        // START ADD 2013/12/20 Def.3151
        param.put("TR_DRCT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST", TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST);
        // END ADD 2013/12/20 Def.3151
        // 030:EXPENSE SHIPMENT
        param.put("TR_EXP_SHIP", TRX_RSN.EXPENSE_SHIPMENT);
        param.put("TR_EXP_RTN", TRX_RSN.EXPENSE_RETURN);
        // START ADD 2013/12/20 Def.3151
        param.put("TR_EXP_SHIPMENT_ASSET", TRX_RSN.EXPENSE_SHIPMENT_ASSET);
        // END ADD 2013/12/20 Def.3151
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("EX_LOAN_SHIP_OUT", TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT);
        param.put("EX_LOAN_INVTY_OUT", TRX_RSN.EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT);
        param.put("EX_LOAN_DROP_OUT", TRX_RSN.EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT);
        param.put("EX_LOAN_INTANG_OUT", TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT_INTANGIBLE_WITH_COST);
        // END 2016/07/01 T.Kikuhara V2.0

        // 040:LOAN SHIPMENT
        param.put("TR_LOAN_SHIP_STK_OUT", TRX_RSN.LOAN_SHIPMENT_STOCK_OUT);
        param.put("TR_DROP_SHIP_LOAN_STK_OUT", TRX_RSN.DROP_SHIPMENT_LOAN_STOCK_OUT);
//        param.put("TR_LOAN_TO_ASSET_RETURN", TRX_RSN.LOAN_TO_ASSET_RETURN);               // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.2928
//        param.put("TR_LOAN_SHIP_STK_OUT_ASSET", TRX_RSN.LOAN_SHIPMENT_STOCK_OUT_ASSET);   // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.2928
        // 050:RENTAL SHIPMENT
//        param.put("TR_RNTL_SHIP_STK_OUT", TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT);
//        param.put("TR_RNTL_SHIP_RNTL_STK_OUT", TRX_RSN.DROP_SHIPMENT_RENTAL_STOCK_OUT);   // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.2928
//        param.put("TR_RNTL_TO_ASSET_RETURN", TRX_RSN.RENTAL_TO_ASSET_RETURN);             // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.2928
//        param.put("TR_RNTL_SHIP_STK_OUT_ASSET", TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_ASSET); // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.2928
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("TR_RNTL_SHIP_STK_OUT", TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT);
        param.put("TR_RNTL_SHIP_DROP_STK_OUT", TRX_RSN.DROP_SHIPMENT_RENTAL_STOCK_OUT);
        param.put("TR_RNTL_SHIP_STK_OUT_INTANG", TRX_RSN.RENTAL_STOCK_OUT_INTANGIBLE_WITH_COST);
        param.put("TR_RNTL_TO_ASSET_RETURN", TRX_RSN.RENTAL_TO_ASSET_RETURN);
        param.put("TR_RNTL_SHIP_STK_OUT_EXP", TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_EXPENSE);
        // END 2016/07/01 T.Kikuhara V2.0

        // 060:ADJUSTMENT
        param.put("TR_ADJ", TRX_RSN.ADJUSTMENT);
        param.put("TR_DSPL", TRX_RSN.DISPOSAL);
//        param.put("TR_LOAN_TO_DSPL", TRX_RSN.LOAN_TO_DISPOSAL);                         // DEL 09/04/2013 MEX-IF021
        param.put("TR_ITEM_CHNG_STK_OUT", TRX_RSN.ITEM_CHANGE_STOCK_OUT);
        param.put("TR_ITEM_CHNG_STK_IN", TRX_RSN.ITEM_CHANGE_STOCK_IN);
        param.put("TR_ITEM_CHNG_STK_OUT_FROM_VND", TRX_RSN.ITEM_CHANGE_STOCK_OUT_FROM_VENDOR);
        param.put("TR_ITEM_CHNG_STK_IN_FROM_VND", TRX_RSN.ITEM_CHANGE_STOCK_IN_FROM_VENDOR);
        param.put("TR_ASSET_DSPL", TRX_RSN.ASSET_DISPOSAL);                               // ADD 09/04/2013 MEX-IF021
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("CYCLE_ADJ", TRX_RSN.CYCLE_COUNT_ADJUSTMENT);
        param.put("PI_ADJ", TRX_RSN.PHYSICAL_INVENTORY_ADJUSTMENT);
        param.put("RE_VEND_OUT", TRX_RSN.REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR);
        param.put("RE_EXP_OUT", TRX_RSN.REFURB_EXPENSE_SHIP_OUT);
        param.put("KIT_OUT", TRX_RSN.KITTING_ITEM_CHANGE_STOCK_OUT);
        param.put("KIT_IN", TRX_RSN.KITTING_ITEM_CHANGE_STOCK_IN);
        param.put("WH_TR_OUT", TRX_RSN.WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT);
        // END 2016/07/01 T.Kikuhara V2.0
        // 070:INSURANCE CLAIM
        param.put("TR_LOSS_ON_SHIP", TRX_RSN.LOSS_ON_SHIPMENT);
        param.put("TR_LOSS_ON_RCV", TRX_RSN.LOSS_ON_RECEIVING);
        // 230:PARTS USAGE
        param.put("TR_PARTS_USG", TRX_RSN.PARTS_USAGE);                                   // ADD 2013/11/21 Def.3151
        param.put("TR_PARTS_USG_RTN", TRX_RSN.PARTS_USAGE_RETURN);                        // ADD 2013/11/21 Def.3151
        // START 2016/07/01 T.Kikuhara V2.0
        // 240:REMANUFACTURING
        param.put("REMAN_PART_USE", TRX_RSN.PARTS_USAGE_FOR_REMAN);
        param.put("REMAN_ITEM_OUT", TRX_RSN.REMAN_ITEM_CHANGE_STOCK_OUT);
        param.put("REMAN_ITEM_IN", TRX_RSN.REMAN_ITEM_CHANGE_STOCK_IN);
        param.put("REMAN_PART_RETURN", TRX_RSN.PARTS_USAGE_RETURN_FOR_REMAN);
        param.put("REMAN_COMP_IN", REMAN_ITEM_CHANGE_COMP_STOCK_IN);
        param.put("REMAN_COMP_OUT", REMAN_ITEM_CHANGE_COMP_STOCK_OUT);
        // 310:EMSD_SHIPMENT
        param.put("EMSD_OUT", TRX_RSN.EMSD_SHIPMENT_STOCK_OUT);
        param.put("EMSD_DROP_OUT", TRX_RSN.EMSD_DROP_SHIPMENT_STOCK_OUT);
        param.put("EMSD_OUT_INTG", TRX_RSN.EMSD_STOCK_OUT_INTANGIBLE_WITH_COST);
        param.put("EMSD_OUT_EXP", TRX_RSN.EMSD_SHIPMENT_STOCK_OUT_EXPENSE);
        // END 2016/07/01 T.Kikuhara V2.0

        // START ADD 09/04/2013 MEX-IF021
        param.put("CPO_ORD_TP_RNTL", CPO_ORD_TP.RENTAL_DS);
        // START ADD 2013/12/20 Def.3151
        param.put("CPO_ORD_TP_LOAN", CPO_ORD_TP.LOAN);
        // END ADD 2013/12/20 Def.3151
        param.put("DUMMY_WH_CD", DUMMY_WH_CD);
        param.put("MDSE_RGTN_TP", MDSE_RGTN_TP.MERCURY);
        // END   ADD 09/04/2013 MEX-IF021

        // START ADD 2013/10/30 Def.2928
        param.put("WAREHOUSE", LOC_TP.WAREHOUSE);
// START MOD 12/10/2013 Def.2957
//        param.put("RGRL_STK_WH", LOC_ROLE_TP.RGRL_STK_WH);
        param.put("RGRL_STK_WH", LOC_ROLE_TP.REGULAR_STOCK_WAREHOUSE);
// END   MOD 12/10/2013 Def.2957
        param.put("LOAN", ASSET_TP.LOAN_ASSET);
        param.put("RENTAL", ASSET_TP.RENTAL_ASSET);
        // END   ADD 2013/10/30 Def.2928

        // ADD 2013/11/21 Def.3151
        param.put("MDSE_INCL_TECH_INVTY_CINC_FLG", mdseInclTechInvtyCincFlg);
        param.put("LOC_TP_CD_TECH", LOC_TP.TECHNICIAN);

        return param;
    }

    /**
     * <pre>
     * Set the find for Inventory Transaction parameters.
     * </pre>
     * @return Find for IInventory Transaction parameters.
     */
    private Map<String, String> getFindRentalToSalesParam() {

        Map<String, String> param = new HashMap<String, String>();

        param.put("GLBL_CMPY_CD", glblCmpyCd);
        param.put("PROC_DATE", batProcDate);

        // Stock Status Code
        param.put("SS_GOOD", STK_STS.GOOD);

        param.put("TRX_SALES", TRX.SALES);
        param.put("TRX_RSN_RENTAL_TO_SALES", TRX_RSN.RENTAL_TO_SALES);
        param.put("TRX_SRC_TP_PARTS", TRX_SRC_TP.SERVICE_OR_PARTS);

        // START ADD 09/04/2013 MEX-IF021
        param.put("DUMMY_WH_CD", DUMMY_WH_CD);
        param.put("MDSE_RGTN_TP", MDSE_RGTN_TP.MERCURY);
        // END   ADD 09/04/2013 MEX-IF021

        // START ADD 10/17/2013 [Def.2719]
        param.put("CPO_TP_LOAN_TO_SALES", CPO_ORD_TP.LOAN_TO_SALES);
        param.put("CPO_TP_RENTAL_TO_SALES", CPO_ORD_TP.RENTAL_TO_SALES);
        // END   ADD 10/17/2013 [Def.2719]

        return param;
    }

   //START ADD 2013/10/30 Def.2928
    /**
     * <pre>
     * Set the find for Machine Master Snap Shot parameters.
     * </pre>
     * @return Find for Machine Master Snap Shot.
     */
    private Map<String, String> getFindRentalInstalQtyParam() {

        Map<String, String> param = new HashMap<String, String>();

        param.put("GLBL_CMPY_CD", glblCmpyCd);
        param.put("PROC_DATE", batProcDate);
        param.put("MDSE_RGTN_TP", MDSE_RGTN_TP.MERCURY);
        param.put("CPO_ORD_TP", CPO_ORD_TP.RENTAL_DS);
        param.put("DUMMY_WH_CD", DUMMY_WH_CD);
// START MOD 12/10/2013 Def.2957
//        param.put("RGRL_STK_WH", LOC_ROLE_TP.RGRL_STK_WH);
        param.put("RGRL_STK_WH", LOC_ROLE_TP.REGULAR_STOCK_WAREHOUSE);
// END   MOD 12/10/2013 Def.2957

        // START ADD 2013/11/21 Def.3151
        param.put("T_RNTL_SHIP", TRX.RENTAL_SHIPMENT);
        param.put("TR_RNTL_SHIP_OUT_DUMMY", TRX_RNTL_SHIP_OUT_DUMMY);
        // END   ADD 2013/11/21 Def.3151
        // START ADD 2013/12/17 Def.3151
        param.put("COMP_DATE", compDate);
        // END ADD 2013/12/17 Def.3151
        return param;
    }
    //END   ADD 2013/10/30 Def.2928


// START DEL 09/04/2013 MEX-IF021
//    /**
//     * <pre>
//     * Check Stock Point Code.
//     * </pre>
//     * 
//     * @param stkOutRsltWrkTMsg STK_OUT_RSLT_WRKTMsg
//     * @return Check result.
//     */
//    private boolean checkStkPnt(STK_OUT_RSLT_WRKTMsg stkOutRsltWrkTMsg) {
//
//        String trxCd = stkOutRsltWrkTMsg.trxCd.getValue();
//        String trxRsnCd = stkOutRsltWrkTMsg.trxRsnCd.getValue();
//
//        if (TRX.ADJUSTMENT.equals(trxCd)
//                && TRX_RSN.ITEM_CHANGE_STOCK_OUT_FROM_VENDOR.equals(trxRsnCd)
//                || TRX_RSN.ITEM_CHANGE_STOCK_IN_FROM_VENDOR.equals(trxRsnCd)) {
//            if (!ZYPCommonFunc.hasValue(stkOutRsltWrkTMsg.whCd)) {
//                return false;
//            }
//        }
//
//        return true;
//    }

//    /**
//     * <pre>
//     * Set Default Warehouse Code.
//     * </pre>
//     * @param  rs                ResultSet
//     * @param  stkOutRsltWrkTMsg STK_OUT_RSLT_WRKTMsg
//     * @return result (false:error)
//     * @throws SQLException
//     */
//    private boolean setDefWHCd(ResultSet rs, STK_OUT_RSLT_WRKTMsg stkOutRsltWrkTMsg) throws SQLException {
//
//        NWZC046001 drvDefWHApi = null;
//        NWZC046001PMsg drvDefWHPMsg = null;
//        String defWHPram = null;
//        String[] apiPramList = null;
//        StringBuffer logMsg = null;
//        int size = 0;
//        String msgId = null;
//
//        // Warehouse Transfer Stock Out
//        // Set Default Warehouse Code is Wharehouse Code(Location code). 
//        if (TRX.MOVEMENT.equals(stkOutRsltWrkTMsg.trxCd.getValue())
//                && TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT.equals(stkOutRsltWrkTMsg.trxRsnCd.getValue())) {
//            stkOutRsltWrkTMsg.defWhCd.setValue(stkOutRsltWrkTMsg.whCd.getValue());
//            return true;
//        }
//
//        // IF API_PARAM is Null, not set Default warehouse.
//        defWHPram = rs.getString("API_PARAM");
//        if (!ZYPCommonFunc.hasValue(defWHPram)) {
//            return true;
//        }
//
//        // Check Tangible.
//        if (ZYPConstant.FLG_OFF_N.equals(rs.getString("INVTY_CTRL_FLG"))) {
//            stkOutRsltWrkTMsg.defWhCd.setValue(
//                stkOutRsltWrkTMsg.whCd.getValue());
//            return true;
//        }
//
//        // Execute "Deriving default warehouse API".
//        if (defWHPram.indexOf(",") >= 0) {
//
//            apiPramList = defWHPram.split(",");
//
//            // Set api parameters.
//            drvDefWHPMsg = new NWZC046001PMsg();
//            drvDefWHPMsg.glblCmpyCd.setValue(glblCmpyCd);
//            drvDefWHPMsg.mdseCd.setValue(stkOutRsltWrkTMsg.mdseCd.getValue());
//            drvDefWHPMsg.stkStsCd.setValue(stkOutRsltWrkTMsg.stkStsCd.getValue());
//            drvDefWHPMsg.shipToCustCd.setValue(rs.getString("SHIP_TO_CUST_CD"));
//            drvDefWHPMsg.shipToPostCd.setValue(apiPramList[0]);
//            drvDefWHPMsg.shipToCtryCd.setValue(apiPramList[1]);
//
//            drvDefWHApi = new NWZC046001();
//            drvDefWHApi.execute(drvDefWHPMsg, ONBATCH_TYPE.BATCH);
//
//            // Error case next record.
//            if (S21ApiUtil.isXxMsgId(drvDefWHPMsg)) {
//                logMsg = new StringBuffer();
//                size = drvDefWHPMsg.xxMsgIdList.getValidCount();
//                logMsg.insert(0, "Deriving default warehouse API Message ID=");
//                boolean errFlg = false;
//                for (int i = 0; i < size; i++) {
//                    msgId = drvDefWHPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
//                    if (msgId.endsWith("E")) {
//                        errFlg = true;
//                    }
//                    logMsg.append(msgId);
//                    if (i < size - 1) {
//                        logMsg.append(",");
//                    }
//                }
//                logMsg.append(".");
//                EZDDebugOutput.println(CST_DEBUG_MSG_LVL, logMsg.toString(), this);
//                if (errFlg) {
////TODO Delete skip logic for API error.
////                    logMsg.delete(0, logMsg.length());
////                    logMsg.append("Skip INVTY_TRX_PK=");
////                    logMsg.append(stkOutRsltWrkTMsg.invtyTrxPk.getValue());
////                    logMsg.append(".");
//////                    EZDDebugOutput.println(CST_DEBUG_MSG_LVL, logMsg.toString(), this);
////                    S21InfoLogOutput.println(logMsg.toString());
////                    skipCount++;
////                    return false;
//
//                    // Set Stop Point Code.
//                    stkOutRsltWrkTMsg.defWhCd.setValue(
//                        stkOutRsltWrkTMsg.whCd.getValue());
//                    return true;
//                }
//            }
//
//            // Set the Default Warehouse from api result.
//            size = drvDefWHPMsg.shipFromList.getValidCount();
//            for (int i = 0; i < size; i++) {
//                if (ZYPConstant.FLG_ON_Y.equals(
//                        drvDefWHPMsg.shipFromList.no(i).xxDefShipFromFlg.getValue())) {
//                    stkOutRsltWrkTMsg.defWhCd.setValue(
//                            drvDefWHPMsg.shipFromList.no(i).xxShipFromCd.getValue());
//                    break;
//                }
//            }
//
//            if (!ZYPCommonFunc.hasValue(stkOutRsltWrkTMsg.defWhCd)) {
//
//                // Default warehouse does not exist.
//                if (size == 0) {
////TODO Delete skip logic for API error.
////                    logMsg = new StringBuffer();
////                    logMsg.append("Default warehouse does not exist.  Skip INVTY_TRX_PK=");
////                    logMsg.append(stkOutRsltWrkTMsg.invtyTrxPk.getValue());
////                    logMsg.append(".");
//////                    EZDDebugOutput.println(CST_DEBUG_MSG_LVL, logMsg.toString(), this);
////                    S21InfoLogOutput.println(logMsg.toString());
////                    skipCount++;
////                    return false;
//
//                    // Set Stop Point Code.
//                    stkOutRsltWrkTMsg.defWhCd.setValue(
//                        stkOutRsltWrkTMsg.whCd.getValue());
//                    return true;
//                }
//
//                // All xxDefShipFromFlg = 'N'
//                // Set first xxShipFromCd.
//                stkOutRsltWrkTMsg.defWhCd.setValue(
//                        drvDefWHPMsg.shipFromList.no(0).xxShipFromCd.getValue());
//            }
//
//        // If SHIP_TO_CUST_CD was null, set WH_CD.
//        } else if ("STK_PNT_CD".equals(defWHPram)) {
//            stkOutRsltWrkTMsg.defWhCd.setValue(
//                    stkOutRsltWrkTMsg.whCd.getValue());
//        }
//
//        return true;
//    }
// END   DEL 09/04/2013 MEX-IF021

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameters.
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB015001().executeBatch(NLCB015001.class.getSimpleName());
    }

    // START ADD 2013/12/17 Def.3151
    /**
     *<pre>
     * Get Service Machine Master Snap Shot Proc Date
     *</pre>
     * @return String
     */
    private String getServiceMachineMasterSnapShotProcDate() {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("GLBL_CMPY_CD", glblCmpyCd);
        paramMap.put("PROC_DATE", batProcDate);

        return  (String) ssmBatchClient.queryObject("getServiceMachineMasterSnapShotProcDate", paramMap);
    }

    /**
     *<pre>
     * Get Service Machine Master Snap Shot Proc Date
     *</pre>
     * @return String
     */
    private String getServiceMachineMasterSnapShotCompareDate() {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("GLBL_CMPY_CD", glblCmpyCd);
        paramMap.put("PROC_DATE", batProcDate);

        return  (String) ssmBatchClient.queryObject("getServiceMachineMasterSnapShotCompareDate", paramMap);
    }
    // END ADD 2013/12/17 Def.3151
}
