package business.blap.NSAL1170.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL1170.constant.NSAL1170Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21Text;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1170.NSAL1170CMsg;
import business.blap.NSAL1170.NSAL1170Query;
import business.blap.NSAL1170.NSAL1170SMsg;
import business.blap.NSAL1170.NSAL1170_ACMsg;
import business.blap.NSAL1170.NSAL1170_ACMsgArray;
import business.blap.NSAL1170.NSAL1170_ASMsg;
import business.blap.NSAL1170.NSAL1170_ASMsgArray;
import business.db.DS_MDL_ESCL_BLLG_MTRTMsg;
import business.db.DS_MDL_ESCL_RULETMsg;

/**
 *<pre>
 * Model Escalation Rules Maintenance Popup.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27    Hitachi         T.Nishimura    Create          N/A
 * 2016/02/05    Hitachi         T.Nishimura    Update          QC#4121
 * 2016/12/13    Hitachi         T.Mizuki       Update          QC#16369
 * 2018/03/16    CITS            M.Naito        Update          QC#21671
 *</pre>
 */
public class NSAL1170CommonLogic {

    // paging methods

    /**
     * preSetToPageOne.
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }

    /**
     * copyGlblMsgToBizMsg.
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++, ixS++) {
            EZDMsg.copy(sMsg.A.no(ixS), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);

    }

    /**
     * copy To SMsg for Current Page Info
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     */
    public static void copyCurrentPageToSMsgA(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        // NSAL1170_ACMsg -> NSAL1170_BSMsg
        NSAL1170_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL1170_ACMsg acMsg = (NSAL1170_ACMsg) acMsgArray.no(i);
            int targetIdxNumA = acMsg.xxRowNum_T.getValueInt() - 1;

            NSAL1170_ASMsg asMsg = sMsg.A.no(targetIdxNumA);
            EZDMsg.copy(acMsg, null, asMsg, null);
        }

    }

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {
        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);

    }

    /**
     * get Search Data. 
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     */
    public static void getSearchData(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.X);
        Map<String, Object> sc = createSearchCondition(cMsg, sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NSAL1170Query.getInstance().getSearchData(sc, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.A.length()), Integer.toString(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            } else {
                cMsg.setMessageInfo(NZZM0002I, new String[] {Integer.toString(sMsg.A.length()) });
            }

            // Copy one page from SMsg to CMsg
            cMsg.A.clear();
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
            setValue(cMsg.xxPageShowOfNum, new BigDecimal(queryResCnt));
        }
    }

    private static Map<String, Object> createSearchCondition(NSAL1170CMsg cMsg, int i) {
        Map<String, Object> sc = new HashMap<String, Object>();
        sc.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        sc.put("slsDt", cMsg.slsDt.getValue());
        sc.put("thruDtLimit", THRU_DT_LIMIT);
        sc.put("mdlId", cMsg.t_MdlId.getValue());
        sc.put("rowNum", i);
        return sc;
    }

    /**
     * get add row data.
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     */
    public static void getAddRowData(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        Map<String, Object> sc = createSearchCondition(cMsg, sMsg.Y.length() + 1);
        NSAL1170Query.getInstance().getAddRowData(sc, sMsg);

    }

    /**
     * insert rows.
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     */
    public static void insertRow(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        int count = sMsg.A.getValidCount();
        int addRowCount = sMsg.Y.getValidCount();

        // mod start 2016/12/13 CSA QC#16369
        if (addRowCount == 0) {
            addRowCount++;
        }
        // mod end 2016/12/13 CSA QC#16369
        if (sMsg.A.length() < count + addRowCount) {
            cMsg.setMessageInfo(NSAM0112E);
            return;
        }

        NSAL1170_ASMsgArray tempASMsg = sMsg.A;
        for (int i = 0; i < count; i++) {
            setValue(tempASMsg.no(i).xxRowNum, tempASMsg.no(i).xxRowNum.getValue().add(BigDecimal.ONE));
            setValue(tempASMsg.no(i).xxRowNum_T, tempASMsg.no(i).xxRowNum_T.getValue().add(BigDecimal.valueOf(addRowCount)));
        }

        tempASMsg.setValidCount(addRowCount + count);

        for (int i = 0; i < addRowCount; i++) {
            EZDMsg.copy(sMsg.Y.no(i), "I", tempASMsg.no(i + count), "");
            NSAL1170_ASMsg addRowData = tempASMsg.no(i + count);
            setValue(addRowData.xxRowNum, BigDecimal.ONE);
            setValue(addRowData.xxRowNum_T, BigDecimal.valueOf(i + 1));
            setValue(addRowData.xxRowNum_D, BigDecimal.valueOf(i + 1));
            setValue(addRowData.mtrReqMdlFlg, sMsg.Y.no(i).mtrReqMdlFlg_I);
            addRowData.dsMdlEsclRulePk.clear();
            addRowData.uplftBasePrcUpRatio.clear();
            addRowData.dsMdlEsclBllgMtrPk.clear();
            addRowData.uplftMtrPrcUpRatio.clear();
            addRowData.effFromDt.clear();
            addRowData.effThruDt.clear();
            addRowData.ezUpTime.clear();
            addRowData.ezUpTimeZone.clear();
            addRowData.ezUpTime_D.clear();
            addRowData.ezUpTimeZone_D.clear();
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.clear();
    }

    /**
     * delete rows. 
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     */
    public static void deleteRow(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        int deleteRows = 0;
        BigDecimal deleteRowPos = cMsg.xxRadioBtn.getValue();
        BigDecimal deleteRowNum = cMsg.A.no(deleteRowPos.intValue()).xxRowNum.getValue();
        BigDecimal deleteRowTotalNum = cMsg.A.no(deleteRowPos.intValue()).xxRowNum_T.getValue();
        for (int i = deleteRowTotalNum.intValue() - 1; i < sMsg.A.getValidCount(); i++) {
            NSAL1170_ASMsg row = sMsg.A.no(i);
            if (row.xxRowNum.getValue().equals(deleteRowNum)) {
                deleteRows++;
                if (hasValue(row.dsMdlEsclBllgMtrPk)) {
                    int i2 = sMsg.X.getValidCount();
                    sMsg.X.setValidCount(i2 + 1);

                    EZDMsg.copy(row, "", sMsg.X.no(i2), "X");
                    setValue(sMsg.X.no(i2).ezUpTime_XD, row.ezUpTime_D);
                    setValue(sMsg.X.no(i2).ezUpTimeZone_XD, row.ezUpTimeZone_D);
                } else if (hasValue(row.dsMdlEsclRulePk)) {
                    int i2 = sMsg.X.getValidCount();
                    sMsg.X.setValidCount(i2 + 1);
                    EZDMsg.copy(row, "", sMsg.X.no(i2), "X");
                }
            } else {
                row.xxRowNum.setValue(row.xxRowNum.getValue().subtract(BigDecimal.ONE));
                row.xxRowNum_T.setValue(row.xxRowNum_T.getValueInt() - deleteRows);
                EZDMsg.copy(row, null, sMsg.A.no(i - deleteRows), null);
            }
        }
        sMsg.A.setValidCount(sMsg.A.getValidCount() - deleteRows);
    }

    // validation check methods
    /**
     * validationCheck. 
     * @param cMsg NSAL1170CMsg
     * @param sMsg NSAL1170SMsg
     * @return boolean
     */
    public static boolean validationCheck(NSAL1170CMsg cMsg, NSAL1170SMsg sMsg) {
        boolean checkResult = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL1170_ACMsg checkRecord1 = cMsg.A.no(i);
            if (hasValue(checkRecord1.mtrLbDescTxt)) {
                if (!hasValue(checkRecord1.uplftMtrPrcUpRatio)) {
                    // mod start 2016/03/23 CSA Defect#5077
                    checkRecord1.uplftMtrPrcUpRatio.setErrorInfo(1, "NSBM0036E", new String[] {"Billing Counter Percentage"});
                    // mod end 2016/03/23 CSA Defect#5077
                    checkResult = false;
                    continue;
                }
            }
            if (!BigDecimal.ONE.equals(checkRecord1.xxRowNum_D.getValue())) {
                continue;
            }
            String fromDt1 = checkRecord1.effFromDt.getValue();
            String thruDt1 = checkRecord1.effThruDt.getValue();
            if (hasValue(checkRecord1.effThruDt) && ZYPDateUtil.compare(fromDt1, fromDt1) > 0) {
                checkRecord1.effFromDt.setErrorInfo(1, "NSAM0323E", new String[] {"Start Date", "End Date" });
                checkRecord1.effThruDt.setErrorInfo(1, "NSAM0323E", new String[] {"Start Date", "End Date" });

                checkResult = false;
                continue;
            }
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                NSAL1170_ASMsg checkRecord2 = sMsg.A.no(j);
                if (checkRecord1.xxRowNum_T.getValue().equals(checkRecord2.xxRowNum_T.getValue())) {
                    continue;
                }
                if (!BigDecimal.ONE.equals(checkRecord2.xxRowNum_D.getValue())) {
                    continue;
                }
                String fromDt2 = checkRecord2.effFromDt.getValue();
                String thruDt2 = checkRecord2.effThruDt.getValue();
                if (!checkOverlapingPeriod(fromDt1, thruDt1, fromDt2, thruDt2)) {
                    checkRecord1.effFromDt.setErrorInfo(1, "NSBM0030E");
                    checkRecord1.effThruDt.setErrorInfo(1, "NSBM0030E");
                    checkResult = false;
                    continue;
                }
            }
            if (!periodModelMeterValidCheck(sMsg, checkRecord1)) {
                checkResult = false;
            }
            // START 2018/03/16 M.Naito [QC#21671, DEL]
//            if (!periodBillingMeterMappingValidCheck(sMsg, checkRecord1)) {
//                checkResult = false;
//            }
            // END 2018/03/16 M.Naito [QC#21671, DEL]
        }
        return checkResult;
    }

    // START 2018/03/16 M.Naito [QC#21671, DEL]
