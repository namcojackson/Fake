package com.canon.cusa.s21.batch.NSA.NSAB052001;

import static com.canon.cusa.s21.batch.NSA.NSAB052001.constant.NSAB052001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_PRC_CHNG_RECTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeBuilder;
import com.canon.cusa.s21.framework.ZYP.aspose.barcode.S21BarcodeType;
import com.canon.cusa.s21.framework.ZYP.aspose.pdf.S21PDFDocument;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_RULE_RCPNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailAttachment;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.printing.S21ExecutionStatus;
import com.canon.cusa.s21.framework.printing.S21PrintingUtil;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.cups.S21CUPSPrintOption;
import com.canon.cusa.s21.framework.printing.eip.S21CUPSFileOutputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;

/**
 * <pre>
 * Contract Agreement Letter Creation Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/03/2016   Hitachi         M.Gotou         Create          N/A
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 01/04/2017   Hitachi         K.Kitachi       Update          QC#16796
 * 01/23/2017   Hitachi         K.Kitachi       Update          QC#17245
 * 08/08/2017   Hitachi         K.Kim           Update          QC#20351
 * 08/15/2017   CITS            M.Naito         Update          QC#8661
 * 12/11/2017   Hitachi         K.Ochiai        Update          QC#23006
 * 03/07/2018   Hitachi         U.Kim           Update          QC#23842
 * 12/03/2018   Hitachi         S.Kitamura      Update          QC#29402
 * 2019/02/27   Hitachi         K.Kitachi       Update          QC#30517
 * 2019/03/01   Hitachi         K.Kitachi       Update          QC#30517
 * 2019/03/07   Hitachi         K.Fujimoto      Update          QC#30663
 * 2019/03/07   Hitachi         T.Tomita        Update          QC#30671
 * 2019/05/07   Hitachi         K.Kitachi       Update          QC#31341
 * 2019/06/13   Fujitsu         W.Honda         Update          QC#50798
 * 2019/06/13   Fujitsu         W.Honda         Update          QC#50744
 * 2020/03/17   Hitachi         A.Kohinata      Update          QC#56228
 * 2024/03/19   Hitachi         T.Nagae         Update          QC#63552
 * 2024/03/27   Hitachi         T.Nagae         Update          QC#63552
 * 2024/03/28   Hitachi         T.Nagae         Update          QC#63552
 * 2024/03/29   Hitachi         T.Nagae         Update          QC#63552
 * </pre>
 */
public class NSAB052001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** total Count */
    private int totalCount = 0;

    /** Error Count */
    private int errorCont = 0;

    /** sysTimeStamp */
    private String sysTimeStamp;

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2017/08/15 M.Naito [QC#8661, ADD]
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;
    // END 2017/08/15 M.Naito [QC#8661, ADD]

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** termination code */
    private TERM_CD termCd = null;

    // START 2017/08/15 M.Naito [QC#8661, DEL]
//    /** DS_CONTR_PK List */
//    private List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
//
//    /** DS_CONTR_PRC_EFF List (DS_CONTR_PRC_EFF_PK/EIP_RPT_RQST_PK) */
//    private List<Map<String, BigDecimal>> dsContrPrcEffList = new ArrayList<Map<String, BigDecimal>>();
//
//    /** DS_CONTR_NUM */
//    private String dsContrNum = null;
    // END 2017/08/15 M.Naito [QC#8661, DEL]

    /** Error Message list */
    private List<String> errList = new ArrayList<String>();

    // START 2019/02/27 K.Kitachi [QC#30517, ADD]
    /** 2D Barcode Avoid Flag. */
    private String twodBarcodeAvoidFlag;
    // END 2019/02/27 K.Kitachi [QC#30517, ADD]

    // Add Start 2019/03/07 QC#30671
    private S21EIPPrintingService service;
    // Add End 2019/03/07 QC#30671

    // Add Start 2019/06/13 QC#50798
    private int cupsPageCnt = 50;
    // Add End 2019/06/13 QC#50798

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        new NSAB052001().executeBatch(NSAB052001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        this.glblCmpyCd = getGlobalCompanyCode();
        // Global company code
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NASM0010E);
        }

        // Sales date
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(NASM0011E);
        }

        // Initialization
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        // START 2017/08/15 M.Naito [QC#8661, ADD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(getClass());
        // END 2017/08/15 M.Naito [QC#8661, ADD]
        this.termCd = TERM_CD.NORMAL_END;
        sysTimeStamp = ZYPDateUtil.getCurrentSystemTime(TYPE_TIME_STAMP);

        this.excParam = new S21SsmExecutionParameter();
        this.excParam.setFetchSize(FETCH_SIZE_MAX);
        this.excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        this.excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        this.excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // START 2019/02/27 K.Kitachi [QC#30517, ADD]
        this.twodBarcodeAvoidFlag = ZYPCodeDataUtil.getVarCharConstValue(RENEWAL_TICKMARK_FLG, this.glblCmpyCd);
        if (!hasValue(this.twodBarcodeAvoidFlag)) {
            this.twodBarcodeAvoidFlag = ZYPConstant.FLG_OFF_N;
        }
        // END 2019/02/27 K.Kitachi [QC#30517, ADD]

        // START 2019/06/13 W.Honda [QC#50798, ADD]
        BigDecimal tmpVal = ZYPCodeDataUtil.getNumConstValue(CUPS_PAGE_CNT_NUM_CONST_NM, this.glblCmpyCd);
        if (hasValue(tmpVal)) {
            this.cupsPageCnt = tmpVal.intValue();
        }
        // END 2019/06/31 W.Honda [QC#50798, ADD]
    }

    @Override
    protected void mainRoutine() {

        // START 2019/05/07 K.Kitachi [QC#31341, MOD]
//        doProcess();
        List<Map<String, Object>> ltrRptCtrlList = getLtrRptCtrl();
        for (Map<String, Object> ltrRptCtrl : ltrRptCtrlList) {
        // START 2024/03/19 T.Nagae [QC#63552, MOD]
//            doProcess(ltrRptCtrl);
            doProcess(ltrRptCtrl, NSAF0030);
            doProcess(ltrRptCtrl, NSAF0040);
            doProcess(ltrRptCtrl, NSAF0050);
            doProcess(ltrRptCtrl, NSAF0060);
        // END   2024/03/19 T.Nagae [QC#63552, MOD]
        }
        // END 2019/05/07 K.Kitachi [QC#31341, MOD]
        if (!errList.isEmpty()) {
            sendErrMail(errList);
        }
    }

    @Override
    protected void termRoutine() {

        if (this.errorCont > 0) {
            // START 2018/03/07 U.Kim [QC#23842, MOD]
            // this.termCd = TERM_CD.ABNORMAL_END;
            this.termCd = TERM_CD.WARNING_END;
            // START 2018/03/07 U.Kim [QC#23842, MOD]
        }
        // Set term code and total count.
        setTermState(this.termCd, this.totalCount - this.errorCont, this.errorCont, this.totalCount);

    }

    // START 2017/08/15 M.Naito [QC#8661, DEL]
