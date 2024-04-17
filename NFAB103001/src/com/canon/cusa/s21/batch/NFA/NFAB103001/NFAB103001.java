package com.canon.cusa.s21.batch.NFA.NFAB103001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import business.db.AJE_INV_ACCT_DISTTMsg;
import business.db.AJE_INV_ACCT_DIST_ERRTMsg;
import business.db.DFRD_ACCTG_RULETMsg;
import business.db.DS_INV_SLS_CRTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.parts.NFAC000101PMsg;
import business.parts.NFACommonJrnlEntry;
import business.parts.NFZC102001PMsg;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NWZ.NWZC055001.DfrdRevSetupData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INV_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL_RSN_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_IND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * Deferred Revenue Recognition
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/04/2016   CSAI            K.Uramori       Create          N/A
 * 06/21/2016   CSAI            K.Uramori       Update          QC#9033 set deferred related information in DS_INV_SLS_CR before processing
 * 09/15/2016   Hitachi         K.Kasai         Update          QC#12540,14349
 * 09/26/2016   Fujitsu         S.Fujita        Update          QC#13362
 * 2016/09/29   Hitachi         K.Kojima        Update          QC#14609
 * 09/15/2016   Hitachi         K.Kasai         Update          QC#14349
 * 02/09/2017   Fujitsu         S.Fujita        Update          QC#14655
 * 02/17/2017   Fujitsu         H.Ikeda         Update          QC#15637
 * 07/03/2017   Hitachi         E.Kameishi      Update          QC#19734
 * 07/04/2017   CITS            K.Ogino         Update          QC#19715
 * 09/07/2017   Hitachi         T.Tsuchida      Update          QC#20995
 * 09/29/2017   Fujitsu         T.Murai         Update          QC#21254
 * 10/20/2017   CITS            K.Ogino         Update          QC#21878
 * 11/20/2017   CITS            K.Ogino         Update          QC#20995
 * 11/20/2017   Hitachi         Y.Takeno        Update          QC#17089
 * 11/28/2017   Hitachi         Y.Takeno        Update          QC#17089-1
 * 11/29/2017   CITS            T.Wada          Update          QC#22688
 * 11/30/2017   Hitachi         J.Kim           Update          QC#21051
 * 12/05/2017   Hitachi         E.Kameishi      Update          QC#22740
 * 12/14/2017   CITS            K.Ogino         Update          QC#23084
 * 12/20/2017   Hitachi         E.Kameishi      Update          QC#22912
 * 01/24/2018   Hitachi         Y.Takeno        Update          QC#23692
 * 04/23/2018   CITS            K.Ogino         Update          QC#25313
 * 05/17/2018   CITS            S.Katsuma       Update          QC#25822
 * 05/24/2018   CITS            K.Kameoka       Update          QC#25311
 * 06/11/2018   Hitachi         E.Kameishi      Update          QC#26462
 * 06/15/2018   Hitachi         E.Kameishi      Update          QC#26693
 * 07/06/2018   Hitachi         J.Kim           Update          QC#26682
 * 07/10/2018   Hitachi         E.Kameishi      Update          QC#26843
 * 01/07/2019   Hitachi         E.Kameishi      Update          QC#29734
 * 03/17/2022   CITS            D.Mamaril       Update          QC#59657
 *</pre>
 */
public class NFAB103001 extends S21BatchMain implements ZYPConstant, NFAB103001Constant{

    /** Normal Counter */
    private int normCnt = 0;

    /** Normal Counter */
    private int normTmpCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batProcDt = null;

    // START 2018/01/24 [QC#23692, ADD]
    /** User Variable1 */
    private String usrVar1;
    // END   2018/01/24 [QC#23692, ADD]

    /** Term Code */
    private TERM_CD termCd = null;

    // Del Start 2017/09/29 QC#21254
//    /** sysSrcNm */
//    private String sysSrcNm;
    // Del End 2017/09/29 QC#21254

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Ssm Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;
    
    /** AJE common function */
    NFACommonJrnlEntry cmmnFunc = new NFACommonJrnlEntry();
    
    /** List of Tmsg*/
    List<AJE_INV_ACCT_DISTTMsg> acctDistList = new ArrayList<AJE_INV_ACCT_DISTTMsg> ();
    
    /** List of Tmsg*/
    List<AJE_INV_ACCT_DIST_ERRTMsg> acctDistErrList = new ArrayList<AJE_INV_ACCT_DIST_ERRTMsg> ();
    
    /** List of Tmsg*/
    List<DS_INV_SLS_CRTMsg> slsCrList = new ArrayList<DS_INV_SLS_CRTMsg> ();
    
    /** AJE_LINE_IDX_NUM map */
    Map<BigDecimal, Integer> lineIdxMap = new HashMap<BigDecimal, Integer>();
    
    private String coaMdseTpLabor;
    private String coaMdseTpOptima;
    // START 2018/06/11 E.Kameishi [QC#26462,ADD]
    /** AJE_DFRD_REV_CONTR_STS*/
    private String ajeDfrdRevContrSts;

    /** AJE_DFRD_REV_CONTR_CHK_FLG*/
    private String ajeDfrdRevContChkFlg;
    // END 2018/06/11 E.Kameishi [QC#26462,ADD]

    
    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        // START 2018/02/24 [QC#23692, ADD]
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"User Variable1"});
        }
        // END   2018/02/24 [QC#23692, ADD]

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);

        // Del Start 2017/09/29 QC#21254
