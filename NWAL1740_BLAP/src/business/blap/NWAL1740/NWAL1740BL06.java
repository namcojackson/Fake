/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1740;

import static business.blap.NWAL1740.constant.NWAL1740Constant.DB_FLAG_DELETE;
import static business.blap.NWAL1740.constant.NWAL1740Constant.DB_FLAG_INSERT;
import static business.blap.NWAL1740.constant.NWAL1740Constant.NWAM8448E;
import static business.blap.NWAL1740.constant.NWAL1740Constant.NWAM8449E;
import static business.blap.NWAL1740.constant.NWAL1740Constant.NWAM8450E;
import static business.blap.NWAL1740.constant.NWAL1740Constant.NWAM8451E;
import static business.blap.NWAL1740.constant.NWAL1740Constant.NZZM0003E;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ORD_PRFT_LINE_CATG_RULETMsg;
import business.db.ORD_PRFT_MDSE_TP_RULETMsg;
import business.db.ORD_PRFT_NODE_PRFLTMsg;
import business.db.ORD_PRFT_WH_RULETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1740BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/15   Fujitsu         M.Suzuki        Update          QC#6288
 *</pre>
 */
public class NWAL1740BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1740CMsg bizMsg = (NWAL1740CMsg) cMsg;
            NWAL1740SMsg glblMsg = (NWAL1740SMsg) sMsg;

            if ("NWAL1740Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1740Scrn00_CMN_Submit(bizMsg, glblMsg);

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
    private void doProcess_NWAL1740Scrn00_CMN_Submit(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.ordProcNodePrflCd_H)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ordProcNodePrflCd_H, bizMsg.ordProcNodePrflCd);
        }

        ORD_PRFT_NODE_PRFLTMsg tmsg = new ORD_PRFT_NODE_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
        tmsg = (ORD_PRFT_NODE_PRFLTMsg) EZDTBLAccessor.findByKey(tmsg);
        String ordPrfRuletpCd = tmsg.ordPrftRuleTpCd.getValue();

        boolean errFlag = false;
        errFlag = checkExitMdse(bizMsg, errFlag);
        errFlag = checkExistLine(bizMsg, errFlag);
        errFlag = checkExistWH(bizMsg, errFlag);

        if (errFlag) {
            return;
        }

        if (!updateMdse(bizMsg, ordPrfRuletpCd, glblMsg)) {
            return;
        }

        if (!updateLine(bizMsg, glblMsg)) {
            return;
        }

        if (!updateWH(bizMsg, glblMsg)) {
            return;
        }
    }

    private boolean updateWH(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            String bizRowId = bizMsg.C.no(i).xxRowId_C.getValue();
            if (DB_FLAG_INSERT.equals(bizMsg.C.no(i).xxRowId_C.getValue())) {
                ORD_PRFT_WH_RULETMsg whrulesTmsg = new ORD_PRFT_WH_RULETMsg();
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlWhCd, bizMsg.C.no(i).rtlWhCd_C);
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlSwhCd, bizMsg.C.no(i).rtlSwhCd_C);
                setScreenValueWH(bizMsg, i, whrulesTmsg);
                EZDTBLAccessor.create(whrulesTmsg);
                if (!RTNCD_NORMAL.equals(whrulesTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"ORD_PRFT_WH_RULE" });
                    return false;
                }
                continue;
            }

            for (int j = 0; j < glblMsg.C.getValidCount(); j++) {
                String glblRowId = glblMsg.C.no(j).xxRowId_C.getValue();

                if (glblRowId.equals(bizRowId)) {
                    if (isUpdateWH(bizMsg, glblMsg, i, j)) { //2016/04/15 S21_NA#6288 MOD
                        ORD_PRFT_WH_RULETMsg whrulesTmsg = new ORD_PRFT_WH_RULETMsg();
                        ZYPEZDItemValueSetter.setValue(whrulesTmsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(whrulesTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                        ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlWhCd, bizMsg.C.no(i).rtlWhCd_C);
                        ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlSwhCd, bizMsg.C.no(i).rtlSwhCd_C);
                        whrulesTmsg = (ORD_PRFT_WH_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(whrulesTmsg);
                        //2016/04/21 S21_NA#6288 MOD End ------------
                        if (whrulesTmsg == null) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                        //2016/04/21 S21_NA#6288 MOD End ------------
                        if (!ZYPDateUtil.isSameTimeStamp(glblMsg.C.no(j).ezUpTime_C.getValue(), glblMsg.C.no(j).ezUpTimeZone_C.getValue(), whrulesTmsg.ezUpTime.getValue(), whrulesTmsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                        setScreenValueWH(bizMsg, i, whrulesTmsg);
                        EZDTBLAccessor.update(whrulesTmsg);
                        if (!RTNCD_NORMAL.equals(whrulesTmsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NWAM8448E, new String[] {"ORD_PRFT_WH_RULE" });
                            return false;
                        }
                    }
                }
            }
        }

        for (int k = 0; k < glblMsg.C.getValidCount(); k++) {
            String glblRowId = glblMsg.C.no(k).xxRowId_C.getValue();
            if (DB_FLAG_DELETE.equals(glblRowId)) {
                ORD_PRFT_WH_RULETMsg whrulesTmsg = new ORD_PRFT_WH_RULETMsg();
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlWhCd, glblMsg.C.no(k).rtlWhCd_C);
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlSwhCd, glblMsg.C.no(k).rtlSwhCd_C);
                whrulesTmsg = (ORD_PRFT_WH_RULETMsg) EZDTBLAccessor.findByKey(whrulesTmsg);

                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.C.no(k).ezUpTime_C.getValue(), glblMsg.C.no(k).ezUpTimeZone_C.getValue(), whrulesTmsg.ezUpTime.getValue(), whrulesTmsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDTBLAccessor.logicalRemove(whrulesTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(whrulesTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8449E);
                    return false;
                }
            }

        }

        return true;
    }

    private void setScreenValueWH(NWAL1740CMsg bizMsg, int i, ORD_PRFT_WH_RULETMsg whrulesTmsg) {
        ZYPEZDItemValueSetter.setValue(whrulesTmsg.effFromDt, bizMsg.C.no(i).effFromDt_C);
        ZYPEZDItemValueSetter.setValue(whrulesTmsg.effThruDt, bizMsg.C.no(i).effThruDt_C);

        if (ZYPCommonFunc.hasValue(bizMsg.C.no(i).actvFlg_C)) {
            ZYPEZDItemValueSetter.setValue(whrulesTmsg.actvFlg, bizMsg.C.no(i).actvFlg_C);
        } else {
            ZYPEZDItemValueSetter.setValue(whrulesTmsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    //2016/04/15 S21_NA#6288 MOD Start --------------
    private boolean isUpdateWH(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg, int i, int j) {
        boolean checkFlag = false;
        if (!glblMsg.C.no(j).effFromDt_C.getValue().equals(bizMsg.C.no(i).effFromDt_C.getValue())) {
            checkFlag = true;
        }

        if (!glblMsg.C.no(j).effThruDt_C.getValue().equals(bizMsg.C.no(i).effThruDt_C.getValue())) {
            checkFlag = true;
        }

        if (!glblMsg.C.no(j).actvFlg_C.getValue().equals((bizMsg.C.no(i).actvFlg_C.getValue()))) {
            checkFlag = true;
        }

        return checkFlag;
    }
    //2016/04/15 S21_NA#6288 MOD End --------------

    private boolean updateLine(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            String bizRowId = bizMsg.B.no(i).xxRowId_B.getValue();
            if (DB_FLAG_INSERT.equals(bizRowId)) {
                ORD_PRFT_LINE_CATG_RULETMsg lineCatgRuleTmsg = new ORD_PRFT_LINE_CATG_RULETMsg();
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.dsOrdLineCatgCd, bizMsg.B.no(i).dsOrdLineCatgCd_B);
                setScreenValueLine(bizMsg, i, lineCatgRuleTmsg);
                EZDTBLAccessor.create(lineCatgRuleTmsg);
                if (!RTNCD_NORMAL.equals(lineCatgRuleTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"ORD_PRFT_LINE_CATG_RULE" });
                    return false;
                }
                continue;
            }

            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                String glblRowId = glblMsg.B.no(j).xxRowId_B.getValue();

                if (glblRowId.equals(bizRowId)) {
                    if (isUpdateLine(bizMsg, glblMsg, i, j)) {
                        ORD_PRFT_LINE_CATG_RULETMsg lineCatgRuleTmsg = new ORD_PRFT_LINE_CATG_RULETMsg();
                        ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                        ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.dsOrdLineCatgCd, bizMsg.B.no(i).dsOrdLineCatgCd_B);
                        lineCatgRuleTmsg = (ORD_PRFT_LINE_CATG_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(lineCatgRuleTmsg);
                        //2016/04/21 S21_NA#6288 MOD End ------------
                        if (lineCatgRuleTmsg == null) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                        //2016/04/21 S21_NA#6288 MOD End ------------
                        if (!ZYPDateUtil.isSameTimeStamp(glblMsg.B.no(j).ezUpTime_B.getValue(), glblMsg.B.no(j).ezUpTimeZone_B.getValue(), lineCatgRuleTmsg.ezUpTime.getValue(), lineCatgRuleTmsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                        setScreenValueLine(bizMsg, i, lineCatgRuleTmsg);
                        EZDTBLAccessor.update(lineCatgRuleTmsg);
                        if (!RTNCD_NORMAL.equals(lineCatgRuleTmsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NWAM8448E, new String[] {"ORD_PRFT_LINE_CATG_RULE" });
                            return false;
                        }
                    }
                }
            }
        }

        for (int k = 0; k < glblMsg.B.getValidCount(); k++) {
            String glblRowId = glblMsg.B.no(k).xxRowId_B.getValue();
            if (DB_FLAG_DELETE.equals(glblRowId)) {

                ORD_PRFT_LINE_CATG_RULETMsg lineCatgRuleTmsg = new ORD_PRFT_LINE_CATG_RULETMsg();
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.dsOrdLineCatgCd, glblMsg.B.no(k).dsOrdLineCatgCd_B);
                lineCatgRuleTmsg = (ORD_PRFT_LINE_CATG_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(lineCatgRuleTmsg);
                if (!ZYPDateUtil.isSameTimeStamp(glblMsg.B.no(k).ezUpTime_B.getValue(), glblMsg.B.no(k).ezUpTimeZone_B.getValue(), lineCatgRuleTmsg.ezUpTime.getValue(), lineCatgRuleTmsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDTBLAccessor.logicalRemove(lineCatgRuleTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(lineCatgRuleTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8449E);
                    return false;
                }
            }
        }
        return true;
    }

    private void setScreenValueLine(NWAL1740CMsg bizMsg, int i, ORD_PRFT_LINE_CATG_RULETMsg lineCatgRuleTmsg) {
        ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.effFromDt, bizMsg.B.no(i).effFromDt_B);
        ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.effThruDt, bizMsg.B.no(i).effThruDt_B);
        if (ZYPCommonFunc.hasValue(bizMsg.B.no(i).actvFlg_B)) {
            ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.actvFlg, bizMsg.B.no(i).actvFlg_B);
        } else {
            ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.actvFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    private boolean isUpdateLine(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg, int i, int j) {

        boolean checkFlag = false;
        if (!glblMsg.B.no(j).effFromDt_B.getValue().equals(bizMsg.B.no(i).effFromDt_B.getValue())) {
            checkFlag = true;
        }
        if (!glblMsg.B.no(j).effThruDt_B.getValue().equals(bizMsg.B.no(i).effThruDt_B.getValue())) {
            checkFlag = true;
        }
        if (!glblMsg.B.no(j).actvFlg_B.getValue().equals(bizMsg.B.no(i).actvFlg_B.getValue())) {
            checkFlag = true;
        }

        return checkFlag;
    }

    private boolean updateMdse(NWAL1740CMsg bizMsg, String ordPrfRuletpCd, NWAL1740SMsg glblMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String bizRowId = bizMsg.A.no(i).xxRowId_A.getValue();
            if (DB_FLAG_INSERT.equals(bizRowId)) {
                ORD_PRFT_MDSE_TP_RULETMsg mdseRuleTmsg = new ORD_PRFT_MDSE_TP_RULETMsg();
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.coaMdseTpCd, bizMsg.A.no(i).coaMdseTpCd_A);
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.ordPrftRuleTpCd, ordPrfRuletpCd);

                setMdseScreenValue(bizMsg, i, mdseRuleTmsg);
                EZDTBLAccessor.create(mdseRuleTmsg);
                if (!RTNCD_NORMAL.equals(mdseRuleTmsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8448E, new String[] {"ORD_PRFT_MDSE_TP_RULE" });
                    return false;
                }
                continue;
            }

            for (int j = 0; j < glblMsg.A.getValidCount(); j++) {
                String glblRowId = glblMsg.A.no(j).xxRowId_A.getValue();

                if (glblRowId.equals(bizRowId)) {
                    if (isUpdateMdse(bizMsg, glblMsg, i, j)) {

                        ORD_PRFT_MDSE_TP_RULETMsg tMsg = new ORD_PRFT_MDSE_TP_RULETMsg();
                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                        ZYPEZDItemValueSetter.setValue(tMsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                        ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, bizMsg.A.no(i).coaMdseTpCd_A);
                        tMsg = (ORD_PRFT_MDSE_TP_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                        //2016/04/21 S21_NA#6288 MOD End ------------
                        if (tMsg == null) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                        //2016/04/21 S21_NA#6288 MOD End ------------
                        if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(j).ezUpTime_A.getValue(), glblMsg.A.no(j).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            bizMsg.setMessageInfo(NZZM0003E);
                            return false;
                        }
                        setMdseScreenValue(bizMsg, i, tMsg);
                        EZDTBLAccessor.update(tMsg);
                        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NWAM8448E, new String[] {"ORD_PRFT_MDSE_TP_RULE" });
                            return false;
                        }

                    }
                }
            }
        }

        for (int k = 0; k < glblMsg.A.getValidCount(); k++) {
            String glblRowId = glblMsg.A.no(k).xxRowId_A.getValue();
            if (DB_FLAG_DELETE.equals(glblRowId)) {
                ORD_PRFT_MDSE_TP_RULETMsg tMsg = new ORD_PRFT_MDSE_TP_RULETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(tMsg.coaMdseTpCd, glblMsg.A.no(k).coaMdseTpCd_A);
                tMsg = (ORD_PRFT_MDSE_TP_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                } else if (!ZYPDateUtil.isSameTimeStamp(glblMsg.A.no(k).ezUpTime_A.getValue(), glblMsg.A.no(k).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDTBLAccessor.logicalRemove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NWAM8449E);
                    return false;
                }

            }
        }
        return true;
    }

    private void setMdseScreenValue(NWAL1740CMsg bizMsg, int i, ORD_PRFT_MDSE_TP_RULETMsg mdseRuleTmsg) {
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.flPrcCalcInclFlg, bizMsg.A.no(i).flPrcCalcInclFlg_A);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.repRevCalcInclFlg, bizMsg.A.no(i).repRevCalcInclFlg_AR);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.discMdseTpFlg, bizMsg.A.no(i).repRevCalcInclFlg_AD);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.redFlPrcFlg, bizMsg.A.no(i).repRevCalcInclFlg_AF);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.redRepRevFlg, bizMsg.A.no(i).redRepRevFlg_A);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.discAllocMethCd, bizMsg.A.no(i).discAllocMethDescTxt_A);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.dlrCrPrftInclFlg, bizMsg.A.no(i).dlrCrPrftInclFlg_A);
        ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.redCompAmtFlg, bizMsg.A.no(i).redCompAmtFlg_A);
    }

    private boolean isUpdateMdse(NWAL1740CMsg bizMsg, NWAL1740SMsg glblMsg, int i, int j) {

        boolean upFlag = false;
        if (!glblMsg.A.no(j).flPrcCalcInclFlg_A.getValue().equals(bizMsg.A.no(i).flPrcCalcInclFlg_A.getValue())) {
            upFlag = true;
        }
        if (!glblMsg.A.no(j).repRevCalcInclFlg_AR.getValue().equals(bizMsg.A.no(i).repRevCalcInclFlg_AR.getValue())) {
            upFlag = true;
        }
        if (!glblMsg.A.no(j).repRevCalcInclFlg_AD.getValue().equals(bizMsg.A.no(i).repRevCalcInclFlg_AD.getValue())) {
            upFlag = true;
        }
        if (!glblMsg.A.no(j).repRevCalcInclFlg_AF.getValue().equals(bizMsg.A.no(i).repRevCalcInclFlg_AF.getValue())) {
            upFlag = true;
        }

        //2016/04/15 S21_NA#6288 MOD Start --------------
        if (!glblMsg.A.no(j).redRepRevFlg_A.getValue().equals(bizMsg.A.no(i).redRepRevFlg_A.getValue())) {
            upFlag = true;
        }
        if (!glblMsg.A.no(j).discAllocMethDescTxt_A.getValue().equals(bizMsg.A.no(i).discAllocMethDescTxt_A.getValue())) {
            upFlag = true;
        }
        if (!glblMsg.A.no(j).dlrCrPrftInclFlg_A.getValue().equals(bizMsg.A.no(i).dlrCrPrftInclFlg_A.getValue())) {
            upFlag = true;
        }
        if (!glblMsg.A.no(j).redCompAmtFlg_A.getValue().equals(bizMsg.A.no(i).redCompAmtFlg_A.getValue())) {
            upFlag = true;
        }
        //2016/04/15 S21_NA#6288 MOD end --------------

        return upFlag;
    }

    private boolean checkExistWH(NWAL1740CMsg bizMsg, boolean errFlag) {
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (DB_FLAG_INSERT.equals(bizMsg.C.no(i).xxRowId_C.getValue())) {
                ORD_PRFT_WH_RULETMsg whrulesTmsg = new ORD_PRFT_WH_RULETMsg();
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlWhCd, bizMsg.C.no(i).rtlWhCd_C);
                ZYPEZDItemValueSetter.setValue(whrulesTmsg.rtlSwhCd, bizMsg.C.no(i).rtlSwhCd_C);
                whrulesTmsg = (ORD_PRFT_WH_RULETMsg) EZDTBLAccessor.findByKey(whrulesTmsg);
                if (whrulesTmsg != null) {
                    bizMsg.C.no(i).rtlWhNm_C.setErrorInfo(1, NWAM8451E, new String[] {"Warehouse", bizMsg.C.no(i).rtlWhNm_C.getValue() });
                    bizMsg.C.no(i).rtlSwhNm_C.setErrorInfo(1, NWAM8451E, new String[] {"Sub Warehouse", bizMsg.C.no(i).rtlSwhNm_C.getValue() });
                    errFlag = true;
                }
            }
        }
        return errFlag;
    }

    private boolean checkExistLine(NWAL1740CMsg bizMsg, boolean errFlag) {
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (DB_FLAG_INSERT.equals(bizMsg.B.no(i).xxRowId_B.getValue())) {
                ORD_PRFT_LINE_CATG_RULETMsg lineCatgRuleTmsg = new ORD_PRFT_LINE_CATG_RULETMsg();
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(lineCatgRuleTmsg.dsOrdLineCatgCd, bizMsg.B.no(i).dsOrdLineCatgCd_B);
                lineCatgRuleTmsg = (ORD_PRFT_LINE_CATG_RULETMsg) EZDTBLAccessor.findByKey(lineCatgRuleTmsg);
                if (lineCatgRuleTmsg != null) {
                    bizMsg.B.no(i).dsOrdLineCatgNm_B.setErrorInfo(1, NWAM8450E, new String[] {"Line Category", bizMsg.B.no(i).dsOrdLineCatgCd_B.getValue() });
                    errFlag = true;
                }
            }
        }
        return errFlag;
    }

    private boolean checkExitMdse(NWAL1740CMsg bizMsg, boolean errFlag) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (DB_FLAG_INSERT.equals(bizMsg.A.no(i).xxRowId_A.getValue())) {
                ORD_PRFT_MDSE_TP_RULETMsg mdseRuleTmsg = new ORD_PRFT_MDSE_TP_RULETMsg();
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.ordProcNodePrflCd, bizMsg.ordProcNodePrflCd_H);
                ZYPEZDItemValueSetter.setValue(mdseRuleTmsg.coaMdseTpCd, bizMsg.A.no(i).coaMdseTpCd_A);
                mdseRuleTmsg = (ORD_PRFT_MDSE_TP_RULETMsg) EZDTBLAccessor.findByKey(mdseRuleTmsg);
                if (mdseRuleTmsg != null) {
                    bizMsg.A.no(i).coaMdseTpCd_A.setErrorInfo(1, NWAM8450E, new String[] {"Merchandise Type", bizMsg.A.no(i).coaMdseTpCd_A.getValue() });
                    errFlag = true;
                }
            }
        }
        return errFlag;
    }

}
