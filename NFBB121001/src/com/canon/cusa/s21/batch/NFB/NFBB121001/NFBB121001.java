/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB121001;

import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.CM_PROC_STS_CD_N;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.EDI_INTFC_ITEM_LINE_TP_CD_FREIGHT;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.ERR_MSG_CRLF;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.ERR_MSG_ITEM;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_FIELD_MESSAGE;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_FIELD_TIMESTAMP;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_GROUP_ID_CC;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_GROUP_ID_FROM;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_GROUP_ID_TO;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_KEY_CC;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_KEY_TO;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_LEN_FMT_SUBSTR;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_TEMPLATE_ID_M001;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_TS_FMT;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_TYPE_CC;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MAIL_TYPE_TO;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MSG_TXT_HYPHEN;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.MSG_TXT_SPACE;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.NFBM0073E;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.NFBM0184E;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.NFBM0181E;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.NFBM0199E;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.NFBM0207E;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.NFBM0217E;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.PD_ISS_RQST_FLG;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.STRING_LENGTH_100;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.STRING_LENGTH_15;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.STRING_LENGTH_2;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.STRING_LENGTH_30;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.STRING_LENGTH_4;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.STRING_LENGTH_5;
import static com.canon.cusa.s21.batch.NFB.NFBB121001.constant.NFBB121001Constant.VAR_CHAR_CONST_NM_CCY;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NFBI0250_18TMsg;
import business.db.POTMsg;
import business.db.VND_INV_BOL_WRKTMsg;
import business.db.VND_INV_DISC_TERM_WRKTMsg;
import business.db.VND_INV_LIC_ACCS_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsg;
import business.db.VND_INV_WRKTMsg;
import business.db.VND_INV_WRKTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Invoice Import For EDI Vendor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/04   Fujitsu         T.Murai         Create
 * 2016/09/20   Hitachi         K.Kojima        Update          QC#14224
 * 2016/10/14   Fujitsu         T.Murai         Update          QC#15119,14801
 * 2016/10/19   Fujitsu         T.Murai         Update          QC#15331
 * 2016/10/20   Fujitsu         T.Murai         Update          QC#15339
 * 2016/10/26   Fujitsu         T.Murai         Update          QC#15538
 * 2016/11/01   Fujitsu         T.Murai         Update          QC#15683,15681
 * 2016/11/04   Hitachi         T.Tsuchida      Update          QC#15809
 * 2016/11/08   Fujitsu         T.Murai         Update          QC#15811
 * 2016/11/10   Hitachi         T.Tsuchida      Update          QC#15538
 * 2016/11/11   Hitachi         T.Tsuchida      Update          QC#15811
 * 2016/11/15   Fujitsu         T.Murai         Update          QC#15809
 * 2016/12/15   CITS            Y.IWASAKI       Update          QC#16586
 *</pre>
 */
public class NFBB121001 extends S21BatchMain {

    /** Mail Business Error Message */
     private StringBuilder mailBusinessErrorMessage;

    /** Mail Business Error Message Detail */
     private StringBuilder mailBusinessErrorMessageDetail;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Currency Code */
    private String ccyCd = null; // 2016/11/01 [QC#15683, ADD]

    /** Sales Date */
    // private String slsDt; // 2016/10/26 [QC#15538, DEL]

    /** Interface ID */
    private String interfaceId = null;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** total count */
    private int totalCount = 0;

    /** Error Flag : Whether error occurred or not in process */
    boolean allErrorFlg = false;

    /** EDI Line# Length */
    int ediLineNumLength;

    /** PO Line# Length */
    int poLineNumLength;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** S21MailAddress : from */
    private S21MailAddress from = null;
    /** List<S21MailAddress> : addrToList */
    private List<S21MailAddress> addrToList  = null;
    /** List<S21MailAddress> : addrCcList */
    private List<S21MailAddress> addrCcList  = null;
    /** S21MailTemplate : template */
    private S21MailTemplate template = null;

    @Override
    protected void initRoutine() {
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NFBM0199E);
        }

        // START 2016/11/01 [QC#15683, ADD]
        this.ccyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CCY, this.glblCmpyCd);
        // END   2016/11/01 [QC#15683, ADD]

        //this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd); // 2016/10/26 [QC#15538, DEL]
        this.interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            throw new S21AbendException(NFBM0207E, new String[] {"Interface Id" });
        }

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = this.trxAccess.getIntegrationRecord(this.interfaceId);

        VND_INV_LINE_WRKTMsg invLineWrk = new VND_INV_LINE_WRKTMsg();
        this.ediLineNumLength = invLineWrk.getAttr("ediPoOrdDtlLineNum").getDigit();
        this.poLineNumLength = invLineWrk.getAttr("poOrdDtlLineNum").getDigit();

        termCd = TERM_CD.NORMAL_END;

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        this.mailBusinessErrorMessage = new StringBuilder();
        this.mailBusinessErrorMessageDetail = new StringBuilder();
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            // MOD START 2016/11/15 [QC#15809]
            
