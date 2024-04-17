/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_PHYS_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            SRAA            Create          N/A
 * 2015/12/14   Hitachi         K.Kasai         Update          QC1896
 * 2017/09/05   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/27   Hitachi         K.Kasai         Update          QC#21443
 * 2018/06/04   Hitachi         A.Kohinata      Update          QC#26052
 * 2018/09/26   Hitachi         K.Kojima        Update          QC#28388
 * 2019/08/23   Hitachi         A.Kohinata      Update          QC#52934
 * 2019/09/19   Hitachi         A.Kohinata      Update          QC#53403
 * 2020/03/03   Hitachi         A.Kohinata      Update          QC#56123
 *</pre>
 */
public class NSXC003001MtrCntTwoPntEst {

    /**
     * Scale of AVG_MTR_READ_CNT
     */
    private static final int AVG_MTR_READ_CNT_SCL = 8;

    // add start 2017/09/05 QC#15134
    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLNT = S21SsmBatchClient.getClient(NSXC003001MtrCntTwoPntEst.class);
    // add end 2017/09/05 QC#15134

    public NSXC003001MtrCntTwoPntEst() {
    }

    // START 2018/09/26 K.Kojima [QC#28388,ADD]
    public BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList) {
        return calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, true);
    }

    public BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList, int scl) {
        return calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, scl, true);
    }

    // END 2018/09/26 K.Kojima [QC#28388,ADD]

    /**
     * Calculate average daily copy volume
     * @param glblCmpyCd Global company code
     * @param mtrReadList Meter read
     * @return Calculated average daily copy volume (ADCV)
     */
    // mod start 2017/09/05 QC#15134
    //public BigDecimal calcAvgDlyCopyVol(List<SvcPhysMtrReadInfoBean> mtrReadList) {
    //    return calcAvgDlyCopyVol(mtrReadList, AVG_MTR_READ_CNT_SCL);
    //}
    // START 2018/09/26 K.Kojima [QC#28388,MOD]
    // public BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList) {
    //     return calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, AVG_MTR_READ_CNT_SCL);
    // }
    public BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList, boolean rollOverExchFlg) {
        return calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, AVG_MTR_READ_CNT_SCL, rollOverExchFlg);
    }
    // END 2018/09/26 K.Kojima [QC#28388,MOD]
    // mod end 2017/09/05 QC#15134

    /**
     * Calculate average daily copy volume
     * @param glblCmpyCd Global company code
     * @param mtrReadList Meter read
     * @param scl Scale
     * @return Calculated average daily copy volume (ADCV)
     */
    // mod start 2017/09/05 QC#15134
    //public BigDecimal calcAvgDlyCopyVol(List<SvcPhysMtrReadInfoBean> mtrReadList, int scl) {
    // START 2018/09/26 K.Kojima [QC#28388,MOD]
    // public BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList, int scl) {
    public BigDecimal calcAvgDlyCopyVol(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList, int scl, boolean rollOverExchFlg) {
    // END 2018/09/26 K.Kojima [QC#28388,MOD]
    // mod end 2017/09/05 QC#15134
        if (mtrReadList == null) {
            return null;
        }
        if (mtrReadList.size() < 2) {
            return null;
        }

        SvcPhysMtrReadInfoBean oldRead = mtrReadList.get(mtrReadList.size() - 2);
        SvcPhysMtrReadInfoBean newRead = mtrReadList.get(mtrReadList.size() - 1);

        BigDecimal oldCnt = oldRead.getReadMtrCnt();
        if (oldCnt == null) {
            return null;
        }
        BigDecimal newCnt = newRead.getReadMtrCnt();
        if (newCnt == null) {
            return null;
        }
        String oldDt = oldRead.getMtrReadDt();
        if (oldDt == null) {
            return null;
        }
        String newDt = newRead.getMtrReadDt();
        if (newDt == null) {
            return null;
        }

        // add start 2017/09/05 QC#15134
        // START 2018/09/26 K.Kojima [QC#28388,MOD]
        // BigDecimal rollOverExchCnt = getRollOverExchCnt(glblCmpyCd, oldRead, newRead);
        BigDecimal rollOverExchCnt = BigDecimal.ZERO;
        if (rollOverExchFlg) {
            rollOverExchCnt = getRollOverExchCnt(glblCmpyCd, oldRead, newRead);
        }
        // END 2018/09/26 K.Kojima [QC#28388,MOD]
        // add end 2017/09/05 QC#15134

        int days = ZYPDateUtil.getDiffDays(newDt, oldDt);
        if (days == 0) {
            return BigDecimal.ZERO;
        } else {
            //START 2017/09/27 K.Kasai [QC#21443,ADD]
            // START 2018/09/26 K.Kojima [QC#28388,MOD]
            // BigDecimal exclPer = getExclPer(glblCmpyCd, oldRead, newRead);
            // if (ZYPCommonFunc.hasValue(exclPer)) {
            //     days = days - exclPer.intValue();
            //     if (days == 0) {
            //         return BigDecimal.ZERO;
            //     }
            // }
            if (rollOverExchFlg) {
                BigDecimal exclPer = getExclPer(glblCmpyCd, oldRead, newRead);
                if (ZYPCommonFunc.hasValue(exclPer)) {
                    days = days - exclPer.intValue();
                    if (days == 0) {
                        return BigDecimal.ZERO;
                    }
                }
            }
            // END 2018/09/26 K.Kojima [QC#28388,MOD]
            //END 2017/09/27 K.Kasai [QC#21443,ADD]

            // mod start 2017/09/05 QC#15134
            //return newCnt.subtract(oldCnt).divide(BigDecimal.valueOf(days), scl, RoundingMode.HALF_UP);
            return newCnt.subtract(oldCnt).add(rollOverExchCnt).divide(BigDecimal.valueOf(days), scl, RoundingMode.HALF_UP);
            // mod end 2017/09/05 QC#15134
        }
    }

    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param dt The day
     * @param svcPhysMtrReadTMsgList Meter read
     * @return Estimated meter count
     */
    // mod start 2017/09/05 QC#15134
    //public BigDecimal estMtrCnt(String dt, List<SvcPhysMtrReadInfoBean> mtrReadList) {
    //    return estMtrCnt(dt, mtrReadList, 0);
    //}
    public BigDecimal estMtrCnt(String glblCmpyCd, String dt, List<SvcPhysMtrReadInfoBean> mtrReadList) {
        return estMtrCnt(glblCmpyCd, dt, mtrReadList, 0);
    }
    // mod end 2017/09/05 QC#15134

    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param dt The day
     * @param svcPhysMtrReadTMsgList Meter read
     * @param scl Scale
     * @return Estimated meter count
     */
    // mod start 2017/09/05 QC#15134
    //private BigDecimal estMtrCnt(String dt, List<SvcPhysMtrReadInfoBean> mtrReadList, int scl) {
    private BigDecimal estMtrCnt(String glblCmpyCd, String dt, List<SvcPhysMtrReadInfoBean> mtrReadList, int scl) {
    // mod end 2017/09/05 QC#15134
        if (!ZYPCommonFunc.hasValue(dt)) {
            return null;
        }
        if (mtrReadList == null) {
            return null;
        }
        if (mtrReadList.size() < 2) {
            return null;
        }
        // MOD START 2015/12/14 K.Kasai [QC1896]
        // BigDecimal avgDlyCopyVol = calcAvgDlyCopyVol(mtrReadList);
        // mod start 2017/09/05 QC#15134
        //BigDecimal avgDlyCopyVol = calcAvgDlyCopyVol(mtrReadList, 0);
        BigDecimal avgDlyCopyVol = calcAvgDlyCopyVol(glblCmpyCd, mtrReadList, 0);
        // mod end 2017/09/05 QC#15134
        // MOD END 2015/12/14 K.Kasai [QC1896]
        if (avgDlyCopyVol == null) {
            return null;
        }
        SvcPhysMtrReadInfoBean oldRead = mtrReadList.get(mtrReadList.size() - 2);
        SvcPhysMtrReadInfoBean newRead = mtrReadList.get(mtrReadList.size() - 1);
        BigDecimal oldCnt = oldRead.getReadMtrCnt();
        BigDecimal newCnt = newRead.getReadMtrCnt();
        String oldDt = oldRead.getMtrReadDt();
        String newDt = newRead.getMtrReadDt();
        // mod start 2017/09/05 QC#15134
        BigDecimal estMtrCnt = null;
        if (dt.compareTo(newDt) >= 0) {
            int days = ZYPDateUtil.getDiffDays(dt, newDt);
            //return avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(newCnt).setScale(scl, RoundingMode.HALF_UP);
            estMtrCnt = avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(newCnt).setScale(scl, RoundingMode.HALF_UP);
        } else if (dt.compareTo(oldDt) >= 0) {
            //START 2017/09/27 K.Kasai [QC#21443,ADD]
            Map<String, Object> exchInfo = getAdjOldInfo(glblCmpyCd, mtrReadList);
            if (exchInfo != null) {
                oldCnt = (BigDecimal) exchInfo.get("READ_MTR_CNT");
                oldDt = (String) exchInfo.get("MTR_READ_DT");
            }
            //END 2017/09/27 K.Kasai [QC#21443,ADD]
            int days = ZYPDateUtil.getDiffDays(dt, oldDt);
            //return avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(oldCnt).setScale(scl, RoundingMode.HALF_UP);
            estMtrCnt = avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(oldCnt).setScale(scl, RoundingMode.HALF_UP);
        } else {
            //START 2017/09/27 K.Kasai [QC#21443,ADD]
            Map<String, Object> exchInfo = getAdjOldInfo(glblCmpyCd, mtrReadList);
            if (exchInfo != null) {
                oldCnt = (BigDecimal) exchInfo.get("READ_MTR_CNT");
                oldDt = (String) exchInfo.get("MTR_READ_DT");
            }
            //END 2017/09/27 K.Kasai [QC#21443,ADD]
            int days = ZYPDateUtil.getDiffDays(dt, oldDt);
            //return avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(oldCnt).setScale(scl, RoundingMode.HALF_UP);
            estMtrCnt = avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(oldCnt).setScale(scl, RoundingMode.HALF_UP);
        }

        SvcPhysMtrReadInfoBean mtrRead = mtrReadList.get(mtrReadList.size() - 1);
        BigDecimal cntrMaxCnt = getCntrMaxCnt(glblCmpyCd, mtrRead.getSvcPhysMtrPk());
        if (cntrMaxCnt == null) {
            return estMtrCnt;
        }
        BigDecimal rollOverTimes = estMtrCnt.divide(cntrMaxCnt).setScale(scl, RoundingMode.FLOOR);
        return estMtrCnt.subtract(rollOverTimes.multiply(cntrMaxCnt));
        // mod end 2017/09/05 QC#15134
    }

    // add start 2017/09/05 QC#15134
    /**
     * Get Rollover and Exchange count
     * @param glblCmpyCd String
     * @param startRead SvcPhysMtrReadInfoBean
     * @param endRead SvcPhysMtrReadInfoBean
     * @return Rollover and Exchange count
     */
    public BigDecimal getRollOverExchCnt(String glblCmpyCd, SvcPhysMtrReadInfoBean startRead, SvcPhysMtrReadInfoBean endRead) {
        // add start 2018/06/04 QC#26052
        if (startRead.getSvcPhysMtrReadPk().compareTo(endRead.getSvcPhysMtrReadPk()) == 0) {
            return BigDecimal.ZERO;
        }
        // add end 2018/06/04 QC#26052
        SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, startRead.getSvcPhysMtrReadPk());
        SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, endRead.getSvcPhysMtrReadPk());
        if (startSvcPhysMtrRead == null || endSvcPhysMtrRead == null) {
            return BigDecimal.ZERO;
        }

        // mod start 2019/08/23 QC#52934
        //BigDecimal rollOverCnt = getRollOverCnt(startSvcPhysMtrRead, endSvcPhysMtrRead);
        //BigDecimal exchCnt = getExchCnt(startSvcPhysMtrRead, endSvcPhysMtrRead);
        BigDecimal rollOverCnt = getRollOverCnt(startSvcPhysMtrRead, endSvcPhysMtrRead, null);
        BigDecimal exchCnt = getExchCnt(startSvcPhysMtrRead, endSvcPhysMtrRead, null);
        // mod end 2019/08/23 QC#52934

        return rollOverCnt.add(exchCnt);
    }

    private static SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadByKey(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        SVC_PHYS_MTR_READTMsg prmTMsg = new SVC_PHYS_MTR_READTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcPhysMtrReadPk, svcPhysMtrReadPk);
        return (SVC_PHYS_MTR_READTMsg) EZDTBLAccessor.findByKey(prmTMsg);
    }

    // mod start 2019/08/23 QC#52934
    //private static BigDecimal getRollOverCnt(SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead, SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead) {
    private static BigDecimal getRollOverCnt(SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead, SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead, BigDecimal exclSvcPhysMtrReadPk) {
    // mod end 2019/08/23 QC#52934
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", startSvcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", startSvcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("rollOver", CNTR_RESET_TP.METER_ROLLOVER);
        prm.put("startMtrReadDt", startSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", startSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", endSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("endSvcPhysMtrReadGrpSq", endSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        // add start 2018/06/04 QC#26052
        // mod start 2020/03/03 QC#56123
        //prm.put("dsMtrReadTpGrpCd", endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue());
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String endDsMtrReadTpGrpCd = endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(endDsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(endDsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // mod end 2020/03/03 QC#56123
        if (startSvcPhysMtrRead.mtrReadDt.getValue().equals(endSvcPhysMtrRead.mtrReadDt.getValue())) {
            prm.put("sameDt", ZYPConstant.FLG_ON_Y);
        }
        // add end 2018/06/04 QC#26052
        // add start 2019/08/23 QC#52934
        prm.put("exclSvcPhysMtrReadPk", exclSvcPhysMtrReadPk);
        // add end 2019/08/23 QC#52934
        Map<String, Object> rollOverInfoMap = (Map<String, Object>) SSM_CLNT.queryObject("getRollOverCnt", prm);
        if (rollOverInfoMap == null) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) rollOverInfoMap.get("ROLL_OVER_CNT");
    }

    // mod start 2019/08/23 QC#52934
    //private static BigDecimal getExchCnt(SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead, SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead) {
    private static BigDecimal getExchCnt(SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead, SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead, BigDecimal exclSvcPhysMtrReadPk) {
    // mod end 2019/08/23 QC#52934
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", startSvcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", startSvcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        prm.put("startMtrReadDt", startSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", startSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", endSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("endSvcPhysMtrReadGrpSq", endSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        // add start 2020/03/03 QC#56123
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String endDsMtrReadTpGrpCd = endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(endDsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(endDsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // add end 2020/03/03 QC#56123
        // add start 2018/06/04 QC#26052
        if (startSvcPhysMtrRead.mtrReadDt.getValue().equals(endSvcPhysMtrRead.mtrReadDt.getValue())) {
            prm.put("sameDt", ZYPConstant.FLG_ON_Y);
        }
        // add end 2018/06/04 QC#26052
        // add start 2019/08/23 QC#52934
        prm.put("exclSvcPhysMtrReadPk", exclSvcPhysMtrReadPk);
        // add end 2019/08/23 QC#52934
        Map<String, Object> exchInfoMap = (Map<String, Object>) SSM_CLNT.queryObject("getExchCnt", prm);
        if (exchInfoMap == null) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) exchInfoMap.get("EXCH_CNT");
    }

    private static BigDecimal getCntrMaxCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk) {
        if (svcPhysMtrPk== null) {
            return null;
        }
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrPk", svcPhysMtrPk);
        return (BigDecimal) SSM_CLNT.queryObject("getCntrMaxCnt", prm);
    }

    /**
     * Get Rollover or Exchange List
     * @param glblCmpyCd String
     * @param startRead SvcPhysMtrReadInfoBean
     * @param endRead SvcPhysMtrReadInfoBean
     * @return Rollover and Exchange count List<Map<String, Object>>
     */
    public List<Map<String, Object>> getRollOverExchList(String glblCmpyCd, SvcPhysMtrReadInfoBean startRead, SvcPhysMtrReadInfoBean endRead) {
        // add start 2018/06/04 QC#26052
        if (startRead.getSvcPhysMtrReadPk().compareTo(endRead.getSvcPhysMtrReadPk()) == 0) {
            return null;
        }
        // add end 2018/06/04 QC#26052
        SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, startRead.getSvcPhysMtrReadPk());
        SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, endRead.getSvcPhysMtrReadPk());
        if (startSvcPhysMtrRead == null || endSvcPhysMtrRead == null) {
            return null;
        }

        // mod start 2019/08/23 QC#52934
        //List<Map<String, Object>> rollOverExchList = getRollOverExchList(startSvcPhysMtrRead, endSvcPhysMtrRead);
        List<Map<String, Object>> rollOverExchList = getRollOverExchList(startSvcPhysMtrRead, endSvcPhysMtrRead, null);
        // mod end 2019/08/23 QC#52934

        return rollOverExchList;
    }

    // mod start 2019/08/23 QC#52934
    //private static List<Map<String, Object>> getRollOverExchList(SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead, SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead) {
    private static List<Map<String, Object>> getRollOverExchList(SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead, SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead, BigDecimal exclSvcPhysMtrReadPk) {
    // mod end 2019/08/23 QC#52934
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", startSvcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", startSvcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("rollOver", CNTR_RESET_TP.METER_ROLLOVER);
        prm.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        prm.put("startMtrReadDt", startSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", startSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", endSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("endSvcPhysMtrReadGrpSq", endSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        // add start 2018/06/04 QC#26052
        // mod start 2020/03/03 QC#56123
        //prm.put("dsMtrReadTpGrpCd", endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue());
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String endDsMtrReadTpGrpCd = endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(endDsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(endDsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // mod end 2020/03/03 QC#56123
        if (startSvcPhysMtrRead.mtrReadDt.getValue().equals(endSvcPhysMtrRead.mtrReadDt.getValue())) {
            prm.put("sameDt", ZYPConstant.FLG_ON_Y);
        }
        // add end 2018/06/04 QC#26052
        // add start 2019/08/23 QC#52934
        prm.put("exclSvcPhysMtrReadPk", exclSvcPhysMtrReadPk);
        // add end 2019/08/23 QC#52934
        List<Map<String, Object>> rollOverInfoList = (List<Map<String, Object>>) SSM_CLNT.queryObjectList("getRollOverExchList", prm);
        return (List<Map<String, Object>>) rollOverInfoList;
    }
    // add end 2017/09/05 QC#15134

    //START 2017/09/27 K.Kasai [QC#21443,ADD]
    private static BigDecimal getExclPer(String glblCmpyCd, SvcPhysMtrReadInfoBean startRead, SvcPhysMtrReadInfoBean endRead) {
        SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, startRead.getSvcPhysMtrReadPk());
        SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, endRead.getSvcPhysMtrReadPk());
        if (startSvcPhysMtrRead == null || endSvcPhysMtrRead == null) {
            return null;
        }

        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", startSvcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", startSvcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        prm.put("startMtrReadDt", startSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", startSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", endSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("endSvcPhysMtrReadGrpSq", endSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        // add start 2020/03/03 QC#56123
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String endDsMtrReadTpGrpCd = endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(endDsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(endDsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // add end 2020/03/03 QC#56123
        // add start 2018/06/04 QC#26052
        if (startSvcPhysMtrRead.mtrReadDt.getValue().equals(endSvcPhysMtrRead.mtrReadDt.getValue())) {
            prm.put("sameDt", ZYPConstant.FLG_ON_Y);
        }
        // add end 2018/06/04 QC#26052

        Map<String, Object> exchInfoMap = (Map<String, Object>) SSM_CLNT.queryObject("getExclPer", prm);
        if (exchInfoMap == null) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) exchInfoMap.get("EXCL_PER");
    }
    //END 2017/09/27 K.Kasai [QC#21443,ADD]

    //START 2017/09/27 K.Kasai [QC#21443,ADD]
    /**
     * Calculate average daily copy volume
     * @param glblCmpyCd Global company code
     * @param mtrReadList Meter read
     * @return read meter count in meter exchange
     */
    public Map<String, Object> getAdjOldInfo(String glblCmpyCd, List<SvcPhysMtrReadInfoBean> mtrReadList) {
        if (mtrReadList == null) {
            return null;
        }
        if (mtrReadList.size() < 2) {
            return null;
        }
    
        SvcPhysMtrReadInfoBean oldRead = mtrReadList.get(mtrReadList.size() - 2);
        SvcPhysMtrReadInfoBean newRead = mtrReadList.get(mtrReadList.size() - 1);
    
        BigDecimal oldCnt = oldRead.getReadMtrCnt();
        if (oldCnt == null) {
            return null;
        }
        BigDecimal newCnt = newRead.getReadMtrCnt();
        if (newCnt == null) {
            return null;
        }
        String oldDt = oldRead.getMtrReadDt();
        if (oldDt == null) {
            return null;
        }
        String newDt = newRead.getMtrReadDt();
        if (newDt == null) {
            return null;
        }
    
        return getReadMtrCntInExch(glblCmpyCd, oldRead, newRead);

    }

    private Map<String, Object> getReadMtrCntInExch(String glblCmpyCd, SvcPhysMtrReadInfoBean startRead, SvcPhysMtrReadInfoBean endRead) {
        SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, startRead.getSvcPhysMtrReadPk());
        SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, endRead.getSvcPhysMtrReadPk());
        if (startSvcPhysMtrRead == null || endSvcPhysMtrRead == null) {
            return null;
        }

        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", startSvcPhysMtrRead.glblCmpyCd.getValue());
        prm.put("svcPhysMtrPk", startSvcPhysMtrRead.svcPhysMtrPk.getValue());
        prm.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        prm.put("startMtrReadDt", startSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("startSvcPhysMtrReadGrpSq", startSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        prm.put("endMtrReadDt", endSvcPhysMtrRead.mtrReadDt.getValue());
        prm.put("endSvcPhysMtrReadGrpSq", endSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue());
        // add start 2020/03/03 QC#56123
        List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
        String endDsMtrReadTpGrpCd = endSvcPhysMtrRead.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(endDsMtrReadTpGrpCd) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(endDsMtrReadTpGrpCd)) {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        } else {
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
        }
        prm.put("dsMtrReadTpGrpCdList", dsMtrReadTpGrpCdList);
        // add end 2020/03/03 QC#56123
        // add start 2018/06/04 QC#26052
        if (startSvcPhysMtrRead.mtrReadDt.getValue().equals(endSvcPhysMtrRead.mtrReadDt.getValue())) {
            prm.put("sameDt", ZYPConstant.FLG_ON_Y);
        }
        // add end 2018/06/04 QC#26052
        Map<String, Object> exchInfoMap = (Map<String, Object>) SSM_CLNT.queryObject("getReadMtrCntInExch", prm);
        if (exchInfoMap == null || exchInfoMap.isEmpty()) {
            return null;
        }
        return (Map<String, Object>) exchInfoMap;
    }

    //END 2017/09/27 K.Kasai [QC#21443,ADD]

    // add start 2019/08/23 QC#52934
    /**
     * Get Rollover and Exchange count For Credit Rebill
     * @param glblCmpyCd String
     * @param startRead SvcPhysMtrReadInfoBean
     * @param endRead SvcPhysMtrReadInfoBean
     * @param exclSvcPhysMtrReadPk BigDecimal
     * @return Rollover and Exchange count
     */
    public BigDecimal getRollOverExchCntForCrRebil(String glblCmpyCd, SvcPhysMtrReadInfoBean startRead, SvcPhysMtrReadInfoBean endRead, BigDecimal exclSvcPhysMtrReadPk) {
        if (startRead.getSvcPhysMtrReadPk().compareTo(endRead.getSvcPhysMtrReadPk()) == 0) {
            return BigDecimal.ZERO;
        }
        SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, startRead.getSvcPhysMtrReadPk());
        SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, endRead.getSvcPhysMtrReadPk());
        if (startSvcPhysMtrRead == null || endSvcPhysMtrRead == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal rollOverCnt = getRollOverCnt(startSvcPhysMtrRead, endSvcPhysMtrRead, exclSvcPhysMtrReadPk);
        BigDecimal exchCnt = getExchCnt(startSvcPhysMtrRead, endSvcPhysMtrRead, exclSvcPhysMtrReadPk);

        return rollOverCnt.add(exchCnt);
    }

    /**
     * Get Rollover or Exchange List For Credit Rebill
     * @param glblCmpyCd String
     * @param startRead SvcPhysMtrReadInfoBean
     * @param endRead SvcPhysMtrReadInfoBean
     * @param exclSvcPhysMtrReadPk BigDecimal
     * @return Rollover and Exchange count List<Map<String, Object>>
     */
    public List<Map<String, Object>> getRollOverExchListForCrRebil(String glblCmpyCd, SvcPhysMtrReadInfoBean startRead, SvcPhysMtrReadInfoBean endRead, BigDecimal exclSvcPhysMtrReadPk) {
        if (startRead.getSvcPhysMtrReadPk().compareTo(endRead.getSvcPhysMtrReadPk()) == 0) {
            return null;
        }
        SVC_PHYS_MTR_READTMsg startSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, startRead.getSvcPhysMtrReadPk());
        SVC_PHYS_MTR_READTMsg endSvcPhysMtrRead = getSvcPhysMtrReadByKey(glblCmpyCd, endRead.getSvcPhysMtrReadPk());
        if (startSvcPhysMtrRead == null || endSvcPhysMtrRead == null) {
            return null;
        }

        List<Map<String, Object>> rollOverExchList = getRollOverExchList(startSvcPhysMtrRead, endSvcPhysMtrRead, exclSvcPhysMtrReadPk);

        return rollOverExchList;
    }
    // add end 2019/08/23 QC#52934

    // add start 2019/09/19 QC#53403
    /**
     * Estimate the meter count of the day
     * @param glblCmpyCd Global company code
     * @param dt The day
     * @param avgDlyCopyVol average daily copy volume
     * @param latestMtrRead Latest Meter read
     * @param scl Scale
     * @return Estimated meter count
     */
    public BigDecimal estMtrCnt(String glblCmpyCd, String dt, BigDecimal avgDlyCopyVol, SvcPhysMtrReadInfoBean latestMtrRead, int scl) {
        if (!ZYPCommonFunc.hasValue(dt)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(avgDlyCopyVol)) {
            return null;
        }
        if (latestMtrRead == null) {
            return null;
        }
        BigDecimal latestCnt = latestMtrRead.getReadMtrCnt();
        String latestDt = latestMtrRead.getMtrReadDt();
        BigDecimal estMtrCnt = null;
        if (dt.compareTo(latestDt) < 0) {
            return null;
        }
        int days = ZYPDateUtil.getDiffDays(dt, latestDt);
        estMtrCnt = avgDlyCopyVol.multiply(BigDecimal.valueOf(days)).add(latestCnt).setScale(scl, RoundingMode.HALF_UP);

        BigDecimal cntrMaxCnt = getCntrMaxCnt(glblCmpyCd, latestMtrRead.getSvcPhysMtrPk());
        if (cntrMaxCnt == null) {
            return estMtrCnt;
        }
        BigDecimal rollOverTimes = estMtrCnt.divide(cntrMaxCnt).setScale(scl, RoundingMode.FLOOR);
        return estMtrCnt.subtract(rollOverTimes.multiply(cntrMaxCnt));
    }
    // add end 2019/09/19 QC#53403
}
