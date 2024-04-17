/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1120.common;

import static business.blap.NSAL1120.constant.NSAL1120Constant.END_READ;
import static business.blap.NSAL1120.constant.NSAL1120Constant.END_TEST;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CODE_BASE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.MODE_CODE_USAGE;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NSZM0860E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.NSAM0707E;
import static business.blap.NSAL1120.constant.NSAL1120Constant.PAGE_MODE_INIT;
import static business.blap.NSAL1120.constant.NSAL1120Constant.PAGE_MODE_NEXT;
import static business.blap.NSAL1120.constant.NSAL1120Constant.PAGE_MODE_PREV;
import static business.blap.NSAL1120.constant.NSAL1120Constant.PAGE_MODE_SUBMIT_ERR;
import static business.blap.NSAL1120.constant.NSAL1120Constant.START_READ;
import static business.blap.NSAL1120.constant.NSAL1120Constant.START_TEST;
import static business.blap.NSAL1120.constant.NSAL1120Constant.TABLE_A;
import static business.blap.NSAL1120.constant.NSAL1120Constant.TABLE_B;
import static business.blap.NSAL1120.constant.NSAL1120Constant.TABLE_C;
import static business.blap.NSAL1120.constant.NSAL1120Constant.TEST_COPIES;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.blap.NSAL1120.NSAL1120CMsg;
import business.blap.NSAL1120.NSAL1120SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Meter and Pricing Details
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         O.Okuma         Create          N/A
 * 2017/09/15   Hitachi         E.Kameishi      Update          QC#18636
 *</pre>
 */
