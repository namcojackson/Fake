/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7230;

import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0014E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0072E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0163E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0176E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0177E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM0182E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NMAM8446W;
import static business.blap.NMAL7230.constant.NMAL7230Constant.NZZM0003E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.RESULT_NUM_ABNORMAL;
import static business.blap.NMAL7230.constant.NMAL7230Constant.RESULT_NUM_NORMAL;
import static business.blap.NMAL7230.constant.NMAL7230Constant.RESULT_NUM_NOUPDATE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.SAVE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.SEARCH_OPTION_NAME;
import static business.blap.NMAL7230.constant.NMAL7230Constant.THIS_FREIGHT_ZONE;
import static business.blap.NMAL7230.constant.NMAL7230Constant.ZZM9000E;
import static business.blap.NMAL7230.constant.NMAL7230Constant.ZZM9037E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7230.common.NMAL7230CommonLogic;
import business.db.CTRYTMsg;
import business.db.FRT_ZONETMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.LINE_BIZ_TPTMsgArray;
import business.db.PRC_GRPTMsg;
import business.db.PRC_GRPTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.STTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7230BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/06/13   Fujitsu         W.Honda         Update          QC#9809
 *</pre>
 */
public class NMAL7230BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7230CMsg bizMsg = (NMAL7230CMsg) cMsg;
            NMAL7230SMsg glblMsg = (NMAL7230SMsg) sMsg;

            if ("NMAL7230Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_DeleteSearch(bizMsg, glblMsg);

            } else if ("NMAL7230Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL7230Scrn00_SaveSearch(bizMsg, glblMsg);

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
    private void doProcess_NMAL7230Scrn00_CMN_Submit(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

        // Create Delete Record Map
        List<BigDecimal> deleteList = new ArrayList<BigDecimal>();
        Map<BigDecimal, NMAL7230_ASMsg> deleteMap = new HashMap<BigDecimal, NMAL7230_ASMsg>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            boolean unmatchFlg = true;
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).frtZonePk_A1)
                        && ZYPCommonFunc.hasValue(glblMsg.A.no(i).frtZonePk_A1)
                        && glblMsg.A.no(i).frtZonePk_A1.getValue().compareTo(bizMsg.A.no(j).frtZonePk_A1.getValue()) == 0) {
                    unmatchFlg = false;
                    break;
                }
            }

            if (unmatchFlg) {
                deleteList.add(glblMsg.A.no(i).frtZonePk_A1.getValue());
                deleteMap.put(glblMsg.A.no(i).frtZonePk_A1.getValue(), glblMsg.A.no(i));
            }
        }

        // Detail Value Check
        boolean isErr = false;
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

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dsAcctNm_A1)) {
                SELL_TO_CUSTTMsg inTMsg = new SELL_TO_CUSTTMsg();
                SELL_TO_CUSTTMsgArray outTMsg = null;
                inTMsg.setSQLID("505");
                inTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                inTMsg.setConditionValue("dsAcctNm01", bizMsg.A.no(i).dsAcctNm_A1.getValue());
                outTMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    bizMsg.A.no(i).dsAcctNm_A1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.A.no(i).dsAcctNm_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dsAcctNum_A1, outTMsg.no(0).sellToCustCd);
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcGrpNm_A1)) {
                PRC_GRPTMsg inTMsg = new PRC_GRPTMsg();
                PRC_GRPTMsgArray outTMsg = null;
                inTMsg.setSQLID("002");
                inTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
                inTMsg.setConditionValue("prcGrpNm01", bizMsg.A.no(i).prcGrpNm_A1.getValue());
                inTMsg.setConditionValue("prcGrpTpCd01", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
                outTMsg = (PRC_GRPTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                if (outTMsg.length() < 1) {
                    bizMsg.A.no(i).prcGrpNm_A1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.A.no(i).prcGrpNm_A1.getValue(), inTMsg.getTableName()});
                    isErr = true;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).prcGrpPk_A1, outTMsg.no(0).prcGrpPk);
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).shipToCtryCd_A1)) {
                CTRYTMsg inCtryMsg = new CTRYTMsg();
                CTRYTMsg outCtryMsg = null;
                ZYPEZDItemValueSetter.setValue(inCtryMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(inCtryMsg.ctryCd, bizMsg.A.no(i).shipToCtryCd_A1);
                outCtryMsg = (CTRYTMsg) S21CodeTableAccessor.findByKey(inCtryMsg);
                if (outCtryMsg == null) {
                    bizMsg.A.no(i).shipToCtryCd_A1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.A.no(i).shipToCtryCd_A1.getValue(), inCtryMsg.getTableName()});
                    isErr = true;
                }

                // QC#9809 2016/06/13 Mod start
