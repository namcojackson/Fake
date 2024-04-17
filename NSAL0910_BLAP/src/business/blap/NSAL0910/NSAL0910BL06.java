/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0910;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0910.constant.NSAL0910Constant.*;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0910.common.NSAL0910CommonLogic;
import business.db.CFS_INV_INTFCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * CFS Invoice Error Correction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0910BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0910CMsg cMsg = (NSAL0910CMsg) arg0;
        NSAL0910SMsg sMsg = (NSAL0910SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0910Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NSAL0910Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0910Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0910Scrn00_CMN_Submit(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

        //SMsg -> CMsg
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.length(); i++) {
            if (pageFromNum + i < sMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(i), null, sMsg.A.get(pageFromNum + i), null);
            }
        }
        //input check
        if (!checkInputData(cMsg, sMsg)) {
            return;
        }

        //update CFS_INV_INTFC info
        doSubmit(cMsg, sMsg);
    }

    /**
     * @param cMsg
     * @param sMsg
     * @param isErr
     * @param index
     * @return
     */
    private boolean checkInputData(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {
        boolean isErr = false;
        int index = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0910_ASMsg asMsg = sMsg.A.no(i);
            String dsContrNumByInv = null;
            String dsContrNumBySer = null;
            if (CFS_INV_PROC_STS.ERROR_RECORD.equals(asMsg.cfsInvProcStsCd_A.getValue())) {

                //Mandatory check of Serial#
                if (!hasValue(asMsg.cfsSerNum_A)) {
                    isErr = setErrFlg(cMsg, isErr, i);
                    index = cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
                    if (i < cMsg.xxPageShowCurNum.getValueInt() * cMsg.A.length()) {
                        cMsg.A.no(i - index).cfsSerNum_A.setErrorInfo(1, ZZM9000E, new String[]{COLUMN_NAME_SER_NUM});
                    }
                    continue;
                }

                //Existence check of Serial#
                if (!NSAL0910CommonLogic.getSvcMachData(cMsg, asMsg)) {
                    isErr = setErrFlg(cMsg, isErr, i);
                    index = cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
                    if (i < cMsg.xxPageShowCurNum.getValueInt() * cMsg.A.length()) {
                        cMsg.A.no(i - index).cfsSerNum_A.setErrorInfo(1, NSZM0703E, new String[]{cMsg.A.no(i - index).cfsSerNum_A.getValue()});
                    }
                    continue;
                }

                //Invoice Check
                if (hasValue(asMsg.csaInvNum_A)) {
                    dsContrNumByInv = NSAL0910CommonLogic.getInvData(cMsg, asMsg);
                    if (dsContrNumByInv == null) {
                        isErr = setErrFlg(cMsg, isErr, i);
                        index = cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
                        if (i < cMsg.xxPageShowCurNum.getValueInt() * cMsg.A.length()) {
                            if (hasValue(asMsg.csaContrNum_A)) {
                                cMsg.A.no(i - index).csaContrNum_A.setErrorInfo(1, NSAM0533E);
                                cMsg.A.no(i - index).csaInvNum_A.setErrorInfo(1, NSAM0533E);
                            } else {
                                cMsg.A.no(i - index).csaInvNum_A.setErrorInfo(1, NSAM0539E);
                            }
                        }
                        continue;
                    }
                }

                //Contract check
                dsContrNumBySer = NSAL0910CommonLogic.getContrData(cMsg, asMsg);
                if (dsContrNumBySer == null) {
                    isErr = setErrFlg(cMsg, isErr, i);
                    index = cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
                    if (i < cMsg.xxPageShowCurNum.getValueInt() * cMsg.A.length()) {
                        if (hasValue(asMsg.csaContrNum_A)) {
                            cMsg.A.no(i - index).csaContrNum_A.setErrorInfo(1, NSAM0534E);
                            cMsg.A.no(i - index).cfsSerNum_A.setErrorInfo(1, NSAM0534E);
                        } else {
                            cMsg.A.no(i - index).cfsSerNum_A.setErrorInfo(1, NSAM0538E);
                        }
                    }
                    continue;
                }

                //Specified Contract Check
                if (hasValue(asMsg.csaInvNum_A) && !hasValue(asMsg.csaContrNum_A) && !dsContrNumBySer.equals(dsContrNumByInv)) {
                    isErr = setErrFlg(cMsg, isErr, i);
                    index = cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
                    if (i < cMsg.xxPageShowCurNum.getValueInt() * cMsg.A.length()) {
                        cMsg.A.no(i - index).csaInvNum_A.setErrorInfo(1, NSAM0540E);
                        cMsg.A.no(i - index).cfsSerNum_A.setErrorInfo(1, NSAM0540E);
                    }
                }
            }
        }
        return !isErr;
    }

    /**
     * @param cMsg
     * @param isErr
     * @param i
     * @return
     */
    private boolean setErrFlg(NSAL0910CMsg cMsg, boolean isErr, int i) {
        if (!isErr) {
            setPageShowCurNum(cMsg, isErr, i);
            isErr = true;
        }
        return isErr;
    }

    /**
     * @param cMsg
     * @param isErr
     * @param i
     */
    private void setPageShowCurNum(NSAL0910CMsg cMsg, boolean isErr, int i) {
        int pageShowCurNum = 0;
        pageShowCurNum = i / cMsg.A.length() + 1;
        cMsg.xxPageShowCurNum.setValue(pageShowCurNum);
    }



    private void doSubmit(NSAL0910CMsg cMsg, NSAL0910SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0910_ASMsg asMsg = sMsg.A.no(i);
            if (CFS_INV_PROC_STS.ERROR_RECORD.equals(asMsg.cfsInvProcStsCd_A.getValue())) {
                if (!updateCfsInvIntfc(cMsg, asMsg)) {
                    return;
                }
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private boolean updateCfsInvIntfc(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg) {

        CFS_INV_INTFCTMsg inMsg  = new CFS_INV_INTFCTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.cfsInvIntfcPk, asMsg.cfsInvIntfcPk_A);

        CFS_INV_INTFCTMsg updTMsg = (CFS_INV_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setParam(cMsg, asMsg, updTMsg);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {TBL_NM_CFS_INV_INTFC });
            return false;
        }
        return true;
    }

    private void setParam(NSAL0910CMsg cMsg, NSAL0910_ASMsg asMsg, CFS_INV_INTFCTMsg tMsg) {

        setValue(tMsg.csaInvNum, asMsg.csaInvNum_A);
        setValue(tMsg.csaContrNum, asMsg.csaContrNum_A);
        setValue(tMsg.cfsSerNum, asMsg.cfsSerNum_A);
        setValue(tMsg.cfsInvProcStsCd, CFS_INV_PROC_STS.ERROR_FIXED);
    }

}
