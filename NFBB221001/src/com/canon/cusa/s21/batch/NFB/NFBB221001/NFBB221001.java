/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB221001;

import static com.canon.cusa.s21.batch.NFB.NFBB221001.constant.NFBB221001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.NFBI0430_03TMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
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
 * Invoice Import For EDI Vendor(TST/Dietzgen)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/06   CITS            K.Kameoka       Create
 * 11/12/2018   CITS            M.Naito         Update          QC#29050
 * 11/19/2018   CITS            M.Naito         Update          QC#29047
 * 12/21/2018   CITS            M.Naito         Update          QC#29722
 * 2022/11/24   Hitachi         S.Nakatani      Update          QC#60402
 * 2023/01/05   Hitachi         S.Nakatani      Update          QC#60248
 * 2023/03/13   Hitachi         E.Watabe        Update          QC#61128
 * 2023/09/25/  Hitachi         TZ.Win          Update          QC#61608
 *</pre>
 */
public class NFBB221001 extends S21BatchMain {

    /** Mail Business Error Message */
     private StringBuilder mailBusinessErrorMessage;

    /** Mail Business Error Message Detail */
     private StringBuilder mailBusinessErrorMessageDetail;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Currency Code */
    private String ccyCd = null;

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

    /** Vendor Code */
    private String vndCd;

