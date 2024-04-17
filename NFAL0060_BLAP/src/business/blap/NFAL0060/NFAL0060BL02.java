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
import business.db.AJE_CD_TBL_LISTTMsg;
import business.db.AJE_CD_TBL_LISTTMsgArray;
import business.db.AJE_INTFC_TPTMsg;
import business.db.AJE_INTFC_TPTMsgArray;
import business.db.AJE_PTRN_IND_TPTMsg;
import business.db.AJE_PTRN_IND_TPTMsgArray;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * Class name: NFAL0060BL02
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0060BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0060BL02 extends S21BusinessHandler implements NFAL0060Constant {

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

            if ("NFAL0060_INIT".equals(screenAplID)) {
                doProcess_NFAL0060_INIT(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_SearchAllBtn".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_SearchAllBtn(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD_SEARCH".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD_SEARCH(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_OnChange_AJE_PTRN_IND_TP_CD".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_IND_TP_CD(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD(cMsg, sMsg);
            //---- start del 2016/03/24
            //} else if ("NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD".equals(screenAplID)) {
            //    doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD(cMsg, sMsg);
            //---- end 2016/03/24
            } else if ("NFAL0060Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_NFAL0060Scrn00_AddRow(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NFAL0060Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFAL0060Scrn00_CMN_Clear(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // 
            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL0060_INIT
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060_INIT================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        //common.getExistAjeIntfcTpCdList(bizMsg);
        common.getExistAjePtrnIndTpCdList(bizMsg);
        setAjeCdTblListCdListBox(bizMsg);
        //setAjeIntfcTpCdListBox(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060_INIT================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0060Scrn00_NFAL0060Scrn00_AddRow
     * <dd>The method explanation: Business processing for AddRow
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_NFAL0060Scrn00_AddRow(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_NFAL0060Scrn00_AddRow================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        if (sameKeyExist(bizMsg)) {
            // Refresh the list in case of adding a new code
            // after openning this session
            common.getExistAjePtrnIndTpCdList(bizMsg);
            // NFAM0019E=0,Code : @ cannot be added because
            // it has already been registered.
            //bizMsg.ajeIntfcTpCd_3.setErrorInfo(1, "NFAM0019E", new String[] {bizMsg.ajeIntfcTpNm.getValue() + ":" + bizMsg.ajePtrnIndTpCd_NW.getValue() });
            bizMsg.ajePtrnIndTpCd_NW.setErrorInfo(1, "NFAM0019E", new String[] {bizMsg.ajePtrnIndTpCd_NW.getValue() });
            return;
        }

        setRecordToTempAddRow(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_NFAL0060Scrn00_AddRow================================END", null);
    }

    private void setRecordToTempAddRow(NFAL0060CMsg bizMsg) {

        bizMsg.C.clear();
        bizMsg.C.setValidCount(0);

        // Selected "Actual Code Table" name
        String tableName = getTableName(bizMsg);
        if (ZYPCommonFunc.hasValue(tableName)) {
            bizMsg.xxTblNm.setValue(tableName);
            bizMsg.xxTblCdColNm.setValue(tableName + SUFFIX_CD);
            bizMsg.xxTblNmColNm.setValue(tableName + SUFFIX_NM);
            bizMsg.xxTblSortColNm.setValue(tableName + SUFFIX_SORT_NUM);

            S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getCdTblListCd(bizMsg);
            // The the code table
            // determins a new set of code
            if (ssmResult.isCodeNormal()) {
                List resultList = (List) ssmResult.getResultObject();
                int size = ssmResult.getQueryResultCount();
                for (int i = 0; i < resultList.size(); i++) {
                    Map map = (Map) resultList.get(i);
                    bizMsg.C.no(i).ajePtrnActlCd_C.setValue(parts.checkNull((String) map.get("COL_CD")));
                    bizMsg.C.no(i).ajePtrnActlNm_C.setValue(parts.checkNull((String) map.get("COL_NM")));
                }
                bizMsg.C.setValidCount(size);
            }
        }
    }

    private boolean sameKeyExist(NFAL0060CMsg bizMsg) {

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
        tMsg.setConditionValue("ajePtrnIndTpCd01", bizMsg.ajePtrnIndTpCd_NW.getValue());

        AJE_PTRN_IND_TPTMsgArray tMsgArr = (AJE_PTRN_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private String getTableName(NFAL0060CMsg bizMsg) {

        String ajeCdTblListCd = bizMsg.ajeCdTblListCd_3.getValue();
        for (int i = 0; i < bizMsg.ajeCdTblListCd_1.length(); i++) {
            if (bizMsg.ajeCdTblListCd_1.no(i).getValue().equals(ajeCdTblListCd)) {
                return bizMsg.ajeCdTblListNm_2.no(i).getValue();
            }
        }
        return null;
    }

    private void setAjeCdTblListCdListBox(NFAL0060CMsg bizMsg) {

        bizMsg.ajeCdTblListCd_1.clear();
        bizMsg.ajeCdTblListNm_2.clear();
        bizMsg.ajeCdTblListCd_3.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // AJE_CD_TBL_LIST_SORT_NUM ASC
        AJE_CD_TBL_LISTTMsg tMsg = new AJE_CD_TBL_LISTTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());

        AJE_CD_TBL_LISTTMsgArray tMsgArr = (AJE_CD_TBL_LISTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.ajeCdTblListCd_1.no(i).setValue(tMsgArr.no(i).ajeCdTblListCd.getValue());
                bizMsg.ajeCdTblListNm_2.no(i).setValue(tMsgArr.no(i).ajeCdTblListNm.getValue());
            }
        }
    }

    //---- start del 2016/03/24
    /*
    private void setAjeIntfcTpCdListBox(NFAL0060CMsg bizMsg) {

        bizMsg.ajeIntfcTpCd_1.clear();
        bizMsg.ajeIntfcTpCd_2.clear();
        bizMsg.ajeIntfcTpCd_3.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // AJE_INTFC_TP_SORT_NUM ASC
        AJE_INTFC_TPTMsg tMsg = new AJE_INTFC_TPTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());

        AJE_INTFC_TPTMsgArray tMsgArr = (AJE_INTFC_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            int index = 0;
            for (int i = 0; i < tMsgArr.length(); i++) {
                String ajeIntfcTpCd = tMsgArr.no(i).ajeIntfcTpCd.getValue();
                if (!ajeIntfcTpCd.equals(DEFAULT_INTFC_TP_CD)) {
                    bizMsg.ajeIntfcTpCd_1.no(index).setValue(ajeIntfcTpCd);
                    bizMsg.ajeIntfcTpCd_2.no(index).setValue(ajeIntfcTpCd);
                    index++;
                }
            }
        }
    }
    */
    //---- end 2016/03/24

    //
    /**
     * Method name:
     * doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD_SEARCH
     * <dd>The method explanation:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD_SEARCH(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD_SEARCH================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        //common.setAjeIntfcTpNmSearch(bizMsg);
        common.doSearch(bizMsg, SEARCH_BY_KEY);
        common.setResetFields(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD_SEARCH================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD
     * <dd>The method explanation:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    //---- start del 2016/03/24
    /*
    private void doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        setAjeIntfcTpNm(bizMsg);

        String ajeIntfcTblNm = getAjeIntfcTblNm(bizMsg);

        bizMsg.ajeIntfcColTxt_1.clear();
        bizMsg.ajeIntfcColTxt_2.clear();
        bizMsg.ajeIntfcColTxt_3.clear();

        if (!ajeIntfcTblNm.equals(BLANK)) {
            setAjeIntfcColTxtListBox(bizMsg, ajeIntfcTblNm);
        } else {
            // NFAM0024E=0,Code @ was not found as @
            bizMsg.ajeIntfcTpCd_3.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeIntfcTpCd_3.getValue() + ":" + bizMsg.ajeIntfcTpNm.getValue(), "Interface Table" });
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_INTFC_TP_CD================================END", null);
    }
    
    private String getAjeIntfcTblNm(NFAL0060CMsg bizMsg) {

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // AJE_INTFC_TP_CD = ?ajeIntfcTpCd01?
        AJE_INTFC_TPTMsg tMsg = new AJE_INTFC_TPTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("ajeIntfcTpCd01", bizMsg.ajeIntfcTpCd_3.getValue());

        AJE_INTFC_TPTMsgArray tMsgArr = (AJE_INTFC_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            return parts.checkNull(tMsgArr.no(0).ajeIntfcTblNm.getValue());
        } else {
            return BLANK;
        }
    }
    

    private void setAjeIntfcColTxtListBox(NFAL0060CMsg bizMsg, String ajeIntfcTblNm) {

        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getColumnNameByIntfcNm(bizMsg, ajeIntfcTblNm);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            int index = 0;
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                String columnName = parts.checkNull((String) map.get("COLUMN_NAME"));
                if (ZYPCommonFunc.hasValue(columnName) && !columnName.startsWith("EZ")) {
                    bizMsg.ajeIntfcColTxt_1.no(index).setValue(columnName);
                    bizMsg.ajeIntfcColTxt_2.no(index).setValue(columnName);
                    index++;
                }
            }
        } else {
            // NFAM0024E=0,Code @ was not found as @
            bizMsg.ajeIntfcTpCd_3.setErrorInfo(1, "NFAM0024E", new String[] {ajeIntfcTblNm, "Table" });
        }
    }
 
    
    private void setAjeIntfcTpNm(NFAL0060CMsg bizMsg) {

        bizMsg.ajeIntfcTpNm.clear();

        // <finder>
        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // AJE_INTFC_TP_CD = ?ajeIntfcTpCd01?
        AJE_INTFC_TPTMsg tMsg = new AJE_INTFC_TPTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("ajeIntfcTpCd01", bizMsg.ajeIntfcTpCd_3.getValue());

        AJE_INTFC_TPTMsgArray tMsgArr = (AJE_INTFC_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.ajeIntfcTpNm.setValue(tMsgArr.no(0).ajeIntfcTpNm.getValue());
        }
    }
   */
  //---- end 2016/03/24

    /**
     * Method name: doProcess_NFAL0060Scrn00_SearchAllBtn
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_SearchAllBtn(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_SearchAllBtn================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        common.doSearch(bizMsg, SEARCH_ALL);
        common.setResetFields(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_SearchAllBtn================================END", null);
    }

    /**
     * Method name:
     * doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_IND_TP_CD
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_IND_TP_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_IND_TP_CD================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        common.setAjeActlCdListBox(bizMsg);
        common.setAjePtrnIndTpNm(bizMsg);
        common.doSearch(bizMsg, SEARCH_BY_KEY);
        common.setResetFields(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_IND_TP_CD================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        common.setAjePtrnActlNm(bizMsg);
        common.doSearch(bizMsg, SEARCH_BY_KEY);
        common.setResetFields(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_OnChange_AJE_PTRN_ACTL_CD================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0060Scrn00_CMN_Reset
     * <dd>The method explanation: Business processing for Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Reset================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;

        common.doSearch(bizMsg, SEARCH_BY_KEY);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Reset================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0060Scrn00_CMN_Clear
     * <dd>The method explanation: Business processing for Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0060Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Clear================================START", null);

        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        common.getExistAjePtrnIndTpCdList(bizMsg);
        //common.getExistAjeIntfcTpCdList(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0060Scrn00_CMN_Clear================================END", null);
    }
}
