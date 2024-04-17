/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB013002;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.NLCI0120_01TMsg;
import business.db.NLCI0130_01TMsg;
import business.db.INTFC_SLS_MTH_MGTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * SCM/DB Daily Stock-In Result To Canon,Inc.
 * 
 * Argument:Global Company Code
 *          User Company Code
 *          Interface ID
 *          User Variable1(Process Code)
 *          User Variable3(Commit Count for Bulk insert)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2010   Fujitsu         M.Yamada        Create          N/A
 * 04/06/2010   Fujitsu         S.Yoshida       Update          RQ1318
 * 09/17/2013   Fujitsu         K.Fujita        Update          MEX-IF020
 * </pre>
 */
public class NLCB013002 extends S21BatchMain {

    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;

    /** Message ID : ZZM9000E The field of [@] is not input. */
    private static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZM9001E [@] field has too many digits entered. */
    private static final String ZZM9001E = "ZZM9001E";

    /** The value which is not numerical was input to the field of [@]. */
    private static final String ZZM9004E = "ZZM9004E";

    /** Message ID : ZZBM0009I */
    private static final String ZZBM0009I = "ZZBM0009I";

    /** Message ID : NLCM0053E The process abended. */
    private static final String NLCM0053E = "NLCM0053E";

    /** Message ID : NLCM0063E The value you entered is incorrect. */
    private static final String NLCM0063E = "NLCM0063E";

    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Process Code */
    private static final String MSG_STR_PROC_CODE = "Process Code(VAR_USER1)";

    /** Message string : Interface ID */
    private static final String MSG_STR_INTERFACE_ID = "Interface ID";

    /** Message string : Commit Count */
    private static final String MSG_STR_COMMIT_COUNT = "Commit Count(VAR_USER3)";

    /** Delimiter of List String : COMMA */
    private static final String DELIMITER = ",";

    /** Table ID : STK_IN_RSLT_WRK */
    private static final String TABLE_ID_STK_IN_RSLT_WRK = "STK_IN_RSLT_WRK";

    /** Table ID : NLCI0120_01 */
    private static final String TABLE_ID_NLCI0120_01 = "NLCI0120_01";

    //-----09/17/2013 Delete Start
    /** Table ID : NLCI0260_01 */
//    private static final String TABLE_ID_NLCI0260_01 = "NLCI0260_01";
    //-----09/17/2013 Delete End
    
    /** Table ID : NLCI0130_01 */
    private static final String TABLE_ID_NLCI0130_01 = "NLCI0130_01";

    //-----09/17/2013 Delete Start
//    /** Table ID : NLCI0290_01 */
//    private static final String TABLE_ID_NLCI0290_01 = "NLCI0290_01";
//
//    /** Table ID : NLCI0270_01 */
//    private static final String TABLE_ID_NLCI0270_01 = "NLCI0270_01";
//
//    /** Table ID : NLCI0410_01 */
//    private static final String TABLE_ID_NLCI0410_01 = "NLCI0410_01";
    //-----09/17/2013 Delete End
    
    /** Colmn Name : GLBL_CMPY_CD */
    private static final String COL_NM_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Colmn Name : MDSE_CD */
    private static final String COL_NM_MDSE_CD = "MDSE_CD";

    /** Colmn Name : LOCAL_MDSE_CD */
    private static final String COL_NM_LOCAL_MDSE_CD = "LOCAL_MDSE_CD";

    /** Colmn Name : VND_GLBL_CMPY_CD */
    private static final String COL_NM_VND_GLBL_CMPY_CD = "VND_GLBL_CMPY_CD";

    /** Colmn Name : WH_CD */
    private static final String COL_NM_WH_CD = "WH_CD";

    /** Colmn Name : WRK_DB_QTY_01 */
    private static final String COL_NM_WRK_DB_QTY_01 = "WRK_DB_QTY_01";

    /** Colmn Name : WRK_DB_QTY_02 */
    private static final String COL_NM_WRK_DB_QTY_02 = "WRK_DB_QTY_02";

    /** Fetch Size */
    private static final int FETCH_SIZE = 1000;

    /** Max Value of Work DB Qty */
    private static final BigDecimal MAX_WRK_DB_QTY = new BigDecimal(999999999);

    /** Minimam Value of Work DB Qty */
    private static final BigDecimal MIN_WRK_DB_QTY = new BigDecimal(-999999999);

    /** Zero Value of SCM/DB Qty */
    private static final String ZERO_SCM_DB_QTY = "000000000+";

    /** Length of Item : GLBL_CMPY_3_CD */
    private static final int LENGTH_GLBL_CMPY_3_CD = 3;

    /** Length of Item : MDSE_12_CD */
    private static final int LENGTH_MDSE_12_CD = 12;

    /** Length of Item : LENGTH_ADMIN_YR_MTH */
    private static final int LENGTH_ADMIN_YR_MTH = 6;

    /** getScmDbQty : qty == 0 check true */
    private static final boolean CHECK_ZERO_TRUE = true;

    /** S21UserProfileService */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd = "";

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** The number of cases : Select */
    private int selectCount = 0;

    /** Commit Count */
    private int commitCount = 0;

    /** The number of cases : Insert to NLCI0120_01 */
    private int insertCountAlci0120 = 0;

    /** The number of cases : Insert to NLCI0260_01 */
    private int insertCountAlci0260 = 0;

    /** The number of cases : Insert to NLCI0130_01 */
    private int insertCountAlci0130 = 0;

