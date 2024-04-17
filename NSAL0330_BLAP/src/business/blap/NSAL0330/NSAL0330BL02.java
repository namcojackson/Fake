/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0330;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0330.common.NSAL0330CommonLogic;
import business.blap.NSAL0330.constant.NSAL0330Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/21   Hitachi         T.Tomita        Update          N/A
 * 2015/12/04   Hitachi         K.Yamada        Update          CSA QC#1450,1453,1457
 * 2015/12/08   Hitachi         T.Kanasaka      Update          QC#1463
 * 2015/12/09   Hitachi         K.Yamada        Update          CSA QC#1728
 * 2016/02/18   Hitachi         T.Tomita        Update          CSA QC#3892
 * 2016/04/14   Hitachi         T.Kanasaka      Update          QC#4960
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/07/13   Hitachi         K.Kishimoto     Update          QC#11809
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/05   Hitachi         K.Kishimoto     Update          QC#12879
 * 2016/11/29   Hitachi         K.Ochiai        Update          QC#14204
 * 2018/05/11   Hitachi         K.Kim           Update          QC#25897
 *</pre>
 */
public class NSAL0330BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0330CMsg bizMsg = (NSAL0330CMsg) cMsg;
        NSAL0330SMsg sharedMsg = (NSAL0330SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0330_INIT".equals(screenAplID)) {
                doProcess_NSAL0330_INIT(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_CMN_Save(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_DeleteRow(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_InsertRow(bizMsg, sharedMsg);
            // START 2015/10/21 T.Tomita [N/A, DEL]
//            } else if ("NSAL0330Scrn00_OnChangeAmount".equals(screenAplID)) {
//                doProcess_NSAL0330Scrn00_OnChangeAmount(bizMsg, sharedMsg);
//            } else if ("NSAL0330Scrn00_OnChangeBllgCycle".equals(screenAplID)) {
//                doProcess_NSAL0330Scrn00_OnChangeBllgCycle(bizMsg, sharedMsg);
//            } else if ("NSAL0330Scrn00_OnChangeClosingDay".equals(screenAplID)) {
//                doProcess_NSAL0330Scrn00_OnChangeClosingDay(bizMsg, sharedMsg);
            // END 2015/10/21 T.Tomita [N/A, DEL]
            } else if ("NSAL0330Scrn00_OnChangeFromDate".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_OnChangeFromDate(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_OnChangePeriods".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_OnChangePeriods(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_OnChangeThruDate".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_OnChangeThruDate(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_OnChangeUOM".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_OnChangeUOM(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_Schedules".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_Schedules(bizMsg, sharedMsg);
            } else if ("NSAL0330Scrn00_SkipMonth".equals(screenAplID)) {
                doProcess_NSAL0330Scrn00_SkipMonth(bizMsg, sharedMsg);
            // START 2016/07/28 O.Okuma [QC#12430, ADD]
            } else if ("NSAL0330Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0330_INIT(bizMsg, sharedMsg);
            // END 2016/07/28 O.Okuma [QC#12430, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0330_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330_INIT(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {

        // START 2015/12/08 T.Kanasaka [QC#1463, MOD]
        NSAL0330CommonLogic.createHeaderFooterPulldownList(cMsg, getGlobalCompanyCode());
        // END 2015/12/08 T.Kanasaka [QC#1463, MOD]

        search(cMsg, sMsg);

        // START 2016/07/28 O.Okuma [QC#12430, ADD]
        cMsg.svcMemoRsnCd_F3.clear();
        cMsg.svcCmntTxt_F1.clear();
        // END 2016/07/28 O.Okuma [QC#12430, ADD]
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_DeleteRow <dd>The method
     * explanation: Add Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_DeleteRow(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(cMsg.A, NSAL0330Constant.XX_CHKBOX_A1, ZYPConstant.FLG_ON_Y);
        if (!selectRows.isEmpty()) {
            ZYPTableUtil.deleteRows(cMsg.A, selectRows);

            int cnt = cMsg.A.getValidCount();
            for (int i = 0; i < cnt; i++) {
                NSAL0330_ACMsg acMsg = cMsg.A.no(i);

                String sqNum = Integer.toString(i + 1);
                ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, sqNum);
            }
        } else {
            cMsg.setMessageInfo(NSAL0330Constant.NSAM0034E);
            return;
        }
        NSAL0330CommonLogic.calcTermAmout(cMsg);
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_InsertRow <dd>The method
     * explanation: AddRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_InsertRow(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {

        int addCount = 1;
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(cMsg.A, NSAL0330Constant.XX_CHKBOX_A1, ZYPConstant.FLG_ON_Y);
        if (!selectRows.isEmpty()) {
            addCount = selectRows.size();
        }
        if (cMsg.A.length() < cMsg.A.getValidCount() + addCount) {
            cMsg.setMessageInfo(NSAL0330Constant.NSAM0320E, new String[] {"Row", String.valueOf(cMsg.A.length()) });
            return;
        }

        if (selectRows.isEmpty()) {

            int index = cMsg.A.getValidCount();
            NSAL0330_ACMsg acMsg = cMsg.A.no(index);
            acMsg.clear();

            NSAL0330_ACMsg lastAcMsg = null;
            if (index != 0) {
                lastAcMsg = cMsg.A.no(index - 1);
            }

            setLastAddRowData(acMsg, cMsg, index, lastAcMsg);
            cMsg.A.setValidCount(index + 1);
        } else {
            NSAL0330CMsg workCMsg = new NSAL0330CMsg();

            int cnt = 0;
            int orgCnt = cMsg.A.getValidCount();
            for (int i = 0; i < orgCnt; i++) {
                NSAL0330_ACMsg orgAcMsg = cMsg.A.no(i);
                NSAL0330_ACMsg workAcMsg = workCMsg.A.no(cnt);
                if (selectRows.contains(i)) {
                    if (i == 0) {
                        setAddRowData(workAcMsg, cMsg, i, null, orgAcMsg);
                    } else {
                        setAddRowData(workAcMsg, cMsg, i, workCMsg.A.no(cnt - 1), orgAcMsg);
                    }
                    cnt++;

                    workAcMsg = workCMsg.A.no(cnt);
                    EZDMsg.copy(orgAcMsg, null, workAcMsg, null);
                    ZYPEZDItemValueSetter.setValue(workAcMsg.dsContrBllgSchdSqNum_A1, Integer.toString(cnt + 1));
                    cnt++;
                } else {
                    EZDMsg.copy(orgAcMsg, null, workAcMsg, null);
                    ZYPEZDItemValueSetter.setValue(workAcMsg.dsContrBllgSchdSqNum_A1, Integer.toString(cnt + 1));
                    cnt++;
                }
            }
            workCMsg.A.setValidCount(cnt);
            EZDMsg.copy(workCMsg.A, null, cMsg.A, null);
        }

        NSAL0330CommonLogic.calcTermAmout(cMsg);
    }

    private void setLastAddRowData(NSAL0330_ACMsg acMsg, NSAL0330CMsg cMsg, int index, NSAL0330_ACMsg lastAcMsg) {
        // START 2015/10/21 T.Tomita [N/A, MOD]
        ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, "1");
        } else {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, NSAL0330CommonLogic.getNextSeqNum(lastAcMsg));
        }
        ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(acMsg.dsContrPrcEffSqNum_A1, cMsg.dsContrPrcEffSqNum_H1);
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, cMsg.contrEffFromDt_H1);
        } else {
            if (lastAcMsg.bllgSchdThruDt_A1.getValue().equals(cMsg.contrEffThruDt_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, cMsg.contrEffThruDt_H1);
            } else {
                ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, ZYPDateUtil.addDays(lastAcMsg.bllgSchdThruDt_A1.getValue(), 1));
            }
        }

        ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, cMsg.contrEffThruDt_H1);
        ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A1, cMsg.baseBllgCycleCd_H1);
        ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleDescTxt_A1, cMsg.bllgCycleDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, cMsg.basePrcDealAmt_H1);
        ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAdjAmt_A1, BigDecimal.ZERO);
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, cMsg.ccyCd_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, lastAcMsg.ccyCd_A1);
        }

        // START 2016/02/18 T.Tomita [QC#3892, MOD]
        NSAL0330CommonLogic.calcPerSchdUom(acMsg, getGlobalCompanyCode());
        // END 2016/02/18 T.Tomita [QC#3892, MOD]
        // END 2015/10/21 T.Tomita [N/A, MOD]

        // del start 2015/12/04 CSA Defect#1450
        //NSAL0330CommonLogic.calcRowAmout(acMsg, cMsg, getGlobalCompanyCode());
        // del end 2015/12/04 CSA Defect#1450
        NSAL0330CommonLogic.calcTermAmout(cMsg);

        NSAL0330CommonLogic.createScheduleRowPulldownList(acMsg);
    }

    private void setAddRowData(NSAL0330_ACMsg acMsg, NSAL0330CMsg cMsg, int index, NSAL0330_ACMsg prevAcMsg, NSAL0330_ACMsg selectAcMsg) {
        // START 2015/10/21 T.Tomita [N/A, MOD]
        ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, "1");
        } else {
            ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, NSAL0330CommonLogic.getNextSeqNum(prevAcMsg));
        }
        ZYPEZDItemValueSetter.setValue(acMsg.dsContrPrcEffSqNum_A1, cMsg.dsContrPrcEffSqNum_H1);

        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, cMsg.contrEffFromDt_H1);
        } else {
            if (ZYPCommonFunc.hasValue(prevAcMsg.bllgSchdThruDt_A1)) {
                ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, ZYPDateUtil.addDays(prevAcMsg.bllgSchdThruDt_A1.getValue(), 1));
            }
        }

        if (ZYPCommonFunc.hasValue(selectAcMsg.bllgSchdFromDt_A1)) {
            ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, ZYPDateUtil.addDays(selectAcMsg.bllgSchdFromDt_A1.getValue(), -1));
            if (ZYPDateUtil.compare(acMsg.bllgSchdFromDt_A1.getValue(), acMsg.bllgSchdThruDt_A1.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, acMsg.bllgSchdFromDt_A1.getValue());
            }
        }

        if (index == 0) {
            ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A1, selectAcMsg.bllgCycleCd_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleDescTxt_A1, selectAcMsg.bllgCycleDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, selectAcMsg.basePrcDealAmt_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAdjAmt_A1, selectAcMsg.basePrcDealAdjAmt_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, selectAcMsg.ccyCd_A1);
        } else {
            ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A1, prevAcMsg.bllgCycleCd_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleDescTxt_A1, prevAcMsg.bllgCycleDescTxt_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, prevAcMsg.basePrcDealAmt_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAdjAmt_A1, prevAcMsg.basePrcDealAdjAmt_A1);
            ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, prevAcMsg.ccyCd_A1);
        }

        // START 2016/02/18 T.Tomita [QC#3892, MOD]
        NSAL0330CommonLogic.calcPerSchdUom(acMsg, getGlobalCompanyCode());
        // END 2016/02/18 T.Tomita [QC#3892, MOD]
        // END 2015/10/21 T.Tomita [N/A, MOD]

        // del start 2015/12/04 CSA Defect#1450
        //NSAL0330CommonLogic.calcRowAmout(acMsg, cMsg, getGlobalCompanyCode());
        // del end 2015/12/04 CSA Defect#1450
        NSAL0330CommonLogic.calcTermAmout(cMsg);

        NSAL0330CommonLogic.createScheduleRowPulldownList(acMsg);
    }

    // START 2015/10/21 T.Tomita [N/A, DEL]
