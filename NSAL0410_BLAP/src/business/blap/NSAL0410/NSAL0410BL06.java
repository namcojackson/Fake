/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0410;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0410.common.NSAL0410CommonLogic;
import business.blap.NSAL0410.constant.NSAL0410Constant;
import business.db.DS_CONTR_ADDL_CHRGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2016/02/16   Hitachi         K.Kasai         Update          QC#3021
 * 2017/12/26   Hitachi         K.Kojima        Update          QC#18562
 * 2018/05/24   Hitachi         K.Kitachi       Update          QC#26223
 *</pre>
 */
public class NSAL0410BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_CMN_Submit((NSAL0410CMsg) cMsg);
            } else if ("NSAL0410Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSAL0410Scrn00_CMN_Delete((NSAL0410CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * NSAL0410Scrn00_Submit
     */
    private void doProcess_NSAL0410Scrn00_CMN_Submit(NSAL0410CMsg cMsg) {

        if (!NSAL0410CommonLogic.inputCheckNoError(cMsg, getGlobalCompanyCode())) {
            return;
        }

        // create insert / update object
        List<DS_CONTR_ADDL_CHRGTMsg> insList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>(cMsg.A.getValidCount());
        List<DS_CONTR_ADDL_CHRGTMsg> updList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>(cMsg.A.getValidCount());

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0410_ACMsg acMsg = cMsg.A.no(i);

            if (ZYPCommonFunc.hasValue(acMsg.dsContrAddlChrgPk_A)) {
                DS_CONTR_ADDL_CHRGTMsg updTMsg = new DS_CONTR_ADDL_CHRGTMsg();
                updTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                updTMsg.dsContrAddlChrgPk.setValue(acMsg.dsContrAddlChrgPk_A.getValue());

                updTMsg = (DS_CONTR_ADDL_CHRGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
                if (updTMsg == null) {
                    cMsg.setMessageInfo(NSAL0410Constant.NZZM0003E);
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NSAL0410Constant.NZZM0003E);
                    return;
                }

                if (acMsg.trmnDt_A.getValue().equals(updTMsg.trmnDt.getValue()) || acMsg.svcBillByTpCd_SE.getValue().equals(updTMsg.svcBillByTpCd.getValue())
                        || acMsg.addlChrgFlatDealPrcAmt_A.getValue().equals(updTMsg.addlChrgFlatDealPrcAmt.getValue()) || acMsg.addlChrgAplcPct_A.getValue().equals(updTMsg.addlChrgAplcPct.getValue())
                        || acMsg.addlChrgInvTpCd_SE.getValue().equals(updTMsg.addlChrgInvTpCd.getValue()) || acMsg.printDtlFlg_A.getValue().equals(updTMsg.printDtlFlg.getValue())) {
                    setUpdRegistDataToDS_CONTR_ADDL_CHRGTMsg(cMsg, acMsg, updTMsg);
                    updList.add(updTMsg);
                }

            } else {

                DS_CONTR_ADDL_CHRGTMsg insTMsg = new DS_CONTR_ADDL_CHRGTMsg();
                setInsRegistDataToDS_CONTR_ADDL_CHRGTMsg(cMsg, acMsg, insTMsg);
                insList.add(insTMsg);
            }

        }

        if (!insList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (insCnt != insList.size()) {
                cMsg.setMessageInfo(NSAL0410Constant.NSAM0032E, new String[] {"DS_CONTR_ADDL_CHRG" });
                return;
            }
        }

        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (updCnt != updList.size()) {
                cMsg.setMessageInfo(NSAL0410Constant.NSAM0031E, new String[] {"DS_CONTR_ADDL_CHRG" });
                return;
            }
        }

    }

    private void setInsRegistDataToDS_CONTR_ADDL_CHRGTMsg(NSAL0410CMsg cMsg, NSAL0410_ACMsg inMsg, DS_CONTR_ADDL_CHRGTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        tMsg.dsContrAddlChrgPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_ADDL_CHRG_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, cMsg.dsContrPk_P);
        // mod start 2016/02/15 CSA Defect#3021
