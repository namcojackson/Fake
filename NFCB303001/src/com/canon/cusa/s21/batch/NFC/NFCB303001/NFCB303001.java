/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB303001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_ADJTMsg;
import business.db.AR_ADJ_COA_INFOTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_WRT_OFF_RQSTTMsg;
import business.db.AR_WRT_OFF_RQST_RSLTTMsg;
import business.parts.NFZC202001PMsg;
import business.parts.NFZC301001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.api.NFC.NFZC301001.NFZC301001;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.WrtOffTargetTrxBal;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21BatchUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Auto Write-Off
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 2015/11/05   CSAI            K.Uramori       Create          
 * 2016/08/24   Fujitsu         S.Yoshida       Update          QC12491
 * 2016/11/28   Hitachi         J.Kim           Update          QC16248
 * 2017/01/16   Fujitsu         T.Murai         Update          QC16867
 * 2018/04/10   Fujitsu         H.Ikeda         Update          QC24725
 * 2018/08/30   Hitachi         E.Kameishi      Update          QC#25188
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2018/11/14   Fujitsu         T.Ogura         Update          QC#29232
 * 2018/11/19   Fujitsu         T.Ogura         Update          QC#29332
 * 2018/11/19   Fujitsu         T.Ogura         Update          QC#29334
 * 2019/02/28   Fujitsu         S.Ohki          Update          QC#30584
 * 2019/03/07   Fujitsu         H.Ikeda         Update          QC#30593
 * 03/22/2019   Fujitsu         T.Ogura         Update          QC#30565
 * 2022/11/28   Hitachi         A.Kohinata      Update          QC#57252
 *</pre>
 */
public class NFCB303001 extends S21BatchMain implements ZYPConstant, NFCB3030Constant{
    
    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;
    
    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;
    
    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;
    
    /** batProcDate */
    private String batProcDate = null;
    
    /** Execution Mode */
    private String execMode = null;

    // START 2018/11/19 T.Ogura [QC#29334,DEL]
//    /** Ssm Batch Client */
//    private S21SsmBatchClient ssmBatchClient = null;
    // END   2018/11/19 T.Ogura [QC#29334,DEL]

    /** AR_WRT_OFF_RQST_RSLT List */
    List<AR_WRT_OFF_RQST_RSLTTMsg> listRslt = null;
    
    /** Account Date */
    private String acctDt = null;

    /** default TOC_CD */
    private String tocCd = null;
    
    /** default COA_PROD_CD */
    private String coaProdCd = null;
    
    /** AR_WRT_OFF_INV */
    private String arTrxTpInv = null;
    
    /** AR_WRT_OFF_CRM */
    private String arTrxTpCm = null;
    
    /** Collection Default Email Address */
    private String defEmlAddr = null;
    
    /** First Error message per request */
    private String firstErrMsg = null;
    
    private boolean isErrPerRqst = false;
    
    private List<BigDecimal> arWrtOffRqstErr = null;
    
    /** Termination Code */
    private TERM_CD termCd;
    
    /** Normal Counter */
    private int normCnt = 0;

    /** Error Counter */
    private int errCnt = 0;

//DEL Start QC12491
//    /** Previous Bill To Cust Code */
//    String prevBillTo = null;
//    
//    /** Current Bill To Cust Code */
//    String currBillTo = null;
//DEL End   QC12491
    
    /** Current Bill To Cust Code */
    boolean isErrOfTrx = false;
    
    /** Default email address list */
    private List<String> defEmlAddList = new ArrayList<String>();
    