//    /**
//     * Method name: doProcess_NSAL0330Scrn00_OnChangeClosingDay <dd>
//     * The method explanation: OnChangeClosingDay.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NSAL0330Scrn00_OnChangeClosingDay(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
//
//        if (isInvoiced(cMsg)) {
//            return;
//        }
//
//        String glblCmpyCd = getGlobalCompanyCode();
//        String cloDay = null;
//        if (NSAL0330Constant.RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH.equals(cMsg.xxRadioBtn_H1.getValue())) {
//            cloDay = NSAL0330Constant.MAX_DAY;
//        } else {
//            cloDay = cMsg.contrCloDay_H1.getValue();
//            if (!ZYPCommonFunc.hasValue(cloDay)) {
//                return;
//            }
//        }
//
//        String contrEffFromDt = cMsg.contrEffFromDt_H1.getValue();
//        if ("0".equals(cloDay)) {
//            setSchedule(glblCmpyCd, cMsg, 0, contrEffFromDt, cMsg.baseBllgCycleCd_H1.getValue());
//
//        } else if (NSAL0330Constant.MAX_DAY.equals(cloDay)) {
//            String lastDt = NSAL0330CommonLogic.getLastDate(contrEffFromDt);
//            setSchedule(glblCmpyCd, cMsg, lastDt);
//
//        } else {
//            String endDt = NSAL0330CommonLogic.getEndDate(contrEffFromDt, cloDay);
//            setSchedule(glblCmpyCd, cMsg, endDt);
//
//        }
//
//        int cnt = cMsg.A.getValidCount();
//        for (int i = 0; i < cnt; i++) {
//            NSAL0330CommonLogic.calcRowAmout(cMsg.A.no(i), cMsg, glblCmpyCd);
//        }
//        NSAL0330CommonLogic.calcTermAmout(cMsg);
//    }
//
//    private void setSchedule(String glblCmpyCd, NSAL0330CMsg cMsg, String endDt) {
//        int cnt = 0;
//        String baseBllgCycleCd = cMsg.baseBllgCycleCd_H1.getValue();
//        String targetFromDt = cMsg.contrEffFromDt_H1.getValue();
//        if (!BLLG_CYCLE.DAILY.equals(baseBllgCycleCd)) {
//            int diffDays = ZYPDateUtil.getDistance(targetFromDt, endDt, ZYPDateUtil.CALENDAR_GENERAL) + 1;
//
//            BLLG_CYCLETMsg dailyBllgCycle = NSAL0330CommonLogic.getBllgCycle(glblCmpyCd, BLLG_CYCLE.DAILY);
//            long amount = diffDays / dailyBllgCycle.bllgCycleAot.getValue().longValue();
//
//            String targetThruDt = ZYPDateUtil.addDays(targetFromDt, (int) (diffDays - 1));
//
//            NSAL0330_ACMsg acMsg = cMsg.A.no(cnt);
//            acMsg.clear();
//            ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, String.valueOf(cnt + 1));
//            ZYPEZDItemValueSetter.setValue(acMsg.perSchdNum_A1, new BigDecimal(amount));
//            ZYPEZDItemValueSetter.setValue(acMsg.perBllgCycleCd_A1, dailyBllgCycle.bllgCycleCd);
//            ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, targetFromDt);
//            ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, targetThruDt);
//            // START 2015/10/21 T.Tomita [N/A, MOD]
//            ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A1, baseBllgCycleCd);
//            // END 2015/10/21 T.Tomita [N/A, MOD]
//            ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, cMsg.basePrcDealAmt_H1);
//            ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, cMsg.ccyCd_H1);
//            NSAL0330CommonLogic.createScheduleRowPulldownList(acMsg);
//
//            cnt++;
//
//            targetFromDt = ZYPDateUtil.addDays(targetThruDt, 1);
//        }
//        setSchedule(glblCmpyCd, cMsg, cnt, targetFromDt, baseBllgCycleCd);
//    }
//
//    private void setSchedule(String glblCmpyCd, NSAL0330CMsg cMsg, int cnt, String targetFromDt, String baseBllgCycleCd) {
//
//        BLLG_CYCLETMsgArray bllgCycleTMsgArray = NSAL0330CommonLogic.getBLLG_CYCLETMsgArray(getGlobalCompanyCode());
//
//        BLLG_CYCLETMsg targetCycle = NSAL0330CommonLogic.getBllgCycle(glblCmpyCd, baseBllgCycleCd);
//        String contrEffThruDt = cMsg.contrEffThruDt_H1.getValue();
//        int diffDays = ZYPDateUtil.getDistance(targetFromDt, contrEffThruDt, ZYPDateUtil.CALENDAR_GENERAL) + 1;
//        while (diffDays > 0) {
//            int durationDays = NSAL0330CommonLogic.getDurationDays(bllgCycleTMsgArray, targetCycle.bllgCycleCd.getValue());
//
//            if (durationDays <= diffDays || BLLG_CYCLE.DAILY.equals(baseBllgCycleCd)) {
//                long amount = diffDays / durationDays;
//                diffDays = diffDays % durationDays;
//                String targetThruDt = ZYPDateUtil.addDays(targetFromDt, (int) (durationDays * amount - 1));
//
//                NSAL0330_ACMsg acMsg = cMsg.A.no(cnt);
//                acMsg.clear();
//                ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, String.valueOf(cnt + 1));
//                ZYPEZDItemValueSetter.setValue(acMsg.perSchdNum_A1, new BigDecimal(amount));
//                ZYPEZDItemValueSetter.setValue(acMsg.perBllgCycleCd_A1, targetCycle.bllgCycleCd);
//                ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, targetFromDt);
//                ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, targetThruDt);
//                // START 2015/10/21 T.Tomita [N/A, MOD]
//                ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A1, baseBllgCycleCd);
//                // END 2015/10/21 T.Tomita [N/A, MOD]
//                ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, cMsg.basePrcDealAmt_H1);
//                ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, cMsg.ccyCd_H1);
//                NSAL0330CommonLogic.createScheduleRowPulldownList(acMsg);
//
//                cnt++;
//                targetFromDt = ZYPDateUtil.addDays(targetThruDt, 1);
//            }
//
//            targetCycle = NSAL0330CommonLogic.getPrevBLLG_CYCLETMsg(bllgCycleTMsgArray, targetCycle);
//            if (targetCycle == null) {
//                break;
//            }
//        }
//        cMsg.A.setValidCount(cnt);
//    }
//
//    private boolean isInvoiced(NSAL0330CMsg cMsg) {
//        int cnt = cMsg.A.getValidCount();
//        for (int i = 0; i < cnt; i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).invFlg_A1.getValue())) {
//                return true;
//            }
//        }
//        return false;
//    }
    // END 2015/10/21 T.Tomita [N/A, DEL]

    // START 2015/10/21 T.Tomita [N/A, DEL]