    @Override
    protected void initRoutine() {
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NFBM0199E);
        }

        this.ccyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_CCY, this.glblCmpyCd);

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
        
        // get Vendor Code
        Map<String, Object> ssmParamVndCd = new HashMap<String, Object>();
        ssmParamVndCd.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        if (KEY_INTERFACE_ID_TST.equals(this.interfaceId)) {
            ssmParamVndCd.put(KEY_VND_SYS_TP_CD, VND_SYS_TP.TST_IMPRESO);
        }
        if (KEY_INTERFACE_ID_DIETZGEN.equals(this.interfaceId)) {
            ssmParamVndCd.put(KEY_VND_SYS_TP_CD, VND_SYS_TP.DIETZGEN);
        }
        // START 2023/03/13 E.Watabe [QC#61128,ADD]
        if (KEY_INTERFACE_ID_HYTEC.equals(this.interfaceId)) {
            ssmParamVndCd.put(KEY_VND_SYS_TP_CD, VND_SYS_TP.HYTEC);
        }
        // END 2023/03/13 E.Watabe [QC#61128,ADD]
        // START 2023/09/25 TZ.Win [QC#61608,ADD]
        if (KEY_INTERFACE_ID_ATRIX.equals(this.interfaceId)) {
            ssmParamVndCd.put(KEY_VND_SYS_TP_CD, VND_SYS_TP.ATRIX);
        }
        // END 2023/09/25 TZ.Win [QC#61608,ADD]


        
        this.vndCd = (String) ssmBatchClient.queryObject("getVndCd", ssmParamVndCd);
        
        if (!ZYPCommonFunc.hasValue(this.vndCd)) {
            throw new S21AbendException(NPAM1537E, new String[] {"VND_CD", "RCV_ASN_VND", "GLBL_CMPY_CD = " + this.glblCmpyCd + COMMA + "VND_SYS_TP_CD = " + VND_SYS_TP.TST_IMPRESO + " Or " + VND_SYS_TP.DIETZGEN});
        }
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            
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
        new NFBB221001().executeBatch(NFBB221001.class.getSimpleName());
    }

    /**
     * dataImport(The main processing)
     * @throws SQLException
     */
    private void dataImport(ResultSet rs) throws SQLException {

        List<VND_INV_LIC_ACCS_WRKTMsg> invLicTmsgList = new ArrayList<VND_INV_LIC_ACCS_WRKTMsg>();
        List<VND_INV_LINE_WRKTMsg> invLineTmsgList = new ArrayList<VND_INV_LINE_WRKTMsg>();

        String vndInvNum = getForwardSubString(rs.getString("INBD_INV_NUM"), STRING_LENGTH_15);
        int targetCnt = countTargetByInv(rs);
        if (targetCnt > 1) {
            String message = S21MessageFunc.clspGetMessage(NFBM0181E, new String[] {"vndInvNum : " + vndInvNum });
            editErrorDetailMailMessage(message);

        } else if (!duplicateCheckByInvNum(vndInvNum)) {
          String message = S21MessageFunc.clspGetMessage(NFBM0181E, new String[] {"vndInvNum : " + vndInvNum });
          editErrorDetailMailMessage(message);
        } else {
            List<Map<String, Object>> invAmtList = getInvAmt(rs);
            
            List<Map<String, Object>> prntVndTermList = getPrntVndTerm(rs);

            //Insert VND_INV_WRK
            VND_INV_WRKTMsg vndInvWrk = getInsertInvMsg(rs, invAmtList, prntVndTermList);

            //Insert VND_INV_DISC_TERM_WRK
//            VND_INV_DISC_TERM_WRKTMsg vndInvDiscTermTmsg = getInsertDiscTermMsg(rs);

            //Insert VND_INV_BOL_WRK
            VND_INV_BOL_WRKTMsg vndInvBolTmsg = getInsertBolMsg(rs, invAmtList);

            // Get [NFBI0430_03] Data
            List<NFBI0430_03TMsg> i0430_03TMsgList = getTargetNfbi0430_03(rs);

            // START 2018/12/21 M.Naito [QC#29722,DEL]
//            int lineNum = 1;
//            BigDecimal unitId = BigDecimal.ZERO;
//            BigDecimal unitIdPre = BigDecimal.ZERO;
//            String ediPoLineNum = null;
            // END 2018/12/21 M.Naito [QC#29722,DEL]
            String poNum = null;

            // START 2022/11/24 S.Nakatani [QC#60402,ADD]
            int maxInvLineNum = 0;
            for (NFBI0430_03TMsg i0430_03Msg : i0430_03TMsgList) {
                if (maxInvLineNum < Integer.parseInt(i0430_03Msg.invLineNum.getValue()))
                    maxInvLineNum = Integer.parseInt(i0430_03Msg.invLineNum.getValue());
            }
            // END 2022/11/24 S.Nakatani [QC#60402,ADD]

            // insert VND_INV_LINE_WRK, VND_INV_LIC_ACCS_WRK
            for (NFBI0430_03TMsg i0430_03Msg : i0430_03TMsgList) {

                // START 2018/12/21 M.Naito [QC#29722,DEL]
//                unitId = i0430_03Msg.unitId.getValue();
//                if (ZYPCommonFunc.hasValue(unitIdPre)) {
//                    if (unitIdPre.equals(unitIdPre)) {
//                        lineNum++;
//                    } else {
//                        lineNum = 1;
//                        unitIdPre = unitId;
//                    }
//                }
                // END 2018/12/21 M.Naito [QC#29722,DEL]

                // START 2022/11/24 S.Nakatani [QC#60402,ADD]
                for (VND_INV_LINE_WRKTMsg invLineTmsg : invLineTmsgList) {
                    if (invLineTmsg.vndInvLineNum.getValue().equals(i0430_03Msg.invLineNum.getValue()))
                        ZYPEZDItemValueSetter.setValue(i0430_03Msg.invLineNum, String.format("%5s", ++maxInvLineNum).replace(" ", "0"));
                }
                // END 2022/11/24 S.Nakatani [QC#60402,ADD]
                
                // get ordQty from PO_DTL
                poNum = checkEdiPoNum(rs.getString("CUST_PO_NUM"));

                VND_INV_LINE_WRKTMsg vndInvLineWrk = getInsertLineMsg(i0430_03Msg, vndInvNum, poNum);
                invLineTmsgList.add(vndInvLineWrk);

//                VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk = getInsertLicMsg(lineNum, vndInvNum);
//                invLicTmsgList.add(vndInvLicAccsWrk);

            }

            EZDFastTBLAccessor.insert(vndInvWrk);
            if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(vndInvWrk.getReturnCode())) {
                String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {vndInvWrk.getTableName(), vndInvWrk.getReturnCode() });
                editErrorDetailMailMessage(message);
            }
            EZDFastTBLAccessor.insert(vndInvBolTmsg);
            if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(vndInvBolTmsg.getReturnCode())) {
                String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {vndInvBolTmsg.getTableName(), vndInvBolTmsg.getReturnCode() });
                editErrorDetailMailMessage(message);
            }
