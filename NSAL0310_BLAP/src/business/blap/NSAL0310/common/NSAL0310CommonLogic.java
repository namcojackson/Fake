/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0310.common;

import static business.blap.NSAL0310.constant.NSAL0310Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NSAL0310.NSAL0310CMsg;
import business.blap.NSAL0310.NSAL0310Query;
import business.blap.NSAL0310.NSAL0310SMsg;
import business.blap.NSAL0310.NSAL0310_ACMsgArray;
import business.blap.NSAL0310.NSAL0310_ASMsg;
import business.db.DS_CONTRTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#3017
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC#4086
 * 2016/03/03   Hitachi         T.Tomita        Update          CSA Defect#1720
 * 2016/05/24   Hitachi         T.Mizuki        Update          QC#447
 * 2017/03/01   Hitachi         N.Arai          Update          QC#17620
 * 2017/09/04   Hitachi         U.Kim           Update          QC#20856
 * 2017/11/16   Hitachi         M.Kidokoro      Update          QC#22294
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 * 2023/04/24   Hitachi         K.Watanabe      Update          QC#61146
 *</pre>
 */
public class NSAL0310CommonLogic {

    // START 2015/10/08 T.Tomita [N/A, DEL]
//    public static boolean isValidPopupParameter(NSAL0310CMsg cMsg) {
//        boolean valid = true;
//
//        if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum)) {
//            cMsg.setMessageInfo(NSAL0310Constant.NSAM0131E, new String[] {"Direct Sales Account Number" });
//            valid = false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(cMsg.contrEffFromDt)) {
//            cMsg.setMessageInfo(NSAL0310Constant.NSAM0131E, new String[] {"Contract Effective From Date" });
//            valid = false;
//        }
//
//        if (!ZYPCommonFunc.hasValue(cMsg.contrEffThruDt)) {
//            cMsg.setMessageInfo(NSAL0310Constant.NSAM0131E, new String[] {"Contract Effective Thru Date" });
//            valid = false;
//        }
//
//        return valid;
//    }
    // END 2015/10/08 T.Tomita [N/A, DEL]

    /**
     * Is Valid Machines
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @return boolean
     */
    public static boolean isValidMachines(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd) {
        boolean valid = true;

        // START 2017/03/01 N.Arai [QC#17620, MOD]
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        Map<BigDecimal, Object> cnfgKeyMap = new HashMap<BigDecimal, Object>();
//        Map<BigDecimal, Object> expdMap = new HashMap<BigDecimal, Object>();
        Map<BigDecimal, HashMap<BigDecimal, Object>> cnfgKeyMap = new HashMap<BigDecimal, HashMap<BigDecimal, Object>>();
        Map<BigDecimal, HashMap<BigDecimal, Object>> expdMap = new HashMap<BigDecimal, HashMap<BigDecimal, Object>>();
        // END   2022/01/21 R.Cabral [QC#59502, MOD]

//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg, cnfgKeyMap, expdMap)) {
            valid = false;
        }

//        if (!NSAL0310CommonLogic.isValidMachineSelection(cMsg, sMsg)) {
//            valid = false;
//        }
        // Start 2016/05/24 T.Mizuki
//        if (!NSAL0310CommonLogic.isValidMachineEOL(cMsg, sMsg, glblCmpyCd)) {
        if (!NSAL0310CommonLogic.isValidMachineEOL(cMsg, sMsg, glblCmpyCd, cnfgKeyMap, expdMap)) {
            valid = false;
        }
        // START 2017/09/04 U.Kim [QC#20856, ADD]
        if (!NSAL0310CommonLogic.checkMainMachineForAccessory(cMsg, sMsg, glblCmpyCd, expdMap)) {
            valid = false;
        }
        // END 2017/09/04 U.Kim [QC#20856, ADD]
        // End 2016/05/24 T.Mizuki
        if (expdMap.isEmpty()) {
            return true;
        }
        BigDecimal svcConfigMstPk;
        Boolean hasAccessory;
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
        BigDecimal rowDsContrDtlPk;
        BigDecimal rowPrntDsContrDtlPk;
        BigDecimal dsContrDtlPk;
        // END   2022/01/21 R.Cabral [QC#59502, ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            svcConfigMstPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            rowDsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            rowPrntDsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            boolean rowIsMachine = SVC_MACH_TP.MACHINE.equals(sMsg.A.no(i).svcMachTpCd_A.getValue());
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
            if (!expdMap.containsKey(svcConfigMstPk)) {
                continue;
            }
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            hasAccessory = (Boolean) expdMap.get(svcConfigMstPk);
            HashMap<BigDecimal, Object> innerMap = (HashMap<BigDecimal, Object>) expdMap.get(svcConfigMstPk);
            if (rowIsMachine) {
                dsContrDtlPk = rowDsContrDtlPk;
            } else {
                dsContrDtlPk = rowPrntDsContrDtlPk;
            }
            if (!innerMap.containsKey(dsContrDtlPk)) {
                continue;
            }
            hasAccessory = (Boolean) innerMap.get(dsContrDtlPk);
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            if (hasAccessory) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtlStsCd_A, DTL_STS_XPND);
            }
        }
        // END 2017/03/01 N.Arai [QC#17620, MOD]

        return valid;
    }

    /**
     * Is Valid Effective Date
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @param cnfgMap Map<BigDecimal, Object>
     * @param exMap Map<BigDecimal, Object>
     * @return boolean
     */
    // START 2017/03/01 N.Arai [QC#17620, MOD]