    /** The number of cases : Insert to NLCI0290_01 */
    private int insertCountAlci0290 = 0;

    /** The number of cases : Insert to NLCI0270_01 */
    private int insertCountAlci0270 = 0;

    /** The number of cases : Insert to NLCI0410_01 */
    private int insertCountAlci0410 = 0;

    /** Process Code : WE100 */
    private static final String PROC_CD_WE100 = "WE100";

    /** Process Code : WE100M */
    private static final String PROC_CD_WE100M = "WE100M";

    /** Process Code : CAS100 */
    private static final String PROC_CD_CAS100 = "CAS100";

    /** Interface ID : NLCI0120 */
    private static final String INTERFACE_ID_NLCI0120 = "NLCI0120";

    /** Interface ID : NLCI0260 */
    //-----09/17/2013 Delete Start
//    private static final String INTERFACE_ID_NLCI0260 = "NLCI0260";
    //-----09/17/2013 Delete End
    
    /** Interface ID : NLCI0130 */
    private static final String INTERFACE_ID_NLCI0130 = "NLCI0130";

    /** Interface ID : NLCI0290 */
    private static final String INTERFACE_ID_NLCI0290 = "NLCI0290";

    //-----09/17/2013 Delete Start
//    /** Interface ID : NLCI0270 */
//    private static final String INTERFACE_ID_NLCI0270 = "NLCI0270";
//
//    /** Interface ID : NLCI0410 */
//    private static final String INTERFACE_ID_NLCI0410 = "NLCI0410";
    //-----09/17/2013 Delete End
    
    /** Transaction ID : NLCI0120 */
    private BigDecimal transactionIdAlci0120 = null;

    /** Transaction ID : NLCI0260 */
    //-----09/17/2013 Delete Start
//    private BigDecimal transactionIdAlci0260 = null;
    //-----09/17/2013 Delete End
    
    /** Transaction ID : NLCI0130 */
    private BigDecimal transactionIdAlci0130 = null;

    /** Transaction ID : NLCI0290 */
    private BigDecimal transactionIdAlci0290 = null;

    //-----09/17/2013 Delete Start
    /** Transaction ID : NLCI0270 */
//    private BigDecimal transactionIdAlci0270 = null;

    /** Transaction ID : NLCI0410 */
//    private BigDecimal transactionIdAlci0410 = null;
    //-----09/17/2013 Delete End
    
    /** Termination code */
    private TERM_CD termCd = null;

    /** Batch Proc Date */
    private String batProcDate = ""; 

    /** Interface Sales Year Month */
    private String intfcSlsYrMth = "";

    /** Process code */
    private String procCd = "";

