/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1200;

import static business.blap.NSAL1200.constant.NSAL1200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1200.common.NSAL1200CommonLogic;
import business.db.MTR_GRPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2016/04/26   Hitachi         Y.Takeno        Update          QC#6700
 * 2016/04/27   Hitachi         T.Tomita        Update          QC#7648
 * 2016/07/13   Hitachi         A.Kohinata      Update          QC#11813
 * 2016/07/15   Hitachi         T.Tomita        Update          QC#11811
 * 2016/08/01   Hitachi         Y.Osawa         Update          QC#12242
 * 2016/12/01   Hitachi         K.Kojima        Update          QC#14204
 * 2016/12/08   Hitachi         A.Kohinata      Update          QC#16418
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18195
 *</pre>
 */
public class NSAL1200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1200_INIT".equals(screenAplID)) {
                doProcess_NSAL1200_INIT((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
                ZYPGUITableColumn.getColData((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_PageJump((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_PageNext((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_PagePrev((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_CMN_Clear((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_CMN_Submit((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_AddLine((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_DeleteLine((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_AddCounterGroup".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_AddCounterGroup((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_Search((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200_NSAL9900".equals(screenAplID)) {
                return;
            } else if ("NSAL1200Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1200Scrn00_TBLColumnSort((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
            } else if ("NSAL1200Scrn00_CMN_ColClear".equals(screenAplID)) {
                return;
            } else if ("NSAL1200Scrn00_CMN_ColSave".equals(screenAplID)) {
                return;
            // START 2017/08/03 T.Kanasaka [QC#18195,DEL]
//            // add start 2016/07/13 QC#11813
//            } else if ("NSAL1200Scrn00_OpenWin_NSAL1290".equals(screenAplID)) {
//                doProcess_NSAL1200Scrn00_OpenWin_NSAL1290((NSAL1200CMsg) cMsg, (NSAL1200SMsg) sMsg);
//            // add end 2016/07/13 QC#11813
            // END 2017/08/03 T.Kanasaka [QC#18195,DEL]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL1200_INIT(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));

        ZYPCodeDataUtil.createPulldownList(MTR_GRP_TP.class, cMsg.mtrGrpTpCd_L1, cMsg.mtrGrpTpDescTxt_L1);
    }

    // START 2017/01/20 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSAL1200Scrn00_CMN_Clear(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
//        if (hasValue(cMsg.mtrGrpPk_H)) {
//            sMsg.clear();
//            ZYPTableUtil.clear(cMsg.A);
//            ZYPTableUtil.clear(sMsg.A);
//            cMsg.setCommitSMsg(true);
//
//            getSearchData(cMsg, sMsg);
//
//            if (!hasValue(cMsg.getMessageCode())) {
//                cMsg.setMessageInfo(NZZM0002I);
//            }
//        } else {
        doProcess_NSAL1200_INIT(cMsg, sMsg);
//        }
    }
    // EMD 2017/01/20 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSAL1200Scrn00_CMN_Submit(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        cMsg.setCommitSMsg(true);

        getSearchData(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void doProcess_NSAL1200Scrn00_AddCounterGroup(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfMsgAlrdyDplyFlg.getValue())) {
            // set confirmation message
            setValue(cMsg.xxConfMsgAlrdyDplyFlg, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NSAM0449W);
            return;
        }

        // initialize screen 
        doProcess_NSAL1200_INIT(cMsg, sMsg);
    }

    private void doProcess_NSAL1200Scrn00_Search(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        cMsg.setCommitSMsg(true);

        getSearchData(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void doProcess_NSAL1200Scrn00_PageJump(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // del start 2016/04/27 CSA Defect#7648
//        if (!NSAL1200CommonLogic.validateDetailLines(cMsg, sMsg)) {
//            int errIndex = NSAL1200CommonLogic.getFirstErrorIndex(cMsg, sMsg);
//            NSAL1200CommonLogic.pagenation(cMsg, sMsg, errIndex);
//            return;
//        }
        // del end 2016/04/27 CSA Defect#7648
        int rowIndex = NSAL1200CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL1200CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL1200Scrn00_PageNext(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // del start 2016/04/27 CSA Defect#7648
//        if (!NSAL1200CommonLogic.validateDetailLines(cMsg, sMsg)) {
//            int errIndex = NSAL1200CommonLogic.getFirstErrorIndex(cMsg, sMsg);
//            NSAL1200CommonLogic.pagenation(cMsg, sMsg, errIndex);
//            return;
//        }
        // del end 2016/04/27 CSA Defect#7648
        int rowIndex = NSAL1200CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSAL1200CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL1200Scrn00_PagePrev(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // del start 2016/04/27 CSA Defect#7648
//        if (!NSAL1200CommonLogic.validateDetailLines(cMsg, sMsg)) {
//            int errIndex = NSAL1200CommonLogic.getFirstErrorIndex(cMsg, sMsg);
//            NSAL1200CommonLogic.pagenation(cMsg, sMsg, errIndex);
//            return;
//        }
        // del end 2016/04/27 CSA Defect#7648
        int rowIndex = NSAL1200CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSAL1200CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL1200Scrn00_AddLine(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int currentRowCount = sMsg.A.getValidCount();
        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Counter", String.valueOf(cMsg.A.length()) });
            return;
        }
        // del start 2016/04/27 CSA Defect#7648
//        if (!NSAL1200CommonLogic.validateDetailLines(cMsg, sMsg)) {
//            int errIndex = NSAL1200CommonLogic.getFirstErrorIndex(cMsg, sMsg);
//            NSAL1200CommonLogic.pagenation(cMsg, sMsg, errIndex);
//            return;
//        }
        // del end 2016/04/27 CSA Defect#7648
        NSAL1200CommonLogic.setEmptyRecord(getGlobalCompanyCode(), cMsg, sMsg, currentRowCount);

        int rowsPerPage = cMsg.A.length();
        int lastPage = NSAL1200CommonLogic.getLastPageNum(cMsg, sMsg);
        int pagenationFrom = NSAL1200CommonLogic.convertPageNumToFirstRowIndex(rowsPerPage, lastPage);
        NSAL1200CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSAL1200Scrn00_DeleteLine(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A, ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        // START 2016/07/15 T.Tomita [QC#11811, ADD]
        for (Integer selectedRow : selectedRows) {
            if (hasValue(sMsg.A.no(selectedRow).dsMdlMtrPk_A)) {
                EZDMsg.copy(sMsg.A.no(selectedRow), "A", sMsg.B.no(sMsg.B.getValidCount()), "B");
                // START 2017/08/03 T.Kanasaka [QC#18195,ADD]
                setValue(sMsg.B.no(sMsg.B.getValidCount()).bllgMtrMapPk_B1, sMsg.A.no(selectedRow).bllgMtrMapPk_L1);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).bllgMtrMapPk_B2, sMsg.A.no(selectedRow).bllgMtrMapPk_L2);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).bllgMtrMapPk_B3, sMsg.A.no(selectedRow).bllgMtrMapPk_L3);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTime_B1, sMsg.A.no(selectedRow).ezUpTime_A1);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTimeZone_B1, sMsg.A.no(selectedRow).ezUpTimeZone_A1);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTime_B2, sMsg.A.no(selectedRow).ezUpTime_A2);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTimeZone_B2, sMsg.A.no(selectedRow).ezUpTimeZone_A2);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTime_B3, sMsg.A.no(selectedRow).ezUpTime_A3);
                setValue(sMsg.B.no(sMsg.B.getValidCount()).ezUpTimeZone_B3, sMsg.A.no(selectedRow).ezUpTimeZone_A3);
                // END 2017/08/03 T.Kanasaka [QC#18195,ADD]
                sMsg.B.setValidCount(sMsg.B.getValidCount() + 1);
            }
        }
        // END 2016/07/15 T.Tomita [QC#11811, ADD]

        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        int rowIndex = NSAL1200CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL1200CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL1200Scrn00_TBLColumnSort(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
        NSAL1200CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            // START 2016/08/01 [QC#12242, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxRowNum_A.setValue(i + 1);
            }
            // END 2016/08/01 [QC#12242, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set Paging Data
            NSAL1200CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    // START 2017/08/03 T.Kanasaka [QC#18195,DEL]
//    // add start 2016/07/13 QC#11813
//    private void doProcess_NSAL1200Scrn00_OpenWin_NSAL1290(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {
//        cMsg.mtrLbNm.clear();
//
//        NSAL1200_ACMsg acMsg = cMsg.A.no(cMsg.xxRowNum.getValueInt());
//        if (!hasValue(acMsg.mtrLbDescTxt_CN)) {
//            return;
//        }
//
//        MTR_LBTMsg tMsg = new MTR_LBTMsg();
//        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
//        setValue(tMsg.mtrLbDescTxt, acMsg.mtrLbDescTxt_CN.getValue());
//        MTR_LBTMsgArray tMsgList = (MTR_LBTMsgArray) ZYPCodeDataUtil.findByCondition(tMsg);
//
//        if (tMsgList.length() > 0) {
//            setValue(cMsg.mtrLbNm, tMsgList.no(0).mtrLbNm);
//        }
//    }
//    // add end 2016/07/13 QC#11813
    // END 2017/08/03 T.Kanasaka [QC#18195,DEL]

    /**
     * get Search Data List
     * @param cMsg NSAL1200CMsg
     * @return Data List
     */
    private void getSearchData(NSAL1200CMsg cMsg, NSAL1200SMsg sMsg) {

        // get header
        MTR_GRPTMsg mtrGrp = new MTR_GRPTMsg();
        setValue(mtrGrp.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(mtrGrp.mtrGrpPk, cMsg.mtrGrpPk_H);
        mtrGrp = (MTR_GRPTMsg) EZDTBLAccessor.findByKey(mtrGrp);
        // START 2016/04/26 [QC#6700, MOD]
        if (mtrGrp != null) {
        // END   2016/04/26 [QC#6700, MOD]
            setValue(cMsg.mtrGrpNm_H, mtrGrp.mtrGrpNm);
            setValue(cMsg.mtrGrpTpCd_H, mtrGrp.mtrGrpTpCd);
            setValue(cMsg.mtrGrpDescTxt_H, mtrGrp.mtrGrpDescTxt);
            // add start 2016/12/08 QC#16418
            if (ZYPConstant.FLG_ON_Y.equals(mtrGrp.prcVldFlg.getValue())) {
                setValue(cMsg.prcVldFlg_H, ZYPConstant.CHKBOX_ON_Y);
            } else {
                cMsg.prcVldFlg_H.clear();
            }
            // add end 2016/12/08 QC#16418
            setValue(cMsg.ezUpTime_H, mtrGrp.ezUpTime);
            setValue(cMsg.ezUpTimeZone_H, mtrGrp.ezUpTimeZone);
            // START 2016/12/01 K.Kojima [QC#14204,ADD]
            setValue(cMsg.xxRecHistCratTs, mtrGrp.ezInTime);
            setValue(cMsg.xxRecHistCratByNm, mtrGrp.ezInUserID);
            setValue(cMsg.xxRecHistUpdTs, mtrGrp.ezUpTime);
            setValue(cMsg.xxRecHistUpdByNm, mtrGrp.ezUpUserID);
            setValue(cMsg.xxRecHistTblNm, mtrGrp.ezTableID);
            // END 2016/12/01 K.Kojima [QC#14204,ADD]
        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowCurNum.clear();
            cMsg.xxPageShowTotNum.clear();
        }

        // copy CMsg to SMsg
        EZDMsg.copy(cMsg, null, sMsg, null);

        // get list
        S21SsmEZDResult ssmResult = NSAL1200Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            // set Paging Data
            NSAL1200CommonLogic.pagenation(cMsg, sMsg, 0);

        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowCurNum.clear();
            cMsg.xxPageShowTotNum.clear();
        }
    }
}
