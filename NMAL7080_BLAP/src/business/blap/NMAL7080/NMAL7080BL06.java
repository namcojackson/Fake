/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7080;

import static business.blap.NMAL7080.constant.NMAL7080Constant.CHK_ATTRB_NM_LIST;
import static business.blap.NMAL7080.constant.NMAL7080Constant.GRP_ATTRB_NM_LIST;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM0072E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM0176E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM0177E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8234I;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8656W;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NZZM0001W;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NZZM0002I;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NZZM0003E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.SPLY_AGMT_PLN_DTL_TABLE_NAME;
import static business.blap.NMAL7080.constant.NMAL7080Constant.SPLY_AGMT_PLN_TABLE_NAME;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7080.common.NMAL7080CommonLogic;
import business.db.SPLY_AGMT_DOC_TPTMsg;
import business.db.SPLY_AGMT_FREQ_TPTMsg;
import business.db.SPLY_AGMT_PLNTMsg;
import business.db.SPLY_AGMT_PLN_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 * 2016/05/06   SRAA            K.Aratani       Update          QC#8012
 * 2016/10/14   Fujitsu         M.Ohno          Update          S21_NA#13253
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#23336
 *</pre>
 */
public class NMAL7080BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7080CMsg bizMsg = (NMAL7080CMsg) cMsg;
            NMAL7080SMsg glblMsg = (NMAL7080SMsg) sMsg;

            if ("NMAL7080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL7080Scrn00_CMN_Delete(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NMAL7080Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NMAL7080Scrn00_CMN_Delete(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        NMAL7080CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (!submiCheck(bizMsg, glblMsg)) {
            return;
        }

        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxYesNoCd_CD.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_ON_Y);
            bizMsg.setMessageInfo(NMAM8234I);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_OFF_N);
        }

        // delete
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyAgmtPlnDtlPk_A)) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A.getValue())) {
                    if (!updatePlnDtl(bizMsg, glblMsg, i, ZYPConstant.FLG_ON_Y)) {
                        return;
                    }
                }
            } else {
                continue;
            }
        }

        // annual term cap
        SPLY_AGMT_DOC_TPTMsg docInMsg = new SPLY_AGMT_DOC_TPTMsg();
        SPLY_AGMT_DOC_TPTMsg docOutMsg = new SPLY_AGMT_DOC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(docInMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(docInMsg.splyAgmtDocTpCd, bizMsg.splyAgmtDocTpCd);
        docOutMsg = (SPLY_AGMT_DOC_TPTMsg) S21CodeTableAccessor.findByKey(docInMsg);
        String dtlFlg = docOutMsg.dtlLvlQtyEntryFlg.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(dtlFlg)) {
            // 2018/04/04 S21_NA#23336 Mod Start
            // BigDecimal total = calcAnnualTermCap(bizMsg, glblMsg);
            BigDecimal total = NMAL7080CommonLogic.calcAnnualTermCap(bizMsg.splyAgmtPlnPk.getValue(), getGlobalCompanyCode());
            // 2018/04/04 S21_NA#23336 Mod End
            SPLY_AGMT_PLNTMsg inTMsg = new SPLY_AGMT_PLNTMsg();
            SPLY_AGMT_PLNTMsg outTMsg = new SPLY_AGMT_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnPk, bizMsg.splyAgmtPlnPk.getValue());
            outTMsg = (SPLY_AGMT_PLNTMsg) EZDTBLAccessor.findByKey(inTMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, outTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, outTMsg.ezUpTimeZone);
            updatePlan(bizMsg, glblMsg, total);
            ZYPEZDItemValueSetter.setValue(bizMsg.annTermCapNum, total);
        }

        // search
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getSupplyAgreement(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_PL, ZYPConstant.FLG_OFF_N); // 2016/10/14 S21_NA#13253 Add

            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
                bizMsg.setMessageInfo(NZZM0002I);
            }
            return;

        } else if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
            glblMsg.A.setValidCount(glblMsg.A.length());
        }
        List<Map<?, ?>> resultList = (List<Map<?, ?>>) ssmResult.getResultObject();
        // 2018/04/04 S21_NA#23336 Mod Start
        // NMAL7080CommonLogic.setSeachResult(resultList, glblMsg, bizMsg);
        NMAL7080CommonLogic.setSeachResult(resultList, glblMsg, bizMsg, getGlobalCompanyCode());
        // 2018/04/04 S21_NA#23336 Mod End
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_PL, ZYPConstant.FLG_OFF_N); // 2016/10/14 S21_NA#13253 Add

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }

    }

    private void doProcess_NMAL7080Scrn00_CMN_Submit(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {

        NMAL7080CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        if (!submiCheck(bizMsg, glblMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(glblMsg.H.no(0).splyAgmtPlnPk)) {
            if (!updatePlan(bizMsg, glblMsg, null)) {
                return;
            }
        } else {
            if (!insertPlan(bizMsg, glblMsg)) {
                return;
            }
        }

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).splyAgmtPlnDtlPk_A)) {
                if (ZYPConstant.FLG_ON_Y.equals(glblMsg.A.no(i).xxChkBox_A.getValue())) {
                    if (!updatePlnDtl(bizMsg, glblMsg, i, ZYPConstant.FLG_OFF_N)) {
                        return;
                    }
                }
            } else {
                if (!insertPlanDtl(bizMsg, glblMsg, i)) {
                    return;
                }
            }
        }

        // annual term cap
        SPLY_AGMT_DOC_TPTMsg docInMsg = new SPLY_AGMT_DOC_TPTMsg();
        SPLY_AGMT_DOC_TPTMsg docOutMsg = new SPLY_AGMT_DOC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(docInMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(docInMsg.splyAgmtDocTpCd, bizMsg.splyAgmtDocTpCd);
        docOutMsg = (SPLY_AGMT_DOC_TPTMsg) S21CodeTableAccessor.findByKey(docInMsg);
        String dtlFlg = docOutMsg.dtlLvlQtyEntryFlg.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(dtlFlg)) {
            // 2018/04/04 S21_NA#23336 Mod Start
            // BigDecimal total = calcAnnualTermCap(bizMsg, glblMsg);
            BigDecimal total = NMAL7080CommonLogic.calcAnnualTermCap(bizMsg.splyAgmtPlnPk.getValue(), getGlobalCompanyCode());
            // 2018/04/04 S21_NA#23336 Mod End
            SPLY_AGMT_PLNTMsg inTMsg = new SPLY_AGMT_PLNTMsg();
            SPLY_AGMT_PLNTMsg outTMsg = new SPLY_AGMT_PLNTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnPk, bizMsg.splyAgmtPlnPk.getValue());
            outTMsg = (SPLY_AGMT_PLNTMsg) EZDTBLAccessor.findByKey(inTMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, outTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, outTMsg.ezUpTimeZone);
            updatePlan(bizMsg, glblMsg, total);
            ZYPEZDItemValueSetter.setValue(bizMsg.annTermCapNum, total);
        }

        // search
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getSupplyAgreement(bizMsg, glblMsg);
        if (ssmResult.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_PL, ZYPConstant.FLG_OFF_N); // 2016/10/14 S21_NA#13253 Add

            if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
                bizMsg.setMessageInfo(NZZM0002I);
            }
            return;

        } else if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
            glblMsg.A.setValidCount(glblMsg.A.length());
        }
        List<Map<?, ?>> resultList = (List<Map<?, ?>>) ssmResult.getResultObject();
        // 2018/04/04 S21_NA#23336 Mod Start
        // NMAL7080CommonLogic.setSeachResult(resultList, glblMsg, bizMsg);
        NMAL7080CommonLogic.setSeachResult(resultList, glblMsg, bizMsg, getGlobalCompanyCode());
        // 2018/04/04 S21_NA#23336 Mod End
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_DR, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_CD, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_PL, ZYPConstant.FLG_OFF_N); // 2016/10/14 S21_NA#13253 Add

        if (!"E".equals(bizMsg.getMessageKind()) && !"W".equals(bizMsg.getMessageKind())) {
            bizMsg.setMessageInfo(NZZM0002I);
        }

    }

    /**
     * updatePlan
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean updatePlan(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg, BigDecimal termCap) {

        if (termCap == null //
                && !NMAL7080CommonLogic.checkChangedHeaderValueExist(glblMsg)) {
            return true;
        }
        if (termCap != null //
                && !NMAL7080CommonLogic.checkChangeValue(termCap, glblMsg.H.no(0).annTermCapNum.getValue())) {
            return true;
        }

        // 2016/10/14 S21_NA#13253 Add Start
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxYesNoCd_PL.getValue()) //
                && !ZYPCommonFunc.hasValue(glblMsg.xxChkBox_AF.getValue()) //
                && ZYPConstant.FLG_ON_Y.equals(glblMsg.H.no(0).xxChkBox_AF.getValue()) //
                && !ZYPCommonFunc.hasValue(termCap)) {
            S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().countActiveSupplyPriceList(bizMsg, glblMsg);

            if ((Integer) ssmResult.getResultObject() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxYesNoCd_PL, ZYPConstant.FLG_ON_Y);
                bizMsg.setMessageInfo(NMAM8656W);
                return false;
            }
        }
        // 2016/10/14 S21_NA#13253 Add End

        SPLY_AGMT_PLNTMsg inTMsg = new SPLY_AGMT_PLNTMsg();
        SPLY_AGMT_PLNTMsg outTMsg = new SPLY_AGMT_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnPk, bizMsg.splyAgmtPlnPk.getValue());
        outTMsg = (SPLY_AGMT_PLNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            // anyone update
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnNm, bizMsg.splyAgmtPlnNm.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnShortNm, bizMsg.splyAgmtPlnShortNm.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnDescTxt, bizMsg.splyAgmtPlnDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnTpCd, bizMsg.splyAgmtPlnTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.effFromDt, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.effThruDt, bizMsg.effThruDt.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_AF)) {
            ZYPEZDItemValueSetter.setValue(outTMsg.actvFlg, bizMsg.xxChkBox_AF.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(outTMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
        if (termCap == null) {
            ZYPEZDItemValueSetter.setValue(outTMsg.annTermCapNum, bizMsg.annTermCapNum.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(outTMsg.annTermCapNum, termCap);
        }

        EZDTBLAccessor.update(outTMsg);
        String returnCode = outTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {SPLY_AGMT_PLN_TABLE_NAME });
            return false;
        }

        return true;
    }

    /**
     * insert Price Group
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private Boolean insertPlan(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        SPLY_AGMT_PLNTMsg inTMsg = new SPLY_AGMT_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal agmtPlnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_AGMT_PLN_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnPk, agmtPlnPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnNm, bizMsg.splyAgmtPlnNm.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnShortNm, bizMsg.splyAgmtPlnShortNm.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnDescTxt, bizMsg.splyAgmtPlnDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnTpCd, bizMsg.splyAgmtPlnTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtDocTpCd, bizMsg.splyAgmtDocTpCd.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_AF)) {
            ZYPEZDItemValueSetter.setValue(inTMsg.actvFlg, bizMsg.xxChkBox_AF.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(inTMsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, bizMsg.effFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, bizMsg.effThruDt.getValue());

        ZYPEZDItemValueSetter.setValue(inTMsg.annTermCapNum, bizMsg.annTermCapNum.getValue());

        EZDTBLAccessor.insert(inTMsg);
        String returnCode = inTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {SPLY_AGMT_PLN_TABLE_NAME });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.splyAgmtPlnPk, agmtPlnPk);
        ZYPEZDItemValueSetter.setValue(bizMsg.annTermCapNum, inTMsg.annTermCapNum);
        return true;
    }

    /**
     * update Price Group Detail
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     */
    private Boolean updatePlnDtl(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg, int index, String dFlag) {
        if (ZYPConstant.FLG_OFF_N.equals(dFlag)) {
            if (!NMAL7080CommonLogic.checkChangedDetailValueExist(glblMsg.A.no(index), glblMsg)) {
                return true;
            }
        }

        SPLY_AGMT_PLN_DTLTMsg inTMsg = new SPLY_AGMT_PLN_DTLTMsg();
        SPLY_AGMT_PLN_DTLTMsg outTMsg = new SPLY_AGMT_PLN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnDtlPk, glblMsg.A.no(index).splyAgmtPlnDtlPk_A);
        outTMsg = (SPLY_AGMT_PLN_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
        if (outTMsg == null) {
            glblMsg.A.no(index).xxChkBox_A.setErrorInfo(1, NZZM0003E);
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        // Check time stamp
        if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(index).ezUpTime_A.getValue(), glblMsg.A.no(index).ezUpTimeZone_A.getValue(), outTMsg.ezUpTime.getValue(), outTMsg.ezUpTimeZone.getValue())) {
            // anyone update
            glblMsg.A.no(index).xxChkBox_A.setErrorInfo(1, NZZM0003E);
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(outTMsg.mdseCd, glblMsg.A.no(index).mdseCd_A);
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnFirstQty, glblMsg.A.no(index).splyAgmtPlnFirstQty_A);
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnQty, glblMsg.A.no(index).splyAgmtPlnQty_A);
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtPlnSqNum, glblMsg.A.no(index).splyAgmtPlnSqNum_A);
        ZYPEZDItemValueSetter.setValue(outTMsg.splyAgmtFreqTpCd, glblMsg.A.no(index).splyAgmtFreqTpCd_A);
        ZYPEZDItemValueSetter.setValue(outTMsg.effFromDt, glblMsg.A.no(index).effFromDt_A.getValue());
        ZYPEZDItemValueSetter.setValue(outTMsg.effThruDt, glblMsg.A.no(index).effThruDt_A.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(dFlag)) {
            ZYPEZDItemValueSetter.setValue(outTMsg.delFlg, dFlag);
        }
        EZDTBLAccessor.update(outTMsg);
        String returnCode = outTMsg.getReturnCode();

        if (!RTNCD_NORMAL.equals(returnCode)) {
            glblMsg.A.no(index).xxChkBox_A.setErrorInfo(1, NMAM0177E, new String[] {SPLY_AGMT_PLN_DTL_TABLE_NAME });
            bizMsg.setMessageInfo(NMAM0177E, new String[] {SPLY_AGMT_PLN_DTL_TABLE_NAME });
            return false;
        }

        return true;
    }

    /**
     * insert Price Group Detail
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     */
    private Boolean insertPlanDtl(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg, int index) {
        SPLY_AGMT_PLN_DTLTMsg inTMsg = new SPLY_AGMT_PLN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal prcGrpDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_AGMT_PLN_DTL_SQ);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnDtlPk, prcGrpDtlPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnPk, bizMsg.splyAgmtPlnPk);
        ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, glblMsg.A.no(index).mdseCd_A);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtFreqTpCd, glblMsg.A.no(index).splyAgmtFreqTpCd_A);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnFirstQty, glblMsg.A.no(index).splyAgmtPlnFirstQty_A);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnQty, glblMsg.A.no(index).splyAgmtPlnQty_A);
        ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnSqNum, glblMsg.A.no(index).splyAgmtPlnSqNum_A);
        ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, glblMsg.A.no(index).effFromDt_A.getValue());
        ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, glblMsg.A.no(index).effThruDt_A.getValue());

        EZDTBLAccessor.insert(inTMsg);
        String returnCode = inTMsg.getReturnCode();
        if (!RTNCD_NORMAL.equals(returnCode)) {
            glblMsg.A.no(index).xxChkBox_A.setErrorInfo(1, NMAM0176E, new String[] {SPLY_AGMT_PLN_DTL_TABLE_NAME });
            bizMsg.setMessageInfo(NMAM0176E, new String[] {SPLY_AGMT_PLN_DTL_TABLE_NAME });
            return false;
        }

        return true;
    }

    // 2018/04/04 S21_NA#23336 Del Start