//    public static boolean isValidEffectiveDate(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    public static boolean isValidEffectiveDate(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg,  Map<BigDecimal, Object> cnfgMap, Map<BigDecimal, Object> exMap) {
    public static boolean isValidEffectiveDate(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg,  Map<BigDecimal, HashMap<BigDecimal, Object>> cnfgMap, Map<BigDecimal, HashMap<BigDecimal, Object>> exMap) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]
        boolean valid = true;
        // START 2015/10/08 T.Tomita [N/A, MOD]
//        String contrEffFromDt = cMsg.contrEffFromDt.getValue();
//        String contrEffThruDt = cMsg.contrEffThruDt.getValue();
//        String contrEffFromDt = cMsg.contrVrsnEffFromDt_H.getValue();
//        String contrEffThruDt = cMsg.contrVrsnEffThruDt_H.getValue();
        String contrEffFromDt = sMsg.contrVrsnEffFromDt_H.getValue();
        String contrEffThruDt = sMsg.contrVrsnEffThruDt_H.getValue();
        BigDecimal svcConfigMstrPk;
        // END 2015/10/08 T.Tomita [N/A, MOD]
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // mod start 2016/03/03 CSA Defect#1720
//            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).xxChkBox_AM) || !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_AM.getValue())) {
//                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).xxChkBox_AA) || !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_AA.getValue())) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_AM) || !ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_AM.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxChkBox_AA) || !ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_AA.getValue())) {
                    continue;
                }
            }
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            BigDecimal dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                // Do not check date values for items that have already been registered to the contract
                continue;
            }
            if (SVC_MACH_TP.ACCESSORY.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())) {
                dsContrDtlPk = sMsg.A.no(i).prntDsContrDtlPk_A.getValue();
            }
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
            svcConfigMstrPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            setKey(cnfgMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
            setKey(cnfgMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
            // END   2022/01/21 R.Cabral [QC#59502, MOD]

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffFromDt_A)) {
                sMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, ZZM9000E, new String[] {"Effective From Date"});
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                valid = false;
            }

//            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrEffFromDt_A) && !ZYPCommonFunc.hasValue(cMsg.A.no(i).contrEffThruDt_A)) {
//                cMsg.A.no(i).contrEffThruDt_A.setErrorInfo(1, NSAM0066E, new String[] {"Effective From Date", "Effective Through Date" });
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffFromDt_A) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffThruDt_A)) {
                sMsg.A.no(i).contrEffThruDt_A.setErrorInfo(1, NSAM0066E, new String[] {"Effective From Date", "Effective Through Date" });
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                valid = false;
            }
//            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).contrEffFromDt_A) && ZYPCommonFunc.hasValue(cMsg.A.no(i).contrEffThruDt_A)) {
//                cMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, NSAM0066E, new String[] {"Effective Through Date", "Effective From Date" });
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffFromDt_A) && ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffThruDt_A)) {
                sMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, NSAM0066E, new String[] {"Effective Through Date", "Effective From Date" });
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                valid = false;
            }
//            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).contrEffFromDt_A) && ZYPCommonFunc.hasValue(cMsg.A.no(i).contrEffThruDt_A)) {
//                String effFromDt = cMsg.A.no(i).contrEffFromDt_A.getValue();
//                String effThruDt = cMsg.A.no(i).contrEffThruDt_A.getValue();
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffFromDt_A) && ZYPCommonFunc.hasValue(sMsg.A.no(i).contrEffThruDt_A)) {
                String effFromDt = sMsg.A.no(i).contrEffFromDt_A.getValue();
                String effThruDt = sMsg.A.no(i).contrEffThruDt_A.getValue();
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
//                    cMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, NSAM0347E, new String[] {"Effective From Date", "Effective Through Date" });
                    sMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, NSAM0347E, new String[] {"Effective From Date", "Effective Through Date" });
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    valid = false;
                }
                if (ZYPDateUtil.compare(contrEffFromDt, effFromDt) > 0) {
//                    cMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, NSAM0346E, new String[] {"Effective From Date", "Contract From Date" });
                    sMsg.A.no(i).contrEffFromDt_A.setErrorInfo(1, NSAM0346E, new String[] {"Effective From Date", "Contract From Date" });
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    valid = false;
                }
                if (ZYPDateUtil.compare(contrEffThruDt, effThruDt) < 0) {
//                    cMsg.A.no(i).contrEffThruDt_A.setErrorInfo(1, NSAM0347E, new String[] {"Effective Through Date", "Contract Through Date" });
                    sMsg.A.no(i).contrEffThruDt_A.setErrorInfo(1, NSAM0347E, new String[] {"Effective Through Date", "Contract Through Date" });
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    valid = false;
                }
            }
            // mod end 2016/03/03 CSA Defect#1720]
        }
        return valid;
    }
    // END 2017/03/01 N.Arai [QC#17620, MOD]

    // START 2017/03/01 N.Arai [QC#17620, MOD]
