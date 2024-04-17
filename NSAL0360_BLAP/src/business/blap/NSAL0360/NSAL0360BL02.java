/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0360;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import business.blap.NSAL0330.constant.NSAL0330Constant;
import business.blap.NSAL0360.common.NSAL0360CommonLogic;
import business.blap.NSAL0360.constant.NSAL0360Constant;
import business.db.DS_CONTR_PRC_EFFTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
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
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2015/10/23   Hitachi         T.Tomita        Update          N/A
 * 2015/12/10   Hitachi         A.Kohinata      Update          QC1727
 * 2016/03/01   Hitachi         T.Tomita        Update          QC#4131
 * 2016/04/14   Hitachi         T.Kanasaka      Update          QC#4960
 * 2016/05/13   Hitachi         T.Kanasaka      Update          QC#7916
 * 2016/05/18   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/07/13   Hitachi         K.Kishimoto     Update          QC#11809
 * 2016/07/28   Hitachi         O.Okuma         Update          QC#12430
 * 2016/08/08   Hitachi         K.Kishimoto     Update          QC#12879
 * 2016/08/08   Hitachi         K.Kishimoto     Update          QC#12310
 * 2016/12/01   Hitachi         K.Ochiai        Update          QC#14204
 * 2017/08/07   Hitachi         E.Kameishi      Update          QC#18586
 *</pre>
 */
public class NSAL0360BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0360CMsg bizMsg = (NSAL0360CMsg) cMsg;
        NSAL0360SMsg sharedMsg = (NSAL0360SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0360_INIT".equals(screenAplID)) {
                doProcess_NSAL0360_INIT(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_CMN_Save(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_DeleteRow(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_InsertRow(bizMsg, sharedMsg);
            // START 2015/10/23 T.Tomita [N/A, MOD]
//            } else if ("NSAL0360Scrn00_OnChangeAmount".equals(screenAplID)) {
//                doProcess_NSAL0360Scrn00_OnChangeAmount(bizMsg, sharedMsg);
//            } else if ("NSAL0360Scrn00_OnChangeBllgCycle".equals(screenAplID)) {
//                doProcess_NSAL0360Scrn00_OnChangeBllgCycle(bizMsg, sharedMsg);
//            } else if ("NSAL0360Scrn00_OnChangeClosingDay".equals(screenAplID)) {
//                doProcess_NSAL0360Scrn00_OnChangeClosingDay(bizMsg, sharedMsg);
            // END 2015/10/23 T.Tomita [N/A, MOD]
            } else if ("NSAL0360Scrn00_OnChangeFromDate".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_OnChangeFromDate(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_OnChangePeriods".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_OnChangePeriods(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_OnChangeThruDate".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_OnChangeThruDate(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_OnChangeUOM".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_OnChangeUOM(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_Schedules".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_Schedules(bizMsg, sharedMsg);
            } else if ("NSAL0360Scrn00_SkipMonth".equals(screenAplID)) {
                doProcess_NSAL0360Scrn00_SkipMonth(bizMsg, sharedMsg);
            // START 2016/07/28 O.Okuma [QC#12430, ADD]
            } else if ("NSAL0360Scrn00_CMN_Reset".equals(screenAplID)) {
                    doProcess_NSAL0360_INIT(bizMsg, sharedMsg);
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
     * Method name: doProcess_NSAL0360_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360_INIT(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        // START 2015/12/10 [QC1727, MOD]
        NSAL0360CommonLogic.createHeaderFooterPulldownList(cMsg, getGlobalCompanyCode());
        // END 2015/12/10 [QC1727, MOD]

        search(cMsg, sMsg);

        // START 2016/07/28 O.Okuma [QC#12430, ADD]
        cMsg.svcMemoRsnCd_F3.clear();
        cMsg.svcCmntTxt_F1.clear();
        // END 2016/07/28 O.Okuma [QC#12430, ADD]
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_DeleteRow <dd>The method
     * explanation: Add Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_DeleteRow(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        getSelectedDsContrBllgSchdSmryPk(cMsg);
    }

    private void getSelectedDsContrBllgSchdSmryPk(NSAL0360CMsg cMsg) {

        List<String> list = new ArrayList<String>();

        String tblNm = cMsg.srcTblNm_P1.getValue();
        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(tblArray, NSAL0360CommonLogic.replSfx("xxChkBox_A1", tblNm), ZYPConstant.FLG_ON_Y);
        // START 2016/05/13 T.Kanasaka [QC#7916, ADD]
        if (selectRows.isEmpty()) {
            cMsg.setMessageInfo(NSAL0360Constant.NSAM0034E);
            return;
        }
        // END 2016/05/13 T.Kanasaka [QC#7916, ADD]

        for (int row : selectRows) {
            EZDMsg ezdMsg = tblArray.get(row);
            EZDCStringItem sqItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            list.add(sqItem.getValue());
        }

        List<Integer> deleteList = new ArrayList<Integer>();
        int cnt = tblArray.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg ezdMsg = tblArray.get(i);
            EZDCStringItem sqItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            if (hasValue(sqItem.getValue(), list)) {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(tblArray, deleteList);

        EZDMsg prevEzdMsg = tblArray.get(0);
        EZDCStringItem prevSqItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(prevEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
        String prevSq = prevSqItem.getValue();
        int sqNum = 1;
        cnt = tblArray.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg ezdMsg = tblArray.get(i);
            EZDCStringItem sqItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            if (!prevSq.equals(sqItem.getValue())) {
                sqNum++;
                prevSq = sqItem.getValue();
            }
            ZYPEZDItemValueSetter.setValue(sqItem, Integer.toString(sqNum));
        }

    }

    private boolean hasValue(String value, List<String> list) {
        int cnt = list.size();
        for (int i = 0; i < cnt; i++) {
            if (value.equals(list.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_InsertRow <dd>The method
     * explanation: AddRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    private void doProcess_NSAL0360Scrn00_InsertRow(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        String tblNm = cMsg.srcTblNm_P1.getValue();
        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);

        int addCount = 1;
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(tblArray, NSAL0360CommonLogic.replSfx("xxChkBox_A1", tblNm), ZYPConstant.FLG_ON_Y);
        if (!selectRows.isEmpty()) {
            addCount = selectRows.size();
        }

        int length = tblArray.length();
        if (length < tblArray.getValidCount() + addCount) {
            cMsg.setMessageInfo(NSAL0360Constant.NSAM0320E, new String[] {"Row", String.valueOf(length) });
            return;
        }

        // SATRT 2015/10/27 T.Tomita [N/A, MOD]
        BigDecimal dsContrBllgMtrPk = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cMsg, tblNm, "dsContrBllgMtrPk_A").getValue();
        // START 2016/05/13 T.Kanasaka [QC#7916, MOD]
        S21SsmEZDResult res = NSAL0360Query.getInstance().getContrXsCopyList(getGlobalCompanyCode(), dsContrBllgMtrPk, cMsg.dsContrPrcEffPk_HD.getValue());
        // END 2016/05/13 T.Kanasaka [QC#7916, MOD]
        if (!res.isCodeNormal()) {
            String[] args = {"CONTR_XS_COPY" };
            cMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
            return;
        }

        List<Map<String, Object>> xsCopyList = (List<Map<String, Object>>) res.getResultObject();

        if (selectRows.isEmpty()) {
            int cnt = tblArray.getValidCount();
            addLastLineData(cMsg, tblArray, tblNm, cnt, xsCopyList);

//            if (cnt == 0) {
//                // nothing
//                setLastAddRowDataNew(xsCopyList, cMsg, tblNm, tblArray);
//            } else {
//                EZDMsg lastEzdMsg = tblArray.get(cnt - 1);
//                EZDCStringItem lastSqNumItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(lastEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
//
//                String lastSqNum = lastSqNumItem.getValue();
//                List<Integer> indexList = new ArrayList<Integer>();
//                for (int i = 0; i < cnt; i++) {
//                    EZDMsg ezdMsg = tblArray.get(i);
//                    EZDCStringItem curSqNumItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
//
//                    if (lastSqNum.equals(curSqNumItem.getValue())) {
//                        indexList.add(i);
//                    }
//                }
//                setLastAddRowData(indexList, cMsg, tblNm, tblArray, lastEzdMsg);
//            }
        } else {
            NSAL0360CMsg workCMsg = new NSAL0360CMsg();
            EZDMsgArray workTblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(workCMsg, tblNm);

            int orgCnt = tblArray.getValidCount();
            String targetSq = null;
            boolean checkFoundFlg = false;
            List<Integer> indexList = new ArrayList<Integer>();
            for (int i = 0; i < orgCnt; i++) {
                EZDMsg orgEzdMsg = tblArray.get(i);
                EZDCStringItem orgSqItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(orgEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");

                String orgSq = orgSqItem.getValue();
                if (targetSq == null || !targetSq.equals(orgSq)) {
                    if (checkFoundFlg) {
                        setAddRowData(indexList, cMsg, tblNm, tblArray, workTblArray, xsCopyList);
                    }
                    setCopyRowData(indexList, cMsg, tblNm, tblArray, workTblArray);

                    checkFoundFlg = false;
                    indexList.clear();
                    targetSq = orgSq;
                }

                indexList.add(i);
                if (selectRows.contains(i)) {
                    checkFoundFlg = true;
                }

            }
            if (checkFoundFlg) {
                setAddRowData(indexList, cMsg, tblNm, tblArray, workTblArray, xsCopyList);
            }
            setCopyRowData(indexList, cMsg, tblNm, tblArray, workTblArray);

            EZDMsg.copy(workTblArray, null, tblArray, null);
        }
        // END 2015/10/27 T.Tomita [N/A, MOD]
   }

    // START 2015/10/23 T.Tomita [N/A, DEL]
//    @SuppressWarnings("unchecked")
//    private void setLastAddRowDataNew(List xsCopyList, NSAL0360CMsg cMsg, String tblNm, EZDMsgArray tblArray) {
//
//        if (xsCopyList.size() > 0) {
//
//            String sq = "1";
//            String bllgSchdFromDt = cMsg.contrEffFromDt_H1.getValue();
//            String bllgSchdThruDt = cMsg.contrEffThruDt_H1.getValue();
//
//            EZDCBigDecimalItem workItem = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cMsg, tblNm, "dsContrBllgMtrPk_A");
//            BigDecimal dsContrBllgMtrPk = workItem.getValue();
//            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = NSAL0360CommonLogic.findDsContrBllgMtr(getGlobalCompanyCode(), dsContrBllgMtrPk);
//            if (dsContrBllgMtrTMsg == null) {
//                String[] args = {"DS_CONTR_BLLG_MTR" };
//                cMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
//                return;
//            }
//            String bllgMtrBllgCycleCd = dsContrBllgMtrTMsg.bllgMtrBllgCycleCd.getValue();
//
//            // NSXC001001Contr is not completed.
//            ScheduleInfo info = NSXC001001Contr.getScheduleInfo(getGlobalCompanyCode(), bllgSchdFromDt, bllgSchdThruDt, bllgMtrBllgCycleCd);
//
//            int cnt = 0;
//            BigDecimal xsCopyListSize = new BigDecimal(xsCopyList.size());
//            for (int i = 0; i < xsCopyListSize.intValue(); i++) {
//                Map map = (Map) xsCopyList.get(i);
//                EZDMsg ezdMsg = tblArray.get(cnt);
//                ezdMsg.clear();
//
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), sq);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1"), (BigDecimal) map.get("DS_CONTR_PRC_EFF_SQ_NUM"));
//                // NSXC001001Contr is not completed.
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), info.getPerSchdNum());
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), info.getBllgCycleUomCd());
//
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDt);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDt);
//
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleCd_A1"), (String) map.get("BLLG_CYCLE_CD"));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleDescTxt_A1"), (String) map.get("BLLG_CYCLE_DESC_TXT"));
//
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1"), (BigDecimal) map.get("XS_MTR_COPY_QTY"));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1"), (BigDecimal) map.get("XS_MTR_AMT_RATE"));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1"), (String) map.get("CCY_CD"));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xxDtlCnt_A1"), xsCopyListSize);
//
//                NSAL0360CommonLogic.calcRowAmout(ezdMsg, tblNm, cMsg, getGlobalCompanyCode());
//                NSAL0360CommonLogic.createScheduleRowPulldownList(ezdMsg, tblNm);
//
//                cnt++;
//            }
//            tblArray.setValidCount(cnt);
//        }
//    }
    // END 2015/10/23 T.Tomita [N/A, DEL]