//            EZDFastTBLAccessor.insert(vndInvDiscTermTmsg);
//            if (!EZDFastTBLAccessor.RTNCD_NORMAL.equals(vndInvDiscTermTmsg.getReturnCode())) {
//                String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {vndInvDiscTermTmsg.getTableName(), vndInvDiscTermTmsg.getReturnCode() });
//                editErrorDetailMailMessage(message);
//            }

            if (invLineTmsgList.size() > 0) {
                int rtn = EZDFastTBLAccessor.insert(invLineTmsgList.toArray(new VND_INV_LINE_WRKTMsg[invLineTmsgList.size()]));
                if (rtn != invLineTmsgList.size()) {
                    String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {invLineTmsgList.get(0).getTableName(), Integer.toString(rtn) });
                    editErrorDetailMailMessage(message);
                }
//                rtn = EZDFastTBLAccessor.insert(invLicTmsgList.toArray(new VND_INV_LIC_ACCS_WRKTMsg[invLicTmsgList.size()]));
//                if (rtn != invLicTmsgList.size()) {
//                    String message = S21MessageFunc.clspGetMessage(NFBM0073E, new String[] {invLicTmsgList.get(0).getTableName(), Integer.toString(rtn) });
//                    editErrorDetailMailMessage(message);
//                }
            }
        }

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
        commit();
    }

    /**
     * getTargetNfbi0250_18
     * @param rs ResultSet
     * @return List<NFBI0250_18TMsg>
     * @throws SQLException
     */
    private List<NFBI0430_03TMsg> getTargetNfbi0430_03(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceId", rs.getString("INTERFACE_ID"));
        params.put("trxId", rs.getString("TRANSACTION_ID"));
        params.put("unitId", rs.getString("UNIT_ID"));
        // START 2018/12/21 M.Naito [QC#29722,ADD]
        params.put("segmentId", rs.getString("SEGMENT_ID"));
        // END 2018/12/21 M.Naito [QC#29722,ADD]
//        params.put("lineTpCd", EDI_INTFC_ITEM_LINE_TP_CD_FREIGHT);

        List<NFBI0430_03TMsg> msgList = (List<NFBI0430_03TMsg>) ssmBatchClient.queryObjectList("getTargetNfbi0430_03", params);
        return msgList;
    }


    /**
     * get new VND_INV_TMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private VND_INV_WRKTMsg getInsertInvMsg(ResultSet rs, List<Map<String, Object>> invAmtList,  List<Map<String, Object>> vndInfoList) throws SQLException { 
        VND_INV_WRKTMsg getMsg = new VND_INV_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(rs.getString("INBD_INV_NUM"), STRING_LENGTH_15));
        ZYPEZDItemValueSetter.setValue(getMsg.origVndInvNum, getForwardSubString(rs.getString("INBD_INV_NUM"),STRING_LENGTH_15)); 
        ZYPEZDItemValueSetter.setValue(getMsg.invDt, rs.getString("INV_DT"));
        String poNum = checkEdiPoNum(rs.getString("CUST_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(getMsg.vndCd, getVndCD(poNum));
        ZYPEZDItemValueSetter.setValue(getMsg.invTpCd, INV_TP.INVOICE);
        String prchGrpCd = getPrchGrpCd(poNum);
        
        //get Address from VAR_CHAR_CONST
        if(PRCH_GRP_CD_ESS.equals(prchGrpCd)){
            ZYPEZDItemValueSetter.setValue(getMsg.ofcNm, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_NM_ESS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcFirstLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR1_ESS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcScdLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR2_ESS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcThirdLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR3_ESS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcFrthLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR4_ESS, this.glblCmpyCd));
        }else{
            ZYPEZDItemValueSetter.setValue(getMsg.ofcNm, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_NM_LFS_PPS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcFirstLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR1_LFS_PPS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcScdLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR2_LFS_PPS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcThirdLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR3_LFS_PPS, this.glblCmpyCd));
            ZYPEZDItemValueSetter.setValue(getMsg.ofcFrthLineAddr, ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR4_LFS_PPS, this.glblCmpyCd));
        }
        if (vndInfoList != null && vndInfoList.size() > 0) {
            Map<String, Object> vndInfo = vndInfoList.get(0);
             ZYPEZDItemValueSetter.setValue(getMsg.vndPmtTermCd, (String) vndInfo.get("VND_PMT_TERM_CD"));
            ZYPEZDItemValueSetter.setValue(getMsg.vndPmtTermNm, (String) vndInfo.get("VND_PMT_TERM_DESC_TXT"));
        }
        //get Value from NFBI0430_04
        if (invAmtList != null && invAmtList.size() > 0) {
            Map<String, Object> invAmt = invAmtList.get(0);
            ZYPEZDItemValueSetter.setValue(getMsg.invTotDealNetAmt, (BigDecimal) invAmt.get("TOT_INV_AMT"));
            ZYPEZDItemValueSetter.setValue(getMsg.invTotDealFrtAmt, (BigDecimal) invAmt.get("INV_FRT_AMT"));
            ZYPEZDItemValueSetter.setValue(getMsg.invTotDealTaxAmt, (BigDecimal) invAmt.get("INV_SAL_TAX_AMT"));
            // START 2023/01/05 S.Nakatani [QC#60248,ADD]
            ZYPEZDItemValueSetter.setValue(getMsg.invTotDealHdlgCostAmt, (BigDecimal) invAmt.get("INV_HAND_AMT"));
            // END 2023/01/05 S.Nakatani [QC#60248,ADD]
        }
        //Constant Value
        ZYPEZDItemValueSetter.setValue(getMsg.dealCcyCd, this.ccyCd); 
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvProcStsCd, VND_INV_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(getMsg.itrlIntfcId, rs.getString("INTERFACE_ID"));
        ZYPEZDItemValueSetter.setValue(getMsg.cmProcStsCd, CM_PROC_STS_CD_N);
        ZYPEZDItemValueSetter.setValue(getMsg.psIssRqstFlg, PS_ISS_RQST_FLG);

        return getMsg;
    }

    /**
     * get new VND_INV_BOL_WRKTMsg
     * @param rs ResultSet
     * @throws SQLException
     */
    private VND_INV_BOL_WRKTMsg getInsertBolMsg(ResultSet rs, List<Map<String, Object>> invAmtList) throws SQLException { 
        VND_INV_BOL_WRKTMsg getMsg = new VND_INV_BOL_WRKTMsg();

        //set from NFBI0430_01
        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, getForwardSubString(rs.getString("INBD_INV_NUM"),STRING_LENGTH_15));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
