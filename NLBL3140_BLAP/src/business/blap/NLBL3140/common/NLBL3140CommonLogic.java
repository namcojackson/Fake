/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3140.common;

import static business.blap.NLBL3140.constant.NLBL3140Constant.ASTERISK;
import static business.blap.NLBL3140.constant.NLBL3140Constant.BIZ_ID;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1265E;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1120E;
import static business.blap.NLBL3140.constant.NLBL3140Constant.ZZZM9026E;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLAM0240E;
import static business.blap.NLBL3140.constant.NLBL3140Constant.NLBM1023E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3140.NLBL3140CMsg;
import business.blap.NLBL3140.NLBL3140Query;
import business.blap.NLBL3140.NLBL3140SMsg;
import business.blap.NLBL3140.NLBL3140_ASMsg;
import business.blap.NLBL3140.NLBL3140_CSMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.VAR_CHAR_CONSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NLBL3140CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/08/16   Fujitsu         S.Yamamoto      Update          QC#20555
 * 2017/09/22   CITS            S.Endo          Update          QC#21077
 * 2023/07/10   Hitachi         G.Quan          Update          QC#61543
 *</pre>
 */
public class NLBL3140CommonLogic {

    /**
     * update S message
     * @param cMsg NLBL3140CMsg
     * @param sMsg NLBL3140SMsg
     */
    public static void updateSMsg(NLBL3140CMsg cMsg, NLBL3140SMsg sMsg) {
        int ixG = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }

