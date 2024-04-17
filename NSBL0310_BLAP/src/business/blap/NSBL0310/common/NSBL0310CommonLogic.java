/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0310.common;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import parts.common.EZDMsg;
import business.blap.NSBL0310.NSBL0310CMsg;
import business.blap.NSBL0310.NSBL0310Query;
import business.blap.NSBL0310.NSBL0310SMsg;
import business.db.DS_ORG_UNITTMsg;

/**
 * <pre>
 * Service Request By Manager
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Harada        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          CSA QC#5738
 *</pre>
 */
public class NSBL0310CommonLogic {

    /**
     * copy bizMsg to shareMsg
     * @param bizMsg NSBL0310CMsg
     * @param shareMsg NSBL0310SMsg
     */
    public static void copyBizToShare(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {

        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (pageFrom + j < shareMsg.A.length()) {
                EZDMsg.copy(bizMsg.A.no(j), null, shareMsg.A.no(pageFrom + j), null);
            } else {
                break;
            }
        }
    }

    /**
     * copy shareMsg to bizMsg
     * @param bizMsg NSBL0310CMsg
     * @param shareMsg NSBL0310SMsg
     */
    public static void copyShareToBiz(NSBL0310CMsg bizMsg, NSBL0310SMsg shareMsg) {
        int pageFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (pageFrom + j < shareMsg.A.length()) {
                EZDMsg.copy(shareMsg.A.no(pageFrom + j), null, bizMsg.A.no(j), null);
            } else {
                break;
            }
        }
    }

    /**
     * Check existence of Service Group
     * @param cMsg NSBL0310CMsg
     * @return DS_ORG_UNITTMsg
     */
    public static DS_ORG_UNITTMsg isExistSvcGrp(NSBL0310CMsg cMsg) {
        DS_ORG_UNITTMsg result = NSBL0310Query.getInstance().getDsOrgUnit(cMsg.glblCmpyCd.getValue(), cMsg.orgCd_HT.getValue());
        return result;
    }

    /**
     * sumResult
     * @param shareMsg NSBL0310SMsg
     */
    public static void sumResult(NSBL0310SMsg shareMsg) {

        BigDecimal sumXxRcvTm = new BigDecimal("0.00");

        for (int i = 0; i < shareMsg.A.getValidCount(); i++) {

            BigDecimal rcvTm = new BigDecimal(shareMsg.A.no(i).xxRcvTm_A.getValue());
            sumXxRcvTm = sumXxRcvTm.add(shareMsg.A.no(i).svcRspTmNum_A.getValue().multiply(rcvTm));

        }
        BigDecimal sumRcvTm = new BigDecimal(shareMsg.xxRcvTm_T.getValue());

        // mod start 2016/03/22 CSA Defect#5738
        if (ZYPCommonFunc.hasValue(sumRcvTm) && sumRcvTm.compareTo(BigDecimal.ZERO) > 0) {
            sumXxRcvTm = sumXxRcvTm.divide(sumRcvTm, 0, BigDecimal.ROUND_HALF_UP);
        } else {
            sumXxRcvTm = BigDecimal.ZERO;
        }
        // mod end 2016/03/22 CSA Defect#5738

        ZYPEZDItemValueSetter.setValue(shareMsg.xxRcvTm_T, sumXxRcvTm.toString());
    }

    /**
     * sumResult
     * @param shareMsg NSBL0310SMsg
     */
    public static void managerFormat(NSBL0310SMsg shareMsg) {

        for (int i = 0; i < shareMsg.A.getValidCount(); i++) {

            StringBuilder manager = new StringBuilder();
            manager.append(shareMsg.A.no(i).psnLastNm_A.getValue());
            manager.append(',');
            manager.append(' ');
            manager.append(shareMsg.A.no(i).psnFirstNm_A.getValue());

            String managerFormat = manager.toString();
            ZYPEZDItemValueSetter.setValue(shareMsg.A.no(i).mgrNm_A, managerFormat);
        }
    }

    /**
     * sumResult
     * @param shareMsg NSBL0310SMsg
     */
    public static void hhmmFormat(NSBL0310SMsg shareMsg) {

        BigDecimal hh = new BigDecimal("60.00");

        for (int i = 0; i < shareMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(shareMsg.A.no(i).svcRspTmNum_A)) {

                BigDecimal svcRspTmNum = null;
                BigDecimal svcRspTmNum2 = null;
                svcRspTmNum = shareMsg.A.no(i).svcRspTmNum_A.getValue().divide(hh, 0, BigDecimal.ROUND_DOWN);
                svcRspTmNum2 = shareMsg.A.no(i).svcRspTmNum_A.getValue().remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

                StringBuilder hhmm = new StringBuilder();

                if (1 == svcRspTmNum.precision()) {
                    hhmm.append('0');
                    hhmm.append(svcRspTmNum);
                } else {
                    hhmm.append(svcRspTmNum);
                }
                hhmm.append(':');
                if (1 == svcRspTmNum2.precision()) {
                    hhmm.append('0');
                    hhmm.append(svcRspTmNum2);
                } else {
                    hhmm.append(svcRspTmNum2);
                }

                ZYPEZDItemValueSetter.setValue(shareMsg.A.no(i).xxRcvTm_A, hhmm.toString());
            }
        }
        BigDecimal svcRspTmNum = new BigDecimal(shareMsg.xxRcvTm_T.getValue());
        BigDecimal svcRspTmNum2 = new BigDecimal(shareMsg.xxRcvTm_T.getValue());
        svcRspTmNum = svcRspTmNum.divide(hh, 0, BigDecimal.ROUND_DOWN);
        svcRspTmNum2 = svcRspTmNum2.remainder(hh).setScale(0, BigDecimal.ROUND_DOWN);

        StringBuilder hhmm = new StringBuilder();

        if (1 == svcRspTmNum.precision()) {
            hhmm.append('0');
            hhmm.append(svcRspTmNum);
        } else {
            hhmm.append(svcRspTmNum);
        }
        hhmm.append(':');
        if (1 == svcRspTmNum2.precision()) {
            hhmm.append('0');
            hhmm.append(svcRspTmNum2);
        } else {
            hhmm.append(svcRspTmNum2);
        }

        ZYPEZDItemValueSetter.setValue(shareMsg.xxRcvTm_T, hhmm.toString());

    }
}