    @Override
    protected void initRoutine() {
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // START 2018/11/19 T.Ogura [QC#29334,DEL]
//        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END   2018/11/19 T.Ogura [QC#29334,DEL]
        this.termCd = TERM_CD.NORMAL_END;
        
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {NFCDbConst.GLBL_CMPY_CD });
        }
        
       
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        
        // get user input parameter
        execMode = S21BatchUtil.getUserVariable1();

        // get account date
        getAcctDt();
        if (this.acctDt == null) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {"Account Date" });   
        }        

        // get values from CONST table
        tocCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_TOC_CD, this.glblCmpyCd);
        coaProdCd = ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_AR_RCPT_PROD_CD, this.glblCmpyCd); 
        arTrxTpInv = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_AR_WRT_OFF_INV, this.glblCmpyCd);
        arTrxTpCm = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_AR_WRT_OFF_CRM, this.glblCmpyCd);
        defEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_AR_CLT_DEF_EML_ADDR, this.glblCmpyCd);
        
        //---- start add 09/01/2016
        if (!hasValue(defEmlAddr)) {
            // error
            throw new S21AbendException("ZZZM9025E", new String[] {"Default Collector's Email Address" });
        }
        // split the email addresses
        divDefEmlAdd();
        //---- end 2016/09/01
        
        if ((arTrxTpInv == null || "".equals(arTrxTpInv)) || (arTrxTpCm == null || "".equals(arTrxTpCm))) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, new String[] {"Varchar Constant [AR_WRT_OFF_INV/AR_WRT_OFF_CRM]"});
        }
        
        listRslt = new ArrayList<AR_WRT_OFF_RQST_RSLTTMsg>();
        arWrtOffRqstErr = new ArrayList<BigDecimal>();  // To hold PK of errored request

    }

    private void divDefEmlAdd() {
        StringTokenizer st = new StringTokenizer(defEmlAddr, ",");

        while (st.hasMoreTokens()) {
            defEmlAddList.add(st.nextToken());
        }

    }
    
    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            // get target request data
            stmt = getRequestData();
            rs = stmt.executeQuery(); 
            
            // Per request
            while (rs.next()) {
                // initialize
                firstErrMsg = "";
                isErrPerRqst = false;
                
                // check parameter
                // START 2016/06/30 J.Kim [QC#16248,MOD]
                if (!AR_WRT_OFF_RQST_TP.COMPLETE_STRATEGY.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
                    if (chkParameter(rs) == false) {
                        updateWrtOffRqst(rs, S21MessageFunc.clspGetMessage(NFCM0768E), PROC_STS.ERROR);
                        commit();
                        // go to next request
                        continue;
                    }
                }
                // END 2016/06/30 J.Kim [QC#16248,MOD]
                
                // START 2018/11/19 T.Ogura [QC#29334,DEL]
//                // approval limit check
//                if (AR_WRT_OFF_RQST_TP.SYSTEM.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
//                    String errMsg = aprvlLimitCheck(rs); 
//                    if (!"".equals(errMsg)) {
//                        updateWrtOffRqst(rs, errMsg, PROC_STS.ERROR);
//                        commit();
//                        // go to next request
//                        continue;
//                    }
//                }
                // END   2018/11/19 T.Ogura [QC#29334,DEL]
              
                // get AR_TRX_BAL that matches the request and process the transaction
                if (!processTrxBal(rs)) {  
                    // unique constraint error of AR_WRT_OFF_RQST_RSLT
                    rollback();
                    throw new S21AbendException(NFDM0003E, new String[] {"Unique Constraint Error"});
                }
                
                // update AR_WRT_OFF_RQST
                String procSts = "";
                if (isErrPerRqst) {
                    procSts = PROC_STS.ERROR;
                } else {
                    procSts = PROC_STS.COMPLEATED;
                }
                updateWrtOffRqst(rs, firstErrMsg, procSts);
                commit();
                
            }  // end loop
            
            // If error occurs, send email to user
            if (arWrtOffRqstErr.size() != 0) {
                // get user information
                stmt = getUserOfErrRqst();
                rs = stmt.executeQuery();
                
                // Per request group and user
                while (rs.next()) {
                    // send mail
                    sendMail(rs);
                }
            }
            
        } catch (SQLException sqlEx) {
            rollback();
            throw new S21AbendException(NFDM0003E, new String[] {sqlEx.getMessage()});
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }               

    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, normCnt, errCnt, normCnt + errCnt);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NFCB303001().executeBatch(NFCB303001.class.getSimpleName());
    }
    
    
    private PreparedStatement getRequestData() throws SQLException{
        PreparedStatement stmt = null;
        
        Map<String, String> sqlParam = new HashMap<String, String>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("unProc", PROC_STS.IN_COMPLETED);
        sqlParam.put("wfSts", AR_DS_WF_STS.APPROVED);
        sqlParam.put("execMode", this.execMode);
        sqlParam.put("system", AR_WRT_OFF_RQST_TP.SYSTEM);
        
        stmt = this.ssmLLClient.createPreparedStatement("getRequestData", sqlParam);

        return stmt;
    }
    
    private PreparedStatement getUserOfErrRqst() throws SQLException{
        PreparedStatement stmt = null;
        
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", this.glblCmpyCd);
        sqlParam.put("pkList", arWrtOffRqstErr);
        
        stmt = this.ssmLLClient.createPreparedStatement("getUserOfErrRqst", sqlParam);

        return stmt;
    }
    
    private boolean chkParameter(ResultSet rs) throws SQLException{
        
        if (rs.getString(AR_ADJ_TP_NM) == null) {
           return false;
        }
        
        // In case system, or user request, amount is mandatory 
        if (AR_WRT_OFF_RQST_TP.SYSTEM.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD)) || AR_WRT_OFF_RQST_TP.USER_REQUEST.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
            if (rs.getBigDecimal(LOW_RMNG_BAL_AMT) == null || rs.getBigDecimal(HIGH_RMNG_BAL_AMT) == null) {
                return false;
            }
        }
        
        if (rs.getBigDecimal(LOW_RMNG_BAL_AMT) != null && rs.getBigDecimal(HIGH_RMNG_BAL_AMT) != null) {
            if (rs.getBigDecimal(LOW_RMNG_BAL_AMT).compareTo(rs.getBigDecimal(HIGH_RMNG_BAL_AMT)) == 1) {
                return false;
            }
        }

        if (rs.getString(LOW_INV_NUM) != null && rs.getString(HIGH_INV_NUM) != null) {
            if (rs.getString(LOW_INV_NUM).compareTo(rs.getString(HIGH_INV_NUM)) == 1) {
                return false;
            }
        }

        if (rs.getString(LOW_INV_DUE_DT) != null && rs.getString(HIGH_INV_DUE_DT) != null) {
            if (rs.getString(LOW_INV_DUE_DT).compareTo(rs.getString(HIGH_INV_DUE_DT)) == 1) {
                return false;
            }
        }
        if (rs.getString(LOW_DS_ACCT_NUM) != null && rs.getString(HIGH_DS_ACCT_NUM) != null) {
            if (rs.getString(LOW_DS_ACCT_NUM).compareTo(rs.getString(HIGH_DS_ACCT_NUM)) == 1) {
                return false;
            }
        }
        if (rs.getString(LOW_BILL_TO_CUST_CD) != null && rs.getString(HIGH_BILL_TO_CUST_CD) != null) {
            if (rs.getString(LOW_BILL_TO_CUST_CD).compareTo(rs.getString(HIGH_BILL_TO_CUST_CD)) == 1) {
                return false;
            }
        }
        
        if (!"1".equals(rs.getString(WRT_OFF_OPT_TP_CD)) && !"2".equals(rs.getString(WRT_OFF_OPT_TP_CD))) {
            return false;
        }

        return true;
        
    }

    private void updateWrtOffRqst(ResultSet rs, String msg, String sts) throws SQLException{

        AR_WRT_OFF_RQSTTMsg cond = new AR_WRT_OFF_RQSTTMsg();
        AR_WRT_OFF_RQSTTMsg upd = new AR_WRT_OFF_RQSTTMsg();
        
        ZYPEZDItemValueSetter.setValue(cond.arWrtOffRqstPk, rs.getBigDecimal(AR_WRT_OFF_RQST_PK));
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, this.glblCmpyCd);
        if (!AR_WRT_OFF_RQST_TP.SYSTEM.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(upd.procStsCd, sts);
        } else {
            ZYPEZDItemValueSetter.setValue(upd.procStsCd, PROC_STS.IN_COMPLETED);
        }
        
        ZYPEZDItemValueSetter.setValue(upd.wrtOffErrMsgTxt, msg);
        ZYPEZDItemValueSetter.setValue(upd.wrtOffProcDt, this.batProcDate);

        int cnt = S21FastTBLAccessor.updateByPartialValue(cond, new String[] {"glblCmpyCd", "arWrtOffRqstPk" }, upd, new String[] {"procStsCd", "wrtOffErrMsgTxt", "wrtOffProcDt" });

       if (cnt != 1) {
           rollback();
           throw new S21AbendException(NFDM0003E, new String[] {S21MessageFunc.clspGetMessage(NFCM0770E)});
       }
       
       // store PK of error request
       if (PROC_STS.ERROR.equals(sts)) {
           arWrtOffRqstErr.add(rs.getBigDecimal(AR_WRT_OFF_RQST_PK));
           errCnt++;
       } else {
           normCnt++;
       }
      
    }

    // START 2018/11/19 T.Ogura [QC#29334,DEL]
