/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0760.common;

import static business.blap.NSAL0760.constant.NSAL0760Constant.*;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import business.blap.NSAL0760.NSAL0760CMsg;
import business.blap.NSAL0760.NSAL0760Query;
import business.blap.NSAL0760.NSAL0760SMsg;

/**
 *<pre>
 * Contract Status History
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class NSAL0760CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0760CMsg
     * @param sMsg NSAL0760SMsg
     */
    public static void clearMsg(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        // TODO
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSAL0760CMsg
     * @param sMsg NSAL0760SMsg
     */
    public static void setInitParams(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        // TODO
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NSAL0760CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NSAL0760CMsg cMsg) {
        // TODO
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0760CMsg
     * @param sMsg NSAL0760SMsg
     */
    public static void getSearchData(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0760Query.getInstance().getSearchData(cMsg, sMsg);
        setViewData(sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > sMsg.A.length()
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
     * @param cMsg NSAL0760CMsg
     * @param sMsg NSAL0760SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSAL0760CMsg cMsg, NSAL0760SMsg sMsg, int pageFrom) {

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
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Set View Data processing is executed.
     * @param sMsg Global area information
     */
    public static void setViewData(NSAL0760SMsg sMsg) {

        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        if (sMsg.B.getValidCount() == 0) {
            return;
        }
        int aIdx = 0;
        int bIdx = 0;
        while (bIdx < sMsg.B.getValidCount()) {
            int lvl = sMsg.B.no(bIdx).xxRefCseNum.getValueInt();
            EZDMsg.copy(sMsg.B.no(bIdx), null, sMsg.A.no(aIdx), null);
            BigDecimal brkDsContrDtlPk = sMsg.B.no(bIdx).dsContrDtlPk.getValue();
            aIdx++;
            bIdx++;
            sMsg.A.setValidCount(aIdx);
            if (lvl == LVL20) {
                bIdx = setViewDataLvl3x(aIdx, bIdx, brkDsContrDtlPk, sMsg);
                aIdx = sMsg.A.getValidCount();
            }
        }
        sMsg.A.setValidCount(aIdx);
    }

    private static int setViewDataLvl3x(int aIdx, int bIdx, BigDecimal brkDsContrDtlPk, NSAL0760SMsg sMsg) {
        int st3xIdx = bIdx - 1;
        while (bIdx < sMsg.B.getValidCount()) {
            BigDecimal dsContrDtlPk = sMsg.B.no(bIdx).dsContrDtlPk.getValue();
            int lvl = sMsg.B.no(bIdx).xxRefCseNum.getValueInt();
            if (brkDsContrDtlPk.compareTo(dsContrDtlPk) != 0 || lvl < LVL30) {
                break;
            }
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.B.no(st3xIdx).xxBtnFlg_A1.getValue())) {
                bIdx++;
                continue;
            }

            if (lvl > LVL40) {
                bIdx = setViewDataLvl4x(aIdx, bIdx, brkDsContrDtlPk, sMsg);
                aIdx = sMsg.A.getValidCount();
            } else {
                EZDMsg.copy(sMsg.B.no(bIdx), null, sMsg.A.no(aIdx), null);
                aIdx++;
                bIdx++;
                sMsg.A.setValidCount(aIdx);
            }
        }
        return bIdx;
    }

    private static int setViewDataLvl4x(int aIdx, int bIdx, BigDecimal brkDsContrDtlPk, NSAL0760SMsg sMsg) {
        int st4xIdx = bIdx - 1;
        while (bIdx < sMsg.B.getValidCount()) {
            BigDecimal dsContrDtlPk = sMsg.B.no(bIdx).dsContrDtlPk.getValue();
            int lvl = sMsg.B.no(bIdx).xxRefCseNum.getValueInt();
            if (brkDsContrDtlPk.compareTo(dsContrDtlPk) != 0 || lvl < LVL40) {
                break;
            }
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.B.no(st4xIdx).xxBtnFlg_A2.getValue())) {
                bIdx++;
                continue;
            }
            EZDMsg.copy(sMsg.B.no(bIdx), null, sMsg.A.no(aIdx), null);
            aIdx++;
            bIdx++;
            sMsg.A.setValidCount(aIdx);
        }
        return bIdx;
    }
}
