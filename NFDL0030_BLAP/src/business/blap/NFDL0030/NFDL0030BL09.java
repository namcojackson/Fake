/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0030;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * NFDL0030BL09
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/02/07   Fujitsu         S.Ohki          Create          QC#30023
 * 2019/02/20   Fujitsu         S.Ohki          Update          QC#30434
 * 2019/07/31   Fujitsu         H.Ikeda         Update          QC#52112
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030BL09 extends S21BusinessHandler implements NFDL0030Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;
            NFDL0030SMsg glblMsg = (NFDL0030SMsg) sMsg;

            if ("NFDL0030Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0030Scrn00_CMN_Submit(NFDL0030CMsg bizMsg, NFDL0030SMsg glblMsg) {
        try {

            sendConfirmationLetter(bizMsg);

            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

        } catch (S21AbendException e) {
            bizMsg.setMessageInfo("ZZXM0001E", new String[] {e.getMessage() });
        }
    }

    private void sendConfirmationLetter(NFDL0030CMsg bizMsg) {

        // create S21EIP service instance
        S21EIPPrintingService service = new S21EIPPrintingService();
        List<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();

        // create email report
        for (int i = 0; i < bizMsg.L.getValidCount(); i++) {

            BigDecimal tgtRqstNum = bizMsg.L.no(i).cltPmtConfLtrRqstNum_L1.getValue();

            Map<String, Object> confLtrWrkMap = (Map<String, Object>) NFDL0030Query.getInstance().getConfLtrWrk(getGlobalCompanyCode(), tgtRqstNum).getResultObject();
            if (confLtrWrkMap != null) {

                // create report request bean
                S21ReportRequestBean request = new S21ReportRequestBean(RPT_ID);

                // create report parameter
                S21InputParameter inputParam = createInputParameter(request, confLtrWrkMap);
                request.setInputParamBean(inputParam);
                requestList.add(request);
            }
        }

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
        // START 2019/02/20 S.Ohki [QC#30434,DEL]
//        service.activateAsyncReportJob();
        // END   2019/02/20 S.Ohki [QC#30434,DEL]
    }

    private S21InputParameter createInputParameter(S21ReportRequestBean request, Map<String, Object> confLtrWrkMap) {
        S21InputParameter inputParam = request.getInputParamBeanInstance();

        String sysTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        // START 2019/07/31 [QC#52112, MOD]
        //request.setRptTtlNm(getReportTitle(RPT_TTL_NM, (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_CD"), sysTimeStamp));
        // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//        request.setRptTtlNm(getReportTitle(RPT_TTL_NM, (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD"), sysTimeStamp));
        String dtlBillToCustAcctCd = (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD");
        if (ZYPCommonFunc.hasValue(dtlBillToCustAcctCd)) {
            request.setRptTtlNm(getReportTitle(RPT_TTL_NM, (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD"), sysTimeStamp));
        } else {
            request.setRptTtlNm(getReportTitle(E_CHECK_RPT_TTL_NM, (String) confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD"), sysTimeStamp));
        }
        // END 2023/03/10 S.Nakatani [QC#55645,MOD]
        // END   2019/07/31 [QC#52112, MOD]
        request.setRptArcFlg(true);
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);

        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter("GLBL_CMPY_CD", getGlobalCompanyCode());
        inputParam.addReportParameter("CLT_PMT_CONF_LTR_RQST_NUM", confLtrWrkMap.get("CLT_PMT_CONF_LTR_RQST_NUM"));
        inputParam.addReportParameter("DTL_BILL_TO_CUST_ACCT_CD", confLtrWrkMap.get("DTL_BILL_TO_CUST_ACCT_CD"));

        return inputParam;
    }

    private String getReportTitle(String titleName, String billToCustCd, String timeStampStr) {
        StringBuilder signature = new StringBuilder();
        signature.append(titleName);

        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            signature.append(SPACE);
            // START 2019/07/31 [QC#52112, MOD]
            //signature.append("BillToCustomer");
            signature.append("BillToCustomerAccount");
            // END   2019/07/31 [QC#52112, MOD]
            signature.append(SPACE);
            signature.append(billToCustCd);
        }

        signature.append(SPACE);
        signature.append("Time");
        signature.append(SPACE);
        signature.append(timeStampStr);

        return signature.toString();
    }

}
