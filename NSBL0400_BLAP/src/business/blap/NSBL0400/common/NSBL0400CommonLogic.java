/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0400.common;

import static business.blap.NSBL0400.constant.NSBL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MNF;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDMsg;
import business.blap.NSBL0400.NSBL0400CMsg;
import business.blap.NSBL0400.NSBL0400Query;
import business.blap.NSBL0400.NSBL0400SMsg;

/**
 *<pre>
 * Mods Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Hitachi         M.Gotou         Create          N/A
 * 2016/04/11   Hitachi         M.Gotou         Update          QC#4716
 * 2018/07/04   Fujitsu         T.Ogura         Update          QC#27065
 * 2020/03/02   Fujitsu         S.Kosaka        Update          QC#55899
 *</pre>
 */
public class NSBL0400CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0400CMsg
     * @param sMsg NSBL0400SMsg
     */
    public static void clearMsg(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {
        cMsg.clear();
        cMsg.A.clear();
        sMsg.A.clear();
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0400CMsg
     */
    public static void createPullDown(NSBL0400CMsg cMsg) {
        // mod start 2016/04/11 CSA Defect#4716
        ZYPCodeDataUtil.createPulldownList(SVC_MNF.class, cMsg.svcMnfCd_01, cMsg.xxDplyByCdNmCnctTxt_01, ":");
        // mod end 2016/04/11 CSA Defect#4716
        // 2020/03/02 QC#55899 Mod Start
        // START 2018/07/04 T.Ogura [QC#27065,ADD]
        //creatYearPullDown(cMsg);
        // END   2018/07/04 T.Ogura [QC#27065,ADD]
        // 2020/03/02 QC#55899 Mod End
        creatMonthPullDown(cMsg);
        creatDayPullDown(cMsg);
    }

    // 2020/03/02 QC#55899 Del Start
    // START 2018/07/04 T.Ogura [QC#27065,ADD]
//    private static void creatYearPullDown(NSBL0400CMsg cMsg) {
//        int salesDateYear = Integer.valueOf(ZYPDateUtil.getSalesDate().substring(0, 4));
//        int cnt = -5;
//        for (int i = 0; i < 11; i++) {
//            setValue(cMsg.xxDplyByCtrlItemCd_YY.no(i), String.valueOf(salesDateYear + cnt));
//            setValue(cMsg.xxDplyByCtrlItemCdNm_YY.no(i), String.valueOf(salesDateYear + cnt));
//            cnt++;
//        }
//    }
    // END   2018/07/04 T.Ogura [QC#27065,ADD]
    // 2020/03/02 QC#55899 Del End

    private static void creatMonthPullDown(NSBL0400CMsg cMsg) {
        String strVal;
        for (int i = 0; i < INT_MONTH_LST; i++) {
            strVal = String.format("%1$02d", i + 1);
            setValue(cMsg.xxDplyByCtrlItemCd_MM.no(i), strVal);
            setValue(cMsg.xxDplyByCtrlItemCdNm_MM.no(i), strVal);
        }
    }

    private static void creatDayPullDown(NSBL0400CMsg cMsg) {
        String strVal;
        for (int i = 0; i < INT_DAY_LST; i++) {
            strVal = String.format("%1$02d", i + 1);
            setValue(cMsg.xxDplyByCtrlItemCd_DD.no(i), strVal);
            setValue(cMsg.xxDplyByCtrlItemCdNm_DD.no(i), strVal);
        }
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0400CMsg
     * @param sMsg NSBL0400SMsg
     */
    public static void getSearchData(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSBL0400Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            // No result
            cMsg.setMessageInfo(NSBM0123I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * copySMsgTocMsg
     * @param cMsg NSBL0400CMsg
     * @param sMsg NSBL0400SMsg
     */
    public static void copyGlblMsgToBizMsg(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(ixS++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0400CMsg
     * @param sMsg NSBL0400SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSBL0400CMsg cMsg, NSBL0400SMsg sMsg, int pageFrom) {

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

}