//    private static boolean isValidMachineSelection(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    public static boolean isValidMachineSelection(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    // END 2017/03/01 N.Arai [QC#17620, MOD]

        List<Integer> machList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_AM", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> asryList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_AA", ZYPConstant.CHKBOX_ON_Y);
        if (machList.isEmpty() && asryList.isEmpty()) {
            cMsg.setMessageInfo(NSAM0310E);
            return false;
        }

        if (machList.size() + asryList.size() > cMsg.P.length()) {
            cMsg.setMessageInfo(NSAM0312E, new String[] {"" + cMsg.P.length() });
            return false;
        }

        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//      return true;
        boolean hasValidSelection = false;
        List<Integer> selectedList = new ArrayList<Integer>();
        selectedList.addAll(machList);
        selectedList.addAll(asryList);

        for (int idx = 0; idx < selectedList.size(); idx++) {
            if (sMsg.A.no(selectedList.get(idx)).dsContrDtlPk_A.getValue() == null) {
                // At least 1 valid selected item
                hasValidSelection = true;
                break;
            }
        }
        if (!hasValidSelection) {
            cMsg.setMessageInfo(NSAM0310E);
        }

        return hasValidSelection;
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
    }

    // START 2017/03/01 N.Arai [QC#17620, MOD]
    // Start 2016/05/24 T.Mizuki
//    private static boolean isValidMachineEOL(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd) {
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    private static boolean isValidMachineEOL(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd,  Map<BigDecimal, Object> cnfgMap, Map<BigDecimal, Object> exMap) {
    private static boolean isValidMachineEOL(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd,  Map<BigDecimal, HashMap<BigDecimal, Object>> cnfgMap, Map<BigDecimal, HashMap<BigDecimal, Object>> exMap) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]

