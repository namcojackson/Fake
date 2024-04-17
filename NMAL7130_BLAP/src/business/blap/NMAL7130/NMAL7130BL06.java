/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7130;

import static business.blap.NMAL7130.constant.NMAL7130Constant.CHK_D;
import static business.blap.NMAL7130.constant.NMAL7130Constant.COMMA;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM0010E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM0043E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM0072E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM8020E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM8054E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM8121E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NZZM0003E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.TAB_INIT_PRC_PNT_SUM;
import static business.blap.NMAL7130.constant.NMAL7130Constant.TAB_TRX_CHRG;
import static business.blap.NMAL7130.constant.NMAL7130Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7130.common.NMAL7130CommonLogic;
import business.db.PRC_CONTRTMsg;
import business.db.PRC_CONTR_ORD_CATGTMsg;
import business.db.PRC_CONTR_TERM_CONDTMsg;
import business.db.PRC_CONTR_TRX_CHRGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TERM_COND_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7130BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4704
 * 2016/07/01   Fujitsu         Y.Kanefusa      Update          S21_NA#11221
 *</pre>
 */
public class NMAL7130BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7130CMsg bizMsg = (NMAL7130CMsg) cMsg;
            NMAL7130SMsg glblMsg = (NMAL7130SMsg) sMsg;

            if ("NMAL7130Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7130Scrn00_CratNewVrsn".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CratNewVrsn(bizMsg, glblMsg);

            } else if ("NMAL7130Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_DeleteRow(bizMsg);

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
    private void doProcess_NMAL7130Scrn00_CMN_Submit(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {

        boolean isErr = false;
        if (isExistContrNum(bizMsg)) {
            isErr = true;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_H1)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.effFromDt_H1)
                    || ZYPDateUtil.compare(bizMsg.effFromDt_H1.getValue(), bizMsg.effThruDt_H1.getValue()) > 0) {
                bizMsg.effFromDt_H1.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                isErr = true;
            }
        }
        // QC#11221 2016/07/01 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_C1)) {
            S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().getLeaseAcct(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.dsAcctNum_C1.setErrorInfo(1, NMAM8121E, new String[] {"Leasing Company" });
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_INIT_PRC_PNT_SUM);
                return;
            }
        }
        // QC#11221 2016/07/01 Add End


        Map<String, List<String[]>> keyListRec = new HashMap<String, List<String[]>>(bizMsg.D.getValidCount());

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_C1)) {
            
        }
        StringBuilder keyInfo = new StringBuilder();
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if (!isRebTpMdse(bizMsg, bizMsg.D.no(i).mdseCd_D1)) {
                isErr = true;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).dsAcctNum_D1)) {
                if (!isExistAcctNum(bizMsg, bizMsg.D.no(i).dsAcctNum_D1)) {
                    isErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).xxRecNmTxt_D1)) {
                if (!NMAL7130CommonLogic.checkOrdTpAndSetCodeBK(bizMsg, bizMsg.D.no(i).xxRecNmTxt_D1, bizMsg.D.no(i).xxRecNmTxt_BK)) {
                    isErr = true;
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).effThruDt_D1)) {
                if (ZYPDateUtil.compare(bizMsg.D.no(i).effFromDt_D1.getValue(), bizMsg.D.no(i).effThruDt_D1.getValue()) > 0) {
                    bizMsg.D.no(i).effFromDt_D1.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isErr = true;
                }
            }

            keyInfo.setLength(0);
            keyInfo = keyInfo.append(bizMsg.D.no(i).prcContrChrgTpCd_D1.getValue()).append(COMMA);
            keyInfo = keyInfo.append(bizMsg.D.no(i).mdseCd_D1.getValue()).append(COMMA);
            keyInfo = keyInfo.append(bizMsg.D.no(i).xxRecNmTxt_D1.getValue()).append(COMMA);
            keyInfo = keyInfo.append(bizMsg.D.no(i).dsAcctNum_D1.getValue()).append(COMMA);
            keyInfo = keyInfo.append(bizMsg.D.no(i).prcContrRebTpCd_D1.getValue());

            String effThruDt = NMAL7130CommonLogic.toHighValDate(bizMsg.D.no(i).effThruDt_D1.getValue());
            if (keyListRec.containsKey(keyInfo.toString())) {
                List<String[]> extList =  keyListRec.get(keyInfo.toString());

                for (String[] extStr : extList) {
                    if (ZYPDateUtil.compare(extStr[0], effThruDt) <= 0
                            && ZYPDateUtil.compare(bizMsg.D.no(i).effFromDt_D1.getValue(), extStr[1]) <= 0) {
                        bizMsg.D.no(i).xxChkBox_D1.setErrorInfo(1, NMAM0072E, new String[] {"Transaction Charges"});
                        isErr = true;
                        break;
                    }
                }
                String[] newStr = new String[] {bizMsg.D.no(i).effFromDt_D1.getValue(), effThruDt};
                extList.add(newStr);
                keyListRec.put(keyInfo.toString(), extList);

            } else {
                String[] newStr = new String[] {bizMsg.D.no(i).effFromDt_D1.getValue(), effThruDt};
                List<String[]> newList = new ArrayList<String[]>();
                newList.add(newStr);
                keyListRec.put(keyInfo.toString(), newList);
            }
        }
        if (isErr) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_TRX_CHRG);
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcContrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcContrPk_H1, bizMsg.prcContrPk_BK);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcContrPk_H1, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CONTR_SQ));
        }

        // Insert/Update
        if (!submitPrcContr(bizMsg, glblMsg)) {
            return;
        }

        if (!submitPrcContrTrxChrg(bizMsg, glblMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.prcTermCondVrsnNum_L1.no(0))) {
            if (bizMsg.prcTermCondVrsnNum_L1.no(0).getValue().equals(bizMsg.prcTermCondVrsnNum_E1.getValue())) {
                // Only the latest version is updated.
                if (!submitPrcContrTermCond(bizMsg, glblMsg, bizMsg.prcTermCondVrsnNum_E1.getValue(), true)) {
                    return;
                }
            }
        } else {
            // 1st Version.
            if (!submitPrcContrTermCond(bizMsg, glblMsg, BigDecimal.ONE, false)) {
                return;
            }
        }
    }

    /**
     * CratNewVrsn Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7130Scrn00_CratNewVrsn(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {

        // Set Default Value.
        ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondStsCd_E1, PRC_TERM_COND_STS.INITIAL_DRAFT);

        // Insert PRC_CONTR_TERM_COND
        if (!submitPrcContrTermCond(bizMsg, glblMsg, bizMsg.prcTermCondVrsnNum_L1.no(0).getValue().add(BigDecimal.ONE), false)) {
            return;
        }

    }

    /**
     * Delete Row.
     * @param bizMsg NMAL7130CMsg
     * @param glblMsg NMAL7130SMsg
     */
    private void doProcess_NMAL7130Scrn00_DeleteRow(NMAL7130CMsg bizMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.D, CHK_D, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {

            if (!ZYPCommonFunc.hasValue(bizMsg.D.no(idx).prcContrTrxChrgPk_D1)) {
                continue;
            }

            PRC_CONTR_TRX_CHRGTMsg tMsg = new PRC_CONTR_TRX_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrPk, bizMsg.prcContrPk_BK);
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrTrxChrgPk, bizMsg.D.no(idx).prcContrTrxChrgPk_D1);
            tMsg = (PRC_CONTR_TRX_CHRGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.D.no(idx).ezUpTime_D1.getValue(), bizMsg.D.no(idx).ezUpTimeZone_D1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
            }

            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM8020E);
                return;
            }

        }

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.D, selectRows);
    }

    private boolean submitPrcContr(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {
        if (!checkNoChangedHeaderValue(bizMsg, glblMsg)) {
            return true;
        }

        PRC_CONTRTMsg tMsg = new PRC_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.prcContrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrPk, bizMsg.prcContrPk_BK);
            tMsg = (PRC_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_C1.getValue(), bizMsg.ezUpTimeZone_C1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrPk, bizMsg.prcContrPk_H1);
        }

        ZYPEZDItemValueSetter.setValue(tMsg.prcContrNm, bizMsg.prcContrNm_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrNum, bizMsg.prcContrNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrCustBidNum, bizMsg.prcContrCustBidNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.assnPgmContrFlg, getFlg(bizMsg.assnPgmContrFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrShortDescTxt, bizMsg.prcContrShortDescTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, getFlg(bizMsg.actvFlg_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrVrsnNum, bizMsg.prcContrVrsnNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrRnwDt, bizMsg.prcContrRnwDt_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrTermMthNum, bizMsg.prcContrTermMthNum_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizTpCd, bizMsg.lineBizTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyCd, bizMsg.dsAcctNum_C1.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.initFdRate, bizMsg.initFdRate_C1);

        if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.prcContrPk_BK))) {
            bizMsg.setMessageInfo(NMAM8020E);
            return false;
        }

        return true;
    }

    private boolean submitPrcContrTrxChrg(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            if (!checkNoChangedTrxChrgValue(bizMsg, glblMsg, i)) {
                continue;
            }
            PRC_CONTR_TRX_CHRGTMsg tMsg = new PRC_CONTR_TRX_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrPk, bizMsg.prcContrPk_H1);
            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).prcContrTrxChrgPk_D1)) {
                ZYPEZDItemValueSetter.setValue(tMsg.prcContrTrxChrgPk, bizMsg.D.no(i).prcContrTrxChrgPk_D1);
                tMsg = (PRC_CONTR_TRX_CHRGTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(bizMsg.D.no(i).ezUpTime_D1.getValue(), bizMsg.D.no(i).ezUpTimeZone_D1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return false;
                    }
                }

            } else {

                ZYPEZDItemValueSetter.setValue(tMsg.prcContrTrxChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_CONTR_TRX_CHRG_SQ));
            }

            ZYPEZDItemValueSetter.setValue(tMsg.prcContrChrgTpCd, bizMsg.D.no(i).prcContrChrgTpCd_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, bizMsg.D.no(i).mdseCd_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrTrxChrgPct, bizMsg.D.no(i).prcContrTrxChrgPct_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrTrxChrgAmt, bizMsg.D.no(i).prcContrTrxChrgAmt_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrTrxChrgNm, bizMsg.D.no(i).prcContrTrxChrgNm_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, bizMsg.D.no(i).dsAcctNum_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, bizMsg.D.no(i).effFromDt_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, bizMsg.D.no(i).effThruDt_D1);
            ZYPEZDItemValueSetter.setValue(tMsg.prcContrRebTpCd, bizMsg.D.no(i).prcContrRebTpCd_D1.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.appReqFlg, getFlg(bizMsg.D.no(i).appReqFlg_D1.getValue()));

            if (!submitTbl(tMsg, ZYPCommonFunc.hasValue(bizMsg.D.no(i).prcContrTrxChrgPk_D1))) {
                bizMsg.setMessageInfo(NMAM8020E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).prcContrTrxChrgPk_D1, tMsg.prcContrTrxChrgPk);

            // 2016/03/03 CSA-QC#4703 Mod Start
            //if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).xxRecNmTxt_D1)) {
            if (!bizMsg.D.no(i).xxRecNmTxt_D1.getValue().equals(glblMsg.D.no(i).xxRecNmTxt_D1.getValue())) {
            // 2016/03/03 CSA-QC#4703 Mod Start
                if (!submitPrcContrOrdCatg(bizMsg, bizMsg.D.no(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean submitPrcContrOrdCatg(NMAL7130CMsg bizMsg, NMAL7130_DCMsg dCMsg) {

        String ordCatgTxt = dCMsg.xxRecNmTxt_BK.getValue();
        String[] ordCatgAry = ordCatgTxt.split(bizMsg.addCharTxt.getValue());

        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().getPrcContrOrdCatg(bizMsg.prcContrPk_H1.getValue(), dCMsg.prcContrTrxChrgPk_D1.getValue());
        List<String> rsltStrList = (List<String>) ssmResult.getResultObject();

        boolean isExist = false;
        if (rsltStrList != null) {
            for (String rsltStr : rsltStrList) {
                isExist = false;
                for (String ordCatgCd : ordCatgAry) {
                    if (!ZYPCommonFunc.hasValue(ordCatgCd)) {
                        continue;
                    }
                    if (ordCatgCd.equals(rsltStr)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    PRC_CONTR_ORD_CATGTMsg inTMsg = new PRC_CONTR_ORD_CATGTMsg();
                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcContrPk, bizMsg.prcContrPk_H1);
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcContrTrxChrgPk, dCMsg.prcContrTrxChrgPk_D1);
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsOrdCatgCd, rsltStr);

                    PRC_CONTR_ORD_CATGTMsg outTMsg = (PRC_CONTR_ORD_CATGTMsg) EZDTBLAccessor.findByKeyForUpdate(inTMsg);
                    EZDTBLAccessor.remove(outTMsg);
                }
            }
        }

        for (String ordCatgCd : ordCatgAry) {
            if (!ZYPCommonFunc.hasValue(ordCatgCd)) {
                continue;
            }

            isExist = false;
            if (rsltStrList != null) {
                for (String rsltStr : rsltStrList) {
                    if (ordCatgCd.equals(rsltStr)) {
                        isExist = true;
                        break;
                    }
                }
            }

            if (isExist) {
                continue;
            }  else {
                PRC_CONTR_ORD_CATGTMsg newTMsg = new PRC_CONTR_ORD_CATGTMsg();
                ZYPEZDItemValueSetter.setValue(newTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(newTMsg.prcContrPk, bizMsg.prcContrPk_H1);
                ZYPEZDItemValueSetter.setValue(newTMsg.prcContrTrxChrgPk, dCMsg.prcContrTrxChrgPk_D1);
                ZYPEZDItemValueSetter.setValue(newTMsg.dsOrdCatgCd, ordCatgCd);

                if (!submitTbl(newTMsg, isExist)) {
                    bizMsg.setMessageInfo(NMAM8020E);
                    return false;
                }
            }
        }

        return true;
    }

    private boolean submitPrcContrTermCond(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg, BigDecimal vrsnNum, boolean isUpd) {
        if (!checkNoChangedTermCondValue(bizMsg, glblMsg)) {
            return true;
        }

        PRC_CONTR_TERM_CONDTMsg tMsg = new PRC_CONTR_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrPk, bizMsg.prcContrPk_H1);
        ZYPEZDItemValueSetter.setValue(tMsg.prcTermCondVrsnNum, vrsnNum);
        if (isUpd) {
            tMsg = (PRC_CONTR_TERM_CONDTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            } else {
                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_E1.getValue(), bizMsg.ezUpTimeZone_E1.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(tMsg.prcTermCondStsCd, bizMsg.prcTermCondStsCd_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTrxFlexPct, bizMsg.ordTrxFlexPct_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.allwDclnMaintFlg, getFlg(bizMsg.allwDclnMaintFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.mustUseEquipPrcFlg, getFlg(bizMsg.mustUseEquipPrcFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.leaseRtrnInclInPrcFlg, getFlg(bizMsg.leaseRtrnInclInPrcFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.ovrdSysTonerTpFlg, getFlg(bizMsg.ovrdSysTonerTpFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.billTonerFrtChrgFlg, getFlg(bizMsg.billTonerFrtChrgFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.tonerAlwncPct, bizMsg.tonerAlwncPct_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.nonStdStartTm, bizMsg.nonStdStartTm_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.lnrEttlFlg, getFlg(bizMsg.lnrEttlFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.maxDownTmDaysAot, bizMsg.maxDownTmDaysAot_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.lflReplOptFlg, getFlg(bizMsg.lflReplOptFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.lflReplTermNum, bizMsg.lflReplTermNum_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.unltdTngReqFlg, getFlg(bizMsg.unltdTngReqFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.custPrvtyFlg, getFlg(bizMsg.custPrvtyFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.hddSvcInclFlg, getFlg(bizMsg.hddSvcInclFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.hddCleanPrcGtdFlg, getFlg(bizMsg.hddCleanPrcGtdFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.tmAndMatUplftTxt, bizMsg.tmAndMatUplftTxt_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.docReqFrmAgmtNm, bizMsg.docReqFrmAgmtNm_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.mstrAgmtDocNm, bizMsg.mstrAgmtDocNm_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.mstrReplAquFlg, getFlg(bizMsg.mstrReplAquFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.mstrReplCmbnPrchFlg, getFlg(bizMsg.mstrReplCmbnPrchFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.mstrReplLeaseFlg, getFlg(bizMsg.mstrReplLeaseFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.leaseTrxAllwFlg, getFlg(bizMsg.leaseTrxAllwFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.supplTermCmpyStdFrmTxt, bizMsg.supplTermCmpyStdFrmTxt_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.aftHourBillRate, bizMsg.aftHourBillRate_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.rspTmMeasPerCd, bizMsg.rspTmMeasPerCd_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.rspTmComitTxt, bizMsg.rspTmComitTxt_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.svcEtaCallReqHrsNum, bizMsg.svcEtaCallReqHrsNum_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.tonerTpNm, bizMsg.tonerTpNm_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.tonerYieldCnt, bizMsg.tonerYieldCnt_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.stplInclSvcFlg, getFlg(bizMsg.stplInclSvcFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.prcContrPrcTpCd, bizMsg.prcContrPrcTpCd_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.dlyFirstCallGtdFlg, getFlg(bizMsg.dlyFirstCallGtdFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.onSiteTechInclFlg, getFlg(bizMsg.onSiteTechInclFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.primTechInclFlg, getFlg(bizMsg.primTechInclFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.iwrEsclFlg, getFlg(bizMsg.iwrEsclFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.maxRnwIncrAmtRate, bizMsg.maxRnwIncrAmtRate_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.maxStdAnnIncrPct, bizMsg.maxStdAnnIncrPct_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.erlTrmnOptFlg, getFlg(bizMsg.erlTrmnOptFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.upTmGtdPct, bizMsg.upTmGtdPct_E1);
        ZYPEZDItemValueSetter.setValue(tMsg.fleetContrAllwFlg, getFlg(bizMsg.fleetContrAllwFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.aggrContrAllwFlg, getFlg(bizMsg.aggrContrAllwFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.custQtlyBizRvwReqFlg, getFlg(bizMsg.custQtlyBizRvwReqFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.stdQtlyBizRvwReqFlg, getFlg(bizMsg.stdQtlyBizRvwReqFlg_E1.getValue()));
        ZYPEZDItemValueSetter.setValue(tMsg.reqRptIntvlCd, bizMsg.reqRptIntvlCd_E1);

        if (!submitTbl(tMsg, isUpd)) {
            bizMsg.setMessageInfo(NMAM8020E);
            return false;
        }

        return true;
    }

    private boolean isExistContrNum(NMAL7130CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().isExistContrNum(bizMsg.prcContrPk_BK.getValue(), bizMsg.prcContrNum_H1.getValue());
        if (!S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())) {
            bizMsg.prcContrNum_H1.setErrorInfo(1, NMAM0010E, new String[] {bizMsg.prcContrNum_H1.getValue()});
            return true;
        }
        return false;
    }

    private boolean isRebTpMdse(NMAL7130CMsg bizMsg, EZDCStringItem mdseCdItem) {
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().isRebTpMdse(mdseCdItem.getValue(), bizMsg.coaMdseTpCd_P1.getValue());
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            mdseCdItem.setErrorInfo(1, ZZZM9006E, new String[] {mdseCdItem.getValue()});
            return false;
        }
        return true;
    }

    private boolean isExistAcctNum(NMAL7130CMsg bizMsg, EZDCStringItem dsAcctNum) {
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().isExistAcctNum(dsAcctNum.getValue());
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            dsAcctNum.setErrorInfo(1, ZZZM9006E, new String[] {dsAcctNum.getValue()});
            return false;
        }
        return true;
    }

    private static String getFlg(String val) {
        if (ZYPConstant.FLG_ON_Y.equals(val)) {
            return val;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private boolean submitTbl(EZDTMsg inTMsg, boolean isExists) {
        if (isExists) {
            EZDTBLAccessor.update(inTMsg);
        } else {
            EZDTBLAccessor.insert(inTMsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean checkNoChangedHeaderValue(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {
        BigDecimal prcContrVrsnNum_H1 = nvlBigDecimal(glblMsg.prcContrVrsnNum_H1.getValue());
        BigDecimal prcContrTermMthNum_H1 = nvlBigDecimal(glblMsg.prcContrTermMthNum_H1.getValue());
        BigDecimal initFdRate_C1 = nvlBigDecimal(glblMsg.initFdRate_C1.getValue());

        if (!glblMsg.prcContrNm_H1.getValue().equals(bizMsg.prcContrNm_H1.getValue())
                || !glblMsg.prcContrNum_H1.getValue().equals(bizMsg.prcContrNum_H1.getValue())
                || !glblMsg.prcContrCustBidNum_H1.getValue().equals(bizMsg.prcContrCustBidNum_H1.getValue())
                || !glblMsg.assnPgmContrFlg_H1.getValue().equals(getFlg(bizMsg.assnPgmContrFlg_H1.getValue()))
                || !glblMsg.prcContrShortDescTxt_H1.getValue().equals(bizMsg.prcContrShortDescTxt_H1.getValue())
                || !glblMsg.effFromDt_H1.getValue().equals(bizMsg.effFromDt_H1.getValue())
                || !glblMsg.effThruDt_H1.getValue().equals(bizMsg.effThruDt_H1.getValue())
                || !glblMsg.actvFlg_H1.getValue().equals(getFlg(bizMsg.actvFlg_H1.getValue()))
                || !glblMsg.lineBizTpCd_H1.getValue().equals(bizMsg.lineBizTpCd_H1.getValue())
                || (prcContrVrsnNum_H1.compareTo(nvlBigDecimal(bizMsg.prcContrVrsnNum_H1.getValue())) != 0)
                || !glblMsg.prcContrRnwDt_H1.getValue().equals(bizMsg.prcContrRnwDt_H1.getValue())
                || (prcContrTermMthNum_H1.compareTo(nvlBigDecimal(bizMsg.prcContrTermMthNum_H1.getValue())) != 0)
                || !glblMsg.dsAcctNum_C1.getValue().equals(bizMsg.dsAcctNum_C1.getValue())
                || (initFdRate_C1.compareTo(nvlBigDecimal(bizMsg.initFdRate_C1.getValue())) != 0)) {
            return true;
        }
        return false;
    }

    private boolean checkNoChangedTrxChrgValue(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg, int index) {
        if (!ZYPCommonFunc.hasValue(glblMsg.D.no(index).prcContrTrxChrgPk_D1)) {
            return true;
        }

        BigDecimal prcContrTrxChrgPct_D1 = nvlBigDecimal(glblMsg.D.no(index).prcContrTrxChrgPct_D1.getValue());
        BigDecimal prcContrTrxChrgAmt_D1 = nvlBigDecimal(glblMsg.D.no(index).prcContrTrxChrgAmt_D1.getValue());

        if (!glblMsg.D.no(index).prcContrChrgTpCd_D1.getValue().equals(bizMsg.D.no(index).prcContrChrgTpCd_D1.getValue())
                || !glblMsg.D.no(index).mdseCd_D1.getValue().equals(bizMsg.D.no(index).mdseCd_D1.getValue())
                || (prcContrTrxChrgPct_D1.compareTo(nvlBigDecimal(bizMsg.D.no(index).prcContrTrxChrgPct_D1.getValue())) != 0)
                || (prcContrTrxChrgAmt_D1.compareTo(nvlBigDecimal(bizMsg.D.no(index).prcContrTrxChrgAmt_D1.getValue())) != 0)
                || !glblMsg.D.no(index).prcContrTrxChrgNm_D1.getValue().equals(bizMsg.D.no(index).prcContrTrxChrgNm_D1.getValue())
                || !glblMsg.D.no(index).xxRecNmTxt_D1.getValue().equals(bizMsg.D.no(index).xxRecNmTxt_D1.getValue())
                || !glblMsg.D.no(index).dsAcctNum_D1.getValue().equals(bizMsg.D.no(index).dsAcctNum_D1.getValue())
                || !glblMsg.D.no(index).dsAcctNm_D1.getValue().equals(bizMsg.D.no(index).dsAcctNm_D1.getValue())
                || !glblMsg.D.no(index).effFromDt_D1.getValue().equals(bizMsg.D.no(index).effFromDt_D1.getValue())
                || !glblMsg.D.no(index).effThruDt_D1.getValue().equals(bizMsg.D.no(index).effThruDt_D1.getValue())
                || !glblMsg.D.no(index).prcContrRebTpCd_D1.getValue().equals(bizMsg.D.no(index).prcContrRebTpCd_D1.getValue())
                || !glblMsg.D.no(index).appReqFlg_D1.getValue().equals(getFlg(bizMsg.D.no(index).appReqFlg_D1.getValue()))) {
            return true;
        }
        return false;
    }

    private boolean checkNoChangedTermCondValue(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {
        BigDecimal prcTermCondVrsnNum_E1 = nvlBigDecimal(glblMsg.prcTermCondVrsnNum_E1.getValue());
        BigDecimal prcContrTrxChrgAmt_D1 = nvlBigDecimal(glblMsg.ordTrxFlexPct_E1.getValue());
        BigDecimal tonerAlwncPct_E1 = nvlBigDecimal(glblMsg.tonerAlwncPct_E1.getValue());
        BigDecimal maxDownTmDaysAot_E1 = nvlBigDecimal(glblMsg.maxDownTmDaysAot_E1.getValue());
        BigDecimal lflReplTermNum_E1 = nvlBigDecimal(glblMsg.lflReplTermNum_E1.getValue());
        BigDecimal aftHourBillRate_E1 = nvlBigDecimal(glblMsg.aftHourBillRate_E1.getValue());
        BigDecimal svcEtaCallReqHrsNum_E1 = nvlBigDecimal(glblMsg.svcEtaCallReqHrsNum_E1.getValue());
        BigDecimal tonerYieldCnt_E1 = nvlBigDecimal(glblMsg.tonerYieldCnt_E1.getValue());
        BigDecimal maxRnwIncrAmtRate_E1 = nvlBigDecimal(glblMsg.maxRnwIncrAmtRate_E1.getValue());
        BigDecimal maxStdAnnIncrPct_E1 = nvlBigDecimal(glblMsg.maxStdAnnIncrPct_E1.getValue());
        BigDecimal upTmGtdPct_E1 = nvlBigDecimal(glblMsg.upTmGtdPct_E1.getValue());

        if ((prcTermCondVrsnNum_E1.compareTo(nvlBigDecimal(bizMsg.prcTermCondVrsnNum_E1.getValue()))) != 0
                || !glblMsg.prcTermCondStsCd_E1.getValue().equals(bizMsg.prcTermCondStsCd_E1.getValue())
                || (prcContrTrxChrgAmt_D1.compareTo(nvlBigDecimal(bizMsg.ordTrxFlexPct_E1.getValue())) != 0)
                || !glblMsg.allwDclnMaintFlg_E1.getValue().equals(getFlg(bizMsg.allwDclnMaintFlg_E1.getValue()))
                || !glblMsg.mustUseEquipPrcFlg_E1.getValue().equals(getFlg(bizMsg.mustUseEquipPrcFlg_E1.getValue()))
                || !glblMsg.leaseRtrnInclInPrcFlg_E1.getValue().equals(getFlg(bizMsg.leaseRtrnInclInPrcFlg_E1.getValue()))
                || !glblMsg.ovrdSysTonerTpFlg_E1.getValue().equals(getFlg(bizMsg.ovrdSysTonerTpFlg_E1.getValue()))
                || !glblMsg.billTonerFrtChrgFlg_E1.getValue().equals(getFlg(bizMsg.billTonerFrtChrgFlg_E1.getValue()))
                || (tonerAlwncPct_E1.compareTo(nvlBigDecimal(bizMsg.tonerAlwncPct_E1.getValue())) != 0)
                || !glblMsg.nonStdStartTm_E1.getValue().equals(bizMsg.nonStdStartTm_E1.getValue())
                || !glblMsg.lnrEttlFlg_E1.getValue().equals(getFlg(bizMsg.lnrEttlFlg_E1.getValue()))
                || (maxDownTmDaysAot_E1.compareTo(nvlBigDecimal(bizMsg.maxDownTmDaysAot_E1.getValue())) != 0)
                || !glblMsg.lflReplOptFlg_E1.getValue().equals(getFlg(bizMsg.lflReplOptFlg_E1.getValue()))
                || (lflReplTermNum_E1.compareTo(nvlBigDecimal(bizMsg.lflReplTermNum_E1.getValue())) != 0)
                || !glblMsg.unltdTngReqFlg_E1.getValue().equals(getFlg(bizMsg.unltdTngReqFlg_E1.getValue()))
                || !glblMsg.custPrvtyFlg_E1.getValue().equals(getFlg(bizMsg.custPrvtyFlg_E1.getValue()))
                || !glblMsg.hddSvcInclFlg_E1.getValue().equals(getFlg(bizMsg.hddSvcInclFlg_E1.getValue()))
                || !glblMsg.hddCleanPrcGtdFlg_E1.getValue().equals(getFlg(bizMsg.hddCleanPrcGtdFlg_E1.getValue()))
                || !glblMsg.tmAndMatUplftTxt_E1.getValue().equals(bizMsg.tmAndMatUplftTxt_E1.getValue())
                || !glblMsg.docReqFrmAgmtNm_E1.getValue().equals(bizMsg.docReqFrmAgmtNm_E1.getValue())
                || !glblMsg.mstrAgmtDocNm_E1.getValue().equals(bizMsg.mstrAgmtDocNm_E1.getValue())
                || !glblMsg.mstrReplAquFlg_E1.getValue().equals(getFlg(bizMsg.mstrReplAquFlg_E1.getValue()))
                || !glblMsg.mstrReplCmbnPrchFlg_E1.getValue().equals(getFlg(bizMsg.mstrReplCmbnPrchFlg_E1.getValue()))
                || !glblMsg.leaseTrxAllwFlg_E1.getValue().equals(getFlg(bizMsg.leaseTrxAllwFlg_E1.getValue()))
                || !glblMsg.supplTermCmpyStdFrmTxt_E1.getValue().equals(bizMsg.supplTermCmpyStdFrmTxt_E1.getValue())
                || (aftHourBillRate_E1.compareTo(nvlBigDecimal(bizMsg.aftHourBillRate_E1.getValue())) != 0)
                || !glblMsg.rspTmMeasPerCd_E1.getValue().equals(bizMsg.rspTmMeasPerCd_E1.getValue())
                || !glblMsg.rspTmComitTxt_E1.getValue().equals(bizMsg.rspTmComitTxt_E1.getValue())
                || (svcEtaCallReqHrsNum_E1.compareTo(nvlBigDecimal(bizMsg.svcEtaCallReqHrsNum_E1.getValue())) != 0)
                || !glblMsg.tonerTpNm_E1.getValue().equals(bizMsg.tonerTpNm_E1.getValue())
                || (tonerYieldCnt_E1.compareTo(nvlBigDecimal(bizMsg.tonerYieldCnt_E1.getValue())) != 0)
                || !glblMsg.stplInclSvcFlg_E1.getValue().equals(getFlg(bizMsg.stplInclSvcFlg_E1.getValue()))
                || !glblMsg.prcContrPrcTpCd_E1.getValue().equals(bizMsg.prcContrPrcTpCd_E1.getValue())
                || !glblMsg.dlyFirstCallGtdFlg_E1.getValue().equals(getFlg(bizMsg.dlyFirstCallGtdFlg_E1.getValue()))
                || !glblMsg.onSiteTechInclFlg_E1.getValue().equals(getFlg(bizMsg.onSiteTechInclFlg_E1.getValue()))
                || !glblMsg.primTechInclFlg_E1.getValue().equals(getFlg(bizMsg.primTechInclFlg_E1.getValue()))
                || !glblMsg.iwrEsclFlg_E1.getValue().equals(getFlg(bizMsg.iwrEsclFlg_E1.getValue()))
                || (maxRnwIncrAmtRate_E1.compareTo(nvlBigDecimal(bizMsg.maxRnwIncrAmtRate_E1.getValue())) != 0)
                || (maxStdAnnIncrPct_E1.compareTo(nvlBigDecimal(bizMsg.maxStdAnnIncrPct_E1.getValue())) != 0)
                || !glblMsg.erlTrmnOptFlg_E1.getValue().equals(getFlg(bizMsg.erlTrmnOptFlg_E1.getValue()))
                || (upTmGtdPct_E1.compareTo(nvlBigDecimal(bizMsg.upTmGtdPct_E1.getValue())) != 0)
                || !glblMsg.fleetContrAllwFlg_E1.getValue().equals(getFlg(bizMsg.fleetContrAllwFlg_E1.getValue()))
                || !glblMsg.aggrContrAllwFlg_E1.getValue().equals(getFlg(bizMsg.aggrContrAllwFlg_E1.getValue()))
                || !glblMsg.custQtlyBizRvwReqFlg_E1.getValue().equals(getFlg(bizMsg.custQtlyBizRvwReqFlg_E1.getValue()))
                || !glblMsg.stdQtlyBizRvwReqFlg_E1.getValue().equals(getFlg(bizMsg.stdQtlyBizRvwReqFlg_E1.getValue()))
                || !glblMsg.reqRptIntvlCd_E1.getValue().equals(bizMsg.reqRptIntvlCd_E1.getValue())) {
            return true;
        }
        return false;
    }
    
    private static BigDecimal nvlBigDecimal(BigDecimal arg) {

        if (ZYPCommonFunc.hasValue(arg)) {
            return arg;
        }
        return BigDecimal.ZERO;
        
    }
}
