/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0320.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import parts.common.EZDMessageInfo;
import business.blap.NSAL0320.NSAL0320CMsg;
import business.blap.NSAL0320.NSAL0320Query;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC#1811
 * 2016/05/20   Hitachi         T.Tomita        Update          QC#4958
 * 2016/06/08   Hitachi         T.Kanasaka      Update          QC#5005
 * 2016/07/07   Hitachi         T.Tomita        Update          QC#11466
 * 2016/07/26   Hitachi         A.Kohinata      Update          QC#12106
 *</pre>
 */
public class NSAL0320CommonLogic {

    public static void resetParameter(NSAL0320CMsg cMsg) {
        BigDecimal dsContrDtlPk = cMsg.dsContrDtlPk.getValue();
        // START 2015/10/13 T.Tomita [N/A, ADD]
        String xxModeCd = cMsg.xxModeCd.getValue();
        // END 2015/10/13 T.Tomita [N/A, ADD]
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2015/10/13 T.Tomita [N/A, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd, xxModeCd);
        // END 2015/10/13 T.Tomita [N/A, ADD]
    }

    @SuppressWarnings("unchecked")
    public static boolean hasError(NSAL0320CMsg cMsg) {
        if ("E".equals(cMsg.getMessageKind())) {
            return true;
        }
        try {
            Field field = NSAL0320CMsg.class.getSuperclass().getDeclaredField("errorHash");
            field.setAccessible(true);
            Map<String, EZDMessageInfo> errorHash = (Map<String, EZDMessageInfo>) field.get(cMsg);
            // add start 2016/07/07 CSA Defect#11466
            if (errorHash == null) {
                return false;
            }
            // add end 2016/07/07 CSA Defect#11466
            return !errorHash.isEmpty();
        } catch (Exception e) {
            throw new S21AbendException(e);
        }
    }

