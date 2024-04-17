/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0350.common;

import static business.blap.NSBL0350.constant.NSBL0350Constant.*;
import static business.blap.NSBL0350.constant.NSBL0350Constant.NZZM0000E;
import static business.blap.NSBL0350.constant.NSBL0350Constant.NZZM0001W;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCItem;
import parts.common.EZDMsg;
import business.blap.NSBL0350.NSBL0350CMsg;
import business.blap.NSBL0350.NSBL0350Query;
import business.blap.NSBL0350.NSBL0350SMsg;
import business.db.DS_ORG_UNITTMsg;


/**
 *<pre>
 * Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/30   Hitachi         K.Yamada        Update          CSA QC#6081
 *</pre>
 */
public class NSBL0350CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0350CMsg
     * @param sMsg NSBL0350SMsg
     */
    public static void clearMsg(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        cMsg.orgCd_HT.clear();
        cMsg.xxFromDt_H.clear();
        cMsg.orgDescTxt_H.clear();
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0350CMsg
     * @param sMsg NSBL0350SMsg
     */
    public static void setInitParams(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        findInitItem(cMsg, sMsg);
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

    /**
     * Check existence of Service Group
     * @param cMsg NSBL0350CMsg
     * @return DS_ORG_UNITTMsg
     */
    public static DS_ORG_UNITTMsg isExistSvcGrp(NSBL0350CMsg cMsg) {
        DS_ORG_UNITTMsg result = NSBL0350Query.getInstance().getDsOrgUnit(cMsg.glblCmpyCd.getValue(), cMsg.orgCd_HT.getValue());
        return result;
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0350CMsg
     * @param sMsg NSBL0350SMsg
     */
    public static void getSearchData(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSBL0350Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            hhmmFormat(sMsg);
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0350CMsg
     * @param sMsg NSBL0350SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg, int pageFrom) {

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

    private static void findInitItem(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        // mod start 2016/03/30 CSA Defect#6081
        // Search report by As of date
        S21SsmEZDResult ssmResult = null;
        setValue(cMsg.xxFromDt_H, cMsg.slsDt);
        ssmResult = NSBL0350Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }
            hhmmFormat(sMsg);
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            return;
        }

        // Get latest date of report creation
        String ltstCratDt = NSBL0350Query.getInstance().getLtstCratDt(cMsg);
        if (!hasValue(ltstCratDt)) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        // Search report by latest date of report creation
        setValue(cMsg.xxFromDt_H, ltstCratDt);
        ssmResult = NSBL0350Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }
            hhmmFormat(sMsg);
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
            return;
        }

        cMsg.setMessageInfo(NZZM0000E);
        // mod end 2016/03/30 CSA Defect#6081
    }

    /**
     * hasValueItems
     * @param sMsg NSBL0350SMsg
     * @return boolean
     */
    public static NSBL0350SMsg hhmmFormat(NSBL0350SMsg sMsg) {

        BigDecimal hh = new BigDecimal("60.00");

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (hasValue(sMsg.A.no(i).rspTmCustTaskRate_A)) {

                BigDecimal rspTmCustTaskRate = null;
                BigDecimal rspTmCustTaskRate2 = null;
                rspTmCustTaskRate = sMsg.A.no(i).rspTmCustTaskRate_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmCustTaskRate2 = sMsg.A.no(i).rspTmCustTaskRate_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuffer hhmm = new StringBuffer();

                if (1 == rspTmCustTaskRate.precision()) {
                    hhmm.append('0');
                    hhmm.append(rspTmCustTaskRate);
                } else {
                    hhmm.append(rspTmCustTaskRate);
                }
                hhmm.append(':');
                if (1 == rspTmCustTaskRate2.precision()) {
                    hhmm.append('0');
                    hhmm.append(rspTmCustTaskRate2);
                } else {
                    hhmm.append(rspTmCustTaskRate2);
                }
                sMsg.A.no(i).xxDtTm_A1.setValue(hhmm.toString());
            }

            if (hasValue(sMsg.A.no(i).rspTmAllTaskRate_A)) {

                BigDecimal rspTmAllTaskRate = null;
                BigDecimal rspTmAllTaskRate2 = null;
                rspTmAllTaskRate = sMsg.A.no(i).rspTmAllTaskRate_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmAllTaskRate2 = sMsg.A.no(i).rspTmAllTaskRate_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuffer hhmm2 = new StringBuffer();

                if (1 == rspTmAllTaskRate.precision()) {
                    hhmm2.append('0');
                    hhmm2.append(rspTmAllTaskRate);
                } else {
                    hhmm2.append(rspTmAllTaskRate);
                }
                hhmm2.append(':');
                if (1 == rspTmAllTaskRate2.precision()) {
                    hhmm2.append('0');
                    hhmm2.append(rspTmAllTaskRate2);
                } else {
                    hhmm2.append(rspTmAllTaskRate2);
                }
                sMsg.A.no(i).xxDtTm_A2.setValue(hhmm2.toString());
            }
        }
        return sMsg;
    }
}
