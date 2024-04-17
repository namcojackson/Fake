/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL2020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 12/07/2016   CITS            Y.Fujii         Update          R360
 * 02/19/2018   CITS            T.Tokutomi      Update          QC#18367
 * 03/30/2018   CITS            K.Fukumura      Update          QC#25023
 * 07/02/2018   CITS            Y.Iwasaki       Update          QC#27042
 *</pre>
 */
public class NLBL2020BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL2020Scrn00_Print".equals(screenAplID)) {

                if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {
                    doProcess_Print((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
                }
            } else if ("NLBL2020Scrn00_CustomDocPrint".equals(screenAplID)){
                if (!"E".equals(cMsg.getMessageKind()) && !"W".equals(cMsg.getMessageKind())) {
                    doProcess_CustomDocPrint((NLBL2020CMsg) cMsg, (NLBL2020SMsg) sMsg);
                }
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_Print
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Print(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        S21EIPPrintingService service = new S21EIPPrintingService();
        // Generate S21ReportRequestBean
        // QC#25025 2018/03/28 Start
        ArrayList<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String soNum = sMsg.A.no(i).soNum_A1.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A1.getValue()) && 
                    ZYPCommonFunc.hasValue(soNum)) {
                
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).shpgOrdRptPrintRqstSq_A1.getValue())) {
                    S21ReportRequestBean requestBean = new S21ReportRequestBean(NLBL2020Constant.REPORT_ID);
                    requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                    requestBean.setRptArcFlg(true);
                    BigDecimal shpgOrdRptPrintRqstSq = sMsg.A.no(i).shpgOrdRptPrintRqstSq_A1.getValue();
                    // Report Title
                    String reportTitle = ZYPCommonFunc.concatString(NLBL2020Constant.REPORT_TITLE, NLBL2020Constant.REPORT_TITLE_SEPARATOR, soNum);
                    reportTitle = ZYPCommonFunc.concatString(reportTitle, NLBL2020Constant.REPORT_TITLE_SEPARATOR, shpgOrdRptPrintRqstSq.toPlainString());
                    requestBean.setRptTtlNm(reportTitle);
    
                    // Generate S21InputParameter
                    S21InputParameter param = requestBean.getInputParamBeanInstance();
                    param.addReportParameter(NLBL2020Constant.INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
                    param.addReportParameter(NLBL2020Constant.GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                    param.addReportParameter(NLBL2020Constant.SO_NUM, soNum);
                    param.addReportParameter(NLBL2020Constant.SHPG_ORD_RPT_PRINT_RQST_SQ, shpgOrdRptPrintRqstSq);
    
                    requestBean.setInputParamBean(param);
                    requestList.add(requestBean);
                }
            }
        }
        byte[] pdf = service.onlineMergeReports(requestList);
        // QC#25025 2018/03/28 End
        // QC#25025 2018/03/28 Start
