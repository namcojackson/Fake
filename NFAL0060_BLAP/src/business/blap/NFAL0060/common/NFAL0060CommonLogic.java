/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0060.common;

import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFAL0060.NFAL0060CMsg;
import business.blap.NFAL0060.NFAL0060Query;
import business.blap.NFAL0060.constant.NFAL0060Constant;
import business.db.AJE_INTFC_TPTMsg;
import business.db.AJE_INTFC_TPTMsgArray;
import business.db.AJE_PTRNTMsg;
import business.db.AJE_PTRNTMsgArray;
import business.db.AJE_PTRN_IND_TPTMsg;
import business.db.AJE_PTRN_IND_TPTMsgArray;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NFAL0060CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0060CommonLogic implements NFAL0060Constant {

    /** S21UserProfileService profileService */
    private static S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** String globalCompanyCode */
    private static String globalCompanyCode = profileService.getGlobalCompanyCode();

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    /**
     * Method name: getGlobalCompanyCode
     * <dd>The method explanation:
     * <dd>Remarks:
     * @return String globalCompanyCode
     */
    public static String getGlobalCompanyCode() {
        return globalCompanyCode;
    }

    /**
     * Method name: doSearch
     * <dd>The method explanation: Search Record
     * @param bizMsg Business Component Interface Message
     * @param searchMode int
     */
    public void doSearch(NFAL0060CMsg bizMsg, int searchMode) {

        int size = 0;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(size);

        if (isSearchFiledAllClear(bizMsg) && (searchMode == SEARCH_BY_KEY || searchMode == RESET)) {
            // When on-change event and all search fields are clear
            // only clear the list
            return;
        }

        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getAjePtrnIndTpByKey(bizMsg);
        if (ssmResult.isCodeNormal()) {
            size = ssmResult.getQueryResultCount();
            // Store timestamp
            if (size > 0) {
                List resultList = (List) ssmResult.getResultObject();
                for (int i = 0; i < size; i++) {
                    Map map = (Map) resultList.get(i);
                    if (i == 0) {
                        bizMsg.ezUpTime.setValue(parts.checkNull((String) map.get("EZUPTIME")));
                        bizMsg.ezUpTimeZone.setValue(parts.checkNull((String) map.get("EZUPTIMEZONE")));
                    }
                    bizMsg.A.no(i).ezUpTime_A.setValue(parts.checkNull((String) map.get("EZUPTIME")));
                    bizMsg.A.no(i).ezUpTimeZone_A.setValue(parts.checkNull((String) map.get("EZUPTIMEZONE")));

                    //bizMsg.A.no(i).ajeIntfcTpCd_A.setValue(parts.checkNull((String) map.get("AJE_INTFC_TP_CD")));
                    bizMsg.A.no(i).ajePtrnIndTpCd_A.setValue(parts.checkNull((String) map.get("AJE_PTRN_IND_TP_CD")));
                    bizMsg.A.no(i).ajePtrnIndTpNm_A.setValue(parts.checkNull((String) map.get("AJE_PTRN_IND_TP_NM")));

                    bizMsg.A.no(i).ajePtrnActlCd_A.setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                    bizMsg.A.no(i).ajePtrnActlNm_A.setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
                    //bizMsg.A.no(i).ajeIntfcTpNm_A.setValue(parts.checkNull((String) map.get("AJE_INTFC_TP_NM")));
                    bizMsg.A.no(i).ajeIntfcColTxt_A.setValue(parts.checkNull((String) map.get("AJE_INTFC_COL_TXT")));
                }
            }
            bizMsg.A.setValidCount(size);
        }
        if (searchMode == SEARCH_BY_KEY || searchMode == SEARCH_ALL) {
            // Showing search result info;
            // number or telling it's over limit
            if (size == bizMsg.A.length()) {
                bizMsg.setMessageInfo("NFAM0008E", new String[] {Integer.toString(size) });
            } else {
                bizMsg.setMessageInfo("NFAM0067I", new String[] {Integer.toString(size) });
            }
        }
    }

    private boolean isSearchFiledAllClear(NFAL0060CMsg bizMsg) {
        //if (bizMsg.ajeIntfcTpCd_3S.isClear() && bizMsg.ajePtrnIndTpCd_3.isClear() && bizMsg.ajePtrnActlCd_3.isClear()) {
        if (bizMsg.ajePtrnIndTpCd_3.isClear() && bizMsg.ajePtrnActlCd_3.isClear()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: setAjePtrnIndTpNm
     * <dd>The method explanation: Search Record
     * @param bizMsg Business Component Interface Message
     */
    public void setAjePtrnIndTpNm(NFAL0060CMsg bizMsg) {

        bizMsg.ajePtrnIndTpNm.clear();

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // AJE_PTRN_IND_TP_CD = ?ajePtrnIndTpCd01?
        // ORDER BY
        // AJE_PTRN_ACTL_CD ASC
        AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("ajePtrnIndTpCd01", bizMsg.ajePtrnIndTpCd_3.getValue());

        AJE_PTRN_IND_TPTMsgArray tMsgArr = (AJE_PTRN_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.ajePtrnIndTpNm.setValue(tMsgArr.no(0).ajePtrnIndTpNm.getValue());
        }
    }

    /**
     * Method name: getExistAjeIntfcTpCdList
     * <dd>The method explanation:
     * @param bizMsg Business Component Interface Message
     */
    //---- start 2016/03/24 del
    /*
    public void getExistAjeIntfcTpCdList(NFAL0060CMsg bizMsg) {

        bizMsg.ajeIntfcTpCd_1S.clear();
        bizMsg.ajeIntfcTpCd_2S.clear();
        bizMsg.ajeIntfcTpCd_3S.clear();
        bizMsg.ajeIntfcTpNm_S.clear();

        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getExistAjeIntfcTpCdList(bizMsg);
        int size = 0;
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            size = resultList.size();
            int index = 0;
            for (int i = 0; i < size; i++) {
                Map map = (Map) resultList.get(i);

                String ajeIntfcTpCd = parts.checkNull((String) map.get("AJE_INTFC_TP_CD"));
                if (!ajeIntfcTpCd.equals(DEFAULT_INTFC_TP_CD)) {
                    bizMsg.ajeIntfcTpCd_1S.no(index).setValue(ajeIntfcTpCd);
                    bizMsg.ajeIntfcTpCd_2S.no(index).setValue(ajeIntfcTpCd);
                    index++;
                }
            }
        }
    }
    */
    //---- end 2016/03/24

    /**
     * Method name: getExistAjePtrnIndTpCdList
     * <dd>The method explanation:
     * @param bizMsg Business Component Interface Message
     */
    public void getExistAjePtrnIndTpCdList(NFAL0060CMsg bizMsg) {

        bizMsg.ajePtrnIndTpCd_1.clear();
        bizMsg.ajePtrnIndTpCd_2.clear();
        bizMsg.ajePtrnIndTpCd_3.clear();
        bizMsg.ajePtrnIndTpNm.clear();

        bizMsg.ajePtrnActlCd_1.clear();
        bizMsg.ajePtrnActlCd_2.clear();
        bizMsg.ajePtrnActlCd_3.clear();
        bizMsg.ajePtrnActlNm.clear();

        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getExistAjePtrnIndTpCdList(bizMsg);
        int size = 0;
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            size = resultList.size();
            int index = 0;
            for (int i = 0; i < size; i++) {
                Map map = (Map) resultList.get(i);

                String ajePtrnIndTpCd = parts.checkNull((String) map.get("AJE_PTRN_IND_TP_CD"));
                if (!ajePtrnIndTpCd.equals(DEFAULT_IND_TP_CD)) {
                    bizMsg.ajePtrnIndTpCd_1.no(index).setValue(ajePtrnIndTpCd);
                    bizMsg.ajePtrnIndTpCd_2.no(index).setValue(ajePtrnIndTpCd);
                    index++;
                }
            }
        }
    }

    /**
     * Method name: setAjeActlCdListBox
     * <dd>The method explanation:
     * @param bizMsg Business Component Interface Message
     */
    public void setAjeActlCdListBox(NFAL0060CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_1.clear();
        bizMsg.ajePtrnActlCd_2.clear();
        bizMsg.ajePtrnActlCd_3.clear();
        bizMsg.ajePtrnActlNm.clear();

        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_3.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            String selectedVal = BLANK;
            String selectedNm = BLANK;
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_1.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlCd_2.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                selectedVal = parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD"));
                selectedNm = parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM"));
            }
            // Set selected value when only one code in the list
            if (i == 1) {
                bizMsg.ajePtrnActlCd_3.setValue(selectedVal);
                bizMsg.ajePtrnActlNm.setValue(selectedNm);
            }
        }
    }

    /**
     * Method name: setAjePtrnActlNm
     * <dd>The method explanation:
     * @param bizMsg Business Component Interface Message
     */
    public void setAjePtrnActlNm(NFAL0060CMsg bizMsg) {

        bizMsg.ajePtrnActlNm.clear();

        // <ID>804</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0'
        // AND GLBL_CMPY_CD = ?glblCmpyCd01?
        // AND AJE_PTRN_IND_TP_CD = ?ajePtrnIndTpCd01?
        // AND AJE_PTRN_ACTL_CD = ?ajePtrnActlCd01?
        AJE_PTRN_IND_TPTMsg tMsg = new AJE_PTRN_IND_TPTMsg();
        tMsg.setSQLID("804");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("ajePtrnIndTpCd01", bizMsg.ajePtrnIndTpCd_3.getValue());
        tMsg.setConditionValue("ajePtrnActlCd01", bizMsg.ajePtrnActlCd_3.getValue());

        AJE_PTRN_IND_TPTMsgArray tMsgArr = (AJE_PTRN_IND_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.ajePtrnActlNm.setValue(tMsgArr.no(0).ajePtrnActlNm.getValue());
        } else {
            bizMsg.ajePtrnActlNm.clear();
        }
    }

    /**
     * Method name: setAjeIntfcTpNmSearch
     * <dd>The method explanation:
     * @param bizMsg Business Component Interface Message
     */
    //---- start del 2016/03/24
    /*
    public void setAjeIntfcTpNmSearch(NFAL0060CMsg bizMsg) {

        bizMsg.ajeIntfcTpNm_S.clear();

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
        tMsg.setConditionValue("ajeIntfcTpCd01", bizMsg.ajeIntfcTpCd_3S.getValue());

        AJE_INTFC_TPTMsgArray tMsgArr = (AJE_INTFC_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.ajeIntfcTpNm_S.setValue(tMsgArr.no(0).ajeIntfcTpNm.getValue());
        }
    }
    */
    //---- end 2016/03/24

    /**
     * Method name: setResetFields
     * <dd>The method explanation:
     * @param bizMsg Business Component Interface Message
     */
    public void setResetFields(NFAL0060CMsg bizMsg) {
        //bizMsg.ajeIntfcTpCd_TS.setValue(bizMsg.ajeIntfcTpCd_3S.getValue());
        bizMsg.ajePtrnIndTpCd_T.setValue(bizMsg.ajePtrnIndTpCd_3.getValue());
        bizMsg.ajePtrnActlCd_T.setValue(bizMsg.ajePtrnActlCd_3.getValue());
    }
    
    //---- start add 2016/03/24
    // Logically delete patterns if its pattern indicator type in deleted.
    public static void deleteAJEPtrn(NFAL0060CMsg bizMsg) {
        
        S21SsmEZDResult ssmResult = NFAL0060Query.getInstance().getPtrnToBeDeleted(bizMsg);
        
        if (ssmResult.isCodeNormal()) {
        
            List<AJE_PTRNTMsg> result = (List<AJE_PTRNTMsg>) ssmResult.getResultObject(); 
            
            if (result.size() > 0) {
                int cnt = S21FastTBLAccessor.removeLogical(result.toArray(new AJE_PTRNTMsg[result.size()]));
                
                if (cnt != result.size()) {
                    bizMsg.setMessageInfo("NFAM0035E", new String[] {"DB update process", "AJE Pattern removal error"});
                    return;
                }
            }
        }
        
    }
    
    //---- end 2016/03/24
}
