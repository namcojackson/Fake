/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0690;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static business.blap.NSAL0690.constant.NSAL0690Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0690.common.NSAL0690CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/11/25   Hitachi         T.Tsuchida      Update          QC#994
 * 2015/11/26   Hitachi         T.Tsuchida      Update          QC#1225
 * 2015/12/15   Hitachi         T.Tsuchida      Update          QC#1659
 * 2015/12/23   Hitachi         T.Tsuchida      Update          QC#2383
 * 2016/01/04   Hitachi         K.Kasai         Create          QC#2384
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC#3694
 * 2016/04/08   Hitachi         T.Tomita        Update          QC#6729
 * 2016/05/11   Hitachi         T.Kanasaka      Update          QC#7907
 * 2017/01/13   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/09/28   Hitachi         K.Kojima        Update          QC#18376
 * 2017/10/27   Hitachi         K.Kojima        Update          QC#21742
 * 2017/12/06   Hitachi         K.Yamada        Update          QC#22891
 * 2019/05/14   Hitachi         A.Kohinata      Update          QC#50331
 * 2024/03/29   Hitachi         K.Ogasawara     Update          QC#63550
 *</pre>
 */
public class NSAL0690BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0690CMsg cMsg = (NSAL0690CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0690_INIT".equals(screenAplID)) {
                doProcess_NSAL0690_INIT(cMsg);
            } else if ("NSAL0690Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0690Scrn00_ApplyToAll(cMsg);
            } else if ("NSAL0690Scrn00_OnChangeDurationPeriod".equals(screenAplID)) {
                doProcess_NSAL0690Scrn00_OnChangeDurationPeriod(cMsg);
            } else if ("NSAL0690Scrn00_OpenWin_OveragePricing".equals(screenAplID)) {
                doProcess_NSAL0690Scrn00_OpenWin_OveragePricing(cMsg);
            // START 2015/11/26 T.Tsuchida [QC#1225,ADD]
            } else if ("NSAL0690Scrn00_OpenWin_Pricing".equals(screenAplID)) {
                doProcess_NSAL0690Scrn00_OpenWin_OveragePricing(cMsg);
            // END 2015/11/26 T.Tsuchida [QC#1225,ADD]
            // START 2017/01/13 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0690Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0690_CMN_Reset(cMsg);
            }
            // END 2017/01/13 K.Ochiai [QC#16331, MOD]
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0690_INIT(NSAL0690CMsg cMsg) {
        init(cMsg);
        // START 2024/03/29 K.Ogasawara [QC#63550, ADD]
        BigDecimal initialDuration = ZYPCodeDataUtil.getNumConstValue(RENEW_NOW_DURATION, cMsg.glblCmpyCd.getValue());
        String initialPeriod = ZYPCodeDataUtil.getVarCharConstValue(RENEW_NOW_PERIOD, cMsg.glblCmpyCd.getValue());
        if (hasValue(initialDuration) && hasValue(initialPeriod)) {
            setValue(cMsg.xxNum_H1, initialDuration);
            setValue(cMsg.bllgCycleUomCd_H3, initialPeriod);
        } else {
            setValue(cMsg.xxNum_H1, BigDecimal.ONE);
            setValue(cMsg.bllgCycleUomCd_H3, BLLG_CYCLE_UOM.YEARS);
        }
        doProcess_NSAL0690Scrn00_ApplyToAll(cMsg);
        // END 2024/03/29 K.Ogasawara [QC#63550, ADD]
    }

    // START 2017/01/13 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0690_CMN_Reset(NSAL0690CMsg cMsg) {
        // START 2024/03/29 K.Ogasawara [QC#63550, MOD]
        // init(cMsg);
        doProcess_NSAL0690_INIT(cMsg);
        // END 2024/03/29 K.Ogasawara [QC#63550, MOD]
    }
    // END 2017/01/13 K.Ochiai [QC#16331, MOD]

    // START 2016/05/11 T.Kanasaka [QC#7907,MOD]
    private void doProcess_NSAL0690Scrn00_ApplyToAll(NSAL0690CMsg cMsg) {
        BigDecimal dur = cMsg.xxNum_H1.getValue();
        String bllgCycleUomCd = cMsg.bllgCycleUomCd_H3.getValue();
//        int dur = cMsg.xxNum_H1.getValueInt();
//        int bllgCycle;
//        int bllgCycleMthAot = 0;
//
//        //getBllgCycle from BllgCycleUom
//        bllgCycle = setBllgCycle(cMsg.bllgCycleUomCd_H3.getValue());
//
//        //get BLLG_CYCLE_MTH_AOT
//        if (bllgCycle != Calendar.WEEK_OF_MONTH) {
//            BLLG_CYCLETMsgArray tMsgArray = NSAL0690CommonLogic.getBllgCycleData(cMsg.glblCmpyCd.getValue(), cMsg.bllgCycleUomCd_H3.getValue());
//            if (tMsgArray.getValidCount() == 0) {
//                cMsg.bllgCycleUomCd_H3.setErrorInfo(1, NSAM0011E, new String[]{cMsg.bllgCycleUomCd_H3.getValue()});
//                return;
//            }
//            bllgCycleMthAot = tMsgArray.no(0).bllgCycleMthAot.getValueInt();
//        }

        // add start 2016/02/23 CSA Defect#4132
        BigDecimal dsContrPk = BigDecimal.ONE;
        int idxA = -1;
        // add end 2016/02/23 CSA Defect#4132
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            // add start 2016/02/23 CSA Defect#4132
            if (i == 0 || !cMsg.B.no(i).dsContrPk_B1.getValue().equals(dsContrPk)) {
                dsContrPk = cMsg.B.no(i).dsContrPk_B1.getValue();
                idxA++;
            }
            // START 2017/10/27 K.Kojima [QC#21742,MOD]
            // if (protectContrLine(cMsg.A.no(idxA).dsContrCtrlStsCd_A1.getValue())) {
            if (protectContrLine(cMsg.A.no(idxA).contrRnwAvalFlg_A1.getValue())) {
            // END 2017/10/27 K.Kojima [QC#21742,MOD]
                continue;
            }
            // add end 2016/02/23 CSA Defect#4132
            NSAL0690_BCMsg bCMsg = cMsg.B.no(i);

            String endDate = bCMsg.contrEffThruDt_B1.getValue();
//            int yyyy = Integer.parseInt(endDate.substring(0, 4));
//            int mm = Integer.parseInt(endDate.substring(4, 6)) - 1;
//            int dd = Integer.parseInt(endDate.substring(6, 8));
//            Calendar cal = Calendar.getInstance();
//            cal.set(yyyy, mm, dd);
//            //add duration to endDate
//            if (bllgCycle == Calendar.MONTH) {
//                cal.add(bllgCycle, dur * bllgCycleMthAot);
//            } else {
//                cal.add(bllgCycle, dur);
//            }
//            //set End Of Month
//            if (isEndOfMonth(endDate) && bllgCycle == Calendar.MONTH) {
//                cal.getActualMaximum(Calendar.DATE);
//            }
//            //set new Period Date
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
//            setValue(bCMsg.xxThruDt_B1, fmt.format(cal.getTime()));

            ContrDurationInfo param = new ContrDurationInfo();
            param.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
            param.setContrEffFromDt(ZYPDateUtil.addDays(endDate, 1));
            param.setContrDurnNum(dur);
            param.setCycleUomCd(bllgCycleUomCd);
            NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
            calculator.calcEndDt();
            setValue(bCMsg.xxThruDt_B1, param.getContrEffThruDt());
        }
    }

    private void doProcess_NSAL0690Scrn00_OnChangeDurationPeriod(NSAL0690CMsg cMsg) {
        int rowNumber = cMsg.rowSqNum_H1.getValueInt();

        NSAL0690_ACMsg aCMsg = cMsg.A.no(rowNumber);
        BigDecimal dur = aCMsg.xxNum_A1.getValue();
        String bllgCycleUomCd = aCMsg.bllgCycleUomCd_A3.getValue();
//        int dur = aCMsg.xxNum_A1.getValueInt();
//        int bllgCycle;
//        int bllgCycleMthAot;
//
//        //getBllgCycle from BllgCycleUom
//        bllgCycle = setBllgCycle(aCMsg.bllgCycleUomCd_A3.getValue());
//
//        //get BLLG_CYCLE_MTH_AOT
//        BLLG_CYCLETMsgArray tMsgArray = NSAL0690CommonLogic.getBllgCycleData(cMsg.glblCmpyCd.getValue(), aCMsg.bllgCycleUomCd_A3.getValue());
//        if (tMsgArray.getValidCount() == 0) {
//            return;
//        }
//        bllgCycleMthAot = tMsgArray.no(0).bllgCycleMthAot.getValueInt();

        BigDecimal dsContrPk = aCMsg.dsContrPk_A1.getValue();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0690_BCMsg bCMsg = cMsg.B.no(i);
            if (!dsContrPk.equals(bCMsg.dsContrPk_B1.getValue())) {
                continue;
            }

            String endDate = bCMsg.contrEffThruDt_B1.getValue();
//            int yyyy = Integer.parseInt(endDate.substring(0, 4));
//            int mm = Integer.parseInt(endDate.substring(4, 6)) - 1;
//            int dd = Integer.parseInt(endDate.substring(6, 8));
//            Calendar cal = Calendar.getInstance();
//            cal.set(yyyy, mm, dd);
//            //add duration to endDate
//            if (bllgCycle == Calendar.MONTH) {
//                cal.add(bllgCycle, dur * bllgCycleMthAot);
//            } else {
//                cal.add(bllgCycle, dur);
//            }
//            //set End Of Month
//            if (isEndOfMonth(endDate) && bllgCycle == Calendar.MONTH) {
//                cal.getActualMaximum(Calendar.DATE);
//            }
//            //set new Period Date
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
//            setValue(bCMsg.xxThruDt_B1, fmt.format(cal.getTime()));

            ContrDurationInfo param = new ContrDurationInfo();
            param.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
            param.setContrEffFromDt(ZYPDateUtil.addDays(endDate, 1));
            param.setContrDurnNum(dur);
            param.setCycleUomCd(bllgCycleUomCd);
            NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
            calculator.calcEndDt();
            setValue(bCMsg.xxThruDt_B1, param.getContrEffThruDt());
        }
    }
    // END 2016/05/11 T.Kanasaka [QC#7907,MOD]

    private void doProcess_NSAL0690Scrn00_OpenWin_OveragePricing(NSAL0690CMsg cMsg) {
        if (!hasValue(cMsg.rowSqNum_H1)) {
            return;
        }
        NSAL0690_ACMsg aCMsg = cMsg.A.no(cMsg.rowSqNum_H1.getValueInt());
        // START 2015/11/26 T.Tsuchida [QC#1225,MOD]
        //S21SsmEZDResult ssmResult = NSAL0690Query.getInstance().getPrcUpRatio(queryMap);
        //if (ssmResult.isCodeNormal()) {
        //    NSAL0690_ACMsgArray outMsg = (NSAL0690_ACMsgArray) ssmResult.getResultObject();
        //    setValue(aCMsg.dsContrDtlPk_A1, outMsg.no(0).dsContrDtlPk_A1.getValue());
        //    setValue(aCMsg.mtrPrcUpRatio_A1, outMsg.no(0).mtrPrcUpRatio_A1.getValue());
        //}
        Map<String, Object> rsltMap = NSAL0690CommonLogic.getPrcUpRatio(cMsg, aCMsg, OVERAGE);
        if (rsltMap != null && !rsltMap.isEmpty()) {
            setValue(aCMsg.dsContrDtlPk_A1, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            setValue(aCMsg.mtrPrcUpRatio_A1, (BigDecimal) rsltMap.get("MTR_PRC_UP_RATIO"));
        }
        // END 2015/11/26 T.Tsuchida [QC#1225,MOD]
    }

    // START 2016/05/11 T.Kanasaka [QC#7907,DEL]
//    /**
//     * @param endDate
//     * @param cal
//     * @return
//     */
//    private boolean isEndOfMonth(String endDate) {
//        boolean isEndOfMonth;
//        int yyyy = Integer.parseInt(endDate.substring(0, 4));
//        int mm = Integer.parseInt(endDate.substring(4, 6)) - 1;
//        int dd = Integer.parseInt(endDate.substring(6, 8));
//        Calendar cal = Calendar.getInstance();
//        cal.set(yyyy, mm, dd);
//        if (endDate.substring(6, 8).equals(String.valueOf(cal.getActualMaximum(Calendar.DATE)))) {
//            isEndOfMonth = true;
//        } else {
//            isEndOfMonth = false;
//        }
//        return isEndOfMonth;
//    }
//
//    /**
//     * @param cMsg
//     * @return
//     */
//    private int setBllgCycle(String bllgCycleUomCd) {
//        int bllgCycle;
//        if (BLLG_CYCLE_UOM.DAYS.equals(bllgCycleUomCd)) {
//            bllgCycle = Calendar.DAY_OF_MONTH;
//        } else if (BLLG_CYCLE_UOM.WEEKS.equals(bllgCycleUomCd)) {
//            bllgCycle = Calendar.WEEK_OF_MONTH;
//        } else {
//            bllgCycle = Calendar.MONTH;
//        }
//        return bllgCycle;
//    }
    // END 2016/05/11 T.Kanasaka [QC#7907,DEL]

    private void init(NSAL0690CMsg cMsg) {
        cMsg.xxNum_H1.clear();
        cMsg.bllgCycleUomCd_H3.clear();
        cMsg.svcCmntTxt_H1.clear();
        cMsg.svcMemoRsnCd_H3.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0690CommonLogic.createPullDown(cMsg);
        findContrHdrInfo(cMsg);
        findContrDtlInfo(cMsg);
        setNewPeriodEnd(cMsg);
        setHeaderAmount(cMsg);
    }

    /**
     * @param cMsg
     * @return
     */
    private void findContrHdrInfo(NSAL0690CMsg cMsg) {

        Map<String, Object> queryMap = setParamForSearch(cMsg);
        S21SsmEZDResult ssmResult = NSAL0690Query.getInstance().getContrHdrInfo(queryMap, cMsg.A);
        cMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
        }
    }

    /**
     * @param cMsg
     * @return
     */
    private void findContrDtlInfo(NSAL0690CMsg cMsg) {

        Map<String, Object> queryMap = setParamForSearch(cMsg);
        S21SsmEZDResult ssmResult = NSAL0690Query.getInstance().getContrDtlInfo(queryMap, cMsg.B);
        cMsg.B.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        if (ssmResult.getQueryResultCount() > SEARCH_LIMIT_CNT) {
            cMsg.setMessageInfo(NZZM0001W);
            cMsg.B.setValidCount(cMsg.B.length());
        }
    }

    /**
     * @param cMsg
     * @return
     */
    private void setNewPeriodEnd(NSAL0690CMsg cMsg) {

        NSAL0690_BCMsgArray bMsgArray = cMsg.B;
        String newPeriodEnd = null;
        for (int i = 0; i < bMsgArray.getValidCount(); i++) {
            NSAL0690_BCMsg bMsg = bMsgArray.no(i);
            newPeriodEnd = NSAL0690Query.getInstance().getNewPeriodEnd(cMsg, bMsg);
            setValue(bMsg.xxThruDt_B1, newPeriodEnd);
        }
    }

    /**
     * @param cMsg
     * @return
     */
    private void setHeaderAmount(NSAL0690CMsg cMsg) {

        NSAL0690_ACMsgArray aMsgArray = cMsg.A;
        NSAL0690_BCMsgArray bMsgArray = cMsg.B;
        BigDecimal baseDealAmt = BigDecimal.ZERO;
        BigDecimal newDealAmt = BigDecimal.ZERO;
        for (int i = 0; i < aMsgArray.getValidCount(); i++) {
            NSAL0690_ACMsg aMsg = aMsgArray.no(i);
            baseDealAmt = BigDecimal.ZERO;
            newDealAmt = BigDecimal.ZERO;
            if (hasValue(aMsg.dsContrCatgCd_A1)
                    && !DS_CONTR_CATG.FLEET.equals(aMsg.dsContrCatgCd_A1.getValue())) {
                for (int j = 0; j < bMsgArray.getValidCount(); j++) {
                    NSAL0690_BCMsg bMsg = bMsgArray.no(j);
                    if (hasValue(bMsg.xxScrItem8Txt_B1)
                            && aMsg.dsContrPk_A1.getValue().equals(bMsg.dsContrPk_B1.getValue())
                            && DETAIL_BASE_NM.equals(bMsg.xxScrItem8Txt_B1.getValue())) {
                        baseDealAmt = getSumAmount(baseDealAmt, bMsg.basePrcDealAmt_B1.getValue());
                        newDealAmt = getSumAmount(newDealAmt, bMsg.newBaseDealAmt_B1.getValue());
                    }
                }
                setValue(aMsg.basePrcDealAmt_A1, baseDealAmt);
                setValue(aMsg.newBaseDealAmt_A1, newDealAmt);
            // START 2017/09/28 K.Kojima [QC#18376,ADD]
            } else if (hasValue(aMsg.dsContrCatgCd_A1) && DS_CONTR_CATG.FLEET.equals(aMsg.dsContrCatgCd_A1.getValue())
                    && hasValue(aMsg.rnwPrcMethCd_AB) && aMsg.rnwPrcMethCd_AB.getValue().equals(RNW_PRC_METH.MODEL_PERCENT)) {
                // START 2017/12/06 [QC#22891,MOD]
                //newDealAmt = NSAL0690Query.getInstance().getFleetLineNewAmount(cMsg.glblCmpyCd.getValue(), aMsg.dsContrPk_A1.getValue(), aMsg.basePrcDealAmt_A1.getValue());
                newDealAmt = getFleetLineNewAmount(cMsg.glblCmpyCd.getValue(), aMsg.basePrcDealAmt_A1.getValue());
                // END 2017/12/06 [QC#22891,MOD]
                setValue(aMsg.newBaseDealAmt_A1, newDealAmt);
            // END 2017/09/28 K.Kojima [QC#18376,ADD]
            }
        }
    }

    private BigDecimal getSumAmount(BigDecimal inDecimal1, BigDecimal inDecimal2) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        if (hasValue(inDecimal1)) {
            rtnVal = inDecimal1;
        }
        if (hasValue(inDecimal2)) {
            rtnVal = rtnVal.add(inDecimal2);
        }
        return rtnVal;
    }

    /**
     * @param cMsg
     * @return
     */
    private Map<String, Object> setParamForSearch(NSAL0690CMsg cMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList.add(cMsg.P.no(i).dsContrPk_P1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }
        queryMap.put("dsContrPkArray", dsContrPkList);
        queryMap.put("dsContrDtlPkArray", dsContrDtlPkList);
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]
        queryMap.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        queryMap.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        queryMap.put("dsContrCatgReg", DS_CONTR_CATG.REGULAR);
        queryMap.put("dsContrCatgFlt", DS_CONTR_CATG.FLEET);
        queryMap.put("dsContrCatgAgg", DS_CONTR_CATG.AGGREGATE);
        queryMap.put("baseChrgFlg", FLG_ON_Y);
        queryMap.put("usgChrgFlg", FLG_ON_Y);
        queryMap.put("mainUnitLineFlg", FLG_ON_Y);
        // START 2015/11/25 T.Tsuchida [QC#994,MOD]
        //queryMap.put("fltLineFlg", FLG_ON_Y);
        queryMap.put("fltLineFlg", FLG_OFF_N);
        // END 2015/11/25 T.Tsuchida [QC#994,MOD]
        queryMap.put("detailBaseNm", DETAIL_BASE_NM);
        queryMap.put("detailOverageNm", DETAIL_OVERAGE_NM);
        queryMap.put("baseNm", BASE);
        queryMap.put("usgNm", OVERAGE);
        queryMap.put("MachLvlNum3", LVL_NUM_3);
        queryMap.put("MachLvlNum2", LVL_NUM_2);
        queryMap.put("MachLvlNum1", LVL_NUM_1);
        // START 2017/09/28 K.Kojima [QC#18376,ADD]
        queryMap.put("rnwPrcMethMarkup", RNW_PRC_METH.MARKUP_PERCENT);
        queryMap.put("rnwPrcMethModel", RNW_PRC_METH.MODEL_PERCENT);
        // END 2017/09/28 K.Kojima [QC#18376,ADD]
        // add start 2019/05/14 QC#50331
        queryMap.put("dsContrCratTp", DS_CONTR_CRAT_TP.OTHER);
        // add end 2019/05/14 QC#50331
        return queryMap;
    }

    // add start 2016/02/23 CSA Defect#4132
    // START 2017/10/27 K.Kojima [QC#21742,MOD]
    // /**
    //  * @param contrCtrlStsCd String
    //  */
    // private boolean protectContrLine(String contrCtrlStsCd) {
    //     if (!DS_CONTR_CTRL_STS.ACTIVE.equals(contrCtrlStsCd)
    //             && !DS_CONTR_CTRL_STS.SINGED.equals(contrCtrlStsCd)
    //             && !DS_CONTR_CTRL_STS.RENEWAL_HOLD.equals(contrCtrlStsCd)) {
    //         return true;
    //     }
    //     return false;
    // }
    // END 2017/10/27 K.Kojima [QC#21742,MOD]
    /**
     * @param contrRnwAvalFlg String
     */
    private boolean protectContrLine(String contrRnwAvalFlg) {
        if (!ZYPCommonFunc.hasValue(contrRnwAvalFlg) || ZYPConstant.FLG_OFF_N.equals(contrRnwAvalFlg)) {
            return true;
        }
        return false;
    }
    // add end 2016/02/23 CSA Defect#4132

    // START 2017/12/06 [QC#22891,ADD]
    private BigDecimal getFleetLineNewAmount(String glblCmpyCd, BigDecimal basePrcDealAmt) {
        BigDecimal defPrcUpRatio = ZYPCodeDataUtil.getNumConstValue("DEF_MDL_RULE_BASE", glblCmpyCd);

        if (!hasValue(basePrcDealAmt)) {
            return null;
        }
        if (!hasValue(defPrcUpRatio)) {
            return null;
        }
        BigDecimal hundred = new BigDecimal("100");
        int digitNum = 2;
        return basePrcDealAmt.multiply((hundred.add(defPrcUpRatio)).divide(hundred)).setScale(digitNum, BigDecimal.ROUND_HALF_UP);
    }
    // END 2017/12/06 [QC#22891,ADD]
}