//    /**
//     * doProcess
//     */
//    private void doProcess() {
//
//        List<DS_CONTR_PRC_EFFTMsg> updateTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
//
//// START 2017/08/08 K.Kim [QC#20351, DEL]
////        try {
//// END 2017/08/08 K.Kim [QC#20351, DEL]
//            // 2.(1)   get RNWH(Renewal Hold) Target
//            getPriceRenewalLetterList("RNWH");
//            // 2.(2)-1 get ACTV(Active) Target
//            getPriceRenewalLetterList("ACTV");
//            // 2.(2)-2 get SIGD(Signed) Target
//            getPriceRenewalLetterList("SIGD");
//
//            // 2.(3) update DS_CONTR_PRC_EFF
//            for (Map<String, BigDecimal> map : dsContrPrcEffList) {
//                updateTMsgList.add(setForUpdateDsContrPrcEff(map.get("dsContrPrcEffPk"), this.slsDt, map.get("eipRptRqstPk")));
//            }
//            if (!updateDsContrPrcEff(updateTMsgList)) {
//                addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrNum);
//                this.errorCont++;
//                return;
//            }
//
//            // add start 2016/06/06 CSA Defect#1523, 4624
//            callContrTrk(this.dsContrPkList, this.errList);
//            // add end 2016/06/06 CSA Defect#1523, 4624
//
//// START 2017/08/08 K.Kim [QC#20351, DEL]
////            // 3. delete Work Table
////            for (int i = 0; i < dsContrPkList.size(); i++) {
////                if (!deletePrcRnwLtrWrk(this.glblCmpyCd, dsContrPkList.get(i))) {
////                    addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrNum);
////                    this.errorCont++;
////                    return;
////                }
////                if (!deleteTermCondLtrWrk(this.glblCmpyCd, dsContrPkList.get(i))) {
////                    addErrList(errList, S21MessageFunc.clspGetMessage(ZZZM9004E), dsContrNum);
////                    this.errorCont++;
////                    return;
////                }
////                this.totalCount++;
////            }
////        } catch (SQLException e) {
////            sqlExceptionHandler(e);
////        }
//// END 2017/08/08 K.Kim [QC#20351, DEL]
//    }
//
//    /**
//     * getPriceRenewalLetterList
//     * @param dsContrCtrlStsCd(DS_CONTR_CTRL_STS_CD)
//     */
//    private void getPriceRenewalLetterList(String dsContrCtrlStsCd) {
//
//        PreparedStatement psLetterlList = null;
//        ResultSet rsLetter = null;
//        BigDecimal dsContrPk = BigDecimal.ZERO;
//        BigDecimal dsContrPrcEffPk = BigDecimal.ZERO;
//
//        S21EIPPrintingService service = new S21EIPPrintingService();
//        S21ReportRequestBean request = null;
//        S21InputParameter inputParam = null;
//        S21PrinterOutputParameter outputParam = null;
//        String rptTtlNm = null;
//        String printJobName = null;
//        long eipRptRqstPk = 0;
//        // START 2017/01/23 K.Kitachi [QC#17245, ADD]
//        int loopCount = 0;
//        // END 2017/01/23 K.Kitachi [QC#17245, ADD]
//
//        try {
//            psLetterlList = ssmLLClient.createPreparedStatement("getPriceRenewalLetterList", setParamForLetter(dsContrCtrlStsCd), excParam);
//            rsLetter = psLetterlList.executeQuery();
//
//            while (rsLetter.next()) {
//                // get DS_CONTR_PK
//                dsContrPk = rsLetter.getBigDecimal("DS_CONTR_PK");
//                dsContrNum = rsLetter.getString("DS_CONTR_NUM");
//                // get DS_CONTR_PRC_EFF_PK
//                dsContrPrcEffPk = rsLetter.getBigDecimal("DS_CONTR_PRC_EFF_PK");
//
//                // get DS_CONTR_PK -> List
//                if (!dsContrPkList.contains(dsContrPk)) {
//                    dsContrPkList.add(dsContrPk);
//                }
//
//                // make EIP Request
//                request = new S21ReportRequestBean(REPORT_ID);
//                request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
//                request.setRptArcFlg(true);
//                rptTtlNm = RPT_TTL_NAME + sysTimeStamp;
//                request.setRptTtlNm(rptTtlNm);
//
//                inputParam = request.getInputParamBeanInstance();
//
//                inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
//                inputParam.addReportParameter(DS_CONTR_PK, dsContrPk);
//                inputParam.addReportParameter(SLS_DT, this.slsDt);
//                inputParam.addReportParameter(BIZ_APP_ID, "NSAB0520");
//                inputParam.addReportParameter(OTPT_OP_CD, "Batch");
//                inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
//
//                request.setInputParamBean(inputParam);
//
//                outputParam = request.getPrintOutParamBeanInstance();
//                outputParam.setBranchNo("00");
//
//                printJobName = PRINT_JOB_NAME + sysTimeStamp;
//                outputParam.setPrintJobName(printJobName);
//
//                // get EIP_RPT_RQST_PK
//                eipRptRqstPk = service.createReportByAsync(request);
//
//                // set DS_CONTR_PRC_EFF_PK -> List
//                // set EIP_RPT_RQST_PK -> List
//                Map<String, BigDecimal> mapParam = new HashMap<String, BigDecimal>();
//                mapParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
//                mapParam.put("eipRptRqstPk", new BigDecimal(eipRptRqstPk));
//                dsContrPrcEffList.add(mapParam);
//                // START 2017/01/23 K.Kitachi [QC#17245, MOD]
////                this.totalCount++;
//                loopCount++;
//                // END 2017/01/23 K.Kitachi [QC#17245, MOD]
//            }
//        } catch (SQLException e) {
//            sqlExceptionHandler(e);
//        } finally {
//            // START 2017/01/23 K.Kitachi [QC#17245, ADD]
//            if (loopCount > 0) {
//                service.activateAsyncReportJob();
//                this.totalCount = this.totalCount + loopCount;
//            }
//            // END 2017/01/23 K.Kitachi [QC#17245, ADD]
//            S21SsmLowLevelCodingClient.closeResource(psLetterlList, rsLetter);
//        }
//    }
//
//    /**
//     * setParamForLetter
//     * @return 
//     */
//    private Map<String, Object> setParamForLetter(String dsContrCtrlStsCd) {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("glblCmpyCd", this.glblCmpyCd);
//        paramMap.put("dsCntCltStsCd", dsContrCtrlStsCd);
//        return paramMap;
//    }
//
//    /**
//     * setForUpdateDsContrPrcEff
//     * @param dsContrPrcEffPk
//     * @param rnwAdvNtcDt
//     * @param eipRptRqstPk
//     * @return
//     */
//    private DS_CONTR_PRC_EFFTMsg setForUpdateDsContrPrcEff(BigDecimal dsContrPrcEffPk, String rnwAdvNtcDt, BigDecimal eipRptRqstPk) {
//        DS_CONTR_PRC_EFFTMsg inParam = new DS_CONTR_PRC_EFFTMsg();
//        setValue(inParam.glblCmpyCd, this.glblCmpyCd);
//        setValue(inParam.dsContrPrcEffPk, dsContrPrcEffPk);
//        setValue(inParam.rnwAdvNtcDt, rnwAdvNtcDt);
//        setValue(inParam.eipRptRqstPk, eipRptRqstPk);
//        return inParam;
//    }
//
//    /**
//     * updateDsContrPrcEff
//     * @param inMsgLst
//     * @return
//     */
//    private boolean updateDsContrPrcEff(List<DS_CONTR_PRC_EFFTMsg> inMsgLst) {
//        // START 2017/01/04 K.Kitachi [QC#16796, ADD]
//        if (inMsgLst.size() == 0) {
//            return true;
//        }
//        // END 2017/01/04 K.Kitachi [QC#16796, ADD]
//        DS_CONTR_PRC_EFFTMsg[] outTMsgArray = new DS_CONTR_PRC_EFFTMsg[inMsgLst.size()];
//        List<DS_CONTR_PRC_EFFTMsg> outTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
//
//        for (DS_CONTR_PRC_EFFTMsg inTMsg : inMsgLst) {
//            setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(inTMsg.dsContrPrcEffPk, inTMsg.dsContrPrcEffPk);
//            DS_CONTR_PRC_EFFTMsg outTMsg = (DS_CONTR_PRC_EFFTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
//            if (outTMsg == null) {
//                rollback();
//                return false;
//            }
//            setValue(outTMsg.rnwAdvNtcDt, inTMsg.rnwAdvNtcDt);
//            setValue(outTMsg.eipRptRqstPk, inTMsg.eipRptRqstPk);
//            outTMsgList.add(outTMsg);
//            this.totalCount++;
//        }
//        S21FastTBLAccessor.update(outTMsgList.toArray(outTMsgArray));
//        return true;
//    }
    // END 2017/08/15 M.Naito [QC#8661, DEL]

    // START 2017/08/15 M.Naito [QC#8661, ADD]
    /**
     * doProcess
     */
    // START 2019/05/07 K.Kitachi [QC#31341, MOD]