//            Map<String, Object> params = new HashMap<String, Object>();
//            params.put("interfaceId", this.interfaceId);
//            params.put("trxIdList", this.trxIdList);
//
//            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();
//
//            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            // Add Start 2016/08/22 S21_NA#11928
//            ssmEexcParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//            ssmEexcParam.setFetchSize(DEFAULT_FETCH_SIZE);
//            // Add End 2016/08/22 S21_NA#11928
//
//            stmt = this.ssmLLClient.createPreparedStatement("getTarget", params, ssmEexcParam);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                dataImport(rs);
//            }
//
//            // has Error
//            if (this.errorCount > 0) {
//                termCd = TERM_CD.WARNING_END;
//            }
//            // Add Start 2016/10/20 S21_NA#15339
//            for (BigDecimal trxId : this.trxIdList) {
//                this.trxAccess.endIntegrationProcess(interfaceId, trxId);
//            }
            // Add End   2016/10/20 S21_NA#15339
            
            for (BigDecimal trxId : this.trxIdList) {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("interfaceId", this.interfaceId);
                params.put("trxId", trxId);

                S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

                ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
                ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
                ssmEexcParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
                ssmEexcParam.setFetchSize(DEFAULT_FETCH_SIZE);

                stmt = this.ssmLLClient.createPreparedStatement("getTargetInvByTranId", params, ssmEexcParam);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    dataImport(rs);
                }
                this.trxAccess.endIntegrationProcess(interfaceId, trxId);
            }
            // has Error
            if (this.errorCount > 0) {
                termCd = TERM_CD.WARNING_END;
            }
         // MOD END   2016/11/15 [QC#15809]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.successCount, this.errorCount, this.totalCount);

    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NFBB121001().executeBatch(NFBB121001.class.getSimpleName());
    }

    /**
     * dataImport(The main processing)
     * @throws SQLException
     */
    private void dataImport(ResultSet rs) throws SQLException {

        List<VND_INV_LIC_ACCS_WRKTMsg> invLicTmsgList = new ArrayList<VND_INV_LIC_ACCS_WRKTMsg>();
        List<VND_INV_LINE_WRKTMsg> invLineTmsgList = new ArrayList<VND_INV_LINE_WRKTMsg>();

        BigDecimal dealFrtAmt = getSumFreightAmt(rs); // 2016/10/18 [QC#14801,Add]

        // START 2016/11/11 [QC#15809, ADD]
        String vndInvNum = getForwardSubString(rs.getString("EDI_INTFC_INV_NUM"), STRING_LENGTH_15);
        int targetCnt = countTargetByInv(rs);
        if (targetCnt > 1) {
            String message = S21MessageFunc.clspGetMessage(NFBM0181E, new String[] {"vndInvNum : " + vndInvNum });
            editErrorDetailMailMessage(message);

        } else if (!duplicateCheckByInvNum(vndInvNum)) {
          String message = S21MessageFunc.clspGetMessage(NFBM0181E, new String[] {"vndInvNum : " + vndInvNum });
          editErrorDetailMailMessage(message);
        } else {
            List<Map<String, Object>> dtlInfoList = getTargetI08(rs);
        // END   2016/11/11 [QC#15809, ADD]
        
        // Create [VND_INV_WRK] Data
        // Start 2016/10/18 [QC#14801,Mod]
        VND_INV_WRKTMsg vndInvWrk = getInsertInvMsg(rs, dealFrtAmt, dtlInfoList);
        //VND_INV_WRKTMsg vndInvWrk = getInsertInvMsg(rs);
        // End 2016/10/18 [QC#14801,Mod]

        // Create [VND_INV_DISC_TERM_WRK ] insert Data
        VND_INV_DISC_TERM_WRKTMsg vndInvDiscTermTmsg = getInsertDiscTermMsg(rs);

        // Create [VND_INV_BOL_WRK ] insert Data
        // Start 2016/10/18 [QC#14801,Mod]
        VND_INV_BOL_WRKTMsg vndInvBolTmsg = getInsertBolMsg(rs, dealFrtAmt);
        //VND_INV_BOL_WRKTMsg vndInvBolTmsg = getInsertBolMsg(rs);
        // End 2016/10/18 [QC#14801,Mod]

        // START 2016/10/26 [QC#15538 , DEL]
//        boolean doPoDtlCheckFlg = false;
//        if (ZYPCommonFunc.hasValue(vndInvBolTmsg.ediPoOrdNum)) {
//            doPoDtlCheckFlg = poCheck(vndInvWrk, vndInvBolTmsg);
//        } else {
//            // START 2016/09/20 K.Kojima [QC#14224,MOD]
//            // String message =
//            // S21MessageFunc.clspGetMessage(NFBM0044E, new String[]
//            // {"NFBI0255_01.EDI_PO_ORD_NUM" });
//            String message = S21MessageFunc.clspGetMessage(NFBM0044E, new String[] {"NFBI0250_06.EDI_PO_ORD_NUM" });
//            // END 2016/09/20 K.Kojima [QC#14224,MOD]
//            editErrorDetailMailMessage(message);
//        }
        // END   2016/10/26 [QC#15538 , DEL]

        // START 2016/09/20 K.Kojima [QC#14224,MOD]
        // // Get [NFBI0267_01] Data
        // List<NFBI0267_01TMsg> i0267TMsgList = getTargetNfbi0267(rs);
        // Get [NFBI0250_18] Data
        List<NFBI0250_18TMsg> i0250_18TMsgList = getTargetNfbi0250_18(rs);
        // END 2016/09/20 K.Kojima [QC#14224,MOD]

        int lineNum = 1;
        String invNum = null;
        String invNumPre = null;
        // insert VND_INV_LINE_WRK, VND_INV_LIC_ACCS_WRK
        // START 2016/09/20 K.Kojima [QC#14224,MOD]
        // for (NFBI0267_01TMsg i0267Msg : i0267TMsgList) {
        for (NFBI0250_18TMsg i0250_18Msg : i0250_18TMsgList) {
            // END 2016/09/20 K.Kojima [QC#14224,MOD]

            // START 2016/09/20 K.Kojima [QC#14224,MOD]
            // invNum = i0267Msg.ediIntfcInvNum.getValue();
            invNum = i0250_18Msg.ediIntfcInvNum.getValue();
            // END 2016/09/20 K.Kojima [QC#14224,MOD]
            if (ZYPCommonFunc.hasValue(invNum)) {
                if (invNum.equals(invNumPre)) {
                    lineNum++;
                } else {
                    lineNum = 1;
                    invNumPre = invNum;
                }
            }

            // Create [VND_INV_LINE_WRK ] insert Data
            // START 2016/09/20 K.Kojima [QC#14224,MOD]
            // VND_INV_LINE_WRKTMsg vndInvLineWrk =
            // getInsertLineMsg(i0267Msg, lineNum);
            VND_INV_LINE_WRKTMsg vndInvLineWrk = getInsertLineMsg(i0250_18Msg, lineNum);
            // END 2016/09/20 K.Kojima [QC#14224,MOD]
            invLineTmsgList.add(vndInvLineWrk);

            // START 2016/10/26 [QC#15538, DEL]
//            if (!ZYPCommonFunc.hasValue(vndInvLineWrk.shipQty)) {
//                // START 2016/09/20 K.Kojima [QC#14224,MOD]
//                // String message =
//                // S21MessageFunc.clspGetMessage(NFBM0044E, new
//                // String[] {"NFBI0267_01.EDI_INTFC_INV_CD" });
//                String message = S21MessageFunc.clspGetMessage(NFBM0044E, new String[] {"NFBI0250_18.EDI_INTFC_INV_CD" });
//                // END 2016/09/20 K.Kojima [QC#14224,MOD]
//                editErrorDetailMailMessage(message);
//            }
//            if (!checkMdse(vndInvLineWrk.mdseCd.getValue())) {
//                String message = S21MessageFunc.clspGetMessage(NFDM0012E, new String[] {"MDSE CD: " + vndInvLineWrk.mdseCd.getValue() });
//                editErrorDetailMailMessage(message);
//            }
            // END   2016/10/26 [QC#15538, DEL]

            // Create [VND_INV_LIC_ACCS_WRK ] insert Data
            // START 2016/09/20 K.Kojima [QC#14224,MOD]
            // VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk =
            // getInsertLicMsg(i0267Msg, lineNum);
            VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk = getInsertLicMsg(i0250_18Msg, lineNum);
            // END 2016/09/20 K.Kojima [QC#14224,MOD]
            invLicTmsgList.add(vndInvLicAccsWrk);

            // START 2016/10/26 [QC#15538, DEL]
//            if (doPoDtlCheckFlg) {
//                poDtlCheck(vndInvWrk, vndInvBolTmsg, vndInvLineWrk);
//            }
            // END   2016/10/26 [QC#15538, DEL]
        }

        // START 2016/11/04 [QC#15809, MOD]
//        // START 2016/10/26 [QC#15538, MOD]
//        this.successCount++;
////      // Result Check
////      if (this.mailBusinessErrorMessageDetail.length() == 0) {
////          this.successCount++;
////
////      } else {
////          // Update VND_INV_WRK (Error End)
////          ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvProcStsCd, VND_INV_PROC_STS.ERROR);
////
////          // send Error Message
////          editItemErrorMailMessage(invNum);
////          sendMail();
////
////          this.mailBusinessErrorMessage = new StringBuilder();
////          this.mailBusinessErrorMessageDetail = new StringBuilder();
////          this.errorCount++;
////      }
//      // END   2016/10/26 [QC#15538, MOD]
//
//        EZDFastTBLAccessor.insert(vndInvWrk);
//        EZDFastTBLAccessor.insert(vndInvBolTmsg);
//        EZDFastTBLAccessor.insert(vndInvDiscTermTmsg);
//        if (invLineTmsgList.size() > 0) {
//            EZDFastTBLAccessor.insert(invLineTmsgList.toArray(new VND_INV_LINE_WRKTMsg[invLineTmsgList.size()]));
//            EZDFastTBLAccessor.insert(invLicTmsgList.toArray(new VND_INV_LIC_ACCS_WRKTMsg[invLicTmsgList.size()]));
//        }

        EZDFastTBLAccessor.insert(vndInvWrk);
        if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(vndInvWrk.getReturnCode())) {
            // START 2016/11/11 [QC#15809. MOD]
            // this.errorCount++;
            // throw new S21AbendException(NFBM0073E, new String[] {vndInvWrk.getTableName(), vndInvWrk.getReturnCode()});
            String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {vndInvWrk.getTableName(), vndInvWrk.getReturnCode() });
            editErrorDetailMailMessage(message);
            // END 2016/11/11 [QC#15809. MOD]
        }
        EZDFastTBLAccessor.insert(vndInvBolTmsg);
        if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(vndInvBolTmsg.getReturnCode())) {
            // START 2016/11/11 [QC#15809. MOD]
            // this.errorCount++;
            // throw new S21AbendException(NFBM0073E, new String[] {vndInvBolTmsg.getTableName(), vndInvBolTmsg.getReturnCode()});
            String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {vndInvBolTmsg.getTableName(), vndInvBolTmsg.getReturnCode() });
            editErrorDetailMailMessage(message);
            // END 2016/11/11 [QC#15809. MOD]
        }
        EZDFastTBLAccessor.insert(vndInvDiscTermTmsg);
        if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(vndInvDiscTermTmsg.getReturnCode())) {
            // START 2016/11/11 [QC#15809. MOD]
            // this.errorCount++;
            // throw new S21AbendException(NFBM0073E, new String[] {vndInvDiscTermTmsg.getTableName(), vndInvDiscTermTmsg.getReturnCode()});
            String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {vndInvDiscTermTmsg.getTableName(), vndInvDiscTermTmsg.getReturnCode() });
            editErrorDetailMailMessage(message);
            // END 2016/11/11 [QC#15809. MOD]
        }

        if (invLineTmsgList.size() > 0) {
            int rtn = EZDFastTBLAccessor.insert(invLineTmsgList.toArray(new VND_INV_LINE_WRKTMsg[invLineTmsgList.size()]));
            if (rtn != invLineTmsgList.size()) {
                // START 2016/11/11 [QC#15809. MOD]
                // this.errorCount++;
                // throw new S21AbendException(NFBM0073E, new String[] {invLineTmsgList.get(0).getTableName(), Integer.toString(rtn)});
                String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {invLineTmsgList.get(0).getTableName(), Integer.toString(rtn) });
                editErrorDetailMailMessage(message);
                // END 2016/11/11 [QC#15809. MOD]
            }
            rtn = EZDFastTBLAccessor.insert(invLicTmsgList.toArray(new VND_INV_LIC_ACCS_WRKTMsg[invLicTmsgList.size()]));
            if (rtn != invLicTmsgList.size()) {
                // START 2016/11/11 [QC#15809. MOD]
                // this.errorCount++;
                // throw new S21AbendException(NFBM0073E, new String[] {invLicTmsgList.get(0).getTableName(), Integer.toString(rtn)});
                String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {invLicTmsgList.get(0).getTableName(), Integer.toString(rtn) });
                editErrorDetailMailMessage(message);
                // END 2016/11/11 [QC#15809. MOD]
            }
        }
        } // 2016/11/11 [QC#15809, ADD]

        // START 2016/11/11 [QC#15809, ADD]
        // Result Check
        if (this.mailBusinessErrorMessageDetail.length() == 0) {
            this.successCount++;

        } else {

            // send Error Message
            editItemErrorMailMessage(vndInvNum);
            sendMail();

            this.mailBusinessErrorMessage = new StringBuilder();
            this.mailBusinessErrorMessageDetail = new StringBuilder();
            this.errorCount++;
        }
        // END   2016/11/11 [QC#15809, ADD]
