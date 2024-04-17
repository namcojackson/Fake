/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0350;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import business.blap.NSAL0350.common.NSAL0350CommonLogic;
import business.blap.NSAL0350.constant.NSAL0350Constant;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.SKIP_RECOV_TPTMsg;
import business.db.SKIP_RECOV_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC2325
 * 2016/08/09   Hitachi         T.Aoyagi        Update          QC4659
 * 2016/11/02   Hitachi         K.Ochiai        Update          QC15232
 * 2018/05/17   Fujitsu         H.Nagashima     Update          QC#25405
 *</pre>
 */
public class NSAL0350BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NSAL0350CMsg bizMsg = (NSAL0350CMsg) cMsg;
        NSAL0350SMsg sharedMsg = (NSAL0350SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0350Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0350Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NSAL0350Scrn00_CMN_Submit <dd>The method
     * explanation: submit button event
     * @param bizMsg NSAL0350CMsg
     * @param sharedMsg NSAL0350SMsg
     */
    private void doProcess_NSAL0350Scrn00_CMN_Submit(NSAL0350CMsg bizMsg, NSAL0350SMsg sharedMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (!inputCheck(glblCmpyCd, bizMsg)) {
            return;
        }

        if (!updateScheduleInfo(bizMsg, sharedMsg, glblCmpyCd)) {
            return;
        }
    }

    private boolean updateScheduleInfo(NSAL0350CMsg bizMsg, NSAL0350SMsg sharedMsg, String glblCmpyCd) {

        if (!NSAL0350CommonLogic.isChangedSchedules(bizMsg, sharedMsg)) {
            return true;
        }

        String[] tblNms = null;
        String svcInvChrgTpCd = sharedMsg.svcInvChrgTpCd_H1.getValue();
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            tblNms = new String[1];
            tblNms[0] = NSAL0350Constant.BASE_TBL_NM;

        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
            tblNms = NSAL0350Constant.USAGE_TBL_NM_ARRAY;
        }

        SKIP_RECOV_TPTMsgArray skipRecovTpArray = NSAL0350CommonLogic.getSkipRecovTpArray(glblCmpyCd);

        List<DS_CONTR_BLLG_SCHDTMsg> updateList = new ArrayList<DS_CONTR_BLLG_SCHDTMsg>();
        for (String tblNm : tblNms) {
            EZDMsgArray cTblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(bizMsg, tblNm);
            EZDMsgArray sTblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(sharedMsg, tblNm);
            int cnt = cTblArray.getValidCount();
            updateList.clear();
            // START 2016/11/02 K.Ochiai [QC#15232, ADD]
            if (cnt <= 0) {
                continue;
            }
            EZDMsg wkEsdMsg = cTblArray.get(cnt - 1);
            String setNextBllgDt = NSAL0350CommonLogic.getDateValueFromEZDMsg(wkEsdMsg, tblNm, "rvsSchdDt_A1").getValue();
            if (!ZYPCommonFunc.hasValue(setNextBllgDt)) {
                setNextBllgDt = NSAL0350CommonLogic.getDateValueFromEZDMsg(wkEsdMsg, tblNm, "nextBllgDt_A2").getValue();
            }
            for (int k = cnt -1 ; k > 0; k--) {
                EZDMsg cEsdMsg = cTblArray.get(k);
                EZDCStringItem skipRecovTpCd_C = NSAL0350CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "skipRecovTpCd_A3");
                SKIP_RECOV_TPTMsg skipRecovTpTMsg = getSKIP_RECOV_TPTMsgFromArray(skipRecovTpArray, skipRecovTpCd_C.getValue());
                EZDCDateItem nextBllgDt = NSAL0350CommonLogic.getDateValueFromEZDMsg(cEsdMsg, tblNm, "nextBllgDt_A2");
                EZDCDateItem rvsSchdDt = NSAL0350CommonLogic.getDateValueFromEZDMsg(cEsdMsg, tblNm, "rvsSchdDt_A1");
                if (ZYPConstant.FLG_ON_Y.equals(skipRecovTpTMsg.bllgRecovFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(rvsSchdDt, setNextBllgDt);
                } else {
                    rvsSchdDt.clear();
                    if (SKIP_RECOV_TP.NONE.equals(skipRecovTpCd_C.getValue())) {
                        setNextBllgDt = nextBllgDt.getValue();
                    }
                }
            }
            // END 2016/11/02 K.Ochiai [QC#15232, ADD]
            for (int i = 0; i < cnt; i++) {
                EZDMsg cEsdMsg = cTblArray.get(i);
                // DEL 2016/11/02 K.Ochiai [QC#15232, DEL]
//                EZDMsg sEsdMsg = sTblArray.get(i);
//                if (!NSAL0350CommonLogic.isChanged(tblNm, cEsdMsg, sEsdMsg)) {
//                    continue;
//                }
                // DEL 2016/11/02 K.Ochiai [QC#15232, DEL]
                EZDCBigDecimalItem dsContrBllgSchdPk = NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "dsContrBllgSchdPk_A1");
                EZDCBigDecimalItem dsContrBllgSchdSmryPk = NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(cEsdMsg, tblNm, "dsContrBllgSchdSmryPk_A1");
                EZDCStringItem ezUpTime = NSAL0350CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "ezUpTime_A1");
                EZDCStringItem ezUpTimeZone = NSAL0350CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "ezUpTimeZone_A1");

                DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = NSAL0350CommonLogic.findDsContrBllgSchdForUpdate(glblCmpyCd, dsContrBllgSchdPk.getValue(), dsContrBllgSchdSmryPk.getValue(), bizMsg.dsContrDtlPk_H1.getValue());
                if (dsContrBllgSchdTMsg == null) {
                    String[] args = {"DS_CONTR_BLLG_SCHD" };
                    bizMsg.setMessageInfo(NSAL0350Constant.NSAM0045E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(ezUpTime.getValue(), ezUpTimeZone.getValue(), dsContrBllgSchdTMsg.ezUpTime.getValue(), dsContrBllgSchdTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NSAL0350Constant.NZZM0003E);
                    return false;
                }

                EZDCStringItem skipRecovTpCd_C = NSAL0350CommonLogic.getStringValueFromEZDMsg(cEsdMsg, tblNm, "skipRecovTpCd_A3");
                ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.skipRecovTpCd, skipRecovTpCd_C);

                SKIP_RECOV_TPTMsg skipRecovTpTMsg = getSKIP_RECOV_TPTMsgFromArray(skipRecovTpArray, skipRecovTpCd_C.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(skipRecovTpTMsg.bllgSkipFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.bllblFlg, ZYPConstant.FLG_OFF_N);
                    // START 2016/02/15 T.Aoyagi [QC2325, ADD]
                    // START 2016/08/09 T.Aoyagi [QC4959, DEL]
//                    ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
                    // END 2016/08/09 T.Aoyagi [QC4959, DEL]
                    // END 2016/02/15 T.Aoyagi [QC2325, ADD]
                } else {
                    ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.bllblFlg, ZYPConstant.FLG_ON_Y);
                }
                if (ZYPConstant.FLG_ON_Y.equals(skipRecovTpTMsg.bllgRecovFlg.getValue())) {
                    // START 2016/11/02 K.Ochiai [Q#C15232, MOD]
//                  if (i + 1 < cnt) {
                      EZDMsg nextCEsdMsg = cTblArray.get(i);
                      EZDCDateItem nextBllgDt = NSAL0350CommonLogic.getDateValueFromEZDMsg(nextCEsdMsg, tblNm, "rvsSchdDt_A1");
                      ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTMsg.rvsSchdDt, nextBllgDt);
