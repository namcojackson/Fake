/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB088001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDTMsg;
import business.db.INVTY_DTL_RPT_SMRY_AMTTMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFACommonJrnlEntryConstant;
import business.parts.NFACommonJrnlEntry.JrnlCommonException;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
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


/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * This batch copy the data from S21_ORG to TOC_MTH_SNAP_SHOT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2009   CSA             N.Sasaki        Create          N/A
 * 05/17/2013   CSAI            K.Uramori       Update          Modification for DS
 * 07/29/2013   Fujitsu         T.Tanaka        Update          Def#1453  Mdse/Parts Inventry In-Out Report modify Logic
 * 02/19/2016   CSAI            K.Uramori       Update          Modification for CSA
 * 08/23/2018   CITS            T.Hakodate      Update          QC#24951
 * 12/11/2018   Hitachi         Y.Takeno        Update          QC#29498
 * </pre>
 */
public class NFAB088001 extends S21BatchMain implements NFAB088001Constant, ZYPConstant, NFACommonJrnlEntryConstant {
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

    /** Process Date */
    private static String procDt = "";

    /** Previous date of Process Date */
    private static String prevOfProcDt = "";   // latest and past date of the process date in the existing data

//    /** Date format */
//    private SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);

    /** Array of TMsg */
    private EZDTMsg[] tMsgCreate;

    /** Size of Array */
    private int tMsgCnt = 0;

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry common = new NFACommonJrnlEntry();

