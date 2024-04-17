/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB302001;

import static com.canon.cusa.s21.batch.NLA.NLAB302001.constant.NLAB302001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.VND_INV_BOL_WRKTMsg;
import business.db.VND_INV_BOL_WRKTMsgArray;
import business.db.VND_INV_DISC_TERM_WRKTMsg;
import business.db.VND_INV_DISC_TERM_WRKTMsgArray;
import business.db.VND_INV_LIC_ACCS_WRKTMsg;
import business.db.VND_INV_LIC_ACCS_WRKTMsgArray;
import business.db.VND_INV_LINE_WRKTMsg;
import business.db.VND_INV_LINE_WRKTMsgArray;
import business.db.VND_INV_WRKTMsg;
import business.db.VND_INV_WRKTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LAK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Receive Invoice Information for CUSA Domestic Batch 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/26/2013   Hitachi         K.Kishimoto     Create          QC1388
 * 03/25/2014   Hitachi         K.Kishimoto     Update          CSA-028
 * 03/14/2016   CITS            R.Shimamoto     Update          V2.0
 * 04/13/2016   CITS            T.Hakodate      Update          QC#6997 
 * 04/18/2016   CITS            T.Hakodate      Update          QC#7169
 * 08/22/2016   CITS            T.Wada          Update          QC#8660
 * 09/16/2016   CITS            R.Shimamoto     Update          QC#14485
 * 09/23/2016   CSAI            Y.Imazu         Update          QC#14782
 * 09/27/2016   CITS            R.Shimamoto     Update          QC#14714
 * 01/10/2020   Fujitsu         M.Ishii         Update          QC#55137
 *</pre>
 */
public class NLAB302001 extends S21BatchMain {

    /** Mail System Error Message */
    private StringBuilder mailSystemErrorMessage;

    /** Mail System Error Message Detail */
    private StringBuilder mailSystemErrorMessageDetail;

    /** Interface ID */
    private String interfaceId = null;

    /** SSM LowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** CUSA Global Company Code */
    private String cusaGlblCmpyCd = null;

    /** CUSA EDI Trading Partner Code */
    private String cusaEdiTradingPartnerCd = null;

    /** CUSA Excluded System Source Code */
    private String cusaExcludedSystemSourceCd = null;

    /** CUSA Parts EDI Trading Partner Code */
    private String cusaPartsEdiTradingPartnerCd = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** success count */
    private int successCount = 0;

    /** error count */
    private int errorCount = 0;

    /** Sales Date */
    private String slsDt;

    /** Error Flag : Whether error occurred or not in process */
    boolean allErrorFlg = false;

