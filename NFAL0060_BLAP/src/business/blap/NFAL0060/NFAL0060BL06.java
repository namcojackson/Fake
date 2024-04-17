/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0060;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0060.common.NFAL0060CommonLogic;
import business.blap.NFAL0060.constant.NFAL0060Constant;
import business.db.AJE_PTRN_IND_TPTMsg;
import business.db.AJE_PTRN_IND_TPTMsgArray;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NFAL0060BL06
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0060BL06
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0060BL06 extends S21BusinessHandler implements NFAL0060Constant {

    /** Singleton instance. */
    private NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_CMN_Delete(cMsg, sMsg);
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
     * Method name: doProcess_NFAL0060Scrn00_CMN_Submit
     * <dd>The method explanation: Business processing for Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Submit================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        // Exclusion control
        // for Ind Type only
        //---- start del 2016/03/24
        /*if (addingKeyAlreadyExistForExclusion(bizMsg)) {
            common.getExistAjePtrnIndTpCdList(bizMsg);
            bizMsg.setMessageInfo("NFAM0004E");
            return;
        }
        //---- end 2016/03/24
        */

        logicalRemoveDeletedRow(bizMsg);
        insertRow(bizMsg);
        
        //---- start add 2016/03/24
        NFAL0060CommonLogic.deleteAJEPtrn(bizMsg);
        //---- end 2016/03/24
        
        common.getExistAjePtrnIndTpCdList(bizMsg);        

        // Reset rows after adding records
        bizMsg.ajePtrnIndTpCd_3.setValue(bizMsg.ajePtrnIndTpCd_T.getValue());
        //---- start 2016/03/24 QC#5849
        //common.getExistAjeIntfcTpCdList(bizMsg);
        //bizMsg.ajeIntfcTpCd_3S.setValue(bizMsg.ajeIntfcTpCd_TS.getValue());
        //common.setAjeIntfcTpNmSearch(bizMsg);
        //---- end 2016/03/24
        common.setAjePtrnIndTpNm(bizMsg);
        
        common.setAjeActlCdListBox(bizMsg);
        // common.doSearch(bizMsg, RESET);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Submit================================END", null);
    }

    private boolean addingKeyAlreadyExistForExclusion(NFAL0060CMsg bizMsg) {

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            // <ID>806</ID>
            // <query><![CDATA[
            // WHERE
            // EZCANCELFLAG = '0'
            // AND GLBL_CMPY_CD = ?glblCmpyCd01?
            // AND AJE_INTFC_TP_CD = ?ajeIntfcTpCd01?
            // AND AJE_PTRN_IND_TP_CD = ?ajePtrnIndTpCd01?
            AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();
            tMsg.setSQLID("806");
            tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
            tMsg.setConditionValue("ajeIntfcTpCd01", INTFC_TP_CD);
            //---- end 2016/03/24
            tMsg.setConditionValue("ajePtrnIndTpCd01", bizMsg.D.no(i).ajePtrnIndTpCd_D.getValue());

            AJE_PTRN_IND_TPTMsgArray tMsgArr = (AJE_PTRN_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

            if (tMsgArr != null && tMsgArr.length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method name: doProcess_NFAL0060Scrn00_CMN_Delete
     * <dd>The method explanation: Business processing for Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Delete================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        if (isDeleted(bizMsg)) {
            bizMsg.setMessageInfo("NFAM0004E");
            return;
        }

        deleteRecordByKeys(bizMsg);
        // Refresh Pull down
        common.getExistAjePtrnIndTpCdList(bizMsg);
        //---- start 2016/03/24 remove
        //common.getExistAjeIntfcTpCdList(bizMsg);
        //---- end 2016/03/24

        bizMsg.ezUpTime.clear();
        bizMsg.ezUpTimeZone.clear();

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Delete================================END", null);
    }

    private void deleteRecordByKeys(NFAL0060CMsg bizMsg) {

        // <ID>806</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0'
        // AND GLBL_CMPY_CD = ?glblCmpyCd01?
        // AND AJE_INTFC_TP_CD = ?ajeIntfcTpCd01?
        // AND AJE_PTRN_IND_TP_CD = ?ajePtrnIndTpCd01?
        AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();
        tMsg.setSQLID("806");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
        tMsg.setConditionValue("ajeIntfcTpCd01", INTFC_TP_CD);
        //---- end 2016/03/24
        tMsg.setConditionValue("ajePtrnIndTpCd01", bizMsg.ajePtrnIndTpCd_3.getValue());

        AJE_PTRN_IND_TPTMsgArray tMsgArr = (AJE_PTRN_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {

                AJE_PTRN_IND_TPTMsg tMsgDelete = new AJE_PTRN_IND_TPTMsg();

                tMsgDelete.glblCmpyCd.setValue(this.getGlobalCompanyCode());
              //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
                tMsgDelete.ajeIntfcTpCd.setValue(INTFC_TP_CD);
                //---- end 2016/03/24
                tMsgDelete.ajePtrnIndTpCd.setValue(tMsgArr.no(i).ajePtrnIndTpCd.getValue());
                tMsgDelete.ajePtrnActlCd.setValue(tMsgArr.no(i).ajePtrnActlCd.getValue());

                tMsgDelete = (AJE_PTRN_IND_TPTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsgDelete);
                if (tMsgDelete != null) {
                    EZDTBLAccessor.logicalRemove(tMsgDelete);
                }
            }
        }
    }

    private void logicalRemoveDeletedRow(NFAL0060CMsg bizMsg) {

        if (bizMsg.B.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

                // Mutual Exclusion
                // for same Ind type and actual code
                if (isRowUpdated(bizMsg, i, DELETE)) {
                    bizMsg.setMessageInfo("NFAM0004E");
                    return;
                }

                AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();

                tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
              //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
                tMsg.ajeIntfcTpCd.setValue(INTFC_TP_CD);
                //---- end 2016/03/24
                tMsg.ajePtrnIndTpCd.setValue(bizMsg.B.no(i).ajePtrnIndTpCd_B.getValue());
                tMsg.ajePtrnActlCd.setValue(bizMsg.B.no(i).ajePtrnActlCd_B.getValue());

                tMsg = (AJE_PTRN_IND_TPTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

                if (tMsg != null) {
                    EZDTBLAccessor.logicalRemove(tMsg);

                    // Store deleted keys
                    // bizMsg.ajePtrnIndTpCd_T.setValue(bizMsg.B.no(i).ajePtrnIndTpCd_B.getValue());
                    // bizMsg.ajeIntfcTpCd_TS.setValue(bizMsg.B.no(i).ajeIntfcTpCd_B.getValue());
                }
            }
        }
        bizMsg.B.setValidCount(0);
    }

    private void insertRow(NFAL0060CMsg bizMsg) {

        if (bizMsg.A.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                // Mutual Exclusion
                // for same Ind type and actual code
                if (isRowUpdated(bizMsg, i, INSERT)) {
                    bizMsg.setMessageInfo("NFAM0004E");
                    return;
                }

                AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();

                tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
              //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
                tMsg.ajeIntfcTpCd.setValue(INTFC_TP_CD);
                //---- end 2016/03/24
                tMsg.ajePtrnIndTpCd.setValue(bizMsg.A.no(i).ajePtrnIndTpCd_A.getValue());
                tMsg.ajePtrnActlCd.setValue(bizMsg.A.no(i).ajePtrnActlCd_A.getValue());

                tMsg = (AJE_PTRN_IND_TPTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

                if (tMsg == null) {

                    AJE_PTRN_IND_TPTMsg tMsgInsert = new AJE_PTRN_IND_TPTMsg();

                    tMsgInsert.ajePtrnIndTpNm.setValue(bizMsg.A.no(i).ajePtrnIndTpNm_A.getValue());
                    tMsgInsert.ajePtrnActlNm.setValue(bizMsg.A.no(i).ajePtrnActlNm_A.getValue());
                    //---- start mod 2016/03/24
                    //tMsgInsert.ajeIntfcTpNm.setValue(bizMsg.A.no(i).ajeIntfcTpNm_A.getValue());
                    //---- end 2016/03/24
                    tMsgInsert.ajeIntfcColTxt.setValue(bizMsg.A.no(i).ajeIntfcColTxt_A.getValue());
                    // primary key(s) for the condition
                    tMsgInsert.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                  //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
                    tMsgInsert.ajeIntfcTpCd.setValue(INTFC_TP_CD);
                    //---- end 2016/03/24
                    tMsgInsert.ajePtrnIndTpCd.setValue(bizMsg.A.no(i).ajePtrnIndTpCd_A.getValue());
                    tMsgInsert.ajePtrnActlCd.setValue(bizMsg.A.no(i).ajePtrnActlCd_A.getValue());

                    EZDTBLAccessor.create(tMsgInsert);

                    // Store added keys
                    bizMsg.ajePtrnIndTpCd_T.setValue(bizMsg.A.no(i).ajePtrnIndTpCd_A.getValue());
                    //---- start del 2016/03/24
                    //bizMsg.ajeIntfcTpCd_TS.setValue(bizMsg.A.no(i).ajeIntfcTpCd_A.getValue());
                    //---- end 2016/03/24
                }
            }
        }
    }

    private boolean isDeleted(NFAL0060CMsg bizMsg) {

        String beforeEzUpTime = bizMsg.ezUpTime.getValue();
        String beforeEzUpTimeZone = bizMsg.ezUpTimeZone.getValue();

        String currentEzUpTime = BLANK;
        String currentEzUpTimeZone = BLANK;

        // <ID>806</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0'
        // AND GLBL_CMPY_CD = ?glblCmpyCd01?
        // AND AJE_INTFC_TP_CD = ?ajeIntfcTpCd01?
        // AND AJE_PTRN_IND_TP_CD = ?ajePtrnIndTpCd01?
        // ORDER BY
        // AJE_PTRN_IND_TP_CD ASC,
        // AJE_INTFC_TP_CD ASC,
        // AJE_PTRN_ACTL_CD ASC
        AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();
        tMsg.setSQLID("806");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        //---- start mod 2016/03/24  AJE_INTFC_TP_CD to be a fixed value.
        tMsg.setConditionValue("ajeIntfcTpCd01", INTFC_TP_CD);
        //---- end 2016/03/24
        tMsg.setConditionValue("ajePtrnIndTpCd01", bizMsg.ajePtrnIndTpCd_3.getValue());

        AJE_PTRN_IND_TPTMsgArray tMsgArrExist = (AJE_PTRN_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if ((tMsgArrExist != null && tMsgArrExist.length() > 0) && (beforeEzUpTime.equals(BLANK) || beforeEzUpTimeZone.equals(BLANK))) {
            // Record DOES exist but EzUpTime OR EzUpTimeZone was/were
            // originally null (Inconsistent data)
            currentEzUpTime = tMsgArrExist.no(0).ezUpTime.getValue();
            currentEzUpTimeZone = tMsgArrExist.no(0).ezUpTimeZone.getValue();

            if (beforeEzUpTime.equals(BLANK) || beforeEzUpTimeZone.equals(BLANK)) {
                return false;
            } else if (ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                // record is not updated /deleted
                return false;
            } else {
                // record updated or deleted by somebody else
                return true;
            }
        } else if (beforeEzUpTime.equals(BLANK) && beforeEzUpTimeZone.equals(BLANK)) {
            // Record originally didn't exixt,
            // but now it exist means added by somebody else
            if (tMsgArrExist != null && tMsgArrExist.length() > 0) {
                // record added by somebody else
                return true;
            } else {
                // record is not added
                return false;
            }
        } else {
            if (tMsgArrExist != null && tMsgArrExist.length() > 0) {
                // Record updated or unchanged
                currentEzUpTime = tMsgArrExist.no(0).ezUpTime.getValue();
                currentEzUpTimeZone = tMsgArrExist.no(0).ezUpTimeZone.getValue();
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

    private boolean isRowUpdated(NFAL0060CMsg bizMsg, int index, boolean submit) {

        List list = findExistingRecord(bizMsg, index, submit);

        String beforeEzUpTime = BLANK;
        String beforeEzUpTimeZone = BLANK;

        // Determine Insert or Delete
        if (submit == INSERT) {
            beforeEzUpTime = bizMsg.A.no(index).ezUpTime_A.getValue();
            beforeEzUpTimeZone = bizMsg.A.no(index).ezUpTimeZone_A.getValue();
        } else {
            beforeEzUpTime = bizMsg.B.no(index).ezUpTime_B.getValue();
            beforeEzUpTimeZone = bizMsg.B.no(index).ezUpTimeZone_B.getValue();
        }

        String currentEzUpTime = BLANK;
        String currentEzUpTimeZone = BLANK;

        if ((list != null && list.size() > 0) && (beforeEzUpTime.equals(BLANK) || beforeEzUpTimeZone.equals(BLANK))) {
            // Record DOES exist but EzUpTime OR EzUpTimeZone was/were
            // originally null (Inconsistent data)
            Map map = (Map) list.get(0);
            currentEzUpTime = parts.checkNull((String) map.get("EZUPTIME"));
            currentEzUpTimeZone = parts.checkNull((String) map.get("EZUPTIMEZONE"));

            if (beforeEzUpTime.equals(BLANK) || beforeEzUpTimeZone.equals(BLANK)) {
                return false;
            } else if (ZYPDateUtil.isSameTimeStamp(beforeEzUpTime, beforeEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
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
                Map map = (Map) list.get(0);
                currentEzUpTime = parts.checkNull((String) map.get("EZUPTIME"));
                currentEzUpTimeZone = parts.checkNull((String) map.get("EZUPTIMEZONE"));
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

    private List findExistingRecord(NFAL0060CMsg bizMsg, int index, boolean submit) {

        String ajeIntfcTpCd = BLANK;
        String ajePtrnIndTpCd = BLANK;
        String ajePtrnActlCd = BLANK;

        if (submit == INSERT) {
            //---- start mod 2016/03/24
            ajeIntfcTpCd = INTFC_TP_CD;
            //---- end 2016/03/24
            ajePtrnIndTpCd = bizMsg.A.no(index).ajePtrnIndTpCd_A.getValue();
            ajePtrnActlCd = bizMsg.A.no(index).ajePtrnActlCd_A.getValue();
        } else {
          //---- start mod 2016/03/24
            ajeIntfcTpCd = INTFC_TP_CD;
            //---- end 2016/03/24
            ajePtrnIndTpCd = bizMsg.B.no(index).ajePtrnIndTpCd_B.getValue();
            ajePtrnActlCd = bizMsg.B.no(index).ajePtrnActlCd_B.getValue();
        }

        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getAjePtrnIndTpByKeyForExclusiveCheck(bizMsg, ajeIntfcTpCd, ajePtrnIndTpCd, ajePtrnActlCd);
        List resultList = null;
        if (ssmResult.isCodeNormal()) {
            int size = ssmResult.getQueryResultCount();
            if (size > 0) {
                resultList = (List) ssmResult.getResultObject();
            }
        }
        return resultList;
    }
}
