/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1620.common;

import static business.blap.NWAL1620.constant.NWAL1620Constant.NUMCONST_NUM_OF_COPY;
import static business.blap.NWAL1620.constant.NWAL1620Constant.NWAM0370E;
import static business.blap.NWAL1620.constant.NWAL1620Constant.NWAM0695E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1620.NWAL1620CMsg;
import business.blap.NWAL1620.NWAL1620Query;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1620CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1620CommonLogic {

    /**
     * existCpoConfigLine
     * @param bizMsg Business Msg
     * @return boolean
     */
    public static boolean existCpoConfigLine(NWAL1620CMsg bizMsg) {

        if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd.getValue())) {
            int cntDsCpoDtl = NWAL1620Query.getInstance().getCntDsCpoDtl(bizMsg);
            if (cntDsCpoDtl < 1) {
                return false;
            }
        } else {
            int cntDsCpoRtrnDtl = NWAL1620Query.getInstance().getCntDsCpoRtrnDtl(bizMsg);
            if (cntDsCpoRtrnDtl < 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * checkNumOfCopy
     * @param bizMsg Business Msg
     * @return boolean
     */
    public static boolean checkNumOfCopy(NWAL1620CMsg bizMsg) {

        if (bizMsg.xxQty10Num.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            bizMsg.xxQty10Num.setErrorInfo(1, NWAM0370E);
            return false;
        }

        BigDecimal maxCpyNum = ZYPCodeDataUtil.getNumConstValue(NUMCONST_NUM_OF_COPY, bizMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(maxCpyNum)) {
            if (bizMsg.xxQty10Num.getValue().compareTo(maxCpyNum) > 0) {
                bizMsg.xxQty10Num.setErrorInfo(1, NWAM0695E, new String[] {"Number of copies", maxCpyNum.toString() });
                return false;
            }
        }

        return true;
    }

    /**
     * Method name: getConfigCatg
     * @param bizMsg NWAL1620CMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean getConfigCatg(NWAL1620CMsg bizMsg) {

        // call EZTblAccessor
        S21SsmEZDResult ssmResultLineSrc = NWAL1620Query.getInstance().getConfigCatg(bizMsg);

        if (!ssmResultLineSrc.isCodeNormal()) {
            return false;
        }

        List<Map<String, String>> resultLineSrcList = (List<Map<String, String>>) ssmResultLineSrc.getResultObject();
        int idx = 0;

        for (Map<String, String> map : resultLineSrcList) {
            // has over max limit of Array
            if (idx > bizMsg.configCatgCd_CD.length()) {
                break;
            }
            if (!map.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_CD.no(idx), (String) map.get("CONFIG_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.configCatgDescTxt_DI.no(idx), (String) map.get("CONFIG_CATG_DESC_TXT"));
            }
            idx++;
        }

        return true;
    }
}
