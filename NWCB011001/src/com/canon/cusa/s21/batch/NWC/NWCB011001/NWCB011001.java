package com.canon.cusa.s21.batch.NWC.NWCB011001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.dbcommon.EZDTBLAccessor;
import business.db.INV_PRT_CTRLTMsg;
import business.db.AUD_TRAIL_RPT_WRKTMsg;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_BAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21CUPSFileOutputParameter;
import com.canon.cusa.s21.framework.printing.cups.S21CUPSPrintOption;
import com.canon.cusa.s21.framework.printing.S21ExecutionStatus;
//QC#18693
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeBuilder;
import com.canon.cusa.s21.framework.ZYP.aspose.pdf.S21PDFDocument;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeType;
import java.awt.image.BufferedImage;

/**
 * <pre>
 * Invoice Print Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         Y.Kanefusa      Create          N/A
 * 2017/01/04   SRAA            K.Aratani       Update          QC#16851
 * 2017/01/31   SRAA            K.Aratani       Update          QC#17380
 * 2017/04/11   SRAA            K.Aratani       Update          QC#18308
 * 2017/05/19   SRAA            K.Aratani       Update          QC#18679
 * 2017/12/11   SRAA            K.Aratani       Update          QC#18694
 * 2017/12/11   SRAA            K.Aratani       Update          QC#16758
 * 2018/03/29   SRAA            K.Aratani       Update          QC#18693
 * 2018/06/18   SRAA            K.Aratani       Update          QC#26769
 * 2019/01/09   Fujitsu         T.Noguchi       Update          QC#29863
 * 2019/02/14   Fujitsu         Y.Kanefusa      Update          QC#30356
 * 2019/02/25   Fujitsu         K.Ishizuka      Update          QC#30461
 * 2019/02/27   Fujitsu         K.Kato          Update          QC#30540
 * 2019/02/28   Fujitsu         C.Hara          Update          QC#30517
 * </pre>
 */
public class NWCB011001 extends S21BatchMain {

    /** for Debug */
    int DEBUG_MSG_LVL = 1;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** Terminate Cord */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Mode */
    private String mode;

    /** Reprint Mode */
    private String reprintMode;

    /** Branch No. */
    private String prBrNo;

    /** sysTimeStamp */
    private String sysTimeStamp;

    /** Mail List */
    private List<Map<String, String>> mailErrorList = new ArrayList<Map<String, String>>();

    /** processPk */
    private long processPk = 0;
    
    /** Printer Avoid Flag. */
    private String printerAvoidFlag;

    /** Cleansing Days. */
    private BigDecimal clnsDaysAot;

    /** Audit Trail Not Set Up Message. */
    private String auditTrailNotSetUpMsg;
    
    /** 2D Barcode Avoid Flag. */
    private String twodBarcodeAvoidFlag;

    //QC#26769
    /** CUPS page count. */
    private BigDecimal cupsPageCount;

    /**
     * It is the main method that is called from the batch processing.
     * @param args Argument
     */
    public static void main(String[] args) {

        new NWCB011001().executeBatch(NWCB011001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            setMainRoutineEnd();
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NWCB011001Constant.ZZZM9025E, new String[] {"Global Company Code" });
        }
        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Mode (01:Print Out / 02:PDF)
        mode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mode)) {
            throw new S21AbendException(NWCB011001Constant.ZZZM9025E, new String[] {"Sales Date" });
        }

        // Reprint Mode (REPRINT / NULL)
        reprintMode = getUserVariable2();
        
        // Branch No.
        // Print Out
        if (S21StringUtil.isEquals(mode, NWCB011001Constant.MODE_01_PRINT_OUT)) {
            prBrNo = ZYPCodeDataUtil.getVarCharConstValue(NWCB011001Constant.DEF_PRT_BR_NUM, glblCmpyCd);
            if (prBrNo == null || prBrNo.isEmpty()) {
                throw new S21AbendException(NWCB011001Constant.NWCM0102E, null);
            }
        // PDF
        } else {
            //Not Used
            prBrNo = ZYPCodeDataUtil.getVarCharConstValue(NWCB011001Constant.DEF_PDF_CRE_BR_NUM, glblCmpyCd);
            if (prBrNo == null || prBrNo.isEmpty()) {
                throw new S21AbendException(NWCB011001Constant.NWCM0103E, null);
            }
        }
        
        printerAvoidFlag = ZYPCodeDataUtil.getVarCharConstValue(NWCB011001Constant.NWCB0100_PRINTER_AVOID_FLAG, glblCmpyCd);
        if (printerAvoidFlag == null) {
            printerAvoidFlag = ZYPConstant.FLG_OFF_N;
        }
System.out.println("Print Avoid Flag[" + printerAvoidFlag + "]");

        auditTrailNotSetUpMsg = ZYPCodeDataUtil.getVarCharConstValue(NWCB011001Constant.NWCB0100_AUDC_NOT_SETUP_MSG, glblCmpyCd);
        if (auditTrailNotSetUpMsg == null) {
            auditTrailNotSetUpMsg = "NOT SETUP";
        }
System.out.println("Audit Trail Not Setup Message[" + auditTrailNotSetUpMsg + "]");

        // 2019/02/28 QC#30517 Mod Start
        // twodBarcodeAvoidFlag = ZYPCodeDataUtil.getVarCharConstValue(NWCB011001Constant.NWCB0110_2DBARCODE_AVOID_FLAG, glblCmpyCd);
        twodBarcodeAvoidFlag = ZYPCodeDataUtil.getVarCharConstValue(NWCB011001Constant.NWCF0100_TICKMARK_FLG, glblCmpyCd);
        // 2019/02/28 QC#30517 Mod End
        if (twodBarcodeAvoidFlag == null) {
            twodBarcodeAvoidFlag = ZYPConstant.FLG_OFF_N;
        }