//        // Generate S21ReportRequestBean
//        S21ReportRequestBean requestBean = new S21ReportRequestBean(NLBL2020Constant.REPORT_ID);
//        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
//        requestBean.setRptArcFlg(true);
//        String soNum = cMsg.soNum_PR.getValue();
//        BigDecimal shpgOrdRptPrintRqstSq = cMsg.shpgOrdRptPrintRqstSq_PR.getValue();
//        String reportTitle = ZYPCommonFunc.concatString(NLBL2020Constant.REPORT_TITLE, NLBL2020Constant.REPORT_TITLE_SEPARATOR, soNum);
//        reportTitle = ZYPCommonFunc.concatString(reportTitle, NLBL2020Constant.REPORT_TITLE_SEPARATOR, shpgOrdRptPrintRqstSq.toPlainString());
//        requestBean.setRptTtlNm(reportTitle);
//
//        // Generate S21InputParameter
//        S21InputParameter param = requestBean.getInputParamBeanInstance();
//        param.addReportParameter(NLBL2020Constant.INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
//        param.addReportParameter(NLBL2020Constant.GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
//        param.addReportParameter(NLBL2020Constant.SO_NUM, soNum);
//        param.addReportParameter(NLBL2020Constant.SHPG_ORD_RPT_PRINT_RQST_SQ, shpgOrdRptPrintRqstSq);
//
//        requestBean.setInputParamBean(param);
//
//        byte[] pdf = service.onlineReport(requestBean);
        // QC#25025 2018/03/28 End
        try {
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);
                cMsg.setMessageInfo(NLBL2020Constant.ZZZM9003I, new String[] {"Print" });
            } else {
                throw new S21AbendException(NLBL2020Constant.ABEND_MSG_FAILED_GET_REPORT);
            }
        } catch (S21AbendException e) {
            cMsg.setMessageInfo(NLBL2020Constant.NLBM1356E, new String[] {e.getMessage() });
        }
    }

    /**
     * doProcess_CustomDocPrint
     * QC#18367 Add method.
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_CustomDocPrint(NLBL2020CMsg cMsg, NLBL2020SMsg sMsg) {
        S21EIPPrintingService service = new S21EIPPrintingService();

        // Generate S21ReportRequestBean
        ArrayList<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();
        // Confirm if SO# is already in request
        ArrayList<String> printedSoList = new ArrayList<String>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String soNum = sMsg.A.no(i).soNum_A1.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxExstFlg_A1.getValue()) && ZYPCommonFunc.hasValue(soNum) && !printedSoList.contains(soNum)) {
                if (CTRY.CANADA.equals(sMsg.A.no(i).shipToCtryCd_A1.getValue())) {
                    S21ReportRequestBean requestBean = new S21ReportRequestBean(NLBL2020Constant.REPORT_ID_EXPORT_CA);
                    requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                    requestBean.setRptArcFlg(true);

                    String reportTitle = ZYPCommonFunc.concatString(NLBL2020Constant.REPORT_EXPORT_CA_TITLE, NLBL2020Constant.REPORT_TITLE_SEPARATOR, soNum);
                    reportTitle = ZYPCommonFunc.concatString(reportTitle, NLBL2020Constant.REPORT_TITLE_SEPARATOR, String.valueOf(i));
                    requestBean.setRptTtlNm(reportTitle);

                    // Generate S21InputParameter
                    S21InputParameter param = requestBean.getInputParamBeanInstance();
                    param.addReportParameter(NLBL2020Constant.INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
                    param.addReportParameter(NLBL2020Constant.GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                    param.addReportParameter(NLBL2020Constant.SO_NUM, soNum);
                    param.addReportParameter(NLBL2020Constant.EXPT_DOC_CA_PRINT_RQST_SQ, sMsg.A.no(i).exptDocPrintRqstSq_A1.getValue());

                    requestBean.setInputParamBean(param);
                    requestList.add(requestBean);
                } else {
                    S21ReportRequestBean requestBean = new S21ReportRequestBean(NLBL2020Constant.REPORT_ID_EXPORT_NON_CA);
                    requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                    requestBean.setRptArcFlg(true);

                    String reportTitle = ZYPCommonFunc.concatString(NLBL2020Constant.REPORT_EXPORT_NON_CA_TITLE, NLBL2020Constant.REPORT_TITLE_SEPARATOR, soNum);
                    reportTitle = ZYPCommonFunc.concatString(reportTitle, NLBL2020Constant.REPORT_TITLE_SEPARATOR, String.valueOf(i));
                    requestBean.setRptTtlNm(reportTitle);

                    // Generate S21InputParameter
                    S21InputParameter param = requestBean.getInputParamBeanInstance();
                    param.addReportParameter(NLBL2020Constant.INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
                    param.addReportParameter(NLBL2020Constant.GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                    param.addReportParameter(NLBL2020Constant.SO_NUM, soNum);
                    param.addReportParameter(NLBL2020Constant.EXPT_DOC_PRINT_RQST_SQ, sMsg.A.no(i).exptDocPrintRqstSq_A1.getValue());

                    requestBean.setInputParamBean(param);
                    requestList.add(requestBean);
                }
                printedSoList.add(soNum);
            }
        }

        byte[] pdf = service.onlineMergeReports(requestList);

        try {
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);
                cMsg.setMessageInfo(NLBL2020Constant.ZZZM9003I, new String[] {"Print" });
            } else {
                throw new S21AbendException(NLBL2020Constant.ABEND_MSG_FAILED_GET_REPORT);
            }
        } catch (S21AbendException e) {
            cMsg.setMessageInfo(NLBL2020Constant.NLBM1356E, new String[] {e.getMessage() });
        }
    }
}
