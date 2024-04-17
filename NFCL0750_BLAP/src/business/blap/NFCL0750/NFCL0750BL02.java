/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL0750;

import static business.blap.NFCL0750.constant.NFCL0750Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL0750.common.NFCL0750CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_WRT_OFF_RQST_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/18   Hitachi         T.Tsuchida      Update          QC#5703
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/04/20   Hitachi         J.Kim           Update          QC#24885
 *</pre>
 */
public class NFCL0750BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NFCL0750CMsg cMsg = (NFCL0750CMsg) arg0;
        NFCL0750SMsg sMsg = (NFCL0750SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        // Common Column Order Text
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL0750_INIT".equals(screenAplID)) {
                doProcess_NFCL0750_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_CMN_Clear(cMsg, sMsg);
                // START 2016/03/18 T.Tsuchida [QC#5703,MOD]
                ZYPGUITableColumn.getColData(cMsg, sMsg);
                // END 2016/03/18 T.Tsuchida [QC#5703,MOD]
            } else if ("NFCL0750Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_Search(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_SrchCustNm".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_SrchCustNm(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NFCL0750Scrn00_OpenWin_Detail".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_OpenWin_Detail(cMsg, sMsg);
                // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
            } else if ("NFCL0750Scrn00_OnChange_WrtOffRqstTp".equals(screenAplID)) {
                doProcess_NFCL0750Scrn00_OnChange_WrtOffRqstTp(cMsg, sMsg);
                // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
            }
            
        } finally {
            // Set Common Column Order Text of SMsg
            sMsg.xxComnColOrdTxt.clear();
            setValue(sMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL0750_INIT(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        NFCL0750CommonLogic.clearMsg(cMsg, sMsg);
        NFCL0750CommonLogic.setInitParams(cMsg, getGlobalCompanyCode());
        NFCL0750CommonLogic.createPullDown(cMsg);
        NFCL0750CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFCL0750Scrn00_CMN_Clear(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        doProcess_NFCL0750_INIT(cMsg, sMsg);
    }

    private void doProcess_NFCL0750Scrn00_CMN_Download(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        // START 2017/08/21 T.Tsuchida [QC#19573,MOD]
        //cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME + "_" + cMsg.slsDt.getValue()), ".csv");
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
        // END 2017/08/21 T.Tsuchida [QC#19573,MOD]
        NFCL0750CommonLogic.writeCsvFile(cMsg, sMsg);
    }

    private void doProcess_NFCL0750Scrn00_PageJump(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        //cMsg.xxRadioBtn_A.clear();
        setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt();
        NFCL0750CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int rowIndex = NFCL0750CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NFCL0750CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NFCL0750Scrn00_PageNext(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        //cMsg.xxRadioBtn_A.clear();
        setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL0750CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFCL0750CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFCL0750Scrn00_PagePrev(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        //cMsg.xxRadioBtn_A.clear();
        setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL0750CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFCL0750CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NFCL0750Scrn00_Search(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        if (NFCL0750CommonLogic.isErrorSearchCondition(cMsg)) {
            return;
        }

        NFCL0750CommonLogic.getSearchData(cMsg, sMsg);
        NFCL0750CommonLogic.setDisplayData(cMsg, sMsg);

        NFCL0750CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NFCL0750Scrn00_SrchCustNm(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        if (NFCL0750CommonLogic.existDsAcctCustNum(cMsg)) {
            NFCL0750CommonLogic.getDsAcctCustNm(cMsg);
        }
    }

    private void doProcess_NFCL0750Scrn00_TBLColumnSort(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Show 1st page
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
        }
    }

    private void doProcess_NFCL0750Scrn00_OpenWin_Detail(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        if (!hasValue(cMsg.xxRadioBtn_A)) {
            cMsg.setMessageInfo(NFCM0094E);
        }
        int rowNum = cMsg.xxRadioBtn_A.getValueInt();
        setValue(cMsg.arWrtOffRqstPk_P, sMsg.A.no(rowNum).arWrtOffRqstPk_A);
        if (hasValue(sMsg.A.no(rowNum).wrtOffRqstGrpCd_A)) {
            setValue(cMsg.wrtOffRqstGrpCd_P, sMsg.A.no(rowNum).wrtOffRqstGrpCd_A);
        } else {
            setValue(cMsg.wrtOffRqstGrpCd_P, WRT_OFF_RQST_GRP_CD_IS_NONE);
        }
        setValue(cMsg.wrtOffRqstUsrId_P, sMsg.A.no(rowNum).wrtOffRqstUsrId_A);
        setValue(cMsg.arWrtOffRqstTpCd_P, sMsg.A.no(rowNum).arWrtOffRqstTpCd_A);
        // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
        setValue(cMsg.xxFromDt_P, cMsg.xxFromDt_H);
        setValue(cMsg.xxThruDt_P, cMsg.xxThruDt_H);
        // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
    }

    // START 2017/08/21 T.Tsuchida [QC#19573,ADD]
    private void doProcess_NFCL0750Scrn00_OnChange_WrtOffRqstTp(NFCL0750CMsg cMsg, NFCL0750SMsg sMsg) {
        String strUsrId =  "";
        if(hasValue(cMsg.arWrtOffRqstTpDescTxt_SV)
                && (AR_WRT_OFF_RQST_TP.COMPLETE_STRATEGY.equals(cMsg.arWrtOffRqstTpDescTxt_SV.getValue())
                        || AR_WRT_OFF_RQST_TP.SYSTEM.equals(cMsg.arWrtOffRqstTpDescTxt_SV.getValue()))) {
            strUsrId =  ZYPCodeDataUtil.getVarCharConstValue(AR_SYS_BAT_USR_ID, cMsg.glblCmpyCd.getValue());
            setValue(cMsg.wrtOffRqstUsrId_H, strUsrId);
        } else {
            strUsrId =  cMsg.getUserID();
            setValue(cMsg.wrtOffRqstUsrId_H, strUsrId);
        }
    }
    // END 2017/08/21 T.Tsuchida [QC#19573,ADD]
}
