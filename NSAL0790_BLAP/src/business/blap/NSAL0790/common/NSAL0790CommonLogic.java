/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0790.common;

import static business.blap.NSAL0790.constant.NSAL0790Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import business.blap.NSAL0790.NSAL0790CMsg;
import business.blap.NSAL0790.NSAL0790Query;
import business.blap.NSAL0790.NSAL0790SMsg;
import business.blap.NSAL0790.NSAL0790_ACMsg;

/**
 *<pre>
 * Fleet Rollup Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/01   Hitachi         K.Kitachi       Update          QC#17752
 * 2017/03/10   Hitachi         K.Kitachi       Update          QC#17752
 *</pre>
 */
public class NSAL0790CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0790CMsg
     * @param sMsg NSAL0790SMsg
     */
    public static void clearMsg(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        cMsg.dsContrNum_H.clear();
        cMsg.fleetCalcProcDescTxt_H.clear();
        cMsg.vldMsgTxt_H.clear();
        cMsg.dsContrPk_H.clear();
        cMsg.fleetCalcProcCd_H.clear();
        // START 2017/03/10 K.Kitachi [QC#17752, DEL]
//        cMsg.mtrReadDt_H.clear();
        // END 2017/03/10 K.Kitachi [QC#17752, DEL]
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Get Fleet Rollup Info
     * @param cMsg NSAL0790CMsg
     * @param sMsg NSAL0790SMsg
     */
    public static void getFleetRollupInfo(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg) {

        NSAL0790Query.getInstance().getHeaderInfo(cMsg);
        S21SsmEZDResult ssmResult = NSAL0790Query.getInstance().getDetailInfo(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0790CMsg
     * @param sMsg NSAL0790SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0790CMsg cMsg, NSAL0790SMsg sMsg, int pageFrom) {

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
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    // START 2017/03/01 K.Kitachi [QC#17752, ADD]
    /**
     * setTableLayout
     * @param cMsg NSAL0780CMsg
     */
    public static void setTableLayout(NSAL0790CMsg cMsg) {
        if (cMsg.A.getValidCount() == 0) {
            return;
        }
        int baseIdx = 0;
        setValue(cMsg.A.no(baseIdx).xxBtnFlg_A, ZYPConstant.FLG_ON_Y);
        int compIdx = 1;
        for (; compIdx < cMsg.A.getValidCount(); compIdx++) {
            if (isEqualsLineKey(cMsg.A.no(baseIdx), cMsg.A.no(compIdx))) {
                setValue(cMsg.A.no(compIdx).xxBtnFlg_A, ZYPConstant.FLG_OFF_N);
            } else {
                setValue(cMsg.A.no(compIdx).xxBtnFlg_A, ZYPConstant.FLG_ON_Y);
                setValue(cMsg.A.no(baseIdx).xxRowNum_A, BigDecimal.valueOf(compIdx - baseIdx));
                baseIdx = compIdx;
            }
        }
        if (baseIdx != compIdx) {
            setValue(cMsg.A.no(baseIdx).xxRowNum_A, BigDecimal.valueOf(compIdx - baseIdx));
        }
    }

    private static boolean isEqualsLineKey(NSAL0790_ACMsg baseACMsg, NSAL0790_ACMsg compACMsg) {
        if (!isEqualsBigDecimal(baseACMsg.svcMachMstrPk_A.getValue(), compACMsg.svcMachMstrPk_A.getValue())) {
            return false;
        }
        return true;
    }

    private static boolean isEqualsBigDecimal(BigDecimal bd1, BigDecimal bd2) {
        if (!hasValue(bd1) && !hasValue(bd2)) {
            return true;
        }
        if (!hasValue(bd1) && hasValue(bd2)) {
            return false;
        }
        if (hasValue(bd1) && !hasValue(bd2)) {
            return false;
        }
        if (bd1.compareTo(bd2) == 0) {
            return true;
        }
        return false;
    }
    // END 2017/03/01 K.Kitachi [QC#17752, ADD]
}
