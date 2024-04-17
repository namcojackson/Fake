/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0610;

import static business.blap.NSAL0610.constant.NSAL0610Constant.*;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0610.common.NSAL0610CommonLogic;
import business.db.DS_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto         Create          N/A
 * 2016/04/21   Hitachi         T.Iwamoto         Update          QC#4424
 *</pre>
 */
public class NSAL0610BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0610_INIT".equals(screenAplID)) {
                doProcess_NSAL0610_INIT((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_PagePrev((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_PageNext((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_LeftArrow".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_LeftArrow((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_RightArrow".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_RightArrow((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_CMN_Reset((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_OrigCollapse".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_OrigCollapse((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else if ("NSAL0610Scrn00_NewCollapse".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_NewCollapse((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610_INIT(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.N);
        ZYPTableUtil.clear(sMsg.O);
        cMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        if (getContrDataByPk(cMsg)) {
            // get Detail Data and Set SMsg
            getMainData(cMsg, sMsg);

            // copy FleetLine or AggregateLine
            if (DS_CONTR_DTL_TP.FLEET.equals(cMsg.A.no(0).dsContrDtlTpCd_A.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.A.no(0).dsContrDtlTpCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(0).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
                rightArrow(cMsg, sMsg);
            }
        }
        // mod start 2016/04/21 CSA Defect#4424
        NSAL0610CommonLogic.setAllSelectedFlg(cMsg, sMsg);
        // mod end 2016/04/21 CSA Defect#4424

    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_PagePrev(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            NSAL0610CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
            NSAL0610CommonLogic.setViewData_A(sMsg);

            int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - cMsg.A.length() - 1;
            NSAL0610CommonLogic.pagenation_A(cMsg, sMsg, pagenationFrom);
        } else {
            NSAL0610CommonLogic.copyCurrentPageToSMsgN(cMsg, sMsg);
            NSAL0610CommonLogic.setViewData_N(sMsg);
            int pagenationFrom = cMsg.xxPageShowFromNum_N.getValueInt() - cMsg.N.length() - 1;
            NSAL0610CommonLogic.pagenation_N(cMsg, sMsg, pagenationFrom);
        }
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_PageNext(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            NSAL0610CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
            NSAL0610CommonLogic.setViewData_A(sMsg);

            int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() + cMsg.A.length() - 1;
            NSAL0610CommonLogic.pagenation_A(cMsg, sMsg, pagenationFrom);
        } else {
            NSAL0610CommonLogic.copyCurrentPageToSMsgN(cMsg, sMsg);
            NSAL0610CommonLogic.setViewData_N(sMsg);

            int pagenationFrom = cMsg.xxPageShowFromNum_N.getValueInt() + cMsg.N.length() - 1;
            NSAL0610CommonLogic.pagenation_N(cMsg, sMsg, pagenationFrom);
        }
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_CMN_Reset(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        cMsg.dsContrNum_H2.clear();
        doProcess_NSAL0610_INIT(cMsg, sMsg);
        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NSAM0200I);
        }
    }

    /**
     * do process (LeftArrow)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_LeftArrow(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        // CMsg=>OSMsg
        NSAL0610CommonLogic.copyCurrentPageToSMsgN(cMsg, sMsg);

        // contract selected
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H2.getValue())) {
            for (int i = 0; i < sMsg.O.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.O.no(i).xxDplyCtrlFlg_N.getValue())) {
                    if (!DS_CONTR_DTL_TP.FLEET.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.O.no(i).xxChkBox_N, ZYPConstant.CHKBOX_ON_Y);
                    }
                }
            }
        }

        cMsg.setCommitSMsg(true);
        // input check
        if (!checkInputDataN(cMsg, sMsg)) {
            return;
        }

        // OSMsg=>BSMsg
        NSAL0610CommonLogic.removeCopyContractData(sMsg);

        // OSMsg Sort
        sortNewContract(sMsg);

        // BMsg=>ASMsg
        NSAL0610CommonLogic.setViewData_A(sMsg);

        // OSMsg=>NSMsg
        NSAL0610CommonLogic.setViewData_N(sMsg);

        // SMsg=>CMsg
        int pagenationAFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        NSAL0610CommonLogic.pagenation_A(cMsg, sMsg, pagenationAFrom);
        int pagenationNFrom = cMsg.xxPageShowFromNum_N.getValueInt() - 1;
        NSAL0610CommonLogic.pagenation_N(cMsg, sMsg, pagenationNFrom);
        cMsg.xxChkBox_H2.clear();
    }

    /**
     * do process (RightArrow)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_RightArrow(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        // CMsg=>BSMsg
        NSAL0610CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);

        // contract selected
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(i).xxDplyCtrlFlg_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
                }
            }
        }

        cMsg.setCommitSMsg(true);
        // input check
        if (!checkInputDataA(cMsg, sMsg)) {
            return;
        }

        rightArrow(cMsg, sMsg);
    }

    /**
     * do process (OrigCollapse)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_OrigCollapse(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
        // CMsg=>BSMsg
        NSAL0610CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);

        // BMsg=>ASMsg
        NSAL0610CommonLogic.setViewData_A(sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        NSAL0610CommonLogic.pagenation_A(cMsg, sMsg, pagenationFrom);
    }

    /**
     * do process (NewCollapse)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_NewCollapse(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
        // CMsg=>OSMsg
        NSAL0610CommonLogic.copyCurrentPageToSMsgN(cMsg, sMsg);

        // OMsg=>NSMsg
        NSAL0610CommonLogic.setViewData_N(sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum_N.getValueInt() - 1;
        NSAL0610CommonLogic.pagenation_N(cMsg, sMsg, pagenationFrom);
    }

    /**
     * get Main Data List
     * @param cMsg NSAL0610CMsg
     * @return Data List
     */
    private void getMainData(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getMainData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 10000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.B.length()), Integer.toString(sMsg.B.length()) });
            }

            // BMsg=>ASMsg
            NSAL0610CommonLogic.setViewData_A(sMsg);

            // Copy one page from SAMsg to CMsg
            if (cMsg.A.length() > sMsg.A.getValidCount()) {
                cMsg.A.setValidCount(sMsg.A.getValidCount());
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            // set Paging Data
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(sMsg.A.getValidCount());
            cMsg.xxPageShowFromNum_N.clear();
            cMsg.xxPageShowToNum_N.clear();
            cMsg.xxPageShowOfNum_N.clear();
        } else {
            // No result
            cMsg.setMessageInfo(NSAM0353E, new String[] {ERR_PRAM_NO_DATA });
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.xxPageShowFromNum_N.clear();
            cMsg.xxPageShowToNum_N.clear();
            cMsg.xxPageShowOfNum_N.clear();
        }
    }

    /**
     * @param cMsg
     * @return
     */
    private boolean getContrDataByPk(NSAL0610CMsg cMsg) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, cMsg.dsContrPk);
        DS_CONTRTMsg rsMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);

        if (rsMsg != null) {
            cMsg.dsContrNum_H1.setValue((String) rsMsg.dsContrNum.getValue());
            cMsg.dsContrCatgCd.setValue((String) rsMsg.dsContrCatgCd.getValue());
            return true;
        }
        cMsg.setMessageInfo(NSAM0353E, new String[] {ERR_PRAM_NO_DATA });
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
        cMsg.xxPageShowFromNum_N.clear();
        cMsg.xxPageShowToNum_N.clear();
        cMsg.xxPageShowOfNum_N.clear();
        return false;
    }

    /**
     * sortNewContract
     * @param sMsg NSAL0610SMsg
     */
    private static void sortNewContract(NSAL0610SMsg sMsg) {
        S21SortTarget sortTarget = new S21SortTarget(sMsg.O, sMsg.O.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add("xxRowNum_N", S21SortKey.ASC);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.O.getValidCount());
    }

    /**
     * check Input for A
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     * @return Change Line : true
     */
    private boolean checkInputDataA(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
        boolean isChkBox = false;
        boolean isAccErr = false;
        int pageIdx = 0;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxChkBox_A.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(sMsg.B.no(i).xxSelFlg_A.getValue())) {
                isChkBox = true;

                if (DS_CONTR_DTL_TP.ACCESSORIES.equals(sMsg.B.no(i).dsContrDtlTpCd_A.getValue())) {
                    for (int j = 0; j < i; j++) {
                        if (sMsg.B.no(i).prntDsContrDtlPk_A.getValueInt() == sMsg.B.no(j).dsContrDtlPk_A.getValueInt() && LEVEL_1.equals(sMsg.B.no(j).xxDplyByCtrlAncrLvlCd_A.getValue())
                                && !ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(j).xxChkBox_A.getValue())) {
                            if (!isAccErr) {
                                pageIdx = getPageIdxA(sMsg.B.no(i).xxRowNum_A.getValueInt(), sMsg.A);
                                isAccErr = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // checkBox is not selected
        if (!isChkBox) {
            cMsg.setMessageInfo(NSAM0015E);
            return false;
        }
        // Accessories Only
        if (isAccErr) {
            // BMsg=>ASMsg
            NSAL0610CommonLogic.setViewData_A(sMsg);

            int pageFrom = pageIdx / cMsg.A.length();
            int pagenationFrom = pageFrom * cMsg.A.length();
            NSAL0610CommonLogic.pagenation_A(cMsg, sMsg, pagenationFrom);

            cMsg.setMessageInfo(NSAM0397E);
            return false;
        }
        return true;
    }

    /**
     * getPageIdxA
     * @param targetNum int
     * @param asMsg NSAL0610_ASMsgArray
     * @return
     */
    private int getPageIdxA(int targetNum, NSAL0610_ASMsgArray asMsg) {

        for (int k = 0; k < asMsg.getValidCount(); k++) {
            if (asMsg.no(k).xxRowNum_A.getValueInt() == targetNum) {
                return k;
            }
        }
        return 0;
    }

    /**
     * check Input for N
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     * @return
     */
    private boolean checkInputDataN(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
        boolean isChkBox = false;
        boolean isAccErr = false;
        int pageIdx = 0;

        for (int i = 0; i < sMsg.O.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.O.no(i).xxChkBox_N.getValue())) {
                if (!DS_CONTR_DTL_TP.FLEET.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue())) {
                    isChkBox = true;
                }

                if (!DS_CONTR_DTL_TP.ACCESSORIES.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue())) {
                    for (int j = i; j < sMsg.O.getValidCount(); j++) {
                        if (sMsg.O.no(i).dsContrDtlPk_N.getValueInt() == sMsg.O.no(j).prntDsContrDtlPk_N.getValueInt() && LEVEL_4.equals(sMsg.O.no(j).xxDplyByCtrlAncrLvlCd_N.getValue())
                                && !ZYPConstant.FLG_ON_Y.equals(sMsg.O.no(j).xxChkBox_N.getValue())) {
                            if (!isAccErr) {
                                pageIdx = getPageIdxN(sMsg.O.no(i).xxRowNum_N.getValueInt(), sMsg.N);
                                isAccErr = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // checkBox is not selected
        if (!isChkBox) {
            cMsg.setMessageInfo(NSAM0015E);
            return false;
        }
        // Accessories not checked
        if (isAccErr) {
            // BMsg=>ASMsg
            NSAL0610CommonLogic.setViewData_N(sMsg);
            int pageFrom = pageIdx / cMsg.N.length();
            int pagenationFrom = pageFrom * cMsg.N.length();
            NSAL0610CommonLogic.pagenation_N(cMsg, sMsg, pagenationFrom);

            cMsg.setMessageInfo(NSAM0398E);
            return false;
        }
        return true;
    }

    /**
     * getPageIdxN
     * @param targetNum int
     * @param nsMsg NSAL0610_NSMsgArray
     * @return
     */
    private int getPageIdxN(int targetNum, NSAL0610_NSMsgArray nsMsg) {

        for (int k = 0; k < nsMsg.getValidCount(); k++) {
            if (nsMsg.no(k).xxRowNum_N.getValueInt() == targetNum) {
                return k;
            }
        }
        return 0;
    }

    /**
     * remove Copy Contract Data.
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private static void rightArrow(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        // BSMsg=>OSMsg
        NSAL0610CommonLogic.setCopyContractData(sMsg);

        // OSMsg Sort
        sortNewContract(sMsg);

        // BMsg=>ASMsg
        NSAL0610CommonLogic.setViewData_A(sMsg);

        // OSMsg=>NSMsg
        NSAL0610CommonLogic.setViewData_N(sMsg);

        // SMsg=>CMsg
        int pagenationAFrom = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        NSAL0610CommonLogic.pagenation_A(cMsg, sMsg, pagenationAFrom);
        NSAL0610CommonLogic.pagenation_N(cMsg, sMsg, 0);
        cMsg.xxChkBox_H1.clear();
    }

}