//        ZYPEZDItemValueSetter.setValue(getMsg.asnSoNum, rs.getString("BOL_NUM"));
//        ZYPEZDItemValueSetter.setValue(getMsg.bolNum, rs.getString("BOL_NUM"));
//        ZYPEZDItemValueSetter.setValue(getMsg.proNum, rs.getString("BOL_NUM"));
        ZYPEZDItemValueSetter.setValue(getMsg.shipToCustCd, rs.getString("CUST_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(getMsg.ediPoOrdNum, rs.getString("CUST_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(getMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);

        //set from NFBI0430_04
        if (invAmtList != null && invAmtList.size() > 0) {
            Map<String, Object> invAmt = invAmtList.get(0);
//            ZYPEZDItemValueSetter.setValue(getMsg.carrCd, rs.getString("CARR_VND_CD"));
            ZYPEZDItemValueSetter.setValue(getMsg.shipDealNetAmt, (BigDecimal) invAmt.get("TOT_INV_AMT"));
            ZYPEZDItemValueSetter.setValue(getMsg.shipDealFrtAmt, (BigDecimal) invAmt.get("INV_FRT_AMT"));
        }        
        return getMsg;
    }

    /**
     * get new VND_INV_LINE_WRKTMsg
     * @param i0250_18Msg NFBI0250_18TMsg
     * @param lineNum int
     * @return VND_INV_LINE_WRKTMsg
     * @throws SQLException
     */
    private VND_INV_LINE_WRKTMsg getInsertLineMsg(NFBI0430_03TMsg i0430_03Msg, String vndInvNum, String poOrdNum) throws SQLException {
        VND_INV_LINE_WRKTMsg getMsg = new VND_INV_LINE_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, vndInvNum);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
//        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum), STRING_LENGTH_5, "0"));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum, i0430_03Msg.invLineNum);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubTrxNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd, i0430_03Msg.trdPtnrSkuCd);
        ZYPEZDItemValueSetter.setValue(getMsg.mdseCd, i0430_03Msg.cusaMdseCd);
        // START 2018/11/19 M.Naito [QC#29047,MOD]
        String vndMdseCd = getSplyItemNumFromAsl(i0430_03Msg.cusaMdseCd.getValue());
        if (ZYPCommonFunc.hasValue(vndMdseCd)) {
            ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd, vndMdseCd);
        } else {
            vndMdseCd = getSplyItemNumFromAsl(i0430_03Msg.trdPtnrSkuCd.getValue());
            if (ZYPCommonFunc.hasValue(vndMdseCd)) {
                ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd, vndMdseCd);
                ZYPEZDItemValueSetter.setValue(getMsg.mdseCd, i0430_03Msg.trdPtnrSkuCd);
            }
        }
        // END 2018/11/19 M.Naito [QC#29047,MOD]
        // START 2018/11/12 M.Naito [QC#29050,MOD]