//    private void setLastAddRowData(List<Integer> indexList, NSAL0360CMsg cMsg, String tblNm, EZDMsgArray tblArray, EZDMsg lastEzdMsg) {
//
//        if (indexList.size() > 0) {
//
//            String sq = null;
//            if (lastEzdMsg == null) {
//                sq = "1";
//            } else {
//                sq = NSAL0360CommonLogic.getNextSeqNum(lastEzdMsg, tblNm);
//            }
//
//            String bllgSchdFromDt = null;
//            if (lastEzdMsg == null) {
//                bllgSchdFromDt = cMsg.contrEffFromDt_H1.getValue();
//            } else {
//                EZDCDateItem workItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(lastEzdMsg, tblNm, "bllgSchdThruDt_A1");
//                if (cMsg.contrEffThruDt_H1.getValue().equals(workItem.getValue())) {
//                    bllgSchdFromDt = cMsg.contrEffThruDt_H1.getValue();
//                } else {
//                    bllgSchdFromDt = ZYPDateUtil.addDays(workItem.getValue(), 1);
//                }
//            }
//
//            String bllgSchdThruDt = cMsg.contrEffThruDt_H1.getValue();
//
//            String bllgMtrBllgCycleCd = null;
//            if (lastEzdMsg == null) {
//                EZDCBigDecimalItem workItem = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cMsg, tblNm, "dsContrBllgMtrPk_A");
//                BigDecimal dsContrBllgMtrPk = workItem.getValue();
//                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = NSAL0360CommonLogic.findDsContrBllgMtr(getGlobalCompanyCode(), dsContrBllgMtrPk);
//                if (dsContrBllgMtrTMsg == null) {
//                    String[] args = {"DS_CONTR_BLLG_MTR" };
//                    cMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
//                    return;
//                }
//                bllgMtrBllgCycleCd = dsContrBllgMtrTMsg.bllgMtrBllgCycleCd.getValue();
//            } else {
//                // START 2015/10/23 T.Tomita [N/A, MOD]
//                EZDCStringItem workItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(lastEzdMsg, tblNm, "bllgCycleCd_A1");
//                // END 2015/10/23 T.Tomita [N/A, MOD]
//                bllgMtrBllgCycleCd = workItem.getValue();
//
//            }
//            // NSXC001001Contr is not completed.
//            ScheduleInfo info = NSXC001001Contr.getScheduleInfo(getGlobalCompanyCode(), bllgSchdFromDt, bllgSchdThruDt, bllgMtrBllgCycleCd);
//
//            int cnt = tblArray.getValidCount();
//            for (int index : indexList) {
//                EZDMsg srcEzdMsg = tblArray.get(index);
//                EZDMsg copyEzdMsg = tblArray.get(cnt);
//                copyEzdMsg.clear();
//
//                EZDMsg.copy(srcEzdMsg, null, copyEzdMsg, null);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(copyEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), sq);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(copyEzdMsg, tblNm, "perSchdNum_A1"), info.getPerSchdNum());
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(copyEzdMsg, tblNm, "perBllgCycleCd_A1"), info.getBllgCycleUomCd());
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(copyEzdMsg, tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDt);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(copyEzdMsg, tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDt);
//
//                NSAL0360CommonLogic.calcRowAmout(copyEzdMsg, tblNm, cMsg, getGlobalCompanyCode());
//
//                cnt++;
//            }
//            tblArray.setValidCount(cnt);
//        }
//    }

    // START 2015/10/23 T.Tomita [N/A, MOD]
    private void setAddRowData(List<Integer> indexList, NSAL0360CMsg cMsg, String tblNm, EZDMsgArray tblArray, EZDMsgArray workTblArray, List<Map<String, Object>> xsCopyList) {

        if (indexList.size() > 0) {

            EZDMsg prevEzdMsg = null;
            if (workTblArray.getValidCount() > 0) {
                prevEzdMsg = workTblArray.get(workTblArray.getValidCount() - 1);
            }

//            List<Integer> prevIndexList = getPrevIndexList(tblNm, workTblArray, prevEzdMsg);

            String sq = null;
            if (prevEzdMsg == null) {
                sq = "1";
            } else {
                sq = NSAL0360CommonLogic.getNextSeqNum(prevEzdMsg, tblNm);
            }
            String bllgSchdFromDt = null;
            if (prevEzdMsg == null) {
                bllgSchdFromDt = cMsg.contrEffFromDt_H1.getValue();
            } else {
                EZDCDateItem workItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(prevEzdMsg, tblNm, "bllgSchdThruDt_A1");
                bllgSchdFromDt = ZYPDateUtil.addDays(workItem.getValue(), 1);
            }

            EZDMsg selectedEzdMsg = tblArray.get(indexList.get(0));
            EZDCDateItem dateItem = NSAL0360CommonLogic.getDateValueFromEZDMsg(selectedEzdMsg, tblNm, "bllgSchdFromDt_A1");
            String bllgSchdThruDt = ZYPDateUtil.addDays(dateItem.getValue(), -1);

            // START 2016/03/01 T.Tomita [QC#4131, MOD]
            ContrDurationInfo param = new ContrDurationInfo();
            param.setGlblCmpyCd(getGlobalCompanyCode());
            param.setContrEffFromDt(bllgSchdFromDt);
            param.setContrEffThruDt(bllgSchdThruDt);
            NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
            calculator.calcDuration();
            BigDecimal perSchdNum = param.getContrDurnNum();
            String perBllgCycleCd = param.getCycleUomCd();
            // END 2016/03/01 T.Tomita [QC#4131, MOD]
            int cnt = workTblArray.getValidCount();
            for (Map<String, Object> xsCopy : xsCopyList) {
                EZDMsg addEzdMsg = workTblArray.get(cnt);
                addEzdMsg.clear();

                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "xxChkBox_A1"), ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "dsContrBllgMtrPk_A1"), (BigDecimal) xsCopy.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "mtrLbNm_A1"), (String) xsCopy.get("MTR_LB_NM"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), sq);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "dsContrPrcEffPk_A1"), (BigDecimal) xsCopy.get("DS_CONTR_PRC_EFF_PK"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "dsContrPrcEffSqNum_A1"), (BigDecimal) xsCopy.get("DS_CONTR_PRC_EFF_SQ_NUM"));

                // START 2016/03/01 T.Tomita [QC#4131, MOD]
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "perSchdNum_A1"), perSchdNum);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "perBllgCycleCd_A1"), perBllgCycleCd);
                // END 2016/03/01 T.Tomita [QC#4131, MOD]
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(addEzdMsg, tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDt);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(addEzdMsg, tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDt);

                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "bllgCycleCd_A1"), (String) xsCopy.get("BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "bllgCycleDescTxt_A1"), (String) xsCopy.get("BLLG_CYCLE_DESC_TXT"));
                // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "bllgCycleStartMth_A1"), (String) xsCopy.get("BLLG_CYCLE_START_MTH"));
                // END 2016/05/18 T.Kanasaka [QC#2184, ADD]
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "xsMtrCopyQty_A1"), (BigDecimal) xsCopy.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "xsMtrCopyQty_AS"), (BigDecimal) xsCopy.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "xsMtrAmtRate_A1"), (BigDecimal) xsCopy.get("XS_MTR_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "ccyCd_A1"), (String) xsCopy.get("CCY_CD"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "contrXsCopyPk_A1"), (BigDecimal) xsCopy.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "xsMtrFirstFlg_A1"), (String) xsCopy.get("XS_MTR_FIRST_FLG"));
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(addEzdMsg, tblNm, "invFlg_A1"), ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(addEzdMsg, tblNm, "xxDtlCnt_A1"), (BigDecimal) new BigDecimal(xsCopyList.size()));

                // START 2016/03/01 T.Tomita [QC#4131, DEL]
                // NSAL0360CommonLogic.calcRowAmout(addEzdMsg, tblNm, cMsg, getGlobalCompanyCode());
                // END 2016/03/01 T.Tomita [QC#4131, DEL]
                NSAL0360CommonLogic.createScheduleRowPulldownList(addEzdMsg, tblNm);

                cnt++;
            }
            workTblArray.setValidCount(cnt);
        }
    }
    // END 2015/10/23 T.Tomita [N/A, MOD]

    private void setCopyRowData(List<Integer> indexList, NSAL0360CMsg cMsg, String tblNm, EZDMsgArray tblArray, EZDMsgArray workTblArray) {

        if (indexList.size() > 0) {

            EZDMsg prevEzdMsg = null;
            if (workTblArray.getValidCount() > 0) {
                prevEzdMsg = workTblArray.get(workTblArray.getValidCount() - 1);
            }

            String sq = null;
            if (prevEzdMsg == null) {
                sq = "1";
            } else {
                sq = NSAL0360CommonLogic.getNextSeqNum(prevEzdMsg, tblNm);
            }

            int cnt = workTblArray.getValidCount();
            for (int index : indexList) {
                EZDMsg srcEzdMsg = tblArray.get(index);
                EZDMsg copyEzdMsg = workTblArray.get(cnt);
                copyEzdMsg.clear();

                EZDMsg.copy(srcEzdMsg, null, copyEzdMsg, null);
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(copyEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), sq);
                cnt++;
            }
            workTblArray.setValidCount(cnt);
        }

    }

    // START 2016/03/01 T.Tomita [QC#4131, DEL]