//    private void doProcess() {
    // START 2024/03/19 T.Nagae [QC#63552, MOD]
//    private void doProcess(Map<String, Object> ltrRptCtrl) {
      private void doProcess(Map<String, Object> ltrRptCtrl, String rptId) {
    // END   2024/03/19 T.Nagae [QC#63552, MOD]
    // END 2019/05/07 K.Kitachi [QC#31341, MOD]
        // Add Start 2019/03/07 QC#30671
        this.service = new S21EIPPrintingService();
        // Add End 2019/03/07 QC#30671
        PreparedStatement psDsContrPkList = null;
        ResultSet rsDsContrPk = null;
        // START 2019/03/01 K.Kitachi [QC#30517, ADD]
        List<Long> eipRptRqstPkList = new ArrayList<Long>();
        // END 2019/03/01 K.Kitachi [QC#30517, ADD]

        // START 2024/03/19 T.Nagae [QC#63552, ADD]
        List<Map<String, Object>> eipRptRqstList = new ArrayList<Map<String, Object>>();
        // END 2024/03/19 T.Nagae [QC#63552, ADD]

        try {
            // get RenewalEscalationLetter target
            // START 2019/05/07 K.Kitachi [QC#31341, MOD]
//            rsDsContrPk = getRenewalEscalationLetterList(psDsContrPkList);
            // START 2024/03/19 T.Nagae [QC#63552, MOD]
            //rsDsContrPk = getRenewalEscalationLetterList(psDsContrPkList, (String) ltrRptCtrl.get("SVC_LINE_BIZ_CD"), (BigDecimal) ltrRptCtrl.get("SVC_RG_PK"));
            rsDsContrPk = getRenewalEscalationLetterList(psDsContrPkList, (String) ltrRptCtrl.get("SVC_LINE_BIZ_CD"), (BigDecimal) ltrRptCtrl.get("SVC_RG_PK"), rptId);
            // END   2024/03/19 T.Nagae [QC#63552, MOD]
            // END 2019/05/07 K.Kitachi [QC#31341, MOD]
            // Mod Start 2019/03/07 QC#30671
            // Create PDF
            // START 2024/03/19 T.Nagae [QC#63552, DEL]
//            List<Map<String, Object>> eipRptRqstList = new ArrayList<Map<String,Object>>();
            // END   2024/03/19 T.Nagae [QC#63552, DEL]
            while (rsDsContrPk.next()) {
                this.totalCount++;

                BigDecimal dsContrPk = rsDsContrPk.getBigDecimal("DS_CONTR_PK");
                String dsContrNum = rsDsContrPk.getString("DS_CONTR_NUM");
                // add start 2020/03/17 QC#56228
                String rnwEffFromDt = rsDsContrPk.getString("RNW_EFF_FROM_DT");
                String uplftEffFromDt = rsDsContrPk.getString("UPLFT_EFF_FROM_DT");
                // add end 2020/03/17 QC#56228

                // write RenewalEscalationLetter
                // BigDecimal eipRptRqstPk = writeRenewalEscalationLetter(dsContrPk, dsContrNum);
                // mod start 2020/03/17 QC#56228
                //List<Map<String, Object>> rnwEsclLttrList = writeRenewalEscalationLetter(dsContrPk, dsContrNum);
                // START 2024/03/19 T.Nagae [QC#63552, MOD]
                //List<Map<String, Object>> rnwEsclLttrList = writeRenewalEscalationLetter(dsContrPk, dsContrNum, rnwEffFromDt, uplftEffFromDt);
                List<Map<String, Object>> rnwEsclLttrList = writeRenewalEscalationLetter(dsContrPk, dsContrNum, rnwEffFromDt, uplftEffFromDt, rptId);
                // END   2024/03/19 T.Nagae [QC#63552, MOD]
                // mod end 2020/03/17 QC#56228
                // if (eipRptRqstPk.compareTo(BigDecimal.ZERO) == 0) {
                if (rnwEsclLttrList.size() == 0) {
                    rollback();
                    addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0691E), dsContrNum);
                    this.errorCont++;
                    continue;
                }
                for (Map<String, Object> rnwEsclLttr : rnwEsclLttrList) {
                    eipRptRqstList.add(rnwEsclLttr);
                }
                commit();
                // // START 2019/03/01 K.Kitachi [QC#30517, ADD]
                // if (!eipRptRqstPkList.contains(eipRptRqstPk.longValue())) {
                //     eipRptRqstPkList.add(eipRptRqstPk.longValue());
                // }
                // // END 2019/03/01 K.Kitachi [QC#30517, ADD]
            }
            if (eipRptRqstList.size() == 0) {
                return;
            }
            long processPk = this.service.activateAsyncReportJob();
            commit();

            // Polling
            S21ExecutionStatus pdfCreationResult = this.service.invokeAsyncPollingBatchTypeEIP(processPk);
            boolean pdfResultFlg = pdfCreationResult.isSuccessful();
            if (!pdfResultFlg) {
                return;
            }

            // Add Barcode to PDF
            List<Map<String, Object>> updContrList = new ArrayList<Map<String,Object>>();
            for (Map<String, Object> eipRptRqst : eipRptRqstList) {
                BigDecimal eipRptRqstPk = (BigDecimal) eipRptRqst.get(EIP_RPT_RQST_PK);
                String dsContrNum = (String) eipRptRqst.get(DS_CONTR_NUM);
                String filePath = service.getFilePathForCupsPrint(eipRptRqstPk.longValue(), 0);
                if (hasValue(filePath)) {
                    try {
                        if (!ZYPConstant.FLG_ON_Y.equals(this.twodBarcodeAvoidFlag)) {
                            String uniqueIdentifier = ZYPMaxTenDigitsNumbering.getUniqueID("CUPS_UNIQ_CD_2D"); // from AUTO_SQ_TEN_DIGIT
                            addBarcodeImageToPdf(uniqueIdentifier, filePath);
                        }
                        commit();
                    } catch (Exception e) {
                        rollback();
                        e.printStackTrace();
                        addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0691E), dsContrNum);
                        this.errorCont++;
                        continue;
                    }
                    updContrList.add(eipRptRqst);
                    // START 2019/05/07 K.Kitachi [QC#31341, ADD]
                    eipRptRqstPkList.add(eipRptRqstPk.longValue());
                    // END 2019/05/07 K.Kitachi [QC#31341, ADD]
                }
            }

            // Update Contract
            for (Map<String, Object> updContr : updContrList) {
                BigDecimal dsContrPk = (BigDecimal) updContr.get(DS_CONTR_PK);
                String dsContrNum = (String) updContr.get(DS_CONTR_NUM);
                BigDecimal eipRptRqstPk = (BigDecimal) updContr.get(EIP_RPT_RQST_PK);

                // update DS_CONTR_PRC_EFF
                List<BigDecimal> dsContrPrcEffPkList = getForUpdateDsContrPrcEff(dsContrPk);
                if (!updateDsContrPrcEff(dsContrPrcEffPkList, this.slsDt, eipRptRqstPk)) {
                    rollback();
                    addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0691E), dsContrNum);
                    this.errorCont++;
                    continue;
                }

                // update DS_CONTR_PRC_CHNG_REC
                if (!updateDsContrPrcChngRec(dsContrPk)) {
                    rollback();
                    addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0691E), dsContrNum);
                    this.errorCont++;
                    continue;
                }

                if (!callContrTrkAPI(dsContrPk, errList)) {
                    rollback();
                    addErrList(errList, S21MessageFunc.clspGetMessage(NSAM0691E), dsContrNum);
                    this.errorCont++;
                    continue;
                }
                commit();
            }
            // Mod Start 2019/03/07 QC#30671
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(psDsContrPkList, rsDsContrPk);
            // START 2019/03/01 K.Kitachi [QC#30517, ADD]
            // START 2019/05/07 K.Kitachi [QC#31341, MOD]
