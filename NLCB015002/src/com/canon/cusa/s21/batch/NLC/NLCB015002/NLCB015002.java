package com.canon.cusa.s21.batch.NLC.NLCB015002;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import business.db.NLCI0140_01TMsg;
import business.db.NLCI0150_01TMsg;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
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
 * SCM/DB Daily Stock-Out Result To Canon,Inc.
 * Create Interface Data.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2010   Fujitsu         S.Yoshida       Create          N/A
 * 03/17/2010   Fujitsu         S.Yoshida       Update          Def.4694
 * 03/19/2010   Fujitsu         S.Yoshida       Update          Def.5007
 * 02/11/2011   Fujitsu         M.Yamada        Update          intangible case missing
 * 10/08/2012   CSAI            M.Takahashi     Update          PROD#416428
 * 09/04/2013   Fujitsu         Y.Taoka         Update          MEX-IF021
 * 11/21/2013   Fujitsu         Y.Taoka         Update          Def.3151
 * 12/29/2013   CSAI            K.Lee           Update          Def.3322
 * </pre>
 */
public class NLCB015002 extends S21BatchMain {

    //-- Error Message Code --------------------
    /** Message ID : ZZM9000E The field of [@] is not input. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";
    /** The value which is not numerical was input to the field of [@]. */
    private static final String MSG_ID_ZZM9004E = "ZZM9004E";
    /** Message ID : ZZBM0009I */
    private static final String MSG_ID_ZZBM0009I = "ZZBM0009I";
    /** Message ID : NLCM0053E The process abended. */
    private static final String MSG_ID_NLCM0053E = "NLCM0053E";

    //-- Error Message Parameter --------------------
    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";
    /** Message string : Global Company Code */
    private static final String MSG_STR_INTERFACE_ID = "Interface ID";
    /** Message string : Process Code */
    private static final String MSG_STR_PARAM_01 = "Process Code(VAR_USER1)";
    /** Message string : Commit Count */
    private static final String MSG_STR_PARAM_03 = "Commit Count(VAR_USER3)";
    /** Table ID : STK_IN_RSLT_WRK */
    private static final String TBL_ID_STK_OUT_RSLT_WRK = "STK_OUT_RSLT_WRK";

    //-- Interface ID -------------------------------
    /** Interface ID : NLCI0140 */
    private static final String IF_ID_NLCI0140 = "NLCI0140";
    /** Interface ID : NLCI0150 */
    private static final String IF_ID_NLCI0150 = "NLCI0150";

    // START DEL 09/04/2013 MEX-IF021
//    /** Interface ID : NLCI0280 */
//    private static final String IF_ID_NLCI0280 = "NLCI0280";
//    /** Interface ID : NLCI0200 */
//    private static final String IF_ID_NLCI0200 = "NLCI0200";
//    /** Interface ID : NLCI0420 */
//    private static final String IF_ID_NLCI0420 = "NLCI0420";
//    /** Interface ID : NLCI0250 */
//    private static final String IF_ID_NLCI0250 = "NLCI0250";
//    /** Interface ID : NLCI0230 */
//    private static final String IF_ID_NLCI0230 = "NLCI0230";
    // END  DEL 09/04/2013 MEX-IF021

    //-- Other Internal constants --------------
    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;
    /** Fetch Size */
    private static final int FETCH_SIZE = 1000;

    //-- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd = "";;
    /** Interface ID */
    private String[] interfaceId = null;
    /** Process code */
    private String procCd = "";;
    /** Commit Count */
    private int commitCount = 0;

    //-- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount = 0;
    /** The number of cases : Insert */
    private int[] insertCount = null;
    /** The number of case : Skip */
    private int skipCount = 0;
    /** Insert table name list. */
    private String[] insertTableName = null;

    //-- Other Internal Variable ---------------
    /** User Profile Service  */
    private S21UserProfileService profileService = null;
    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    /** Termination code */
    private TERM_CD termCd = null;
    /** Batch Proc Date */
    private String batProcDate = "";
    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess = null;

