/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1030.common;

import static business.blap.NSAL1030.constant.NSAL1030Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import business.blap.NSAL1030.NSAL1030CMsg;
import business.blap.NSAL1030.NSAL1030Query;
import business.blap.NSAL1030.NSAL1030SMsg;

/**
 *<pre>
 * Contract Invoice Detail Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/29   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1030CommonLogic {

    /**
     * Search Invoice Info
     * @param cMsg NSAL1030CMsg
     * @param sMsg NSAL1030SMsg
     */
    public static void searchInvoiceInfo(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        clearMsg(cMsg, sMsg);
        getInvoiceInfo(cMsg, sMsg);

        pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Set Sequence Number
     * @param sMsg NSAL1030SMsg
     */
    public static void setSeqNum(NSAL1030SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1030CMsg
     * @param sMsg NSAL1030SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg, int pageFrom) {

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
     * Clear Message
     * @param cMsg NSAL1030CMsg
     * @param sMsg NSAL1030SMsg
     */
    private static void clearMsg(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        cMsg.custIncdtId_H.clear();
        cMsg.svcCrRebilStsDescTxt_H.clear();
        cMsg.svcCrRebilDescTxt_H.clear();
        cMsg.svcCrRebilPk_H.clear();
        cMsg.svcCrRebilStsCd_H.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Get Invoice Info
     * @param cMsg NSAL1030CMsg
     * @param sMsg NSAL1030SMsg
     */
    private static void getInvoiceInfo(NSAL1030CMsg cMsg, NSAL1030SMsg sMsg) {

        if (hasValue(cMsg.svcCrRebilPk_P)) {
            NSAL1030Query.getInstance().getHeaderInfo(cMsg);
        }
        S21SsmEZDResult ssmResult = NSAL1030Query.getInstance().getDetailInfo(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
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
}