//        EndOfLifeBean infoBean = new EndOfLifeBean();
        Boolean chkEOL = false;
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//            String chkMach = sMsg.A.no(i).xxChkBox_AM.getValue();
//            if (ZYPConstant.FLG_ON_Y.equals(chkMach)) {
//                infoBean.setGlblCmpyCd(glblCmpyCd);
//                infoBean.setSvcMachMstrPk(sMsg.A.no(i).svcMachMstrPk_A.getValue());
//                infoBean.setEolDt(sMsg.A.no(i).contrEffFromDt_A.getValue());
//                if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
//                    cMsg.A.no(i).serNum_A.setErrorInfo(1, infoBean.getXxMsgId(), new String[] {});
//                    chkEOL = true;
//                } else {
//                    if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
//                        cMsg.A.no(i).serNum_A.setErrorInfo(1, NSAM0478E, new String[] {cMsg.A.no(i).serNum_A.getValue() });
//                        chkEOL = true;
//                    }
//                }
//            }
//        }
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        Map<BigDecimal, Object> callApiMap = new HashMap<BigDecimal, Object>();
        Map<BigDecimal, HashMap<BigDecimal, Object>> callApiMap = new HashMap<BigDecimal, HashMap<BigDecimal, Object>>();
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
        BigDecimal svcConfigMstrPk;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            svcConfigMstrPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            if (!cnfgMap.containsKey(svcConfigMstrPk)) {
//                continue;
//            }
            BigDecimal dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                // Do not check date values for items that have already been registered to the contract
                continue;
            }
            if (SVC_MACH_TP.ACCESSORY.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())) {
                // Substitute DS_CONTR_DTL_PK value with the PRNT_DS_CONTR_DTL_PK for setKey processing
                dsContrDtlPk = sMsg.A.no(i).prntDsContrDtlPk_A.getValue();
            }
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            if (!cnfgMap.containsKey(svcConfigMstrPk)) {
                continue;
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            }
            } else {
                HashMap<BigDecimal, Object> innerMap = cnfgMap.get(svcConfigMstrPk);
                if (!innerMap.containsKey(dsContrDtlPk)) {
                    continue;
                }
            }
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            if (SVC_MACH_TP.MACHINE.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())) {
                if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_AM.getValue())) {
                    callEndOfLifeInfoAPI(glblCmpyCd, sMsg.A.no(i), callApiMap, false);
                } else {
                    if (!callEndOfLifeInfoAPI(glblCmpyCd, sMsg.A.no(i), callApiMap, true)) {
                        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                        setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                        setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                        // END   2022/01/21 R.Cabral [QC#59502, MOD]
                        chkEOL = true;
                    }
                }
                continue;
            }
            if (SVC_MACH_TP.ACCESSORY.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())) {
                if (!ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_AA.getValue())) {
                    continue;
                }
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                if (callApiMap.containsKey(svcConfigMstrPk)) {
                HashMap<BigDecimal, Object> innerCallApiMap = callApiMap.get(svcConfigMstrPk);
                if (callApiMap.containsKey(svcConfigMstrPk) && innerCallApiMap.containsKey(dsContrDtlPk)) {
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    sMsg.A.no(i).serNum_A.setErrorInfo(1, NSAM0478E, new String[] {sMsg.A.no(i).serNum_A.getValue() });
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    chkEOL = true;
                } else {
                    if (!callEndOfLifeInfoAPI(glblCmpyCd, sMsg.A.no(i), callApiMap, true)) {
                        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                        setKey(exMap, svcConfigMstrPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                        setKey(exMap, svcConfigMstrPk, dsContrDtlPk, sMsg.A.no(i).svcMachTpCd_A.getValue());
                        // END   2022/01/21 R.Cabral [QC#59502, MOD]
                        chkEOL = true;
                    }
                }
            }
        }
        if (chkEOL) {
            return false;
        }
        return true;
    }
    // End 2016/05/24 T.Mizuki
    // END 2017/03/01 N.Arai [QC#17620, MOD]

    // START 2017/03/01 N.Arai [QC#17620, MOD]
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    private static boolean callEndOfLifeInfoAPI(String glblCmpyCd, NSAL0310_ASMsg asMsg, Map<BigDecimal, Object> callMap, boolean target) {
    private static boolean callEndOfLifeInfoAPI(String glblCmpyCd, NSAL0310_ASMsg asMsg, Map<BigDecimal, HashMap<BigDecimal, Object>> callMap, boolean target) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]
        EndOfLifeBean infoBean = new EndOfLifeBean();
        infoBean.setGlblCmpyCd(glblCmpyCd);
        infoBean.setSvcMachMstrPk(asMsg.svcMachMstrPk_A.getValue());
        infoBean.setEolDt(asMsg.contrEffFromDt_A.getValue());
        BigDecimal svcConfigMstrPk = asMsg.svcConfigMstrPk_A.getValue();
        if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
            if (!target) {
                return true;
            }
            asMsg.serNum_A.setErrorInfo(1, infoBean.getXxMsgId(), new String[] {});
            return false;
        } else {
            if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
                if (SVC_MACH_TP.MACHINE.equals(asMsg.svcMachTpCd_A.getValue())) {
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    setKey(callMap, svcConfigMstrPk, asMsg.svcMachTpCd_A.getValue());
                    setKey(callMap, svcConfigMstrPk, asMsg.dsContrDtlPk_A.getValue(), asMsg.svcMachTpCd_A.getValue());
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                }
                if (!target) {
                    return true;
                }
                asMsg.serNum_A.setErrorInfo(1, NSAM0478E, new String[] {asMsg.serNum_A.getValue() });
                return false;
            }
        }
        return true;
    }
    // END 2017/03/01 N.Arai [QC#17620, MOD]

    /**
     * Paginate Table A
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     */
    public static void copyTableA(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        for (int c = 0; c < cMsg.A.getValidCount(); c++) {
            BigDecimal cPk = cMsg.A.no(c).svcMachMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            BigDecimal cDtlPk = cMsg.A.no(c).dsContrDtlPk_A.getValue();
            BigDecimal cPrntDtlPk = cMsg.A.no(c).prntDsContrDtlPk_A.getValue();
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
            for (int s = 0; s < sMsg.A.getValidCount(); s++) {
                BigDecimal sPk = sMsg.A.no(s).svcMachMstrPk_A.getValue();
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
                // if (cPk.compareTo(sPk) == 0) {
                BigDecimal sDtlPk = sMsg.A.no(s).dsContrDtlPk_A.getValue();
                BigDecimal sPrntDtlPk = sMsg.A.no(s).prntDsContrDtlPk_A.getValue();
                boolean isDtlPkEqual = (cDtlPk == null && sDtlPk == null) || (cDtlPk != null && sDtlPk != null && cDtlPk.compareTo(sDtlPk) == 0);
                boolean isPrntDtlPkEqual = (cPrntDtlPk == null && sPrntDtlPk == null) || (cPrntDtlPk != null && sPrntDtlPk != null && cPrntDtlPk.compareTo(sPrntDtlPk) == 0);
                if (cPk.compareTo(sPk) == 0 && isDtlPkEqual && isPrntDtlPkEqual) {
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(s).xxChkBox_AM, cMsg.A.no(c).xxChkBox_AM);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(s).xxChkBox_AA, cMsg.A.no(c).xxChkBox_AA);
                    // START 2017/03/01 N.Arai [QC#17620, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(s).serNum_A, cMsg.A.no(c).serNum_A);
                    // END 2017/03/01 N.Arai [QC#17620, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(s).contrEffFromDt_A, cMsg.A.no(c).contrEffFromDt_A);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(s).contrEffThruDt_A, cMsg.A.no(c).contrEffThruDt_A);
                    // START 2015/10/08 T.Tomita [N/A, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(s).xxChkBox_AB, cMsg.A.no(c).xxChkBox_AB);
                    // END 2015/10/08 T.Tomita [N/A, ADD]
                    break;
                }
            }
        }
    }

    /**
     * Paginate Table A To Item
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @param itemIndexInView int
     */
    public static void paginateTableAToItem(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, int itemIndexInView) {
        int firstRowIndexInPage = convertRowIndexToFirstRowIndexInPage(cMsg.A.length(), itemIndexInView);
        List<NSAL0310_ASMsg> sMsgArrayView = createSMsgArrayView(sMsg);
        paginateTableA(cMsg, sMsgArrayView, firstRowIndexInPage);
    }

    /**
     * @param cMsg
     * @param sMsgArrayView
     * @param startIndex
     */
    private static void paginateTableA(NSAL0310CMsg cMsg, List<NSAL0310_ASMsg> sMsgArrayView, int startIndexInView) {
        paginate(cMsg.A, sMsgArrayView, startIndexInView);
        setTableAPaginationParameters(cMsg, sMsgArrayView, startIndexInView);
    }

    private static int convertRowIndexToFirstRowIndexInPage(int rowsPerPage, int rowIndex) {
        return (rowIndex / rowsPerPage) * rowsPerPage;
    }

    private static List<NSAL0310_ASMsg> createSMsgArrayView(NSAL0310SMsg sMsg) {
        List<NSAL0310_ASMsg> sMsgArrayView = new ArrayList<NSAL0310_ASMsg>(sMsg.A.getValidCount());
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String xxDtlStsCd = sMsg.A.no(i).xxDtlStsCd_A.getValue();
            String svcMachTpCd = sMsg.A.no(i).svcMachTpCd_A.getValue();
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd) || DTL_STS_XPND.equals(xxDtlStsCd)) {
                // Add Machine or Expanded Accessory to the view
                sMsgArrayView.add(sMsg.A.no(i));
            }
        }
        return sMsgArrayView;
    }

    /**
     * Convert Index In Page To Index In SMsgArray
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @param cPk BigDecimal
     * @param cDtlPk BigDecimal
     * @param cPrntDtlPk BigDecimal
     * @return int
     */
    // START 2017/03/01 N.Arai [QC#17620, MOD]