    /** Interface ID List */
    private List<String> interfaceIdList = null;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = null;

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Initialization of variable
        selectCount = 0;
        insertCountAlci0120 = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Check on input parameter
        checkParameter();

    }

    @Override
    protected void mainRoutine() {

        // Get operation date
        batProcDate = ZYPDateUtil.getBatProcDate(profile.getGlobalCompanyCode().trim());

        // Get Interface Sales Year Month
        intfcSlsYrMth = getIntfcSlsYrMth();

        insertInterfaceRecords();

        insertIntegrationRacord();
    }

    @Override
    protected void termRoutine() {

        // The number of cases : Select is output
        String[] tmp1 = {TABLE_ID_STK_IN_RSLT_WRK, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(ZZBM0009I, tmp1);
        // The number of cases : Insert is output
        String[] tmp2 = {TABLE_ID_NLCI0120_01, "Insert", Integer.toString(insertCountAlci0120) };
        S21InfoLogOutput.println(ZZBM0009I, tmp2);
        //-----09/17/2013 Delete Start
//        String[] tmp3 = {TABLE_ID_NLCI0260_01, "Insert", Integer.toString(insertCountAlci0260) };
//        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp3);
        //-----09/17/2013 Delete End
        String[] tmp4 = {TABLE_ID_NLCI0130_01, "Insert", Integer.toString(insertCountAlci0130) };
        S21InfoLogOutput.println(ZZBM0009I, tmp4);
        //-----09/17/2013 Delete Start
//        String[] tmp5 = {TABLE_ID_NLCI0290_01, "Insert", Integer.toString(insertCountAlci0290) };
//        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp5);
//        String[] tmp6 = {TABLE_ID_NLCI0270_01, "Insert", Integer.toString(insertCountAlci0270) };
//        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp6);
//        String[] tmp7 = {TABLE_ID_NLCI0410_01, "Insert", Integer.toString(insertCountAlci0410) };
//        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp7);
        //-----09/17/2013 Delete End
        
        // Setting of termination code
        setTermState(termCd, selectCount, 0, insertCountAlci0120 + insertCountAlci0260 + insertCountAlci0130 + insertCountAlci0290 + insertCountAlci0270 + insertCountAlci0410);

    }

    /**
     * @param args argument
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB013002().executeBatch(NLCB013002.class.getSimpleName());

    }

    /**
     * Check processing of input parameter
     */
    private void checkParameter() {

        glblCmpyCd = profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            String[] tmp = {MSG_STR_COMP_CODE };
            throw new S21AbendException(ZZM9000E, tmp);
        }

        procCd = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(procCd)) {
            String[] tmp = {MSG_STR_PROC_CODE };
            throw new S21AbendException(ZZM9000E, tmp);
        }

        String strList = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(strList)) {
            String[] tmp = {MSG_STR_INTERFACE_ID };
            throw new S21AbendException(ZZM9000E, tmp);
        }
        interfaceIdList = S21StringUtil.toList(strList, DELIMITER);
        checkInterfaceId(procCd, interfaceIdList);

        String str = getUserVariable3();
        if (!ZYPCommonFunc.hasValue(str)) {
            String[] tmp = {MSG_STR_COMMIT_COUNT };
            throw new S21AbendException(ZZM9000E, tmp);
        }
        if (str.matches("[0-9]+")) {
            commitCount = Integer.valueOf(str).intValue();
        } else {
            throw new S21AbendException(ZZM9004E, new String[] {MSG_STR_COMMIT_COUNT + "(" + str + ")" });
        }

    }

    /**
     * Check interface Id
     * @param pc Process Code
     * @param ifList Interface Id List
     */
    private void checkInterfaceId(String pc, List<String> ifList) {

        for (String interfaceId : ifList) {
            if (PROC_CD_WE100.equals(pc)) {
                if (INTERFACE_ID_NLCI0120.equals(interfaceId)) {
                    transactionIdAlci0120 = trxAccess.getNextTransactionId();
                    //-----09/17/2013 Delete Start
//                } else if (INTERFACE_ID_NLCI0260.equals(interfaceId)) {
//                    transactionIdAlci0260 = trxAccess.getNextTransactionId();
                    //-----09/17/2013 Delete End
                } else {
                    printDebugLog("++ Error:WE100 mismatch Interface ID(" + interfaceId + ")");
                    throw new S21AbendException(NLCM0063E, new String[] {"WE100 * Interface ID" });
                }
            } else if (PROC_CD_WE100M.equals(pc)) {
                if (INTERFACE_ID_NLCI0130.equals(interfaceId)) {
                    transactionIdAlci0130 = trxAccess.getNextTransactionId();
                    //-----09/17/2013 Delete Start
//                } else if (INTERFACE_ID_NLCI0290.equals(interfaceId)) {
//                    transactionIdAlci0290 = trxAccess.getNextTransactionId();
                    //-----09/17/2013 Delete End
                } else {
                    printDebugLog("++ Error:WE100M mismatch Interface ID(" + interfaceId + ")");
                    throw new S21AbendException(NLCM0063E, new String[] {"WE100M * Interface ID" });
                }
                //-----09/17/2013 Delete Start
//            } else if (PROC_CD_CAS100.equals(pc)) {
//                if (INTERFACE_ID_NLCI0270.equals(interfaceId)) {
//                    transactionIdAlci0270 = trxAccess.getNextTransactionId();
//                } else if (INTERFACE_ID_NLCI0410.equals(interfaceId)) {
//                    transactionIdAlci0410 = trxAccess.getNextTransactionId();
//                } else {
//                    printDebugLog("++ Error:CAS100 mismatch Interface ID(" + interfaceId + ")");
//                    throw new S21AbendException(NLCM0063E, new String[] {"CAS100 * Interface ID" });
//                }
                //-----09/17/2013 Delete End
            } else {
                printDebugLog("++ Error:Invalid Process Code(VER_USER1:" + pc + ")");
                throw new S21AbendException(NLCM0063E, new String[] {"Process Code(VER_USER1)" });
            }
        } // end for

    }

    /**
     * Check interface Id
     * @param id Process Code
     * @param ifList Interface Id List
     */
    private boolean hasInterfaceId(String id, List<String> ifList) {

        for (String interfaceId : ifList) {
            if (id.equals(interfaceId)) {
                return true;
            }
        } // end for
        return false;

    }

    /**
     * Insert Interface Records from STK_IN_RSLT_WRK.
     */
    private void insertInterfaceRecords() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        //-----09/17/2013 Delete Start
//        if (PROC_CD_CAS100.equals(procCd)) {
//            insertInterface("getInterfaceCAS", params);
//        } else {
        //-----09/17/2013 Delete End
            insertInterface("getInterfaceWE", params);
