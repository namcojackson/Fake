package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/14   SRAA            N.Otsuji        Create          N/A
 * 2015/10/13   Hitachi         K.Kishimoto     Update          coping with S21 CSA
 * 2016/01/22   Hitachi         T.Tomita        Update          CSA QC#2881
 * 2016/04/26   Hitachi         T.Kanasaka      Update          CSA QC#7056
 * 2017/09/05   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/19   Hitachi         K.Kojima        Update          QC#20869
 * 2017/09/21   Hitachi         K.Kojima        Update          QC#20869
 * 2018/06/04   Hitachi         A.Kohinata      Update          QC#26052
 * 2018/07/23   Hitachi         K.Kishimoto     Update          QC#23863
 * 2018/10/19   Hitachi         K.Kitachi       Update          QC#28733
 * 2019/09/05   Hitachi         K.Kitachi       Update          QC#53247
 * 2019/09/19   Hitachi         A.Kohinata      Update          QC#53403
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 *</pre>
 */
public class NSXC003001SvcPhysMtrRead {
    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLNT = S21SsmBatchClient.getClient(NSXC003001SvcPhysMtrRead.class);

    /** Map Key AMOUNT */
    public static final String AMOUNT = "AMOUNT";

    /** Map Key QTY */
    public static final String QTY = "QTY";

    /** Map Key EXCESS_COPY_AMOUNT */
    public static final String EXCESS_COPY_AMOUNT = "EXCESS_COPY_AMOUNT";

    /** Map Key EXCESS_COPY_PK */
    public static final String EXCESS_COPY_PK = "EXCESS_COPY_PK";

    /**
     * Meter Count Lower Threshold Factor
     */
    private static final String NSXC0030_MTR_LOW_THRHD_FCT_NUM = "NSXC0030_MTR_LOW_THRHD_FCT_NUM";

    // ADD-S 10/13/2015 K.Kishimoto

    /**
     * tolerance limit Value Average Daily Copy Volume　
     */
    private static final String NSXC0030_ADCV_LIMIT_VAL = "NSXC0030_ADCV_LIMIT_VAL";

    /**
     * Start Meter Read Window Before Days
     */
    private static final String START_MTR_READ_WINDOW_BEF_DAYS = "START_MTR_READ_WINDOW_BEF_DAYS";

    /**
     * Start Meter Read Window After Days
     */
    private static final String START_MTR_READ_WINDOW_AFT_DAYS = "START_MTR_READ_WINDOW_AFT_DAYS";

    // ADD-E 10/13/2015 K.Kishimoto

    /**
     * Meter Count Upper Threshold Factor
     */
    private static final String NSXC0030_MTR_UP_THRHD_FCT_NUM = "NSXC0030_MTR_UP_THRHD_FCT_NUM";

    /**
     * Get excess meter charge
     * @param totCopyQty Total copy quantity
     * @param testCopyQty Test copy quantity
     * @param mlyCopyInclPrcQty Included quantity per month
     * @param contrXsCopyList Contract Excess Copy
     * @param mthAot Month
     * @return Excess meter charge
     */
    public static List<Map<String, BigDecimal>> getXsMtrChrg(BigDecimal totCopyQty, BigDecimal testCopyQty, BigDecimal mlyCopyInclPrcQty, List<Map<String, Object>> contrXsCopyList, BigDecimal mthAot) {

        BigDecimal copyInclPrcQty = mlyCopyInclPrcQty.multiply(mthAot).setScale(0, BigDecimal.ROUND_DOWN);

        if (totCopyQty.compareTo(copyInclPrcQty.add(testCopyQty)) <= 0) {
            return null;
        }

        BigDecimal netCopyQty = totCopyQty.subtract(testCopyQty);
        if (netCopyQty.compareTo(BigDecimal.ZERO) < 0) {
            return null;
        }

        List<Map<String, BigDecimal>> xsMtrChrgPrcList = new ArrayList<Map<String, BigDecimal>>();

        int cnt = 0;

        for (Map<String, Object> contrXsCopy : contrXsCopyList) {

            BigDecimal xsQty;
            if (contrXsCopy.get("XS_MTR_COPY_QTY") == null) {
                xsQty = BigDecimal.ZERO;
            } else {
                xsQty = (BigDecimal) contrXsCopy.get("XS_MTR_COPY_QTY");
            }
            xsQty = xsQty.subtract(BigDecimal.ONE).multiply(mthAot).add(BigDecimal.ONE).setScale(0, BigDecimal.ROUND_DOWN);

            if (netCopyQty.compareTo(xsQty) < 0) {

                break;
            } else {

                if (contrXsCopyList.size() > cnt + 1) {

                    Map<String, Object> nextContrXsCopy = contrXsCopyList.get(cnt + 1);

                    BigDecimal nextXsQty = (BigDecimal) nextContrXsCopy.get("XS_MTR_COPY_QTY");
                    nextXsQty = nextXsQty.subtract(BigDecimal.ONE).multiply(mthAot).add(BigDecimal.ONE).setScale(0, BigDecimal.ROUND_DOWN);

                    if (netCopyQty.compareTo(nextXsQty) >= 0) {
                        Map<String, BigDecimal> xsMtrChrgPrcMap = new HashMap<String, BigDecimal>();
                        xsMtrChrgPrcMap.put(AMOUNT, (BigDecimal) contrXsCopy.get("XS_MTR_AMT_RATE"));
                        xsMtrChrgPrcMap.put(QTY, nextXsQty.subtract(xsQty));
                        xsMtrChrgPrcMap.put(EXCESS_COPY_AMOUNT, nextXsQty.subtract(xsQty).multiply((BigDecimal) contrXsCopy.get("XS_MTR_AMT_RATE")).setScale(2, BigDecimal.ROUND_DOWN));
                        xsMtrChrgPrcMap.put(EXCESS_COPY_PK, (BigDecimal) contrXsCopy.get("CONTR_XS_COPY_PK"));
                        xsMtrChrgPrcList.add(xsMtrChrgPrcMap);
                    } else {
                        Map<String, BigDecimal> xsMtrChrgPrcMap = new HashMap<String, BigDecimal>();
                        xsMtrChrgPrcMap.put(AMOUNT, (BigDecimal) contrXsCopy.get("XS_MTR_AMT_RATE"));
                        xsMtrChrgPrcMap.put(QTY, netCopyQty.add(BigDecimal.ONE).subtract(xsQty));
                        xsMtrChrgPrcMap.put(EXCESS_COPY_AMOUNT, netCopyQty.add(BigDecimal.ONE).subtract(xsQty).multiply((BigDecimal) contrXsCopy.get("XS_MTR_AMT_RATE")).setScale(2, BigDecimal.ROUND_DOWN));
                        xsMtrChrgPrcMap.put(EXCESS_COPY_PK, (BigDecimal) nextContrXsCopy.get("CONTR_XS_COPY_PK"));
                        xsMtrChrgPrcList.add(xsMtrChrgPrcMap);
                    }
                } else {
                    Map<String, BigDecimal> xsMtrChrgPrcMap = new HashMap<String, BigDecimal>();
                    xsMtrChrgPrcMap.put(AMOUNT, (BigDecimal) contrXsCopy.get("XS_MTR_AMT_RATE"));
                    xsMtrChrgPrcMap.put(QTY, netCopyQty.add(BigDecimal.ONE).subtract(xsQty));
                    xsMtrChrgPrcMap.put(EXCESS_COPY_AMOUNT, netCopyQty.add(BigDecimal.ONE).subtract(xsQty).multiply((BigDecimal) contrXsCopy.get("XS_MTR_AMT_RATE")).setScale(2, BigDecimal.ROUND_DOWN));
                    xsMtrChrgPrcMap.put(EXCESS_COPY_PK, (BigDecimal) contrXsCopy.get("CONTR_XS_COPY_PK"));
                    xsMtrChrgPrcList.add(xsMtrChrgPrcMap);
                }
            }

            cnt++;
        }

        return xsMtrChrgPrcList;
    }