//    private static boolean periodBillingMeterMappingValidCheck(NSAL1170SMsg sMsg, NSAL1170_ACMsg aCMsg) {
//        for (int i = 0; i < sMsg.Y.getValidCount(); i++) {
//            if (!checkIncludedRecodePeriod(aCMsg.effFromDt.getValue(), aCMsg.effThruDt.getValue(), sMsg.Y.no(i).effFromDt_BM.getValue(), sMsg.Y.no(i).effThruDt_BM.getValue())) {
//                aCMsg.effFromDt.setErrorInfo(1, "NSAM0444E");
//                aCMsg.effThruDt.setErrorInfo(1, "NSAM0444E");
//                return false;
//            }
//        }
//        return true;
//    }
    // END 2018/03/16 M.Naito [QC#21671, DEL]

    private static boolean periodModelMeterValidCheck(NSAL1170SMsg sMsg, NSAL1170_ACMsg aCMsg) {
        boolean checkRsult = true;
        for (int i = 0; i < sMsg.Y.getValidCount(); i++) {
            // START 2018/03/16 M.Naito [QC#21671, MOD]
            String dsMdlMtrEffFromDt = sMsg.Y.no(i).effFromDt_DM.getValue();
            String dsMdlMtrEffThruDt = sMsg.Y.no(i).effThruDt_DM.getValue();
//            if (!checkIncludedRecodePeriod(aCMsg.effFromDt.getValue(), aCMsg.effThruDt.getValue(), sMsg.Y.no(i).effFromDt_DM.getValue(), sMsg.Y.no(i).effThruDt_DM.getValue())) {
            if (!checkIncludedRecodePeriod(aCMsg.effFromDt.getValue(), aCMsg.effThruDt.getValue(), dsMdlMtrEffFromDt, dsMdlMtrEffThruDt)) {
//                aCMsg.effFromDt.setErrorInfo(1, "NSAM0443E");
//                aCMsg.effThruDt.setErrorInfo(1, "NSAM0443E");
                aCMsg.effFromDt.setErrorInfo(1, "NSAM0443E", new String[] {dsMdlMtrEffFromDt, dsMdlMtrEffThruDt });
                aCMsg.effThruDt.setErrorInfo(1, "NSAM0443E", new String[] {dsMdlMtrEffFromDt, dsMdlMtrEffThruDt });
            // END 2018/03/16 M.Naito [QC#21671, MOD]
                checkRsult = false;
            }
        }
        return checkRsult;
    }

    private static boolean checkIncludedRecodePeriod(String fromDt1, String thruDt1, String fromDt2, String thruDt2) {
        if (fromDt2.compareTo(fromDt1) > 0) {
            return false;
        }
        if (!S21Text.isNullOrEmpty(thruDt1)) {
            if (fromDt2.compareTo(thruDt1) > 0) {
                return false;
            }
            if (!S21Text.isNullOrEmpty(thruDt2)) {
                if (thruDt1.compareTo(thruDt2) > 0) {
                    return false;
                }
            }
        } else if (!S21Text.isNullOrEmpty(thruDt2)) {
            return false;
        }
        return true;
    }

    //
    // private static void
    // setInnerPeriodValidationErrorInfo(NSAL1170_ASMsg checkRecord1,
    // NSAL1170_ASMsg checkRecord2) {
    // checkRecord1.effFromDt.setErrorInfo(1, "NSBM0030E");
    // checkRecord1.effThruDt.setErrorInfo(1, "NSBM0030E");
    // if (checkRecord2 != null) {
    // checkRecord2.effFromDt.setErrorInfo(1, "NSBM0030E");
    // checkRecord2.effThruDt.setErrorInfo(1, "NSBM0030E");
    // }
    // }

    private static boolean checkOverlapingPeriod(String fromDt1, String thruDt1, String fromDt2, String thruDt2) {
        if (S21Text.isNullOrEmpty(thruDt1)) {
            if (S21Text.isNullOrEmpty(thruDt2)) {
                return false;
            }
            if (fromDt1.compareTo(thruDt2) <= 0) {
                return false;
            }
        } else if (S21Text.isNullOrEmpty(thruDt2)) {
            if (fromDt2.compareTo(thruDt1) <= 0) {
                return false;
            }
        } else {
            if ((fromDt2.compareTo(fromDt1) >= 0) && (thruDt2.compareTo(fromDt1)) <= 0) {
                return false;
            }
            if ((fromDt2.compareTo(fromDt1) <= 0) && (thruDt2.compareTo(fromDt1)) >= 0) {
                return false;
            }
            if ((fromDt2.compareTo(thruDt1) >= 0) && (thruDt2.compareTo(thruDt1)) <= 0) {
                return false;
            }
            if ((fromDt2.compareTo(thruDt1) <= 0) && (thruDt2.compareTo(thruDt1)) >= 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * insert record into DS_MDL_ESCL_RULE table.
     * @param msg NSAL1170SMsg
     * @param cMsg NSAL1170CMsg
     * @param aSMsg NSAL1170_ASMsg
     * @param dsMdlEsclRulePk BigDecimal
     */
    public static void insertDsMdlEsclRule(NSAL1170SMsg msg, NSAL1170CMsg cMsg, NSAL1170_ASMsg aSMsg, BigDecimal dsMdlEsclRulePk) {
        DS_MDL_ESCL_RULETMsg tMsg = new DS_MDL_ESCL_RULETMsg(); // GLBL_CMPY_CD
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        // DS_MDL_ESCL_RULE_PK
        setValue(tMsg.dsMdlEsclRulePk, dsMdlEsclRulePk);
        setValue(tMsg.mdlId, cMsg.t_MdlId);
        setValue(tMsg.uplftBasePrcUpRatio, aSMsg.uplftBasePrcUpRatio);
        setValue(tMsg.effFromDt, aSMsg.effFromDt);
        setValue(tMsg.effThruDt, aSMsg.effThruDt);

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_MDL_ESCL_RULE" });
            return;
        }
    }

    /**
     * insert record into DS_MDL_ESCL_BLLG_MTR table.
     * @param msg NSAL1170SMsg
     * @param cMsg NSAL1170CMsg
     * @param aSMsg NSAL1170_ASMsg
     * @param dsMdlEsclRulePk BigDecimal
     */
    public static void insertDsMdlEsclBllgMtr(NSAL1170SMsg msg, NSAL1170CMsg cMsg, NSAL1170_ASMsg aSMsg, BigDecimal dsMdlEsclRulePk) {
        DS_MDL_ESCL_BLLG_MTRTMsg tMsg = new DS_MDL_ESCL_BLLG_MTRTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        // DS_MDL_ESCL_BLLG_MTR_PK
        setValue(tMsg.dsMdlEsclBllgMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_MDL_ESCL_BLLG_MTR_SQ));
        setValue(tMsg.dsMdlEsclRulePk, dsMdlEsclRulePk);
        setValue(tMsg.bllgMtrLbCd, aSMsg.bllgMtrLbCd);
        setValue(tMsg.uplftMtrPrcUpRatio, aSMsg.uplftMtrPrcUpRatio);
        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_MDL_ESCL_BLLG_MTR_PK" });
            return;
        }
    }

    /**
     * get getDsMdlEsclRule By Primary Key
     * @param glblCmpyCd String
     * @param dsMdlEsclRulePk BigDecimal
     * @return DS_MDL_ESCL_RULETMsg
     */
    public static DS_MDL_ESCL_RULETMsg getDsMdlEsclRule(String glblCmpyCd, BigDecimal dsMdlEsclRulePk) {
        DS_MDL_ESCL_RULETMsg prmTMsg = new DS_MDL_ESCL_RULETMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsMdlEsclRulePk, dsMdlEsclRulePk);
        return (DS_MDL_ESCL_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * get sMdlEsclBllgMtr By Primary Key
     * @param glblCmpyCd String
     * @param dsMdlEsclRulePk BigDecimal
     * @param dsMdlEsclBllgMtrPk BigDecimal
     * @return DS_MDL_ESCL_BLLG_MTRTMsg
     */
    public static DS_MDL_ESCL_BLLG_MTRTMsg getDsMdlEsclBllgMtr(String glblCmpyCd, BigDecimal dsMdlEsclRulePk, BigDecimal dsMdlEsclBllgMtrPk) {
        DS_MDL_ESCL_BLLG_MTRTMsg prmTMsg = new DS_MDL_ESCL_BLLG_MTRTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsMdlEsclRulePk, dsMdlEsclRulePk);
        setValue(prmTMsg.dsMdlEsclBllgMtrPk, dsMdlEsclBllgMtrPk);
        return (DS_MDL_ESCL_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

}