//            if (eipRptRqstPkList.size() > 0) {
            if (eipRptRqstPkList.size() > 0 && STMT_RPT_BR_NUM_FOR_PRT.equals((String) ltrRptCtrl.get("RPT_BR_NUM"))) {
            // END 2019/05/07 K.Kitachi [QC#31341, MOD]
                S21EIPPrintingService service = new S21EIPPrintingService();
                // START 2024/03/19 T.Nagae [QC#63552,MOD]
                //String printerName = service.getPrinterQueueName(NSAF0020, STMT_RPT_BR_NUM_FOR_PRT);
                String printerName01 = service.getPrinterQueueName(rptId, STMT_RPT_BR_NUM_FOR_PRT);
                String printerName02 = service.getPrinterQueueName(rptId, STMT_RPT_BR_NUM_FOR_PRT_02);
                // END   2024/03/19 T.Nagae [QC#63552,MOD]
                S21CUPSPrintOption printOption = new S21CUPSPrintOption();
                printOption.setFitToPage();
                // START 2019/06/13 W.Honda [QC#50798, MOD]
//                service.mergeAndPrintRequestToCups(eipRptRqstPkList, printerName, printOption);
                // START 2024/03/19 T.Nagae [QC#63552,ADD]
                String custBllgVcleCd = null;
                Map<String, Object> eipRptRqstInfo = null;
                // END 2024/03/19 T.Nagae [QC#63552,ADD]
                // START 2024/04/11 T.Nagae [QC#63552,MOD]
//                List<Long> wrkEipRptRqstPkList = new ArrayList<Long>();
                List<Long> wrkEipRptRqstPkList01 = new ArrayList<Long>();
                List<Long> wrkEipRptRqstPkList02 = new ArrayList<Long>();
                // END 2024/04/11 T.Nagae [QC#63552,MOD]
                for(int i = 0; i < eipRptRqstPkList.size(); i++) {
                    // START 2024/04/11 T.Nagae [QC#63552,MOD]
//                    wrkEipRptRqstPkList.add(eipRptRqstPkList.get(i));
//                    if(wrkEipRptRqstPkList.size() >= this.cupsPageCnt) {
//                        service.mergeAndPrintRequestToCups(wrkEipRptRqstPkList, printerName, printOption);
//                        wrkEipRptRqstPkList = new ArrayList<Long>();
//                    }
                    eipRptRqstInfo = eipRptRqstList.get(i);
                    BigDecimal eipRptRqstPk = (BigDecimal) eipRptRqstInfo.get(EIP_RPT_RQST_PK);

                    custBllgVcleCd = getCustBllgVcleCd((String) eipRptRqstInfo.get(DS_ACCT_NUM), CUST_INV_SRC.RNW_UPLFT);

                    if (hasValue(custBllgVcleCd)) {
                        // Print Only
                        if (CUST_BLLG_VCLE.PRINT_ONLY.equals(custBllgVcleCd)) {
                            wrkEipRptRqstPkList01.add(eipRptRqstPk.longValue());
                        }
                        // Strategic Accounts Print Only or Strategic Accounts Print & Email
                        if (CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_ONLY.equals(custBllgVcleCd) || CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(custBllgVcleCd)) {
                            wrkEipRptRqstPkList02.add(eipRptRqstPk.longValue());
                        }
                        // Email Only or Strategic Accounts Print & Email
                        if (CUST_BLLG_VCLE.EMAIL_ONLY.equals(custBllgVcleCd) || CUST_BLLG_VCLE.STRATEGIC_ACCOUNTS_PRINT_AND_EMAIL.equals(custBllgVcleCd)) {
                            this.sendAccountMail(eipRptRqstInfo);
                        }
                    } else {
                        wrkEipRptRqstPkList01.add(eipRptRqstPk.longValue());
                    }

                    if (wrkEipRptRqstPkList01.size() >= this.cupsPageCnt) {
                        service.mergeAndPrintRequestToCups(wrkEipRptRqstPkList01, printerName01, printOption);
                        wrkEipRptRqstPkList01 = new ArrayList<Long>();
                    }
                    if (wrkEipRptRqstPkList02.size() >= this.cupsPageCnt) {
                        service.mergeAndPrintRequestToCups(wrkEipRptRqstPkList02, printerName02, printOption);
                        wrkEipRptRqstPkList02 = new ArrayList<Long>();
                    }

                    // END 2024/04/11 T.Nagae [QC#63552,MOD]
                }
                // START 2024/04/11 T.Nagae [QC#63552,MOD]
//                if (wrkEipRptRqstPkList.size() > 0) {
//                    service.mergeAndPrintRequestToCups(wrkEipRptRqstPkList, printerName, printOption);
//                }
                if (wrkEipRptRqstPkList01.size() > 0) {
                    service.mergeAndPrintRequestToCups(wrkEipRptRqstPkList01, printerName01, printOption);
                }
                if (wrkEipRptRqstPkList02.size() > 0) {
                    service.mergeAndPrintRequestToCups(wrkEipRptRqstPkList02, printerName02, printOption);
                }
                // END 2024/04/11 T.Nagae [QC#63552,MOD]

                // END 2019/06/13 W.Honda [QC#50798, MOD]
            }
            // END 2019/03/01 K.Kitachi [QC#30517, ADD]
        }
    }

    /**
     * getRenewalEscalationLetterList
     * @return ResultSet
     */
    // START 2019/05/07 K.Kitachi [QC#31341, ADD]