    /**
     * Calculate average daily copy volume
     * @param glblCmpyCd Global company code
     * @param mtrReadList Meter read. this should be sorted in
     * chronological order
     * @return Calculated ADCV (average daily copy volume)
     */
    // mod start 2017/09/05 QC#15134
    public static BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList) {
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        return strgy.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, 0);
    }
    // mod end 2017/09/05 QC#15134

    /**
     * Calculate average daily copy volume
     * @param glblCmpyCd Global company code
     * @param mtrReadList Meter read. this should be sorted in
     * chronological order
     * @return Calculated ADCV (average daily copy volume)
     */
    // mod start 2017/09/05 QC#15134
    public static BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, SvcPhysMtrReadInfoBean... mtrReadList) {
        if (mtrReadList == null) {
            return null;
        }
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        return strgy.calcAvgDlyCopyVol(glblCmpyCd, Arrays.asList(mtrReadList), 0);
    }
    // mod end 2017/09/05 QC#15134

    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param dt The day
     * @param mtrReadList Meter read. this should be sorted in
     * chronological order
     * @return Estimated meter count
     */
    // mod start 2017/09/05 QC#15134
    public static BigDecimal estMtrCnt(String glblCmpyCd, String dt, List<SvcPhysMtrReadInfoBean> mtrReadList) {
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        return strgy.estMtrCnt(glblCmpyCd, dt, mtrReadList);
    }
    // mod end 2017/09/05 QC#15134

    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param dt The day
     * @param mtrReadList Meter read. this should be sorted in
     * chronological order
     * @return Estimated meter count
     */
    // mod start 2017/09/05 QC#15134
    public static BigDecimal estMtrCnt(String glblCmpyCd, String dt, SvcPhysMtrReadInfoBean... mtrReadList) {
        if (mtrReadList == null) {
            return null;
        }
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        return strgy.estMtrCnt(glblCmpyCd, dt, Arrays.asList(mtrReadList));
    }
    // mod end 2017/09/05 QC#15134

    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param svcPhysMtrPk Service physical meter pk
     * @param dt The day
     * @return Estimated meter count
     */
    public static BigDecimal estMtrCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk, String dt) {
        List<SvcPhysMtrReadInfoBean> mtrReadList = getLastTwoMeterRead(glblCmpyCd, svcPhysMtrPk, null, null, dt);
        if (mtrReadList.size() > 1) {
            // mod start 2017/09/05 QC#15134
            return estMtrCnt(glblCmpyCd, dt, mtrReadList);
            // mod end 2017/09/05 QC#15134
        } else {
            return null;
        }
    }

    /**
     * Get minimum allowed meter count on the day
     * @param glblCmpyCd Global company code
     * @param svcPhysMtrPk Service physical meter pk
     * @param dt The day
     * @return Minimum allowed meter count on the day
     */
    public static BigDecimal getMinAllwMtrCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk, String dt) {
        BigDecimal mtrLowThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_LOW_THRHD_FCT_NUM, glblCmpyCd);
        if (mtrLowThrhdFctNum == null) {
            return null;
        }
        List<SvcPhysMtrReadInfoBean> mtrReadList = getFirstAndLastMeterRead(glblCmpyCd, svcPhysMtrPk, null, null, dt);
        if (mtrReadList.size() == 0) {
            return BigDecimal.ZERO;
        }
        if (mtrReadList.size() == 1) {
            return mtrReadList.get(0).getReadMtrCnt();
        }
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        // mod start 2017/09/05 QC#15134
        BigDecimal avgDlyCopyVol = strgy.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
        // mod end 2017/09/05 QC#15134
        if (avgDlyCopyVol == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean lastMtrReadInfo = mtrReadList.get(mtrReadList.size() - 1);
        BigDecimal allwCopyVol = avgDlyCopyVol.multiply(BigDecimal.valueOf(ZYPDateUtil.getDiffDays(dt, lastMtrReadInfo.getMtrReadDt())));
        BigDecimal minAllwReadMtrCnt = allwCopyVol.multiply(mtrLowThrhdFctNum).add(lastMtrReadInfo.getReadMtrCnt());
        return minAllwReadMtrCnt.setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * Get maximum allowed meter count on the day
     * @param glblCmpyCd Global company code
     * @param svcPhysMtrPk Service physical meter pk
     * @param dt The day
     * @return Maximum allowed meter count on the day
     */
    public static BigDecimal getMaxAllwMtrCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk, String dt) {
        BigDecimal mtrUpThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_UP_THRHD_FCT_NUM, glblCmpyCd);
        if (mtrUpThrhdFctNum == null) {
            return null;
        }
        List<SvcPhysMtrReadInfoBean> mtrReadList = getFirstAndLastMeterRead(glblCmpyCd, svcPhysMtrPk, null, null, dt);
        if (mtrReadList.size() == 0) {
            return BigDecimal.ZERO;
        }
        if (mtrReadList.size() == 1) {
            return null;
        }
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        // mod start 2017/09/05 QC#15134
        BigDecimal avgDlyCopyVol = strgy.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
        // mod end 2017/09/05 QC#15134
        if (avgDlyCopyVol == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean lastMtrReadInfo = mtrReadList.get(mtrReadList.size() - 1);
        BigDecimal allwCopyVol = avgDlyCopyVol.multiply(BigDecimal.valueOf(ZYPDateUtil.getDiffDays(dt, lastMtrReadInfo.getMtrReadDt())));
        BigDecimal maxAllwReadMtrCnt = allwCopyVol.multiply(mtrUpThrhdFctNum).add(lastMtrReadInfo.getReadMtrCnt());
        return maxAllwReadMtrCnt.setScale(0, RoundingMode.HALF_UP);
    }

    // ADD-S 10/13/2015 K.Kishimoto
    /**
     * Get Start Meter svcPhysMtrReadGrpSq
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail pk
     * @param svcPhysMtrPk Service physical meter pk
     * @return boolean
     */
    public static BigDecimal getStartMeterSvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).svcPhysMtrReadGrpSq.getValue();
    }

    /**
     * Get Last Billing Meter svcPhysMtrReadGrpSq
     * @param glblCmpyCd Global company code String
     * @param dsContrDtlPk DS Contract Detail Pk
     * @param dsContrBllgMtrPk DS Contract Billing Meter Pk
     * @param fromDt String
     * @param thruDt String
     * @return boolean
     */
    public static BigDecimal getLastBillingMeterSvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String fromDt, String thruDt) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setConditionValue("bllgSchdFromDt01", fromDt);
        inMsg.setConditionValue("bllgSchdFromDt02", thruDt);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).svcPhysMtrReadGrpSq.getValue();
    }

    // START 2017/09/19 K.Kojima [QC#20869,ADD]
    /**
     * Get Last Staging Meter svcPhysMtrReadGrpSq
     * @param glblCmpyCd Global company code String
     * @param dsContrDtlPk DS Contract Detail Pk
     * @param dsContrBllgMtrPk DS Contract Billing Meter Pk
     * @param fromDt String
     * @param thruDt String
     * @return boolean
     */
    public static BigDecimal getLastStagingMeterSvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String fromDt, String thruDt) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("201");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setConditionValue("bllgSchdFromDt01", fromDt);
        inMsg.setConditionValue("bllgSchdFromDt02", thruDt);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        inMsg.setConditionValue("mtrEntryCpltFlg01", FLG_ON_Y);
        inMsg.setConditionValue("bllgStageFlg01", FLG_ON_Y);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).svcPhysMtrReadGrpSq.getValue();
    }
    // END 2017/09/19 K.Kojima [QC#20869,ADD]

    // START 2018/07/23 [QC#23863]
    /**
     * Get Billing Meter svcPhysMtrReadTmsg
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcMachMstrPk BigDecimal
     * @param svcPhysMtrPk BigDecimal
     * @param fromDt String
     * @param thruDt String
     * @return boolean
     */
    public static SVC_PHYS_MTR_READTMsg getBillingMeterSvcPhysMtrRead(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk, String fromDt, String thruDt) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        inMsg.setConditionValue("mtrReadDt01", fromDt);
        inMsg.setConditionValue("mtrReadDt02", thruDt);
        inMsg.setConditionValue("dsMtrReadTpGrpCd01", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0);
    }
    // END   2018/07/23 [QC#23863]

    /**
     * Check Machine is missing start reads.
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail pk
     * @param startReadDt Start Read Date
     * @return boolean
     */
    public static boolean hasErrMachineMissingStartRead(String glblCmpyCd, BigDecimal dsContrDtlPk, String startReadDt) {
        if (!hasValue(startReadDt)) {
            return false;
        }
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        inMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return false;
        }
        BigDecimal befDays = ZYPCodeDataUtil.getNumConstValue(START_MTR_READ_WINDOW_BEF_DAYS, glblCmpyCd);
        BigDecimal aftDays = ZYPCodeDataUtil.getNumConstValue(START_MTR_READ_WINDOW_AFT_DAYS, glblCmpyCd);
        int intBefDays = 0;
        int intAftDays = 0;
        if (hasValue(befDays)) {
            intBefDays = befDays.intValue();
        }
        if (hasValue(aftDays)) {
            // START 2016/01/22 T.Tomita [QC#2881, MOD]
            intAftDays = aftDays.intValue();
            // END 2016/01/22 T.Tomita [QC#2881, MOD]
        }
        String contrEffFromDt = inMsg.contrEffFromDt.getValue();
        String winBef = ZYPDateUtil.addDays(contrEffFromDt, -intBefDays);
        String winAft = ZYPDateUtil.addDays(contrEffFromDt, intAftDays);

        if (winBef.compareTo(startReadDt) <= 0 && startReadDt.compareTo(contrEffFromDt) <= 0) {
            return false;
        }
        if (contrEffFromDt.compareTo(startReadDt) <= 0 && startReadDt.compareTo(winAft) <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Check current meter reading should be within +/- xx% of the
     * average volume for this meter.
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail Pk
     * @param dsContrBllgMtrPk DS Contract Billing Meter Pk
     * @param svcPhysMtrPk Service physical meter pk
     * @param dt meter Read Date
     * @param mtrCnt read Meter Count
     * @return boolean
     */
    public static boolean hasErrAveVolForMtr(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcPhysMtrPk, String dt, BigDecimal mtrCnt) {
        if (!hasUsageInv(glblCmpyCd, dsContrDtlPk)) {
            return false;
        }
        if (!hasValue(dsContrBllgMtrPk)) {
            return false;
        }
        BigDecimal startPhysMtrReadGrpSq = getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
        if (startPhysMtrReadGrpSq == null) {
            return false;
        }
        SVC_PHYS_MTR_READTMsg mtrMsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, startPhysMtrReadGrpSq);
        if (mtrMsg == null) {
            return false;
        }
        BigDecimal lastPhysMtrReadGrpSq = null;
        DS_CONTR_BLLG_SCHDTMsg inSchdMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inSchdMsg.setSQLID("005");
        inSchdMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inSchdMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inSchdMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inSchdMsg.setConditionValue("bllgSchdFromDt01", mtrMsg.mtrReadDt.getValue());
        inSchdMsg.setConditionValue("bllgSchdFromDt02", dt);
        inSchdMsg.setConditionValue("invFlg01", FLG_ON_Y);
        inSchdMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray schdArray = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inSchdMsg);
        if (schdArray.getValidCount() == 0) {
            return false;
        }
        lastPhysMtrReadGrpSq = schdArray.no(0).svcPhysMtrReadGrpSq.getValue();
        if (lastPhysMtrReadGrpSq == null) {
            return false;
        }

        // add start 2017/09/05 QC#15134
        SVC_PHYS_MTR_READTMsg lastPhysMtrReadTMsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastPhysMtrReadGrpSq);
        BigDecimal lastExchPhysMtrReadGrpSq = getLastExchSvcPhysMtrReadGrpSq(lastPhysMtrReadTMsg, dt);
        if (lastExchPhysMtrReadGrpSq != null) {
            lastPhysMtrReadGrpSq = lastExchPhysMtrReadGrpSq;
            lastPhysMtrReadTMsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastExchPhysMtrReadGrpSq);
        }
        BigDecimal calcMtrCnt = calcRollOverMtrCnt(lastPhysMtrReadTMsg, dt, mtrCnt);
        // add end 2017/09/05 QC#15134

        BigDecimal minAllwReadMtrCnt = getMinAllwMtrCnt(glblCmpyCd, svcPhysMtrPk, startPhysMtrReadGrpSq, lastPhysMtrReadGrpSq, dt);
        // mod start 2017/09/05 QC#15134
        //if (minAllwReadMtrCnt != null && mtrCnt.compareTo(minAllwReadMtrCnt) < 0) {
        if (minAllwReadMtrCnt != null && calcMtrCnt.compareTo(minAllwReadMtrCnt) < 0) {
        // mod end 2017/09/05 QC#15134
            return true;
        }

        BigDecimal maxAllwReadMtrCnt = getMaxAllwMtrCnt(glblCmpyCd, svcPhysMtrPk, startPhysMtrReadGrpSq, lastPhysMtrReadGrpSq, dt);
        // mod start 2017/09/05 QC#15134
        //if (maxAllwReadMtrCnt != null && mtrCnt.compareTo(maxAllwReadMtrCnt) > 0) {
        if (maxAllwReadMtrCnt != null && calcMtrCnt.compareTo(maxAllwReadMtrCnt) > 0) {
        // mod end 2017/09/05 QC#15134
            return true;
        }
        return false;
    }

    /**
     * Check current meter reading should be Over Model Average Daily
     * Copy Volume
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail Pk
     * @param svcMachMstrPk Service Machine Master Pk
     * @param mdlMtrLbCd Meter Label Code
     * @param svcPhysMtrPk Service physical meter pk
     * @param lastPhysMtrReadGrpSq Last svcPhysMtrReadGrpSq
     * @param dt meter Read Date
     * @param mtrCnt read Meter COunt
     * @return boolean
     */
    public static boolean isOverAdcv(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, String mdlMtrLbCd, BigDecimal svcPhysMtrPk, BigDecimal lastPhysMtrReadGrpSq, String dt, BigDecimal mtrCnt) {
        BigDecimal adcvLimitVal = ZYPCodeDataUtil.getNumConstValue(NSXC0030_ADCV_LIMIT_VAL, glblCmpyCd);
        if (!hasValue(adcvLimitVal)) {
            return false;
        }
        SVC_PHYS_MTR_READTMsg lastReadPhysMtrTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastPhysMtrReadGrpSq);
        if (lastReadPhysMtrTmsg == null) {
            return false;
        }

        // add start 2017/09/05 QC#15134
        BigDecimal lastExchPhysMtrReadGrpSq = getLastExchSvcPhysMtrReadGrpSq(lastReadPhysMtrTmsg, dt);
        if (lastExchPhysMtrReadGrpSq != null) {
            lastReadPhysMtrTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastExchPhysMtrReadGrpSq);
        }
        BigDecimal calcMtrCnt = calcRollOverMtrCnt(lastReadPhysMtrTmsg, dt, mtrCnt);
        // add end 2017/09/05 QC#15134

        int diffDays = ZYPDateUtil.getDiffDays(dt, lastReadPhysMtrTmsg.mtrReadDt.getValue());
        if (diffDays < 0) {
            return false;
        }
        if (diffDays == 0) {
            diffDays = 1;
        }
        Map<String, Object> adcvMap = getAdcvAvfMtrReadCnt(glblCmpyCd, svcMachMstrPk, mdlMtrLbCd);
        if (adcvMap == null) {
            return false;
        }
        BigDecimal adcvAvgCnt = (BigDecimal) adcvMap.get("AVG_MTR_READ_CNT");
        if (!hasValue(adcvAvgCnt) || BigDecimal.ZERO.compareTo(adcvAvgCnt) == 0) {
            return false;
        }
        // mod start 2017/09/05 QC#15134
        //BigDecimal dailyCopyCnt = mtrCnt.subtract(lastReadPhysMtrTmsg.readMtrCnt.getValue()).divide(BigDecimal.valueOf(diffDays), 0, BigDecimal.ROUND_HALF_UP);
        BigDecimal dailyCopyCnt = calcMtrCnt.subtract(lastReadPhysMtrTmsg.readMtrCnt.getValue()).divide(BigDecimal.valueOf(diffDays), 0, BigDecimal.ROUND_HALF_UP);
        // mod end 2017/09/05 QC#15134
        // START 2016/04/26 T.Kanasaka [QC#7056, MOD]