//        }

        return;
    }

    /**
     * <pre>
     * Insert the Interface Table. 
     * </pre>
     * 
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void insertInterface(String key, Map<String, String> params) {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            List<NLCI0120_01TMsg> alci0120List = new ArrayList<NLCI0120_01TMsg>();
            //-----09/17/2013 Delete Start
//            List<NLCI0260_01TMsg> alci0260List = new ArrayList<NLCI0260_01TMsg>();
            //-----09/17/2013 Delete End
            List<NLCI0130_01TMsg> alci0130List = new ArrayList<NLCI0130_01TMsg>();
            //-----09/17/2013 Delete Start
//            List<NLCI0290_01TMsg> alci0290List = new ArrayList<NLCI0290_01TMsg>();
//            List<NLCI0270_01TMsg> alci0270List = new ArrayList<NLCI0270_01TMsg>();
//            List<NLCI0410_01TMsg> alci0410List = new ArrayList<NLCI0410_01TMsg>();
            //-----09/17/2013 Delete End
            
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement(key, params, execParam);
            rs = stmt.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (rs.next()) {

                selectCount++;
                if (hasInterfaceId(INTERFACE_ID_NLCI0120, interfaceIdList)) {

                    cnt = procAlci0120(rs, alci0120List, cnt);
                }
                //-----09/17/2013 Delete Start
//                if (hasInterfaceId(INTERFACE_ID_NLCI0260, interfaceIdList)) {
//
//                    cnt = procAlci0260(rs, alci0260List, cnt);
//                }
                //-----09/17/2013 Delete End
                if (hasInterfaceId(INTERFACE_ID_NLCI0130, interfaceIdList)) {

                    cnt = procAlci0130(rs, alci0130List, cnt);
                }
                //-----09/17/2013 Delete Start
//                if (hasInterfaceId(INTERFACE_ID_NLCI0290, interfaceIdList)) {
//
//                    cnt = procAlci0290(rs, alci0290List, cnt);
//                }
//                if (hasInterfaceId(INTERFACE_ID_NLCI0270, interfaceIdList)) {
//
//                    cnt = procAlci0270(rs, alci0270List, cnt);
//                }
//                if (hasInterfaceId(INTERFACE_ID_NLCI0410, interfaceIdList)) {
//
//                    cnt = procAlci0410(rs, alci0410List, cnt);
//                }
                //-----09/17/2013 Delete End
                if (cnt >= commitCount) {
                    commit();
                    cnt = 0;
                }

            } // end while

            if (!alci0120List.isEmpty()) {

                execCnt = S21FastTBLAccessor.insert((NLCI0120_01TMsg[]) alci0120List.toArray(new NLCI0120_01TMsg[0]));
                if (execCnt != alci0120List.size()) {
                    S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0120, ":", execCnt, "/", cnt));
                    throw new S21AbendException(NLCM0053E);
                }
                alci0120List = null;
            }
            //-----09/17/2013 Delete Start
//            if (!alci0260List.isEmpty()) {
//
//                execCnt = S21FastTBLAccessor.insert((NLCI0260_01TMsg[]) alci0260List.toArray(new NLCI0260_01TMsg[0]));
//                if (execCnt != alci0260List.size()) {
//                    S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0260, ":", execCnt, "/", cnt));
//                    throw new S21AbendException(NLCM0053E);
//                }
//                alci0260List = null;
//            }
            //-----09/17/2013 Delete End
            if (!alci0130List.isEmpty()) {

                execCnt = S21FastTBLAccessor.insert((NLCI0130_01TMsg[]) alci0130List.toArray(new NLCI0130_01TMsg[0]));
                if (execCnt != alci0130List.size()) {
                    S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0130, ":", execCnt, "/", cnt));
                    throw new S21AbendException(NLCM0053E);
                }
                alci0130List = null;
            }
            //-----09/17/2013 Delete Start
//            if (!alci0290List.isEmpty()) {
//
//                execCnt = S21FastTBLAccessor.insert((NLCI0290_01TMsg[]) alci0290List.toArray(new NLCI0290_01TMsg[0]));
//                if (execCnt != alci0290List.size()) {
//                    S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0290, ":", execCnt, "/", cnt));
//                    throw new S21AbendException(NLCM0053E);
//                }
//                alci0290List = null;
//            }
//
//            if (!alci0270List.isEmpty()) {
//
//                execCnt = S21FastTBLAccessor.insert((NLCI0270_01TMsg[]) alci0270List.toArray(new NLCI0270_01TMsg[0]));
//                if (execCnt != alci0270List.size()) {
//                    S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0270, ":", execCnt, "/", cnt));
//                    throw new S21AbendException(NLCM0053E);
//                }
//                alci0270List = null;
//            }
//
//            if (!alci0410List.isEmpty()) {
//
//                execCnt = S21FastTBLAccessor.insert((NLCI0410_01TMsg[]) alci0410List.toArray(new NLCI0410_01TMsg[0]));
//                if (execCnt != alci0410List.size()) {
//                    S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0410, ":", execCnt, "/", cnt));
//                    throw new S21AbendException(NLCM0053E);
//                }
//                alci0410List = null;
//            }
            //-----09/17/2013 Delete End
        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * @param rs
     * @param alci0120List
     * @param cnt
     * @return
     * @throws SQLException
     */
    private int procAlci0120(ResultSet rs, List<NLCI0120_01TMsg> alci0120List, int cnt) throws SQLException {
        int execCnt;
        NLCI0120_01TMsg alci0120TMsg = new NLCI0120_01TMsg();
        alci0120TMsg = getAlci0120(rs);
        if (ZYPCommonFunc.hasValue(alci0120TMsg.scmDbQty.getValue())) {
            if (!ZERO_SCM_DB_QTY.equals(alci0120TMsg.scmDbQty.getValue())) {
                alci0120List.add(alci0120TMsg);
                cnt++;

            }
        } else {
            S21InfoLogOutput.println(ZZM9001E, new String[] {"NLCI0120 SCM_DB_QTY_01" });
            infoLogOutRS(rs);
        }

        if (cnt >= commitCount && !alci0120List.isEmpty()) {
            execCnt = S21FastTBLAccessor.insert((NLCI0120_01TMsg[]) alci0120List.toArray(new NLCI0120_01TMsg[0]));
            if (execCnt != alci0120List.size()) {
                S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0120, ":", execCnt, "/", cnt));
                throw new S21AbendException(NLCM0053E);
            }
            alci0120List.clear();
        }
        return cnt;
    }

    /**
     * @param rs
     * @param alci0260List
     * @param cnt
     * @return
     * @throws SQLException
     */
    //-----09/17/2013 Delete Start