    /**
     * control pagenation
     * @param cMsg NLBL3140CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NLBL3140SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NLBL3140CMsg cMsg, EZDCMsgArray cMsgArray, NLBL3140SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }
        if (i == 0) {
            cMsgArray.setValidCount(i);
        } else {
            cMsgArray.setValidCount(i - startIndex);
        }
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * check Order Category on DS_ORD_CATG_CD Table
     * @param glblCmpyCd Global Company Code
     * @param sMsg NLBL3140_ASMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean checkOrdCatg(NLBL3140_ASMsg acMsg) {
        if (ASTERISK.equals(acMsg.dsOrdCatgDescTxt_A.getValue()) || !ZYPCommonFunc.hasValue(acMsg.dsOrdCatgDescTxt_A)) {
            ZYPEZDItemValueSetter.setValue(acMsg.dsOrdCatgCd_A, ASTERISK);
            return true;
        }
        S21SsmEZDResult ssmResult = NLBL3140Query.getInstance().checkOrdCatg(acMsg);
        // 2017/08/14 QC#20555 MOD BEGIN
//        if (ssmResult.isCodeNormal()) {
        if (ssmResult.isCodeNormal() && 0 < ssmResult.getQueryResultCount()) {
//            if (!ZYPCommonFunc.hasValue(acMsg.dsOrdCatgCd_A)) {
//                ZYPEZDItemValueSetter.setValue(acMsg.dsOrdCatgCd_A, ((Map<String, String>) ssmResult.getResultObject()).get("DS_ORD_CATG_CD"));
//            }
            ZYPEZDItemValueSetter.setValue(acMsg.dsOrdCatgCd_A, ((Map<String, String>) ssmResult.getResultObject()).get("DS_ORD_CATG_CD"));
            // 2017/08/14 QC#20555 MOD END
            return true;
        }
        acMsg.dsOrdCatgDescTxt_A.setErrorInfo(1, NLBM1120E);
        return false;
    }

    /**
     * check Retail Warehouse Sub Warehouse Set
     * @param globalCompanyCode
     * @param acMsg
     * @return
     */
    public static boolean checkWhSwhCd(String globalCompanyCode, NLBL3140_ASMsg acMsg) {
        if (ASTERISK.equals(acMsg.rtlSwhCd_A.getValue()) || !ZYPCommonFunc.hasValue(acMsg.rtlSwhCd_A)) {
            for (String str : getNotAllocWhCd(globalCompanyCode).split(",")) {
                if (str.equals(acMsg.rtlWhCd_A.getValue())) {
                    acMsg.rtlWhCd_A.setErrorInfo(1, ZZZM9026E, new String[] {"WH_CD" });
                    return false;
                }
            }
            if (!checkWhCd(acMsg.rtlWhCd_A.getValue(), globalCompanyCode)) {
                acMsg.rtlWhCd_A.setErrorInfo(1, NLBM1120E);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(acMsg.rtlSwhCd_A, ASTERISK);
            return true;
        }
        RTL_SWHTMsg rtlSwhTMsg = new RTL_SWHTMsg();
        rtlSwhTMsg.setConditionValue("glblCmpyCd01", globalCompanyCode);
        rtlSwhTMsg.setConditionValue("rtlWhCd01", acMsg.rtlWhCd_A.getValue());
        rtlSwhTMsg.setConditionValue("rtlSwhCd01", acMsg.rtlSwhCd_A.getValue());
        rtlSwhTMsg.setSQLID("004");
        RTL_SWHTMsgArray result = (RTL_SWHTMsgArray) EZDTBLAccessor.findByCondition(rtlSwhTMsg);
        if (result.length() != 0) {
            return true;
        }
        acMsg.rtlWhCd_A.setErrorInfo(1, NLBM1023E, new String[] {"RTL_SWH", "WH_CD", acMsg.rtlWhCd_A.getValue() });
        acMsg.rtlSwhCd_A.setErrorInfo(1, NLBM1023E, new String[] {"RTL_SWH", "SWH_CD", acMsg.rtlSwhCd_A.getValue() });
        return false;
    }

    /**
     * get not allocation Code From var char const
     * @param glblCmpyCd
     * @return
     */
    private static String getNotAllocWhCd(String glblCmpyCd) {
        VAR_CHAR_CONSTTMsg varCharTMsg = new VAR_CHAR_CONSTTMsg();
        varCharTMsg.glblCmpyCd.setValue(glblCmpyCd);
        varCharTMsg.varCharConstNm.setValue("NOT_HARD_ALLOC_WH_CD");

        VAR_CHAR_CONSTTMsg resTMsg = (VAR_CHAR_CONSTTMsg) S21CodeTableAccessor.findByKey(varCharTMsg);

        if (resTMsg == null) {
        }
        return resTMsg.varCharConstVal.getValue();

    }

    /**
     * check Retail Warehouse
     * @param whCd
     * @param glblCupyCd
     * @return
     */
    private static boolean checkWhCd(String whCd, String glblCupyCd) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, glblCupyCd);
        ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, whCd);
        rtlWhTMsg = (RTL_WHTMsg) EZDFastTBLAccessor.findByKey(rtlWhTMsg);

        if (rtlWhTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * DB access to check registered items
     * @param acMsg NLBL3140_ASMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean checkRegisteredRule(NLBL3140_ASMsg acMsg) {

        S21SsmEZDResult ssmResult = NLBL3140Query.getInstance().checkRegisteredRule(acMsg);
        if (ssmResult.isCodeNormal()) {
            if ((((Map<String, BigDecimal>) ssmResult.getResultObject()).get("CN").compareTo(new BigDecimal("0"))) != 0) {
                // 2017/08/16 QC#20555 START
                acMsg.lineBizTpCd_A.setErrorInfo(1, NLBM1265E, new String[] {"This combination"});
                acMsg.rtlWhCd_A.setErrorInfo(1, NLBM1265E, new String[] {"This combination"});
                acMsg.rtlSwhCd_A.setErrorInfo(1, NLBM1265E, new String[] {"This combination"});
                acMsg.dsOrdCatgDescTxt_A.setErrorInfo(1, NLBM1265E, new String[] {"This combination"});
                acMsg.hardAllocTpCd_A.setErrorInfo(1, NLBM1265E, new String[] {"This combination"});
//                acMsg.rtlWhCd_A.setErrorInfo(1, NLBM1023E, new String[] {"MDSE_WH_COND", "WH_CD", acMsg.rtlWhCd_A.getValue() });
//                acMsg.rtlSwhCd_A.setErrorInfo(1, NLBM1023E, new String[] {"MDSE_WH_COND", "SWH_CD", acMsg.rtlSwhCd_A.getValue() });
//                acMsg.dsOrdCatgCd_A.setErrorInfo(1, NLBM1023E, new String[] {"MDSE_WH_COND", "DS_ORD_CATG_CD", acMsg.dsOrdCatgCd_A.getValue() });
                // 2017/08/16 QC#20555 END
                // START 2023/07/10 G.Quan [QC#61543, ADD] 
                acMsg.coaProdCd_A.setErrorInfo(1, NLBM1265E, new String[] {"This combination"});
                // END 2023/07/10 G.Quan [QC#61543, ADD]
                return true;
            }
        }
        return false;
    }

    /**
     * check combination
     * @param asMsg NLBL3140_ASMsg
     * @param csMsg NLBL3140_CSMsg
     * @return
     */
    public static boolean checkCombination(NLBL3140_ASMsg asMsg, NLBL3140_CSMsg csMsg) {
        if (!asMsg.lineBizTpCd_A.getValue().equals(csMsg.lineBizTpCd_C.getValue()) || //
                !asMsg.rtlWhCd_A.getValue().equals(csMsg.rtlWhCd_C.getValue()) || // 
                !asMsg.rtlSwhCd_A.getValue().equals(csMsg.rtlSwhCd_C.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * set Function Id
     * @param bizMsg NLBL3140CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NLBL3140CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId_Z.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }

    /**
     * check difference
     * @param asMsg NLBL3140SMsg
     * @param csMsg NLBL3140_CSMsg
     * @return boolean
     */
    public static boolean checkDifference(NLBL3140_ASMsg asMsg, NLBL3140_CSMsg csMsg) {

        if (!ZYPCommonFunc.hasValue(asMsg.mdseWhCondPk_A)) {
            return true;
        }

        if (asMsg.mdseWhCondPk_A.getValue().equals(csMsg.mdseWhCondPk_C.getValue())) {
            if (!asMsg.lineBizTpCd_A.getValue().equals(csMsg.lineBizTpCd_C.getValue()) || //
                    !asMsg.rtlWhCd_A.getValue().equals(csMsg.rtlWhCd_C.getValue()) || // 
                    !asMsg.rtlSwhCd_A.getValue().equals(csMsg.rtlSwhCd_C.getValue()) || //
                    !asMsg.hardAllocTpCd_A.getValue().equals(csMsg.hardAllocTpCd_C.getValue()) || //
                    !asMsg.dsOrdCatgCd_A.getValue().equals(csMsg.dsOrdCatgCd_C.getValue()) || //
                    !asMsg.dsOrdCatgDescTxt_A.getValue().equals(csMsg.dsOrdCatgDescTxt_C.getValue()) || //
                    // START 2023/07/10 G.Quan [QC#61543, ADD]
                    !asMsg.coaProdCd_A.getValue().equals(csMsg.coaProdCd_C.getValue())) {// || //
                    // END 2023/07/10 G.Quan [QC#61543, ADD]
                return true;
            }
            // 2017/09/22 S.Endo Mod QC#21077 START
            if (ZYPCommonFunc.hasValue(asMsg.tmFenceDaysAot_A) && ZYPCommonFunc.hasValue(csMsg.tmFenceDaysAot_C)) {
                if (!asMsg.tmFenceDaysAot_A.getValue().equals(csMsg.tmFenceDaysAot_C.getValue())) {
                    return true;
                }
            }
            if ((!ZYPCommonFunc.hasValue(asMsg.tmFenceDaysAot_A) && ZYPCommonFunc.hasValue(csMsg.tmFenceDaysAot_C))) {
                return true;
            }
            if ((ZYPCommonFunc.hasValue(asMsg.tmFenceDaysAot_A) && !ZYPCommonFunc.hasValue(csMsg.tmFenceDaysAot_C))) {
                return true;
            }
            // 2017/09/22 S.Endo Mod QC#21077 END
        }
        return false;
    }

    /**
     * check Duplicate
     * @param sMsg NLBL3140SMsg
     * @return
     */
    public static boolean checkDuplicate(NLBL3140SMsg sMsg, int num) {
        NLBL3140_ASMsg asMsg = sMsg.A.no(num);
        int dupNum = 0;
        for (int i = 0; i < sMsg.D.getValidCount(); i++) {
            // 2017/08/14 QC#20555 MOD BEGIN
//            if (asMsg.lineBizTpCd_A.getValue().equals(sMsg.D.no(i).lineBizTpCd_D.getValue()) && //
//                    asMsg.rtlWhCd_A.getValue().equals(sMsg.D.no(i).rtlWhCd_D.getValue()) && // 
//                    asMsg.rtlSwhCd_A.getValue().equals(sMsg.D.no(i).rtlSwhCd_D.getValue()) && //
//                    asMsg.hardAllocTpCd_A.getValue().equals(sMsg.D.no(i).hardAllocTpCd_D.getValue()) && //
//                    asMsg.dsOrdCatgCd_A.getValue().equals(sMsg.D.no(i).dsOrdCatgCd_D.getValue())) {
            boolean lineBizTpCdDuplicated = asMsg.lineBizTpCd_A.getValue().equals(sMsg.D.no(i).lineBizTpCd_D.getValue());
            boolean rtlWhCdDuplicated = asMsg.rtlWhCd_A.getValue().equals(sMsg.D.no(i).rtlWhCd_D.getValue());
            boolean rtlSwhCdDuplicated = asMsg.rtlSwhCd_A.getValue().equals(sMsg.D.no(i).rtlSwhCd_D.getValue()) || ASTERISK.equals(sMsg.D.no(i).rtlSwhCd_D.getValue());
            boolean hardAllocTpCdDuplicated = asMsg.hardAllocTpCd_A.getValue().equals(sMsg.D.no(i).hardAllocTpCd_D.getValue());
            boolean dsOrdCatgCdDuplicated = asMsg.dsOrdCatgCd_A.getValue().equals(sMsg.D.no(i).dsOrdCatgCd_D.getValue()) || ASTERISK.equals(sMsg.D.no(i).dsOrdCatgCd_D.getValue());
            // START 2023/07/10 G.Quan [QC#61543, ADD]
            boolean coaProdCdDuplicated = asMsg.coaProdCd_A.getValue().equals(sMsg.D.no(i).coaProdCd_D.getValue()) || ASTERISK.equals(sMsg.D.no(i).coaProdCd_D.getValue());
            // END 2023/07/10 G.Quan [QC#61543, ADD]
            if (lineBizTpCdDuplicated &&
                    rtlWhCdDuplicated &&
                    rtlSwhCdDuplicated &&
                    hardAllocTpCdDuplicated &&
                    dsOrdCatgCdDuplicated &&
                    // START 2023/07/10 G.Quan [QC#61543, ADD]
                    coaProdCdDuplicated) {
                    // END 2023/07/10 G.Quan [QC#61543, ADD]
            // 2017/08/14 QC#20555 MOD END
                if (ZYPCommonFunc.hasValue(asMsg.tmFenceDaysAot_A) && ZYPCommonFunc.hasValue(sMsg.D.no(i).tmFenceDaysAot_D)) {
                    if (asMsg.tmFenceDaysAot_A.getValue().equals(sMsg.D.no(i).tmFenceDaysAot_D.getValue())) {
                        dupNum++;
                        if (dupNum == 2) {
                            asMsg.rtlWhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                            asMsg.rtlSwhCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                            asMsg.dsOrdCatgDescTxt_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                            asMsg.coaProdCd_A.setErrorInfo(1, NLAM0240E, new String[] {"Input" });
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // START 2023/07/10 G.Quan [QC#61543, ADD]
    /**
     * check ProductCode
     * @param acMsg coaProdCd 
     * @param glblCupyCd glblCupyCd
     * @return
     */
    public static boolean checkProductCode(NLBL3140_ASMsg acMsg) {
        S21SsmEZDResult ssmResult = NLBL3140Query.getInstance().countProductCode(acMsg.coaProdCd_A.getValue());
        if (ssmResult.isCodeNormal()) {
            int count = (Integer) ssmResult.getResultObject();
            if (count < 1) {
                acMsg.coaProdCd_A.setErrorInfo(1, NLBM1120E);
                return false;
            }
        }
        return true;
    }
    // END 2023/07/10 G.Quan [QC#61543, ADD]
}