//        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd_H.getValue())) {
//            if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
//                ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, cMsg.dsContrDtlPk_P);
//            }
//        } else {
//            if (ZYPCommonFunc.hasValue(inMsg.dsContrDtlPk_A)) {
//                ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, inMsg.dsContrDtlPk_A);
//            }
//        }
        if (ZYPCommonFunc.hasValue(inMsg.dsContrDtlPk_A)) {
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, inMsg.dsContrDtlPk_A);
        }
        // mod end 2016/02/15 CSA Defect#3021
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgTpCd, inMsg.addlChrgTpCd_SE);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, inMsg.effFromDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, inMsg.effThruDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.trmnDt, inMsg.trmnDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleCd, inMsg.bllgCycleCd_SE);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBillByTpCd, inMsg.svcBillByTpCd_SE);
        if (!ZYPCommonFunc.hasValue(inMsg.addlChrgFlatDealPrcAmt_A)) {
            ZYPEZDItemValueSetter.setValue(inMsg.addlChrgFlatDealPrcAmt_A, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgFlatDealPrcAmt, inMsg.addlChrgFlatDealPrcAmt_A);

        if (!ZYPCommonFunc.hasValue(inMsg.addlChrgAplcPct_A)) {
            ZYPEZDItemValueSetter.setValue(inMsg.addlChrgAplcPct_A, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgAplcPct, inMsg.addlChrgAplcPct_A);

        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgInvTpCd, inMsg.addlChrgInvTpCd_SE);
        if (!ZYPConstant.FLG_ON_Y.equals(inMsg.printDtlFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.printDtlFlg, inMsg.printDtlFlg_A);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgInvdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
        tMsg.ccyCd.clear();
        tMsg.addlChrgFlatFuncPrcAmt.clear();
    }

    private void setUpdRegistDataToDS_CONTR_ADDL_CHRGTMsg(NSAL0410CMsg cMsg, NSAL0410_ACMsg inMsg, DS_CONTR_ADDL_CHRGTMsg tMsg) {

        ZYPEZDItemValueSetter.setValue(tMsg.trmnDt, inMsg.trmnDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBillByTpCd, inMsg.svcBillByTpCd_SE);
        if (!ZYPCommonFunc.hasValue(inMsg.addlChrgFlatDealPrcAmt_A)) {
            ZYPEZDItemValueSetter.setValue(inMsg.addlChrgFlatDealPrcAmt_A, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgFlatDealPrcAmt, inMsg.addlChrgFlatDealPrcAmt_A);

        if (!ZYPCommonFunc.hasValue(inMsg.addlChrgAplcPct_A)) {
            ZYPEZDItemValueSetter.setValue(inMsg.addlChrgAplcPct_A, BigDecimal.ZERO);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgAplcPct, inMsg.addlChrgAplcPct_A);
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgInvTpCd, inMsg.addlChrgInvTpCd_SE);
        if (!ZYPConstant.FLG_ON_Y.equals(inMsg.printDtlFlg_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.printDtlFlg, inMsg.printDtlFlg_A);
        }
        // START 2017/12/26 K.Kojima [QC#18562,ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleCd, inMsg.bllgCycleCd_SE);
        // END 2017/12/26 K.Kojima [QC#18562,ADD]
        // START 2018/05/23 K.Kitachi [QC#26223, ADD]
        ZYPEZDItemValueSetter.setValue(tMsg.addlChrgTpCd, inMsg.addlChrgTpCd_SE);
        ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, inMsg.effFromDt_A);
        ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, inMsg.effThruDt_A);
        // END 2018/05/23 K.Kitachi [QC#26223, ADD]
    }

    /**
     * NSAL0410Scrn00_Delete
     */
    private void doProcess_NSAL0410Scrn00_CMN_Delete(NSAL0410CMsg cMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        removeFromTable(cMsg, selectedRows);
        if ("E".equals(cMsg.getMessageKind())) {
            return;
        }
        ZYPTableUtil.deleteRows(cMsg.A, selectedRows);
    }

    /**
     * removeFromTable
     */
    private void removeFromTable(NSAL0410CMsg cMsg, List<Integer> selectedRows) {

        List<DS_CONTR_ADDL_CHRGTMsg> rmList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>(selectedRows.size());

        // for update
        for (Integer index : selectedRows) {

            if (ZYPCommonFunc.hasValue(cMsg.A.no(index).dsContrAddlChrgPk_A)) {
                DS_CONTR_ADDL_CHRGTMsg rmTMsg = new DS_CONTR_ADDL_CHRGTMsg();

                ZYPEZDItemValueSetter.setValue(rmTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(rmTMsg.dsContrAddlChrgPk, cMsg.A.no(index).dsContrAddlChrgPk_A);
                ZYPEZDItemValueSetter.setValue(rmTMsg.ezUpTime, cMsg.A.no(index).ezUpTime_A);
                ZYPEZDItemValueSetter.setValue(rmTMsg.ezUpTimeZone, cMsg.A.no(index).ezUpTimeZone_A);

                DS_CONTR_ADDL_CHRGTMsg tMsg = checkDeleteData(cMsg, rmTMsg);
                if (tMsg == null) {
                    cMsg.setMessageInfo(NSAL0410Constant.NZZM0003E);
                    return;
                }
                rmList.add(rmTMsg);
            }
        }

        int rmCnt = rmList.size();

        if (rmCnt != 0) {
            int rsltCnt = S21FastTBLAccessor.removeLogical(rmList.toArray(new DS_CONTR_ADDL_CHRGTMsg[0]));
            if (rsltCnt != rmCnt) {
                cMsg.setMessageInfo(NSAL0410Constant.NSAM0033E, new String[] {"DS_CONTR_ADDL_CHRG" });
                return;
            }
        }
    }

    /**
     * checkDeleteData
     */
    private DS_CONTR_ADDL_CHRGTMsg checkDeleteData(NSAL0410CMsg cMsg, DS_CONTR_ADDL_CHRGTMsg updTMsg) {

        DS_CONTR_ADDL_CHRGTMsg wrkMsg = (DS_CONTR_ADDL_CHRGTMsg) updTMsg.clone();
        DS_CONTR_ADDL_CHRGTMsg curTMsg = (DS_CONTR_ADDL_CHRGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (curTMsg == null) {
            return null;
        }

        String findEzUpTime = wrkMsg.ezUpTime.getValue();
        String findEzUpTimeZone = wrkMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = curTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = curTMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }

        return curTMsg;
    }

}
