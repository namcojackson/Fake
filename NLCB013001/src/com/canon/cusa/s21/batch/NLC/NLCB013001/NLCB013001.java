/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB013001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import business.db.STK_IN_RSLT_WRKTMsg;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
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
 *          User Variable1(Commit Count for Bulk insert)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/19/2010   Fujitsu         M.Yamada        Create          N/A
 * 01/19/2010   Fujitsu         S.Yoshida       Update          RQ1352
 * 09/27/2010   Fujitsu         M.Yamada        Update          ATLAS
 * 09/17/2013   Fujitsu         K.Fujita        Update          MEX-IF020
 * 11/20/2013   Fujitsu         K.Fujita        Update          Def3019
 * 07/01/2016   CITS            T.Kikuhara      Update          Ver2.0
 * </pre>
 */
public class NLCB013001 extends S21BatchMain {

    //-----09/17/2013 Delete Start
//    private static final int SIZE_IMPT_INV_DO_NUM = 14;
    //-----09/17/2013 Delete End

    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;

    /** Message ID : ZZM9000E The field of [@] is not input. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /** The value which is not numerical was input to the field of [@]. */
    private static final String MSG_ID_ZZM9004E = "ZZM9004E";

    /** ZZZM9026E :   */
    private static final String MSG_ID_ZZZM9026E = "ZZZM9026E";

    /** Message ID : ZZBM0009I */
    private static final String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** Message ID : NLCM0053E The process abended. */
    private static final String MSG_ID_NLCM0053E = "NLCM0053E";

    //-----09/17/2013 Delete Start
//    /** Message ID : NLCM0065E Entered @ is invalid. */
//    private static final String MSG_ID_NLCM0065E = "NLCM0065E";
    //-----09/17/2013 Delete End

    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Commit Count */
    private static final String MSG_STR_COMMIT_COUNT = "Commit Count(VAR_USER1)";

    /** Message string : Technician Inventory included Flag in SCMDB IF */
    private static final String MSG_STR_MDSE_INCL_TECH_INVTY_CINC_FLG = "Technician Inventory included Flag in SCMDB IF";

    /** Table ID : INVTY_TRX */
    private static final String TABLE_ID_INVTY_TRX = "INVTY_TRX";

    /** Table ID : STK_IN_RSLT_WRK */
    private static final String TABLE_ID_STK_IN_RSLT_WRK = "STK_IN_RSLT_WRK";

    /** Colmn Name : INVTY_TRX_PK */
    private static final String COL_NM_INVTY_TRX_PK = "INVTY_TRX_PK";

    /** Colmn Name : MDSE_CD */
    private static final String COL_NM_MDSE_CD = "MDSE_CD";

    /** Colmn Name : VND_GLBL_CMPY_CD */
    private static final String COL_NM_VND_GLBL_CMPY_CD = "VND_GLBL_CMPY_CD";

    /** Colmn Name : INVTY_LOC_CD */
    private static final String COL_NM_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Colmn Name : INVTY_TRX_QTY */
    private static final String COL_NM_INVTY_TRX_QTY = "INVTY_TRX_QTY";

    /** Colmn Name : TRX_CD */
    private static final String COL_NM_TRX_CD = "TRX_CD";

    /** Colmn Name : TRX_RSN_CD */
    private static final String COL_NM_TRX_RSN_CD = "TRX_RSN_CD";

    /** Colmn Name : IMPT_INV_DO_NUM */
    private static final String COL_NM_IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";

    //-----09/17/2013 Delete Start
//    /** Colmn Name : IMPT_INV_NUM */
//    private static final String COL_NM_IMPT_INV_NUM = "IMPT_INV_NUM";
    //-----09/17/2013 Delete End

    /** Colmn Name : IMPT_CNTNR_NUM */
    private static final String COL_NM_IMPT_CNTNR_NUM = "IMPT_CNTNR_NUM";

    /** Colmn Name : INVTY_TRX_DT */
    private static final String COL_NM_INVTY_TRX_DT = "INVTY_TRX_DT";