//    @SuppressWarnings("unchecked")
//    private String aprvlLimitCheck(ResultSet rs) throws SQLException{
//        String errMsg = "";
//        
//        Map<String, String> param = new HashMap<String, String>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("usrId", rs.getString(WRT_OFF_RQST_USR_ID));
//        param.put("arAdjCatgCd", rs.getString(AR_ADJ_CATG_CD));
//        param.put("flgY", FLG_ON_Y);
//        param.put("flgN", FLG_OFF_N);
//
//        Map<String, BigDecimal> rslt = (Map<String, BigDecimal>) ssmBatchClient.queryObject("getApprovalLimit", param);
//
//        if (rslt == null) {
//            errMsg = S21MessageFunc.clspGetMessage(NFCM0769E);
//        } else {
//            BigDecimal fromAmt = rslt.get(APVL_LIMIT_FROM_AMT);
//            BigDecimal toAmt = rslt.get(APVL_LIMIT_TO_AMT);
//            BigDecimal lowAmt = rs.getBigDecimal(LOW_RMNG_BAL_AMT);  // mandatory check has been gone through so should not be null
//            BigDecimal highAmt = rs.getBigDecimal(HIGH_RMNG_BAL_AMT); // mandatory check has been gone through so should not be null
// 
//            if (fromAmt == null) {
//                fromAmt = BigDecimal.ZERO;
//            }
//            if (toAmt == null) {
//                toAmt = BigDecimal.ZERO;
//            }
//            
//            if (lowAmt.compareTo(fromAmt) >= 0 && toAmt.compareTo(highAmt) >= 0) {
//                // OK
//            } else {
//                errMsg = S21MessageFunc.clspGetMessage(NFCM0084E);
//            }            
//        }
//        return errMsg;
//    }
    // END   2018/11/19 T.Ogura [QC#29334,DEL]

    private boolean processTrxBal(ResultSet rs) throws SQLException{
        String adjNum = "";

//ADD Start QC12491
        String prevBillTo = null;
        String currBillTo = null;
//ADD End   QC12491

        AR_WRT_OFF_RQSTTMsg param = new AR_WRT_OFF_RQSTTMsg();
        // set the parameter of the request
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, rs.getString(GLBL_CMPY_CD));
        ZYPEZDItemValueSetter.setValue(param.arWrtOffRqstPk, rs.getBigDecimal(AR_WRT_OFF_RQST_PK));
        // START 2016/06/30 J.Kim [QC#16248,MOD]
        // ZYPEZDItemValueSetter.setValue(param.lowRmngBalAmt, rs.getBigDecimal(LOW_RMNG_BAL_AMT));
        // ZYPEZDItemValueSetter.setValue(param.highRmngBalAmt, rs.getBigDecimal(HIGH_RMNG_BAL_AMT));
        if (!checkNullRmngBalAmt(rs.getBigDecimal(LOW_RMNG_BAL_AMT), rs.getBigDecimal(HIGH_RMNG_BAL_AMT))) {
            ZYPEZDItemValueSetter.setValue(param.lowRmngBalAmt, rs.getBigDecimal(LOW_RMNG_BAL_AMT));
            ZYPEZDItemValueSetter.setValue(param.highRmngBalAmt, rs.getBigDecimal(HIGH_RMNG_BAL_AMT));
        }
        // END 2016/06/30 J.Kim [QC#16248,MOD]
        ZYPEZDItemValueSetter.setValue(param.lowInvNum, rs.getString(LOW_INV_NUM));
        ZYPEZDItemValueSetter.setValue(param.highInvNum, rs.getString(HIGH_INV_NUM));
        ZYPEZDItemValueSetter.setValue(param.lowInvDueDt, rs.getString(LOW_INV_DUE_DT));
        ZYPEZDItemValueSetter.setValue(param.highInvDueDt, rs.getString(HIGH_INV_DUE_DT));
        ZYPEZDItemValueSetter.setValue(param.lowDsAcctNum, rs.getString(LOW_DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(param.highDsAcctNum, rs.getString(HIGH_DS_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(param.lowBillToCustCd, rs.getString(LOW_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(param.highBillToCustCd, rs.getString(HIGH_BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(param.invTpCd, rs.getString(INV_TP_CD));
        ZYPEZDItemValueSetter.setValue(param.inclConslInvFlg, rs.getString(INCL_CONSL_INV_FLG));
        // START 2019/03/07 H.Ikeda [QC#30593,MOD]
        ZYPEZDItemValueSetter.setValue(param.arWrtOffRqstTpCd, rs.getString(AR_WRT_OFF_RQST_TP_CD));
        // END   2019/03/07 H.Ikeda [QC#30593,MOD]
        List<String> arTrxTpList = new ArrayList<String>();
        
        if (INV_TP.INVOICE.equals(rs.getString(INV_TP_CD))) {
            StringTokenizer st = new StringTokenizer(this.arTrxTpInv , ",");
            
            while (st.hasMoreTokens()) {
                arTrxTpList.add(st.nextToken());
            }
            
        } else {
            StringTokenizer st = new StringTokenizer(this.arTrxTpCm , ",");
            
            while (st.hasMoreTokens()) {
                arTrxTpList.add(st.nextToken());
            }
        }
        
        //---- start 20160829 Invoice type condition has to be same as the one request created.
        if (AR_WRT_OFF_RQST_TP.COMPLETE_STRATEGY.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(param.invTpCd, INV_TP.INVOICE);
            arTrxTpList.add(AR_TRX_TP.INVOICE);
            arTrxTpList.add(AR_TRX_TP.DEBIT_MEMO);
            // START 2018/04/10 H.Ikeda [QC#24725,DEL]
            //arTrxTpList.add(AR_TRX_TP.DEDUCTION);
            // START 2018/04/10 H.Ikeda [QC#24725,DEL]
        }
        //---- end 20160829

        // START 2018/11/14 T.Ogura [QC#29232,ADD]
        if (!ZYPCommonFunc.hasValue(rs.getString(INV_TP_CD)) && AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(param.invTpCd, INV_TP.INVOICE);
            arTrxTpList.add(AR_TRX_TP.INVOICE);
            arTrxTpList.add(AR_TRX_TP.DEDUCTION);
        }
        // END   2018/11/14 T.Ogura [QC#29232,ADD]

        // get target transaction
        //---- start 2016/04/07 QC#6751 Rsvd amount should be ignored
        List<WrtOffTargetTrxBal> trxList= NFCCmnMethod.getWrtOffTargetDataResrc(param, arTrxTpList);
        //---- end 2016/04/07
        
        if (trxList == null) {
            // error
            return false;
        }
        
        // loop
        for (WrtOffTargetTrxBal thisRec : trxList) {

            // START 2018/11/19 T.Ogura [QC#29332,ADD]
            if (AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD)) && !ZYPCommonFunc.hasValue(thisRec.getInvTrxBalLastUpdTs())) {
                continue;   // skip
            }
            // END   2018/11/19 T.Ogura [QC#29332,ADD]

            currBillTo = thisRec.getBillToCustCd();

            // check if bill to breaks
            if (prevBillTo != null && !prevBillTo.equals(currBillTo)) {
                if (!completeOneBillTo(prevBillTo)) {
                    return false;  // abend
                }
//ADD Start QC12491
                prevBillTo = currBillTo;
//ADD End   QC12491
            }
            
            // when it's first record, set current bill to.
            if (prevBillTo == null) {
                prevBillTo = currBillTo;
            }
           
            // If any error has occurred previous transaction of same bill to, skip to next bill to.
            if (isErrOfTrx) {
                setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0771E), "", false);
                continue;   // skip
            }   

            // If the request type is "System", check if customer is prohibitted for write-off process.
            if (AR_WRT_OFF_RQST_TP.SYSTEM.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
                if (thisRec.getArAdjExclCustCd() != null) {
                    rollback();
                    // error
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0772E), "", false);
                    // go to next record
                    continue;
                }
            }

            //******* Write-Off Process *******//           
            // Other than Report Only
            if (!"1".equals(rs.getString(WRT_OFF_OPT_TP_CD))) {
                // create data for dummy receipt data
            
             // exclusion check
                if (!AR_WRT_OFF_RQST_TP.SYSTEM.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))){
                    String errMsg = checkTrxBalUpdated(thisRec);
                    if (errMsg != null) {
                        // error
                        rollback();
                        isErrOfTrx = true;
                        setWrtOffRqstRslt(rs, thisRec, errMsg, "", false);
                        // go to next record
                        continue;
                    }
                }
                
                // 1. Create AR_RCPT & AR_RCPT_DTL
                AR_RCPTTMsg arRcpt = new AR_RCPTTMsg();
                if (!createArRcpt(thisRec, arRcpt)) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0532E, new String[] {"AR Rcpt"}), "", false);
                    // go to next record
                    continue;
                }
                // 2. AR_RCPT_DTL
                if (!createArRcptDtl(arRcpt)) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0532E, new String[] {"AR Rcpt Detail"}), "", false);
                    // go to next record
                    continue;
                }
                
                AR_TRX_BALTMsg arTrxBal = new AR_TRX_BALTMsg();
                // 3. AR_TRX_BAL
                if (!createArTrxBal(thisRec, arRcpt, arTrxBal)) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0532E, new String[] {"AR Transaction Balance"}), "", false);
                    // go to next record
                    continue;
                }
                // 4. AR_APPLY_INTFC_WRK
                String aGrKey = rs.getString(WRT_OFF_RQST_USR_ID).concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

                if (!createArApplyIntfcWrk(rs, thisRec, aGrKey, arRcpt.rcptNum.getValue(), arTrxBal.arTrxBalPk.getValue())) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0532E, new String[] {"AR Apply Interface Work for dummy rcpt"}), "", false);
                    // go to next record
                    continue;
                }

                // call cash application API
                String rtnCd = callCashApplyAPI(aGrKey);

                if (rtnCd != null) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0002E, new String[] {rtnCd}), "", false);
                    // go to next record
                    continue; 
                }

                // create AR_APPLY_INTFC for cash application
                aGrKey = rs.getString(WRT_OFF_RQST_USR_ID).concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                if (!createArApplyIntfcWrk2(rs, thisRec, aGrKey, arRcpt.rcptNum.getValue(), arTrxBal.arTrxBalPk.getValue())) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0532E, new String[] {"AR Apply Interface Work"}), "", false);
                    // go to next record
                    continue;
                }
                
                // call cash application API
                rtnCd = callCashApplyAPI(aGrKey);

                if (rtnCd != null) {
                    // error
                    rollback();
                    isErrOfTrx = true;
                    setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0002E, new String[] {rtnCd}), "", false);
                    // go to next record
                    continue; 
                }

                // Get created AR_ADJ_NUM
                adjNum = getArAdjNum(aGrKey);

                // If WRT_OFF_RQST_TP_CD = '2' (Write-Off Screen), update AR_ADJ with user entered value.
                if (AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
                    if (!updateArAdj(rs, adjNum)) {
                        // error
                        rollback();
                        isErrOfTrx = true;
                        setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0533E, new String[] {"AR ADJ"}), adjNum, false);
                        // go to next record
                        continue;
                    }

                    // add start 2022/11/28 QC#57252
                    if (!createArAdjCoaInfo(rs, adjNum)) {
                        // error
                        rollback();
                        isErrOfTrx = true;
                        setWrtOffRqstRslt(rs, thisRec, S21MessageFunc.clspGetMessage(NFCM0532E, new String[] {"AR Adjustment COA Information" }), adjNum, false);
                        // go to next record
                        continue;
                    }
                    // add end 2022/11/28 QC#57252
                }

                // Update Reserved Amount
                if (thisRec.getDealApplyAdjRsvdAmt() != null && thisRec.getDealApplyAdjRsvdAmt().compareTo(BigDecimal.ZERO) != 0) {
                    // START 2018/08/30 E.Kameishi [QC#25188,MOD]
                    // update AR_TRX_BAL with new reserved amount
                    //if (!updateArTrxBal(thisRec, rs.getBigDecimal(WRT_OFF_APPLY_AMT))) {
                    if (!updateArTrxBal(thisRec, thisRec.getWrtOffGrsAmt())) {
                    // END 2018/08/30 E.Kameishi [QC#25188,MOD]
                        // error
                        rollback();
                        isErrOfTrx = true;
                        setWrtOffRqstRslt(rs, thisRec,S21MessageFunc.clspGetMessage(NFCM0533E, new String[] {"Write Off Reserved Amount"}), adjNum, false);
                        // go to next record
                        continue;
                    }
                }
                
                
                                
            } // end if
            
            //no error
            setWrtOffRqstRslt(rs, thisRec, "", adjNum, false);
            
        } // end loop
        
        if (listRslt.size() > 0) {
            // process last bill to
            return completeOneBillTo(currBillTo);
        } else {
            return true;  // no data to be processed
            
        }

    }

    // START 2016/06/30 J.Kim [QC#16248,MOD]
    private boolean checkNullRmngBalAmt(BigDecimal lowRmngBalAmt, BigDecimal highRmngBalAmt) {

        if (ZYPCommonFunc.hasValue(lowRmngBalAmt) && ZYPCommonFunc.hasValue(highRmngBalAmt)) {
            if (BigDecimal.ZERO.compareTo(lowRmngBalAmt) == 0 && BigDecimal.ZERO.compareTo(highRmngBalAmt) == 0) {
                return true;
            }
        }
        return false;
    }
    // END 2016/06/30 J.Kim [QC#16248,MOD]

    private boolean completeOneBillTo(String billToCustCd){
     // finalize one bill to
        if (isErrOfTrx) {
            // create AR_WRT_OFF_RQST_RSLT
            if (!createRqstRslt(listRslt, isErrOfTrx)) {
                return false;  // abend
            } else {
                commit();
            }
        } else {
            // call credit profile update API
            if (!callCrPrflUpdtAPI(billToCustCd)) {
                
            }
            
            // create AR_WRT_OFF_RQST_RSLT
            if (!createRqstRslt(listRslt, isErrOfTrx)) {
                return false;  // abend
            } else {
                commit();
            }
        }
        // initialize
        isErrOfTrx = false;
//DEL Start QC12491
//        prevBillTo = currBillTo;
//DEL End   QC12491
        listRslt.clear();
        
        return true;
    }
    
   // added 2016/02/18
    private boolean callCrPrflUpdtAPI (String billToCust) {
                    
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();
        
        ZYPEZDItemValueSetter.setValue(paramMsg.xxModeCd, NFZC202001.MODE_BILL_TO_CUST);
        ZYPEZDItemValueSetter.setValue(paramMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramMsg.billToCustCd, billToCust);
        ZYPEZDItemValueSetter.setValue(paramMsg.procDt, this.batProcDate);

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.BATCH);
        
        List<String> msgList = S21ApiUtil.getXxMsgIdList(paramMsg);
        for (String msgId : msgList) {
            if (msgId.endsWith("E")) {
                return false;
            }
        }
        
        return true;
    }
    // end 2016/02/18

    private boolean createRqstRslt(List<AR_WRT_OFF_RQST_RSLTTMsg> listRslt, boolean isErr) {
        int size = listRslt.size();
        AR_WRT_OFF_RQST_RSLTTMsg[] tmsgs = new AR_WRT_OFF_RQST_RSLTTMsg[size];
        AR_WRT_OFF_RQST_RSLTTMsg tmsg = new AR_WRT_OFF_RQST_RSLTTMsg();

        for (int i = 0; i < size; i++) {
            tmsg = listRslt.get(i);
            if (isErr) {
                // if the row is not error, update it to error.
                if (PROC_STS.COMPLEATED.equals(tmsg.procStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.ERROR);
                    ZYPEZDItemValueSetter.setValue(tmsg.wrtOffErrMsgTxt, S21MessageFunc.clspGetMessage(NFCM0771E));
                }
            }
            tmsgs[i] = tmsg;
        }
        
        // insert
        int retCnt = S21FastTBLAccessor.insert(tmsgs);
        if (retCnt != tmsgs.length) {
            return false;
        }
        
        return true;
    }

    private void setWrtOffRqstRslt(ResultSet rs, WrtOffTargetTrxBal thisRec, String errMsg, String adjNum, boolean isInnerCall) throws SQLException{
         AR_WRT_OFF_RQST_RSLTTMsg tmsg = new AR_WRT_OFF_RQST_RSLTTMsg();
         BigDecimal amt = null;             
         
        
         ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, thisRec.getGlblCmpyCd());
         
         BigDecimal seqNum = ZYPOracleSeqAccessor.getNumberBigDecimal("AR_WRT_OFF_RQST_RSLT_SQ");
         
         ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstRsltPk, seqNum);
         ZYPEZDItemValueSetter.setValue(tmsg.arWrtOffRqstPk, rs.getBigDecimal(AR_WRT_OFF_RQST_PK));
         ZYPEZDItemValueSetter.setValue(tmsg.arTrxBalPk, thisRec.getArTrxBalPk());
         ZYPEZDItemValueSetter.setValue(tmsg.wrtOffRqstUsrId, rs.getString(WRT_OFF_RQST_USR_ID));

         if (AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
             // START 2018/08/30 E.Kameishi [QC#25188,MOD]
             //amt = rs.getBigDecimal(WRT_OFF_APPLY_AMT);
             amt = thisRec.getWrtOffGrsAmt();
             // END 2018/08/30 E.Kameishi [QC#25188,MOD]
         } else {
             amt = thisRec.getDealRmngBalGrsAmt();
         }
         ZYPEZDItemValueSetter.setValue(tmsg.wrtOffApplyAmt, amt);

         if (isErrOfTrx) {
             ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.ERROR);
             ZYPEZDItemValueSetter.setValue(tmsg.wrtOffErrMsgTxt, errMsg);
             
             if (!isInnerCall) {
                 if (thisRec.getDealApplyAdjRsvdAmt() != null && thisRec.getDealApplyAdjRsvdAmt().compareTo(BigDecimal.ZERO) != 0) {
                     // START 2018/08/30 E.Kameishi [QC#25188,MOD]
                     // reset reserved amount in AR_TRX_BAL
                     //if (!updateArTrxBal(thisRec, rs.getBigDecimal(WRT_OFF_APPLY_AMT))) {
                     if (!updateArTrxBal(thisRec, thisRec.getWrtOffGrsAmt())) {
                     // END 2018/08/30 E.Kameishi [QC#25188,MOD]
                         rollback();
                         isErrOfTrx = true;
                         setWrtOffRqstRslt(rs, thisRec,S21MessageFunc.clspGetMessage(NFCM0533E, new String[] {"Write Off Reserved Amount"}), adjNum, true);
                     }
                 }
             }
             
         } else {
             ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.COMPLEATED);
             ZYPEZDItemValueSetter.setValue(tmsg.arAdjNum, adjNum);
         }            

         listRslt.add(tmsg); 
         
         //error flag per request
         if (!isErrPerRqst && isErrOfTrx) {
             isErrPerRqst = true;
             firstErrMsg = errMsg;
         }
   }

    private boolean createArRcpt(WrtOffTargetTrxBal thisRec, AR_RCPTTMsg tmsg) {
 
        String rcptNum = getRcptNum();
        if (rcptNum == null) {
            return false;
        }
            

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.rcptNum, rcptNum); 
        ZYPEZDItemValueSetter.setValue(tmsg.rcptBatNum, ZYPCodeDataUtil.getVarCharConstValue(AR_RCPT_CONST_KEY_RCPT_BAT_SQ_NUM, this.glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(tmsg.arRcptTrxTpCd, AR_RCPT_TRX_TP.OFFSET);
        ZYPEZDItemValueSetter.setValue(tmsg.arRcptTpCd, AR_RCPT_TP.OFFSET);
        ZYPEZDItemValueSetter.setValue(tmsg.dealCcyCd, thisRec.getDealCcyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dealRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjAmt, BigDecimal.ZERO);      
        ZYPEZDItemValueSetter.setValue(tmsg.dealRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealRcptRmngBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.exchRate, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tmsg.funcCcyCd, thisRec.getFuncCcyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.funcRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcRfAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcVoidAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcRvalVarAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcRcptRmngBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.rcptDt, this.acctDt);
        ZYPEZDItemValueSetter.setValue(tmsg.glDt, this.acctDt);
        ZYPEZDItemValueSetter.setValue(tmsg.payerCustCd, thisRec.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, tocCd);
        ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, coaProdCd);
        // ZYPEZDItemValueSetter.setValue(tmsg.crAnlstPsnCd, thisRec.getCrMgrPsnCd()); // DEL 2017/01/16 [QC16867]
        ZYPEZDItemValueSetter.setValue(tmsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
        ZYPEZDItemValueSetter.setValue(tmsg.voidFlg, FLG_OFF_N); 
        ZYPEZDItemValueSetter.setValue(tmsg.cratMethTpCd, "M");
        ZYPEZDItemValueSetter.setValue(tmsg.exptFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.exptFirstBankChrgCcyCd, thisRec.getFuncCcyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dealFirstExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcFirstExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.exptScdBankChrgCcyCd, thisRec.getFuncCcyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dealScdExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcScdExptChrgAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealNetRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.funcNetRcptAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.fgnExchLossGainAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.ajeCratCpltFlg, FLG_OFF_N); 
        // START 2018/09/20 T.Ogura [QC#28097,ADD]
        ZYPEZDItemValueSetter.setValue(tmsg.arRcptSrcCd, AR_RCPT_SRC.SYSTEM_CREATED);
        // END   2018/09/20 T.Ogura [QC#28097,ADD]

        EZDTBLAccessor.create(tmsg);
         
        // error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            return false;
        }                

        return true;
    }

    private boolean createArRcptDtl(AR_RCPTTMsg rcptTmsg) {
        AR_RCPT_DTLTMsg tmsgDtl = new AR_RCPT_DTLTMsg();
        
        ZYPEZDItemValueSetter.setValue(tmsgDtl.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsgDtl.rcptNum, rcptTmsg.rcptNum.getValue());
        ZYPEZDItemValueSetter.setValue(tmsgDtl.rcptDtlNum, "0001");
        ZYPEZDItemValueSetter.setValue(tmsgDtl.dealRcptDtlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsgDtl.funcRcptDtlAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsgDtl.autoCratFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsgDtl.grpInvFlg, FLG_OFF_N);
        
        EZDTBLAccessor.create(tmsgDtl);
        
        // error
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsgDtl.getReturnCode())) {
                return false;
            }
            return true;
        }
 
        private boolean createArTrxBal(WrtOffTargetTrxBal thisRec, AR_RCPTTMsg arRcpt, AR_TRX_BALTMsg tmsg) {
            BigDecimal arTrxBalPk = getArTrxBalPk();

            if (arTrxBalPk == null) {
                return false;
            }

            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);  
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxBalPk, arTrxBalPk);
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxNum, arRcpt.rcptNum.getValue());
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxTpCd, AR_TRX_TP.RECEIPT);
            ZYPEZDItemValueSetter.setValue(tmsg.dealCcyCd, thisRec.getDealCcyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.dealOrigGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealApplyGrsAmt, BigDecimal.ZERO); 
            ZYPEZDItemValueSetter.setValue(tmsg.dealApplyCashDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealApplyCrAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealRmngBalGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealNetSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealFrtAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealInvDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealPrmoDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.dealRcptVoidAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.exchRate, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tmsg.funcCcyCd, thisRec.getFuncCcyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.funcOrigGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcApplyGrsAmt, BigDecimal.ZERO); 
            ZYPEZDItemValueSetter.setValue(tmsg.funcApplyCashDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcApplyCrAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcApplyAdjAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcRvalVarAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcRmngBalGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcNetSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcFrtAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcTaxAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcInvDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcPrmoDiscAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.funcRcptVoidAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.cashDiscPct, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(tmsg.arCashApplyStsCd, AR_CASH_APPLY_STS.APPLIED);
            ZYPEZDItemValueSetter.setValue(tmsg.arTrxDt, this.acctDt);
            ZYPEZDItemValueSetter.setValue(tmsg.glDt, this.acctDt);
            ZYPEZDItemValueSetter.setValue(tmsg.billToCustCd, thisRec.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(tmsg.sellToCustCd, thisRec.getSellToCustCd());
            ZYPEZDItemValueSetter.setValue(tmsg.payerCustCd, thisRec.getPayerCustCd());
            ZYPEZDItemValueSetter.setValue(tmsg.tocCd, this.tocCd);
            ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, this.coaProdCd);
            ZYPEZDItemValueSetter.setValue(tmsg.exptFlg, FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tmsg.arAutoPurgeOfsFlg, FLG_OFF_N);
            
            EZDTBLAccessor.create(tmsg);
             
            // error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            return false;
        }  

        return true;
    }

    private boolean createArApplyIntfcWrk(ResultSet rs, WrtOffTargetTrxBal thisRec, String aGrKey, String rcptNum, BigDecimal arTrxBalPk) throws SQLException{
        AR_APPLY_INTFC_WRKTMsg tmsg = new AR_APPLY_INTFC_WRKTMsg();         

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.glblCmpyCd, this.glblCmpyCd);

        AR_RCPTTMsg outArRcptMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inArRcptMsg);
        if (outArRcptMsg == null) {
            return false;
        }

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxBalPk, arTrxBalPk);
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.glblCmpyCd, this.glblCmpyCd);
                
        AR_TRX_BALTMsg outArTrxBalMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);
        if (outArTrxBalMsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(tmsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tmsg.bizAppId, "NFCL0660");
        ZYPEZDItemValueSetter.setValue(tmsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.procTpCd, "1");
        ZYPEZDItemValueSetter.setValue(tmsg.dealSqNum, "00000001");
        ZYPEZDItemValueSetter.setValue(tmsg.dealSqDtlNum, ZYPCommonFunc.leftPad("1", 4, "0"));
        ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(tmsg.usrId, rs.getString(WRT_OFF_RQST_USR_ID));
        ZYPEZDItemValueSetter.setValue(tmsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(tmsg.rcptDtlNum, "0");
        ZYPEZDItemValueSetter.setValue(tmsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.rcptGlDt, this.acctDt);
        ZYPEZDItemValueSetter.setValue(tmsg.payerCustCd, thisRec.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptTrxBalPk, outArTrxBalMsg.arTrxBalPk.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptHdrLastUpdTs, outArRcptMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptHdrTz, outArRcptMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptTrxBalLastUpdTs, outArTrxBalMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptTrxBalTz, outArTrxBalMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.grpInvFlg, FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.invTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealCcyCd, thisRec.getDealCcyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealOnAcctCashAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
       
        EZDTBLAccessor.create(tmsg);
         
        // error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            return false;
        } 

        return true;
    }

    private boolean createArApplyIntfcWrk2(ResultSet rs, WrtOffTargetTrxBal thisRec, String aGrKey, String rcptNum, BigDecimal arTrxBalPk) throws SQLException{
        AR_APPLY_INTFC_WRKTMsg tmsg = new AR_APPLY_INTFC_WRKTMsg();         

        // AR_RCPT TimeStamp -Get
        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();
        
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.rcptNum, rcptNum);
        ZYPEZDItemValueSetter.setValue(inArRcptMsg.glblCmpyCd, this.glblCmpyCd);
                
        AR_RCPTTMsg outArRcptMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inArRcptMsg);
        if (outArRcptMsg == null) {
            return false;
        }

        // AR_TRX_BAL TimeStamp -Get
        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();
        
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.arTrxBalPk, arTrxBalPk); 
        ZYPEZDItemValueSetter.setValue(inArTrxBalMsg.glblCmpyCd, this.glblCmpyCd);
        
        AR_TRX_BALTMsg outArTrxBalMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);
        if (outArTrxBalMsg == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(tmsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(tmsg.bizAppId, "NFCL0660");
        ZYPEZDItemValueSetter.setValue(tmsg.upldCsvRqstPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.procTpCd, "1");
        ZYPEZDItemValueSetter.setValue(tmsg.dealSqNum, "00000001");
        ZYPEZDItemValueSetter.setValue(tmsg.dealSqDtlNum, ZYPCommonFunc.leftPad("1", 4, "0"));
        ZYPEZDItemValueSetter.setValue(tmsg.procStsCd, PROC_STS.IN_COMPLETED);
        ZYPEZDItemValueSetter.setValue(tmsg.usrId, rs.getString(WRT_OFF_RQST_USR_ID));
        ZYPEZDItemValueSetter.setValue(tmsg.rcptNum,rcptNum);
        ZYPEZDItemValueSetter.setValue(tmsg.rcptInProcSqPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.rcptGlDt, this.acctDt);
        ZYPEZDItemValueSetter.setValue(tmsg.payerCustCd, thisRec.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptTrxBalPk, outArTrxBalMsg.arTrxBalPk.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptHdrLastUpdTs, outArRcptMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptHdrTz, outArRcptMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptTrxBalLastUpdTs, outArTrxBalMsg.ezUpTime.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.rcptTrxBalTz, outArTrxBalMsg.ezUpTimeZone.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.grpInvFlg, FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(tmsg.invNum, thisRec.getArTrxNum());
        ZYPEZDItemValueSetter.setValue(tmsg.arTrxTpCd, thisRec.getArTrxTpCd());
        ZYPEZDItemValueSetter.setValue(tmsg.invTrxBalPk, thisRec.getArTrxBalPk());
        ZYPEZDItemValueSetter.setValue(tmsg.invTrxBalLastUpdTs, thisRec.getEzUpTime());
        ZYPEZDItemValueSetter.setValue(tmsg.invTrxBalTz, thisRec.getEzUpTimeZone());
                    
        ZYPEZDItemValueSetter.setValue(tmsg.crTrxBalPk, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealCcyCd, thisRec.getDealCcyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.cashAppGlDt, this.acctDt);
        ZYPEZDItemValueSetter.setValue(tmsg.cashDiscPct, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealCashDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(tmsg.dealOnAcctCashAmt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(tmsg.arAdjTrxTpCd, AR_ADJ_TRX_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(tmsg.arAdjTpCd, rs.getString(AR_ADJ_TP_CD));
        
        BigDecimal amt = null;
        if (AR_WRT_OFF_RQST_TP.WRITE_OFF_SCREEN.equals(rs.getString(AR_WRT_OFF_RQST_TP_CD))) {
            // START 2018/08/30 E.Kameishi [QC#25188,MOD]
            //amt = rs.getBigDecimal(WRT_OFF_APPLY_AMT);
            amt = thisRec.getWrtOffGrsAmt();
            // END 2018/08/30 E.Kameishi [QC#25188,MOD]
        } else {
            amt = thisRec.getDealRmngBalGrsAmt();
        }
         
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjAmt, amt);            
        ZYPEZDItemValueSetter.setValue(tmsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
       
        ZYPEZDItemValueSetter.setValue(tmsg.adjCmntTxt, rs.getString(AR_ADJ_RSN_NM));
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, thisRec.getTocCd());
        ZYPEZDItemValueSetter.setValue(tmsg.coaProdCd, thisRec.getCoaProdCd());

        EZDTBLAccessor.create(tmsg);
         
        // error
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            return false;
        }
        
        return true;

    }

    private String getRcptNum() {
         String rcptNum = null;
         try {
            rcptNum = ZYPNumbering.getUniqueID(BIZAPL_RCPTNUMKEY);
         } catch (IllegalArgumentException iae) {
            return null;
         }
         return rcptNum;
    
    }

    private void getAcctDt() {
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", this.glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.onlBatTpCd, "2");
            ZYPEZDItemValueSetter.setValue(inMsg.subSysCd, subSysCd);             

            AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);
 
            if (outMsg == null) {
                this.acctDt = null;
            } else {
                String lclAcctDt = outMsg.acctDt.getValue();
                if (lclAcctDt == null) {
                    this.acctDt = null;
                } else {

                    // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                    if (lclAcctDt.substring(0,5).equals(this.batProcDate.substring(0,5))) {
                    if (lclAcctDt.substring(0,6).equals(this.batProcDate.substring(0,6))) {
                    // END   2019/03/22 T.Ogura [QC#30565,MOD]
                        this.acctDt = this.batProcDate;
                    } else {
                        this.acctDt = lclAcctDt;
                    }
                }
            }

        }  
 
       /**
    * Get AR_TRX_BAL_PK
    * @return BigDecimal
    */
    private BigDecimal getArTrxBalPk() {    
        BigDecimal arTrxBalPk = null;

        NFCNumbering nfcNumbering = new NFCNumbering();
        NFXC3060Bean outGetNumber = nfcNumbering.getNumber(ZYPOracleSeqAccessor.AR_TRX_BAL_SQ, "", AR_TRX_BAL_SQ_START_NUM);
        if ("0".equals(outGetNumber.getRtrnNo())) {
                arTrxBalPk = outGetNumber.getOraSqNo();
            } else {
                return null;
            }
            return arTrxBalPk;
        }
         
    private String callCashApplyAPI(String aGrKey) {
        String errMsg = null;
 
        NFZC301001 api = new NFZC301001();
        NFZC301001PMsg apiMsg = new NFZC301001PMsg();
        
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.applyGrpKey, aGrKey);
        ZYPEZDItemValueSetter.setValue(apiMsg.dealSqNum, "00000001"); 
        ZYPEZDItemValueSetter.setValue(apiMsg.batDt, this.batProcDate); 
           
        api.execute(apiMsg, S21ApiInterface.ONBATCH_TYPE.BATCH);
        String result = apiMsg.getReturnCode();

        // result == "0"
        if (APPLY_RTNCD_UN_PROCCES.equals(result)) {
            errMsg  = "Unprocessed";

            // result == "2"
        } else if (APPLY_RTNCD_CASHAPP_ERROR.equals(result)) {
            errMsg = "Cash Application Error";

            // result == "3"
        } else if (APPLY_RTNCD_EXCLUSION_ERROR.equals(result)) {
            errMsg = "Exclusion Error";

            // result == "4"
        } else if (APPLY_RTNCD_OTHERS_ERROR.equals(result)) {
            errMsg = "Others Error";

        }

        return errMsg;
   }   

   private String checkTrxBalUpdated(WrtOffTargetTrxBal thisRec) {
       String origTs = thisRec.getInvTrxBalLastUpdTs();
       String origTz = thisRec.getInvTrxBalTz();
       String currTs = thisRec.getEzUpTime();
       String currTz = thisRec.getEzUpTimeZone();
       
       if (origTs == null) {
           return S21MessageFunc.clspGetMessage(NFCM0773E);
       }

       if (!ZYPDateUtil.isSameTimeStamp(origTs, origTz, currTs, currTz)) {
           return S21MessageFunc.clspGetMessage(NFCM0594E, new String[] {"This record"}); 
       }
       return null;
       
   }  

  private String getArAdjNum(String aGrpKey) {
        AR_APPLY_INTFC_WRKTMsg inMsg = new AR_APPLY_INTFC_WRKTMsg();
        
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpKey, aGrpKey);
        ZYPEZDItemValueSetter.setValue(inMsg.applyGrpSubPk, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                
        AR_APPLY_INTFC_WRKTMsg outMsg = (AR_APPLY_INTFC_WRKTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return "";
        }
        return outMsg.arAdjNum.getValue();

  }

  private boolean updateArAdj(ResultSet rs, String arAdjNum) throws SQLException{
      AR_ADJTMsg inMsg = new AR_ADJTMsg();
        
        
        ZYPEZDItemValueSetter.setValue(inMsg.arAdjNum, arAdjNum);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                
        AR_ADJTMsg outMsg = (AR_ADJTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(outMsg.arWrtOffNoteCd, rs.getString(AR_WRT_OFF_NOTE_CD));
        ZYPEZDItemValueSetter.setValue(outMsg.arWrtOffNoteTxt, rs.getString(AR_WRT_OFF_NOTE_TXT));
        
        EZDTBLAccessor.update(outMsg);
        
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            return false;
        }
        return true;
  }

      private boolean updateArTrxBal(WrtOffTargetTrxBal thisRec, BigDecimal applyAmt) {
            AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
            
            ZYPEZDItemValueSetter.setValue(inMsg.arTrxBalPk, thisRec.getArTrxBalPk());
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
                    
            AR_TRX_BALTMsg outMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inMsg);
            if (outMsg == null) {
                return false;
            }
            
            BigDecimal newRsvdAmt = null;
 
            if (applyAmt == null) {
                newRsvdAmt = thisRec.getDealApplyAdjRsvdAmt().subtract(thisRec.getDealRmngBalGrsAmt());
            } else {
                newRsvdAmt = thisRec.getDealApplyAdjRsvdAmt().subtract(applyAmt);
            }             
          
            ZYPEZDItemValueSetter.setValue(outMsg.dealApplyAdjRsvdAmt, newRsvdAmt);
            ZYPEZDItemValueSetter.setValue(outMsg.funcApplyAdjRsvdAmt, newRsvdAmt);
          
            EZDTBLAccessor.update(outMsg);
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                return false;
            }
            return true;
      }
      
      /**
       *<pre>
       * Send Mail
       * @param rs ResultSet
       *</pre>
       */
      public void sendMail(ResultSet rs) throws SQLException{

       // 1. Get From Address
          S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
          groupFrom.setMailKey1("NFD");  // Collection
          List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

          S21MailAddress from = null;
          if (!addrFromList.isEmpty()) {
              from = addrFromList.get(0);
          }

         // 2. Get To Address
          //---- start 2016/09/01 change to list since default collector may have more than one.
          /*
         String toAddr = rs.getString(EML_ADDR);
         if (toAddr == null || "".equals(toAddr)) {
             toAddr = defEmlAddr;
         }
         
         S21MailAddress addrTo = new S21MailAddress(this.glblCmpyCd, toAddr);
         */
          
          List<S21MailAddress> addrToList = getAddrTo(rs.getString(EML_ADDR));
         //---- end 2016/09/01
          
          // 4. Create Subject and Body
          S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TMPL_ID);
          if (template == null) {
              throw new S21AbendException("NFBM0184E", new String[] {MAIL_TMPL_ID });
          }

          template.setTemplateParameter(MAIL_TMPL_KEY_APPLY_GRP_KEY, rs.getString(WRT_OFF_RQST_GRP_CD));
          template.setTemplateParameter(MAIL_TMPL_KEY_RQST_DT, rs.getString(EZINTIME));
          
          // 5. Call Mail API
          S21Mail mail = new S21Mail(glblCmpyCd);
          mail.setFromAddress(from);
          //mail.setToAddress(addrTo);
          mail.setToAddressList(addrToList);
          mail.setMailTemplate(template);
          // START 2019/02/28 S.Ohki [QC#30584, MOD]
