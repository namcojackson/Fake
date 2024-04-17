/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7220.common;

import static business.blap.NMAL7220.constant.NMAL7220Constant.NMAM8186E;
import static business.blap.NMAL7220.constant.NMAL7220Constant.NMAM8296E;
import static business.blap.NMAL7220.constant.NMAL7220Constant.VAR_CHAR_CONST_STD_COST_DUMMY_PRC_LIST;

import java.math.BigDecimal;
import java.util.Map;

import business.blap.NMAL7220.NMAL7220CMsg;
import business.blap.NMAL7220.NMAL7220Query;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_FUNC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7220CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7220CommonLogic {

    /**
     * createPullDown
     * 
     * @param bizMsg NMAL7220CMsg
     */
    public static void createPullDown(NMAL7220CMsg bizMsg) {
        // Pull down
        ZYPCodeDataUtil.createPulldownList(PRC_FMLA_TP.class, bizMsg.prcFmlaTpCd_L1, bizMsg.prcFmlaTpNm_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_FMLA_OP.class, bizMsg.prcFmlaOpCd_L1, bizMsg.prcFmlaOpNm_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_FUNC_TP.class, bizMsg.prcFuncTpCd_L1, bizMsg.prcFuncTpNm_L1);
    }

    /**
     * createDataType
     * 
     * @param bizMsg NMAL7220CMsg
     * @param rsltMap Map< ? , ? >
     * @param globalCompanyCode String
     */
    public static void createSearchData(NMAL7220CMsg bizMsg, Map< ? , ? > rsltMap, String globalCompanyCode) {

        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaNm_H1, (String) rsltMap.get("PRC_FMLA_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaNm_ZZ, bizMsg.prcFmlaNm_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaDescTxt_H1, (String) rsltMap.get("PRC_FMLA_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaDescTxt_ZZ, bizMsg.prcFmlaDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, (String) rsltMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_ZZ, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_H1, (String) rsltMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_ZZ, bizMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_H1, setActvFlg((String) rsltMap.get("ACTV_FLG")));
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_ZZ, bizMsg.actvFlg_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcFmlaTpCd_A1, (String) rsltMap.get("PRC_FMLA_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).prcFmlaTpCd_Z1, bizMsg.A.no(0).prcFmlaTpCd_A1);
        if (PRC_FMLA_TP.STANDARD_COST.equals(bizMsg.A.no(0).prcFmlaTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcCatgNm_A1, ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_STD_COST_DUMMY_PRC_LIST, globalCompanyCode));
        } else if (!ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcCatgNm_A1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcCatgNm_A1, (String) rsltMap.get("PRC_CATG_NM"));
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).prcCatgNm_Z1, bizMsg.A.no(0).prcCatgNm_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcCatgCd_A1, (String) rsltMap.get("PRC_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).prcCatgCd_Z1, bizMsg.A.no(0).prcCatgCd_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcFmlaOpCd_A1, (String) rsltMap.get("PRC_FMLA_OP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).prcFmlaOpCd_Z1, bizMsg.A.no(0).prcFmlaOpCd_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcFmlaNum_A1, (BigDecimal) rsltMap.get("PRC_FMLA_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).prcFmlaNum_Z1, bizMsg.A.no(0).prcFmlaNum_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcFuncTpCd_A1, (String) rsltMap.get("PRC_FUNC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.Z.no(0).prcFuncTpCd_Z1, bizMsg.A.no(0).prcFuncTpCd_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcFmlaPk_ZZ, bizMsg.prcFmlaPk_H1);
    }

    /**
     * chkUpdateSameData
     * 
     * @param bizMsg NMAL7220CMsg
     * @return boolean
     */
    public static boolean chkUpdateSameData(NMAL7220CMsg bizMsg) {
        if (chkBigDecimalUpdateData(bizMsg.prcFmlaPk_ZZ.getValue(), bizMsg.prcFmlaPk_H1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.prcFmlaNm_ZZ.getValue(), bizMsg.prcFmlaNm_H1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.prcFmlaDescTxt_ZZ.getValue(), bizMsg.prcFmlaDescTxt_H1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.effFromDt_ZZ.getValue(), bizMsg.effFromDt_H1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.effThruDt_ZZ.getValue(), bizMsg.effThruDt_H1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.actvFlg_ZZ.getValue(), bizMsg.actvFlg_H1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.Z.no(0).prcFmlaTpCd_Z1.getValue(), bizMsg.A.no(0).prcFmlaTpCd_A1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.Z.no(0).prcCatgNm_Z1.getValue(), bizMsg.A.no(0).prcCatgNm_A1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.Z.no(0).prcFmlaOpCd_Z1.getValue(), bizMsg.A.no(0).prcFmlaOpCd_A1.getValue())) {
            return true;
        }
        if (chkBigDecimalUpdateData(bizMsg.Z.no(0).prcFmlaNum_Z1.getValue(), bizMsg.A.no(0).prcFmlaNum_A1.getValue())) {
            return true;
        }
        if (chkStringUpdateData(bizMsg.Z.no(0).prcFuncTpCd_Z1.getValue(), bizMsg.A.no(0).prcFuncTpCd_A1.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean chkStringUpdateData(String searchItem, String submitItem) {
        if (ZYPCommonFunc.hasValue(searchItem)) {
            if (searchItem.equals(submitItem)) {
                return false;
            } else {
                return true;
            }
        } else {
            if (ZYPCommonFunc.hasValue(submitItem)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean chkBigDecimalUpdateData(BigDecimal searchItem, BigDecimal submitItem) {
        if (ZYPCommonFunc.hasValue(searchItem)) {
            if (ZYPCommonFunc.hasValue(submitItem)) {
                if (searchItem.compareTo(submitItem) == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (ZYPCommonFunc.hasValue(submitItem)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * setActvFlg
     * 
     * @param item String
     * @return String
     */
    public static String setActvFlg(String item) {
        if (ZYPConstant.FLG_ON_Y.equals(item)) {
            return item;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    /**
     * isErrCheck
     * 
     * @param bizMsg NMAL7220CMsg
     * @return boolean
     */
    public static boolean isErrCheck(NMAL7220CMsg bizMsg) {
        boolean errFlg = false;
        if (isExistFmlaNm(bizMsg)) {
            errFlg = true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).prcCatgNm_A1)) {
            if (isExistPriceList(bizMsg)) {
                errFlg = true;
            }
        }
        if (!chkUpdateSameData(bizMsg)) {
            errFlg = true;
        }

        return errFlg;
    }

    private static boolean isExistFmlaNm(NMAL7220CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7220Query.getInstance().isExistFmlaNm(bizMsg);
        if (!S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())) {
            bizMsg.prcFmlaNm_H1.setErrorInfo(1, NMAM8296E, new String[] {bizMsg.prcFmlaNm_H1.getValue()});
            return true;
        }
        return false;
    }

    private static boolean isExistPriceList(NMAL7220CMsg bizMsg) {
        if (!PRC_FMLA_TP.STANDARD_COST.equals(bizMsg.A.no(0).prcFmlaTpCd_A1.getValue())) {
            S21SsmEZDResult ssmResult = NMAL7220Query.getInstance().isExistPriceList(bizMsg.A.no(0).prcCatgNm_A1.getValue());
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                bizMsg.A.no(0).prcCatgNm_A1.setErrorInfo(1, NMAM8186E, new String[] {bizMsg.A.no(0).prcCatgNm_A1.getValue()});
                return true;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).prcCatgCd_A1, (String) ssmResult.getResultObject());
        }

        return false;
    }
}
