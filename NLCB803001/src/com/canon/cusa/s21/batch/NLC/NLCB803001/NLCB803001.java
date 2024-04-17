package com.canon.cusa.s21.batch.NLC.NLCB803001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.AJE_MDSE_RECLS_INTFCTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;

/**
 * <Pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </Pre>
 * 
 * <Pre>
 * This batch stores front transactions in interface table.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/20/2010   CSA             K.Uramori       Create          N/A
 * 05/14/2013   CSAI            K.Uramori       Update          Modification for DS.
 * 03/31/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 02/06/2018   Hitachi         E.Kameishi      Update          QC#22714
 * </pre>
 */
public class NLCB803001 extends S21BatchMain implements NLCB803001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** Process Date */
    private static String procDt = "";

    // START 2018/02/02 E.Kameishi [QC#22714,ADD]
    /** Batch Process Date */
    private static String batProcDt;
    // END 2018/02/02 E.Kameishi [QC#22714,ADD]

    /** SSM Batch Clinent */
    private S21SsmBatchClient ssmBatchClient;

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Count */
    private int commitCount = 0;

    /** Termination Code */
    private TERM_CD termCd;

    /** Error message */
    private String errMsg = "";

    /** Array of TMsg */
    private EZDTMsg[] intfcMsgs;
    /** Array of TMsg */
    private EZDTMsg[] rmvMsgs;

    /** Size of Array */
    private int intfcMsgCount = 0;
    /** Size of Array */
    private int rmvMsgCount = 0;

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry commonJrnlEntry = new NFACommonJrnlEntry();

    /**
     * @param args Argument from a batch shell. 
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        /* Initialize S21BatchMain */
        new NLCB803001().executeBatch(NLCB803001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }


    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        procDt = S21BatchUtil.getInputParam1();

        // if it's set by the parameter, it should not be overwritten
        if (procDt == null || procDt.equals("")) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }
        // START 2018/02/02 E.Kameishi [QC#22714,ADD]
        this.batProcDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        // END 2018/02/02 E.Kameishi [QC#22714,ADD]
        // initialize
        intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");

    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> targetData = getAjeMdseReclsIntfcListNotJrnlized();

        if (targetData.size() > 0) {

            if (removeAjeMdseReclsIntfcNotJrnlized(targetData)) {
                commit();
            } else {
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
            }

        }

        if (!doEntryToAjeMdseRvalIntfc()) {
            commitCount = 0;
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
        } else {
            commit();
        }

        S21InfoLogOutput.println("mainRoutine Method End");

    }

    /**
     * <pre>
     *  Get AJE Interface Key List that is not journalized from AJE Interface Control.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAjeMdseReclsIntfcListNotJrnlized() {

        // ** Get All Transaction Pattern in AJE Merchandise Reclass Interface. **
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParam.put("jrnlCpltFlg", FLG_ON_Y);
        queryParam.put("trxRsnCdRcls", TRX_RSN_CD_08_VAL);
        queryParam.put("trxRsnCdRvsl", TRX_RSN_CD_09_VAL);
        queryParam.put("glDt", procDt);
        queryParam.put("glDtY", ZYPDateUtil.addDays(procDt, -1));

        List<Map> ssmRes = (List<Map>) ssmBatchClient.queryObjectList("getAjeMdseReclsIntfcListNotJrnlized", queryParam);

        return ssmRes;
    }

    /**
     * <pre>
     *  Remove AJE Merchandise Inventory Revaluation Interface that is not journalized.
     * </pre>
     * @param List<Map> AJE Interface Key List that is not journalized.
     * @return boolean true: OK  false: NG
     */
    private boolean removeAjeMdseReclsIntfcNotJrnlized(List<Map> ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

        try {
            for (Map<String, Object> rsAjeIntfcCtrlListNotJrnlized : ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

                AJE_MDSE_RECLS_INTFCTMsg ajeMdseReclsIntfc = new AJE_MDSE_RECLS_INTFCTMsg();
                String glblCmpyCdStr = (String) rsAjeIntfcCtrlListNotJrnlized.get(GLBL_CMPY_CD);
                BigDecimal ajeMdseReclsIntfcPkNum = new BigDecimal(rsAjeIntfcCtrlListNotJrnlized.get(AJE_MDSE_RECLS_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "glblCmpyCd", glblCmpyCdStr);
                commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "ajeMdseReclsIntfcPk", ajeMdseReclsIntfcPkNum);

                removeAjeIntfcNotJrnlizedHelper(ajeMdseReclsIntfc);

            }

            if (rmvMsgCount != 0) {
                removeAjeIntfcNotJrnlizedHelper(null);
            }
        } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
            errMsg = ex.getMessage();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * <pre>
     *  Remove AJE Interface Control per a certain amount of records.
     *  @param rmvRec TMsg to be removed
     *  @throws JrnlCommonException JrnlCommonException
     * </pre>
     */
    private void removeAjeIntfcNotJrnlizedHelper(EZDTMsg rmvRec) throws NFACommonJrnlEntry.JrnlCommonException {

        if (rmvRec != null) {
            rmvMsgs[rmvMsgCount] = rmvRec;
            rmvMsgCount += 1;
        } else {
            rmvMsgs = commonJrnlEntry.changeArraySize(rmvMsgs, rmvMsgCount);
        }

        // per 10000 lines
        if (rmvMsgCount >= BULK_INSERT_COUNT || rmvRec == null) {

            int retCnt = S21FastTBLAccessor.removePhysical(rmvMsgs);

            // if passed records' count and return count don't match, error
            if (retCnt != rmvMsgCount) {
                throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
            }
            // initialize
            rmvMsgCount = 0;
            rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        }
    }

    /**
     * <pre>
     *  Store Target Transactions in AJE Merchandise Inventory Revaluation Interface.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private boolean doEntryToAjeMdseRvalIntfc() {

        // ** Get Target Transactions in ASSET_TRX. **        
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("assetCd", ASSET_CD_VAL);
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParam.put("jrnlCpltFlg", FLG_ON_Y);
        queryParam.put("trxRsnCdRcls", TRX_RSN_CD_08_VAL);
        queryParam.put("trxRsnCdRvsl", TRX_RSN_CD_09_VAL);
        queryParam.put("glDt", procDt);
        queryParam.put("glDtY", ZYPDateUtil.addDays(procDt, -1));
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("stdCost", MDSE_COST_TP.STANDARD_COST);
        //-- start add QC#16819 2017/01/04
        queryParam.put("sysSrcCd", SYS_SRC.S21_LOGISTICS);
        queryParam.put("trxCd", TRX.COST_CALCULATION);
        //-- end 2017/01/04

        Boolean checkEntryToAjeMdseRvalIntfc = (Boolean) ssmBatchClient.queryObject("getMdseReclsNotJrnlized", queryParam, new AjeMdseRvalIntfcHandler());

        return checkEntryToAjeMdseRvalIntfc;
    }

    /**
     * <pre>
     *  AJE Merchandise Inventory Revaluation .
     *  Store Target Transactions in AJE Merchandise Inventory Revaluation Interface.
     * </pre>
     */
    private class AjeMdseRvalIntfcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rsMdseReclsNotJrnlized) throws SQLException {

            Boolean rtn = doProcessLoop(rsMdseReclsNotJrnlized);
            
            // for trx_rsn_cd = '08' or '10'
            /*Boolean rtn = doProcessLoop(rsMdseReclsNotJrnlized, TRX_RSN_CD_08_VAL);

            // for trx_rsn_cd = '09' or '11'
            if (rtn) {
                // initialize
                rsMdseReclsNotJrnlized.beforeFirst();
                intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
                intfcMsgCount = 0;
                rtn = doProcessLoop(rsMdseReclsNotJrnlized, TRX_RSN_CD_09_VAL);
            }
            */

            return rtn;
        }

        protected boolean doProcessLoop(ResultSet rsMdseReclsNotJrnlized) {
            try {

                while (rsMdseReclsNotJrnlized.next()) {

                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_MDSE_RECLS_INTFC_SQ);

                    AJE_MDSE_RECLS_INTFCTMsg ajeMdseReclsIntfc = new AJE_MDSE_RECLS_INTFCTMsg();
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "glblCmpyCd", rsMdseReclsNotJrnlized.getString(GLBL_CMPY_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "ajeMdseReclsIntfcPk", seqNum);
                    // START 2018/02/02 E.Kameishi [QC#22714,MOD]
                    String trxRsnCd = rsMdseReclsNotJrnlized.getString(TRX_RSN_CD);
                    if (TRX_RSN_CD_09_VAL.equals(trxRsnCd)) {
                        commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "glDt", addMonths(batProcDt, 1));
                    } else {
                        commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "glDt", rsMdseReclsNotJrnlized.getString(INVTY_SNAP_SHOT_DT));
                    }
                    // END 2018/02/02 E.Kameishi [QC#22714,MOD]
                    
                    /* System Souce Code is decided in AJE Interface Process.*/
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "sysSrcCd", SYS_SRC.S21_LOGISTICS);

                    /* Transaction Code is decided in AJE Interface Process.*/
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "trxCd", TRX.COST_CALCULATION);

                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "trxRsnCd", rsMdseReclsNotJrnlized.getString(TRX_RSN_CD));
                    
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "invtySnapShotPk", rsMdseReclsNotJrnlized.getBigDecimal(INVTY_SNAP_SHOT_PK));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "invtySnapShotDt", rsMdseReclsNotJrnlized.getString(INVTY_SNAP_SHOT_DT));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "mdseCd", rsMdseReclsNotJrnlized.getString(MDSE_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "invtyQty", rsMdseReclsNotJrnlized.getBigDecimal(INVTY_QTY));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "locStsCd", rsMdseReclsNotJrnlized.getString(LOC_STS_CD));

                    setValue(ajeMdseReclsIntfc.coaAcctCd, rsMdseReclsNotJrnlized.getString(COA_ACCT_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "coaProdCd", rsMdseReclsNotJrnlized.getString(COA_PROD_CD));
                    setValue(ajeMdseReclsIntfc.coaAfflCd, rsMdseReclsNotJrnlized.getString(COA_AFFL_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "costCcyCd", rsMdseReclsNotJrnlized.getString(COST_CCY_CD));

                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "vndCd", rsMdseReclsNotJrnlized.getString(VND_CD));

                    commonJrnlEntry.setFieldValue(ajeMdseReclsIntfc, "reclsAmt", rsMdseReclsNotJrnlized.getBigDecimal(RECLS_AMT));
                    
                    
                    // CSA New
                    setValue(ajeMdseReclsIntfc.invtyLocCd, rsMdseReclsNotJrnlized.getString(INVTY_LOC_CD));
                    setValue(ajeMdseReclsIntfc.coaBrCd, rsMdseReclsNotJrnlized.getString(COA_BR_CD));
                    setValue(ajeMdseReclsIntfc.coaCcCd, rsMdseReclsNotJrnlized.getString(COA_CC_CD));
                    setValue(ajeMdseReclsIntfc.coaChCd, rsMdseReclsNotJrnlized.getString(COA_CH_CD));
                    setValue(ajeMdseReclsIntfc.coaProjCd, rsMdseReclsNotJrnlized.getString(COA_PROJ_CD));
                    setValue(ajeMdseReclsIntfc.coaExtnCd, rsMdseReclsNotJrnlized.getString(COA_EXTN_CD));
                    setValue(ajeMdseReclsIntfc.rtlWhCd, rsMdseReclsNotJrnlized.getString(RTL_WH_CD));
                    setValue(ajeMdseReclsIntfc.rtlSwhCd, rsMdseReclsNotJrnlized.getString(RTL_SWH_CD));
                    setValue(ajeMdseReclsIntfc.mdseCostTpCd, rsMdseReclsNotJrnlized.getString(MDSE_COST_TP_CD));
                    setValue(ajeMdseReclsIntfc.mdseInvtyCostPct, rsMdseReclsNotJrnlized.getBigDecimal(MDSE_INVTY_COST_PCT));
                    
                    createAjeMdseReclsIntfc(ajeMdseReclsIntfc);

                }

                if (intfcMsgCount != 0) {
                    createAjeMdseReclsIntfc(null);
                }

            } catch (EZDAbendException exEz) {  // null exception
                errMsg = exEz.getMessage();
                return Boolean.FALSE;
            } catch (S21AbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException sqlEx) {
                errMsg = sqlEx.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException exJ) {
                errMsg = exJ.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        /**
         * @param BigDecimal Invoice Quantity
         * @param BigDecimal this month standard cost amount
         * @return BigDecimal Calculated Revaluation Total Amount 
         */
        protected BigDecimal calculateReclsAmount(BigDecimal invtyQtyParam, BigDecimal calculatedReclsAmountParam) {

            BigDecimal calculatedReclsAmount = commonJrnlEntry.checkNull(invtyQtyParam).multiply(commonJrnlEntry.checkNull(calculatedReclsAmountParam));
            return calculatedReclsAmount;
        }

        /**
         * @param EZDTMsg AJE Merchandise Inventory Revaluation Interface.
         * @throws JrnlCommonException JrnlCommonException
         */
        protected void createAjeMdseReclsIntfc(EZDTMsg ajeMdseReclsIntfc) throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeMdseReclsIntfc != null) {
                intfcMsgs[intfcMsgCount] = ajeMdseReclsIntfc;
                intfcMsgCount += 1;

            } else {  // array may be not full
                intfcMsgs = commonJrnlEntry.changeArraySize(intfcMsgs, intfcMsgCount);
            }

            // per 10000 lines
            if (intfcMsgCount >= BULK_INSERT_COUNT || ajeMdseReclsIntfc == null) {

                int retCnt = S21FastTBLAccessor.insert(intfcMsgs);

                // if passed records' count and return count don't match, error
                if (retCnt != intfcMsgCount) {
                    throw commonJrnlEntry.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += intfcMsgCount;
                // initialize
                intfcMsgCount = 0;
                intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }

    }
    
    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

    // START 2018/02/02 E.Kameishi [QC#22714,ADD]
    /**
     * addMonths
     * @param String date
     * @param int addMth
     * @return String
     */
    private String addMonths(String date, int addMth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, 01);

        calendar.add(Calendar.MONTH, addMth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String addMthYrMth = dateFormat.format(calendar.getTime());

        return addMthYrMth;
    }
    // END 2018/02/02 E.Kameishi [QC#22714,ADD]

}