//    private ResultSet getRenewalEscalationLetterList(PreparedStatement psLetterlList) {

    // START 2024/03/19 T.Nagae [QC#63552, MOD]
    //private ResultSet getRenewalEscalationLetterList(PreparedStatement psLetterlList, String svcLineBizCd, BigDecimal svcRgPk) {
    private ResultSet getRenewalEscalationLetterList(PreparedStatement psLetterlList, String svcLineBizCd, BigDecimal svcRgPk, String rptId) {
    // END   2024/03/19 T.Nagae [QC#63552, MOD]
    // END 2019/05/07 K.Kitachi [QC#31341, ADD]

        ResultSet rs = null;

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            // START 2019/05/07 K.Kitachi [QC#31341, ADD]
            paramMap.put("svcLineBizCd", svcLineBizCd);
            paramMap.put("svcRgPk", svcRgPk);
            // END 2019/05/07 K.Kitachi [QC#31341, ADD]
            // add start 2020/03/17 QC#56228
            // START 2024/03/19 T.Nagae [QC#63552, MOD]
            //paramMap.put("prtId", NSAF0020);
            paramMap.put("prtId", rptId);
            // END   2024/03/19 T.Nagae [QC#63552, MOD]
            // add end 2020/03/17 QC#56228

            psLetterlList = ssmLLClient.createPreparedStatement("getRenewalEscalationLetterList", paramMap, excParam);
            rs = psLetterlList.executeQuery();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return rs;
    }

    // Mod Start 2019/03/07 QC#30671
    /**
     * writeRenewalEscalationLetter
     * @param dsContrPk BigDecimal
     * @param dsContrNum String
     * @return List<Map<String, Object>>
     */
    // mod start 2020/03/17 QC#56228
    //private List<Map<String, Object>> writeRenewalEscalationLetter(BigDecimal dsContrPk, String dsContrNum) {
    // START 2024/03/19 T.Nagae [QC#63552, MOD]
    //private List<Map<String, Object>> writeRenewalEscalationLetter(BigDecimal dsContrPk, String dsContrNum, String rnwEffFromDt, String uplftEffFromDt) {
    private List<Map<String, Object>> writeRenewalEscalationLetter(BigDecimal dsContrPk, String dsContrNum, String rnwEffFromDt, String uplftEffFromDt, String rptId) {
    // END   2024/03/19 T.Nagae [QC#63552, MOD]
    // mod end 2020/03/17 QC#56228
        List<Map<String, Object>> rtnList = new ArrayList<Map<String,Object>>();
        // START 2018/12/03 S.Kitamura [QC#29402, DEL]
        //PreparedStatement psLetterlList = null;
        //ResultSet rsLetter = null;
        // END 2018/12/03 S.Kitamura [QC#29402, DEL]
        // S21EIPPrintingService service = new S21EIPPrintingService();
        S21ReportRequestBean request = null;
        S21InputParameter inputParam = null;
        S21PrinterOutputParameter outputParam = null;
        String rptTtlNm = null;
        String printJobName = null;
        long eipRptRqstPk = 0;
        // int loopCount = 0;

        String printerAvoidFlag = ZYPCodeDataUtil.getVarCharConstValue(NSAB0520_PRINTER_AVOID_FLAG, glblCmpyCd);
        if (printerAvoidFlag == null) {
            printerAvoidFlag = ZYPConstant.FLG_OFF_N;
        }

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            // START 2017/12/11 K.Ochiai [QC#23006, ADD]
            // START 2024/03/19 T.Nagae [QC#63552, MOD]
            //paramMap.put("prtId", NSAF0020);
            paramMap.put("prtId", rptId);
            // END   2024/03/19 T.Nagae [QC#63552, MOD]
            // END 2017/01/23 K.Ochiai [QC#23006, ADD]

            // get EIP Information
            paramMap.put("dsContrPk", dsContrPk);

            // add start 2020/03/17 QC#56228
            List<String> bizAppIdList = new ArrayList<String>();
            if (hasValue(rnwEffFromDt)) {
                bizAppIdList.add(BIZ_APP_ID_NSAB0030);
            }
            if (hasValue(uplftEffFromDt)) {
                bizAppIdList.add(BIZ_APP_ID_NSAB0360);
            }
            paramMap.put("bizAppIdList", bizAppIdList);
            // add end 2020/03/17 QC#56228

            // START 2018/12/03 S.Kitamura [QC#29402, MOD]
            //PreparedStatement psEipInfoList = null;
            //ResultSet rsEipInfo = null;
            //psEipInfoList = ssmLLClient.createPreparedStatement("getLetterInfoForEIP", paramMap, excParam);
            //rsEipInfo = psEipInfoList.executeQuery();
            List<Map<String, Object>> rsEipInfoList = ssmBatchClient.queryObjectList("getLetterInfoForEIP", paramMap);

            Map<String, Object> rtnMap;
            for (Map<String, Object> rsEipInfo : rsEipInfoList) {
            //while (rsEipInfo.next()) {
                // make EIP Request
                //request = new S21ReportRequestBean(rsEipInfo.getString("RPT_ID"));
                request = new S21ReportRequestBean((String) rsEipInfo.get("RPT_ID"));
                // END 2018/12/03 S.Kitamura [QC#29402, MOD]
                request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                request.setRptArcFlg(true);
                rptTtlNm = RPT_TTL_NAME + dsContrNum + " " + sysTimeStamp;
                request.setRptTtlNm(rptTtlNm);

                inputParam = request.getInputParamBeanInstance();

                inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
                inputParam.addReportParameter(DS_CONTR_PK, dsContrPk);
                // START 2018/12/03 S.Kitamura [QC#29402, MOD]
                //inputParam.addReportParameter(BIZ_APP_ID, rsEipInfo.getString("BIZ_APP_ID"));
                //inputParam.addReportParameter(RPT_ID, rsEipInfo.getString("RPT_ID"));
                inputParam.addReportParameter(BIZ_APP_ID, (String) rsEipInfo.get("BIZ_APP_ID"));
                inputParam.addReportParameter(RPT_ID, (String) rsEipInfo.get("RPT_ID"));
                // END 2018/12/03 S.Kitamura [QC#29402, MOD]
                inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
                // START 2019/03/07 K.Fujimoto [QC#30663, ADD]
                inputParam.addReportParameter(SVC_CONTR_BR_CD, (String) rsEipInfo.get("SVC_CONTR_BR_CD"));
                inputParam.addReportParameter(DS_CONTR_NUM, (String) rsEipInfo.get("DS_CONTR_NUM"));
                inputParam.addReportParameter(DS_ACCT_NUM, (String) rsEipInfo.get("DS_ACCT_NUM"));
                inputParam.addReportParameter(LINE_BIZ_TP_DESC_TXT, (String) rsEipInfo.get("LINE_BIZ_TP_DESC_TXT"));
                // END   2019/03/07 K.Fujimoto [QC#30663, ADD]

                request.setInputParamBean(inputParam);

                //If Printer is not setup, PDF only out.
                if (!ZYPConstant.FLG_ON_Y.equals(printerAvoidFlag)) {
                    outputParam = request.getPrintOutParamBeanInstance();
                    // START 2018/12/03 S.Kitamura [QC#29402, MOD]
                    //outputParam.setBranchNo(rsEipInfo.getString("RPT_BR_NUM"));
                    outputParam.setBranchNo((String) rsEipInfo.get("RPT_BR_NUM"));
                    // END 2018/12/03 S.Kitamura [QC#29402, MOD]
                    printJobName = PRINT_JOB_NAME + dsContrNum + " " + sysTimeStamp;
                    outputParam.setPrintJobName(printJobName);
                    request.setPrintOutParamBean(outputParam);
                }

                // START 2019/03/01 K.Kitachi [QC#30517, ADD]
                S21CUPSFileOutputParameter cupsFileoutParam = request.getCUPSFileOutParamBeanInstance();
                // START 2024/03/19 T.Nagae [QC#63552,MOD]
                //cupsFileoutParam.setFileName(NSAF0020 + "_" + dsContrNum + "_" + this.sysTimeStamp);
                cupsFileoutParam.setFileName(rptId + "_" + dsContrNum + "_" + this.sysTimeStamp);
                // END   2024/03/19 T.Nagae [QC#63552,MOD]
                request.setCUPSFileOutParamBean(cupsFileoutParam);
                // END 2019/03/01 K.Kitachi [QC#30517, ADD]

                // get EIP_RPT_RQST_PK
                eipRptRqstPk = this.service.createReportByAsync(request);

                rtnMap = new HashMap<String, Object>();
                rtnMap.put(DS_CONTR_PK, dsContrPk);
                rtnMap.put(DS_CONTR_NUM, dsContrNum);
                rtnMap.put(EIP_RPT_RQST_PK, new BigDecimal(eipRptRqstPk));
                // START 2024/03/19 T.Nagae [QC#63552,ADD]
                rtnMap.put(DS_ACCT_NUM, (String) rsEipInfo.get("DS_ACCT_NUM"));
                rtnMap.put(SVC_LINE_BIZ_CD, (String) rsEipInfo.get("SVC_LINE_BIZ_CD"));
                // END   2024/03/19 T.Nagae [QC#63552,ADD]
                rtnList.add(rtnMap);
                // loopCount++;
            }
        } 
        // START 2018/12/03 S.Kitamura [QC#29402, DEL]
        //catch (SQLException e) {
        //    sqlExceptionHandler(e);
        //}
        // END 2018/12/03 S.Kitamura [QC#29402, DEL]
        finally {
//            if (loopCount > 0) {
//                // START 2019/03/01 K.Kitachi [QC#30517, MOD]
//                // START 2019/02/27 K.Kitachi [QC#30517, MOD]
////                service.activateAsyncReportJob();
//                long processPk = service.activateAsyncReportJob();
//                commit();
//
//                S21ExecutionStatus pdfCreationResult = service.invokeAsyncPollingBatchTypeEIP(processPk);
//                boolean pdfResultFlg = pdfCreationResult.isSuccessful();
//                if (!pdfResultFlg) {
//                    return BigDecimal.ZERO;
//                }
//
//                String filePath = service.getFilePathForCupsPrint(eipRptRqstPk, 0);
//                if (hasValue(filePath)) {
//                    try {
//                        if (!ZYPConstant.FLG_ON_Y.equals(this.twodBarcodeAvoidFlag)) {
//                            String uniqueIdentifier = ZYPMaxTenDigitsNumbering.getUniqueID("CUPS_UNIQ_CD_2D"); // from AUTO_SQ_TEN_DIGIT
//                            addBarcodeImageToPdf(uniqueIdentifier, filePath);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return BigDecimal.ZERO;
//                    }
//                }
//                // END 2019/02/27 K.Kitachi [QC#30517, MOD]
//                // END 2019/03/01 K.Kitachi [QC#30517, MOD]
//            }
//            // START 2018/12/03 S.Kitamura [QC#29402, DEL]
//            //S21SsmLowLevelCodingClient.closeResource(psLetterlList, rsLetter);
//            // END 2018/12/03 S.Kitamura [QC#29402, DEL]
        }
        // return BigDecimal.valueOf(eipRptRqstPk);
        return rtnList;
    }
    // Mod End 2019/03/07 QC#30671

    /**
     * getForUpdateDsContrPrcEff
     * @param dsContrPk
     * @return List<BigDecimal>
     */
    private List<BigDecimal> getForUpdateDsContrPrcEff(BigDecimal dsContrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        List<BigDecimal> dsContrPrcEffPkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getPriceEffectivityRecord", paramMap);
        return dsContrPrcEffPkList;
    }

    // Mod Start 2019/03/13 QC#30671
    /**
     * updateDsContrPrcEff
     * @param dsContrPrcEffPkList List<BigDecimal>
     * @param rnwAdvNtcDt String
     * @param eipRptRqstPk BigDecimal
     * @return boolean
     */
    private boolean updateDsContrPrcEff(List<BigDecimal> dsContrPrcEffPkList, String rnwAdvNtcDt, BigDecimal eipRptRqstPk) {
        if (dsContrPrcEffPkList.size() == 0) {
            return true;
        }
        DS_CONTR_PRC_EFFTMsg[] outTMsgArray = new DS_CONTR_PRC_EFFTMsg[dsContrPrcEffPkList.size()];
        List<DS_CONTR_PRC_EFFTMsg> outTMsgList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();

        DS_CONTR_PRC_EFFTMsg inTMsg = new DS_CONTR_PRC_EFFTMsg();

        for (BigDecimal dsContrPrcEffPk : dsContrPrcEffPkList) {
            setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
            DS_CONTR_PRC_EFFTMsg outTMsg = (DS_CONTR_PRC_EFFTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
            if (outTMsg == null) {
                return false;
            }
            setValue(outTMsg.rnwAdvNtcDt, rnwAdvNtcDt);
            setValue(outTMsg.eipRptRqstPk, eipRptRqstPk);
            outTMsgList.add(outTMsg);
        }
        int updCnt = S21FastTBLAccessor.update(outTMsgList.toArray(outTMsgArray));
        if (updCnt != outTMsgList.size()) {
            return false;
        }
        return true;
    }
    // Mod Start 2019/03/13 QC#30671

    /**
     * updateDsContrPrcChngRec
     * @param dsContrPk BigDecimal
     * @return boolean
     */
    private boolean updateDsContrPrcChngRec(BigDecimal dsContrPk) {

        DS_CONTR_PRC_CHNG_RECTMsg inTMsg = new DS_CONTR_PRC_CHNG_RECTMsg();
        List<DS_CONTR_PRC_CHNG_RECTMsg> outTMsgList = new ArrayList<DS_CONTR_PRC_CHNG_RECTMsg>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        List<BigDecimal> dsContrPrcChngList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getPriceChangeRecord", paramMap);
        if (dsContrPrcChngList.size() == 0) {
            // START 2019/06/13 W.Honda [QC#50744, MOD]
//            return false;
            return true;
            // END 2019/06/13 W.Honda [QC#50744, MOD]
        }
        for (BigDecimal dsContrPrcChngRecPk : dsContrPrcChngList) {
            setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inTMsg.dsContrPrcChngRecPk, dsContrPrcChngRecPk);
            DS_CONTR_PRC_CHNG_RECTMsg outTMsg = (DS_CONTR_PRC_CHNG_RECTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inTMsg);
            if (outTMsg == null) {
                return false;
            }
            setValue(outTMsg.eipRptRqstDt, slsDt);
            outTMsgList.add(outTMsg);
        }
        DS_CONTR_PRC_CHNG_RECTMsg[] outTMsgArray = new DS_CONTR_PRC_CHNG_RECTMsg[dsContrPrcChngList.size()];
        if (outTMsgList.size() != 0) {
            S21FastTBLAccessor.update(outTMsgList.toArray(outTMsgArray));
        }
        return true;
    }
    // END 2017/08/16 M.Naito [QC#8661, ADD]