//    public static int convertIndexInPageToIndexInSMsgArrayView(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, int indexInPage) {
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    public static int convertIndexInPageToIndexInSMsgArrayView(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, BigDecimal cPk) {
    public static int convertIndexInPageToIndexInSMsgArrayView(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, BigDecimal cPk, BigDecimal cDtlPk, BigDecimal cPrntDtlPk) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]
//      BigDecimal cPk = cMsg.A.no(indexInPage).svcMachMstrPk_A.getValue();
        List<NSAL0310_ASMsg> sMsgArrayView = createSMsgArrayView(sMsg);
        for (int i = 0; i < sMsgArrayView.size(); i++) {
            BigDecimal sPk = sMsgArrayView.get(i).svcMachMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            if (cPk.compareTo(sPk) == 0) {
            BigDecimal sDtlPk = sMsgArrayView.get(i).dsContrDtlPk_A.getValue();
            BigDecimal sPrntDtlPk = sMsgArrayView.get(i).prntDsContrDtlPk_A.getValue();
            boolean isDtlPkEqual = (cDtlPk == null && sDtlPk == null) || (cDtlPk != null && sDtlPk != null && cDtlPk.compareTo(sDtlPk) == 0);
            boolean isPrntDtlPkEqual = (cPrntDtlPk == null && sPrntDtlPk == null) || (cPrntDtlPk != null && sPrntDtlPk != null && cPrntDtlPk.compareTo(sPrntDtlPk) == 0);
            if (cPk.compareTo(sPk) == 0 && isDtlPkEqual && isPrntDtlPkEqual) {
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
                return i;
            }
        }
     // END 2017/03/01 N.Arai [QC#17620, MOD]
        return 0;
    }

    private static void paginate(NSAL0310_ACMsgArray cMsgArray, List<NSAL0310_ASMsg> sMsgArrayView, int startIndexInView) {
        int num = 0;
        ZYPTableUtil.clear(cMsgArray);
        for (int i = startIndexInView; i < sMsgArrayView.size(); i++) {
            if (num >= cMsgArray.length()) {
                break;
            }
            EZDMsg.copy(sMsgArrayView.get(i), null, cMsgArray.no(num), null);
            num++;
        }
        cMsgArray.setValidCount(num);
    }

    private static void setTableAPaginationParameters(NSAL0310CMsg cMsg, List<NSAL0310_ASMsg> sMsgArrayView, int startIndexInView) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndexInView + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndexInView + cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsgArrayView.size()));
    }

    /**
     *  Reset Parameter
     * @param glblCmpyCd String
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     */
    public static void resetParameter(String glblCmpyCd, NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        // START 2015/10/08 T.Tomita [N/A, MOD]
        BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
        // START 2017/11/16 M.Kidokoro [QC#22294, ADD]
        String dsAcctNum = cMsg.dsAcctNum_H.getValue();
        String contrVrsnEffFromDt = cMsg.contrVrsnEffFromDt_H.getValue();
        String contrVrsnEffThruDt = cMsg.contrVrsnEffThruDt_H.getValue();
        // END 2017/11/16 M.Kidokoro [QC#22294, ADD]
        clearParameter(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, dsContrPk);
        // [QC#3017,MOD]START
        ZYPEZDItemValueSetter.setValue(cMsg.xxConfigFlg, ZYPConstant.FLG_OFF_N);
        // [QC#3017,MOD]END
        // END 2015/10/08 T.Tomita [N/A, MOD]
        // START 2016/02/24 T.Kanasaka [QC4086, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.contrInacFlg, ZYPConstant.FLG_ON_Y);
        // END 2016/02/24 T.Kanasaka [QC4086, ADD]

        // START 2015/10/08 T.Tomita [N/A, ADD]
        NSAL0310Query query = NSAL0310Query.getInstance();
        // Get DS_CONTR
        DS_CONTRTMsg dsContrTmsg = query.getDsContrForPk(glblCmpyCd, dsContrPk);
        if (dsContrTmsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract"});
            return;
        }
        // Set Hidden Parameter
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_H, dsContrTmsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt_H, dsContrTmsg.contrVrsnEffFromDt);
        ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt_H, dsContrTmsg.contrVrsnEffThruDt);
        // START 2017/11/16 M.Kidokoro [QC#22294, ADD]
        if (dsAcctNum != null && dsAcctNum.length() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_H, dsAcctNum);
        }
        if (contrVrsnEffFromDt != null && contrVrsnEffFromDt.length() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt_H, contrVrsnEffFromDt);
        }
        if (contrVrsnEffThruDt != null && contrVrsnEffThruDt.length() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt_H, contrVrsnEffThruDt);
        }
        // END 2017/11/16 M.Kidokoro [QC#22294, ADD]
        EZDMsg.copy(cMsg, null, sMsg, null);
        // END 2015/10/08 T.Tomita [N/A, ADD]

        // [QC#3017,ADD]START
        String dsAcctNm = NSAL0310Query.getInstance().getDsAcctNm(glblCmpyCd, dsContrPk);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, dsAcctNm);
        // [QC#3017,ADD]END
    }

    private static void clearParameter(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Format Address
     * @param firstLineAddr String
     * @param ctyAddr String
     * @param stCd String
     * @param postCd String
     * @return String
     */
    public static String formatAddress(String firstLineAddr, String ctyAddr, String stCd, String postCd) {
        StringBuilder buf = new StringBuilder();
        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            buf.append(firstLineAddr);
        }
        if (ZYPCommonFunc.hasValue(ctyAddr)) {
            buf.append(", ");
            buf.append(ctyAddr);
        }
        if (ZYPCommonFunc.hasValue(stCd)) {
            buf.append(", ");
            buf.append(stCd);
        }
        if (ZYPCommonFunc.hasValue(postCd)) {
            buf.append(" ");
            buf.append(postCd);
        }
        return buf.toString();
    }

    // START 2017/03/01 N.Arai [QC#17620, ADD]
    /**
     * select Operation
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @param flg String
     */
    public static void selectOperation(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String flg) {
        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String svcMachTpCd = sMsg.A.no(i).svcMachTpCd_A.getValue();
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_AM, flg);
            } else if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_AA, flg);
            }
        }
        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, startIndex);
    }

    /**
     * Check Accessory And Collapse
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @param rowNum int
     * @param stsCd String
     * @param flg String
     */
    public static void checkBoxOperation(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, int rowNum, String stsCd, String flg) {

        BigDecimal cPk = cMsg.A.no(rowNum).svcConfigMstrPk_A.getValue();
        String svcMachTpCd;
        BigDecimal sPk;
        // START 2022/01/21 R.Cabral [QC#59502, ADD]
        BigDecimal rowDsContrDtlPk = cMsg.A.no(rowNum).dsContrDtlPk_A.getValue();
        BigDecimal prntDsContrDtlPk;
        BigDecimal dsContrDtlPk;
        // END   2022/01/21 R.Cabral [QC#59502, ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            svcMachTpCd = sMsg.A.no(i).svcMachTpCd_A.getValue();
            sPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            prntDsContrDtlPk = sMsg.A.no(i).prntDsContrDtlPk_A.getValue();
            dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            // END   2022/01/21 R.Cabral [QC#59502, ADD]

            if (ZYPCommonFunc.hasValue(sPk) && cPk.compareTo(sPk) == 0) {
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtlStsCd_A, stsCd);
                boolean isMachineDtlPkEqual = ((dsContrDtlPk == null && rowDsContrDtlPk == null)
                        || (dsContrDtlPk != null && rowDsContrDtlPk != null && dsContrDtlPk.compareTo(rowDsContrDtlPk) == 0));
                boolean isAccPrntDtlPkEqualToRowDtlPk = ((prntDsContrDtlPk == null && rowDsContrDtlPk == null)
                        || (prntDsContrDtlPk != null && rowDsContrDtlPk != null && prntDsContrDtlPk.compareTo(rowDsContrDtlPk) == 0));
                if ((SVC_MACH_TP.MACHINE.equals(svcMachTpCd) && isMachineDtlPkEqual) || (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd) && isAccPrntDtlPkEqualToRowDtlPk)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtlStsCd_A, stsCd);
                }
                // END   2022/01/21 R.Cabral [QC#59502, MOD]

                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
                if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd) && isAccPrntDtlPkEqualToRowDtlPk) {
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_AA, flg);
                }
            }
        }
    }

    /**
     * Set Key
     * @param map Map<BigDecimal, HashMap<BigDecimal, Object>>
     * @param cnfgMstrPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @param svcMachTpCd String
     */
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    public static void setKey(Map<BigDecimal, Object> map, BigDecimal cnfgMstrPk, String svcMachTpCd) {
    public static void setKey(Map<BigDecimal, HashMap<BigDecimal, Object>> map, BigDecimal cnfgMstrPk, BigDecimal dsContrDtlPk, String svcMachTpCd) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]
        Boolean isAccessory = false;
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
            isAccessory = true;
        }
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        if (!map.containsKey(cnfgMstrPk)) {
//            map.put(cnfgMstrPk, isAccessory);
//            return;
        HashMap<BigDecimal, Object> innerMap;
        if (!map.containsKey(cnfgMstrPk)) {
            innerMap = new HashMap<BigDecimal, Object>();
            innerMap.put(dsContrDtlPk, isAccessory);
            map.put(cnfgMstrPk, innerMap);
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
            return;
        }
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        Boolean mapValue = (Boolean) map.get(cnfgMstrPk);
//        if (!mapValue) {
//            map.put(cnfgMstrPk, isAccessory);
//        }
        innerMap = map.get(cnfgMstrPk);
        if (!innerMap.containsKey(dsContrDtlPk)) {
            innerMap.put(dsContrDtlPk, isAccessory);
        } else {
            Boolean mapValue = (Boolean) innerMap.get(dsContrDtlPk);
            if (!mapValue) {
                innerMap.put(dsContrDtlPk, isAccessory);
            }
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
        }
    }
    
    /**
     * getErrPageFromNum
     * @param cMsg NSAL0310CMsg
     * @param msgView List<NSAL0310_ASMsg>
     * @return int
     */
    public static int getErrPageFromNum(NSAL0310CMsg cMsg, List<NSAL0310_ASMsg> msgView) {

        int errIndex = 0;
        for (int i = 0; i < msgView.size(); i++) {
            // Error
            // START 2017/09/04 U.Kim [QC#20856, MOD]
            // if (msgView.get(i).serNum_A.isError() || msgView.get(i).contrEffFromDt_A.isError() || msgView.get(i).contrEffThruDt_A.isError()) {
            if (msgView.get(i).xxChkBox_AA.isError() || msgView.get(i).serNum_A.isError() || msgView.get(i).contrEffFromDt_A.isError() || msgView.get(i).contrEffThruDt_A.isError()) {
            // END 2017/09/04 U.Kim [QC#20856, MOD]
                errIndex = i;
                break;
            }
        }

        return errIndex / cMsg.A.length() * cMsg.A.length();
    }

    public static void createAddMachinesTableA(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        List<NSAL0310_ASMsg> sMsgArrayView = createSMsgArrayView(sMsg);
        int startIndex = getErrPageFromNum(cMsg, sMsgArrayView);
        paginateTableA(cMsg, sMsgArrayView, startIndex);
    }
    // END 2017/03/01 N.Arai [QC#17620, ADD]

    /**
     * Check Main Machine for Accessory
     * @param cMsg NSAL0310CMsg
     * @param sMsg NSAL0310SMsg
     * @param glblCmpyCd String
     * @param exMap Map<BigDecimal, HashMap<BigDecimal, Object>>
     * @return boolean
     */
    // START 2017/09/04 U.Kim [QC#20856, ADD]
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    public static boolean checkMainMachineForAccessory(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd, Map<BigDecimal, Object> exMap) {
    public static boolean checkMainMachineForAccessory(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd, Map<BigDecimal, HashMap<BigDecimal, Object>> exMap) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]
        boolean valid = true;

        for (int lineCount = 0; lineCount < sMsg.A.getValidCount(); lineCount++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(lineCount).xxChkBox_AA.getValue())) {
                continue;
            }
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            BigDecimal dsContrDtlPk = sMsg.A.no(lineCount).dsContrDtlPk_A.getValue();
            if (dsContrDtlPk != null) {
                // has valid DS_CONTR_DTL_PK, already saved in contract
                continue;
            }
            if (SVC_MACH_TP.ACCESSORY.equals(sMsg.A.no(lineCount).svcMachTpCd_A.getValue())) {
                // Substitute DS_CONTR_DTL_PK value with the PRNT_DS_CONTR_DTL_PK for setKey processing
                dsContrDtlPk = sMsg.A.no(lineCount).prntDsContrDtlPk_A.getValue();
            }
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
            for (int mainMachSearchCount = lineCount - 1; mainMachSearchCount >= 0; mainMachSearchCount--) {
                if (!sMsg.A.no(mainMachSearchCount).svcConfigMstrPk_A.getValue().equals(sMsg.A.no(lineCount).svcConfigMstrPk_A.getValue())) {
                    break;
                }
                if (!sMsg.A.no(mainMachSearchCount).svcMachTpCd_A.getValue().equals(SVC_MACH_TP.MACHINE)) {
                    continue;
                }
                // START 2022/01/21 R.Cabral [QC#59502, ADD]
                BigDecimal mainMachDsContrDtlPk = sMsg.A.no(mainMachSearchCount).dsContrDtlPk_A.getValue();
                BigDecimal lineCountPrntDsContrDtlPk = sMsg.A.no(lineCount).prntDsContrDtlPk_A.getValue();
                if (!((mainMachDsContrDtlPk == null && lineCountPrntDsContrDtlPk == null)
                        || (mainMachDsContrDtlPk != null && lineCountPrntDsContrDtlPk != null
                                && mainMachDsContrDtlPk.compareTo(lineCountPrntDsContrDtlPk) == 0))) {
                    break;
                }
                // END   2022/01/21 R.Cabral [QC#59502, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(mainMachSearchCount).xxChkBox_AM.getValue())) {
                    break;
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                }
                } else if (mainMachDsContrDtlPk == null && lineCountPrntDsContrDtlPk == null) {
                    // Mach is not checked but Acc is checked for machine&accessory combo that is not added to the contract yet
                    sMsg.A.no(lineCount).xxChkBox_AA.setErrorInfo(1, NSAM0697E);
                    setKey(exMap, sMsg.A.no(lineCount).svcConfigMstrPk_A.getValue(), dsContrDtlPk, sMsg.A.no(lineCount).svcMachTpCd_A.getValue());
                    valid = false;
                }
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                BigDecimal mainMachCount = NSAL0310Query.getInstance().getMainMachine(glblCmpyCd, cMsg.dsContrPk.getValue(), sMsg.A.no(lineCount).svcConfigMstrPk_A.getValue());
                if (BigDecimal.ZERO.compareTo(mainMachCount) == 0) {
                    sMsg.A.no(lineCount).xxChkBox_AA.setErrorInfo(1, NSAM0697E);
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    setKey(exMap, sMsg.A.no(lineCount).svcConfigMstrPk_A.getValue(), sMsg.A.no(lineCount).svcMachTpCd_A.getValue());
                    setKey(exMap, sMsg.A.no(lineCount).svcConfigMstrPk_A.getValue(), dsContrDtlPk, sMsg.A.no(lineCount).svcMachTpCd_A.getValue());
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                    valid = false;
                }
            }
        }
        return valid;
    }
    // END 2017/09/04 U.Kim [QC#20856, ADD]

    // START 2023/04/24 K.Watanabe [QC#61146, ADD]
    /**
     * formatAddress
     * @param String firstLineAddr
     * @param String secondLineAddr
     * @param String thirdLineAddr
     * @param String fourthLineAddr
     * @param String ctyAddr
     * @param String stCd
     * @param String postCd
     * @return String
     */
    public static String formatAddress(String firstLineAddr, String secondLineAddr, String thirdLineAddr, String fourthLineAddr, String ctyAddr, String stCd, String postCd) {
        StringBuilder buf = new StringBuilder();
        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            buf.append(firstLineAddr);
        }
        if (ZYPCommonFunc.hasValue(secondLineAddr)) {
            buf.append(" ");
            buf.append(secondLineAddr);
        }
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            buf.append(" ");
            buf.append(thirdLineAddr);
        }
        if (ZYPCommonFunc.hasValue(fourthLineAddr)) {
            buf.append(" ");
            buf.append(fourthLineAddr);
        }
        if (ZYPCommonFunc.hasValue(ctyAddr)) {
            buf.append(", ");
            buf.append(ctyAddr);
        }
        if (ZYPCommonFunc.hasValue(stCd)) {
            buf.append(", ");
            buf.append(stCd);
        }
        if (ZYPCommonFunc.hasValue(postCd)) {
            buf.append(" ");
            buf.append(postCd);
        }
        return buf.toString();
    }

    /**
     * convertDateEzd8ToDisp
     * @param String date
     * @return String
     */
    public static String convertDateEzd8ToDisp(String date) {
        if (ZYPCommonFunc.hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END 2023/04/24 K.Watanabe [QC#61146, ADD]
}