//    private List<Integer> getPrevIndexList(String tblNm, EZDMsgArray tblArray, EZDMsg prevEzdMsg) {
//        List<Integer> prevIndexList = new ArrayList<Integer>();
//        if (prevEzdMsg == null) {
//            return prevIndexList;
//        }
//
//        EZDCStringItem sqNumItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(prevEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
//        String prevSq = sqNumItem.getValue();
//
//        int orgCnt = tblArray.getValidCount();
//        for (int i = 0; i < orgCnt; i++) {
//            EZDMsg orgEzdMsg = tblArray.get(i);
//            EZDCStringItem workSqItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(orgEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
//            if (prevSq.equals(workSqItem.getValue())) {
//                prevIndexList.add(i);
//            }
//        }
//
//        return prevIndexList;
//    }
    // END 2016/03/01 T.Tomita [QC#4131, DEL]

    // START 2016/03/01 T.Tomita [QC#4131, DEL]
//    private void copyItem(String itemNm, String tblNm, EZDMsg srcEzdMsg, EZDMsg destEzdMsg) {
//        EZDCItem srcItem = NSAL0360CommonLogic.getItemValueFromEZDMsg(srcEzdMsg, tblNm, itemNm);
//        EZDCItem destItem = NSAL0360CommonLogic.getItemValueFromEZDMsg(destEzdMsg, tblNm, itemNm);
//        ZYPEZDItemValueSetter.setValue(destItem, srcItem);
//    }
    // END 2016/03/01 T.Tomita [QC#4131, DEL]

