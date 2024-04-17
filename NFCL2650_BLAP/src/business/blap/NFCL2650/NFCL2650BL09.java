/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL2650;

import static com.canon.cusa.s21.api.NFZ.NFZC203001.constant.NFZC203001Constant.STMT_RPT_ID;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/15   Fujitsu         Y.Matsui        Create          QC#24329
 * 2018/10/12   Fujitsu         S.Ohki          Update          QC#28771
 *</pre>
 */
public class NFCL2650BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NFCL2650CMsg bizMsg = (NFCL2650CMsg) cMsg;
            NFCL2650SMsg glblMsg = (NFCL2650SMsg) sMsg;

            if ("NFCL2650Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL2650Scrn00_CMN_Submit(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL2650Scrn00_CMN_Submit(NFCL2650CMsg bizMsg, NFCL2650SMsg glblMsg) {
        try {
            String sysTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
            S21EIPPrintingService service = new S21EIPPrintingService();
            List<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();

            for (int i = 0; i < bizMsg.X.getValidCount(); i++) {

                S21ReportRequestBean request = new S21ReportRequestBean(STMT_RPT_ID);
                request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
                request.setRptArcFlg(true);

                String titleBase = makeTitleBase(bizMsg.X.no(i).billToCustCd_X.getValue(), sysTimeStamp);
                String rptTtlNm = "Print Statement " + titleBase;
                request.setRptTtlNm(rptTtlNm);

                S21InputParameter inputParam = request.getInputParamBeanInstance();
                inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
                inputParam.addReportParameter("GLBL_CMPY_CD", getGlobalCompanyCode());
                inputParam.addReportParameter("STMT_SQ_PK", bizMsg.X.no(i).stmtSqPk_X.getValue());
                request.setInputParamBean(inputParam);
                requestList.add(request);
            }

            // START 2018/10/12 [QC#28771, ADD]
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
            // END 2018/10/12 [QC#28771, ADD]

            byte[] pdf = service.onlineMergeReports(requestList);
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                bizMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), pdf);
                bizMsg.setMessageInfo("ZZZM9003I", new String[] {"Print" });
            } else {
                throw new S21AbendException("get report bytes failure");
            }
        } catch (S21AbendException e) {
            bizMsg.setMessageInfo("ZZXM0001E", new String[] {e.getMessage() });
        }
    }

    private String makeTitleBase(String billToCustCd, String sysTimeStamp) {
        StringBuilder rptTtl = new StringBuilder();
        rptTtl.append("BilltoCustomer ");
        rptTtl.append(billToCustCd);
        rptTtl.append(" Time ");
        rptTtl.append(sysTimeStamp);
        return rptTtl.toString();
    }

}