//                  }
                    // END 2016/11/02 K.Ochiai [QC#15232, MOD]
                } else {
                    dsContrBllgSchdTMsg.rvsSchdDt.clear();
                }

                updateList.add(dsContrBllgSchdTMsg);
            }
            if (updateList.size() > 0) {
                int updateCnt = S21FastTBLAccessor.update(updateList.toArray(new DS_CONTR_BLLG_SCHDTMsg[0]));
                if (updateCnt != updateList.size()) {
                    bizMsg.setMessageInfo(NSAL0350Constant.NSAM0001E, new String[] {"DS_CONTR_BLLG_SCHD", "tblNm=" + tblNm });
                    return false;
                }
            }

        }
        return true;
    }

    private boolean inputCheck(String glblCmpyCd, NSAL0350CMsg bizMsg) {

        boolean result = true;
        String[] tblNms = null;
        String svcInvChrgTpCd = bizMsg.svcInvChrgTpCd_H1.getValue();
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            tblNms = new String[1];
            tblNms[0] = NSAL0350Constant.BASE_TBL_NM;

        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
            tblNms = NSAL0350Constant.USAGE_TBL_NM_ARRAY;
        }

        SKIP_RECOV_TPTMsgArray skipRecovTpArray = NSAL0350CommonLogic.getSkipRecovTpByRecovFlg(glblCmpyCd, ZYPConstant.FLG_ON_Y);
        for (String tblNm : tblNms) {
            if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
                EZDCBigDecimalItem dsContrBllgSchdPk = NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(bizMsg, tblNm, "dsContrBllgMtrPk_A");
                if (!ZYPCommonFunc.hasValue(dsContrBllgSchdPk)) {
                    continue;
                }
            }

            EZDMsgArray tblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(bizMsg, tblNm);
            //QC#25405 add Start
            if (tblArray.getValidCount() == 0) {
                continue;
            }
            //QC#25405 add End
            EZDMsg lastEzdMsg = tblArray.get(tblArray.getValidCount() - 1);
            EZDCStringItem skipRecovTpCd = NSAL0350CommonLogic.getStringValueFromEZDMsg(lastEzdMsg, tblNm, "skipRecovTpCd_A3");
            if (isRecovFlagY(skipRecovTpArray, skipRecovTpCd.getValue())) {
                skipRecovTpCd.setErrorInfo(1, NSAL0350Constant.NSAM0355E);
                result = false;
            }
        }
        return result;
    }

    private boolean isRecovFlagY(SKIP_RECOV_TPTMsgArray skipRecovTpArray, String skipRecovTpCd) {
        SKIP_RECOV_TPTMsg tMsg = getSKIP_RECOV_TPTMsgFromArray(skipRecovTpArray, skipRecovTpCd);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private SKIP_RECOV_TPTMsg getSKIP_RECOV_TPTMsgFromArray(SKIP_RECOV_TPTMsgArray skipRecovTpArray, String skipRecovTpCd) {
        SKIP_RECOV_TPTMsg result = null;
        int size = skipRecovTpArray.length();
        for (int i = 0; i < size; i++) {
            SKIP_RECOV_TPTMsg tMsg = skipRecovTpArray.no(i);
            if (tMsg.skipRecovTpCd.getValue().equals(skipRecovTpCd)) {
                result = tMsg;
                break;
            }
        }
        return result;
    }
}
