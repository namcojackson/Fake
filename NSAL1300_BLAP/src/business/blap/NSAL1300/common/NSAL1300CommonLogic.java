/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1300.common;

import static business.blap.NSAL1300.constant.NSAL1300Constant.NZZM0001W;
import static business.blap.NSAL1300.constant.NSAL1300Constant.SCALE_DIVIDE;
import static business.blap.NSAL1300.constant.NSAL1300Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NSAL1300.NSAL1300CMsg;
import business.blap.NSAL1300.NSAL1300Query;
import business.blap.NSAL1300.NSAL1300SMsg;
import business.blap.NSAL1300.NSAL1300_BSMsg;
import business.db.SVC_CONTR_BLLGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Usage Meter Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 * 2017/06/21   Hitachi         K.Kishimoto     Update          QC#19423
 * 2018/03/07   Hitachi         K.Kojima        Update          QC#24671
 * 2018/11/21   Hitachi         S.Kitamura      Update          QC#29280
 *</pre>
 */
public class NSAL1300CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     */
    public static void clearMsg(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        BigDecimal svcContrBllg_Pk = cMsg.svcContrBllgPk.getValue();
        sMsg.clear();
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        setValue(cMsg.svcContrBllgPk, svcContrBllg_Pk);
        // START 2018/03/07 K.Kojima [QC#24671,ADD]
        setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/03/07 K.Kojima [QC#24671,ADD]
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     */
    public static void setInitParams(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.B.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.B.getValidCount()) {
                EZDMsg.copy(cMsg.B.get(cnt - pagenationFrom), null, sMsg.B.get(cnt), null);
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
    public static void pagenation(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg, int pageFrom) {

        int startIndex = (pageFrom / cMsg.B.length()) * cMsg.B.length();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.B.length(); i++) {
            if (i < sMsg.B.getValidCount()) {
                EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.B.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.B.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
        setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.B.getValidCount()).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));

    }

    /**
     * setUsageMeterData
     * @param cMsg NSAL1300CMsg
     */
    public static void setUsageMeterData(NSAL1300CMsg cMsg) {

        int cnt = 0;
        int xsRengCnt = 0;
        String compMtrLbCd = null;
        for (; cnt < cMsg.A.getValidCount(); cnt++) {
            if (cnt == 0) {
                xsRengCnt++;
                compMtrLbCd = cMsg.A.no(cnt).mtrLbCd_A.getValue();
                if (XS_CHRG_TP.RANGE.equals(cMsg.A.no(cnt).xsChrgTpCd_A.getValue()) &&
                        BigDecimal.ONE.compareTo(cMsg.A.no(cnt).xxDtlCnt_A.getValue()) > 0) {

                    cMsg.A.no(cnt).bllgCopyQty_A.setValue(cMsg.A.no(cnt).xsMtrCopyQty_A.getValue());
                    cMsg.A.no(cnt).mtrChrgDealAmt_A.setValue(cMsg.A.no(cnt).xsMtrChrgDealAmt_A.getValue());
                }
            } else {
                if (!compMtrLbCd.equals(cMsg.A.no(cnt).mtrLbCd_A.getValue())){
                    xsRengCnt = 0;
                    compMtrLbCd = cMsg.A.no(cnt).mtrLbCd_A.getValue();
                }
                if (XS_CHRG_TP.RANGE.equals(cMsg.A.no(cnt).xsChrgTpCd_A.getValue()) &&
                        BigDecimal.ONE.compareTo(cMsg.A.no(cnt).xxDtlCnt_A.getValue()) < 0) {

                    xsRengCnt++;
                    cMsg.A.no(cnt).bllgCopyQty_A.setValue(cMsg.A.no(cnt).xsMtrCopyQty_A.getValue());
                    cMsg.A.no(cnt).mtrChrgDealAmt_A.setValue(cMsg.A.no(cnt).xsMtrChrgDealAmt_A.getValue());
                    if (xsRengCnt > 1) {
                        cMsg.A.no(cnt).mtrLbDescTxt_A.setValue("");
                        cMsg.A.no(cnt).copyInclQty_A.setValue(null);
                        cMsg.A.no(cnt).mtrCopyQty_A.setValue(null);
                        cMsg.A.no(cnt).xxTotAmt_A.setValue(null);
                    }
                }
            }
        }
    }

    /**
     * getErrPageFromNum
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     * @return int
     */
    public static int getErrPageFromNum(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        int errIndex = 0;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            // Error
            if (sMsg.B.no(i).readMtrCnt_BN.isError() || sMsg.B.no(i).mtrEntryCmntTxt_BN.isError()) {
                errIndex = i;
                break;
            }
        }

        return errIndex / cMsg.B.length() * cMsg.B.length();
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NSAL1300CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NSAL1300CMsg cMsg) {

        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        } else if (cMsg.xxPageShowTotNum.getValueInt() < cMsg.xxPageShowCurNum.getValueInt()) {
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
        if (cMsg.B.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.B.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);

    }

    /**
     * initDisplay
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     */
    public static void initDisplay(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg) {

        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = NSAL1300Query.getInstance().getSvcCntrBllg(cMsg);

        S21SsmEZDResult ssmResultDtlA = NSAL1300Query.getInstance().getUsageMeterData(cMsg, svcContrBllgTMsg);

        if (ssmResultDtlA.isCodeNormal()) {
            NSAL1300CommonLogic.setUsageMeterData(cMsg);
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        S21SsmEZDResult ssmResultDtlB =null;
        // Mod Start 06/21/2017 <QC#19423>
        if (DS_CONTR_CATG.FLEET.equals(svcContrBllgTMsg.dsContrCatgCd.getValue())) {
             ssmResultDtlB = NSAL1300Query.getInstance().getFleetUsageMeterDetail(cMsg,sMsg,sMsg.B.length() + 1);
        } else {
             ssmResultDtlB = NSAL1300Query.getInstance().getNotFleetUsageMeterDetail(cMsg,sMsg,sMsg.B.length() + 1);
        }
        // Mod End   06/21/2017 <QC#19423>

        if (ssmResultDtlB.isCodeNormal()) {

            getTotalAdcvRatio(sMsg);

            //Result > 1000
            int queryResCnt = ssmResultDtlB.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        NSAL1300CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.B.getValidCount());
        cMsg.xxPageShowTotNum.setValue(BigDecimal.valueOf(sMsg.B.getValidCount()).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));

    }

    /**
     * getTotalAdcvRatio
     * @param sMsg NSAL1300SMsg
     */
    public static void getTotalAdcvRatio(NSAL1300SMsg sMsg) {

         // adcv
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (i > sMsg.B.length()){
                break;
            }

            NSAL1300_BSMsg bSMsg = (NSAL1300_BSMsg)sMsg.B.get(i);
            int diffDays1 = ZYPDateUtil.getDiffDays(bSMsg.mtrReadDt_B.getValue(), bSMsg.mtrReadDt_BL.getValue());
            BigDecimal diffMtrCnt1 = bSMsg.readMtrCnt_B.getValue().subtract(bSMsg.readMtrCnt_BL.getValue());
            BigDecimal adcv1 = diffMtrCnt1.divide(BigDecimal.valueOf(diffDays1), SCALE_DIVIDE, RoundingMode.HALF_UP);

            S21SsmEZDResult ssmResultStartRead = NSAL1300Query.getInstance().getPastSvcPhysMtrRead((NSAL1300_BSMsg) sMsg.B.get(i));
            BigDecimal adcv2 = adcv1;
            if (ssmResultStartRead != null && ssmResultStartRead.isCodeNormal()) {

                Map<String, Object> ssmMap = (Map<String, Object>) ssmResultStartRead.getResultObject();

                int diffDays2 = ZYPDateUtil.getDiffDays(bSMsg.mtrReadDt_BL.getValue(), ssmMap.get("MTR_READ_DT").toString());
                BigDecimal diffMtrCnt2 = bSMsg.readMtrCnt_BL.getValue().subtract((BigDecimal) ssmMap.get("READ_MTR_CNT"));
                adcv2 = diffMtrCnt2.divide(BigDecimal.valueOf(diffDays2), SCALE_DIVIDE, RoundingMode.HALF_UP);
            }
            BigDecimal totAdcvRatio = BigDecimal.ZERO;
            // START 2018/11/21 S.Kitamura [QC#29280,MOD]
            //if (BigDecimal.ZERO.compareTo(adcv1) != 0 && BigDecimal.ZERO.compareTo(adcv2) != 0) {                        
            if (BigDecimal.ZERO.compareTo(adcv2) != 0) {
                totAdcvRatio = (adcv1.subtract(adcv2)).divide(adcv2, SCALE_DIVIDE, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
            }
            // END 2018/11/21 S.Kitamura [QC#29280,MOD]
            setValue(bSMsg.avgMtrReadCnt_B, totAdcvRatio);
        }

    }

}