//    private int procAlci0260(ResultSet rs, List<NLCI0260_01TMsg> alci0260List, int cnt) throws SQLException {
//
//        int execCnt;
//        NLCI0260_01TMsg alci0260TMsg = new NLCI0260_01TMsg();
//        alci0260TMsg = getAlci0260(rs);
//        if (ZYPCommonFunc.hasValue(alci0260TMsg.scmDbQty.getValue())) {
//            if (!ZERO_SCM_DB_QTY.equals(alci0260TMsg.scmDbQty.getValue())) {
//                alci0260List.add(alci0260TMsg);
//                cnt++;
//
//            }
//        } else {
//            S21InfoLogOutput.println(ZZM9001E, new String[] {"NLCI0260 SCM_DB_QTY_01" });
//            infoLogOutRS(rs);
//        }
//
//        if (cnt >= commitCount && !alci0260List.isEmpty()) {
//            execCnt = S21FastTBLAccessor.insert((NLCI0260_01TMsg[]) alci0260List.toArray(new NLCI0260_01TMsg[0]));
//            if (execCnt != alci0260List.size()) {
//                S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0260, ":", execCnt, "/", cnt));
//                throw new S21AbendException(NLCM0053E);
//            }
//            alci0260List.clear();
//        }
//        return cnt;
//    }
    //-----09/17/2013 Delete End
    
    /**
     * @param rs
     * @param alci0130List
     * @param cnt
     * @return
     * @throws SQLException
     */
    private int procAlci0130(ResultSet rs, List<NLCI0130_01TMsg> alci0130List, int cnt) throws SQLException {

        int execCnt;
        NLCI0130_01TMsg alci0130TMsg = new NLCI0130_01TMsg();
        alci0130TMsg = getAlci0130(rs);
        if (ZYPCommonFunc.hasValue(alci0130TMsg.scmDbQty.getValue())) {
            if (!ZERO_SCM_DB_QTY.equals(alci0130TMsg.scmDbQty.getValue())) {
                alci0130List.add(alci0130TMsg);
                cnt++;

            }
        } else {
            S21InfoLogOutput.println(ZZM9001E, new String[] {"NLCI0130 SCM_DB_QTY_01" });
            infoLogOutRS(rs);
        }

        if (cnt >= commitCount && !alci0130List.isEmpty()) {
            execCnt = S21FastTBLAccessor.insert((NLCI0130_01TMsg[]) alci0130List.toArray(new NLCI0130_01TMsg[0]));
            if (execCnt != alci0130List.size()) {
                S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0130, ":", execCnt, "/", cnt));
                throw new S21AbendException(NLCM0053E);
            }
            alci0130List.clear();
        }
        return cnt;
    }

    /**
     * @param rs
     * @param alci0290List
     * @param cnt
     * @return
     * @throws SQLException
     */
    //-----09/17/2013 Delete Start
//    private int procAlci0290(ResultSet rs, List<NLCI0290_01TMsg> alci0290List, int cnt) throws SQLException {
//
//        int execCnt;
//        NLCI0290_01TMsg alci0290TMsg = new NLCI0290_01TMsg();
//        alci0290TMsg = getAlci0290(rs);
//        if (ZYPCommonFunc.hasValue(alci0290TMsg.scmDbQty.getValue())) {
//            if (!ZERO_SCM_DB_QTY.equals(alci0290TMsg.scmDbQty.getValue())) {
//                alci0290List.add(alci0290TMsg);
//                cnt++;
//
//            }
//        } else {
//            S21InfoLogOutput.println(ZZM9001E, new String[] {"NLCI0290 SCM_DB_QTY_01" });
//            infoLogOutRS(rs);
//        }
//
//        if (cnt >= commitCount && !alci0290List.isEmpty()) {
//            execCnt = S21FastTBLAccessor.insert((NLCI0290_01TMsg[]) alci0290List.toArray(new NLCI0290_01TMsg[0]));
//            if (execCnt != alci0290List.size()) {
//                S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0290, ":", execCnt, "/", cnt));
//                throw new S21AbendException(NLCM0053E);
//            }
//            alci0290List.clear();
//        }
//        return cnt;
//    }
    //-----09/17/2013 Delete End
    
    /**
     * @param rs
     * @param alci0270List
     * @param cnt
     * @return
     * @throws SQLException
     */
    //-----09/17/2013 Delete Start
//    private int procAlci0270(ResultSet rs, List<NLCI0270_01TMsg> alci0270List, int cnt) throws SQLException {
//
//        int execCnt;
//        NLCI0270_01TMsg alci0270TMsg = new NLCI0270_01TMsg();
//        alci0270TMsg = getAlci0270(rs);
//        if (ZYPCommonFunc.hasValue(alci0270TMsg.scmDbQty_01.getValue()) && ZYPCommonFunc.hasValue(alci0270TMsg.scmDbQty_02.getValue())) {
//            alci0270List.add(alci0270TMsg);
//            cnt++;
//
//        } else {
//            S21InfoLogOutput.println(ZZM9001E, new String[] {"NLCI0270 SCM_DB_QTY_01 or SCM_DB_QTY_02" });
//            infoLogOutRS(rs);
//        }
//
//        if (cnt >= commitCount && !alci0270List.isEmpty()) {
//            execCnt = S21FastTBLAccessor.insert((NLCI0270_01TMsg[]) alci0270List.toArray(new NLCI0270_01TMsg[0]));
//            if (execCnt != alci0270List.size()) {
//                S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0270, ":", execCnt, "/", cnt));
//                throw new S21AbendException(NLCM0053E);
//            }
//            alci0270List.clear();
//        }
//        return cnt;
//    }
    //-----09/17/2013 Delete End

    /**
     * @param rs
     * @param alci0410List
     * @param cnt
     * @return
     * @throws SQLException
     */
    //-----09/17/2013 Delete Start