//    /**
//     * Method name: doProcess_NSAL0360Scrn00_OnChangeClosingDay <dd>
//     * The method explanation: OnChangeClosingDay.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    @SuppressWarnings("unchecked")
//    private void doProcess_NSAL0360Scrn00_OnChangeClosingDay(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
//
//        if (isInvoiced(cMsg)) {
//            return;
//        }
//
//        String glblCmpyCd = getGlobalCompanyCode();
//        String cloDay = null;
//        if (NSAL0360Constant.RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH.equals(cMsg.xxRadioBtn_H1.getValue())) {
//            cloDay = NSAL0360Constant.MAX_DAY;
//        } else {
//            cloDay = cMsg.mtrCloDay_H1.getValue();
//            if (!ZYPCommonFunc.hasValue(cloDay)) {
//                return;
//            }
//        }
//
//        String contrEffFromDt = cMsg.contrEffFromDt_H1.getValue();
//        for (String tblNm : NSAL0360Constant.TBL_NM_ARRAY) {
//            EZDCBigDecimalItem dsContrBllgMtrPk = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cMsg, tblNm, "dsContrBllgMtrPk_A");
//            if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
//                continue;
//            }
//            S21SsmEZDResult res = NSAL0360Query.getInstance().getContrXsCopyList(glblCmpyCd, dsContrBllgMtrPk.getValue());
//            if (!res.isCodeNormal()) {
//                String[] args = {"CONTR_XS_COPY" };
//                cMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
//                return;
//            }
//            List xsCopyList = (List) res.getResultObject();
//
//            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = NSAL0360CommonLogic.findDsContrBllgMtr(glblCmpyCd, dsContrBllgMtrPk.getValue());
//            if (dsContrBllgMtrTMsg == null) {
//                String[] args = {"DS_CONTR_BLLG_MTR" };
//                cMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
//                return;
//            }
//            String bllgMtrBllgCycleCd = dsContrBllgMtrTMsg.bllgMtrBllgCycleCd.getValue();
//
//            if ("0".equals(cloDay)) {
//                setSchedule(glblCmpyCd, cMsg, 0, contrEffFromDt, bllgMtrBllgCycleCd, tblNm, 1, xsCopyList);
//
//            } else if (NSAL0360Constant.MAX_DAY.equals(cloDay)) {
//                String lastDt = NSAL0360CommonLogic.getLastDate(contrEffFromDt);
//                setSchedule(glblCmpyCd, cMsg, bllgMtrBllgCycleCd, lastDt, tblNm, xsCopyList);
//
//            } else {
//                String endDt = NSAL0360CommonLogic.getEndDate(contrEffFromDt, cloDay);
//                setSchedule(glblCmpyCd, cMsg, bllgMtrBllgCycleCd, endDt, tblNm, xsCopyList);
//
//            }
//        }
//    }

//    @SuppressWarnings("unchecked")
//    private void setSchedule(String glblCmpyCd, NSAL0360CMsg cMsg, String bllgMtrBllgCycleCd, String endDt, String tblNm, List xsCopyList) {
//
//        int index = 0;
//        int sq = 0;
//        String targetFromDt = cMsg.contrEffFromDt_H1.getValue();
//        if (!BLLG_CYCLE.DAILY.equals(bllgMtrBllgCycleCd)) {
//            int diffDays = ZYPDateUtil.getDistance(targetFromDt, endDt, ZYPDateUtil.CALENDAR_GENERAL) + 1;
//
//            BLLG_CYCLETMsg dailyBllgCycle = NSAL0360CommonLogic.getBllgCycle(glblCmpyCd, BLLG_CYCLE.DAILY);
//            long amount = diffDays / dailyBllgCycle.bllgCycleAot.getValue().longValue();
//
//            String targetThruDt = ZYPDateUtil.addDays(targetFromDt, (int) (diffDays - 1));
//
//            EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
//            int maxCnt = tblArray.length();
//            BigDecimal xsCopyListSize = new BigDecimal(xsCopyList.size());
//            for (int i = 0; i < xsCopyListSize.intValue(); i++) {
//                Map map = (Map) xsCopyList.get(i);
//                EZDMsg ezdMsg = tblArray.get(index);
//                ezdMsg.clear();
//
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1"), ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), String.valueOf(sq));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), new BigDecimal(amount));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), dailyBllgCycle.bllgCycleCd);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), targetFromDt);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), targetThruDt);
//                // START 2015/10/23 T.Tomita [N/A, MOD]
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleCd_A1"), bllgMtrBllgCycleCd);
//                // END 2015/10/23 T.Tomita [N/A, MOD]
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1"), (BigDecimal) map.get("XS_MTR_COPY_QTY"));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1"), (BigDecimal) map.get("XS_MTR_AMT_RATE"));
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1"), cMsg.ccyCd_HH);
//                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xxDtlCnt_A1"), xsCopyListSize);
//                NSAL0360CommonLogic.createScheduleRowPulldownList(ezdMsg, tblNm);
//                NSAL0360CommonLogic.calcRowAmout(ezdMsg, tblNm, cMsg, glblCmpyCd);
//
//                index++;
//                tblArray.setValidCount(index);
//                if (index >= maxCnt) {
//                    String num = String.valueOf(maxCnt);
//                    cMsg.setMessageInfo(NSAL0360Constant.NSAM0024W, new String[] {num, num });
//                    return;
//                }
//            }
//            targetFromDt = ZYPDateUtil.addDays(targetThruDt, 1);
//        }
//        setSchedule(glblCmpyCd, cMsg, index, targetFromDt, bllgMtrBllgCycleCd, tblNm, sq, xsCopyList);
//    }