//        BigDecimal calcAdcvLimit = adcvAvgCnt.subtract(dailyCopyCnt).divide(adcvAvgCnt).multiply(BigDecimal.valueOf(100)).abs();
        BigDecimal calcAdcvLimit = adcvAvgCnt.subtract(dailyCopyCnt).multiply(BigDecimal.valueOf(100)).divide(adcvAvgCnt, 6, BigDecimal.ROUND_HALF_UP).abs();
        // END 2016/04/26 T.Kanasaka [QC#7056, MOD]
        if (calcAdcvLimit.compareTo(adcvLimitVal) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Check current Total meter reading should be Over Model Total
     * Copy Volume
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail Pk
     * @param svcMachMstrPk Service Machine Master Pk
     * @param svcPhysMtrPk Service physical meter pk
     * @param lastPhysMtrReadGrpSq Last svcPhysMtrReadGrpSq
     * @param dt meter Read Date
     * @param mtrCnt Total read Meter COunt
     * @return boolean
     */
    public static boolean isOverTotal(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk, BigDecimal lastPhysMtrReadGrpSq, String dt, BigDecimal mtrCnt) {
        SVC_PHYS_MTR_READTMsg lastReadPhysMtrTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastPhysMtrReadGrpSq);
        if (lastReadPhysMtrTmsg == null) {
            return false;
        }

        // add start 2017/09/05 QC#15134
        // START 2019/09/05 K.Kitachi [QC#53247, DEL]
//        BigDecimal times = getRollOverExchTimes(lastReadPhysMtrTmsg, dt);
//        if (BigDecimal.ZERO.compareTo(times) < 0) {
//            return false;
//        }
        // END 2019/09/05 K.Kitachi [QC#53247, DEL]
        // add end 2017/09/05 QC#15134

        int diffDays = ZYPDateUtil.getDiffDays(dt, lastReadPhysMtrTmsg.mtrReadDt.getValue());
        if (diffDays < 0) {
            return false;
        }
        if (diffDays == 0) {
            diffDays = 1;
        }

        // START 2019/09/05 K.Kitachi [QC#53247, MOD]
//        BigDecimal lastTotCnt = getTotMtrCntByGrpSq(glblCmpyCd, lastPhysMtrReadGrpSq);
        // mod start 2020/03/03 QC#56123
        //BigDecimal lastTotCnt = getTotMtrCntByGrpSq(glblCmpyCd, svcMachMstrPk, lastPhysMtrReadGrpSq);
        BigDecimal lastTotCnt = getTotMtrCntByGrpSq(glblCmpyCd, svcMachMstrPk, lastPhysMtrReadGrpSq, lastReadPhysMtrTmsg.dsMtrReadTpGrpCd.getValue());
        // mod end 2020/03/03 QC#56123
        // END 2019/09/05 K.Kitachi [QC#53247, MOD]
        if (lastTotCnt == null) {
            return false;
        }
        // add start 2017/09/05 QC#15134
        if (mtrCnt.compareTo(lastTotCnt) < 0) {
            return false;
        }
        // add end 2017/09/05 QC#15134

        Map<String, Object> mdltotMap = getMdlPerDayTotCnt(glblCmpyCd, svcMachMstrPk);
        if (mdltotMap == null) {
            return false;
        }
        BigDecimal mdlTotCnt = (BigDecimal) mdltotMap.get("MAX_COPY_PER_DAY_TOT_CNT");
        if (!hasValue(mdlTotCnt) || BigDecimal.ZERO.compareTo(mdlTotCnt) == 0) {
            return false;
        }
        BigDecimal currTotMtrCnt = mtrCnt.subtract(lastTotCnt);
        BigDecimal calcTotMtrCnt = mdlTotCnt.multiply(BigDecimal.valueOf(diffDays));
        if (currTotMtrCnt.compareTo(calcTotMtrCnt) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Get minimum allowed meter count on the day
     * @param glblCmpyCd Global company code
     * @param svcPhysMtrPk Service physical meter pk
     * @param startPhysMtrReadGrpSq start svcPhysMtrReadGrpSq
     * @param lastPhysMtrReadGrpSq Last svcPhysMtrReadGrpSq
     * @param dt The day
     * @return Minimum allowed meter count on the day
     */
    public static BigDecimal getMinAllwMtrCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk, BigDecimal startPhysMtrReadGrpSq, BigDecimal lastPhysMtrReadGrpSq, String dt) {
        BigDecimal mtrLowThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_LOW_THRHD_FCT_NUM, glblCmpyCd);
        if (mtrLowThrhdFctNum == null) {
            return null;
        }
        SVC_PHYS_MTR_READTMsg startTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, startPhysMtrReadGrpSq);
        if (startTmsg == null) {
            return null;
        }
        SVC_PHYS_MTR_READTMsg lastTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastPhysMtrReadGrpSq);
        if (lastTmsg == null) {
            return null;
        }

        List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();
        SvcPhysMtrReadInfoBean startBean = toBeanFromSvcPhysMtrReadTmsg(startTmsg);
        mtrReadList.add(startBean);
        SvcPhysMtrReadInfoBean lastBean = toBeanFromSvcPhysMtrReadTmsg(lastTmsg);
        mtrReadList.add(lastBean);
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        // mod start 2017/09/05 QC#15134
        BigDecimal avgDlyCopyVol = strgy.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
        // mod end 2017/09/05 QC#15134
        if (avgDlyCopyVol == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean lastMtrReadInfo = mtrReadList.get(mtrReadList.size() - 1);
        BigDecimal allwCopyVol = avgDlyCopyVol.multiply(BigDecimal.valueOf(ZYPDateUtil.getDiffDays(dt, lastMtrReadInfo.getMtrReadDt())));
        BigDecimal minAllwReadMtrCnt = allwCopyVol.multiply(mtrLowThrhdFctNum).add(lastMtrReadInfo.getReadMtrCnt());
        return minAllwReadMtrCnt.setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * Get maximum allowed meter count on the day
     * @param glblCmpyCd Global company code
     * @param svcPhysMtrPk Service physical meter Pk
     * @param startPhysMtrReadGrpSq start svcPhysMtrReadGrpSq
     * @param lastPhysMtrReadGrpSq Last svcPhysMtrReadGrpSq
     * @param dt The day
     * @return Maximum allowed meter count on the day
     */
    public static BigDecimal getMaxAllwMtrCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk, BigDecimal startPhysMtrReadGrpSq, BigDecimal lastPhysMtrReadGrpSq, String dt) {
        BigDecimal mtrUpThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_UP_THRHD_FCT_NUM, glblCmpyCd);
        if (mtrUpThrhdFctNum == null) {
            return null;
        }
        SVC_PHYS_MTR_READTMsg startTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, startPhysMtrReadGrpSq);
        if (startTmsg == null) {
            return null;
        }
        SVC_PHYS_MTR_READTMsg lastTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastPhysMtrReadGrpSq);
        if (lastTmsg == null) {
            return null;
        }

        List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();
        SvcPhysMtrReadInfoBean startBean = toBeanFromSvcPhysMtrReadTmsg(startTmsg);
        mtrReadList.add(startBean);
        SvcPhysMtrReadInfoBean lastBean = toBeanFromSvcPhysMtrReadTmsg(lastTmsg);
        mtrReadList.add(lastBean);
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        // mod start 2017/09/05 QC#15134
        BigDecimal avgDlyCopyVol = strgy.calcAvgDlyCopyVol(glblCmpyCd, mtrReadList);
        // mod end 2017/09/05 QC#15134
        if (avgDlyCopyVol == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean lastMtrReadInfo = mtrReadList.get(mtrReadList.size() - 1);
        BigDecimal allwCopyVol = avgDlyCopyVol.multiply(BigDecimal.valueOf(ZYPDateUtil.getDiffDays(dt, lastMtrReadInfo.getMtrReadDt())));
        BigDecimal maxAllwReadMtrCnt = allwCopyVol.multiply(mtrUpThrhdFctNum).add(lastMtrReadInfo.getReadMtrCnt());
        return maxAllwReadMtrCnt.setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * Get svcPhysMtrRead by svcPhysMtrReadGrpSq
     * @param glblCmpyCd Global company code
     * @param svcPhysMtrPk Service physical meter pk
     * @param svcPhysMtrReadGrpSq Service physical meter read group Sq
     * @return SVC_PHYS_MTR_READTMsg
     */
    public static SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrPk, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0);
    }

    /**
     * has Usage Invoice
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail pk
     * @return boolean
     */
    public static boolean hasUsageInv(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("dsContrDtlPk", dsContrDtlPk);
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) SSM_CLNT.queryObjectList("hasUsageInv", prm);
        if (rsltList.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * get Average Daily Copy Volume
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param mtrLbCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getAdcvAvfMtrReadCnt(String glblCmpyCd, BigDecimal svcMachMstrPk, String mtrLbCd) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        prm.put("mtrLbCd", mtrLbCd);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getAdcvAvfMtrReadCnt", prm);

        return rsltMap;
    }

    /**
     * get Model Total Daily Copy Volume
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return Map<String, Object>
     */
    public static Map<String, Object> getMdlPerDayTotCnt(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getMdlPerDayTotCnt", prm);

        return rsltMap;
    }

    /**
     * get Total meter Count By svcPhysMtrReadGrpSq
     * @param glblCmpyCd String
     * @param svcPhysMtrReadGrpSq BigDecimal
     * @return total Count BigDecimal
     */
    public static BigDecimal getTotMtrCntByGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getTotMtrCntByGrpSq", prm);
        if (rsltMap == null) {
            return null;
        }

        return (BigDecimal) rsltMap.get("TOT_MTR_CNT");
    }

    private static SvcPhysMtrReadInfoBean toBeanFromSvcPhysMtrReadTmsg(SVC_PHYS_MTR_READTMsg inMsg) {
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setSvcPhysMtrReadPk(inMsg.svcPhysMtrReadPk.getValue());
        bean.setSvcPhysMtrPk(inMsg.svcPhysMtrPk.getValue());
        bean.setMtrReadDt(inMsg.mtrReadDt.getValue());
        bean.setReadMtrCnt(inMsg.readMtrCnt.getValue());
        return bean;
    }

    // ADD-E 10/13/2015 K.Kishimoto

    private static List<SvcPhysMtrReadInfoBean> getFirstAndLastMeterRead(String glblCmpyCd, BigDecimal svcPhysMtrPk, String dsMtrReadTpGrpCd, String dsMtrReadTpCd, String dt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("dsMtrReadTpGrpCd", dsMtrReadTpGrpCd);
        prm.put("dsMtrReadTpCd", dsMtrReadTpCd);
        prm.put("mtrReadDt", dt);
        List<String> dsMtrReadTpCdList = new ArrayList<String>();
        // DEL-S 10/13/2015 K.Kishimoto
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.INITIAL_METER_READING);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.PERIODIC_METER_READING);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.FINAL_METER_READING);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.CORRECTION);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.EXCHANGE_METER_TO);
        // prm.put("dsMtrReadTpCdList", dsMtrReadTpCdList);
        // DEL-E 10/13/2015 K.Kishimoto
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) SSM_CLNT.queryObjectList("getFirstAndLastMeterRead", prm);
        return toBeanList(rsltList);
    }

    private static List<SvcPhysMtrReadInfoBean> getLastTwoMeterRead(String glblCmpyCd, BigDecimal svcPhysMtrPk, String dsMtrReadTpGrpCd, String dsMtrReadTpCd, String dt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("dsMtrReadTpGrpCd", dsMtrReadTpGrpCd);
        prm.put("dsMtrReadTpCd", dsMtrReadTpCd);
        prm.put("mtrReadDt", dt);
        List<String> dsMtrReadTpCdList = new ArrayList<String>();
        // DEL-S 10/13/2015 K.Kishimoto
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.INITIAL_METER_READING);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.PERIODIC_METER_READING);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.FINAL_METER_READING);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.CORRECTION);
        // dsMtrReadTpCdList.add(DS_MTR_READ_TP.EXCHANGE_METER_TO);
        prm.put("dsMtrReadTpCdList", dsMtrReadTpCdList);
        // DEL-E 10/13/2015 K.Kishimoto
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) SSM_CLNT.queryObjectList("getLastTwoMeterRead", prm);
        return toBeanList(rsltList);
    }

    private static List<SvcPhysMtrReadInfoBean> toBeanList(List<Map<String, Object>> rsltList) {
        List<SvcPhysMtrReadInfoBean> list = new ArrayList<SvcPhysMtrReadInfoBean>();
        for (Map<String, Object> rsltMap : rsltList) {
            list.add(toBean(rsltMap));
        }
        return list;
    }

    private static SvcPhysMtrReadInfoBean toBean(Map<String, Object> rsltMap) {
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        return bean;
    }

    // add start 2017/09/05 QC#15134
    private static BigDecimal calcRollOverMtrCnt(SVC_PHYS_MTR_READTMsg lastReadPhysMtrTmsg, String dt, BigDecimal mtrCnt) {
        BigDecimal calcMtrCnt = mtrCnt;
        Map<String, Object> rollOverInfoMap = getRollOverInfo(lastReadPhysMtrTmsg, dt);
        if (rollOverInfoMap != null) {
            BigDecimal cntrMaxCnt = (BigDecimal) rollOverInfoMap.get("CNTR_MAX_CNT");
            BigDecimal currentMtrCnt = getCurrentMtrCnt(lastReadPhysMtrTmsg, dt);
            if (currentMtrCnt != null && calcMtrCnt.compareTo(currentMtrCnt) < 0) {
                calcMtrCnt = calcMtrCnt.add(cntrMaxCnt);
            }

            BigDecimal rollOverCnt = (BigDecimal) rollOverInfoMap.get("ROLL_OVER_CNT");
            calcMtrCnt = calcMtrCnt.add(rollOverCnt);
        }
        return calcMtrCnt;
    }

    private static BigDecimal getLastExchSvcPhysMtrReadGrpSq(SVC_PHYS_MTR_READTMsg svcPhysMtrRead, String dt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", svcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", svcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        prm.put("startMtrReadDt", svcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", svcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", dt);
        // add start 2020/03/03 QC#56123
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String dsMtrReadTpGrpCd = svcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(dsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // add end 2020/03/03 QC#56123
        Map<String, Object> svcPhysMtrReadMap = (Map<String, Object>) SSM_CLNT.queryObject("getSvcPhysMtrRead", prm);
        if (svcPhysMtrReadMap == null) {
            return null;
        }
        return (BigDecimal) svcPhysMtrReadMap.get("SVC_PHYS_MTR_READ_GRP_SQ");
    }

    private static Map<String, Object> getRollOverInfo(SVC_PHYS_MTR_READTMsg svcPhysMtrRead, String dt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", svcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", svcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("rollOver", CNTR_RESET_TP.METER_ROLLOVER);
        // add start 2018/06/04 QC#26052
        prm.put("dsMtrReadTpGrpCd", svcPhysMtrRead.dsMtrReadTpGrpCd.getValue());
        // add end 2018/06/04 QC#26052
        prm.put("startMtrReadDt", svcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", svcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", dt);
        // add start 2020/03/03 QC#56123
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String dsMtrReadTpGrpCd = svcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(dsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // add end 2020/03/03 QC#56123
        return (Map<String, Object>) SSM_CLNT.queryObject("getRollOverInfo", prm);
    }

    private static BigDecimal getCurrentMtrCnt(SVC_PHYS_MTR_READTMsg svcPhysMtrRead, String dt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", svcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", svcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("startMtrReadDt", svcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", svcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", dt);
        // add start 2020/03/03 QC#56123
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String dsMtrReadTpGrpCd = svcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(dsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // add end 2020/03/03 QC#56123
        Map<String, Object> svcPhysMtrReadMap = (Map<String, Object>) SSM_CLNT.queryObject("getSvcPhysMtrRead", prm);
        if (svcPhysMtrReadMap == null) {
            return null;
        }
        return (BigDecimal) svcPhysMtrReadMap.get("READ_MTR_CNT");
    }

// del start 2020/03/03 QC#56123
//    private static BigDecimal getRollOverExchTimes(SVC_PHYS_MTR_READTMsg svcPhysMtrRead, String dt) {
//        Map<String, Object> prm = new HashMap<String, Object>();
//        prm.put("glblCmpyCd", svcPhysMtrRead.glblCmpyCd.getValue());
//        prm.put("svcPhysMtrPk", svcPhysMtrRead.svcPhysMtrPk.getValue());
//        prm.put("rollOver", CNTR_RESET_TP.METER_ROLLOVER);
//        prm.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
//        // add start 2018/06/04 QC#26052
//        prm.put("dsMtrReadTpGrpCd", svcPhysMtrRead.dsMtrReadTpGrpCd.getValue());
//        // add end 2018/06/04 QC#26052
//        prm.put("startMtrReadDt", svcPhysMtrRead.mtrReadDt.getValue());
//        prm.put("startSvcPhysMtrReadGrpSq", svcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
//        prm.put("endMtrReadDt", dt);
//        return (BigDecimal) SSM_CLNT.queryObject("getRollOverExchTimes", prm);
//    }
 // del end 2020/03/03 QC#56123
    // add end 2017/09/05 QC#15134

    // START 2018/10/19 K.Kitachi [QC#28733, ADD]
    /**
     * Pre check current meter reading should be within +/- xx% of the
     * average volume for this meter.
     * @param glblCmpyCd Global company code
     * @param dsContrDtlPk DS Contract Detail Pk
     * @param dsContrBllgMtrPk DS Contract Billing Meter Pk
     * @param svcPhysMtrPk Service physical meter pk
     * @param dt meter Read Date
     * @return boolean
     */
    public static boolean hasErrAveVolForMtrPreChk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcPhysMtrPk, String dt) {
        if (!hasUsageInv(glblCmpyCd, dsContrDtlPk)) {
            return true;
        }
        if (!hasValue(dsContrBllgMtrPk)) {
            return true;
        }
        BigDecimal startPhysMtrReadGrpSq = getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
        if (startPhysMtrReadGrpSq == null) {
            return true;
        }
        SVC_PHYS_MTR_READTMsg mtrMsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, startPhysMtrReadGrpSq);
        if (mtrMsg == null) {
            return true;
        }
        BigDecimal lastPhysMtrReadGrpSq = null;
        DS_CONTR_BLLG_SCHDTMsg inSchdMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inSchdMsg.setSQLID("005");
        inSchdMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inSchdMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inSchdMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inSchdMsg.setConditionValue("bllgSchdFromDt01", mtrMsg.mtrReadDt.getValue());
        inSchdMsg.setConditionValue("bllgSchdFromDt02", dt);
        inSchdMsg.setConditionValue("invFlg01", FLG_ON_Y);
        inSchdMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray schdArray = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inSchdMsg);
        if (schdArray.getValidCount() == 0) {
            return true;
        }
        lastPhysMtrReadGrpSq = schdArray.no(0).svcPhysMtrReadGrpSq.getValue();
        if (lastPhysMtrReadGrpSq == null) {
            return true;
        }
        return false;
    }
    // END 2018/10/19 K.Kitachi [QC#28733, ADD]

    // START 2019/09/05 K.Kitachi [QC#53247, ADD]
    /**
     * Is used total meter
     * @param glblCmpyCd Global company code
     * @param svcMachMstrPk Service machine master pk
     * @return boolean
     */
    public static boolean isUsedTotMtr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.DRAFT);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.ENTERED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.SINGED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
        ssmParam.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        BigDecimal count = (BigDecimal) SSM_CLNT.queryObject("isUsedTotMtr", ssmParam);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    /**
     * get Total meter Count By svcPhysMtrReadGrpSq
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param svcPhysMtrReadGrpSq BigDecimal
     * @return total Count BigDecimal
     */
    //public static BigDecimal getTotMtrCntByGrpSq(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrReadGrpSq) {
    public static BigDecimal getTotMtrCntByGrpSq(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrReadGrpSq, String dsMtrReadTpGrpCd) {
        // mod start 2020/03/03 QC#56123
        //boolean isUsedTotMtr = isUsedTotMtr(glblCmpyCd, svcMachMstrPk);
        boolean isUsedTotMtr = true;
        if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(dsMtrReadTpGrpCd)) {
            isUsedTotMtr = isUsedTotMtr(glblCmpyCd, svcMachMstrPk);
        }
        // mod end 2020/03/03 QC#56123
        BigDecimal totMtrCntTotMtr = getTotMtrCntByGrpSq(glblCmpyCd, svcPhysMtrReadGrpSq, FLG_ON_Y);
        if (isUsedTotMtr && hasValue(totMtrCntTotMtr)) {
            return totMtrCntTotMtr;
        }
        return getTotMtrCntByGrpSq(glblCmpyCd, svcPhysMtrReadGrpSq, FLG_OFF_N);
    }

    /**
     * get Total meter Count By svcPhysMtrReadGrpSq
     * @param glblCmpyCd String
     * @param svcPhysMtrReadGrpSq BigDecimal
     * @param totMtrFlg String
     * @return total Count BigDecimal
     */
    public static BigDecimal getTotMtrCntByGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq, String totMtrFlg) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        prm.put("totMtrFlg", totMtrFlg);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getTotMtrCntByGrpSq", prm);
        if (rsltMap == null) {
            return null;
        }
        return (BigDecimal) rsltMap.get("TOT_MTR_CNT");
    }
    // END 2019/09/05 K.Kitachi [QC#53247, ADD]

    // add end 2019/09/19 QC#53403
    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param dt The day
     * @param avgDlyCopyVol average daily copy volume
     * @param latestMtrRead Latest Meter read
     * @return Estimated meter count
     */
    public static BigDecimal estMtrCnt(String glblCmpyCd, String dt, BigDecimal avgDlyCopyVol, SvcPhysMtrReadInfoBean latestMtrRead) {
        NSXC003001MtrCntTwoPntEst strgy = new NSXC003001MtrCntTwoPntEst();
        return strgy.estMtrCnt(glblCmpyCd, dt, avgDlyCopyVol, latestMtrRead, 0);
    }
    // add end 2019/09/19 QC#53403

    // START 2020/03/03 [QC#56123, ADD]
    /**
     * Get previous Meter Read Info By Meter Read Type Group 
     * @param glblCmpyCd Global company code
     * @param entryDt Entry Date
     * @param svcPhysMtrPk Service Physical Meter PK
     * @param dsMtrReadTpGrpCdList  Meter Read Type Group List
     * @return SvcPhysMtrReadInfoBean
     */
    public static SvcPhysMtrReadInfoBean getPrevMtrReadInfoByMtrReadTpGrp(String glblCmpyCd, String entryDt, BigDecimal svcPhysMtrPk, List<String> dsMtrReadTpGrpCdList) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("entryDt", entryDt);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getPrevMtrReadInfoByMtrReadTpGrp", prm);
        if (rsltMap == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        return bean;
    }

    /**
     * Get Next Meter Read Info By Meter Read Type Group 
     * @param glblCmpyCd Global company code
     * @param entryDt Entry Date
     * @param svcPhysMtrPk Service Physical Meter PK
     * @param dsMtrReadTpGrpList  Meter Read Type Group List
     * @return SvcPhysMtrReadInfoBean
     */
    public static SvcPhysMtrReadInfoBean getNextMtrReadInfoByMtrReadTpGrp(String glblCmpyCd, String entryDt, BigDecimal svcPhysMtrPk, List<String> dsMtrReadTpGrpCdList) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("entryDt", entryDt);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getNextMtrReadInfoByMtrReadTpGrp", prm);
        if (rsltMap == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        return bean;
    }

    /**
     * Get previous Meter Read Info exclusive GroupSq
     * @param glblCmpyCd Global company code
     * @param entryDt Entry Date
     * @param svcPhysMtrPk Service Physical Meter PK
     * @param dsMtrReadTpGrpCdList  Meter Read Type Group List
     * @param svcPhysMtrReadGrpSq svcPhysMtrReadGrpSq
     * @return SvcPhysMtrReadInfoBean
     */
    public static SvcPhysMtrReadInfoBean getPrevMtrReadInfoExclGrpSq(String glblCmpyCd, String entryDt, BigDecimal svcPhysMtrPk, List<String> dsMtrReadTpGrpCdList, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("entryDt", entryDt);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        prm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getPrevMtrReadInfoExclGrpSq", prm);
        if (rsltMap == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        return bean;
    }

    /**
     * Get previous Meter Read Info exclusive serviceTaskNum
     * @param glblCmpyCd Global company code
     * @param entryDt Entry Date
     * @param svcPhysMtrPk Service Physical Meter PK
     * @param dsMtrReadTpGrpCdList  Meter Read Type Group List
     * @param svcTaskNum svcTaskNum
     * @return SvcPhysMtrReadInfoBean
     */
    public static SvcPhysMtrReadInfoBean getPrevMtrReadInfoExclSvcTaskNum(String glblCmpyCd, String entryDt, BigDecimal svcPhysMtrPk, List<String> dsMtrReadTpGrpCdList, String svcTaskNum) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("entryDt", entryDt);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        prm.put("svcTaskNum", svcTaskNum);
        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLNT.queryObject("getPrevMtrReadInfoExclSvcTaskNum", prm);
        if (rsltMap == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean bean = new SvcPhysMtrReadInfoBean();
        bean.setMtrReadDt((String) rsltMap.get("MTR_READ_DT"));
        bean.setReadMtrCnt((BigDecimal) rsltMap.get("READ_MTR_CNT"));
        bean.setSvcPhysMtrReadPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_READ_PK"));
        bean.setSvcPhysMtrPk((BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
        return bean;
    }

    /**
     * Check current meter reading should be Over Model Average Daily
     * Copy Volume
     * @param glblCmpyCd Global company code
     * @param svcMachMstrPk Service Machine Master Pk
     * @param mdlMtrLbCd Meter Label Code
     * @param svcPhysMtrPk Service physical meter pk
     * @param lastPhysMtrReadGrpSq Last svcPhysMtrReadGrpSq
     * @param dt meter Read Date
     * @param mtrCnt read Meter COunt
     * @return boolean
     */
    public static boolean isOverAdcvSvcRead(String glblCmpyCd, BigDecimal svcMachMstrPk, String mdlMtrLbCd, BigDecimal svcPhysMtrPk, BigDecimal lastPhysMtrReadGrpSq, String dt, BigDecimal mtrCnt) {
        BigDecimal mtrLowThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_LOW_THRHD_FCT_NUM, glblCmpyCd);
        BigDecimal mtrUpThrhdFctNum = ZYPCodeDataUtil.getNumConstValue(NSXC0030_MTR_UP_THRHD_FCT_NUM, glblCmpyCd);
        if (!hasValue(mtrLowThrhdFctNum) || !hasValue(mtrUpThrhdFctNum)) {
            return false;
        }

        SVC_PHYS_MTR_READTMsg lastReadPhysMtrTmsg = getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, lastPhysMtrReadGrpSq);
        if (lastReadPhysMtrTmsg == null) {
            return false;
        }

        int diffDays = ZYPDateUtil.getDiffDays(dt, lastReadPhysMtrTmsg.mtrReadDt.getValue());
        if (diffDays < 0) {
            return false;
        }
        if (diffDays == 0) {
            diffDays = 1;
        }

        BigDecimal adcvAvgCnt = getAdcvForSvcRead(glblCmpyCd, svcMachMstrPk, mdlMtrLbCd);
        if (!hasValue(adcvAvgCnt) || BigDecimal.ZERO.compareTo(adcvAvgCnt) == 0) {
            return false;
        }

        BigDecimal allwCopyVol = adcvAvgCnt.multiply(BigDecimal.valueOf(diffDays));
        BigDecimal minAllwReadMtrCnt = allwCopyVol.multiply(mtrLowThrhdFctNum).add(lastReadPhysMtrTmsg.readMtrCnt.getValue()).setScale(0, RoundingMode.HALF_UP);
        if (mtrCnt.compareTo(minAllwReadMtrCnt) < 0) {
            return true;
        }
        BigDecimal maxAllwReadMtrCnt = allwCopyVol.multiply(mtrUpThrhdFctNum).add(lastReadPhysMtrTmsg.readMtrCnt.getValue()).setScale(0, RoundingMode.HALF_UP);
        if (mtrCnt.compareTo(maxAllwReadMtrCnt) > 0) {
            return true;
        }
        return false;
    }

    private static BigDecimal getAdcvForSvcRead(String glblCmpyCd, BigDecimal svcMachMstrPk, String mtrLbCd) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcMachMstrPk", svcMachMstrPk);
        prm.put("mtrLbCd", mtrLbCd);
        return (BigDecimal) SSM_CLNT.queryObject("getAdcvForSvcRead", prm);
    }
    // END 2020/03/03 [QC#56123, ADD]
}
