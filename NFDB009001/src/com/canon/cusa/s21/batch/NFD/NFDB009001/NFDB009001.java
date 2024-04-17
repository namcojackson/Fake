/*
 * <Pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB009001;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CLT_DUN_LTR_WRKTMsg;
import business.db.CLT_DUN_LTR_WRKTMsgArray;

import com.canon.cusa.s21.batch.NFD.NFDB009001.constant.NFDB009001Constant;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeBuilder;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeType;
import com.canon.cusa.s21.framework.ZYP.aspose.pdf.S21PDFDocument;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DUN_LTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_PRINT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.S21ExecutionStatus;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.cups.S21CUPSPrintOption;
import com.canon.cusa.s21.framework.printing.eip.S21CUPSFileOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21EmailOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 * <pre>
 * Dunning Letter e-mail sending / print request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/21   CSAI            K.Uramori       Update          QC#5751
 * 2016/04/18   Fujitsu         C.Naito         Update          QC#7166
 * 2016/04/22   CSAI            K.Uramori       Update          QC#7311
 * 2016/04/28   Fujitsu         C.Naito         Update          QC#7310
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13004
 * 2016/11/30   Fujitsu         S.Yoshida       Update          QC#11128
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/07/11   Fujitsu         S.Ohki          Update          QC#27002
 * 2018/08/21   CITS            K.Kameoka       Update          QC#25825
 * 2019/01/07   Fujitsu         H.Ikeda         Update          QC#29731
 * 2021/05/10   CITS            G.Delgado       Update          QC#58340
 * 2021/05/24   CITS            K.Suzuki        Update          QC#58340
 * 2021/06/11   CITS            K.Suzuki        Update          QC#58340
 * 2021/06/23   CITS            K.Suzuki        Update          QC#58340-1
 * 2021/07/12   CITS            K.Suzuki        Update          QC#58945
 * 2021/07/14   CITS            K.Suzuki        Update          QC#58340-2
 * 2021/07/28   CITS            G.Delgado       Update          QC#58340-3
 * 2021/08/19   CITS            K.Suzuki        Update          QC#59070
 * 2021/09/14   CITS            K.Suzuki        Update          QC#59195
 * 2021/09/14   CITS            K.Suzuki        Update          QC#59188
 * 2021/09/21   CITS            K.Suzuki        Update          QC#59212
 * 2024/03/29   CITS            T.Aizawa        Update          QC#63832
 * </pre>
 */
public class NFDB009001 extends S21BatchMain implements NFDB009001Constant {

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Batch Process Date */
    private String batProcDt;

    /** Default email address */
    private String defEmlAdd = "";

    /** Default email address list */
    private List<String> defEmlAddList = new ArrayList<String>();

    //QC#25825 Mod Start for CUPS    
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient;

    S21EIPPrintingService service = new S21EIPPrintingService();
    //QC#25825 Mod End for CUPS

    // START 2021/05/10 G.Delgado [QC#58340,ADD]
    /** Process Mode */
    private String processMode;
    // END 2021/05/10 G.Delgado [QC#58340,ADD]

    ///** processPk */
    //[QC#7166] DELETE  //private long processPk = 0;

    // START 2021/09/14 K.Suzuki [QC#59188,ADD]
    /** Cups Count(Def:50) */
    private int cupsPageCnt = 50;
    // END   2021/09/14 K.Suzuki [QC#59188,ADD]

    /**
     * It is the main method that is called from the batch processing.
     * @param args Argument
     */
    public static void main(String[] args) {

        new NFDB009001().executeBatch(NFDB009001.class.getSimpleName());
    }