//    @SuppressWarnings("unchecked")
//    private void setSchedule(String glblCmpyCd, NSAL0360CMsg cMsg, int index, String targetFromDt, String baseBllgCycleCd, String tblNm, int sq, List xsCopyList) {
//
//        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
//        int maxCnt = tblArray.length();
//
//        BigDecimal xsCopyListSize = new BigDecimal(xsCopyList.size());
//
//        BLLG_CYCLETMsgArray bllgCycleTMsgArray = NSAL0360CommonLogic.getBLLG_CYCLETMsgArray(getGlobalCompanyCode());
//
//        BLLG_CYCLETMsg targetCycle = NSAL0360CommonLogic.getBllgCycle(glblCmpyCd, baseBllgCycleCd);
//        String contrEffThruDt = cMsg.contrEffThruDt_H1.getValue();
//        int diffDays = ZYPDateUtil.getDistance(targetFromDt, contrEffThruDt, ZYPDateUtil.CALENDAR_GENERAL) + 1;
//        while (diffDays > 0) {
//            int durationDays = NSAL0360CommonLogic.getDurationDays(bllgCycleTMsgArray, targetCycle.bllgCycleCd.getValue());
//
//            if (durationDays <= diffDays || BLLG_CYCLE.DAILY.equals(baseBllgCycleCd)) {
//                long amount = diffDays / durationDays;
//                diffDays = diffDays % durationDays;
//                String targetThruDt = ZYPDateUtil.addDays(targetFromDt, (int) (durationDays * amount - 1));
//
//                sq++;
//                for (int i = 0; i < xsCopyListSize.intValue(); i++) {
//                    Map map = (Map) xsCopyList.get(i);
//                    EZDMsg ezdMsg = tblArray.get(index);
//                    ezdMsg.clear();
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1"), ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), String.valueOf(sq));
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), new BigDecimal(amount));
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), targetCycle.bllgCycleCd);
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), targetFromDt);
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), targetThruDt);
//                    // START 2015/10/23 T.Tomita [N/A, MOD]
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleCd_A1"), baseBllgCycleCd);
//                    // END 2015/10/23 T.Tomita [N/A, MOD]
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1"), (BigDecimal) map.get("XS_MTR_COPY_QTY"));
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1"), (BigDecimal) map.get("XS_MTR_AMT_RATE"));
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1"), cMsg.ccyCd_HH);
//                    ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xxDtlCnt_A1"), xsCopyListSize);
//                    NSAL0360CommonLogic.createScheduleRowPulldownList(ezdMsg, tblNm);
//                    NSAL0360CommonLogic.calcRowAmout(ezdMsg, tblNm, cMsg, glblCmpyCd);
//                    index++;
//                    tblArray.setValidCount(index);
//                    if (index >= maxCnt) {
//                        String num = String.valueOf(maxCnt);
//                        cMsg.setMessageInfo(NSAL0360Constant.NSAM0024W, new String[] {num, num });
//                        return;
//                    }
//                }
//
//                targetFromDt = ZYPDateUtil.addDays(targetThruDt, 1);
//            }
//
//            targetCycle = NSAL0360CommonLogic.getPrevBLLG_CYCLETMsg(bllgCycleTMsgArray, targetCycle);
//            if (targetCycle == null) {
//                break;
//            }
//        }
//
//    }

//    private boolean isInvoiced(NSAL0360CMsg cMsg) {
//        int cnt = cMsg.A.getValidCount();
//        for (int i = 0; i < cnt; i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).invFlg_A1.getValue())) {
//                return true;
//            }
//        }
//        return false;
//    }

//    /**
//     * Method name: doProcess_NSAL0360Scrn00_OnChangeBllgCycle <dd>The
//     * method explanation: OnChangeBllgCycle.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NSAL0360Scrn00_OnChangeBllgCycle(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
//        int index = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
//        String tblNm = cMsg.srcTblNm_P1.getValue();
//
//        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
//        EZDMsg selectedEzdMsg = tblArray.get(index);
//
//        NSAL0360CommonLogic.calcRowAmout(selectedEzdMsg, tblNm, cMsg, getGlobalCompanyCode());
//    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_OnChangeFromDate <dd>The
     * method explanation: OnChangeFromDate.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_OnChangeFromDate(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        // START 2016/03/01 T.Tomita [QC#4131, ADD]
        calcThruDate(cMsg, sMsg);
        // END 2016/03/01 T.Tomita [QC#4131, ADD]
    }

