/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.batch.NFC.NFCB057001;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ACCT_DTTMsg;
import business.db.AR_STMT_INFOTMsg;
import business.parts.NFZC203001PMsg;

import com.canon.cusa.s21.api.NFZ.NFZC203001.NFZC203001;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeBuilder;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeType;
import com.canon.cusa.s21.framework.ZYP.aspose.pdf.S21PDFDocument;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.printing.S21ExecutionStatus;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.cups.S21CUPSPrintOption;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Create Statement Batch.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 02/15/2016   Fujitsu         M.Nakamura      Create
 * 12/13/2016   Hitachi         E.Kameishi      Update          QC#16337
 * 2018/10/02   Fujitsu         H.Ikeda         Update          QC#25825
 * 2018/11/02   Fujitsu         H.Ikeda         Update          QC#25825
 * 2019/02/27   Fujitsu         H.Ikeda         Update          QC#30517
 * 2019/06/17   Fujitsu         H.Ikeda         Update          QC#50797
 * 2020/02/03   Fujitsu         Y.Matsui        Update          QC#55625
 * 2020/02/12   Fujitsu         Y.Matsui        Update          QC#55625-1
 * 2022/01/26   CITS            G.Delgado       Update          QC#56682
 * 2024/03/29   CITS            T.Aizawa        Update          QC#63832
 */
public class NFCB057001 extends S21BatchMain {

    /** GLOBAL_COMPANY_CODE */
    private String glblCmpyCd = null;

    /** batProcDate */
    private String batProcDate = null;

    /** Account Date */
    private String acctDt = null;

    /** Termination Code */
    private TERM_CD termCd;

    /** Processing Count */
    private int totalRecordCnt = 0;

    /** normal Count */
    private int normalRecordCnt = 0;

    /** err Count */
    private int errorRecordCnt = 0;

    /** Message String PROGLAM ID. */
    private static final String[] MSG_PROGLAM_ID = {"NFCB057001" };
    // START 2016/12/13 E.Kameishi [QC#16337,ADD]
    /** ssm Batch Client */
    private S21SsmBatchClient ssmClient;

    /** AR Statement Date */
    String arStmtDt = null;
    // END 2016/12/13 E.Kameishi [QC#16337,ADD]

    // START 2018/10/02 H.ikeda [QC#25825,ADD]
    /** Statement Report ID */
    private static final String STMT_RPT_ID = "NFCF0010";

    /** Statement Report Branch Number for Print */
    private static final String STMT_RPT_BR_NUM_FOR_PRT = "01";
    // END  2018/10/02 H.ikeda [QC#25825,ADD]

    // START 2019/02/27 H.ikeda [QC#30517, ADD]
    /** var_char_const : STATEMENT_TICKMARK_FLG */
    public static final String STATEMENT_TICKMARK_FLG = "STATEMENT_TICKMARK_FLG";
    // END   2019/02/27 H.ikeda [QC#30517, ADD]

    // START 2019/06/17 H.ikeda [QC#50797, ADD]
    /** var_char_const : STATEMENT_CUPS_PAGE_COUNT */
    private static final String STATEMENT_CUPS_PAGE_COUNT = "NWCB011001_CUPS_PAGE_COUNT";

    /** Cups Count(Def:50) */
    private int  cupsPageCnt = 50;
    // END   2019/06/17 H.ikeda [QC#50797, ADD]

    // START 2020/02/03 [QC#55625,ADD]
    /** Process Mode: Data Creation */
    private static final String PROCESS_MODE_DATA_CREATION = "01";

    /** Process Mode: Print Request */
    private static final String PROCESS_MODE_PRINT_REQUEST = "02";

    /** Process Mode */
    private String processMode = null;
    // END 2020/02/03 [QC#55625,ADD]

    // START 2022/01/26 G.Delgado [QC#56682, ADD]
    /** It failed to print the AR Statement. */
    private static final String NFCM0918E = "NFCM0918E";
    // END 2022/01/26 G.Delgado [QC#56682, ADD]