//                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).shipToStCd_A1)
//                        || CTRY.UNITED_STATES_OF_AMERICA.equals(bizMsg.A.no(i).shipToCtryCd_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).shipToStCd_A1)) {
                // QC#9809 2016/06/13 Mod start
                    STTMsg inStMsg = new STTMsg();
                    STTMsg outStMsg = null;
                    ZYPEZDItemValueSetter.setValue(inStMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inStMsg.stCd, bizMsg.A.no(i).shipToStCd_A1.getValue());
                    outStMsg = (STTMsg) S21CodeTableAccessor.findByKey(inStMsg);
                    if (outStMsg == null) {
                        bizMsg.A.no(i).shipToStCd_A1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.A.no(i).shipToStCd_A1.getValue(), inStMsg.getTableName()});
                        isErr = true;
                    }
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
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).frtZonePk_A1)) {
                updateList.add(bizMsg.A.no(i).frtZonePk_A1.getValue());
            }
        }

        if (NMAL7230CommonLogic.checkActiveZoneForScreen(bizMsg)) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (NMAL7230CommonLogic.checkActiveDuplicateZone(bizMsg.A.no(i), deleteList, updateList)) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAM0072E, new String[] {THIS_FREIGHT_ZONE});
                isErr = true;
            }
        }

        if (isErr) {
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        int cnt = 0;
        // Delete
        for (BigDecimal frtZonePk : deleteList) {
            if (ZYPCommonFunc.hasValue(frtZonePk)) {
                int delCnt = deleteFrtZone(deleteMap.get(frtZonePk), bizMsg);
                if (delCnt == RESULT_NUM_ABNORMAL) {
                    return;
                } else {
                    cnt += delCnt;
                }
            }
        }

        // update & insert
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).frtZonePk_A1)) {
                int updCnt = updateFrtZone(bizMsg, glblMsg, i);
                if (updCnt == RESULT_NUM_ABNORMAL) {
                    return;
                } else {
                    cnt += updCnt;
                }
            } else {
                int insCnt = insertFrtZone(bizMsg, i);
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
     * delete Freight Zone
     * @param glblLineMsg Global Line Msg
     * @param bizMsg Business Msg
     * @return 1 normal end, -1 abnormal end
     */
    private int deleteFrtZone(NMAL7230_ASMsg glblLineMsg, NMAL7230CMsg bizMsg) {

        FRT_ZONETMsg inTMsg = new FRT_ZONETMsg();
        FRT_ZONETMsg outTMsg = null;
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.frtZonePk, glblLineMsg.frtZonePk_A1);
        outTMsg = (FRT_ZONETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
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
     * update Freight Zone
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     * @return 1 normal end, 0 not update, -1 abnormal end
     */
    private int updateFrtZone(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg, int index) {
        if (!checkChangedValueExist(bizMsg.A.no(index), glblMsg)) {
            return RESULT_NUM_NOUPDATE;
        }

        FRT_ZONETMsg inTMsg = new FRT_ZONETMsg();
        FRT_ZONETMsg outTMsg = null;
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.frtZonePk, bizMsg.A.no(index).frtZonePk_A1);
        outTMsg = (FRT_ZONETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
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
        ZYPEZDItemValueSetter.setValue(outTMsg.frtZoneNum, bizMsg.A.no(index).frtZoneNum_A1);
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
    private Boolean checkChangedValueExist(NMAL7230_ACMsg bizMsgALine, NMAL7230SMsg glblMsg) {
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (bizMsgALine.frtZonePk_A1.getValue().compareTo(glblMsg.A.no(i).frtZonePk_A1.getValue()) != 0) {
                continue;
            }

            if (!bizMsgALine.frtZoneNum_A1.getValue().equals(glblMsg.A.no(i).frtZoneNum_A1.getValue())
                    || !bizMsgALine.effThruDt_A1.getValue().equals(glblMsg.A.no(i).effThruDt_A1.getValue())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * insert Freight Zone
     * @param bizMsg Business Msg
     * @param index Global Msg index
     * @return 1 normal end, -1 abnormal end
     */
    private int insertFrtZone(NMAL7230CMsg bizMsg, int index) {
        FRT_ZONETMsg inTMsg = new FRT_ZONETMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal frtZonePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FRT_ZONE_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.frtZonePk, frtZonePk);
        ZYPEZDItemValueSetter.setValue(inTMsg.lineBizTpCd, bizMsg.A.no(index).lineBizTpCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, bizMsg.A.no(index).dsAcctNum_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcGrpPk, bizMsg.A.no(index).prcGrpPk_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.frtZoneNum, bizMsg.A.no(index).frtZoneNum_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToCtryCd, bizMsg.A.no(index).shipToCtryCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToStCd, bizMsg.A.no(index).shipToStCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToFromPostCd, bizMsg.A.no(index).shipToFromPostCd_A1);
        ZYPEZDItemValueSetter.setValue(inTMsg.shipToThruPostCd, bizMsg.A.no(index).shipToThruPostCd_A1);
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
    private void doProcess_NMAL7230Scrn00_DeleteSearch(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, NMAM0014E, new String[] {"Saved Search Options"});
            return;
        }

        NMAL7230CommonLogic.callNszc0330forDeleteSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7230Scrn00_SaveSearch(NMAL7230CMsg bizMsg, NMAL7230SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E, new String[] {SEARCH_OPTION_NAME});
            return;
        }

        if (NMAL7230CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E, new String[] {SAVE, SEARCH_OPTION_NAME});
            return;
        }

        NMAL7230CommonLogic.callNszc0330forSaveSearch(bizMsg, glblMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

        return;
    }
}