    //-----09/17/2013 Delete Start
//    /** Colmn Name : INVTY_TRX_SLP_NUM */
//    private static final String COL_NM_INVTY_TRX_SLP_NUM = "INVTY_TRX_SLP_NUM";
//
//    /** Colmn Name : PO_RCV_NUM */
//    private static final String COL_NM_PO_RCV_NUM = "PO_RCV_NUM";
//
//    /** Colmn Name : INVTY_TRX_REF_NUM */
//    private static final String COL_NM_INVTY_TRX_REF_NUM = "INVTY_TRX_REF_NUM";
//
//    /** Colmn Name : CPO_ORD_NUM */
//    private static final String COL_NM_CPO_ORD_NUM = "CPO_ORD_NUM";
//
//    /** Colmn Name : ROSS_ORD_NUM */
//    private static final String COL_NM_ROSS_ORD_NUM = "ROSS_ORD_NUM";
//
//    /** Colmn Name : INV_NUM */
//    private static final String COL_NM_INV_NUM = "INV_NUM";
//
//    /** Colmn Name : VND_RTRN_NUM */
//    private static final String COL_NM_VND_RTRN_NUM = "VND_RTRN_NUM";
//
//    /** Colmn Name : EXPT_INV_NUM */
//    private static final String COL_NM_EXPT_INV_NUM = "EXPT_INV_NUM";
    //-----09/17/2013 Delete End

    // START 2013/11/20 K.Fujita [Defect#3019 Add]
    /** Varchar Const Search Key: Technician Inventory included Flag in SCMDB IF */
    public static final String VC_KEY_MDSE_INCL_TECH_INVTY_CINC_FLG = "MDSE_INCL_TECH_INVTY_CINC_FLG";

    // END   2013/11/20 K.Fujita [Defect#3019 Add]

    /** S21UserProfileService */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Fetch Size */
    private static final int FETCH_SIZE = 1000;

    /** Default Commit Count */
    private static final String DEFAULT_COMMIT_COUNT = "1000";

    /** Default Global Company Code */
    private static final String DEFAULT_VND_GLBL_CMPY_CD = "000";

    //-----09/17/2013 Delete Start
//    /** Hyphen */
//    private static final String STR_HYPHEN = "-";
//
//    /** Length of 1st Part of DO# XXXXXXXXX-@@@b value:9 */
//    private static final int LENGTH_1ST_PART_DO_NUM = 9;
//
//    /** Length of 2nd Part of DO# XXXXXXX-@@@b value:1 */
//    private static final int LENGTH_2ND_PART_DO_NUM = 1;
//
//    /** Length of 2nd Part Offset of DO# XXXXXXX-@@@b value:9 */
//    private static final int LENGTH_2ND_PART_OFST_DO_NUM = 9;
//
//    /** Length of 3rd Part of DO# XXXXXXX-@@@b value:4 */
//    private static final int LENGTH_3RD_PART_DO_NUM = 4;
//
//    /** Length of 3rd Part Offset of DO# XXXXXXX-@@@b value:10 */
//    private static final int LENGTH_3RD_PART_OFST_DO_NUM = 10;
//
//    
//    /** Length of 1st Part of DO# XXXXXXX-@@b value:7 */
//    private static final int LENGTH_1ST_PART_DO_NUM_NL = 7;
//
//    /** Length of 2nd Part of DO# XXXXXXX-@@b value:1 */
//    private static final int LENGTH_2ND_PART_DO_NUM_NL = 1;
//
//    /** Length of 2nd Part Offset of DO# XXXXXXX-@@b value:7 */
//    private static final int LENGTH_2ND_PART_OFST_DO_NUM_NL = 7;
//
//    /** Length of 3rd Part of DO# XXXXXXX-@@b value:3 */
//    private static final int LENGTH_3RD_PART_DO_NUM_NL = 3;
//
//    /** Length of 3rd Part Offset of DO# XXXXXXX-@@b value:8 */
//    private static final int LENGTH_3RD_PART_OFST_DO_NUM_NL = 8;
    //-----09/17/2013 Delete End

    /** Termination code */
    private TERM_CD termCd;

    /** Batch Proc Date */
    private String batProcDate;

    /** The number of cases : Select */
    private int selectCount;

    /** Commit Count */
    private int commitCount = Integer.valueOf(DEFAULT_COMMIT_COUNT).intValue();

    /** The number of cases : Insert to STK_IN_RSLT_WRK */
    private int insertCountStkInRsltWrk;

    // START 2013/11/20 K.Fujita [Defect#3019 Add]

    /** Technician Inventory included Flag in SCMDB IF */
    private String techInvIcldFlg;

