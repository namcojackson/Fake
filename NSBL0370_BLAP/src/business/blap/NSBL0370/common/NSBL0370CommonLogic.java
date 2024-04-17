/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0370.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import static business.blap.NSBL0370.constant.NSBL0370Constant.NUM_7;
import static business.blap.NSBL0370.constant.NSBL0370Constant.NUM_8;
import static business.blap.NSBL0370.constant.NSBL0370Constant.NUM_10;
import static business.blap.NSBL0370.constant.NSBL0370Constant.NUM_12;
import parts.common.EZDMsg;
import business.blap.NSBL0370.NSBL0370CMsg;
import business.blap.NSBL0370.NSBL0370SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Hourly Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0370CommonLogic {

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0370CMsg
     * @param sMsg NSBL0370SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0370CMsg cMsg, NSBL0370SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSBL0370CMsg cMsg, NSBL0370SMsg sMsg, int pageFrom) {

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
     * hhmmFormat
     * @param sMsg NSBL0370SMsg
     * @return NSBL0370SMsg
     */
    public static NSBL0370SMsg hhmmFormat(NSBL0370SMsg sMsg) {

        BigDecimal hh = new BigDecimal("60.00");

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rspTmCustTaskRate_A)) {

                BigDecimal rspTmCustTaskRate = null;
                BigDecimal rspTmCustTaskRate2 = null;
                rspTmCustTaskRate = sMsg.A.no(i).rspTmCustTaskRate_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmCustTaskRate2 = sMsg.A.no(i).rspTmCustTaskRate_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuilder hhmm = new StringBuilder();

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

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rspTmAllTaskRate_A)) {

                BigDecimal rspTmAllTaskRate = null;
                BigDecimal rspTmAllTaskRate2 = null;
                rspTmAllTaskRate = sMsg.A.no(i).rspTmAllTaskRate_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                rspTmAllTaskRate2 = sMsg.A.no(i).rspTmAllTaskRate_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuilder hhmm2 = new StringBuilder();

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

        if (ZYPCommonFunc.hasValue(sMsg.B.no(0).rspTmCustTaskRate_B)) {

            BigDecimal rspTmCustTaskRate = null;
            BigDecimal rspTmCustTaskRate2 = null;
            rspTmCustTaskRate = sMsg.B.no(0).rspTmCustTaskRate_B.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
            rspTmCustTaskRate2 = sMsg.B.no(0).rspTmCustTaskRate_B.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

            StringBuilder hhmm = new StringBuilder();

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
            sMsg.B.no(0).xxDtTm_B1.setValue(hhmm.toString());
        }

        if (ZYPCommonFunc.hasValue(sMsg.B.no(0).rspTmAllTaskRate_B)) {

            BigDecimal rspTmAllTaskRate = null;
            BigDecimal rspTmAllTaskRate2 = null;
            rspTmAllTaskRate = sMsg.B.no(0).rspTmAllTaskRate_B.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
            rspTmAllTaskRate2 = sMsg.B.no(0).rspTmAllTaskRate_B.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

            StringBuilder hhmm2 = new StringBuilder();

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
            sMsg.B.no(0).xxDtTm_B2.setValue(hhmm2.toString());
        }

        sMsg = hhmmAMPMFormat(sMsg);

        return sMsg;
    }

    /**
     * hasValueItems
     * @param sMsg NSBL0370SMsg
     * @return boolean
     */
    public static NSBL0370SMsg removeMsg(NSBL0370SMsg sMsg) {

      setValue(sMsg.B.no(0).orgDescTxt_B, sMsg.A.no(0).orgDescTxt_A);
      setValue(sMsg.B.no(0).totInProcTaskCnt_B, sMsg.A.no(0).totInProcTaskCnt_A);
      setValue(sMsg.B.no(0).prtWaitTaskCnt_B, sMsg.A.no(0).prtWaitTaskCnt_A);
      setValue(sMsg.B.no(0).spclWaitTaskCnt_B, sMsg.A.no(0).spclWaitTaskCnt_A);
      setValue(sMsg.B.no(0).othOpenTaskCnt_B, sMsg.A.no(0).othOpenTaskCnt_A);
      setValue(sMsg.B.no(0).custTaskCnt_B, sMsg.A.no(0).custTaskCnt_A);
      setValue(sMsg.B.no(0).esclTaskCnt_B, sMsg.A.no(0).esclTaskCnt_A);
      setValue(sMsg.B.no(0).cratTaskCnt_B, sMsg.A.no(0).cratTaskCnt_A);
      setValue(sMsg.B.no(0).cratTaskPerTechRate_B, sMsg.A.no(0).cratTaskPerTechRate_A);
      setValue(sMsg.B.no(0).aftHourTaskCnt_B, sMsg.A.no(0).aftHourTaskCnt_A);
      setValue(sMsg.B.no(0).aftHourTaskPerTechRate_B, sMsg.A.no(0).aftHourTaskPerTechRate_A);
      setValue(sMsg.B.no(0).cloTaskCnt_B, sMsg.A.no(0).cloTaskCnt_A);
      setValue(sMsg.B.no(0).cloTaskPerTechRate_B, sMsg.A.no(0).cloTaskPerTechRate_A);
      setValue(sMsg.B.no(0).prtFailCnt_B, sMsg.A.no(0).prtFailCnt_A);
      setValue(sMsg.B.no(0).postEntryTaskCnt_B, sMsg.A.no(0).postEntryTaskCnt_A);
      setValue(sMsg.B.no(0).avalTechCnt_B, sMsg.A.no(0).avalTechCnt_A);
      setValue(sMsg.B.no(0).rspTmCustTaskRate_B, sMsg.A.no(0).rspTmCustTaskRate_A);
      setValue(sMsg.B.no(0).rspTmAllTaskRate_B, sMsg.A.no(0).rspTmAllTaskRate_A);
      setValue(sMsg.B.no(0).xxDtTm_B1, sMsg.A.no(0).xxDtTm_A1);
      setValue(sMsg.B.no(0).xxDtTm_B2, sMsg.A.no(0).xxDtTm_A2);
      setValue(sMsg.B.no(0).orgCd_B, sMsg.A.no(0).orgCd_A);
      setValue(sMsg.B.no(0).orgLayerNum_B, sMsg.A.no(0).orgLayerNum_A);
      sMsg.B.setValidCount(1);

        return sMsg;
    }

    /**
     * hhmmFormat
     * @param sMsg NSBL0370SMsg
     * @return NSBL0370SMsg
     */
    public static NSBL0370SMsg hhmmAMPMFormat(NSBL0370SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).snapShotDtTmTs_A)) {

                String hhmm = sMsg.A.no(i).snapShotDtTmTs_A.getValue();

                String hh = hhmm.substring(NUM_8, NUM_10);
                String ampm = null;
                if (NUM_12 <= Integer.parseInt(hh)) {
                    ampm = "PM";
                    hh = String.valueOf(Integer.parseInt(hh) - NUM_12);
                } else {
                    ampm = "AM";
                }

                StringBuilder hhmmAMPM = new StringBuilder(NUM_7);
                if (hh.length() == 1) {
                    hhmmAMPM.append('0');
                }
                hhmmAMPM.append(hh);
                hhmmAMPM.append(':');
                hhmmAMPM.append(hhmm.substring(NUM_10, NUM_12));
                hhmmAMPM.append(ampm);

                String hhmmAMPM2 = hhmmAMPM.toString();

                setValue(sMsg.A.no(i).xxDtTm_A3, hhmmAMPM2);
            }
        }
        return sMsg;
    }
}