System.out.println("2D Barcode Avoid Flag[" + twodBarcodeAvoidFlag + "]");
        
        this.clnsDaysAot = ZYPCodeDataUtil.getNumConstValue(NWCB011001Constant.AUDIT_TRAIL_CLEANS_DAYS_AOT, this.glblCmpyCd);
        
        //QC#26769
        this.cupsPageCount = ZYPCodeDataUtil.getNumConstValue(NWCB011001Constant.NWCB011001_CUPS_PAGE_COUNT, this.glblCmpyCd);
        if (this.cupsPageCount == null) {
            this.cupsPageCount = new BigDecimal("500");
        }
        
        sysTimeStamp = ZYPDateUtil.getCurrentSystemTime(NWCB011001Constant.TYPE_TIME_STAMP);

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {
        try {
            doProcess();
        } finally {
            if (mailErrorList.size() > 0) {
                postErrMail();
                termCd = TERM_CD.WARNING_END;
            }
            commit();
        }

    }

    @Override
    protected void termRoutine() {
        int totalCount = totalCommitCount + totalErrCount;
        setTermState(termCd, totalCommitCount, totalErrCount, totalCount);
    }

    @SuppressWarnings("unchecked")
    private void doProcess() {
        S21EIPPrintingService service = new S21EIPPrintingService();
        S21ReportRequestBean request = null;
        S21InputParameter inputParam = null;

        List<Map<?, ?>> list = null;
        Map<String, Object> mapParam = new HashMap<String, Object>();
        // Print Out (New Print / Re Print)
        if (S21StringUtil.isEquals(mode, NWCB011001Constant.MODE_01_PRINT_OUT)) {
System.out.println("*************** START Print Out ***************");
            //Purge Audit Trail Work Table
            purgeAUD_TRAIL_RPT_WRK();
            //get target data order by REPR_RPT_BR_NUM, INV_PRT_BAT_TP_CD, INV_PRT_BR_CD, BILL_TO_POST_CD, CONSL_BILL_NUM(Bill#, Invoice#)
            mapParam.put("glblCmpyCd", glblCmpyCd);
            mapParam.put("invPrtCtrlRecCd", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
            mapParam.put("invPrtProcStsCd", NWCB011001Constant.NOT_EXECUTED);
            mapParam.put("invOtptReqFlg", ZYPConstant.FLG_ON_Y);
            //RePrint
            if (ZYPCommonFunc.hasValue(reprintMode)) {
                mapParam.put("reprintMode", reprintMode);
            }
            list = ssmBatchClient.queryObjectList("selectInvPrtCtrl", mapParam);
            if (list == null || list.isEmpty()) {
                return;
            }
            String reprRptBrNum = "";
            String preInvPrtBatTpCd = "";
            String preInvPrtBrCd = "";
            BigDecimal invPrtRqstSq = null;
            String printerName = "";
            long auditTrailRqstPkForCupsPrint = -1;
            //Map<BigDecimal, Long> invoiceAndRquestPkMapForCupsPrint = new HashMap<BigDecimal, Long>(); //All Invoices List per Audit Trail
            List<Map<?, ?>> invoiceAndRquestPkListForCupsPrint = new ArrayList<Map<?, ?>>();
            boolean audcNotSetupFirstFlag = false;
            for (Map<?, ?> data : list) {
                
                //change the reprint printer
                if (!reprRptBrNum.equals(nvl((String) data.get("REPR_RPT_BR_NUM")))) {
                    preInvPrtBatTpCd = "";
                    preInvPrtBrCd = "";
                    audcNotSetupFirstFlag = false;
                }
                //Audit Trail for Batch Type (Service Contract Invoice or Consolidated Bill)
                //TODO Add INV_RPT_BR_PRINT_NUM
                if (ZYPCommonFunc.hasValue((String) data.get("INV_PRT_BAT_TP_CD")) 
                        && !preInvPrtBatTpCd.equals((String) data.get("INV_PRT_BAT_TP_CD"))) {
                    //If list has Audit Trail & Invoice List
                    if (auditTrailRqstPkForCupsPrint != -1 && invoiceAndRquestPkListForCupsPrint != null && invoiceAndRquestPkListForCupsPrint.size() > 0) {
System.out.println("Print Batch Type="+preInvPrtBatTpCd+";");
                        callCupsAPI(service, invoiceAndRquestPkListForCupsPrint, auditTrailRqstPkForCupsPrint, invPrtRqstSq, printerName);
                    }
                    //Initialize
                    auditTrailRqstPkForCupsPrint = -1; // Audit Trail
                    invoiceAndRquestPkListForCupsPrint = new ArrayList<Map<?, ?>>(); //All Invoices List per Audit Trail
                    invPrtRqstSq = null;
                    //Printer Name
                    printerName = getPrinterName(service, (String) data.get("INV_RPT_BR_PRINT_NUM_BAT"), (String) data.get("REPR_RPT_BR_NUM"));
                    //Create AUD_TRAIL_RPT_WRK
                    invPrtRqstSq = createAuditTrailWorkTable(true, (String) data.get("INV_PRT_BAT_TP_CD"), (String) data.get("INV_PRT_BAT_TP_NM"), (String) data.get("REPR_RPT_BR_NUM"));
                    if (ZYPCommonFunc.hasValue(invPrtRqstSq)) {
                        //Create Audit Trail Report PDF
                        auditTrailRqstPkForCupsPrint = callEIPforAuditTrail(service, invPrtRqstSq, (String) data.get("INV_PRT_BAT_TP_NM"));
                    }
                }
                //Audit Trail for Print Branch (OM Invoice or Parts & Labor Invoice or AR Manual Invoice)
                //TODO Add INV_RPT_BR_PRINT_NUM
                if (ZYPCommonFunc.hasValue((String) data.get("INV_PRT_BR_CD")) 
                        && !preInvPrtBrCd.equals((String) data.get("INV_PRT_BR_CD"))) {
                    //If list has Audit Trail & Invoice List
                    if (auditTrailRqstPkForCupsPrint != -1 && invoiceAndRquestPkListForCupsPrint != null && invoiceAndRquestPkListForCupsPrint.size() > 0) {
System.out.println("Print Branch ="+preInvPrtBrCd+";");
                        callCupsAPI(service, invoiceAndRquestPkListForCupsPrint, auditTrailRqstPkForCupsPrint, invPrtRqstSq, printerName);
                    }
                    //Initialize
                    auditTrailRqstPkForCupsPrint = -1;
                    invoiceAndRquestPkListForCupsPrint = new ArrayList<Map<?, ?>>();
                    invPrtRqstSq = null;
                    //Printer Name
                    printerName = getPrinterName(service, (String) data.get("INV_RPT_BR_PRINT_NUM_BR"), (String) data.get("REPR_RPT_BR_NUM"));
                    //Create AUD_TRAIL_RPT_WRK
                    invPrtRqstSq = createAuditTrailWorkTable(false, (String) data.get("INV_PRT_BR_CD"), (String) data.get("INV_PRT_BR_NM"), (String) data.get("REPR_RPT_BR_NUM"));
                    if (ZYPCommonFunc.hasValue(invPrtRqstSq)) {
                        //Create Audit Trail Report PDF
                        auditTrailRqstPkForCupsPrint = callEIPforAuditTrail(service, invPrtRqstSq, (String) data.get("INV_PRT_BR_NM"));
                    }
                }
                
                //Audit Trail for Not Setup goes to Default Printer
                if (!ZYPCommonFunc.hasValue((String) data.get("INV_PRT_BAT_TP_CD")) 
                        && !ZYPCommonFunc.hasValue((String) data.get("INV_PRT_BR_CD")) 
                        && !audcNotSetupFirstFlag) {
                    //If list has Audit Trail & Invoice List
                    if (auditTrailRqstPkForCupsPrint != -1 && invoiceAndRquestPkListForCupsPrint != null && invoiceAndRquestPkListForCupsPrint.size() > 0) {
System.out.println("Print Batch Type=null, Print Branch=null");
                        callCupsAPI(service, invoiceAndRquestPkListForCupsPrint, auditTrailRqstPkForCupsPrint, invPrtRqstSq, printerName);
                    }
                    //Initialize
                    auditTrailRqstPkForCupsPrint = -1;
                    invoiceAndRquestPkListForCupsPrint = new ArrayList<Map<?, ?>>();
                    invPrtRqstSq = null;
                    audcNotSetupFirstFlag = true;
                    //Printer Name
                    printerName = getPrinterName(service, null, (String) data.get("REPR_RPT_BR_NUM"));
                    //Create AUD_TRAIL_RPT_WRK
                    invPrtRqstSq = createAuditTrailWorkTable(false, null, auditTrailNotSetUpMsg, (String) data.get("REPR_RPT_BR_NUM"));
                    if (ZYPCommonFunc.hasValue(invPrtRqstSq)) {
                        //Create Audit Trail Report PDF
                        auditTrailRqstPkForCupsPrint = callEIPforAuditTrail(service, invPrtRqstSq, (String) data.get("INV_PRT_BR_NM"));
                    }
                }
                
                //No PDF
                if (data.get("INV_FTP_RQST_NUM") == null) {
                    //send EIP error eMail
                    setMailMessageForEIP(((BigDecimal) data.get("INV_PRT_CTRL_PK")).longValue(), 
                            (String) data.get("NUM"), 
                            "Can't print out because PDF was not created.");
                } else {
                    //Store Each Invoice
                    invoiceAndRquestPkListForCupsPrint.add(data);
                }

                preInvPrtBatTpCd = nvl((String) data.get("INV_PRT_BAT_TP_CD"));
                preInvPrtBrCd = nvl((String) data.get("INV_PRT_BR_CD"));
                reprRptBrNum = nvl((String) data.get("REPR_RPT_BR_NUM"));
            }
            
            //Remain unprinted report
            if (auditTrailRqstPkForCupsPrint != -1 && invoiceAndRquestPkListForCupsPrint != null && invoiceAndRquestPkListForCupsPrint.size() > 0) {
                callCupsAPI(service, invoiceAndRquestPkListForCupsPrint, auditTrailRqstPkForCupsPrint, invPrtRqstSq, printerName);
            }
System.out.println("*************** END Print Out ***************");
        // PDF Creation
        } else {
System.out.println("*************** START PDF Creation ***************");
            // 1-1. createReportByAsync per invoice
            mapParam.put("glblCmpyCd", glblCmpyCd);
            mapParam.put("invPrtCtrlRecCd", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
            mapParam.put("invFtpProcStsCd", NWCB011001Constant.NOT_EXECUTED);
            list = ssmBatchClient.queryObjectList("selectInvPrtCtrlForPDF", mapParam);
            if (list == null || list.isEmpty()) {
                return;
            }
            Map<BigDecimal, Long> invoiceAndRquestPkMap = new HashMap<BigDecimal, Long>();
            for (Map<?, ?> data : list) {
                request = new S21ReportRequestBean(NWCB011001Constant.REPORT_ID);
                request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                request.setRptArcFlg(true);
                request.setRptTtlNm(NWCB011001Constant.PDF_TTL_NAME + (String) data.get("NUM") + NWCB011001Constant.RPT_TIME + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

                inputParam = request.getInputParamBeanInstance();

                inputParam.addReportParameter(NWCB011001Constant.GLBL_CMPY_CD, glblCmpyCd);
                inputParam.addReportParameter(NWCB011001Constant.INV_BILL_NUM, (String) data.get("NUM"));
                inputParam.addReportParameter(NWCB011001Constant.INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
                inputParam.addReportParameter(NWCB011001Constant.CONSL_BILL_FLG, (String) data.get("CONSL_BILL_FLG"));
                inputParam.addReportParameter(NWCB011001Constant.INV_PRT_CTRL_PK, String.valueOf((BigDecimal) data.get("INV_PRT_CTRL_PK")));
                request.setInputParamBean(inputParam);
                
                // PDF file name if needed,
                // If dosen't set file name, automatically it creates like 
                // e.g) /NWC/20171206/'ReportID'_'yyyyMMddHHmmssSSS'_UUID.pdf
                S21CUPSFileOutputParameter cupsFileoutParam = request.getCUPSFileOutParamBeanInstance();
                // if set file name, e.g) /NWC/20171206/123456789_20171206115023999.pdf
                cupsFileoutParam.setFileName((String) data.get("NUM") + "_" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
                request.setCUPSFileOutParamBean(cupsFileoutParam);
                invoiceAndRquestPkMap.put((BigDecimal) data.get("INV_PRT_CTRL_PK"), service.createReportByAsync(request)); // set invoice number and corresponded requestPk
                
            }
            
            // 1-2. If success, activate Report Processing. (insert EIP_RPT_PROC_LOG Table)
            processPk = service.activateAsyncReportJob();
            commit(); // commit the transaction before call EIP
            

            // 1-3. invokeAsyncPollingBatchTypeEIP (Async EIP Batch service call + Waiting completion per 1 minute)
            S21ExecutionStatus pdfCreationResult = service.invokeAsyncPollingBatchTypeEIP(processPk);
            boolean pdfResultFlg = pdfCreationResult.isSuccessful();
            if (!pdfResultFlg) { // Some PDF creations failed.
                Map<String, Object> eipResultChkParam = new HashMap<String, Object>();
                eipResultChkParam.put("glblCmpyCd", glblCmpyCd);
                eipResultChkParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
                List<Map<String, Object>> eipResultList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getEIPResultChk", eipResultChkParam);
                if (eipResultList != null && eipResultList.size() > 0) {
                    for (Map<String, Object> eipResultMap : eipResultList) {
                        if (eipResultMap != null && eipResultMap.get("EIP_RPT_RQST_PK") != null) {
                            //send EIP error eMail
                            setMailMessageForEIP(((BigDecimal) eipResultMap.get("EIP_RPT_RQST_PK")).longValue(), 
                                    (String) eipResultMap.get("RPT_RQST_STS_TXT"), 
                                    (String) eipResultMap.get("RPT_TTL_NM"));
                        }
                    }
                }
            }
            
            // 2. Distinguish 'SINGLE' and 'MULTI' Audit Trail
            // 2-1. Get Page number
            // 2019/02/27 S21_NA#30540 Add Start
            boolean addBarcodeImageError = false;
            // 2019/02/27 S21_NA#30540 Add End
            for (Entry<BigDecimal, Long> entry : invoiceAndRquestPkMap.entrySet()) {
                String filePath = service.getFilePathForCupsPrint(entry.getValue(), 0);
                if (ZYPCommonFunc.hasValue(filePath)) {
                    //QC#18693 add Barcode Image to Pdf
                    boolean aspose2DError = false;
                    try {
                        if (!ZYPConstant.FLG_ON_Y.equals(twodBarcodeAvoidFlag)) {
                            String uniqueIdentifier = ZYPMaxTenDigitsNumbering.getUniqueID("CUPS_UNIQ_CD_2D"); //from AUTO_SQ_TEN_DIGIT
                            addBarcodeImageToPdf(uniqueIdentifier, service.getFilePathForCupsPrint(entry.getValue(), 0));
                        }
                    } catch (Exception e) {
                        //catch Aspose Several Exception
                        e.printStackTrace();
                        aspose2DError = true;
                        // 2019/02/27 S21_NA#30540 Add Start
                        addBarcodeImageError = true;
                        // 2019/02/27 S21_NA#30540 Add End
                    }
                    //Update in only case of 2D barcode success
                    if (!aspose2DError) {
                        int totalPageNumber = service.getReportTotalPageNumber(entry.getValue(), 0); // rqstPk, rqstSubId(0:default)
                        updateInvPrtCtrlForPDF(entry.getKey(), entry.getValue(), totalPageNumber);
                    }
                }
            }
            commit(); // commit the transaction only success data
            
            // 2019/02/27 S21_NA#30540 Mod Start
            //if (!pdfResultFlg) {
            if (!pdfResultFlg || addBarcodeImageError) {
            // 2019/02/27 S21_NA#30540 Mod End
                throw new S21AbendException("Failed to create PDF. Please check the failed data and re-run the job.");
            }
            
System.out.println("*************** END PDF Creation ***************");
        }
    }

    private void updateInvPrtCtrlforPrint(Map<BigDecimal, Long> invoiceAndCupsReqIdMap, BigDecimal invPrtRqstSq, String cupsPrintReqId) {
System.out.println("*************** START Update INV_PRT_CTRL for Print Out ***************");
System.out.println("list="+invoiceAndCupsReqIdMap.toString()+", trailReq="+invPrtRqstSq+", cupsId="+cupsPrintReqId+";");
        for (Entry<BigDecimal, Long> entry : invoiceAndCupsReqIdMap.entrySet()) {
            INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, entry.getKey());
            invPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(invPrtCtrlTMsg);
            if (invPrtCtrlTMsg == null) {
                return;
            }
            
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtProcStsCd, NWCB011001Constant.EXECUTED);
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrintRqstNum, new BigDecimal(entry.getValue()));
            ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrintProcDt, slsDt);
            invPrtCtrlTMsg.reprRptBrNum.clear();
            //Only Print
            if (!ZYPCommonFunc.hasValue(reprintMode)) {
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.cupsPrintRqstId, cupsPrintReqId);
                if (ZYPCommonFunc.hasValue(invPrtRqstSq)) {
                    ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtRqstSq, invPrtRqstSq);
                } else {
                    invPrtCtrlTMsg.invPrtRqstSq.clear();
                }
            }
            S21FastTBLAccessor.update(invPrtCtrlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invPrtCtrlTMsg.getReturnCode())) {
                // Error
                totalErrCount = totalErrCount + 1;
                setMailMessage(entry.getValue(), (String) invPrtCtrlTMsg.invNum.getValue(), NWCB011001Constant.ZZZM9013E, new String[] {invPrtCtrlTMsg.getReturnCode() });
                rollback();
                return;
            }
            //Release lock record
            commit();
            totalCommitCount = totalCommitCount + 1;
        }
