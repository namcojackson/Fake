package business.blap.NSAL0730;

import static business.blap.NSAL0730.constant.NSAL0730Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSAL0730.common.NSAL0730CommonLogic;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Update PO Information
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2015/12/21   Hitachi         T.Tsuchida      Update          QC#2296
 * 2016/03/23   Hitachi         M.Gotou         Update          QC#4465
 * 2016/04/07   Hitachi         T.Tomita        Update          QC#4464
 * 2016/08/08   Hitachi         A.Kohinata      Update          QC#12001
 * 2016/11/11   Hitachi         T.Mizuki        Update          QC#4210
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 * 2023/05/24   Hitachi         K.Watanabe      Update          QC#61498
 *</pre>
 */
public class NSAL0730BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0730CMsg cMsg = (NSAL0730CMsg) arg0;
        NSAL0730SMsg sMsg = (NSAL0730SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);
        String screenAplId = cMsg.getScreenAplID();
        try {
            if (screenAplId.equals("NSAL0730Scrn00_CMN_Submit")) {
                doProcess_NSAL0730Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private void doProcess_NSAL0730Scrn00_CMN_Submit(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {
    // mod start 2016/11/11 CSA QC#4210
        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSAL0730CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }

        // mod start 2016/08/08 CSA Defect#12001
//        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // mod end 2016/11/07 CSA QC#4210
        // add start 2016/03/23 CSA Defect#4465
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        copyLowerLines(sMsg);
        NSAL0730CommonLogic.selectHiddenCheckBox(cMsg, sMsg);
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        // add end 2016/03/23 CSA Defect#4465
        boolean checkFlg1 = false;
        boolean checkFlg2 = false;
        boolean errFlg = false;
        BigDecimal dsContrPkFlg = BigDecimal.ZERO;
        BigDecimal dsContrDtlPkFlg = BigDecimal.ZERO;
        for (int idx = 0; idx < sMsg.A.getValidCount(); idx++) {
            NSAL0730_ASMsg asMsg = sMsg.A.no(idx);
            String xxChkBoxD1 = asMsg.xxChkBox_D1.getValue();
            String xxChkBoxD2 = asMsg.xxChkBox_D2.getValue();
            BigDecimal dsContrPk = asMsg.dsContrPk_P.getValue();
            BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
            String flgNm = asMsg.xxFlgNm.getValue();

            if (ZYPConstant.FLG_ON_1.equals(flgNm)) {
                continue;
            }

            String dsContrMachLvlNum = asMsg.dsContrMachLvlNum.getValue();
            if (LVL_NUM_1.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD1)) {
                dsContrPkFlg = asMsg.dsContrPk_P.getValue();
                checkFlg1 = true;
            } else if (LVL_NUM_1.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD1)) {
                dsContrPkFlg = asMsg.dsContrPk_P.getValue();
                checkFlg1 = false;
            }

            if (LVL_NUM_2.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD1)) {
                dsContrDtlPkFlg = asMsg.dsContrDtlPk.getValue();
                checkFlg2 = true;
            } else if (LVL_NUM_2.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD1)) {
                dsContrDtlPkFlg = asMsg.dsContrDtlPk.getValue();
                checkFlg2 = false;
            }

            if ((LVL_NUM_1.equals(dsContrMachLvlNum) && checkFlg1) || (ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD2))) {
                // mod start 2016/04/07 CSA Defect#4464
//                if (!hasValue(acMsg.custPoNum_D2)) {
//                    cMsg.A.no(idx).custPoNum_D2.setErrorInfo(1, ZZM9000E, new String[] {"New PO#" });
//                    errFlg = true;
//                }
//
//                if (!hasValue(acMsg.poDt_D2)) {
//                    cMsg.A.no(idx).poDt_D2.setErrorInfo(1, ZZM9000E, new String[] {"New Exp Date" });
//                    errFlg = true;
//                }
                // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//                if (!NSXC001001ContrValidation.checkPoExprDt(asMsg.custPoNum_D2.getValue(), asMsg.poDt_D2.getValue())) {
//                    if (hasValue(asMsg.custPoNum_D2)) {
//                        asMsg.poDt_D2.setErrorInfo(1, NSAM0066E, new String[] {"New PO#", "New Exp Date" });
//                        errFlg = true;
//                    } else if (hasValue(asMsg.poDt_D2)) {
//                        asMsg.custPoNum_D2.setErrorInfo(1, NSAM0066E, new String[] {"New Exp Date", "New PO#" });
//                        errFlg = true;
//                    }
//                }
                if (!NSXC001001ContrValidation.checkPoExprDt(asMsg.custPoNum_A.getValue(), asMsg.poFromDt_A.getValue(), asMsg.poDt_A.getValue())) {
                    if (hasValue(asMsg.custPoNum_A)) {
                        asMsg.poFromDt_A.setErrorInfo(1, NSAM0066E, new String[] {"PO#", "PO Date" });
                        asMsg.poDt_A.setErrorInfo(1, NSAM0066E, new String[] {"PO#", "PO Date" });
                        errFlg = true;
                    } else {
                        asMsg.custPoNum_A.setErrorInfo(1, NSAM0066E, new String[] {"PO Date", "PO#" });
                        errFlg = true;
                    }
                }
                // END 2019/01/10 K.Kitachi [QC#26928, MOD]

                if (isErrPoReq(asMsg)) {
                    errFlg = true;
                }
                // mod end 2016/04/07 CSA Defect#4464
            }

            if (!LVL_NUM_1.equals(dsContrMachLvlNum) && ((checkFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0) || (checkFlg2 && dsContrDtlPkFlg.compareTo(dsContrDtlPk) == 0))) {
                // mod start 2016/04/07 CSA Defect#4464
//                if (!hasValue(acMsg.custPoNum_D2)) {
//                    cMsg.A.no(idx).custPoNum_D2.setErrorInfo(1, ZZM9000E, new String[] {"New PO#" });
//                    errFlg = true;
//                }
//
//                if (!hasValue(acMsg.poDt_D2)) {
//                    cMsg.A.no(idx).poDt_D2.setErrorInfo(1, ZZM9000E, new String[] {"New Exp Date" });
//                    errFlg = true;
//                }
                // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//                if (!NSXC001001ContrValidation.checkPoExprDt(asMsg.custPoNum_D2.getValue(), asMsg.poDt_D2.getValue())) {
//                    if (hasValue(sMsg.A.no(idx).custPoNum_D2)) {
//                        asMsg.poDt_D2.setErrorInfo(1, NSAM0066E, new String[] {"New PO#", "New Exp Date" });
//                        errFlg = true;
//                    } else if (hasValue(sMsg.A.no(idx).poDt_D2)) {
//                        asMsg.custPoNum_D2.setErrorInfo(1, NSAM0066E, new String[] {"New Exp Date", "New PO#" });
//                        errFlg = true;
//                    }
//                }
                if (!NSXC001001ContrValidation.checkPoExprDt(asMsg.custPoNum_A.getValue(), asMsg.poFromDt_A.getValue(), asMsg.poDt_A.getValue())) {
                    if (hasValue(sMsg.A.no(idx).custPoNum_A)) {
                        asMsg.poFromDt_A.setErrorInfo(1, NSAM0066E, new String[] {"PO#", "PO Date" });
                        asMsg.poDt_A.setErrorInfo(1, NSAM0066E, new String[] {"PO#", "PO Date" });
                        errFlg = true;
                    } else {
                        asMsg.custPoNum_A.setErrorInfo(1, NSAM0066E, new String[] {"PO Date", "PO#" });
                        errFlg = true;
                    }
                }
                // END 2019/01/10 K.Kitachi [QC#26928, MOD]

                if (isErrPoReq(asMsg)) {
                    errFlg = true;
                }
                // mod end 2016/04/07 CSA Defect#4464
            }
        }

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        if (!NSAL0730CommonLogic.relationCheck(cMsg, sMsg)) {
            errFlg = true;
        }
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        if (!errFlg) {
            NSAL0730CommonLogic.submitContractInfo(cMsg, sMsg);
        }

        NSAL0730CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        // mod end 2016/08/08 CSA Defect#12001
    }

    // add start 2016/03/23 CSA Defect#4465
    // mod start 2016/08/08 CSA Defect#12001
    // START 2019/01/10 K.Kitachi [QC#26928, DEL]