//        this.successCount++; // DEL2016/11/11 [QC#15809, DEL]
        // END 2016/11/04 [QC#15809, MOD]
        commit();
    }

    // START 2016/10/26 [QC#15538, DEL]
//    /**
//     * PO_DTL Check
//     * @param vndInvWrk
//     * @param vndInvBolWrk
//     * @param isErrorVndInvWrk
//     * @throws SQLExceptiongetPoDtlKey(vndInvBolWrk, poDtlTMsg, null)
//     */
//    private void poDtlCheck(VND_INV_WRKTMsg vndInvWrk, VND_INV_BOL_WRKTMsg vndInvBolWrk, VND_INV_LINE_WRKTMsg vndInvLineWrk) throws SQLException {
//
//        PO_DTLTMsg poDtlTMsg = new PO_DTLTMsg();
//
//        // A) Get PO_DTL From EDI API(NPZC110001) Call
//        NPZC110001PMsg pMsg = new NPZC110001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum, vndInvBolWrk.ediPoOrdNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, vndInvBolWrk.poOrdNum);
//
//        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).ediPoOrdDtlLineNum, vndInvLineWrk.ediPoOrdDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).poOrdDtlLineNum, vndInvLineWrk.poOrdDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.poDetailInList.no(0).mdseCd, vndInvLineWrk.mdseCd);
//
//        pMsg.poDetailInList.setValidCount(1);
//        NPZC110001 apiGetPoLine = new NPZC110001();
//        apiGetPoLine.execute(pMsg, ONBATCH_TYPE.BATCH);
//
//        // Add Start 2016/08/22 S21_NA#11928
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            
//            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
//            for (String xxMsgId : xxMsgIdList) {
//                S21InfoLogOutput.println(xxMsgId);
//                String errorApi = "NPZC110001:" + S21MessageFunc.clspGetMessage(xxMsgId);
//                editErrorDetailMailMessage(errorApi);
//                return;
//            }
//        }
//        // Add End 2016/08/22 S21_NA#11928
//
//        if (pMsg.poDetailOutList.no(0) == null) {
//            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, pMsg.poOrdNum_O);
//            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, vndInvLineWrk.ediPoOrdDtlLineNum);
//            String message = S21MessageFunc.clspGetMessage(NFBM0222E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, null) });
//            editErrorDetailMailMessage(message);
//            return;
//        }
//
//        if (!ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).poOrdDtlLineNum)) {
//
//            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdNum, pMsg.poOrdNum_O);
//            ZYPEZDItemValueSetter.setValue(poDtlTMsg.poOrdDtlLineNum, vndInvLineWrk.ediPoOrdDtlLineNum);
//            String message = S21MessageFunc.clspGetMessage(NFBM0222E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, null) });
//            editErrorDetailMailMessage(message);
//        } else {
//            // Get [PO] Data
//            poDtlTMsg = getPoDtl(pMsg.poOrdNum_O.getValue(), pMsg.poDetailOutList.no(0).poOrdDtlLineNum.getValue());
//
//            if (ZYPCommonFunc.hasValue(pMsg.poDetailOutList.no(0).xxMsgId)) {
//                String message = S21MessageFunc.clspGetMessage(NFBM0223E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, poDtlTMsg.mdseCd.getValue()) });
//                editErrorDetailMailMessage(message);
//                return;
//            }
//
//            if (poDtlTMsg == null) {
//                return;
//            }
//            String poStsCd = poDtlTMsg.poStsCd.getValue();
//
//            // PO_STS is "Canceled", "Saved", "Waiting for Approval",
//            // in the case of blank and error. (However, the process continues)
//            if (ZYPCommonFunc.hasValue(poStsCd)) {
//                if (PO_STS.CANCELLED.equals(poStsCd)) {
//                    String message = S21MessageFunc.clspGetMessage(NFBM0220E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, null) });
//                    editErrorDetailMailMessage(message);
//                    return;
//                } else if (PO_STS.SAVED.equals(poStsCd)) {
//                    String message = S21MessageFunc.clspGetMessage(NFBM0218E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, null) });
//                    editErrorDetailMailMessage(message);
//                    return;
//                } else if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
//                    String message = S21MessageFunc.clspGetMessage(NFBM0219E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, null) });
//                    editErrorDetailMailMessage(message);
//                    return;
//                }
//            } else {
//                String message = S21MessageFunc.clspGetMessage(NFBM0221E, new String[] {getPoDtlKey(vndInvBolWrk, poDtlTMsg, null) });
//                editErrorDetailMailMessage(message);
//                return;
//            }
//        }
//    }
//
//    /**
//     * PO related check
//     * @param vndInvWrk VND_INV_WRKTMsg
//     * @param vndInvBolWrk VND_INV_BOL_WRKTMsg
//     * @return boolean : whether to do po_detail related Check
//     */
//    private boolean poCheck(VND_INV_WRKTMsg vndInvWrk, VND_INV_BOL_WRKTMsg vndInvBolWrk) {
//
//        POTMsg poTMsg = null;
//
//        // A) Get PO From EDI API(NPZC110001) Call
//        NPZC110001PMsg pMsg = new NPZC110001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.slsDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.ediPoOrdNum, vndInvBolWrk.ediPoOrdNum);
//        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum_I, vndInvBolWrk.poOrdNum);
//
//        pMsg.poDetailInList.setValidCount(0);
//        NPZC110001 apiGetPoLine = new NPZC110001();
//        apiGetPoLine.execute(pMsg, ONBATCH_TYPE.BATCH);
//
//        // Add Start 2016/08/22 S21_NA#11928
//        if (S21ApiUtil.isXxMsgId(pMsg)) {
//            
//            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
//            for (String xxMsgId : xxMsgIdList) {
//                S21InfoLogOutput.println(xxMsgId);
//                String errorApi = "NPZC110001:" + S21MessageFunc.clspGetMessage(xxMsgId);
//                editErrorDetailMailMessage(errorApi);
//                return false;
//            }
//        }
//        // Add End 2016/08/22 S21_NA#11928
//
//        if (ZYPCommonFunc.hasValue(pMsg.poOrdNum_O)) {
//            // Get [PO] Data
//            poTMsg = getPo(pMsg.poOrdNum_O.getValue());
//        }
//
//        if (poTMsg == null) {
//            return false;
//        }
//        String poStsCd = poTMsg.poStsCd.getValue();
//
//        // PO_STS is "Canceled", "Saved", "Waiting for Approval",
//        // in the case of blank and error. (However, the process continues)
//        if (ZYPCommonFunc.hasValue(poStsCd)) {
//            if (PO_STS.CANCELLED.equals(poStsCd)) {
//                String message = S21MessageFunc.clspGetMessage(NFBM0220E, new String[] {getPoKey(vndInvBolWrk, poTMsg) });
//                editErrorDetailMailMessage(message);
//                return true;
//            } else if (PO_STS.SAVED.equals(poStsCd)) {
//                String message = S21MessageFunc.clspGetMessage(NFBM0218E, new String[] {getPoKey(vndInvBolWrk, poTMsg) });
//                editErrorDetailMailMessage(message);
//                return true;
//            } else if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
//                String message = S21MessageFunc.clspGetMessage(NFBM0219E, new String[] {getPoKey(vndInvBolWrk, poTMsg) });
//                editErrorDetailMailMessage(message);
//                return true;
//            }
//        } else {
//            String message = S21MessageFunc.clspGetMessage(NFBM0221E, new String[] {getPoKey(vndInvBolWrk, poTMsg) });
//            editErrorDetailMailMessage(message);
//            return true;
//        }
//
//        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.poOrdNum, poTMsg.poOrdNum);
//        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.poApvlDt, poTMsg.poApvlDt);
//        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndCd, poTMsg.vndCd);
//
//        return true;
//    }
//
//    /**
//     * get Error Message parameter
//     * @param vndInvBolWrk
//     * @param poTMsg
//     * @return String
//     */
//    private String getPoKey(VND_INV_BOL_WRKTMsg vndInvBolWrk, POTMsg poTMsg) {
//
//        StringBuilder poKey = new StringBuilder();
//        poKey.append(VND_INV_NUM);
//        poKey.append(MSG_TXT_EQUALS);
//        poKey.append(vndInvBolWrk.vndInvNum.getValue());
//        poKey.append(MSG_TXT_DELIMITER);
//        poKey.append(MSG_TXT_EDI_PO_NUM);
//        poKey.append(MSG_TXT_EQUALS);
//        poKey.append(vndInvBolWrk.ediPoOrdNum.getValue());
//        poKey.append(MSG_TXT_DELIMITER);
//        poKey.append(MSG_TXT_PO_NUM);
//        poKey.append(MSG_TXT_EQUALS);
//        poKey.append(poTMsg.poOrdNum.getValue());
//        return poKey.toString();
//    }
//
//    private String getPoDtlKey(VND_INV_BOL_WRKTMsg vndInvBolWrk, PO_DTLTMsg poDtlTMsg, String poDtlMdseCd) {
//
//        StringBuilder poDtlKey = new StringBuilder();
//        poDtlKey.append(VND_INV_NUM);
//        poDtlKey.append(MSG_TXT_EQUALS);
//        poDtlKey.append(vndInvBolWrk.vndInvNum.getValue());
//        poDtlKey.append(MSG_TXT_DELIMITER);
//        poDtlKey.append(MSG_TXT_EDI_PO_NUM);
//        poDtlKey.append(MSG_TXT_EQUALS);
//        poDtlKey.append(vndInvBolWrk.ediPoOrdNum.getValue());
//        poDtlKey.append(MSG_TXT_DELIMITER);
//        poDtlKey.append(MSG_TXT_PO_NUM);
//        poDtlKey.append(MSG_TXT_EQUALS);
//        poDtlKey.append(poDtlTMsg.poOrdNum.getValue());
//        poDtlKey.append(MSG_TXT_DELIMITER);
//        poDtlKey.append(MSG_TXT_LINE_NUM);
//        poDtlKey.append(MSG_TXT_EQUALS);
//        poDtlKey.append(poDtlTMsg.poOrdDtlLineNum.getValue());
//
//        if (ZYPCommonFunc.hasValue(poDtlMdseCd)) {
//            poDtlKey.append(MSG_TXT_DELIMITER);
//            poDtlKey.append(MSG_TXT_PO_DTL_MDSE_CD);
//            poDtlKey.append(MSG_TXT_EQUALS);
//            poDtlKey.append(poDtlMdseCd);
//        }
//        return poDtlKey.toString();
//    }
    // END   2016/10/26 [QC#15538, DEL]

    // START 2016/09/20 K.Kojima [QC#14224,MOD]
    // /**
    // * getTargetNfbi0267
    // * @param rs ResultSet
    // * @return List<NFBI0267_01TMsg>
    // * @throws SQLException
    // */
    // private List<NFBI0267_01TMsg> getTargetNfbi0267(ResultSet rs)
    // throws SQLException {
    //
    // Map<String, Object> params = new HashMap<String, Object>();
    // params.put("interfaceId", rs.getString("INTERFACE_ID"));
    // params.put("trxId", rs.getString("TRANSACTION_ID"));
    // params.put("invNum", rs.getString("EDI_INTFC_INV_NUM"));
    //
    // List<NFBI0267_01TMsg> msgList = (List<NFBI0267_01TMsg>)
    // ssmBatchClient.queryObjectList("getTargetNfbi0267", params);
    // return msgList;
    // }
    /**
     * getTargetNfbi0250_18
     * @param rs ResultSet
     * @return List<NFBI0250_18TMsg>
     * @throws SQLException
     */
    private List<NFBI0250_18TMsg> getTargetNfbi0250_18(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceId", rs.getString("INTERFACE_ID"));
        params.put("trxId", rs.getString("TRANSACTION_ID"));
        params.put("invNum", rs.getString("EDI_INTFC_INV_NUM"));
        params.put("lineTpCd", EDI_INTFC_ITEM_LINE_TP_CD_FREIGHT); // 2016/10/14 [QC#14801,ADD]

        List<NFBI0250_18TMsg> msgList = (List<NFBI0250_18TMsg>) ssmBatchClient.queryObjectList("getTargetNfbi0250_18", params);
        return msgList;
    }

    // END 2016/09/20 K.Kojima [QC#14224,MOD]

    /**
     * get new VND_INV_TMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private VND_INV_WRKTMsg getInsertInvMsg(ResultSet rs, BigDecimal invTotDealFrtAmt, List<Map<String, Object>> dtlInfoList) throws SQLException { // 2016/10/18 [QC#14801,Add] BigDecimal invTotDealFrtAmt
        VND_INV_WRKTMsg getMsg = new VND_INV_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(rs.getString("EDI_INTFC_INV_NUM"), STRING_LENGTH_15));
        ZYPEZDItemValueSetter.setValue(getMsg.origVndInvNum, getForwardSubString(rs.getString("EDI_INTFC_INV_NUM"),STRING_LENGTH_15)); // 2016/11/01 [QC#15683, ADD]
        // END 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.invDt, rs.getString("EDI_INTFC_INV_DT_TXT"));
        // START 2016/11/01 [QC#15681, MOD]
        //ZYPEZDItemValueSetter.setValue(getMsg.vndCd, getVndCD(rs.getString("EDI_INTFC_PO_NUM")));
        String poNum = checkEdiPoNum(rs.getString("EDI_INTFC_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(getMsg.vndCd, getVndCD(poNum));
        // END   2016/11/01 [QC#15681, MOD]
        // START 2016/11/10 T.Tsuchida [QC#15538,ADD]
        ZYPEZDItemValueSetter.setValue(getMsg.invTpCd, INV_TP.INVOICE);
        // END 2016/11/10 T.Tsuchida [QC#15538,ADD]

        // START 2016/11/15 T.Murai [QC#15809,Mod]
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcNm, rs.getString("EDI_INTFC_BILL_TXT"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcFirstLineAddr, rs.getString("EDI_INTFC_BILL_ADDR_01"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcScdLineAddr, rs.getString("EDI_INTFC_BILL_ADDR_02"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcThirdLineAddr, rs.getString("EDI_INTFC_BILL_ADDR_03"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcFrthLineAddr, rs.getString("EDI_INTFC_BILL_ADDR_04"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcCtyAddr, rs.getString("EDI_INTFC_BILL_CTY_ADDR"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcStCd, rs.getString("EDI_INTFC_BILL_ST_INT_TXT"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcProvNm, rs.getString("EDI_INTFC_BILL_PROV_TXT"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcCntyNm, rs.getString("EDI_INTFC_BILL_CNTY_TXT"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcPostCd, rs.getString("EDI_INTFC_BILL_POST_TXT"));
//        ZYPEZDItemValueSetter.setValue(getMsg.ofcCtryCd, rs.getString("EDI_INTFC_BILL_CTRY_TXT"));
        if (dtlInfoList!= null && dtlInfoList.size() > 0) {
            Map<String, Object> dtlInfo = dtlInfoList.get(0);
            ZYPEZDItemValueSetter.setValue(getMsg.ofcNm, (String) dtlInfo.get("EDI_INTFC_BILL_TXT"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcFirstLineAddr, (String) dtlInfo.get("EDI_INTFC_BILL_ADDR_01"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcScdLineAddr, (String) dtlInfo.get("EDI_INTFC_BILL_ADDR_02"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcThirdLineAddr, (String) dtlInfo.get("EDI_INTFC_BILL_ADDR_03"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcFrthLineAddr, (String) dtlInfo.get("EDI_INTFC_BILL_ADDR_04"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcCtyAddr, (String) dtlInfo.get("EDI_INTFC_BILL_CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcStCd, (String) dtlInfo.get("EDI_INTFC_BILL_ST_INT_TXT"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcProvNm, (String) dtlInfo.get("EDI_INTFC_BILL_PROV_TXT"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcCntyNm, (String) dtlInfo.get("EDI_INTFC_BILL_CNTY_TXT"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcPostCd, (String) dtlInfo.get("EDI_INTFC_BILL_POST_TXT"));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcCtryCd, (String) dtlInfo.get("EDI_INTFC_BILL_CTRY_TXT"));
        }
        // END   2016/11/15 T.Murai [QC#15809,Mod]

        // ZYPEZDItemValueSetter.setValue(getMsg.vndPmtTermCd, rs.getString("EDI_INTFC_TP_CD")); 
        ZYPEZDItemValueSetter.setValue(getMsg.vndPmtTermNm, rs.getString("EDI_INTFC_TERM_INT_TXT_01"));
        ZYPEZDItemValueSetter.setValue(getMsg.invTotDealNetAmt, getAmt(rs.getString("EDI_INTFC_INV_TXT")));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvProcStsCd, VND_INV_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(getMsg.itrlIntfcId, rs.getString("INTERFACE_ID"));
        ZYPEZDItemValueSetter.setValue(getMsg.cmProcStsCd, CM_PROC_STS_CD_N);
        ZYPEZDItemValueSetter.setValue(getMsg.psIssRqstFlg, PD_ISS_RQST_FLG);

        ZYPEZDItemValueSetter.setValue(getMsg.dealCcyCd, this.ccyCd); // 2016/11/01 [QC#15683, ADD]
        ZYPEZDItemValueSetter.setValue(getMsg.invTotDealFrtAmt, invTotDealFrtAmt); // Start 2016/10/18 [QC#14801,Add]

        return getMsg;
    }

    /**
     * get new VND_INV_DISC_TERMTMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private VND_INV_DISC_TERM_WRKTMsg getInsertDiscTermMsg(ResultSet rs) throws SQLException {
        VND_INV_DISC_TERM_WRKTMsg getMsg = new VND_INV_DISC_TERM_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(rs.getString("EDI_INTFC_INV_NUM"),STRING_LENGTH_15));
        // END 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.invCashDiscTermDtlNum, "1");

        return getMsg;
    }

    /**
     * get new VND_INV_BOL_WRKTMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private VND_INV_BOL_WRKTMsg getInsertBolMsg(ResultSet rs, BigDecimal shipDealFrtAmt) throws SQLException { // 2016/10/18 [QC#14801,Add] BigDecimal shipDealFrtAmt
        VND_INV_BOL_WRKTMsg getMsg = new VND_INV_BOL_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(rs.getString("EDI_INTFC_INV_NUM"),STRING_LENGTH_15));
        // END 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.ediPoOrdNum, rs.getString("EDI_INTFC_PO_NUM"));

        ZYPEZDItemValueSetter.setValue(getMsg.shipDealFrtAmt, shipDealFrtAmt); //2016/10/18 [QC#14801,Add]
        
        return getMsg;
    }

    // START 2016/09/20 K.Kojima [QC#14224,MOD]
    // /**
    // * get new VND_INV_LINE_WRKTMsg
    // * @param i0267Msg NFBI0267_01TMsg
    // * @param lineNum int
    // * @return VND_INV_LINE_WRKTMsg
    // * @throws SQLException
    // */
    // private VND_INV_LINE_WRKTMsg getInsertLineMsg(NFBI0267_01TMsg
    // i0267Msg, int lineNum) throws SQLException {
    // VND_INV_LINE_WRKTMsg getMsg = new VND_INV_LINE_WRKTMsg();
    //
    // ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum,
    // i0267Msg.ediIntfcInvNum);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum,
    // ZYPCommonFunc.leftPad(String.valueOf(lineNum), STRING_LENGTH_5,
    // "0"));
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubNum, "001");
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubTrxNum,
    // "001");
    // ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd,
    // i0267Msg.ediIntfcItemCd);
    // ZYPEZDItemValueSetter.setValue(getMsg.mdseCd,
    // i0267Msg.ediIntfcItemCd);
    // ZYPEZDItemValueSetter.setValue(getMsg.ordQty,
    // toBigDecimal(i0267Msg.ediIntfcInvCd.getValue()));
    // ZYPEZDItemValueSetter.setValue(getMsg.shipQty,
    // toBigDecimal(i0267Msg.ediIntfcInvCd.getValue()));
    // ZYPEZDItemValueSetter.setValue(getMsg.poOrdDtlLineNum,
    // i0267Msg.ediIntfcPoLineNum);
    // ZYPEZDItemValueSetter.setValue(getMsg.ediPoOrdDtlLineNum,
    // i0267Msg.ediIntfcPoLineNum);
    // ZYPEZDItemValueSetter.setValue(getMsg.manPrcFlg,
    // ZYPConstant.FLG_OFF_N);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndMdseIntgFlg,
    // ZYPConstant.FLG_OFF_N);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndPoReqFlg,
    // ZYPConstant.FLG_OFF_N);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndShpgIntgOnlyFlg,
    // ZYPConstant.FLG_OFF_N);
    //
    // return getMsg;
    // }
    /**
     * get new VND_INV_LINE_WRKTMsg
     * @param i0250_18Msg NFBI0250_18TMsg
     * @param lineNum int
     * @return VND_INV_LINE_WRKTMsg
     * @throws SQLException
     */
    private VND_INV_LINE_WRKTMsg getInsertLineMsg(NFBI0250_18TMsg i0250_18Msg, int lineNum) throws SQLException {
        VND_INV_LINE_WRKTMsg getMsg = new VND_INV_LINE_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(i0250_18Msg.ediIntfcInvNum.getValue(), STRING_LENGTH_15));
        // END 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum), STRING_LENGTH_5, "0"));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubTrxNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd, i0250_18Msg.ediIntfcItemCd);
        ZYPEZDItemValueSetter.setValue(getMsg.mdseCd, i0250_18Msg.ediIntfcItemCd);
        // START 2016/11/08 [QC#15811, MOD]
