/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1310.common;

import static business.blap.NSAL1310.constant.NSAL1310Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1310.NSAL1310CMsg;
import business.blap.NSAL1310.NSAL1310Query;
import business.blap.NSAL1310.NSAL1310SMsg;
import business.blap.NSAL1310.NSAL1310_ACMsg;
import business.blap.NSAL1310.NSAL1310_ASMsg;
import business.db.DEF_COV_TERM_CONDTMsg;
import business.db.SVC_TERM_COND_ATTRBTMsg;
import business.db.SVC_TERM_COND_ATTRB_MAPTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 * 2018/07/31   Hitachi         A.Kohinata      Update          QC#26659
 *</pre>
 */
public class NSAL1310CommonLogic {

    /**
     * getSvcTermCondAttrbTMsg
     * @param glblCmpyCd String
     * @param svcTermCondAttrbPk BigDecimal
     * @return SVC_TERM_COND_ATTRBTMsg
     */
    public static SVC_TERM_COND_ATTRBTMsg getSvcTermCondAttrbTMsg(String glblCmpyCd, BigDecimal svcTermCondAttrbPk) {
        SVC_TERM_COND_ATTRBTMsg tMsg = new SVC_TERM_COND_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondAttrbPk, svcTermCondAttrbPk);
        return (SVC_TERM_COND_ATTRBTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * getSvcTermCondAttrbMapTMsg
     * @param glblCmpyCd String
     * @param physMaintTrgtTblNm String
     * @return SVC_TERM_COND_ATTRB_MAPTMsg
     */
    public static SVC_TERM_COND_ATTRB_MAPTMsg getSvcTermCondAttrbMapTMsg(String glblCmpyCd, String physMaintTrgtTblNm) {
        SVC_TERM_COND_ATTRB_MAPTMsg tMsg = new SVC_TERM_COND_ATTRB_MAPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.physMaintTrgtTblNm, physMaintTrgtTblNm);
        return (SVC_TERM_COND_ATTRB_MAPTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    /**
     * createDropdown
     * @param glblCmpyCd String
     * @param acMsg NSAL1310_ACMsg
     * @param dropdownDataListMap List<Map<String, Object>>>
     */
    public static void createDropdown(String glblCmpyCd, NSAL1310_ACMsg acMsg, Map<String, List<Map<String, Object>>> dropdownDataListMap) {
        List<Map<String, Object>> dropdownDataList = null;
        if (dropdownDataListMap != null) {
            dropdownDataList = dropdownDataListMap.get(acMsg.svcTermCondDataSrcCd_A1.getValue());
        }
        if (dropdownDataList == null) {
            dropdownDataList = NSAL1310Query.getInstance().getDropDownData(glblCmpyCd, acMsg.svcTermCondDataSrcCd_A1.getValue());
            if (dropdownDataListMap != null) {
                dropdownDataListMap.put(acMsg.svcTermCondDataSrcCd_A1.getValue(), dropdownDataList);
            }
        }
        if (dropdownDataList != null) {
            for (int i = 0; i < dropdownDataList.size(); i++) {
                Map<String, Object> dropdownData = dropdownDataList.get(i);
                ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_DC.no(i), (String) dropdownData.get(SVC_TERM_COND_DATA_VAL_CD));
                ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataDispTxt_DS.no(i), (String) dropdownData.get(SVC_TERM_COND_DATA_DISP_TXT));
            }
        }
    }

