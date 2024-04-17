package com.canon.cusa.s21.batch.NLC.NLCB802001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.AJE_MDSE_RVAL_INTFCTMsg;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;


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
 * 08/21/2009   CSA             H.Naoi          Create          N/A
 * 05/14/2013   CSAI            K.Uramori       Update          Modification for DS.
 * 02/11/2016   CSAI            K.Uramori       Update          Modification for CSA. Reval to be created daily basis.
 * 08/09/2021   CITS            G.Delgado       Update          QC#59046
 * 09/01/2021   CITS            D.Morimoto      Update          QC#59166
 * </pre>
 */
public class NLCB802001 extends S21BatchMain implements NLCB802001Constant, ZYPConstant, NFACommonJrnlEntryConstant {

    /** Process Date */
    private static String procDt = "";

    /** Process Date : YYYYMMDD */
    private String procYrMth;

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
        new NLCB802001().executeBatch(NLCB802001.class.getSimpleName());

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
        this.procYrMth = ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyyMM");

        // initialize
        intfcMsgs = new EZDTMsg[BULK_INSERT_COUNT];
        rmvMsgs = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");
        
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map> rtGetAjeMdseRvalIntfcKeyListNotJrnlized = getAjeMdseRvalIntfcListNotJrnlized();

        Boolean checkRemoveProcess = Boolean.TRUE;
        if (rtGetAjeMdseRvalIntfcKeyListNotJrnlized.size() > 0) {
            checkRemoveProcess = removeAjeMdseRvalIntfcNotJrnlized(rtGetAjeMdseRvalIntfcKeyListNotJrnlized);

            if (checkRemoveProcess) {
                commit();
            } else {
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
            }

        }