System.out.println("*************** END Update INV_PRT_CTRL for Print Out ***************");
    }

    @SuppressWarnings("unchecked")
    private void updateInvPrtCtrlForPDF(BigDecimal invPrtCtrlPk, long requestPk, int totalPageNumber) {
System.out.println("*************** START Update INV_PRT_CTRL for PDF Creation ***************");
System.out.println("INV_PRT_CTRL_PK="+invPrtCtrlPk+", ReqId="+requestPk+", totalPageNumber="+totalPageNumber+";");
        INV_PRT_CTRLTMsg invPrtCtrlTMsg = new INV_PRT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtCtrlPk, invPrtCtrlPk);
        invPrtCtrlTMsg = (INV_PRT_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(invPrtCtrlTMsg);
        if (invPrtCtrlTMsg == null) {
            return;
        }
        //If EIP_RPT_RQST doesn't have success, skip the update
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("requestPk", new BigDecimal(String.valueOf(requestPk)));
        Map<String, Object> eipResultMap = (Map<String, Object>) ssmBatchClient.queryObject("getEIPResult", param);
        if (eipResultMap == null || !"SUCCESS".equals((String)eipResultMap.get("RPT_RQST_STS_TXT"))) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invFtpProcStsCd, NWCB011001Constant.EXECUTED);
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invFtpRqstNum, new BigDecimal(requestPk));
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invFtpProcDt, slsDt);
        //Overwrite Batch Type
        if (INV_PRT_BAT_TP.SINGLE_PAGE.equals(invPrtCtrlTMsg.invPrtBatTpCd.getValue())
                || INV_PRT_BAT_TP.MULTIPLE_PAGE.equals(invPrtCtrlTMsg.invPrtBatTpCd.getValue())) {
            if (totalPageNumber > 0 && totalPageNumber <= 2) {
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtBatTpCd, INV_PRT_BAT_TP.SINGLE_PAGE);
            } else if (totalPageNumber > 2) {
                ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.invPrtBatTpCd, INV_PRT_BAT_TP.MULTIPLE_PAGE);
            }
        }
        //Store Page Count
        ZYPEZDItemValueSetter.setValue(invPrtCtrlTMsg.conslBillInvPgCnt, new BigDecimal(String.valueOf(totalPageNumber)));
        S21FastTBLAccessor.update(invPrtCtrlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(invPrtCtrlTMsg.getReturnCode())) {
            // Error
            totalErrCount = totalErrCount + 1;
            setMailMessage(requestPk, (String) invPrtCtrlTMsg.invNum.getValue(), NWCB011001Constant.ZZZM9013E, new String[] {invPrtCtrlTMsg.getReturnCode() });
            rollback();
            return;
        }
        //Release lock record
        commit();
        totalCommitCount = totalCommitCount + 1;