//        // obtain SYS_SRC_NM for AJE
//        SYS_SRCTMsg tmsg = new SYS_SRCTMsg();
//        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(tmsg.sysSrcCd, SYS_SRC.S21_ACCOUNTING_AJE);
//        
//        tmsg = (SYS_SRCTMsg) S21FastTBLAccessor.findByKey(tmsg);
//        
//        sysSrcNm = tmsg.sysSrcNm.getValue();
//        
//        if (!hasValue(sysSrcNm)) {
//            throw new S21AbendException(ZZZM9025E, new String[] {"System Source Name for AJE" });
//        }
        // Del Start 2017/09/29 QC#21254

        // get Labor's merchandise type from varchar const
        coaMdseTpLabor = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_MDSE_TP_LABOR", glblCmpyCd);
        coaMdseTpOptima = ZYPCodeDataUtil.getVarCharConstValue("AJE_COA_MDSE_TP_OPTIMA", glblCmpyCd);
        
        // START 2018/06/11 E.Kameishi [QC#26462,ADD]
        ajeDfrdRevContrSts = ZYPCodeDataUtil.getVarCharConstValue("AJE_DFRD_REV_CONTR_STS", glblCmpyCd);
        ajeDfrdRevContChkFlg = ZYPCodeDataUtil.getVarCharConstValue("AJE_DFRD_REV_CONTR_CHK_FLG", glblCmpyCd);
        // END 2018/06/11 E.Kameishi [QC#26462,ADD]
        
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void mainRoutine() {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        //---- start 2016/06/22 QC#9033
        try {
            S21InfoLogOutput.println("1.Sql - getDfrdInv ================================ START");
            ps = getDfrdInv();
            rs = ps.executeQuery();
            S21InfoLogOutput.println("1.Sql - getDfrdInv ================================ END");
            
            setDfrdInfo(rs);
            
        } catch (SQLException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()}); 
            
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        // if no error, commit;
        commit();
        
        //---- end 2016/06/22
        
        // get aje id list
        List<Map> ajeIdList = getAjeIdList();
        
        //---- start add 2016/04/08
        if (ajeIdList.size() == 0) {
            // no data to process
            return;
        }
        //----end 2016/04/08
        
        // get AJE pattern list (It will get credit side of the pattern)
        List<Map> listAjePtrn = cmmnFunc.getAjePtrnForDfrd(ajeIdList, this.glblCmpyCd);
     
        
        // get target data
        ps = null;
        rs = null;
        
        // for monthly recognition
        try {
            S21InfoLogOutput.println("2.Sql - getTargetData ================================ START");
            ps = getTargetData();
            rs = ps.executeQuery();
            S21InfoLogOutput.println("2.Sql - getTargetData ================================ END");

            mainProcess(rs, listAjePtrn, false);
            
        } catch (SQLException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()}); 
            
        } catch (ParseException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()});
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        // for contract termination
        try {
            S21InfoLogOutput.println("3.Sql - getTargetDataForTermination  ================================ START");
            ps = getTargetDataForTermination();
            rs = ps.executeQuery();
            S21InfoLogOutput.println("3.Sql - getTargetDataForTermination  ================================ END");

            mainProcess(rs, listAjePtrn, true);
            
        } catch (SQLException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()}); 
            
        } catch (ParseException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()});
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        
        //-- start 2016/10/21 When error occurs, the other side of pair entry needs to be error, too.
        ps = null;
        rs = null;
        
        try {
            S21InfoLogOutput.println("4.Sql - getErrorDist ================================ START");
            ps = getErrorDist();
            rs = ps.executeQuery();
            S21InfoLogOutput.println("4.Sql - getErrorDist ================================ END");

            setErrorStsForPair(rs);
            
        } catch (SQLException ex) {
            // abend
            throw new S21AbendException(NFAM0035E, new String[] {BUSINESS_ID, ex.getMessage()}); 
            
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
        //--- end 2016/10/21
        commit();
    }

    //---- start add 2016/06/22 QC#9033
    private void setDfrdInfo(ResultSet rs) throws SQLException{
        // START 2018/01/24 [QC#23692, MOD]
        S21InfoLogOutput.println("setDfrdInfo ================================ START");
        int listSize = Integer.parseInt(this.usrVar1);
        // List<DS_INV_SLS_CRTMsg> listTmsg = new ArrayList<DS_INV_SLS_CRTMsg> ();
        List<DS_INV_SLS_CRTMsg> listTmsg = new ArrayList<DS_INV_SLS_CRTMsg> (listSize);
        // END   2018/01/24 [QC#23692, MOD]

        while (rs.next()) {
            // START 2018/01/24 [QC#23692, MOD]
            if (listTmsg.size() > 0 && (listTmsg.size() % listSize) == 0) {
                // update
                updateSlsCr(listTmsg);
                listTmsg.clear();
            }
            // END  2018/01/24 [QC#23692, MOD]

            DfrdRevSetupData dfrdData = setDfrdRevSetupDate(rs);
            
            if (!setDfrdRelatedInfo(rs, dfrdData, listTmsg)) {
                // unexpected error
                throw new SQLException();
            }
        }

        if (listTmsg.size() > 0) {
            // update
            updateSlsCr(listTmsg);
        }
        S21InfoLogOutput.println("setDfrdInfo ================================ END " + listTmsg.size());
        // START 2018/01/24 [QC#23692, ADD]
        listTmsg = null;
        // END   2018/01/24 [QC#23692, ADD]
    }
    
    private void updateSlsCr(List<DS_INV_SLS_CRTMsg> list) {
        if (list.size() == 0) {
            return;
        }
        
        int rtn = S21FastTBLAccessor.update(list.toArray(new DS_INV_SLS_CRTMsg[list.size()]));
            
        if (rtn != list.size()) {
            throw new S21AbendException(UNIQUE_ERROR);
        }
           
    }
   
    private DfrdRevSetupData setDfrdRevSetupDate(ResultSet rs) throws SQLException{
        
        DfrdRevSetupData dfrdData = new DfrdRevSetupData();
        
        dfrdData.setBllgCycleMthAot(rs.getBigDecimal("BLLG_CYCLE_MTH_AOT"));
        dfrdData.setContrEffFromDt(rs.getString("CONTR_EFF_FROM_DT"));
        dfrdData.setContrEffThruDt(rs.getString("CONTR_EFF_THRU_DT"));
        dfrdData.setDfrdRevFlg(rs.getString("DFRD_REV_FLG"));
        dfrdData.setFixDurnFlg(rs.getString("FIX_DURN_FLG"));
        dfrdData.setDfrdAcctgRuleDurnAot(rs.getBigDecimal("DFRD_ACCTG_RULE_DURN_AOT"));
        dfrdData.setCoaMdseTpCd(rs.getString("COA_MDSE_TP_CD"));
        dfrdData.setDfrdAcctgRuleCd(rs.getString("DFRD_ACCTG_RULE_CD"));
        
        return dfrdData;
    }

    private boolean setDfrdRelatedInfo(ResultSet rs, DfrdRevSetupData dfrdData, List<DS_INV_SLS_CRTMsg> listTmsg) throws SQLException{
        String origAcctgRule = rs.getString("DFRD_ACCTG_RULE_CD");

        // search TMsg
        DS_INV_SLS_CRTMsg tmsg = new DS_INV_SLS_CRTMsg();
        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.dsInvSlsCrPk, rs.getBigDecimal("DS_INV_SLS_CR_PK"));
        
        tmsg = (DS_INV_SLS_CRTMsg) S21FastTBLAccessor.findByKey(tmsg);
        
        if (tmsg == null) {
            return false;
        }
        
        // START 2016/09/30 [QC#14349, ADD]
        if (FIXED_VALUE_SRT.equals(rs.getString("TRGT_DATA"))) {
            setValue(tmsg.dfrdAcctgRuleCd, origAcctgRule);
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("DFRD_REV_FLG"))) {
                // START 2018/09/10 J.Kim [QC#28132,MOD]
                // setValue(tmsg.dfrdAcctgRuleDurnAot, BigDecimal.valueOf(rs.getInt("SUM_TERM_MTH_NUM")));
                setValue(tmsg.dfrdAcctgRuleDurnAot, rs.getBigDecimal("SUM_TERM_MTH_NUM"));
                // END 2018/09/10 J.Kim [QC#28132,MOD]
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString("FIX_DURN_FLG"))) {
                    setValue(tmsg.dfrdAcctgRuleDurnAot, BigDecimal.valueOf(rs.getInt("DFRD_ACCTG_RULE_DURN_AOT")));
                }

                setValue(tmsg.dealOrigDfrdAmt, tmsg.dealSlsCrAmt);
                setValue(tmsg.funcOrigDfrdAmt, tmsg.funcSlsCrAmt);
                setValue(tmsg.dealDfrdBalAmt, tmsg.dealSlsCrAmt);
                setValue(tmsg.funcDfrdBalAmt, tmsg.funcSlsCrAmt);
                BigDecimal dealSchdRevAmt = null;
                int aftDeclPntDigitNum = rs.getInt("AFT_DECL_PNT_DIGIT_NUM");
                if (hasValue(tmsg.dfrdAcctgRuleDurnAot)) {
                    dealSchdRevAmt = tmsg.dealDfrdBalAmt.getValue().divide(tmsg.dfrdAcctgRuleDurnAot.getValue(), aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
                }
                setValue(tmsg.dealSchdRevAmt, dealSchdRevAmt);
                BigDecimal funcSchdRevAmt = null;
                if (hasValue(tmsg.dfrdAcctgRuleDurnAot)) {
                    funcSchdRevAmt = tmsg.funcDfrdBalAmt.getValue().divide(tmsg.dfrdAcctgRuleDurnAot.getValue(), aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
                }
                setValue(tmsg.funcSchdRevAmt, funcSchdRevAmt);

                // QC#23084
                String fromDt = "";
                if (ZYPCommonFunc.hasValue(rs.getString("BILL_WITH_EQUIP_FLG")) && ZYPConstant.FLG_ON_Y.equals(rs.getString("BILL_WITH_EQUIP_FLG"))) {
                    fromDt = rs.getString("CONTR_EFF_FROM_DT");
                } else {
                    fromDt = rs.getString("BLLG_PER_FROM_DT");
                }

                setValue(tmsg.durnStartDt, fromDt);
                setValue(tmsg.nextRevRecogDt, fromDt);

                setValue(tmsg.revRecogCnt, BigDecimal.ZERO);

                if (ZYPDateUtil.compare(rs.getString("INV_DT"), this.batProcDt) < 0) {
                    setValue(tmsg.nextRevRecogDt, this.batProcDt);
                }

                setValue(tmsg.revRecogProcStsCd, REV_RECOG_STS_NOT_PROCESSED);
            }
            // add to list
            listTmsg.add(tmsg);
            return true;
        }
        // END 2016/09/30 [QC#14349, ADD]
        
        
        //---- start 2016/09/23 QC#12903 ----//
        /*
        // if the mdse type is Service Labor, overwrite it to Maintenance Billing.
        if (coaMdseTpLabor.equals(dfrdData.getCoaMdseTpCd())) {
            
            if (SYS_SRC.S21_SERVICE.equals(rs.getString("SYS_SRC_CD"))) {
                setValue(tmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.MAINTENANCE_DEFERRAL);
            } else {  // OM
            
                // If it's created by allocation, overwrite only if the accounting rule of the AJE_INV_LINE_ALLOC is also deferred.
                // It has been wrongly updated by NWZC0550. This is correction.
                String dfrdFlgFromAlloc = ZYPConstant.FLG_OFF_N;  // initialize by "N"
                
                if (INV_LINE_SPL_TP.EQUIPMENT.equals(rs.getString("INV_LINE_SPL_TP_CD"))) {
                    dfrdFlgFromAlloc = rs.getString("DFRD_REV_FLG_EQUIP");
                } else if (INV_LINE_SPL_TP.SERVICE.equals(rs.getString("INV_LINE_SPL_TP_CD"))) {
                    dfrdFlgFromAlloc = rs.getString("DFRD_REV_FLG_SVC");
                } else if (INV_LINE_SPL_TP.SUPPLY.equals(rs.getString("INV_LINE_SPL_TP_CD"))) {
                    dfrdFlgFromAlloc = rs.getString("DFRD_REV_FLG_SPLY");
                }
                
                if (ZYPConstant.FLG_ON_Y.equals(dfrdFlgFromAlloc)) {
                    setValue(tmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.MAINTENANCE_DEFERRAL);
                } else {
                    setValue(tmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.IMMEDIATE);
                }
            }
            
        } else if (coaMdseTpOptima.equals(dfrdData.getCoaMdseTpCd()) && INV_LINE_SPL_TP.SERVICE.equals(rs.getString("INV_LINE_SPL_TP_CD"))) {
            // change to OPTIMA's accounting rule. It will be a fixed duration.
            setValue(tmsg.dfrdAcctgRuleCd,      DFRD_ACCTG_RULE.OPTIMA_DEFERRAL);
        }

        // reset deferred accounting rule setup from master if it's changed.
        if (!origAcctgRule.equals(tmsg.dfrdAcctgRuleCd.getValue())) {
            dfrdData.setDfrdAcctgRuleCd(tmsg.dfrdAcctgRuleCd.getValue());

            DFRD_ACCTG_RULETMsg dfrdAcctgRule = getDfrdAcctgRule(tmsg.dfrdAcctgRuleCd.getValue());

            dfrdData.setDfrdAcctgRuleDurnAot(dfrdAcctgRule.dfrdAcctgRuleDurnAot.getValue());
            dfrdData.setDfrdRevFlg(dfrdAcctgRule.dfrdRevFlg.getValue());
            dfrdData.setFixDurnFlg(dfrdAcctgRule.fixDurnFlg.getValue());
        }

        */
        //----end 2016/09/23 QC#12903  ----//
        // START 2017/12/05 E.Kameishi [QC#22740,MOD]
        // If it's credit memo, should be immediate except for Subscription Service and External Purpose.
        if (INV_TP.CREDIT_MEMO.equals(rs.getString("INV_TP_CD"))) {
            if (!DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals(origAcctgRule)
                    || !CR_REBIL_RSN_CATG.EXTERNAL.equals(rs.getString("CR_REBIL_RSN_CATG_CD"))) {
                 dfrdData.setDfrdAcctgRuleCd(DFRD_ACCTG_RULE.IMMEDIATE);
                 dfrdData.setDfrdRevFlg(ZYPConstant.FLG_OFF_N);

                 setValue(tmsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.IMMEDIATE);
            }
        }
        // END 2017/12/05 E.Kameishi [QC#22740,MOD]
        
        if (ZYPConstant.FLG_ON_Y.equals(dfrdData.getDfrdRevFlg())) {
            BigDecimal durnAot = getDurnAot(dfrdData, rs.getBigDecimal("ORD_QTY"));

            if (durnAot == null) {
                durnAot = BigDecimal.ONE;
            }
            setValue(tmsg.dfrdAcctgRuleDurnAot, durnAot);

            // START 2017/09/07 T.Tsuchida [QC#20995,MOD]
            //setValue(tmsg.durnStartDt, rs.getString("INV_DT"));
            //setValue(tmsg.revRecogCnt, BigDecimal.ZERO);
            //if (ZYPDateUtil.isPastDate(rs.getString("INV_DT"))) {
            //    setValue(tmsg.nextRevRecogDt, batProcDt);
            //} else {
            //    setValue(tmsg.nextRevRecogDt, rs.getString("INV_DT"));
            //}
            // QC#23084 Add OP or SS
            // START 2018/06/15 E.Kameishi [QC#26693,MOD]
            // START 2018/06/11 E.Kameishi [QC#26462,MOD]
            boolean contrStsChkFlg = false;
            String dsContrDtlStsCd = rs.getString("DS_CONTR_DTL_STS_CD");
            if (ZYPConstant.FLG_ON_Y.equals(ajeDfrdRevContChkFlg)) {
                if (dsContrDtlStsCd != null && ajeDfrdRevContrSts.contains(rs.getString("DS_CONTR_DTL_STS_CD"))) {
                    contrStsChkFlg = true;
                }
            } else {
                contrStsChkFlg = true;
            }
            // END 2018/06/15 E.Kameishi [QC#26693,MOD]
            //if (DS_CONTR_DTL_STS.ACTIVE.equals(rs.getString("DS_CONTR_DTL_STS_CD")) //
            //        || DFRD_ACCTG_RULE.OPTIMA_DEFERRAL.equals(rs.getString("DFRD_ACCTG_RULE_CD")) //
            //        || DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals(rs.getString("DFRD_ACCTG_RULE_CD"))) {
            if (contrStsChkFlg //
                    || DFRD_ACCTG_RULE.OPTIMA_DEFERRAL.equals(rs.getString("DFRD_ACCTG_RULE_CD")) //
                    || DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE.equals(rs.getString("DFRD_ACCTG_RULE_CD"))) {

                String fromDt = "";
                String cntrEffFromDt = rs.getString("CONTR_EFF_FROM_DT");
                if (ZYPCommonFunc.hasValue(rs.getString("BILL_WITH_EQUIP_FLG")) && ZYPConstant.FLG_ON_Y.equals(rs.getString("BILL_WITH_EQUIP_FLG")) && cntrEffFromDt != null) {
                    fromDt = rs.getString("CONTR_EFF_FROM_DT");
                } else {
                    fromDt = rs.getString("BLLG_PER_FROM_DT");
                }
                // END 2018/06/11 E.Kameishi [QC#26462,MOD]

                setValue(tmsg.durnStartDt,fromDt);
                setValue(tmsg.revRecogCnt, BigDecimal.ZERO);

                // START 2018/07/05 J.Kim [QC#26682,MOD]
                // if (ZYPDateUtil.isPastDate(fromDt)) {
                if (ZYPCommonFunc.hasValue(fromDt) && fromDt.compareTo(this.batProcDt) < 0) {
                    // END 2018/07/05 J.Kim [QC#26682,MOD]
                    setValue(tmsg.nextRevRecogDt, batProcDt);
                } else {
                    setValue(tmsg.nextRevRecogDt, fromDt);
                }
                // END 2017/09/07 T.Tsuchida [QC#20995,MOD]
            }
            // QC#20995 End
            setValue(tmsg.revRecogProcStsCd, PROC_STS.IN_COMPLETED);
            
            BigDecimal slsCrAmt = rs.getBigDecimal("DEAL_SLS_CR_AMT");
                
            setValue(tmsg.dealOrigDfrdAmt, slsCrAmt);
            setValue(tmsg.funcOrigDfrdAmt, slsCrAmt);
            setValue(tmsg.dealDfrdBalAmt, slsCrAmt);
            setValue(tmsg.funcDfrdBalAmt, slsCrAmt);

            BigDecimal dfrdRevAmt;

            // calculate amount for next revenue recognition
            // QC#21878 Start
            if (hasValue(durnAot) && BigDecimal.ZERO.compareTo(durnAot) < 0) {
                dfrdRevAmt = slsCrAmt.divide(durnAot, rs.getBigDecimal("AFT_DECL_PNT_DIGIT_NUM").intValue(), HALF_UP);
            } else {
                setValue(tmsg.dfrdAcctgRuleDurnAot, BigDecimal.ONE);
                dfrdRevAmt = tmsg.dealSlsCrAmt.getValue();
            }

            setValue(tmsg.dealSchdRevAmt, dfrdRevAmt);
            setValue(tmsg.funcSchdRevAmt, dfrdRevAmt);
            // QC#21878 End

        }
        
        /* 2016/10/25 remove this modification. This should be done before account distribution generation.
        // START 2016/09/15 [QC#12540, ADD]
        if (hasValue(rs.getString("BLLG_TMG_CD")) && BLLG_TMG_TP.ARREARS.equals(rs.getString("BLLG_TMG_CD"))) {
            setValue(tmsg.dfrdAcctgRuleCd, DFRD_ACCTG_RULE.IMMEDIATE);
            tmsg.dfrdAcctgRuleDurnAot.clear();
            tmsg.durnStartDt.clear();
            tmsg.dealOrigDfrdAmt.clear();
            tmsg.funcOrigDfrdAmt.clear();
            tmsg.dealDfrdBalAmt.clear();
            tmsg.funcDfrdBalAmt.clear();
            tmsg.dealSchdRevAmt.clear();
            tmsg.funcSchdRevAmt.clear();
            tmsg.revRecogCnt.clear();
            tmsg.firstRevRecogDt.clear();
            tmsg.nextRevRecogDt.clear();
            tmsg.revRecogProcStsCd.clear();
        }
        // END 2016/09/15 [QC#12540, ADD]
         */
        
        // add to list
        listTmsg.add(tmsg);
        
        return true;

    }
    
    private BigDecimal getDurnAot(DfrdRevSetupData dfrdData, BigDecimal ordQty) {

        // START 2017/07/03 E.Kameishi [QC#19734, ADD]
        if (DFRD_ACCTG_RULE.MAINTENANCE_DEFERRAL.equals(dfrdData.getDfrdAcctgRuleCd())) {
            return ordQty;
        } else {
        // END 2017/07/03 E.Kameishi [QC#19734, ADD]
            //---- start 2016/09/23 QC#12903
           // If billing cycle month amount of time has value
            if (hasValue(dfrdData.getBllgCycleMthAot())) {
                // In case of -1
            	// QC#22688 Mod Start
                return dfrdData.getBllgCycleMthAot();
//
//                if (BigDecimal.ONE.negate().equals(dfrdData.getBllgCycleMthAot())) {
//                    // calculate AOT by From To date.
//                    if (hasValue(dfrdData.getContrEffFromDt()) && hasValue(dfrdData.getContrEffThruDt())) {
//                        try {
//                            int aot = differenceMonth(strDateFormat(dfrdData.getContrEffThruDt()), strDateFormat(dfrdData.getContrEffFromDt()));
//    
//                            return BigDecimal.valueOf(aot);
//                        } catch (ParseException ex) {
//                            return null;
//                        }
//    
//                    } else {
//                        return null;
//                    }
//                } else {
//                    return dfrdData.getBllgCycleMthAot();
//                }
            	// QC#22688 Mod End
                
            // no value
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(dfrdData.getFixDurnFlg())&& BigDecimal.ZERO.compareTo(dfrdData.getDfrdAcctgRuleDurnAot()) < 0 ) {
                 // from Deferred Accounting Rule Master
                    return dfrdData.getDfrdAcctgRuleDurnAot();
                } else {
                    return ordQty;
                }
            }
        }
        
        /*
        // If it is maintenance billing, order quantity to be set to AOT.
        if (DFRD_ACCTG_RULE.MAINTENANCE_DEFERRAL.equals(dfrdData.getDfrdAcctgRuleCd())) {
            return ordQty;
        } else {

            if (dfrdData.getDfrdAcctgRuleCd() == null) {
                return null;
            }

            if (ZYPConstant.FLG_ON_Y.equals(dfrdData.getDfrdRevFlg()) && ZYPConstant.FLG_ON_Y.equals(dfrdData.getFixDurnFlg())) {
                // from Deferred Accounting Rule Master
                return dfrdData.getDfrdAcctgRuleDurnAot();

            // If not fixed duration
            } else if (ZYPConstant.FLG_ON_Y.equals(dfrdData.getDfrdRevFlg()) && ZYPConstant.FLG_OFF_N.equals(dfrdData.getFixDurnFlg())) {
                // get BLLG_CYCLE_MTH_AOT

                // If it cannot be obtained from Billing Cycle
                if (!hasValue(dfrdData.getBllgCycleMthAot())) {
                 // from Deferred Accounting Rule Master
                    return dfrdData.getDfrdAcctgRuleDurnAot();
                } else {
                    // In case of -1
                    if (BigDecimal.ONE.negate().equals(dfrdData.getBllgCycleMthAot())) {
                        // calculate AOT by From To date.
                        if (hasValue(dfrdData.getContrEffFromDt()) && hasValue(dfrdData.getContrEffThruDt())) {
                            try {
                                int aot = differenceMonth(strDateFormat(dfrdData.getContrEffThruDt()), strDateFormat(dfrdData.getContrEffFromDt()));

                                return BigDecimal.valueOf(aot);
                            } catch (ParseException ex) {
                                return null;
                            }

                        } else {
                            return null;
                        }
                    } else {
                        return dfrdData.getBllgCycleMthAot();
                    }
                }
            } else {
                return null;
            }
        }
        */
        //---- end 2016/09/23 QC#12903 ----//
    }
    
    private DFRD_ACCTG_RULETMsg getDfrdAcctgRule(String dfrdAcctgRuleCd) {

        DFRD_ACCTG_RULETMsg tmsg = new DFRD_ACCTG_RULETMsg();
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.dfrdAcctgRuleCd, dfrdAcctgRuleCd);
        tmsg = (DFRD_ACCTG_RULETMsg) S21FastTBLAccessor.findByKey(tmsg);

        return tmsg;
    }
    //---- end 2016/06/22
    
    private void setErrorStsForPair(ResultSet rs) throws SQLException{
        List<AJE_INV_ACCT_DISTTMsg> distList = new ArrayList<AJE_INV_ACCT_DISTTMsg> ();
        S21InfoLogOutput.println("setErrorStsForPair ================================ START");
        while (rs.next()) {
            
            AJE_INV_ACCT_DISTTMsg distTmsg = new AJE_INV_ACCT_DISTTMsg();
            
            setValue(distTmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(distTmsg.ajeInvAcctDistPk, rs.getBigDecimal("AJE_INV_ACCT_DIST_PK"));
            
            distTmsg = (AJE_INV_ACCT_DISTTMsg) S21FastTBLAccessor.findByKeyForUpdate(distTmsg);
            
            setValue(distTmsg.procStsCd, PROC_STS.ERROR);
            
            distList.add(distTmsg);
            
            submitUpdateDist(distList, false);
        }
        
        submitUpdateDist(distList, true);
        S21InfoLogOutput.println("setErrorStsForPair ================================ END");
    }
    
    private void mainProcess(ResultSet rs, List<Map> listAjePtrn, boolean isTrmntd) throws SQLException, ParseException{
        S21InfoLogOutput.println("mainProcess ================================ START");
        int numOfCnt = 0;

        int lineIdxNum = 0;
        while (rs.next()) {
            
            // TMsg for Distribution
            AJE_INV_ACCT_DISTTMsg distTmsg = new AJE_INV_ACCT_DISTTMsg();
            // TMsg for Distribution Erro   r
            AJE_INV_ACCT_DIST_ERRTMsg errTmsg = new AJE_INV_ACCT_DIST_ERRTMsg();
            
            // START 2017/11/20 [QC#17089, ADD]
            // if (isTrmntd) {
            if (isTrmntd || isCratTrnsfJrnl(rs)) {
            // END   2017/11/20 [QC#17089, ADD]
                // recognize fully
                setValue(distTmsg.jrnlDealAmt, rs.getBigDecimal("DEAL_DFRD_BAL_AMT"));
                setValue(distTmsg.jrnlFuncAmt, distTmsg.jrnlDealAmt.getValue());
                // QC#25313
                numOfCnt = 1;
//                if (isCratTrnsfJrnl(rs) && hasValue(rs.getBigDecimal("REV_RECOG_CNT"))) {
//                    numOfCnt = rs.getInt("REV_RECOG_CNT");
//                } else {
//                    numOfCnt = 1;
//                }
            } else {
                // calculate amount to be recognized
                numOfCnt = calcRecogAmt(distTmsg, rs.getString("DURN_START_DT"), rs.getInt("REV_RECOG_CNT"), rs.getInt("DFRD_ACCTG_RULE_DURN_AOT"), 
                                                   rs.getBigDecimal("DEAL_DFRD_BAL_AMT"), rs.getInt("AFT_DECL_PNT_DIGIT_NUM"));
            }
            
            // 9 segment
            if ("D".equals(rs.getString("DR_CR_TP_CD"))) {
                
                setValue(distTmsg.ajeCoaCmpyCd, rs.getString("AJE_COA_CMPY_CD"));
                setValue(distTmsg.ajeCoaBrCd, rs.getString("AJE_COA_BR_CD"));
                setValue(distTmsg.ajeCoaCcCd, rs.getString("AJE_COA_CC_CD"));
                setValue(distTmsg.ajeCoaAcctCd, rs.getString("AJE_COA_ACCT_CD"));
                setValue(distTmsg.ajeCoaProdCd, rs.getString("AJE_COA_PROD_CD"));
                setValue(distTmsg.ajeCoaChCd, rs.getString("AJE_COA_CH_CD"));
                setValue(distTmsg.ajeCoaAfflCd, rs.getString("AJE_COA_AFFL_CD"));
                setValue(distTmsg.ajeCoaProjCd, rs.getString("AJE_COA_PROJ_CD"));
                setValue(distTmsg.ajeCoaExtnCd, rs.getString("AJE_COA_EXTN_CD"));

                // QC#19715 START
                BigDecimal dsInvSlsCrPk = rs.getBigDecimal("DS_INV_SLS_CR_PK");
                if (lineIdxMap.containsKey(dsInvSlsCrPk)) {
                    lineIdxNum = lineIdxMap.get(dsInvSlsCrPk);
                } else {
                    String ajeLineIdxNum = rs.getString("AJE_LINE_IDX_NUM");
                    if (hasValue(ajeLineIdxNum)) {
                        lineIdxNum = Integer.parseInt(ajeLineIdxNum);
                    }
                }
                lineIdxNum++;
                lineIdxMap.put(dsInvSlsCrPk , lineIdxNum);
                // QC#19715 END

            } else {  // credit
                setValue(distTmsg.ajeCoaCmpyCd, rs.getString("AJE_COA_CMPY_CD"));
                setValue(distTmsg.ajeCoaCcCd, rs.getString("AJE_COA_CC_CD"));
                setValue(distTmsg.ajeCoaAcctCd, rs.getString("AJE_COA_ACCT_CD"));
                setValue(distTmsg.ajeCoaProdCd, rs.getString("AJE_COA_PROD_CD"));
                setValue(distTmsg.ajeCoaChCd, rs.getString("AJE_COA_CH_CD"));
                setValue(distTmsg.ajeCoaAfflCd, rs.getString("AJE_COA_AFFL_CD"));
                setValue(distTmsg.ajeCoaProjCd, rs.getString("AJE_COA_PROJ_CD"));
                setValue(distTmsg.ajeCoaExtnCd, rs.getString("AJE_COA_EXTN_CD"));
                
                // COA Branch to be re-set according to latest AJE pattern. 
                // determine AJE pattern
                List<NFAC000101PMsg> pMsgList = cmmnFunc.getAJEPtrnByAjeIdAndIndTp(rs.getString("AJE_ID"), rs, listAjePtrn);

                // only one pattern
                
                JRNL_ENTRYTMsg jrnlEntry = new JRNL_ENTRYTMsg();
                for (NFAC000101PMsg ptrn : pMsgList) {

                    // only when @TOC, replace the value with latest value on Sales Rep.
                    if ("@TOC".equals(ptrn.crAjeCoaBrCd.getValue())) {
                    
                        // determine the value by common function
                        if (!cmmnFunc.setValueToJrnlEntryByAjePtrn(jrnlEntry, CoaSegment.BR, "cr", ptrn, rs, null)) {
                            // error
                            setErrorMsg(errTmsg, S21MessageFunc.clspGetMessage(COA_ERROR_MSG_ID, new String[] {cmmnFunc.getCoAMessage(CoaSegment.BR)}));
                        }
                        
                        //---- start QC#16075 2016/12/12 If COA value can't be retrieved, default value is set and that is logged to comment text.
                        setValue(distTmsg.ajeIntfcCmntTxt, jrnlEntry.ajeIntfcCmntTxt.getValue());
                        //---- end QC#16075
                        
                    } else {
                        jrnlEntry.crCoaBrCd.setValue(rs.getString("AJE_COA_BR_CD"));
                    }
                    break;  // only one pattern is selected. not need to loop
                }
                setValue(distTmsg.ajeCoaBrCd, jrnlEntry.crCoaBrCd.getValue());
                
            }
            
            // call validation API
            glCodeCombinationCheck(distTmsg, errTmsg);
            
            // create AJE_INV_ACCT_DIST
            // QC#19715 START
            BigDecimal distPk = createAcctDist(distTmsg, rs, hasValue(errTmsg.invErrMsgTxt), lineIdxNum);
            // QC#19715 END

            // if there's any error, crate AJE_INV_ACCT_DIST
            if (hasValue(errTmsg.invErrMsgTxt)) {
                createAcctDistErr(errTmsg, distTmsg, rs);
            }
            
            // update DS_INV_SLS_CR when it's credit record. (last one per DS_INV_SLS_CR)
            if ("C".equals(rs.getString("DR_CR_TP_CD"))) {
                updateSlsCr(rs, distTmsg.jrnlDealAmt.getValue(), numOfCnt, isTrmntd);
            }
            
        }  // while
        
        // process remaining data
        submitInsert(acctDistList, true);
        submitErrInsert(acctDistErrList, true);
        submitUpdate(slsCrList, true);
        S21InfoLogOutput.println("Size : acctDistList= " + acctDistList.size() + "_acctDistErrList = " + acctDistErrList.size() + "_slsCrList = " + slsCrList.size());
        S21InfoLogOutput.println("End mainProcess  ================================ END");
    }
    
    // START 2017/11/20 [QC#17089, ADD]
    private boolean isCratTrnsfJrnl(ResultSet rs) throws SQLException {
        if (!ZYPConstant.FLG_ON_Y.equals(rs.getString("CM_EXST_FLG"))) {
            return false;
        }
        BigDecimal dealDfrdBalAmt = rs.getBigDecimal("DEAL_DFRD_BAL_AMT");
        if (!ZYPCommonFunc.hasValue(dealDfrdBalAmt)) {
            return false;
        }
        if (BigDecimal.ZERO.compareTo(dealDfrdBalAmt) == 0) {
            return false;
        }
        return true;
    }
    // END   2017/11/20 [QC#17089, ADD]
    
    private void updateSlsCr(ResultSet rs, BigDecimal recogAmt, int cnt, boolean isTrmntd) throws SQLException, ParseException{
        boolean isLast = false;
        
        DS_INV_SLS_CRTMsg tmsg = new DS_INV_SLS_CRTMsg();
        
        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.dsInvSlsCrPk, rs.getBigDecimal("DS_INV_SLS_CR_PK"));
        
        tmsg = (DS_INV_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);
        
        setValue(tmsg.dealDfrdBalAmt, nullVal(rs.getBigDecimal("DEAL_DFRD_BAL_AMT")).add(recogAmt.negate()));
        setValue(tmsg.funcDfrdBalAmt, tmsg.dealDfrdBalAmt);
        
        if (!hasValue(tmsg.firstRevRecogDt)) {
            setValue(tmsg.firstRevRecogDt, this.batProcDt);
        }
        
        setValue(tmsg.revRecogCnt, new BigDecimal(rs.getInt("REV_RECOG_CNT") + cnt));
        
        // If balance is zero, this is last recognition. (it will include terminated.)
        if (BigDecimal.ZERO.compareTo(tmsg.dealDfrdBalAmt.getValue()) == 0) {
            isLast = true;
        }
        
        if (isLast) {
            setValue(tmsg.nextRevRecogDt, "99991231");
            if (isTrmntd) {
                setValue(tmsg.revRecogProcStsCd, "9");  // terminated
            } else {
                setValue(tmsg.revRecogProcStsCd, PROC_STS.COMPLEATED);
            }
            
            setValue(tmsg.dealSchdRevAmt, BigDecimal.ZERO);
            setValue(tmsg.funcSchdRevAmt, BigDecimal.ZERO);
            setValue(tmsg.cpltRevRecogDt, this.batProcDt);
        } else {
            setValue(tmsg.nextRevRecogDt, getNextRecogDt(tmsg.durnStartDt.getValue()));
            setValue(tmsg.revRecogProcStsCd, "2");  // in progress
            setValue(tmsg.dealSchdRevAmt, calcNextRecogAmt(tmsg, rs.getInt("AFT_DECL_PNT_DIGIT_NUM")));
            setValue(tmsg.funcSchdRevAmt, tmsg.dealSchdRevAmt.getValue());
        }
        
        slsCrList.add(tmsg);
        
        submitUpdate(slsCrList, false);
    }
    
    private BigDecimal calcNextRecogAmt(DS_INV_SLS_CRTMsg tmsg, int scale) {
        BigDecimal balance = tmsg.dealDfrdBalAmt.getValue();   // updated balance by this recognition
        int dfrdDurnAot = tmsg.dfrdAcctgRuleDurnAot.getValueInt();
        int revRecogCnt = tmsg.revRecogCnt.getValueInt();  // updated recognition number of count by this recognition
        
        if (dfrdDurnAot - revRecogCnt == 0) {
            return BigDecimal.ZERO;
        }
        
        return balance.divide(new BigDecimal(dfrdDurnAot - revRecogCnt), scale, HALF_UP);
        
    }
    
    private String getNextRecogDt(String durnStDt) throws ParseException{
        String year = this.batProcDt.substring(0, 4);
        String month = this.batProcDt.substring(4, 6);
        String date = durnStDt.substring(6, 8);
        
        Calendar cal = Calendar.getInstance();

        // YYYYMM of process date and DD of duration start date
        cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date));
        
        // next month
        // This returns last date of the month if the date doesn't exist in the next month. ex. next month of 2016/01/31 return 2016/2/29
        cal.add(Calendar.MONTH, 1);
        
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);  // month is 0 to 11. need to add 1.
        if (month.length() == 1) {
            month = "0".concat(month);
        }
        
        date = String.valueOf(cal.get(Calendar.DATE));
        if (date.length() == 1) {
            date = "0".concat(date);
        }
        
        return year.concat(month).concat(date);
    }
    
    private BigDecimal nullVal(BigDecimal val) {
        if (hasValue(val)) {
            return val;
        }
        return BigDecimal.ZERO;
    }
    
    private void createAcctDistErr(AJE_INV_ACCT_DIST_ERRTMsg tmsg, AJE_INV_ACCT_DISTTMsg distTmsg, ResultSet rs) throws SQLException{
        
        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_ERR_SQ);

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.ajeInvAcctDistErrPk, seqNum);
        setValue(tmsg.ajeInvAcctDistPk, distTmsg.ajeInvAcctDistPk.getValue());
        
        setValue(tmsg.invNum, rs.getString("INV_NUM"));
        setValue(tmsg.invBolLineNum, rs.getString("INV_BOL_LINE_NUM"));
        setValue(tmsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(tmsg.invLineSubNum, rs.getString("INV_LINE_SUB_NUM"));
        setValue(tmsg.invTrxLineSubNum, rs.getString("INV_TRX_LINE_SUB_NUM"));
        
        setValue(tmsg.invldValTxt, getCoaConcatString(distTmsg));
        
        acctDistErrList.add(tmsg);
        
        submitErrInsert(acctDistErrList, false);
        
    }
    
    private String getCoaConcatString(AJE_INV_ACCT_DISTTMsg distTmsg) {
        String val = distTmsg.ajeCoaCmpyCd.getValue().concat(",").concat(distTmsg.ajeCoaBrCd.getValue()).concat(",").concat(distTmsg.ajeCoaCcCd.getValue())
                    .concat(",").concat(distTmsg.ajeCoaAcctCd.getValue()).concat(",").concat(distTmsg.ajeCoaProdCd.getValue()).concat(",")
                    .concat(distTmsg.ajeCoaChCd.getValue()).concat(",").concat(distTmsg.ajeCoaAfflCd.getValue()).concat(",").concat(distTmsg.ajeCoaProjCd.getValue())
                    .concat(",").concat(distTmsg.ajeCoaExtnCd.getValue());
        
        return val;
    }
    
    private BigDecimal createAcctDist(AJE_INV_ACCT_DISTTMsg tmsg, ResultSet rs, boolean isErr, int lineIdxNum) throws SQLException{
        
        BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal(AJE_INV_ACCT_DIST_SQ);

        setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tmsg.ajeInvAcctDistPk, seqNum);
        
        setValue(tmsg.invNum, rs.getString("INV_NUM"));
        setValue(tmsg.invBolLineNum, rs.getString("INV_BOL_LINE_NUM"));
        setValue(tmsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(tmsg.invLineSubNum, rs.getString("INV_LINE_SUB_NUM"));
        setValue(tmsg.invLineSubTrxNum, rs.getString("INV_TRX_LINE_SUB_NUM"));
        setValue(tmsg.procDt, this.batProcDt);
        // START 2016/09/26 S.Fujita [QC#13362,MOD]
//      setValue(tmsg.ajeInvAcctClsCd, rs.getString("AJE_INV_ACCT_CLS_CD"));
        setValue(tmsg.ajeInvAcctClsCd, AJE_INV_ACCT_CLS.REVENUE_EARNED);
        // END   2016/09/26 S.Fujita [QC#13362,MOD]
        setValue(tmsg.drCrTpCd, rs.getString("DR_CR_TP_CD"));
        
        setValue(tmsg.dealCcyCd, rs.getString("DEAL_CCY_CD"));
        setValue(tmsg.funcCcyCd, rs.getString("STD_CCY_CD"));
        setValue(tmsg.ajeInvAcctDistPct, rs.getBigDecimal("AJE_INV_ACCT_DIST_PCT"));
        setValue(tmsg.glDt, this.batProcDt);
        setValue(tmsg.dsInvSlsCrPk, rs.getBigDecimal("DS_INV_SLS_CR_PK"));
        
        if (isErr) {
            setValue(tmsg.procStsCd, PROC_STS.ERROR);
        } else {
            setValue(tmsg.procStsCd, PROC_STS.COMPLEATED);
        }
        setValue(tmsg.dfrdRevGlStrgFlg, FLG_OFF_N);

        // Mod Start 2017/09/29 QC#21254
        setValue(tmsg.sysSrcCd, rs.getString("SYS_SRC_CD"));  // create with AJE system source code
        setValue(tmsg.sysSrcNm, rs.getString("SYS_SRC_NM"));
//        setValue(tmsg.sysSrcCd, SYS_SRC.S21_ACCOUNTING_AJE);  // create with AJE system source code
//        setValue(tmsg.sysSrcNm, sysSrcNm);
        // Mod End 2017/09/29 QC#21254
        setValue(tmsg.trxCd, rs.getString("TRX_CD"));
        setValue(tmsg.trxNm, rs.getString("TRX_RSN_NM"));
        setValue(tmsg.trxRsnCd, rs.getString("TRX_RSN_CD"));
        setValue(tmsg.trxRsnNm, rs.getString("TRX_RSN_NM"));
        
        setValue(tmsg.ajePtrnIndTpCd_01, rs.getString("AJE_PTRN_IND_TP_CD_01"));
        setValue(tmsg.ajePtrnIndTpNm_01, rs.getString("AJE_PTRN_IND_TP_NM_01"));
        setValue(tmsg.ajePtrnActlCd_01, rs.getString("AJE_PTRN_ACTL_CD_01"));
        setValue(tmsg.ajePtrnActlNm_01, rs.getString("AJE_PTRN_ACTL_NM_01"));
        
        setValue(tmsg.ajePtrnIndTpCd_02, rs.getString("AJE_PTRN_IND_TP_CD_02"));
        setValue(tmsg.ajePtrnIndTpNm_02, rs.getString("AJE_PTRN_IND_TP_NM_02"));
        setValue(tmsg.ajePtrnActlCd_02, rs.getString("AJE_PTRN_ACTL_CD_02"));
        setValue(tmsg.ajePtrnActlNm_02, rs.getString("AJE_PTRN_ACTL_NM_02"));
        
        setValue(tmsg.ajePtrnIndTpCd_03, rs.getString("AJE_PTRN_IND_TP_CD_03"));
        setValue(tmsg.ajePtrnIndTpNm_03, rs.getString("AJE_PTRN_IND_TP_NM_03"));
        setValue(tmsg.ajePtrnActlCd_03, rs.getString("AJE_PTRN_ACTL_CD_03"));
        setValue(tmsg.ajePtrnActlNm_03, rs.getString("AJE_PTRN_ACTL_NM_03"));

        // Mod Start 2017/09/29 QC#21254
        setValue(tmsg.jrnlSrcCd, rs.getString("JRNL_SRC_CD"));
        setValue(tmsg.jrnlSrcNm, rs.getString("JRNL_SRC_NM"));
//        setValue(tmsg.jrnlSrcCd, SYS_SRC.S21_ACCOUNTING_AJE);
//        setValue(tmsg.jrnlSrcNm, sysSrcNm);
        // Mod End 2017/09/29 QC#21254

        setValue(tmsg.jrnlCatgCd, rs.getString("JRNL_CATG_CD"));
        setValue(tmsg.jrnlCatgNm, rs.getString("JRNL_CATG_NM"));
        // QC#19715 START
        setValue(tmsg.ajeLineIdxNum, String.format("%02d", lineIdxNum));
        // QC#19715 END
        setValue(tmsg.ajeLineIdxDescTxt, rs.getString("AJE_LINE_IDX_DESC_TXT"));
        
        acctDistList.add(tmsg);
        
        submitInsert(acctDistList, false);
        
        return seqNum;
    }
    
    private void submitInsert(List<AJE_INV_ACCT_DISTTMsg> list, boolean isLast) {
        if (list.size() == 0) {
            return;
        }
        
        if (list.size() == BULK_INSERT_CNT || isLast) {
            
            int rtn = S21FastTBLAccessor.insert(list.toArray(new AJE_INV_ACCT_DISTTMsg[list.size()]));
            
            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }
            
            normCnt += rtn;
            
            // reset
            list.clear();
        }
    }
    
    private void submitErrInsert(List<AJE_INV_ACCT_DIST_ERRTMsg> list, boolean isLast) {
        if (list.size() == 0) {
            return;
        }
        
        if (list.size() == BULK_INSERT_CNT || isLast) {
            
            int rtn = S21FastTBLAccessor.insert(list.toArray(new AJE_INV_ACCT_DIST_ERRTMsg[list.size()]));
            
            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }
            
            errCnt += rtn;
            
            // reset
            list.clear();
        }
    }
    
    private void submitUpdate(List<DS_INV_SLS_CRTMsg> list, boolean isLast) {
        if (list.size() == 0) {
            return;
        }
        
        if (list.size() == BULK_INSERT_CNT || isLast) {
            
            int rtn = S21FastTBLAccessor.update(list.toArray(new DS_INV_SLS_CRTMsg[list.size()]));
            
            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }
             
            // reset
            list.clear();
        }
    }
    
    private void submitUpdateDist(List<AJE_INV_ACCT_DISTTMsg> list, boolean isLast) {
        if (list.size() == 0) {
            return;
        }
        
        if (list.size() == BULK_INSERT_CNT || isLast) {
            
            int rtn = S21FastTBLAccessor.update(list.toArray(new AJE_INV_ACCT_DISTTMsg[list.size()]));
            
            if (rtn != list.size()) {
                throw new S21AbendException(UNIQUE_ERROR);
            }
             
            // reset
            list.clear();
        }
    }
    
    private void setErrorMsg(AJE_INV_ACCT_DIST_ERRTMsg tmsg, String newMsg) {
        String msg;
        if (hasValue(tmsg.invErrMsgTxt)) {
            if ((tmsg.invErrMsgTxt.getValue() + ", " + newMsg).length() > ERR_MSG_TXT_LEN) {
                // if length exceeds, not to add new message.
                msg = tmsg.invErrMsgTxt.getValue();
            } else {
                msg = tmsg.invErrMsgTxt.getValue() + ", " + newMsg;
            }

        } else {
            msg = newMsg;
        }
        
        tmsg.invErrMsgTxt.setValue(msg);
    }
    
    /**
     * GL Code Combination Check
     * @param param
     * @param ajeInvAcctDist
     * @param ajeInvAcctDistErr
     */
    private void glCodeCombinationCheck(AJE_INV_ACCT_DISTTMsg tmsg, AJE_INV_ACCT_DIST_ERRTMsg ajeInvAcctDistErr) {

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        apiMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        
        apiMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_OFF_N);
        apiMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
        apiMsg.xxArcsApiChkFlg.setValue(ZYPConstant.FLG_OFF_N);

        apiMsg.coaCmpyCd.setValue(tmsg.ajeCoaCmpyCd.getValue());
        apiMsg.coaBrCd.setValue(tmsg.ajeCoaBrCd.getValue());
        apiMsg.coaCcCd.setValue(tmsg.ajeCoaCcCd.getValue());
        apiMsg.coaAcctCd.setValue(tmsg.ajeCoaAcctCd.getValue());
        apiMsg.coaProdCd.setValue(tmsg.ajeCoaProdCd.getValue());
        apiMsg.coaChCd.setValue(tmsg.ajeCoaChCd.getValue());
        apiMsg.coaAfflCd.setValue(tmsg.ajeCoaAfflCd.getValue());
        apiMsg.coaProjCd.setValue(tmsg.ajeCoaProjCd.getValue());
        apiMsg.coaExtnCd.setValue(tmsg.ajeCoaExtnCd.getValue());

        // START 2016/09/28 K.Kojima [QC#14609,ADD]
        apiMsg.bizAppId.setValue(BUZ_APP_ID);
        // END 2016/09/28 K.Kojima [QC#14609,ADD]

        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);

        int i = 0;
        String msgId;
        String msgTxt;
        for (; i < apiMsg.xxMsgIdList.getValidCount(); i++) {
        
            msgId = apiMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();

            setErrorMsg(ajeInvAcctDistErr, S21MessageFunc.clspGetMessage(msgId, new String[] {msgTxt }));

        }
        
    }

    private int calcRecogAmt(AJE_INV_ACCT_DISTTMsg tmsg, String startDt, int revRecogCnt, int dfrdDurnAot, BigDecimal balance, int scale) throws ParseException{
        int cnt;
        
        // calculate count of times
        cnt = differenceMonth(strDateFormat(this.batProcDt), strDateFormat(startDt)) - revRecogCnt;
        
        // recognition is already done somehow..
        if (cnt < 0) {
            // START 2018/05/17 S.Katsuma [QC#25822,ADD]
            setValue(tmsg.jrnlDealAmt, new BigDecimal(0));
            setValue(tmsg.jrnlFuncAmt, new BigDecimal(0));
            // END 2018/05/17 S.Katsuma [QC#25822,ADD]
            return 0;
        // will not happen, but take care anyway
        } else if ((cnt + revRecogCnt) > dfrdDurnAot) {
            cnt = dfrdDurnAot - revRecogCnt;
        }
        
        boolean isLast = false;
        
        if ((cnt + revRecogCnt) >= dfrdDurnAot) {
            isLast = true;
        }
      
        // If this is the last recognition, entire balance to be recognized
        if (isLast) {
            setValue(tmsg.jrnlDealAmt, balance);
            setValue(tmsg.jrnlFuncAmt, balance);
        } else {
        
            // calculate the amount for one month
            BigDecimal amtPerMonth = balance.divide(new BigDecimal(dfrdDurnAot - revRecogCnt), scale, HALF_UP);
            
            // calculate the amount to be recognized this time
            BigDecimal thisTimeAmt = amtPerMonth.multiply(new BigDecimal(cnt));
            setValue(tmsg.jrnlDealAmt, thisTimeAmt);
            setValue(tmsg.jrnlFuncAmt, thisTimeAmt);
        }
        
        return cnt;
        
    }
    
    private PreparedStatement getTargetData() throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("batProcDt", this.batProcDt);
        map.put("flgY", FLG_ON_Y);
        map.put("notProcessed", REV_RECOG_STS_NOT_PROCESSED);
        map.put("inProcess", REV_RECOG_STS_IN_PROCESS);
        map.put("completed", PROC_STS.COMPLEATED);
        // START 2017/02/17 H.Ikeda [QC#15637,ADD]
        map.put("subscription", DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE);
        // END   2017/02/17 H.Ikeda [QC#15637,ADD]
        // START 2017/11/20 [QC#17089, ADD]
        map.put("invTpCdCm", INV_TP.CREDIT_MEMO);
        // END   2017/11/20 [QC#17089, ADD]
        // START 2019/01/07 E.Kameishi [QC#29734,ADD]
        String varRental = ZYPCodeDataUtil.getVarCharConstValue("AJE_RENTAL_SVC_ALLOC_TP_CD", glblCmpyCd);
        List<String> varRentalCdList = new ArrayList<String>();

        if (varRental != null) {
            String[] varRentalList = varRental.split(",");
            varRentalCdList = Arrays.asList(varRentalList);
        }
        map.put("varRental", varRentalCdList);
        map.put("re", SVC_ALLOC_IND_TP.RENTAL);
        map.put("nonRe", SVC_ALLOC_IND_TP.NON_RENTAL);
        // END 2019/01/07 E.Kameishi [QC#29734,ADD]
        
        // START 2018/01/24 [QC#23692, MOD]
        // return this.ssmLLClient.createPreparedStatement("getTargetData", map);
        S21SsmExecutionParameter execParam = getSsmExecuteParameter();
        return this.ssmLLClient.createPreparedStatement("getTargetData", map, execParam);
        // END   2018/01/24 [QC#23692, MOD]
    }
    
    private PreparedStatement getTargetDataForTermination() throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("batProcDt", this.batProcDt);
        map.put("flgY", FLG_ON_Y);
        map.put("notProcessed", REV_RECOG_STS_NOT_PROCESSED);
        map.put("inProcess", REV_RECOG_STS_IN_PROCESS);

        // START 2017/02/09 S.Fujita [QC#14655,MOD]
