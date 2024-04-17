/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * AJE AR RECEIPT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2009   Canon           U.Usui          Create          N/A
 * 11/11/2009   Canon           U.Usui          Update          DefID-1504
 * 03/04/2010   Canon           I.Kondo         Update          Merge
 * 10/05/2011   Canon           T.Tanaka        Update          ITG 363113
 * 07/29/2013   Fujitsu         T.Tanaka        Update          Def#1456 Modify AJE Interface Logic
 *</pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB272001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AJE_AR_INTFCTMsg;
import business.db.AR_RCPT_UN_APPLYTMsg;
import business.db.AR_RCPTTMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AJE Link File Creation - AR Receipt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 10/08/2015   CSAI            K.Uramori       Update          For CSA
 * 02/23/2016   Fujitsu         T.Tanaka        Update          Def#2631
 * 04/13/2016   CSAI            K.Uramori       Update          QC#7002
 * 2019/10/07   Hitachi         H.Umeda         Update          QC#53990
 *</pre>
 */

/**
 * AJE AR RECEIPT
 */
public class NFCB272001 extends S21BatchMain {
    /** SQL Access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Message Id */
    private static final String NFCM0584I = "NFCM0584I";

    /** Message Id */
    private static final String NFCM0593I = "NFCM0593I";

    /** Message Id */
    private static final String NFCM0501E = "NFCM0501E";

    /** Message Id */
    private static final String NFCM0586I = "NFCM0586I";

    /** */
    private static final String NFCM0533E = "NFCM0533E";

    /** */
    private static final String NFCM0594E = "NFCM0594E";

    /** Message Id */
    private static final String NFCM0587I = "NFCM0587I";

    /** Message Id */
    private static final String NFCM0588I = "NFCM0588I";

    /** Message Id */
    private static final String NFCM0632E = "NFCM0632E";

    /** Message Id */
    private static final String NFCM0638E = "NFCM0638E";

    /** Message Id */
    private static final String NFCM0532E = "NFCM0532E";

    /** Program Id */
    private static final String[] PROGRAM_ID = {"NFCB272001" };

    /** Param Name */
    private static final String[] PARAM_NAME_GLBL_CMPY_CD = {NFCDbConst.GLBL_CMPY_CD };

    /** ITEM Name */
    private static final String[] ITEM_NAME_AJE_INTFC_SQ = {"AJE_INTFC_SQ" };

    /** Tbl Name */
    private static final String[] ITEM_NAME_AJE_AR_INTFC = {"AJE_AR_INTFC" };

    /** Tbl Name */
    private static final String[] ITEM_NAME_AR_RCPT_UN_APPLY = {"AR_RCPT_UN_APPLY" };

    /** Tbl Name */
    private static final String[] ITEM_NAME_AR_RCPT = {"AR_RCPT" };

    /** AR_UN_APPLY_STS_CD : NEW */
    public static final String CST_AR_UN_APPLY_STS_CD_NAME_NEW = "new";

    /** AR_UN_APPLY_STS_CD : CASH */
    public static final String CST_AR_UN_APPLY_STS_CD_NAME_CASH = "cashApp";

    /** AR_UN_APPLY_STS_CD : CASH_CANC */
    public static final String CST_AR_UN_APPLY_STS_CD_NAME_CASH_CANC = "cashAppCancel";

    /** AR_UN_APPLY_STS_CD : VOID */
    public static final String CST_AR_UN_APPLY_STS_CD_NAME_VOID = "void";

    /** NUMBER 3 */
    public static final int CST_NUMBER_3 = 3;

    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;

    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;

    /** batProcDate */
    private String batProcDate = null;

    /** AR_SUB_SYS_ID */
    private String arSubSysId = null;

    /** AR_AJE_INTFC OBJECT */
    private int ajeArIntfcObject = 0;

    /** AR_AJE_INTFC ADD */
    private int ajeArIntfcAdd = 0;

    /** AR_RCPT_UN_APPLY UPDATE */
    private int arRcptUnApplyUpdate = 0;

    /** AR_RCPT UPDATE */
    private int arRcptUpdate = 0;