// START 2017/08/08 K.Kim [QC#20351, DEL]
//    /**
//     * get SVC_PRC_RNW_LTR_WRK
//     * @param glblCmpyCd String
//     * @param dsContrPk BigDecimal
//     * @return SVC_PRC_RNW_LTR_WRKTMsgArray
//     */
//    private static SVC_PRC_RNW_LTR_WRKTMsgArray getSVC_PRC_RNW_LTR_WRK(String glblCmpyCd, BigDecimal dsContrPk) {
//        SVC_PRC_RNW_LTR_WRKTMsg param = new SVC_PRC_RNW_LTR_WRKTMsg();
//        param.setSQLID("001");
//        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        param.setConditionValue("dsContrPk01", dsContrPk);
//        SVC_PRC_RNW_LTR_WRKTMsgArray result =  (SVC_PRC_RNW_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(param);
//        return result;
//    }
//
//    /**
//     * get SVC_TERM_COND_LTR_WRK
//     * @param glblCmpyCd String
//     * @param dsContrPk BigDecimal
//     * @return SVC_TERM_COND_LTR_WRKTMsgArray
//     */
//    private static SVC_TERM_COND_LTR_WRKTMsgArray getSVC_TERM_COND_LTR_WRK(String glblCmpyCd, BigDecimal dsContrPk) {
//        SVC_TERM_COND_LTR_WRKTMsg param = new SVC_TERM_COND_LTR_WRKTMsg();
//        param.setSQLID("001");
//        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        param.setConditionValue("dsContrPk01", dsContrPk);
//        SVC_TERM_COND_LTR_WRKTMsgArray result =  (SVC_TERM_COND_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(param);
//        return result;
//    }
//
//    /**
//     * removePhysical SVC_PRC_RNW_LTR_WRK
//     * @param glblCmpyCd String
//     * @param dsContrPk BigDecimal
//     * @return boolean
//     * @throws SQLException
//     */
//    private static boolean deletePrcRnwLtrWrk(String glblCmpyCd, BigDecimal dsContrPk) throws SQLException {
//        List<SVC_PRC_RNW_LTR_WRKTMsg> deleteTMsgList = new ArrayList<SVC_PRC_RNW_LTR_WRKTMsg>();
//        SVC_PRC_RNW_LTR_WRKTMsgArray result =  getSVC_PRC_RNW_LTR_WRK(glblCmpyCd, dsContrPk);
//        // START 2017/01/04 K.Kitachi [QC#16796, ADD]
//        if (result.getValidCount() == 0) {
//            return true;
//        }
//        // END 2017/01/04 K.Kitachi [QC#16796, ADD]
//        for (int i = 0; i < result.getValidCount(); i++) {
//            deleteTMsgList.add(result.no(i));
//        }
//        int delCnt = S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new SVC_PRC_RNW_LTR_WRKTMsg[deleteTMsgList.size()]));
//        if (delCnt != deleteTMsgList.size()) {
//            EZDConnectionMgr.getInstance().rollback();
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * removePhysical SVC_TERM_COND_LTR_WRK
//     * @param glblCmpyCd String
//     * @param dsContrPk BigDecimal
//     * @return boolean
//     * @throws SQLException
//     */
//    private static boolean deleteTermCondLtrWrk(String glblCmpyCd, BigDecimal dsContrPk) throws SQLException {
//        List<SVC_TERM_COND_LTR_WRKTMsg> deleteTMsgList = new ArrayList<SVC_TERM_COND_LTR_WRKTMsg>();
//        SVC_TERM_COND_LTR_WRKTMsgArray result =  getSVC_TERM_COND_LTR_WRK(glblCmpyCd, dsContrPk);
//        // START 2017/01/04 K.Kitachi [QC#16796, ADD]
//        if (result.getValidCount() == 0) {
//            return true;
//        }
//        // END 2017/01/04 K.Kitachi [QC#16796, ADD]
//        for (int i = 0; i < result.getValidCount(); i++) {
//            deleteTMsgList.add(result.no(i));
//        }
//        int delCnt = S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new SVC_TERM_COND_LTR_WRKTMsg[deleteTMsgList.size()]));
//        if (delCnt != deleteTMsgList.size()) {
//            EZDConnectionMgr.getInstance().rollback();
//            return false;
//        }
//        return true;
//    }
// END 2017/08/08 K.Kim [QC#20351, DEL]

    /**
     * addErrList
     * @param errList
     * @param msgTxt
     * @param dsCntNum
     */
    private void addErrList(List<String> errList, String msgTxt, String dsCntNum) {
        errList.add(ZYPCommonFunc.concatString(msgTxt, " :Contract#: ", dsCntNum));

    }

    /**
     * sendErrMail
     * @param errList
     */
    private void sendErrMail(List<String> errList) {
        NSXC001001SendMailForErrorInfo errorInfo = new NSXC001001SendMailForErrorInfo();
        errorInfo.addErrMsgList(errList);
        errorInfo.sendMail(this.glblCmpyCd, BATCH_ID);
    }

    // START 2017/08/16 M.Naito [QC#8661, DEL]
