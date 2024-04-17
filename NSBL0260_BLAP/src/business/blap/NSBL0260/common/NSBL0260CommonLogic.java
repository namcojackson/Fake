/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0260.common;

import static business.blap.NSBL0260.constant.NSBL0260Constant.MAX_THRU_DATE;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NZZM0003E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0163E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0164E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.NSBM0165E;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SEARCH_CRITERIA_CD_ALL;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SEARCH_CRITERIA_CD_CUST_HEADER;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SEARCH_CRITERIA_CD_MDL_AND_SER_COMB;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SEARCH_CRITERIA_CD_MDL_COMB;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SEARCH_CRITERIA_CD_MDL_HEADER;
import static business.blap.NSBL0260.constant.NSBL0260Constant.SEARCH_CRITERIA_CD_SER_COMB;
import static business.blap.NSBL0260.constant.NSBL0260Constant.TBL_DS_MDL_EOL_EX;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0260.NSBL0260CMsg;
import business.blap.NSBL0260.NSBL0260Query;
import business.blap.NSBL0260.NSBL0260SMsg;
import business.blap.NSBL0260.NSBL0260_ACMsg;
import business.blap.NSBL0260.NSBL0260_ACMsgArray;
import business.blap.NSBL0260.NSBL0260_ASMsg;
import business.db.DS_MDL_EOL_EXTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MDL_EOL_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * EOL Exception Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/07/03   Hitachi         K.Kim           Update          QC#18164
 *</pre>
 */
public class NSBL0260CommonLogic {

    /**
     * Paginate to item
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @param itemIndex int
     */
    public static void pagenation(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        setValue(cMsg.xxPageShowFromNum_A, BigDecimal.valueOf(startIndex + 1));
        setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowCurNum_A, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum_A, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {

        // NSBL0260_ACMsg -> NSBL0260_ASMsg
        NSBL0260_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSBL0260_ACMsg acMsg = (NSBL0260_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_A.getValueInt() - 1;
            EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(targetIdxNumA), null);
        }
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * getSearchCriteriaMap
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @param searchCriteriaCd String
     * @param limitNum int
     * @return Map<String, Object> SearchCriteriaMap
     */
    public static Map<String, Object> getSearchCriteriaMap(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg, String searchCriteriaCd, int limitNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("salesDate", cMsg.slsDt.getValue());
        params.put("maxThruDate", MAX_THRU_DATE);
        params.put("limitNum", limitNum);
        params.put("noNewContractsAfterDate", DS_MDL_EOL_STS.EOL_NO_CONTRACT);
        params.put("stopServiceDate", DS_MDL_EOL_STS.EOL_NO_SERVICE);
        if (hasValue(cMsg.xxChkBox_ON) && !hasValue(cMsg.xxChkBox_OF)) {
            params.put("serviceableOffFlag", ZYPConstant.FLG_ON_Y + ZYPConstant.FLG_OFF_N);
        } else if (!hasValue(cMsg.xxChkBox_ON) && hasValue(cMsg.xxChkBox_OF)) {
            params.put("serviceableOffFlag", ZYPConstant.FLG_OFF_N + ZYPConstant.FLG_ON_Y);
        }

        if (!hasValue(searchCriteriaCd)) {
            params.put("searchCriteriaCd", SEARCH_CRITERIA_CD_ALL);
            return params;
        }
        params.put("searchCriteriaCd", searchCriteriaCd);

        if (SEARCH_CRITERIA_CD_MDL_COMB.equals(searchCriteriaCd)) {
            params.put("mdlNm", cMsg.mdlNm_C.getValue());
        } else if (SEARCH_CRITERIA_CD_SER_COMB.equals(searchCriteriaCd)) {
            params.put("serNum", cMsg.serNum_C.getValue());
        } else if (SEARCH_CRITERIA_CD_MDL_AND_SER_COMB.equals(searchCriteriaCd)) {
            params.put("mdlNm", cMsg.mdlNm_C.getValue());
            params.put("serNum", cMsg.serNum_C.getValue());
        } else if (SEARCH_CRITERIA_CD_MDL_HEADER.equals(searchCriteriaCd)) {
            params.put("mdlNm", cMsg.mdlNm_H.getValue());
        } else if (SEARCH_CRITERIA_CD_CUST_HEADER.equals(searchCriteriaCd)) {
            params.put("dsAcctNum", cMsg.dsAcctNum_H.getValue());
        }
        return params;
    }

    /**
     * getSearchCriteriaCd
     * @param cMsg NSBL0260CMsg
     * @return String searchCriteriaCd
     */
    public static String getSearchCriteriaCd(NSBL0260CMsg cMsg) {
        if (hasValue(cMsg.mdlNm_C) && !hasValue(cMsg.serNum_C)) {
            return SEARCH_CRITERIA_CD_MDL_COMB;
        } else if (!hasValue(cMsg.mdlNm_C) && hasValue(cMsg.serNum_C)) {
            return SEARCH_CRITERIA_CD_SER_COMB;
        } else if (hasValue(cMsg.mdlNm_C) && hasValue(cMsg.serNum_C)) {
            return SEARCH_CRITERIA_CD_MDL_AND_SER_COMB;
        } else if (hasValue(cMsg.mdlNm_H)) {
            return SEARCH_CRITERIA_CD_MDL_HEADER;
        } else if (hasValue(cMsg.dsAcctNum_H)) {
            return SEARCH_CRITERIA_CD_CUST_HEADER;
        } else if (!hasValue(cMsg.mdlNm_C) && !hasValue(cMsg.serNum_C) && !hasValue(cMsg.mdlNm_H) && !hasValue(cMsg.dsAcctNum_H)) {
            return SEARCH_CRITERIA_CD_ALL;
        }
        return SEARCH_CRITERIA_CD_ALL;
    }


    /**
     * Set empty record.
     * @param glblCmpyCd String
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @param index int
     */
    public static final void setEmptyRecord(String glblCmpyCd, NSBL0260CMsg cMsg, NSBL0260SMsg sMsg, int index) {
        NSBL0260_ASMsg asMsg = sMsg.A.no(index);
        setValue(asMsg.xxRowNum_A, BigDecimal.valueOf(index + 1));
        sMsg.A.setValidCount(index + 1);
    }

    /**
     * getLastPageNum
     * 
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @return int
     */
    public static int getLastPageNum(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        if (sMsg.A.getValidCount() <= cMsg.A.length()) {
            return BigDecimal.ONE.intValue();
        }
        return BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP).intValue();
    }

