/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0780.common;

import static business.blap.NSAL0780.constant.NSAL0780Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCItem;
import parts.common.EZDMsg;
import business.blap.NSAL0780.NSAL0780CMsg;
import business.blap.NSAL0780.NSAL0780Query;
import business.blap.NSAL0780.NSAL0780SMsg;
import business.blap.NSAL0780.NSAL0780_ACMsg;
import business.blap.NSAL0780.NSAL0780_ASMsg;

/**
 *<pre>
 * Fleet Rollup Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Hitachi         A.Kohinata      Create          N/A
 * 2017/03/01   Hitachi         K.Kitachi       Update          QC#17752
 *</pre>
 */
public class NSAL0780CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     */
    public static void clearMsg(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * HasValue Search Condition
     * @param cMsg NSAL0780CMsg
     * @return boolean
     */
    public static boolean hasValueSearchCondition(NSAL0780CMsg cMsg) {

        if (hasValueItems(cMsg.dsAcctNm_H, cMsg.dsAcctNum_H, cMsg.serNum_H, cMsg.serNum_H, cMsg.dsContrNum_H, cMsg.bllgFromDt_H, cMsg.bllgThruDt_H, cMsg.xxChkBox_H1, cMsg.xxChkBox_H2)) {
            return true;
        }
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     */
    public static void getSearchData(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0780Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
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
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg, int pageFrom) {

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

    /**
     * hasValueItems
     * @param items EZDCItem
     * @return boolean
     */
    public static boolean hasValueItems(EZDCItem... items) {

        for (EZDCItem item : items) {
            if (hasValue(item)) {
                return true;
            }
        }
        return false;
    }

    // START 2017/03/01 K.Kitachi [QC#17752, ADD]
    /**
     * setTableLayout
     * @param cMsg NSAL0780CMsg
     */
    public static void setTableLayout(NSAL0780CMsg cMsg) {
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

    private static boolean isEqualsLineKey(NSAL0780_ACMsg baseACMsg, NSAL0780_ACMsg compACMsg) {
        if (!isEqualsBigDecimal(baseACMsg.dsContrPk_A.getValue(), compACMsg.dsContrPk_A.getValue())) {
            return false;
        }
        if (ZYPDateUtil.compare(baseACMsg.bllgFromDt_A.getValue(), compACMsg.bllgFromDt_A.getValue()) != 0) {
            return false;
        }
        if (ZYPDateUtil.compare(baseACMsg.bllgThruDt_A.getValue(), compACMsg.bllgThruDt_A.getValue()) != 0) {
            return false;
        }
        if (!isEqualsBigDecimal(baseACMsg.svcCrRebilDtlPk_A.getValue(), compACMsg.svcCrRebilDtlPk_A.getValue())) {
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

    // START 2017/03/10 K.Kitachi [QC#17752, ADD]
    /**
     * createParamForDetailButton
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     */
    public static void createParamForDetailButton(NSAL0780CMsg cMsg, NSAL0780SMsg sMsg) {
        int rowNum = cMsg.xxRowNum_H.getValueInt();
        int pIdx = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (isEqualsLineKey(cMsg.A.no(rowNum), sMsg.A.no(i))) {
                setValue(cMsg.P.no(pIdx).fleetReadRollUpPk_P, sMsg.A.no(i).fleetReadRollUpPk_A);
                pIdx++;
            }
        }
        cMsg.P.setValidCount(pIdx);
    }

    private static boolean isEqualsLineKey(NSAL0780_ACMsg baseACMsg, NSAL0780_ASMsg compASMsg) {
        if (!isEqualsBigDecimal(baseACMsg.dsContrPk_A.getValue(), compASMsg.dsContrPk_A.getValue())) {
            return false;
        }
        if (ZYPDateUtil.compare(baseACMsg.bllgFromDt_A.getValue(), compASMsg.bllgFromDt_A.getValue()) != 0) {
            return false;
        }
        if (ZYPDateUtil.compare(baseACMsg.bllgThruDt_A.getValue(), compASMsg.bllgThruDt_A.getValue()) != 0) {
            return false;
        }
        if (!isEqualsBigDecimal(baseACMsg.svcCrRebilDtlPk_A.getValue(), compASMsg.svcCrRebilDtlPk_A.getValue())) {
            return false;
        }
        return true;
    }
    // END 2017/03/01 K.Kitachi [QC#17752, ADD]
}
