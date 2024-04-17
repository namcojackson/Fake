/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0080.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0080.NFAL0080CMsg;
import business.blap.NFAL0080.NFAL0080Query;
import business.blap.NFAL0080.constant.NFAL0080Constant;
import business.db.ELIG_COA_SEG_PTRNTMsg;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NFAL0080CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0080CommonLogic implements NFAL0080Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    /**
     * Method name: getFirstProdCtrlCdForHeader
     * <dd>The method explanation: Get FIRST_PROD_CTRL_CD Header Pull
     * Down value.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void getFirstProdCtrlCdForHeader(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "getFirstProdCtrlCdForHeader================================START", null);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFAL0080Query.getInstance().getCoaSetLkupTpCd();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                // Set [COA_SEG_LKUP_TP_CD] pulldown values
                bizMsg.coaSegLkupTpCd_1.no(i).setValue((String) map.get(COA_SEG_LKUP_TP_CD));
                bizMsg.coaSegLkupTpCd_2.no(i).setValue((String) map.get(COA_SEG_LKUP_TP_CD));
            }
        }

        EZDDebugOutput.println(5, "getFirstProdCtrlCdForHeader================================END", null);
    }

    /**
     * Method name: getCoaSegLkupTpCdListBox
     * <dd>The method explanation: Get COA_SEG_LKUP_TP_CD Detail Pull
     * Down value.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param i int
     */
    private static void getCoaSegLkupTpCdListBox(EZDCMsg cMsg, int i) {

        EZDDebugOutput.println(5, "getCoaSegLkupTpCdListBox================================START", null);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        for (int j = 0; j < bizMsg.coaSegLkupTpCd_1.length(); j++) {
            // Set [COA_SEG_LKUP_TP_CD] pulldown values
            bizMsg.A.no(i).coaSegLkupTpCd_A1.no(j).setValue(bizMsg.coaSegLkupTpCd_1.no(j).getValue());
            bizMsg.A.no(i).coaSegLkupTpCd_A2.no(j).setValue(bizMsg.coaSegLkupTpCd_1.no(j).getValue());
        }

        EZDDebugOutput.println(5, "getCoaSegLkupTpCdListBox================================END", null);
    }

    /**
     * Method name: getResult
     * <dd>The method explanation: Get result from IMPT_INV_PROD_LINE
     * table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public void getWholeRecords(EZDCMsg cMsg) {

        EZDDebugOutput.println(5, "getResult================================START", null);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NFAL0080Query.getInstance().getWholeRecords();

        if (ssmResult.isCodeNormal()) {

            // Message when over max record count.
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > bizMsg.A.length()) {
                bizMsg.A.setValidCount(bizMsg.A.length());
                bizMsg.setMessageInfo(NFAM0001W, new String[] {Long.toString(bizMsg.A.length()), Long.toString(queryResCnt) });
            } else {
                bizMsg.A.setValidCount(queryResCnt);
                bizMsg.setMessageInfo(ZZM8002I);
            }

            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);

                if (i == 0) {
                    bizMsg.ezUpTime.setValue(parts.checkNull((String) map.get(EZUPTIME)));
                    bizMsg.ezUpTimeZone.setValue(parts.checkNull((String) map.get(EZUPTIMEZONE)));
                }
                bizMsg.A.no(i).ezUpTime_A.setValue(parts.checkNull((String) map.get(EZUPTIME)));
                bizMsg.A.no(i).ezUpTimeZone_A.setValue(parts.checkNull((String) map.get(EZUPTIMEZONE)));

                bizMsg.A.no(i).eligCoaSegPtrnCd_A.setValue(parts.checkNull((String) map.get(ELIG_COA_SEG_PTRN_CD)));
                bizMsg.A.no(i).coaSegLkupTpCd_A3.setValue(parts.checkNull((String) map.get(COA_SEG_LKUP_TP_CD)));

                // FIRST_PROD_CTRL_CD pull down(Detail)
                getCoaSegLkupTpCdListBox(bizMsg, i);

                // To keep original state.
                bizMsg.A.no(i).eligCoaSegPtrnCd_OR.setValue((String) map.get(ELIG_COA_SEG_PTRN_CD));
                bizMsg.A.no(i).coaSegLkupTpCd_OR.setValue((String) map.get(COA_SEG_LKUP_TP_CD));
            }

        } else {
            // Not found
            bizMsg.A.setValidCount(0);
            return;
        }

        EZDDebugOutput.println(5, "getResult================================E N D", null);

    }

    /**
     * Method name: clearHeader
     * <dd>The method explanation: Clear header information.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearHeader(EZDCMsg cMsg) {

        EZDDebugOutput.println(5, "clearHeader================================START", null);

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        bizMsg.eligCoaSegPtrnCd.clear();
        bizMsg.coaSegLkupTpCd_3.clear();

        EZDDebugOutput.println(5, "clearHeader================================E N D", null);

    }

    /**
     * Method name: checkRecordExistence
     * <dd>The method explanation: Check record existence in
     * IMPT_INV_PROD_LINE table.
     * <dd>Remarks:
     * @param eligCoaSegPtrnCd String
     * @param coaSegLkupTpCd String
     * @return boolean
     */
    public static boolean checkRecordExistence(String eligCoaSegPtrnCd, String coaSegLkupTpCd) {

        Map<String, String> queryMap = new HashMap<String, String>();

        queryMap.put("glblCmpyCd", GLBL_CMPY_CD);
        queryMap.put("eligCoaSegPtrnCd", eligCoaSegPtrnCd);
        queryMap.put("coaSegLkupTpCd", coaSegLkupTpCd);

        S21SsmEZDResult ssmResult = NFAL0080Query.getInstance().checkRecord(queryMap);

        if (ssmResult.isCodeNormal()) {
            return true;
        }

        return false;
    }

    /**
     * Method name: createNewRecord
     * <dd>The method explanation: Create record into
     * IMPT_INV_PROD_LINE table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void createNewRecord(EZDCMsg cMsg) {

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        ELIG_COA_SEG_PTRNTMsg tMsg = new ELIG_COA_SEG_PTRNTMsg();

        // Primary Keys
        tMsg.glblCmpyCd.setValue(GLBL_CMPY_CD);
        tMsg.eligCoaSegPtrnCd.setValue(bizMsg.eligCoaSegPtrnCd.getValue());
        tMsg.coaSegLkupTpCd.setValue(bizMsg.coaSegLkupTpCd_3.getValue());

        EZDTBLAccessor.create(tMsg);
    }

    /**
     * Method name: removeRecordForDetail
     * <dd>The method explanation: Delete record from
     * IMPT_INV_PROD_LINE table.
     * <dd>Remarks:
     * @param eligCoaSegPtrnCd String
     * @param coaSegLkupTpCd String
     */
    public static void removeRecordForDetail(String eligCoaSegPtrnCd, String coaSegLkupTpCd) {

        ELIG_COA_SEG_PTRNTMsg tMsgDlt = new ELIG_COA_SEG_PTRNTMsg();

        // Primary Keys
        tMsgDlt.glblCmpyCd.setValue(GLBL_CMPY_CD);
        tMsgDlt.eligCoaSegPtrnCd.setValue(eligCoaSegPtrnCd);
        tMsgDlt.coaSegLkupTpCd.setValue(coaSegLkupTpCd);

        tMsgDlt = (ELIG_COA_SEG_PTRNTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgDlt);

        if (tMsgDlt != null) {
            EZDTBLAccessor.logicalRemove(tMsgDlt);
        }
    }

    /**
     * Method name: createRecordForDetail
     * <dd>The method explanation: Create record into
     * IMPT_INV_PROD_LINE table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param i int
     */
    public static void createRecordForDetail(EZDCMsg cMsg, int i) {

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        ELIG_COA_SEG_PTRNTMsg tMsgCreate = new ELIG_COA_SEG_PTRNTMsg();

        // Primary Keys
        tMsgCreate.glblCmpyCd.setValue(GLBL_CMPY_CD);
        tMsgCreate.eligCoaSegPtrnCd.setValue(bizMsg.A.no(i).eligCoaSegPtrnCd_A.getValue());
        tMsgCreate.coaSegLkupTpCd.setValue(bizMsg.A.no(i).coaSegLkupTpCd_A3.getValue());

        EZDTBLAccessor.create(tMsgCreate);

        bizMsg.setMessageInfo(ZZM8100I);

    }

    /**
     * Method name: updateRecordForDetail
     * <dd>The method explanation: Update a record in
     * IMPT_INV_PROD_LINE table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param i int
     */
    public static void updateOrInsertRecordForDetail(EZDCMsg cMsg, int i) {

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        ELIG_COA_SEG_PTRNTMsg tMsgUpdate = new ELIG_COA_SEG_PTRNTMsg();

        // Primary Keys
        tMsgUpdate.glblCmpyCd.setValue(GLBL_CMPY_CD);
        tMsgUpdate.eligCoaSegPtrnCd.setValue(bizMsg.A.no(i).eligCoaSegPtrnCd_A.getValue());
        tMsgUpdate.coaSegLkupTpCd.setValue(bizMsg.A.no(i).coaSegLkupTpCd_A3.getValue());

        tMsgUpdate = (ELIG_COA_SEG_PTRNTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgUpdate);

        if (tMsgUpdate != null) {

            tMsgUpdate.eligCoaSegPtrnCd.setValue(bizMsg.A.no(i).eligCoaSegPtrnCd_A.getValue());
            tMsgUpdate.coaSegLkupTpCd.setValue(bizMsg.A.no(i).coaSegLkupTpCd_A3.getValue());

            EZDTBLAccessor.update(tMsgUpdate);
        } else {

            ELIG_COA_SEG_PTRNTMsg tMsgInsert = new ELIG_COA_SEG_PTRNTMsg();

            tMsgInsert.glblCmpyCd.setValue(GLBL_CMPY_CD);
            tMsgInsert.eligCoaSegPtrnCd.setValue(bizMsg.A.no(i).eligCoaSegPtrnCd_A.getValue());
            tMsgInsert.coaSegLkupTpCd.setValue(bizMsg.A.no(i).coaSegLkupTpCd_A3.getValue());

            EZDTBLAccessor.create(tMsgInsert);
        }
    }

    /**
     * Method name: checkExclusiveControl
     * <dd>The method explanation: Check exclusive control for
     * IMPT_INV_PROD_LINE table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param i int
     * @return boolean
     */
    public static boolean checkExclusiveControl(EZDCMsg cMsg, int i) {

        NFAL0080CMsg bizMsg = (NFAL0080CMsg) cMsg;

        ELIG_COA_SEG_PTRNTMsg tMsg = new ELIG_COA_SEG_PTRNTMsg();

        // Primary Keys
        tMsg.glblCmpyCd.setValue(GLBL_CMPY_CD);
        tMsg.eligCoaSegPtrnCd.setValue(bizMsg.A.no(i).eligCoaSegPtrnCd_OR.getValue());
        tMsg.coaSegLkupTpCd.setValue(bizMsg.A.no(i).coaSegLkupTpCd_OR.getValue());

        tMsg = (ELIG_COA_SEG_PTRNTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.A.no(i).eligCoaSegPtrnCd_A.setErrorInfo(1, NFAM0004E);
            bizMsg.A.no(i).coaSegLkupTpCd_A3.setErrorInfo(1, NFAM0004E);
            return false;
        } else {
            // Get _EZUpdateDateTime and _EZUpTimeZone
            // for exclusive control.
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.A.no(i).ezUpTime_A.getValue(), bizMsg.A.no(i).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.A.no(i).eligCoaSegPtrnCd_A.setErrorInfo(1, NFAM0004E);
                bizMsg.A.no(i).coaSegLkupTpCd_A3.setErrorInfo(1, NFAM0004E);
                return false;
            }
        }
        return true;
    }

}
