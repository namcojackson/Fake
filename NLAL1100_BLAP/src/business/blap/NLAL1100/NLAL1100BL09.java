/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLAL1100;

import static business.blap.NLAL1100.constant.NLAL1100Constant.CHAR_BLANK;
import static business.blap.NLAL1100.constant.NLAL1100Constant.CHAR_HYPHEN;
import static business.blap.NLAL1100.constant.NLAL1100Constant.CSA_RMA_RPT_PRINT_RQST_SQ;
import static business.blap.NLAL1100.constant.NLAL1100Constant.EVENT_NM_NLAL1100_PRINT;
import static business.blap.NLAL1100.constant.NLAL1100Constant.GLBL_CMPY_CD;
import static business.blap.NLAL1100.constant.NLAL1100Constant.INTL_LANG_VAL_COL_NM;
import static business.blap.NLAL1100.constant.NLAL1100Constant.NLZM2275E;
import static business.blap.NLAL1100.constant.NLAL1100Constant.REPORT_FILE_EXTENSION;
import static business.blap.NLAL1100.constant.NLAL1100Constant.REPORT_ID;
import static business.blap.NLAL1100.constant.NLAL1100Constant.REPORT_TITLE;
import static business.blap.NLAL1100.constant.NLAL1100Constant.RWS_REF_NUM;
import static business.blap.NLAL1100.constant.NLAL1100Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 * <pre>
 * Business ID : NLAL1100 Manage RMA Orders
 * Function Name : business print process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/22/2016   CITS            Y.Fujii         Create          R362
 * 12/27/2016   CITS            Y.Fujii         Update          QC#16547
 * 03/28/2018   CITS            K.Fukumura      Update          QC#25025
 * </pre>
 */
public class NLAL1100BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NLAL1100CMsg bizMsg = (NLAL1100CMsg) cMsg;
            NLAL1100SMsg glblMsg = (NLAL1100SMsg) sMsg;

            bizMsg.setCommitSMsg(true);

            if (EVENT_NM_NLAL1100_PRINT.equals(screenAplID)) {
                doProcess_NLAL1100Scrn00_Print(bizMsg, glblMsg);
            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
 // QC#25025 2018/03/28 Start
//    private void doProcess_NLAL1100Scrn00_Print(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {
//
//        //BigDecimal csaRmaRptPrintRqstSq = cMsg.csaRmaRptPrintRqstSq_PR.getValue();
//        //String rwsRefNum = cMsg.rwsRefNum_PR.getValue();
//        //String titleRwsRefNum = rwsRefNum.replace(CHAR_HYPHEN, CHAR_BLANK);
//        
//        BigDecimal csaRmaRptPrintRqstSq = BigDecimal.ZERO;
//        String rwsRefNum = "";
//        String titleRwsRefNum = rwsRefNum.replace(CHAR_HYPHEN, CHAR_BLANK);
//
//        // Generate S21ReportRequestBean
//        S21ReportRequestBean requestBean = new S21ReportRequestBean(REPORT_ID);
//        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
//        requestBean.setRptArcFlg(true);
//        requestBean.setRptTtlNm(titleRwsRefNum + REPORT_TITLE);
//
//        // Generate S21InputParameter
//        S21InputParameter param = requestBean.getInputParamBeanInstance();
//        param.addReportParameter(INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
//        param.addReportParameter(GLBL_CMPY_CD, cMsg.glblCmpyCd_G1.getValue());
//        param.addReportParameter(CSA_RMA_RPT_PRINT_RQST_SQ, csaRmaRptPrintRqstSq);
//        param.addReportParameter(RWS_REF_NUM, rwsRefNum);
//
//        requestBean.setInputParamBean(param);
//
//        S21EIPPrintingService service = new S21EIPPrintingService();
//        byte[] pdf = service.onlineReport(requestBean);
//
//        try {
//            if (pdf != null) {
//                StringBuilder fileName = new StringBuilder();
//                fileName.append(String.valueOf(System.currentTimeMillis()));
//                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), REPORT_FILE_EXTENSION);
//                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);
//                cMsg.setMessageInfo(ZZZM9003I, new String[] {"Print" });
//            } else {
//                throw new S21AbendException(NLZM2275E);
//            }
//        } catch (S21AbendException e) {
//            cMsg.setMessageInfo(NLZM2275E, new String[] {e.getMessage() });
//        }
//    }
    private void doProcess_NLAL1100Scrn00_Print(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        // Generate S21ReportRequestBean
        ArrayList<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();
        S21ReportRequestBean requestBean;
        // --------------------------------------------------------------
        // EIP Parameter Set
        // --------------------------------------------------------------
        for (int i = 0; i < cMsg.L.getValidCount(); i++) {
            BigDecimal csaRmaRptPrintRqstSq = cMsg.L.no(i).csaRmaRptPrintRqstSq_PR.getValue();
            String rwsRefNum = cMsg.L.no(i).rwsRefNum_PR.getValue();
            String titleRwsRefNum = rwsRefNum.replace(CHAR_HYPHEN, CHAR_BLANK);

            requestBean = new S21ReportRequestBean(REPORT_ID);
            requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
            requestBean.setRptArcFlg(true);
            requestBean.setRptTtlNm(titleRwsRefNum + REPORT_TITLE);
            // Generate S21InputParameter
            S21InputParameter param = requestBean.getInputParamBeanInstance();
            param.addReportParameter(INTL_LANG_VAL_COL_NM, param.getSystemDefaultLanguage());
            param.addReportParameter(GLBL_CMPY_CD, cMsg.glblCmpyCd_G1.getValue());
            param.addReportParameter(CSA_RMA_RPT_PRINT_RQST_SQ, csaRmaRptPrintRqstSq);
            param.addReportParameter(RWS_REF_NUM, rwsRefNum);
            // List Add
            requestBean.setInputParamBean(param);
            requestList.add(requestBean);
        }
        // --------------------------------------------------------------
        // PDFCreate
        // --------------------------------------------------------------
        try {
            S21EIPPrintingService service = new S21EIPPrintingService();
            byte[] pdf = service.onlineMergeReports(requestList);
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), REPORT_FILE_EXTENSION);
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);
                cMsg.setMessageInfo(ZZZM9003I, new String[] {"Print" });
            } else {
                throw new S21AbendException(NLZM2275E);
            }
        } catch (S21AbendException e) {
            cMsg.setMessageInfo(NLZM2275E, new String[] {e.getMessage() });
        }
    }
 // QC#25025 2018/03/28 End
}