//    /**
//     * copy Lower Lines
//     * @param sMsg NSAL0730SMsg
//     */
//    public static void copyLowerLines(NSAL0730SMsg sMsg) {
//
//        for (int idx = 0; idx < sMsg.A.getValidCount(); idx++) {
//            NSAL0730_ASMsg asMsg = sMsg.A.no(idx);
//            String xxChkBoxD1 = asMsg.xxChkBox_D1.getValue();
//            BigDecimal dsContrPk = asMsg.dsContrPk_P.getValue();
//            BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
//            String dsContrMachLvlNum = asMsg.dsContrMachLvlNum.getValue();
//
//            if (LVL_NUM_1.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD1)) {
//                for (int cpyidx = idx + 1; cpyidx < sMsg.A.getValidCount(); cpyidx++) {
//                    BigDecimal dsContrPkCpy = sMsg.A.no(cpyidx).dsContrPk_P.getValue();
//                    String dsContrMachLvlNumCpy = sMsg.A.no(cpyidx).dsContrMachLvlNum.getValue();
//                    if (!dsContrPk.equals(dsContrPkCpy)) {
//                        break;
//                    }
//                    if (LVL_NUM_2.equals(dsContrMachLvlNumCpy) && dsContrPk.equals(dsContrPkCpy)) {
//                        if (hasValue(asMsg.custPoNum_D2) && !hasValue(sMsg.A.no(cpyidx).custPoNum_D2)) {
//                            setValue(sMsg.A.no(cpyidx).custPoNum_D2, asMsg.custPoNum_D2);
//                        }
//                        if (hasValue(asMsg.poDt_D2) && !hasValue(sMsg.A.no(cpyidx).poDt_D2)) {
//                            setValue(sMsg.A.no(cpyidx).poDt_D2, asMsg.poDt_D2);
//                        }
//                        dsContrDtlPk = sMsg.A.no(cpyidx).dsContrDtlPk.getValue();
//                        for (int cpyidx2 = cpyidx + 1; cpyidx2 < sMsg.A.getValidCount(); cpyidx2++) {
//                            BigDecimal dsContrDtlPkCpy = sMsg.A.no(cpyidx2).dsContrDtlPk.getValue();
//                            if (!dsContrDtlPk.equals(dsContrDtlPkCpy)) {
//                                break;
//                            }
//                            if (hasValue(asMsg.custPoNum_D2) && dsContrDtlPk.equals(dsContrDtlPkCpy) && !hasValue(sMsg.A.no(cpyidx2).custPoNum_D2)) {
//                                setValue(sMsg.A.no(cpyidx2).custPoNum_D2, sMsg.A.no(cpyidx).custPoNum_D2);
//                            }
//                            if (hasValue(asMsg.poDt_D2) && dsContrDtlPk.equals(dsContrDtlPkCpy) && !hasValue(sMsg.A.no(cpyidx2).poDt_D2)) {
//                                setValue(sMsg.A.no(cpyidx2).poDt_D2, sMsg.A.no(cpyidx).poDt_D2);
//                            }
//                        }
//                    }
//                }
//            } else if (LVL_NUM_2.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBoxD1)) {
//                for (int cpyidx = idx + 1; cpyidx < sMsg.A.getValidCount(); cpyidx++) {
//                    BigDecimal dsContrDtlPkCpy = sMsg.A.no(cpyidx).dsContrDtlPk.getValue();
//                    if (dsContrDtlPk.equals(dsContrDtlPkCpy)) {
//                        if (hasValue(asMsg.custPoNum_D2) && !hasValue(sMsg.A.no(cpyidx).custPoNum_D2)) {
//                            setValue(sMsg.A.no(cpyidx).custPoNum_D2, asMsg.custPoNum_D2);
//                        }
//                        if (hasValue(asMsg.poDt_D2) && !hasValue(sMsg.A.no(cpyidx).poDt_D2)) {
//                            setValue(sMsg.A.no(cpyidx).poDt_D2, asMsg.poDt_D2);
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }
//
//        }
//    }
    // END 2019/01/10 K.Kitachi [QC#26928, DEL]
    // mod end 2016/08/08 CSA Defect#12001
    // add end 2016/03/23 CSA Defect#4465

    // add start 2016/04/07 CSA Defect#4464
    // mod start 2016/08/08 CSA Defect#12001
    private boolean isErrPoReq(NSAL0730_ASMsg asMsg) {
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        if (hasValue(asMsg.custPoNum_D2)) {
        if (hasValue(asMsg.custPoNum_A)) {
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            return false;
        }

        boolean reqFlg = false;
        String dsContrMachLvlNum = asMsg.dsContrMachLvlNum.getValue();
        if (LVL_NUM_1.equals(dsContrMachLvlNum)) {
            // Contract
            reqFlg = NSAL0730CommonLogic.getPoReqForContr(getGlobalCompanyCode(), asMsg);
        // START 2023/05/24 K.Watanabe [QC#61498, DEL]
//        } else if (LVL_NUM_2.equals(dsContrMachLvlNum)) {
//            // Machine
//            reqFlg = getPoReqForMach(asMsg);
//        } else if (LVL_NUM_3.equals(dsContrMachLvlNum)) {
//            // Base or Usage
//            if (hasValue(asMsg.dsContrBllgMtrPk)) {
//                // Usage
//                reqFlg = getPoReqForUsg(asMsg);
//            } else {
//                // Base
//                reqFlg = getPoReqForBase(asMsg);
//            }
        // END 2023/05/24 K.Watanabe [QC#61498, DEL]
        }

        if (reqFlg) {
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            asMsg.custPoNum_D2.setErrorInfo(1, ZZM9000E, new String[] {"New PO#" });
            asMsg.custPoNum_A.setErrorInfo(1, ZZM9000E, new String[] {"PO#" });
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            return true;
        }
        return false;
    }

    // START 2023/05/24 K.Watanabe [QC#61498, DEL]
//    private boolean getPoReqForContr(NSAL0730_ASMsg asMsg) {
//        BigDecimal dsContrPk = asMsg.dsContrPk_P.getValue();
//        if (!hasValue(dsContrPk)) {
//            return false;
//        }
//
//        boolean reqFlg = false;
//        String glblCmpyCd = getGlobalCompanyCode();
//        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
//
//        DS_CONTRTMsg dsContrTMsg = NSAL0730Query.getInstance().getDsContr(glblCmpyCd, dsContrPk);
//        if (dsContrTMsg == null) {
//            return reqFlg;
//        }
//
//        String dsAcctNum = dsContrTMsg.dsAcctNum.getValue();
//        String billToCustCd = dsContrTMsg.altPayerCustCd.getValue();
//        if (hasValue(billToCustCd)) {
//            reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);
//        }
//
//        billToCustCd = dsContrTMsg.leaseCmpyCd.getValue();
//        if (!reqFlg && hasValue(billToCustCd)) {
//            reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);
//        }
//        return reqFlg;
//    }
    // END 2023/05/24 K.Watanabe [QC#61498, DEL]

    private boolean getPoReqForMach(NSAL0730_ASMsg asMsg) {
        BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
        if (!hasValue(dsContrDtlPk)) {
            return false;
        }

        boolean reqFlg = false;
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);

        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0730Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return reqFlg;
        }

        DS_CONTRTMsg dsContrTMsg = NSAL0730Query.getInstance().getDsContr(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            return reqFlg;
        }

        DS_CONTR_DTL_TPTMsg dsContrDtlTpTMsg = NSAL0730Query.getInstance().getDsContrDtlTp(glblCmpyCd, dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        if (dsContrDtlTpTMsg == null) {
            return reqFlg;
        }

        String dsAcctNum = dsContrTMsg.dsAcctNum.getValue();
        String billToCustCd;
        if (ZYPConstant.FLG_ON_Y.equals(dsContrDtlTpTMsg.baseChrgFlg.getValue())) {
            billToCustCd = dsContrDtlTMsg.baseBillToCustCd.getValue();
            reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);
        }

        return reqFlg;
    }

    private boolean getPoReqForBase(NSAL0730_ASMsg asMsg) {
        BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
        if (!hasValue(dsContrDtlPk)) {
            return false;
        }

        boolean reqFlg = false;
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);

        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0730Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return reqFlg;
        }

        DS_CONTRTMsg dsContrTMsg = NSAL0730Query.getInstance().getDsContr(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            return reqFlg;
        }

        String dsAcctNum = dsContrTMsg.dsAcctNum.getValue();
        String billToCustCd = dsContrDtlTMsg.baseBillToCustCd.getValue();
        reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);

        return reqFlg;
    }

    private boolean getPoReqForUsg(NSAL0730_ASMsg asMsg) {
        BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
        if (!hasValue(dsContrDtlPk)) {
            return false;
        }

        boolean reqFlg = false;
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);

        DS_CONTR_DTLTMsg dsContrDtlTMsg = NSAL0730Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return reqFlg;
        }

        DS_CONTRTMsg dsContrTMsg = NSAL0730Query.getInstance().getDsContr(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            return reqFlg;
        }

        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = NSAL0730Query.getInstance().getDsContrBllgMtr(glblCmpyCd, asMsg.dsContrBllgMtrPk.getValue());
        if (dsContrBllgMtrTMsg == null) {
            return reqFlg;
        }

        String dsAcctNum = dsContrTMsg.dsAcctNum.getValue();
        String billToCustCd = dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue();
        reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);

        return reqFlg;
    }
    // mod end 2016/08/08 CSA Defect#12001
    // add end 2016/04/07 CSA Defect#4464
}