//    // add start 2016/06/06 CSA Defect#1523, 4624
//    private void callContrTrk(List<BigDecimal> dsContrPkList, List<String> errList) {
//        for (BigDecimal dsContrPk : dsContrPkList) {
//            callContrTrkAPI(dsContrPk, errList);
//        }
//    }
    // END 2017/08/16 M.Naito [QC#8661, DEL]

    // Mod Start 2019/03/13 QC#30671
    private boolean callContrTrkAPI(BigDecimal dsContrPk, List<String> errList) {
        String userId = BATCH_ID;
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code, dsContrPk, userId, this.slsDt, null, null, ONBATCH_TYPE.BATCH)) {
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.dsContrPk, dsContrPk);
            tMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                addErrList(errList, S21MessageFunc.clspGetMessage(NSXC001001ContractTracking.ERR_MSG_ID), tMsg.dsContrNum.getValue());
                return false;
            }
        }
        return true;
    }
    // Mod End 2019/03/13 QC#30671
    // add end 2016/06/06 CSA Defect#1523, 4624

    // START 2019/02/27 K.Kitachi [QC#30517, ADD]
    private void addBarcodeImageToPdf(String uniqueIdentifier, String pdfFilePath) {

        // ***1. Initialize barcode builder
        S21BarcodeBuilder builder = new S21BarcodeBuilder(S21BarcodeType.Datamatrix);

        // ***2. Open PDF
        S21PDFDocument pdf = new S21PDFDocument(pdfFilePath);

        // ***3. Get Total page of the PDF
        int pagesSize = pdf.getTotalPageSize();
        int pagesSizeCnt = (pdf.getTotalPageSize() + 1) / 2;

        // Add Barcode image to Odd page in a PDF (1,3,5,7...)
        for (int pdfPageIdx = 1; pdfPageIdx <= pagesSize; pdfPageIdx++) {

            // int pageNum = pdfPageIdx;
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
    // END 2019/02/27 K.Kitachi [QC#30517, ADD]

    // START 2019/05/07 K.Kitachi [QC#31341, ADD]
    private List<Map<String, Object>> getLtrRptCtrl() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLtrRptCtrl", paramMap);
    }
    // END 2019/05/07 K.Kitachi [QC#31341, ADD]
    
    // START 2024/03/19 T.Nagae [QC#63552, ADD]
    private String getCustBllgVcleCd(String dsAcctNum, String custInvSrcCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsAcctNum", dsAcctNum);
        paramMap.put("custInvSrcCd", custInvSrcCd);
        return (String) this.ssmBatchClient.queryObject("getCustBllgVcleCd", paramMap);
    }

    // START 2024/03/28 T.Nagae [QC#63552, MOD]
//    private String getDsAcctNm(String dsContrPk) {
    private String getDsAcctNm(BigDecimal dsContrPk) {
    // END 2024/03/28 T.Nagae [QC#63552, MOD]
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        return (String) this.ssmBatchClient.queryObject("getDsAcctNm", paramMap);
    }

    private String getAcctEmlAddr(String dsAcctNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsAcctNum", dsAcctNum);
        paramMap.put("custInvSrcCd", CUST_INV_SRC.RNW_UPLFT);
        paramMap.put("invRuleRcpntTpCd", INV_RULE_RCPNT_TP.EXTERNAL);
        paramMap.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.EMAIL_WORK);
        return (String) this.ssmBatchClient.queryObject("getAcctEmlAddr", paramMap);
    }

    // START 2024/03/28 T.Nagae [QC#63552, MOD]
