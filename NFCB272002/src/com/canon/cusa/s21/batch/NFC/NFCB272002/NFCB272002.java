/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * AR RECEIPT_ADJUSTMENT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2009   Canon           M.Mimura        Create          N/A
 * 11/12/2009   Canon           Y.Koiwai        Update          DefID-1504
 * 03/04/2010   Canon           I.Kondo         Update          Merge
 * 03/25/2010   Canon           I.Kondo         Update          DefID-5173
 * 04/19/2010   Canon           H.Tokunaga      Update          DefID-5526
 * 04/20/2010   Canon           H.Tokunaga      Update          DefID-5829
 * 04/23/2010   Canon           H.Tokunaga      Update          DefID-5949
 * 08/26/2013   Canon           K.Kimura        Update          WDS#1931 Remove Trx Rsn Cd Logic for adjustment(120)
 *</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB272002;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AJE_AR_INTFCTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_ADJTMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;

/**
 * <pre>
 * AJE Link File Creation - Other than Rcpt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 10/09/2015   CSAI            K.Uramori       Update          For CSA
 * 04/15/2016   CSAI            K.Uramori       Update          QC#7146
 * 2019/10/07   Hitachi         H.Umeda         Update          QC#53990
 * 2022/11/28   Hitachi         A.Kohinata      Update          QC#57252
 *</pre>
 */

/**
 * AR RECEIPT_ADJUSTMENT
 */
public class NFCB272002 extends S21BatchMain {
    /** Program Id */
    private static final String[] PROGRAM_ID = {"NFCB272002" };

    /** Message ID */
    private static final String NFCM0584I = "NFCM0584I";

    /** Message ID */
    private static final String NFCM0593I = "NFCM0593I";

    /** Message ID */
    private static final String NFCM0586I = "NFCM0586I";

    /** Message ID */
    private static final String NFCM0587I = "NFCM0587I";

    /** Message Id */
    private static final String NFCM0588I = "NFCM0588I";

    /** Message ID */
    private static final String NFCM0501E = "NFCM0501E";

    /** Message ID */
    private static final String NFCM0533E = "NFCM0533E";

    /** Message ID */
    private static final String NFCM0594E = "NFCM0594E";

    /** Message ID */
    private static final String NFCM0638E = "NFCM0638E";

    /** Message ID */
    private static final String NFCM0532E = "NFCM0532E";

    /** Message ID */
    private static final String NFCM0632E = "NFCM0632E";

    /** Message String */
    private static final String[] MSG_STR_GLBL_CMPY_CD = {"GLBL_CMPY_CD" };

    /** Message String */
    private static final String[] MSG_STR_AJE_INTFC_SQ = {"AJE_INTFC_SQ" };

    /** Message String */
    private static final String[] MSG_STR_AJE_AR_INTFC = {"AJE_AR_INTFC" };

    /** Message String */
    private static final String[] MSG_STR_AR_CASH_APP = {"AR_CASH_APP" };
    
    /** Message String */
    private static final String[] MSG_STR_AR_ADJ = {"AR_ADJ" };

    /** Table Name */
    private static final String TNM_AR_CASH_APP = "AR_CASH_APP";

    /** Table Name */
    private static final String TNM_AR_ADJ = "AR_ADJ";
    
    /** NUMBER 3 */
    public static final int CST_NUMBER_3 = 3;

    /** Delete Err Count */
    private int delErrCnt = 0;

    /** Processing Count */
    private int procCnt = 0;

    /** Add ADE_AR_INTEFC Count */
    private int addIntfcCnt = 0;

    /** Upd AR_CASH_APP Count */
    private int updCashCnt = 0;

    /** Upd AR_ADJ Count */
    private int updAdjCnt = 0;

    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** GlobalCompanyCode */
    private String glblCmpyCd = null;

    /** Operation Date */
    private String batProcDate = null;

    /** AR_SUB_SYS_ID */
    private String arSubSysId = null;
    
    /** DEF_AR_RCPT_SRC_CD */
    private String defArRcptSrcCd = null;
    
    /** AR Adjustment Type of Credit Memo Refund */
    private String adjTpCMRfnd = null;
    
    /** TRX_RSN_CD */
    private static final String TRX_RSN_CD_01 = "01";
    
    /** TRX_RSN_CD */
    private static final String TRX_RSN_CD_02 = "02";
    
