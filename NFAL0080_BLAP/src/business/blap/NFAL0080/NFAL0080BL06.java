/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0080;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import business.blap.NFAL0080.common.NFAL0080CommonLogic;
import business.blap.NFAL0080.constant.NFAL0080Constant;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NFAL0080BL06
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0080BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080BL06 extends S21BusinessHandler implements NFAL0080Constant {

    /** Singleton instance. */
    private NFAL0080CommonLogic common = new NFAL0080CommonLogic();

    /**  Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

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

            if ("NFAL0080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0080Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFAL0080Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NFAL0080Scrn00_AddRow(cMsg, sMsg);
            } else if ("NFAL0080Scrn00_DeleteRows".equals(screenAplID)) {
                doProcess_NFAL0080Scrn00_DeleteRows(cMsg, sMsg);
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
     * Method name: doProcess_NFAL0080Scrn00_CMN_Submit
     * <dd>The method explanation: Update detail records.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0080Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_CMN_Submit================================START", this);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        if (isUpdated(bizMsg)) {
            bizMsg.setMessageInfo("NFAM0004E");
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (!bizMsg.A.no(i).coaSegLkupTpCd_A3.getValue().equals(bizMsg.A.no(i).coaSegLkupTpCd_OR.getValue())) {
                // Remove an existing record by
                // old primary keys.
                NFAL0080CommonLogic.removeRecordForDetail(bizMsg.A.no(i).eligCoaSegPtrnCd_OR.getValue(), bizMsg.A.no(i).coaSegLkupTpCd_OR.getValue());
                // Insert a record into ELIG_COA_SEG_PTRN table
                NFAL0080CommonLogic.updateOrInsertRecordForDetail(bizMsg, i);
            }
        }

        // Clear Header
        NFAL0080CommonLogic.clearHeader(bizMsg);
        // Get Result
        common.getWholeRecords(bizMsg);

        bizMsg.setMessageInfo(ZZM8100I);

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_CMN_Submit================================END", this);
    }

    private boolean isUpdated(NFAL0080CMsg bizMsg) {

        List list = findExistingRecord(bizMsg);

        String beforeEzUpTime = bizMsg.ezUpTime.getValue();
        String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();

        String currentEzUpTime = BLANK;
        String currentEzUpTimeZone = BLANK;

        if (list != null && list.size() > 0) {
            Map map = (Map) list.get(0);
            currentEzUpTime = parts.checkNull((String) map.get(EZUPTIME));
            currentEzUpTimeZone = parts.checkNull((String) map.get(EZUPTIMEZONE));
        }

        if ((list != null && list.size() > 0) && (beforeEzUpTime.equals(BLANK) || beforeEzUpTimeZone.equals(BLANK))) {
            // Record DOES exist but EzUpTime OR EzUpTimeZone was/were
            // originally null (Inconsistent data)
            if (ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                // record is not updated /deleted
                return false;
            } else {
                // record updated or deleted by somebody else
                return true;
            }
        } else if (beforeEzUpTime.equals(BLANK) && beforeEzUpTimeZone.equals(BLANK)) {
            // Record originally didn't exixt,
            // but now it exist means added by somebody else
            if (list != null && list.size() > 0) {
                // record added by somebody else
                return true;
            } else {
                // record is not added
                return false;
            }
        } else {
            if (list != null && list.size() > 0) {
                // Record updated or unchanged
                if (ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                    // record is not updated /deleted
                    return false;
                } else {
                    // record updated or deleted by somebody else
                    return true;
                }
            } else {
                // Record doesn't exist means deleted by somebody else
                return true;
            }
        }
    }

    private List findExistingRecord(NFAL0080CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NFAL0080Query.getInstance().getWholeRecords();
        List resultList = null;
        if (ssmResult.isCodeNormal()) {
            resultList = (List) ssmResult.getResultObject();
        }
        return resultList;
    }

    /**
     * Method name: doProcess_NFAL0080Scrn00_AddRow
     * <dd>The method explanation: Insert Record.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0080Scrn00_AddRow(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_AddRow================================START", this);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        if (NFAL0080CommonLogic.checkRecordExistence(bizMsg.eligCoaSegPtrnCd.getValue(), bizMsg.coaSegLkupTpCd_3.getValue())) {
            bizMsg.eligCoaSegPtrnCd.setErrorInfo(1, NFAM0070E, new String[] {RECORD, ELIG_COA_SEG_PTRN_TABLE });
            bizMsg.coaSegLkupTpCd_3.setErrorInfo(1, NFAM0070E, new String[] {RECORD, ELIG_COA_SEG_PTRN_TABLE });
            return;
        }

        // Insert a record into IMPT_INV_PROD_LINE table.
        NFAL0080CommonLogic.createNewRecord(bizMsg);

        // Clear Header
        NFAL0080CommonLogic.clearHeader(bizMsg);

        // Get Result
        common.getWholeRecords(bizMsg);

        bizMsg.setMessageInfo(ZZM8100I);

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_AddRow================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0080Scrn00_DeleteRows
     * <dd>The method explanation: Delete records.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0080Scrn00_DeleteRows(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_DeleteRows================================START", this);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (CHECKED.equals(bizMsg.A.no(i).xxChkBox_A.getValue())) {
                // Exclusive Control
                if (NFAL0080CommonLogic.checkExclusiveControl(bizMsg, i)) {
                    // Remove an existing record
                    // by old primary keys.
                    NFAL0080CommonLogic.removeRecordForDetail(bizMsg.A.no(i).eligCoaSegPtrnCd_A.getValue(), bizMsg.A.no(i).coaSegLkupTpCd_A3.getValue());
                } else {
                    return;
                }
            }
        }

        // Clear Header
        NFAL0080CommonLogic.clearHeader(bizMsg);

        // Get Result
        common.getWholeRecords(bizMsg);

        bizMsg.setMessageInfo(ZZM8100I);

        EZDDebugOutput.println(5, "doProcess_NFAL0080Scrn00_DeleteRows================================END", this);
    }
}