//    /**
//     * Method name: doProcess_NSAL0330Scrn00_OnChangeBllgCycle <dd>The
//     * method explanation: OnChangeBllgCycle.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NSAL0330Scrn00_OnChangeBllgCycle(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
//        NSAL0330_ACMsg acMsg = cMsg.A.no(Integer.parseInt(cMsg.xxSelNum_H1.getValue()));
//        NSAL0330CommonLogic.calcRowAmout(acMsg, cMsg, getGlobalCompanyCode());
//        NSAL0330CommonLogic.calcTermAmout(cMsg);
//    }
    // END 2015/10/21 T.Tomita [N/A, DEL]

    /**
     * Method name: doProcess_NSAL0330Scrn00_OnChangeFromDate <dd>The
     * method explanation: OnChangeFromDate.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_OnChangeFromDate(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        // START 2016/02/18 T.Tomita [QC#3892, MOD]
        int selectIndex = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
        NSAL0330CommonLogic.calcThruDate(cMsg.A.no(selectIndex), getGlobalCompanyCode());
        // END 2016/02/18 T.Tomita [QC#3892, MOD]
        NSAL0330CommonLogic.calcTermAmout(cMsg);
    }

    // START 2015/10/21 T.Tomita [N/A, DEL]
//    /**
//     * Method name: doProcess_NSAL0330Scrn00_OnChangeAmount <dd>The
//     * method explanation: OnChangeAmount.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NSAL0330Scrn00_OnChangeAmount(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
//        NSAL0330_ACMsg acMsg = cMsg.A.no(Integer.parseInt(cMsg.xxSelNum_H1.getValue()));
//        NSAL0330CommonLogic.calcRowAmout(acMsg, cMsg, getGlobalCompanyCode());
//        NSAL0330CommonLogic.calcTermAmout(cMsg);
//    }
    // END 2015/10/21 T.Tomita [N/A, DEL]

    /**
     * Method name: doProcess_NSAL0330Scrn00_OnChangePeriods <dd>The
     * method explanation: OnChangePeriods.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_OnChangePeriods(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        // START 2016/02/18 T.Tomita [QC#3892, MOD]
        int selectIndex = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
        NSAL0330CommonLogic.calcThruDate(cMsg.A.no(selectIndex), getGlobalCompanyCode());
        // END 2016/02/18 T.Tomita [QC#3892, MOD]
        NSAL0330CommonLogic.calcTermAmout(cMsg);
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_OnChangeThruDate <dd>The
     * method explanation: OnChangeThruDate.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_OnChangeThruDate(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        // START 2016/02/18 T.Tomita [QC#3892, MOD]
        int selectIndex = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
        NSAL0330CommonLogic.calcPerSchdUom(cMsg.A.no(selectIndex), getGlobalCompanyCode());
        // END 2016/02/18 T.Tomita [QC#3892, MOD]
        NSAL0330CommonLogic.calcTermAmout(cMsg);
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_OnChangeUOM <dd>The
     * method explanation: OnChangeThruDate.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_OnChangeUOM(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        // START 2016/02/18 T.Tomita [QC#3892, MOD]
        int selectIndex = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
        NSAL0330CommonLogic.calcThruDate(cMsg.A.no(selectIndex), getGlobalCompanyCode());
        // END 2016/02/18 T.Tomita [QC#3892, MOD]
        NSAL0330CommonLogic.calcTermAmout(cMsg);
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_Schedules <dd>The method
     * explanation: Schedules.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_Schedules(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {

        doProcess_NSAL0330Scrn00_CMN_Save(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_CMN_Save <dd>The method
     * explanation: Reset values.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0330Scrn00_CMN_Save(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        if ("W".equals(cMsg.getMessageKind())) {
            return;
        }
        search(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NSAL0330Scrn00_SkipMonth <dd>The method
     * explanation: SkipMonth.
     * @param cMsg NSAL0330CMsg
     * @param sMsg NSAL0330SMsg
     */
    private void doProcess_NSAL0330Scrn00_SkipMonth(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        if (NSAL0330CommonLogic.isChanged(cMsg, sMsg)) {
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxBtnFlg_H1.getValue())) {
                cMsg.setMessageInfo(NSAL0330Constant.NSAM0338W);
                cMsg.xxBtnFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void search(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        S21SsmEZDResult res = NSAL0330Query.getInstance().getDsContrDtlInfo(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue());
        if (!res.isCodeNormal()) {
            String[] args = {"DS_CONTR_DTL" };
            cMsg.setMessageInfo(NSAL0330Constant.NSAM0045E, args);
            return;
        }

        List list = (List) res.getResultObject();
        Map map = (Map) list.get(0);
        setDsContrDtlInfo(cMsg, map);

        // START 2015/10/21 T.Tomita [N/A, DEL]
//        searchSvcMemo(cMsg, sMsg);
        // END 2015/10/21 T.Tomita [N/A, DEL]
        searchSchedules(cMsg, sMsg);

        NSAL0330CommonLogic.calcTermAmout(cMsg);

        EZDMsg.copy(cMsg, null, sMsg, null);
    }

    @SuppressWarnings("unchecked")
    private void searchSchedules(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult res = NSAL0330Query.getInstance().getScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > cMsg.A.length()) {
                listSize = cMsg.A.length();
                cMsg.setMessageInfo(NSAL0330Constant.NSAM0024W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
            }

            // mod start 2015/12/09 CSA Defect#1728
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                setScheduleInfo(map, cMsg.A.no(i), cMsg);
                NSAL0330CommonLogic.createScheduleRowPulldownList(cMsg.A.no(i));
                // START 2016/08/05 K.Kishimoto [QC#12879, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).invFlg_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.invFlg_H1, ZYPConstant.FLG_ON_Y);
                }
                // END 2016/08/05 K.Kishimoto [QC#12879, ADD]
            }
            // mod end 2015/12/09 CSA Defect#1728
            cMsg.A.setValidCount(listSize);
        }
    }

    // START 2015/10/21 T.Tomita [N/A, DEL]