    /** TRX_RSN_CD */
    private static final String TRX_RSN_CD_03 = "03";
    
    /** TRX_CD */
    private static final String TRX_CD_RCPT_ADJ = "120";
    
    /** TRX_CD */
    private static final String TRX_CD_INV_ADJ = "130";
    
    /** TRX_CD */
    private static final String TRX_CD_ON_ACCT = "150";
    
    /** TRX_CD */
    private static final String TRX_CD_CASH_APP = "160";
    
    /** DB field */
    private static final String DS_INV_TP_CD = "DS_INV_TP_CD";
    
    /** DB field */
    private static final String AJE_CRAT_REQ_FLG = "AJE_CRAT_REQ_FLG";
    
    // add start 2022/11/28 QC#57252
    /** DB field */
    private static final String ADJ_COA_CMPY_CD = "ADJ_COA_CMPY_CD";

    /** DB field */
    private static final String ADJ_COA_BR_CD = "ADJ_COA_BR_CD";

    /** DB field */
    private static final String ADJ_COA_CC_CD = "ADJ_COA_CC_CD";

    /** DB field */
    private static final String ADJ_COA_ACCT_CD = "ADJ_COA_ACCT_CD";

    /** DB field */
    private static final String ADJ_COA_PROD_CD = "ADJ_COA_PROD_CD";

    /** DB field */
    private static final String ADJ_COA_CH_CD = "ADJ_COA_CH_CD";

    /** DB field */
    private static final String ADJ_COA_AFFL_CD = "ADJ_COA_AFFL_CD";

    /** DB field */
    private static final String ADJ_COA_PROJ_CD = "ADJ_COA_PROJ_CD";

    /** DB field */
    private static final String ADJ_COA_EXTN_CD = "ADJ_COA_EXTN_CD";
    // add end 2022/11/28 QC#57252

    /** DB field */
    private static final String DS_ACCT_NUM = "DS_ACCT_NUM";
    
    /** Var Char Const */
    private static final String AR_DEF_RCPT_SRC_CD = "AR_DEF_RCPT_SRC_CD";
    
    /** Var Char Const */
    private static final String AR_ADJ_TP_CM_RFND = "AR_ADJ_TP_CM_RFND";
    
    /**
     * Initialization method
     */
    @Override
    protected void initRoutine() {
        debugLog("initRoutine start");

        /* Output Start Message Job Log */
        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        /* Get GlobalCompanyCode */
        getGlblCmpyCd();

        //---- start added 2016/01/19
        defArRcptSrcCd = ZYPCodeDataUtil.getVarCharConstValue(AR_DEF_RCPT_SRC_CD, this.glblCmpyCd);
        adjTpCMRfnd = ZYPCodeDataUtil.getVarCharConstValue(AR_ADJ_TP_CM_RFND, this.glblCmpyCd);
        //---- end 2016/01/19
        
        debugLog("initRoutine end");
    }

    /**
     * Main method
     */
    @Override
    protected void mainRoutine() {
        debugLog("mainRoutine start");

        execute();

        debugLog("mainRoutine end");
    }

    /**
     * Termination method
     */
    @Override
    protected void termRoutine() {
        debugLog("termRoutine start");

        /* Output Termination Message Job Log */
        printJobLog(NFCM0586I, this.procCnt);
        printJobLog(NFCM0587I, this.addIntfcCnt);
        printJobLog(NFCM0588I, this.updCashCnt);
        printJobLog(NFCM0588I, this.updAdjCnt);

        /* Set Normal End */
        setTermState(TERM_CD.NORMAL_END, this.addIntfcCnt, this.delErrCnt, this.procCnt);

        S21InfoLogOutput.println(NFCM0593I, PROGRAM_ID);

        debugLog("termRoutine end");
    }

    /**
     * Main method that is called from batch
     * @param args parameter
     */
    public static void main(String[] args) {
        /* Initialization S21BatchMain */
        new NFCB272002().executeBatch(NFCB272002.class.getSimpleName());
    }

    /**
     * Get Global Company Code
     */
    private void getGlblCmpyCd() {
        debugLog("getGlblCmpyCd start");

        /* Get Global Company Code */
        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            execAbendException(NFCM0501E, MSG_STR_GLBL_CMPY_CD);
        }