//        ZYPEZDItemValueSetter.setValue(getMsg.ordQty, toBigDecimal(i0250_18Msg.ediIntfcInvCd.getValue()));
//        ZYPEZDItemValueSetter.setValue(getMsg.shipQty, toBigDecimal(i0250_18Msg.ediIntfcInvCd.getValue()));
        ZYPEZDItemValueSetter.setValue(getMsg.ordQty, getAmt(i0250_18Msg.ediIntfcInvCd.getValue()));
        ZYPEZDItemValueSetter.setValue(getMsg.shipQty, getAmt(i0250_18Msg.ediIntfcInvCd.getValue()));
        // START 2016/11/08 [QC#15811, MOD]
        // QC#16586
        // PO_ORD_DTL_LINE_NUM/EDI_PO_DTL_LINE_NUM: Fill values with "0" at left side.
        String ediPoOrdDtlLineNum = ZYPCommonFunc.leftPad(i0250_18Msg.ediIntfcPoLineNum.getValue(), this.ediLineNumLength, "0");
        ediPoOrdDtlLineNum = getSubString(ediPoOrdDtlLineNum, this.ediLineNumLength);
        String poOrdDtlLineNum = ZYPCommonFunc.leftPad(ediPoOrdDtlLineNum, this.poLineNumLength, "0");
        poOrdDtlLineNum = getSubString(poOrdDtlLineNum, this.poLineNumLength);
        ZYPEZDItemValueSetter.setValue(getMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(getMsg.ediPoOrdDtlLineNum, ediPoOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(getMsg.manPrcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.vndMdseIntgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.vndPoReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.vndShpgIntgOnlyFlg, ZYPConstant.FLG_OFF_N);
        // START 2016/11/11 T.Tsuchida [QC#15811,ADD]
        ZYPEZDItemValueSetter.setValue(getMsg.dealNetUnitPrcAmt, getAmt(i0250_18Msg.ediIntfcUnitPrcTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineDealNetAmt, getAmt(i0250_18Msg.ediIntfcInvPrcTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(getMsg.dealGrsUnitPrcAmt, getAmt(i0250_18Msg.ediIntfcUnitPrcTxt.getValue()));
        ZYPEZDItemValueSetter.setValue(getMsg.dealGrsTotPrcAmt, getAmt(i0250_18Msg.ediIntfcInvPrcTxt.getValue()));
        // END 2016/11/11 T.Tsuchida [QC#15811,ADD]

        return getMsg;
    }

    // END 2016/09/20 K.Kojima [QC#14224,MOD]

    private BigDecimal toBigDecimal(String str) {
        if (ZYPCommonFunc.hasValue(str)) {
            return new BigDecimal(str);
        }
        return null;
    }

    // START 2016/09/20 K.Kojima [QC#14224,MOD]
    // /**
    // * get new VND_INV_LIC_ACCS_WRKTMsg
    // * @param i0267Msg NFBI0267_01TMsg
    // * @param lineNum int
    // * @return VND_INV_LIC_ACCS_WRKTMsg
    // * @throws SQLException
    // */
    // private VND_INV_LIC_ACCS_WRKTMsg
    // getInsertLicMsg(NFBI0267_01TMsg i0267Msg, int lineNum) throws
    // SQLException {
    // VND_INV_LIC_ACCS_WRKTMsg getMsg = new
    // VND_INV_LIC_ACCS_WRKTMsg();
    //
    // ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd,
    // this.glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum,
    // i0267Msg.ediIntfcInvNum);
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum,
    // ZYPCommonFunc.leftPad(String.valueOf(lineNum), STRING_LENGTH_5,
    // "0"));
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubNum, "001");
    // ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubTrxNum,
    // "001");
    // ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd,
    // i0267Msg.ediIntfcItemCd);
    // ZYPEZDItemValueSetter.setValue(getMsg.mdseCd,
    // i0267Msg.ediIntfcItemCd);
    // ZYPEZDItemValueSetter.setValue(getMsg.licAccsNum,
    // i0267Msg.ediIntfcItemCd);
    //
    // return getMsg;
    // }
    /**
     * get new VND_INV_LIC_ACCS_WRKTMsg
     * @param i0250_18Msg NFBI0250_18TMsg
     * @param lineNum int
     * @return VND_INV_LIC_ACCS_WRKTMsg
     * @throws SQLException
     */
    private VND_INV_LIC_ACCS_WRKTMsg getInsertLicMsg(NFBI0250_18TMsg i0250_18Msg, int lineNum) throws SQLException {
        VND_INV_LIC_ACCS_WRKTMsg getMsg = new VND_INV_LIC_ACCS_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(i0250_18Msg.ediIntfcInvNum.getValue(), STRING_LENGTH_15));
        // END 2016/11/07 E.Kameishi [QC#15790, MOD]
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum), STRING_LENGTH_5, "0"));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubTrxNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd, i0250_18Msg.ediIntfcItemCd);
        ZYPEZDItemValueSetter.setValue(getMsg.mdseCd, i0250_18Msg.ediIntfcItemCd);
        ZYPEZDItemValueSetter.setValue(getMsg.licAccsNum, i0250_18Msg.ediIntfcItemCd);

        return getMsg;
    }

    // END 2016/09/20 K.Kojima [QC#14224,MOD]

    // START 2016/10/26 [QC#15538, DEL]
