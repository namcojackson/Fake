package business.blap.NSAL0730;

import static business.blap.NSAL0730.constant.NSAL0730Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0730.common.NSAL0730CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
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
 * 2015/12/22   Hitachi         T.Tsuchida      Update          QC#2328
 * 2016/08/08   Hitachi         A.Kohinata      Update          QC#12001
 * 2016/11/11   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL0730CMsg cMsg = (NSAL0730CMsg) arg0;
        NSAL0730SMsg sMsg = (NSAL0730SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0730_INIT".equals(screenAplID)) {
                doProcess_NSAL0730_INIT(cMsg, sMsg);
            } else if ("NSAL0730Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_ApplyToAll(cMsg, sMsg);
            // START 2017/02/14 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0730Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_CMN_Reset(cMsg, sMsg);
            // END   2017/02/14 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0730Scrn00_Contraction".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_Contraction(cMsg, sMsg);
            } else if ("NSAL0730Scrn00_Expansion".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_Expansion(cMsg, sMsg);
            // mod start 2016/11/07 CSA QC#4210
            } else if ("NSAL0730Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0730Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_PagePrev(cMsg, sMsg);
            // mod end 2016/11/07 CSA QC#4210
            // START 2019/01/10 K.Kitachi [QC#26928, ADD]
            } else if ("NSAL0730Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_AddLine(cMsg, sMsg);
            } else if ("NSAL0730Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL0730Scrn00_DeleteLine(cMsg, sMsg);
            // END 2019/01/10 K.Kitachi [QC#26928, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Initialize event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private void doProcess_NSAL0730_INIT(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        initialize(cMsg, sMsg);
        // mod start 2016/11/07 CSA QC#4210
        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // mod end 2016/11/07 CSA QC#4210
    }

    /**
     * Search event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private void doProcess_NSAL0730Scrn00_ApplyToAll(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {
        // mod start 2016/11/11 CSA QC#4210
        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSAL0730CommonLogic.checkSelect(cMsg, sMsg)) {
            return;
        }

        // mod start 2016/08/08 CSA Defect#12001
//        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // mod end 2016/11/07 CSA QC#4210
        // START 2019/01/10 K.Kitachi [QC#26928, DEL]
//        boolean applyFlg1 = false;
//        boolean applyFlg2 = false;
//        BigDecimal dsContrPkFlg = BigDecimal.ZERO;
//        BigDecimal dsContrDtlPkFlg = BigDecimal.ZERO;
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//            String flgNm = sMsg.A.no(i).xxFlgNm.getValue();
//            String xxChkBox01 = sMsg.A.no(i).xxChkBox_D1.getValue();
//            String xxChkBox02 = sMsg.A.no(i).xxChkBox_D2.getValue();
//            String dsContrMachLvlNum = sMsg.A.no(i).dsContrMachLvlNum.getValue();
//            BigDecimal dsContrPk = sMsg.A.no(i).dsContrPk_P.getValue();
//            BigDecimal dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk.getValue();
//
//            if (LVL_NUM_1.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
//                dsContrPkFlg = sMsg.A.no(i).dsContrPk_P.getValue();
//                applyFlg1 = true;
//            } else if (LVL_NUM_1.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
//                dsContrPkFlg = sMsg.A.no(i).dsContrPk_P.getValue();
//                applyFlg1 = false;
//            }
//
//            if (LVL_NUM_2.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
//                dsContrDtlPkFlg = sMsg.A.no(i).dsContrDtlPk.getValue();
//                applyFlg2 = true;
//            } else if (LVL_NUM_2.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
//                dsContrDtlPkFlg = sMsg.A.no(i).dsContrDtlPk.getValue();
//                applyFlg2 = false;
//            }
//
//            if (LVL_NUM_1.equals(dsContrMachLvlNum) && !ZYPConstant.FLG_ON_1.equals(flgNm) && (applyFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0)) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).custPoNum_D2, cMsg.custPoNum);
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poDt_D2, cMsg.poDt);
//            }
//
//            if (LVL_NUM_2.equals(dsContrMachLvlNum) && !ZYPConstant.FLG_ON_1.equals(flgNm) && (applyFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0 || applyFlg2 && dsContrDtlPk.compareTo(dsContrDtlPkFlg) == 0)) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).custPoNum_D2, cMsg.custPoNum);
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poDt_D2, cMsg.poDt);
//            }
//
//            if (LVL_NUM_3.equals(dsContrMachLvlNum) && !ZYPConstant.FLG_ON_1.equals(flgNm) && (applyFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0 || applyFlg2 && dsContrDtlPk.compareTo(dsContrDtlPkFlg) == 0)) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).custPoNum_D2, cMsg.custPoNum);
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poDt_D2, cMsg.poDt);
//            }
//
//            if (LVL_NUM_3.equals(dsContrMachLvlNum) && !ZYPConstant.FLG_ON_1.equals(flgNm) && (!applyFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0 || !applyFlg2 && dsContrDtlPk.compareTo(dsContrDtlPkFlg) == 0) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox02)) {
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).custPoNum_D2, cMsg.custPoNum);
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poDt_D2, cMsg.poDt);
//            }
//        }
        // END 2019/01/10 K.Kitachi [QC#26928, DEL]

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.custPoNum) || !ZYPCommonFunc.hasValue(cMsg.poFromDt) || !ZYPCommonFunc.hasValue(cMsg.poDt)) {
            if (!ZYPCommonFunc.hasValue(cMsg.custPoNum)) {
                cMsg.custPoNum.setErrorInfo(1, NSAM0645E, new String[] {"New PO#" });
            }
            if (!ZYPCommonFunc.hasValue(cMsg.poFromDt)) {
                cMsg.poFromDt.setErrorInfo(1, NSAM0645E, new String[] {"From Date" });
            }
            if (!ZYPCommonFunc.hasValue(cMsg.poDt)) {
                cMsg.poDt.setErrorInfo(1, NSAM0645E, new String[] {"Thru Date" });
            }
            return;
        }
        if (!NSXC001001ContrValidation.checkConsistentPoDt(cMsg.poFromDt.getValue(), cMsg.poDt.getValue())) {
            cMsg.poFromDt.setErrorInfo(1, NSAM0743E, new String[] {"Thru Date", "From Date" });
            cMsg.poDt.setErrorInfo(1, NSAM0743E, new String[] {"Thru Date", "From Date" });
            return;
        }
        List<Integer> addRows = NSAL0730CommonLogic.getAddRows(cMsg, sMsg);
        List<Integer> ovrdRows = new ArrayList<Integer>();
        for (int addRow : addRows) {
            NSAL0730_ASMsg asMsg = sMsg.A.no(addRow - 1);
            if (!ZYPCommonFunc.hasValue(asMsg.custPoNum_A) && !ZYPCommonFunc.hasValue(asMsg.poFromDt_A) && !ZYPCommonFunc.hasValue(asMsg.poDt_A)) {
                ovrdRows.add(addRow - 1);
            }
        }
        if (sMsg.A.length() < (sMsg.A.getValidCount() + addRows.size() - ovrdRows.size())) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Record", String.valueOf(sMsg.A.length()) });
            return;
        }
        for (int addRow : addRows) {
            if (ovrdRows.contains(addRow - 1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow - 1).custPoNum_A, cMsg.custPoNum);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow - 1).poFromDt_A, cMsg.poFromDt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow - 1).poDt_A, cMsg.poDt);
            } else {
                NSAL0730CommonLogic.addRow(cMsg, sMsg, addRow, cMsg.custPoNum.getValue(), cMsg.poFromDt.getValue(), cMsg.poDt.getValue());
            }
        }
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        NSAL0730CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        // mod end 2016/08/08 CSA Defect#12001

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        int pageFrom = (cMsg.xxPageShowFromNum.getValueInt() - 1) / cMsg.A.length() * cMsg.A.length();
        NSAL0730CommonLogic.pagenation(cMsg, sMsg, pageFrom);
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
    }

    /**
     * Reset event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    // START 2017/02/14 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0730Scrn00_CMN_Reset(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {
    // END   2017/02/14 K.Ochiai [QC#16331, MOD]

        cMsg.custPoNum.clear();
        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        cMsg.poFromDt.clear();
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
        cMsg.poDt.clear();
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.svcCmntTxt.clear();
        cMsg.xxChkBox_H1.clear();
        cMsg.xxChkBox_H2.clear();
        ZYPTableUtil.clear(cMsg.A);

        initialize(cMsg, sMsg);
        // mod start 2016/11/11 CSA QC#4210
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // mod end 2016/11/11 CSA QC#4210
    }

    /**
     * Contraction event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private void doProcess_NSAL0730Scrn00_Contraction(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        // set Expansion Button
        NSAL0730CommonLogic.executeContraction(cMsg, sMsg);
    }

    /**
     * Expansion event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private void doProcess_NSAL0730Scrn00_Expansion(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        // set Expansion Button
        NSAL0730CommonLogic.executeExpansion(cMsg, sMsg);
    }

    /**
     * initialize event handler
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private void initialize(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        // get global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // Service Memo Reason Pull down
        NSAL0730CommonLogic.setServiceMemoReasonInfo(cMsg, sMsg);

        if (cMsg.P.getValidCount() > 0) {
            ZYPTableUtil.clear(sMsg.A);
            // START 2019/01/10 K.Kitachi [QC#26928, ADD]
            ZYPTableUtil.clear(sMsg.D);
            // END 2019/01/10 K.Kitachi [QC#26928, ADD]
            NSAL0730CommonLogic.setDetailListInfo(cMsg, sMsg);

            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
        }
    }

    // mod start 2016/11/07 CSA QC#4210
    private void doProcess_NSAL0730Scrn00_PageNext(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0730_ACMsgArray acMsgArray = cMsg.A;

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int rowNum = cMsg.A.no(cMsg.A.getValidCount() - 1).xxRowNum_A.getValueInt() + 1;
        int cMsgCnt = 0;
        ZYPTableUtil.clear(acMsgArray);
        for (; cMsgCnt < acMsgArray.length();) {
            if (rowNum < sMsg.A.getValidCount()) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowNum).xxBtnFlg.getValue())) {
                    EZDMsg.copy(sMsg.A.no(rowNum), null, acMsgArray.no(cMsgCnt), null);
                    ++cMsgCnt;
                }
                ++rowNum;
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(cMsgCnt);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    private void doProcess_NSAL0730Scrn00_PagePrev(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0730_ACMsgArray acMsgArray = cMsg.A;

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int rowNum = pageFrom;
        int cMsgCnt = 0;
        int flgCnt = 0;
        ZYPTableUtil.clear(acMsgArray);

        int rowCnt = 0;
        for (int bfrFrom = 0; bfrFrom < pageFrom; rowCnt++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowCnt).xxBtnFlg.getValue())) {
                ++flgCnt;
            } else {
                ++bfrFrom;
            }
        }
        rowNum = rowNum + flgCnt;
        for (; cMsgCnt < acMsgArray.length();) {
            if (rowNum < sMsg.A.getValidCount()) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowNum).xxBtnFlg.getValue())) {
                    EZDMsg.copy(sMsg.A.no(rowNum), null, acMsgArray.no(cMsgCnt), null);
                    ++cMsgCnt;
                }
                ++rowNum;
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(cMsgCnt);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // mod end 2016/11/07 CSA QC#4210

    // START 2019/01/10 K.Kitachi [QC#26928, ADD]
    private void doProcess_NSAL0730Scrn00_AddLine(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {
        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (sMsg.A.length() < (sMsg.A.getValidCount() + 1)) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Record", String.valueOf(sMsg.A.length()) });
            return;
        }

        int addRow = NSAL0730CommonLogic.getAddRow(cMsg, sMsg);
        int tmpIdx = addRow;
        int validIdx = 0;
        for (int sIdx = 0; sIdx < sMsg.A.getValidCount(); sIdx++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sIdx).xxBtnFlg.getValue())) {
                validIdx++;
            }
            if (addRow < validIdx) {
                tmpIdx = sIdx;
                break;
            }
        }

        NSAL0730CommonLogic.addRow(cMsg, sMsg, tmpIdx);

        NSAL0730CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        int pageFrom = tmpIdx / cMsg.A.length() * cMsg.A.length();
        NSAL0730CommonLogic.pagenation(cMsg, sMsg, pageFrom);

        int focusIdx = addRow % cMsg.A.length();
        cMsg.xxNum.setValue(focusIdx);
    }

    private void doProcess_NSAL0730Scrn00_DeleteLine(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {
        NSAL0730CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        int targetIdxNum = cMsg.xxNum.getValueInt();
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt();
        int sMsgIdx = targetIdxNum + pageShowFromNum - 1;
        int validIdx = 0;
        for (int sIdx = 0; sIdx < sMsg.A.getValidCount(); sIdx++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sIdx).xxBtnFlg.getValue())) {
                validIdx++;
            }
            if (sMsgIdx < validIdx) {
                sMsgIdx = sIdx;
                break;
            }
        }

        if (NSAL0730CommonLogic.isExistMultRow(cMsg, sMsg, sMsgIdx)) {
            BigDecimal dsContrCrCardPoPk = cMsg.A.no(targetIdxNum).dsContrCrCardPoPk.getValue();
            if (ZYPCommonFunc.hasValue(dsContrCrCardPoPk)) {
                int dSMsgLastIdx = sMsg.D.getValidCount();
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(dSMsgLastIdx).dsContrCrCardPoPk, dsContrCrCardPoPk);
                sMsg.D.setValidCount(dSMsgLastIdx + 1);
            }
            if (NSAL0730CommonLogic.isFirstRow(cMsg, sMsg)) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).xxScrItem34Txt, sMsg.A.no(sMsgIdx).xxScrItem34Txt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).serNum, sMsg.A.no(sMsgIdx).serNum);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).mtrLbDescTxt, sMsg.A.no(sMsgIdx).mtrLbDescTxt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).xxDplyCtrlFlg, sMsg.A.no(sMsgIdx).xxDplyCtrlFlg);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).xxChkBox_D1, sMsg.A.no(sMsgIdx).xxChkBox_D1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).xxChkBox_D2, sMsg.A.no(sMsgIdx).xxChkBox_D2);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIdx + 1).xxFlgNm, sMsg.A.no(sMsgIdx).xxFlgNm);
            }
            for (int i = sMsgIdx + 1; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A, sMsg.A.no(i).xxRowNum_A.getValue().subtract(BigDecimal.ONE));
            }
            List<Integer> selectedRows = new ArrayList<Integer>();
            selectedRows.add(sMsgIdx);
            ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        } else {
            sMsg.A.no(sMsgIdx).custPoNum_A.clear();
            sMsg.A.no(sMsgIdx).poFromDt_A.clear();
            sMsg.A.no(sMsgIdx).poDt_A.clear();
        }

        NSAL0730CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        int pageFrom = pageShowFromNum - 1;
        if (sMsgIdx == sMsg.A.getValidCount() && sMsgIdx % cMsg.A.length() == 0) {
            pageFrom = pageFrom - cMsg.A.length();
        }
        NSAL0730CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }
    // END 2019/01/10 K.Kitachi [QC#26928, ADD]
}
