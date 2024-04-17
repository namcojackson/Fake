/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7130.common;

import static business.blap.NMAL7130.constant.NMAL7130Constant.HIGH_VAL_DT;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM0072E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCStringItem;
import parts.common.EZDMsg;

import business.blap.NMAL7130.NMAL7130CMsg;
import business.blap.NMAL7130.NMAL7130Query;
import business.blap.NMAL7130.NMAL7130SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7130CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130CommonLogic {

    /**
     * checkOrdTpAndSetCodeBK.
     * @param bizMsg NMAL7130CMsg
     * @param ordTpItem EZDCStringItem
     * @param ordTpItemBk EZDCStringItem
     * @return boolean
     */
    public static boolean checkOrdTpAndSetCodeBK(NMAL7130CMsg bizMsg, EZDCStringItem ordTpItem, EZDCStringItem ordTpItemBk) {
        String[] ordTpAry = ordTpItem.getValue().split(bizMsg.addCharTxt.getValue());
        List<String> ordTpList = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        S21SsmEZDResult ssmResult = null;
        for (String ordTpNm : ordTpAry) {
            ssmResult = NMAL7130Query.getInstance().getAnyColmn("DS_ORD_CATG_CD", "DS_ORD_CATG", "DS_ORD_CATG_DESC_TXT", ordTpNm);
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                ordTpItem.setErrorInfo(1, ZZZM9006E, new String[] {ordTpNm});
                return false;
            }

            if (ordTpList.contains(ordTpNm)) {
                ordTpItem.setErrorInfo(1, NMAM0072E, new String[] {ordTpNm});
                return false;
            } else {
                ordTpList.add(ordTpNm);
            }

            if (isFirst) {
                sb = sb.append((String) ssmResult.getResultObject());
                isFirst = false;
            } else {
                sb = sb.append(bizMsg.addCharTxt.getValue()).append((String) ssmResult.getResultObject());
            }
        }

        ZYPEZDItemValueSetter.setValue(ordTpItemBk, sb.toString());

        return true;
    }

    /**
     * toStr.
     * @param val BigDecimal
     * @return String
     */
    public static String toStr(BigDecimal val) {
        if (ZYPCommonFunc.hasValue(val)) {
            return val.toString();
        } else {
            return "";
        }
    }

    /**
     * toHighValDate.
     * @param dateVal String
     * @return String
     */
    public static String toHighValDate(String dateVal) {
        if (ZYPCommonFunc.hasValue(dateVal)) {
            return dateVal;
        }
        return HIGH_VAL_DT;
    }

    /**
     * copy From BizMsg To GlblMsg.
     * @param bizMsg NMAL7130CMsg
     */
    public static void copyFromBizMsgToGlblMsg(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {
        // Header
        EZDMsg.copy(bizMsg, null, glblMsg, null);

        // Detail
        EZDMsg.copy(bizMsg.D, null, glblMsg.D, null);
    }

    /**
     * clear GlblMsg.
     * @param bizMsg NMAL7130CMsg
     */
    public static void clearGlblMsg(NMAL7130SMsg glblMsg) {
        ZYPTableUtil.clear(glblMsg.D);
        glblMsg.clear();

        glblMsg.dsAcctNum_C1.clear();
        glblMsg.initFdRate_C1.clear();

        glblMsg.prcTermCondVrsnNum_E1.clear();

        glblMsg.prcTermCondVrsnNum_E1.clear();
        glblMsg.prcTermCondStsCd_E1.clear();
        glblMsg.ordTrxFlexPct_E1.clear();
        glblMsg.allwDclnMaintFlg_E1.clear();
        glblMsg.mustUseEquipPrcFlg_E1.clear();
        glblMsg.leaseRtrnInclInPrcFlg_E1.clear();
        glblMsg.ovrdSysTonerTpFlg_E1.clear();
        glblMsg.billTonerFrtChrgFlg_E1.clear();
        glblMsg.tonerAlwncPct_E1.clear();
        glblMsg.nonStdStartTm_E1.clear();
        glblMsg.lnrEttlFlg_E1.clear();
        glblMsg.maxDownTmDaysAot_E1.clear();
        glblMsg.lflReplOptFlg_E1.clear();
        glblMsg.lflReplTermNum_E1.clear();
        glblMsg.unltdTngReqFlg_E1.clear();
        glblMsg.custPrvtyFlg_E1.clear();
        glblMsg.hddSvcInclFlg_E1.clear();
        glblMsg.hddCleanPrcGtdFlg_E1.clear();
        glblMsg.tmAndMatUplftTxt_E1.clear();
        glblMsg.docReqFrmAgmtNm_E1.clear();
        glblMsg.mstrAgmtDocNm_E1.clear();
        glblMsg.mstrReplAquFlg_E1.clear();
        glblMsg.mstrReplCmbnPrchFlg_E1.clear();
        glblMsg.mstrReplLeaseFlg_E1.clear();
        glblMsg.leaseTrxAllwFlg_E1.clear();
        glblMsg.supplTermCmpyStdFrmTxt_E1.clear();
        glblMsg.aftHourBillRate_E1.clear();
        glblMsg.rspTmMeasPerCd_E1.clear();
        glblMsg.rspTmComitTxt_E1.clear();
        glblMsg.svcEtaCallReqHrsNum_E1.clear();
        glblMsg.tonerTpNm_E1.clear();
        glblMsg.tonerYieldCnt_E1.clear();
        glblMsg.stplInclSvcFlg_E1.clear();
        glblMsg.prcContrPrcTpCd_E1.clear();
        glblMsg.dlyFirstCallGtdFlg_E1.clear();
        glblMsg.onSiteTechInclFlg_E1.clear();
        glblMsg.primTechInclFlg_E1.clear();
        glblMsg.iwrEsclFlg_E1.clear();
        glblMsg.maxRnwIncrAmtRate_E1.clear();
        glblMsg.maxStdAnnIncrPct_E1.clear();
        glblMsg.erlTrmnOptFlg_E1.clear();
        glblMsg.upTmGtdPct_E1.clear();
        glblMsg.fleetContrAllwFlg_E1.clear();
        glblMsg.aggrContrAllwFlg_E1.clear();
        glblMsg.custQtlyBizRvwReqFlg_E1.clear();
        glblMsg.stdQtlyBizRvwReqFlg_E1.clear();
        glblMsg.reqRptIntvlCd_E1.clear();
        
    }
}
