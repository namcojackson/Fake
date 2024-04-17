/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0120;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NFAL0120.constant.NFAL0120Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.S21ExecutionStatus;
import com.canon.cusa.s21.framework.printing.S21InputParameter;
import com.canon.cusa.s21.framework.printing.common.S21BOPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportParameter;

/**
 * Class name: NFAL0120BL06
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0120BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120BL09 extends S21BusinessHandler implements NFAL0120Constant {

    /** Report ID */
    private final String reportId = "NFAF0120";

    /** Report Branch No. */
    private final String reportBranchNum = "01";

    /** Date format */
    private SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD);

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0120Scrn00_CreateReportBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CreateReportBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CreateReportSuppressReclassBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CreateReportBtn(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_CreateReportBtn
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_CreateReportBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CreateReportBtn=======(NFAL0120BL09)=========START", this);

        // NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        genPrintingDoc();

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CreateReportBtn=======(NFAL0120BL09)===========END", this);
    }

    private void genPrintingDoc() {

        // Initialize BO server
        S21BOPrintingService boBase = new S21BOPrintingService();
        try {
            // Login BO server
            boBase.open();

            // Get report name and info to be created
            S21ReportParameter repParam = new S21ReportParameter();
            // Set report ID (required)
            repParam.setReportId(reportId);
            // Set report branch number (required)
            repParam.setReportBranchNo(reportBranchNum);

            // In order to put the parameter, need to create the
            // parameter object
            S21InputParameter repInputParam = boBase.getInputParameter();

            repInputParam.addReportParameter("glblCmpyCd", this.getGlobalCompanyCode());

            // Put the title created in archive viewer
            repParam.setReportTitle("generated =" + dateFormat.format(new Date()));

            // Give an output and download the report
            S21ExecutionStatus status = boBase.printOrRefreshOnlyReport(repInputParam, repParam, null);

            if (status.isSuccessful()) {
                S21InfoLogOutput.println("Report has been created");
            } else {
                throw new S21AbendException(reportId + ":" + reportBranchNum);
            }
        } catch (Exception e) {
            StringWriter wr = new StringWriter();
            e.printStackTrace(new PrintWriter(wr));

            S21InfoLogOutput.println("Error Message: " + wr.toString());
            // Put stack trace as a parameter
            throw new S21AbendException(wr.toString());
        } finally {
            // Connection must be colsed
            boBase.close();
        }
    }
}