    /**
     * initRoutine
     */
    @Override
    protected void initRoutine() {

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;

        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9025E, new String[] {ERR_PRM_GLBL_CMPY_CD });
        }

        this.batProcDt = ZYPDateUtil.getBatProcDate(this.glblCmpyCd, BUSINESS_ID);
        if (!ZYPCommonFunc.hasValue(this.batProcDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9025E, new String[] {ERR_PRM_BATCH_PRC_DT });
        }

        // START 2021/05/10 G.Delgado [QC#58340,ADD]
        this.processMode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.processMode)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9025E, new String[] {ERR_PRM_VAR_USER1 });
        }
        if (!Arrays.asList(PROCESS_MODE_PDF_CREATION, PROCESS_MODE_PDF_PRINT_REQUEST).contains(this.processMode)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9026E, new String[] {ERR_PRM_VAR_USER1 });
        }

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END 2021/05/10 G.Delgado [QC#58340,ADD]

        this.defEmlAdd = ZYPCodeDataUtil.getVarCharConstValue(AR_CLT_DEF_EML_ADDR, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.defEmlAdd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(ZZZM9025E, new String[] {ERR_PRM_DEF_CLT_EMAIL_ADDRESS });
        }
        // split the email addresses
        divDefEmlAdd();
    }

    /**
     * divide Default Email Address
     */
    private void divDefEmlAdd() {
        StringTokenizer st = new StringTokenizer(this.defEmlAdd, DIV_CONMA);

        while (st.hasMoreTokens()) {
            this.defEmlAddList.add(st.nextToken());
        }

    }

    /**
     * mainRoutine
     */
    @Override
    protected void mainRoutine() {

        // START 2021/05/10 G.Delgado [QC#58340,MOD]
        if (PROCESS_MODE_PDF_CREATION.equals(this.processMode)) {
            executePdfCreationProcess();
        } else if (PROCESS_MODE_PDF_PRINT_REQUEST.equals(this.processMode)) {
            executePdfPrintRequestProcess();
        }
        // END 2021/05/10 G.Delgado [QC#58340,MOD]
    }

    /**
     * termRoutine
     */
    @Override
    protected void termRoutine() {
        int totalCount = this.totalCommitCount + this.totalErrCount;
        setTermState(this.termCd, this.totalCommitCount, this.totalErrCount, totalCount);
    }

    /**
     * Report Process
     * @param rs ResultSet
     * @return BigDecimal
     * @throws SQLException
     */
    private BigDecimal reportProcess(ResultSet rs) throws SQLException {

        long requestPk = 0;
        // START 2021/07/12 K.Suzuki [QC#58945,ADD]
        boolean destPrintFlg = false;
        // START 2021/07/12 K.Suzuki [QC#58945,ADD]

        // Get From Address
        String fromAddr = getCollectorMailAddress(rs.getString(CLT_PSN_CD));
        if (fromAddr == null) {
            fromAddr = getDefaultCollectorMailAddress();
        }

        // Get To Address
        List<String> mailAddressList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(rs.getString(DS_CTAC_PNT_VAL_TXT_BC))) {
            mailAddressList.add(rs.getString(DS_CTAC_PNT_VAL_TXT_BC));

        } else if (ZYPCommonFunc.hasValue(rs.getString(DS_CTAC_PNT_VAL_TXT_AC))) {
            mailAddressList.add(rs.getString(DS_CTAC_PNT_VAL_TXT_AC));

        } else {
            mailAddressList.add(fromAddr);
            // START 2021/07/12 K.Suzuki [QC#58945,ADD]
            destPrintFlg = true;
            // START 2021/07/12 K.Suzuki [QC#58945,ADD]
        }

        // Create Subject and Body
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, rs.getString(DUN_ML_TMPL_ID));
        if (template == null) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFBM0184E, new String[] {rs.getString(DUN_ML_TMPL_ID) });
        }
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_CLT_ACCT_CD, rs.getString(CLT_ACCT_CD));

        // Set Report Input Parameter
        S21ReportRequestBean request = new S21ReportRequestBean(rs.getString(RPT_ID));
        S21InputParameter inputParam = request.getInputParamBeanInstance();

        String sysTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        String titleBase = makeTitleBase(rs.getString("BILL_TO_CUST_CD"), sysTimeStamp);
        String rptTtlNm = rs.getString(RPT_TTL_NM) + " " + titleBase;
        request.setRptTtlNm(rptTtlNm);
        request.setRptArcFlg(true);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);

        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("EZCANCELFLAG", "0");
        inputParam.addReportParameter("GLBL_CMPY_CD", this.glblCmpyCd);
        inputParam.addReportParameter("BILL_TO_CUST_CD", rs.getString(BILL_TO_CUST_CD));
        inputParam.addReportParameter("INV_PAST_DUE_FLG", ZYPConstant.FLG_ON_Y);

        request.setInputParamBean(inputParam);

        // START 2021/05/10 G.Delgado [QC#58340,ADD]
        String fileName = S21StringUtil.concatStrings((String) rs.getString("BILL_TO_CUST_CD"), UNDERSCORE,
                ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN));
        // END 2021/05/10 G.Delgado [QC#58340,ADD]
        // START 2021/07/12 K.Suzuki [QC#58945,MOD]
        if (destPrintFlg) {
            // END 2021/07/12 K.Suzuki [QC#58945,MOD]

            // Set Print out option for CUPS
            // PDF file name if needed,
            // If dosen't set file name, automatically it creates like 
            // e.g) /NWC/20171206/'ReportID'_'yyyyMMddHHmmssSSS'_UUID.pdf
            S21CUPSFileOutputParameter cupsFileoutParam = request.getCUPSFileOutParamBeanInstance();
            cupsFileoutParam.setFileName(fileName);
            // END 2021/05/10 G.Delgado [QC#58340,MOD]
            request.setCUPSFileOutParamBean(cupsFileoutParam);
            // START 2021/07/12 K.Suzuki [QC#58945,MOD]
        } else {
            // END 2021/07/12 K.Suzuki [QC#58945,MOD]

            // Set e-mail option
            S21EmailOutputParameter emailOutParam = request.getEmailOutParamInstance();
            emailOutParam.setBranchNo(rs.getString(RPT_BR_NUM_EML));
            emailOutParam.setSubject(template.getSubject());
            emailOutParam.setBodyText(template.getBody());
            for (String toAddr : mailAddressList) {
                emailOutParam.addToAddress(toAddr);
            }

            emailOutParam.setSenderAddress(fromAddr);
            // START 2021/05/10 G.Delgado [QC#58340,MOD]
            // emailOutParam.setAttachFileName(ATTACH_FILE_NM + EXTENSION_PDF);
            emailOutParam.setAttachFileName(S21StringUtil.concatStrings(fileName, EXTENSION_PDF));
            // END 2021/05/10 G.Delgado [QC#58340,MOD]

            request.setEmailOutParamBean(emailOutParam);
        }

        //********************************
        // Create Report
        //********************************
        requestPk = service.createReportByAsync(request);

        S21InfoLogOutput.println("||||||||||||| Request PK for print & e-Mail: " + requestPk + " |||||||||||||||||||");

        return BigDecimal.valueOf(requestPk);
    }

    private String makeTitleBase(String billToCustCd, String sysTimeStamp) {
        StringBuilder rptTtl = new StringBuilder();
        rptTtl.append("BilltoCustomer ");
        rptTtl.append(billToCustCd);
        rptTtl.append(" Time ");
        rptTtl.append(sysTimeStamp);
        return rptTtl.toString();
    }

    /**
     * setParamForCltDunLtrWrk
     * @return Map<String, Object>
     */
    private Map<String, Object> setParamForCltDunLtrWrk() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        String[] cltPrintStsCdList = new String[] {CLT_PRINT_STS.CREATED, CLT_PRINT_STS.ERROR };
        paramMap.put("cltPrintStsCdList", cltPrintStsCdList);
        paramMap.put("ctacTpCdIsCreditOrCollectons", CTAC_TP.CREDIT_OR_COLLECTONS);
        paramMap.put("dsCtacPntTpIsEmailWork", DS_CTAC_PNT_TP.EMAIL_WORK);
        return paramMap;
    }

    /**
     * getCltDunLtrWrk
     * @return PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement getCltDunLtrWrk() throws SQLException {
        return ssmLLClient.createPreparedStatement("getCltDunLtrWrk", setParamForCltDunLtrWrk(), getExecPrm());
    }

    /**
     * get S21SsmExecutionParameter
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * getCltDunLtrWrk
     * @param cltStrgyWrkItemTrx BigDecimal
     * @return CLT_DUN_LTR_WRKTMsgArray
     */
    private CLT_DUN_LTR_WRKTMsgArray getCltDunLtrWrk(BigDecimal cltStrgyWrkItemTrx) {
        CLT_DUN_LTR_WRKTMsg tMsg = new CLT_DUN_LTR_WRKTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("cltStrgyWrkItemTrxPk01", cltStrgyWrkItemTrx);
        return (CLT_DUN_LTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(tMsg);
    }

    // START 2018/01/18 [QC#21734, ADD]
    private String getCollectorMailAddress(String cltPsnCd) {
        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(cltPsnCd);
        if (userInfo == null) {
            return null;
        }
        if (ZYPCommonFunc.hasValue(userInfo.getEmailAddress())) {
            return userInfo.getEmailAddress();
        }

        S21UserInfo superVisorInfo = getUserProfileService().getUserInfoFor(userInfo.getUserDetails().getManagerId());
        if (superVisorInfo == null) {
            return null;
        }
        if (ZYPCommonFunc.hasValue(superVisorInfo.getEmailAddress())) {
            return superVisorInfo.getEmailAddress();
        }

        S21UserInfo managerInfo = getUserProfileService().getUserInfoFor(superVisorInfo.getUserDetails().getManagerId());
        if (managerInfo == null) {
            return null;
        }
        if (ZYPCommonFunc.hasValue(managerInfo.getEmailAddress())) {
            return managerInfo.getEmailAddress();
        }

        return null;
    }

    private String getDefaultCollectorMailAddress() {
        if (!defEmlAddList.isEmpty()) {
            return defEmlAddList.get(0);
        }
        return null;
    }
    // END   2018/01/18 [QC#21734, ADD]

    // START 2021/05/10 G.Delgado [QC#58340,ADD]
    /**
     * executePdfCreationProcess
     */
    private void executePdfCreationProcess() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = getCltDunLtrWrk();
            rs = ps.executeQuery();

            BigDecimal requestPk = BigDecimal.ZERO;
            // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
            Map<BigDecimal, String> cltStrgyWrkItemTrxMapForPrint = new HashMap<BigDecimal, String>();
            Map<BigDecimal, String> cltStrgyWrkItemTrxMapForEmail = new HashMap<BigDecimal, String>();
            // END 2021/07/28 G.Delgado [QC#58340-3,MOD]

            // Loop acquired dunning letter work data
            while (rs.next()) {
                // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
                // START 2021/08/19 K.Suzuki [QC#59070,MOD]
                //if (ZYPConstant.FLG_ON_Y.equals(rs.getString(INV_PAST_DUE_FLG)) || CLT_DUN_LTR_TP.FINAL.equals(rs.getString(CLT_DUN_LTR_TP_CD))) {
                if (ZYPConstant.FLG_ON_Y.equals(rs.getString(INV_PAST_DUE_FLG))
                        || CLT_DUN_LTR_TP.FINAL.equals(rs.getString(CLT_DUN_LTR_TP_CD))
                        || CLT_DUN_LTR_TP.MANUAL_FINAL.equals(rs.getString(CLT_DUN_LTR_TP_CD))) {
                // END   2021/08/19 K.Suzuki [QC#59070,MOD]
                // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
                    requestPk = reportProcess(rs);

                    // START 2021/07/28 G.Delgado [QC#58340-3,ADD]
                    String procInvPastDueFlg = null;
                    // START 2021/08/19 K.Suzuki [QC#59070,MOD]
                    //if (!CLT_DUN_LTR_TP.FINAL.equals(rs.getString(CLT_DUN_LTR_TP_CD))) {
                    if (!CLT_DUN_LTR_TP.FINAL.equals(rs.getString(CLT_DUN_LTR_TP_CD))
                            && !CLT_DUN_LTR_TP.MANUAL_FINAL.equals(rs.getString(CLT_DUN_LTR_TP_CD))) {
                    // END   2021/08/19 K.Suzuki [QC#59070,MOD]
                        // if not Final Dunning Letter, process only entries with past due date
                        procInvPastDueFlg = ZYPConstant.FLG_ON_Y;
                    }
                    // END 2021/07/28 G.Delgado [QC#58340-3,ADD]

                    // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
                    updateCltDunLtrRqstNum(rs.getBigDecimal(CLT_STRGY_WRK_ITEM_TRX_PK), requestPk, procInvPastDueFlg);
                    // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
                    // START 2021/07/12 K.Suzuki [QC#58945,MOD]
                    if (isPrintClrDunLtrWrk(rs)) {
                        // END 2021/07/12 K.Suzuki [QC#58945,MOD]
                        // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
                        cltStrgyWrkItemTrxMapForPrint.put(rs.getBigDecimal(CLT_STRGY_WRK_ITEM_TRX_PK), procInvPastDueFlg);
                        // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
                        // START 2021/07/12 K.Suzuki [QC#58945,MOD]
                    } else {
                        // END 2021/07/12 K.Suzuki [QC#58945,MOD]
                        // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
                        cltStrgyWrkItemTrxMapForEmail.put(rs.getBigDecimal(CLT_STRGY_WRK_ITEM_TRX_PK), procInvPastDueFlg);
                        // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
                    }

                    totalCommitCount++;
                }
            }

            if (totalCommitCount > 0) {
                long processPk = service.activateAsyncReportJob();
                S21InfoLogOutput.println("||||||||||||| Process Pk Print or e-Mail: " + processPk + " |||||||||||||||||||");
                // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
                updateCltDunLtrWrk(cltStrgyWrkItemTrxMapForPrint, BigDecimal.valueOf(processPk), CLT_PRINT_STS.PRINTED, true);
                updateCltDunLtrWrk(cltStrgyWrkItemTrxMapForEmail, BigDecimal.valueOf(processPk), CLT_PRINT_STS.PRINTED, false);
                // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * executePdfPrintRequestProcess
     */
    private void executePdfPrintRequestProcess() {

        BigDecimal processPk = getEipRptProcLogPk();
        if (BigDecimal.ZERO.compareTo(processPk) == 0) {
            return;
        }

        // START 2021/07/14 K.Suzuki [QC#58340-2,ADD]
        // get AR_DUN_LTR_ADD_BARCODE_FLG
        String addBarCodeFlg = ZYPCodeDataUtil.getVarCharConstValue(AR_DUN_LTR_ADD_BARCODE_FLG, this.glblCmpyCd);
        // END 2021/07/14 K.Suzuki [QC#58340-2,ADD]
        // START 2021/09/14 K.Suzuki [QC#59188,ADD]
        BigDecimal tmpVal = ZYPCodeDataUtil.getNumConstValue(AR_DUN_LTR_CUPS_PAGE_COUNT, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(tmpVal)) {
            this.cupsPageCnt = tmpVal.intValue();
        }
        // END   2021/09/14 K.Suzuki [QC#59188,ADD]

        S21ExecutionStatus pdfCreationResult = service.invokeAsyncPollingBatchTypeEIP(processPk.longValue());
        if (pdfCreationResult.isFailure()) {
            List<Map<String, Object>> eipResultList = getEIPResultChk(processPk);
            if (eipResultList != null) {
                for (Map<String, Object> eipResultMap : eipResultList) {
                    S21InfoLogOutput.println("[Warning][EIP Report Output Error]"
                            + " EIP_RPT_RQST_PK=" + (BigDecimal) eipResultMap.get(EIP_RPT_RQST_PK)
                            + " RPT_RQST_STS_TXT=" + (String) eipResultMap.get(RPT_RQST_STS_TXT)
                            + " RPT_TTL_NM=" + (String) eipResultMap.get(RPT_TTL_NM));
                }
            }
        }

        List<Map<String, Object>> eipRptRqstPkList = getEipRptRqstPkListToOutput(processPk);
        if (eipRptRqstPkList != null) {
            // START 2021/09/14 K.Suzuki [QC#59188,ADD]
            BigDecimal prevEipRptRqstPk = new BigDecimal(0);
            // END   2021/09/14 K.Suzuki [QC#59188,ADD]
            for (Map<String, Object> rstMap : eipRptRqstPkList) {
                BigDecimal eipRptRqstPk = (BigDecimal) rstMap.get(EIP_RPT_RQST_PK);
                BigDecimal cltStrgyWrkItemTrxPk = (BigDecimal) rstMap.get(CLT_STRGY_WRK_ITEM_TRX_PK);
                // START 2021/09/14 K.Suzuki [QC#59188,ADD]
                if (prevEipRptRqstPk.compareTo(eipRptRqstPk) == 0) {
                    continue;
                }
                // END   2021/09/14 K.Suzuki [QC#59188,ADD]
                // START 2021/07/14 K.Suzuki [QC#58340-2,ADD]
                if (ZYPConstant.FLG_ON_Y.equals(addBarCodeFlg)) {
                    String filePath = service.getFilePathForCupsPrint(eipRptRqstPk.longValue(), 0);
                    if (ZYPCommonFunc.hasValue(filePath)) {
                        try {
                            String uniqueIdentifier = ZYPMaxTenDigitsNumbering.getUniqueID("CUPS_UNIQ_CD_2D"); //from AUTO_SQ_TEN_DIGIT
                            addBarcodeImageToPdf(uniqueIdentifier, filePath);

                            updateRptOtptCpltFlg(cltStrgyWrkItemTrxPk, eipRptRqstPk);
                            // START 2021/09/21 K.Suzuki [QC#59212,ADD]
                            commit();
                            // END   2021/09/21 K.Suzuki [QC#59212,ADD]

                        } catch (Exception e) {
                            e.printStackTrace();
                            S21InfoLogOutput.println("Failed to add barcode image:" + filePath + ":" + e.getMessage());
                        }
                    } else {
                        S21InfoLogOutput.println("PDF file does not exist: EIP_RPT_RQST_PK=" + eipRptRqstPk);
                    }
                } else {
                    updateRptOtptCpltFlg(cltStrgyWrkItemTrxPk, eipRptRqstPk);
                    // START 2021/09/21 K.Suzuki [QC#59212,ADD]
                    commit();
                    // END   2021/09/21 K.Suzuki [QC#59212,ADD]
                }
                // END 2021/07/14 K.Suzuki [QC#58340-2,ADD]
                // START 2021/09/14 K.Suzuki [QC#59188,ADD]
                prevEipRptRqstPk = eipRptRqstPk;
                // END   2021/09/14 K.Suzuki [QC#59188,ADD]
            }
        }

        commit();

        eipRptRqstPkList = getEipRptRqstPkListToPrintRequest(processPk);
        if (eipRptRqstPkList != null && !eipRptRqstPkList.isEmpty()) {
            S21CUPSPrintOption printOption = new S21CUPSPrintOption();
            // START 2024/03/29 t.aizawa [QC#63832,MOD]
            // printOption.setFitToPage();
            printOption.setDoubleSideLongEdge();
            // END   2024/03/29 t.aizawa [QC#63832,MOD]

            List<Long> rptRqstPkList = new ArrayList<Long>();
            List<BigDecimal> rptRqstDunPkList = new ArrayList<BigDecimal>();
            // START 2021/09/14 K.Suzuki [QC#59188,ADD]
            String printerName = "";
            String prevPrinterName = "";
            BigDecimal prevEipRptRqstPk = new BigDecimal(0);
            // END   2021/09/14 K.Suzuki [QC#59188,ADD]
            int sCnt = 0;

            try {
                // START 2021/09/14 K.Suzuki [QC#59188,MOD]
                for (Map<String, Object> rstMap : eipRptRqstPkList) {
                    BigDecimal eipRptRqstPk = (BigDecimal) rstMap.get(EIP_RPT_RQST_PK);
                    if (prevEipRptRqstPk.compareTo(eipRptRqstPk) == 0) {
                        continue;
                    }

                    // START 2021/07/12 K.Suzuki [QC#58945,MOD]
                    //printerName = service.getPrinterQueueName((String) rstMap.get(RPT_ID), (String) rstMap.get(RPT_BR_NUM_EML));
                    printerName = service.getPrinterQueueName((String) rstMap.get(RPT_ID), (String) rstMap.get(RPT_BR_NUM_PRINT));
                    // END 2021/07/12 K.Suzuki [QC#58945,MOD]
                    if (sCnt == 0) {
                        prevPrinterName = printerName;
                    }
                    // if printer changed, mergeAndPrintRequestToCups
                    if (!prevPrinterName.equals(printerName)) {
                        service.mergeAndPrintRequestToCups(rptRqstPkList, printerName, printOption);
                        sCnt = sCnt + rptRqstPkList.size();
                        updateRptPrintRqstCpltFlg(rptRqstDunPkList);
                        // START 2021/09/21 K.Suzuki [QC#59212,ADD]
                        commit();
                        // END   2021/09/21 K.Suzuki [QC#59212,ADD]
                        rptRqstPkList.clear();
                        rptRqstDunPkList.clear();
                    }
                    rptRqstPkList.add(eipRptRqstPk.longValue());
                    rptRqstDunPkList.add(eipRptRqstPk);

                    // if it comes to merge size, mergeAndPrintRequestToCups
                    if (rptRqstPkList.size() == this.cupsPageCnt) {
                        service.mergeAndPrintRequestToCups(rptRqstPkList, printerName, printOption);
                        sCnt = sCnt + rptRqstPkList.size();
                        updateRptPrintRqstCpltFlg(rptRqstDunPkList);
                        // START 2021/09/21 K.Suzuki [QC#59212,ADD]
                        commit();
                        // END   2021/09/21 K.Suzuki [QC#59212,ADD]
                        rptRqstPkList.clear();
                        rptRqstDunPkList.clear();
                    }

                    prevPrinterName = printerName;
                    prevEipRptRqstPk = eipRptRqstPk;
                }
                if (rptRqstPkList.size() > 0) {
                    service.mergeAndPrintRequestToCups(rptRqstPkList, printerName, printOption);
                    sCnt = sCnt + rptRqstPkList.size();
                    updateRptPrintRqstCpltFlg(rptRqstDunPkList);
                    // START 2021/09/21 K.Suzuki [QC#59212,ADD]
                    commit();
                    // END   2021/09/21 K.Suzuki [QC#59212,ADD]
                    rptRqstPkList.clear();
                    rptRqstDunPkList.clear();
                }
                // END   2021/09/14 K.Suzuki [QC#59188,MOD]
            } catch (Exception e) {
                this.termCd = TERM_CD.ABNORMAL_END;
                this.totalErrCount = eipRptRqstPkList.size() - sCnt;
                rollback();
                e.printStackTrace();
                return;
            }

            this.totalCommitCount = sCnt;
        }

        commit();

        // START 2021/09/21 K.Suzuki [QC#59212,ADD]
        eipRptRqstPkList = getEipRptRqstPkListToPrintComplete(processPk);
        if (eipRptRqstPkList != null && !eipRptRqstPkList.isEmpty()) {
            for (Map<String, Object> rstMap : eipRptRqstPkList) {
                BigDecimal eipRptRqstPk = (BigDecimal) rstMap.get(EIP_RPT_RQST_PK);
                S21InfoLogOutput.println("Print Failed. EIP_RPT_RQST_PK=" + eipRptRqstPk);
            }
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFDM0058E);
        }
        // END   2021/09/21 K.Suzuki [QC#59212,ADD]
    }

    /**
     * getEipRptProcLogPk
     * @return BigDecimal
     */
    private BigDecimal getEipRptProcLogPk() {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltDunLtrIssDt", this.batProcDt);
        queryParam.put("rptOtptCpltFlg", ZYPConstant.FLG_ON_Y);

        BigDecimal eipRptProcLogPk = (BigDecimal) ssmBatchClient.queryObject("getEipRptProcLogPk", queryParam);
        if (eipRptProcLogPk == null) {
            return BigDecimal.ZERO;
        }
        return eipRptProcLogPk;
    }

    /**
     * getEIPResultChk
     * @param processPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEIPResultChk(BigDecimal processPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("eipRptProcLogPk", processPk);
        queryParam.put("rptRqstStsTxtSuccess", RPT_RQST_STS_TXT_SUCCESS);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getEIPResultChk", queryParam);
    }

    /**
     * getEipRptRqstPkListToOutput
     * @param processPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEipRptRqstPkListToOutput(BigDecimal processPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltDunLtrIssDt", this.batProcDt);
        queryParam.put("eipRptProcLogPk", processPk);
        queryParam.put("cltPrintStsCd", CLT_PRINT_STS.PRINTED);
        queryParam.put("rptOtptCpltFlg", ZYPConstant.FLG_OFF_N);
        queryParam.put("rptPrintRqstCpltFlg", ZYPConstant.FLG_OFF_N);
        queryParam.put("rptRqstStsTxtSuccess", RPT_RQST_STS_TXT_SUCCESS);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSuccessEIPRptRqstPk", queryParam);
    }

    /**
     * getEipRptRqstPkListToPrintRequest
     * @param processPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEipRptRqstPkListToPrintRequest(BigDecimal processPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltDunLtrIssDt", this.batProcDt);
        queryParam.put("eipRptProcLogPk", processPk);
        queryParam.put("cltPrintStsCd", CLT_PRINT_STS.PRINTED);
        queryParam.put("rptOtptCpltFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("rptPrintRqstCpltFlg", ZYPConstant.FLG_OFF_N);
        queryParam.put("rptRqstStsTxtSuccess", RPT_RQST_STS_TXT_SUCCESS);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSuccessEIPRptRqstPk", queryParam);
    }

    // START 2021/09/21 K.Suzuki [QC#59212,ADD]
    /**
     * getEipRptRqstPkListToPrintComplete
     * @param processPk BigDecimal
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEipRptRqstPkListToPrintComplete(BigDecimal processPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("cltDunLtrIssDt", this.batProcDt);
        queryParam.put("eipRptProcLogPk", processPk);
        queryParam.put("cltPrintStsCd", CLT_PRINT_STS.PRINTED);
        queryParam.put("rptEmlOtptFlg", ZYPConstant.FLG_OFF_N);
        queryParam.put("rptPrintOtptFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("rptOtptCpltFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("rptPrintRqstCpltFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("rptRqstStsTxtSuccess", RPT_RQST_STS_TXT_SUCCESS);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getEipRptRqstPkListToPrintComplete", queryParam);
    }
    // END   2021/09/21 K.Suzuki [QC#59212,ADD]

    /**
     * updateCltDunLtrRqstNum
     * @param cltStrgyWrkItemTrx BigDecimal
     * @param requestPk BigDecimal
     * @param invPastDueFlg String
     */
    // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
    private void updateCltDunLtrRqstNum(BigDecimal cltStrgyWrkItemTrx, BigDecimal requestPk, String invPastDueFlg) {
    // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
        if (!ZYPCommonFunc.hasValue(cltStrgyWrkItemTrx)) {
            return;
        }
        CLT_DUN_LTR_WRKTMsgArray tMsgArray = getCltDunLtrWrk(cltStrgyWrkItemTrx);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            CLT_DUN_LTR_WRKTMsg tMsg = tMsgArray.no(i);
            // START 2021/07/28 G.Delgado [QC#58340-3,ADD]
            if (ZYPCommonFunc.hasValue(invPastDueFlg) && !invPastDueFlg.equals(tMsg.invPastDueFlg.getValue())) {
                // START 2021/09/14 K.Suzuki [QC#59195,ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.cltPrintStsCd, CLT_PRINT_STS.PRINT_SKIPPED);
                S21FastTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.termCd = TERM_CD.ABNORMAL_END;
                    throw new S21AbendException(NFDM0004E, new String[] {ERR_PRM_CLT_DUN_LTR_WRK });
                }
                // END   2021/09/14 K.Suzuki [QC#59195,ADD]
                continue;
            }
            // END 2021/07/28 G.Delgado [QC#58340-3,ADD]

            ZYPEZDItemValueSetter.setValue(tMsg.cltDunLtrRqstNum, requestPk);
            S21FastTBLAccessor.update(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                this.termCd = TERM_CD.ABNORMAL_END;
                throw new S21AbendException(NFDM0004E, new String[] {ERR_PRM_CLT_DUN_LTR_WRK });
            }
        }
    }

    /**
     * updateCltDunLtrWrk
     * @param cltStrgyWrkItemTrxMap Map<BigDecimal, String>
     * @param processPk BigDecimal
     * @param cltPrintStsCd String
     * @param printFlg boolean
     */
    // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
    private void updateCltDunLtrWrk(Map<BigDecimal, String> cltStrgyWrkItemTrxMap, BigDecimal processPk, String cltPrintStsCd, boolean printFlg) {
    // END 2021/07/28 G.Delgado [QC#58340-3,MOD]
        if (cltStrgyWrkItemTrxMap == null || cltStrgyWrkItemTrxMap.isEmpty()) {
            return;
        }
        // START 2021/07/28 G.Delgado [QC#58340-3,MOD]
        for (Map.Entry<BigDecimal, String> entry : cltStrgyWrkItemTrxMap.entrySet()) {
            BigDecimal cltStrgyWrkItemTrx = entry.getKey();
            String invPastDueFlg = entry.getValue();
            // END 2021/07/28 G.Delgado [QC#58340-3,MOD]

            CLT_DUN_LTR_WRKTMsgArray tMsgArray = getCltDunLtrWrk(cltStrgyWrkItemTrx);
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                CLT_DUN_LTR_WRKTMsg tMsg = tMsgArray.no(i);
                // START 2021/07/28 G.Delgado [QC#58340-3,ADD]
                if (ZYPCommonFunc.hasValue(invPastDueFlg) && !invPastDueFlg.equals(tMsg.invPastDueFlg.getValue())) {
                    continue;
                }
                // END 2021/07/28 G.Delgado [QC#58340-3,ADD]

                ZYPEZDItemValueSetter.setValue(tMsg.cltPrintStsCd, cltPrintStsCd);
                if (printFlg) {
                    ZYPEZDItemValueSetter.setValue(tMsg.rptPrintOtptFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(tMsg.eipRptProcLogPk, processPk);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.rptEmlOtptFlg, ZYPConstant.FLG_ON_Y);
                }
                S21FastTBLAccessor.update(tMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    this.termCd = TERM_CD.ABNORMAL_END;
                    throw new S21AbendException(NFDM0004E, new String[] {ERR_PRM_CLT_DUN_LTR_WRK });
                }
            }
        }
    }

    /**
     * updateRptOtptCpltFlg
     * @param cltDunLtrWrkPk BigDecimal
     * @param rptRqstDunPk BigDecimal
     */
    private void updateRptOtptCpltFlg(BigDecimal cltStrgyWrkItemTrxPk, BigDecimal rptRqstDunPk) {
        CLT_DUN_LTR_WRKTMsg tMsg = new CLT_DUN_LTR_WRKTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("cltStrgyWrkItemTrxPk01", cltStrgyWrkItemTrxPk);
        tMsg.setConditionValue("cltDunLtrRqstNum01", rptRqstDunPk);
        CLT_DUN_LTR_WRKTMsgArray tMsgArray = (CLT_DUN_LTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(tMsg);
        List<CLT_DUN_LTR_WRKTMsg> updTmsgList = new ArrayList<CLT_DUN_LTR_WRKTMsg>();
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            tMsg = tMsgArray.no(i);
            ZYPEZDItemValueSetter.setValue(tMsg.rptOtptCpltFlg, ZYPConstant.FLG_ON_Y);
            updTmsgList.add(tMsg);
        }
        int rtnCnt = S21FastTBLAccessor.update(updTmsgList.toArray(new CLT_DUN_LTR_WRKTMsg[updTmsgList.size()]));
        if (rtnCnt != updTmsgList.size()) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFDM0004E, new String[] {ERR_PRM_CLT_DUN_LTR_WRK });
        }
    }

    /**
     * updateRptPrintRqstCpltFlg
     * @param rptRqstDunPkList List<BigDecimal>
     */
    private void updateRptPrintRqstCpltFlg(List<BigDecimal> rptRqstDunPkList) {
        if (rptRqstDunPkList == null || rptRqstDunPkList.isEmpty()) {
            return;
        }
        for (BigDecimal rptRqstDunPk : rptRqstDunPkList) {
            CLT_DUN_LTR_WRKTMsg tMsg = new CLT_DUN_LTR_WRKTMsg();
            tMsg.setSQLID("003");
            tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
            tMsg.setConditionValue("cltDunLtrRqstNum01", rptRqstDunPk);
            CLT_DUN_LTR_WRKTMsgArray tMsgArray = (CLT_DUN_LTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(tMsg);
            List<CLT_DUN_LTR_WRKTMsg> updTmsgList = new ArrayList<CLT_DUN_LTR_WRKTMsg>();
            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                tMsg = tMsgArray.no(i);
                ZYPEZDItemValueSetter.setValue(tMsg.rptPrintRqstCpltFlg, ZYPConstant.FLG_ON_Y);
                updTmsgList.add(tMsg);
            }
            if (updTmsgList != null) {
                int rtnCnt = S21FastTBLAccessor.update(updTmsgList.toArray(new CLT_DUN_LTR_WRKTMsg[updTmsgList.size()]));
                if (rtnCnt != updTmsgList.size()) {
                    this.termCd = TERM_CD.ABNORMAL_END;
                    throw new S21AbendException(NFDM0004E, new String[] {ERR_PRM_CLT_DUN_LTR_WRK });
                }
            }
        }
    }
    // END 2021/05/10 G.Delgado [QC#58340,ADD]

    // START 2021/07/12 K.Suzuki [QC#58945,ADD]
    private boolean isPrintClrDunLtrWrk(ResultSet rs) throws SQLException {
        boolean result = false;
        if (ZYPCommonFunc.hasValue(rs.getString(DS_CTAC_PNT_VAL_TXT_BC))) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(rs.getString(DS_CTAC_PNT_VAL_TXT_AC))) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    // END 2021/07/12 K.Suzuki [QC#58945,ADD]

    // START 2021/07/14 K.Suzuki [QC#58340-2,ADD]
    /**
     * addBarcodeImageToPdf
     * 
     * @param uniqueIdentifier String
     * @param pdfFilePath      String
     */
    private static void addBarcodeImageToPdf(String uniqueIdentifier, String pdfFilePath) {

        // ***1. Initialize barcode builder
        S21BarcodeBuilder builder = new S21BarcodeBuilder(S21BarcodeType.Datamatrix);

        // ***2. Open PDF
        S21PDFDocument pdf = new S21PDFDocument(pdfFilePath);

        // ***3. Get Total page of the PDF
        int pagesSize = pdf.getTotalPageSize();
        int pagesSizeCnt = (pdf.getTotalPageSize() + 1) / 2;

        // Add Barcode image to Odd page in a PDF (1,3,5,7...)
        for (int pdfPageIdx = 1; pdfPageIdx <= pagesSize; pdfPageIdx++) {

            //int pageNum = pdfPageIdx;
            int pageNum = 0;

            if (pdfPageIdx % 2 > 0) {
                // Odd page (1,3,5...)
                pageNum = ((int) (pdfPageIdx / 2)) + 1;
            } else {
                // Even page (2,4,6...) Skip to add barcode
                continue;
            }

            // ***4. Get the bar code image for the text
            // 1. 1-9 Unique Identifier
            // 2. 10-12 Page Number
            // 3. 13-15 Total Page Number
            String key = uniqueIdentifier + String.format("%03d", pageNum) + String.format("%03d", pagesSizeCnt);
            BufferedImage barcode = builder.getBarCodeImage(key);

            // ***5. Add Barcode into PDF with specifying position
            pdf.addBarcodeImage(pdfPageIdx, barcode, 294, 681, 22, 22);
        }
        pdf.save(); // Save edited PDF
    }
    // END 2021/07/14 K.Suzuki [QC#58340-2,ADD]
}