    // mod start 2016/07/26 CSA Defect#12106
    public static List<Map<String, Object>> getBllgMtrMap(String glblCmpyCd, String mdlMtrLbCd, String bllgMtrLbCd, String bllgMtrMapLvlNum, BigDecimal dsContrDtlPk, String slsDt, BigDecimal prcMtrPkgPk) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getBllgMtrMap(glblCmpyCd, mdlMtrLbCd, bllgMtrLbCd, bllgMtrMapLvlNum, dsContrDtlPk, slsDt, prcMtrPkgPk);
        if (rslt != null && rslt.isCodeNormal()) {
            return (List<Map<String, Object>>) rslt.getResultObject();
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    // START 2016/06/08 T.Kanasaka [QC#5005, MOD]
    public static String getBllgMtrMapLvlNum(String glblCmpyCd, String mdlMtrLbCd, String bllgMtrLbCd, String dsContrCatgCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal prcMtrPkgPk) {
        if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return null;
        }
        List<Map<String, Object>> selBllgMtrMapList = null;
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            selBllgMtrMapList = getBllgMtrMap(glblCmpyCd, mdlMtrLbCd, bllgMtrLbCd, null, dsContrDtlPk, slsDt, prcMtrPkgPk);
        } else {
            selBllgMtrMapList = NSAL0320Query.getInstance().getBllgMtrLbForFltAgg(glblCmpyCd, slsDt, mdlMtrLbCd, dsContrCatgCd, bllgMtrLbCd, dsContrDtlPk, prcMtrPkgPk);
        }
        if (selBllgMtrMapList.isEmpty()) {
            return null;
        } else {
            Map<String, Object> selBllgMtrMap = selBllgMtrMapList.get(0);
            return (String) selBllgMtrMap.get("BLLG_MTR_MAP_LVL_NUM");
        }
    }
    // END 2016/06/08 T.Kanasaka [QC#5005, MOD]
    // mod end 2016/07/26 CSA Defect#12106

    public static boolean isEqualStr(String a, String b) {
        if (!ZYPCommonFunc.hasValue(a) && !ZYPCommonFunc.hasValue(b)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(a) && ZYPCommonFunc.hasValue(b) && ZYPCommonFunc.trimTail(a).equals(ZYPCommonFunc.trimTail(b))) {
            return true;
        }
        return false;
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    public static void applyBllblFlg(NSAL0320CMsg cMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).bllblFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllblFlg, ZYPConstant.FLG_OFF_N);
                cMsg.A.no(i).contrMtrMultRate.clear();
                cMsg.A.no(i).bllgMtrLbCd.clear();
                cMsg.A.no(i).bllgMtrMapLvlNum.clear();
            }
        }
    }

    public static TreeSet<BigDecimal> getDsContrBllgMtrPkList(NSAL0320CMsg cMsg) {
        TreeSet<BigDecimal> set = new TreeSet<BigDecimal>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).dsContrBllgMtrPk)) {
                set.add(cMsg.A.no(i).dsContrBllgMtrPk.getValue());
            }
        }
        return set;
    }

    // mod start 2016/07/26 CSA Defect#12106
    // START 2015/10/13 T.Tomita [N/A, ADD]
    // START 2015/12/11 T.Kanasaka [QC#1811, MOD]
    /**
     * getBllgCntrInfo
     * @param glblCmpyCd String
     * @param prcMtrPkgPk BigDecimal
     * @param mdlId BigDecimal
     * @param mdlMtrLbCd String
     * @param dsContrCatgCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getBllgCntrInfo(String glblCmpyCd, BigDecimal prcMtrPkgPk, BigDecimal mdlId, String mdlMtrLbCd, String dsContrCatgCd, BigDecimal dsContrDtlPk, String slsDt) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getBllgCntrInfo(glblCmpyCd, prcMtrPkgPk, mdlId, mdlMtrLbCd, dsContrCatgCd, dsContrDtlPk, slsDt);
        if (rslt != null && rslt.isCodeNormal()) {
            return (List<Map<String, Object>>) rslt.getResultObject();
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }
    // END 2015/12/11 T.Kanasaka [QC#1811, MOD]
    // mod end 2016/07/26 CSA Defect#12106

    // mod start 2016/07/26 CSA Defect#12106
    // mod start 2016/05/24 CSA Defect#4958
    // START 2016/01/07 T.Tomita [QC#2806, MOD]
    /**
     * getBllgMtrLb
     * @param glblCmpyCd String
     * @param bllgMtrLbCdArray String[]
     * @param bllgMtrMapLvlNum String
     * @param mdlMtrLbCd String
     * @param dsContrCatgCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getBllgMtrLb(String glblCmpyCd, String[] bllgMtrLbCdArray, String bllgMtrMapLvlNum, String mdlMtrLbCd, String dsContrCatgCd, BigDecimal dsContrDtlPk, String slsDt) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getBllgMtrLb(glblCmpyCd, bllgMtrLbCdArray, bllgMtrMapLvlNum, mdlMtrLbCd, dsContrCatgCd, dsContrDtlPk, slsDt);
        if (rslt != null && rslt.isCodeNormal()) {
            return (List<Map<String, Object>>) rslt.getResultObject();
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }
    // END 2016/01/07 T.Tomita [QC#2806, MOD]
    // mod start 2016/05/24 CSA Defect#4958
    // mod end 2016/07/26 CSA Defect#12106

    /**
     * getMtrMultRate
     * @param glblCmpyCd String
     * @param prcMtrPkgPk BigDecimal
     * @param bllgMtrLbCd String
     * @param physMtrLbCd String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getMtrMultRate(String glblCmpyCd, BigDecimal prcMtrPkgPk, String bllgMtrLbCd, String physMtrLbCd) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getMtrMultRate(glblCmpyCd, prcMtrPkgPk, bllgMtrLbCd, physMtrLbCd);
        if (rslt != null && rslt.isCodeNormal()) {
            return (List<Map<String, Object>>) rslt.getResultObject();
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * getBllgMtrSchd
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getBllgMtrSchd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        NSAL0320Query query = NSAL0320Query.getInstance();
        S21SsmEZDResult rslt = query.getBllgMtrSchd(glblCmpyCd, dsContrDtlPk);
        if (rslt != null && rslt.isCodeNormal()) {
            return (List<Map<String, Object>>) rslt.getResultObject();
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }
    // END 2015/10/13 T.Tomita [N/A, ADD]
}