public class NSAL1120CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     */
    public static void clearMsg(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        String xxModeCd = cMsg.xxModeCd.getValue();
        BigDecimal svcCrRebilPk = cMsg.svcCrRebilPk.getValue();
        String svcCrRebilStsCd = cMsg.svcCrRebilStsCd.getValue();
        BigDecimal svcCrRebilDtlPk = cMsg.svcCrRebilDtlPk.getValue();
        BigDecimal svcInvLinePk = cMsg.svcInvLinePk.getValue();

        sMsg.clear();
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);

        setValue(cMsg.xxModeCd, xxModeCd);
        setValue(cMsg.svcCrRebilPk, svcCrRebilPk);
        setValue(cMsg.svcCrRebilStsCd, svcCrRebilStsCd);
        setValue(cMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
        setValue(cMsg.svcInvLinePk, svcInvLinePk);
    }

    /**
     * setPagenationForAll <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     */
    public static void setPagenationForAll(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        if (MODE_CODE_BASE.equals(cMsg.xxModeCd.getValue())) {
            setPagenationForBase(cMsg, sMsg);
        } else if (MODE_CODE_USAGE.equals(cMsg.xxModeCd.getValue())) {
            setPagenationForMeter(cMsg, sMsg);
            setPagenationForPricing(cMsg, sMsg);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1120CMsg
     * @param sMsg NSAL1120SMsg
     */
    public static void setPagenation(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            setPagenationForBase(cMsg, sMsg);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            setPagenationForMeter(cMsg, sMsg);
        } else if (TABLE_C.equals(cMsg.xxModeInd.getValue())) {
            setPagenationForPricing(cMsg, sMsg);
        }
    }

    private static void setPagenationForBase(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    private static void setPagenationForMeter(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.B.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.B.getValidCount()) {
                EZDMsg.copy(cMsg.B.get(cnt - pagenationFrom), null, sMsg.B.get(cnt), null);
            } else {
                break;
            }
        }
    }

    private static void setPagenationForPricing(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum_C.getValueInt() - 1;
        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.C.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.C.getValidCount()) {
                EZDMsg.copy(cMsg.C.get(cnt - pagenationFrom), null, sMsg.C.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenationForInit <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void pagenationForInit(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        if (MODE_CODE_BASE.equals(cMsg.xxModeCd.getValue())) {
            pagenationForBase(cMsg, sMsg, PAGE_MODE_INIT);
        } else if (MODE_CODE_USAGE.equals(cMsg.xxModeCd.getValue())) {
            pagenationForMeter(cMsg, sMsg, PAGE_MODE_INIT);
            pagenationFoPricing(cMsg, sMsg, PAGE_MODE_INIT);
        }
    }

    /**
     * pagenationForReview <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void pagenationForReview(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        pagenationFoPricing(cMsg, sMsg, PAGE_MODE_INIT);

    }


    /**
     * pagenationForReview <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void pagenationForSubmitErr(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg) {

        pagenationFoPricing(cMsg, sMsg, PAGE_MODE_SUBMIT_ERR);

    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageMode String<Pagenationn Mode>
     */
    public static void pagenation(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, String pageMode) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            pagenationForBase(cMsg, sMsg, pageMode);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            pagenationForMeter(cMsg, sMsg, pageMode);
        } else if (TABLE_C.equals(cMsg.xxModeInd.getValue())) {
            pagenationFoPricing(cMsg, sMsg, pageMode);
        }
    }

    private static void pagenationForBase(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg, String pageMode) {

        int pageFrom = 0;
        if (PAGE_MODE_NEXT.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1;
        } else if (PAGE_MODE_PREV.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
        }
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
    }

    private static void pagenationForMeter(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg,  String pageMode) {

        int pageFrom = 0;
        if (PAGE_MODE_NEXT.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_B.getValueInt() + cMsg.B.length() - 1;
        } else if (PAGE_MODE_PREV.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_B.getValueInt() - cMsg.B.length() - 1;
        }
        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.B.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
    }

    private static void pagenationFoPricing(NSAL1120CMsg cMsg, NSAL1120SMsg sMsg,  String pageMode) {

        int pageFrom = 0;
        if (PAGE_MODE_NEXT.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_C.getValueInt() + cMsg.C.length() - 1;
        } else if (PAGE_MODE_PREV.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_C.getValueInt() - cMsg.C.length() - 1;
        } else if (PAGE_MODE_SUBMIT_ERR.equals(pageMode)) {
            pageFrom = cMsg.xxPageShowFromNum_C.getValueInt();
        }
        int i = pageFrom;
        for (; i < pageFrom + cMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.get(i), null, cMsg.C.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.C.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_C.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_C.setValue(pageFrom + cMsg.C.getValidCount());
    }

    /**
     * cheackMeterDetails
     * @param cMsg NSAL1120CMsg
     * @return boolean
     */
    public static boolean cheackMeterDetails(NSAL1120CMsg cMsg) {

        boolean errFlg = true;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            BigDecimal startReadMtrCnt;
            BigDecimal endReadMtrCnt;
            //START 2017/09/15 E.Kameishi [QC#18636,MOD]
            //BigDecimal startTestMtrCnt;
            //BigDecimal endTestMtrCnt;
            BigDecimal testMtrCnt;
            //START 2017/09/15 E.Kameishi [QC#18636,MOD]

            if (hasValue(cMsg.B.no(i).newStartReadMtrCnt_B)) {
                startReadMtrCnt = cMsg.B.no(i).newStartReadMtrCnt_B.getValue();
            } else {
                startReadMtrCnt = cMsg.B.no(i).oldStartReadMtrCnt_B.getValue();
            }

            if (hasValue(cMsg.B.no(i).newEndReadMtrCnt_B)) {
                endReadMtrCnt = cMsg.B.no(i).newEndReadMtrCnt_B.getValue();
            } else {
                endReadMtrCnt = cMsg.B.no(i).oldEndReadMtrCnt_B.getValue();
            }

            //START 2017/09/15 E.Kameishi [QC#18636,MOD]
//            if (hasValue(cMsg.B.no(i).newStartTestMtrCnt_B)) {
//                if (hasValue(cMsg.B.no(i).newTestMtrCnt_B)) {
//                startTestMtrCnt = cMsg.B.no(i).newStartTestMtrCnt_B.getValue();
//            } else {
//                startTestMtrCnt = cMsg.B.no(i).oldStartTestMtrCnt_B.getValue();
//            }
//
//            if (hasValue(cMsg.B.no(i).newEndTestMtrCnt_B)) {
//                if (hasValue(cMsg.B.no(i).newTestMtrCnt_B)) {
//                endTestMtrCnt = cMsg.B.no(i).newEndTestMtrCnt_B.getValue();
//            } else {
//                endTestMtrCnt = cMsg.B.no(i).oldEndTestMtrCnt_B.getValue();
//            }
              //START 2017/10/17 E.Kameishi [QC#18636,MOD]
              if (hasValue(cMsg.B.no(i).newTestMtrCnt_B)) {
                  testMtrCnt = cMsg.B.no(i).newTestMtrCnt_B.getValue();
              } else if (hasValue(cMsg.B.no(i).oldTestMtrCnt_B)) {
                  testMtrCnt = cMsg.B.no(i).oldTestMtrCnt_B.getValue();
              } else {
                  testMtrCnt = BigDecimal.ZERO;
              }
              //END 2017/10/17 E.Kameishi [QC#18636,MOD]
              //END 2017/09/15 E.Kameishi [QC#18636,MOD]

            if (endReadMtrCnt.compareTo(startReadMtrCnt) == -1) {
                cMsg.B.no(i).newStartReadMtrCnt_B.setErrorInfo(1, NSZM0860E, new String[] {END_READ, START_READ });
                cMsg.B.no(i).newEndReadMtrCnt_B.setErrorInfo(1, NSZM0860E, new String[] {END_READ, START_READ });
                errFlg = false;
                continue;
            }

            // START 2017/09/15 E.Kameishi [QC#18636,DEL]
//            if (endTestMtrCnt.compareTo(startTestMtrCnt) == -1) {
//                cMsg.B.no(i).newStartTestMtrCnt_B.setErrorInfo(1, NSZM0860E, new String[] {END_TEST, START_TEST });
//                cMsg.B.no(i).newEndTestMtrCnt_B.setErrorInfo(1, NSZM0860E, new String[] {END_TEST, START_TEST });
//                errFlg = false;
//            }
            // END 2017/09/15 E.Kameishi [QC#18636,DEL]
            // START 2017/09/15 E.Kameishi [QC#18636,ADD]
            BigDecimal subReadMtrCnt = endReadMtrCnt.subtract(startReadMtrCnt);
            if (subReadMtrCnt.compareTo(testMtrCnt) < 0) {
                cMsg.B.no(i).newTestMtrCnt_B.setErrorInfo(1, NSAM0707E, new String[] {TEST_COPIES, START_READ, END_READ });
                errFlg = false;
            }
            // END 2017/09/15 E.Kameishi [QC#18636,ADD]
        }
        return errFlg;
    }

}