//    private int procAlci0410(ResultSet rs, List<NLCI0410_01TMsg> alci0410List, int cnt) throws SQLException {
//
//        int execCnt;
//        NLCI0410_01TMsg alci0410TMsg = new NLCI0410_01TMsg();
//        alci0410TMsg = getAlci0410(rs);
//        if (ZYPCommonFunc.hasValue(alci0410TMsg.scmDbQty_01.getValue())) {
//            if (!ZERO_SCM_DB_QTY.equals(alci0410TMsg.scmDbQty_01.getValue())) {
//                alci0410List.add(alci0410TMsg);
//                cnt++;
//            }
//
//        } else {
//            S21InfoLogOutput.println(ZZM9001E, new String[] {"NLCI0410 SCM_DB_QTY_01 or SCM_DB_QTY_02" });
//            infoLogOutRS(rs);
//        }
//
//        if (cnt >= commitCount && !alci0410List.isEmpty()) {
//            execCnt = S21FastTBLAccessor.insert((NLCI0410_01TMsg[]) alci0410List.toArray(new NLCI0410_01TMsg[0]));
//            if (execCnt != alci0410List.size()) {
//                S21InfoLogOutput.println(S21StringUtil.concatStrings(NLCM0053E, ":", INTERFACE_ID_NLCI0410, ":", execCnt, "/", cnt));
//                throw new S21AbendException(NLCM0053E);
//            }
//            alci0410List.clear();
//        }
//        return cnt;
//    }
    //-----09/17/2013 Delete End
    
    /**
     * <pre>Get NLCI0120_01 TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    private NLCI0120_01TMsg getAlci0120(ResultSet rs) throws SQLException {

        NLCI0120_01TMsg alci0120TMsg = new NLCI0120_01TMsg();

        String scmDbQty = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_01), CHECK_ZERO_TRUE);
        if (!ZYPCommonFunc.hasValue(scmDbQty)) {
            return alci0120TMsg;
        }

        ZYPEZDItemValueSetter.setValue(alci0120TMsg.interfaceId, INTERFACE_ID_NLCI0120);
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.transactionId, transactionIdAlci0120);
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.unitId, new BigDecimal(++insertCountAlci0120));
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.seqNumber, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(alci0120TMsg.amendCd, "");

        ZYPEZDItemValueSetter.setValue(alci0120TMsg.glblCmpy3Cd, S21StringUtil.subStringByLength(glblCmpyCd, 0, LENGTH_GLBL_CMPY_3_CD));
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.mdse12Cd, S21StringUtil.subStringByLength(rs.getString(COL_NM_MDSE_CD), 0, LENGTH_MDSE_12_CD));
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.localMdseCd, rs.getString(COL_NM_LOCAL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.vndAfflCd, rs.getString(COL_NM_VND_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.intfcWhCd, "");

        ZYPEZDItemValueSetter.setValue(alci0120TMsg.adminYrMth, intfcSlsYrMth);
        ZYPEZDItemValueSetter.setValue(alci0120TMsg.adminDt, batProcDate);

        ZYPEZDItemValueSetter.setValue(alci0120TMsg.scmDbQty, scmDbQty);

        ZYPEZDItemValueSetter.setValue(alci0120TMsg.fill32Txt, "");

        return alci0120TMsg;
    }

    /**
     * <pre>Get NLCI0260_01 TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    //-----09/17/2013 Delete Start
//    private NLCI0260_01TMsg getAlci0260(ResultSet rs) throws SQLException {
//
//        NLCI0260_01TMsg alci0260TMsg = new NLCI0260_01TMsg();
//
//        String scmDbQty = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_01), CHECK_ZERO_TRUE);
//        if (!ZYPCommonFunc.hasValue(scmDbQty)) {
//            return alci0260TMsg;
//        }
//
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.interfaceId, INTERFACE_ID_NLCI0260);
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.transactionId, transactionIdAlci0260);
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.unitId, new BigDecimal(++insertCountAlci0260));
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.seqNumber, BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.amendCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.glblCmpy3Cd, S21StringUtil.subStringByLength(glblCmpyCd, 0, LENGTH_GLBL_CMPY_3_CD));
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.mdse12Cd, S21StringUtil.subStringByLength(rs.getString(COL_NM_MDSE_CD), 0, LENGTH_MDSE_12_CD));
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.localMdseCd, rs.getString(COL_NM_LOCAL_MDSE_CD));
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.vndAfflCd, rs.getString(COL_NM_VND_GLBL_CMPY_CD));
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.intfcWhCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.adminYrMth, intfcSlsYrMth);
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.adminDt, batProcDate);
//
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.scmDbQty, scmDbQty);
//
//        ZYPEZDItemValueSetter.setValue(alci0260TMsg.fill32Txt, "");
//
//        return alci0260TMsg;
//    }
    //-----09/17/2013 Delete End
    
    /**
     * <pre>Get NLCI0130_01 TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    private NLCI0130_01TMsg getAlci0130(ResultSet rs) throws SQLException {

        NLCI0130_01TMsg alci0130TMsg = new NLCI0130_01TMsg();

        String scmDbQty = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_01), CHECK_ZERO_TRUE);
        if (!ZYPCommonFunc.hasValue(scmDbQty)) {
            return alci0130TMsg;
        }

        ZYPEZDItemValueSetter.setValue(alci0130TMsg.interfaceId, INTERFACE_ID_NLCI0130);
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.transactionId, transactionIdAlci0130);
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.segmentId, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.unitId, new BigDecimal(++insertCountAlci0130));
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.seqNumber, BigDecimal.ONE);

        ZYPEZDItemValueSetter.setValue(alci0130TMsg.amendCd, "");

        ZYPEZDItemValueSetter.setValue(alci0130TMsg.glblCmpy3Cd, S21StringUtil.subStringByLength(glblCmpyCd, 0, LENGTH_GLBL_CMPY_3_CD));
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.mdse12Cd, S21StringUtil.subStringByLength(rs.getString(COL_NM_MDSE_CD), 0, LENGTH_MDSE_12_CD));
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.localMdseCd, rs.getString(COL_NM_LOCAL_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.vndAfflCd, rs.getString(COL_NM_VND_GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.intfcWhCd, "");

        ZYPEZDItemValueSetter.setValue(alci0130TMsg.adminYrMth, intfcSlsYrMth);
        ZYPEZDItemValueSetter.setValue(alci0130TMsg.adminDt, batProcDate);

        ZYPEZDItemValueSetter.setValue(alci0130TMsg.scmDbQty, scmDbQty);

        ZYPEZDItemValueSetter.setValue(alci0130TMsg.fill32Txt, "");

        return alci0130TMsg;
    }

    /**
     * <pre>Get NLCI0290_01 TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    //-----09/17/2013 Delete Start
//    private NLCI0290_01TMsg getAlci0290(ResultSet rs) throws SQLException {
//
//        NLCI0290_01TMsg alci0290TMsg = new NLCI0290_01TMsg();
//
//        String scmDbQty = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_01), CHECK_ZERO_TRUE);
//        if (!ZYPCommonFunc.hasValue(scmDbQty)) {
//            return alci0290TMsg;
//        }
//
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.interfaceId, INTERFACE_ID_NLCI0290);
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.transactionId, transactionIdAlci0290);
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.unitId, new BigDecimal(++insertCountAlci0290));
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.seqNumber, BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.amendCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.glblCmpy3Cd, S21StringUtil.subStringByLength(glblCmpyCd, 0, LENGTH_GLBL_CMPY_3_CD));
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.mdse12Cd, S21StringUtil.subStringByLength(rs.getString(COL_NM_MDSE_CD), 0, LENGTH_MDSE_12_CD));
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.localMdseCd, rs.getString(COL_NM_LOCAL_MDSE_CD));
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.vndAfflCd, rs.getString(COL_NM_VND_GLBL_CMPY_CD));
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.intfcWhCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.adminYrMth, intfcSlsYrMth);
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.adminDt, batProcDate);
//
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.scmDbQty, scmDbQty);
//
//        ZYPEZDItemValueSetter.setValue(alci0290TMsg.fill32Txt, "");
//
//        return alci0290TMsg;
//    }
    //-----09/17/2013 Delete Start

    /**
     * <pre>Get NLCI0270_01 TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    //-----09/17/2013 Delete Start
//    private NLCI0270_01TMsg getAlci0270(ResultSet rs) throws SQLException {
//
//        NLCI0270_01TMsg alci0270TMsg = new NLCI0270_01TMsg();
//
//        String scmDbQty01 = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_01));
//        String scmDbQty02 = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_02));
//        if (!ZYPCommonFunc.hasValue(scmDbQty01) || !ZYPCommonFunc.hasValue(scmDbQty02)) {
//            return alci0270TMsg;
//        }
//
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.interfaceId, INTERFACE_ID_NLCI0270);
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.transactionId, transactionIdAlci0270);
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.segmentId, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.unitId, new BigDecimal(++insertCountAlci0270));
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.seqNumber, BigDecimal.ONE);
//
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.amendCd, "");
//
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.glblCmpy3Cd, S21StringUtil.subStringByLength(glblCmpyCd, 0, LENGTH_GLBL_CMPY_3_CD));
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.mdse12Cd, S21StringUtil.subStringByLength(rs.getString(COL_NM_MDSE_CD), 0, LENGTH_MDSE_12_CD));
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.localMdseCd, rs.getString(COL_NM_LOCAL_MDSE_CD));
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.vndAfflCd, rs.getString(COL_NM_VND_GLBL_CMPY_CD));
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.intfcWhCd, rs.getString(COL_NM_WH_CD));
//
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.adminYrMth, intfcSlsYrMth);
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.adminDt, batProcDate);
//
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.scmDbQty_01, scmDbQty01);
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.scmDbQty_02, scmDbQty02);
//
//        ZYPEZDItemValueSetter.setValue(alci0270TMsg.fill22Txt, "");
//
//        return alci0270TMsg;
//    }
    //-----09/17/2013 Delete End

    /**
     * <pre>Get NLCI0410_01 TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    //-----09/17/2013 Delete Start
//    private NLCI0410_01TMsg getAlci0410(ResultSet rs) throws SQLException {
//
//        NLCI0410_01TMsg alci0410TMsg = new NLCI0410_01TMsg();
//
//        String scmDbQty = getScmDbQty(rs.getBigDecimal(COL_NM_WRK_DB_QTY_01));
//        if (!ZYPCommonFunc.hasValue(scmDbQty)) {
//            return alci0410TMsg;
//        }
//
//        if (!ZERO_SCM_DB_QTY.equals(scmDbQty)) {
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.interfaceId, INTERFACE_ID_NLCI0410);
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.transactionId, transactionIdAlci0410);
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.segmentId, BigDecimal.ONE);
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.unitId, new BigDecimal(++insertCountAlci0410));
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.seqNumber, BigDecimal.ONE);
//
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.amendCd, "");
//
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.glblCmpy3Cd, S21StringUtil.subStringByLength(glblCmpyCd, 0, LENGTH_GLBL_CMPY_3_CD));
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.mdse12Cd, S21StringUtil.subStringByLength(rs.getString(COL_NM_MDSE_CD), 0, LENGTH_MDSE_12_CD));
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.localMdseCd, rs.getString(COL_NM_LOCAL_MDSE_CD));
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.vndAfflCd, rs.getString(COL_NM_VND_GLBL_CMPY_CD));
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.intfcWhCd, rs.getString(COL_NM_WH_CD));
//
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.adminYrMth, intfcSlsYrMth);
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.adminDt, batProcDate);
//
//            ZYPEZDItemValueSetter.setValue(alci0410TMsg.fill32Txt, "");
//        }
//        ZYPEZDItemValueSetter.setValue(alci0410TMsg.scmDbQty_01, scmDbQty);
//
//        return alci0410TMsg;
//    }
    //-----09/17/2013 Delete End

    /**
     * <pre>Get SCM/DB Qty by Decimal Format</pre>
     * 
     * @param bdQty SCM/DB Qty 01 or 02.
     */
    private String getScmDbQty(BigDecimal bdQty, boolean checkZero) {

        if (BigDecimal.ZERO.compareTo(bdQty) == 0) {
            return null;
        }
        return getScmDbQty(bdQty);
    }

    /**
     * <pre>Get SCM/DB Qty by Decimal Format</pre>
     * 
     * @param bdQty SCM/DB Qty 01 or 02.
     */
    private String getScmDbQty(BigDecimal bdQty) {

        // Overflow
        if ((MAX_WRK_DB_QTY.compareTo(bdQty) < 0) || (MIN_WRK_DB_QTY.compareTo(bdQty) > 0)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat("000000000'+';000000000-");
        return df.format(bdQty);
    }

    /**
     * Insert Integration Racord
     * @param pc Process Code
     * @param interfaceIdList Interface Id List
     */
    private void insertIntegrationRacord() {

        for (String interfaceId : interfaceIdList) {

            if (INTERFACE_ID_NLCI0120.equals(interfaceId)) {
                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0120);
//            } else if (INTERFACE_ID_NLCI0260.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0260);
            } else if (INTERFACE_ID_NLCI0130.equals(interfaceId)) {
                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0130);
            } else if (INTERFACE_ID_NLCI0290.equals(interfaceId)) {
                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0290);
//            } else if (INTERFACE_ID_NLCI0270.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0270);
//            } else if (INTERFACE_ID_NLCI0410.equals(interfaceId)) {
//                trxAccess.createIntegrationRecordForBatch(interfaceId, transactionIdAlci0410);
            }

        }
        commit();

    }

    /**
     * InfoLog Output Result Set
     * @param rs Result Set
     */
    private void infoLogOutRS(ResultSet rs) throws SQLException {

        S21InfoLogOutput.println("+++ Result Set +++");
        S21InfoLogOutput.println(COL_NM_GLBL_CMPY_CD + ":" + rs.getString(COL_NM_GLBL_CMPY_CD));
        S21InfoLogOutput.println(COL_NM_MDSE_CD + ":" + rs.getString(COL_NM_MDSE_CD));
        S21InfoLogOutput.println(COL_NM_LOCAL_MDSE_CD + ":" + rs.getString(COL_NM_LOCAL_MDSE_CD));
        S21InfoLogOutput.println(COL_NM_VND_GLBL_CMPY_CD + ":" + rs.getString(COL_NM_VND_GLBL_CMPY_CD));
        if (PROC_CD_CAS100.equals(procCd)) {
            S21InfoLogOutput.println(COL_NM_WH_CD + ":" + rs.getString(COL_NM_WH_CD));
        }
        S21InfoLogOutput.println(COL_NM_WRK_DB_QTY_01 + ":" + rs.getString(COL_NM_WRK_DB_QTY_01));
        if (PROC_CD_CAS100.equals(procCd)) {
            S21InfoLogOutput.println(COL_NM_WRK_DB_QTY_02 + ":" + rs.getString(COL_NM_WRK_DB_QTY_02));
        }
        S21InfoLogOutput.println("++++++++++++++++++");

    }

    /**
     * Get Interface Sales Year Month.
     * 
     * @return Interface Sales Year Month
     */
    private String getIntfcSlsYrMth() {

        INTFC_SLS_MTH_MGTTMsg iFSlsMthTMsg = new INTFC_SLS_MTH_MGTTMsg();
        iFSlsMthTMsg.glblCmpyCd.setValue(glblCmpyCd);
        iFSlsMthTMsg = (INTFC_SLS_MTH_MGTTMsg) S21FastTBLAccessor.findByKey(iFSlsMthTMsg);
        if (iFSlsMthTMsg == null) {
            S21InfoLogOutput.println("Interface Sales Year Month dose not exist.");
            throw new S21AbendException(NLCM0053E);
        }
        return iFSlsMthTMsg.intfcSlsYrMth.getValue();

    }

    /**
     * <pre>
     * Print debug log.
     * </pre>
     * 
     * @param debugMsg debug message
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }

}