//          String rtn = mail.sendMail();
          String rtn = mail.postMail();
          // END 2019/02/28 S.Ohki [QC#30584, MOD]

          if (!ZYPCommonFunc.hasValue(rtn)) {
              throw new S21AbendException("NFDM0003E", new String[] {S21MessageFunc.clspGetMessage(NFCM0774E)});
          }

      }
      
      private List<S21MailAddress> getAddrTo(String toAddr) {
          List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
          
          if (hasValue(toAddr)) {
              toAddrList.add(new S21MailAddress(this.glblCmpyCd, toAddr));
          } else {
              for (String add : defEmlAddList) {
                  String toStr = add;
                  toAddrList.add(new S21MailAddress(this.glblCmpyCd, toStr));
              }
          }
          
          return toAddrList;
      }
    
      // add start 2022/11/28 QC#57252
      private boolean createArAdjCoaInfo(ResultSet rs, String arAdjNum) throws SQLException {
          if (!AR_ADJ_TP_OTHER.equals(rs.getString(AR_ADJ_TP_NM))) {
              return true;
          }

          AR_ADJ_COA_INFOTMsg tmsg = new AR_ADJ_COA_INFOTMsg();
          ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
          ZYPEZDItemValueSetter.setValue(tmsg.arAdjNum, arAdjNum);
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaCmpyCd, rs.getString(ADJ_COA_CMPY_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaBrCd, rs.getString(ADJ_COA_BR_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaCcCd, rs.getString(ADJ_COA_CC_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaAcctCd, rs.getString(ADJ_COA_ACCT_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaProdCd, rs.getString(ADJ_COA_PROD_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaChCd, rs.getString(ADJ_COA_CH_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaAfflCd, rs.getString(ADJ_COA_AFFL_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaProjCd, rs.getString(ADJ_COA_PROJ_CD));
          ZYPEZDItemValueSetter.setValue(tmsg.adjCoaExtnCd, rs.getString(ADJ_COA_EXTN_CD));
          EZDTBLAccessor.create(tmsg);

          // error
          if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
              return false;
          }
          return true;
      }
      // add end 2022/11/28 QC#57252
}