    /** TEMP_ALL_MAX_COUNT */
    private String tempAllMaxCount = "001";

    /** TRX_RSN_CD */
    private static final String TRX_RSN_CD_01 = "01";

    /** TRX_RSN_CD */
    private static final String TRX_RSN_CD_02 = "02";

    /** TRX_RSN_CD */
    private static final String TRX_RSN_CD_03 = "03";

    /** Default Receipt Source */
    private String defArRcptSrcCd;
    
    /**
     * Debug Entry Point
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB272001().executeBatch(NFCB272001.class.getSimpleName());
    }

    /**
     * Init Routine
     */
    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println(NFCM0584I, PROGRAM_ID);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        if (!getGlblCmpyCd()) {
            this.normalRecordCnt = this.ajeArIntfcAdd;
            this.totalRecordCnt = this.ajeArIntfcObject;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0501E, PARAM_NAME_GLBL_CMPY_CD);
        }

        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        
        //--- start add 2016/06/09
        defArRcptSrcCd = ZYPCodeDataUtil.getVarCharConstValue("AR_DEF_RCPT_SRC_CD", this.glblCmpyCd);
        //---- end 2016/06/09
    }

    /**
     * Main Routine
     */
    @Override
    protected void mainRoutine() {
        execute();
    }

    /**
     * Term Routine
     */
    @Override
    protected void termRoutine() {
        String[] sAjeArIntfcObject = {Integer.toString(this.ajeArIntfcObject) };
        S21InfoLogOutput.println(NFCM0586I, sAjeArIntfcObject);
        String[] sAjeArIntfcAdd = {Integer.toString(this.ajeArIntfcAdd) };
        S21InfoLogOutput.println(NFCM0587I, sAjeArIntfcAdd);
        String[] sArRcptUnApplyUpdate = {Integer.toString(this.arRcptUnApplyUpdate) };
        S21InfoLogOutput.println(NFCM0588I, sArRcptUnApplyUpdate);
        String[] sArRcptUpdate = {Integer.toString(this.arRcptUpdate) };
        S21InfoLogOutput.println(NFCM0588I, sArRcptUpdate);

        S21InfoLogOutput.println(NFCM0593I, PROGRAM_ID);

        this.normalRecordCnt = this.ajeArIntfcAdd;
        this.totalRecordCnt = this.ajeArIntfcObject;
        setTermState(TERM_CD.NORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
    }

    /**
     * Get Global Company Code
     * @return boolean TRUE : Get Data FALSE : No Data
     */
    private boolean getGlblCmpyCd() {
        debugLog("getGlblCmpyCd start");

        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            debugLog("getGlblCmpyCd : glblCmpyCd is null");
            return false;
        }

        debugLog("getGlblCmpyCd end");
        return true;
    }

    /**
     */
    private void execute() {
        debugLog("execute start");

        if (!getSubSysCd()) {
            this.normalRecordCnt = this.ajeArIntfcAdd;
            this.totalRecordCnt = this.ajeArIntfcObject;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0632E);
        }

        PreparedStatement stmtSelect = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, this.glblCmpyCd);
            queryParam.put(CST_AR_UN_APPLY_STS_CD_NAME_NEW, NFCConst.CST_AR_UN_APPLY_STS_CD_NEW);
            queryParam.put(CST_AR_UN_APPLY_STS_CD_NAME_VOID, NFCConst.CST_AR_UN_APPLY_STS_CD_VOID);
            queryParam.put(NFCDbConst.AJE_STATUS_FLG_J, NFCConst.CST_FLAG_OFF);
            queryParam.put(NFCDbConst.DEF_TRX_RSN_CD_J, NFCConst.CST_TRX_RSN_CD_AR_TO_CFS);
            queryParam.put("batch", NFCConst.CST_ONL_BAT_TP_CD_BAT);
            
            //---- start add 2016/06/09
            queryParam.put("defRcptSrc", defArRcptSrcCd);
            //---- end 2016/06/09
            
            // queryParam.put("batRcptStsClosed",
            // AR_BAT_RCPT_STS.CLOSED);

            stmtSelect = this.ssmLLClient.createPreparedStatement("getArReceipt", queryParam, execParam);
            rs = stmtSelect.executeQuery();

            AJE_AR_INTFCTMsg insertTMsg = new AJE_AR_INTFCTMsg();
            AR_RCPT_UN_APPLYTMsg updateTMsg = new AR_RCPT_UN_APPLYTMsg();
            AR_RCPTTMsg updateTMsgRcpt = new AR_RCPTTMsg();

            while (rs.next()) {

                this.ajeArIntfcObject++;

                // decide TRX_RSN_CD

                //---- start 2016/06/09 If this is AR_RCPT for offset (dummy), not generate AJE_AR__INTFC.
                if (! (AR_RCPT_TP.OFFSET.equals(rs.getString(NFCDbConst.AR_RCPT_TP_CD)) && AR_RCPT_TRX_TP.OFFSET.equals(rs.getString(NFCDbConst.AR_RCPT_TRX_TP_CD)))) {
                
                    // 110-01
                    if (rs.getString(NFCDbConst.AR_UN_APPLY_STS_CD) == null) {
                        setValues(rs, insertTMsg, TRX_RSN_CD_01);
                        insertTbl(insertTMsg);
                        // 110-02
                    } else if (NFCConst.CST_AR_UN_APPLY_STS_CD_NEW.equals(rs.getString(NFCDbConst.AR_UN_APPLY_STS_CD))) {
                        setValues(rs, insertTMsg, TRX_RSN_CD_02);
                        insertTbl(insertTMsg);
                        // void (negative 110-01 or 110-03)
                    } else {
                        setValues(rs, insertTMsg, null);
                        insertTbl(insertTMsg);
                    }
                }

                // 110-01
                if (rs.getString(NFCDbConst.AR_UN_APPLY_STS_CD) == null) {

                    // Update AR_RCPT
                    updateTMsgRcpt.glblCmpyCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.GLBL_CMPY_CD)));
                    updateTMsgRcpt.rcptNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.RCPT_NUM)));

                    AR_RCPTTMsg target = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsgRcpt);

                    if (target == null) {
                        this.normalRecordCnt = this.ajeArIntfcAdd;
                        this.totalRecordCnt = this.ajeArIntfcObject;
                        setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
                        throw new S21AbendException(NFCM0533E, ITEM_NAME_AR_RCPT);
                    }

                    if ((rs.getString(NFCDbConst.EZUPTIME).equals(target.ezUpTime.getValue())) && (rs.getString(NFCDbConst.EZUPTIMEZONE).equals(target.ezUpTimeZone.getValue()))) {
                        target.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_ON);

                        EZDTBLAccessor.update(target);

                        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(target.getReturnCode())) {
                            this.normalRecordCnt = this.ajeArIntfcAdd;
                            this.totalRecordCnt = this.ajeArIntfcObject;
                            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
                            throw new S21AbendException(NFCM0533E, ITEM_NAME_AR_RCPT);
                        } else {
                            this.arRcptUpdate++;
                        }
                    } else {
                        this.normalRecordCnt = this.ajeArIntfcAdd;
                        this.totalRecordCnt = this.ajeArIntfcObject;
                        setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
                        throw new S21AbendException(NFCM0594E, ITEM_NAME_AR_RCPT);
                    }

                } else {
                    // UPDATE AR_RCPT_UN_APPLY
                    updateTMsg.glblCmpyCd.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.GLBL_CMPY_CD)));
                    updateTMsg.arCashAppPk.setValue(rs.getBigDecimal(NFCDbConst.AR_CASH_APP_PK));
                    updateTMsg.arCashAppSqNum.setValue(NFCCmnMethod.convertDbString(rs.getString(NFCDbConst.AR_CASH_APP_SQ_NUM)));

                    AR_RCPT_UN_APPLYTMsg target = (AR_RCPT_UN_APPLYTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTMsg);

                    if (target == null) {
                        this.normalRecordCnt = this.ajeArIntfcAdd;
                        this.totalRecordCnt = this.ajeArIntfcObject;
                        setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
                        throw new S21AbendException(NFCM0533E, ITEM_NAME_AR_RCPT_UN_APPLY);
                    }

                    if ((rs.getString(NFCDbConst.EZUPTIME).equals(target.ezUpTime.getValue())) && (rs.getString(NFCDbConst.EZUPTIMEZONE).equals(target.ezUpTimeZone.getValue()))) {
                        target.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_ON);
                        String ajeCratVrsnNum = this.batProcDate + "_" + this.tempAllMaxCount;
                        target.ajeCratVrsnNum.setValue(ajeCratVrsnNum);

                        EZDTBLAccessor.update(target);

                        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(target.getReturnCode())) {
                            this.normalRecordCnt = this.ajeArIntfcAdd;
                            this.totalRecordCnt = this.ajeArIntfcObject;
                            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
                            throw new S21AbendException(NFCM0533E, ITEM_NAME_AR_RCPT_UN_APPLY);
                        } else {
                            this.arRcptUnApplyUpdate++;
                        }
                    } else {
                        this.normalRecordCnt = this.ajeArIntfcAdd;
                        this.totalRecordCnt = this.ajeArIntfcObject;
                        setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
                        throw new S21AbendException(NFCM0594E, ITEM_NAME_AR_RCPT_UN_APPLY);
                    }
                }

            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
            // if any error occurs, rollback
            rollback();
            return;
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        int commitGoCount = this.ajeArIntfcAdd;
        if (commitGoCount > 0) {
            commit();
        }

        debugLog("execute end");
    }

    /**
     * @return true:OK false:NG boolean
     */
    private boolean getSubSysCd() {
        debugLog("getSubSysCd start");

        this.arSubSysId = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_SUB_SYS_ID, this.glblCmpyCd);
        if (S21StringUtil.isEmpty(this.arSubSysId)) {
            return false;
        }

        debugLog("getSubSysCd end");
        return true;
    }

    /**
     * @return OraSqNo BigDecimal
     */
    private BigDecimal getOraSqNo() {
        debugLog("getOraSqNo start");

        NFCNumbering numbering = new NFCNumbering();
        NFXC3060Bean numberBean = numbering.getNumber(NFCConst.CST_SEQ_ID_AJE_INTFC, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {
            this.normalRecordCnt = this.ajeArIntfcAdd;
            this.totalRecordCnt = this.ajeArIntfcObject;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0638E, ITEM_NAME_AJE_INTFC_SQ);
        }

        debugLog("getOraSqNo end");
        return numberBean.getOraSqNo();
    }

    /**
     * @param insertTMsgOnAccount AJE_AR_INTFCTMsg
     * @param insertTMsgUnIdentifiedToOnAccount AJE_AR_INTFCTMsg
     * @param insertTMsgUnIdentified AJE_AR_INTFCTMsg
     * @throws java.sql.SQLException SQLError
     */
    private void insertTbl(AJE_AR_INTFCTMsg insertTMsg) {
        debugLog("insertTbl start");

        insertTMsg.ajeIntfcPk.setValue(getOraSqNo());
        EZDTBLAccessor.insert(insertTMsg);
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(insertTMsg.getReturnCode())) {
            this.normalRecordCnt = this.ajeArIntfcAdd;
            this.totalRecordCnt = this.ajeArIntfcObject;
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException(NFCM0532E, ITEM_NAME_AJE_AR_INTFC);
        }
        this.ajeArIntfcAdd++;

        debugLog("insertTbl end");
    }

    /**
     * @param paramRs ResultSet
     * @param insertTMsg AJE_AR_INTFCTMsg
     * @param totalFlg String
     * @throws java.sql.SQLException SQLError
     */
    private void setValues(final ResultSet paramRs, AJE_AR_INTFCTMsg insertTMsg, String trxRsnCd) throws SQLException {
        debugLog("setValues start");

        insertTMsg.glblCmpyCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GLBL_CMPY_CD)));