    /**
     * insertAndUpdateDsMdlEolEx
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @return true:normal, false:error
     */
    public static boolean insertAndUpdateDsMdlEolEx(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        DS_MDL_EOL_EXTMsg dsMdlEolExForNoTMsg = null;
        DS_MDL_EOL_EXTMsg dsMdlEolExForStopTMsg = null;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // For No New Contracts After Date
            if (hasValue(sMsg.A.no(i).dsMdlEolExPk_AN)) {
                dsMdlEolExForNoTMsg = getDsMdlEolExForPk(cMsg, sMsg.A.no(i).dsMdlEolExPk_AN.getValue());
                if (dsMdlEolExForNoTMsg == null) {
                    cMsg.setMessageInfo(NSBM0163E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            } else {
                dsMdlEolExForNoTMsg = new DS_MDL_EOL_EXTMsg();
            }

            if (hasValue(sMsg.A.no(i).dsMdlEolExPk_AN)) {
                if (isChangeData(dsMdlEolExForNoTMsg, sMsg.A.no(i), true)) {
                    setDsMdlEolExTMsg(cMsg, sMsg.A.no(i), dsMdlEolExForNoTMsg, true, false);
                    // Update
                    if (!checkUpdated(dsMdlEolExForNoTMsg.ezUpTime.getValue(), dsMdlEolExForNoTMsg.ezUpTimeZone.getValue(), sMsg.A.no(i).ezUpTime_AN.getValue(), sMsg.A.no(i).ezUpTimeZone_AN.getValue())) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }

                    // Update
                    EZDTBLAccessor.update(dsMdlEolExForNoTMsg);
                }
            } else {
                // Insert
                setDsMdlEolExTMsg(cMsg, sMsg.A.no(i), dsMdlEolExForNoTMsg, true, true);
                EZDTBLAccessor.insert(dsMdlEolExForNoTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolExForNoTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSBM0164E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            }

            // For Stop Service Date
            if (hasValue(sMsg.A.no(i).dsMdlEolExPk_AS)) {
                dsMdlEolExForStopTMsg = getDsMdlEolExForPk(cMsg, sMsg.A.no(i).dsMdlEolExPk_AS.getValue());
                if (sMsg == null) {
                    cMsg.setMessageInfo(NSBM0163E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            } else {
                dsMdlEolExForStopTMsg = new DS_MDL_EOL_EXTMsg();
            }

            if (hasValue(sMsg.A.no(i).dsMdlEolExPk_AS)) {
                if (isChangeData(dsMdlEolExForStopTMsg, sMsg.A.no(i), false)) {
                    setDsMdlEolExTMsg(cMsg, sMsg.A.no(i), dsMdlEolExForStopTMsg, false, false);
                    // Update
                    if (!checkUpdated(dsMdlEolExForStopTMsg.ezUpTime.getValue(), dsMdlEolExForStopTMsg.ezUpTimeZone.getValue(), sMsg.A.no(i).ezUpTime_AS.getValue(), sMsg.A.no(i).ezUpTimeZone_AS.getValue())) {
                        cMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }

                    // Update
                    EZDTBLAccessor.update(dsMdlEolExForStopTMsg);
                }
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolExForStopTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSBM0163E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            } else {
                // Insert
                setDsMdlEolExTMsg(cMsg, sMsg.A.no(i), dsMdlEolExForStopTMsg, false, true);
                EZDTBLAccessor.insert(dsMdlEolExForStopTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolExForStopTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSBM0164E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * deleteDsMdlEolEx
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @return true:normal, false:error
     */
    public static boolean deleteDsMdlEolEx(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        int size = sMsg.D.getValidCount();
        for (int i = 0; i < size; i++) {
            if (hasValue(sMsg.D.no(i).dsMdlEolExPk_DN)) {
                // For No New Contracts After Date
                DS_MDL_EOL_EXTMsg dsMdlEolExTMsg = getDsMdlEolExForPk(cMsg, sMsg.D.no(i).dsMdlEolExPk_DN.getValue());
                if (dsMdlEolExTMsg == null) {
                    cMsg.setMessageInfo(NSBM0165E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
                if (!checkUpdated(dsMdlEolExTMsg.ezUpTime.getValue(), dsMdlEolExTMsg.ezUpTimeZone.getValue(), sMsg.D.no(i).ezUpTime_DN.getValue(), sMsg.D.no(i).ezUpTimeZone_DN.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(dsMdlEolExTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolExTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSBM0165E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            }
            if (hasValue(sMsg.D.no(i).dsMdlEolExPk_DS)) {
                // For Stop Service Date
                DS_MDL_EOL_EXTMsg dsMdlEolExTMsg = getDsMdlEolExForPk(cMsg, sMsg.D.no(i).dsMdlEolExPk_DS.getValue());
                if (dsMdlEolExTMsg == null) {
                    cMsg.setMessageInfo(NSBM0165E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }

                if (!checkUpdated(dsMdlEolExTMsg.ezUpTime.getValue(), dsMdlEolExTMsg.ezUpTimeZone.getValue(), sMsg.D.no(i).ezUpTime_DS.getValue(), sMsg.D.no(i).ezUpTimeZone_DS.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(dsMdlEolExTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolExTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSBM0165E, new String[] {TBL_DS_MDL_EOL_EX });
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isChangeData(DS_MDL_EOL_EXTMsg tMsg, NSBL0260_ASMsg dtlMsg, boolean noNewFlg) {
        if (tMsg == null || dtlMsg == null) {
            return true;
        }
        if (!tMsg.mdlId.getValue().equals(dtlMsg.mdlId_A.getValue())) {
            return true;
        }
        if (!tMsg.serNum.getValue().equals(dtlMsg.serNum_A.getValue())) {
            return true;
        }
        if (noNewFlg) {
            if (!tMsg.dsMdlEolDt.getValue().equals(dtlMsg.dsMdlEolDt_AN.getValue())) {
                return true;
            }
        } else {
            if (!tMsg.dsMdlEolDt.getValue().equals(dtlMsg.dsMdlEolDt_AS.getValue())) {
                return true;
            }
        }
        return false;
    }
    /**
     * getDsMdlEolExForPk
     * @param bizMsg NSBL0260CMsg
     * @param dsMdlEolExPk BigDecimal
     * @return DS_MDL_EOL_EXTMsg
     */
    private static DS_MDL_EOL_EXTMsg getDsMdlEolExForPk(NSBL0260CMsg cMsg, BigDecimal dsMdlEolExPk) {
        DS_MDL_EOL_EXTMsg paramTMsg = new DS_MDL_EOL_EXTMsg();
        setValue(paramTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(paramTMsg.dsMdlEolExPk, dsMdlEolExPk);
        return (DS_MDL_EOL_EXTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(paramTMsg);
    }

    private static boolean checkUpdated(String findEzUpTime, String findEzUpTimeZone, String currentEzUpTime, String currentEzUpTimeZone) {
        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return false;
        }
        return true;
    }

    private static void setDsMdlEolExTMsg(NSBL0260CMsg cMsg, NSBL0260_ASMsg dtlMsg, DS_MDL_EOL_EXTMsg dsMdlEolExTMsg, boolean noNewFlg, boolean insertFlg) {
        if (insertFlg) {
            setValue(dsMdlEolExTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            BigDecimal dsMdlEolExPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MDL_EOL_EX_SQ);
            setValue(dsMdlEolExTMsg.dsMdlEolExPk, dsMdlEolExPk);
            if (noNewFlg) {
                setValue(dsMdlEolExTMsg.dsMdlEolStsCd, DS_MDL_EOL_STS.EOL_NO_CONTRACT);
            } else {
                setValue(dsMdlEolExTMsg.dsMdlEolStsCd, DS_MDL_EOL_STS.EOL_NO_SERVICE);
            }
        }
        setValue(dsMdlEolExTMsg.mdlId, dtlMsg.mdlId_A);
        setValue(dsMdlEolExTMsg.serNum, dtlMsg.serNum_A);
        if (noNewFlg) {
            setValue(dsMdlEolExTMsg.dsMdlEolDt, dtlMsg.dsMdlEolDt_AN);
        } else {
            setValue(dsMdlEolExTMsg.dsMdlEolDt, dtlMsg.dsMdlEolDt_AS);
        }
    }
    /**
     * getCurrLine
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     * @param idx int
     * @return int
     */
    public static int getCurrLine(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg, int idx) {
        int pagenationFrom = cMsg.xxPageShowCurNum_A.getValueInt();
        int curr = idx - (pagenationFrom - 1) * cMsg.A.length();
        if (curr >= 0 && curr < cMsg.A.length()) {
            return curr;
        }
        return -1;
    }

    /**
     * copy shareMsg to bizMsg
     * @param cMsg NSBL0260CMsg
     * @param sMsg NSBL0260SMsg
     */
    public static void copyShareToBiz(NSBL0260CMsg cMsg, NSBL0260SMsg sMsg) {
        int pageFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int j = 0;
        if (sMsg.A.getValidCount() == 0) {
            cMsg.A.setValidCount(0);
            return;
        }
        for (j = 0; j < cMsg.A.length(); j++) {
            if (pageFrom + j <= sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(pageFrom + j - 1), null, cMsg.A.no(j), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(j);
    }
    /**
     * isCheckExistMdlNm
     * @param glblCmpyCd String
     * @param asMsg NSBL0260_ASMsg
     * @return boolean
     */
    public static  boolean isCheckExistMdlNm(String glblCmpyCd, NSBL0260_ASMsg asMsg) {
        if (!hasValue(glblCmpyCd) || asMsg == null) {
            return false;
        }
        MDL_NMTMsg inMsg = new MDL_NMTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("t_MdlNm01", asMsg.mdlNm_A.getValue());
        MDL_NMTMsgArray list = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            MDL_NMTMsg tMsg = (MDL_NMTMsg) list.get(0);
            if (tMsg != null) {
                setValue(asMsg.mdlId_A, tMsg.t_MdlId);
                return true;
            }
        }
        return false;
    }

    /**
     * isCheckExistSvcMachMstr
     * @param glblCmpyCd String
     * @param asMsg NSBL0260_ASMsg
     * @return boolean
     */
    public static boolean isCheckExistSvcMachMstr(String glblCmpyCd, NSBL0260_ASMsg asMsg) {
        if (!hasValue(glblCmpyCd) || asMsg == null) {
            return false;
        }
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", asMsg.serNum_A.getValue());
        SVC_MACH_MSTRTMsgArray list = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) list.get(0);
            if (tMsg != null) {
                return true;
            }
        }
        return false;
    }

    // START 2017/07/03 K.Kim [QC#18164, ADD]
    /**
     * isCheckExistMdlNmSvcMachMstr
     * @param glblCmpyCd String
     * @param asMsg NSBL0260_ASMsg
     * @return boolean
     */
    public static boolean isCheckExistMdlNmSvcMachMstr(String glblCmpyCd, NSBL0260_ASMsg asMsg) {
        if (!hasValue(glblCmpyCd) || asMsg == null) {
            return false;
        }
        String mdlNm = asMsg.mdlNm_A.getValue();
        String serNum = asMsg.serNum_A.getValue();
        return NSBL0260Query.getInstance().existMdlNmSvcMachMstr(glblCmpyCd, mdlNm, serNum);
    }
    // END 2017/07/03 K.Kim [QC#18164, ADD]
}