//        ZYPEZDItemValueSetter.setValue(getMsg.shipQty, i0430_03Msg.invQty.getValue());
        // END 2018/11/12 M.Naito [QC#29050,MOD]

        String ediPoOrdDtlLineNum = ZYPCommonFunc.leftPad(i0430_03Msg.ediPoLineNum.getValue(), this.ediLineNumLength, "0");
        ediPoOrdDtlLineNum = getSubString(ediPoOrdDtlLineNum, this.ediLineNumLength);
        String poOrdDtlLineNum = ZYPCommonFunc.leftPad(ediPoOrdDtlLineNum, this.poLineNumLength, "0");
        poOrdDtlLineNum = getSubString(poOrdDtlLineNum, this.poLineNumLength);

        ZYPEZDItemValueSetter.setValue(getMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(getMsg.ediPoOrdDtlLineNum, ediPoOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(getMsg.manPrcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.vndMdseIntgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.vndPoReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.vndShpgIntgOnlyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(getMsg.dealNetUnitPrcAmt, i0430_03Msg.unitPrcAmt.getValue());
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineDealNetAmt, i0430_03Msg.lineTotGrsAmt.getValue());
        ZYPEZDItemValueSetter.setValue(getMsg.dealGrsUnitPrcAmt, i0430_03Msg.unitPrcAmt.getValue());
        ZYPEZDItemValueSetter.setValue(getMsg.dealGrsTotPrcAmt, i0430_03Msg.lineTotGrsAmt.getValue());
        // START 2018/11/12 M.Naito [QC#29050,MOD]
//      ZYPEZDItemValueSetter.setValue(getMsg.uomCd, i0430_03Msg.uomCd.getValue());
        BigDecimal invQty = i0430_03Msg.invQty.getValue();
        if (ZYPCommonFunc.hasValue(i0430_03Msg.uomCd.getValue())) {
            String uomCd = getUomCdFromPo(poOrdNum, poOrdDtlLineNum);
            String vndUomCd = i0430_03Msg.uomCd.getValue();
            if (KEY_INTERFACE_ID_TST.equals(this.interfaceId)) {
                vndUomCd = getVndUomCd(vndUomCd, uomCd);
                if (ZYPCommonFunc.hasValue(uomCd) && ZYPCommonFunc.hasValue(vndUomCd) && !uomCd.equals(vndUomCd)) {
                    BigDecimal inEachQty = getInEachQty(uomCd, vndUomCd, i0430_03Msg.cusaMdseCd.getValue());
                    if (ZYPCommonFunc.hasValue(invQty) && ZYPCommonFunc.hasValue(inEachQty) && !BigDecimal.ZERO.equals(inEachQty)) {
                        invQty = inEachQty.multiply(invQty);
                        vndUomCd = uomCd;
                    }
                }
            }
            ZYPEZDItemValueSetter.setValue(getMsg.uomCd, vndUomCd);
        }
        ZYPEZDItemValueSetter.setValue(getMsg.shipQty, invQty);
        // END 2018/11/12 M.Naito [QC#29050,MOD]
        
        BigDecimal ordQty = getOrdQty(poOrdNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(getMsg.ordQty, ordQty);

        return getMsg;
    }

    /**
     * get new VND_INV_LIC_ACCS_WRKTMsg
     * @param i0250_18Msg NFBI0250_18TMsg
     * @param lineNum int
     * @return VND_INV_LIC_ACCS_WRKTMsg
     * @throws SQLException
     */
    private VND_INV_LIC_ACCS_WRKTMsg getInsertLicMsg(int lineNum, String vndInvNum) throws SQLException {
        VND_INV_LIC_ACCS_WRKTMsg getMsg = new VND_INV_LIC_ACCS_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(getMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvNum, vndInvNum);
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvBolLineNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum), STRING_LENGTH_5, "0"));
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubNum, "001");
        ZYPEZDItemValueSetter.setValue(getMsg.vndInvLineSubTrxNum, "001");