    /**
     * @param args Argument from a batch shell.
     */
    public static void main(String[] args) {

        S21InfoLogOutput.println("Main Method Start");

        // Get parameter (PROC_DT)
        procDt = S21BatchUtil.getInputParam1();

        new NFAB088001().executeBatch(NFAB088001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("initRoutine Method Start");

        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();

        if (procDt == null || procDt.equals(BLANK)) {
            procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        }
        // get a previous date of process date
//        prevOfProcDt = getPrevDateStr(procDt);

        // initialize
        tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {

        S21InfoLogOutput.println("mainRoutine Method Start");

        // get a previous date of process date
        //---- start mod 2016/02/23 The standard cost will be different in dalily basis
        // So this condition to be removed. 
        /*if(!getPrevDateStr(procDt)){
            return;
        }
        */
        //---- end 2016/02/23
        
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("locStsCdInTransit", LOC_STS_CD_IN_TRANSIT);
        queryParam.put("invtyLocTpCdWarehouse", INVTY_LOC_TP_CD_WAREHOUSE);
        queryParam.put("invtyLocTpCdConsignee", INVTY_LOC_TP_CD_CONSIGNEE);
        queryParam.put("invtyLocTpCdVendor", INVTY_LOC_TP_CD_VENDOR);
        queryParam.put("vndTpKitting", VND_TP_CD_KITTING);
        queryParam.put("vndTpRefurbish", VND_TP_CD_REFURBISH);
        queryParam.put("assetCdF", ASSET_CD_F);        
        
        //---- start mod 2016/02/23 The standard cost will be different in daily basis
        // So this condition to be removed.
        // Only when beginning of month, retrieve the latest previous data again. (last month data)
        /*if(isFirstDateOfMonth(procDt)) {
            queryParam.put("dateStart", prevOfProcDt);
            queryParam.put("dateEnd", procDt);
        } else {
        */
            queryParam.put("dateStart", procDt);
            queryParam.put("dateEnd", "");
        //}
        //---- end 2016/02/23

        //---- start add 2016/02/19
        queryParam.put("stdCost", MDSE_COST_TP.STANDARD_COST);
        //---- end 2016/02/19

        // QC#24951 add start
        // START 2018/12/11 [QC#29498, MOD]
        // String inventoryInTr = ZYPCodeDataUtil.getVarCharConstValue("NFAB0880_INVENTORY_IN_TR", this.glblCmpyCd);
        // String loanMdse = ZYPCodeDataUtil.getVarCharConstValue("NFAB0880_LOAN_MERCHANDISE", this.glblCmpyCd);
        // String mdseStage = ZYPCodeDataUtil.getVarCharConstValue("NFAB0880_MERCHANDISE_S", this.glblCmpyCd);
        // String serviceParts = ZYPCodeDataUtil.getVarCharConstValue("NFAB0880_SERVICE_PARTS", this.glblCmpyCd);
        // String parts = ZYPCodeDataUtil.getVarCharConstValue("NFAB0880_PROJ_CD_PARTS", this.glblCmpyCd);
        // 
        // if (!ZYPCommonFunc.hasValue(inventoryInTr)) {
        //     inventoryInTr = "";
        // }
        // if (!ZYPCommonFunc.hasValue(loanMdse)) {
        //     loanMdse = "";
        // }
        // if (!ZYPCommonFunc.hasValue(mdseStage)) {
        //     mdseStage = "";
        // }
        // if (!ZYPCommonFunc.hasValue(serviceParts)) {
        //     serviceParts = "";
        // }
        // if (!ZYPCommonFunc.hasValue(parts)) {
        //     parts = "";
        // }
        //
        // queryParam.put("locStsCd11", LOC_STS.TRIAL_USE);
        // queryParam.put("locStsCd15", LOC_STS.WAITING_FOR_INSTALLATION);
        // queryParam.put("locStsCd16", LOC_STS.IN_TRANSIT_WH);
        // queryParam.put("invtyLocCdCr", "CR");
        // 
        // queryParam.put("inventoryInTr", inventoryInTr);
        // queryParam.put("loanMdse", loanMdse);
        // queryParam.put("mdseStage", mdseStage);
        // queryParam.put("parts", parts + "%");
        // queryParam.put("serviceParts", serviceParts);
        queryParam.put("aster", "*");
        // END   2018/12/11 [QC#29498, MOD]

        queryParam.put("coaProjAcctTpCd", COA_PROJ_ACCT_TP.INV);

        // QC#24951 add end
        
        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getResultNFAB088001", queryParam, new DBProc());

        if (result) {
            commit();
        } else {
            rollback();
            throw new S21AbendException(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    /** DB Process */
    private class DBProc extends S21SsmBooleanResultSetHandlerSupport {

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                //---- start mod 2016/02/23 The standard cost will be different in dalily basis
                // So this condition to be removed.
                // Delete Yesterday's record
                /*if(isFirstDateOfMonth(procDt)) {
                    INVTY_DTL_RPT_SMRY_AMTTMsg tMsgDeletePrevDt = new INVTY_DTL_RPT_SMRY_AMTTMsg();
                    common.setFieldValue(tMsgDeletePrevDt, "glblCmpyCd", glblCmpyCd);
                    common.setFieldValue(tMsgDeletePrevDt, "invtySnapShotDt", prevOfProcDt);
                    S21FastTBLAccessor.removeByPartialValue(tMsgDeletePrevDt, new String[] {"glblCmpyCd", "invtySnapShotDt" });
                }
                */
                //---- end mod 2016/02/23

                // Delete Today's record
                INVTY_DTL_RPT_SMRY_AMTTMsg tMsgDeleteProcDt = new INVTY_DTL_RPT_SMRY_AMTTMsg();
                common.setFieldValue(tMsgDeleteProcDt, "glblCmpyCd", glblCmpyCd);
                common.setFieldValue(tMsgDeleteProcDt, "invtySnapShotDt", procDt);
                S21FastTBLAccessor.removeByPartialValue(tMsgDeleteProcDt, new String[] {"glblCmpyCd", "invtySnapShotDt" });

                while (rs.next()) {

                    String invtySnapShotDt = common.checkNull(rs.getString(INVTY_SNAP_SHOT_DT));
                    String mdseCd = common.checkNull(rs.getString(MDSE_CD));
                    String coaProdCd = common.checkNull(rs.getString(COA_PROD_CD));
                    String rgtnStsCd = common.checkNull(rs.getString(RGTN_STS_CD));
                    BigDecimal invtyTotQty = common.checkNull(rs.getBigDecimal(INVTY_TOT_QTY));
                    BigDecimal thisMthTotStdCostAmt = common.checkNull(rs.getBigDecimal(THIS_MTH_TOT_STD_COST_AMT));
                    
                    // QC#24951 add start
                    String locStsCd = common.checkNull(rs.getString("LOC_STS_CD"));
                    // QC#24951 add end
                    
                    //---- start mod 2016/02/19  amount to be calculated by actual cost * inventory qty
                    //BigDecimal dlyMdseSmryAmt = common.checkNull(rs.getBigDecimal(DLY_MDSE_SMRY_AMT));
                    BigDecimal dlyMdseSmryAmt = rs.getBigDecimal("ACTL_COST_AMT").multiply(rs.getBigDecimal("INVTY_TOT_QTY").setScale(rs.getBigDecimal("SCALE").intValue(), HALF_UP ));
                    //---- end 2016/02/19
                    
                    String coaAcctCd = common.checkNull(rs.getString(COA_ACCT_CD));

                    //---- start mod 2016/02/23 The standard cost will be different in dalily basis
                    // So this condition to be removed. 
                    // latest previous data should be last month, when it's beginning of month
                    /*if (isFirstDateOfMonth(procDt) && invtySnapShotDt.equals(prevOfProcDt)) {
                        thisMthTotStdCostAmt = rs.getBigDecimal(LAST_MTH_TOT_STD_COST_AMT);
                        dlyMdseSmryAmt = rs.getBigDecimal(LAST_DLY_MDSE_SMRY_AMT);
                    }
                    */
                    //---- end 2016/02/23
                    
                    if (coaProdCd == null || coaProdCd.equals(BLANK)) {
                        coaProdCd = UNKNOWN;
                    }

                    INVTY_DTL_RPT_SMRY_AMTTMsg tMsg = new INVTY_DTL_RPT_SMRY_AMTTMsg();

                    common.setFieldValue(tMsg, "glblCmpyCd", glblCmpyCd);

                    common.setFieldValue(tMsg, "invtySnapShotDt", invtySnapShotDt);
                    common.setFieldValue(tMsg, "mdseCd", mdseCd);
                    common.setFieldValue(tMsg, "coaProdCd", coaProdCd);
                    common.setFieldValue(tMsg, "rgtnStsCd", rgtnStsCd);

                    common.setFieldValue(tMsg, "invtyTotQty", invtyTotQty);
                    common.setFieldValue(tMsg, "thisMthTotStdCostAmt", thisMthTotStdCostAmt);
                    common.setFieldValue(tMsg, "dlyMdseSmryAmt", dlyMdseSmryAmt);
                    common.setFieldValue(tMsg, "coaAcctCd", coaAcctCd);
                    
                    // QC#24951 add start
                    ZYPEZDItemValueSetter.setValue(tMsg.locStsCd, locStsCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.invtyDtlRptSmryPk, ZYPOracleSeqAccessor.getNumberBigDecimal("INVTY_DTL_RPT_SMRY_SQ"));
                    // QC#24951 add end

                    //---- start add 2016/02/19
                    setValue(tMsg.invtyLocCd, rs.getString("INVTY_LOC_CD"));
                    setValue(tMsg.rtlWhCd, rs.getString("RTL_WH_CD"));
                    setValue(tMsg.rtlSwhCd, rs.getString("RTL_SWH_CD"));
                    setValue(tMsg.mdseCostTpCd, rs.getString("MDSE_COST_TP_CD"));
                    setValue(tMsg.mdseInvtyCostPct, rs.getBigDecimal("MDSE_INVTY_COST_PCT"));
                    setValue(tMsg.assetRecovCostAmt, rs.getBigDecimal("ASSET_RECOV_COST_AMT"));
                    setValue(tMsg.actlCostAmt, rs.getBigDecimal("ACTL_COST_AMT"));
                    //---- end 2016/02/19
                    
                    createRecord(tMsg);
                } // while

                if (tMsgCnt != 0) {
                    createRecord(null);
                }

            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (NFACommonJrnlEntry.JrnlCommonException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }

            return Boolean.TRUE;
        }

        /**
         * <pre>
         *  Create New Record
         * </pre>
         * 
         * @param EZDTMsg tMsg
         * @throws JrnlCommonException JrnlCommonException
         */
        private void createRecord(EZDTMsg tMsg) throws NFACommonJrnlEntry.JrnlCommonException {

            if (tMsg != null) {
                tMsgCreate[tMsgCnt] = tMsg;
                tMsgCnt += 1;

            } else { // array may be not full
                tMsgCreate = common.changeArraySize(tMsgCreate, tMsgCnt);
            }

            // per 10000 lines
            if (tMsgCnt >= BULK_INSERT_COUNT || tMsg == null) {

                int retCnt = S21FastTBLAccessor.insert(tMsgCreate);

                // if passed records' count and return count don't
                // match, error
                if (retCnt != tMsgCnt) {
                    throw common.new JrnlCommonException(ZZBM0074E);
                }
                commitCount += tMsgCnt;
                // initialize
                tMsgCnt = 0;
                tMsgCreate = new EZDTMsg[BULK_INSERT_COUNT];
            }
        }
    }

    /** Get Previous of Process Date */
    private boolean getPrevDateStr(String procDtStr) {
    
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dateProc", procDtStr);
    
        Boolean result = (Boolean) this.ssmBatchClient.queryObject("getMaxPrevDt", queryParam, new GetPrevDate(procDtStr));
    
        if (!result) {
            S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, errMsg });
        }
          
        return result;
    }
    
    private final class GetPrevDate extends S21SsmBooleanResultSetHandlerSupport {

        private GetPrevDate(String procDtStr) {
            prevOfProcDt = procDtStr;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            try {
                while (rs.next()) {
                    prevOfProcDt = rs.getString(DT_PREV);
                }
                
                if(prevOfProcDt == null){
                    prevOfProcDt = procDt;
                }
            } catch (EZDAbendException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            } catch (SQLException ex) {
                errMsg = ex.getMessage();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }
    
//    private String getPrevDateStr(String procDtStr) {
//
//        try {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(dateFormat.parse(procDtStr));
//            cal.add(Calendar.DATE, -1);
//
//            return dateFormat.format(cal.getTime());
//        } catch (ParseException e) {
//            S21InfoLogOutput.println(NFAM0035E, new String[] {MSG_PARAM, e.getMessage() });
//            throw new S21AbendException("Error occured during the procedure: " + e.getMessage());
//        }
//    }

    private boolean isFirstDateOfMonth(String dateStr) {

        if (dateStr != null && dateStr.length() == 8) {
            String date = dateStr.substring(6, 8);
            if (date.equals("01")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    protected void termRoutine() {

        S21InfoLogOutput.println("termRoutine Method Start");

        // Set termination code and total commit count.
        setTermState(this.termCd, commitCount, 0, commitCount);

        S21InfoLogOutput.println("termRoutine Method End");
    }
}
