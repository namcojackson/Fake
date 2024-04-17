/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7280;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import business.db.PRC_RULE_COND_DTLTMsg;
import business.db.PRC_RULE_COND_GRPTMsg;
import business.db.PRC_RULE_COND_HDRTMsg;
import business.db.PRC_RULE_HDRTMsg;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_01;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_02;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_03;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_04;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_05;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_06;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_07;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_08;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_09;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_10;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_11;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_12;
import static business.blap.NMAL7280.constant.NMAL7280Constant.DETAIL_NAME;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CHK_A;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NZZM0003E;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAM8020E;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAM8260E;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAL7280SCRN00_CMN_SUBMIT;
import static business.blap.NMAL7280.constant.NMAL7280Constant.NMAL7280SCRN00_DELETEROW_CONDGRP;
import static business.blap.NMAL7280.constant.NMAL7280Constant.CONDITION_COUNT;


/**
 *<pre>
 * NMAL7280BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7280BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7280CMsg bizMsg = (NMAL7280CMsg) cMsg;


            if (NMAL7280SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL7280Scrn00_CMN_Submit(bizMsg);
            } else if (NMAL7280SCRN00_DELETEROW_CONDGRP.equals(screenAplID)) {
                doProcess_NMAL7280Scrn00_DeleteRow_CondGrp(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * DeleteRow_CondGrp Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7280Scrn00_DeleteRow_CondGrp(NMAL7280CMsg bizMsg) {

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, ZYPConstant.FLG_ON_Y);
        for (int idx : selectRows) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).prcRuleCondHdrPk_A)) {
                S21SsmEZDResult ssmResult = NMAL7280Query.getInstance().getDtl(bizMsg, idx);
                if (ssmResult.isCodeNormal()) {
                  bizMsg.setMessageInfo(NMAM8260E, new String[] {DETAIL_NAME});
                  return;
                }

                PRC_RULE_COND_HDRTMsg tMsg = new PRC_RULE_COND_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, bizMsg.A.no(idx).prcRuleCondHdrPk_A);
                tMsg = (PRC_RULE_COND_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(
                        bizMsg.A.no(idx).ezUpTime_A.getValue()
                        , bizMsg.A.no(idx).ezUpTimeZone_A.getValue()
                        , tMsg.ezUpTime.getValue()
                        , tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return;
                    }
                }

                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return;
                }
                //Detail
                for(int j = 1; j <= CONDITION_NO_12; j ++){
                    boolean condition = condDtlDelete(bizMsg, idx, j, tMsg.prcRuleCondHdrPk.getValue());
                    if (!condition) {
                        return;
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.A, selectRows);
    }
    private boolean condDtlDelete(NMAL7280CMsg bizMsg, int i, int j, BigDecimal hdrPk) {
        PRC_RULE_COND_DTLTMsg tMsg = new PRC_RULE_COND_DTLTMsg();
        if (j == CONDITION_NO_01) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A1);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_02) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A2);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_03) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A3);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_04) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A4)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A4);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_05) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A5)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A5);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_06) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A6)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A6);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_07) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A7)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A7);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_08) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A8)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A8);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_09) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A9)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A9);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_10) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AA);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_11) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AB);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        if (j == CONDITION_NO_12) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AC)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AC);
                tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7280Scrn00_CMN_Submit(NMAL7280CMsg bizMsg) {

        if (bizMsg.A.getValidCount() > 0) {
            PRC_RULE_HDRTMsg tHMsg = new PRC_RULE_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(tHMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tHMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk);
            tHMsg = (PRC_RULE_HDRTMsg) EZDTBLAccessor.findByKey(tHMsg);
            if (!ZYPDateUtil.isSameTimeStamp(
                bizMsg.ezUpTime.getValue()
                , bizMsg.ezUpTimeZone.getValue()
                , tHMsg.ezUpTime.getValue()
                , tHMsg.ezUpTimeZone.getValue()
                )) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }
        } else {
            return;
        }

        Hashtable<BigDecimal, BigDecimal> pkTable = new Hashtable<BigDecimal, BigDecimal>();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            pkTable.put(bizMsg.B.no(i).prcRuleCondNum_B.getValue()
                    , bizMsg.B.no(i).prcRuleTrxCondPk_B.getValue());

        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            //Header
            PRC_RULE_COND_HDRTMsg tMsg = new PRC_RULE_COND_HDRTMsg();
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondHdrPk_A)) {
                //update
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, bizMsg.A.no(i).prcRuleCondHdrPk_A);
                tMsg = (PRC_RULE_COND_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (!ZYPDateUtil.isSameTimeStamp(
                        bizMsg.A.no(i).ezUpTime_A.getValue()
                        , bizMsg.A.no(i).ezUpTimeZone_A.getValue()
                        , tMsg.ezUpTime.getValue()
                        , tMsg.ezUpTimeZone.getValue()
                        )) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                setScreenValue(bizMsg, i, tMsg);
                EZDTBLAccessor.update(tMsg);

            } else {
                //Insert
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_HDR_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, bizMsg.prcRuleHdrPk);
                setScreenValue(bizMsg, i, tMsg);
                EZDTBLAccessor.insert(tMsg);
            }

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM8020E);
                return;
            }
            
            //Detail
            for(int j = 1; j <= CONDITION_NO_12; j ++){
                boolean condition = condDtlAccess(bizMsg, pkTable, i, j, tMsg.prcRuleCondHdrPk.getValue());
                if (!condition) {
                    return;
                }
            }
        }
    }

    private void setScreenValue(NMAL7280CMsg bizMsg, int i, PRC_RULE_COND_HDRTMsg tMsg) {
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondGrpCd, bizMsg.A.no(i).prcRuleCondGrpCd_A);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleOpTpCd, bizMsg.A.no(i).prcRuleOpTpCd_A1);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.A.no(i).effFromDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.A.no(i).effThruDt_A.getValue());
    }

    private boolean condDtlAccess(NMAL7280CMsg bizMsg, Hashtable<BigDecimal, BigDecimal> pkTable, int i, int j, BigDecimal hdrPk) {
        PRC_RULE_COND_DTLTMsg tMsg = new PRC_RULE_COND_DTLTMsg();

        if (j == CONDITION_NO_01) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A1)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A1);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A1);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A1)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A1)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A1);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_02) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A2)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A2)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A2);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A2);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A2.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A2)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A2)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A2);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_03) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A3)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A3)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A3);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A3);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A3.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A3)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A3)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A3);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_04) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A4)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A4)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A4);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A4);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A4.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A4)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A4)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A4);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_05) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A5)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A5)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A5);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A5);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A5.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A5)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A5)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A5);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_06) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A6)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A6)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A6);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A6);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A6.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A6)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A6)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A6);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_07) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A7)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A7)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A7);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A7);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A7.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A7)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A7)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A7);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_08) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A8)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A8)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A8);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A8);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A8.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A8)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A8)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A8);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_09) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_A9)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A9)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A9);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_A9);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_A9.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A9)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_A9)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_A9);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_10) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_AA)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AA)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AA);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_AA);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_AA.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AA)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AA)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AA);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_11) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_AB)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AB)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AB);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_AB);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_AB.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AB)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AB)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AB);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        if (j == CONDITION_NO_12) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondNum_AC)) {
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AC)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AC);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondHdrPk, hdrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondNum, bizMsg.A.no(i).prcRuleCondNum_AC);
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleTrxCondPk, pkTable.get(bizMsg.A.no(i).prcRuleCondNum_AC.getValue()));
                ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlNum, new BigDecimal(j));
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AC)) {
                    EZDTBLAccessor.update(tMsg);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRC_RULE_COND_DTL_SQ));
                    EZDTBLAccessor.insert(tMsg);
                }
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).prcRuleCondDtlPk_AC)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(tMsg.prcRuleCondDtlPk, bizMsg.A.no(i).prcRuleCondDtlPk_AC);
                    tMsg = (PRC_RULE_COND_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8020E);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