//    /**
//     * checkMdse 
//     * @param mdseCd
//     * @return
//     */
//    private boolean checkMdse(String mdseCd) {
//
//        if (!ZYPCommonFunc.hasValue(mdseCd)) {
//            return false;
//        }
//
//        MDSETMsg paramMsg = new MDSETMsg();
//        paramMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//        paramMsg.mdseCd.setValue(mdseCd);
//        MDSETMsg mdseMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(paramMsg);
//
//        if (mdseMsg != null) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * getPo
//     * @param poOrdNum
//     * @return
//     */
//    private POTMsg getPo(String poOrdNum) {
//        POTMsg inPoTMsg = new POTMsg();
//        if (!ZYPCommonFunc.hasValue(poOrdNum) || inPoTMsg.getAttr("poOrdNum").getDigit() < poOrdNum.length()) {
//            return null;
//        }
//        inPoTMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//        inPoTMsg.poOrdNum.setValue(poOrdNum);
//        POTMsg result = (POTMsg) EZDTBLAccessor.findByKey(inPoTMsg);
//        return result;
//    }
//
//    /**
//     * getPoDtl
//     * @param poOrdNum
//     * @param poDrdDtlLineNum
//     * @return
//     */
//    private PO_DTLTMsg getPoDtl(String poOrdNum, String poDrdDtlLineNum) {
//        PO_DTLTMsg paramMsg = new PO_DTLTMsg();
//        paramMsg.glblCmpyCd.setValue(this.glblCmpyCd);
//        paramMsg.poOrdNum.setValue(poOrdNum);
//        paramMsg.poOrdDtlLineNum.setValue(poDrdDtlLineNum);
//        PO_DTLTMsg poDtlMsg = (PO_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(paramMsg);
//
//        return poDtlMsg;
//    }
    // End   2016/10/26 [QC#15538, DEL]

    /**
     * get VND_CD by poOrdNum
     * @param poOrdNum String
     * @return String
     */
    private String getVndCD(String poOrdNum) {
        POTMsg poTmsg = new POTMsg();

        ZYPEZDItemValueSetter.setValue(poTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poTmsg.poOrdNum, poOrdNum);
        poTmsg = (POTMsg) EZDFastTBLAccessor.findByKey(poTmsg);

        if (poTmsg == null) {
            return "";
        }
        return poTmsg.vndCd.getValue();
    }

    // START 2016/11/01 [QC#15681, ADD]
    /**
     * check ediPoNum -> get poNum
     * @param ediPoNum String
     * @return String
     */
    private String checkEdiPoNum(String ediPoNum) {
        POTMsg poTmsg = new POTMsg();
        String poNum = ediPoNum;
        
        int length = poTmsg.getAttr("poOrdNum").getDigit();
        if (ediPoNum != null && length < ediPoNum.length()) {
            poNum = ediPoNum.substring(0, length);
        }
        return poNum;
    }
    // END   2016/11/01 [QC#15681, ADD]

    // START 2016/10/14 [QC#14801,ADD]
    /**
     * getTargetNfbi0250_18
     * @param rs ResultSet
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal getSumFreightAmt(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceId", rs.getString("INTERFACE_ID"));
        params.put("trxId", rs.getString("TRANSACTION_ID"));
        params.put("invNum", rs.getString("EDI_INTFC_INV_NUM"));
        params.put("lineTpCd", EDI_INTFC_ITEM_LINE_TP_CD_FREIGHT);

        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getSumFreightAmt", params);

        if (resultMap == null) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) resultMap.get("EDI_INTFC_INV_PRC_TXT");
    }
    // END 2016/10/14 [QC#14801,ADD]

    // START 2016/10/19  [QC#15331,ADD]
    /**
     * getSubString
     * @param str ReStringsultSet
     * @param strNum int
     * @return String
     */
    private String getSubString(String str , int strNum){

        String returnStr = str;
        if (ZYPCommonFunc.hasValue(str) && str.length() > strNum) {
            returnStr = str.substring(str.length() - strNum);
        }
        return returnStr;
    }
    // END 2016/10/19 [QC#15331,ADD]

    // START 2016/11/07 E.Kameishi [QC#15790,ADD]
    private String getForwardSubString(String str, int strNum) {

        String returnStr = str;
        if (ZYPCommonFunc.hasValue(str) && str.length() > strNum) {
            returnStr = str.substring(0, strNum);
        }
        return returnStr;
    }
    // END 2016/11/07 E.Kameishi [QC#15790,ADD]
    /**
     * get bigDecimal Amt
     * @param poOrdNum String
     * @return String
     */
    private BigDecimal getAmt(String strAmt) {

        BigDecimal returnAmt = null;
        if (ZYPCommonFunc.hasValue(strAmt)) {
            try {
                // returnAmt = new BigDecimal(strAmt); // 2016/11/08 [QC#15881, MOD]
                returnAmt = new BigDecimal(strAmt.trim()); // 2016/11/08 [QC#15881, MOD]
            } catch (NumberFormatException e) {
                return BigDecimal.ZERO;
            }
        }
        return returnAmt;
    }

    /**
     * editItemErrorMailMessage
     * @param invNum
     */
    private void editItemErrorMailMessage(String invNum) {
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.leftPad("", STRING_LENGTH_100, MSG_TXT_HYPHEN));
        this.mailBusinessErrorMessage.append(ERR_MSG_CRLF);
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_4, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.rightPad(this.interfaceId, STRING_LENGTH_30, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_2, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.rightPad(MSG_TXT_SPACE, STRING_LENGTH_30, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_2, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.rightPad(invNum, STRING_LENGTH_15, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_2, MSG_TXT_SPACE));
        this.mailBusinessErrorMessage.append(ERR_MSG_ITEM);
        this.mailBusinessErrorMessage.append(ERR_MSG_CRLF);
        this.mailBusinessErrorMessage.append(this.mailBusinessErrorMessageDetail);
        this.mailBusinessErrorMessage.append(ERR_MSG_CRLF);
    }

    /**
     * editErrorDetailMailMessage
     * @param message
     */
    private void editErrorDetailMailMessage(String message) {
        S21InfoLogOutput.println(message);
        mailBusinessErrorMessageDetail.append(message);
        mailBusinessErrorMessageDetail.append(ERR_MSG_CRLF);
    }

    /**
     * Send Error Mail
     */
    private void sendMail() {

        // Get From Mail Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        if (from == null) {
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
            if (!addrFromList.isEmpty()) {
                from = addrFromList.get(0);
            }
        }

        // Get To Mail Address
        if (addrToList == null) {
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
            groupTo.setMailKey1("01");
            groupTo.setMailKey2(MAIL_KEY_TO);
            addrToList = groupTo.getMailAddress();
            if (addrToList.isEmpty()) {
                throw new S21AbendException(NFBM0217E, new String[] {MAIL_TYPE_TO, MAIL_GROUP_ID_TO, });
            }
        }

        // Get CC Mail Address
        if (addrCcList == null) {
            S21MailGroup groupCc = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_CC);
            groupCc.setMailKey1("01");
            groupCc.setMailKey2(MAIL_KEY_CC);
            addrCcList = groupCc.getMailAddress();
            if (addrCcList.isEmpty()) {
                throw new S21AbendException(NFBM0217E, new String[] {MAIL_TYPE_CC, MAIL_GROUP_ID_CC });
            }
        }

        // Get Template
        if (template == null) {
            template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_M001);
            if (!ZYPCommonFunc.hasValue(template.getBody())) {
                throw new S21AbendException(NFBM0184E, new String[] {MAIL_TEMPLATE_ID_M001 });
            }
        }

        String inFormat = template.getDefaultDateFormat() + MAIL_TS_FMT.substring(MAIL_LEN_FMT_SUBSTR);
        String currentTs = ZYPDateUtil.getCurrentSystemTime(inFormat);
        ZYPLocalTimeData locTmDatlocal = ZYPLocalTimeUtil.convertTimeSys2Biz(currentTs, inFormat);
        currentTs = locTmDatlocal.getTime();
        template.setTemplateParameter(MAIL_FIELD_TIMESTAMP, currentTs);
        template.setTemplateParameter(MAIL_FIELD_MESSAGE, this.mailBusinessErrorMessage.toString());

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setCcAddressList(addrCcList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

 // START 2016/11/11 [QC#15809, ADD]
    /**
     * Duplicate check by InvNum
     * @return boolean : ERROR - false
     */
    private boolean duplicateCheckByInvNum(String vndInvNum) {
        
        VND_INV_WRKTMsg vndInvWrkTmsg = new VND_INV_WRKTMsg();
        vndInvWrkTmsg.setSQLID("002");
        vndInvWrkTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        vndInvWrkTmsg.setConditionValue("vndInvNum01", vndInvNum);
        VND_INV_WRKTMsgArray wrkTmsgArray = (VND_INV_WRKTMsgArray) EZDTBLAccessor.findByCondition(vndInvWrkTmsg);
        
        if (wrkTmsgArray != null && wrkTmsgArray.getValidCount() > 0) {
            return false;
        }
        return true;
    }
    /**
     * Duplicate check by InvNum In Transaction
     * @return count Target
     * @throws int 
     */
    private int countTargetByInv(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceId", rs.getString("INTERFACE_ID"));
        params.put("trxId", rs.getString("TRANSACTION_ID"));
        params.put("invNum", rs.getString("EDI_INTFC_INV_NUM"));

        BigDecimal targetCnt =  (BigDecimal) ssmBatchClient.queryObject("countTargetByInv", params);

        return targetCnt.intValue();
    }

    /**
     * Duplicate check by InvNum In Transaction
     * @return boolean : ERROR - false
     * @throws Map<String, Object> 
     */
    private  List<Map<String, Object>> getTargetI08(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceId", rs.getString("INTERFACE_ID"));
        params.put("trxId", rs.getString("TRANSACTION_ID"));
        params.put("invNum", rs.getString("EDI_INTFC_INV_NUM"));

        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTargetI08", params);

        return resultMapList;
    }
    // END   2016/11/11 [QC#15809, ADD]
    
}