        debugLog("glblCmpyCd=<" + this.glblCmpyCd + ">");
        debugLog("getGlblCmpyCd end");
    }

    /**
     * Batch Main Function
     */
    private void execute() {
        debugLog("execute start");

        /* Get Operation Date */
        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        /* Get AR_SUB_SYS_ID */
        getSubSysCd();

        /* SSM initialization */
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        try {

            /* AJE_AR_INTFC registration */
            regAjeArIntfc();
            
            /* commit */
            if (this.addIntfcCnt > 0) {
                commit();
            }
            
        } catch (SQLException e) {
            sqlExceptionHandler(e);
            rollback();
        }

        debugLog("execute end");
    }

    /**
     * Get Subsystem ID
     */
    private void getSubSysCd() {
        debugLog("getSubSysCd start");

        this.arSubSysId = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.arSubSysId)) {
            execAbendException(NFCM0632E);
        }

        debugLog("getSubSysCd end");
    }

    /**
     * AJE_AR_INTFC registration
     * @throws SQLException
     */
    private void regAjeArIntfc() throws SQLException {
        debugLog("regAjeArIntfc start");

        PreparedStatement stmtSelect = null;
        ResultSet arCashAppRs = null;

        try {
            /* Get AR_CASH_APP */
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            queryParam.put("trxRsnOnAcct", TRX_RSN_CD_01);
            queryParam.put("trxOnAcct", TRX_CD_ON_ACCT);
            queryParam.put("rcptSrcDef", defArRcptSrcCd);
            queryParam.put("flagY", NFCConst.CST_FLAG_ON);
            queryParam.put("flagN", NFCConst.CST_FLAG_OFF);
            queryParam.put("trxTpAcc", NFCConst.CST_AR_TRX_TP_CD_ACCOUNT);
            queryParam.put("trxRsnApp02", TRX_RSN_CD_02);
            queryParam.put("trxTpDed", NFCConst.CST_AR_TRX_TP_CD_DEDUCTION);
            queryParam.put("trxRsnApp03", TRX_RSN_CD_03);
            queryParam.put("trxRsnApp01", TRX_RSN_CD_01);
            queryParam.put("trxCashApp", TRX_CD_CASH_APP);
            queryParam.put("adjTpCMRef", adjTpCMRfnd);
            queryParam.put("arApplyTpCash", NFCConst.CST_AR_APPLY_TP_CD_CASH);
            queryParam.put("trxRsnLateFee", TRX_RSN_CD_02);
            //---- start QC#7146
            queryParam.put("trxLateFee", TRX.AR_DEDUCTION);
            //---- end QC#7146
// START 2019/10/07 H.Umeda [QC#53990,ADD]
            queryParam.put("batch", NFCConst.CST_ONL_BAT_TP_CD_BAT);
// AND   2019/10/07 H.Umeda [QC#53990,ADD]
            
            stmtSelect = this.ssmLLClient.createPreparedStatement("getArCashApp", queryParam, execParam);
            arCashAppRs = stmtSelect.executeQuery();

            /* Create AJE_AR_INTFC Registration Infomation Object */
            AJE_AR_INTFCTMsg ajeArIntfcTMsg = new AJE_AR_INTFCTMsg();
            AR_CASH_APPTMsg updArCashAppTMsg = new AR_CASH_APPTMsg();
            AR_ADJTMsg updArAdjTMsg = new AR_ADJTMsg();
            
            /* Loop until record count */
            while (arCashAppRs.next()) {
                
                /*
                 * Initialize AJE_AR_INTFC Registration Infomation
                 * Object
                 */
                ajeArIntfcTMsg.clear();
                
                /* Increment Proccess Count */
                this.procCnt++;
                
                setAjeIntfcPkToAjeArIntfc(ajeArIntfcTMsg);
                
                // set value by trx rsn
                setValueByTrxRsn(arCashAppRs, ajeArIntfcTMsg);
                
                /* Insert */
                insAjeArIntfc(ajeArIntfcTMsg);
                this.addIntfcCnt++;
                
                if (BigDecimal.ZERO.compareTo(arCashAppRs.getBigDecimal(NFCDbConst.AR_CASH_APP_PK)) == 0) {
                	 /* update AR_ADJ */
	                updArAdj(arCashAppRs, updArAdjTMsg);
                } else {
	                /* update AR_CAHS_APP */
	                updArCashApp(arCashAppRs, updArCashAppTMsg);
                }

            }
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, arCashAppRs);
            debugLog("regAjeArIntfc : closeResource");
        }

        debugLog("regAjeArIntfc end");
    }
    
    private void setValueByTrxRsn(ResultSet arCashAppRs, AJE_AR_INTFCTMsg ajeArIntfcTMsg) throws SQLException{
    	
        String trxCd = arCashAppRs.getString(NFCDbConst.TRX_CD);
        String trxRsnCd = arCashAppRs.getString(NFCDbConst.TRX_RSN_CD);
        
        ajeArIntfcTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
// START 2019/10/07 H.Umeda [QC#53990,MOD]
//      ajeArIntfcTMsg.glDt.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.GL_DT)));
        ajeArIntfcTMsg.glDt.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.ACCT_DT)));