System.out.println("*************** END Update INV_PRT_CTRL for PDF Creation ***************");
    }
    
    private void postErrMail() {

        // get mail information : address
        S21Mail mail = new S21Mail(glblCmpyCd);
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, NWCB011001Constant.GROUP_FROM);

        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList.isEmpty()) {
            throw new S21AbendException(NWCB011001Constant.NWCM0060E);
        }
        mail.setFromAddress(fromAddrList.get(0));

        S21MailGroup group = new S21MailGroup(glblCmpyCd, NWCB011001Constant.BUSINESS_ID);
        List<S21MailAddress> toAddrList = group.getMailAddress();

        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NWCB011001Constant.NWCM0060E);
        }
        mail.setToAddressList(toAddrList);

        // get mail template
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, NWCB011001Constant.MAIL_TEMPLATE_ID);
        template.setTemplateParameter("batchId", NWCB011001Constant.BUSINESS_ID);
        template.setTemplateParameter("batchNm", NWCB011001Constant.BATCH_NM);
        template.setTemplateParameter("batchProcLogId", super.getBatchProcessLogID());
        template.setTemplateParameter("eipRptProcLogPk", processPk);

        // set Message
        StringBuilder msg = new StringBuilder();
        msg.append(NWCB011001Constant.TITLE);
        for (Map<String, String> map : mailErrorList) {
            msg.append(NWCB011001Constant.LINE_FEED_CODE);
            msg.append(map.get(NWCB011001Constant.KEYID));
            msg.append(NWCB011001Constant.SEPARATOR);
            msg.append(map.get(NWCB011001Constant.NUMBER));
            msg.append(NWCB011001Constant.SEPARATOR);
            msg.append(map.get(NWCB011001Constant.MESSAGE));
        }

        template.setTemplateParameter("ErrorInfo", msg.toString());
        mail.setMailTemplate(template);

        // post mail
        mail.postMail();
    }

    /**
     * setMailMessage
     * @param key long
     * @param number String
     * @param msgId String
     * @param msgParam String[]
     */
    private void setMailMessage(long key, String number, String msgId, String[] msgParam) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(NWCB011001Constant.KEYID, new BigDecimal(key).toString());
        map.put(NWCB011001Constant.NUMBER, number);
        map.put(NWCB011001Constant.MESSAGE, S21MessageFunc.clspGetMessage(msgId, msgParam));
        mailErrorList.add(map);
    }

    /**
     * setMailMessage
     * @param key long
     * @param number String
     * @param msgId String
     * @param msgParam String[]
     */
    private void setMailMessageForEIP(long key, String status, String msg) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(NWCB011001Constant.KEYID, new BigDecimal(key).toString());
        map.put(NWCB011001Constant.NUMBER, "Status : " + status);
        map.put(NWCB011001Constant.MESSAGE, msg);
        mailErrorList.add(map);
    }

    private String nvl(String val) {
        if (val == null) {
            return "";
        }
        return val;
    }
    
    private void callCupsAPI(S21EIPPrintingService service, List<Map<?, ?>> invoiceAndRquestPkListForCupsPrint, long auditTrailRqstPkForCupsPrint, BigDecimal invPrtRqstSq, String printerName) {
System.out.println("*************** START CUPS Call per Audit Trail ***************");
System.out.println("Audit Trail's EIP_RPT_RQST_PK="+auditTrailRqstPkForCupsPrint+";");
System.out.println("Invoice List="+invoiceAndRquestPkListForCupsPrint.toString()+";");
        List<Long> mergeRequestPkListForCupsPrint = new ArrayList<Long>(); //CUPS list(Audit Trail + Invoice)
        mergeRequestPkListForCupsPrint.add(auditTrailRqstPkForCupsPrint);
        S21CUPSPrintOption printOption = new S21CUPSPrintOption();
        Map<BigDecimal, Long> invoiceAndCupsReqIdMap = new HashMap<BigDecimal, Long>();//CUPS list(Invoice) for update INV_PRT_CTRL
        printOption.setDoubleSideLongEdge();
        for (Map<?, ?> data : invoiceAndRquestPkListForCupsPrint) {
            mergeRequestPkListForCupsPrint.add(((BigDecimal) data.get("INV_FTP_RQST_NUM")).longValue());
            invoiceAndCupsReqIdMap.put((BigDecimal) data.get("INV_PRT_CTRL_PK"), ((BigDecimal) data.get("INV_FTP_RQST_NUM")).longValue());
            
            //QC#26769
            //if (mergeRequestPkListForCupsPrint.size() == 500) {
            if (mergeRequestPkListForCupsPrint.size() == this.cupsPageCount.intValue()) {
//System.out.println("CUPS 500 separate list="+mergeRequestPkListForCupsPrint.toString()+";");
System.out.println("CUPS " + this.cupsPageCount.intValue() + " separate list="+mergeRequestPkListForCupsPrint.toString()+";");
                String cupsPrintReqId = null;
                if (!ZYPConstant.FLG_ON_Y.equals(printerAvoidFlag)) {
                    // 2019/01/09 QC#29863 Mod Start
                    //service.mergeAndPrintRequestToCups(mergeRequestPkListForCupsPrint, printerName, printOption);
                    cupsPrintReqId = service.mergeAndPrintRequestToCups(mergeRequestPkListForCupsPrint, printerName, printOption);
                    // 2019/01/09 QC#29863 Mod End
                }
                mergeRequestPkListForCupsPrint.clear();
                //update INV_PRT_CTRL
                updateInvPrtCtrlforPrint(invoiceAndCupsReqIdMap, invPrtRqstSq, cupsPrintReqId);
                invoiceAndCupsReqIdMap.clear();
            }
        }
        // Be careful, do not skip remain requests last under 500
        if (mergeRequestPkListForCupsPrint.size() != 0) {
System.out.println("CUPS remain list="+mergeRequestPkListForCupsPrint.toString()+";");
            String cupsPrintReqId = null;
            if (!ZYPConstant.FLG_ON_Y.equals(printerAvoidFlag)) {
                cupsPrintReqId = service.mergeAndPrintRequestToCups(mergeRequestPkListForCupsPrint, printerName, printOption);
            }
System.out.println("CUPS Print Request ID: [" + cupsPrintReqId + "]");
            //update INV_PRT_CTRL
            updateInvPrtCtrlforPrint(invoiceAndCupsReqIdMap, invPrtRqstSq, cupsPrintReqId);
            invoiceAndCupsReqIdMap.clear();
        }
System.out.println("*************** END CUPS Call per Audit Trail ***************");
    }

    @SuppressWarnings("unchecked")
    private BigDecimal createAuditTrailWorkTable(boolean batTpFlg, String code, String name, String reprRptBrNum) {
        //Audit trail Report
    	BigDecimal invPrtRqstSq = null;
        Map<String, Object> audMapParam = new HashMap<String, Object>();
        audMapParam.put("glblCmpyCd", glblCmpyCd);
        audMapParam.put("invPrtCtrlRecCd", INV_PRT_CTRL_REC.CONSOLIDATED_BILL);
        audMapParam.put("invPrtProcStsCd", NWCB011001Constant.NOT_EXECUTED);
        audMapParam.put("invOtptReqFlg", ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(code)) {
            if (batTpFlg) {
                audMapParam.put("invPrtBatTpCd", code);
            } else {
                audMapParam.put("invPrtBrCd", code);
            }
        } else {
            audMapParam.put("invPrtBrBatTpNull", "Y");
        }
        audMapParam.put("dollarMark", "$");
        if (ZYPCommonFunc.hasValue(reprintMode)) {
            audMapParam.put("reprintMode", reprintMode);
            audMapParam.put("reprRptBrNum", reprRptBrNum);
        }
        List<Map<?, ?>> auditTrailList = ssmBatchClient.queryObjectList("getAuditTrailReport", audMapParam);
        //Create Audit Trail Report for Batch Type
        if (auditTrailList != null && auditTrailList.size() > 0) {
            //Create AUD_TRAIL_RPT_WRK
            invPrtRqstSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.INV_PRT_RQST_SQ);
            for (Map<?, ?> map : auditTrailList) {
                AUD_TRAIL_RPT_WRKTMsg audTrailRptWrkTMsg = new AUD_TRAIL_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.glblCmpyCd, glblCmpyCd);  //GLBL_CMPY_CD
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.AUD_TRAIL_RPT_WRK_SQ));  //AUD_TRAIL_RPT_WRK_PK
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailProcTs, sysTimeStamp);  //AUD_TRAIL_PROC_TS (20161222103712342)
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptTtlTxt, NWCB011001Constant.AUD_TRAIL_TTL_TXT + name);  //AUD_TRAIL_RPT_TTL_TXT (Audit Trail - SINGLE PAGE)
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.invPrtBatTpCd, code);  //INV_PRT_BAT_TP_CD
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptTsPrintTxt, (String) map.get("AUD_TRAIL_RPT_TS_PRINT_TXT"));  //AUD_TRAIL_RPT_TS_PRINT_TXT (11/15/2016 5:10:27 AM)
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.invPrtRqstSq, invPrtRqstSq);  //INV_PRT_RQST_SQ
                java.text.DecimalFormat df1 = new java.text.DecimalFormat("#,###,###,###");
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptCntPrintTxt, df1.format(auditTrailList.size()));  //AUD_TRAIL_RPT_CNT_PRINT_TXT (1,090)
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.invBillNum, (String) map.get("NUM"));  //INV_BILL_NUM
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.invTotAmtPrintTxt, (String) map.get("AMT"));  //INV_TOT_AMT_PRINT_TXT ($45.40)
                ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptDtPrintTxt, (String) map.get("INV_DT"));  //AUD_TRAIL_RPT_DT_PRINT_TXT (November 15,2016)
                // 2019/02/25 S21_NA#30461 Mod Start
                // if (batTpFlg) {
                //     ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptInvTpTxt, "Contracts");  //AUD_TRAIL_RPT_INV_TP_TXT
                // } else {
                //     ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptInvTpTxt, "Equipment/Supply");  //AUD_TRAIL_RPT_INV_TP_TXT
                // }
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, (String) map.get("CONSL_FLG"))) {
                    ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptInvTpTxt, "CONSOLIDATED");  //AUD_TRAIL_RPT_INV_TP_TXT
                } else {
                    ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptInvTpTxt, (String) map.get("ORD_CLS_NM"));  //AUD_TRAIL_RPT_INV_TP_TXT
                }
                // 2019/02/25 S21_NA#30461 Mod End
                
                S21FastTBLAccessor.insert(audTrailRptWrkTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(audTrailRptWrkTMsg.getReturnCode())) {
                    totalErrCount = totalErrCount + 1;
                    setMailMessage(invPrtRqstSq.longValue(), name, NWCB011001Constant.ZZZM9013E, new String[] {audTrailRptWrkTMsg.getReturnCode() });
                    rollback();
                }
            }
        }
        return invPrtRqstSq;
    }
    private long callEIPforAuditTrail(S21EIPPrintingService service, BigDecimal invPrtRqstSq, String name) {
        S21ReportRequestBean request = new S21ReportRequestBean(NWCB011001Constant.AUD_TRAIL_REPORT_ID);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(true);
        String rptTtlNm = NWCB011001Constant.AUD_TRAIL_RPT_TTL_NAME + name + " " + invPrtRqstSq + NWCB011001Constant.RPT_TIME + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        request.setRptTtlNm(rptTtlNm);
        S21InputParameter inputParam = request.getInputParamBeanInstance();
        inputParam.addReportParameter(NWCB011001Constant.GLBL_CMPY_CD, glblCmpyCd);
        inputParam.addReportParameter(NWCB011001Constant.INV_PRT_RQST_SQ, invPrtRqstSq);
        inputParam.addReportParameter(NWCB011001Constant.INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
        request.setInputParamBean(inputParam);
        // PDF file name if needed,
        // If dosen't set file name, automatically it creates like 
        // e.g) /NWC/20171206/'ReportID'_'yyyyMMddHHmmssSSS'_UUID.pdf
        S21CUPSFileOutputParameter cupsFileoutParam = request.getCUPSFileOutParamBeanInstance();
        // if set file name, e.g) /NWC/20171206/123456789_20171206115023999.pdf
        cupsFileoutParam.setFileName("AuditTrail" + "_" + invPrtRqstSq + "_" + ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        request.setCUPSFileOutParamBean(cupsFileoutParam);
        return service.createReportBySync(request);
    }
    private String getPrinterName(S21EIPPrintingService service, String printBrNum, String reprRptBrNum) {
        //Default
        if (!ZYPCommonFunc.hasValue(printBrNum)) {
            printBrNum = this.prBrNo;
        }
        //Re Print
        if (ZYPCommonFunc.hasValue(reprRptBrNum)) {
            printBrNum = reprRptBrNum;
        }
        //Printer Name
        return service.getPrinterQueueName(NWCB011001Constant.REPORT_ID, printBrNum);
    }
    //QC#18308
    @SuppressWarnings("unchecked")
    private void purgeAUD_TRAIL_RPT_WRK() {
        if (this.clnsDaysAot != null) {
            Map<String, Object> mapClensParam = new HashMap<String, Object>();
            mapClensParam.put("glblCmpyCd", glblCmpyCd);
            String clensDt = ZYPDateUtil.addDays(slsDt, (this.clnsDaysAot.negate()).intValue());
            mapClensParam.put("clensDt", clensDt);
            List<Map<String, Object>> clensList = ssmBatchClient.queryObjectList("getDeleteAudTrailRptWrk", mapClensParam);
            if (clensList != null && clensList.size() > 0) {
                for (Map<String, Object> clensMap : clensList) {
                    if (clensMap != null) {
                        AUD_TRAIL_RPT_WRKTMsg audTrailRptWrkTMsg = new AUD_TRAIL_RPT_WRKTMsg();
                        ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(audTrailRptWrkTMsg.audTrailRptWrkPk, (BigDecimal) clensMap.get("AUD_TRAIL_RPT_WRK_PK"));
                        audTrailRptWrkTMsg = (AUD_TRAIL_RPT_WRKTMsg) EZDTBLAccessor.findByKey(audTrailRptWrkTMsg);
                        if (audTrailRptWrkTMsg != null) {
                            EZDTBLAccessor.remove(audTrailRptWrkTMsg);
                        }
                    }
                }
            }
        }
    }
    //QC#18693
    private static void addBarcodeImageToPdf(String uniqueIdentifier, String pdfFilePath) {
        
        // ***1. Initialize barcode builder
        S21BarcodeBuilder builder = new S21BarcodeBuilder(S21BarcodeType.Datamatrix);
        
        // ***2. Open PDF
        S21PDFDocument pdf = new S21PDFDocument(pdfFilePath);
        
        // ***3. Get Total page of the PDF
        int pagesSize = pdf.getTotalPageSize();
        int pagesSizeCnt = ( pdf.getTotalPageSize() + 1 ) / 2;  //QC#30356 Add
        
        // Add Barcode image to Odd page in a PDF (1,3,5,7...)
        for (int pdfPageIdx = 1; pdfPageIdx <= pagesSize; pdfPageIdx++) {

            //int pageNum = pdfPageIdx;
            int pageNum = 0;

            if (pdfPageIdx % 2 > 0) {
                // Odd page (1,3,5...)
                pageNum = ((int) (pdfPageIdx / 2)) + 1;  //QC#30356 Mod
            } else {
                // Even page (2,4,6...) Skip to add barcode
                //pageNum = (int) (pdfPageIdx / 2);
                continue;
            }

            // ***4. Get the bar code image for the text
            // 1. 1-9 Unique Identifier
            // 2. 10-12 Page Number
            // 3. 13-15 Total Page Number
            //String key = uniqueIdentifier + String.format("%03d", pageNum) + String.format("%03d", pagesSize);  //QC#30356 Mod
            String key = uniqueIdentifier + String.format("%03d", pageNum) + String.format("%03d", pagesSizeCnt);
System.out.println("2D Unique Key[" + key + "]");
            BufferedImage barcode = builder.getBarCodeImage(key);

            // ***5. Add Barcode into PDF with specifying position
            // pdf.addBarcodeImage(pageNum, barcode, 294, 681, 22, 22); //QC#30356 Mod
            pdf.addBarcodeImage(pdfPageIdx, barcode, 294, 681, 22, 22);
        }
        pdf.save(); // Save edited PDF
System.out.println("Save edited PDF");
	}
}