//        map.put("trmd", DS_CONTR_DTL_STS.TERMINATED);
        String contrInactvStsCd = ZYPCodeDataUtil.getVarCharConstValue(AJE_CONTR_INACTV_STS, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(contrInactvStsCd)) {
            String[] contrInactvStsCdArray = contrInactvStsCd.split(STR_COMMA);
            map.put("contrInactvStsCdArray", contrInactvStsCdArray);
        }
        String contrActvStsCd = ZYPCodeDataUtil.getVarCharConstValue(AJE_CONTR_ACTV_STS, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(contrActvStsCd)) {
            String[] contrActvStsCdArray = contrActvStsCd.split(STR_COMMA);
            map.put("contrActvStsCd", contrActvStsCdArray);
        }
        map.put("maxEndDate", MAX_END_DATE);
        // END   2017/02/09 S.Fujita [QC#14655,MOD]

        map.put("completed", PROC_STS.COMPLEATED);
        // START 2017/02/17 H.Ikeda [QC#15637,ADD]
        map.put("subscription", DFRD_ACCTG_RULE.SUBSCRIPTION_SERVICE);
        // END   2017/02/17 H.Ikeda [QC#15637,ADD]
        // START 2017/11/20 [QC#17089, ADD]
        map.put("invTpCdCm", INV_TP.CREDIT_MEMO);
        // END   2017/11/20 [QC#17089, ADD]
        // START 2017/12/20 E.Kameishi [QC#22912,ADD]
        map.put("invLineSplTpAlloc", INV_LINE_SPL_TP.ALLOCATION);
        // END 2017/12/20 E.Kameishi [QC#22912,ADD]
        // START 2018/07/10 E.Kameishi [QC#26843,ADD]
        map.put("svcExch", ORD_CATG_CTX_TP.SERVICE_EXCHANGE_ORDER_VALUE_SET);
        // END 2018/07/10 E.Kameishi [QC#26843,ADD]

        // START 2019/01/07 E.Kameishi [QC#29734,ADD]
        String varRental = ZYPCodeDataUtil.getVarCharConstValue("AJE_RENTAL_SVC_ALLOC_TP_CD", glblCmpyCd);
        List<String> varRentalCdList = new ArrayList<String>();
        if (varRental != null) {
            String[] varRentalList = varRental.split(",");
            varRentalCdList = Arrays.asList(varRentalList);
        }
        map.put("varRental", varRentalCdList);
        map.put("re", SVC_ALLOC_IND_TP.RENTAL);
        map.put("nonRe", SVC_ALLOC_IND_TP.NON_RENTAL);
        // END 2019/01/07 E.Kameishi [QC#29734,ADD]

        // START 2018/01/24 [QC#23692, MOD]
        // return this.ssmLLClient.createPreparedStatement("getTargetDataForTermination", map);
        S21SsmExecutionParameter execParam = getSsmExecuteParameter();
        return this.ssmLLClient.createPreparedStatement("getTargetDataForTermination", map, execParam);
        // END   2018/01/24 [QC#23692, MOD]
    }
    
    private PreparedStatement getDfrdInv() throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("flgY", FLG_ON_Y);
        map.put("nfc", SYS_SRC.S21_ACCOUNTING_AR);
        map.put("service", SYS_SRC.S21_SERVICE);
        map.put("ur", FIXED_VALUE_UR);
        map.put("srt", FIXED_VALUE_SRT);

        map.put("dev", INV_LINE_SPL_TP.DEVIATION);
        map.put("alloc", INV_LINE_SPL_TP.ALLOCATION);
        map.put("invTpCr", INV_TP.CREDIT_MEMO);
        map.put("dfrdAcctgRuleIm", DFRD_ACCTG_RULE.IMMEDIATE);
        // START 2018/05/24 [QC#25311, ADD]
        map.put("warranty", DS_CONTR_TP.WARRANTY);
        // START 2018/05/24 [QC#25311, ADD]

        // START 2022/03/17 D.Mamaril [QC#59657, ADD]
        String defaultTermMthNum = ZYPCodeDataUtil.getVarCharConstValue(NFAB1030_DEFAULT_TERM_MTH_NUM, glblCmpyCd);
        map.put("defaultTermMthNum", Integer.valueOf(defaultTermMthNum));
        map.put("dsContrStsCancelled", DS_CONTR_STS.CANCELLED);
        // END 2022/03/17 D.Mamaril [QC#59657, ADD]

        // START 2018/01/24 [QC#23692, MOD]
        // return this.ssmLLClient.createPreparedStatement("getDfrdInv", map);
        S21SsmExecutionParameter execParam = getSsmExecuteParameter();
        return this.ssmLLClient.createPreparedStatement("getDfrdInv", map, execParam);
        // END   2018/01/24 [QC#23692, MOD]
    }
    
    private PreparedStatement getErrorDist() throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("error", PROC_STS.ERROR);

        // START 2018/01/24 [QC#23692, MOD]
        // return this.ssmLLClient.createPreparedStatement("getErrorDist", map);
        S21SsmExecutionParameter execParam = getSsmExecuteParameter();
        return this.ssmLLClient.createPreparedStatement("getErrorDist", map, execParam);
        // END   2018/01/24 [QC#23692, MOD]
    }
    
    /**
     * <pre>
     *  Get All Transaction Patterns in AJE Loan Depreciation Interface.
     * </pre>
     * 
     * @param
     */
    @SuppressWarnings("unchecked")
    private List<Map> getAjeIdList() {

        // ** Get All Transaction Pattern in AJE Loan Depreciation
        // Interface. **
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", this.glblCmpyCd);
        map.put("batProcDt", this.batProcDt);
        map.put("flgY", FLG_ON_Y);
        map.put("notProcessed", REV_RECOG_STS_NOT_PROCESSED);
        map.put("inProcess", REV_RECOG_STS_IN_PROCESS);
        // START 2017/11/28 [QC#17089-1, ADD]
        map.put("invTpCdCm", INV_TP.CREDIT_MEMO);
        // END   2017/11/28 [QC#17089-1, ADD]
        // START 2017/11/30 J.Kim [QC#21051,ADD]
        String contrInactvStsCd = ZYPCodeDataUtil.getVarCharConstValue(AJE_CONTR_INACTV_STS, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(contrInactvStsCd)) {
            String[] contrInactvStsCdArray = contrInactvStsCd.split(STR_COMMA);
            map.put("contrInactvStsCdArray", contrInactvStsCdArray);
        }
        String contrActvStsCd = ZYPCodeDataUtil.getVarCharConstValue(AJE_CONTR_ACTV_STS, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(contrActvStsCd)) {
            String[] contrActvStsCdArray = contrActvStsCd.split(STR_COMMA);
            map.put("contrActvStsCd", contrActvStsCdArray);
        }
        map.put("maxEndDate", MAX_END_DATE);
        // END 2017/11/30 J.Kim [QC#21051,ADD]

        List<Map> listTrxPtrn = (List<Map>) ssmBatchClient.queryObjectList("getAjeIdList", map);

        return listTrxPtrn;
    }
    
    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NFAB103001().executeBatch(NFAB103001.class.getSimpleName());

    }
    
    /**
     * return difference of two dates in months
     * @param strDate1    yyyy/MM/dd
     * @param strDate2    yyyy/MM/dd
     * @return int months
     * @throws ParseException
     */
    private int differenceMonth(String strDate1, String strDate2) throws ParseException {
        //---- start mod 2016/03/30 add locale and style
        Date date1 = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN).parse(strDate1);
        Date date2 = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN).parse(strDate2);
        //---- end 2016/03/30
        
        return differenceMonth(date1,date2);
    }
    /**
     * return difference of two dates in months
     * 
     * @param date1    java.util.Date
     * @param date2    java.util.Date
     * @return int months
     */
    private int differenceMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.set(Calendar.DATE, 1);
        Calendar cal2 = Calendar.getInstance(); 
        cal2.setTime(date2);
        cal2.set(Calendar.DATE, 1);
        int count = 0;
        while (!cal1.before(cal2) || cal1.compareTo(cal2) == 0) {
            cal1.add(Calendar.MONTH, -1);
            count++;
        }
        
        return count;
    }
    
    /**
     * convert YYYYMMDD to YYYY/MM/DD
     * @param str
     * @return String
     */
    private String strDateFormat(String str) {
        String rtn = str.substring(0, 4).concat("/").concat(str.substring(4, 6)).concat("/").concat(str.substring(6, 8));
        
        return rtn;
    }

    // START 2018/01/24 [QC#23692, ADD]
    private S21SsmExecutionParameter getSsmExecuteParameter() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(Integer.parseInt(this.usrVar1));
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }
    // END   2018/01/24 [QC#23692, ADD]
}