    @Override
    protected void initRoutine() {

        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        glblCmpyCd = prof.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_NLAM1118E, new String[] {MSG_TXT_GLBL_CMPY_CD });
        }

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_GLBL_CMPY_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {
            throw new S21AbendException(MSG_ID_NLAM1118E, new String[] {MSG_TXT_CUSA_GLBL_CMPY_CD });
        }

        // get SalesDate
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // get InterfaceID
        this.interfaceId = getInterfaceID();

        if (!ZYPCommonFunc.hasValue(this.interfaceId)) {
            // INTERFACE ID is no data
            throw new S21AbendException(NPAM1168E);
        }

        mailSystemErrorMessage = new StringBuilder();
        mailSystemErrorMessageDetail = new StringBuilder();
    }

    @Override
    protected void mainRoutine() {

        if (INTERFACE_ID_CUSA_WS.equals(this.interfaceId)) {

            importWsInv();

        } else if (INTERFACE_ID_CUSA_PARTS.equals(this.interfaceId)) {

            importPartsInv();

        }

    }

    @Override
    protected void termRoutine() {

        if (mailSystemErrorMessage.length() > 0) {
            sendSystemErrorMail();
            termCd = TERM_CD.WARNING_END;
        }

        setTermState(this.termCd, this.successCount, this.errorCount);

    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialize S21BatchMain
        new NLAB302001().executeBatch(NLAB302001.class.getSimpleName());
    }

    /**
     * Import Invoice for CUSA WS
     */
    private void importWsInv() {

        // Get CUSA EDI Trading Partner Code
        cusaEdiTradingPartnerCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_EDI_TRD_PTNR_CD, glblCmpyCd);
        if (cusaEdiTradingPartnerCd == null || cusaEdiTradingPartnerCd.equals(BLANK)) {
            // CUSA EDI Trading Partner Code is no data
            throw new S21AbendException(MSG_ID_NLAM1118E, new String[] {MSG_TXT_CUSA_EDI_TRADING_PARTNER_CD });
        }
        ArrayList<String> cusaEdiTrdPtnrCdList = new ArrayList<String>();
        String[] arrayEdiTrdPtnrCd = cusaEdiTradingPartnerCd.split(",", 0);
        for (int i = 0; i < arrayEdiTrdPtnrCd.length; i++) {
            cusaEdiTrdPtnrCdList.add(arrayEdiTrdPtnrCd[i]);
        }

        // Get CUSA Excluded System Source Code
        cusaExcludedSystemSourceCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_EXCL_SYS_SRC_CD, glblCmpyCd);
        if (cusaExcludedSystemSourceCd == null || cusaExcludedSystemSourceCd.equals(BLANK)) {
            // CUSA Excluded System Source Code is no data
            throw new S21AbendException(MSG_ID_NLAM1118E, new String[] {MSG_TXT_CUSA_EXCLUDED_SYSTEM_SOURCE_CD });
        }
        ArrayList<String> cusaExcludedSystemSourceCdList = new ArrayList<String>();
        String[] arrayExcludedSystemSourceCd = cusaExcludedSystemSourceCd.split(",", 0);
        for (int i = 0; i < arrayExcludedSystemSourceCd.length; i++) {
            cusaExcludedSystemSourceCdList.add(arrayExcludedSystemSourceCd[i]);
        }

        // Get VND_INV_WRK
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpCd", this.glblCmpyCd);
            queryParam.put("cusaGlblCmpCd", this.cusaGlblCmpyCd);
            queryParam.put("ediTrdPtnrCd", cusaEdiTrdPtnrCdList);
            queryParam.put("crsRefActvFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("salesDate", this.slsDt);
            queryParam.put("sysSrcCd", cusaExcludedSystemSourceCdList);
            queryParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("sendInvViaGrpFlg", ZYPConstant.FLG_ON_Y);
            queryParam.put("flPlnFlgN", ZYPConstant.FLG_OFF_N);
            queryParam.put("flPlnFlgY", ZYPConstant.FLG_ON_Y);
            queryParam.put("cpoSrcTpCdCreditRE", CPO_SRC_TP.CREDIT_AND_REBILL_ENTRY);
            queryParam.put("cpoSrcTpCdCreditR", BILL_TP.CREDIT_AND_REBILL);
            queryParam.put("cpoSrcTpCdPairOE", CPO_SRC_TP.PAIR_ORDER_ENTRY);
            queryParam.put("invTpCdCreditMemo", INV_TP.CREDIT_MEMO);
            queryParam.put("invTpCdCreditInvoice", BILL_TP.CREDIT_INVOICE);
            queryParam.put("invTpCdReqularInvoice", BILL_TP.REQULAR_INVOICE);
            queryParam.put("ediCustTpCdSell", STR_1);
            queryParam.put("ediCustTpCdShip", STR_2);
            queryParam.put("effThruDtDefault", EFF_THRU_DT_DEFAULT);
            // START 2020/01/10 M.Ishii [QC#55137, ADD]
            queryParam.put("twoMonthBefore", TWO_MONTH_BEFORE);
            // END 2020/01/10 M.Ishii [QC#55137, ADD]

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            stmt = this.ssmLLClient.createPreparedStatement("getCusaVndInvWrk", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            String invNum = BLANK;
            while (rs.next()) {

                invNum = rs.getString(INV_NUM);

                if (!duplicateCheckVndInv(invNum)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }

                // Set Header For Map
                Map<String, Object> invWrkMap = setCusaVndInvWrk(rs);

                if (!importWsInvWrk(invWrkMap)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }

                if (!importWsInvBolWrk(invNum)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
                if (!importWsInvLineWrk(invNum)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
                if (!importWsInvCashDiscTermWrk(invNum)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
                if (!importWsInvLicAccsWrk(invNum)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }

                // Commit for invoice.
                this.successCount++;
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }


    }

    /**
     * Import Invoice for CUSA Parts
     */
    private void importPartsInv() {

        // Get CUSA Parts EDI Trading Partner Code
        cusaPartsEdiTradingPartnerCd = ZYPCodeDataUtil.getVarCharConstValue(CUSA_PRT_EDI_TRD_PTNR_CD, glblCmpyCd);
        if (cusaPartsEdiTradingPartnerCd == null || cusaPartsEdiTradingPartnerCd.equals(BLANK)) {
            // CUSA Excluded System Source Code is no data
            throw new S21AbendException(MSG_ID_NLAM1118E, new String[] {MSG_TXT_CUSA_PARTS_EDI_TRADING_PARTNER_CD });
        }
        ArrayList<String> cusaPartsEdiTradingPartnerCdList = new ArrayList<String>();
        String[] arrayPartsEdiTradingPartnerCd = cusaPartsEdiTradingPartnerCd.split(",", 0);
        for (int i = 0; i < arrayPartsEdiTradingPartnerCd.length; i++) {
            cusaPartsEdiTradingPartnerCdList.add(arrayPartsEdiTradingPartnerCd[i]);
        }


        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpCd", this.glblCmpyCd);
            queryParam.put("salesDate", this.slsDt);
            queryParam.put("cusaPrtEdiTrdPtnrCd", cusaPartsEdiTradingPartnerCdList);
            queryParam.put("shipQty", STR_0);
            // START 2020/01/10 M.Ishii [QC#55137, ADD]
            queryParam.put("twoMonthBefore", TWO_MONTH_BEFORE);
            // END 2020/01/10 M.Ishii [QC#55137, ADD]

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get CUSA Parts Invoice
            stmt = this.ssmLLClient.createPreparedStatement("getCusaPartsInvoice", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            String invNum = BLANK;
            int lineNum = 0;
            int vndInvBolLineNum = 0;
            String soNum = "";
            boolean skipFlg = false;
            int insertCnt = 0;
            while (rs.next()) {

                // Set Header Fro Map
                Map<String, Object> vndInvWrkMap = setCusaPartsInvoice(rs);

                if (skipFlg) {
                    if (invNum.equals(rs.getString(P_NUM_INVOICE_CPI))) {
                        continue;
                    }
                    skipFlg = false;
                }

                if (!invNum.equals(rs.getString(P_NUM_INVOICE_CPI))) {

                    if (!invNum.equals(BLANK)) {
                        // Commit for Previous invoice.
                        insertCnt = 0;
                        this.successCount++;
                        commit();
                    }

                    invNum = rs.getString(P_NUM_INVOICE_CPI);
                    lineNum = 0;
                    vndInvBolLineNum = 0;
                    if (!duplicateCheckVndInv(invNum)) {
                        this.errorCount++;
                        skipFlg = true;
                        rollback();
                        continue;
                    }

                    // Create VND_INV_WRK.
                    if (!importPartsInvWrk(vndInvWrkMap)) {
                        this.errorCount++;
                        skipFlg = true;
                        rollback();
                        continue;
                    }
                }

                if (!soNum.equals(rs.getString(P_NUM_SO))) {
                    soNum = rs.getString(P_NUM_SO);
                    vndInvBolLineNum++;

                    if (!importPartsInvBolWrk(vndInvWrkMap, vndInvBolLineNum)) {
                        this.errorCount++;
                        skipFlg = true;
                        rollback();
                        continue;
                    }

                }

                lineNum++;

                if (!importPartsInvLineWrk(vndInvWrkMap, lineNum, vndInvBolLineNum)) {
                    this.errorCount++;
                    skipFlg = true;
                    rollback();
                    continue;
                }
                insertCnt++;
            }

            if (insertCnt > 0) {
                // Commit for last invoice.
                this.successCount++;
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

    }

    /**
     * duplicateCheckVndInv
     * @param invNum String
     * @return boolean
     */
    private boolean duplicateCheckVndInv(String invNum) {
        boolean result = true;

        boolean dupFlg;
        dupFlg = duplecateCheckVndInvWrk(invNum);
        if (!dupFlg) {
            dupFlg = duplecateCheckVndInvBolWrk(invNum);
        }
        if (!dupFlg) {
            dupFlg = duplecateCheckVndInvLineWrk(invNum);
        }
        if (!dupFlg) {
            dupFlg = duplecateCheckVndInvDiscTermWrk(invNum);
        }
        if (!dupFlg) {
            dupFlg = duplecateCheckVndInvLicAccsWrk(invNum);
        }
        if (dupFlg) {
            String message = S21MessageFunc.clspGetMessage(MSG_ID_NLAM1101E, new String[] {invNum });
            editErrorDetailMailMessage(this.mailSystemErrorMessageDetail, message);
            result = false;
        }
        if (this.mailSystemErrorMessageDetail.length() > 0) {
            editImportErrorMailMessage(this.mailSystemErrorMessage, this.mailSystemErrorMessageDetail, invNum);
            this.mailSystemErrorMessageDetail = new StringBuilder();
        }

        return result;
    }

    /**
     * duplecateCheckVndInvWrk
     * @param vndInvNum String
     * @return boolean
     */
    private boolean duplecateCheckVndInvWrk(String vndInvNum) {
        boolean dupFlg = false;
        VND_INV_WRKTMsg paramVndInvWrkTmsg = new VND_INV_WRKTMsg();
        paramVndInvWrkTmsg.setSQLID("002");
        paramVndInvWrkTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        paramVndInvWrkTmsg.setConditionValue("vndInvNum01", vndInvNum);
        VND_INV_WRKTMsgArray vndInvWrkTmsgArray = (VND_INV_WRKTMsgArray) EZDTBLAccessor.findByCondition(paramVndInvWrkTmsg);
        if (vndInvWrkTmsgArray.getValidCount() > 0) {
            for (int cIdx = 0; cIdx < vndInvWrkTmsgArray.getValidCount(); cIdx++) {
                if (ZYPConstant.FLG_OFF_0.equals(vndInvWrkTmsgArray.no(cIdx).ezCancelFlag.getValue())) {
                    dupFlg = true;
                }
            }
        }
        return dupFlg;
    }

    /**
     * duplecateCheckVndInvBolWrk
     * @param vndInvNum String
     * @return boolean
     */
    private boolean duplecateCheckVndInvBolWrk(String vndInvNum) {
        boolean dupFlg = false;
        VND_INV_BOL_WRKTMsg paramVndInvBolWrkTmsg = new VND_INV_BOL_WRKTMsg();
        paramVndInvBolWrkTmsg.setSQLID("001");
        paramVndInvBolWrkTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        paramVndInvBolWrkTmsg.setConditionValue("vndInvNum01", vndInvNum);
        VND_INV_BOL_WRKTMsgArray vndInvBolWrkTmsgArray = (VND_INV_BOL_WRKTMsgArray) EZDTBLAccessor.findByCondition(paramVndInvBolWrkTmsg);
        if (vndInvBolWrkTmsgArray.getValidCount() > 0) {
            for (int cIdx = 0; cIdx < vndInvBolWrkTmsgArray.getValidCount(); cIdx++) {
                if (ZYPConstant.FLG_OFF_0.equals(vndInvBolWrkTmsgArray.no(cIdx).ezCancelFlag.getValue())) {
                    dupFlg = true;
                }
            }
        }
        return dupFlg;
    }

    /**
     * duplecateCheckVndInvLineWrk
     * @param vndInvNum String
     * @return boolean
     */
    private boolean duplecateCheckVndInvLineWrk(String vndInvNum) {
        boolean dupFlg = false;
        VND_INV_LINE_WRKTMsg paramVndInvLineWrkTmsg = new VND_INV_LINE_WRKTMsg();
        paramVndInvLineWrkTmsg.setSQLID("002");
        paramVndInvLineWrkTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        paramVndInvLineWrkTmsg.setConditionValue("vndInvNum01", vndInvNum);
        VND_INV_LINE_WRKTMsgArray vndInvLineWrkTmsgArray = (VND_INV_LINE_WRKTMsgArray) EZDTBLAccessor.findByCondition(paramVndInvLineWrkTmsg);
        if (vndInvLineWrkTmsgArray.getValidCount() > 0) {
            for (int cIdx = 0; cIdx < vndInvLineWrkTmsgArray.getValidCount(); cIdx++) {
                if (ZYPConstant.FLG_OFF_0.equals(vndInvLineWrkTmsgArray.no(cIdx).ezCancelFlag.getValue())) {
                    dupFlg = true;
                }
            }
        }
        return dupFlg;
    }

    /**
     * duplecateCheckVndInvDiscTermWrk
     * @param vndInvNum String
     * @return boolean
     */
    private boolean duplecateCheckVndInvDiscTermWrk(String vndInvNum) {
        boolean dupFlg = false;
        VND_INV_DISC_TERM_WRKTMsg paramVndInvDiscTermWrk = new VND_INV_DISC_TERM_WRKTMsg();
        paramVndInvDiscTermWrk.setSQLID("001");
        paramVndInvDiscTermWrk.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        paramVndInvDiscTermWrk.setConditionValue("vndInvNum01", vndInvNum);
        VND_INV_DISC_TERM_WRKTMsgArray vndInvDiscTermWrkTmsgArray = (VND_INV_DISC_TERM_WRKTMsgArray) EZDTBLAccessor.findByCondition(paramVndInvDiscTermWrk);
        if (vndInvDiscTermWrkTmsgArray.getValidCount() > 0) {
            for (int cIdx = 0; cIdx < vndInvDiscTermWrkTmsgArray.getValidCount(); cIdx++) {
                if (ZYPConstant.FLG_OFF_0.equals(vndInvDiscTermWrkTmsgArray.no(cIdx).ezCancelFlag.getValue())) {
                    dupFlg = true;
                }
            }
        }
        return dupFlg;
    }

    /**
     * duplecateCheckVndInvLicAccsWrk
     * @param vndInvNum String
     * @return boolean
     */
    private boolean duplecateCheckVndInvLicAccsWrk(String vndInvNum) {
        boolean dupFlg = false;
        VND_INV_LIC_ACCS_WRKTMsg paramVndInvLicAccsWrkTmsg = new VND_INV_LIC_ACCS_WRKTMsg();
        paramVndInvLicAccsWrkTmsg.setSQLID("002");
        paramVndInvLicAccsWrkTmsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        paramVndInvLicAccsWrkTmsg.setConditionValue("vndInvNum01", vndInvNum);
        VND_INV_LIC_ACCS_WRKTMsgArray vndInvLicAccsWrkTmsgArray = (VND_INV_LIC_ACCS_WRKTMsgArray) EZDTBLAccessor.findByCondition(paramVndInvLicAccsWrkTmsg);
        if (vndInvLicAccsWrkTmsgArray.getValidCount() > 0) {
            for (int cIdx = 0; cIdx < vndInvLicAccsWrkTmsgArray.getValidCount(); cIdx++) {
                if (ZYPConstant.FLG_OFF_0.equals(vndInvLicAccsWrkTmsgArray.no(cIdx).ezCancelFlag.getValue())) {
                    dupFlg = true;
                }
            }
        }
        return dupFlg;
    }

    /**
     * importWsInvWrk
     * @param getMap Map<String, Object>
     * @return boolean
     */
    private boolean importWsInvWrk(Map<String, Object> getMap) {

        VND_INV_WRKTMsg vndInvWrk = new VND_INV_WRKTMsg();
        setVndInvWrkTMsg(getMap, vndInvWrk);

        return insertVndInvWrk(this.interfaceId, vndInvWrk);

    }

    /**
     * importWsInvBolWrk
     * @param invNum String
     * @return boolean
     */
    private boolean importWsInvBolWrk(String invNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpCd", this.glblCmpyCd);
        queryParam.put("cusaGlblCmpCd", this.cusaGlblCmpyCd);
        queryParam.put("parentInvNum", invNum);

        List<Map<String, Object>> cusaVndInvBolWrkList = ssmBatchClient.queryObjectList("getCusaVndInvBolWrk", queryParam);

        // AWCA0010_02TMsg getTmsg;
        ArrayList<VND_INV_BOL_WRKTMsg> vndInvBolWrkList = new ArrayList<VND_INV_BOL_WRKTMsg>(SELECT_SIZE);

        int loopCount = cusaVndInvBolWrkList.size();
        for (int i = 0; i < loopCount; i++) {
            VND_INV_BOL_WRKTMsg vndInvBolWrk = new VND_INV_BOL_WRKTMsg();

            Map<String, Object> getMap = cusaVndInvBolWrkList.get(i);
            setVndInvBolWrkTMsg(getMap, vndInvBolWrk);
            vndInvBolWrkList.add(vndInvBolWrk);
        }
        return insertVndInvBolWrk(this.interfaceId, vndInvBolWrkList, loopCount);
    }

    /**
     * importWsInvLineWrk
     * @param invNum String
     * @return boolean
     */
    private boolean importWsInvLineWrk(String invNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpCd", this.glblCmpyCd);
        queryParam.put("cusaGlblCmpCd", this.cusaGlblCmpyCd);
        queryParam.put("parentInvNum", invNum);

        List<Map<String, Object>> cusaVndInvLineWrkList = ssmBatchClient.queryObjectList("getCusaVndInvLineWrk", queryParam);

        ArrayList<VND_INV_LINE_WRKTMsg> vndInvLineWrkList = new ArrayList<VND_INV_LINE_WRKTMsg>(SELECT_SIZE);

        int loopCount = cusaVndInvLineWrkList.size();
        for (int i = 0; i < loopCount; i++) {
            VND_INV_LINE_WRKTMsg vndInvLineWrk = new VND_INV_LINE_WRKTMsg();

            Map<String, Object> getMap = cusaVndInvLineWrkList.get(i);
            setVndInvLineWrkTMsg(getMap, vndInvLineWrk);
            vndInvLineWrkList.add(vndInvLineWrk);
        }
        return insertVndInvLineWrk(this.interfaceId, vndInvLineWrkList, loopCount);
    }

    /**
     * importWsInvCashDiscTermWrk
     * @param invNum String
     * @return boolean
     */
    private boolean importWsInvCashDiscTermWrk(String invNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpCd", this.glblCmpyCd);
        queryParam.put("cusaGlblCmpCd", this.cusaGlblCmpyCd);
        queryParam.put("parentInvNum", invNum);

        List<Map<String, Object>> cusaVndInvDiscTeamWrkList = ssmBatchClient.queryObjectList("getCusaVndInvDiscTeamWrk", queryParam);

        ArrayList<VND_INV_DISC_TERM_WRKTMsg> vndInvDiscTermWrkList = new ArrayList<VND_INV_DISC_TERM_WRKTMsg>(SELECT_SIZE);

        int loopCount = cusaVndInvDiscTeamWrkList.size();
        for (int i = 0; i < loopCount; i++) {
            VND_INV_DISC_TERM_WRKTMsg vndInvDiscTermWrk = new VND_INV_DISC_TERM_WRKTMsg();

            Map<String, Object> getMap = cusaVndInvDiscTeamWrkList.get(i);
            setVndInvDiscTermWrkTMsg(getMap, vndInvDiscTermWrk);
            vndInvDiscTermWrkList.add(vndInvDiscTermWrk);
        }
        return insertVndInvDiscTermWrk(this.interfaceId, vndInvDiscTermWrkList, loopCount);
    }

    /**
     * importWsInvLicAccsWrk
     * @param invNum String
     * @return boolean
     */
    private boolean importWsInvLicAccsWrk(String invNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpCd", this.glblCmpyCd);
        queryParam.put("cusaGlblCmpCd", this.cusaGlblCmpyCd);
        queryParam.put("parentInvNum", invNum);
        queryParam.put("lakStsCd", LAK_STS.USED);

        List<Map<String, Object>> cusaVndInvLicAccsWrkList = ssmBatchClient.queryObjectList("getCusaVndInvLicAccsWrk", queryParam);

        ArrayList<VND_INV_LIC_ACCS_WRKTMsg> vndInvLicAccsWrkList = new ArrayList<VND_INV_LIC_ACCS_WRKTMsg>(SELECT_SIZE);

        int loopCount = cusaVndInvLicAccsWrkList.size();
        for (int i = 0; i < loopCount; i++) {
            VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk = new VND_INV_LIC_ACCS_WRKTMsg();

            Map<String, Object> getMap = cusaVndInvLicAccsWrkList.get(i);
            setVndInvLicAccsWrkTMsg(getMap, vndInvLicAccsWrk);
            vndInvLicAccsWrkList.add(vndInvLicAccsWrk);
        }
        return insertVndInvLicAccsWrk(this.interfaceId, vndInvLicAccsWrkList, loopCount);
    }

    /**
     * importPartsInvWrk
     * @param getMap Map<String, Object>
     * @return boolean
     */
    private boolean importPartsInvWrk(Map<String, Object> getMap) {

        VND_INV_WRKTMsg vndInvWrk = new VND_INV_WRKTMsg();
        setCusaPartsInvWrkTMsg(getMap, vndInvWrk);

        return insertVndInvWrk(this.interfaceId, vndInvWrk);

    }

    /**
     * importPartsInvBolWrk
     * @param getMap Map<String, Object>
     * @param vndInvBolLineNum int
     * @return boolean
     */
    private boolean importPartsInvBolWrk(Map<String, Object> getMap, int vndInvBolLineNum) {

        ArrayList<VND_INV_BOL_WRKTMsg> vndInvBolWrkList = new ArrayList<VND_INV_BOL_WRKTMsg>(SELECT_SIZE);
        VND_INV_BOL_WRKTMsg vndInvBolWrk = new VND_INV_BOL_WRKTMsg();

        setCusaPartsInvBolWrkTMsg(getMap, vndInvBolWrk, vndInvBolLineNum);

        vndInvBolWrkList.add(vndInvBolWrk);

        return insertVndInvBolWrk(this.interfaceId, vndInvBolWrkList, 1);
    }

    /**
     * importPartsInvLineWrk
     * @param getMap Map<String, Object>
     * @param lineNum int
     * @param vndInvBolLineNum int
     * @return boolean
     */
    private boolean importPartsInvLineWrk(Map<String, Object> getMap, int lineNum, int vndInvBolLineNum) {
        
        ArrayList<VND_INV_LINE_WRKTMsg> vndInvLineWrkList = new ArrayList<VND_INV_LINE_WRKTMsg>(SELECT_SIZE);
        VND_INV_LINE_WRKTMsg vndInvLineWrk = new VND_INV_LINE_WRKTMsg();
        setCusaPartsInvLineWrkTMsg(getMap, vndInvLineWrk, lineNum, vndInvBolLineNum);
        vndInvLineWrkList.add(vndInvLineWrk);

        return insertVndInvLineWrk(this.interfaceId, vndInvLineWrkList, 1);
    }

    /**
     * insertVndInvWrk
     * @param intfcId String
     * @param vndInvWrk VND_INV_WRKTMsg
     * @return boolean
     */
    private boolean insertVndInvWrk(String intfcId, VND_INV_WRKTMsg vndInvWrk) {

        EZDTBLAccessor.create((VND_INV_WRKTMsg) vndInvWrk);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvWrk.getReturnCode())) {
            insertWrkErrorLogOutPut(intfcId, /* trxSq, */VND_INV_WRK);
            return false;
        }

        return true;
    }

    /**
     * insertVndInvBolWrk
     * @param intfcId String
     * @param vndInvBolWrkList ArrayList<VND_INV_BOL_WRKTMsg>
     * @param insertCnt int
     * @return boolean
     */
    private boolean insertVndInvBolWrk(String intfcId, ArrayList<VND_INV_BOL_WRKTMsg> vndInvBolWrkList, int insertCnt) {

        for (int idx = 0; idx < insertCnt; idx++) {
            VND_INV_BOL_WRKTMsg vndInvBolWrk = vndInvBolWrkList.get(idx);
            EZDTBLAccessor.create((VND_INV_BOL_WRKTMsg) vndInvBolWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvBolWrk.getReturnCode())) {
                insertWrkErrorLogOutPut(intfcId, /* trxSq, */VND_INV_BOL_WRK);
                return false;
            }
        }

        return true;
    }

    /**
     * insertVndInvLineWrk
     * @param intfcId String
     * @param vndInvLineWrkList ArrayList<VND_INV_LINE_WRKTMsg>
     * @param insertCnt int
     * @return boolean
     */
    private boolean insertVndInvLineWrk(String intfcId, ArrayList<VND_INV_LINE_WRKTMsg> vndInvLineWrkList, int insertCnt) {

        for (int idx = 0; idx < insertCnt; idx++) {
            VND_INV_LINE_WRKTMsg vndInvLineWrk = vndInvLineWrkList.get(idx);
            EZDTBLAccessor.create((VND_INV_LINE_WRKTMsg) vndInvLineWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvLineWrk.getReturnCode())) {
                insertWrkErrorLogOutPut(intfcId, /* trxSq, */VND_INV_LINE_WRK);
                return false;
            }
        }

        return true;
    }

    /**
     * insertVndInvDiscTermWrk
     * @param intfcId String
     * @param vndInvDiscTermWrkList ArrayList<VND_INV_DISC_TERM_WRKTMsg>
     * @param insertCnt int
     * @return boolean
     */
    private boolean insertVndInvDiscTermWrk(String intfcId, ArrayList<VND_INV_DISC_TERM_WRKTMsg> vndInvDiscTermWrkList, int insertCnt) {
        if (insertCnt == 0) {
            return true;
        }
        for (int idx = 0; idx < insertCnt; idx++) {
            VND_INV_DISC_TERM_WRKTMsg vndInvDiscTermWrk = vndInvDiscTermWrkList.get(idx);
            EZDTBLAccessor.create((VND_INV_DISC_TERM_WRKTMsg) vndInvDiscTermWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvDiscTermWrk.getReturnCode())) {
                insertWrkErrorLogOutPut(intfcId, VND_INV_DISC_TERM_WRK);
                return false;
            }
        }

        return true;
    }

    /**
     * insertVndInvLicAccsWrk
     * @param intfcId String
     * @param vndInvLicAccsWrkList ArrayList<VND_INV_LIC_ACCS_WRKTMsg>
     * @param insertCnt int
     * @return boolean
     */
    private boolean insertVndInvLicAccsWrk(String intfcId, ArrayList<VND_INV_LIC_ACCS_WRKTMsg> vndInvLicAccsWrkList, int insertCnt) {
        if (insertCnt == 0) {
            return true;
        }
        for (int idx = 0; idx < insertCnt; idx++) {
            VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk = vndInvLicAccsWrkList.get(idx);
            EZDTBLAccessor.create((VND_INV_LIC_ACCS_WRKTMsg) vndInvLicAccsWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndInvLicAccsWrk.getReturnCode())) {
                insertWrkErrorLogOutPut(intfcId, /* trxSq, */VND_INV_LIC_ACCS_WRK);
                return false;
            }
        }

        return true;
    }

    /**
     * insertWrkErrorLogOutPut
     * @param intfcId String
     * @param tableId String
     * @return boolean
     */
    private boolean insertWrkErrorLogOutPut(String intfcId, String tableId) {
        StringBuilder sbKey = new StringBuilder();

        sbKey.append(ITRL_INTFC_ID);
        sbKey.append(MSG_TXT_EQUALS);
        sbKey.append(intfcId);
        sbKey.append(MSG_TXT_SPACE);

        S21InfoLogOutput.println(MSG_ID_NLAM1270E, new String[] {tableId, sbKey.toString() });

        return false;
    }

    /**
     * editImportErrorMailMessage
     * @param mailMessage StringBuilder
     * @param mailMessageDetail StringBuilder
     * @param invNum String
     * @return boolean
     */
    private void editImportErrorMailMessage(StringBuilder mailMessage, StringBuilder mailMessageDetail, String invNum) {
        mailMessage.append(ZYPCommonFunc.leftPad(BLANK, STRING_LENGTH_100, MSG_TXT_HYPHEN));
        mailMessage.append(ERR_MSG_CRLF);
        mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_4, MSG_TXT_SPACE));
        mailMessage.append(ZYPCommonFunc.rightPad(this.interfaceId, STRING_LENGTH_30, MSG_TXT_SPACE));
        mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_2, MSG_TXT_SPACE));

        mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_2, MSG_TXT_SPACE));
        mailMessage.append(ZYPCommonFunc.rightPad(invNum, STRING_LENGTH_15, MSG_TXT_SPACE));
        mailMessage.append(ZYPCommonFunc.leftPad(MSG_TXT_SPACE, STRING_LENGTH_2, MSG_TXT_SPACE));
        mailMessage.append(ERR_MSG_IMPORT);
        mailMessage.append(ERR_MSG_CRLF);
        mailMessage.append(mailMessageDetail);
        mailMessage.append(ERR_MSG_CRLF);
    }

    /**
     * editErrorDetailMailMessage
     * @param mailMessageDetail StringBuilder
     * @param message String
     * @return boolean
     */
    private void editErrorDetailMailMessage(StringBuilder mailMessageDetail, String message) {
        S21InfoLogOutput.println(message);
        mailMessageDetail.append(message);
        mailMessageDetail.append(ERR_MSG_CRLF);
    }

    /**
     * sendSystemErrorMail
     */
    private void sendSystemErrorMail() {
        // Get To Mail Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO_SYSTEM);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList.isEmpty()) {
            throw new S21AbendException(MSG_ID_NLAM1273E, new String[] {MAIL_GROUP_ID_TO_SYSTEM });
        }
        sendMail(addrToList, this.mailSystemErrorMessage.toString());
    }

    /**
     * Send Error Mail
     */
    private void sendMail(List<S21MailAddress> addrToList, String mailBody) {

        String currentTs = ZYPDateUtil.getCurrentSystemTime(MAIL_TS_FMT);
        // Get From Mail Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        S21MailAddress from = null;
        if (!addrFromList.isEmpty()) {
            from = addrFromList.get(0);
        }
        // Get Template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_M001);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(MSG_ID_NLAM1272E, new String[] {MAIL_TEMPLATE_ID_M001 });
        }

        template.setTemplateParameter(MAIL_FIELD_TIMESTAMP, currentTs);
        template.setTemplateParameter(MAIL_FIELD_MESSAGE, mailBody);

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    /**
     * setCusaVndInvWrk
     * @param rs ResultSet
     * @return Map<String, Object>
     * @throws SQLException
     */
    private Map<String, Object> setCusaVndInvWrk(ResultSet rs) throws SQLException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(INV_NUM, rs.getString(INV_NUM));
        map.put(ORIG_INV_NUM, rs.getString(ORIG_INV_NUM));
        map.put(INV_DT, rs.getString(INV_DT));
        map.put(INV_TP_CD, rs.getString(INV_TP_CD));
        map.put(NET_DUE_DT, rs.getString(NET_DUE_DT));
        map.put(CPO_ORD_NUM, rs.getString(CPO_ORD_NUM));
        map.put(ORD_DT, rs.getString(ORD_DT));
        map.put(CPO_ORD_TP_CD, rs.getString(CPO_ORD_TP_CD));
        map.put(OFC_NM, rs.getString(OFC_NM));
        map.put(OFC_BR_CD, rs.getString(OFC_BR_CD));
        map.put(OFC_FIRST_LINE_ADDR, rs.getString(OFC_FIRST_LINE_ADDR));
        map.put(OFC_SCD_LINE_ADDR, rs.getString(OFC_SCD_LINE_ADDR));
        map.put(OFC_THIRD_LINE_ADDR, rs.getString(OFC_THIRD_LINE_ADDR));
        map.put(OFC_FRTH_LINE_ADDR, rs.getString(OFC_FRTH_LINE_ADDR));
        map.put(OFC_CTY_ADDR, rs.getString(OFC_CTY_ADDR));
        map.put(OFC_ST_CD, rs.getString(OFC_ST_CD));
        map.put(OFC_PROV_NM, rs.getString(OFC_PROV_NM));
        map.put(OFC_CNTY_NM, rs.getString(OFC_CNTY_NM));
        map.put(OFC_POST_CD, rs.getString(OFC_POST_CD));
        map.put(OFC_CTRY_CD, rs.getString(OFC_CTRY_CD));
        map.put(OFC_TEL_NUM, rs.getString(OFC_TEL_NUM));
        map.put(OFC_FAX_NUM, rs.getString(OFC_FAX_NUM));
        map.put(OFC_LOC_NM, rs.getString(OFC_LOC_NM));
        map.put(OFC_ADDL_LOC_NM, rs.getString(OFC_ADDL_LOC_NM));
        map.put(REMIT_TO_LOC_NM, rs.getString(REMIT_TO_LOC_NM));
        map.put(REMIT_TO_ADDL_LOC_NM, rs.getString(REMIT_TO_ADDL_LOC_NM));
        map.put(REM_ID, rs.getString(REM_ID));
        map.put(DUNS_NUM, rs.getString(DUNS_NUM));
        map.put(BILL_TO_CUST_CD, rs.getString(BILL_TO_CUST_CD));
        map.put(SELL_TO_CUST_CD, rs.getString(SELL_TO_CUST_CD));
        map.put(SELL_TO_LOC_NM, rs.getString(SELL_TO_LOC_NM));
        map.put(SELL_TO_ADDL_LOC_NM, rs.getString(SELL_TO_ADDL_LOC_NM));
        map.put(SELL_TO_FIRST_LINE_ADDR, rs.getString(SELL_TO_FIRST_LINE_ADDR));
        map.put(SELL_TO_SCD_LINE_ADDR, rs.getString(SELL_TO_SCD_LINE_ADDR));
        map.put(SELL_TO_THIRD_LINE_ADDR, rs.getString(SELL_TO_THIRD_LINE_ADDR));
        map.put(SELL_TO_FRTH_LINE_ADDR, rs.getString(SELL_TO_FRTH_LINE_ADDR));
        map.put(SELL_TO_CTY_ADDR, rs.getString(SELL_TO_CTY_ADDR));
        map.put(SELL_TO_ST_CD, rs.getString(SELL_TO_ST_CD));
        map.put(SELL_TO_PROV_NM, rs.getString(SELL_TO_PROV_NM));
        map.put(SELL_TO_CNTY_NM, rs.getString(SELL_TO_CNTY_NM));
        map.put(SELL_TO_POST_CD, rs.getString(SELL_TO_POST_CD));
        map.put(SELL_TO_CTRY_CD, rs.getString(SELL_TO_CTRY_CD));
        map.put(SELL_TO_FIRST_REF_CMNT_TXT, rs.getString(SELL_TO_FIRST_REF_CMNT_TXT));
        map.put(SELL_TO_SCD_REF_CMNT_TXT, rs.getString(SELL_TO_SCD_REF_CMNT_TXT));
        map.put(RCPNT_FIRST_LINE_ADDR, rs.getString(RCPNT_FIRST_LINE_ADDR));
        map.put(RCPNT_SCD_LINE_ADDR, rs.getString(RCPNT_SCD_LINE_ADDR));
        map.put(RCPNT_THIRD_LINE_ADDR, rs.getString(RCPNT_THIRD_LINE_ADDR));
        map.put(RCPNT_FRTH_LINE_ADDR, rs.getString(RCPNT_FRTH_LINE_ADDR));
        map.put(RCPNT_CTY_ADDR, rs.getString(RCPNT_CTY_ADDR));
        map.put(RCPNT_ST_CD, rs.getString(RCPNT_ST_CD));
        map.put(RCPNT_PROV_NM, rs.getString(RCPNT_PROV_NM));
        map.put(RCPNT_CNTY_NM, rs.getString(RCPNT_CNTY_NM));
        map.put(RCPNT_POST_CD, rs.getString(RCPNT_POST_CD));
        map.put(RCPNT_CTRY_CD, rs.getString(RCPNT_CTRY_CD));
        map.put(RCPNT_LOC_NM, rs.getString(RCPNT_LOC_NM));
        map.put(RCPNT_ADDL_LOC_NM, rs.getString(RCPNT_ADDL_LOC_NM));
        map.put(INV_RCPNT_FAX_NUM, rs.getString(INV_RCPNT_FAX_NUM));
        map.put(PMT_TERM_START_DT, rs.getString(PMT_TERM_START_DT));
        map.put(PMT_TERM_CD, rs.getString(PMT_TERM_CD));
        map.put(PMT_TERM_NM, rs.getString(PMT_TERM_NM));
        map.put(CASH_DISC_TERM_CD, rs.getString(CASH_DISC_TERM_CD));
        map.put(SLS_ADMIN_PSN_CD, rs.getString(SLS_ADMIN_PSN_CD));
        map.put(CR_ANLST_PSN_CD, rs.getString(CR_ANLST_PSN_CD));
        map.put(CR_ANLST_PSN_NM, rs.getString(CR_ANLST_PSN_NM));
        map.put(INV_TOT_DEAL_NET_AMT, rs.getBigDecimal(INV_TOT_DEAL_NET_AMT));
        map.put(INV_TOT_DEAL_SLS_AMT, rs.getBigDecimal(INV_TOT_DEAL_SLS_AMT));
        map.put(INV_TOT_DEAL_FRT_AMT, rs.getBigDecimal(INV_TOT_DEAL_FRT_AMT));
        map.put(INV_TOT_DEAL_TAX_AMT, rs.getBigDecimal(INV_TOT_DEAL_TAX_AMT));
        map.put(INV_TOT_DEAL_DISC_AMT, rs.getBigDecimal(INV_TOT_DEAL_DISC_AMT));
        map.put(INV_TOT_DEAL_INS_AMT, rs.getBigDecimal(INV_TOT_DEAL_INS_AMT));
        map.put(DEAL_CCY_CD, rs.getString(DEAL_CCY_CD));
        map.put(PAYER_CUST_CD, rs.getString(PAYER_CUST_CD));
        map.put(INV_FIRST_CMNT_TXT, rs.getString(INV_FIRST_CMNT_TXT));
        map.put(INV_SCD_CMNT_TXT, rs.getString(INV_SCD_CMNT_TXT));
        map.put(INV_THIRD_CMNT_TXT, rs.getString(INV_THIRD_CMNT_TXT));
        map.put(INV_FRTH_CMNT_TXT, rs.getString(INV_FRTH_CMNT_TXT));
        map.put(ACCT_DT, rs.getString(ACCT_DT));

        map.put(CUST_ISS_PO_NUM, rs.getString(CUST_ISS_PO_NUM));
        map.put(CR_DR_RSN_CD, rs.getString(CR_DR_RSN_CD));

        map.put(PMT_TERM_CASH_DISC_CD, rs.getString(PMT_TERM_CASH_DISC_CD));

        return map;
    }

    /**
     * setCusaPartsInvoice
     * @param rs ResultSet
     * @return Map<String, Object>
     * @throws SQLException
     */
    private Map<String, Object> setCusaPartsInvoice(ResultSet rs) throws SQLException {

        Map<String, Object> map = new HashMap<String, Object>();
        // VND_INV_WORK
        map.put(P_NUM_INVOICE_CPI, rs.getString(P_NUM_INVOICE_CPI));
        map.put(P_DATE_INVO, rs.getString(P_DATE_INVO));
        map.put(P_CODE_ORDR_TYPE_PART, rs.getString(P_CODE_ORDR_TYPE_PART));
        map.put(P_NET_DAYS, rs.getInt(P_NET_DAYS));
        map.put(P_NUM_ORDR, rs.getString(P_NUM_ORDR));
        map.put(P_DATE_ORDR_RCV, rs.getString(P_DATE_ORDR_RCV));
        map.put(P_CODE_CUST_CHRG_TO, rs.getString(P_CODE_CUST_CHRG_TO));
        map.put(P_CODE_CUST_SOLD_TO, rs.getString(P_CODE_CUST_SOLD_TO));
        map.put(P_CODE_TERM_S80, rs.getString(P_CODE_TERM_S80));
        map.put(P_AMT_INVO_TOT_CPI, rs.getBigDecimal(P_AMT_INVO_TOT_CPI));
        map.put(P_AMT_SHIP_CPI, rs.getBigDecimal(P_AMT_SHIP_CPI));
        map.put(P_AMT_INVO_FRT_CPI, rs.getBigDecimal(P_AMT_INVO_FRT_CPI));
        map.put(P_AMT_SO_TAX_CPI, rs.getBigDecimal(P_AMT_SO_TAX_CPI));
        map.put(P_AMT_DISCOUNT_CPI, rs.getBigDecimal(P_AMT_DISCOUNT_CPI));
        map.put(P_MSG_SO_1, rs.getString(P_MSG_SO_1));
        map.put(P_MSG_SO_2, rs.getString(P_MSG_SO_2));

        // VND_INV_BOL_WRK
        map.put(P_NUM_INVOICE_CPSH, rs.getString(P_NUM_INVOICE_CPSH));
        map.put(P_NUM_SO, rs.getString(P_NUM_SO));
        map.put(P_NUM_CUST_PO, rs.getString(P_NUM_CUST_PO));
        map.put(P_CODE_WH_SHIP, rs.getString(P_CODE_WH_SHIP));
        map.put(P_CODE_CUST_SHIP_TO, rs.getString(P_CODE_CUST_SHIP_TO));
        map.put(P_NAME_CUST_SHIP_TO_1, rs.getString(P_NAME_CUST_SHIP_TO_1));
        map.put(P_NAME_CUST_SHIP_TO_2, rs.getString(P_NAME_CUST_SHIP_TO_2));
        map.put(P_SHIP_TO_EXT_ADDR_01, rs.getString(P_SHIP_TO_EXT_ADDR_01));
        map.put(P_SHIP_TO_EXT_ADDR_02, rs.getString(P_SHIP_TO_EXT_ADDR_02));
        map.put(P_SHIP_TO_EXT_ADDR_03, rs.getString(P_SHIP_TO_EXT_ADDR_03));
        map.put(P_SHIP_TO_EXT_ADDR_04, rs.getString(P_SHIP_TO_EXT_ADDR_04));
        map.put(P_CODE_SHIP_TO_STA, rs.getString(P_CODE_SHIP_TO_STA));
        map.put(P_ADDR_SHIP_TO_ZIP, rs.getString(P_ADDR_SHIP_TO_ZIP));
        map.put(P_ADDR_SHIP_TO_CITY, rs.getString(P_ADDR_SHIP_TO_CITY));
        map.put(P_AMT_SHIP_CPSH, rs.getBigDecimal(P_AMT_SHIP_CPSH));
        map.put(P_AMT_INVO_TOT_CPSH, rs.getBigDecimal(P_AMT_INVO_TOT_CPSH));
        map.put(P_AMT_INVO_FRT_CPSH, rs.getBigDecimal(P_AMT_INVO_FRT_CPSH));
        map.put(P_AMT_DISCOUNT_CPSH, rs.getBigDecimal(P_AMT_DISCOUNT_CPSH));
        map.put(P_AMT_HANDLG_CHRG, rs.getBigDecimal(P_AMT_HANDLG_CHRG));
        map.put(P_DATE_SHIP_PRSS, rs.getString(P_DATE_SHIP_PRSS));
        map.put(P_AMT_SO_TAX_CPSH, rs.getBigDecimal(P_AMT_SO_TAX_CPSH));

        // VND_INV_LINE_WRK
        map.put(P_NUM_LINE, rs.getString(P_NUM_LINE));
        map.put(P_PARTS_NUM_FLIP, rs.getString(P_PARTS_NUM_FLIP));
        map.put(P_PARTS_NUM_SO, rs.getString(P_PARTS_NUM_SO));
        map.put(P_QTY_SHIP_SCHED, rs.getBigDecimal(P_QTY_SHIP_SCHED));
        map.put(P_QTY_SHIP, rs.getBigDecimal(P_QTY_SHIP));
        map.put(P_PRC_SALE, rs.getBigDecimal(P_PRC_SALE));
        map.put(P_PRC_DISCOUNT, rs.getBigDecimal(P_PRC_DISCOUNT));
        map.put(P_DESC_PART, rs.getString(P_DESC_PART));
        map.put(P_PARTS_NUM_ORIG, rs.getString(P_PARTS_NUM_ORIG));

        return map;
    }

    /**
     * setVndInvWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvWrk VND_INV_WRKTMsg
     */
    private void setVndInvWrkTMsg(Map<String, Object> getMap, VND_INV_WRKTMsg vndInvWrk) {

        ZYPEZDItemValueSetter.setValue(vndInvWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvNum, (String) getMap.get(INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.origVndInvNum, (String) getMap.get(ORIG_INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invDt, (String) getMap.get(INV_DT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTpCd, (String) getMap.get(INV_TP_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.netDueDt, (String) getMap.get(NET_DUE_DT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndCpoOrdNum, (String) getMap.get(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ordDt, (String) getMap.get(ORD_DT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndCpoOrdTpCd, (String) getMap.get(CPO_ORD_TP_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcNm, (String) getMap.get(OFC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcBrCd, (String) getMap.get(OFC_BR_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcBrCd, (String) getMap.get(OFC_BR_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcFirstLineAddr, (String) getMap.get(OFC_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcScdLineAddr, (String) getMap.get(OFC_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcThirdLineAddr, (String) getMap.get(OFC_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcFrthLineAddr, (String) getMap.get(OFC_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcCtyAddr, (String) getMap.get(OFC_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcStCd, (String) getMap.get(OFC_ST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcProvNm, (String) getMap.get(OFC_PROV_NM));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcCntyNm, (String) getMap.get(OFC_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcPostCd, (String) getMap.get(OFC_POST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcCtryCd, (String) getMap.get(OFC_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcTelNum, (String) getMap.get(OFC_TEL_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcFaxNum, (String) getMap.get(OFC_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcLocNm, (String) getMap.get(OFC_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ofcAddlLocNm, (String) getMap.get(OFC_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.remitToLocNm, (String) getMap.get(REMIT_TO_LOC_NM));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.remitToAddlLocNm, (String) getMap.get(REMIT_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.remId, (String) getMap.get(REM_ID));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.dunsNum, (String) getMap.get(DUNS_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.billToCustCd, (String) getMap.get(BILL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToCustCd, (String) getMap.get(SELL_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToLocNm, (String) getMap.get(SELL_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToAddlLocNm, (String) getMap.get(SELL_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToFirstLineAddr, (String) getMap.get(SELL_TO_FIRST_LINE_ADDR));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToScdLineAddr, (String) getMap.get(SELL_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToThirdLineAddr, (String) getMap.get(SELL_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToFrthLineAddr, (String) getMap.get(SELL_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToCtyAddr, (String) getMap.get(SELL_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToStCd, (String) getMap.get(SELL_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToProvNm, (String) getMap.get(SELL_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToCntyNm, (String) getMap.get(SELL_TO_CNTY_NM));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToPostCd, (String) getMap.get(SELL_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToCtryCd, (String) getMap.get(SELL_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToFirstRefCmntTxt, (String) getMap.get(SELL_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToScdRefCmntTxt, (String) getMap.get(SELL_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntFirstLineAddr, (String) getMap.get(RCPNT_FIRST_LINE_ADDR));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntScdLineAddr, (String) getMap.get(RCPNT_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntThirdLineAddr, (String) getMap.get(RCPNT_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntFrthLineAddr, (String) getMap.get(RCPNT_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntCtyAddr, (String) getMap.get(RCPNT_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntStCd, (String) getMap.get(RCPNT_ST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntProvNm, (String) getMap.get(RCPNT_PROV_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntCntyNm, (String) getMap.get(RCPNT_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntPostCd, (String) getMap.get(RCPNT_POST_CD));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntCtryCd, (String) getMap.get(RCPNT_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntLocNm, (String) getMap.get(RCPNT_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.rcpntAddlLocNm, (String) getMap.get(RCPNT_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invRcpntFaxNum, (String) getMap.get(INV_RCPNT_FAX_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndPmtTermStartDt, (String) getMap.get(PMT_TERM_START_DT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.cashDiscTermCd, (String) getMap.get(CASH_DISC_TERM_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.slsAdminPsnCd, (String) getMap.get(SLS_ADMIN_PSN_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.crAnlstPsnCd, (String) getMap.get(CR_ANLST_PSN_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.crAnlstPsnNm, (String) getMap.get(CR_ANLST_PSN_NM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealNetAmt, (BigDecimal) getMap.get(INV_TOT_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealSlsAmt, (BigDecimal) getMap.get(INV_TOT_DEAL_SLS_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealFrtAmt, (BigDecimal) getMap.get(INV_TOT_DEAL_FRT_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealTaxAmt, (BigDecimal) getMap.get(INV_TOT_DEAL_TAX_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealDiscAmt, (BigDecimal) getMap.get(INV_TOT_DEAL_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealInsAmt, (BigDecimal) getMap.get(INV_TOT_DEAL_INS_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.dealCcyCd, (String) getMap.get(DEAL_CCY_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.payerCustCd, (String) getMap.get(PAYER_CUST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invFirstCmntTxt, (String) getMap.get(INV_FIRST_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invScdCmntTxt, (String) getMap.get(INV_SCD_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invThirdCmntTxt, (String) getMap.get(INV_THIRD_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invFrthCmntTxt, (String) getMap.get(INV_FRTH_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.acctDt, (String) getMap.get(ACCT_DT));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvProcStsCd, VND_INV_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.itrlIntfcId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.cmProcStsCd, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(vndInvWrk.psIssRqstFlg, ZYPConstant.FLG_OFF_N);

        //QC8660
        ZYPEZDItemValueSetter.setValue(vndInvWrk.custIssPoNum, (String) getMap.get(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.crDrRsnCd, (String) getMap.get(CR_DR_RSN_CD));

        // V3.1 Add QC#14714
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndPmtTermCashDiscCd, (String) getMap.get(PMT_TERM_CASH_DISC_CD));

    }

    /**
     * setVndInvBolWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvBolWrk VND_INV_BOL_WRKTMsg
     */
    private void setVndInvBolWrkTMsg(Map<String, Object> getMap, VND_INV_BOL_WRKTMsg vndInvBolWrk) {

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.vndInvNum, (String) getMap.get(INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.vndInvBolLineNum, (String) getMap.get(INV_BOL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.asnSoNum, (String) getMap.get(SO_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.bolNum, (String) getMap.get(BOL_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.carrCd, (String) getMap.get(CARR_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.carrNm, (String) getMap.get(CARR_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.proNum, (String) getMap.get(PRO_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.ediPoOrdNum, (String) getMap.get(CUST_ISS_PO_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.vndInvtyLocCd, (String) getMap.get(SHIP_FROM_INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToCustCd, (String) getMap.get(SHIP_TO_CUST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.dropShipFlg, (String) getMap.get(DROP_SHIP_FLG));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToLocNm, (String) getMap.get(SHIP_TO_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToAddlLocNm, (String) getMap.get(SHIP_TO_ADDL_LOC_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToFirstLineAddr, (String) getMap.get(SHIP_TO_FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToScdLineAddr, (String) getMap.get(SHIP_TO_SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToThirdLineAddr, (String) getMap.get(SHIP_TO_THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToFrthLineAddr, (String) getMap.get(SHIP_TO_FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToStCd, (String) getMap.get(SHIP_TO_ST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToProvNm, (String) getMap.get(SHIP_TO_PROV_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToCntyNm, (String) getMap.get(SHIP_TO_CNTY_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToFirstRefCmntTxt, (String) getMap.get(SHIP_TO_FIRST_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToScdRefCmntTxt, (String) getMap.get(SHIP_TO_SCD_REF_CMNT_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToPostCd, (String) getMap.get(SHIP_TO_POST_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToCtyAddr, (String) getMap.get(SHIP_TO_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToCtryCd, (String) getMap.get(SHIP_TO_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealSlsAmt, (BigDecimal) getMap.get(SHIP_DEAL_SLS_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealNetAmt, (BigDecimal) getMap.get(SHIP_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealFrtAmt, (BigDecimal) getMap.get(SHIP_DEAL_FRT_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealDiscAmt, (BigDecimal) getMap.get(SHIP_DEAL_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealHdlgChrgAmt, (BigDecimal) getMap.get(SHIP_DEAL_HDLG_CHRG_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDt, (String) getMap.get(SHIP_DT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.arvDt, (String) getMap.get(ARV_DT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.totBolDealTaxAmt, (BigDecimal) getMap.get(TOT_BOL_DEAL_TAX_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shpgSvcLvlCd, (String) getMap.get(SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.frtChrgToCd, (String) getMap.get(FRT_CHRG_TO_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.frtChrgToNm, (String) getMap.get(FRT_CHRG_TO_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.frtChrgMethCd, (String) getMap.get(FRT_CHRG_METH_CD));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.frtChrgMethNm, (String) getMap.get(FRT_CHRG_METH_NM));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.frtTaxPct, (BigDecimal) getMap.get(FRT_TAX_PCT));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.frtDealTaxAmt, (BigDecimal) getMap.get(FRT_DEAL_TAX_AMT));

    }

    /**
     * setVndInvLineWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvLineWrk VND_INV_LINE_WRKTMsg
     */
    private void setVndInvLineWrkTMsg(Map<String, Object> getMap, VND_INV_LINE_WRKTMsg vndInvLineWrk) {

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvNum, (String) getMap.get(INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvBolLineNum, (String) getMap.get(INV_BOL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineNum, (String) getMap.get(INV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineSubNum, (String) getMap.get(INV_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineSubTrxNum, (String) getMap.get(INV_LINE_SUB_TRX_NUM));
        // EDI_NUM Set
        String setMdseCd = (String) getMap.get(SET_MDSE_CD);
        if (setMdseCd != null && !setMdseCd.equals(BLANK)) {
            // set item
            String custIssPoNum = (String) getMap.get(CUST_ISS_PO_NUM);
            String ediNum = (String) getMap.get(EDI_NUM);
            String mdseCd = (String) getMap.get(MDSE_CD);
            String setEdiPoOrdDtlLineNum = null;
            setEdiPoOrdDtlLineNum = selectSetPoLineNum(custIssPoNum, ediNum, mdseCd);

            if (setEdiPoOrdDtlLineNum != null && !setEdiPoOrdDtlLineNum.equals(BLANK)) {
                setEdiPoOrdDtlLineNum = ZYPCommonFunc.leftPad(setEdiPoOrdDtlLineNum, 6, STR_0);
            } else {
                // Dummy EDI Line Num
                setEdiPoOrdDtlLineNum = (String) getMap.get(CPO_DTL_LINE_NUM) + (String) getMap.get(CPO_DTL_LINE_SUB_NUM);
            }
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.ediPoOrdDtlLineNum, setEdiPoOrdDtlLineNum);

        } else {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.ediPoOrdDtlLineNum, (String) getMap.get(EDI_NUM));
        }

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndCpoOrdNum, (String) getMap.get(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndCpoDtlLineNum, (String) getMap.get(CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndCpoDtlLineSubNum, (String) getMap.get(CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.stkStsCd, (String) getMap.get(STK_STS_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseCd, (String) getMap.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.mdseNm, (String) getMap.get(MDSE_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.ordQty, (BigDecimal) getMap.get(ORD_QTY));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.shipQty, (BigDecimal) getMap.get(SHIP_QTY));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.boQty, (BigDecimal) getMap.get(BO_QTY));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.manPrcFlg, (String) getMap.get(MAN_PRC_FLG));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealNetUnitPrcAmt, (BigDecimal) getMap.get(DEAL_NET_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineDealTaxAmt, (BigDecimal) getMap.get(INV_LINE_DEAL_TAX_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineDealNetAmt, (BigDecimal) getMap.get(INV_LINE_DEAL_NET_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealDiscUnitPrcAmt, (BigDecimal) getMap.get(DEAL_DISC_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.taxPct, (BigDecimal) getMap.get(TAX_PCT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.mercStmtOnInvTxt, (String) getMap.get(MERC_STMT_ON_INV_TXT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.origVndInvNum, (String) getMap.get(ORIG_INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.setMdseCd, (String) getMap.get(SET_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealGrsUnitPrcAmt, (BigDecimal) getMap.get(DEAL_GRS_UNIT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealGrsTotPrcAmt, (BigDecimal) getMap.get(DEAL_GRS_TOT_PRC_AMT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.uomCd, (String) getMap.get(CUST_UOM_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(CUST_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.zerothProdCtrlCd, (String) getMap.get(ZEROTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.zerothProdCtrlNm, (String) getMap.get(ZEROTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.firstProdCtrlCd, (String) getMap.get(FIRST_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.firstProdCtrlNm, (String) getMap.get(FIRST_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.scdProdCtrlCd, (String) getMap.get(SCD_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.scdProdCtrlNm, (String) getMap.get(SCD_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.thirdProdCtrlCd, (String) getMap.get(THIRD_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.thirdProdCtrlNm, (String) getMap.get(THIRD_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.frthProdCtrlCd, (String) getMap.get(FRTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.frthProdCtrlNm, (String) getMap.get(FRTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.fifthProdCtrlCd, (String) getMap.get(FIFTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.fifthProdCtrlNm, (String) getMap.get(FIFTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.sixthProdCtrlCd, (String) getMap.get(SIXTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.sixthProdCtrlNm, (String) getMap.get(SIXTH_PROD_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.svnthProdCtrlCd, (String) getMap.get(SVNTH_PROD_CTRL_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.svnthProdCtrlNm, (String) getMap.get(SVNTH_PROD_CTRL_NM));

        if (ZYPConstant.FLG_ON_Y.equals((String) getMap.get(INVTY_CTRL_FLG))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseIntgFlg, ZYPConstant.FLG_OFF_N);
        } else if (ZYPConstant.FLG_OFF_N.equals((String) getMap.get(INVTY_CTRL_FLG))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseIntgFlg, ZYPConstant.FLG_ON_Y);
        } else if ((String) getMap.get(INVTY_CTRL_FLG) == null || BLANK.equals((String) getMap.get(INVTY_CTRL_FLG))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseIntgFlg, ZYPConstant.FLG_OFF_N);
        }

        if ((String) getMap.get(PO_REQ_FLG) == null || BLANK.equals((String) getMap.get(PO_REQ_FLG))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndPoReqFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndPoReqFlg, (String) getMap.get(PO_REQ_FLG));
        }

        if (!ZYPCommonFunc.hasValue((String) getMap.get(SO_NUM))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndShpgIntgOnlyFlg, ZYPConstant.FLG_ON_Y);
        } else if ((ZYPCommonFunc.hasValue((String) getMap.get(SO_NUM)))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndShpgIntgOnlyFlg, ZYPConstant.FLG_OFF_N);
        } else if ((String) getMap.get(SO_NUM) == null || BLANK.equals((String) getMap.get(SO_NUM))) {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndShpgIntgOnlyFlg, ZYPConstant.FLG_OFF_N);
        }

    }

    /**
     * setVndInvDiscTermWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvDiscTermWrk VND_INV_DISC_TERM_WRKTMsg
     */
    private void setVndInvDiscTermWrkTMsg(Map<String, Object> getMap, VND_INV_DISC_TERM_WRKTMsg vndInvDiscTermWrk) {

        ZYPEZDItemValueSetter.setValue(vndInvDiscTermWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvDiscTermWrk.vndInvNum, (String) getMap.get(INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvDiscTermWrk.invCashDiscTermDtlNum, (String) getMap.get(INV_CASH_DISC_TERM_DTL_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvDiscTermWrk.vndInvCashDiscDueDt, (String) getMap.get(INV_CASH_DISC_DUE_DT));
        ZYPEZDItemValueSetter.setValue(vndInvDiscTermWrk.vndInvCashDiscRatioPct, (BigDecimal) getMap.get(INV_CASH_DISC_RATIO_PCT));
        ZYPEZDItemValueSetter.setValue(vndInvDiscTermWrk.vndInvCashDiscAmt, (BigDecimal) getMap.get(INV_CASH_DISC_AMT));
    }

    /**
     * setVndInvLicAccsWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvLicAccsWrk VND_INV_LIC_ACCS_WRKTMsg
     */
    private void setVndInvLicAccsWrkTMsg(Map<String, Object> getMap, VND_INV_LIC_ACCS_WRKTMsg vndInvLicAccsWrk) {

        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndInvNum, (String) getMap.get(INV_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndInvBolLineNum, (String) getMap.get(INV_BOL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndInvLineNum, (String) getMap.get(INV_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndInvLineSubNum, (String) getMap.get(INV_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndInvLineSubTrxNum, (String) getMap.get(INV_LINE_SUB_TRX_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndMdseCd, (String) getMap.get(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.licAccsNum, (String) getMap.get(LIC_ACCS_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndCpoOrdNum, (String) getMap.get(CPO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndCpoDtlLineNum, (String) getMap.get(CPO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.vndCpoDtlLineSubNum, (String) getMap.get(CPO_DTL_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(vndInvLicAccsWrk.ilmsLicAccsNum, (String) getMap.get(ILMS_LIC_ACCS_NUM));
    }

    /**
     * setCusaPartsInvWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvWrk VND_INV_WRKTMsg
     */
    private void setCusaPartsInvWrkTMsg(Map<String, Object> getMap, VND_INV_WRKTMsg vndInvWrk) {

        // VND_INV_WRK
        ZYPEZDItemValueSetter.setValue(vndInvWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvNum, (String) getMap.get(P_NUM_INVOICE_CPI));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invDt, (String) getMap.get(P_DATE_INVO));

        String[] str = {STR_20, STR_21, STR_22 };
        List<String> list = Arrays.asList(str);
        if (list.contains((String) getMap.get(P_CODE_ORDR_TYPE_PART))) {
            ZYPEZDItemValueSetter.setValue(vndInvWrk.invTpCd, INV_TP.CREDIT_MEMO);

        } else {
            ZYPEZDItemValueSetter.setValue(vndInvWrk.invTpCd, INV_TP.INVOICE);
        }

        String pDateInvo = (String) getMap.get(P_DATE_INVO);
        int pNetDays = (Integer) getMap.get(P_NET_DAYS);
        String netDueDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, pDateInvo, pNetDays);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.netDueDt, netDueDt);

        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndCpoOrdNum, (String) getMap.get(P_NUM_ORDR));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.ordDt, (String) getMap.get(P_DATE_ORDR_RCV));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndCpoOrdTpCd, (String) getMap.get(P_CODE_ORDR_TYPE_PART));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.billToCustCd, (String) getMap.get(P_CODE_CUST_CHRG_TO));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.sellToCustCd, (String) getMap.get(P_CODE_CUST_SOLD_TO));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndPmtTermStartDt, (String) getMap.get(P_DATE_INVO));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealNetAmt, (BigDecimal) getMap.get(P_AMT_INVO_TOT_CPI));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealSlsAmt, (BigDecimal) getMap.get(P_AMT_SHIP_CPI));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealFrtAmt, (BigDecimal) getMap.get(P_AMT_INVO_FRT_CPI));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealTaxAmt, (BigDecimal) getMap.get(P_AMT_SO_TAX_CPI));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invTotDealDiscAmt, (BigDecimal) getMap.get(P_AMT_DISCOUNT_CPI));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.dealCcyCd, "USD");
        ZYPEZDItemValueSetter.setValue(vndInvWrk.payerCustCd, (String) getMap.get(P_CODE_CUST_CHRG_TO));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invFirstCmntTxt, (String) getMap.get(P_MSG_SO_1));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.invScdCmntTxt, (String) getMap.get(P_MSG_SO_2));
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndInvProcStsCd, VND_INV_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.itrlIntfcId, this.interfaceId);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.cmProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(vndInvWrk.acctDt, (String) getMap.get(P_DATE_INVO));

        ZYPEZDItemValueSetter.setValue(vndInvWrk.psIssRqstFlg, ZYPConstant.FLG_OFF_N);

        // V3.1 Add QC#14714
        ZYPEZDItemValueSetter.setValue(vndInvWrk.vndPmtTermCashDiscCd, (String) getMap.get(P_CODE_TERM_S80));

    }

    /**
     * setCusaPartsInvBolWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvBolWrk VND_INV_BOL_WRKTMsg
     * @param vndInvBolLineNum int
     */
    private void setCusaPartsInvBolWrkTMsg(Map<String, Object> getMap, VND_INV_BOL_WRKTMsg vndInvBolWrk, int vndInvBolLineNum) {

        // VND_INV_BOL_WRK
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.vndInvNum, (String) getMap.get(P_NUM_INVOICE_CPSH));

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.vndInvBolLineNum, String.format("%03d", vndInvBolLineNum));

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.asnSoNum, (String) getMap.get(P_NUM_SO));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.ediPoOrdNum, (String) getMap.get(P_NUM_CUST_PO));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.vndInvtyLocCd, (String) getMap.get(P_CODE_WH_SHIP));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToCustCd, (String) getMap.get(P_CODE_CUST_SHIP_TO));

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToLocNm, (String) getMap.get(P_NAME_CUST_SHIP_TO_1));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToAddlLocNm, (String) getMap.get(P_NAME_CUST_SHIP_TO_2));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToFirstLineAddr, (String) getMap.get(P_SHIP_TO_EXT_ADDR_01));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToScdLineAddr, (String) getMap.get(P_SHIP_TO_EXT_ADDR_02));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToThirdLineAddr, (String) getMap.get(P_SHIP_TO_EXT_ADDR_03));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToFrthLineAddr, (String) getMap.get(P_SHIP_TO_EXT_ADDR_04));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToStCd, (String) getMap.get(P_CODE_SHIP_TO_STA));

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToPostCd, (String) getMap.get(P_ADDR_SHIP_TO_ZIP));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipToCtyAddr, (String) getMap.get(P_ADDR_SHIP_TO_CITY));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealSlsAmt, (BigDecimal) getMap.get(P_AMT_SHIP_CPSH));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealNetAmt, (BigDecimal) getMap.get(P_AMT_INVO_TOT_CPSH));

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealFrtAmt, (BigDecimal) getMap.get(P_AMT_INVO_FRT_CPSH));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealDiscAmt, (BigDecimal) getMap.get(P_AMT_DISCOUNT_CPSH));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDealHdlgChrgAmt, (BigDecimal) getMap.get(P_AMT_HANDLG_CHRG));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.shipDt, (String) getMap.get(P_DATE_SHIP_PRSS));
        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.totBolDealTaxAmt, (BigDecimal) getMap.get(P_AMT_SO_TAX_CPSH));

        ZYPEZDItemValueSetter.setValue(vndInvBolWrk.dropShipFlg, ZYPConstant.FLG_OFF_N);

    }

    /**
     * setCusaPartsInvLineWrkTMsg
     * @param getMap Map<String, Object>
     * @param vndInvLineWrk VND_INV_LINE_WRKTMsg
     * @param lineNum int
     * @param vndInvBolLineNum int
     */
    private void setCusaPartsInvLineWrkTMsg(Map<String, Object> getMap, VND_INV_LINE_WRKTMsg vndInvLineWrk, int lineNum, int vndInvBolLineNum) {

        // VND_INV_LINE_WRK
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvNum, (String) getMap.get(P_NUM_INVOICE_CPSH));

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvBolLineNum, String.format("%03d", vndInvBolLineNum));

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineNum, String.valueOf(lineNum));

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineSubNum, STR_1);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineSubTrxNum, STR_1);

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.ediPoOrdDtlLineNum, (String) getMap.get(P_NUM_LINE));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndCpoOrdNum, (String) getMap.get(P_NUM_ORDR));

        String[] str = {STR_20, STR_21, STR_22 };
        List<String> list = Arrays.asList(str);
        if (list.contains((String) getMap.get(P_CODE_ORDR_TYPE_PART))) {
            String pPartsNum = (String) getMap.get(P_PARTS_NUM);
            if (pPartsNum != null && !pPartsNum.equals(BLANK)) {
                ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseCd, (String) getMap.get(P_PARTS_NUM_FLIP));
            } else {
                ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseCd, (String) getMap.get(P_PARTS_NUM_SO));
            }

        } else {
            ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseCd, (String) getMap.get(P_PARTS_NUM_SO));
        }

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.ordQty, (BigDecimal) getMap.get(P_QTY_SHIP_SCHED));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.shipQty, (BigDecimal) getMap.get(P_QTY_SHIP));

        BigDecimal pPrcSale = (BigDecimal) getMap.get(P_PRC_SALE);
        BigDecimal pPrcDiscount = (BigDecimal) getMap.get(P_PRC_DISCOUNT);
        BigDecimal dealNetUnitPrcAmt = pPrcSale.subtract(pPrcDiscount);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealNetUnitPrcAmt, dealNetUnitPrcAmt);

        BigDecimal vndInvLineDealNetAmt = dealNetUnitPrcAmt.multiply((BigDecimal) getMap.get(P_QTY_SHIP));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndInvLineDealNetAmt, vndInvLineDealNetAmt);

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealDiscUnitPrcAmt, (BigDecimal) getMap.get(P_PRC_DISCOUNT));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.mercStmtOnInvTxt, (String) getMap.get(P_DESC_PART));
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealGrsUnitPrcAmt, (BigDecimal) getMap.get(P_PRC_SALE));

        BigDecimal pQtyShip = (BigDecimal) getMap.get(P_QTY_SHIP);
        BigDecimal dealGrsTotPrcAmt = pPrcSale.multiply(pQtyShip);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.dealGrsTotPrcAmt, dealGrsTotPrcAmt);

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.uomCd, EA);

        if (list.contains((String) getMap.get(P_CODE_ORDR_TYPE_PART))) {
            String pCustPartNum = (String) getMap.get(P_CUST_PART_NUM);
            if (pCustPartNum != null) {
                if (pCustPartNum.equals(BLANK)) {
                    if ((String) getMap.get(P_PARTS_NUM_FLIP) != null) {
                        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_FLIP));
                    } else {
                        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_SO));
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_CUST_PART_NUM));
                }

            } else {
                String pPartsNumOrig = (String) getMap.get(P_PARTS_NUM_ORIG);
                if (pPartsNumOrig.equals(BLANK)) {
                    if ((String) getMap.get(P_PARTS_NUM_FLIP) != null) {
                        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_FLIP));
                    } else {
                        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_SO));
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_ORIG));
                }

            }

        } else {
            String pCustPartNum = (String) getMap.get(P_CUST_PART_NUM);
            if (pCustPartNum != null) {
                if (pCustPartNum.equals(BLANK)) {
                    ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_SO));
                } else {
                    ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_CUST_PART_NUM));
                }
            } else {
                String pPartsNumOrig = (String) getMap.get(P_PARTS_NUM_ORIG);
                if (pPartsNumOrig != null) {
                    if (pPartsNumOrig.equals(BLANK)) {
                        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_SO));
                    } else {
                        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.custMdseCd, (String) getMap.get(P_PARTS_NUM_ORIG));
                    }
                }

            }
        }

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndMdseIntgFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndPoReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.vndShpgIntgOnlyFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(vndInvLineWrk.manPrcFlg, ZYPConstant.FLG_OFF_N);

    }

    /**
     * selectSetPoLineNum
     * @param custIssPoNum String
     * @param ediNum String
     * @param mdseCd String
     * @return String
     */
    private String selectSetPoLineNum(String custIssPoNum, String ediNum, String mdseCd) {

        String setEdiPoOrdDtlLineNum = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            if (custIssPoNum != null) {
                queryParam.put("custIssPoNum", custIssPoNum);
            } else {
                queryParam.put("custIssPoNum", BLANK);
            }
            if (ediNum != null) {
                queryParam.put("ediNum", ediNum);
            } else {
                queryParam.put("ediNum", BLANK);
            }
            if (mdseCd != null) {
                queryParam.put("mdseCd", mdseCd);
            } else {
                queryParam.put("mdseCd", BLANK);
            }

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get
            stmt = this.ssmLLClient.createPreparedStatement("selectSetPoLineNum", queryParam, ssmEexcParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                setEdiPoOrdDtlLineNum = rs.getString(PO_ORD_DTL_LINE_NUM);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        return setEdiPoOrdDtlLineNum;
    }

}
