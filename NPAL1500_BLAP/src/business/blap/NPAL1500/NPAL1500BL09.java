/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1500;

import static business.blap.NPAL1500.constant.NPAL1500Constant.ABEND_MSG_FAILED_GET_REPORT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.EVENT_NM_NPAL1500_PRINT;
import static business.blap.NPAL1500.constant.NPAL1500Constant.GLBL_CMPY_CD;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_ORD_NUM;
import static business.blap.NPAL1500.constant.NPAL1500Constant.PO_RPT_PRINT_RQST_SQ;
import static business.blap.NPAL1500.constant.NPAL1500Constant.RCV_RPT_TS;
import static business.blap.NPAL1500.constant.NPAL1500Constant.REPORT_ID;
import static business.blap.NPAL1500.constant.NPAL1500Constant.USR_ID;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZXM0001E;
import static business.blap.NPAL1500.constant.NPAL1500Constant.ZZZM9003I;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 * <pre>
 * Business ID : NPAL1500 PO Entry
 * Function Name : business print process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 11/28/2016   CITS            Y.Fujii         Update          R350
 * </pre>
 */
public class NPAL1500BL09 extends S21BusinessHandler {

    /**
     * Email(PDF) Download Report Title Name.
     */
    public static final String RPT_NM_SUFIX_DOWN = "PO Download(PDF) : ";

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
            NPAL1500SMsg glblMsg = (NPAL1500SMsg) sMsg;

            bizMsg.setCommitSMsg(true);

            if (EVENT_NM_NPAL1500_PRINT.equals(screenAplID)) {
                doProcess_NPAL1500Scrn00_Print(bizMsg, glblMsg);
            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL1500Scrn00_Print(NPAL1500CMsg bizMsg, NPAL1500SMsg glblMsg) {
        S21EIPPrintingService service = new S21EIPPrintingService();

        // Generate S21ReportRequestBean
        S21ReportRequestBean requestBean = new S21ReportRequestBean(REPORT_ID);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        requestBean.setRptArcFlg(true);
        requestBean.setRptTtlNm(RPT_NM_SUFIX_DOWN + bizMsg.poNum.getValue() + bizMsg.xxTsDsp19Txt_PR.getValue());

        // Generate S21InputParameter
        S21InputParameter param = requestBean.getInputParamBeanInstance();
        param.addReportParameter("INTL_LANG_VAL_COL_NM", param.getSystemDefaultLanguage());
        param.addReportParameter(GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        param.addReportParameter(USR_ID, bizMsg.poSubmtPsnCd.getValue());
        param.addReportParameter(PO_ORD_NUM, bizMsg.poNum.getValue());
        param.addReportParameter(RCV_RPT_TS, bizMsg.xxTsDsp19Txt_PR.getValue());
        param.addReportParameter(PO_RPT_PRINT_RQST_SQ, bizMsg.poRptPrintRqstSq_PR.getValue());

        requestBean.setInputParamBean(param);

        byte[] pdf = service.onlineReport(requestBean);

        try {
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                bizMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), pdf);
                bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Print" });
            } else {
                throw new S21AbendException(ABEND_MSG_FAILED_GET_REPORT);
            }
        } catch (S21AbendException e) {
            bizMsg.setMessageInfo(ZZXM0001E, new String[] {e.getMessage() });
        }
    }
}