//    @SuppressWarnings("unchecked")
//    private void searchSvcMemo(NSAL0330CMsg cMsg, NSAL0330SMsg sMsg) {
//        String glblCmpyCd = getGlobalCompanyCode();
//
//        S21SsmEZDResult res = NSAL0330Query.getInstance().getSvcMemo(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue());
//        if (res.isCodeNormal()) {
//            List list = (List) res.getResultObject();
//            Map map = (Map) list.get(0);
//            setSvcMemoInfo(map, cMsg);
//        }
//    }
    // END 2015/10/21 T.Tomita [N/A, DEL]

    @SuppressWarnings("unchecked")
    private void setDsContrDtlInfo(NSAL0330CMsg cMsg, Map map) {
        // START 2015/10/21 T.Tomita [N/A, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_H1, (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_H1, (String) map.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk_H1, (BigDecimal) map.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk_H1, (BigDecimal) map.get("DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPrcEffPk_H1, (BigDecimal) map.get("DS_CONTR_PRC_EFF_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPrcEffSqNum_H1, (BigDecimal) map.get("DS_CONTR_PRC_EFF_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.contrEffFromDt_H1, (String) map.get("CONTR_PRC_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.contrEffThruDt_H1, (String) map.get("CONTR_PRC_EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.baseBllgLastBllgDt_H1, (String) map.get("BASE_BLLG_LAST_BLLG_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.baseBllgTmgCd_H1, (String) map.get("BASE_BLLG_TMG_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.baseBillToCustCd_H1, (String) map.get("BASE_BILL_TO_CUST_CD"));
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        String contrCloDay = (String) map.get("CONTR_CLO_DAY");
        ZYPEZDItemValueSetter.setValue(cMsg.contrCloDay_H1, contrCloDay);
        String baseDplyPerEndDay = (String) map.get("BASE_DPLY_PER_END_DAY");
        // START 2016/08/05 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0330Constant.MAX_DAY.equals(baseDplyPerEndDay)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H1, NSAL0330Constant.RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH);
//            cMsg.baseDplyPerEndDay_H1.clear();
//        } else {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H1, NSAL0330Constant.RADIO_VALUE_CLOSING_DAY);
//            ZYPEZDItemValueSetter.setValue(cMsg.baseDplyPerEndDay_H1, baseDplyPerEndDay);
//        }
        ZYPEZDItemValueSetter.setValue(cMsg.baseDplyPerEndDay_H1, baseDplyPerEndDay);
        // END 2016/08/05 K.Kishimoto [QC#12879, MOD]
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
        String contrBllgDay = (String) map.get("CONTR_BLLG_DAY");
        // START 2016/08/05 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0330Constant.MAX_DAY.equals(contrBllgDay)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H2, NSAL0330Constant.RADIO_VALUE_BLLG_LAST_DAY_OF_MONTH);
//            cMsg.contrBllgDay_H1.clear();
//        } else {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H2, NSAL0330Constant.RADIO_VALUE_BLLG_DAY);
//            ZYPEZDItemValueSetter.setValue(cMsg.contrBllgDay_H1, contrBllgDay);
//        }
        ZYPEZDItemValueSetter.setValue(cMsg.contrBllgDay_H1, contrBllgDay);
        // END 2016/08/05 K.Kishimoto [QC#12879, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.baseBllgCycleCd_H1, (String) map.get("BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleDescTxt_H1, (String) map.get("BLLG_CYCLE_DESC_TXT"));
        // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleStartMth_H1, (String) map.get("BLLG_CYCLE_START_MTH"));
        // END 2016/05/18 T.Kanasaka [QC#2184, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.basePrcDealAmt_H1, (BigDecimal) map.get("BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum_H1, (String) map.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.ccyCd_H1, (String) map.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.serNum_H1, (String) map.get("SER_NUM"));
//        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H1, (String) map.get("T_MDL_NM"));
        // END 2015/10/21 T.Tomita [N/A, MOD]
        // START 2016/04/14 T.Kanasaka [QC#4960, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.invSeptBaseUsgFlg_H1, (String) map.get("INV_SEPT_BASE_USG_FLG"));
        // END 2016/04/14 T.Kanasaka [QC#4960, ADD]
        // START 2016/08/05 K.Kishimoto [QC#12879, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.invFlg_H1, ZYPConstant.FLG_OFF_N);
        // END 2016/08/05 K.Kishimoto [QC#12879, ADD]
        // START 2018/05/11 K.Kim [QC#25897, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd_H1, (String) map.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlTpCd_H1, (String) map.get("DS_CONTR_DTL_TP_CD"));
        // END 2018/05/11 K.Kim [QC#25897, ADD]
    }

    @SuppressWarnings("unchecked")
    private void setScheduleInfo(Map lineMap, NSAL0330_ACMsg acMsg, NSAL0330CMsg cMsg) {
        acMsg.clear();
        // START 2015/10/21 T.Tomita [N/A, MOD]
        ZYPEZDItemValueSetter.setValue(acMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(acMsg.ezUpTime_A1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(acMsg.ezUpTimeZone_A1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSmryPk_A1, (BigDecimal) lineMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsContrBllgSchdSqNum_A1, (String) lineMap.get("DS_CONTR_BLLG_SCHD_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.dsContrPrcEffSqNum_A1, (BigDecimal) lineMap.get("DS_CONTR_PRC_EFF_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.perSchdNum_A1, (BigDecimal) lineMap.get("PER_SCHD_NUM"));
        // Mod Start 07/13/2016 <QC#11809>
        ZYPEZDItemValueSetter.setValue(acMsg.perBllgCycleCd_A1, (String) lineMap.get("BLLG_CYCLE_UOM_CD"));
        // Mod End   07/13/2016 <QC#11809>
        ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdFromDt_A1, (String) lineMap.get("BLLG_SCHD_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(acMsg.bllgSchdThruDt_A1, (String) lineMap.get("BLLG_SCHD_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_A1, (String) lineMap.get("BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleDescTxt_A1, (String) lineMap.get("BLLG_CYCLE_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAmt_A1, (BigDecimal) lineMap.get("BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(acMsg.basePrcDealAdjAmt_A1, (BigDecimal) lineMap.get("BASE_PRC_DEAL_ADJ_AMT"));
        ZYPEZDItemValueSetter.setValue(acMsg.baseSubTotPrcDealAmt_A1, (BigDecimal) lineMap.get("BASE_SUB_TOT_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(acMsg.ccyCd_A1, (String) lineMap.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(acMsg.invFlg_A1, (String) lineMap.get("INV_FLG"));
        // END 2015/10/21 T.Tomita [N/A, MOD]
        // START 2016/11/29 K.Ochiai [QC#14204, ADD]
        ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistCratTs_A1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistCratByNm_A1, (String) lineMap.get("XX_REC_HIST_CRAT_BY_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistUpdTs_A1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistUpdByNm_A1, (String) lineMap.get("XX_REC_HIST_UPD_BY_NM"));
        ZYPEZDItemValueSetter.setValue(acMsg.xxRecHistTblNm_A1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
        // END 2016/11/29 K.Ochiai [QC#14204, ADD]
    }

    // START 2015/10/21 T.Tomita [N/A, DEL]
//    @SuppressWarnings("unchecked")
//    private void setSvcMemoInfo(Map lineMap, NSAL0330CMsg cMsg) {
//        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_F1, (String) lineMap.get("EZUPTIME"));
//        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_F1, (String) lineMap.get("EZUPTIMEZONE"));
//        ZYPEZDItemValueSetter.setValue(cMsg.svcMemoPk_F1, (BigDecimal) lineMap.get("SVC_MEMO_PK"));
//        ZYPEZDItemValueSetter.setValue(cMsg.svcMemoRsnCd_F3, (String) lineMap.get("SVC_MEMO_RSN_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt_F1, (String) lineMap.get("SVC_CMNT_TXT"));
//    }
    // END 2015/10/21 T.Tomita [N/A, DEL]

}