// START 2019/10/07 H.Umeda [QC#53990,MOD]
//      insertTMsg.glDt.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.GL_DT)));
        insertTMsg.glDt.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.ACCT_DT)));
// END   2019/10/07 H.Umeda [QC#53990,MOD]

        insertTMsg.sysSrcCd.setValue(this.arSubSysId);
        insertTMsg.trxCd.setValue(NFCConst.CST_TRX_CD_AR_RECEIPT);

        insertTMsg.arRcptTrxTpCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_RCPT_TRX_TP_CD)));
        insertTMsg.billToCustCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.PAYER_CUST_CD)));
        // ---- start add 2016/01/19
        insertTMsg.dsAcctNum.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.PAYER_CUST_CD)));
        // ---- end 2016/01/19
        insertTMsg.arCashAppPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        insertTMsg.arCashAppSqNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.arApplyTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.rcptNum.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.RCPT_NUM)));
        insertTMsg.arRcptTpCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_RCPT_TP_CD)));
        insertTMsg.arBankAcctCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_BANK_ACCT_CD)));
        insertTMsg.arAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.arAdjTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.arAdjTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.arTrxNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.arTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        insertTMsg.arTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.crArTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        insertTMsg.crArTrxNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.crArTrxTpCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.coaProdCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.COA_PROD_CD)));
        insertTMsg.dealCcyCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.DEAL_CCY_CD)));
        insertTMsg.funcCcyCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.FUNC_CCY_CD)));
        insertTMsg.exchRate.setValue(paramRs.getBigDecimal(NFCDbConst.EXCH_RATE));

        if (NFCConst.CST_AR_UN_APPLY_STS_CD_VOID.equals(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_UN_APPLY_STS_CD)))) {
            // reversal of 110-01
            // ---- start QC#7002 2016/04/13 convertDbString return
            // blank not null
            if (NFCConst.CST_DB_INIT_VAL_STR.equals(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.PAYER_CUST_CD)))) {
                // ---- end 2016/04/13
                trxRsnCd = TRX_RSN_CD_01;
                insertTMsg.dealAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.DEAL_VOID_AMT).negate());
                insertTMsg.funcAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.FUNC_VOID_AMT).negate());
                insertTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_VOID_AMT);
                insertTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_VOID_AMT);
            } else { // 110-03
                trxRsnCd = TRX_RSN_CD_03;
                // ---- start QC#7002 2016/04/13 don't have to negate
                insertTMsg.dealAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.DEAL_VOID_AMT));
                insertTMsg.funcAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.FUNC_VOID_AMT));
                // ---- end 2016/04/13
                insertTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_VOID_AMT);
                insertTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_VOID_AMT);
            }
        } else {
            if (paramRs.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT).compareTo(BigDecimal.ZERO) != 0) {
                insertTMsg.dealAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.DEAL_RCPT_AMT));
                insertTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_RCPT_AMT);
                insertTMsg.funcAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.FUNC_RCPT_AMT));
                insertTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_RCPT_AMT);
            } else {
                insertTMsg.dealAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.DEAL_NET_RCPT_AMT));
                insertTMsg.dealAttrbItemNm.setValue(NFCDbConst.DEAL_NET_RCPT_AMT);
                insertTMsg.funcAcctAmt.setValue(paramRs.getBigDecimal(NFCDbConst.FUNC_NET_RCPT_AMT));
                insertTMsg.funcAttrbItemNm.setValue(NFCDbConst.FUNC_NET_RCPT_AMT);
            }
        }

        insertTMsg.amtInfoEntyNm.setValue(NFCConst.CST_AMT_INFO_ENTY_NM_AR_RCPT);
        insertTMsg.ajeIntfcCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.cashAppDt.setValue(this.batProcDate);

        insertTMsg.tocCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.TOC_CD)));
        insertTMsg.drCoaAcctCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        insertTMsg.crCoaAcctCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        insertTMsg.drArRcptSrcCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_RCPT_SRC_CD)));
        insertTMsg.crArRcptSrcCd.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.AR_RCPT_SRC_CD)));
        insertTMsg.ajeCratReqFlg.setValue(NFCConst.CST_FLAG_ON);

        insertTMsg.trxRsnCd.setValue(trxRsnCd);

        insertTMsg.rcptChkNum.setValue(NFCCmnMethod.convertDbString(paramRs.getString(NFCDbConst.RCPT_CHK_NUM)));

        debugLog("setValues end");
    }

    /**
     * Debug Log Print
     * @param logmsg String
     */
    protected final void debugLog(final String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }

}
