/** Copyright (c) 2009 Canon USA Inc. All rights reserved. */
package com.canon.cusa.s21.batch.NWA.NWAB110001;

import parts.dbcommon.EZDTBLAccessor;
import business.db.INV_RPTTMsg;
import business.db.INV_RPTTMsgArray;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.printing.common.S21BOPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Invoice Report Purge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2011   Fujitsu         S.Yoshida       Create          N/A
 * 01/04/2012   CSAI            N.Sasaki        Update          375167
 * </pre>
 */
public class NWAB110001 extends S21BatchMain {

    /** S21UserProfileService */
    private S21UserProfileService profile;

    /** Global Company code */
    private String glblCmpyCd;

    /** Term Code * */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal count */
    private int normalCnt;

    /** Abnormal count */
    private int abNormalCnt;

    /**
     * Initialization
     */
    protected void initRoutine() {
        profile     = S21UserProfileServiceFactory.getInstance().getService();
        normalCnt   = 0;
        abNormalCnt = 0;
    }

    /**
     * Main Processing
     */
    protected void mainRoutine() {
        glblCmpyCd = profile.getGlobalCompanyCode();
        doProcess();
    }

    /**
     * Termination
     */
    protected void termRoutine() {
        setTermState(termCd, normalCnt, abNormalCnt, normalCnt + abNormalCnt);
    }

    /**
     * Main Method
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB110001().executeBatch(NWAB110001.class.getSimpleName());
    }

    /**
     * doProcess
     */
    private void doProcess() {
        deleteExpiredReport();
    }

    /**
     * executePrint
     * @param whCd String
     * @param rptBrNum String
     */
    private void deleteExpiredReport() {

        // Find Report#
        INV_RPTTMsg invRptTMsg = new INV_RPTTMsg();
        invRptTMsg.setSQLID("001");
        invRptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        INV_RPTTMsgArray invRptTMsgArry =
            (INV_RPTTMsgArray) EZDTBLAccessor.findByCondition(invRptTMsg);
        if (invRptTMsgArry.length() == 0) {
            return;
        }

        // Delete Report
        S21BOPrintingService boBase   = null;
        S21ReportParameter   repParam = null;
        for (int i = 0; i < invRptTMsgArry.getValidCount(); i++) {

            try {
                boBase = new S21BOPrintingService();
                boBase.open();

                repParam = new S21ReportParameter();
                repParam.setReportId(invRptTMsgArry.no(i).invRptId.getValue());
                repParam.setReportBranchNo("01");
                // Def# 375167 start
                // boBase.deleteExpiredReport(repParam);
                // Def# 375167 end
                normalCnt++;

            } catch (S21AbendException e) {
                S21InfoLogOutput.println("NWAM0189E", new String[]{"Report#:" + invRptTMsgArry.no(i).invRptId.getValue() + ", Message:" + e.getMessage()});
                this.termCd = TERM_CD.WARNING_END;
                abNormalCnt++;
                rollback();

            } finally {
                boBase.close();
                commit();
            }
        }
    }
}
