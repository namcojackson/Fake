/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7240;

import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM0072E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM0163E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM0176E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM0177E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8433E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8446W;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NMAM8513E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.NZZM0003E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.RESULT_NUM_ABNORMAL;
import static business.blap.NMAL7240.constant.NMAL7240Constant.RESULT_NUM_NORMAL;
import static business.blap.NMAL7240.constant.NMAL7240Constant.RESULT_NUM_NOUPDATE;
import static business.blap.NMAL7240.constant.NMAL7240Constant.SAVED_SEARCH_OPTIONS;
import static business.blap.NMAL7240.constant.NMAL7240Constant.SEARCH_OPTION_NAME;
import static business.blap.NMAL7240.constant.NMAL7240Constant.STANDARD_CURRENCY;
import static business.blap.NMAL7240.constant.NMAL7240Constant.THIS_FREIGHT_RATE;
import static business.blap.NMAL7240.constant.NMAL7240Constant.VAR_CHAR_CONST_PER_UNIT_TP_CD;
import static business.blap.NMAL7240.constant.NMAL7240Constant.VAR_CHAR_CONST_QTY_UNIT_TP_CD;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZZM9000E;
import static business.blap.NMAL7240.constant.NMAL7240Constant.ZZM9037E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7240.common.NMAL7240CommonLogic;
import business.db.FRT_RATE_SHPGTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.LINE_BIZ_TPTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SHPG_SVC_LVLTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7240BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 *</pre>
 */
