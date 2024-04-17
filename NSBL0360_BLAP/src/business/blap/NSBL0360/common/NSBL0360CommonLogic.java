/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0360.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import business.blap.NSBL0360.NSBL0360CMsg;
import business.blap.NSBL0360.NSBL0360SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 *<pre>
 * Sub Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0360CommonLogic {

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0360CMsg
     * @param sMsg NSBL0360SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg, int pagenationFrom) {

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
    public static void pagenation(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg, int pageFrom) {

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
     * @param sMsg Global area information
     * @return boolean
     */
    public static NSBL0360SMsg hhmmFormat(NSBL0360SMsg sMsg) {

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
            rspTmCustTaskRate = sMsg.B.no(0).rspTmCustTaskRate_B.getValue().divide(hh , 0, BigDecimal.ROUND_DOWN);
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
        return sMsg;
    }

    /**
     * hasValueItems
     * @param sMsg Global area information
     * @param cMsg Business Component Interface Message
     * @return boolean
     */
    public static NSBL0360SMsg removeMsg(NSBL0360SMsg sMsg, NSBL0360CMsg cMsg) {

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
      setValue(sMsg.B.no(0).orgCd_B, cMsg.orgCd_HL);
      setValue(sMsg.B.no(0).orgLayerNum_B, sMsg.A.no(0).orgLayerNum_A);
      sMsg.B.setValidCount(1);

        return sMsg;
    }
}