// END   2019/10/07 H.Umeda [QC#53990,MOD]
        ajeArIntfcTMsg.sysSrcCd.setValue(this.arSubSysId);
        ajeArIntfcTMsg.trxCd.setValue(NFCCmnMethod.convertDbString(trxCd));
        ajeArIntfcTMsg.trxRsnCd.setValue(NFCCmnMethod.convertDbString(trxRsnCd));
        
        ajeArIntfcTMsg.arRcptTrxTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_RCPT_TRX_TP_CD)));
        ajeArIntfcTMsg.billToCustCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.BILL_TO_CUST_CD)));
        
        //---- start add 2016/01/19
        ajeArIntfcTMsg.dsAcctNum.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(DS_ACCT_NUM)));
        //---- end 2016/01/19
        
        ajeArIntfcTMsg.arCashAppPk.setValue(arCashAppRs.getBigDecimal(NFCDbConst.AR_CASH_APP_PK));
        ajeArIntfcTMsg.arCashAppSqNum.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_CASH_APP_SQ_NUM)));
        ajeArIntfcTMsg.arApplyTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_APPLY_TP_CD)));
        ajeArIntfcTMsg.rcptNum.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.RCPT_NUM)));
        ajeArIntfcTMsg.arRcptTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_RCPT_TP_CD)));
        ajeArIntfcTMsg.arBankAcctCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_BANK_ACCT_CD)));
        ajeArIntfcTMsg.arAdjNum.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_ADJ_NUM)));
        ajeArIntfcTMsg.arAdjTrxTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_ADJ_TRX_TP_CD)));
        ajeArIntfcTMsg.arAdjTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_ADJ_TP_CD)));
        ajeArIntfcTMsg.arTrxNum.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_TRX_NUM)));
        ajeArIntfcTMsg.arTrxBalPk.setValue(arCashAppRs.getBigDecimal(NFCDbConst.AR_TRX_BAL_PK));
        ajeArIntfcTMsg.arTrxTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_TRX_TP_CD)));
        ajeArIntfcTMsg.crArTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        ajeArIntfcTMsg.crArTrxNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        ajeArIntfcTMsg.crArTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        ajeArIntfcTMsg.coaProdCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.COA_PROD_CD)));
        ajeArIntfcTMsg.dealCcyCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.DEAL_CCY_CD)));
        ajeArIntfcTMsg.funcCcyCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.FUNC_CCY_CD)));
        ajeArIntfcTMsg.exchRate.setValue(arCashAppRs.getBigDecimal(NFCDbConst.EXCH_RATE));
        
        // 120
        if (TRX_CD_RCPT_ADJ.equals(trxCd)) {
        	ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_APPLY_AMT);
            ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_APPLY_AMT);
            ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
            ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
            ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_CASH_APP);
        } else if (TRX_CD_INV_ADJ.equals(trxCd)) { // 130
        	if (TRX_RSN_CD_01.equals(trxRsnCd)) {  // 01
        		ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_APPLY_AMT);
                ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_APPLY_AMT);
                ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_CASH_APP);
        	}
            //---- start 2016/04/15 QC#7146 Late Fee AJE ID Change
        	/*else {  // 02
        		ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_AR_ADJ_AMT);
                ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_AR_ADJ_AMT);
                ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_AR_ADJ_AMT));
                ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_AR_ADJ_AMT));
                ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_ADJ);
        	}
        	*/
        	//---- end 2016/04/15
        } else if (TRX_CD_ON_ACCT.equals(trxCd)) { // 150
            if (TRX_RSN_CD_01.equals(trxRsnCd)) {  // 01
            	ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_APPLY_AMT);
                ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_APPLY_AMT);
                ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_CASH_APP);
            //---- start 2016/04/15 QC#7146
            } else {  // 02 Late Fee
                ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_AR_ADJ_AMT);
                ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_AR_ADJ_AMT);
                ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_AR_ADJ_AMT));
                ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_AR_ADJ_AMT));
                ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_ADJ);
            }
            //---- end 2016/04/15
        } else if (TRX_CD_CASH_APP.equals(trxCd)) { // 160
        	if (TRX_RSN_CD_01.equals(trxRsnCd) || TRX_RSN_CD_03.equals(trxRsnCd)) {  // 01 or 03
        		ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_APPLY_AMT);
                ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_APPLY_AMT);
                ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT));
                ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT));
                ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_CASH_APP);
        	} else { // 02
        		ajeArIntfcTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_APPLY_AMT);
                ajeArIntfcTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_APPLY_AMT);
                ajeArIntfcTMsg.dealAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.DEAL_APPLY_AMT).negate());
                ajeArIntfcTMsg.funcAcctAmt.setValue(arCashAppRs.getBigDecimal(NFCDbConst.FUNC_APPLY_AMT).negate());
                ajeArIntfcTMsg.amtInfoEntyNm.setValue(TNM_AR_CASH_APP);
        	}
        }
        
        ajeArIntfcTMsg.cashAppDt.setValue(this.batProcDate);
        ajeArIntfcTMsg.tocCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.TOC_CD)));
        
        ajeArIntfcTMsg.drArRcptSrcCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_RCPT_SRC_CD)));
        ajeArIntfcTMsg.crArRcptSrcCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(NFCDbConst.AR_RCPT_SRC_CD)));
        
        // 160-02
        if (TRX_CD_CASH_APP.equals(trxCd) && TRX_RSN_CD_02.equals(trxRsnCd)) {
        	ajeArIntfcTMsg.drArRcptSrcCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString("ORIG_"+ NFCDbConst.AR_RCPT_SRC_CD)));
        }
        
        if (TRX_CD_RCPT_ADJ.equals(trxCd) && arCashAppRs.getString("CRM_" + DS_INV_TP_CD) != null) {
        	ajeArIntfcTMsg.dsInvTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString("CRM_" + DS_INV_TP_CD)));
        } else {
        	ajeArIntfcTMsg.dsInvTpCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(DS_INV_TP_CD)));
        }
        
        ajeArIntfcTMsg.ajeCratReqFlg.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(AJE_CRAT_REQ_FLG)));
        
        // add start 2022/11/28 QC#57252
        ajeArIntfcTMsg.adjCoaCmpyCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_CMPY_CD)));
        ajeArIntfcTMsg.adjCoaBrCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_BR_CD)));
        ajeArIntfcTMsg.adjCoaCcCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_CC_CD)));
        ajeArIntfcTMsg.adjCoaAcctCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_ACCT_CD)));
        ajeArIntfcTMsg.adjCoaProdCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_PROD_CD)));
        ajeArIntfcTMsg.adjCoaChCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_CH_CD)));
        ajeArIntfcTMsg.adjCoaAfflCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_AFFL_CD)));
        ajeArIntfcTMsg.adjCoaProjCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_PROJ_CD)));
        ajeArIntfcTMsg.adjCoaExtnCd.setValue(NFCCmnMethod.convertDbString(arCashAppRs.getString(ADJ_COA_EXTN_CD)));
        // add end 2022/11/28 QC#57252
    }
        
    /**
     * Set AJE_INTFC_PK to AJE_AR_INTFC Registration Infomation Object
     * @param AJE_AR_INTFCTMsg ajeArIntfcTMsg
     */
    private void setAjeIntfcPkToAjeArIntfc(AJE_AR_INTFCTMsg ajeArIntfcTMsg) {
        debugLog("setAjeIntfcPkToAjeArIntfc start");

        NFCNumbering numbering = new NFCNumbering();
        NFXC3060Bean numberBean = numbering.getNumber(NFCConst.CST_SEQ_ID_AJE_INTFC, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {
            execAbendException(NFCM0638E, MSG_STR_AJE_INTFC_SQ);
        }
        ajeArIntfcTMsg.ajeIntfcPk.setValue(numberBean.getOraSqNo());

        debugLog("setAjeIntfcPkToAjeArIntfc end");
    }

    /**
     * Insert AJE_AR_INTFC
     * @param AJE_AR_INTFCTMsg ajeArIntfcTMsg
     */
    private void insAjeArIntfc(AJE_AR_INTFCTMsg ajeArIntfcTMsg) {
        debugLog("insAjeArIntfc start");

        EZDTBLAccessor.insert(ajeArIntfcTMsg);
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(ajeArIntfcTMsg.getReturnCode())) {
            execAbendException(NFCM0532E, MSG_STR_AJE_AR_INTFC);
        }

        debugLog("insAjeArIntfc end");
    }

    /**
     * update updArCashApp
     * @param ResultSet rs,AR_CASH_APPTMsg updArCashAppTMsg
     */
    private void updArCashApp(ResultSet rs, AR_CASH_APPTMsg updArCashAppTMsg) throws SQLException {
        debugLog("updArCashApp start");

        updArCashAppTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        updArCashAppTMsg.arCashAppPk.setValue(rs.getBigDecimal(NFCDbConst.AR_CASH_APP_PK));
        updArCashAppTMsg.arCashAppSqNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_CASH_APP_SQ_NUM)));

        AR_CASH_APPTMsg target = (AR_CASH_APPTMsg) EZDTBLAccessor.findByKeyForUpdate(updArCashAppTMsg);

        if (target == null) {
            execAbendException(NFCM0533E, MSG_STR_AR_CASH_APP);
        }

        if ((rs.getString(NFCDbConst.EZUPTIME).equals(target.ezUpTime.getValue())) && (rs.getString(NFCDbConst.EZUPTIMEZONE).equals(target.ezUpTimeZone.getValue()))) {
            target.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_ON);
            
            EZDTBLAccessor.update(target);

            if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(target.getReturnCode())) {
                execAbendException(NFCM0533E, MSG_STR_AR_CASH_APP);
            } else {
                this.updCashCnt++;
            }
        } else {
            execAbendException(NFCM0594E, MSG_STR_AR_CASH_APP);
        }
        debugLog("updArCashApp end");
    }

    /**
     * update updArCashApp
     * @param ResultSet rs,AR_CASH_APPTMsg updArCashAppTMsg
     */
    private void updArAdj(ResultSet rs, AR_ADJTMsg updArAdjTMsg) throws SQLException {
        debugLog("updArAdj start");

        updArAdjTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        updArAdjTMsg.arAdjNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_ADJ_NUM)));

        AR_ADJTMsg target = (AR_ADJTMsg) EZDTBLAccessor.findByKeyForUpdate(updArAdjTMsg);

        if (target == null) {
            execAbendException(NFCM0533E, MSG_STR_AR_ADJ);
        }

        if ((rs.getString(NFCDbConst.EZUPTIME).equals(target.ezUpTime.getValue())) && (rs.getString(NFCDbConst.EZUPTIMEZONE).equals(target.ezUpTimeZone.getValue()))) {
            target.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_ON);
            
            EZDTBLAccessor.update(target);

            if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(target.getReturnCode())) {
                execAbendException(NFCM0533E, MSG_STR_AR_ADJ);
            } else {
                this.updAdjCnt++;
            }
        } else {
            execAbendException(NFCM0594E, MSG_STR_AR_ADJ);
        }
        debugLog("updArAdj end");
    }
    

    /**
     * Execution S21AbendException
     * @param msgId String
     * @param msgStr String[]
     */
    private void execAbendException(String msgId, String[] msgStr) {
        setTermState(TERM_CD.ABNORMAL_END, this.addIntfcCnt, this.delErrCnt, this.procCnt);
        throw new S21AbendException(msgId, msgStr);
    }

    /**
     * Execution S21AbendException
     * @param msgId String
     */
    private void execAbendException(String msgId) {
        setTermState(TERM_CD.ABNORMAL_END, this.addIntfcCnt, this.delErrCnt, this.procCnt);
        throw new S21AbendException(msgId);
    }

    /**
     * Print Job Log
     * @param String msgId
     * @param int val
     */
    private void printJobLog(String msgId, int val) {
        String valStr = Integer.toString(val);
        String[] msgStr = {valStr };
        S21InfoLogOutput.println(msgId, msgStr);
    }

    /**
     * Output Debug Log
     * @param logmsg String
     */
    private void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