//    /**
//     * Method name: doProcess_NSAL0360Scrn00_OnChangeAmount <dd>The
//     * method explanation: OnChangeAmount.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NSAL0360Scrn00_OnChangeAmount(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
//        doProcess_NSAL0360Scrn00_OnChangeBllgCycle(cMsg, sMsg);
//    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_OnChangePeriods <dd>The
     * method explanation: OnChangePeriods.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_OnChangePeriods(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        // START 2016/03/01 T.Tomita [QC#4131, ADD]
        calcThruDate(cMsg, sMsg);
        // END 2016/03/01 T.Tomita [QC#4131, ADD]
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_OnChangeThruDate <dd>The
     * method explanation: OnChangeThruDate.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_OnChangeThruDate(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        // START 2016/03/01 T.Tomita [QC#4131, ADD]
        calcPerSchdUom(cMsg, sMsg);
        // END 2016/03/01 T.Tomita [QC#4131, ADD]
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_OnChangeUOM <dd>The
     * method explanation: OnChangeThruDate.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_OnChangeUOM(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        // START 2016/03/01 T.Tomita [QC#4131, ADD]
        calcThruDate(cMsg, sMsg);
        // END 2016/03/01 T.Tomita [QC#4131, ADD]
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_Schedules <dd>The method
     * explanation: Schedules.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_Schedules(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {

        doProcess_NSAL0360Scrn00_CMN_Save(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_CMN_Save <dd>The method
     * explanation: Reset values.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NSAL0360Scrn00_CMN_Save(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        if ("W".equals(cMsg.getMessageKind())) {
            return;
        }
        search(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NSAL0360Scrn00_SkipMonth <dd>The method
     * explanation: SkipMonth.
     * @param cMsg NSAL0360CMsg
     * @param sMsg NSAL0360SMsg
     */
    private void doProcess_NSAL0360Scrn00_SkipMonth(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        if (NSAL0360CommonLogic.isChanged(cMsg, sMsg)) {
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxBtnFlg_H1.getValue())) {
                cMsg.setMessageInfo(NSAL0360Constant.NSAM0338W);
                cMsg.xxBtnFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
                return;
            }
        }
    }

    // START 2015/10/23 T.Tomita [N/A, MOD]
    @SuppressWarnings("unchecked")
    private void search(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        S21SsmEZDResult res = NSAL0360Query.getInstance().getDsContrDtlInfo(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue());
        if (!res.isCodeNormal()) {
            String[] args = {"DS_CONTR_DTL" };
            cMsg.setMessageInfo(NSAL0360Constant.NSAM0045E, args);
            return;
        }

        List list = (List) res.getResultObject();
        Map map = (Map) list.get(0);
        setDsContrDtlInfo(cMsg, map);

//        searchSvcMemo(cMsg, sMsg);

        // START 2016/05/13 T.Kanasaka [QC#7916, ADD]
        setDsContrPrcEffInfo(cMsg);
        // END 2016/05/13 T.Kanasaka [QC#7916, ADD]

        searchSchedules(cMsg, sMsg);

        EZDMsg.copy(cMsg, null, sMsg, null);
    }
    // END 2015/10/23 T.Tomita [N/A, MOD]

    @SuppressWarnings("unchecked")
    private void searchSchedules(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        // START 2015/10/23 T.Tomita [N/A, MOD]
        // START 2016/05/13 T.Kanasaka [QC#7916, MOD]
        S21SsmEZDResult res = NSAL0360Query.getInstance().getScheduleInfoList(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue(), cMsg.dsContrPrcEffPk_HD.getValue());
        // END 2016/05/13 T.Kanasaka [QC#7916, MOD]
        // END 2015/10/23 T.Tomita [N/A, MOD]
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();

            if (listSize > 0) {
                int mtrCnt = 0;
                int maxListCnt = cMsg.A.length();

                int index = 0;
                Map map = (Map) list.get(0);
                BigDecimal targetMtrPk = (BigDecimal) map.get(NSAL0360Constant.DS_CONTR_BLLG_MTR_PK);
                for (int i = 0; i < listSize; i++) {
                    map = (Map) list.get(i);
                    BigDecimal mtrPk = (BigDecimal) map.get(NSAL0360Constant.DS_CONTR_BLLG_MTR_PK);
                    if (targetMtrPk.compareTo(mtrPk) != 0) {
                        targetMtrPk = mtrPk;
                        index = 0;
                        mtrCnt++;
                        if (mtrCnt >= NSAL0360Constant.TBL_NM_ARRAY.length) {
                            int maxCnt = NSAL0360Constant.TBL_NM_ARRAY.length;
                            cMsg.setMessageInfo(NSAL0360Constant.NSAM0360W, new String[] {"Meter", String.valueOf(maxCnt), String.valueOf(maxCnt) });
                            break;
                        }
                    }

                    setScheduleInfo(map, NSAL0360Constant.TBL_NM_ARRAY[mtrCnt], cMsg, index);
                    // START 2016/08/08 K.Kishimoto [QC#12879, ADD]
                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(index).invFlg_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.invFlg_HH, ZYPConstant.FLG_ON_Y);
                    }
                    // END 2016/08/08 K.Kishimoto [QC#12879, ADD]
                    index++;
                    if (index >= maxListCnt) {
                        cMsg.setMessageInfo(NSAL0360Constant.NSAM0024W, new String[] {String.valueOf(maxListCnt), String.valueOf(maxListCnt) });
                        break;
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void setScheduleInfo(Map map, String tblNm, NSAL0360CMsg cMsg, int index) {
        EZDMsgArray ezdMsgArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
        setScheduleInfo(map, tblNm, ezdMsgArray.get(index), cMsg);
        NSAL0360CommonLogic.createScheduleRowPulldownList(ezdMsgArray.get(index), tblNm);
        ezdMsgArray.setValidCount(index + 1);

        if (index == 0) {
            // START 2015/10/23 T.Tomita [N/A, MOD]
            EZDCBigDecimalItem dsContrBllgMtrPk = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(cMsg, tblNm, "dsContrBllgMtrPk_A");
            EZDCStringItem mtrLbNm = NSAL0360CommonLogic.getStringValueFromEZDMsg(cMsg, tblNm, "mtrLbNm_A");
//            EZDCStringItem bllgMtrBillToCustCd = NSAL0360CommonLogic.getStringValueFromEZDMsg(cMsg, tblNm, "bllgMtrBillToCustCd_A");

            ZYPEZDItemValueSetter.setValue(dsContrBllgMtrPk, (BigDecimal) map.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(mtrLbNm, (String) map.get("MTR_LB_NM"));
//            ZYPEZDItemValueSetter.setValue(bllgMtrBillToCustCd, (String) map.get("BLLG_MTR_BILL_TO_CUST_CD"));
            // END 2015/10/23 T.Tomita [N/A, MOD]
        }
    }

    // START 2015/10/23 T.Tomita [N/A, DEL]
//    @SuppressWarnings("unchecked")
//    private void searchSvcMemo(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
//        String glblCmpyCd = getGlobalCompanyCode();
//
//        S21SsmEZDResult res = NSAL0360Query.getInstance().getSvcMemo(glblCmpyCd, cMsg.dsContrDtlPk_H1.getValue());
//        if (res.isCodeNormal()) {
//            List list = (List) res.getResultObject();
//            Map map = (Map) list.get(0);
//            setSvcMemoInfo(map, cMsg);
//        }
//    }
    // END 2015/10/23 T.Tomita [N/A, DEL]

    // START 2015/10/23 T.Tomita [N/A, MOD]
    @SuppressWarnings("unchecked")
    private void setDsContrDtlInfo(NSAL0360CMsg cMsg, Map map) {
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_HH, (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_HH, (String) map.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk_H1, (BigDecimal) map.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrDtlPk_H1, (BigDecimal) map.get("DS_CONTR_DTL_PK"));
        // START 2016/05/13 T.Kanasaka [QC#7916, DEL]
//        ZYPEZDItemValueSetter.setValue(cMsg.contrEffFromDt_H1, (String) map.get("CONTR_EFF_FROM_DT"));
//        ZYPEZDItemValueSetter.setValue(cMsg.contrEffThruDt_H1, (String) map.get("CONTR_EFF_THRU_DT"));
        // START 2016/05/13 T.Kanasaka [QC#7916, DEL]
        ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgLastBllgDt_H1, (String) map.get("MTR_BLLG_LAST_BLLG_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgTmgCd_H1, (String) map.get("MTR_BLLG_TMG_CD"));
        // START 2016/05/18 T.Kanasaka [QC#2184, MOD]
        String mtrCloDay = (String) map.get("MTR_CLO_DAY");
        ZYPEZDItemValueSetter.setValue(cMsg.mtrCloDay_H1, mtrCloDay);
        String mtrDplyPerEndDay = (String) map.get("MTR_DPLY_PER_END_DAY");
        // START 2016/08/08 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0360Constant.MAX_DAY.equals(mtrDplyPerEndDay)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H1, NSAL0360Constant.RADIO_VALUE_CLOSING_LAST_DAY_OF_MONTH);
//            cMsg.mtrDplyPerEndDay_H1.clear();
//        } else {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H1, NSAL0360Constant.RADIO_VALUE_CLOSING_DAY);
//            ZYPEZDItemValueSetter.setValue(cMsg.mtrDplyPerEndDay_H1, mtrDplyPerEndDay);
//        }
        ZYPEZDItemValueSetter.setValue(cMsg.mtrDplyPerEndDay_H1, mtrDplyPerEndDay);
        // END 2016/08/08 K.Kishimoto [QC#12879, MOD]
        // END 2016/05/18 T.Kanasaka [QC#2184, MOD]
        String mtrBllgDay = (String) map.get("MTR_BLLG_DAY");
        // START 2016/08/08 K.Kishimoto [QC#12879, MOD]
//        if (NSAL0360Constant.MAX_DAY.equals(mtrBllgDay)) {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H2, NSAL0360Constant.RADIO_VALUE_BLLG_LAST_DAY_OF_MONTH);
//            cMsg.mtrBllgDay_H1.clear();
//        } else {
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_H2, NSAL0360Constant.RADIO_VALUE_BLLG_DAY);
//            ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgDay_H1, mtrBllgDay);
//        }
        ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgDay_H1, mtrBllgDay);
        // END 2016/08/08 K.Kishimoto [QC#12879, MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.baseBllgCycleCd_H1, (String) map.get("MTR_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.basePrcDealAmt_H1, (BigDecimal) map.get("BASE_PRC_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum_H1, (String) map.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.ccyCd_HH, (String) map.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.serNum_H1, (String) map.get("SER_NUM"));
//        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H1, (String) map.get("T_MDL_NM"));
        // START 2016/04/14 T.Kanasaka [QC#4960, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.invSeptBaseUsgFlg_H1, (String) map.get("INV_SEPT_BASE_USG_FLG"));
        // END 2016/04/14 T.Kanasaka [QC#4960, ADD]
        // START 2016/08/08 K.Kishimoto [QC#12879, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.invFlg_HH, ZYPConstant.FLG_OFF_N);
        // END 2016/08/08 K.Kishimoto [QC#12879, ADD]
    }
    // END 2015/10/23 T.Tomita [N/A, MOD]

    // START 2015/10/23 T.Tomita [N/A, MOD]
    @SuppressWarnings("unchecked")
    private void setScheduleInfo(Map lineMap, String tblNm, EZDMsg ezdMsg, NSAL0360CMsg cMsg) {
        ezdMsg.clear();
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1"), ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTime_A1"), (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTimeZone_A1"), (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTime_AM"), (String) lineMap.get("EZUPTIME_MTR"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ezUpTimeZone_AM"), (String) lineMap.get("EZUPTIMEZONE_MTR"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgMtrPk_A1"), (BigDecimal) lineMap.get("DS_CONTR_BLLG_MTR_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "mtrLbNm_A1"), (String) lineMap.get("MTR_LB_NM"));
//        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgMtrBillToCustCd_A1"), (String) lineMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdMtrPk_A1"), (BigDecimal) lineMap.get("DS_CONTR_BLLG_SCHD_MTR_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSmryPk_A1"), (BigDecimal) lineMap.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffPk_A1"), (BigDecimal) lineMap.get("DS_CONTR_PRC_EFF_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), (String) lineMap.get("DS_CONTR_BLLG_SCHD_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffPk_A1"), (BigDecimal) lineMap.get("DS_CONTR_PRC_EFF_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1"), (BigDecimal) lineMap.get("DS_CONTR_PRC_EFF_SQ_NUM"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), (BigDecimal) lineMap.get("PER_SCHD_NUM"));
        // Mod Start 07/13/2016 <QC#11809>
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), (String) lineMap.get("BLLG_CYCLE_UOM_CD"));
        // Mod End   07/13/2016 <QC#11809>
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), (String) lineMap.get("BLLG_SCHD_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), (String) lineMap.get("BLLG_SCHD_THRU_DT"));

        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleCd_A1"), (String) lineMap.get("BLLG_MTR_BLLG_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleDescTxt_A1"), (String) lineMap.get("BLLG_CYCLE_DESC_TXT"));
        // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleStartMth_A1"), (String) lineMap.get("BLLG_CYCLE_START_MTH"));
        // END 2016/05/18 T.Kanasaka [QC#2184, ADD]
        // START 2016/03/01 T.Tomita [QC#4131, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1"), (BigDecimal) lineMap.get("XS_MTR_COPY_QTY"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_AS"), (BigDecimal) lineMap.get("XS_MTR_COPY_QTY_SMRY"));
        // END 2016/03/01 T.Tomita [QC#4131, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1"), (BigDecimal) lineMap.get("XS_MTR_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1"), (String) lineMap.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "contrXsCopyPk_A1"), (BigDecimal) lineMap.get("CONTR_XS_COPY_PK"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xsMtrFirstFlg_A1"), (String) lineMap.get("XS_MTR_FIRST_FLG"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "invFlg_A1"), (String) lineMap.get("INV_FLG"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xxDtlCnt_A1"), (BigDecimal) lineMap.get("XX_DTL_CNT"));
        // START 2016/12/01 K.Ochiai [QC#14204, ADD]
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistCratTs_A1"), (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistCratByNm_A1"), (String) lineMap.get("XX_REC_HIST_CRAT_BY_NM"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistUpdTs_A1"), (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistUpdByNm_A1"), (String) lineMap.get("XX_REC_HIST_UPD_BY_NM"));
        ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxRecHistTblNm_A1"), (String) lineMap.get("XX_REC_HIST_TBL_NM"));
        // END 2016/12/01 K.Ochiai [QC#14204, ADD]
        // START 2016/03/01 T.Tomita [QC#4131, DEL]
        // NSAL0360CommonLogic.calcRowAmout(ezdMsg, tblNm, cMsg, getGlobalCompanyCode());
        // START 2016/03/01 T.Tomita [QC#4131, DEL]
    }
    // END 2015/10/23 T.Tomita [N/A, MOD]

    // START 2015/10/23 T.Tomita [N/A, DEL]
//    @SuppressWarnings("unchecked")
//    private void setSvcMemoInfo(Map lineMap, NSAL0360CMsg cMsg) {
//        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_FF, (String) lineMap.get("EZUPTIME"));
//        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_FF, (String) lineMap.get("EZUPTIMEZONE"));
//        ZYPEZDItemValueSetter.setValue(cMsg.svcMemoPk_F1, (BigDecimal) lineMap.get("SVC_MEMO_PK"));
//        ZYPEZDItemValueSetter.setValue(cMsg.svcMemoRsnCd_F3, (String) lineMap.get("SVC_MEMO_RSN_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.svcCmntTxt_F1, (String) lineMap.get("SVC_CMNT_TXT"));
//    }
    // END 2015/10/23 T.Tomita [N/A, DEL]

    // START 2015/10/23 T.Tomita [N/A, ADD]
    private void addLastLineData(NSAL0360CMsg cMsg, EZDMsgArray tblArray, String tblNm, int rowNm, List<Map<String, Object>> xsCopyList) {
        int i = rowNm;
        String schdSqNum = "1";
        String fromDt = cMsg.contrEffFromDt_H1.getValue();
        String thruDt = cMsg.contrEffThruDt_H1.getValue();
        if (tblArray.getValidCount() > 0) {
            int lastRow = tblArray.getValidCount() - 1;
            EZDMsg lastRowEzdMsg = tblArray.get(lastRow);
            String lastThruDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(lastRowEzdMsg, tblNm, "bllgSchdThruDt_A1").getValue();
            fromDt = ZYPDateUtil.addDays(lastThruDt, 1);
            if (ZYPDateUtil.compare(fromDt, thruDt) > 0) {
                thruDt = fromDt;
            }

            schdSqNum = NSAL0360CommonLogic.getNextSeqNum(lastRowEzdMsg, tblNm);
        }
        // START 2016/03/01 T.Tomita [QC#4131, MOD]
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(fromDt);
        param.setContrEffThruDt(thruDt);
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcDuration();
        BigDecimal perSchdNum = param.getContrDurnNum();
        String perBllgCycleCd = param.getCycleUomCd();
        // END 2016/03/01 T.Tomita [QC#4131, MOD]

        for (Map<String, Object> xsCopy : xsCopyList) {
            EZDMsg ezdMsg = tblArray.get(i);
            ezdMsg.clear();

            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1"), ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgMtrPk_A1"), (BigDecimal) xsCopy.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "mtrLbNm_A1"), (String) xsCopy.get("MTR_LB_NM"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1"), schdSqNum);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffPk_A1"), (BigDecimal) xsCopy.get("DS_CONTR_PRC_EFF_PK"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1"), (BigDecimal) xsCopy.get("DS_CONTR_PRC_EFF_SQ_NUM"));
            // START 2016/03/01 T.Tomita [QC#4131, MOD]
            if (BigDecimal.valueOf(999).compareTo(perSchdNum.abs()) >= 0) {
                ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), perSchdNum);
            }
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), perBllgCycleCd);
            // END 2016/03/01 T.Tomita [QC#4131, MOD]
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), fromDt);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), thruDt);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleCd_A1"), (String) xsCopy.get("BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleDescTxt_A1"), (String) xsCopy.get("BLLG_CYCLE_DESC_TXT"));
            // START 2016/05/18 T.Kanasaka [QC#2184, ADD]
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "bllgCycleStartMth_A1"), (String) xsCopy.get("BLLG_CYCLE_START_MTH"));
            // END 2016/05/18 T.Kanasaka [QC#2184, ADD]
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_A1"), (BigDecimal) xsCopy.get("XS_MTR_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrCopyQty_AS"), (BigDecimal) xsCopy.get("XS_MTR_COPY_QTY"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xsMtrAmtRate_A1"), (BigDecimal) xsCopy.get("XS_MTR_AMT_RATE"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "ccyCd_A1"), (String) xsCopy.get("CCY_CD"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "contrXsCopyPk_A1"), (BigDecimal) xsCopy.get("CONTR_XS_COPY_PK"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xsMtrFirstFlg_A1"), (String) xsCopy.get("XS_MTR_FIRST_FLG"));
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "invFlg_A1"), ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "xxDtlCnt_A1"), (BigDecimal) new BigDecimal(xsCopyList.size()));

            // START 2016/03/01 T.Tomita [QC#4131, DEL]
            // NSAL0360CommonLogic.calcRowAmout(ezdMsg, tblNm, cMsg, getGlobalCompanyCode());
            // START 2016/03/01 T.Tomita [QC#4131, DEL]
            NSAL0360CommonLogic.createScheduleRowPulldownList(ezdMsg, tblNm);
            i++;
        }
        tblArray.setValidCount(i);
    }
    // END 2015/10/23 T.Tomita [N/A, ADD]
    // START 2016/03/01 T.Tomita [QC#4131, ADD]
    private void calcThruDate(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        int index = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
        String tblNm = cMsg.srcTblNm_P1.getValue();

        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
        EZDMsg selectedEzdMsg = tblArray.get(index);

        EZDCStringItem tergetDsContrBllgSchdSqNumSrc = NSAL0360CommonLogic.getStringValueFromEZDMsg(selectedEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
        EZDCBigDecimalItem tergetDsContrPrcEffSqNumSrc = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(selectedEzdMsg, tblNm, "dsContrPrcEffSqNum_A1");
        EZDCDateItem bllgSchdFromDtSrc = NSAL0360CommonLogic.getDateValueFromEZDMsg(selectedEzdMsg, tblNm, "bllgSchdFromDt_A1");
        EZDCBigDecimalItem perSchdNum = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(selectedEzdMsg, tblNm, "perSchdNum_A1");
        EZDCStringItem perBllgCycleCd = NSAL0360CommonLogic.getStringValueFromEZDMsg(selectedEzdMsg, tblNm, "perBllgCycleCd_A1");

        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(bllgSchdFromDtSrc.getValue());
        param.setContrDurnNum(perSchdNum.getValue());
        param.setCycleUomCd(perBllgCycleCd.getValue());
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcEndDt();
        // START 2017/08/07 E.Kameishi [QC#18586,MOD]
        if (!ZYPCommonFunc.hasValue(param.getContrEffThruDt())) {
            return;
        } else if (param.getContrEffThruDt().length() > NSAL0330Constant.MAX_NUM_DIGT_DATE) {
            NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(selectedEzdMsg, tblNm, "perSchdNum_A1").setErrorInfo(1, NSAL0360Constant.NSZM1054E);
            NSAL0360CommonLogic.getStringValueFromEZDMsg(selectedEzdMsg, tblNm, "perBllgCycleCd_A1").setErrorInfo(1, NSAL0360Constant.NSZM1054E);
            return;
        }
        // END 2017/08/07 E.Kameishi [QC#18586,MOD]
        String bllgSchdThruDt = param.getContrEffThruDt();

        EZDMsg ezdMsg;
        EZDCStringItem dsContrBllgSchdSqNumSrc;
        EZDCBigDecimalItem dsContrPrcEffSqNumSrc;
        for (int i = 0; i < tblArray.getValidCount(); i++) {
            ezdMsg = tblArray.get(i);
            dsContrBllgSchdSqNumSrc = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            dsContrPrcEffSqNumSrc = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1");
            if (!NSAL0360CommonLogic.equalString(tergetDsContrBllgSchdSqNumSrc, dsContrBllgSchdSqNumSrc)) {
                continue;
            }
            if (!NSAL0360CommonLogic.equalBigDecimal(tergetDsContrPrcEffSqNumSrc, dsContrPrcEffSqNumSrc)) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), perSchdNum.getValue());
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), perBllgCycleCd.getValue());
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDtSrc.getValue());
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDt);
        }
    }

    private void calcPerSchdUom(NSAL0360CMsg cMsg, NSAL0360SMsg sMsg) {
        int index = Integer.parseInt(cMsg.xxSelNum_H1.getValue());
        String tblNm = cMsg.srcTblNm_P1.getValue();

        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(cMsg, tblNm);
        EZDMsg selectedEzdMsg = tblArray.get(index);

        EZDCStringItem tergetDsContrBllgSchdSqNumSrc = NSAL0360CommonLogic.getStringValueFromEZDMsg(selectedEzdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
        EZDCBigDecimalItem tergetDsContrPrcEffSqNumSrc = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(selectedEzdMsg, tblNm, "dsContrPrcEffSqNum_A1");
        EZDCDateItem bllgSchdFromDtSrc = NSAL0360CommonLogic.getDateValueFromEZDMsg(selectedEzdMsg, tblNm, "bllgSchdFromDt_A1");
        EZDCDateItem bllgSchdThruDtSrc = NSAL0360CommonLogic.getDateValueFromEZDMsg(selectedEzdMsg, tblNm, "bllgSchdThruDt_A1");

        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(bllgSchdFromDtSrc.getValue());
        param.setContrEffThruDt(bllgSchdThruDtSrc.getValue());
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcDuration();
        BigDecimal perSchdNum = param.getContrDurnNum();
        if (perSchdNum == null) {
            NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(selectedEzdMsg, tblNm, "perSchdNum_A1").setErrorInfo(1, NSAL0360Constant.NSZM1054E);
            return;
        }
        // Add Start 08/09/2016 <QC#12310>
        if (NSAL0360Constant.OVER_PER_SEQ.compareTo(param.getContrDurnNum().abs()) <= 0) {
            NSAL0360CommonLogic.getDateValueFromEZDMsg(selectedEzdMsg, tblNm, "bllgSchdFromDt_A1").setErrorInfo(1, NSAL0360Constant.NSZM1054E);
            NSAL0360CommonLogic.getDateValueFromEZDMsg(selectedEzdMsg, tblNm, "bllgSchdThruDt_A1").setErrorInfo(1, NSAL0360Constant.NSZM1054E);
            return;
        }
        // Add End   08/09/2016 <QC#12310>
        String perBllgCycleCd = param.getCycleUomCd();

        EZDMsg ezdMsg;
        EZDCStringItem dsContrBllgSchdSqNumSrc;
        EZDCBigDecimalItem dsContrPrcEffSqNumSrc;
        for (int i = 0; i < tblArray.getValidCount(); i++) {
            ezdMsg = tblArray.get(i);
            dsContrBllgSchdSqNumSrc = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsContrBllgSchdSqNum_A1");
            dsContrPrcEffSqNumSrc = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dsContrPrcEffSqNum_A1");
            if (!NSAL0360CommonLogic.equalString(tergetDsContrBllgSchdSqNumSrc, dsContrBllgSchdSqNumSrc)) {
                continue;
            }
            if (!NSAL0360CommonLogic.equalBigDecimal(tergetDsContrPrcEffSqNumSrc, dsContrPrcEffSqNumSrc)) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1"), perSchdNum);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1"), perBllgCycleCd);
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1"), bllgSchdFromDtSrc.getValue());
            ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1"), bllgSchdThruDtSrc.getValue());
        }
    }
    // END 2016/03/01 T.Tomita [QC#4131, ADD]

    // START 2016/05/13 T.Kanasaka [QC#7916, ADD]
    private void setDsContrPrcEffInfo(NSAL0360CMsg cMsg) {
        DS_CONTR_PRC_EFFTMsg dsConntrPrcEffTMsg = NSAL0360Query.getInstance().getDsContrPrcEff(getGlobalCompanyCode(), cMsg.dsContrPrcEffPk_HD.getValue());
        if (dsConntrPrcEffTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrEffFromDt_H1, dsConntrPrcEffTMsg.contrPrcEffFromDt);
            ZYPEZDItemValueSetter.setValue(cMsg.contrEffThruDt_H1, dsConntrPrcEffTMsg.contrPrcEffThruDt);
        }
    }
    // END 2016/05/13 T.Kanasaka [QC#7916, ADD]
}