    // END   2013/11/20 K.Fujita [Defect#3019 Add]

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Initialization of variable
        selectCount = 0;
        insertCountStkInRsltWrk = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // Check on input parameter
        checkParameter();

    }

    @Override
    protected void mainRoutine() {

        // Get operation date
        batProcDate = ZYPDateUtil.getBatProcDate(profile.getGlobalCompanyCode().trim());

        insertWorkRecords();
    }

    @Override
    protected void termRoutine() {

        // The number of cases : Select is output
        String[] tmp1 = {TABLE_ID_INVTY_TRX, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp1);
        // The number of cases : Insert is output
        String[] tmp2 = {TABLE_ID_STK_IN_RSLT_WRK, "Insert", Integer.toString(insertCountStkInRsltWrk) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp2);

        // Setting of termination code
        setTermState(termCd, insertCountStkInRsltWrk, 0, selectCount);

    }

    /**
     * @param args argument
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB013001().executeBatch(NLCB013001.class.getSimpleName());

    }

    /**
     * Check processing of input parameter
     */
    private void checkParameter() {

        glblCmpyCd = profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            String[] tmp = {MSG_STR_COMP_CODE };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

        String str = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(str)) {
            String[] tmp = {MSG_STR_COMMIT_COUNT };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        if (str.matches("[0-9]+")) {
            commitCount = Integer.valueOf(str).intValue();
        } else {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {MSG_STR_COMMIT_COUNT + "(" + str + ")" });
        }

        // START 2013/11/20 K.Fujita [Defect#3019 Add]
        techInvIcldFlg = ZYPCodeDataUtil.getVarCharConstValue(VC_KEY_MDSE_INCL_TECH_INVTY_CINC_FLG, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(techInvIcldFlg)) {
            String[] tmp = {MSG_STR_MDSE_INCL_TECH_INVTY_CINC_FLG };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        } else if (!ZYPConstant.FLG_ON_Y.equals(techInvIcldFlg) && !ZYPConstant.FLG_OFF_N.equals(techInvIcldFlg)) {
            String[] tmp = {MSG_STR_MDSE_INCL_TECH_INVTY_CINC_FLG };
            throw new S21AbendException(MSG_ID_ZZZM9026E, tmp);
        }
        // END   2013/11/20 K.Fujita [Defect#3019 Add]


    }

    /**
     * Insert Work Records from Inventory Transaction.
     */
    private void insertWorkRecords() {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("invtyTrxDtTo", getTrxDtTo());
        params.put("trxCdPurStkIn", TRX.PURCHASE_STOCK_IN);
        params.put("trxRsnPurStkIn", TRX_RSN.PURCHASE_STOCK_IN);
        params.put("trxRsnExpVndRtn", TRX_RSN.EXPORT_VENDOR_RETURN);
        params.put("trxRsnDomVndRtn", TRX_RSN.DOMESTIC_VENDOR_RETURN);
        params.put("trxCdMovement", TRX.MOVEMENT);
        params.put("trxRsnWHTrnsStkIn", TRX_RSN.WAREHOUSE_TRANSFER_STOCK_IN);
        //-----09/17/2013 Add Start
        params.put("mdseRgtnTpCd", MDSE_RGTN_TP.MERCURY);
        //-----09/17/2013 Add End

        // START 2013/11/20 K.Fujita [Defect#3019 Add]

        params.put("techInvIcldFlg", techInvIcldFlg);
//        params.put("techInvIcldFlgN", ZYPConstant.FLG_OFF_N);
        params.put("locTpCdTechnician", LOC_TP.TECHNICIAN);
       // END   2013/11/20 K.Fujita [Defect#3019 Add]

        // START 2016/07/01 T.Kikuhara V2.0
        params.put("FLG_Y", ZYPConstant.FLG_ON_Y);
        // END 2016/07/01 T.Kikuhara V2.0

        insertWork("getWorkData", params);

        return;
    }

    /**
     * Get Transaction Date To (SQL Select Condition).
     */
    private String getTrxDtTo() {

        return batProcDate;
    }

    /**
     * <pre>
     * Insert the Stock-In Result Work. 
     * </pre>
     * 
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void insertWork(String key, Map<String, Object> params) {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            List<STK_IN_RSLT_WRKTMsg> stkInRsltWrkList = new ArrayList<STK_IN_RSLT_WRKTMsg>();
            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement(key, params, execParam);
            rs = stmt.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (rs.next()) {

                stkInRsltWrkList.add(getStkInRsltWrk(rs));
                selectCount++;
                cnt++;

                if (cnt == commitCount) {
                    execCnt = S21FastTBLAccessor.insert((STK_IN_RSLT_WRKTMsg[]) stkInRsltWrkList.toArray(new STK_IN_RSLT_WRKTMsg[0]));
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    insertCountStkInRsltWrk += cnt;
                    cnt = 0;
                    stkInRsltWrkList.clear();
                }
            }
            if (cnt > 0) {
                execCnt = S21FastTBLAccessor.insert((STK_IN_RSLT_WRKTMsg[]) stkInRsltWrkList.toArray(new STK_IN_RSLT_WRKTMsg[0]));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                insertCountStkInRsltWrk += cnt;
                stkInRsltWrkList = null;
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * <pre>Get Stock-In Result Work TMsg.</pre>
     * 
     * @param rs Result Set.
     */
    private STK_IN_RSLT_WRKTMsg getStkInRsltWrk(ResultSet rs) throws SQLException {

        STK_IN_RSLT_WRKTMsg stkInRsltWrkTMsg = new STK_IN_RSLT_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.invtyTrxPk, rs.getBigDecimal(COL_NM_INVTY_TRX_PK));
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.mdseCd, rs.getString(COL_NM_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.localMdseCd, rs.getString(COL_NM_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.vndGlblCmpyCd, getVendorGlobalCompanyCode(rs));
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.whCd, rs.getString(COL_NM_INVTY_LOC_CD));

        BigDecimal qty = rs.getBigDecimal(COL_NM_INVTY_TRX_QTY);
        String trxCd = rs.getString(COL_NM_TRX_CD);
        String trxRsnCd = rs.getString(COL_NM_TRX_RSN_CD);

        if (isPurchaseStockIn(trxCd, trxRsnCd)) {
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.wrkDbQty_01, qty);
            //-----09/17/2013 Update Start