public class NMAL7240BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7240CMsg bizMsg = (NMAL7240CMsg) cMsg;
            NMAL7240SMsg glblMsg = (NMAL7240SMsg) sMsg;

            if ("NMAL7240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL7240Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7240Scrn00_SaveSearch(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_CMN_Submit(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {

        // default Value Check
        StringBuilder sb = new StringBuilder();
        boolean isErr = false;

        if (!ZYPCommonFunc.hasValue(bizMsg.qtyUnitTpCd_H)) {
            sb.append(VAR_CHAR_CONST_QTY_UNIT_TP_CD);
            isErr = true;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.perUnitTpCd_H)) {
            if (isErr) {
                sb.append(", ");
            }
            sb.append(VAR_CHAR_CONST_PER_UNIT_TP_CD);
            isErr = true;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.frtRateCcyCd_H)) {
            if (isErr) {
                sb.append(", ");
            }
            sb.append(STANDARD_CURRENCY);
            isErr = true;
        }

        if (isErr) {
            bizMsg.setMessageInfo(NMAM8433E, new String[] {sb.toString()});
            return;
        }

        // Create Delete Record Map
        List<BigDecimal> deleteList = new ArrayList<BigDecimal>();
        Map<BigDecimal, NMAL7240_ASMsg> deleteMap = new HashMap<BigDecimal, NMAL7240_ASMsg>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            boolean unmatchFlg = true;
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).frtRateShpgPk_A1)
                        && ZYPCommonFunc.hasValue(glblMsg.A.no(i).frtRateShpgPk_A1)
                        && glblMsg.A.no(i).frtRateShpgPk_A1.getValue().compareTo(bizMsg.A.no(j).frtRateShpgPk_A1.getValue()) == 0) {
                    unmatchFlg = false;
                    break;
                }
            }

            if (unmatchFlg) {
                deleteList.add(glblMsg.A.no(i).frtRateShpgPk_A1.getValue());
                deleteMap.put(glblMsg.A.no(i).frtRateShpgPk_A1.getValue(), glblMsg.A.no(i));
            }
        }

        // Detail Value Check
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).lineBizTpDescTxt_A1)) {
                LINE_BIZ_TPTMsg inTMsg = new LINE_BIZ_TPTMsg();
                LINE_BIZ_TPTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.lineBizTpDescTxt, bizMsg.A.no(i).lineBizTpDescTxt_A1);
                outTMsg = (LINE_BIZ_TPTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    bizMsg.A.no(i).lineBizTpDescTxt_A1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.A.no(i).lineBizTpDescTxt_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).lineBizTpCd_A1, outTMsg.no(0).lineBizTpCd);
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).shpgSvcLvlDescTxt_A1)) {
                SHPG_SVC_LVLTMsg inTMsg = new SHPG_SVC_LVLTMsg();
                SHPG_SVC_LVLTMsgArray outTMsg = null;
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inTMsg.shpgSvcLvlDescTxt, bizMsg.A.no(i).shpgSvcLvlDescTxt_A1);
                outTMsg = (SHPG_SVC_LVLTMsgArray) S21CodeTableAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    bizMsg.A.no(i).shpgSvcLvlDescTxt_A1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.A.no(i).shpgSvcLvlDescTxt_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shpgSvcLvlCd_A1, outTMsg.no(0).shpgSvcLvlCd);
                }
            }
        }

        if (isErr) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        // Create Update Record List
        List<BigDecimal> updateList = new ArrayList<BigDecimal>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).frtRateShpgPk_A1)) {
                updateList.add(bizMsg.A.no(i).frtRateShpgPk_A1.getValue());
            }
        }

        if (NMAL7240CommonLogic.checkActiveRateForScreen(bizMsg)) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (NMAL7240CommonLogic.checkActiveDuplicateRate(bizMsg.A.no(i), deleteList, updateList)) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {THIS_FREIGHT_RATE});
                isErr = true;
            }
        }

        if (isErr) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        int cnt = 0;
        // Delete
        for (BigDecimal frtRatePk : deleteList) {
            if (ZYPCommonFunc.hasValue(frtRatePk)) {
                int delCnt = deleteFrtRate(deleteMap.get(frtRatePk), bizMsg);
                if (delCnt == RESULT_NUM_ABNORMAL) {
                    return;
                } else {
                    cnt += delCnt;
                }
            }
        }

        // update & insert
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).frtRateShpgPk_A1)) {
                int updCnt = updateFrtRate(bizMsg, glblMsg, i);
                if (updCnt == RESULT_NUM_ABNORMAL) {
                    return;
                } else {
                    cnt += updCnt;
                }
            } else {
                int insCnt = insertFrtRate(bizMsg, i);
                if (insCnt == RESULT_NUM_ABNORMAL) {
                    return;
                } else {
                    cnt += insCnt;
                }
            }
        }

        if (cnt == RESULT_NUM_NOUPDATE) {
            bizMsg.setMessageInfo(NMAM8446W);
            return;
        }
    }

    /**
     * delete Freight Rate
     * @param glblLineMsg Global Line Msg
     * @param bizMsg Business Msg
     * @return 1 normal end, -1 abnormal end
     */
    private int deleteFrtRate(NMAL7240_ASMsg glblLineMsg, NMAL7240CMsg bizMsg) {

        FRT_RATE_SHPGTMsg inTMsg = new FRT_RATE_SHPGTMsg();
        FRT_RATE_SHPGTMsg outTMsg = null;
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.frtRateShpgPk, glblLineMsg.frtRateShpgPk_A1);
        outTMsg = (FRT_RATE_SHPGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return RESULT_NUM_ABNORMAL;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                glblLineMsg.ezUpTime_A1.getValue(), glblLineMsg.ezUpTimeZone_A1.getValue()
                , outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NZZM0003E);
            return RESULT_NUM_ABNORMAL;
        }

        EZDTBLAccessor.logicalRemove(outTMsg);
        String returnCode = outTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0177E, new String[]{inTMsg.getTableName()});
            return RESULT_NUM_ABNORMAL;
        }

        return RESULT_NUM_NORMAL;
    }

    /**
     * update Freight Rate
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     * @return 1 normal end, 0 not update, -1 abnormal end
     */
    private int updateFrtRate(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg, int index) {
        if (!checkChangedValueExist(bizMsg.A.no(index), glblMsg)) {
            return RESULT_NUM_NOUPDATE;
        }

        FRT_RATE_SHPGTMsg inTMsg = new FRT_RATE_SHPGTMsg();
        FRT_RATE_SHPGTMsg outTMsg = null;
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.frtRateShpgPk, bizMsg.A.no(index).frtRateShpgPk_A1);
        outTMsg = (FRT_RATE_SHPGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
        if (outTMsg == null) {
            bizMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
            bizMsg.setMessageInfo(NZZM0003E);
            return RESULT_NUM_ABNORMAL;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(
                bizMsg.A.no(index).ezUpTime_A1.getValue(), bizMsg.A.no(index).ezUpTimeZone_A1.getValue()
                , outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NZZM0003E);
            bizMsg.setMessageInfo(NZZM0003E);
            return RESULT_NUM_ABNORMAL;
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.shpgFrtRate, bizMsg.A.no(index).shpgFrtRate_A1);
        ZYPEZDItemValueSetter.setValue(outTMsg.frtRatePerNum, bizMsg.A.no(index).frtRatePerNum_A1);
        ZYPEZDItemValueSetter.setValue(outTMsg.effThruDt, bizMsg.A.no(index).effThruDt_A1);

        EZDTBLAccessor.update(outTMsg);
        String returnCode = outTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            glblMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAM0177E, new String[]{inTMsg.getTableName()});
            bizMsg.setMessageInfo(NMAM0177E, new String[]{inTMsg.getTableName()});
            return RESULT_NUM_ABNORMAL;
        }

        return RESULT_NUM_NORMAL;
    }

    /**
     * Changed Value Exist Check
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean checkChangedValueExist(NMAL7240_ACMsg bizMsgALine, NMAL7240SMsg glblMsg) {
        // QC#7040 2016/05/17 Mod start
//        BigDecimal shpgFrtRate_A1 = nvlBigDecimal(bizMsgALine.shpgFrtRate_A1.getValue());
//        BigDecimal frtRatePerNum_A1 = nvlBigDecimal(bizMsgALine.frtRatePerNum_A1.getValue());
        BigDecimal shpgFrtRate = nvlBigDecimal(bizMsgALine.shpgFrtRate_A1.getValue());
        BigDecimal frtRatePerNum = nvlBigDecimal(bizMsgALine.frtRatePerNum_A1.getValue());
        // QC#7040 2016/05/17 Mod end

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (bizMsgALine.frtRateShpgPk_A1.getValue().compareTo(glblMsg.A.no(i).frtRateShpgPk_A1.getValue()) != 0) {
                continue;
            }

            // QC#7040 2016/05/17 Mod start
//            if (shpgFrtRate_A1.compareTo(glblMsg.A.no(i).shpgFrtRate_A1.getValue()) != 0
//                    || frtRatePerNum_A1.compareTo(glblMsg.A.no(i).frtRatePerNum_A1.getValue()) != 0
            if (shpgFrtRate.compareTo(glblMsg.A.no(i).shpgFrtRate_A1.getValue()) != 0
                    || frtRatePerNum.compareTo(glblMsg.A.no(i).frtRatePerNum_A1.getValue()) != 0
            // QC#7040 2016/05/17 Mod end
                    || !bizMsgALine.effThruDt_A1.getValue().equals(glblMsg.A.no(i).effThruDt_A1.getValue())) {
                return true;
            }
            return false;
        }
        return false;
    }

    private static BigDecimal nvlBigDecimal(BigDecimal arg) {

        if (ZYPCommonFunc.hasValue(arg)) {
            return arg;
        }
        return BigDecimal.ZERO;
    }

    /**
     * insert Freight Rate
     * @param bizMsg Business Msg
     * @param index Global Msg index
     * @return 1 normal end, -1 abnormal end
     */
    private int insertFrtRate(NMAL7240CMsg bizMsg, int index) {
        FRT_RATE_SHPGTMsg inTMsg = new FRT_RATE_SHPGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal frtRateShpgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FRT_RATE_SHPG_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.frtRateShpgPk, frtRateShpgPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.lineBizTpCd, bizMsg.A.no(index).lineBizTpCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.frtZoneNum, bizMsg.A.no(index).frtZoneNum_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgSvcLvlCd, bizMsg.A.no(index).shpgSvcLvlCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.fromSclQty, bizMsg.A.no(index).fromSclQty_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.qtyUnitTpCd, bizMsg.A.no(index).qtyUnitTpCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.shpgFrtRate, bizMsg.A.no(index).shpgFrtRate_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.frtRateCcyCd, bizMsg.A.no(index).frtRateCcyCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.frtRatePerNum, bizMsg.A.no(index).frtRatePerNum_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.perUnitTpCd, bizMsg.A.no(index).perUnitTpCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, bizMsg.A.no(index).effFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, bizMsg.A.no(index).effThruDt_A1.getValue());

        EZDTBLAccessor.insert(inTMsg);
        String returnCode = inTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAM0176E, new String[]{inTMsg.getTableName()});
            bizMsg.setMessageInfo(NMAM0176E, new String[]{inTMsg.getTableName()});
            return RESULT_NUM_ABNORMAL;
        }

        return RESULT_NUM_NORMAL;
    }

    /**
     * DeleteSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_DeleteSearch(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            // QC#7040 2016/05/17 Mod start
//            bizMsg.srchOptPk_S.setErrorInfo(1, "NMAM0014E", new String[] {"Saved Search Options"});
            bizMsg.srchOptPk_S.setErrorInfo(1, "NMAM0014E", new String[] {SAVED_SEARCH_OPTIONS});
            // QC#7040 2016/05/17 Mod end
            return;
        }

        NMAL7240CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7240Scrn00_SaveSearch(NMAL7240CMsg bizMsg, NMAL7240SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            // QC#7040 2016/05/17 Mod start
//            bizMsg.srchOptNm_S.setErrorInfo(1, "ZZM9000E" , new String[] {"Search Option Name"});
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E , new String[] {SEARCH_OPTION_NAME});
            // QC#7040 2016/05/17 Mod end
            return;
        }
        if (NMAL7240CommonLogic.isExistSaveSearchName(bizMsg)) {
            // QC#7040 2016/05/17 Mod start
//            bizMsg.srchOptNm_S.setErrorInfo(1, "NSAM0059E", new String[] {"Search Option Name"});
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM8513E, new String[] {SEARCH_OPTION_NAME});
            // QC#7040 2016/05/17 Mod end
            return;
        }

        NMAL7240CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
}