        if (checkRemoveProcess) {
            boolean checkDoEntryToAjeMdseRvalIntfcProcess = doEntryToAjeMdseRvalIntfc();

            if (!checkDoEntryToAjeMdseRvalIntfcProcess) {
                commitCount = 0;
                rollback();
                throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg});
            } else {
                commit();
            }
        }

        S21InfoLogOutput.println("mainRoutine Method End");

    }

    /**
     * <pre>
     *  Get AJE Interface Key List that is not journalized from AJE Interface Control.
     * </pre>
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAjeMdseRvalIntfcListNotJrnlized() {

        // ** Get All Transaction Pattern in AJE Merchandise Inventory Revaluation Interface. **
        Map<String, String>queryParamToGetAjeMdseRvalIntfcKeyListNotJrnlized = new HashMap<String, String>();
        queryParamToGetAjeMdseRvalIntfcKeyListNotJrnlized.put("glblCmpyCd", this.glblCmpyCd);
        queryParamToGetAjeMdseRvalIntfcKeyListNotJrnlized.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParamToGetAjeMdseRvalIntfcKeyListNotJrnlized.put("jrnlCpltFlg", FLG_ON_Y);
        queryParamToGetAjeMdseRvalIntfcKeyListNotJrnlized.put("batProcDt", procDt);
        
        List<Map> ssmResForGetAjeMdseRvalIntfcKeyListNotJrnlized = (List<Map>) ssmBatchClient.queryObjectList("getAjeMdseRvalIntfcListNotJrnlized", queryParamToGetAjeMdseRvalIntfcKeyListNotJrnlized);

        return ssmResForGetAjeMdseRvalIntfcKeyListNotJrnlized;
    }

    /**
     * <pre>
     *  Remove AJE Merchandise Inventory Revaluation Interface that is not journalized.
     * </pre>
     * @param List<Map> AJE Interface Key List that is not journalized.
     * @return boolean true: OK  false: NG
     */
    private boolean removeAjeMdseRvalIntfcNotJrnlized(List<Map> ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

        try {
            for (Map<String, Object> rsAjeIntfcCtrlListNotJrnlized : ssmResForGetAjeIntfcCtrlKeyNotJrnlized) {

                AJE_MDSE_RVAL_INTFCTMsg ajeMdseRvalIntfc = new AJE_MDSE_RVAL_INTFCTMsg();
                String glblCmpyCdStr = (String) rsAjeIntfcCtrlListNotJrnlized.get(GLBL_CMPY_CD);
                BigDecimal ajeMdseRvalIntfcPkNum = new BigDecimal(rsAjeIntfcCtrlListNotJrnlized.get(AJE_MDSE_RVAL_INTFC_PK).toString());

                commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "glblCmpyCd", glblCmpyCdStr);
                commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "ajeMdseRvalIntfcPk", ajeMdseRvalIntfcPkNum);

                removeAjeIntfcNotJrnlizedHelper(ajeMdseRvalIntfc);

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
        queryParam.put("ajeIntfcTpCd", AJE_INTFC_TP_CD_VAL);
        queryParam.put("jrnlCpltFlg", FLG_ON_Y);
        
      //---- start CSA mod 2016/02/16
        queryParam.put("aprvd", MDSE_CST_UPD_STS.APPROVED);  //Approve
        queryParam.put("pending", MDSE_CST_UPD_STS.PENDING);  //Pending
        queryParam.put("rgtnStsCdP10", RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);  //P10
        queryParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);  //P20
        queryParam.put("batProcDt", this.procDt);
        queryParam.put("flgY", FLG_ON_Y);
        queryParam.put("flgN", FLG_OFF_N);
        queryParam.put("stdCostTp", MDSE_COST_TP.STANDARD_COST);
        queryParam.put("assetRecovCostTp", MDSE_COST_TP.ASSET_RECOVERY);
        //---- end 2016/02/16
        
        Boolean checkEntryToAjeMdseRvalIntfc = (Boolean) ssmBatchClient.queryObject("getMdseRvalNotJrnlized", queryParam, new AjeMdseRvalIntfcHandler());

        return checkEntryToAjeMdseRvalIntfc;
    }

    /**
     * <pre>
     *  AJE Merchandise Inventory Revaluation .
     *  Store Target Transactions in AJE Merchandise Inventory Revaluation Interface.
     * </pre>
     */
    private class AjeMdseRvalIntfcHandler extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rsMdseRvalNotJrnlized) throws SQLException {

            try {
                //--- start del 2016/02/11. GL_DT to be next date
                /*
                String glDt = null;
                if(procCd.equals(PROC_CD_THIS_MONTH)) {
                    glDt = procDt.substring(0,6) + "01";
                } else {
                    glDt = getNextMthFisrstDay();
                }
                */
                //---- end 2016/02/11
                
                while (rsMdseRvalNotJrnlized.next()) {

                    // START 2021/09/01 D.Morimoto [QC#59166, ADD]
                    if (TYPE_STD.equals(rsMdseRvalNotJrnlized.getString(TYPE))
                            && MDSE_COST_TP_CD_ASSET.equals(rsMdseRvalNotJrnlized.getString(MDSE_COST_TP_CD))) {
                        continue;
                   }
                    // END   2021/09/01 D.Morimoto [QC#59166, ADD]

                    BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_MDSE_RVAL_INTFC_SQ);

                    AJE_MDSE_RVAL_INTFCTMsg ajeMdseRvalIntfc = new AJE_MDSE_RVAL_INTFCTMsg();
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "glblCmpyCd", rsMdseRvalNotJrnlized.getString(GLBL_CMPY_CD));
                    
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "ajeMdseRvalIntfcPk", seqNum);
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "glDt", rsMdseRvalNotJrnlized.getString(GL_DT));

                    /* System Souce Code is decided in AJE Interface Process.*/
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "sysSrcCd", SYS_SRC.S21_LOGISTICS);

                    /* Transaction Code is decided in AJE Interface Process.*/
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "trxCd", TRX_CD_VAL);

                    /* Transaction Reason Code is decided in AJE Interface Process.*/
                    /* Revaluation : Transaction Reason "06" */
                    /* Write Down  : Transaction Reason "07" */
                    String targetTrxRsnCd = getTrxRsnCdByWrtDownFlg(rsMdseRvalNotJrnlized.getString(WRT_DOWN_FLG));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "trxRsnCd", targetTrxRsnCd);

                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "invtySnapShotPk", rsMdseRvalNotJrnlized.getBigDecimal(INVTY_SNAP_SHOT_PK));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "invtySnapShotDt", rsMdseRvalNotJrnlized.getString(INVTY_SNAP_SHOT_DT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "mdseCd", rsMdseRvalNotJrnlized.getString(MDSE_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "invtyLocCd", rsMdseRvalNotJrnlized.getString(INVTY_LOC_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "locStsCd", rsMdseRvalNotJrnlized.getString(LOC_STS_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "stkStsCd", rsMdseRvalNotJrnlized.getString(STK_STS_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "mlyCloYrMth", rsMdseRvalNotJrnlized.getString(MLY_CLO_YR_MTH));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "invtyQty", rsMdseRvalNotJrnlized.getBigDecimal(INVTY_QTY));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "assetCd", rsMdseRvalNotJrnlized.getString(ASSET_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "mdseOrPrtCd", MDSE_OR_PRT_CD_M_VAL);
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "coaProdCd", rsMdseRvalNotJrnlized.getString(COA_PROD_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "wrtDownFlg", rsMdseRvalNotJrnlized.getString(WRT_DOWN_FLG));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "thisMthFobAmt", rsMdseRvalNotJrnlized.getBigDecimal(THIS_MTH_FOB_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "thisMthInlndFrtAmt", rsMdseRvalNotJrnlized.getBigDecimal(THIS_MTH_INLND_FRT_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "thisMthIntlFrtAmt", rsMdseRvalNotJrnlized.getBigDecimal(THIS_MTH_INTL_FRT_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "thisMthImptDtyAmt", rsMdseRvalNotJrnlized.getBigDecimal(THIS_MTH_IMPT_DTY_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "thisMthInsAmt", rsMdseRvalNotJrnlized.getBigDecimal(THIS_MTH_INS_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "thisMthTotStdCostAmt", rsMdseRvalNotJrnlized.getBigDecimal(THIS_MTH_TOT_STD_COST_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "lastMthTotStdCostAmt", rsMdseRvalNotJrnlized.getBigDecimal(LAST_MTH_TOT_STD_COST_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "nextMthTotStdCostAmt", rsMdseRvalNotJrnlized.getBigDecimal(NEXT_MTH_TOT_STD_COST_AMT));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "costCcyCd", rsMdseRvalNotJrnlized.getString(COST_CCY_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "coaAcctCd", rsMdseRvalNotJrnlized.getString(COA_ACCT_CD));
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "mdseCatgCd", rsMdseRvalNotJrnlized.getString(MDSE_CATG_CD));
                    
                    commonJrnlEntry.setFieldValue(ajeMdseRvalIntfc, "totRvalAmt", rsMdseRvalNotJrnlized.getBigDecimal(RVAL_AMT));
                    
                    //---- start add 2016/02/11
                    setValue(ajeMdseRvalIntfc.rtlWhCd, rsMdseRvalNotJrnlized.getString(RTL_WH_CD));
                    setValue(ajeMdseRvalIntfc.mdseCstUpdEffFromDt, rsMdseRvalNotJrnlized.getString(MDSE_CST_UPD_EFF_FROM_DT));
                    setValue(ajeMdseRvalIntfc.assetRecovCstEffFromDt, rsMdseRvalNotJrnlized.getString(ASSET_RECOV_CST_EFF_FROM_DT));
                    setValue(ajeMdseRvalIntfc.assetRecovCostAmt, rsMdseRvalNotJrnlized.getBigDecimal(ASSET_RECOV_COST_AMT));
                    setValue(ajeMdseRvalIntfc.prevAssetRecovCostAmt, rsMdseRvalNotJrnlized.getBigDecimal(PREV_ASSET_RECOV_COST_AMT));
                    setValue(ajeMdseRvalIntfc.nextAssetRecovCostAmt, rsMdseRvalNotJrnlized.getBigDecimal(NEXT_ASSET_RECOV_COST_AMT));
                    setValue(ajeMdseRvalIntfc.rtlSwhCd, rsMdseRvalNotJrnlized.getString(RTL_SWH_CD));
                    setValue(ajeMdseRvalIntfc.mdseCostTpCd, rsMdseRvalNotJrnlized.getString(MDSE_COST_TP_CD));
                    setValue(ajeMdseRvalIntfc.mdseInvtyCostPct, rsMdseRvalNotJrnlized.getBigDecimal(MDSE_INVTY_COST_PCT));
                    setValue(ajeMdseRvalIntfc.mdseCstUpdTpCd, rsMdseRvalNotJrnlized.getString(MDSE_CST_UPD_TP_CD));
                    //---- end 2016/02/11

                    createAjeMdseRvalIntfc(ajeMdseRvalIntfc);

                }

                if (intfcMsgCount != 0) {
                    createAjeMdseRvalIntfc(null);
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
         * @param String Write Down Flag
         */
        protected String getTrxRsnCdByWrtDownFlg(String wrtDownFlgParam) {

            if (FLG_ON_Y.equals(wrtDownFlgParam)) {
                return TRX_RSN_CD_WRT_DOWN_VAL;
            } else {
                return TRX_RSN_CD_RVAL_VAL;
            }
        }

        /**
         * @param BigDecimal This Month's Total Standard Cost Amount
         * @param BigDecimal Next Month's Total Standard Cost Amount 
         */
        protected BigDecimal calculateRevalAmount(BigDecimal nextMthTotStdCostAmtParam, BigDecimal thisMthTotStdCostAmtParam) {

            BigDecimal calculatedRevalAmount = nextMthTotStdCostAmtParam.subtract(thisMthTotStdCostAmtParam);
            return calculatedRevalAmount;
        }
        
        /**
         * @param BigDecimal This Month's Total Standard Cost Amount
         * @param BigDecimal Next Month's Total Standard Cost Amount 
         */
        protected String getNextMthFisrstDay() {

            String dt = "";
            Calendar cal = Calendar.getInstance();

            // make date of yyyymm01 of process date
            cal.set(Integer.valueOf(ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "yyyy")),
                    Integer.valueOf(ZYPDateUtil.DateFormatter(procDt, "yyyyMMdd", "MM")) - 1,
                    1);

            // plus one month 
            cal.add(Calendar.MONTH, 1);
            
            dt = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
            
            return dt;
        }

        /**
         * @param BigDecimal Invoice Quantity
         * @param BigDecimal Calculated Revaluation Total Amount 
         */
        protected BigDecimal calculateRevalTotalAmount(BigDecimal invtyQtyParam, BigDecimal calculatedRevalAmountParam) {

            BigDecimal calculatedRevalTotalAmount = invtyQtyParam.multiply(calculatedRevalAmountParam);
            return calculatedRevalTotalAmount;
        }

        /**
         * @param EZDTMsg AJE Merchandise Inventory Revaluation Interface.
         * @throws JrnlCommonException JrnlCommonException
         */
        protected void createAjeMdseRvalIntfc(EZDTMsg ajeMdseRvalIntfc) throws NFACommonJrnlEntry.JrnlCommonException {

            if (ajeMdseRvalIntfc != null) {
                intfcMsgs[intfcMsgCount] = ajeMdseRvalIntfc;
                intfcMsgCount += 1;

            } else {  // array may be not full
                intfcMsgs = commonJrnlEntry.changeArraySize(intfcMsgs, intfcMsgCount);
            }

            // per 10000 lines
            if (intfcMsgCount >= BULK_INSERT_COUNT || ajeMdseRvalIntfc == null) {

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

    /**
     * 
     * @param procDt
     * @return
     */
    private static String prevYearMonth(String procDt) {
        String year = procDt.substring(0,4);
        String month = procDt.substring(4,6);
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        String prevYrMth = null;

        if(month.equals("01")) {
            prevYrMth = String.valueOf(yearInt -1 ) + "12";
        } else {
            prevYrMth = year + String.format("%1$02d", monthInt - 1);
        }
        return prevYrMth;
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");

    }

}
