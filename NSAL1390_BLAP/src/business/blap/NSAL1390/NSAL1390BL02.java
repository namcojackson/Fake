/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1390;

import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0011E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0015E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NSAM0320E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NZZM0000E;
import static business.blap.NSAL1390.constant.NSAL1390Constant.NZZM0001W;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1390.common.NSAL1390CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 * 2017/10/27   Hitachi         K.Kojima        Update          QC#21586
 *</pre>
 */
public class NSAL1390BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL1390CMsg bizMsg = (NSAL1390CMsg) cMsg;
            NSAL1390SMsg glblMsg = (NSAL1390SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1390_INIT".equals(screenAplID)) {
                doProcess_NSAL1390_INIT(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_Search(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_Add".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_Add(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_Delete(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_OpenWin_RegionLine".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_OpenWin_RegionLine(bizMsg);
            } else if ("NSAL1390Scrn00_OpenWin_BranchLine".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_OpenWin_BranchLine(bizMsg);

            } else if ("NSAL1390Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_PageJump(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_Search(bizMsg, glblMsg);
            } else if ("NSAL1390Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL1390Scrn00_TBLColumnSort(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param bizMsg NSAL1390CMsg
     * @param glblMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390_INIT(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxMaxSrchCnt, new BigDecimal(glblMsg.A.length()));

        // START 2017/10/27 K.Kojima [QC#21586,MOD]
        // ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_PL, bizMsg.lineBizTpDescTxt_PL);
        NSAL1390CommonLogic.setSvcLineBizPulldown(bizMsg);
        // END 2017/10/27 K.Kojima [QC#21586,MOD]
    }

    /**
     * do process (Search)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_Search(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        // Master Check
        bizMsg.svcRgDescTxt_H.clear();
        if (ZYPCommonFunc.hasValue(bizMsg.svcRgPk_H)) {
            String svcRgDescTxt = NSAL1390CommonLogic.getSvcRgNm(bizMsg, bizMsg.svcRgPk_H.getValue());

            if (!ZYPCommonFunc.hasValue(svcRgDescTxt)) {
                bizMsg.svcRgPk_H.setErrorInfo(1, NSAM0011E, new String[] {"Region" });
                return;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.svcRgDescTxt_H, svcRgDescTxt);
        }

        bizMsg.svcContrBrDescTxt_H.clear();
        if (ZYPCommonFunc.hasValue(bizMsg.svcContrBrCd_H)) {

            List<String> svcContrBrDescTxtList = NSAL1390Query.getInstance().getSvcContrBrNmLike(bizMsg, bizMsg.svcContrBrCd_H.getValue());

            if (svcContrBrDescTxtList.size() == 0) {
                bizMsg.svcContrBrCd_H.setErrorInfo(1, NSAM0011E, new String[] {"Branch" });
                return;
            } else if (svcContrBrDescTxtList.size() == 1) {
                if (!bizMsg.svcContrBrCd_H.getValue().contains("%")) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcContrBrDescTxt_H, svcContrBrDescTxtList.get(0));
                }
            }
        }

        // Clear
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        // Search
        S21SsmEZDResult ssmResult = NSAL1390Query.getInstance().search(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            }
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, 0);
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
    }

    /**
     * do process (Add)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_Add(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        if (bizMsg.A.getValidCount() != 0) {
            int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);
        }

        if (glblMsg.A.length() == glblMsg.A.getValidCount()) {
            bizMsg.setMessageInfo(NSAM0320E, new String[] {"SETUP", String.valueOf(glblMsg.A.length()) });
            return;
        }

        int firstNumOnPage = (glblMsg.A.getValidCount() / bizMsg.A.length()) * bizMsg.A.length();
 
        glblMsg.A.no(glblMsg.A.getValidCount()).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, firstNumOnPage);
    }

    /**
     * do process (Delete)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_Delete(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);

        if (checkList.size() == 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NSAM0015E);
            }
            return;
        }

        for (int checkIndex : checkList) {
            NSAL1390_ASMsg glblAMsg = glblMsg.A.no(checkIndex);

            if (ZYPCommonFunc.hasValue(glblAMsg.ezUpTime_A)) {
                NSAL1390_BSMsg delgGlblMsg = glblMsg.B.no(glblMsg.B.getValidCount());
                NSAL1390CommonLogic.copyDelMsg(glblAMsg, delgGlblMsg);
                glblMsg.B.setValidCount(glblMsg.B.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(glblMsg.A, checkList);

        if (glblMsg.A.getValidCount() == 0) {
            ZYPTableUtil.clear(bizMsg.A);

            bizMsg.xxPageShowFromNum.setValue(0);
            bizMsg.xxPageShowToNum.setValue(0);
            bizMsg.xxPageShowOfNum.setValue(0);
            return;
        }

        if (pageFromNum >= glblMsg.A.getValidCount()) {
            pageFromNum = pageFromNum - bizMsg.A.length();
        }
        NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, pageFromNum);
    }

    /**
     * do process (OpenWin_RegionLine)
     * @param cMsg NSAL1390CMsg
     */
    private void doProcess_NSAL1390Scrn00_OpenWin_RegionLine(NSAL1390CMsg bizMsg) {

        int cellIdx = bizMsg.xxCellIdx.getValueInt();
        NSAL1390_ACMsg acMsg = bizMsg.A.no(cellIdx);

        // Region
        String svcRgDescTxt = NSAL1390CommonLogic.getSvcRgNm(bizMsg, acMsg.svcRgPk_A.getValue());
        if (!ZYPCommonFunc.hasValue(svcRgDescTxt)) {
            acMsg.svcRgDescTxt_A.setValue("");
            return;
        }
        ZYPEZDItemValueSetter.setValue(acMsg.svcRgDescTxt_A, svcRgDescTxt);

    }

    /**
     * do process (OpenWin_BranchLine)
     * @param cMsg NSAL1390CMsg
     */
    private void doProcess_NSAL1390Scrn00_OpenWin_BranchLine(NSAL1390CMsg bizMsg) {

        int cellIdx = bizMsg.xxCellIdx.getValueInt();
        NSAL1390_ACMsg acMsg = bizMsg.A.no(cellIdx);

        // Branch
        String svcContrBrDescTxt = NSAL1390CommonLogic.getSvcContrBrNm(bizMsg, acMsg.svcContrBrCd_A.getValue());
        if (!ZYPCommonFunc.hasValue(svcContrBrDescTxt)) {
            acMsg.svcContrBrDescTxt_A.setValue("");
            return;
        }
        ZYPEZDItemValueSetter.setValue(acMsg.svcContrBrDescTxt_A, svcContrBrDescTxt);

    }

    /**
     * do process (PagePrev)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_PagePrev(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);

        pageFromNum = pageFromNum - bizMsg.A.length();
        NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, pageFromNum);
    }

    /**
     * do process (PageNext)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_PageNext(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);

        pageFromNum = pageFromNum + bizMsg.A.length();
        NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, pageFromNum);
    }

    /**
     * do process (PageJump)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_PageJump(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);

        pageFromNum = (bizMsg.xxPageShowCurNum.getValueInt() - 1) * bizMsg.A.length();
        NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, pageFromNum);
    }

    /**
     * do process (Clear)
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     */
    private void doProcess_NSAL1390Scrn00_CMN_Clear(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        bizMsg.lineBizTpCd_H.clear();
        bizMsg.svcRgPk_H.clear();
        bizMsg.svcRgDescTxt_H.clear();
        bizMsg.svcContrBrCd_H.clear();
        bizMsg.svcContrBrDescTxt_H.clear();
        // Clear
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
    }

    private void doProcess_NSAL1390Scrn00_TBLColumnSort(NSAL1390CMsg bizMsg, NSAL1390SMsg glblMsg) {

        int pageFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NSAL1390CommonLogic.setPagenation(bizMsg, glblMsg, pageFromNum);

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            NSAL1390CommonLogic.pagenation(bizMsg, glblMsg, 0);
        }

    }

}