    @Override
    protected void initRoutine() {

        S21InfoLogOutput.println("NFCM0584I", MSG_PROGLAM_ID);

        this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isEmpty(this.glblCmpyCd)) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("NFCM0501E", new String[] {NFCDbConst.GLBL_CMPY_CD });
        }

        // START 2020/02/03 [QC#55625,ADD]
        this.processMode = getUserVariable1();
        if (!checkProcessMode(this.processMode)) {
            throw new S21AbendException("NFCM0501E", new String[] {"Process Mode" });
        }
        // END 2020/02/03 [QC#55625,ADD]

        this.batProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        getAcctDt();
        if (this.acctDt == null) {
            setTermState(TERM_CD.ABNORMAL_END, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
            throw new S21AbendException("NFCM0501E", new String[] {"Account Date"});
        }

        // START 2016/12/13 E.Kameishi [QC#16337,ADD]
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
        arStmtDt = getArStmtIssDay();
        if (arStmtDt == null) {
            arStmtDt = this.acctDt;
        }
        // END 2016/12/13 E.Kameishi [QC#16337,ADD]

        // START 2019/06/17 H.ikeda [QC#50797, ADD]
        BigDecimal tmpVal = ZYPCodeDataUtil.getNumConstValue(STATEMENT_CUPS_PAGE_COUNT, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(tmpVal)) {
            this.cupsPageCnt = tmpVal.intValue();
        }
        // END   2019/06/17 H.ikeda [QC#50797, ADD]

        this.termCd = TERM_CD.NORMAL_END;
    }

    @Override
    protected void mainRoutine() {
        // START 2020/02/03 [QC#55625,MOD]
        if (isDataCreationMode()) {
            executeDataCreationProcess();

        } else if (isPrintRequestMode()) {
            executePrintRequestProcess();
        }
        // END 2020/02/03 [QC#55625,MOD]
    }

    // START 2020/02/03 [QC#55625,MOD] - Extract method from mainRoutine
    private void  executeDataCreationProcess(){
        NFZC203001 nfzc203001 = new NFZC203001();
        NFZC203001PMsg pMsg = new NFZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        // START 2016/12/13 E.Kameishi [QC#16337,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.acctDt);
        ZYPEZDItemValueSetter.setValue(pMsg.arStmtDt, this.arStmtDt);
        // END 2016/12/13 E.Kameishi [QC#16337,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.procModeCd, NFZC203001.MODE_01_PRINT_DATA_CRAT);

        nfzc203001.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            this.termCd = TERM_CD.ABNORMAL_END;
            // START 2017/06/09 J.Kim [QC#18413,MOD]
            // this.errorRecordCnt = 1;
            this.errorRecordCnt = getItemValue(pMsg.batProcTotRecCnt) - getItemValue(pMsg.batProcNormRecCnt);
            // END 2017/06/09 J.Kim [QC#18413,MOD]
            rollback();
            return;
        }
        // START 2017/06/09 J.Kim [QC#18413,MOD]
        // this.normalRecordCnt = 1;
        this.normalRecordCnt = getItemValue(pMsg.batProcNormRecCnt);
        // END 2017/06/09 J.Kim [QC#18413,MOD]
        commit();
        return;
    }

    private void  executePrintRequestProcess(){
        // If success, activate Report Processing. (insert EIP_RPT_PROC_LOG Table)
//        long processPk = 0;
//        if (ZYPCommonFunc.hasValue(pMsg.eipRptRqstPk)) {
//            processPk = pMsg.eipRptRqstPk.getValue().longValue();
//        }

        long processPk = getEipRptProcLogPk();

        if (0 != processPk) {
            S21EIPPrintingService service = new S21EIPPrintingService();

            // invokeAsyncPollingBatchTypeEIP (Async EIP Batch service call + Waiting completion per 1 minute)
            S21ExecutionStatus pdfCreationResult = service.invokeAsyncPollingBatchTypeEIP(processPk);
            boolean pdfResultFlg = pdfCreationResult.isSuccessful();
            if (!pdfResultFlg) { // Some PDF creations failed.
                Map<String, Object> eipResultChkParam = new HashMap<String, Object>();
                eipResultChkParam.put("glblCmpyCd", glblCmpyCd);
                eipResultChkParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
                List<Map<String, Object>> eipResultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getEIPResultChk", eipResultChkParam);
                if (eipResultList != null && eipResultList.size() > 0) {
                    for (Map<String, Object> eipResultMap : eipResultList) {
                        if (eipResultMap != null && eipResultMap.get("EIP_RPT_RQST_PK") != null) {
                            //send EIP error eMail
                            S21InfoLogOutput.println((BigDecimal) eipResultMap.get("EIP_RPT_RQST_PK") + " " +
                                                     (String) eipResultMap.get("RPT_RQST_STS_TXT") +  " " +
                                                     (String) eipResultMap.get("RPT_TTL_NM"));
                        }
                    }
                }
            }

            // START  2019/02/27 H.ikeda [QC#30517, ADD]
            // get eipRptRqstPkList
            List<Map<String, Object>> eipRptRqstPkList = getEipRptRqstPkListToOutput(processPk);
            // get STATEMENT_TICKMARK_FLG
            String tFlg = ZYPCodeDataUtil.getVarCharConstValue(STATEMENT_TICKMARK_FLG, glblCmpyCd);
            if (ZYPConstant.FLG_OFF_N.equals(tFlg)) {
                if (eipRptRqstPkList != null && eipRptRqstPkList.size() > 0) {
                    for (Map<String, Object> rstMap : eipRptRqstPkList) {
                        if (rstMap != null && rstMap.get("EIP_RPT_RQST_PK") != null) {
                            BigDecimal eipRptRqstPk = (BigDecimal) rstMap.get("EIP_RPT_RQST_PK");
                            if (ZYPCommonFunc.hasValue(eipRptRqstPk)) {
                                String filePath = service.getFilePathForCupsPrint(eipRptRqstPk.longValue(), 0);
                                if (ZYPCommonFunc.hasValue(filePath)) {
                                    try {
                                        String uniqueIdentifier = ZYPMaxTenDigitsNumbering.getUniqueID("CUPS_UNIQ_CD_2D"); //from AUTO_SQ_TEN_DIGIT
                                        addBarcodeImageToPdf(uniqueIdentifier, filePath);

                                        // START 2020/02/03 [QC#55625,ADD]
                                        updateRptOtptCpltFlg((BigDecimal) rstMap.get("STMT_SQ_PK"));
                                        // END 2020/02/03 [QC#55625,ADD]

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        S21InfoLogOutput.println("Failed to add barcode image:" + filePath + ":" + e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                // START 2020/02/03 [QC#55625,ADD]
                for (Map<String, Object> rstMap : eipRptRqstPkList) {
                    updateRptOtptCpltFlg((BigDecimal) rstMap.get("STMT_SQ_PK"));
                }
                // END 2020/02/03 [QC#55625,ADD]
            }
            // END    2019/02/27 H.ikeda [QC#30517, ADD]

            commit(); // commit the transaction only success data
            // 
            eipRptRqstPkList = getEipRptRqstPkListToPrintRequest(processPk);

            S21CUPSPrintOption printOption = new S21CUPSPrintOption();
            // START 2024/03/29 t.aizawa [QC#63832,MOD]
            // printOption.setFitToPage();
            printOption.setDoubleSideLongEdge();
            // END   2024/03/29 t.aizawa [QC#63832,MOD]
            List<Long> rptRqstPkList = new ArrayList<Long>(); //CUPS list
            List<BigDecimal> rptRqstStmtPkList = new ArrayList<BigDecimal>();
            // START  2018/11/02 H.ikeda [QC#25825,MOD]
            // START  2019/02/27 H.ikeda [QC#30517, DEL]
            //Map<String, Object> sqlParam = new HashMap<String, Object>();
            //sqlParam.put("glblCmpyCd", glblCmpyCd);
            //sqlParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
            //List<Map<String, Object>> eipRptRqstPkList = (List<Map<String, Object>>) ssmClient.queryObjectList("getEIPRptRqstPk", sqlParam);
            // END    2019/02/27 H.ikeda [QC#30517, DEL]
            if (eipRptRqstPkList != null && eipRptRqstPkList.size() > 0) {
                // START 2019/06/17 H.ikeda [QC#50797, MOD]
                int sCnt = 0;
                try {
                    // printerName
                    String printerName = service.getPrinterQueueName(STMT_RPT_ID, STMT_RPT_BR_NUM_FOR_PRT);
                    for (Map<String, Object> rstMap : eipRptRqstPkList) {
                        if (rstMap != null && rstMap.get("EIP_RPT_RQST_PK") != null) {
                            BigDecimal eipRptRqstPk = (BigDecimal) rstMap.get("EIP_RPT_RQST_PK");
                            rptRqstPkList.add(eipRptRqstPk.longValue());
                            rptRqstStmtPkList.add((BigDecimal) rstMap.get("STMT_SQ_PK"));
                            // START 2019/06/17 H.ikeda [QC#50797, MOD]
                            if (rptRqstPkList.size() == this.cupsPageCnt) {
                                // mergeAndPrintRequestToCups
                                service.mergeAndPrintRequestToCups(rptRqstPkList, printerName, printOption);
                                sCnt = sCnt + rptRqstPkList.size();
                                // START 2020/02/12 [QC#55625-1,MOD]
                                updateRptPrintRqstCpltFlg(rptRqstStmtPkList);
                                rptRqstPkList = new ArrayList<Long>();
                                rptRqstStmtPkList = new ArrayList<BigDecimal>();
                                // START 2020/02/12 [QC#55625-1,MOD]
                            }
                        }
                    }
                    if (rptRqstPkList.size() > 0) {
                        // mergeAndPrintRequestToCups
                        service.mergeAndPrintRequestToCups(rptRqstPkList, printerName, printOption);
                        sCnt = sCnt + rptRqstPkList.size();
                        // START 2020/02/03 [QC#55625,ADD]
                        updateRptPrintRqstCpltFlg(rptRqstStmtPkList);
                        // END 2020/02/03 [QC#55625,ADD]
                    }
                } catch (Exception e) {
                    this.termCd = TERM_CD.ABNORMAL_END;
                    this.errorRecordCnt = eipRptRqstPkList.size() - sCnt;
                    rollback();
                    e.printStackTrace();
                    return;
                }
                // END   2019/06/17 H.ikeda [QC#50797, MOD]

                this.normalRecordCnt = eipRptRqstPkList.size() - this.errorRecordCnt;
            }
//            if (pMsg.xxArStmtList.getValidCount() > 0) {
//                for (int i = 0; i < pMsg.xxArStmtList.getValidCount(); i++) {
//                    rptRqstPkList.add(pMsg.xxArStmtList.no(i).eipRptRqstPk.getValue().longValue());
//                }
//                try {
//                    // printerName
//                    String printerName = service.getPrinterQueueName(STMT_RPT_ID, STMT_RPT_BR_NUM_FOR_PRT);
//                    // mergeAndPrintRequestToCups
//                    service.mergeAndPrintRequestToCups(rptRqstPkList, printerName, printOption);
//                } catch (Exception e) {
//                    this.termCd = TERM_CD.ABNORMAL_END;
//                    this.errorRecordCnt = getItemValue(pMsg.batProcTotRecCnt) - getItemValue(pMsg.batProcNormRecCnt);
//                    rollback();
//                    e.printStackTrace();
//                    return;
//                }
//            }
            // END  2018/11/02 H.ikeda [QC#25825,MOD]

            // START 2022/01/26 G.Delgado [QC#56682, ADD]
            commit();

            eipRptRqstPkList = getEipRptRqstPkListToPrintComplete(processPk);
            if (eipRptRqstPkList != null && !eipRptRqstPkList.isEmpty()) {
                for (Map<String, Object> rstMap : eipRptRqstPkList) {
                    BigDecimal eipRptRqstPk = (BigDecimal) rstMap.get("EIP_RPT_RQST_PK");
                    S21InfoLogOutput.println("Print Failed. EIP_RPT_RQST_PK=" + eipRptRqstPk);
                }
                this.termCd = TERM_CD.ABNORMAL_END;
                throw new S21AbendException(NFCM0918E);
            }
            // END 2022/01/26 G.Delgado [QC#56682, ADD]
        }
        // END   2018/10/02 H.ikeda [QC#25825,ADD]

        // START 2022/01/26 G.Delgado [QC#56682, DEL]
        // commit();
        // END 2022/01/26 G.Delgado [QC#56682, DEL]
    }
    // END 2020/02/03 [QC#55625,MOD]

    // START 2019/02/27 H.ikeda [QC#30517, ADD]
    /** 
     * getEipRptRqstPkList
     * 
     * @param processPk long
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getEipRptRqstPkList(long processPk) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", glblCmpyCd);
        sqlParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getEIPRptRqstPk", sqlParam);
    }


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
        int pagesSizeCnt = ( pdf.getTotalPageSize() + 1 ) / 2;
        
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
    // END   2019/02/27 H.ikeda [QC#30517, ADD]

    @Override
    protected void termRoutine() {
        this.totalRecordCnt = this.normalRecordCnt + this.errorRecordCnt;
        setTermState(this.termCd, this.normalRecordCnt, this.errorRecordCnt, this.totalRecordCnt);
        S21InfoLogOutput.println("NFCM0593I", MSG_PROGLAM_ID);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB057001().executeBatch(NFCB057001.class.getSimpleName());
    }
    // START 2016/12/13 E.Kameishi [QC#16337,ADD]
    private String getArStmtIssDay() {
        Map<String, String> ssmParam = new HashMap<String, String>();

        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("actvFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("arAcctDt", this.acctDt);
        ssmParam.put("arStmtStsCd", AR_STMT_STS.PENDING);
        // START 2020/02/03 [QC#55625,ADD]
        if (isPrintRequestMode()) {
            ssmParam.put("arStmtStsCd", AR_STMT_STS.PRINTED);
        }
        // END 2020/02/03 [QC#55625,ADD]

        return (String) ssmClient.queryObject("getArStmtDt", ssmParam);
    }
    // END 2016/12/13 E.Kameishi [QC#16337,ADD]
    private void getAcctDt() {
        AR_ACCT_DTTMsg inMsg = new AR_ACCT_DTTMsg();
        // START 2016/12/13 E.Kameishi [QC#16337,MOD]
        final int dateLen = 6;
        // START 2016/12/13 E.Kameishi [QC#16337,MOD]
        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", this.glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.onlBatTpCd, "2");
        ZYPEZDItemValueSetter.setValue(inMsg.subSysCd, subSysCd);

        AR_ACCT_DTTMsg outMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            this.acctDt = "";
        } else {
            String lclAcctDt = outMsg.acctDt.getValue();
            if (lclAcctDt == null) {
                this.acctDt = "";
            } else {

                if (lclAcctDt.substring(0, dateLen).equals(this.batProcDate.substring(0, dateLen))) {
                    this.acctDt = this.batProcDate;
                } else {
                    this.acctDt = lclAcctDt;
                }
            }
        }
    }

    private int getItemValue(EZDPBigDecimalItem value) {

        if (!ZYPCommonFunc.hasValue(value)) {
            return 0;
        }
        return value.getValueInt();
    }

    // START 2020/02/03 [QC#55625,ADD]
    private boolean checkProcessMode(String mode) {
        return Arrays.asList(PROCESS_MODE_DATA_CREATION, PROCESS_MODE_PRINT_REQUEST).contains(mode);
    }

    private boolean isDataCreationMode() {
        return PROCESS_MODE_DATA_CREATION.equals(this.processMode);
    }

    private boolean isPrintRequestMode() {
        return PROCESS_MODE_PRINT_REQUEST.equals(this.processMode);
    }

    private void updateRptOtptCpltFlg(BigDecimal stmtSqPk) {
        AR_STMT_INFOTMsg inMsg = new AR_STMT_INFOTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.stmtSqPk.setValue(stmtSqPk);
        AR_STMT_INFOTMsg updTMsg = (AR_STMT_INFOTMsg) S21FastTBLAccessor.findByKey(inMsg);
        if (updTMsg != null) {
            updTMsg.rptOtptCpltFlg.setValue(ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(updTMsg);
        }
    }

    private void updateRptPrintRqstCpltFlg(List<BigDecimal> stmtSqPkList) {
        List<AR_STMT_INFOTMsg> updTmsgList = new ArrayList<AR_STMT_INFOTMsg>(stmtSqPkList.size());
        for (BigDecimal stmtSqPk : stmtSqPkList) {
            AR_STMT_INFOTMsg inMsg = new AR_STMT_INFOTMsg();
            inMsg.glblCmpyCd.setValue(glblCmpyCd);
            inMsg.stmtSqPk.setValue(stmtSqPk);
            AR_STMT_INFOTMsg updTMsg = (AR_STMT_INFOTMsg) S21FastTBLAccessor.findByKey(inMsg);
            if (updTMsg != null) {
                updTMsg.rptPrintRqstCpltFlg.setValue(ZYPConstant.FLG_ON_Y);
                updTmsgList.add(updTMsg);
            }
        }
        S21FastTBLAccessor.update(updTmsgList.toArray(new AR_STMT_INFOTMsg[updTmsgList.size()]));
    }

    private Long getEipRptProcLogPk() {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", glblCmpyCd);
        sqlParam.put("stmtPrintDt", this.arStmtDt);
        BigDecimal eipRptProcLogPk = (BigDecimal) ssmClient.queryObject("getEipRptProcLogPk", sqlParam);
        if (eipRptProcLogPk == null) {
            return 0L;
        }
        return eipRptProcLogPk.longValue();
    }

    private List<Map<String, Object>> getEipRptRqstPkListToOutput(long processPk) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", glblCmpyCd);
        sqlParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
        sqlParam.put("rptOtptCpltFlg", ZYPConstant.FLG_OFF_N);
        sqlParam.put("rtPrintRqstCpltFlg", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSuccessEIPRptRqstPk", sqlParam);
    }

    private List<Map<String, Object>> getEipRptRqstPkListToPrintRequest(long processPk) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", glblCmpyCd);
        sqlParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
        sqlParam.put("rptOtptCpltFlg", ZYPConstant.FLG_ON_Y);
        sqlParam.put("rtPrintRqstCpltFlg", ZYPConstant.FLG_OFF_N);
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getSuccessEIPRptRqstPk", sqlParam);
    }
    // END 2020/02/03 [QC#55625,ADD]

    // START 2022/01/26 G.Delgado [QC#56682, ADD]
    /**
     * getEipRptRqstPkListToPrintComplete
     * @param processPk long
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getEipRptRqstPkListToPrintComplete(long processPk) {
        Map<String, Object> sqlParam = new HashMap<String, Object>();
        sqlParam.put("glblCmpyCd", glblCmpyCd);
        sqlParam.put("eipRptProcLogPk", new BigDecimal(String.valueOf(processPk)));
        sqlParam.put("rptEmlOtptFlg", ZYPConstant.FLG_OFF_N);
        sqlParam.put("rptPrintOtptFlg", ZYPConstant.FLG_ON_Y);
        sqlParam.put("rptOtptCpltFlg", ZYPConstant.FLG_ON_Y);
        sqlParam.put("rptPrintRqstCpltFlg", ZYPConstant.FLG_ON_Y);
        return (List<Map<String, Object>>) ssmClient.queryObjectList("getEipRptRqstPkListToPrintComplete", sqlParam);
    }
    // END 2022/01/26 G.Delgado [QC#56682, ADD]
}