//            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptInvDoNum, getDoNum(rs));
//            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptInvNum, getInvoiceNum(rs));
//            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptCntnrNum, getContainerNum(rs));
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptInvDoNum, "");
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptInvNum, "");
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptCntnrNum, "");

            //-----09/17/2013 Update End
        } else {

            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.wrkDbQty_01, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptInvDoNum, "");
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptInvNum, "");
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.imptCntnrNum, "");
        }
        if (isMovement(trxCd, trxRsnCd)) {
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.wrkDbQty_02, qty);
        } else {
            ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.wrkDbQty_02, BigDecimal.ZERO);
        }

        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.trxDt, rs.getString(COL_NM_INVTY_TRX_DT));
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.trxCd, trxCd);
        ZYPEZDItemValueSetter.setValue(stkInRsltWrkTMsg.trxRsnCd, trxRsnCd);

        return stkInRsltWrkTMsg;
    }

    /**
     * <pre>
     * If Purchase Stock-In / Purchase Stock-In, Export Vendor Return, Domestic Vendor Return,
     * then true.
     * </pre>
     * 
     * @param trxCd Trunsaction Code.
     * @param trxRsnCd Trunsaction Reason Code.
     */
    private boolean isPurchaseStockIn(String trxCd, String trxRsnCd) {

        if (TRX.PURCHASE_STOCK_IN.equals(trxCd)) {
            if (TRX_RSN.PURCHASE_STOCK_IN.equals(trxRsnCd) || TRX_RSN.EXPORT_VENDOR_RETURN.equals(trxRsnCd) || TRX_RSN.DOMESTIC_VENDOR_RETURN.equals(trxRsnCd)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * If Movement / Warehouse Transfer Stock-In,
     * then true.
     * </pre>
     * 
     * @param trxCd Trunsaction Code.
     * @param trxRsnCd Trunsaction Reason Code.
     */
    private boolean isMovement(String trxCd, String trxRsnCd) {

        if (TRX.MOVEMENT.equals(trxCd)) {
            if (TRX_RSN.WAREHOUSE_TRANSFER_STOCK_IN.equals(trxRsnCd)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * get Vendor Global Company Code
     * </pre>
     * 
     * @param rs Result Set
     */
    private String getVendorGlobalCompanyCode(ResultSet rs) throws SQLException {

        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_VND_GLBL_CMPY_CD))) {
            return rs.getString(COL_NM_VND_GLBL_CMPY_CD);
        }
        return DEFAULT_VND_GLBL_CMPY_CD;
    }

    /**
     * <pre>
     * get D/O# 
     * </pre>
     * 
     * @param rs Result Set
     */
    //-----09/17/2013 Delete Start
//    private String getDoNum(ResultSet rs) throws SQLException {
//
//        String tmpDoNum = ZYPCommonFunc.rightPad(
//                rs.getString(COL_NM_IMPT_INV_DO_NUM)
//                , SIZE_IMPT_INV_DO_NUM
//                , " ");
//        if (!ZYPCommonFunc.hasValue(tmpDoNum)) {
//            return "";
//        }
//
//        int ix_hypen = tmpDoNum.indexOf(STR_HYPHEN, LENGTH_1ST_PART_DO_NUM_NL - 1);
//        String tmpDoNum1 = "";
//        String tmpDoNum2 = "";
//        String tmpDoNum3 = "";
//        if(ix_hypen > 0){
//            if(ix_hypen == LENGTH_1ST_PART_DO_NUM){
//                tmpDoNum1 = S21StringUtil.subStringByLength(tmpDoNum, 0, LENGTH_1ST_PART_DO_NUM);
//                tmpDoNum2 = S21StringUtil.subStringByLength(tmpDoNum, LENGTH_2ND_PART_OFST_DO_NUM, LENGTH_2ND_PART_DO_NUM);
//                tmpDoNum3 = S21StringUtil.subStringByLength(tmpDoNum, LENGTH_3RD_PART_OFST_DO_NUM, LENGTH_3RD_PART_DO_NUM);
//            }else{
//                tmpDoNum1 = S21StringUtil.subStringByLength(tmpDoNum, 0, LENGTH_1ST_PART_DO_NUM_NL);
//                tmpDoNum2 = S21StringUtil.subStringByLength(tmpDoNum, LENGTH_2ND_PART_OFST_DO_NUM_NL, LENGTH_2ND_PART_DO_NUM_NL);
//                tmpDoNum3 = S21StringUtil.subStringByLength(tmpDoNum, LENGTH_3RD_PART_OFST_DO_NUM_NL, LENGTH_3RD_PART_DO_NUM_NL);
//            }
//            if (STR_HYPHEN.equals(tmpDoNum2)) {
//                tmpDoNum2 = "";
//            }
//        }else{
//            tmpDoNum1 = tmpDoNum.trim();
//        }
//
//        String doNum = S21StringUtil.concatStrings(tmpDoNum1, tmpDoNum2, tmpDoNum3.trim());
//        if(doNum.length() > 12){
//            S21InfoLogOutput.println(MSG_ID_NLCM0065E,new String[] {"DO_NUM:" + doNum});
//        }
//        return S21StringUtil.concatStrings(tmpDoNum1, tmpDoNum2, tmpDoNum3.trim());
//    }
    //-----09/17/2013 Delete End

    /**
     * <pre>
     * get Invoice# 
     * </pre>
     * 
     * @param rs Result Set
     */
    //-----09/17/2013 Delete Start
//    private String getInvoiceNum(ResultSet rs) throws SQLException {
//
//        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_IMPT_INV_NUM))) {
//            return rs.getString(COL_NM_INVTY_TRX_SLP_NUM);
//        }
//
//        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_PO_RCV_NUM))) {
//            return rs.getString(COL_NM_INVTY_TRX_REF_NUM);
//        }
//
//        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_CPO_ORD_NUM))) {
//            return rs.getString(COL_NM_INV_NUM);
//        }
//
//        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_ROSS_ORD_NUM))) {
//            return rs.getString(COL_NM_ROSS_ORD_NUM);
//        }
//
//        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_VND_RTRN_NUM))) {
//            return rs.getString(COL_NM_EXPT_INV_NUM);
//        }
//
//        return rs.getString(COL_NM_INVTY_TRX_SLP_NUM);
//    }
    //-----09/17/2013 Delete End

    /**
     * <pre>
     * get Container# 
     * </pre>
     * 
     * @param rs Result Set
     */
    private String getContainerNum(ResultSet rs) throws SQLException {

        if (ZYPCommonFunc.hasValue(rs.getString(COL_NM_IMPT_INV_DO_NUM))) {
            return rs.getString(COL_NM_IMPT_CNTNR_NUM);
        }

        return "";
    }

}