    //-- Dummy Value constants --------------
    // ADD 2013/11/21 Def.3151
    /** Dummy Value : TRX_RSN_CD Rental Shipment out */
    private static final String TRX_RNTL_SHIP_OUT_DUMMY = "99";
    // START 2016/07/01 T.Kikuhara V2.0
    private static final String REMAN_ITEM_CHANGE_COMP_STOCK_IN = "05";
    private static final String REMAN_ITEM_CHANGE_COMP_STOCK_OUT = "06";
    // END 2016/07/01 T.Kikuhara V2.0

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Check on input parameter
        checkParameter();

    }

    @Override
    protected void mainRoutine() {

        // Get operation date.
        batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);

        // Create interface date.
        createStkOutRsltIF();
    }

    @Override
    protected void termRoutine() {

        String[] tmp = null;

        // The number of cases : Select is output
        tmp = new String[]{TBL_ID_STK_OUT_RSLT_WRK, "Read", Integer.toString(selectCount / interfaceId.length)};
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp);

        // The number of cases : Insert is output
        int totalInsertCount = 0;
        for (int i = 0; i < interfaceId.length; i++) {
            tmp = new String[]{interfaceId[i], "Insert", Integer.toString(insertCount[i])};
            S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp);
            totalInsertCount += insertCount[i];
        }

        // Setting of termination code
        setTermState(termCd, selectCount, skipCount, totalInsertCount);

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

        // Interface ID
        boolean errFlg = false;
        interfaceId = getInterfaceIDList();
        if (interfaceId == null || interfaceId.length == 0) {
            errFlg = true;
        } else {
            for (int i = 0; i < interfaceId.length; i++) {
                if (!ZYPCommonFunc.hasValue(interfaceId[i])) {
                    errFlg = true;
                    break;
                }
            }
        }
        if (errFlg) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[]{MSG_STR_INTERFACE_ID});
        }
        insertCount = new int[interfaceId.length];
        insertTableName = new String[interfaceId.length];

        // Process code
        procCd = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(procCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_PARAM_01});
        }

        // Commit Count
        String str = getUserVariable3();
        if (!ZYPCommonFunc.hasValue(str)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_PARAM_03});
        }
        try {
            commitCount = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {"Commit Count(" + str + ")"});
        }
    }

    /**
     * <pre>
     * Create interface data for SCM/DB Daily Stock-Out Result. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void createStkOutRsltIF() {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement prdStmt = null;
        ResultSet rs = null;

        try {

            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            prdStmt = ssmLLClient.createPreparedStatement("findWork", getFindWorkParam(), execParam);
            rs = prdStmt.executeQuery();

            // Check query result.
            if (!rs.next()) {
                // Create INTERFACE_TRANSACTION for send blank data to
                // COMETS.
                // Sending blan file or not is depend on the
                // configuratin of WMB .
                for (int i = 0; i < interfaceId.length; i++) {
                    // Get Transaction ID.
                    BigDecimal trxId = trxAccess.getNextTransactionId();
                    trxAccess.createIntegrationRecordForBatch(interfaceId[i], trxId);
                }
                commit();
                return;
            }
            rs.beforeFirst();

            EZDTMsg tMsg = null;
            BigDecimal trxId = null;
            for (int i = 0; i < interfaceId.length; i++) {

                // Get Transaction ID.
                trxId = trxAccess.getNextTransactionId();

                // Generate TMsg of each Interface ID.
                if (IF_ID_NLCI0140.equals(interfaceId[i])) {
                    tMsg = new NLCI0140_01TMsg();

                // DEL 09/04/2013 MEX-IF021
//                } else if (IF_ID_NLCI0280.equals(interfaceId[i])) {
//                    tMsg = new NLCI0280_01TMsg();

                } else if (IF_ID_NLCI0150.equals(interfaceId[i])) {
                    tMsg = new NLCI0150_01TMsg();

                // DEL 09/04/2013 MEX-IF021
//                } else if (IF_ID_NLCI0200.equals(interfaceId[i])) {
//                    tMsg = new NLCI0200_01TMsg();
//
//                } else if (IF_ID_NLCI0420.equals(interfaceId[i])) {
//                    tMsg = new NLCI0420_01TMsg();
//
//                } else if (IF_ID_NLCI0250.equals(interfaceId[i])) {
//                    tMsg = new NLCI0250_01TMsg();
//
//                } else if (IF_ID_NLCI0230.equals(interfaceId[i])) {
//                    tMsg = new NLCI0230_01TMsg();

                } else {
                    throw new S21AbendException(MSG_ID_ZZM9000E, new String[]{MSG_STR_INTERFACE_ID});
                }

                // Set parameters.
                tMsg.setConditionValue("transactionId", trxId);
                tMsg.setConditionValue("interfaceId", interfaceId[i]);
                tMsg.setConditionValue("segmentId", 1);
                tMsg.setConditionValue("seqNumber", 1);

                // Insert Interface Table.
                insertIF(rs, tMsg, interfaceId[i], i);
                trxAccess.createIntegrationRecordForBatch(interfaceId[i], trxId);
                commit();
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * <pre>
     * Set the find for Stock Out Result Work parameters.
     * </pre>
     * @return Find for IInventory Transaction parameters.
     */
    private Map<String, String> getFindWorkParam() {
        Map<String, String> param = new HashMap<String, String>();

        param.put("GLBL_CMPY_CD", glblCmpyCd);
        param.put("PROC_DATE", batProcDate);
        param.put("PROC_CD", procCd);

        // Stock Status Code
        param.put("SS_GOOD", STK_STS.GOOD);

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
        param.put("TR_RNTL_TO_SLS", TRX_RSN.RENTAL_TO_SALES);
        param.put("TR_REG_SLS_ASSET", TRX_RSN.REGULAR_SALES_ASSET);                    // ADD 09/04/2013 MEX-IF021
        param.put("TR_LOAN_TO_SALES", TRX_RSN.LOAN_TO_SALES);                          // ADD 09/04/2013 MEX-IF021
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("CASH_MDSE", TRX_RSN.REGULAR_SALES_CASH_LEASE_MDSE);
        param.put("CASH_INI_SUP", TRX_RSN.REGULAR_SALES_CASH_LEASE_INIT_SUP);
        param.put("CASH_SUP", TRX_RSN.REGULAR_SALES_CASH_LEASE_SUP);
        param.put("CASH_CNT_SUP", TRX_RSN.REGULAR_SALES_CASH_LEASE_CNTR_SUP);
        param.put("R_CASH_RMA", TRX_RSN.RETURN);
        // END 2016/07/01 T.Kikuhara V2.0
        // 020:MOVEMENT
//        param.put("TR_WH_TRNSF_STK_OUT", TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);      // DEL 09/04/2013 MEX-IF021
//        param.put("TR_STK_STS_CHNG_STK_OUT", TRX_RSN.STOCK_STATUS_CHANGE_STOCK_OUT); // DEL 2013/10/30 Def.3151(Def.2928)
//        param.put("TR_STK_STS_CHNG_STK_IN", TRX_RSN.STOCK_STATUS_CHANGE_STOCK_IN);   // DEL 2013/10/30 Def.3151(Def.2928)
        param.put("TR_TRIAL_SHIP_STK_OUT", TRX_RSN.TRIAL_SHIPMENT_STOCK_OUT);
        param.put("TR_DRCT_SALE_SHIP_STK_OUT", TRX_RSN.DIRECT_SALE_SHIPMENT_STOCK_OUT);
        //START 2013/12/29 ADD QC3322
        param.put("TR_DRCT_SALE_STK_OUT_INTANGIBLE_WITH_COST", TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST);
        param.put("TR_EXPT_SALE_SHIP_STK_OUT", TRX_RSN.EXPORT_SALE_SHIPMENT_STOCK_OUT);
        //END 2013/12/29 ADD QC3322
        param.put("TR_TRIAL_TO_INVTY_STK_IN", TRX_RSN.TRIAL_TO_INVENTORY_STOCK_IN);
        param.put("TR_DRCT_SALE_SHIP_CANC_STK_IN", TRX_RSN.DIRECT_SALE_SHIPPMENT_CANCEL_STOCK_IN);
        param.put("TR_DROP_SHIP_TRIAL_STK_OUT", TRX_RSN.DROP_SHIPMENT_TRIAL_STOCK_OUT);
        param.put("TR_EXPT_TRIAL_STK_OUT", TRX_RSN.EXPORT_TRIAL_STOCK_OUT);
        param.put("TR_TRIAL_STK_OUT_INTG_W_COST", TRX_RSN.TRIAL_STOCK_OUT_INTANGIBLE_WITH_COST);
//        param.put("TR_DRCT_SALE_STK_OUT_INTG_W_COST", TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST); // DEL 09/04/2013 MEX-IF021
        param.put("TR_WH_TRANS_STK_OUT", TRX_RSN.WAREHOUSE_TRANSFER_STOCK_OUT);         // ADD 11/21/2013 Def.3151
        param.put("TR_INTNL_WH_TRANS_STK_IN", TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN);   // ADD 2013/11/21 Def.3151
        // 030:EXPENSE SHIPMENT
        param.put("TR_EXP_SHIP", TRX_RSN.EXPENSE_SHIPMENT);
        param.put("TR_EXP_RTN", TRX_RSN.EXPENSE_RETURN);
        //START 2013/12/29 ADD QC3322
        param.put("TR_EXP_SHIP_ASSET", TRX_RSN.EXPENSE_SHIPMENT_ASSET);
        //END 2013/12/29 ADD QC3322
        // START 2016/07/01 T.Kikuhara V2.0
        param.put("TR_DRCT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST", TRX_RSN.DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST);
        param.put("EX_LOAN_SHIP_OUT", TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT);
        param.put("EX_LOAN_INVTY_OUT", TRX_RSN.EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT);
        param.put("EX_LOAN_DROP_OUT", TRX_RSN.EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT);
        param.put("EX_LOAN_INTANG_OUT", TRX_RSN.EXPENSE_LOAN_SHIPMENT_STOCK_OUT_INTANGIBLE_WITH_COST);
        // END 2016/07/01 T.Kikuhara V2.0
        // 040:LOAN SHIPMENT
        param.put("TR_LOAN_SHIP_STK_OUT", TRX_RSN.LOAN_SHIPMENT_STOCK_OUT);
        param.put("TR_DROP_SHIP_LOAN_STK_OUT", TRX_RSN.DROP_SHIPMENT_LOAN_STOCK_OUT);
//        param.put("TR_LOAN_TO_ASSET_RETURN", TRX_RSN.LOAN_TO_ASSET_RETURN);               // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.3151(Def.2928)
//        param.put("TR_LOAN_SHIP_STK_OUT_ASSET", TRX_RSN.LOAN_SHIPMENT_STOCK_OUT_ASSET);   // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.3151(Def.2928)
        // 050:RENTAL SHIPMENT
//        param.put("TR_RNTL_SHIP_STK_OUT", TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT);
//        param.put("TR_RNTL_SHIP_RNTL_STK_OUT", TRX_RSN.DROP_SHIPMENT_RENTAL_STOCK_OUT);   // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.3151(Def.2928)
//        param.put("TR_RNTL_TO_ASSET_RNTL", TRX_RSN.RENTAL_TO_ASSET_RETURN);               // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.3151(Def.2928)
//        param.put("TR_RNTL_SHIP_STK_OUT_ASSET", TRX_RSN.RENTAL_SHIPMENT_STOCK_OUT_ASSET); // ADD 09/04/2013 MEX-IF021 // DEL 2013/10/30 Def.3151(Def.2928)
        param.put("TR_RNTL_SHIP_OUT_DUMMY", TRX_RNTL_SHIP_OUT_DUMMY);                     // ADD 2013/11/21 Def.3151
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
        return param;
    }

    /**
     * <pre>
     * Insert Interface Table.
     * </pre>
     * @param  rs    Result set
     * @param  tMsg  TMsg of each Interface ID
     * @param  ifId  Interface ID
     * @param  index Index of Interface ID
     * @throws SQLException
     */
    private void insertIF(ResultSet rs, EZDTMsg ezdTMsg, String ifId, int index) throws SQLException {

        List<EZDTMsg> ezdTMsgList = new ArrayList<EZDTMsg>();

        int cnt = 0;
        int execCnt = 0;
        EZDTMsg tMsg = null;
        int i = 0;

        if (index > 0) {
            rs.beforeFirst();
        }

        while (rs.next()) {
            selectCount++;
            cnt++;

            // Copy Result Set to TMsg
            tMsg = (EZDTMsg) ezdTMsg.clone();
            tMsg.setConditionValue("unitId", ++i);
            S21ResultSetMapper.map(rs, tMsg);

            // Check Qty
            if (!checkQty(tMsg)) {
                i--;
                cnt--;
                continue;
            }

            ezdTMsgList.add(tMsg);

            // commit
            if (cnt == commitCount) {
                execCnt = S21FastTBLAccessor.insert(convertAryToMsg(ezdTMsgList, ifId));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                insertCount[index] += cnt;
                cnt = 0;
                ezdTMsgList.clear();
            }
        }
        if (cnt > 0) {
            execCnt = S21FastTBLAccessor.insert(convertAryToMsg(ezdTMsgList, ifId));
            if (execCnt != cnt) {
                throw new S21AbendException(MSG_ID_NLCM0053E);
            }
            commit();
            insertCount[index] += cnt;
        }

        // Set Insert Table Name.
        insertTableName[index] = ifId;
    }

    /**
     * <pre>
     * Convert List<EZDTMsg> to EZDTMsg[].
     * </pre>
     * @param  ezdTMsgList List<EZDTMsg>
     * @param  ifId        Interface ID
     * @return EZDTMsg[]   TMsg List of each Interface ID
     */
    private EZDTMsg[] convertAryToMsg(List<EZDTMsg> ezdTMsgList, String ifId) {

        EZDTMsg[] tMsgList = null;

        if (IF_ID_NLCI0140.equals(ifId)) {
            tMsgList = (NLCI0140_01TMsg[]) ezdTMsgList.toArray(new NLCI0140_01TMsg[0]);

        // DEL 09/04/2013 MEX-IF021
//        } else if (IF_ID_NLCI0280.equals(ifId)) {
//            tMsgList = (NLCI0280_01TMsg[]) ezdTMsgList.toArray(new NLCI0280_01TMsg[0]);

        } else if (IF_ID_NLCI0150.equals(ifId)) {
            tMsgList = (NLCI0150_01TMsg[]) ezdTMsgList.toArray(new NLCI0150_01TMsg[0]);

         // DEL 09/04/2013 MEX-IF021
//        } else if (IF_ID_NLCI0200.equals(ifId)) {
//            tMsgList = (NLCI0200_01TMsg[]) ezdTMsgList.toArray(new NLCI0200_01TMsg[0]);
//
//        } else if (IF_ID_NLCI0420.equals(ifId)) {
//            tMsgList = (NLCI0420_01TMsg[]) ezdTMsgList.toArray(new NLCI0420_01TMsg[0]);
//
//        } else if (IF_ID_NLCI0250.equals(ifId)) {
//            tMsgList = (NLCI0250_01TMsg[]) ezdTMsgList.toArray(new NLCI0250_01TMsg[0]);
//
//        } else if (IF_ID_NLCI0230.equals(ifId)) {
//            tMsgList = (NLCI0230_01TMsg[]) ezdTMsgList.toArray(new NLCI0230_01TMsg[0]);

        } else {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[]{MSG_STR_INTERFACE_ID});
        }

        return tMsgList;
    }

    /**
     * <pre>
     * Check SCM DB Qty.
     * </pre>
     * @param msg
     * @return
     */
    private boolean checkQty(EZDTMsg msg) {

        StringBuffer logMsg = null;
        StringBuffer key = new StringBuffer();
        String qty = null;

        // Check Qty 1 to 25.
        for (int i = 1; i <= 25; i++) {

            // Create msg key.
            key.append("scmDbQty_");
            if (i < 10) {
                key.append("0");
            }
            key.append(String.valueOf(i));
            qty = msg.getValueString(key.toString(), 0);

            // error
            if (ZYPCommonFunc.hasValue(qty)
                    && qty.indexOf("#") >= 0) {

                logMsg = new StringBuffer();
                logMsg.append(key);
                logMsg.append(" is overflow. Skip MDSE_CD=");
                logMsg.append(msg.getValueString("mdse12Cd", 0));
                logMsg.append(".");
                EZDDebugOutput.println(CST_DEBUG_MSG_LVL, logMsg.toString(), this);
                skipCount++;

                return false;
            }

            key.delete(0, key.length());
        }

        return true;
    }

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameters.
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB015002().executeBatch(NLCB015002.class.getSimpleName());
    }

}