    /**
     * createDropdown
     * @param glblCmpyCd String
     * @param asMsg NSAL1310_ASMsg
     * @param dropdownDataListMap List<Map<String, Object>>>
     */
    public static void createDropdown(String glblCmpyCd, NSAL1310_ASMsg asMsg, Map<String, List<Map<String, Object>>> dropdownDataListMap) {
        List<Map<String, Object>> dropdownDataList = null;
        if (dropdownDataListMap != null) {
            dropdownDataList = dropdownDataListMap.get(asMsg.svcTermCondDataSrcCd_A1.getValue());
        }
        if (dropdownDataList == null) {
            dropdownDataList = NSAL1310Query.getInstance().getDropDownData(glblCmpyCd, asMsg.svcTermCondDataSrcCd_A1.getValue());
            if (dropdownDataListMap != null) {
                dropdownDataListMap.put(asMsg.svcTermCondDataSrcCd_A1.getValue(), dropdownDataList);
            }
        }
        if (dropdownDataList != null) {
            for (int i = 0; i < dropdownDataList.size(); i++) {
                Map<String, Object> dropdownData = dropdownDataList.get(i);
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataValCd_DC.no(i), (String) dropdownData.get(SVC_TERM_COND_DATA_VAL_CD));
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataDispTxt_DS.no(i), (String) dropdownData.get(SVC_TERM_COND_DATA_DISP_TXT));
            }
        }
    }

    /**
     * createLookup
     * @param glblCmpyCd String
     * @param acMsg NSAL1310_ACMsg
     * @param lookupDataListMap Map<String, List<Map<String, Object>>>
     */
    public static void createLookup(String glblCmpyCd, NSAL1310_ACMsg acMsg, Map<String, List<Map<String, Object>>> lookupDataListMap) {
        List<Map<String, Object>> lookupDataList = null;
        if (lookupDataListMap != null) {
            lookupDataList = lookupDataListMap.get(acMsg.physMaintTrgtTblNm_A1.getValue());
        }
        if (lookupDataList == null) {
            SVC_TERM_COND_ATTRB_MAPTMsg tMsg = getSvcTermCondAttrbMapTMsg(glblCmpyCd, acMsg.physMaintTrgtTblNm_A1.getValue());
            lookupDataList = NSAL1310Query.getInstance().getLookupData(tMsg);
            if (lookupDataListMap != null) {
                lookupDataListMap.put(acMsg.physMaintTrgtTblNm_A1.getValue(), lookupDataList);
            }
        }
        if (lookupDataList != null) {
            for (int i = 0; i < lookupDataList.size(); i++) {
                Map<String, Object> lookupData = lookupDataList.get(i);
                ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_LC.no(i), (String) lookupData.get(TARGET_CD));
                ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataDispTxt_LS.no(i), (String) lookupData.get(TARGET_NAME));
            }
        }
    }

    /**
     * createLookup
     * @param glblCmpyCd String
     * @param asMsg NSAL1310_ASMsg
     * @param lookupDataListMap Map<String, List<Map<String, Object>>>
     */
    public static void createLookup(String glblCmpyCd, NSAL1310_ASMsg asMsg, Map<String, List<Map<String, Object>>> lookupDataListMap) {
        List<Map<String, Object>> lookupDataList = null;
        if (lookupDataListMap != null) {
            lookupDataList = lookupDataListMap.get(asMsg.physMaintTrgtTblNm_A1.getValue());
        }
        if (lookupDataList == null) {
            SVC_TERM_COND_ATTRB_MAPTMsg tMsg = getSvcTermCondAttrbMapTMsg(glblCmpyCd, asMsg.physMaintTrgtTblNm_A1.getValue());
            lookupDataList = NSAL1310Query.getInstance().getLookupData(tMsg);
            if (lookupDataListMap != null) {
                lookupDataListMap.put(asMsg.physMaintTrgtTblNm_A1.getValue(), lookupDataList);
            }
        }
        if (lookupDataList != null) {
            for (int i = 0; i < lookupDataList.size(); i++) {
                Map<String, Object> lookupData = lookupDataList.get(i);
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataValCd_LC.no(i), (String) lookupData.get(TARGET_CD));
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataDispTxt_LS.no(i), (String) lookupData.get(TARGET_NAME));
            }
        }
    }

    /**
     * getDefaultValue
     * @param asMsg NSAL1310_ASMsg
     * @return String
     */
    public static String getDefaultValue(NSAL1310_ASMsg asMsg) {
        if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            return asMsg.svcTermCondDataValCd_D1.getValue();
        } else if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            return asMsg.svcTermAttrbDefValTxt_A1.getValue();
        } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            if (ZYPCommonFunc.hasValue(asMsg.condValNum_A1)) {
                return asMsg.condValNum_A1.getValue().toString();
            } else {
                return null;
            }
        } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            return asMsg.xxTrxDt_A1.getValue();
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            return asMsg.svcTermCondDataValCd_L1.getValue();
        // add start 2018/06/25 QC#17369
        } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            return asMsg.svcTermCondDataValCd_L1.getValue();
        // add end 2018/06/25 QC#17369
        // add start 2018/07/31 QC#26659
        } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(asMsg.svcTermDataTpCd_A1.getValue())) {
            return asMsg.svcTermAttrbDefValTxt_A1.getValue();
        // add end 2018/07/31 QC#26659
        }
        return null;
    }

    /**
     * changeCheck
     * @param acMsg NSAL1310_ACMsg
     * @return boolean
     */
    public static boolean changeCheck(NSAL1310_ASMsg asMsg) {
        String svcTermAttrbDefValTxt = nvl(getDefaultValue(asMsg));
        String attrbUpdAvalFlg = convertCheckBoxFlag(asMsg.attrbUpdAvalFlg_A1.getValue());
        String asgContrLvlFlg = convertCheckBoxFlag(asMsg.asgContrLvlFlg_A1.getValue());
        String asgMachLvlFlg = convertCheckBoxFlag(asMsg.asgMachLvlFlg_A1.getValue());
        String covTermCondActvFlg = convertCheckBoxFlag(asMsg.covTermCondActvFlg_A1.getValue());
        String effFromDt = nvl(asMsg.effFromDt_A1.getValue());
        String effThruDt = nvl(asMsg.effThruDt_A1.getValue());

        String backupSvcTermAttrbDefValTxt = nvl(asMsg.svcTermAttrbDefValTxt_BK.getValue());
        String backupAttrbUpdAvalFlg = asMsg.attrbUpdAvalFlg_BK.getValue();
        String backupAsgContrLvlFlg = asMsg.asgContrLvlFlg_BK.getValue();
        String backupAsgMachLvlFlg = asMsg.asgMachLvlFlg_BK.getValue();
        String backupCovTermCondActvFlg = asMsg.covTermCondActvFlg_BK.getValue();
        String backupEffFromDt = nvl(asMsg.effFromDt_BK.getValue());
        String backupEffThruDt = nvl(asMsg.effThruDt_BK.getValue());

        if (svcTermAttrbDefValTxt.equals(backupSvcTermAttrbDefValTxt) // DefaultValue
                && attrbUpdAvalFlg.equals(backupAttrbUpdAvalFlg) // AttrbUpdatable
                && asgContrLvlFlg.equals(backupAsgContrLvlFlg) // ContarctLevel
                && asgMachLvlFlg.equals(backupAsgMachLvlFlg) // MachineLevel
                && covTermCondActvFlg.equals(backupCovTermCondActvFlg) // ActiveFlag
                && effFromDt.equals(backupEffFromDt) // EffectiveFromDate
                && effThruDt.equals(backupEffThruDt) // EffectiveThruDate
        ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * setDefCovTermCondTMsg
     * @param asMsg NSAL1310_ASMsg
     * @param tMsg DEF_COV_TERM_CONDTMsg
     */
    public static void setDefCovTermCondTMsg(NSAL1310_ASMsg asMsg, DEF_COV_TERM_CONDTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.svcTermAttrbDefValTxt, NSAL1310CommonLogic.getDefaultValue(asMsg));
        ZYPEZDItemValueSetter.setValue(tMsg.attrbUpdAvalFlg, NSAL1310CommonLogic.convertCheckBoxFlag(asMsg.attrbUpdAvalFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.asgContrLvlFlg, NSAL1310CommonLogic.convertCheckBoxFlag(asMsg.asgContrLvlFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.asgMachLvlFlg, NSAL1310CommonLogic.convertCheckBoxFlag(asMsg.asgMachLvlFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.covTermCondActvFlg, NSAL1310CommonLogic.convertCheckBoxFlag(asMsg.covTermCondActvFlg_A1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, asMsg.effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, asMsg.effThruDt_A1);
    }

    /**
     * convertCheckBoxFlag
     * @param str String
     * @return String
     */
    public static String convertCheckBoxFlag(String str) {
        if (str == null || !str.equals(ZYPConstant.FLG_ON_Y)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    /**
     * nvl
     * @param str String
     * @return String
     */
    private static String nvl(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * nvl
     * @param obj EZDTStringItem
     * @return String
     */
    private static String nvl(EZDTStringItem obj) {
        if (ZYPCommonFunc.hasValue(obj)) {
            return obj.getValue();
        } else {
            return "";
        }
    }

    /**
     * nvl
     * @param obj EZDTDateItem
     * @return String
     */
    private static String nvl(EZDTDateItem obj) {
        if (ZYPCommonFunc.hasValue(obj)) {
            return obj.getValue();
        } else {
            return "";
        }
    }

    /**
     * checkDuplicate
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     * @return boolean
     */
    public static boolean checkDuplicate(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        boolean checkResult = true;

        Map<String, List<BigDecimal>> pkListMap = new HashMap<String, List<BigDecimal>>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1310_ASMsg asMsg = sMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(asMsg.defCovTermCondPk_A1)) {
                String svcCovTmplTpCd = asMsg.svcCovTmplTpCd_A1.getValue();
                List<BigDecimal> pkList = null;
                if (pkListMap.containsKey(svcCovTmplTpCd)) {
                    pkList = pkListMap.get(svcCovTmplTpCd);
                } else {
                    pkList = new ArrayList<BigDecimal>();
                }
                pkList.add(asMsg.defCovTermCondPk_A1.getValue());
                pkListMap.put(svcCovTmplTpCd, pkList);
            }
        }

        int firstErrNumber = -1;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL1310_ASMsg asMsg = sMsg.A.no(i);
            if (!NSAL1310CommonLogic.changeCheck(asMsg)) {
                continue;
            }
            // screen duplicate check
            boolean duplicateErroFlag = false;
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }
                NSAL1310_ASMsg asMsgCheck = sMsg.A.no(j);
                if (asMsg.svcCovTmplTpCd_A1.getValue().equals(asMsgCheck.svcCovTmplTpCd_A1.getValue()) //
                        && (asMsg.svcTermCondAttrbPk_A1.getValue().compareTo(asMsgCheck.svcTermCondAttrbPk_A1.getValue()) == 0)) {
                    if (!checkDuplicateDate(asMsg.effFromDt_A1.getValue(), asMsg.effThruDt_A1.getValue(), asMsgCheck.effFromDt_A1.getValue(), asMsgCheck.effThruDt_A1.getValue())) {
                        asMsg.svcCovTmplTpCd_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                        asMsg.svcTermAttrbDescTxt_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                        asMsg.effFromDt_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                        asMsg.effThruDt_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                        checkResult = false;
                        duplicateErroFlag = true;
                        if (firstErrNumber < 0) {
                            firstErrNumber = i;
                        }
                    }
                }
            }
            // database duplicate check
            if (duplicateErroFlag == false) {
                List<BigDecimal> pkList = null;
                if (pkListMap.containsKey(asMsg.svcCovTmplTpCd_A1.getValue())) {
                    pkList = pkListMap.get(asMsg.svcCovTmplTpCd_A1.getValue());
                }
                BigDecimal sameDataCount = NSAL1310Query.getInstance().getDuplicateData(cMsg.glblCmpyCd.getValue(), asMsg.svcCovTmplTpCd_A1.getValue(), //
                        asMsg.svcTermCondAttrbPk_A1.getValue(), asMsg.effFromDt_A1.getValue(), asMsg.effThruDt_A1.getValue(), pkList);
                if (BigDecimal.ZERO.compareTo(sameDataCount) < 0) {
                    asMsg.svcCovTmplTpCd_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                    asMsg.svcTermAttrbDescTxt_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                    asMsg.effFromDt_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                    asMsg.effThruDt_A1.setErrorInfo(1, NSAM0035E, new String[] {"Coverage Template, T&C Attrb" });
                    checkResult = false;
                    duplicateErroFlag = true;
                    if (firstErrNumber < 0) {
                        firstErrNumber = i;
                    }
                }

            }
        }
        if (firstErrNumber >= 0) {
            moveViewPage(cMsg, sMsg, firstErrNumber);
        }
        return checkResult;
    }

    /**
     * checkDuplicateDate
     * @param fromDt1 String
     * @param thruDt1 String
     * @param fromDt2 String
     * @param thruDt2 String
     * @return boolean
     */
    private static boolean checkDuplicateDate(String fromDt1, String thruDt1, String fromDt2, String thruDt2) {
        if (thruDt1 == null || thruDt1.length() == 0) {
            thruDt1 = MAX_DATE;
        }
        if (thruDt2 == null || thruDt2.length() == 0) {
            thruDt2 = MAX_DATE;
        }
        if (fromDt2.compareTo(thruDt1) <= 0 && thruDt2.compareTo(fromDt1) >= 0) {
            return false;
        }
        return true;
    }

    /**
     * checkDate
     * @param cMsg NSAL1310CMsg
     * @return boolean
     */
    public static boolean checkDate(NSAL1310CMsg cMsg) {
        boolean checkResult = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL1310_ACMsg acMsg = cMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(acMsg.effThruDt_A1)) {
                if (ZYPCommonFunc.hasValue(acMsg.effFromDt_A1)) {
                    int start = Integer.parseInt(acMsg.effFromDt_A1.getValue());
                    int end = Integer.parseInt(acMsg.effThruDt_A1.getValue());
                    if (start > end) {
                        acMsg.effThruDt_A1.setErrorInfo(1, NSAM0457E, new String[] {"Effective Thru Date", "Effective From Date" });
                        checkResult = false;
                    }
                }
            } else {
                if (!ZYPCommonFunc.hasValue(acMsg.covTermCondActvFlg_A1)) {
                    acMsg.effThruDt_A1.setErrorInfo(1, NSAM0081E, new String[] {"Active Checkbox OFF", "Effective Thru Date" });
                    checkResult = false;
                }
            }
        }
        return checkResult;
    }

    /**
     * pagenation
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg, int pageFrom) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowFromNum_BK.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     */
    public static void copyCMsgToSMsg(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg) {
        copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - 1);
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     * @param fromCnt int
     */
    public static void copyCMsgToSMsg(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.A.getValidCount()); i++) {
            EZDMsg.copy(cMsg.A.no(cMsgCnt), null, sMsg.A.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * moveErroPage
     * @param cMsg NSAL1310CMsg
     * @param sMsg NSAL1310SMsg
     * @param viewNumber int
     */
    public static void moveViewPage(NSAL1310CMsg cMsg, NSAL1310SMsg sMsg, int viewNumber) {
        int pageNumber = (viewNumber - 1) / cMsg.A.length();
        pagenation(cMsg, sMsg, pageNumber * cMsg.A.length());
    }

    // add start 2018/06/25 QC#17369
    /**
     * setLookupPopup
     * @param glblCmpyCd String
     * @param acMsg NSAL1310_ACMsg
     */
    public static void setLookupPopup(String glblCmpyCd, NSAL1310_ACMsg acMsg) {
        if (!ZYPCommonFunc.hasValue(acMsg.physMaintTrgtTblNm_A1)) {
            return;
        }
        SVC_TERM_COND_ATTRB_MAPTMsg tMsg = getSvcTermCondAttrbMapTMsg(glblCmpyCd, acMsg.physMaintTrgtTblNm_A1.getValue());
        if (tMsg == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(acMsg.logicMaintTrgtTblNm_A1, tMsg.logicMaintTrgtTblNm);
        ZYPEZDItemValueSetter.setValue(acMsg.physMaintTrgtColNm_A1, tMsg.physMaintTrgtColNm);
        ZYPEZDItemValueSetter.setValue(acMsg.logicMaintTrgtColNm_A1, tMsg.logicMaintTrgtColNm);
        ZYPEZDItemValueSetter.setValue(acMsg.physDplyTrgtColNm_A1, tMsg.physDplyTrgtColNm);
        ZYPEZDItemValueSetter.setValue(acMsg.logicDplyTrgtColNm_A1, tMsg.logicDplyTrgtColNm);

        if (ZYPCommonFunc.hasValue(acMsg.svcTermAttrbDefValTxt_A1)) {
            Map<String, Object> lookupData = NSAL1310Query.getInstance().getLookupPopupData(tMsg, acMsg.svcTermAttrbDefValTxt_A1.getValue());
            if (lookupData != null) {
                ZYPEZDItemValueSetter.setValue(acMsg.svcTermCondDataValCd_L1, (String) lookupData.get(TARGET_CD));
                ZYPEZDItemValueSetter.setValue(acMsg.svcTermAttrbDefValTxt_A1, (String) lookupData.get(TARGET_NAME));
            }
        }
    }

    /**
     * setLookupPopup
     * @param glblCmpyCd String
     * @param asMsg NSAL1310_ASMsg
     */
    public static void setLookupPopup(String glblCmpyCd, NSAL1310_ASMsg asMsg) {
        if (!ZYPCommonFunc.hasValue(asMsg.physMaintTrgtTblNm_A1)) {
            return;
        }
        SVC_TERM_COND_ATTRB_MAPTMsg tMsg = getSvcTermCondAttrbMapTMsg(glblCmpyCd, asMsg.physMaintTrgtTblNm_A1.getValue());
        if (tMsg == null) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(asMsg.logicMaintTrgtTblNm_A1, tMsg.logicMaintTrgtTblNm);
        ZYPEZDItemValueSetter.setValue(asMsg.physMaintTrgtColNm_A1, tMsg.physMaintTrgtColNm);
        ZYPEZDItemValueSetter.setValue(asMsg.logicMaintTrgtColNm_A1, tMsg.logicMaintTrgtColNm);
        ZYPEZDItemValueSetter.setValue(asMsg.physDplyTrgtColNm_A1, tMsg.physDplyTrgtColNm);
        ZYPEZDItemValueSetter.setValue(asMsg.logicDplyTrgtColNm_A1, tMsg.logicDplyTrgtColNm);

        if (ZYPCommonFunc.hasValue(asMsg.svcTermAttrbDefValTxt_A1)) {
            Map<String, Object> lookupData = NSAL1310Query.getInstance().getLookupPopupData(tMsg, asMsg.svcTermAttrbDefValTxt_A1.getValue());
            if (lookupData != null) {
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermCondDataValCd_L1, (String) lookupData.get(TARGET_CD));
                ZYPEZDItemValueSetter.setValue(asMsg.svcTermAttrbDefValTxt_A1, (String) lookupData.get(TARGET_NAME));
            }
        }
    }
    // add end 2018/06/25 QC#17369
}