//        ZYPEZDItemValueSetter.setValue(getMsg.vndMdseCd, i0250_18Msg.ediIntfcItemCd);
//        ZYPEZDItemValueSetter.setValue(getMsg.mdseCd, i0250_18Msg.ediIntfcItemCd);
//        ZYPEZDItemValueSetter.setValue(getMsg.licAccsNum, i0250_18Msg.ediIntfcItemCd);

        return getMsg;
    }

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

    private String getForwardSubString(String str, int strNum) {

        String returnStr = str;
        if (ZYPCommonFunc.hasValue(str) && str.length() > strNum) {
            returnStr = str.substring(0, strNum);
        }
        return returnStr;
    }

    /**
     * get bigDecimal Amt
     * @param poOrdNum String
     * @return String
     */
    private BigDecimal getAmt(String strAmt) {

        BigDecimal returnAmt = null;
        if (ZYPCommonFunc.hasValue(strAmt)) {
            try {
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
        params.put("invNum", rs.getString("INBD_INV_NUM"));

        BigDecimal targetCnt =  (BigDecimal) ssmBatchClient.queryObject("countTargetByInv", params);

        return targetCnt.intValue();
    }

    /**
     * get VND_CD by poOrdNum
     * @param poOrdNum String
     * @return String
     */
    private String getPrchGrpCd(String poOrdNum) {
        POTMsg poTmsg = new POTMsg();

        ZYPEZDItemValueSetter.setValue(poTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poTmsg.poOrdNum, poOrdNum);
        poTmsg = (POTMsg) EZDFastTBLAccessor.findByKey(poTmsg);

        if (poTmsg == null) {
            return "";
        }
        return poTmsg.prchGrpCd.getValue();
    }
    
    /**
     * Duplicate check by InvNum In Transaction
     * @return boolean : ERROR - false
     * @throws Map<String, Object> 
     */
    private  List<Map<String, Object>> getInvAmt(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("interfaceId", rs.getString("INTERFACE_ID"));
        params.put("trxId", rs.getString("TRANSACTION_ID"));
        params.put("unitId", rs.getString("UNIT_ID"));
        // START 2018/12/21 M.Naito [QC#29722,ADD]
        params.put("segmentId", rs.getString("SEGMENT_ID"));
        // END 2018/12/21 M.Naito [QC#29722,ADD]

        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInvAmt", params);

        return resultMapList;
    }
    
    /**
     * get PRNT_VND by vndCd
     * @return boolean : ERROR - false
     * @throws Map<String, Object> 
     */
    private  List<Map<String, Object>> getPrntVndTerm(ResultSet rs) throws SQLException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", this.glblCmpyCd);
        params.put("vndCd", this.vndCd);

        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrntVndTerm", params);

        return resultMapList;
    }
    
    /**
     * get PO_DTL 
     * @param poOrdNum String
     * @return String
     */
    private BigDecimal getOrdQty(String poOrdNum, String ediPoLineNum) {
        PO_DTLTMsg poDtlTmsg = new PO_DTLTMsg();

        ZYPEZDItemValueSetter.setValue(poDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdDtlLineNum, ediPoLineNum);
        poDtlTmsg = (PO_DTLTMsg) EZDFastTBLAccessor.findByKey(poDtlTmsg);

        if (poDtlTmsg == null) {
            return BigDecimal.ZERO;
        }
        return poDtlTmsg.poQty.getValue();
    }

    // START 2018/11/12 M.Naito [QC#29050,ADD]
    /**
     * getVndUomCd
     * @param vndUomCd String
     * @param uomCd String
     * @return String
     */
    private String getVndUomCd(String trdPtnrUomCd, String uomCd) {

        if (!ZYPCommonFunc.hasValue(uomCd)) {
            return trdPtnrUomCd;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("vndCd", this.vndCd);
        ssmParam.put("trdPtnrUomCd", trdPtnrUomCd);
        ssmParam.put("pkgUomCd", uomCd);
        String vndUomCd = (String) ssmBatchClient.queryObject("getVndUom", ssmParam);
        if (ZYPCommonFunc.hasValue(vndUomCd)) {
            return vndUomCd;
        }
        return trdPtnrUomCd;
    }

    /**
     * getUomCdFromPo
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return String
     */
    private String getUomCdFromPo(String poOrdNum, String poOrdDtlLineNum) {

        PO_DTLTMsg poDtlTmsg = new PO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdDtlLineNum, poOrdDtlLineNum);
        poDtlTmsg = (PO_DTLTMsg) EZDFastTBLAccessor.findByKey(poDtlTmsg);

        String poUomCd = null;
        if (poDtlTmsg != null) {
            poUomCd = poDtlTmsg.poDispUomCd.getValue();
        }
        return poUomCd;
    }

    /**
     * getInEachQty
     * @param uomCd String
     * @param vndUomCd String
     * @param mdseCd String
     * @return BigDecimal
     */
    private BigDecimal getInEachQty(String uomCd, String vndUomCd, String mdseCd) {

        BigDecimal inEachQty = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("basePkgUomCd", uomCd);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);
        BigDecimal targetCnt = (BigDecimal) ssmBatchClient.queryObject("getMdseStorePkg", ssmParam);
        if (BigDecimal.ZERO.equals(targetCnt)) {
            return inEachQty;
        }

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("pkgUomCd01", vndUomCd);
        tMsg.setConditionValue("mdseCd01", mdseCd);
        MDSE_STORE_PKGTMsgArray tMsgArray = (MDSE_STORE_PKGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArray != null && tMsgArray.getValidCount() > 0 && ZYPCommonFunc.hasValue(tMsgArray.no(0).inEachQty.getValue())) {
            inEachQty = tMsgArray.no(0).inEachQty.getValue();
        }
        return inEachQty;
    }

    // START 2018/11/19 M.Naito [QC#29047,MOD]
    /**
     * getSplyItemNumFromAsl
     * @param mdseCd String
     * @return String
     */
    private String getSplyItemNumFromAsl(String mdseCd) {
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(KEY_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put("vndCd", this.vndCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("batDt", ZYPDateUtil.getBatProcDate(this.glblCmpyCd));
        return (String) ssmBatchClient.queryObject("getSplyItemNumFromAsl", ssmParam);
    }
    // END 2018/11/19 M.Naito [QC#29047,MOD]
    // END 2018/11/12 M.Naito [QC#29050,ADD]
}