//    /**
//     * insert Price Group Detail
//     * @param bizMsg Business Msg
//     * @param glblMsg Global Msg
//     * @param index Global Msg index
//     */
//    private BigDecimal calcAnnualTermCap(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
//        S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getSupplyAgreementDetailFreq(bizMsg, glblMsg);
//        if (ssmResult.isCodeNotFound()) {
//            return new BigDecimal(0);
//        }
//        List<Map<?, ?>> resultList = (List<Map<?, ?>>) ssmResult.getResultObject();
//        BigDecimal total = new BigDecimal(0);
//        for (Map<?, ?> result : resultList) {
//            SPLY_AGMT_FREQ_TPTMsg freqInMsg = new SPLY_AGMT_FREQ_TPTMsg();
//            SPLY_AGMT_FREQ_TPTMsg freqOutMsg = new SPLY_AGMT_FREQ_TPTMsg();
//            ZYPEZDItemValueSetter.setValue(freqInMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(freqInMsg.splyAgmtFreqTpCd, (String) result.get("SPLY_AGMT_FREQ_TP_CD"));
//            freqOutMsg = (SPLY_AGMT_FREQ_TPTMsg) S21CodeTableAccessor.findByKey(freqInMsg);
//            total = total.add(((BigDecimal) result.get("SPLY_AGMT_PLN_QTY")).multiply((new BigDecimal(12)).divide(freqOutMsg.splyAgmtFreqMthAot.getValue())));
//        }
//        return total;
//    }
    // 2018/04/04 S21_NA#23336 Del End

    private Boolean submiCheck(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        // checkHeader
        if (!(NMAL7080CommonLogic.checkPlnNmUnique(bizMsg, glblMsg))) {
            return false;
        }

        // checkDetail
        if (!(NMAL7080CommonLogic.checkMdse(getGlobalCompanyCode(), bizMsg))) {
            return false;
        }

        // checkDocTp
        if (!NMAL7080CommonLogic.checkDocTp(getGlobalCompanyCode(), bizMsg)) {
            return false;
        }

        Integer[] chkErrIdxList = NMAL7080CommonLogic.checkDupAttrb(bizMsg.A, CHK_ATTRB_NM_LIST);
        if (chkErrIdxList.length > 0) {
            for (int errIdx : chkErrIdxList) {
                bizMsg.A.no(errIdx).xxChkBox_A.setErrorInfo(1, NMAM0072E, new String[] {"Plan Detail" });
            }
            return false;
        }

        Integer[] grpErrIdxList = NMAL7080CommonLogic.checkDupTermByGrp(bizMsg.A, "effFromDt_A", "effThruDt_A", GRP_ATTRB_NM_LIST);
        if (grpErrIdxList.length > 0) {
            for (int errIdx : grpErrIdxList) {
                bizMsg.A.no(errIdx).effFromDt_A.setErrorInfo(1, NMAM0072E, new String[] {"Plan Detail" });
                bizMsg.A.no(errIdx).effThruDt_A.setErrorInfo(1, NMAM0072E, new String[] {"Plan Detail" });
            }
            return false;
        }

        if (!ZYPCommonFunc.hasValue(glblMsg.H.no(0).splyAgmtPlnPk)) {
            return true;
        }

        // DB check
        List<BigDecimal> dtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        	//QC#8012
            //if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).splyAgmtPlnDtlPk_A)) {
        	if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).splyAgmtPlnDtlPk_A) //
                    && ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A)) {
                dtlPkList.add(bizMsg.A.no(i).splyAgmtPlnDtlPk_A.getValue());
            }
        }

        boolean errFlg = true;
        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).splyAgmtPlnDtlPk_A) //
                    && !ZYPCommonFunc.hasValue(bizMsg.A.no(j).xxChkBox_A)) {
                continue;
            } else {
                //QC#8012
                List<BigDecimal> exclDtlPkList = new ArrayList<BigDecimal>();
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).splyAgmtPlnDtlPk_A)) exclDtlPkList.add(bizMsg.A.no(j).splyAgmtPlnDtlPk_A.getValue());
                //S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getNotDeleteSupplyAgreementDetail(bizMsg.A.no(j), glblMsg, dtlPkList, bizMsg.splyAgmtPlnPk.getValue());
                S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getNotDeleteSupplyAgreementDetail(bizMsg.A.no(j), glblMsg, exclDtlPkList, bizMsg.splyAgmtPlnPk.getValue());
                if ((Integer) ssmResult.getResultObject() > 0) {
                    bizMsg.A.no(j).xxChkBox_A.setErrorInfo(1, NMAM0072E, new String[] {"Plan Detail" });
                    errFlg = false;
                }
            }
        }

        for (int k = 0; k < bizMsg.A.getValidCount(); k++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(k).splyAgmtPlnDtlPk_A) //
                    && !ZYPCommonFunc.hasValue(bizMsg.A.no(k).xxChkBox_A)) {
                continue;
            } else {
                if (SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER.equals(bizMsg.A.no(k).splyAgmtFreqTpCd_A.getValue())) {
                    continue;
                }
                //QC#8012
                //List<BigDecimal> exclDtlPkList = new ArrayList<BigDecimal>();
                //if (ZYPCommonFunc.hasValue(bizMsg.A.no(k).splyAgmtPlnDtlPk_A)) exclDtlPkList.add(bizMsg.A.no(k).splyAgmtPlnDtlPk_A.getValue());
                S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().countDtlDupByEffDt(bizMsg.A.no(k), glblMsg, dtlPkList, bizMsg.splyAgmtPlnPk.getValue());
                //S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().countDtlDupByEffDt(bizMsg.A.no(k), glblMsg, exclDtlPkList, bizMsg.splyAgmtPlnPk.getValue());
                if ((Integer) ssmResult.getResultObject() > 0) {
                    bizMsg.A.no(k).effFromDt_A.setErrorInfo(1, NMAM0072E, new String[] {"Plan Detail" });
                    bizMsg.A.no(k).effThruDt_A.setErrorInfo(1, NMAM0072E, new String[] {"Plan Detail" });
                    errFlg = false;
                }
            }
        }

        return errFlg;
    }
}