//    private String getSlsRepEmlAddr(String dsContrPk) {
    private String getSlsRepEmlAddr(BigDecimal dsContrPk) {
    // END 2024/03/28 T.Nagae [QC#63552, MOD]
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("dsContrPk", dsContrPk);
        return (String) this.ssmBatchClient.queryObject("getSlsRepEmlAddr", paramMap);
    }

    private void sendAccountMail(Map<String, Object> eipRptRqstInfo) {

        S21Mail mail = new S21Mail(glblCmpyCd);

        // From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, MAIL_FROM_GROUP_ID);
        groupFrom.setMailKey1("NS");
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        S21MailAddress fromAddr = null;
        if (!addrFromList.isEmpty()) {
            fromAddr = addrFromList.get(0);
        } else {
            throw new S21AbendException(ZZMM0007E, new String[] {"From Address" });
        }

        mail.setFromAddress(fromAddr);

        // To Address
        List<S21MailAddress> toAddrs = new ArrayList<S21MailAddress>();
        String dsAcctNum = (String) eipRptRqstInfo.get(DS_ACCT_NUM);
        String acctEmlAddr = getAcctEmlAddr(dsAcctNum);
        if (!hasValue(acctEmlAddr)) {
            throw new S21AbendException(ZZMM0007E, new String[] {"To Address" });
        }
        toAddrs.add(new S21MailAddress(glblCmpyCd, acctEmlAddr));

        // START 2024/03/29 T.Nagae [QC#63552, ADD]
        mail.setToAddressList(toAddrs);
        // END 2024/03/29 T.Nagae [QC#63552, ADD]

        // Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        // START 2024/03/28 T.Nagae [QC#63552, MOD]
//        String dsContrPk = (String) eipRptRqstInfo.get(DS_CONTR_PK);
        BigDecimal dsContrPk = (BigDecimal) eipRptRqstInfo.get(DS_CONTR_PK);
        // END 2024/03/28 T.Nagae [QC#63552, MOD]
        String slsRepEmlAddr = getSlsRepEmlAddr(dsContrPk);

        String svcLineBizCd = (String) eipRptRqstInfo.get(SVC_LINE_BIZ_CD);

        if (!LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
            // START 2024/03/29 T.Nagae [QC#63552, MOD]
//            slsRepEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(glblCmpyCd, NSAB052001_LFS_PPS_REP_ADDR);
            slsRepEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(NSAB052001_LFS_PPS_REP_ADDR, glblCmpyCd);
            // END 2024/03/29 T.Nagae [QC#63552, MOD]
        }

        String dsContrNum = (String) eipRptRqstInfo.get(DS_CONTR_NUM);
        String dsAcctNm = getDsAcctNm(dsContrPk);

        template.setTemplateParameter("contrNum", dsContrNum);
        template.setTemplateParameter("acctNm", dsAcctNm);
        template.setTemplateParameter("emlAddr", slsRepEmlAddr);

        mail.setMailTemplate(template);

        // Attachment (pdf)
        // START 2024/03/27 T.Nagae [QC#63552, MOD]
//        byte[] pdf = service.getArchivedReport((Long) eipRptRqstInfo.get(EIP_RPT_RQST_PK));
        BigDecimal eipRptRqstPk = (BigDecimal) eipRptRqstInfo.get(EIP_RPT_RQST_PK);
        byte[] pdf = service.getArchivedReport(eipRptRqstPk.longValue());
        // END 2024/03/27 T.Nagae [QC#63552, MOD]
        if (pdf == null) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Attachment PDF File" });
        }
        String pdfFileNameHead = null;
        // START 2024/03/29 T.Nagae [QC#63552, MOD]
//        pdfFileNameHead = ZYPCodeDataUtil.getVarCharConstValue(glblCmpyCd, NSAB052001_MAIL_DPF_FILE_NAME);
        pdfFileNameHead = ZYPCodeDataUtil.getVarCharConstValue(NSAB052001_MAIL_DPF_FILE_NAME, glblCmpyCd);
        // END 2024/03/29 T.Nagae [QC#63552, MOD]
        if (!hasValue(pdfFileNameHead)) {
            pdfFileNameHead = MAIL_PDF_FILE_NAME;
        }
        S21MailAttachment attachment = new S21MailAttachment(glblCmpyCd);
        attachment.setAttachment(pdf);
        String prmAttachFileName = pdfFileNameHead + "(" + dsContrNum + ")" + MAIL_DPF_FILE_EXT;
        attachment.setFileName(prmAttachFileName);
        mail.setAttachment(attachment);

        // Call sendMail
        mail.postMail();
    }
    // END 2024/03/19 T.Nagae [QC#63552, ADD]
}
